(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.TURB_DIGI");
	
	module.configDoc = function($codigo, $page, $anio, $tipo, $tipArchDocumento, $tamMaxArchDocumento){
		let $doc = {
			id : $page,
			anhoDocumento : $anio,
			tipoDocumento : $tipo,
			codDocumento : $codigo,
			tipArchDocumento : $tipArchDocumento,
			tamMaxArchDocumento : $tamMaxArchDocumento,
			secciones : [
				{
					id : 'upload_files_turb_digi',
					cod: 1,
					codCantMax: 200
				}
			]
		};
		return $doc;
	}
	
	module.initUpload = function() {
		let $page = nsSAA.obtenerIdPagina();
		let $codigo = $("#codigo").val();
		let $anio = '2017';
		let $tipo = $("#codigoTipo").val();
		let $add = true; //($("#editar").val() == "true");
		let $tipArchDocumento = $("#tipArchDocumento").val();
		let $tamMaxArchDocumento = $("#tamMaxArchDocumento").val();
		let $doc = module.configDoc($codigo, $page, $anio, $tipo, $tipArchDocumento, $tamMaxArchDocumento);
		nsSAA.configurarGlobal($add, $doc);
		$("#page").val($page);
	};

	
})(jQuery);	

$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTurbidimetroDigital').click(function () {

		 $('#codigoHide').val($('#idCodigo').val());
		 $('#myTableTurbidimetroDigital').DataTable().ajax.reload();
	});

	//CARGA EL COMBO DE GRUPO
	//cargarCombo("listGrupoTodos","#codGrupo");
	
	
});

var idEquipo;
function modificarTurbidimetroDigital(id) {
	idEquipo = id;
	$.ajax({
		type: 'GET',
		url: 'turbidimetroDigitalGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			
			$('#codigoVerif').val(id);
			cargarCombo("listComboMeses","#codMes");
			cargarCombo("listComboMeses","#codMesProx");
			cargarCombo("listComboUbicacion","#codUbicacion");
			if(id != -1){
				

				
				$("#idTableVerificacion").show();
				$("#strCodEquipo").attr('disabled', 'disabled');
				$('#idModalTitle').html($('#idValEditModalTitle').html());

			}else{

			$("#idTableVerificacion").hide();
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			enableValidationRules();								
		},complete: function() {				
			$('#modal-registra-turbidimetroDigital').modal('show');
			if(id != -1){		
				setTimeout(function(){
					$('#codMes').val($('#codMesHide').val());
					$('#codMesProx').val($('#codMesProxHide').val());
				}, 500);

				//$('#codMes').find("[value='" + $('#codMesHide').val() + "']").prop('selected', 'selected');
				//$('#codMes').find("[value='" + $('#codMesHide').val() + "']").attr('selected', 'selected');
				
			}
			$.unblockUI();
			
		}
	});
}


function enableValidationRules() {

	var validator = $("#form-edit-turbidimetroDigital-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			strCodEquipo: {
					validators : {
								
						stringLength : {
							max : 20,
							message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
						},
						notEmpty: {
	                        message: 'Ingrese Código de equipo'
	                    }
					}
			},
			strRangoNTU1: {
					validators : {
								
						stringLength : {
							max : 20,
							message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
						},			
						regexp: {
							 
							 regexp: /^[0-9]+([.][0-9]+)?$/, 
		 
							 message: 'Valor incorrecto'
		 
						}
					}
			},
			strRangoNTU2: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strRangoNTU3: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strRangoNTU4: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strRangoNTU5: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},			
			strMaxNTU1: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMaxNTU2: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMaxNTU3: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMaxNTU4: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMaxNTU5: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMinNTU1: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMinNTU2: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMinNTU3: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMinNTU4: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
			strMinNTU5: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Valor incorrecto'
	 
					}
				}
			},
            
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTurbidimetroDigitalForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-turbidimetroDigital-id').attr('action'),
			data: $('#form-edit-turbidimetroDigital-id').serialize(),
			success: function(result){
				$nuevoTurbidimetroDigitalForm.bootstrapValidator('resetForm', true);
				$nuevoTurbidimetroDigitalForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});	
}

function modificarVerifTurbidimetroDigital() {

	$.ajax({
		type: 'GET',
		url: 'turbidimetroDigitalVerificacionGet/'+idEquipo, 
		success: function(result) {
			$('#id-modal-content-verif').replaceWith(result);
			
			$('#idFormVerificacion').val(idEquipo);			
			
			$('#strFechaVerificacion').datetimepicker({
			      //  format: 'DD/MM/YYYY HH:mm:SS',
					format: 'DD/MM/YYYY',
			        ignoreReadonly: false,
			        date: new Date(),
			        locale: "es",
			        maxDate: "now"
			    });
			
			if(idEquipo != 0){
				$('#idModalVerifTitle').html($('#idValEditModalVerifTitle').html());

			}else{
				$('#idModalVerifTitle').html($('#idValCrearModalVerifTitle').html());
			}
			enableValidationRulesVerificacion();								
		},complete: function() {
			 
			$('#modal-registra-turbidimetroDigitalVerif').modal('show');
			$.unblockUI();
			
		}
	});
}

function enableValidationRulesVerificacion() {

	var validator = $("#form-edit-turbidimetroDigitalVerif-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			
			strFechaVerificacion: {
				validators : {
							
					notEmpty: {
                        message: 'Ingrese Fecha'
                    }		
					
				}
			},
			strResponsable: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},
				
				notEmpty: {
				message: 'Ingrese valor del campo'
				}
					
				}
			},
			strObservacion: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					}	
					
				}
			},
			 strValorVerificacion1: {
	    			validators : {
	    				regexp: {
	    					 regexp:/^\d{1,7}(\.\d{1,2})?$/,
	    					 message: 'Valor m\u00E1ximo 7 enteros y 2 decimales'
	    				},
	    				
	    				notEmpty: {
	    					message: 'Ingrese valor del campo'
	                  }
	    			}
	          },
            strValorVerificacion2: {
    			validators : {
    				regexp: {
    					 regexp:/^\d{1,7}(\.\d{1,2})?$/,
    					 message: 'Valor m\u00E1ximo 7 enteros y 2 decimales'
    				},
    				
    				notEmpty: {
    					message: 'Ingrese valor del campo'
                  }
    			}
          },
            strValorVerificacion3: {
    			validators : {
    				regexp: {
    					 regexp:/^\d{1,7}(\.\d{1,2})?$/,
    					 message: 'Valor m\u00E1ximo 7 enteros y 2 decimales'
    				},
    				
    				notEmpty: {
    					message: 'Ingrese valor del campo'
                  }
    			}
          },
            strValorVerificacion4: {
    			validators : {
    				regexp: {
    					 regexp:/^\d{1,7}(\.\d{1,2})?$/,
    					 message: 'Valor m\u00E1ximo 7 enteros y 2 decimales'
    				},
    				
    				notEmpty: {
    					message: 'Ingrese valor del campo'
                  }
    			}
          },
           
            
            strValorVerificacion5: {
    			validators : {
    				regexp: {
    					 regexp:/^\d{1,7}(\.\d{1,2})?$/,
    					 message: 'Valor m\u00E1ximo 7 enteros y 2 decimales'
    				},
    				
    				notEmpty: {
    					message: 'Ingrese valor del campo'
                  }
    			}
          }
            
            
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTurbidimetroDigitalVerificacionForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-turbidimetroDigitalVerif-id').attr('action'),
			data: $('#form-edit-turbidimetroDigitalVerif-id').serialize(),
			success: function(result){
				$nuevoTurbidimetroDigitalVerificacionForm.bootstrapValidator('resetForm', true);
				$nuevoTurbidimetroDigitalVerificacionForm[0].reset();
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
function inactivarTurbidimetroDigital(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma eliminar registro?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacId').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-turbidimetroDigital').attr('action'),
			    	   data: $('#form-inactiva-turbidimetroDigital').serialize(),  
			    	   success: function(result){  		   
			    	       $('.container_save').html(result);		    	      
				    	   },
							complete: function() {
								$.unblockUI();
							}
				    	}); 
		        	}
		        },
		        Cancelar: {
		        }
		    }
		});	   
};

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarTurbidimetroDigitalVerificacion(id) {

	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma eliminar registro?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdVerif').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-turbidimetroDigitalVerif').attr('action'),
			    	   data: $('#form-inactiva-turbidimetroDigitalVerif').serialize(),  
			    	   success: function(result){  		
			    	       $('.container_save').html(result);		    	      
				    	   },
							complete: function(data) {
								$.unblockUI();
							}
				    	}); 
		        	}
		        },
		        Cancelar: {
		        }
		    }
		});	   
};

function showToastTurbidimetroDigital(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-turbidimetroDigital').modal('hide');
		 $('#idBtnBuscarTurbidimetroDigital').click();
	 }if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-turbidimetroDigital').modal('hide');
		 $('#idBtnBuscarTurbidimetroDigital').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 $('#modal-registra-turbidimetroDigital').modal('hide');
		 $('#idBtnBuscarTurbidimetroDigital').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
		 //$('#modal-registra-turbidimetroDigital').modal('hide');
		 //$('#idBtnBuscarTurbidimetroDigital').click();
	 }
	 if(mensajeTipo==='grabadoOkSubForm'){
		 toastr["success"](mensajeError, "Registro agregado correctamente");
		 $('#modal-registra-turbidimetroDigitalVerif').modal('hide');
		 $('#idCodigoVerifEquipo').val($('#intCodigo').val());
		 $('#myTableTurbidimetroDigitalVerif').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='inactivadoOkSubForm'){

		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 //$('#modal-registra-turbidimetroDigitalVerif').modal('hide');
		 $('#idCodigoVerifEquipo').val($('#intCodigo').val());
		 $('#myTableTurbidimetroDigitalVerif').DataTable().ajax.reload();
	 }
}

