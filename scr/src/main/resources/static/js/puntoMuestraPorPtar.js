agregarBotonera();
agregarBotoneraOrden();
function agregarBotonera(){
	var botoneraHtml = "<br/>";
	botoneraHtml += " <input type='button' title='Agregar' id='btnAdicionar' class='btn btn-default btn-xs' value='>' >";
	botoneraHtml += " <input type='button' title='Agregar Todos' id='btnAdicionarTodos' class='btn btn-default btn-xs' value='&raquo;'/>";
	botoneraHtml += " <input type='button' title='Quitar' id='btnRemover'   class='btn btn-default btn-xs' value='<'/><br />";
	botoneraHtml += " <input type='button' title='Quitar Todos' id='btnRemoverTodos'  class='btn btn-default btn-xs' value='&laquo;' />";
	$("#botonera").html(""+botoneraHtml);
}

function agregarBotoneraOrden(){
	var botoneraOrdenHtml = "<br/>";
	botoneraOrdenHtml += " <input type='button' title='Subir' id='btnSubir' class='btn btn-default btn-xs' value='&xwedge;' >";
	botoneraOrdenHtml += " <input type='button' title='Bajar' id='btnBajar' class='btn btn-default btn-xs' value='&xvee;'/>";
	$("#botoneraOrden").html(""+botoneraOrdenHtml);
}

$(document).ready(function() {
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
	var cboParametro = null;
	var cboSubParametro = null;
	var selPerfilesNoAsignados = null;
	var selPerfilesAsignados = null;
	var listaAsignados = [];
	var listaNoAsignados = [];
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnCancelar = $('#btnCancelar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboPtarPorSector = $("#cboPtarPorSector");
	cboParametro = $("#cboParametro");
	cboSubParametro = $("#cboSubParametro");
	selPerfilesNoAsignados = $("#selPerfilesNoAsignados");
	selPerfilesAsignados = $("#selPerfilesAsignados");
	
	cboPtarPorSector.html("");
    $.each(ltaPtarPorSector, function(i, item) {
    	cboPtarPorSector.append($("<option/>").attr("value",ltaPtarPorSector[i].idPtarxsector).text(""+ltaPtarPorSector[i].descripcionPtar +" - "+ltaPtarPorSector[i].descripcionSector)); 
    }); 

	mostrarMensaje();
    
    cboPtarPorSector.change(function(event){
    	mostrarMensaje();
		buscarParametro();
		limpiarBusqueda();
    });
    
    btnCancelar.click(function(event) { 
    	mostrarMensaje();
    	buscarPuntosMuestraNoAsignados();
        buscarPuntosMuestraAsignados();
        limpiarBusqueda();
    });
    
    btnGrabar.click(function(event) { 
    	mostrarMensaje();
    	var tituloModal = 'Mantenimiento de Puntos de Muestra por PTAR';
		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'registrarPuntoMuestraPtar()', '', tituloModal);
    });
    
    cboParametro.change(function(event){
		mostrarMensaje();
		buscarSubParametro();
		limpiarBusqueda();
    });
    
    cboSubParametro.change(function(event){
		mostrarMensaje();
		buscarPuntosMuestraNoAsignados();
		buscarPuntosMuestraAsignados();
		limpiarBusqueda();
    });
    
    $("#btnAdicionar").click(function(event) { 
    	adicionarItem();
    });
    
	$("#btnAdicionarTodos").click(function(event) { 
		adicionarTodos();	
    });

	$("#btnRemover").click(function(event) { 
		removerItem();
	});
	
	$("#btnRemoverTodos").click(function(event) { 
		removerTodos();
	});
	
	$("#btnSubir").click(function(event) { 
		subirItem();
	});
	
	$("#btnBajar").click(function(event) { 
		bajarItem();
	});
	
	buscarParametro();
	
	/*Filtro de Búsqueda*/
	$('#txtBusqNoAsignado').keyup(function () {	           
    	var rex = new RegExp($(this).val(), 'i');
    	$("#selPerfilesNoAsignados").children("option").hide();
    	$("#selPerfilesNoAsignados").children("option").filter(function () {
        	return rex.test($(this).text());
    	}).show();

	});
	
	$('#txtBusqAsignado').keyup(function () {	           
    	var rex = new RegExp($(this).val(), 'i');
    	$("#selPerfilesAsignados").children("option").hide();
    	$("#selPerfilesAsignados").children("option").filter(function () {
        	return rex.test($(this).text());
    	}).show();

	});
	/**/
}

function limpiarBusqueda(){
    $("#txtBusqAsignado").val("");
    $("#txtBusqNoAsignado").val("");
    $("#txtBusqAsignado").keyup();
    $("#txtBusqNoAsignado").keyup();
}

function removerTodos(){
	for(var i = 0 ; i < listaAsignados.length ; i++){
		listaNoAsignados.push(clonarObjeto(listaAsignados[i]));
	}
	listaAsignados = [];
	listarComboAsignados(listaAsignados);
	listarComboNoAsignados(listaNoAsignados);
}

function adicionarTodos(){
	for(var i = 0 ; i < listaNoAsignados.length ; i++){
		listaAsignados.push(clonarObjeto(listaNoAsignados[i]));
	}
	listaNoAsignados = [];
	listarComboAsignados(listaAsignados);
	listarComboNoAsignados(listaNoAsignados);
}

function adicionarItem(){
	var itemsMarcados = $("#selPerfilesNoAsignados").val();
	if(itemsMarcados != null && itemsMarcados != undefined && itemsMarcados.length > 0){
		for(var i = 0 ; i < itemsMarcados.length ; i++){
			for(var j = 0 ; j < listaNoAsignados.length ; j++){
				if(listaNoAsignados[j].idPuntoMuestra == itemsMarcados[i]){
					listaAsignados.push(listaNoAsignados[j]);
					listaNoAsignados.splice(j, 1);
					break;
				}
			}
		}
		listarComboAsignados(listaAsignados);
		listarComboNoAsignados(listaNoAsignados);		
	}	
}

function removerItem(){
	var itemsMarcados = $("#selPerfilesAsignados").val();
	if(itemsMarcados != null && itemsMarcados != undefined && itemsMarcados.length > 0){
		for(var i = 0 ; i < itemsMarcados.length ; i++){
			for(var j = 0 ; j < listaAsignados.length ; j++){
				if(listaAsignados[j].idPuntoMuestra == itemsMarcados[i]){
					listaNoAsignados.push(listaAsignados[j]);
					listaAsignados.splice(j, 1);
					break;
				}
			}
		}		
		listarComboAsignados(listaAsignados);
		listarComboNoAsignados(listaNoAsignados);
	}
}

function subirItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selPerfilesAsignados.val() == lista[i].idPuntoMuestra){
				var index = i;
				break;
			}
		}
		if(index == 0){
			mostrarMensaje("Es el primer valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaAsignados = [];
			for(var i=0;i<lista.length;i++){
				if(i == index-1){
					listaAsignados.push(lista[index]);
				}else if(i == index){
					listaAsignados.push(lista[index-1]);
				}else{
					listaAsignados.push(lista[i]);
				}
			}
			selPerfilesAsignados.html("");
			agregarListaItem(selPerfilesAsignados, listaAsignados, "idPuntoMuestra", "descripcionPunto");
			selPerfilesAsignados.val(lista[index].idPuntoMuestra);
		}
	}
}

function bajarItem(){
	if(validarMoverItem()){
		var lista = obtenerValoresLista();
		for(var i=0; i<lista.length;i++){
			if(selPerfilesAsignados.val() == lista[i].idPuntoMuestra){
				var index = i;
				break;
			}
		}
		if(index == lista.length-1){
			mostrarMensaje("Es el ultimo valor", ConstanteServices.IMAGEN_DANGER);
		}else{
			listaAsignados = [];
			for(var i=0;i<lista.length;i++){
				if(i == index){
					listaAsignados.push(lista[index+1]);
				}else if(i == index+1){
					listaAsignados.push(lista[index]);
				}else{
					listaAsignados.push(lista[i]);
				}
			}
			selPerfilesAsignados.html("");
			agregarListaItem(selPerfilesAsignados, listaAsignados, "idPuntoMuestra", "descripcionPunto");
			selPerfilesAsignados.val(lista[index].idPuntoMuestra);
		}
	}
}

function validarMoverItem(){
	var estado = true;
	if(selPerfilesAsignados.val()==null){
		mostrarMensaje('Debe seleccionar un Punto de Muestra', ConstanteServices.IMAGEN_DANGER);
		estado = false;
	}else if(selPerfilesAsignados.val().length > 1){
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
    if(IsEmpty(cboParametro.val())){           
       	 mostrarMensaje('Debe Seleccionar un Parámetro.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
    }
    if(IsEmpty(cboSubParametro.val())){           
      	 mostrarMensaje('Debe Seleccionar un SubParámetro.', ConstanteServices.IMAGEN_DANGER); 
        estado = false; 
   }
    return estado;
}


function listarComboAsignados(lista){
	selPerfilesAsignados.html("");
    $.each(lista, function(i, item) {
    	selPerfilesAsignados.append($("<option/>").attr("value",lista[i].idPuntoMuestra).text(""+parseFloat(i+1)+". "+lista[i].descripcionPunto));
    }); 
}

function listarComboNoAsignados(listaNo){
	selPerfilesNoAsignados.html("");
    $.each(listaNo, function(i, item) {
    	selPerfilesNoAsignados.append($("<option/>").attr("value",listaNo[i].idPuntoMuestra).text(""+listaNo[i].descripcionPunto)); 
    });   
}

function buscarSubParametro(){
	if(validarFormularioBusquedaParametro()) {
	 	$.ajax({
            url : "./buscarSubParametroAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
					idParametro    	: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	var listaJSON = [];
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		listaJSON =  JSON.parse(ListaResultado);		            		
            	} 
            	selPerfilesAsignados.html("");
            	selPerfilesNoAsignados.html("");
            	listaAsignados = [];
            	listaNoAsignados = [];
            	cboSubParametro.html("");   
                agregarListaItem(cboSubParametro, listaJSON, "idSubParametro", "descripcionSubParametro");
                buscarPuntosMuestraNoAsignados();
                buscarPuntosMuestraAsignados();
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPerfilesAsignados.html("");
            	selPerfilesNoAsignados.html("");
            	listaAsignados = [];
            	listaNoAsignados = [];
            	cboSubParametro.html("");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selPerfilesAsignados.html("");
    	selPerfilesNoAsignados.html("");
    	listaAsignados = [];
    	listaNoAsignados = [];
    	cboSubParametro.html("");
	}
}

function buscarParametro(){
	if(validarFormularioBusquedaPtaxSector()) {
	 	$.ajax({
            url : "./buscarParametroAsignados",
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
            	selPerfilesAsignados.html("");
            	selPerfilesNoAsignados.html("");
            	listaAsignados = [];
            	listaNoAsignados = [];
            	cboParametro.html("");   
                agregarListaItem(cboParametro, listaJSON, "idParametro", "descripcionParametro");
                buscarSubParametro();
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPerfilesAsignados.html("");
            	selPerfilesNoAsignados.html("");
            	listaAsignados = [];
            	listaNoAsignados = [];
            	cboParametro.html("");
            	cboSubParametro.html("");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selPerfilesAsignados.html("");
    	selPerfilesNoAsignados.html("");
    	listaAsignados = [];
    	listaNoAsignados = [];
		cboParametro.html("");
		cboSubParametro.html("");
	}
}

function buscarPuntosMuestraAsignados(){
	if(validarFormularioBusqueda()) {
	 	$.ajax({
            url : "./buscarPuntosMuestraAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
					idParametro: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null),
					idSubParametro: (!IsEmpty(cboSubParametro.val()) ? cboSubParametro.val() : null)
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	var listaJSON = [];
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		listaJSON =  JSON.parse(ListaResultado);		            		
            	} 
            	selPerfilesAsignados.html("");
                $.each(listaJSON, function(i, item) {
                	selPerfilesAsignados.append($("<option/>").attr("value",listaJSON[i].idPuntoMuestra).text(""+parseFloat(i+1)+". "+listaJSON[i].descripcionPunto)); 
                });        
                listaAsignados = listaJSON;
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPerfilesAsignados.html("");
            	listaAsignados = [];
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selPerfilesAsignados.html("");
		listaAsignados = [];
	}
}

function buscarPuntosMuestraNoAsignados(){
	if(validarFormularioBusqueda()) {
	 	$.ajax({
            url : "./buscarPuntosMuestraNoAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
					idParametro: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null),
					idSubParametro: (!IsEmpty(cboSubParametro.val()) ? cboSubParametro.val() : null)
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	var listaJSON = [];
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		listaJSON =  JSON.parse(ListaResultado);		            		
            	} 
            	selPerfilesNoAsignados.html("");
                $.each(listaJSON, function(i, item) {
                	selPerfilesNoAsignados.append($("<option/>").attr("value",listaJSON[i].idPuntoMuestra).text(""+listaJSON[i].descripcionPunto)); 
                });      
                listaNoAsignados = listaJSON;
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPerfilesNoAsignados.html("");
            	listaNoAsignados = [];
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}else{
		selPerfilesNoAsignados.html("");
		listaNoAsignados = [];
	}
}

function validarFormularioBusquedaParametro(){
    var estado = true;
    if(IsEmpty(cboPtarPorSector.val())){           
      	mostrarMensaje('Debe Seleccionar un Ptar por Sector.', ConstanteServices.IMAGEN_DANGER); 
        estado = false; 
    }
    if(IsEmpty(cboParametro.val())){           
       	mostrarMensaje('Debe Seleccionar un Parámetro.', ConstanteServices.IMAGEN_DANGER); 
        estado = false; 
    }
    return estado;
}

function validarFormularioBusquedaPtaxSector(){
    var estado = true;
    if(IsEmpty(cboPtarPorSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar por Sector.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
    }
    return estado;
}

function registrarPuntoMuestraPtar() {
	construirListaOrden();
	if(validarFormularioBusqueda()) {
		$.ajax({
			url: "./registrarPuntoMuestraPtar",
			type: "POST",
			dataType: "json",
			cache: false,
			data : {
				 idPtarxsector    		  : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
				 idParametro    		  : (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null),
				 idSubParametro			  : (!IsEmpty(cboSubParametro.val()) ? cboSubParametro.val() : null),
				 listaAsignadosPuntoxPtar : JSON.stringify(listaAsignados)
			}
		}).done(function(respuestaBean) {
		    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){		    	
		    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
		    	buscarPuntosMuestraNoAsignados();
		        buscarPuntosMuestraAsignados();    
	        } else {
	            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
		    limpiarBusqueda();
		}).fail(function( jqXHR, textStatus, errorThrown ) {
			validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
		});  
	}
}

function construirListaOrden(){
	for(var i=0;i<listaAsignados.length; i++){
		listaAsignados[i].secuencia = i+1;
	}
}

function obtenerValoresLista(){
	var lista = [];
	for(var i=0; i<selPerfilesAsignados[0].length; i++){
		var objeto = {};
		objeto.idPuntoMuestra = selPerfilesAsignados[0].options[i].value;
		objeto.descripcionPunto = selPerfilesAsignados[0].options[i].text;
		lista.push(objeto);
	}
	return lista;
}
