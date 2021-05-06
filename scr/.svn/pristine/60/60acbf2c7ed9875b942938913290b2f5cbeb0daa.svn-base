$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevoSolidoSuspendido = null;
	var tblResultados = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarxSector = null;
	var cboTipoSolido = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoSolidoSuspendido = $('#btnNuevoSolidoSuspendido');
	tblResultados = $('#tblResultados');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	cboPtarxSector = $("#cboPtarxSector");
	cboTipoSolido = $("#cboTipoSolido");
	
	btnNuevoSolidoSuspendido.click(function(event) {            
    	$.redirect('./cargarVentanaSolidoSuspendido', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA ,filtro:obtenerFiltros(), idPtarxSector : cboPtarxSector.val(), idTipoSolido : cboTipoSolido.val(), descripcionPtar : extraerTextoCombo("cboPtarxSector"), descripcionTipoSolido : extraerTextoCombo("cboTipoSolido")});
	});
	
	agregarListaItem(cboPtarxSector, ltaPtarxSector, "idPtarxsector", "descripcionPtarxSector");
	agregarListaItem(cboTipoSolido, ltaTipoSolido, "idDetalleGeneral", "descripcion");
	
	cboPtarxSector.change(function(event){
		buscarSolidoSuspendido();
    });
	cboTipoSolido.change(function(event){
		buscarSolidoSuspendido();
    });
	
	cargarGrillaResultado();
	buscarSolidoSuspendido();
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Solido Suspendido',
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
			formatter: 'crearAccionesMantenimientoSolidoSuspendido',
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
			field : 'descripcionPunto',
			title : 'Puntos de Muestreo.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nuPesoInicial',
			title : 'Peso<br>Inicial (A)<br>(g)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nuPesoFiltrado',
			title : 'Volumen<br>Filtrado<br>(mL)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nuPesoFinal',
			title : 'Peso<br>Final (B)<br>(g)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nuPesoCalcina',
			title : 'Peso<br>Calcinado<br>(C) (g)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'nNumSS',
			title : 'S.S.<br>(mg/L)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{	
			field : 'nNumSSF',
			title : 'S.S.F.<br>(mg/L)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{	
			field : 'nNumSSV',
			title : 'S.S.V.<br>(mg/L)',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
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
			var descripcionPtar = row.descripcionPtar;
			var descripcionTipoSolido = row.descripcionTipoSolido;
			$.redirect('./cargarVentanaSolidoSuspendido', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR ,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector, idTipoSolido : cboTipoSolido.val(), descripcionPtar : descripcionPtar, descripcionTipoSolido : descripcionTipoSolido});
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
			var descripcionPtar = row.descripcionPtar;
			var descripcionTipoSolido = row.descripcionTipoSolido;
			if(row != null) {
				$.redirect('./cargarVentanaSolidoSuspendido', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector, descripcionPtar : descripcionPtar, descripcionTipoSolido : descripcionTipoSolido });
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Solido Suspendido N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSolidoSuspendido(' + row.idSolidoSuspendido +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function crearAccionesMantenimientoSolidoSuspendido(value, row, index) {			
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
     if(IsEmpty(cboPtarxSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
     if(IsEmpty(cboTipoSolido.val())){           
       	 mostrarMensaje('Debe Seleccionar un Tipo.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}

function buscarSolidoSuspendido() {
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarSolidoSuspendido",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
	        	idTipoSolido: (!IsEmpty(cboTipoSolido.val()) ? cboTipoSolido.val() : null)
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

function obtenerFiltros(){
    var formFiltro = {		    
        'cboPtarxSector': cboPtarxSector.val(),
        'cboTipoSolido': cboTipoSolido.val()
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboPtarxSector").val(""+filtroObjecto.cboPtarxSector);
		$("#cboTipoSolido").val(""+filtroObjecto.cboTipoSolido);
		buscarSolidoSuspendido();
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function anularSolidoSuspendido(idSolidoSuspendido) {
	if(!IsEmpty(idSolidoSuspendido)) {
		$.ajax({
            url : "./anularSolidoSuspendido",
            type : "POST",
            dataType : "json",
            data : {
            	idSolidoSuspendido : idSolidoSuspendido             	
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarSolidoSuspendido();		
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

