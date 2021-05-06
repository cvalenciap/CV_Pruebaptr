$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
function inicializarVariables() {
	var divMantenimiento 	= null;
	var txtCodigo = null;
	var txtVariable = null;
	var txtLetra = null;
	var btnGrabar 	= null;
	var btnRegresar	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var divMantenimiento = null;
}
function cargarComponentes() {
	divMantenimiento = $('#divMantenimiento');
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	txtVariable  = $('#txtVariable');
	txtLetra  = $('#txtLetra');
	txtCodigo  = $('#txtCodigo');
		
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormulario();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimiento.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}
	
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaVariables', {regresar:ConstanteServices.REGRESAR});
	}); 
	
	btnGrabar.click(function() {
   		if(validarFormularioDatos()){
   			var tituloModal = 'Mantenimiento de Variables';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarVariable()', '', tituloModal);            	
        }
    }); 
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();
	if((txtLetra.val()).length > 3) {
		mostrarMensaje('El campo Letra no debe superar los 3 caracteres.', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
	}
    if(IsEmpty(txtLetra.val()))
    {
    	mostrarMensaje('El campo Letra es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    }
    if(IsEmpty(txtVariable.val()))
    {
    	mostrarMensaje('El campo Nombre de Variable es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    }
    return estado;
}

function grabarVariable() {
	$.ajax({
		url: "./grabarVariable",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    			: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjVariable())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaVariables', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjVariable(){    
	var obj = {};
	obj.idVariable = !IsEmpty(objVariable.idVariable) ? objVariable.idVariable : null;
	obj.idParametro = !IsEmpty(objVariable.idParametro) ? objVariable.idParametro : null;
	obj.descripcionVariable = txtVariable.val().trim();
	obj.descripcionLetra = txtLetra.val().trim();
    return obj;
}

function pintarDataFormulario(){
	txtCodigo.val(""+objVariable.idVariable);
	txtVariable.val(""+objVariable.descripcionVariable);
	txtLetra.val(""+objVariable.descripcionLetra);
}