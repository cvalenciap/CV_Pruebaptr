$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

function inicializarVariables() {
	console.log("prueba modal");
	var divMantenimiento = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var txtCodigo = null;
	var txtNombre = null;
	var txtMensaje = null;
	var btnGrabar = null;
	var btnRegresar = null;
}

function cargarComponentes(){
	divMantenimiento = $('#divMantenimiento');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	txtCodigo = $('#txtCodigo');
	txtNombre = $('#txtNombre');
	txtMensaje = $('#txtMensaje');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');

	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormulario();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimiento.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}
	
	btnGrabar.click(function() {
		if(validarFormularioDatos()){
			var tituloModal = 'Configuración de Alertas';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarAlerta()', '', tituloModal);
		}
	});
}

function pintarDataFormulario(){
	txtCodigo.val(""+objAlerta.idAlertas);
	txtNombre.val(""+objAlerta.nombreAlertas);
	txtMensaje.val(""+objAlerta.descripcionAlertas);
}


function grabarAlerta(){
	$.ajax({
		url: "./grabarAlerta",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 alertaBean	: JSON.stringify(obtenerObjAlerta())
		}
	}).done(function(respuestaBean) {
		if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){
			buscarAlertas();
			mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);
    		$("#btnRegresar").click();
        } else {
        	$('#divMensajeInformacionModal').html("");
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER, 'divMensajeInformacionModal');
        	cerrarModal();
        }
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}


function anularRegistroAnalista(idAlertaAnalista) {
	$.ajax({
		url : "./anularRegistroAnalista",
		type : "POST",
		dataType : "json",
		async:true,
		data : {
			idAlertaAnalista : (!IsEmpty(idAlertaAnalista) ? idAlertaAnalista : null)
		}
	}).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){		
    		mostrarMensajeModal(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensajeModal(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();        
    if(IsEmpty(txtCodigo.val()))
    {
    	mostrarMensaje('El campo Código es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtNombre.val()))
    {
    	mostrarMensaje('El campo Nombre es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtMensaje.val()))
    {
    	mostrarMensaje('El campo Mensaje es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    return estado;    
}

function obtenerObjAlerta(){    
	var obj = {};
	obj.idAlertas = !IsEmpty(objAlerta.idAlertas) ? objAlerta.idAlertas : null;  
	obj.nombreAlertas = txtNombre.val();
	obj.descripcionAlertas = txtMensaje.val();
    return obj;
}
