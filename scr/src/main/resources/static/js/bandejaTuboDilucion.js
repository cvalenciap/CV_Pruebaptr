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
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevo = $('#btnNuevo');
	tblResultados = $('#tblResultados');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	
	btnNuevo.click(function(event) {            
    	$.redirect('./cargarVentanaTuboDilucion', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA});
	});
	
	cargarGrillaResultado();
	buscarTuboDilucion();
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Tubo Dilucion',
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
			formatter: 'crearAccionesMantenimientoTuboDilucion',
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
			field : 'combinacion',
			title : 'Combinación',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionNMP100',
			title : 'Lista NMP/100 mL',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'debajo95',
			title : 'Lim. Confianza Por Debajo 95%',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'sobre95',
			title : 'Lim. Confianza Sobre 95%',
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

function crearAccionesMantenimientoTuboDilucion(value, row, index) {			
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

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			$.redirect('./cargarVentanaTuboDilucion', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR});
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Tubo de Dilucion N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularTuboDilucion(' + row.idTuboDilucion +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaTuboDilucion', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER});
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Tubo de Dilucion N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularTuboDilucion(' + row.idTuboDilucion +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function anularTuboDilucion(idTuboDilucion) {
	if(!IsEmpty(idTuboDilucion)) {
		$.ajax({
            url : "./anularTuboDilucion",
            type : "POST",
            dataType : "json",
            data : {
            	idTuboDilucion : idTuboDilucion
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarTuboDilucion();		
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

function buscarTuboDilucion() {
	 	$.ajax({
            url : "./buscarTuboDilucion",
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

function cargarFiltros(){
	 if(!IsEmpty(mensajeRespuestaServer)){							
	    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	 }
}