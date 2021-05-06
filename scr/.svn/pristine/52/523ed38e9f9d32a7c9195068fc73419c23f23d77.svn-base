/**
 * Hoja de scripts manejados en el mantenimiento de clima
 */
$(document).ready(function() {

	// MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTablaPoisson').click(function() {
		$('#cboLambdasHidden').val($('#cboLambdas').val());
		$('#myPlanOperativoDetalle').DataTable().ajax.reload();
	});

	// CARGA EL COMBO DE LAMBDAS
	cargarCombo("listLambdas", "#cboLambdas");

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

	// cargando combos de Areas
	cargarCombo('listAreas', "#cboAreas");

});

var PLAN_APROBADO = 0;
//var AREAS_CARGADAS = {};
//var ACTIVIDADES_CARGADAS = {};
//var SUBACTIVIDADES_CARGADAS = {};
//var UNIDAD_MEDIDAS_CARGADAS = {};


/*METODO PARA INSERTAR */
function insertarActividad(insertarActividad) {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'POST',
		url: 'insertarParametroXSubactividad',
		data : dataInsertar(insertarActividad),
		success: function(result) {
			$('#id-modal-parametroplan').html(result);
		},complete: function() {
			$.unblockUI();
		}
	});
}

function removerActividad(indices) {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'POST',
		url: 'modificarParametroXSubactividad',
		data : dataEliminar(indices),
		success: function(result) {
			$('#id-modal-parametroplan').html(result);
		},complete: function() {
			$.unblockUI();
		}
	});
}


function dataInsertar(indices){
	var data = {};
	data.descripcionAsignada = "";
	data.idMaster = $('#cboSubActividades').find(":selected").data("idmaster");
	data.idDetalle = $('#cboActPosibles').find(":selected").data("id");;
	data.estado = "1";
	data.indices = indices;
	console.log("data: " + JSON.stringify(data));
	return data;
}

function addCboMedida(texto){
	var text2 = texto;
	$("#medida option").filter(function() {
	    return this.text == text2; 
	}).attr('selected', true);
}



function dataEliminar(indices) {
	var data = {};
	data.descripcionAsignada = "";
	data.idMaster = $('#cboSubActividades').find(":selected").data("idmaster");
	data.idDetalle = $('#cboActAsignadas').find(":selected").data("id");
	data.estado = "0";
	data.indices = indices;
	return data;
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
});


	function cambioArea(idArea){
		$("#cboActividades").html("");
		$("#cboActPosibles").html("");
		$("#cboActAsignadas").html("");
		armarCboParametrosAsignados();
		var idAreaSeleccionada = idArea;
		
		if(idAreaSeleccionada == undefined || idAreaSeleccionada == null){
			clearCombosForArea();
			armarCboParametrosAsignados();
		}else{
			
			MYAPPL.blockPageLoad();
			$.ajax({
				url : "listActividadesCbo/"+idArea,
				type : 'GET',
				dataType : "json",
				complete : function(xhr, status) {
					if(status == 'success' || status=='notmodified')
					{
						console.log("lenght: "+ xhr)
						var data =  $.parseJSON(xhr.responseText);
						var html = '';
						var len = data.length;
						if(len<1){
							$("#cboActividades").html("");
							$("#cboActPosibles").html("");
							
						}else{
							html += '<option>-Seleccione-</option>';
							for ( var i = 0; i < len; i++) {
								html += '<option data-idMaster="'+ data[i].idMaster + '"  data-idDetalle="'+ data[i].idDetalle +'" value="' + data[i].value + '">'
								+ ((data[i].custom=='' || typeof data[i].custom === "undefined" )?data[i].label:data[i].custom) + '</option>';
							}
						}
						html += '</option>';
						$("#cboActividades").html(html);
					}
					$.unblockUI();
				}
			});
		}
	}

	function cambioActividad(idActividad){
		
		$("#cboActPosibles").html("");
		$("#cboActAsignadas").html("");
		$("#cboSubActividades").html("");
		armarCboParametrosAsignados();
		
		console.log("idActividad: " + idActividad)
		if(idActividad != undefined && idActividad != null){
			
			MYAPPL.blockPageLoad();
			$.ajax({
				url : "listSubActividadesCbo/"+idActividad,
				type : 'GET',
				dataType : "json",
				complete : function(xhr, status) {
					if(status == 'success' || status=='notmodified')
					{
						console.log("lenght: "+ xhr)
						var data =  $.parseJSON(xhr.responseText);
						var html = '';
						var len = data.length;
						if(len<1){
							$("#cboSubActividades").html("");
							$("#cboActPosibles").html("");
							
						}else{
							html += '<option>-Seleccione-</option>';
							for ( var i = 0; i < len; i++) {
								html += '<option data-idMaster="'+ data[i].idMaster + '"  data-idDetalle="'+ data[i].idDetalle +'" value="' + data[i].value + '">'
								+ data[i].label + '</option>';
							}
						}
						html += '</option>';
						$("#cboSubActividades").html(html);
					}
					
					$.unblockUI();
				}
			});
			
		}
	}
	
	
	function cambioSubactividad(idSubactividad){
		var idMaster = idSubactividad;
		
		if(idMaster==undefined || idMaster == null){
			$("#cboActPosibles").html("");
			$("#cboActAsignadas").html("");
			armarCboParametrosAsignados();
			return;
		}
		
		var arrayAsignadas = [];
		var actPosibles;
		
		MYAPPL.blockPageLoad();
		$.getJSON("listParametros").then(function(data){
			actPosibles = data;

			$.getJSON("listParametrosAsignados/"+idMaster).then(function(data){
		 
		var actAsignadas = data;

		for(var i in actAsignadas) {
			  		arrayAsignadas.push(actAsignadas[i].id);
		};

		actPosibles = actPosibles.filter(function(currentObject,index) {
		   if(arrayAsignadas.indexOf(currentObject.id)!=-1){
			   
		   		return false;
		   }
		   return true;
		});
		//CARGA DE LOS DISTRITOS EN LA BUSQUEDA
		
		armarCboWithButtonAdd(actAsignadas,"#cboActAsignadas");
		
		setTimeout(function(){
			$('#cboActAsignadas option:eq(0)').attr('selected', 'selected');
			$('#cboActAsignadas option:eq(0)').click();
			
		},200)

		});
			$.unblockUI();
		});
	}
	
	$('#cboAreas').change(function() {
		var idArea = $('#cboAreas').find(":selected").data("id");
		cambioArea(idArea);		
	});
	
	$('#cboActividades').change(function() {
		var idActividad = $('#cboActividades').find(":selected").data("idmaster");
		cambioActividad(idActividad);
	});
	
	$('#cboSubActividades').change(function() {
		var idSubactividad = $('#cboSubActividades').find(":selected").data("idmaster");
		cambioSubactividad(idSubactividad);
	});
	
	$('#form-search-tp').on('click','#btnActualizarDescri', function() {
		var codplan = $("#idPlan").val();
		console.log("Aprobando plan " + codplan)
		aprobarPlan(codplan);
	});
	$(".list-group-item").click(function(){
		console.log("GGWP");
	})
	
	function unselectOption(idPickListDestinoMovido,idItemMoved) {
		  var ident = idPickListDestinoMovido + " option"
	     $.each($(ident), function() {
	    	 var id= $(this).attr('data-id');
	    	 			if(idItemMoved!=id){
	    	 				$(this).attr('selected', false);
	    	 				$(this).prop('selected', false);
	    	 			}
	                });
	 }
	
	function clearCombosForArea(){
		$("#cpDesc").val("");
		$("#cboSubActividades").html("");
		$("#cboActPosibles").html("");
		$("#cboActAsignadas").html("");
	}
	
	function armarCboParametrosAsignados(){
		$("#cboActAsignadas").html('<li class="list-group-item" style="font-weight: bold;">Par&aacute;metros asignados</li>');
	}
	 


	//FUNCION PARA INACTIVAR UN REGISTRO
	function aprobarPlan(id) {
		
		var urlAprobar = 'aprobarPlan'
		var mensaje = '\u00BFConfirma aprobar Plan Operativo\u003F';
		if(PLAN_APROBADO==0){
			mensaje = '\u00BFConfirma desaprobar Plan Operativo\u003F';
		}
		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: mensaje,
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
		        		var rowConDatosVacio = $("#myPlanOperativoDetalle > tbody").find("tr > .dataTables_empty").length;
		        		if (rowConDatosVacio == 1 && PLAN_APROBADO!=0){
		        			var idMessage = 1;
		        			$.ajax({
		        		    	   type: 'POST',
		        		    	   url: 'showMessageInfo/'+idMessage,
		        		    	   success: function(result){ 
		        		    		   $('#id-modal-parametroplan').html(result);
		        			    	   },
		        					complete: function() {
		        					   }
		        			   }); 
		        		}else {
		        		
					    $.ajax({
				    	   type: 'POST',
				    	   url: urlAprobar+'/'+id+'/'+PLAN_APROBADO,
				    	   success: function(result){  	
				    		   if(PLAN_APROBADO==1){
				    			   $("#btnActualizarDescri").data("aprobado",0);
					    		   $("#btnActualizarDescri").attr("disabled","disabled"); 
					    		   $(".param").addClass("hidden")
					    		   $(".paramAdd").attr("disabled","disabled"); 
					    		   
					    		   MYAPPL.showToast("genericOk", "Plan operativo correctamente aprobado");
				    		   }else{
				    			   $("#btnActualizarDescri").data("aprobado",1);
				    			   $("#btnActualizarDescri").removeAttr("disabled");
				    			   
				    			   setTimeout(function(){
				    				   console.log("habilitando boton ")
				    				   $(".btnParametro").removeAttr("disabled");  
				    				   $(".param").removeClass("hidden")
				    				   $(".paramAdd").removeAttr("disabled");
				    				   
				    				   MYAPPL.showToast("genericOk", "Plan operativo correctamente desaprobado");
				    			   },300)
				    			   
				    		   }
				    		   	
					    	   },
								complete: function() {
									$('#myPlanOperativoDetalle').DataTable().ajax.reload();
									
								}
					    	});
		        		   }
			        	}
			        },
			      Cancelar: {
			        }
			    }
			});
		
	};
	
	function modificarParametroPlanGrid(codact,codsubact,codsxp,insert,textparam) {
		var tipoModal = "MODIFICAR";
		
		var data = {};
		data.codsxp = codsxp;
		data.codplan = $('#idPlan').val();
		data.codarea = $('#cboAreas').find(":selected").data("id");
		data.codact = codact;
		data.codsubact = codsubact;
		data.textparam = textparam;
		data.insert = insert;
		data.tipoModal = tipoModal;
		
		console.log("data.insert: " +codact);
		
		modificarParametro(data);
	}
	
	function modificarParametroPlan(codsxp,insert,textparam) {
		var data = dataModificarParametro(codsxp,textparam);
		data.insert = insert;
		
		modificarParametro(data);
		
//		console.log("insert: " + insert)
		$("#parametro-modal").text("prueba");
	}
	
	function modificarParametro(data) {
		
		$.ajax({
			type: 'GET',
			url: 'planOperativoGet/'+data.insert, 
			data :data,
			success: function(result) {
				
				$('#modal-registra-parametroplan').modal('show');
				$('#id-modal-parametroplan').html(result);
				
				if(data.codsxp != 0){
					
					$('#idModalTitle').html($('#idValEditModalTitle').html());
					$("#intCodLambda").attr('disabled', 'disabled');
					$("#intCodIgx").attr('disabled', 'disabled');
					
				} else {
					
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
					
				}
				if(data.tipoModal == "MODIFICAR"){
					$("#parametro-modal").text("Editar Par\u00E1metro de Plan Operativo");
				}else{
					$("#parametro-modal").text("Registrar Par\u00E1metro de Plan Operativo");
				}
				
				
				enableValidationRules();
			},complete: function() {
				
				$('#id-modal-parametroplan').removeClass('hidden');
				
				//cargarComboSameValue("listUnidadMedida","#medida");
				cargarCombo("listUnidadMedida","#medida")
				
				setTimeout(function(){
					var valueInput = $("#medidaInput").val();
					
					if(valueInput == "" || valueInput == undefined){
						$("#medida").val(1);
					}
					else{
						
						$("#medida").val(valueInput);
					}
					
				}, 200);
				$.unblockUI();
			}
		});
	}
		
	function dataModificarParametro(codsxp,textparam){
		
		var data = {};
		data.codsxp = codsxp;
		data.codplan = $('#idPlan').val();
		data.codarea = $('#cboAreas').find(":selected").data("id");
		data.codact = $('#cboActividades').find(":selected").data("iddetalle");
		data.codsubact = $('#cboSubActividades').find(":selected").data("iddetalle");
		data.textparam = textparam;
		console.log("actividad: " + $('#cboActividades').find(":selected").data("idmaster"));
		return data;
	}

	//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
	function enableValidationRules() {

		var validator = $("#form-edit-parametroplan-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				 mes1: {
						validators : {
							regexp: {
								 regexp:/^[0-9]+$/,
								 message: 'Cantidad incorrecta'
							},
							stringLength : {
								max : 5,
								message : 'Ingrese cantidad correcta'
							},
							notEmpty: {
		                        message: 'Ingrese cantidad'
		                    }
						}
		        },
		        mes2: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes3: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes4: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes5: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes6: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes7: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes8: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes9: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes10: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes11: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          mes12: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 5,
							message : 'Ingrese cantidad correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          medida: {
					validators : {
						
						stringLength : {
							max : 20,
							message : 'Ingrese unidad medida correcta'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	          propuesta: {
					validators : {
						regexp: {
							 regexp:/^[0-9]+$/,
							 message: 'Cantidad incorrecta'
						},
						stringLength : {
							max : 9,
							message : 'Ingrese propuesta correcta'
						}
					}
	          },
	          parametro: {
					validators : {
						
						stringLength : {
							max : 500,
							message : 'Ingrese valor correcto'
						},
						notEmpty: {
	                        message: 'Ingrese cantidad'
	                    }
					}
	          },
	           
			}
		})	
		.on('success.form.bv', function(e, data) {
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoTablaPoissonForm = $(e.target);
			$.ajax({
				type: 'POST',
				url:"parametroPlanSave",
				data: $('#form-edit-parametroplan-id').serialize(),
				success: function(result){
					$nuevoTablaPoissonForm.bootstrapValidator('resetForm', false);
					$nuevoTablaPoissonForm[0].reset();
					$('.container_save').html(result);
				},
				complete: function() {
					$("#guardarButton").html('Guardar');
					$('#guardarButton').removeAttr("disabled");
				}
			});
		});	
	}


	//FUNCION PARA INACTIVAR UN PARAMETRO
	function inactivarParametroPlan(codsxp) {
		console.log("codsxp : " + codsxp);
		console.log("codsxp : " +  $('#idPlan').val());
		 
		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: '\u00BFConfirma eliminar el registro\u003F',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
					    $.ajax({
				    	   type: 'POST',
				    	   url: 'inactivarParametroPlan',
				    	   data: dataInactivarParametro(codsxp),
				    	   success: function(result){
				    		   $('#id-modal-parametroplan').html(result);
					    	   },
					    	}); 
			        	}
			        },
			        Cancelar: {
			        }
			    }
			});	   
	};
	
	function dataInactivarParametro(codsxp){
			var data = {};
			data.codsxp = codsxp;
			data.codplan = $('#idPlan').val();
			return data;
	}
	
	function cargarTableParametroPlan(){
		
		var codplan = $('#idPlan').val(); 
		var aprobado;
		$('#myPlanOperativoDetalle').DataTable({
			
			"ordering": false,
			"bProcessing": true,
			"serverSide": true,
			"searching": false,
			"lengthChange":   false,
			"paging" : false,
			"ajax":{
				url :"planOperativoDetalle/pagination/" + codplan, 
	            type: "post",  // type of method  ,GET/POST/DELETE
	           
	            error: function(){
	              $("#myPlanOperativoDetalle_processing").css("display","none");
	            },
	            complete: function(xhr,status){
	            	
	            	var data =  $.parseJSON(xhr.responseText);
	            	aprobado = data.aprobado;
	            	PLAN_APROBADO = aprobado;
	            	if(aprobado==0){
	            		
	            		aprobado = 'disabled="disabled"';
	            		setTimeout(function(){
	            			console.log("aprobado es 0 ");
		            		$(".btnParametro").attr("disabled","disabled");
		            		$("#btnActualizarDescri").text("Desaprobar plan");
		            	},500)
	            	}else{
	            		
	            		$("#btnActualizarDescri").text("Aprobar plan");
	            	}
	            	
	            	$("#btnActualizarDescri").removeAttr("disabled");
	            	console.log("cargando actividades");
	            	
	            	setTimeout(function(){
	            	 cargarNombresActividades();
	            	 cargarNombresSubactividades();
	            	 cargarNombresUnidadMedidas();
	            	},500)
	            }
	            
//	            "data": function ( d ) {
//	            }
			},
			"columnDefs": [
				{"width": "120px",
	        		className: "dt-body-center",
	        		sortable :false,
	                "targets": 0                 
	            },

              	{"width": "40px",
        		  	className: "dt-body-center",
        		  	sortable :false,
                  	"targets": 1
              	},
              	
              	{"width": "100px",
        		  	className: "dt-body-center actividades",
        		  	sortable :false,
                  	"targets": 2
              	},
	            
	            {"width": "100px",
        		  	className: "dt-body-center subactividades",
        		  	sortable :false,
                  	"targets": 3
              	},
             
              	 {"width": "50px",
        		  	className: "dt-body-center",
        		  	sortable :false,
                  	"targets": 4
              	},
              	{"width": "100px",
        		  	className: "dt-body-center",
        		  	sortable :false,
                  	"targets": 5
              	},
              	{"width": "80px",
        		  	className: "dt-body-center unidadesMedidas",
        		  	sortable :false,
                  	"targets": 6
              	},
              	{"width": "40px",
              		sortable :false,
        		  	className: "dt-body-center",
                  	"targets": [7,8,9,10,11,12,13,14,15,16,17,18,19]
              	},             	
              	{
        		  	visible: false,
                  	"targets": 20
              	},
              	
              	{"width": "40px",
              		className: "dt-body-center",
              		"sortable" :false,
              		"targets": 0,
              		visible: true,
              		"data": 1,
              		"render": function ( data, type, row) {
              			return '<button class="btn btn-info btn-modif-punto btnParametro" '+aprobado+' title="Editar" onclick="modificarParametroPlanGrid('+row[2]+ ','+row[3]+ ','+row[20]+',1,\'' + row[5] + '\')"><span class="glyphicon glyphicon-pencil"></span></button>'+
              			 '<button class="btn btn-danger btn-delete-punto btnParametro" title="Eliminar" onclick="inactivarParametroPlan('+row[20]+')"><span class="glyphicon glyphicon-erase"></span></button>';
              		}
              	}
			]
		});
	}
	
	function showToastPlanOperativo(mensajeTipo, mensaje, mensajeError) {
		console.log("entrando al toast")
		
		//Mensajes despues de grabar
		if(mensajeTipo==='grabadoOk'){
			 toastr["success"](mensajeError, mensaje);
			 console.log("existente param")
			 setTimeout(function(){
				 $('#modal-registra-parametroplan').modal('hide');
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
	
	$('#modal-registra-planoperativo-bandeja').on('click','#idModalParametroDetalle', function() {
		$('#id-modal-parametroplan').addClass('hidden');
	});
	
	 $('#id-modal-parametroplan').on('click','#cancelParametroDetalleEdit', function() {
		 console.log("ocultando popup")
			$('#id-modal-parametroplan').addClass('hidden');
	 });
	
	function armarCboWithButtonAdd(data,idSelect){
		var html = '';
		var len = data.length;
		var classI = PLAN_APROBADO==0? "glyphicon glyphicon-plus param hidden" : "glyphicon glyphicon-plus";
		var classButton = PLAN_APROBADO==0? "btn btn-info btn-crear-mant-correo paramAdd pull-right param hidden" : "btn btn-info btn-crear-mant-correo paramAdd pull-right";
		var tagButton = ''
		var tagButtonCierre = '</button></div>'
		
		html += ' <li class="list-group-item" style="font-weight: bold;">Par&aacute;metros asignados</li>';
		for ( var i = 0; i < len; i++) {
			tagButton = '<div class="col-md-2 col-sm-2 col-xs-2 "><button type="button" class="'+classButton+'" data-toggle="modal" style="width:40px; text-align:center;margin-left:90px" onclick="modificarParametroPlan('+data[i].custom+',0,\'' + data[i].label + '\')">'
			html += '<li class="list-group-item" style="text-align:left;height:70px" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
			+ '<div class="col-md-12 col-sm-12 col-xs-12">'
			+ '<label class="col-md-10 col-sm-10 col-xs-10">'
			+ data[i].label 
			+ '</label>'
			+ tagButton + '<i  class="'+classI+'"></i>'+tagButtonCierre+'</li></div>';
		}
		html += '</li>';
		$(idSelect).html(html);
	}
	
	
	
	function cargarNombresActividades(){
		$(".actividades").each(function(){
			console.log("modificando actividades : " )
			var valorActividad = $(this).text();
			var valorNuevo = ACTIVIDADES_CARGADAS[valorActividad];
			
			if(valorNuevo!=null && valorNuevo!=undefined){
				$(this).text(valorNuevo);
			}
		})
	}
	
	function cargarNombresSubactividades(){
		$(".subactividades").each(function(){
			console.log("modificando subactividades : ");
			var valorSubactividad = $(this).text();
			var valorNuevo = SUBACTIVIDADES_CARGADAS[valorSubactividad];
			
			if(valorNuevo!=null && valorNuevo!=undefined){
				$(this).text(valorNuevo);
			}
		})
	}
	
	function cargarNombresUnidadMedidas(){
		$(".unidadesMedidas").each(function(){
			console.log("modificando unidadesMedidas : ");
			var unidadesMedidas = $(this).text();
			var valorNuevo = UNIDAD_MEDIDAS_CARGADAS[unidadesMedidas];
			
			if(valorNuevo!=null && valorNuevo!=undefined){
				$(this).text(valorNuevo);
			}
		})
	}

	
	$("#modal-registra-planoperativo-bandeja").on("hidden.bs.modal", function () {
		console.log("cerrando modal");
		$('#idTablaPlanOperativoBandeja').DataTable().ajax.reload();
    });
	
	function actualizarPropuesta(){
		var cantPropuesta = 0;
		$(".cantidadmes").each(function(){
			cantPropuesta+= new Number($(this).val());
		})
		
		console.log("cantidad propuesta: " + cantPropuesta);
		$("#propuesta").val(cantPropuesta);
		$("#propuesta").text(cantPropuesta);
		$("#propuesta").attr("value",cantPropuesta);
		$("#propuesta").prop("value",cantPropuesta);
	}
	
	
	


