$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
function inicializarVariables() {
	var divMantenimiento 	= null;
	var btnGrabar 	= null;
	var btnRegresar	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPlantaTratamiento = null;
	var cboSector = null;
	
}
function cargarComponentes() {
	divMantenimiento = $('#divMantenimiento');
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboPlantaTratamiento = $('#cboPlantaTratamiento');
	cboSector = $('#cboSector');
	
	cboPlantaTratamiento.html("");
    $.each(ltaPlantaTratamiento, function(i, item) {
    	cboPlantaTratamiento.append($("<option/>").attr("value",ltaPlantaTratamiento[i].idDetalleGeneral).text(ltaPlantaTratamiento[i].descripcion)); 
    }); 
    
    cboSector.html("");
    $.each(ltaSector, function(i, item) {
    	cboSector.append($("<option/>").attr("value",ltaSector[i].idDetalleGeneral).text(ltaSector[i].descripcion)); 
    }); 
	
     
    document.getElementById("cboSector").selectedIndex=0;
    
	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimiento.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}	
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) { 
		cboPlantaTratamiento.attr('disabled','disabled');
	}
	
	
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaPtarxSector', {regresar:ConstanteServices.REGRESAR});
	}); 
	
	btnGrabar.click(function() {
   		if(validarFormularioDatos()){
   			var tituloModal = 'Mantenimiento de Planta de Tratamiento por Sector';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarPtarxSector()', '', tituloModal);            	
        }
    }); 
	pintandoFormulario();
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();
    if(IsEmpty(cboPlantaTratamiento.val()))
    {
    	mostrarMensaje('El campo Nombre de Planta es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    }    
    if(IsEmpty(cboSector.val())){
		    mostrarMensaje('El campo Nombre de Sector es obligatorio', ConstanteServices.IMAGEN_DANGER);
		    estado = false;
    }
	
    return estado;
}

function grabarPtarxSector() {
	$.ajax({
		url: "./grabarPtarxSector",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    			: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjMantenimiento())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaPtarxSector', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjMantenimiento(){    
	var descripcionCboTratamiento = extraerTextoCombo("cboPlantaTratamiento");
	var descripcionCboSector = extraerTextoCombo("cboSector");
	var obj = {};
	obj.idPtarxsector = !IsEmpty(objPtarxSector.idPtarxsector) ? objPtarxSector.idPtarxsector : null;    
	obj.idPtar = cboPlantaTratamiento.val();
	obj.idSector = cboSector.val();   
	obj.descripcionPtarxSector = descripcionCboTratamiento + " - " +descripcionCboSector;
    return obj;
} 

function pintandoFormulario(){
	if(objPtarxSector.idPtarxsector != null && objPtarxSector.idPtarxsector != ""){
		$("#txtCodigo").val(""+objPtarxSector.idPtarxsector);
	}
	if(objPtarxSector.idPtar != null && objPtarxSector.idPtar != ""){
		$("#cboPlantaTratamiento").val(""+objPtarxSector.idPtar);
	}else{
		 document.getElementById("cboPlantaTratamiento").selectedIndex=0;  
	}
	if(objPtarxSector.idSector != null && objPtarxSector.idSector != ""){
		$("#cboSector").val(""+objPtarxSector.idSector);
	}else{
		 document.getElementById("cboSector").selectedIndex=0;  
	}
}