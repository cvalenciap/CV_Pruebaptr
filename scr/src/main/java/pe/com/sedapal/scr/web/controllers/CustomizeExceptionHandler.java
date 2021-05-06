/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomizeExceptionHandler.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
	
	/** The env. */
	@Autowired
	Environment env;

	/** The Constant DEFAULT_ERROR_VIEW. */
	public static final String DEFAULT_ERROR_VIEW = "exception";

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(CustomizeExceptionHandler.class);

	/**
	 * Método que permite gestionar la excepción MultipartException.
	 *
	 * @param exception Es la excepción lanzada
	 * @return Objeto de tipo ResponseEntity con un mensaje descriptivo
	 */
	@ExceptionHandler(value = MultipartException.class)
	public ResponseEntity<String> IllegalStateHandler(Exception exception) {
		logger.error(exception.getMessage());
		return new ResponseEntity<>("El archivo ingresado supera el tama&ntilde;o permitido ("+
				env.getProperty("spring.http.multipart.maxFileSize")+")", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
