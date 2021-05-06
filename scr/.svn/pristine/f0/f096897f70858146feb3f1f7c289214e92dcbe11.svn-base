$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});
var accion = false;
function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevo = null;
	var tblResultados = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarxSector = null;
	var dtpFechaInicio = null;
	var dtpFechaFin = null;
	var cboFechas = null;
	var btnCopiar = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevo = $('#btnNuevo');
	tblResultados = $('#tblResultados');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	cboPtarxSector = $("#cboPtarxSector");
	cboFechas = $("#cboFechas");
	btnCopiar = $("#btnCopiar");
		
	btnNuevo.click(function(event) {  
		if(validarFormularioBusqueda()){
			var ltaFechas = cboFechas.val().split("_");
			$.redirect('./cargarVentanaFisicoQuimico', {mantenimientoBean:'', modo: ConstanteServices.ESTADO_OPCION_NUEVA ,filtro:obtenerFiltros(), idPtarxSector : cboPtarxSector.val(),descripcionPtar : extraerTextoCombo("cboPtarxSector"),fechaInicio : ltaFechas[0] ,fechaFin : ltaFechas[1] });
		}
    });
	
	agregarListaItem(cboPtarxSector, ltaPtarxSector, "idPtarxsector", "descripcionPtarxSector");
	
	cboPtarxSector.change(function(event){
		buscarFechasFisicoQuimico();
    });
	
	btnCopiar.click(function(event) {
		redireccionModal('./cargarVentanaVerModalCopiar', {}, $('#verModalCopiar'));
	});
	
	cboFechas.change(function(event){
		buscarFisicoQuimico();
    });
	cargarGrillaResultado();
	buscarFechasFisicoQuimico();
}

function validarFormularioPtarBusqueda(){
	mostrarMensaje();
    var estado = true;
    if(IsEmpty(cboPtarxSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
         return estado;
    }
    return estado;
}

function buscarFechasFisicoQuimico() {
	if(validarFormularioPtarBusqueda()) {
		$.ajax({
	        url : "./buscarFechasFisicoQuimico",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		var lista = JSON.parse(ListaResultado);
	        		cboFechas.html("");
	        	    $.each(lista, function(i, item) {
	        	    	cboFechas.append($("<option/>").attr("value",item.fechaInicio+"_"+item.fechaFin).text(item.periodo)); 
	        	    });  	
	        	    buscarFisicoQuimico();
	        	} else {
	        		cboFechas.html("");	       
	        		tblResultados.bootstrapTable('load', []);
	        	}
	        } else {
	        	cboFechas.html("");
	        	tblResultados.bootstrapTable('load', []);
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	}else{
		tblResultados.bootstrapTable('load', []);
	} 	
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : true,
		pageSize : 10,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Fisico Quimico',
             ignoreColumn: ['accion']
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{
			field : 'accion',
			title : 'Acción',
			align : 'center',					
			valign : 'bottom',
			sortable : false,
			formatter: 'crearAccionesMantenimientoFisicoQuimico',
			class: 'controls',
			events : operateEvents,
			cellStyle : estiloAcciones
		},{
			field : 'nro',
			title : 'Nro',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			formatter: 'formatoNro',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'descripcionPunto',
			title : 'Puntos de Muestreo.',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'descripcionFisico',
			title : 'Tipo Fisico',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'nuDilc',
			title : '% Dilc',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{	
			field : 'nNumVolumen',
			title : 'Volumen mL',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'nNumOdi',
			title : 'ODi mg/L',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'nNumOdf',
			title : 'ODf mg/L',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		},{	
			field : 'nuDeplec',
			title : '% Deplec',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{	
			field : 'nNumDbo5',
			title : 'DBO5 mg/L',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{	
			field : 'nNumDbo5Prom',
			title : 'DBO5 Promedio',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloColor
		},{
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			cellStyle : verificarEstiloDeplec
		}]
	});
}

function verificarEstiloDeplec(value, row, index) {	
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {
		if(ConstanteServices.RANGO_DEPLEC_MINIMO <= eval(row.nuDeplec) && eval(row.nuDeplec) <= ConstanteServices.RANGO_DEPLEC_MAXIMO){
			return {
			    css: {
			      'color' : ConstanteServices.COLOR_DEPLEC,
			      'font-weight': ConstanteServices.ESTILO_STRONG
			    }
			};
		}else{
			return {};
		}		
	}else{		
		return {
		    css: {
		      'color' : 'red'
		    }
		};
	}
}

function verificarEstiloColor(value, row, index) {	
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {
		if(ConstanteServices.RANGO_DEPLEC_MINIMO <= eval(row.nuDeplec) && eval(row.nuDeplec) <= ConstanteServices.RANGO_DEPLEC_MAXIMO){
			return {
			    css: {
			      'color' : ConstanteServices.COLOR_DEPLEC,
			      'font-weight': ConstanteServices.ESTILO_STRONG,
			      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			    }
			};
		}else{
			return {
			    css: {
			      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
			    }
			};
		}		
	}else{		
		return {
		    css: {
		      'color' : 'red',
		      'background' : ConstanteServices.COLOR_COLUMNA_CALCULO
		    }
		};
	}
}


	
function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			var descripcionPtar = row.descripcionPtar+" - "+row.descripcionSector;
			$.redirect('./cargarVentanaFisicoQuimico', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_EDITAR ,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector,descripcionPtar : descripcionPtar ,fechaInicio : row.fechaInicioString ,fechaFin : row.fechaFinString });
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Fisico Quimico N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularFisicoQuimico(' + row.idFisicoQuimico +','+row.nNumVolumen+','+row.idPuntoMuestra+','+row.idPtarxSector+',"'+row.fechaInicioString+'","'+row.fechaFinString+'")' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnVerMantenimiento': function (e, value, row, index) {
			if(row != null) {
				var descripcionPtar = row.descripcionPtar+" - "+row.descripcionSector;
				$.redirect('./cargarVentanaFisicoQuimico', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER,filtro:obtenerFiltros(), idPtarxSector : row.idPtarxSector,descripcionPtar : descripcionPtar ,fechaInicio : row.fechaInicioString ,fechaFin : row.fechaFinString });
			}
		},
		'click #btnActivarMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se Activará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Activaci&oacute;n de Fisico Quimico N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularFisicoQuimico(' + row.idFisicoQuimico +','+row.nNumVolumen+','+row.idPuntoMuestra+','+row.idPtarxSector+',"'+row.fechaInicioString+'","'+row.fechaFinString+'")' , '', titulo, 'Aceptar', 'Cancelar');
		},
	}

function crearAccionesMantenimientoFisicoQuimico(value, row, index) {			
	if(row != null && row.stRegi == ConstanteServices.ESTADO_ACTIVO) {			
		return [				
			'<button id="btnEditarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>',
			'<button id="btnVerMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Visualizar">',
				'<i class="fa fa-search"></i>',
			'</button>', 
			'<button id="btnAnularMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>'
        ].join('');	
    } else {
    	return ['<button id="btnActivarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Activar">',
			'<i class="fa fa-check"></i>',
		    '</button>'].join('');
    }		
}

function validarFormularioBusqueda(){
	mostrarMensaje();
    var estado = true;
    if(IsEmpty(cboPtarxSector.val())){           
       	 mostrarMensaje('Debe Seleccionar un Ptar.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
         return estado;
    }
    if(IsEmpty(cboFechas.val())){
    	mostrarMensaje('Debe Seleccionar un periodo.', ConstanteServices.IMAGEN_DANGER); 
	    return false;
    }
    return estado;
}

function buscarFisicoQuimico() {
	if(validarFormularioBusqueda()) {
		var ltaFechas = cboFechas.val().split("_");
		$.ajax({
	        url : "./buscarFisicoQuimico",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idPtarxSector: (!IsEmpty(cboPtarxSector.val()) ? cboPtarxSector.val() : null),
	        	fechaInicio :  (!IsEmpty(ltaFechas[0]) ? ltaFechas[0] : null),
	        	fechaFin :  (!IsEmpty(ltaFechas[1]) ? ltaFechas[1] : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		tblResultados.bootstrapTable('load', JSON.parse(ListaResultado));		            		
	        	} else {
	        		tblResultados.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	tblResultados.bootstrapTable('load', []);
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	}else{
		tblResultados.bootstrapTable('load', []);
	} 	
}

function obtenerFiltros(){
    var formFiltro = {		    
        'cboPtarxSector': cboPtarxSector.val(),
        'cboFechas' : cboFechas.val()
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboPtarxSector").val(""+filtroObjecto.cboPtarxSector);
		buscarFechasFisicoQuimico();
		if(!IsEmpty(filtroObjecto.cboFechas)){
			$("#cboFechas").val(""+filtroObjecto.cboFechas);
			buscarFisicoQuimico();
		}
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function anularFisicoQuimico(idFisicoQuimico,volumen,idPuntoMuestra,idPtarxSector,fechaInicio,fechaFin) {
	if(!IsEmpty(idFisicoQuimico)) {
		$.ajax({
            url : "./anularFisicoQuimico",
            type : "POST",
            dataType : "json",
            data : {
            	idFisicoQuimico : idFisicoQuimico ,
            	volumen : volumen ,
            	idPuntoMuestra : idPuntoMuestra ,
            	idPtarxSector : idPtarxSector ,
            	fechaInicio : fechaInicio , 
            	fechaFin : fechaFin 
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarFisicoQuimico();		
        		mostrarMensaje(respuestaBean.mensajeRespuesta , ConstanteServices.IMAGEN_SUCCESS);	            		            	
            } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });
    } else {
    	mostrarMensaje('Debe Seleccionar un Registro a Anular.', ConstanteServices.IMAGEN_DANGER);
    }
}
