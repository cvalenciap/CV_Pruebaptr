$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
var indicadorEdicionValor = 0;
var valorPromedioSCF = 0;
function inicializarVariables() {
	var divMensajeInformacionBlanks = null;
	var divMensajeInformacionSeeded = null;
	var divMensajeInformacionStandard = null;
	var divMensajeInformacionSamples = null;	
	var tblResultadosBlanks = null;
	var tblResultadosSeeded = null;
	var tblResultadosStandard = null;
	var tblResultadosSamples = null;	
	var btnNuevoBlanks = null;
	var btnNuevoSeeded = null;
	var btnNuevoStandard = null;
	var btnNuevoSamples = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var btnEditarValorGGA = null;
	var btnGuardarValorGGA = null;
}

function cargarComponentes() {
	accordion();
	divMensajeInformacionBlanks = $('#divMensajeInformacionBlanks');
	divMensajeInformacionSeeded = $('#divMensajeInformacionSeeded');
	divMensajeInformacionStandard = $('#divMensajeInformacionStandard');
	divMensajeInformacionSamples = $('#divMensajeInformacionSamples');
	tblResultadosBlanks = $('#tblResultadosBlanks');
	tblResultadosSeeded = $('#tblResultadosSeeded');
	tblResultadosStandard = $('#tblResultadosStandard');
	tblResultadosSamples = $('#tblResultadosSamples');
	btnNuevoBlanks = $('#btnNuevoBlanks');
	btnNuevoSeeded = $('#btnNuevoSeeded');
	btnNuevoStandard = $('#btnNuevoStandard');
	btnNuevoSamples = $('#btnNuevoSamples');
	btnEditarValorGGA = $("#btnEditarValorGGA");
	btnGuardarValorGGA = $("#btnGuardarValorGGA");
	
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	
	inputDecimal("txtValorGGA", 3, 2);
	
	btnNuevoBlanks.click(function() {
		redireccionModal('./cargarVentanaVerModalBlanks', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA}, $('#verModalBlanks'));
	}); 
	
	btnNuevoSeeded.click(function() {
		if(indicadorEdicionValor == 0){
			redireccionModal('./cargarVentanaVerModalSeeded', {mantenimientoBean:'',valorGGA : ""+$("#txtValorGGA").val(), modo: ConstanteServices.ESTADO_OPCION_NUEVA}, $('#verModalSeeded'));
		}else{
			mostrarMensaje('Debe Guardar El Valor GGA para Continuar.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
		}
	}); 
	
	btnNuevoStandard.click(function() {
		verificarSeededActivos();
	}); 
	
	btnNuevoSamples.click(function() {
		verificarSeededActivosSamples();
	}); 
	
	btnEditarValorGGA.click(function() {
		$("#divMensajeInformacionSeeded").html("");
		editarValorGGA();
	});
	
	btnGuardarValorGGA.click(function() {
		$("#divMensajeInformacionSeeded").html("");
		if(validarInputGGA()){
			var tituloModal = 'modificacion de Valor GGA';
   			modal.confirmer('¿Esta seguro de Grabar El Valor GGA ?', 'guardarValorGGA()', '', tituloModal);
		}
	});
	
	
	cargarGrillaResultadoBlanks();
	cargarGrillaResultadoSeeded();
	cargarGrillaResultadoStandard();
	cargarGrillaResultadoSamples();
	buscarSemillaBlanks();
	buscarSemillaSeeded();
}

function cargarGrillaResultadoBlanks(){
	tblResultadosBlanks.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Semilla Blanks',
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
			formatter: 'crearAccionesBlanks',
			class: 'controls',
			events : operateEventsBlanks,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numInicial',
			title : 'Initial DO',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numFinal',
			title : 'Final DO',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numPromedio',
			title : 'Promedio.',
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
function crearAccionesBlanks(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoBlanks" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoBlanks" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoBlanks" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoBlanks" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');		
    }		
}

window.operateEventsBlanks = {
		'click #btnEditarMantenimientoBlanks': function (e, value, row, index) {
			redireccionModal('./cargarVentanaVerModalBlanks', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_EDITAR}, $('#verModalBlanks'));
		},
		'click #btnAnularMantenimientoBlanks': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Blanks N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularBlanks(' + row.idBlank +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoBlanks': function (e, value, row, index) {
			if(row != null) {
				redireccionModal('./cargarVentanaVerModalBlanks', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_VER}, $('#verModalBlanks'));
			}
		},
		'click #btnActivarMantenimientoBlanks': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Blanks N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularBlanks(' + row.idBlank +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
}

function anularBlanks(idBlank) {
	if(!IsEmpty(idBlank)) {
		$.ajax({
            url : "./anularBlanks",
            type : "POST",
            dataType : "json",
            async:false,
            data : {
            	idBlank : idBlank 
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarSemillaBlanks();
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionBlanks');
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionBlanks');
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionBlanks');
    }
}

function buscarSemillaBlanks() {
 	$.ajax({
        url : "./buscarSemillaBlanks",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadosBlanks.bootstrapTable('load', JSON.parse(ListaResultado));		            		
        	} else {
        		tblResultadosBlanks.bootstrapTable('load', []);	            		
        	}
        } else {
        	tblResultadosBlanks.bootstrapTable('load', []);	
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionBlanks");
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}


//SEEDED

function validarInputGGA(){
	var indicador = true;
	if(IsEmpty($("#txtValorGGA").val())){
		mostrarMensaje('Debe Ingresar un Valor GGA', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
		indicador = false;
		return indicador;
	}	
	return indicador;
}


function editarValorGGA(){
	indicadorEdicionValor = 1;
	$("#txtValorGGA").removeAttr("disabled");
	btnGuardarValorGGA[0].style.display = 'inherit';
	btnEditarValorGGA[0].style.display = 'none';
}

function guardarValorGGA(){
	$("#divMensajeInformacionSeeded").html("");
	$.ajax({
        url : "./guardarValorGGA",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	valorGGA : $("#txtValorGGA").val()
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionSeeded');
        	$("#txtValorGGA").attr("disabled",true);
        	btnEditarValorGGA[0].style.display = 'inherit';
        	btnGuardarValorGGA[0].style.display = 'none';
        	indicadorEdicionValor = 0;
        	buscarSemillaSeeded();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaResultadoSeeded(){
	tblResultadosSeeded.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Semilla Seeded',
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
			formatter: 'crearAccionesSeeded',
			class: 'controls',
			events : operateEventsSeeded,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numVolumen',
			title : 'Volumen.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numInicial',
			title : 'Initial DO B1.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numFinal',
			title : 'Final DO B2.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numPorcentajeDeplec',
			title : '% Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado,
			formatter: 'formatoPorcentaje'
		},{	
			field : 'numValorBOD',
			title : 'BOD.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numValorSCF',
			title : 'SCF.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numPromedio',
			title : 'Promedio SCF.',
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

function formatoPorcentaje(value, row, index) {
	return [row.numPorcentajeDeplec + '%'].join('');			
}

function crearAccionesSeeded(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoSeeded" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoSeeded" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoSeeded" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoSeeded" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');		
    }		
}

window.operateEventsSeeded = {
		'click #btnEditarMantenimientoSeeded': function (e, value, row, index) {
			if(indicadorEdicionValor == 0){
				redireccionModal('./cargarVentanaVerModalSeeded', {mantenimientoBean:JSON.stringify(row),valorGGA : ""+row.numValGGa , modo: ConstanteServices.ESTADO_OPCION_EDITAR}, $('#verModalSeeded'));
			}else{
				mostrarMensaje('Debe Guardar El Valor GGA para Continuar.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
			}
		},
		'click #btnAnularMantenimientoSeeded': function (e, value, row, index) {	
			if(indicadorEdicionValor == 1){
				mostrarMensaje('Debe Guardar El Valor GGA para Continuar.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
				return "";
			}			
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Seeded N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSeeded(' + row.idSeeded +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoSeeded': function (e, value, row, index) {
			if(row != null) {
				redireccionModal('./cargarVentanaVerModalSeeded', {mantenimientoBean:JSON.stringify(row),valorGGA : ""+row.numValGGa, modo: ConstanteServices.ESTADO_OPCION_VER}, $('#verModalSeeded'));
			}
		},
		'click #btnActivarMantenimientoSeeded': function (e, value, row, index) {
			if(indicadorEdicionValor == 1){
				mostrarMensaje('Debe Guardar El Valor GGA para Continuar.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
				return "";
			}	
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Seeded N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSeeded(' + row.idSeeded +')' , '', titulo, 'Aceptar', 'Cancelar');
		}	
}

function buscarSemillaSeeded() {
 	$.ajax({
        url : "./buscarSemillaSeeded",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {	
        		tblResultadosSeeded.bootstrapTable('load', JSON.parse(ListaResultado));	
        	} else {
        		tblResultadosSeeded.bootstrapTable('load', []);	 
        	}
        } else {
        	tblResultadosSeeded.bootstrapTable('load', []);
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionSeeded");
        }
    	buscarSemillaSeededActivo();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function buscarSemillaSeededActivo(){
	$.ajax({
        url : "./buscarSemillaSeededActivas",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {	
        		var ltaJSON = JSON.parse(ListaResultado);
            	$("#txtValorGGA").val(""+ltaJSON[0].numValGGa);
            	valorPromedioSCF = eval(ltaJSON[0].numPromedio);
            	actualizarCalculoStandard();
            	actualizarCalculoSamples();
        	} else {
            	$("#txtValorGGA").val(""+valorGGADefecto);
            	valorPromedioSCF = 0;
            	actualizarCalculoStandard();
            	actualizarCalculoSamples();
        	}
        } else {
        	$("#txtValorGGA").val(""+valorGGADefecto);
        	valorPromedioSCF = 0;
        	actualizarCalculoStandard();
        	actualizarCalculoSamples();
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}


function anularSeeded(idSeeded) {
	if(!IsEmpty(idSeeded)) {
		$.ajax({
            url : "./anularSeeded",
            type : "POST",
            dataType : "json",
            async:false,
            data : {
            	idSeeded : idSeeded ,
            	valorGGA : $("#txtValorGGA").val()
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarSemillaSeeded();
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionSeeded');
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSeeded');
    }
}

//STANTARD

function verificarSeededActivos(){
	$("#divMensajeInformacionStandard").html("");
	$.ajax({
        url : "./buscarSemillaSeededActivas",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		redireccionModal('./cargarVentanaVerModalStandard', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA}, $('#verModalStandard'));
        	} else {
        		mostrarMensaje("Para Poder Agregar GGA Standard debe haber registros en Sedded", ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionStandard");            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionStandard");
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaResultadoStandard(){
	tblResultadosStandard.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Semilla Standard',
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
			formatter: 'crearAccionesStandard',
			class: 'controls',
			events : operateEventsStandard,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numVolGGa',
			title : 'Vol (mL)<br>of GGA.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numVolPoly',
			title : 'Vol (mL)<br>of PolySeed.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numInicial',
			title : 'Initial DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numFinal',
			title : 'Final DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numNetDep',
			title : 'Net Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numPorcentajeDeplec',
			title : '% Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado,
			formatter: 'formatoPorcentaje'
		},{	
			field : 'numValorBOD',
			title : 'BOD.',
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

function formatoPorcentaje(value, row, index) {
	return [row.numPorcentajeDeplec + '%'].join('');			
}

function crearAccionesStandard(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoStandard" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoStandard" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoStandard" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoStandard" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');		
    }		
}

window.operateEventsStandard = {
		'click #btnEditarMantenimientoStandard': function (e, value, row, index) {
			redireccionModal('./cargarVentanaVerModalStandard', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_EDITAR}, $('#verModalStandard'));
		},
		'click #btnAnularMantenimientoStandard': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Standard N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularStandard(' + row.idStandard +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoStandard': function (e, value, row, index) {
			if(row != null) {
				redireccionModal('./cargarVentanaVerModalStandard', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_VER}, $('#verModalStandard'));
			}
		},
		'click #btnActivarMantenimientoStandard': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Standard N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularStandard(' + row.idStandard +')' , '', titulo, 'Aceptar', 'Cancelar');
		}	
}

function actualizarCalculoStandard() {
 	$.ajax({
        url : "./actualizarCalculoStandard",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	promedioSCF : valorPromedioSCF
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    	
    	} else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionStandard");
        }
    	buscarSemillaStandard();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function buscarSemillaStandard() {
 	$.ajax({
        url : "./buscarSemillaStandard",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadosStandard.bootstrapTable('load', JSON.parse(ListaResultado));		            		
        	} else {
        		tblResultadosStandard.bootstrapTable('load', []);	            		
        	}
        } else {
        	tblResultadosStandard.bootstrapTable('load', []);
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionStandard");
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function anularStandard(idStandard) {
	if(!IsEmpty(idStandard)) {
		$.ajax({
            url : "./anularStandard",
            type : "POST",
            dataType : "json",
            async:false,
            data : {
            	idStandard : idStandard 
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarSemillaStandard();
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionStandard');
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionStandard');
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionStandard');
    }
}

//SAMPLES

function verificarSeededActivosSamples(){
	$("#divMensajeInformacionSamples").html("");
	$.ajax({
        url : "./buscarSemillaSeededActivas",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		redireccionModal('./cargarVentanaVerModalSamples', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA}, $('#verModalSamples'));
        	} else {
        		mostrarMensaje("Para Poder Agregar Samples debe haber registros activos en Sedded", ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionSamples");            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionSamples");
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaResultadoSamples(){
	tblResultadosSamples.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Semilla Samples',
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
			formatter: 'crearAccionesSamples',
			class: 'controls',
			events : operateEventsSamples,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionBotella',
			title : 'Bottle #.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numVolSample',
			title : 'Vol (mL)<br>of sample.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numVolPoly',
			title : 'Vol (mL)<br>of PolySeed.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numInicial',
			title : 'Initial DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numFinal',
			title : 'Final DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numValorSCF',
			title : 'SCF.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numValorNetDep',
			title : 'Net Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numDilcFactor',
			title : 'Dil. Factor.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numSampleBOD',
			title : 'Sample BOD.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'numPromedioDBO',
			title : 'Promedio DBO5<br>(mg/L).',
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

function crearAccionesSamples(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimientoSamples" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimientoSamples" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimientoSamples" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimientoSamples" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');		
    }		
}

window.operateEventsSamples = {
		'click #btnEditarMantenimientoSamples': function (e, value, row, index) {
			redireccionModal('./cargarVentanaVerModalSamples', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_EDITAR}, $('#verModalSamples'));
		},
		'click #btnAnularMantenimientoSamples': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Samples N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSamples(' + row.idSamples +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimientoSamples': function (e, value, row, index) {
			if(row != null) {
				redireccionModal('./cargarVentanaVerModalSamples', {mantenimientoBean:JSON.stringify(row), modo: ConstanteServices.ESTADO_OPCION_VER}, $('#verModalSamples'));
			}
		},
		'click #btnActivarMantenimientoSamples': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Samples N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularSamples(' + row.idSamples +')' , '', titulo, 'Aceptar', 'Cancelar');
		}	
}

function actualizarCalculoSamples() {
 	$.ajax({
        url : "./actualizarCalculoSamples",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	promedioSCF : valorPromedioSCF
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    	
    	} else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionSamples");
        }
    	buscarSemillaSamples();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function buscarSemillaSamples() {
 	$.ajax({
        url : "./buscarSemillaSamples",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadosSamples.bootstrapTable('load', JSON.parse(ListaResultado));		            		
        	} else {
        		tblResultadosSamples.bootstrapTable('load', []);	            		
        	}
        } else {
        	tblResultadosSamples.bootstrapTable('load', []);
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,"divMensajeInformacionSamples");
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function anularSamples(idSamples) {
	if(!IsEmpty(idSamples)) {
		$.ajax({
            url : "./anularSamples",
            type : "POST",
            dataType : "json",
            async:false,
            data : {
            	idSamples : idSamples,
            	promedioSCF : valorPromedioSCF
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarSemillaSamples();
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS,'divMensajeInformacionSamples');
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSamples');
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionSamples');
    }
}


function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}





