$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	console.log('bandeja aprobacion');
	var divMensajeInformacion = null;
	var btnBusquedaRegistro = null;
	var btnReporte = null;
	var btnAprobar = null;
	var btnRechazar = null;
	var btnLimpiar = null;
	var tblResultados = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarxSector = null;
	var cboEstado = null;
	var txtSector = null;
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
	cboPtarxSector = $("#cboPtarxSector");
	cboEstado = $("#cboEstado");
	txtSector = $("#txtSector");
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
	
	btnBusquedaRegistro.click(function(event){
		buscarRegistroLaboratorio();
	});
	
	btnLimpiar.click(function(event){
		limpiarParametrosBusqueda();
	});
	
	btnAprobar.click(function(event){
		if(validarSeleccionRegistroRechazar()){
			resultadoFlag = evaluarFlagParametros();
			if(resultadoFlag == 1){
				var html = 
					'<div class="row">' + 
						'<div class="col-sm-12">' + 
							'<label class="control-label">Existen parametros de laboratorio pendientes de aprobación.</label><br>' +
							'<label class="control-label">¿Desea continuar con la aprobación?</label>' +
						'</div>' + 
					'</div>';
				var titulo = 'Aprobaci&oacute;n de Registros de Laboratorio';
				modal.defaultModal(html, 'aprobarLtaRegistroLaboratorio()' , '', titulo, 'Aceptar', 'Cancelar');
			}else{
				var html = 
					'<div class="row">' + 
						'<div class="col-sm-12">' + 
							'<label class="control-label">Se aprobarán los Registros. ¿Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Aprobaci&oacute;n de Registros de Laboratorio';
				modal.defaultModal(html, 'aprobarRegistroLaboratorio()' , '', titulo, 'Aceptar', 'Cancelar');
			}
		}		
	});
	
	btnRechazar.click(function(event){
		var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se rechazarán los Registros. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
		var titulo = 'Aprobaci&oacute;n de Registros de Laboratorio';
		modal.defaultModal(html, 'rechazarRegistroLaboratorio()' , '', titulo, 'Aceptar', 'Cancelar');
	});
	
	
	btnReporte.click(function(event){
		mostrarMensaje();
		var lista = tblResultados.bootstrapTable("getSelections");
		if(lista != null && lista.length > 0){
			var html = 
				'<div class="row">' + 
					'<div class="col-sm-12">' + 
						'<label class="control-label">¿Se imprimir&aacute;n los Registros seleccionados. ¿Desea Continuar?</label>' + 
					'</div>' + 
				'</div>';
			var titulo = 'Reporte de Registros de Laboratorio';
			modal.defaultModal(html, 'reporteRegistroLaboratorio()' , '', titulo, 'Aceptar', 'Cancelar');
		}else{
		    mostrarMensaje('Debe seleccionar los registros a imprimir..', ConstanteServices.IMAGEN_DANGER);	
		}		
	});
	
	agregarListaItemTodos(cboPtarxSector, ltaPtarxSector, "idPtarxsector", "descripcionPtar");
	agregarListaItemTodos(cboEstado, ltaEstado, "idDetalleGeneral", "descripcion");
	cargarEstado();
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	
	
	cboPtarxSector.change(function(event){
		cargarEstado();
    });
	
	cargarGrillaResultado();
	validacionBotonesValidador();
}

function reporteRegistroLaboratorio(){	
	var lista = tblResultados.bootstrapTable("getSelections");
	$.redirect("./reporteRegistroLaboratorio", {"listaRegistrosLaboratorio" : JSON.stringify(lista) });
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

function cargarEstado() {	
	for(i=0; i<ltaPtarxSector.length; i++){
		if(cboPtarxSector.val() == ltaPtarxSector[i].idPtarxsector){
			txtSector.val(ltaPtarxSector[i].descripcionSector);
		}
	}
	if(cboPtarxSector.val()==""){
		txtSector.val("");
	}
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'right',
		toolbarAlign : 'right',
		checkBoxHeader: true,
		clickToSelect : true,
		exportOptions: {
             fileName: 'Bandeja Aprobación'
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [
		{
			field : 'checkbox',
			title : '',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			checkbox : true,
			cellStyle : bloquearRechazado
		},{
			field : 'idRegistroLaboratorio',
			title : 'Id Registro',
			align : 'center',
			valign : 'bottom',
			sortable : false
		},{	
			field : 'fechaHoraCreacionString',
			title : 'Fecha Registro',
			align : 'center',
			valign : 'bottom',
			sortable : false
		},{	
			field : 'fechaMonitoreoString',
			title : 'Fecha Monitoreo',
			align : 'center',
			valign : 'bottom',
			sortable : false
		},{	
			field : 'descripcionPtar',
			title : 'Planta',
			align : 'center',
			valign : 'bottom',
			sortable : false
		},{	
			field : 'descripcionSector',
			title : 'Nombre de Sector',
			align : 'center',
			valign : 'bottom',
			sortable : false
		},{	
			field : 'descripFlagDirecto',
			title : 'Directo',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceDirecto',
			events : operateEvents,
		},{	
			field : 'descripFlagDBO',
			title : 'DBO5',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceDBO',
			events : operateEvents,
		},{	
			field : 'descripFlagAceite',
			title : 'Aceites y Grasas',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceAceite',
			events : operateEvents,
		},{	
			field : 'descripFlagSolido',
			title : 'Solidos',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceSolido',
			events : operateEvents,
		},{	
			field : 'descripFlagMicrobiologico',
			title : 'Microbiológicos',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceMicro',
			events : operateEvents,
		},{	
			field : 'descripFlagHidrobiologico',
			title : 'Hidrobiológicos',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlaceHidro',
			events : operateEvents,
		},{	
			field : 'descripFlagParasitologico',
			title : 'Parasitológico',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'crearBotonesEnlacePara',
			events : operateEvents,
		},{
			field : 'descripcionEstadoAprob',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false
		}]
	});
}

function bloquearRechazado(value, row, index){
	if(row.descripcionEstadoAprob == ConstanteServices.REGISTRO_RECHAZADO){
		return {
		    css: {
		      'display' : 'none'
		    }
		};
	}else{
		return {
		    css: {
		      'display' : 'inherit'
		    }
		};
	}
}

function verificarEstiloColor(value, row, index) {	
	var color = "#e8f2fb";
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {
		return {
		    css: {
		      'background' : color
		    }
		};
	}else{
		return {
		    css: {
		      'color' : 'red',
		      'background' : color
		    }
		};
	}
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			var opcionConsulta = document.querySelector('input[name="opcionConsulta"]:checked').value;
			$.redirect('./cargarVentanaSolidoSuspendido', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR ,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector, idTipoSolido : cboTipoSolido.val(), tipoConsulta : opcionConsulta});
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Sólido N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSolidoSuspendido(' + row.idSolidoSuspendido +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaSolidoSuspendido', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector });
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Analista N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSolidoSuspendido(' + row.idSolidoSuspendido +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #enlaceRegistroDirecto': function (e, value, row, index) {
			row['idParametro'] = row.idDirecto;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroDBO': function (e, value, row, index) {
			row['idParametro'] = row.idDBO;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroAceite': function (e, value, row, index) {
			row['idParametro'] = row.idAceite;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroSolido': function (e, value, row, index) {
			row['idParametro'] = row.idSolido;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroHidro': function (e, value, row, index) {
			row['idParametro'] = row.idHidrobiologico;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroMicro': function (e, value, row, index) {
			row['idParametro'] = row.idMicrobiologico;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		},
		'click #enlaceRegistroPara': function (e, value, row, index) {
			row['idParametro'] = row.idParasitologico;
			if(row != null) {
				autorizarParametro(e, value, row, index);
			}
		}
	}
function autorizarParametro(e, value, row, index){
	$.ajax({
        url : "./buscarParametroAsignadosMenu",
        type : "POST",
        dataType : "json",
        async:true,
        data : {                     
				idPtarPorSector: row.idPtarxSector
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	var listaJSON = [];
        	if(ListaResultado != null && ListaResultado != "[]") {
        		listaJSONOpciones =  JSON.parse(ListaResultado);
        		var indicador = false;
        		for(var  i = 0 ; i < listaJSONOpciones.length;i++){
        			if(listaJSONOpciones[i].idParametro == row.idParametro){
        				$.redirect('./cargarVentanaRegistroPrincipal', {objRegistroLaboratorio: JSON.stringify(row), filtro:obtenerFiltros()});
        				indicador = true;
        				break;
        			}
        		}
        		if(!indicador){
        			mostrarMensaje('No tiene permiso para ver este Parámetro.', ConstanteServices.IMAGEN_DANGER);
        		}
        	}else{
        		mostrarMensaje('No tiene permiso para ver este Parámetro.', ConstanteServices.IMAGEN_DANGER);
        	}			           
    	}else{
    		mostrarMensaje('No tiene permiso para ver este Parámetro.', ConstanteServices.IMAGEN_DANGER);
    	}
    	
    }).fail(function(jqXHR, textStatus, errorThrown) {
       validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function crearAccionesRegistroLaboratorio(value, row, index) {			
	if(row != null) {  			
		return [
			'<button id="btnVerMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }		
}

function crearBotonesEnlaceDirecto(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagDirecto, "Directo");
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }	
}

function crearBotonesEnlaceDBO(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagDBO, "DBO");
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }	
}

function crearBotonesEnlaceAceite(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagAceite, "Aceite");
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }	
}

function crearBotonesEnlaceSolido(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagSolido, "Solido");
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }	
}

function crearBotonesEnlaceHidro(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagHidrobiologico, "Hidro");	
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }
}

function crearBotonesEnlaceMicro(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagMicrobiologico, "Micro");
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }
}

function crearBotonesEnlacePara(value, row, index) {
	if(row != null) {  			
		var html = crearBotonesEnlace(row.descripFlagParasitologico, "Para");	
		return [html].join('');	
    } else {
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }
}

function formatoChkbox(value, row, index) {
	if(row != null) {  			
		return [
			'<input name="btSelectAll" type="checkbox">'
        ].join('');	
    } else {
    	return [''].join('');	
    }		
}

function validarFormularioBusqueda(){
    var estado = true;
    var fechaInicio = $(datetimepickerInicio).datepicker("getDate");
    var fechaFin = $(datetimepickerFin).datepicker("getDate");
    if(IsEmpty(dtpInicio.val())){           
    	mostrarMensaje('Debe Seleccionar una Fecha de Inicio.', ConstanteServices.IMAGEN_DANGER);
    	tblResultados.bootstrapTable('load', []);
        estado = false; 
    }
    if(IsEmpty(dtpFin.val())){           
     	mostrarMensaje('Debe Seleccionar una Fecha Fin', ConstanteServices.IMAGEN_DANGER);
     	tblResultados.bootstrapTable('load', []);
        estado = false; 
    }
    if(fechaInicio > fechaFin){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
    	tblResultados.bootstrapTable('load', []);
        estado = false;
    }
    if(fechaFin < fechaInicio){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
    	tblResultados.bootstrapTable('load', []);
        estado = false;
    }
    return estado;
}

function validarSeleccionRegistroAprobar(){
    var estado = true;
    if(seleccionados.length == 0){           
    	mostrarMensaje('Debe Seleccionar Registros para Aprobarlos.', ConstanteServices.IMAGEN_DANGER); 
        estado = false; 
    }
    
    return estado;
}

function evaluarFlagParametros(){
	var flagParametros = 0;
	var seleccionados = tblResultados.bootstrapTable('getSelections');
    for(i=0; i<seleccionados.length; i++){
    	if(parseInt(seleccionados[i].descripFlagDirecto)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagDirecto)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagDBO)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagDBO)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagAceite)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagAceite)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagSolido)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagSolido)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagMicrobiologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagMicrobiologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagHidrobiologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagHidrobiologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    	if(parseInt(seleccionados[i].descripFlagParasitologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO || parseInt(seleccionados[i].descripFlagParasitologico)==ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
    		flagParametros = 1;
    	}
    }
    if(flagParametros == 1){ 
        return flagParametros; 
    }
}


function validarSeleccionRegistroRechazar(){
    var estado = true;
    var seleccionados = tblResultados.bootstrapTable('getSelections');
    if(seleccionados.length == 0){           
    	mostrarMensaje('Debe Seleccionar Registros para Rechazarlos.', ConstanteServices.IMAGEN_DANGER); 
        estado = false; 
    }
    return estado;
}

function limpiarParametrosBusqueda(){
	cboPtarxSector.val("");
	cargarEstado();
	cboEstado.val("");
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	tblResultados.bootstrapTable('load', []);
	mostrarMensaje();
}

function buscarRegistroLaboratorio() {
	opcionConsulta = document.querySelector('input[name="opcionConsulta"]:checked').value;
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarRegistroLaboratorio",
	        type : "POST",
	        dataType : "json",
	        async:true,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
	        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
	        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null),
	        	idEstado: (!IsEmpty(cboEstado.val()) ? cboEstado.val() : null),
	        	tipoConsulta : opcionConsulta
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {
	        		tblResultados.bootstrapTable('load', JSON.parse(ListaResultado));
	        	} else {
	        		tblResultados.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}

function aprobarRegistroLaboratorio() {	
	$.ajax({
		url : "./aprobarRegistroLaboratorio",
		type : "POST",
		dataType : "json",
		async:true,
		data : {
			listaRegistroAprobar: JSON.stringify(listarRegistrosCheck()),
			idCambioEstado: ConstanteServices.ID_ESTADO_APROBAR
	}
	}).done(function(respuestaBean) {
		mostrarMensaje();
		if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
	    	buscarRegistroLaboratorio();
	    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	} else {
		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});	
}

function aprobarLtaRegistroLaboratorio() {	
	$.ajax({
		url : "./aprobarLtaRegistroLaboratorio",
		type : "POST",
		dataType : "json",
		async:true,
		data : {
			listaRegistroAprobar: JSON.stringify(listarRegistrosCheck()),
			idCambioEstado: ConstanteServices.ID_ESTADO_APROBAR
	}
	}).done(function(respuestaBean) {
		mostrarMensaje();
		if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
	    	buscarRegistroLaboratorio();
	    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	} else {
		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});	
}

function rechazarRegistroLaboratorio() {	
	if(validarSeleccionRegistroRechazar()) {
		$.ajax({
	        url : "./aprobarRegistroLaboratorio",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	listaRegistroAprobar: JSON.stringify(listarRegistrosCheck()),
	        	idCambioEstado: ConstanteServices.ID_ESTADO_RECHAZAR
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
	    		buscarRegistroLaboratorio();
	    		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });
	} 	
}

function listarRegistrosCheck(){
	var listaSeleccionados = tblResultados.bootstrapTable('getSelections');
	return listaSeleccionados;
}

function obtenerFiltros(){
	var opciones = tblResultados.bootstrapTable("getOptions")
    var formFiltro = {		    
        'cboPtarxSector': cboPtarxSector.val(),
        'cboEstado': cboEstado.val(),
        'dtpInicio': dtpInicio.val(),
        'dtpFin': dtpFin.val(),
        'paginaSelect' : opciones.pageNumber,
        'tipoConsulta' : document.querySelector('input[name="opcionConsulta"]:checked').value,
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboPtarxSector").val(""+filtroObjecto.cboPtarxSector);
		$("#cboTipoSolido").val(""+filtroObjecto.cboTipoSolido);
		$('#dtpInicio').val(""+filtroObjecto.dtpInicio);
		$('#dtpFin').val(""+filtroObjecto.dtpFin);
		$('#cboEstado').val(""+filtroObjecto.cboEstado);
		
		var fechaInicio = filtroObjecto.dtpInicio;
		var fechaFin = filtroObjecto.dtpFin
		var datePartsInicio = fechaInicio.split("/");
		var datePartsFin = fechaFin.split("/");
		var dateObjectInicio = new Date(datePartsInicio[2], datePartsInicio[1] - 1, datePartsInicio[0]);
		var dateObjectFin = new Date(datePartsFin[2], datePartsFin[1] - 1, datePartsFin[0]);
		$(datetimepickerInicio).datepicker("setDate", dateObjectInicio);
		$(datetimepickerFin).datepicker("setDate", dateObjectFin);
		
		var pageNumber = filtroObjecto.paginaSelect;
		var radio = document.getElementsByName("opcionConsulta");
		for(var i=0;i<radio.length;i++){
			if(radio[i].value == filtroObjecto.tipoConsulta){
				radio[i].checked = true;
				break
			}
		}		
		buscarRegistroLaboratorioFilter(pageNumber);		
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function buscarRegistroLaboratorioFilter(pageNumber) {
	opcionConsulta = document.querySelector('input[name="opcionConsulta"]:checked').value;
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarRegistroLaboratorio",
	        type : "POST",
	        dataType : "json",
	        async:true,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
	        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
	        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null),
	        	idEstado: (!IsEmpty(cboEstado.val()) ? cboEstado.val() : null),
	        	tipoConsulta : opcionConsulta
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {
	        		tblResultados.bootstrapTable('load', JSON.parse(ListaResultado));
	        		tblResultados.bootstrapTable('selectPage', pageNumber);
	        	} else {
	        		tblResultados.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}

