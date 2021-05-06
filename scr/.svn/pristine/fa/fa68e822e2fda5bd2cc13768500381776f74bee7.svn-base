var nsSAA = MYAPPL.createNS("MYAPPL.LOGIC.SUBIDA.ARCHVOS.ADJUNTOS");
const sufijo_TamanhoKB = ' KB';

nsSAA.obtenerIdPagina = function() {
	return $.now();
}

nsSAA.configurarGlobal = function(bl, oDoc) {
	var i = oDoc.id;
	var vAnhoDoc = oDoc.anhoDocumento;
	var vTipDoc = oDoc.tipoDocumento;
	var vCodDoc = oDoc.codDocumento;
	var vTipArchDoc = oDoc.tipArchDocumento;
	var vPMaxArchDoc = oDoc.tamMaxArchDocumento;
	var arrSec = oDoc.secciones;
	$.ajax({
		type: 'GET',
		cache: false,
		url: 'upload/configurar',
		data: {
			pagId: i,
			anhoDoc: vAnhoDoc,
			tipDoc: vTipDoc,
			codDoc: vCodDoc,
			tipArchDoc: vTipArchDoc,
			pMaxArchDoc: vPMaxArchDoc
		},
		success : function() {
			$.each(arrSec, function(index, seccion) {
				nsSAA.configurarFileUpload(i, seccion, bl);
			});
		}
	});
};

nsSAA.configurarFileUpload = function(i, oSec, bl) {
	nsSAA.consultarListaInicial(i, oSec);
	var vIdSec = oSec.id;
	$('#' + oSec.id + ' input:file').fileupload({
		url: 'upload/subir',
		dataType: 'json',
		formData: {
			pagId: i,
			idSec: vIdSec
		},
		done: function(e, data){
			console.log("data::"+data)
			if (data.result.status === "OK") {
				$('#' + vIdSec + ' table>tr:has(td)').remove();
				$.each(data.result.seccion.lstArchivoAdjunto, function(index, file) {
					$('#' + vIdSec + ' table').append(
							$('<tr/>')
							.append($('<td/>').text(index + 1))
							.append($('<td/>').text(file.nombreSinExtension))
							.append($('<td/>').text(file.extensionArchivo))
							.append($('<td/>').text(file.tamanhoKilobytes + sufijo_TamanhoKB))
							.append($('<td/>').html("<a class='btn btn-danger delete' title='Eliminar' onclick='nsSAA.eliminarAdjunto(" + i + ",\"" + vIdSec + "\","  + index + ")'><i class='glyphicon glyphicon-trash'></i></a>" + 
									"<a class='btn btn-info' title='Descargar' href='upload/descargar/" + i + "/" + vIdSec + "/" + index + "' onclick='nsSAA.clickOnLink()'><i class='glyphicon glyphicon-download'></i></a>"))
					);
				});
			} else if (data.result.status === "BAD_REQUEST") {
				
				MYAPPL.showToast(data.result.codMensaje, data.result.valMensaje, null, null);
			}
		},
		fail: function(jqXHR, status) {		
			//MYAPPL.showToast('subidaArchivoTamanhoNoOk', status.jqXHR.responseText, null, null);
			MYAPPL.showToast('subidaArchivoTamanhoNoOk', 'El archivo ingresado supera el tama&ntilde;o permitido (1Mb)', null, null);			
		}
	});
	if (!bl) {
		$('#' + oSec.id + ' input:file').attr('disabled', !bl);
		$('#' + oSec.id + ' a.delete').addClass('disabled');
	}

};

nsSAA.consultarListaInicial = function(i, oSec) {
	var vIdSec = oSec.id;
	var vCodSec = oSec.cod;
	var vCodCantMaxSec = oSec.codCantMax;
	$.ajax({
		type: 'GET',
		cache: false,
		url: 'upload/listar',
		async: false,
		data: {
			pagId: i,
			idSec: vIdSec,
			codSec: vCodSec,
			codCantMaxSec: vCodCantMaxSec
		},
		success: function(data) {
			$('#' + vIdSec + ' table>tr:has(td)').remove();
			$.each(data.lstArchivoAdjunto, function(index, file) {
				$('#' + vIdSec + ' table').append(
						$('<tr/>')
						.append($('<td/>').text(index + 1))
						.append($('<td/>').text(file.nombreSinExtension))
						.append($('<td/>').text(file.extensionArchivo))
						.append($('<td/>').text(file.tamanhoKilobytes + sufijo_TamanhoKB))
						.append($('<td/>').html("<a class='btn btn-danger delete' title='Eliminar' onclick='nsSAA.eliminarAdjunto(" + i + ",\"" + vIdSec + "\"," + index + ")'><i class='glyphicon glyphicon-trash'></i></a>" + 
								"<a class='btn btn-info' title='Descargar' href='upload/descargar/" + i + "/" + vIdSec + "/" + index + "' onclick='nsSAA.clickOnLink()'><i class='glyphicon glyphicon-download'></i></a>"))
				);
			});
		},
		error: function() {
			
		}
	});
};

nsSAA.eliminarAdjunto = function(i, idDiv, arch){
	console.log("inactivarPlanOperativo ....")
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: '\u00BFConfirma eliminar el registro\u003F',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
				    	$.ajax({
				    		type: 'POST',
				    		url: 'upload/eliminar',
				    		data: {
				    			pagId: i,
				    			idSec: idDiv,
				    			archIndex: arch
				    		},
				    		success: function(data){
				    			$('#' + idDiv + ' table>tr:has(td)').remove();
				    			$.each(data.lstArchivoAdjunto, function(index, file) {
				    				$('#' + idDiv + ' table').append(
				    						$('<tr/>')
				    						.append($('<td/>').text(index + 1))
				    						.append($('<td/>').text(file.nombreSinExtension))
				    						.append($('<td/>').text(file.extensionArchivo))
				    						.append($('<td/>').text(file.tamanhoKilobytes + sufijo_TamanhoKB))
				    						.append($('<td/>').html("<a class='btn btn-danger delete' title='Eliminar' onclick='nsSAA.eliminarAdjunto(" + i + ",\"" + idDiv + "\"," + index + ")'><i class='glyphicon glyphicon-trash'></i></a>" + 
				    								"<a class='btn btn-info' title='Descargar' href='upload/descargar/" + i + "/" + idDiv + "/" + index + "' onclick='nsSAA.clickOnLink()'><i class='glyphicon glyphicon-download'></i></a>"))
				    				);
				    			});
				    		},
				    		error: function() {
				    			
				    		}
				   });
				    	
		        	}
		        },
		        Cancelar: {
		        	
		        }
		    }
		});	 
	
	
}; 

nsSAA.clickOnLink = function(){
	// Para evitar que redireccione al LOGIN
	MYAPPL.validNavigation = true;
}; 

nsSAA.guardarArchivosAdjuntos = function(oDoc, $modal) {
	var i = oDoc.id;
	$.ajax({
		url: 'upload/guardar',
		type: 'POST',
		data: {
			pagId: i,
			codDoc: oDoc.codDocumento
		},
		success: function(data) {
			if (data.status === "EXPECTATION_FAILED") {
				$.each(data.lstArchivosSiGuardados, function(index, seccion) {
					$('#' + seccion.id + ' table>tr:has(td)').remove();
					$.each(seccion.lstArchivoAdjunto, function(index, file) {
						$('#' + seccion.id + ' table').append(
								$('<tr/>')
								.append($('<td/>').text(index + 1))
								.append($('<td/>').text(file.nombreSinExtension))
								.append($('<td/>').text(file.extensionArchivo))
								.append($('<td/>').text(file.tamanhoKilobytes + sufijo_TamanhoKB))
								.append($('<td/>').html("<a class='btn btn-danger delete' title='Eliminar' onclick='nsSAA.eliminarAdjunto(" + i + ",\"" + seccion.id + "\"," + index + ")'><i class='glyphicon glyphicon-trash'></i></a>" + 
										"<a class='btn btn-info' title='Descargar' href='upload/descargar/" + i + "/" + seccion.id + "/" + index + "' onclick='nsSAA.clickOnLink()'><i class='glyphicon glyphicon-download'></i></a>"))
						);
						
					});
				});
				$.each(data.lstArchivosNoGuardados, function(index, seccion) {
					var html = '<p>No se guardaron los siguientes archivos:</p>';
					html += '<ul>';
					$.each(seccion.lstArchivoAdjunto, function(index, file) {
						html += '<li>' + file.nombreArchivoOriginal + '</li>';
					});
					html += '</ul>';
					if (seccion.lstArchivoAdjunto == null || seccion.lstArchivoAdjunto.length == 0) {
						html = '';
					}
					$('#' + seccion.id + ' .msjErrArchivos').html(html);
				});
				let $messageType = "genericFileOk";
				$('#'+$modal.btnRefreshId).click(); // Que se refresque la tabla de la bandeja
				if($modal.mensajeTipo == 'grabadoOk'){
					$("#"+$modal.idLabelCodigo).val($modal.codigo); // Se setea en caso de insercion para que pueda EDITAR
					MYAPPL.showToast($messageType, 
							"Registro guardado correctamente <br /><br /> Sin embargo, alguno o todos los archivos no pudieron ser guardados, revisar" +
							" la lista en la secci&oacute;n correspondiente", 
							null, null);
				}
				else if($modal.mensajeTipo == 'actualizadoOk'){
					MYAPPL.showToast($messageType, 
							"Registro actualizado correctamente <br /><br /> Sin embargo, alguno o todos los archivos no pudieron ser guardados, revisar" +
							" la lista en la secci&oacute;n correspondiente", 
							null, null);
				}
			} else {
				if ($modal !== undefined) {
					MYAPPL.showToast(mensajeTipo, mensajeError, btnRefreshId, modalId);
				}
			}
		}
	});
	
	
}