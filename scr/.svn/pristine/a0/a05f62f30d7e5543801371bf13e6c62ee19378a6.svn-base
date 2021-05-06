$(document).ready(function() {
			inicializarVariablesStandard();
 			cargarComponentesStandard();
});


function inicializarVariablesStandard() {
	var btnGrabarStandard = null;
	var divMantenimientoStandard = null;
	var txtCodigo = null;
	var txtInicialStandard = null;
	var txtFinalStandard = null
	var txtDepletionStandard = null;
	var txtVolumenStandard = null;
	var txtDepStandard = null;
	var txtBODStandard = null;
}

function cargarComponentesStandard() {
	btnGrabarStandard = $("#btnGrabarStandard");
	divMantenimientoStandard = $("#divMantenimientoStandard");
	btnGrabarStandard = $("#btnGrabarStandard");
	
	txtCodigo = $("#txtCodigo");
	txtVolumenGGAStandard = $("#txtVolumenGGAStandard");
	txtVolumenPolyStandard = $("#txtVolumenPolyStandard");
	txtInicialStandard = $("#txtInicialStandard");
	txtFinalStandard = $("#txtFinalStandard");
	txtDepletionStandard = $("#txtDepletionStandard");
	txtNetDepStandard = $("#txtNetDepStandard");
	txtDepStandard = $("#txtDepStandard");
	txtBODStandard = $("#txtBODStandard");
	
	
	inputNumerico("txtVolumenGGAStandard", 2);
	inputNumerico("txtVolumenPolyStandard", 2);
	
	inputDecimal("txtInicialStandard", 3, 2);
	inputDecimal("txtFinalStandard", 3, 2);	
	
	
	btnGrabarStandard.click(function(event){
		if(validarFormularioDatosStandard()){
			var tituloModal = 'Mantenimiento de Standard';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarStandard()', '', tituloModal);
		}
    });
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormularioStandard();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimientoStandard.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabarStandard").hide();
	}	
}

function validarFormularioDatosStandard(){
	var estado = true;            
    $('#mensajeInformacionStandardM').html("");        
    if(IsEmpty(txtVolumenGGAStandard.val()))
    {
    	mostrarMensaje('El campo Volumen (mL) of GGA es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtVolumenPolyStandard.val()))
    {
    	mostrarMensaje('El campo Volumen (mL) of PolySeed es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtInicialStandard.val()))
    {
    	mostrarMensaje('El campo Initial DO es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtFinalStandard.val()))
    {
    	mostrarMensaje('El campo Final DO es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
    	estado = false;
    	return estado;
    }
    if(eval(txtFinalStandard.val()) > eval(txtInicialStandard.val())){
    	mostrarMensaje('El campo Final DO debe ser Menor a Initial DO', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
    	estado = false;
    	return estado;
    } 
    return estado;
}

function grabarStandard(){
	$.ajax({
		url: "./grabarStandard",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo ,	
			 mantenimientoBean	: JSON.stringify(obtenerobjSemillaStandard())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
	    	buscarSemillaStandard();
    		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionStandard');
    		$("#btnCancelarStandard").click();
         } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'mensajeInformacionStandardM');
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	}); 
}

function obtenerobjSemillaStandard(){
	var obj = {};
	obj.idStandard = !IsEmpty(objSemillaStandard.idStandard) ? objSemillaStandard.idStandard : null;  
	obj.numVolGGa  = txtVolumenGGAStandard.val();
	obj.numVolPoly  = txtVolumenPolyStandard.val();
	obj.numInicial = txtInicialStandard.val(); 
	obj.numFinal = txtFinalStandard.val();
	obj.numDepletion = txtDepletionStandard.val();
	obj.numNetDep = txtNetDepStandard.val();
	obj.numPorcentajeDeplec = txtDepStandard.val();
	obj.numValorBOD = txtBODStandard.val();
	obj.numPromedioGGA = 0;
	return obj;
}

function pintarDataFormularioStandard(){
	txtCodigo.val(""+objSemillaStandard.idStandard);
	txtVolumenGGAStandard.val(""+objSemillaStandard.numVolGGa);
	txtVolumenPolyStandard.val(""+objSemillaStandard.numVolPoly)
	txtInicialStandard.val(""+objSemillaStandard.numInicial);
	txtFinalStandard.val(""+objSemillaStandard.numFinal);
	txtDepletionStandard.val(""+objSemillaStandard.numDepletion);
	txtNetDepStandard.val(""+objSemillaStandard.numNetDep);
	txtDepStandard.val(""+objSemillaStandard.numPorcentajeDeplec);
	txtBODStandard.val(""+objSemillaStandard.numValorBOD);
}

function calcularValoresStandard(){
	var inicial = parseFloat( !IsEmpty(txtInicialStandard.val()) ? txtInicialStandard.val() : "0" );
	var final = parseFloat( !IsEmpty(txtFinalStandard.val()) ? txtFinalStandard.val() : "0" );
	var volumenGGA = parseFloat( !IsEmpty(txtVolumenGGAStandard.val()) ? txtVolumenGGAStandard.val() : "0" );
	var depletion = eval(inicial - final);
	depletion = (isNaN(depletion))? "0": depletion;
	depletion = (depletion == Infinity || depletion == -Infinity )? "0": depletion;
	depletion = cortarDecimalRedondear(depletion,4);
	txtDepletionStandard.val(""+depletion);	
	var netDep = eval((inicial - final) - valorPromedioSCF);
	netDep = (isNaN(netDep))? "0": netDep;
	netDep = (netDep == Infinity || netDep == -Infinity )? "0": netDep;
	netDep = cortarDecimalRedondear(netDep,2);
	txtNetDepStandard.val(""+netDep);	
	var porcen_dep = eval(((inicial - final) - valorPromedioSCF) / inicial);
	porcen_dep = (isNaN(porcen_dep))? "0": porcen_dep;
	porcen_dep = (porcen_dep == Infinity || porcen_dep == -Infinity )? "0": porcen_dep;
	porcen_dep = eval(porcen_dep*100);
	porcen_dep = cortarDecimalRedondear(porcen_dep,1);
	txtDepStandard.val(""+porcen_dep);
	var bod = eval((((inicial - final) - valorPromedioSCF)*(300/volumenGGA)));
	bod = (isNaN(bod))? "0": bod;
	bod = (bod == Infinity || bod == -Infinity )? "0": bod;
	bod = cortarDecimalRedondear(bod,1);
	txtBODStandard.val(""+bod);	
}






