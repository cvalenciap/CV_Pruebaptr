$(document).ready(function() {
			console.log("entra");
			inicializarVariablesMicrobiologico();
			cargarComponentesMicrobiologico();
});
var accion = false;
var flagMicrobiologico = 0;
var flagMejorValor = 1;
var resultadoEvaluacion = null;
var tipoEstado = null;

function inicializarVariablesMicrobiologico() {
	var tblResultadoRegistroMicrobiologico = null;
	var cboTipoMicrobiologico = null;
	/*inicio observacion para el parametro Microbiologico*/
	var txtObservacionRegistro = null;
	var btnEditarObservacionRegistro = null;
	var btnGuardarObservacionRegistro = null;
	var btnRegresarObservacionRegistro = null; 		
	/**/
}

function cargarComponentesMicrobiologico() {
	accordion();
	/*inicio observacion para el parametro Microbiologico*/
	txtObservacionRegistro = $('#txtObservacionRegistro');
	btnEditarObservacionRegistro = $('#btnEditarObservacionRegistro');
	btnGuardarObservacionRegistro = $('#btnGuardarObservacionRegistro');
	btnRegresarObservacionRegistro  = $('#btnRegresarObservacionRegistro');
	btnEditarObservacionRegistro.hide();
	btnGuardarObservacionRegistro.hide();
	btnRegresarObservacionRegistro.hide();
	/**/
	
	if(ltaRegMicrobiologico.length == 0){
		mostrarMensaje('No existen subparametros asociados al PTAR.', ConstanteServices.IMAGEN_DANGER);
	}else{
		$("#fechaMonitoreo").text(ltaRegMicrobiologico[0].fechaMonitoreoString);
	}
	
	cboTipoMicrobiologico = $("#cboTipoMicrobiologico");
	
	agregarListaItem(cboTipoMicrobiologico, ltaTipoMicrobiologico, "idSubParametro", "descripcionSubParametro");
	
	$("#cboTipoMicrobiologico").val(""+idTipoMicrobiologico);
	
	var id = $("#idRegistroLaboratorio").val();
	if(id != null && !IsEmpty(id)){
		$("#lblTitulo").text("Registro Microbiológico - N° Registro "+id);
	}
	if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
		$("#btnGuardarTodoRegistroMicrobiologico").hide();
		btnEditarObservacionRegistro.show();
		txtObservacionRegistro[0].disabled = true;
	}
	
	tblResultadoRegistroMicrobiologico = $("#tblResultadoRegistroMicrobiologico");
	
	cargarGrillaResultadoMicrobiologico(ltaRegMicrobiologico);
	
	/*inicio observacion para el parametro Microbiologico*/
	btnEditarObservacionRegistro.click(function(){
		txtObservacionRegistro[0].disabled = false;
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.show();
		btnRegresarObservacionRegistro.show();
	});
	
	btnGuardarObservacionRegistro.click(function(){
		confirmacionGuardarObservacionMicrobiologico();
		txtObservacionRegistro[0].disabled = true;
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
	});
	
	btnRegresarObservacionRegistro.click(function(){
		regresarObservacionMicrobiologico();
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	});
	/**/
	
	$('#cboTipoMicrobiologico').change(function() {
		validacionSeleccionTipoMicrobiologico();
    });
	
	$('#btnAdjuntarRegMicrobiologico').click(function() {
		verAdjuntosProceso($("#idRegistroLaboratorio").val(),idPtarSector,idParametro,estadoAprobacionMicrobiologico);
    });
	
	$('#btnNuevoRegMicrobiologico').click(function() {
		if(estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarParametroAgregarMicrobiologico();
		}
    });
	
	$('#btnGuardarTodoRegistroMicrobiologico').click(function() {
		if(estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			verificarDataGrabarMicrobiologico();
		}else{
			mostrarMensaje('El registro esta aprobado, solicitar habilitación', ConstanteServices.IMAGEN_DANGER);
		}
    });
	
	$('#btnAprobarRegistroMicrobiologico').click(function() {
		verificarAprobacionMicrobiologico();
    });
	
	$('#btnHabilitarRegistroMicrobiologico').click(function() {
		verificarHabilitarRegistroMicrobiologico();
    });
	
	$('#btnGrabarMejorValor').click(function() {
		if(flagMejorValor == 0){
			flagMejorValor = 1;
			$("#btnGrabarMejorValor").text("Habilitar Mejor Valor");
			grabarMejoresValores();
		}else{
			if(flagMicrobiologico == 0){
				flagMejorValor = 0;
				$("#btnGrabarMejorValor").text("Grabar Mejor Valor");
				flagMicrobiologico = 1;
			}
		}
		visibleGrabarMejorValor();
    });
	
	validacionBotonMicrobiologico();
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
		generarFilasHijasDefault();
	}
	formatearInputMicrobiologico();
	
	var currCell = $('td').first();
	
	if(ltaRegMicrobiologico.length != 0){
		bloquearCheckMejorValor();
	}
	validarPerfilesMicrobiologico();
}

function validarPerfilesMicrobiologico(){
	if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){
		$("#btnHabilitarRegistroMicrobiologico").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'mejorValor');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$('#cboTipoMicrobiologico').removeAttr('disabled');
		$("#btnGuardarTodoRegistroMicrobiologico").hide();
		$("#btnAprobarRegistroMicrobiologico").hide();
		$("#btnNuevoRegMicrobiologico").hide();
		$("#btnGrabarMejorValor").hide();
		$("#btnAdjuntarRegMicrobiologico").hide();
	}else{		
		tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'accion');
		$("#idCargaNuevaPantalla").find('input, textarea, select').attr('disabled','disabled');
		$("#btnGuardarTodoRegistroMicrobiologico").hide();
		$("#btnAprobarRegistroMicrobiologico").hide();
		$("#btnHabilitarRegistroMicrobiologico").hide();
		$("#btnNuevoRegMicrobiologico").hide();
	}
}


function validacionSeleccionTipoMicrobiologico(){
	var idTipo = $("#cboTipoMicrobiologico").val();
	if(idTipo != ConstanteServices.ID_COLIFORME_TERMOTOLERANTES && idTipo != ConstanteServices.ID_COLIFORME_TOTALES){
		var obj = {};
		obj.idPtarSector = idPtarSector;
		obj.idParametro = idParametro;
		obj.idRegistroLaboratorio = !IsEmpty($("#idRegistroLaboratorio").val())? $("#idRegistroLaboratorio").val() : null;
		obj.descripcionPtar = descripcionPtar;
		obj.descripcionSector = descripcionSector;
		obj.idTipoMicrobiologico = $("#cboTipoMicrobiologico").val();
		cargarPantalla('./cargarVentanaRegistroMicrobiologicoSecundario', obj, $('#idCargaNuevaPantalla'));
	}
}

function validacionBotonMicrobiologico(){
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		$("#btnGrabarMejorValor").show();
		if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR && estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnAprobarRegistroMicrobiologico").show();
		}else{
			$("#btnAprobarRegistroMicrobiologico").hide();
		}
		if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionMicrobiologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO && objRegistroLaboratorio.descripcionEstadoAprob == ConstanteServices.REGISTRO_PENDIENTE){
			$("#btnHabilitarRegistroMicrobiologico").show();
			$("#btnGrabarMejorValor").hide();
		}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
			$("#btnHabilitarRegistroMicrobiologico").hide();
			$("#btnGrabarMejorValor").hide();
		}
		btnEditarObservacionRegistro.show();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = true;
	}else{
		$("#btnAprobarRegistroMicrobiologico").hide();
		$("#btnHabilitarRegistroMicrobiologico").hide();
		btnEditarObservacionRegistro.hide();
		btnGuardarObservacionRegistro.hide();
		btnRegresarObservacionRegistro.hide();
		txtObservacionRegistro[0].disabled = false;
	}	
	if(estadoAprobacionMicrobiologico == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'accion');
		tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'mejorValor');
		$("#btnGrabarMejorValor").hide();
	}
}

function formatearInputMicrobiologico(){
	ponerClassConjunto("inputNumericoDefinido","valorDilucion",9,3,0);
	ponerClassConjunto("inputNumerico","CLT24",1,0,0);
	ponerClassConjunto("inputNumerico","CLT48",1,0,0);
	ponerClassConjunto("inputNumerico","CLVBB24",1,0,0);
	ponerClassConjunto("inputNumerico","CLVBB48",1,0,0);
	ponerClassConjunto("inputNumerico","EC24",1,0,0);
	ponerClassConjunto("inputNumerico","EC48",1,0,0);
	ponerClassConjunto("inputNumerico","valorCT",0,4,2);
	ponerClassConjunto("inputNumerico","CTNMP",0,4,2);
	ponerClassConjunto("inputNumerico","valorCTTNMP",0,4,2);
	ponerClassConjunto("inputNumerico","CTTNMP",0,4,2);
	ponerClassConjuntoFechas("fechaMonitoreo");
	ponerClassConjuntoFechas("fechaRegDato");
}


function cargarGrillaResultadoMicrobiologico(lista){
	var claseValidador = "";	
	if(flagAnalistaValidador != ConstanteServices.ROL_VALIDADOR){
		claseValidador = "hidden";
	} 
	tblResultadoRegistroMicrobiologico.bootstrapTable({
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
			field : 'accion',
			title : 'Acción',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesRegistroMicrobiologico',
			class: 'controls',
			events : operateEventsMicrobiologico,
			cellStyle : estiloAcciones
		},
		{	
			field : 'descripcionPuntoMuestra',
			title : 'Pto.<br>Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter : 'crearHtmlPuntoMuestra',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'descripcionDilucionInicial',
			title : 'Dilución<br>Inicial',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter : 'crearHtmlDilucionInicial',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numValorDilucion',
			title : 'Nro.<br>Dilución',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlValorDilucion',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'descripcionDilucion',
			title : 'Dil.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlDescripcionDilucion',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCLT24',
			title : 'CLT<br>24',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCLT24',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCLT48',
			title : 'CLT<br>48',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCLT48',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCLVBB24',
			title : 'CLVBB<br>24',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCLVBB24',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCLVBB48',
			title : 'CLVBB<br>48',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCLVBB48',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numEC24',
			title : 'EC<br>24',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlEC24',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numEC48',
			title : 'EC<br>48',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlEC48',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numValorCT',
			title : 'Valor<br>CT',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlValorCT',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCTNMP',
			title : 'CT<br>NMP/100l',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCTNMP',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numValorCTTNMP',
			title : 'Valor<br>CTT<br>NMP',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlValorCTTNMP',
			cellStyle : verificarEstiloMicro
		},{	
			field : 'numCTTNMP',
			title : 'CTT<br>NMP/100l',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter : 'crearHtmlCTTNMP',
			cellStyle : verificarEstiloMicro
		},{
			field : 'mejorValor',
			title : 'Mejor<br>Valor',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearComboMejorValor',
			class: 'controls',
			events : operateEventsMicrobiologico
		}]
	});
}

function verificarEstiloMicro(value, row, index) {	
	if(modo == ConstanteServices.ESTADO_OPCION_EDITAR && row.flagMejorValor == ConstanteServices.FLAG_MEJOR_VALOR && row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		return {
		    css: {
		      'color' : ConstanteServices.COLOR_DEPLEC,
		      'font-weight': ConstanteServices.ESTILO_STRONG,
		      'background' : ConstanteServices.COLOR_MEJOR_VALOR_CELDA
		    }
		};
	}else{
		return {};
	}
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearComboMejorValor(value, row, index) {	
	if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		if(row.flagMejorValor == ConstanteServices.FLAG_MEJOR_VALOR){
			return [
				'<input type="checkbox" id="checkMejorValorMicrobiologico" name="check_'+row.idPuntoMuestra+'" class="control-table tooltip-r" data-toggle="tooltip" data-placement="middle" title="Mejor Valor" value="'+index+'" checked>'
		    ].join('');
		}else{
			return [
				'<input type="checkbox" id="checkMejorValorMicrobiologico" name="check_'+row.idPuntoMuestra+'" class="control-table tooltip-r" data-toggle="tooltip" data-placement="middle" title="Mejor Valor" value="'+index+'">'
		    ].join('');
		}
	}else{
		return [''].join('');
	}
}

function crearAccionesRegistroMicrobiologico(value, row, index) {	
	if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		if(tipoEstado == index){
			if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
				return [
					'<button id="btnGuardarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar">',
					'<i class="glyphicon glyphicon-floppy-disk"></i>',
					'</button>',
					'<button id="btnEditarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar" style="display:none;">',
					'<i class="fa fa-pencil-square-o"></i>',
					'</button>',			
					'<button id="btnAnularRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
						'<i class="fa fa-times"></i>',
					'</button>',
					'<button id="btnRegresarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar">',//
						'<i class="fa fa-reply"></i>',
					'</button>'
			    ].join('');
			}else{
				return [
					'<button id="btnGuardarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar">',
					'<i class="glyphicon glyphicon-floppy-disk"></i>',
					'</button>',
					'<button id="btnEditarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar" style="display:none;">',
					'<i class="fa fa-pencil-square-o"></i>',
					'</button>',			
					'<button id="btnAnularRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
						'<i class="fa fa-times"></i>',
					'</button>',
					'<button id="btnRegresarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
						'<i class="fa fa-reply"></i>',
					'</button>'
			    ].join('');
			}
		}else{
			return [
				'<button id="btnGuardarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
				'<i class="glyphicon glyphicon-floppy-disk"></i>',
				'</button>',
				'<button id="btnEditarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
				'</button>',			
				'<button id="btnAnularRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
					'<i class="fa fa-times"></i>',
				'</button>',
				'<button id="btnRegresarRegistroMicrobiologico" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
					'<i class="fa fa-reply"></i>',
				'</button>'
		    ].join('');
		}
	}else{
		return [''].join('');
	}
}

function crearHtmlPuntoMuestra(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.descripcionPuntoMuestra].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
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
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.descripcionPuntoMuestra].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function crearHtmlDilucionInicial(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlCombo = '<select class="form-control input-sm" id="cboDilucionInicial_'+index+'" onchange="cambiarValoresDilucion('+index+')" style="width: 65px !important;">';
			for (var i = 0; i < ltaDilucionInicial.length; i++) {
			   if(ltaDilucionInicial[i].idDetalleGeneral == row.idDilucionInicial){
				   htmlCombo += '<option selected="selected" value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'
			   }else{
				   htmlCombo += '<option value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'	
			   }
			}
			htmlCombo 	  +='</select>'			
			return [htmlCombo].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlCombo = '<select class="form-control input-sm" id="cboDilucionInicial_'+index+'" onchange="cambiarValoresDilucion('+index+')" style="width: 65px !important;">';
				for (var i = 0; i < ltaDilucionInicial.length; i++) {
				   if(ltaDilucionInicial[i].idDetalleGeneral == row.idDilucionInicial){
					   htmlCombo += '<option selected="selected" value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'
				   }else{
					   htmlCombo += '<option value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'	
				   }
				}
				htmlCombo 	  +='</select>'			
				return [htmlCombo].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.descripcionDilucionInicial].join('');
			}else{
				return [''].join('');
			}
		}	
	}
}

function crearHtmlAnalista(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlCombo = '<select class="form-control input-sm" id="cboAnalista_'+index+'" >';
		for (var i = 0; i < ltaAnalista.length; i++) {
			if(ltaAnalista[i].idAnalista == row.idAnalista){
				htmlCombo += '<option selected="selected" value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
			}else{
			    htmlCombo += '<option value = "'+ltaAnalista[i].idAnalista+'">'+ltaAnalista[i].nombreCompleto+'</option>'
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

function crearHtmlValorDilucion(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numValorDilucion)){
				htmlimput = '<input type="text"  id="divValorDilucion_'+index+'" value="" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divValorDilucion_'+index+'" value="'+row.numValorDilucion+'" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlimput = "";
				if(IsEmpty(row.numValorDilucion)){
					htmlimput = '<input type="text"  id="divValorDilucion_'+index+'" value="" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
				}else{
					htmlimput = '<input type="text"  id="divValorDilucion_'+index+'" value="'+row.numValorDilucion+'" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
				}
				return [htmlimput].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.numValorDilucion].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function crearHtmlDescripcionDilucion(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.descripcionDilucion)){
			htmlimput = '<input type="text" id="divDescripcionDilucion_'+index+'" readonly = "readonly" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divDescripcionDilucion_'+index+'" readonly = "readonly" value="'+row.descripcionDilucion+'" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.descripcionDilucion)){
				htmlimput = '<input type="text" id="divDescripcionDilucion_'+index+'" readonly = "readonly" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divDescripcionDilucion_'+index+'" readonly = "readonly" value="'+row.descripcionDilucion+'" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.descripcionDilucion].join('');
		}
	}
}

function crearHtmlCLT24(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCLT24)){
			htmlimput = '<input type="text"  id="divCLT24_'+index+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCLT24_'+index+'" value="'+row.numCLT24+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numCLT24)){
				htmlimput = '<input type="text"  id="divCLT24_'+index+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCLT24_'+index+'" value="'+row.numCLT24+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numCLT24].join('');
		}
	}
}

function crearHtmlCLT48(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCLT48)){
			htmlimput = '<input type="text"  id="divCLT48_'+index+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCLT48_'+index+'" value="'+row.numCLT48+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numCLT48)){
				htmlimput = '<input type="text"  id="divCLT48_'+index+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCLT48_'+index+'" value="'+row.numCLT48+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numCLT48].join('');
		}
	}
}

function crearHtmlCLVBB24(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCLVBB24)){
			htmlimput = '<input type="text"  id="divCLVBB24_'+index+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCLVBB24_'+index+'" value="'+row.numCLVBB24+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numCLVBB24)){
				htmlimput = '<input type="text"  id="divCLVBB24_'+index+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCLVBB24_'+index+'" value="'+row.numCLVBB24+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numCLVBB24].join('');
		}
	}
}

function crearHtmlCLVBB48(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numCLVBB48)){
			htmlimput = '<input type="text"  id="divCLVBB48_'+index+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divCLVBB48_'+index+'" value="'+row.numCLVBB48+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numCLVBB48)){
				htmlimput = '<input type="text"  id="divCLVBB48_'+index+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCLVBB48_'+index+'" value="'+row.numCLVBB48+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numCLVBB48].join('');
		}
	}
}

function crearHtmlEC24(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numEC24)){
			htmlimput = '<input type="text"  id="divEC24_'+index+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divEC24_'+index+'" value="'+row.numEC24+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numEC24)){
				htmlimput = '<input type="text"  id="divEC24_'+index+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divEC24_'+index+'" value="'+row.numEC24+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numEC24].join('');
		}
	}
}

function crearHtmlEC48(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		var htmlimput = "";
		if(IsEmpty(row.numEC48)){
			htmlimput = '<input type="text"  id="divEC48_'+index+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
		}else{
			htmlimput = '<input type="text"  id="divEC48_'+index+'" value="'+row.numEC48+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
		}
		return [htmlimput].join('');
	}else{
		if(row.modoIngreso == 1){
			var htmlimput = "";
			if(IsEmpty(row.numEC48)){
				htmlimput = '<input type="text"  id="divEC48_'+index+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divEC48_'+index+'" value="'+row.numEC48+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''+row.numEC48].join('');
		}
	}
}

function crearHtmlValorCT(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numValorCT)){
				htmlimput = '<input type="text"  id="divValorCT_'+index+'"  readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divValorCT_'+index+'" value="'+row.numValorCT+'" readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlimput = "";
				if(IsEmpty(row.numValorCT)){
					htmlimput = '<input type="text"  id="divValorCT_'+index+'"  readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
				}else{
					htmlimput = '<input type="text"  id="divValorCT_'+index+'" value="'+row.numValorCT+'" readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
				}
				return [htmlimput].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.numValorCT].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function crearHtmlCTNMP(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numCTNMP)){
				htmlimput = '<input type="text"  id="divCTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCTNMP_'+index+'" value="'+row.numCTNMP+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlimput = "";
				if(IsEmpty(row.numCTNMP)){
					htmlimput = '<input type="text"  id="divCTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
				}else{
					htmlimput = '<input type="text"  id="divCTNMP_'+index+'" value="'+row.numCTNMP+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
				}
				return [htmlimput].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.numCTNMP].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function crearHtmlValorCTTNMP(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numValorCTTNMP)){
				htmlimput = '<input type="text"  id="divValorCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divValorCTTNMP_'+index+'" value="'+row.numValorCTTNMP+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlimput = "";
				if(IsEmpty(row.numValorCTTNMP)){
					htmlimput = '<input type="text"  id="divValorCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
				}else{
					htmlimput = '<input type="text"  id="divValorCTTNMP_'+index+'" value="'+row.numValorCTTNMP+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
				}
				return [htmlimput].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.numValorCTTNMP].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function crearHtmlCTTNMP(value,row,index){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			var htmlimput = "";
			if(IsEmpty(row.numCTTNMP)){
				htmlimput = '<input type="text"  id="divCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
			}else{
				htmlimput = '<input type="text"  id="divCTTNMP_'+index+'" value="'+row.numCTTNMP+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
			}
			return [htmlimput].join('');
		}else{
			return [''].join('');
		}
	}else{
		if(row.modoIngreso == 1){
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				var htmlimput = "";
				if(IsEmpty(row.numCTTNMP)){
					htmlimput = '<input type="text"  id="divCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
				}else{
					htmlimput = '<input type="text"  id="divCTTNMP_'+index+'" value="'+row.numCTTNMP+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
				}
				return [htmlimput].join('');
			}else{
				return [''].join('');
			}
		}else{
			if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
				return [''+row.numCTTNMP].join('');
			}else{
				return [''].join('');
			}
		}
	}
}

function agregarFilasHijas(indicadorAgregar, objetoClon){
	guardarTablaAntesCambiosMicrobiologico();
	tblResultadoRegistroMicrobiologico.bootstrapTable('insertRow', {
		index: indicadorAgregar,
		row: objetoClon
	});
	actualizarIndices();
}

function eliminarFilasHijas(indicadorEliminar){
	guardarTablaAntesCambiosMicrobiologico();
	tblResultadoRegistroMicrobiologico.bootstrapTable('remove', {
		field: 'indice',
		values: [parseInt(indicadorEliminar)]
	});
	actualizarIndices();
}

function generarFilasHijasDefault(){
	asignarValoresIniciales();
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var ltaClonGrilla = clonarLista(ltaGrilla);
	var a = ltaClonGrilla.length;
	for(j=0;j<a;j++){
		ltaClonGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
		if(ltaClonGrilla[j].indicePadre == ConstanteServices.INDICADOR_PADRE){
			var numValorDilucion = $("#divValorDilucion_"+j).val();
			var objetoClonPadre = clonarObjeto(ltaClonGrilla[j]);
			var objetoClon = clonarObjeto(ltaClonGrilla[j]);
			for(i=0; i<numValorDilucion-1; i++){
				objetoClon.indicePadre = ConstanteServices.INDICADOR_HIJO;
				objetoClon.secuencia = objetoClonPadre.secuencia + i + 1;
				objetoClon.grupo = objetoClonPadre.grupo;
				agregarFilasHijas(objetoClonPadre.indice + 1 + i, objetoClon);
			}
		}
		a = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData")).length;
	}
	guardarTablaAntesCambiosMicrobiologico();
	ltaGrillaFinal = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	for(k=0;k<ltaGrillaFinal.length;k++){
		if(ltaGrillaFinal[k].indicePadre == ConstanteServices.INDICADOR_PADRE){
			console.log("entraulti");
			var ltaValoresDilucion = obtenerValoresDilucion(k);
			asignarDilucion(k, ltaValoresDilucion);
		}
	}
	asignarValoresDilucionDefault();
	guardarTablaAntesCambiosMicrobiologico();
	formatearInputMicrobiologico();
}

function asignarValoresDilucionDefault(){
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(var i=0; i<ltaGrilla.length; i++){
		if(ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			generarValorCT(i);
			generarValorCTTNMP(i);
		}
	}
}

function generarFilasHijasNuevo(index){
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var ltaClonGrilla = clonarLista(ltaGrilla);
	var numValorDilucion = $("#divValorDilucion_"+index).val();
	var objetoClonPadre = clonarObjeto(ltaClonGrilla[index]);
	var objetoClon = clonarObjeto(ltaClonGrilla[index]);
	for(i=0; i<numValorDilucion-1; i++){
		objetoClon.indicePadre = ConstanteServices.INDICADOR_HIJO;
		objetoClon.secuencia = objetoClonPadre.secuencia + i + 1;
		objetoClon.grupo = objetoClonPadre.grupo;
		agregarFilasHijas(objetoClonPadre.indice + 1 + i, objetoClon);
	}
	cambiarValoresDilucion(index);
	guardarTablaAntesCambiosMicrobiologico();
	formatearInputMicrobiologico();
	bloquearCheckMejorValor();
}

function generarFilasHijasEditar(index){
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var ltaClonGrilla = clonarLista(ltaGrilla);
	var objetoClonPadre = clonarObjeto(ltaClonGrilla[index]);
	var objetoClon = clonarObjeto(ltaClonGrilla[index]);
	for(i=0; i<ConstanteServices.NUM_DILUCION_INICIAL-1; i++){
		objetoClon.indicePadre = ConstanteServices.INDICADOR_HIJO;
		objetoClon.secuencia = objetoClonPadre.secuencia + i + 1;
		objetoClon.grupo = objetoClonPadre.grupo;
		objetoClon.descripcionPuntoMuestra = "";
		objetoClon.descripcionDilucionInicial = "";
		objetoClon.numValorDilucion = "";
		tblResultadoRegistroMicrobiologico.bootstrapTable('insertRow', {
			index: objetoClonPadre.indice + 1 + i,
			row: objetoClon
		});
	}
	$("#divValorDilucion_"+index).val(ConstanteServices.NUM_DILUCION_INICIAL);
	formatearInputMicrobiologico();
	bloquearCheckMejorValor();
}

function asignarValoresIniciales(){
	var tamaño = tblResultadoRegistroMicrobiologico.bootstrapTable("getData").length;
	for(i=0;i<tamaño;i++){
		$("#divValorDilucion_"+i).val(ConstanteServices.NUM_DILUCION_INICIAL);
		$("#divCLT24_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
		$("#divCLT48_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
		$("#divCLVBB24_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
		$("#divCLVBB48_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
		$("#divEC24_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
		$("#divEC48_"+i).val(ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT);
	}
	guardarTablaAntesCambiosMicrobiologico();
}

function generarFilasHijas(index){
	var numValorDilucionNuevo = parseInt($("#divValorDilucion_"+index).val());
	var numValorDilucionAnterior= parseInt(tblResultadoRegistroMicrobiologico.bootstrapTable("getData")[index].numValorDilucion);
	var dilucionInicial = $("#cboDilucionInicial_"+index).val();

	if(IsEmpty(numValorDilucionNuevo) || IsEmpty(dilucionInicial) || IsEmpty(numValorDilucionAnterior)){
		console.log("sin cambios");
	}else{
		var accion = obtenerAccion(numValorDilucionNuevo, numValorDilucionAnterior);
		switch(accion) {
	    case 1:
	        for(i=0;i<numValorDilucionNuevo-numValorDilucionAnterior;i++){
	        	var ltaClonGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	        	var objetoClonPadre = clonarObjeto(ltaClonGrilla[index]);
	        	var objetoClon = clonarObjeto(ltaClonGrilla[index]);
	        	objetoClon.indicePadre = ConstanteServices.INDICADOR_HIJO;
				objetoClon.secuencia = numValorDilucionAnterior + i + 1;
				objetoClon.grupo = objetoClonPadre.grupo;
				objetoClon.numCLT24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numCLT48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numCLVBB24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numCLVBB48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numEC24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numEC48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
				objetoClon.numValorCT = "";
				objetoClon.numCTNMP = "";
				objetoClon.numValorCTTNMP = "";
				objetoClon.numCTTNMP = "";
				agregarFilasHijas(parseInt(objetoClonPadre.indice) + parseInt(numValorDilucionAnterior) + i, objetoClon);
	        }
	        break;
	    case 2:
	    	for(i=0;i<numValorDilucionAnterior-numValorDilucionNuevo;i++){
	        	var ltaClonGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	        	var objetoClonPadre = clonarObjeto(ltaClonGrilla[index]);
				eliminarFilasHijas(parseInt(objetoClonPadre.indice) + parseInt(numValorDilucionAnterior) - i - 1);
	        }
	        break;
	    default:
	    	console.log("sin cambios");
		}
	}
	if(accion == 1 || accion == 2){
		cambiarValoresDilucion(index);
	}
	asignarValoresDilucionDefault();
	bloquearCheckMejorValor();
}

function cambiarValoresDilucion(index){
	guardarTablaAntesCambiosMicrobiologico();
	var ltaValoresDilucion = obtenerValoresDilucion(index);
	actualizarTablaMicrobiologicos(index, ltaValoresDilucion);
	asignarDilucion(index, ltaValoresDilucion);
	asignarValoresDilucionDefault();
	formatearInputMicrobiologico();
}

function asignarDilucion(index, ltaValoresDilucion){
	for(i=0;i<ltaValoresDilucion.length;i++){
		$("#divDescripcionDilucion_"+(index+i)).val(ltaValoresDilucion[i].descripcion);
	}
	guardarTablaAntesCambiosMicrobiologico();
}

function actualizarIndices(){
	guardarTablaAntesCambiosMicrobiologico();
	var ltaGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	for(var i=0;i<ltaGrilla.length;i++){
		ltaGrilla[i].indice = parseInt(i);
	}
	tblResultadoRegistroMicrobiologico.bootstrapTable('load', ltaGrilla);
}

function obtenerAccion(numValorDilucionNuevo, numValorDilucionAnterior){
	var tipoAccion = 0;
	if(numValorDilucionNuevo > numValorDilucionAnterior){
		tipoAccion = 1;
	}
	if(numValorDilucionNuevo < numValorDilucionAnterior){
		tipoAccion = 2;
	}
	return tipoAccion;
}

function obtenerValoresDilucion(index){
	var listaValores = [];
	var ltaClonDilInicial = clonarLista(ltaDilucionInicial);
	var dilucionInicial = $("#cboDilucionInicial_"+index).val();
	var numValoresGrupo = parseInt($("#divValorDilucion_"+index).val());
	var indiceLista = null 
	for(i=0;i<ltaClonDilInicial.length;i++){
		if(!IsEmpty(dilucionInicial) && dilucionInicial == ltaDilucionInicial[i].idDetalleGeneral){
			var objetoClon = clonarObjeto(ltaDilucionInicial[i]);
			var valorElegido = parseInt(objetoClon.descripcion);
			var indiceElegido = i;
			break;
		}
	}
    listaValores.push(objetoClon);
    if(numValoresGrupo>1){
    	for(var i=0;i<numValoresGrupo-1;i++){
    		listaValores.push(ltaDilucionInicial[indiceElegido+i+1]);
    	}
    }
    console.log(limpiarArray(listaValores));
    return limpiarArray(listaValores);
}

function obtenerIndiceLista(){
	var indice = null;
	for(i=0;i<ltaDilucionInicial.length;i++){
		if(ltaDilucionInicial[i].vlDato01 == "1"){
			indice = i;
			break;
		}
	}
	return indice;
}

function actualizarTablaMicrobiologicos(index, ltaValoresDilucion){
	guardarTablaAntesCambiosMicrobiologico();
	var ltaGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));	
	var numValoresGrupo = parseInt($("#divValorDilucion_"+index).val());
	if(ltaValoresDilucion.length < numValoresGrupo){
		ltaGrilla.splice(index+ltaValoresDilucion.length,numValoresGrupo-ltaValoresDilucion.length);
		tblResultadoRegistroMicrobiologico.bootstrapTable('load', ltaGrilla);
		$("#divValorDilucion_"+index).val(ltaValoresDilucion.length);
		actualizarIndices();
	}
}

function generarValorCT(index){
		var flag = true;
		var cadenaEvaluar = "";
		var exponente = null;
		ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
		objetoClon = clonarObjeto(ltaGrilla[index]);
		for(i=0;i<ltaGrilla.length;i++){
			if(ltaGrilla[i].grupo == objetoClon.grupo && ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
				objetoPadre = clonarObjeto(ltaGrilla[i]);
				break;
			}
		}
		for(i=0;i<ConstanteServices.TOTAL_VALORES_TUBO_DIL;i++){
			var operador = parseInt($("#divValorDilucion_"+String(objetoPadre.indice)).val());
			if(!IsEmpty($("#divCLVBB48_"+String(objetoPadre.indice+operador+i-ConstanteServices.TOTAL_VALORES_TUBO_DIL)).val())){
				var agregado = $("#divCLVBB48_"+String(objetoPadre.indice+operador+i-ConstanteServices.TOTAL_VALORES_TUBO_DIL)).val();
				cadenaEvaluar = cadenaEvaluar.concat(agregado);
				if(i!=ConstanteServices.TOTAL_VALORES_TUBO_DIL-1){
					cadenaEvaluar = cadenaEvaluar.concat("-");
				}
			}else{
				flag = false;
				break;
			}
		}
		if(!IsEmpty($("#divDescripcionDilucion_"+String(objetoPadre.indice+operador-2)).val())){
			exponente = parseInt($("#divDescripcionDilucion_"+String(objetoPadre.indice+operador-2)).val());
			if(parseInt(exponente) < 0){
				exponente = parseInt(exponente)*-1;
			}
		}else{
			flag = false;
		}
		if(flag){
			console.log(cadenaEvaluar);
			obtenerValorDilucion(cadenaEvaluar, exponente);
			if(!IsEmpty(resultadoEvaluacion)){
				var resultado = resultadoEvaluacion[0];
				$("#divValorCT_"+objetoPadre.indice).val(resultado.nMP100);
				$("#divCTNMP_"+objetoPadre.indice).val(resultado.descripcionExponencial);
				ltaGrilla[objetoPadre.indice].idTuboDilucionCLV48 = resultado.idTuboDilucion;
				ltaGrilla[objetoPadre.indice].valorDecimalCLV48 = resultado.valorDecimal;
			}else{
				$("#divValorCT_"+objetoPadre.indice).val("");
				$("#divCTNMP_"+objetoPadre.indice).val("");
				ltaGrilla[objetoPadre.indice].idTuboDilucionCLV48 = "";
			}
		}else{
			$("#divValorCT_"+objetoPadre.indice).val("");
			$("#divCTNMP_"+objetoPadre.indice).val("");
			ltaGrilla[objetoPadre.indice].idTuboDilucionCLV48 = "";
		}
}

function generarValorCTTNMP(index){
		var flag = true;
		var cadenaEvaluar = "";
		ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
		objetoClon = clonarObjeto(ltaGrilla[index]);
		for(i=0;i<ltaGrilla.length;i++){
			if(ltaGrilla[i].grupo == objetoClon.grupo && ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
				objetoPadre = clonarObjeto(ltaGrilla[i]);
				break;
			}
		}
		for(i=0;i<ConstanteServices.TOTAL_VALORES_TUBO_DIL;i++){
			var operador = parseInt($("#divValorDilucion_"+String(objetoPadre.indice)).val());
			if(!IsEmpty($("#divEC48_"+String(objetoPadre.indice+operador+i-ConstanteServices.TOTAL_VALORES_TUBO_DIL)).val())){
				var agregado = $("#divEC48_"+String(objetoPadre.indice+operador+i-ConstanteServices.TOTAL_VALORES_TUBO_DIL)).val();
				cadenaEvaluar = cadenaEvaluar.concat(agregado);
				if(i!=ConstanteServices.TOTAL_VALORES_TUBO_DIL-1){
					cadenaEvaluar = cadenaEvaluar.concat("-");
				}
			}else{
				flag = false;
				break;
			}
		}
		if(!IsEmpty($("#divDescripcionDilucion_"+String(objetoPadre.indice+operador-2)).val())){
			exponente = parseInt($("#divDescripcionDilucion_"+String(objetoPadre.indice+operador-2)).val());
			if(parseInt(exponente) < 0){
				exponente = parseInt(exponente)*-1;
			}
		}else{
			flag = false;
		}
		if(flag){
			console.log(cadenaEvaluar);
			console.log(exponente);
			obtenerValorDilucion(cadenaEvaluar, exponente);
			if(!IsEmpty(resultadoEvaluacion)){
				var resultado = resultadoEvaluacion[0];
				$("#divValorCTTNMP_"+objetoPadre.indice).val(resultado.nMP100);
				$("#divCTTNMP_"+objetoPadre.indice).val(resultado.descripcionExponencial); 
				ltaGrilla[objetoPadre.indice].idTuboDilucionEC48 = resultado.idTuboDilucion;
				ltaGrilla[objetoPadre.indice].valorDecimalEC48 = resultado.valorDecimal;
			}else{
				$("#divValorCTTNMP_"+objetoPadre.indice).val("");
				$("#divCTTNMP_"+objetoPadre.indice).val(""); 
				ltaGrilla[objetoPadre.indice].idTuboDilucionEC48 = "";
			}
		}else{
			$("#divValorCTTNMP_"+objetoPadre.indice).val("");
			$("#divCTTNMP_"+objetoPadre.indice).val(""); 
			ltaGrilla[objetoPadre.indice].idTuboDilucionEC48 = "";
		}
}

function eliminarSubParametroNuevoMicrobiologico(index){
	guardarTablaAntesCambiosMicrobiologico();
	var ltaGrilla = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));	
	var numValoresGrupo = parseInt($("#divValorDilucion_"+index).val());
	ltaGrilla.splice(index,numValoresGrupo);
	tblResultadoRegistroMicrobiologico.bootstrapTable('load', ltaGrilla);
	actualizarIndices();
	formatearInputMicrobiologico();
	flagMicrobiologico = 0;
	bloquearCheckMejorValor();
}

function verificarParametroAgregarMicrobiologico(){
	mostrarMensaje();
	if(flagMicrobiologico == 0){
		var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
			var objetoClon = clonarObjeto(ltaGrilla[countRow-1]);
			objetoClon.flagMejorValor = ConstanteServices.FLAG_NO_MEJOR_VALOR;
			objetoClon.indice = countRow;
			objetoClon.grupo = objetoClon.grupo + 1;
			if(ltaGrilla != null && ltaGrilla.length > 0){
				objetoClon.grupo = obtenerValorMaximoGrupoMicrobiologico(ltaGrilla)+1;
			}else{
				objetoClon.grupo = 1;	
			}
			objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(countRow,null);
			objetoClon.idDilucionInicial = ConstanteServices.ID_DILUCION10;
			objetoClon.indicePadre = ConstanteServices.INDICADOR_PADRE;
			objetoClon.secuencia = ConstanteServices.INDICADOR_PADRE;
			objetoClon.numValorDilucion = ConstanteServices.NUM_DILUCION_INICIAL;
			objetoClon.numCLT24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numCLT48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numCLVBB24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numCLVBB48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numEC24 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numEC48 = ConstanteServices.VALOR_MICROBIOLOGICO_DEFAULT;
			objetoClon.numValorCT = "";
			objetoClon.numCTNMP = "";
			objetoClon.numValorCTTNMP = "";
			objetoClon.numCTTNMP = "";
			if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				objetoClon.idRegMicrobiologico = null;
				objetoClon.modoIngreso = 1;		
			}
			guardarTablaAntesCambiosMicrobiologico();
			tblResultadoRegistroMicrobiologico.bootstrapTable('insertRow', {
				index: countRow,
				row: objetoClon
			});
			generarFilasHijasNuevo(countRow);
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				bloquearRegistrosPadres();
			}else{
				var accionRegresar = tblResultadoRegistroMicrobiologico.find('tr').eq(countRow+1).find('td').eq(0).find('#btnRegresarRegistroMicrobiologico');
				accionRegresar[0].style.display = 'inherit';
			}
			var accionGuardar = tblResultadoRegistroMicrobiologico.find('tr').eq(countRow+1).find('td').eq(0).find('#btnGuardarRegistroMicrobiologico');
			var accionEditar = tblResultadoRegistroMicrobiologico.find('tr').eq(countRow+1).find('td').eq(0).find('#btnEditarRegistroMicrobiologico');
			var accionAnular = tblResultadoRegistroMicrobiologico.find('tr').eq(countRow+1).find('td').eq(0).find('#btnAnularRegistroMicrobiologico');
		        
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
			
			tipoEstado = countRow;
			generarValorCT(countRow);
			generarValorCTTNMP(countRow);
			validacionResultado(countRow);
			formatearInputMicrobiologico();
			flagMicrobiologico = 1;
			bloquearCheckMejorValor();
		}else{
			mostrarMensaje('No Hay SubParametros Por Agregar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputMicrobiologico();
}

function bloquearRegistrosPadres(){
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(i=0;i<ltaGrilla.length;i++){
		if(ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			$('#cboDilucionInicial_'+i).attr('disabled', 'disabled');
			$('#divValorDilucion_'+i).attr('disabled', 'disabled');
		}
	}
}

function activarRegistrosPadres(){
	var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(i=0;i<ltaGrilla.length;i++){
		if(ltaGrilla[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			$('#cboDilucionInicial_'+i).removeAttr('disabled');
			$('#divValorDilucion_'+i).removeAttr('disabled');
		}
	}
}

function crearHtmlComboPuntoMuestraNuevo(index, value){
		var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" >';
		for (var i = 0; i < ltaPuntoMuestra.length; i++) {
			if(ltaPuntoMuestra[i].idPuntoMuestra == value){
				htmlCombo += '<option selected="selected" value = "'+ltaPuntoMuestra[i].idPuntoMuestra+'">'+ltaPuntoMuestra[i].descripcionCorta+'</option>';
			}else{
				htmlCombo += '<option value = "'+ltaPuntoMuestra[i].idPuntoMuestra+'">'+ltaPuntoMuestra[i].descripcionCorta+'</option>';
			}
		}
		htmlCombo 	  +='</select>';			
		return htmlCombo;
}

function crearHtmlDilucionInicialNuevo(index,value){
		var htmlCombo = '<select class="form-control input-sm" id="cboDilucionInicial_'+index+'" onchange="cambiarValoresDilucion('+index+')" style="width: 65px !important;">';
		for (var i = 0; i < ltaDilucionInicial.length; i++) {
		   if(ltaDilucionInicial[i].idDetalleGeneral == value){
			   htmlCombo += '<option selected="selected" value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'
		   }else{
			   htmlCombo += '<option value = "'+ltaDilucionInicial[i].idDetalleGeneral+'">'+ltaDilucionInicial[i].descripcion+'</option>'	
		   }
		}
		htmlCombo 	  +='</select>'			
		return [htmlCombo].join('');
}

function crearHtmlValorDilucionNuevo(index,value){
		if(IsEmpty(value)){
			return '<input type="text"  id="divValorDilucion_'+index+'" value="" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
		}else{
			return '<input type="text"  id="divValorDilucion_'+index+'" value="'+value+'" onkeyup="generarFilasHijas('+index+')" class="form-control input-sm valorDilucion" style="width: 30px !important;" />';
		}
}

function crearHtmlDescripcionDilucionNuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text" id="divDescripcionDilucion_'+index+'" readonly = "readonly" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
	}else{
		return '<input type="text"  id="divDescripcionDilucion_'+index+'" readonly = "readonly" value="'+value+'" class="form-control input-sm descripcionDilucion" style="width: 55px !important;" />';
	}
}

function crearHtmlCLT24Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCLT24_'+index+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divCLT24_'+index+'" value="'+value+'" class="form-control input-sm CLT24" style="width: 40px !important;" />';
	}
}

function crearHtmlCLT48Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCLT48_'+index+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divCLT48_'+index+'" value="'+value+'" class="form-control input-sm CLT48" style="width: 40px !important;" />';
	}
}

function crearHtmlCLVBB24Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCLVBB24_'+index+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divCLVBB24_'+index+'" value="'+value+'" class="form-control input-sm CLVBB24" style="width: 40px !important;" />';
	}
}

function crearHtmlCLVBB48Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCLVBB48_'+index+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divCLVBB48_'+index+'" value="'+value+'" onkeyup="generarValorCT('+index+')" class="form-control input-sm CLVBB48" style="width: 40px !important;" />';
	}
}

function crearHtmlEC24Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divEC24_'+index+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divEC24_'+index+'" value="'+value+'" class="form-control input-sm EC24" style="width: 40px !important;" />';
	}
}

function crearHtmlEC48Nuevo(index,value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divEC48_'+index+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
	}else{
		return '<input type="text"  id="divEC48_'+index+'" value="'+value+'" onkeyup="generarValorCTTNMP('+index+')" class="form-control input-sm EC48" style="width: 40px !important;" />';
	}
}

function crearHtmlValorCTNuevo(index,value){
		if(IsEmpty(value)){
			return '<input type="text"  id="divValorCT_'+index+'"  readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
		}else{
			return '<input type="text"  id="divValorCT_'+index+'" value="'+value+'" readonly = "readonly" class="form-control input-sm valorCT" style="width: 50px !important;" />';
		}
}

function crearHtmlCTNMPNuevo(index,value){
		if(IsEmpty(value)){
			return '<input type="text"  id="divCTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
		}else{
			return '<input type="text"  id="divCTNMP_'+index+'" value="'+value+'" readonly = "readonly" class="form-control input-sm CTNMP" style="width: 85px !important;" />';
		}
}

function crearHtmlValorCTTNMPNuevo(index,value){
		if(IsEmpty(value)){
			return '<input type="text"  id="divValorCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
		}else{
			return '<input type="text"  id="divValorCTTNMP_'+index+'" value="'+value+'" readonly = "readonly" class="form-control input-sm valorCTTNMP" style="width: 50px !important;" />';
		}
}

function crearHtmlCTTNMPNuevo(index,value){
		if(IsEmpty(value)){
			return '<input type="text"  id="divCTTNMP_'+index+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
		}else{
			return '<input type="text"  id="divCTTNMP_'+index+'" value="'+value+'" readonly = "readonly" class="form-control input-sm CTTNMP" style="width: 85px !important;" />';
		}
}




function crearHtmlCantidadNuevo(index, value){
	if(IsEmpty(value)){
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresMicrobiologico('+index+')" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}else{
		return '<input type="text"  id="divCantidad_'+index+'" onkeyup="calcularValoresMicrobiologico('+index+')" value = "'+value+'" class="form-control input-sm cantidad" style="width: 80px !important;" />';
	}
}

function validacionResultado(index){
}

function validacionResultadoEditar(index){
}

function asignarParametrosHijas(){
	console.log('entra asignacion');
	var listaFinal = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	for(i=0;i<listaFinal.length;i++){
		if(listaFinal[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
			objetoPadre = clonarObjeto(listaFinal[i]);
			for(j=0;j<parseInt(objetoPadre.numValorDilucion)-1;j++){
				listaFinal[objetoPadre.indice+j+1].idPuntoMuestra = objetoPadre.idPuntoMuestra;
				listaFinal[objetoPadre.indice+j+1].idDilucionInicial = objetoPadre.idDilucionInicial;
				listaFinal[objetoPadre.indice+j+1].numValorDilucion = objetoPadre.numValorDilucion;
				listaFinal[objetoPadre.indice+j+1].numValorCT = objetoPadre.numValorCT;
				listaFinal[objetoPadre.indice+j+1].numValorCTTNMP = objetoPadre.numValorCTTNMP;
				listaFinal[objetoPadre.indice+j+1].numCTNMP = objetoPadre.numCTNMP;
				listaFinal[objetoPadre.indice+j+1].numCTTNMP = objetoPadre.numCTTNMP;
				listaFinal[objetoPadre.indice+j+1].idTuboDilucionCLV48 = objetoPadre.idTuboDilucionCLV48;
				listaFinal[objetoPadre.indice+j+1].valorDecimalCLV48 = objetoPadre.valorDecimalCLV48;
				listaFinal[objetoPadre.indice+j+1].idTuboDilucionEC48 = objetoPadre.idTuboDilucionEC48;
				listaFinal[objetoPadre.indice+j+1].valorDecimalEC48 = objetoPadre.valorDecimalEC48;
			}
		}
	}
}

function asignarParametrosHijasEditar(index){
	var listaFinal = clonarLista(tblResultadoRegistroMicrobiologico.bootstrapTable("getData"));
	objetoPadre = clonarObjeto(listaFinal[index]);
	var idPuntoM = $("#cboPuntoMuestra_"+index).val();
	if(idPuntoM != null && idPuntoM != undefined){
		objetoPadre.idPuntoMuestra  = parseInt(idPuntoM);
	}
	var idDilucionInicial = $("#cboDilucionInicial_"+index).val();
	if(idDilucionInicial != null && idDilucionInicial != undefined){
		objetoPadre.idDilucionInicial  = parseInt(idDilucionInicial);
	}
	var numValorDilucion = $("#divValorDilucion_"+index).val();
	if(numValorDilucion != null && numValorDilucion != undefined){
		objetoPadre.numValorDilucion  = numValorDilucion;
	}
	var descripcionDilucion = $("#divDescripcionDilucion_"+index).val();
	if(descripcionDilucion != null && descripcionDilucion != undefined){
		objetoPadre.descripcionDilucion  = descripcionDilucion;
	}
	var descripcionDilucionInicial = extraerTextoCombo("cboDilucionInicial_"+index);
	if(descripcionDilucionInicial != null && descripcionDilucionInicial != undefined){
		objetoPadre.descripcionDilucionInicial  = descripcionDilucionInicial;
	}
	var numValorCT = $("#divValorCT_"+index).val();
	if(numValorCT != null && numValorCT != undefined){
		objetoPadre.numValorCT  = numValorCT;
	}
	var numCTNMP = $("#divCTNMP_"+index).val();
	if(numCTNMP != null && numCTNMP != undefined){
		objetoPadre.numCTNMP  = numCTNMP;
	}
	var numValorCTTNMP = $("#divValorCTTNMP_"+index).val();
	if(numValorCTTNMP != null && numValorCTTNMP != undefined){
		objetoPadre.numValorCTTNMP  = numValorCTTNMP;
	}
	var numCTTNMP = $("#divCTTNMP_"+index).val();
	if(numCTTNMP != null && numCTTNMP != undefined){
		objetoPadre.numCTTNMP  = numCTTNMP;
	}
	for(j=0;j<parseInt(objetoPadre.numValorDilucion)-1;j++){
		listaFinal[objetoPadre.indice+j+1].idPuntoMuestra = objetoPadre.idPuntoMuestra;
		listaFinal[objetoPadre.indice+j+1].idDilucionInicial = objetoPadre.idDilucionInicial;
		listaFinal[objetoPadre.indice+j+1].numValorDilucion = objetoPadre.numValorDilucion;
		listaFinal[objetoPadre.indice+j+1].numValorCT = objetoPadre.numValorCT;
		listaFinal[objetoPadre.indice+j+1].numValorCTTNMP = objetoPadre.numValorCTTNMP;
		listaFinal[objetoPadre.indice+j+1].numCTNMP = objetoPadre.numCTNMP;
		listaFinal[objetoPadre.indice+j+1].numCTTNMP = objetoPadre.numCTTNMP;
		listaFinal[objetoPadre.indice+j+1].idTuboDilucionCLV48 = objetoPadre.idTuboDilucionCLV48;
		listaFinal[objetoPadre.indice+j+1].valorDecimalCLV48 = objetoPadre.valorDecimalCLV48;
		listaFinal[objetoPadre.indice+j+1].idTuboDilucionEC48 = objetoPadre.idTuboDilucionEC48;
		listaFinal[objetoPadre.indice+j+1].valorDecimalEC48 = objetoPadre.valorDecimalEC48;
	}
}

function verificarDataGrabarMicrobiologico(){
	if(flagMicrobiologico == 0){
		guardarTablaAntesCambiosMicrobiologico();
		asignarParametrosHijas();
		var lta = completarLtaRegistroMicrobiologico();
		if(validarDataTabla(lta)){	
			var tituloModal = 'Registro Microbiologico';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarDatosRegistroMicrobiologico()', '', tituloModal);
		}	
	}
}

function validarDataTabla(listaFinal){
	var indicador = true;
	for(var i = 0 ; i < listaFinal.length ; i++){		
		if(IsEmpty(listaFinal[i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].idDilucionInicial)){
			mostrarMensaje('Debe Ingresar una Dilucion Inicial en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numValorDilucion)){
			mostrarMensaje('Debe Ingresar un N° Dilución en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].descripcionDilucion)){
			mostrarMensaje('Debe Ingresar una Dilución en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCLT24)){
			mostrarMensaje('Debe Ingresar un CLT24 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCLT48)){
			mostrarMensaje('Debe Ingresar un CLT48 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCLVBB24)){
			mostrarMensaje('Debe Ingresar un CLVBB24 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCLVBB48)){
			mostrarMensaje('Debe Ingresar un CLVBB48 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCTNMP)){
			mostrarMensaje('Debe Ingresar un CTNMP en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numCTTNMP)){
			mostrarMensaje('Debe Ingresar un CTTNMP en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numEC24)){
			mostrarMensaje('Debe Ingresar un EC24 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numEC48)){
			mostrarMensaje('Debe Ingresar un EC48 en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numValorCT)){
			mostrarMensaje('Debe Ingresar un Valor CT en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[i].numValorCTTNMP)){
			mostrarMensaje('Debe Ingresar un Valor CTTNMP en el item N° '+(i+1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	return indicador
}

function completarLtaRegistroMicrobiologico(){
	var listaFinal = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(var i = 0 ; i < listaFinal.length ; i++){
		var idPuntoM = $("#cboPuntoMuestra_"+i).val();
		if(idPuntoM != null && idPuntoM != undefined){
			listaFinal[i].idPuntoMuestra  = parseInt(idPuntoM);
		}
		var idDilucionInicial = $("#cboDilucionInicial_"+i).val();
		if(idDilucionInicial != null && idDilucionInicial != undefined){
			listaFinal[i].idDilucionInicial  = parseInt(idDilucionInicial);
		}
		var numValorDilucion = $("#divValorDilucion_"+i).val();
		if(numValorDilucion != null && numValorDilucion != undefined){
			listaFinal[i].numValorDilucion  = numValorDilucion;
		}
		var descripcionDilucion = $("#divDescripcionDilucion_"+i).val();
		if(descripcionDilucion != null && descripcionDilucion != undefined){
			listaFinal[i].descripcionDilucion  = descripcionDilucion;
		}
		
		var descripcionDilucionInicial = extraerTextoCombo("cboDilucionInicial_"+i);
		if(descripcionDilucionInicial != null && descripcionDilucionInicial != undefined){
			listaFinal[i].descripcionDilucionInicial  = descripcionDilucionInicial;
		}
		
		var numCLT24 = $("#divCLT24_"+i).val();
		if(numCLT24 != null && numCLT24 != undefined){
			listaFinal[i].numCLT24  = numCLT24;
		}
		
		var numCLT48 = $("#divCLT48_"+i).val();
		if(numCLT48 != null && numCLT48 != undefined){
			listaFinal[i].numCLT48  = numCLT48;
		}
		
		var numCLVBB24 = $("#divCLVBB24_"+i).val();
		if(numCLVBB24 != null && numCLVBB24 != undefined){
			listaFinal[i].numCLVBB24  = numCLVBB24;
		}
		
		var numCLVBB48 = $("#divCLVBB48_"+i).val();
		if(numCLVBB48 != null && numCLVBB48 != undefined){
			listaFinal[i].numCLVBB48  = numCLVBB48;
		}
		
		var numEC24 = $("#divEC24_"+i).val();
		if(numEC24 != null && numEC24 != undefined){
			listaFinal[i].numEC24  = numEC24;
		}
		
		var numEC48 = $("#divEC48_"+i).val();
		if(numEC48 != null && numEC48 != undefined){
			listaFinal[i].numEC48  = numEC48;
		}
		
		var numValorCT = $("#divValorCT_"+i).val();
		if(numValorCT != null && numValorCT != undefined){
			listaFinal[i].numValorCT  = numValorCT;
		}
		
		var numCTNMP = $("#divCTNMP_"+i).val();
		if(numCTNMP != null && numCTNMP != undefined){
			listaFinal[i].numCTNMP  = numCTNMP;
		}
		
		var numValorCTTNMP = $("#divValorCTTNMP_"+i).val();
		if(numValorCTTNMP != null && numValorCTTNMP != undefined){
			listaFinal[i].numValorCTTNMP  = numValorCTTNMP;
		}
		
		var numCTTNMP = $("#divCTTNMP_"+i).val();
		if(numCTTNMP != null && numCTTNMP != undefined){
			listaFinal[i].numCTTNMP  = numCTTNMP;
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
			listaFinal[i].numCantidad  = numCantidad;
		}
	}
	return listaFinal;
}

function grabarDatosRegistroMicrobiologico(){
	var lta = completarLtaRegistroMicrobiologico();
	$.ajax({
        url : "./grabarDatosRegistroMicrobiologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	listaRegistroMicrobiologico : JSON.stringify(lta),
        	idPtarxSector : idPtarSector ,
        	flagValidador : 0 ,
        	fechaRegistro : fechaActual,
        	observacionRegistroMicrobiologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		$("#btnGuardarTodoRegistroMicrobiologico").hide();
        	modo = respuestaBean.parametros.modo;
        	$("#idRegistroLaboratorio").val(""+respuestaBean.parametros.idRegistroLaboratorio);
        	$("#lblTitulo").text("Registro Microbiologico - N° Registro "+respuestaBean.parametros.idRegistroLaboratorio);
        	txtObservacionRegistro.val(respuestaBean.parametros.observacionMicrobiologico);
        	observacionMicrobiologico = respuestaBean.parametros.observacionMicrobiologico;
        	validacionBotonMicrobiologico();
        	buscarDatosRegistroMicrobiologico();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarDatosRegistroMicrobiologico(){
	$.ajax({
        url : "./buscarDatosRegistroMicrobiologico",
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
        		ltaResultado = JSON.parse(ListaResultado);
        		asignarModoIngreso(ltaResultado);
        		tblResultadoRegistroMicrobiologico.bootstrapTable('load', ltaResultado);
        		$("#fechaMonitoreo").text(JSON.parse(ListaResultado)[0].fechaMonitoreoString);
        		bloquearCheckMejorValor();
        	}else{
            	tblResultadoRegistroMicrobiologico.bootstrapTable('load', []);
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputMicrobiologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function asignarModoIngreso(ltaResultado){
	for(i=0;i<ltaResultado.length;i++){
		ltaResultado[i].modoIngreso = 0;
	}
}

function obtenerValorDilucion(valorCadena, numExponente){
	mostrarMensaje();
	console.log("entra" + valorCadena);
	$.ajax({
        url : "./buscarTuboDilucionByCadena",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	valorCombinacion : valorCadena,
        	exponente : numExponente
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {
        		console.log(JSON.parse(ListaResultado));
        		resultadoEvaluacion =  JSON.parse(ListaResultado);
        	}else{
            	resultadoEvaluacion = null;
        		mostrarMensaje("No se encontraron resultados con los datos CLVBB48", ConstanteServices.IMAGEN_DANGER);
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputMicrobiologico();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarNuevoRegistroEditar(index){
	var listaFinal = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var indicador = true;
	for(var i = 0 ; i < parseInt(listaFinal[index].numValorDilucion) ; i++){
		if(IsEmpty(listaFinal[index + i].idPuntoMuestra)){
			mostrarMensaje('Debe Seleccionar un Punto Muestra en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].idDilucionInicial)){
			mostrarMensaje('Debe Ingresar una Dilucion Inicial en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numValorDilucion)){
			mostrarMensaje('Debe Ingresar un N° Dilución en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].descripcionDilucion)){
			mostrarMensaje('Debe Ingresar una Dilución en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCLT24)){
			mostrarMensaje('Debe Ingresar un CLT24 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCLT48)){
			mostrarMensaje('Debe Ingresar un CLT48 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCLVBB24)){
			mostrarMensaje('Debe Ingresar un CLVBB24 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCLVBB48)){
			mostrarMensaje('Debe Ingresar un CLVBB48 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCTNMP)){
			mostrarMensaje('Debe Ingresar un CTNMP en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numCTTNMP)){
			mostrarMensaje('Debe Ingresar un CTTNMP en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numEC24)){
			mostrarMensaje('Debe Ingresar un EC24 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numEC48)){
			mostrarMensaje('Debe Ingresar un EC48 en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numValorCT)){
			mostrarMensaje('Debe Ingresar un Valor CT en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
		if(IsEmpty(listaFinal[index + i].numValorCTTNMP)){
			mostrarMensaje('Debe Ingresar un Valor CTTNMP en el item N° '+(index + i + 1), ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}
	
	return indicador;
}

function grabarItemRegistroMicrobiologico(index){
	var listaFinal = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var listaNueva = [];
	var count = parseInt(listaFinal[index].numValorDilucion)
	for(var i = 0 ; i < count ; i++){
		listaNueva.push(listaFinal[index+i]);
	}
	$.ajax({
        url : "./grabarItemRegistroMicrobiologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	ltaRegMicrobiologicoBean : JSON.stringify(listaNueva),
        	flagValidador : 0 
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagMicrobiologico = 0;
        	tipoEstado = null;
        	buscarDatosRegistroMicrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function eliminarItemRegistroMicrobiologico(indice){
	var listaFinal = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	var listaNueva = [];
	for(var i = 0 ; i < parseInt(listaFinal[indice].numValorDilucion) ; i++){
		listaNueva.push(listaFinal[indice+i]);
	}
	console.log(listaNueva);
	$.ajax({
        url : "./eliminarItemRegistroMicrobiologico",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	ltaRegMicrobiologico : JSON.stringify(listaNueva)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagMicrobiologico = 0;
        	buscarDatosRegistroMicrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarParametroEditarNuevoMicrobiologico(index,row){
	mostrarMensaje();
	if(flagMicrobiologico == 0){
		var ltaGrilla = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
		var ltaClonSubParametroTodo = clonarLista(ltaSubParametrosTodo);
		if(ltaClonSubParametroTodo != null && ltaClonSubParametroTodo.length > 0){
			var objetoClon = clonarObjeto(row);
			quitarCheckSeleccionado(index, row);
			var accionGuardar = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(0).find('#btnGuardarRegistroMicrobiologico');
			var accionEditar = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(0).find('#btnEditarRegistroMicrobiologico');
			var accionAnular = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(0).find('#btnAnularRegistroMicrobiologico');
			var accionRegresar = tblResultadoRegistroMicrobiologico.find('tr').eq(index + 1).find('td').eq(0).find('#btnRegresarRegistroMicrobiologico');
			
			var descripcionPuntoMuestra = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(1);
			var descripcionDilucionInicial = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(2);
			var numValorDilucion = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(3);
			
			
			var numValorCT = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(11);
			var numCTNMP = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(12);
			var numValorCTTNMP = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(13);
			var numCTTNMP = tblResultadoRegistroMicrobiologico.find('tr').eq(index+1).find('td').eq(14);
			
			
			descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(index,row.idPuntoMuestra));
			descripcionDilucionInicial.html(""+crearHtmlDilucionInicialNuevo(index,row.idDilucionInicial));
			numValorDilucion.html(""+crearHtmlValorDilucionNuevo(index,row.numValorDilucion));
			
			
			numValorCT.html(""+crearHtmlValorCTNuevo(index,row.numValorCT));
			numCTNMP.html(""+crearHtmlCTNMPNuevo(index,row.numCTNMP));
			numValorCTTNMP.html(""+crearHtmlValorCTTNMPNuevo(index,row.numValorCTTNMP));
			numCTTNMP.html(""+crearHtmlCTTNMPNuevo(index,row.numCTTNMP));
			
			accionGuardar[0].style.display = 'inherit';
			accionEditar[0].style.display = 'none';
			accionAnular[0].style.display = 'inherit';
			
			if(modo != ConstanteServices.ESTADO_OPCION_NUEVA) {
				for(i=0;i<parseInt(row.numValorDilucion);i++){
					var descripcionDilucion = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(4);
					var numCLT24 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(5);
					var numCLT48 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(6);
					var numCLVBB24 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(7);
					var numCLVBB48 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(8);
					var numEC24 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(9);
					var numEC48 = tblResultadoRegistroMicrobiologico.find('tr').eq(index+i+1).find('td').eq(10);
					
					descripcionDilucion.html(""+crearHtmlDescripcionDilucionNuevo(index+i,ltaGrilla[index+i].descripcionDilucion));
					numCLT24.html(""+crearHtmlCLT24Nuevo(index+i,""+ltaGrilla[index+i].numCLT24));
					numCLT48.html(""+crearHtmlCLT48Nuevo(index+i,""+ltaGrilla[index+i].numCLT48));
					numCLVBB24.html(""+crearHtmlCLVBB24Nuevo(index+i,""+ltaGrilla[index+i].numCLVBB24));
					numCLVBB48.html(""+crearHtmlCLVBB48Nuevo(index+i,""+ltaGrilla[index+i].numCLVBB48));
					numEC24.html(""+crearHtmlEC24Nuevo(index+i,""+ltaGrilla[index+i].numEC24));
					numEC48.html(""+crearHtmlEC48Nuevo(index+i,""+ltaGrilla[index+i].numEC48));
					
					ltaGrilla[index+i].modoIngreso = 1;
				}
				accionRegresar[0].style.display = 'inherit';
		    }
			else{
		    	bloquearRegistrosPadres();
		    }
			tipoEstado = index;
			validacionResultadoEditar(index);
			calcularValoresMicrobiologico(index);
			formatearInputMicrobiologico();
			flagMicrobiologico = 1;
		}else{
			mostrarMensaje('No Hay SubParametros Por Editar.', ConstanteServices.IMAGEN_DANGER);
		}
    }
	formatearInputMicrobiologico();
}

function guardarTablaAntesCambiosMicrobiologico(){
	var lista = completarLtaRegistroMicrobiologico();
	tblResultadoRegistroMicrobiologico.bootstrapTable('load', lista);
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

function calcularValoresMicrobiologico(index){
}

function saberRowEdicionIndexMicrobiologico(idDiv){
	var indice = null;
	var data = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(var  i = 0 ; i < data.length ;i++){
		var valor = $("#"+idDiv+i).val();
		if(valor != null && valor != undefined){
			indice = i;
			break;
		}
	}
	return indice;
}

function verificarAprobacionMicrobiologico(){
	if(flagMicrobiologico == 0){
		var tituloModal = 'Aprobación Registro Microbiologico';
		modal.confirmer('¿Esta seguro de Aprobar Registro Microbiologico ?', 'aprobarRegistroMicrobiologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function aprobarRegistroMicrobiologico(){
	$.ajax({
        url : "./aprobarRegistroMicrobiologico",
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
    		$("#btnAprobarRegistroMicrobiologico").hide();
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionMicrobiologico = ConstanteServices.ID_FLAG_ESTADO_APROBADO;
        	validacionBotonMicrobiologico();
        	tblResultadoRegistroMicrobiologico.bootstrapTable('hideColumn', 'accion');
        	buscarDatosRegistroMicrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function verificarHabilitarRegistroMicrobiologico(){
	if(flagMicrobiologico == 0){
		var tituloModal = 'Habilitación Registro Microbiologico';
		modal.confirmer('¿Esta seguro de Habilitar Registro Microbiologico ?', 'habilitarRegistroMicrobiologico()', '', tituloModal);
	}else{
		mostrarMensaje("Debe Guardar Para Continuar", ConstanteServices.IMAGEN_DANGER);
	}
}

function habilitarRegistroMicrobiologico(){
	$.ajax({
        url : "./aprobarRegistroMicrobiologico",
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
    		$("#btnAprobarRegistroMicrobiologico").hide();
        	mostrarMensaje('Se Habilitó Registo Microbiologico Correctamente', ConstanteServices.IMAGEN_SUCCESS);
        	estadoAprobacionMicrobiologico = ConstanteServices.ID_FLAG_ESTADO_PEND_APROB;
        	validacionBotonMicrobiologico();
        	buscarDatosRegistroMicrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function bloquearCheckMejorValor(){
	if(flagAnalistaValidador != ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		for(var i=0; i<checkMejorValorMicrobiologico.length; i++){
			checkMejorValorMicrobiologico[i].disabled=true;
		}
	}
}

function activarCheckMejorValor(){
	if(flagAnalistaValidador != ConstanteServices.ROL_ADMINISTRADOR && estadoAprobacionMicrobiologico != ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		for(var i=0; i<checkMejorValorMicrobiologico.length; i++){
			checkMejorValorMicrobiologico[i].disabled=false;
		}
	}
}

function evaluarMejorValorSeleccionado(index, row){
	var checks = document.getElementsByName('check_'+row.idPuntoMuestra);
	var lista = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	if(row.flagMejorValor == 0){
		for(var i=0;i<checks.length; i++){
			if(checks[i].value!=index){
				checks[i].checked = false;
			}
		}
		for(var i=0;i<lista.length; i++){
			if(lista[i].idPuntoMuestra == row.idPuntoMuestra && lista[i].indicePadre == ConstanteServices.INDICADOR_PADRE){
				lista[i].flagMejorValor = ConstanteServices.FLAG_NO_MEJOR_VALOR;
			}
		}
		row.flagMejorValor = ConstanteServices.FLAG_MEJOR_VALOR;
	}else{
		row.flagMejorValor = ConstanteServices.FLAG_NO_MEJOR_VALOR;
	}
}

function quitarCheckSeleccionado(index, row){
	var checks = document.getElementsByName('check_'+row.idPuntoMuestra);
	for(var i=0;i<checks.length; i++){
		if(checks[i].value==index){
			checks[i].checked = false;
			break;
		}
	}
	row.flagMejorValor = ConstanteServices.FLAG_NO_MEJOR_VALOR;
}

function visibleGrabarMejorValor(){
	if(flagMejorValor == 1){
		bloquearCheckMejorValor();
	}else{
		activarCheckMejorValor();	
	}
}

function grabarMejoresValores(){
	var listaFinal = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
	for(var i=0;i<listaFinal.length;i++){
		listaFinal[i].idTuboDilucionCLV48 = null;
		listaFinal[i].idTuboDilucionEC48 = null;
	}
	$.ajax({
        url : "./grabarMejoresValores",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	ltaRegMicrobiologicoBean : JSON.stringify(listaFinal)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagMicrobiologico = 0;
        	tipoEstado = null;
        	buscarDatosRegistroMicrobiologico();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

/*funcion grabar observacion de parametro Microbiologico*/
function guardarObservacionMicrobiologico(){
	$.ajax({
        url : "./guardarObservacionMicrobiologico",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idRegistroLaboratorio : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null),
        	observacionRegistroMicrobiologico : !IsEmpty(txtObservacionRegistro.val()) ? txtObservacionRegistro.val() : " "
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS, "divMensajeInformacionObservacionRegistro");
        	validacionBotonMicrobiologico();
        	observacionMicrobiologico = respuestaBean.parametros.observacionMicrobiologico;
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function confirmacionGuardarObservacionMicrobiologico(){
	var tituloModal = 'Observacion Registro Microbiológico';
	modal.confirmer('¿Esta seguro de Grabar la Observación del Registro?', 'guardarObservacionMicrobiologico()', 'regresarObservacionMicrobiologico()', tituloModal);
}

function regresarObservacionMicrobiologico(){
	txtObservacionRegistro.val(""+observacionMicrobiologico);
}
/**/

	function personalizado(e) {
	    switch (e.key) {
	      case 'ArrowRight':
	        var el = document.activeElement;
	        var rowNo = el.id.substring(el.id.indexOf("_")+1);
	        var fieldId = el.id.substring(0, el.id.indexOf("_"));
	        if(fieldId == 'divCLT24'){
	        	var newId = "divCLT48_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		          window.setTimeout (function(){ 
		              newEl.select(); 
		           },100);
		        }
	        }
	        if(fieldId == 'divCLT48'){
	        	var newId = "divCLVBB24_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
			        	newEl.select(); 
		        	},100);
		        }
	        }
			if(fieldId == 'divCLVBB24'){
				var newId = "divCLVBB48_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
			        	newEl.select(); 
		        	},100);
		        }        	
			}
			if(fieldId == 'divCLVBB48'){
				var newId = "divEC24_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
			        	newEl.select(); 
		        	},100);
		        }        	
			}
			if(fieldId == 'divEC24'){
				var newId = "divEC48_"+rowNo;
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
			if(fieldId == 'divEC48'){
				var newId = "divEC24_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }        	
			}
			if(fieldId == 'divEC24'){
				var newId = "divCLVBB48_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }        	
			}
			if(fieldId == 'divCLVBB48'){
				var newId = "divCLVBB24_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
			        	newEl.select(); 
		        	},100);
		        }        	
			}
			if(fieldId == 'divCLVBB24'){
	        	var newId = "divCLT48_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }
	        }
			if(fieldId == 'divCLT48'){
	        	var newId = "divCLT24_"+rowNo;
		        var newEl = document.getElementById(newId);
		        if (newEl != null) {
		        	window.setTimeout (function(){ 
		        		newEl.select(); 
		        	},100);
		        }
	        }
	        break;
	    }
	  };

