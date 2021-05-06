$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			inicializarComponenteGoogleMaps();
 			console.log(filtro);
});
var accion = false;
var listaPuntos = [];
var listaPtar = [];
function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var idDetalleGeneral	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var txtCodigo = null;
	var txtDescripcion = null;
	var txtDescripcionCorta = null;
	var txtDireccion = null;
	var txtLatitud = null;
	var txtLongitud = null;		
	var btnLimpiar = null;
	var btnBuscar = null;
	var divBotonesParametros = null;
	var dtpInicio = null;
	var dtpFin = null;
}

function cargarComponentes() {	
	btnLimpiar = $('#btnLimpiar');
	btnBuscar = $('#btnBuscar');
	dtpInicio = $('#dtpInicio');
	dtpFin = $('#dtpFin');
	
	btnLimpiar.click(function(event){
		limpiarParametrosBusqueda();
	});
	
	btnBuscar.click(function(event){
		console.log(listaPuntos);
		obj = {};
		obj.listaPuntos = JSON.stringify(listaPuntos);
		obj.listaPtar = JSON.stringify(listaPtar);
		obj.fechaInicio = dtpInicio.val();
		obj.fechaFin = dtpFin.val();
		if(validarDatosEntrada()){
			mostrarMensaje();
			cargarPantalla('./cargarVentanaDashboard', obj, $('#idCargaNuevaPantalla'));
		}		
	});
	
	$('#datetimepickerInicio').datepicker({
        autoclose: true,
        format: 'dd/mm/yyyy'
	});

	$('#datetimepickerFin').datepicker({
		autoclose: true,
		format: 'dd/mm/yyyy'
	});
	
	divBotonesParametros = $("#divBotonesParametros");	
	MenuMapa();
	$("#btnExpanderMapa").click();
	
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
}

var MenuMapa = function(){
    $('body').on('click', '.btn-showMapa', function () {  
        $('.wrap-ui').addClass('opacity-capa');  
        $('.menuMapa').animate({
            left: '0'
        });
    });

    $('body').on('click', '.btn-hideMapa', function () {
        $('.wrap-ui').removeClass('opacity-capa');
        $('.menuMapa').animate({
            left: '-470'
        });
    });  
}

function inicializarComponenteGoogleMaps(){
	latitudporDefecto = "-12.043333";
 	LongitudporDefecto = "-77.028333";
    PintarMapa(latitudporDefecto,LongitudporDefecto);
}
function PintarMapa(latitudParametro,longitudParametro){
	  var latitud = Number(latitudParametro);
	  var longitud = Number(longitudParametro);
	  map = new GMaps({
		  div: '#map',
		  lat: latitud,
		  lng: longitud,
		  zoom : 10
	  });
	  pintarPuntosMaps(ltaPlantaTratamiento);
}

function pintarPuntosMaps(ltaMaps){
	limpiarMapa();
	var urlColor = retornoColor("azul");
	for(var i = 0 ; i < ltaMaps.length ; i++){
		var objeto = ltaMaps[i];
		var totalCaracter = objeto.direccion.length;
		var puntoCorte = totalCaracter/2;
		var direccionHtml = cortarCadenaLimitada(objeto.direccion,0,puntoCorte)+ " -<br>"+cortarCadenaTotal(objeto.direccion,puntoCorte);
		var etiqueta  = "<span><strong>"+objeto.descripcionPtarxSector+"</strong></span><br>";	
		    etiqueta  += "<span><strong>Dirección :</strong></span><br>";
	        etiqueta  +="<span>"+direccionHtml+"</span><br>";			       
	        etiqueta  +="<span><strong>LAT: </strong>"+objeto.latitud+"</span><br>";	
	        etiqueta  +="<span><strong>LNG: </strong>"+objeto.longitud+"</span><br>";	
        if(!IsEmpty($("#idRegistroLaboratorio").val())){		
    		if(idParametroInicial != null && !IsEmpty(idParametroInicial)){    			
    			agregarPuntoMapaSinEvento(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    		}else{
    			agregarPuntoMapaConEvento(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    		}
        }else{
        	agregarPuntoMultipleMapa(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    	}
	}
}

function limpiarMapa(){
  eliminandoMarcasMaps();
  map.markers = [];
  map.cleanRoute();
}

function eliminandoMarcasMaps(){
  var ListaMarcas = map.markers;
  if(IsEmpty(ListaMarcas) == false){
    for(var i = 0 ; i <ListaMarcas.length ;i++ ){
	    map.markers[i].setMap(null);
	  }
  }		  
}

function limpiarParametrosBusqueda(){
	dtpInicio.val(fechaActual);
	dtpFin.val(fechaActual);
	var fechaInicio = dtpInicio.val();
	var dateParts = fechaInicio.split("/");
	var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
	$(datetimepickerInicio).datepicker("setDate", dateObject);
	$(datetimepickerFin).datepicker("setDate", dateObject);
	listaPuntos = [];
	listaPtar = [];
	pintarPuntosMaps(ltaPlantaTratamiento);
	$('#idCargaNuevaPantalla').html("");
	mostrarMensaje();
}

function validarDatosEntrada(){
    var estado = true;
    var fechaInicio = $(datetimepickerInicio).datepicker("getDate");
    var fechaFin = $(datetimepickerFin).datepicker("getDate");
    if(IsEmpty(dtpInicio.val())){           
    	mostrarMensaje('Debe Seleccionar una Fecha de Inicio.', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(IsEmpty(dtpFin.val())){           
     	mostrarMensaje('Debe Seleccionar una Fecha Fin', ConstanteServices.IMAGEN_DANGER);
        estado = false; 
    }
    if(fechaInicio > fechaFin){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    if(fechaFin < fechaInicio){
    	mostrarMensaje('Las Fechas Ingresadas son Incorrectas.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    if(listaPuntos.length == 0){
    	mostrarMensaje('Debe seleccionar un PTAR.', ConstanteServices.IMAGEN_DANGER);
        estado = false;
    }
    return estado;
}
