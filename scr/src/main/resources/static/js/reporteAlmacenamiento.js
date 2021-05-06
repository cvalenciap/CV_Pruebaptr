$(document).ready(function() {
	
	// Inicializamos multiselect
	loadMultiSelectRepresa();
	
	// INICALIZAMOS PARA MANEJO DE PERIODOS
	$('#txtPeriodo').datetimepicker({
        format: 'YYYYMM',
        ignoreReadonly: true,
        date: new Date(),
        maxDate: new Date(),
        locale: "es"
    });
});

function loadMultiSelectRepresa() {
	$('#cbxRepresa').multiselect({
		disableIfEmpty: true,
		includeSelectAllOption: true,
        selectAllValue: 'select-all-value',
        nonSelectedText: 'SELECCIONE REPRESAS',
        nSelectedText: ' REPRESAS SELECCIONADAS',
        allSelectedText: 'TODAS',
        selectAllText: 'SELECCIONAR TODAS',
        buttonWidth: '400px',
        maxHeight: 350,
	});
}

formatDias = function(val, axis){
	return ("0" + val).slice(-2);
}

formatPromedios = function(val, axis) {
	return parseFloat(Math.round(val * 100) / 100).toFixed(2);
}

var dataset = [];
var numCuadriculasVertical = 8;

llenarDatos = function () {
	MYAPPL.blockPageLoad();
	$.ajax({
 	   type: 'GET',
 	   url: "reporteAlmacenamiento/obtenerData",
 	   data: $('#frmReporteGrafico').serialize(),  
 	   success: function(result){
 		   dataset = [];
 		   var listData = result.lstDataReporte;
 		   len = listData.length;
 		   if(len === 0){
 			   dataset = [[]];
 		   }
 		   else{
 			  for ( var i = 0; i < len; i++) {
 				   if(i === 0){
 					   dataset.push({label:listData[i].label,data: listData[i].data, points: { symbol: "triangle"} });
 				   }else{
 					   dataset.push({label:listData[i].label,data: listData[i].data, yaxis:1});
 				   }
 			   }
 		   }
	   },
	   complete: function(result) {
		   if(len == 0){
				  $.unblockUI();
				  $("#chart_plot_02").html("");
				  $("#legendContainer").html("");
				  $.confirm({
					    title: 'Mensaje del Sistema',
					    content: 'No hay datos registrados para el per&iacute;odo y represa(s) ingresados',
					    buttons: {
					    	Aceptar: { 
						        }
						    }
						});	 
			  } 
		   else{
			   var metaReporte = result.responseJSON;
				  var plot = $.plot( $("#wrap_chart_plot_02 #chart_plot_02"), 
							dataset,
							{
							   series: {
								        lines: {
								            show: true
								        },
								        points: {
								            radius: 3,
								            fill: true,
								            show: true            
								        }
						   			},					  
							   xaxis: {
								      axisLabel: metaReporte.strEjexTitulo,
							          min: 1,
							          max: metaReporte.intDiasPeriodo, // Leer dias del periodo
							          tickSize: 1,
							          tickFormatter: formatDias
							        },
							   yaxis: {
								   	  axisLabel: metaReporte.strEjeyTitulo,
							          min:0,
							          //tickSize: parseInt(metaReporte.dblValorMax/numCuadriculasVertical),  // MAX / CANTIDAD DE CUADRICULAS
							          tickFormatter: formatPromedios
							        },
								    
								legend: { 
//										  show: true,
//										  position : "se",
										  container:$("#legendContainer"),            
								          noColumns: 3,
								          labelFormatter: function(label, series){
								              return label+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
								          }
									},
							    grid: {
						            hoverable: true,
						            borderWidth: 2,
						            borderColor: "#633200",
						            backgroundColor: { colors: ["#ffffff", "#EDF5FF"] }
						        },
//						        colors: ["#FF0000", "#0022FF"]
							}
						);
				   $("#chart_plot_02").UseTooltip();
				   $.unblockUI();
		   }
	   }
	}); 
};

var previousPoint = null, previousLabel = null;

$.fn.UseTooltip = function () {
    $(this).bind("plothover", function (event, pos, item) {
        if (item) {
            if ((previousLabel != item.series.label) || (previousPoint != item.dataIndex)) {
                previousPoint = item.dataIndex;
                previousLabel = item.series.label;
                $("#tooltip").remove();

                var x = item.datapoint[0];
                var y = item.datapoint[1];

                var color = item.series.color;
                var dayNumber = ("0" + x).slice(-2);

                if (item.seriesIndex >= 0) { // Tomara todos
                    showTooltip(item.pageX,
                    item.pageY,
                    color,
                    "<strong>" + item.series.label + "</strong><br>DIA " + dayNumber + " : <strong>" + y + "</strong>(m3)");
                }
                
            }
        } else {
            $("#tooltip").remove();
            previousPoint = null;
        }
    });
};

function showTooltip(x, y, color, contents) {
    $('<div id="tooltip">' + contents + '</div>').css({
        position: 'absolute',
        display: 'none',
        top: y - 40,
        left: x - 120,
        border: '2px solid ' + color,
        padding: '3px',
        'font-size': '9px',
        'border-radius': '5px',
        'background-color': '#fff',
        'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
        opacity: 0.9
    }).appendTo("body").fadeIn(200);
}

generarReporteVisual = function () {
	llenarDatos();
};