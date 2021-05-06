agregarBotonera();
function agregarBotonera(){
	var botoneraHtml = "<br/>";
	botoneraHtml += " <input type='button' id='btnAdicionar' class='btn btn-default btn-xs' value='>' >";
	botoneraHtml += " <input type='button' id='btnAdicionarTodos' class='btn btn-default btn-xs' value='&raquo;'/>";
	botoneraHtml += " <input type='button' id='btnRemover'   class='btn btn-default btn-xs' value='<'/><br />";
	botoneraHtml += " <input type='button' id='btnRemoverTodos'  class='btn btn-default btn-xs' value='&laquo;' />";
	$("#botonera").html(""+botoneraHtml);
}

$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var txtNumeroFicha = null;
	var cboTipoDocumento = null;
	var txtnumeroDoc = null;
	var txtNombre = null;
	var txtApellido = null;
	var cboPtarPorSector = null;
	var txtCargo = null;
	var txtObservacion = null;
	var btnGrabar = null;
	var btnRegresar = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var btnBuscarTrabajador = null;
	var divMantenimiento = null;
	var txtCodigo = null;
	var cboTipo = null;
	var txtUsuario = null;
	var txtCorreo = null;
	var selPerfilesNoAsignados = null;
	var selPerfilesAsignados = null;
	var listaAsignados = [];
	var listaNoAsignados = [];
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	divMantenimiento = $("#divMantenimiento");
	txtNumeroFicha = $('#txtNumeroFicha');
	cboTipoDocumento = $('#cboTipoDocumento');
	txtnumeroDoc = $('#txtnumeroDoc');
	txtNombre = $('#txtNombre');
	txtApellido = $('#txtApellido');
	txtCargo = $('#txtCargo');
	txtObservacion = $('#txtObservacion');
	btnGrabar = $('#btnGrabar');
	btnRegresar = $('#btnRegresar');	
	btnBuscarTrabajador = $('#btnBuscarTrabajador');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	txtCodigo = $("#txtCodigo");
	cboTipo = $("#cboTipo");
	txtUsuario = $("#txtUsuario");
	txtCorreo = $("#txtCorreo");	
	selPerfilesNoAsignados = $("#selPerfilesNoAsignados");
	selPerfilesAsignados = $("#selPerfilesAsignados");
	
	inputNumerico("txtNumeroFicha", 5);
	agregarListaItem(cboTipo, ltaTipo, "idDetalleGeneral", "descripcionCorta");
	
	agregarListaItem(cboTipoDocumento, ltaTipoDocumento, "idDetalleGeneral", "descripcionCorta");	
	changeTipoDocu("#cboTipoDocumento", "#txtnumeroDoc", '');
	
	$("#tipoAnalistaInterno").val(""+ConstanteServices.ID_ANALISTA_INTERNO);
	$("#tipoAnalistaExterno").val(""+ConstanteServices.ID_ANALISTA_EXTERNO);	
	$("#tipoAnalistaInterno").prop("checked","checked");
	habilitarCamposAnalistaInterno()
	
	
	btnRegresar.click(function() {
		 $.redirect('./cargarVentanaBandejaAnalista', {regresar:ConstanteServices.REGRESAR});
	}); 
	
	btnBuscarTrabajador.click(function() {
		buscarTrabajador();
	}); 
	
	$('#cboTipoDocumento').change(function() {
		changeTipoDocu("#cboTipoDocumento", "#txtnumeroDoc", '');
    });
	
	btnGrabar.click(function() {
		validacionPreviaGrabar();
	}); 	
	
	$('#tipoAnalistaInterno').click(function() {
		if(modo == ConstanteServices.ESTADO_OPCION_EDITAR && objAnalista.tipoAnalista == ConstanteServices.ID_ANALISTA_INTERNO){
			pintarDataFormulario();
		}else{
			habilitarCamposAnalistaInterno();
			limpiarFormularioTrabajador();
			$("#txtNumeroFicha").val("");
		}
    });
	
	$('#tipoAnalistaExterno').click(function() {
		if(modo == ConstanteServices.ESTADO_OPCION_EDITAR && objAnalista.tipoAnalista == ConstanteServices.ID_ANALISTA_EXTERNO){
			pintarDataFormulario();
		}else{
			habilitarCamposAnalistaExterno();
			limpiarFormularioTrabajador();
			$("#txtNumeroFicha").val("");
		}
    });	
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER || modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		pintarDataFormulario();
	}
	
	cargarParametrosAsignados(ltaParametrosAsignados);
	cargarParametrosNoAsignados(ltaParametrosNoAsignados);
	

	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		divMantenimiento.find('input, textarea, button, select').attr('disabled','disabled');
		$("#btnGrabar").hide();
	}	
	
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

function adicionarItem(){
	var itemsMarcados = $("#selPerfilesNoAsignados").val();
	if(itemsMarcados != null && itemsMarcados != undefined && itemsMarcados.length > 0){
		for(var i = 0 ; i < itemsMarcados.length ; i++){
			for(var j = 0 ; j < listaNoAsignados.length ; j++){
				if(listaNoAsignados[j].idParametro == itemsMarcados[i]){
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
				if(listaAsignados[j].idParametro == itemsMarcados[i]){
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

function adicionarTodos(){
	for(var i = 0 ; i < listaNoAsignados.length ; i++){
		listaAsignados.push(clonarObjeto(listaNoAsignados[i]));
	}
	listaNoAsignados = [];
	listarComboAsignados(listaAsignados);
	listarComboNoAsignados(listaNoAsignados);
}

function removerTodos(){
	for(var i = 0 ; i < listaAsignados.length ; i++){
		listaNoAsignados.push(clonarObjeto(listaAsignados[i]));
	}
	listaAsignados = [];
	listarComboAsignados(listaAsignados);
	listarComboNoAsignados(listaNoAsignados);
}


function cargarParametrosNoAsignados(lista){
	listaNoAsignados = [];
	listarComboNoAsignados(lista);     
    listaNoAsignados = lista;
}

function cargarParametrosAsignados(lista){
	listaAsignados = [];
	listarComboAsignados(lista);
    listaAsignados = lista;
}

function listarComboAsignados(lista){
	selPerfilesAsignados.html("");
    $.each(lista, function(i, item) {
    	selPerfilesAsignados.append($("<option/>").attr("value",lista[i].idParametro).text(""+lista[i].descripcionParametro)); 
    }); 
}

function listarComboNoAsignados(listaNo){
	selPerfilesNoAsignados.html("");
    $.each(listaNo, function(i, item) {
    	selPerfilesNoAsignados.append($("<option/>").attr("value",listaNo[i].idParametro).text(""+listaNo[i].descripcionParametro)); 
    });   
}

function pintarDataFormulario(){
	if(objAnalista.tipoAnalista ==  ConstanteServices.ID_ANALISTA_INTERNO){
		$("#tipoAnalistaInterno").prop("checked","checked");
		habilitarCamposAnalistaInterno();
		limpiarFormularioTrabajador();
	}else if(objAnalista.tipoAnalista ==  ConstanteServices.ID_ANALISTA_EXTERNO){
		$("#tipoAnalistaExterno").prop("checked","checked");
		habilitarCamposAnalistaExterno();
		limpiarFormularioTrabajador();
	}
	txtCodigo.val(""+objAnalista.idAnalista);
	txtNumeroFicha.val(""+(!IsEmpty(objAnalista.numeroFicha) ? objAnalista.numeroFicha : ""));
	cboTipoDocumento.val(""+objAnalista.tipoDocumento);
	changeTipoDocu("#cboTipoDocumento", "#txtnumeroDoc", '');
	txtnumeroDoc.val(""+objAnalista.numeroDocumento);
	txtNombre.val(""+objAnalista.nombre);
	txtApellido.val(""+objAnalista.apellido);
	txtCargo.val(""+objAnalista.cargo);
	txtObservacion.val(""+objAnalista.observacion);
	txtCorreo.val(""+objAnalista.correo);
	txtUsuario.val(""+objAnalista.codigoUsuario);
	cboTipo.val(""+objAnalista.idFlagTipo);
}

function validacionPreviaGrabar(){
	if(validarFormularioDatos()){
		if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) { 
			existeAnalista();
		}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
			//comparamos si ah modificado tipoAnalista y Documento
			var tipoAnalista = $('input:radio[name=tipoAnalista]:checked').attr("value");
			if(objAnalista.tipoAnalista != tipoAnalista || objAnalista.numeroDocumento != txtnumeroDoc.val() ){
				existeAnalista();
			}else{
				validarUsuarioExiste();
			}
		}
	}	
}

function grabarAnalista(){
	$.ajax({
		url: "./grabarAnalista",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    				  : modo,	
			 analistaBean			  : JSON.stringify(obtenerObjAnalistaBean()),
			 listaParametrosAsignados : JSON.stringify(listaAsignados)
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    $.redirect('./cargarVentanaBandejaAnalista', {mensajeRespuesta: respuestaBean.mensajeRespuesta, estadoRespuesta: respuestaBean.estadoRespuesta, regresar:ConstanteServices.REGRESAR});	            		 		            	
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjAnalistaBean(){    
	var obj = {};
	obj.idAnalista = !IsEmpty(objAnalista.idAnalista) ? objAnalista.idAnalista : null;    
	obj.tipoAnalista = $('input:radio[name=tipoAnalista]:checked').attr("value");
	obj.numeroFicha = !IsEmpty(txtNumeroFicha.val()) ? txtNumeroFicha.val() : " ";  
	obj.tipoDocumento = cboTipoDocumento.val();
	obj.numeroDocumento   = Trim(txtnumeroDoc.val());
	obj.nombre = Trim(txtNombre.val());
	obj.apellido = Trim(txtApellido.val());
	obj.idPtarPorSector  = 0;
	obj.cargo = Trim(txtCargo.val());
	obj.observacion = Trim(txtObservacion.val());
	obj.flagRegistrador = !IsEmpty(objAnalista.flagRegistrador) ? objAnalista.flagRegistrador : " ";
	obj.flagAprobador = !IsEmpty(objAnalista.flagAprobador) ? objAnalista.flagAprobador : " ";
	obj.correo = Trim(txtCorreo.val());
	obj.codigoUsuario = Trim(txtUsuario.val());
	obj.idFlagTipo = !IsEmpty(cboTipo.val()) ? cboTipo.val() : null;
    return obj;
} 

function existeAnalista() {
	    var tipoAnalista = $('input:radio[name=tipoAnalista]:checked').attr("value");
	 	$.ajax({
            url : "./existeAnalista",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
            	numeroDocumento: (!IsEmpty(txtnumeroDoc.val()) ? Trim(txtnumeroDoc.val()) : null),
            	tipoAnalista : (!IsEmpty(tipoAnalista) ? tipoAnalista : null),
            }
        }).done(function(respuestaBean) {
        	mostrarMensaje();
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    			var indicador = respuestaBean.parametros.indicador; 
            	if(indicador == 0){
            		validarUsuarioExiste();
            	}else{
            		mostrarMensaje('Ya Existe un Analista con el Nro.Doc. indicado', ConstanteServices.IMAGEN_DANGER);
            	}
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	limpiarFormularioTrabajador();
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });		
}

function validarUsuarioExiste(){
	if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) { 
		existeUsuarioAnalista();
	}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
		if(objAnalista.codigoUsuario == Trim(txtUsuario.val())){
			var tituloModal = 'Mantenimiento de Analista';
   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarAnalista()', '', tituloModal);
		}else{
			existeUsuarioAnalista();
		}
	}	
}


function existeUsuarioAnalista() {
 	$.ajax({
        url : "./existeUsuarioAnalista",
        type : "POST",
        dataType : "json",
        async:false,
        data : {                     
        	codigoUsuario: (!IsEmpty(Trim(txtUsuario.val())) ? Trim(txtUsuario.val()) : null)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
			var indicadorCorreo = respuestaBean.parametros.indicadorCorreo; 
        	if(indicadorCorreo == 0){
        		var tituloModal = 'Mantenimiento de Analista';
	   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarAnalista()', '', tituloModal);
        	}else{
        		var nombreAnalista = respuestaBean.parametros.nombreAnalista;
        		mostrarMensaje('El Usuario ingresado le pertenece al analista '+nombreAnalista+'. Por favor Ingrese uno diferente!', ConstanteServices.IMAGEN_DANGER);
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        	limpiarFormularioTrabajador();
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });		
}

function validarFormularioDatos(){
    var estado = true;            
    mostrarMensaje();        
    var tipoAnalista = $('input:radio[name=tipoAnalista]:checked').attr("value");
    if(IsEmpty(tipoAnalista))
    {
    	mostrarMensaje('Debe Seleccionar el Tipo de Analista', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }    
    if(tipoAnalista == ConstanteServices.ID_ANALISTA_INTERNO){
    	if(IsEmpty(txtNumeroFicha.val()))
        {
    		mostrarMensaje('El campo Nro de Ficha es obligatorio', ConstanteServices.IMAGEN_DANGER);
        	estado = false;
        	return estado;
        }
    }
    
    if(IsEmpty(txtNombre.val()))
    {
    	mostrarMensaje('El campo Nombre es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }    
    if(IsEmpty(txtApellido.val())){
		    mostrarMensaje('El campo Apellidos es obligatorio', ConstanteServices.IMAGEN_DANGER);
		    estado = false;
		    return estado;
    }
    if(IsEmpty(txtCargo.val())){
	    mostrarMensaje('El campo Cargo es obligatorio', ConstanteServices.IMAGEN_DANGER);
	    estado = false;
	    return estado;
    }    
    if(IsEmpty(txtObservacion.val()))
    {
    	mostrarMensaje('El campo Observación es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(cboTipoDocumento.val()))
    {
    	mostrarMensaje('Debe Seleccionar Un Tipo de Documento.', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtnumeroDoc.val()))
    {
    	mostrarMensaje('El campo Nro.Doc es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(cboTipo.val()))
    {
    	mostrarMensaje('Debe Seleccionar Un Rol.', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtCorreo.val()))
    {
    	mostrarMensaje('El campo Correo es obligatorio.', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(IsEmpty(txtUsuario.val()))
    {
    	mostrarMensaje('El campo Usuario es obligatorio.', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    } 
    if(!IsEmpty(cboTipoDocumento.val()) && !IsEmpty(txtnumeroDoc.val())){
    	var descripcion = $('#cboTipoDocumento option:selected').text();
		if(descripcion == 'DNI') {
			if((txtnumeroDoc.val()).length != 8) {
				mostrarMensaje('Debe ingresar DNI correctamente.', ConstanteServices.IMAGEN_DANGER);
        		estado = false;
        		return estado;
			}
		 } 
		 if(descripcion == 'LE') {
			if((txtnumeroDoc.val()).length != 8) {
				mostrarMensaje('Debe ingresar LE correctamente.', ConstanteServices.IMAGEN_DANGER);
        		estado = false;
        		return estado;
			}
		} 
		if(descripcion == 'CE') {
			if((txtnumeroDoc.val()).length != 12) {
				mostrarMensaje('Debe ingresar CE correctamente.', ConstanteServices.IMAGEN_DANGER);
        		estado = false;
        		return estado;
			}
		}
		if(descripcion == 'RUC') {
			if((txtnumeroDoc.val()).length != 11) {
				mostrarMensaje('Debe ingresar RUC correctamente.', ConstanteServices.IMAGEN_DANGER);
        		estado = false;
        		return estado;
			}
		}    
    }	
    
    
    return estado;
}

function habilitarCamposAnalistaInterno(){
	cboTipoDocumento.prop( "disabled", true);
	txtnumeroDoc.prop( "disabled", true);
	txtNombre.prop( "disabled", true);
	txtApellido.prop( "disabled", true);
	txtCargo.prop( "disabled", true);
	txtCorreo.prop( "disabled", true);
	txtNumeroFicha.prop( "disabled", false);
	btnBuscarTrabajador.prop( "disabled", false);	
}

function habilitarCamposAnalistaExterno(){
	cboTipoDocumento.prop( "disabled", false);
	txtnumeroDoc.prop( "disabled", false);
	txtNombre.prop( "disabled", false);
	txtApellido.prop( "disabled", false);
	txtCargo.prop( "disabled", false);
	txtCorreo.prop( "disabled", false);
	txtNumeroFicha.prop( "disabled", true);
	btnBuscarTrabajador.prop( "disabled", true);
}

function validarFichaTrabajador(){
    var estado = true;
     if(IsEmpty(txtNumeroFicha.val())){           
       	 mostrarMensaje('Debe Ingresar un Nro de Ficha.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}

function buscarTrabajador() {
	if(validarFichaTrabajador()) {
	 	$.ajax({
            url : "./buscarTrabajador",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
            	numeroFicha: (!IsEmpty(txtNumeroFicha.val()) ? txtNumeroFicha.val() : null)
            }
        }).done(function(respuestaBean) {
        	mostrarMensaje();
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	if(ListaResultado != null && ListaResultado != "[]") {		
            		var lta = JSON.parse(ListaResultado);            		
            		pintarFormularioTrabajador(lta[0]);		            		
            	} else {
            		limpiarFormularioTrabajador();
    		        mostrarMensaje('No se encontraron resultados coincidentes para el nro de ficha ingresado.', ConstanteServices.IMAGEN_DANGER);		            		
            	}
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            	limpiarFormularioTrabajador();
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });			
	}
}
function limpiarFormularioTrabajador(){
	txtNombre.val("");
	txtApellido.val("");
	txtCargo.val("");
	txtnumeroDoc.val("");
	txtCorreo.val("");
}

function pintarFormularioTrabajador(beanTrabajador){
	txtNombre.val(""+beanTrabajador.nombre);
	txtApellido.val(""+beanTrabajador.apellidoPaterno+" "+beanTrabajador.apellidoMaterno);
	txtCargo.val(""+beanTrabajador.cargo);
	$("#cboTipoDocumento").val(""+ConstanteServices.ID_TIPO_DOC_DNI);
	changeTipoDocu("#cboTipoDocumento", "#txtnumeroDoc", '');
	txtnumeroDoc.val(""+beanTrabajador.dni);
	txtnumeroDoc.prop( "disabled", true);
	txtCorreo.val(""+beanTrabajador.correo);
}

