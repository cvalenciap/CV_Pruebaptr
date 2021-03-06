$(document).ready(function() {
	inicializarVariables();
		cargarComponentes();
});
var numPromedioSCFEntrada = 0;
var numPromedioSCFSalida = 0;
var accion = false;
var flagDBO = 0;
var ltaPuntoMuestra = [];
var ltaSubParametro = [];
var ltaPtoMuestraFalta = null;
var ltaRegistrosPlantillaSobra = null;
function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var btnGrabar 	= null;
	var btnRegresar	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboPtarPorSector = null;
	var tblResultadoRegistroDBO = null;
	var btnNuevoRegDBO = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnGrabar = $('#btnGrabar');
	btnCancelar = $('#btnCancelar');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');
	cboPtarPorSector = $("#cboPtarPorSector");
	tblResultadoRegistroDBO = $("#tblResultadoRegistroDBO");
	btnNuevoRegDBO = $("#btnNuevoRegDBO");
	cboPtarPorSector.html("");
    $.each(ltaPtarPorSector, function(i, item) {
    	cboPtarPorSector.append($("<option/>").attr("value",ltaPtarPorSector[i].idPtarxsector).text(""+ltaPtarPorSector[i].descripcionPtar)); 
    }); 
    cboPtarPorSector.change(function(event){
    	mostrarMensaje();
    	buscarPlantillaDBO();
    	verificarElementosAlterados();
    });    
    
    btnNuevoRegDBO.click(function(event){
    	mostrarMensaje();
    	verificarParametroAgregarDBO();
    }); 
    cargarGrillaResultadoDBO();
    buscarPlantillaDBO();
    verificarElementosAlterados();
    formatearInputDBO();
}

function formatearInputDBO(){
	ponerClassConjunto("inputNumerico","numValorSemilla",3,0,0);
	ponerClassConjunto("inputDecimal","numVolumen",0,5,2);
}

function cargarGrillaResultadoDBO(){
	tblResultadoRegistroDBO.bootstrapTable({
		data : [],
		buttonsAlign : 'right',
		toolbarAlign : 'right',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{
			field : 'accion',
			title : 'Acci??n',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter: 'crearAccionesRegistroDBO',
			class: 'controls',
			events : operateEventsDBO,
			cellStyle : estiloAcciones
		},{	
			field : 'descripcionPuntoMuestra',
			title : 'Punto de<br>Muestra.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false,
			formatter : 'crearDescripcionPuntoMuestra'
		},{	
			field : 'descripcionSubparametro',
			title : 'Sub Par??metro.',
			align : '-webkit-center',
			valign : 'middle',
			formatter : 'crearDescripcionSubParametro'
		},{	
			field : 'numVolumen',
			title : 'Volumen<br>(a)',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlVolumen'
		},{
			field : 'numValorSemilla',
			title : 'Semilla.',
			align : '-webkit-center',
			valign : 'middle',
			sortable : false ,
			formatter: 'crearHtmlSemilla'
		}]
	});
}

function crearDescripcionPuntoMuestra(value,row,index){
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.descripcionPuntoMuestra].join('');
		}else{
			return [''].join('');
		}
}


function crearHtmlVolumen(value,row,index){
	if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		return [''+row.numVolumen].join('');
	}else{
		return [''].join('');
	}
}

function crearDescripcionSubParametro(value,row,index){
		if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
			return [''+row.descripcionSubparametro].join('');
		}else{
			return [''].join('');
		}
}

function crearHtmlComboSubParametroNuevo(ltaSubPara,index,value){
		var htmlComboSubParametro = '<select class="form-control input-sm subParametro" onchange = "verPuntoMuestra('+index+')" id="divSubParametro_'+index+'" >';
		for (var i = 0; i < ltaSubPara.length; i++) {
			if(ltaSubPara[i].idSubParametro == value){
				htmlComboSubParametro += '<option  selected="selected" value = "'+ltaSubPara[i].idSubParametro+'">'+ltaSubPara[i].descripcionCorta+'</option>';
			}else{
				htmlComboSubParametro += '<option value = "'+ltaSubPara[i].idSubParametro+'">'+ltaSubPara[i].descripcionCorta+'</option>';
			}
		}
		htmlComboSubParametro 	  +='</select>';		
		return [htmlComboSubParametro].join('');
}

function verPuntoMuestra(index){
	var idSubParametroAux = $("#divSubParametro_"+index).val();
	var descripcionPuntoMuestra = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(1);
	descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametroAux),index,null));
}

function crearHtmlComboPuntoMuestraNuevo(ltaPM,index,value){
	var htmlCombo = '<select class="form-control input-sm" id="cboPuntoMuestra_'+index+'" onchange="validacionIndicadorSemilla('+index+')" >';
	for (var i = 0; i < ltaPM.length; i++) {
		if(ltaPM[i].idPuntoMuestra == value){
			htmlCombo += '<option selected="selected" value = "'+ltaPM[i].idPuntoMuestra+'">'+ltaPM[i].descripcionPunto+'</option>';
		}else{
			htmlCombo += '<option value = "'+ltaPM[i].idPuntoMuestra+'">'+ltaPM[i].descripcionPunto+'</option>';
		}	   
	}
	htmlCombo 	  +='</select>';		
	return htmlCombo;
}

function crearHtmlVolumenNuevo(index,value){
	var tamannio = 55;
	if(IsEmpty(value)){
		return '<input type="text"  id="divnumVolumen_'+index+'" value="0" class="form-control input-sm numVolumen"  style="width: '+tamannio+'px !important;" />';
	}else{
		return '<input type="text"  id="divnumVolumen_'+index+'" value = "'+value+'" class="form-control input-sm numVolumen" style="width: '+tamannio+'px !important;" />';
	}
}

function verificarParametroAgregarDBO(){
	mostrarMensaje();
	if(ltaPuntoMuestra == null || ltaPuntoMuestra.length <= 0){
		mostrarMensaje('La Planta de Tratamiento no posee Punto de Muestra.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}
	if(ltaSubParametro == null || ltaSubParametro.length <= 0){
		mostrarMensaje('La Planta de Tratamiento no posee Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}	
	if(flagDBO == 0){
		var ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
		var countRow = ltaGrilla.length;
		var objetoClon = {};
		objetoClon.indice = countRow;
		objetoClon.descripcionSubparametro = crearHtmlComboSubParametroNuevo(ltaSubParametro,countRow,null);
		var idSubParametroInicial = ltaSubParametro[0].idSubParametro;		
		objetoClon.descripcionPuntoMuestra = crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(idSubParametroInicial),countRow,null);
		objetoClon.indicePadre = ConstanteServices.INDICADOR_PADRE;
		if(ltaGrilla != null && ltaGrilla.length > 0){
			objetoClon.idGrupo = obtenerValorMaximoGrupo(ltaGrilla)+1;
		}else{
			objetoClon.idGrupo = 1;	
		}			
		objetoClon.numVolumen  = crearHtmlVolumenNuevo(countRow,null);
		objetoClon.idPlantillaDBO = null;
		objetoClon.numOdi = 0;
		objetoClon.numOdf = 0;
		objetoClon.numFactor = 0;
		objetoClon.numDeplec = 0;
		objetoClon.numDilc = 0;
		objetoClon.numDbo5 = 0;
		var objetoClonHijo = clonarObjeto(objetoClon);
		objetoClonHijo.indicePadre = ConstanteServices.INDICADOR_HIJO;
		objetoClonHijo.indice = countRow + 1;	
		objetoClonHijo.idPlantillaDBO = null;
		objetoClonHijo.numVolumen  = crearHtmlVolumenNuevo(countRow+1,null);
		objetoClonHijo.numOdi = 0;
		objetoClonHijo.numOdf = 0;
		objetoClonHijo.numFactor = 0;
		objetoClonHijo.numDeplec = 0
		objetoClonHijo.numDilc = 0;
		objetoClonHijo.numDbo5 = 0;
		
		tblResultadoRegistroDBO.bootstrapTable('insertRow', {
            index: countRow,
            row: objetoClon
        });
		tblResultadoRegistroDBO.bootstrapTable('insertRow', {
            index: countRow+1,
            row: objetoClonHijo
        });
		var accionGuardar = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(0).find('#btnGuardarRegistroDBO');
        var accionEditar = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(0).find('#btnEditarRegistroDBO');
        var accionAnular = tblResultadoRegistroDBO.find('tr').eq(countRow+1).find('td').eq(0).find('#btnAnularRegistroDBO');
        
        accionGuardar[0].style.display = 'inherit';
        accionEditar[0].style.display = 'none';
        accionAnular[0].style.display = 'inherit';
        validacionIndicadorSemilla(countRow);
        flagDBO = 1;	
        formatearInputDBO();
    }
}

function validacionIndicadorSemilla(index, ptoMuestraActual){
	if(!IsEmpty(ptoMuestraActual)){
		var idPuntoMuestra = ptoMuestraActual;
	}else{
		var idPuntoMuestra = $("#cboPuntoMuestra_"+index).val();
	}
	var htmlRow = tblResultadoRegistroDBO.find('tr').eq((index+1)).find('td').eq(4);
	var htmlRowHija = tblResultadoRegistroDBO.find('tr').eq((index+2)).find('td').eq(4);
	for(var i = 0 ; i < ltaPuntoMuestra.length ; i ++){
		if(ltaPuntoMuestra[i].idPuntoMuestra == idPuntoMuestra){
			var objeto = clonarObjeto(ltaPuntoMuestra[i]);
			if(objeto.indicadorSemilla != null && objeto.indicadorSemilla == ConstanteServices.INDICADOR_SEMILLA) {
				var semilla = htmlRow[0].textContent;
				var semillaHija = htmlRowHija[0].textContent;
				var htmlimput = '<input type="text" id="divnumValorSemilla_'+index+'" value="'+semilla+'"  class="form-control input-sm numValorSemilla" style="width: 55px !important;" />';
				var htmlimputHija = '<input type="text" id="divnumValorSemilla_'+parseInt(index+1)+'" value="'+semillaHija+'"  class="form-control input-sm numValorSemilla" style="width: 55px !important;" />';
				htmlRow.html(htmlimput);
				htmlRowHija.html(htmlimputHija);
			}else{
				htmlRow.html("");
				htmlRowHija.html("");
			}
			break;
		}
	}	
	formatearInputDBO();
}

function crearHtmlSemilla(value,row,index){
	if(row.numValorSemilla != null && row.numValorSemilla != 0){
		return [''+row.numValorSemilla].join('');
	}else{
		return [''].join('');
	}
}
function buscarPlantillaDBO(){
	$.ajax({
        url : "./buscarPlantillaDBO",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarSector : (!IsEmpty($("#cboPtarPorSector").val()) ? $("#cboPtarPorSector").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
    		var ListaResultado = respuestaBean.parametros.ListaResultado; 
    		var ListaPuntoMuestra = respuestaBean.parametros.ListaPuntoMuestra; 
    		var ListaSubParametro = respuestaBean.parametros.ListaSubParametro; 
    		ltaPuntoMuestra = JSON.parse(ListaPuntoMuestra);
    		ltaSubParametro = JSON.parse(ListaSubParametro);
        	if(ListaResultado != null && ListaResultado != "[]") {		            		
        		tblResultadoRegistroDBO.bootstrapTable('load', JSON.parse(ListaResultado));	
        	}else{
        		tblResultadoRegistroDBO.bootstrapTable('load', []);
        	}
        	formatearInputDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function crearAccionesRegistroDBO(value, row, index) {	
	if(row.indicePadre == ConstanteServices.INDICADOR_PADRE){
		
		return [
			'<button id="btnGuardarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar"  style="display:none;" >',
			'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar" >',
			'<i class="fa fa-pencil-square-o"></i>',
			'</button>',			
			'<button id="btnAnularRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Eliminar">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarRegistroDBO" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
			'<i class="fa fa-reply"></i>',
			'</button>'
	    ].join('');	
		
	}else{
		return [''].join('');
	}    	
}

function eliminarSubParametroNuevoDBO(idIndice){	
	tblResultadoRegistroDBO.bootstrapTable('remove', {field: 'indice', values: [parseInt(idIndice)]});
	tblResultadoRegistroDBO.bootstrapTable('remove', {field: 'indice', values: [parseInt(idIndice)+1]});
	flagDBO = 0;
	mostrarMensaje();
}
function eliminarItemRegistroDBO(idGrupo,idPtarxSectorInterno){
	$.ajax({
        url : "./eliminarItemPlantillaDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	idGrupoRegistro : idGrupo,
        	idPtarxSector : idPtarxSectorInterno
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarPlantillaDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputDBO();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function buscarIndicadorSemilla(idPuntoMuestra){
	var indica = null;
	for(var i = 0 ; i < ltaPuntoMuestra.length ; i ++){
		if(parseInt(ltaPuntoMuestra[i].idPuntoMuestra) == parseInt(idPuntoMuestra)){
			indica = ltaPuntoMuestra[i].indicadorSemilla;
			break;
		}
	}
	return indica;
}

window.operateEventsDBO = {
		'click #btnEditarRegistroDBO': function (e, value, row, index) {
				verificarParametroEditarNuevoDBO(index,row)
		},
		'click #btnRegresarRegistroDBO': function (e, value, row, index) {	
			flagDBO = 0;
			buscarPlantillaDBO();
		},
		'click #btnAnularRegistroDBO': function (e, value, row, index) {
			var indicadorAnulacion = false;			
			if(flagDBO == 1){
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
							'<label class="control-label">Se Eliminara el Registro. ??Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Eliminaci??n de Registro N??<font><b>' + (index + 1) + '</b></font>';
				if(IsEmpty(row.idPlantillaDBO)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoDBO('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					var idPtarxSector = cboPtarPorSector.val();
					modal.defaultModal(html, 'eliminarItemRegistroDBO('+row.idGrupo+','+idPtarxSector+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroDBO': function (e, value, row, index) {
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var ltaActual = tblResultadoRegistroDBO.bootstrapTable("getData");		
				row.idPuntoMuestra = (idPunto != null)? parseInt(idPunto) : idPunto;
				row.descripcionPuntoMuestra = textoPunto;
				row.idSubParametro = (idSub != null)? parseInt(idSub) : idSub; //parseInt(idSub);
				row.descripcionSubparametro = textoSub;
				row.idPtarxSector = cboPtarPorSector.val();
				var valorSemillaNew = $("#divnumValorSemilla_"+index).val();
				var indicad = buscarIndicadorSemilla(row.idPuntoMuestra);
				if(indicad == ConstanteServices.INDICADOR_SEMILLA){	
					row.numValorSemilla = valorSemillaNew;
					row.indicadorSemilla = parseInt(ConstanteServices.INDICADOR_SEMILLA);
				}else{
					row.numValorSemilla = 0;
					row.indicadorSemilla = 0;
				}
				row.numVolumen = $("#divnumVolumen_"+index).val();
				row.numOdi = 0;
				row.numOdf = 0;
				row.numFactor = 0;
				row.numDeplec = 0;
				row.numDilc = 0;
				row.numDbo5 = 0;
				
				//HIJA
				var rowHija = clonarObjeto(row);	
				rowHija.idPlantillaDBO = ltaActual[index+1].idPlantillaDBO;
				var valorSemillaNew = $("#divnumValorSemilla_"+(index+1)).val();
				var indicad = buscarIndicadorSemilla(row.idPuntoMuestra);
				if(indicad == ConstanteServices.INDICADOR_SEMILLA){	
					rowHija.numValorSemilla = valorSemillaNew;
					rowHija.indicadorSemilla = parseInt(ConstanteServices.INDICADOR_SEMILLA);
				}else{
					rowHija.numValorSemilla = 0;
					rowHija.indicadorSemilla = 0;
				}
				rowHija.numOdi = 0;
				rowHija.numOdf = 0;
				rowHija.numDeplec = 0;
				rowHija.numDilc = 0;
				rowHija.numDbo5 = 0;
				rowHija.indicePadre = ConstanteServices.INDICADOR_HIJO;				
				ltaActual[index+1] = rowHija;
				if(validarNuevoRegistroEditarDBO(index, ConstanteServices.INDICADOR_PADRE, row.idPuntoMuestra) && validarNuevoRegistroEditarDBO((index+1), ConstanteServices.INDICADOR_HIJO, rowHija.idPuntoMuestra)){
					var tituloModal = 'Plantilla DBO';
					modal.confirmer('??Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroDBO('+JSON.stringify(row)+','+JSON.stringify(rowHija)+')', '', tituloModal);
				}
		}
	}

function grabarItemRegistroDBO(rowPapa,rowHija){
	$.ajax({
        url : "./grabarItemPlantillaDBO",
        type : "POST",
        dataType : "json",
        async:true,
        data : {
        	itemPapa : JSON.stringify(rowPapa),
        	itemHijo : JSON.stringify(rowHija)
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarPlantillaDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    	formatearInputDBO();
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function validarNuevoRegistroEditarDBO(index, indicadorPadre, idPuntoMuestraNew){
	var indicador = true;	
	
	if(IsEmpty($("#cboPuntoMuestra_"+index).val()) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Punto Muestra.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	if(IsEmpty($("#divSubParametro_"+index).val()) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Seleccionar un Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return false;
	}
	
	var numValorSemillaNew = $("#divnumValorSemilla_"+index).val();	
	var indicad = buscarIndicadorSemilla(idPuntoMuestraNew);
	if(indicad == ConstanteServices.INDICADOR_SEMILLA){	
		if(IsEmpty(numValorSemillaNew)){
			mostrarMensaje('Debe Ingresar un Valor Semilla.', ConstanteServices.IMAGEN_DANGER);
			indicador = false;
			return indicador;
		}
	}	
	
	var numVolumenNew = $("#divnumVolumen_"+index).val();
	if(IsEmpty(numVolumenNew) && indicadorPadre == ConstanteServices.INDICADOR_PADRE){
		mostrarMensaje('Debe Ingresar Volumen.', ConstanteServices.IMAGEN_DANGER);
		indicador = false;
		return indicador;
	}
	
	return indicador;
}

function verificarParametroEditarNuevoDBO(index,row){
	mostrarMensaje();
	if(ltaPuntoMuestra == null || ltaPuntoMuestra.length <= 0){
		mostrarMensaje('La Planta de Tratamiento no posee Punto de Muestra.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}
	if(ltaSubParametro == null || ltaSubParametro.length <= 0){
		mostrarMensaje('La Planta de Tratamiento no posee Sub Par??metro.', ConstanteServices.IMAGEN_DANGER);
		return "";
	}	
	if(flagDBO == 0){
		var ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
		var objetoClon = clonarObjeto(row);
		var accionGuardar = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(0).find('#btnGuardarRegistroDBO');
        var accionEditar = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(0).find('#btnEditarRegistroDBO');
        var accionAnular = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(0).find('#btnAnularRegistroDBO');
        var accionRegresar = tblResultadoRegistroDBO.find('tr').eq(index + 1).find('td').eq(0).find('#btnRegresarRegistroDBO');
        		
		var descripcionPuntoMuestra = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(1);
        var descripcionSubparametro = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(2);
		var numVolumen  = tblResultadoRegistroDBO.find('tr').eq(index+1).find('td').eq(3);
        		        
        accionGuardar[0].style.display = 'inherit';
        accionEditar[0].style.display = 'none';
        accionAnular[0].style.display = 'inherit';
        
        descripcionPuntoMuestra.html(""+crearHtmlComboPuntoMuestraNuevo(buscarListaPuntoMuestraSubParametro(row.idSubParametro),index,row.idPuntoMuestra));	
        descripcionSubparametro.html(""+crearHtmlComboSubParametroNuevo(ltaSubParametro,index,row.idSubParametro));
        //HIJA
		var objetoClonHija = clonarObjeto(ltaGrilla[index+1]);
    	numVolumen.html(""+crearHtmlVolumenNuevo(index,""+row.numVolumen));
        validacionIndicadorSemilla(index, row.idPuntoMuestra);			
		accionRegresar[0].style.display = 'inherit';		
		//HIJA		
        formatearInputDBO();
        flagDBO = 1;
    }
	formatearInputDBO();
}

function buscarListaPuntoMuestraSubParametro(idSubParametro){
	var listaAuxiliar = [];
	for(var i = 0 ; i< ltaPuntoMuestra.length ; i++){
		if(idSubParametro == ltaPuntoMuestra[i].idSubParametro){
			listaAuxiliar.push(ltaPuntoMuestra[i]);
		}
	}
	return listaAuxiliar;	
}

function verificarElementosAlterados(){
	encontrarRegistrosPlantillaSobra();
	encontrarPtoMuestraFalta();
	if(!IsEmpty(ltaRegistrosPlantillaSobra) || !IsEmpty(ltaPtoMuestraFalta)){
		visualizarModalElementosAlterados();
	}
}

function visualizarModalElementosAlterados(){
		var html = 
		'<div class="row">' + 
			'<div class="col-sm-12">' + 
				'<label class="control-label">Se encontraron Inconsistencias en los Puntos de Muestra asociados a los Registros en la PTAR ??Desea Modificar los registros?</label>' + 
			'</div>' + 
		'</div>';
		var titulo = 'Puntos de Muestra Asociados Distintos';
		modal.defaultModal(html, 'modificarAlterados()' , '', titulo, 'Aceptar', 'Cancelar');
}

function agregarFaltantes(){
	var ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
	var countRow = ltaGrilla.length;
	ltaAgregados = [];
	for(var i=0; i<ltaPtoMuestraFalta.length; i++){
		var objetoClon = {};
		objetoClon.idPtarxSector = (!IsEmpty($("#cboPtarPorSector").val()) ? $("#cboPtarPorSector").val() : null);
		objetoClon.numValorSemilla = ConstanteServices.VALOR_DEFECTO;
		if(countRow == 0){
			objetoClon.idGrupo = i+1;
		}else{
			objetoClon.idGrupo = obtenerValorMaximoGrupo(ltaGrilla)+1+i;
		}
		objetoClon.indicePadre = ConstanteServices.INDICADOR_PADRE;
		objetoClon.idPuntoMuestra = ltaPtoMuestraFalta[i].idPuntoMuestra;
		objetoClon.idSubParametro = ltaPtoMuestraFalta[i].idSubParametro;  //ConstanteServices.ID_TOTAL;
		objetoClon.numVolumen  = ConstanteServices.VALOR_DEFECTO;
		objetoClon.numDilc = ConstanteServices.VALOR_DEFECTO;
		objetoClon.numOdi = ConstanteServices.VALOR_DEFECTO;
		objetoClon.numOdf = ConstanteServices.VALOR_DEFECTO;
		objetoClon.numDbo5 = ConstanteServices.VALOR_DEFECTO;
		var objetoClonHijo = clonarObjeto(objetoClon);
		objetoClonHijo.indicePadre = ConstanteServices.INDICADOR_HIJO;
		ltaAgregados.push(objetoClon);
		ltaAgregados.push(objetoClonHijo);
	}
//	grabarListaFaltantes(ltaAgregados);
	return ltaAgregados
}

function modificarAlterados(){
	$.ajax({
        url : "./modificarAlterados",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarSector : cboPtarPorSector.val() != null ? cboPtarPorSector.val() : null,
        	ltaSobrantesJSON : ltaRegistrosPlantillaSobra != null && ltaRegistrosPlantillaSobra.length > 0 ? JSON.stringify(ltaRegistrosPlantillaSobra) : null,
        	ltaAgregadosJSON : ltaPtoMuestraFalta != null && ltaPtoMuestraFalta.length > 0 ? JSON.stringify(agregarFaltantes()) : null
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarPlantillaDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function grabarListaFaltantes(ltaAgregados){
	$.ajax({
        url : "./grabarListaFaltantes",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	ltaAgregadosJSON : JSON.stringify(ltaAgregados),   	
        }
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	 
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_SUCCESS);
        	flagDBO = 0;
        	buscarPlantillaDBO();
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function encontrarPtoMuestraFalta(){
	$.ajax({
        url : "./buscarPtoMuestraFalta",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarSector : (!IsEmpty($("#cboPtarPorSector").val()) ? $("#cboPtarPorSector").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		var ltaFalta = JSON.parse(respuestaBean.parametros.ListaResultado);
    		if(ltaFalta != null && ltaFalta != "[]") {		            		
    			ltaPtoMuestraFalta = ltaFalta;
            }
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}

function encontrarRegistrosPlantillaSobra(){
	$.ajax({
        url : "./buscarRegistrosPlantillaSobra",
        type : "POST",
        dataType : "json",
        async:false,
        data : {
        	idPtarSector : (!IsEmpty($("#cboPtarPorSector").val()) ? $("#cboPtarPorSector").val() : null)   	
        }
    }).done(function(respuestaBean) {
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){
    		var ltaSobra = JSON.parse(respuestaBean.parametros.ListaResultado);
    		if(ltaSobra != null && ltaSobra != "[]") {		            		
    			ltaRegistrosPlantillaSobra = ltaSobra;
            }
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });
}