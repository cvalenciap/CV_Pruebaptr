/**
 * Hoja de scripts manejados en el mantenimiento de represa
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
	
	$('#idBtnFormBuscarColaborador').click(function () {
		$('#myColaborador').DataTable().ajax.reload();
	});
	
	$('#idBtnFormBuscarTrabajador').click(function () {
		$('#myTrabajador').DataTable().ajax.reload();
	});

	$("#cpDesc").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#cpNomEmp").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
});

$("#modal-registra-colaborador").on("show.bs.modal", function () {
	$("#colaboradorNombre").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#colaboradorDni").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#colaboradorEmpresa").keydown(function(event){	
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#colaboradorAbreviatura").keydown(function(event){	
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});	
	
	$('#numFicha').val("");
	$('#nombreCompleto').val("");
	$('#myTrabajador').DataTable().ajax.reload();
});

$("#modal-busca-trabajador").on("show.bs.modal", function () {
	$("#numFicha").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#nombreCompleto").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});	
	
	// Refresh 
	$('#myTrabajador').DataTable().ajax.reload();
	
});

function logicaEdicion(colaboradorBean) {
	if(colaboradorBean.codigo != null){
		$("#optBuscarTrabajador").css("display", "none");
	}
	
	if($.trim(colaboradorBean.tipoTrabajador).toUpperCase() === '1'){
		$("#optActualizarInterno").css("display", "block");
		$('#tipoTrabajador').val("1"); // INTERNO
		$('#colaboradorAbreviatura').attr("readonly", "readonly");
		$('#colaboradorNombre').attr("readonly", "readonly");
		$('#colaboradorDni').attr("readonly", "readonly");
		$('#colaboradorEmpresa').attr("readonly", "readonly");
	}
	else{
		$("#optActualizarInterno").css("display", "none");
		$('#tipoTrabajador').val("2"); // EXTERNO
	}	
}

function actualizarInfoInterno() {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'GET',
		url: 'trabajador/'+$.trim($('#colaboradorAbreviatura').val()), 
		success: function(result) {
			$('#colaboradorAbreviatura').val(result.numFicha);
			$('#colaboradorNombre').val(result.nombreCompleto);
			$('#colaboradorDni').val(result.numDocumento);
			$('#colaboradorEmpresa').val(result.nombreEmpresa);
		},complete: function() {
			$.unblockUI();
		}
	});
}

//EVENTO DE CERRADO DE MODAL
$("#modal-registra-colaborador").on("hidden.bs.modal", function(){
	$("#codigo").val("");
});

function clearFormEdit() {
	$('[name="nombreColaborador"]').val('');
	$('[name="dni"]').val('');
	$('[name="nombreEmpresa"]').val('');
	$('[name="abreviatura"]').val('');
	$('[name="estado"]').val('1');
	
	// Se quita el readOnly
	$('#colaboradorAbreviatura').removeAttr("readonly");
	$('#colaboradorNombre').removeAttr("readonly");
	$('#colaboradorDni').removeAttr("readonly");
	$('#colaboradorEmpresa').removeAttr("readonly");
}

function showToastColaborador(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro guardado correctamente");
		 $('#modal-registra-colaborador').modal('hide');
		 $('#idBtnFormBuscarColaborador').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 if(mensajeError===MSG_VALID_ABREV && $("[name='codigo']").val() === ""){
			 clearFormEdit();
		 }
	 }
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro actualizado correctamente");
		 $('#modal-registra-colaborador').modal('hide');
		 $('#idBtnFormBuscarColaborador').click();
	 }
	 if(mensajeTipo==='actualizadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue actualizado");
		 if(mensajeError===MSG_VALID_ABREV && $("[name='codigo']").val() === ""){
			 clearFormEdit();
		 }
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro inactivado correctamente");
		 $('#modal-registra-colaborador').modal('hide');
		 $('#idBtnFormBuscarColaborador').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
}

function enableClientSideValidationColaborador() {
	var validator = $("#form-edit-colaborador")
	.bootstrapValidator({
		trigger: 'blur',
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled'],
		fields : {
			nombreColaborador :{				
				validators : {
					stringLength : {
						max : 50,
						message : "El nombre colaborador ha de ser como m\u00e1ximo 50 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre colaborador'
			        }
				}
			},
			dni :{				
				validators : {
						notEmpty: {
				            message: 'Ingrese n\u00famero documento'
				        },
				        callback:{
	                        message: 'El n\u00famero documento no es v\u00e1lido',
	                        callback: function(value, password, $field){

	                        	if(value===''){ // PARA QUE NO VALIDE DE NUEVO
	                                return true;
	                             }
	                        	
	                            if (value.length != 8) {
	                                return {
	                                    valid: false,
	                                    message: 'El n\u00famero documento debe ser de 8 d\u00edgitos'
	                                };
	                            }

	                            if (!/[^0{8,}]/.test(value)) {
	                                return {
	                                    valid: false,
	                                    message: 'El n\u00famero documento no es v\u00e1lido'
	                                }
	                            }
	                            
	                            return true;
	                        }               

	                    }
				}
			},			
			nombreEmpresa :{
				validators : {
					stringLength : {
						max : 80,
						message : "El nombre empresa ha de ser como m\u00e1ximo 80 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre empresa'
			        }
				}
			},
			abreviatura :{
				validators : {
					stringLength : {
						max : 30,
						message : "La abreviaura ha de ser como m\u00e1ximo 30 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese la abreviatura'
			        }
				}
			}
		}
	})
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoRepresaForm = $(e.target);
		//console.log($nuevoRepresaForm);
		MYAPPL.toUppercaseInputs();
		$.ajax({
			type: 'POST',
			url: $('#form-edit-colaborador').attr('action'),
			data: $('#form-edit-colaborador').serialize(),
			success: function(result){
				$nuevoRepresaForm.bootstrapValidator('resetForm', true);
				$nuevoRepresaForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});
}

enableClientSideValidationColaborador();

function modificarColaborador(id) {	
	$.ajax({
		type: 'GET',
		url: 'colaboradorGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableClientSideValidationColaborador();
		},complete: function() {
			$('#modal-registra-colaborador').modal('show');
			$.unblockUI();
			
		}
	});
}

function clearSearchColaborador() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
	$('#cpEstado').val("1");
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchColaborador();
});

function clearFormEditColaborador() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
}

$(document).on("click", ".btn-create-colaborador", function(e) {
	$("#form-edit-colaborador")[0].reset();
	$("#form-edit-colaborador").bootstrapValidator('resetForm', true);
	$('#idModalTitle').html($('#idValCrearModalTitle').html());
	clearFormEditColaborador();
	// Oculta el combo de estado
	$("#idEstado").css("display", "none");
	$('#tipoTrabajador').val("2"); // POR DEFECTO EXTERNO
	// Mostrar opcion
	$("#optBuscarTrabajador").css("display", "block");
	// ocultar opcion
	$("#optActualizarInterno").css("display", "none");
	$('#modal-registra-colaborador').modal('show');
	$.unblockUI();
});

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarColaborador(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '¿Confirma inactivar colaborador?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdColaborador').val(id);
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-colaborador').attr('action'),
			    	   data: $('#form-inactiva-colaborador').serialize(),  
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

function openModalTrabajador(){
	$('#numFicha').val("");
	$('#nombreCompleto').val("");
}

function seleccionarTrabajador(ficha, nombre, dni, empresa, tipo){
	// Refrescamos bootstrap validator de la edicion de colaborador
	// En el metodo success del validator podemos poner console log y ver cual es el componente
	// Lo hacemos aca porque solo debe pasar si se selecciona algo, sino que no limpie campos
	$('form#form-edit-colaborador.bv-form').bootstrapValidator('resetForm', true);
	$('form#form-edit-colaborador.bv-form')[0].reset();	
	//////
	
	$('#colaboradorAbreviatura').val(ficha);
	$('#colaboradorNombre').val(nombre);
	$('#colaboradorDni').val(dni);
	$('#colaboradorEmpresa').val(empresa);	
	$('#tipoTrabajador').val(tipo);
	
	$('#colaboradorAbreviatura').attr("readonly", "readonly");
	$('#colaboradorNombre').attr("readonly", "readonly");
	$('#colaboradorDni').attr("readonly", "readonly");
	$('#colaboradorEmpresa').attr("readonly", "readonly");
		
	$('#modal-busca-trabajador').modal('hide');		
}

$("#modal-registra-colaborador").on("hidden.bs.modal", function(){
	$('#colaboradorAbreviatura').removeAttr("readonly");
	$('#colaboradorNombre').removeAttr("readonly");
	$('#colaboradorDni').removeAttr("readonly");
	$('#colaboradorEmpresa').removeAttr("readonly");
});