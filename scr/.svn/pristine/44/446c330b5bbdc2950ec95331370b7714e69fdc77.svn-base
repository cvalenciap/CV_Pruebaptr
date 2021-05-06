$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

var accion = false;

function inicializarVariables() {

	var divMensajeInformacion = null;
	var txtCodigo = null;
	var cboTipoSensor = null;
	var txtAirePdte = null;
	var txtAireSaturacion = null;
	var txtSolucionPdte = null;	
	var txtSolucionSaturacion = null;
	var txtObservacion = null;
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
	cboTipoSensor = $('#cboTipoSensor');
	txtAirePdte = $('#txtAirePdte');
	txtAireSaturacion = $('#txtAireSaturacion');
	txtSolucionPdte = $('#txtSolucionPdte');
	txtSolucionSaturacion = $('#txtSolucionSaturacion');
	txtObservacion = $('#txtObservacion');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');	
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	inputDecimal("txtAirePdte", 5, 2);
	inputDecimal("txtAireSaturacion", 5, 2);
	inputDecimal("txtSolucionPdte", 5, 2);
	inputDecimal("txtSolucionSaturacion", 5, 2);
	
	agregarListaItem(cboTipoSensor, ltaTipoOximetro, "idDetalleGeneral", "descripcion");
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaOximetro', {regresar:ConstanteServices.REGRESAR});
	});
	
	btnGrabar.click(function() {
		if(validarFormularioDatos()){
			var tituloModal = 'Determinación Oxímetros';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarOximetro()', '', tituloModal);
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

function grabarOximetro(){
	$.ajax({
		url: "./registrarOximetro",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjOximetro())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaOximetro', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjOximetro(){    
	var obj = {};
	obj.idOximetro = !IsEmpty(objOximetro.idOximetro) ? objOximetro.idOximetro : null;
	obj.idSensor = cboTipoSensor.val();
	obj.numAirePdte   = txtAirePdte.val();
	obj.numSaturacionAirePdte = txtAireSaturacion.val();
	obj.numSolucionPdte = txtSolucionPdte.val();
	obj.numSaturacionSolucionPdte = txtSolucionSaturacion.val();
	obj.descripcionObservacion = txtObservacion.val();
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		obj.fechaRegistroString = objOximetro.fechaRegistroString;
	}
    return obj;
}

function pintarDataFormulario(){
	txtCodigo.val(""+objOximetro.idOximetro);
	cboTipoSensor.val(""+objOximetro.idSensor);
	txtAirePdte.val(""+objOximetro.numAirePdte);
	txtAireSaturacion.val(""+objOximetro.numSaturacionAirePdte);
	txtSolucionPdte.val(""+objOximetro.numSolucionPdte);
	txtSolucionSaturacion.val(""+objOximetro.numSaturacionSolucionPdte);
	txtObservacion.val(""+objOximetro.descripcionObservacion);
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();        
    if(IsEmpty(cboTipoSensor.val()))
    {
    	mostrarMensaje('El campo Tipo de Sensor es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtAirePdte.val()))
     {
      	mostrarMensaje('El campo Calibración Aire Pendiente es obligatorio', ConstanteServices.IMAGEN_DANGER);
      	estado = false;
      	return estado;
     }
    if(IsEmpty(txtAireSaturacion.val()))
    {
    	mostrarMensaje('El campo Calibración Aire % Saturación es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }  
    if(IsEmpty(txtSolucionPdte.val()))
    {
     	mostrarMensaje('El campo Calibración Solución Cero Pendiente es obligatorio', ConstanteServices.IMAGEN_DANGER);
     	estado = false;
     	return estado;
    }
   if(IsEmpty(txtSolucionSaturacion.val()))
   {
   	mostrarMensaje('El campo Calibración Solución Cero % Saturación es obligatorio', ConstanteServices.IMAGEN_DANGER);
   	estado = false;
   	return estado;
   }
   if(parseFloat(txtAireSaturacion.val())>100)
   {
   	mostrarMensaje('El campo Calibración Aire % Saturación máximo es 100', ConstanteServices.IMAGEN_DANGER);
   	estado = false;
   	return estado;
   }
   if(parseFloat(txtSolucionSaturacion.val())>100)
   {
   	mostrarMensaje('El campo Calibración Solución Cero % Saturación máximo es 100', ConstanteServices.IMAGEN_DANGER);
   	estado = false;
   	return estado;
   }
    
    return estado;    
}