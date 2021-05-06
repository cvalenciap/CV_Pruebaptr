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
	
	
	loadDateBandeja('#idFechaMuestreoBandeja');
});


$("#modal-registra-parametroplan").on("keypress",'#observacion', function(event){
	
	let kCode = event.keyCode || event.which;
    if (kCode == 13) {
        //event.preventDefault();
        return false;   
    }
});

$("#modal-registra-parametroplan").on("drop",'#observacion', function(event){
	return false;
});

revalidateField = function($field){
	let $id = "#" + $field;
	$('#form-edit-ensayo').bootstrapValidator('revalidateField', $($id));
};

//$("#modal-registra-parametroplan").on("change",'select[name=punto1]', function(event){
$("#modal-registra-parametroplan").on("change",'#punto1', function(event){
	let $nameSelectComplemtnario = "select[name=punto2]";
	if($(this).val() == PUNTO_MUESTREO_BOCATOMA1){
		$($nameSelectComplemtnario).val(PUNTO_MUESTREO_BOCATOMA2);
	}
	else if($(this).val() == PUNTO_MUESTREO_FILTRO1){
		$($nameSelectComplemtnario).val(PUNTO_MUESTREO_FILTRO2);
	}
	asignarPuntoMuestreo('.tabla-datos-1');
	asignarPuntoMuestreo('.tabla-datos-2');
	revalidateField("punto2");
});

//$("#modal-registra-parametroplan").on("change",'select[name=punto2]', function(event){
$("#modal-registra-parametroplan").on("change",'#punto2', function(event){
	let $nameSelectComplemtnario = "select[name=punto1]";
	if($(this).val() == PUNTO_MUESTREO_BOCATOMA2){
		$($nameSelectComplemtnario).val(PUNTO_MUESTREO_BOCATOMA1);
	}
	else if($(this).val() == PUNTO_MUESTREO_FILTRO2){
		$($nameSelectComplemtnario).val(PUNTO_MUESTREO_FILTRO1);
	}
	asignarPuntoMuestreo('.tabla-datos-1');
	asignarPuntoMuestreo('.tabla-datos-2');
	revalidateField("punto1");
});

var LABEL_RESULTADO = "Demanda de Cloro (mg/L) en ";
var RGB_BACKGROUND = "rgb(238, 238, 238)";
var RGB_TEXT= "rgb(85, 85, 85)";

function actualizarCamposEstandadares(){
	actualizarEstandarSodio('.estandarSodio');
	setTimeout(function(){
		actualizarEstandarCloro('.estandarCloro');
	}, 100);
	
	setTimeout(function(){
		actualizarCampos();
	}, 400);
	
}

function actualizarCampos(){
	
	actualizarValores("#body-detalles");
	actualizarValores("#body-detalles2");
	
	//06-11-2017 actualizar los seleccionados
	$(".tabla-datos-1 > .seleccionado").each(function(index){
		console.log("la fila index " + index + " posee seleccionado: "+ $(this).attr("value"))
		if($(this).attr("value")==1){
			$("#resultadoTable1").text($("#demanda-table1-"+ index).val());
			$("#demanda-table1-"+ index).css("background-color","#5286b9")
			$("#demanda-table1-"+ index).css("color","white")
			
		}
	});
	
	$(".tabla-datos-2 > .seleccionado").each(function(index){
		
		console.log("la fila index " + index + " posee seleccionado: "+ $(this).attr("value"))
		if($(this).attr("value")==1){
			$("#resultadoTable2").text($("#demanda-table2-"+ index).val());
			$("#demanda-table2-"+ index).css("background-color","#5286b9")
			$("#demanda-table2-"+ index).css("color","white")
		}
	});
	

}

var VALOR_ESTANDAR_SODIO;
var VALOR_ESTANDAR_CLORO;
var FACTOR_SODIO_FORMULA= 0.1;
var FACTOR_CLORO_FORMULA= 35450;


function actualizarValores(tablebody){
//	"#body-detalles tr"
	var estandarCloro = VALOR_ESTANDAR_CLORO;
	var valorInputVolumen;
	var valorDosis
	var row;
	var valorInputCl2Libre;
	var valorInputCl2Total;
	var valorDemanda;
	
	var COLUMN_VOLUMEN = 2;
	var COLUMN_DOSIS = 3;
	var COLUMN_CL2LIBRE = 4;
	var COLUMN_CL2COMB = 5;
	var COLUMN_CL2TOTAL = 6;
	var COLUMN_TOTAL = 8;
	var COLUMN_DEMANDA = 9;
	
	
$(tablebody + " tr").each(function(index){
		console.log("row nro : " + index);
		if(index < 5){
		row = $(tablebody).find("tr").eq(index);
		valorInputVolumen = $(this).find("td").eq(COLUMN_VOLUMEN).find("input").val();
		valorInputCl2Libre = $(this).find("td").eq(COLUMN_CL2LIBRE).find("input").val();
		valorInputCl2Total = $(this).find("td").eq(COLUMN_CL2TOTAL).find("input").val();
		console.log("valorInputVolumen : " + valorInputVolumen);
		console.log("valorInputCl2Total : " + valorInputCl2Total);
		console.log("valorInputCl2Libre : " + valorInputCl2Libre);
		
		valorDosis = (valorInputVolumen*estandarCloro)/100;
		console.log("valorDosis : " + valorDosis);
//		valorDosis = valorDosis.toFixed(2);
		var valorCl2Comb = valorInputCl2Total - valorInputCl2Libre;
		var valorCl2CombToInput;
		
		valorDemanda = valorDosis - valorInputCl2Total;
		
		console.log("valorDemanda : " + valorDemanda);
		//actualizando valor Dosis
		if(isNaN(valorDosis) || valorDosis == Number.POSITIVE_INFINITY){
			//valorDosis = "-"
			valorDosis = "0.0";
		}else{
			valorDosis = valorDosis.toFixed(2);
		}
		
		$(row).find("td").eq(COLUMN_DOSIS).find("input").val(valorDosis);
		$(row).find("td").eq(COLUMN_DOSIS).find("input").text(valorDosis);
		$(row).find("td").eq(COLUMN_DOSIS).find("input").attr("value",valorDosis);
		$(row).find("td").eq(COLUMN_DOSIS).find("input").prop("value",valorDosis);
		
		//actualizando valor Cl2Comb
		console.log("valorCl2Comb: " + valorCl2Comb=="");
		if(isNaN(valorCl2Comb) || valorCl2Comb == undefined || valorCl2Comb == Number.POSITIVE_INFINITY){
			
			//valorCl2CombToInput = "-"
			valorCl2CombToInput = "0.0";
		}else if(valorCl2Comb==0){
			valorCl2CombToInput = "0.00";
		}else{
			valorCl2CombToInput = valorCl2Comb.toFixed(2);
		}
		
		
		
		$(row).find("td").eq(COLUMN_CL2COMB).find("input").val(valorCl2CombToInput);
		$(row).find("td").eq(COLUMN_CL2COMB).find("input").attr("value",valorCl2CombToInput);
		
		//actualizando valor cl2Total
		if(isNaN(valorInputCl2Total)|| valorInputCl2Total == undefined || valorInputCl2Total == "" || valorInputCl2Total == Number.POSITIVE_INFINITY){
			//valorInputCl2Total = "-"
			valorInputCl2Total = "0.0"
		}
		$(row).find("td").eq(COLUMN_TOTAL).find("input").val(valorInputCl2Total);
		$(row).find("td").eq(COLUMN_TOTAL).find("input").attr("value",valorInputCl2Total);
		
		//actualizando valor cl2Total
		if(isNaN(valorDemanda) || valorDemanda == undefined || valorDemanda == "" || valorDemanda == Number.POSITIVE_INFINITY){
			//valorDemanda = "-"
			valorDemanda = "0.0"
		}else{
			valorDemanda = valorDemanda.toFixed(2);
		}
		$(row).find("td").eq(COLUMN_DEMANDA).find("input").val(valorDemanda);
		$(row).find("td").eq(COLUMN_DEMANDA).find("input").attr("value",valorDemanda);
		
		COLUMN_VOLUMEN = 1;
		COLUMN_DOSIS = 2;
		COLUMN_CL2LIBRE = 3;
		COLUMN_CL2COMB = 4;
		COLUMN_CL2TOTAL = 5;
		COLUMN_TOTAL = 7;
		COLUMN_DEMANDA = 8;
		}
		
		
	})
}

//$('#modal-registra-parametroplan').on("click", '#tablaReporteHidroBiologicoDetalle tr', function () {
////	   var row = $(this).find("#body-detalles").html();
//	   var row = $(this).html();
//	   alert('You row ' + row);
//	   var td = $(row).find("#detalles0.volumen").text();
//	   alert('You td ' + td);   
//});

//$('#modal-registra-parametroplan').find("#tablaReporteHidroBiologicoDetalle").click( function(){
////	  var row = $(this).find('td:first').text();
//	  alert('You clicked ' + row);
//});

//$('#body-detalles').find('tr').click( function(){
//	  var row = $(this).find('td:first').text();
//	  alert('You clicked ' + row);
//});

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

function refrescarEnsayosCloroXFecha(){
	
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'refrescarEnsayosCloroXFecha',
			data: dataFiltrar(),
			success: function(result){
				$('#tablaBandeja').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				enableValidationRulesBandeja1();
				$.unblockUI();
			}
		});
}

// metodo por defecto sera' 1
function dataSearch(codplan,codDesc,descPlan, area ){
//	buscarReportePlan(1,plan01, ' p011'  , ' 1 ')
	
	var data = {};
	data.idMes = "01";
	
	data.codplan = codplan;
	data.codDesc = codDesc;
	data.descPlan = descPlan;
	data.codare = area;
	
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
			    			   
			    			   $('#id-modal-plan-bandeja-inactivar').html(result);
			    			   $('#ensayoCloroBandeja').DataTable().ajax.reload();
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

function agregarEnsayoCloro(idEnsayo){
	buscarEnsayoCloro(idEnsayo);
}

function buscarEnsayoCloro(idEnsayo){
	
	console.log("idEnsayo: " + idEnsayo);
	$.ajax({
		type: 'POST',
		url: 'buscarEnsayoCloro/' + idEnsayo,
		before: function(result){
			
		},
		success: function(result){
			$('#id-modal-plan-bandeja').html(result);
			$('#modal-registra-parametroplan').modal('show');
			
		},
		complete: function() {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
			
			setTimeout(function(){
				loadDateOnInput('#idFechaInicioEnsayo');
				loadDateOnInput('#fechaMuestreo');
				loadDateOnInput('#idFechaRecepcionEnsayo');
				loadDateOnInput('#idFechaReporteEnsayo');
				
				loadHourOnInput("#horaMuestreo");
				loadHourOnInput("#horaRecepcion");
				loadHourOnInput("#horaInicioEnsayo");
				
				cargarSelect("listTipoEnsayo2","#metodoMuestreo","#metodoInput");
				cargarSelect("listTurnos","#cboTurnos","#turnoInput");
				cargarSelect("listMuestreadores","#cboMuestreador","#muestreadorInput");
				cargarSelect("listAnalistas","#cboAnalista","#analistaInput");
				
				actualizarEstandarSodio(".estandarSodio");
				actualizarEstandarCloro(".estandarCloro");
				
				//juntamos los td de las primeras columnas
				joinColumnaMuestreo();
				
			},300);
			
			
		}
	});
	
}

function reactDOM(){
	setTimeout(function(){
		
	},400);
}
//cargarComboForFieldAndPutValue("listPuntosEnsayo",".tabla-datos-1 .labelPunto",puntoInput, "1");
function cargarComboForFieldAndPutValue(urlJson,idSelect,value,endWith){
	
//	$.getJSON(urlJson, {
//		ajax : 'true'
//	}, function(data) {
//		var html = '';
//		var len = data.length;
//		for ( var i = 0; i < len; i++) {
//			html += '<option data-id="'+ data[i].id+'"  value="' + data[i].id + '">'
//					+ data[i].label + '</option>';
//		}
//		html += '</option>';
//                    //now that we have our options, give them to our select
//		$(idSelect).html(html);
//		console.log("cargando combo value: "+ value);
//		$(idSelect).val(value)
//	});
	
	
	MYAPPL.blockPageLoad();
//	$(".blockOverlay").css("z-index","100000");
	$.ajax({
		url : urlJson,
		type : 'GET',
		dataType : "json",
		error : function(xhr, status) {
			
		},
		complete : function(xhr, status) {
			if(status == 'success' || status=='notmodified')
			{
				var data =  $.parseJSON(xhr.responseText);
				var html = '';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					if(data[i].label.endsWith(endWith)){
						html += '<option data-id="'+ data[i].id+'"  value="' + data[i].id + '">'
						+ data[i].label + '</option>';
					}
				}
				html += '</option>';
		                    //now that we have our options, give them to our select
				$(idSelect).html(html);
				console.log("cargando combo value: "+ value);
				$(idSelect).val(value)
			}
			
			asignarPuntoMuestreo(".tabla-datos-1");
			asignarPuntoMuestreo(".tabla-datos-2");
			
			actualizarCampos();
			$.unblockUI();
		}
	});
	
}

if (!String.prototype.endsWith) {
	  String.prototype.endsWith = function(searchString, position) {
	      var subjectString = this.toString();
	      if (typeof position !== 'number' || !isFinite(position) 
	          || Math.floor(position) !== position || position > subjectString.length) {
	        position = subjectString.length;
	      }
	      position -= searchString.length;
	      var lastIndex = subjectString.indexOf(searchString, position);
	      return lastIndex !== -1 && lastIndex === position;
	  };
}

function cargarSelect(urlCombo,idSelect,idInput){
	var value = $(idInput).text();
	cargarComboForFieldSinLoader(urlCombo,idSelect);
	console.log("asignando valor al combo " + value );
	setTimeout(function(){
		$(idSelect).val(value);
	},500);
	
}

function mergeCommonRows(table) {
    var firstColumnBrakes = [];
    // iterate through the columns instead of passing each column as function parameter:
    for(var i=1; i<=table.find('th').length; i++){
    if(i==1){
    console.log("i th: " + i);
        var previous = null, cellToExtend = null, rowspan = 1;
        table.find("td:nth-child(" + i + ")").each(function(index, e){
            var jthis = $(this), content = jthis.find("input").prop("class");
            console.log("content: " + content)
            // check if current row "break" exist in the array. If not, then extend rowspan:
            if ((content == 'frasco')||(previous == content && content !== "" && $.inArray(index, firstColumnBrakes) === -1)) {
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


function dataEnsayoCloro(){
	var data = {}
	data.fechaMuestreo = $("#idFechaMuestreoBandeja").val();
	return data;
}



function cargarEnsayoCloroXfecha(){
	MYAPPL.blockPageLoad();
	$("#idBtnBuscarEnsayoCloro").attr("disabled","disabled");
	 $.ajax({
  	   type: 'POST',
  	   url: 'cargarEnsayoXfecha',
  	   success: function(result){		   
  		   setTimeout(function(){
  			   console.log("cargando resultado por fecha " )
  			   $('#tablaBandeja').html(result);  
  		   },200);
	  },complete:function(){
       	$.unblockUI();
	    }
	  });
}

function cargarEnsayoCloro(){
	
	MYAPPL.blockPageLoad();
	
	var url = "ensayoCloro/pagination";
	
	$("#idBtnBuscarEnsayoCloro").attr("disabled","disabled");
	
	$('#ensayoCloroBandeja').DataTable({
		"bProcessing": true,
		"serverSide": true,
		"searching": false,
		"lengthChange":   false,
		"ajax":{
			url : url, // json datasource
            type: "post",
            data: dataEnsayoCloro(),
            // type of method  ,GET/POST/DELETE
            error: function(){
              $("#myTableTablaPoisson_processing").css("display","none");
            },complete:function(){
            	$.unblockUI();
            	$("#idBtnBuscarEnsayoCloro").removeAttr("disabled");
            }
		},
		"order": [ 1, 'desc' ],
		"columnDefs": [ 
			
			{"width": "50px",
        		className: "dt-body-center",
        		sortable :true,
                "targets": 0    ,
                visible:true,
            },
            {"width": "40px",
        		className: "dt-body-center",
                "targets": 1  ,
                visible:true,
            },
            {"width": "40px",
    		  	className: "dt-body-center",
    		  	sortable :true,
    		  	visible:true,
              	"targets": 2
          	},
          	{"width": "40px",
        		className: "dt-body-center",
        		sortable :true,
        		visible:false,
                "targets": 3               
            },
            
            {"width": "40px",
        		className: "dt-body-center",
        		sortable :true,
        		visible:true,
                "targets": 4               
            },
            
            {"width": "40px",
        		className: "dt-body-center",
        		sortable :true,
        		visible:true,
                "targets": 5               
            },

            
          	{"width": "40px",
          		className: "dt-body-center",
          		sortable :true,
          		"orderable": false,
          		"targets": 0,
          		visible: false,
          		"data": 1,
          		"render": function ( data, type, row) {
          			 
          			 return '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="buscarEnsayoCloro('+row[6]+')"><span class="glyphicon glyphicon-pencil"></span></button>'+
          			'<button class="btn btn-danger btn-delete-punto" title="Eliminar" onclick="inactivarEnsayoCloro('+row[6]+')"><span class="glyphicon glyphicon-erase"></span></button>';
          		}
          	}
		]
	});
}

function inactivarEnsayoCloro(id){
console.log("inactivarPlanOperativo ....")
$.confirm({
    title: 'Confirmaci\u00f3n',
    content: '\u00BFConfirma eliminar el registro\u003F',
    buttons: {
    	Aceptar: { 
        	btnClass: 'btn-blue',
        	action: function () {
			    $.ajax({
		    	   type: 'POST',
		    	   url: 'inactivarEnsayoCloro/'+id,
		    	   success: function(result){		   
		    		   setTimeout(function(){
		    			   console.log("activando modal")

		    			   
//		    			   $('#id-modal-plan-bandeja-inactivar').html(result);
		    			   $('.container_save_reporte').html(result);
		    			   
		    			   $('#ensayoCloroBandeja').DataTable().ajax.reload();
		    		   },500);
			    	  },
			    	}); 
	        	}
	        },
	        Cancelar: {
	        	
	        }
	    }
	});
}

function loadDateBandeja(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate: new Date()
	}) .on('changeDate', function(e) {
	});
	
	if(valInput == null || valInput == undefined){
		valInput = "";
	}
	
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
}

function loadDateOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate: new Date()
	}) .on('changeDate', function(e) {
	});
	
	if(valInput == null || valInput == undefined || valInput == ""){
		valInput = getDDMMYYYY();
	}
	
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
}


function loadHourOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'HH:mm:ss',
	    ignoreReadonly: false,
	    date: null,
	    locale: "es"
	}) .on('changeDate', function(e) {
	});
	
	if(valInput == null || valInput == undefined || valInput == ""){
		valInput = getHhmmss();
	}
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
}

function validationNumericWithDecimals(evt){
	  if(evt.which == 0 || evt.which ==8){
			return true;
		}
		var theEvent = evt || window.event;
	    var key = theEvent.keyCode || theEvent.which;

	    key = String.fromCharCode(key);
	    console.log("key: " + key)
	    
	    if (key.length == 0) return;
	
  var regex = /^\d{1,9}(\.\d{1,2})?$/;
  var isValid = regex.test(key);
  
  console.log("isValid: " + isValid)
  if (!isValid) {
      if (theEvent.preventDefault) theEvent.preventDefault();
      return false;
  }
  return true;
}

function actualizarEstandarSodio(clase){
	var promedio = obtenerPromedioEstandar(clase);
	console.log("promedio " + promedio);
	VALOR_ESTANDAR_SODIO = FACTOR_SODIO_FORMULA/promedio;
	var valorEstandar = VALOR_ESTANDAR_SODIO.toFixed(3);
	console.log("valorEstandar Sodio: " + valorEstandar);
	if(isNaN(valorEstandar) || valorEstandar == Number.POSITIVE_INFINITY){
		$("#estandarSodio").val(0);
	}else{
		$("#estandarSodio").val(valorEstandar);
	}
	console.log("valorEstandar : " + valorEstandar);
}

function actualizarEstandarCloro(clase){
	
	var valorEstandarSodio = VALOR_ESTANDAR_SODIO;
	var tioSulfato = FACTOR_CLORO_FORMULA;
	var promedio = obtenerPromedioEstandar(clase);
	console.log("promedio " + promedio);
	VALOR_ESTANDAR_CLORO = promedio*valorEstandarSodio*tioSulfato
	var valorEstandar = VALOR_ESTANDAR_CLORO.toFixed(3);
	console.log("valorEstandar cloro : " + valorEstandar);
	if(isNaN(valorEstandar) || valorEstandar == Number.POSITIVE_INFINITY){
		$("#estandarCloro").val(0);
	}else{
		$("#estandarCloro").val(valorEstandar);
	}
}

function obtenerPromedioEstandar(clase){
	var total = Number(0);
	var c = 0;
	$(clase).each(function(){
		total += Number($(this).val());
		c++ ;
	});
	
	var promedio = total>0? (total/c) : 0;
	return promedio;
}

function joinColumnaMuestreo(){
	
	
	$("#body-detalles tr > td:first-child").remove();// removemos todas los primeros td
	$("#body-detalles2 tr > td:first-child").remove();// removemos todas los primeros td 

//	<select class="form-control col-md-2 col-sm-2 col-xs-2"  th:field="*{puntoMuestreo}" id="cboPuntoMuestreo" onchange="setValueToInputMuestreo()"></select>
	var divInput = '<label class="col-md-3 col-sm-3 col-xs-3 " style="text-weight: bold"><span style="font-weight: bold">Punto :</span></label>'
					//'<input class="col-md-6 col-sm-6 col-xs-6 labelPunto"></input>'
					+ '<div class="col-md-9 col-sm-9 col-xs-9 form-group "><select class="form-control  labelPunto " name="punto1" id="punto1"></select></div>'
					+'<label class="col-md-3 col-sm-3 col-xs-3" style="font-weight: bold"><span style="font-weight: bold">PH :</span></label>'+'<div class="col-md-9 col-sm-9 col-xs-9 form-group has-feedback"><input maxlength="5" name="ph1" id="ph1" data-bv-field="ph1" class="form-control labelPh " onkeyup="asignarPh(\'.tabla-datos-1\');"></input></div>'
					+'<label class="col-md-3 col-sm-3 col-xs-3 " style="font-weight: bold"><span style="font-weight: bold">Temp. :</span></label>'+'<div class="col-md-9 col-sm-9 col-xs-9 form-group has-feedback"><input name="temp1"  id="temp1" data-bv-field="temp1" class="form-control labelTemp" onkeyup="asignarTemp(\'.tabla-datos-1\');" onkeypress="return MYAPPL.soloNumerosReales(event)"></input></div>'

	var divInput2 = '<label class="col-md-3 col-sm-3 col-xs-3 " style="text-weight: bold"><span style="font-weight: bold">Punto :</span></label>'
						//'<input class="col-md-6 col-sm-6 col-xs-6 labelPunto"></input>'
						+ '<div class="col-md-9 col-sm-9 col-xs-9 form-group"><select class="form-control  labelPunto" name="punto2" id="punto2"></select></div>'
						+'<label class="col-md-3 col-sm-3 col-xs-3" style="font-weight: bold"><span style="font-weight: bold">PH :</span></label>'+'<div class="col-md-9 col-sm-9 col-xs-9 form-group has-feedback"><input maxlength="5" name="ph2" id="ph2" data-bv-field="ph2" class="form-control labelPh" onkeyup="asignarPh(\'.tabla-datos-2\');"></input></div>'
						+'<label class="col-md-3 col-sm-3 col-xs-3 " style="font-weight: bold"><span style="font-weight: bold">Temp. :</span></label>'+'<div class="col-md-9 col-sm-9 col-xs-9 form-group has-feedback"><input name="temp2" id="temp2" data-bv-field="temp2" class="form-control labelTemp" onkeyup="asignarTemp(\'.tabla-datos-2\');"onkeypress="return MYAPPL.soloNumerosReales(event)" ></input></div>'

//	var tdInput = '<td rowspan="5" class="container-first-column" ><div type="text" id="inputMuestreo"   style="height:30px"/>'+divInput+'</div></td>'
	var tdInput = '<td rowspan="5" class="container-first-column">'+divInput+'</td>'
	var tdInput2 = '<td rowspan="5" class="container-first-column" ><div type="text" id="inputMuestreo2"   style="height:30px"/>'+divInput2+'</div></td>'
	
	$(tdInput).insertBefore($("#body-detalles tr:first-child td:first-child"));//ingresamos un td con rowspan del tamaio 5
	$(tdInput2).insertBefore($("#body-detalles2 tr:first-child td:first-child"));//ingresamos un td con rowspan del tamaio 5
	
	var puntoInput = $(".tabla-datos-1 > .puntoMuestreo:first").val();
	var phInput = $(".tabla-datos-1 > .phInput:first").val();
	var temperaturaInput = $(".tabla-datos-1 > .temperaturaInput:first").val();
	
	var puntoInput2 = $(".tabla-datos-2 > .puntoMuestreo:first").val();
	var phInput2 = $(".tabla-datos-2 > .phInput:first").val();
	var temperaturaInput2 = $(".tabla-datos-2 > .temperaturaInput:first").val();
	
	
	
	$( ".tabla-datos-1 .labelPunto").val(puntoInput)
	$( ".tabla-datos-1 .labelPh").val(phInput)
	$( ".tabla-datos-1 .labelTemp").val(temperaturaInput)
	
	$( ".tabla-datos-2 .labelPunto").val(puntoInput2)
	$( ".tabla-datos-2 .labelPh").val(phInput2)
	$( ".tabla-datos-2 .labelTemp").val(temperaturaInput2)
	
	cargarComboForFieldAndPutValue("listPuntosEnsayo",".tabla-datos-1 .labelPunto",puntoInput, "1");
	cargarComboForFieldAndPutValue("listPuntosEnsayo",".tabla-datos-2 .labelPunto",puntoInput2, "2");
	
	enableValidationRulesBandeja1();
	
}


function asignarPuntoMuestreo(classTable){
	
	var valuePuntoMuestra = $(classTable + " .labelPunto option:selected" ).attr("data-id");
	
	$(classTable + ' > .puntoMuestreo').each(function(index){
		console.log("asignar puntos muestreo tabla nro "+ classTable +" :" + valuePuntoMuestra);
		$(this).val(valuePuntoMuestra);
		$(this).prop("value",valuePuntoMuestra);
		$(this).attr("value",valuePuntoMuestra);
	})
	
	$("#resultadoLabel1").text(LABEL_RESULTADO + $(".tabla-datos-1 .labelPunto option:selected" ).text() + ":" );
	$("#resultadoLabel2").text(LABEL_RESULTADO + $(".tabla-datos-2 .labelPunto option:selected" ).text() + ":" );

}

function asignarPh(classTable){
	var valuePuntoMuestra = $( classTable + " .labelPh" ).val();
	
	$(classTable + ' > .phInput').each(function(index){
		console.log("asignar asignarPh tabla nro "+ classTable +" :" + valuePuntoMuestra);
		$(this).val(valuePuntoMuestra);
		$(this).prop("value",valuePuntoMuestra);
		$(this).attr("value",valuePuntoMuestra);
	})
}

function asignarTemp(classTable){
	
	var valuePuntoMuestra = $(classTable+ " .labelTemp" ).val();
	$(classTable + ' > .temperaturaInput').each(function(index){
		console.log("asignar Temp tabla nro " + classTable +":"+ valuePuntoMuestra);
		$(this).val(valuePuntoMuestra);
		$(this).prop("value",valuePuntoMuestra);
		$(this).attr("value",valuePuntoMuestra);
	})
}



function asignarSeleccionado(e){
	var valSelect = $(e).val();
	var bck = RGB_BACKGROUND
	var color = RGB_TEXT
	console.log("bck: " + bck + "; color " + color);
	

	
	var selectedIndex = $(e).closest('tr').index();
	console.log("valSelect: " + valSelect);
	var divTableClass = $(e).parent().parent().parent().parent().parent().parent().attr("class");
	
	
	
	$("."+divTableClass).find(".demandaClass").each(function(index){
		$(this).css("background-color",bck)
		$(this).css("color",color)
	})
	
	$(e).css("background-color","#5286b9")
	$(e).css("color","white")
	
	$("."+divTableClass+ " > .seleccionado").each(function(index){
		$(this).val(0);
		$(this).attr("value",0);
		$(this).prop("value",0);
		
		
		
		console.log("selectedIndex: " + selectedIndex+ "; index: " + index);
		if(selectedIndex == index){
			
			
			$(this).val(1);
			$(this).attr("value",1);
			$(this).prop("value",1);
		}
	});
	
	var nroResult = divTableClass[divTableClass.length -1];
	$("#resultadoTable"+nroResult).text(valSelect);
	
}

function setValueToInputMuestreo(){
	var puntoValue = $("#cboPuntoMuestreo").val();
	var phValue = $("#ph").val();
	var temperatura = $("#inputTemperatura").val();

	console.log("puntoValue: " + puntoValue);
	console.log("phValue: " + phValue);
	console.log("temperatura: " + temperatura);
						
	
	$(".labelPunto").text(puntoValue!=undefined && puntoValue!="" ?puntoValue: "-" );
	$(".labelPh").text(phValue!=undefined && phValue!="" ?phValue: "-" );
	$(".labelTemp").text(temperatura!=undefined && temperatura!="" ?temperatura: "-" );
}
