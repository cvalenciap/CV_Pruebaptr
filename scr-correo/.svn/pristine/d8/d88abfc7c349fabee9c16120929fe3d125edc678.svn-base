/**
 * 
 * Resumen.
 * Objeto 				: GuardarArchivoCaudalesTask
 * Descripción 			: Clase relacionada con la lectura y proceso de archivos
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

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.beans.ServidorArchivos;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.common.Messages;
import pe.com.sedapal.scr.correo.core.helper.CaudalDetalleHelper;

public class LeerArchivoCaudalesTask {
	
	final static Logger logger = Logger.getLogger(LeerArchivoCaudalesTask.class);
	
	private final ServidorArchivos servidorArchivos;
	private final List<RioBean> lstRios;
	private final CaudalDetalleHelper caudalDetalleHelper;

	public LeerArchivoCaudalesTask(final ServidorArchivos servidorArchivos, final List<RioBean> lstRios, final GregorianCalendar todayAtNow) {
		this.servidorArchivos = servidorArchivos;
		this.lstRios = lstRios;
		
		caudalDetalleHelper = new CaudalDetalleHelper(todayAtNow);
	}
	
	/**
	 * Método que permite leer y procesar el contenido de un archivo 
	 * @Return Objeto de tipo List<Caudal> con el contenido de un archivo (leido) 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public List<Caudal> leerCaudales(final MensajeCorreo mensajeCorreo) throws GmdException {
		List<Caudal> lstCaudales = new ArrayList<Caudal>();
		try {
			// Leemos el contenido del archivo txt
			List<List<String>> lstContenidoArchivo 
				= caudalDetalleHelper.leerContenidoArchivo(servidorArchivos.getStrUrl(), lstRios, servidorArchivos.getIntRutaDefault(), 
						mensajeCorreo.getStrNombreAdjuntoGuardado());
			
			// Procesamos el contenido extraido del archivo txt
			lstCaudales = caudalDetalleHelper.extraerCaudales(lstContenidoArchivo, lstRios);
		} catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
		// Retornamos los caudales extraidos
		return lstCaudales;
	}
	
	/**
	 * Método que permite validar un archivo (adjunto) 
	 * @Return Objeto de tipo boolean con el contenido true o false 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public boolean isArchivoAdjuntoValido() {
		if(caudalDetalleHelper.getSetMensajesValidacion()!=null &&
				caudalDetalleHelper.getSetMensajesValidacion().size()>0 &&
				caudalDetalleHelper.contieneTipoError(caudalDetalleHelper.getSetMensajesValidacion(), Constants.ERROR_TIPO_NO_ESPERADO)){
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Método que permite acumular mensajes de validación
	 * @Return Objeto de tipo String con el contenido de los mensajes de validación 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public String getMensajeValidacion(){
		StringBuilder sbRio = new StringBuilder("");
		StringBuilder sbCaudal = new StringBuilder("");
		StringBuilder sbCaudalPasado = new StringBuilder("");
		StringBuilder sbCaudalFuturo = new StringBuilder("");
		StringBuilder sbSinCaudal = new StringBuilder("");
		
		for(String element : caudalDetalleHelper.getSetMensajesValidacion()){
			String[] partsError = element.split(Constants.ERROR_SEPARATOR_TYPE);
			if(partsError.length == 2){
				if(partsError[0].equals(Constants.ERROR_TIPO_RIO)){
					sbRio.append(Messages.HTML_TAB).append(Messages.HTML_CHECK).append(partsError[1]).append(Messages.HTML_NEWLINE);
				}
				else if(partsError[0].equals(Constants.ERROR_TIPO_CAUDAL)){
					sbCaudal.append(Messages.HTML_TAB).append(Messages.HTML_CHECK).append(partsError[1]).append(Messages.HTML_NEWLINE);
				}
				else if(partsError[0].equals(Constants.ERROR_TIPO_CAUDAL_PASADO)){
					sbCaudalPasado.append(Messages.HTML_TAB).append(Messages.HTML_CHECK).append(partsError[1]).append(Messages.HTML_NEWLINE);
				}
				else if(partsError[0].equals(Constants.ERROR_TIPO_CAUDAL_FUTURO)){
					sbCaudalFuturo.append(Messages.HTML_TAB).append(Messages.HTML_CHECK).append(partsError[1]).append(Messages.HTML_NEWLINE);
				}
				else if(partsError[0].equals(Constants.ERROR_TIPO_SIN_CAUDAL)){
					sbSinCaudal.append(Messages.HTML_TAB).append(Messages.HTML_CHECK).append(partsError[1]).append(Messages.HTML_NEWLINE);
				}
			}
		}
		
		StringBuilder mensajeFinal = new StringBuilder(Messages.MENSAJE_ARCHIVO_ERRORES_HEADER);
		if(!sbRio.toString().equals("")){
			mensajeFinal.append(Messages.MENSAJE_ARCHIVO_ERRORES_RIO).append(sbRio.toString());
		}
		if(!sbCaudal.toString().equals("")){
			mensajeFinal.append(Messages.MENSAJE_ARCHIVO_ERRORES_CAUDAL).append(sbCaudal.toString());
		}
		if(!sbCaudalPasado.toString().equals("")){
			mensajeFinal.append(Messages.MENSAJE_ARCHIVO_ERRORES_CAUDAL_PASADO).append(sbCaudalPasado.toString());
		}
		if(!sbCaudalFuturo.toString().equals("")){
			mensajeFinal.append(Messages.MENSAJE_ARCHIVO_ERRORES_CAUDAL_FUTURO).append(sbCaudalFuturo.toString());
		}
		if(!sbSinCaudal.toString().equals("")){
			mensajeFinal.append(Messages.MENSAJE_ARCHIVO_ERRORES_SIN_CAUDAL).append(sbSinCaudal.toString());
		}
		
		String mensajeFinalValid = mensajeFinal.toString();
		if(mensajeFinalValid.length() > Constants.MAX_SIZE_DESC_ERROR){
			mensajeFinalValid = mensajeFinalValid.substring(0, Constants.MAX_SIZE_DESC_ERROR);
			mensajeFinalValid = mensajeFinalValid + Messages.HTML_NEWLINE + "...";
		}
		
		return mensajeFinalValid;
	}
	
	public Set<String> getSetMensajesValidacion(){
		return caudalDetalleHelper.getSetMensajesValidacion();
	}
}
