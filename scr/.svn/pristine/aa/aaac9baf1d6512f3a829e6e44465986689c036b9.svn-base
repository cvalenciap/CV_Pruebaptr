$(document).ready(function() {
			inicializarVariablesSamples();
 			cargarComponentesSamples();
});


function inicializarVariablesSamples() {
	var btnGrabarSamples = null;
	var divMantenimientoSamples = null;
	var txtCodigo = null;
	var txtBottleSamples = null;
	var txtVolumenSamples = null;
	var txtVolumenPolySamples = null;
	var txtInicialSamples = null;
	var txtFinalSamples = null;
	var txtDepletionSamples = null;
	var txtSCFSamples = null;
	var txtNetDepSamples = null;
	var txtDilFactorSamples = null;
	var txtBODSamples = null;
	
	
}

function cargarComponentesSamples() {
	btnGrabarSamples = $("#btnGrabarSamples");
	divMantenimientoSamples = $("#divMantenimientoSamples");
	btnGrabarSamples = $("#btnGrabarSamples");
	
	txtCodigo = $("#txtCodigo");
	txtBottleSamples = $("#txtBottleSamples");
	txtVolumenSamples = $("#txtVolumenSamples");
	txtVolumenPolySamples = $("#txtVolumenPolySamples");
	txtInicialSamples = $("#txtInicialSamples");
	txtFinalSamples = $("#txtFinalSamples");
	txtDepletionSamples = $("#txtDepletionSamples");
	txtSCFSamples = $("#txtSCFSamples");
	txtNetDepSamples = $("#txtNetDepSamples");
	txtDilFactorSamples = $("#txtDilFactorSamples");
	txtBODSamples = $("#txtBODSamples");
	
	
	inputNumerico("txtVolumenSamples", 2);
	inputNumerico("txtVolumenPolySamples", 2);
	
	inputDecimal("txtInicialSamples", 3, 2);
	inputDecimal("txtFinalSamples", 3, 2);	
	
	
	btnGrabarSamples.click(function(event){
		if(validarFormularioDatosSamples()){
			var tituloModal = 'Mantenimiento de Samples';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarSamples()', '', tituloModal);
		}
    });
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormularioSamples();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimientoSamples.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabarSamples").hide();
	}	
}

function validarFormularioDatosSamples(){
	var estado = true;            
    $('#mensajeInformacionSamplesM').html("");    
    if(IsEmpty(txtBottleSamples.val()))
    {
    	mostrarMensaje('El campo Bottle # es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtVolumenSamples.val()))
    {
    	mostrarMensaje('El campo Volumen (mL) of Sample es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtVolumenPolySamples.val()))
    {
    	mostrarMensaje('El campo Volumen (mL) of PolySeed es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtInicialSamples.val()))
    {
    	mostrarMensaje('El campo Initial DO D1 es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtFinalSamples.val()))
    {
    	mostrarMensaje('El campo Final DO D2 es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    }
    if(eval(txtFinalSamples.val()) > eval(txtInicialSamples.val())){
    	mostrarMensaje('El campo Final DO D2 debe ser Menor a Initial DO D1', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
    	estado = false;
    	return estado;
    } 
    return estado;
}

function grabarSamples(){
	$.ajax({
		url: "./grabarSamples",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo ,	
			 mantenimientoBean	: JSON.stringify(obtenerobjSemillaSamples()),
			 promedioSCF : valorPromedioSCF
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
	    	buscarSemillaSamples();
    		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionSamples');
    		$("#btnCancelarSamples").click();
         } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'mensajeInformacionSamplesM');
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	}); 
}

function obtenerobjSemillaSamples(){
	var obj = {};
	obj.idSamples = !IsEmpty(objSemillaSamples.idSamples) ? objSemillaSamples.idSamples : null;  
	obj.descripcionBotella = txtBottleSamples.val().trim();
	obj.numVolSample  = txtVolumenSamples.val();
	obj.numVolPoly  = txtVolumenPolySamples.val();
	obj.numInicial = txtInicialSamples.val(); 
	obj.numFinal = txtFinalSamples.val();
	obj.numDepletion = txtDepletionSamples.val();
	obj.numValorSCF = txtSCFSamples.val();
	obj.numValorNetDep = txtNetDepSamples.val();
	obj.numDilcFactor = txtDilFactorSamples.val();
	obj.numSampleBOD = txtBODSamples.val();
	obj.numPromedioDBO = 0;
	return obj;
}

function pintarDataFormularioSamples(){
	txtCodigo.val(""+objSemillaSamples.idSamples);
	txtBottleSamples.val(""+objSemillaSamples.descripcionBotella);
	txtVolumenSamples.val(""+objSemillaSamples.numVolSample);
	txtVolumenPolySamples.val(""+objSemillaSamples.numVolPoly)
	txtInicialSamples.val(""+objSemillaSamples.numInicial);
	txtFinalSamples.val(""+objSemillaSamples.numFinal);	
	txtDepletionSamples.val(""+objSemillaSamples.numDepletion);	
	txtSCFSamples.val(""+objSemillaSamples.numValorSCF);
	txtNetDepSamples.val(""+objSemillaSamples.numValorNetDep);
	txtDilFactorSamples.val(""+objSemillaSamples.numDilcFactor);
	txtBODSamples.val(""+objSemillaSamples.numSampleBOD);
}

function calcularValoresSamples(){
	var inicial = parseFloat( !IsEmpty(txtInicialSamples.val()) ? txtInicialSamples.val() : "0" );
	var final = parseFloat( !IsEmpty(txtFinalSamples.val()) ? txtFinalSamples.val() : "0" );
	var volumenSample = parseFloat( !IsEmpty(txtVolumenSamples.val()) ? txtVolumenSamples.val() : "0" );
	var depletion = eval(inicial - final);
	depletion = (isNaN(depletion))? "0": depletion;
	depletion = (depletion == Infinity || depletion == -Infinity )? "0": depletion;
	depletion = cortarDecimalRedondear(depletion,4);
	txtDepletionSamples.val(""+depletion);
	txtSCFSamples.val(""+valorPromedioSCF);
	var netDep = eval((inicial - final) - valorPromedioSCF);
	netDep = (isNaN(netDep))? "0": netDep;
	netDep = (netDep == Infinity || netDep == -Infinity )? "0": netDep;
	netDep = cortarDecimalRedondear(netDep,2);
	txtNetDepSamples.val(""+netDep);		
	var dilFactor = eval(volumenSample / 300);
	dilFactor = (isNaN(dilFactor))? "0": dilFactor;
	dilFactor = (dilFactor == Infinity || dilFactor == -Infinity )? "0": dilFactor;
	dilFactor = cortarDecimalRedondear(dilFactor,2);
	txtDilFactorSamples.val(""+dilFactor);	
	var bod = eval(((inicial - final) - valorPromedioSCF)/(volumenSample / 300));
	bod = (isNaN(bod))? "0": bod;
	bod = (bod == Infinity || bod == -Infinity )? "0": bod;
	bod = cortarDecimalRedondear(bod,2);
	txtBODSamples.val(""+bod);	
}






