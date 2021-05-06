$(document).ready(function() {			
	inicializarVariablesAdjuntos();
 	cargarComponentesAdjuntos();
});
var tblListaAdjuntos =null;	

function inicializarVariablesAdjuntos() {
	tblListaAdjuntos = $("#tblListaAdjuntos");		
}

function cargarComponentesAdjuntos(){
	agregarFileInput("fileInputAdjunto", "fileAdjunto","subirAdjunto");
	validarPerfilesAdjuntar();
	cargarDataGrillaAdjuntos();	
	console.log("adjuntos");
	buscarListaAdjuntos();
}	   

function validarPerfilesAdjuntar(){
	if(estadoAprobacionG == ConstanteServices.ID_FLAG_ESTADO_APROBADO ){
		$("#idDivAdjunto").hide();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_VALIDADOR ){
		$("#idDivAdjunto").show();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_REGISTRADOR){			
		$("#idDivAdjunto").show();
	}else if(flagAnalistaValidador == ConstanteServices.ROL_ADMINISTRADOR){
		$("#idDivAdjunto").hide();
	}else{		
		$("#idDivAdjunto").hide();
	}
}

function cargarDataGrillaAdjuntos (){  
	tblListaAdjuntos.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 5,
		exportOptions: {
             fileName: 'adjuntos'
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [
		{
			field : 'nro',
			title : 'Item',
			align : 'center',
			valign : 'middle',
			align : 'center',
			formatter: 'formatoNro',
			sortable : false
		},{
			field : 'nombreOriginal',
			title : 'Nombre de Archivo',
			valign : 'middle',
			align : 'center',
			sortable : false
		},{
			field : 'fechaRegistro',
			title : 'Fecha',
			valign : 'middle',
			align : 'center',
			sortable : false
		},{
			field : '',
			title : 'Accion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: crearAccionesAdjuntos,
			class: 'controls',
			events : operateEventsAdjuntos
		}]
	});
}	

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}


function crearAccionesAdjuntos(value, row, index) {
	return [
		'<button id="btnDescargar" class="control-table">',
			'<i class="fa fa-download"></i>',
		'</button>'
    ].join('');			
}    

function buscarListaAdjuntos(){
	$.ajax({
        url : "./buscarListaAdjuntos",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroLaboratorioAdj : (!IsEmpty(idRegistroLaboratorioAdjunto) ? idRegistroLaboratorioAdjunto : null),
        	idParametroAdj : (!IsEmpty(idParametroAdjunto) ? idParametroAdjunto : null)        	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		$("#tblListaAdjuntos").bootstrapTable('load', JSON.parse(ListaResultado));		            		
        	}else{
        		$("#tblListaAdjuntos").bootstrapTable('load', []);
        	}
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function subirAdjunto(){
	console.log("SubiendoAdjunto");
	var formData = new FormData();
	formData.append("idRegistroLaboratorioAdjunto", idRegistroLaboratorioAdjunto);           
    formData.append("idPtarSectorAdjunto", idPtarSectorAdjunto);   
    formData.append("idParametroAdjunto", idParametroAdjunto);
    formData.append("fileArchivo", document.getElementById('fileAdjunto').files[0]);
    
    $.ajax({
        url: "./accionRegistrarAdjunto",
        type: "POST",
        dataType: "json",
        processData: false,
        contentType: false,
        data:formData 
	}).done(function(respuestaBean) {
		mostrarMensaje(null, null, 'mensajeInformacionAdjunto');
		if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){
		    mostrarMensaje('Archivo adjunto Guardado Correctamente.', ConstanteServices.IMAGEN_SUCCESS, 'mensajeInformacionAdjunto');		            		
		    $("#closefileAdjunto").click();
		    buscarListaAdjuntos();
		} else {
	    	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER, 'mensajeInformacionAdjunto');
	    }
	}).fail(function(jqXHR, textStatus, errorThrown) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});
}
		