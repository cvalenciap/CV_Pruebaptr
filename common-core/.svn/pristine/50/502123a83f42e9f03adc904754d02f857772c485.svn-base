/**
 * JS rutas 
 * Libreria para el grafico de rutas y marcadores en Google Maps
 * Autor: Edison Arrunátegui
 */
function CentrarMapa(objMapa, latitud, longitud)
{
    var mapa = new GMaps(
        {
            el: objMapa,
            lat: latitud,
            lng: longitud
        }
    );
    return mapa;
}

function AgregaMarcador(objMapa,latitud,longitud, titulo, colorDefault,textoLabel){
	objMapa.addMarker(
        {
	       lat: latitud,
	       lng: longitud,
	       //title: titulo,
	       //icon:  retornoColor(colorDefault),
	       icon: {
               url: retornoColor(colorDefault),
               labelOrigin: new google.maps.Point(15, 10)
           },
	       infoWindow: {
				content: titulo
		   },
//		   animation: google.maps.Animation.DROP,
		   label : {color: '#000', fontSize: '14px', fontWeight: '600',
				    text: textoLabel}
		   
	   }
    );
}

function retornoColor(color){
//	   var colorVerde = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
//	   var colorNaranja = "http://maps.google.com/mapfiles/ms/icons/orange-dot.png";
//	   var colorRojo = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
//	   var colorAmarillo = "http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";	   
	   var colorVerde = "/cxpress-web/resources/images/icons/green-dot.png"; //"http://maps.google.com/mapfiles/ms/icons/green-dot.png";
	   var colorNaranja = "/cxpress-web/resources/images/icons/orange-dot.png"; //"http://maps.google.com/mapfiles/ms/icons/orange-dot.png";
	   var colorRojo = "/cxpress-web/resources/images/icons/red-dot.png"; //"http://maps.google.com/mapfiles/ms/icons/red-dot.png";
	   var colorAmarillo = "/cxpress-web/resources/images/icons/yellow-dot.png"; //"http://maps.google.com/mapfiles/ms/icons/yellow-dot.png";
	   var colorAzul = "/cxpress-web/resources/images/icons/blue-dot.png"; //"http://maps.google.com/mapfiles/ms/icons/blue-dot.png";
	   
	   
	   if(color == "rojo"){
	      return colorRojo;
	   }else{
	      if(color == "naranja"){
	        return colorNaranja;
	      }else{
	        if(color == "verde"){
	          return colorVerde;
	        }else{
	          if(color == "amarillo"){
	          	return colorAmarillo;
	          }
	        }
	      }
	   }
	   return colorRojo;
	}
function agregarElementos(inicio,fin, lista){
	var listaPuntosTemp = [];
	listaPuntosTemp.push(inicio);
	//recorremos la lista
	for(var  i = 0 ; i < lista.length ; i++){
		listaPuntosTemp.push(lista[i]);
	}
	listaPuntosTemp.push(fin);
	return listaPuntosTemp;
}

function obtenerLista21PuntosRutaJS(puntosRuta,i){
   var lista21PuntosRuta=[];
   for(var j=i;j<i+21;j++){
      if(j<puntosRuta.length){
         lista21PuntosRuta.push(puntosRuta[j]);
      }else{
         break;
      }
     
   }
   return lista21PuntosRuta;
}
function transformarObjetoLocation(latitud,longitud){
	var item = {
        location: new window.google.maps.LatLng(latitud,longitud),
        stopover: true
    }	
	return item;
}

function TrazarRutas(objMapa, puntosRuta, color, opacidad,modoViaje='driving',anchoTrazo=6)
{
	//var objetoOrigen = transformarObjetoLocation(origen[0],origen[1]);
	//var objetoDestino = transformarObjetoLocation(destino[0],destino[1]);
	//var puntosRuta = agregarElementos(objetoOrigen,objetoDestino,puntosDePaso);
	//var objetoHub = transformarObjetoLocation(latitudHub,longitudHub);
	//var puntosRuta = agregarElementos(objetoHub,puntosDePaso);
	if(puntosRuta.length > 1){
		  for(var i =0;i< puntosRuta.length;i++){
	          var lista21PuntosRuta= obtenerLista21PuntosRutaJS(puntosRuta,i);
	          i=i+lista21PuntosRuta.length;//-1;
	          
	          var latitudOrigen = lista21PuntosRuta[0].location.lat();
		      var longitudOrigen = lista21PuntosRuta[0].location.lng();
		      var latitudFinal = lista21PuntosRuta[lista21PuntosRuta.length-1].location.lat();
		      var longitudFinal = lista21PuntosRuta[lista21PuntosRuta.length-1].location.lng();
		      var inicio = new google.maps.LatLng(String(latitudOrigen), String(longitudOrigen));
			  var fin = new google.maps.LatLng(String(latitudFinal), String(longitudFinal));	
			  //lista21PuntosRuta.splice(lista21PuntosRuta.length-1, 1);
			  //lista21PuntosRuta.shift();
			  objMapa.drawRoute({
			    origin: [latitudOrigen, longitudOrigen],
			    destination: [latitudFinal, longitudFinal],
			    travelMode: modoViaje,
			    waypoints : lista21PuntosRuta,
			    //optimizeWaypoints: true,
			    strokeColor: color,
			    strokeOpacity: opacidad,
			    strokeWeight: anchoTrazo
		      });
	      
	      } 
	}
//    objMapa.drawRoute(
//        {
//            origin: origen,
//            waypoints: puntosDePaso,
//            destination: destino,
//            travelMode: modoViaje,
//		    strokeColor: color,
//		    strokeOpacity: opacidad,
//		    strokeWeight: anchoTrazo
//        }
//    );
}

function TrazarRuta(objMapa,origen,destino,color, opacidad,modoViaje='driving',anchoTrazo=6){
	objMapa.drawRoute({
		  origin: origen,
		  destination: destino,
		  travelMode: modoViaje,
		  strokeColor: color,
		  strokeOpacity: opacidad,
		  strokeWeight: anchoTrazo
		});
}

//function CalcularInfoDistanciaPuntosMaps(Matrixorigen,Matrixdestino,fechaHoraDeseadaCalculo){
//	var retorno = {};
//	var service = new google.maps.DistanceMatrixService;
//	var dfd = $.Deferred();
//    service.getDistanceMatrix({
//      origins: Matrixorigen,
//      destinations: Matrixdestino,
//      travelMode: 'DRIVING',
//      drivingOptions: {
//    	    departureTime: fechaHoraDeseadaCalculo ,  // for the time N milliseconds from now.
//    	    trafficModel: 'bestguess'
//      },
//      unitSystem: google.maps.UnitSystem. METRIC,
//      avoidHighways: false,
//      avoidTolls: false
//    }, function(response, status) {
//    	retorno.status = status;
//    	retorno.response = response;
//    	return retorno; 
//    	
//    	return dfd;
//    });  
//}
//function llamandoFunction(){
//	
//	var listaDestino = [];
//	var listaOrigen = [];
//	var listaPuntosDestinoFormateado = [];
//	var punto1 = {};
//	punto1.latitud = "-12.07738592455823";
//	punto1.longitud = "-77.03733444213867";
//	var punto2 = {};
//	punto2.latitud = "-12.059277220";
//	punto2.longitud = "-77.0414362102747";
//	var punto3 = {};
//	punto3.latitud = "-12.082002084429265";
//	punto3.longitud = "-77.03583240509033";
//	var punto4 = {};
//	punto4.latitud = "-12.070692351";
//	punto4.longitud = "-77.04980470";
//	listaDestino.push(punto1);
//	listaDestino.push(punto2);
//	listaDestino.push(punto3);
//	listaDestino.push(punto4);
//	var fecha = new Date();
//	for(var i = 0;i <listaDestino.length;i++){
//	    var punto = new google.maps.LatLng(listaDestino[i].latitud, listaDestino[i].longitud);
//		listaPuntosDestinoFormateado.push(punto);
//    }
//	var latitudOrigenHub = "-12.051869715";
//	var longitudOrigenHub = "-77.042104750";
//	var punto = new google.maps.LatLng(latitudOrigenHub, longitudOrigenHub);	
//	listaOrigen.push(punto);
//	var respuesta = CalcularInfoDistanciaPuntosMaps(listaOrigen, listaPuntosDestinoFormateado,fecha);
////	console.log(respuesta);
//}
//function ordenarPuntosMap(listaPuntosHub,latitudHub,longitudHub,fechaHoraCalculo){
//	var listaFinal = [];
//	var objetoRespuesta = {};
//	objetoRespuesta.listaOriginal = listaPuntosHub;
//	var listaPuntos = listaPuntosHub;
//	var listaPuntosDestinoFormateado = [];
//	var listaPuntosOrigenFormateado = [];	
//	//formateando puntos del hub
//	var puntoHub = new google.maps.LatLng(latitudHub, longitudHub);
//	listaPuntosOrigenFormateado.push(puntoHub);
//	//formateando puntos destinos
//	for(var i = 0;i <listaPuntos.length;i++){
//		var punto = new google.maps.LatLng(listaPuntos[i].latitud, listaPuntos[i].longitud);
//		listaPuntosDestinoFormateado.push(punto);
//	}
//	if(IsEmpty(fechaHoraCalculo)){
//		fechaHoraCalculo = new Date();
//	}
//	var Matrixorigen = listaPuntosOrigenFormateado;
//	var Matrixdestino = listaPuntosDestinoFormateado;
//	var fechaHoraDeseadaCalculo = fechaHoraCalculo;
//	var service = new google.maps.DistanceMatrixService;
//	service.getDistanceMatrix({
//		      origins: Matrixorigen,
//		      destinations: Matrixdestino,
//		      travelMode: 'DRIVING',
//		      drivingOptions: {
//		    	    departureTime: fechaHoraDeseadaCalculo ,  // for the time N milliseconds from now.
//		    	    trafficModel: 'bestguess'
//		      },
//		      unitSystem: google.maps.UnitSystem. METRIC,
//		      avoidHighways: false,
//		      avoidTolls: false
//	}, function(response, status) {
//		if(response != null && response != 'undefined' && status == google.maps.DistanceMatrixStatus.OK){
//			var listaPuntosConsulta = response.rows[0].elements;
//			if(listaPuntosConsulta.length > 0){
//				//calculandoprimerElementoCercano
//				var distanciaMenorValue = listaPuntosConsulta[0].distance.value;
//				var distanciaMenorTexto = listaPuntosConsulta[0].distance.text;
//				var tiempovalue = listaPuntosConsulta[0].duration.value;
//				var tiempotexto = listaPuntosConsulta[0].duration.text;
//				var posicionMenor = 0;
//				for(var i = 0; i < listaPuntosConsulta.length ; i++){
//					if(listaPuntosConsulta[i].distance.value <= distanciaMenorValue){
//						distanciaMenorValue = listaPuntosConsulta[i].distance.value;
//						distanciaMenorTexto  = listaPuntosConsulta[i].distance.text;
//						tiempovalue = listaPuntosConsulta[i].duration.value;
//						tiempotexto = listaPuntosConsulta[i].duration.text;
//						posicionMenor = i;
//					}
//				}
//				var objetoPuntoMenor = listaPuntos[posicionMenor];
//				objetoPuntoMenor.distanciaPuntoAnteriorTexto = distanciaMenorTexto;
//				objetoPuntoMenor.distanciaPuntoAnteriorValor = distanciaMenorValue;
//				objetoPuntoMenor.tiempoPuntoAnteriorTexto = tiempotexto;
//				objetoPuntoMenor.tiempoPuntoAnteriorValor = tiempovalue;
//				//agregando el primer elemento
//				listaFinal.push(objetoPuntoMenor);
//				listaPuntos.splice(posicionMenor,1);
//				var listaOrigen = [];
//				var listaDestino = [];
//				while(listaFinal < listaPuntosHub){
//					listaOrigen = [];
//					listaDestino = [];
//					//formateando puntos Destino Origen
//					var objetoOrigenUltimo = listaFinal[listaFinal.length-1];
//					var puntoOrigen = new google.maps.LatLng(objetoOrigenUltimo.latitud, objetoOrigenUltimo.longitud);
//					listaOrigen.push(puntoOrigen);
//					//formateando puntos destinos
//					for(var i = 0;i <listaPuntos.length;i++){
//						var puntoDestino = new google.maps.LatLng(listaPuntos[i].latitud, listaPuntos[i].longitud);
//						listaDestino.push(puntoDestino);
//					}
//					var servicePunto = new google.maps.DistanceMatrixService;
//					servicePunto.getDistanceMatrix({
//						      origins: listaOrigen,
//						      destinations: listaDestino,
//						      travelMode: 'DRIVING',
//						      drivingOptions: {
//						    	    departureTime: fechaHoraDeseadaCalculo ,  // for the time N milliseconds from now.
//						    	    trafficModel: 'bestguess'
//						      },
//						      unitSystem: google.maps.UnitSystem. METRIC,
//						      avoidHighways: false,
//						      avoidTolls: false
//					}, function(response, status) {
//						alert("sigue");
//					});
////					servicePunto.then(function(result) {
////						  console.log(result); // "Stuff worked!"
////					}, function(err) {
////						  console.log(err); // Error: "It broke"
////					});
//					alert("hola");
//				}	
//				objetoRespuesta.estado = "OK";
//				objetoRespuesta.listaOrdenada = listaFinal;
//		    }else{
//		    	objetoRespuesta.estado = "ERROR";
//				objetoRespuesta.listaOrdenada = listaFinal;
//		    }
//	    }else{
//	    	objetoRespuesta.estado = "ERROR";
//			objetoRespuesta.listaOrdenada = listaFinal;
//	    }
//		return objetoRespuesta;
//	}); 
//	
//	CalcularInfoDistanciaPuntosMaps(listaPuntosOrigenFormateado,listaPuntosDestinoFormateado,fechaHoraCalculo).then( function(response) {
//		var respuesta = response;
//		console.log(respuesta);
//	});
//	return CalcularInfoDistanciaPuntosMaps(listaPuntosOrigenFormateado,listaPuntosDestinoFormateado,fechaHoraCalculo).then(function(response) {
//	    var respues = response;
//	    return respues;
//	}).done(function(distanceMatrixResult) {
//	    var myString = "distance is: "+distanceMatrixResult;
//	    return "hola";
//	    // do something with your string now
//	});
//	var respuesta = CalcularInfoDistanciaPuntosMaps(listaPuntosOrigenFormateado,listaPuntosDestinoFormateado,fechaHoraCalculo);
//	if(respuesta != null && respuesta != 'undefined'){
//		
//	}
//		if(respuesta.status == "OK"){
//			var listaPuntosConsulta = respuesta.response.rows[0].elements;
//			if(listaPuntosConsulta.length > 0){
//				//calculandoprimerElementoCercano
//				var distanciaMenorValue = listaPuntosConsulta[0].distance.value;
//				var distanciaMenorTexto = listaPuntosConsulta[0].distance.text;
//				var tiempovalue = listaPuntosConsulta[0].duration.value;
//				var tiempotexto = listaPuntosConsulta[0].duration.text;
//				var posicionMenor = 0;
//				for(var i = 0; i < listaPuntosConsulta.length ; i++){
//					if(listaPuntosConsulta[i].distance.value <= distanciaMenorValue){
//						distanciaMenorValue = listaPuntosConsulta[i].distance.value;
//						distanciaMenorTexto  = listaPuntosConsulta[i].distance.text;
//						tiempovalue = listaPuntosConsulta[i].duration.value;
//						tiempotexto = listaPuntosConsulta[i].duration.text;
//						posicionMenor = i;
//					}
//				}
//				var objetoPuntoMenor = listaPuntos[posicionMenor];
//				objetoPunto.distanciaPuntoAnteriorTexto = distanciaMenorTexto;
//				objetoPunto.distanciaPuntoAnteriorValor = distanciaMenorValue;
//				objetoPunto.tiempoPuntoAnteriorTexto = tiempotexto;
//				objetoPunto.tiempoPuntoAnteriorValor = tiempovalue;
//				//agregando el primer elemento
//				listaFinal.push(objetoPunto);
//				listaPuntos.splice(posicionMenor,1);
//				var listaOrigen = [];
//				var listaDestino = [];
//				while(listaFinal < listaPuntosHub){
//					listaOrigen = [];
//					listaDestino = [];
//					//formateando puntos Destino Origen
//					var objetoOrigenUltimo = listaFinal[listaFinal.length-1];
//					var puntoOrigen = new google.maps.LatLng(objetoOrigenUltimo.latitud, objetoOrigenUltimo.longitud);
//					listaOrigen.push(puntoOrigen);
//					//formateando puntos destinos
//					for(var i = 0;i <listaPuntos.length;i++){
//						var puntoDestino = new google.maps.LatLng(listaPuntos[i].latitud, listaPuntos[i].longitud);
//						listaDestino.push(puntoDestino);
//					}
//					var respuestaMap = CalcularInfoDistanciaPuntosMaps(listaOrigen,listaDestino,fechaHoraCalculo);
//					if(respuestaMap != null && respuestaMap != 'undefined'){
//						if(respuestaMap.status == "OK"){
//							var listaPuntosRespuesta = respuesta.response.rows[0].elements;
//							if(listaPuntosRespuesta.length > 0){
//								//calculandoprimerElementoCercano
//								var PuntodistanciaMenorValue = listaPuntosRespuesta[0].distance.value;
//								var PuntodistanciaMenorTexto = listaPuntosRespuesta[0].distance.text;
//								var Puntotiempovalue = listaPuntosRespuesta[0].duration.value;
//								var Puntotiempotexto = listaPuntosRespuesta[0].duration.text;
//								var PuntoposicionMenor = 0;
//								for(var i = 0; i < listaPuntosRespuesta.length ; i++){
//									if(listaPuntosRespuesta[i].distance.value <= PuntodistanciaMenorValue){
//										PuntodistanciaMenorValue = listaPuntosRespuesta[i].distance.value;
//										PuntodistanciaMenorTexto  = listaPuntosRespuesta[i].distance.text;
//										Puntotiempovalue = listaPuntosRespuesta[i].duration.value;
//										Puntotiempotexto = listaPuntosRespuesta[i].duration.text;
//										PuntoposicionMenor = i;
//									}
//								}
//								var objetoPuntoSiguiente = listaPuntos[PuntoposicionMenor];
//								objetoPuntoSiguiente.distanciaPuntoAnteriorTexto = PuntodistanciaMenorTexto;
//								objetoPuntoSiguiente.distanciaPuntoAnteriorValor = PuntodistanciaMenorValue;
//								objetoPuntoSiguiente.tiempoPuntoAnteriorTexto = Puntotiempotexto;
//								objetoPuntoSiguiente.tiempoPuntoAnteriorValor = Puntotiempovalue;
//								//agregando siguiente elemento en la lista
//								listaFinal.push(objetoPuntoSiguiente);
//								listaPuntos.splice(PuntoposicionMenor,1);
//							}else{
//								objetoRespuesta.estado = "ERROR";
//								objetoRespuesta.listaOrdenada = listaFinal;
//								break;
//							}
//						}else{
//							objetoRespuesta.estado = "ERROR";
//							objetoRespuesta.listaOrdenada = listaFinal;
//							break;
//						}
//					}else{
//						objetoRespuesta.estado = "ERROR";
//						objetoRespuesta.listaOrdenada = listaFinal;
//						break;
//					}	
//				}
//				objetoRespuesta.estado = "OK";
//				objetoRespuesta.listaOrdenada = listaFinal;
//			}else{
//				objetoRespuesta.estado = "ERROR";
//				objetoRespuesta.listaOrdenada = listaFinal;
//			}
//		}else{
//			objetoRespuesta.estado = "ERROR";
//			objetoRespuesta.listaOrdenada = listaFinal;
//		}
//	}else{	
//		objetoRespuesta.estado = "ERROR";
//		objetoRespuesta.listaOrdenada = listaFinal;
//	}
//	return objetoRespuesta;
//}

	
