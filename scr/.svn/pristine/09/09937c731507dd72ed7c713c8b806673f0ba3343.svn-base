(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.ANALISIS");
	//let module = MYAPPL.LOGIC.MANIFIESTO;
	
	module.configDoc = function($codigo, $page, $anio, $tipo, $tipArchDocumento, $tamMaxArchDocumento){
		let $doc = {
			id : $page,
			anhoDocumento : $anio,
			tipoDocumento : $tipo,
			codDocumento : $codigo,
			tipArchDocumento : $tipArchDocumento,
			tamMaxArchDocumento : $tamMaxArchDocumento,
			secciones : [
				{
					id : 'upload_files_analisis',
					cod: 1,
					codCantMax: 200
				}
			]
		};
		return $doc;
	}
	
	module.initUpload = function() {
		let $page = nsSAA.obtenerIdPagina();
		let $codigo = $("#codigo").val();
		let $anio = '2017';
		let $tipo = $("#codigoTipo").val();
		let $add = true; //($("#editar").val() == "true");
		let $tipArchDocumento = $("#tipArchDocumento").val();
		let $tamMaxArchDocumento = $("#tamMaxArchDocumento").val();
		let $doc = module.configDoc($codigo, $page, $anio, $tipo, $tipArchDocumento, $tamMaxArchDocumento);
		nsSAA.configurarGlobal($add, $doc);
		$("#page").val($page);
	};

	
})(jQuery);	

	$(document).ready(function() {
		
		$(document).on('dp.change', '#strFechaMuestreo', function() { 
			$('#form-edit-analisisBacteriologico').bootstrapValidator('revalidateField', $("#strFechaMuestreo"));
		});
		
		//MANEJO DEL FORMULARIO DE BUSQUEDA
		$('#idBtnBuscarAnalisisBacteriologico').click(function () {		 
			 $('#myTableAnalisisBacteriologico').DataTable().ajax.reload();
		});
		
		$('#idFechaMuestreo').datetimepicker({
		      //  format: 'DD/MM/YYYY HH:mm:SS',
				format: 'DD/MM/YYYY',
		        ignoreReadonly: false,
		        date: null,
		        maxDate : new Date(),
		        locale: "es"
		});
		
	});
	
	var idCabecera;
	function modificarAnalisisBacteriologico(id) {
		idCabecera = id;
		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'analisisBacteriologicoGet/'+id, 
			success: function(result) {
				$('#id-modal-content').replaceWith(result);						
				
				cargarCombo("listPersonaMuestrea","#strMuestreador");
				cargarCombo("listEspecialistas","#strAnalista");
				
				if(id != -1){
					$("#idDetalleAnalisisBactereologico").show();
					$('#idModalTitle').html($('#idValEditModalTitle').html());			
						
				}else{
					$("#idDetalleAnalisisBactereologico").hide();
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
					
				
					
				}
				
				var retorno = false;
				//loadDateOnInput('#strFechaMuestreo');
				setTimeout(function(){
					
					$('#myTableMuestraFirst tbody tr').each(function () {
						var dil = $(this).find("td").eq(2).html();
						var desc = $(this).find("td").eq(1).html();
						
						//vericamos si el registro tiene data para deplicarse
						if(desc != 'DUPLICADO' && (dil == "" || dil == undefined)){							
							retorno = true;
							return false;
						}						
					});
					
					if(retorno){			
						$('#idBtnFirst').attr("disabled", true);
					}else{
						$('#idBtnFirst').attr("disabled", false);
					}
					
				}, 500);
				
				var retornoSecond = false;
				setTimeout(function(){
					
					$('#myTableMuestraSecond tbody tr').each(function () {
						var cl2res = $(this).find("td").eq(2).html();
						var desc = $(this).find("td").eq(1).html();
						
						//vericamos si el registro tiene data para deplicarse
						if(desc != 'DUPLICADO' && desc != 'BLANCO' && (cl2res == "" || cl2res == undefined)){							
							retornoSecond = true;
							return false;
						}						
					});
					
					if(retornoSecond){			
						$('#idBtnSecond').attr("disabled", true);
					}else{
						$('#idBtnSecond').attr("disabled", false);
					}
					
				}, 500);
				
				enableValidationRules();
			},complete: function() {
				$('#modal-registra-analisisBacteriologico').modal('show');
				
				$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
				
				
				
				
							
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
						
						
						
						
					} else{
						
						$('#strFechaRecepcionMuestra').datetimepicker({
						      //  format: 'DD/MM/YYYY HH:mm:SS',
								format: 'DD/MM/YYYY',
						        ignoreReadonly: false,
						        date: new Date(),
						        locale: "es",
						        maxDate : 'now'
						});
						
						$('#strFechaInicioEnsayo').datetimepicker({
						      //  format: 'DD/MM/YYYY HH:mm:SS',
								format: 'DD/MM/YYYY',
						        ignoreReadonly: false,
						        date: new Date(),
						        locale: "es",
						        maxDate : 'now'
						});
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
					}
					
					loadDateOnInput("#strFechaMuestreo");
					loadDateOnInput("#strFechaRecepcionMuestra");
					loadDateOnInput("#strFechaInicioEnsayo");
					loadHourOnInput("#strHoraMuestreo");
					loadHourOnInput("#strHoraRecepcionMuestra");
					loadHourOnInput("#strHoraInicioEnsayo");
				}, 500);
				
				
				
				$.unblockUI();
				
			}
		});
	}
	

	function loadHourOnInput(idInput){
		var valInput = $(idInput).val();
		console.log(idInput + " : " + valInput)
		$(idInput).datetimepicker({
			format: 'HH:mm',
		    ignoreReadonly: false,
		    date: null,
		    locale: "es"
		}) .on('changeDate', function(e) {
		});
		
		if(valInput == null || valInput == undefined || valInput == ""){
			valInput = getHhmm();
		}
		$(idInput).val(valInput);
		$(idInput).attr("value",valInput);
		$(idInput).prop("value",valInput);
	}
	
	//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE CREACION O EDICION
	function enableValidationRules() {
		
		$('input[name="strHoraMuestreo"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-analisisBacteriologico-id").bootstrapValidator('revalidateField', 'strHoraMuestreo');
		});
		
		$('input[name="strHoraRecepcionMuestra"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-analisisBacteriologico-id").bootstrapValidator('revalidateField', 'strHoraRecepcionMuestra');
		});
		
		$('input[name="strHoraInicioEnsayo"]').on('dp.change dp.show dp.hide', function(e) {	
			$("#form-edit-analisisBacteriologico-id").bootstrapValidator('revalidateField', 'strHoraInicioEnsayo');
		});
		
		var validator = $("#form-edit-analisisBacteriologico-id").bootstrapValidator({
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
	                        message: 'Ingrese código de equipo'
	                    }
					}
				},
				strFechaMuestreo: {
					autoFocus: false,
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese fecha de muestreo'
	                    }
					}
				},
				
				strHoraMuestreo: {
					autoFocus: false,
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese hora de muestreo'
	                    }
					}
				},
				
				/*strTurno: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese turno'
	                    }
					}
				},
				
				strFechaRecepcionMuestra: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese fecha de recepci\u00F3n'
	                    }
					}
				},
				
				strHoraRecepcionMuestra: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese hora de recepci\u00F3n'
	                    }
					}
				},
				
				strMuestreador: {
					validators : {
						  callback: {
		                      message: 'Elegir valor para muestreador',
		                      callback: function(value, validator, $field) {
		                          var options = validator.getFieldElements('strMuestreador').val();
		                          console.log("valor strMuestreador: " + options)
		                          return (options != null && options != undefined && options!=0 && options!="0");
		                      }
		                  },			
						notEmpty: {
	                        message: 'Ingrese muestreador'
	                    }
					}
				},
				
				strFechaInicioEnsayo: {
					validators : {
										
						notEmpty: {
	                        message: 'Ingrese fecha de ensayo'
	                    }
					}
				},
				
				strHoraInicioEnsayo: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese hora de ensayo'
	                    }
					}
				},
				
				strAnalista: {
					validators : {
						  callback: {
		                      message: 'Elegir valor para Analista',
		                      callback: function(value, validator, $field) {
		                          var options = validator.getFieldElements('strAnalista').val();
		                          console.log("valor strAnalista: " + options)
		                          return (options != null && options != undefined && options!=0 && options!="0");
		                      }
		                  }
					}
				},
				
				strObservacion: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese observaci\u00F3n'
	                    }
					}
				}*/
				
			}
		})	
		.on('success.form.bv', function(e, data) {
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoAnalisisBacteriologicoForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-analisisBacteriologico-id').attr('action'),
				data: $('#form-edit-analisisBacteriologico-id').serialize(),
				success: function(result){
					$nuevoAnalisisBacteriologicoForm.bootstrapValidator('resetForm', true);
					$nuevoAnalisisBacteriologicoForm[0].reset();
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
	function inactivarAnalisisBacteriologico(id) {
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
				    	   url: $('#form-inactiva-analisisBacteriologico').attr('action'),
				    	   data: $('#form-inactiva-analisisBacteriologico').serialize(),  
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
	
	function modificarMuestraFirst(idDetalle) {

		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'muestraFirstGet/'+idDetalle +'/' + idCabecera, 
			success: function(result) {
				$("#id-modal-content-first").html("");
				$('#id-modal-content-first').replaceWith(result);			
				
				if(idDetalle != -1){
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				enableValidationFirstRules();
			},complete: function() {
				$('#modal-registra-muestraFirst').modal('show');
										
				$.unblockUI();
				
			}
		});
	}

	function enableValidationFirstRules() {
				
		var validator = $("#form-edit-muestraFirst-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
					
				strTurbntu : {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTurbntu").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string	                        				
		                        	if(numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strDil: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strDil").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string	                        				
		                        	if(numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strTclt24: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTclt24").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strTclt48: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTclt48").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strTclv24: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTclv24").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strTclv48: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTclv48").value; //ID numero puede ser un input text.
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strTuec24: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTuec24").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgarml: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgarml").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgardl: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgardl").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strColaga: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColaga").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
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
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoMuestraFirstForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-muestraFirst-id').attr('action'),
				data: $('#form-edit-muestraFirst-id').serialize(),
				success: function(result){
					$nuevoMuestraFirstForm.bootstrapValidator('resetForm', true);
					$nuevoMuestraFirstForm[0].reset();
					$('.container_save').html(result);
				},
				complete: function() {
					$("#guardarButton").html('Guardar');
					$('#guardarButton').removeAttr("disabled");
					
				}
			});
		});	
	}
	
	function addDuplicadoMuestraFirst(idDetalle) {
		var retorno = 0;
		$('#myTableMuestraFirst tr').each(function () {
			var idDetalleDupl;
			var descripcion = $(this).find("td").eq(1).html();
			var dil = $(this).find("td").eq(3).html();
			var id = $(this).find("td").eq(12).html();
			
			//vericamos si el registro tiene data para deplicarse
			if(id == idDetalle){
				if(dil == "" || dil == undefined){				
					retorno = 2;
					return false;
				}
			}else if(descripcion == 'DUPLICADO' && (dil == "" || dil == undefined)){
				idDetalleDupl   = $(this).find("td").eq(12).html();	

				$.ajax({
					type: 'GET',
					url: 'addDuplicadoMuestraFirst/'+idDetalle +'/' + idCabecera  +'/' + idDetalleDupl, 
					success: function(result) {	
						
						$('#myTableMuestraFirst').DataTable().ajax.reload();
					},complete: function() {					
						
					}
				});
				retorno=0;
				return false;
			}else{
				retorno=1;
				
			}
			
		});
		
		if(retorno == 1){
			showToastAnalisisBacteriologico("maximoSelect", "");
			$('#myTableMuestraFirst').DataTable().ajax.reload();
		}
		if(retorno == 2){
			showToastAnalisisBacteriologico("registroSinData", "");
			$('#myTableMuestraFirst').DataTable().ajax.reload();
		}
		
	}
	
	function deleteDuplicadoMuestraFirst(idDetalle) {				

		$.ajax({
			type: 'GET',
			url: 'deleteDuplicadoMuestraFirst/'+idDetalle +'/' + idCabecera, 
			success: function(result) {	
				
				$('#myTableMuestraFirst').DataTable().ajax.reload();
				
			},complete: function() {					
				
			}
		});		
		
	}
	
	function modificarMuestraSecond(idDetalle) {

		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'muestraSecondGet/'+idDetalle +'/' + idCabecera, 
			success: function(result) {
				$("#id-modal-content-second").html("");
				$('#id-modal-content-second').replaceWith(result);			
				
				if(idDetalle != -1){
	
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				enableValidationSecondRules();
			},complete: function() {
				$('#modal-registra-muestraSecond').modal('show');
										
				$.unblockUI();
				
			}
		});
	}
	
	function enableValidationSecondRules() {

		var validator = $("#form-edit-muestraSecond-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strCl2res: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strCl2res").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgarel: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgarel").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgele: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgele").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strGamfc: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strGamfc").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgmfc: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgmfc").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgarml: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgarml").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strAgardl: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor debe ser menor que 10',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgardl").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
		                        	}
	                        	}
	                            return true;
	                        }
	                    }
					}
				},
				strCoagar: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strCoagar").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if( numero.match(/^-?[0-9]+(\.[0-9])?$/) )
		                        	{
		                        		return true;
		                        	}
		                        	else
		                        	{
		                        		return false;
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
			$("#guardarButton").html('Guardando...');
			$("#guardarButton").attr('disabled', 'disabled');
			e.preventDefault();
			var $nuevoMuestraSecondForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-muestraSecond-id').attr('action'),
				data: $('#form-edit-muestraSecond-id').serialize(),
				success: function(result){
					$nuevoMuestraSecondForm.bootstrapValidator('resetForm', true);
					$nuevoMuestraSecondForm[0].reset();
					$('.container_save').html(result);
				},
				complete: function() {
					$("#guardarButton").html('Guardar');
					$('#guardarButton').removeAttr("disabled");
					
				}
			});
		});	
	}
	
	
	function addDuplicadoMuestraSecond(idDetalle) {
		var retorno = 0;
		$('#myTableMuestraSecond tr').each(function () {
			var idDetalleDupl;
			var descripcion = $(this).find("td").eq(1).html();
			var clres = $(this).find("td").eq(3).html();
			var id = $(this).find("td").eq(11).html();
			
			//vericamos si el registro tiene data para deplicarse
			if(id == idDetalle){
				if(clres == "" || clres == undefined){				
					retorno = 2;
					return false;
				}
			}else if(descripcion == 'DUPLICADO' && (clres == "" || clres == undefined)){
				idDetalleDupl   = $(this).find("td").eq(11).html();	

				$.ajax({
					type: 'GET',
					url: 'addDuplicadoMuestraSecond/'+idDetalle +'/' + idCabecera  +'/' + idDetalleDupl, 
					success: function(result) {	
						
						$('#myTableMuestraSecond').DataTable().ajax.reload();
					},complete: function() {					
						
					}
				});
				retorno=0;
				return false;
			}else{
				retorno=1;
				
			}
			
		});
		
		if(retorno == 1){
			showToastAnalisisBacteriologico("maximoSelect", "");
			$('#myTableMuestraSecond').DataTable().ajax.reload();
		}
		if(retorno == 2){
			showToastAnalisisBacteriologico("registroSinData", "");
			$('#myTableMuestraSecond').DataTable().ajax.reload();
		}
		
	}
	
	function deleteDuplicadoMuestraSecond(idDetalle) {				

		$.ajax({
			type: 'GET',
			url: 'deleteDuplicadoMuestraSecond/'+idDetalle +'/' + idCabecera, 
			success: function(result) {	
				
				$('#myTableMuestraSecond').DataTable().ajax.reload();
				
			},complete: function() {					
				
			}
		});		
		
	}
	

	function calcularMuestraFirst() {

		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: 'Confirma ejecuci\u00F3n de C\u00E1lculo de Primera Muestra?',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
			        	$('#idCabecera').val(idCabecera); 
					    $.ajax({
				    	   type: 'POST',
				    	   url: $('#form-calculo-muestraFirst').attr('action'),
				    	   data: $('#form-calculo-muestraFirst').serialize(),  
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
	
	function calcularMuestraSecond() {

		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: 'Confirma ejecuci\u00F3n de C\u00E1lculo de Segunda Muestra?',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
			        	$('#idCabecera2').val(idCabecera); 
					    $.ajax({
				    	   type: 'POST',
				    	   url: $('#form-calculo-muestraSecond').attr('action'),
				    	   data: $('#form-calculo-muestraSecond').serialize(),  
				    	   success: function(result){  	
				    		   
				    	       $('.container_save').html(result);		    	      
					    	   },
								complete: function() {
									
								}
					    	}); 
			        	}
			        },
			        Cancelar: {
			        }
			    }
			});	   
	}
	
	function loadDateOnInput(idInput){
		var valInput = $(idInput).val();
		console.log(idInput + " : " + valInput)
		$(idInput).datetimepicker({
			format: 'DD/MM/YYYY',
	        ignoreReadonly: true,
	        date: null,
	        locale: "es",
	        maxDate: new Date()
		}) .on('changeDate', function(e) {
		});
		
		if(valInput == null || valInput == undefined || valInput == ""){
			valInput = getDDMMYYYY();
		}
		
		$(idInput).val(valInput);
		$(idInput).attr("value",valInput);
		$(idInput).prop("value",valInput);
	}
	
	
	function showToastAnalisisBacteriologico(mensajeTipo, mensajeError) {

		//Mensajes despues de grabar
		if(mensajeTipo==='grabadoOk'){
			 toastr["success"](mensajeError, "Registro correctamente guardado");
			 $('#modal-registra-analisisBacteriologico').modal('hide');
			 $('#idBtnBuscarAnalisisBacteriologico').click();
		 }if(mensajeTipo==='actualizadoOk'){
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
		 
		 if(mensajeTipo==='borradoOk'){
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
			 toastr["success"](mensajeError, "Registro agregado correctamente");
			 $('#myTableAnalisisBacteriologicoDetalle').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='actualizadoOkSubFirstForm'){
				toastr["success"](mensajeError, "Se actualizó el registro correctamente");
			    $('#modal-registra-muestraFirst').modal('hide');
				$('#myTableMuestraFirst').DataTable().ajax.reload();
			 
				var retorno = false;
				setTimeout(function(){
					
					$('#myTableMuestraFirst tbody tr').each(function () {
						
	
						var dil = $(this).find("td").eq(2).html();
						var desc = $(this).find("td").eq(1).html();
						
						//vericamos si el registro tiene data para deplicarse
						if(desc != 'DUPLICADO' && (dil == "" || dil == undefined)){
							
							retorno = true;
							return false;
						}
						
					});
					
					if(retorno){			
						$('#idBtnFirst').attr("disabled", true);
					}else{
						$('#idBtnFirst').attr("disabled", false);
					}
					
				}, 500);				
			 
		 }
		 if(mensajeTipo==='actualizadoOkSubSecondForm'){
			 toastr["success"](mensajeError, "Se actualizó el registro correctamente");
			 $('#modal-registra-muestraSecond').modal('hide');
			 $('#myTableMuestraSecond').DataTable().ajax.reload();
			 
			 var retornoSecond = false;
			 setTimeout(function(){
					
					$('#myTableMuestraSecond tbody tr').each(function () {
						var cl2res = $(this).find("td").eq(2).html();
						var desc = $(this).find("td").eq(1).html();
						
						//vericamos si el registro tiene data para deplicarse
						if(desc != 'DUPLICADO' && desc != 'BLANCO' && (cl2res == "" || cl2res == undefined)){							
							retornoSecond = true;
							return false;
						}						
					});
					
					if(retornoSecond){			
						$('#idBtnSecond').attr("disabled", true);
					}else{
						$('#idBtnSecond').attr("disabled", false);
					}
					
				}, 500);
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
		 if(mensajeTipo==='calculoErrAgarML'){
			 toastr["error"](mensajeError, "Campo 'AGAR ml' no debe ser cero, verificar.");
		 }
		 if(mensajeTipo==='calculoErrObtDiluMenor'){
			 toastr["error"](mensajeError, "Hubo un error al obtener la diluci\u00F3n menor, verificar.");
		 }
		 if(mensajeTipo==='calculoErrCalcPotDisol'){
			 toastr["error"](mensajeError, "Hubo un error al calcular la potencia de la diluci\u00F3n, verificar.");
		 }
		 if(mensajeTipo==='maximoSelect'){				
			 toastr["error"](mensajeError, "Los registro de DUPLICADO ya se realizaron");
		 }if(mensajeTipo==='registroSinData'){				
			 toastr["error"](mensajeError, "Registro no contiene data para duplicarse");
		 }
		 if(mensajeTipo==='calculoErrNumAlto'){				
			 toastr["error"](mensajeError, "Uno de los resultados del c\u00E1lculo sobrepasa la cantidad de caracteres permitida");
		 }		 
		 if(mensajeTipo==='calculoColifTot'){
			 toastr["error"](mensajeError, "Hubo un error al calcular el Colifor. Totales NMP/100 mL");
		 }
		 if(mensajeTipo==='calculoColifTermot'){
			 toastr["error"](mensajeError, "Hubo un error al calcular el Colifor. Termot. NMP/100 mL");
		 }
		 if(mensajeTipo==='calculoBacterHeterot'){
			 toastr["error"](mensajeError, "Hubo un error al calcular el Bacter. Heterot. UFC/mL");
		 }
	}
	
	
	
	$("#modal-registra-analisisBacteriologico").on("keypress",'#strObservacion', function(event){
		
		let kCode = event.keyCode || event.which;
	    if (kCode == 13) {
	        return false;   
	    }
	});

	$("#modal-registra-analisisBacteriologico").on("drop",'#strObservacion', function(event){
		return false;
	});
	

	
