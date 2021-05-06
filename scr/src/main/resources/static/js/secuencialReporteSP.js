agregarBotoneraOrden();

function agregarBotoneraOrden(){
	var botoneraOrdenHtml = "<br/>";
	botoneraOrdenHtml += " <input type='button' title='Subir' id='btnSubir' class='btn btn-default btn-xs' value='&xwedge;' >";
	botoneraOrdenHtml += " <input type='button' title='Bajar' id='btnBajar' class='btn btn-default btn-xs' value='&xvee;'/>";
	$("#botoneraOrden").html(""+botoneraOrdenHtml);
}

$(document).ready(function() {
	console.log('secuencialSP');
	inicializarVariables();
	cargarComponentes();
});

function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var btnGrabar 	= null;
	var btnRegresar	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarPorSector = null;
	var selSubParametroSecuencial = null;
	var listaSubParametroSecuencial = [];
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnCancelar = $('#btnCancelar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboPtarPorSector = $("#cboPtarPorSector");
	selSubParametroSecuencial = $("#selSubParametroSecuencial");
	
	cboPtarPorSector.html("");
    $.each(ltaPtarPorSector, function(i, item) {
    	cboPtarPorSector.append($("<option/>").attr("value",ltaPtarPorSector[i].idPtarxsector).text(""+ltaPtarPorSector[i].descripcionPtar +" - "+ltaPtarPorSector[i].descripcionSector)); 
    }); 

	mostrarMensaje();
    
    cboPtarPorSector.change(function(event){
    	mostrarMensaje();
    	buscarlistaSubParametroSecuencial();
    	registrarSubParametroPtarSecuencialAuxiliar();    	
    });
    
    btnCancelar.click(function(event) { 
    	mostrarMensaje();
    	buscarlistaSubParametroSecuencial();
    });
    
    btnGrabar.click(function(event) { 
    	mostrarMensaje();
    	var tituloModal = 'Mantenimiento de Secuencial de Reporte por PTAR';
		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'registrarSubParametroPtarSecuencial()', '', tituloModal);
    });
	
	$("#btnSubir").click(function(event) { 
		subirItem();
	});
	
	$("#btnBajar").click(function(event) { 
		bajarItem();
	});
	
	buscarlistaSubParametroSecuencial();
	registrarSubParametroPtarSecuencialAuxiliar(); 
}

function subirItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selSubParametroSecuencial.val() == lista[i].idSubParametro){
				var index = i;
				break;
			}
		}
		if(index == 0){
			mostrarMensaje("Es el primer valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaSubParametroSecuencial = [];
			for(var i=0;i<lista.length;i++){
				if(i == index-1){
					listaSubParametroSecuencial.push(lista[index]);
				}else if(i == index){
					listaSubParametroSecuencial.push(lista[index-1]);
				}else{
					listaSubParametroSecuencial.push(lista[i]);
				}
			}
			selSubParametroSecuencial.html("");
			agregarListaItem(selSubParametroSecuencial, listaSubParametroSecuencial, "idSubParametro", "descripcionSubParametro");
			selSubParametroSecuencial.val(lista[index].idSubParametro);
		}
	}
}

function bajarItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selSubParametroSecuencial.val() == lista[i].idSubParametro){
				var index = i;
				break;
			}
		}
		if(index == lista.length-1){
			mostrarMensaje("Es el ultimo valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaSubParametroSecuencial = [];
			for(var i=0;i<lista.length;i++){
				if(i == index){
					listaSubParametroSecuencial.push(lista[index+1]);
				}else if(i == index+1){
					listaSubParametroSecuencial.push(lista[index]);
				}else{
					listaSubParametroSecuencial.push(lista[i]);
				}
			}
			selSubParametroSecuencial.html("");
			agregarListaItem(selSubParametroSecuencial, listaSubParametroSecuencial, "idSubParametro", "descripcionSubParametro");
			selSubParametroSecuencial.val(lista[index].idSubParametro);
		}
	}
}

function validarMoverItem(){
	var estado = true;
	if(selSubParametroSecuencial.val()==null){
		mostrarMensaje('Debe seleccionar un Subparámetro', ConstanteServices.IMAGEN_DANGER);
		estado = false;
	}else if(selSubParametroSecuencial.val().length > 1){
		mostrarMensaje('Debe seleccionar solo un Subparámetro', ConstanteServices.IMAGEN_DANGER);
		estado = false;
	}
	return estado;
}

function validarFormularioBusqueda(){
    var estado = true;
    if(IsEmpty(cboPtarPorSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar por Sector.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
    }
    
    return estado;
}

function buscarlistaSubParametroSecuencial(){
	if(validarFormularioBusqueda()) {
	 	$.ajax({
            url : "./buscarlistaSubParametroSecuencial",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null)
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	var listaJSON = [];
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		listaJSON =  JSON.parse(ListaResultado);		            		
            	} 
            	selSubParametroSecuencial.html("");
                $.each(listaJSON, function(i, item) {
                	selSubParametroSecuencial.append($("<option/>").attr("value",listaJSON[i].idSubParametro).text(""+parseFloat(i+1)+". "+listaJSON[i].descripcionSubParametro)); 
                });        
                listaSubParametroSecuencial = listaJSON;
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selSubParametroSecuencial.html("");
            	listaSubParametroSecuencial = [];
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selSubParametroSecuencial.html("");
		listaSubParametroSecuencial = [];
	}
}

function registrarSubParametroPtarSecuencialAuxiliar() {
	construirListaOrden();
	if(validarFormularioBusqueda()) {
		$.ajax({
			url: "./registrarSubParametroPtarSecuencial",
			type: "POST",
			dataType: "json",
			cache: true,
			data : {
				 idPtarxsector    		  : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
				 listaSubParametroSecuencial : JSON.stringify(listaSubParametroSecuencial)
			}
		}).done(function(respuestaBean) {
		    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
		    	buscarlistaSubParametroSecuencial();
	        } else {
	        }
		}).fail(function( jqXHR, textStatus, errorThrown ) {
			validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
		});  
	}
}

function registrarSubParametroPtarSecuencial() {
	construirListaOrden();
	if(validarFormularioBusqueda()) {
		$.ajax({
			url: "./registrarSubParametroPtarSecuencial",
			type: "POST",
			dataType: "json",
			cache: false,
			data : {
				 idPtarxsector    		  : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
				 listaSubParametroSecuencial : JSON.stringify(listaSubParametroSecuencial)
			}
		}).done(function(respuestaBean) {
		    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){		    	
		    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
		    	buscarlistaSubParametroSecuencial();
	        } else {
	            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
		}).fail(function( jqXHR, textStatus, errorThrown ) {
			validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
		});  
	}
}

function construirListaOrden(){
	for(var i=0;i<listaSubParametroSecuencial.length; i++){
		listaSubParametroSecuencial[i].secuencia = i+1;
	}
}

function obtenerValoresLista(){
	var lista = [];
	for(var i=0; i<selSubParametroSecuencial[0].length; i++){
		var objeto = {};
		objeto.idSubParametro = selSubParametroSecuencial[0].options[i].value;
		objeto.descripcionSubParametro = selSubParametroSecuencial[0].options[i].text;
		lista.push(objeto);
	}
	return lista;
}
