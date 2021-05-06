$(document).ready(function() {
	//INICALIZAMOS PARA MANEJO DE PERIODOS
	$('#txtPeriodo').datetimepicker({
	    format: 'YYYYMM',
	    ignoreReadonly: true,
	    date: new Date(),
	    maxDate: new Date(),
	    locale: "es"
	});
});


//FUNCION PARA MOSTRAR EL DIALOG CON REPORTE PDF
cargandoReportePDF = function (periodo) {
	$("#pdf").text("Cargando Reporte...");
	var options = {
		fallbackLink: '<p>Este navegador no soporta la previsualización de archivos PDF. <a href="[url]">Clic aquí para descargar el PDF</a>.</p>',
		forcePDFJS: true,
		PDFJS_URL: "js/pdfjs/web/viewer.html"
	};
	PDFObject.embed(getContextPath()+"/reportesResumenPDF/reportesResumenPDF"+getCurrentTimeStamp()+".pdf?periodo="+periodo, "#pdf", options);
}


getContextPath = function () {
	
   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));	
}

//FUNCION PARA OBTENER LA FECHA HORA EN FORMATO YYYYMMDDHHmmSS	 
getCurrentTimeStamp = function (){
	var d = new Date();
	return ""+	d.getFullYear() +
	("00" + (d.getMonth() + 1)).slice(-2) + 
	    ("00" + d.getDate()).slice(-2)  + 
	    ("00" + d.getHours()).slice(-2)+ 
	    ("00" + d.getMinutes()).slice(-2) + 
	    ("00" + d.getSeconds()).slice(-2);	
};

$(document).ready(function(){
	   $("#enlaceajax").click(function(evento){
	      evento.preventDefault();
	      $("#cargando").css("display", "inline");
	   });
});


// FUNCION PARA VERIFICAR QUE EXISTAN REGISTROS

obtenerReporte = function() {
	MYAPPL.blockPageLoad();
	var periodo = $('#txtPeriodo').val();
	$.getJSON('reportesResumenPDF/cantidad?periodo='+periodo, {
		ajax : 'true',
	}, function(data) {
		$.unblockUI();
		if(data > 0){
			$('#modal-previsualizar-pdf').modal('show');
			cargandoReportePDF(periodo);
		}
		else{
			$.alert({
			    title: 'Mensaje del Sistema',
			    content: 'No hay datos registrados para el per&iacute;odo ingresado',
			    buttons: {
			    	Aceptar: {}
				}
			});	
		}
	});
};