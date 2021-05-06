/**
 * 
 * Resumen.
 * Objeto 				: LeerMensajesTask
 * Descripción 			: Clase relacionada con la lectura y proceso de mensajes
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 08/09/2020		cvalenciap				actualizacion
 *
 */
package pe.com.sedapal.scr.correo.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.beans.AdjuntoMensaje;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.common.Messages;

public class LeerMensajesTask {
	
	final static Logger logger = Logger.getLogger(LeerMensajesTask.class);
	
	private final ExchangeService exchangeService;
	private final ConfiguracionCorreo configuracionCorreo;
	
	public LeerMensajesTask(final ConfiguracionCorreo configuracionCorreo, 
			                final ExchangeService exchangeService) throws Exception {
		this.configuracionCorreo = configuracionCorreo;
		this.exchangeService = exchangeService;
	}
	
	/**
	 * Método que permite leer y validar mensajes 
	 * @Return Objeto de tipo List<MensajeCorreo> con los mensajes leídos y validados 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public List<MensajeCorreo> leerMensajes(final Integer cantidadCorreosLeer) throws GmdException {
		List<MensajeCorreo> mensajes = new ArrayList<MensajeCorreo>(0);
		try {
			Folder inboxFolder = Folder.bind(exchangeService, WellKnownFolderName.Inbox);
			
			SearchFilter searchFilter 
				= new SearchFilter.SearchFilterCollection(LogicalOperator.And, new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false));
			ItemView itemView = new ItemView(cantidadCorreosLeer);
			// Sorting (decreciente por fecha de recibido)
			itemView.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Descending);
			
			FindItemsResults<Item> unreadItems = exchangeService.findItems(inboxFolder.getId(), searchFilter, itemView);
			List<Item> items = unreadItems.getItems();
			
			for(Item unreadItem : items) {
				MensajeCorreo mensajeCorreo = leerMensaje(unreadItem);
				if(mensajeCorreo != null) {
					mensajeCorreo.setBolValid(true);
					mensajes.add(mensajeCorreo);
				}
			}
			
			// Validamos que hayan mensajes nuevos (sin leer)
			if(items.isEmpty()){ // Si no habían mensajes nuevos
				MensajeCorreo mensajeCorreo = new MensajeCorreo();
				mensajeCorreo.setBolValid(false);
				mensajeCorreo.setStrMensajeError(Messages.MENSAJE_NO_NUEVOS);
				mensajes.add(mensajeCorreo);
			}
			else{ // Validamos si se han cumplido las condiciones
				// 1) asunto 2) remitente 3) nombre adjunto 4) existencia de correo nuevo con adjunto
				if(mensajes.isEmpty()){
					MensajeCorreo mensajeCorreo = new MensajeCorreo();
					mensajeCorreo.setBolValid(false);
					mensajeCorreo.setStrMensajeError(Messages.MENSAJE_CORREO_NO_VALIDO);
					mensajes.add(mensajeCorreo);
				}
			}
		} catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return mensajes;
	}
	
	/**
	 * Método que permite leer un mensaje 
	 * @Return Objeto de tipo MensajeCorreo con el contenido del mensaje
	 * @throws Exception Excepción que puede ser lanzada
	 */
	private MensajeCorreo leerMensaje(final Item item) throws ServiceLocalException, GmdException {
		MensajeCorreo mensajeCorreo = null;
		try {
			EmailMessage emailMessage = EmailMessage.bind(exchangeService, item.getId());
			
			if(emailMessage.getHasAttachments() &&
					configuracionCorreo.getMensajeCorreo().getStrRemitente().equals(emailMessage.getFrom().getAddress()) &&
					configuracionCorreo.getMensajeCorreo().getStrAsunto().equals(emailMessage.getSubject())) {
//			if(emailMessage.getHasAttachments() &&
//					("cvalenciap@canvia.com").equals(emailMessage.getFrom().getAddress()) &&
//					("pruebaCaudales").equals(emailMessage.getSubject())) {
				FileAttachment  fileAttachment = (FileAttachment) emailMessage.getAttachments().getItems().get(0);
				fileAttachment.load();
				
				if(configuracionCorreo.getMensajeCorreo().getStrNombreAdjuntoConfig().equals(fileAttachment.getName())) {
//				if(("sedapal_1.txt").equals(fileAttachment.getName())) {
					mensajeCorreo = new MensajeCorreo();
					mensajeCorreo.setStrIdMensaje(emailMessage.getId().getUniqueId());
					mensajeCorreo.setStrRemitente(emailMessage.getSender().getAddress());
					mensajeCorreo.setStrAsunto(emailMessage.getSubject());
					
					List<AdjuntoMensaje> adjuntos = new ArrayList<AdjuntoMensaje>();
					AdjuntoMensaje adjuntoMensaje = new AdjuntoMensaje();
					adjuntoMensaje.setStrNombreAdjunto(fileAttachment.getName());
					adjuntoMensaje.setBytArchivoAdjunto(fileAttachment.getContent());
					
					adjuntos.add(adjuntoMensaje);
					
					mensajeCorreo.setLstAdjuntos(adjuntos);
				}
			}
		} catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return mensajeCorreo;
	}
	
	public static class RedirectionUrlCallback implements IAutodiscoverRedirectionUrl {
        public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl) {
          return redirectionUrl.toLowerCase().startsWith("https://");
        }
    }
	
}
