$(document).ready(function() {
	//Evento para cargar archivo 
    $("#upload-carga-input").on("change", uploadFile);
    
    drawTable(modelAttributeValue.lstDetalles,"cabeceraDetalle","cuerpoDetalle");
});

function saveMatriz() {
	MYAPPL.blockPageLoad();
	$.ajax({
 	   type: 'POST',
 	   url: $('#form-save-matriz').attr('action') + "?caudalId=" + $("#codigoCaudal").val() + "&periodo=" + $("#periodoCarga").val(),
 	   dataType: "json",
 	   success: function(result){  	
 		  // Refrescamos la variable del model, porque en cancelar se desfasa
 		  modelAttributeValue.lstDetalles = result.detalle.lstDetalles;
 		  clearImportData(modelAttributeValue.lstDetalles); 
 		  // Mensjae de Exito
 		  toastr.success('Carga manual correctamente realizada');
	    },
	    error: function( jqXHR, textStatus, errorThrown ) {
	    	toastr.error(jqXHR.responseJSON.errorMessage);
	    },
		complete: function() {
			$.unblockUI();
		}
	}); 
}

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
  if($.trim($("#upload-carga-input").val())!=""){
	  MYAPPL.blockPageLoad();
	  $.ajax({
	    url: $("#form-upload-file").attr('action') + "?periodo=" + $("#periodoCarga").val() + 
	    												"&rioId="+$("#codigoRio").val()+"&caudalId="+$("#codigoCaudal").val(),
	    type: "POST",
	    data: new FormData($("#upload-file-form")[0]),
	    enctype: 'multipart/form-data',
	    processData: false,
	    contentType: false,
	    cache: false,
	    dataType: 'json',
	    complete: function (data) {
	    	if(data.status == HTTP_STATUS_OK){
	    		var hiperlinkGo = '<a href="#datosImport">Ver datos para importar</a>';
	    		$("#panel-load-information").
	    			html(printInformationLoad("success", "Archivo Cargado Correctamente.", "ok", hiperlinkGo));
	    		var map = $.parseJSON(data.responseText);
	    		clearInputTypeFile("upload-carga-input");
	    		drawTable(map.lstExcelValues,"cabeceraExcelDetalle","cuerpoExcelDetalle");
	    		drawTable(map.lstDbValues,"cabeceraDetalle","cuerpoDetalle");
	    	    printTableExcel();
	    	}
	    	else if(data.status == HTTP_STATUS_INTERNAL_SERVER_ERROR){
	    		$("#panel-load-information").html(printInformationLoad("danger", data.responseText, "remove", ""));
	    		clearImportDataWithoutStatusUpload(modelAttributeValue.lstDetalles);
	    	}
	    	else{ // UNDEFINED WHEN IS TOO LARGE, EXCEPTION UNCONTROLATED
	    		$("#panel-load-information").html(printInformationLoad("danger", 'El archivo ingresado supera el tama&ntilde;o permitido (1MB)', "remove", ""));
	    		clearImportDataWithoutStatusUpload(modelAttributeValue.lstDetalles);
	    	}
	    	$("#panel-load-information").show();
	    	$.unblockUI();
	    }
	  })
  }
} // function uploadFile


// FUNCION PARA MOSTRAR LA TABLA CARGADA DEL EXCEL
function printTableExcel(){
	$("#panel-datos-excel").show();
}

function hideTableExcel(){
	$("#panel-datos-excel").hide();
}

function clearInputFile(){
	clearInputTypeFile("upload-carga-input"); // Limpiamos el file
	$("span.fileinput-filename").html("No hay archivo"); // reseteamos el mensaje
}

function cancelImport() {
	clearImportData(modelAttributeValue.lstDetalles);
}

function clearImportData(listRender) {
	hideTableExcel(); // cerramos datos del excel
	$("#panel-load-information").html(""); // Limpiamos div de status
	clearInputFile();
	drawTable(listRender,"cabeceraDetalle","cuerpoDetalle");
}

function clearImportDataWithoutStatusUpload(listRender) {
	hideTableExcel(); // cerramos datos del excel
	clearInputFile();
	drawTable(listRender,"cabeceraDetalle","cuerpoDetalle");
}

function printInformationLoad(result, message, icon, hipervinculo) { //success|danger, EXITOO, ok|remove
	var html = '<div class="alert-'+result+' center-align " style="height: 34px;" role="alert">';
	html += '<span class="glyphicon glyphicon-'+icon+'"></span> <font size="3">'+message+' '+hipervinculo+'</font>';
	html += '</div>';
	return html;
}

function drawTable(lst,header,body){
	drawHeaderTable(lst,header);
    drawBodyTable(lst,body);
}

function drawHeaderTable(lst,header){
	var dia1 = lst[0];
	var html = "<tr><th style='width: 13px;'>D\\H</th>";
	$.each( dia1, function( key, value ) {
		if(key < 24){ // LAS HORAS MAS NO EL PRIMEDIO
			var formated = ("0" + (key+1)).slice(-2);
			html += "<th style='width: 9px;'>"+formated+"</th>";
		}
		else{
			html += "<th style='width: 15px;'>PROM</th>";
		}
	});
	html += "</tr>";
	$("#"+header).html(html);
}

function drawBodyTable(lst,body){ 
	var html = "";
	$.each( lst, function( key, value ) {
		var row = value;
		var formated = ("0" + (key+1)).slice(-2);
		html += "<tr>";
		html += "<th>DIA "+formated+"</th>";
		$.each( row, function( key, value ) {
			var valueCelda = (value.strData == "-1.0" ? '' : value.strData);
			if(key < 24){ // LAS HORAS MAS NO EL PRIMEDIO
				html += "<td class='"+value.strClass+"'>"+valueCelda+"</td>";
			}
			else{
				html += "<td class='"+value.strClass+"' style='font-weight:bold' >"+valueCelda+"</td>";
			}
		});
		html += "</tr>";
	});
	$("#"+body).html(html);
}