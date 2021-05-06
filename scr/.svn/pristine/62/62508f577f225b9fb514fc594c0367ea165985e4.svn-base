$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

function inicializarVariables() {
	var divMensajeInformacion = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarOrigen = null;
	var cboPtarDestino = null;
	var cboFechasOrigen = null;
	var cboFechasDestino = null;
	var dtpFechaInicio = null;
	var dtpFechaFin = null;
	var btnCopiarFisico = null;
}

function cargarComponentes(){
	cboPtarOrigen = $("#cboPtarOrigen");
	cboPtarDestino = $("#cboPtarDestino");
	cboFechasOrigen = $("#cboFechasOrigen");
	cboFechasDestino = $("#cboFechasDestino");	
	btnCopiarFisico = $("#btnCopiarFisico");
	
	$('#datetimepickerFechaFin').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});
	dtpFechaInicio = $("#dtpFechaInicio");
	dtpFechaFin = $("#dtpFechaFin");
	$("#datetimepickerFechaFin").datepicker("setDate", formatoFechaDatePicker(fechaActual));
	
	agregarListaItem(cboPtarOrigen, ltaPtarGeneral, "idPtarxsector", "descripcionPtarxSector");	
	agregarListaItem(cboPtarDestino, ltaPtarGeneral, "idPtarxsector", "descripcionPtarxSector");	
	
	cboPtarOrigen.click(function(event){
		buscarFechaOrigenFisicoQuimico();
    });
	
	cboPtarDestino.change(function(event){
		buscarFechaDestinoFisicoQuimico();
		validacionFechaDestino();
    });
	
	cboFechasDestino.change(function(event){
		validacionFechaDestino();
    });	
	
	btnCopiarFisico.click(function(event){
		validacionCopiarData();
    });	
	inhabilitarFecha();
	

	buscarFechaOrigenFisicoQuimico();
	buscarFechaDestinoFisicoQuimico();
	validacionFechaDestino();
	console.log("copiar");
}

function validarFormularioCopiar(){
	$("#mensajeInformacionCopiar").html("");
    var estado = true;
    if(IsEmpty(cboPtarOrigen.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar de Origen.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
         estado = false; 
         return estado;
    }
    if(IsEmpty(cboFechasOrigen.val())){           
      	 mostrarMensaje('Debe Seleccionar un Periodo de Origen.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
         estado = false; 
         return estado;
    }
    if(IsEmpty(cboPtarDestino.val())){           
      	 mostrarMensaje('Debe Seleccionar un Ptar de Destino.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
         estado = false; 
         return estado;
    }
    if(IsEmpty(dtpFechaInicio.val())){           
     	 mostrarMensaje('Debe Ingresar una Fecha Inicio.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
        estado = false; 
        return estado;
    }
    if(IsEmpty(dtpFechaFin.val())){           
     	mostrarMensaje('Debe Ingresar una Fecha Fin.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
        estado = false; 
        return estado;
    }
    
    if(DateDiff("d", dtpFechaInicio.val(), dtpFechaFin.val(), "") < 0){
    	mostrarMensaje('Fecha Inicio debe ser menor que Fecha Fin.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
    	estado = false; 
        return estado;
    }
    return estado;
}

function validacionCopiarData(){
	if(validarFormularioCopiar()){
		var ltaFechaOrigen =  cboFechasOrigen.val().split("_");
		$.ajax({
	        url : "./copiarDataFisicoQuimico",
	        type : "POST",
	        dataType : "json",
	        async:true,
	        data : {
	        	idPtarOrigen : (!IsEmpty(cboPtarOrigen.val()) ? cboPtarOrigen.val() : null),
	        	fechaInicioOrigen : ltaFechaOrigen[0],
	        	fechaFinOrigen : ltaFechaOrigen[1],
	        	idPtarDestino: (!IsEmpty(cboPtarDestino.val()) ? cboPtarDestino.val() : null),
	        	fechaInicioDestino : (!IsEmpty(dtpFechaInicio.val()) ? dtpFechaInicio.val() : null),
	        	fechaFinDestino : (!IsEmpty(dtpFechaFin.val()) ? dtpFechaFin.val() : null),
	        }
	    }).done(function(respuestaBean) {
	    	$("#mensajeInformacionCopiar").html("");
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	
	    		buscarFechaOrigenFisicoQuimico();
	    		buscarFechaDestinoFisicoQuimico();
	    		buscarFechasFisicoQuimico();
	    		validacionFechaDestino();
	    		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS,"mensajeInformacionCopiar");
	        } else {
	        	validacionFechaDestino();
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar");
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA,"mensajeInformacionCopiar");
	    });	
	}
}

function validacionFechaDestino(){
	if(!IsEmpty(cboFechasDestino.val())){
		var ltaFechas = cboFechasDestino.val().split("_");
		var fechaFinal = ltaFechas[1];
		var fechaAumentada = dateAdd('d', 1, fechaFinal);
		$("#datetimepickerFechaInicio").datepicker("setDate", formatoFechaDatePicker(fechaAumentada));
		$("#datetimepickerFechaFin").datepicker("setDate", formatoFechaDatePicker(""+dateAdd('d', 1, fechaAumentada)));
		inhabilitarFecha();
	}else{
		$("#datetimepickerFechaInicio").datepicker("setDate", formatoFechaDatePicker(fechaActual));
		$("#datetimepickerFechaFin").datepicker("setDate", formatoFechaDatePicker(""+dateAdd('d', 1, fechaActual)));
		habilitarFecha();
	}
}

function habilitarFecha(){
	$('#datetimepickerFechaInicio').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});
}

function inhabilitarFecha(){
	$('#datetimepickerFechaInicio').datepicker('remove');
}

function validarPtarOrigenBusqueda(){
	$("#mensajeInformacionCopiar").html("");
    var estado = true;
    if(IsEmpty(cboPtarOrigen.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar de Origen.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
         estado = false; 
         return estado;
    }
    return estado;
}
function validarPtarDestinoBusqueda(){
	$("#mensajeInformacionCopiar").html("");
    var estado = true;
    if(IsEmpty(cboPtarDestino.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar de Destino.', ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar"); 
         estado = false; 
         return estado;
    }
    return estado;
}

function buscarFechaDestinoFisicoQuimico() {
	if(validarPtarDestinoBusqueda()) {
		$.ajax({
	        url : "./buscarFechasFisicoQuimico",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarDestino.val()) ? cboPtarDestino.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	$("#mensajeInformacionCopiar").html("");
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		var lista = JSON.parse(ListaResultado);
	        		cboFechasDestino.html("");
	        	    $.each(lista, function(i, item) {
	        	    	cboFechasDestino.append($("<option/>").attr("value",item.fechaInicio+"_"+item.fechaFin).text(item.periodo)); 
	        	    });  	
	        	} else {
	        		cboFechasDestino.html("");	       
	        	}
	        } else {
	        	cboFechasDestino.html("");
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar");
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA,"mensajeInformacionCopiar");
	    });	
	}	
}

function buscarFechaOrigenFisicoQuimico() {
	if(validarPtarOrigenBusqueda()) {
		$.ajax({
	        url : "./buscarFechasFisicoQuimico",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarOrigen.val()) ? cboPtarOrigen.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	$("#mensajeInformacionCopiar").html("");
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		var lista = JSON.parse(ListaResultado);
	        		cboFechasOrigen.html("");
	        	    $.each(lista, function(i, item) {
	        	    	cboFechasOrigen.append($("<option/>").attr("value",item.fechaInicio+"_"+item.fechaFin).text(item.periodo)); 
	        	    });  	
	        	} else {
	        		cboFechasOrigen.html("");	       
	        	}
	        } else {
	        	cboFechasOrigen.html("");
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"mensajeInformacionCopiar");
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA,"mensajeInformacionCopiar");
	    });	
	}	
}