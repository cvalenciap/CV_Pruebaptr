$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

var accion = false;

function inicializarVariables() {

	var divMensajeInformacion = null;
	var txtCodigo = null;
	var cboPuntoMuestreo = null;
	var txtVolumen = null;
	var txtOdi = null;
	var txtOdf = null;	
	var txtDilc = null;
	var txtDbo5 = null;
	var btnGrabar = null;
	var btnRegresar = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var divMantenimiento = null;	
	var cboTipoFisico = null;
	var txtDeplec = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	divMantenimiento = $("#divMantenimiento");
	txtCodigo = $('#txtCodigo');
	cboPuntoMuestreo = $('#cboPuntoMuestreo');
	txtVolumen = $('#txtVolumen');
	txtOdi = $('#txtOdi');
	txtOdf = $('#txtOdf');
	txtDilc = $('#txtDilc');
	txtDbo5 = $('#txtDbo5');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');	
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboTipoFisico = $("#cboTipoFisico");
	txtDeplec = $("#txtDeplec");
	
	inputDecimal("txtVolumen", 3, 1);
	inputDecimal("txtOdi", 3,2);
	inputDecimal("txtOdf", 3, 2);
	inputDecimal("txtDilc", 3,2);
	
	
	agregarListaItem(cboPuntoMuestreo, ltaPuntoMuestra, "idPuntoMuestra", "descripcionPunto");
	agregarListaItem(cboTipoFisico, ltaTipoFisico, "idSubParametro", "descripcionCorta");
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaFisicoQuimico', {regresar:ConstanteServices.REGRESAR});
	});
	
	btnGrabar.click(function() {
		if(validarFormularioDatos()){
			var tituloModal = 'Mantenimiento Fisico Quimico';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarFisicoQuimico()', '', tituloModal);
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

function grabarFisicoQuimico(){
	$.ajax({
		url: "./registrarFisicoQuimico",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjFisicoQuimico()),
			 volumenOriginal : volumenOriginal ,
			 idPuntoMuestraOriginal : idPuntoMuestraOriginal
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaFisicoQuimico', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjFisicoQuimico(){    
	var obj = {};
	obj.idFisicoQuimico = !IsEmpty(objFisicoQuimico.idFisicoQuimico) ? objFisicoQuimico.idFisicoQuimico : null;  
	obj.idPtarxSector = !IsEmpty(objFisicoQuimico.idPtarxSector) ? objFisicoQuimico.idPtarxSector : null;  
	obj.idPuntoMuestra = cboPuntoMuestreo.val();
	obj.idTipoFisico = cboTipoFisico.val();
	obj.nNumVolumen   = txtVolumen.val();
	obj.nNumOdi = txtOdi.val();
	obj.nNumOdf = txtOdf.val();
	obj.nuDeplec = txtDeplec.val();
	obj.fechaInicioString = fechaInicio;
	obj.fechaFinString = fechaFin;
    return obj;
}

function pintarDataFormulario(){
	txtCodigo.val(""+objFisicoQuimico.idFisicoQuimico);
	cboPuntoMuestreo.val(""+objFisicoQuimico.idPuntoMuestra);
	cboTipoFisico.val(""+objFisicoQuimico.idTipoFisico);
	txtVolumen.val(""+objFisicoQuimico.nNumVolumen);
	txtOdi.val(""+objFisicoQuimico.nNumOdi);
	txtOdf.val(""+objFisicoQuimico.nNumOdf);
	txtDilc.val(""+objFisicoQuimico.nuDilc);
	txtDbo5.val(""+objFisicoQuimico.nNumDbo5);
	txtDeplec.val(""+objFisicoQuimico.nuDeplec);
}

function calcularValores(){
	var volumen = parseFloat( !IsEmpty(txtVolumen.val()) ? txtVolumen.val() : "0" );
	var odi = parseFloat( !IsEmpty(txtOdi.val()) ? txtOdi.val() : "0" );
	var odf = parseFloat( !IsEmpty(txtOdf.val()) ? txtOdf.val() : "0" );
	var DBO5 = ((odi - odf) * 300) / volumen;
	var Dilc = (volumen * 100) / 300 ;
	var deplec = (odf / odi)*100;
	DBO5 = (isNaN(DBO5))? "0": DBO5;
	Dilc = (isNaN(Dilc))? "0": Dilc;	
	deplec = (isNaN(deplec))? "0": deplec;
	DBO5 = (DBO5 == Infinity || DBO5 == -Infinity )? "0": DBO5;
	Dilc = (Dilc == Infinity || Dilc == -Infinity )? "0": Dilc;	
	deplec = (deplec == Infinity || deplec == -Infinity )? "0": deplec;	
	DBO5 = retornarEnteroRedondear(DBO5);
	Dilc = cortarDecimalRedondear(Dilc,2);
	deplec = cortarDecimalRedondear(deplec,2);
	txtDilc.val(""+Dilc);
	txtDbo5.val(""+DBO5);
	txtDeplec.val(""+deplec);
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();        
    if(IsEmpty(cboPuntoMuestreo.val()))
    {
    	mostrarMensaje('El campo Punto de Muestreo es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(cboTipoFisico.val()))
    {
    	mostrarMensaje('El campo Tipo Fisico es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtVolumen.val()))
    {
    	mostrarMensaje('El campo Volumen mL es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }   
    if(IsEmpty(txtOdi.val()))
    {
    	mostrarMensaje('El campo ODi mg/L es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtOdf.val()))
    {
    	mostrarMensaje('El campo ODf mg/L es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    
    if(eval(txtOdf.val()) > eval(txtOdi.val())){
    	mostrarMensaje('El campo ODf mg/L debe ser Menor a ODi mg/L', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }    
    return estado;    
}