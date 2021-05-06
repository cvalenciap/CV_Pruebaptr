var nsPd = MYAPPL.createNS("MYAPPL.LOGIC.UPDATECLAVE");

//FUNCION PARA CAMPOS EN EL FORMULARIO DE CAMBIO DE CLAVE

$(document).ready(function() {
    
    $('#form-cambiar-clave').bootstrapValidator({
	  
	    feedbackIcons: {
	      valid: 'glyphicon glyphicon-ok',
	      invalid: 'glyphicon glyphicon-remove',
	      validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	currentpassword: {
	        validators: {
	          notEmpty: { message: 'La clave actual no puede estar vac&iacute;a'}
	        }
	      },
	      newpassword: {
	        validators: {
	          notEmpty: { message: 'La nueva clave no puede estar vac&iacute;a'},	          
	        }
	      },
	      confirmnewpassword: {
	        validators: {
	          notEmpty: { message: 'El confirmar nueva clave no puede estar vac&iacute;a'},	        
	        }
	      },
	    }
	  }).on('success.form.bv', function(e, data) {
			$("#actualizarClave").html('Actualizando...');
			$("#actualizarClave").attr('disabled', 'disabled');
			e.preventDefault();//EVITAR HACER REDIRECT
			var $cambiarClaveForm = $(e.target);
			$.ajax({
		    	   type: 'POST',
		    	   url: $('#form-cambiar-clave').attr('action'),
		    	   data: $('#form-cambiar-clave').serialize(),  
		    	   success: function(result){
		    		   
		    		   if(result.codigo==0){
		    			   console.log(result);
			    		   $('#divErrorMessage').html('');
			    		   $('#divErrorMessage').prepend($(
			    				   '<div class="alert alert-danger col-md-12">'+
								    '<span>'+result.mensaje+'</span>'+
									'</div>'
							));
		    		   }else{
		    			   var url = $('#urlRedirect').text();
		    			   console.log(url);
		    			   window.location.href=url;
		    		   }
		    	   },
					complete: function() {
						$("#actualizarClave").html('Actualizar');
						$('#actualizarClave').removeAttr("disabled");
					}
		    });  
		});	
});
