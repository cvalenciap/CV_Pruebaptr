$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
});
var accion = false;
function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var idDetalleGeneral	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var divBotonesParametrosFisicoQuimico = null;
	var btnRegresar = null;
	console.log("fisicoQuimico");
}

function cargarComponentes() {
	divBotonesParametrosFisicoQuimico = $("#divBotonesParametrosFisicoQuimico");
	btnRegresar = $("#btnRegresar");
	
	cargarParametrosFisicoQuimico(ltaParametrosFisicoQuimico);
	
	if(!IsEmpty(idRegistroLaboratorio)){
		if(!IsEmpty(idParametro)){
			cargarPantallaProcesoFisicoQuimico(idParametro);
		}
	}
	if(IsEmpty(idParametroInicial)){
		cargarPantallaProcesoFisicoQuimico(ConstanteServices.ID_PARAMETRO_DBO);
	}
}

function cargarParametrosFisicoQuimico(listaPFQ){
	var htmlBotonesFQ = "";
	if(listaPFQ != null) {	
		var objetoDBO  = listaPFQ.find(objeto => objeto.idParametro === ConstanteServices.ID_PARAMETRO_DBO);
		if(!IsEmpty(objetoDBO)){
			htmlBotonesFQ += "<button type='button' id='btn_FQ_"+objetoDBO.idParametro+"' onclick='cargarPantallaProcesoFisicoQuimico("+objetoDBO.idParametro+");' class='btn-s btn-addBotoneraChild BotonesFisicoQuimico'>DBO</button>&nbsp;&nbsp;";		
		}		
		var objetoSolido  = listaPFQ.find(objeto => objeto.idParametro === ConstanteServices.ID_PARAMETRO_SOLIDO);
		var objetoAceite  = listaPFQ.find(objeto => objeto.idParametro === ConstanteServices.ID_PARAMETRO_ACEITE);
		if(!IsEmpty(objetoSolido)){
			htmlBotonesFQ += "<button type='button' id='btn_FQ_"+objetoSolido.idParametro+"' onclick='cargarPantallaProcesoFisicoQuimico("+objetoSolido.idParametro+");' class='btn-s btn-addBotoneraChild BotonesFisicoQuimico'>"+objetoSolido.descripcionParametro+"</button>&nbsp;&nbsp;";		
		}
		if(!IsEmpty(objetoAceite)){
			htmlBotonesFQ += "<button type='button' id='btn_FQ_"+objetoAceite.idParametro+"' onclick='cargarPantallaProcesoFisicoQuimico("+objetoAceite.idParametro+");' class='btn-s btn-addBotoneraChild BotonesFisicoQuimico'>"+objetoAceite.descripcionParametro+"</button>&nbsp;&nbsp;";		
		}		
	}
	$("#divBotonesParametrosFisicoQuimico").html(htmlBotonesFQ);
}

function cargarPantallaProcesoFisicoQuimico(identificador){
	$("#divMensajeInformacionFisicoQuimico").html("");
	var obj = {};
	obj.idPtarSector = idPtarSector;
	obj.idParametro = identificador;
	obj.idRegistroLaboratorio = !IsEmpty($("#idRegistroLaboratorio").val())? $("#idRegistroLaboratorio").val() : null;
	obj.descripcionPtar = descripcionPtar;
	obj.descripcionSector = descripcionSector;
		
	var divHijos = "divBotonesParametrosFisicoQuimico";
	var claseBuscar = "BotonesFisicoQuimico";
	if(obj.idParametro == ConstanteServices.ID_PARAMETRO_SOLIDO){
		//Validamos si Ya ah sido creado un DBO
		validacionDatosRegistroDBO(divHijos,claseBuscar,obj);
	}else if(obj.idParametro == ConstanteServices.ID_PARAMETRO_ACEITE){
		modificarEstilosBotonera(divHijos,claseBuscar,"btn_FQ_"+ConstanteServices.ID_PARAMETRO_ACEITE,ConstanteServices.COLOR_SELECT_FISICO_Q,ConstanteServices.TEXTO_SELECT_FISICO_Q,ConstanteServices.COLOR_NORMAL_FISICO_Q,ConstanteServices.TEXTO_NORMAL_FISICO_Q);
		cargarPantalla('./cargarVentanaRegistroAceite', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
	}else if(obj.idParametro == ConstanteServices.ID_PARAMETRO_DBO){
		modificarEstilosBotonera(divHijos,claseBuscar,"btn_FQ_"+ConstanteServices.ID_PARAMETRO_DBO,ConstanteServices.COLOR_SELECT_FISICO_Q,ConstanteServices.TEXTO_SELECT_FISICO_Q,ConstanteServices.COLOR_NORMAL_FISICO_Q,ConstanteServices.TEXTO_NORMAL_FISICO_Q);
		cargarPantalla('./cargarVentanaRegistroDBO', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
	}else{
		modificarEstilosBotonera(divHijos,claseBuscar,"btn_FQ_0",ConstanteServices.COLOR_SELECT_FISICO_Q,ConstanteServices.TEXTO_SELECT_FISICO_Q,ConstanteServices.COLOR_NORMAL_FISICO_Q,ConstanteServices.TEXTO_NORMAL_FISICO_Q);
		$("#idCargaNuevaPantallaFisicoQuimico").html("");
	}
}

function validacionDatosRegistroDBO(divHijos,claseBuscar,obj){
	$.ajax({
        url : "./buscarDatosRegistroDBO",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idRegistroLabBusqueda : (!IsEmpty($("#idRegistroLaboratorio").val()) ? $("#idRegistroLaboratorio").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		      
        		modificarEstilosBotonera(divHijos,claseBuscar,"btn_FQ_"+ConstanteServices.ID_PARAMETRO_SOLIDO,ConstanteServices.COLOR_SELECT_FISICO_Q,ConstanteServices.TEXTO_SELECT_FISICO_Q,ConstanteServices.COLOR_NORMAL_FISICO_Q,ConstanteServices.TEXTO_NORMAL_FISICO_Q);
        		cargarPantalla('./cargarVentanaRegistroSolido', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
        	}else{
        		modificarEstilosBotonera(divHijos,claseBuscar,"btn_FQ_"+ConstanteServices.ID_PARAMETRO_SOLIDO,ConstanteServices.COLOR_SELECT_FISICO_Q,ConstanteServices.TEXTO_SELECT_FISICO_Q,ConstanteServices.COLOR_NORMAL_FISICO_Q,ConstanteServices.TEXTO_NORMAL_FISICO_Q);
        		modal.confirmer('No se ha ingresado información del parámetro DBO ¿Desea continuar?', 'puenteToSolidos('+JSON.stringify(obj)+')', '', 'Ausencia DBO');
//        		mostrarMensaje(ConstanteServices.MENSAJE_DBO_SOLIDO, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionFisicoQuimico');
        	}
        } else {
        	mostrarMensaje(ConstanteServices.MENSAJE_DBO_SOLIDO, ConstanteServices.IMAGEN_DANGER,'divMensajeInformacionFisicoQuimico');
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function puenteToSolidos(obj){
//	obj = JSON.parse(obj);
	cargarPantalla('./cargarVentanaRegistroSolido', obj, $('#idCargaNuevaPantallaFisicoQuimico'));
}

window.operateEventsAceite = {
		'click #btnEditarRegistroAceite': function (e, value, row, index) {
			verificarParametroEditarNuevoAceite(index,row);
		},
		'click #btnRegresarRegistroAceite': function (e, value, row, index) {	
			flagAceite = 0;
			buscarDatosRegistroAceite();
			buscarFormulasPrincipalesAceite();
			visibleAnalistaAceite();
		},
		'click #btnAnularRegistroAceite': function (e, value, row, index) {			
			var indicadorAnulacion = false;			
			if(flagAceite == 1){
				var idSubPCombo = $("#divSubParametro_"+index).val();  
				if(idSubPCombo != undefined){
					indicadorAnulacion = true;
				}
			}else{
			   indicadorAnulacion = true;
			}			
			if(indicadorAnulacion){
				var html = 
					'<div class="row">' + 
						'<div class="col-sm-12">' + 
							'<label class="control-label">Se Eliminara el Registro. ¿Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Eliminación de Registro N°<font><b>' + (index + 1) + '</b></font>';
				if(IsEmpty(row.idRegAceite)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoAceite('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroAceite('+row.idRegAceite+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroAceite': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroAceite.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.idSubParametro = idSub;
				objetoModificar.descripcionSubparametro = textoSub;
				objetoModificar.idPuntoMuestra = idPunto;
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				if(validarNuevoRegistroNuevoAceite(objetoModificar)){
					guardarTablaAntesCambiosAceite();
					tblResultadoRegistroAceite.bootstrapTable('updateRow', {index: index, row: objetoModificar});
					formatearInputAceite();
					flagAceite = 0;
					visibleAnalistaAceite();
				}				
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				if(validarNuevoRegistroEditarAceite(index)){
					var idAnalistaNew = $("#cboAnalista_"+index).val();   
					var fechaMonitoreoNew = $("#dtpMonitoreo_"+index).val();
					var fechaRegDatoNew = $("#dtpRegDato_"+index).val();
					var idSubPNew = $("#divSubParametro_"+index).val();
					var textoSubNew = extraerTextoCombo("divSubParametro_"+index);
					var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
					var numFrascoNew = $("#divFrasco_"+index).val();
					var numVolumenNew = $("#divVolumen_"+index).val();
					var numPesoInicialNew = $("#divPesoInicial_"+index).val();
					var numPesoFinalNew = $("#divPesoFinal_"+index).val();
					var numResultadoNew = $("#divResultado_"+index).val();
					var descripcionObservacionNew = $("#divDescripcionObservacion_"+index).val();
					
					row.idRegLaboratorio = $("#idRegistroLaboratorio").val();
					row.idAnalista = idAnalistaNew;
					row.fechaMonitoreoString = fechaMonitoreoNew;
					row.fechaRegDatoString = fechaRegDatoNew;
					row.idSubParametro = idSubPNew;
					row.idPuntoMuestra = idPuntoMuestraNew;
					row.numFrasco = numFrascoNew;
					row.numVolumen = numVolumenNew;
					row.numPesoInicial = numPesoInicialNew;
					row.numPesoFinal = numPesoFinalNew;
					row.numResultado = numResultadoNew;
					row.descripcionObservacion = descripcionObservacionNew;
					row.idPtarxSector = idPtarSector;
					var itemFormulaResultado = JSON.parse(txtFormulaAceite1.attr("valor"));
					row.idFormulaResultado  = itemFormulaResultado.idFormulaxParametro;
					if(validarNuevoRegistroNuevoAceite(row)){
						modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroAceite('+JSON.stringify(row)+')', '', tituloModal);
						var tituloModal = 'Registro Aceite';
					}					
				}
			}
		}
}