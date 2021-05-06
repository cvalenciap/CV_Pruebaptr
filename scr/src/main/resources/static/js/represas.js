/**
 * Hoja de scripts manejados en el mantenimiento de represas
 */
$(document).ready(function() {
	
	// Evento para la importacion
	$("#upload-carga-input").on("change", uploadFile);
	
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

	$('#idBtnFormBuscarRepresas').click(function () {
		$('#myRepresas').DataTable().ajax.reload();
	});
	
	// BLOQUEAMOS PEGAR EN FORMULARIO DE BUSQUEDA Y DE CREACION
	$("#cpNomRep").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#cpNumPre").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	// AHORA CREACION
	$("#inputFechaReg").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strLluvia']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strCota']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strVolumen']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strVolumenTotal']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strCaudalTrasvasado']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strDiferenciaNivel']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strDiferenciaVolumen']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strEvaporacion']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='intHumedadRelativa']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strTemperaturaMaxima']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strTemperaturaMinima']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strHy']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strCaudal']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	////////////////////////
	$('#inputFechaReg').on('dp.change', function(e) {	
		obtenerAforoRioRimac($(this).val(), 'strAforoRio');
	});
	
	// AUTOCOMPLETADOS:
	$("#cpNomRep").autocomplete({
	    source: cargarListaRepresasAutocomplete(lstRepresasTodoCombo),
	    minLength: 1
	});
	
});

function cargarListaRepresasAutocomplete(listaCombo) {
	var listaAcutoComplete = [];
	$.each( listaCombo, function( key, value ) {
		listaAcutoComplete.push(value.label);
	});
	return listaAcutoComplete;
}

// EVENTO CUANDO SE PRESIONA ALGUNA TECLA SOBRE CAUDAL TRASVASADO (antes era con keyup)
//$('#strCaudalTrasvasado').on('input propertychange paste', function() {
//	var valToCopy = ($.trim($(this).val()).length === 0 ? "0" : $.trim($(this).val()));
//	calcularVolumenTrasvasado(valToCopy, 'strVolumenTrasvasado');
//	// QUE REVALIDE
//	$("#form-edit-represas").bootstrapValidator('revalidateField', 'strVolumenTrasvasado');
//});

$(document).on('input propertychange paste', '#strCaudalTrasvasado', function() { 
	var valToCopy = ($.trim($(this).val()).length === 0 ? "0" : $.trim($(this).val()));
	calcularVolumenTrasvasado(valToCopy, 'strVolumenTrasvasado');
	// QUE REVALIDE
	$("#form-edit-represas").bootstrapValidator('revalidateField', 'strVolumenTrasvasado');
});

$(document).on('change', '#allowChangeAforo', function() { 
	if($(this).prop("checked")){
		$("input[name='strAforoRio']").removeAttr("readonly");
	}
	else{
		obtenerAforoRioRimac($("#inputFechaReg").val(), 'strAforoRio');
		$("input[name='strAforoRio']").attr("readonly","readonly");
	}
});

// EVENTO DE CAMBIO SOBRE EL COMBO DE REPRESAS
$(document).on('change', '#cbxRepresas', function() { 
	$("#form-edit-represas").bootstrapValidator('revalidateField', 'strFechaRegistro');
});

//EVENTO DE CERRADO DE MODAL DE MANIOBRAS
$("#modal-maniobras").on("hidden.bs.modal", function () {
	// DESTRUIMOS DATATABLE PARA PODER CREARLO POR CADA REGISTRO DE REPRESAS
	$('#form-edit-maniobras').bootstrapValidator('resetForm', true);
	$("#myManiobras").DataTable().destroy();
});

//EVENTO DE CERRADO DE MODAL DE EDICION REPRESAS
$("#modal-registra-represas").on("hidden.bs.modal", function () {
	$("#codigo").val("");
	// QUITAMOS ESTILO DE DATETIMEPICKER
	$("#inputFechaReg").removeAttr("disabled");
	// LIMPIAMOS EL SELECT DE REPRESAS
	$("#cbxRepresas").html("");
	// BORRAMOS EL OBJETO PARA QUE LIMPIE PORQUE "CREAR" SOLO ABRE MODAL
	// LIMPIAMOS EL SELECT DE COLABORADORES
	$("#cbxColaboradores").html("");	
	// LIMPIAMOS EL SELECT DE REPRESAS
	$("#cbxClimas").html("");	
	modelAttributeValue = null;
});

//EVENTO DE APERTURA DE MODAL DE EDICION DE REPRESAS
$('#modal-registra-represas').on('shown.bs.modal', function() {
	crearDatosModalRepresas();
})

//EVENTO DE CLICK EN EL BOTON DE AGREGAR REPRESAS
$('#idBtnCrearRepresas').on('click', function(e) {
	$('#inputFechaReg').datetimepicker({
		format 	: 'DD/MM/YYYY',		
		ignoreReadonly: true,
		maxDate : new Date(),
		locale 	: "es"
	});
})

function obtenerComboCompletoRepresas(listaActivos, codigo) {
	var newDatos = listaActivos.slice();
	var bolDatoEncontrado = false;
	$.each( newDatos, function( key, value ) {
		if(value.value === codigo){
			bolDatoEncontrado = true;
		}
	});
	if(!bolDatoEncontrado){
		var dato = null;
		$.each( lstRepresasTodoCombo, function( key, value ) {
			if(value.value === codigo){
				dato = value;
			}
		});
		newDatos.push(dato);
	}
	return newDatos;
}

function obtenerComboCompletoColaboradores(listaActivos, codigo) {
	var newDatos = listaActivos.slice();
	var bolDatoEncontrado = false;
	$.each( newDatos, function( key, value ) {
		if(value.value === codigo){
			bolDatoEncontrado = true;
		}
	});
	if(!bolDatoEncontrado){
		var dato = null;
		$.each( lstColaboradoresTodoCombo, function( key, value ) {
			if(value.value === codigo){
				dato = value;
			}
		});
		newDatos.push(dato);
	}
	return newDatos;
}

function obtenerComboCompletoClimas(listaActivos, codigo) {
	var newDatos = listaActivos.slice();
	var bolDatoEncontrado = false;
	$.each( newDatos, function( key, value ) {
		if(value.value === codigo){
			bolDatoEncontrado = true;
		}
	});
	if(!bolDatoEncontrado){
		var dato = null;
		$.each( lstClimasTodoCombo, function( key, value ) {
			if(value.value === codigo){
				dato = value;
			}
		});
		newDatos.push(dato);
	}
	return newDatos;
}

function crearDatosModalRepresas() {	
	if (modelAttributeValue != null && modelAttributeValue.intCodigo != null) {
		$("#inputFechaReg").attr("disabled","disabled");		 
		
		var datosCompletosRepresas = obtenerComboCompletoRepresas(lstRepresasActivasCombo, modelAttributeValue.strCodigoRepresa);
		var datosCompletosColaboradores = obtenerComboCompletoColaboradores(lstColaboradoresActivosCombo, modelAttributeValue.strCodigoColaborador);
		var datosCompletosClimas = obtenerComboCompletoClimas(lstClimasActivosCombo, modelAttributeValue.strCodigoClima);
		
		populateRepresasColaboradoresClimas("cbxRepresas", datosCompletosRepresas, modelAttributeValue.strCodigoRepresa);
		populateRepresasColaboradoresClimas("cbxColaboradores", datosCompletosColaboradores, modelAttributeValue.strCodigoColaborador);
		populateRepresasColaboradoresClimas("cbxClimas", datosCompletosClimas, modelAttributeValue.strCodigoClima);
	} else {
		$("#inputFechaReg").val(fechaDeHoy(new Date()));				
		
		populateRepresasColaboradoresClimas("cbxRepresas", lstRepresasActivasCombo, null);
		populateRepresasColaboradoresClimas("cbxColaboradores", lstColaboradoresActivosCombo, null);
		populateRepresasColaboradoresClimas("cbxClimas", lstClimasActivosCombo, null);
	}
}

function fechaDeHoy(d) {
	let month 	= String(d.getMonth() + 1);
	let day 	= String(d.getDate());
	const year 	= String(d.getFullYear());

	if (month.length < 2) month = '0' + month;
	if (day.length < 2) day = '0' + day;

	return day+"/"+month+"/"+year;
}

// FUNCION PARA POBLAR COMBOS
function populateRepresasColaboradoresClimas(idCombo,data,selectedValue){
	var html = '<option value="">-SELECCIONE-</option>';
	var len = data.length;
	for ( var i = 0; i < len; i++) {
		html += '<option value="' + data[i].value + '" '+(data[i].value === selectedValue ? 'selected="selected"' : '')+'>'
				+ data[i].label + '</option>';
	}
	html += '</option>';
	$('#'+idCombo).html(html);
}

function saveDataImport() {
	MYAPPL.blockPageLoad();
	$("#importButton").html('Importando...');
	$("#importButton").attr('disabled', 'disabled');
	$.ajax({
 	   type: 'POST',
 	   url: $('#form-save-import').attr('action'),
 	   dataType: "json",
 	   success: function(result){  	
 		   console.log(result);
 		  toastr.success('Represas importadas correctamente');
	    },
	    error: function( jqXHR, textStatus, errorThrown ) {
	    	toastr.error(jqXHR.responseJSON.errorMessage);
	    },
		complete: function() {
			$('#idBtnFormBuscarRepresas').click();
			$('#modal-importar-represas').modal('toggle');
			$("#importButton").html('Guardar');
			$('#importButton').removeAttr("disabled");
			$.unblockUI();
		}
	}); 
}

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
	if($.trim($("#upload-carga-input").val())!=""){
		MYAPPL.blockPageLoad();
		  $.ajax({
		    url: $("#form-upload-file").attr('action'),
		    type: "POST",
		    data: new FormData($("#upload-file-form")[0]),
		    enctype: 'multipart/form-data',
		    processData: false,
		    contentType: false,
		    cache: false,
		    dataType: 'json',
		    complete: function (data) {
		    	if(data.status == HTTP_STATUS_OK){
		    		var list = $.parseJSON(data.responseText);
		    		if(list.length == 0){
		    			$("#valid-section").show();
		    		}
		    		else{
		    			$("#invalid-section").show();
		    			drawBodyTable(list,"body-table-errors");
		    		}
		    		$('#modal-importar-represas').modal('show'); 
		    	}
		    	else if(data.status == HTTP_STATUS_INTERNAL_SERVER_ERROR){
		    		$.confirm({
		    		    title: 'Error en la Carga',
		    		    content: data.responseText,
		    		    buttons: {
		    		    	Aceptar: {}
		    			}
		    		});	  
		    	}
		    	else{ // ERROR NO CONTROLADO PARA ARCHIVOS MUUY GRANDES
		    		$.confirm({
		    		    title: 'Error en la Carga',
		    		    content: 'El archivo ingresado supera el tama&ntilde;o permitido (1MB)',
		    		    buttons: {
		    		    	Aceptar: {}
		    			}
		    		});	  
		    	}
		    	$.unblockUI();
		    	clearInputTypeFile("upload-carga-input"); 
		    	$("span.fileinput-filename").html("No hay archivo"); // reseteamos el mensaje
		    }
		  });
	}
}

$("#modal-importar-represas").on("hidden.bs.modal", function () {
	$("#valid-section").hide();
	$("#invalid-section").hide();
});

function drawBodyTable(lst,body){ 
	var html = "";
	$.each( lst, function( key, value ) {
		var row = value;
		html += "<tr>";
		html += "<th class='center-align' scope='row'>"+(key+1)+"</th>";
		$.each( row, function( key, value ) {
			html += "<td class='center-align'>"+value+"</td>";
		});
		html += "</tr>";
	});
	$("#"+body).html(html);
}

function printInformationLoad(result, message, icon) { //success|danger, EXITOO, ok|remove
	var html = '<div class="alert-'+result+' center-align " style="height: 34px;" role="alert">';
	html += '<span class="glyphicon glyphicon-'+icon+'"></span> <font size="3">'+message+'</font>';
	html += '</div>';
	return html;
}

function showToastRepresas(mensajeTipo, mensajeError, codigoRepresas, fechaRepresas, numPrecipitacion) {
	// Mensajes despues de GRABAR
	if(mensajeTipo==='grabadoOk'){		
		if(codigoRepresas!=''){			
			$("#strCodigoRepresas").val(codigoRepresas);			
        	$("#intTipoManiobra").val(3);
			
			toastr["success"](mensajeError, "Registro guardado correctamente con el N&uacute;mero de Precipitaci&oacute;n " + numPrecipitacion);
			$('#modal-registra-represas').modal('hide');
			$('#idBtnFormBuscarRepresas').click();		
			
			// ABRIR MODAL
			buscarManiobras(codigoRepresas, fechaRepresas);
		}
	}
	if(mensajeTipo==='grabadoNoOk'){
		$('#modal-registra-represas').modal('hide');
		toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	}
	if(mensajeTipo==='actualizadoOk'){
		toastr["success"](mensajeError, "Registro actualizado correctamente");
		$('#modal-registra-represas').modal('hide');
		$('#idBtnFormBuscarRepresas').click();
	}
	if(mensajeTipo==='actualizadoNoOk'){
		$('#modal-registra-represas').modal('hide');
		toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue actualizado");
	}
	// Mensajes despues de ELIMINAR
	if(mensajeTipo==='inactivadoOk'){
		toastr["success"](mensajeError, "Registro inactivado correctamente");
		$('#modal-registra-represas').modal('hide');
		$('#idBtnFormBuscarRepresas').click();
	}
	if(mensajeTipo==='inactivadoNoOk'){
		toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	}
}

function enableClientSideValidationRepresas() {
	
	$('#inputFechaReg').on('dp.change dp.show', function(e) {	
		$("#form-edit-represas").bootstrapValidator('revalidateField', 'strFechaRegistro');
	});
	
	var validator = $("#form-edit-represas")
	.bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled']
		,
		fields : {
			strVolumenTotal :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el volumen total'
			        },
                    regexp : {
						regexp : /^\d{1,9}(\.\d{1,6})?$/,
						message : "S\u00F3lo se permite hasta 9 enteros y 6 decimales"
					}
				}
			},
			strFechaRegistro :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la fecha de registro'
			        },
                    regexp : {
						regexp : /^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[/\\/](19|20)\d{2}$/,
						message : "La fecha de registro debe ser DD/MM/YYYY"
					},
					callback: {
                        message: 'Ya existe un registro para la fecha y represa ingresados. Elim\u00EDnelo o ed\u00EDtelo',
                        callback: function(value, validator, $field) {
                        	var fechaRegistro = $('input[name="strFechaRegistro"]').val();
                        	var codRepresa = $('select[name="strCodigoRepresa"]').val();     
                        	var retorno = false;
                        	$.ajax({
								type: "get",
								async: false,
								url: 'represas/getCantActivasFecha?fechaRegistro='+fechaRegistro+"&codRepresa="+codRepresa,
								success: function(response) {
									if (response === '0') {
										retorno = true;
									}
								}
							});
                        	
                        	return retorno;
                        }
                    }
				}
			},			
			strCodigoRepresa :{
				validators : {					
					notEmpty: {
			            message: 'Seleccione la represa'
			        }
				}
			},
			strCodigoColaborador :{
				validators : {					
					notEmpty: {
			            message: 'Seleccione el colaborador'
			        }
				}
			},
			strCodigoClima :{
				validators : {					
					notEmpty: {
			            message: 'Seleccione el clima'
			        }
				}
			},
			strCota :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la cota'
			        },
			        regexp : {
						regexp : /^\d{1,4}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 4 enteros y 4 decimales"
					}
				}
			},
			strVolumen :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el volumen'
			        },
                    regexp : {
						regexp : /^\d{1,9}(\.\d{1,6})?$/,
						message : "S\u00F3lo se permite hasta 9 enteros y 6 decimales"
					}
				}
			},
			strVolumenTrasvasado :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el volumen trasvasado'
			        },
                    regexp : {
						regexp : /^\d{1,10}(\.\d{1,8})?$/,
						message : "S\u00F3lo se permite hasta 10 enteros y 8 decimales"
					}
				}
			},						
			strTemperaturaMaxima :{
				validators : {					
					notEmpty: {
						message: 'Ingrese la temperatura m\u00e1xima'
			        },
			        regexp : {
						regexp : /^-?\d{1,2}(\.\d{1,2})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 2 decimales"
					},
					callback: {
                        message: 'La temperatura m\u00e1xima debe ser mayor que la temperartura m\u00EDnima',
                        callback: function(value, validator, $field) {
                        	var minima = $('#strTemperaturaMinima').val();
                        	var maxima = $('#strTemperaturaMaxima').val();                            
                            if (parseFloat(minima) > parseFloat(maxima)) {
                            	return {
                                    valid: false,
                                    message: 'La temperatura m\u00e1xima debe ser mayor que la temperartura m\u00EDnima'
                                }
                            } else {
                            	validator.updateStatus('strTemperaturaMinima', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                        }
                    }
				}
			},
			strTemperaturaMinima :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la temperatura m\u00EDnima'
			        },
			        regexp : {
						regexp : /^-?\d{1,2}(\.\d{1,2})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 2 decimales"
					},
					callback: {
                        message: 'La temperatura m\u00EDnima debe ser menor que la temperartura m\u00e1xima',
                        callback: function(value, validator, $field) {
                        	var minima = $('#strTemperaturaMinima').val();
                        	var maxima = $('#strTemperaturaMaxima').val();
                            if (parseFloat(minima) > parseFloat(maxima)) {
                            	return {
                                    valid: false,
                                    message: 'La temperatura m\u00EDnima debe ser menor que la temperartura m\u00e1xima'
                                }
                            } else {
                            	validator.updateStatus('strTemperaturaMaxima', validator.STATUS_VALID, 'callback');
                                return true;
                            }
                        }
                    }
				}
			},
			strDescarga :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la descarga'
			        },
                    regexp : {
						regexp : /^\d{1,2}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 4 decimales"
					}
				}
			},
			strCaudalTrasvasado :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el caudal trasvasado'
			        },
                    regexp : {
                    	regexp : /^\d{1,2}(\.\d{1,8})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 8 decimales"
					}
				}
			},
			strDiferenciaNivel :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la diferencia nivel'
			        },
                    regexp : {
						regexp : /^-?\d{1,8}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 8 enteros y 4 decimales"
					}
				}
			},
			strDiferenciaVolumen :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la diferencia volumen'
			        },
                    regexp : {
                    	regexp : /^-?\d{1,8}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 8 enteros y 4 decimales"
					}
				}
			},
			strLluvia :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese las lluvias'
			        },
                    regexp : {
						regexp : /^\d{1,2}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 4 decimales"
					}
				}
			},
			strEvaporacion :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la evaporaci\u00F3n'
			        },
                    regexp : {
                    	regexp : /^\d{1,2}(\.\d{1,4})?$/,
						message : "S\u00F3lo se permite hasta 2 enteros y 4 decimales"
					}
				}
			},
			intHumedadRelativa :{
				validators : {
					notEmpty: {
						message: 'Ingrese la humedad relativa'
			        },
                    regexp : {
                    	regexp : /^[0-9]+$/,
                    	message : "S\u00F3lo se permite valores enteros"
					}
				}
			},
			strAforoRio :{
				validators : {					
					notEmpty: {
						message: 'Ingrese el aforo r\u00EDo'
			        },
                    regexp : {
						regexp : /^\d{1,12}(\.\d{1,12})?$/,
						message : "S\u00F3lo se permite hasta 12 enteros y 12 decimales"
					}
				}
			},
			strHy :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el canal HY'
			        },
                    regexp : {
                    	regexp : /^\d{1,2}(\.\d{1,4})?$/,
                    	message : "S\u00F3lo se permite hasta 2 enteros y 4 decimales"
					}
				}
			},
			strCaudal :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el canal caudal'
			        },
                    regexp : {
                    	regexp : /^\d{1,2}(\.\d{1,4})?$/,
                    	message : "S\u00F3lo se permite hasta 2 enteros y 4 decimales"
					}
				}
			}			
		}
	})
	.on('submit.form.bv', function(e, data){
	})
	.on('error.form.bv', function(e, data){
	})
	.on('success.form.bv', function(e, data) {		
    	$("#fechaRepresas").val($("#inputFechaReg").val());
		
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoRepresasForm = $(e.target);
		MYAPPL.toUppercaseInputs();
		$.ajax({
			type: 'POST',
			url: $('#form-edit-represas').attr('action'),
			data: $('#form-edit-represas').serialize(),
			success: function(result){
				$nuevoRepresasForm.bootstrapValidator('resetForm', true);
				$nuevoRepresasForm[0].reset();
				$('.container_save').html(result);
			},
			error: function(){
				$nuevoRepresasForm.bootstrapValidator('resetForm', true);
				$nuevoRepresasForm[0].reset();
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});
	
}

enableClientSideValidationRepresas();

function modificarRepresas(id) {	
	$.ajax({
		type: 'GET',
		url: 'represasGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableClientSideValidationRepresas();
		},complete: function() {
			$('#codigo').val(id);
			$('#modal-registra-represas').modal('show');
			$.unblockUI();			
		}
	});
}

function clearSearchRepresas() {
	$('#cpNomRep').val("");
	$('#cpNumPre').val("");
	$('#cpEstado').val("1");
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchRepresas();
});

function clearFormEditRepresas() {
	$('#cpNomRep').val("");
	$('#cpNumPre').val("");
}

$(document).on("click", ".btn-create-represas", function(e) {
	$("#form-edit-represas")[0].reset();
	$("#form-edit-represas").bootstrapValidator('resetForm', true);
	$('#idModalTitle').html($('#idValCrearModalTitle').html());
	clearFormEditRepresas();
	/// CAMPOS AUTOCALCULADOS INICIO
	var date = new Date();
	obtenerAforoRioRimac(("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear(), 'strAforoRio');
	$('#inputFechaReg').on('dp.change', function(e) {	
		obtenerAforoRioRimac($(this).val(), 'strAforoRio');
	});
	//
	$("input[name='strDescarga']").val("0"); // Por defecto 0 al crear
	//CAMPOS AUTOCALCULADOS FIN
	$("#idEstado").css("display", "none");
	
	// VALORES EN CERO
	
	$("input[name='strLluvia']").val("0");
	$("input[name='strVolumenTotal']").val("0");
	$("input[name='strCaudalTrasvasado']").val("0");
	$("input[name='strVolumenTrasvasado']").val("0");
	$("input[name='strDiferenciaVolumen']").val("0");
	$("input[name='strEvaporacion']").val("0");
	$("input[name='intHumedadRelativa']").val("0");
	$("input[name='strTemperaturaMaxima']").val("0");
	$("input[name='strTemperaturaMinima']").val("0");
	$("input[name='strHy']").val("0");
	$("input[name='strCaudal']").val("0");
	
	$('#modal-registra-represas').modal('show');
	$.unblockUI();
});

function inactivarRepresas(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma inactivar la Represa?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdRepresas').val(id);
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-represas').attr('action'),
			    	   data: $('#form-inactiva-represas').serialize(),  
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

function obtenerAforoRioRimac(fechaRegistro, inputName) {
	$.getJSON('represas/getAforo?fechaRegistro='+fechaRegistro, {
		ajax : 'true',
	}, function(data) {
		$("input[name='"+inputName+"']").val(data);
		$("#form-edit-represas").bootstrapValidator('revalidateField', inputName);
	});
}

function calcularVolumenTrasvasado(caudalTrasvasado, inputName) {
	$.ajax({
		type: "get",
		async: false,
		url: 'represas/getFormulaTrasv',
		success: function(response) {
			var valVolumenTrasvasado;
			
			try {
				valVolumenTrasvasado = math.eval(caudalTrasvasado+""+response);
				valVolumenTrasvasado = Math.round(valVolumenTrasvasado * 100000000) / 100000000;
        	}
        	catch(err) {
        		valVolumenTrasvasado = "0";
        	}
			
			$("input[name='"+inputName+"']").val(valVolumenTrasvasado);
		},
		error: function( jqXHR, textStatus, errorThrown) {
		},
		complete: function() {
		}
	});
}