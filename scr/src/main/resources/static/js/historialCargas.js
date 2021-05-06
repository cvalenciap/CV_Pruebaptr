$(document).ready(function() {
});

//FUNCION PARA VISUALIZAR EL DETALLE DEL ERROR EN LA PAGINA
function showErrorDetail(mensaje) {
	$.confirm({
	    title: 'Detalle de Carga',
	    content: mensaje.replace(REGEX_ALL_WITHOUT_DESC_LIST, ''),
	    columnClass: 'col-md-8 col-md-offset-2',
	    buttons: {
	    	Aceptar: { 
		        }
		    }
		});	
}