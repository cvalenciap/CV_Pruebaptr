var OK = "";
var ERROR = "";
var IMAGEN_SUCCESS = "";
var IMAGEN_DANGER = "";
var MENSAJE_SESION_EXPIRADA = ""; 
var CONSTANTESPARAMETROS = [];
var textoItemSeleccionar = "";
var textoItemTodos		= "";


function asignarVariablesPeticionAjax(OK, ERROR, IMAGEN_SUCCESS, IMAGEN_DANGER, MENSAJE_SESION_EXPIRADA){
	this.OK = OK;
	this.ERROR = ERROR;
	this.IMAGEN_SUCCESS = IMAGEN_SUCCESS;
	this.IMAGEN_DANGER = IMAGEN_DANGER;
	this.MENSAJE_SESION_EXPIRADA = MENSAJE_SESION_EXPIRADA;
	
 	$('.tooltip-r').tooltip(); //permite inicializar el tooltip de bootstrap;
}

function clonarObjeto(objeto){
	return $.extend({}, objeto); // Object.assign({},objeto);
}

function asignarTextoItemSeleccionar(texto){
	this.textoItemSeleccionar = texto;
}

function obtenerTextoItemSeleccionar(){
	return this.textoItemSeleccionar;
}

function asignarTextoItemTodos(texto){
	this.textoItemTodos = texto;
}

function obtenerTextoItemTodos(){
	return this.textoItemTodos;
}

function asignarConstantesParametros(nombreParametro, valorParametro){
	var obj = {};
	obj.nombreParametro = nombreParametro;
	obj.valorParametro = valorParametro;
}

function limpiarConstantesParametros(){
	CONSTANTESPARAMETROS = [];
}

function obtenerValorConstanteParametroSession(nombreParametro){
	 $.each(CONSTANTESPARAMETROS, function(index) {
	        if(nombreParametro == CONSTANTESPARAMETROS[index].nombreParametro){
	            return CONSTANTESPARAMETROS[index].valorParametro;
	        }
	    });
	 
	 return "";
}

var tienePermisosOpcion = function(codigoOpcion, listaOpciones){
    var tienePermisos = false;
    $.each(listaOpciones, function(index) {
        if(codigoOpcion==listaOpciones[index].codigoOpcion){
            tienePermisos = true;
            return tienePermisos;
        }
    });
    return tienePermisos;
}

var validarFinDeSesion = function(jqXHR, textStatus, errorThrown, mensajeSesionExpirada, url){
    if(jqXHR.responseText != '' && jqXHR.responseText.indexOf(mensajeSesionExpirada) > -1){
        var url = jqXHR.responseText.split(",")[1];
        window.location.href = url;
   }else{
       alert('La aplicación ha detectado un error inesperado al ejecutar la operación seleccionada. Favor intentar nuevamente, en caso el error persista comuníquese con el área responsable.');
   }
}

function formatoFecha(){
	$('.fecha').mask('99/99/9999');
}

function formatoHora(){
	$('.hora').mask('99:99', {autoclear: false});
}


//function autocompletar(caller){
//	var index = caller.attr('row-index');
//	console.log(index);
//	
//}

function JSON2CSV(objArray) {
    var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;

    var str = '';
    var line = '';

    if ($("#labels").is(':checked')) {
        var head = array[0];
        if ($("#quote").is(':checked')) {
            for (var index in array[0]) {
                var value = index + "";
                line += '"' + value.replace(/"/g, '""') + '",';
            }
        } else {
            for (var index in array[0]) {
                line += index + ',';
            }
        }

        line = line.slice(0, -1);
        str += line + '\r\n';
    }

    for (var i = 0; i < array.length; i++) {
        var line = '';

        if ($("#quote").is(':checked')) {
            for (var index in array[i]) {
                var value = array[i][index] + "";
                line += '"' + value.replace(/"/g, '""') + '",';
            }
        } else {
            for (var index in array[i]) {
                line += array[i][index] + ',';
            }
        }

        line = line.slice(0, -1);
        str += line + '\r\n';
    }
    return str;
    
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
	        //if(head.visible == true && head.radio == false && head.checkbox == false && head.class != 'controls'){
	        if(head.visible == true && head.radio == false && head.checkbox == false && head['class']==undefined){
	            //if(!$(head).hasClass('controls')){
	                arregloCabecera.push(head);
	                if(title == ""){
	                    title = head.title;
	                }else{
	                    title = title + "," + head.title;
	                }
	            //}
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

function colorCelda (color){
	return {
	    css: {
	      'background' : color
	    }
  	}
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

function dateAdd(interval, d, fecha) {
	//interval >> d = dia | m = mes | yyyy == año
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

function horaMayor(sHora1, sHora2) { 
    if((sHora1 != null && sHora1.indexOf(":") != -1) || (sHora2 != null && sHora2.indexOf(":") != -1)) {
	    var arHora1 = sHora1.split(":"); 
	    var arHora2 = sHora2.split(":"); 
	     
	    // Obtener horas y minutos (hora 1)
	    var hh1 = parseInt(arHora1[0],10); 
	    var mm1 = parseInt(arHora1[1],10); 
	
	    // Obtener horas y minutos (hora 2)
	    var hh2 = parseInt(arHora2[0],10); 
	    var mm2 = parseInt(arHora2[1],10); 
	
	    // Comparar
	    if (hh1<hh2 || (hh1==hh2 && mm1<mm2)) 
	        return true; 
	    else if (hh1>hh2 || (hh1==hh2 && mm1>mm2)) 
	        return false; 
	    else  
	        return false; 
    } else {
    	return false; 
    }
}

function validarHora(hora){
	 var arHora = hora.split(":");
	 if(arHora.length==2){
		 if(arHora[0].length==2 && arHora[1].length==2){
			 return true;
		 }
	 }
	 return false;
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

function validarPosicionCabecera(clave, posicion, objHead){
    var claveCabecera = objHead[posicion].field;
    return claveCabecera==clave;
}

function formatearDatosPie(listaDatos){
    var objetoTemporal = null;
    if(listaDatos[listaDatos.length-1].value==0 || listaDatos[0].value==0){
        for(var posicion=0; posicion < listaDatos.length-2; posicion++){
            if(listaDatos[posicion].value>0){
                objetoTemporal = listaDatos[listaDatos.length-1];
                listaDatos[listaDatos.length-1] = listaDatos[posicion];
                listaDatos[posicion] = objetoTemporal;
                break;
            }
        }
    }
}


function contarCeros(listaDatos){
	var contador = 0;
	for(var posicion=0; posicion < listaDatos.length; posicion++){
		if(listaDatos[posicion].value == 0){
			contador++;
		}
	}
	return contador;
}

//para implementarlo se debe de poner onkeypress="return validarEntero(event)"
function validarEntero(e){
	var tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla==8){
        return true;
    }
        
    // Patron de entrada, en este caso solo acepta numeros
    var patron =/[0-9]/;
    var tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);	
}

function tamanioColumnaTabla(columnna , tamanio){
	$('th[data-field="'+columnna+'"] .th-inner').css("width",''+tamanio+'');
}

//para implementarlo se debe de poner onkeypress="return validarEntero(event)"
function validarEnteroConTamannio(e,dimension,componente){
	var tecla = (document.all) ? e.keyCode : e.which;

    //Tecla de retroceso para borrar, siempre la permite
    if (tecla==8){
        return true;
    }
        
    // Patron de entrada, en este caso solo acepta numeros
    var patron =/[0-9]/;
    var tecla_final = String.fromCharCode(tecla);
    var indicador = false;
    if(componente.value.length < dimension){
    	if(patron.test(tecla_final) == true){
        	indicador = true;
        }else{
        	indicador = false;
        }
    }else{
    	indicador = false;
    }    
    
    return indicador;	
}

function validarSiesNumero(numero){
    if (!/^([0-9])*$/.test(numero)){
    	return false;
    }else{
    	return true;
    }
 }

function redondearMonto (monto, cantidadDecimal){
	var redondeo= Math.round(monto * Math.pow(10, cantidadDecimal)) / Math.pow(10, cantidadDecimal).toFixed(cantidadDecimal);
	return redondeo;
}

function validarDecimal(event,element){

	var text  = ($(element).val()+ '').replace(/[^0-9,'.']/g, '');
    if ((text.indexOf('.') != -1) &&
	    (text.substring(text.indexOf('.')).length > 2) &&
	    (event.which != 0 && event.which != 8) &&
	    ($(element)[0].selectionStart >= text.length - 2)) {
    	  event.preventDefault();
	 }else{
		 $(element).val(text);
	 }     
	// $(element).val(text);
	  
	  
}
function validateDecimal(valor) {
    var RE = /^-?\d+(\.\d+)?$/;
    if (RE.test(valor)) {
        return true;
    } else {
        return false;
    }
}

/*
 * Error : type= "danger"
 *         icon= "fa fa-warning"
 *         mensajeError = "<spring:message code='liquidacion.seleccionar.courrier' javaScriptEscape='true'/>"
 *         
 * Correcto: type= "success"
 *           icon= "glyphicon glyphicon-ok"
 *           mensajeError = "<spring:message code='liquidacion.seleccionar.courrier' javaScriptEscape='true'/>"
 * 
 * Nota :esto "liquidacion.seleccionar.courrier" se configura en mensajes.properties 
 *           
 * */

function limpiarMensaje(idDiv){
	var idDiv= idDiv || "divMensajeInformacion";
	$('#'+idDiv).html('');
}

function cerrarModal(){
	$('#dialogComunConf #btnNoConfDialogComunConf').click();
}

function IsMenorAFechaActual(fechaEnviada){
	var fechaActual=obtenerfechaActualSinHoras();
	if( DateDiff("d", fechaEnviada , fechaActual , "") > 0){
		return true;
	}
	return false;
}

function IsMayorAFechaActual(fechaEnviada){
	var fechaActual=obtenerfechaActualSinHoras();	
	if( DateDiff("d",obtenerfechaActualSinHoras() ,fechaEnviada  , "") > 0){
		return true;
	}
	return false;
}

function IsMayorIgualFechaActual(fechaEnviada){
	var fechaActual=obtenerfechaActualSinHoras();	
	if( DateDiff("d",obtenerfechaActualSinHoras() ,fechaEnviada  , "") >= 0){
		return true;
	}
	return false;
}


function cargarListaProgressBar(idDiv,lista){
	
   var progreso ="";
	for(var i=0 ; i < lista.length ; i++ ){
		  		  
        var itemTitulo = '<div class="item-cell">'+
			'<div class="cell-col name-ruta">'+lista[i].titulo+'</div>'+
			'<div class="cell-col entregadas">'+
				'<div class="progress-custom-ruta">'+				
					'<div class="progress">';	
		
		var itemCuerpo="";
		
			var total = lista[i].valorInfo + lista[i].valorWarning + lista[i].valorSuccess ;
			var porcentajeInfo =  redondearMonto((lista[i].valorInfo * 100)/total ,2);
			var porcentajeWarning = redondearMonto( (lista[i].valorWarning * 100)/total ,2);
			var porcentajeSuccess =  redondearMonto((lista[i].valorSuccess * 100)/total ,2);
			
			itemCuerpo = itemCuerpo +  '<div class="progress-bar progress-bar-info" style="width:'+porcentajeInfo+'%">'+
											'<span class="sr-only">'+lista[i].valorInfo+'</span>'+
										'</div>'+										  
										'<div class="progress-bar progress-bar-warning" style="width:'+porcentajeWarning+'%">'+
											'<span class="sr-only">'+lista[i].valorWarning+'</span>'+
										'</div>'+											  
										'<div class="progress-bar progress-bar-success" style="width:'+porcentajeSuccess+'%">'+
											'<span class="sr-only">'+lista[i].valorSuccess+'</span>'+
										'</div>';
		
				
					
		var itemPie =	'</div>'+					
				'</div>'+
			'</div>'+
			'<div class="cell-col total"><span>'+total+'</span></div>'+
		'</div>';
		
		progreso = progreso + itemTitulo + itemCuerpo + itemPie;
	}
	
	  $('#'+idDiv).append(progreso);
	  
}

function mostrarMensaje(mensaje,estadoMensaje,idDiv){
	//idDiv,type,icon,mensajeError
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
	
	
    /*var mensajeHTML = '';
    mensajeHTML = '<div class="alert alert-'+type+'" role="alert" style="height: auto!important;">';
    mensajeHTML = mensajeHTML + '<i class="'+icon+'"></i>';
    mensajeHTML = mensajeHTML + mensajeError;
    $('#'+idDiv+' div').remove();
    $('#'+idDiv).append(mensajeHTML);*/
}

function evitarTraslapePieCuatro(listaDatos){
	var objetoTemporal = null;
	var cantidadCeros = contarCeros(listaDatos);
	if(cantidadCeros==2){
		//CASO 1
		if(listaDatos[0].value==0 && listaDatos[1].value==0 && listaDatos[2].value>0 && listaDatos[3].value>0){
			objetoTemporal = listaDatos[1];
			listaDatos[1] = listaDatos[2];
			listaDatos[2] = objetoTemporal;
		//CASO 2
		}else if(listaDatos[0].value==0 && listaDatos[1].value>0 && listaDatos[2].value>0 && listaDatos[3].value==0){
			objetoTemporal = listaDatos[2];
			listaDatos[2] = listaDatos[3];
			listaDatos[3] = objetoTemporal;
		}
	}else{
		if(listaDatos[listaDatos.length-1].value==0 || listaDatos[0].value==0){
			for(var posicion=1; posicion < listaDatos.length-2; posicion++){
				if(listaDatos[posicion].value == 0 && posicion==1){
					objetoTemporal = listaDatos[posicion];
					listaDatos[posicion] = listaDatos[posicion+1];
					listaDatos[posicion+1] = objetoTemporal;
				}else if(listaDatos[posicion].value>0){
					objetoTemporal = listaDatos[listaDatos.length-1];
					listaDatos[listaDatos.length-1] = listaDatos[posicion];
					listaDatos[posicion] = objetoTemporal;
					break;
				}
			}
		}
	}
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

function xmlhttpPost(strURL, queryStr) {
    var xmlHttpReq = false;
    var self = this;
    // Mozilla/Safari, opera etc
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }else{
        alert("no ajax")
        return
    }
    self.xmlHttpReq.open('GET', strURL, true);
    //self.xmlHttpReq.setRequestHeader('Content-Type', 'application/json'); //'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function() {
        if (self.xmlHttpReq.readyState == 4) {
            updatepageConfirm(self.xmlHttpReq.responseText);
        }
    }
    self.xmlHttpReq.send(); //queryStr
}

function updatepageConfirm(str){
    document.getElementById("divTest").innerHTML = str;
}

function Trim(cadena)
{
	//return $.trim(cadena);
	if(cadena != "" && cadena != null && cadena != undefined){
		return cadena.trim();
	}else{
		return cadena;
	}		
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
function ValidFecha(element, msj)
{
	// / Formato dd/mm/yyyy
	var fecha = $.trim(element.val());
	var filter = /^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[/\\/](19|20)\d{2}$/;
	if ( filter.test(fecha) )
	{
		return true;
	}
	else
	{
		if(msj != null)
			alert(msj);
		return false;
	}
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

function cleanCombo (element) {
	var html = '<option value="">--Seleccionar--</option>';
	element.html(html);
}

function llenarCombo (data, id, desc, element) {
    var html = '<option value="">--Seleccionar--</option>';
    if (data != null) {
        $.each(data, function(k, v) {
            html += '<option value="' + v[id] + '">' + v[desc] + '</option>';
        });
    }
    element.html(html);
}

function comboValorId(data, id, desc, element, valorId) {
    var html = '<option value="">--Seleccionar--</option>';
    if (data != null) {
        $.each(data, function(k, v) {
            html += '<option value="' + v[id] + '">' + v[desc] + '</option>';
        });
    }
    element.html(html);
    element.val(valorId);
}

function mostrarMensajeError(element, type, icon, mensajeError){
    var mensajeHTML = '';
    mensajeHTML = '<div class="alert alert-'+type+'" role="alert" style="height: auto!important;">';
    mensajeHTML = mensajeHTML + '<i class="'+icon+'"></i>';
    mensajeHTML = mensajeHTML + mensajeError;
    $('#' + element + ' div').remove();
    $('#' + element).append(mensajeHTML);
    
}

function agregarFileInput(idDiv, idFileInput, evtRemoveFile){
	
	var html = ''
		+ '<div class="fileupload fileupload-new" data-provides="fileupload">'
		//+ 	'<input type="hidden" value="" name>'
		+     '<span class="btn-file fa fa-file-text-o fa-2x">'
		+         '<span class="fileupload-new">&nbsp;</span>'
		+         '<span class="fileupload-exists" name>&nbsp;</span>'         
		+         '<input type="file" id='+ idFileInput + '>'
		+     '</span>'
		+     '<span class="fileupload-preview" id=' + 'carga' + idFileInput + '></span>'
		+     '<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none" id= close'+ idFileInput + '>×</a>'
		+ '</div>'
	    
	$('#' + idDiv).append(html);
	
	if(IsEmpty(evtRemoveFile) == false){
		$('#close'+ idFileInput + '').attr('onclick', evtRemoveFile);		
	}
}

//function agregarFileInput1(idDiv, idFileInput){
//	
//	var html = ''
//		+ '<div class="fileupload fileupload-new" data-provides="fileupload">'
//		+   '<span class="fa fa-file-text-o fa-2x" style="cursor: pointer;" id="'+ 'btn' + idFileInput +'" onclick="javascript:invocarFileInput(\'' + idFileInput + '\')"></span>'
//		+	'&nbsp;&nbsp;<label class="control-label" type="text" id="'+ 'lbl' + idFileInput +'"></label>'
//	    +		'<input type="file" style="display:none;" id='+ idFileInput + '>'
//	   //+	'</span>'
//	    +	'<span class="fileupload-preview" id=' + 'carga' + idFileInput + '></span>'
//	    +		'<a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">×</a>'
//	    + '</div>';
//	    
//	$('#' + idDiv).append(html);
//}

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

function validarArchivo(nombreArchivoInterno, nombreArchivoExterno, url, idDiv){
	
	var urlGeneral = url || '../../general';

	$.ajax({
		url: url + "/validarArchivo",
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
		
		if(respuesta.mensajeRespuesta == OK){
			descargarArchivo(nombreArchivoInterno, nombreArchivoExterno, url);
		}
		else if(IsEmpty(idDiv) == false){
			mostrarMensaje(respuesta.mensajeRespuesta, IMAGEN_DANGER, idDiv);
		}
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(respuesta.jqXHR, respuesta.textStatus, respuesta.errorThrown, MENSAJE_SESION_EXPIRADA);
	});
}

function descargarArchivo(nombreArchivoInterno, nombreArchivoExterno, url){
	var urlGeneral = url || '../../general';
	
	window.location.href = url + '/accionExportarArchivo?nSecuencial=' + nombreArchivoInterno 
	+ '&nVisible=' + nombreArchivoExterno;
}

function procesarExcel(fileArchivo, nomCabecera,idCliente){
	
	var obj = null;
	
	var formData = new FormData();
    formData.append("fileObj", document.getElementById(fileArchivo).files[0]);
    formData.append("idCliente", String(idCliente));
	formData.append("nomCabecera", nomCabecera);
       
      $.ajax({
           url : "../../general/leerArchivoExcel",
           type : "POST",
           dataType : "json",
           cache: false,
           processData: false,
           contentType: false,
           data : formData,
           async : false
       }).done(function(respuestaBean) {

    	   obj = respuestaBean;
    	   
       }).fail(function(jqXHR, textStatus, errorThrown) {
       	validarFinDeSesion(jqXHR, textStatus, errorThrown, 'sesion expirada');
       });
      
      return obj;
}

function AddKeyPressEvent(element, keyCode, lengthVal, method, elementClicked){
	element.bind('keypress', function(event){
		
		var keycode = event.keyCode || event.which;
		
		var elementVal = element.val();
		
		if(keyCode != null && lengthVal != null){
			if(keycode == keyCode && elementVal.length == lengthVal){
				if(method != null){
					window[method]();
				}
				
				if(elementClicked != null){
					elementClicked.click();
				}
			}
		}else if(keyCode != null){
			if(keycode == keyCode){
				if(method != null){
					window[method]();
				}
				
				if(elementClicked != null){
					elementClicked.click();
				}
			}
			
		}else if(lengthVal != null){
			if(elementVal.length == lengthVal){
				if(method != null){
					window[method]();
				}
				
				if(elementClicked != null){
					elementClicked.click();
				}
			}
		}		
		
	});
}



$body = $("body");
$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
    ajaxStop: function() { $body.removeClass("loading"); }    
});
     
/********/
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

function agregarItem(element, value, text){
	
	if(text == null){
		if(value == null){
			value = "-1";
		}
		text  = this.textoItemSeleccionar;
	}
	
	element.append($("<option/>").attr("value",value).text(text));
}

function agregarListaItem(element,lista, campoValue, campoText){
	  $.each(lista, function(i, item) {
		  element.append($("<option/>").attr("value",lista[i][campoValue] ).text(lista[i][campoText])); 
      });
}

function seleccionarItem(element, value){
	element.val(value);
}

function eliminarListaItem(element){
	element.empty(); 
}

function eliminarItemByIndex(element, index){
	element.removeOption(0);
}

function eliminarItemByValue(element, value){
	element.removeOption(value);
}

function eliminarItemSeleccionado(element){
	element.find('option:selected').remove();
}

function cantidadItem(element){
	return element.find('option').length;
}

function obtenerItem(lista, campoBusqueda, valorBusqueda){
	 
	var resultado;
	
	 $.each(lista, function(i, item) {
		  if(lista[i][campoBusqueda] == valorBusqueda){
			  resultado = item;
			  return false;
		  }
     });
	 
	 return resultado;
}

function obtenerTextoItemSeleccionado(element){
	var text = element.find("option:selected").text();
	return text;
}

function limpiarYAgregarItem(element, value, text){
	
	eliminarListaItem(element);
	agregarItem(element, value, text);
}

function  deshabilitarFormulario(element,estado){
	if(estado){
		element.find('input, textarea, button, select').attr('disabled','disabled');
	}else{
		element.find('input, textarea, button, select').removeAttr('disabled');
	}
} 

function deshabilitarElemento(element, estado){
	element.prop("disabled", estado);
}

/*campoBusqueda value o text */
function obtenerItemSelect(element, propBusqueda, valorBusqueda)
{
	var item = null;
	
	$(element.selector + ' option').each(function(){
	
			if(this[propBusqueda] == valorBusqueda){
				item = {};
				item.text = this.text;
				item.value = this.value;
			}
	});
	
	return item;
}

function obtenerIdDepartamentoPorUbigeo(idUbigeo){
	return idUbigeo.substring(0, 2);
}

function obtenerIdProvinciaPorUbigeo(idUbigeo){
 	return idUbigeo.substring(2, 4);
}

function obtenerIdDistritoPorUbigeo(idUbigeo){
 	return idUbigeo.substring(4, 6);
}


/*function formatearDecimalUS( numero ){
	return  numero.toLocaleString('en-US'); 
}

function desFormatearDecimalUS(numero){
	var regex = new RegExp(",","g");
	return numero.replace(regex, "");
}*/

function replaceAll(cadenaCompleta, caracterBuscar,caracterReeplazar){
	var regex = new RegExp(caracterBuscar,"g");
	return cadenaCompleta.replace(regex, caracterReeplazar);
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

function formatearMiles(entrada) {
	var partes = entrada.split(".");
	var input = partes[0]; 

	var num = input.replace(/\./g,'');
	if(!isNaN(num)){
		if(input.substring(0,1) == "-"){
			num = input.substring(1);
			input = "-";
		} else {
			input = "";
		}
		num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,');
		num = num.split('').reverse().join('').replace(/^[\,]/,'');
		num = input + num;
	}
	
	if(partes.length == 2){
		num = num + "." + partes[1];
	}
	
	return num;
}

function cortarCadena(cadena,posicionInicio,PosicionFin) {
	var resultado = cadena.slice(posicionInicio, PosicionFin);	
	return resultado;
}

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
     	  validarFinDeSesion(jqXHR, textStatus, errorThrown, '<%= ConstanteServices.MENSAJE_SESION_EXPIRADA %>');
     });
}

function formatearNumeroPuntoDecimalComaMil(nStr) {
    if(IsEmpty(nStr) == false){
    	nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
                x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
    }else{
    	return "0";
    }	
}


function replaceAll(str, term, replacement) {
	return str.replace(new RegExp(term.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"), 'g'), replacement);
}

function validarLetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    if (tecla == 9)
        return true;
    if (tecla == 11)
        return true;
    patron = /[A-Za-zÃ±Ã\s]/;
    letras_latinas = /^[a-zA-ZáéíóúàèìòùÀÈÌÒÙÁÉÍÓÚñÑüÜ_\s]+$/;
    te = String.fromCharCode(tecla);
    return letras_latinas.test(te);
}

function eliminarArchivo(nombreArchivoInterno, url, idDiv){
	
	var urlGeneral = url || '../../general';
    var retorno = false;
	$.ajax({
		url: url + "/accionEliminarArchivo",
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
		
		if(respuesta.mensajeRespuesta == OK){
			retorno = true;
		}
		else if(IsEmpty(idDiv) == false){
			mostrarMensaje(respuesta.mensajeRespuesta, IMAGEN_DANGER, idDiv);
		}
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(respuesta.jqXHR, respuesta.textStatus, respuesta.errorThrown, MENSAJE_SESION_EXPIRADA);
	});
	return retorno;
}

function obtenerfechaActual(){
  var fechaActual = new Date();
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

function sumarMinutosFecha(fechaActual,cantidadMinutos){
	  //var fechaActual = new Date();
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


function obtenerHoraActual(){
	var fechaActual = new Date();
	var hora = String(fechaActual.getHours());
	var minuto =  String(fechaActual.getMinutes());
	var sFecha = (hora+":"+minuto);
	return sFecha;
	
}


function obtenerfechaActualSinHoras(){
	  var fechaActual = new Date();
	  var dia = String(fechaActual.getDate());
	  var mes = String((fechaActual.getMonth() +1));
	  var annio = String(fechaActual.getFullYear());
	
	  if(dia.length == 1){
		  dia = "0"+dia;
	  }
	  if(mes.length == 1){
		  mes = "0"+mes;
	  }
	  
	  var sFecha = (dia+ "/" + mes + "/" + annio);
	  return sFecha;
}


var geoMaps = {
		 
	map  			    : null,        
	
	initGeoMaps: function (llave){
		map = new GeodirClient(llave);
	},
	getCoordenadasGeoMaps: function (idDireccion,ubigeo,direccion,keyGeo){
		var resultado = map.geocodeAddressForward(ubigeo,direccion).then(function(res) {
			if(res.address[0].results[0].nivelGeo == "NIVEL_LOTE" ||
					res.address[0].results[0].nivelGeo == "NIVEL_PUERTA" ||
					res.address[0].results[0].nivelGeo == "NIVEL_PUERTA_NUCLEO" ||
					res.address[0].results[0].nivelGeo == "NIVEL_MANZANA" ){
				var estado = res.status;
				console.log("estado del Geo "+estado);
				var latitud = res.address[0].results[0].geometry.coordinates[1];
				var longitud = res.address[0].results[0].geometry.coordinates[0];
				var direccionNormalizada = res.address[0].results[0].properties.label;
				var direccion = res.address[0].results[0].properties.direccion;
				
				var datos = {  "estado": estado,
		                       "latitud":latitud,
		                       "longitud":longitud,
		                       "direccionNormalizada": direccionNormalizada,
		                       "direccion":direccion
	             };
				
				return datos;
			}else{
				console.log("NO CUMPLE CON LOS NIVELES DE GEOCODIFICACION PERMITIDOS");
				var datos = {  "estado": "No Cumple GEO"}	    	 
		    	return datos;
			}
			
	      }).catch(function(err) {
	    	  var datos = {  "estado": err}	    	 
	    	  return datos;
	      });
		
		return resultado;
		
		
	}
};

var maps = {	
		map 			  : null,
		latitudporDefecto : "-12.043333",
		longitudporDefecto: "-77.028333",
		dirNormPorDefecto : "",
		idDivMap		  : "",
		divLatitud		  : null,
		divLongitud		  : null,
		txtDireccionNormalizada: null,
		initGoogleMaps: function(idDivMap, divLatitud, divLongitud, txtDireccionNormalizada) {
			maps.idDivMap=idDivMap;
			maps.divLatitud=(divLatitud != undefined ? divLatitud: null);
			maps.divLongitud=(divLongitud != undefined ? divLongitud: null);
			maps.txtDireccionNormalizada=(txtDireccionNormalizada != undefined ? txtDireccionNormalizada: null);
			if(navigator.geolocation){
			      //el navegador tiene soporte para geolocalización
			      //obteniendo la posicion actual
				navigator.geolocation.getCurrentPosition(maps.onSuccessGeolocating, maps.onErrorGeolocating, {
               		enableHighAccuracy: true,
               		maximumAge:         5000,
               		timeout:            10000
                });
			}else{
			   //El navegador no cuenta con soporte para geolocalizacion.
				maps.printarMapa(latitudporDefecto, longitudporDefecto);
			}		    
	    },
	    onSuccessGeolocating: function(position) {				
		    //se encontro la posicion actual
		    var miLatitud  = position.coords.latitude;
		    var miLongitud  = position.coords.longitude;
		    
//		    if(maps.isLoading == true){
//		    	 maps.printarMapa(maps.latitudporDefecto, maps.longitudporDefecto);
//		    }else{
		    	maps.printarMapa(miLatitud, miLongitud);
//		    }
		},
		onErrorGeolocating: function(error) {	   
		   //ocurrio un error al buscar la posicion actual
			latitudporDefecto = "-12.043333";
			longitudporDefecto = "-77.028333";
			maps.printarMapa(latitudporDefecto, longitudporDefecto);
		},
	    formEditMarkerTemplate: function() {
	    	var html = '<form class="edit_marker" action="#" method="post" data-marker-index="{{index}}">';
	    	html+=	 		'<div id="info_window">';
	    	html+=		         '<span id="spDireccionTotalMap"></span><br/>';
	    	html+=		         '<strong>Latitud:</strong><span>{{lat}} | </span>';
	    	html+=				 '<strong>Longitud:</strong><span> {{lng}}</span><br/><br/>'; 
	    	//html+=		         '<span><button type="button" class="btn btn-primary" onclick="maps.getCoordenadasMaps();">Obtener Altitud</button></span>';
	    	html+=				 '<input type="hidden" id="idLatitudMaps" value="{{lat}}" />';
	    	html+=				 '<input type="hidden" id="idLongitudMaps" value="{{lng}}" />';
	    	html+=			'</div>';
	    	html+=		'</form>';
	    	return html;					      
	    },
	    getCoordenadasMaps: function() {
			  var nuevalatitudMaps = $("#idLatitudMaps").val();
			  var nuevaLongitudMaps = $("#idLongitudMaps").val();
			  if(maps.divLatitud != null && !IsEmpty(""+nuevalatitudMaps)) {
				  (maps.divLatitud).text(cortarCadena(""+nuevalatitudMaps,0,13));
			  } if(maps.divLongitud != null && !IsEmpty(""+nuevaLongitudMaps)) {
				  (maps.divLongitud).text(cortarCadena(""+nuevaLongitudMaps,0,13));
			  } if(maps.txtDireccionNormalizada != null) {
				  maps.txtDireccionNormalizada.text("");
			  }
	    },
	    getNuevaDireccion: function(){
	    	var direccionNormalizada = '';
	    	if(map.markers.length > 0){
	    		var geocoder = new google.maps.Geocoder(); 
	    		var marker = map.markers[0];
	    		geocoder.geocode({'latLng': marker.getPosition()}, function(results, status) {
	    			if(status == google.maps.GeocoderStatus.OK) {
	    				direccionNormalizada=results[0]['formatted_address'];	
	    				direccionNormalizada = (!IsEmpty(direccionNormalizada) ? direccionNormalizada : "");	    				
	    				$("#spDireccionTotalMap").text(""+direccionNormalizada);	
	    				if(maps.txtDireccionNormalizada != null) {
	    					(maps.txtDireccionNormalizada).text(""+direccionNormalizada);
	    				}
	    			}	    		
		    	});
	    	}  	
        },
	    eliminarMarcasMaps: function(){
			  var listaMarcas = map.markers;
			  for(var i=0;i<listaMarcas.length;i++){
			    map.markers[i].setMap(null);
			  }
		},	   
		setTxtsMaps: function(){
		   if(map.markers.length > 0){
		    	var marker = map.markers[0];
		    	google.maps.event.trigger(map.markers[0], "click");	
		        var lat = marker.position.lat();
		        var lng = marker.position.lng();
		        $("#idLatitudMaps").val(""+lat); 	
		        $("#idLongitudMaps").val(""+lng);
		        if(maps.divLatitud != null && !IsEmpty(""+lat)) {
		        	(maps.divLatitud).text(""+cortarCadena(""+lat,0,13));
		        } if(maps.divLongitud != null && !IsEmpty(""+lng)) {
		        	(maps.divLongitud).text(""+cortarCadena(""+lng,0,13));
		        } if(maps.txtDireccionNormalizada != null) {
		        	maps.txtDireccionNormalizada.text("");
				}
		    }
		},		
	    printarMapa: function(latitud, longitud) {
	    	latitud = Number(latitud);
	  		longitud = Number(longitud);
	  		map = new GMaps({
	  			  div: maps.idDivMap,
	  			  lat: latitud,
	  			  lng: longitud
	  		});
	  		GMaps.on('click', map.map, function(event) {
	  		    var index = map.markers.length;
	  		    var lat = event.latLng.lat();
	  		    var lng = event.latLng.lng();		
	  		    var template =  maps.formEditMarkerTemplate();		
	  		    var content = template.replace(/{{index}}/g, index).replace(/{{lat}}/g, lat).replace(/{{lng}}/g, lng);	
	  		    maps.eliminarMarcasMaps();
	  		    map.markers = [];	
	  		    map.addMarker({
		  		      lat: lat,
		  		      lng: lng,
		  		      title: 'Marker #' + index,
		  		      infoWindow: {
		  		        content : content
		  		      }
	  		    });
	  		    maps.setTxtsMaps();    		    		    
	  		});
	    },
	    buscarDireccionMaps: function(address){
	    	GMaps.geocode({
				  address: address,
				  callback: function(results, status) {
					  if (status == 'OK') {
						      var latlng = results[0].geometry.location;
						      var index = map.markers.length;
						      var lat = latlng.lat();
						      var lng = latlng.lng();		
						      var template = maps.formEditMarkerTemplate();
						      var content = template.replace(/{{index}}/g, index).replace(/{{lat}}/g, lat).replace(/{{lng}}/g, lng);
						      var dirNorm = results[0]['formatted_address'];
//						      
//						      maps.latitudRes = lat;
//						      maps.longitudRes = lng;
//						      maps.dirNormRes = dirNorm;
						      
						      maps.eliminarMarcasMaps();
							  map.markers = [];	
							  map.setCenter(latlng.lat(), latlng.lng());
							  map.addMarker({
							    lat: latlng.lat(),
							    lng: latlng.lng(),
							    title: 'Marker #' + index,
							    infoWindow: {
							      content : content
							    }
							  });	
							  maps.setTxtsMaps();
							  maps.getCoordenadasMaps();	
							  
							  setTimeout(function() {
 								  maps.getCoordenadasMaps();
								  $("#spDireccionTotalMap").text(dirNorm);
							  }, 1000); 
					 }
				  }
	    	});
	    },
	    buscarDireccionMapsMant: function(address){
	    	GMaps.geocode({
				  address: address,
				  callback: function(results, status) {
					  if (status == 'OK') {
						      var latlng = results[0].geometry.location;
						      maps.latitudporDefecto = latlng.lat();
						      maps.longitudporDefecto = latlng.lng();		
						      maps.dirNormPorDefecto = results[0]['formatted_address'];
						      var template = maps.formEditMarkerTemplate();
						      var content = template.replace(/{{index}}/g, 0).replace(/{{lat}}/g, maps.latitudporDefecto).replace(/{{lng}}/g, maps.longitudporDefecto);
						      
						      
						      map = new GMaps({
					  			  div: maps.idDivMap
					  		  });
						      
						      var index = map.markers.length;
						      
						      maps.eliminarMarcasMaps();
							  map.markers = [];	
							  map.setCenter(latlng.lat(), latlng.lng());
							  map.addMarker({
							    lat: latlng.lat(),
							    lng: latlng.lng(),
							    title: 'Marker #0',
							    infoWindow: {
							      content : content
							    }
							  });	
							  maps.setTxtsMaps();
							  maps.getCoordenadasMaps();	
							  
//							  setTimeout(function() {
//								  $("#spDireccionTotalMap").text(maps.dirNormPorDefecto);
//							  }, 2000); 
					  }
				  }
	    	});
	    }
//	    ,setReturnsMaps : function(){
//        	maps.txtDirNorm.val(txtDirNormMap.text());
//			maps.txtLat.val(txtLatitudMap.text());
//			maps.txtLong.val(txtLongitudMap.text());
//	    }
};

//tracking 

var itemCollapse = function(){

    $(document).ready( function() {

        if ($(window).width() < 668) {
            $('.btn-collapse').trigger('click');
        }
        else {
            // $('.btn-collapse').trigger('click');
        }
    });

    $(window).resize(function() {

        if ($(window).width() < 668) {
            //$('.btn-collapse').trigger('click');
        }
        else {
            // alert('salio');
        }
    });

    // $('body').on('click', '.remove-item-publico', function(){
    //     $('.btn-item-resp').trigger('click');
    // });
    
};

//functiones de Graficos

//funcion para llamar al grafico
function generarGrafico(listaDataGrafico,idCanvaGrafico){
//par aprobar
//    var idCanvaGrafico = "canvasServicioAjustado";
//	var listaDataGrafico = [];
//	var itemGrafico1 = {
//	     cantidad  : 70,
//		 titulo    : 'Entregados',
//		 colorHtml : '#0a95ff'
//	}
//	var itemGrafico2 = {
//	     cantidad  : 30,
//		 titulo    : 'Cliente',
//		 colorHtml : '#ffba00'
//	}
//	var itemGrafico3 = {
//	     cantidad  : 70,
//		 titulo    : 'CX',
//		 colorHtml : '#acacac'
//	}
//	listaDataGrafico.push(itemGrafico1);
//	listaDataGrafico.push(itemGrafico2);
//	listaDataGrafico.push(itemGrafico3);
//	generarGrafico(listaDataGrafico,idCanvaGrafico)
//	  la lista debe tener los siguientes atributos
//	  cantidad : es la cantidad en porcentaje de la porcion de la torta
//	  titulo :  es el titulo que se vera en el grafico esto para cada item
//	  ejemplo 
//	  var itemGrafico = {
//			  cantidad : 2,
//			  titulo : 'prueba',
//	          colorHtml : '#0a95ff'
//	  }
      var dataSet = [];
      //aca lo parceamos a una lista para que el pie lo contemple
      $.each(listaDataGrafico, function(i, itemGrafico) {
    	 if(itemGrafico.cantidad !=0 ){
         	dataSet.push({value: itemGrafico.cantidad,color: obtenerColores(itemGrafico.colorHtml), title: itemGrafico.titulo});
    	 }
      });
      var formatoValor = '\u003C'+'%=formatearPorcentaje(v1,v2,v6)%'+'\u003E';
      var datos = dataSet;
	  //Inicio creacion de grafico circular
	  var startWithDataset =1;
	  var startWithData =1;
	  var opciones = { 	        
	        animationStartWithDataset : startWithDataset,
            animationStartWithData : startWithData,
            animateRotate : true,
            animateScale : false,
            animationByData : false,
            animationSteps : 50,
            canvasBorders : false,
            canvasBordersWidth : 1,
            canvasBordersColor : "gray",
            legend : false,
            maxLegendCols : 1,
            legendPosX:4,
            legendPosY:-2,
            legendXPadding:0,
            legendYPadding:0,
            animationEasing: "linear",
            annotateDisplay : true,
            spaceBetweenBar : 5,
            graphTitleFontSize: 14,
            segmentShowStroke: false,
            inGraphDataShow : false
	  }  
	  $("#"+idCanvaGrafico).attr("width",210).attr("height", 110);	 
	  new Chart(document.getElementById(""+idCanvaGrafico).getContext("2d")).Pie(datos, opciones);
	  //Fin grafico
}

function colorAleatorio(){ 
	   var hexadecimal = new Array("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F");
	   var colorAleatorio = "#";
	   var contador; 
	   for (contador=0;contador<6;contador++){ 
	      var posarray = aleatorio(0,hexadecimal.length); 
	      colorAleatorio += hexadecimal[posarray];
	   }
	   return colorAleatorio;
  }  

function obtenerColores(indicador){ 
	 var  color = "";
	 if(IsEmpty(indicador)){
		 color = colorAleatorio();
	 }else{
		 color = indicador;
	 }  
	 return color;
} 
function aleatorio(inferior,superior){ 
    var numPosibilidades = superior - inferior;
    var aleat = Math.random() * numPosibilidades; 
    aleat = Math.floor(aleat);
    return parseInt(inferior) + aleat; 
}

function formatearPorcentaje(texto,valor,porcentaje) {
	var resultado = '';
    if (valor != null && valor > 0) {
    	resultado = texto + ": " + formatoNumeroComas(valor) + " (" + porcentaje + " %)";
	} 
    return resultado;
} 

function formatoNumeroComas(numero){
	numero = numero.toString();
    var pattern = /(-?\d+)(\d{3})/;
    while (pattern.test(numero)){
           numero = numero.replace(pattern, "$1,$2");
    }
    return numero;
}

function cargarListaProgressBarPersonalizado(idDiv,lista){
	
	    var progreso = "";
	    var sumaTotal = 0;;
	    var sumaEntregados = 0;
	    var sumaExcepcion = 0;
	    var sumaPendientes = 0;
	    var htmlHub = "";
		for(var i=0 ; i < lista.length ; i++ ){
			  		  
	        var itemTitulo = '<div class="item-cell">'+
				'<div class="cell-col name-ruta">'+lista[i].nombreRuta+'</div>'+
				'<div class="cell-col entregadas">'+
					'<div class="progress-custom-ruta">'+				
						'<div class="progress">';	
			
			var itemCuerpo="";
			
				var total = lista[i].cantidadEntregados + lista[i].cantidadExcepciones + lista[i].cantidadPendientes ;
				sumaTotal = sumaTotal + total;
				sumaEntregados = sumaEntregados + lista[i].cantidadEntregados;
				sumaExcepcion = sumaExcepcion + lista[i].cantidadExcepciones;
				sumaPendientes = sumaPendientes + lista[i].cantidadPendientes;
				var porcentajeInfo =  redondearMonto((lista[i].cantidadEntregados * 100)/total ,2);
				var porcentajeWarning = redondearMonto( (lista[i].cantidadExcepciones * 100)/total ,2);
				var porcentajeSuccess =  redondearMonto((lista[i].cantidadPendientes * 100)/total ,2);
				
				itemCuerpo = itemCuerpo +  '<div class="progress-bar progress-bar-info" style="width:'+porcentajeInfo+'%">'+
												'<span class="sr-only">'+lista[i].cantidadEntregados+'</span>'+
											'</div>'+										  
											'<div class="progress-bar progress-bar-warning" style="width:'+porcentajeWarning+'%">'+
												'<span class="sr-only">'+lista[i].cantidadExcepciones+'</span>'+
											'</div>'+											  
											'<div class="progress-bar progress-bar-success" style="width:'+porcentajeSuccess+'%">'+
												'<span class="sr-only">'+lista[i].cantidadPendientes+'</span>'+
											'</div>';
			
					
						
			var itemPie =	'</div>'+					
					'</div>'+
				'</div>'+
				'<div class="cell-col total"><span>'+total+'</span></div>'+
			'</div>';
			
			progreso = progreso + itemTitulo + itemCuerpo + itemPie;
		}
		var porcentajeEntregados = redondearMonto((sumaEntregados * 100)/sumaTotal ,2);
		var porcentajeExcepciones = redondearMonto((sumaExcepcion * 100)/sumaTotal ,2);
		var porcentajePendientes = redondearMonto((sumaPendientes * 100)/sumaTotal ,2);
		htmlHub+="<div class='cell-col name-ruta'><span>Hub:</span></div>";
		htmlHub+="<div class='cell-col entregadas'>";
		htmlHub+="	<div class='progress-custom-ruta'>";
		htmlHub+="		<div class='progress'>";
		htmlHub+="			<div class='progress-bar progress-bar-info' style='width:"+porcentajeEntregados+"%'>";
		htmlHub+="				<span class='sr-only'>"+sumaEntregados+"</span>";
		htmlHub+="			</div>";
		htmlHub+="			<div class='progress-bar progress-bar-warning' style='width:"+porcentajeExcepciones+"%'>";
		htmlHub+="				<span class='sr-only'>"+sumaExcepcion+"</span>";
		htmlHub+="			</div>";
		htmlHub+="			<div class='progress-bar progress-bar-success' style='width:"+porcentajePendientes+"%'>";
		htmlHub+="				<span class='sr-only'>"+sumaPendientes+"</span>";
		htmlHub+="			</div>";
		htmlHub+="		</div>";
		htmlHub+="	</div>";
		htmlHub+="</div>";
		htmlHub+="<div class='cell-col total'>";
		htmlHub+="	<span class='label-cantidad'><span>"+sumaTotal+"</span> Total</span>";
		htmlHub+="</div>";
		$("#divProgressGestionHubTitulo").html(htmlHub);
		$('#'+idDiv).html(progreso);
		  
	}

function cargarListaProgressBarTipoServicio(idDiv,lista){
	
	   var progreso ="";
	   var sumaPendiente = 0;
	   var sumaEntregados = 0;
	   var sumaExcepcion = 0 ; 
		for(var i=0 ; i < lista.length ; i++ ){
			  		  
	        var itemTitulo = '<div class="item-cell" style = "height: 120px;text-align-last: end;">'+
				'<div class="cell-col name-ruta" style="transform: rotate(90deg);position: absolute;margin-top: 30px;">'+lista[i].nombreServicio+'</div>'+
				'<div class="cell-col entregadas">'+
					'<div class="progress-custom-ruta">'+				
						'<div class="progress">';	
			
			var itemCuerpo="";
			
				var total = lista[i].cantidadEntregados + lista[i].cantidadExcepcion + lista[i].cantidadPendiente ;
				sumaPendiente = sumaPendiente + lista[i].cantidadPendiente;
				sumaEntregados = sumaEntregados + lista[i].cantidadEntregados;
				sumaExcepcion = sumaExcepcion + lista[i].cantidadExcepcion;
				var porcentajeInfo =  redondearMonto((lista[i].cantidadEntregados * 100)/total ,2);
				var porcentajeWarning = redondearMonto( (lista[i].cantidadExcepcion * 100)/total ,2);
				var porcentajeSuccess =  redondearMonto((lista[i].cantidadPendiente * 100)/total ,2);
				
				itemCuerpo = itemCuerpo +  '<div class="progress-bar progress-bar-info" style="height: 20px;width:'+porcentajeInfo+'%">'+
												'<span class="sr-only" style = "transform: rotate(90deg);text-align-last: center;width: 20px;">'+lista[i].cantidadEntregados+'</span>'+
											'</div>'+										  
											'<div class="progress-bar progress-bar-warning" style="height: 20px;width:'+porcentajeWarning+'%">'+
												'<span class="sr-only" style = "transform: rotate(90deg);text-align-last: center;width: 20px;">'+lista[i].cantidadExcepcion+'</span>'+
											'</div>'+											  
											'<div class="progress-bar progress-bar-success" style="height: 20px;width:'+porcentajeSuccess+'%">'+
												'<span class="sr-only" style = "transform: rotate(90deg);text-align-last: center;width: 20px;">'+lista[i].cantidadPendiente+'</span>'+
											'</div>';
			
					
						
			var itemPie =	'</div>'+					
					'</div>'+
				'</div>'+
				'<div class="cell-col total" style = "transform: rotate(90deg);text-align-last: center;"><span>'+total+'</span></div>'+
			'</div>';
			
			progreso = progreso + itemTitulo + itemCuerpo + itemPie;
		}
		  $("#lblPendienteTS").text(""+sumaPendiente);
		  $("#lblExcepcionTS").text(""+sumaExcepcion);
		  $("#lblEntregadosTS").text(""+sumaEntregados);
		  $('#'+idDiv).html(progreso);
		  
	}

function cargarListaProgressBarAvanceVisitas(idDiv,lista){
	
    var progreso = "";
    var sumaTotal = 0;
    var sumaVisitadas = 0;
    var sumaNoVisitas = 0;
    var htmlHub = "";
	for(var i=0 ; i < lista.length ; i++ ){
		  		  
        var itemTitulo = '<div class="item-cell">'+
						 	'<div class="cell-col name-ruta">'+lista[i].nombreRuta+'</div>'+
						 	'<div class="cell-col entregadas">'+
						 		'<div style="float: left;margin-left: 90px;">'+
						 		  '<span>'+lista[i].visitas_12+' %</span>'+
					    	    '</div>'+
					    	    '<div style="float: left;margin-left: 230px;">'+
					    	      '<span>'+lista[i].visitas_17+' %</span>'+
					            '</div>&nbsp;'+
						 		'<div class="progress-custom-ruta">'+				
						 			'<div class="progress">';	
		var itemCuerpo="";
		
			var total = lista[i].cantidadVisitas + lista[i].cantidadNoVisitas ;
			sumaTotal = sumaTotal + total;
			sumaVisitadas = sumaVisitadas + lista[i].cantidadVisitas;
			sumaNoVisitas = sumaNoVisitas + lista[i].cantidadNoVisitas;
			var porcentajeInfo =  redondearMonto((lista[i].cantidadVisitas * 100)/total ,2);
			var porcentajeWarning = redondearMonto( (lista[i].cantidadNoVisitas * 100)/total ,2);
			//var porcentajeSuccess =  redondearMonto((lista[i].cantidadPendientes * 100)/total ,2);
			
			itemCuerpo = itemCuerpo +  '<div class="progress-bar progress-bar-info" style="height: 26px;width:'+porcentajeInfo+'%">'+
											'<span class="sr-only" style="z-index: 2;color: #acacac;margin-top: 22px;margin-right: -46px;">'+porcentajeInfo+' %</span>'+
										'</div>'+										  
										'<div class="progress-bar progress-bar-warning" style="z-index: 1;height: 26px;background : #eaf1f8;width:'+porcentajeWarning+'%">'+
//											'<span class="sr-only" style="margin-top: 22px;text-align: left;margin-left: 13px;"><font style="color: #acacac;">'+porcentajeWarning+' %</font></span>'+
										'</div>';
		
				
			
		var fecha_atraso = 	lista[i].FECHA_ATRASO == undefined || lista[i].FECHA_ATRASO == null ? "" : lista[i].FECHA_ATRASO; 
		var hora_atraso = 	lista[i].HORA_ATRASO == undefined || lista[i].HORA_ATRASO == null ? "" : lista[i].HORA_ATRASO; 
		var itemPie =			'</div>'+					
				        	'</div>'+
				        '</div>'+
				        	'<div class="cell-col total"><span>'+fecha_atraso+'<br><font color = "red">'+hora_atraso+'</font></span></div>'+
				        '</div>';
		
		progreso = progreso + itemTitulo + itemCuerpo + itemPie;
	}
	var porcentajeVisitado = redondearMonto((sumaVisitadas * 100)/sumaTotal ,2);
	var porcentajeNoVisitado = redondearMonto((sumaNoVisitas * 100)/sumaTotal ,2);
	htmlHub+="<div class='cell-col name-ruta'><span>Hub:</span></div>";
	htmlHub+="<div class='cell-col entregadas'>";
	htmlHub+="	<div class='progress-custom-ruta'>";
	htmlHub+="		<div class='progress'>";
	htmlHub+="			<div class='progress-bar progress-bar-info' style='height: 26px;width:"+porcentajeVisitado+"%'>";
	htmlHub+="				<span class='sr-only' style='z-index: 2;color: #acacac;margin-top: 22px;margin-right: -46px;'>"+porcentajeVisitado+" %</span>";
	htmlHub+="			</div>";
	htmlHub+="			<div class='progress-bar progress-bar-warning' style='z-index: 1;height: 26px;background : #eaf1f8;width:"+porcentajeNoVisitado+"%'>";
//	htmlHub+="				<span class='sr-only' style='margin-top: 22px;text-align: left;margin-left: 13px;'><font style='color: #acacac;'>"+porcentajeNoVisitado+" %</font></span>";
	htmlHub+="			</div>";
	htmlHub+="		</div>";
	htmlHub+="	</div>";
	htmlHub+="</div>";
	htmlHub+="<div class='cell-col total'>";
	htmlHub+="	<span class='label-cantidad'><span></span></span>";
	htmlHub+="</div>";
	$("#divProgressAvanceVisitaTitulo").html(htmlHub);
	$("#lblVisitas").text(""+sumaVisitadas);
	$("#lblNoVisitas").text(""+sumaNoVisitas);
	$('#'+idDiv).html(progreso);
}

function cargarListaProgressBarBarrasSalidas(idDiv,lista){
	var sumaTotal = 0;
	var htmlItem = "";
	for(var i=0 ; i < lista.length ; i++ ){
		sumaTotal = sumaTotal + parseInt(lista[i].cantidadSalida);
	}
	for(var i=0 ; i < lista.length ; i++ ){
		var porcentajeInfo =  redondearMonto((parseInt(lista[i].cantidadSalida) * 100)/sumaTotal ,2);
		htmlItem += "<div class='right pendiente-ingreso' style='width: 1%;'>";
		htmlItem += "	<div class='barra line' style='text-align: center;'>";
		htmlItem += "		<div class='vertical-barra'>";
		htmlItem += "			<span class='progress-dinamic' style='height: "+porcentajeInfo+"%;background-color: #0994ff;'></span>";
		htmlItem += "		</div>";
		htmlItem += "	</div>";
		htmlItem += "	<div style='text-align: center;'><span><font size='1'>"+porcentajeInfo+"%</font></span></div>";
		htmlItem += "	<div class='name-date' style='text-align: center;'>";
		htmlItem += ""+lista[i].nombreDepartamento;
		htmlItem += "	</div>";
		htmlItem += "</div>";		
	}
	$('#'+idDiv).html(htmlItem);
}

function cargarListaEstadosTranking(idDiv,lista){
	var htmlItem = "";
	var listaIconosEstados = initIconosTracking();
	for(var i=0 ; i < lista.length ; i++ ){
		var objetoIconEstado = buscarClaseIconoTracking(lista[i].nombreEstado,listaIconosEstados);
		if(objetoIconEstado != null && objetoIconEstado != undefined){
			if(lista[i].indicador == "1" || lista[i].indicador == "2" ){
				var claseHabilitado = String(lista[i].indicador) == "2" ? "" : "-disabled";
				htmlItem += "<div class='"+objetoIconEstado.nombreclase+claseHabilitado+"' style='width: 150px;'>";
				htmlItem += "	<div class='state-paso'>";
				htmlItem += "		<div class='state-paso-ico'><i></i></div>";
				htmlItem += "	</div>";
				htmlItem += "	<div class='fecha-proceso'>"+lista[i].fechaEstado+"</div>";
				htmlItem += "	<div class='estado-proceso'>"+objetoIconEstado.etiquetaHtml+"</div>";
				htmlItem += "</div>";
			}
		}				
	}
	$('#'+idDiv).html(htmlItem);
}

function cargarListaEstadosPiezasTranking(idDiv,listaPiezas){
	var htmlItem = "";
	$('#'+idDiv).html("");
	var listaIconosEstados = initIconosTracking();
	for(var i=0 ; i < listaPiezas.length ; i++ ){
		htmlItem += "<div class='box-listado-piezas'>";
		htmlItem += "	<div class='col-piezas'>";
		htmlItem += "		<div class='col-left'>";
		htmlItem += "			<div class='numero-paquete'>Paquete <strong><big>0"+(i+1)+"</big></strong></div>";
		htmlItem += "			<div class='pieza'>"+listaPiezas[i].nuPieza+"</div>";
		htmlItem += "		</div>";
		htmlItem += "		<div class='col-center'>";
		htmlItem += "			<div class='line-tacking'>";
		htmlItem += "				<div class='box-point-tracking'>";
		var listaEstadosPieza = listaPiezas[i].listaEstadosPieza;
		if(listaEstadosPieza != null && listaEstadosPieza != undefined && listaEstadosPieza.length > 0){
		   for(var k = 0 ; k < listaEstadosPieza.length ; k++){
			   var objetoIconEstado = buscarClaseIconoTracking(listaEstadosPieza[k].nombreEstado,listaIconosEstados);
			   if(objetoIconEstado != null && objetoIconEstado != undefined){
					if(listaEstadosPieza[k].indicador == "1" || listaEstadosPieza[k].indicador == "2" ){
						var claseHabilitado = String(listaEstadosPieza[k].indicador) == "2" ? "" : "-disabled";
						if(objetoIconEstado.identificador == "excepcion"){
							claseHabilitado = "-excepcion"
						}						
						htmlItem += "<div class='poit-tracking realizado"+claseHabilitado+"' style='width: 150px;'>";
						htmlItem += "	<div class='state-paso'>";
						htmlItem += "		<div class='state-paso-ico'><i></i></div>";
						htmlItem += "	</div>";
						htmlItem += "	<div class='fecha-proceso'>"+listaEstadosPieza[k].fechaEstado+"</div>";
						htmlItem += "	<div class='estado-proceso'>"+objetoIconEstado.etiquetaHtml+"</div>";
						htmlItem += "</div>";
					}
				}
		   }	
		}
		htmlItem += "               </div>";
		htmlItem += "           </div>";
		htmlItem += "       </div>";
		htmlItem += "		<div class='col-right'>";
		htmlItem += "			<button type='button' class='btn-mostrar' id='btn-mostrar_"+i+"' onclick='desplazarAcordionPaquete("+i+")'></button>";
		htmlItem += "		</div>";
		htmlItem += "   </div>";
		htmlItem += "</div>";	
		
		htmlItem += "<div id='idMostrarGrilla_"+i+"' class='mostrar-grilla'>";
		htmlItem += "	<div class='table-custom-entrega'>";
		htmlItem += "      <table id='tblDetallePiezas_"+i+"' class='table table-striped table-bordered table-hover text-center text-middle' data-flat='true' data-show-export='false' data-cache='false'></table>";
		htmlItem += "	</div>";
		htmlItem += "</div>";
		$('#'+idDiv).append(htmlItem);
		htmlItem = "";
		cargarDataGrillaDetalle(listaPiezas[i].listaEventosPieza, i);
		//aca cargamos la tabla
	}
	
}

function buscarClaseIconoTracking(identificador,listaIconosEstados){
	var objetoIconoEstado = null;
	for(var j = 0 ; j < listaIconosEstados.length ; j++){
		if(identificador == listaIconosEstados[j].identificador){
			return listaIconosEstados[j];
		}
	}
	return objetoIconoEstado;
}
function initIconosTracking(){
	var listaIconosEstados = [];
	var iconopedidoCX = {
			identificador : 'pedidoCX',
			nombreclase   : 'poit-tracking entrada recibido',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Pedido recibido<br>por CX</span></strong>' 
		}
	var iconopickup = {
			identificador : 'pickup',
			nombreclase   : 'poit-tracking recogio-pedido',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Se recogi\u00F3<br>pedido</span></strong>'
		}
	var iconoprepaPedidoEnvio = {
			identificador : 'prepaPedidoEnvio',
			nombreclase   : 'poit-tracking para-envio',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Preparando pedido <br>para env\u00EDo</span></strong>'
		}
	var iconosalidaProvincia = {
			identificador : 'salidaProvincia',
			nombreclase   : 'poit-tracking en-camino',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Salida a<br>Provincia</span></strong>'
		}
	var iconoarriboTransito = {
			identificador : 'arriboTransito',
			nombreclase   : 'poit-tracking en-camino',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Arribo<br>Transito</span></strong>'
		}
	var iconosalidaTransito = {
			identificador : 'salidaTransito',
			nombreclase   : 'poit-tracking en-camino',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Salida<br>Transito</span></strong>'
		}
	var iconollegadaDestino = {
			identificador : 'llegadaDestino',
			nombreclase   : 'poit-tracking en-camino',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Llegada a<br>Destino</span></strong>'
		}
	var iconopedidoCamino = {
			identificador : 'pedidoCamino',
			nombreclase   : 'poit-tracking en-camino',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Pedido<br>en Camino</span></strong>'
		}
	var iconoEnAgencia = {
			identificador : 'enAgencia',
			nombreclase   : 'poit-tracking para-envio',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">En<br>Agencia</span></strong>'
		}
	var iconoentregado = {
			identificador : 'entregado',
			nombreclase   : 'poit-tracking salida terminado',
			etiquetaHtml  : '<strong><span class="estado-proceso inicio">Entregado</span></strong>'
		}
	var iconoexcepcion = {
			identificador : 'excepcion',
			nombreclase   : 'poit-tracking excepcion',
			//style="color: red;"
			etiquetaHtml  : '<strong><span class="estado-proceso inicio" >Excepción</span></strong>'
		}
	listaIconosEstados.push(iconopedidoCX);
	listaIconosEstados.push(iconopickup);
	listaIconosEstados.push(iconoprepaPedidoEnvio);
	listaIconosEstados.push(iconosalidaProvincia);
	listaIconosEstados.push(iconoarriboTransito);
	listaIconosEstados.push(iconosalidaTransito);
	listaIconosEstados.push(iconollegadaDestino);
	listaIconosEstados.push(iconopedidoCamino);
	listaIconosEstados.push(iconoEnAgencia);
	listaIconosEstados.push(iconoentregado);
	listaIconosEstados.push(iconoexcepcion);
	return listaIconosEstados;
}

