/**
 * Hoja de scripts manejados en el mantenimiento de clima
 */
$(document).ready(function() {
	
	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"newestOnTop" : false,
		"progressBar" : false,
		"positionClass" : "toast-top-center",
		"preventDuplicates" : false,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
	
	cargarComboTodos("listAreas","#cboArea");

	function mergeCommonRows(table) {
	    var firstColumnBrakes = [];
	    // iterate through the columns instead of passing each column as function parameter:
	    for(var i=1; i<=table.find('th').length; i++){
	    if(i==1 || i== 2){
	    console.log("i th: " + i);
	        var previous = null, cellToExtend = null, rowspan = 1;
	        table.find("td:nth-child(" + i + ")").each(function(index, e){
	            var jthis = $(this), content = jthis.text();
	            // check if current row "break" exist in the array. If not, then extend rowspan:
	            if (previous == content && content !== "" && $.inArray(index, firstColumnBrakes) === -1) {
	                // hide the row instead of remove(), so the DOM index won't "move" inside loop.
	                jthis.addClass('hidden');
	                cellToExtend.attr("rowspan", (rowspan = rowspan+1));
	            }else{
	                // store row breaks only for the first column:
	                if(i === 1) firstColumnBrakes.push(index);
	                rowspan = 1;
	                previous = content;
	                cellToExtend = jthis;
	            }
	        });
	        }
	    }
	    // now remove hidden td's (or leave them hidden if you wish):
	    $('td.hidden').remove();
	}
	


});



function showToastReportePlan(mensajeTipo, mensaje, mensajeError) {
	console.log("entrando al toast")
	
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, mensaje);
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

function filtrarReportePorArea(){
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'refrescarTablaFiltradaXArea',
			data: dataFiltrar(),
			success: function(result){
				$('#tablaBandeja').html(result);
				enableValidationRulesBandeja1();
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				
				$.unblockUI();
			}
		});
}

// metodo por defecto sera' 1
function dataSearch(codplan,codDesc,descPlan, area,periodo ){
//	buscarReportePlan(1,plan01, ' p011'  , ' 1 ')
	
	console.log("periodo: " + periodo);
	var data = {};
	data.idMes = "01";
	
	data.codplan = codplan;
	data.codDesc = codDesc;
	data.descPlan = descPlan;
	data.codare = area;
	data.periodo = periodo;
	
	return data;
	
}

function dataSearchXFecha(codplan){
	var data = {};
	var mes = $('#cboMeses').find(":selected").val();
	if(mes<10){
		mes = '0'+mes;
	}
	data.idMes = mes;
	data.codplan = $("#reporteData").data("codplan");
	data.codDesc = $("#codigoDesc").val();
	data.descPlan = $("#descPlan").val();
	data.codare = $("#reporteData").data("codare");
	data.periodo = $("#periodo").val();
	
	return data;
}

function dataModificar(){
	var data = {};
	var mes = $('#cboMeses').find(":selected").val();
	if(mes<10){
		mes = '0'+mes;
	}
	data.idMes = mes;
	return data;
}


function dataFiltrar(){
	var data = {};
	var area = $("#cboArea").val();
	
	
	data.codare = area;
	return data;
}

function dataRefrescarDatos(){
	var area = $("#reporteData").data("codare");
	if(area == 1){
		area = "BIOQUIMICA";
	}else{
		area = "FISICOQUIMICA";
	}
	
	var mes = Number($("#reporteData").data("mes"));
	console.log("mes: " + mes)
	setTimeout(function(){
	  $("#textArea").val(area);
	  $("#cboMeses").val(mes)
	},200)
}


function buscarReportePlanXFecha(){
	$.ajax({
		type: 'POST',
		url: 'buscarReportePlanSearch',
		data: dataSearchXFecha(),
		success: function(result){
			$('#id-modal-plan-bandeja').html(result);
			$('#modal-registra-parametroplan').modal('show');
			
				cargarCombo("listComboMeses","#cboMeses");
				calcularTotales();
				dataRefrescarDatos();
		},
		complete: function() {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
			enableValidationRulesBandeja1();
			
			
		}
	});
}

function buscarReportePlan(codplan,codDesc,descPlan, area , periodo){
	console.log("codplan: " + codplan);
	$.ajax({
		type: 'POST',
		url: 'buscarReportePlanSearch',
		data: dataSearch(codplan,codDesc,descPlan, area, periodo),
		success: function(result){
			$('#id-modal-plan-bandeja').html(result);
			
			var estadoplan = $(result).find("#reporteData").data("estadoplan");
			
			console.log("CODIGO: " + estadoplan);
			
			$('#modal-registra-parametroplan').modal('show');
			
			if(estadoplan!="OK"){
				$('#modal-registra-parametroplan').modal('hide');
				$("#reportesPlanOperativo").DataTable().ajax.reload();
			}else{
			
			setTimeout(function(){
				cargarCombo("listComboMeses","#cboMeses");
				calcularTotales();
				dataRefrescarDatos();
			},200)
			}
		},
		
		error:function(xhr){
		},
		
		complete: function(xhr,status) {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
			enableValidationRulesBandeja1();
		}
		 
	});
}

function modificarReportePlan(){
	var idMes = $('#cboMeses').find(":selected").val();
	if(idMes<10){
		idMes = '0'+idMes;
	}
	console.log("idMes: " + idMes);
	
	$.ajax({
		type: 'POST',
		url: 'modificarReportePlanSearch',
		data: dataModificar(),
		success: function(result){
			$('.container_save').html(result);
		},
		complete: function() {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
		}
	});
}

function calcularTotales(){
	$(".subactcalcular").each(function(index){
		
		var totalEjecutados = Number(0) ;
		$(this).find(".no-total").each(function(stat){
			
			var progr = Number($(this).parent().find(".programado").text());
			var ejecutado = Number($(this).children().prop("value"));
			var porcentaje = (ejecutado/progr)*100;
			
			totalEjecutados += ejecutado;
			
			console.log("["+ index + "] [ "+ stat + "] programado: " + progr + " ; ejecutado " + ejecutado + "; porcentaje: " + porcentaje)
			
			if(isNaN(porcentaje) || porcentaje == Number.POSITIVE_INFINITY){
				porcentaje = '-'
			} else {
				porcentaje = porcentaje.toFixed(2);
			}
			
			if(ejecutado==0){
				porcentaje = 0;
			}
			$(this).parent().find(".porcentaje").text(porcentaje);
		});
		console.log("---------------------------");
		totalEjecutados = isNaN(totalEjecutados)? 0 : totalEjecutados;
		
		$(this).find(".total").children().val(totalEjecutados.toFixed(2));
		
		var prograTotal = Number($(this).find(".programado-total").text());
		console.log("programador Total: " + prograTotal)
		
		var porTotal = (totalEjecutados/prograTotal)*100;
		
		if(isNaN(porTotal) || porTotal == Number.POSITIVE_INFINITY){
			porTotal = '-'
		} else {
			porTotal = porTotal.toFixed(2);
		}
		
		if(totalEjecutados==0){
				porTotal = 0;
		}
		console.log("programador Total: " + prograTotal +  ";totalEjecutados : " + totalEjecutados +"; porcentaje Total: " + porTotal)
		
		
		$(this).find(".porcentaje-total").text(porTotal);
	})
	
	$(".total > input").attr("disabled","disabled");
}


function getJsonToValidate(){
	var allFields = '';
	$(".actividad").each(function(index){
		console.log("index actividad: " +index )
		$(this).find(".subactividad").each(function(indexSubactividad){
			console.log("index subactividad: " + indexSubactividad)
			
			$(this).find(".parametroPlan").each(function(parametroPlan){
				console.log("index parametroPlan: " + parametroPlan)
				allFields += obtenerJsonField(index,indexSubactividad,parametroPlan);
			})
		});
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

function obtenerJsonField(act, subact, param){
	return '"actividades['+act+'].subactividades['+subact+'].parametros['+param+'].ejecutado": {'+
	'					"validators" : {'+
	'						"regexp": {'+
	'							 "regexp":"^\\\\d{1,7}(\\\\.\\\\d{1,2})?$",'+
	'							 "message": "Ingrese valor correcto"'+
	'						},'+
	'						"stringLength" : {'+
	'							"max" : 10,'+
	'							"message" : "S\u00f3lo permite hasta 7 enteros y 2 decimales"'+
	'						},'+
	'						"notEmpty": {'+
	'	                        "message": "Ingrese valor n\u00famerico"'+
	'	                    } '+
	'					}'+
	'	        },'
};

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION

function enableValidationRulesBandeja1() {

	var validator = $("#form-edit-reporte-plan").bootstrapValidator(getJsonToValidate())	
	.on('success.form.bv', function(e, data) {
		
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		
		var $nuevoTablaPoissonForm = $(e.target);
		console.log("guardando cambios para reporte");
		
		$.ajax({
			type: 'POST',
			url: 'reportePlanOperativoSave1',
			data: $('#form-edit-reporte-plan').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', true);
				$nuevoTablaPoissonForm[0].reset();
				
				$('.container_save_reporte').html(result);
			},
			complete: function() {
				$("#buttonCloseDetail").click();
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				$("#reportesPlanOperativo").DataTable().ajax.reload();
			}
		});
	});	
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

			    			   $('#reportesPlanOperativo').DataTable().ajax.reload();
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


function cargarReportePlanOperativo(url){
	var urldefault  = "reportePlanOperativo/pagination/";
	if(url == null || url == undefined){
		url = urldefault;
	}
	//var codare = $("#cboArea").data("codigoarea");
	var codare = $("#cboArea").val();
	
//	var codare = $("#cboArea").val();
	console.log("codareeeee: " + codare);
	
	if(codare==null || codare== undefined){
		codare = -1;
	}else if(codare.match("^B")){
			codare = 1;
 	}else if(codare.match("^F")){
			codare = 2;
 	}
	
	
	$('#reportesPlanOperativo').DataTable({
		"bProcessing": true,
		"serverSide": true,
		"searching": false,
		"lengthChange":   false,
		"ajax":{
			url : url + codare, // json datasource
            type: "post",  // type of method  ,GET/POST/DELETE
            error: function(){
              $("#myTableTablaPoisson_processing").css("display","none");
            },
            "data": function ( d ) {
            }
		},
		"order": [ 1, 'desc' ],
		"columnDefs": [ 
			{ targets: '_all', visible: true,  },
			{"width": "50px",
        		className: "dt-body-center",
        		sortable :true,
                "targets": 0                 
            },
            {"width": "40px",
        		className: "dt-body-center hidden",
                "targets": 1                
            },
            {"width": "40px",
    		  	className: "dt-body-center",
    		  	sortable :true,
              	"targets": 2
          	},
          	{"width": "40px",
        		className: "dt-body-center",
        		sortable :true,
                "targets": 3               
            },
            
            {"width": "40px",
        		className: "dt-body-center",
        		sortable :true,
                "targets": 4               
            },
          	{"width": "40px",
          		className: "dt-body-center",
          		sortable :true,
          		"orderable": false,
          		"targets": 0,
          		visible: true,
          		"data": 1,
          		"render": function ( data, type, row) {
          			 
          			 return '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="buscarReportePlan('+row[1]+ ', \''+ row[2] +'\' , \''+ row[3] + '\' , \'' + row[7] + '\' , \''+ row[6] +  '\')"><span class="glyphicon glyphicon-pencil"></span></button>';
          		}
          	}
		]
	});
}

			
