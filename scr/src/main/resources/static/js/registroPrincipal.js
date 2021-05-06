$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			inicializarComponenteGoogleMaps();
 			console.log(filtro);
});
var accion = false;
function inicializarVariables() {
	var divDetalleGeneral 	= null;
	var idDetalleGeneral	= null;
	var modo 		= null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var txtCodigo = null;
	var txtDescripcion = null;
	var txtDescripcionCorta = null;
	var txtDireccion = null;
	var txtLatitud = null;
	var txtLongitud = null;		
	var btnDirecto = null;
	var btnDBO5 = null;
	var btnAceites = null;
	var divBotonesParametros = null;
	var btnRegresar = null;
}

function cargarComponentes() {	
	btnDirecto = $("#btnDirecto");
	btnDBO5 = $("#btnDBO5");
	btnAceites = $("#btnAceites");
	divBotonesParametros = $("#divBotonesParametros");
	btnRegresar = $("#btnRegresar");
	
	MenuMapa();
	
	btnDirecto.click(function() {
		cargarPantallaDirecto();
	}); 
	
	btnDBO5.click(function() {
		cargarPantallaDBO5();
	}); 
	
	btnAceites.click(function() {
		cargarPantallaAceites();
	}); 
	
	if(!IsEmpty($("#idRegistroLaboratorio").val())){		
		if(idParametroInicial != null && !IsEmpty(idParametroInicial) && ltaPlantaTratamiento != null && ltaPlantaTratamiento.length > 0){
			var planta = ltaPlantaTratamiento[0];	
			buscarParametroAsignados(planta);
		}
	}else{
		$("#btnExpanderMapa").click();
	}
	
	btnRegresar.click(function() {
		if(!IsEmpty(idParametroInicial)){
			$.redirect(contexto+'aprobacionSptar/cargarVentanaAprobacion', {regresar:ConstanteServices.REGRESAR, filtro : filtro});
		}
	}); 
	
	if(!IsEmpty(idParametroInicial)){
		$("#btnRegresar").css("display", "block");
	}
	
	if(!IsEmpty(nombrePlantaInicial)){
		$("#lblPlantaTratamiento").text("Planta de Tratamiento : "+nombrePlantaInicial);
	}
	
	//adicion interval dont finish session
	setInterval(function(){
		$.ajax({
	        url : "./continuarSesion",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {}
	    }).done(function(respuestaBean) {
	    	console.log("continua en sesion");
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    }); 
		}, 300000);
}

var MenuMapa = function(){

    $('body').on('click', '.btn-showMapa', function () {  
        $('.wrap-ui').addClass('opacity-capa');  
        $('.menuMapa').animate({
            left: '0'
        });
    });

    $('body').on('click', '.btn-hideMapa', function () {
        $('.wrap-ui').removeClass('opacity-capa');
        $('.menuMapa').animate({
            left: '-470'
        });
    });  
       

}

function inicializarComponenteGoogleMaps(){
	latitudporDefecto = "-12.043333";
 	LongitudporDefecto = "-77.028333";
    PintarMapa(latitudporDefecto,LongitudporDefecto);   
}
function PintarMapa(latitudParametro,longitudParametro){
	  var latitud = Number(latitudParametro);
	  var longitud = Number(longitudParametro);
	  map = new GMaps({
		  div: '#map',
		  lat: latitud,
		  lng: longitud,
		  zoom : 10
	  });
	  pintarPuntosMaps(ltaPlantaTratamiento);
}

function cargarPantallaProceso(objetoPlanta){
	var obj = {};
	obj.idPtarSector = objetoPlanta.idPtarxSector;
	obj.idParametro = objetoPlanta.idParametro;
	obj.idRegistroLaboratorio = !IsEmpty($("#idRegistroLaboratorio").val())? $("#idRegistroLaboratorio").val() : null;
	obj.descripcionPtar = objetoPlanta.descripcionCorta;
	obj.descripcionSector = objetoPlanta.descripcionSector;
	
	var divPrincipal = "divBotonesParametros";
	var claseBuscarPrin = "BotonesPrincipales";
	if(objetoPlanta.idParametro == ConstanteServices.ID_PARAMETRO_DIRECTO){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_PARAMETRO_DIRECTO;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroDirecto', obj, $('#idCargaNuevaPantalla'));
	}else if(objetoPlanta.idParametro == ConstanteServices.ID_PARAMETRO_DBO5){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_PARAMETRO_DBO5;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroDBO5', obj, $('#idCargaNuevaPantalla'));
	}else if(objetoPlanta.idParametro == ConstanteServices.ID_PARAMETRO_ACEITE||objetoPlanta.idParametro == ConstanteServices.ID_PARAMETRO_SOLIDO){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_PARAMETRO_DBO5;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroDBO5', obj, $('#idCargaNuevaPantalla'), filtro);
	}else if(objetoPlanta.idParametro == ConstanteServices.ID_PARASITOLOGICOS){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_PARASITOLOGICOS;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroParasitologico', obj, $('#idCargaNuevaPantalla'));
	}else if(objetoPlanta.idParametro == ConstanteServices.ID_HIDROBIOLOGICOS){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_HIDROBIOLOGICOS;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroHidrobiologico', obj, $('#idCargaNuevaPantalla'));
	}else if(objetoPlanta.idParametro == ConstanteServices.ID_MICROBIOLOGICOS){
		var divDirec = "btn_Parametro_"+ConstanteServices.ID_MICROBIOLOGICOS;
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,divDirec,ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		cargarPantalla('./cargarVentanaRegistroMicrobiologico', obj, $('#idCargaNuevaPantalla'));		
	}else{
		modificarEstilosBotonera(divPrincipal,claseBuscarPrin,"",ConstanteServices.COLOR_SELECT_BOTONERA,ConstanteServices.TEXTO_SELECT_BOTONERA,ConstanteServices.COLOR_NORMAL_BOTONERA,ConstanteServices.TEXTO_NORMAL_BOTONERA);
		$("#idCargaNuevaPantalla").html("");
	}
}

function cargarPuntosMapa() {
 	$.ajax({
        url : "./buscarListaPtarMaps",
        type : "POST",
        dataType : "json",
        async:false,
        data : {}
    }).done(function(respuestaBean) {
    	mostrarMensaje();
    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
        	var ListaResultado = respuestaBean.parametros.ListaResultado; 
        	if(ListaResultado != null && ListaResultado != "[]") {		
        		var lista = JSON.parse(ListaResultado);
        		pintarPuntosMaps(lista);
        	}else{
        		limpiarMapa();
        	}
        } else {
        	limpiarMapa();
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
    });	
}


function pintarPuntosMaps(ltaMaps){
	limpiarMapa();
	var urlColor = retornoColor("azul");
	for(var i = 0 ; i < ltaMaps.length ; i++){
		var objeto = ltaMaps[i];
		var totalCaracter = objeto.direccion.length;
		var puntoCorte = totalCaracter/2;
		var direccionHtml = cortarCadenaLimitada(objeto.direccion,0,puntoCorte)+ " -<br>"+cortarCadenaTotal(objeto.direccion,puntoCorte);
		var etiqueta  = "<span><strong>"+objeto.descripcionPtarxSector+"</strong></span><br>";	
		    etiqueta  += "<span><strong>Dirección :</strong></span><br>";
	        etiqueta  +="<span>"+direccionHtml+"</span><br>";			       
	        etiqueta  +="<span><strong>LAT: </strong>"+objeto.latitud+"</span><br>";	
	        etiqueta  +="<span><strong>LNG: </strong>"+objeto.longitud+"</span><br>";	
        if(!IsEmpty($("#idRegistroLaboratorio").val())){		
    		if(idParametroInicial != null && !IsEmpty(idParametroInicial)){    			
    			agregarPuntoMapaSinEvento(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    		}else{
    			agregarPuntoMapaConEvento(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    		}
        }else{
        	agregarPuntoMapaConEvento(map,objeto.latitud,objeto.longitud,urlColor,etiqueta,JSON.stringify(objeto),objeto.descripcionCorta);
    	}
	}
}


function limpiarMapa(){
  eliminandoMarcasMaps();
  map.markers = [];
  map.cleanRoute();
}

function eliminandoMarcasMaps(){
  var ListaMarcas = map.markers;
  if(IsEmpty(ListaMarcas) == false){
    for(var i = 0 ; i <ListaMarcas.length ;i++ ){
	    map.markers[i].setMap(null);
	  }
  }		  
}

function verPlantaTratamiento(objetoJSONString){
	$("#idCargaNuevaPantalla").html("");
	var objetoJSONPlantaT = JSON.parse(objetoJSONString);
	$("#idRegistroLaboratorio").val("");
	$("#lblPlantaTratamiento").text("Planta de Tratamiento : "+objetoJSONPlantaT.descripcionPtarxSector);
	buscarParametroAsignados(objetoJSONPlantaT);
}

function buscarParametroAsignados(objetoJSONPlantaT){
	 	$.ajax({
            url : "./buscarParametroAsignadosMenu",
            type : "POST",
            dataType : "json",
            async:false,
            data : {                     
					idPtarPorSector: objetoJSONPlantaT.idPtarxSector
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
            	var ListaResultado = respuestaBean.parametros.ListaResultado; 
            	var listaJSON = [];
            	if(ListaResultado != null && ListaResultado != "[]") {		            		
            		listaJSONOpciones =  JSON.parse(ListaResultado);
            		//formateamos para que no coja Solido ni aceite
            		var objetoSolido  = null;
            		for(var i = 0 ; i < listaJSONOpciones.length ; i++ ){
            			if(listaJSONOpciones[i].idParametro == ConstanteServices.ID_PARAMETRO_SOLIDO){
            				objetoSolido = listaJSONOpciones[i];
            				break;
            			}
            		}
            		if(!IsEmpty(objetoSolido)){
            			var posicionSolido = listaJSONOpciones.indexOf(objetoSolido);
            			listaJSONOpciones.splice(posicionSolido,1);
            		}
            		var objetoAceite  = null;
            		for(var i = 0 ; i < listaJSONOpciones.length ; i++ ){
            			if(listaJSONOpciones[i].idParametro == ConstanteServices.ID_PARAMETRO_ACEITE){
            				objetoAceite = listaJSONOpciones[i];
            				break;
            			}
            		}            		
            		if(!IsEmpty(objetoAceite)){
            			var posicionAceite = listaJSONOpciones.indexOf(objetoAceite);
            			listaJSONOpciones.splice(posicionAceite,1);
            		}  
            		if(!IsEmpty(idParametroInicial)){
            			var listaJSONAuxiliar = [];
            			if(idParametroInicial == ConstanteServices.ID_PARAMETRO_SOLIDO ||  idParametroInicial == ConstanteServices.ID_PARAMETRO_ACEITE){
            				var objetoDBO5  = null;
            				for(var i = 0 ; i < listaJSONOpciones.length ; i++ ){
                    			if(listaJSONOpciones[i].idParametro == ConstanteServices.ID_PARAMETRO_DBO5){
                    				objetoDBO5 = listaJSONOpciones[i];
                    				break;
                    			}
                    		} 
            				listaJSONAuxiliar.push(objetoDBO5);
            			}else{
            				var objetoInicial  = null;
            				for(var i = 0 ; i < listaJSONOpciones.length ; i++ ){
                    			if(listaJSONOpciones[i].idParametro == idParametroInicial){
                    				objetoInicial = listaJSONOpciones[i];
                    				break;
                    			}
                    		} 
                			listaJSONAuxiliar.push(objetoInicial)
            			}
            			listaJSONOpciones = [];
            			listaJSONOpciones = listaJSONAuxiliar;
            		}            		
            		construirHtmlBotones(listaJSONOpciones,objetoJSONPlantaT);
            	}else{
            		$("#divBotonesParametros").html("");
            	} 
            } else{
            	$("#divBotonesParametros").html("");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
        	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
        });	
}

function construirHtmlBotones(listaParametros,objetoJSONPlantaT){
	var htmlBotones = "";
	for(var i = 0 ; i < listaParametros.length ; i++){
		var objeto = listaParametros[i];		
		var nuevo = clonarObjeto(objetoJSONPlantaT);
		nuevo.idParametro = objeto.idParametro;	
		var objetoJsonString = JSON.stringify(nuevo);
		htmlBotones += "<button type='button' id='btn_Parametro_"+nuevo.idParametro+"' onclick='cargarPantallaProceso("+objetoJsonString+");' class='btn-s btn-addBotoneraChild BotonesPrincipales'>"+objeto.descripcionParametro+"</button>&nbsp;&nbsp;";		
	}
	$("#divBotonesParametros").html(htmlBotones);
	if(idParametroInicial != null &&  !IsEmpty(idParametroInicial)){
		var clonado = clonarObjeto(objetoJSONPlantaT);
		clonado.idParametro = idParametroInicial;	
		cargarPantallaProceso(clonado);
	}
}

window.operateEventsAdjuntos = {
		'click #btnDescargar': function (e, value, row, index) {	
			var idDiv = "mensajeInformacionAdjunto";				
			validarArchivo(row.nombreInterno, row.nombreOriginal,idDiv);
		}
}


window.operateEventsDirecto = {
		'click #btnEditarRegistroDirecto': function (e, value, row, index) {
				verificarParametroEditarNuevo(index,row)
		},
		'click #btnRegresarRegistroDirecto': function (e, value, row, index) {	
			flagDirecto = 0;
			var lista = tblResultadoRegistroDirecto.bootstrapTable("getData");
			tblResultadoRegistroDirecto.bootstrapTable('load', lista);
			visibleAnalistaDirecto();
		},
		'click #btnAnularRegistroDirecto': function (e, value, row, index) {
			var indicadorAnulacion = false;			
			if(flagDirecto == 1){
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
				if(IsEmpty(row.idRegDirecto)){
					modal.defaultModal(html, 'eliminarSubParametroNuevo('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroDirecto('+row.idRegDirecto+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroDirecto': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroDirecto.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.descripcionSubparametro = textoSub;
				if(idSub != null){
					objetoModificar.idSubParametro = parseInt(idSub);
				}else{
					objetoModificar.idSubParametro = idSub;
				}
				if(idPunto != null){
					objetoModificar.idPuntoMuestra = parseInt(idPunto);
				}else{
					objetoModificar.idPuntoMuestra = idPunto;
				}
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				objetoModificar.indicadorFactor = buscarIndicadorFactorSubParametro(idSub);
				if(validarNuevoRegistroNuevoDirecto(objetoModificar)){
					guardarTablaAntesCambios();
					tblResultadoRegistroDirecto.bootstrapTable('updateRow', {index: index, row: objetoModificar});
					formatearInputDirecto();
					flagDirecto = 0;
					visibleAnalistaDirecto();
				}	
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				if(validarNuevoRegistroEditar(index)){
					var idAnalistaNew = $("#cboAnalista_"+index).val();   
					var fechaMonitoreoNew = $("#dtpMonitoreo_"+index).val();
					var fechaRegDatoNew = $("#dtpRegDato_"+index).val();
					var idSubPNew = $("#divSubParametro_"+index).val();
					var textoSubNew = extraerTextoCombo("divSubParametro_"+index);
					var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
					var numValorNew = $("#divValor_"+index).val();
					var numProfundidaNew = $("#divProfundida_"+index).val();
					var numFactorNew = $("#divFactor_"+index).val();
					var numValorDQONew = $("#divResultado_"+index).val();	
					
					row.idRegLaboratorio = $("#idRegistroLaboratorio").val();
					row.idAnalista = idAnalistaNew;
					row.fechaMonitoreoString = fechaMonitoreoNew;
					row.fechaRegDatoString = fechaRegDatoNew;
					row.idSubParametro = idSubPNew;
					row.idPuntoMuestra = idPuntoMuestraNew;
					row.numValor = numValorNew;
					row.numProfundida = numProfundidaNew;
					row.numValorDQO = numValorDQONew;					
					var indicad = buscarIndicadorFactor(idSubPNew);
					if(indicad == ConstanteServices.INDICADOR_FACTOR){	
						row.numFactor = numFactorNew;
					}else{
						row.numFactor = null;
					}
					row.idPtarxSector = idPtarSector;
					var tituloModal = 'Registro Directo';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroDirecto('+JSON.stringify(row)+')', '', tituloModal);
				}
			}
		}
}

window.operateEventsParasitologico = {
		'click #btnEditarRegistroParasitologico': function (e, value, row, index) {
			verificarParametroEditarNuevoParasitologico(index,row);
		},
		'click #btnRegresarRegistroParasitologico': function (e, value, row, index) {
			flagParasitologico = 0;
			buscarDatosRegistroParasitologico();
			buscarFormulasPrincipalesParasitologico();
			visibleAnalistaParasitologico();
		},
		'click #btnAnularRegistroParasitologico': function (e, value, row, index) {			
			var indicadorAnulacion = false;			
			if(flagParasitologico == 1){
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
				if(IsEmpty(row.idRegParasitologico)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoParasitologico('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroParasitologico('+row.idRegParasitologico+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroParasitologico': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroParasitologico.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.descripcionSubparametro = textoSub;
				if(idSub != null){
					objetoModificar.idSubParametro = parseInt(idSub);
				}else{
					objetoModificar.idSubParametro = idSub;
				}
				if(idPunto != null){
					objetoModificar.idPuntoMuestra = parseInt(idPunto);
				}else{
					objetoModificar.idPuntoMuestra = idPunto;
				}
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				if(validarNuevoRegistroNuevoParasitologico(objetoModificar)){
					guardarTablaAntesCambiosParasitologico();
					tblResultadoRegistroParasitologico.bootstrapTable('updateRow', {index: index, row: objetoModificar});
					formatearInputParasitologico();	
					flagParasitologico = 0;
					visibleAnalistaParasitologico();
				}
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				if(validarNuevoRegistroEditar(index)){
					var idAnalistaNew = $("#cboAnalista_"+index).val();   
					var fechaMonitoreoNew = $("#dtpMonitoreo_"+index).val();
					var fechaRegDatoNew = $("#dtpRegDato_"+index).val();
					var idSubPNew = $("#divSubParametro_"+index).val();
					var textoSubNew = extraerTextoCombo("divSubParametro_"+index);
					var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
					var numVolumenNew = $("#divVolumen_"+index).val();
					var numCantidadNew = $("#divCantidad_"+index).val();
					var idMicroorganismoNew = $("#cboMicroorganismo_"+index).val();
					var numResultado = $("#divResultado_"+index).val();
					var descripcionObservacion = $("#divDescripcionObservacion_"+index).val();
					
					row.idRegLaboratorio = $("#idRegistroLaboratorio").val();
					row.idAnalista = idAnalistaNew;
					row.fechaMonitoreoString = fechaMonitoreoNew;
					row.fechaRegDatoString = fechaRegDatoNew;
					row.idSubParametro = idSubPNew;
					row.idPuntoMuestra = idPuntoMuestraNew;
					row.numCantidad = numCantidadNew;
					row.numVolumen = numVolumenNew;
					row.idPtarxSector = idPtarSector;
					row.idMicroorganismo = idMicroorganismoNew;
					row.numResultado = numResultado;
					row.descripcionObservacion = descripcionObservacion;
					var itemFormulaResultado = JSON.parse(txtFormulaParasitologico1.attr("valor"));
					row.idFormulaResultado  = itemFormulaResultado.idFormulaxParametro;
					
					var tituloModal = 'Registro Parasitologico';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroParasitologico('+JSON.stringify(row)+')', '', tituloModal);
				}
			}
		}
}

window.operateEventsHidrobiologico = {
		'click #btnEditarRegistroHidrobiologico': function (e, value, row, index) {
			verificarParametroEditarNuevoHidrobiologico(index,row);
		},
		'click #btnRegresarRegistroHidrobiologico': function (e, value, row, index) {	
			flagHidrobiologico = 0;
			buscarDatosRegistroHidrobiologico();
			buscarFormulasPrincipalesHidrobiologico();
			visibleAnalistaHidrobiologico();
		},
		'click #btnHabilitarRegistroHidrobiologico': function (e, value, row, index) {				
		},
		'click #btnAnularRegistroHidrobiologico': function (e, value, row, index) {			
			var indicadorAnulacion = false;			
			if(flagHidrobiologico == 1){
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
				if(IsEmpty(row.idRegHidrobiologico)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoHidrobiologico('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroHidrobiologico('+row.idRegHidrobiologico+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroHidrobiologico': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroHidrobiologico.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.descripcionSubparametro = textoSub;
				if(idSub != null){
					objetoModificar.idSubParametro = parseInt(idSub);
				}else{
					objetoModificar.idSubParametro = idSub;
				}
				if(idPunto != null){
					objetoModificar.idPuntoMuestra = parseInt(idPunto);
				}else{
					objetoModificar.idPuntoMuestra = idPunto;
				}
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				if(validarNuevoRegistroNuevoHidrobiologico(objetoModificar)){
					guardarTablaAntesCambiosHidrobiologico();
					tblResultadoRegistroHidrobiologico.bootstrapTable('updateRow', {index: index, row: objetoModificar});
					formatearInputHidrobiologico();
					flagHidrobiologico = 0;
					calcularValoresHidrobiologico(index);
				}
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				if(validarNuevoRegistroEditar(index)){
					var idAnalistaNew = $("#cboAnalista_"+index).val();   
					var fechaMonitoreoNew = $("#dtpMonitoreo_"+index).val();
					var fechaRegDatoNew = $("#dtpRegDato_"+index).val();
					var idSubPNew = $("#divSubParametro_"+index).val();
					var textoSubNew = extraerTextoCombo("divSubParametro_"+index);
					var idPuntoMuestraNew = $("#cboPuntoMuestra_"+index).val();
					var numCantidadNew = $("#divCantidad_"+index).val();
					var numConteoNew = $("#divConteo_"+index).val();
					var idMicroorganismoNew = $("#cboMicroorganismo_"+index).val();
					var idTipoConteoNew = $("#cboTipoConteo_"+index).val();
					var numResultado = $("#divResultado_"+index).val();
					var descripcionObservacion = $("#divDescripcionObservacion_"+index).val();
					
					row.idRegLaboratorio = $("#idRegistroLaboratorio").val();
					row.idAnalista = idAnalistaNew;
					row.fechaMonitoreoString = fechaMonitoreoNew;
					row.fechaRegDatoString = fechaRegDatoNew;
					row.idSubParametro = idSubPNew;
					row.idPuntoMuestra = idPuntoMuestraNew;
					row.numCantidad = numCantidadNew;
					row.numConteo = numConteoNew;
					row.idPtarxSector = idPtarSector;
					row.idMicroorganismo = idMicroorganismoNew;
					row.idTipoConteo = idTipoConteoNew;
					row.numResultado = numResultado;
					row.descripcionObservacion = descripcionObservacion;
					
					if(idTipoConteoNew == ConstanteServices.ID_TIPO_CONTEO_CAMPO){
						if(idSubPNew == ConstanteServices.ID_SUBPARAMETRO_FITO){
							var itemFormula = JSON.parse(txtFormulaHidrobiologico1.attr("valor"));
							if(itemFormula != null && itemFormula != undefined ){
								row.idFormula  = itemFormula.idFormulaxParametro;
							}
						}else{
							var itemFormula = JSON.parse(txtFormulaHidrobiologico2.attr("valor"));
							if(itemFormula != null && itemFormula != undefined ){
								row.idFormula  = itemFormula.idFormulaxParametro;
							}
						}
					}else if(idTipoConteoNew == ConstanteServices.ID_TIPO_CONTEO_FRANJA){
						if(idSubPNew == ConstanteServices.ID_SUBPARAMETRO_FITO){
							var itemFormula = JSON.parse(txtFormulaHidrobiologico3.attr("valor"));
							if(itemFormula != null && itemFormula != undefined ){
								row.idFormula  = itemFormula.idFormulaxParametro;
							}
						}else{
							var itemFormula = JSON.parse(txtFormulaHidrobiologico3.attr("valor"));
							if(itemFormula != null && itemFormula != undefined ){
								row.idFormula  = itemFormula.idFormulaxParametro;
							}
						}
					}else{
						row.idFormula = ConstanteServices.VALOR_DEFECTO;
					}
					
					var tituloModal = 'Registro Hidrobiologico';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroHidrobiologico('+JSON.stringify(row)+')', '', tituloModal);
				}
			}
		}
}


window.operateEventsSolido = {
	'click #btnEditarRegistroSolido': function (e, value, row, index) {
			verificarParametroEditarNuevoSolido(index,row)
	},
	'click #btnRegresarRegistroSolido': function (e, value, row, index) {	
		flagSolido = 0;
		buscarDatosRegistroSolido();
		buscarFormulasPrincipales();
		visibleAnalistaSolido();
	},
	'click #btnAnularRegistroSolido': function (e, value, row, index) {
		var indicadorAnulacion = false;			
		if(flagSolido == 1){
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
			if(IsEmpty(row.idSolido)){
				modal.defaultModal(html, 'eliminarSubParametroNuevoSolido('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
			}else{
				modal.defaultModal(html, 'eliminarItemRegistroSolido('+row.idSolido+')' , '', titulo, 'Aceptar', 'Cancelar');
			}
		}
	},
	'click #btnGuardarRegistroSolido': function (e, value, row, index) {
		if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
			var idSub = $("#divSubParametro_"+index).val();
			var textoSub = extraerTextoCombo("divSubParametro_"+index);
			var idPunto = $("#cboPuntoMuestra_"+index).val();
			var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
			var lta = tblResultadoRegistroSolido.bootstrapTable("getData");
			var objetoModificar = lta[index];
			objetoModificar.idSubParametro = idSub   ;
			objetoModificar.descripcionSubparametro = textoSub;
			objetoModificar.idPuntoMuestra = idPunto ;
			objetoModificar.descripcionPuntoMuestra = textoPunto;
			objetoModificar.idTipoSolido =  $("#cboTipoSolido").val();
			if(validarNuevoRegistroNuevoSolido(objetoModificar)){
				guardarTablaAntesCambiosSolido();
				tblResultadoRegistroSolido.bootstrapTable('updateRow', {index: index, row: objetoModificar});
				formatearInputSolido();
				flagSolido = 0;
				visibleAnalistaSolido();
			}			
		}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
			if(validarNuevoRegistroEditarSolido(index)){				
				row.idRegistroLaboratorio = $("#idRegistroLaboratorio").val();
				row.idAnalista = $("#cboAnalista_"+index).val();  
				row.fechaMonitoreoString = $("#dtpMonitoreo_"+index).text();
				row.fechaRegDatoString = $("#dtpRegDato_"+index).val();
				row.idSubParametro = $("#divSubParametro_"+index).val();
				row.idPuntoMuestra = $("#cboPuntoMuestra_"+index).val();				
				row.idPtarxSector = idPtarSector;				
				row.pesoInicial = $("#divPesoInicial_"+index).val();
				row.volumenFiltrado = $("#divVolumenFiltrado_"+index).val();
				row.pesoFinal = $("#divPesoFinal_"+index).val();	
				row.pesoCalcinado = $("#divPesoCalcinado_"+index).val();
				row.numSst = $("#divnumSst_"+index).val();
				row.numSsf = $("#divnumSsf_"+index).val();
				row.numSsv = $("#divnumSsv_"+index).val();
				row.idTipoSolido = $("#cboTipoSolido").val();
				var itemFormulaSst = JSON.parse(txtVariable1.attr("valor"));
				row.idFormulaSst  = itemFormulaSst.idFormulaxParametro;
				var itemFormulaSsf = JSON.parse(txtVariable2.attr("valor"));
				row.idFormulaSsf  = itemFormulaSsf.idFormulaxParametro;
				var itemFormulaSsv = JSON.parse(txtVariable3.attr("valor"));
				row.idFormulaSsv  = itemFormulaSsv.idFormulaxParametro;
				if(validarNuevoRegistroNuevoSolido(row)){
					var tituloModal = 'Registro Solido';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroSolido('+JSON.stringify(row)+')', '', tituloModal);
				}				
			}
		}
	}
}

window.operateEventsMicrobiologico = {
		'click #checkMejorValorMicrobiologico': function (e, value, row, index) {
			evaluarMejorValorSeleccionado(index, row);
		},
		'click #btnEditarRegistroMicrobiologico': function (e, value, row, index) {
			verificarParametroEditarNuevoMicrobiologico(index,row);
		},
		'click #btnRegresarRegistroMicrobiologico': function (e, value, row, index) {	
			flagMicrobiologico = 0;
			tipoEstado = null;
			buscarDatosRegistroMicrobiologico();			
		},
		'click #btnHabilitarRegistroMicrobiologico': function (e, value, row, index) {				
		},
		'click #btnAnularRegistroMicrobiologico': function (e, value, row, index) {
			if(flagMicrobiologico != 1 || modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				var html = 
					'<div class="row">' + 
						'<div class="col-sm-12">' + 
							'<label class="control-label">Se Eliminara el Registro. ¿Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Eliminación de Registro N°<font><b>' + (index + 1) + '</b></font>';
				if(IsEmpty(row.idRegMicrobiologico)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoMicrobiologico('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroMicrobiologico('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroMicrobiologico': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroMicrobiologico.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.idPuntoMuestra = parseInt(idPunto);
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				activarRegistrosPadres();
				tipoEstado = null;
				guardarTablaAntesCambiosMicrobiologico();
				tblResultadoRegistroMicrobiologico.bootstrapTable('updateRow', {index: index, row: objetoModificar});
				formatearInputMicrobiologico();	
				flagMicrobiologico = 0;
				bloquearCheckMejorValor();
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
				asignarParametrosHijasEditar(index);
				completarLtaRegistroMicrobiologico();
				if(validarNuevoRegistroEditar(index)){
					var tituloModal = 'Registro Microbiologico';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroMicrobiologico('+index+')', '', tituloModal);
				}
			}
		}
}

window.operateEventsMicrobiologicoSecundario = {
		'click #checkMejorValorMicrobiologicoSecundario': function (e, value, row, index) {
			evaluarMejorValorSeleccionadoSecundario(index, row);
		},
		'click #btnEditarRegistroMicrobiologicoSecundario': function (e, value, row, index) {
			verificarParametroEditarNuevoMicrobiologicoSecundario(index,row);
		},
		'click #btnRegresarRegistroMicrobiologicoSecundario': function (e, value, row, index) {	
			flagMicrobiologicoSecundario = 0;
			tipoEstadoSecundario = null;
			buscarDatosRegistroMicrobiologicoSecundario();			
		},
		'click #btnHabilitarRegistroMicrobiologicoSecundario': function (e, value, row, index) {				
		},
		'click #btnAnularRegistroMicrobiologicoSecundario': function (e, value, row, index) {		
			if(flagMicrobiologicoSecundario != 1 || modo == ConstanteServices.ESTADO_OPCION_NUEVA){
				var html = 
					'<div class="row">' + 
						'<div class="col-sm-12">' + 
							'<label class="control-label">Se Eliminara el Registro. ¿Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Eliminación de Registro N°<font><b>' + (index + 1) + '</b></font>';
				if(IsEmpty(row.idRegMicrobiologico)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoMicrobiologicoSecundario('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					modal.defaultModal(html, 'eliminarItemRegistroMicrobiologicoSecundario('+row.indice+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroMicrobiologicoSecundario': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var lta = tblResultadoRegistroMicrobiologicoSecundario.bootstrapTable("getData");
				var objetoModificar = lta[index];
				objetoModificar.idPuntoMuestra = parseInt(idPunto);
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				activarRegistrosPadresSecundario();
				tipoEstadoSecundario = null;
				guardarTablaAntesCambiosMicrobiologicoSecundario();
				tblResultadoRegistroMicrobiologicoSecundario.bootstrapTable('updateRow', {index: index, row: objetoModificar});
				formatearInputMicrobiologicoSecundario();	
				flagMicrobiologicoSecundario = 0;
				bloquearCheckMejorValorSecundario();
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR){
				asignarParametrosHijasEditarSecundario(index);
				completarLtaRegistroMicrobiologicoSecundario();
				if(validarNuevoRegistroEditarSecundario(index)){
					var tituloModal = 'Registro Microbiologico';
		   			modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroMicrobiologicoSecundario('+index+')', '', tituloModal);
				}
			}
		}
}



window.operateEventsDBO = {
		'click #btnEditarRegistroDBO': function (e, value, row, index) {
				verificarParametroEditarNuevoDBO(index,row)
		},
		'click #btnRegresarRegistroDBO': function (e, value, row, index) {	
			flagDBO = 0;
			buscarDatosRegistroDBO();
			buscarFormulasPrincipales();
			visibleAnalistaDBO();
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
							'<label class="control-label">Se Eliminara el Registro. ¿Desea continuar?</label>' + 
						'</div>' + 
					'</div>';
				var titulo = 'Eliminación de Registro N°<font><b>' + (index + 1) + '</b></font>';
				if(IsEmpty(row.idDBO)){
					modal.defaultModal(html, 'eliminarSubParametroNuevoDBO('+row.idGrupo+')' , '', titulo, 'Aceptar', 'Cancelar');					
				}else{
					ltaGrilla = tblResultadoRegistroDBO.bootstrapTable("getData");
					ltaGrilla.splice(index,2);
					ltaActual = completarDatosSemillaEdicion(ltaGrilla);
					modal.defaultModal(html, 'eliminarItemRegistroDBO('+row.idGrupo+','+JSON.stringify(ltaActual)+')' , '', titulo, 'Aceptar', 'Cancelar');
				}
			}
		},
		'click #btnGuardarRegistroDBO': function (e, value, row, index) {
			if(modo == ConstanteServices.ESTADO_OPCION_NUEVA) {
				var idPunto = $("#cboPuntoMuestra_"+index).val();
				var textoPunto = extraerTextoCombo("cboPuntoMuestra_"+index);
				var idSub = $("#divSubParametro_"+index).val();
				var textoSub = extraerTextoCombo("divSubParametro_"+index);
				var lta = tblResultadoRegistroDBO.bootstrapTable("getData");
				var objetoModificar = lta[index];
				var objetoModificarHija = lta[index+1];
				objetoModificar.idPuntoMuestra = (idPunto != null)? parseInt(idPunto) : idPunto;
				objetoModificar.descripcionPuntoMuestra = textoPunto;
				objetoModificar.idSubParametro = (idSub != null)? parseInt(idSub) : idSub;
				objetoModificar.descripcionSubparametro = textoSub;
				objetoModificar.indicadorSemilla = buscarIndicadorSemillaPuntoMuestra(idPunto);
				objetoModificarHija.idPuntoMuestra = objetoModificar.idPuntoMuestra;
				objetoModificarHija.descripcionPuntoMuestra = objetoModificar.descripcionPuntoMuestra;
				objetoModificarHija.idSubParametro = objetoModificar.idSubParametro;
				objetoModificarHija.descripcionSubparametro = objetoModificar.descripcionSubparametro;
				objetoModificarHija.indicadorSemilla = objetoModificar.indicadorSemilla;
				if(validarNuevoRegistroNuevoDBO(objetoModificar, ConstanteServices.INDICADOR_PADRE) && validarNuevoRegistroNuevoDBO(objetoModificarHija, ConstanteServices.INDICADOR_HIJO)){
					guardarTablaAntesCambiosDBO();
					tblResultadoRegistroDBO.bootstrapTable('updateRow', {index: index, row: objetoModificar});
					tblResultadoRegistroDBO.bootstrapTable('updateRow', {index: index+1, row: objetoModificarHija});
					formatearInputDBO();
					flagDBO = 0;
					visibleAnalistaDBO();
				}				
			}else if(modo == ConstanteServices.ESTADO_OPCION_EDITAR) {
				var ltaActual = tblResultadoRegistroDBO.bootstrapTable("getData");
					
				row.idRegistroLaboratorio = $("#idRegistroLaboratorio").val();
				row.idAnalista = $("#cboAnalista_"+index).val();  
//				row.fechaInicialString = $("#dtpFechaInicial").val();
				row.fechaInicialString = $("#dtpFechaInicial").val() + " " + $("#horaInicio").val() + ":00";
				row.fechaVencimientoString = $("#dtpFechaVencimiento").val();
				row.nroFrasco =  $("#divnroFrasco_"+index).val();	
				row.idPuntoMuestra = (typeof $("#cboPuntoMuestra_"+index).val() == "undefined")?  row.idPuntoMuestra : $("#cboPuntoMuestra_"+index).val();	
				row.idSubParametro = (typeof $("#divSubParametro_"+index).val() == "undefined")?  row.idSubParametro : $("#divSubParametro_"+index).val();			
				row.idPtarxSector = idPtarSector;
				var valorSemillaNew = $("#divnumValorSemilla_"+index).val();
				var indicad = buscarIndicadorSemilla(row.idPuntoMuestra);
				if(indicad == ConstanteServices.INDICADOR_SEMILLA){	
					if(IsEmpty(""+valorSemillaNew)){
						row.numValorSemilla = ConstanteServices.VALOR_DEFECTO;
					}else{
						row.numValorSemilla = valorSemillaNew;
					}
					row.indicadorSemilla = parseInt(ConstanteServices.INDICADOR_SEMILLA);
				}else{
					row.numValorSemilla = null;
					row.indicadorSemilla = 0;
				}
				row.numVolumen = $("#divnumVolumen_"+index).val();
				row.numOdi = $("#divnumOdi_"+index).val();
				row.numOdf = $("#divnumOdf_"+index).val();	
				row.numFactor = 0;
				row.numDeplec = $("#divnumDeplec_"+index).val();
				row.numDilc = $("#divnumDilc_"+index).val();
				row.numDbo5 = $("#divnumDbo5_"+index).val();
				var itemFormulaDeplec = JSON.parse(txtVariable1.attr("valor"));
				row.idFormulaDeplec  = itemFormulaDeplec.idFormulaxParametro;
				var itemFormulaDilc = JSON.parse(txtVariable2.attr("valor"));
				row.idFormulaDilc  = itemFormulaDilc.idFormulaxParametro;
				var itemFormulaDbo5 = JSON.parse(txtVariable3.attr("valor"));
				row.idFormulaDbo5  = itemFormulaDbo5.idFormulaxParametro;
				
				//HIJA
				var rowHija = clonarObjeto(row);
				
				rowHija.idDBO = ltaActual[index+1].idDBO;
				var valorSemillaNew = $("#divnumValorSemilla_"+(index+1)).val();
				var indicad = buscarIndicadorSemilla(row.idPuntoMuestra);
				if(indicad == ConstanteServices.INDICADOR_SEMILLA){
					if(IsEmpty(""+valorSemillaNew)){
						rowHija.numValorSemilla = ConstanteServices.VALOR_DEFECTO;
					}else{
						rowHija.numValorSemilla = valorSemillaNew;
					}
					rowHija.indicadorSemilla = parseInt(ConstanteServices.INDICADOR_SEMILLA);
				}else{
					rowHija.numValorSemilla = null;
					rowHija.indicadorSemilla = 0;
				}
				rowHija.nroFrasco =  $("#divnroFrasco_"+(index+1)).val();
				rowHija.numOdi = $("#divnumOdi_"+(index+1)).val();
				rowHija.numOdf = $("#divnumOdf_"+(index+1)).val();
				rowHija.numDeplec = $("#divnumDeplec_"+(index+1)).val();
				rowHija.numDilc = $("#divnumDilc_"+(index+1)).val();
				rowHija.numDbo5 = $("#divnumDbo5_"+(index+1)).val();
				rowHija.indicePadre = ConstanteServices.INDICADOR_HIJO;
				
				ltaActual[index+1] = rowHija;
				
				ltaActual = completarDatosSemillaEdicion(ltaActual);
				ltaActual = completarDatosSemillaEdicion(ltaActual);

				console.log(rowHija);
				console.log(ltaActual);
				if(validarNuevoRegistroEditarDBO(index, ConstanteServices.INDICADOR_PADRE, row.idPuntoMuestra,row) && validarNuevoRegistroEditarDBO((index+1), ConstanteServices.INDICADOR_HIJO, rowHija.idPuntoMuestra,rowHija)){
					var tituloModal = 'Registro DBO';
					modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarItemRegistroDBO('+JSON.stringify(ltaActual)+')', '', tituloModal);
				}				
			}
		}
	}
