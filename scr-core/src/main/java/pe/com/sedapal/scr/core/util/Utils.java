/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.common.Constants;
// TODO: Auto-generated Javadoc

/**
 * The Class Utils.
 */
public class Utils {
	
	/**
	 * Eval math expression.
	 *
	 * @param strMathExpression the str math expression
	 * @return the string
	 * @throws ScriptException the script exception
	 */
	public static String evalMathExpression(String strMathExpression) throws ScriptException{
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		return String.valueOf(engine.eval(strMathExpression));
	}
	

	
	/**
	 * String to UPPER.
	 *
	 * @param campo the campo
	 * @return the string
	 */
	public static String StringToUPPER(String campo){
		String cadena = "";
		if(campo!=null && !campo.trim().equals("")){
			cadena = campo.toUpperCase();
		}
		
		return cadena;
	}
	
	/**
	 * Gets the date oracle.
	 *
	 * @param campo the campo
	 * @return the date oracle
	 */
	public static java.sql.Date getDateOracle(String campo){
		java.sql.Date f1sql = null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(!campo.trim().equals("")){
				java.util.Date f1 = formatter.parse(campo);				
				f1sql = new java.sql.Date(f1.getTime());
			}
		} catch (ParseException e) {
			f1sql=null;
		}
		return f1sql;
	}
	
	/**
	 * Gets the date time oracle.
	 *
	 * @param campo the campo
	 * @return the date time oracle
	 */
	public static java.sql.Date getDateTimeOracle(String campo){
		java.sql.Date f1sql = null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		try {
			if(!campo.trim().equals("")){
				java.util.Date f1 = formatter.parse(campo);				
				f1sql = new java.sql.Date(f1.getTime());
			}
		} catch (ParseException e) {
			f1sql=null;
		}
		return f1sql;
	}
	
	/**
	 * Convertir fecha string.
	 *
	 * @param fecha the fecha
	 * @return the string
	 */
	public static String convertirFechaString(Date fecha) {
		String resultado = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(fecha!=null){
				resultado = df.format(fecha);
			}
		} catch (Exception e) {
			resultado = "";


		}
		return resultado;
	}
	
	/**
	 * Format fecha stringdd MMYYY.
	 *
	 * @param fecha the fecha
	 * @return the string
	 */
	public static String formatFechaStringddMMYYY(java.sql.Date fecha){
		String resultado = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(fecha!=null){
				resultado = df.format(fecha);
			}
		} catch (Exception e) {
			resultado = "";
		}
		return resultado;
	}
	

	/**
	 * Método que permite validar la precisión de un texto numérico decimal.
	 *
	 * @param strValue Es el valor que se quiere validar
	 * @param intDigEnt Es el número de dígitos permitidos en la parte entera del dato
	 * @param intDigDec Es el número de dígitos permitidos en la parte decimal del dato
	 * @return true, if successful
	 * @Return boolean que informa si el decimal es o no válido
	 */
	public static boolean longValidInputDecimal(String strValue, int intDigEnt, int intDigDec){
		String strNum = strValue;
		if(strNum.contains(".")){
			String[] parts = strNum.split("\\.");
			return (parts[0].length() <= intDigEnt && parts[1].length() <= intDigDec);
		}
		else{
			return (strNum.length() <= intDigEnt);
		}
	}
	
	/**
	 * Método que permite saber si un texto está en una lista de textos.
	 *
	 * @param collection Es la colección donde se busca
	 * @param value Es el valor que se busca en la colección
	 * @return true, if successful
	 * @Return boolean que informa si el texto se encuentra en la colección
	 */
	public static boolean valueInCollection(String[] collection, String value){
		for(String elemento : collection){
			if(elemento.equals(value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que permite obtener el número literal del día actual.
	 *
	 * @return the actual date
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return entero con el resultado
	 */
	public static int getActualDate() throws Exception{
		Calendar cal = DateUtils.getCalendar();
		int day = cal.get(Calendar.DATE);
		return day;
	}
	
	/**
	 * Método que permite saber si un periodo es el periodo actual.
	 *
	 * @param period the period
	 * @return true, if is actual period
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return booleano que indica si es o no el periodo actual
	 */
	public static boolean isActualPeriod(String period) throws Exception{
		Calendar cal = DateUtils.getCalendar();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String periodoActual = year+month;
		return periodoActual.equals(period);
	}
	
	/**
	 * Método que permite saber la cantidad de días que tiene un periodo específico (período está a nivel de mes).
	 *
	 * @param period Es el periodo del que se quiere saber la cantidad de días
	 * @return the int
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return entero con la información de días
	 */
	public static int daysOfMonthFromPeriod(String period) throws Exception{
		Date date = DateUtils.toYyyyMMddToDate(period+"01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Método que permite saber si una fecha en un determinado formato es válido.
	 *
	 * @param date es la fecha que se desea validar
	 * @param formatParse es el formato en el que está la fecha
	 * @return true, if is valid date
	 * @Return booleano que indica si la fecha es o no válida
	 */
	public static boolean isValidDate(String date, String formatParse){
		String formatString = formatParse == null ? "dd/MM/yyyy" : formatParse;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
	}
	
	/**
	 * Método que permite validar la precisión de un texto numérico decimal.
	 *
	 * @param num Es el valor que se quiere validar
	 * @param numInts Es el número de dígitos permitidos en la parte entera del dato
	 * @param numFloats Es el número de dígitos permitidos en la parte decimal del dato
	 * @return true, if is valid decimal
	 * @Return boolean que informa si el decimal válido o no
	 */
	public static boolean isValidDecimal(String num, int numInts, int numFloats){
		return num.matches(Constants.REGEX_DECIMAL_NUMBERS.replace(Constants.PARAM1, numInts+"").replace(Constants.PARAM2, numFloats+""));
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("-1.2".matches("^\\d{1,2}(\\.\\d{1,4})?$"));
	}
	
	/**
	 * Método que permite calcular el promedio de una lista de cadenas de texto numéricas.
	 *
	 * @param row Es la lista de valores que se quieren promediar
	 * @return the string
	 * @Return Objeto de tipo String con el promedio de los datos
	 */
	public static String calculateRowAverage(List<String> row){
		List<String> data = row;
		boolean bolExisteAlMenosUnDatoFila = false;
		double dblCaudalPromedioDia = 0;
		int contadorDatos = 0;
		for(String cell : data){
			if(!cell.trim().equals("")){
				// Si encuentra alguna celda con dato
				bolExisteAlMenosUnDatoFila = true;
				try {
					dblCaudalPromedioDia = dblCaudalPromedioDia + Double.parseDouble(cell.toString());
					contadorDatos++;
				} catch (Exception e) {
				}
			}
		}
		dblCaudalPromedioDia = dblCaudalPromedioDia / contadorDatos;
		dblCaudalPromedioDia = Math.round(dblCaudalPromedioDia * 10000)/10000D;
		// Si es que existe dato, retornar el promedio, sino vacio para que no lo compare en las matrices
		if(bolExisteAlMenosUnDatoFila){
			// para evitar la notacion exponencial PE: 12.3434E4
			return new BigDecimal(dblCaudalPromedioDia + "").stripTrailingZeros().toPlainString();
		}else{
			return "";
		}
	}
	
	/**
	 * Método que permite comparar una fecha (en un determinado formato) con la actual.
	 *
	 * @param date es la fecha que se desea comparar con la actual
	 * @param formatParse es el formato en el que está la fecha a comparar
	 * @return the int
	 * @Return entero con el resultado de la comparación
	 */
	public static int compareWithToday(String date, String formatParse){
		String formatString = formatParse == null ? "dd/MM/yyyy" : formatParse;
		try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            Date input = format.parse(date);
            Date today = new Date();
            return input.compareTo(today);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return 0;
	}
	
	/**
	 * Método que permite obtener el literal del día y el periodo a raíz de una fecha en formato dd/MM/yyyy.
	 *
	 * @param strDate es la fecha que se desea disgregar
	 * @return the day and period from date
	 * @Return mapa con info de día y periodo
	 */
	public static Map<String, String> getDayAndPeriodFromDate(String strDate){
		if(strDate.length() == 10){
			strDate = strDate.substring(0, 10);
		}
		else if(strDate.length() == 9){
			strDate = strDate.substring(0, 9);
		}
		Map<String, String> informacion = new HashMap<>();
		try {
			String[] valuesDate = strDate.split("/");
			informacion.put("dia", valuesDate[0]);
			informacion.put("periodo", valuesDate[2] + valuesDate[1]);
		} catch (Exception e) {
		}
		return informacion;
	}
	
	/**
	 * Método que permite obtener un mes en texto en base a su valor numérico.
	 *
	 * @param monthNumber es el mes en formato numérico
	 * @return the month name
	 * @Return Objeto de tipo String con el resultado
	 */
	public static String getMonthName(String monthNumber){
		switch (monthNumber) {
		case "01":
			return "ENERO";
		case "02":
			return "FEBRERO";
		case "03":
			return "MARZO";
		case "04":
			return "ABRIL";
		case "05":
			return "MAYO";
		case "06":
			return "JUNIO";
		case "07":
			return "JULIO";
		case "08":
			return "AGOSTO";
		case "09":
			return "SETIEMBRE";
		case "10":
			return "OCTUBRE";
		case "11":
			return "NOVIEMBRE";
		case "12":
			return "DICIEMBRE";
		default:
			return "";
		}
	}
	
	/**
	 * Round.
	 *
	 * @param value the value
	 * @param places the places
	 * @return the double
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	/**
	 * Obtener diferencia fechas en HHMMSS.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the map
	 */
	public static Map<String, Long> obtenerDiferenciaFechasEnHHMMSS(long start, long end) {
		Map<String, Long> result = new HashMap<>();
		long timeDiff = Math.abs(end - start);
		result.put("hours", TimeUnit.MILLISECONDS.toHours(timeDiff));
		result.put("minutes", TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));
		       result.put("seconds", TimeUnit.MILLISECONDS.toSeconds(timeDiff) -
		               (TimeUnit.HOURS.toSeconds(TimeUnit.MILLISECONDS.toHours(timeDiff)) +
		                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)))));
		       return result;
		}
	
	/**
	 * Diferencia es mayor A configurado.
	 *
	 * @param hour1 the hour 1
	 * @param minute1 the minute 1
	 * @param second1 the second 1
	 * @param hourConfig the hour config
	 * @param minuteConfig the minute config
	 * @param secondConfig the second config
	 * @return true, if successful
	 */
	public static boolean diferenciaEsMayorAConfigurado(long hour1, long minute1, long second1,
		    long hourConfig, long minuteConfig, long secondConfig){
		    Calendar cal1 = Calendar.getInstance();
		    cal1.set(Calendar.HOUR_OF_DAY, (int) hour1);
		    cal1.set(Calendar.MINUTE, (int) minute1);
		    cal1.set(Calendar.SECOND, (int) second1);

		    Calendar calConfig = Calendar.getInstance();
		    calConfig.set(Calendar.HOUR_OF_DAY, (int) hourConfig);
		    calConfig.set(Calendar.MINUTE, (int) minuteConfig);
		    calConfig.set(Calendar.SECOND, (int) secondConfig);

		    return cal1.after(calConfig); // cal1 mayor que configurado
		   }
	

}
