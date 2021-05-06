$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

var accion = false;

function inicializarVariables() {

	var divMensajeInformacion = null;
	var txtCodigo = null;
	var cboPuntoMuestreo = null;
	var txtPesoInicial = null;
	var txtVolumenFiltrado = null;
	var txtPesoFinal = null;	
	var txtPesoCalcinado = null;
	var txtSS = null;
	var txtSSF = null;
	var txtSSV = null;
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
	cboPuntoMuestreo = $('#cboPuntoMuestreo');
	txtPesoInicial = $('#txtPesoInicial');
	txtVolumenFiltrado = $('#txtVolumenFiltrado');
	txtPesoFinal = $('#txtPesoFinal');
	txtPesoCalcinado = $('#txtPesoCalcinado');
	txtSS = $('#txtSS');
	txtSSF = $('#txtSSF');
	txtSSV = $('#txtSSV');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');	
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	inputDecimal("txtPesoInicial", 6, 4);
	inputDecimal("txtVolumenFiltrado", 6, 4);
	inputDecimal("txtPesoFinal",6, 4);
	inputDecimal("txtPesoCalcinado", 6, 4);
	inputDecimal("txtSS", 20, 8);
	inputDecimal("txtSSF", 20, 8);
	inputDecimal("tstSSV", 20, 8);
	
	agregarListaItem(cboPuntoMuestreo, ltaPuntoMuestra, "idPuntoMuestra", "descripcionPunto");
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaSolidoSuspendido', {regresar:ConstanteServices.REGRESAR});
	});
	
	btnGrabar.click(function() {
		if(validarFormularioDatos()){
			var tituloModal = 'Determinación Sólidos';
			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarSolidoSuspendido()', '', tituloModal);
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

function grabarSolidoSuspendido(){
	$.ajax({
		url: "./registrarSolidoSuspendido",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjSolidoSuspendido()),
			 volumenOriginal : volumenOriginal ,
			 idPuntoMuestraOriginal : idPuntoMuestraOriginal
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaSolidoSuspendido', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjSolidoSuspendido(){    
	var obj = {};
	obj.idSolidoSuspendido = !IsEmpty(objSolidoSuspendido.idSolidoSuspendido) ? objSolidoSuspendido.idSolidoSuspendido : null;  
	obj.idPtarxSector = !IsEmpty(objSolidoSuspendido.idPtarxSector) ? objSolidoSuspendido.idPtarxSector : null;
	obj.idTipoSolido = !IsEmpty(objSolidoSuspendido.idTipoSolido) ? objSolidoSuspendido.idTipoSolido : null;
	obj.idPuntoMuestra = cboPuntoMuestreo.val();
	obj.nuPesoInicial   = txtPesoInicial.val();
	obj.nuPesoFiltrado = txtVolumenFiltrado.val();
	obj.nuPesoFinal = txtPesoFinal.val();
	obj.nuPesoCalcina = txtPesoCalcinado.val();
    return obj;
}

function pintarDataFormulario(){
	txtCodigo.val(""+objSolidoSuspendido.idSolidoSuspendido);
	cboPuntoMuestreo.val(""+objSolidoSuspendido.idPuntoMuestra);
	txtPesoInicial.val(""+objSolidoSuspendido.nuPesoInicial);
	txtVolumenFiltrado.val(""+objSolidoSuspendido.nuPesoFiltrado);
	txtPesoFinal.val(""+objSolidoSuspendido.nuPesoFinal);
	txtPesoCalcinado.val(""+objSolidoSuspendido.nuPesoCalcina);
	txtSS.val(""+objSolidoSuspendido.nNumSS);
	txtSSF.val(""+objSolidoSuspendido.nNumSSF);
	txtSSV.val(""+objSolidoSuspendido.nNumSSV);
}

function calcularValores(){
	var pesoInicial = parseFloat( !IsEmpty(txtPesoInicial.val()) ? txtPesoInicial.val() : "0" );
	var volumenFiltrado = parseFloat( !IsEmpty(txtVolumenFiltrado.val()) ? txtVolumenFiltrado.val() : "0" );
	var pesoFinal = parseFloat( !IsEmpty(txtPesoFinal.val()) ? txtPesoFinal.val() : "0" );
	var pesoCalcinado = parseFloat( !IsEmpty(txtPesoCalcinado.val()) ? txtPesoCalcinado.val() : "0" );
	if(validarDatosCalculo()){
		var SS = ((pesoFinal-pesoInicial)*1000000) / volumenFiltrado;
		var SSV = ((pesoFinal-pesoCalcinado)*1000000) / volumenFiltrado;
		var SSF = SS - SSV;
		SS = Math.round(SS);
		SSV = Math.round(SSV);
		SSF = Math.round(SSF);
		txtSS.val(""+SS);
		txtSSV.val(""+SSV);
		txtSSF.val(""+SSF);
	} else{
		txtSS.val("");
		txtSSV.val("");
		txtSSF.val("");
	}
}

function validarDatosCalculo(){
	if(IsEmpty(txtPesoInicial.val()) || IsEmpty(txtVolumenFiltrado.val()) || IsEmpty(txtPesoFinal.val()) || IsEmpty(txtPesoCalcinado.val()) ){
		return false;
	}else {
		return true;
	}
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
    if(IsEmpty(txtPesoInicial.val()))
     {
      	mostrarMensaje('El campo Peso Inicial es obligatorio', ConstanteServices.IMAGEN_DANGER);
      	estado = false;
      	
      	
      	return estado;
     }
    if(IsEmpty(txtVolumenFiltrado.val()))
    {
    	mostrarMensaje('El campo Volumen Filtrado es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }  
    if(IsEmpty(txtPesoFinal.val()))
    {
    	mostrarMensaje('El campo Peso Final es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }  
    if(IsEmpty(txtPesoCalcinado.val()))
    {
    	mostrarMensaje('El campo Peso Calcinado es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    
    return estado;    
}