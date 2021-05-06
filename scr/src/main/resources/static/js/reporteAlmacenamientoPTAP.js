
	$(document).ready(function() {
								
		
	});
	
	var idCabecera;
	function modificarReporteAlmacenamientoPTAP(id) {
	
		idCabecera = id;
		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'reporteAlmacenamientoPTAPGet/'+id, 
			success: function(result) {
				$('#id-modal-content').replaceWith(result);						
				
				cargarCombo("listPersonaMuestrea","#strMuestreador");
				cargarCombo("listEspecialistas","#strAnalista");
								
				if(id != -1){
					
					$("#strFechaMuestreo").attr('disabled', 'disabled');
					$("#strHoraMuestreo").attr('disabled', 'disabled');
					$("#strTurno").attr('disabled', 'disabled');
					$("#strFechaRecepcionMuestra").attr('disabled', 'disabled');
					$("#strHoraRecepcionMuestra").attr('disabled', 'disabled');
					$("#strMuestreador").attr('disabled', 'disabled');
					$("#strFechaInicioEnsayo").attr('disabled', 'disabled');
					$("#strHoraInicioEnsayo").attr('disabled', 'disabled');
					$("#strAnalista").attr('disabled', 'disabled');
					
					$("#idDetalleAlmacenamientoPTAP").show();
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{					
					$("#idDetalleAlmacenamientoPTAP").hide();
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
					
					$('#strFechaRecepcionMuestra').datetimepicker({
					      //  format: 'DD/MM/YYYY HH:mm:SS',
							format: 'DD/MM/YYYY',
					        ignoreReadonly: false,
					        date: new Date(),
					        locale: "es"
					});
					$('#strFechaMuestreo').datetimepicker({
					      //  format: 'DD/MM/YYYY HH:mm:SS',
							format: 'DD/MM/YYYY',
					        ignoreReadonly: false,
					        date: new Date(),
					        locale: "es"
					});
					$('#strFechaInicioEnsayo').datetimepicker({
					      //  format: 'DD/MM/YYYY HH:mm:SS',
							format: 'DD/MM/YYYY',
					        ignoreReadonly: false,
					        date: new Date(),
					        locale: "es"
					});
				}								
				
				enableValidationRules();
			},complete: function() {
				$('#modal-registra-almacenamientoPTAP').modal('show');
				

				$('input[name="strHoraMuestreo"]').datetimepicker({
				    format: 'HH:mm',
				    ignoreReadonly: false,
				    locale: "es"
				});
	
				
				$('input[name="strHoraRecepcionMuestra"]').datetimepicker({
				    format: 'HH:mm',
				    ignoreReadonly: false,
				    locale: "es"
				});
	
				
				$('input[name="strHoraInicioEnsayo"]').datetimepicker({
				    format: 'HH:mm',
				    ignoreReadonly: false,
				    locale: "es"
				});
							
				if($('#intCumplimientoLBDHide').val()==1){
					setTimeout(function(){
						
						$("#bolCumplimientoLBD1").prop("checked", true); //cambia el id del checkbox para que funcione
					}, 500);
				}
				if($('#intMantConexionesHide').val()==1){
					setTimeout(function(){
						
						$("#bolMantConexiones1").prop("checked", true);
					}, 500);
				}
				
				setTimeout(function(){
					if(id != -1){
						$('#strMuestreador').val($('#strMuestreadorHide').val());
						$('#strAnalista').val($('#strAnalistaHide').val());
					}
				}, 500);
				
				$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				
				$.unblockUI();
				
			}
		});
	}
	
	//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
	function enableValidationRules() {
		
		$('input[name="strHoraMuestreo"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-almacenamientoPTAP-id").bootstrapValidator('revalidateField', 'strHoraMuestreo');
		});
		
		$('input[name="strHoraRecepcionMuestra"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-almacenamientoPTAP-id").bootstrapValidator('revalidateField', 'strHoraRecepcionMuestra');
		});
		
		$('input[name="strHoraInicioEnsayo"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-almacenamientoPTAP-id").bootstrapValidator('revalidateField', 'strHoraInicioEnsayo');
		});
		
		var validator = $("#form-edit-almacenamientoPTAP-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strCodEquipo: {
					validators : {
								
						stringLength : {
							max : 20,
							message : "El valor ha de ser como m\u00e1ximo 20 caracteres"
						},
						notEmpty: {
	                        message: 'Ingrese Código de equipo'
	                    }
					}
				}			
			}
		})	
		.on('success.form.bv', function(e, data) {
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoAlmacenamientoPTAPForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-almacenamientoPTAP-id').attr('action'),
				data: $('#form-edit-almacenamientoPTAP-id').serialize(),
				success: function(result){
					$nuevoAlmacenamientoPTAPForm.bootstrapValidator('resetForm', true);
					$nuevoAlmacenamientoPTAPForm[0].reset();
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
	function inactivarAlmacenamientoPTAP(id) {
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
				    	   url: $('#form-inactiva-almacenamientoPTAP').attr('action'),
				    	   data: $('#form-inactiva-almacenamientoPTAP').serialize(),  
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
	}
	
	function modificarPTAPResultTable(id) {
		
		idCabecera = id;
		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'PTAPResultTable/'+id, 
			success: function(result) {
				$('#id-modal-content-PTAPResultTable').replaceWith(result);						
												
				if(id != -1){
					
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{					

					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				
				enableValidationRulesPTAPResultTable();
			},complete: function() {
				$('#modal-registra-PTAPResultTableEdit').modal('show');				
								
				$.unblockUI();
				
			}
		});
	}
	
	function enableValidationRulesPTAPResultTable() {
		
		var validator = $("#form-edit-PTAPResultTable-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strReservorioMenacho: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    regexp: {
	                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
	                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
	                    }
					}
				},
				strReservorioVicentelo: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    regexp: {
	                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
	                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
	                    }
					}
				},
				strReservorioVicenteloRS: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    regexp: {
	                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
	                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
	                    }
					}
				},
				strCamraRegulacion: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    regexp: {
	                        regexp: /^\d*(\.\d{1})?\d{0,1}$/,
	                        message: "Debe ser un n\u00famero de m\u00e1ximo 2 decimales"
	                    }
					}
				}
			}
		})	
		.on('success.form.bv', function(e, data) {
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoPTAPResultTableForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-PTAPResultTable-id').attr('action'),
				data: $('#form-edit-PTAPResultTable-id').serialize(),
				success: function(result){
					$nuevoPTAPResultTableForm.bootstrapValidator('resetForm', true);
					$nuevoPTAPResultTableForm[0].reset();
					$('.container_save').html(result);
				},
				complete: function() {
					$("#guardarButton").html('Guardar');
					$('#guardarButton').removeAttr("disabled");
					
				}
			});
		});	
	}
	
	function showToastReporteAlmacenamientoPTAP(mensajeTipo, mensajeError) {

		//Mensajes despues de grabar
		if(mensajeTipo==='grabadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente guardado");
			 $('#modal-registra-almacenamientoPTAP').modal('hide');
			 $('#idBtnBuscarAlmacenamientoPTAP').click();
		 }
		if(mensajeTipo==='actualizadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente actualizado");
			 $('#modal-registra-almacenamientoPTAP').modal('hide');
			 $('#idBtnBuscarAlmacenamientoPTAP').click();
		 }
		 if(mensajeTipo==='grabadoNoOk'){
			 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 }
		 //Mensajes despues de eliminar
		 if(mensajeTipo==='inactivadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente eliminado");
			 $('#modal-registra-almacenamientoPTAP').modal('hide');
			 $('#idBtnBuscarAlmacenamientoPTAP').click();
		 }
		 if(mensajeTipo==='inactivadoNoOk'){
			 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
		 }
		 if(mensajeTipo==='grabadoOkSubForm'){
			 toastr["success"](mensajeError, "Registro agregado correctamente");
			 $('#modal-registra-PTAPResultTableEdit').modal('hide');
			 $('#myTablePTAPResult').DataTable().ajax.reload();
		 }
		
	}
	
	$("#modal-registra-almacenamientoPTAP").on("keypress",'#strObsInterpelacion', function(event){
		let kCode = event.keyCode || event.which;
	    if (kCode == 13) {
	        //event.preventDefault();
	        return false;   
	    }
	});

	$("#modal-registra-almacenamientoPTAP").on("drop",'#strObsInterpelacion', function(event){
		return false;
	});
	 
