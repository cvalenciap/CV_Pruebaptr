$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
var indicadorEdicionValor = 0;
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
	
	if(idRegistroLaboratorio != null && !IsEmpty(idRegistroLaboratorio)){
		$("#lblTituloMantenimiento").text("Semillas - N° Registro "+ idRegistroLaboratorio);
	}
	
	if(ltaValidacionBlank != null && ltaValidacionBlank.length > 0){
		$("#lblValidacionBlanks").text(""+ltaValidacionBlank[0].vlDato01);
		validacionBlank = eval(ltaValidacionBlank[0].vlDato02);
	}
	
	if(ltaValidacionSeeded != null && ltaValidacionSeeded.length > 0){
		$("#lblValidacionSeededVal2").text(""+ltaValidacionSeeded[0].vlDato02+" mL");
		$("#lblValidacionSeededVal3").text(""+ltaValidacionSeeded[0].vlDato03);
		validacionSeededMinimo = eval(ltaValidacionSeeded[0].vlDato04);
		validacionSeededMaximo = eval(ltaValidacionSeeded[0].vlDato05);
	}
	
	if(ltaValidacionGGA != null && ltaValidacionGGA.length > 0){
		$("#lblValidacionGGAVal1").text(""+ltaValidacionGGA[0].vlDato01);
		validacionGGAMinimo = eval(ltaValidacionGGA[0].vlDato02);
		validacionGGAMaximo = eval(ltaValidacionGGA[0].vlDato03);
	}
	
	if(ltaValidacionSamples != null && ltaValidacionSamples.length > 0){
		$("#lblValidacionSamples").text(""+ltaValidacionSamples[0].vlDato01);
		validacionSamples = eval(ltaValidacionSamples[0].vlDato02);	
	}
	
	$("#btnRegresarDBO").click(function() {
		cargarPantallaProcesoFisicoQuimico(ConstanteServices.ID_PARAMETRO_DBO5);
	});
	
	console.log("semilla");
	cargarGrillaResultadoBlanks(ltaSemillaBlankDBO);
	cargarGrillaResultadoSeeded(ltaSemillaSeededDBO);
	cargarGrillaResultadoStandard(ltaSemillaStandardDBO);
	cargarGrillaResultadoSamples(ltaSemillaSamplesDBO);
}

function cargarGrillaResultadoBlanks(lista){
	tblResultadosBlanks.bootstrapTable({
		data : lista,
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
		columns : [
		{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'descripcionPtoMuestra',
			title : 'Punto de Muestra',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'descripcionBotella',
			title : 'Bottle #.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'numInicial',
			title : 'Initial DO',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'numFinal',
			title : 'Final DO',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		},{	
			field : 'numPromedio',
			title : 'Promedio DBO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlankPromedio
		},{
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloBlank
		}]
	});
}

function verificarEstiloBlankPromedio(value, row, index){
	if(eval(row.numPromedio) < eval(validacionBlank)) {
		return {
			css: {
				'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			}
		};
	}else{
		return {
		    css: {
		    	'background' : ConstanteServices.COLOR_COLUMNA_CALCULO,
		    	'color' : 'red'
		    }
		};
	}
}

function verificarEstiloBlank(value, row, index) {	
	if(eval(row.numPromedio) < eval(validacionBlank)) {
		return {};
	}else{
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

function cargarGrillaResultadoSeeded(lista){
	tblResultadosSeeded.bootstrapTable({
		data : lista,
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
		columns : [
		{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'descripcionPtoMuestra',
			title : 'Punto de Muestra',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'descripcionBotella',
			title : 'Bottle #.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numVolumen',
			title : 'Volumen.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numInicial',
			title : 'Initial DO B1.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numFinal',
			title : 'Final DO B2.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numPorcentajeDeplec',
			title : '% Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded,
			formatter: 'formatoPorcentaje'
		},{	
			field : 'numValorBOD',
			title : 'BOD.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numValorSCF',
			title : 'SCF.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		},{	
			field : 'numPromedio',
			title : 'Promedio SCF.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSeddedPromedio
		},{
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSedded
		}]
	});
}

function formatoPorcentaje(value, row, index) {
	return [row.numPorcentajeDeplec + '%'].join('');			
}

function verificarEstiloSeddedPromedio(value, row, index){
	if(eval(validacionSeededMinimo) <= eval(row.numValorSCF) && eval(row.numValorSCF) <= eval(validacionSeededMaximo)) {
		return {
			css: {
				'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			}
		};
	}else{
		return {
		    css: {
		    	'background' : ConstanteServices.COLOR_COLUMNA_CALCULO,
		    	'color' : 'red'
		    }
		};
	}
}

function verificarEstiloSedded(value, row, index) {	
	if(eval(validacionSeededMinimo) <= eval(row.numValorSCF) && eval(row.numValorSCF) <= eval(validacionSeededMaximo)) {
		return {};
	}else{
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

//STANTARD

function cargarGrillaResultadoStandard(lista){
	tblResultadosStandard.bootstrapTable({
		data : lista,
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
		columns : [
		{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'descripcionPtoMuestra',
			title : 'Punto de Muestra',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'descripcionBotella',
			title : 'Bottle #.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numVolGGa',
			title : 'Vol (mL)<br>of GGA.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numVolPoly',
			title : 'Vol (mL)<br>of PolySeed.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numInicial',
			title : 'Initial DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numFinal',
			title : 'Final DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numNetDep',
			title : 'Net Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numPorcentajeDeplec',
			title : '% Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA,
			formatter: 'formatoPorcentaje'
		},{	
			field : 'numValorBOD',
			title : 'BOD.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		},{	
			field : 'numPromedioGGA',
			title : 'Promedio<br>GGA',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGAPromedio
		},{
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloGGA
		}]
	});
}

function formatoPorcentaje(value, row, index) {
	return [row.numPorcentajeDeplec + '%'].join('');			
}

function verificarEstiloGGAPromedio(value, row, index) {	
	if(eval(validacionGGAMinimo-validacionGGAMaximo) <= eval(row.numValorBOD) && eval(row.numValorBOD) <= eval(validacionGGAMinimo+validacionGGAMaximo)) {
		return {
			css: {
				'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			}
		};
	}else{
		return {
		    css: {
		    	'background' : ConstanteServices.COLOR_COLUMNA_CALCULO,
		    	'color' : 'red'
		    }
		};
	}
}


function verificarEstiloGGA(value, row, index) {	
	if(eval(validacionGGAMinimo-validacionGGAMaximo) <= eval(row.numValorBOD) && eval(row.numValorBOD) <= eval(validacionGGAMinimo+validacionGGAMaximo)) {
		return {};
	}else{
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

//SAMPLES

function cargarGrillaResultadoSamples(lista){
	tblResultadosSamples.bootstrapTable({
		data : lista,
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
		columns : [
		{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'descripcionPtoMuestra',
			title : 'Punto de Muestra',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'descripcionBotella',
			title : 'Bottle #.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numVolSample',
			title : 'Vol (mL)<br>of sample.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numVolPoly',
			title : 'Vol (mL)<br>of PolySeed.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numInicial',
			title : 'Initial DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numFinal',
			title : 'Final DO.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numDepletion',
			title : 'Depletion.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numValorSCF',
			title : 'SCF.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numValorNetDep',
			title : 'Net Dep.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numDilcFactor',
			title : 'Dil. Factor.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numSampleBOD',
			title : 'Sample BOD.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		},{	
			field : 'numPromedioDBO',
			title : 'Promedio DBO5<br>(mg/L).',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamplesPromedio
		},{
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloSamples
		}]
	});
}

function verificarEstiloSamplesPromedio(value, row, index) {	
	if(eval(row.numPromedioDBO) > eval(validacionSamples)) {
		return {
			css: {
				'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			}
		};
	}else{
		return {
		    css: {
		    	'background' : ConstanteServices.COLOR_COLUMNA_CALCULO,
		    	'color' : 'red'
		    }
		};
	}
}

function verificarEstiloSamples(value, row, index) {	
	if(eval(row.numPromedioDBO) > eval(validacionSamples)) {
		return {};
	}else{
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}





