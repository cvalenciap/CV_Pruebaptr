/**
 * 
 * Resumen.
 * Objeto 				: ActualizarMensajeTask
 * Descripción 			: Clase relacionada con los mensajes de correo
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.task;

import java.util.List;

import org.apache.log4j.Logger;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.schema.FolderSchema;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FolderView;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.common.Constants;

public class ActualizarMensajeTask {
	
	final static Logger logger = Logger.getLogger(ActualizarMensajeTask.class);
	
	private final ExchangeService exchangeService;
	
	public ActualizarMensajeTask(final ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}
	
	/**
	 * Método que permite marcar mensajes (como leídos)
	 * @Return Objeto de tipo int que contiene el numero de mensajes marcados (como leídos) 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public int marcarMensajes(final List<String> idsMensajes) throws Exception {
		int counter = 0;
		
		for(String idMensaje : idsMensajes) {
			ItemId itemId = new ItemId(idMensaje);
			
			EmailMessage emailMessage = EmailMessage.bind(exchangeService, itemId);
			emailMessage.setIsRead(true);
			
			emailMessage.update(ConflictResolutionMode.AutoResolve);
			emailMessage.move(WellKnownFolderName.ArchiveRoot);
			
			counter++;
		}
		
		return counter;
	}
	
	/**
	 * Método que permite marcar mensaje (como leído)
	 * y mover a una carpeta
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void marcarMensaje(final String idMensaje) throws GmdException {
		try {
			ItemId itemId = new ItemId(idMensaje);
			
			EmailMessage emailMessage = EmailMessage.bind(exchangeService, itemId);
			emailMessage.setIsRead(true);
			
			String strFolderId = getReadMessageFolder();
			
			if(strFolderId == null) {
				createReadMessageFolder();
				strFolderId = getReadMessageFolder();
			}
			
			FolderId folderId = new FolderId(strFolderId);
			emailMessage.update(ConflictResolutionMode.AutoResolve);
			emailMessage.move(folderId);
		} catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
		
	}
	
	/**
	 * Método que permite encontrar una carpeta
	 * @Return Objeto de tipo String que contiene el ID carpeta (encontrada)
	 * @throws Exception Excepción que puede ser lanzada
	 */
	private String getReadMessageFolder() throws Exception {
		String strFolderId = null;
		
		FolderView folderView = new FolderView(100);
		
		PropertySet propertySet = new PropertySet();
		propertySet.add(FolderSchema.DisplayName);
		propertySet.add(FolderSchema.Id);
		
		FindFoldersResults findResults = exchangeService.findFolders(WellKnownFolderName.Inbox, folderView);

        for(Folder folder : findResults.getFolders()) {
        	System.out.println("Folder " + folder.getDisplayName() + " found");
        	
            if (folder instanceof Folder && folder.getDisplayName().equals(Constants.CORREO_DIR_LEIDOS)) {
                strFolderId = folder.getId().getUniqueId();
                
                break;
            }
        }
		
		return strFolderId;
	}
	
	/**
	 * Método que permite crear carpeta
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	private void createReadMessageFolder() throws Exception {
		Folder folder = new Folder(exchangeService);
		folder.setDisplayName(Constants.CORREO_DIR_LEIDOS);
		folder.setFolderClass("IPF.Note");
		
		folder.save(WellKnownFolderName.Inbox);
	}
	
	public void returnInboxFolder(final String idMensaje) throws GmdException {
		try {
			ItemId itemId = new ItemId(idMensaje);
			EmailMessage emailMessage = EmailMessage.bind(exchangeService, itemId);
			Folder inboxFolder = Folder.bind(exchangeService, WellKnownFolderName.Inbox);
			emailMessage.setIsRead(false);
			emailMessage.update(ConflictResolutionMode.AutoResolve);
			emailMessage.move(inboxFolder.getId());
		} catch (Exception ex) {
			throw new GmdException(ex);
		}
	}
	
}
