
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTablaPoisson').click(function () {		 
		 $('#cboLambdasHidden').val($('#cboLambdas').val());
		 $('#myTableTablaPoisson').DataTable().ajax.reload();
	});
	
	//CARGA EL COMBO DE LAMBDAS
	cargarCombo("listLambdas","#cboLambdas");
});

function modificarTablaPoisson(id) {

	$.ajax({
		type: 'GET',
		url: 'tablaPoissonGet/'+id, 
		success: function(result) {
			$('#id-modal-content').replaceWith(result);
			
			if(id != 0){
				$('#idModalTitle').html($('#idValEditModalTitle').html());
				$("#intCodLambda").attr('disabled', 'disabled');
				$("#strCodIgx").attr('disabled', 'disabled');
			}else{
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			enableValidationRules();
		},complete: function() {
			$('#modal-registra-tablaPoisson').modal('show');
			$.unblockUI();
			
		}
	});
}

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
function enableValidationRules() {

	var validator = $("#form-edit-tablaPoisson-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			intCodLambda: {
				validators : {
					regexp: {
						 
						 regexp: /^\d*(\.\d{1})?\d{0,3}$/,
	 
						 message: 'Código Y incorrecto. Ejemplo: 19.7461'
	 
					},
					stringLength : {
						max : 20,
						message : "El campo código Lambda ha de ser como m\u00e1ximo 20 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese Código Lambda'
                    }
				}
            },
            strCodIgx: {
				validators : {
					stringLength : {
						max : 20,
						message : "El campo código X ha de ser como m\u00e1ximo 20 caracteres"
					},
					regexp: {
						 
						 regexp: /^\d*(\.\d{1})?\d{0,3}$/, 
	 
						 message: 'Código X incorrecto. Ejemplo: 19.7461'
	 
					},
					notEmpty: {
                        message: 'Ingrese Código X'
                    }
				}
            },
            strValorPoisson: {
				validators : {
					stringLength : {
						max : 20,
						message : "El campo Valor ha de ser como m\u00e1ximo 20 caracteres"
					},
					regexp: {
						 
						 regexp: /^[0-9]+([.][0-9]+)?$/, 
	 
						 message: 'Código Valor incorrecto. ejemplo: nn.nn'
	 
					},
					notEmpty: {
                        message: 'Ingrese Campo Valor'
                    }
				}
            }
		}
	})	
	.on('success.form.bv', function(e, data) {
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTablaPoissonForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-tablaPoisson-id').attr('action'),
			data: $('#form-edit-tablaPoisson-id').serialize(),
			success: function(result){
				$nuevoTablaPoissonForm.bootstrapValidator('resetForm', true);
				$nuevoTablaPoissonForm[0].reset();
				$('.container_save').html(result);
			},
			error: function(error) {
				console.log("error");
				$('.container_save').html(error.responseText);
		    },
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	});	
}

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarTablaPoisson(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacId').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-tablaPoisson').attr('action'),
			    	   data: $('#form-inactiva-tablaPoisson').serialize(),  
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

function showToastTablaPoisson(mensajeTipo, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-tablaPoisson').modal('hide');
		 $('#idBtnBuscarTablaPoisson').click();
	 }
	if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-tablaPoisson').modal('hide');
		 $('#idBtnBuscarTablaPoisson').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 $('#modal-registra-tablaPoisson').modal('hide');
		 $('#idBtnBuscarTablaPoisson').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
		 //$('#modal-registra-tablaPoisson').modal('hide');
		 //$('#idBtnBuscarTablaPoisson').click();
	 }
}
