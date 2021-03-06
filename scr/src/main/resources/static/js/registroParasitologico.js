$(document).ready(function() {
			inicializarVariablesParasitologico();
			cargarComponentesParasitologico();
});
var accion = false;
var flagParasitologico = 0;
var flagVerAnalistaParasitologico = 0;
var nombreAnalistaParasitologico = "nombreAnalistaParasitologico";
var flagVerFormulasParasitologico = 0;
var ltaMicroorganismoSubPara = null;

function inicializarVariablesParasitologico() {
	console.log("PARASITO");
	var tblResultadoRegistroParasitologico = null;
	var tblVariables1Parasitologico = null;
	var tblVariables2Parasitologico = null;
	var tblVariables3Parasitologico = null;
	var tblVariables4Parasitologico = null;
	var lblFormulaParasitologico1 = null;
	var txtFormulaParasitologico1 = null;
	var btnGuardarFormulaParasitologico1 = null;
	var btnEditarFormulaParasitologico1 = null;
	/*inicio observacion para parametro Parasitologico*/
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 
	/**/
}

function cargarComponentesParasitologico() {
	accordion();
	btnGuardarFormulaParasitologico1 = $('#btnGuardarFormulaParasitologico1');
	btnEditarFormulaParasitologico1 = $('#btnEditarFormulaParasitologico1');
	txtFormulaParasitologico1 = $('#txtFormulaParasitologico1');
	lblFormulaParasitologico1 = $('#lblFormulaParasitologico1');
	txtDescripcionFormulaParasitologico = $('#txtDescripcionFormulaParasitologico');
	tblVariables1Parasitologico = $('#tblVariables1Parasitologico');
	tblVariables2Parasitologico = $('#tblVariables2Parasitologico');
	tblVariables3Parasitologico = $('#tblVariables3Parasitologico');
	tblVariables4Parasitologico = $('#tblVariables4Parasitologico');
	/*inicio observacion para parametro Parasitologico*/
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	/**/
	
	if(ltaRegParasitologico.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else{
		$("#fechaMonitoreo").text(ltaRegParasitologico[0].fechaMonitoreoString);
	}
	
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Parasitol??gico - N?? Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroParasitologico").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;	
	}
	
	tblResultadoRegistroParasitologico = $("#tblResultadoRegistroParasitologico");
	
	cargarGrillaResultadoParasitologico(ltaRegParasitologico);
		
	$('#btnAdjuntarRegParasitologico').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionParasitologico);
    });
	
	$('#btnNuevoRegParasitologico').click(function() {
		if(estadoAprobacionParasitologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarParasitologico();
		}
    });
	
	$('#btnGuardarTodoRegistroParasitologico').click(function() {
		mostrarMensaje();
		if(flagParasitologico == 0){
			if(estadoAprobacionParasitologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
				verificarDataGrabarParasitologico();
			}
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}
    });
		
	$('#btnBorrarMultipleRegistroParasitologico').click(function() {
		mostrarMensaje();
		if(flagParasitologico == 0){
			verificarEliminarMultipleParasitologico();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	btnEditarFormulaParasitologico1.click(function() {
		if(estadoAprobacionParasitologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			txtFormulaParasitologico1.attr("disabled",false);
			btnEditarFormulaParasitologico1.attr('style', 'display : none');
			btnGuardarFormulaParasitologico1.attr('style', 'display : inherit');
		}
    });
	
	btnGuardarFormulaParasitologico1.click(function() {
		if(estadoAprobacionParasitologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			mostrarMensaje();
			var item1 = JSON.parse(txtFormulaParasitologico1.attr("valor"));
			descripcionFormula = txtFormulaParasitologico1.val();
			validacion = validarFormulaIngreso(ltaVariable, descripcionFormula);
			if(!IsEmpty(descripcionFormula)){
				if(!IsEmpty(validacion)){
					mostrarMensaje('El campo '+item1.descripcionFormula+' No Contiene variables o expresi??n v??lida ', ConstanteServices.IMAGEN_DANGER);
				}else {
					item1.tipoOrden = ConstanteServices.TIPO_FORMULA_SECUNDARIO;
					item1.combinacionFormula = descripcionFormula;
					console.log(item1);
					buscarRegistroFormula(item1, descripcionFormula);
				}
			}else{
				mostrarMensaje('El campo '+item1.descripcionFormula+' es obligatorio', ConstanteServices.IMAGEN_DANGER);
			}
		}		
    });
	
	$('#btnAprobarRegistroParasitologico').click(function() {
		mostrarMensaje();
		if(flagParasitologico == 0){
			verificarAprobacionParasitologico();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}
    });
	
	$('#btnHabilitarRegistroParasitologico').click(function() {
		verificarHabilitarRegistroParasitologico();
    });
	
	$('#btnMostrarFRegistroParasitologico').click(function() {
		if(flagVerFormulasParasitologico == 0){
			flagVerFormulasParasitologico = 1;
			$("#btnMostrarFRegistroParasitologico").text("Ocultar Formulas");
			$("#divVariablesParasitologico").show(1000);
			$("#divFormulasParasitologico").show(1000);
		}else{
			flagVerFormulasParasitologico = 0;
			$("#btnMostrarFRegistroParasitologico").text("Ver Formulas");
			$("#divVariablesParasitologico").hide(1000);
			$("#divFormulasParasitologico").hide(1000);
		}	
    });
	
	$('#btnVerAnalistaRegParasitologico').click(function() {
		if(flagVerAnalistaParasitologico == 0){
			flagVerAnalistaParasitologico = 1;
			$("#btnVerAnalistaRegParasitologico").text("Ocultar Analista");
		}else{
			flagVerAnalistaParasitologico = 0;
			$("#btnVerAnalistaRegParasitologico").text("Ver Analista");
		}
		visibleAnalistaParasitologico();				
    });
	
	/*inicio observacion para parametro Parasitologico*/
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionParasitologico();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionParasitologico();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	/**/
	
	formatearInputParasitologico();
	cargarGrillaVariablesParasitologico();
	buscarFormulasPrincipalesParasitologico();
	buscarVariablesParasitologico();
	validacionBotonParasitologico();
	validarPerfilesParasitologico();
}

function visibleAnalistaParasitologico(){
	var a  = document.getElementsByClassName(nombreAnalistaParasitologico);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaParasitologico == 1){
			 a[i].className = ""+nombreAnalistaParasitologico;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaParasitologico;	
		}
	}
}

function validarPerfilesParasitologico(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegParasitologico").show();
		$("#btnMostrarFRegistroParasitologico").show();
		$("#btnHabilitarRegistroParasitologico").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegParasitologico").hide();
		$("#btnAprobarRegistroParasitologico").hide();
		$("#btnHabilitarRegistroParasitologico").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroParasitologico").hide();
		$("#btnAprobarRegistroParasitologico").hide();
		$("#btnVerAnalistaRegParasitologico").hide();
		$("#btnNuevoRegParasitologico").hide();
		$("#btnAdjuntarRegParasitologico").hide();
		$("#btnBorrarMultipleRegistroParasitologico").hide();
	}else{		
		tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroParasitologico").hide();
		$("#btnAprobarRegistroParasitologico").hide();
		$("#btnHabilitarRegistroParasitologico").hide();
		$("#btnVerAnalistaRegParasitologico").hide();
		$("#btnNuevoRegParasitologico").hide();
	}
}

function validacionBotonParasitologico(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionParasitologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroParasitologico").show();
		}else{
			$("#btnAprobarRegistroParasitologico").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionParasitologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroParasitologico").show();
		}else{
			$("#btnHabilitarRegistroParasitologico").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroParasitologico").hide();
		$("#btnHabilitarRegistroParasitologico").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionParasitologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroParasitologico").hide();
	}
}

function formatearInputParasitologico(){
	ponerClassConjunto("inputDecimal","cantidad",0,6,2);
	ponerClassConjunto("inputDecimal","volumen",0,6,2);
	ponerClassConjunto("inputDecimal","factor",0,4,2);
	ponerClassConjuntoFechas("fechaMonitoreo");
	ponerClassConjuntoFechas("fechaRegDato");
}


function cargarGrillaResultadoParasitologico(lista){
	tblResultadoRegistroParasitologico.bootstrapTable("destroy")
	var claseValidador = "";
	var claseVerAnalista = "nombreAnalistaParasitologico";
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	}
	if(flagVerAnalistaParasitologico != 1){
		claseVerAnalista = "hidden nombreAnalistaParasitologico";
	}
	tblResultadoRegistroParasitologico.bootstrapTable({
		data : lista,
		pagination : false,
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
			formatter: 'crearAccionesRegistroParasitologico',
			class: 'controls',
			events : operateEventsParasitologico,
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
			class : claseVerAnalista,
			sortable : false ,
			formatter : 'crearHtmlAnalista'
		},
		{	
			field : 'descripcionSubparametro',
			title : 'Sub<br>Par??metro.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false
		},{	
			field : 'descripcionPuntoMuestra',
			title : 'Pto.<br>Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false
		},{	
			field : 'descripcionMicroorganismo',
			title : 'Microorganismo',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlMicroorganismo'
		},{	
			field : 'numVolumen',
			title : 'Vol L',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : crearHtmlVolumen
		},{	
			field : 'numCantidad',
			title : '# Organismos',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : crearHtmlCantidad
		},{	
			field : 'numResultado',
			title : 'Organismos/L',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter : 'crearHtmlResultado',
			cellStyle : verificarEstiloColor
		},{	
			field : 'descripcionObservacion',
			title : 'Observaci??n',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlObservacion'
		}]
	});
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearAccionesRegistroParasitologico(value, row, index) {	
	    return [
			'<button id="btnGuardarRegistroParasitologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroParasitologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroParasitologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroParasitologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
				'<i class="fa fa-reply"></i>',
			'</button>'
        ].join('');			    	
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

function crearHtmlMicroorganismo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var ltaMicroorganismoSubPara = obtenerListaMicroorganismo(row.idSubParametro);
		var htmlCombo = '<select class="form-control input-sm" id="cboMicroorganismo_'+index+'" >';
		for (var i = 0; i < ltaMicroorganismoSubPara.length; i++) {
		   if(ltaMicroorganismoSubPara[i].idDetalleGeneral == row.idMicroorganismo){
			   htmlCombo += '<option selected="selected" value = "'+ltaMicroorganismoSubPara[i].idDetalleGeneral+'">'+ltaMicroorganismoSubPara[i].descripcion+'</option>'
		   }else{
			   htmlCombo += '<option value = "'+ltaMicroorganismoSubPara[i].idDetalleGeneral+'">'+ltaMicroorganismoSubPara[i].descripcion+'</option>'	
		   }
		}
		htmlCombo 	  +='</select>'			
		return [htmlCombo].join('');
	}else{
		return [''+row.descripcionMicroorganismo].join('');
	}
}

function crearHtmlAnalista(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
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
	}else{
		return [''+row.nombreAnalista].join('');
	}
}

function crearHtmlFechaMonitoreo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = '<div class="input-group date fechaMonitoreo" id="datetimepickerMonitoreo_'+index+'" style="width: 130px !important;">';
		htmlimput += '		<input type="text" id="dtpMonitoreo_'+index+'" value='+row.fechaMonitoreoString+' readonly="readonly" class="form-control "/>';
		htmlimput += '		<span class="input-group-addon">';
		htmlimput += '			<span class="glyphicon glyphicon-calendar"></span>';
		htmlimput += '		</span>';
		htmlimput += '	</div>';
		return [htmlimput].join('');
	}else{
		return [''+row.fechaMonitoreoString].join('');
	}
}

function crearHtmlFechaRegDato(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = '<div class="input-group date fechaRegDato" id="datetimepickerRegDato_'+index+'" style="width: 130px !important;">';
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

function crearHtmlCantidad(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCantidad)){
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" value="'+row.numCantidad+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numCantidad].join('');
	}
}

function crearHtmlVolumen(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numVolumen)){
			htmlimput = '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm volumen" style="width: 80px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divVolumen_'+index+'" value="'+row.numVolumen+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm volumen" style="width: 80px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numVolumen].join('');
	}
}

function crearHtmlObservacion(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.descripcionObservacion)){
			htmlimput = '<input type="text"  id="divDescripcionObservacion_'+index+'" class="form-control input-sm descripcionObservacion" style="width: 120px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divDescripcionObservacion_'+index+'" value="'+row.descripcionObservacion+'" class="form-control input-sm descripcionObservacion" style="width: 120px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.descripcionObservacion].join('');
	}
}

function crearHtmlResultado(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numResultado)){
			htmlimput = '<input type="text" id="divResultado_'+index+'" readonly = "readonly" class="form-control input-sm resultado" style="width: 80px !important;" />';
		}else{
			htmlimput = '<input type="text" id="divResultado_'+index+'" value="'+row.numResultado+'" readonly = "readonly" class="form-control input-sm resultado" style="width: 80px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numResultado != null){
			return [''+row.numResultado].join('');
		}else{
			return [''].join('');
		}
	}
}

function calcularValoresParasitologico(index){
	var numCantidad = $("#divCantidad_"+index).val();
	var numVolumen = $("#divVolumen_"+index).val();	
	
	//para SST
	if(IsEmpty(numCantidad) || IsEmpty(numVolumen)){
		$("#divResultado_"+index).val("");
	}else{
		
		var listaVariablesInternaParasitologico = [];
		var objcantidad = {
		    descripcionLetra : 'a',
		    valor : ''+numCantidad
		};
		var objvolumen = {
		    descripcionLetra : 'b',
		    valor : ''+numVolumen
		};
		
		listaVariablesInternaParasitologico.push(objcantidad);
		listaVariablesInternaParasitologico.push(objvolumen);
		
		var formulaParasitologico = txtFormulaParasitologico1.val();		
		var respuestaParasitologico = ejecutarFormula(listaVariablesInternaParasitologico,formulaParasitologico);
		console.log(listaVariablesInternaParasitologico);
		console.log(respuestaParasitologico);
		if(respuestaParasitologico != ConstanteServices.VARIABLES_INEXISTENTES && respuestaParasitologico != ConstanteServices.EXPRESION_INVALIDA && respuestaParasitologico != null){
			var numResultadoFinal = cortarDecimalRedondear(respuestaParasitologico,4);
			$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
		}else{
			$("#divResultado_"+index).val(""+respuestaParasitologico);
		}
	}
	completarLtaRegistroParasitologico();
}

function eliminarSubParametroNuevoParasitologico(index){
	guardarTablaAntesCambiosParasitologico();
	tblResultadoRegistroParasitologico.bootstrapTable('remove', {field: 'indice', values: [parseInt(index)]});
	formatearInputParasitologico();
	flagParasitologico = 0;
	mostrarMensaje();
	visibleAnalistaParasitologico();
}

function verificarParametroAgregarParasitologico(){
	mostrarMensaje();
	if(flagParasitologico == 0){
		var ltaGrilla = tblResultadoRegistroParasitologico.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
			var objetoClon = clonarObjeto(ltaClonSubParametroTodo[0]);
			objetoClon.indice = countRow;
			objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,countRow,null);
			objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(ltaClonSubParametroTodo[0].idSubParametro),countRow,null);
			objetoClon.nombreAnalista = crearHtmlAnalistaNuevo(countRow,null);
			if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				objetoClon.numCantidad = crearHtmlCantidadNuevo(countRow,null);
				objetoClon.numVolumen = crearHtmlVolumenNuevo(countRow,null);
				objetoClon.descripcionMicroorganismo = crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(ltaClonSubParametroTodo[0].idSubParametro),countRow,null);
				objetoClon.numResultado = crearHtmlResultadoNuevo(countRow, null);
				objetoClon.descripcionObservacion = crearHtmlObservacionNuevo(countRow, null);
			}
			guardarTablaAntesCambiosParasitologico();
			tblResultadoRegistroParasitologico.bootstrapTable('insertRow', {
				index: countRow,
				row: objetoClon
			});
			var accionGuardar = tblResultadoRegistroParasitologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnGuardarRegistroParasitologico');
			var accionEditar = tblResultadoRegistroParasitologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnEditarRegistroParasitologico');
			var accionAnular = tblResultadoRegistroParasitologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnAnularRegistroParasitologico');
		        
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
			validacionResultado(countRow);
			formatearInputParasitologico();
			flagParasitologico = 1;
			visibleAnalistaParasitologico();
			actualizarIndicesParasitologico();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputParasitologico();
}

function crearHtmlComboPuntoMuestraNuevo(ltaPtoMuestraSP,index,value){
	var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" >';
	for (var i = 0; i < ltaPtoMuestraSP.length; i++) {
		if(ltaPtoMuestraSP[i].idPuntoMuestra == value){
			htmlCombo += '<option selected="selected" value = "'+ltaPtoMuestraSP[i].idPuntoMuestra+'">'+ltaPtoMuestraSP[i].descripcionCorta+'</option>';
		}else{
			htmlCombo += '<option value = "'+ltaPtoMuestraSP[i].idPuntoMuestra+'">'+ltaPtoMuestraSP[i].descripcionCorta+'</option>';
		}	   
	}
	htmlCombo 	  +='</select>';		
	return htmlCombo;
}

function crearHtmlComboMicroorganismoNuevo(ltaMicroorganismoSP,index,value){
	var htmlCombo = '<select class="form-control input-sm" id="cboMicroorganismo_'+index+'" >';
	for (var i = 0; i < ltaMicroorganismoSP.length; i++) {
		if(ltaMicroorganismoSP[i].idDetalleGeneral == value){
			htmlCombo += '<option selected="selected" value = "'+ltaMicroorganismoSP[i].idDetalleGeneral+'">'+ltaMicroorganismoSP[i].descripcion+'</option>';
		}else{
			htmlCombo += '<option value = "'+ltaMicroorganismoSP[i].idDetalleGeneral+'">'+ltaMicroorganismoSP[i].descripcion+'</option>';
		}	   
	}
	htmlCombo 	  +='</select>';		
	return htmlCombo;
}

function actualizarListaMicroorganismo(index, value){
	var idSubParametro = $('#divSubParametro_'+index).val();
	var descripcionMicroorganismo = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(6);
	descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(index,value,idSubParametro));
}

function crearHtmlComboSubParametroNuevo(ltaSubPara,index,value){
	var htmlComboSubParametro = '<select class="form-control input-sm subParametro" id="divSubParametro_'+index+'" onchange="crearMicroorganismo('+index+'); crearPuntoMuestra('+index+')" >';
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

function crearHtmlCantidadNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}else{
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" value = "'+value+'" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}
}

function crearHtmlVolumenNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}else{
		return '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresParasitologico('+index+')" value = "'+value+'" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}
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
	var htmlimput = '<div class="input-group date fechaMonitoreo" id="datetimepickerMonitoreo_'+index+'" style="width: 130px !important;">';
	if(IsEmpty(value)){
		htmlimput += '		<input type="text" id="dtpMonitoreo_'+index+'" value="'+fechaActual+'" readonly="readonly" class="form-control "/>';
	}else{
		htmlimput += '		<input type="text" id="dtpMonitoreo_'+index+'" value="'+value+'" readonly="readonly" class="form-control "/>';
	}	
	htmlimput += '		<span class="input-group-addon">';
	htmlimput += '			<span class="glyphicon glyphicon-calendar"></span>';
	htmlimput += '		</span>';
	htmlimput += '	</div>';
	return htmlimput;
}

function crearHtmlFechaRegDatoNuevo(index,value){
	var htmlimput = '<div class="input-group date fechaRegDato" id="datetimepickerRegDato_'+index+'" style="width: 130px !important;">';
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

function crearHtmlObservacionNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divDescripcionObservacion_'+index+'" class="form-control input-sm observacion" style="width: 120px !important;" />';
	}else{
		return '<input type="text"  id="divDescripcionObservacion_'+index+'" value = "'+value+'" class="form-control input-sm observacion" style="width: 120px !important;" />';
	}
}

function crearHtmlResultadoNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text" id="divResultado_'+index+'" readonly = "readonly"  class="form-control input-sm resultado" style="width: 80px !important;" />';
	}else{
		return '<input type="text" id="divResultado_'+index+'" readonly = "readonly" value = "'+value+'"  class="form-control input-sm resultado" style="width: 80px !important;" />';
	}	
}

function validacionResultado(index){
}

function validacionResultadoEditar(index){
}

function verificarDataGrabarParasitologico(){
	if(flagParasitologico == 0){	
		var lta = completarLtaRegistroParasitologico();
		mostrarMensaje();
		if(validarDataTabla(lta)){	
			var tituloModal = 'Registro Parasitologico';
   			modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroParasitologico()', '', tituloModal);
		}	
	}
}

function validarDataTabla(listaFinal){
	var indicador = true;
	for(var i = 0 ; i < listaFinal.length ; i++){
		if(IsEmpty(listaFinal[i].idAnalista)){
			mostrarMensaje('Debe Seleccionar una Analista en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(listaFinal[i].fechaMonitoreoString)){
			mostrarMensaje('Debe Ingresar una Fecha Monitoreo en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].fechaRegDatoString)){
			mostrarMensaje('Debe Ingresar una Fecha Reg Dato en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idSubParametro)){
			mostrarMensaje('Debe Seleccionar un SubParametro en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idMicroorganismo)){
			mostrarMensaje('Debe Seleccionar un Microorganismo en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numCantidad) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar una Cantidad en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numVolumen) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Volumen en el item N?? '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return indicador
}

function completarLtaRegistroParasitologico(){
	var listaFinal = tblResultadoRegistroParasitologico.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
		var idMicroorganismo = $("#cboMicroorganismo_"+i).val();
		if(idMicroorganismo != null && idMicroorganismo != undefined){
			listaFinal[i].idMicroorganismo  = parseInt(idMicroorganismo);
		}else{
			listaFinal[i].idMicroorganismo = null;
		}
		var numValor = $("#divValor_"+i).val();
		if(numValor != null && numValor != undefined){
			listaFinal[i].numValor  = numValor;
		}
		var fechaMonitoreoString = $("#dtpMonitoreo_"+i).val();
		if(fechaMonitoreoString != null && fechaMonitoreoString != undefined){
			listaFinal[i].fechaMonitoreoString  = fechaMonitoreoString;
		}
		
		var fechaRegDatoString = $("#dtpRegDato_"+i).val();
		if(fechaRegDatoString != null && fechaRegDatoString != undefined){
			listaFinal[i].fechaRegDatoString  = fechaRegDatoString;
		}
		
		var idSubPM = $("#divSubParametro_"+i).val();
		if(idSubPM != null && idSubPM != undefined){
			listaFinal[i].idSubParametro  = parseInt(idSubPM);
		}
				
		var idAnalistaInterno = $("#cboAnalista_"+i).val();
		if(idAnalistaInterno != null && idAnalistaInterno != undefined){
			listaFinal[i].idAnalista  = idAnalistaInterno;
		}
		
		var numCantidad = $("#divCantidad_"+i).val();
		if(numCantidad != null && numCantidad != undefined){
			if(!IsEmpty(numCantidad)){
				listaFinal[i].numCantidad  = numCantidad;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numCantidad  = ConstanteServices.VALOR_DEFECTO;
			}		
		}
		if(listaFinal[i].numCantidad == Infinity || listaFinal[i].numCantidad == -Infinity || isNaN(listaFinal[i].numCantidad)){
			listaFinal[i].numCantidad = ConstanteServices.VALOR_DEFECTO;
		}
		var numVolumen = $("#divVolumen_"+i).val();
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
		
		var numResultado = $("#divResultado_"+i).val();
		if(numResultado != null && numResultado != undefined){
			if(!IsEmpty(numResultado)){
				listaFinal[i].numResultado = numResultado;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numResultado = ConstanteServices.VALOR_DEFECTO;
			}
		}
		
		var descripcionObservacion = $("#divDescripcionObservacion_"+i).val();
		if(descripcionObservacion != null && descripcionObservacion != undefined){
			if(!IsEmpty(descripcionObservacion)){
				listaFinal[i].descripcionObservacion = descripcionObservacion;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].descripcionObservacion = " ";
			}
		}
		
		var itemFormulaResultado = JSON.parse(txtFormulaParasitologico1.attr("valor"));
		if(itemFormulaResultado != null && itemFormulaResultado != undefined ){
			listaFinal[i].idFormula  = itemFormulaResultado.idFormulaxParametro;
		}
	}
	return listaFinal;
}

function calcularValoresParasitologicoAll(){
	var listaFinal = tblResultadoRegistroParasitologico.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		calcularValoresParasitologico(i);
	}
}

function grabarDatosRegistroParasitologico(){
	var lta = completarLtaRegistroParasitologico();
	$.ajax({
        url : "./grabarDatosRegistroParasitologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroParasitologico : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroParasitologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroParasitologico").hide();
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);
        	$("#lblTitulo").text("Registro Parasitologico - N?? Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionParasitologico);
        	observacionParasitologico = respuestaBean.parametros.observacionParasitologico;
        	validacionBotonParasitologico();
        	buscarDatosRegistroParasitologico();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroParasitologico(){
	$.ajax({
        url : "./buscarDatosRegistroParasitologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLabBusqueda : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadoRegistroParasitologico.bootstrapTable('load', JSON.parse(ListaResultado));
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		visibleAnalistaParasitologico();
        	}else{
            	tblResultadoRegistroParasitologico.bootstrapTable('load', []);
            	visibleAnalistaParasitologico();
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputParasitologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarNuevoRegistroEditar(index){
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
	
	var numCantidad = $("#divCantidad_"+index).val();
	if(IsEmpty(numCantidad)){
		mostrarMensaje('Debe Ingresar una Cantidad', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numVolumen = $("#divVolumen_"+index).val();
	if(IsEmpty(numVolumen)){
		mostrarMensaje('Debe Ingresar un Volumen', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var idMicroorganismoNew = $("#cboMicroorganismo_"+index).val();
	if(IsEmpty(idMicroorganismoNew)){
		mostrarMensaje('Debe Seleccionar un Microorganismo', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var idSubParametroNew = $("#divSubParametro_"+index).val();
	if(IsEmpty(idSubParametroNew)){
		mostrarMensaje('Debe Seleccionar un SubParametro', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	return indicador;
}

function grabarItemRegistroParasitologico(item){
	$.ajax({
        url : "./grabarItemRegistroParasitologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	registroParasitologicoBean : JSON.stringify(item),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		flagParasitologico = 0;
    		buscarFormulasPrincipalesParasitologico();
    		buscarDatosRegistroParasitologico();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroParasitologico(idRegParasitologico){
	$.ajax({
        url : "./eliminarItemRegistroParasitologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroParasitologico : idRegParasitologico
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagParasitologico = 0;
        	buscarDatosRegistroParasitologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaParasitologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarParametroEditarNuevoParasitologico(index,row){
	mostrarMensaje();
	if(flagParasitologico == 0){
		var ltaGrilla = tblResultadoRegistroParasitologico.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
			var objetoClon = clonarObjeto(row);
			var accionGuardar = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(1).find('#btnGuardarRegistroParasitologico');
			var accionEditar = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(1).find('#btnEditarRegistroParasitologico');
			var accionAnular = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(1).find('#btnAnularRegistroParasitologico');
			var accionRegresar = tblResultadoRegistroParasitologico.find('tr').eq(index + 1).find('td').eq(1).find('#btnRegresarRegistroParasitologico');
			
			var nombreAnalista = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(3);
			var descripcionSubparametro = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(4);
			var descripcionPuntoMuestra = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(5);
			var descripcionMicroorganismo = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(6);
			var numVolumen = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(7);
			var numCantidad = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(8);
			var numResultado = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(9);
			var descripcionObservacion = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(10);
			
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
										
			descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
			descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(row.idSubParametro),index,row.idPuntoMuestra));
			if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
				nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));
				descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(row.idSubParametro),index,row.idMicroorganismo));
				numVolumen.html(""+crearHtmlVolumenNuevo(index, ""+row.numVolumen));
				numCantidad.html(""+crearHtmlCantidadNuevo(index, ""+row.numCantidad));
				numResultado.html(""+crearHtmlResultadoNuevo(index, row.numResultado));
				descripcionObservacion.html(""+crearHtmlObservacionNuevo(index, row.descripcionObservacion));
//				extraemos las formulas 					
				buscarFormulaEdicionParasitologico(row.idFormula);
				accionRegresar[0].style.display = 'inherit';
		    }
			validacionResultadoEditar(index);
			calcularValoresParasitologico(index);
			formatearInputParasitologico();
			flagParasitologico = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputParasitologico();
}

function verificarAprobacionParasitologico(){
	if(flagParasitologico == 0){
		var tituloModal = 'Aprobaci??n Registro Parasitologico';
		modal.confirmer('??Esta seguro de Aprobar Registro Parasitologico ?', 'aprobarRegistroParasitologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroParasitologico(){
	$.ajax({
        url : "./aprobarRegistroParasitologico",
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
    		$("#btnAprobarRegistroParasitologico").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionParasitologico = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonParasitologico();
        	tblResultadoRegistroParasitologico.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroParasitologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroParasitologico(){
	if(flagParasitologico == 0){
		var tituloModal = 'Habilitaci??n Registro Parasitologico';
		modal.confirmer('??Esta seguro de Habilitar Registro Parasitologico ?', 'habilitarRegistroParasitologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroParasitologico(){
	$.ajax({
        url : "./aprobarRegistroParasitologico",
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
    		$("#btnAprobarRegistroParasitologico").hide();
        	mostrarMensaje('Se Habilit?? Registo Parasitologico Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionParasitologico = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonParasitologico();
        	tblResultadoRegistroParasitologico.bootstrapTable('showColumn', 'accion');
        	buscarDatosRegistroParasitologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function guardarTablaAntesCambiosParasitologico(){
	var lista = completarLtaRegistroParasitologico();
	tblResultadoRegistroParasitologico.bootstrapTable('load', lista);
	visibleAnalistaParasitologico();
}

function verificarEliminarMultipleParasitologico(){
	if(flagParasitologico == 0){
		var seleccionados = tblResultadoRegistroParasitologico.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminaci??n M??ltiple Parasitologico';
   			modal.confirmer('??Esta seguro de Proceder con la '+tituloModal+' ?', 'eliminarMultipleRegistroParasitologico()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function eliminarMultipleRegistroParasitologico(){
	var seleccionados = tblResultadoRegistroParasitologico.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			eliminarSubParametroNuevoParasitologico(seleccionados[i].indice);
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		eliminarVariosItemRegistroParasitologico(JSON.stringify(seleccionados));
	}
}

function actualizarIndicesParasitologico(){
	var data = tblResultadoRegistroParasitologico.bootstrapTable("getData");
	for(var i=0; i<data.length; i++){
		data[i].indice = i+1;
	}
}

function eliminarVariosItemRegistroParasitologico(ltaRegistroParasitologico){
	$.ajax({
        url : "./eliminarVariosItemRegistroParasitologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	ltaRegistroParasitologico : ltaRegistroParasitologico
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagParasitologico = 0;
        	buscarDatosRegistroParasitologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaParasitologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarListaMicro(idSubParametro){
	$.ajax({
        url : "./buscarListaMicroPara",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idSubParametro : idSubParametro
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {
        		ltaMicroorganismoSubPara = JSON.parse(ListaResultado);
        	} else {
		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	ltaMicroorganismoSubPara = [];
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaVariablesParasitologico(){
	tblVariables1Parasitologico.bootstrapTable({
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
	
	tblVariables2Parasitologico.bootstrapTable({
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
	
	tblVariables3Parasitologico.bootstrapTable({
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
	
	tblVariables4Parasitologico.bootstrapTable({
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

function buscarFormulasPrincipalesParasitologico() {
	$.ajax({
        url : contexto+"general/buscarFormulaPrincipales",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: (!IsEmpty(idParametro) ? idParametro : null)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		if(lista != null && lista.length > 0){
        			if(lista.length > 0){
            			lblFormulaParasitologico1.text(""+lista[0].descripcionFormula);
            			txtFormulaParasitologico1.val(""+lista[0].combinacionFormula);
            			txtFormulaParasitologico1.attr("valor",JSON.stringify(lista[0]));
            			txtFormulaParasitologico1.attr("disabled",true);
					}
        		}
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

function buscarVariablesParasitologico() {
	$.ajax({
        url : contexto+"general/buscarVariableActivo",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idDetalleGeneral: (!IsEmpty(idParametro) ? idParametro : null)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = JSON.parse(respuestaBean.parametros.ListaResultado);
        	ltaVariable = ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {
        		var indice = Math.ceil(ListaResultado.length/4);
        		var listaAux = [];
        		for(i = 0; i < indice; i++){
        			listaAux.push(ListaResultado[i]);
        		}
        		tblVariables1Parasitologico.bootstrapTable('load', listaAux);
        		
        		var listaAux = [];
        		for(i = indice; i < (indice*2); i++){
        			listaAux.push(ListaResultado[i]);
        		}
        		tblVariables2Parasitologico.bootstrapTable('load', listaAux);
        		var listaAux = [];
        		for(i = (indice*2); i < (indice*3); i++){
        			listaAux.push(ListaResultado[i]);
        		}
        		tblVariables3Parasitologico.bootstrapTable('load', listaAux);
        		var listaAux = [];
        		for(i = (indice*3); i < ListaResultado.length; i++){
        			listaAux.push(ListaResultado[i]);
        		}
        		tblVariables4Parasitologico.bootstrapTable('load', listaAux);
        	} else {
        		tblVariables1Parasitologico.bootstrapTable('load', []);
        		tblVariables2Parasitologico.bootstrapTable('load', []);
        		tblVariables3Parasitologico.bootstrapTable('load', []);
        		tblVariables4Parasitologico.bootstrapTable('load', []);
		        mostrarMensaje('No se encontraron Variables registradas en el sistema.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    }); 	
}

function buscarRegistroFormula(value, descripcionFormula) {
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: (!IsEmpty(value.idParametro) ? value.idParametro : null),
        	idFormula : null,
        	combinacion : (!IsEmpty(descripcionFormula) ? descripcionFormula : null)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	var indicadorGrabar = false;
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado;
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		if(lista != null && lista.length > 0){
        			if(value.idFormulaxParametro != lista[0].idFormulaxParametro){
        				//asigna el la nueva formula
        				console.log(lista);
        				$("#txtFormulaParasitologico1").attr("valor",JSON.stringify(lista[0]));
        				$("#txtFormulaParasitologico1").attr("disabled",true);
        				$("#btnGuardarFormulaParasitologico1").attr('style', 'display : none');
        				$("#btnEditarFormulaParasitologico1").attr('style', 'display : inherit');
        				if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
        					calcularValoresParasitologicoAll();
        				}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndexParasitologico("divResultado_");
        					if(rpta != null){
        						calcularValoresParasitologico(parseInt(rpta));
        					}
        				}
        			}else{
        				console.log(lista);
        				$("#txtFormulaParasitologico1").attr("disabled",true);
        				$("#btnGuardarFormulaParasitologico1").attr('style', 'display : none');
        				$("#btnEditarFormulaParasitologico1").attr('style', 'display : inherit');
        				if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndexParasitologico("divResultado_");
        					if(rpta != null){
        						calcularValoresParasitologico(parseInt(rpta));
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
     		modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'guardarItemRegistroFormulaParasitologico('+JSON.stringify(value)+')', '', tituloModal);	
    	}
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function saberRowEdicionIndexParasitologico(idDiv){
	var indice = null;
	var data = tblResultadoRegistroParasitologico.bootstrapTable("getData");
	for(var  i = 0 ; i < data.length ;i++){
		var valor = $("#"+idDiv+i).val();
		if(valor != null && valor != undefined){
			indice = i;
			break;
		}
	}
	return indice;
}

function buscarFormulaEdicionParasitologico(idFormula){
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: ConstanteServices.ID_PARASITOLOGICOS,
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
        			$("#txtFormulaParasitologico1").attr("valor",JSON.stringify(lista[0]));
        			$("#txtFormulaParasitologico1").val(""+lista[0].combinacionFormula);
    				$("#txtFormulaParasitologico1").attr("disabled",true);
    				$("#btnGuardarFormulaParasitologico1").attr('style', 'display : none');
    				$("#btnEditarFormulaParasitologico1").attr('style', 'display : inherit');
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
    		mostrarMensaje('No se encontro la Formula del Registro Seleccionado.', ConstanteServices.IMAGEN_DANGER);    		
    	}    	
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function guardarItemRegistroFormulaParasitologico(formula){
	console.log((formula));	
	$.ajax({
		url: contexto+"general/registrarFormula",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			mantenimientoBean	: JSON.stringify(formula)
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
	    	mostrarMensaje('Formula Registrada Correctamente', ConstanteServices.IMAGEN_SUCCESS);
	    	$("#txtFormulaParasitologico1").attr("valor",respuestaBean.parametros.formulaResultado);
			$("#txtFormulaParasitologico1").attr("disabled",true);
			$("#btnGuardarFormulaParasitologico1").attr('style', 'display : none');
			$("#btnEditarFormulaParasitologico1").attr('style', 'display : inherit');
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				calcularValoresParasitologicoAll();
			}else{
				var rpta = saberRowEdicionIndexParasitologico("divResultado_");
				if(rpta != null){
					calcularValoresParasitologico(parseInt(rpta));
				}
			}
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
	
	mostrarMensaje('SE GRABO');	
	$("#txtFormulaParasitologico1").attr("disabled",true);
	$("#btnGuardarFormulaParasitologico1").attr('style', 'display : none');
	$("#btnEditarFormulaParasitologico1").attr('style', 'display : inherit');
}

function obtenerListaPuntoMuestra(idSubParametro){
	var listaPuntoMuestraAux = [];
	for(var i=0; i<ltaPuntoMuestra.length; i++){
		if(ltaPuntoMuestra[i].idSubParametro == idSubParametro){
			listaPuntoMuestraAux.push(ltaPuntoMuestra[i]);
		}
	}
	return listaPuntoMuestraAux;
}

function crearPuntoMuestra(index){
	var idSubParametro = $("#divSubParametro_"+index).val();
	var descripcionPuntoMuestra = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(5);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(idSubParametro),index,null));
}


function obtenerListaMicroorganismo(idSubParametro){
	var listaMicroorganismoAux = [];
	for(var i=0; i<ltaMicroorganismo.length; i++){
		if(parseInt(ltaMicroorganismo[i].vlDato01) == idSubParametro){
			listaMicroorganismoAux.push(ltaMicroorganismo[i]);
		}
	}
	return listaMicroorganismoAux;
}

function crearMicroorganismo(index){
	var idSubParametro = $("#divSubParametro_"+index).val();
	var descripcionMicroorganismo = tblResultadoRegistroParasitologico.find('tr').eq(index+1).find('td').eq(6);
	descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(idSubParametro),index,null));
}

function validarNuevoRegistroNuevoParasitologico(objeto){
	var indicador = true;	
	
	if(IsEmpty(objeto.idPuntoMuestra)){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	if(IsEmpty(objeto.idSubParametro)){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	return indicador;
}

/*funcion grabar observacion de parametro Parasitologico*/
function guardarObservacionParasitologico(){
	$.ajax({
        url : "./guardarObservacionParasitologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroParasitologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonParasitologico();
        	observacionParasitologico = respuestaBean.parametros.observacionParasitologico;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionParasitologico(){
	var tituloModal = 'Observacion Registro Parasitol??gico';
	modal.confirmer('??Esta seguro de Grabar la Observaci??n del Registro?', 'guardarObservacionParasitologico()', 'regresarObservacionParasitologico()', tituloModal);
}

function regresarObservacionParasitologico(){
	txtObservacionRegistro.val(""+observacionParasitologico);
}
/**/

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divVolumen'){
        	var newId = "divCantidad_"+rowNo;
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

        if(fieldId == 'divCantidad'){
        	var newId = "divVolumen_"+rowNo;
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

