
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTablaPoisson').click(function () {		 
		 $('#cboLambdasHidden').val($('#cboLambdas').val());
		 $('#myTableTablaPoisson').DataTable().ajax.reload();
	});
	
	//CARGA EL COMBO DE LAMBDAS
	cargarCombo("listAreas","#cboAreas");
	cargarComboTodos("listAreas","#cboAreasPlan");
	
	
	cargarComboForField("listAnalistas","#cboAnalista");
	cargarComboForField("listMuestreadores","#cboMuestrador");
	
	cargarPuntosAnalisis("listPuntos","#cboPuntos");//cboOrganismos
	cargarComboSelectable("listPuntos",".cboLocalidades");
	
	loadDateOnInput('#idFechaAnalisisHidro');
	loadDateOnInput('#idFechaReporteHidro');
	
	$("#idFechaAnalisisHidro").val(getDDMMYYYY());
	$("#idFechaReporteHidro").val(getDDMMYYYY());
	
	enableValidationReporteHidroDetalle();
	
	$.getJSON("listAnalistas", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		
		for ( var i = 0; i < len; i++) {
			ANALISTAS[data[i].id] = data[i].label;
		}
	});
	
	$.getJSON("listMuestreadores", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		
		for ( var i = 0; i < len; i++) {
			MUESTREADORES[data[i].id] = data[i].label;
		}
	});
	
	$.getJSON("listPuntos", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		
		for ( var i = 0; i < len; i++) {
			PUNTOS_MUESTREO[data[i].id] = data[i].label;
			PUNTOS_MUESTREO_VALUE[data[i].id] = data[i].value;
		}
	});
	
});

var ANALISTAS = {};
var MUESTREADORES = {};
var PUNTOS_MUESTREO = {};

var PUNTOS_MUESTREO_VALUE = {};

function consultarReporteXFecha() {
	var fecha = $("#idFechaReporteHidro").val();
	
}


function dataModificarPlan(codplan) {
	var data = {};
	
	if(codplan==0){
		
		data.codplan = codplan; 
		
	} else {
		
		var atributes = codplan.split("#");
		data.codplan = atributes[0]; 
		data.codDescPlan = atributes[1]; 
		data.descripcion = atributes[3];
		data.codare = atributes[2];
	}
	
	return data;
}


//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION


function refrescarTablaHidroBiologia(){
	
	$('#tablaReporteHidroBiologico').DataTable().ajax.reload();
}

function refrescarReportePorFecha(){
	console.log("refrescando tabla por fecha")
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'reporteFitoplanctonPorFecha',
			success: function(result){
				console.log("result: " + result);
				$('#tablaReporteHidro').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				$.unblockUI();
			}
		});
}



function cargarTablaHidroBiologia(){
	var fechaReporte = $("#idFechaReporteHidro").val();
	
	if(fechaReporte == undefined || fechaReporte == null || fechaReporte == ""){
		fechaReporte = getDDMMYYYY();
	}
	console.log("fechaReporte: " + fechaReporte);
	
	$('#tablaReporteHidroBiologico').DataTable({
		"bProcessing": true,
		"serverSide": true,
		"searching": false,
		"lengthChange":   false,
		"scrollX": true,
		"bJQueryUI": true,
		"ajax":{
			url :"reporteFitoplancton/pagination", // json datasource
            type: "post",  // type of method  ,GET/POST/DELETE
            error: function(){
              $("#myTableTablaPoisson_processing").css("display","none");
            },
            "data": function ( d ) {
            	
                d.fechaReporte = fechaReporte;
            },
            complete: function(xhr,status){
            	setTimeout(function(){
            		cargarNombresMuestreadores();
                	cargarNombresAnalistas();
            	},200)
            }
		},
		"order": [ 4, 'desc' ],
		"columnDefs": [ 
			{ targets: '_all', visible: true,  },
			{"width": "150px",
        		className: "dt-body-center",
                "targets": 0                 
            },
            {"width": "80px",
        		className: "dt-body-center",
                "targets": 1,               
            },
            {"width": "150px",
        		className: "dt-body-center",
                "targets": 2,               
            },
            {"width": "200px",
        		className: "dt-body-center",
                "targets": 3,               
            },
            {"width": "200px",
        		className: "dt-body-center muestreadores",
                "targets": 4,               
            },
            
            {"width": "280px",
        		className: "dt-body-center analistas",
                "targets": 5,               
            },
            
          	{"width": "275px",
          		className: "dt-body-center",
          		"orderable": false,
          		"targets": 0,
          		visible: true,
          		"data": 1,
          		"render": function ( data, type, row) {//
          			return '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="reporteAnalisisHidroSearch('+row[1]+')"><span class="glyphicon glyphicon-search"></span></button>'+
          			 '<button class="btn btn-danger btn-delete-punto" title="Eliminar" onclick="eliminarReportePorCorrelativo('+row[1]+')"><span class="glyphicon glyphicon-erase"></span></button>';
          		}
          	}
		]
	});
}	
	

function buscarReporteDetalle(){
	console.log("refrescando tabla por fecha")
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'buscarReporteDetalle',
			success: function(result){
				console.log("result: " + result);
				$('#tablaReporteHidroDetalle').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
//				enableValidationRulesBandeja1();
				$.unblockUI();
			}
		});
}


function enableValidationReporteHidroDetalle() {

	var validator = $("#form-edit-reporte-hidro-detalle").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			
			fechaMuestreo: {
            	 validators: {
            		 notEmpty: {
            		   message: 'Debe ingresar alguna fecha'
            		 },
            		 date: {
            		   format: 'DD/MM/YYYY',
            		   message: 'The format is MM/DD/YYYY'
            		 }
            	   }
 	        }, 
            	 
			codigoDescripcionPlan: {
					validators : {
						regexp: {
							 regexp:/^[a-z0-9\u00E1\u00E9\u00ED\u00F3\u00FA\u00FC\u00F1\s\.-]+$/i,
							 message: 'Ingrese valor alfanumerico'
						},
						stringLength : {
							max : 5,
							message : 'Campo incorrecto'
						},
						notEmpty: {
	                        message: 'Ingrese valor alfanumerico'
	                    } 
					}
	        },
	        descripcion: {
				validators : {
					regexp: {
						 regexp:/^[a-z0-9\u00E1\u00E9\u00ED\u00F3\u00FA\u00FC\u00F1\s\.-]+$/i,
						 message: 'Ingrese valor alfanumerico'
					},
					stringLength : {
						max : 5,
						message : 'Ingrese valor alfanumerico'
					},
					notEmpty: {
                        message: 'Ingrese valor alfanumerico'
                    }
				}
          },
         
        
           
		}
	})	
	.on('success.form.bv', function(e, data) {
		
		var fechaMuestreo = $("#idFechaAnalisisHidro").val();
		e.preventDefault();
		
		var $nuevoTablaPoissonForm = $(e.target);
		console.log("guardando cambios para reporte");
		
		$.ajax({
			type: 'POST',
			url: 'reporteFitoplanctonSave',
			data: $('#form-edit-reporte-hidro-detalle').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', true);
				$nuevoTablaPoissonForm[0].reset();
				
				$('.container_save_reporte').html(result);
			},
			complete: function() {
				
				$("#buttonCloseDetail").click();
				
				console.log(" refrescando tabla superior")
				refrescarTablaHidroBiologia();

				setTimeout(function(){
					$("#idFechaAnalisisHidro").val(fechaMuestreo);
					
				},500);
				
				limpiarDetalle();
			}
		});
	});	
}


function agregarFilaVacia(){
	var nroFila = $("#detalleReporteAnalisis tr").length;
	$("#detalleReporteAnalisis").append(obtenerFilaFitoPlancton(nroFila))
	setTimeout(function(){
		cargarComboSelectable("listPuntos",$(".cboLocalidades").eq(nroFila));
	},200);
	
}

function obtenerFilaFitoPlancton(nroFila){
	var fila = '<tr>'+
	'<td class="text-center"></td>'+
	'<td><select class="cboLocalidades form-control" name="detalles['+nroFila+'].codsub" indiceRow='+nroFila+'></select></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<input type="hidden" id="detalles'+nroFila+'.algas" name="detalles['+nroFila+'].fechaMuestreo" value="88">'+
	'<input type="hidden" id="detalles'+nroFila+'.cianobacterias" name="detalles['+nroFila+'].clorofila" value="88">'+
	'<input type="hidden" id="detalles'+nroFila+'.nematodes" name="detalles['+nroFila+'].ficocianina" value="88">'+
	'</tr>'
	return fila;
}

function reemplazarFilaVacia(nroFila){
	
	$("#detalleReporteAnalisis").find('tr').eq(nroFila).replaceWith($(obtenerFilaFitoPlancton(nroFila)))
		setTimeout(function(){
			cargarComboSelectable("listPuntos",$(".cboLocalidades").eq(nroFila));
			//cargarCalendarioPicker("#idFechaAnalisisHidro");
		},200);
}

function aumentarFilaTabla(nroFila,valorPuntoMuestreo,valueSelectPunto){
	
	var length = $("#detalleReporteAnalisis tr").length;
	if(nroFila!=0){
		console.log("removiendo fila " + nroFila);
		$("#detalleReporteAnalisis tr").eq(nroFila).remove();
	}
	
	var rowToAboutAppend = nroFila-1
	console.log("agregando fila " + nroFila);
	
		$.ajax({
			type: 'GET',
			url: 'cargarRegistroFitoplancton',
			data: dataCargarRegistro(nroFila,valorPuntoMuestreo,valueSelectPunto),
			success: function(result){
				//enableValidationReporteHidroDetalle();
				
				console.log("result: " + result);
				var $resultado = $(result);
				 
				if(nroFila == length){
					
					$("#detalleReporteAnalisis").html($resultado);
					
				} else if(nroFila == 0){
					$resultado.insertBefore($("#detalleReporteAnalisis tr:first-child"))
					$("#detalleReporteAnalisis tr").eq(1).remove();
					
				} else {
					
					$resultado.insertAfter($("#detalleReporteAnalisis tr ").eq(rowToAboutAppend));
				}
				 
			},
			complete: function() {
				$('#guardarButton').removeAttr("disabled");
				cargarPuntosAnalisisAndPutValue("listPuntos",$(".cboLocalidades").eq(nroFila),valorPuntoMuestreo);
				
				loadDateOnInput('#idFechaAnalisisHidro');
				
				console.log("indice valueSelectPunto : " + valueSelectPunto + " ; nroFila: " + nroFila);
				
				setTimeout(function(){
					$(".cboLocalidades").eq(nroFila).val(valorPuntoMuestreo)
				},200);
				
			}
		});
}

function cargarPuntosAnalisisAndPutValue(urlJson,idSelect,value){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option data-id="'+ data[i].id +'" value="' + data[i].id + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
		
		$(idSelect).html(html);
		
//		setTimeout(function(){
			$(idSelect).val(value)
//		},50);
		
		
		$.unblockUI();
	});
}


$('#tablaReporteHidroDetalle').on("change",".cboLocalidades",function() {
	
	var valorPuntoMuestreo = $(this).find(":selected").attr("data-id");
	
	console.log("verificando que "+valorPuntoMuestreo+ " No exista en otros combos");
	
	var c=0;//cuenta cuantos existentes con el mismo valor
	$(".cboLocalidades").each(function(index){
		var valueSelected =  $(this).find(":selected").attr("data-id");
		console.log("val " + index + ": "+ $(this).val());
		if(valorPuntoMuestreo == valueSelected){
			c++;
		}
	})
	
	if(c>1){
		 var result = $(mensajeCboExistente());
		 $(this).val("");
		  $('#container-msg-combo-existing').html(result);
		  var indiceLimpiar = $(this).attr("indicerow");
		  console.log("limpiando el indice: "+indiceLimpiar);
		  reemplazarFilaVacia(indiceLimpiar);
		  return 0;
	}else{
	
	var valueSelectPunto = $(this).val();
	var indexRow =  $(this).attr("indicerow");
	console.log("indexRow: " + indexRow);
	console.log("valorPuntoMuestreo: " + valorPuntoMuestreo);
	
	aumentarFilaTabla(indexRow,valorPuntoMuestreo,valueSelectPunto);
	console.log("valorPuntoMuestreo: " + valorPuntoMuestreo);
	}
});

function mensajeCboExistente(){
	var msg = '<script>/*<![CDATA[*/  mensajeTipo = "grabadoNoOk";'+
	'    mensajeMostrar = "";'+
	'    mensajeError = "Valor seleccionado ya existe";		'+
	'/*]]>*/'+
	'showToastGeneric(mensajeTipo,mensajeMostrar, mensajeError)'+	
	'</script>';	

    return msg;
}


function dataCargarOrganismos() {
	
	var data = {};
	data.fechaAnalisis = $("#idFechaAnalisisHidro").val();
	data.tipoOrganismo = $("#cboOrganismos").val();
	return data;
	
}

function dataCargarRegistro(indiceRow,puntoMuestra,valueSelectPunto) {
	
	var data = {};
	data.indiceRow = indiceRow;
	data.puntoMuestra = PUNTOS_MUESTREO_VALUE[puntoMuestra];
	data.valueSelectPunto = valueSelectPunto;// value delm html del combo, sirve para regresar el valor al combo
	data.fechaMuestreo = $("#idFechaAnalisisHidro").val();
	data.idMuestrador =  $("#cboMuestrador").find(":selected").attr("data-id");
	data.idAnalista =  $("#cboAnalista").find(":selected").attr("data-id");
	data.indiceRow = indiceRow;
	return data;
	
}

$("#idFechaAnalisisHidro").on("blur",function(){
	console.log("cambiando fecha " + $("#idFechaAnalisisHidro").val());
	
});


function cargarCalendarioPicker(idInput){
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: false,
        date: null,
        locale: "es"
	});
}

function limpiarDetalle(){
	
	$( "#detalleReporteAnalisis").children().remove();
	bloquearSeleccionables(false);
	loadDateOnInput("#idFechaAnalisisHidro");
	$("#idFechaAnalisisHidro").val(getDDMMYYYY());
}

function cargarNombresMuestreadores(){
	$(".muestreadores").each(function(){
		console.log("MUESTREADORES : " + JSON.stringify(MUESTREADORES) );
		var valoraMuestreador = $(this).text();
		
		console.log("valoraMuestreador: " + valoraMuestreador);
		var valorNuevo = MUESTREADORES[valoraMuestreador];
		
		console.log("valorNuevo: " + valorNuevo);
		
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
		}
	})
}

function cargarNombresAnalistas(){
	
	$(".analistas").each(function(){
		
		var valoraAnalista = $(this).text();
		console.log("valoraAnalista : " + JSON.stringify(ANALISTAS));
		
		var valorNuevo = ANALISTAS[valoraAnalista];
		
		console.log("valorNuevo : " + valorNuevo);
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
		}
	});
	
}

function cargarNombresPuntosMuestreo(){
	
	$(".cboLocalidades").each(function(){
		
		var valorPunto = $(this).attr("idpunto");
		$(this).val(valorPunto);
		console.log("valorPunto posibles: " + JSON.stringify(PUNTOS_MUESTREO));
		console.log("valorPunto : " + valorPunto);
		var valorNuevo = PUNTOS_MUESTREO[valorPunto];
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
			$(this).prop("value",valorNuevo);
		}
		
	})
}


$('#detalleReporteAnalisis').on("click",'.inactivar-registro-detalle', function(){
	
	  var nroFila = $(this).attr("indicerow");
	  eliminarRegistroPorIndice(nroFila);
	  
});

function mensajeEliminadoOk(){
	var msg = '<script>/*<![CDATA[*/  mensajeTipo = "grabadoOk";'+
	'    mensajeMostrar = "Registro Eliminado Correctamente";'+
	'    mensajeError = "";		'+
	'/*]]>*/'+
	'showToastGeneric(mensajeTipo,mensajeMostrar, mensajeError)'+	
	'</script>';	

    return msg;
}

function eliminarRegistroPorIndice(nroIndex){
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar el registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
	        		
	        		 $("#detalleReporteAnalisis tr").each(function(){
	        			 var indice =  $(this).attr("indicerow");
	        			 console.log("indice: " + indice);
	        			 if(indice == nroIndex){
	        				 $(this).remove();
	        			 }
	        		  })
	        		 
	        		  setTimeout(function(){
//	        			  var result = $(mensajeEliminadoOk());
//	 	        		 console.log("result eliminado: " + result)
	 	        		  $('#container_save_reporte').html(mensajeEliminadoOk());
	        		  },300)
	        		 
		        	}
		        },
		      Cancelar: {
		        }
		    }
		});
	
	
}

function reporteAnalisisHidroSearch(nroCorrelativo){
console.log("reporteAnalisisHidroSearch")
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'reporteFitoplanctonSearch/'+ nroCorrelativo,
			success: function(result){
				console.log("result: " + result);
				$('#tablaReporteHidroDetalle').html(result);
			},
			complete: function() {
				cargarPuntosAnalisis("listPuntos",".cboLocalidades");
				setTimeout(function(){
					cargarNombresMuestreadores();
					cargarNombresPuntosMuestreo();	
					cargarComboForField("listAnalistas","#cboAnalista");
					cargarComboForField("listMuestreadores","#cboMuestrador");
				},500);
				
				$("#buttonAgregar").attr('disabled', 'disabled');
				
				bloquearSeleccionables(true);
				enableValidationReporteHidroDetalle();
				$.unblockUI();
			}
		});
}

function cargarPuntosAnalisis(urlJson,idSelect){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option data-id="'+ data[i].id +'" value="' + data[i].id + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$.unblockUI();
	});
}

function loadDateOnInput(idInput){
	
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate : new Date(),
	}) .on('changeDate', function(e) {
	});
	

}

$('#idFechaAnalisisHidro').on('dp.change dp.show', function(e) {
	console.log("loadinggggg")
})

function bloquearSeleccionables(flagBloqueo){
	if(flagBloqueo){
		$("#guardarButton").attr('disabled', 'disabled');
		$("#buttonAgregar").attr('disabled', 'disabled');
		$("#cboAnalista").attr('disabled', 'disabled');
		$("#cboMuestrador").attr('disabled', 'disabled');
		$(".cboLocalidades").attr('disabled', 'disabled');
		$("#inputMuestrador").show();
		$("#cboMuestrador").hide();
		$("#inputAnalista").show();
		$("#cboAnalista").hide();
		
		// pasando los codigos a nombres
		setTimeout(function(){
			var keyMuestreador =  $("#inputMuestrador").val();
			$("#inputMuestrador").val(MUESTREADORES[keyMuestreador]);
			
			var keyAnalista =  $("#inputAnalista").val();
			$("#inputAnalista").val(ANALISTAS[keyAnalista]);
			
		},200);
		
	}else{
		$("#guardarButton").removeAttr('disabled');
		$("#buttonAgregar").removeAttr('disabled');
		$("#cboAnalista").removeAttr('disabled');
		$("#cboMuestrador").removeAttr('disabled');
		$(".cboLocalidades").removeAttr('disabled');
		$("#inputMuestrador").hide();
		$("#cboMuestrador").show();
		$("#inputAnalista").hide();
		$("#cboAnalista").show();
		
	}
}

function eliminarReportePorCorrelativo(idCorrelativo){
	
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar el registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
	        		$.ajax({
	        			type: 'POST',
	        			url: 'eliminarReportePorCorrelativo/' + idCorrelativo ,
	        			success: function(result){
	        				console.log("result eliminado: " + result)
	        				$('.container_save_reporte').html(result);
	        			},
	        			complete: function() {
	        				
	        				refrescarTablaHidroBiologia();
	        				setTimeout(function(){
	   	 	        		  $('#container_save_reporte').html(mensajeEliminadoOk());
	   	        		  },300)
	        				
	        			}
	        		});
	        		
		        	}
		        },
		      Cancelar: {
		        }
		    }
		});
	

}




