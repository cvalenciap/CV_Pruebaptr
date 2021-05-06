//Autor			Empresa			Fecha de Creación
//*****			*******			*****************
//CVALENCIAP		CANVIA			15/08/2019
package pe.com.sedapal.scr.correo.util;

import static java.text.MessageFormat.format;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.MensajeUtil;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;

public class ExchangeUtil {
	
	final static Logger logger = Logger.getLogger(ExchangeUtil.class);
	
	private static ExchangeService exchangeService;
	
	private static final String URL_SERVICE_OFFICE_365 = "asmx.url.office.365";
	
	public static  ExchangeService getExchangeService(ConfiguracionCorreo configCorreo) throws GmdException{
		try{
			exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
			WebCredentials credentials = new WebCredentials(configCorreo.getCuentaCorreo().getStrCorreo(), configCorreo.getCuentaCorreo().getStrClave());
			exchangeService.setCredentials(credentials);
			String URI = obtenerPropiedad(URL_SERVICE_OFFICE_365);
			exchangeService.setUrl(new java.net.URI(URI));
			exchangeService.setTraceEnabled(true);
			logger.info("Conexion al correo establecida");
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
		}
		return exchangeService;
	}
	
	public static String obtenerPropiedad(String value) throws GmdException {
		String property = null;
		try{
			Properties prpMensajes = new Properties();
			InputStream inputStream = ExchangeUtil.class.getClassLoader().getResourceAsStream("correo.properties");
			prpMensajes.load(inputStream);
			property = prpMensajes.getProperty(value);
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
		}
		return property;
	}
	
	
}
