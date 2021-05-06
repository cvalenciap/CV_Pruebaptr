/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.configuration.log;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class UserConverter.
 */
public class UserConverter extends ClassicConverter{

	/**
	 * Método que permite obtener el nombre de usuario.
	 *
	 * @param arg0 Es un parámetro del entorno
	 * @return String con el valor
	 */
	@Override
	public String convert(ILoggingEvent arg0) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
            return auth.getName();
		}
		return "NO_USER";
	}

}
