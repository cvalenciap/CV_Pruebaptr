$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			console.log("entrada");
});
var accion = false;
var flagTree = false;
var listaTreeSelect = [];

function inicializarVariables() {
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
	var cboTipoReporte = null;
	var txtSector = null;
	var dtpInicio = null;
	var dtpFin = null;
	
	var btnBuscarArbol = null;
	var txtIdRegistroLaboratorio = null;
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
	cboTipoReporte = $("#cboTipoReporte");
	txtSector = $("#txtSector");
	dtpInicio = $('#dtpInicio');
	dtpFin = $('#dtpFin');
	btnBuscarArbol = $('#btnBuscarArbol');
	txtIdRegistroLaboratorio = $("#txtIdRegistroLaboratorio");
	
	$('#datetimepickerInicio').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});

	$('#datetimepickerFin').datepicker({
		autoclose: true,
		format: 'dd/mm/yyyy'
	});
		
	btnLimpiar.click(function(event){
		limpiarParametrosBusqueda();
	});
	
	btnReporte.click(function(event){
		if(validarDatosEntradaPrev()){
			var html = 
				'<div class="row">' + 
					'<div class="col-sm-12">' + 
						'<label class="control-label">¿Se imprimir&aacute; el Reporte. ¿Desea Continuar?</label>' + 
					'</div>' + 
				'</div>';
			var titulo = 'Reporte Histórico de Registros de Laboratorio';
			modal.defaultModal(html, 'buscarRegistroReporteHistorico()' , '', titulo, 'Aceptar', 'Cancelar');
		}		
	});
	
	agregarListaItem(cboPtarxSector, ltaPtarxSector, "idPtarxsector", "descripcionPtar");
	cargarSector();
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	
	
	cboPtarxSector.change(function(event){
		cargarSector();
    });
	
	btnBuscarArbol.click(function(event){
		if(validarDatosEntradaPrev()){
			buscarReporteHistoricoPrev();
		}
	});
	
}

function reporteRegistroLaboratorioHistorico(){
	$.redirect("./reporteRegistroLaboratorioHistorico", {"fechaInicio" : dtpInicio.val(), "fechaFin" : dtpFin.val(), "idPtarxSector" : cboPtarxSector.val(), "descripcionPtar" : extraerTextoCombo("cboPtarxSector"), "descripcionSector" : txtSector.val(), "listaTree" : JSON.stringify(listaTreeSelect)});
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

function cargarSector() {	
	for(i=0; i<ltaPtarxSector.length; i++){
		if(cboPtarxSector.val() == ltaPtarxSector[i].idPtarxsector){
			txtSector.val(ltaPtarxSector[i].descripcionSector);
		}
	}
	if(cboPtarxSector.val()=="" || cboPtarxSector.val()==null){
		txtSector.val("");
	}
}

function validarDatosEntradaPrev(){
    var estado = true;
    var fechaInicio = $(datetimepickerInicio).datepicker("getDate");
    var fechaFin = $(datetimepickerFin).datepicker("getDate");
    if(IsEmpty(dtpInicio.val())){           
    	mostrarMensaje('Debe Seleccionar una Fecha de Inicio.', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(IsEmpty(dtpFin.val())){           
     	mostrarMensaje('Debe Seleccionar una Fecha Fin', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(fechaInicio > fechaFin){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    if(fechaFin < fechaInicio){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    var idPtarxSector = $(cboPtarxSector).val();
	if(IsEmpty(idPtarxSector)){
		mostrarMensaje('Debe Seleccionar un PTAR.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var descripcionSector = $(txtSector).val();
	if(IsEmpty(descripcionSector)){
		mostrarMensaje('Debe Seleccionar un Sector', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
    return estado;
}

function validarDatosEntrada(){
	var estado = true;
	if(flagTree){
		if(!listaTreeSelect.length > 0){
			mostrarMensaje('Debe seleccionar por lo menos un nodo para imprimir el Reporte', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return estado;
}

function limpiarParametrosBusqueda(){
	cboPtarxSector.val("");
	cargarSector();
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	$('#divTreeSection').hide();
	listaTreeSelect = [];
	cboPtarxSector.removeAttr("disabled");
	mostrarMensaje();
}

function buscarRegistroReporteHistorico() {
	if(validarDatosEntrada()) {
		$.ajax({
	        url : "./buscarRegistroReporteHistorico",
	        type : "POST",
	        dataType : "json",
	        async:true,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
	        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
	        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	    		var ListaResultado = respuestaBean.parametros.ListaResultado;
	    		console.log(ListaResultado);
	        	if(ListaResultado != null && ListaResultado.length > 0 ) {		            		
	        		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	        		reporteRegistroLaboratorioHistorico();
	        	}else{
	        		mostrarMensaje("No se encontraron registros con los parametros ingresados", ConstanteServices.IMAGEN_DANGER);
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}

function buscarReporteHistoricoPrev(){
	$.ajax({
        url : "./buscarReporteHistoricoPrev",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),	
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
    		var listaResultado = respuestaBean.parametros.ListaResultado;
        	if(listaResultado != null && listaResultado.length > 0 ) {
        		listaTreeSelect = [];
        		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        		var registroLab = respuestaBean.parametros.registro;
        		$("#divTreeSection").show();
        		$("#nodeTittle").text("Nodos Asociados a " + extraerTextoCombo("cboPtarxSector")); 
        		construirArbol(listaResultado);
        		cboPtarxSector.attr("disabled", "disabled");
        	}else{
        		mostrarMensaje("No se encontraron registros para construir el árbol de parámetros", ConstanteServices.IMAGEN_DANGER);
        		$('#divTreeSection').hide();
        		listaTreeSelect = [];
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarListaNoRepeat(lista, item, value){
	var retorno = true;
	for(var i=0; i<lista.length; i++) {
		if(lista[i][item] == value) {
			retorno = false;
			break;
		}
	}
	return retorno;
}

function construirArbol(listaArbol){
	var parametros = [];
//	lista parametros
	for(var i=0; i<listaArbol.length; i++){
		if(validarListaNoRepeat(parametros, "id", listaArbol[i]["descripcionTipoRegistro"])){
			parametros.push({"id":listaArbol[i]["descripcionTipoRegistro"], "text":listaArbol[i]["descripcionTipoRegistro"], "tipo": ConstanteServices.TIPO_PARAMETRO});
		}
	}
	
//	add lista subParametros
	for(var i=0; i<parametros.length; i++){
		var subParametros = [];
		for(var j=0; j<listaArbol.length; j++){
			if(listaArbol[j]["descripcionTipoRegistro"] == parametros[i]["id"]){
				if(validarListaNoRepeat(subParametros, "id", listaArbol[j]["idSubParametro"])){
					subParametros.push({"id":listaArbol[j]["idSubParametro"], "text":listaArbol[j]["descripcionSubParametro"],"tipo": ConstanteServices.TIPO_SUB_PARAMETRO, "descripcionTipoRegistro": parametros[i]["id"]});
				}
			}
		}
		parametros[i]["items"] = subParametros;
	}
	
	
//	add lista ptoMuestra
	for(var i=0; i<parametros.length; i++){
		for(var j=0; j<parametros[i]["items"].length; j++){
			var ptoMuestra = [];
			for(var k=0; k<listaArbol.length; k++){
				if(listaArbol[k]["idSubParametro"] == parametros[i]["items"][j]["id"] && listaArbol[k]["descripcionTipoRegistro"] == parametros[i]["id"]){
					if(validarListaNoRepeat(ptoMuestra, "id", listaArbol[k]["idPtoMuestra"])){
						ptoMuestra.push({"id":listaArbol[k]["idPtoMuestra"], "text":listaArbol[k]["descripcionPtoMuestra"], 
							"tipo": ConstanteServices.TIPO_PUNTO_MUESTRA,
							"descripcionTipoRegistro": parametros[i]["id"],
							"idSubParametro": parametros[i]["items"][j]["id"],
							"checked": true});
						listaTreeSelect.push({
			            	"idPtoMuestra" : listaArbol[k]["idPtoMuestra"],
			            	"idSubParametro" : parametros[i]["items"][j]["id"],
			            	"descripcionTipoRegistro" : parametros[i]["id"]
			            });
					}
				}
			}
			parametros[i]["items"][j]["items"] = ptoMuestra;
		}
	}
	
	pruebaparam = parametros;
	
	if(flagTree == true){
		var treeview = $("#treeview").data("kendoTreeView");
		treeview.setDataSource(new kendo.data.HierarchicalDataSource({data : parametros}));
	}else{
		generateTree(parametros);
		flagTree = true;
	}
}

function generateTree(data){
	$("#treeview").kendoTreeView({
        check: onCheck,
        checkboxes: {
            checkChildren: true
        },
        dataSource : data
    });
}


// function that gathers IDs of checked nodes
function checkedNodeIds(nodes, checkedNodes, listaTreeSelect) {
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].checked && nodes[i].tipo == ConstanteServices.TIPO_PUNTO_MUESTRA) {
        	checkedNodes.push(nodes[i].id);
        	listaTreeSelect.push({
            	"idPtoMuestra" : nodes[i].id,
            	"idSubParametro" : nodes[i].idSubParametro,
            	"idParametro" : nodes[i].idParametro
            });
        }

        if (nodes[i].hasChildren) {
            checkedNodeIds(nodes[i].children.view(), checkedNodes, listaTreeSelect);
        }
    }
}

// show checked node IDs on datasource change
function onCheck() {
	listaTreeSelect = [];
    var checkedNodes = [],
        treeView = $("#treeview").data("kendoTreeView"),
        message;

    checkedNodeIds(treeView.dataSource.view(), checkedNodes, listaTreeSelect);

    if (checkedNodes.length > 0) {
        message = "IDs of checked nodes: " + checkedNodes.join(",");
    } else {
        message = "No nodes checked.";
    }

    $("#result").html(message);
}
