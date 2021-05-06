/**
 * Hoja de scripts manejados en el mantenimiento de maniobras
 */
$(document).ready(function() {
	
	enableClientSideValidationFactorDescarga();
	
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
	
	$('#flgClean').val(0);	

	// PARA MOSTRAR Y OCULTAR FACTOR
	$('#factor-descarga-check').change(function() {
		habilitarInputFactor($(this).is(":checked"));
    });
	
	// BLOQUEAMOS CTRL + V
	$("#factorDescargaId").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='strHora']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='dblAperturaInicial']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	$("input[name='dblAperturaFinal']").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
	// FIN BLOQUEAMOS CTRL + V
	
	// INICIALIZAMOS TIMEPICKER
	$('input[name="strHora"]').datetimepicker({
	    format: 'HH:mm',
	    ignoreReadonly: true,
	    locale: "es",
	    stepping: 30
	});
//	.on('dp.show', function () {
//	    $('a.btn[data-action="incrementMinutes"], a.btn[data-action="decrementMinutes"]').removeAttr('data-action').attr('disabled', true);
//	    $('span.timepicker-minute[data-action="showMinutes"]').removeAttr('data-action').attr('disabled', true).text('00');
//	}).on('dp.change', function () {
//	    $(this).val($(this).val().split(':')[0]+':00')
//	    $('span.timepicker-minute').text('00')
//	});
});

function habilitarInputFactor(isChecked) {
	if(isChecked) {
    	$("#factor-descarga").val("1");
    	$("#factorDescargaId").removeAttr("readonly");
    }
    else{
    	$("#factor-descarga").val("0");
    	$("#factorDescargaId").attr("readonly","readonly");
    }  
}

function buscarManiobras(id,fecha) {
	MYAPPL.blockPageLoad();
	$('#myManiobras').DataTable({
		"bProcessing": true,
		"serverSide": true,
		"searching": false,
		"lengthChange":   false,
		"scrollX": true,
		"bJQueryUI": true,
		"ajax":{
			url :"maniobras/pagination/"+id+"?fechaRepresas="+fecha,
            type: "post",
            complete: function(data) {
            	var idRepresas = data.responseJSON.represasId+"";
            	$("#strCodigoRepresas").val(idRepresas);
            	$("#fechaRepresas").val(data.responseJSON.represasFecha.substring(0,2)+"/"+data.responseJSON.represasFecha.substring(3,5)+"/"+data.responseJSON.represasFecha.substring(6,10));
            	$("#intTipoManiobra").val(3);
            	
            	// LOGICA PARA FACTOR DE DESCARGA
            	$("#form-edit-factor").bootstrapValidator('resetForm', true); // QUE LIMPIE Y DESPUES PONEMOS EL VALOR
            	$("#represasId").val(idRepresas); // EL HIDDEN PARA EL FORMULARIO DE FACTOR
            	var represasBean = data.responseJSON.represasBean;
            	$('#factor-descarga-check').prop('checked', (represasBean.intUsaFactorDescarga === 1));
            	habilitarInputFactor((represasBean.intUsaFactorDescarga === 1));
            	$("#factorDescargaId").val($.trim(represasBean.strFactorDescarga));
            	// FIN DE LOGICA PARA FACTOR
            	// LOGICA PARA VALIDAR SI SE DEBE SETEAR UNA APERTURA INICIAL EN BASE A LA ULTIMA MANIOBRA ACTIVA
            	validarDatosDeAperturaConUltimaManiobra(idRepresas, true); // AQUI APERTURAMOS EL MODAL (segundo parametro)
            	// FIN LOGICA PARA VALIDAR SI SE DEBE SETEAR UNA APERTURA INICIAL EN BASE A LA ULTIMA MANIOBRA ACTIVA
			},
            error: function(){
              $("#myManiobras_processing").css("display","none");
            },
            "data": function ( d ) {
            }
		},
		"order": [ 7, 'asc' ],
		"columnDefs": [ 
			{ targets: '_all', visible: true },	
			{"width": "180px",
        		className: "dt-body-center",
                "targets": [2]                
            },
            {"width": "60px",
        		className: "dt-body-center",
                "targets": [1, 3, 7]                
            },
            {"width": "100px",
        		className: "dt-body-center",
                "targets": [4, 5]                
            },
            {"width": "120px",
        		className: "dt-body-center",
                "targets": [6, 8, 9, 10, 11]                
            },
          	{"width": "80px",
          		className: "dt-body-center",
          		"orderable": false,
          		"targets": 0,
          		visible: true,
          		"data": 1,
          		"render": function (data, type, row) {
          			return '<table><tr>' + 
          				((row[1]==='INACTIVO')?'<td style="border:none; width:46px;"></td>':'<td style="border:none; width:46px;"><button class="btn btn-info" title="Editar" onclick="modificarManiobras('+row[12]+',\''+row[7]+'\',\''+row[3]+'\','+row[4]+','+row[5]+')"><span class="glyphicon glyphicon-pencil"></span></button></td>' +
          					'<td style="border:none; width:46px;"><button class="btn btn-danger btn-delete-manio" title="Inactivar" onclick="inactivarManiobras('+row[12]+')"><span class="glyphicon glyphicon-erase"></span></button></td>') +
          					'</tr></table>';
          		}
          	}
		]
	});
}

function showToastManiobras(mensajeTipo, mensajeError) {
	// Mensajes despues de GRABAR
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro guardado correctamente");
		 $('#myManiobras').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 $('#modal-maniobras').modal('hide');
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro actualizado correctamente");
		 $('#myManiobras').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='actualizadoNoOk'){
		 $('#modal-maniobras').modal('hide');
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue actualizado");
	 }
	 // Mensajes despues de ELIMINAR
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro inactivado correctamente");
		 $('#myManiobras').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 $('#modal-maniobras').modal('hide');
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
	 // MENSAJES DE ACTUALIZACION DEL FACTOR
	 if(mensajeTipo==='actualizadoFactorOk'){
		 toastr["success"](mensajeError, "Factor actualizado correctamente");
	 }
	 if(mensajeTipo==='actualizadoFactorNoOk'){
		 $('#modal-maniobras').modal('hide');
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el factor no fue actualizado");
	 }
}

function enableClientSideValidationManiobras() {
	
	$('input[name="strHora"]').on('dp.change dp.show dp.hide', function(e) {	
		$("#form-edit-maniobras").bootstrapValidator('revalidateField', 'strHora');
	});
	
	var validator = $("#form-edit-maniobras")
	.bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled'],
		fields : {
			strHora :{				
				validators : {
					notEmpty: {
			            message: 'Ingrese la hora'
			        },
                    regexp : {
						regexp : /^^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/,
						message : "La hora debe ser HH:MM"
					}
				}
			},			
			dblAperturaInicial :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la apertura inicial'
			        },
                    regexp : {
						regexp : /^\d{1,2}(\.\d{1,4})?$/,
						message : "Solo se permite hasta 2 enteros y 4 decimales"
					}
				}
			},
			dblAperturaFinal :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese la apertura final'
			        },
                    regexp : {
						regexp : /^\d{1,2}(\.\d{1,4})?$/,
						message : "Solo se permite hasta 2 enteros y 4 decimales"
					}
				}
			}
		}
	})
	.on('error.form.bv', function(e) {
		$('#flgClean').val(1);
    })
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoManiobrasForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-maniobras').attr('action'),
			data: $('#form-edit-maniobras').serialize(),
			success: function(result){
				$nuevoManiobrasForm.bootstrapValidator('resetForm', true);
				$nuevoManiobrasForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				// Cada vez que se realiza esto realiza las funciones de apertura de modal y realiza las validaciones
				$("[name='codigo']").val(null); // RESETAMOS HIDDEN PARA QUE PUEDA VOLVER A CREAR
				
				// PRIMERO LIMPIAMOS PARA QUE NO HAYA CONFLICTOS CON MIN Y MAX
				var today = new Date();
				refreshInputDate("strHora", today.getFullYear(), today.getMonth(), today.getDay());
				
				// VALIDAMOS APERTURA INICIAL EN BASE A MANIOBRAS
				validarDatosDeAperturaConUltimaManiobra($("#represasId").val(), false);
				
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});
}

function enableClientSideValidationFactorDescarga() {
	var validator = $("#form-edit-factor")
	.bootstrapValidator({
		message: 'El factor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled'],
		fields : {
			factorDescargaName :{				
				validators : {
					notEmpty: {
			            message: 'Ingrese el factor'
			        },
			        stringLength : {
						max : 50,
						message : "El factor ha de ser como m\u00e1ximo 50 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	var factorValor = 0;
                        	var formulaFactor = $('#factorDescargaId').val();
                        	try {
                        		if(!$.isNumeric(formulaFactor)){
                        			factorValor = math.eval(formulaFactor);
                            		factorValor = Math.round(factorValor * 100) / 100
                        		}
                        		else{
                        			factorValor = formulaFactor;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'El factor debe ser una f\u00F3rmula v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^\d{1,2}(\.\d{1,4})?$/.test(factorValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 2 enteros y 4 decimales para el valor del factor"
                                }
                            }
                        	
                        	return true;
                        }
                    }
				}
			}		
		}
	})
	.on('success.form.bv', function(e, data) {
		$("#actualizarFactorBtn").html('Actualizando...');
		$("#actualizarFactorBtn").attr('disabled', 'disabled');
		// GUARDAMOS EL VALOR DEL FACTOR
		$('#factor-descarga-valor').val(math.eval($('#factorDescargaId').val()));
		///
		e.preventDefault();
		var $nuevoFactorForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-factor').attr('action'),
			data: $('#form-edit-factor').serialize(),
			success: function(result){
				var valSaved = $("#factorDescargaId").val();
				$nuevoFactorForm.bootstrapValidator('resetForm', true);
				$("#factorDescargaId").val(valSaved);
				$('.container_save').html(result);
			},
			complete: function() {
				$("#actualizarFactorBtn").html('Actualizar Factor');
				$('#actualizarFactorBtn').removeAttr("disabled");
			}
		});
	});
}

enableClientSideValidationManiobras();

$(document).on("click", ".btn-limpiar", function(e) {	
	$('#form-edit-maniobras').bootstrapValidator('resetForm', true);
	if($('#flgClean').val() == 1){		
		$('#flgClean').val(0);
	}else{
		$("[name='codigo']").val(null); // RESETAMOS HIDDEN
		$("[name='strHora']").val(null);
		$("[name='intTipoManiobra']").val(null);
		$("[name='dblAperturaInicial']").val(null);
		$("[name='dblAperturaFinal']").val(null);
	}
	// PRIMERO LIMPIAMOS PARA QUE NO HAYA CONFLICTOS CON MIN Y MAX
	var today = new Date();
	refreshInputDate("strHora", today.getFullYear(), today.getMonth(), today.getDay());
	// VALIDAMOS APERTURA INICIAL EN BASE A MANIOBRAS
	validarDatosDeAperturaConUltimaManiobra($("#represasId").val(), false);
});

function modificarManiobras(id, hora, tipMan, apeIni, apeFin) {	
	//PRIMERO DEBEMOS VALIDAR QUE CAMPOS SE PERMITIRAN EDITAR
	// LIMPIAMOS 
	var today = new Date();
	$('#form-edit-maniobras').bootstrapValidator('resetForm', true);
	var bolLockHora = false;
	var bolLockApeIni = false;
	var bolLockApeFin = false;
	var horaMin;
	var minutoMin;
	var horaAct = hora.substring(0,2);
	var minutoAct = hora.substring(3,5);
	var horaMax;
	var minutoMax;
	$.ajax({
		type : 'GET',
		url : 'maniobras/getByRepresas?codRepresas=' + $("#represasId").val(),
		dataType : 'json',
		success : function(data) {
			// VALIDAMOS
			if(data.length === 1){
				bolLockHora = false;
				bolLockApeIni = false;
				bolLockApeFin = false;
				horaMin = 0;
				minutoMin = 0;
				horaMax = 23;
				minutoMax = 59;
			}
			else{
				var maniobraActual;
				var indManiobraActual;
				var maniobraAnterior;
				var maniobraSiguiente;
				$.each( data, function( key, value ) {
					if(value.intCodigo === id){
						indManiobraActual = key;
						return false; // SALIR DEL BUCLE
					}
				});
				
				$.each( data, function( key, value ) {
					if(key === (indManiobraActual-1)){
						maniobraAnterior = value;
					}
					else if(key === indManiobraActual){
						maniobraActual = value;
					}
					else if(key === (indManiobraActual+1)){
						maniobraSiguiente = value;
					}
				});
				
				if(indManiobraActual === 0) { // PRIMERO
//					console.log("PRIMERO");
					bolLockApeFin = true;
					horaMin = 0;
					minutoMin = 0;
					if(maniobraSiguiente.strHora.substring(3,5) == '00'){
						horaMax =  pad(parseInt(maniobraSiguiente.strHora.substring(0,2)) - 1, 2);
						minutoMax = 30;
					}
					else if(maniobraSiguiente.strHora.substring(3,5) == '30'){
						horaMax =  pad(parseInt(maniobraSiguiente.strHora.substring(0,2)), 2);
						minutoMax = 00;
					}
//					console.log("HORA MAX : " + horaMin);
				}
				else if(indManiobraActual === (data.length - 1)){ // ULTIMO
//					console.log("ULTIMO");
					bolLockApeIni = true;
					horaMin = pad(parseInt(maniobraAnterior.strHora.substring(0,2)) + 1, 2);
//					console.log("HORA MIN : " + horaMin);
					minutoMin = maniobraAnterior.strHora.substring(3,5);
					if(maniobraAnterior.strHora.substring(3,5) == '00'){
						horaMin =  pad(parseInt(maniobraAnterior.strHora.substring(0,2)), 2);
						minutoMin = 30;
					}
					else if(maniobraAnterior.strHora.substring(3,5) == '30'){
						horaMin =  pad(parseInt(maniobraAnterior.strHora.substring(0,2)) + 1, 2);
						minutoMin = 00;
					}
				}
				else{ // POSICIONES DEL MEDIO
//					console.log("MEDIO");
					bolLockApeIni = true;
					bolLockApeFin = true;
					if(maniobraAnterior.strHora.substring(3,5) == '00'){
						horaMin =  pad(parseInt(maniobraAnterior.strHora.substring(0,2)), 2);
						minutoMin = 30;
					}
					else if(maniobraAnterior.strHora.substring(3,5) == '30'){
						horaMin =  pad(parseInt(maniobraAnterior.strHora.substring(0,2)) + 1, 2);
						minutoMin = 00;
					}
					if(maniobraSiguiente.strHora.substring(3,5) == '00'){
						horaMax =  pad(parseInt(maniobraSiguiente.strHora.substring(0,2)) - 1, 2);
						minutoMax = 30;
					}
					else if(maniobraSiguiente.strHora.substring(3,5) == '30'){
						horaMax =  pad(parseInt(maniobraSiguiente.strHora.substring(0,2)), 2);
						minutoMax = 00;
					}
				}
			}
			
			// PRIMERO LIMPIAMOS PARA QUE NO HAYA CONFLICTOS CON MIN Y MAX
			refreshInputDate("strHora", today.getFullYear(), today.getMonth(), today.getDay());
			
			// SETEAMOS LOS CAMPOS 
			$("[name='codigo']").val(id);
			setearValoreMaxMin("strHora", "min", today.getFullYear(), today.getMonth(), today.getDay(),	
					horaMin, minutoMin, 0, 0);
			setearValoreMaxMin("strHora", "now", today.getFullYear(), today.getMonth(), today.getDay(),	
					horaAct, minutoAct, 0, 0);
			setearValoreMaxMin("strHora", "max", today.getFullYear(), today.getMonth(), today.getDay(),	
					horaMax, minutoMax, 0, 0);
			
			if(tipMan == "REGULADO"){		
				$("[name='intTipoManiobra']").val(1);
			}else if(tipMan == "NATURAL"){
				$("[name='intTipoManiobra']").val(2);
			}else{
				$("[name='intTipoManiobra']").val(3);
			}	
			$("[name='dblAperturaInicial']").val(apeIni);
			$("[name='dblAperturaFinal']").val(apeFin);	
			
			// AGREGAMOS READONLY SEGUN LAS VALIDACIONES
			if(bolLockHora){
				$("[name='strHora']").attr("readonly", "readonly");
			}
			else{
				$("[name='strHora']").removeAttr("readonly");
			}
			
			if(bolLockApeIni){
				$("[name='dblAperturaInicial']").attr("readonly", "readonly");
			}
			else{
				$("[name='dblAperturaInicial']").removeAttr("readonly");
			}
			
			if(bolLockApeFin){
				$("[name='dblAperturaFinal']").attr("readonly", "readonly");
			}
			else{
				$("[name='dblAperturaFinal']").removeAttr("readonly");
			}
		},
		error : function(xhr, status, error) {
		},
		complete : function(data) {
		}
	}); 
	
}

function inactivarManiobras(id) {
	$.ajax({
		type : 'GET',
		url : 'maniobras/getLast?codRepresas=' + $("#represasId").val(),
		dataType : 'json',
		success : function(data) {
		    // VALIDAMOS SI ES QUE EXISTE
			if(data.intCodigo != null && data.intCodigo != id){
				$.confirm({
				    title: 'Mensaje del Sistema',
				    content: 'Primero debe inactivar las maniobra de hora mayor',
				    buttons: {
				    	Aceptar: { 
				    	}
				    }
				});	
			}
			else{
				$.confirm({
				    title: 'Confirmaci\u00f3n',
				    content: '¿Confirma inactivar la Maniobra?',
				    buttons: {
				    	Aceptar: { 
				        	btnClass: 'btn-blue',
				        	action: function () {
				        		$('#inacIdManiobra').val(id);
							    $.ajax({
							    	type: 'POST',
							    	url: $('#form-inactiva-maniobras').attr('action'),
							    	data: $('#form-inactiva-maniobras').serialize(),  
							    	success: function(result){  		   
							    		$('.container_save').html(result);		 
							    		// Automaticamente ejecuta las validaciones de apertura de modal
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
		},
		error : function(xhr, status, error) {
		},
		complete : function(data) {
			$('#modal-maniobras').modal('show');
        	$.unblockUI();
		}
	}); 
	
};

function validarDatosDeAperturaConUltimaManiobra(codRepresas, openModal) {
	$.ajax({
		type : 'GET',
		url : 'maniobras/getLast?codRepresas=' + codRepresas,
		dataType : 'json',
		success : function(data) {
		    // VALIDAMOS SI ES QUE EXISTE
			var today = new Date();
			if(data.intCodigo != null){
				//INICALIZAMOS PARA MANEJO DE HORAS
				if(data.strHora.substring(3,5) == '00'){
					setearValoreMaxMin("strHora", "min", today.getFullYear(), today.getMonth(), today.getDay(),	
	            			pad(parseInt(data.strHora.substring(0,2)), 2), 30, 0, 0);
					setearValoreMaxMin("strHora", "now", today.getFullYear(), today.getMonth(), today.getDay(),	
	            			pad(parseInt(data.strHora.substring(0,2)), 2), 30, 0, 0);
				}
				else if(data.strHora.substring(3,5) == '30'){
					setearValoreMaxMin("strHora", "min", today.getFullYear(), today.getMonth(), today.getDay(),	
	            			pad(parseInt(data.strHora.substring(0,2)) + 1, 2), 00, 0, 0);
					setearValoreMaxMin("strHora", "now", today.getFullYear(), today.getMonth(), today.getDay(),	
	            			pad(parseInt(data.strHora.substring(0,2)) + 1, 2), 00, 0, 0);
				}
				
//            	setearValoreMaxMin("strHora", "min", today.getFullYear(), today.getMonth(), today.getDay(),	
//            			pad(parseInt(data.strHora.substring(0,2)) + 1, 2), data.strHora.substring(3,5), 0, 0);
//            	setearValoreMaxMin("strHora", "now", today.getFullYear(), today.getMonth(), today.getDay(),	
//            			pad(parseInt(data.strHora.substring(0,2)) + 1, 2), data.strHora.substring(3,5), 0, 0);
				setearValoreMaxMin("strHora", "max", today.getFullYear(), today.getMonth(), today.getDay(),	23, 59, 0, 0);
        	    // LIMPIAMOS EL FORMULARIO (SE HACE DESPUES PORQUE SINO VALIDA LOS CAMPOS DATETIMEPICKER)
            	$('#form-edit-maniobras').bootstrapValidator('resetForm', true);
            	// DESPUES DE LIMPIAR FORMULARIO, VALIDAMOS
            	$("[name='dblAperturaInicial']").val(data.dblAperturaFinal);
				$("[name='dblAperturaInicial']").attr("readonly","readonly");
			}
			else{
				setearValoreMaxMin("strHora", "min", today.getFullYear(), today.getMonth(), today.getDay(),	0, 0, 0, 0);
				setearValoreMaxMin("strHora", "now", today.getFullYear(), today.getMonth(), today.getDay(),	0, 0, 0, 0);
				setearValoreMaxMin("strHora", "max", today.getFullYear(), today.getMonth(), today.getDay(),	23, 59, 0, 0);
				// LIMPIAMOS EL FORMULARIO (SE HACE DESPUES PORQUE SINO VALIDA LOS CAMPOS DATETIMEPICKER)
            	$('#form-edit-maniobras').bootstrapValidator('resetForm', true);
				// QUITAMOS 
				$("[name='dblAperturaInicial']").removeAttr("readonly");
			}
			// SIEMPRE LIMPIAR LO SIGUIENTE
			$("[name='dblAperturaFinal']").removeAttr("readonly");
		},
		error : function(xhr, status, error) {
		},
		complete : function(data) {
			if(openModal){
				$('#modal-maniobras').modal('show');
				$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
	        	$.unblockUI();
			}
		}
	}); 
}


//EVENTO DE APERTURA DE MODAL DE MANIOBRAS
$('#modal-maniobras').on('shown.bs.modal', function() {
	// PARA QUE SE REAJUSTE
	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
})

function refreshInputDate(input, year, month, day) {
	$('input[name="'+input+'"]').data("DateTimePicker").minDate(new Date(year, month, day, 0, 0, 0, 0));
	$('input[name="'+input+'"]').data("DateTimePicker").maxDate(new Date(year, month, day, 23, 59, 0, 0));
}

function setearValoreMaxMin(input, tipoSet, year, month, day, hour, minute, second, milisecond) {
	if(tipoSet === "min"){
		$('input[name="'+input+'"]').data("DateTimePicker").minDate(new Date(year, month, day, hour, minute, second, milisecond));
	}
	else if(tipoSet === "max"){
		$('input[name="'+input+'"]').data("DateTimePicker").maxDate(new Date(year, month, day, hour, minute, second, milisecond));
	}
	else if(tipoSet === "now"){
		$('input[name="'+input+'"]').data("DateTimePicker").date(new Date(year, month, day, hour, minute, second, milisecond));
	}
}
