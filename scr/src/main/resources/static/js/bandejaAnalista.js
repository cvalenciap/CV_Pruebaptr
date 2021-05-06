$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevoAnalista = null;
	var tblResultadosAnalista = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoAnalista = $('#btnNuevoAnalista');
	tblResultadosAnalista = $('#tblResultadosAnalista');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	btnNuevoAnalista.click(function(event) {            
    	$.redirect('./cargarVentanaAnalista', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA});
	});
	cargarGrillaResultado();
	buscarAnalista();
}

function cargarGrillaResultado (){
	tblResultadosAnalista.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Analista',
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
			formatter: 'crearAccionesMantenimientoAnalista',
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
			field : 'codigoUsuario',
			title : 'Usuario.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nombreCompleto',
			title : 'Analista.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionTipoAnalista',
			title : 'Tipo.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionFlagTipo',
			title : 'Rol.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{
			field : 'estado',
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


function crearAccionesMantenimientoAnalista(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoAnalista" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoAnalista" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoAnalista" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoAnalista" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');		
    }		
}

window.operateEvents = {
		'click #btnEditarMantenimientoAnalista': function (e, value, row, index) {
			$.redirect('./cargarVentanaAnalista', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR});
		},
		'click #btnAnularMantenimientoAnalista': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Analista N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularAnalista(' + row.idAnalista +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoAnalista': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaAnalista', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER});
			}
		},
		'click #btnActivarMantenimientoAnalista': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Analista N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularAnalista(' + row.idAnalista +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function anularAnalista(idAnalista) {
	if(!IsEmpty(idAnalista)) {
		$.ajax({
            url : "./anularAnalista",
            type : "POST",
            dataType : "json",
            data : {
            	idAnalista: idAnalista
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);	   
        		buscarAnalista();
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

function buscarAnalista() {
 	$.ajax({
        url : "./buscarAnalista",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadosAnalista.bootstrapTable('load', JSON.parse(ListaResultado));		            		
        	} else {
        		tblResultadosAnalista.bootstrapTable('load', []);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function cargarFiltros(){
	 if(!IsEmpty(mensajeRespuestaServer)){							
	    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	 }
}