package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.reflect.TypeToken;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.gmd.util.properties.PropiedadesUtil;
import pe.com.sedapal.scr.core.beans.AlertasNotificacionBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.RegistroAdjuntoBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.services.IAlertasNotificacionService;
import pe.com.sedapal.scr.core.services.IAlertasService;
import pe.com.sedapal.scr.core.services.IAnalistaService;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;
import pe.com.sedapal.scr.core.services.INotificacionService;
import pe.com.sedapal.scr.core.services.IParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.IPlantaTratamientoService;
import pe.com.sedapal.scr.core.services.IPuntoMuestraPtarSectorService;
import pe.com.sedapal.scr.core.services.IRegistroAceiteService;
import pe.com.sedapal.scr.core.services.IRegistroAdjuntoService;
import pe.com.sedapal.scr.core.services.IRegistroDBOService;
import pe.com.sedapal.scr.core.services.IRegistroDirectoService;
import pe.com.sedapal.scr.core.services.IRegistroHidrobiologicoService;
import pe.com.sedapal.scr.core.services.IRegistroLaboratorioService;
import pe.com.sedapal.scr.core.services.IRegistroMicrobiologicoSecundarioService;
import pe.com.sedapal.scr.core.services.IRegistroMicrobiologicoService;
import pe.com.sedapal.scr.core.services.IRegistroParasitologicoService;
import pe.com.sedapal.scr.core.services.IRegistroSolidoService;
import pe.com.sedapal.scr.core.services.ISemillaBlankService;
import pe.com.sedapal.scr.core.services.ISemillaSamplesService;
import pe.com.sedapal.scr.core.services.ISemillaSeededService;
import pe.com.sedapal.scr.core.services.ISemillaStandardService;
import pe.com.sedapal.scr.core.services.ISubParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.ITuboDilucionService;
import pe.com.sedapal.scr.core.services.IVariableService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.BPdf;
import pe.com.sedapal.scr.web.util.ExportWebUtil;
import pe.com.sedapal.scr.web.util.JsonUtil;
import pe.com.sedmail.cliente.bean.ParametrosCorreo;
import pe.com.sedmail.cliente.bean.ResponseBean;
import pe.com.sedmail.cliente.util.ConstantesCliente;
import pe.com.sedmail.cliente.ws.SedmailClienteWs;

/**
 * @author GMD
 * Controlador de Registros PTAR
 */
@Controller
@RequestMapping(value = {"/registroSptar", "/aprobacionSptar"})
public class RegistroController {
	
	private static final Logger LOGGER = Logger.getLogger(MantenimientoController.class);

	private BRespuestaBean respuestaBean = new BRespuestaBean();
	
	@Autowired
	private GeneralController generalController;	
	
	@Autowired
	private IPlantaTratamientoService plantaTratamientoService;
	
	@Autowired
	private IParametroPtarSectorService parametroPtarSectorService;
	
	@Autowired
	private IAnalistaService analistaService;
	
	@Autowired
	private IRegistroDirectoService registroDirectoService;
	
	@Autowired
	private IPuntoMuestraPtarSectorService puntoMuestraPtarSectorService;
	
	@Autowired
	private IRegistroLaboratorioService registroLaboratorioService;
	
	@Autowired
	private IRegistroParasitologicoService registroParasitologicoService;
	
	@Autowired
	private IRegistroHidrobiologicoService registroHidrobiologicoService;
	
	@Autowired
	private IRegistroMicrobiologicoService registroMicrobiologicoService;
	
	@Autowired
	private IRegistroMicrobiologicoSecundarioService registroMicrobiologicoSecundarioService;
	
	@Autowired
	private IRegistroAceiteService registroAceiteService;
	
	@Autowired
	private IDetalleGeneralService detalleGeneralService;
	
	@Autowired
	private IRegistroAdjuntoService registroAdjuntoService;
	
	@Autowired
	private IRegistroSolidoService registroSolidoService;
	
	@Autowired
	private IRegistroDBOService registroDBOService;
	
	@Autowired
	private IVariableService variableService;
	
	@Autowired
	private ITuboDilucionService tuboDilucionService;
	
	@Autowired
	IAlertasNotificacionService alertasNotificacionService;
	
	@Autowired
	INotificacionService notificacionService;
	
	@Autowired
	private ISemillaBlankService  semillaBlankService;
	
	@Autowired
	private ISemillaSeededService  semillaSeededService;
	
	@Autowired
	private ISemillaStandardService  semillaStandardService;
	
	@Autowired
	private ISemillaSamplesService  semillaSamplesService;
	
	@Autowired
	private IAlertasService alertasService;
	
	@Autowired
	private ISubParametroPtarSectorService subParametroPtarSectorService;
	
	/*inicio adecuacion sedmail*/
	@Autowired
	private IConfigCorreoService configCorreoService;
	
	@Autowired
	private SedmailClienteWs mailtub;
	/*fin adecuacion sedmail*/
	
	
	/**
	 * M??todo que carga la vista principal de registros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista principal de registros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroPrincipal", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroPrincipal(HttpServletRequest request, Model model) throws GmdException {
		try {
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			String registroLabJSON = request.getParameter("objRegistroLaboratorio");
			RegistroLaboratorioBean bean = JsonUtil.convertirCadenaJsonAObjeto(registroLabJSON,RegistroLaboratorioBean.class);
			if(bean != null && bean.getIdRegistroLaboratorio() != null && bean.getIdRegistroLaboratorio().intValue() != 0) {
				List<PlantaTratamientoBean> ltaPlantaTratamiento = plantaTratamientoService.obtenerPlantaTratamientoxPtarSector(bean.getIdPtarxSector());
				model.addAttribute("objRegistroLaboratorio",bean);
				model.addAttribute("ltaPlantaTratamiento",ltaPlantaTratamiento);
				model.addAttribute("idParametroInicial",bean.idParametro);
				model.addAttribute("nombrePlantaInicial",bean.getDescripcionPtar()+" - "+bean.getDescripcionSector());				
			}else {
				List<PlantaTratamientoBean> ltaPlantaTratamiento = plantaTratamientoService.obtenerPlantaTratamiento();
				model.addAttribute("objRegistroLaboratorio", new RegistroLaboratorioBean());
				model.addAttribute("ltaPlantaTratamiento",ltaPlantaTratamiento);
				model.addAttribute("idParametroInicial",null);
				model.addAttribute("nombrePlantaInicial","");			
			}
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
		return "/sptar/registro/registroPrincipal";
	}
	
	/**
	 * M??todo que carga la vista de archivos adjuntos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de archivos adjuntos
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerAdjuntos", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerAdjuntos(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("idRegistroLaboratorioAdjunto", request.getParameter("idRegistroLaboratorioAdjunto"));
			model.addAttribute("idPtarSectorAdjunto", request.getParameter("idPtarSectorAdjunto"));
			model.addAttribute("idParametroAdjunto", request.getParameter("idParametroAdjunto"));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("estadoAprobacionG",request.getParameter("estadoAprobacion"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/verAdjuntos";
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros adjuntos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro adjunto 
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarListaAdjuntos" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarListaAdjuntos(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorioAdj = request.getParameter("idRegistroLaboratorioAdj");
			String idParametroAdj = request.getParameter("idParametroAdj");
			List<RegistroAdjuntoBean> ltaResultado = registroAdjuntoService.obtenerRegistroAdjuntobyRegistroLab(Integer.parseInt(idRegistroLaboratorioAdj), Integer.parseInt(idParametroAdj));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ltaResultado));
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
	
	/**
	 * M??todo que realiza la b??squeda de plantas de tratamiento
	 * 
	 * @param request
	 * @param model
	 * @return Lista de plsntas de tratamiento
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarListaPtarMaps" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarListaPtarMaps(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			List<PlantaTratamientoBean> lista = plantaTratamientoService.obtenerPlantaTratamiento();
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
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
	
	/**
	 * M??todo que realiza la b??squeda de par??metros asignados al Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Lista de parametros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarParametroAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarParametroAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			List<ParametroPtarSectorBean> lista = parametroPtarSectorService.obtenerltaParametroPtarSectorAsignados(idPtarPorSector);
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de par??metros asignados para men??
	 * 
	 * @param request
	 * @param model
	 * @return Lista de par??metros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarParametroAsignadosMenu", method = RequestMethod.POST)
	public @ResponseBody String buscarParametroAsignadosMenu(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			List<ParametroPtarSectorBean> lista = parametroPtarSectorService.obtenerltaParametroPtarSectorAsignadosMenu(idPtarPorSector,analistaBean.getIdAnalista());
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que carga la vista de registros directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registros directo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroDirecto", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroDirecto(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			List<RegistroDirectoBean> ltaRegDirecto = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionDirecto = 0;
			String observacionDirecto = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionDirecto = beanExtraido.getFlagDirecto();
					observacionDirecto = beanExtraido.getObservacionDirecto();
				}
				ltaRegDirecto = registroDirectoService.obtenerRegistroDirectoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegDirecto != null && ltaRegDirecto.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegDirecto = registroDirectoService.obtenerRegistroDirectoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegDirecto = registroDirectoService.obtenerRegistroDirectoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionDirecto", estadoAprobacionDirecto);
			model.addAttribute("ltaRegDirecto", ltaRegDirecto);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroDirectoService.obtenerRegistroDirectoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionDirecto", observacionDirecto);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroDirecto";
	}
	
	/**
	 * M??todo que carga la vista de registros DBO5 - F??sico Qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registros F??sico Qu??mico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroDBO5", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroDBO5(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
			List<ParametroPtarSectorBean> ltaParametrosFisicoQuimico = parametroPtarSectorService.obtenerltaParametroPtarSectorAsignadosMenu(Integer.parseInt(idPtarSector),analistaBean.getIdAnalista());
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("idRegistroLaboratorio", idRegistroLaboratorio);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("ltaParametrosFisicoQuimico",ltaParametrosFisicoQuimico);			
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));		
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroFisicoQuimico";
	}
	
	/**
	 * M??todo que realiza el registro de objetos directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegDirectoJSON = request.getParameter("listaRegistroDirecto");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroDirecto = request.getParameter("observacionRegistroDirecto");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_DIRECTO;
			Type listType = new TypeToken<List<RegistroDirectoBean>>() {}.getType();
			List<RegistroDirectoBean> ltaRegistroDirecto = JsonUtil.convertirCadenaJsonALista(ltaRegDirectoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroDirecto(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroDirecto, auditoria, Integer.parseInt(flagValidador), observacionRegistroDirecto);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroDirecto(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroDirecto, auditoria, Integer.parseInt(flagValidador), observacionRegistroDirecto);
			}
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGDIRECTO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_DIRECTO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionDirecto", registroFinal.getObservacionDirecto());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionDirecto", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionDirecto(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionDirecto = request.getParameter("observacionRegistroDirecto");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionDirecto(observacionDirecto);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionDirecto", observacionDirecto);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros directo
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos de tipo directo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroDirectoBean> ListaResultado = registroDirectoService.obtenerRegistroDirectoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de objetos directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String registroDirectoJSON = request.getParameter("registroDirectoBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_DIRECTO;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));			
			RegistroDirectoBean registroDirectoBean = JsonUtil.convertirCadenaJsonAObjeto(registroDirectoJSON,RegistroDirectoBean.class);
			registroDirectoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroDirectoBean.setIdUsuaCrea(Util.getUserLoged());
			registroDirectoBean.setDeTermCrea(Util.obtenerTerminal(request));
			registroDirectoBean.setIdUsuaModi(Util.getUserLoged());
			registroDirectoBean.setDeTermModi(Util.obtenerTerminal(request));
			registroDirectoBean.setIdParametro(idParametro);
			registroDirectoBean.setFechaMonitoreo(ParametrosUtil.getFechaActualToDateHour());
			registroDirectoBean.setFechaRegDato(ParametrosUtil.getFechaActualToDateHour());
			registroDirectoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
			registroDirectoBean.setFlagAprobador(flagValidador);
			registroDirectoBean.setDescripcionObservacion(" ");
			registroDirectoBean.setNumProfundida(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumProfundida(),2));
			registroDirectoBean.setNumValor(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumValor(),2));
			if(registroDirectoBean.getNumFactor() != null) {
				registroDirectoBean.setNumFactor(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumFactor(),2));
				registroDirectoBean.setNumValorDQO(ParametrosUtil.formatearDecimales((registroDirectoBean.getNumValor() * registroDirectoBean.getNumFactor()) ,2));
			}else {
				registroDirectoBean.setNumFactor(ParametrosUtil.formatearDecimales(0.00,0));
				registroDirectoBean.setNumValorDQO(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumValor(),2));
			}
			if(registroDirectoBean.getIdRegDirecto() != null) {
				registroDirectoService.actualizarRegistroDirecto(registroDirectoBean);
				idRegistroLab = registroDirectoBean.getIdRegLaboratorio();
			}else {
				registroDirectoService.registrarRegistroDirecto(registroDirectoBean);
				idRegistroLab = registroDirectoBean.getIdRegLaboratorio();
			}
			registroDirectoService.actualizarEstadoRegistroLaboratorioDirecto(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGDIRECTO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de registro directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroDirecto = request.getParameter("idRegistroDirecto");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroDirectoBean bean = new RegistroDirectoBean();
			bean.setIdRegDirecto(Integer.parseInt(idRegistroDirecto));
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			List<RegistroDirectoBean> lta = registroDirectoService.obtenerRegistroDirectoByidDirecto(Integer.parseInt(idRegistroDirecto),null,null);
			Integer idLab = null;
			if(lta != null && lta.size() > 0) {
				idLab = lta.get(0).getIdRegLaboratorio();
			}
			registroDirectoService.anularRegistroDirecto(bean);		
			registroDirectoService.actualizarEstadoRegistroLaboratorioDirecto(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO,auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n m??ltiple de registros directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String ltaRegistroDirectoJSON = request.getParameter("ltaRegistroDirecto");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroDirectoBean>>() {}.getType();
			List<RegistroDirectoBean> ltaRegistroDirecto = JsonUtil.convertirCadenaJsonALista(ltaRegistroDirectoJSON, listType);
			Integer idLab = ltaRegistroDirecto.get(0).getIdRegLaboratorio();
			registroDirectoService.anularMultipleRegistroDirecto(ltaRegistroDirecto, auditoria);
			registroDirectoService.actualizarEstadoRegistroLaboratorioDirecto(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO, auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n del registro directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroDirecto" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroDirecto(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroDirectoBean registroDirecto = new RegistroDirectoBean();
			registroDirecto.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroDirecto.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroDirecto.setIdUsuaModi(Util.getUserLoged());
			registroDirecto.setDeTermModi(Util.obtenerTerminal(request));
			registroDirectoService.aprobarRegistroDirecto(registroDirecto);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Directo Correctamente.");
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
	 * M??todo que carga la vista de registros parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registros parasitol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroParasitologico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroParasitologico(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			List<RegistroParasitologicoBean> ltaRegParasitologico = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionParasitologico = 0;
			String observacionParasitologico = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionParasitologico = beanExtraido.getFlagParasitologico();
					observacionParasitologico = beanExtraido.getObservacionParasitologico();
				}
				ltaRegParasitologico = registroParasitologicoService.obtenerRegistroParasitologicoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegParasitologico != null && ltaRegParasitologico.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegParasitologico = registroParasitologicoService.obtenerRegistroParasitologicoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegParasitologico = registroParasitologicoService.obtenerRegistroParasitologicoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionParasitologico", estadoAprobacionParasitologico);
			model.addAttribute("ltaRegParasitologico", ltaRegParasitologico);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("ltaMicroorganismo", detalleGeneralService.obtenerListaMicroPara(ConstantesUtil.ID_MICRO_PARASITO));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroParasitologicoService.obtenerRegistroParasitologicoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionParasitologico", observacionParasitologico);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroParasitologico";
	}
	
	/**
	 * M??todo que realiza la b??squeda de microorganismos parasitol??gicos por subparametro
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo detalle general
	 * @throws GmdException
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/buscarListaMicroPara", method = RequestMethod.POST)
	public @ResponseBody String buscarListaMicroPara(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idSubParametro = request.getParameter("idSubParametro");
			List<DetalleGeneralBean> lista = detalleGeneralService.obtenerListaMicroPara(Integer.parseInt(idSubParametro));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza el registro de objetos tipo parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegParasitologicoJSON = request.getParameter("listaRegistroParasitologico");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroParasitologico = request.getParameter("observacionRegistroParasitologico");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO;
			Type listType = new TypeToken<List<RegistroParasitologicoBean>>() {}.getType();
			List<RegistroParasitologicoBean> ltaRegistroParasitologico = JsonUtil.convertirCadenaJsonALista(ltaRegParasitologicoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroParasitologico(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroParasitologico, auditoria, Integer.parseInt(flagValidador), observacionRegistroParasitologico);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroParasitologico(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroParasitologico, auditoria, Integer.parseInt(flagValidador), observacionRegistroParasitologico);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGPARASITOLOGICO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_PARA);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionParasitologico", registroFinal.getObservacionParasitologico());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionParasitologico", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionParasitologico(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionParasitologico = request.getParameter("observacionRegistroParasitologico");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionParasitologico(observacionParasitologico);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionParasitologico", observacionParasitologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros parasitol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro parasitol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroParasitologicoBean> ListaResultado = registroParasitologicoService.obtenerRegistroParasitologicoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de objetos parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String registroParasitologicoJSON = request.getParameter("registroParasitologicoBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));	
			RegistroParasitologicoBean registroParasitologicoBean = JsonUtil.convertirCadenaJsonAObjeto(registroParasitologicoJSON, RegistroParasitologicoBean.class);
			registroParasitologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroParasitologicoBean.setIdUsuaCrea(Util.getUserLoged());
			registroParasitologicoBean.setDeTermCrea(Util.obtenerTerminal(request));
			registroParasitologicoBean.setIdUsuaModi(Util.getUserLoged());
			registroParasitologicoBean.setDeTermModi(Util.obtenerTerminal(request));
			registroParasitologicoBean.setIdParametro(idParametro);
			registroParasitologicoBean.setFechaMonitoreo(ParametrosUtil.getFechaActualToDateHour());
			registroParasitologicoBean.setFechaRegDato(ParametrosUtil.getFechaActualToDateHour());
			registroParasitologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
			registroParasitologicoBean.setFlagAprobador(flagValidador);
			if(registroParasitologicoBean.getDescripcionObservacion() == null || StringUtils.isEmpty(registroParasitologicoBean.getDescripcionObservacion())) {
				registroParasitologicoBean.setDescripcionObservacion(" ");
			}
			registroParasitologicoBean.setNumCantidad(ParametrosUtil.formatearDecimales(registroParasitologicoBean.getNumCantidad(),2));
			if(registroParasitologicoBean.getIdRegParasitologico() != null) {
				registroParasitologicoService.actualizarRegistroParasitologico(registroParasitologicoBean);
				idRegistroLab = registroParasitologicoBean.getIdRegLaboratorio();
			}else {
				registroParasitologicoService.registrarRegistroParasitologico(registroParasitologicoBean);
				idRegistroLab = registroParasitologicoBean.getIdRegLaboratorio();
			}
			registroParasitologicoService.actualizarEstadoRegistroLaboratorioParasitologico(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGPARASITOLOGICO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroParasitologico = request.getParameter("idRegistroParasitologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroParasitologicoBean bean = new RegistroParasitologicoBean();
			bean.setIdRegParasitologico(Integer.parseInt(idRegistroParasitologico));
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			List<RegistroParasitologicoBean> lta = registroParasitologicoService.obtenerRegistroParasitologicoByidParasitologico(Integer.parseInt(idRegistroParasitologico),null,null);
			Integer idLab = null;
			if(lta != null && lta.size() > 0) {
				idLab = lta.get(0).getIdRegLaboratorio();
			}
			registroParasitologicoService.anularRegistroParasitologico(bean);		
			registroParasitologicoService.actualizarEstadoRegistroLaboratorioParasitologico(idLab,ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO,auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n de m??ltiples registros parasitol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {			
			String ltaRegistroParasitologicoJSON = request.getParameter("ltaRegistroParasitologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroParasitologicoBean>>() {}.getType();
			List<RegistroParasitologicoBean> ltaRegistroParasitologico = JsonUtil.convertirCadenaJsonALista(ltaRegistroParasitologicoJSON, listType);
			Integer idLab = ltaRegistroParasitologico.get(0).getIdRegLaboratorio();
			registroParasitologicoService.anularMultipleRegistroParasitologico(ltaRegistroParasitologico, auditoria);
			registroParasitologicoService.actualizarEstadoRegistroLaboratorioParasitologico(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO, auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n de un registro parasitol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroParasitologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroParasitologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroParasitologicoBean registroParasitologico = new RegistroParasitologicoBean();
			registroParasitologico.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroParasitologico.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroParasitologico.setIdUsuaModi(Util.getUserLoged());
			registroParasitologico.setDeTermModi(Util.obtenerTerminal(request));
			registroParasitologicoService.aprobarRegistroParasitologico(registroParasitologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Parasitologico Correctamente.");
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
	 * M??todo que carga la vista de registros hidrobiol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registros hidrobiol??gicos
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroHidrobiologico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroHidrobiologico(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			List<RegistroHidrobiologicoBean> ltaRegHidrobiologico = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionHidrobiologico = 0;
			String observacionHidrobiologico = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionHidrobiologico = beanExtraido.getFlagHidrobiologico();
					observacionHidrobiologico = beanExtraido.getObservacionHidrobiologico();
				}
				ltaRegHidrobiologico = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegHidrobiologico != null && ltaRegHidrobiologico.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegHidrobiologico = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegHidrobiologico = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionHidrobiologico", estadoAprobacionHidrobiologico);
			model.addAttribute("ltaRegHidrobiologico", ltaRegHidrobiologico);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("ltaMicroorganismo", detalleGeneralService.obtenerListaMicroHidro(ConstantesUtil.ID_MICRO_HIDRO));
			model.addAttribute("ltaTipoConteo", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_TIPO_CONTEO));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaVariableGeneral", variableService.obtenerLtaVariable(Integer.parseInt(idParametro)));
			model.addAttribute("ltaSubParametrosTodo", registroHidrobiologicoService.obtenerRegistroHidrobiologicoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionHidrobiologico", observacionHidrobiologico);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroHidrobiologico";
	}
	
	/**
	 * M??todo que realiza la b??squeda de microorganismos hidrobiol??gicos por subparametro
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo detale general
	 * @throws GmdException
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/buscarListaMicroHidro", method = RequestMethod.POST)
	public @ResponseBody String buscarListaMicroHidro(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idSubParametro = request.getParameter("idSubParametro");
			List<DetalleGeneralBean> lista = detalleGeneralService.obtenerListaMicroHidro(Integer.parseInt(idSubParametro));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza el registro de objetos tipo hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegHidrobiologicoJSON = request.getParameter("listaRegistroHidrobiologico");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroHidrobiologico = request.getParameter("observacionRegistroHidrobiologico");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO;
			Type listType = new TypeToken<List<RegistroHidrobiologicoBean>>() {}.getType();
			List<RegistroHidrobiologicoBean> ltaRegistroHidrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegHidrobiologicoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroHidrobiologico(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroHidrobiologico, auditoria, Integer.parseInt(flagValidador), observacionRegistroHidrobiologico);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroHidrobiologico(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroHidrobiologico, auditoria, Integer.parseInt(flagValidador), observacionRegistroHidrobiologico);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGHIDROBIOLOGICO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_HIDRO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionHidrobiologico", registroFinal.getObservacionHidrobiologico());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionHidrobiologico", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionHidrobiologico(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionHidrobiologico = request.getParameter("observacionRegistroHidrobiologico");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionHidrobiologico(observacionHidrobiologico);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionHidrobiologico", observacionHidrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro hidrobiol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroHidrobiologicoBean> ListaResultado = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de objetos tipo hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String registroHidrobiologicoJSON = request.getParameter("registroHidrobiologicoBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));	
			RegistroHidrobiologicoBean registroHidrobiologicoBean = JsonUtil.convertirCadenaJsonAObjeto(registroHidrobiologicoJSON, RegistroHidrobiologicoBean.class);
			registroHidrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroHidrobiologicoBean.setIdUsuaCrea(Util.getUserLoged());
			registroHidrobiologicoBean.setDeTermCrea(Util.obtenerTerminal(request));
			registroHidrobiologicoBean.setIdUsuaModi(Util.getUserLoged());
			registroHidrobiologicoBean.setDeTermModi(Util.obtenerTerminal(request));
			registroHidrobiologicoBean.setIdParametro(idParametro);
			registroHidrobiologicoBean.setFechaMonitoreo(ParametrosUtil.getFechaActualToDateHour());
			registroHidrobiologicoBean.setFechaRegDato(ParametrosUtil.getFechaActualToDateHour());
			registroHidrobiologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
			registroHidrobiologicoBean.setFlagAprobador(flagValidador);
			if(registroHidrobiologicoBean.getDescripcionObservacion() == null || StringUtils.isEmpty(registroHidrobiologicoBean.getDescripcionObservacion())) {
				registroHidrobiologicoBean.setDescripcionObservacion(" ");
			}
			registroHidrobiologicoBean.setNumCantidad(ParametrosUtil.formatearDecimales(registroHidrobiologicoBean.getNumCantidad(),2));
			if(registroHidrobiologicoBean.getIdRegHidrobiologico() != null) {
				registroHidrobiologicoService.actualizarRegistroHidrobiologico(registroHidrobiologicoBean);
				idRegistroLab = registroHidrobiologicoBean.getIdRegLaboratorio();
			}else {
				registroHidrobiologicoService.registrarRegistroHidrobiologico(registroHidrobiologicoBean);
				idRegistroLab = registroHidrobiologicoBean.getIdRegLaboratorio();
			}
			registroHidrobiologicoService.actualizarEstadoRegistroLaboratorioHidrobiologico(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGHIDROBIOLOGICO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroHidrobiologico = request.getParameter("idRegistroHidrobiologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroHidrobiologicoBean bean = new RegistroHidrobiologicoBean();
			bean.setIdRegHidrobiologico(Integer.parseInt(idRegistroHidrobiologico));
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			List<RegistroHidrobiologicoBean> lta = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByidHidrobiologico(Integer.parseInt(idRegistroHidrobiologico),null,null);
			Integer idLab = null;
			if(lta != null && lta.size() > 0) {
				idLab = lta.get(0).getIdRegLaboratorio();
			}
			registroHidrobiologicoService.anularRegistroHidrobiologico(bean);		
			registroHidrobiologicoService.actualizarEstadoRegistroLaboratorioHidrobiologico(idLab,ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO,auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n de m??ltiples registros hidrobiol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {			
			String ltaRegistroHidrobiologicoJSON = request.getParameter("ltaRegistroHidrobiologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroHidrobiologicoBean>>() {}.getType();
			List<RegistroHidrobiologicoBean> ltaRegistroHidrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegistroHidrobiologicoJSON, listType);
			Integer idLab = ltaRegistroHidrobiologico.get(0).getIdRegLaboratorio();
			registroHidrobiologicoService.anularMultipleRegistroHidrobiologico(ltaRegistroHidrobiologico, auditoria);
			registroHidrobiologicoService.actualizarEstadoRegistroLaboratorioHidrobiologico(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO, auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n de un registro hidrobiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroHidrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroHidrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroHidrobiologicoBean registroHidrobiologico = new RegistroHidrobiologicoBean();
			registroHidrobiologico.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroHidrobiologico.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroHidrobiologico.setIdUsuaModi(Util.getUserLoged());
			registroHidrobiologico.setDeTermModi(Util.obtenerTerminal(request));
			registroHidrobiologicoService.aprobarRegistroHidrobiologico(registroHidrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Hidrobiologico Correctamente.");
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
	 * M??todo que realiza el registro de un archivo adjunto
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/accionRegistrarAdjunto" , method = RequestMethod.POST)
	public @ResponseBody String  accionRegistrarAdjunto(MultipartHttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorioAdjunto");
			String idPtarxSector = request.getParameter("idPtarSectorAdjunto");
			String idParametro = request.getParameter("idParametroAdjunto");
			
			String KeyEstadoRptaArchivo = "estadoRespuesta";
			String KeyMensajeRptaArchivo = "mensajeRespuesta";
			String KeynombreArchivoRpta = "nombreArchivo";
			String KeynombreArchivoInternoRpta = "nombreArchivoInterno";
			Map<String,Object> parametrosRetornoArchivo = generalController.procesarSubirArchivo(request.getFile("fileArchivo"),"adjunto");			
			if(ParametrosUtil.ValidarKeyMapParametros(KeyEstadoRptaArchivo,parametrosRetornoArchivo)){
				if(parametrosRetornoArchivo.get(KeyEstadoRptaArchivo).toString().equals(ConstanteServices.OK)){
					String archivoInterno = parametrosRetornoArchivo.get(KeynombreArchivoInternoRpta).toString();
					String archivoOriginal = parametrosRetornoArchivo.get(KeynombreArchivoRpta).toString();
					RegistroAdjuntoBean adjuntoBean = new RegistroAdjuntoBean();
					adjuntoBean.setIdRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
					adjuntoBean.setIdPtarxSector(Integer.parseInt(idPtarxSector));
					adjuntoBean.setIdParametro(Integer.parseInt(idParametro));
					adjuntoBean.setNombreInterno(archivoInterno);
					adjuntoBean.setNombreOriginal(archivoOriginal);
					adjuntoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
					adjuntoBean.setFlagAprobador(ConstantesUtil.FLAG_VALIDADOR);
					adjuntoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					adjuntoBean.setIdUsuaCrea(Util.getUserLoged());
					adjuntoBean.setDeTermCrea(Util.obtenerTerminal(request));
					registroAdjuntoService.registrarRegistroAdjunto(adjuntoBean);					
					respuestaBean.setMensajeRespuesta("Archivo Guardado Correctamente.");
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				}else {
					respuestaBean.setMensajeRespuesta(parametrosRetornoArchivo.get(KeyMensajeRptaArchivo).toString());
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				}
			}else {
				respuestaBean.setMensajeRespuesta("Ocurrio un error al Cargar el Archivo");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}			
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
	 * M??todo que carga la vista de registro aceita
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registro aceite
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroAceite", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroAceite(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			List<RegistroAceiteBean> ltaRegAceite = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionAceite = 0;
			String observacionAceite = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionAceite = beanExtraido.getFlagAceite();
					observacionAceite = beanExtraido.getObservacionAceites();
				}
				ltaRegAceite = registroAceiteService.obtenerRegistroAceiteByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegAceite != null && ltaRegAceite.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegAceite = registroAceiteService.obtenerRegistroAceiteByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegAceite = registroAceiteService.obtenerRegistroAceiteByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionAceite", estadoAprobacionAceite);
			model.addAttribute("ltaRegAceite", ltaRegAceite);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("ltaMicroorganismo", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_MICROORGANISMO));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroAceiteService.obtenerRegistroAceiteSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionAceite", observacionAceite);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroAceite";
	}
	
	/**
	 * M??todo que realiza el registro de objetos tipo aceite
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegAceiteJSON = request.getParameter("listaRegistroAceite");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroAceite = request.getParameter("observacionRegistroAceite");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_ACEITE;
			Type listType = new TypeToken<List<RegistroAceiteBean>>() {}.getType();
			List<RegistroAceiteBean> ltaRegistroAceite = JsonUtil.convertirCadenaJsonALista(ltaRegAceiteJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroAceite(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroAceite, auditoria, Integer.parseInt(flagValidador), observacionRegistroAceite);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroAceite(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroAceite, auditoria, Integer.parseInt(flagValidador), observacionRegistroAceite);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGACEITE, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_ACEITE);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionAceite", registroFinal.getObservacionAceites());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Directo
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionAceite", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionAceite(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionAceite = request.getParameter("observacionRegistroAceite");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionAceites(observacionAceite);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionAceite", observacionAceite);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros aceite
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro aceite
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroAceiteBean> ListaResultado = registroAceiteService.obtenerRegistroAceiteByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de objetos tipo aceite
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String registroAceiteJSON = request.getParameter("registroAceiteBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_ACEITE;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));	
			RegistroAceiteBean registroAceiteBean = JsonUtil.convertirCadenaJsonAObjeto(registroAceiteJSON, RegistroAceiteBean.class);
			registroAceiteBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroAceiteBean.setIdUsuaCrea(Util.getUserLoged());
			registroAceiteBean.setDeTermCrea(Util.obtenerTerminal(request));
			registroAceiteBean.setIdUsuaModi(Util.getUserLoged());
			registroAceiteBean.setDeTermModi(Util.obtenerTerminal(request));
			registroAceiteBean.setIdParametro(idParametro);
			registroAceiteBean.setFechaMonitoreo(ParametrosUtil.getFechaActualToDateHour());
			registroAceiteBean.setFechaRegDato(ParametrosUtil.getFechaActualToDateHour());
			registroAceiteBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
			registroAceiteBean.setFlagAprobador(flagValidador);
			if(StringUtils.isEmpty(registroAceiteBean.getDescripcionObservacion())){
				registroAceiteBean.setDescripcionObservacion(" ");
			}else {
				registroAceiteBean.setDescripcionObservacion(registroAceiteBean.getDescripcionObservacion());
			}
			registroAceiteBean.setNumResultado(ParametrosUtil.formatearDecimales(registroAceiteBean.getNumResultado(),2));
			if(registroAceiteBean.getIdRegAceite() != null) {
				registroAceiteService.actualizarRegistroAceite(registroAceiteBean);
				idRegistroLab = registroAceiteBean.getIdRegLaboratorio();
			}else {
				registroAceiteService.registrarRegistroAceite(registroAceiteBean);
				idRegistroLab = registroAceiteBean.getIdRegLaboratorio();
			}
			registroAceiteService.actualizarEstadoRegistroLaboratorioAceite(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGACEITE, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro aceite
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroAceite = request.getParameter("idRegistroAceite");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroAceiteBean bean = new RegistroAceiteBean();
			bean.setIdRegAceite(Integer.parseInt(idRegistroAceite));
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			List<RegistroAceiteBean> lta = registroAceiteService.obtenerRegistroAceiteByidAceite(Integer.parseInt(idRegistroAceite),null,null);
			Integer idLab = null;
			if(lta != null && lta.size() > 0) {
				idLab = lta.get(0).getIdRegLaboratorio();
			}
			registroAceiteService.anularRegistroAceite(bean);		
			registroAceiteService.actualizarEstadoRegistroLaboratorioAceite(idLab,ConstantesUtil.ID_PARAMETRO_ACEITE,auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n de m??ltiples registros aceite
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {			
			String ltaRegistroAceiteJSON = request.getParameter("ltaRegistroAceite");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroAceiteBean>>() {}.getType();
			List<RegistroAceiteBean> ltaRegistroAceite = JsonUtil.convertirCadenaJsonALista(ltaRegistroAceiteJSON, listType);
			Integer idLab = ltaRegistroAceite.get(0).getIdRegLaboratorio();
			registroAceiteService.anularMultipleRegistroAceite(ltaRegistroAceite, auditoria);
			registroAceiteService.actualizarEstadoRegistroLaboratorioAceite(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO, auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n de un registro aceite
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroAceite" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroAceite(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroAceiteBean registroAceite = new RegistroAceiteBean();
			registroAceite.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroAceite.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroAceite.setIdUsuaModi(Util.getUserLoged());
			registroAceite.setDeTermModi(Util.obtenerTerminal(request));
			registroAceiteService.aprobarRegistroAceite(registroAceite);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Aceite Correctamente.");
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
	 * M??todo que carga la vista de registro solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registro solido
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroSolido", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroSolido(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			String idTipoSolido = request.getParameter("idTipoSolido");
			if(StringUtils.isEmpty(idTipoSolido)) {
				idTipoSolido = ""+ConstantesUtil.ID_TIPO_SOLIDOS_SUSPENDIDOS_TOTALES;
			}
			List<RegistroSolidoBean> ltaRegSolido = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			String fechaDBOInicial = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionSolido = 0;
			String observacionSolido = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				List<RegistroDBOBean> ListaResultado = registroDBOService.obtenerRegistroDBOByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ListaResultado != null && ListaResultado.size() > 0) {
					fechaDBOInicial = ListaResultado.get(0).getFechaInicialString();
				}
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionSolido = beanExtraido.getFlagSolido();
					observacionSolido = beanExtraido.getObservacionSolidos();
				}
				ltaRegSolido = registroSolidoService.obtenerRegistroSolidoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idTipoSolido));
				if(ltaRegSolido != null && ltaRegSolido.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {					
					ltaRegSolido = registroSolidoService.obtenerRegistroSolidoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					for (RegistroSolidoBean itemSolido : ltaRegSolido) {
						itemSolido.setFechaMonitoreoString(fechaDBOInicial);
						itemSolido.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(itemSolido.getFechaMonitoreoString()));
					}
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}	
			}else {
				ltaRegSolido = registroSolidoService.obtenerRegistroSolidoByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				if(!StringUtils.isEmpty(idRegistroLaboratorio)) {
					List<RegistroDBOBean> ListaResultado = registroDBOService.obtenerRegistroDBOByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
					if(ListaResultado != null && ListaResultado.size() > 0) {
						fechaDBOInicial = ListaResultado.get(0).getFechaInicialString();
					}
					for (RegistroSolidoBean itemSolido : ltaRegSolido) {
						itemSolido.setFechaMonitoreoString(fechaDBOInicial);
						itemSolido.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(itemSolido.getFechaMonitoreoString()));
					}
				}
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionSolido", estadoAprobacionSolido);
			model.addAttribute("ltaRegSolido", ltaRegSolido);
			model.addAttribute("fechaDBOInicial", fechaDBOInicial);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("idTipoSolido", idTipoSolido);
			model.addAttribute("ltaSubParametrosTodo", registroSolidoService.obtenerRegistroSolidoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaVariableGeneral", variableService.obtenerLtaVariable(Integer.parseInt(idParametro)));		
			model.addAttribute("ltaTipoSolido", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_TIPO_SOLIDO));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionSolido", observacionSolido);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroSolido";
	}
	
	/**
	 * M??todo que realiza el registro de objetos tipo solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegSolidoJSON = request.getParameter("listaRegistroSolido");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroSolido = request.getParameter("observacionRegistroSolido");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_SOLIDO;
			Type listType = new TypeToken<List<RegistroSolidoBean>>() {}.getType();
			List<RegistroSolidoBean> ltaRegistroSolido = JsonUtil.convertirCadenaJsonALista(ltaRegSolidoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroSolido(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroSolido, auditoria, Integer.parseInt(flagValidador), observacionRegistroSolido);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroSolido(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroSolido, auditoria, Integer.parseInt(flagValidador), observacionRegistroSolido);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGSOLIDO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_SOLIDO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionSolido", registroFinal.getObservacionSolidos());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionSolido", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionSolido(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionSolido = request.getParameter("observacionRegistroSolido");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionSolidos(observacionSolido);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionSolido", observacionSolido);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros solidos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo solido
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			String idTipoSolido = request.getParameter("idTipoSolido");
			List<RegistroSolidoBean> ListaResultado = registroSolidoService.obtenerRegistroSolidoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda),Integer.parseInt(idTipoSolido));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de un registro solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Respuesta con estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String registroSolidoJSON = request.getParameter("registroSolidoBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_SOLIDO;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));			
			RegistroSolidoBean registroSolidoBean = JsonUtil.convertirCadenaJsonAObjeto(registroSolidoJSON,RegistroSolidoBean.class);
			registroSolidoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroSolidoBean.setIdUsuaCrea(Util.getUserLoged());
			registroSolidoBean.setDeTermCrea(Util.obtenerTerminal(request));
			registroSolidoBean.setIdUsuaModi(Util.getUserLoged());
			registroSolidoBean.setDeTermModi(Util.obtenerTerminal(request));
			registroSolidoBean.setIdParametro(idParametro);
			registroSolidoBean.setFechaMonitoreo(ParametrosUtil.getFechaActualToDateHour());
			registroSolidoBean.setFechaRegDato(ParametrosUtil.getFechaActualToDateHour());
			registroSolidoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
			registroSolidoBean.setFlagAprobador(flagValidador);
			registroSolidoBean.setDescripcionObservacion(" ");			
			
			if(registroSolidoBean.getIdSolido() != null) {
				registroSolidoService.actualizarRegistroSolido(registroSolidoBean);
				idRegistroLab = registroSolidoBean.getIdRegistroLaboratorio();
			}else {
				registroSolidoService.registrarRegistroSolido(registroSolidoBean);
				idRegistroLab = registroSolidoBean.getIdRegistroLaboratorio();
			}
			registroSolidoService.actualizarEstadoRegistroLaboratorioSolido(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGSOLIDO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroSolido = request.getParameter("idRegistroSolido");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroSolidoBean bean = new RegistroSolidoBean();
			bean.setIdSolido(Integer.parseInt(idRegistroSolido));
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			List<RegistroSolidoBean> lta = registroSolidoService.obtenerRegistroSolidoByidSolido(Integer.parseInt(idRegistroSolido),null,null,null);
			Integer idLab = null;
			if(lta != null && lta.size() > 0) {
				idLab = lta.get(0).getIdRegistroLaboratorio();
			}
			registroSolidoService.anularRegistroSolido(bean);		
			registroSolidoService.actualizarEstadoRegistroLaboratorioSolido(idLab,ConstantesUtil.ID_PARAMETRO_SOLIDO,auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n de m??ltiples registros solidos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {			
			String ltaRegistroSolidoJSON = request.getParameter("ltaRegistroSolido");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroSolidoBean>>() {}.getType();
			List<RegistroSolidoBean> ltaRegistroSolido = JsonUtil.convertirCadenaJsonALista(ltaRegistroSolidoJSON, listType);
			Integer idLab = ltaRegistroSolido.get(0).getIdRegistroLaboratorio();
			registroSolidoService.anularMultipleRegistroSolido(ltaRegistroSolido, auditoria);
			registroSolidoService.actualizarEstadoRegistroLaboratorioSolido(idLab,ConstantesUtil.ID_PARAMETRO_DIRECTO, auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n de registros tipo solido
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroSolido" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroSolido(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroSolidoBean registroSolido = new RegistroSolidoBean();
			registroSolido.setIdRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroSolido.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroSolido.setIdUsuaModi(Util.getUserLoged());
			registroSolido.setDeTermModi(Util.obtenerTerminal(request));
			registroSolidoService.aprobarRegistroSolido(registroSolido);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Solido Correctamente.");
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
	 * M??todo que carga la vista de registro DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registro DBO
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroDBO", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroDBO(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			String observacionDBO = " ";
			List<RegistroDBOBean> ltaRegDBO = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			String fechaInicial = ParametrosUtil.getFechaActual();
			String horaActual = ParametrosUtil.getHoraActual();
			Integer diasVencimiento = 0;			
			Integer estadoAprobacionDBO = 0;
			List<DetalleGeneralBean> ltaDetalle = generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_DIAS_VENCIMIENTO);
			if(ltaDetalle != null && ltaDetalle.size() > 0) {
				diasVencimiento = (ltaDetalle.get(0).getVlDato01() != null  && !Strings.isEmpty(ltaDetalle.get(0).getVlDato01()) && !ltaDetalle.get(0).getVlDato01().trim().equals("") && !ltaDetalle.get(0).getVlDato01().equals("") && !Strings.isEmpty(ltaDetalle.get(0).getVlDato01())? Integer.parseInt(ltaDetalle.get(0).getVlDato01().trim()) : null );
			}
			String fechaVencimiento = ParametrosUtil.convertirDateSPString(ParametrosUtil.sumaroRestarTiempoaFecha(ParametrosUtil.getFechaActualToDate(), Calendar.DAY_OF_YEAR, diasVencimiento));
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					estadoAprobacionDBO = beanExtraido.getFlagDBO();
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					fechaInicial = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					fechaVencimiento = ParametrosUtil.convertirDateSPString(ParametrosUtil.sumaroRestarTiempoaFecha(beanExtraido.getFechaRegistro(), Calendar.DAY_OF_YEAR, diasVencimiento));
					observacionDBO = beanExtraido.getObservacionDBO();
				}
				ltaRegDBO = registroDBOService.obtenerRegistroDBOByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegDBO != null && ltaRegDBO.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
//					fechaInicial = ParametrosUtil.convertirDateSPString(ltaRegDBO.get(0).getFechaInicial());
					fechaVencimiento = ParametrosUtil.convertirDateSPString(ltaRegDBO.get(0).getFechaVencimiento());
//	        		cabecera.setHoraMuestraString(ParametrosUtil.convertTimestampToStringFormato(cabecera.getFechaHoraMuestra(),ConstantesParametros.FORMATO_24_HORAS_STRING));
	        		fechaInicial = ParametrosUtil.convertTimestampToStringFormato(ltaRegDBO.get(0).getFechaInicial(), ConstantesUtil.FORMATO_DD_MM_YYYY_DATE);
	        		horaActual = ParametrosUtil.convertTimestampToStringFormato(ltaRegDBO.get(0).getFechaInicial(), ConstantesUtil.FORMATO_24_HORAS_STRING);				
				}else {
					ltaRegDBO = registroDBOService.obtenerRegistroDBOByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegDBO = registroDBOService.obtenerRegistroDBOByPtarSubP(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			
			model.addAttribute("horaActual", horaActual);
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionDBO", estadoAprobacionDBO);
			model.addAttribute("diasVencimiento", diasVencimiento);
			model.addAttribute("fechaInicial", fechaInicial);
			model.addAttribute("fechaVencimiento", fechaVencimiento);
			model.addAttribute("ltaRegDBO", ltaRegDBO);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaPuntoMuestraTotal", generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_PUNTO_MUESTRA));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroDBOService.obtenerRegistroDBOSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaVariableGeneral", variableService.obtenerLtaVariable(Integer.parseInt(idParametro)));			
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionDBO", observacionDBO);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroDBO";
	}	
	
	/**
	 * M??todo que realiza el registro de objetos tipo DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroDBO(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegDBOJSON = request.getParameter("listaRegistroDBO");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroDBO = request.getParameter("observacionRegistroDBO");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO;
			Type listType = new TypeToken<List<RegistroDBOBean>>() {}.getType();
			System.out.println(ltaRegDBOJSON);
			List<RegistroDBOBean> ltaRegistroDBO = JsonUtil.convertirCadenaJsonALista(ltaRegDBOJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroDBO(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroDBO, auditoria, Integer.parseInt(flagValidador), observacionRegistroDBO);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroDBO(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroDBO, auditoria, Integer.parseInt(flagValidador), observacionRegistroDBO);
			}
//			inicio obs
//			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());			
//			establecerMejorValor(registroFinal.getIdRegistroLaboratorio());
//			fin obs
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGDBO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_DBO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionDBO", registroFinal.getObservacionDBO());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionDBO", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionDBO(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionDBO = request.getParameter("observacionRegistroDBO");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionDBO(observacionDBO);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionDBO", observacionDBO);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros DBO
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro DBO
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroDBO(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroDBOBean> ListaResultado = new ArrayList<RegistroDBOBean>();
			if(!Strings.isEmpty(idRegistroLabBusqueda)) {
				ListaResultado = registroDBOService.obtenerRegistroDBOByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			}
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de un objeto tipo registro DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroDBO(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String listaRegistroDBOJSON = request.getParameter("listaRegistroDBOBean");	
			String flagValidador = request.getParameter("flagValidador");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO;
			Integer idRegistroLab = null;
			BaseSptarBean auditoria = new BaseSptarBean();
			System.out.println(listaRegistroDBOJSON);
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroDBOBean>>() {}.getType();
			List<RegistroDBOBean> ltaRegistroDBO = JsonUtil.convertirCadenaJsonALista(listaRegistroDBOJSON, listType);
			idRegistroLab =  registroDBOService.grabarListaItemRegistroDBO(ltaRegistroDBO, auditoria, flagValidador);
//			inicio obs
//			establecerMejorValor(idRegistroLab);
//			fin obs
			registroDBOService.actualizarEstadoRegistroLaboratorioDBO(idRegistroLab,idParametro,auditoria);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGDBO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroDBO(HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idGrupoRegistroDBO = request.getParameter("idGrupoRegistro");
			String listaRegistroDBOJSON = request.getParameter("listaRegistroDBO");
			String flagValidador = request.getParameter("flagValidador");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroDBOBean bean = new RegistroDBOBean();
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroDBOBean>>() {}.getType();
			List<RegistroDBOBean> ltaRegistroDBO = JsonUtil.convertirCadenaJsonALista(listaRegistroDBOJSON, listType);
			Integer idLab = null;
			if(ltaRegistroDBO != null && ltaRegistroDBO.size() > 0) {
				idLab =  registroDBOService.grabarListaItemRegistroDBO(ltaRegistroDBO, auditoria, flagValidador);
			}
			registroDBOService.anularRegistroDBO(Integer.parseInt(idGrupoRegistroDBO), idLab, auditoria);		
			registroDBOService.actualizarEstadoRegistroLaboratorioDBO(idLab,ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO,auditoria);
//			inicio obs
//			establecerMejorValor(idLab);
//			fin obs
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la eliminaci??n de m??ltiples registros DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarVariosItemRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarVariosItemRegistroDBO(HttpServletRequest request, Model model) throws GmdException{
		try {		
			String listaGrupoRegistroDBO = request.getParameter("listaGrupoRegistro");
			String listaRegistroDBOJSON = request.getParameter("listaRegistroDBO");
			String flagValidador = request.getParameter("flagValidador");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroDBOBean bean = new RegistroDBOBean();
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			Type listType = new TypeToken<List<RegistroDBOBean>>() {}.getType();
			List<RegistroDBOBean> ltaRegistroDBO = JsonUtil.convertirCadenaJsonALista(listaRegistroDBOJSON, listType);
			Integer idLab = null;
			if(ltaRegistroDBO != null && ltaRegistroDBO.size() > 0) {
				idLab =  registroDBOService.grabarListaItemRegistroDBO(ltaRegistroDBO, auditoria, flagValidador);
			}
			registroDBOService.anularMultipleRegistroDBO(JsonUtil.convertirCadenaJsonAObjeto(listaGrupoRegistroDBO, Integer[].class), idLab, auditoria);		
			registroDBOService.actualizarEstadoRegistroLaboratorioDBO(idLab,ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO,auditoria);
//			inicio obs
//			establecerMejorValor(idLab);
//			fin obs
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Eliminaron los Registros.");
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
	 * M??todo que realiza la aprobaci??n de un registro DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroDBO(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroDBOBean registroDBO = new RegistroDBOBean();
			registroDBO.setIdRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroDBO.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroDBO.setIdUsuaModi(Util.getUserLoged());
			registroDBO.setDeTermModi(Util.obtenerTerminal(request));
			registroDBOService.aprobarRegistroBDO(registroDBO);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro DBO Correctamente.");
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
	 * M??todo que realiza la actualizaci??n de fechas en los registros DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/actualizarFechasRegistroDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  actualizarFechasRegistroDBO(HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String fechaInicial = request.getParameter("fechaInicial");
			String fechaVencimiento = request.getParameter("fechaVencimiento");
			RegistroDBOBean registroDBOBean = new RegistroDBOBean();
			registroDBOBean.setIdRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));	
//			registroDBOBean.setFechaInicial(ParametrosUtil.convertirStringtoDateSP(fechaInicial));
			registroDBOBean.setFechaInicial(ParametrosUtil.convertStringToTimestamp(fechaInicial, ConstantesUtil.FORMATO_24_HORAS_DATE));
			registroDBOBean.setFechaVencimiento(ParametrosUtil.convertirStringtoDateSP(fechaVencimiento));			
			registroDBOBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroDBOBean.setIdUsuaModi(Util.getUserLoged());
			registroDBOBean.setDeTermModi(Util.obtenerTerminal(request));
			registroDBOService.actualizarFechasRegistroDBO(registroDBOBean);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza que limpia el flag mejor valor de las semillas y registra 
	 * los mejores valores en todos los par??metros del registro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	public void establecerMejorValor(Integer idRegistroLaboratorio) throws GmdException{
		try {
			List<RegistroDBOBean> ltaRegDBO = null;
			ltaRegDBO = registroDBOService.obtenerRegistroDBOByRegLaboratorio(idRegistroLaboratorio);
			registroDBOService.actualizarMejorValorInicial(ltaRegDBO);
			ltaRegDBO = registroDBOService.obtenerRegistroDBOByRegLaboratorio(idRegistroLaboratorio);
			registroDBOService.actualizarMejorValor(ltaRegDBO);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			LOGGER.error(error[1], excepcion);
		}
	}
	
	/**
	 * M??todo que construye el mensaje de comparaci??n entre parametros de entrada y salida
	 * de DBO y Directo
	 * 
	 * @param request
	 * @param model
	 * @return Objetos tipo String con los mensajes de salida
	 * @throws GmdException
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/obtenerMensajeValoresDBO_Directo" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  obtenerMensajeValoresDBO_Directo(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistro");
			List<RegistroLabReporteBean> listaComparativoDBO = registroDBOService.obtenerMejorRegistroDBOES(Integer.parseInt(idRegistroLaboratorio));
			List<RegistroLabReporteBean> listaComparativoDirecto = registroDirectoService.obtenerMejorRegistroDirectoES(Integer.parseInt(idRegistroLaboratorio));
			String mensajeEntrada = "";
			String mensajeSalida = "";
			Integer flagMuestraMensaje = 0;
			if(listaComparativoDBO.size() > 0 && listaComparativoDirecto.size() > 0) {
				Double valorEntradaDBO = null;
				Double valorSalidaDBO = null;
				Double valorEntradaDirecto = null;
				Double valorSalidaDirecto = null;
				//Asignaci??n valores DBO
				for(RegistroLabReporteBean registroDBO : listaComparativoDBO) {
//					if(registroDBO.getIdPtoMuestra() == ConstantesUtil.ID_SUBPARA_EP) {
//						valorEntradaDBO = registroDBO.getNumValor();
//					}
//					if(registroDBO.getIdPtoMuestra() == ConstantesUtil.ID_SUBPARA_SP) {
//						valorSalidaDBO = registroDBO.getNumValor();
//					}
					if(registroDBO.getDescripcionTipoRegistro().equals(ConstantesUtil.TIPO_EP)) {
						valorEntradaDBO = registroDBO.getNumValor();
					}
					if(registroDBO.getDescripcionTipoRegistro().equals(ConstantesUtil.TIPO_SP)) {
						valorSalidaDBO = registroDBO.getNumValor();
					}
				}
				//Asignaci??n valores Directo
				for(RegistroLabReporteBean registroDirecto : listaComparativoDirecto) {
//					if(registroDirecto.getIdPtoMuestra() == ConstantesUtil.ID_SUBPARA_EP) {
//						valorEntradaDirecto = registroDirecto.getNumValor();
//					}
//					if(registroDirecto.getIdPtoMuestra() == ConstantesUtil.ID_SUBPARA_SP) {
//						valorSalidaDirecto = registroDirecto.getNumValor();
//					}
					if(registroDirecto.getDescripcionTipoRegistro().equals(ConstantesUtil.TIPO_EP)) {
						valorEntradaDirecto = registroDirecto.getNumValor();
					}
					if(registroDirecto.getDescripcionTipoRegistro().equals(ConstantesUtil.TIPO_SP)) {
						valorSalidaDirecto = registroDirecto.getNumValor();
					}
				}
				//Creaci??n mensaje entrada
				if(valorEntradaDBO != null && valorEntradaDirecto != null && valorEntradaDBO > valorEntradaDirecto) {
					mensajeEntrada = "El Punto de Muestra de tipo EP con Valor " + valorEntradaDBO.toString() + "del Parametro DBO5 Total es mayor que el resultado promedio DQO con valor " + valorEntradaDirecto.toString()
						+ " del par??metro Directo del tipo EP.";
					flagMuestraMensaje = 1;
				}
				
				if(valorSalidaDBO != null && valorSalidaDirecto != null && valorSalidaDBO > valorSalidaDirecto) {
					mensajeSalida = "El Punto de Muestra de tipo SP con Valor " + valorSalidaDBO.toString() + " del Parametro DBO5 Total es mayor que el resultado promedio DQO con valor " + valorSalidaDirecto.toString()
						+ " del par??metro Directo del tipo SP.";
					flagMuestraMensaje = 1;
				}
			}
			mapResultado.put("mensajeEntrada", mensajeEntrada);
			mapResultado.put("mensajeSalida", mensajeSalida);
			mapResultado.put("flagMuestraMensaje", String.valueOf(flagMuestraMensaje));
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
	
	/**
	 * M??todo que carga la vista de registro microbiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registro microbiol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroMicrobiologico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroMicrobiologico(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			String idTipoMicrobiologico = request.getParameter("idTipoMicrobiologico");
			if(StringUtils.isEmpty(idTipoMicrobiologico)) {
				idTipoMicrobiologico = ""+ConstantesUtil.ID_COLIFORME_TERMOTOLERANTES;
			}
			List<RegistroMicrobiologicoBean> ltaRegMicrobiologico = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionMicrobiologico= 0;
			String observacionMicrobiologico = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionMicrobiologico = beanExtraido.getFlagMicrobiologico();
					observacionMicrobiologico = beanExtraido.getObservacionMicrobiologico();
				}
				ltaRegMicrobiologico = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(ltaRegMicrobiologico != null && ltaRegMicrobiologico.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegMicrobiologico = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegMicrobiologico = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionMicrobiologico", estadoAprobacionMicrobiologico);
			model.addAttribute("ltaRegMicrobiologico", ltaRegMicrobiologico);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaDilucionInicial", detalleGeneralService.obtenerDetalleGeneralI(ConstantesUtil.ID_DILUCION_INICIAL));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idRegistroLaboratorio",idRegistroLaboratorio);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("idTipoMicrobiologico",idTipoMicrobiologico);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroMicrobiologicoService.obtenerRegistroMicrobiologicoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaTipoMicrobiologico",subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionMicrobiologico", observacionMicrobiologico);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroMicrobiologico";
	}
	
	/**
	 * M??todo que carga la vista de registro microbiol??gico secundario (otros)
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de registro microbiol??gico secundario (otros)
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaRegistroMicrobiologicoSecundario", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaRegistroMicrobiologicoSecundario(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			String idParametro = request.getParameter("idParametro");
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionSector = request.getParameter("descripcionSector");
			String idTipoMicrobiologico = request.getParameter("idTipoMicrobiologico");
			List<RegistroMicrobiologicoBean> ltaRegMicrobiologico = null;
			String modo = "";
			String fechaActual = ParametrosUtil.getFechaActual();
			Integer estadoAprobacionMicrobiologico= 0;
			String observacionMicrobiologico = " ";
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				RegistroLaboratorioBean beanExtraido = registroLaboratorioService.obtenerRegistroLaboratorio(Integer.parseInt(idRegistroLaboratorio));
				if(beanExtraido != null) {
					fechaActual = ParametrosUtil.convertirDateSPString(beanExtraido.getFechaRegistro());
					estadoAprobacionMicrobiologico = beanExtraido.getFlagMicrobiologico();
					observacionMicrobiologico = beanExtraido.getObservacionMicrobiologico();
				}
				ltaRegMicrobiologico = registroMicrobiologicoSecundarioService.obtenerRegistroMicrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idTipoMicrobiologico));
				if(ltaRegMicrobiologico != null && ltaRegMicrobiologico.size() > 0) {
					modo = ConstanteServices.ESTADO_OPCION_EDITAR;
				}else {
					ltaRegMicrobiologico = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByPtarPuntoMuestraSecundario(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
					modo = ConstanteServices.ESTADO_OPCION_NUEVA;
				}				
			}else {
				ltaRegMicrobiologico = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByPtarPuntoMuestraSecundario(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro));
				modo = ConstanteServices.ESTADO_OPCION_NUEVA;
			}
			model.addAttribute("modo", modo);
			model.addAttribute("estadoAprobacionMicrobiologico", estadoAprobacionMicrobiologico);
			model.addAttribute("ltaRegMicrobiologico", ltaRegMicrobiologico);
			model.addAttribute("ltaAnalista", analistaService.obtenerAnalista());
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametroMicroSecundario(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaDilucionInicial", detalleGeneralService.obtenerDetalleGeneralI(ConstantesUtil.ID_DILUCION_INICIAL));
			model.addAttribute("idPtarSector",idPtarSector);
			model.addAttribute("idParametro",idParametro);
			model.addAttribute("idRegistroLaboratorio",idRegistroLaboratorio);
			model.addAttribute("idTipoMicrobiologico",idTipoMicrobiologico);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionSector",descripcionSector);
			model.addAttribute("fechaActual",fechaActual);
			model.addAttribute("ltaSubParametrosTodo", registroMicrobiologicoService.obtenerRegistroMicrobiologicoSubParametros(Integer.parseInt(idPtarSector), Integer.parseInt(idParametro)));
			model.addAttribute("ltaTipoMicrobiologico",subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(Integer.parseInt(idPtarSector),Integer.parseInt(idParametro)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("observacionMicrobiologico", observacionMicrobiologico);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/registroMicrobiologicoSecundario";
	}
	
	/**
	 * M??todo que realiza el registro de objetos tipo microbiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroMicrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroMicrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegMicrobiologicoJSON = request.getParameter("listaRegistroMicrobiologico");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroMicrobiologico = request.getParameter("observacionRegistroMicrobiologico");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO;
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroMicrobiologico(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroMicrobiologico, auditoria, Integer.parseInt(flagValidador), analistaBean, observacionRegistroMicrobiologico);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroMicrobiologico(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroMicrobiologico, auditoria, Integer.parseInt(flagValidador), analistaBean, observacionRegistroMicrobiologico);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGMICROBIOLOGICO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_MICRO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionMicrobiologico", registroFinal.getObservacionMicrobiologico());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la actualizaci??n de la observaci??n del par??metro Microbiologico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Resultado con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarObservacionMicrobiologico", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String guardarObservacionMicrobiologico(HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idRegistroLaboratorio = Integer.parseInt(request.getParameter("idRegistroLaboratorio"));
			String observacionMicrobiologico = request.getParameter("observacionRegistroMicrobiologico");
			RegistroLaboratorioBean registro = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
			registro.setDeTermCrea(Util.obtenerTerminal(request));
			registro.setIdUsuaModi(Util.getUserLoged());
			registro.setDeTermModi(Util.obtenerTerminal(request));
			registro.setObservacionMicrobiologico(observacionMicrobiologico);
			registroLaboratorioService.actualizarRegistroLaboratorio(registro);
			mapResultado.put("observacionMicrobiologico", observacionMicrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se actualiz?? la observaci??n correctamente");
			respuestaBean.setParametros(mapResultado);
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la b??squeda de registros microbiol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo registro microbiol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroMicrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroMicrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			List<RegistroMicrobiologicoBean> ListaResultado = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de un objeto tipo registro microbiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroMicrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroMicrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologicoBean");
			String flagValidador = request.getParameter("flagValidador");
			String usuarioLogueado = Util.getUserLoged();
			BaseSptarBean auditoria = new BaseSptarBean();
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO;
			Integer idRegistroLab  = null;
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));	
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			RegistroMicrobiologicoBean registroPadre = ltaRegistroMicrobiologico.get(0);
			if(registroPadre.getIdRegMicrobiologico()!=null && !registroPadre.getIdRegMicrobiologico().equals("")){
				registroMicrobiologicoService.eliminarGrupoRegistroMicrobiologico(registroPadre.getIdRegLaboratorio(), registroPadre.getGrupo(), auditoria);
				for(RegistroMicrobiologicoBean registroMicrobiologico:ltaRegistroMicrobiologico) {
					registroMicrobiologico.setIdRegMicrobiologico(null);
				}
			}
			idRegistroLab =  registroMicrobiologicoService.grabarListaItemRegistroMicrobiologico(ltaRegistroMicrobiologico, auditoria, usuarioLogueado, flagValidador);
			registroMicrobiologicoService.actualizarEstadoRegistroLaboratorioMicrobiologico(idRegistroLab,idParametro,auditoria);
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGMICROBIOLOGICO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza el registro de flag mejor valor en los objetos de tipo microbiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarMejoresValores" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarMejoresValores(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologicoBean");
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			registroMicrobiologicoService.actualizarMejorValorListaMicrobiologico(ltaRegistroMicrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Actualizaron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro microbiol??gico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroMicrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroMicrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			String usuarioLogueado = Util.getUserLoged();
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			registroMicrobiologicoService.anularListaItemRegistroMicrobiologico(ltaRegMicrobiologico, auditoria, usuarioLogueado);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la b??squeda de tubos de diluci??n por cadena
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo tubo de diluci??n 
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarTuboDilucionByCadena", method = RequestMethod.POST)
	public @ResponseBody String buscarTuboDilucionByCadena(HttpServletRequest request, Model model) throws GmdException {
		String valorCombinacion = request.getParameter("valorCombinacion");
		String numExponente = request.getParameter("exponente");
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<TuboDilucionBean> lista = tuboDilucionService.obtenerTuboDilucionByCadena(String.valueOf(valorCombinacion), Integer.parseInt(numExponente));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * M??todo que realiza la aprobaci??n de registros microbiol??gicos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroMicrobiologico" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroMicrobiologico(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroMicrobiologicoBean registroMicrobiologico = new RegistroMicrobiologicoBean();
			registroMicrobiologico.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroMicrobiologico.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroMicrobiologico.setIdUsuaModi(Util.getUserLoged());
			registroMicrobiologico.setDeTermModi(Util.obtenerTerminal(request));
			registroMicrobiologicoService.aprobarRegistroMicrobiologico(registroMicrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Microbiologico Correctamente.");
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
	 * M??todo que realiza el registro de objetos tipo microbiol??gico secundario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarDatosRegistroMicrobiologicoSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarDatosRegistroMicrobiologicoSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		AnalistaBean analistaBean = Util.obtenerAnalistaBeanLogin();
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			String ltaRegMicrobiologicoJSON = request.getParameter("listaRegistroMicrobiologico");	
			String idPtarxSector = request.getParameter("idPtarxSector");
			String flagValidador = request.getParameter("flagValidador");
			String fechaRegistro = request.getParameter("fechaRegistro");
			String observacionRegistroMicrobiologico = request.getParameter("observacionRegistroMicrobiologico");
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO;
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			RegistroLaboratorioBean registroFinal = null;
			if(idRegistroLaboratorio != null && !idRegistroLaboratorio.equals("")) {
				registroFinal = registroLaboratorioService.registrarListaRegistroMicrobiologicoSecundario(Integer.parseInt(idRegistroLaboratorio), Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroMicrobiologico, auditoria, Integer.parseInt(flagValidador), analistaBean, observacionRegistroMicrobiologico);
			}else {
				registroFinal = registroLaboratorioService.registrarListaRegistroMicrobiologicoSecundario(null, Integer.parseInt(idPtarxSector), idParametro, fechaRegistro ,ltaRegistroMicrobiologico, auditoria, Integer.parseInt(flagValidador), analistaBean, observacionRegistroMicrobiologico);
			}	
			registroLaboratorioService.asignarFechaDBO(registroFinal.getIdRegistroLaboratorio());
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGMICROBIOLOGICO, registroFinal.getIdRegistroLaboratorio(), request.getSession().getAttribute("userAnalistaBean").toString());
			String mensajeRespuesta = alertasService.obtenerMensajeAlerta(ConstantesUtil.ID_MENSAJE_REGISTRO_MICRO);
			respuestaBean.setMensajeRespuesta(mensajeRespuesta);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			mapResultado.put("idRegistroLaboratorio", registroFinal.getIdRegistroLaboratorio());
			mapResultado.put("observacionMicrobiologico", registroFinal.getObservacionMicrobiologico());
			mapResultado.put("modo", ConstanteServices.ESTADO_OPCION_EDITAR);
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
	 * M??todo que realiza la b??squeda de registros microbiol??gicos secundarios
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo microbiol??gico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarDatosRegistroMicrobiologicoSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  buscarDatosRegistroMicrobiologicoSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String idRegistroLabBusqueda = request.getParameter("idRegistroLabBusqueda");
			String idTipoMicrobiologico = request.getParameter("idTipoMicrobiologico");
			List<RegistroMicrobiologicoBean> ListaResultado = registroMicrobiologicoSecundarioService.obtenerRegistroMicrobiologicoByRegLaboratorio(Integer.parseInt(idRegistroLabBusqueda), Integer.parseInt(idTipoMicrobiologico));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
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
	
	/**
	 * M??todo que realiza el registro de objetos tipo microbiol??gico secundario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemRegistroMicrobiologicoSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemRegistroMicrobiologicoSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologicoBean");
			String flagValidador = request.getParameter("flagValidador");
			String usuarioLogueado = Util.getUserLoged();
			BaseSptarBean auditoria = new BaseSptarBean();
			Integer idParametro = ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO;
			Integer idRegistroLab  = null;
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));	
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			RegistroMicrobiologicoBean registroPadre = ltaRegistroMicrobiologico.get(0);
			if(registroPadre.getIdRegMicrobiologico()!=null && !registroPadre.getIdRegMicrobiologico().equals("")){
				registroMicrobiologicoSecundarioService.eliminarGrupoRegistroMicrobiologico(registroPadre.getIdRegLaboratorio(), registroPadre.getGrupo(), auditoria, registroPadre.getIdSubParametro());
				for(RegistroMicrobiologicoBean registroMicrobiologico:ltaRegistroMicrobiologico) {
					registroMicrobiologico.setIdRegMicrobiologico(null);
				}
			}
			idRegistroLab =  registroMicrobiologicoSecundarioService.grabarListaItemRegistroMicrobiologico(ltaRegistroMicrobiologico, auditoria, usuarioLogueado, flagValidador);
			registroMicrobiologicoSecundarioService.actualizarEstadoRegistroLaboratorioMicrobiologico(idRegistroLab,idParametro,auditoria, registroPadre.getIdSubParametro());
			registroLaboratorioService.asignarFechaDBO(idRegistroLab);
			enviarNotificacion(ConstantesUtil.ID_NOTIFICACION_GUARDAR_REGMICROBIOLOGICO, idRegistroLab, request.getSession().getAttribute("userAnalistaBean").toString());
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza el registro de flag mejor valor en los objetos tipo microbiologico secundario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarMejoresValoresSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarMejoresValoresSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologicoBean");
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			registroMicrobiologicoSecundarioService.actualizarMejorValorListaMicrobiologico(ltaRegistroMicrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Actualizaron los Datos Correctamente.");
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
	 * M??todo que realiza la eliminaci??n de un registro microbiologico secundario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemRegistroMicrobiologicoSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemRegistroMicrobiologicoSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String ltaRegMicrobiologicoJSON = request.getParameter("ltaRegMicrobiologico");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			String usuarioLogueado = Util.getUserLoged();
			Type listType = new TypeToken<List<RegistroMicrobiologicoBean>>() {}.getType();
			List<RegistroMicrobiologicoBean> ltaRegMicrobiologico = JsonUtil.convertirCadenaJsonALista(ltaRegMicrobiologicoJSON, listType);
			registroMicrobiologicoSecundarioService.anularListaItemRegistroMicrobiologico(ltaRegMicrobiologico, auditoria, usuarioLogueado);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
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
	 * M??todo que realiza la aprobaci??n de un registro microbiol??gico secundario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroMicrobiologicoSecundario" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroMicrobiologicoSecundario(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");			
			RegistroMicrobiologicoBean registroMicrobiologico = new RegistroMicrobiologicoBean();
			registroMicrobiologico.setIdRegLaboratorio(Integer.parseInt(idRegistroLaboratorio));
			registroMicrobiologico.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroMicrobiologico.setIdUsuaModi(Util.getUserLoged());
			registroMicrobiologico.setDeTermModi(Util.obtenerTerminal(request));
			registroMicrobiologicoSecundarioService.aprobarRegistroMicrobiologico(registroMicrobiologico);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Aprob?? el Registro Microbiologico Correctamente.");
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
	 * M??todo que obtiene la descripci??n del estado del registro por par??metro
	 * 
	 * @param mapAux lista map con los es
	 * @param key
	 * @return Objeto tipo String con la descripci??n del estado del par??metro
	 */
	public String verDescripcionEstadoRegistro(Map<String, String> mapAux,String key) {
		String descripcion = "";
		if(mapAux.get(key).equals(""+ConstantesUtil.FLAG_PARAMETRO_ESTADO_NO_EXISTE)) {
			descripcion = "No Aplica";
		}else if(mapAux.get(key).equals(""+ConstantesUtil.FLAG_PARAMETRO_PEND_INGRESO)) {
			descripcion = "Pendiente de Ingreso";
		}else if(mapAux.get(key).equals(""+ConstantesUtil.FLAG_PARAMETRO_PEND_APROB)) {
			descripcion = "Pendiente de Aprobaci??n";
		}else if(mapAux.get(key).equals(""+ConstantesUtil.FLAG_PARAMETRO_ESTADO_APROBADO)) {
			descripcion = "Aprobado";
		}else {
			descripcion = "No Aplica";
		}
		return descripcion;
	}
		
	/**
	 * M??todo que genera los datos para el reporte de laboratorio 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws GmdException
	 */
	@RequestMapping(value = "/reporteRegistroLaboratorio", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody void reporteRegistroLaboratorio(HttpServletRequest request, HttpServletResponse response, Model model) throws GmdException {
		try {
			String rutaJasper = this.getClass().getResource("/reportes/").getPath()+PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "ruta.reporteRegLab");
			String nombreArchivo = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "nombre.reporteRegLab");
			String listaRegistrosLaboratorio = request.getParameter("listaRegistrosLaboratorio");
			listaRegistrosLaboratorio = ParametrosUtil.formatearAUTF8(listaRegistrosLaboratorio);			
			Type type = new TypeToken<List<HashMap<String, String>>>() {}.getType();					
			List<Map<String, String>> lista = JsonUtil.convertirCadenaJsonALista(listaRegistrosLaboratorio, type);
			if(lista != null && lista.size() > 0) {
				if(!CollectionUtils.isEmpty(lista)) {	
					List<BPdf> listaPDFBean = new ArrayList<BPdf>();		
					for(Map<String, String> mapAux : lista) {
						final Map<String,Object> parametrosGeneral = new HashMap<String,Object>();
						Integer idRegistroLaboratorio = Integer.parseInt(mapAux.get("idRegistroLaboratorio"));
						mapAux.put("desFlagDirecto",verDescripcionEstadoRegistro(mapAux,"descripFlagDirecto"));
						mapAux.put("desFlagDBO",verDescripcionEstadoRegistro(mapAux,"descripFlagDBO"));
						mapAux.put("desFlagSolido",verDescripcionEstadoRegistro(mapAux,"descripFlagSolido"));
						mapAux.put("desFlagAceite",verDescripcionEstadoRegistro(mapAux,"descripFlagAceite"));
						mapAux.put("desFlagHidrobiologico",verDescripcionEstadoRegistro(mapAux,"descripFlagHidrobiologico"));
						mapAux.put("desFlagMicrobiologico",verDescripcionEstadoRegistro(mapAux,"descripFlagMicrobiologico"));
						mapAux.put("desFlagParasitologico",verDescripcionEstadoRegistro(mapAux,"descripFlagParasitologico"));						
						
						List<RegistroDirectoBean> ltaRegistroDirecto = registroDirectoService.obtenerRegistroDirectoByRegLaboratorio(idRegistroLaboratorio);						
						List<RegistroDBOBean> ltaRegistroDBO = registroDBOService.obtenerRegistroDBOByRegLaboratorio(idRegistroLaboratorio);
						List<RegistroSolidoBean> ltaRegistroSolido = registroSolidoService.obtenerRegistroSolidoByRegLaboratorio(idRegistroLaboratorio, null);
						List<RegistroAceiteBean>  ltaRegistroAceite = registroAceiteService.obtenerRegistroAceiteByRegLaboratorio(idRegistroLaboratorio);
						List<RegistroHidrobiologicoBean> ltaRegistroHidro = registroHidrobiologicoService.obtenerRegistroHidrobiologicoByRegLaboratorio(idRegistroLaboratorio);
						List<RegistroMicrobiologicoBean> ltaRegistroMicro = registroMicrobiologicoService.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegistroLaboratorio);
						List<RegistroMicrobiologicoBean> ltaRegistroMicroSalmonella = registroMicrobiologicoSecundarioService.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegistroLaboratorio,ConstantesUtil.ID_SALMONELLA);
						List<RegistroMicrobiologicoBean> ltaRegistroMicroPseunoma = registroMicrobiologicoSecundarioService.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegistroLaboratorio,ConstantesUtil.ID_PSEUDOMONA);
						List<RegistroMicrobiologicoBean> ltaRegistroMicroVidrio = registroMicrobiologicoSecundarioService.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegistroLaboratorio,ConstantesUtil.ID_VIDRIO_CHOLERAE);
												
						List<RegistroParasitologicoBean> ltaRegistroPara = registroParasitologicoService.obtenerRegistroParasitologicoByRegLaboratorio(idRegistroLaboratorio);

						List<Map<String, String>> ltaRegistroDirectoMap = JsonUtil.convertirObjetoaMapString(ltaRegistroDirecto);
						List<Map<String, String>> ltaRegistroDBOMap = JsonUtil.convertirObjetoaMapString(ltaRegistroDBO);
						List<Map<String, String>> ltaRegistroSolidoMap = JsonUtil.convertirObjetoaMapString(ltaRegistroSolido);
						List<Map<String, String>> ltaRegistroAceiteMap = JsonUtil.convertirObjetoaMapString(ltaRegistroAceite);
						List<Map<String, String>> ltaRegistroHidroMap = JsonUtil.convertirObjetoaMapString(ltaRegistroHidro);
						List<Map<String, String>> ltaRegistroMicroMap = JsonUtil.convertirObjetoaMapString(ltaRegistroMicro);
						List<Map<String, String>> ltaRegistroMicroSalmonellaMap = JsonUtil.convertirObjetoaMapString(ltaRegistroMicroSalmonella);
						List<Map<String, String>> ltaRegistroMicroPseunomaMap = JsonUtil.convertirObjetoaMapString(ltaRegistroMicroPseunoma);
						List<Map<String, String>> ltaRegistroMicroVidrioMap = JsonUtil.convertirObjetoaMapString(ltaRegistroMicroVidrio);
						List<Map<String, String>> ltaRegistroParaMap = JsonUtil.convertirObjetoaMapString(ltaRegistroPara);
						
						parametrosGeneral.put("ltaRegistroDirectoMap", ltaRegistroDirectoMap);
						parametrosGeneral.put("ltaRegistroDBOMap", ltaRegistroDBOMap);
						parametrosGeneral.put("ltaRegistroSolidoMap", ltaRegistroSolidoMap);
						parametrosGeneral.put("ltaRegistroAceiteMap", ltaRegistroAceiteMap);
						parametrosGeneral.put("ltaRegistroHidroMap", ltaRegistroHidroMap);
						parametrosGeneral.put("ltaRegistroMicroMap", ltaRegistroMicroMap);
						parametrosGeneral.put("ltaRegistroMicroSalmonellaMap", ltaRegistroMicroSalmonellaMap);
						parametrosGeneral.put("ltaRegistroMicroPseunomaMap", ltaRegistroMicroPseunomaMap);
						parametrosGeneral.put("ltaRegistroMicroVidrioMap", ltaRegistroMicroVidrioMap);
						parametrosGeneral.put("ltaRegistroParaMap", ltaRegistroParaMap);
						parametrosGeneral.putAll(mapAux);
						BPdf bPdfBean = new BPdf();
						bPdfBean.setParametro(parametrosGeneral);
						bPdfBean.setRutaJasper(rutaJasper);
						bPdfBean.setListaDataSource(null);
						listaPDFBean.add(bPdfBean);						
					}
					ExportWebUtil.exportarVariasPaginasPdf(response, listaPDFBean, nombreArchivo);
				}
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
	 * M??todo que realiza el env??o de notificaci??n por correo
	 * 
	 * @param idAlertasNotificacion identificador del registro de notificaci??n
	 * @param idRegistroLaboratorio identificador del registro de laboratorio
	 * @param nombreAnalista nombre de analista
	 * @throws GmdException
	 */
	private void enviarNotificacion(Integer idAlertasNotificacion, Integer idRegistroLaboratorio, String nombreAnalista) throws GmdException{
        try {
        	DetalleGeneralBean datosCorreo = generalController.obtenerParametrosCorreo();
        	if(Integer.valueOf(datosCorreo.getVlDato06()) == ConstantesUtil.FLAG_ENVIO_CORREO) {
        		String fechaActual = ParametrosUtil.getFechaActual();
                List<NotificacionAnalistaBean> listarCorreos = alertasNotificacionService.obtenerLtaAnalistasVinculados(idAlertasNotificacion);
                RegistroLaboratorioBean registroFinal = registroLaboratorioService.obtenerRegistroLaboratorio(idRegistroLaboratorio);
                AlertasNotificacionBean notificacion = alertasNotificacionService.buscarAlertasNotificacionHTML(idAlertasNotificacion);
//        		inicio adecuacion sedmail
//        		obtencion de parametro de tipo de envio
        		DetalleGeneralBean parametroEnvio = detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_ENVIO_CORREO).get(0);
//        		common parameter mail SMTP
        		ParametrosCorreo correoParam = new ParametrosCorreo();
        		correoParam.setAsunto(notificacion.getDescripcionNotificacion());
        		correoParam.setRemitenteCorreo(datosCorreo.getVlDato03());
        		correoParam.setRemitenteClave(datosCorreo.getVlDato04());
        		correoParam.setRemitente(datosCorreo.getVlDato02());
//        		parametros de autenticacion de rest sedmail
        		String usuario = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "sedmail.user");
        		String password = PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "sedmail.password");
        		switch (parametroEnvio.getIdDetalleGeneral()) {
//        			envio por metodo SMTP
        			case ConstantesUtil.ID_TIPO_ENVIO_SMTP:
        				for(NotificacionAnalistaBean correos : listarCorreos){
                            if(correos.getCorreo() != null && !correos.getCorreo().equals("")){
                            	List<String> destinatarios = new ArrayList<String>();
                            	destinatarios.add(correos.getCorreo());
                                String bodyConvertido = notificacion.getHtmlMensajeLong();
                                bodyConvertido = bodyConvertido.replace("%VAR1%", correos.getNombreCompleto());
                                bodyConvertido = bodyConvertido.replace("%VAR2%", String.valueOf(idRegistroLaboratorio));
                        		bodyConvertido = bodyConvertido.replace("%VAR4%", fechaActual);
                        		bodyConvertido = bodyConvertido.replace("%VAR5%", nombreAnalista);
                        		bodyConvertido = bodyConvertido.replace("%VAR6%", String.valueOf(registroFinal.getDescripcionPtar()));
                        		correoParam.setIdTipoEnvio(ConstantesUtil.ID_REST_SMTP);
                        		correoParam.setCuerpoHTML(bodyConvertido);
                        		correoParam.setDestinatario(destinatarios);
                        		ResponseBean response = mailtub.enviarCorreo(correoParam, usuario, password);
                        		LOGGER.info(response.getEstadoRespuesta());
                        		LOGGER.info(response.getMensajeRespuesta());
                        		if(response.getEstadoRespuesta().equals(ConstantesCliente.RESULTADO_ERROR)) {
                        			throw new GmdException(response.getMensajeRespuesta());
                        		}
                            }
                        }
        				break;
//        			envio por office 365
        			case ConstantesUtil.ID_TIPO_ENVIO_OFF365:
//        				common parameter mail OFF365
        				ConfiguracionCorreo configCorreoEnvios = configCorreoService.obtenerConfigCorreo(Constants.TIPO_CORREO_ENVIOS, Constants.ESTADO_ACTIVO);
        				correoParam.setUserOffice365(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
        				correoParam.setPassOffice365(configCorreoEnvios.getCuentaCorreo().getStrClave());
        				for(NotificacionAnalistaBean correos : listarCorreos){
                            if(correos.getCorreo() != null && !correos.getCorreo().equals("")){
                            	List<String> destinatarios = new ArrayList<String>();
                            	destinatarios.add(correos.getCorreo());
                                String bodyConvertido = notificacion.getHtmlMensajeLong();
                                bodyConvertido = bodyConvertido.replace("%VAR1%", correos.getNombreCompleto());
                                bodyConvertido = bodyConvertido.replace("%VAR2%", String.valueOf(idRegistroLaboratorio));
                        		bodyConvertido = bodyConvertido.replace("%VAR4%", fechaActual);
                        		bodyConvertido = bodyConvertido.replace("%VAR5%", nombreAnalista);
                        		bodyConvertido = bodyConvertido.replace("%VAR6%", String.valueOf(registroFinal.getDescripcionPtar()));
                        		correoParam.setIdTipoEnvio(ConstantesUtil.ID_REST_OFF365);
                        		correoParam.setCuerpoHTML(bodyConvertido);
                        		correoParam.setDestinatario(destinatarios);
                        		ResponseBean response = mailtub.enviarCorreo(correoParam, usuario, password);
                        		LOGGER.info(response.getEstadoRespuesta());
                        		LOGGER.info(response.getMensajeRespuesta());
                        		if(response.getEstadoRespuesta().equals(ConstantesCliente.RESULTADO_ERROR)) {
                        			throw new GmdException(response.getMensajeRespuesta());
                        		}
                            }
                        }
        				break;
        		}
//                for(NotificacionAnalistaBean correos : listarCorreos){
//                    if(correos.getCorreo() != null && !correos.getCorreo().equals("")){                	
//                    	List<String> almacenarCorreos = new ArrayList<String>();
//                        almacenarCorreos.add(correos.getCorreo());
//                        Map<String, Object> parametrosCorreo = new HashMap<String, Object>();
//                        String bodyConvertido = notificacion.getHtmlMensajeLong();
//                        bodyConvertido = bodyConvertido.replace("%VAR1%", correos.getNombreCompleto());
//                        bodyConvertido = bodyConvertido.replace("%VAR2%", String.valueOf(idRegistroLaboratorio));
//                		bodyConvertido = bodyConvertido.replace("%VAR4%", fechaActual);
//                		bodyConvertido = bodyConvertido.replace("%VAR5%", nombreAnalista);
//                		bodyConvertido = bodyConvertido.replace("%VAR6%", String.valueOf(registroFinal.getDescripcionPtar()));
//                        parametrosCorreo.put("BODY", bodyConvertido);
//                        parametrosCorreo.put("SUBJECT", notificacion.getDescripcionNotificacion());
//                        parametrosCorreo.put("TO", almacenarCorreos);
//                        parametrosCorreo.put("ID_PLANTILLA", idAlertasNotificacion);
//                        NotificacionUtil.getInstancia(generalController).enviarCorreoElectronico(notificacionService.generarCorreo(parametrosCorreo));
//                    }
//                }
//        		fin adecuacion sedmail
        	}
        }catch(Exception excepcion) {
        	String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
        }
    }
	
	
	/**
	 * M??todo que carga la vista de semillas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo string con la ruta de la vista de semillas
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerSemilla", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaSemilla(HttpServletRequest request, Model model) throws GmdException {
		try {
			String idRegistroLaboratorio = request.getParameter("idRegistroLaboratorio");
			model.addAttribute("valorGGADefecto","4");
			model.addAttribute("ltaValidacionBlank",detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_VALIDACION_BLANK));
			model.addAttribute("ltaValidacionSeeded",detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_VALIDACION_SEEDED));
			model.addAttribute("ltaValidacionGGA",detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_VALIDACION_GGA));
			model.addAttribute("ltaValidacionSamples", detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_VALIDACION_SAMPLES));
			model.addAttribute("ltaSemillaBlankDBO",semillaBlankService.obtenerLtaSemillaBlankDBO(Integer.parseInt(idRegistroLaboratorio)));
			model.addAttribute("ltaSemillaSeededDBO",semillaSeededService.obtenerLtaSemillaSeededTodoDBO(Integer.parseInt(idRegistroLaboratorio)));
			model.addAttribute("ltaSemillaStandardDBO",semillaStandardService.obtenerLtaSemillaStandardTodoDBO(Integer.parseInt(idRegistroLaboratorio)));
			model.addAttribute("ltaSemillaSamplesDBO",semillaSamplesService.obtenerLtaSemillaSamplesTodoDBO(Integer.parseInt(idRegistroLaboratorio)));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("idRegistroLaboratorio", idRegistroLaboratorio);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/registro/verBandejaSemilla";
	}	
	
//	continuar en sesion
	@RequestMapping(value = "/continuarSesion", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String continuarSesion(HttpServletRequest request, HttpServletResponse response, Model model) throws GmdException {
		try {
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Continua en Sesion");			
		} catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
}
