/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.beans.CommSeccionArchivoAdjuntoBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICommSubidaArchivosService.
 */
public interface ICommSubidaArchivosService {
	
	/**
	 * Archivo es valido.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @param listaArchivoAdjuntoBean the lista archivo adjunto bean
	 * @param cantidadMaximaPermitida the cantidad maxima permitida
	 * @param expRegTipArch the exp reg tip arch
	 * @param tamanhoMaximo the tamanho maximo
	 * @return the map
	 */
	public Map<String, Object> archivoEsValido(CommArchivoAdjuntoBean archivoAdjuntoBean,
			CommSeccionArchivoAdjuntoBean listaArchivoAdjuntoBean, int cantidadMaximaPermitida, String expRegTipArch,
			Long tamanhoMaximo);

	/**
	 * Obtener file stream.
	 *
	 * @param ruta the ruta
	 * @return the input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public InputStream obtenerFileStream(String ruta) throws IOException;

	/**
	 * Copiar archivo en carpeta temporal.
	 *
	 * @param multipartFile the multipart file
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @return the comm archivo adjunto bean
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CommArchivoAdjuntoBean copiarArchivoEnCarpetaTemporal(MultipartFile multipartFile,
			CommArchivoAdjuntoBean archivoAdjuntoBean) throws IOException;

	/**
	 * Eliminar archivo de carpeta compartida.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean eliminarArchivoDeCarpetaCompartida(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception;

	/**
	 * Copiar archivo en carpeta compartida.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @param mapParametrosDocumento the map parametros documento
	 * @param mapParametrosRutaCompartida the map parametros ruta compartida
	 * @return true, if successful
	 */
	public boolean copiarArchivoEnCarpetaCompartida(CommArchivoAdjuntoBean archivoAdjuntoBean,
			Map<String, String> mapParametrosDocumento, Map<String, String> mapParametrosRutaCompartida);

	/**
	 * Eliminar archivo de carpeta temporal.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean eliminarArchivoDeCarpetaTemporal(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception;
	
	/**
	 * Limpiar temporales.
	 *
	 * @param now the now
	 */
	public void limpiarTemporales(Date now);

}
