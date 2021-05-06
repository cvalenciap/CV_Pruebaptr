$(document).ready(function() {
			inicializarVariables();
 			cargarComponentes();
 			cargarFiltros();
});

var flagNuevo = 0;
var existPrincipal = 0;
var ltaVariable = {};

function inicializarVariables() {
	var divMensajeInformacion = null;
	var btnNuevoFormula = null;
	var tblResultados = null;
	var tblVariables1 = null;
	var tblVariables2 = null;
	var tblVariables3 = null;
	var tblVariables4 = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var cboParametro = null;
	var cboOrden = null;
	var modo = null;
}

function cargarComponentes() {
	divMensajeInformacion = $('#divMensajeInformacion');
	btnNuevoFormula = $('#btnNuevoFormula');
	tblResultados = $('#tblResultados');
	tblVariables1 = $('#tblVariables1');
	tblVariables2 = $('#tblVariables2');
	tblVariables3 = $('#tblVariables3');
	tblVariables4 = $('#tblVariables4');
	mensajeRespuesta = $('#mensajeRespuesta');
	estadoRespuesta = $('#estadoRespuesta');	
	cboParametro = $("#cboParametro");
	
	agregarListaItem(cboParametro, ltaParametro, "idDetalleGeneral", "descripcion");
		
	cboParametro.change(function(event){
		flagNuevo = 0;
		buscarFormula();
		buscarVariable();
    });
	
	cargarGrillaVariables();
	buscarVariable();
	cargarGrillaResultado();
	buscarFormula();
		
	btnNuevoFormula.click(function(event) {
		var countRow = $('#tblResultados').bootstrapTable('getData').length;
		modo = ConstanteServices.ESTADO_OPCION_NUEVA;
		flagNuevo = 1
		
        tblResultados.bootstrapTable('insertRow', {
            index: countRow + 1,
            row: {
            	accion : 123,
            	codigoFormula : "",
            	descripcionOrden : crearCboOrden('accion', 1, 0),
            	descripcionParametro : extraerTextoCombo("cboParametro"),
            	descripcionFormula : "",
            	combinacionFormula : ""
            }
        });


        var accionGuardar = tblResultados.find('tr').eq(countRow + 1).find('td').eq(0).find('#btnGuardarMantenimiento');
        var accionEditar = tblResultados.find('tr').eq(countRow + 1).find('td').eq(0).find('#btnEditarMantenimiento');
        var accionRegresar = tblResultados.find('tr').eq(countRow + 1).find('td').eq(0).find('#btnRegresarMantenimiento');
        var codigoFormula = tblResultados.find('tr').eq(countRow + 1).find('td').eq(2);
        var descripcionParametro = tblResultados.find('tr').eq(countRow + 1).find('td').eq(3);
        var descripcionFormula = tblResultados.find('tr').eq(countRow + 1).find('td').eq(4);
        var combinacionFormula = tblResultados.find('tr').eq(countRow + 1).find('td').eq(5);
         
        accionGuardar[0].style.display = 'inherit';
        accionEditar[0].style.display = 'none';
        accionRegresar[0].style.display = 'inherit';
        codigoFormula.html("");
        
        descripcionFormula.attr('contentEditable', true);
        combinacionFormula.attr('contentEditable', true);
        descripcionFormula.attr('style', 'text-align: center; vertical-align: bottom; border: #A9BCF5 solid 2px !important;');
        combinacionFormula.attr('style', 'text-align: center; vertical-align: bottom; border: #A9BCF5 solid 2px !important;');
	});
}

function cargarGrillaResultado (){
	tblResultados.bootstrapTable({
		data : [],
		exportDataType : 'all',
		pagination : false,
		buttonsAlign : 'right',
		exportOptions: {
             fileName: 'Fórmula',
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
			formatter: 'crearAccionesMantenimientoFormula',
			class: 'controls',
			events : operateEvents,
			cellStyle : estiloAcciones
		},{
			field : 'descripcionOrden',
			title : 'Orden',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'codigoFormula',
			title : 'Código de Formula',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			formatter: 'formatoNro',
		},{	
			field : 'descripcionParametro',
			title : 'Tipo',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'descripcionFormula',
			title : 'Nombre de Fórmula',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'combinacionFormula',
			title : 'Fórmula',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		}]
	});
}

function cargarGrillaVariables(){
	tblVariables1.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables2.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables3.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
	
	tblVariables4.bootstrapTable({
		data : [],
		pagination : false,
		buttonsAlign : 'left',
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},		
		filter: false,
		detailFilter : false,
		sortable : false,
		columns : [{
			field : 'descripcionConcat',
			title : 'Descripcion',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : false
		}]
	});
}

function estiloAcciones (value, row, index) {	   
	return {
	    css: {
	      'width' : '100px'
	    }
	};		    	     
}

function estiloCelda (value, row, index) {	   
	return colorCelda("#e8f2fb");			    	     
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

window.operateEvents = {
		'click #btnEditarMantenimiento': function (e, value, row, index) {
			flagNuevo = 1;
			modo = ConstanteServices.ESTADO_OPCION_EDITAR;
			
			var accionGuardar = tblResultados.find('tr').eq(index + 1).find('td').eq(0).find('#btnGuardarMantenimiento');
	        var accionEditar = tblResultados.find('tr').eq(index + 1).find('td').eq(0).find('#btnEditarMantenimiento');
	        var accionRegresar = tblResultados.find('tr').eq(index + 1).find('td').eq(0).find('#btnRegresarMantenimiento');
			var descripcionOrden = tblResultados.find('tr').eq(index + 1).find('td').eq(1);
			var codigoFormula = tblResultados.find('tr').eq(index + 1).find('td').eq(2);
	        var descripcionFormula = tblResultados.find('tr').eq(index + 1).find('td').eq(4);
	        var combinacionFormula = tblResultados.find('tr').eq(index + 1).find('td').eq(5);
			
			var htmlCombo = '<select class="form-control input-sm" id="cboOrden">';			
			for (var i = 0; i < ltaOrden.length; i++) {
			    if(ltaOrden[i].idDetalleGeneral == row.tipoOrden){
			    	htmlCombo += '<option selected value = "'+ltaOrden[i].idDetalleGeneral+'">'+ltaOrden[i].descripcion+'</option>'
				} else {
					htmlCombo += '<option value = "'+ltaOrden[i].idDetalleGeneral+'">'+ltaOrden[i].descripcion+'</option>'
				}			    
			}
			htmlCombo +='</select>';
			
			accionGuardar[0].style.display = 'inherit';
	        accionEditar[0].style.display = 'none';
	        accionRegresar[0].style.display = 'inherit';
	        if(descripcionOrden[0].innerHTML == "PRINCIPAL"){
	        	existPrincipal = 0;
	        }
			descripcionOrden.html(""+htmlCombo);
			codigoFormula.html("");
			descripcionFormula.attr('contentEditable', true);
	        combinacionFormula.attr('contentEditable', true);
	        descripcionFormula.attr('style', 'text-align: center; vertical-align: bottom; border: #A9BCF5 solid 2px !important;');
	        combinacionFormula.attr('style', 'text-align: center; vertical-align: bottom; border: #A9BCF5 solid 2px !important;');
			
		},
		'click #btnAnularMantenimiento': function (e, value, row, index) {				
			var html = 
			'<div class="row">' + 
				'<div class="col-sm-12">' + 
					'<label class="control-label">Se anulará el Registro. ¿Desea continuar?</label>' + 
				'</div>' + 
			'</div>';
			var titulo = 'Anulaci&oacute;n de Formula N°<font class="text-danger"><b>' + (index + 1) + '</b></font>';
			modal.defaultModal(html, 'anularFormula(' + row.idFormulaxParametro +')' , '', titulo, 'Aceptar', 'Cancelar');
		},
		'click #btnGuardarMantenimiento': function (e, value, row, index) {
			if(row != null) {
				var obj = {};
				var idFormulaxParametro = row.idFormulaxParametro;
				var tipoOrden = tblResultados.find('tr').eq(index + 1).find('td').eq(1)[0].children[0].value;
				var tipoOrdenString = tipoOrden.toString();
				var tipoFormula = cboParametro.val();
				var nombreFormula = tblResultados.find('tr').eq(index + 1).find('td').eq(4).html();
				var formula = tblResultados.find('tr').eq(index + 1).find('td').eq(5).html();

				if(validarFormularioDatos(idFormulaxParametro, tipoOrdenString, tipoFormula, nombreFormula, formula)){
					var tituloModal = 'Mantenimiento Fórmulas';
					modal.confirmer('¿Esta seguro de Grabar Datos de '+tituloModal+' ?', 'grabarFormula('+idFormulaxParametro+','+tipoOrdenString+',"'+tipoFormula+'","'+nombreFormula+'","'+formula+'")', '', tituloModal);
				}
			}
		},
		'click #btnAgregarMantenimiento': function (e, value, row, index) {
			if(row != null) {
				$.redirect('./cargarVentanaFormula', {mantenimientoBean: JSON.stringify(row), modo:  ConstanteServices.ESTADO_OPCION_VER,filtro:obtenerFiltros(), idDetalleGeneral : row.idDetalleGeneral });
			}
		},
		'click #btnRegresarMantenimiento': function (e, value, row, index) {
			flagNuevo = 0;
			buscarFormula();
		}
	}


function crearCboOrden(value, row, index) {
	if(row != null) {
		var htmlCombo = '<select class="form-control input-sm" id="cboOrden">';
		for (var i = 0; i < ltaOrden.length; i++) {
		    if(ltaOrden[i].idDetalleGeneral == row.tipoOrden){
		    	htmlCombo += '<option value = "'+ltaOrden[i].idDetalleGeneral+'">'+ltaOrden[i].descripcion+'</option>'
			} else {
				htmlCombo += '<option value = "'+ltaOrden[i].idDetalleGeneral+'">'+ltaOrden[i].descripcion+'</option>'
			}			    
		}
		htmlCombo +='</select>'			
		return [htmlCombo].join('');
	} else {
		return [''].join('');
	}
}

function crearAccionesMantenimientoFormula(value, row, index) {			
	if(row != null ) {	
		return [
			'<button id="btnGuardarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Guardar" style="display:none;">',
				'<i class="glyphicon glyphicon-floppy-disk"></i>',
			'</button>',
			'<button id="btnEditarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Editar">',
				'<i class="fa fa-pencil-square-o"></i>',
			'</button>', 
			'<button id="btnAnularMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Anular">',
				'<i class="fa fa-times"></i>',
			'</button>',
			'<button id="btnRegresarMantenimiento" class="control-table tooltip-r" data-toggle="tooltip" data-placement="left" title="Cancelar" style="display:none;">',
				'<i class="fa fa-reply"></i>',
			'</button>'
        ].join('');	
    } else {
    	return [''].join('');	
    }		
}

function validarFormularioBusqueda(){
    var estado = true;
     if(IsEmpty(cboParametro.val())){           
       	 mostrarMensaje('Debe Seleccionar un Parametro.', ConstanteServices.IMAGEN_DANGER); 
         estado = false; 
     }
    return estado;
}

function buscarVariable() {
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarVariableActivo",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idDetalleGeneral: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = JSON.parse(respuestaBean.parametros.ListaResultado);
	        	ltaVariable = ListaResultado;
	        	if(ListaResultado != null && ListaResultado != "[]") {
	        		var indice = Math.ceil(ListaResultado.length/4);
	        		var listaAux = [];
	        		for(i = 0; i < indice; i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables1.bootstrapTable('load', listaAux);
	        		
	        		var listaAux = [];
	        		for(i = indice; i < (indice*2); i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables2.bootstrapTable('load', listaAux);
	        		var listaAux = [];
	        		for(i = (indice*2); i < (indice*3); i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables3.bootstrapTable('load', listaAux);
	        		var listaAux = [];
	        		for(i = (indice*3); i < ListaResultado.length; i++){
	        			listaAux.push(ListaResultado[i]);
	        		}
	        		tblVariables4.bootstrapTable('load', listaAux);
	        	} else {
	        		tblVariables1.bootstrapTable('load', []);
	        		tblVariables2.bootstrapTable('load', []);
	        		tblVariables3.bootstrapTable('load', []);
	        		tblVariables4.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron Variables registradas en el sistema.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}

function buscarFormula() {
	if(validarFormularioBusqueda()) {
		$.ajax({
	        url : "./buscarFormula",
	        type : "POST",
	        dataType : "json",
	        async:false,
	        data : {
	        	idDetalleGeneral: (!IsEmpty(cboParametro.val()) ? cboParametro.val() : null)
	        }
	    }).done(function(respuestaBean) {
	    	mostrarMensaje();
	    	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK ){	      	
	        	var ListaResultado = respuestaBean.parametros.ListaResultado;
	        	if(ListaResultado != null && ListaResultado != "[]") {		            		
	        		existPrincipal = 0;
	        		var Lista = JSON.parse(ListaResultado)
	        		Lista.forEach(function(element) {
	        			  if(element.tipoOrden == ConstanteServices.TIPO_FORMULA_PRINCIPAL){
	        				  existPrincipal = 1;
	        			  }
	        		});
	        		tblResultados.bootstrapTable('load', JSON.parse(ListaResultado));
	        	} else {
	        		tblResultados.bootstrapTable('load', []);
			        mostrarMensaje('No se encontraron resultados coincidentes para los criterios ingresados.', ConstanteServices.IMAGEN_DANGER);		            		
	        	}
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
	        }
	    }).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	    });	
	} 	
}

function obtenerFiltros(){
    var formFiltro = {		    
        'cboParametro': cboParametro.val()
    }
    return JSON.stringify(formFiltro);
} 

function cargarFiltros(){
	if(filtro != undefined && filtro != null && filtro != "" && filtro != "{}"){
		var filtroObjecto = JSON.parse(filtro);
		$("#cboParametro").val(""+filtroObjecto.cboParametro);
		buscarFormula();
	}	
    if(!IsEmpty(mensajeRespuestaServer)){							
    	mostrarMensaje(mensajeRespuestaServer , ConstanteServices.IMAGEN_SUCCESS);
	} 
}

function grabarFormula(idFormulaxParametro, tipoOrden, tipoFormula, nombreFormula, formula){
	$.ajax({
		url: "./registrarFormula",
		type: "POST",
		dataType: "json",
		cache: false,
		data : {
			 modo    		: modo,	
			 mantenimientoBean	: JSON.stringify(obtenerObjFormula(idFormulaxParametro, tipoOrden, tipoFormula, nombreFormula, formula)),
			 filtro : obtenerFiltros()
		}
	}).done(function(respuestaBean) {
	    if(respuestaBean.estadoRespuesta == ConstanteServices.OK){
        	    buscarFormula();
        	    buscarVariable();
        	    flagNuevo = 0;
        } else {
            	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstanteServices.IMAGEN_DANGER);
         }
		
	}).fail(function( jqXHR, textStatus, errorThrown ) {
		validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstanteServices.MENSAJE_SESION_EXPIRADA);
	});  
}

function obtenerObjFormula(idFormulaxParametro, tipoOrden, tipoFormula, nombreFormula, formula){    
	var obj = {};
	obj.idFormulaxParametro = !IsEmpty(idFormulaxParametro) ? idFormulaxParametro : null;
	obj.idParametro = !IsEmpty(tipoFormula) ? tipoFormula : null;
	obj.tipoOrden = tipoOrden;
	obj.descripcionFormula = nombreFormula;
	obj.combinacionFormula   = formula;
	
    return obj;
}

function anularFormula(idFormulaxParametro) {
	if(!IsEmpty(idFormulaxParametro)) {
		$.ajax({
            url : "./anularFormula",
            type : "POST",
            dataType : "json",
            data : {
            	idFormulaxParametro : idFormulaxParametro             	
            }
        }).done(function(respuestaBean) {
        	if(respuestaBean.estadoRespuesta ==  ConstanteServices.OK){	
        		buscarFormula();		
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

function validarFormularioDatos(idFormulaxParametro, tipoOrdenString, tipoFormula, nombreFormula, formula){
    var estado = true;            
    mostrarMensaje();
    if(IsEmpty(tipoFormula))
    {
    	mostrarMensaje('El campo Orden es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    if(IsEmpty(tipoFormula))
    {
    	mostrarMensaje('El campo Tipo de Fórmula es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }  
    if(IsEmpty(nombreFormula))
    {
    	mostrarMensaje('El campo Nombre de Fórmula es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }  
    if(IsEmpty(formula))
    {
    	mostrarMensaje('El campo Fórmula es obligatorio', ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    
    var rptaValidacion = validarFormulaIngreso(ltaVariable, formula);
    if(!IsEmpty(rptaValidacion)){
    	mostrarMensaje(rptaValidacion, ConstanteServices.IMAGEN_DANGER);
    	estado = false;
    	return estado;
    }
    return estado;    
}

$(document).ready(function(){
    var prueba = $("#tablaContenido");
    var tabla = prueba.find(".pull-left");
    tabla.removeClass("bars pull-left pull .pull");
    tabla.addClass("col-md-11");
    
    var alert = $("#divMensajeInformacion").find(".alert")
    alert.css("margin", "0px");
    alert.css("padding", "0px");
});
        

$(document).bind('DOMSubtreeModified', function() {
	var alert = $("#divMensajeInformacion").find(".alert")
	alert.css("margin", "0px");
	alert.css("padding", "0px");
	if(flagNuevo == 1 && !IsEmpty(document.getElementById("btnEditarMantenimiento")) && !IsEmpty(document.getElementById("btnAnularMantenimiento"))){
		document.getElementById("btnNuevoFormula").disabled = true;
		var collectEditar = btnEditarMantenimiento;
		for(var i = 0; i < collectEditar.length; i++){
			collectEditar[i].disabled = true;
		}
		var collectAnular = btnAnularMantenimiento;
		for(var i = 0; i < collectAnular.length; i++){
			collectAnular[i].disabled = true;
		}
		$('#btnShowMenu').removeClass('btn-show');
		$('#btnShowMenu').addClass('btn-show enlace_desactivado');
	}
	if(flagNuevo == 0 && !IsEmpty(document.getElementById("btnNuevoFormula")) && !IsEmpty(document.getElementById("btnEditarMantenimiento")) && !IsEmpty(document.getElementById("btnAnularMantenimiento"))){
		document.getElementById("btnNuevoFormula").disabled = false;
		var collectEditar = btnEditarMantenimiento;
		for(var i = 0; i < collectEditar.length; i++){
			collectEditar[i].disabled = false;
		}
		var collectAnular = btnAnularMantenimiento;
		for(var i = 0; i < collectAnular.length; i++){
			collectAnular[i].disabled = false;
		}
		$('#btnShowMenu').removeClass('btn-show enlace_desactivado');
		$('#btnShowMenu').addClass('btn-show');
	}
	if(!IsEmpty(document.getElementById("cboOrden"))){
		if(existPrincipal == 1){
			
		}
	}
});

