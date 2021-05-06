/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {
	
	/**
	 * Gets the page call.
	 *
	 * @return the page call
	 */
	public static String getPageCall(){
		String strProgram = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		strProgram = request.getHeader("Referer");
		strProgram = strProgram.substring(strProgram.lastIndexOf("/")+1);
		return strProgram;
	}
	
	/**
	 * Gets the user loged.
	 *
	 * @return the user loged
	 */
	public static String getUserLoged(){
		String strUser = "";
		//TODO implemetar la logica para obtener el usuario logeado
		return strUser;
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
	 * Fmt.
	 *
	 * @param d the d
	 * @return the string
	 */
	public static String fmt(double d){
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%s",d);
	}
/*	public static void main(String[] args) {
		Util.StringToUPPER(null);
		
	}*/

}
