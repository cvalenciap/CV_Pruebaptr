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
		
		//MANEJO DEL FORMULARIO DE BUSQUEDA
		$('#idBtnBuscarAlmacenamientoPTAP').click(function () {		 
			 $('#myTableAlmacenamientoPTAP').DataTable().ajax.reload();
		});
		
		$('#idFechaMuestreo').datetimepicker({
		      //  format: 'DD/MM/YYYY HH:mm:SS',
				format: 'DD/MM/YYYY',
		        ignoreReadonly: false,
		        maxDate: new Date(),
		        date: null,
		        locale: "es"
		});
				
		
	});
	
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


	
	$("#modal-registra-almacenamientoPTAP").on("keypress",'#strObservacion', function(event){
		
		console.log("area espacio")
		let kCode = event.keyCode || event.which;
	    if (kCode == 13) {
	        //event.preventDefault();
	        return false;   
	    }
	});
	

	$("#modal-registra-almacenamientoPTAP").on("drop",'#strObservacion', function(event){
		return false;
	});
	
	var idCabecera;
	function modificarAlmacenamientoPTAP(id) {
	
		idCabecera = id;
		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'almacenamientoPTAPGet/'+id, 
			success: function(result) {
				$('#id-modal-content').replaceWith(result);						
				
				cargarCombo("listPersonaMuestrea","#strMuestreador");
				cargarCombo("listEspecialistas","#strAnalista");
				console.log(" modificarAlmacenamientoPTAP 1 ")			
				if(id != -1){					
					$("#idDetalleAlmacenamientoPTAP").show();
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{					
					$("#idDetalleAlmacenamientoPTAP").hide();
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				
				enableValidationRules();
			},complete: function() {
				$('#modal-registra-almacenamientoPTAP').modal('show');
				
				
				
				
				console.log(" modificarAlmacenamientoPTAP 2 ")
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
						$("#bolCumplimientoLBD1").prop("checked", true); //cambia el id del checkbox para que funcione
				}
				
				if($('#intMantConexionesHide').val()==1){
						$("#bolMantConexiones1").prop("checked", true);
				}
				
				setTimeout(function(){
						console.log(" modificarAlmacenamientoPTAP 3 ")
						$('#strMuestreador').val($('#strMuestreadorHide').val());
						$('#strAnalista').val($('#strAnalistaHide').val());
					
						console.log(" fecha muestreo " + $('#strFechaMuestreo').val())
						
						loadDateOnInput('#strFechaMuestreo');
						loadDateOnInput('#strFechaRecepcionMuestra');
						loadDateOnInput('#strFechaInicioEnsayo');
						loadDateOnInput('#strFechaMuestreo');
						loadDateOnInput('#strFechaRecepcionMuestra');
						loadDateOnInput('#strFechaInicioEnsayo');
						
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
				},
				strFechaMuestreo: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese Fecha de Muestreo'
	                    }
					}
				},
				strHoraMuestreo: {
					validators : {
											
						notEmpty: {
	                        message: 'Ingrese Hora de Muestreo'
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
	
	function modificarTableFirst(idDetalle) {

		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'tableFirstGet/'+idDetalle +'/' + idCabecera, 
			success: function(result) {
				$("#id-modal-content-tableFirst").html("");
				$('#id-modal-content-tableFirst').replaceWith(result);			
				
				if(idDetalle != -1){
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				enableValidationFirstRules();
			},complete: function() {
				$('#modal-registra-tableFirst').modal('show');
										
				$.unblockUI();
				
			}
		});
	}

	function enableValidationFirstRules() {
				
		var validator = $("#form-edit-tableFirst-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strTurntu: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strTurntu").value; //ID numero puede ser un input text.

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
				strClrlib: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strClrlib").value; //ID numero puede ser un input text.
	                        	
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
				strAgelfi: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgelfi").value; //ID numero puede ser un input text.
	                        	
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
				strCoagel: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strCoagel").value; //ID numero puede ser un input text.

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
				strAgmfcv: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgmfcv").value; //ID numero puede ser un input text.

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
				strColamf: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColamf").value; //ID numero puede ser un input text.

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
				strAg2aml: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAg2aml").value; //ID numero puede ser un input text.

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
				strAg2adl: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAg2adl").value; //ID numero puede ser un input text.

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
				strCola2a: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strCola2a").value; //ID numero puede ser un input text.

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
				strAgmpvf: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAgmpvf").value; //ID numero puede ser un input text.

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
				strColapa: {
					validators : {					
	                    
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColapa").value; //ID numero puede ser un input text.

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
			var $nuevoTableFirstForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-tableFirst-id').attr('action'),
				data: $('#form-edit-tableFirst-id').serialize(),
				success: function(result){
					$nuevoTableFirstForm.bootstrapValidator('resetForm', true);
					$nuevoTableFirstForm[0].reset();
					$('.container_save').html(result);					
				},
				complete: function() {
					$("#guardarButton").html('Guardar');
					$('#guardarButton').removeAttr("disabled");
					
				}
			});
		});	
	}
	
	
	
	function modificarTableSecond(idDetalle) {

		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'tableSecondGet/'+idDetalle +'/' + idCabecera, 
			success: function(result) {
				$("#id-modal-content-tableSecond").html("");
				$('#id-modal-content-tableSecond').replaceWith(result);			
								
				cargarCombo("listPuntoMuestreoSeleccione","#strDescMuestra");
				
				if(idDetalle != -1){
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				enableValidationSecondRules();
			},complete: function() {
				$('#modal-registra-tableSecond').modal('show');
										
				$.unblockUI();
				
			}
		});
	}
	
	function enableValidationSecondRules() {

		var validator = $("#form-edit-tableSecond-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strColoni: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColoni").value; //ID numero puede ser un input text.
	                        	
	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strGasclt: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strGasclt").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strGasclv: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strGasclv").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strGsec24: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strGsec24").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strFecmug: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strFecmug").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strColtot: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColtot").value; //ID numero puede ser un input text.

	                        	if(numero != undefined && numero != ''){
		                        	//numero ahora es string
		                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
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
				strColter: {
					validators : {					
	                   
						
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColter").value; //ID numero puede ser un input text.

	                        	//numero ahora es string

	                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
	                            return false;
	                        }
	                    }
					}
				},
				strEscoli: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strEscoli").value; //ID numero puede ser un input text.

	                        	//numero ahora es string

	                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
	                            return false;
	                        }
	                    }
					}
				},
				strDescMuestra: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strDescMuestra").value; //ID numero puede ser un input text.

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
			var $nuevoTableSecondForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-tableSecond-id').attr('action'),
				data: $('#form-edit-tableSecond-id').serialize(),
				success: function(result){
					$nuevoTableSecondForm.bootstrapValidator('resetForm', true);
					$nuevoTableSecondForm[0].reset();
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
	function inactivarTableSecond(id) {
		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: 'Confirma eliminar registro?',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
			        	$('#inacIdDetalle2').val(id); 
					    $.ajax({
				    	   type: 'POST',
				    	   url: $('#form-inactiva-tableSecond').attr('action'),
				    	   data: $('#form-inactiva-tableSecond').serialize(),  
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
	
	function modificarTableThree(idDetalle) {

		MYAPPL.blockPageLoad();
		$.ajax({
			type: 'GET',
			url: 'tableThreeGet/'+idDetalle +'/' + idCabecera, 
			success: function(result) {
				$("#id-modal-content-tableThree").html("");
				$('#id-modal-content-tableThree').replaceWith(result);			
								
				cargarCombo("listPuntoMuestreoSeleccione","#strDescMuest");
				
				if(idDetalle != -1){
					$('#idModalTitle').html($('#idValEditModalTitle').html());
				}else{
					$('#idModalTitle').html($('#idValCrearModalTitle').html());
				}
				enableValidationThreeRules();
			},complete: function() {
				$('#modal-registra-tableThree').modal('show');
										
				$.unblockUI();
				
			}
		});
	}
	
	function enableValidationThreeRules() {

		var validator = $("#form-edit-tableThree-id").bootstrapValidator({
			message: 'El valor no es v\u00e1lido',
			excluded: [':disabled', ':hidden', '#strParaAdd', '#strCcAdd'],
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphico-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {			
				strColoni: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strColoni").value; //ID numero puede ser un input text.

	                        	//numero ahora es string
	                        	
	                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
	                            return false;
	                        }
	                    }
					}
				},
				strAglech: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strAglech").value; //ID numero puede ser un input text.

	                        	//numero ahora es string

	                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
	                            return false;
	                        }
	                    }
					}
				},
				strPseae: {
					validators : {					
	                   
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strPseae").value; //ID numero puede ser un input text.

	                        	//numero ahora es string

	                        	if(numero=="" || numero.match(/^-?[0-9]+(\.[0-9])?$/) )
	                        	{
	                        		return true;
	                        	}
	                        	else
	                        	{
	                        		return false;
	                        	}
	                            return false;
	                        }
	                    }
					}
				},
				strDescMuest: {
					validators : {					
	                   
						notEmpty: {
	                        message: 'Ingrese valor'
	                    },
	                    callback: {
	                        message: 'Valor incorrecto',
	                        callback: function(value, validator, $field) {
	                        	var numero = document.getElementById("strDescMuest").value; //ID numero puede ser un input text.

	                        	//numero ahora es string
	                        	if(numero != undefined && numero != ''){
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
			var $nuevoTableThreeForm = $(e.target);
			$.ajax({
				type: 'POST',
				url: $('#form-edit-tableThree-id').attr('action'),
				data: $('#form-edit-tableThree-id').serialize(),
				success: function(result){
					$nuevoTableThreeForm.bootstrapValidator('resetForm', true);
					$nuevoTableThreeForm[0].reset();
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
	function inactivarTableThree(id) {
		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: 'Confirma eliminar registro?',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
			        	$('#inacIdDetalle').val(id); 
					    $.ajax({
				    	   type: 'POST',
				    	   url: $('#form-inactiva-tableThree').attr('action'),
				    	   data: $('#form-inactiva-tableThree').serialize(),  
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
	
	function calcularValores(id) {

		$.confirm({
		    title: 'Confirmaci\u00f3n',
		    content: 'Confirma ejecuci\u00F3n de Cálculo?',
		    buttons: {
		    	Aceptar: { 
		        	btnClass: 'btn-blue',
		        	action: function () {
			        	$('#idDetalle').val(id); 
			        	$('#idCabecera').val(idCabecera); 
					    $.ajax({
				    	   type: 'POST',
				    	   url: $('#form-calculo-tableFirst').attr('action'),
				    	   data: $('#form-calculo-tableFirst').serialize(),  
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
	
	function showToastAlmacenamientoPTAP(mensajeTipo, mensajeError) {

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
		 if(mensajeTipo==='RegDuplicado'){
			 toastr["error"](mensajeError, "Registro ya existe en la Tabla");
			 //$('#modal-registra-almacenamientoPTAP').modal('hide');
			 //$('#idBtnBuscarAlmacenamientoPTAP').click();
		 }
		 if(mensajeTipo==='grabadoOkSubForm'){
			 toastr["success"](mensajeError, "Registro agregado correctamente");
			 $('#modal-registra-almacenamientoPTAP-detalle-edit').modal('hide');
			 $('#myTableAlmacenamientoPTAPDetalle').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='inactivadoSubFormOk'){
			 toastr["success"](mensajeError, "Registro agregado correctamente");
			 $('#myTableAlmacenamientoPTAPDetalle').DataTable().ajax.reload();
		 }
		 if(mensajeTipo==='actualizadoOkFirstForm'){
				toastr["success"](mensajeError, "Se actualizó el registro correctamente");
			    $('#modal-registra-tableFirst').modal('hide');
				$('#myTableMuestraFirst').DataTable().ajax.reload();		 
		 }
		 if(mensajeTipo==='grabadoOkSecondForm'){
			 toastr["success"](mensajeError, "Se grabó el registro correctamente");
			 $('#modal-registra-tableSecond').modal('hide');
			 $('#myTableMuestraSecond').DataTable().ajax.reload();
			 			 
		 }
		 if(mensajeTipo==='inactivadoOkSecondForm'){
			 toastr["success"](mensajeError, "Se eliminó el registro correctamente");
			 $('#myTableMuestraSecond').DataTable().ajax.reload();
			 			 
		 }
		 if(mensajeTipo==='grabadoOkThreeForm'){
			 toastr["success"](mensajeError, "Se grabó el registro correctamente");
			 $('#modal-registra-tableThree').modal('hide');
			 $('#myTableMuestraThree').DataTable().ajax.reload();
			 			 
		 }
		 if(mensajeTipo==='inactivadoOkThreeForm'){
			 toastr["success"](mensajeError, "Se eliminó el registro correctamente");
			 $('#myTableMuestraThree').DataTable().ajax.reload();
			 			 
		 }if(mensajeTipo==='calculoFirstOK'){			 
			 $('#myTableMuestraFirst').DataTable().ajax.reload();		
			 toastr["success"](mensajeError, "Ejecución de cálculo OK");
		 }
		 if(mensajeTipo==='calculoErrAGELFI'){
			 toastr["error"](mensajeError, "Error, Campo ''Agar ELES Vol. Filt(mL)'' no debe ser cero");
			 			 
		 }
		 if(mensajeTipo==='calculoErrAG2AML'){
			 toastr["error"](mensajeError, "Error, Campo ''Agar R2-A (mL)'' no debe ser cero");
			 			 
		 }
		 if(mensajeTipo==='calculoErr'){
			 toastr["error"](mensajeError, "Error al cácular");
			 			 
		 }
		
	}
	 
