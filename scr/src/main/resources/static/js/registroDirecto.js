$(document).ready(function() {
			inicializarVariablesDirecto();
			cargarComponentesDirecto();
});
var accion = false;
var flagDirecto = 0;
var flagVerAnalistaDirecto = 0;
var nombreAnalistaDirecto = "nombreAnalistaDirecto";
function inicializarVariablesDirecto() {
	var tblResultadoRegistroDirecto = null;
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 
	console.log("directo");
}

function cargarComponentesDirecto() {	
	accordion();
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	
	if(ltaRegDirecto.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else {
		$("#fechaMonitoreo").text(ltaRegDirecto[0].fechaMonitoreoString);
	}
	
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Directo - N° Registro "+id);
	}
	
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroDirecto").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}	
	
	tblResultadoRegistroDirecto = $("#tblResultadoRegistroDirecto");

	cargarGrillaResultadoDirecto(ltaRegDirecto);
		
	$('#btnAdjuntarRegDirecto').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionDirecto);
    });
		
	$('#btnNuevoRegDirecto').click(function() {
		if(estadoAprobacionDirecto != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregar();
		}
    });
	
	$('#btnGuardarTodoRegistroDirecto').click(function() {
		mostrarMensaje();
		if(flagDirecto == 0){
			verificarDataGrabar();
		}else{
			mostrarMensaje('Debe Guardar para Grabar.', ConstanteServices.IMAGEN_DANGER);
		}		
    });
	
	$('#btnBorrarMultipleRegistroDirecto').click(function() {
		mostrarMensaje();
		if(flagDirecto == 0){
			verificarEliminarMultipleDirecto();
		}else{
			mostrarMensaje('Debe Guardar para Continuar.', ConstanteServices.IMAGEN_DANGER);
		}			
    });
	
	$('#btnAprobarRegistroDirecto').click(function() {
		mostrarMensaje();
		if(flagDirecto == 0){
			verificarAprobacionDirecto();
		}else{
			mostrarMensaje('Debe Guardar para Aprobar.', ConstanteServices.IMAGEN_DANGER);
		}		
    });	
	
	$('#btnHabilitarRegistroDirecto').click(function() {
		verificarHabilitarRegistroDirecto();
    });	
	
	$('#btnVerAnalistaRegDirecto').click(function() {
		if(flagVerAnalistaDirecto == 0){
			flagVerAnalistaDirecto = 1;
			$("#btnVerAnalistaRegDirecto").text("Ocultar Analista");
		}else{
			flagVerAnalistaDirecto = 0;
			$("#btnVerAnalistaRegDirecto").text("Ver Analista");
		}
		visibleAnalistaDirecto();			
    });
	
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionDirecto();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionDirecto();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});

	validacionBotonDirecto();	
	formatearInputDirecto();
	validarPerfilesDirecto();
	
}
function visibleAnalistaDirecto(){
	var a  = document.getElementsByClassName(nombreAnalistaDirecto);
	for(var i = 0 ; i < a.length;i++){
		if(flagVerAnalistaDirecto == 1){
			 a[i].className = ""+nombreAnalistaDirecto;	
		}else{
			 a[i].className = "hidden "+nombreAnalistaDirecto;	
		}
	}
}

function validarPerfilesDirecto(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#btnVerAnalistaRegDirecto").show();
		$("#btnHabilitarRegistroDirecto").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#btnVerAnalistaRegDirecto").hide();
		$("#btnAprobarRegistroDirecto").hide();
		$("#btnHabilitarRegistroDirecto").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'checkbox');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroDirecto").hide();
		$("#btnAprobarRegistroDirecto").hide();
		$("#btnVerAnalistaRegDirecto").hide();
		$("#btnNuevoRegDirecto").hide();
		$("#btnBorrarMultipleRegistroDirecto").hide();
		$("#btnAdjuntarRegDirecto").hide();
	}else{		
		tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroDirecto").hide();
		$("#btnAprobarRegistroDirecto").hide();
		$("#btnHabilitarRegistroDirecto").hide();
		$("#btnVerAnalistaRegDirecto").hide();
		$("#btnNuevoRegDirecto").hide();
	}
}

function validacionBotonDirecto(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionDirecto != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroDirecto").show();
		}else{
			$("#btnAprobarRegistroDirecto").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionDirecto == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroDirecto").show();
		}else{
			$("#btnHabilitarRegistroDirecto").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroDirecto").hide();
		$("#btnHabilitarRegistroDirecto").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionDirecto == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'checkbox');
		$("#btnBorrarMultipleRegistroDirecto").hide();
	}
}


function formatearInputDirecto(){
	ponerClassConjunto("inputDecimal","valor",0,6,2);
	ponerClassConjunto("inputNumerico","profundida",3,0,0);
	ponerClassConjunto("inputDecimal","factor",0,4,2);
}


function cargarGrillaResultadoDirecto(lista){
	tblResultadoRegistroDirecto.bootstrapTable("destroy")
	var claseValidador = "";	
	var claseVerAnalista = "nombreAnalistaDirecto";
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	} 
	if(flagVerAnalistaDirecto != 1){
		claseVerAnalista = "hidden nombreAnalistaDirecto";
	}
	
	tblResultadoRegistroDirecto.bootstrapTable({
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
			formatter: 'crearAccionesRegistroDirecto',
			class: "controls",
			events : operateEventsDirecto,
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
		}
		,{	
			field : 'descripcionSubparametro',
			title : 'Sub Parámetro.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false
		},{	
			field : 'descripcionPuntoMuestra',
			title : 'Punto de Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false
		},{	
			field : 'numValor',
			title : 'Valor.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlValor',
			cellStyle : estiloAcciones65
		},{	
			field : 'numProfundida',
			title : 'Profundida.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlProfundida',
			cellStyle : estiloAcciones40
		},{	
			field : 'numFactor',
			title : 'Factor.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlFactor',
			cellStyle : estiloAcciones55
		},{	
			field : 'numValorDQO',
			title : 'Resultado.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			class : claseValidador,
			formatter : 'crearHtmlResultado',
			cellStyle : verificarEstiloColor
		}]
	});
}


function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearAccionesRegistroDirecto(value, row, index) {			  
	   return [
			'<button id="btnGuardarRegistroDirecto" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroDirecto" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroDirecto" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroDirecto" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
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

function crearHtmlValor(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numValor)){
			htmlimput = '<input type="text" id="divValor_'+index+'" value="" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm valor" style="width: 65px !important;" />';
		}else{
			htmlimput = '<input type="text" id="divValor_'+index+'" value="'+row.numValor+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm valor" style="width: 65px !important;" />';
		}		
		return [htmlimput].join('');
	}else{
		return [''+row.numValor].join('');
	}
}

function crearHtmlFechaMonitoreo(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = '<div class="input-group date fechaMonitoreo" style="width: 130px !important;" id="datetimepickerMonitoreo_'+index+'">';
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
		var htmlimput = '<div class="input-group date fechaRegDato" style="width: 130px !important;" id="datetimepickerRegDato_'+index+'">';
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

function crearHtmlProfundida(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numProfundida)){
			htmlimput = '<input type="text"  id="divProfundida_'+index+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm profundida" value = "30" style="width: 43px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divProfundida_'+index+'" value="'+row.numProfundida+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm profundida" value = "30" style="width: 43px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		return [''+row.numProfundida].join('');
	}
}

function crearHtmlFactor(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA && row.indicadorFactor == ConstanteServices.INDICADOR_FACTOR) {
		var htmlimput = "";
		if(IsEmpty(row.numFactor)){
			htmlimput = '<input type="text" id="divFactor_'+index+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm factor" value = "1" style="width: 55px !important;" />';
		}else{
			htmlimput = '<input type="text" id="divFactor_'+index+'" value="'+row.numFactor+'"  onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm factor" style="width: 55px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numFactor != null && row.indicadorFactor == 1){
			return [''+row.numFactor].join('');
		}else{
			return [''].join('');
		}		
	}
}

function crearHtmlResultado(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numValorDQO)){
			htmlimput = '<input type="text" id="divResultado_'+index+'" readonly = "readonly" class="form-control input-sm resultado" style="width: 80px !important;" />';
		}else{
			htmlimput = '<input type="text" id="divResultado_'+index+'" value="'+row.numValorDQO+'" readonly = "readonly" class="form-control input-sm resultado" style="width: 80px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.numValorDQO != null){
			return [''+row.numValorDQO].join('');
		}else{
			return [''].join('');
		}
	}
}

function calcularValoresDirecto(index){
	var numValor = $("#divValor_"+index).val();
	var numFactor = $("#divFactor_"+index).val();
	if(IsEmpty(numFactor)){
		$("#divResultado_"+index).val(""+numValor);
	}else{
		var calculado = numValor * numFactor;
		$("#divResultado_"+index).val(""+calculado);
	}
}


function eliminarSubParametroNuevo(idIndice){	
	guardarTablaAntesCambios();
	tblResultadoRegistroDirecto.bootstrapTable('remove', {field: 'indice', values: [parseInt(idIndice)]});
	formatearInputDirecto();
	flagDirecto = 0;
	mostrarMensaje();
	visibleAnalistaDirecto();
}

function verificarParametroAgregar(){
	mostrarMensaje();
	if(flagDirecto == 0){
		var ltaGrilla = tblResultadoRegistroDirecto.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(ltaClonSubParametroTodo[0]);
				objetoClon.indice = countRow;
				objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,countRow,null);
				objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(ltaClonSubParametroTodo[0].idSubParametro),countRow,null);
				objetoClon.nombreAnalista = crearHtmlAnalistaNuevo(countRow,null);
				if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
					objetoClon.numValor  = crearHtmlValorNuevo(countRow,null);
					objetoClon.numProfundida = crearHtmlProfundidaNuevo(countRow,null);
					objetoClon.numValorDQO = crearHtmlResultadoNuevo(countRow,null);
				}
				guardarTablaAntesCambios();
				tblResultadoRegistroDirecto.bootstrapTable('insertRow', {
		            index: countRow,
		            row: objetoClon
		        });
				var accionGuardar = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(countRow+1)).find('td').eq(1).find('#btnGuardarRegistroDirecto');
		        var accionEditar = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(countRow+1)).find('td').eq(1).find('#btnEditarRegistroDirecto');
		        var accionAnular = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(countRow+1)).find('td').eq(1).find('#btnAnularRegistroDirecto');
		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
		        validacionResultado(countRow);
		        formatearInputDirecto();
		        flagDirecto = 1;
		        visibleAnalistaDirecto();
		        actualizarIndicesDirecto();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputDirecto();
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

function crearHtmlComboSubParametroNuevo(ltaSubPara,index,value){
	var htmlComboSubParametro = '<select class="form-control input-sm subParametro" id="divSubParametro_'+index+'" onchange="validacionResultado('+index+'); crearPuntoMuestra('+index+')" >';
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

function crearHtmlValorNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text" id="divValor_'+index+'" onfocusout = "calcularValoresDirecto('+index+')"  onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm valor" style="width: 65px !important;" />';	
	}else{
		return '<input type="text" id="divValor_'+index+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" value = "'+value+'" class="form-control input-sm valor" style="width: 65px !important;" />';	
	}	
}

function crearHtmlProfundidaNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divProfundida_'+index+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm profundida" value = "30" style="width: 43px !important;" />';
	}else{
		return '<input type="text"  id="divProfundida_'+index+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" value = "'+value+'" class="form-control input-sm profundida" value = "30" style="width: 43px !important;" />';
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
	var htmlimput = '<div class="input-group date fechaMonitoreo" style="width: 130px !important;" id="datetimepickerMonitoreo_'+index+'">';
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
	var htmlimput = '<div class="input-group date fechaRegDato" style="width: 130px !important;" id="datetimepickerRegDato_'+index+'">';
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
	var idSubPCombo = $("#divSubParametro_"+index).val();
	var htmlRow = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(8);
	for(var i = 0 ; i < ltaSubParametrosTodo.length ; i ++){
		if(ltaSubParametrosTodo[i].idSubParametro == idSubPCombo){
			var objeto = clonarObjeto(ltaSubParametrosTodo[i]);
			if(objeto.indicadorFactor != null && objeto.indicadorFactor == ConstanteServices.INDICADOR_FACTOR) {
				var valor = htmlRow[0].textContent;
				if(!IsEmpty(valor)){
					var htmlimput = '<input type="text" id="divFactor_'+index+'" value="'+valor+'" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm factor" style="width: 55px !important;" />';	
				}else{
					var htmlimput = '<input type="text" id="divFactor_'+index+'" value="1" onfocusout = "calcularValoresDirecto('+index+')" onkeyup="calcularValoresDirecto('+index+')" class="form-control input-sm factor" style="width: 55px !important;" />';
				}
				htmlRow.html(htmlimput);
				formatearInputDirecto();
			}else{
				htmlRow.html("");
			}
			break;
		}
	}	
}

function posicionIndexHtml(index){
	return index;
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

function verificarDataGrabar(){
	if(flagDirecto == 0){
		var lta = completarLtaRegistroDirecto();
		mostrarMensaje();
		if(validarDataTabla(lta)){	
			var tituloModal = 'Registro Directo';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroDirecto()', '', tituloModal);
		}	
	}
}

function confirmacionGuardarObservacionDirecto(){
	var tituloModal = 'Observacion Registro Directo';
	modal.confirmer('¿Esta seguro de Grabar la Observación del Registro?', 'guardarObservacionDirecto()', 'regresarObservacionDirecto()', tituloModal);
}

function regresarObservacionDirecto(){
	txtObservacionRegistro.val(""+observacionDirecto);
}

function validarDataTabla(listaFinal){
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
		if(IsEmpty(""+listaFinal[i].numValor) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar un Numero Valor en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		
		if(IsEmpty(""+listaFinal[i].numProfundida) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
			mostrarMensaje('Debe Ingresar una Profundida en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		
		if(!IsEmpty(listaFinal[i].indicadorFactor) && listaFinal[i].indicadorFactor == ConstanteServices.INDICADOR_FACTOR ){
			if(IsEmpty(""+listaFinal[i].numFactor) && modo == ConstanteServices.ESTADO_OPCION_EDITAR){
				mostrarMensaje('Debe Ingresar un Factor en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
				indicador = false;
				return indicador;
			}
		}
		
		if(listaFinal[i].numValorDQO == ConstanteServices.VARIABLES_INEXISTENTES || listaFinal[i].numValorDQO == ConstanteServices.EXPRESION_INVALIDA || IsEmpty(""+listaFinal[i].numValorDQO)){
			if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
				mostrarMensaje('El DBO5 Total es Erróneo!', ConstanteServices.IMAGEN_DANGER);
				indicador = false;
				return indicador;
			}
		}
	}
	return indicador
}

function completarLtaRegistroDirecto(){
	var listaFinal = tblResultadoRegistroDirecto.bootstrapTable("getData");
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
		
		var numValor = $("#divValor_"+i).val();
		if(numValor != null && numValor != undefined){
			if(!IsEmpty(numValor)){
				listaFinal[i].numValor  = numValor;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numValor  = ConstanteServices.VALOR_DEFECTO;
			}		
		}
		if(listaFinal[i].numValor == Infinity || listaFinal[i].numValor == -Infinity || isNaN(listaFinal[i].numValor)){
			listaFinal[i].numValor = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numProfundida = $("#divProfundida_"+i).val();
		if(numProfundida != null && numProfundida != undefined){
			if(!IsEmpty(numProfundida)){
				listaFinal[i].numProfundida  = numProfundida;
			}else if(modo != ConstanteServices.ESTADO_OPCION_EDITAR){
				listaFinal[i].numProfundida  = ConstanteServices.VALOR_DEFECTO;
			}			
		}		
		if(listaFinal[i].numProfundida == Infinity || listaFinal[i].numProfundida == -Infinity || isNaN(listaFinal[i].numProfundida)){
			listaFinal[i].numProfundida = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numFactor = $("#divFactor_"+i).val();
		if(numFactor != null && numFactor != undefined){
			if(!IsEmpty(numFactor)){
				listaFinal[i].numFactor = numFactor;
			}else{
				listaFinal[i].numFactor  = ConstanteServices.VALOR_DEFECTO;
			}
		}else{
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				listaFinal[i].numFactor = null;
			}			
		}
		if(listaFinal[i].numFactor == Infinity || listaFinal[i].numFactor == -Infinity || isNaN(listaFinal[i].numFactor)){
			listaFinal[i].numFactor = ConstanteServices.VALOR_DEFECTO;
		}
		
		var numValorDQO = $("#divResultado_"+i).val();
		if(numValorDQO != null && numValorDQO != undefined){
			if(!IsEmpty(numValorDQO)){
				listaFinal[i].numValorDQO = numValorDQO;
			}else{
				listaFinal[i].numValorDQO  = ConstanteServices.VALOR_DEFECTO;
			}			
		}
		if(listaFinal[i].numValorDQO == Infinity || listaFinal[i].numValorDQO == -Infinity || isNaN(listaFinal[i].numValorDQO)){
			listaFinal[i].numValorDQO = ConstanteServices.VALOR_DEFECTO;
		}
		
	}
	return listaFinal;
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
		
	var numValorNew = $("#divValor_"+index).val();
	if(IsEmpty(numValorNew)){
		mostrarMensaje('Debe Ingresar un Numero Valor.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numProfundidaNew = $("#divProfundida_"+index).val();
	if(IsEmpty(numProfundidaNew)){
		mostrarMensaje('Debe Ingresar una Profundida.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	var numFactorNew = $("#divFactor_"+index).val();	
	var indicad = buscarIndicadorFactor(idSubPNew);
	if(indicad == ConstanteServices.INDICADOR_FACTOR){	
		if(IsEmpty(numFactorNew)){
			mostrarMensaje('Debe Ingresar un Factor.', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return indicador;
}


function buscarIndicadorFactor(idSubParamet){
	var indica = null;
	for(var i = 0 ; i < ltaSubParametrosTodo.length ; i ++){
		if(ltaSubParametrosTodo[i].idSubParametro == parseInt(idSubParamet)){
			indica = ltaSubParametrosTodo[i].indicadorFactor;
			break;
		}
	}
	return indica;
}
	
function validarNuevoRegistroNuevoDirecto(objeto){
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

function verificarParametroEditarNuevo(index,row){
	mostrarMensaje();
	if(flagDirecto == 0){
		var ltaGrilla = tblResultadoRegistroDirecto.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
				var objetoClon = clonarObjeto(row);
				var accionGuardar = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(1).find('#btnGuardarRegistroDirecto');
		        var accionEditar = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(1).find('#btnEditarRegistroDirecto');
		        var accionAnular = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(1).find('#btnAnularRegistroDirecto');
		        var accionRegresar = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index + 1)).find('td').eq(1).find('#btnRegresarRegistroDirecto');
		        
		        
				var nombreAnalista = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(3);
		        var descripcionSubparametro = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(4);
				var descripcionPuntoMuestra = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(5);
				var numValor  = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(6);
				var numProfundida = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(7);
				var numFactor = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(8);
				var numValorDQO = tblResultadoRegistroDirecto.find('tr').eq(posicionIndexHtml(index+1)).find('td').eq(9);
		        		        
		        accionGuardar[0].style.display = 'inherit';
		        accionEditar[0].style.display = 'none';
		        accionAnular[0].style.display = 'inherit';
			
		        descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaClonSubParametroTodo,index,row.idSubParametro));
	        	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(row.idSubParametro),index,row.idPuntoMuestra));
		        if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
					nombreAnalista.html(""+crearHtmlAnalistaNuevo(index,row.idAnalista));	
					numValor.html(""+crearHtmlValorNuevo(index,""+row.numValor));
					numProfundida.html(""+crearHtmlProfundidaNuevo(index,""+row.numProfundida));
					numValorDQO.html(""+crearHtmlResultadoNuevo(index,""+row.numValorDQO));
					validacionResultado(index);					
					accionRegresar[0].style.display = 'inherit';
		        }
		        calcularValoresDirecto(index);
		        formatearInputDirecto();
		        flagDirecto = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputDirecto();
}

function grabarItemRegistroDirecto(item){
	$.ajax({
        url : "./grabarItemRegistroDirecto",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	registroDirectoBean : JSON.stringify(item),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDirecto = 0;
        	buscarDatosRegistroDirecto();
        	//Muestra mensaje de comparación
			obtenerMensajeValoresDirecto(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroDirecto(idRegDirect){
	$.ajax({
        url : "./eliminarItemRegistroDirecto",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroDirecto : idRegDirect
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDirecto = 0;
        	buscarDatosRegistroDirecto();
        	//Muestra mensaje de comparación
			obtenerMensajeValoresDirecto(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaDirecto();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function grabarDatosRegistroDirecto(){
	var lta = completarLtaRegistroDirecto();
	$.ajax({
        url : "./grabarDatosRegistroDirecto",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroDirecto : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroDirecto : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroDirecto").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);  
        	$("#lblTitulo").text("Registro Directo - N° Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionDirecto);
        	observacionDirecto = respuestaBean.parametros.observacionDirecto;
        	validacionBotonDirecto();
        	buscarDatosRegistroDirecto();
        	//Muestra mensaje de comparación
			obtenerMensajeValoresDirecto(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroDirecto(){
	$.ajax({
        url : "./buscarDatosRegistroDirecto",
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
        		tblResultadoRegistroDirecto.bootstrapTable('load', JSON.parse(ListaResultado));		
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		visibleAnalistaDirecto();
        	}else{
            	tblResultadoRegistroDirecto.bootstrapTable('load', []);
            	visibleAnalistaDirecto();
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputDirecto();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarAprobacionDirecto(){
	if(flagDirecto == 0){
		var tituloModal = 'Aprobación Registro Directo';
		modal.confirmer('¿Esta seguro de Aprobar Registro Directo ?', 'aprobarRegistroDirecto()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroDirecto(){
	$.ajax({
        url : "./aprobarRegistroDirecto",
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
    		$("#btnAprobarRegistroDirecto").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionDirecto = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonDirecto();
        	tblResultadoRegistroDirecto.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroDirecto();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroDirecto(){
	if(flagDirecto == 0){
		var tituloModal = 'Habilitación Registro Directo';
		modal.confirmer('¿Esta seguro de Habilitar Registro Directo ?', 'habilitarRegistroDirecto()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroDirecto(){
	$.ajax({
        url : "./aprobarRegistroDirecto",
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
    		$("#btnAprobarRegistroDirecto").hide();
        	mostrarMensaje('Se Habilitó Registo Directo Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionDirecto = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonDirecto();
        	buscarDatosRegistroDirecto();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

/*funcion grabar observacion de parametro directo*/
function guardarObservacionDirecto(){
	$.ajax({
        url : "./guardarObservacionDirecto",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroDirecto : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonDirecto();
        	observacionDirecto = respuestaBean.parametros.observacionDirecto;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}
/**/


function guardarTablaAntesCambios(){
	var lista = completarLtaRegistroDirecto();
	tblResultadoRegistroDirecto.bootstrapTable('load', lista);
	visibleAnalistaDirecto();
}

function verificarEliminarMultipleDirecto(){
	if(flagDirecto == 0){
		var seleccionados = tblResultadoRegistroDirecto.bootstrapTable('getSelections');
		if(seleccionados.length != 0){	
			var tituloModal = 'Eliminación Múltiple Directo';
   			modal.confirmer('¿Esta seguro de Proceder con la '+tituloModal+' ?', 'eliminarMultipleRegistroDirecto()', '', tituloModal);
		}else{
			mostrarMensaje('Debe seleccionar registros para eliminarlos', ConstanteServices.IMAGEN_DANGER);
		}	
	}
}

function eliminarMultipleRegistroDirecto(){
	var seleccionados = tblResultadoRegistroDirecto.bootstrapTable('getSelections');
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		for(var i=0; i<seleccionados.length; i++){
			eliminarSubParametroNuevo(seleccionados[i].indice);
		}
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
		eliminarVariosItemRegistroDirecto(JSON.stringify(seleccionados));
	}
}

function actualizarIndicesDirecto(){
	var data = tblResultadoRegistroDirecto.bootstrapTable("getData");
	for(var i=0; i<data.length; i++){
		data[i].indice = i+1;
	}
}

function eliminarVariosItemRegistroDirecto(ltaRegistroDirecto){
	$.ajax({
        url : "./eliminarVariosItemRegistroDirecto",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	ltaRegistroDirecto : ltaRegistroDirecto
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDirecto = 0;
        	buscarDatosRegistroDirecto();
        	//Muestra mensaje de comparación
			obtenerMensajeValoresDirecto(parseInt($("#idRegistroLaboratorio").val()));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	visibleAnalistaDirecto();
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
	var descripcionPuntoMuestra = tblResultadoRegistroDirecto.find('tr').eq(index+1).find('td').eq(5);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(obtenerListaPuntoMuestra(idSubParametro),index,null));
}

function obtenerMensajeValoresDirecto(idRegistroLaboratorio){
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
	    		var tituloModal = 'Alerta Valores Directo - DBO';
	   			modal.open(lineaEntrada + '<br>' + lineaSalida, '', tituloModal);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });
	}
}

function personalizado(e) {
    switch (e.key) {
      case 'ArrowRight':
        var el = document.activeElement;
        var rowNo = el.id.substring(el.id.indexOf("_")+1);
        var fieldId = el.id.substring(0, el.id.indexOf("_"));
        if(fieldId == 'divValor'){
        	var newId = "divProfundida_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }
        }
        if(fieldId == 'divProfundida'){
        	var newId = "divFactor_"+rowNo;
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
		if(fieldId == 'divProfundida'){
			var newId = "divValor_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }        	
		}
		if(fieldId == 'divFactor'){
			var newId = "divProfundida_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
	        		newEl.select(); 
	        	},100);
	        }        	
        break;
		}
    }
  }


