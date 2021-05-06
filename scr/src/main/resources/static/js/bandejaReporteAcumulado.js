$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			console.log("entrada");
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnBusquedaRegistro = null;
	var btnReporte = null;
	var btnAprobar = null;
	var btnRechazar = null;
	var btnLimpiar = null;
	var tblResultados = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var dtpInicio = null;
	var dtpFin = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnBusquedaRegistro = $('#btnBusquedaRegistro');
	btnReporte = $('#btnReporte');
	btnAprobar = $('#btnAprobar');
	btnRechazar = $('#btnRechazar');
	btnLimpiar = $('#btnLimpiar');
	tblResultados = $('#tblResultados');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	dtpInicio = $('#dtpInicio');
	dtpFin = $('#dtpFin');
	
	$('#datetimepickerInicio').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});

	$('#datetimepickerFin').datepicker({
		autoclose: true,
		format: 'dd/mm/yyyy'
	});
		
	btnLimpiar.click(function(event){
		limpiarParametrosBusqueda();
	});
	
	btnReporte.click(function(event){
		if(validarDatosEntrada()){
			var html = 
				'<div class="row">' + 
					'<div class="col-sm-12">' + 
						'<label class="control-label">¿Se imprimir&aacute; el Reporte. ¿Desea Continuar?</label>' + 
					'</div>' + 
				'</div>';
			var titulo = 'Reporte Acumulado de Registros de Laboratorio';
			modal.defaultModal(html, 'buscarRegistroReporteAcumulado()' , '', titulo, 'Aceptar', 'Cancelar');
		}		
	});
	
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	
}

function reporteRegistroLaboratorioAcumulado(){
	$.redirect("./reporteRegistroLaboratorioAcumulado", {"fechaInicio" : dtpInicio.val(), "fechaFin" : dtpFin.val()});
}

function validacionBotonesValidador(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR) {
		$('#btnAprobar').show();
		$('#btnRechazar').show();
	}else{
		$('#btnAprobar').hide();
		$('#btnRechazar').hide();
	}
}


function validarDatosEntrada(){
    var estado = true;
    var fechaInicio = $(datetimepickerInicio).datepicker("getDate");
    var fechaFin = $(datetimepickerFin).datepicker("getDate");
    if(IsEmpty(dtpInicio.val())){           
    	mostrarMensaje('Debe Seleccionar una Fecha de Inicio.', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(IsEmpty(dtpFin.val())){           
     	mostrarMensaje('Debe Seleccionar una Fecha Fin', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(fechaInicio > fechaFin){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    if(fechaFin < fechaInicio){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    return estado;
}

function limpiarParametrosBusqueda(){
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	mostrarMensaje();
}

function buscarRegistroReporteAcumulado() {
	if(validarDatosEntrada()) {
		$.ajax({
	        url : "./buscarRegistroReporteAcumulado",
	        type : "POST",
	        dataType : "json",
	        async:true,
	        data : {
	        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
	        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	    		var ListaResultado = respuestaBean.parametros.ListaResultado;
	    		console.log(ListaResultado);
	        	if(ListaResultado != null && ListaResultado.length > 0 ) {		            		
	        		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	        		reporteRegistroLaboratorioAcumulado();
	        	}else{
	        		mostrarMensaje("No se encontraron registros con los parametros ingresados", ConstanteServices.IMAGEN_DANGER);
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}
