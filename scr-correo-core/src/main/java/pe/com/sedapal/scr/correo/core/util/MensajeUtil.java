package pe.com.sedapal.scr.correo.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MensajeUtil {
	private static Properties prpMensajes = new Properties();
	
//	inicio cvalenciap
	public MensajeUtil() {
		try {
			InputStream inputStream = MensajeUtil.class.getClassLoader().getResourceAsStream("/correo.properties");
			prpMensajes.load(inputStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
//	fin cvalenciap
	
	static {
	    try {
	    	InputStream inputStream = MensajeUtil.class.getClassLoader().getResourceAsStream("/correo.properties");
			prpMensajes.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMensaje(final String claveMensaje) {
		return prpMensajes.getProperty(claveMensaje);
	}
	
	public static final String MENSAJE_RIOS_NO_VALIDOS = "mensaje.rios.no.validos";
	public static final String MENSAJE_FILA_CAUDAL_NO_VALIDO = "mensaje.fila.caudal.no.valido";
	public static final String MENSAJE_ARCHIVO_CORRUPTO = "mensaje.archivo.corrupto";
	
	public static final String MENSAJE_REMITENTE_CORREO = "mensaje.remitente.correo";
	
//	inicio cvalenciap
	public static final String URL_SERVICIO_OFFICE_365 = "mensaje.url.office.365";
//	fin cvalenciap
}
