/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.web.common.Constants;

	// TODO: Auto-generated Javadoc
/**
	 * The Class MessageController.
	 */
	@Controller
	@PropertySources(value = { @PropertySource(name = "props", value = {
			"classpath:application.properties" }, ignoreResourceNotFound = true) })
	public class MessageController {

		/** The Constant LOG. */
		private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
		
		/** The env. */
		@Autowired
		Environment env;

		/** The message source. */
		@Autowired
		MessageSource messageSource;
		
		/**
		 * Show message info.
		 *
		 * @param model the model
		 * @param request the request
		 * @param session the session
		 * @param idMessage the id message
		 * @return the string
		 */
		@RequestMapping(value = "/showMessageInfo/{idMessage}", method = RequestMethod.POST)
		public String showMessageInfo(ModelMap model, HttpServletRequest request, HttpSession session,@PathVariable int idMessage){
			String mensaje = obtenerMensajeXCodigo(idMessage);
			LOG.info("mensaje: ".concat(mensaje));
			
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensajeXCodigo(idMessage));
			model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_OK);
			model.addAttribute(Constants.MENSAJE_ERROR, "");
		
			return "generic/genericSave :: genericSave";
			
		}
		
		/**
		 * Show message error.
		 *
		 * @param model the model
		 * @param request the request
		 * @param session the session
		 * @param idMessage the id message
		 * @return the string
		 */
		@RequestMapping(value = "/showMessageError/{idMessage}", method = RequestMethod.POST)
		public String showMessageError(ModelMap model, HttpServletRequest request, HttpSession session,@PathVariable int idMessage){
		
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensajeXCodigo(idMessage));
			model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_NO_OK);
		
			return "generic/genericSave :: genericSave";
			
		}
		
		/**
		 * Obtener mensaje.
		 *
		 * @param messageProp the message prop
		 * @return the string
		 */
		public String obtenerMensaje(String messageProp){
			return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
		}
		
		/**
		 * Obtener mensaje X codigo.
		 *
		 * @param idMensaje the id mensaje
		 * @return the string
		 */
		public String obtenerMensajeXCodigo(int idMensaje){
			return messageSource.getMessage("codigo.mensaje."+idMensaje, new Object[] {}, Locale.US);
		}
		
	
}
