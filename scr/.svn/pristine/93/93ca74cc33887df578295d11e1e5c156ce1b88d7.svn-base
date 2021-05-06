(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.PHME_DIGI");
	//let module = MYAPPL.LOGIC.MANIFIESTO;
	
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
					id : 'upload_files_phme_digi',
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
	$('#idBtnBuscarPhmetroDigital').click(function () {		 
		 $('#myTablePhmetroDigital').DataTable().ajax.reload();
	});
	
});



function modificarPhmetroDigital(id) {
	
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'phmetroDigitalGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			
			
			
			if(id != -1){
				$("#strCodEquipo").attr('disabled', 'disabled');
				$('#idModalTitle').html($('#idValEditModalTitle').html());
				setTimeout(function(){
					///FIX FECHAS EN CADUCIDAD
				loadDateOnInput("#strCalCaducidad1");
				loadDateOnInput("#strCalCaducidad2");
				loadDateOnInput("#strCalCaducidad3");
				
				loadDateOnInput("#strVerCaducidad1");
				loadDateOnInput("#strVerCaducidad2");
				loadDateOnInput("#strVerCaducidad3");
				},200)
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
				setTimeout(function(){
					///FIX FECHAS EN CADUCIDAD
				loadDateOnInput("#strCalCaducidad1");
				loadDateOnInput("#strCalCaducidad2");
				loadDateOnInput("#strCalCaducidad3");
				
				loadDateOnInput("#strVerCaducidad1");
				loadDateOnInput("#strVerCaducidad2");
				loadDateOnInput("#strVerCaducidad3");
				},200)
			}
			enableValidationRules();
		},complete: function() {
			$('#modal-registra-phmetroDigital').modal('show');
			$.unblockUI();
						
		}
	});
}

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRules() {
		
	var validator = $("#form-edit-phmetroDigital-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {	
			strNomEquipo: {
				validators : {                    
					notEmpty: {
                        message: 'Ingrese valor'
                    },
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},
                    callback: {
                        message: 'No debe haber espacios al inicio ni al final',
                        callback: function(value, validator, $field) {
                        	let numero = value; //$("#form-edit-phmetroDigital-id #strNomEquipo").val(); 

                        	if(numero != undefined && numero != '' && numero != ' '){
	                        	//numero ahora es string
	                        	if( numero.trim().localeCompare(numero) === 0)
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
                        	}
                            return true;
                        }
                    }
				}
			},		
		
			strCodEquipo: {
				validators : {
							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese Código de equipo'
                    },
                    callback: {
                        message: 'No debe haber espacios al inicio ni al final',
                        callback: function(value, validator, $field) {
                        	let numero = value; //$("#form-edit-phmetroDigital-id #strNomEquipo").val(); 

                        	if(numero != undefined && numero != '' && numero != ' '){
	                        	//numero ahora es string
	                        	if( numero.trim().localeCompare(numero) === 0)
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
                        	}
                            return true;
                        }
                    }
				}
			},	
			
			strNroSerie: {
						validators : {							
							stringLength : {
								max : 20,
								message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
							},
							regexp: {
								regexp:/^[a-z\d\-_\s]+$/i, 
								 message: 'Ingrese valor correcto de modelo'
							},
							notEmpty: {
		                        message: 'Ingrese Valor'
		                    }

						}
		   },		
	
		  strMarca: {
					validators : {							
						stringLength : {
							max : 20,
							message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
						},
						regexp: {
							regexp:/^[a-z\d\-_.\s]+$/i, 
							 message: 'Ingrese valor correcto de modelo'
						},
						notEmpty: {
	                        message: 'Ingrese Valor'
	                    }

					}
		   },	
			strModelo: {
				validators : {							
					stringLength : {
						max : 20,
						message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
					},
					regexp: {
						regexp:/^[a-z\d\-_\s]+$/i, 
						 message: 'Ingrese valor correcto de modelo'
					},
					notEmpty: {
                        message: 'Ingrese Valor'
                    }

				}
			},
			strCalPendiente1: {
				validators : {							
		
                    regexp: {
                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
                    }
				}
			},
			strCalPendiente2: {
				validators : {							
		
                    regexp: {
                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
                    }
				}
			},
			strCalPendiente3: {
				validators : {							
		
                    regexp: {
                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
                    }
				}
			},
			strCalPendiente4: {
				validators : {							
		
                    regexp: {
                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
                    }
				}
			},
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		MYAPPL.toUppercaseInputs();
		var $nuevoPhmetroDigitalForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-phmetroDigital-id').attr('action'),
			data: $('#form-edit-phmetroDigital-id').serialize(),
			success: function(result){
				$nuevoPhmetroDigitalForm.bootstrapValidator('resetForm', true);
				$nuevoPhmetroDigitalForm[0].reset();
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
function inactivarPhmetroDigital(id) {
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
			    	   url: $('#form-inactiva-phmetroDigital').attr('action'),
			    	   data: $('#form-inactiva-phmetroDigital').serialize(),  
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
}

var idPhMetro;
function modificarPhmetroDigitalDetalle(id) {
	idPhMetro=id;
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'phmetroDigitalDetalleGet/'+id, 
		success: function(result) {
			$('#id-modal-content-detalle').replaceWith(result);
			
			$('#idCodigoPHmetro').val(id);
			
			if(id != -1){
				$("#strNomEquipo2").attr('disabled', 'disabled');
				$("#strCodEquipo2").attr('disabled', 'disabled');
				$("#strMarca2").attr('disabled', 'disabled');
				$("#strModelo2").attr('disabled', 'disabled');
				$("#strNroSerie2").attr('disabled', 'disabled');
				
				$('#idModalTitle').html($('#idValEditModalTitle').html());
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
		},complete: function() {
			$('#modal-registra-phmetroDigital-detalle').modal('show');
			$.unblockUI();
			

		}
	});
}


function modificarPhmetroDigitalDetalleEdit() {
	//alert(idPhMetro);
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'phmetroDigitalDetalleEditGet/'+idPhMetro, 
		success: function(result) {
			$('#id-modal-content-detalle-edit').replaceWith(result);
			
			$('#intIdPhmetroDigital').val(idPhMetro);
			
//			$('#strFecha').datetimepicker({
//			      //  format: 'DD/MM/YYYY HH:mm:SS',
//					format: 'DD/MM/YYYY',
//			        ignoreReadonly: false,
//			        date: new Date(),
//			        locale: "es"
//			    });
						
			$('#idModalTitle').html($('#idValCrearModalTitle').html());
			enableValidationRulesDetalle();
		},complete: function() {
			$('#modal-registra-phmetroDigital-detalle-edit').modal('show');
			$.unblockUI();
			console.log("Cargando fechaaa");
			loadDateOnInput('#strFecha');
		}
	});
}

function enableValidationRulesDetalle() {

	var validator = $("#form-edit-phmetroDigitalDetalleEdit-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			
			strFecha: {
				validators : {
							
					notEmpty: {
                        message: 'Ingrese Fecha'
                    }		
					
				}
			},
			strResponsable: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
			},
			
			strRevisado: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
			},
			strcalibracion1: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			strcalibracion2: {
				
				
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },	
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						 regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			strcalibracion3: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			strcalibracion4: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			
			strVerificacion1: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			strVerificacion2: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			},
			strVerificacion3: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    },		
                    stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					}
				}
			}
			
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		MYAPPL.toUppercaseInputs();
		var $nuevoPhmetroDigitalDetalleEditForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-phmetroDigitalDetalleEdit-id').attr('action'),
			data: $('#form-edit-phmetroDigitalDetalleEdit-id').serialize(),
			success: function(result){
				$nuevoPhmetroDigitalDetalleEditForm.bootstrapValidator('resetForm', true);
				$nuevoPhmetroDigitalDetalleEditForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});	
}

function inactivarPhmetroDigitalDetalle(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma eliminar registro?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdDetalle').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-phmetroDigitalDetalle').attr('action'),
			    	   data: $('#form-inactiva-phmetroDigitalDetalle').serialize(),  
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
}

function showToastPhmetroDigital(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-phmetroDigital').modal('hide');
		 $('#idBtnBuscarPhmetroDigital').click();
	}if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-phmetroDigital').modal('hide');
		 $('#idBtnBuscarPhmetroDigital').click();
	 }
	
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 $('#modal-registra-phmetroDigital').modal('hide');
		 $('#idBtnBuscarPhmetroDigital').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
		 //$('#modal-registra-phmetroDigital').modal('hide');
		 //$('#idBtnBuscarPhmetroDigital').click();
	 }
	 if(mensajeTipo==='grabadoOkSubForm'){
		 toastr["success"](mensajeError, "Registro agregado correctamente");
		 $('#modal-registra-phmetroDigital-detalle-edit').modal('hide');
		 $('#myTablePhmetroDigitalDetalle').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='inactivadoSubFormOk'){
		 toastr["success"](mensajeError, "Registro eliminado correctamente");
		 $('#myTablePhmetroDigitalDetalle').DataTable().ajax.reload();
	 }
}

//FIX PARA PONER FECHAS EN CADUCIDAD
function loadDateOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        //maxDate: new Date()
	}) .on('changeDate', function(e) {
	});
	
	if(valInput == null || valInput == undefined || valInput == ""){
		valInput = getDDMMYYYY();
	}
	
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
}




function loadDateOnInputRegistrar(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate: 'now'
	}) .on('changeDate', function(e) {
	});

}
	 
