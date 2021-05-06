
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarFormulaMuestra').click(function () {
		 $('#codGrupoHide').val($('#codGrupo').val());
		 $('#myTableFormulaMuestra').DataTable().ajax.reload();
	});

	//CARGA EL COMBO DE GRUPO
	cargarCombo("listGrupoTodos","#codGrupo");
	
	
});

function modificarFormulaMuestra(id) {

	$.ajax({
		type: 'GET',
		url: 'formulaMuestraGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			//CARGA EL COMBO DE GRUPO
			cargarCombo("listGrupo","#codGrupoEdit");
			if(id != 0){
				$('#idModalTitle').html($('#idValEditModalTitle').html());
				$("#codGrupoEdit").attr('disabled', 'disabled');
				//$("#strCodFormulario").attr('disabled', 'disabled');
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			
			enableValidationRules();
		},complete: function() {
			$('#modal-registra-formulaMuestra').modal('show');
			$.unblockUI();
			
		}
	});
}

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRules() {

	var validator = $("#form-edit-formulaMuestra-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			
            strCodFormulario: {
				validators : {
					stringLength : {
						max : 20,
						message : "El campo Valor ha de ser como m\u00e1ximo 20 caracteres"
					},					
					notEmpty: {
                        message: 'Ingrese Código de Formulario'
                    }
				}
            }
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoFormulaMuestraForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-formulaMuestra-id').attr('action'),
			data: $('#form-edit-formulaMuestra-id').serialize(),
			success: function(result){
				$nuevoFormulaMuestraForm.bootstrapValidator('resetForm', true);
				$nuevoFormulaMuestraForm[0].reset();
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
function inactivarFormulaMuestra(id) {
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
			    	   url: $('#form-inactiva-formulaMuestra').attr('action'),
			    	   data: $('#form-inactiva-formulaMuestra').serialize(),  
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

function showToastFormulaMuestra(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-formulaMuestra').modal('hide');
		 $('#idBtnBuscarFormulaMuestra').click();
	 }
	if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-formulaMuestra').modal('hide');
		 $('#idBtnBuscarFormulaMuestra').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 $('#modal-registra-formulaMuestra').modal('hide');
		 $('#idBtnBuscarFormulaMuestra').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["success"](mensajeError, "Registro ya existe en la Tabla");
		 $('#modal-registra-formulaMuestra').modal('hide');
		 //$('#idBtnBuscarFormulaMuestra').click();
	 }
}
