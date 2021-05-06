(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.PARAM_CAMPO");
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
					id : 'upload_files_param_campo',
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

$('.contenedor-check').on("change",".my_checkbox_cumpleEpp", function() {
console.log("cambiando check")
if($(this).is(":checked")) {
	$(this).val("true");
}else{
	$(this).val("false");
}
});

$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarFormulario').click(function () {

		 $('#pFechaMuestreoHidden').val($('#inputFechaMuestreo').val());
		 $('#myTableFormulario242').DataTable().ajax.reload();  //PARA LLAMAR AL DATATABLE PRINCIPAL A TRAVES DEL ID
	});

	$('#inputFechaMuestreo').datetimepicker({
	        format: 'DD/MM/YYYY',
			//format: 'YYYY-MM-DD HH:mm:ss',
	        ignoreReadonly: false,
	        minDate: new Date(1900, 0, 1),
	        maxDate : 'now', 
//	        date: new Date(),
	        locale: "es"
	    });
	
	
});

var MODE_EDIT = 0 //GRABAR POR DEFAULT

$('#modal-registra-formularioHeader').on('shown.bs.modal', function() {
	$($.fn.dataTable.tables(true)).DataTable().columns.adjust();
})

var idCabecera;
function modificarFormulario(id) {
	console.log("modificando " + id)
	
//	id= -1 Boton Agregar
	idCabecera = id;
	$('#codigoCabecera').val(id); //input definido en la pagina principal
	$.ajax({
		type: 'GET',
		url: 'formulario242Get/'+id, //LLAMA AL CONTROLER Y DEVUELVE UNA PAGINA(PAGUNA HEADER)
		success: function(result) {
			$('#id-modal-content').replaceWith(result); //SE INYECTA EN LA PAGINA PRINCIPAL EL HEADER
			
			cargarCombo("listPersonaMuestrea","#cboPersonaMuestrea");
			cargarCombo("listPersonaRecepcion","#cboPersonaRecepcion");
			if(id != -1){ //EDITAR

				$("#idTableVerificacion").show();
//				$("#strFechaMuestreo").attr('disabled', 'disabled');
//				$("#strFechaRecepcion").attr('disabled', 'disabled');
//				$("#cboPersonaMuestrea").attr('disabled', 'disabled');
//				$("#cboPersonaRecepcion").attr('disabled', 'disabled');
//				$("#observacion").attr('disabled', 'disabled');
				$('#idModalTitle').html($('#idValEditModalTitle').html());
				
				$("#strFechaMuestreo").attr("disabled","disabled")
				
//				$('#strFechaRecepcion').datetimepicker({
//			        format: 'DD/MM/YYYY HH:mm:ss',
//			        ignoreReadonly: false,
//			        locale: "es"
//			    });

				
			}else{
				console.log("ocultando paneld detalle " + id)
				$("#idTableVerificacion").show();
				$("#detalle-242").hide();
//				$('#strFechaMuestreo').datetimepicker({
//			        format: 'DD/MM/YYYY',
//			        ignoreReadonly: false,
//			        date: new Date(),
//			        locale: "es"
//			    });
//				
//				$('#strFechaRecepcion').datetimepicker({
//			        format: 'DD/MM/YYYY HH:mm:ss',
//			        ignoreReadonly: false,
//			        date: new Date(),
//			        locale: "es"
//			    });

			    $("#idTableVerificacion").hide();
				$('#idModalTitle').html($('#idValCrearModalTitle').html());
			}
			enableValidationRules();	
			
		},complete: function() {
			
			$('#modal-registra-formularioHeader').modal('show'); //MUESTRA EN LA PANTALLA PRINCIPAL FORMULARIO HEADER
			if(id != -1){		
			
				setTimeout(function(){
				
					$('#cboPersonaMuestrea').val($('#cboMuestreaHidden').val());
					$('#cboPersonaRecepcion').val($('#cboRecepcionHidden').val());
					
					
				}, 500);

				//$('#codMes').find("[value='" + $('#codMesHide').val() + "']").prop('selected', 'selected');
				//$('#codMes').find("[value='" + $('#codMesHide').val() + "']").attr('selected', 'selected');
				
			}
			setTimeout(function(){
				console.log("CARGANDO CALENDARIOS");
				loadDateOnInput("#strFechaMuestreo");
				loadDateWithHourOnInput("#strFechaRecepcion");
				var valorcheck = $('#cumpleEppHidden').val();
				console.log("valorcheck : " + valorcheck);
				
				$("#checkboxFlagEpp").prop("checked", valorcheck!="0"); //cambia el id del checkbox para que funcione
				$("#checkboxFlagEpp").val(valorcheck);
			},500);
			
			
			$.unblockUI();
		}
	});
	
}

function duplicarRegistro(id,fecha) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma duplicar el registro con la fecha actual\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
	        		//ACCION LUEGO DE CONFIRMAR
	        		if(fecha == getDDMMYYYY()){
	        		   console.log("Ya existe un registro con fecha actual")
	        		   var result = $(mensajeCboExistente());
	        		   $('#container-msg-record-existing').html(result);
	        			
	        	   } else {
	        		
	        		MYAPPL.blockPageLoad();
	        		$('#codigoCabecera').val(id); //input definido en la pagina principal
	        		$.ajax({
	        			type: 'GET',
	        			url: 'formularioDuplicar/'+id, //LLAMA AL CONTROLER Y DEVUELVE UNA PAGINA(PAGUNA HEADER)
	        			success: function(result) {
//	        				var indice = $(result).html().indexOf("2001")
	        				console.log("result: " + $(result).html())
	        				$('#container-msg-record-existing').html(result);
	        				
	        				$('#myTableFormulario242').DataTable().ajax.reload();
	        			},complete: function() {
	        				$.unblockUI();
	        			}
	        		 });
	        		}//FIN ELSE
		          }
		        },
		      Cancelar: {
		        }
		    }
	});
}

function mensajeCboExistente(){
	var msg = '<script>/*<![CDATA[*/  mensajeTipo = "grabadoNoOk";'+
	'    mensajeMostrar = "";'+
	'    mensajeError = "Ya existe un registro con fecha actual";		'+
	'/*]]>*/'+
	'showToastGeneric(mensajeTipo,mensajeMostrar, mensajeError)'+	
	'</script>';	

    return msg;
}



function cambioFlag() {
	var flagEpp = $("#checkboxFlagEpp").is(":checked");
	console.log(">>>>> "+ flagEpp)
	if(flagEpp == true){
		console.log(">>>>>  1 ")
		$(cumpleEppHidden).val("1");
	}else{
		console.log(">>>>>  0")
		$(cumpleEppHidden).val("0");
	}
}



function loadDateWithHourOnInput(idInput){
 
 var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
	 format: 'DD/MM/YYYY HH:mm:ss',
     ignoreReadonly: true,
     date: null,
     locale: "es",
     minDate: new Date(1900, 0, 1),
     maxDate: 'now'
	}).on('dp.change dp.show', function (e) {
        $('#form-edit-turbidimetroDigital-id').bootstrapValidator('revalidateField', 'strFechaRecepcion');
    });
	
	if(valInput == null || valInput == undefined){
		valInput = "";
	}
	
	$(idInput).val(valInput);
}

function loadDateOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: null,
        locale: "es",
        minDate: new Date(1900, 0, 1),
        maxDate: 'now'
	}) .on('dp.change dp.show', function (e) {
        $('#form-edit-turbidimetroDigital-id').bootstrapValidator('revalidateField', 'strFechaMuestreo');
    });
	
	if(valInput == null || valInput == undefined){
		valInput = new Date();
	}
	
	$(idInput).val(valInput);
	$(idInput).attr("value",valInput);
	$(idInput).prop("value",valInput);
	
}

function modificarDetalle() {
	
MODE_EDIT = 0 //GRABAR POR DEFAULT
var X = 0; 
	$.ajax({
		type: 'GET',
		url: 'formularioDetalleGet/'+X,
//		url: 'formularioDetalleGet/'+idCabecera, //LLAMA AL CONTROLER DEVUELVE UNA PAGINA(PAGINA DETALLE)
		success: function(result) {
			$('#id-modal-content-verif').replaceWith(result); //ESA PAGINA SE INYECTA EN LA PAGINA PRINCIPAL
			
			$('#idFormularioHeader').val(idCabecera);			
			
			cargarComboSelectable("listSubactividades","#cboLocalMuestreo");
			cargarComboSelectable(null,"#cboEstacionMuestreo");
			//cargarCombo("listPuntos","#cboEstacionMuestreo");
			
			$('#idModalEditTitle').html($('#idValCrearModalDetalleTitle').html());
			
//			if(idCabecera != 0){
//				$('#idModalEditTitle').html($('#idValEditModalDetalleTitle').html());
//
//			}else{
//				$('#idModalEditTitle').html($('#idValCrearModalDetalleTitle').html());
//			}
			enableValidationRulesVerificacion();								
		},complete: function() {
			 
			$('#modal-registra-detalle').modal('show'); //HACE VISIBLE EN LA PANTALLA PRINCIPAL EL FORMULARIO DETALLE
			$.unblockUI();
			
		}
	});
		
}

function modificarDetalle2(id) {
MODE_EDIT = 1 //EDITAR
	$.ajax({
		type: 'GET',
		url: 'formularioDetalleGet/'+id, //LLAMA AL CONTROLER DEVUELVE UNA PAGINA(PAGINA DETALLE)
		success: function(result) {
			$('#id-modal-content-verif').replaceWith(result); //ESA PAGINA SE INYECTA EN LA PAGINA PRINCIPAL
			
			$('#idFormularioHeader').val(idCabecera);			
			
			cargarComboSelectable("listSubactividades","#cboLocalMuestreo");
			//cargarComboSelectable("listPuntos","#cboEstacionMuestreo");
			
			$('#idModalEditTitle').html($('#idValEditModalDetalleTitle').html());
			
//			if(idCabecera != 0){
//				$('#idModalEditTitle').html($('#idValEditModalDetalleTitle').html());
//
//			}else{
//				$('#idModalEditTitle').html($('#idValCrearModalDetalleTitle').html());
//			}
			enableValidationRulesVerificacion();								
		},complete: function() {
			
			setTimeout(function(){
				
				$('#cboLocalMuestreo').val($('#cboLocalidadHidden').val());
				$('#cboEstacionMuestreo').val($('#cboEstacionHidden').val());
				
				changeEstacionMuestreoDef();
				
			}, 500);
			 
			$('#modal-registra-detalle').modal('show'); //HACE VISIBLE EN LA PANTALLA PRINCIPAL EL FORMULARIO DETALLE
			$.unblockUI();
			
		}
	});
}


function enableValidationRules() {

	var validator = $("#form-edit-turbidimetroDigital-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			strFechaMuestreo: {
					validators : {
						
						notEmpty: {
	                        message: 'Ingrese la Fecha'
	                    }
					}
			},
			strFechaRecepcion: {
					validators : {
						
						notEmpty: {
	                        message: 'Ingrese la Fecha'
	                    }		
						
					}
			},
			observacion: {
				validators : {
							
					stringLength : {
						max : 400,
						message : "El valor ha de ser como m\u00e1ximo 400 caracteres"
					}		
				}
			},
			transporteChofer: {
				validators : {
							
					stringLength : {
						max : 200,
						message : "El valor ha de ser como m\u00e1ximo 400 caracteres"
					}		
				}
			},
		}
	})	
	.on('success.form.bv', function(e, data) {
		let objObs = $("#form-edit-turbidimetroDigital-id #observacion"); 
		objObs.val(objObs.val().replace(/\r\n|\r|\n/g, ""));
		let objChof = $("#form-edit-turbidimetroDigital-id #transporte-chofer");
		objChof.val(objChof.val().replace(/\r\n|\r|\n/g, ""));
		MYAPPL.toUppercaseInputs();
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTurbidimetroDigitalForm = $(e.target);
		
		$("#strFechaMuestreo").removeAttr("disabled");
		
		$.ajax({
			type: 'POST',
			url: $('#form-edit-turbidimetroDigital-id').attr('action'),
			data: $('#form-edit-turbidimetroDigital-id').serialize(),
			success: function(result){
//				$nuevoTurbidimetroDigitalForm.bootstrapValidator('resetForm', true);
//				$nuevoTurbidimetroDigitalForm[0].reset();
				
				console.log("guardado ok")
				$('.container_save').html(result);
			},
			complete: function() {
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
			}
		});
	}).on('status.field.bv', function(e, data) {
		MYAPPL.toUppercaseInputs();
    });	
}



function enableValidationRulesVerificacion() {
	
var TIME_PATTERN = /^([01]?[0-9]|2[0-3]):([0-5][0-9])$/;
	
	$('#hora').datetimepicker({
		//defaultDate: new Date(),
        format: 'HH:mm',
        minDate: new Date(1900, 0, 1),
        ignoreReadonly: true,
        locale: "es"
    }).on('dp.change dp.show', function (e) {
        $('#form-edit-Detalle-id').bootstrapValidator('revalidateField', 'hora');
    });
	

	$("#form-edit-Detalle-id").bootstrapValidator({
//	var validator = $("#form-edit-turbidimetroDigitalVerif-id").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			estacionMuestreo: {
				validators : {					
					greaterThan: {
                        value: 1,
                        message: 'Seleccione un valor'
                    }, notEmpty: {
                        message: ' '
                    }
				}
			},		
			
			localMuestreo: {
				validators : {
							
					notEmpty: {
                        message: 'Seleccione un valor'
                    }
				}
			},
			ph: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			temperatura: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			conductividad: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			turbiedad: {
				validators : {
							
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			
          
			oxigeno: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			clorofila: {
				validators : {
							
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						 regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			coordenadaW: {
				validators : {
							
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						 regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						 message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
           
			altura: {
				validators : {
					
					notEmpty: {
						message: 'Valor incorrecto'
		          }
				}
			},
			
			ficocianina: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						 
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
	 
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			coordenadaE: {
				validators : {
					
					stringLength : {
						max : 10,
						message : "El valor ha de ser como m\u00e1ximo 10 caracteres"
					},			
					regexp: {
						regexp:/^\d{1,7}(\.\d{1,2})?$/,
						message: 'Valor m\u00E1ximo: 7 enteros y 2 decimales'
	 
					},
					notEmpty: {
                        message: 'Valor incorrecto'
                    }
				}
			},
			
			observaciones: {
				validators : {
							
					stringLength : {
						max : 400,
						message : "El valor ha de ser como m\u00e1ximo 400 caracteres"
					}		
				}
			},
			
            hora : {
				verbose: false,
				message : "Ingrese la hora",
				validators : {
					regexp: {
						regexp: /^([01]?[0-9]|2[0-3]):([0-5][0-9])$/,
                        message: "Debe tener el formato HH:MM (24 horas)"
                    },
                    stringLength : {
						max : 5,
						message : "El campo ha de ser como m\u00e1ximo 5 caracteres"
					},

				}
			},
            
		}
	})	
	.on('success.form.bv', function(e, data) {
		MYAPPL.toUppercaseInputs();
		$("#guardarButton").html('Guardando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $nuevoTurbidimetroDigitalVerificacionForm = $(e.target);
		$.ajax({
			type: 'POST',
			url: $('#form-edit-Detalle-id').attr('action'),
			data: $('#form-edit-Detalle-id').serialize(),
			success: function(result){
				$nuevoTurbidimetroDigitalVerificacionForm.bootstrapValidator('resetForm', true);
				$nuevoTurbidimetroDigitalVerificacionForm[0].reset();
				$('.container_save').html(result);
				
				console.log("MODE_EDIT: " + MODE_EDIT)
				if(MODE_EDIT==1){
					MYAPPL.showToast("actualizadoOk", "");	
				}
			},
			complete: function() {
				
				$("#guardarButton").html('Guardar');
				$('#guardarButton').removeAttr("disabled");
				$('#modal-registra-detalle').modal("hide");
			}
		});
	});	
}



//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarHeader(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdHeader').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-header242').attr('action'),
			    	   data: $('#form-inactiva-header242').serialize(),  
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

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarDetalle(id) {

	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inactivarDetalleID').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-detalle').attr('action'),
			    	   data: $('#form-inactiva-detalle').serialize(),  
			    	   success: function(result){  		
			    	       $('.container_save').html(result);		    	      
				    	   },
							complete: function(data) {
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

	function changeEstacionMuestreo() {
		var idLocalidad = $('#cboLocalMuestreo').find(":selected").data("id");

	       	$.ajax({
				type: 'GET',
				url: 'listPuntosAsignadas/'+idLocalidad, 
				success: function(result) {	
					armarCboSel(result,"#cboEstacionMuestreo", 0);				
				},complete: function() {					
					$('#form-edit-Detalle-id').bootstrapValidator('revalidateField', 'estacionMuestreo');
				}
			});		
				      
		}
	
	function changeEstacionMuestreoDef() {
		var idLocalidad = $('#cboLocalMuestreo').find(":selected").data("id");

	       	$.ajax({
				type: 'GET',
				url: 'listPuntosAsignadas/'+idLocalidad, 
				success: function(result) {	
					armarCboSel(result,"#cboEstacionMuestreo", $('#cboEstacionHidden').val());				
				},complete: function() {					
					$('#form-edit-Detalle-id').bootstrapValidator('revalidateField', 'estacionMuestreo'); 	
				}
			});		
				      
		}

function showToastTurbidimetroDigital(mensajeTipo, mensajeError) {

	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente guardado");
		 $('#modal-registra-formularioHeader').modal('hide');
		 $('#idBtnBuscarFormulario').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue guardado");
	 }
	 
	 if(mensajeTipo==='actualizadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente actualizado");
		 $('#modal-registra-formularioHeader').modal('hide');
		 $('#idBtnBuscarFormulario').click();
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado");
//		 $('#modal-registra-formularioHeader').modal('hide');
		 $('#idBtnBuscarFormulario').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, "Hubo un error en el servidor, el registro no fue inactivado");
	 }
	 if(mensajeTipo==='RegDuplicado'){
		 toastr["success"](mensajeError, "Registro ya existe en la Tabla");
		 $('#modal-registra-formularioHeader').modal('hide');
		 //$('#idBtnBuscarTurbidimetroDigital').click();
	 }
	 if(mensajeTipo==='inactivarIdDetalle'){
		 toastr["success"](mensajeError, "Registro correctamente eliminado ");
		 $('#modal-registra-detalle').modal('hide');
		 $('#myTableDetalle').DataTable().ajax.reload();
	 }
	 if(mensajeTipo==='grabadoOkSubForm'){
		 toastr["success"](mensajeError, "Registro agregado correctamente");
		 $('#modal-registra-detalle').modal('hide');
//		 $('#idCodigoVerifEquipo').val($('#intCodigo').val());
		 $('#myTableDetalle').DataTable().ajax.reload();   //LLAMA AL DATATABLE DETALLE A TRAVES DE SU ID
	 }
	 if(mensajeTipo==='inactivadoOkSubForm'){

		 toastr["success"](mensajeError, "Registro correctamente eliminado");
		 //$('#modal-registra-turbidimetroDigitalVerif').modal('hide');
		 $('#idCodigoVerifEquipo').val($('#intCodigo').val());
		 $('#myTableTurbidimetroDigitalVerif').DataTable().ajax.reload();
	 }
}


