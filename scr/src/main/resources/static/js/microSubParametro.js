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
	cboSubParametro = $("#cboSubParametro");
	selPerfilesNoAsignados = $("#selPerfilesNoAsignados");
	selPerfilesAsignados = $("#selPerfilesAsignados");
	
	cboSubParametro.html("");
    $.each(ltaSubParametro, function(i, item) {
    	cboSubParametro.append($("<option/>").attr("value",ltaSubParametro[i].idptarPorSector+"_"+ltaSubParametro[i].idParametro+"_"+ltaSubParametro[i].idSubParametro).text(""+ltaSubParametro[i].descripcionLarga)); 
    }); 
    
    mostrarMensaje();
    buscarMicroSubParametroNoAsignados();
	buscarMicroSubParametroAsignados();
    
    cboSubParametro.change(function(event){
    	mostrarMensaje();
    	buscarMicroSubParametroNoAsignados();
    	buscarMicroSubParametroAsignados(); 
    });
    
    btnCancelar.click(function(event) { 
    	mostrarMensaje();
    	buscarMicroSubParametroNoAsignados();
    	buscarMicroSubParametroAsignados(); 
    });
    
    btnGrabar.click(function(event) { 
    	mostrarMensaje();
    	if(validarFormularioBusqueda()) {
    		var tituloModal = 'Mantenimiento de Microorganismos por SubParametros por PTAR';
    		modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'registrarMicroSubParametroPtarSector()', '', tituloModal);
        }
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
				if(listaNoAsignados[j].idMicroOrganismo == itemsMarcados[i]){
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
				if(listaAsignados[j].idMicroOrganismo == itemsMarcados[i]){
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
    	selPerfilesAsignados.append($("<option/>").attr("value",lista[i].idMicroOrganismo).text(""+lista[i].descripcionMicroOrganismo)); 
    }); 
}

function listarComboNoAsignados(listaNo){
	selPerfilesNoAsignados.html("");
    $.each(listaNo, function(i, item) {
    	selPerfilesNoAsignados.append($("<option/>").attr("value",listaNo[i].idMicroOrganismo).text(""+listaNo[i].descripcionMicroOrganismo)); 
    });   
}


function cortarIds(valor){
	var array = valor.split("_");
	return array;
}

function validarFormularioBusqueda(){
    var estado = true;
     if(IsEmpty(cboSubParametro.val())){           
       	 mostrarMensaje('Debe Seleccionar un Sub Parametro.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}


function buscarMicroSubParametroAsignados(){
	if(validarFormularioBusqueda()) {
		var infoSubParametro = cboSubParametro.val();
		var ltaIds = cortarIds(infoSubParametro);
	 	$.ajax({
            url : "./buscarMicroSubParametroAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: (!IsEmpty(ltaIds[0]) ? ltaIds[0] : null),
					idParametro: (!IsEmpty(ltaIds[1]) ? ltaIds[1] : null),
					idSubParametro: (!IsEmpty(ltaIds[2]) ? ltaIds[2] : null)
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
                	selPerfilesAsignados.append($("<option/>").attr("value",listaJSON[i].idMicroOrganismo).text(""+listaJSON[i].descripcionMicroOrganismo)); 
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

function buscarMicroSubParametroNoAsignados(){
	if(validarFormularioBusqueda()) {
		var infoSubParametro = cboSubParametro.val();
		var ltaIds = cortarIds(infoSubParametro);
	 	$.ajax({
            url : "./buscarMicroSubParametroNoAsignados",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
            	idPtarPorSector: (!IsEmpty(ltaIds[0]) ? ltaIds[0] : null),
				idParametro: (!IsEmpty(ltaIds[1]) ? ltaIds[1] : null),
				idSubParametro: (!IsEmpty(ltaIds[2]) ? ltaIds[2] : null)
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
                	selPerfilesNoAsignados.append($("<option/>").attr("value",listaJSON[i].idMicroOrganismo).text(""+listaJSON[i].descripcionMicroOrganismo)); 
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

function registrarMicroSubParametroPtarSector() {
	var infoSubParametro = cboSubParametro.val();
	var ltaIds = cortarIds(infoSubParametro);
	$.ajax({
		url: "./registrarMicroSubParametroPtarSector",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 idPtarPorSector : (!IsEmpty(ltaIds[0]) ? ltaIds[0] : null),
			 idParametro : (!IsEmpty(ltaIds[1]) ? ltaIds[1] : null),
			 idSubParametro : (!IsEmpty(ltaIds[2]) ? ltaIds[2] : null),
			 listaAsignadosMicroSubParametro : JSON.stringify(listaAsignados)
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){		    	
	    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
	    	buscarMicroSubParametroNoAsignados();
	    	buscarMicroSubParametroAsignados();    
        } else {
            mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

