/* Colocar javascript compartida específico de la aplicacion web */
const OPERATION_TYPE_REPORTE_RESUMEN = '1';
const OPERATION_TYPE_PROCESO_AUTOMATICO = '2';
const OPERATION_TYPE_UNSELECTED = '';

const OPERATION_TYPE_CORREO_FUENTE_ENVIO = '3';
const OPERATION_TYPE_REPORTE_GERENCIA = '4';


//PARA REPORTE RESUMEN DE REPRESAS
const REPORTE_REPRESAS_TYPE_REPRESA = 1;
const REPORTE_REPRESAS_TYPE_ALMACENAMIENTO = 2;
const REPORTE_REPRESAS_TYPE_VOLUMENTOTAL = 3;
const REPORTE_REPRESAS_TYPE_AFORORIO = 4;
const REPORTE_REPRESAS_TYPE_MANIOBRAS = 5;
const REPORTE_REPRESAS_TYPE_AGREGADOS = 6;
//CONSTANTES PARA RUTA COMPARTIDA DEFAULT
const CONF_CORREO_EXTRACCION_RUTA = 'repofile';
const CONF_CORREO_EXTRACCION_USER = 'user';
const CONF_CORREO_EXTRACCION_PASSWORD = 'password';


const HTTP_STATUS_OK = 200;
const HTTP_BAD_REQUEST = 400;
const HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;


//const REGEX_MAIL_VALIDATION = /\S+@\S+\.\S+/;
//const REGEX_MAIL_VALIDATION = /^(([^<>()\[\]\\.,;:ñÑ\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{1,}))$/;
const REGEX_MAIL_VALIDATION = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
const REGEX_ALL_WITHOUT_DESC = /[^A-Za-z0-9áéíóúÁÉÍÓÚñÑ .,#&/()<>;-\s]/gi;
const REGEX_ALL_WITHOUT_DESC_LIST = /[^A-Za-z0-9áéíóúÁÉÍÓÚñÑ\✓ .,#&/()<>;-\s]/gi;


var AREAS_CARGADAS = {};
var ACTIVIDADES_CARGADAS = {};
var SUBACTIVIDADES_CARGADAS = {};
var UNIDAD_MEDIDAS_CARGADAS = {};



//FUNCIONES GENERICAS

//LIMPIAR FILES PARA QUE FUNCIONE EN DIVERSOS BROWSERS
//IMPOTANTE: PARA USARLO IMPORTAR EN EL HTML EL PLUGIN jquery.browser.min.js
function clearInputTypeFile(idInputFile) {
	$fileInput = $('#' + idInputFile);
	if($.browser.msie){
		$fileInput.replaceWith( $fileInput = $fileInput.clone( true ) );
 } else {
 	$fileInput.val('');
 }
}

function validationNumericWithDecimals(evt){
	  if(evt.which == 0 || evt.which ==8){
			return true;
		}
		var theEvent = evt || window.event;
	    var theVal = theEvent.target.value;
	    var key = theEvent.keyCode || theEvent.which;

	    key = String.fromCharCode(key);

	    if (key.length == 0) return;
	
    var regex = /^\d{1,9}(\.\d{1,2})?$/;
    var isValid = regex.test(key);
    
    console.log("isValid: " + isValid)
    if (!isValid) {
        if (theEvent.preventDefault) theEvent.preventDefault();
        return false;
    }
    return true;
}

function ValidationCorreo(value, show) {
	this.value = value;
    this.show = show;
}

function pad(str, max) {
	str = str.toString();
	return str.length < max ? pad("0" + str, max) : str;
}

// MENSAJES VALIDACION
const MSG_VALID_ABREV = "La abreviatura ya se encuentra registrada";


function cargarCombo(urlJson,idSelect){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$.unblockUI();
	});
}

function cargarComboSinLoader(urlJson,idSelect){
	console.log("cargando combo");
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
	});
}



function cargarComboValue(urlJson,idSelect, valueSel){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$(idSelect).val(valueSel);
		$.unblockUI();
	});
}

function cargarComboSelectable(urlJson,idSelect){
	MYAPPL.blockPageLoad();
	$.ajax({
		url : urlJson,
		type : 'GET',
		dataType : "json",
		error : function(xhr, status) {
			
		},
		complete : function(xhr, status) {
			if(status == 'success' || status=='notmodified')
			{
				var data =  $.parseJSON(xhr.responseText);
				var html = '';
				var len = data.length;
				html += '<option data-id="" value="">-Seleccione-</option>';
				for ( var i = 0; i < len; i++) {
					html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
							+ data[i].label + '</option>';
				}
				html += '</option>';
				$(idSelect).html(html);
			}
			$.unblockUI();
		}
	});
}

function cargarComboTodos(urlJson,idSelect){
	MYAPPL.blockPageLoad();
	$.ajax({
		url : urlJson,
		type : 'GET',
		dataType : "json",
		error : function(xhr, status) {
			
		},
		complete : function(xhr, status) {
			if(status == 'success' || status=='notmodified')
			{
				var data =  $.parseJSON(xhr.responseText);
				var html = '';
				var len = data.length;
				html += '<option value="-1">-Todos-</option>';
				for ( var i = 0; i < len; i++) {
					html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
							+ data[i].label + '</option>';
				}
				html += '</option>';
				$(idSelect).html(html);
			}
			$.unblockUI();
		}
	});
}

function cargarComboRelacional(urlJson,idSelect){
	MYAPPL.blockPageLoad();
	$.ajax({
		url : urlJson,
		type : 'GET',
		dataType : "json",
		error : function(xhr, status) {
			
		},
		complete : function(xhr, status) {
			if(status == 'success' || status=='notmodified')
			{
				console.log("lenght: "+ xhr)
				var data =  $.parseJSON(xhr.responseText);
				var html = '';
				var len = data.length;
				if(len<1){
					return;
				}
				html += '<option>-Seleccione-</option>';
				for ( var i = 0; i < len; i++) {
					html += '<option title="'+ data[i].label +'" data-idMaster="'+ data[i].idMaster + '"  data-idDetalle="'+ data[i].idDetalle +'" value="' + data[i].value + '">'
					+ data[i].label + '</option>';
				}
				html += '</option>';
				$(idSelect).html(html);
			}else{
				$(idSelect).html("");
			}
			
			$.unblockUI();
		}
	});
	
}

function armarCbo(data,idSelect){
	var html = '';
	var len = data.length;
	for ( var i = 0; i < len; i++) {
		html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
		+ data[i].label + '</option>';
	}
	html += '</option>';
                //now that we have our options, give them to our select
	$(idSelect).html(html);
}

function armarCboSel(data,idSelect, valueSel){
	var html = '';
	html += '<option value="0">-Seleccione-</option>';
	var len = data.length;
	for ( var i = 0; i < len; i++) {
		html += '<option title="'+ data[i].label +'" data-defecto="'+ data[i].defecto +'" data-custom="'+ data[i].custom + '"  data-id="'+ data[i].id +'" value="' + data[i].value + '">'
		+ data[i].label + '</option>';
	}
	html += '</option>';
                //now that we have our options, give them to our select
	$(idSelect).html(html);
	$(idSelect).val(valueSel);
}

//<span class="glyphicon glyphicon-plus"></span>

function armarCboRelacional(data,idSelect){
	var html = '';
	var len = data.length;
	for ( var i = 0; i < len; i++) {
		html += '<option title="'+ data[i].label +'" data-idMaster="'+ data[i].idMaster + '"  data-idDetalle="'+ data[i].idDetalle +'" value="' + data[i].value + '">'
		+ data[i].label + '</option>';
	}
	html += '</option>';
                //now that we have our options, give them to our select
	$(idSelect).html(html);
}


function getJSON(urlJson){
var datos;
$.ajax({
	url : urlJson,
	type : 'GET',
	dataType : "json",
	success : function(dataServer) {
		datos = dataServer;
	},
	complete : function(xhr, status) {
		return datos;
	}
});

return datos;
}


function showToastGeneric(mensajeTipo, mensaje, mensajeError) {
	//Mensajes despues de grabar
	if(mensajeTipo==='grabadoOk'){
		 toastr["success"](mensajeError, mensaje);
//		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='grabadoNoOk'){
		 toastr["error"](mensajeError, mensaje);
	 }
	 //Mensajes despues de eliminar
	 if(mensajeTipo==='inactivadoOk'){
		 toastr["success"](mensajeError, mensaje);
//		 $('#modal-registra-clima').modal('hide');
//		 $('#idBtnFormBuscarClima').click();
	 }
	 if(mensajeTipo==='inactivadoNoOk'){
		 toastr["error"](mensajeError, mensaje);
	 }
}

//SOLO PERMITE INGRESAR NUMEROS Y GUIONES
function soloNumerosGuiones(e){
	  tecla = (document.all) ? e.keyCode : e.which;
	  tecla = String.fromCharCode(tecla)
	  return /^[0-9\-]+$/.test(tecla);
	}

function printGridsInquiriesCustomized(div, title) {

	var isOSSystem = false;
	var isSafariBrowser = false;
	var isAndroid = false;

	var htmlContent = div.html();
	var stylesCssForPrint = $('#stylesTemplate').html();

	var cssDependencies = [ "/scr/css/myTheme.css",
			"/scr/css/defaultTheme.css",
			"/main/resources/css/bootstrap-select.min.css",
			"/scr/webjars/bootstrap/3.3.7/css/bootstrap.min.css",
			"/scr/webjars/bootstrap/3.3.7/css/bootstrap-theme.min.css",
   	        "/scr/webjars/jquery-ui/1.12.1/jquery-ui.min.css",
   	        "/scr/webjars/bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css",
			"/scr/css/bootstrap-datetimepicker.min.css",
			"/scr/webjars/bootstrap-select/1.12.2/dist/css/bootstrap-select.min.css",
			"/scr/css/jquery.dataTables.min.css",
			"/scr/css/jquery-confirm.min.css",
			"/scr/css/main-app.css"
   	                    ];

	var scriptsDependencies = [ "/scr/webjars/jquery/3.2.1/jquery.min.js",
			"/scr/webjars/bootstrap/3.3.7/js/bootstrap.min.js",
			"/scr/webjars/toastr/2.1.3/toastr.min.css",
			"/scr/webjars/toastr/2.1.3/toastr.min.js",
			"/scr/webjars/jquery-ui/1.12.1/jquery-ui.min.js",
			"/scr/js/main-constants.js",
			"/scr/js/main-app-constants.js",
			"/scr/js/main.js",
			"/scr/webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js",
			"/scr/webjars/bootstrap-select/1.12.2/dist/js/bootstrap-select.js",
			"/scr/js/jquery.dataTables.min.js",
			"/scr/js/jquery.blockUI.js",
			"/scr/webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js",
			"/scr/webjars/bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js",
			"/scr/js/jquery.dataTables.min.js",
			"/scr/js/jquery-confirm.min.js",
			"/scr/js/main-app.js"
			];

	var mywindow = window.open('', 'Home', 'height=700,width=1000');

	mywindow.document.head.innerHTML = '';
	mywindow.document.body.innerHTML = '';

	mywindow.document.write('<!doctype html><html><head><title>'+title+'</title>');
	    
	    // Seteo de dependencias js
	    for (var i in scriptsDependencies) {
	    
	    var scriptDep = scriptsDependencies[i];
	        
	    mywindow.document.write('<script ');
	    mywindow.document.write('src="' + scriptDep + '"/>');
	    mywindow.document.write('</'+'script'+'>');
	  }
	    
	     // Seteo de dependencias css
	    for (var i in cssDependencies) {
	    var cssDep = cssDependencies[i];  
	    mywindow.document.write('<link rel="stylesheet" href="'+ cssDep+'" type="text/css" />');
	  }

	mywindow.document.write('<style type="text/css" media="screen,print">');
	mywindow.document.write(stylesCssForPrint);
	mywindow.document.write('</style>');

	// metodo interval
	mywindow.document.writeln('    <script>');
	mywindow.document.writeln('       var interval = setInterval(function(){');
	mywindow.document.writeln('          if(document.getElementById("grid-body-print")){');
	mywindow.document.writeln('          if('+isOSSystem+'){');
	mywindow.document.writeln('          		var d = document.getElementById(\'headerColDynamic\'); if(d.className != null && d.className!=undefined){d.className = \'head-tabl\'}; setTimeout(function(){window.focus();window.print() ,1000});');
	mywindow.document.writeln('          } else if('+isSafariBrowser+' || '+isAndroid+'){');
	mywindow.document.writeln('					var d = document.getElementById(\'headerColDynamic\'); if(d.className != null && d.className!=undefined){d.className = \'head-tabl\'}; window.focus(); setTimeout(function(){window.print(), window.close(),1000});');
	mywindow.document.writeln('			 } else { ');
	mywindow.document.writeln('			 	var d = document.getElementById(\'headerColDynamic\'); if(d.className != null && d.className!=undefined){d.className = \'head-tabl\'}; setTimeout(function(){window.focus();window.print(); window.close(),1000});');
	mywindow.document.writeln(' 		 } ');
	mywindow.document.writeln('             clearInterval(interval)');
	mywindow.document.writeln('          }');
	mywindow.document.writeln('       },750)');
	mywindow.document.writeln('    </script>');
	// fin metodo interval

	mywindow.document.write('</head><body style="padding:20px">');
	mywindow.document.write(htmlContent);
	mywindow.document.write('</body></html>');
	mywindow.document.close();
}

function cargarComboSameValue(urlJson,idSelect){
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option data-defecto="'+ data[i].defecto + '" value="' + data[i].label + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$.unblockUI();
	});
}

function soloNumeros(e){

    // capturamos la tecla pulsada
    var teclaPulsada=window.event ? window.event.keyCode:e.which;

    // capturamos el contenido del input
    var valor=document.getElementById("num").value;

    // 45 = tecla simbolo menos (-)
    // Si el usuario pulsa la tecla menos, y no se ha pulsado anteriormente
    // Modificamos el contenido del mismo aÃ±adiendo el simbolo menos al
    // inicio
    if(teclaPulsada==45 && valor.indexOf("-")==-1)
    {
        document.getElementById("num").value="-"+valor;
    }

    // 13 = tecla enter
    // 46 = tecla punto (.)
    // Si el usuario pulsa la tecla enter o el punto y no hay ningun otro
    // punto
    if(teclaPulsada==13 || (teclaPulsada==46 && valor.indexOf(".")==-1))
    {
        return true;
    }

    // devolvemos true o false dependiendo de si es numerico o no
    return /\d/.test(String.fromCharCode(teclaPulsada));
}

//FUNCION PARA QUE SOLO PERMITA INGRESAR NUMEROS INCLUYE PUNTO 
soloNumerosTmp = function (e) {
    var keynum = window.event ? window.event.keyCode : e.which;
    if ((keynum == 8) || (keynum == 45)){
    	return true;
    }
     
    return /\d/.test(String.fromCharCode(keynum));
};


function getDDMMYYYY(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	today = dd+'/'+mm+'/'+yyyy;
	console.log("today " + today)
	return today;
}

function getDDMMYYYYhhmmss(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var hh = today.getHours();
	var min = today.getMinutes();
	var ss = today.getSeconds();

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	if(hh<10){
	    hh='0'+hh;
	} 	
	if(min<10){
		min='0'+min;
	}	
	if(ss<10){
		ss='0'+ss;
	}		
	today = dd+'/'+mm+'/'+yyyy+' '+hh+':'+min+':'+ss;
	console.log("today " + today)
	return today;
}

function getHhmmss(){
	var today = new Date();
	var hh = today.getHours();
	var min = today.getMinutes();
	var ss = today.getSeconds();

	if(hh<10){
	    hh='0'+hh;
	} 	
	if(min<10){
		min='0'+min;
	}	
	if(ss<10){
		ss='0'+ss;
	}		
	today = hh+':'+min+':'+ss;
	console.log("today hour: " + today)
	return today;
}

function getHhmm(){
	var today = new Date();
	var hh = today.getHours();
	var min = today.getMinutes();
	var ss = today.getSeconds();

	if(hh<10){
	    hh='0'+hh;
	} 	
	if(min<10){
		min='0'+min;
	}	
		
	today = hh+':'+min;
	console.log("today hour: " + today)
	return today;
}

function cargarComboForField(urlJson,idSelect){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option value="' + data[i].id + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$.unblockUI();
	});
}


function cargarComboForFieldSinLoader(urlJson,idSelect){
	console.log("cargando combo");
	MYAPPL.blockPageLoad();
	$.getJSON(urlJson, {
		ajax : 'true'
	}, function(data) {
		var html = '';
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			html += '<option  data-id="'+ data[i].id +'" value="' + data[i].id + '">'
					+ data[i].label + '</option>';
		}
		html += '</option>';
                    //now that we have our options, give them to our select
		$(idSelect).html(html);
		$.unblockUI();
	});
}


(function($) {

	let app = MYAPPL.createNS("MYAPPL.LOGIC.COMMONDIST");

	app.modalParams = {}

	app.setModalParams = function($modal, $padre, $title, $idhidden, $idtext,
			$paginado, $filtro, $busqueda) {
		let $params = {
			modal : $modal,
			idPadre : $padre,
			paginado : $paginado === undefined ? false : $paginado,
			filtro : $filtro === undefined ? false : $filtro,
			busqueda : $busqueda === undefined ? false : $busqueda,
			title : $title,
			hidden : $idhidden,
			text : $idtext
		}
		return $params;
	};

	app.paramsDatatable = {
		descripcion : '',
		abreviatura : ''
	};

	app.addCustomModalOpenClass = function($id) {
		$($id).on('shown.bs.modal', function() {
			$('body').addClass('custom-modal-open');
		}).on("hidden.bs.modal", function() {
			$('body').removeClass('custom-modal-open')
		});
	};

	app.showModal = function($params) {
		MYAPPL.blockPageLoad();
		$.ajax({
			type : 'GET',
			url : 'modal',
			success : function(result) {
				app.modalParams = $params;
				$("#form-modal-common-popup").replaceWith(result);
				$('#modal-common-popup').modal('show');
				$('#modal-common-popup').appendTo('body');
			},
			error : function() {
				MYAPPL.showToast('cargaNoOk', null, null);
			},
			complete : function() {
				$.unblockUI();
				MYAPPL.removeInputEnter();
			}
		});
	};

	app.dataTable = function() {
		let $table = $('#common-popup-grid').DataTable({
			autowidth : false,
			dom : app.modalParams.paginado ? 'rtilp' : 'rt',
			info : app.modalParams.paginado,
			lengthChange : false,
			language : {
				emptyTable : "Sin datos a mostrar"
			},
			pageLength : 10,
			processing : true,
			ordering : app.modalParams.paginado,
			searching : false,
			serverSide : true,
			ajax : {
				url : 'modal/pagination',
				type : "POST",
				data : {
					modal : app.modalParams.modal,
					idPadre : app.modalParams.idPadre,
					filtro : app.modalParams.filtro,
					paginado : app.modalParams.paginado,
					descripcion : app.paramsDatatable.descripcion
				},
				error : function() {
					$('#common-popup-grid_processing').css("display", "none");
				}
			},
			columns : [{
				data : 'descripcion'
			}, {
				data : 'abreviatura'
			}],
			columnDefs : [{
				className : "dt-body-left dt-body-width-300 dt-body-pad-left",
				targets : [0],
				visible : true,
				orderable : app.modalParams.paginado
			}, {
				className : "dt-body-left dt-body-width-190 dt-body-pad-left",
				targets : [1],
				visible : false,
				orderable : app.modalParams.paginado
			}]
		});
		return $table;
	};

	app.disableFilter = function() {
		if (!app.modalParams.busqueda) {
			$('#modal-search').hide();
		}
	};

	app.selectRow = function() {
		$('#common-popup-grid tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				$('#common-popup-grid tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});
	};

	app.search = function($table) {
		$table.destroy();
		$table = app.dataTable();
		return $table;
	};

	app.send = function($table) {
		let $info = $table.row('.selected').data();
		let $continue = true;
		if ($info === undefined) {
			$continue = false;
		}
		if ($continue) {
			$(app.modalParams.hidden).val($info.codigo).trigger('change');
			$(app.modalParams.text).val($info.descripcion).trigger('change');

			$("#modal-common-popup").modal('hide');
		} else {
			MYAPPL.showToast('genericNoOk', 'Por favor seleccione un registro');
		}
		return $info;
	};

	app.getJSON = function($url, $id, $value) {
		$.getJSON($url).done(function(data) {
			let $html = app.renderSelectItem(data, $value);
			$($id).html($html);
		});
	};

	app.getJSON2 = function($url, $id, value) {
		$.getJSON($url, {
			ajax : 'true'
		}).done(
				function(data) {
					let html = '<option value="">-SELECCIONE- </option>';
					const len = data.length;
					for (let i = 0; i < len; i++) {
						html += '<option value="'
								+ data[i].value
								+ '" '
								+ (value == data[i].value
										? 'selected="selected"'
										: '') + '>' + data[i].label;
						html += '</option>';
					}
					$($id).html(html);

				});
	};

	app.getJSONParam = function($url, $paramName, $paramValue) {
		$.getJSON($url + '?').done(function(data) {
			let $html = app.renderSelectItem(data, $value);
			$($id).html($html);
			$html == '1' ? 1 : 0;
		});
	}

	app.getListEstadoDocumento = function($id, $tipoDocumento) {
		$.getJSON('lstEstadoDocumentoPerfil?tipoDocumento=' + $tipoDocumento,
				function(data) {
					let $html = app.renderSelectItem(data);
					$($id).html($html);
				});
	};

	app.getListPlanta = function($id, $equipo) {
		if ($equipo !== undefined) {
			$.getJSON('lstPlanta?equipo=' + $equipo, function(data) {
				let $html = app.renderSelectItem(data);
				$($id).html($html);
			});
		} else {
			var html = '<option value="0">-SELECCIONE-</option>';
			$($id).html(html);
		}
	}

	app.getListEquipoPerfil = function($id, $value) {
		$.getJSON('listEquipoPerfil', function(data) {
			let $html = app.renderSelectItem(data, $value);
			$($id).html($html).trigger('change');
		});
	}

	app.getListEnlaceEquipo = function($id, $equipo) {
		if ($equipo !== undefined) {
			$.getJSON('listEnlaceEquipo?equipo=' + $equipo, function(data) {
				let $html = app.renderSelectItem(data);
				$($id).html($html);
			});
		} else {
			let html = '<option value="0">-SELECCIONE-</option>';
			$($id).html(html);
		}
	};

	app.getResiduoXAnios = function($id, $anioInicio, $anioFin) {
		if ($anioInicio !== undefined && $anioFin !== undefined) {
			$.getJSON('listResiduoXAnios?anioInicio=' + $anioInicio
					+ '&anioFin=' + $anioFin, function(data) {
				let $html = app.renderSelectItem(data);
				$($id).html($html);
			});
		} else {
			let html = '<option value="0">-SELECCIONE-</option>';
			$($id).html(html);
		}
	};

	app.getResiduoAnio = function($id, $anio) {
		if ($anio !== undefined) {
			$.getJSON('listResiduoAnio?anio=' + $anio, function(data) {
				let $html = app.renderSelectItem(data);
				$($id).html($html);
			});
		} else {
			let html = '<option value="0">-SELECCIONE-</option>';
			$($id).html(html);
		}
	};

	app.renderSelectItem = function($data, $value) {
		let html = '<option value="0">-SELECCIONE-</option>';
		let len = $data.length;
		for (var i = 0; i < len; i++) {
			html += '<option value="' + $data[i].value + '" ';
			html += (($value !== undefined && $data[i].value == $value)
					? 'selected="selected"'
					: '')
					+ '>';
			html += $data[i].label + '</option>';
		}
		return html;
	};

	app.serializeForm = function($array) {
		let $json = {};
		$.each($array, function() {
			if ($json[this.name] !== undefined) {
				if (!$json[this.name].push) {
					$json[this.name] = [$json[this.name]];
				}
				$json[this.name].push(this.value || '');
			} else {
				$json[this.name] = this.value || '';
			}
		});
		return $json;
	}

	app.enableExport = function($table, $button) {
		let $rows = $($table + ' tbody tr[role="row"]');
		let $cantCol = $rows.length;
		$($button).attr('disabled', $cantCol == 0);
	};

	app.getCurrentTimeStamp = function() {
		let $d = new Date();
		return "" + $d.getFullYear() + ("00" + ($d.getMonth() + 1)).slice(-2)
				+ ("00" + $d.getDate()).slice(-2)
				+ ("00" + $d.getHours()).slice(-2)
				+ ("00" + $d.getMinutes()).slice(-2)
				+ ("00" + $d.getSeconds()).slice(-2);
	};

	app.getContextPath = function() {
		let $path = window.location.pathname.substring(0,
				window.location.pathname.indexOf("/", 2));
		return $path;
	}

	app.configDoc = function($codigo, $page) {
		// $codigo es el codigo del documento generado
		// $page es id de la pagina registro o edicion
		let $doc = {
			id : $page,
			codDocumento : $codigo
		};
		return $doc;
	};
	
	app.getListAnios = function($tipoDocumento, $indAnioActual, $id, $value) {
		$.getJSON('listAnios?tipoDocumento=' + $tipoDocumento + '&anioActual=' + $indAnioActual).done(function(data) {
			let $html = app.renderSelectItem(data, $value);
			$($id).html($html);
			if ($value === undefined) {
				$($id).val((new Date()).getFullYear());
			}
		});
	};

	$(document).ready(function() {
		$('body').bind("cut copy paste", function(e) {
	      e.preventDefault();
	   });
		
		$('#modal-common-popup').on('show.bs.modal', function(event) {
			let title = app.modalParams.title === undefined ? 'un registro' : app.modalParams.title;
			let modal = $(this);
			modal.find('.modal-title').text('Seleccione ' + title + ' de la lista');
		});
	})

})(jQuery);