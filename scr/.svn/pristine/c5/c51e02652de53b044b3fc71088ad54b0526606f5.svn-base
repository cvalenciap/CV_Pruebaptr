(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.COND_DIGI");
	
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
					id : 'upload_files_cond_digi',
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
	$('#idBtnBuscarConductimetroDigital').click(function () {		 
		 $('#myTableConductimetroDigital').DataTable().ajax.reload();
	});
	
});


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

function loadDateOnInputCaducidad(idInput){
	
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        maxDate: new Date(2200, 0, 1)	
	}) .on('changeDate', function(e) {
	});
	
	console.log("valInput: " + valInput);
	if(valInput == null || valInput == undefined || valInput == "" || valInput == " "){
		valInput = getDDMMYYYY();
	}
	
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
}

function modificarConductimetroDigital(id) {
	
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'conductimetroDigitalGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$("#intCodigo").val(id);			
			
			cargarCombo("listEspecialistas","#strEspecialista");
			cargarCombo("listComboUbicacion","#intArea");
			
			if(id != -1){
				$("#strCodEquipo").attr('disabled', 'disabled');
				$('#idModalTitle').html($('#idValEditModalTitle').html());
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			enableValidationRules();
		},complete: function() {
			$('#modal-registra-conductimetroDigital').modal('show');
			
			if(id != -1){		
				setTimeout(function(){

					$('#intArea').val($('#intAreaHide').val());
					$('#strEspecialista').val($('#strEspecialistaHide').val());
				}, 500);
			}
			
			setTimeout(function(){
				loadDateOnInputCaducidad("#strCalCaducidad1");
				loadDateOnInputCaducidad("#strVerCaducidad1");
			}, 500);
			
			$.unblockUI();
			
		}
	});
}

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRules() {
		
	var validator = $("#form-edit-conductimetroDigital-id").bootstrapValidator({
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
                    },
                    regexp: {
                   	 		regexp: /[^0{1,}]/, 
                   	 		message: 'Valor incorrecto'
	 
					}
				}
			},			
			strValor1: {
				validators : {
					
					notEmpty: {
                        message: 'Ingrese Código de equipo'
                    },
                    
                    
                    regexp: {
						 
						 regexp: /^-?\d{1,2}(\.\d{1,3})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 2 enteros y 3 decimales'
	 
					}
				}
			},
			strValor2: {
				validators : {
					
					notEmpty: {
                        message: 'Ingrese Código de equipo'
                    },
                    
                    regexp: {
						 
						 regexp: /^-?\d{1,2}(\.\d{1,3})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 2 enteros y 3 decimales'
	 
					}
				}
			},
			strKmax: {
				validators : {	
					
					notEmpty: {
                        message: 'Ingrese valor'
					},
				
                    regexp: {
						 
						 regexp: /^-?\d{1,2}(\.\d{1,3})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 2 enteros y 3 decimales'
	 
					},
					callback: {
                        message: 'Kmax debe ser mayor que Kmin',
                        callback: function(value, validator, $field) {
                        	var minima = $('#strKmin').val();
                        	var maxima = $('#strKmax').val();                            
                            if (parseFloat(minima) > parseFloat(maxima)) {
                            	return {
                                    valid: false,
                                    message: 'Kmax debe ser mayor que Kmin'
                                }
                            } else {
                            	validator.updateStatus('strKmin', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                        }
                    }
				}
			},
			strKmin: {
				validators : {
					
					notEmpty: {
                        message: 'Ingrese valor'
					},
                    regexp: {
						 
						 regexp: /^-?\d{1,2}(\.\d{1,3})?$/, 
	 
						 message: 'Valor m\u00E1ximo: 2 enteros y 3 decimales'
	 
					},
					callback: {
                        message: 'Kmin debe ser menor que Kmax',
                        callback: function(value, validator, $field) {
                        	var minima = $('#strKmin').val();
                        	var maxima = $('#strKmax').val();
                            if (parseFloat(minima) > parseFloat(maxima)) {
                            	return {
                                    valid: false,
                                    message: 'Kmin debe ser menor que Kmax'
                                }
                            } else {
                            	validator.updateStatus('strKmax', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                        }
                    }
				}
			},
			
			
			strCalCodigo: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor alfanum\u00E9rico o gui\u00F3n'
					},
					stringLength : {
						max : 20,
						message : "Valor m\u00e1ximo: 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[ A-Za-z0-9-]*$/, 
	 
						 message: 'Ingrese valor alfanum\u00E9rico o gui\u00F3n'
	 
					}
				}
			},
			
			
			strCalNombre: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			
			strCalMarca: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			strCalLote: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			strCalCaducidad: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 
			 }
			},
			strCalEspecificacion: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor alfanum\u00E9rico'
					},
			 }
			},
			
			strVerCodigo: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor alfanum\u00E9rico o gui\u00F3n'
					},
					stringLength : {
						max : 20,
						message : "Valor m\u00e1ximo: 20 caracteres"
					},			
					regexp: {
						 
						 regexp: /^[ A-Za-z0-9-]*$/, 
	 
						 message: 'Ingrese valor alfanum\u00E9rico o gui\u00F3n'
	 
					}
				}
			},
			
			
			strVerNombre: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			
			strVerMarca: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			strVerLote: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			strVerCaducidad: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
					},
			 }
			},
			
			strVerEspecificacion: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor alfanum\u00E9rico'
					},
			 }
			}
			
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoConductimetroDigitalForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-conductimetroDigital-id').attr('action'),
			data: $('#form-edit-conductimetroDigital-id').serialize(),
			success: function(result){
				$nuevoConductimetroDigitalForm.bootstrapValidator('resetForm', true);
				$nuevoConductimetroDigitalForm[0].reset();
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
function inactivarConductimetroDigital(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar registro?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacId').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-conductimetroDigital').attr('action'),
			    	   data: $('#form-inactiva-conductimetroDigital').serialize(),  
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



var idConductimetro;
function modificarConductimetroDigitalDetalle(id) {
	idConductimetro=id;
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'conductimetroDigitalDetalleGet/'+id, 
		success: function(result) {
			$('#id-modal-content-detalle').replaceWith(result);
			
			cargarCombo("listEspecialistas","#strEspecialista2");
			cargarCombo("listComboUbicacion","#intArea2");
			$('#intIdConductimetro').val(id);
			
			$("#intArea2").attr('disabled', 'disabled');				
			$("#strCodEquipo2").attr('disabled', 'disabled');		
			$("#strEquipoMedicion2").attr('disabled', 'disabled');		
			$("#strNroSerie2").attr('disabled', 'disabled');		
			$("#strEspecialista2").attr('disabled', 'disabled');		

			$('#idModalTitle2').html($('#idValEditModalTitle').html());
			
		},complete: function() {
			$('#modal-registra-conductimetroDigitalDetalle').modal('show');
			setTimeout(function(){

				$('#intArea2').val($('#intArea2Hide').val());
				$('#strEspecialista2').val($('#strEspecialista2Hide').val());
			}, 500);
			$.unblockUI();
			
		}
	});
}

function modificarConductimetroDigitalDetalleEdit() {
	//alert(idPhMetro);
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'conductimetroDigitalDetalleEditGet/'+idConductimetro, 
		success: function(result) {
			$('#id-modal-content-detalle-edit').replaceWith(result);
			
			$('#intIdConductimetroEdit').val(idConductimetro);
			
			
			$('#strFecha').datetimepicker({
			      //  format: 'DD/MM/YYYY HH:mm:SS',
					format: 'DD/MM/YYYY',
			        ignoreReadonly: true,
			        date: new Date(),
			        maxDate : new Date(),
			        locale: "es"
			    });
									
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableValidationRulesDetalle();
		},complete: function() {
			$('#modal-registra-conductimetroDigital-detalle-edit').modal('show');		
			
			$.unblockUI();
			
		}
	});
}

function enableValidationRulesDetalle() {

	var validator = $("#form-edit-conductimetroDigitalDetalleEdit-id").bootstrapValidator({
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
			strCodEquipo: {
				validators : {
							
					notEmpty: {
                        message: 'Ingrese Código'
                    }		
					
				}
			},
			strResult1: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						
					 regexp:/^\d{1,6}(\.\d{1,3})?$/,
					 message: 'Valor m\u00E1ximo: 6 enteros y 3 decimales'
	 
					}
				}
			},
			strKCelda: {
				validators : {
							
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						
					 regexp:/^\d{1,6}(\.\d{1,3})?$/,
					 message: 'Valor m\u00E1ximo: 6 enteros y 3 decimales'
	 
					}
				}
			},
			strResult2: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						
					 regexp:/^\d{1,6}(\.\d{1,3})?$/,
					 message: 'Valor m\u00E1ximo: 6 enteros y 3 decimales'
	 
					}
				}
			}
			
			
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoConductimetroDigitalDetalleEditForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-conductimetroDigitalDetalleEdit-id').attr('action'),
			data: $('#form-edit-conductimetroDigitalDetalleEdit-id').serialize(),
			success: function(result){
				$nuevoConductimetroDigitalDetalleEditForm.bootstrapValidator('resetForm', true);
				$nuevoConductimetroDigitalDetalleEditForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});	
}

function inactivarConductimetroDigitalDetalle(id) {

	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar registro?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdDetalle').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-conductimetroDigitalDetalle').attr('action'),
			    	   data: $('#form-inactiva-conductimetroDigitalDetalle').serialize(),  
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

function showToastConductimetroDigital(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-conductimetroDigital').modal('hide');
		 $('#idBtnBuscarConductimetroDigital').click();
	 }if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-conductimetroDigital').modal('hide');
		 $('#idBtnBuscarConductimetroDigital').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 $('#modal-registra-conductimetroDigital').modal('hide');
		 $('#idBtnBuscarConductimetroDigital').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
		 //$('#modal-registra-conductimetroDigital').modal('hide');
		 //$('#idBtnBuscarConductimetroDigital').click();
	 }
	 if(mensajeTipo==='grabadoOkSubForm'){
		 toastr["success"](mensajeError, "Registro agregado correctamente");
		 $('#modal-registra-conductimetroDigital-detalle-edit').modal('hide');
		 $('#myTableConductimetroDigitalDetalle').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='inactivadoSubFormOk'){
		 toastr["success"](mensajeError, "Registro eliminado correctamente");
		 $('#myTableConductimetroDigitalDetalle').DataTable().ajax.reload();
	 }
}
	 
