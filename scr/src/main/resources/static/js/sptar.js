var validarFinDeSesion = function(jqXHR, textStatus, errorThrown, mensajeSesionExpirada, url){
   if(jqXHR.responseText != '' ){
        window.location.href = contexto+"login";
   }else{
       alert('La aplicación ha detectado un error inesperado al ejecutar la operación seleccionada. Favor intentar nuevamente, en caso el error persista comuníquese con el área responsable.');
   }
}

function mostrarMensaje(mensaje,estadoMensaje,idDiv){
	//librería Growl
    if(estadoMensaje == "success"){
    	$.growl.notice({ title: '<i class="glyphicon glyphicon-ok"></i>'+"  Éxito", message: mensaje });
    }else if(estadoMensaje == "danger"){
    	$.growl.error({ title: '<i class="fa fa-warning"></i>'+"  Alerta", message: mensaje });
    }else if(estadoMensaje == "warning"){
    	$.growl.warning({ title: '<i class="fa fa-warning"></i>'+"  Peligro", message: mensaje });
    }
    
	var mensajeHTML = '';
	if(IsEmpty(idDiv)) {
		idDiv= "divMensajeInformacion";
	}
	if(IsEmpty(mensaje) && IsEmpty(estadoMensaje)) {
		$('#'+idDiv).html('');
	}else if(estadoMensaje == "success"){		
		mensajeHTML = '<div class="alert alert-success" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="glyphicon glyphicon-ok"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	}else if (estadoMensaje == "danger") {		
		mensajeHTML = '<div class="alert alert-danger" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="fa fa-warning"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	} 
	else if (estadoMensaje == "warning") {		
		mensajeHTML = '<div class="alert alert-warning" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="fa fa-warning"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	} 
}

function mostrarMensajeModal(mensaje,estadoMensaje,idDiv){
	var mensajeHTML = '';
	if(IsEmpty(idDiv)) {
		idDiv= "divMensajeInformacionModal";
	}
	if(IsEmpty(mensaje) && IsEmpty(estadoMensaje)) {
		$('#'+idDiv).html('');
	}else if(estadoMensaje == "success"){		
		mensajeHTML = '<div class="alert alert-success" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="glyphicon glyphicon-ok"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacionModal').parent().removeClass("alert-custom");
	    $('#divMensajeInformacionModal').removeClass("alert-custom");
	}else if (estadoMensaje == "danger") {		
		mensajeHTML = '<div class="alert alert-danger" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="fa fa-warning"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacionModal').parent().removeClass("alert-custom");
	    $('#divMensajeInformacionModal').removeClass("alert-custom");
	} 
	else if (estadoMensaje == "warning") {		
		mensajeHTML = '<div class="alert alert-warning" role="alert" style="height: auto!important;">';
	    mensajeHTML = mensajeHTML + '<i class="fa fa-warning"></i>';
	    mensajeHTML = mensajeHTML + mensaje;
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacionModal').parent().removeClass("alert-custom");
	    $('#divMensajeInformacionModal').removeClass("alert-custom");
	} 
}

var modal = {
	    confirmer: function(msj, evtSiClick, evtNoClick, title) {
	    	/*
		   	 msj : cuerpo del mensaje del modal.
		   	 evtSiClick : funcion a ejecutar cuando se presiona click en Si, si no se considera enviar vacio.
		   	 evtNoClick : funcion a ejecutar cuando se presiona click en No, si no se considera enviar vacio.
		   	 title: titulo del modal, si no se considera enviar vacio.
		   	 */
        	if (IsEmpty(title)) { title = 'Confirmaci&oacute;n'; }	
        	if (IsEmpty(evtSiClick)) { evtSiClick = ''; }
        	if (IsEmpty(evtNoClick)) { evtNoClick = ''; }
        	if (IsEmpty(msj)) { msj = ''; }
        	$('#dialogComunConf').remove();
        	var html = '<div class="modal fade" id="dialogComunConf" tabindex="-1"  data-keyboard="false" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel">' +
					   		'<div class="modal-dialog" role="document">' +
					        	'<div class="modal-content">' +
					                 '<div class="modal-header">' +
					                     '<button type="button" class="close" onclick="modal.close();" aria-label="Close">' +
					                         '<span aria-hidden="true">&times;</span>' +
					                     '</button>' +
					                     '<h4 class="modal-title">' + title + '</h4>' +
					                 '</div>' +
					                 '<div class="modal-body">' + msj + '</div>' +
					                 '<div class="modal-footer">' +
					                     '<button type="button" class="btn-s btn-add boton" id="btnSiConfDialogComunConf">SI</button>' +
					                     '<button type="button" class="btn-s btn-clean boton" id="btnNoConfDialogComunConf" style="margin-left:10px;">NO</button>' +
					                 '</div>' +
					            '</div>' +
					        '</div>' +
					   '</div>';
            $(document.body).append(html);
            $('#dialogComunConf #btnSiConfDialogComunConf').attr('onclick', "modal.close(); " + evtSiClick);
        	$('#dialogComunConf #btnNoConfDialogComunConf').attr('onclick', "modal.close(); " + evtNoClick);
            $('#dialogComunConf').modal('show');	      
	    },
	    defaultModal: function(msj, evtConfirmClick, evtNotConfirmClick, title, nomBtnConfirm,  nomBtnNotConfirm) {
	    	/*
		   	 msj : cuerpo del mensaje del modal.
		   	 evtConfirmClick : funcion a ejecutar cuando se presiona click en confirmar, si no se considera enviar vacio.
		   	 evtNotConfirmClick : funcion a ejecutar cuando se presiona click en No confirmar, si no se considera enviar vacio.
		   	 title: titulo del modal, si no se considera enviar vacio.
		   	 nomBtnConfirm: titulo del nombre del boton confirm. Si no se considera no se mostrara el boton.
		   	 nomBtnNotConfirm: titulo del nombre del boton no confirm. Si no se considera no se mostrara el boton.
		   	 */
        	if (IsEmpty(title)) { title = 'Confirmaci&oacute;n'; }	
        	if (IsEmpty(evtConfirmClick)) { evtConfirmClick = ''; }
        	if (IsEmpty(evtNotConfirmClick)) { evtNotConfirmClick = ''; }      	
        	$('#dialogComunConf').remove();
        	var html = '<div class="modal fade" id="dialogComunConf" tabindex="-1" data-keyboard="false" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel">' +
					   		'<div class="modal-dialog" role="document">' +
					        	'<div class="modal-content">' +
					                 '<div class="modal-header">' +
					                     '<button type="button" class="close" onclick="modal.close();" aria-label="Close">' +
					                         '<span aria-hidden="true">&times;</span>' +
					                     '</button>' +
					                     '<h4 class="modal-title">' + title + '</h4>' +
					                 '</div>' +
					                 '<div class="modal-body">' + msj + '</div>' +
					                 '<div class="modal-footer">';
        	if (!IsEmpty(nomBtnConfirm)) { 
					        	  html+= '<button type="button" class="btn-s btn-add boton" id="btnSiConfDialogComunConf">' + nomBtnConfirm + '</button>';
        	}
        	if (!IsEmpty(nomBtnNotConfirm)) { 
        						  html+= '<button type="button" class="btn-s btn-clean boton" id="btnNoConfDialogComunConf" style="margin-left:10px;">' + nomBtnNotConfirm + '</button>';
        	}
        					  html+= '</div>' +
					            '</div>' +
					        '</div>' +
					   '</div>';
            $(document.body).append(html);
            $('#dialogComunConf #btnSiConfDialogComunConf').attr('onclick', "modal.close(); " + evtConfirmClick);
        	$('#dialogComunConf #btnNoConfDialogComunConf').attr('onclick', "modal.close(); " + evtNotConfirmClick);
            $('#dialogComunConf').modal('show');	      
	    },
	    open: function(msj, evtClick, title) {
	    	/*
		   	 msj : cuerpo del mensaje del modal.
		   	 evtClick : funcion a ejecutar cuando se presiona click en Aceptar, si no se considera enviar vacio.
		   	 title: titulo del modal, si no se considera enviar vacio.
		   	 */
	    	if (IsEmpty(title)) { title = 'Confirmaci&oacute;n'; }	
        	if (IsEmpty(evtClick)) { evtClick = ''; }
        	if (IsEmpty(msj)) { msj = ''; }
        	if (title == undefined || title == null || title == '') {
        		title = "Confirmaci&oacute;n";
        	}	  
        	$('#dialogComunConf').remove();
        	var html = '<div class="modal fade" id="dialogComunConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
					   		'<div class="modal-dialog" role="document">' +
					        	'<div class="modal-content">' +
					                 '<div class="modal-header">' +
					                     '<button type="button" class="close" aria-label="Close" onclick="modal.close();">' +
					                         '<span aria-hidden="true">&times;</span>' +
					                     '</button>' +
					                     '<h4 class="modal-title">' + title + '</h4>' +
					                 '</div>' +
					                 '<div class="modal-body">' + msj + '</div>' +
					                 '<div class="modal-footer">' +
					                     '<button type="button" class="btn-s btn-add" id="btnSiConfDialogComunConf">Aceptar</button>' +
					                 '</div>' +
					            '</div>' +
					        '</div>' +
					   '</div>';
            $(document.body).append(html);
            $('#dialogComunConf #btnSiConfDialogComunConf').attr('onclick', "modal.close(); " + evtClick);
            $('#dialogComunConf').modal('show');
	    },
	    close: function() {	   
	    	$('#dialogComunConf').modal('hide');
	    },
	    confirmerPersonalizado: function(msj, evtSiClick, evtNoClick, title,textoBotonSi,textoBotonNo) {
	    	/*
		   	 msj : cuerpo del mensaje del modal.
		   	 evtSiClick : funcion a ejecutar cuando se presiona click en Si, si no se considera enviar vacio.
		   	 evtNoClick : funcion a ejecutar cuando se presiona click en No, si no se considera enviar vacio.
		   	 title: titulo del modal, si no se considera enviar vacio.
		   	 */
        	if (IsEmpty(title)) { title = 'Confirmaci&oacute;n'; }	
        	if (IsEmpty(evtSiClick)) { evtSiClick = ''; }
        	if (IsEmpty(evtNoClick)) { evtNoClick = ''; }
        	if (IsEmpty(msj)) { msj = ''; }
        	$('#dialogComunConf').remove();
        	var html = '<div class="modal fade" id="dialogComunConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
					   		'<div class="modal-dialog" role="document">' +
					        	'<div class="modal-content">' +
					                 '<div class="modal-header">' +
					                     '<button type="button" class="close" onclick="modal.close();" aria-label="Close">' +
					                         '<span aria-hidden="true">&times;</span>' +
					                     '</button>' +
					                     '<h4 class="modal-title">' + title + '</h4>' +
					                 '</div>' +
					                 '<div class="modal-body">' + msj + '</div>' +
					                 '<div class="modal-footer">' +
					                     '<button type="button" class="btn-s btn-add boton" id="btnSiConfDialogComunConf">'+textoBotonSi+'</button>' +
					                     '<button type="button" class="btn-s btn-clean boton" id="btnNoConfDialogComunConf" style="margin-left:10px;">'+textoBotonNo+'</button>' +
					                 '</div>' +
					            '</div>' +
					        '</div>' +
					   '</div>';
            $(document.body).append(html);
            $('#dialogComunConf #btnSiConfDialogComunConf').attr('onclick', "modal.close(); " + evtSiClick);
        	$('#dialogComunConf #btnNoConfDialogComunConf').attr('onclick', "modal.close(); " + evtNoClick);
            $('#dialogComunConf').modal('show');	      
	    }
};

$(document).on('hidden.bs.modal', '.modal', function () {
	if ($('.modal:visible').length) {
	    $('body').addClass('modal-open');	    	    
	} else {
		$('.modal-backdrop').remove();
	    $('body').removeClass('modal-open');	    
	}
});

function redireccionModal(url, obj, element){
	$.ajax({
          url : url,
          type : "GET",
          async : false,
          data : obj
     }).success(function(data) {
          element.html(data);
          element.modal('toggle');
          
     }).fail(function(jqXHR, textStatus, errorThrown) {                	
     	  validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
     });
}

function cerrarModal(){
	$('#dialogComunConf #btnNoConfDialogComunConf').click();
}

function limpiarMensaje(idDiv){
	var idDiv= idDiv || "divMensajeInformacion";
	$('#'+idDiv).html('');
}

function ValidEmail (element)
{
	// creamos nuestra regla con expresiones regulares.
	var filter = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
	// utilizamos test para comprobar si el parametro valor cumple la regla
	if ( filter.test($.trim(element.val())) )
	{
		return true;
	}
	else
	{
		return false;
	}
}

function IsEmpty(cadena) {
	if(cadena == undefined || cadena == null || cadena == "" || (cadena.toString()).trim() == ""){
		return true;
	}
	return false;
}

function extraerTextoCombo(idCombo){
	return $('#'+idCombo+' option:selected').html();	
}

function ConvertirACsv(objArray, objHead) {
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
    var head = typeof objHead != 'object' ? JSON.parse(objHead) : objHead;
    var str = '';
    var cadena = "";
    var title = "";
    
    var arregloCabecera = new Array();;
    $.each(head, function(item, head) 
    {
        if(navigator.appVersion.indexOf("MSIE 8.")==-1){
	        if(head.visible == true && head.radio == false && head.checkbox == false && head['class']==undefined){
	                arregloCabecera.push(head);
	                if(title == ""){
	                    title = head.title;
	                }else{
	                    title = title + "," + head.title;
	                }
	        }
        }
    });
    
    title = title + "\r\n";
    
    $.each(objArray, function(item, objArray) {
    	if (!Object.keys) {
		  Object.keys = (function () {
		    'use strict';
		    var hasOwnProperty = Object.prototype.hasOwnProperty,
		        hasDontEnumBug = !({toString: null}).propertyIsEnumerable('toString'),
		        dontEnums = [
		          'toString',
		          'toLocaleString',
		          'valueOf',
		          'hasOwnProperty',
		          'isPrototypeOf',
		          'propertyIsEnumerable',
		          'constructor'
		        ],
		        dontEnumsLength = dontEnums.length;

		    return function (obj) {
		      if (typeof obj !== 'object' && (typeof obj !== 'function' || obj === null)) {
		        throw new TypeError('Object.keys called on non-object');
		      }

		      var result = [], prop, i;

		      for (prop in obj) {
		        if (hasOwnProperty.call(obj, prop)) {
		          result.push(prop);
		        }
		      }

		      if (hasDontEnumBug) {
		        for (i = 0; i < dontEnumsLength; i++) {
		          if (hasOwnProperty.call(obj, dontEnums[i])) {
		            result.push(dontEnums[i]);
		          }
		        }
		      }
		      return result;
		    };
		  }());
		}
        var cantidadParametros = Object.keys(objArray).length;
        var claveFila = "";
        var valor = "";
        var esPosicionCabeceraCorrecta = false;
        var contador = 0;
        $.each(arregloCabecera, function(item, arregloCabecera){
            var encontroCabecera = false;
            if(navigator.appVersion.indexOf("MSIE 8.")!=-1){
                 for(var posicion = 0; posicion < cantidadParametros; posicion++){
                     if(objArray.hasOwnProperty(arregloCabecera.field)==true){
                        if(objArray[arregloCabecera.field].length > 15 && /^([0-9])*$/.test(objArray[arregloCabecera.field])){
                            cadena = cadena + ('="' + objArray[arregloCabecera.field] + '"') + ",";
                            encontroCabecera = true;
                            break;
                        }else{
                            cadena = cadena + ('"' + objArray[arregloCabecera.field] + '"') + ",";
                            encontroCabecera = true;
                            break;
                        }
                     }
                 }
            }else{
                for(var posicion = 0; posicion < cantidadParametros; posicion++){
                    if(arregloCabecera.field == Object.getOwnPropertyNames(objArray)[posicion]){
                        if(objArray[Object.getOwnPropertyNames(objArray)[posicion]].length > 15 && 
                           /^([0-9])*$/.test(objArray[Object.getOwnPropertyNames(objArray)[posicion]])){
                            cadena = cadena + ('="' + objArray[Object.getOwnPropertyNames(objArray)[posicion]] + '"') + ",";
                            encontroCabecera = true;
                            break;
                        }else{
                            cadena = cadena + ('"' + (objArray[Object.getOwnPropertyNames(objArray)[posicion]]).toString() + '"') + ",";
                            encontroCabecera = true;
                            break;
                        }
                    }
                }
            }
            if(encontroCabecera==false){
                cadena = cadena + ",";
            }
        });
        cadena = cadena + '\r\n';
    });
    return htmlEscape(title+cadena);
}

function htmlEscape(str) {
    return String(str)
           .replace(/&aacute;/g, '\u00E1')
           .replace(/&eacute;/g, '\u00E9')
           .replace(/&iacute;/g, '\u00ED')
           .replace(/&oacute;/g, '\u00F3')
           .replace(/&uacute;/g, '\u00FA')
           .replace(/&ntilde;/g, '\u00F1')
           .replace(/&Aacute;/g, '\u00C1')
           .replace(/&Eacute;/g, '\u00C9')
           .replace(/&Iacute;/g, '\u00CD')
           .replace(/&Oacute;/g, '\u00D3')
           .replace(/&Uacute;/g, '\u00DA')
           .replace(/&Ntilde;/g, '\u00D1');
}


function validarLatitudLongitud(valor){
	var reg = /^-?([1-8]?[1-9]|[1-9]0)\.{1}\d{1,6}/;
	if(!IsEmpty(valor)){		
		if(reg.test(valor)) {
			 return true;
		} else {
			return false;
		}
	}else{
		return false;
	}
} 


function clonarObjeto(objeto){
	return $.extend({}, objeto);
}

function clonarLista(lista){
	return $.extend([], lista);
}


function changeTipoDocu(origen, destino, form, tipo) {
    var tipoDocu = $(origen).find(':checked').html();
    switch (tipoDocu) {
        case "DNI":
        	$(destino).attr("disabled", false);
            $(destino).attr("maxlength", "8");
            $(destino).attr("minlength", "8");
            $(destino).attr("validate", "[SN]");
            break;
        case "LE":
        	$(destino).attr("disabled", false);
            $(destino).attr("maxlength", "8");
            $(destino).attr("minlength", "8");
            $(destino).attr("validate", "[SN]");
            break;
        case "CE":
        	$(destino).attr("disabled", false);
            $(destino).attr("maxlength", "12");
            $(destino).attr("minlength", "8");
            $(destino).attr("validate", "[SLN]");
            break;
        case "RUC":
        	$(destino).attr("disabled", false);
        	$(destino).attr("maxlength", "11");
            $(destino).attr("minlength", "11");
            $(destino).attr("validate", "[SN]");
            break;
        case this.textoItemSeleccionar, this.textoItemTodos:
        	$(destino).attr("disabled", true);
        	break;
        default:
        	$(destino).attr("disabled", false);
            $(destino).attr("maxlength", "10");
            $(destino).removeAttr("minlength");
            $(destino).attr("validate", "[SLN]");
            break;
    }
    $(destino).unbind("keypress");
    $(form).validarForm();
    $(destino).val("");
}

(function($) {
    $.fn.validarForm = function(data) {
    	var caracteres = "abcdefghijklmnopqrstuvwxyzñÑABCDEFGHIJKLMNOPQRSTUVWXYZáéíóú ";
    	var numeros = "0123456789";
    	var numeros_caracteres = numeros + caracteres;
    	var moneda = numeros + ".";
        var form = $(this);
        var id = "#" + form.attr("id");
        $(id + ' input,' + id + ' textarea').each(
            function(index) {
                var cadena = "";
                var input = $(this);

                var validacion = input.attr('validate');
                if (validacion !== null && typeof validacion !== "undefined")
                    if (validacion.indexOf("[SL]") !== -1 || validacion.indexOf("[SN]") !== -1 || validacion.indexOf("[SNP]") !== -1) {
                        if (validacion.indexOf("[SL]") !== -1) {
                            cadena = caracteres;
                        } else if (validacion.indexOf("[SN]") !== -1) {
                            cadena = numeros;
                        } else if (validacion.indexOf("[SNP]") !== -1) {
                            cadena = numeros;
                        } 

                        $(this).on({
                            keypress: function(e) {
                                var key = e.which,
                                        keye = e.keyCode,
                                        tecla = String.fromCharCode(key).toLowerCase(),
                                        letras = cadena;
                                if (letras.indexOf(tecla) == -1 && keye != 9 && (key == 37 || keye != 37) && (keye != 39 || key == 39) && keye != 8 && (keye != 46 || key == 46) || key == 161) {
                                    e.preventDefault();
                                }
                            }
                        });
                    }
            }
        );

    };
})(jQuery); 

function agregarListaItem(element,lista, campoValue, campoText){
	  $.each(lista, function(i, item) {
		  element.append($("<option/>").attr("value",lista[i][campoValue] ).text(lista[i][campoText])); 
      });
}

function agregarListaItemTodos(element,lista, campoValue, campoText){
	element.append($("<option/>").attr("value", "").text("TODOS"));
	$.each(lista, function(i, item) {
		  element.append($("<option/>").attr("value",lista[i][campoValue] ).text(lista[i][campoText])); 
  });
}

function inputDecimal(elemento, maxPreDecimalPlaces, maxDecimalPlaces){	
	if(maxDecimalPlaces != null && maxDecimalPlaces != undefined) {
		/*Jim Modificacion Inicio*/
		maxPreDecimalPlaces = maxPreDecimalPlaces - maxDecimalPlaces ;
		/*Jim Modificacion Fin*/
		$("#"+elemento).numeric({
		    allowMinus   : false, // Allow the - sign
		    allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
		    maxPreDecimalPlaces: maxPreDecimalPlaces, // The max number digits before the decimal point
		    maxDecimalPlaces: maxDecimalPlaces // The max number of decimal places
		});
	} else {
		$("#"+elemento).numeric({
		    allowMinus   : false, 
		    allowThouSep : false, 
		    maxPreDecimalPlaces: maxPreDecimalPlaces 
		});
	}
}

function inputNumerico(elemento, maxDigits){
	/*
	 Allow the - sign
	 allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
	 allowDecSep  : false, // // Allow the decimal separator, default is the fullstop eg 3.141
	 maxDigits: maxDigits // // The max number of digits
	 */
	if(maxDigits != null && maxDigits != undefined) {
		$("#"+elemento).numeric({
		    allowMinus   : true, 
		    allowThouSep : false,
		    allowDecSep  : false,
		    maxDigits: maxDigits 
		});
	} else {
		$("#"+elemento).numeric({
		    allowMinus   : true,
		    allowThouSep : false,
		    allowDecSep  : false
		});
	}
}

function inputNumericoDefinido(elemento, maxValue, minValue){
	/*
	 Allow the - sign
	 allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
	 allowDecSep  : false, // // Allow the decimal separator, default is the fullstop eg 3.141
	 maxDigits: maxDigits // // The max number of digits
	 */
	$("#"+elemento).alphanum({
		disallow           : '012!@#$%^&*()+=[]\\\';,/{}|":<>?~`.- _°¡¿¨´',
		allowNumeric       : true,
		maxLength          : 1,
	});
}

function colorCelda (color){
	return {
	    css: {
	      'background' : color
	    }
  	}
}

function cargarPantalla(url, obj, element){
	$.ajax({
          url : url,
          type : "GET",
          async : true,
          data : obj
     }).success(function(data) {
          element.html(data);          
     }).fail(function(jqXHR, textStatus, errorThrown) {                	
     	  validarFinDeSesion(jqXHR, textStatus, errorThrown,  ConstanteServices.MENSAJE_SESION_EXPIRADA );
     });
}

function estiloAcciones (value, row, index) {	   
	return {
	    css: {
	      'width' : '100px'
	    }
	};		    	     
}

function estiloAccionesChild (value, row, index) {	   
	return {
	    css: {
	      'width' : '70px'
	    }
	};		    	     
}

function estiloAcciones65 (value, row, index) {	   
	return {
	    css: {
	      'width' : '65px'
	    }
	};		    	     
}

function estiloAcciones40 (value, row, index) {	   
	return {
	    css: {
	      'width' : '40px'
	    }
	};		    	     
}

function estiloAcciones55 (value, row, index) {	   
	return {
	    css: {
	      'width' : '55px'
	    }
	};		    	     
}

function verificarEstiloEstado(value, row, index) {	
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {
		return {};
	}else{
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

function retornarEnteroRedondear(valor){
	return Math.round(valor);
}

function retornarEnteroTrunca(valor){
	return Math.trunc(valor);
}

function cortarDecimalRedondear(valor,cantidadDecimales){
	var numero  = parseFloat(valor);
	return numero.toFixed(cantidadDecimales);
}

function cortarDecimalSinRedondearTwo(valor){
	return valor.toString().match(/^-?\d+(?:\.\d{0,11})?/)[0];
}

function cortarDecimalSinRedondearOne(valor){
	return valor.toString().match(/^-?\d+(?:\.\d{0,1})?/)[0];
}

function cortarDecimalSinRedondearFour(valor){
	return valor.toString().match(/^-?\d+(?:\.\d{0,1111})?/)[0];
}

function cortarCadenaLimitada(valor,inicio,fin){
	return valor.substring(inicio,fin)
}

function cortarCadenaTotal(valor,inicio){
	return valor.substring(inicio)
}

function Trim(cadena)
{
	if(cadena != "" && cadena != null && cadena != undefined){
		return cadena.trim();
	}else{
		return cadena;
	}		
}

function verAdjuntosProceso(idRegLab,idPtarSec,idPara,estadoAprobacionG){
	if(idRegLab != null && !IsEmpty(idRegLab)){
		var objeto = {};
		objeto.idRegistroLaboratorioAdjunto = idRegLab;
		objeto.idPtarSectorAdjunto = idPtarSec;
		objeto.idParametroAdjunto = idPara;
		objeto.estadoAprobacion = estadoAprobacionG;
		redireccionModal('./cargarVentanaVerAdjuntos', objeto, $('#verAdjuntosDiv'));
	}else{
		mostrarMensaje('Debe Grabar algun Registro.', ConstanteServices.IMAGEN_DANGER);
	}
}

function crearBotonesEnlace(valorFlag, nombreEnlace) {
	var html = '<div class="box-2-icon-grid" style="text-align:center;">';
		           html += '<span class="control-table" >';
		       if(valorFlag == ConstanteServices.ID_FLAG_ESTADO_NO_EXISTE){
		          html +='<img alt="" title = "No Aplica" src="'+contexto+'/images/pudo/eli06_u891.png">';
		       }else if(valorFlag == ConstanteServices.ID_FLAG_ESTADO_PEND_INGRESO){
		    	   html +='<img alt="" title = "Pendiente de Ingreso" src="'+contexto+'/images/pudo/eli06_u37.png" id=enlaceRegistro'+nombreEnlace+' style="cursor:pointer;">';
		       }else if(valorFlag == ConstanteServices.ID_FLAG_ESTADO_PEND_APROB){
		    	   html +='<img alt="" title = "Pendiente de Aprobación" src="'+contexto+'/images/pudo/eli01_u29.png" id=enlaceRegistro'+nombreEnlace+' style="cursor:pointer;">';
		       }else if(valorFlag == ConstanteServices.ID_FLAG_ESTADO_APROBADO){
		    	   html +='<img alt="" title = "Aprobado" src="'+contexto+'/images/pudo/eli01_u33.png" id=enlaceRegistro'+nombreEnlace+' style="cursor:pointer;">';
		       }
		       
	html += '</span>';
	html += '</div>';
	return html;
}

function ponerClassConjunto(tipoInput,nombreClase,MaxDigito,maxPreDecimalPlaces, maxDecimalPlaces){
	var lista = document.getElementsByClassName(nombreClase);
	for(var  i = 0 ; i < lista.length ; i++){
		var identificador = lista[i].id;
		if(tipoInput == "inputDecimal"){
			inputDecimal(identificador, maxPreDecimalPlaces, maxDecimalPlaces);
		}else if(tipoInput == "inputNumerico"){
			inputNumerico(identificador, MaxDigito);
		}else if(tipoInput == "inputNumericoDefinido"){
			inputNumericoDefinido(identificador, MaxDigito, maxPreDecimalPlaces);
		}
	}	
}

function ponerClassConjuntoFechas(nombreClase){
	var lista = document.getElementsByClassName(nombreClase);
	for(var  i = 0 ; i < lista.length ; i++){
		var identificador = lista[i].id;
		$('#'+identificador).datepicker({
            autoclose: true,
            format: 'dd/mm/yyyy'
		});
	}	
}

function ponerClassConjuntoHorasHoy(nombreClase){
	$('.'+nombreClase).datetimepicker({
		format: 'HH:mm',
		locale: 'es'
	});
	var lista = document.getElementsByClassName(nombreClase);
	for(var  i = 0 ; i < lista.length ; i++){	
		var identificador = lista[i].id;
		$('#'+identificador).val(horaActual);
	}	
	formatoHora();
}

function formatoHora(){
	$('.hora').mask('99:99', {autoclear: false});
}

function deshabilitarFormularioInputSelect(element,estado){
	if(estado){
		element.find('input, textarea, select').attr('disabled','disabled');
	}else{
		element.find('input, textarea, select').removeAttr('disabled');
	}
} 

function agregarFileInput(idDiv, idFileInput,metodoGrabar, evtRemoveFile){
	var input = "";
	if(metodoGrabar != null){
		input =  '<input type="file" id='+ idFileInput + ' onchange="if(!this.value.length)return false; '+metodoGrabar+'();"    >';
	}else{
		input =   '<input type="file" id='+ idFileInput + '>';
	}	
	
	var html = ''
		+ '<div class="fileupload fileupload-new" data-provides="fileupload">'
		+     '<span class="btn-file fa fa-file-text-o fa-2x">'
		+         '<span class="fileupload-new">&nbsp;</span>'
		+         '<span class="fileupload-exists" name>&nbsp;</span>'  
		+	input	
		+     '</span>'
		+     '<span class="fileupload-preview" id=' + 'carga' + idFileInput + '></span>'
		+     '<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none" id= close'+ idFileInput + '>×</a>'
		+ '</div>'
	    
	$('#' + idDiv).append(html);
	
	if(IsEmpty(evtRemoveFile) == false){
		$('#close'+ idFileInput + '').attr('onclick', evtRemoveFile);		
	}
}

function textFileName(element){
	
	var file = document.getElementById(element).files[0];
	
	if(file != null){
		$("#lbl" + element).text(" " + file.name);
	}else{
		$("#lbl" + element).text("");
	}
}

function invocarFileInput(element){
	
	$("#" + element).bind("change", function(){ 
		textFileName(element);
	});
	
	$("#" + element).click();
}

function eliminarFileInput(element){
	$("#lbl" + element).text("");
	element.removeAttribute('value');
	element.parentNode.replaceChild(element.cloneNode(true),element);
}

function validarArchivo(nombreArchivoInterno, nombreArchivoExterno,idDiv){
	var url = contexto+"general";
	
	$.ajax({
		url: url+"/validarArchivo",
		type: "POST",
		dataType: "json",
		cache: false,
		async: false,
		data : {
			nSecuencial: nombreArchivoInterno
		}
	}).done(function(paramJson) {	
		
		if(IsEmpty(idDiv) == false){
			limpiarMensaje(idDiv);
		}
		
		var respuesta = paramJson.respuesta;
		
		if(respuesta.mensajeRespuesta == ConstanteServices.OK){
			descargarArchivo(nombreArchivoInterno, nombreArchivoExterno, url);
		}
		else if(IsEmpty(idDiv) == false){
			mostrarMensaje(respuesta.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER, idDiv);
		}
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(respuesta.jqXHR, respuesta.textStatus, respuesta.errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
}

function descargarArchivo(nombreArchivoInterno, nombreArchivoExterno, url){
	var urlGeneral = url ;
	
	window.location.href = url + '/accionExportarArchivo?nSecuencial=' + nombreArchivoInterno 
	+ '&nVisible=' + nombreArchivoExterno;
}

function accordion() {
    var text = '<span class="pull-right clickable accordion"><i class="glyphicon glyphicon-chevron-up"></i></span>';
    $('.panel-heading').each(function(){                     
        $(this).html($(this).html() + text);
    });
    
    $('.panel-heading span.clickable').on("click", function (e) {
                   if ($(this).hasClass('panel-collapsed')) {
                       // expand the panel
                       $(this).parents('.panel').find('.panel-body').slideDown();
                       $(this).removeClass('panel-collapsed');
                       $(this).find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
                   }
                   else {
                       // collapse the panel
                       $(this).parents('.panel').find('.panel-body').slideUp();
                       $(this).addClass('panel-collapsed');
                       $(this).find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
                   }
    });           
    $('.panel-heading span.clickable').click();
}

function ReplaceAll (cadena, CaracterBusqueda, CaracterReemplazo) {
	  var outString = cadena;
	  if(cadena != null && cadena != undefined){
	  if(cadena != ""){
	  while (true) {
	    var idx = outString.indexOf(CaracterBusqueda);
	    if (idx == -1) {
	      break;
	    }
	      outString = outString.substring(0, idx) + CaracterReemplazo +
	      outString.substring(idx + CaracterBusqueda.length);
	  }  
	  }   
	  }
	  return outString;
}

function isDate (date)
{
	return (new Date(date) !== "Invalid Date" && !isNaN(new Date(date)) ) ? true : false;
}

function validarFormulaIngreso(ltaVariable, formulaIngreso){
	var flagError = null; 
    var cadenaFinal = "";
    var variableComparacion = "";
    for(i=0;i<formulaIngreso.length;i++){  
    	if(formulaIngreso.charAt(i)!= '('&&formulaIngreso.charAt(i)!= ')'&&formulaIngreso.charAt(i)!= '+'&&formulaIngreso.charAt(i)!= '-'&&formulaIngreso.charAt(i)!= '*'&&formulaIngreso.charAt(i)!= '/'){
    		variableComparacion = variableComparacion.concat(formulaIngreso.charAt(i));
    		if(i == formulaIngreso.length-1){
    			if(IsEmpty(variableComparacion)){
        			cadenaFinal = cadenaFinal.concat(formulaIngreso.charAt(i));
    			}else{
    				if(!isNaN(parseInt(variableComparacion))){
        				cadenaFinal = cadenaFinal.concat(variableComparacion);
        				variableComparacion="";
        			}else{
        				var flag = 1;
                		for(j=0;j<ltaVariable.length;j++){
                			if(ltaVariable[j].descripcionLetra == variableComparacion){
                				flag = 0;
                			}
                		}
                		if(flag == 1){
                			flagError = 1;
                			return ("El campo Fórmula contiene variables que no existen");;
                			break;
                		} else {
                			cadenaFinal = cadenaFinal.concat("1");
                		}
        			}
    			}
    		}
    	} else {
    		if(IsEmpty(variableComparacion)){
    			cadenaFinal = cadenaFinal.concat(formulaIngreso.charAt(i));
    		}else{
    			if(!isNaN(parseInt(variableComparacion))){
    				cadenaFinal = cadenaFinal.concat(variableComparacion+formulaIngreso.charAt(i));
    				variableComparacion="";
    			}else{
    				var flag = 1;
            		for(j=0;j<ltaVariable.length;j++){
            			if(ltaVariable[j].descripcionLetra == variableComparacion){
            				flag = 0;
            			}
            		}
            		if(flag == 1){
            			flagError = 1;
            			return ("El campo Fórmula contiene variables que no existen");;
            			break;
            		} else {
            			cadenaFinal = cadenaFinal.concat("1"+formulaIngreso.charAt(i));
            			variableComparacion = "";
            		}
    			}
    		}
    	} 
    }
    console.log(cadenaFinal);
    try{
    	eval(cadenaFinal);
    }catch(error){
    	flagError = 2;
    	return("El campo Fórmula tiene no contiene una expresión válida");
    }
    return null;
}

function verificarEstiloColor(value, row, index) {	
	var color = "#e8f2fb";
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {
		return {
		    css: {
		      'background' : color
		    }
		};
	}else{
		return {
		    css: {
		      'color' : '#618AAE',
		      'background' : color
		    }
		};
	}
}

function ejecutarFormula(ltaVariable, formulaIngreso){
	var flagError = null; 
    var cadenaFinal = "";
    var variableComparacion = "";
    for(i=0;i<formulaIngreso.length;i++){  
    	if(formulaIngreso.charAt(i)!= '('&&formulaIngreso.charAt(i)!= ')'&&formulaIngreso.charAt(i)!= '+'&&formulaIngreso.charAt(i)!= '-'&&formulaIngreso.charAt(i)!= '*'&&formulaIngreso.charAt(i)!= '/'){
    		variableComparacion = variableComparacion.concat(formulaIngreso.charAt(i));
    		if(i == formulaIngreso.length-1){
    			if(IsEmpty(variableComparacion)){
        			cadenaFinal = cadenaFinal.concat(formulaIngreso.charAt(i));
    			}else{
    				if(!isNaN(parseInt(variableComparacion))){
        				cadenaFinal = cadenaFinal.concat(variableComparacion);
        				variableComparacion="";
        			}else{
        				var flag = 1;
        				var valorVariable = "";
                		for(j=0;j<ltaVariable.length;j++){
                			if(ltaVariable[j].descripcionLetra == variableComparacion){
                				flag = 0;
                				valorVariable = ""+ltaVariable[j].valor;
                			}
                		}
                		if(flag == 1){
                			flagError = 1;
                			return (ConstanteServices.VARIABLES_INEXISTENTES);
                			break;
                		} else {
                			cadenaFinal = cadenaFinal.concat(valorVariable);
                		}
        			}
    			}
    		}
    	} else {
    		if(IsEmpty(variableComparacion)){
    			cadenaFinal = cadenaFinal.concat(formulaIngreso.charAt(i));
    		}else{
    			if(!isNaN(parseInt(variableComparacion))){
    				cadenaFinal = cadenaFinal.concat(variableComparacion+formulaIngreso.charAt(i));
    				variableComparacion="";
    			}else{
    				var flag = 1;
    				var valorVariable = "";
            		for(j=0;j<ltaVariable.length;j++){
            			if(ltaVariable[j].descripcionLetra == variableComparacion){
            				flag = 0;
            				valorVariable = ""+ltaVariable[j].valor;
            			}
            		}
            		if(flag == 1){
            			flagError = 1;
            			return (ConstanteServices.VARIABLES_INEXISTENTES);
            			break;
            		} else {
            			cadenaFinal = cadenaFinal.concat(valorVariable+""+formulaIngreso.charAt(i));
            			variableComparacion = "";
            		}
    			}
    		}
    	} 
    }
    console.log(cadenaFinal);
    try{
    	resultado = eval(cadenaFinal);
    }catch(error){
    	return(ConstanteServices.EXPRESION_INVALIDA);
    }
    return resultado;
}

function sumarMinutosFecha(fechaActual,cantidadMinutos){
	  fechaActual.setMinutes(fechaActual.getMinutes()+cantidadMinutos);
	  var dia = String(fechaActual.getDate());
	  var mes = String((fechaActual.getMonth() +1));
	  var annio = String(fechaActual.getFullYear());
	  var hora = String(fechaActual.getHours());
	  var minuto =  String(fechaActual.getMinutes());
	  var segundo = String(fechaActual.getSeconds());
	  if(dia.length == 1){
		  dia = "0"+dia;
	  }
	  if(mes.length == 1){
		  mes = "0"+mes;
	  }
	  if(hora.length == 1){
		  hora = "0"+hora;
	  }
	  if(minuto.length == 1){
		  minuto = "0"+minuto;
	  }
	  if(segundo.length == 1){
		  segundo = "0"+segundo;
	  }
	  var sFecha = (dia+ "/" + mes + "/" + annio +" "+hora+":"+minuto+":"+segundo);
	  return sFecha;
}

function dateAdd(interval, d, fecha) {
	var Fecha = new Date();
	var sFecha = fecha || (Fecha.getDate() + "/" + (Fecha.getMonth() +1) + "/" + Fecha.getFullYear());
	var sep = sFecha.indexOf('/') != -1 ? '/' : '-'; 
	var aFecha = sFecha.split(sep);
 	var fecha = aFecha[2]+'/'+aFecha[1]+'/'+aFecha[0];
 	fecha= new Date(fecha);
	if(interval == "d") { 
		fecha.setDate(fecha.getDate()+parseInt(d));
		var anno=fecha.getFullYear();
		var mes= fecha.getMonth()+1;
		var dia= fecha.getDate();
		mes = (mes < 10) ? ("0" + mes) : mes;
		dia = (dia < 10) ? ("0" + dia) : dia;
		var fechaFinal = dia+sep+mes+sep+anno;
		return (fechaFinal);
	 } else if (interval == "m") {
		var anno =fecha.getFullYear();
		var mes = fecha.getMonth() + 1;
		var dia = fecha.getDate();
		mes = (mes < 10) ? ("0" + mes) : mes;
		dia = (dia < 10) ? ("0" + dia) : dia;		 
		if((parseInt(mes) + d) > 12) {
			var auxMes = (parseInt(mes) + d) - 12;
	        auxMes = (auxMes < 10) ? ("0" + auxMes) : auxMes;
			var fechaFinal = dia+sep+auxMes+sep+(parseInt(anno)+1);
			return fechaFinal;
		} else {
	        var auxMes = parseInt(mes) + d;
	        auxMes = (auxMes < 10) ? ("0" + auxMes) : auxMes;
	        var fechaFinal = dia+sep+auxMes+sep+anno;
			return fechaFinal;
		}			 
	 } else if (interval == "yyyy") {
		 var anno =fecha.getFullYear();
		 var mes = fecha.getMonth() + 1;
		 var dia = fecha.getDate();
		 mes = (mes < 10) ? ("0" + mes) : mes;
		 dia = (dia < 10) ? ("0" + dia) : dia;
	     var auxMes = parseInt(anno) + d;
		 var fechaFinal = dia+sep+mes+sep+auxMes;
		 return fechaFinal;			 
	}
}

function limpiarArray(arrayActual) {
	var newArray = new Array();
	for (var i = 0; i < arrayActual.length; i++) {
	   if (arrayActual[i]){
	      newArray.push(arrayActual[i]);
	   }
	}
	return newArray;
}

function DateDiff(comparador, fecha1, fecha2, caso){

	var dateParts = fecha1.split("/");

	var date1 = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);

	dateParts = fecha2.split("/");

	var date2 = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);

	var res = -1; 

	switch(comparador)
	{
	case "m":

		var year1=date1.getFullYear();
		var year2=date2.getFullYear();
		var month1=date1.getMonth();
		var month2=date2.getMonth();
		if(month1===0){ //Tomar en cuenta si el mes es 0
			month1++;
			month2++;
		}


		switch(caso){
		//excluyendo ambos meses
		case 1:
			res = (year2 - year1) * 12 + (month2 - month1) - 1;
			break;

		case 2:
		default:
			//incluyendo 1 mes
			res = (year2 - year1) * 12 + (month2 - month1);
		break;

		case 3:
			//incluyendo ambos meses
			res = (year2 - year1) * 12 + (month2 - month1) + 1;
			break;
		}

		break;

	case "d":

		var dif = date2.getTime() - date1.getTime();
		res = Math.floor(dif/(1000*24*60*60));
		break;

	case "n": //MINUTOS
		
		dateParts = fecha1.split("/");
		var yearParts = dateParts[2].split(" ");
		var timeParts = yearParts[1].split(":");
		var dateHour1 = new Date(yearParts[0], dateParts[1] - 1, dateParts[0], timeParts[0], timeParts[1], timeParts[2]);
		
		dateParts = fecha2.split("/");
		yearParts = dateParts[2].split(" ");
		timeParts = yearParts[1].split(":");
		var dateHour2 = new Date(yearParts[0], dateParts[1] - 1, dateParts[0], timeParts[0], timeParts[1], timeParts[2]);
		
		var dif = dateHour2.getTime() - dateHour1.getTime();
		
		res = Math.round(dif / 60000);
		break;
	case "s": //MINUTOS
		
		dateParts = fecha1.split("/");
		var yearParts = dateParts[2].split(" ");
		var timeParts = yearParts[1].split(":");
		var dateHour1 = new Date(yearParts[0], dateParts[1] - 1, dateParts[0], timeParts[0], timeParts[1], timeParts[2]);
		
		dateParts = fecha2.split("/");
		yearParts = dateParts[2].split(" ");
		timeParts = yearParts[1].split(":");
		var dateHour2 = new Date(yearParts[0], dateParts[1] - 1, dateParts[0], timeParts[0], timeParts[1], timeParts[2]);
		
		var dif = dateHour2.getTime() - dateHour1.getTime();
		
		res = Math.round(dif / 1000);
		break;
		
	default:
		break;
	}

	return res;
}

function formatoFechaDatePicker(fecha){

	var dateParts = fecha.split("/");

	var date1 = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	return date1;
}

function modificarEstilosBotonera(divBusqueda,claseBusqueda,divSeleccionado,colorFondoSelect,colorTextoSelect,colorFondoNormal,colorTextoNormal){
	var listaBotones = $("#"+divBusqueda).find("."+claseBusqueda);
	listaBotones.css("background",""+colorFondoNormal);
	listaBotones.css("color",""+colorTextoNormal);
	$("#"+divSeleccionado).css("background",""+colorFondoSelect);
	$("#"+divSeleccionado).css("color",colorTextoSelect);
}

function validarIncorrectosValores(valor){
	if(valor == Infinity || valor == -Infinity || isNaN(valor)){
		return ConstanteServices.VALOR_DEFECTO;
	}else{
		return valor;
	}
}

function obtenerValorMaximoGrupo(lista){
	return Math.max.apply(Math,lista.map(function(o){return o.idGrupo;}));
}

function obtenerValorMaximoGrupoMicrobiologico(lista){
	return Math.max.apply(Math,lista.map(function(o){return o.grupo;}));
}

function removeOptions(cboBox)
{
	for(i = cboBox[0].options.length - 1 ; i >= 0 ; i--){
		cboBox[0].remove(i);
    }
}


document.onkeydown =
	  function updownintable(e) {
	    switch (e.key) {
	      case 'ArrowUp':
	    	var el = document.activeElement;
		    var rowNo = el.id.substring(el.id.indexOf("_")+1);
		    var fieldId = el.id.substring(0, el.id.indexOf("_"));  
	    	if (rowNo > 0) {
	    		if(typeof indicadorDVolumen  !== 'undefined'){
	    			if(indicadorDVolumen == 0 && fieldId == ConstanteServices.DIV_DVOLUMEN){
	    		    	rowNo = rowNo-2;	
	    		      }else{
	    		    	  rowNo--;  
	    		      }
	    		}else{
	    			rowNo--;
	    		}
	          var newId = fieldId+"_"+rowNo;
	          var newEl = document.getElementById(newId);
	          if (newEl != null) {
	        	  window.setTimeout (function(){ 
		              newEl.select(); 
		           },100);
	          }
	        }
	        break;
	      case 'ArrowDown':
	        var el = document.activeElement;
	        var rowNo = el.id.substring(el.id.indexOf("_")+1);
	        var fieldId = el.id.substring(0, el.id.indexOf("_"));
	        if(typeof indicadorDVolumen  !== 'undefined'){
    			if(indicadorDVolumen == 0 && fieldId == ConstanteServices.DIV_DVOLUMEN){
    				rowNo = parseFloat(rowNo)+2;	
    		      }else{
    		    	  rowNo++;  
    		      }
    		}else{
    			rowNo++;
    		}
	        var newId = fieldId+"_"+rowNo;
	        var newEl = document.getElementById(newId);
	        if (newEl != null) {
	        	window.setTimeout (function(){ 
		              newEl.select(); 
		           },100);
	        }
	        break;
	      case 'Enter':
	    	var el = document.activeElement;
	    	var rowNo = el.id.substring(el.id.indexOf("_")+1);
	    	var fieldId = el.id.substring(0, el.id.indexOf("_"));
	    	if(typeof indicadorDVolumen  !== 'undefined'){
    			if(indicadorDVolumen == 0 && fieldId == ConstanteServices.DIV_DVOLUMEN){
    				rowNo = parseFloat(rowNo)+2;	
    		      }else{
    		    	  rowNo++;  
    		      }
    		}else{
    			rowNo++;
    		}
	    	var newId = fieldId+"_"+rowNo;
	    	var newEl = document.getElementById(newId);
	    	if (newEl != null) {
	    		window.setTimeout (function(){ 
		              newEl.select(); 
		           },100);
	    	}
	    	break;
	      default:
	    	  try{
	    		  personalizado(e);
	    	  }catch(error){
	    		  console.log("no se encuentra la funcion personalizado");
	    	  }  
	    }
	  };
