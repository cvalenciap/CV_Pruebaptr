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

	$('#idBtnFormBuscarRepresa').click(function () {
		$('#myRepresa').DataTable().ajax.reload();
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

//EVENTO DE APERTURA DE MODAL
$("#modal-registra-represa").on("show.bs.modal", function () {
	$("#represaNombre").keydown(function(event){		
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
	$("#represaAbreviatura").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
});

//EVENTO DE CERRADO DE MODAL
$("#modal-registra-represa").on("hidden.bs.modal", function () {
	$("#codigo").val("");
	// HACEMOS QUE SE MARQUE EL CHECK EN FA
	$("#perteneceAlmacenamiento2").attr('checked', 'checked');
});

function clearFormEdit() {
	$('[name="nombreRepresa"]').val('');
	$('[name="abreviatura"]').val('');
	$('[name="estado"]').val('1');
}

function showToastRepresa(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro guardado correctamente");
		 $('#modal-registra-represa').modal('hide');
		 $('#idBtnFormBuscarRepresa').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 if(mensajeError===MSG_VALID_ABREV && $("[name='codigo']").val() === ""){
			 clearFormEdit();
		 }
	 }
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro actualizado correctamente");
		 $('#modal-registra-represa').modal('hide');
		 $('#idBtnFormBuscarRepresa').click();
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
		 $('#modal-registra-represa').modal('hide');
		 $('#idBtnFormBuscarRepresa').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
}

function enableClientSideValidationRepresa() {
	var validator = $("#form-edit-represa")
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
			nombreRepresa :{
				message : "Ingrese el nombre represa",
				validators : {
					stringLength : {
						max : 50,
						message : "La descripci\u00f3n ha de ser como m\u00e1ximo 50 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre represa'
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
			},
			strTopeMaximo :{
				validators : {					
					notEmpty: {
			            message: 'Ingrese el tope m\u00e1ximo'
			        },
                    regexp : {
						regexp : /^\d{1,10}(\.\d{1,8})?$/,
						message : "S\u00F3lo se permite hasta 10 enteros y 8 decimales"
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
		MYAPPL.toUppercaseInputs();
		$.ajax({
			type: 'POST',
			url: $('#form-edit-represa').attr('action'),
			data: $('#form-edit-represa').serialize(),
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

enableClientSideValidationRepresa();

function modificarRepresa(id) {
	
	$.ajax({
		type: 'GET',
		url: 'represaGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableClientSideValidationRepresa();
		},complete: function() {
			$('#modal-registra-represa').modal('show');
			$.unblockUI();
			
		}
	});
}

function clearSearchRepresa() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
	$('#cpEstado').val("1");
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchRepresa();
});

function clearFormEditRepresa() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
}

$(document).on("click", ".btn-create-represa", function(e) {
	$("#form-edit-represa")[0].reset();
	$("#form-edit-represa").bootstrapValidator('resetForm', true);
	$('#idModalTitle').html($('#idValCrearModalTitle').html());
	clearFormEditRepresa();
	 $("#idEstado").css("display", "none");
	$('#modal-registra-represa').modal('show');
	$.unblockUI();
});



//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarRepresa(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '¿Confirma inactivar represa?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdRepresa').val(id);
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-represa').attr('action'),
			    	   data: $('#form-inactiva-represa').serialize(),  
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