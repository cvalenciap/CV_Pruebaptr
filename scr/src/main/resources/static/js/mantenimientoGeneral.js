$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
var map; 
var latitudporDefecto = "";
var LongitudporDefecto = "";
function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var btnGrabar 	= null;
	var btnRegresar	= null;
	var idDetalleGeneral	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var txtCodigo = null;
	var txtDescripcion = null;
	var txtDescripcionCorta = null;
	var txtDireccion = null;
	var txtLatitud = null;
	var txtLongitud = null;	
	var chkAsociado = null;
	var chkImpresion = null;
	var cboTipoSemilla = null;
}

function cargarComponentes() {
	divMantenimientoGeneral = $('#divMantenimientoGeneral');
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	txtCodigo = $('#txtCodigo');
	txtDescripcion = $('#txtDescripcion');
	txtDescripcionCorta = $('#txtDescripcionCorta');
	txtDireccion = $('#txtDireccion');
	txtLatitud = $('#txtLatitud');
	txtLongitud = $('#txtLongitud');
	cboTipoPuntoMuestra = $('#cboTipoPuntoMuestra');
	cboFamiliaSemilla = $('#cboFamiliaSemilla');
	chkColumnaSemilla = $('#chkColumnaSemilla');
	cboPtoMuestraAsociado = $('#cboPtoMuestraAsociado');
	chkAsociado = $('#chkAsociado');
	cboSubparametro = $('#cboSubparametro');
	chkImpresion = $('#chkImpresion');
	cboTipoSemilla = $('#cboTipoSemilla');

	btnRegresar.click(function() {
    		 $.redirect('./cargarVentanaBandejaGenerales', {regresar:ConstanteServices.REGRESAR});
     }); 
	
	btnGrabar.click(function() {
   		if(validarFormularioDatos()){
   			var tituloModal = 'MANTENIMIENTO DE '+titulo;
   			modal.confirmer('¿Esta seguro de Grabar Datos del Mantenimiento ?', 'grabarMantenimiento()', '', tituloModal);            	
        }
    }); 
	
	if(idGeneral == ConstanteServices.CODIGO_PLANTA_TRATAMIENTO){
		$("#divUbicacion").css("display","block");
		$("#divUbicacionGeografica").css("display","block");
	}
	if(idGeneral == ConstanteServices.CODIGO_PUNTO_MUESTRA){
		$("#divMantenimientoPuntoMuestra").css("display","block");
		if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			evaluarChkAsociado();
			pintarDataFormularioPuntoMuestra();
		}
	}
	if(idGeneral == ConstanteServices.CODIGO_MICROORGANISMO || idGeneral == ConstanteServices.CODIGO_MICRO_PARASITO){
		$("#divMicroorganismo").css("display","block");
		cargarComboSubParametro();
		if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			pintarDataFormularioMicroorganismo();
		}
	}
	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimientoGeneral.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}	
	
	chkAsociado.click(function(){
		evaluarChkAsociado();
	});
	
	cboFamiliaSemilla.change(function(){
		evaluarTipoSemilla();
	});
}

function evaluarTipoSemilla(){
	if(!IsEmpty(cboFamiliaSemilla.val()) && cboFamiliaSemilla.val() != ConstanteServices.TIPO_BLANK){
		$("#divTipoSemilla").css("display", "block");
		cboTipoSemilla.val(ConstanteServices.COLUMNA_EP);
	}else{
		$("#divTipoSemilla").css("display", "none");
	}
}

function obtenerObjMantenimiento(){    
		var obj = {};
		obj.idDetalleGeneral = !IsEmpty(objDetalleGeneral.idDetalleGeneral) ? objDetalleGeneral.idDetalleGeneral : null;    
		obj.idGeneral = objDetalleGeneral.idGeneral;
		obj.descripcion = txtDescripcion.val();   
		obj.descripcionCorta = txtDescripcionCorta.val();
		obj.vlDato01 = !IsEmpty(txtDireccion.val()) ? txtDireccion.val() : " ";   
		obj.vlDato02 = !IsEmpty(txtLatitud.val()) ? txtLatitud.val() : " ";        
		obj.vlDato03 = !IsEmpty(txtLongitud.val()) ? txtLongitud.val() : " ";                 
		obj.vlDato04 = " ";
		obj.vlDato05 = " ";
		obj.vlDato06 = " ";
		if(idGeneral == ConstanteServices.CODIGO_PUNTO_MUESTRA){
			if(chkColumnaSemilla[0].checked){
				obj.vlDato01 = chkColumnaSemilla.val();
			}else{
				obj.vlDato01 = " ";
			}
			obj.vlDato03 = !IsEmpty(cboFamiliaSemilla.val()) ? cboFamiliaSemilla.val() : " ";
			if(!IsEmpty(cboFamiliaSemilla.val()) && cboFamiliaSemilla.val() != ConstanteServices.TIPO_BLANK){
				obj.vlDato06 = cboTipoSemilla.val();
			}else{
				obj.vlDato06 = " ";
			}
//			obj.vlDato06 = !IsEmpty(cboTipoPuntoMuestra.val()) ? cboTipoPuntoMuestra.val() : " ";
			if(!IsEmpty(cboTipoPuntoMuestra.val())){
				obj.vlDato04 = cboTipoPuntoMuestra.val();
			}else{
				obj.vlDato04 = " ";
			}
			if(!IsEmpty(cboPtoMuestraAsociado.val())){
				obj.vlDato05 = cboPtoMuestraAsociado.val()
			}else{
				obj.vlDato05 = " ";
			}
			if(chkImpresion[0].checked){
				obj.vlDato02 = chkImpresion.val();
			}else{
				obj.vlDato02 = " ";
			}
		}else if(idGeneral == ConstanteServices.CODIGO_MICROORGANISMO || idGeneral == ConstanteServices.CODIGO_MICRO_PARASITO){
			obj.vlDato01 = cboSubparametro.val();
		}
	    return obj;
} 


function grabarMantenimiento() {
	$.ajax({
		url: "./grabarMantenimiento",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    			: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjMantenimiento())
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){	
	    	$.redirect('./cargarVentanaBandejaGenerales', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}


function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();
    if(IsEmpty(txtDescripcion.val()))
    {
    	mostrarMensaje('El campo Descripción es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    }    
    if(IsEmpty(txtDescripcionCorta.val())){
		    mostrarMensaje('El campo Descripción Corta es obligatorio', ConstanteServices.IMAGEN_DANGER);
		    estado = false;
    }
    if(idGeneral == ConstanteServices.CODIGO_PLANTA_TRATAMIENTO){
    	if(IsEmpty(txtDireccion.val())){
			    mostrarMensaje('El campo Dirección es obligatorio', ConstanteServices.IMAGEN_DANGER);
			    estado = false;
	    }
		if(IsEmpty(txtLatitud.val())){
			    mostrarMensaje('El campo Latitud es obligatorio', ConstanteServices.IMAGEN_DANGER);
			    estado = false;
	    }else if(validarLatitudLongitud(txtLatitud.val()) == false){
	    	   mostrarMensaje('Ingrese una Latitud Valida!', ConstanteServices.IMAGEN_DANGER);
		       estado = false;
	    }
		if(IsEmpty(txtLongitud.val())){
			    mostrarMensaje('El campo Longitud es obligatorio', ConstanteServices.IMAGEN_DANGER);
			    estado = false;
		}else if(validarLatitudLongitud(txtLongitud.val()) == false){
	    	   mostrarMensaje('Ingrese una Longitud Valida!', ConstanteServices.IMAGEN_DANGER);
		       estado = false;
	    }
    }
	
    return estado;
}

function pintandoFormulario(){
	$("#txtCodigo").val(""+objDetalleGeneral.idDetalleGeneral);
	$("#txtDescripcion").text(""+objDetalleGeneral.descripcion);
	$("#txtDescripcionCorta").val(""+objDetalleGeneral.descripcionCorta);
	$("#txtDireccion").val(""+objDetalleGeneral.vlDato01);
	$("#txtLatitud").val(""+objDetalleGeneral.vlDato02);
	$("#txtLongitud").val(""+objDetalleGeneral.vlDato03);
}

function pintarDataFormularioPuntoMuestra(){
	if(objDetalleGeneral.vlDato01 == ConstanteServices.VALOR_COLUMNA_SEMILLA){
		objDetalleGeneral.vlDato01 = ConstanteServices.COLUMNA_SEMILLA;
	}else{
		objDetalleGeneral.vlDato01 = "";
	}
	if(objDetalleGeneral.vlDato03 == ConstanteServices.VALOR_NO_FAMILIA_SEMILLA){
		objDetalleGeneral.vlDato03 = "";
	}
	if(objDetalleGeneral.vlDato06 == ConstanteServices.VALOR_ENTRADA){
		objDetalleGeneral.vlDato06 = ConstanteServices.COLUMNA_EP; 
	}else if(objDetalleGeneral.vlDato06 == ConstanteServices.VALOR_SALIDA){
		objDetalleGeneral.vlDato06 = ConstanteServices.COLUMNA_SP;
	}else{
		objDetalleGeneral.vlDato06 = "";
	}
	cboTipoSemilla.val(""+objDetalleGeneral.vlDato06);
	cboFamiliaSemilla.val(""+objDetalleGeneral.vlDato03);
	evaluarTipoSemilla();
	if(!IsEmpty(objDetalleGeneral.vlDato01)){
		chkColumnaSemilla[0].checked = true;
	}else{
		chkColumnaSemilla[0].checked = false;
	}
	if(!IsEmpty(objDetalleGeneral.vlDato05)){
		chkAsociado[0].checked = true;
		buscarPuntosMuestraES();
		evaluarChkAsociado();
		cboPtoMuestraAsociado.val(""+objDetalleGeneral.vlDato05);
	}else{
		chkAsociado[0].checked = false;;
	}
	if(!IsEmpty(objDetalleGeneral.vlDato02)){
		chkImpresion[0].checked = true;
	}else{
		chkImpresion[0].checked = false;
	}
	cboTipoPuntoMuestra.val(!IsEmpty(objDetalleGeneral.vlDato04) ? objDetalleGeneral.vlDato04 : "");
}

function pintarDataFormularioMicroorganismo(){
	if(!IsEmpty(objDetalleGeneral.vlDato01)){
		cboSubparametro.val(""+objDetalleGeneral.vlDato01);
	}else{
		cboSubparametro.val("");
		mostrarMensaje('El microorganismo no tiene un SubParametroAsociado.', ConstanteServices.IMAGEN_DANGER);
	}
}

function evaluarChkAsociado(){
	if(chkAsociado[0].checked){
		$("#divEsAsociado").css("display","block");
		buscarPuntosMuestraES();
	}else{
		$("#divEsAsociado").css("display","none");
		cboPtoMuestraAsociado.val("");
		removeOptions(cboPtoMuestraAsociado);
	}
}

function buscarPuntosMuestraES(){
	$.ajax({
        url : "./buscarPuntosMuestraES",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		agregarListaItem(cboPtoMuestraAsociado, JSON.parse(ListaResultado), "idDetalleGeneral", "descripcion");
        	} else {
		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarComboSubParametro(){
	var idParametro = 0;
	if(idGeneral == ConstanteServices.CODIGO_MICROORGANISMO){
		idParametro = ConstanteServices.ID_HIDROBIOLOGICOS;
	}else if(idGeneral == ConstanteServices.CODIGO_MICRO_PARASITO){
		idParametro = ConstanteServices.ID_PARASITOLOGICOS;
	}
	$.ajax({
        url : "./buscarSubParametroMicroorganismo",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametro  : idParametro	
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		agregarListaItem(cboSubparametro, JSON.parse(ListaResultado), "idSubParametro", "descripcionSubParametro");
        	} else {
		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}
