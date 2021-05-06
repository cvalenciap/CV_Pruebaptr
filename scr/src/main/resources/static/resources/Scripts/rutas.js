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
	       icon: {
               url: retornoColor(colorDefault),
               labelOrigin: new google.maps.Point(15, 10)
           },
	       infoWindow: {
				content: titulo
		   },
		   label : {color: '#000', fontSize: '14px', fontWeight: '600',
				    text: textoLabel}
		   
	   }
    );
}

function retornoColor(color){   
	   var colorVerde = "/cxpress-web/resources/images/icons/green-dot.png";
	   var colorNaranja = "/cxpress-web/resources/images/icons/orange-dot.png";
	   var colorRojo = "/cxpress-web/resources/images/icons/red-dot.png";
	   var colorAmarillo = "/cxpress-web/resources/images/icons/yellow-dot.png";
	   var colorAzul = "/cxpress-web/resources/images/icons/blue-dot.png";
	   
	   
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
	if(puntosRuta.length > 1){
		  for(var i =0;i< puntosRuta.length;i++){
	          var lista21PuntosRuta= obtenerLista21PuntosRutaJS(puntosRuta,i);
	          i=i+lista21PuntosRuta.length;
	          
	          var latitudOrigen = lista21PuntosRuta[0].location.lat();
		      var longitudOrigen = lista21PuntosRuta[0].location.lng();
		      var latitudFinal = lista21PuntosRuta[lista21PuntosRuta.length-1].location.lat();
		      var longitudFinal = lista21PuntosRuta[lista21PuntosRuta.length-1].location.lng();
		      var inicio = new google.maps.LatLng(String(latitudOrigen), String(longitudOrigen));
			  var fin = new google.maps.LatLng(String(latitudFinal), String(longitudFinal));
			  objMapa.drawRoute({
			    origin: [latitudOrigen, longitudOrigen],
			    destination: [latitudFinal, longitudFinal],
			    travelMode: modoViaje,
			    waypoints : lista21PuntosRuta,
			    strokeColor: color,
			    strokeOpacity: opacidad,
			    strokeWeight: anchoTrazo
		      });
	      
	      } 
	}
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
	
