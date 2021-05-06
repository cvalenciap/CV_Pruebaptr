/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.net.HttpHeaders;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.beans.CommPaginaArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.beans.CommParametrosBean;
import pe.com.sedapal.scr.core.beans.CommSeccionArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.beans.common.Estado;
import pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService;
//import pe.com.sedapal.scr.core.services.ICommParametrosService;
import pe.com.sedapal.scr.core.services.ICommSubidaArchivosService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class CommSubidaArchivosController.
 */
@Controller
public class CommSubidaArchivosController {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(CommSubidaArchivosController.class);

	/** The Constant COD_DOCUMENTO_NUEVO. */
	public final static int COD_DOCUMENTO_NUEVO = 0;
	
	/** The Constant ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR. */
	public final static String ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR = "listaArchivosEliminar";
	
	/** The Constant COD_ARCHIVO_NUEVO. */
	public final static int COD_ARCHIVO_NUEVO = 0;
	
	/** The Constant PESO_MIN_ARCH_ADJ. */
	public final static int PESO_MIN_ARCH_ADJ = 0;
	
	/** The Constant CANT_MIN_ARCH_ADJ. */
	public final static int CANT_MIN_ARCH_ADJ = 0; 

	/** The Constant ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS. */
	public final static String ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS = "paginaArchivosAdjuntos";

	/** The subida archivo service. */
	@Autowired
	ICommSubidaArchivosService subidaArchivoService;
	
	/** The archivo adjunto service. */
	@Autowired
	ICommArchivoAdjuntoService archivoAdjuntoService;
	//@Autowired
	/** The environment. */
	//ICommParametrosService parametrosService;
	@Autowired
	private Environment environment;
	
	/** The message source. */
	@Autowired
    private MessageSource messageSource;

	/**
	 * Upload.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/subir", method = RequestMethod.GET)
	public String upload() {
		return "COMM/formEjemploSubidaArchivos";
	}

	// Método que guarda en sesión un objeto para la pagina
	/**
	 * Configurar.
	 *
	 * @param atributos the atributos
	 * @param session the session
	 */
	// que contiene un mapa vacío para las secciones
	@RequestMapping(value = "/upload/configurar", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public void configurar(@RequestParam Map<String, String> atributos, HttpSession session) {
		logger.debug("Inicia configuracion");
		String strPagId = atributos.get(Constants.OBJ_JS_PAGINA_ID);
		String strAnhoDocumento = Constants.OBJ_JS_DOCUMENTO_ANHO; //atributos.get(Constants.OBJ_JS_DOCUMENTO_ANHO);
		String strTipoDocumento = atributos.get(Constants.OBJ_JS_DOCUMENTO_TIPO);
		String strCodDocumento = atributos.get(Constants.OBJ_JS_DOCUMENTO_COD);
		//String strCodParamTipPermArchAdjDocumento = atributos.get(Constants.OBJ_JS_DOCUMENTO_TIPARCH);
		//String strCodParamPesoMaxArchAdjDocumento = atributos.get(Constants.OBJ_JS_DOCUMENTO_PMAXARCH);

		logger.debug("configurar : " + strPagId);

		//CommParametrosBean parametrosBean = new CommParametrosBean();
		//parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamTipPermArchAdjDocumento));
		String strTipPermArchAdjDocumento =  environment.getProperty(Constants.PAR_TIP_ARCHIVOS); // parametrosBean.getValor();
		//parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamPesoMaxArchAdjDocumento));
		String strPesoMaxArchAdjDocumento = environment.getProperty(Constants.PAR_PESO_MAX_DOC); //parametrosBean.getValor();
		//parametrosBean = parametrosService
			//	.consultar(Integer.valueOf(environment.getProperty(Constants.PROP_APP_ARCHADJ_PMAX)));
		String strPesoMaxArchAdjApp =  Constants.PAR_PESO_MAX_APP;//parametrosBean.getValor();
		
		//parametrosBean = parametrosService.consultar(Integer.valueOf(environment.getProperty(Constants.PROP_APP_ARCHADJ_CMAX)));
		String strCantMaxArchAdjDoc = environment.getProperty(Constants.PAR_CANT_MAX_DOC); // parametrosBean.getValor();

		if (session.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId)) != null) {
			session.removeAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		}
		session.setAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId),
				new CommPaginaArchivoAdjuntoBean(strPagId, Integer.valueOf(strAnhoDocumento),
						Integer.valueOf(strTipoDocumento), Integer.valueOf(strCodDocumento), strTipPermArchAdjDocumento,
						Long.valueOf(strPesoMaxArchAdjDocumento), Long.valueOf(strPesoMaxArchAdjApp), Integer.valueOf(strCantMaxArchAdjDoc),
						new HashMap<String, CommSeccionArchivoAdjuntoBean>()));
		if (session.getAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId)) != null) {
			session.removeAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId));
		}
		session.setAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId),
				new ArrayList<CommArchivoAdjuntoBean>());
		logger.debug("Se guarda en sesion el atributo " + ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId)
				+ " con un mapa de secciones vacia");
	}

	/**
	 * Listar archivos por seccion.
	 *
	 * @param atributos the atributos
	 * @param session the session
	 * @return the comm seccion archivo adjunto bean
	 * @throws Exception the exception
	 */
	// Método que devuelve la lista de archivos por seccion
	@RequestMapping(value = "/upload/listar", method = RequestMethod.GET)
	public @ResponseBody CommSeccionArchivoAdjuntoBean listarArchivosPorSeccion(
			@RequestParam Map<String, String> atributos, HttpSession session) throws Exception {
		logger.debug("Inicia listar seccion " + atributos.get(Constants.OBJ_JS_SECCION_ID));
		String strPagId = atributos.get(Constants.OBJ_JS_PAGINA_ID);
		String strIdSeccion = atributos.get(Constants.OBJ_JS_SECCION_ID);
		String strCodParamCodSeccion = atributos.get(Constants.OBJ_JS_SECCION_COD);
		String strCodParamCantMaxSeccion = atributos.get(Constants.OBJ_JS_SECCION_COD_CANT_MAX);

		CommPaginaArchivoAdjuntoBean paginaBean = (CommPaginaArchivoAdjuntoBean) session
				.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones = paginaBean.getMapSecciones();

		//CommParametrosBean parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamCodSeccion));
		String strCodSeccion = StringUtils.isEmpty(strCodParamCodSeccion)?"1":strCodParamCodSeccion; //parametrosBean.getValor();
		//parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamCantMaxSeccion));
		Integer intCantMaxSeccion = Integer.parseInt(strCodParamCantMaxSeccion); // Integer.valueOf(parametrosBean.getValor());

		CommSeccionArchivoAdjuntoBean seccionBean = new CommSeccionArchivoAdjuntoBean(strIdSeccion,
				Integer.valueOf(strCodSeccion), intCantMaxSeccion);

		if (paginaBean.getCodigo() != null && paginaBean.getCodigo() == COD_DOCUMENTO_NUEVO) {
			logger.debug("Se trata de un documento nuevo");
			seccionBean.setLstArchivoAdjunto(new LinkedList<CommArchivoAdjuntoBean>());
		} else {
			logger.debug("Se trata de un documento existente");
			// Consultar los archivos adjuntos
			CommArchivoAdjuntoBean archivoAdjuntoBeanBusq = new CommArchivoAdjuntoBean();
			archivoAdjuntoBeanBusq.setCodigoDocumento(paginaBean.getCodigo());
			archivoAdjuntoBeanBusq.setCodigoSeccion(seccionBean.getCodigo());
			archivoAdjuntoBeanBusq.setCodigoTipoDocumento(paginaBean.getTipo());
			archivoAdjuntoBeanBusq.setEstado(Estado.ACTIVO.getValor());
			Result result = archivoAdjuntoService.listar(archivoAdjuntoBeanBusq, new Paginacion());
			List lstData = result.getData();
			LinkedList<CommArchivoAdjuntoBean> lstArchivoAdjunto = new LinkedList<CommArchivoAdjuntoBean>();
			if (lstData != null && lstData.size() > 0) {
				// Completamos los datos del archivo adjunto
				for (Object archivoAdjuntoBean : lstData) {
					((CommArchivoAdjuntoBean) archivoAdjuntoBean).setNombreSinExtension(Utils.obtenerNombreSinExtension(
							((CommArchivoAdjuntoBean) archivoAdjuntoBean).getNombreArchivoOriginal()));
					lstArchivoAdjunto.add((CommArchivoAdjuntoBean) archivoAdjuntoBean);
				}
			}
			seccionBean.setLstArchivoAdjunto(lstArchivoAdjunto);
		}
		mapSecciones.put(strIdSeccion, seccionBean);
		logger.debug("Se guarda en sesion el atributo " + ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId)
				+ " con una lista de "
				+ ((CommPaginaArchivoAdjuntoBean) session
						.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId))).getMapSecciones().size()
				+ " seccion(es)");
		logger.debug("Fin listar seccion");
		return mapSecciones.get(strIdSeccion);
	}

	/**
	 * Subir archivo.
	 *
	 * @param request the request
	 * @param response the response
	 * @param atributos the atributos
	 * @param session the session
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/upload/subir", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> subirArchivo(MultipartHttpServletRequest request,
			HttpServletResponse response, @RequestParam Map<String, String> atributos, HttpSession session)
			throws Exception {
		String strPagId = atributos.get(Constants.OBJ_JS_PAGINA_ID);
		String strIdSeccion = atributos.get(Constants.OBJ_JS_SECCION_ID);

		CommPaginaArchivoAdjuntoBean paginaBean = (CommPaginaArchivoAdjuntoBean) session
				.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones = paginaBean.getMapSecciones();
		CommSeccionArchivoAdjuntoBean seccionBean = mapSecciones.get(strIdSeccion);

		Long lngPesoMaximoArchAdj = null;
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		CommArchivoAdjuntoBean archivoAdjuntoBean = new CommArchivoAdjuntoBean();

		Integer intCantidadMaximaPermitida = null;
		
		if (seccionBean.getCantidadMaximaArchivos() == CANT_MIN_ARCH_ADJ) {
			intCantidadMaximaPermitida = paginaBean.getCantMaxArchivoApp();
		} else {
			intCantidadMaximaPermitida = seccionBean.getCantidadMaximaArchivos();
		}

		String strExpRegTipArchDoc = paginaBean.getExpRegTipoArchivoPermitido();

		if (paginaBean.getPesoMaximoArchivo() == PESO_MIN_ARCH_ADJ) {
			lngPesoMaximoArchAdj = paginaBean.getPesoMaximoArchivoApp();
		} else {
			lngPesoMaximoArchAdj = paginaBean.getPesoMaximoArchivo();
		}
		Map<String, Object> mapRespuesta = new HashMap<String, Object>();
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			if(mpf.getOriginalFilename().indexOf(".")<0){
				String parametro = messageSource.getMessage("validacion.adj.archivo.param1", null, Locale.getDefault());
				String extensiones = strExpRegTipArchDoc;
				extensiones = extensiones.replace("(", "");
				extensiones = extensiones.replace(")$", "");
				extensiones = extensiones.replace("|", ", ");
				mapRespuesta.put("seccion", seccionBean);
				mapRespuesta.put(Constants.HTTP_STATUS_PARAM, HttpStatus.BAD_REQUEST);
				mapRespuesta.put("codMensaje", "subidaArchivoTipoNoOk");
				mapRespuesta.put("valMensaje", messageSource.getMessage("validacion.adj.archivo.tipo", null, Locale.getDefault()).replace(parametro, extensiones));
			}else{
				archivoAdjuntoBean = Utils.obtenerArchivoAdjuntoBeanDeMultipart(mpf);
				archivoAdjuntoBean.setCodigoSeccion(seccionBean.getCodigo());
				try {
					archivoAdjuntoBean = subidaArchivoService.copiarArchivoEnCarpetaTemporal(mpf, archivoAdjuntoBean);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw e;
				}
			}
		}
		if(mapRespuesta.size()==0){
			mapRespuesta = agregarArchivoALista(seccionBean, archivoAdjuntoBean, intCantidadMaximaPermitida,
				strExpRegTipArchDoc, lngPesoMaximoArchAdj);
		}
		logger.debug("La lista de la seccion " + strIdSeccion + " tiene "
				+ ((CommPaginaArchivoAdjuntoBean) session
						.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId))).getMapSecciones()
								.get(strIdSeccion).getLstArchivoAdjunto().size()
				+ " archivos adjuntos");
		return new ResponseEntity<Map<String, Object>>(mapRespuesta, HttpStatus.OK);
	}

	/**
	 * Agregar archivo A lista.
	 *
	 * @param seccion the seccion
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @param intCantidadMaximaPermitida the int cantidad maxima permitida
	 * @param expRegTipArch the exp reg tip arch
	 * @param tamanhoMaximo the tamanho maximo
	 * @return the map
	 */
	private Map<String, Object> agregarArchivoALista(CommSeccionArchivoAdjuntoBean seccion,
			CommArchivoAdjuntoBean archivoAdjuntoBean, int intCantidadMaximaPermitida, String expRegTipArch,
			Long tamanhoMaximo) {
		Map<String, Object> mapRespuesta = new HashMap<String, Object>();
		Map<String, Object> mapValidacionArchivo = subidaArchivoService.archivoEsValido(archivoAdjuntoBean, seccion,
				intCantidadMaximaPermitida, expRegTipArch, tamanhoMaximo);
		boolean blnArchivoValido = (Boolean) mapValidacionArchivo.get("esValido");
		String codigoMensaje = (String) mapValidacionArchivo.get("codMensaje");
		String valorMensaje = (String) mapValidacionArchivo.get("valMensaje");
		mapRespuesta.put("seccion", seccion);
		if (blnArchivoValido) {
			agregarArchivoALista(seccion, archivoAdjuntoBean);
			mapRespuesta.put(Constants.HTTP_STATUS_PARAM, HttpStatus.OK);
		} else {
			mapRespuesta.put(Constants.HTTP_STATUS_PARAM, HttpStatus.BAD_REQUEST);
			mapRespuesta.put("codMensaje", codigoMensaje);
			mapRespuesta.put("valMensaje", valorMensaje);
		}
		return mapRespuesta;
	}

	/**
	 * Agregar archivo A lista.
	 *
	 * @param seccion the seccion
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 */
	private void agregarArchivoALista(CommSeccionArchivoAdjuntoBean seccion,
			CommArchivoAdjuntoBean archivoAdjuntoBean) {
		LinkedList<CommArchivoAdjuntoBean> lstArchivoAdjuntoBean = seccion.getLstArchivoAdjunto();
		lstArchivoAdjuntoBean.add(archivoAdjuntoBean);
	}

	/**
	 * Descargar archivo.
	 *
	 * @param session the session
	 * @param response the response
	 * @param strPagId the str pag id
	 * @param strIdSeccion the str id seccion
	 * @param value the value
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/upload/descargar/{pagId}/{seccion}/{value}", method = RequestMethod.GET)
	public void descargarArchivo(HttpSession session, HttpServletResponse response,
			@PathVariable(name = "pagId") String strPagId, @PathVariable(name = "seccion") String strIdSeccion,
			@PathVariable(name = "value") String value) throws Exception {

		CommPaginaArchivoAdjuntoBean paginaBean = (CommPaginaArchivoAdjuntoBean) session
				.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones = paginaBean.getMapSecciones();
		CommSeccionArchivoAdjuntoBean seccionBean = mapSecciones.get(strIdSeccion);

		CommArchivoAdjuntoBean archivoAdjuntoBean = seccionBean.getLstArchivoAdjunto().get(Integer.parseInt(value));
		try {
			response.setContentType(archivoAdjuntoBean.getTipoArchivo());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + archivoAdjuntoBean.getNombreArchivoOriginal() + "\"");
			InputStream is = null;
			String strRuta = (archivoAdjuntoBean.getRutaTemporalArchivo() != null
					? archivoAdjuntoBean.getRutaTemporalArchivo() : archivoAdjuntoBean.getRutaArchivo());
			is = subidaArchivoService.obtenerFileStream(strRuta);
			FileCopyUtils.copy(is, response.getOutputStream());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * Elimina archivo.
	 *
	 * @param atributos the atributos
	 * @param session the session
	 * @return the comm seccion archivo adjunto bean
	 */
	@RequestMapping(value = "/upload/eliminar", method = RequestMethod.POST)
	public @ResponseBody CommSeccionArchivoAdjuntoBean eliminaArchivo(@RequestParam Map<String, String> atributos,
			HttpSession session) {

		String strPagId = atributos.get(Constants.OBJ_JS_PAGINA_ID);
		String strIdSeccion = atributos.get(Constants.OBJ_JS_SECCION_ID);
		String strArchivoIndex = atributos.get(Constants.OBJ_JS_ARCHIVO_INDEX);

		CommPaginaArchivoAdjuntoBean paginaBean = (CommPaginaArchivoAdjuntoBean) session
				.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones = paginaBean.getMapSecciones();
		CommSeccionArchivoAdjuntoBean seccionBean = mapSecciones.get(strIdSeccion);
		CommArchivoAdjuntoBean archivoAdjuntoBean = seccionBean.getLstArchivoAdjunto()
				.get(Integer.parseInt(strArchivoIndex));

		List<CommArchivoAdjuntoBean> lstArchivosEliminar = (List) session
				.getAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId));
		// Solo agregamos a la lista a eliminar el archivo si este proviene
		// de BD
		if ((archivoAdjuntoBean != null) && (archivoAdjuntoBean.getNumeroArchivo() != null)
				&& (archivoAdjuntoBean.getNumeroArchivo() != COD_ARCHIVO_NUEVO)) {
			logger.debug("el archivo para eliminar tiene codigo " + archivoAdjuntoBean.getNumeroArchivo());
			lstArchivosEliminar.add(archivoAdjuntoBean);
			logger.debug("Esta lista ahora tiene " + lstArchivosEliminar.size() + " archivos");
		}
		seccionBean.getLstArchivoAdjunto().remove(archivoAdjuntoBean);
		logger.debug("La lista de archivos para eliminar tiene "
				+ ((List) session.getAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId))).size()
				+ " archivos");
		return seccionBean;
	}

	/**
	 * Guardar.
	 *
	 * @param atributos the atributos
	 * @param session the session
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/upload/guardar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> guardar(@RequestParam Map<String, String> atributos,
			HttpSession session) throws Exception {
		String strPagId = atributos.get(Constants.OBJ_JS_PAGINA_ID);
		String strCodDoc = atributos.get(Constants.OBJ_JS_DOCUMENTO_COD);

		CommPaginaArchivoAdjuntoBean paginaBean = (CommPaginaArchivoAdjuntoBean) session
				.getAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
		paginaBean.setCodigo(Integer.valueOf(strCodDoc));
		Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones = paginaBean.getMapSecciones();

		Map<String, String> mapParametrosDocumento = new HashMap<String, String>();
		mapParametrosDocumento.put("anhoDocumento", String.valueOf(paginaBean.getAnho()));
		mapParametrosDocumento.put("tipoDocumento", String.valueOf(paginaBean.getTipo()));
		mapParametrosDocumento.put("codigoDocumento", String.valueOf(paginaBean.getCodigo()));

		//String strCodParamRutaCompartida = environment.getProperty(Constants.PROP_APP_RUTA_CARPETA_COMPARTIDA);
		//String strCodParamRaizArchivosAdjuntos = environment.getProperty(Constants.PROP_APP_ARCHADJ_RAIZ);
		//CommParametrosBean parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamRutaCompartida));
		String strRutaCompartida = environment.getProperty(Constants.PROP_APP_RUTA_CARPETA_COMPARTIDA); //parametrosBean.getValor();
		//parametrosBean = parametrosService.consultar(Integer.valueOf(strCodParamRaizArchivosAdjuntos));
		String strRaizArchivosAdjuntos = environment.getProperty(Constants.PROP_APP_ARCHADJ_RAIZ); //parametrosBean.getValor();

		Map<String, String> mapParametrosRutaCompartida = new HashMap<String, String>();
		mapParametrosRutaCompartida.put("rutaCompartida", strRutaCompartida);
		mapParametrosRutaCompartida.put("raizArchivosAdjuntos", strRaizArchivosAdjuntos);

		// Lista que va guardar los archivos adjuntos que no llegaron a copiarse
		// en la carpeta compartida ni tampoco se guardaron en tabla de adjuntos
		LinkedList<CommSeccionArchivoAdjuntoBean> lstArchivosNoGuardados = new LinkedList<CommSeccionArchivoAdjuntoBean>();
		boolean blnHayArchivosNoCopiados = false;
		// Lista que va guardar los archivos adjuntos que si llegaron a copiarse
		// en la carpeta compartida y en la tabla de adjuntos
		LinkedList<CommSeccionArchivoAdjuntoBean> lstArchivosSiGuardados = new LinkedList<CommSeccionArchivoAdjuntoBean>();

		// Solo se va guardar en la carpeta compartida y en BD los archivos
		// nuevos que se adjuntaron, no los que ya existen
		for (CommSeccionArchivoAdjuntoBean seccionBean : mapSecciones.values()) {
			CommSeccionArchivoAdjuntoBean lstArchivosNoGuardadosPorSeccion = new CommSeccionArchivoAdjuntoBean(
					seccionBean.getId(), seccionBean.getCodigo(), seccionBean.getCantidadMaximaArchivos());
			CommSeccionArchivoAdjuntoBean lstArchivosSiGuardadosPorSeccion = new CommSeccionArchivoAdjuntoBean(
					seccionBean.getId(), seccionBean.getCodigo(), seccionBean.getCantidadMaximaArchivos());

			LinkedList<CommArchivoAdjuntoBean> lk1 = new LinkedList<CommArchivoAdjuntoBean>();
			LinkedList<CommArchivoAdjuntoBean> lk2 = new LinkedList<CommArchivoAdjuntoBean>();

			for (CommArchivoAdjuntoBean archivoAdjuntoBean : seccionBean.getLstArchivoAdjunto()) {
				boolean blnArchivoCopiado = false;
				if (archivoAdjuntoBean.getNumeroArchivo() == null) {
					blnArchivoCopiado = subidaArchivoService.copiarArchivoEnCarpetaCompartida(archivoAdjuntoBean,
							mapParametrosDocumento, mapParametrosRutaCompartida);
					if (blnArchivoCopiado) {
						// Si se copiaron los archivos adjuntos, vamos a
						// guardarlos en BD
						archivoAdjuntoBean.setUsuarioCreacion(CoreUtils.getUserLoged());
						archivoAdjuntoBean.setUsuarioModificacion(CoreUtils.getUserLoged());
						archivoAdjuntoBean.setEstado(Estado.ACTIVO.getValor());
						archivoAdjuntoBean.setCodigoTipoDocumento(paginaBean.getTipo());
						archivoAdjuntoBean.setCodigoDocumento(paginaBean.getCodigo());
						try {
							archivoAdjuntoService.guardar(archivoAdjuntoBean);
							lk1.add(archivoAdjuntoBean);
						} catch (Exception e) {
							// Si ocurrió una excepcion en BD se elimina el
							// archivo fisicamente de la carpeta compartida
							subidaArchivoService.eliminarArchivoDeCarpetaCompartida(archivoAdjuntoBean);
							blnHayArchivosNoCopiados = true;
							lk2.add(archivoAdjuntoBean);
						}
					} else {
						blnHayArchivosNoCopiados = true;
						lk2.add(archivoAdjuntoBean);
					}
				}
				lstArchivosNoGuardadosPorSeccion.setLstArchivoAdjunto(lk2);
			}
			// La lista de archivos que si se guardaron van a venir de BD
			CommArchivoAdjuntoBean archivoAdjuntoBeanBusq = new CommArchivoAdjuntoBean();
			archivoAdjuntoBeanBusq.setCodigoDocumento(paginaBean.getCodigo());
			archivoAdjuntoBeanBusq.setCodigoSeccion(seccionBean.getCodigo());
			archivoAdjuntoBeanBusq.setCodigoTipoDocumento(paginaBean.getTipo());
			archivoAdjuntoBeanBusq.setEstado(Estado.ACTIVO.getValor());
			Result result = archivoAdjuntoService.listar(archivoAdjuntoBeanBusq, new Paginacion());
			List lstData = result.getData();
			LinkedList<CommArchivoAdjuntoBean> lstArchivoAdjunto = new LinkedList<CommArchivoAdjuntoBean>();
			if (lstData != null && lstData.size() > 0) {
				// Completamos los datos del archivo adjunto
				for (Object archivoAdjuntoBean : lstData) {
					((CommArchivoAdjuntoBean) archivoAdjuntoBean).setNombreSinExtension(Utils.obtenerNombreSinExtension(
							((CommArchivoAdjuntoBean) archivoAdjuntoBean).getNombreArchivoOriginal()));
					lstArchivoAdjunto.add((CommArchivoAdjuntoBean) archivoAdjuntoBean);
				}
			}
			lstArchivosSiGuardadosPorSeccion.setLstArchivoAdjunto(lstArchivoAdjunto);

			lstArchivosNoGuardados.add(lstArchivosNoGuardadosPorSeccion);
			lstArchivosSiGuardados.add(lstArchivosSiGuardadosPorSeccion);
		}
		// Se procede a eliminar en BD (lógico) y en la carpeta compartida
		// (físico) los archivos que vinieron de BD y que se eliminaron de la
		// vista
		if (session.getAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId)) != null) {
			logger.debug("hay archivos para eliminar");
			List<CommArchivoAdjuntoBean> lstArchivosEliminar = (List) session
					.getAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId));
			logger.debug("son " + lstArchivosEliminar.size() + " archivos");
			for (CommArchivoAdjuntoBean archivoAdjuntoBean : lstArchivosEliminar) {
				try {
					logger.debug("se va eliminar el archivo con codigo " + archivoAdjuntoBean.getNumeroArchivo());
					archivoAdjuntoBean.setEstado(Estado.ELIMINADO.getValor());
					archivoAdjuntoBean.setUsuarioModificacion(CoreUtils.getUserLoged());
					archivoAdjuntoService.actualizar(archivoAdjuntoBean);
					subidaArchivoService.eliminarArchivoDeCarpetaCompartida(archivoAdjuntoBean);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		// Procedemos a eliminar los archivos que se hayan subido a la carpeta
		// temporal
		for (CommSeccionArchivoAdjuntoBean seccion : mapSecciones.values()) {
			for (CommArchivoAdjuntoBean archivoAdjuntoBean : seccion.getLstArchivoAdjunto()) {
				if (archivoAdjuntoBean.getRutaTemporalArchivo() != null) {
					try {
						logger.debug("Se va eliminar el archivo " + archivoAdjuntoBean.getNombreTemporal() + " de la carpeta temporal");
						subidaArchivoService.eliminarArchivoDeCarpetaTemporal(archivoAdjuntoBean);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		}
		Map<String, Object> mapRespuesta = new HashMap<String, Object>();
		mapRespuesta.put("status", HttpStatus.OK);
		if (blnHayArchivosNoCopiados) {
			// Al haber archivos que no se copiaron, se permanece en la pagina
			// sin recargar
			// en sesion permanecen lista de secciones y lista de archivos a
			// eliminar desactualizada
			// Actualizar la lista de sesion solo con los archivos que se
			// llegaron a guardar
			session.removeAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId));
			session.setAttribute(ATTR_SESION_PAGINA_ARCHIVOS_ADJUNTOS.concat(strPagId), lstArchivosSiGuardados);
			// Reemplazar la lista de archivos a eliminar de sesion por una
			// lista vacia
			session.removeAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId));
			session.setAttribute(ATTR_SESION_LISTA_ARCHIVOS_ELIMINAR.concat(strPagId),
					new ArrayList<CommArchivoAdjuntoBean>());
			mapRespuesta.put("status", HttpStatus.EXPECTATION_FAILED);
			mapRespuesta.put("lstArchivosNoGuardados", lstArchivosNoGuardados);
			mapRespuesta.put("lstArchivosSiGuardados", lstArchivosSiGuardados);
		}
		return new ResponseEntity<>(mapRespuesta, HttpStatus.OK);
	}

}
