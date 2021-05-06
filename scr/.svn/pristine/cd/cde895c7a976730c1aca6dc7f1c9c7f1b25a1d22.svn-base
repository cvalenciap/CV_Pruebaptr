$(document).ready(function() {
			inicializarVariablesHidrobiologico();
			cargarComponentesHidrobiologico();
});
var accion = false;
var flagHidrobiologico = 0;
var flagVerAnalistaHidrobiologico = 0;
var nombreAnalistaHidrobiologico = "nombreAnalistaHidrobiologico";
var flagVerFormulasHidrobiologico = 0;

function inicializarVariablesHidrobiologico() {
	var tblResultadoRegistroHidrobiologico = null;
	var tblVariables1Hidrobiologico = null;
	var tblVariables2Hidrobiologico = null;
	var tblVariables3Hidrobiologico = null;
	var tblVariables4Hidrobiologico = null;
	var lblFormulaHidrobiologico1 = null;
	var txtFormulaHidrobiologico1 = null;
	var lblFormulaHidrobiologico2 = null;
	var txtFormulaHidrobiologico2 = null;
	var lblFormulaHidrobiologico3 = null;
	var txtFormulaHidrobiologico3 = null;
	var lblFormulaHidrobiologico4 = null;
	var txtFormulaHidrobiologico4 = null;
	/*inicio observacion para parametro Hidrobiologico*/
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 
	/**/
}

function cargarComponentesHidrobiologico() {	
	console.log("HIDRO");
	accordion();
	txtFormulaHidrobiologico1 = $('#txtFormulaHidrobiologico1');
	lblFormulaHidrobiologico1 = $('#lblFormulaHidrobiologico1');
	txtFormulaHidrobiologico2 = $('#txtFormulaHidrobiologico2');
	lblFormulaHidrobiologico2 = $('#lblFormulaHidrobiologico2');
	txtFormulaHidrobiologico3 = $('#txtFormulaHidrobiologico3');
	lblFormulaHidrobiologico3 = $('#lblFormulaHidrobiologico3');
	txtFormulaHidrobiologico4 = $('#txtFormulaHidrobiologico4');
	lblFormulaHidrobiologico4 = $('#lblFormulaHidrobiologico4');
	txtDescripcionFormulaHidrobiologico = $('#txtDescripcionFormulaHidrobiologico');
	tblVariables1Hidrobiologico = $('#tblVariables1Hidrobiologico');
	tblVariables2Hidrobiologico = $('#tblVariables2Hidrobiologico');
	tblVariables3Hidrobiologico = $('#tblVariables3Hidrobiologico');
	tblVariables4Hidrobiologico = $('#tblVariables4Hidrobiologico');
	/*inicio observacion para parametro Hidrobiologico*/
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	/**/
	
	if(ltaRegHidrobiologico.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else{
		$("#fechaMonitoreo").text(ltaRegHidrobiologico[0].fechaMonitoreoString);
	}
		
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Hidrobiologico - N° Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroHidrobiologico").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}
		
	tblResultadoRegistroHidrobiologico = $("#tblResultadoRegistroHidrobiologico");
	
	cargarGrillaResultadoHidrobiologico(ltaRegHidrobiologico);
		
	$('#btnAdjuntarRegHidrobiologico').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionHidrobiologico);
    });
	
	$('#btnNuevoRegHidrobiologico').click(function() {
		if(estadoAprobacionHidrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarHidrobiologico();
		}
    });
	
	$('#btnGuardarTodoRegistroHidrobiologico').click(function() {
		mostrarMensaje();
		if(flagHidrobiologico == 0){
			if(estadoAprobacionHidrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
				verificarDataGrabarHidrobiologico();
			}
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}
    });
		
	$('#btnBorrarMultipleRegistroHidrobiologico').click(function() {
		mostrarMensaje();
		if(flagHidrobiologico == 0){
			verificarEliminarMultipleHidrobiologico();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	$('#btnAprobarRegistroHidrobiologico').click(function() {
		mostrarMensaje();
		if(flagHidrobiologico == 0){
			verificarAprobacionHidrobiologico();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}		
    });
	
	$('#btnHabilitarRegistroHidrobiologico').click(function() {
		verificarHabilitarRegistroHidrobiologico();
    });
	
	$('#btnMostrarFRegistroHidrobiologico').click(function() {
		if(flagVerFormulasHidrobiologico == 0){
			flagVerFormulasHidrobiologico = 1;
			$("#btnMostrarFRegistroHidrobiologico").text("Ocultar Formulas");
			$("#divVariablesHidrobiologico").show(1000);
			$("#divFormulasHidrobiologico").show(1000);
		}else{
			flagVerFormulasHidrobiologico = 0;
			$("#btnMostrarFRegistroHidrobiologico").text("Ver Formulas");
			$("#divVariablesHidrobiologico").hide(1000);
			$("#divFormulasHidrobiologico").hide(1000);
		}	
    });
	
	$('#btnVerAnalistaRegHidrobiologico').click(function() {
		if(flagVerAnalistaHidrobiologico == 0){
			flagVerAnalistaHidrobiologico = 1;
			$("#btnVerAnalistaRegHidrobiologico").text("Ocultar Analista");
		}else{
			flagVerAnalistaHidrobiologico = 0;
			$("#btnVerAnalistaRegHidrobiologico").text("Ver Analista");
		}
		visibleAnalistaHidrobiologico();				
    });
	
	/*inicio observacion para parametro Hidrobiologico*/
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionHidrobiologico();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionHidrobiologico();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	/**/
	
	cargarGrillaVariablesHidrobiologico();
	buscarFormulasPrincipalesHidrobiologico();
	buscarVariableHidrobiologico(ltaVariableGeneral);
	
	validacionBotonHidrobiologico();
	formatearInputHidrobiologico();
	
	validarPerfilesHidrobiologico();
}

function visibleAnalistaHidrobiologico(){
	var a  = document.getElementsByClassName(nombreAnalistaHidrobiologico);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaHidrobiologico == 1){
			 a[i].className = ""+nombreAnalistaHidrobiologico;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaHidrobiologico;	
		}
	}
}

function validarPerfilesHidrobiologico(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegHidrobiologico").show();
		$("#btnMostrarFRegistroHidrobiologico").show();
		$("#btnHabilitarRegistroHidrobiologico").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegHidrobiologico").hide();
		$("#btnAprobarRegistroHidrobiologico").hide();
		$("#btnHabilitarRegistroHidrobiologico").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroHidrobiologico").hide();
		$("#btnAprobarRegistroHidrobiologico").hide();
		$("#btnVerAnalistaRegHidrobiologico").hide();
		$("#btnNuevoRegHidrobiologico").hide();
		$("#btnAdjuntarRegHidrobiologico").hide();
		$("#btnBorrarMultipleRegistroHidrobiologico").hide();
	}else{		
		tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroHidrobiologico").hide();
		$("#btnAprobarRegistroHidrobiologico").hide();
		$("#btnHabilitarRegistroHidrobiologico").hide();
		$("#btnVerAnalistaRegHidrobiologico").hide();
		$("#btnNuevoRegHidrobiologico").hide();
	}
}

function validacionBotonHidrobiologico(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionHidrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroHidrobiologico").show();
		}else{
			$("#btnAprobarRegistroHidrobiologico").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionHidrobiologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroHidrobiologico").show();
		}else{
			$("#btnHabilitarRegistroHidrobiologico").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroHidrobiologico").hide();
		$("#btnHabilitarRegistroHidrobiologico").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionHidrobiologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroHidrobiologico").hide();
	}
}

function formatearInputHidrobiologico(){
	ponerClassConjunto("inputDecimal","conteo",0,5,2);
	ponerClassConjunto("inputDecimal","cantidad",0,5,2);
	ponerClassConjuntoFechas("fechaMonitoreo");
	ponerClassConjuntoFechas("fechaRegDato");	
}


function cargarGrillaResultadoHidrobiologico(lista){
	tblResultadoRegistroHidrobiologico.bootstrapTable("destroy")
	var claseValidador = "";
	var claseVerAnalista = "nombreAnalistaHidrobiologico";
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	}
	if(flagVerAnalistaHidrobiologico != 1){
		claseVerAnalista = "hidden nombreAnalistaHidrobiologico";
	}
	tblResultadoRegistroHidrobiologico.bootstrapTable({
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
			formatter: 'crearAccionesRegistroHidrobiologico',
			class: 'controls',
			events : operateEventsHidrobiologico,
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
			field : 'descripcionMicroorganismo',
			title : 'Microorganismo',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlMicroorganismo'
		},{	
			field : 'numConteo',
			title : 'Nro<br>Organismo',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : crearHtmlConteo
		},{	
			field : 'numCantidad',
			title : 'Nro<br>Franja/Campo',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : crearHtmlCantidad
		},{	
			field : 'descripcionTipoConteo',
			title : 'Tipo<br>Conteo',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlTipoConteo'
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
			title : 'Observación',
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

function crearAccionesRegistroHidrobiologico(value, row, index) {	
	    return [
			'<button id="btnGuardarRegistroHidrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroHidrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroHidrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroHidrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
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
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 60px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCantidad_'+index+'" value="'+row.numCantidad+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 60px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numCantidad].join('');
	}
}

function crearHtmlConteo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCantidad)){
			htmlimput = '<input type="text"  id="divConteo_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 60px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divConteo_'+index+'" value="'+row.numConteo+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 60px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numConteo].join('');
	}
}



function crearHtmlTipoConteo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlCombo = '<select class="form-control input-sm" id="cboTipoConteo_'+index+'" onchange="calcularValoresHidrobiologico('+index+')" style="width: 105px !important">';
		for (var i = 0; i < ltaTipoConteo.length; i++) {
		   if(ltaTipoConteo[i].idDetalleGeneral == row.idTipoConteo){
			   htmlCombo += '<option selected="selected" value = "'+ltaTipoConteo[i].idDetalleGeneral+'">'+ltaTipoConteo[i].descripcion+'</option>'
		   }else{
			   htmlCombo += '<option value = "'+ltaTipoConteo[i].idDetalleGeneral+'">'+ltaTipoConteo[i].descripcion+'</option>'	
		   }
		}
		htmlCombo 	  +='</select>'			
		return [htmlCombo].join('');
	}else{
		return [''+row.descripcionTipoConteo].join('');
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

function calcularValoresHidrobiologico(index){
	var numValor = $("#divValor_"+index).val();
	var numFactor = $("#divFactor_"+index).val();
	if(!IsEmpty(numFactor)){
		var calculado = numValor * numFactor;
		$("#divResultado_"+index).val(""+calculado);
	}else{
		$("#divResultado_"+index).val(""+numValor);
	}
	
	var numConteo = $("#divConteo_"+index).val();
	var numCantidad = $("#divCantidad_"+index).val();
	var idTipoConteo = $("#cboTipoConteo_"+index).val();
	var idSubParametro = $("#divSubParametro_"+index).val();
	if(idSubParametro == undefined){
		idSubParametro = tblResultadoRegistroHidrobiologico.bootstrapTable("getData")[index].idSubParametro;
	}
	
	if(IsEmpty(numConteo) || IsEmpty(numCantidad) || IsEmpty(idTipoConteo) || IsEmpty(idSubParametro)){
		$("#divResultado_"+index).val("");
	}else{
		var listaVariablesInternaHidrobiologico = [];
		var objareacampo = {
		    descripcionLetra : 'a',
		    valor : 0.9224
		};
		var objprofundidadcampo = {
		    descripcionLetra : 'b',
		    valor : 1
		};
		var objnrofranja = {
		    descripcionLetra : 'c',
		    valor : ''+numCantidad
		};
		var objnroorganismo = {
			descripcionLetra : 'd',
			valor : ''+numConteo
		};
		var objlongfranja = {
			descripcionLetra : 'e',
			valor : 20
		};
		var objanchofranja = {
			descripcionLetra : 'f',
			valor : 0.98
		};
		
		listaVariablesInternaHidrobiologico.push(objareacampo);
		listaVariablesInternaHidrobiologico.push(objprofundidadcampo);
		listaVariablesInternaHidrobiologico.push(objnrofranja);
		listaVariablesInternaHidrobiologico.push(objnroorganismo);
		listaVariablesInternaHidrobiologico.push(objlongfranja);
		listaVariablesInternaHidrobiologico.push(objanchofranja);
		if(idTipoConteo == ConstanteServices.ID_TIPO_CONTEO_CAMPO){
			if(idSubParametro == ConstanteServices.ID_SUBPARAMETRO_FITO){
				var formulaHidrobiologico = txtFormulaHidrobiologico1.val();		
				var respuestaHidrobiologico = ejecutarFormula(listaVariablesInternaHidrobiologico,formulaHidrobiologico);
				if(respuestaHidrobiologico != ConstanteServices.VARIABLES_INEXISTENTES && respuestaHidrobiologico != ConstanteServices.EXPRESION_INVALIDA && respuestaHidrobiologico != null){
					var numResultadoFinal = cortarDecimalRedondear(respuestaHidrobiologico,4);
					$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
				}else{
					$("#divResultado_"+index).val(""+respuestaHidrobiologico);
				}
			}else if(idSubParametro == ConstanteServices.ID_SUBPARAMETRO_ZOO){
				var formulaHidrobiologico = txtFormulaHidrobiologico2.val();		
				var respuestaHidrobiologico = ejecutarFormula(listaVariablesInternaHidrobiologico,formulaHidrobiologico);
				if(respuestaHidrobiologico != ConstanteServices.VARIABLES_INEXISTENTES && respuestaHidrobiologico != ConstanteServices.EXPRESION_INVALIDA && respuestaHidrobiologico != null){
					var numResultadoFinal = cortarDecimalRedondear(respuestaHidrobiologico,4);
					$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
				}else{
					$("#divResultado_"+index).val(""+respuestaHidrobiologico);
				}
			}
		}else if(idTipoConteo == ConstanteServices.ID_TIPO_CONTEO_FRANJA){
			if(idSubParametro == ConstanteServices.ID_SUBPARAMETRO_FITO){
				var formulaHidrobiologico = txtFormulaHidrobiologico3.val();		
				var respuestaHidrobiologico = ejecutarFormula(listaVariablesInternaHidrobiologico,formulaHidrobiologico);
				if(respuestaHidrobiologico != ConstanteServices.VARIABLES_INEXISTENTES && respuestaHidrobiologico != ConstanteServices.EXPRESION_INVALIDA && respuestaHidrobiologico != null){
					var numResultadoFinal = cortarDecimalRedondear(respuestaHidrobiologico,4);
					$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
				}else{
					$("#divResultado_"+index).val(""+respuestaHidrobiologico);
				}
			}else if(idSubParametro == ConstanteServices.ID_SUBPARAMETRO_ZOO){
				var formulaHidrobiologico = txtFormulaHidrobiologico4.val();		
				var respuestaHidrobiologico = ejecutarFormula(listaVariablesInternaHidrobiologico,formulaHidrobiologico);
				if(respuestaHidrobiologico != ConstanteServices.VARIABLES_INEXISTENTES && respuestaHidrobiologico != ConstanteServices.EXPRESION_INVALIDA && respuestaHidrobiologico != null){
					var numResultadoFinal = cortarDecimalRedondear(respuestaHidrobiologico,4);
					$("#divResultado_"+index).val(""+numResultadoFinal.replace(".0000",""));
				}else{
					$("#divResultado_"+index).val(""+respuestaHidrobiologico);
				}
			}
		}else if(idTipoConteo == ConstanteServices.ID_TIPO_CONTEO_TOTAL){
			$("#divResultado_"+index).val(""+numConteo);
		}
	}
	completarLtaRegistroHidrobiologico();
}

function eliminarSubParametroNuevoHidrobiologico(index){
	guardarTablaAntesCambiosHidrobiologico();
	tblResultadoRegistroHidrobiologico.bootstrapTable('remove', {field: 'indice', values: [parseInt(index)]});
	formatearInputHidrobiologico();
	flagHidrobiologico = 0;
	mostrarMensaje();
	visibleAnalistaHidrobiologico();
}


function verificarParametroAgregarHidrobiologico(){
	mostrarMensaje();
	if(flagHidrobiologico == 0){
		var ltaGrilla = tblResultadoRegistroHidrobiologico.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(ltaClonSubParametroTodo[0]);
				objetoClon.indice = countRow;
				objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,countRow,null);
				objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(ltaClonSubParametroTodo[0].idSubParametro),countRow,null);
				objetoClon.nombreAnalista = crearHtmlAnalistaNuevo(countRow,null);
				if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
					objetoClon.numConteo = crearHtmlConteoNuevo(countRow,null);
					objetoClon.numCantidad = crearHtmlCantidadNuevo(countRow,null);
					objetoClon.descripcionMicroorganismo = crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(ltaClonSubParametroTodo[0].idSubParametro),countRow,null);
					objetoClon.descripcionTipoConteo = crearHtmlComboTipoConteoNuevo(countRow,null);
					objetoClon.numResultado = crearHtmlResultadoNuevo(countRow, null);
					objetoClon.descripcionObservacion = crearHtmlObservacionNuevo(countRow, null);
				}
				guardarTablaAntesCambiosHidrobiologico();
				tblResultadoRegistroHidrobiologico.bootstrapTable('insertRow', {
		            index: countRow,
		            row: objetoClon
		        });
				var accionGuardar = tblResultadoRegistroHidrobiologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnGuardarRegistroHidrobiologico');
		        var accionEditar = tblResultadoRegistroHidrobiologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnEditarRegistroHidrobiologico');
		        var accionAnular = tblResultadoRegistroHidrobiologico.find('tr').eq(countRow+1).find('td').eq(1).find('#btnAnularRegistroHidrobiologico');
		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
		        validacionResultado(countRow);
		        formatearInputHidrobiologico();
		        flagHidrobiologico = 1;
		        visibleAnalistaHidrobiologico();
		        actualizarIndicesHidrobiologico();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputHidrobiologico();
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
	var descripcionMicroorganismo = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(6);
	descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(index,value,idSubParametro));
	
	calcularValoresHidrobiologico(index);
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

function crearHtmlComboTipoConteoNuevo(index, value){
	var htmlCombo = '<select class="form-control input-sm" id="cboTipoConteo_'+index+'" onchange="calcularValoresHidrobiologico('+index+')" style="width: 105px !important">';
	for (var i = 0; i < ltaTipoConteo.length; i++) {
		if(ltaTipoConteo[i].idDetalleGeneral == value){
			htmlCombo += '<option selected="selected" value = "'+ltaTipoConteo[i].idDetalleGeneral+'">'+ltaTipoConteo[i].descripcion+'</option>';
		}else{
			htmlCombo += '<option value = "'+ltaTipoConteo[i].idDetalleGeneral+'">'+ltaTipoConteo[i].descripcion+'</option>';
		}
	}
	htmlCombo 	  +='</select>';			
	return htmlCombo;
}

function crearHtmlCantidadNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 60px !important;" />';
	}else{
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" value = "'+value+'" class="form-control input-sm cantidad" style="width: 60px !important;" />';
	}
}

function crearHtmlConteoNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divConteo_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" class="form-control input-sm conteo" style="width: 60px !important;" />';
	}else{
		return '<input type="text"  id="divConteo_'+index+'" onkeyup="calcularValoresHidrobiologico('+index+')" value = "'+value+'" class="form-control input-sm conteo" style="width: 60px !important;" />';
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

function verificarDataGrabarHidrobiologico(){
	if(flagHidrobiologico == 0){	
		var lta = completarLtaRegistroHidrobiologico();
		mostrarMensaje();
		if(validarDataTabla(lta)){	
			var tituloModal = 'Registro Hidrobiologico';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroHidrobiologico()', '', tituloModal);
		}	
	}
}

function validarDataTabla(listaFinal){
	var indicador = true;
	for(var i = 0 ; i < listaFinal.length ; i++){
		if(IsEmpty(listaFinal[i].idAnalista)){
			mostrarMensaje('Debe Seleccionar una Analista en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}	
		if(IsEmpty(listaFinal[i].fechaMonitoreoString)){
			mostrarMensaje('Debe Ingresar una Fecha Monitoreo en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].fechaRegDatoString)){
			mostrarMensaje('Debe Ingresar una Fecha Reg Dato en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}		
		if(IsEmpty(listaFinal[i].idSubParametro)){
			mostrarMensaje('Debe Seleccionar un SubParametro en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idMicroorganismo)){
			mostrarMensaje('Debe Seleccionar un Microorganismo en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idTipoConteo)){
			mostrarMensaje('Debe Seleccionar un Tipo de Conteo en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numCantidad) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar una Cantidad en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(""+listaFinal[i].numConteo) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Conteo en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}		
	}
	return indicador
}

function completarLtaRegistroHidrobiologico(){
	var listaFinal = tblResultadoRegistroHidrobiologico.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
		var idMicroorganismo = $("#cboMicroorganismo_"+i).val();
		if(idMicroorganismo != null && idMicroorganismo != undefined){
			listaFinal[i].idMicroorganismo  = parseInt(idMicroorganismo);
		}else{
			listaFinal[i].idMicroorganismo  = null;
		}
		var idTipoConteo = $("#cboTipoConteo_"+i).val();
		if(idTipoConteo != null && idTipoConteo != undefined){
			listaFinal[i].idTipoConteo  = parseInt(idTipoConteo);
		}else{
			listaFinal[i].idTipoConteo  = null;
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
		var numConteo = $("#divConteo_"+i).val();
		if(numConteo != null && numConteo != undefined){
			if(!IsEmpty(numConteo)){
				listaFinal[i].numConteo  = numConteo;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numConteo  = ConstanteServices.VALOR_DEFECTO;
			}
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
		
		if(idTipoConteo == ConstanteServices.ID_TIPO_CONTEO_CAMPO){
			if(listaFinal[i].idSubParametro == ConstanteServices.ID_SUBPARAMETRO_FITO){
				var itemFormula = JSON.parse(txtFormulaHidrobiologico1.attr("valor"));
				if(itemFormula != null && itemFormula != undefined ){
					listaFinal[i].idFormula  = itemFormula.idFormulaxParametro;
				}
			}else{
				var itemFormula = JSON.parse(txtFormulaHidrobiologico2.attr("valor"));
				if(itemFormula != null && itemFormula != undefined ){
					listaFinal[i].idFormula  = itemFormula.idFormulaxParametro;
				}
			}
		}else if(idTipoConteo == ConstanteServices.ID_TIPO_CONTEO_FRANJA){
			if(listaFinal[i].idSubParametro == ConstanteServices.ID_SUBPARAMETRO_FITO){
				var itemFormula = JSON.parse(txtFormulaHidrobiologico3.attr("valor"));
				if(itemFormula != null && itemFormula != undefined ){
					listaFinal[i].idFormula  = itemFormula.idFormulaxParametro;
				}
			}else{
				var itemFormula = JSON.parse(txtFormulaHidrobiologico4.attr("valor"));
				if(itemFormula != null && itemFormula != undefined ){
					listaFinal[i].idFormula  = itemFormula.idFormulaxParametro;
				}
			}
		}else{
			listaFinal[i].idFormula = ConstanteServices.VALOR_DEFECTO;
		}
	}
	return listaFinal;
}

function grabarDatosRegistroHidrobiologico(){
	var lta = completarLtaRegistroHidrobiologico();
	$.ajax({
        url : "./grabarDatosRegistroHidrobiologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroHidrobiologico : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroHidrobiologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroHidrobiologico").hide();
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);  
        	$("#lblTitulo").text("Registro Hidrobiologico - N° Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionHidrobiologico);
        	observacionHidrobiologico = respuestaBean.parametros.observacionHidrobiologico;
        	validacionBotonHidrobiologico();
        	buscarDatosRegistroHidrobiologico();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroHidrobiologico(){
	$.ajax({
        url : "./buscarDatosRegistroHidrobiologico",
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
        		tblResultadoRegistroHidrobiologico.bootstrapTable('load', JSON.parse(ListaResultado));
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		visibleAnalistaHidrobiologico();
        	}else{
            	tblResultadoRegistroHidrobiologico.bootstrapTable('load', []);
            	visibleAnalistaHidrobiologico();
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputHidrobiologico();
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
	
	var numCantidad = $("#divCantidad_"+index).val();
	if(IsEmpty(numCantidad)){
		mostrarMensaje('Debe Ingresar una Nro Franja/Campo', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numConteo = $("#divConteo_"+index).val();
	if(IsEmpty(numConteo)){
		mostrarMensaje('Debe Ingresar un Nro Organismo', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var idMicroorganismoNew = $("#cboMicroorganismo_"+index).val();
	if(IsEmpty(idMicroorganismoNew)){
		mostrarMensaje('Debe Seleccionar un Microorganismo', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var idTipoConteoNew = $("#cboTipoConteo_"+index).val();
	if(IsEmpty(idTipoConteoNew)){
		mostrarMensaje('Debe Seleccionar un Tipo de Conteo', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	var numResultado = $("#divResultado_"+index).val();
	if(IsEmpty(numResultado)){
		mostrarMensaje('Debe Ingresar un Resultado.', ConstanteServices.IMAGEN_DANGER);
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

function grabarItemRegistroHidrobiologico(item){
	$.ajax({
        url : "./grabarItemRegistroHidrobiologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	registroHidrobiologicoBean : JSON.stringify(item),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	flagHidrobiologico = 0;
        	buscarDatosRegistroHidrobiologico();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroHidrobiologico(idRegHidrobiologico){
	$.ajax({
        url : "./eliminarItemRegistroHidrobiologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroHidrobiologico : idRegHidrobiologico
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagHidrobiologico = 0;
        	buscarDatosRegistroHidrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarParametroEditarNuevoHidrobiologico(index,row){
	mostrarMensaje();
	if(flagHidrobiologico == 0){
		var ltaGrilla = tblResultadoRegistroHidrobiologico.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(row);
				var accionGuardar = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(1).find('#btnGuardarRegistroHidrobiologico');
		        var accionEditar = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(1).find('#btnEditarRegistroHidrobiologico');
		        var accionAnular = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(1).find('#btnAnularRegistroHidrobiologico');
		        var accionRegresar = tblResultadoRegistroHidrobiologico.find('tr').eq(index + 1).find('td').eq(1).find('#btnRegresarRegistroHidrobiologico');
		        
				var nombreAnalista = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(3);
		        var descripcionSubparametro = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(4);
				var descripcionPuntoMuestra = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(5);
				var descripcionMicroorganismo = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(6);
				var numConteo = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(7);
				var numCantidad = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(8);
				var descripcionTipoConteo = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(9);
				var numResultado = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(10);
				var descripcionObservacion = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(11);
		        		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
								
		        descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
		        descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(row.idSubParametro),index,row.idPuntoMuestra));
		        console.log(row);
		        
		        if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		        	nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));
					descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(row.idSubParametro),index,row.idMicroorganismo));
		        	descripcionTipoConteo.html(""+crearHtmlComboTipoConteoNuevo(index,row.idTipoConteo));
					numCantidad.html(""+crearHtmlCantidadNuevo(index, row.numCantidad));
					numConteo.html(""+crearHtmlConteoNuevo(index, row.numConteo));
					numResultado.html(""+crearHtmlResultadoNuevo(index, row.numResultado));
					descripcionObservacion.html(""+crearHtmlObservacionNuevo(index, row.descripcionObservacion));
					accionRegresar[0].style.display = 'inherit';
		        }
		        validacionResultadoEditar(index);
		        calcularValoresHidrobiologico(index);
		        formatearInputHidrobiologico();
		        flagHidrobiologico = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputHidrobiologico();
}

function verificarAprobacionHidrobiologico(){
	if(flagHidrobiologico == 0){
		var tituloModal = 'Aprobación Registro Hidrobiologico';
		modal.confirmer('¿Esta seguro de Aprobar Registro Hidrobiologico ?', 'aprobarRegistroHidrobiologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroHidrobiologico(){
	$.ajax({
        url : "./aprobarRegistroHidrobiologico",
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
    		$("#btnAprobarRegistroHidrobiologico").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionHidrobiologico = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonHidrobiologico();
        	tblResultadoRegistroHidrobiologico.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroHidrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroHidrobiologico(){
	if(flagHidrobiologico == 0){
		var tituloModal = 'Habilitación Registro Hidrobiologico';
		modal.confirmer('¿Esta seguro de Habilitar Registro Hidrobiologico ?', 'habilitarRegistroHidrobiologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroHidrobiologico(){
	$.ajax({
        url : "./aprobarRegistroHidrobiologico",
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
    		$("#btnAprobarRegistroHidrobiologico").hide();
        	mostrarMensaje('Se Habilitó Registo Hidrobiologico Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionHidrobiologico = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonHidrobiologico();
        	buscarDatosRegistroHidrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function guardarTablaAntesCambiosHidrobiologico(){
	var lista = completarLtaRegistroHidrobiologico();
	tblResultadoRegistroHidrobiologico.bootstrapTable('load', lista);
	visibleAnalistaHidrobiologico();
}

function verificarEliminarMultipleHidrobiologico(){
	if(flagHidrobiologico == 0){
		var seleccionados = tblResultadoRegistroHidrobiologico.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminación Múltiple Hidrobiologico';
   			modal.confirmer('¿Esta seguro de Proceder con la '+tituloModal+' ?', 'eliminarMultipleRegistroHidrobiologico()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function eliminarMultipleRegistroHidrobiologico(){
	var seleccionados = tblResultadoRegistroHidrobiologico.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			eliminarSubParametroNuevoHidrobiologico(seleccionados[i].indice);
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		eliminarVariosItemRegistroHidrobiologico(JSON.stringify(seleccionados));
	}
}

function actualizarIndicesHidrobiologico(){
	var data = tblResultadoRegistroHidrobiologico.bootstrapTable("getData");
	for(var i=0; i<data.length; i++){
		data[i].indice = i+1;
	}
}

function eliminarVariosItemRegistroHidrobiologico(ltaRegistroHidrobiologico){
	$.ajax({
        url : "./eliminarVariosItemRegistroHidrobiologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	ltaRegistroHidrobiologico : ltaRegistroHidrobiologico
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagHidrobiologico = 0;
        	buscarDatosRegistroHidrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaHidrobiologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarListaMicro(idSubParametro){
	$.ajax({
        url : "./buscarListaMicroHidro",
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
        		ltaMicroorganismoSubPara = [];
		        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaVariablesHidrobiologico(){
	tblVariables1Hidrobiologico.bootstrapTable({
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
	
	tblVariables2Hidrobiologico.bootstrapTable({
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
	
	tblVariables3Hidrobiologico.bootstrapTable({
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
	
	tblVariables4Hidrobiologico.bootstrapTable({
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

function buscarVariableHidrobiologico(ListVariables) {
	if(ListVariables != null && ListVariables.length > 0) {
		var indice = Math.ceil(ListVariables.length/4);
		var listaAux = [];
		for(i = 0; i < indice; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables1Hidrobiologico.bootstrapTable('load', listaAux);
		
		var listaAux = [];
		for(i = indice; i < (indice*2); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables2Hidrobiologico.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*2); i < (indice*3); i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables3Hidrobiologico.bootstrapTable('load', listaAux);
		var listaAux = [];
		for(i = (indice*3); i < ListVariables.length; i++){
			listaAux.push(ListVariables[i]);
		}
		tblVariables4Hidrobiologico.bootstrapTable('load', listaAux);
	} else {
		tblVariables1Hidrobiologico.bootstrapTable('load', []);
		tblVariables2Hidrobiologico.bootstrapTable('load', []);
		tblVariables3Hidrobiologico.bootstrapTable('load', []);
		tblVariables4Hidrobiologico.bootstrapTable('load', []);	            		
	}
}

function buscarFormulasPrincipalesHidrobiologico() {
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
        				txtFormulaHidrobiologico1.val(""+lista[0].combinacionFormula);
        				txtFormulaHidrobiologico1.attr("valor",JSON.stringify(lista[0]));
					}
        			if(lista.length > 1){
        				txtFormulaHidrobiologico2.val(""+lista[1].combinacionFormula);
        				txtFormulaHidrobiologico2.attr("valor",JSON.stringify(lista[1]));
					}
        			if(lista.length > 2){
        				txtFormulaHidrobiologico3.val(""+lista[2].combinacionFormula);
        				txtFormulaHidrobiologico3.attr("valor",JSON.stringify(lista[2]));
					}
        			if(lista.length > 3){
        				txtFormulaHidrobiologico4.val(""+lista[3].combinacionFormula);
        				txtFormulaHidrobiologico4.attr("valor",JSON.stringify(lista[3]));
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
	var descripcionPuntoMuestra = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(5);
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
	var descripcionMicroorganismo = tblResultadoRegistroHidrobiologico.find('tr').eq(index+1).find('td').eq(6);
	descripcionMicroorganismo.html(""+crearHtmlComboMicroorganismoNuevo(obtenerListaMicroorganismo(idSubParametro),index,null));
}

function validarNuevoRegistroNuevoHidrobiologico(objeto){
	var indicador = true;	
	
	if(IsEmpty(objeto.idPuntoMuestra)){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	if(IsEmpty(objeto.idSubParametro)){
		mostrarMensaje('Debe Seleccionar un Sub Parámetro.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	return indicador;
}

/*funcion grabar observacion de parametro Hidrobiologico*/
function guardarObservacionHidrobiologico(){
	$.ajax({
        url : "./guardarObservacionHidrobiologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroHidrobiologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonHidrobiologico();
        	observacionHidrobiologico = respuestaBean.parametros.observacionHidrobiologico;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionHidrobiologico(){
	var tituloModal = 'Observacion Registro Hidrobiológico';
	modal.confirmer('¿Esta seguro de Grabar la Observación del Registro?', 'guardarObservacionHidrobiologico()', 'regresarObservacionHidrobiologico()', tituloModal);
}

function regresarObservacionHidrobiologico(){
	txtObservacionRegistro.val(""+observacionHidrobiologico);
}
/**/

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divConteo'){
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
        	var newId = "divConteo_"+rowNo;
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
