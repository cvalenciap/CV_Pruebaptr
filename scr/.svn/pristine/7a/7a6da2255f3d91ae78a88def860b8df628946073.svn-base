$(document).ready(function() {
	//INICALIZAMOS PARA MANEJO DE PERIODOS
	$('#txtPeriodo').datetimepicker({
	    format: 'YYYYMM',
	    ignoreReadonly: true,
	    date: new Date(),
	    maxDate: new Date(),
	    locale: "es"
	});
	
	obtenerDataReporte($('#txtPeriodo').val());

	// AGREGAMOS EVENTO A INPUT DE PERIODO
	$('#txtPeriodo').on('dp.change', function(e) {	
		obtenerDataReporte($(this).val());
	});
	
	$("#idBtnFormExportar").click(function(e) {
	    e.preventDefault();
	    exportExcel();
	});
})

function exportExcel() {
	window.open("represasResumen/export/xls?" + setExportParams(), '_blank');
}

function setExportParams(){
	let $periodo = $('#txtPeriodo').val() != null ? $('#txtPeriodo').val() : '';	
	let $params = "periodo=" + $periodo;
	return $params;
}

function enableClientSideValidationFormulas() {
	var validator = $("#form-edit-formula")
	.bootstrapValidator({
//		trigger: 'blur',
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled'],
		fields : {
			strFormulaEmbalse :{
				validators : {
					notEmpty: {
			            message: 'Ingrese la f\u00F3rmula de embalse'
			        },
			        stringLength : {
						max : 600,
						message : "La f\u00F3rmula de embalse ha de ser como m\u00e1ximo 600 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	
                        	if($.trim($('#formula-embalse').val()) === ''){
                        		return true;
                        	}
                        	
                        	var formulaValor = 0;
                        	var formulaEmbalse = $('#formula-embalse').val();
                        	try {
                        		if(!$.isNumeric(formulaEmbalse)){
                        			formulaValor = math.eval(formulaEmbalse);
                        			formulaValor = Math.round(formulaValor * 10000) / 10000
                        		}
                        		else{
                        			formulaValor = formulaEmbalse;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'La f\u00F3rmula de embalse debe ser una f\u00F3rmula v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^-?\d{1,3}(\.\d{1,4})?$/.test(formulaValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 3 enteros y 4 decimales para la f\u00F3rmula de embalse"
                                }
                            }
                        	
                        	return true;
                        }
                    }
				}
			},
			strFormulaDescarga :{
				validators : {
					notEmpty: {
			            message: 'Ingrese la f\u00F3rmula de descarga'
			        },
			        stringLength : {
						max : 600,
						message : "La f\u00F3rmula de descarga ha de ser como m\u00e1ximo 600 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	
                        	if($.trim($('#formula-qdescarga').val()) === ''){
                        		return true;
                        	}
                        	
                        	var formulaValor = 0;
                        	var formulaDescarga = $('#formula-qdescarga').val();
                        	try {
                        		if(!$.isNumeric(formulaDescarga)){
                        			formulaValor = math.eval(formulaDescarga);
                        			formulaValor = Math.round(formulaValor * 1000000000000) / 1000000000000
                        		}
                        		else{
                        			formulaValor = formulaDescarga;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'La f\u00F3rmula de descarga debe ser una f\u00F3rmula v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^-?\d{1,13}(\.\d{1,12})?$/.test(formulaValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 13 enteros y 12 decimales para la f\u00F3rmula de descarga"
                                }
                            }
                        	
                        	return true;
                        }
                    }
				}
			},
			strFormulaColUno :{
				validators : {
					notEmpty: {
			            message: 'Ingrese la f\u00F3rmula uno'
			        },
			        stringLength : {
						max : 600,
						message : "La f\u00F3rmula uno ha de ser como m\u00e1ximo 600 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	
                        	if($.trim($('#formula-uno').val()) === ''){
                        		return true;
                        	}
                        	
                        	var formulaValor = 0;
                        	var formulaUno = $('#formula-uno').val();
                        	try {
                        		if(!$.isNumeric(formulaUno)){
                        			formulaValor = math.eval(formulaUno);
                        			formulaValor = Math.round(formulaValor * 1000000000000) / 1000000000000
                        		}
                        		else{
                        			formulaValor = formulaUno;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'La f\u00F3rmula uno debe ser una f\u00F3rmula v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^-?\d{1,3}(\.\d{1,4})?$/.test(formulaValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 3 enteros y 4 decimales para la f\u00F3rmula uno"
                                }
                            }
                        	
                        	return true;
                        }
                    }
				}
			},
			strFormulaColDos :{
				validators : {
					notEmpty: {
			            message: 'Ingrese la f\u00F3rmula dos'
			        },
			        stringLength : {
						max : 600,
						message : "La f\u00F3rmula dos ha de ser como m\u00e1ximo 600 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	
                        	if($.trim($('#formula-dos').val()) === ''){
                        		return true;
                        	}
                        	
                        	var formulaValor = 0;
                        	var formulaDos = $('#formula-dos').val();
                        	try {
                        		if(!$.isNumeric(formulaDos)){
                        			formulaValor = math.eval(formulaDos);
                        			formulaValor = Math.round(formulaValor * 1000000000000) / 1000000000000
                        		}
                        		else{
                        			formulaValor = formulaDos;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'La f\u00F3rmula dos debe ser una f\u00F3rmula v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^-?\d{1,3}(\.\d{1,4})?$/.test(formulaValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 3 enteros y 4 decimales para la f\u00F3rmula dos"
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
		$("#guardarButton").html('Recalculando...');
		$("#guardarButton").attr('disabled', 'disabled');
		e.preventDefault();
		var $formulaForm = $(e.target);
		$.ajax({
		 	   type: 'POST',
		 	   url: $('#form-edit-formula').attr('action'),
		 	   contentType: "application/json",
		 	   dataType: "json",
		 	   data: JSON.stringify({"strFecha":$("#fechaRegistro").val(),
		 		   "strFormulaEmbalse": $("#formula-embalse").val(), 
		 		   "strFormulaDescarga":$("#formula-qdescarga").val(),
		 		   "strFormulaUno":$("#formula-uno").val(),
		 		   "strFormulaDos":$("#formula-dos").val() }),
		 	   success: function(result){  	
		 		   $formulaForm.bootstrapValidator('resetForm', true);
		 		   $formulaForm[0].reset();
		 		   toastr.success("Valores recalculados correctamente");
		 		   drawTable(result,"cabeceraReporte","cuerpoReporte");
		 	   },
		 	   error: function( jqXHR, textStatus, errorThrown ) {
		 		   toastr.error(jqXHR.responseText);
		 	   },
		 	   complete: function() {
		 		  $("#guardarButton").html('Recalcular');
				  $('#guardarButton').removeAttr("disabled");
		 		  $('#modal-actualiza-almacenamiento').modal('hide');
		 		}
		 });  
	});
}

function enableClientSideValidationFormulasPorRepresa() {
	var validator = $("#form-edit-formula-por-represa")
	.bootstrapValidator({
//		trigger: 'blur',
		message: 'El valor no es v\u00e1lido',
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		excluded: [':disabled'],
		fields : {
			strFormulaVolTrasv :{
				validators : {
					notEmpty: {
			            message: 'Ingrese la f\u00F3rmula de volumen trasvasado'
			        },
			        stringLength : {
						max : 600,
						message : "La f\u00F3rmula de volumen trasvasado ha de ser como m\u00e1ximo 600 caracteres"
					},
					callback: {
                        callback: function(value, validator, $field) {
                        	
                        	if($.trim($('#formula-voltras').val()) === ''){
                        		return true;
                        	}
                        	
                        	var formulaValor = 0;
                        	var formulaVolumenTrasvasado = $('#formula-voltras').val();
                        	try {
                        		if(!$.isNumeric(formulaVolumenTrasvasado)){
                        			formulaValor = math.eval(formulaVolumenTrasvasado);
                        			formulaValor = Math.round(formulaValor * 100000000) / 100000000;
                        		}
                        		else{
                        			formulaValor = formulaVolumenTrasvasado;
                        		}
                        	}
                        	catch(err) {
                        	    return {
                                    valid: false,
                                    message: 'La f\u00F3rmula de volumen trasvasado debe ser v\u00e1lida'
                                };
                        	}
                        	
                        	if (!/^-?\d{1,10}(\.\d{1,8})?$/.test(formulaValor)) {
                                return {
                                    valid: false,
                                    message: "S\u00F3lo se permite hasta 10 enteros y 8 decimales para la f\u00F3rmula de volumen trasvasado"
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
		$("#guardarButton2").html('Recalculando...');
		$("#guardarButton2").attr('disabled', 'disabled');
		e.preventDefault();
		var $formulaForm = $(e.target);
		$.ajax({
		 	   type: 'POST',
		 	   url: $('#form-edit-formula-por-represa').attr('action'),
		 	   contentType: "application/json",
		 	   dataType: "json",
		 	   data: JSON.stringify({"strFecha":$("#fechaRegistro").val(),
		 		   "intIdRepresas":$("#idRepresas").val(),
		 		   "strFormulaVolumenTrasvasado":$("#formula-voltras").val() }),
		 	   success: function(result){ 
		 		   $formulaForm.bootstrapValidator('resetForm', true);
		 		   $formulaForm[0].reset();
		 		   toastr.success("Valores recalculados correctamente");
		 		   drawTable(result,"cabeceraReporte","cuerpoReporte");
		 	   },
		 	   error: function( jqXHR, textStatus, errorThrown ) {
		 		   toastr.error(jqXHR.responseText);
		 	   },
		 	   complete: function() {
		 		  $("#guardarButton2").html('Recalcular');
				  $('#guardarButton2').removeAttr("disabled");
		 		  $('#modal-actualiza-vol-trasvasado').modal('hide');
		 		}
		 });  
	});
}

enableClientSideValidationFormulas();
enableClientSideValidationFormulasPorRepresa();

function obtenerDataReporte(periodo) {
	MYAPPL.blockPageLoad();
	$.ajax({
 	   type: 'POST',
 	   url: $('#form-get-reporte').attr('action') + "?periodo="+periodo,
 	   dataType: "json",
 	   success: function(result){  	
 		  drawTable(result,"cabeceraReporte","cuerpoReporte");
	    },
	    error: function( jqXHR, textStatus, errorThrown ) {
	    	toastr.error(jqXHR.responseText);
	    },
		complete: function() {
			$.unblockUI();
		}
	}); 
}

function drawTable(lst,header,body){
	drawHeaderTable(lst,header);
    drawBodyTable(lst,body);
}

function drawHeaderTable(lst,header){
	var cantRepresas = 0;
	var represasAlmacenamiento = "";
	var represasExcluidas = "";
	var dia1 = lst[0]; // COGE EL PRIMER REGISTRO, PERO EN TODOS ESTA LOS DATOS DE CABECERA
	var html = "<tr>";
	$.each( dia1, function( key, value ) {
		if(key === 'strDia'){
			html += "<th style='width: 40px;' rowspan='3'>D\\R</th>";
			html += "<th style='width: 60px;' rowspan='3'>ACCION</th>";
		}
		else if(key === 'lstData'){
			cantRepresas = value.length - 5; // NO INCLUIMOS LOS RESUMENES (ALMACENAMIENTO, VOLUMEN TOTAL, MANIOBRAS, AFORO, CALCULADOS)
			$.each(value, function(key2, value2) {
				if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_ALMACENAMIENTO){
					represasAlmacenamiento = value2.strRepresasAlmacenamiento;
					represasExcluidas = value2.strRepresasExcluidas;
					html += "<th style='width: 300px; border-left: 3px solid #1b1818;' colspan='4'>"+value2.strNombrePrincipalColumna+"</th>";
				}
				else if (value2.intTipoColumna === REPORTE_REPRESAS_TYPE_REPRESA){
					html += "<th style='width: 900px; border-left: 3px solid #1b1818;' colspan='9'>"+value2.strNombrePrincipalColumna+"</th>";
				}
				else if (value2.intTipoColumna === REPORTE_REPRESAS_TYPE_VOLUMENTOTAL){
					html += "<th style='width: 120px; border-left: 3px solid #1b1818;' rowspan='2'>"+value2.strNombrePrincipalColumna+"</th>";
				}
				else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_MANIOBRAS){
					html += "<th style='width: 450px; border-left: 3px solid #1b1818;' colspan='3'>"+value2.strNombrePrincipalColumna+"</th>";
				}
				else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_AFORORIO){
					html += "<th style='width: 100px; border-left: 3px solid #1b1818;' rowspan='2'>"+value2.strNombrePrincipalColumna+"</th>";
				}
				else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_AGREGADOS){
					html += "<th style='width: 300px; border-left: 3px solid #1b1818;' colspan='2' >"+value2.strNombrePrincipalColumna+"</th>";
				}
			});
		}
	});
	html += "</tr>";
	// INICIO AHORA LOS VALORES POR REPRESA
	html += "<tr>";
	for (var i=0; i<cantRepresas; i++) {
		html += "<th style='width: 100px; border-left: 3px solid #1b1818;'>COTA</th>";
		html += "<th style='width: 100px;'>VOLUMEN</th>";
		html += "<th style='width: 100px;'>VOLUMEN<br />TOTAL</th>";
		html += "<th style='width: 100px;'>DESCARGA</th>";
		html += "<th style='width: 100px;'>CAUDAL<br />TRASVASADO<br />PROMEDIO</th>";
		html += "<th style='width: 200px; border-left: 3px solid #29731f; border-right: 3px solid #29731f;' colspan='2'>VOLUMEN<br />TRASVASADO</th>";
		html += "<th style='width: 100px;'>PRECIPITACI&Oacute;N</th>";
		html += "<th style='width: 100px;'>CLIMA</th>";
	}
	// AHORA PARA EMBALSES
	html += "<th style='width: 150px; border-left: 3px solid #1b1818;' colspan='2' ><button onclick='showMensajeOnPopup(1, \""+represasAlmacenamiento+"\")' " +
			"style='color: black;'>REPRESAS</button></th>";
	html += "<th style='width: 150px;' colspan='2' >TOTAL 19 EMBALSES</th>";
	// AHORA PARA LOS TIPOS DE MANIOBRA
	html += "<th style='width: 150px;' >Q. NATURAL</th>";
	html += "<th style='width: 150px;' >Q. DESCARGA</th>";
	html += "<th style='width: 150px;' >Q. REGULADO</th>";
	// AHORA PARA LAS COLUMNAS AGREGADAS
	html += "<th colspan='2'><button onclick='showMensajeOnPopup(2, \""+represasExcluidas+"\")' " +
			"style='color: black;'>REPRESAS EXCLUIDAS</button></th>";
	//.-------
	html += "</tr>";
	// FIN AHORA LOS VALORES POR REPRESA
	// INICIO LAS CABECERAS DE UNIDADES
	html += "<tr>";
	for (var i=0; i<cantRepresas; i++) {
		html += "<th style='border-left: 3px solid #1b1818;'>(m)</th>";
		html += "<th>(MMC)</th>";
		html += "<th>(MMC)</th>";
		html += "<th>(m3/s)</th>";
		html += "<th>(m3/s)</th>";
		html += "<th style='border-left: 3px solid #29731f;'>(m3)</th>"; // PARA VOLUMEN TRASVASADO VALOR
		html += "<th style='border-right: 3px solid #29731f;'>ACCION</th>"; // PARA VOLUMEN TRASVASADO FORMULA
		html += "<th>(mm)</th>";
		html += "<th>Tiempo</th>";
	}
	html += "<th style='width: 75px; border-left: 3px solid #1b1818;'>(MMC)</th>";
	html += "<th style='width: 75px;'>% ALMAC</th>";
	html += "<th style='width: 75px;'>(MMC)</th>";
	html += "<th style='width: 75px;'>% ALMAC</th>";
	// PARA VOLUMEN TOTAL
	html += "<th style='width: 100px; border-left: 3px solid #1b1818;'>(MMC)</th>";
	// PARA MANIOBRAS
	html += "<th colspan='3' style='border-left: 3px solid #1b1818;'>(m3/s)</th>";
	// PARA AFORO RIO RIMAC
	html += "<th style='width: 100px; border-left: 3px solid #1b1818;'>(m3/s)</th>";
	// AHORA PARA LAS COLUMNAS AGREGADAS
	html += "<th colspan='2' style='border-left: 3px solid #1b1818;'>(m3)</th>";
	html += "</tr>";
	// FIN LAS CABECERAS DE UNIDADES
	$("#"+header).html(html);
}

//FUNCION PARA VISUALIZAR LOS NOMBRES DE LAS REPRESAS
function showMensajeOnPopup(codFrom, mensaje) {
	$.confirm({
	    title: (codFrom === 1 ? 'Represas Almacenamiento' : 'Represas Excluidas' ),
	    content: mensaje,
	    buttons: {
	    	Aceptar: { 
		        }
		    }
	});	
}

function drawBodyTable(lst,body){ 
	var html = "";
	$.each( lst, function( key, value ) {
		html += "<tr>";
		html += "<th>"+value.strDia+"</th>";
		if(value.bolExistData){
			html += "<td class='center-align'><button class='btn btn-info btn-sm' " +
					"onclick='openModalEditAlmacenamiento(\""+value.strFecha+"\", \""+value.strFormulaAlmacenamiento+"\", \""+value.strFormulaManDescarga+"\", " +
							"\""+value.strFormulaColumnaUno+"\", \""+value.strFormulaColumnaDos+"\")'>" +
			"<span class='glyphicon glyphicon-pencil'></span></button></td>";
		}
		else{
			html += "<td class='center-align'></td>";
		}
		
		$.each(value.lstData, function(key2, value2) {
			if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_ALMACENAMIENTO){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;' >"+value2.strAlmacenamientoCantidad+"</td>";
				html += "<td class='center-align' >"+value2.strAlmacenamientoPorcentaje+"</td>";
				html += "<td class='center-align' >"+value2.strTotalEmbalseCantidad+"</td>";
				html += "<td class='center-align' >"+value2.strTotalEmbalsePorcentaje+"</td>";
			}
			else if (value2.intTipoColumna === REPORTE_REPRESAS_TYPE_REPRESA){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;'>"+value2.strCota+"</td>";
				html += "<td class='center-align' >"+value2.strVolumen+"</td>";
				html += "<td class='center-align' >"+value2.strVolumenTotalRep+"</td>";
				html += "<td class='center-align' >"+value2.strDescarga+"</td>";
				html += "<td class='center-align' >"+value2.strCaudalTrasvasado+"</td>";
				html += "<td class='center-align' style='border-left: 3px solid #29731f;' >"+value2.strVolumenTrasvasado+"</td>";
				if(value2.bolExistDataRepresa){ // PARA EDITAR LA FORMULA
					html += "<td class='center-align' style='border-right: 3px solid #29731f;'><button class='btn btn-info btn-sm' " +
							"onclick='openModalEditVolumenTrasvasado(\""+value.strFecha+"\", \""+value2.strFormulaVolumenTrasvasado+"\", \""+value2.intIdRepresas+"\")'>" +
					"<span class='glyphicon glyphicon-pencil'></span></button></td>";
				}
				else{
					html += "<td class='center-align' style='border-right: 3px solid #29731f;'></td>";
				}
				html += "<td class='center-align' >"+value2.strPrecipitacion+"</td>";
				html += "<td class='center-align' >"+value2.strTiempoAtmosferico+"</td>";
			}
			else if (value2.intTipoColumna === REPORTE_REPRESAS_TYPE_VOLUMENTOTAL){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;' >"+value2.strVolumenTotal+"</td>";
			}
			else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_MANIOBRAS){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;'>"+value2.strCaudalNatural+"</td>";
				html += "<td class='center-align' >"+value2.strCaudalDescarga+"</td>";
				html += "<td class='center-align' >"+value2.strCaudalRegulado+"</td>";
			}
			else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_AFORORIO){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;' >"+value2.strAforoRioRimac+"</td>";
			}
			else if(value2.intTipoColumna === REPORTE_REPRESAS_TYPE_AGREGADOS){
				html += "<td class='center-align' style='border-left: 3px solid #1b1818;' >"+value2.strColumnaUno+"</td>";
				html += "<td class='center-align' >"+value2.strColumnaDos+"</td>";
			}
		});
		html += "</tr>";
	});
	$("#"+body).html(html);
	
	// AHORA FIXEAMOS
	$("#myTableReporteRepresas").tableHeadFixer({"head" : true, "left" : 1}); 
}

//EVENTO DE CERRADO DE MODAL
$("#modal-actualiza-almacenamiento").on("hidden.bs.modal", function () {
	$("#form-edit-formula")[0].reset();
	$("#form-edit-formula").bootstrapValidator('resetForm', true);
});

//EVENTO DE CERRADO DE MODAL
$("#modal-actualiza-vol-trasvasado").on("hidden.bs.modal", function () {
	$("#form-edit-formula-por-represa")[0].reset();
	$("#form-edit-formula-por-represa").bootstrapValidator('resetForm', true);
});

function openModalEditAlmacenamiento(fecha, formulaAlmacenamiento, formulaManDescarga, formulaUno, formulaDos) {
	$("#idModalTitle").text("F\u00F3rmulas " + fecha);
	$("#fechaRegistro").val(fecha);
	$("#formula-embalse").val(formulaAlmacenamiento);
	$("#formula-qdescarga").val(formulaManDescarga);
	$("#formula-uno").val(formulaUno);
	$("#formula-dos").val(formulaDos);
	//enableClientSideValidationFormulas(); ACA NO PORQUE CREA UN EVENTO POR CADA LLAMADA
	$('#modal-actualiza-almacenamiento').modal('show');
}

function openModalEditVolumenTrasvasado(fecha, formulaAlmacenamiento, idRepresas) {
	$("#idModalTitle2").text("F\u00F3rmula Volumen Trasvasado " + fecha);
	$("#fechaRegistro").val(fecha);
	$("#idRepresas").val(idRepresas);
	$("#formula-voltras").val(formulaAlmacenamiento);
	//enableClientSideValidationFormulasPorRepresa();
	$('#modal-actualiza-vol-trasvasado').modal('show');
}