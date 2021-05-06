$(document).ready(function() {
	initializeFieldtoken("strPara","div-para-group", true);
	initializeFieldtoken("strCc","div-cc-group", false); 
	// PARA REPORTE DE GERENCIA
	initializeFieldtoken("strParaGer","div-para-ger-group", true);
	initializeFieldtoken("strCcGer","div-cc-ger-group", false); 
	
	if(modelAttributeValue.lonCodigo != null){ // SI ES EDICION
		if(modelAttributeValue.strPara != null){
			var res = modelAttributeValue.strPara.split(",");
			var list = [];
			$.each( res, function( key, value ) {
				list.push(value);
			});
			$('#strPara').tokenfield('setTokens', list);
			clearValidationDecorate("strPara", "div-para-group", true);
		}
		if(modelAttributeValue.strCc != null){
			var res = modelAttributeValue.strCc.split(",");
			var list = [];
			$.each( res, function( key, value ) {
				list.push(value);
			});
			$('#strCc').tokenfield('setTokens', list);
			clearValidationDecorate("strCc", "div-cc-group", true);
		}
		
		// PARA REPORTE DE GERENCIA
		if(modelAttributeValue.strParaGer != null){
			var res = modelAttributeValue.strParaGer.split(",");
			var list = [];
			$.each( res, function( key, value ) {
				list.push(value);
			});
			$('#strParaGer').tokenfield('setTokens', list);
			clearValidationDecorate("strParaGer", "div-para-ger-group", true);
		}
		if(modelAttributeValue.strCcGer != null){
			var res = modelAttributeValue.strCcGer.split(",");
			var list = [];
			$.each( res, function( key, value ) {
				list.push(value);
			});
			$('#strCcGer').tokenfield('setTokens', list);
			clearValidationDecorate("strCc", "div-cc-ger-group", true);
		}
	}
	
});

function initializeFieldtoken(idInput, idDiv, required){
	$('#'+idInput).on('tokenfield:createtoken', function (e) {
	    var data = e.attrs.value.split('|')
	    e.attrs.value = data[1] || data[0]
	    e.attrs.label = data[1] ? data[0] + ' (' + data[1] + ')' : data[0];
	    
	  })
	  .on('tokenfield:createdtoken', function (e) {
	    // Über-simplistic e-mail validation
	    var re = REGEX_MAIL_VALIDATION
	    var valid = re.test(e.attrs.value)
	    if (!valid) {
	      $(e.relatedTarget).addClass('invalid')
	    }
	    
	      var validToken = validaAndDecorateInputToken(idInput, idDiv, required, true);
	      if(validToken!=""){
	  		$("#guardarButton").attr('disabled', 'disabled');
		  }
		  else{
		  	$('#guardarButton').removeAttr("disabled");
		  }
	  })
	  .on('tokenfield:edittoken', function (e) {
	    if (e.attrs.label !== e.attrs.value) {
	      var label = e.attrs.label.split(' (')
	      e.attrs.value = label[0] + '|' + e.attrs.value
	    }
	    	/*var validToken = validaAndDecorateInputToken(idInput, idDiv, required);
	    	if(validToken!=""){
		  		$("#guardarButton").attr('disabled', 'disabled');
			  }
			  else{
			  	$('#guardarButton').removeAttr("disabled");
			  }*/
	  })
	  .on('tokenfield:removedtoken', function (e) {
		  	var validToken = validaAndDecorateInputToken(idInput, idDiv, required, true);
		  	if(validToken!=""){
		  		$("#guardarButton").attr('disabled', 'disabled');
			  }
			  else{
			  	$('#guardarButton').removeAttr("disabled");
			  }
	  })
	  .tokenfield();
}

function validaAndDecorateInputToken(idInput, idDiv, required, disabledButton) {
	var resp = validateFieldToken(idInput, required);
	var result = (resp === "");
	decorateValidationFieldToken(idInput, idDiv, result, resp, disabledButton);
	return resp;
}

