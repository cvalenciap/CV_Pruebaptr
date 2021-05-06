/**
 * Hoja de scripts manejados en el mantenimiento de clima
 */
$(document).ready(function() {
	
	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"newestOnTop" : false,
		"progressBar" : false,
		"positionClass" : "toast-top-center",
		"preventDuplicates" : false,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}
	MYAPPL.blockPageLoad();
	cargarCombo("listAreas","#cboAreas");
	
	var arrayAsignadas = [];
	
	var actPosibles;
	//Validar picklist
	setTimeout(function(){
		
	
	$.getJSON("listActividades").then(function(data){
		actPosibles = data;
		var idAreaSeleccionada = $('#cboAreas').find(":selected").data("id");
		
		//cargando id del inputtext del codigo
		$("#cpDesc").val("000"+idAreaSeleccionada);
		
		$.getJSON("listActividadesAsignadas/"+idAreaSeleccionada).then(function(data){
	 
	var actAsignadas = data;

	for(var i in actAsignadas) {
		 arrayAsignadas.push(actAsignadas[i].id);
	};

	actPosibles = actPosibles.filter(function(currentObject,index) {
	   if(arrayAsignadas.indexOf(currentObject.id)!= -1){
	   		return false;
	   }
	   return true;
	});
	
	//CARGA DE LOS DISTRITOS EN LA BUSQUEDA
	armarCbo(actPosibles,"#cboActPosibles");
	armarCbo(actAsignadas,"#cboActAsignadas");
	
	setTimeout(function(){
		$('#cboActAsignadas option:eq(0)').attr('selected', 'selected');
		$('#cboActAsignadas option:eq(0)').click();
		
	},200)

	});
		
		$.unblockUI();
	});
	
	},500);
});


function modificarMensajeActividad() {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'POST',
		url: 'modificarActividad',
		data : dataActualizarDescripcion(),
		success: function(result) {
			$('#id-modal-content').html(result);
			
			var valueItemSelected = $("#commentActPersonalizada").val();
			$('#cboActAsignadas').find(":selected").attr("data-custom",valueItemSelected)
			
		},complete: function() {
			$.unblockUI();
		}
	});
}

/*METODO PARA INSERTAR */
function insertarActividad(insertarActividad) {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'POST',
		url: 'insertarActividad',
		data : dataInsertar(insertarActividad),
		success: function(result) {
			$('#id-modal-content').html(result);
		},complete: function() {
			$.unblockUI();
		}
	});
}

function removerActividad(indices) {
	MYAPPL.blockPageLoad();
	$.ajax({
		type: 'POST',
		url: 'modificarActividad',
		data : dataEliminar(indices),
		success: function(result) {
			$('#id-modal-content').html(result);
		},complete: function() {
			$.unblockUI();
		}
	});
}


function dataInsertar(indices){
	var data = {};
	data.descripcionAsignada = "";
	data.idArea = $('#cboAreas').find(":selected").data("id");;
	data.idActividad = $('#cboActPosibles').find(":selected").data("id");;
	data.estado = "1";
	data.indices = indices;
	return data;
}

function dataActualizarDescripcion() {
	var data = {};
	data.descripcionAsignada = $("#commentActPersonalizada").val();
	data.idArea = $('#cboAreas').find(":selected").data("id");
	data.idActividad = $('#cboActAsignadas').find(":selected").data("id");
	data.estado = "1";
	data.accion = "UD";
	return data;
}

function dataEliminar(indices) {
	var data = {};
	data.descripcionAsignada = "";
	data.idArea = $('#cboAreas').find(":selected").data("id");
	data.idActividad = $('#cboActAsignadas').find(":selected").data("id");
	data.estado = "0";
	data.indices = indices;
	return data;
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchClima();
});


//PickList
	  $.fn.pickList = function(options) {

	    var opts = $.extend({}, $.fn.pickList.defaults, options);

	    this.fill = function() {
	      var option = '';

	      $.each(opts.data, function(key, val) {
	        option += '<option data-id=' + val.id + '>' + val.text + '</option>';
	      });
	      this.find('.pickData').append(option);
	    };
	    this.controll = function() {
	      var pickThis = this;

	      pickThis.find(".pAdd").on('click', function() {
	    	var arrayInsertar = "";
		    var p = pickThis.find("#cboActPosibles option:selected");
		    if(p.length<1){
		    	return;
		    }
		    p.each(function(index){
		        	var idSeleccionado = $(this).attr('data-id');
		        	arrayInsertar += (idSeleccionado + "#");
		    })  
	    	  
	    	insertarActividad(arrayInsertar);
		        
	        p.clone().appendTo(pickThis.find(".pickListResult"));
	        p.remove();
	        var itemAdded = p.attr("data-id");
	        $("#commentActDefault").val("");
	        seleccionarItemMovido("#cboActAsignadas",itemAdded);
	        refrescarSectorAsignado();
	        
	      });

	      pickThis.find(".pRemove").on('click', function() {
	    	
	    	var arrayModificar = "";
	        var p = pickThis.find(".pickListResult option:selected");
	        if(p.length<1){
		    	return;
		    }
	        p.each(function(index){
	        	var idSeleccionado = $(this).attr('data-id');
	        	arrayModificar += (idSeleccionado + "#");
	        	$(this).attr('data-defecto', $(this).attr('title'));
	        	$(this).attr('data-custom', '');
	        })
	        
	        removerActividad(arrayModificar);
	        
	        p.clone().appendTo(pickThis.find(".pickData"));
	        p.remove();
	        $("#commentActPersonalizada").val("");
	        var itemAdded = p.attr("data-id");
	        
	        setTimeout(function(){
	        	seleccionarItemMovido("#cboActPosibles",itemAdded)
	        },100);
	        
	        setTimeout(function(){
	        	refrescarSectorPosibles();
	        },300);
	        
	      });

	    };

	    this.getValues = function() {
	      var objResult = [];
	      this.find(".pickListResult option").each(function() {
	        objResult.push({
	          id: $(this).data('id'),
	          text: this.text
	        });
	      });
	      return objResult;
	    };



		  this.fill();
	      this.controll();
	 
	    return this;
	  };

	  $.fn.pickList.defaults = {
	    add: 'Add',
	    addAll: 'Add All',
	    remove: 'Remove',
	    removeAll: 'Remove All'
	  };

	  //cargar picklist

	var pick = $("#pickList").pickList({
	  data: {}
	});
	
	$("#btnActualizarDescri").click(function() {
		modificarMensajeActividad();
	});
	
	
	$('#cboAreas').change(function() {
		
		$('#commentActPersonalizada').val('');
		$('#commentActDefault').val('');
		
		var idArea = $('#cboAreas').find(":selected").data("id");
		var arrayAsignadas = [];
		var actPosibles;
		
		$("#cpDesc").val("000"+idArea);
		$.getJSON("listActividades").then(function(data){
			actPosibles = data;
			
			$.getJSON("listActividadesAsignadas/"+idArea).then(function(data){
		 
		var actAsignadas = data;

		for(var i in actAsignadas) {
			  		arrayAsignadas.push(actAsignadas[i].id);
		};

		actPosibles = actPosibles.filter(function(currentObject,index) {
			
		   if(arrayAsignadas.indexOf(currentObject.id) != -1){
		   		return false;
		   }
		   return true;
		});
		
		//CARGA DE LOS DISTRITOS EN LA BUSQUEDA
		
		armarCbo(actPosibles,"#cboActPosibles");
		armarCbo(actAsignadas,"#cboActAsignadas");
		
		
		setTimeout(function(){
			$('#cboActAsignadas option:eq(0)').attr('selected', 'selected');
			$('#cboActAsignadas option:eq(0)').click();
			
		},200)

		});
			
			$.unblockUI();
		});
		
		
	});
	
	$('#cboActAsignadas').click(function() {
		
		var valueItemSelected = $('#cboActAsignadas').find(":selected").attr("data-custom");
		if(valueItemSelected==null || valueItemSelected == "null" || valueItemSelected == undefined) {
			valueItemSelected = "";
		}
		$("#commentActPersonalizada").val(valueItemSelected);
		$('#cboActAsignadas').find(":selected").data("custom",valueItemSelected)
	
	});
	
	$('#cboActPosibles').click(function() {
		refrescarSectorPosibles();
	});
	
	function seleccionarItemMovido(idPickListDestinoMovido, dataIdOption){
		 unselectOption(idPickListDestinoMovido);
		 $(idPickListDestinoMovido).find("[data-id='" + dataIdOption + "']").attr('selected', 'selected');
	}
	
	function refrescarSectorAsignado(){
		var valueItemSelected = $('#cboActAsignadas').find(":selected").attr("data-custom");
		if(valueItemSelected==null || valueItemSelected == "null" || valueItemSelected == undefined) {
			valueItemSelected = "";
		}
		$("#commentActPersonalizada").val(valueItemSelected);
	}
	
	function refrescarSectorPosibles(){
		var valueItemSelected = $('#cboActPosibles').find(":selected").data("defecto");
		if(valueItemSelected==null || valueItemSelected == "null" || valueItemSelected == undefined) {
			valueItemSelected = "";
		}
		$("#commentActDefault").val(valueItemSelected);
	}
	
	function unselectOption(idPickListDestinoMovido,idItemMoved) {
		  var ident = idPickListDestinoMovido + " option"
	     $.each($(ident), function() {
	    	 var id= $(this).attr('data-id');
	    	 			if(idItemMoved!=id){
	    	 				$(this).attr('selected', false);
	    	 				$(this).prop('selected', false);
	    	 			}
	                });
	   
	 }
	
	
	
	
	
	
	

	



	
	
	
	



	
	
