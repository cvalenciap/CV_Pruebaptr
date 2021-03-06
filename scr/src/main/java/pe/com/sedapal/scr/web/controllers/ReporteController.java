package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;

import net.sf.jasperreports.engine.util.FileResolver;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.gmd.util.properties.PropiedadesUtil;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.TreeReporteBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.services.IAlertasNotificacionService;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;
import pe.com.sedapal.scr.core.services.IParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.IPlantaTratamientoService;
import pe.com.sedapal.scr.core.services.IPtarxSectorService;
import pe.com.sedapal.scr.core.services.IPuntoMuestraPtarSectorService;
import pe.com.sedapal.scr.core.services.IRegistroAceiteService;
import pe.com.sedapal.scr.core.services.IRegistroDBOService;
import pe.com.sedapal.scr.core.services.IRegistroDirectoService;
import pe.com.sedapal.scr.core.services.IRegistroHidrobiologicoService;
import pe.com.sedapal.scr.core.services.IRegistroLabReporteService;
import pe.com.sedapal.scr.core.services.IRegistroLaboratorioService;
import pe.com.sedapal.scr.core.services.IRegistroMicrobiologicoService;
import pe.com.sedapal.scr.core.services.IRegistroParasitologicoService;
import pe.com.sedapal.scr.core.services.IRegistroSolidoService;
import pe.com.sedapal.scr.core.services.ISubParametroPtarSectorService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.BPdf;
import pe.com.sedapal.scr.web.util.ExportWebUtil;
import pe.com.sedapal.scr.web.util.JsonUtil;

/**
 * @author GMD
 * Controlador de Reportes y dashboard PTAR
 */
@Controller
@RequestMapping(value = "/reporteSptar")
public class ReporteController {

	private static final Logger LOGGER = Logger.getLogger(MantenimientoController.class);
	
	private BRespuestaBean respuestaBean = new BRespuestaBean();
	
	@Autowired
	private GeneralController generalController;
	
	@Autowired
	private IPtarxSectorService ptarxSectorService;
	
	@Autowired
	private IPuntoMuestraPtarSectorService puntoMuestraPtarSectorService;
	
	@Autowired
	private ISubParametroPtarSectorService subParametroPtarSectorService;
	
	@Autowired
	private IParametroPtarSectorService parametroPtarSectorService;
	
	@Autowired
	private IRegistroLaboratorioService registroLaboratorioService;
	
	@Autowired
	private IRegistroLabReporteService registroLabReporteService;
	
	@Autowired
	IDetalleGeneralService detalleGeneralService;
	
	@Autowired
	private IRegistroSolidoService registroSolidoService;
	
	@Autowired
	private IRegistroDBOService registroDBOService;
	
	@Autowired
	private IRegistroParasitologicoService registroParasitologicoService;
	
	@Autowired
	private IRegistroHidrobiologicoService registroHidrobiologicoService;
	
	@Autowired
	private IRegistroMicrobiologicoService registroMicrobiologicoService;
	
	@Autowired
	private IRegistroDirectoService registroDirectoService;
	
	@Autowired
	private IRegistroAceiteService registroAceiteService;
	
	@Autowired
	private IPlantaTratamientoService plantaTratamientoService;
	
	@Autowired
	IAlertasNotificacionService alertasNotificacionService;
	
	public ReporteController() {
		
	}
	
	/**
	 * M??todo que carga la vista de los reportes de gesti??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo string con la ruta de la vista de reportes de gesti??n
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaReporteGestionPTAR", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaReporteGestionPTAR(HttpServletRequest request, Model model) throws GmdException {
		try {
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			String fechaActual = ParametrosUtil.getFechaActual();
			model.addAttribute("ltaPtarxSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("ltaEstado", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_ESTADO));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("filtro", request.getParameter("filtro"));
			if(analistaBean.getIdFlagTipo() != null) {
				model.addAttribute("flagAnalistaValidador", analistaBean.getIdFlagTipo());
			}else {
				model.addAttribute("flagAnalistaValidador","0");
			}
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/reporte/bandejaReporte";
	}
	
	
	/**
	 * M??todo que genera datos y reporte de gesti??n de laboratorio
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws GmdException
	 */
	@RequestMapping(value = "/reporteRegistroLaboratorioPTAR", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void reporteRegistroLaboratorioPTAR(HttpServletRequest request, HttpServletResponse response, Model model) throws GmdException {
		try {
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String arrayParametros = request.getParameter("arrayParametros");
			String listaTree = request.getParameter("listaTree");
			String rutaJasper = this.getClass().getResource("/reportes/").getPath()+PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "ruta.reporteGestionLab");
			String nombreArchivo = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "nombre.reporteGestionLab");
//			inicio cvalenciap
//			Type listType = new TypeToken<List<String>>() {}.getType();
//			List<String> parametros = JsonUtil.convertirCadenaJsonALista(arrayParametros, listType);
			List<RegistroLabReporteBean> listaObtenida = new ArrayList<RegistroLabReporteBean>();
			List<String> parametros = new ArrayList<String>();
			parametros.add(ConstanteServices.TIPO_DIRECTO);
			parametros.add(ConstanteServices.TIPO_FISICO_QUIMICO);
			parametros.add(ConstanteServices.TIPO_MICRO_HIDRO);
			parametros.add(ConstanteServices.TIPO_PARA);
//			fin cvalenciap
			listaObtenida = registroLabReporteService.obtenerListaReporte(null, Integer.parseInt(idRegistroLaboratorio), null, null, parametros);
			RegistroLaboratorioBean RegistroLaboratorio = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			List<PuntoMuestraPtarSectorBean> listaPtoMuestra = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraSecEnlace(RegistroLaboratorio.getIdPtarxSector());
			if(!CollectionUtils.isEmpty(listaObtenida)) {	
				List<BPdf> listaPDFBean = new ArrayList<BPdf>();
				final Map<String,Object> parametrosGeneral = new HashMap<String,Object>();
				List<RegistroLabReporteBean> listaDirecto = new ArrayList<RegistroLabReporteBean>();
				List<RegistroLabReporteBean> listaFisicoQuimico = new ArrayList<RegistroLabReporteBean>();
				List<RegistroLabReporteBean> listaMicroHidro = new ArrayList<RegistroLabReporteBean>();
				List<RegistroLabReporteBean> listaPara = new ArrayList<RegistroLabReporteBean>();
				DecimalFormat format = new DecimalFormat("0.#");
				for(RegistroLabReporteBean RegistroReporte : listaObtenida) {
					switch (RegistroReporte.descripcionTipoRegistro) {
						case ConstanteServices.TIPO_DIRECTO:
							RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
							RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
							listaDirecto.add(RegistroReporte);
							break;
						case ConstanteServices.TIPO_FISICO_QUIMICO:
							RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,0);
							RegistroReporte.setDescripcionNumValor(format.format(RegistroReporte.numValor));
							if(!StringUtils.isEmpty(RegistroReporte.getTipoSemilla()) && !RegistroReporte.getTipoSemilla().equals(" ")) {
								for(PuntoMuestraPtarSectorBean puntoMuestra : listaPtoMuestra) {
									if(String.valueOf(RegistroReporte.getIdPtoMuestra()).equals(puntoMuestra.getPtoEnlace())) {
										RegistroReporte.setDescripcionPtoMuestra(puntoMuestra.getDescripcionPunto());
										RegistroReporte.setSecuencia(puntoMuestra.getSecuencia());
										break;
									}
								}
							}
							listaFisicoQuimico.add(RegistroReporte);
							break;
						case ConstanteServices.TIPO_MICRO_HIDRO:
							if(RegistroReporte.getDescripcionNumValor().equals(ConstanteServices.CAMPO_SIN_VALOR)) {
								RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,0);
								RegistroReporte.setDescripcionNumValor(format.format(RegistroReporte.numValor).trim());
							}else {
								RegistroReporte.setDescripcionNumValor(RegistroReporte.getDescripcionNumValor().trim());
							}
							listaMicroHidro.add(RegistroReporte);
							break;
						case ConstanteServices.TIPO_PARA:
							RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,0);
							RegistroReporte.setDescripcionNumValor(format.format(RegistroReporte.numValor));
							listaPara.add(RegistroReporte);
							break;
					}
				}
//				inicio cvalenciap
				Type listTypeTree = new TypeToken<List<TreeReporteBean>>() {}.getType();
				List<TreeReporteBean> listaTreeSelect = JsonUtil.convertirCadenaJsonALista(listaTree, listTypeTree);
				listaDirecto = filtrarValoresLista(listaDirecto, listaTreeSelect);
				listaFisicoQuimico = filtrarValoresLista(listaFisicoQuimico, listaTreeSelect);
				listaMicroHidro = filtrarValoresLista(listaMicroHidro, listaTreeSelect);
				listaPara = filtrarValoresLista(listaPara, listaTreeSelect);
//				fin cvalenciap
				
				List<Map<String, String>> listaDirectoMap = JsonUtil.convertirObjetoaMapString(listaDirecto);
				List<Map<String, String>> listaFisicoQuimicoMap = JsonUtil.convertirObjetoaMapString(listaFisicoQuimico);
				List<Map<String, String>> listaMicroHidroMap = JsonUtil.convertirObjetoaMapString(listaMicroHidro);
				List<Map<String, String>> listaParaMap = JsonUtil.convertirObjetoaMapString(listaPara);
				
				parametrosGeneral.put("ltaDirectoMap", listaDirectoMap);
				parametrosGeneral.put("ltaFisicoQuimicoMap", listaFisicoQuimicoMap);
				parametrosGeneral.put("ltaMicroHidroMap", listaMicroHidroMap);
				parametrosGeneral.put("ltaParaMap", listaParaMap);
				parametrosGeneral.put("descripcionPtar", RegistroLaboratorio.getDescripcionPtar());
				parametrosGeneral.put("descripcionSector", RegistroLaboratorio.getDescripcionSector());
//				parametrosGeneral.put("fechaRegistro", RegistroLaboratorio.getFechaRegistroString());
				parametrosGeneral.put("fechaRegistro", RegistroLaboratorio.getFechaMonitoreoString());
				parametrosGeneral.put("estado", RegistroLaboratorio.getDescripcionEstadoAprob());
				parametrosGeneral.put("idRegistro", idRegistroLaboratorio);
				String logo = this.getClass().getResource("/reportes/reportesImg/").getPath()+"logo"+String.valueOf(RegistroLaboratorio.getIdPtarxSector())+".jpg";
				File reportImage = new File(logo);
				if(!reportImage.exists()) {
					parametrosGeneral.put("logo", "default");
				}else {
					parametrosGeneral.put("logo", logo);
				}
				BPdf bPdfBean = new BPdf();
				bPdfBean.setParametro(parametrosGeneral);
				bPdfBean.setRutaJasper(rutaJasper);
				bPdfBean.setListaDataSource(null);
				listaPDFBean.add(bPdfBean);
				ExportWebUtil.exportToExcel(response, listaPDFBean, nombreArchivo);
			}
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
	}
	
	
	/**
	 * M??todo que encuentra la URI de un archivo
	 * 
	 * @param fileName nombre del archivo
	 * @return URI de archivo
	 */
	public File resolveFile(String fileName) {
		URI uri;
		try {
			uri = new URI(this.getClass().getResource(fileName).getPath());
			return new File(uri.getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * M??todo que realiza la b??squeda de los datos del reporte de gesti??n de laboratorio
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo regsitro de laboratorio reporte
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarRegistroReporte" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String buscarRegistroReporte(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String idPtarxSector = request.getParameter("idPtarxSector");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
//			inicio cvalenciap
//			String arrayParametros = request.getParameter("arrayParametros");
//			Type listType = new TypeToken<List<String>>() {}.getType();
//			List<String> parametros = JsonUtil.convertirCadenaJsonALista(arrayParametros, listType);
			List<String> parametros = new ArrayList<String>();
			parametros.add(ConstanteServices.TIPO_DIRECTO);
			parametros.add(ConstanteServices.TIPO_FISICO_QUIMICO);
			parametros.add(ConstanteServices.TIPO_MICRO_HIDRO);
			parametros.add(ConstanteServices.TIPO_PARA);
//			fin cvalenciap
			List<RegistroLabReporteBean> listaObtenida = new ArrayList<RegistroLabReporteBean>();
			listaObtenida = registroLabReporteService.obtenerListaReporte(null, Integer.parseInt(idRegistroLaboratorio), null, null, parametros);
//			listaObtenida = registroLabReporteService.obtenerListaReporte(null, Integer.parseInt(idRegistroLaboratorio), null, null);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se encontraron Registros.");
			mapResultado.put("ListaResultado", listaObtenida);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);		
	}
	
	
	/**
	 * M??todo que carga la vista del dashboard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo string con la ruta de la vista dashboard
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaDashBoard", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaDashBoard(HttpServletRequest request, Model model) throws GmdException {
		try {			
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			List<PlantaTratamientoBean> ltaPlantaTratamiento = plantaTratamientoService.obtenerPlantaTratamiento();
			String fechaActual = ParametrosUtil.getFechaActual();
			model.addAttribute("objRegistroLaboratorio", new RegistroLaboratorioBean());
			model.addAttribute("ltaPlantaTratamiento",ltaPlantaTratamiento);
			model.addAttribute("idParametroInicial",null);
			model.addAttribute("nombrePlantaInicial","");
			model.addAttribute("fechaActual",fechaActual);
			if(analistaBean.getIdFlagTipo() != null) {
				model.addAttribute("flagAnalistaValidador",analistaBean.getIdFlagTipo());
			}else {
				model.addAttribute("flagAnalistaValidador","0");
			}
			model.addAttribute("idAnalistaSelect",analistaBean.getIdAnalista());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("filtro", request.getParameter("filtro"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/reporte/dashBoard";
	}
	
	/**
	 * M??todo que carga la vista del reporte hist??rico
	 * 
	 * @param request
	 * @param model
	 * @return objeto tipo string con la ruta de la vista del reporte hist??rico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaReporteHistorico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaReporteHistorico(HttpServletRequest request, Model model) throws GmdException {
		try {
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			String fechaActual = ParametrosUtil.getFechaActual();
			model.addAttribute("ltaPtarxSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("ltaEstado", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_ESTADO));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("filtro", request.getParameter("filtro"));
			if(analistaBean.getIdFlagTipo() != null) {
				model.addAttribute("flagAnalistaValidador", analistaBean.getIdFlagTipo());
			}else {
				model.addAttribute("flagAnalistaValidador","0");
			}
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/reporte/bandejaReporteHistorico";
	}
	
	/**
	 * M??todo que realiza la b??squeda de los registros del reporte hist??rico
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos de tipo registro de laboratorio reporte
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarRegistroReporteHistorico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String buscarRegistroReporteHistorico(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String idPtarxSector = request.getParameter("idPtarxSector");
			List<RegistroLabReporteBean> listaHistorico = registroLabReporteService.obtenerListaReporteHistorico(Integer.parseInt(idPtarxSector), fechaInicio, fechaFin);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se encontraron Registros.");
			mapResultado.put("ListaResultado", listaHistorico);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);		
	}
	
	/**
	 * M??todo que genera los datos y reporte hist??rico
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws GmdException
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/reporteRegistroLaboratorioHistorico", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void reporteRegistroLaboratorioHistorico(HttpServletRequest request, HttpServletResponse response, Model model) throws GmdException {
		try {
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String idPtarxSector = request.getParameter("idPtarxSector");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			String listaTree = request.getParameter("listaTree");
			String rutaJasper = this.getClass().getResource("/reportes/").getPath()+PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "ruta.reporteHistoricoLab");
			String nombreArchivo = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "nombre.reporteHistoricoLab");
			List<RegistroLabReporteBean> listaHistorico = registroLabReporteService.obtenerListaReporteHistorico(Integer.parseInt(idPtarxSector), fechaInicio, fechaFin);
			List<PuntoMuestraPtarSectorBean> listaPtoMuestra = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraSecEnlace(Integer.parseInt(idPtarxSector));
//			DecimalFormat format = new DecimalFormat("0.#");
			if(!CollectionUtils.isEmpty(listaHistorico)) {
				for(RegistroLabReporteBean RegistroReporte : listaHistorico) {
					switch (RegistroReporte.descripcionTipoRegistro) {
							case ConstanteServices.TIPO_FISICO_QUIMICO:
								RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
								RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
								if(!StringUtils.isEmpty(RegistroReporte.getTipoSemilla()) && !RegistroReporte.getTipoSemilla().equals(" ")) {
									for(PuntoMuestraPtarSectorBean puntoMuestra : listaPtoMuestra) {
										if(String.valueOf(RegistroReporte.getIdPtoMuestra()).equals(puntoMuestra.getPtoEnlace())) {
											RegistroReporte.setDescripcionPtoMuestra(puntoMuestra.getDescripcionPunto());
											RegistroReporte.setSecuencia(puntoMuestra.getSecuencia());
											break;
										}
									}
								}
							case ConstanteServices.TIPO_MICRO_HIDRO:
								if(RegistroReporte.getDescripcionNumValor().equals(ConstanteServices.CAMPO_SIN_VALOR)) {
									RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
									RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
									if(RegistroReporte.getIdSubParametro() == ConstantesUtil.ID_COLIFORME_TERMOTOLERANTES || RegistroReporte.getIdSubParametro() == ConstantesUtil.ID_COLIFORME_TOTALES) {
										Formatter fmtScientific = new Formatter();
										RegistroReporte.setDescripcionNumValor((String.valueOf(fmtScientific.format("%16.2e", RegistroReporte.numValor))).trim());
									}
								}else {
									RegistroReporte.setDescripcionNumValor(RegistroReporte.getDescripcionNumValor());
								}
								break;
							default : 
								RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
								RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
								break;
						}
					
				}
//				inicio cvalenciap
				Type listTypeTree = new TypeToken<List<TreeReporteBean>>() {}.getType();
				List<TreeReporteBean> listaTreeSelect = JsonUtil.convertirCadenaJsonALista(listaTree, listTypeTree);
				listaHistorico = filtrarValoresLista(listaHistorico, listaTreeSelect);
//				fin cvalenciap
				
//				inicio lista inicial ordenamiento
				List<RegistroLabReporteBean> listaFinalHistorico = new ArrayList<RegistroLabReporteBean>();
				List<RegistroLabReporteBean> listaInicialOrdenamiento = registroLabReporteService.obtenerListaTree(Integer.parseInt(idPtarxSector));
				listaInicialOrdenamiento = filtrarValoresLista(listaInicialOrdenamiento, listaTreeSelect);
				
				for(RegistroLabReporteBean registroInicial : listaInicialOrdenamiento) {
					registroInicial.setDescripcionNumValor(null);
					registroInicial.setNumValor(null);
				}
				
				listaFinalHistorico.addAll(listaInicialOrdenamiento);
				listaFinalHistorico.addAll(listaHistorico);

				List<BPdf> listaPDFBean = new ArrayList<BPdf>();
				final Map<String,Object> parametrosGeneral = new HashMap<String,Object>();
//				List<Map<String, String>> listaHistoricoMap = JsonUtil.convertirObjetoaMapString(listaHistorico);
//				parametrosGeneral.put("ltaHistoricoMap", listaHistoricoMap);
				List<Map<String, String>> listaHistoricoMap = JsonUtil.convertirObjetoaMapString(listaFinalHistorico);
//				fin lista inicial ordenamiento
				parametrosGeneral.put("ltaHistoricoMap", listaHistoricoMap);
				parametrosGeneral.put("descripcionPtar", descripcionPtar);
				parametrosGeneral.put("descripcionSector", descripcionSector);
				parametrosGeneral.put("fechaInicio", fechaInicio);
				parametrosGeneral.put("fechaFin", fechaFin);
				BPdf bPdfBean = new BPdf();
				bPdfBean.setParametro(parametrosGeneral);
				bPdfBean.setRutaJasper(rutaJasper);
				bPdfBean.setListaDataSource(null);
				listaPDFBean.add(bPdfBean);
				ExportWebUtil.exportToExcel(response, listaPDFBean, nombreArchivo);
			}
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
	}
		
	/**
	 * M??todo que carga la vista de reporte acumulado
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo string que contiene la ruta de la vista de reporte acumulado
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaReporteAcumulado", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaReporteAcumulado(HttpServletRequest request, Model model) throws GmdException {
		try {
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			String fechaActual = ParametrosUtil.getFechaActual();
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("filtro", request.getParameter("filtro"));
			if(analistaBean.getIdFlagTipo() != null) {
				model.addAttribute("flagAnalistaValidador", analistaBean.getIdFlagTipo());
			}else {
				model.addAttribute("flagAnalistaValidador","0");
			}
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/reporte/bandejaReporteAcumulado";
	}
		
	/**
	 * M??todo que realiza la b??squeda de registros de reporte acumulado
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos de tipo registro laboratorio reporte
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarRegistroReporteAcumulado" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String buscarRegistroReporteAcumulado(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			List<RegistroLabReporteBean> listaAcumulado = registroLabReporteService.obtenerListaReporteAcumulado(fechaInicio, fechaFin);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se encontraron Registros.");
			mapResultado.put("ListaResultado", listaAcumulado);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);		
	}
	
	/**
	 * M??todo que genera datos y reporte registros de laboratorio acumulado
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws GmdException
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/reporteRegistroLaboratorioAcumulado", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void reporteRegistroLaboratorioAcumulado(HttpServletRequest request, HttpServletResponse response, Model model) throws GmdException {
		try {
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String rutaJasper = this.getClass().getResource("/reportes/").getPath()+PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "ruta.reporteAcumuladoLab");
			String nombreArchivo = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "nombre.reporteAcumuladoLab");
			List<RegistroLabReporteBean> listaAcumulado = registroLabReporteService.obtenerListaReporteAcumulado(fechaInicio, fechaFin);
			
			if(!CollectionUtils.isEmpty(listaAcumulado)) {
				for(RegistroLabReporteBean RegistroReporte : listaAcumulado) {
					switch (RegistroReporte.descripcionTipoRegistro) {
					case ConstanteServices.TIPO_FISICO_QUIMICO:
						RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
						RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
						if(!StringUtils.isEmpty(RegistroReporte.getTipoSemilla()) && !RegistroReporte.getTipoSemilla().equals(" ")) {
							List<PuntoMuestraPtarSectorBean> listaPtoMuestra = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraSecEnlace(RegistroReporte.getIdPtarxSector());
							for(PuntoMuestraPtarSectorBean puntoMuestra : listaPtoMuestra) {
								if(String.valueOf(RegistroReporte.getIdPtoMuestra()).equals(puntoMuestra.getPtoEnlace())) {
									RegistroReporte.setDescripcionPtoMuestra(puntoMuestra.getDescripcionPunto());
									RegistroReporte.setSecuencia(puntoMuestra.getSecuencia());
									break;
								}
							}
						}
						break;
					case ConstanteServices.TIPO_MICRO_HIDRO:
						if(RegistroReporte.getDescripcionNumValor().equals(ConstanteServices.CAMPO_SIN_VALOR)) {
							RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
							RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
							if(RegistroReporte.getIdSubParametro() == ConstantesUtil.ID_COLIFORME_TERMOTOLERANTES || RegistroReporte.getIdSubParametro() == ConstantesUtil.ID_COLIFORME_TOTALES) {
								Formatter fmtScientific = new Formatter();
								RegistroReporte.setDescripcionNumValor((String.valueOf(fmtScientific.format("%16.2e", RegistroReporte.numValor))).trim());
							}
						}else {
							RegistroReporte.setDescripcionNumValor(RegistroReporte.getDescripcionNumValor());
						}
						break;
					default:
						RegistroReporte.numValor = ParametrosUtil.formatearDecimales(RegistroReporte.numValor,2);
						RegistroReporte.setDescripcionNumValor(String.valueOf(RegistroReporte.numValor));
						break;
					}
				}
				
				List<BPdf> listaPDFBean = new ArrayList<BPdf>();
				final Map<String,Object> parametrosGeneral = new HashMap<String,Object>();
				List<Map<String, String>> listaAcumuladoMap = JsonUtil.convertirObjetoaMapString(listaAcumulado);
				parametrosGeneral.put("ltaAcumuladoMap", listaAcumuladoMap);
				parametrosGeneral.put("fechaInicio", fechaInicio);
				parametrosGeneral.put("fechaFin", fechaFin);
				BPdf bPdfBean = new BPdf();
				bPdfBean.setParametro(parametrosGeneral);
				bPdfBean.setRutaJasper(rutaJasper);
				bPdfBean.setListaDataSource(null);
				listaPDFBean.add(bPdfBean);
				ExportWebUtil.exportToExcel(response, listaPDFBean, nombreArchivo);
			}
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
	}
	
	/**
	 * M??todo que carga la vista del resultado dashboard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto de tipo string con la ruta de la vista del resultado dashboard
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaDashboard", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaDashboard(HttpServletRequest request, Model model) throws GmdException {
		try {
			String listaPuntos = request.getParameter("listaPuntos");
			String listaPtar  = request.getParameter("listaPtar");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			Integer[] arrayPuntos = JsonUtil.convertirCadenaJsonAObjeto(listaPuntos, Integer[].class);
			List<RegistroLabReporteBean> listaDashboardEP = new ArrayList<RegistroLabReporteBean>();
			List<RegistroLabReporteBean> listaDashboardSP = new ArrayList<RegistroLabReporteBean>();
			List<RegistroLabReporteBean> listaDashboard = registroLabReporteService.obtenerListaDashboard(fechaInicio, fechaFin, arrayPuntos);
			for (RegistroLabReporteBean registro : listaDashboard) {
				if (!StringUtils.isEmpty(registro.getTipoSemilla()) && !registro.getTipoSemilla().equals(" ")) {
					List<PuntoMuestraPtarSectorBean> listaPtoMuestra = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraSecEnlace(registro.getIdPtarxSector());
					for (PuntoMuestraPtarSectorBean puntoMuestra : listaPtoMuestra) {
						if (String.valueOf(registro.getIdPtoMuestra()).equals(puntoMuestra.getPtoEnlace())) {
							registro.setDescripcionPtoMuestra(puntoMuestra.getDescripcionPunto());
							registro.setIdPtoMuestra(puntoMuestra.getIdPuntoMuestra());
							break;
						}
					}
				}
			}
			for(RegistroLabReporteBean RegistroDashboard : listaDashboard) {
				RegistroDashboard.numValor = ParametrosUtil.formatearDecimales(RegistroDashboard.numValor,2);
				if(RegistroDashboard.getIdEPSP().equals(ConstantesUtil.TIPO_EP)) {
					listaDashboardEP.add(RegistroDashboard);
				}else if(RegistroDashboard.getIdEPSP().equals(ConstantesUtil.TIPO_SP)) {
					listaDashboardSP.add(RegistroDashboard);
				}
			}
			model.addAttribute("listaPuntos", listaPuntos);
			model.addAttribute("listaPtar", listaPtar);
			model.addAttribute("listaDashboardEP", listaDashboardEP);
			model.addAttribute("listaDashboardSP", listaDashboardSP);
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/reporte/resultadoDashboard";
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros para el dashboard lineal
	 * 
	 * @param idRutaProg
	 * @param request
	 * @param model
	 * @return Listas de objetos con el resultado de b??squeda dashboard
	 * @throws GmdException
	 */
	@RequestMapping(value = "/obtenerListaLineDashboard" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  obtenerListaLineDashboard(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			
			List<RegistroLabReporteBean> listaDashboard = registroLabReporteService.obtenerListaLineDashboard(fechaInicio, fechaFin, Integer.parseInt(idPtarxSector));
			List<RegistroLabReporteBean> listaSubParametrosEPSP= new ArrayList<RegistroLabReporteBean>();
			List<String> listaEjeX = new ArrayList<String>();
			for(RegistroLabReporteBean registro : listaDashboard) {
				if(validarListaNoRepeat(listaEjeX, registro.getFechaRegistroString())) {
					listaEjeX.add(registro.getFechaRegistroString());
				}
			}
			for(RegistroLabReporteBean registro : listaDashboard) {
				if(validarListaNoRepeatEPSP(listaSubParametrosEPSP, registro)) {
					listaSubParametrosEPSP.add(registro);
				}
			}
			listaSubParametrosEPSP.sort(new Comparator<RegistroLabReporteBean>() {
				@Override
				public int compare(RegistroLabReporteBean sp1, RegistroLabReporteBean sp2) {
					return sp1.getIdSubParametro().compareTo(sp2.getIdSubParametro());
				}
			});
			
			mapResultado.put("listaSubParametrosEPSP", JsonUtil.convertirObjetoACadenaJson(listaSubParametrosEPSP));
			mapResultado.put("listaDashboard", JsonUtil.convertirObjetoACadenaJson(listaDashboard));
			mapResultado.put("listaEjeX", JsonUtil.convertirObjetoACadenaJson(listaEjeX));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}	
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	public boolean validarListaNoRepeat(List<String> lista, String valorCompare) {
		boolean retorno = true;
		for(String descripcionSubParametro : lista) {
			if(descripcionSubParametro.equals(valorCompare)) {
				retorno = false;
				break;
			}
		}
		return retorno;
	}
	
	public boolean validarListaNoRepeatEPSP(List<RegistroLabReporteBean> listaSubParametrosEPSP, RegistroLabReporteBean registro) {
		boolean retorno = true;
		for(RegistroLabReporteBean subParametroEPSP : listaSubParametrosEPSP) {
			if(registro.getIdSubParametro() == subParametroEPSP.getIdSubParametro() && registro.getIdPtoMuestra() == subParametroEPSP.getIdPtoMuestra()) {
				retorno = false;
				break;
			}
		}
		return retorno;
	}
	
	@RequestMapping(value = "/obtenerListaLineNewAlterDashboard" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  obtenerListaLineNewAlterDashboard(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		Map<Integer, String> mapAuxiliar = new HashMap<Integer, String>();
		try {		
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			List<RegistroLabReporteBean> listaDashboard = registroLabReporteService.obtenerListaLineNewAlterDashboard(fechaInicio, fechaFin, Integer.parseInt(idPtarxSector));
			List<String> listaSubParametros = new ArrayList<String>();
			List<String> listaEjeX = new ArrayList<String>();
			for(RegistroLabReporteBean registro : listaDashboard) {
				if(validarListaNoRepeat(listaSubParametros, registro.getDescripcionSubParametro())) {
					listaSubParametros.add(registro.getDescripcionSubParametro());
				}
			}
			for(RegistroLabReporteBean registro : listaDashboard) {
				if(validarListaNoRepeat(listaEjeX, registro.getDescripcionPtoMuestra())) {
					mapAuxiliar.put(registro.getSecuencia(), registro.getDescripcionPtoMuestra());
				}
			}
			       
			Map<Integer, String> treeMap = new TreeMap<>(mapAuxiliar);
			for (String str : treeMap.values()) {
			    listaEjeX.add(str);
			}
			
			mapResultado.put("listaSubParametros", JsonUtil.convertirObjetoACadenaJson(listaSubParametros));
			mapResultado.put("listaDashboard", JsonUtil.convertirObjetoACadenaJson(listaDashboard));
			mapResultado.put("listaEjeX", JsonUtil.convertirObjetoACadenaJson(listaEjeX));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}	
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
//	inicio cvalenciap -- tree
	@RequestMapping(value = "/buscarReporteGestionPrev" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String buscarReporteGestionPrev(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		RegistroLaboratorioBean registroLaboratorio  = null;
//		inicio lista reporte inicial
//		List<TreeReporteBean> listaObtenida = new ArrayList<TreeReporteBean>();
		List<RegistroLabReporteBean> listaObtenida = new ArrayList<RegistroLabReporteBean>();
//		fin lista reporte inicial
		try {
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			registroLaboratorio = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			if(registroLaboratorio != null && registroLaboratorio.idPtarxSector != null) {
				listaObtenida = registroLabReporteService.obtenerListaTree(registroLaboratorio.getIdPtarxSector());
			}
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se construy?? el ??rbol de par??metros");
			mapResultado.put("registro", registroLaboratorio);
			mapResultado.put("ListaResultado", listaObtenida);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);		
	}
	
	public List<RegistroLabReporteBean> filtrarValoresLista(List<RegistroLabReporteBean> lista, List<TreeReporteBean> listaFiltro) throws GmdException{
		List<RegistroLabReporteBean> newList = new ArrayList<RegistroLabReporteBean>();
		try {
			for(RegistroLabReporteBean registro : lista) {
				boolean flag = false;
				for(TreeReporteBean filtro : listaFiltro) {
					if(registro.getIdSubParametro().intValue() == filtro.getIdSubParametro().intValue() && registro.getIdPtoMuestra().intValue() == filtro.getIdPtoMuestra().intValue()) {
						flag = true;
						break;
					}
				}
				if(flag) {
					newList.add(registro);
				}
			}
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			LOGGER.error(error[1], excepcion);
		}
		return newList;
	}
	
	@RequestMapping(value = "/buscarReporteHistoricoPrev" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String buscarReporteHistoricoPrev(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
//		inicio lista reporte incial
//		List<TreeReporteBean> listaObtenida = new ArrayList<TreeReporteBean>();
		List<RegistroLabReporteBean> listaObtenida = new ArrayList<RegistroLabReporteBean>();
//		fin lista reporte inicial
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			listaObtenida = registroLabReporteService.obtenerListaTree(Integer.parseInt(idPtarxSector));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se construy?? el ??rbol de par??metros");
			mapResultado.put("ListaResultado", listaObtenida);
			respuestaBean.setParametros(mapResultado);
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);		
	}
//	fin cvalenciap -- tree
	
}
