$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	console.log("variable");
	var divMensajeInformacion = null;
	var btnNuevo = null;
	var tblResultadosVariables = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboParametro = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevo = $('#btnNuevo');
	tblResultadosVariables = $('#tblResultadosVariables');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboParametro = $("#cboParametro");
	
	agregarListaItem(cboParametro, ltaParametro, "idDetalleGeneral", "descripcion");
	
	cboParametro.change(function(event){
		buscarVariable();
    });
	
	btnNuevo.click(function(event) {            
    	$.redirect('./cargarVentanaVariable', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA, filtro:obtenerFiltros(), idParametro : cboParametro.val(), descripcionParametro : extraerTextoCombo("cboParametro")});
	});

	cargarGrillaResultado();
	buscarVariable();
}

function cargarGrillaResultado (){
	tblResultadosVariables.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Variables',
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
			formatter: 'crearAccionesMantenimientoVariable',
			class: 'controls',
			events : operateEvents,
			cellStyle : estiloAcciones
		},{	
			field : 'descripcionLetra',
			title : 'Letra',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionVariable',
			title : 'Descripcion Variable',
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

function crearAccionesMantenimientoVariable(value, row, index) {			
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
			$.redirect('./cargarVentanaVariable', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR, filtro:obtenerFiltros(), idParametro : cboParametro.val(), descripcionParametro : extraerTextoCombo("cboParametro")});
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Variable N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularVariable(' + row.idVariable +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaVariable', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER, filtro:obtenerFiltros(), idParametro : cboParametro.val(), descripcionParametro : extraerTextoCombo("cboParametro")});
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Variable N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularVariable(' + row.idVariable +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function anularVariable(idVariable) {
	if(!IsEmpty(idVariable)) {
		$.ajax({
            url : "./anularVariable",
            type : "POST",
            dataType : "json",
            data : {
            	idVariable: idVariable
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarVariable();		
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

function buscarVariable() {
	if(validarFormularioBusqueda){
		$.ajax({
            url : "./buscarVariable",
            type : "POST",
            dataType : "json",
            async:false,
            data : {
	        	idDetalleGeneral: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
	        }
        }).done(function(respuestaBean) {
        	mostrarMensaje();
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		tblResultadosVariables.bootstrapTable('load', JSON.parse(ListaResultado));		            		
            	} else {
            		tblResultadosVariables.bootstrapTable('load', []);
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

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboParametro").val(""+filtroObjecto.cboParametro);
		buscarVariable();
	}
	 if(!IsEmpty(mensajeRespuestaServer)){							
	    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	 }
}

function obtenerFiltros(){
    var formFiltro = {		    
        'cboParametro': cboParametro.val()
    }
    return JSON.stringify(formFiltro);
}

function validarFormularioBusqueda(){
    var estado = true;
     if(IsEmpty(cboParametro.val())){           
       	 mostrarMensaje('Debe Seleccionar un Parametro.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}
