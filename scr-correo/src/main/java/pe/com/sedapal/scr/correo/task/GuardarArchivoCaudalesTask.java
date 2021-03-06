/**
 * 
 * Resumen.
 * Objeto 				: GuardarArchivoCaudalesTask
 * Descripción 			: Clase relacionada con los adjuntos de correo
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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.beans.AdjuntoMensaje;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.beans.ServidorArchivos;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.ArchivoUtil;

public class GuardarArchivoCaudalesTask {
	
	final static Logger logger = Logger.getLogger(GuardarArchivoCaudalesTask.class);
	
	private final ServidorArchivos servidorArchivos;
	
	public GuardarArchivoCaudalesTask(final ServidorArchivos servidorArchivos) {
		this.servidorArchivos = servidorArchivos;
	}
	
	/**
	 * Método que permite guardar adjunto(s) de un mensaje 
	 * @Return Objeto de tipo MensajeCorreo que contiene el nombre del adjunto guardado 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public MensajeCorreo guardarAdjunto(MensajeCorreo mensajeCorreo) throws GmdException{
		FileOutputStream fileOuputStream = null;
		try {
			try { 
				for(AdjuntoMensaje adjunto : mensajeCorreo.getLstAdjuntos()) {
					String strNombreArchivoFormateado = ArchivoUtil.formatearNombreArchivo(adjunto.getStrNombreAdjunto());
					mensajeCorreo.setStrNombreAdjuntoGuardado(strNombreArchivoFormateado);
					
					String pathFile = servidorArchivos.getStrUrl();
					System.out.println("pathFile:" + pathFile);
					
					if(servidorArchivos.getIntRutaDefault() == 0){
//						fileOuputStream = new FileOutputStream(pathFile + File.separator + strNombreArchivoFormateado); 
//						fileOuputStream.write(adjunto.getBytArchivoAdjunto());
//						fileOuputStream.close();
						InputStream inputStream = new ByteArrayInputStream(adjunto.getBytArchivoAdjunto());
						ArchivoUtil.copyFileUsingJcifs(inputStream, (pathFile + Constants.PATH_SHARED_FILE + strNombreArchivoFormateado));
						inputStream.close();
					}
					else{
						ClassLoader classloader = Thread.currentThread().getContextClassLoader();
						fileOuputStream = new FileOutputStream(classloader.getResource(pathFile).getPath() + File.separator + strNombreArchivoFormateado); 
						fileOuputStream.write(adjunto.getBytArchivoAdjunto());
						fileOuputStream.close();
					}
				}
			 } catch (IOException e) {
				 // No tiene acceso a la ruta del file
				 System.out.println("No hay conexion a la ruta del path");
				 mensajeCorreo.setStrNombreAdjuntoGuardado(null);
			 }
		} catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return mensajeCorreo;
	}
}