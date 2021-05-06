$(document).ready(function() {
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarCorreo').click(function () {		 
		 $('#tipoOperacionHide').val($('#cbxTipoOperacion').val());
		 $('#estadoHide').val($('#cbxEstado').val());
		 $('#myTableConfCorreo').DataTable().ajax.reload();
	});
	
	//CARGA DE LOS TIPOS DE FUENTE EN LA BUSQUEDA
	populateTipoOperacion('cbxTipoOperacion',1);
});

$(document).on('focusout', '.tokenfield', function() { 
	var correos = $(this)[0].innerText;
	var input = $(this)[0].firstChild;
	var idInput = input.id;
	var divFormGroup = $(input).parents().eq(2);
	var idDivFormGroup = divFormGroup[0].id;

	var validToken = validaAndDecorateInputToken(idInput, idDivFormGroup, (idInput === "strPara" || idInput === "strParaGer"), true); // ..,..,required,disabledbutton
	if(validToken!=""){
		$("#guardarButton").attr('disabled', 'disabled');
	}
	else{
	  	$('#guardarButton').removeAttr("disabled");
	}
});
	
//FUNCION PARA CUANDO SE SELECCIONA EL CHECKBOX
$(document).on('change', '#chbxRutaDefault', function() { 
	var usarRutaDefault = $(this).prop("checked");
	$("input[name='lonRutaDefault']").val(usarRutaDefault ? "1" : "0");
	if(usarRutaDefault){
		$("input[name='strRutaServFile']").val(CONF_CORREO_EXTRACCION_RUTA);
		$("input[name='strUsuFile']").val(CONF_CORREO_EXTRACCION_USER);
		$("input[name='strPassFile']").val(CONF_CORREO_EXTRACCION_PASSWORD);
		$("input[name='strRutaServFile']").attr('readonly','readonly');
		$("input[name='strUsuFile']").attr('readonly','readonly');
		$("input[name='strPassFile']").attr('readonly','readonly');
	}
	else{
		$("input[name='strRutaServFile']").val("");
		$("input[name='strUsuFile']").val("");
		$("input[name='strPassFile']").val("");
		$("input[name='strRutaServFile']").removeAttr('readonly');
		$("input[name='strUsuFile']").removeAttr('readonly');
		$("input[name='strPassFile']").removeAttr('readonly');
	}
	$("#form-edit-mant-correo").bootstrapValidator('revalidateField', 'strRutaServFile');
	$("#form-edit-mant-correo").bootstrapValidator('revalidateField', 'strUsuFile');
	$("#form-edit-mant-correo").bootstrapValidator('revalidateField', 'strPassFile');
});

// FUNCION PARA CUANDO CAMBIA EL SELECT DEL POPUP
$(document).on('change', '#cbxTipoOperacionPopup', function() { 
	var tipo = $('#cbxTipoOperacionPopup').val();
	var idFormReportes = "form-correo-reportes";
	var idFormProceso = "form-correo-proceso";
	var idFormEnvios = "form-correo-envios";
	var idFormReporteGerencia = "form-correo-reportes-gerencia";
	if(OPERATION_TYPE_UNSELECTED == tipo){
		$("#"+idFormReportes).hide();
		$("#"+idFormProceso).hide();
		$("#"+idFormEnvios).hide();
		$("#"+idFormReporteGerencia).hide();
	}
	else if(OPERATION_TYPE_REPORTE_RESUMEN == tipo){
		$('[name="guardarRepButton"]').removeAttr("disabled");
		$('#strPara').tokenfield('setTokens', []);
		$('#strCc').tokenfield('setTokens', []);
		clearValidationDecorate("strPara", "div-para-group", true);
		clearValidationDecorate("strCc", "div-cc-group", true);
		$("#"+idFormReportes).show();
		$("#"+idFormProceso).hide();
		$("#"+idFormEnvios).hide();
		$("#"+idFormReporteGerencia).hide();
	}
	else if(OPERATION_TYPE_PROCESO_AUTOMATICO == tipo){
		$('[name="guardarExtButton"]').removeAttr("disabled");
		$("#"+idFormProceso).show();
		$("#"+idFormReportes).hide();
		$("#"+idFormEnvios).hide();
		$("#"+idFormReporteGerencia).hide();
	}
	else if(OPERATION_TYPE_CORREO_FUENTE_ENVIO == tipo){
		$('[name="guardarEnvButton"]').removeAttr("disabled");
		$("#"+idFormEnvios).show();
		$("#"+idFormReportes).hide();
		$("#"+idFormProceso).hide();
		$("#"+idFormReporteGerencia).hide();
	}
	else if(OPERATION_TYPE_REPORTE_GERENCIA == tipo){
		$('[name="guardarRepButton"]').removeAttr("disabled");
		$('#strParaGer').tokenfield('setTokens', []);
		$('#strCcGer').tokenfield('setTokens', []);
		clearValidationDecorate("strParaGer", "div-para-ger-group", true);
		clearValidationDecorate("strCcGer", "div-cc-ger-group", true);
		$("#"+idFormReporteGerencia).show();
		$("#"+idFormReportes).hide();
		$("#"+idFormProceso).hide();
		$("#"+idFormEnvios).hide();
	}
	$('form#form-edit-mant-correo.form-horizontal.bv-form').bootstrapValidator('resetForm', true);
	$('form#form-edit-mant-correo.form-horizontal.bv-form')[0].reset();	 
	$('#cbxTipoOperacionPopup').val(tipo); // SE PIERDE AL RESETAR
});

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRules() {

	var validator = $("#form-edit-mant-correo").bootstrapValidator({
		trigger: 'blur',
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strPara', '#strCc'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
            strCuerpo: {
				validators : {
					stringLength : {
						max : 350,
						message : "El cuerpo de mensaje ha de ser como m\u00e1ximo 350 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese cuerpo de mensaje'
                    }
				}
            },
            strCuerpoGer: {
				validators : {
					stringLength : {
						max : 350,
						message : "El cuerpo de mensaje ha de ser como m\u00e1ximo 350 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese cuerpo de mensaje'
                    }
				}
            },
			strAsuntoRep: {
				validators : {
					stringLength : {
						max : 80,
						message : "El asunto ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese asunto'
                    }
				}
            },
			strAsuntoExt: {
				validators : {
					stringLength : {
						max : 80,
						message : "El asunto ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese asunto'
                    }
				}
            },
            strAsuntoGer: {
				validators : {
					stringLength : {
						max : 80,
						message : "El asunto ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese asunto'
                    }
				}
            },
            strNomAdjunto: {
				validators : {
					stringLength : {
						max : 100,
						message : "El nombre adjunto ha de ser como m\u00e1ximo 100 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese el nombre adjunto'
                    }
				}
            },
            strOrigen: {
				validators : {
					stringLength : {
						max : 80,
						message : "El remitente ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese remitente'
                    },
                    regexp : {
						regexp : REGEX_MAIL_VALIDATION,
						message : "El remitente no es v\u00e1lido (example@dominio.com)"
					}
				}
            },
            strRutaServCorreo: {
				validators : {
					stringLength : {
						max : 80,
						message : "El servidor ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese servidor'
                    }
				}
            },
            lonPuerto: {
				validators : {
					stringLength : {
						max : 6,
						message : "El puerto ha de ser como m\u00e1ximo 6 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese puerto'
                    },
                    regexp : {
						regexp : /^[0-9]+$/,
						message : "El puerto debe ser num&eacute;rico"
					}
				}
            },
            strUsuCorreo: {
				validators : {
					stringLength : {
						max : 80,
						message : "El correo ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese el correo'
                    },
                    regexp : {
						regexp : /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i,
						message : "El correo no es v\u00e1lido (example@dominio.com)"
					}
				}
            },
            strPassCorreo: {
				validators : {
					stringLength : {
						max : 30,
						message : "La contrase&ntilde;a ha de ser como m\u00e1ximo 30 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese la constrase&ntilde;a'
                    }
				}
            },
            strRutaServFile: {
            	validators : {
					stringLength : {
						max : 80,
						message : "La ruta ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese la ruta'
                    }
				}
            },
            strUsuFile: {
            	validators : {
					stringLength : {
						max : 80,
						message : "El usuario ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese el usuario'
                    }
				}
            },
            strPassFile: {
            	validators : {
					stringLength : {
						max : 30,
						message : "La constrase&ntilde;a ha de ser como m\u00e1ximo 30 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese la contrase&ntilde;a'
                    }
				}
            },
            // PARA CORREO DE ENVIO
            strRutaServCorreoEnv: {
				validators : {
					stringLength : {
						max : 80,
						message : "El servidor ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese servidor'
                    }
				}
            },
            lonPuertoEnv: {
				validators : {
					stringLength : {
						max : 6,
						message : "El puerto ha de ser como m\u00e1ximo 6 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese puerto'
                    },
                    regexp : {
						regexp : /^[0-9]+$/,
						message : "El puerto debe ser num&eacute;rico"
					}
				}
            },
            strUsuCorreoEnv: {
				validators : {
					stringLength : {
						max : 80,
						message : "El correo ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese el correo'
                    },
                    regexp : {
						regexp : /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i,
						message : "El correo no es v\u00e1lido (example@dominio.com)"
					}
				}
            },
            strPassCorreoEnv: {
				validators : {
					stringLength : {
						max : 30,
						message : "La contrase&ntilde;a ha de ser como m\u00e1ximo 30 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese la constrase&ntilde;a'
                    }
				}
            }
		}
	})	
	.on('submit.form.bv', function(e, data){
		// VALIDAMOS LOS CAMPOS TOKENFIELD
		var validaCc = validaAndDecorateInputToken("strCc", "div-cc-group", false, false);
		if(validaCc != ""){
			$("#strCc-tokenfield").focus();
		}
		var validaPara = validaAndDecorateInputToken("strPara", "div-para-group", true, false);
		if(validaPara != ""){
			$("#strPara-tokenfield").focus();
		}
		
		// VALIDAMOS LOS CAMPOS TOKENFIELD PARA REPORTE DE GERENCIA
		var validaCcGer = validaAndDecorateInputToken("strCcGer", "div-cc-ger-group", false, false);
		if(validaCcGer != ""){
			$("#strCcGer-tokenfield").focus();
		}
		var validaParaGer = validaAndDecorateInputToken("strParaGer", "div-para-ger-group", true, false);
		if(validaParaGer != ""){
			$("#strParaGer-tokenfield").focus();
		}
	})
	.on('error.form.bv', function(e, data){
	})
	.on('success.form.bv', function(e, data) {
		
		// Habilitando boton GUARDAR
		$('#guardarButton').removeAttr("disabled");
		
		/**/
		// VALIDAMOS LOS CAMPOS TOKENFIELD
		var validaPara = validaAndDecorateInputToken("strPara", "div-para-group", true);
		var validaCc = validaAndDecorateInputToken("strCc", "div-cc-group", false);
		
		// VALIDAMOS LOS CAMPOS TOKENFIELD PARA REPORTE DE GERENCIA
		var validaParaGer = validaAndDecorateInputToken("strParaGer", "div-para-ger-group", true);
		var validaCcGer = validaAndDecorateInputToken("strCcGer", "div-cc-ger-group", false);
		
		// DEBE HACERSE SOLO SI ES EL TIPO REPORTE RESUMEN
		if(((validaPara != "")||(validaCc != "")) && $("#cbxTipoOperacionPopup").val() === OPERATION_TYPE_REPORTE_RESUMEN){
			$("#guardarButton").attr('disabled', 'disabled');
			return false;
		}
		else if(((validaParaGer != "")||(validaCcGer != "")) && $("#cbxTipoOperacionPopup").val() === OPERATION_TYPE_REPORTE_GERENCIA){
			$("#guardarButton").attr('disabled', 'disabled');
			return false;
		}
		else{
			$('#cbxTipoOperacionPopup').prop("disabled", false); // Hacemos que el campo sea editable para enviarlo en el request
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoMantCorreoForm = $(e.target);
			//MYAPPL.toUppercaseInputs();
			$.ajax({
		    	   type: 'POST',
		    	   url: $('#form-edit-mant-correo').attr('action'),
		    	   data: $('#form-edit-mant-correo').serialize(),  
		    	   success: function(result){
		    		   $nuevoMantCorreoForm.bootstrapValidator('resetForm', true);
		    		   $nuevoMantCorreoForm[0].reset();	    		   
		    	       $('.container_save').html(result);
		    	       $('#modal-registra-correo').modal('toggle'); // Cerramos el modal
		    	   },
					complete: function() {
						$("#guardarButton").html('Guardar');
						$('#guardarButton').removeAttr("disabled");
					}
		    });  
		}
		
	});	
}

function validateFieldToken(idInput, required) {
	var validData = "";
	var re = REGEX_MAIL_VALIDATION;
	var listValues = $('#'+idInput).tokenfield('getTokens');
	var countCharacters = 0;
	if(required && (listValues == null || listValues.length == 0)){
		validData = "Ingrese al menos un correo";
	}
	else{
		$.each( listValues, function( key, value ) {
			if(value.length != 0){
				var valid = re.test(value.value);
				countCharacters = countCharacters + value.value.length;
				if(!valid){
					validData = "Ingrese correos v&aacute;lidos (example@dominio.com)";
					return false;
				}
			}
		});
	}
	
	if(validData === "" && countCharacters > 180){
		validData = "La longitud total de correos debe ser 180 caracteres";
	}
	
	return validData;
}

function clearValidationDecorate(idInput, idDiv, disabledButton) {
	if(disabledButton){
		$('#guardarButton').removeAttr("disabled");
	}
	$("#"+idDiv).removeClass("has-feedback has-error");
	$("#"+idDiv).removeClass("has-feedback has-success");
	$("#small"+idInput).remove();
}

function decorateValidationFieldToken(idInput, idDiv, success, message, disabledButton) {
	var classCss = "";
	clearValidationDecorate(idInput, idDiv, disabledButton);
	if(!success){
		classCss = "has-feedback has-error";
		$("#"+idInput).parent().after( '<small id="small'+idInput+'" class="help-block" data-bv-result="INVALID" style="">'+message+'</small>' );
		$("#"+idDiv).addClass(classCss);
	}
	else{
		classCss = "has-feedback has-success";
		$("#"+idDiv).addClass(classCss);
	}
}

//FUNCIONAS PARA POBLAR
function populateTipoOperacion(idCombo,operationType){
	$.getJSON('listTiposOperacion', {
		ajax : 'true',
	}, function(data) {
		if(operationType == 1){ // BUSQUEDA
			var html = '<option value="">-TODOS-</option>';
		}
		else if(operationType == 2){ // CREAC)
			var html = '<option value="">-SELECCIONE-</option>';
		}
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option value="' + data[i].value + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$('#'+idCombo).html(html);
		$('#cbxTipoOperacionPopup').val($('#cbxTipoOperacionHide').val());
	});
}

//FUNCIONAS PARA POBLAR CREACION
function populateTipoOperacionCreacionValid(idCombo,mapItems){
	$.getJSON('listTiposOperacion', {
		ajax : 'true',
	}, function(data) {
		var html = '<option value="">-SELECCIONE-</option>';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			if(mapItems[data[i].value].show){
				html += '<option value="' + data[i].value + '">'
				+ data[i].label + '</option>';
			}
		}
		html += '</option>';
		$('#'+idCombo).html(html);
		$('#cbxTipoOperacionPopup').val($('#cbxTipoOperacionHide').val());
	});
}

//FUNCION PARA MOSTRAR EL FORMULARIO DE CREACION O EDICION
function modificarCorreo(id, tipo, estado) {
	$.ajax({
		   type: 'GET',
		   url: 'configuracionCorreo/getValidationInfo',
		   success: function(result) {
			   
			   if(id === 0){ // CREACION
				   if(result.bolExistReporteResumen&&result.bolExistProcesoAutomatico&&result.bolExistEnvios&&result.bolExistReporteGerencia){
					   $.confirm({
						    title: 'Mensaje de Validaci&oacute;n',
						    content: 'Ya existen correos activos para todos los tipos. Primero inactive al menos uno de ellos',
						    buttons: {
						    	Aceptar: { 
						        	btnClass: 'btn-blue',
							    }
							}
						});	 
				   }
				   else{
					   mostrarModalEdicion(id,tipo,result,null);
				   }
			   }
			   else{ // MODIFICACION
				   mostrarModalEdicion(id,tipo,result,estado);
			   }
			   
		   }
	});
	
}

function mostrarModalEdicion(id,tipo,validacion,estadoUpd) {
	MYAPPL.blockPageLoad();
	$.ajax({
		   type: 'GET',
		   url: 'configuracionCorreo/get/'+id, 
		   success: function(result) {
			   $('#form-edit-mant-correo').replaceWith(result);
			   enableValidationRules();
		   },
			complete: function() {
				$('#modal-registra-correo').modal('show');
				// CHECKBOX
				$("input[name='lonRutaDefault']").val($("#chbxRutaDefault").prop("checked") ? '1' : '0');
				if(id==0){ // CREACION
					var map = {};
					var validateReporteResumen = new ValidationCorreo(OPERATION_TYPE_REPORTE_RESUMEN, !validacion.bolExistReporteResumen);
					var validateProcesoAutomatico = new ValidationCorreo(OPERATION_TYPE_PROCESO_AUTOMATICO, !validacion.bolExistProcesoAutomatico);
					var validateReporteEnvio = new ValidationCorreo(OPERATION_TYPE_CORREO_FUENTE_ENVIO, !validacion.bolExistEnvios);
					var validateReporteGerencia = new ValidationCorreo(OPERATION_TYPE_REPORTE_GERENCIA, !validacion.bolExistReporteGerencia);
					map[validateReporteResumen.value] = validateReporteResumen;
					map[validateProcesoAutomatico.value] = validateProcesoAutomatico;
					map[validateReporteEnvio.value] = validateReporteEnvio;
					map[validateReporteGerencia.value] = validateReporteGerencia;
					populateTipoOperacionCreacionValid('cbxTipoOperacionPopup', map);
					$("#cbxTipoOperacionPopup").val($("#cbxTipoOperacionPopup option:first").val());
					$("#form-correo-reportes").hide();
					$("#form-correo-proceso").hide();
					$("#form-correo-envios").hide();
					$("#form-correo-reportes-gerencia").hide();
				}else{ // EDICION
					populateTipoOperacion('cbxTipoOperacionPopup',2);
					if(estadoUpd === 2){ // SI ES QUE ESTA INACTIVO
						if(tipo === 1){ // REPORTE
							if(validacion.bolExistReporteResumen){
								// BORRAR ESTADO
								$("#sectEstadoRep").html("");
								$("#sectEstadoExt").html("");
								$("#sectEstadoEnv").html("");
								$("#sectEstadoGerencia").html("");
							}
						}
						else if(tipo === 2){ // PROCESO
							if(validacion.bolExistProcesoAutomatico){
								// BORRAR ESTADO
								$("#sectEstadoExt").html("");
								$("#sectEstadoRep").html("");
								$("#sectEstadoEnv").html("");
								$("#sectEstadoGerencia").html("");
							}
						}
						else if(tipo === 3){ // ENVIOS
							if(validacion.bolExistEnvios){
								// BORRAR ESTADO
								$("#sectEstadoEnv").html("");
								$("#sectEstadoExt").html("");
								$("#sectEstadoRep").html("");
								$("#sectEstadoGerencia").html("");
							}
						}
						else if(tipo === 4){ // REPORTE GERENCIA
							if(validacion.bolExistReporteGerencia){
								// BORRAR ESTADO
								$("#sectEstadoEnv").html("");
								$("#sectEstadoExt").html("");
								$("#sectEstadoRep").html("");
								$("#sectEstadoGerencia").html("");
							}
						}
					}
					
					var idFormReportes = "form-correo-reportes";
					var idFormProceso = "form-correo-proceso";
					var idFormEnvios = "form-correo-envios";
					var idFormReporteGerencia = "form-correo-reportes-gerencia";
					if(tipo === 1){
						$("#"+idFormReportes).show();
						$("#"+idFormProceso).html(""); // PARA EVITAR QUE EXISTAN LOS CAMPOS, SE REPLICA CUANDO USAS FRAGMENTS
						$("#"+idFormProceso).hide();
						$("#"+idFormEnvios).html(""); 
						$("#"+idFormEnvios).hide();
						$("#"+idFormReporteGerencia).html(""); 
						$("#"+idFormReporteGerencia).hide();
					}
					else if(tipo === 2){
						$("#"+idFormProceso).show();
						$("#"+idFormReportes).html("");
						$("#"+idFormReportes).hide();
						$("#"+idFormEnvios).html(""); 
						$("#"+idFormEnvios).hide();
						$("#"+idFormReporteGerencia).html(""); 
						$("#"+idFormReporteGerencia).hide();
					}
					else if(tipo === 3){
						$("#"+idFormEnvios).show();
						$("#"+idFormProceso).html("");
						$("#"+idFormProceso).hide();
						$("#"+idFormReportes).html("");
						$("#"+idFormReportes).hide();
						$("#"+idFormReporteGerencia).html(""); 
						$("#"+idFormReporteGerencia).hide();
					}
					else if(tipo === 4){
						$("#"+idFormReporteGerencia).show();
						$("#"+idFormReportes).html("");
						$("#"+idFormReportes).hide();
						$("#"+idFormProceso).html(""); 
						$("#"+idFormProceso).hide();
						$("#"+idFormEnvios).html(""); 
						$("#"+idFormEnvios).hide();
					}
					$('#cbxTipoOperacionPopup').prop('disabled', true); // No es modificable en edicion
				}
				$.unblockUI();
				MYAPPL.removeInputEnter();
			}
	    });
}

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarCorreo(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '¿Confirma inactivar correo?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdMantCorreo').val(id);
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-correo').attr('action'),
			    	   data: $('#form-inactiva-correo').serialize(),  
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