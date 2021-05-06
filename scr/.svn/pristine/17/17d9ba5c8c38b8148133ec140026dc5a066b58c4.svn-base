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
	cargarCombo("listSubactividades","#cboSubactividades");
	
	var arrayAsignadas = [];
	
	var actPosibles;
	//Validar picklist

	setTimeout(function(){
	$.getJSON("listPuntos").then(function(data){
		actPosibles = data;
		var idActividadSeleccionada
		
			 idActividadSeleccionada = $('#cboSubactividades').find(":selected").data("id");
			
			//cargando id del inputtext del codigo
			$("#cpDesc").val("000"+idActividadSeleccionada);
		
			 console.log("idActividadSeleccionada: " + idActividadSeleccionada);
		
		$.getJSON("listPuntosAsignadas/"+idActividadSeleccionada).then(function(data){
	 
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
	
	armarCbo(actPosibles,"#cboPuntosPosibles");
	armarCbo(actAsignadas,"#cboPuntosAsignados");
	
	setTimeout(function(){
		$('#cboPuntosAsignados option:eq(0)').attr('selected', 'selected');
		$('#cboPuntosAsignados option:eq(0)').click();
		
	},200)

	});
	});
	
	
	
	},500)
});



function modificarMensajeActividad() {
	
	$.ajax({
		type: 'POST',
		url: 'modificarPunto',
		data : dataActualizarDescripcion(),
		success: function(result) {
			$('#id-modal-content').html(result);
			var valueItemSelected = $("#commentActPersonalizada").val();
			$('#cboPuntosAsignados').find(":selected").attr("data-custom",valueItemSelected)
			
		},complete: function() {
		}
	});
}

/*METODO PARA INSERTAR */
function insertarActividad(insertarActividad) {
	$.ajax({
		type: 'POST',
		url: 'insertarPunto',
		data : dataInsertar(insertarActividad),
		success: function(result) {
			$('#id-modal-content').html(result);
		},complete: function() {
			
		}
	});
}

function removerActividad(indices) {
	
	$.ajax({
		type: 'POST',
		url: 'modificarPunto',
		data : dataEliminar(indices),
		success: function(result) {
			$('#id-modal-content').html(result);
		}
	});
}

function dataInsertar(indices){
	var data = {};
	data.descripcionAsignada = "";
	data.idSubactividad = $('#cboSubactividades').find(":selected").data("id");;
	data.idPunto = $('#cboPuntosPosibles').find(":selected").data("id");;
	data.estado = "1";
	data.indices = indices;
	return data;
}

function dataActualizarDescripcion() {
	var data = {};
	data.idSubactividad = $('#cboSubactividades').find(":selected").data("id");
	data.idPunto = $('#cboPuntosAsignados').find(":selected").data("id");
	data.estado = "1";
	data.accion = "UD";
	return data;
}

function dataEliminar(indices) {
	var data = {};
	data.idSubactividad = $('#cboSubactividades').find(":selected").data("id");
	data.idPunto = $('#cboPuntosAsignados').find(":selected").data("id");
	data.estado = "0";
	data.indices = indices;
	return data;
}

$(document).on("click", ".btn-limpiar", function(e) {
	e.preventDefault();
	clearSearchClima();
});

//FUNCION PARA INACTIVAR UN REGISTRO
function inactivarClima(id) {
	$.confirm({
	    title: 'Confirmaci\u00f3n',
	    content: 'Confirma inactivar Clima ?',
	    buttons: {
	    	Aceptar: { 
	        	btnClass: 'btn-blue',
	        	action: function () {
		        	$('#inacIdclima').val(id); 
				    $.ajax({
			    	   type: 'POST',
			    	   url: $('#form-inactiva-clima').attr('action'),
			    	   data: $('#form-inactiva-clima').serialize(),  
			    	   success: function(result){  		   
			    	       $('.container_save').html(result);		    	      
				    	   },
							complete: function() {
								$.unblockUI();
							}
				    	}); 
		        	}
		        },
		        Cancelar: {
		        }
		    }
		});	   
};


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
		    var p = pickThis.find("#cboPuntosPosibles option:selected");
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
	        seleccionarItemMovido("#cboPuntosAsignados",itemAdded);
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
	        })
	        
	        removerActividad(arrayModificar);
	        
	        p.clone().appendTo(pickThis.find(".pickData"));
	        p.remove();
	        $("#commentActPersonalizada").val("");
	        var itemAdded = p.attr("data-id");
	        
	        setTimeout(function(){
	        	seleccionarItemMovido("#cboPuntosPosibles",itemAdded)
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
	
	$('#cboSubactividades').change(function() {
		var idArea = $('#cboSubactividades').find(":selected").data("id");
		var arrayAsignadas = [];
		var actPosibles;
		
		$("#cpDesc").val("000"+idArea);
		MYAPPL.blockPageLoad();
		$.getJSON("listPuntos").then(function(data){
			actPosibles = data;

		$.getJSON("listPuntosAsignadas/"+idArea).then(function(data){
		 
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
		
		armarCbo(actPosibles,"#cboPuntosPosibles");
		armarCbo(actAsignadas,"#cboPuntosAsignados");
		
		setTimeout(function(){
			$('#cboPuntosAsignados option:eq(0)').attr('selected', 'selected');
			$('#cboPuntosAsignados option:eq(0)').click();
			
		},200)

		});
	 
		});
		$.unblockUI();
	});
	
	$('#cboPuntosAsignados').click(function() {
		
		var valueItemSelected = $('#cboPuntosAsignados').find(":selected").attr("data-custom");
		if(valueItemSelected==null || valueItemSelected == "null" || valueItemSelected == undefined) {
			valueItemSelected = "";
		}
		$("#commentActPersonalizada").val(valueItemSelected);
		$('#cboPuntosAsignados').find(":selected").data("custom",valueItemSelected)
	
	});
	
	$('#cboPuntosPosibles').click(function() {
		refrescarSectorPosibles();
	});
	
	function seleccionarItemMovido(idPickListDestinoMovido, dataIdOption){
		 unselectOption(idPickListDestinoMovido);
		 $(idPickListDestinoMovido).find("[data-id='" + dataIdOption + "']").attr('selected', 'selected');
	}
	
	function refrescarSectorAsignado(){
		var valueItemSelected = $('#cboPuntosAsignados').find(":selected").attr("data-custom");
		if(valueItemSelected==null || valueItemSelected == "null" || valueItemSelected == undefined) {
			valueItemSelected = "";
		}
		$("#commentActPersonalizada").val(valueItemSelected);
	}
	
	function refrescarSectorPosibles(){
		var valueItemSelected = $('#cboPuntosPosibles').find(":selected").data("defecto");
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
	
	
	
	
	
	
	

	



	
	
	
	



	
	
