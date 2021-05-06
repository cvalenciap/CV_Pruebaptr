
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTablaPoisson').click(function () {		 
		 $('#cboLambdasHidden').val($('#cboLambdas').val());
		 $('#myTableTablaPoisson').DataTable().ajax.reload();
	});
	
	//CARGA EL COMBO DE LAMBDAS
	cargarCombo("listAreas","#cboAreas");
	cargarComboTodos("listAreas","#cboAreasPlan");
	
	$.getJSON("listActividades", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		
		
		for ( var i = 0; i < len; i++) {
			if(data[i].custom==''){
				ACTIVIDADES_CARGADAS[data[i].id] = data[i].label;
			}else{
				ACTIVIDADES_CARGADAS[data[i].id] = data[i].custom;
			}
			
		}
		
		console.log("myArray: " + ACTIVIDADES_CARGADAS);
		console.log("myArray 38: " + ACTIVIDADES_CARGADAS[38]);
	});


	$.getJSON("listSubactividades", {
	ajax : 'true'
	}, function(data) {
	var len = data.length;
	
	for ( var i = 0; i < len; i++) {
		SUBACTIVIDADES_CARGADAS[data[i].id] = data[i].label;
	}
	});

	$.getJSON("listUnidadMedida", {
	ajax : 'true'
	}, function(data) {
	var len = data.length;
	for ( var i = 0; i < len; i++) {
		UNIDAD_MEDIDAS_CARGADAS[data[i].value] = data[i].label;
	}
	});
	
	
});

function modificarPlanOperativo(id) {
	/*if(id==0){
		$("#modal-registra-planoperativo-bandeja").css("position","relative");
	}else{
		$("#modal-registra-planoperativo-bandeja").css("position","");
	}*/
	
	$.ajax({
		type: 'GET',
		data: dataModificarPlan(id),
		url: 'planOperativoBandejaGet', 
		success: function(result) {
			console.log("id " + id)
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
//			if(id == 0){
//				$('#modal-registra-planoperativo-agregar').modal('show');
//			}else{
//				$('#modal-registra-planoperativo-bandeja').modal('show');
//			}
			$('#modal-registra-planoperativo-bandeja').modal('show');
			
//			$('#modal-registra-planoperativo-bandeja').modal('show');
			cargarCombo('listAreas',"#cboAreas");
			
			setTimeout(function(){
				var codare = $("#cboAreas").attr("codare");
				console.log("codare: " + codare);
				
				if(codare!=null && codare != undefined){
					$("#cboAreas").val(codare);
				}
				
//				var idArea = $('#cboAreas').find(":selected").data("id");
//				alert("id::"+id)
				if(id != 0){
					cambioArea(codare);	
				}else{
					
					var codigoarea = $("#inputArea").data("codigoarea");
					console.log("codigoarea: " + codigoarea)
					$("#cboAreas").val(codigoarea);
					
					//$("#cboAreas").attr("disabled","disabled");
				}
//				$("#cboAreas").val(idArea)
//				loadYearOnInput("#periodoPlan");
				
				$("#periodoPlan").datetimepicker({
					format: 'YYYY',
			        ignoreReadonly: true,
			        date: new Date(),
			        locale: "es",
			        minDate: new Date(1900, 0, 1),
			        maxDate: new Date(2200, 0, 1)
				}) .on('changeDate', function(e) {
				});
				/*if(id==0){
					$("#modal-registra-planoperativo-bandeja").css("overflow-y","hidden");
				}
				else{
					$("#modal-registra-planoperativo-bandeja").css("overflow-y","auto");
				}*/
				
				
				
				
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
function enableValidationRulesBandeja() {

	var validator = $("#form-edit-plan-operativo")
		.on('init.field.bv', function(e, data) {
            // data.bv      --> The BootstrapValidator instance
            // data.field   --> The field name
            // data.element --> The field element

            var $parent    = data.element.parents('.form-group'),
                $icon      = $parent.find('.form-control-feedback[data-bv-icon-for="' + data.field + '"]'),
                options    = data.bv.getOptions(),                      // Entire options
                validators = data.bv.getOptions(data.field).validators; // The field validators

            if (validators.notEmpty && options.feedbackIcons && options.feedbackIcons.required) {
                // The field uses notEmpty validator
                // Add required icon
                $icon.addClass(options.feedbackIcons.required).show();
            }
        })
	
		.bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			codigoDescripcionPlan: {
					validators : {
						regexp: {
							 regexp:/^[a-z0-9\u00E1\u00E9\u00ED\u00F3\u00FA\u00FC\u00F1\s\.-]+$/i,
							 message: 'Ingrese valor alfanumérico'
						},
						stringLength : {
							max : 20,
							message : 'Campo incorrecto'
						},
						notEmpty: {
	                        message: 'Ingrese valor alfanumérico'
	                    } 
					}
	        },
	        
	        periodo: {
				validators : {
					
					stringLength : {
						max : 4,
						message : 'Ingrese valor periodo'
					},
					notEmpty: {
                        message: 'Ingrese valor periodo'
                    }
				}
          },
	        
	        descripcion: {
				validators : {
					regexp: {
						 regexp:/^[a-z0-9\u00E1\u00E9\u00ED\u00F3\u00FA\u00FC\u00F1\s\.-]+$/i,
						 message: 'Ingrese valor alfanumérico'
					},
					stringLength : {
						max : 5,
						message : 'Ingrese valor alfanumérico'
					},
					notEmpty: {
                        message: 'Ingrese valor alfanumérico'
                    }
				}
          },
          codare: {
        	  validators: {
                  callback: {
                      message: 'Elegir valor correcto',
                      callback: function(value, validator, $field) {
                          /* Get the selected options */
                          var options = validator.getFieldElements('codare').val();
                          console.log("valor codare: " + options)
                          return (options != null && options != undefined);
//                          return false;
                      }
                  }
              }
				
        },
          
		}
	})
	.on('status.field.bv', function(e, data) {
            // Remove the required icon when the field updates its status
            var $parent    = data.element.parents('.form-group'),
                $icon      = $parent.find('.form-control-feedback[data-bv-icon-for="' + data.field + '"]'),
                options    = data.bv.getOptions(),                      // Entire options
                validators = data.bv.getOptions(data.field).validators; // The field validators

            if (validators.notEmpty && options.feedbackIcons && options.feedbackIcons.required) {
                $icon.removeClass(options.feedbackIcons.required).addClass('fa');
            }
     })
        
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTablaPoissonForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: 'planOperativoSave',
			data: $('#form-edit-plan-operativo').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', false);
//				$nuevoTablaPoissonForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});	
}

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarPlanOperativo(id) {
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
	    content: '\u00BFConfirma aprobar Plan Operativo\u003F',
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
	 
	 if(mensajeTipo==='informacionNOOk'){
		 toastr["error"](mensajeError, mensaje);
	 }
	 
}

function refrescarPlanXArea(){
	MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'refrescarPlanXArea',
			data: dataFiltrar(),
			success: function(result){
				console.log("result: " + result);
				$('#tablaBandeja').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
//				enableValidationRulesBandeja1();
				$.unblockUI();
			}
		});
}


function dataFiltrar(){
	var data = {};
	var area = $("#cboArea").val();
	data.codare = area;
	return data;
}


function dataFiltrar(){
	var data = {};
	var area = $("#cboArea").val();
	
	
	data.codare = area;
	return data;
}

function soloNumeros(e) {
	   var key = window.Event ? e.which : e.keyCode;
	   return ((key >= 48 && key <= 57) ||(key==8))
	 }
	 
	 function pierdeFoco(e){
	    var valor = e.value.replace(/^0*/, '');
	    e.value = valor;
	 }

	 function cargarTablaPlanBandeja(){

	 	var urldefault  = "planOperativo/pagination/";


	 //var codare = $("#cboAreasPlan").val();
	 var codare = $("#cboAreasPlan").val();
	 console.log("codare: " + codare);
	 	if(codare==null || codare== undefined){
	 			codare = -1;
	 	}else if(codare.match("^B")){
 			codare = 1;
	 	}else if(codare.match("^F")){
 			codare = 2;
	 	}
	 	
	 	$( document ).ready(function() {
	 		$('#idTablaPlanOperativoBandeja').DataTable({
	 			"bProcessing": true,
	 			"serverSide": true,
	 			"searching": false,
	 			"lengthChange":   false,
	 			"scrollX": true,
	   			"bJQueryUI": true,
	 			"ajax":{
	 				url : urldefault + codare, // json datasource
	 	            type: "post",  // type of method  ,GET/POST/DELETE
	 	            error: function(){
	 	              $("#myTableTablaPoisson_processing").css("display","none");
	 	            },
	 	            "data": function ( d ) {
	 	            }
	 			},
	 			"order": [ 0, 'desc' ],
	 			"columnDefs": [ 
	 				{ targets: '_all', visible: true,  },
	 	            {"width": "40px",
	 	        		className: "dt-body-center hidden",
	 	                "targets": 1                
	 	            },
	 	            {"width": "150px",
	         		  	className: "dt-body-center",
	         		  	sortable :true,
	                   	"targets": 2
	               	},
	 	            {"width": "200px",
	         		  	className: "dt-body-center",
	         		  	sortable :true,
	                   	"targets": 3
	               	},
	               	{"width": "80px",
	 	        		className: "dt-body-center",
	 	        		sortable :true,
	 	                "targets": 4               
	 	            },
	 	            
	 	            {"width": "100px",
	 	        		className: "dt-body-center",
	 	        		sortable :true,
	 	                "targets": 5               
	 	            },
	 	            
	 	            {"width": "50px",
	 	        		className: "dt-body-center",
	 	        		sortable :true,
	 	                "targets":  6             
	 	            },
	 	            
	               	{"width": "90px",
	               		className: "dt-body-center",
	               		sortable :true,
	               		"orderable": false,
	               		"targets": 0,
	               		visible: true,
	               		"data": 1,
	               		"render": function ( data, type, row) {
	               			
	               			var retorno;
	               			if(row[5]=="Aprobado"){
	               				retorno = '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="modificarPlanOperativo(\''+row[1]+'#'+row[2]+ '#'+ row[7] +'#'+row[3]+'\')"><span class="glyphicon glyphicon-pencil"></span></button>'+
	               						  '<button class="btn btn-danger btn-delete-punto" disabled="disabled" title="Eliminar" onclick="inactivarPlanOperativo('+row[1]+')"><span class="glyphicon glyphicon-erase"></span></button>';
	               			}else{
	               				retorno = '<button class="btn btn-info btn-modif-punto" title="Editar" onclick="modificarPlanOperativo(\''+row[1]+'#'+row[2]+ '#'+ row[7] +'#'+row[3]+'\')"><span class="glyphicon glyphicon-pencil"></span></button>'+
	               						  '<button class="btn btn-danger btn-delete-punto" title="Eliminar" onclick="inactivarPlanOperativo('+row[1]+')"><span class="glyphicon glyphicon-erase"></span></button>';
	               			}              			
	               					
	               			return retorno;
	               		}
	               	}
	 			]
	 		});
	 	}); 
	 }

