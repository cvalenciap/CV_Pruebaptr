$(document).ready(function() {
	inicializarVariablesDirecto();
	if(estadoRespuesta == ConstanteServices.ERROR){
		mostrarMensaje("Inconsistencia entre las relaciones de puntos de muestra de entrada y salida, revisar la configuración de puntos de muestra", ConstanteServices.IMAGEN_DANGER);
		limpiarParametrosBusqueda();
	}else{
		cargarComponentesDirecto();
	}	
});
var accion = false;
var flagDirecto = 0;
var flagVerAnalistaDirecto = 0;
var nombreAnalistaDirecto = "nombreAnalistaDirecto";
//var respuesta = JSON.parse(respuesta);

function inicializarVariablesDirecto() {
	console.log("resultadoDashboard");
	listaPuntos = JSON.parse(listaPuntos);
	listaPtar = JSON.parse(listaPtar);
//	respuesta = JSON.parse(respuesta);
	estadoRespuesta = respuesta != null ? respuesta.estadoRespuesta : null;
	var lineChart = null;
	var cboPtarxSector = null;
	var lineChart = null;
	var lineChartCTT = null;
	var lineChartDonaEP = null;
	var lineChartDonaSP = null;
	var lineChartPolar = null;
	var divGraficosGenerados = null;
	var tblResultadoDashboardEP = null;
	var tblResultadoDashboardSP = null;
}

function cargarComponentesDirecto() {
	accordion();
	cboPtarxSector = $("#cboPtarxSector");
	lineChart = $('#lineChart');
	lineChartSP = $('#lineChartSP');
	lineChartCTT = $('#lineChartCTT');
//	lineChartDonaEP = $('#lineChartDonaEP');
//	lineChartDonaSP = $('#lineChartDonaSP');
	line = $('#line');
	lineNewAlter = $("#lineNewAlter");
	divGraficosGenerados = $('#divGraficosGenerados');
//	tblResultadoDashboardEP = $('#tblResultadoDashboardEP');
//	tblResultadoDashboardSP = $('#tblResultadoDashboardSP');
	var listaComboPtar = []
	for(var i=0; i<listaPuntos.length; i++){
		var objPtar = {
				idPtarxSector : listaPuntos[i],
				descripcionPtarxSector : listaPtar[i]
		};
		listaComboPtar.push(objPtar);
	}
	agregarListaItem(cboPtarxSector, listaComboPtar, "idPtarxSector", "descripcionPtarxSector");
	
	var ltaDBO = [];
	var ltaSST = [];
	var ltaCTT = [];
//	var ltaPH = [];
//	var ltaT = [];
//	var ltaAYG = [];
	var ltaDQO = [];
	var ltaDBOSP = [];
	var ltaSSTSP = [];
	var ltaCTTSP = [];
//	var ltaPHSP = [];
//	var ltaTSP = [];
//	var ltaAYGSP = [];
	var ltaDQOSP = [];
	
	for(var i=0; i< listaPuntos.length; i++){
		for(var j=0; j<listaDashboardEP.length; j++){
			if(listaPuntos[i] == listaDashboardEP[j].idPtarxSector){
				switch (listaDashboardEP[j].idSubParametro) {
					case 83: ltaDBO.push(listaDashboardEP[j].numValor);
						break;
					case 100: ltaSST.push(listaDashboardEP[j].numValor);
						break;
					case 26: ltaCTT.push(listaDashboardEP[j].numValor);
						break;
//					case 90: ltaPH.push(listaDashboardEP[j].numValor);
//						break;
//					case 105: ltaT.push(listaDashboardEP[j].numValor);
//						break;
//					case 28: ltaAYG.push(listaDashboardEP[j].numValor);
//						break;
					case 84: ltaDQO.push(listaDashboardEP[j].numValor);
						break;
				}
			}
		}
//		completarListas(i, ltaDBO, ltaSST, ltaCTT, ltaPH, ltaT, ltaAYG, ltaDQO);
		completarListas(i, ltaDBO, ltaSST, ltaCTT, ltaDQO);
	}
	
	for(var i=0; i< listaPuntos.length; i++){
		for(var j=0; j<listaDashboardSP.length; j++){
			if(listaPuntos[i] == listaDashboardSP[j].idPtarxSector){
				switch (listaDashboardSP[j].idSubParametro) {
					case 83: ltaDBOSP.push(listaDashboardSP[j].numValor);
						break;
					case 100: ltaSSTSP.push(listaDashboardSP[j].numValor);
						break;
					case 26: ltaCTTSP.push(listaDashboardSP[j].numValor);
						break;
//					case 90: ltaPHSP.push(listaDashboardSP[j].numValor);
//						break;
//					case 105: ltaTSP.push(listaDashboardSP[j].numValor);
//						break;
//					case 28: ltaAYGSP.push(listaDashboardSP[j].numValor);
//						break;
					case 84: ltaDQOSP.push(listaDashboardSP[j].numValor);
						break;
				}
			}
		}
//		completarListasSP(i, ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaPHSP, ltaTSP, ltaAYGSP, ltaDQOSP);
		completarListasSP(i, ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaDQOSP);
	}
	
	lineChart = new Chart(lineChart, {
		type: 'bar',
	    data: {
	        labels: listaPtar,
	        datasets: [{
	            label: 'DBO',
	            yAxisID: 'A',
	            data: ltaDBO,
	            backgroundColor: 'rgba(51, 233, 203, 0.6)',
	            borderColor: 'rgba(51, 233, 203, 1)',
	            borderWidth: 1
	        },
	        {
	            label: 'SST',
	            yAxisID: 'A',
	            data: ltaSST,
	            backgroundColor: 'rgba(51, 215, 233, 0.6)',
	            borderColor: 'rgba(51, 215, 233, 1)',
	            borderWidth: 1
	        },
	        {
	            label: 'CTT',
	            yAxisID: 'B',
	            data: ltaCTT,
	            backgroundColor: 'rgba(51, 188, 233, 0.6)',
	            borderColor: 'rgba(51, 188, 233, 1)',
	            borderWidth: 1
	        },
//	        {
//	            label: 'PH',
//	            yAxisID: 'A',
//	            data: ltaPH,
//	            backgroundColor:'rgba(51, 148, 233, 0.6)',
//	            borderColor: 'rgba(51, 148, 233, 1)',
//	            borderWidth: 1
//	        },
//	        {
//	            label: 'T°',
//	            yAxisID: 'A',
//	            data: ltaT,
//	            backgroundColor: 'rgba(51, 103, 233, 0.6)',
//	            borderColor: 'rgba(51, 103, 233, 1)',
//	            borderWidth: 1
//	        },
//	        {
//	            label: 'A&G',
//	            yAxisID: 'A',
//	            data: ltaAYG,
//	            backgroundColor: 'rgba(24, 41, 230, 0.6)',
//	            borderColor: 'rgba(24, 41, 230, 1)',	
//	            borderWidth: 1
//	        },
	        {
	            label: 'DQO',
	            yAxisID: 'A',
	            data: ltaDQO,
	            backgroundColor: 'rgba(29, 44, 138, 0.6)',
	            borderColor: 'rgba(29, 44, 138, 1)',
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	            	id: 'A',
					type: 'linear',
					position: 'left',
					scalePositionLeft: true,
					ticks: {
						beginAtZero:true
			            	}
	            	},{
	            	id: 'B',
	            	type: 'linear',
	            	position: 'right',
	            	scalePositionLeft: false,
	            	ticks: {
	            		beginAtZero:true
	            	}
	            }],
	            xAxes: [{
	                ticks: {
	                    autoSkip: false,
	                    maxRotation: 90,
	                    minRotation: 90
	                }
	            }]
	        },
		    title: {
	            display: true,
	            text: 'Gráfico de Barras Entrada de Planta - EP',
	            fontColor: '#1587c6',
	            fontSize : 15,
	            fontFamily: 'Arial',
	            fontStyle: 'normal'
	        },
	    }
	});
	
	lineChartSP = new Chart(lineChartSP, {
		type: 'bar',
	    data: {
	        labels: listaPtar,
	        datasets: [{
	            label: 'DBO',
	            yAxisID: 'A',
	            data: ltaDBOSP,
	            backgroundColor: 'rgba(51, 233, 203, 0.6)',
	            borderColor: 'rgba(51, 233, 203, 1)',
	            borderWidth: 1
	        },
	        {
	            label: 'SST',
	            yAxisID: 'A',
	            data: ltaSSTSP,
	            backgroundColor: 'rgba(51, 215, 233, 0.6)',
	            borderColor: 'rgba(51, 215, 233, 1)',
	            borderWidth: 1
	        },
	        {
	            label: 'CTT',
	            yAxisID: 'B',
	            data: ltaCTTSP,
	            backgroundColor: 'rgba(51, 188, 233, 0.6)',
	            borderColor: 'rgba(51, 188, 233, 1)',
	            borderWidth: 1
	        },
//	        {
//	            label: 'PH',
//	            yAxisID: 'A',
//	            data: ltaPHSP,
//	            backgroundColor:'rgba(51, 148, 233, 0.6)',
//	            borderColor: 'rgba(51, 148, 233, 1)',
//	            borderWidth: 1
//	        },
//	        {
//	            label: 'T°',
//	            yAxisID: 'A',
//	            data: ltaTSP,
//	            backgroundColor: 'rgba(51, 103, 233, 0.6)',
//	            borderColor: 'rgba(51, 103, 233, 1)',
//	            borderWidth: 1
//	        },
//	        {
//	            label: 'A&G',
//	            yAxisID: 'A',
//	            data: ltaAYGSP,
//	            backgroundColor: 'rgba(24, 41, 230, 0.6)',
//	            borderColor: 'rgba(24, 41, 230, 1)',	
//	            borderWidth: 1
//	        },
	        {
	            label: 'DQO',
	            yAxisID: 'A',
	            data: ltaDQOSP,
	            backgroundColor: 'rgba(29, 44, 138, 0.6)',
	            borderColor: 'rgba(29, 44, 138, 1)',
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	            	id: 'A',
					type: 'linear',
					position: 'left',
					scalePositionLeft: true,
					ticks: {
						beginAtZero:true
			            	}
	            	},{
	            	id: 'B',
	            	type: 'linear',
	            	position: 'right',
	            	scalePositionLeft: false,
	            	ticks: {
	            		beginAtZero:true
	            	}
	            }],
	            xAxes: [{
	                ticks: {
	                    autoSkip: false,
	                    maxRotation: 90,
	                    minRotation: 90
	                }
	            }]
	        },
		    title: {
	            display: true,
	            text: 'Gráfico de Barras Salida de Planta - SP',
	            fontColor: '#1587c6',
	            fontSize : 15,
	            fontFamily: 'Arial',
	            fontStyle: 'normal'
	        },
	    }
	});
	
//	cargarGrillaResultado();
	generarTablaDashboard();
	cboPtarxSector.change(function(event){
//		generarGraficosUnitarios(ltaDBO, ltaSST, ltaCTT, ltaPH, ltaT, ltaAYG, ltaDQO);
//		generarGraficosUnitariosSP(ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaPHSP, ltaTSP, ltaAYGSP, ltaDQOSP);
		generarTablaDashboard();
    });
	
//	generarGraficosUnitarios(ltaDBO, ltaSST, ltaCTT, ltaPH, ltaT, ltaAYG, ltaDQO);
//	generarGraficosUnitariosSP(ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaPHSP, ltaTSP, ltaAYGSP, ltaDQOSP);
	$('#defaultAcordion').slideDown();
	$('#defaultAcordion').parents('.panel').find('.panel-heading span.clickable').removeClass('panel-collapsed');
	$('#defaultAcordion').parents('.panel').find('.panel-heading').find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
}

//function completarListas(i, ltaDBO, ltaSST, ltaCTT, ltaPH, ltaT, ltaAYG, ltaDQO){
function completarListas(i, ltaDBO, ltaSST, ltaCTT, ltaDQO){
	if(ltaDBO[i] == null){
		ltaDBO[i] = 0;
	}
	if(ltaSST[i] == null){
		ltaSST[i] = 0;
	}
	if(ltaCTT[i] == null){
		ltaCTT[i] = 0;
	}
//	if(ltaPH[i] == null){
//		ltaPH[i] = 0;
//	}
//	if(ltaT[i] == null){
//		ltaT[i] = 0;
//	}
//	if(ltaAYG[i] == null){
//		ltaAYG[i] = 0;
//	}
	if(ltaDQO[i] == null){
		ltaDQO[i] = 0;
	}
}

//function completarListasSP(i, ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaPHSP, ltaTSP, ltaAYGSP, ltaDQOSP){
function completarListasSP(i, ltaDBOSP, ltaSSTSP, ltaCTTSP, ltaDQOSP){	
	if(ltaDBOSP[i] == null){
		ltaDBOSP[i] = 0;
	}
	if(ltaSSTSP[i] == null){
		ltaSSTSP[i] = 0;
	}
	if(ltaCTTSP[i] == null){
		ltaCTTSP[i] = 0;
	}
//	if(ltaPHSP[i] == null){
//		ltaPHSP[i] = 0;
//	}
//	if(ltaTSP[i] == null){
//		ltaTSP[i] = 0;
//	}
//	if(ltaAYGSP[i] == null){
//		ltaAYGSP[i] = 0;
//	}
	if(ltaDQOSP[i] == null){
		ltaDQOSP[i] = 0;
	}
}

function generarTablaDashboard(){
	listaGenerada = generarListaComboEP();
	listaGeneradaSP = generarListaComboSP();
	generarListasLine();
	generarListaslineNewAlter();
}

function generarListaComboEP(){
	var listaGenerada = [];
	for(var i=0; i<listaDashboardEP.length; i++ ){
		if(listaDashboardEP[i].idPtarxSector == cboPtarxSector.val() && listaDashboardEP[i].idSubParametro != ConstanteServices.ID_COLIFORME_TERMOTOLERANTES){
			listaGenerada.push(listaDashboardEP[i]);
		}
	}
	return listaGenerada;
}

function generarListaComboSP(){
	var listaGenerada = [];
	for(var i=0; i<listaDashboardSP.length; i++ ){
		if(listaDashboardSP[i].idPtarxSector == cboPtarxSector.val() && listaDashboardSP[i].idSubParametro != ConstanteServices.ID_COLIFORME_TERMOTOLERANTES){
			listaGenerada.push(listaDashboardSP[i]);
		}
	}
	return listaGenerada;
}

function generarListasLine(){
	$.ajax({
        url : "./obtenerListaLineDashboard",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null)
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		cargarGrillaLinea(JSON.parse(respuestaBean.parametros.listaEjeX), JSON.parse(respuestaBean.parametros.listaDashboard), JSON.parse(respuestaBean.parametros.listaSubParametrosEPSP));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function cargarGrillaLinea(listaEjeX, listaDashboard, listaSubParametrosEPSP){
	if(line.data.datasets != undefined && line.data.datasets.length != 0){
		line.data.labels = listaEjeX;
		line.data.datasets = [];
		for(var i=0; i<listaSubParametrosEPSP.length; i++){
			line.data.datasets[i].data = construirListaDataEPSP(listaEjeX, listaDashboard, listaSubParametrosEPSP[i]);
		}
		line.update();
	}else{
		var datasetsFormat = construirArrayDataLine(listaDashboard, listaEjeX, listaSubParametrosEPSP);
		line = new Chart(line, {
			  type: 'line',
			  data: {
				  labels: listaEjeX,
				  datasets: datasetsFormat
				},
			  options: {
				  scales: {
					  yAxes: [
					      {
					        id: 'A',
					        type: 'linear',
					        position: 'left',
					        scalePositionLeft: true,
					        ticks: {
			                    beginAtZero:true
			                }
					      },
					      {
					        id: 'B',
					        type: 'logarithmic',
					        position: 'right',
					        scalePositionLeft: false,
					        ticks: {
			                    beginAtZero:true
			                }
					      }
					    ],
						xAxes: [{
			                ticks: {
			                    autoSkip: false,
			                    maxRotation: 90,
			                    minRotation: 90
			                }
			            }]
			        },
				title: {
		            display: true,
		            text: 'Gráfico Diario Lineal',
		            fontColor: '#1587c6',
		            fontSize : 15,
		            fontFamily: 'Arial',
		            fontStyle: 'normal'
		        }
			},
			
		});
	}
}

function construirArrayDataLine(listaDashboard, listaEjeX, listaSubParametrosEPSP){
	var arrayDS = [];
	for(var i=0; i<listaSubParametrosEPSP.length; i++){
		var dataUtil = construirListaDataEPSP(listaEjeX, listaDashboard, listaSubParametrosEPSP[i]);
		var randomColor1 = generateRandomColor();
		var value = {
			      label: listaSubParametrosEPSP[i].descripcionSubParametro + "-" + listaSubParametrosEPSP[i].idEPSP,
			      yAxisID: listaSubParametrosEPSP[i].idSubParametro != ConstanteServices.ID_COLIFORME_TERMOTOLERANTES ? 'A' : 'B',
			      fill: false,
			      lineTension: 0.1,
			      backgroundColor: randomColor1,
			      borderColor: randomColor1, // The main line color
			      borderCapStyle: 'round',
			      borderDash: listaSubParametrosEPSP[i].idEPSP == ConstanteServices.COLUMNA_EP ? [] : [10, 5],
			      borderDashOffset: 0.0,
			      borderJoinStyle: 'miter',
			      pointBorderColor: "black",
			      pointBackgroundColor: "white",
			      pointBorderWidth: 1,
			      pointHoverRadius: 8,
			      pointHoverBackgroundColor: randomColor1,
			      pointHoverBorderColor: "brown",
			      pointHoverBorderWidth: 2,
			      pointRadius: 4,
			      pointHitRadius: 10,
			      // notice the gap in the data and the spanGaps: true
			      data: dataUtil,
			      spanGaps: true,
			    };
		arrayDS.push(value);
	}
	return arrayDS;
}

function construirListaDataEPSP(listaEjeX, listaDashboard, subParametroEPSP){
	var listaValores = [];
	for(var i=0; i<listaEjeX.length; i++){
		var flagUtil = null;
		for(var j=0; j<listaDashboard.length; j++){
			if(listaDashboard[j].idSubParametro == subParametroEPSP.idSubParametro && listaDashboard[j].idPtoMuestra == subParametroEPSP.idPtoMuestra && listaDashboard[j].fechaRegistroString == listaEjeX[i]){
				flagUtil = listaDashboard[j].numValor;
			}
		}
		listaValores.push(flagUtil);
	}
	return listaValores;
}


function generarListaslineNewAlter(){
	$.ajax({
        url : "./obtenerListaLineNewAlterDashboard",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
        	fechaInicio: (!IsEmpty(dtpInicio.val()) ? dtpInicio.val() : null),
        	fechaFin: (!IsEmpty(dtpFin.val()) ? dtpFin.val() : null)
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		cargarGrillaLineaNewAlter(JSON.parse(respuestaBean.parametros.listaEjeX), JSON.parse(respuestaBean.parametros.listaDashboard), JSON.parse(respuestaBean.parametros.listaSubParametros));
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function construirListaData(listaEjeX, listaDashboard, descripcionSubParametro){
	var listaValores = [];
	for(var i=0; i<listaEjeX.length; i++){
		var flagUtil = null;
		for(var j=0; j<listaDashboard.length; j++){
			if(listaDashboard[j].descripcionSubParametro == descripcionSubParametro && listaDashboard[j].descripcionPtoMuestra == listaEjeX[i]){
				flagUtil = listaDashboard[j].numValor;
			}
		}
		listaValores.push(flagUtil);
	}
	return listaValores;
}

function cargarGrillaLineaNewAlter(listaEjeX, listaDashboard, listaSubParametros){
	if(lineNewAlter.data.datasets != undefined && lineNewAlter.data.datasets.length != 0){
		lineNewAlter.data.labels = listaEjeX;
		lineNewAlter.data.datasets = [];
		for(var i=0; i<listaSubParametros.length; i++){
			lineNewAlter.data.datasets[i].data = construirListaData(listaEjeX, listaDashboard, listaSubParametros[i]);
		}
		lineNewAlter.update();
	}else{
		var datasetsFormat = construirArrayDataNewLine(listaDashboard, listaEjeX, listaSubParametros);
		lineNewAlter = new Chart(lineNewAlter, {
			  type: 'line',
			  data: {
				  labels: listaEjeX,
				  datasets: datasetsFormat
				},
			  options: {
				  scales: {
					  yAxes: [
					      {
					        id: 'A',
					        type: 'linear',
					        position: 'left',
					        scalePositionLeft: true,
					        ticks: {
			                    beginAtZero:true
			                }
					      },
					      {
					        id: 'B',
					        type: 'logarithmic',
					        position: 'right',
					        scalePositionLeft: false,
					        ticks: {
			                    beginAtZero:true
			                }
					      }
					    ],
						xAxes: [{
			                ticks: {
			                    autoSkip: false,
			                    maxRotation: 90,
			                    minRotation: 90
			                }
			            }]
			        },
				title: {
		            display: true,
		            text: 'Gráfico Diario Lineal',
		            fontColor: '#1587c6',
		            fontSize : 15,
		            fontFamily: 'Arial',
		            fontStyle: 'normal'
		        }
			},
			
		});
	}
}

function construirArrayDataNewLine(listaDashboard, listaEjeX, listaSubParametros){
	var arrayDS = [];
	for(var i=0; i<listaSubParametros.length; i++){
		var dataUtil = construirListaData(listaEjeX, listaDashboard, listaSubParametros[i]);
		var randomColor1 = generateRandomColor();
		var value = {
			      label: listaSubParametros[i],
			      yAxisID: listaSubParametros[i] != 'Coliformes Termotolerantes (CTT) NMP/100 mL' ? 'A' : 'B',
			      fill: false,
			      lineTension: 0.1,
			      backgroundColor: randomColor1,
			      borderColor: randomColor1, // The main line color
			      borderCapStyle: 'round',
			      borderDash: [], // try [5, 15] for instance
			      borderDashOffset: 0.0,
			      borderJoinStyle: 'miter',
			      pointBorderColor: "black",
			      pointBackgroundColor: "white",
			      pointBorderWidth: 1,
			      pointHoverRadius: 8,
			      pointHoverBackgroundColor: randomColor1,
			      pointHoverBorderColor: "brown",
			      pointHoverBorderWidth: 2,
			      pointRadius: 4,
			      pointHitRadius: 10,
			      // notice the gap in the data and the spanGaps: true
			      data: dataUtil,
			      spanGaps: true,
			    };
		arrayDS.push(value);
	}
	return arrayDS;
}

function generateRandomColor() {
    var x = Math.floor(Math.random() * 256);
    var y = Math.floor(Math.random() * 256);
    var z = Math.floor(Math.random() * 256);
    var bgColor = "rgb(" + x + "," + y + "," + z + ")";
    console.log(bgColor);
    return bgColor;
}
//
//function personalizado(e) {
//    switch (e.key) {
//      case 'ArrowRight':
//        var el = document.activeElement;
//        var rowNo = el.id.substring(el.id.indexOf("_")+1);
//        var fieldId = el.id.substring(0, el.id.indexOf("_"));
//        if(fieldId == 'divValor'){
//        	var newId = "divProfundida_"+rowNo;
//	        var newEl = document.getElementById(newId);
//	        if (newEl != null) {
//	          newEl.focus();
//	        }
//        }
//        if(fieldId == 'divProfundida'){
//        	var newId = "divFactor_"+rowNo;
//	        var newEl = document.getElementById(newId);
//	        if (newEl != null) {
//	          newEl.focus();
//	        }
//        }
//        break;
//      case 'ArrowLeft':
//        var el = document.activeElement;
//        var rowNo = el.id.substring(el.id.indexOf("_")+1);
//        var fieldId = el.id.substring(0, el.id.indexOf("_"));
//		if(fieldId == 'divProfundida'){
//			var newId = "divValor_"+rowNo;
//	        var newEl = document.getElementById(newId);
//	        if (newEl != null) {
//	          newEl.focus();
//	        }        	
//		}
//		if(fieldId == 'divFactor'){
//			var newId = "divProfundida_"+rowNo;
//	        var newEl = document.getElementById(newId);
//	        if (newEl != null) {
//	          newEl.focus();
//	        }        	
//		}
//        break;
//    }
//  }


