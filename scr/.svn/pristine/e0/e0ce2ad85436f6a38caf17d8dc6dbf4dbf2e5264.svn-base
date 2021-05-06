/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {

	/** The Constant SEPARADOR_EXTENSION. */
	static final String SEPARADOR_EXTENSION = ".";
	
	/** The Constant INDICE_CERO. */
	static final int INDICE_CERO = 0;
	
	/** The Constant KILO. */
	static final short KILO = 1024;

	/**
	 * Obtener archivo adjunto bean de multipart.
	 *
	 * @param multipartFile the multipart file
	 * @return the comm archivo adjunto bean
	 */
	public static CommArchivoAdjuntoBean obtenerArchivoAdjuntoBeanDeMultipart(MultipartFile multipartFile) {
		CommArchivoAdjuntoBean archivoAdjuntoBean = new CommArchivoAdjuntoBean();
		String strNombreArchivoOriginal = multipartFile.getOriginalFilename();
		Long lnTamanhoKilobytes = multipartFile.getSize() / KILO;
		String strTipoArchivo = multipartFile.getContentType();
		String strNombreSinExtension = obtenerNombreSinExtension(strNombreArchivoOriginal);
		String strExtensionArchivo = obtenerExtension(strNombreArchivoOriginal);
		String strNombreTemporal = getNombreArchivoTemporal(strNombreSinExtension, strExtensionArchivo);
		archivoAdjuntoBean.setExtensionArchivo(strExtensionArchivo);
		archivoAdjuntoBean.setNombreArchivoOriginal(strNombreArchivoOriginal);
		archivoAdjuntoBean.setNombreTemporal(strNombreTemporal);
		archivoAdjuntoBean.setNombreSinExtension(strNombreSinExtension);
		archivoAdjuntoBean.setTamanhoKilobytes(lnTamanhoKilobytes);
		archivoAdjuntoBean.setTipoArchivo(strTipoArchivo);
		return archivoAdjuntoBean;
	}

	/**
	 * Gets the nombre archivo temporal.
	 *
	 * @param fileShortName the file short name
	 * @param fileExtension the file extension
	 * @return the nombre archivo temporal
	 */
	public static String getNombreArchivoTemporal(String fileShortName, String fileExtension) {
		StringBuilder sb = new StringBuilder();
		sb.append(fileShortName).append(DateUtils.getYyyyMMddHHmmssWithoutDate()).append(SEPARADOR_EXTENSION)
				.append(fileExtension);
		return sb.toString();
	}

	/**
	 * Obtener nombre sin extension.
	 *
	 * @param nombreArchivo the nombre archivo
	 * @return the string
	 */
	public static String obtenerNombreSinExtension(String nombreArchivo) {
		return nombreArchivo.substring(INDICE_CERO, nombreArchivo.lastIndexOf(SEPARADOR_EXTENSION));
	}

	/**
	 * Obtener extension.
	 *
	 * @param nombreArchivo the nombre archivo
	 * @return the string
	 */
	public static String obtenerExtension(String nombreArchivo) {
		return nombreArchivo.substring(nombreArchivo.lastIndexOf(SEPARADOR_EXTENSION) + 1, nombreArchivo.length());
	}


}
