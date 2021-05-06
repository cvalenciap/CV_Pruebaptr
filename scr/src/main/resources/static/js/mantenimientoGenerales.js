$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
var tituloMantenimiento = "";
var ltaPuntoMuestra = null;
var ltaSubParametroMicro = null;
function inicializarVariables() {
	var cboTipo = null;
	var divMensajeInformacion = null;
	var btnNuevoMantenimiento = null;
	var tblResultadosMantenimiento = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
}

function cargarComponentes() {
	cboTipo = $('#cboTipo');
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoMantenimiento = $('#btnNuevoMantenimiento');
	tblResultadosMantenimiento = $('#tblResultadosMantenimiento');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	
	cboTipo.html("");
    $.each(listaGeneral, function(i, item) {
    	cboTipo.append($("<option/>").attr("value",listaGeneral[i].idGeneral).text(listaGeneral[i].descripcion)); 
    });    
		
	btnNuevoMantenimiento.click(function(event) {            
    	$.redirect('./cargarVentanaMantenimientoGeneral', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA,filtro:obtenerFiltros(),tituloMantenimiento : tituloMantenimiento,idGeneral : cboTipo.val()});
	});
	
	cboTipo.change(function(event){
		modificarTitulos();
		buscarMantenimiento();
    });
	
	cargarGrillaResultado();
	seleccionarCombo();
	modificarTitulos();
	ocultarColumnaPuntoMuestra();
	buscarMantenimiento();
	
}

function modificarTitulos(){
	var texto = extraerTextoCombo("cboTipo");
	$("#lblTituloMantenimiento").text("MANTENIMIENTO DE "+texto);
}

function seleccionarCombo(){
	var filtro = $("#filtroId").val();
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		filtro = JSON.parse($("#filtroId").val());
		cboTipo.val(filtro.cboTipo);
	}else{
		$("#cboTipo").val($("#cboTipo option:first").val());
	}
}


function cargarGrillaResultado (){
	tblResultadosMantenimiento.bootstrapTable({
		data : [],
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Mantenimiento Generales',
             ignoreColumn: ['accion']
        },
        exportDataType : 'all',
        showExport: true,
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
			formatter: 'crearAccionesMantenimiento',
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
			field : 'descripcion',
			title : 'Descripción.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'descripcionCorta',
			title : 'Descripción<br>Corta',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'vlDato01',
			title : 'Dirección.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'vlDato02',
			title : 'Latitud.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'vlDato03',
			title : 'Longitud.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'vlDato06',
			title : 'Tipo de<br>Punto',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{	
			field : 'vlDato05',
			title : 'Asociado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloEstado
		},{
			field : 'subParametro',
			title : 'SubParametro',
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



function crearAccionesMantenimiento(value, row, index) {			
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
			row.vlDato05 = row.idPtoAuxiliar;
			$.redirect('./cargarVentanaMantenimientoGeneral', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR ,filtro:obtenerFiltros(),tituloMantenimiento : tituloMantenimiento,idGeneral : cboTipo.val()});
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Mantenimientos Generales N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularMantenimiento(' + row.idDetalleGeneral +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				row.vlDato05 = row.idPtoAuxiliar;
				$.redirect('./cargarVentanaMantenimientoGeneral', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER ,filtro:obtenerFiltros(),tituloMantenimiento : tituloMantenimiento,idGeneral : cboTipo.val()});
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Mantenimientos Generales N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularMantenimiento(' + row.idDetalleGeneral +')' , '', titulo, 'Aceptar', 'Cancelar');
		}
	}

function anularMantenimiento(idDetalleGeneral) {
	if(!IsEmpty(idDetalleGeneral)) {
		$.ajax({
            url : "./anularMantenimiento",
            type : "POST",
            dataType : "json",
            data : {
            	idDetalleGeneral: idDetalleGeneral
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarMantenimiento();		
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

function validarFormularioBusqueda(){
    var estado = true;
     if(IsEmpty(cboTipo.val())){           
       	 mostrarMensaje('Debe Seleccionar un Tipo.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}


function buscarMantenimiento() {
	if(validarFormularioBusqueda()) {
		if(cboTipo.val() == ConstanteServices.CODIGO_PLANTA_TRATAMIENTO){
			verColumnasPlantaTratamiento();
		}else if(cboTipo.val() == ConstanteServices.CODIGO_PUNTO_MUESTRA){
			verColumnasPuntoMuestra();
		}else if(cboTipo.val() == ConstanteServices.CODIGO_MICROORGANISMO || cboTipo.val() == ConstanteServices.CODIGO_MICRO_PARASITO){
			verColumnasMicroorganismo();
		}else{
			ocultarColumnaPuntoMuestra();
		}
		tituloMantenimiento = extraerTextoCombo("cboTipo");
	 	$.ajax({
            url : "./buscarDetalleGeneral",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idGeneral: (!IsEmpty(cboTipo.val()) ? cboTipo.val() : null)
            }
        }).done(function(respuestaBean) {
        	mostrarMensaje();
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		tblResultadosMantenimiento.bootstrapTable('load', JSON.parse(ListaResultado));
            		if(cboTipo.val() == ConstanteServices.CODIGO_PUNTO_MUESTRA){
            			ltaPuntoMuestra = JSON.parse(ListaResultado);
            		}
            		asignarValoresFormato();
            	} else {
            		tblResultadosMantenimiento.bootstrapTable('load', []);
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

function verColumnasPlantaTratamiento(){
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato01');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato02');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato03');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato06');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato05');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'descripcionCorta');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'subParametro');
	tblResultadosMantenimiento.bootstrapTable('refreshColumnTitle', {field: 'vlDato01', title: 'Dirección.'});
	tblResultadosMantenimiento.bootstrapTable('refreshColumnTitle', {field: 'vlDato03', title: 'Longitud.'});
}

function verColumnasPuntoMuestra(){
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'descripcionCorta');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato01');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato03');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato06');
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'vlDato05');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato02');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'subParametro');
	tblResultadosMantenimiento.bootstrapTable('refreshColumnTitle', {field: 'vlDato01', title: 'Columna Semilla'});
	tblResultadosMantenimiento.bootstrapTable('refreshColumnTitle', {field: 'vlDato03', title: 'Familia Semilla'});
}

function verColumnasMicroorganismo(){
	tblResultadosMantenimiento.bootstrapTable('showColumn', 'subParametro');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato01');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato03');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato06');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato05');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato02');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'descripcionCorta');
}

function asignarValoresFormato(){
	var lista = tblResultadosMantenimiento.bootstrapTable('getData');
	if(cboTipo.val() == ConstanteServices.CODIGO_PUNTO_MUESTRA){
		for(var i=0;i<lista.length;i++){
			if(lista[i].vlDato01 == ConstanteServices.COLUMNA_SEMILLA){
				lista[i].vlDato01 = ConstanteServices.VALOR_COLUMNA_SEMILLA;
			}else{
				lista[i].vlDato01 = ConstanteServices.VALOR_NO_COLUMNA_SEMILLA;
			}
			if(IsEmpty(lista[i].vlDato03)){
				lista[i].vlDato03 = ConstanteServices.VALOR_NO_FAMILIA_SEMILLA;
			}
			if(lista[i].vlDato06 == ConstanteServices.COLUMNA_EP){
				lista[i].vlDato06 = ConstanteServices.VALOR_ENTRADA; 
			}else if(lista[i].vlDato06 == ConstanteServices.COLUMNA_SP){
				lista[i].vlDato06 = ConstanteServices.VALOR_SALIDA;
			}else{
				lista[i].vlDato06 = ConstanteServices.VALOR_OTROS;
			}
			if(!IsEmpty(lista[i].vlDato05)){
				lista[i].idPtoAuxiliar = lista[i].vlDato05;
				lista[i].vlDato05 = getDescripcionPtoMuestra(lista[i].vlDato05);
			}else{
				lista[i].idPtoAuxiliar = "";
				lista[i].vlDato05 = ConstanteServices.VALOR_NO_ASOCIADO;
			}
		}
	}else if(cboTipo.val() == ConstanteServices.CODIGO_MICROORGANISMO || cboTipo.val() == ConstanteServices.CODIGO_MICRO_PARASITO){
		obtenerListaSubparametro();
		for(var i=0; i<lista.length; i++){
			if(!IsEmpty(lista[i].vlDato01)){
				lista[i].subParametro = getDescripcionSubParametroMicro(lista[i].vlDato01);
			}else{
				lista[i].subParametro = ConstanteServices.VALOR_NO_ASOCIADO;
			}
		}
	}
	tblResultadosMantenimiento.bootstrapTable('load', lista);
}

function obtenerListaSubparametro(){
	var idParametro = 0;
	if(cboTipo.val() == ConstanteServices.CODIGO_MICROORGANISMO){
		idParametro = ConstanteServices.ID_HIDROBIOLOGICOS;
	}else if(cboTipo.val() == ConstanteServices.CODIGO_MICRO_PARASITO){
		idParametro = ConstanteServices.ID_PARASITOLOGICOS;
	}
	$.ajax({
        url : "./buscarSubParametroMicroorganismo",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametro  : idParametro
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {
        		ltaSubParametroMicro = JSON.parse(ListaResultado);
        	} else {
		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function ocultarColumnaPuntoMuestra(){
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'descripcionCorta');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato01');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato02');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato03');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato06');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'vlDato05');
	tblResultadosMantenimiento.bootstrapTable('hideColumn', 'subParametro');
}

function getDescripcionSubParametroMicro(idSubParametro){
	var descripcion = ConstanteServices.VALOR_NO_ASOCIADO;
	for(var i=0; i<ltaSubParametroMicro.length; i++){
		if(idSubParametro == ltaSubParametroMicro[i].idSubParametro){
			descripcion = ltaSubParametroMicro[i].descripcionSubParametro;
			break;
		}
	}
	return descripcion;
}

function getDescripcionPtoMuestra(idPtoMuestra){
	var descripcion = "";
	for(var i=0; i<ltaPuntoMuestra.length; i++){
		if(idPtoMuestra == ltaPuntoMuestra[i].idDetalleGeneral){
			descripcion = ltaPuntoMuestra[i].descripcion;
			break;
		}
	}
	return descripcion;
}

function obtenerFiltros(){
    var formFiltro = {		    
        'cboTipo': cboTipo.val()
    }
    return JSON.stringify(formFiltro);
} 


function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboTipo").val(""+filtroObjecto.cboTipo);
		buscarMantenimiento();
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

