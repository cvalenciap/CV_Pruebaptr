$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

var accion = false;

function inicializarVariables() {

	var divMensajeInformacion = null;
	var txtCodigo = null;
	var txtCombinacion = null;
	var txtNmp100 = null;
	var txtPorDebajo = null;
	var txtSobre = null;	
	var btnGrabar = null;
	var btnRegresar = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var divMantenimiento = null;	
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	divMantenimiento = $("#divMantenimiento");
	txtCodigo = $('#txtCodigo');
	txtCombinacion = $('#txtCombinacion');
	txtNmp100 = $('#txtNmp100');
	txtPorDebajo = $('#txtPorDebajo');
	txtSobre = $('#txtSobre');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');	
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	inputDecimal("txtNmp100", 13, 8);
	inputDecimal("txtPorDebajo", 13, 8);
	inputDecimal("txtSobre", 13, 8);
	txtCombinacion[0].maxLength = "20";
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaTuboDilucion', {regresar:ConstanteServices.REGRESAR});
	}); 
	
	btnGrabar.click(function() {
		if(validarFormularioDatos()){
			var tituloModal = 'Mantenimiento de Tubo de Dilucion';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarTuboDilucion()', '', tituloModal);
		}
	}); 
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormulario();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimiento.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();        
    if(IsEmpty(txtCombinacion.val()))
    {
    	mostrarMensaje('El campo Combinación es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }   
    if(IsEmpty(txtNmp100.val()))
    {
    	mostrarMensaje('El campo Lista NMP/100 mL es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtPorDebajo.val()))
    {
    	mostrarMensaje('El campo Por Debajo es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtSobre.val()))
    {
    	mostrarMensaje('El campo Sobre es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    return estado;    
}
function grabarTuboDilucion(){
	$.ajax({
		url: "./grabarTuboDilucion",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 tuboDilucionBean	: JSON.stringify(obtenerObjTuboDilucion())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaTuboDilucion', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjTuboDilucion(){    
	var obj = {};
	obj.idTuboDilucion = !IsEmpty(objTuboDilucion.idTuboDilucion) ? objTuboDilucion.idTuboDilucion : null;  
	obj.combinacion = !IsEmpty(txtCombinacion.val()) ? txtCombinacion.val() : " ";  
	obj.nMP100 = txtNmp100.val();
	obj.debajo95   = txtPorDebajo.val();
	obj.sobre95 = txtSobre.val();
    return obj;
} 

function pintarDataFormulario(){
	txtCodigo.val(""+objTuboDilucion.idTuboDilucion);
	txtCombinacion.val(""+objTuboDilucion.combinacion);
	txtNmp100.val(""+objTuboDilucion.nMP100);
	txtPorDebajo.val(""+objTuboDilucion.debajo95);
	txtSobre.val(""+objTuboDilucion.sobre95);	
}