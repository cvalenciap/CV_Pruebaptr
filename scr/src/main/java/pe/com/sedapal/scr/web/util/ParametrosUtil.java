package pe.com.sedapal.scr.web.util;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.util.StringUtils;


public class ParametrosUtil {
	
	/**
	 * Recorta una cadena desde el lado izquierdo
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String left(String cadena, int longitud)
	{
		String result = "";

		if (longitud > cadena.length())
		{
			result = cadena;
		}
		else
		{
			result = cadena.substring(0, longitud);
		}

		return result;
	}
	/**
	 * Recorta una cadena desde el lado derecho
	 * 
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String right(String cadena, int longitud)
	{
		String result = "";

		if (longitud > cadena.length())
		{
			result = cadena;
		}
		else
		{
			result = cadena.substring(cadena.length() - longitud);
		}

		return result;
	}	
	
	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor
	 * inicial y una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @return
	 */
	public static String mid(String cadena, int inicio)
	{
		String result = "";

		if (inicio > cadena.length())
		{
			result = "";
		}
		else
		{
			inicio = inicio - 1;
			result = cadena.substring(inicio);
		}

		return result;
	}
	/**
	 * Permite extraer una subcadena de una cadena principal dado un valor
	 * inicial y una longitud
	 * 
	 * @param cadena
	 * @param inicio
	 * @param longitud
	 * @return
	 */
	public static String mid(String cadena, int inicio, int longitud)
	{
		String result = "";

		if (inicio > cadena.length())
		{
			result = "";
		}
		else
		{
			inicio = inicio - 1;
			if (inicio+longitud>cadena.length()){
				result = cadena.substring(inicio, cadena.length());
			}else{
				result = cadena.substring(inicio, inicio + longitud);
			}
		}

		return result;
	}

	/**
	 * Convierte una cadena de caracteres a mayúscula
	 * 
	 * @param cadena
	 * @return
	 */
	public static String uCase(String cadena)
	{
		return cadena.toUpperCase();
	}

	/**
	 * Convierte una cadena de caracteres a minúsculas
	 * 
	 * @param cadena
	 * @return
	 */
	public static String lCase(String cadena)
	{
		return cadena.toLowerCase();
	}
	/**
	 * Devuelve un valor numérico que especifica la posición de la primera
	 * aparición de una cadena en otra, desde el principio de la cadena
	 * 
	 * @param cadena
	 * @param cadenaBuscada
	 * @return
	 */
	public static int inStr(String cadena, String cadenaBuscada)
	{
		int posicion = cadena.indexOf(cadenaBuscada);
		if (posicion == -1)
		{
			return 0;
		}
		else
		{
			return posicion + 1;
		}

	}
	/**
	 * Devuelve la longitud de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static int len(String cadena)
	{
		return cadena.length();
	}
	/**
	 * Quita espacios en blanco al inicio y fin de una cadena de caracteres
	 * 
	 * @param cadena
	 * @return
	 */
	public static String trim(String cadena)
	{
		return cadena.trim();
	}
	/**
	 * Devuelve la representaci�n String de un n�mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(int numero)
	{
		return String.valueOf(numero);
	}

	/**
	 * Devuelve la representaci�n String de un n�mero.
	 * 
	 * @param numero
	 * @return
	 */
	public static String str(double numero)
	{
		return String.valueOf(numero);
	}
	/**
	 * Adiciona o recorta espacios a la cadena de acuerdo a la longitud
	 * @param cadena
	 * @param longitud
	 * @return
	 */
	public static String rightBlankPad(String cadena,int longitud){
		String cadenaRepetida=string(longitud," ");
		cadena=cadena+cadenaRepetida;
		cadena=left(cadena, longitud);
		return cadena;		
	}
	
	/* Método que rellena de ceros el lado derecho */
	public static String padRight(String str,String pad){
		String retorno="";
		if(str !=null && pad!=null){
			retorno = str + pad.substring(0, pad.length() - str.length());
		}
		return retorno;		
	}

	/**
	 * Repite una cadena de caracteres n (cantidad) veces
	 * 
	 * @param cantidad
	 * @param cadenaRepetir
	 * @return
	 */
	public static String string(int cantidad, String cadenaRepetir)
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < cantidad; i++)
		{
			sb.append(cadenaRepetir);
		}

		return sb.toString();
	}
	/* Método que rellena de ceros el lado izquierdo  */
	public static String padleft(String str,String pad){
		String retorno="";
		if(str !=null && pad!=null){
		retorno = pad.substring(0, pad.length() - str.length()) + str;
		}
		return retorno;		
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	    return formateador.format(ahora);
	}
	
	public static String getFechaHoraActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    return formateador.format(ahora);
	}
	
	public static Date getFechaActualToDateHour(){
		String formato = "dd/MM/yyyy hh:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato); //"yyyy-MM-dd hh:mm:ss.SSS");
		Date parsedDate = new Date();
		try {
			parsedDate = dateFormat.parse(getFechaHoraActual());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parsedDate;
		
	}
	
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaActualconFormato(String formato) {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat(formato);
	    return formateador.format(ahora);
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static String getFechaconFormato(Date date, String formato) {
	    SimpleDateFormat formateador = new SimpleDateFormat(formato);
	    return formateador.format(date);
	}
	/**
	 * Metodo que permite obtener la fecha actual
	 * @return fecha actual formateada
	 */
	public static Date getFechaActualToDate() {
		Calendar now = Calendar.getInstance();
		
	    return now.getTime();
	}
	
	public static Timestamp getFechaActualTimestamp(){
		Timestamp timestamp = null;

		try{
			
			Date ahora = new Date();
			timestamp = new java.sql.Timestamp(ahora.getTime());			

		}catch(Exception ex){
			ex.printStackTrace();
		}

		return timestamp;
	}
	
	
	/*
	 * Metodo que permite obtener la fecha en formato timestamp
	 * @return fecha formateada
	 */
	public static Timestamp getFechaToTimestamp(String fecha, String formato){
		Timestamp timestamp = null;

		try{
			
			if(!StringUtils.isEmpty(fecha)){
				SimpleDateFormat dateFormat = new SimpleDateFormat(formato); //"yyyy-MM-dd hh:mm:ss.SSS");
				Date parsedDate = dateFormat.parse(fecha);
				timestamp = new java.sql.Timestamp(parsedDate.getTime());
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}

		return timestamp;
	}
	
	
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Timestamp
	 * */
	public static Timestamp convertStringToTimestamp(String fecha) throws ParseException{
	    Timestamp timestamp = null;  //declare timestamp
	    
	    try{

	    	if(!fecha.equals("")){
	    		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	    		Date d=dt.parse(fecha); 
	    		//new Date(fecha); // Intialize date with the string date
	    		if(d!=null){  // simple null check
	    			timestamp = new java.sql.Timestamp(d.getTime());
	    		}
	    	}

	    }catch(Exception excepcion){
	    	timestamp = null;
	    }

	    return timestamp;
	}
	
	public static Timestamp convertStringToTimestamp(String fecha, String formato) throws ParseException{
	    Timestamp timestamp = null;  //declare timestamp
	    
	    try{

	    	if(!fecha.equals("")){
	    		SimpleDateFormat dt = new SimpleDateFormat(formato);
	    		Date d=dt.parse(fecha); 
	    		//new Date(fecha); // Intialize date with the string date
	    		if(d!=null){  // simple null check
	    			timestamp = new java.sql.Timestamp(d.getTime());
	    		}
	    	}

	    }catch(Exception excepcion){
	    	timestamp = null;
	    }

	    return timestamp;
	}
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Date
	 * */
	public static Date convertObjectToDate(Object objeto){
	    Date fecha = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        String dateInString = String.valueOf(objeto);
        	fecha = formatter.parse(dateInString);
		} catch(Exception e) {
			fecha = null;
		}
	    return fecha;
	}
	
	/*
	 * Permite convertir un string dd/MM/yyyy to Timestamp y time hh:mm:ss
	 * caso 1: time hh:mm
	 * caso 2: time mm:ss
	 * caso 3: time hh:mm:ss
	 * */
	@SuppressWarnings("deprecation")
	public static Timestamp convertStringToTimestamp(String fecha, String time, Integer caso){
	    Timestamp timestamp = null;  //declare timestamp
	    Date d=new Date(fecha); // Intialize date with the string date
	    
	    if(d!=null){  // simple null check
	    	timestamp = new java.sql.Timestamp(d.getTime());
	    	
	    	addTimeToTimestamp(timestamp, time, caso);
	    }
	    
	    return timestamp;
	}
	
	@SuppressWarnings("deprecation")
	public static Timestamp addTimeToTimestamp(Timestamp timestamp, String time, Integer caso){
		
		Timestamp timestampClone=  (Timestamp)timestamp.clone();
		
		switch(caso){
    	case 1:
    		timestampClone.setHours(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setMinutes(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 2:
    		timestampClone.setMinutes(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setSeconds(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 3:
    		timestampClone.setHours(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setMinutes(Integer.parseInt(time.substring(3,5)));
    		timestampClone.setSeconds(Integer.parseInt(time.substring(6,7)));
    		break;
    	}
		return timestampClone;
	}
	
	
	@SuppressWarnings("deprecation")
	public static Timestamp addTimeToTimestamp(Date date, String time, Integer caso){
		
		
		Timestamp timestampClone= new Timestamp(date.getTime()) ;
		
		switch(caso){
    	case 1:
    		timestampClone.setHours(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setMinutes(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 2:
    		timestampClone.setMinutes(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setSeconds(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 3:
    		timestampClone.setHours(Integer.parseInt(time.substring(0,2)));
    		timestampClone.setMinutes(Integer.parseInt(time.substring(3,5)));
    		timestampClone.setSeconds(Integer.parseInt(time.substring(6,7)));
    		break;
    	}
		return timestampClone;
	}
	
	
	@SuppressWarnings("deprecation")
	public static Timestamp addTimeToTimestamp(String time, Integer caso){	
	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		switch(caso){
    	case 1:
    		timestamp.setHours(Integer.parseInt(time.substring(0,2)));
    		timestamp.setMinutes(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 2:
    		timestamp.setMinutes(Integer.parseInt(time.substring(0,2)));
    		timestamp.setSeconds(Integer.parseInt(time.substring(3,5)));
    		break;
    	case 3:
    		timestamp.setHours(Integer.parseInt(time.substring(0,2)));
    		timestamp.setMinutes(Integer.parseInt(time.substring(3,5)));
    		timestamp.setSeconds(Integer.parseInt(time.substring(6,7)));
    		break;
    	}
		return timestamp;
			    
	}
	
	/*
	 * Permite acceder a un atributo del objeto para setear un valor
	 */
	public static void setValueToFieldObject(Object object, String fieldName, Object value){
		try {
			Field field = object.getClass().getDeclaredField(fieldName);    
			field.setAccessible(true);
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean ValidarKeyMapParametros(String llave,Map<String, Object> map){
		boolean retorno = false;
		if (map.containsKey(llave)){
			retorno = true;           
	    }else{
	    	retorno =  false;
	    }
		return retorno;
	}
	
	public static String obtenerIdDepartamentoPorUbigeo(String idUbigeo){
		return idUbigeo.substring(0, 2);
	}

	public static String obtenerIdProvinciaPorUbigeo(String idUbigeo){
	 	return idUbigeo.substring(2, 4);
	}
	
	public static String obtenerIdDistritoPorUbigeo(String idUbigeo){
	 	return idUbigeo.substring(4, 6);
	}
	public static String formatearAUTF8(String texto){
        String cadena=null;
        try {
        	if(texto != null){
	            cadena = new String(texto.getBytes("ISO-8859-1"),"UTF-8");
	        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cadena;
    }
	public static String isNullOrBlank(Object objeto){
		String respuesta;
		if(objeto == null || String.valueOf(objeto).equals("null") || String.valueOf(objeto).trim().equals("")){
			respuesta = "";
		} else {
			respuesta = String.valueOf(objeto);
		}
		return respuesta;
	}
	
	
	public static int diferenciaMinutosFechas(Date fechaInicial, Date fechaFinal)
	{
	
		long fechainicialms = fechaInicial.getTime();
		long fechafinalms = fechaFinal.getTime();
		long diferencia1 = fechainicialms - fechafinalms;
		double minutosDiferencia = Math.floor(diferencia1 /60000);// para Minutos
		int minutos = (int) minutosDiferencia;
		return minutos;
	}
	
	public static boolean isMenorFechaActual(String fecha)
	{
	    Date fechaDate= convertObjectToDate(fecha);
		return fechaDate.before(getFechaActualToDate());
	}
	
	public static boolean isMayorFechaActual(String fecha)
	{
	    Date fechaDate= convertObjectToDate(fecha);
		return fechaDate.after(getFechaActualToDate());
	}
	
	
	public static Date sumaroRestarTiempoaFecha(Date fecha, int campoaInteractuar, int valorTiempo){
		  //en campo va el atributo que se quiere modificar osea minutos o segundos
		  // si valorTiempo es > a 0 quiere decir que vas agregar tiempo a la fecha
		  //caso contrario si es < a 0 quiere decir que vas a restar tiempo a la fecha
	      if (valorTiempo==0){
	    	  return fecha;
	      }else{
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fecha); 
		      calendar.add(campoaInteractuar, valorTiempo);
		      return calendar.getTime(); 
	      } 
	}
	
	 public static float redondearDecimales(float valorInicial, int numeroDecimales) {
		 
		 
		    double parteEntera, resultado;
		    float convResultado;
	        resultado = valorInicial;
	        parteEntera = Math.floor(resultado);
	        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
	        resultado=Math.round(resultado);
	        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
	        convResultado= Float.parseFloat(String.valueOf(resultado));
	        return convResultado;
	    }
	 public static String FormatearNumeroNotacionCientificaaNormal(String numero) {
		 try {
			 BigDecimal nro = new BigDecimal(numero);
			 return String.valueOf(nro);
		 }catch(Exception ex) {
			 return numero;
		 }
	 }
	 
	 /**
		 * Metodo que permite obtener el formato real de un numero con exponente
		 * 
		 * @return Numero Formateado
		 */
		public static String formatearNumeroconE(String cadena) {
			String parteNumerica = "";
			String parteExponencial = "";
			Boolean encontroExponente = false;
			for (int i = 0; i < len(cadena); i++) {
				char c = cadena.charAt(i);
				if (c == 'E') {
					encontroExponente = true;
				} else {
					if (encontroExponente) {
						parteExponencial = parteExponencial + c;
					} else {
						parteNumerica = parteNumerica + c;
					}
				}
			}
			if (encontroExponente) {
				if (trim(parteNumerica).equals("")) {
					return cadena;
				} else {
					if (trim(parteExponencial).equals("")) {
						return cadena;
					} else {
						if (parteNumerica.contains(".")) {
							String[] array = parteNumerica.split("\\.");
							String parteEntera = array[0];
							String parteDecimal = array[1];
							int indice = Integer.parseInt(parteExponencial);
							String rpta = completa0Derecha(parteDecimal, indice);
							return parteEntera + rpta; //".0";
						} else {
							int indice = Integer.parseInt(parteExponencial);
							String rpta = completa0Derecha(parteNumerica, indice);
							return rpta ;//+ ".0";
						}

					}
				}
			} else {
				return cadena;
			}
		}
		
		/***
		 * Llenar 0 a la izquierda
		 */
		public static String completa0izquierda(String cadena, int cant0) {

			while (cadena.length() < cant0) {
				cadena = "0" + cadena;
			}

			return cadena;
		}

		/***
		 * Llenar 0 a la derecha
		 */
		public static String completa0Derecha(String cadena, int cant0) {

			while (cadena.length() < cant0) {
				cadena = cadena + "0";
			}

			return cadena;
		}
		
		public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
			return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
		}
		
		public static java.sql.Date convertirStringtoDateSP(String fecha) throws ParseException {
    		java.sql.Date fechaEstimada;
			try {
				Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    String parsedDate = formatter.format(initDate);
			    java.sql.Date flag = java.sql.Date.valueOf(parsedDate);
				fechaEstimada = flag;
			} catch (ParseException e) {
				fechaEstimada = null;
			}		
			return fechaEstimada;
		}
}

