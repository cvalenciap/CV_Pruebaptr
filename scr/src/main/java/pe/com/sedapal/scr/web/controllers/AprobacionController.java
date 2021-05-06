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
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
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
 * Controlador de aprobación PTAR
 */
@Controller
@RequestMapping(value = "/aprobacionSptar")
public class AprobacionController {

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
	
	public AprobacionController() {
		
	}
		
	/**
	 * Método que carga la vista de aprobación
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo string con la ruta de la vista de aprobación
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaAprobacion", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaAprobacion(HttpServletRequest request, Model model) throws GmdException {
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
		return "/sptar/aprobacion/aprobacion";
	}
	
	/**
	 * Método que realiza la búsqueda de registros de laboratorio
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos de tipo registro de laboratorio
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarRegistroLaboratorio", method = RequestMethod.POST)
	public @ResponseBody String buscarRegistroLaboratorio(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String idEstado = request.getParameter("idEstado");
			String tipoConsulta = request.getParameter("tipoConsulta");
			List<RegistroLaboratorioBean> lista = new ArrayList<RegistroLaboratorioBean>();
			lista = registroLaboratorioService.obtenerLtaRegistroLaboratorio(idPtarxSector, idEstado, fechaInicio, fechaFin, tipoConsulta);
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
	 * Método que realiza la aprobación de un registro de laboratorio
	 * 
	 * @param idRutaProg
	 * @param request
	 * @param model
	 * @return Objeto de tipo respuesta con el mensaje y el estado del método
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarRegistroLaboratorio" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarRegistroLaboratorio(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String ltaRegLaboratorioJSON = request.getParameter("listaRegistroAprobar");
			String idCambioEstado = request.getParameter("idCambioEstado");
			Type listType = new TypeToken<List<RegistroLaboratorioBean>>() {}.getType();
			List<RegistroLaboratorioBean> ltaRegistroLaboratorio = JsonUtil.convertirCadenaJsonALista(ltaRegLaboratorioJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroLaboratorioService.cambiarFlagAprobacion(ltaRegistroLaboratorio, Integer.parseInt(idCambioEstado), auditoria);
			Util.setFiltroContext(request);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Cambi&oacute; el estado Correctamente.");
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
	 * Método que realiza la aprobación de resgistros de laboratorio
	 * 
	 * @param idRutaProg
	 * @param request
	 * @param model
	 * @return Objeto de tipo respuesta con estado y mensaje del método
	 * @throws GmdException
	 */
	@RequestMapping(value = "/aprobarLtaRegistroLaboratorio" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  aprobarLtaRegistroLaboratorio(String idRutaProg, HttpServletRequest request, Model model) throws GmdException{
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String ltaRegLaboratorioJSON = request.getParameter("listaRegistroAprobar");
			String idCambioEstado = request.getParameter("idCambioEstado");
			Type listType = new TypeToken<List<RegistroLaboratorioBean>>() {}.getType();
			List<RegistroLaboratorioBean> ltaRegistroLaboratorio = JsonUtil.convertirCadenaJsonALista(ltaRegLaboratorioJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			registroLaboratorioService.cambiarFlagEstadoAprobacion(ltaRegistroLaboratorio, Integer.parseInt(idCambioEstado), auditoria);
			Util.setFiltroContext(request);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Cambi&oacute; el estado Correctamente.");
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
}
