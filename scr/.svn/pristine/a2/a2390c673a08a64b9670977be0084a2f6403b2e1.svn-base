agregarBotoneraOrden();

function agregarBotoneraOrden(){
	var botoneraOrdenHtml = "<br/>";
	botoneraOrdenHtml += " <input type='button' title='Subir' id='btnSubir' class='btn btn-default btn-xs' value='&xwedge;' >";
	botoneraOrdenHtml += " <input type='button' title='Bajar' id='btnBajar' class='btn btn-default btn-xs' value='&xvee;'/>";
	$("#botoneraOrden").html(""+botoneraOrdenHtml);
}

$(document).ready(function() {
	console.log('secuencial');
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
	var selPuntosMuestraSecuencial = null;
	var listaPuntosMuestraSecuencial = [];
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnCancelar = $('#btnCancelar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboPtarPorSector = $("#cboPtarPorSector");
	selPuntosMuestraSecuencial = $("#selPuntosMuestraSecuencial");
	
	cboPtarPorSector.html("");
    $.each(ltaPtarPorSector, function(i, item) {
    	cboPtarPorSector.append($("<option/>").attr("value",ltaPtarPorSector[i].idPtarxsector).text(""+ltaPtarPorSector[i].descripcionPtar +" - "+ltaPtarPorSector[i].descripcionSector)); 
    }); 

	mostrarMensaje();
    
    cboPtarPorSector.change(function(event){
    	mostrarMensaje();
    	buscarlistaPuntosMuestraSecuencial();
    	registrarPuntoMuestraPtarSecuencialAuxiliar();    	
    });
    
    btnCancelar.click(function(event) { 
    	mostrarMensaje();
    	buscarlistaPuntosMuestraSecuencial();
    });
    
    btnGrabar.click(function(event) { 
    	mostrarMensaje();
    	var tituloModal = 'Mantenimiento de Secuencial de Reporte por PTAR';
		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'registrarPuntoMuestraPtarSecuencial()', '', tituloModal);
    });
	
	$("#btnSubir").click(function(event) { 
		subirItem();
	});
	
	$("#btnBajar").click(function(event) { 
		bajarItem();
	});
	
	buscarlistaPuntosMuestraSecuencial();
	registrarPuntoMuestraPtarSecuencialAuxiliar(); 
}

function subirItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selPuntosMuestraSecuencial.val() == lista[i].idPuntoMuestra){
				var index = i;
				break;
			}
		}
		if(index == 0){
			mostrarMensaje("Es el primer valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaPuntosMuestraSecuencial = [];
			for(var i=0;i<lista.length;i++){
				if(i == index-1){
					listaPuntosMuestraSecuencial.push(lista[index]);
				}else if(i == index){
					listaPuntosMuestraSecuencial.push(lista[index-1]);
				}else{
					listaPuntosMuestraSecuencial.push(lista[i]);
				}
			}
			selPuntosMuestraSecuencial.html("");
			agregarListaItem(selPuntosMuestraSecuencial, listaPuntosMuestraSecuencial, "idPuntoMuestra", "descripcionPunto");
			selPuntosMuestraSecuencial.val(lista[index].idPuntoMuestra);
		}
	}
}

function bajarItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selPuntosMuestraSecuencial.val() == lista[i].idPuntoMuestra){
				var index = i;
				break;
			}
		}
		if(index == lista.length-1){
			mostrarMensaje("Es el ultimo valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaPuntosMuestraSecuencial = [];
			for(var i=0;i<lista.length;i++){
				if(i == index){
					listaPuntosMuestraSecuencial.push(lista[index+1]);
				}else if(i == index+1){
					listaPuntosMuestraSecuencial.push(lista[index]);
				}else{
					listaPuntosMuestraSecuencial.push(lista[i]);
				}
			}
			selPuntosMuestraSecuencial.html("");
			agregarListaItem(selPuntosMuestraSecuencial, listaPuntosMuestraSecuencial, "idPuntoMuestra", "descripcionPunto");
			selPuntosMuestraSecuencial.val(lista[index].idPuntoMuestra);
		}
	}
}

function validarMoverItem(){
	var estado = true;
	if(selPuntosMuestraSecuencial.val()==null){
		mostrarMensaje('Debe seleccionar un Punto de Muestra', ConstanteServices.IMAGEN_DANGER);
		estado = false;
	}else if(selPuntosMuestraSecuencial.val().length > 1){
		mostrarMensaje('Debe seleccionar solo un Punto de Muestra', ConstanteServices.IMAGEN_DANGER);
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

function buscarlistaPuntosMuestraSecuencial(){
	if(validarFormularioBusqueda()) {
	 	$.ajax({
            url : "./buscarlistaPuntosMuestraSecuencial",
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
            	selPuntosMuestraSecuencial.html("");
                $.each(listaJSON, function(i, item) {
                	selPuntosMuestraSecuencial.append($("<option/>").attr("value",listaJSON[i].idPuntoMuestra).text(""+parseFloat(i+1)+". "+listaJSON[i].descripcionPunto)); 
                });        
                listaPuntosMuestraSecuencial = listaJSON;
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPuntosMuestraSecuencial.html("");
            	listaPuntosMuestraSecuencial = [];
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selPuntosMuestraSecuencial.html("");
		listaPuntosMuestraSecuencial = [];
	}
}

function registrarPuntoMuestraPtarSecuencialAuxiliar() {
	construirListaOrden();
	if(validarFormularioBusqueda()) {
		$.ajax({
			url: "./registrarPuntoMuestraPtarSecuencial",
			type: "POST",
			dataType: "json",
			cache: true,
			data : {
				 idPtarxsector    		  : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
				 listaPuntosMuestraSecuencial : JSON.stringify(listaPuntosMuestraSecuencial)
			}
		}).done(function(respuestaBean) {
		    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
		    	buscarlistaPuntosMuestraSecuencial();
	        } else {
	        }
		}).fail(function( jqXHR, textStatus, errorThrown ) {
			validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
		});  
	}
}

function registrarPuntoMuestraPtarSecuencial() {
	construirListaOrden();
	if(validarFormularioBusqueda()) {
		$.ajax({
			url: "./registrarPuntoMuestraPtarSecuencial",
			type: "POST",
			dataType: "json",
			cache: false,
			data : {
				 idPtarxsector    		  : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
				 listaPuntosMuestraSecuencial : JSON.stringify(listaPuntosMuestraSecuencial)
			}
		}).done(function(respuestaBean) {
		    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){		    	
		    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
		    	buscarlistaPuntosMuestraSecuencial();
	        } else {
	            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
		}).fail(function( jqXHR, textStatus, errorThrown ) {
			validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
		});  
	}
}

function construirListaOrden(){
	for(var i=0;i<listaPuntosMuestraSecuencial.length; i++){
		listaPuntosMuestraSecuencial[i].secuencia = i+1;
	}
}

function obtenerValoresLista(){
	var lista = [];
	for(var i=0; i<selPuntosMuestraSecuencial[0].length; i++){
		var objeto = {};
		objeto.idPuntoMuestra = selPuntosMuestraSecuencial[0].options[i].value;
		objeto.descripcionPunto = selPuntosMuestraSecuencial[0].options[i].text;
		lista.push(objeto);
	}
	return lista;
}
