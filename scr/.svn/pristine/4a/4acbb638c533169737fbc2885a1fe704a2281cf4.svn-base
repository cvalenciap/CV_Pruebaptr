$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});

function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevoOximetro = null;
	var tblResultadosOximetro = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoOximetro = $('#btnNuevoOximetro');
	tblResultadosOximetro = $('#tblResultadosOximetro');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	btnNuevoOximetro.click(function(event) {            
    	$.redirect('./cargarVentanaOximetro', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA ,filtro:obtenerFiltros()});
	});
	
	cargarGrillaResultado();
	asignarNoBorder();
	buscarOximetro();
}

function cargarGrillaResultado(){
	tblResultadosOximetro.bootstrapTable({
		data : [],
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Oxímetro',
             ignoreColumn: ['accion']
        },
        exportDataType : 'all',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},
		columns : 
		[
			[{
				class : 'noBorder'
			},{
				field : 'nro',
				title : 'Nro',
				rowspan: 2,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				formatter: 'formatoNro',
				cellStyle : verificarEstiloEstado
			},{	
				field : 'descripcionSensor',
				title : 'Tipo<br>Sensor',
				rowspan: 2,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{	
				field : 'fechaRegistroString',
				title : 'Fecha de<br>Registro',
				rowspan: 2,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{
	            title: 'Calibracion<br>con Aire',
	            rowspan: 1,
	            colspan: 2,
	            align: 'center'
	        },{
	            title: 'Calibracion con<br>Solución Cero',
	            rowspan: 1,
	            colspan: 2,
	            align: 'center'
	        },{	
				field : 'descripcionObservacion',
				title : 'Observaciones',
				rowspan: 2,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{
				field : 'descripcionEstado',
				title : 'Estado',
				rowspan: 2,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			}],
			[{
				field : 'accion',
				title : 'Acción',
				align : 'center',					
				valign : 'bottom',
				sortable : false,
				formatter: 'crearAccionesMantenimientoOximetro',
				class: 'controls',
				events : operateEvents,
				cellStyle : estiloAcciones
			},{	
				field : 'numAirePdte',
				title : 'Pendiente',
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{	
				field : 'numSaturacionAirePdte',
				title : '% Saturación',
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{	
				field : 'numSolucionPdte',
				title : 'Pendiente',
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			},{	
				field : 'numSaturacionSolucionPdte',
				title : '% Saturación',
				rowspan: 1,
				align : 'center',
				valign : 'bottom',
				sortable : false,
				searchable : true,
				filterControl: 'input',
				cellStyle : verificarEstiloEstado
			}]
		]
	});
}

function asignarNoBorder(){
	$(".noBorder").css('border-bottom', '0px');
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			var descripcionPtar = row.descripcionPtar;
			var descripcionTipoSolido = row.descripcionTipoSolido;
			$.redirect('./cargarVentanaOximetro', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR ,filtro:obtenerFiltros()});
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Oxímetro N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularOximetro(' + row.idOximetro +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			var descripcionPtar = row.descripcionPtar;
			var descripcionTipoSolido = row.descripcionTipoSolido;
			if(row != null) {
				$.redirect('./cargarVentanaOximetro', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER, filtro:obtenerFiltros()});
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
			modal.defaultModal(html, 'anularOximetro(' + row.idOximetro +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function crearAccionesMantenimientoOximetro(value, row, index) {			
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
    	return [
    		'<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
				'<i class="fa fa-check"></i>',
		    '</button>'
		].join('');	
    }		
}

function validarFormularioBusqueda(){
    var estado = true;
    return estado;
}

function buscarOximetro() {
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarOximetro",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		tblResultadosOximetro.bootstrapTable('load', JSON.parse(ListaResultado));		            		
	        	} else {
	        		tblResultadosOximetro.bootstrapTable('load', []);
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

function obtenerFiltros(){
    var formFiltro = {		    
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboPtarxSector").val(""+filtroObjecto.cboPtarxSector);
		$("#cboTipoSolido").val(""+filtroObjecto.cboTipoSolido);
		buscarOximetro();
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function anularOximetro(idOximetro) {
	if(!IsEmpty(idOximetro)) {
		$.ajax({
            url : "./anularOximetro",
            type : "POST",
            dataType : "json",
            data : {
            	idOximetro : idOximetro             	
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarOximetro();		
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


