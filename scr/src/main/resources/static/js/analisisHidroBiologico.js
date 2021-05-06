
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
	
	enableValidationAnalisis();
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
		}
	});
	
});

var ANALISTAS = {};
var MUESTREADORES = {};
var PUNTOS_MUESTREO = {};
var FECHA_MUESTREO = getDDMMYYYY();


function modificarPlanOperativo(id) {
	 
	$.ajax({
		type: 'GET',
		data: dataModificarPlan(id),
		url: 'planOperativoBandejaGet', 
		success: function(result) {
			$('#modal-registra-planoperativo-bandeja').html(result);
			
			if(id != 0){
				$('#idModalTitle').html($('#idValEditModalTitle').html());
				$("#intCodLambda").attr('disabled', 'disabled');
				$("#strCodIgx").attr('disabled', 'disabled');
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			enableValidationRulesBandeja();
		},complete: function() {
			$('#modal-registra-planoperativo-bandeja').modal('show');
			cargarCombo('listAreas',"#cboAreas");
			
			setTimeout(function(){
				var codare = $("#cboAreas").attr("codare");
				console.log("codare: " + codare);
				
				if(codare!=null && codare != undefined){
					$("#cboAreas").val(codare);
				}
				cambioArea(codare);		
			},500)
			
			$.unblockUI();
			
		}
	  });
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
function enableValidationAnalisis() {
	
	var validator = $("#form-edit-analisis-hidro").bootstrapValidator(
			getJsonToValidate())	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").attr('disabled', 'disabled');
		var fechaReporte =  $("#idFechaAnalisisHidro").val();
		e.preventDefault();
		var $nuevoTablaPoissonForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: 'analisisHidroBiologicoSave',
			data: $('#form-edit-analisis-hidro').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', false);
				//$nuevoTablaPoissonForm[0].reset();
				
				setTimeout(function(){
			    			   console.log("activando modal")
			    			   $('#id-modal-plan-bandeja-inactivar').html(result);
			    			   $("#idFechaAnalisisHidro").val(fechaReporte);
    		   },500);
				
			},
			complete: function() {
				$('#guardarButton').removeAttr("enabled");
			}
		});
	});	
}

function obtenerJsonField(nrRows){
	return '"listAnalisis['+nrRows+'].cantidad": {'+
	'					"validators" : {'+
	'						"regexp": {'+
	'							 "regexp":"^\\\\d{1,5}(\\\\.\\\\d{1,2})?$",'+
	'							 "message": "Valor m\u00E1ximo: 5 enteros y 2 decimales"'+
	'						},'+
	'						"notEmpty": {'+
	'	                        "message": "Ingrese valor"'+
	'	                    } '+
	'					}'+
	'	        },'
};

function getJsonToValidate(){
	var allFields = '';
	
			$(".organismoRow").each(function(indexOrganismo){
				console.log("index organismoRow: " + indexOrganismo)
				allFields += obtenerJsonField(indexOrganismo);
			})
	
	allFields = allFields.substring(0, allFields.length-1);
//	var allFields = {};
	
	var jsnToValidate = '{"style" : {'+
	'		"message": "El valor no es v\u00e1lido",'+
	'		"excluded": [":disabled", ":hidden", "#strParaAdd", "#strCcAdd"],'+
	'		"feedbackIcons" : {'+
	'			"valid" : "glyphicon glyphicon-ok",'+
	'			"invalid" : "glyphicon glyphico-remove",'+
	'			"validating" : "glyphicon glyphicon-refresh"'+
	'		},'+
	'		"fields" : {'+
				allFields +
	'		}'+
	'	}}'
	
	var json = JSON.parse(jsnToValidate);
	console.log(">>" + json);
  return json["style"];
}

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarPlanOperativo(id) {
	console.log("inactivarPlanOperativo ....")
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma inactivar Registro ?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
				    $.ajax({
			    	   type: 'POST',
			    	   url: 'inactivarPlanOperativo/'+id,
			    	   success: function(result){		   
			    		   setTimeout(function(){
			    			   console.log("activando modal")

			    			   $('#idTablaPlanOperativoBandeja').DataTable().ajax.reload();
			    			   $('#id-modal-plan-bandeja-inactivar').html(result);
			    			   
			    		   },500);
				    	  },
				    	}); 
		        	}
		        },
		        Cancelar: {
		        	
		        }
		    }
		});	   
};

function aprobarPlan(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BF Confirma aprobar Plan Operativo \u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
				    $.ajax({
			    	   type: 'POST',
			    	   url: 'aprobarPlan/'+id,
			    	   success: function(result){  		   
			    		   	$("#btnActualizarDescri").data("aprobado",0);
			    		   	$("#btnActualizarDescri").attr("disabled","disabled");
			    		   	
			    		   	
				    	   },
							complete: function() {
								$('#myPlanOperativoDetalle').DataTable().ajax.reload();
								$(".param").addClass("hidden")
							}
				    	}); 
		        	}
		        },
		      Cancelar: {
		        }
		    }
		});	   
};



function showToastPlanOperativoBandeja(mensajeTipo, mensaje, mensajeError) {
	console.log("entrando al toast")
	
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, mensaje);
		 console.log("existente param")
		 setTimeout(function(){
			 
			 $('#idTablaPlanOperativoBandeja').DataTable().ajax.reload();
			 $('#modal-registra-planoperativo-bandeja').modal('hide');
			 $('#id-modal-parametroplan').addClass('hidden');
			 $('#myPlanOperativoDetalle').DataTable().ajax.reload();
			 
		 },1000)
	 }
	
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, mensaje);
	 }
	 
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, mensaje);
	 }
	 
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, mensaje);
	 }
	 
	 if(mensajeTipo==='parametroPlanExistente'){
		 toastr["error"](mensajeError, mensaje);
		 console.log("gol inexistente")
		 setTimeout(function(){
			 $('#modal-registra-parametroplan').modal('hide');
		 },400)
	 }
	 
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
			url: 'refrescarReportePorFecha',
			success: function(result){
				console.log("result refrescarReportePorFecha: " + result);
				$('#tablaReporteHidro').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				$.unblockUI();
			}
		});
}


function cargarTablaHidroBiologiaDetalle(){
	var fechaReporte = $("#idFechaReporteHidro").val();
	
	if(fechaReporte == undefined || fechaReporte == null || fechaReporte == ""){
		fechaReporte = getDDMMYYYY();
	}
	console.log("fechaReporte: " + fechaReporte);
	
	$('#tablaReporteHidroBiologicoDetalle').DataTable({
		"bProcessing": true,
		"serverSide": true,
		"searching": false,
		"lengthChange":   false,
		"ajax":{
			url :"reporteAnalisisHidroBiologico/pagination", // json datasource
            type: "post",  // type of method  ,GET/POST/DELETE
            error: function(){
              $("#myTableTablaPoisson_processing").css("display","none");
            },
            "data": function ( d ) {
            	
                d.fechaReporte = fechaReporte;
            }
		},
		"order": [ 4, 'desc' ],
		"columnDefs": [ 
			{ targets: '_all', visible: true,  },
			{"width": "50px",
        		className: "dt-body-center",
                "targets": 0                 
            },
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 1,               
            },
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 2,               
            },
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 3,               
            },
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 4,               
            },
            
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 5,               
            },
            
          	{"width": "40px",
          		className: "dt-body-center",
          		"orderable": false,
          		"targets": 0,
          		visible: true,
          		"data": 1,
          		"render": function ( data, type, row) {
          			return '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="modificarTablaPoisson('+row[4]+')"><span class="glyphicon glyphicon-pencil"></span></button>'+
          			 '<button class="btn btn-danger btn-delete-punto" title="Eliminar" onclick="inactivarTablaPoisson('+row[4]+')"><span class="glyphicon glyphicon-erase"></span></button>';
          		}
          	}
		]
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
		"ajax":{
			url :"reporteAnalisisHidroBiologico/pagination", // json datasource
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
		"order": [ 1, 'desc' ],
		"columnDefs": [ 
			{ targets: '_all', visible: true,  },
			{"width": "150px",
        		className: "dt-body-center",
                "targets": 0                 
            },
            {"width": "150px",
        		className: "dt-body-center",
                "targets": 1,               
            },
            {"width": "150px",
        		className: "dt-body-center",
                "targets": 2,               
            },
            {"width": "150px",
        		className: "dt-body-center",
                "targets": 3,               
            },
            {"width": "150px",
        		className: "dt-body-center muestreadores",
                "targets": 4,               
            },
            
            {"width": "150px",
        		className: "dt-body-center analistas",
                "targets": 5,               
            },
            
          	{"width": "150px",
          		className: "dt-body-center",
          		"orderable": false,
          		"targets": 0,
          		visible: true,
          		"data": 1,
          		"render": function ( data, type, row) {
          			return '<button class="btn btn-info btn-modif-punto" title="Ver detalle" onclick="reporteAnalisisHidroSearch('+row[1]+')"><span class="glyphicon glyphicon-search"></span></button>'+
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
				$('#tablaReporteHidroDetalle').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
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
			url: 'reporteAnalisisHidroBiologicoSave',
			data: $('#form-edit-reporte-hidro-detalle').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', false);
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
	
	var selects = document.getElementsByTagName('select');
	var sel;
	var relevantSelects = [];
	for(var z=0; z<selects.length; z++){
	     sel = selects[z];
	     if(sel.name.indexOf('detalles[') === 0 && sel.value == ''){
	    	 MYAPPL.showToast("genericNoOk", "Antes de agregar la fila debe seleccionar en punto de muestreo para los detalles que han sido agregados.");
	    	 return;
	     }
	}
	
	var nroFila = $("#detalleReporteAnalisis tr").length;	
	agregarFilaVaciaEnIndice(nroFila);
}

function obtenerFila(nroFila){
	var fila = '<tr>'+
	'<td class="text-center"></td>'+
	'<td><select class="cboLocalidades form-control" name="detalles['+nroFila+'].codsub" indiceRow='+nroFila+'></select></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
	'<td class="text-center"></td>'+
		'<input type="hidden" id="detalles'+nroFila+'.algas" name="detalles['+nroFila+']." value="88">'+
		'<input type="hidden" id="detalles'+nroFila+'.cianobacterias" name="detalles['+nroFila+'].cianobacterias" value="88">'+
		'<input type="hidden" id="detalles'+nroFila+'.nematodes" name="detalles['+nroFila+'].nematodes" value="88">'+
		'<input type="hidden" id="detalles'+nroFila+'.otros" name="detalles['+nroFila+'].otros" value="88">'+
		'<input type="hidden" id="detalles'+nroFila+'.total" name="detalles['+nroFila+'].total" value="889">'+
		'<input type="hidden" id="detalles'+nroFila+'.minam" name="detalles['+nroFila+'].minam" value="889">'+
	'</tr>'
	
	return fila;
}

function agregarFilaVaciaEnIndice(nroFila){
	
	$("#detalleReporteAnalisis").append(obtenerFila(nroFila))
		setTimeout(function(){
			cargarComboSelectable("listPuntos",$(".cboLocalidades").eq(nroFila));
			//cargarCalendarioPicker("#idFechaAnalisisHidro");
		},200);
}


function reemplazarFilaVacia(nroFila){
	
	$("#detalleReporteAnalisis").find('tr').eq(nroFila).replaceWith($(obtenerFila(nroFila)))
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
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'cargarRegistro',
			data: dataCargarRegistro(nroFila,valorPuntoMuestreo,valueSelectPunto),
			success: function(result){
				
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
//				cargarPuntosAnalisis("listPuntos",$(".cboLocalidades").eq(nroFila));
				
				loadDateOnInput('#idFechaAnalisisHidro');
				
				console.log("indice valueSelectPunto : " + valueSelectPunto + " ; nroFila: " + nroFila);
				
				
				
				$.unblockUI();
			}
		});
}


function actualizarLocalidad(inputSelected){
	var valueChanged = $(inputSelected).find(":selected").attr("data-id");
	console.log("verificando que "+valueChanged+ " No exista en otros combos");
	
	var c=0;//cuenta cuantos existentes con el mismo valor
	$(".cboLocalidades").each(function(index){
		var valueSelected =  $(this).find(":selected").attr("data-id");
		console.log("val " + index + ": "+ $(this).val());
		if(valueChanged == valueSelected){
			c++;
		}
	})
	
	if(c>1){
		 var result = $(mensajeCboExistente());
		 $(inputSelected).val("");
		  $('#container-msg-combo-existing').html(result);
		  var indiceLimpiar = $(inputSelected).attr("indicerow");
		  console.log("limpiando el indice: "+indiceLimpiar);
		  reemplazarFilaVacia(indiceLimpiar);
		  return 0;
	}else{
	
	var valorPuntoMuestreo = $(inputSelected).find(":selected").attr("data-id");
	var valueSelectPunto = $(inputSelected).val();
	var indexRow =  $(inputSelected).attr("indicerow");
	console.log("indexRow: " + indexRow);
	aumentarFilaTabla(indexRow,valorPuntoMuestreo,valueSelectPunto);
	console.log("valorPuntoMuestreo: " + valorPuntoMuestreo);
	}
}


$('#tablaReporteHidroDetalle').on("change",".cboLocalidades",function() {
	actualizarLocalidad(this);
	
//	var valueChanged = $(this).find(":selected").attr("data-id");
//	console.log("verificando que "+valueChanged+ " No exista en otros combos");
//	
//	var c=0;//cuenta cuantos existentes con el mismo valor
//	$(".cboLocalidades").each(function(index){
//		var valueSelected =  $(this).find(":selected").attr("data-id");
//		console.log("val " + index + ": "+ $(this).val());
//		if(valueChanged == valueSelected){
//			c++;
//		}
//	})
//	
//	if(c>1){
//		 var result = $(mensajeCboExistente());
//		 $(this).val("");
//		  $('#container-msg-combo-existing').html(result);
//		  var indiceLimpiar = $(this).attr("indicerow");
//		  console.log("limpiando el indice: "+indiceLimpiar);
//		  reemplazarFilaVacia(indiceLimpiar);
//		  return 0;
//	}else{
//	
//	var valorPuntoMuestreo = $(this).find(":selected").attr("data-id");
//	var valueSelectPunto = $(this).val();
//	var indexRow =  $(this).attr("indicerow");
//	console.log("indexRow: " + indexRow);
//	aumentarFilaTabla(indexRow,valorPuntoMuestreo,valueSelectPunto);
//	console.log("valorPuntoMuestreo: " + valorPuntoMuestreo);
//	}
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

function mensajeCboExistente(){
	var msg = '<script>/*<![CDATA[*/  mensajeTipo = "grabadoNoOk";'+
	'    mensajeMostrar = "";'+
	'    mensajeError = "Valor seleccionado ya existe";		'+
	'/*]]>*/'+
	'showToastGeneric(mensajeTipo,mensajeMostrar, mensajeError)'+	
	'</script>';	

    return msg;
}


//$("#tablaAnalisisHidro").change("#cboOrganismos", function(){
//	console.log("cambiando de organismos");
//	cargarOrganismos();
//	
//});
function cargarOrganismos(){
	console.log("refrescando tabla por organismos")
	var fechaReporte = $("#idFechaAnalisisHidro").val();
	var valorPunto = $("#cboPuntos").val();
	
	if(fechaReporte == undefined || fechaReporte == null || fechaReporte == ""){
		fechaReporte = getDDMMYYYY();
	}
	
	console.log("fechaReporte: " + fechaReporte);
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'cargarOrganismos',
			data: dataCargarOrganismos(),
			success: function(result){
				$('#tablaAnalisisHidro').html(result);
			},
			complete: function() {
				$('#guardarButton').removeAttr("disabled");
				cargarPuntosAnalisis("listPuntos","#cboPuntos");
				
				cargarCalendarioPicker("#idFechaAnalisisHidro");
				
				setTimeout(function(){
					$("#idFechaAnalisisHidro").val(fechaReporte);
					$("#cboPuntos").val(valorPunto);
				},200);
				
				
				enableValidationAnalisis();
				$.unblockUI();
			}
		});
}

function dataCargarOrganismos() {
	console.log("puntoMuestra: " + $("#cboPuntos").val());
	
	var data = {};
	data.fechaAnalisis = $("#idFechaAnalisisHidro").val();
	data.tipoOrganismo = $("#cboOrganismos").val();
	data.puntoMuestra =  $("#cboPuntos").val();
	return data;
	
}

function dataCargarRegistro(indiceRow,puntoMuestra,valueSelectPunto) {
	
	var data = {};
	data.indiceRow = indiceRow;
	data.puntoMuestra = puntoMuestra;
	data.valueSelectPunto = valueSelectPunto;// value delm html del combo, sirve para regresar el valor al combo
	data.fechaMuestreo = $("#idFechaAnalisisHidro").val();
	data.idMuestrador =  $("#cboMuestrador").find(":selected").attr("data-id");
	data.idAnalista =  $("#cboAnalista").find(":selected").attr("data-id");
	data.indiceRow = indiceRow;
	return data;
	
}



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
		
		console.log("valorNuevo : " + valorNuevo);
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
			$(this).prop("value",valorNuevo);
		}
	})
}


//$('#detalleReporteAnalisis').on("click",'.inactivar-registro-detalle', function(){
//	
//	  var nroFila = $(this).attr("indicerow");
//	  
//	  console.log("eliminando nroFila : " + nroFila);
//	  eliminarRegistroPorIndice(nroFila);
//	  
//});

function eliminarRegistroIndex(elem){
	var nroFila = $(elem).attr("indicerow");
	eliminarRegistroPorIndice(nroFila);
}

//$('#inactivar-registro-detalle').click(function(){
//	  var nroFila = $(this).attr("indicerow");
//	  
//	  console.log("eliminando nroFila : " + nroFila);
//	  eliminarRegistroPorIndice(nroFila);
//	  
//});

var registroDelete = function(param) {
   // param.innerHTML = "Not a button";
    console.log("param: " + param.HTML);
};


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
			url: 'reporteAnalisisHidroSearch/'+ nroCorrelativo,
			success: function(result){
				$('#tablaReporteHidroDetalle').html(result);
			},
			complete: function() {
				
				cargarPuntosAnalisis("listPuntos",".cboLocalidades");
				setTimeout(function(){
					cargarNombresMuestreadores();
					cargarNombresPuntosMuestreo();	
					cargarComboForField("listAnalistas","#cboAnalista");
					cargarComboForField("listMuestreadores","#cboMuestrador");
				},100);
				
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

function loadDateOnInput(idInput){
	
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate: new Date()
	}) .on('changeDate', function(e) {
	});

	
}

$("#idFechaAnalisisHidro").on("blur",function(){
	
	var fechaMuestreoElegida = $("#idFechaAnalisisHidro").val();
	if(FECHA_MUESTREO!==fechaMuestreoElegida){// FECHA_MUESTREO nace con fecha actual
		$("#detalleReporteAnalisis").html("");
		FECHA_MUESTREO = fechaMuestreoElegida;
	}
	
});

//$('#idFechaAnalisisHidro').on('blur', function(e) {
////	console.log("loadinggggg")
//	
//	console.log("actualizado de fecha")
//	$(".cboLocalidades").each(function(index){
//		console.log("index: " + index);
//	})
//})

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
	    content: '\u00BFConfirma eliminar el reporte N\u00B0 ' + idCorrelativo + ' \u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
	        		
	        		$.ajax({
	        			type: 'POST',
	        			url: 'eliminarReporteHidroPorCorrelativo/' + idCorrelativo ,
	        			success: function(result){
	        				$('.container_save_reporte').html(result);
	        			},
	        			complete: function() {
	        				refrescarTablaHidroBiologia();
	        			}
	        		});
	        		   
		        	}
		        },
		      Cancelar: {
		        }
		    }
		});
 }

function valoresCombos(){
	$(".cboLocalidades").each(function(index){
		console.log("val " + index + ": "+ $(this).val());
		$(this).val("");
		
	})
}

