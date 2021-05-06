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

function agregarPuntoMultipleMapa(objMapa,latitud,longitud,colorDefault,etiqueta,valor,textoLabel){
	objMapa.addMarker({
		      lat: latitud,
		      lng: longitud,
		      infoWindow: {
				content: etiqueta
			  },
		      icon: {
	              url: colorDefault,
	              labelOrigin: new google.maps.Point(15, 35)
		      },
		      click: function(e) {
		    	  var iconDefecto = {
			              url: retornoColor("azul"),
			              labelOrigin: new google.maps.Point(15, 35)
		    	  };
		    	  var iconSeleccionado = {
			              url: retornoColor("rojo"),
			              labelOrigin: new google.maps.Point(15, 35)
		    	  };
		    	  objeto = JSON.parse(valor);
		    	  var flagAdd = true;
		    	  for(var i=0;i<listaPuntos.length;i++){
		    		  if(objeto.idPtarxSector == listaPuntos[i]){
		    			  e.setIcon(iconDefecto);
		    			  listaPuntos.splice(i,1);
		    			  listaPtar.splice(i,1);
		    			  flagAdd = false;
		    			  break;
		    		  }
		    	  }
		    	  if(flagAdd){
		    		  listaPuntos.push(objeto.idPtarxSector);
		    		  listaPtar.push(objeto.descripcionCorta);
		    		  e.setIcon(iconSeleccionado);
		    	  }
		      },
		      value : valor,
		      label : {color: '#000', fontSize: '10px', fontWeight: '600',
				    text: textoLabel}
	});  
}

function agregarPuntoMapaConEvento(objMapa,latitud,longitud,colorDefault,etiqueta,valor,textoLabel){
	objMapa.addMarker({
		      lat: latitud,
		      lng: longitud,
		      infoWindow: {
				content: etiqueta
			  },
		      icon: {
	              url: colorDefault,
	              labelOrigin: new google.maps.Point(15, 35)
		      },
		      click: function(e) {
		    	  var iconDefecto = {
			              url: retornoColor("azul"),
			              labelOrigin: new google.maps.Point(15, 35)
		    	  };
		    	  $.each(map.markers, function(i, item) {
		    		  item.setIcon(iconDefecto);
		    	  });
		    	  
		    	  var iconSeleccionado = {
			              url: retornoColor("rojo"),
			              labelOrigin: new google.maps.Point(15, 35)
		    	  };
		    	  e.setIcon(iconSeleccionado);
		    	  verPlantaTratamiento(e.value);
		      },
		      value : valor,
		      label : {color: '#000', fontSize: '10px', fontWeight: '600',
				    text: textoLabel}
	  });  
	}

function agregarPuntoMapaSinEvento(objMapa,latitud,longitud,colorDefault,etiqueta,valor,textoLabel){
	objMapa.addMarker({
		      lat: latitud,
		      lng: longitud,
		      infoWindow: {
				content: etiqueta
			  },
		      icon: {
	              url: retornoColor("rojo"),
	              labelOrigin: new google.maps.Point(15, 35)
		      },
		      label : {color: '#000', fontSize: '10px', fontWeight: '600',
				    text: textoLabel}
	  });  
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
	var colorAzul = contexto+"images/blue-dot.png";
	var colorRojo = contexto+"images/red-dot.png";
	   if(color == "azul"){
	      return colorAzul;
	   }else{
	     return colorRojo; 	     
	   }
	   return colorRojo;
	}
function agregarElementos(inicio,fin, lista){
	var listaPuntosTemp = [];
	listaPuntosTemp.push(inicio);
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


