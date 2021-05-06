$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevo = null;
	var tblResultados = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarxSector = null;
	var dtpFechaInicio = null;
	var dtpFechaFin = null;
	var cboFechas = null;
	var btnCopiar = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevo = $('#btnNuevo');
	tblResultados = $('#tblResultados');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	cboPtarxSector = $("#cboPtarxSector");
	cboFechas = $("#cboFechas");
	btnCopiar = $("#btnCopiar");
	
	cargarGrillaResultado();
	buscarAlertasNotificacion();
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Notificaciones',
             ignoreColumn: ['accion']
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{
			field : 'accion',
			title : 'Acción',
			align : 'center',					
			valign : 'bottom',
			sortable : false,
			formatter: 'crearAccionesMantenimientoAlertasNotificacion',
			class: 'controls',
			events : operateEvents,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Nro',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionNotificacion',
			title : 'Nombre de Notificación',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionEstado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		}]
	});
}
	
function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			redireccionModal('./cargarVentanaVerModalNotificacion', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_EDITAR, descripcionNotificacion: row.descripcionNotificacion}, $('#verModalNotificacion'));
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Notificaci&oacute;n N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularAlertasNotificacion(' + row.idAlertasNotificacion +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				redireccionModal('./cargarVentanaVerModalNotificacion', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_VER, descripcionNotificacion: row.descripcionNotificacion}, $('#verModalNotificacion'));
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Notificaci&oacute;n N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularAlertasNotificacion(' + row.idAlertasNotificacion +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
	}

function crearAccionesMantenimientoAlertasNotificacion(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');
    }		
}

function validarFormularioBusqueda(){
	mostrarMensaje();
    var estado = true;
    if(IsEmpty(cboPtarxSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
         return estado;
    }
    if(IsEmpty(cboFechas.val())){
    	mostrarMensaje('Debe Seleccionar un periodo.', ConstanteServices.IMAGEN_DANGER); 
	    return false;
    }
    return estado;
}

function obtenerFiltros(){
    var formFiltro = {		    
        'cboPtarxSector': cboPtarxSector.val(),
        'cboFechas' : cboFechas.val()
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboPtarxSector").val(""+filtroObjecto.cboPtarxSector);
		buscarFechasAlertasNotificacion();
		if(!IsEmpty(filtroObjecto.cboFechas)){
			$("#cboFechas").val(""+filtroObjecto.cboFechas);
			buscarAlertasNotificacion();
		}
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function anularAlertasNotificacion(idAlertasNotificacion) {
	if(!IsEmpty(idAlertasNotificacion)) {
		$.ajax({
            url : "./anularAlertasNotificacion",
            type : "POST",
            dataType : "json",
            data : {
            	idAlertasNotificacion : idAlertasNotificacion
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarAlertasNotificacion();		
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);	            		            	
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER);
    }
}

function buscarAlertasNotificacion() {
	$.ajax({
		url : "./buscarAlertasNotificacion",
		type : "POST",
		dataType : "json",
		async:false,
		data : {}
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

window.operateEventsNotificacion = {
		'click #btnAnularRegistroAnalista': function (e, value, row, index) {				
			if(!IsEmpty(row.idNotificacionAnalista)){
				anularRegistroAnalista(row.idNotificacionAnalista);
				cargarCboAnalista();
	    		cargaLtaAnalistasVinculados();
			}else{
				mostrarMensajeModal('Debe Seleccionar un Analista para eliminarlo', ConstanteServices.IMAGEN_DANGER);
			}
		}
}
