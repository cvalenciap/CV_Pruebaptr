package pe.com.sedapal.scr.correo.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FechaUtil {
	
	public final static String FECHAHORA_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";	
	
	public static String ahoraEnCadena(String formato) {
        return convertirACadena(ahora(), formato);
    }
	
	public static Date ahora() {
        return new Date();
    }	
	
	public static String convertirACadena(Date fecha, String formato) {
        String result = "";
        if (fecha != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            result = dateFormat.format(fecha);
        }

        return result;
    }	
	
	public static String getFechaHoraActual() {
		return convertirACadena(new Date(), FECHAHORA_YYYYMMDDHHMMSS);
	}    
    
    public static Integer currentYear() {
    	Calendar calendar = getCalendar();
    	return calendar.get(Calendar.YEAR);
    }
    
    public static Integer currentMonth() {
    	Calendar calendar = getCalendar();
    	return calendar.get(Calendar.MONTH);
    }
    
    public static Integer currentDay() {
    	Calendar calendar = getCalendar();
    	return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    public static Integer currentHourOfDay() {
    	Calendar calendar = getCalendar();
    	return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
    }
    
    public static String getPeriodoActual() {
		int year = FechaUtil.currentYear();
		int month = FechaUtil.currentMonth();
		
		month = month + 1;
		
		if(month < 10) {
			return "" + year + "0" + month;
		} else {
			return "" + year + "" + month;
		}
	}
    
    public static String getPeriodoArray(String[] date) {
		int year = Integer.valueOf(date[2]);
		int month = Integer.valueOf(date[1]);
		
		if(month < 10) {
			return "" + year + "0" + month;
		} else {
			return "" + year + "" + month;
		}
	}
    
}
