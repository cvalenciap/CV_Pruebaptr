/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.beans.CommSeccionArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.services.ICommSubidaArchivosService;
import pe.com.sedapal.scr.core.util.Utils;


// TODO: Auto-generated Javadoc
/**
 * The Class CommSubidaArchivosServiceImpl.
 */
@Service
public class CommSubidaArchivosServiceImpl implements ICommSubidaArchivosService {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(CommSubidaArchivosServiceImpl.class);

	/** The message source. */
	@Autowired
    private MessageSource messageSource;
	
	/** The environment. */
	@Autowired
	private Environment environment;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#archivoEsValido(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean, pe.com.sedapal.scr.core.beans.CommSeccionArchivoAdjuntoBean, int, java.lang.String, java.lang.Long)
	 */
	@Override
	public Map<String, Object> archivoEsValido(CommArchivoAdjuntoBean archivoAdjuntoBean,
			CommSeccionArchivoAdjuntoBean seccion, int cantidadMaximaPermitida, String expRegTipArch,
			Long tamanhoMaximo) {
		Map<String, Object> mapRespuesta = new HashMap<String, Object>();
		String parametro = messageSource.getMessage("validacion.adj.archivo.param1", null, Locale.getDefault());
		boolean archivoEsValido = true;
		String claveMensaje = null;
		String valorMensaje = null;
		String tipArch = archivoAdjuntoBean.getExtensionArchivo().toLowerCase();
		LinkedList<CommArchivoAdjuntoBean> listaArchivoAdjuntoBean = seccion.getLstArchivoAdjunto();
		String extensiones = expRegTipArch;
		extensiones = extensiones.replace("(", "");
		extensiones = extensiones.replace(")$", "");
		extensiones = extensiones.replace("|", ", ");
		Pattern p = Pattern.compile(expRegTipArch.toLowerCase());
		Matcher m = p.matcher(tipArch);
		if (listaArchivoAdjuntoBean.contains(archivoAdjuntoBean)) {
			archivoEsValido = false;
			claveMensaje = "subidaArchivoRepetido";
			valorMensaje = messageSource.getMessage("validacion.adj.archivo.repetido", null, Locale.getDefault());
		} else if (cantidadMaximaPermitida < listaArchivoAdjuntoBean.size() + 1) {
			archivoEsValido = false;
			claveMensaje = "subidaArchivoCantidadNoOk";
			valorMensaje = messageSource.getMessage("validacion.adj.archivo.cantidad", null, Locale.getDefault()).replace(parametro, cantidadMaximaPermitida+"");
		} else if (!m.matches()) {
			archivoEsValido = false;
			claveMensaje = "subidaArchivoTipoNoOk";
			valorMensaje = messageSource.getMessage("validacion.adj.archivo.tipo", null, Locale.getDefault()).replace(parametro, extensiones);
		} else if (archivoAdjuntoBean.getTamanhoKilobytes().longValue() > tamanhoMaximo.longValue()) {
			archivoEsValido = false;
			claveMensaje = "subidaArchivoTamanhoNoOk";
			valorMensaje = messageSource.getMessage("validacion.adj.archivo.peso", null, Locale.getDefault()).replace(parametro, tamanhoMaximo.longValue()+"");
		}
		mapRespuesta.put("esValido", archivoEsValido);
		mapRespuesta.put("codMensaje", claveMensaje);
		mapRespuesta.put("valMensaje", valorMensaje);
		return mapRespuesta;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#obtenerFileStream(java.lang.String)
	 */
	@Override
	public InputStream obtenerFileStream(String ruta) throws IOException {
		InputStream inputResponse = null;
		File f = new File(ruta);
		inputResponse = new FileInputStream(f);
		return inputResponse;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#copiarArchivoEnCarpetaTemporal(org.springframework.web.multipart.MultipartFile, pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	public CommArchivoAdjuntoBean copiarArchivoEnCarpetaTemporal(MultipartFile multipartFile,
	CommArchivoAdjuntoBean archivoAdjuntoBean) throws IOException {
	    String directory = System.getProperty("java.io.tmpdir");
		String filepath = Paths.get(directory, environment.getRequiredProperty("parametro.temp.dir.folder"), archivoAdjuntoBean.getNombreTemporal()).toString();
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(new File(filepath)));
		archivoAdjuntoBean.setRutaTemporalArchivo(filepath);
		return archivoAdjuntoBean;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#eliminarArchivoDeCarpetaCompartida(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	public boolean eliminarArchivoDeCarpetaCompartida(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		File file = new File(archivoAdjuntoBean.getRutaArchivo());
		boolean exito = false;
		exito = file.delete();
		return exito;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#copiarArchivoEnCarpetaCompartida(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean, java.util.Map, java.util.Map)
	 */
	@Override
	public boolean copiarArchivoEnCarpetaCompartida(CommArchivoAdjuntoBean archivoAdjuntoBean,
			Map<String, String> mapParametrosDocumento, Map<String, String> mapParametrosRutaCompartida) {
		boolean exito = false;
		try {
			String strRutaCompartida = mapParametrosRutaCompartida.get("rutaCompartida");
			String strRaizArchivosAdjuntos = mapParametrosRutaCompartida.get("raizArchivosAdjuntos");
			StringBuilder sb = new StringBuilder();
			sb.append(strRutaCompartida).append(File.separator).append(strRaizArchivosAdjuntos).append(File.separator);
			File dst = new File(sb.toString());
			dst.mkdir();
			sb.append(mapParametrosDocumento.get("anhoDocumento")).append(File.separator);
			dst = new File(sb.toString());
			dst.mkdir();
			sb.append(mapParametrosDocumento.get("tipoDocumento")).append(File.separator);
			dst = new File(sb.toString());
			dst.mkdir();
			sb.append(mapParametrosDocumento.get("codigoDocumento")).append(File.separator);
			dst = new File(sb.toString());
			dst.mkdir();
			sb.append(archivoAdjuntoBean.getCodigoSeccion()).append(File.separator);
			dst = new File(sb.toString());
			dst.mkdir();
			sb.append(archivoAdjuntoBean.getNombreArchivoOriginal());
			archivoAdjuntoBean.setRutaArchivo(sb.toString());
			dst = new File(sb.toString());
			FileCopyUtils.copy(new File(archivoAdjuntoBean.getRutaTemporalArchivo()), dst);
			logger.debug("Se copió el archivo en la ruta: " + dst.getAbsolutePath());
			exito = true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			exito = false;
		}
		return exito;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#eliminarArchivoDeCarpetaTemporal(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	public boolean eliminarArchivoDeCarpetaTemporal(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		File file = new File(archivoAdjuntoBean.getRutaTemporalArchivo());
		boolean exito = false;
		exito = file.delete();
		return exito;
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommSubidaArchivosService#limpiarTemporales(java.util.Date)
	 */
	@Override
	public void limpiarTemporales(Date now) {
		try {
		String tempDir = System.getProperty("java.io.tmpdir");
		System.out.println("tempDir:: "+tempDir);
		String filepath = Paths.get(tempDir, environment.getRequiredProperty("parametro.temp.dir.folder")).toString();
		File directory = new File(filepath);
		File[] files = directory.listFiles();
		for (File f : files){
		Map<String, Long> difference = Utils.obtenerDiferenciaFechasEnHHMMSS(f.lastModified(), now.getTime());
		long hours = difference.get("hours");
		long minutes = difference.get("minutes");
		long seconds = difference.get("seconds");
		long hoursConf = Long.parseLong(environment.getRequiredProperty("parametro.temp.dir.clear.hours"));
		long minutesConf = Long.parseLong(environment.getRequiredProperty("parametro.temp.dir.clear.minutes"));
		long secondsConf = Long.parseLong(environment.getRequiredProperty("parametro.temp.dir.clear.seconds"));
		if(Utils.diferenciaEsMayorAConfigurado(hours, minutes, seconds, hoursConf, minutesConf, secondsConf)){
		f.delete();
		}
		}
		}
		catch (Exception e) {
		logger.error(e.getMessage(), e);
		}
	}

}
