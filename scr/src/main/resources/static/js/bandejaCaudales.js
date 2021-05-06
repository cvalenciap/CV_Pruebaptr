$(document).ready(function() {
	
	// INICALIZAMOS PARA MANEJO DE PERIODOS
	$('#txtPeriodo').datetimepicker({
        format: 'YYYYMM',
        ignoreReadonly: true,
        date: null,
        maxDate: new Date(),
        locale: "es"
    })
	
    //MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnFormBuscaCaudal').click(function () {		 
		 $('#tipoProcesoHide').val($('#cbxTipoProceso').val());
		 $('#txtPeriodoHide').val($('#txtPeriodo').val());
		 $('#myTableBandejaCaudales').DataTable().ajax.reload();
	});
    
	populateTipoProceso();
	
	// AUTOCOMPLETADOS:
	$("#rioNombreLargo").autocomplete({
	    source: cargarListaRiosAutocomplete(lstRios),
	    minLength: 1
	});
});

function cargarListaRiosAutocomplete(listaCombo) {
	var listaAcutoComplete = [];
	$.each( listaCombo, function( key, value ) {
		listaAcutoComplete.push(value.label);
	});
	return listaAcutoComplete;
}

// FUNCION PARA VISUALIZAR EL DETALLE DEL ERROR EN LA PAGINA
function showErrorDetail() {
	$.confirm({
	    title: 'Detalle de Carga',
	    // Buscamos caracteres especiales y lo reemplazamos por vacio
	    content: $('#detailErrorMessage').val().replace(REGEX_ALL_WITHOUT_DESC, ''),
	    columnClass: 'col-md-8 col-md-offset-2',
	    buttons: {
	    	Aceptar: { 
		        }
		    }
		});	
}

//FUNCIONAS PARA POBLAR
function populateTipoProceso(){
	$.getJSON('listTiposProceso', {
		ajax : 'true',
	}, function(data) {
		var html = '<option value="">-TODOS-</option>';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option value="' + data[i].value + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
		$('#cbxTipoProceso').html(html);
	});
}
