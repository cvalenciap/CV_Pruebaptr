function formatoNumeroComas(numero)
{
	numero = numero.toString();
    var pattern = /(-?\d+)(\d{3})/;
    while (pattern.test(numero))
           numero = numero.replace(pattern, "$1,$2");
    return numero;	
}
function formatoNumeroSinComas(text,busca,reemplaza)
{
    while (text.toString().indexOf(busca) != -1)
	      text = text.toString().replace(busca,reemplaza);
    return text;
}
function validarSiNumero(texto)
{
	var validar=true;
	if(isNaN(texto))
	{
		validar=false;
	}
	return validar;
}
function formatoNumeroDecimales(numero)
{
	var numeroDecimal = numero.toFixed(2);
	return numeroDecimal;
}
function esNumeroEntero(numero)
{
	var validar=false;
    if (numero%1==0)
    {
        validar=true;
    }
    return validar;
}
function ponerDecimales(numero)
{
    if(numero.indexOf(".")==-1) 
    {
        numero += ".00" 
    }
    else
    {
        if(numero.indexOf(".")==numero.length-2)
        {
            numero += "0"
        }
    }
    return numero;
} 