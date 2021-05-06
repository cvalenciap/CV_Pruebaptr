/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtil.
 */
public class DateUtil {

	/**
	 * Método que permite obtener la fecha actual como texto.
	 *
	 * @param formato Es el formato en el que se quiere mostrar la fecha
	 * @return the string
	 * @Return Objeto de tipo String que contiene el resultado
	 */
	public static String ahoraEnCadena(String formato) {
        return convertirACadena(ahora(), formato);
    }
	
	/**
	 * Método que permite obtener la fecha actual.
	 *
	 * @return the date
	 * @Return Objeto de tipo Date que contiene el resultado
	 */
	public static Date ahora() {
        return new Date();
    }	
	
	/**
	 * Método que permite convertir una fecha específica en texto.
	 *
	 * @param fecha Es la fecha que se desea convertir a texto
	 * @param formato Es el formato en el que se quiere mostrar la fecha
	 * @return the string
	 * @Return Objeto de tipo String que contiene el resultado
	 */
	public static String convertirACadena(Date fecha, String formato) {
        String result = "";
        if (fecha != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            result = dateFormat.format(fecha);
        }

        return result;
    }
	
}
