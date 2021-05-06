$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});

function inicializarVariables() {
	console.log("prueba modal");
	var divMensajeInformacion = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var tblResultadosAnalistas = null;
	var cboAnalista = null;
	var btnNuevoRegAnalista = null;
}

function cargarComponentes(){
	divMensajeInformacion = $('#divMensajeInformacion');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	tblResultadosAnalistas = $('#tblResultadosAnalistas');
	cboAnalista = $('#cboAnalista');
	btnNuevoRegAnalista = $('#btnNuevoRegAnalista');
	
	agregarListaItem(cboAnalista, ltaAnalistasNoVinculados, "idAnalista", "nombreCompleto");
	
	btnNuevoRegAnalista.click(function(event) {
		if(validarAgregarAnalista()){
			agregarRegistroAnalista();
			cargarCboAnalista();
    		cargaLtaAnalistasVinculados();
		}
	});
	
	cargarGrillaAnalistasVinculados(ltaAnalistasVinculados);
	
	if(modo == ConstanteServices.ESTADO_OPCION_VER) { 
		$('#sectorOculto').attr('hidden','hidden');
		tblResultadosAnalistas.bootstrapTable('hideColumn', 'accion');
	}
}

function cargarGrillaAnalistasVinculados(lista){
	tblResultadosAnalistas.bootstrapTable({
		data : lista,
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{
			field : 'nro',
			title : 'Item',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'formatoNro'
		},{	
			field : 'nombreCompleto',
			title : 'Analista.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false 
		},{	
			field : 'correo',
			title : 'Correo Electrónico',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false 
		},{
			field : 'accion',
			title : 'Acción',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesNotificacion',
			class: 'controls',
			events : operateEventsNotificacion
		}]
	});
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearAccionesNotificacion(value, row, index) {	
    return [			
		'<button id="btnAnularRegistroAnalista" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
			'<i class="fa fa-times"></i>',
		'</button>'
    ].join('');			    	
}

function agregarRegistroAnalista() {
	$.ajax({
		url : "./agregarRegistroAnalista",
		type : "POST",
		dataType : "json",
		async:false,
		data : {
			idNotificacion : (!IsEmpty(objNotificacion.idAlertasNotificacion) ? objNotificacion.idAlertasNotificacion : null),
			idAnalista : (!IsEmpty(cboAnalista.val()) ? cboAnalista.val() : null)
		}
	}).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){		
    		mostrarMensajeModal(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensajeModal(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function anularRegistroAnalista(idNotificacionAnalista) {
	$.ajax({
		url : "./anularRegistroAnalista",
		type : "POST",
		dataType : "json",
		async:false,
		data : {
			idNotificacionAnalista : (!IsEmpty(idNotificacionAnalista) ? idNotificacionAnalista : null)
		}
	}).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){		
    		mostrarMensajeModal(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);
        } else {
        	mostrarMensajeModal(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}


function cargarCboAnalista() {
	$.ajax({
		url : "./extraerLtaAnalistasNoVinculados",
		type : "POST",
		dataType : "json",
		async:false,
		data : {
			idNotificacion : (!IsEmpty(objNotificacion.idAlertasNotificacion) ? objNotificacion.idAlertasNotificacion : null)
		}
	}).done(function(respuestaBean) {
   	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){
    		var ListaResultado = respuestaBean.parametros.ListaResultadoAnalistasNoVinculados;
    		if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		console.log(lista);
        		cboAnalista.html("");
        		agregarListaItem(cboAnalista, lista, "idAnalista", "nombreCompleto");
        	} else {
        		cboAnalista.html("");
		        mostrarMensajeModal('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensajeModal(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
      }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function cargaLtaAnalistasVinculados() {
	$.ajax({
		url : "./extraerLtaAnalistasVinculados",
		type : "POST",
		dataType : "json",
		async:false,
		data : {
			idNotificacion : (!IsEmpty(objNotificacion.idAlertasNotificacion) ? objNotificacion.idAlertasNotificacion : null)
		}
	}).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){
    		var ListaResultado = respuestaBean.parametros.ListaResultadoAnalistasVinculados;
    		if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		console.log(lista);
        		tblResultadosAnalistas.bootstrapTable('load', JSON.parse(ListaResultado));
        	} else {
        		tblResultadosAnalistas.bootstrapTable('load', []);
		        mostrarMensajeModal('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
        	}
        } else {
        	mostrarMensajeModal(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}

function validarAgregarAnalista(){
    var estado = true;            
    mostrarMensaje();        
    if(IsEmpty(cboAnalista.val()))
    {
    	mostrarMensaje('El Analista es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    
    return estado;    
}
