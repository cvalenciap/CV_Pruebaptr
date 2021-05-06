/**
 * Hoja de scripts manejados en el mantenimiento de tipo de proceso
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

	$('#idBtnFormBuscarRio').click(function () {
		$('#myRio').DataTable().ajax.reload();
	});
	
	$("#cpDesc").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}    	    
	});	
	$("#cpAbrev").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	
});

$("#modal-registra-rio").on("show.bs.modal", function () {
	$("#rioNombre").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#rioNombreLargo").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#rioAbreviatura").keydown(function(event){	
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
});

//EVENTO DE CERRADO DE MODAL
$("#modal-registra-rio").on("hidden.bs.modal", function () {
	$("#codigo").val("");
	// HACEMOS QUE SE MARQUE EL CHECK EN FA
	$("#perteneceAforo2").attr('checked', 'checked');
});

function showToastRio(mensajeTipo, mensajeError) { 
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro guardado correctamente");
		 $('#modal-registra-rio').modal('hide');
		 $('#idBtnFormBuscarRio').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 if(mensajeError===MSG_VALID_ABREV && $("[name='codigo']").val() === ""){
			 clearFormEdit();
		 }
	 }
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro actualizado correctamente");
		 $('#modal-registra-rio').modal('hide');
		 $('#idBtnFormBuscarRio').click();
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
		 $('#modal-registra-rio').modal('hide');
		 $('#idBtnFormBuscarRio').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
}

function enableClientSideValidationRio() {
	var validator = $("#form-edit-rio")
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

			nombrerio :{
				validators : {
					stringLength : {
						max : 50,
						message : "El  r\u00edo ha de ser como m\u00e1ximo 50 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre r\u00edo'
			        }
				}
			},

			nombreLargo :{
				validators : {
					stringLength : {
						max : 200,
						message : "El nombre largo ha de ser como m\u00e1ximo 200 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre largo de r\u00edo'
			        }
				}
			},
			abreviatura :{
				validators : {
					stringLength : {
						max : 30,
						message : "La abreviatura ha de ser como m\u00e1ximo 30 caracteres"
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
		var $nuevoRioForm = $(e.target);
		MYAPPL.toUppercaseInputs();
		$.ajax({
			type: 'POST',
			url: $('#form-edit-rio').attr('action'),
			data: $('#form-edit-rio').serialize(),
			success: function(result){
				$nuevoRioForm.bootstrapValidator('resetForm', true);
				$nuevoRioForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});
}

function clearFormEdit() {
	$('[name="nombrerio"]').val('');
	$('[name="nombreLargo"]').val('');
	$('[name="abreviatura"]').val('');
	$('[name="estado"]').val('1');
}

enableClientSideValidationRio();

function modificarRio(id) {	
	$.ajax({
		type: 'GET',
		url: 'rioGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableClientSideValidationRio();
		},complete: function() {
			$('#modal-registra-rio').modal('show');
			$.unblockUI();			
		}
	});
}

function clearSearchRio() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");	
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchRio();
});

function clearFormEditRio() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
}

$(document).on("click", ".btn-create-rio", function(e) {
	$("#form-edit-rio")[0].reset();
	$("#form-edit-rio").bootstrapValidator('resetForm', true);
	$('#idModalTitle').html($('#idValCrearModalTitle').html());
	clearFormEditRio();
	 $("#idEstado").css("display", "none");
	$('#modal-registra-rio').modal('show');
	$.unblockUI();
});

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarRio(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '¿Confirma inactivar r&iacute;o?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdRio').val(id);
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-rio').attr('action'),
			    	   data: $('#form-inactiva-rio').serialize(),  
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
