$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevoPtarxSector = null;
	var tblResultadosPtarxSector = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoPtarxSector = $('#btnNuevoPtarxSector');
	tblResultadosPtarxSector = $('#tblResultadosPtarxSector');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	btnNuevoPtarxSector.click(function(event) {            
    	$.redirect('./cargarVentanaPtarxSector', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA});
	});
	cargarGrillaResultado();
	buscarPtarxSector();
}

function cargarGrillaResultado (){
	tblResultadosPtarxSector.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Ptar por Sector',
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
			formatter: 'crearAccionesMantenimientoPtarxSector',
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
			field : 'descripcionPtar',
			title : 'Nombre de Planta de Tratamiento.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionSector',
			title : 'Sector.',
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

function crearAccionesMantenimientoPtarxSector(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoPtarxSector" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoPtarxSector" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoPtarxSector" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoPtarxSector" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');	
    }		
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimientoPtarxSector': function (e, value, row, index) {
			$.redirect('./cargarVentanaPtarxSector', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR});
		},
		'click #btnAnularMantenimientoPtarxSector': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Ptar por Sector N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularPtarxSector(' + row.idPtarxsector +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoPtarxSector': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaPtarxSector', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER});
			}
		},
		'click #btnActivarMantenimientoPtarxSector': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activara el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Ptar por Sector N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularPtarxSector(' + row.idPtarxsector +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function anularPtarxSector(idPtarxSector) {
	if(!IsEmpty(idPtarxSector)) {
		$.ajax({
            url : "./anularPtarxSector",
            type : "POST",
            dataType : "json",
            data : {
            	idPtarxSector: idPtarxSector
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarPtarxSector();		
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

function buscarPtarxSector() {
	 	$.ajax({
            url : "./buscarPtarxSector",
            type : "POST",
            dataType : "json",
            async:false,
            data : {}
        }).done(function(respuestaBean) {
        	mostrarMensaje();
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		tblResultadosPtarxSector.bootstrapTable('load', JSON.parse(ListaResultado));		            		
            	} else {
            		tblResultadosPtarxSector.bootstrapTable('load', []);
    		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
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