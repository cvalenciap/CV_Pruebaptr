agregarBotonera();
function agregarBotonera(){
	var botoneraHtml = "<br/>";
	botoneraHtml += " <input type='button' id='btnAdicionar' style='margin-top: 10px;' class='btn btn-default btn-xs' value='>' >";
	botoneraHtml += " <input type='button' id='btnAdicionarTodos' style='margin-top: 10px;' class='btn btn-default btn-xs' value='&raquo;'/>";
	botoneraHtml += " <input type='button' id='btnRemover'   style='margin-top: 10px;' class='btn btn-default btn-xs' value='<'/><br />";
	botoneraHtml += " <input type='button' id='btnRemoverTodos'  style='margin-top: 10px;' class='btn btn-default btn-xs' value='&laquo;' />";
	$("#botonera").html(""+botoneraHtml);
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
	selPerfilesNoAsignados = $("#selPerfilesNoAsignados");
	selPerfilesAsignados = $("#selPerfilesAsignados");
	
	agregarListaItem(cboPtarPorSector, ltaPtarPorSector, "idPtarxsector", "descripcionPtarxSector");

	
	cboPtarPorSector.change(function(event){
    	mostrarMensaje();
    	buscarParametro();
    	limpiarBusqueda();
    });
	
	btnCancelar.click(function(event) { 
    	mostrarMensaje();
    	buscarSubParametroNoAsignados();
    	buscarSubParametroAsignados();
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

	btnGrabar.click(function(event) { 
    	mostrarMensaje();
    	if(validarFormularioBusquedaTotal()) {
    		var tituloModal = 'Mantenimiento de SubParametros por PTAR';
    		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'registrarSubParametroPtarSector()', '', tituloModal);
        }
    });
	
	cboParametro.change(function(event){
		mostrarMensaje();
    	buscarSubParametroNoAsignados();
    	buscarSubParametroAsignados();
    	limpiarBusqueda();
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


function registrarSubParametroPtarSector() {
	$.ajax({
		url: "./registrarSubParametroPtarSector",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 idPtarxsector    				 : (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
			 idParametro    				 : (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null),
			 listaAsignadosSubParametroxPtar : JSON.stringify(listaAsignados)
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){		    	
	    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	    	buscarSubParametroNoAsignados();
            buscarSubParametroAsignados();    
        } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
	    limpiarBusqueda();
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	}); 
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
				if(listaNoAsignados[j].idSubParametro == itemsMarcados[i]){
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
				if(listaAsignados[j].idSubParametro == itemsMarcados[i]){
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

function listarComboAsignados(lista){
	selPerfilesAsignados.html("");
    $.each(lista, function(i, item) {
    	selPerfilesAsignados.append($("<option/>").attr("value",lista[i].idSubParametro).text(""+lista[i].descripcionSubParametro)); 
    }); 
}

function listarComboNoAsignados(listaNo){
	selPerfilesNoAsignados.html("");
    $.each(listaNo, function(i, item) {
    	selPerfilesNoAsignados.append($("<option/>").attr("value",listaNo[i].idSubParametro).text(""+listaNo[i].descripcionSubParametro)); 
    });   
}

function validarFormularioBusqueda(){
    var estado = true;
     if(IsEmpty(cboPtarPorSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar por Sector.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}

function validarFormularioBusquedaParametro(){
    var estado = true;
     if(IsEmpty(cboParametro.val())){           
       	 mostrarMensaje('Debe Seleccionar un Parámetro.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}

function validarFormularioBusquedaTotal(){
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

function buscarParametro(){
	if(validarFormularioBusqueda()) {
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
                buscarSubParametroNoAsignados();
                buscarSubParametroAsignados();
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	selPerfilesAsignados.html("");
            	selPerfilesNoAsignados.html("");
            	listaAsignados = [];
            	listaNoAsignados = [];
            	cboParametro.html("");
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
	}
}
function buscarSubParametroAsignados(){
	if(validarFormularioBusquedaParametro()) {
	 	$.ajax({
            url : "./buscarSubParametroAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
					idParametro: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
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
                	selPerfilesAsignados.append($("<option/>").attr("value",listaJSON[i].idSubParametro).text(""+listaJSON[i].descripcionSubParametro)); 
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

function buscarSubParametroNoAsignados(){
	if(validarFormularioBusquedaParametro()) {
	 	$.ajax({
            url : "./buscarSubParametroNoAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(cboPtarPorSector.val()) ? cboPtarPorSector.val() : null),
					idParametro: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
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
                	selPerfilesNoAsignados.append($("<option/>").attr("value",listaJSON[i].idSubParametro).text(""+listaJSON[i].descripcionSubParametro)); 
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

