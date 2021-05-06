var nsLogin = MYAPPL.createNS("MYAPPL.LOGIC.LOGIN");

//FUNCION PARA HABILITAR LAS VALIDACIONES EN EL FORMULARIO DE LOGIN
nsLogin.activateValidationLogin = function () {	
	$("#form-login").bootstrapValidator({
		message: 'El valor no es v\u00e1lido',
	
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphico-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			username: {
                validators: {
                	notEmpty: {
                        message: 'Ingrese el usuario'
                    }
                }
            },
            password: {
                validators: {
                	notEmpty: {
                        message: 'Ingrese la clave'
                    }
                }
            }
		}
	})	
	.on('success.form.bv', function(e, data) {
		$('#username').val( $('#username').val().toUpperCase());
		$("#idTxtEntrar").html('Autenticando...');
		$("#idTxtEntrar").attr('disabled', 'disabled');
		/*e.preventDefault();
		var $loginForm = $(e.target);
		MYAPPL.toUppercaseInputs();
		$.ajax({
	    	   type: 'POST',
	    	   url: $('#form-login').attr('action'),
	    	   data: $('#form-login').serialize(),  
	    	   success: function(result){
	    		   if(result.indexOf('form-edit-punto')!==-1){
					   $('#form-edit-punto').replaceWith(result);
					   nsPd.activateValidationPuntoDistribucion();
	    		   }else{
		    		   $loginForm.bootstrapValidator('resetForm', true);
		    		   $loginForm[0].reset();	    		   
		    	       $('.container_save').html(result);	    			   
	    		   }
	    	   },
				complete: function() {
					$("#idTxtEntrar").html('Entrar');
					$('#idTxtEntrar').removeAttr("disabled");
				}
	    });*/  
	});	

	$("#username").on({
		  keydown: function(e) {
		    if (e.which === 32)
		      return false;
		  },
		  change: function() {
		    this.value = this.value.replace(new RegExp(' ', 'g'), '');
		  }
	});
	
}	

nsLogin.activateValidationLogin();
	
