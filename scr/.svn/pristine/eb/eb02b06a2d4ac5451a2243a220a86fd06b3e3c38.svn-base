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
	
	//CARGA DE LOS DISTRITOS EN LA BUSQUEDA
	cargarCombo("listLambdas","#cboAreas");
	
	$('#idBtnFormBuscarClima').click(function () {
		
		$('#myClima').DataTable().ajax.reload();
	});

});

//EVENTO DE CERRADO DE MODAL
$("#modal-registra-clima").on("hidden.bs.modal", function () {
	$("#codigo").val("");
});


function showToastClima(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-clima').modal('hide');
		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente inactivado");
		 $('#modal-registra-clima').modal('hide');
		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
}

function enableClientSideValidationClima() {
	var validator = $("#form-edit-actividad-area")
	.bootstrapValidator({
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
						max : 100,
						message : "La Descripci\u00f3n ha de ser como m\u00e1ximo 100 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese el nombre de Clima'
			        }
				}
			},
			abreviatura :{
				validators : {
					stringLength : {
						max : 20,
						message : "La Abreviatura ha de ser como m\u00e1ximo 20 caracteres"
					},
					notEmpty: {
			            message: 'Ingrese la Abreviatura'
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
	    content: 'Confirma inactivar Clima ?',
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



