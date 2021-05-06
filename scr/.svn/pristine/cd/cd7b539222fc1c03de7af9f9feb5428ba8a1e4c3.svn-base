$(document).ready(function() {
			inicializarVariablesBlanks();
 			cargarComponentesBlanks();
});


function inicializarVariablesBlanks() {
	var btnGrabarBlanks = null;
	var divMantenimientoBlanks = null;
	var txtCodigo = null;
	var txtInicial = null;
	var txtFinal = null
	var txtDepletion = null;
}

function cargarComponentesBlanks() {
	btnGrabarBlanks = $("#btnGrabarBlanks");
	divMantenimientoBlanks = $("#divMantenimientoBlanks");
	btnGrabarBlanks = $("#btnGrabarBlanks");
	txtCodigo = $("#txtCodigo");
	txtInicial = $("#txtInicial");
	txtFinal = $("#txtFinal");
	txtDepletion = $("#txtDepletion");
	
	inputDecimal("txtInicial", 3, 2);
	inputDecimal("txtFinal", 3, 2);	
	
	btnGrabarBlanks.click(function(event){
		if(validarFormularioDatosBlank()){
			var tituloModal = 'Mantenimiento de Blanks';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarBlanks()', '', tituloModal);
		}
    });
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormularioBlanks();
	}

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimientoBlanks.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabarBlanks").hide();
	}	
}

function validarFormularioDatosBlank(){
	var estado = true;            
    $('#mensajeInformacionBlanksM').html("");   
    if(IsEmpty(txtInicial.val()))
    {
    	mostrarMensaje('El campo Initial DO es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionBlanksM');
    	estado = false;
    	return estado;
    }
    if(IsEmpty(txtFinal.val()))
    {
    	mostrarMensaje('El campo Final DO es obligatorio', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionBlanksM');
    	estado = false;
    	return estado;
    }
    if(eval(txtFinal.val()) > eval(txtInicial.val())){
    	mostrarMensaje('El campo Final DO debe ser Menor a Initial DO', ConstanteServices.IMAGEN_DANGER,'mensajeInformacionBlanksM');
    	estado = false;
    	return estado;
    } 
    return estado;
}

function grabarBlanks(){
	$.ajax({
		url: "./grabarBlanks",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo ,	
			 mantenimientoBean	: JSON.stringify(obtenerobjSemillaBlanks())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
	    	buscarSemillaBlanks();
    		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionBlanks');
    		$("#btnCancelarBlanks").click();
         } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'mensajeInformacionBlanksM');
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	}); 
}

function obtenerobjSemillaBlanks(){
	var obj = {};
	obj.idBlank = !IsEmpty(objSemillaBlanks.idBlank) ? objSemillaBlanks.idBlank : null;  
	obj.numInicial = txtInicial.val(); 
	obj.numFinal = txtFinal.val();
	obj.numDepletion = txtDepletion.val();
	obj.numPromedio = 0;
	return obj;
}

function pintarDataFormularioBlanks(){
	txtCodigo.val(""+objSemillaBlanks.idBlank);
	txtInicial.val(""+objSemillaBlanks.numInicial);
	txtFinal.val(""+objSemillaBlanks.numFinal);
	txtDepletion.val(""+objSemillaBlanks.numDepletion);
}

function calcularValoresBlanks(){
	var inicial = parseFloat( !IsEmpty(txtInicial.val()) ? txtInicial.val() : "0" );
	var final = parseFloat( !IsEmpty(txtFinal.val()) ? txtFinal.val() : "0" );
	var depletion = eval(inicial - final);
	depletion = (isNaN(depletion))? "0": depletion;
	depletion = (depletion == Infinity || depletion == -Infinity )? "0": depletion;
	depletion = cortarDecimalRedondear(depletion,2);
	txtDepletion.val(""+depletion);
}