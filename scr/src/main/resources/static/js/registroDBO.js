$(document).ready(function() {
			inicializarVariablesDBO();
			cargarComponentesDBO();
});
var accion = false;
var flagDBO = 0;
var indicadorEdicionFecha = 0;
var flagVerAnalistaDBO = 0;
var nombreAnalistaDBO = "nombreAnalistaDBO";
var flagVerFormulasDBO = 0;
var numPromedioSCFEntrada = 0;
var numPromedioSCFSalida = 0;
var indicadorDVolumen = 0;

function inicializarVariablesDBO() {
	var tblResultadoRegistroDBO = null;
	var tblVariables1DBO = null;
	var tblVariables2DBO = null;
	var tblVariables3DBO = null;
	var tblVariables4DBO = null;	
	var txtVariable1 = null;
	var txtVariable2 = null;
	var txtVariable3 = null;	
	var btnEditarVariable1 = null;
	var btnEditarVariable2 = null;
	var btnEditarVariable3 = null;
	var btnGuardarVariable1 = null;
	var btnGuardarVariable2 = null;
	var btnGuardarVariable3 = null;
	var dtpFechaVencimiento = null;
	var dtpFechaInicial = null;
	var btnEditarFecha = null;
	var btnGuardarFecha = null;
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 
	var horaInicio = null;
}

function cargarComponentesDBO() {
	console.log("DBO");
	accordion();
	tblVariables1DBO = $('#tblVariables1DBO');
	tblVariables2DBO = $('#tblVariables2DBO');
	tblVariables3DBO = $('#tblVariables3DBO');
	tblVariables4DBO = $('#tblVariables4DBO');	
	txtVariable1 = $('#txtVariable1');
	txtVariable2 = $('#txtVariable2');
	txtVariable3 = $('#txtVariable3');
	btnEditarVariable1 = $('#btnEditarVariable1');
	btnEditarVariable2 = $('#btnEditarVariable2');
	btnEditarVariable3 = $('#btnEditarVariable3');
	btnGuardarVariable1 = $('#btnGuardarVariable1');
	btnGuardarVariable2 = $('#btnGuardarVariable2');
	btnGuardarVariable3 = $('#btnGuardarVariable3');
	btnEditarFecha = $("#btnEditarFecha");
	btnGuardarFecha = $("#btnGuardarFecha");
	
	dtpFechaVencimiento = $('#dtpFechaVencimiento');
	dtpFechaInicial = $('#dtpFechaInicial');	
	
	dtpFechaInicial.val(""+fechaInicial);
	dtpFechaVencimiento.val(""+fechaVencimiento);
	
	horaInicio = $('#horaInicio');
	ponerClassConjuntoHorasHoy("datetimepickerHoraMonitoreo");
	
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro DBO - N?? Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroDBO").hide();
		btnEditarFecha[0].style.display = 'inherit';
		inhabilitarFecha();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}else{
		habilitarFecha();
	}
	
	$('#dtpFechaInicial').change(function() {
		if(!IsEmpty(dtpFechaInicial.val())){
			dtpFechaVencimiento.val(""+dateAdd('d', diasVencimiento, dtpFechaInicial.val()));
		}else{
			dtpFechaVencimiento.val("");
		}		
    });
	
	tblResultadoRegistroDBO = $("#tblResultadoRegistroDBO");
	
	cargarGrillaResultadoDBO(ltaRegDBO);
		
	$('#btnAdjuntarRegDBO').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionDBO);
    });
	
	$('#btnNuevoRegDBO').click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarDBO();
		}
    });
	
	$('#btnGuardarTodoRegistroDBO').click(function() {
		mostrarMensaje();
		if(flagDBO == 0){
			verificarDataGrabarDBO();
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	$('#btnBorrarMultipleRegistroDBO').click(function() {
		mostrarMensaje();
		if(flagDBO == 0){
			verificarEliminarMultipleDBO();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	$('#btnVerSemillaRegDBO').click(function() {
		mostrarMensaje();
		if(flagDBO == 0){
			if(!IsEmpty($("#idRegistroLaboratorio").val())){
				var obj = {};
				obj.idRegistroLaboratorio = $("#idRegistroLaboratorio").val();
				cargarPantalla('./cargarVentanaVerSemilla', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
			}else{
				mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
			}			
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	btnEditarFecha.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			mostrarMensaje();
			if(flagDBO == 0){
				btnEditarFecha[0].style.display = 'none';
				btnGuardarFecha[0].style.display = 'inherit';
				habilitarFecha();
				indicadorEdicionFecha = 1;
			}else{
				mostrarMensaje('Debe Guardar el Registro para Continuar', ConstanteServices.IMAGEN_DANGER);
			}	
		}			
    });
	
	btnGuardarFecha.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			mostrarMensaje();
			if(validarFechas()){
				var tituloModal = 'Fechas DBO';
	   			modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'guardarEdicionFechas()', '', tituloModal);
			}	
		}			
    });
	
	btnEditarVariable1.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable1();
		}
    });
	
	btnEditarVariable2.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable2();
		}
    });
	
	btnEditarVariable3.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionEditarVariable3();
		}
    });
	
	btnGuardarVariable1.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable1();
		}		
    });
	
	btnGuardarVariable2.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable2();
		}		
    });
	
	btnGuardarVariable3.click(function() {
		if(estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			validacionGuardarVariable3();			
		}
    });
	
	$('#btnAprobarRegistroDBO').click(function() {
		mostrarMensaje();
		if(flagDBO == 0){
			verificarAprobacionDBO();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}		
    });	
	
	$('#btnHabilitarRegistroDBO').click(function() {
		verificarHabilitarRegistroDBO();
    });	
	
	$('#btnMostrarFRegistroDBO').click(function() {
		if(flagVerFormulasDBO == 0){
			flagVerFormulasDBO = 1;
			$("#btnMostrarFRegistroDBO").text("Ocultar Formulas");
			$("#divVariablesDBO").show(1000);
			$("#divFormulasDBO").show(1000);
		}else{
			flagVerFormulasDBO = 0;
			$("#btnMostrarFRegistroDBO").text("Ver Formulas");
			$("#divVariablesDBO").hide(1000);
			$("#divFormulasDBO").hide(1000);
		}	
    });	
	
	$('#btnVerAnalistaRegDBO').click(function() {
		if(flagVerAnalistaDBO == 0){
			flagVerAnalistaDBO = 1;
			$("#btnVerAnalistaRegDBO").text("Ocultar Analista");
		}else{
			flagVerAnalistaDBO = 0;
			$("#btnVerAnalistaRegDBO").text("Ver Analista");
		}
		visibleAnalistaDBO();		
    });	
	
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionDBO();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionDBO();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	
	formatearInputDBO();	
	cargarGrillaVariablesDBO();
	buscarFormulasPrincipales();
	buscarVariableDBO(ltaVariableGeneral);
	validacionBotonDBO();
	validarPerfilesDBO();
	console.log("DBO TERMINO");	
}

function visibleAnalistaDBO(){
	var a  = document.getElementsByClassName(nombreAnalistaDBO);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaDBO == 1){
			 a[i].className = ""+nombreAnalistaDBO;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaDBO;	
		}
	}
}

function validarPerfilesDBO(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegDBO").show();
		$("#btnMostrarFRegistroDBO").show();
		$("#btnHabilitarRegistroDBO").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegDBO").hide();
		$("#btnAprobarRegistroDBO").hide();
		$("#btnHabilitarRegistroDBO").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroDBO").hide();
		$("#btnAprobarRegistroDBO").hide();
		$("#btnVerAnalistaRegDBO").hide();
		$("#btnNuevoRegDBO").hide();
		$("#btnBorrarMultipleRegistroDBO").hide();
		$("#btnAdjuntarRegDBO").hide();
	}else{		
		tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroDBO").hide();
		$("#btnAprobarRegistroDBO").hide();
		$("#btnHabilitarRegistroDBO").hide();
		$("#btnVerAnalistaRegDBO").hide();
		$("#btnNuevoRegDBO").hide();
	}
}

function validacionBotonDBO(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionDBO != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroDBO").show();
		}else{
			$("#btnAprobarRegistroDBO").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionDBO == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroDBO").show();
		}else{
			$("#btnHabilitarRegistroDBO").hide();
		}
		$("#btnVerSemillaRegDBO").show();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroDBO").hide();
		$("#btnHabilitarRegistroDBO").hide();
		$("#btnVerSemillaRegDBO").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionDBO == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroDBO").hide();
	}
}

function guardarEdicionFechas(){
	$.ajax({
        url : "./actualizarFechasRegistroDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
//        	fechaInicial : dtpFechaInicial.val(),
			fechaInicial : dtpFechaInicial.val() + " " + horaInicio.val() + ":00",
        	fechaVencimiento : dtpFechaVencimiento.val()
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	btnGuardarFecha[0].style.display = 'none';
			btnEditarFecha[0].style.display = 'inherit';
			flagDBO = 0;
			indicadorEdicionFecha = 0;
			inhabilitarFecha();
			buscarDatosRegistroDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarFechas(){
	var indicador = true;
	if(IsEmpty(dtpFechaInicial.val())){
		mostrarMensaje('Debe Seleccionar una Fecha Inicial', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}	
	if(IsEmpty(dtpFechaVencimiento.val())){
		mostrarMensaje('Debe Seleccionar una Fecha Vencimiento', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}	
	return indicador;
}


function habilitarFecha(){
	$('#datetimepickerFechaInicial').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});
	$('#horaInicio').removeAttr("disabled");
}

function inhabilitarFecha(){
	$('#datetimepickerFechaInicial').datepicker('remove');
	$('#horaInicio').attr("disabled", "disabled");
}

function validacionGuardarVariable1(){
	mostrarMensaje();
	var item1 = JSON.parse(txtVariable1.attr("valor"));
	if(!IsEmpty(txtVariable1.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable1.val());
		if(!IsEmpty(rptaValidacion)){
	    	mostrarMensaje('El campo Deplec No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
	    }else{
	    	buscarFormula(1);
	    }
	}else{
		mostrarMensaje('El campo Deplec es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
}

function validacionGuardarVariable2(){
	mostrarMensaje();
	var item2 = JSON.parse(txtVariable2.attr("valor"));
	if(!IsEmpty(txtVariable2.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable2.val());
		if(!IsEmpty(rptaValidacion)){
			mostrarMensaje('El campo Dilc No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
		}else{
			buscarFormula(2);
		}
	}else{
		mostrarMensaje('El campo Dilc es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
}

function validacionGuardarVariable3(){
	mostrarMensaje();
	var item3 = JSON.parse(txtVariable3.attr("valor"));
	if(!IsEmpty(txtVariable3.val())){
		var rptaValidacion = validarFormulaIngreso(ltaVariableGeneral, txtVariable3.val());
		if(!IsEmpty(rptaValidacion)){
			mostrarMensaje('El campo DBO5 No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
		}else{
			buscarFormula(3);
		}
	}else{
		mostrarMensaje('El campo DBO5 es obligatorio', ConstanteServices.IMAGEN_DANGER);
	}
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
					}
        			if(lista.length > 1){
            			txtVariable2.val(""+lista[1].combinacionFormula);
            			txtVariable2.attr("valor",JSON.stringify(lista[1]));
					}
        			if(lista.length > 2){
            			txtVariable3.val(""+lista[2].combinacionFormula);
            			txtVariable3.attr("valor",JSON.stringify(lista[2]));
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



function formatearInputDBO(){
	ponerClassConjunto("inputNumerico","numValorSemilla",3,0,0);
	ponerClassConjunto("inputDecimal","numVolumen",0,5,2);
	ponerClassConjunto("inputDecimal","numOdi",0,4,2);
	ponerClassConjunto("inputDecimal","numOdf",0,4,2);
	ponerClassConjunto("inputNumerico","nroFrasco",3,0,0);
}

function cargarGrillaVariablesDBO(){
	tblVariables1DBO.bootstrapTable({
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
	
	tblVariables2DBO.bootstrapTable({
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
	
	tblVariables3DBO.bootstrapTable({
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
	
	tblVariables4DBO.bootstrapTable({
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

function buscarVariableDBO(ListVariables) {
	if(ListVariables != null && ListVariables.length > 0) {
		var indice = Math.ceil(ListVariables.length/4);
		var listaAux = [];
		for(i = 0; i < indice; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables1DBO.bootstrapTable('load', listaAux);
		
		var listaAux = [];
		for(i = indice; i < (indice*2); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables2DBO.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*2); i < (indice*3); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables3DBO.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*3); i < ListVariables.length; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables4DBO.bootstrapTable('load', listaAux);
	} else {
		tblVariables1DBO.bootstrapTable('load', []);
		tblVariables2DBO.bootstrapTable('load', []);
		tblVariables3DBO.bootstrapTable('load', []);
		tblVariables4DBO.bootstrapTable('load', []);	            		
	}
}

function cargarGrillaResultadoDBO(lista){
	tblResultadoRegistroDBO.bootstrapTable("destroy");
	var claseValidador = "";	
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	}
	var claseVerAnalista = "nombreAnalistaDBO";
	if(flagVerAnalistaDBO != 1){
		claseVerAnalista = "hidden nombreAnalistaDBO";
	}
	tblResultadoRegistroDBO.bootstrapTable({
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
			checkbox : true,
			cellStyle : ocultarHijo
		},{
			field : 'accion',
			title : 'Acci??n',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesRegistroDBO',
			class: 'controls',
			events : operateEventsDBO,
			cellStyle : estiloAcciones
		},{
			field : 'nroFrasco',
			title : '# Frasco',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlFrasco',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'nombreAnalista',
			title : 'Analista.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseVerAnalista,
			formatter : 'crearHtmlAnalista',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'descripcionPuntoMuestra',
			title : 'Punto de<br>Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter : 'crearDescripcionPuntoMuestra',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'descripcionSubparametro',
			title : 'Sub Par??metro.',
			align : '-webkit-center',
			valign : 'middle',
			formatter : 'crearDescripcionSubParametro',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'numVolumen',
			title : 'Volumen<br>(a)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlVolumen',
			cellStyle : verificarEstiloDeplec
		},{
			field : 'numValorSemilla',
			title : 'Semilla.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlSemilla',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'numOdi',
			title : 'ODi(mg/L)<br>(b)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlOdi',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'numOdf',
			title : 'ODf(mg/L)<br>(c)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlOdf',
			cellStyle : verificarEstiloDeplec
		},
		{	
			field : 'numDeplec',
			title : 'Deplec (%)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter : 'crearHtmlDeplec',
			cellStyle : verificarEstiloColorDBO
		},{	
			field : 'numDilc',
			title : 'Dilc (%)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter: 'crearHtmlDilc',
			cellStyle : verificarEstiloColorDBO
		},{	
			field : 'numDbo5',
			title : 'DBO5 Total',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter: 'crearHtmlDbo5',
			cellStyle : verificarEstiloColorDBO
		}]
	});
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}


function verificarEstiloDeplec(value, row, index) {	
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		var valorDeplec = 0;
		if((""+row.numDeplec).indexOf("divnumDeplec_") > 0){
			valorDeplec = 0;
		}else{
			valorDeplec = row.numDeplec;
		}

		if(modo == ConstanteServices.ESTADO_OPCION_EDITAR && row.flagMejorValor == ConstanteServices.FLAG_MEJOR_VALOR){
			return {
			    css: {
			      'color' : ConstanteServices.COLOR_DEPLEC,
			      'font-weight': ConstanteServices.ESTILO_STRONG,
			      'background' : ConstanteServices.COLOR_MEJOR_VALOR_CELDA
			    }
			};
		}else{
			if(ConstanteServices.RANGO_DEPLEC_MINIMO <= eval(valorDeplec) && eval(valorDeplec) <= ConstanteServices.RANGO_DEPLEC_MAXIMO){
				return {
				    css: {
				      'color' : ConstanteServices.COLOR_DEPLEC,
				      'font-weight': ConstanteServices.ESTILO_STRONG
				    }
				};
			}else{
				return {};
			}
		}
	}else{
		return {};
	}	
}

function verificarEstiloColorDBO(value, row, index) {	
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		var valorDeplec = 0;
		if((""+row.numDeplec).indexOf("divnumDeplec_") > 0){
			valorDeplec = 0;
		}else{
			valorDeplec = row.numDeplec;
		}
		
		if(modo == ConstanteServices.ESTADO_OPCION_EDITAR && row.flagMejorValor == ConstanteServices.FLAG_MEJOR_VALOR){
			return {
			    css: {
			       'color' : ConstanteServices.COLOR_DEPLEC,
				   'font-weight': ConstanteServices.ESTILO_STRONG,
				   'background' : ConstanteServices.COLOR_MEJOR_VALOR_CELDA
			    }
			};
		}else{
			if(ConstanteServices.RANGO_DEPLEC_MINIMO <= eval(valorDeplec) && eval(valorDeplec) <= ConstanteServices.RANGO_DEPLEC_MAXIMO){
				return {
				    css: {
				      'color' : ConstanteServices.COLOR_DEPLEC,
				      'font-weight': ConstanteServices.ESTILO_STRONG,
				      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
				    }
				};
			}else{
				return {
				    css: {
				      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
				    }
				};
			}
		}
	}else{
		return {
		    css: {
		      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
		    }
		};
	}
}

function ocultarHijo(value, row, index){
	if(row.indicePadre == ConstanteServices.INDICADOR_HIJO){
		return {
		    css: {
		      'display' : 'none'
		    }
		};
	}else{
		return {
		    css: {
		      'display' : 'inherit'
		    }
		};
	}
}

function crearHtmlVolumen(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numVolumen)){
				htmlimput = '<input type="text"  id="divnumVolumen_'+index+'" onfocusout = "calcularValoresDBOAll()" onkeyup="calcularValoresDBOAll()" class="form-control input-sm numVolumen" style="width: '+tamannio+'px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divnumVolumen_'+index+'" onfocusout = "calcularValoresDBOAll()" onkeyup="calcularValoresDBOAll()" value="'+row.numVolumen+'" class="form-control input-sm numVolumen" style="width: '+tamannio+'px !important;" />';
			}
			return [htmlimput].join('');
			htmlCombo 	  +='</select>'			
			return [htmlCombo].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.numVolumen].join('');
		}else{
			return [''].join('');
		}
	}
}



function crearHtmlSemilla(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA && parseInt(row.indicadorSemilla) == parseInt(ConstanteServices.INDICADOR_SEMILLA)) {
		var htmlimput = "";
		if(IsEmpty(""+row.numValorSemilla)){
			htmlimput = '<input type="text"  id="divnumValorSemilla_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numValorSemilla" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumValorSemilla_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value="'+row.numValorSemilla+'" class="form-control input-sm numValorSemilla" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numValorSemilla != null && row.numValorSemilla != 0){
			return [''+row.numValorSemilla].join('');
		}else{
			return [''].join('');
		}
	}
}

function buscarIndicadorSemillaPuntoMuestra(idPuntoMuestra){
	var indicador = 0;
	for(var i = 0 ; i < ltaPuntoMuestra.length ; i ++){
		if(parseInt(ltaPuntoMuestra[i].idPuntoMuestra) == parseInt(idPuntoMuestra)){
			indicador = ltaPuntoMuestra[i].indicadorSemilla;
			break;
		}
	}
	return indicador;
}

function buscarIndicadorSemilla(idPuntoMuestra){
	var indica = null;
	for(var i = 0 ; i < ltaPuntoMuestra.length ; i ++){
		if(parseInt(ltaPuntoMuestra[i].idPuntoMuestra) == parseInt(idPuntoMuestra)){
			indica = ltaPuntoMuestra[i].indicadorSemilla;
			break;
		}
	}
	return indica;
}

function validacionIndicadorSemilla(index, ptoMuestraActual){
	if(!IsEmpty(ptoMuestraActual)){
		var idPuntoMuestra = ptoMuestraActual;
	}else{
		var idPuntoMuestra = $("#cboPuntoMuestra_"+index).val();
	}
	var htmlRow = tblResultadoRegistroDBO.find('tr').eq((index+1)).find('td').eq(7);
	var htmlRowHija = tblResultadoRegistroDBO.find('tr').eq((index+2)).find('td').eq(7);
	for(var i = 0 ; i < ltaPuntoMuestra.length ; i ++){
		if(ltaPuntoMuestra[i].idPuntoMuestra == idPuntoMuestra){
			var objeto = clonarObjeto(ltaPuntoMuestra[i]);
			if(objeto.indicadorSemilla != null && objeto.indicadorSemilla == ConstanteServices.INDICADOR_SEMILLA) {
				var semilla = htmlRow[0].textContent;
				var semillaHija = htmlRowHija[0].textContent;
				var htmlimput = '<input type="text" id="divnumValorSemilla_'+index+'" value="'+semilla+'"  class="form-control input-sm numValorSemilla" style="width: 55px !important;" />';
				var htmlimputHija = '<input type="text" id="divnumValorSemilla_'+parseInt(index+1)+'" value="'+semillaHija+'"  class="form-control input-sm numValorSemilla" style="width: 55px !important;" />';
				htmlRow.html(htmlimput);
				htmlRowHija.html(htmlimputHija);
				formatearInputDBO();
			}else{
				htmlRow.html("");
				htmlRowHija.html("");
			}
			break;
		}
	}	
}


function crearHtmlOdi(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numOdi)){
			htmlimput = '<input type="text"  id="divnumOdi_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numOdi" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumOdi_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value="'+row.numOdi+'" class="form-control input-sm numOdi" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numOdi].join('');
	}
}

function crearHtmlOdf(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numOdf)){
			htmlimput = '<input type="text"  id="divnumOdf_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numOdf" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumOdf_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value="'+row.numOdf+'" class="form-control input-sm numOdf" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numOdf].join('');
	}
}

function crearHtmlFactor(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numFactor)){
			htmlimput = '<input type="text"  id="divnumFactor_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numFactor" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumFactor_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value="'+row.numFactor+'" class="form-control input-sm numFactor" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numFactor].join('');
	}
}

function crearHtmlDeplec(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numDeplec)){
			htmlimput = '<input type="text"  id="divnumDeplec_'+index+'"  disabled="disabled"  class="form-control input-sm numDeplec" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumDeplec_'+index+'" value="'+row.numDeplec+'" disabled="disabled" class="form-control input-sm numDeplec" style="width: 70px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numDeplec != null ){
			return [''+row.numDeplec].join('');
		}else{
			return [''].join('');
		}
	}
}

function crearHtmlDilc(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numDilc)){
			htmlimput = '<input type="text"  id="divnumDilc_'+index+'"  disabled="disabled"  class="form-control input-sm numDilc" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumDilc_'+index+'" value="'+row.numDilc+'" disabled="disabled" class="form-control input-sm numDilc" style="width: 70px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numDilc != null ){
			return [''+row.numDilc].join('');
		}else{
			return [''].join('');
		}
	}
}

function crearHtmlDbo5(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numDbo5)){
			htmlimput = '<input type="text"  id="divnumDbo5_'+index+'"  disabled="disabled"  class="form-control input-sm numDbo5" style="width: 70px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnumDbo5_'+index+'" value="'+row.numDbo5+'" disabled="disabled" class="form-control input-sm numDbo5" style="width: 70px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numDbo5 != null ){
			return [''+row.numDbo5].join('');
		}else{
			return [''].join('');
		}
	}
}


function crearHtmlAnalista(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlCombo = '';
			if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR){
				htmlCombo += '<select class="form-control input-sm" id="cboAnalista_'+index+'" >'
			}else{
				htmlCombo += '<select class="form-control input-sm" disabled = "disabled" id="cboAnalista_'+index+'" >';
			}
			for (var i = 0; i < ltaAnalista.length; i++) {			
				if(!IsEmpty(row.idAnalista)){
					if(ltaAnalista[i].idAnalista == row.idAnalista){
						htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
					}else{
					    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
					}
				}else{
					if(ltaAnalista[i].idAnalista == parseInt(idAnalistaSelect)){
						htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
					}else{
					    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
					}
				}
			}
			htmlCombo 	  +='</select>'			
			return [htmlCombo].join('');
		}else {
			return [''].join('');
		}
		
	}else{
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.nombreAnalista].join('');
		}else{
			return [''].join('');
		}
	}
}


function crearDescripcionPuntoMuestra(value,row,index){
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.descripcionPuntoMuestra].join('');
		}else{
			return [''].join('');
		}
}

function crearDescripcionSubParametro(value,row,index){
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.descripcionSubparametro].join('');
		}else{
			return [''].join('');
		}
}

function crearHtmlFrasco(value,row,index){
	var tamannio = 55;
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.nroFrasco)){
			htmlimput = '<input type="text"  id="divnroFrasco_'+index+'"  class="form-control input-sm nroFrasco" style="width: '+tamannio+'px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divnroFrasco_'+index+'" value="'+row.nroFrasco+'" class="form-control input-sm nroFrasco" style="width: '+tamannio+'px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.nroFrasco].join('');
	}	
}



function crearAccionesRegistroDBO(value, row, index) {	
	if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		
		return [
			'<button id="btnGuardarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar" >',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
			'<i class="fa fa-reply"></i>',
			'</button>'
	    ].join('');	
		
	}else{
		return [''].join('');
	}
	
			    	
}


function buscarFormulaEdicion(idFormula,indicador){
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: ConstanteServices.ID_PARAMETRO_DBO,
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
    			mostrarMensaje('No se encontro la Formula Deplec del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}else if(indicador == 2){
    			mostrarMensaje('No se encontro la Formula Dilc del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}else if(indicador == 3){
    			mostrarMensaje('No se encontro la Formula DBO5 del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);
    		}    		
    	}    	
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function saberRowEdicionIndex(idDiv){
	var indice = null;
	var data = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var  i = 0 ; i < data.length ;i++){
		var valor = $("#"+idDiv+i).val();
		if(valor != null && valor != undefined){
			indice = i;
			break;
		}
	}
	return indice;
}

function calcularValoresDBOAll(){
	var listaFinal = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		calcularValoresDBO(i);
	}
}

function calcularValoresDBO(index){
	validacionMenor(index);
	var ltaCalculo = tblResultadoRegistroDBO.bootstrapTable("getData");
	if(ltaCalculo[index].indicePadre == ConstanteServices.INDICADOR_PADRE){
		var numVolumen = $("#divnumVolumen_"+index).val();
	}else{
		var numVolumen = $("#divnumVolumen_"+(index-1)).val();
	}
	
	var numOdi = $("#divnumOdi_"+index).val();
	var numOdf = $("#divnumOdf_"+index).val();
	//para SST
	if(IsEmpty(numVolumen) || IsEmpty(numOdi) || IsEmpty(numOdf)){
		$("#divnumDeplec_"+index).val("");
		$("#divnumDilc_"+index).val("");
		$("#divnumDbo5_"+index).val("");
	}else{
		var listaVariablesInterna = [];
		var objnumVolumen = {
		    descripcionLetra : 'a',
		    valor : ''+numVolumen
		};
		var objnumOdi = {
		    descripcionLetra : 'b',
		    valor : ''+numOdi
		};		
		var objnumOdf = {
		    descripcionLetra : 'c',
		    valor : ''+numOdf
		};
		listaVariablesInterna.push(objnumVolumen);
		listaVariablesInterna.push(objnumOdi);
		listaVariablesInterna.push(objnumOdf);
		
		var formulaDeplec = txtVariable1.val();
		var formulaDilc = txtVariable2.val();
		var formulaDbo5 = txtVariable3.val();		
		var respuestaDeplec = ejecutarFormula(listaVariablesInterna,formulaDeplec);
		var respuestaDilc = ejecutarFormula(listaVariablesInterna,formulaDilc);
		var respuestaDbo5 = ejecutarFormula(listaVariablesInterna,formulaDbo5);
		
		if(respuestaDeplec != ConstanteServices.VARIABLES_INEXISTENTES && respuestaDeplec != ConstanteServices.EXPRESION_INVALIDA && respuestaDeplec != null){
			respuestaDeplec = cortarDecimalRedondear(respuestaDeplec,2);
			$("#divnumDeplec_"+index).val(""+respuestaDeplec.replace(".00",""));
		}else{
			$("#divnumDeplec_"+index).val(""+respuestaDeplec);
		}
		
		
		if(respuestaDilc != ConstanteServices.VARIABLES_INEXISTENTES && respuestaDilc != ConstanteServices.EXPRESION_INVALIDA && respuestaDilc != null){
			respuestaDilc = cortarDecimalRedondear(respuestaDilc,2);
			$("#divnumDilc_"+index).val(""+respuestaDilc.replace(".00",""));
		}else{
			$("#divnumDilc_"+index).val(""+respuestaDilc);
		}
		
		if(respuestaDbo5 != ConstanteServices.VARIABLES_INEXISTENTES && respuestaDbo5 != ConstanteServices.EXPRESION_INVALIDA && respuestaDbo5 != null){
			respuestaDbo5 = cortarDecimalRedondear(respuestaDbo5,2);
			$("#divnumDbo5_"+index).val(""+respuestaDbo5.replace(".00",""));
		}else{
			$("#divnumDbo5_"+index).val(""+respuestaDbo5);
		}
	}
	completarLtaRegistroDBO();
}

function validacionMenor(index){
	var numOdi = $("#divnumOdi_"+index).val();
	var numOdf = $("#divnumOdf_"+index).val();
	if(!IsEmpty(""+numOdi) && !IsEmpty(""+numOdf)){
		if(parseFloat(numOdf) >= parseFloat(numOdi)){
			var el = document.activeElement;
	        var rowNo = el.id.substring(el.id.indexOf("_")+1);
	        var fieldId = el.id.substring(0, el.id.indexOf("_"));
	        var newId = fieldId+"_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
	        mostrarMensaje('El campo ODf mg/L debe ser Menor a ODi mg/L en el item N?? '+(index+1), ConstanteServices.IMAGEN_DANGER);
		}
	}
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
        	idParametroFormula: ConstanteServices.ID_PARAMETRO_DBO,
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
        				//asigna la nueva formula        				
        				$("#txtVariable"+indicador).attr("valor",JSON.stringify(lista[0]));
        				$("#txtVariable"+indicador).attr("disabled",true);
        				$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
        				$("#btnEditarVariable"+indicador).attr('style', 'display : inherit'); 
        				if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
        					calcularValoresDBOAll();
        				}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndex("divnumDeplec_");
        					if(rpta != null){
        						calcularValoresDBO(parseInt(rpta));
        					}
        				}
        			}else{
        				$("#txtVariable"+indicador).attr("disabled",true);
        				$("#btnGuardarVariable"+indicador).attr('style', 'display : none');
        				$("#btnEditarVariable"+indicador).attr('style', 'display : inherit'); 
        				if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndex("divnumDeplec_");
        					if(rpta != null){
        						calcularValoresDBO(parseInt(rpta));
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
				calcularValoresDBOAll();
			}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
				var rpta = saberRowEdicionIndex("divnumDeplec_");
				if(rpta != null){
					calcularValoresDBO(parseInt(rpta));
				}
			}
        } else {
           	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
}

function guardarTablaAntesCambiosDBO(){
	var lista = completarLtaRegistroDBO();
	tblResultadoRegistroDBO.bootstrapTable('load', lista);
	visibleAnalistaDBO();
}

function completarLtaRegistroDBO(){
	var listaFinal = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		
		var nroFrasco = $("#divnroFrasco_"+i).val();
		if(!IsEmpty(nroFrasco)){
			listaFinal[i].nroFrasco  = nroFrasco;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].nroFrasco  = ConstanteServices.VALOR_DEFECTO;
		}
		
		var idAnalistaInterno = $("#cboAnalista_"+i).val();
		if(idAnalistaInterno != null && idAnalistaInterno != undefined){
			listaFinal[i].idAnalista  = idAnalistaInterno;
		}
		
		var fechaInicialString = $("#dtpFechaInicial").val();
		var horaInicioString = $("#horaInicio").val();
		if(fechaInicialString != null && fechaInicialString != undefined){
			listaFinal[i].fechaInicialString  = fechaInicialString + " " + horaInicioString + ":00";
		}
		
		var fechaVencimientoString = $("#dtpFechaVencimiento").val();
		if(fechaVencimientoString != null && fechaVencimientoString != undefined){
			listaFinal[i].fechaVencimientoString  = fechaVencimientoString;
		}
		
		var idSubPM = $("#divSubParametro_"+i).val();
		if(idSubPM != null && idSubPM != undefined){
			listaFinal[i].idSubParametro  = parseInt(idSubPM);
		}
		
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
				
		
		var numValorSemilla = $("#divnumValorSemilla_"+i).val();
		if(numValorSemilla != null && numValorSemilla != undefined){
			if(!IsEmpty(numValorSemilla)){
				listaFinal[i].numValorSemilla = numValorSemilla;
			}else{
				listaFinal[i].numValorSemilla  = ConstanteServices.VALOR_DEFECTO;
			}
			listaFinal[i].indicadorSemilla = ConstanteServices.INDICADOR_SEMILLA;
		}else{
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				listaFinal[i].numValorSemilla = null;
				listaFinal[i].indicadorSemilla = null;
			}
		}
		if(listaFinal[i].indicadorSemilla == Infinity || listaFinal[i].indicadorSemilla == -Infinity || isNaN(listaFinal[i].indicadorSemilla)){
			listaFinal[i].indicadorSemilla = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numValorSemilla == Infinity || listaFinal[i].numValorSemilla == -Infinity || isNaN(listaFinal[i].numValorSemilla)){
			listaFinal[i].numValorSemilla = ConstanteServices.VALOR_DEFECTO;
		}
				
		var numVolumen = $("#divnumVolumen_"+i).val();
		if(numVolumen != null && numVolumen != undefined){
			if(!IsEmpty(numVolumen)){
				listaFinal[i].numVolumen  = numVolumen;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numVolumen  = ConstanteServices.VALOR_DEFECTO;
			}
		}		
		if(listaFinal[i].numVolumen == Infinity || listaFinal[i].numVolumen == -Infinity || isNaN(listaFinal[i].numVolumen)){
			listaFinal[i].numVolumen = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numOdi = $("#divnumOdi_"+i).val();
		if(!IsEmpty(numOdi)){
			listaFinal[i].numOdi  = numOdi;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].numOdi  = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numOdi == Infinity || listaFinal[i].numOdi == -Infinity || isNaN(listaFinal[i].numOdi)){
			listaFinal[i].numOdi = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numOdf = $("#divnumOdf_"+i).val();
		if(!IsEmpty(numOdf)){
			listaFinal[i].numOdf  = numOdf;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].numOdf  = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numOdf == Infinity || listaFinal[i].numOdf == -Infinity || isNaN(listaFinal[i].numOdf)){
			listaFinal[i].numOdf = ConstanteServices.VALOR_DEFECTO;
		}
		
		listaFinal[i].numFactor = ConstanteServices.VALOR_DEFECTO;
		
		var numDeplec = $("#divnumDeplec_"+i).val();
		if(!IsEmpty(numDeplec)){
			listaFinal[i].numDeplec  = numDeplec;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].numDeplec  = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDeplec == Infinity || listaFinal[i].numDeplec == -Infinity || isNaN(listaFinal[i].numDeplec)){
			listaFinal[i].numDeplec = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numDilc = $("#divnumDilc_"+i).val();
		if(!IsEmpty(numDilc)){
			listaFinal[i].numDilc  = numDilc;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].numDilc  = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDilc == Infinity || listaFinal[i].numDilc == -Infinity || isNaN(listaFinal[i].numDilc)){
			listaFinal[i].numDilc = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numDbo5 = $("#divnumDbo5_"+i).val();
		if(!IsEmpty(numDbo5)){
			listaFinal[i].numDbo5  = numDbo5;
		}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
			listaFinal[i].numDbo5  = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDbo5 == Infinity || listaFinal[i].numDbo5 == -Infinity || isNaN(listaFinal[i].numDbo5)){
			listaFinal[i].numDbo5 = ConstanteServices.VALOR_DEFECTO;
		}
		
		var itemFormulaDeplec = JSON.parse(txtVariable1.attr("valor"));
		if(itemFormulaDeplec != null && itemFormulaDeplec != undefined ){
			listaFinal[i].idFormulaDeplec  = itemFormulaDeplec.idFormulaxParametro;
		}
		
		var itemFormulaDilc = JSON.parse(txtVariable2.attr("valor"));
		if(itemFormulaDilc != null && itemFormulaDilc != undefined ){
			listaFinal[i].idFormulaDilc  = itemFormulaDilc.idFormulaxParametro;
		}
		
		var itemFormulaDbo5 = JSON.parse(txtVariable3.attr("valor"));
		if(itemFormulaDbo5 != null && itemFormulaDbo5 != undefined ){
			listaFinal[i].idFormulaDbo5  = itemFormulaDbo5.idFormulaxParametro;
		}
		
	}
	return listaFinal;
}

function calcularPromediosFinal(listaFinal){
	for(var j=0;j<listaFinal.length;j++){
		calcularPromedio(listaFinal, listaFinal[j].idGrupo, listaFinal[j].idPuntoMuestra);//
	}
	return listaFinal;
}

//Completar datos de semilla
function completarDatosSemilla(objRegistroDBO){
	objRegistroDBO.numDepletion = objRegistroDBO.numOdi - objRegistroDBO.numOdf;
	objRegistroDBO.numDepletion = cortarDecimalRedondear(objRegistroDBO.numDepletion, 4);
	/*inicio cvalenciap newList*/
	ltaAux = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var i=0; i<ltaPuntoMuestraTotal.length; i++){
		if(objRegistroDBO['idPuntoMuestra'] == ltaPuntoMuestraTotal[i]['idDetalleGeneral']){
			switch (ltaPuntoMuestraTotal[i]['vlDato03']){
				case ConstanteServices.TIPO_BLANK :
					objRegistroDBO.indicadorTipoSemilla = ConstanteServices.TIPO_BLANK;
			    	objRegistroDBO.blankPromedio = calcularPromedio(ltaAux, objRegistroDBO.idGrupo, objRegistroDBO.idPuntoMuestra);
			    	objRegistroDBO.blankPromedio = validarIncorrectosValores(objRegistroDBO.blankPromedio);
			    	
			    	objRegistroDBO.seededPorDeplec=null;
			    	objRegistroDBO.seededBOD=null;
			    	objRegistroDBO.seededSCF=null;
			    	objRegistroDBO.seededPromedio=null;
			    	objRegistroDBO.GGANetDeplec=null;
			    	objRegistroDBO.GGAPorDeplec=null;
			    	objRegistroDBO.GGADBO=null;
			    	objRegistroDBO.GGAPromedio=null;
			    	objRegistroDBO.samplesNetDeplec=null;
			    	objRegistroDBO.samplesDilFactor=null;
			    	objRegistroDBO.samplesBOD=null;
			    	objRegistroDBO.samplesPromedio=null;
			        break;
				case ConstanteServices.TIPO_SEEDED :
					objRegistroDBO.indicadorTipoSemilla = ConstanteServices.TIPO_SEEDED;
			    	objRegistroDBO.seededPorDeplec = parseFloat(objRegistroDBO.numDepletion)*100/parseFloat(objRegistroDBO.numOdi);
			    	objRegistroDBO.seededBOD = parseFloat(objRegistroDBO.numDepletion)*300/parseFloat(objRegistroDBO.numVolumen);
			    	objRegistroDBO.seededSCF = parseFloat(objRegistroDBO.seededBOD)*(ConstanteServices.CTE_TEMPORAL/300);
			    	objRegistroDBO.seededPromedio = calcularPromedio(ltaAux, objRegistroDBO.idGrupo, objRegistroDBO.idPuntoMuestra);
			    	objRegistroDBO.seededPorDeplec = validarIncorrectosValores(objRegistroDBO.seededPorDeplec);
			    	objRegistroDBO.seededBOD = validarIncorrectosValores(objRegistroDBO.seededBOD);
			    	objRegistroDBO.seededSCF = validarIncorrectosValores(objRegistroDBO.seededSCF);
			    	objRegistroDBO.seededPromedio = validarIncorrectosValores(objRegistroDBO.seededPromedio);
			    	
			    	objRegistroDBO.blankPromedio=null;
			    	objRegistroDBO.GGANetDeplec=null;
			    	objRegistroDBO.GGAPorDeplec=null;
			    	objRegistroDBO.GGADBO=null;
			    	objRegistroDBO.GGAPromedio=null;
			    	objRegistroDBO.samplesNetDeplec=null;
			    	objRegistroDBO.samplesDilFactor=null;
			    	objRegistroDBO.samplesBOD=null;
			    	objRegistroDBO.samplesPromedio=null;
			        break;
				case ConstanteServices.TIPO_GGA :
					objRegistroDBO.indicadorTipoSemilla = ConstanteServices.TIPO_GGA;
					if(ltaPuntoMuestraTotal[i]['vlDato06'] == ConstanteServices.COLUMNA_SP){
						objRegistroDBO.GGANetDeplec = parseFloat(objRegistroDBO.numDepletion)-parseFloat(numPromedioSCFSalida);
					}else{
						objRegistroDBO.GGANetDeplec = parseFloat(objRegistroDBO.numDepletion)-parseFloat(numPromedioSCFEntrada);
					}
			    	objRegistroDBO.GGAPorDeplec = parseFloat(objRegistroDBO.GGANetDeplec)*100/parseFloat(objRegistroDBO.numOdi);
			    	objRegistroDBO.GGADBO = parseFloat(objRegistroDBO.GGANetDeplec)*300/parseFloat(objRegistroDBO.numVolumen);
			    	objRegistroDBO.GGAPromedio = calcularPromedio(ltaAux, objRegistroDBO.idGrupo, objRegistroDBO.idPuntoMuestra);
			    	objRegistroDBO.GGANetDeplec = validarIncorrectosValores(objRegistroDBO.GGANetDeplec);
			    	objRegistroDBO.GGAPorDeplec = validarIncorrectosValores(objRegistroDBO.GGAPorDeplec);
			    	objRegistroDBO.GGADBO = validarIncorrectosValores(objRegistroDBO.GGADBO);
			    	objRegistroDBO.GGAPromedio = validarIncorrectosValores(objRegistroDBO.GGAPromedio);
			    	
			    	objRegistroDBO.blankPromedio=null;
			    	objRegistroDBO.seededPorDeplec=null;
			    	objRegistroDBO.seededBOD=null;
			    	objRegistroDBO.seededSCF=null;
			    	objRegistroDBO.seededPromedio=null;
			    	objRegistroDBO.samplesNetDeplec=null;
			    	objRegistroDBO.samplesDilFactor=null;
			    	objRegistroDBO.samplesBOD=null;
			    	objRegistroDBO.samplesPromedio=null;
			    	break;
				case ConstanteServices.TIPO_SAMPLES :
					objRegistroDBO.indicadorTipoSemilla = ConstanteServices.TIPO_SAMPLES;
					if(ltaPuntoMuestraTotal[i]['vlDato06'] == ConstanteServices.COLUMNA_EP){
						objRegistroDBO.samplesNetDeplec = parseFloat(objRegistroDBO.numDepletion)-parseFloat(numPromedioSCFEntrada);
			    		objRegistroDBO.seededPromedio = parseFloat(numPromedioSCFEntrada);
					}else if(ltaPuntoMuestraTotal[i]['vlDato06'] == ConstanteServices.COLUMNA_SP){
						objRegistroDBO.samplesNetDeplec = parseFloat(objRegistroDBO.numDepletion)-parseFloat(numPromedioSCFSalida);
			    		objRegistroDBO.seededPromedio = parseFloat(numPromedioSCFSalida);
					}
			    	objRegistroDBO.samplesDilFactor = parseFloat(objRegistroDBO.numVolumen)/300;
			    	objRegistroDBO.samplesBOD = parseFloat(objRegistroDBO.samplesNetDeplec)/parseFloat(objRegistroDBO.samplesDilFactor);
			    	objRegistroDBO.samplesPromedio = calcularPromedio(ltaAux, objRegistroDBO.idGrupo, objRegistroDBO.idPuntoMuestra);
			    	objRegistroDBO.samplesNetDeplec = validarIncorrectosValores(objRegistroDBO.samplesNetDeplec);
			    	objRegistroDBO.samplesDilFactor = validarIncorrectosValores(objRegistroDBO.samplesDilFactor);
			    	objRegistroDBO.samplesBOD = validarIncorrectosValores(objRegistroDBO.samplesBOD);
			    	objRegistroDBO.samplesBOD = cortarDecimalRedondear(objRegistroDBO.samplesBOD, 2);
			    	objRegistroDBO.samplesPromedio = validarIncorrectosValores(objRegistroDBO.samplesPromedio);
			    	objRegistroDBO.samplesPromedio = retornarEnteroRedondear(objRegistroDBO.samplesPromedio);
			    	objRegistroDBO.seededPromedio = validarIncorrectosValores(objRegistroDBO.seededPromedio);
			    	
			    	objRegistroDBO.blankPromedio=null;
			    	objRegistroDBO.seededPorDeplec=null;
			    	objRegistroDBO.seededBOD=null;
			    	objRegistroDBO.seededSCF=null;
			    	objRegistroDBO.GGANetDeplec=null;
			    	objRegistroDBO.GGAPorDeplec=null;
			    	objRegistroDBO.GGADBO=null;
			    	objRegistroDBO.GGAPromedio=null;
			    	break;
				default:
			    	objRegistroDBO.indicadorTipoSemilla = "";
			    	objRegistroDBO.blankPromedio=null;
					objRegistroDBO.seededPorDeplec=null;
					objRegistroDBO.seededBOD=null;
					objRegistroDBO.seededSCF=null;
					objRegistroDBO.seededPromedio=null;
					objRegistroDBO.GGANetDeplec=null;
					objRegistroDBO.GGAPorDeplec=null;
					objRegistroDBO.GGADBO=null;
					objRegistroDBO.GGAPromedio=null;
					objRegistroDBO.samplesNetDeplec = null;
					objRegistroDBO.samplesDilFactor = null;
					objRegistroDBO.samplesBOD = null;
					objRegistroDBO.samplesPromedio = null;
					break;
				}
				break;
			}
		}
	return objRegistroDBO;
}

function completarDatosSemillaEdicion(listaFinal){
	clonNumSCF = clonarObjeto(listaFinal[0]);
	clonNumSCF.indicadorTipoSemilla = ConstanteServices.TIPO_SEEDED;
	clonNumSCF.idPuntoMuestra = ConstanteServices.ID_SEMILLA_EP;
	calcularPromedioEdicion(listaFinal, clonNumSCF);
	clonNumSCF.idPuntoMuestra = ConstanteServices.ID_SEMILLA_SP;
	calcularPromedioEdicion(listaFinal, clonNumSCF);
	for(var i=0;i<listaFinal.length;i++){
		listaFinal[i] = completarDatosSemilla(listaFinal[i]);
	}	
	return listaFinal;
}

function calcularPromedio(listaFinal, identificadorGrupo, idPuntoMuestra){
	var resultado = 0;
	var contador = 0;
	var objetoPuntoMuestra = obtenerValoresPtoMuestra(idPuntoMuestra);
	switch (objetoPuntoMuestra['vlDato03']){
	case ConstanteServices.TIPO_BLANK :
		for(var j=0; j<listaFinal.length; j++){
			var objPuntoMuestraAux = obtenerValoresPtoMuestra(listaFinal[j]['idPuntoMuestra']);
			if(listaFinal[j]['idGrupo'] == identificadorGrupo && objPuntoMuestraAux['vlDato03'] == ConstanteServices.TIPO_BLANK){
				resultado = resultado + parseFloat(listaFinal[j]['numDepletion']);
				contador = contador + 1;
			}
		}
		resultado = resultado/contador;
		break;
	case ConstanteServices.TIPO_SEEDED :
		if(objetoPuntoMuestra['vlDato06'] == ConstanteServices.COLUMNA_EP){
			for(var j=0; j<listaFinal.length; j++){
				var objPuntoMuestraAux = obtenerValoresPtoMuestra(listaFinal[j]['idPuntoMuestra']);
				if(objPuntoMuestraAux['vlDato06'] == ConstanteServices.COLUMNA_EP && objPuntoMuestraAux['vlDato03'] == ConstanteServices.TIPO_SEEDED){
					resultado = resultado + parseFloat(listaFinal[j]['seededSCF']);
	    			contador = contador + 1;
				}
	    	}
			resultado = resultado/contador;
			numPromedioSCFEntrada = resultado;
		}else if(objetoPuntoMuestra['vlDato06'] == ConstanteServices.COLUMNA_SP){
			for(var j=0; j<listaFinal.length; j++){
				var objPuntoMuestraAux = obtenerValoresPtoMuestra(listaFinal[j]['idPuntoMuestra']);
				if(objPuntoMuestraAux['vlDato06'] == ConstanteServices.COLUMNA_SP && objPuntoMuestraAux['vlDato03'] == ConstanteServices.TIPO_SEEDED){
	    			resultado = resultado + parseFloat(listaFinal[j]['seededSCF']);
	    			contador = contador + 1;
	    		}
	    	}
			resultado = resultado/contador;
			numPromedioSCFSalida = resultado;
		}
        break;
	case ConstanteServices.TIPO_GGA :
    	for(var j=0; j<listaFinal.length; j++){
    		var objPuntoMuestraAux = obtenerValoresPtoMuestra(listaFinal[j]['idPuntoMuestra']);
    		if(listaFinal[j]['idGrupo'] == identificadorGrupo && objPuntoMuestraAux['vlDato03'] == ConstanteServices.TIPO_GGA){
    			resultado = resultado + parseFloat(listaFinal[j]['GGADBO']);
    			contador = contador + 1;
    		}
    	}
		resultado = resultado/contador;
        break;
	case ConstanteServices.TIPO_SAMPLES :
    	for(var j=0; j<listaFinal.length; j++){
    		var objPuntoMuestraAux = obtenerValoresPtoMuestra(listaFinal[j]['idPuntoMuestra']);
    		if(listaFinal[j]['idGrupo'] == identificadorGrupo && objPuntoMuestraAux['vlDato03'] == ConstanteServices.TIPO_SAMPLES){
    			resultado = resultado + parseFloat(listaFinal[j]['samplesBOD']);
    			contador = contador + 1;
    		}
    	}
		resultado = resultado/contador;
        break;
	}
	return resultado;
}

function obtenerValoresPtoMuestra(idPtoMuestra){
	for(var i=0; i<ltaPuntoMuestraTotal.length; i++){
		if(ltaPuntoMuestraTotal[i]['idDetalleGeneral'] == idPtoMuestra){
			return ltaPuntoMuestraTotal[i];
			break;
		}
	}
}

function calcularPromedioEdicion(listaFinal, objDBO){
	var resultado = calcularPromedio(listaFinal, objDBO.idGrupo, objDBO.idPuntoMuestra);
	return resultado;
}

function buscarDatosRegistroDBO(){
	$.ajax({
        url : "./buscarDatosRegistroDBO",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroLabBusqueda : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadoRegistroDBO.bootstrapTable('load', JSON.parse(ListaResultado));	
        		visibleAnalistaDBO();
        	}else{
        		tblResultadoRegistroDBO.bootstrapTable('load', []);
        		visibleAnalistaDBO();
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputDBO();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

/***Cambio atributo DBO por resultado samples**/
function cambioValorSamples(lista){
	for(var i=0; i<lista.length; i++){
		var objPuntoMuestraAux = obtenerValoresPtoMuestra(lista[i]['idPuntoMuestra']);
		switch (objPuntoMuestraAux['vlDato03']){
		case ConstanteServices.TIPO_BLANK :
			lista[i]['numDbo5'] = lista[i]['numDepletion'];
			break;
		case ConstanteServices.TIPO_SEEDED :
			lista[i]['numDbo5'] = lista[i]['seededSCF'];
			break;
		case ConstanteServices.TIPO_GGA :
			lista[i]['numDbo5'] = lista[i]['GGADBO'];
			break;
		case ConstanteServices.TIPO_SAMPLES :
			lista[i]['numDbo5'] = lista[i]['samplesBOD'];
			break;
		}
		lista[i]['numDbo5Formula'] = obtenerValorDBO5Formula(lista[i]);
	}
	return lista;
}

/***Realiza calculo valor primitivo DBO*/
function obtenerValorDBO5Formula(rowDBO){
	if(IsEmpty(rowDBO.numVolumen) || IsEmpty(rowDBO.numOdi) || IsEmpty(rowDBO.numOdf)){
		return 0;
	}else{
		var listaVariablesInterna = [];
		var objnumVolumen = {
		    descripcionLetra : 'a',
		    valor : ''+rowDBO.numVolumen
		};
		var objnumOdi = {
		    descripcionLetra : 'b',
		    valor : ''+rowDBO.numOdi
		};		
		var objnumOdf = {
		    descripcionLetra : 'c',
		    valor : ''+rowDBO.numOdf
		};
		listaVariablesInterna.push(objnumVolumen);
		listaVariablesInterna.push(objnumOdi);
		listaVariablesInterna.push(objnumOdf);
		var formulaDbo5 = txtVariable3.val();
		respuestaDbo5 = ejecutarFormula(listaVariablesInterna,formulaDbo5);
		
		if(respuestaDbo5 != ConstanteServices.VARIABLES_INEXISTENTES && respuestaDbo5 != ConstanteServices.EXPRESION_INVALIDA && respuestaDbo5 != null){
			respuestaDbo5 = cortarDecimalRedondear(respuestaDbo5,2);
			return respuestaDbo5;
		}else{
			return 0
		}
	}
}

function grabarItemRegistroDBO(lista){
	$.ajax({
        url : "./grabarItemRegistroDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	listaRegistroDBOBean : JSON.stringify(cambioValorSamples(lista)),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarFormulasPrincipales();
        	buscarDatosRegistroDBO();
			//Muestra mensaje de comparaci??n
			obtenerMensajeValoresDBO_Directo(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroDBO(idGrupo, listaActualizar){
	$.ajax({
        url : "./eliminarItemRegistroDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idGrupoRegistro : idGrupo,
        	listaRegistroDBO : JSON.stringify(cambioValorSamples(listaActualizar)),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarDatosRegistroDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaDBO();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarVariosItemRegistroDBO(listaGrupo, listaActualizar){
	$.ajax({
        url : "./eliminarVariosItemRegistroDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	listaGrupoRegistro : listaGrupo,
        	listaRegistroDBO : listaActualizar,
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarDatosRegistroDBO();
        	//Muestra mensaje de comparaci??n
			obtenerMensajeValoresDBO_Directo(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaDBO();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function grabarDatosRegistroDBO(){
	var lta = completarLtaRegistroDBO();
	$.ajax({
        url : "./grabarDatosRegistroDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroDBO : JSON.stringify(cambioValorSamples(lta)),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroDBO : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroDBO").hide();
    		btnEditarFecha[0].style.display = 'inherit';
    		inhabilitarFecha();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);  
        	$("#lblTitulo").text("Registro DBO - N?? Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionDBO);
        	observacionDBO = respuestaBean.parametros.observacionDBO;
        	validacionBotonDBO();
        	buscarDatosRegistroDBO();
        	//Muestra mensaje de comparaci??n
			obtenerMensajeValoresDBO_Directo(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarDataGrabarDBO(){
	if(flagDBO == 0){
		var lta = completarLtaRegistroDBO();
		lta = asignarParametrosHijas(lta);
		numPromedioSCFEntrada = calcularPromedio(lta, 0, ConstanteServices.ID_SEMILLA_EP);//
		numPromedioSCFSalida = calcularPromedio(lta, 0, ConstanteServices.ID_SEMILLA_SP);//
		for(var i=0;i<lta.length;i++){
			//Completar datos de semilla
			lta[i] = completarDatosSemilla(lta[i]);
		}
		lta = completarDatosSemillaEdicion(lta);
		lta = completarDatosSemillaEdicion(lta);
		mostrarMensaje();
		if(validarDataTablaDBO(lta)){	
			var tituloModal = 'Registro DBO';
   			modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroDBO()', '', tituloModal);
		}	
	}
}

function verificarEliminarMultipleDBO(){
	if(flagDBO == 0){
		var seleccionados = tblResultadoRegistroDBO.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminaci??n M??ltiple DBO';
   			modal.confirmer('??Esta seguro de Proceder con la '+tituloModal+' ?', 'EliminarMultipleRegistroDBO()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function EliminarMultipleRegistroDBO(){
	var seleccionados = tblResultadoRegistroDBO.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			if(seleccionados[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
				eliminarSubParametroNuevoDBO(seleccionados[i].idGrupo);
			}
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		var gruposSeleccionados = [];
		for(var i=0; i<seleccionados.length; i++){
			if(seleccionados[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
				gruposSeleccionados.push(seleccionados[i].idGrupo);
			}
		}
		tblResultadoRegistroDBO.bootstrapTable('remove', {field: 'idGrupo', values: gruposSeleccionados});
		ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
		ltaActual = completarDatosSemillaEdicion(ltaGrilla);
		eliminarVariosItemRegistroDBO(JSON.stringify(gruposSeleccionados), JSON.stringify(cambioValorSamples(ltaActual)));
	}
}

function asignarParametrosHijas(listaFinal){
	console.log('entra asignacion');
	for(i=0;i<listaFinal.length;i++){
		if(listaFinal[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			objetoPadre = clonarObjeto(listaFinal[i]);
			listaFinal[i+1].idAnalista = objetoPadre.idAnalista;
			listaFinal[i+1].idPuntoMuestra = objetoPadre.idPuntoMuestra;
			listaFinal[i+1].idSubParametro = objetoPadre.idSubParametro;
			listaFinal[i+1].numVolumen = objetoPadre.numVolumen;
		}
	}
	return listaFinal;
}

function eliminarSubParametroNuevoDBO(idGrupo){	
	guardarTablaAntesCambiosDBO();
	ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var i=0;i<ltaGrilla.length;i++){
		if(ltaGrilla[i].idGrupo == idGrupo && ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			tblResultadoRegistroDBO.bootstrapTable('remove', {field: 'idGrupo', values: [parseInt(ltaGrilla[i].idGrupo)]});
			break;
		}
	}
	formatearInputDBO();
	flagDBO = 0;
	mostrarMensaje();
	visibleAnalistaDBO();
}

function calcularPromediosFinalEditar(){
	var listaFinal = tblResultadoRegistroDBO.bootstrapTable("getData");
	for(var j=0;j<listaFinal.length;j++){
		calcularPromedio(listaFinal, listaFinal[j].idGrupo, listaFinal[j].idPuntoMuestra);//
	}
}

function validarNuevoRegistroNuevoDBO(objeto,indicadorPadre){
	var indicador = true;	
	
	if(IsEmpty(objeto.idPuntoMuestra) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	if(IsEmpty(objeto.idSubParametro) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	return indicador;
}

function validarNuevoRegistroEditarDBO(index, indicadorPadre, idPuntoMuestraNew,objeto){
	var indicador = true;	
	
	if(IsEmpty(objeto.idPuntoMuestra) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	if(IsEmpty(objeto.idSubParametro) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	var nroFrascoNew = $("#divnroFrasco_"+index).val();  
	if(IsEmpty(nroFrascoNew)){
		mostrarMensaje('Debe Ingresar un Nro Frasco.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var idAnalistaNew = $("#cboAnalista_"+index).val();  
	if(IsEmpty(idAnalistaNew) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar una Analista.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var fechaInicialNew = $("#dtpFechaInicial").val();
	if(IsEmpty(fechaInicialNew)){
		mostrarMensaje('Debe Ingresar una Fecha Inicial.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var horaInicialNew = $("#horaInicio").val();
	if(IsEmpty(horaInicialNew)){
		mostrarMensaje('Debe Ingresar una Hora Incial.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var fechaVencimientoNew = $("#dtpFechaVencimiento").val();
	if(IsEmpty(fechaVencimientoNew)){
		mostrarMensaje('Debe Ingresar una Fecha de Vencimiento.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numVolumenNew = $("#divnumVolumen_"+index).val();
	if(IsEmpty(numVolumenNew) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Ingresar Volumen.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numOdiNew = $("#divnumOdi_"+index).val();
	if(IsEmpty(numOdiNew)){
		mostrarMensaje('Debe Ingresar ODi(mg/L).', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numOdfNew = $("#divnumOdf_"+index).val();
	if(IsEmpty(numOdfNew)){
		mostrarMensaje('Debe Ingresar ODf(mg/L).', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numDeplecNew = $("#divnumDeplec_"+index).val();
	if(numDeplecNew == ConstanteServices.VARIABLES_INEXISTENTES || numDeplecNew == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numDeplecNew) || numDeplecNew == "Infinity" || numDeplecNew == "NaN"){
		mostrarMensaje('El Deplec (%) del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numDilcNew = $("#divnumDilc_"+index).val();
	if(numDilcNew == ConstanteServices.VARIABLES_INEXISTENTES || numDilcNew == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numDilcNew) || numDilcNew == "Infinity" || numDilcNew == "NaN"){
		mostrarMensaje('El Dilc (%) del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numDbo5New = $("#divnumDbo5_"+index).val();
	if(numDbo5New == ConstanteServices.VARIABLES_INEXISTENTES || numDbo5New == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numDbo5New) || numDbo5New == "Infinity" || numDbo5New == "NaN"){
		mostrarMensaje('El DBO5 Total del item N?? '+(i+1)+' es Err??neo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	if(parseFloat(numOdfNew) >= parseFloat(numOdiNew)){
		mostrarMensaje('El campo ODf mg/L debe ser Menor a ODi mg/L en el item N?? '+(index+1), ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	//valores semillas
	calcularPromediosFinalEditar();
	
	return indicador;
}

function validarDataTablaDBO(listaFinal){
	var indicador = true;
	if(IsEmpty(dtpFechaInicial.val())){
		mostrarMensaje('Debe Seleccionar una Fecha Inicial', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	if(IsEmpty(horaInicio.val())){
		mostrarMensaje('Debe Seleccionar una Hora Inicial', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	for(var i = 0 ; i < listaFinal.length ; i++){	
				
		if(IsEmpty(listaFinal[i].nroFrasco)){
			listaFinal[i].nroFrasco = ConstanteServices.VALOR_DEFECTO;
		}
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
		if(!IsEmpty(listaFinal[i].indicadorSemilla) && listaFinal[i].indicadorSemilla == ConstanteServices.INDICADOR_SEMILLA){
			if(IsEmpty(listaFinal[i].numValorSemilla)){
				listaFinal[i].numValorSemilla = ConstanteServices.VALOR_DEFECTO;
			}
		}
		if(listaFinal[i].numValorSemilla == Infinity || listaFinal[i].numValorSemilla == -Infinity || isNaN(listaFinal[i].numValorSemilla)){
			listaFinal[i].numValorSemilla = ConstanteServices.VALOR_DEFECTO;
		}
		
		if(IsEmpty(listaFinal[i].numVolumen)){
			listaFinal[i].numVolumen = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numVolumen == Infinity || listaFinal[i].numVolumen == -Infinity || isNaN(listaFinal[i].numVolumen)){
			listaFinal[i].numVolumen = ConstanteServices.VALOR_DEFECTO;
		}
		if(IsEmpty(listaFinal[i].numOdi)){
			listaFinal[i].numOdi = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numOdi == Infinity || listaFinal[i].numOdi == -Infinity || isNaN(listaFinal[i].numOdi)){
			listaFinal[i].numOdi = ConstanteServices.VALOR_DEFECTO;
		}
		if(IsEmpty(listaFinal[i].numOdf)){
			listaFinal[i].numOdf = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numOdf == Infinity || listaFinal[i].numOdf == -Infinity || isNaN(listaFinal[i].numOdf)){
			listaFinal[i].numOdf = ConstanteServices.VALOR_DEFECTO;
		}			
		
		if(listaFinal[i].numDeplec == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numDeplec == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(listaFinal[i].numDeplec)){
			listaFinal[i].numDeplec = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDeplec == Infinity || listaFinal[i].numDeplec == -Infinity || isNaN(listaFinal[i].numDeplec)){
			listaFinal[i].numDeplec = ConstanteServices.VALOR_DEFECTO;
		}
		
		if(listaFinal[i].numDilc == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numDilc == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(listaFinal[i].numDilc)){
			listaFinal[i].numDilc = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDilc == Infinity || listaFinal[i].numDilc == -Infinity || isNaN(listaFinal[i].numDilc)){
			listaFinal[i].numDilc = ConstanteServices.VALOR_DEFECTO;
		}
		
		if(listaFinal[i].numDbo5 == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numDbo5 == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(listaFinal[i].numDbo5)){
			listaFinal[i].numDbo5 = ConstanteServices.VALOR_DEFECTO;
		}
		if(listaFinal[i].numDbo5 == Infinity || listaFinal[i].numDbo5 == -Infinity || isNaN(listaFinal[i].numDbo5)){
			listaFinal[i].numDbo5 = ConstanteServices.VALOR_DEFECTO;
		}
		
		if(parseFloat(listaFinal[i].numOdf) > parseFloat(listaFinal[i].numOdi)){
			mostrarMensaje('El campo ODf mg/L debe ser Menor a ODi mg/L en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		
	}
	return indicador
}

function crearHtmlComboPuntoMuestraNuevo(ltaPM,index,value){
	var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" onchange="validacionIndicadorSemilla('+index+')" >';
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
	var descripcionPuntoMuestra = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(4);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametroAux),index,null));
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

function crearHtmlFrascoNuevo(index,value){
	var tamannio = 70;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnroFrasco_'+index+'" class="form-control input-sm nroFrasco"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnroFrasco_'+index+'" value = "'+value+'" class="form-control input-sm nroFrasco" style="width: '+tamannio+'px !important;" />';
	}
}


function crearHtmlVolumenNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumVolumen_'+index+'" onfocusout = "calcularValoresDBOAll()" onkeyup="calcularValoresDBOAll()" class="form-control input-sm numVolumen"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumVolumen_'+index+'"  onfocusout = "calcularValoresDBOAll()" onkeyup="calcularValoresDBOAll()" value = "'+value+'" class="form-control input-sm numVolumen" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlSemillaNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return  '<input type="text"  id="divnumValorSemilla_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numValorSemilla" style="width: '+tamannio+'px !important;" />';
	}else{
		return  '<input type="text"  id="divnumValorSemilla_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value="'+value+'" class="form-control input-sm numValorSemilla" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlOdiNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumOdi_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numOdi"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumOdi_'+index+'"  onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value = "'+value+'" class="form-control input-sm numOdi" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlOdfNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumOdf_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numOdf"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumOdf_'+index+'"  onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value = "'+value+'" class="form-control input-sm numOdf" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlFactorNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumFactor_'+index+'" onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" class="form-control input-sm numFactor"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumFactor_'+index+'"  onfocusout = "calcularValoresDBO('+index+')" onkeyup="calcularValoresDBO('+index+')" value = "'+value+'" class="form-control input-sm numFactor" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlDeplecNuevo(index,value){
	var tamannio = 70;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumDeplec_'+index+'" disabled="disabled"  class="form-control input-sm numDeplec"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumDeplec_'+index+'"  disabled="disabled"  value = "'+value+'" class="form-control input-sm numDeplec" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlDilcNuevo(index,value){
	var tamannio = 70;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumDilc_'+index+'" disabled="disabled"  class="form-control input-sm numDilc"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumDilc_'+index+'"  disabled="disabled"  value = "'+value+'" class="form-control input-sm numDilc" style="width: '+tamannio+'px !important;" />';
	}
}

function crearHtmlDbo5Nuevo(index,value){
	var tamannio = 70;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumDbo5_'+index+'" disabled="disabled"  class="form-control input-sm numDbo5"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumDbo5_'+index+'"  disabled="disabled"  value = "'+value+'" class="form-control input-sm numDbo5" style="width: '+tamannio+'px !important;" />';
	}
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

function verificarParametroAgregarDBO(){
	mostrarMensaje();
	if(indicadorEdicionFecha == 1){
		mostrarMensaje('Debe Guardar la Fecha Inicial y Vencimiento para Continuar.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}
	if(flagDBO == 0){
		var ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(ltaClonSubParametroTodo[0]);
				objetoClon.indice = countRow;
				objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,countRow,null);
				var idSubParametro = ltaClonSubParametroTodo[0].idSubParametro;
				objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametro),countRow,null);
				objetoClon.nombreAnalista = crearHtmlAnalistaNuevo(countRow,null);
				objetoClon.indicePadre = ConstanteServices.INDICADOR_PADRE;
				objetoClon.idGrupo = obtenerValorMaximoGrupo(ltaGrilla) + 1;				
				var nroFrascoSiguiente = "1";
				if(ltaGrilla != null && ltaGrilla.length > 0){
					nroFrascoSiguiente = eval(ltaGrilla[ltaGrilla.length - 1 ].nroFrasco +"+ 1");
				}		
				if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
					objetoClon.nroFrasco = crearHtmlFrascoNuevo(countRow,""+nroFrascoSiguiente);		
					objetoClon.numVolumen  = crearHtmlVolumenNuevo(countRow,null);
					objetoClon.numOdi = crearHtmlOdiNuevo(countRow,null);
					objetoClon.numOdf = crearHtmlOdfNuevo(countRow,null);
					objetoClon.numFactor = 0;
					objetoClon.numDeplec = crearHtmlDeplecNuevo(countRow,null);
					objetoClon.numDilc = crearHtmlDilcNuevo(countRow,null);
					objetoClon.numDbo5 = crearHtmlDbo5Nuevo(countRow,null);
					var objetoClonHijo = clonarObjeto(objetoClon);
					objetoClonHijo.indicePadre = ConstanteServices.INDICADOR_HIJO;
					objetoClonHijo.nroFrasco = crearHtmlFrascoNuevo(countRow+1,""+parseInt(nroFrascoSiguiente+1));
					objetoClonHijo.indice = countRow + 1;	
					objetoClonHijo.numVolumen  = crearHtmlVolumenNuevo(countRow+1,null);
					objetoClonHijo.numOdi = crearHtmlOdiNuevo(countRow+1,null);
					objetoClonHijo.numOdf = crearHtmlOdfNuevo(countRow+1,null);
					objetoClonHijo.numFactor = 0;
					objetoClonHijo.numDeplec = crearHtmlDeplecNuevo(countRow+1,null);
					objetoClonHijo.numDilc = crearHtmlDilcNuevo(countRow+1,null);
					objetoClonHijo.numDbo5 = crearHtmlDbo5Nuevo(countRow+1,null);
					
				}else{
					objetoClon.nroFrasco = nroFrascoSiguiente;
					var objetoClonHijo = clonarObjeto(objetoClon);
					objetoClonHijo.indicePadre = ConstanteServices.INDICADOR_HIJO;
					objetoClonHijo.nroFrasco = parseInt(nroFrascoSiguiente+1);
					objetoClonHijo.indice = countRow + 1;
					
				}
				guardarTablaAntesCambiosDBO();
				tblResultadoRegistroDBO.bootstrapTable('insertRow', {
		            index: countRow,
		            row: objetoClon
		        });
				tblResultadoRegistroDBO.bootstrapTable('insertRow', {
		            index: countRow+1,
		            row: objetoClonHijo
		        });
				var accionGuardar = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(1).find('#btnGuardarRegistroDBO');
		        var accionEditar = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(1).find('#btnEditarRegistroDBO');
		        var accionAnular = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(1).find('#btnAnularRegistroDBO');
		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
		        validacionIndicadorSemilla(countRow);
		        formatearInputDBO();
		        flagDBO = 1;
		        visibleAnalistaDBO();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputDBO();
}

function verificarParametroEditarNuevoDBO(index,row){
	mostrarMensaje();
	if(indicadorEdicionFecha == 1){
		mostrarMensaje('Debe Guardar la Fecha Inicial y Vencimiento para Continuar.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}
	if(flagDBO == 0){
		var ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(row);
				var accionGuardar = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(1).find('#btnGuardarRegistroDBO');
		        var accionEditar = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(1).find('#btnEditarRegistroDBO');
		        var accionAnular = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(1).find('#btnAnularRegistroDBO');
		        var accionRegresar = tblResultadoRegistroDBO.find('tr').eq(index + 1).find('td').eq(1).find('#btnRegresarRegistroDBO');
		        
		        var nroFrasco = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(2);
				var nombreAnalista = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(3);
				var descripcionPuntoMuestra = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(4);
		        var descripcionSubparametro = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(5);
				var numVolumen  = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(6);
				var numOdi = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(8);
				var numOdf = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(9);
				var numDeplec = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(10);
				var numDilc = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(11);
				var numDbo5 = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(12);
		        		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
		        if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		        	descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
		        	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(row.idSubParametro),index,row.idPuntoMuestra));	
			    }
		        //HIJA
		        var nroFrascoHija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(2);
		        var numOdiHija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(8);
				var numOdfHija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(9);
				var numDeplecHija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(10);
				var numDilcHija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(11);
				var numDbo5Hija = tblResultadoRegistroDBO.find('tr').eq(index+2).find('td').eq(12);
				var objetoClonHija = clonarObjeto(ltaGrilla[index+1]);
		        if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {	 
		        	nroFrasco.html(""+crearHtmlFrascoNuevo(index,""+row.nroFrasco));
		        	nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));		        	
		        	numVolumen.html(""+crearHtmlVolumenNuevo(index,""+row.numVolumen));
					numOdi.html(""+crearHtmlOdiNuevo(index,""+row.numOdi));
					numOdf.html(""+crearHtmlOdfNuevo(index,""+row.numOdf));
					numDeplec.html(""+crearHtmlDeplecNuevo(index,""+row.numDeplec));
					numDilc.html(""+crearHtmlDilcNuevo(index,""+row.numDilc));
					numDbo5.html(""+crearHtmlDbo5Nuevo(index,""+row.numDbo5));
					//extraemos las formulas 					
					buscarFormulaEdicion(row.idFormulaDeplec,1);
					buscarFormulaEdicion(row.idFormulaDilc,2);
					buscarFormulaEdicion(row.idFormulaDbo5,3);		
			        validacionIndicadorSemilla(index, row.idPuntoMuestra);			
					accionRegresar[0].style.display = 'inherit';
					
					//HIJA
					nroFrascoHija.html(""+crearHtmlFrascoNuevo(index+1,""+objetoClonHija.nroFrasco));
					numOdiHija.html(""+crearHtmlOdiNuevo(index+1,""+objetoClonHija.numOdi));
					numOdfHija.html(""+crearHtmlOdfNuevo(index+1,""+objetoClonHija.numOdf));
					numDeplecHija.html(""+crearHtmlDeplecNuevo(index+1,""+objetoClonHija.numDeplec));
					numDilcHija.html(""+crearHtmlDilcNuevo(index+1,""+objetoClonHija.numDilc));
					numDbo5Hija.html(""+crearHtmlDbo5Nuevo(index+1,""+objetoClonHija.numDbo5));
		        }
		        formatearInputDBO();
		        flagDBO = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputDBO();
}

function verificarAprobacionDBO(){
	if(flagDBO == 0){
		var tituloModal = 'Aprobaci??n Registro DBO';
		modal.confirmer('??Esta seguro de Aprobar Registro DBO ?', 'aprobarRegistroDBO()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroDBO(){
	$.ajax({
        url : "./aprobarRegistroDBO",
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
    		$("#btnAprobarRegistroDBO").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionDBO = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonDBO();
        	tblResultadoRegistroDBO.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroDBO(){
	if(flagDBO == 0){
		var tituloModal = 'Habilitaci??n Registro DBO';
		modal.confirmer('??Esta seguro de Habilitar Registro DBO ?', 'habilitarRegistroDBO()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroDBO(){
	$.ajax({
        url : "./aprobarRegistroDBO",
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
    		$("#btnAprobarRegistroDBO").hide();
        	mostrarMensaje('Se Habilit?? Registo DBO Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionDBO = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonDBO();
        	buscarDatosRegistroDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function obtenerMensajeValoresDBO_Directo(idRegistroLaboratorio){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR){
		$.ajax({
	        url : "./obtenerMensajeValoresDBO_Directo",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idRegistro : (!IsEmpty(idRegistroLaboratorio) ? idRegistroLaboratorio : null)   	
	        }
	    }).done(function(respuestaBean) {
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK && parseInt(respuestaBean.parametros.flagMuestraMensaje) == ConstanteServices.INDICADOR_MUESTRA_MENSAJE_ES){	 
	    		var lineaEntrada = respuestaBean.parametros.mensajeEntrada;
	    		var lineaSalida = respuestaBean.parametros.mensajeSalida;
	    		var tituloModal = 'Alerta Valores DBO - Directo';
	   			modal.open(lineaEntrada + '<br>' + lineaSalida, '', tituloModal);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });
	}
}

/*funcion grabar observacion de parametro DBO*/
function guardarObservacionDBO(){
	$.ajax({
        url : "./guardarObservacionDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroDBO : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonDBO();
        	observacionDBO = respuestaBean.parametros.observacionDBO;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionDBO(){
	var tituloModal = 'Observacion Registro DBO';
	modal.confirmer('??Esta seguro de Grabar la Observaci??n del Registro?', 'guardarObservacionDBO()', 'regresarObservacionDBO()', tituloModal);
}

function regresarObservacionDBO(){
	txtObservacionRegistro.val(""+observacionDBO);
}
/**/

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divnroFrasco'){
        	var newId = "divnumVolumen_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divnumVolumen'){
        	var newId = "divnumValorSemilla_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }else{
	        	var newId = "divnumOdi_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }
	        }
        }
        if(fieldId == 'divnumValorSemilla'){
        	var newId = "divnumOdi_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divnumOdi'){
        	var newId = "divnumOdf_"+rowNo;
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

        if(fieldId == 'divnumOdf'){
        	var newId = "divnumOdi_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divnumOdi'){
        	var newId = "divnumValorSemilla_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }else{
	        	var newId = "divnumVolumen_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }
	        }
        }
        if(fieldId == 'divnumValorSemilla'){
        	var newId = "divnumVolumen_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divnumVolumen'){
        	var newId = "divnroFrasco_"+rowNo;
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