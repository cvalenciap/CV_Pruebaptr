
$(document).ready(function() {
	
	//MANEJO DEL FORMULARIO DE BUSQUEDA
	$('#idBtnBuscarTablaPoisson').click(function () {		 
		 $('#cboLambdasHidden').val($('#cboLambdas').val());
		 $('#myTableTablaPoisson').DataTable().ajax.reload();
	});
	
	loadMonthOnInput("#idFechaMuestreo");
	loadHourOnInput("#idHoraMuestreo");
	
	$.getJSON("listMuestreadores", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		
		for ( var i = 0; i < len; i++) {
			MUESTREADORES[data[i].id] = data[i].label;
		}
	});
	
	
	$.getJSON("listAnalistas", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			ANALISTAS[data[i].id] = data[i].label;
		}
	});
	
	$.getJSON("listTipoEnsayo2", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			METODOS[data[i].id] = data[i].label;
		}
	});
	
	$.getJSON("listPuntosEnsayo", {
		ajax : 'true'
	}, function(data) {
		var len = data.length;
		for ( var i = 0; i < len; i++) {
			PUNTO_MUESTRA[data[i].id] = data[i].label;
		}
	});
	
	
});

var ANALISTAS = {};
var MUESTREADORES = {};
var METODOS = {};
var PUNTO_MUESTRA = {};

function setValueToInputMuestreo(){
	
	var inputColumnFirst = '';
	var puntoValue = $("#cboPuntoMuestreo").val();
	var phValue = $("#ph").val();
	var temperatura = $("#inputTemperatura").val();
	
	inputColumnFirst = inputColumnFirst +puntoValue + "\n"
						+"Ph: " + phValue + "\n"
						+"Temperatura: " + temperatura
	console.log("inputColumnFirst: " + inputColumnFirst);				
						
	$("#labelPunto").text(puntoValue);
	$("#labelPh").text(phValue);
	$("#labelTemp").text(temperatura);
}

function generarReporte(target){
	var fecha = $(target).data("fecha");
	console.log("dia elegido : " + fecha)
	
	buscarReporteEnsayoXFecha(fecha)
}
//reporte-modal
function buscarReporteEnsayoXFecha(fechaDia){
	
	console.log("buscando valores ensayo");
	$.ajax({
		type: 'POST',
		url: 'reporteEnsayoCloroSearch',
		data: dataSearchXFecha(fechaDia),
		success: function(result){
			$('#id-modal-plan-bandeja-inactivar').html(result);
//			$('#modal-registra-parametroplan').modal('show');
			
//				cargarCombo("listComboMeses","#cboMeses");
//				calcularTotales();
//				dataRefrescarDatos();
		},
		complete: function() {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
			setTimeout(function(){
				cargarNombresTabla();
				$("#modal-report").modal("show");
				$("#idModalTitle").text("Reporte para fecha " + fechaDia);
				
			},200);
		}
	});
}



function cargarReporteMes(){
	
	console.log("buscando valores ensayo");
	$.ajax({
		type: 'POST',
		url: 'reporteEnsayoCloroBandeja',
		data: dataSearchXMes(),
		success: function(result){
			$('#tableResult').html(result);
//			$('#modal-registra-parametroplan').modal('show');
			
//				cargarCombo("listComboMeses","#cboMeses");
//				calcularTotales();
//				dataRefrescarDatos();
		},
		complete: function() {
			$("#guardarButton").html('Guardar');
			$('#guardarButton').removeAttr("disabled");
			setTimeout(function(){
				ajustarMedidasTablaBandeja();
				
				$(".punto-muestra").each(function(){
					console.log("modificando punto de ensayo : ");
					var valor = $(this).text();
					var valorNuevo = PUNTO_MUESTRA[valor];
					if(valorNuevo!=null && valorNuevo!=undefined){
						$(this).text(valorNuevo);
					}
				})
				
			},100)
			
		}
	});
}

function dataSearchXMes(){
	var data = {};
	data.fechaMuestreo = $("#idFechaMuestreo").val();
	return data;
}


function dataSearchXFecha(diaElegido){
	var data = {};
	data.fechaMuestreo = diaElegido;
	return data;
}
function cargarSelect(urlCombo,idSelect,idInput){
	var value = $(idInput).text();
	cargarCombo(urlCombo,idSelect);
	console.log("asignando valor al combo " + value );
	setTimeout(function(){
		$(idSelect).val(value);
	},500);
	
}

function joinColumnaMuestreo(){
	
	var inputColumnFirst = '';
	var puntoValue = $("#cboPuntoMuestreo").val();
	var phValue = $("#ph").val();
	var temperatura = $("#inputTemperatura").val();
	
	$("#body-detalles tr > td:first-child").remove();// removemos todas los primeros td 

	
	var divInput = '<label class="col-md-6 col-sm-6 col-xs-6 " style="text-weight: bold"><span style="font-weight: bold">Punto :</span></label>'+'<label class="col-md-6 col-sm-6 col-xs-6" id="labelPunto">-<span></span></label>'
					+'<label class="col-md-6 col-sm-6 col-xs-6 " style="font-weight: bold"><span style="font-weight: bold">PH :</span></label>'+'<label class="col-md-6 col-sm-6 col-xs-6" id="labelPh">-<span></span></label>'
					+'<label class="col-md-6 col-sm-6 col-xs-6 " style="font-weight: bold"><span style="font-weight: bold">Temp. :</span></label>'+'<label class="col-md-6 col-sm-6 col-xs-6" id="labelTemp">-<span></span></label>'
	
	var tdInput = '<td rowspan="5" ><div  type="text" id="inputMuestreo"   style="height:30px"/>'+divInput+'</div></td>'
	$(tdInput).insertBefore($("#body-detalles tr:first-child td:first-child"));//ingresamos un td con rowspan del tamaio 5
}

function actualizarEstandarCloro(clase){
	var tioSulfato = $("#tioSulfato").val();
	var promedio = obtenerPromedioEstandar(clase);
	var valorEstandar = (tioSulfato/promedio).toFixed(2);
	console.log("valorEstandar : " + valorEstandar);
	if(isNaN(valorEstandar)){
		$("#estandarCloro").val(0);
	}else{
		$("#estandarCloro").val(valorEstandar);
	}
	
}

function actualizarEstandarSodio(clase){
	console.log("valor estandar ");
	var bicromatoPotasio = $("#bicromatoPotasio").val();
	console.log("valor estandar 1 ");
	var promedio = obtenerPromedioEstandar(clase);
	console.log("valor estandar 2");
	var valorEstandar = (bicromatoPotasio/promedio).toFixed(2);
	
	if(isNaN(valorEstandar)){
		$("#estandarSodio").val(0);
	}else{
		$("#estandarSodio").val(valorEstandar);
	}
	
	console.log("valorEstandar : " + valorEstandar);
	
}

function obtenerPromedioEstandar(clase){
	var total = Number(0);
	var c = 0;
	$(clase).each(function(){
		total += Number($(this).val());
		c++ ;
	});
	console.log("totallllll : " + total);
	return total>0? (total/c) : 0;
}

function loadDateOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'DD/MM/YYYY',
        ignoreReadonly: true,
        date: new Date(),
        locale: "es",
        maxDate: new Date()
	}) .on('changeDate', function(e) {
	});
}

function loadMonthOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'MM/YYYY',
        ignoreReadonly: true,
        date: new Date(),
        locale: "es",
        maxDate: new Date()
	}) .on('changeDate', function(e) {
	});
}


function loadHourOnInput(idInput){
	var valInput = $(idInput).val();
	console.log(idInput + " : " + valInput)
	$(idInput).datetimepicker({
		format: 'HH:mm:ss',
	    ignoreReadonly: false,
	    date: new Date(),
	    locale: "es"
	}) .on('changeDate', function(e) {
	});
}


function cargarNombresTabla(){
	$(".analistas").each(function(){
		console.log("modificando analistas : ");
		var valorSubactividad = $(this).text();
		var valorNuevo = ANALISTAS[valorSubactividad];
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
		}
	})
	
	
	$(".muestreadores").each(function(){
		console.log("modificando analistas : ");
		var valorSubactividad = $(this).text();
		var valorNuevo = MUESTREADORES[valorSubactividad];
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
		}
	})
	
	$(".metodos").each(function(){
		console.log("modificando analistas : ");
		var valorSubactividad = $(this).text();
		var valorNuevo = METODOS[valorSubactividad];
		if(valorNuevo!=null && valorNuevo!=undefined){
			$(this).text(valorNuevo);
		}
	})
	
}


function ajustarMedidasTablaBandeja(){
	$(".container-parent-position-middle").each(function(){
		var height = $(this).css("height"); // Se resta 2 por el borde
		height = height.replace("px","");
		height = height-2 + "px";
		
		console.log("height: " + height)
		$(this).find(".container-position-middle").css("line-height",height);
	});
}

