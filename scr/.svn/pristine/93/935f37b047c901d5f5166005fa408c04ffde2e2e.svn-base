/**
 * Hoja de scripts manejados en el mantenimiento de clima
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

	$('#idBtnFormBuscarClima').click(function () {
		$('#myClima').DataTable().ajax.reload();
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
$("#modal-registra-clima").on("show.bs.modal", function () {	
	$("#climaNombre").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){						
			event.preventDefault();	   
		}
	});
	
	$("#climaAbreviatura").keydown(function(event){
		if(event.ctrlKey && event.keyCode == 86){
			event.preventDefault();	
		}		
	});
});

//EVENTO DE CERRADO DE MODAL
$("#modal-registra-clima").on("hidden.bs.modal", function () {
	$("#codigo").val("");
});

function clearFormEdit() {
	$('[name="nombreClima"]').val('');
	$('[name="abreviatura"]').val('');
	$('[name="estado"]').val('1');
}

function showToastClima(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro guardado correctamente");
		 $('#modal-registra-clima').modal('hide');
		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 if(mensajeError===MSG_VALID_ABREV && $("[name='codigo']").val() === ""){
			 clearFormEdit();
		 }
	 }
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro actualizado correctamente");
		 $('#modal-registra-clima').modal('hide');
		 $('#idBtnFormBuscarClima').click();
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
		 $('#modal-registra-clima').modal('hide');
		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
}

function enableClientSideValidationClima() {
	var validator = $("#form-edit-clima")
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
			nombreClima :{
				validators : {
					stringLength : {
						max : 50,
						message : "La descripci\u00f3n ha de ser como m\u00e1ximo 50 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre clima'
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
		var $nuevoClimaForm = $(e.target);
		MYAPPL.toUppercaseInputs();
		$.ajax({
			type: 'POST',
			url: $('#form-edit-clima').attr('action'),
			data: $('#form-edit-clima').serialize(),
			success: function(result){
				$nuevoClimaForm.bootstrapValidator('resetForm', true);
				$nuevoClimaForm[0].reset();
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});
}

enableClientSideValidationClima();

function modificarClima(id) {
	
	$.ajax({
		type: 'GET',
		url: 'climaGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			$('#idModalTitle').html($('#idValEditModalTitle').html());
			enableClientSideValidationClima();
		},complete: function() {
			$('#modal-registra-clima').modal('show');
			$.unblockUI();
			
		}
	});
}

function clearSearchClima() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
	$('#cpEstado').val("1");
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchClima();
});

function clearFormEditClima() {
	$('#cpDesc').val("");
	$('#cpAbrev').val("");
}

$(document).on("click", ".btn-create-clima", function(e) {
	$("#form-edit-clima")[0].reset();
	$("#form-edit-clima").bootstrapValidator('resetForm', true);
	$('#idModalTitle').html($('#idValCrearModalTitle').html());
	clearFormEditClima();
	 $("#idEstado").css("display", "none");
	$('#modal-registra-clima').modal('show');
	$.unblockUI();
});

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarClima(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '¿Confirma inactivar clima?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdclima').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-clima').attr('action'),
			    	   data: $('#form-inactiva-clima').serialize(),  
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