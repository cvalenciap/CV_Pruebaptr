$(document).ready(function() {
			inicializarVariablesSeeded();
 			cargarComponentesSeeded();
});


function inicializarVariablesSeeded() {
	var btnGrabarSeeded = null;
	var divMantenimientoSeeded = null;
	var txtCodigo = null;
	var txtInicialSeeded = null;
	var txtFinalSeeded = null
	var txtDepletionSeeded = null;
	var txtVolumenSeeded = null;
	var txtDepSeeded = null;
	var txtBODSeeded = null;
	var txtValorGGA = null;
	var txtSCFSeeded = null;
}

function cargarComponentesSeeded() {
	btnGrabarSeeded = $("#btnGrabarSeeded");
	divMantenimientoSeeded = $("#divMantenimientoSeeded");
	btnGrabarSeeded = $("#btnGrabarSeeded");
	txtCodigo = $("#txtCodigo");
	txtInicialSeeded = $("#txtInicialSeeded");
	txtFinalSeeded = $("#txtFinalSeeded");
	txtDepletionSeeded = $("#txtDepletionSeeded");
	txtVolumenSeeded = $("#txtVolumenSeeded");
	txtDepSeeded = $("#txtDepSeeded");
	txtBODSeeded = $("#txtBODSeeded");
	txtValorGGA = $("#txtValorGGA");
	txtSCFSeeded = $("#txtSCFSeeded");
	
	inputNumerico("txtVolumenSeeded", 2);
	inputDecimal("txtInicialSeeded", 3, 2);
	inputDecimal("txtFinalSeeded", 3, 2);	
	
	btnGrabarSeeded.click(function(event){
		if(validarFormularioDatosSeeded()){
			var tituloModal = 'Mantenimiento de Seeded';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarSeeded()', '', tituloModal);
		}
    });
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormularioSeeded();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimientoSeeded.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabarSeeded").hide();
	}	
}

function validarFormularioDatosSeeded(){
	var estado = true;            
    $('#mensajeInformacionSeededM').html("");        
    if(IsEmpty(txtVolumenSeeded.val()))
    {
    	mostrarMensaje('El campo Volumen es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSeededM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtInicialSeeded.val()))
    {
    	mostrarMensaje('El campo Initial DO B1 es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSeededM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtFinalSeeded.val()))
    {
    	mostrarMensaje('El campo Final DO B2 es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSeededM');
    	estado = false;
    	return estado;
    }
    if(eval(txtFinalSeeded.val()) > eval(txtInicialSeeded.val())){
    	mostrarMensaje('El campo Final DO B2 debe ser Menor a Initial DO B1', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSeededM');
    	estado = false;
    	return estado;
    } 
    return estado;
}

function grabarSeeded(){
	$.ajax({
		url: "./grabarSeeded",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo ,	
			 mantenimientoBean	: JSON.stringify(obtenerobjSemillaSeeded())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
	    	buscarSemillaSeeded();
    		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionSeeded');
    		$("#btnCancelarSeeded").click();
         } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSeededM');
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	}); 
}

function obtenerobjSemillaSeeded(){
	var obj = {};
	obj.idSeeded = !IsEmpty(objSemillaSeeded.idSeeded) ? objSemillaSeeded.idSeeded : null;  
	obj.numVolumen  = txtVolumenSeeded.val();
	obj.numInicial = txtInicialSeeded.val(); 
	obj.numFinal = txtFinalSeeded.val();
	obj.numDepletion = txtDepletionSeeded.val();
	obj.numPorcentajeDeplec = txtDepSeeded.val();
	obj.numValorBOD = txtBODSeeded.val();
	obj.numPromedio = 0;
	obj.numValorSCF = txtSCFSeeded.val();
	obj.numValGGa = !IsEmpty(objSemillaSeeded.numValGGa) ? objSemillaSeeded.numValGGa : 0;
	return obj;
}

function pintarDataFormularioSeeded(){
	txtCodigo.val(""+objSemillaSeeded.idSeeded);
	txtVolumenSeeded.val(""+objSemillaSeeded.numVolumen);
	txtInicialSeeded.val(""+objSemillaSeeded.numInicial);
	txtFinalSeeded.val(""+objSemillaSeeded.numFinal);
	txtDepletionSeeded.val(""+objSemillaSeeded.numDepletion);
	txtDepSeeded.val(""+objSemillaSeeded.numPorcentajeDeplec);
	txtBODSeeded.val(""+objSemillaSeeded.numValorBOD);
	txtSCFSeeded.val(""+objSemillaSeeded.numValorSCF);
}

function calcularValoresSeeded(){
	var inicial = parseFloat( !IsEmpty(txtInicialSeeded.val()) ? txtInicialSeeded.val() : "0" );
	var final = parseFloat( !IsEmpty(txtFinalSeeded.val()) ? txtFinalSeeded.val() : "0" );
	var volumen = parseFloat( !IsEmpty(txtVolumenSeeded.val()) ? txtVolumenSeeded.val() : "0" );
	//depletion INICIO
	var depletion = eval(inicial - final);
	depletion = (isNaN(depletion))? "0": depletion;
	depletion = (depletion == Infinity || depletion == -Infinity )? "0": depletion;
	depletion = cortarDecimalRedondear(depletion,4);
	txtDepletionSeeded.val(""+depletion);
	//depletion FIN
	//porcen_dep INICIO
	var porcen_dep = eval((inicial - final) / inicial);
	porcen_dep = (isNaN(porcen_dep))? "0": porcen_dep;
	porcen_dep = (porcen_dep == Infinity || porcen_dep == -Infinity )? "0": porcen_dep;
	porcen_dep = eval(porcen_dep*100);
	porcen_dep = cortarDecimalRedondear(porcen_dep,1);
	txtDepSeeded.val(""+porcen_dep);
	//porcen_dep FIN
	volumen = (isNaN(volumen))? "0": volumen;
	volumen = (volumen == Infinity || volumen == -Infinity )? "0": volumen;
	volumen = cortarDecimalRedondear(volumen,2);
	//bod INICIO
	var bod = eval((inicial - final)*(300/volumen));
	bod = (isNaN(bod))? "0": bod;
	bod = (bod == Infinity || bod == -Infinity )? "0": bod;
	bod = cortarDecimalRedondear(bod,1);
	txtBODSeeded.val(""+bod);	
	//bod FIN
	//sfc INICIO
	var sfc = eval(((inicial - final)*(300/volumen))*(objSemillaSeeded.numValGGa/300));
	sfc = (isNaN(sfc))? "0": sfc;
	sfc = (sfc == Infinity || sfc == -Infinity )? "0": sfc;
	sfc = cortarDecimalRedondear(sfc,4);
	txtSCFSeeded.val(""+sfc);
}