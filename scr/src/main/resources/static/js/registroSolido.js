$(document).ready(function() {
			inicializarVariablesSolido();
			cargarComponentesSolido();
});
var accion = false;
var flagSolido = 0;
var flagVerAnalistaSolido = 0;
var nombreAnalistaSolido = "nombreAnalistaSolido";
var flagVerFormulasSolido = 0;
function inicializarVariablesSolido() {
	var tblResultadoRegistroSolido = null;
	var tblVariables1Solido = null;
	var tblVariables2Solido = null;
	var tblVariables3Solido = null;
	var tblVariables4Solido = null;	
	var txtVariable1 = null;
	var txtVariable2 = null;
	var txtVariable3 = null;	
	var btnEditarVariable1 = null;
	var btnEditarVariable2 = null;
	var btnEditarVariable3 = null;
	var btnGuardarVariable1 = null;
	var btnGuardarVariable2 = null;
	var btnGuardarVariable3 = null;
	var cboTipoSolido = null;
	/*inicio observacion para parametro solido*/
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 	
	/**/
}

function cargarComponentesSolido() {
	console.log("SOLIDOS");
	accordion();
	tblVariables1Solido = $('#tblVariables1Solido');
	tblVariables2Solido = $('#tblVariables2Solido');
	tblVariables3Solido = $('#tblVariables3Solido');
	tblVariables4Solido = $('#tblVariables4Solido');	
	txtVariable1 = $('#txtVariable1');
	txtVariable2 = $('#txtVariable2');
	txtVariable3 = $('#txtVariable3');
	btnEditarVariable1 = $('#btnEditarVariable1');
	btnEditarVariable2 = $('#btnEditarVariable2');
	btnEditarVariable3 = $('#btnEditarVariable3');
	btnGuardarVariable1 = $('#btnGuardarVariable1');
	btnGuardarVariable2 = $('#btnGuardarVariable2');
	btnGuardarVariable3 = $('#btnGuardarVariable3');
	cboTipoSolido = $("#cboTipoSolido");
	/*inicio observacion para parametro solido*/
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	/**/
		
	if(ltaRegSolido.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else{
		$("#fechaMonitoreo").text(ltaRegSolido[0].fechaMonitoreoString);
	}
	
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Solido - N?? Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroSolido").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}
	
	agregarListaItem(cboTipoSolido, ltaTipoSolido, "idDetalleGeneral", "descripcion");
	
	$("#cboTipoSolido").val(""+idTipoSolido);
	
	tblResultadoRegistroSolido = $("#tblResultadoRegistroSolido");
	
	cargarGrillaResultadoSolido(ltaRegSolido);
		
	$('#btnAdjuntarRegSolido').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionSolido);
    });
	
	$('#btnNuevoRegSolido').click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarSolido();
		}
    });
	
	$('#btnGuardarTodoRegistroSolido').click(function() {
		mostrarMensaje();
		if(flagSolido == 0){
			if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
				verificarDataGrabarSolido();
			}else{
				mostrarMensaje('El registro esta aprobado, solicitar habilitaci??n', ConstanteServices.IMAGEN_DANGER);
			}
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}				
    });
	
	$('#btnBorrarMultipleRegistroSolido').click(function() {
		mostrarMensaje();
		if(flagSolido == 0){
			verificarEliminarMultipleSolido();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		buscarDatosRegistroSolido();
	}
	
	btnEditarVariable1.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable1();
		}		
    });
	
	btnEditarVariable2.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable2();
		}		
    });
	
	btnEditarVariable3.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable3();
		}		
    });
	
	btnGuardarVariable1.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable1();
		}		
    });
	
	btnGuardarVariable2.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable2();
		}		
    });
	
	btnGuardarVariable3.click(function() {
		if(estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable3();			
		}
    });
	
	$('#btnAprobarRegistroSolido').click(function() {
		mostrarMensaje();
		if(flagSolido == 0){
			verificarAprobacionSolido();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}
    });	
	
	$('#btnHabilitarRegistroSolido').click(function() {
		verificarHabilitarRegistroSolido();
    });	
	
	$('#btnMostrarFRegistroSolido').click(function() {
		if(flagVerFormulasSolido == 0){
			flagVerFormulasSolido = 1;
			$("#btnMostrarFRegistroSolido").text("Ocultar Formulas");
			$("#divVariablesSolido").show(1000);
			$("#divFormulasSolido").show(1000);
		}else{
			flagVerFormulasSolido = 0;
			$("#btnMostrarFRegistroSolido").text("Ver Formulas");
			$("#divVariablesSolido").hide(1000);
			$("#divFormulasSolido").hide(1000);
		}	
    });	
	
	$('#btnVerAnalistaRegSolido').click(function() {
		if(flagVerAnalistaSolido == 0){
			flagVerAnalistaSolido = 1;
			$("#btnVerAnalistaRegSolido").text("Ocultar Analista");
		}else{
			flagVerAnalistaSolido = 0;
			$("#btnVerAnalistaRegSolido").text("Ver Analista");
		}
		visibleAnalistaSolido();		
    });	
	
	$('#cboTipoSolido').change(function() {
			var obj = {};
			obj.idPtarSector = idPtarSector;
			obj.idParametro = idParametro;
			obj.idRegistroLaboratorio = !IsEmpty($("#idRegistroLaboratorio").val())? $("#idRegistroLaboratorio").val() : null;
			obj.descripcionPtar = descripcionPtar;
			obj.descripcionSector = descripcionSector;
			obj.idTipoSolido = $("#cboTipoSolido").val();
			cargarPantalla('./cargarVentanaRegistroSolido', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
    });
	
	/*inicio observacion para parametro Aceite*/
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionSolido();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionSolido();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	/**/
	
	formatearInputSolido();	
	cargarGrillaVariablesSolido();
	buscarFormulasPrincipales();
	buscarVariableSolido(ltaVariableGeneral);
	validacionBotonSolido();
	validarPerfilesSolido();
	console.log("solido");
}

function visibleAnalistaSolido(){
	var a  = document.getElementsByClassName(nombreAnalistaSolido);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaSolido == 1){
			 a[i].className = ""+nombreAnalistaSolido;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaSolido;	
		}
	}
}

function validarPerfilesSolido(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegSolido").show();
		$("#btnMostrarFRegistroSolido").show();
		$("#btnHabilitarRegistroSolido").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegSolido").hide();
		$("#btnAprobarRegistroSolido").hide();
		$("#btnHabilitarRegistroSolido").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#cboTipoSolido").removeAttr('disabled');
		$("#btnGuardarTodoRegistroSolido").hide();
		$("#btnAprobarRegistroSolido").hide();
		$("#btnVerAnalistaRegSolido").hide();
		$("#btnNuevoRegSolido").hide();
		$("#btnBorrarMultipleRegistroSolido").hide();
		$("#btnAdjuntarRegSolido").hide();
	}else{		
		tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroSolido").hide();
		$("#btnAprobarRegistroSolido").hide();
		$("#btnHabilitarRegistroSolido").hide();
		$("#btnVerAnalistaRegSolido").hide();
		$("#btnNuevoRegSolido").hide();
	}
}

function validacionBotonSolido(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionSolido != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroSolido").show();
		}else{
			$("#btnAprobarRegistroSolido").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionSolido == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroSolido").show();
		}else{
			$("#btnHabilitarRegistroSolido").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroSolido").hide();
		$("#btnHabilitarRegistroSolido").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionSolido == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroSolido").hide();
	}
}

function validacionGuardarVariable1(){
	mostrarMensaje();
	var item1 = JSON.parse(txtVariable1.attr("valor"));
	if(!IsEmpty(txtVariable1.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable1.val());
		if(!IsEmpty(rptaValidacion)){
	    	mostrarMensaje('El campo S.S.T (mg/L). No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
	    }else{
	    	buscarFormula(1);
	    }
	}else{
		mostrarMensaje('El campo S.S.T (mg/L). es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
}

function validacionGuardarVariable2(){
	mostrarMensaje();
	var item2 = JSON.parse(txtVariable2.attr("valor"));
	if(!IsEmpty(txtVariable2.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable2.val());
		if(!IsEmpty(rptaValidacion)){
			mostrarMensaje('El campo S.S.F (mg/L). No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
		}else{
			buscarFormula(2);
		}
	}else{
		mostrarMensaje('El campo S.S.F (mg/L). es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
}

function validacionGuardarVariable3(){
	mostrarMensaje();
	var item3 = JSON.parse(txtVariable3.attr("valor"));
	if(!IsEmpty(txtVariable3.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable3.val());
		if(!IsEmpty(rptaValidacion)){
			mostrarMensaje('El campo S.S.V (mg/L). No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
		}else{
			buscarFormula(3);
		}
	}else{
		mostrarMensaje('El campo S.S.V (mg/L). es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
}

function saberRowEdicionIndex(idDiv){
	var indice = null;
	var data = tblResultadoRegistroSolido.bootstrapTable("getData");
	for(var  i = 0 ; i < data.length ;i++){
		var valor = $("#"+idDiv+i).val();
		if(valor != null && valor != undefined){
			indice = i;
			break;
		}
	}
	return indice;
}

function buscarFormula(indicador) {
	var item = {};
	var formula = "";
	if(indicador == 1){
		item = JSON.parse(txtVariable1.attr("valor"));
		formula = txtVariable1.val();
	}else if(indicador == 2){
		item = JSON.parse(txtVariable2.attr("valor"));
		formula = txtVariable2.val();
	}else if(indicador == 3){
		item = JSON.parse(txtVariable3.attr("valor"));
		formula = txtVariable3.val();
	}
	
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: ConstanteServices.ID_PARAMETRO_SOLIDO,
        	idFormula : null,
        	combinacion : formula
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	var indicadorGrabar = false;
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		if(lista != null && lista.length > 0){
        			if(item.idFormulaxParametro != lista[0].idFormulaxParametro){
        				//asigna el la nueva formula        				
        				$("#txtVariable"+indicador).attr("valor",JSON.stringify(lista[0]));
        				$("#txtVariable"+indicador).attr("disabled",true);
        				$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
        				$("#btnEditarVariable"+indicador).attr('style', 'display : inherit'); 
        				if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
        					calcularValoresSolidoAll();
        				}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndex("divnumSst_");
        					if(rpta != null){
        						calcularValoresSolido(parseInt(rpta));
        					}
        				}
        			}else{
        				$("#txtVariable"+indicador).attr("disabled",true);
        				$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
        				$("#btnEditarVariable"+indicador).attr('style', 'display : inherit'); 
        				if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndex("divnumSst_");
        					if(rpta != null){
        						calcularValoresSolido(parseInt(rpta));
        					}
        				}
        			}
        		}else{
        			indicadorGrabar = true;
        		}
        	} else {
        		indicadorGrabar = true;
        	}
        } else {
        	indicadorGrabar = true;
        }
    	if(indicadorGrabar){
    		var tituloModal = 'Formula';
     		modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarNuevaFormula('+indicador+')', '', tituloModal);	
    	}
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function grabarNuevaFormula(indicador){
	var item = {};
	var formula = "";
	if(indicador == 1){
		item = JSON.parse(txtVariable1.attr("valor"));
		formula = txtVariable1.val();
	}else if(indicador == 2){
		item = JSON.parse(txtVariable2.attr("valor"));
		formula = txtVariable2.val();
	}else if(indicador == 3){
		item = JSON.parse(txtVariable3.attr("valor"));
		formula = txtVariable3.val();
	}
	item.idFormulaxParametro = null;
	item.combinacionFormula = formula;
	item.tipoOrden = ConstanteServices.TIPO_FORMULA_SECUNDARIO;
	
	$.ajax({
		url: contexto+"general/registrarFormula",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 mantenimientoBean	: JSON.stringify(item)
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){	
	    	mostrarMensaje('Formula Registrada Correctamente', ConstanteServices.IMAGEN_SUCCESS);
	    	$("#txtVariable"+indicador).attr("valor",respuestaBean.parametros.formulaResultado);
			$("#txtVariable"+indicador).attr("disabled",true);
			$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
			$("#btnEditarVariable"+indicador).attr('style', 'display : inherit');
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				calcularValoresSolidoAll();
			}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
				var rpta = saberRowEdicionIndex("divnumSst_");
				if(rpta != null){
					calcularValoresSolido(parseInt(rpta));
				}
			}
        } else {
           	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
}



function validacionEditarVariable1(){
	txtVariable1.attr("disabled",false);
	btnEditarVariable1.attr('style', 'display : none');
	btnGuardarVariable1.attr('style', 'display : inherit');
}

function validacionEditarVariable2(){
	txtVariable2.attr("disabled",false);
	btnEditarVariable2.attr('style', 'display : none');
	btnGuardarVariable2.attr('style', 'display : inherit');
}

function validacionEditarVariable3(){
	txtVariable3.attr("disabled",false);
	btnEditarVariable3.attr('style', 'display : none');
	btnGuardarVariable3.attr('style', 'display : inherit');
}

function formatearInputSolido(){
	ponerClassConjunto("inputDecimal","pesoInicial",0,6,4);
	ponerClassConjunto("inputDecimal","volumenFiltrado",0,7,4);
	ponerClassConjunto("inputDecimal","pesoFinal",0,6,4);
	ponerClassConjunto("inputDecimal","pesoCalcinado",0,6,4);
}

function cargarGrillaVariablesSolido(){
	tblVariables1Solido.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables2Solido.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables3Solido.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables4Solido.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
}

function buscarVariableSolido(ListVariables) {
	if(ListVariables != null && ListVariables.length > 0) {
		var indice = Math.ceil(ListVariables.length/4);
		var listaAux = [];
		for(i = 0; i < indice; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables1Solido.bootstrapTable('load', listaAux);
		
		var listaAux = [];
		for(i = indice; i < (indice*2); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables2Solido.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*2); i < (indice*3); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables3Solido.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*3); i < ListVariables.length; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables4Solido.bootstrapTable('load', listaAux);
	} else {
		tblVariables1Solido.bootstrapTable('load', []);
		tblVariables2Solido.bootstrapTable('load', []);
		tblVariables3Solido.bootstrapTable('load', []);
		tblVariables4Solido.bootstrapTable('load', []);	            		
	}
}

function cargarGrillaResultadoSolido(lista){
	tblResultadoRegistroSolido.bootstrapTable("destroy")
	var claseValidador = "";	
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	} 
	var claseVerAnalista = "nombreAnalistaSolido";
	if(flagVerAnalistaSolido != 1){
		claseVerAnalista = "hidden nombreAnalistaSolido";
	}
	tblResultadoRegistroSolido.bootstrapTable({
		data : lista,
		buttonsAlign : 'right',
		toolbarAlign : 'right',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [
		{
			field : 'checkbox',
			title : '',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			checkbox : true
		},{
			field : 'accion',
			title : 'Acci??n',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesRegistroSolido',
			class: 'controls',
			events : operateEventsSolido,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Item',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'formatoNro'
		},{	
			field : 'nombreAnalista',
			title : 'Analista.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseVerAnalista,
			formatter : 'crearHtmlAnalista'
		}
		,{	
			field : 'descripcionSubparametro',
			title : 'Sub Par??metro.',
			align : '-webkit-center',
			valign : 'middle'
		},{	
			field : 'descripcionPuntoMuestra',
			title : 'Punto de<br>Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
		},{	
			field : 'pesoInicial',
			title : 'Peso<br>Inicial(gr)<br>(h)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlPesoInicial'
		},{	
			field : 'volumenFiltrado',
			title : 'Vol??men<br>Filtrado(mL)<br>(j)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlVolumenFiltrado'
		},{	
			field : 'pesoFinal',
			title : 'Peso<br>Final(gr)<br>(i)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlPesoFinal'
		},{	
			field : 'pesoCalcinado',
			title : 'Peso<br>Calcinado(gr)<br>(k)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlPesoCalcinado'
		},{	
			field : 'numSst',
			title : 'S.S.T.<br>(mg/L).',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter : 'crearHtmlNumSst',
			cellStyle : verificarEstiloColor
		},{	
			field : 'numSsf',
			title : 'S.S.F.<br>(mg/L).',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter: 'crearHtmlNumSsf',
			cellStyle : verificarEstiloColor
		},{	
			field : 'numSsv',
			title : 'S.S.V.<br>(mg/L)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter: 'crearHtmlNumSsv',
			cellStyle : verificarEstiloColor
		}]
	});
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearHtmlAnalista(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR){
			var htmlCombo = '<select class="form-control input-sm" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == parseInt(idAnalistaSelect)){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
				}	
			}
			htmlCombo 	  +='</select>'			
			return [htmlCombo].join('');
		}else{
			var htmlCombo = '<select class="form-control input-sm" disabled = "disabled" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == parseInt(idAnalistaSelect)){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
				}	
			}
			htmlCombo 	  +='</select>'			
			return [htmlCombo].join('');
		}
	}else{
		return [''+row.nombreAnalista].join('');
	}
}

function crearHtmlPuntoMuestra(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" >';
		for (var i = 0; i < ltaPuntoMuestra.length; i++) {
		   if(ltaPuntoMuestra[i].idPuntoMuestra == row.idPuntoMuestra){
			   htmlCombo += '<option selected="selected" value = "'+ltaPuntoMuestra[i].idPuntoMuestra+'">'+ltaPuntoMuestra[i].descripcionCorta+'</option>'
		   }else{
			   htmlCombo += '<option value = "'+ltaPuntoMuestra[i].idPuntoMuestra+'">'+ltaPuntoMuestra[i].descripcionCorta+'</option>'	
		   }
		}
		htmlCombo 	  +='</select>'			
		return [htmlCombo].join('');
	}else{
		return [''+row.descripcionPuntoMuestra].join('');
	}
}

function crearHtmlFechaMonitoreo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = '<div id="dtpMonitoreo_'+index+'">'+row.fechaMonitoreoString+'</div>';
			
		return [htmlimput].join('');
	}else{
		return [''+row.fechaMonitoreoString].join('');
	}
}

function crearHtmlFechaRegDato(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = '<div class="input-group date fechaRegDato" style="width: 128px !important;" id="datetimepickerRegDato_'+index+'">';
		htmlimput += '		<input type="text" id="dtpRegDato_'+index+'" value='+row.fechaRegDatoString+'  readonly="readonly" class="form-control"/>';
		htmlimput += '		<span class="input-group-addon">';
		htmlimput += '			<span class="glyphicon glyphicon-calendar"></span>';
		htmlimput += '		</span>';
		htmlimput += '	</div>';
		return [htmlimput].join('');
	}else{
		return [''+row.fechaRegDatoString].join('');
	}
}

function crearHtmlPesoInicial(value,row,index){
	var tamannio = 64;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.pesoInicial)){
			htmlimput = '<input type="text"  id="divPesoInicial_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoInicial" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divPesoInicial_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value="'+row.pesoInicial+'" class="form-control input-sm pesoInicial" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.pesoInicial].join('');
	}
}

function crearHtmlVolumenFiltrado(value,row,index){
	var tamannio = 72;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.volumenFiltrado)){
			htmlimput = '<input type="text"  id="divVolumenFiltrado_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm volumenFiltrado" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divVolumenFiltrado_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value="'+row.volumenFiltrado+'" class="form-control input-sm volumenFiltrado" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.volumenFiltrado].join('');
	}
}

function crearHtmlPesoFinal(value,row,index){
	var tamannio = 64;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.pesoFinal)){
			htmlimput = '<input type="text"  id="divPesoFinal_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoFinal" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divPesoFinal_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value="'+row.pesoFinal+'" class="form-control input-sm pesoFinal" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.pesoFinal].join('');
	}
}

function crearHtmlPesoCalcinado(value,row,index){
	var tamannio = 64;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.pesoCalcinado)){
			htmlimput = '<input type="text"  id="divPesoCalcinado_'+index+'"  onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoCalcinado" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divPesoCalcinado_'+index+'" value="'+row.pesoCalcinado+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoCalcinado" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.pesoCalcinado].join('');
	}
}

function crearHtmlNumSst(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numSst)){
			htmlimput = '<input type="text"  id="divnumSst_'+index+'"  disabled="disabled"  class="form-control input-sm cantidad" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumSst_'+index+'" value="'+row.numSst+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numSst != null ){
			return [''+row.numSst].join('');
		}else{
			return [''].join('');
		}
	}
}

function crearHtmlNumSsf(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numSsf)){
			htmlimput = '<input type="text"  id="divnumSsf_'+index+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumSsf_'+index+'" value="'+row.numSsf+'" disabled="disabled"  class="form-control input-sm cantidad" style="width: 70px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numSsf != null ){
			return [''+row.numSsf].join('');
		}else{
			return [''].join('');
		}
	}
}

function crearHtmlNumSsv(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numSsv)){
			htmlimput = '<input type="text"  id="divnumSsv_'+index+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumSsv_'+index+'" value="'+row.numSsv+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
			
		}
		return [htmlimput].join('');
	}else{
		if(row.numSsv != null){
			return [''+row.numSsv].join('');
		}else{
			return [''].join('');
		}
	}
}

function crearAccionesRegistroSolido(value, row, index) {	
	    return [
			'<button id="btnGuardarRegistroSolido" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroSolido" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar" >',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroSolido" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroSolido" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
			'<i class="fa fa-reply"></i>',
			'</button>'
        ].join('');			    	
}

function eliminarSubParametroNuevoSolido(idIndice){	
	guardarTablaAntesCambiosSolido();
	tblResultadoRegistroSolido.bootstrapTable('remove', {field: 'indice', values: [parseInt(idIndice)]});
	formatearInputSolido();
	flagSolido = 0;
	mostrarMensaje();
	visibleAnalistaSolido();
}

function verificarDataGrabarSolido(){
	if(flagSolido == 0){	
		var lta = completarLtaRegistroSolido();
		mostrarMensaje();
		if(validarDataTablaSolido(lta)){	
			var tituloModal = 'Registro Solido';
   			modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroSolido()', '', tituloModal);
		}	
	}
}

function validarNuevoRegistroNuevoSolido(objeto){
	var indicador = true;	
	if(IsEmpty(objeto.idPuntoMuestra)){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	if(IsEmpty(objeto.idSubParametro)){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	return indicador;
}

function validarNuevoRegistroEditarSolido(index){
	var indicador = true;	
	var idAnalistaNew = $("#cboAnalista_"+index).val();  
	if(IsEmpty(idAnalistaNew)){
		mostrarMensaje('Debe Seleccionar una Analista.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}	
	
	var idSubPNew = $("#divSubParametro_"+index).val();
	if(IsEmpty(idSubPNew)){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
	if(IsEmpty(idPuntoMuestraNew)){
		mostrarMensaje('Debe Seleccionar un Punto de Muestra.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
		
	var pesoInicialNew = $("#divPesoInicial_"+index).val();
	if(IsEmpty(pesoInicialNew)){
		mostrarMensaje('Debe Ingresar Peso Inicial.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var volumenFiltradoNew = $("#divVolumenFiltrado_"+index).val();
	if(IsEmpty(volumenFiltradoNew)){
		mostrarMensaje('Debe Ingresar Volumen Filtrado.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var pesoFinalNew = $("#divPesoFinal_"+index).val();
	if(IsEmpty(pesoFinalNew)){
		mostrarMensaje('Debe Ingresar Peso Final.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var pesoCalcinadoNew = $("#divPesoCalcinado_"+index).val();
	if(IsEmpty(pesoCalcinadoNew)){
		mostrarMensaje('Debe Ingresar Peso Calcinado.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numSstNew = $("#divnumSst_"+index).val();
	if(numSstNew == ConstanteServices.VARIABLES_INEXISTENTES || numSstNew == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numSstNew) || numSstNew == "Infinity" || numSstNew == "NaN"){
		mostrarMensaje('El S.S.T (mg/L). del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numSsfNew = $("#divnumSsf_"+index).val();
	if(numSsfNew == ConstanteServices.VARIABLES_INEXISTENTES || numSsfNew == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numSsfNew) || numSsfNew == "Infinity" || numSsfNew == "NaN"){
		mostrarMensaje('El S.S.F (mg/L). del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numSsvNew = $("#divnumSst_"+index).val();
	if(numSsvNew == ConstanteServices.VARIABLES_INEXISTENTES || numSsvNew == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numSsvNew) || numSsvNew == "Infinity" || numSsvNew == "NaN"){
		mostrarMensaje('El S.S.V (mg/L). del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	return indicador;
}

function validarDataTablaSolido(listaFinal){
	var indicador = true;
	for(var i = 0 ; i < listaFinal.length ; i++){
		if(IsEmpty(listaFinal[i].idAnalista)){
			mostrarMensaje('Debe Seleccionar una Analista en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(listaFinal[i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].pesoInicial) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar Peso Inicial en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].volumenFiltrado) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar Volumen Filtrado en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(""+listaFinal[i].pesoFinal) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar Peso Final en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(""+listaFinal[i].pesoCalcinado) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar Peso Calcinado en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}			
		
		if(listaFinal[i].numSst == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numSst == ConstanteServices.EXPRESION_INVALIDA){// || IsEmpty(listaFinal[i].numSst) || listaFinal[i].numSst == "Infinity" || listaFinal[i].numSst == "NaN"){
			mostrarMensaje('El S.S.T (mg/L). es Err??neo!', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		
		if(listaFinal[i].numSsf == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numSsf == ConstanteServices.EXPRESION_INVALIDA){// || IsEmpty(listaFinal[i].numSsf) || listaFinal[i].numSsf == "Infinity" || listaFinal[i].numSsf == "NaN"){
			mostrarMensaje('El S.S.F (mg/L). es Err??neo!', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		
		if(listaFinal[i].numSsv == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numSsv == ConstanteServices.EXPRESION_INVALIDA){// || IsEmpty(listaFinal[i].numSsv) || listaFinal[i].numSsv == "Infinity" || listaFinal[i].numSsv == "NaN"){
			mostrarMensaje('El S.S.V (mg/L). es Err??neo!', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return indicador
}

function grabarDatosRegistroSolido(){
	var lta = completarLtaRegistroSolido();
	$.ajax({
        url : "./grabarDatosRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroSolido : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroSolido : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroSolido").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);  
        	$("#lblTitulo").text("Registro Solido - N?? Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionSolido);
        	observacionSolido = respuestaBean.parametros.observacionSolido;
        	validacionBotonSolido();
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroSolido(){
	$.ajax({
        url : "./buscarDatosRegistroSolido",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroLabBusqueda : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	idTipoSolido : (!IsEmpty($("#cboTipoSolido").val()) ? $("#cboTipoSolido").val() : null)
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadoRegistroSolido.bootstrapTable('load', JSON.parse(ListaResultado));	
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		visibleAnalistaSolido();
        		flagSolido = 0;
        	}else{
        		tblResultadoRegistroSolido.bootstrapTable('load', []);
        		visibleAnalistaSolido();
        		flagSolido = 0;
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputSolido();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function grabarItemRegistroSolido(item){
	$.ajax({
        url : "./grabarItemRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	registroSolidoBean : JSON.stringify(item),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagSolido = 0;
        	buscarFormulasPrincipales();
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroSolido(idRegSoli){
	$.ajax({
        url : "./eliminarItemRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroSolido : idRegSoli
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagSolido = 0;
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaSolido();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}


function guardarTablaAntesCambiosSolido(){
	var lista = completarLtaRegistroSolido();
	tblResultadoRegistroSolido.bootstrapTable('load', lista);
	visibleAnalistaSolido();
}

function completarLtaRegistroSolido(){
	var listaFinal = tblResultadoRegistroSolido.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		
		var idAnalistaInterno = $("#cboAnalista_"+i).val();
		if(idAnalistaInterno != null && idAnalistaInterno != undefined){
			listaFinal[i].idAnalista  = idAnalistaInterno;
		}
		
		var idSubPM = $("#divSubParametro_"+i).val();
		if(idSubPM != null && idSubPM != undefined){
			listaFinal[i].idSubParametro  = parseInt(idSubPM);
		}
		
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
		
		var pesoInicial = $("#divPesoInicial_"+i).val();
		if(pesoInicial != null && pesoInicial != undefined){
			if(IsEmpty(pesoInicial)){
				listaFinal[i].pesoInicial = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].pesoInicial  = pesoInicial;
			}
		}
		listaFinal[i].pesoInicial = validarIncorrectosValores(listaFinal[i].pesoInicial);
		
		var volumenFiltrado = $("#divVolumenFiltrado_"+i).val();
		if(volumenFiltrado != null && volumenFiltrado != undefined){
			if(IsEmpty(volumenFiltrado)){
				listaFinal[i].volumenFiltrado = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].volumenFiltrado  = volumenFiltrado;
			}
		}
		listaFinal[i].volumenFiltrado = validarIncorrectosValores(listaFinal[i].volumenFiltrado);
		
		var pesoFinal = $("#divPesoFinal_"+i).val();
		if(pesoFinal != null && pesoFinal != undefined){
			if(IsEmpty(pesoFinal)){
				listaFinal[i].pesoFinal = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].pesoFinal  = pesoFinal;
			}
		}
		listaFinal[i].pesoFinal = validarIncorrectosValores(listaFinal[i].pesoFinal);
		
		var pesoCalcinado = $("#divPesoCalcinado_"+i).val();
		if(pesoCalcinado != null && pesoCalcinado != undefined){
			if(IsEmpty(pesoCalcinado)){
				listaFinal[i].pesoCalcinado = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].pesoCalcinado  = pesoCalcinado;
			}
		}
		listaFinal[i].pesoCalcinado = validarIncorrectosValores(listaFinal[i].pesoCalcinado);
		
		var numSst = $("#divnumSst_"+i).val();
		if(numSst != null && numSst != undefined ){
			if(IsEmpty(numSst)){
				listaFinal[i].numSst = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numSst  = numSst;
			}
		}
		listaFinal[i].numSst = validarIncorrectosValores(listaFinal[i].numSst);
		
		var numSsf = $("#divnumSsf_"+i).val();
		if(numSsf != null && numSsf != undefined ){
			if(IsEmpty(numSsf)){
				listaFinal[i].numSsf = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numSsf  = numSsf;
			}
		}
		listaFinal[i].numSst = validarIncorrectosValores(listaFinal[i].numSst);
		
		var numSsv = $("#divnumSsv_"+i).val();
		if(numSsv != null && numSsv != undefined ){
			listaFinal[i].numSsv  = numSsv;
			if(IsEmpty(numSsv)){
				listaFinal[i].numSsv = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numSsv  = numSsv;
			}
		}
		listaFinal[i].numSsv = validarIncorrectosValores(listaFinal[i].numSsv);
		
		var itemFormulaSst = JSON.parse(txtVariable1.attr("valor"));
		if(itemFormulaSst != null && itemFormulaSst != undefined ){
			listaFinal[i].idFormulaSst  = itemFormulaSst.idFormulaxParametro;
		}
		
		var itemFormulaSsf = JSON.parse(txtVariable2.attr("valor"));
		if(itemFormulaSsf != null && itemFormulaSsf != undefined ){
			listaFinal[i].idFormulaSsf  = itemFormulaSsf.idFormulaxParametro;
		}
		
		var itemFormulaSsv = JSON.parse(txtVariable3.attr("valor"));
		if(itemFormulaSsv != null && itemFormulaSsv != undefined ){
			listaFinal[i].idFormulaSsv  = itemFormulaSsv.idFormulaxParametro;
		}
		listaFinal[i].idTipoSolido = $("#cboTipoSolido").val();
		
	}
	return listaFinal;
}

function crearHtmlComboPuntoMuestraNuevo(ltaPM,index,value){
	var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" >';
	for (var i = 0; i < ltaPM.length; i++) {
		if(ltaPM[i].idPuntoMuestra == value){
			htmlCombo += '<option selected="selected" value = "'+ltaPM[i].idPuntoMuestra+'">'+ltaPM[i].descripcionCorta+'</option>';
		}else{
			htmlCombo += '<option value = "'+ltaPM[i].idPuntoMuestra+'">'+ltaPM[i].descripcionCorta+'</option>';
		}	   
	}
	htmlCombo 	  +='</select>';		
	return htmlCombo;
}

function crearHtmlComboSubParametroNuevo(ltaSubPara,index,value){
	var htmlComboSubParametro = '<select class="form-control input-sm subParametro" onchange = "verPuntoMuestra('+index+')" id="divSubParametro_'+index+'" >';
	for (var i = 0; i < ltaSubPara.length; i++) {
		if(ltaSubPara[i].idSubParametro == value){
			htmlComboSubParametro += '<option  selected="selected" value = "'+ltaSubPara[i].idSubParametro+'">'+ltaSubPara[i].descripcionSubparametro+'</option>';
		}else{
			htmlComboSubParametro += '<option value = "'+ltaSubPara[i].idSubParametro+'">'+ltaSubPara[i].descripcionSubparametro+'</option>';
		}
	}
	htmlComboSubParametro 	  +='</select>';		
	return [htmlComboSubParametro].join('');	
}

function verPuntoMuestra(index){
	var idSubParametroAux = $("#divSubParametro_"+index).val();
	var descripcionPuntoMuestra = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(5);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametroAux),index,null));
}

function buscarListaPuntoMuestraSubParametro(idSubParametro){
	var listaAuxiliar = [];
	for(var i = 0 ; i< ltaPuntoMuestra.length ; i++){
		if(idSubParametro == ltaPuntoMuestra[i].idSubParametro){
			listaAuxiliar.push(ltaPuntoMuestra[i]);
		}
	}
	return listaAuxiliar;	
}


function crearHtmlAnalistaNuevo(index,value){
	if(IsEmpty(value)){
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR){
			var htmlCombo = '<select class="form-control input-sm" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == parseInt(idAnalistaSelect)){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}
			}
			htmlCombo 	  +='</select>';			
			return htmlCombo ;
		}else{
			var htmlCombo = '<select class="form-control input-sm" disabled="disabled" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == parseInt(idAnalistaSelect)){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}
			}
			htmlCombo 	  +='</select>';			
			return htmlCombo ;
		}
	}else{
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR){
			var htmlCombo = '<select class="form-control input-sm" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == value){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}
			}
			htmlCombo 	  +='</select>';			
			return htmlCombo ;
		}else{
			var htmlCombo = '<select class="form-control input-sm" disabled="disabled" id="cboAnalista_'+index+'" >';
			for (var i = 0; i < ltaAnalista.length; i++) {
				if(ltaAnalista[i].idAnalista == value){
					htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}else{
				    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>';
				}
			}
			htmlCombo 	  +='</select>';			
			return htmlCombo ;
		}
	}	
}


function crearHtmlFechaMonitoreoNuevo(index,value){
	var htmlimput = "";
	if(IsEmpty(value)){
		htmlimput += '<div id="dtpMonitoreo_'+index+'">'+fechaDBOInicial+'</div>';
	}else{
		htmlimput += '<div id="dtpMonitoreo_'+index+'">'+value+'</div>';
	}	
	return htmlimput;
}

function crearHtmlFechaRegDatoNuevo(index,value){
	var htmlimput = '<div class="input-group date fechaRegDato" style="width: 128px !important;" id="datetimepickerRegDato_'+index+'">';
	if(IsEmpty(value)){
		htmlimput += '		<input type="text" id="dtpRegDato_'+index+'" value="'+fechaActual+'"  readonly="readonly" class="form-control"/>';
	}else{
		htmlimput += '		<input type="text" id="dtpRegDato_'+index+'" value="'+value+'"  readonly="readonly" class="form-control"/>';
	}	
	htmlimput += '		<span class="input-group-addon">';
	htmlimput += '			<span class="glyphicon glyphicon-calendar"></span>';
	htmlimput += '		</span>';
	htmlimput += '	</div>';
	return htmlimput;
}

function crearHtmlPesoInicialNuevo(index,value){
	var tamannio = 64;
	if(IsEmpty(value)){
		return '<input type="text"  id="divPesoInicial_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoInicial"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divPesoInicial_'+index+'"  onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value = "'+value+'" class="form-control input-sm pesoInicial" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlVolumenFiltradoNuevo(index,value){
	var tamannio = 72;
	if(IsEmpty(value)){
		return '<input type="text"  id="divVolumenFiltrado_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm volumenFiltrado"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divVolumenFiltrado_'+index+'"  onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value = "'+value+'" class="form-control input-sm volumenFiltrado" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlPesoFinalNuevo(index,value){
	var tamannio = 64;
	if(IsEmpty(value)){
		return '<input type="text"  id="divPesoFinal_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoFinal"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divPesoFinal_'+index+'"  onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" value = "'+value+'" class="form-control input-sm pesoFinal" style="width: '+tamannio+'px !important;" />';
	}	
}

function crearHtmlPesoCalcinadoNuevo(index,value){
	var tamannio = 64;
	if(IsEmpty(value)){
		return '<input type="text"  id="divPesoCalcinado_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')" class="form-control input-sm pesoCalcinado"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divPesoCalcinado_'+index+'" onfocusout = "calcularValoresSolido('+index+')" onkeyup="calcularValoresSolido('+index+')"  value = "'+value+'" class="form-control input-sm pesoCalcinado" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlNumSstNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumSst_'+index+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}else{
		return '<input type="text"  id="divnumSst_'+index+'" value="'+value+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}
}

function crearHtmlNumSsfNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumSsf_'+index+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}else{
		return '<input type="text"  id="divnumSsf_'+index+'" value="'+value+'"  disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}
}

function crearHtmlNumSsvNuevo(index,value){
	if(IsEmpty(value)){
		return'<input type="text"  id="divnumSsv_'+index+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}else{
		return'<input type="text"  id="divnumSsv_'+index+'" value="'+value+'" disabled="disabled" class="form-control input-sm cantidad" style="width: 70px !important;" />';
	}
}

function verificarParametroAgregarSolido(){
	mostrarMensaje();
	if(flagSolido == 0){
		var ltaGrilla = tblResultadoRegistroSolido.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(ltaClonSubParametroTodo[0]);
				objetoClon.indice = countRow;
				objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,countRow,null);
				var idSubParametro = ltaClonSubParametroTodo[0].idSubParametro;
				objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametro),countRow,null);
				objetoClon.nombreAnalista = crearHtmlAnalistaNuevo(countRow,null);
				if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {					
					objetoClon.pesoInicial  = crearHtmlPesoInicialNuevo(countRow,null);
					objetoClon.volumenFiltrado = crearHtmlVolumenFiltradoNuevo(countRow,null);
					objetoClon.pesoFinal = crearHtmlPesoFinalNuevo(countRow,null);
					objetoClon.pesoCalcinado = crearHtmlPesoCalcinadoNuevo(countRow,null);
					objetoClon.numSst = crearHtmlNumSstNuevo(countRow,null);
					objetoClon.numSsf = crearHtmlNumSsfNuevo(countRow,null);
					objetoClon.numSsv = crearHtmlNumSsvNuevo(countRow,null);
				}
				guardarTablaAntesCambiosSolido();
				tblResultadoRegistroSolido.bootstrapTable('insertRow', {
		            index: countRow,
		            row: objetoClon
		        });
				var accionGuardar = tblResultadoRegistroSolido.find('tr').eq(countRow+1).find('td').eq(1).find('#btnGuardarRegistroSolido');
		        var accionEditar = tblResultadoRegistroSolido.find('tr').eq(countRow+1).find('td').eq(1).find('#btnEditarRegistroSolido');
		        var accionAnular = tblResultadoRegistroSolido.find('tr').eq(countRow+1).find('td').eq(1).find('#btnAnularRegistroSolido');
		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
		        formatearInputSolido();
		        flagSolido = 1;
		        visibleAnalistaSolido();
		        actualizarIndicesSolido();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputSolido();
}

function verificarParametroEditarNuevoSolido(index,row){
	mostrarMensaje();
	if(flagSolido == 0){
		var ltaGrilla = tblResultadoRegistroSolido.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(row);
				var accionGuardar = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(1).find('#btnGuardarRegistroSolido');
		        var accionEditar = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(1).find('#btnEditarRegistroSolido');
		        var accionAnular = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(1).find('#btnAnularRegistroSolido');
		        var accionRegresar = tblResultadoRegistroSolido.find('tr').eq(index + 1).find('td').eq(1).find('#btnRegresarRegistroSolido');		        
		        
				var nombreAnalista = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(3);
		        var descripcionSubparametro = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(4);
				var descripcionPuntoMuestra = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(5);
				var pesoInicial  = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(6);
				var volumenFiltrado = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(7);
				var pesoFinal = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(8);
				var pesoCalcinado = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(9);
				var numSst = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(10);
				var numSsf = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(11);
				var numSsv = tblResultadoRegistroSolido.find('tr').eq(index+1).find('td').eq(12);
		        		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
			
		        descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
	        	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(row.idSubParametro),index,row.idPuntoMuestra));
		        if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {		     
					nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));	   						
					pesoInicial.html(""+crearHtmlPesoInicialNuevo(index,""+row.pesoInicial));
					volumenFiltrado.html(""+crearHtmlVolumenFiltradoNuevo(index,""+row.volumenFiltrado));
					pesoFinal.html(""+crearHtmlPesoFinalNuevo(index,""+row.pesoFinal));
					pesoCalcinado.html(""+crearHtmlPesoCalcinadoNuevo(index,""+row.pesoCalcinado));
					numSst.html(""+crearHtmlNumSstNuevo(index,""+row.numSst));
					numSsf.html(""+crearHtmlNumSsfNuevo(index,""+row.numSsf));
					numSsv.html(""+crearHtmlNumSsvNuevo(index,""+row.numSsv));
					//extraemos las formulas 					
					buscarFormulaEdicion(row.idFormulaSst,1);
					buscarFormulaEdicion(row.idFormulaSsf,2);
					buscarFormulaEdicion(row.idFormulaSsv,3);
					accionRegresar[0].style.display = 'inherit';
		        }
		        formatearInputSolido();
		        flagSolido = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputSolido();
}

function calcularValoresSolidoAll(){
	var listaFinal = tblResultadoRegistroSolido.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		calcularValoresSolido(i);
	}
}


function calcularValoresSolido(index){
	var pesoInicial = $("#divPesoInicial_"+index).val();
	var volumenFiltrado = $("#divVolumenFiltrado_"+index).val();
	var pesoFinal = $("#divPesoFinal_"+index).val();
	var pesoCalcinado = $("#divPesoCalcinado_"+index).val();
	
	//para SST
	if(IsEmpty(pesoInicial) || IsEmpty(volumenFiltrado) || IsEmpty(pesoFinal) || IsEmpty(pesoCalcinado)){
		$("#divnumSst_"+index).val("");
		$("#divnumSsf_"+index).val("");
		$("#divnumSsv_"+index).val("");
	}else{
		var listaVariablesInterna = [];
		var objpesoInicial = {
		    descripcionLetra : 'h',
		    valor : ''+pesoInicial
		};
		var objvolumenFiltrado = {
		    descripcionLetra : 'j',
		    valor : ''+volumenFiltrado
		};
		
		var objpesoFinal = {
		    descripcionLetra : 'i',
		    valor : ''+pesoFinal
		};
		var objpesoCalcinado = {
		    descripcionLetra : 'k',
		    valor : ''+pesoCalcinado
		};
		listaVariablesInterna.push(objpesoInicial);
		listaVariablesInterna.push(objvolumenFiltrado);
		listaVariablesInterna.push(objpesoFinal);
		listaVariablesInterna.push(objpesoCalcinado);
		
		var formulaSst = txtVariable1.val();
		var formulaSsf = txtVariable2.val();
		var formulaSsv = txtVariable3.val();		
		var respuestaSst = ejecutarFormula(listaVariablesInterna,formulaSst);
		var respuestaSsf = ejecutarFormula(listaVariablesInterna,formulaSsf);
		var respuestaSsv = ejecutarFormula(listaVariablesInterna,formulaSsv);
		
		if(respuestaSst != ConstanteServices.VARIABLES_INEXISTENTES && respuestaSst != ConstanteServices.EXPRESION_INVALIDA && respuestaSst != null){
			var numSstFinal = cortarDecimalRedondear(respuestaSst,2);
			$("#divnumSst_"+index).val(""+numSstFinal.replace(".00",""));
		}else{
			$("#divnumSst_"+index).val(""+respuestaSst);
		}
		
		
		if(respuestaSsf != ConstanteServices.VARIABLES_INEXISTENTES && respuestaSsf != ConstanteServices.EXPRESION_INVALIDA && respuestaSsf != null){
			var numSsfFinal = cortarDecimalRedondear(respuestaSsf,2);
			$("#divnumSsf_"+index).val(""+numSsfFinal.replace(".00",""));
		}else{
			$("#divnumSsf_"+index).val(""+respuestaSsf);
		}
		
		if(respuestaSsv != ConstanteServices.VARIABLES_INEXISTENTES && respuestaSsv != ConstanteServices.EXPRESION_INVALIDA && respuestaSsv != null){
			var numSsvFinal = cortarDecimalRedondear(respuestaSsv,2);
			$("#divnumSsv_"+index).val(""+numSsvFinal.replace(".00",""));
		}else{
			$("#divnumSsv_"+index).val(""+respuestaSsv);
		}
	}
	completarLtaRegistroSolido();
}

function buscarFormulasPrincipales() {
	$.ajax({
        url : contexto+"general/buscarFormulaPrincipales",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: (!IsEmpty(idParametro) ? idParametro : null)
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		if(lista != null && lista.length > 0){
        			if(lista.length > 0){
            			txtVariable1.val(""+lista[0].combinacionFormula);
            			txtVariable1.attr("valor",JSON.stringify(lista[0]));
            			txtVariable1.attr("disabled",true);
					}
        			if(lista.length > 1){
            			txtVariable2.val(""+lista[1].combinacionFormula);
            			txtVariable2.attr("valor",JSON.stringify(lista[1]));
            			txtVariable2.attr("disabled",true);
					}
        			if(lista.length > 2){
            			txtVariable3.val(""+lista[2].combinacionFormula);
            			txtVariable3.attr("valor",JSON.stringify(lista[2]));
            			txtVariable3.attr("disabled",true);
					}
        		}
        	} else {
		        mostrarMensaje('No se encontraron Formulas Principales.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function buscarFormulaEdicion(idFormula,indicador){
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: ConstanteServices.ID_PARAMETRO_SOLIDO,
        	idFormula : idFormula,
        	combinacion : null
        }
    }).done(function(respuestaBean) {
    	var validaError = false;
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		if(lista != null && lista.length > 0){
        			$("#txtVariable"+indicador).attr("valor",JSON.stringify(lista[0]));
        			$("#txtVariable"+indicador).val(""+lista[0].combinacionFormula);
    				$("#txtVariable"+indicador).attr("disabled",true);
    				$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
    				$("#btnEditarVariable"+indicador).attr('style', 'display : inherit');
        		}else{
        			validaError = true;	
        		}
        	}else{
        		validaError = true;	            		
        	} 
        }else{
        	validaError = true;	
        }
    	if(validaError){
    		if(indicador == 1){
    			mostrarMensaje('No se encontro la Formula S.S.T (mg/L) del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}else if(indicador == 2){
    			mostrarMensaje('No se encontro la Formula S.S.F (mg/L) del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}else if(indicador == 3){
    			mostrarMensaje('No se encontro la Formula S.S.V (mg/L) del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}    		
    	}    	
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}


function verificarAprobacionSolido(){
	if(flagSolido == 0){
		var tituloModal = 'Aprobaci??n Registro Solido';
		modal.confirmer('??Esta seguro de Aprobar Registro Solido ?', 'aprobarRegistroSolido()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroSolido(){
	$.ajax({
        url : "./aprobarRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	flagValidador : 1
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnAprobarRegistroSolido").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionSolido = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonSolido();
        	tblResultadoRegistroSolido.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroSolido(){
	if(flagSolido == 0){
		var tituloModal = 'Habilitaci??n Registro Solido';
		modal.confirmer('??Esta seguro de Habilitar Registro Solido ?', 'habilitarRegistroSolido()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroSolido(){
	$.ajax({
        url : "./aprobarRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	flagValidador : 1
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnAprobarRegistroSolido").hide();
        	mostrarMensaje('Se Habilit?? Registo Solido Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionSolido = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonSolido();
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarEliminarMultipleSolido(){
	if(flagSolido == 0){
		var seleccionados = tblResultadoRegistroSolido.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminaci??n M??ltiple Solido';
   			modal.confirmer('??Esta seguro de Proceder con la '+tituloModal+' ?', 'EliminarMultipleRegistroSolido()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function EliminarMultipleRegistroSolido(){
	var seleccionados = tblResultadoRegistroSolido.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			eliminarSubParametroNuevoSolido(seleccionados[i].indice);
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		eliminarVariosItemRegistroSolido(JSON.stringify(seleccionados));
	}
}

function eliminarVariosItemRegistroSolido(ltaRegistroSolido){
	$.ajax({
        url : "./eliminarVariosItemRegistroSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	ltaRegistroSolido : ltaRegistroSolido
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagSolido = 0;
        	buscarDatosRegistroSolido();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaSolido();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function actualizarIndicesSolido(){
	var data = tblResultadoRegistroSolido.bootstrapTable("getData");
	for(var i=0; i<data.length; i++){
		data[i].indice = i+1;
	}
}

/*funcion grabar observacion de parametro solido*/
function guardarObservacionSolido(){
	$.ajax({
        url : "./guardarObservacionSolido",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroSolido : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonSolido();
        	observacionSolido = respuestaBean.parametros.observacionSolido;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionSolido(){
	var tituloModal = 'Observacion Registro Solido';
	modal.confirmer('??Esta seguro de Grabar la Observaci??n del Registro?', 'guardarObservacionSolido()', 'regresarObservacionSolido()', tituloModal);
}

function regresarObservacionSolido(){
	txtObservacionRegistro.val(""+observacionSolido);
}
/**/

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divPesoInicial'){
        	var newId = "divVolumenFiltrado_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divVolumenFiltrado'){
        	var newId = "divPesoFinal_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoFinal'){
        	var newId = "divPesoCalcinado_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        break;
      case 'ArrowLeft':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divPesoCalcinado'){
        	var newId = "divPesoFinal_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoFinal'){
        	var newId = "divVolumenFiltrado_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divVolumenFiltrado'){
        	var newId = "divPesoInicial_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        
        break;
    }
  }


