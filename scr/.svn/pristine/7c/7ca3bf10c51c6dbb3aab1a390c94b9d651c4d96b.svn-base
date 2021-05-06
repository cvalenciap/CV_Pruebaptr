$(document).ready(function() {
			inicializarVariablesAceite();
			cargarComponentesAceite();
});
var accion = false;
var flagAceite = 0;
var flagVerAnalistaAceite = 0;
var nombreAnalistaAceite = "nombreAnalistaAceite";
var flagVerFormulasAceite = 0;
function inicializarVariablesAceite() {
	var tblResultadoRegistroAceite = null;
	var tblVariables1Aceite = null;
	var tblVariables2Aceite = null;
	var tblVariables3Aceite = null;
	var tblVariables4Aceite = null;
	var lblFormulaAceite1 = null;
	var txtFormulaAceite1 = null;
	var btnGuardarFormulaAceite1 = null;
	var btnEditarFormulaAceite1 = null;
	/*inicio observacion para parametro Aceite*/
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 
	/**/
}

function cargarComponentesAceite() {
	accordion();
	btnGuardarFormulaAceite1 = $('#btnGuardarFormulaAceite1');
	btnEditarFormulaAceite1 = $('#btnEditarFormulaAceite1');
	txtFormulaAceite1 = $('#txtFormulaAceite1');
	lblFormulaAceite1 = $('#lblFormulaAceite1');
	txtDescripcionFormulaAceite = $('#txtDescripcionFormulaAceite');
	tblVariables1Aceite = $('#tblVariables1Aceite');
	tblVariables2Aceite = $('#tblVariables2Aceite');
	tblVariables3Aceite = $('#tblVariables3Aceite');
	tblVariables4Aceite = $('#tblVariables4Aceite');
	/*inicio observacion para parametro Aceite*/
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	/**/
		
	if(ltaRegAceite.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else{
		$("#fechaMonitoreo").text(ltaRegAceite[0].fechaMonitoreoString);
	}

	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Aceite - N° Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroAceite").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}
	
	tblResultadoRegistroAceite = $("#tblResultadoRegistroAceite");
	
	cargarGrillaResultadoAceite(ltaRegAceite);
	
		
	$('#btnAdjuntarRegAceite').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionAceite);
    });
	
	$('#btnNuevoRegAceite').click(function() {
		if(estadoAprobacionAceite != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarAceite();
		}		
    });
	
	$('#btnGuardarTodoRegistroAceite').click(function() {
		mostrarMensaje();
		if(flagAceite == 0){
			if(estadoAprobacionAceite != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
				verificarDataGrabarAceite();			
			}
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}		
    });
		
	$('#btnBorrarMultipleRegistroAceite').click(function() {
		mostrarMensaje();
		if(flagAceite == 0){
			verificarEliminarMultipleAceite();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	btnEditarFormulaAceite1.click(function() {
		if(estadoAprobacionAceite != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			txtFormulaAceite1.attr("disabled",false);
			btnEditarFormulaAceite1.attr('style', 'display : none');
			btnGuardarFormulaAceite1.attr('style', 'display : inherit');
		}
    });
	
	btnGuardarFormulaAceite1.click(function() {
		if(estadoAprobacionAceite != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			mostrarMensaje();
			var item1 = JSON.parse(txtFormulaAceite1.attr("valor"));
			descripcionFormula = txtFormulaAceite1.val();
			validacion = validarFormulaIngreso(ltaVariable, descripcionFormula);
			if(!IsEmpty(descripcionFormula)){
				if(!IsEmpty(validacion)){
					mostrarMensaje('El campo '+item1.descripcionFormula+' No Contiene variables o expresión válida ', ConstanteServices.IMAGEN_DANGER);
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
	
	$('#btnAprobarRegistroAceite').click(function() {
		mostrarMensaje();
		if(flagAceite == 0){
			verificarAprobacionAceite();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}
    });	
	
	$('#btnHabilitarRegistroAceite').click(function() {
		verificarHabilitarRegistroAceite();
    });	
	
	$('#btnMostrarFRegistroAceite').click(function() {
		if(flagVerFormulasAceite == 0){
			flagVerFormulasAceite = 1;
			$("#btnMostrarFRegistroAceite").text("Ocultar Formulas");
			$("#divVariablesAceite").show(1000);
			$("#divFormulasAceite").show(1000);
		}else{
			flagVerFormulasAceite = 0;
			$("#btnMostrarFRegistroAceite").text("Ver Formulas");
			$("#divVariablesAceite").hide(1000);
			$("#divFormulasAceite").hide(1000);
		}	
    });	
	
	$('#btnVerAnalistaRegAceite').click(function() {
		if(flagVerAnalistaAceite == 0){
			flagVerAnalistaAceite = 1;
			$("#btnVerAnalistaRegAceite").text("Ocultar Analista");
		}else{
			flagVerAnalistaAceite = 0;
			$("#btnVerAnalistaRegAceite").text("Ver Analista");
		}
		visibleAnalistaAceite();		
    });	
	
	/*inicio observacion para parametro Aceite*/
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionAceite();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionAceite();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	/**/
	
	formatearInputAceite();
	cargarGrillaVariablesAceite();
	buscarFormulasPrincipalesAceite();
	buscarVariablesAceite();
	validacionBotonAceite();
	validarPerfilesAceite();
}

function visibleAnalistaAceite(){
	var a  = document.getElementsByClassName(nombreAnalistaAceite);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaAceite == 1){
			 a[i].className = ""+nombreAnalistaAceite;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaAceite;	
		}
	}
}

function validarPerfilesAceite(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegAceite").show();
		$("#btnMostrarFRegistroAceite").show();
		$("#btnHabilitarRegistroAceite").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegAceite").hide();
		$("#btnAprobarRegistroAceite").hide();
		$("#btnHabilitarRegistroAceite").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroAceite").hide();
		$("#btnAprobarRegistroAceite").hide();
		$("#btnVerAnalistaRegAceite").hide();
		$("#btnNuevoRegAceite").hide();
		$("#btnBorrarMultipleRegistroAceite").hide();
		$("#btnAdjuntarRegAceite").hide();
	}else{		
		tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroAceite").hide();
		$("#btnAprobarRegistroAceite").hide();
		$("#btnHabilitarRegistroAceite").hide();
		$("#btnVerAnalistaRegAceite").hide();
		$("#btnNuevoRegAceite").hide();
	}
}

function validacionBotonAceite(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionAceite != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroAceite").show();
		}else{
			$("#btnAprobarRegistroAceite").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionAceite == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroAceite").show();
		}else{
			$("#btnHabilitarRegistroAceite").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroAceite").hide();
		$("#btnHabilitarRegistroAceite").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionAceite == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroAceite").hide();
	}
}

function formatearInputAceite(){
	ponerClassConjunto("inputDecimal","volumen",0,7,4);
	ponerClassConjunto("inputDecimal","pesoInicial",0,7,4);
	ponerClassConjunto("inputDecimal","pesoFinal",0,7,4);
	ponerClassConjunto("inputNumerico","frasco",3,0,0 );
}


function cargarGrillaResultadoAceite(lista){
	tblResultadoRegistroAceite.bootstrapTable("destroy");
	var claseValidador = "";	
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	} 
	var claseVerAnalista = "nombreAnalistaAceite";
	if(flagVerAnalistaAceite != 1){
		claseVerAnalista = "hidden nombreAnalistaAceite";
	}
	tblResultadoRegistroAceite.bootstrapTable({
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
			title : 'Acción',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesRegistroAceite',
			class: 'controls',
			events : operateEventsAceite,
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
			title : 'Sub<br>Parámetro.',
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
			field : 'numFrasco',
			title : 'Nro.<br>Frasco',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlFrasco'
		},{	
			field : 'numVolumen',
			title : 'Vol L',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlVolumen'
		},{	
			field : 'numPesoInicial',
			title : 'Peso<br>Inicial(gr)<br>(f)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlPesoInicial'
		},{	
			field : 'numPesoFinal',
			title : 'Peso<br>Final(gr)<br>(g)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlPesoFinal'
		},{	
			field : 'numResultado',
			title : 'Resultado<br>(mg/L)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter : 'crearHtmlResultado',
			cellStyle : verificarEstiloColor
		},{	
			field : 'descripcionObservacion',
			title : 'Observación',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlObservacion'
		}]
	});
}

function cargarGrillaVariablesAceite(){
	tblVariables1Aceite.bootstrapTable({
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
	
	tblVariables2Aceite.bootstrapTable({
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
	
	tblVariables3Aceite.bootstrapTable({
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
	
	tblVariables4Aceite.bootstrapTable({
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

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearAccionesRegistroAceite(value, row, index) {	
	    return [
			'<button id="btnGuardarRegistroAceite" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroAceite" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroAceite" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroAceite" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
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
		var htmlCombo = '<select class="form-control input-sm" id="cboMicroorganismo_'+index+'" >';
		for (var i = 0; i < ltaMicroorganismo.length; i++) {
		   if(ltaMicroorganismo[i].idDetalleGeneral == row.idMicroorganismo){
			   htmlCombo += '<option selected="selected" value = "'+ltaMicroorganismo[i].idDetalleGeneral+'">'+ltaMicroorganismo[i].descripcion+'</option>'
		   }else{
			   htmlCombo += '<option value = "'+ltaMicroorganismo[i].idDetalleGeneral+'">'+ltaMicroorganismo[i].descripcion+'</option>'	
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

function validarNuevoRegistroNuevoAceite(objeto){
	var indicador = true;	
	if(IsEmpty(objeto.idPuntoMuestra)){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	if(IsEmpty(objeto.idSubParametro)){
		mostrarMensaje('Debe Seleccionar un Sub Parámetro.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	return indicador;
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
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" value="'+row.numCantidad+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numCantidad].join('');
	}
}

function crearHtmlFrasco(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numFrasco)){
			htmlimput = '<input type="text"  id="divFrasco_'+index+'" class="form-control input-sm frasco" style="width: 42px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divFrasco_'+index+'" value="'+row.numFrasco+'" class="form-control input-sm frasco" style="width: 42px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numFrasco].join('');
	}
}

function crearHtmlVolumen(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numVolumen)){
			htmlimput = '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm volumen" style="width: 73px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divVolumen_'+index+'" value="'+row.numVolumen+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm volumen" style="width: 73px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numVolumen].join('');
	}
}

function crearHtmlPesoInicial(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numPesoInicial)){
			htmlimput = '<input type="text"  id="divPesoInicial_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoInicial" style="width: 73px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divPesoInicial_'+index+'" value="'+row.numPesoInicial+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoInicial" style="width: 73px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numPesoInicial].join('');
	}
}

function crearHtmlPesoFinal(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numPesoFinal)){
			htmlimput = '<input type="text"  id="divPesoFinal_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoFinal" style="width: 73px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divPesoFinal_'+index+'" value="'+row.numPesoFinal+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoFinal" style="width: 73px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numPesoFinal].join('');
	}
}

function crearHtmlObservacion(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.descripcionObservacion)){
			htmlimput = '<input type="text"  id="divDescripcionObservacion_'+index+'" class="form-control input-sm descripcionObservacion" style="width: 65px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divDescripcionObservacion_'+index+'" value="'+row.descripcionObservacion+'" class="form-control input-sm descripcionObservacion" style="width: 65px !important;" />';
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

function calcularValoresAceite(index){
	var numPesoInicial = $("#divPesoInicial_"+index).val();
	var numPesoFinal = $("#divPesoFinal_"+index).val();
	var numVolumen = $("#divVolumen_"+index).val();	
	
	//para SST
	if(IsEmpty(numPesoInicial) || IsEmpty(numPesoFinal) || IsEmpty(numVolumen)){
		$("#divResultado_"+index).val("");
	}else{		
		var listaVariablesInternaAceite = [];
		var objvolumen = {
		    descripcionLetra : 'e',
		    valor : ''+numVolumen
		};
		var objpesoInicial = {
		    descripcionLetra : 'f',
		    valor : ''+numPesoInicial
		};
		
		var objpesoFinal = {
		    descripcionLetra : 'g',
		    valor : ''+numPesoFinal
		};
		
		listaVariablesInternaAceite.push(objvolumen);
		listaVariablesInternaAceite.push(objpesoInicial);
		listaVariablesInternaAceite.push(objpesoFinal);
		
		var formulaAceite = txtFormulaAceite1.val();		
		var respuestaAceite = ejecutarFormula(listaVariablesInternaAceite,formulaAceite);
		console.log(listaVariablesInternaAceite);
		console.log(respuestaAceite);
		if(respuestaAceite != ConstanteServices.VARIABLES_INEXISTENTES && respuestaAceite != ConstanteServices.EXPRESION_INVALIDA && respuestaAceite != null){
			var numResultadoFinal = cortarDecimalRedondear(respuestaAceite,4);
			$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
		}else{
			$("#divResultado_"+index).val(""+respuestaAceite);
		}
	}
	completarLtaRegistroAceite();
}

function eliminarSubParametroNuevoAceite(index){
	guardarTablaAntesCambiosAceite();
	tblResultadoRegistroAceite.bootstrapTable('remove', {field: 'indice', values: [parseInt(index)]});
	formatearInputAceite();
	flagAceite = 0;
	visibleAnalistaAceite();
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

function verificarParametroAgregarAceite(){
	mostrarMensaje();
	if(flagAceite == 0){
		var ltaGrilla = tblResultadoRegistroAceite.bootstrapTable("getData");
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
				objetoClon.numFrasco = crearHtmlFrascoNuevo(countRow,null);
				objetoClon.numVolumen = crearHtmlVolumenNuevo(countRow,null);
				objetoClon.numPesoInicial = crearHtmlPesoInicialNuevo(countRow,null);
				objetoClon.numPesoFinal = crearHtmlPesoFinalNuevo(countRow,null);
				objetoClon.numResultado = crearHtmlResultadoNuevo(countRow,null);
				objetoClon.descripcionObservacion = crearHtmlObservacionNuevo(countRow,null);
			}
			guardarTablaAntesCambiosAceite();
			tblResultadoRegistroAceite.bootstrapTable('insertRow', {
			    index: countRow,
			    row: objetoClon
			});
			var accionGuardar = tblResultadoRegistroAceite.find('tr').eq(countRow+1).find('td').eq(1).find('#btnGuardarRegistroAceite');
			var accionEditar = tblResultadoRegistroAceite.find('tr').eq(countRow+1).find('td').eq(1).find('#btnEditarRegistroAceite');
			var accionAnular = tblResultadoRegistroAceite.find('tr').eq(countRow+1).find('td').eq(1).find('#btnAnularRegistroAceite');
			        
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
			validacionResultado(countRow);
			formatearInputAceite();
			flagAceite = 1;
			visibleAnalistaAceite();
			actualizarIndicesAceite();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputAceite();
}

function crearHtmlComboPuntoMuestraNuevo(ltaPM,index, value){
	var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" style="width: 80px !important;">';
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

function crearHtmlComboSubParametroNuevo(ltaSubPara,index, value){
	var htmlComboSubParametro = '<select class="form-control input-sm subParametro" id="divSubParametro_'+index+'" onchange="verPuntoMuestra('+index+'); validacionResultado('+index+');" style="width: 180px !important;">';
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
	var descripcionPuntoMuestra = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(5);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametroAux),index,null));
}

function crearHtmlFrascoNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divFrasco_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm frasco" style="width: 42px !important;" />';
	}else{
		return '<input type="text"  id="divFrasco_'+index+'" onkeyup="calcularValoresAceite('+index+')" value = "'+value+'" class="form-control input-sm frasco" style="width: 42px !important;" />';
	}
}

function crearHtmlVolumenNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm volumen" style="width: 73px !important;" />';
	}else{
		return '<input type="text"  id="divVolumen_'+index+'" onkeyup="calcularValoresAceite('+index+')" value = "'+value+'" class="form-control input-sm volumen" style="width: 73px !important;" />';
	}
}

function crearHtmlPesoInicialNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divPesoInicial_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoInicial" style="width: 73px !important;" />';
	}else{
		return '<input type="text"  id="divPesoInicial_'+index+'" onkeyup="calcularValoresAceite('+index+')" value = "'+value+'" class="form-control input-sm pesoInicial" style="width: 73px !important;" />';
	}
}

function crearHtmlPesoFinalNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divPesoFinal_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm pesoFinal" style="width: 73px !important;" />';
	}else{
		return '<input type="text"  id="divPesoFinal_'+index+'" onkeyup="calcularValoresAceite('+index+')" value = "'+value+'" class="form-control input-sm pesoFinal" style="width: 73px !important;" />';
	}
}

function crearHtmlObservacionNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divDescripcionObservacion_'+index+'" onkeyup="calcularValoresAceite('+index+')" class="form-control input-sm observacion" style="width: 73px !important;" />';
	}else{
		return '<input type="text"  id="divDescripcionObservacion_'+index+'" onkeyup="calcularValoresAceite('+index+')" value = "'+value+'" class="form-control input-sm observacion" style="width: 73px !important;" />';
	}
}

function crearHtmlResultadoNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text" id="divResultado_'+index+'" readonly = "readonly"  class="form-control input-sm resultado" style="width: 80px !important;" />';
	}else{
		return '<input type="text" id="divResultado_'+index+'" readonly = "readonly" value = "'+value+'"  class="form-control input-sm resultado" style="width: 80px !important;" />';
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

function validacionResultado(index){
}

function validacionResultadoEditar(index){
}

function verificarDataGrabarAceite(){
	if(flagAceite == 0){	
		var lta = completarLtaRegistroAceite();
		if(validarDataTablaAceite(lta)){	
			var tituloModal = 'Registro Aceite';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroAceite()', '', tituloModal);
		}	
	}
}

function validarDataTablaAceite(listaFinal){
	var indicador = true;
	for(var i = 0 ; i < listaFinal.length ; i++){
		if(IsEmpty(listaFinal[i].idAnalista)){
			mostrarMensaje('Debe Seleccionar una Analista en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(listaFinal[i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numFrasco) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Nro. Frasco en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numVolumen) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Volumen en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numPesoInicial) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Peso Inicial en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numPesoFinal) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Peso Final en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numResultado) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Resultado en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return indicador
}

function completarLtaRegistroAceite(){
	var listaFinal = tblResultadoRegistroAceite.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
		var idMicroorganismo = $("#cboMicroorganismo_"+i).val();
		if(idMicroorganismo != null && idMicroorganismo != undefined){
			listaFinal[i].idMicroorganismo  = parseInt(idMicroorganismo);
		}
		var idSubPM = $("#divSubParametro_"+i).val();
		if(idSubPM != null && idSubPM != undefined){
			listaFinal[i].idSubParametro  = parseInt(idSubPM);
		}		
		var idAnalistaInterno = $("#cboAnalista_"+i).val();
		if(idAnalistaInterno != null && idAnalistaInterno != undefined){
			listaFinal[i].idAnalista  = idAnalistaInterno;
		}
		var numVolumen = $("#divVolumen_"+i).val();
		if(numVolumen != null && numVolumen != undefined){
			if(IsEmpty(numVolumen)){
				listaFinal[i].numVolumen = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numVolumen  = numVolumen;
			}
		}
		listaFinal[i].numVolumen = validarIncorrectosValores(listaFinal[i].numVolumen);
		var numFrasco = $("#divFrasco_"+i).val();
		if(numFrasco != null && numFrasco != undefined){
			if(IsEmpty(numFrasco)){
				listaFinal[i].numFrasco = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numFrasco  = numFrasco;
			}
		}
		listaFinal[i].numFrasco = validarIncorrectosValores(listaFinal[i].numFrasco);
		var numCantidad = $("#divCantidad_"+i).val();
		if(numCantidad != null && numCantidad != undefined){
			if(IsEmpty(numCantidad)){
				listaFinal[i].numCantidad = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numCantidad  = numCantidad;
			}
		}
		listaFinal[i].numCantidad = validarIncorrectosValores(listaFinal[i].numCantidad);
		var numPesoInicial = $("#divPesoInicial_"+i).val();
		if(numPesoInicial != null && numPesoInicial != undefined){
			if(IsEmpty(numPesoInicial)){
				listaFinal[i].numPesoInicial = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numPesoInicial  = numPesoInicial;
			}
		}
		listaFinal[i].numPesoInicial = validarIncorrectosValores(listaFinal[i].numPesoInicial);
		var numPesoFinal = $("#divPesoFinal_"+i).val();
		if(numPesoFinal != null && numPesoFinal != undefined){
			if(IsEmpty(numPesoFinal)){
				listaFinal[i].numPesoFinal = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numPesoFinal  = numPesoFinal;
			}
		}
		listaFinal[i].numPesoFinal = validarIncorrectosValores(listaFinal[i].numPesoFinal);
		var numResultado = $("#divResultado_"+i).val();
		if(numResultado != null && numResultado != undefined){
			if(IsEmpty(numResultado)){
				listaFinal[i].numResultado = ConstanteServices.VALOR_DEFECTO;
			}else{
				listaFinal[i].numResultado  = numResultado;
			}
		}
		listaFinal[i].numResultado = validarIncorrectosValores(listaFinal[i].numResultado);
		var descripcionObservacion = $("#divDescripcionObservacion_"+i).val();
		if(descripcionObservacion != null && descripcionObservacion != undefined){
			listaFinal[i].descripcionObservacion  = descripcionObservacion;
		}
		
		var itemFormulaResultado = JSON.parse(txtFormulaAceite1.attr("valor"));
		if(itemFormulaResultado != null && itemFormulaResultado != undefined ){
			listaFinal[i].idFormulaResultado  = itemFormulaResultado.idFormulaxParametro;
		}
	}
	return listaFinal;
}

function calcularValoresAceiteAll(){
	var listaFinal = tblResultadoRegistroAceite.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		calcularValoresAceite(i);
	}
}

function grabarDatosRegistroAceite(){
	var lta = completarLtaRegistroAceite();
	$.ajax({
        url : "./grabarDatosRegistroAceite",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroAceite : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroAceite : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroAceite").hide();
    		mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);
        	$("#lblTitulo").text("Registro Aceite - N° Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionAceite);
        	observacionAceite = respuestaBean.parametros.observacionAceite;
        	validacionBotonAceite();
        	buscarDatosRegistroAceite();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroAceite(){
	$.ajax({
        url : "./buscarDatosRegistroAceite",
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
        		tblResultadoRegistroAceite.bootstrapTable('load', JSON.parse(ListaResultado));	
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		visibleAnalistaAceite();
        	}else{
            	tblResultadoRegistroAceite.bootstrapTable('load', []);
            	visibleAnalistaAceite();
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputAceite();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarNuevoRegistroEditarAceite(index){
	var indicador = true;	
		
	var idAnalistaNew = $("#cboAnalista_"+index).val();  
	if(IsEmpty(idAnalistaNew)){
		mostrarMensaje('Debe Seleccionar una Analista.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var idSubPNew = $("#divSubParametro_"+index).val();
	if(IsEmpty(idSubPNew)){
		mostrarMensaje('Debe Seleccionar un Sub Parámetro.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
	if(IsEmpty(idPuntoMuestraNew)){
		mostrarMensaje('Debe Seleccionar un Punto de Muestra.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numFrasco = $("#divFrasco_"+index).val();
	if(IsEmpty(numFrasco)){
		mostrarMensaje('Debe Ingresar un Frasco', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numVolumen = $("#divVolumen_"+index).val();
	if(IsEmpty(numVolumen)){
		mostrarMensaje('Debe Ingresar un Volumen', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numPesoInicial = $("#divPesoInicial_"+index).val();
	if(IsEmpty(numPesoInicial)){
		mostrarMensaje('Debe Ingresar un Peso Inicial', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numPesoFinal = $("#divPesoFinal_"+index).val();
	if(IsEmpty(numPesoFinal)){
		mostrarMensaje('Debe Ingresar un Peso Final', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numResultado = $("#divResultado_"+index).val();	
	if(numResultado == ConstanteServices.VARIABLES_INEXISTENTES || numResultado == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(numResultado) || numResultado == "Infinity" || numResultado == "NaN"){
		mostrarMensaje('El Campo Resultado. del item N° '+(index+1)+' es Erróneo!', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	if(parseFloat(numPesoInicial) >= parseFloat(numPesoFinal)){
		mostrarMensaje('El campo Peso Inicial debe ser Menor a Peso Final en el item N° '+(index+1), ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	return indicador;
}

function grabarItemRegistroAceite(item){
	$.ajax({
        url : "./grabarItemRegistroAceite",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	registroAceiteBean : JSON.stringify(item),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	flagAceite = 0;
        	buscarFormulasPrincipalesAceite();
        	buscarDatosRegistroAceite();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroAceite(idRegAceite){
	$.ajax({
        url : "./eliminarItemRegistroAceite",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroAceite : idRegAceite
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagAceite = 0;
        	buscarDatosRegistroAceite();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaAceite();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarParametroEditarNuevoAceite(index,row){
	mostrarMensaje();
	if(flagAceite == 0){
		var ltaGrilla = tblResultadoRegistroAceite.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
			var objetoClon = clonarObjeto(row);
			var accionGuardar = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(1).find('#btnGuardarRegistroAceite');
			var accionEditar = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(1).find('#btnEditarRegistroAceite');
			var accionAnular = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(1).find('#btnAnularRegistroAceite');
			var accionRegresar = tblResultadoRegistroAceite.find('tr').eq(index + 1).find('td').eq(1).find('#btnRegresarRegistroAceite');
			
			var nombreAnalista = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(3);
			var descripcionSubparametro = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(4);			
			var descripcionPuntoMuestra = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(5);
			var numFrasco = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(6);
			var numVolumen = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(7);
			var numPesoInicial = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(8);
			var numPesoFinal = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(9);
			var numResultado = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(10);
			var descripcionObservacion = tblResultadoRegistroAceite.find('tr').eq(index+1).find('td').eq(11);
			
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
				
			descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
			descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(row.idSubParametro),index,row.idPuntoMuestra));
			if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
				nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));
				numFrasco.html(""+crearHtmlFrascoNuevo(index, ""+row.numFrasco));
				numVolumen.html(""+crearHtmlVolumenNuevo(index,""+ row.numVolumen));
				numPesoInicial.html(""+crearHtmlPesoInicialNuevo(index, ""+row.numPesoInicial));
				numPesoFinal.html(""+crearHtmlPesoFinalNuevo(index, ""+row.numPesoFinal));
				numResultado.html(""+crearHtmlResultadoNuevo(index, ""+row.numResultado));
				descripcionObservacion.html(""+crearHtmlObservacionNuevo(index, ""+row.descripcionObservacion));
//				extraemos las formulas 					
				buscarFormulaEdicionAceite(row.idFormulaResultado);
				accionRegresar[0].style.display = 'inherit';
		    }
			validacionResultadoEditar(index);
			calcularValoresAceite(index);
			formatearInputAceite();
			flagAceite = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputAceite();
}

function guardarTablaAntesCambiosAceite(){
	var lista = completarLtaRegistroAceite();
	tblResultadoRegistroAceite.bootstrapTable('load', lista);
	visibleAnalistaAceite();
}

function buscarIndicadorFactorSubParametro(idSubPCombo){
	var indicador = 0;
	for(var i = 0 ; i < ltaSubParametrosTodo.length ; i ++){
		if(ltaSubParametrosTodo[i].idSubParametro == idSubPCombo){
			indicador = ltaSubParametrosTodo[i].indicadorFactor;
			break;
		}
	}
	return indicador;
}

function buscarVariablesAceite() {
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
	        		tblVariables1Aceite.bootstrapTable('load', listaAux);
	        		
	        		var listaAux = [];
	        		for(i = indice; i < (indice*2); i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables2Aceite.bootstrapTable('load', listaAux);
	        		var listaAux = [];
	        		for(i = (indice*2); i < (indice*3); i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables3Aceite.bootstrapTable('load', listaAux);
	        		var listaAux = [];
	        		for(i = (indice*3); i < ListaResultado.length; i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables4Aceite.bootstrapTable('load', listaAux);
	        	} else {
	        		tblVariables1Aceite.bootstrapTable('load', []);
	        		tblVariables2Aceite.bootstrapTable('load', []);
	        		tblVariables3Aceite.bootstrapTable('load', []);
	        		tblVariables4Aceite.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron Variables registradas en el sistema.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    }); 	
}

function buscarFormulasPrincipalesAceite() {
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
            			lblFormulaAceite1.text(""+lista[0].descripcionFormula);
            			txtFormulaAceite1.val(""+lista[0].combinacionFormula);
            			txtFormulaAceite1.attr("valor",JSON.stringify(lista[0]));
            			txtFormulaAceite1.attr("disabled",true);
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
        				$("#txtFormulaAceite1").attr("valor",JSON.stringify(lista[0]));
        				$("#txtFormulaAceite1").attr("disabled",true);
        				$("#btnGuardarFormulaAceite1").attr('style', 'display : none');
        				$("#btnEditarFormulaAceite1").attr('style', 'display : inherit');
        				if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
        					calcularValoresAceiteAll();
        				}else if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndexAceite("divResultado_");
        					if(rpta != null){
        						calcularValoresAceite(parseInt(rpta));
        					}
        				}
        			}else{
        				console.log(lista);
        				$("#txtFormulaAceite1").attr("disabled",true);
        				$("#btnGuardarFormulaAceite1").attr('style', 'display : none');
        				$("#btnEditarFormulaAceite1").attr('style', 'display : inherit');
        				if(modo != ConstanteServices.ESTADO_OPCION_NUEVA){
        					var rpta = saberRowEdicionIndexAceite("divResultado_");
        					if(rpta != null){
        						calcularValoresAceite(parseInt(rpta));
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
     		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'guardarItemRegistroFormulaAceite('+JSON.stringify(value)+')', '', tituloModal);	
    	}
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function guardarItemRegistroFormulaAceite(formula){
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
	    	$("#txtFormulaAceite1").attr("valor",respuestaBean.parametros.formulaResultado);
			$("#txtFormulaAceite1").attr("disabled",true);
			$("#btnGuardarFormulaAceite1").attr('style', 'display : none');
			$("#btnEditarFormulaAceite1").attr('style', 'display : inherit');
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				calcularValoresAceiteAll();
			}else{
				var rpta = saberRowEdicionIndexAceite("divResultado_");
				if(rpta != null){
					calcularValoresAceite(parseInt(rpta));
				}
			}
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
	
	mostrarMensaje('SE GRABO');	
	$("#txtFormulaAceite1").attr("disabled",true);
	$("#btnGuardarFormulaAceite1").attr('style', 'display : none');
	$("#btnEditarFormulaAceite1").attr('style', 'display : inherit');
}

function buscarFormulaEdicionAceite(idFormula){
	$.ajax({
        url : contexto+"general/buscarFormula",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idParametroFormula: ConstanteServices.ID_PARAMETRO_ACEITE,
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
        			$("#txtFormulaAceite1").attr("valor",JSON.stringify(lista[0]));
        			$("#txtFormulaAceite1").val(""+lista[0].combinacionFormula);
    				$("#txtFormulaAceite1").attr("disabled",true);
    				$("#btnGuardarFormulaAceite1").attr('style', 'display : none');
    				$("#btnEditarFormulaAceite1").attr('style', 'display : inherit');
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

function saberRowEdicionIndexAceite(idDiv){
	var indice = null;
	var data = tblResultadoRegistroAceite.bootstrapTable("getData");
	for(var  i = 0 ; i < data.length ;i++){
		var valor = $("#"+idDiv+i).val();
		if(valor != null && valor != undefined){
			indice = i;
			break;
		}
	}
	return indice;
}

function verificarAprobacionAceite(){
	if(flagAceite == 0){
		var tituloModal = 'Aprobación Registro Aceite';
		modal.confirmer('¿Esta seguro de Aprobar Registro Aceite ?', 'aprobarRegistroAceite()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroAceite(){
	$.ajax({
        url : "./aprobarRegistroAceite",
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
    		$("#btnAprobarRegistroAceite").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionAceite = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonAceite();
        	tblResultadoRegistroAceite.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroAceite();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroAceite(){
	if(flagAceite == 0){
		var tituloModal = 'Habilitación Registro Aceite';
		modal.confirmer('¿Esta seguro de Habilitar Registro Aceite ?', 'habilitarRegistroAceite()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroAceite(){
	$.ajax({
        url : "./aprobarRegistroAceite",
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
    		$("#btnAprobarRegistroAceite").hide();
        	mostrarMensaje('Se Habilitó Registo Aceite Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionAceite = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonAceite();
        	buscarDatosRegistroAceite();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarEliminarMultipleAceite(){
	if(flagAceite == 0){
		var seleccionados = tblResultadoRegistroAceite.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminación Múltiple Aceite';
   			modal.confirmer('¿Esta seguro de Proceder con la '+tituloModal+' ?', 'EliminarMultipleRegistroAceite()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function EliminarMultipleRegistroAceite(){
	var seleccionados = tblResultadoRegistroAceite.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			eliminarSubParametroNuevoAceite(seleccionados[i].indice);
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		eliminarVariosItemRegistroAceite(JSON.stringify(seleccionados));
	}
}

function eliminarVariosItemRegistroAceite(ltaRegistroAceite){
	$.ajax({
        url : "./eliminarVariosItemRegistroAceite",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	ltaRegistroAceite : ltaRegistroAceite
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagAceite = 0;
        	buscarDatosRegistroAceite();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaAceite();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function actualizarIndicesAceite(){
	var data = tblResultadoRegistroAceite.bootstrapTable("getData");
	for(var i=0; i<data.length; i++){
		data[i].indice = i+1;
	}
}

/*funcion grabar observacion de parametro Aceite*/
function guardarObservacionAceite(){
	$.ajax({
        url : "./guardarObservacionAceite",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroAceite : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonAceite();
        	observacionAceite = respuestaBean.parametros.observacionAceite;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionAceite(){
	var tituloModal = 'Observacion Registro Aceite';
	modal.confirmer('¿Esta seguro de Grabar la Observación del Registro?', 'guardarObservacionAceite()', 'regresarObservacionAceite()', tituloModal);
}

function regresarObservacionAceite(){
	txtObservacionRegistro.val(""+observacionAceite);
}
/**/

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divFrasco'){
        	var newId = "divVolumen_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divVolumen'){
        	var newId = "divPesoInicial_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoInicial'){
        	var newId = "divPesoFinal_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoFinal'){
        	var newId = "divDescripcionObservacion_"+rowNo;
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

        if(fieldId == 'divDescripcionObservacion'){
        	var newId = "divPesoFinal_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoFinal'){
        	var newId = "divPesoInicial_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divPesoInicial'){
        	var newId = "divVolumen_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divVolumen'){
        	var newId = "divFrasco_"+rowNo;
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
