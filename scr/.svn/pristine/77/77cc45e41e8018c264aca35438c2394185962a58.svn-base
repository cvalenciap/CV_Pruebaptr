
	$(document).ready(function() {
		
		
		
	});
	
	function modificarReporteEnsayoBacteriologico(id) {
		idCabecera = id;
		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'reporteEnsayoBacteriologico/'+id, 
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
					$('#idModalTitle').html($('#idValEditModalTitle').html());
					
				}else{					
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
				$('#modal-registra-analisisBacteriologico').modal('show');
				

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

					$('#strMuestreador').val($('#strMuestreadorHide').val());
					$('#strAnalista').val($('#strAnalistaHide').val());
				}, 500);
				
				$.unblockUI();
				
			}
		});
	}
		
	function showToastAnalisisBacteriologico(mensajeTipo, mensajeError) {

		//Mensajes despues de grabar
		if(mensajeTipo==='grabadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente guardado");
			 $('#modal-registra-analisisBacteriologico').modal('hide');
			 $('#idBtnBuscarAnalisisBacteriologico').click();
		 }
		if(mensajeTipo==='actualizadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente actualizado");
			 $('#modal-registra-analisisBacteriologico').modal('hide');
			 $('#idBtnBuscarAnalisisBacteriologico').click();
		 }
		 if(mensajeTipo==='grabadoNoOk'){
			 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
		 }
		 //Mensajes despues de eliminar
		 if(mensajeTipo==='inactivadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente eliminado");
			 $('#modal-registra-analisisBacteriologico').modal('hide');
			 $('#idBtnBuscarAnalisisBacteriologico').click();
		 }
		 if(mensajeTipo==='inactivadoNoOk'){
			 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue eliminado");
		 }
		 if(mensajeTipo==='RegDuplicado'){
			 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
			 //$('#modal-registra-analisisBacteriologico').modal('hide');
			 //$('#idBtnBuscarAnalisisBacteriologico').click();
		 }
		 if(mensajeTipo==='grabadoOkSubForm'){
			 toastr["success"](mensajeError, "Registro agregado correctamente");
			 $('#modal-registra-analisisBacteriologico-detalle-edit').modal('hide');
			 $('#myTableAnalisisBacteriologicoDetalle').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='inactivadoSubFormOk'){
			 toastr["success"](mensajeError, "Registro eliminado correctamente");
			 $('#myTableAnalisisBacteriologicoDetalle').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='actualizadoOkSubFirstForm'){
			 toastr["success"](mensajeError, "Se actualizó el registro correctamente");
			 $('#modal-registra-muestraFirst').modal('hide');
			 $('#myTableMuestraFirst').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='actualizadoOkSubSecondForm'){
			 toastr["success"](mensajeError, "Se actualizó el registro correctamente");
			 $('#modal-registra-muestraSecond').modal('hide');
			 $('#myTableMuestraSecond').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='calculoKO'){
			 toastr["error"](mensajeError, "Hubo un error al calcular, comunicar al administrador.");
		 }
		 if(mensajeTipo==='calculoFirstOK'){
			
			 $('#myTableMuestraResultFirst').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='calculoSecondOK'){
				
			 $('#myTableMuestraResultSecond').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='calculoErrDilucion'){
			 toastr["error"](mensajeError, "Hubo un error en las diluciones, falta ingresar o un dato no cooincide, verificar.");
		 }
		 if(mensajeTipo==='calculoErrTubosclvbb'){
			 toastr["error"](mensajeError, "Hubo un error con los Tubos CLVBB al calcular, verificar.");
		 }
		 if(mensajeTipo==='calculoErrTubosec'){
			 toastr["error"](mensajeError, "Hubo un error con los Tubos EC al calcular, verificar.");
		 }
		 if(mensajeTipo==='maximoSelect'){				
			 toastr["error"](mensajeError, "Los registro de DUPLICADO ya se realizaron");
		 }if(mensajeTipo==='registroSinData'){				
			 toastr["error"](mensajeError, "Registro no contiene data para duplicarse");
		 }
		 
		 
	}
	
	$("#modal-registra-analisisBacteriologico").on("keypress",'#strInterperlacionResult', function(event){
		
		let kCode = event.keyCode || event.which;
	    if (kCode == 13) {
	        return false;   
	    }
	});

	$("#modal-registra-analisisBacteriologico").on("drop",'#strInterperlacionResult', function(event){
		return false;
	});
	 
