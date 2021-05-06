package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.reflect.TypeToken;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.core.beans.AlertasBean;
import pe.com.sedapal.scr.core.beans.AlertasNotificacionBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.services.IAlertasNotificacionService;
import pe.com.sedapal.scr.core.services.IAlertasService;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;
import pe.com.sedapal.scr.core.services.IParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.IPlantaTratamientoService;
import pe.com.sedapal.scr.core.services.IPtarxSectorService;
import pe.com.sedapal.scr.core.services.IPuntoMuestraPtarSectorService;
import pe.com.sedapal.scr.core.services.IRegistroLaboratorioService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.JsonUtil;


/**
 * @author GMD
 * Controlador de configuración PTAR
 */
@Controller
@RequestMapping(value = "/configuracionSptar")
public class ConfiguracionController {

	private static final Logger LOGGER = Logger.getLogger(MantenimientoController.class);
	
	private BRespuestaBean respuestaBean = new BRespuestaBean();
	
	@Autowired
	private GeneralController generalController;
	
	@Autowired
	private IPtarxSectorService ptarxSectorService;
	
	@Autowired
	private IPuntoMuestraPtarSectorService puntoMuestraPtarSectorService;
	
	@Autowired
	private IParametroPtarSectorService parametroPtarSectorService;
	
	@Autowired
	private IRegistroLaboratorioService registroLaboratorioService;
	
	@Autowired
	IDetalleGeneralService detalleGeneralService;
	
	@Autowired
	IAlertasNotificacionService alertasNotificacionService;
	
	@Autowired
	IAlertasService alertasService;
	
	public ConfiguracionController() {
		
	}
	
	/**
	 * Método que carga la ventana de bandeja de notificaciones
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo String con el nombre de la vista de bandeja de notificaciones
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/cargarVentanaBandejaAlertasNotificacion", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaAlertasNotificacion(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarxSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("filtro", Util.obtenerFiltroContext(request));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/configuracion/bandejaAlertasNotificacion";
	}
	
	
	/**
	 * Método que realiza la búsqueda de notificaciones
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return lista de notificaciones en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/buscarAlertasNotificacion", method = {RequestMethod.POST, RequestMethod.GET} )
	public @ResponseBody String buscarAlertasNotificacion(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			List<AlertasNotificacionBean> lista = alertasNotificacionService.obtenerLtaAlertasNotificacionTodo();
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
	 * Método que carga el modal de notificacion
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo String con el nombre de la vista del modal de notificaciones
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/cargarVentanaVerModalNotificacion", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalNotificacion(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String alertaNotificacionJSON = request.getParameter("mantenimientoBean");
			String descripcionNotificacion = request.getParameter("descripcionNotificacion");
			AlertasNotificacionBean bean = new AlertasNotificacionBean();
			bean  = JsonUtil.convertirCadenaJsonAObjeto(alertaNotificacionJSON, AlertasNotificacionBean.class);
			model.addAttribute("ltaAnalistasVinculados", alertasNotificacionService.obtenerLtaAnalistasVinculados(bean.getIdAlertasNotificacion()));
			model.addAttribute("ltaAnalistasNoVinculados", alertasNotificacionService.obtenerLtaAnalistasNoVinculados(bean.getIdAlertasNotificacion()));
			model.addAttribute("objNotificacion", bean);
			model.addAttribute("modo", modo);
			model.addAttribute("descripcionNotificacion", descripcionNotificacion);
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/configuracion/verModalNotificacion";
	}
	
	/**
	 * Método que obtiene la lista de analistas no vinculados a la notificación
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return lista de analistas no vinculados en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/extraerLtaAnalistasNoVinculados", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String extraerLtaAnalistasNoVinculados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAlertasNotificacion = request.getParameter("idNotificacion");			
			List<AnalistaBean> ListaResultado = alertasNotificacionService.obtenerLtaAnalistasNoVinculados(Integer.parseInt(idAlertasNotificacion));
			mapResultado.put("ListaResultadoAnalistasNoVinculados", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se encontraron Analistas No Vinculados");
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
	 * Método que obtiene la lista de analistas vinculados a la notificación
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return lista de analistas vinculados a la notificación en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/extraerLtaAnalistasVinculados", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String extraerLtaAnalistasVinculados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAlertasNotificacion = request.getParameter("idNotificacion");
			List<NotificacionAnalistaBean> ListaResultado = alertasNotificacionService.obtenerLtaAnalistasVinculados(Integer.parseInt(idAlertasNotificacion));
			mapResultado.put("ListaResultadoAnalistasVinculados", JsonUtil.convertirObjetoACadenaJson(ListaResultado));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se encontraron Analistas Vinculados");
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
	 * Método para registrar un Analista a la notificación
	 * 
	 * @param idRutaProg contexto de la petición http
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/agregarRegistroAnalista" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  agregarRegistroAnalista(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAlertasNotificacion = request.getParameter("idNotificacion");
			String idAnalista = request.getParameter("idAnalista");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			alertasNotificacionService.agregarRegistroAnalista(Integer.parseInt(idAlertasNotificacion), Integer.parseInt(idAnalista), auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Asign&oacute; el Analista Correctamente.");
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
	 * Método para eliminar un analista de la notificación
	 * 
	 * @param idRutaProg contexto de la petición http
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/anularRegistroAnalista" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  anularRegistroAnalista(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idNotificacionAnalista = request.getParameter("idNotificacionAnalista");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			alertasNotificacionService.anularRegistroAnalista(Integer.parseInt(idNotificacionAnalista), auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Elimin&oacute; el Analista Correctamente.");
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
	 * Método para eliminar una notificación
	 * 
	 * @param idRutaProg contexto de la petición http
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/anularAlertasNotificacion" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  anularAlertasNotificacion(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAlertasNotificacion = request.getParameter("idAlertasNotificacion");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			alertasNotificacionService.anularAlertasNotificacion(Integer.parseInt(idAlertasNotificacion), auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Elimin&oacute; la Notificaci&oacute;n Correctamente");
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
	 * Método que carga la vista de bandeja de alertas
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo String con el nombre de la vista de la bandeja de alertas
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/cargarVentanaBandejaAlertas", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaAlertas(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarxSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("filtro", Util.obtenerFiltroContext(request));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/configuracion/bandejaAlertas";
	}
	
	/**
	 * Método que busca las alertas existentes
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Lista de alertas en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/buscarAlertas", method = {RequestMethod.POST, RequestMethod.GET} )
	public @ResponseBody String buscarAlertas(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {		
			List<AlertasBean> lista = alertasService.obtenerLtaAlertasTodo();
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
	 * Método que obtiene el mensaje Html de una Alerta
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/obtenerMensaje", method= {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String obtenerMensaje(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {	
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
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
	 * Método que carga la vista del modal de alertas
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo String con el nombre de la vista del modal de alertas
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/cargarVentanaVerModalAlerta", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalAlerta(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String alertaAlertaJSON = request.getParameter("mantenimientoBean");
			String nombreAlerta = request.getParameter("nombreAlerta");
			AlertasBean bean = new AlertasBean();
			bean  = JsonUtil.convertirCadenaJsonAObjeto(alertaAlertaJSON, AlertasBean.class);
			model.addAttribute("objAlerta", bean);
			model.addAttribute("modo", modo);
			model.addAttribute("nombreAlerta", nombreAlerta);
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/configuracion/verModalAlerta";
	}
	
	/**
	 * Método que almacena una nueva alerta
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/grabarAlerta", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String grabarAlerta(HttpServletRequest request, Model model) throws GmdException{
		try {
			String alertaBeanJSON = request.getParameter("alertaBean");
			AlertasBean bean = JsonUtil.convertirCadenaJsonAObjeto(alertaBeanJSON, AlertasBean.class);
			bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			alertasService.actualizarAlertas(bean);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
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
	 * Método que elimina una alerta
	 * 
	 * @param idRutaProg contexto de la petición http
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/anularAlertas" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  anularAlertas(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAlertas = request.getParameter("idAlertas");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			alertasService.anularAlertas(Integer.parseInt(idAlertas), auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Elimin&oacute; la Alerta Correctamente.");
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
}
