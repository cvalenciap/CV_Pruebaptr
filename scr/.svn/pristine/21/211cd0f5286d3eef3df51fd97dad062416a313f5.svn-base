(function($) {
	var module = MYAPPL.createNS("MYAPPL.LOGIC.MUESTRA");
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
					id : 'upload_files_muestra',
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
	
//	cargarCombo("listFrascos","#tipoFrasco");
	
})(jQuery);	

function cargarSelect(urlCombo,idSelect,idInput){
	var value = $(idInput).text();
	cargarComboForFieldSinLoader(urlCombo,idSelect);
	console.log("asignando valor al combo " + value );
	setTimeout(function(){
		$(idSelect).val(value);
	},500);
	
}

var nsPd = MYAPPL.createNS("MYAPPL.LOGIC.PUNTODIST");
	
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnFormBuscarClima').click(function () {

		$("#txtPeriodoHidden").val($("#txtPeriodo").val());
		$('#myClima').DataTable().ajax.reload();  //PARA LLAMAR AL DATATABLE PRINCIPAL A TRAVES DEL ID
	});

	$('#txtPeriodo').datetimepicker({
	        format: 'DD/MM/YYYY',
	        ignoreReadonly: true,
	        maxDate: new Date(),
	        locale: "es"
	    });
	
	
});

function modificarFormulario(id) {
//	id= -1 Boton Agregar
	$.ajax({
		type: 'GET',
		url: 'muestraGet/'+id, //LLAMA AL CONTROLER Y DEVUELVE UNA PAGINA(PAGUNA HEADER)
		success: function(result) {
			$('#id-modal-content').replaceWith(result); //SE INYECTA EN LA PAGINA PRINCIPAL EL HEADER
			enableValidationRules();
				if(id != -1){ //EDITAR
					$('#strFechaHora').datetimepicker({
				        format: 'DD/MM/YYYY',
				        ignoreReadonly: false,
				        locale: "es",
				        maxDate : 'now'
					}).on('dp.change dp.show', function (e) {
				        $('#form-edit-clima').bootstrapValidator('revalidateField', 'strFechaHora');
				        
				    });
				}else{
					
					$('#strFechaHora').datetimepicker({
				        format: 'DD/MM/YYYY',
				        ignoreReadonly: false,
				        date: new Date(),
				        locale: "es",
				        maxDate : 'now'
					}).on('dp.change dp.show', function (e) {
				        $('#form-edit-clima').bootstrapValidator('revalidateField', 'strFechaHora');
				    });
				}

			
				
		},complete: function() {
			
			$('#modal-registra-clima').modal('show'); //MUESTRA EN LA PANTALLA PRINCIPAL FORMULARIO HEADER
			
			cargarComboValue("listTipoAnalisis","#tipoAnalisis",$("#tipoAnalisisHidden").val());
			cargarComboValue("listFrascos","#tipoFrasco",$("#tipoFrascoHidden").val());
			$.unblockUI();
			
			
			
		}
	});
}

$('#modal-registra-clima').on("change","#tipoAnalisis",function() {
	var defaulFrasco = $(this).find(":selected").data("defecto")
	console.log("asignando frasco: " + defaulFrasco)
	
	$('#tipoFrasco').val(defaulFrasco)

});

function addDefaultCboFrasco(){
	
}

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarFormulario(id) {
	$.confirm({
		title : 'Confirmaci\u00f3n',
		content : 'Confirma Eliminar Registro ?',
		buttons : {
			Aceptar : {
				btnClass : 'btn-blue',
				action : function() {
					$('#inacIdmuestra').val(id);
					$.ajax({
						type : 'POST',
						url : $('#form-inactiva-muestra').attr('action'),
						data : $('#form-inactiva-muestra').serialize(),
						success : function(result) {
							$('.container_save').html(result);
						},
						complete : function() {
							$.unblockUI();
						}
					});
				}
			},
			Cancelar : {}
		}
	});
};


//FUNCION PARA MOSTRAR EL DIALOG CON REPORTE PDF
nsPd.cargandoReportePDF = function() {
	var periodo = $('#txtPeriodo').val();
	// var varConsDistrito = $('#consDistrito').val();
	// var varConsTipoFuente = $('#consTipoFuente').val();
	$("#pdf").text("Cargando Reporte...");
	var options = {
		fallbackLink : '<p>Este navegador no soporta la previsualización de archivos PDF. <a href="[url]">Clic aquí para descargar el PDF</a>.</p>',
		forcePDFJS : true,
		PDFJS_URL : "js/pdfjs/web/viewer.html"
	};
	
	PDFObject.embed(MYAPPL.LOGIC.PUNTODIST.getContextPath()+ "/registroMuestraReportePDF/registroMuestraReportePDF"+ MYAPPL.getCurrentTimeStamp() + ".pdf?periodo=" + periodo, "#pdf", options);

}

nsPd.getContextPath = function () {
	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}


function enableValidationRules() {

var TIME_PATTERN = /^([01]?[0-9]|2[0-3]):([0-5][0-9])$/;
	
	$('#hora').datetimepicker({
		//defaultDate: new Date(),
        format: 'HH:mm',
        ignoreReadonly: true,
        locale: "es"
    }).on('dp.change dp.show', function (e) {
        $('#form-edit-Detalle-id').bootstrapValidator('revalidateField', 'hora');
    });
	
	var validator = $("#form-edit-clima").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			
		
			   
			puntoMuestreo : {

				message : "Ingrese la hora",
				validators : {
					
					notEmpty: {
                        message: 'Ingrese Punto de Muestreo'
                    }
				}
			},
			strFechaHora : {
				
				message : "Ingrese la Fecha",
				validators : {
					
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
			},
			hora : {
				autoFocus: false,
				verbose: false,
				message : "Ingrese valor",
				validators : {
                    
                    stringLength : {
						max : 5,
						message : "El campo ha de ser como m\u00e1ximo 5 caracteres"
					},

					notEmpty: {
                        message: 'Ingrese valor'
                    },
				}
			},
			
			codigoFrasco: {
				validators : {
							
					stringLength : {
						max : 8,
						message : "El valor ha de ser como m\u00e1ximo 8 caracteres"
					},
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
		   },
		   temperatura: {
					validators : {
								
						 regexp : {
								regexp : /^-?\d{1,3}(\.\d{1,2})?$/,
								message : "S\u00F3lo se permite 3 enteros con 2 decimales como m\u00E1ximo"
							}
					}
			},

			
			tipoAnalisis: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
			},
			
			tipoFrasco: {
				validators : {
					  callback: {
	                      message: 'Elegir valor para tipoFrasco',
	                      callback: function(value, validator, $field) {
	                          var options = validator.getFieldElements('tipoFrasco').val();
	                          return true;
	                      }
	                  },			
					
				}
			},
			
			aplicacionMedidas: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
                    }
				}
			},
			
			observacion: {
				validators : {
					notEmpty: {
                        message: 'Ingrese valor'
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


function showToastClima(mensajeTipo, mensajeError) {
// Mensajes despues de grabar
if (mensajeTipo === 'grabadoOk') {
	toastr["success"](mensajeError, "Registro correctamente guardado");
	$('#modal-registra-clima').modal('hide');
	$('#idBtnFormBuscarClima').click();
}
if (mensajeTipo === 'grabadoNoOk') {
	toastr["error"](mensajeError,
			"Hubo un error en el servidor, el registro no fue guardado");
}
// Mensajes despues de eliminar
if (mensajeTipo === 'borradoOk') {
	toastr["success"](mensajeError, "Registro correctamente eliminado");
	$('#modal-registra-clima').modal('hide');
	$('#idBtnFormBuscarClima').click();
  }
if (mensajeTipo === 'borradoNoOk') {
	toastr["error"](mensajeError,
			"Hubo un error en el servidor, el registro no fue inactivado");
  }
}

function duplicarRegistro153(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma duplicar el registro con la fecha actual\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
	        		//ACCION LUEGO DE CONFIRMAR
	        		MYAPPL.blockPageLoad();
	        		$('#codigoCabecera').val(id); //input definido en la pagina principal
	        		$.ajax({
	        			type: 'GET',
	        			url: 'formularioDuplicar153/'+id, //LLAMA AL CONTROLER Y DEVUELVE UNA PAGINA(PAGUNA HEADER)
	        			success: function(result) {
	        					console.log("result: " + $(result).html())
	        					$('#container-msg-record-existing-153').html(result);
	        					$('#myClima').DataTable().ajax.reload();
	        			},complete: function() {
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

function mensajeCboExistente(){
	var msg = '<script>/*<![CDATA[*/  mensajeTipo = "grabadoNoOk";'+
	'    mensajeMostrar = "";'+
	'    mensajeError = "Ya existe un registro con fecha actual";		'+
	'/*]]>*/'+
	'showToastGeneric(mensajeTipo,mensajeMostrar, mensajeError)'+	
	'</script>';	

    return msg;
}







