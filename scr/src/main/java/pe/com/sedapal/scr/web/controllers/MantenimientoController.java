package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.MicroPorSubParametroBean;
import pe.com.sedapal.scr.core.beans.OximetroBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.PlantillaDBOBean;
import pe.com.sedapal.scr.core.beans.PtarxSectorBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.SemillaBlankBean;
import pe.com.sedapal.scr.core.beans.SemillaSamplesBean;
import pe.com.sedapal.scr.core.beans.SemillaSeededBean;
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;
import pe.com.sedapal.scr.core.beans.VariableBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.services.IAnalistaService;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;
import pe.com.sedapal.scr.core.services.IFisicoQuimicoService;
import pe.com.sedapal.scr.core.services.IFormulaService;
import pe.com.sedapal.scr.core.services.IMicroSubParametroService;
import pe.com.sedapal.scr.core.services.IOximetroService;
import pe.com.sedapal.scr.core.services.IParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.IPlantillaDBOService;
import pe.com.sedapal.scr.core.services.IPtarxSectorService;
import pe.com.sedapal.scr.core.services.IPuntoMuestraPtarSectorService;
import pe.com.sedapal.scr.core.services.ISemillaBlankService;
import pe.com.sedapal.scr.core.services.ISemillaSamplesService;
import pe.com.sedapal.scr.core.services.ISemillaSeededService;
import pe.com.sedapal.scr.core.services.ISemillaStandardService;
import pe.com.sedapal.scr.core.services.ISolidoSuspendidoService;
import pe.com.sedapal.scr.core.services.ISubParametroPtarSectorService;
import pe.com.sedapal.scr.core.services.ITuboDilucionService;
import pe.com.sedapal.scr.core.services.IVariableService;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.JsonUtil;
import pe.com.sedapal.scr.web.util.ParametrosUtil;

/**
 * @author GMD
 * Controlador de Mantenimientos PTAR
 */
@Controller
@RequestMapping(value = "/mantenimientoSptar")
public class MantenimientoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Constants.PACKAGE);
	
	
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
	private ISubParametroPtarSectorService subParametroPtarSectorService;
	
	@Autowired
	private IAnalistaService analistaService;
	
	@Autowired
	private IMicroSubParametroService microSubParametroService;
	
	@Autowired
	private ITuboDilucionService  tuboDilucionService;
	
	@Autowired
	private IFisicoQuimicoService  fisicoQuimicoService;
	
	@Autowired
	private ISolidoSuspendidoService  solidoSuspendidoService;
	
	@Autowired
	private IVariableService  variableService;
	
	@Autowired
	private IFormulaService  formulaService;
	
	@Autowired
	private ISemillaBlankService  semillaBlankService;
	
	@Autowired
	private ISemillaSeededService  semillaSeededService;
	
	@Autowired
	private ISemillaStandardService  semillaStandardService;
	
	@Autowired
	private ISemillaSamplesService  semillaSamplesService;
	
	@Autowired
	private IOximetroService OximetroService;
	
	@Autowired
	private IPlantillaDBOService plantillaDBOService;
	
	public MantenimientoController() {
	
	}
	
	@Autowired
	IDetalleGeneralService detalleGeneralService;
	
	/**
	 * M??todo que carga la ventana de bandeja generales
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String de la ruta de la vista de bandeja generales
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaGenerales", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaClientes(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaGeneral",generalController.obtenerListaGeneral());
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
		return "/sptar/mantenimiento/mantenimientoGenerales";
	}
	
	/**
	 * M??todo que realiza la busqueda de detalles generales
	 * 
	 * @param request
	 * @param model
	 * @return lista de detalles generales en formato JSON
	 * @throws GmdException
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/buscarDetalleGeneral", method = RequestMethod.POST)
	public @ResponseBody String buscarDetalleGeneral(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idGeneral = Integer.parseInt(""+request.getParameter("idGeneral"));
			if (idGeneral != null) {
				List<DetalleGeneralBean> lista = generalController.obtenerListaDetalleGeneralTodo(idGeneral);
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo realizar la busqueda.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza la b??squeda de puntos de muestra tipo entrada y salida
	 * 
	 * @param request
	 * @param model
	 * @return lista de puntos de muestra tipo entrada y salida en formato JSON
	 * @throws GmdException
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/buscarPuntosMuestraES", method = RequestMethod.POST)
	public @ResponseBody String buscarPuntosMuestraES(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<DetalleGeneralBean> lista = generalController.obtenerListaPuntoMuestraES(ConstantesUtil.ID_PUNTO_MUESTRA);
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
	 * M??todo que realiza la b??squeda de subparametros por microorganismos
	 * 
	 * @param request
	 * @param model
	 * @return lista de subparametros en formato JSON
	 * @throws GmdException
	 */
	@SuppressWarnings({ "unused" })
	@RequestMapping(value = "/buscarSubParametroMicroorganismo", method = RequestMethod.POST)
	public @ResponseBody String buscarSubParametroMicroorganismo(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idParametro");
			List<SubParametroPtarSectorBean> lista = subParametroPtarSectorService.obtenerListaSubparametroMicro(Integer.parseInt(idParametro));
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
	 * M??todo que realiza la anulaci??n de un detalle general
	 * 
	 * @param request
	 * @param model
	 * @return Map de resupuesta con estado y mensaje
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularMantenimiento", method = RequestMethod.POST)
	public @ResponseBody String anularMantenimiento(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idDetalleGeneral = request.getParameter("idDetalleGeneral");
			if (!StringUtils.isEmpty(idDetalleGeneral)) {
				DetalleGeneralBean detalleGeneralBean = new DetalleGeneralBean();
				detalleGeneralBean.setIdDetalleGeneral(Integer.parseInt(idDetalleGeneral));
				detalleGeneralBean.setIdUsuaModi(Util.getUserLoged());
				detalleGeneralBean.setDeTermModi(Util.obtenerTerminal(request));
				detalleGeneralBean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				detalleGeneralService.anularDetalleGeneral(detalleGeneralBean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza el registro de un detalle general
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarMantenimiento", method = RequestMethod.POST)
	public @ResponseBody String grabarMantenimiento(HttpServletRequest request, Model model) throws GmdException {
		try {
			String detalleGeneralJson = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			Boolean flag = true;
			DetalleGeneralBean detalleGeneralBean = JsonUtil.convertirCadenaJsonAObjeto(detalleGeneralJson,DetalleGeneralBean.class);
			flag = validarDescripcionDetalleGeneral(detalleGeneralBean, modo);
			if(flag) {
				if(modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
					detalleGeneralBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					detalleGeneralBean.setIdUsuaCrea(Util.getUserLoged());
					detalleGeneralBean.setDeTermCrea(Util.obtenerTerminal(request));
					detalleGeneralService.registrarDetalleGeneral(detalleGeneralBean);
					respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				}else if(modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
					detalleGeneralBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					detalleGeneralBean.setIdUsuaModi(Util.getUserLoged());
					detalleGeneralBean.setDeTermModi(Util.obtenerTerminal(request));
					detalleGeneralService.actualizarDetalleGeneral(detalleGeneralBean);
					respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				}
			}else {
				respuestaBean.setMensajeRespuesta("La descripci&oacute;n del Registro no puede ser duplicada");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	public Boolean validarDescripcionDetalleGeneral(DetalleGeneralBean detalleGeneralBean, String estado) throws GmdException{
		Boolean flag = true;
		try {
			List<DetalleGeneralBean> listaDetalleGeneral = detalleGeneralService.obtenerDetalleGeneralTodo(detalleGeneralBean.getIdGeneral());
			for(DetalleGeneralBean registro : listaDetalleGeneral) {
				if(estado.equals(ConstantesUtil.ESTADO_OPCION_NUEVA) && (registro.getDescripcion().trim().equals(detalleGeneralBean.getDescripcion().trim()) || registro.getDescripcionCorta().trim().equals(detalleGeneralBean.getDescripcionCorta().trim()))) {
					flag = false;
					break;
				} else if(estado.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
					if(registro.getDescripcion().trim().equals(detalleGeneralBean.getDescripcion().trim()) || registro.getDescripcionCorta().trim().equals(detalleGeneralBean.getDescripcionCorta().trim())){
						if(registro.getIdDetalleGeneral().intValue() != detalleGeneralBean.getIdDetalleGeneral().intValue()) {
							flag = false;
							break;
						}
					}	
				}
			}
		}catch(Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			LOGGER.error(error[1], excepcion);
		}
		return flag;
	}
	
	/**
	 * M??todo que carga la vista de mantenimiento general
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String de ruta de la vista mantenimiento general
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaMantenimientoGeneral", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaMantenimientoGeneral(HttpServletRequest request, Model model) throws GmdException {
		try {

			String modo = request.getParameter("modo");
			String titulo = request.getParameter("tituloMantenimiento");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			String idGeneral = request.getParameter("idGeneral");
			model.addAttribute("modo", modo);
			model.addAttribute("titulo",titulo);
			model.addAttribute("idGeneral",idGeneral);
			Util.setFiltroContext(request);
			DetalleGeneralBean detalleGeneralBean = new DetalleGeneralBean();

			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				detalleGeneralBean = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, DetalleGeneralBean.class);
				model.addAttribute("idDetalle", detalleGeneralBean.getIdDetalleGeneral());
			} else {
				detalleGeneralBean.setIdGeneral(Integer.parseInt(idGeneral));
				model.addAttribute("idDetalle", "-1");
			}
			model.addAttribute("objDetalleGeneral", detalleGeneralBean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/mantenimientoGeneral";
	}
	
	/**
	 * M??todo que carga la ventana de bandeja de Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de bandeja de Ptar
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaPtarxSector", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaPtarxSector(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/bandejaPtarPorSector";
	}
	
	/**
	 * M??todo que realiza la busqueda de Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos Ptar en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarPtarxSector", method = RequestMethod.POST)
	public @ResponseBody String buscarPtarxSector(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<PtarxSectorBean> lista = ptarxSectorService.obtenerPtarxSectorTodo();
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
	 * M??todo que realiza la eliminaci??n de un Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del resultado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularPtarxSector", method = RequestMethod.POST)
	public @ResponseBody String anularPtarxSector(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			if (!StringUtils.isEmpty(idPtarxSector)) {
				PtarxSectorBean bean = new PtarxSectorBean();
				bean.setIdPtarxsector(Integer.parseInt(idPtarxSector));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				ptarxSectorService.anularPtarxSector(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza el registro de un Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y el mensaje del resultado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarPtarxSector", method = RequestMethod.POST)
	public @ResponseBody String grabarPtarxSector(HttpServletRequest request, Model model) throws GmdException {
		try {
			String PtarxSectorJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			PtarxSectorBean bean = JsonUtil.convertirCadenaJsonAObjeto(PtarxSectorJSON,PtarxSectorBean.class);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				Integer indicador = ptarxSectorService.validaPtarxSector(bean);
				if(indicador == 0) {
					ptarxSectorService.registrarPtarxSector(bean);
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
					respuestaBean.setMensajeRespuesta("PTAR POR SECTOR YA SE ENCUENTRA REGISTRADO.");
				}
				
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				Integer indicador = ptarxSectorService.validaPtarxSector(bean);
				if(indicador == 0) {
					ptarxSectorService.actualizarPtarxSector(bean);
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
					respuestaBean.setMensajeRespuesta("PTAR POR SECTOR YA SE ENCUENTRA REGISTRADO.");
				}
				
			}
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
	 * M??todo que carga la vista de Ptar
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista Ptar
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaPtarxSector", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaPtarxSector(HttpServletRequest request, Model model) throws GmdException {
		try {

			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			model.addAttribute("modo", modo);
			Util.setFiltroContext(request);
			PtarxSectorBean bean = new PtarxSectorBean();

			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, PtarxSectorBean.class);
				model.addAttribute("idPtarxSector", bean.getIdPtarxsector());
				model.addAttribute("ltaPlantaTratamiento",generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_PLANTA_TRATAMIENTO));
			} else {
				model.addAttribute("ltaPlantaTratamiento",ptarxSectorService.obtenerNoPtarxSector());
			}
			model.addAttribute("objPtarxSector", bean);
			model.addAttribute("ltaSector",generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_SECTOR));			
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/ptarPorSector";
	}
	
	/**
	 * M??todo que carga la vista de puntos de muestra
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de puntos de muestra
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaPuntoMuestraPorPtar", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaPuntoMuestraPorPtar(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/puntoMuestraPorPtar";
	}
	
	/**
	 * M??todo que realiza la b??squeda de puntos de muestra asignados
	 * 
	 * @param request
	 * @param model
	 * @return lista de objetos Punto de muestra en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarPuntosMuestraAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarPuntosMuestraAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			List<PuntoMuestraPtarSectorBean> lista = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraPtarAsignados(idPtarPorSector,idParametro,idSubParametro);
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
	 * M??todo que realiza la b??squeda de puntos de muestra no asignados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos punto de muestra en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarPuntosMuestraNoAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarPuntosMuestraNoAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			List<PuntoMuestraPtarSectorBean> lista = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraPtarNoAsignados(idPtarPorSector,idParametro,idSubParametro);
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
	 * M??todo que realiza el registro de un punto de muestra
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarPuntoMuestraPtar", method = RequestMethod.POST)
	public @ResponseBody String registrarPuntoMuestraPtar(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaPuntoMuestraxPTARJson = request.getParameter("listaAsignadosPuntoxPtar");
			Integer idPtarxSector = Integer.parseInt(request.getParameter("idPtarxsector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			Type listType = new TypeToken<ArrayList<PuntoMuestraPtarSectorBean>>(){}.getType();			
			List<PuntoMuestraPtarSectorBean> ltaPuntoMuestraxPTAR = JsonUtil.convertirCadenaJsonALista(ltaPuntoMuestraxPTARJson, listType);
			
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaPuntoMuestraxPTAR.size();
			Integer retorno = puntoMuestraPtarSectorService.registrarPuntoMuestraPtar(ltaPuntoMuestraxPTAR, idPtarxSector,idParametro, idSubParametro , auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que realiza el registro de un par??metro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto de tipo respuesta con el mensaje y resultado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarParametroPtarSector", method = RequestMethod.POST)
	public @ResponseBody String registrarParametroPtarSector(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaParametroxPTARJson = request.getParameter("listaAsignadosParametroxPtar");
			Integer idPtarxSector = Integer.parseInt(request.getParameter("idPtarxsector"));
			Type listType = new TypeToken<ArrayList<ParametroPtarSectorBean>>(){}.getType();			
			List<ParametroPtarSectorBean> ltaParametroxPTAR = JsonUtil.convertirCadenaJsonALista(ltaParametroxPTARJson, listType);
			
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaParametroxPTAR.size();
			Integer[] retorno = parametroPtarSectorService.registrarParametroPtarSector(ltaParametroxPTAR, idPtarxSector, auditoria);
			if(indicador == retorno[0]) {
				if(retorno[1] == 1) {
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Existen Sub Parametros Asociados, se hicieron los cambios permitidos");
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
				}
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que realiza la b??squeda de parametros asginados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de par??metros en formato JSON
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
	 * M??todo que realiza la b??squeda de parametros no asignados
	 * 
	 * @param request
	 * @param model
	 * @return lista de objetos tipo parametro en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarParametroNoAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarParametroNoAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			List<ParametroPtarSectorBean> lista = parametroPtarSectorService.obtenerltaParametroPtarSectorNoAsignados(idPtarPorSector);
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
	 * M??todo que carga la vista de par??metros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista par??metros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaParametroPorPtar", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaParametroPorPtar(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/parametroPorPtar";
	}
	
	/**
	 * M??todo que carga la vista de bandeja de analistas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de analistas
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaAnalista", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaAnalista(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/bandejaAnalista";
	}
	
	/**
	 * M??todo que realiza la b??squeda de analistas
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo Analista en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarAnalista", method = RequestMethod.POST)
	public @ResponseBody String buscarAnalista(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<AnalistaBean> lista = analistaService.obtenerAnalistaTodo();
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
	 * M??todo que realiza la eliminaci??n de analistas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y el mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularAnalista", method = RequestMethod.POST)
	public @ResponseBody String anularAnalista(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idAnalista = request.getParameter("idAnalista");
			if (!StringUtils.isEmpty(idAnalista)) {
				AnalistaBean bean = new AnalistaBean();
				bean.setIdAnalista(Integer.parseInt(idAnalista));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				analistaService.anularAnalista(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista de analistas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de analistas
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaAnalista", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaAnalista(HttpServletRequest request, Model model) throws GmdException {
		try {

			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			model.addAttribute("modo", modo);
			Util.setFiltroContext(request);
			AnalistaBean bean = new AnalistaBean();
			List<ParametroPtarSectorBean> ltaParametrosAsignados = new ArrayList<ParametroPtarSectorBean>();
			List<ParametroPtarSectorBean> ltaParametrosNoAsignados = new ArrayList<ParametroPtarSectorBean>();
			
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, AnalistaBean.class);
				model.addAttribute("idAnalista", bean.getIdAnalista());
				ltaParametrosNoAsignados = analistaService.obtenerParametrosxAnalistaNoAsignados(bean.getIdAnalista());
				ltaParametrosAsignados = analistaService.obtenerParametrosxAnalistaAsignados(bean.getIdAnalista());
			}else {
				ltaParametrosNoAsignados = analistaService.obtenerParametrosxAnalistaNoAsignados(0);
			}
			model.addAttribute("objAnalista", bean);
			model.addAttribute("ltaParametrosAsignados", ltaParametrosAsignados);
			model.addAttribute("ltaParametrosNoAsignados", ltaParametrosNoAsignados);
			model.addAttribute("ltaTipoDocumento",generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_TIPO_DOCUMENTO));
			model.addAttribute("ltaTipo",generalController.obtenerListaDetalleGeneral(ConstantesUtil.ID_TIPO));
			model.addAttribute("ltaPtarPorSector",ptarxSectorService.obtenerPtarxSector());			
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/analista";
	}
	
	/**
	 * M??todo que realiza la b??squeda de trabajadores
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo trabajador en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarTrabajador", method = RequestMethod.POST)
	public @ResponseBody String buscarTrabajador(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String numeroFicha = request.getParameter("numeroFicha");
			if(numeroFicha != null && !numeroFicha.equals("") && !StringUtils.isEmpty(numeroFicha)) {
				List<TrabajadorBean> lista = analistaService.obtenerTrabajador(numeroFicha);
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setEstadoRespuesta("No se Encontraron Resultados.");
			}			
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
	 * M??todo que realiza la validaci??n de existencia de analista
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/existeAnalista", method = RequestMethod.POST)
	public @ResponseBody String existeAnalista(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String numeroDocumento = request.getParameter("numeroDocumento");
			String tipoAnalista = request.getParameter("tipoAnalista");
			AnalistaBean bean = new AnalistaBean();
			bean.setNumeroDocumento(numeroDocumento);
			bean.setTipoAnalista(Integer.parseInt(tipoAnalista));
			List<AnalistaBean> lista = analistaService.obtenerAnalistabyFiltro(bean);
			if(lista != null && lista.size() > 0) {
				mapResultado.put("indicador", 1);
			}else {
				mapResultado.put("indicador", 0);
			}			
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
	 * M??todo que realiza la validaci??n de existencia de usuario
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y respuesta del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/existeUsuarioAnalista", method = RequestMethod.POST)
	public @ResponseBody String existeUsuarioAnalista(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String codigoUsuario = request.getParameter("codigoUsuario");
			List<AnalistaBean> lista = analistaService.obtenerAnalistabyUsuario(codigoUsuario);
			if(lista != null && lista.size() > 0) {
				mapResultado.put("indicadorCorreo", 1);
				mapResultado.put("nombreAnalista", lista.get(0).getNombreCompleto());
			}else {
				mapResultado.put("indicadorCorreo", 0);
			}			
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
	 * M??todo que realiza el registro de un analista
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarAnalista", method = RequestMethod.POST)
	public @ResponseBody String grabarAnalista(HttpServletRequest request, Model model) throws GmdException {
		try {
			String analistaBeanJSON = request.getParameter("analistaBean");
			String listaParametrosAsignadosJSON = request.getParameter("listaParametrosAsignados");
			Type listType = new TypeToken<ArrayList<ParametroPtarSectorBean>>(){}.getType();			
			List<ParametroPtarSectorBean> ltaParametrosAsignados = JsonUtil.convertirCadenaJsonALista(listaParametrosAsignadosJSON, listType);
			
			String modo = request.getParameter("modo");
			AnalistaBean bean = JsonUtil.convertirCadenaJsonAObjeto(analistaBeanJSON,AnalistaBean.class);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setFlagRegistrador(ConstantesUtil.FLAG_PENDIENTE_REGISTRAR);
				bean.setFlagAprobador(ConstantesUtil.FLAG_PENDIENTE_APROBAR);
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				bean.setListaParametrosAsignados(ltaParametrosAsignados);
				analistaService.registrarAnalista(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setListaParametrosAsignados(ltaParametrosAsignados);
				//validamos si la combinacion existe
				analistaService.actualizarAnalista(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");				
			}
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
	 * M??todo que carga la vista de subparametros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de subparametros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaSubParametroPorPtar", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaSubParametroPorPtar(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/subParametroPorPtar";
	}
	
	/**
	 * M??todo que realiza la b??squeda de subparametros asignados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo subparametro en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSubParametroAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarSubParametroAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			List<SubParametroPtarSectorBean> lista = subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(idPtarPorSector,idParametro);
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
	 * M??todo que realiza la busqueda de subparametros no asignados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo subparametro en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSubParametroNoAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarSubParametroNoAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			List<SubParametroPtarSectorBean> lista = subParametroPtarSectorService.obtenerltaSubParametroPtarSectorNoAsignados(idPtarPorSector,idParametro);
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
	 * M??todo que realiza el registro de un subparametro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarSubParametroPtarSector", method = RequestMethod.POST)
	public @ResponseBody String registrarSubParametroPtarSector(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaSubParametroxPTARJson = request.getParameter("listaAsignadosSubParametroxPtar");
			Integer idPtarxSector = Integer.parseInt(request.getParameter("idPtarxsector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Type listType = new TypeToken<ArrayList<SubParametroPtarSectorBean>>(){}.getType();			
			List<SubParametroPtarSectorBean> ltaSubParametroxPTAR = JsonUtil.convertirCadenaJsonALista(ltaSubParametroxPTARJson, listType);
			
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaSubParametroxPTAR.size();
			Integer retorno = subParametroPtarSectorService.registrarSubParametroPtarSector(ltaSubParametroxPTAR, idPtarxSector,idParametro, auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que carga la vista de microorganismos por subparametro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista microorganismos por subparametro
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaMicroSubParametro", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaMicroSubParametro(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaSubParametro", subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(null,null));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/microSubParametro";
	}
	
	/**
	 * M??todo que realiza la busqueda de microorganismos por subparametro asignados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo microorganismos en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarMicroSubParametroAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarMicroSubParametroAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			List<MicroPorSubParametroBean> lista = microSubParametroService.obtenerltaMicroPorSubParametroAsignados(idPtarPorSector, idParametro, idSubParametro);
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
	 * M??todo que realiza la b??squeda de microorganismos por subparametro no asignados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de Objetos tipo microorganismo en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarMicroSubParametroNoAsignados", method = RequestMethod.POST)
	public @ResponseBody String buscarMicroSubParametroNoAsignados(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			List<MicroPorSubParametroBean> lista = microSubParametroService.obtenerltaMicroPorSubParametroNoAsignados(idPtarPorSector, idParametro, idSubParametro);
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
	 * M??todo que realiza el registro de microorganismos por subparametro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarMicroSubParametroPtarSector", method = RequestMethod.POST)
	public @ResponseBody String registrarMicroSubParametroPtarSector(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaMicroxPTARJson = request.getParameter("listaAsignadosMicroSubParametro");
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			Integer idParametro = Integer.parseInt(request.getParameter("idParametro"));
			Integer idSubParametro = Integer.parseInt(request.getParameter("idSubParametro"));
			Type listType = new TypeToken<ArrayList<MicroPorSubParametroBean>>(){}.getType();			
			List<MicroPorSubParametroBean> ltaMicroPorSubParametro = JsonUtil.convertirCadenaJsonALista(ltaMicroxPTARJson, listType);
			
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaMicroPorSubParametro.size();
			Integer retorno = microSubParametroService.registrarMicroPorSubParametro(ltaMicroPorSubParametro, idPtarPorSector, idParametro, idSubParametro, auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que carga la vista de bandeja de tubos de diluci??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de bandeja de tubos de diluci??n
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaTuboDilucion", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/bandejaTuboDilucion";
	}	
	
	/**
	 * M??todo que realizala b??squeda de tubos de diluac??n
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo tubo de diluci??n
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarTuboDilucion", method = RequestMethod.POST)
	public @ResponseBody String buscarTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<TuboDilucionBean> lista = tuboDilucionService.obtenerLtaTuboDilucionTodo();
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
	 * M??todo que realiza la eliminaci??n de tubos de diluci??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularTuboDilucion", method = RequestMethod.POST)
	public @ResponseBody String anularTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idTuboDilucion = request.getParameter("idTuboDilucion");
			if (!StringUtils.isEmpty(idTuboDilucion)) {
				TuboDilucionBean bean = new TuboDilucionBean();
				bean.setIdTuboDilucion(Integer.parseInt(idTuboDilucion));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				tuboDilucionService.anularTuboDilucion(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza el registro de tubo de diluci??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarTuboDilucion", method = RequestMethod.POST)
	public @ResponseBody String registrarTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		try {
			String TuboDilucionJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			TuboDilucionBean bean = JsonUtil.convertirCadenaJsonAObjeto(TuboDilucionJSON,TuboDilucionBean.class);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				tuboDilucionService.registrarTuboDilucion(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				tuboDilucionService.actualizarTuboDilucion(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que carga la vista de tubos de diluci??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de tubos de diluci??n
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaTuboDilucion", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			model.addAttribute("modo", modo);
			Util.setFiltroContext(request);
			TuboDilucionBean bean = new TuboDilucionBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, TuboDilucionBean.class);
				model.addAttribute("idTuboDilucion", bean.getIdTuboDilucion());
			} 
			model.addAttribute("objTuboDilucion", bean);		
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/tuboDilucion";
	}
	
	/**
	 * M??todo que realiza el registro de tubos de diluci??n
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarTuboDilucion", method = RequestMethod.POST)
	public @ResponseBody String grabarTuboDilucion(HttpServletRequest request, Model model) throws GmdException {
		try {
			String tuboDilucionBeanJSON = request.getParameter("tuboDilucionBean");
			String modo = request.getParameter("modo");
			TuboDilucionBean bean = JsonUtil.convertirCadenaJsonAObjeto(tuboDilucionBeanJSON,TuboDilucionBean.class);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				tuboDilucionService.registrarTuboDilucion(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				tuboDilucionService.actualizarTuboDilucion(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");				
			}
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
	 * M??todo que carga la vista bandeja de f??sico qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de f??sico qu??mico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaFisicoQuimico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
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
		return "/sptar/mantenimiento/bandejaFisicoQuimico";
	}	
		
	/**
	 * M??todo que realiza la eliminaci??n de f??sico qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularFisicoQuimico", method = RequestMethod.POST)
	public @ResponseBody String anularFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idFisicoQuimico = request.getParameter("idFisicoQuimico");
			String volumen = request.getParameter("volumen");
			String idPuntoMuestra = request.getParameter("idPuntoMuestra");
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			if (!StringUtils.isEmpty(idFisicoQuimico)) {
				FisicoQuimicoBean bean = new FisicoQuimicoBean();
				bean.setIdFisicoQuimico(Integer.parseInt(idFisicoQuimico));
				bean.setnNumVolumen(Double.parseDouble(volumen));
				bean.setIdPuntoMuestra(Integer.parseInt(idPuntoMuestra));
				bean.setIdPtarxSector(Integer.parseInt(idPtarxSector));				
				bean.setFechaInicioString(fechaInicio);
				bean.setFechaFinString(fechaFin);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				fisicoQuimicoService.anularFisicoQuimico(bean);
				//aca llamamos al metodo que calcule
				BaseSptarBean auditoria = new BaseSptarBean();
				auditoria.setIdUsuaModi(Util.getUserLoged());
				auditoria.setDeTermModi(Util.obtenerTerminal(request));
				auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				fisicoQuimicoService.reCalcularRegistrosFisicoQuimico(Integer.parseInt(idPtarxSector),Integer.parseInt(idPuntoMuestra),Double.parseDouble(volumen),auditoria,fechaInicio,fechaFin);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza el registro de f??sico qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarFisicoQuimico", method = RequestMethod.POST)
	public @ResponseBody String registrarFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		try {
			String fisicoQuimicoJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			String volumenOriginal = request.getParameter("volumenOriginal");
			String idPuntoMuestraOriginal = request.getParameter("idPuntoMuestraOriginal");
			FisicoQuimicoBean bean = JsonUtil.convertirCadenaJsonAObjeto(fisicoQuimicoJSON,FisicoQuimicoBean.class);
			Double dbo5 = ((bean.getnNumOdi()-bean.getnNumOdf())*300)/bean.getnNumVolumen();
			Double dilc = (bean.getnNumVolumen() * 100)/300;
			dbo5 = ParametrosUtil.formatearDecimales(dbo5,0);
			dilc = ParametrosUtil.formatearDecimales(dilc,2);
			bean.setnNumDbo5(dbo5);
			bean.setNuDilc(dilc);		
			bean.setNuDeplec(Double.parseDouble("0"));
			bean.setnNumDbo5Prom(Double.parseDouble("0"));
			bean.setFechaInicio(ParametrosUtil.convertirStringtoDateSP(bean.getFechaInicioString()));
			bean.setFechaFin(ParametrosUtil.convertirStringtoDateSP(bean.getFechaFinString()));
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				fisicoQuimicoService.registrarFisicoQuimico(bean);
				fisicoQuimicoService.reCalcularRegistrosFisicoQuimico(bean.getIdPtarxSector(),bean.getIdPuntoMuestra(),bean.getnNumVolumen(),auditoria,bean.getFechaInicioString(),bean.getFechaFinString());
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				fisicoQuimicoService.actualizarFisicoQuimico(bean);
				fisicoQuimicoService.reCalcularRegistrosFisicoQuimico(bean.getIdPtarxSector(),bean.getIdPuntoMuestra(),bean.getnNumVolumen(),auditoria,bean.getFechaInicioString(),bean.getFechaFinString());
				if(bean.getIdPuntoMuestra().intValue() != Integer.parseInt(idPuntoMuestraOriginal) || bean.getnNumVolumen().doubleValue() != Double.parseDouble(volumenOriginal)) {
					fisicoQuimicoService.reCalcularRegistrosFisicoQuimico(bean.getIdPtarxSector(),Integer.parseInt(idPuntoMuestraOriginal),Double.parseDouble(volumenOriginal),auditoria,bean.getFechaInicioString(),bean.getFechaFinString());
				}
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que carga la vista de f??sico qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de f??sico qu??mico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaFisicoQuimico", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			String descripcionPtar = request.getParameter("descripcionPtar");
			model.addAttribute("modo", modo);
			model.addAttribute("idPtarxSector",idPtarxSector);
			model.addAttribute("fechaInicio", fechaInicio);
			model.addAttribute("fechaFin", fechaFin);
			model.addAttribute("descripcionPtar",descripcionPtar);
			Util.setFiltroContext(request);
			FisicoQuimicoBean bean = new FisicoQuimicoBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, FisicoQuimicoBean.class);
				model.addAttribute("idPtarxSector", bean.getIdPtarxSector());
				model.addAttribute("volumenOriginal",bean.getnNumVolumen());
				model.addAttribute("idPuntoMuestraOriginal",bean.getIdPuntoMuestra());
			} else {
				bean.setIdPtarxSector(Integer.parseInt(idPtarxSector));
				model.addAttribute("idPtarxSector", idPtarxSector);
				model.addAttribute("volumenOriginal","");
				model.addAttribute("idPuntoMuestraOriginal","");
			}
			model.addAttribute("objFisicoQuimico", bean);
			model.addAttribute("ltaTipoFisico", subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(Integer.parseInt(idPtarxSector),ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO));
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxPtar(Integer.parseInt(idPtarxSector)));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/fisicoQuimico";
	}
	
	/**
	 * M??todo que realiza la b??squeda de f??sico qu??mico con fechas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarFechasFisicoQuimico", method = RequestMethod.POST)
	public @ResponseBody String buscarFechasFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			if (!StringUtils.isEmpty(idPtarxSector)) {				
				List<DetalleGeneralBean> lista = fisicoQuimicoService.obtenerFechasFisicoQuimicoTodo(Integer.parseInt(idPtarxSector));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<FisicoQuimicoBean> lista = new ArrayList<FisicoQuimicoBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}		
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
	 * M??todo que realiza la b??squeda de f??sico qu??micos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de Objetos f??sico qu??mico en formato JSON
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarFisicoQuimico", method = RequestMethod.POST)
	public @ResponseBody String buscarFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			String fechaInicio = request.getParameter("fechaInicio");
			String fechaFin = request.getParameter("fechaFin");
			if (!StringUtils.isEmpty(idPtarxSector)) {				
				List<FisicoQuimicoBean> lista = fisicoQuimicoService.obtenerLtaFisicoQuimicoTodo(Integer.parseInt(idPtarxSector),fechaInicio,fechaFin);
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<FisicoQuimicoBean> lista = new ArrayList<FisicoQuimicoBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}		
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
	 * M??todo que carga la vista bandeja de solidos suspendidos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de solidos suspendidos
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaSolidoSuspendido", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaSolidoSuspendido(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarxSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("ltaTipoSolido", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_TIPO_SOLIDO));
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
		return "/sptar/mantenimiento/bandejaSolidoSuspendido";
	}
	
	/**
	 * M??todo que realiza la b??esqueda de solidos suspendidos
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo solidos suspendidos
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSolidoSuspendido", method = RequestMethod.POST)
	public @ResponseBody String buscarSolidoSuspendido(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarxSector = request.getParameter("idPtarxSector");
			String idTipoSolido = request.getParameter("idTipoSolido");
			if (!StringUtils.isEmpty(idPtarxSector)) {
				List<SolidoSuspendidoBean> lista = solidoSuspendidoService.obtenerLtaSolidoTodo(Integer.parseInt(idPtarxSector), Integer.parseInt(idTipoSolido));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<SolidoSuspendidoBean> lista = new ArrayList<SolidoSuspendidoBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
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
	 * M??todo que realiza la eliminac??n de solidos suspendidos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularSolidoSuspendido", method = RequestMethod.POST)
	public @ResponseBody String anularSolidoSuspendido(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idSolidoSuspendido = request.getParameter("idSolidoSuspendido");
			if (!StringUtils.isEmpty(idSolidoSuspendido)) {
				SolidoSuspendidoBean bean = new SolidoSuspendidoBean();
				bean.setIdSolidoSuspendido(Integer.parseInt(idSolidoSuspendido));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				solidoSuspendidoService.anularSolidoSuspendido(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista de f??sico qu??mico
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista de f??sico qu??mico
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaSolidoSuspendido", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaSolidoSuspendido(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			String idPtarxSector = request.getParameter("idPtarxSector");
			String idTipoSolido = request.getParameter("idTipoSolido");
			String descripcionPtar = request.getParameter("descripcionPtar");
			String descripcionTipoSolido = request.getParameter("descripcionTipoSolido");
			model.addAttribute("modo", modo);
			model.addAttribute("idPtarxSector",idPtarxSector);
			model.addAttribute("idTipoSolido",idTipoSolido);
			model.addAttribute("descripcionPtar",descripcionPtar);
			model.addAttribute("descripcionTipoSolido",descripcionTipoSolido);
			Util.setFiltroContext(request);
			SolidoSuspendidoBean bean = new SolidoSuspendidoBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, SolidoSuspendidoBean.class);
				model.addAttribute("idPtarxSector", bean.getIdPtarxSector());
				model.addAttribute("idTipoSolido", bean.getIdTipoSolido());
				model.addAttribute("volumenOriginal",bean.getNuPesoFiltrado());
				model.addAttribute("idPuntoMuestraOriginal",bean.getIdPuntoMuestra());
			} else {
				bean.setIdPtarxSector(Integer.parseInt(idPtarxSector));
				bean.setIdTipoSolido(Integer.parseInt(idTipoSolido));
				model.addAttribute("idPtarxSector", idPtarxSector);
				model.addAttribute("idTipoSolido", idTipoSolido);
				model.addAttribute("volumenOriginal","");
				model.addAttribute("idPuntoMuestraOriginal","");
			}
			model.addAttribute("objSolidoSuspendido", bean);
			model.addAttribute("ltaPuntoMuestra", puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxPtar(Integer.parseInt(idPtarxSector)));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/solidoSuspendido";
	}
	
	/**
	 * M??todo que realiza el registro de solidos suspendidos
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarSolidoSuspendido", method = RequestMethod.POST)
	public @ResponseBody String registrarSolidoSuspendido(HttpServletRequest request, Model model) throws GmdException {
		try {
			String solidoSuspendidoJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			String volumenOriginal = request.getParameter("volumenOriginal");
			String idPuntoMuestraOriginal = request.getParameter("idPuntoMuestraOriginal");
			SolidoSuspendidoBean bean = JsonUtil.convertirCadenaJsonAObjeto(solidoSuspendidoJSON,SolidoSuspendidoBean.class);
			Double ss = ((bean.getNuPesoFinal()-bean.getNuPesoInicial())*1000000)/bean.getNuPesoFiltrado();
			Double ssv = ((bean.getNuPesoFinal()-bean.getNuPesoCalcina())*1000000)/bean.getNuPesoFiltrado();
			Double ssf = ss-ssv;
			ss = ParametrosUtil.formatearDecimales(ss,0);
			ssv = ParametrosUtil.formatearDecimales(ssv,0);
			ssf = ParametrosUtil.formatearDecimales(ssf,0);
			bean.setnNumSS(ss);
			bean.setnNumSSV(ssv);
			bean.setnNumSSF(ssf);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				solidoSuspendidoService.registrarSolidoSuspendido(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				solidoSuspendidoService.actualizarSolidoSuspendido(bean);
				if(bean.getIdPuntoMuestra().intValue() != Integer.parseInt(idPuntoMuestraOriginal) || bean.getNuPesoFiltrado().doubleValue() != Double.parseDouble(volumenOriginal)) {
				}
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que carga la vista bandeja de variables
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de variables
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaVariables", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaVariables(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaParametro", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_PARAMETRO_LABORATORIO));
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
		return "/sptar/mantenimiento/bandejaVariables";
	}
	
	/**
	 * M??todo que realiza la b??squeda de variables
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarVariable", method = RequestMethod.POST)
	public @ResponseBody String buscarVariable(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idDetalleGeneral");
			if (!StringUtils.isEmpty(idParametro)) {
				List<VariableBean> lista = variableService.obtenerVariableTodo(Integer.parseInt(idParametro));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<VariableBean> lista = new ArrayList<VariableBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
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
	 * M??todo que realiza la b??squeda de variables activas
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo variables
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarVariableActivo", method = RequestMethod.POST)
	public @ResponseBody String buscarVariableActivo(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idDetalleGeneral");
			if (!StringUtils.isEmpty(idParametro)) {
				List<VariableBean> lista = variableService.obtenerLtaVariable(Integer.parseInt(idParametro));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<VariableBean> lista = new ArrayList<VariableBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
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
	 * M??todo que realiza la eliminaci??n de variables
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularVariable", method = RequestMethod.POST)
	public @ResponseBody String anularVariable(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idVariable = request.getParameter("idVariable");
			if (!StringUtils.isEmpty(idVariable)) {
				VariableBean bean = new VariableBean();
				bean.setIdVariable(Integer.parseInt(idVariable));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				variableService.anularVariable(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza el registro de variables
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Repuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
 	@RequestMapping(value = "/grabarVariable", method = RequestMethod.POST)
	public @ResponseBody String grabarVariable(HttpServletRequest request, Model model) throws GmdException {
		try {
			String VariableJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			VariableBean bean = JsonUtil.convertirCadenaJsonAObjeto(VariableJSON,VariableBean.class);
			Boolean flag = true;
			flag = validarDescripcionVariable(bean, modo);
			if(flag) {
				if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
					bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					bean.setIdUsuaCrea(Util.getUserLoged());
					bean.setDeTermCrea(Util.obtenerTerminal(request));
					variableService.registrarVariable(bean);
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");
				} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
					bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					bean.setIdUsuaModi(Util.getUserLoged());
					bean.setDeTermModi(Util.obtenerTerminal(request));	
					variableService.actualizarVariable(bean);
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
				}
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("La Variable no puede tener una letra o descripci??n duplicada");
			}			
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
 	
 	public Boolean validarDescripcionVariable(VariableBean variable, String modo) throws GmdException{
 		Boolean flag = true;
 		try {
 			List<VariableBean> listaVariable = variableService.obtenerVariableTodo(variable.getIdParametro());
 			for(VariableBean variableCompare : listaVariable) {
 				if(modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA) && (variable.getDescripcionLetra().trim().equals(variableCompare.getDescripcionLetra().trim()) || variable.getDescripcionVariable().trim().equals(variableCompare.getDescripcionVariable().trim()))) {
 					flag = false;
 					break;
 				}else if(modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)){
 					if(variable.getDescripcionLetra().trim().equals(variableCompare.getDescripcionLetra().trim()) || variable.getDescripcionVariable().trim().equals(variableCompare.getDescripcionVariable().trim())) {
 						if(variable.getIdVariable().intValue() != variableCompare.getIdVariable().intValue()) {
 							flag = false;
 							break;
 						}
 					}
 				}
 			}
 		}catch(Exception excepcion) {
 			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
 			LOGGER.error(error[1], excepcion);
 		}
 		return flag;
 	}
 	
 	/**
	 * M??todo que carga la vista variable
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista variable
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVariable", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVariable(HttpServletRequest request, Model model) throws GmdException {
		try {	
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			String idParametro = request.getParameter("idParametro");
			String descripcionParametro = request.getParameter("descripcionParametro");
			model.addAttribute("modo", modo);
			Util.setFiltroContext(request);
			VariableBean bean = new VariableBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, VariableBean.class);
				model.addAttribute("idParametro",idParametro);
				model.addAttribute("descripcionParametro",descripcionParametro);
			} else {
				bean.setIdParametro(Integer.parseInt(idParametro));
				model.addAttribute("idParametro", idParametro);
				model.addAttribute("descripcionParametro",descripcionParametro);
			}			
			model.addAttribute("objVariable", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/variable";
	}
	
	/**
	 * M??todo que carga la vista bandeja de f??rmulas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de f??rmulas
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaFormula", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaFormula(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaParametro", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_PARAMETRO_LABORATORIO));
			model.addAttribute("ltaOrden", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_ORDEN_FORMULA));
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
			model.addAttribute("filtro", Util.obtenerFiltroContext(request));
		} catch (Exception excepcion) {
			model.addAttribute("filtro", Util.obtenerFiltroContext(request));
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/bandejaFormula";
	}
	
	/**
	 * M??todo que realiza la b??squeda de f??rmulas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Repuesta con el estado y el mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarFormula", method = RequestMethod.POST)
	public @ResponseBody String buscarFormula(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idDetalleGeneral");
			if (!StringUtils.isEmpty(idParametro)) {
				List<FormulaBean> lista = formulaService.obtenerLtaFormula(Integer.parseInt(idParametro));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<FormulaBean> lista = new ArrayList<FormulaBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
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
	 * M??todo que realiza la elmiminaci??n de f??rmulas
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y el mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularFormula", method = RequestMethod.POST)
	public @ResponseBody String anularFormula(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idFormula = request.getParameter("idFormulaxParametro");
			if (!StringUtils.isEmpty(idFormula)) {
				FormulaBean bean = new FormulaBean();
				bean.setIdFormulaxParametro(Integer.parseInt(idFormula));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				formulaService.anularFormula(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se anul&oacute el Registro.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo anular el registro.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista f??rmula
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista f??rmula
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaFormula", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaFormula(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			String idParametro = request.getParameter("idParametro");
			model.addAttribute("modo", modo);
			model.addAttribute("idParametro",idParametro);
			Util.setFiltroContext(request);
			FormulaBean bean = new FormulaBean();

			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, FormulaBean.class);
				model.addAttribute("idParametro", bean.getIdParametro());
			} else {
				bean.setIdParametro(Integer.parseInt(idParametro));
				model.addAttribute("idParametro", idParametro);
			}
			model.addAttribute("objFormula", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/formula";
	}
	
	/**
	 * M??todo que realiza el registro de f??rmula
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarFormula", method = RequestMethod.POST)
	public @ResponseBody String registrarFormula(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			FormulaBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, FormulaBean.class);
			Util.setFiltroContext(request);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				formulaService.registrarFormula(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				formulaService.actualizarFormula(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que carga la vista modal de copia de registros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista modal de copia de registros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerModalCopiar", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalCopiar(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("fechaActual",ParametrosUtil.getFechaActual());
			model.addAttribute("ltaPtarGeneral", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/verModalCopiar";
	}
	
	/**
	 * M??todo que realiza la copia de registros f??sico qu??mimco
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/copiarDataFisicoQuimico", method = RequestMethod.POST)
	public @ResponseBody String copiarDataFisicoQuimico(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarOrigen = request.getParameter("idPtarOrigen");
			String fechaInicioOrigen = request.getParameter("fechaInicioOrigen");
			String fechaFinOrigen = request.getParameter("fechaFinOrigen");
			String idPtarDestino = request.getParameter("idPtarDestino");
			String fechaInicioDestino = request.getParameter("fechaInicioDestino");
			String fechaFinDestino = request.getParameter("fechaFinDestino");			
			if (!StringUtils.isEmpty(idPtarOrigen)) {
				List<FisicoQuimicoBean> lista = fisicoQuimicoService.obtenerLtaFisicoQuimicoTodo(Integer.parseInt(idPtarOrigen),fechaInicioOrigen,fechaFinOrigen);
				if(lista != null && lista.size() >0) {
					BaseSptarBean auditoria = new BaseSptarBean();
					auditoria.setIdUsuaCrea(Util.getUserLoged());
					auditoria.setDeTermCrea(Util.obtenerTerminal(request));
					auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					fisicoQuimicoService.registarCopiaLtaFisicoQuimicoTodo(lista,Integer.parseInt(idPtarDestino),fechaInicioDestino,fechaFinDestino,auditoria);
					respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
					respuestaBean.setMensajeRespuesta("Informaci??n Copiada Correctamente");
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
					respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_NO_DATA);
				}
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_NO_DATA);
			}
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
	 * M??todo que carga la vista bandeja semilla
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja semilla
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaSemilla", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaSemilla(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("valorGGADefecto","4");
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/bandejaSemilla";
	}
	
	/**
	 * M??todo que realiza la b??squeda de semillas blanks
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo blanks
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSemillaBlanks", method = RequestMethod.POST)
	public @ResponseBody String buscarSemillaBlanks(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SemillaBlankBean> lista = semillaBlankService.obtenerLtaSemillaBlankTodo();
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
	 * M??todo que carga la vista modal blanks
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista modal blanks
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerModalBlanks", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalBlanks(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");			
			model.addAttribute("modo", modo);
			SemillaBlankBean bean = new SemillaBlankBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, SemillaBlankBean.class);
			} 
			model.addAttribute("objSemillaBlanks", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/verModalBlanks";
	}
	
	/**
	 * M??todo que realiza el registro de blanks
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarBlanks", method = RequestMethod.POST)
	public @ResponseBody String grabarBlanks(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			SemillaBlankBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, SemillaBlankBean.class);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaBlankService.registrarSemillaBlank(bean);
				semillaBlankService.actualizarPromedioBlanks(auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaBlankService.actualizarSemillaBlank(bean);
				semillaBlankService.actualizarPromedioBlanks(auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que realiza la eliminaci??n de blanks
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularBlanks", method = RequestMethod.POST)
	public @ResponseBody String anularBlanks(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idBlank = request.getParameter("idBlank");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (!StringUtils.isEmpty(idBlank)) {
				SemillaBlankBean bean = new SemillaBlankBean();		
				bean.setIdBlank(Integer.parseInt(idBlank));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				semillaBlankService.anularSemillaBlank(bean);
				semillaBlankService.actualizarPromedioBlanks(auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que realiza la b??squeda de semillas seeded
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo seeded 
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSemillaSeeded", method = RequestMethod.POST)
	public @ResponseBody String buscarSemillaSeeded(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SemillaSeededBean> lista = semillaSeededService.obtenerLtaSemillaSeededTodo();
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
	 * M??todo que realiza la b??sqeuda de semillas seeded activas
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo semillas
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSemillaSeededActivas", method = RequestMethod.POST)
	public @ResponseBody String buscarSemillaSeededActivas(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SemillaSeededBean> lista = semillaSeededService.obtenerLtaSemillaSeeded();
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
	 * M??todo que realiza la eliminaci??n de seeded
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Repuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularSeeded", method = RequestMethod.POST)
	public @ResponseBody String anularSeeded(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idSeeded = request.getParameter("idSeeded");
			String valorGGA = request.getParameter("valorGGA");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (!StringUtils.isEmpty(idSeeded)) {
				SemillaSeededBean bean = new SemillaSeededBean();		
				bean.setIdSeeded(Integer.parseInt(idSeeded));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				semillaSeededService.anularSemillaSeeded(bean);
				semillaSeededService.actualizarPromedioSeeded(Double.parseDouble(valorGGA),auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista modal seeded
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista modal seeded
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerModalSeeded", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalSeeded(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");	
			String valorGGA = request.getParameter("valorGGA");
			model.addAttribute("modo", modo);
			SemillaSeededBean bean = new SemillaSeededBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, SemillaSeededBean.class);
			}else {
				bean.setNumValGGa(Double.parseDouble(valorGGA));
			}
			model.addAttribute("objSemillaSeeded", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/verModalSeeded";
	}
	
	/**
	 * M??todo que realiza el registro de Valor GGA
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/guardarValorGGA", method = RequestMethod.POST)
	public @ResponseBody String guardarValorGGA(HttpServletRequest request, Model model) throws GmdException {
		try {
			String valorGGA = request.getParameter("valorGGA");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			semillaSeededService.actualizarPromedioSeeded(Double.parseDouble(valorGGA),auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Valor GGA Correctamente.");
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
	 * M??todo que realiza el registro de seeded
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarSeeded", method = RequestMethod.POST)
	public @ResponseBody String grabarSeeded(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			SemillaSeededBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, SemillaSeededBean.class);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			Double valorGGA = bean.getNumValGGa();
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaSeededService.registrarSemillaSeeded(bean);
				semillaSeededService.actualizarPromedioSeeded(valorGGA,auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaSeededService.actualizarSemillaSeeded(bean);
				semillaSeededService.actualizarPromedioSeeded(valorGGA,auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que realiza la b??squeda de semillas standard
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo ssemillas standard
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSemillaStandard", method = RequestMethod.POST)
	public @ResponseBody String buscarSemillaStandard(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SemillaStandardBean> lista = semillaStandardService.obtenerLtaSemillaStandardTodo();
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
	 * M??todo que realiza la eliminaci??n de standard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularStandard", method = RequestMethod.POST)
	public @ResponseBody String anularStandard(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idStandard = request.getParameter("idStandard");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (!StringUtils.isEmpty(idStandard)) {
				SemillaStandardBean bean = new SemillaStandardBean();		
				bean.setIdStandard(Integer.parseInt(idStandard));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				semillaStandardService.anularSemillaStandard(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista modal standard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista modal standard
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerModalStandard", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalStandard(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");			
			model.addAttribute("modo", modo);
			SemillaStandardBean bean = new SemillaStandardBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, SemillaStandardBean.class);
			} 
			model.addAttribute("objSemillaStandard", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/verModalStandard";
	}
	
	/**
	 * M??todo que realiza el registro de standard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarStandard", method = RequestMethod.POST)
	public @ResponseBody String grabarStandard(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			SemillaStandardBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, SemillaStandardBean.class);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaStandardService.registrarSemillaStandard(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaStandardService.actualizarSemillaStandard(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que realiza los calculos en los registros standard
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/actualizarCalculoStandard", method = RequestMethod.POST)
	public @ResponseBody String actualizarCalculoStandard(HttpServletRequest request, Model model) throws GmdException {
		try {
			String promedioSCF = request.getParameter("promedioSCF");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			semillaStandardService.actualizarInformacionStandard(Double.parseDouble(promedioSCF),auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Valor GGA Correctamente.");
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
	 * M??todo que realiza la b??squeda de semillas samples
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo semillas samples
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarSemillaSamples", method = RequestMethod.POST)
	public @ResponseBody String buscarSemillaSamples(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SemillaSamplesBean> lista = semillaSamplesService.obtenerLtaSemillaSamplesTodo();
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
	 * M??todo que realiza la eliminaci??n de samples
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularSamples", method = RequestMethod.POST)
	public @ResponseBody String anularSamples(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idSamples = request.getParameter("idSamples");
			String promedioSCF = request.getParameter("promedioSCF");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (!StringUtils.isEmpty(idSamples)) {
				SemillaSamplesBean bean = new SemillaSamplesBean();		
				bean.setIdSamples(Integer.parseInt(idSamples));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				semillaSamplesService.anularSemillaSamples(bean);
				semillaSamplesService.actualizarInformacionSamples(Double.parseDouble(promedioSCF), auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista modal samples
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista modal samples
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaVerModalSamples", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalSamples(HttpServletRequest request, Model model) throws GmdException {
		try {
			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");			
			model.addAttribute("modo", modo);
			SemillaSamplesBean bean = new SemillaSamplesBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, SemillaSamplesBean.class);
			} 
			model.addAttribute("objSemillaSamples", bean);
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/verModalSamples";
	}
	
	/**
	 * M??todo que realiza el registro de samples
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarSamples", method = RequestMethod.POST)
	public @ResponseBody String grabarSamples(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			String promedioSCF = request.getParameter("promedioSCF");
			SemillaSamplesBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, SemillaSamplesBean.class);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaSamplesService.registrarSemillaSamples(bean);
				semillaSamplesService.actualizarInformacionSamples(Double.parseDouble(promedioSCF), auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				semillaSamplesService.actualizarSemillaSamples(bean);
				semillaSamplesService.actualizarInformacionSamples(Double.parseDouble(promedioSCF), auditoria);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que realiza la actualizaci??n de calculos en los registros samples
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/actualizarCalculoSamples", method = RequestMethod.POST)
	public @ResponseBody String actualizarCalculoSamples(HttpServletRequest request, Model model) throws GmdException {
		try {
			String promedioSCF = request.getParameter("promedioSCF");
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			semillaSamplesService.actualizarInformacionSamples(Double.parseDouble(promedioSCF),auditoria);
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Valor GGA Correctamente.");
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
	 * M??todo que carga la vista bandeja de ox??metro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista bandeja de ox??metro
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaBandejaOximetro", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaBandejaOximetro(HttpServletRequest request, Model model) throws GmdException {
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
		return "/sptar/mantenimiento/bandejaOximetro";
	}
	
	/**
	 * M??todo que realiza la b??squeda de ox??metros
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo Oximetro 
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarOximetro", method = RequestMethod.POST)
	public @ResponseBody String buscarOximetro(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<OximetroBean> lista = OximetroService.obtenerLtaOximetroTodo();
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
	 * M??todo que realiza la eliminaci??n de ox??metros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/anularOximetro", method = RequestMethod.POST)
	public @ResponseBody String anularOximetro(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idOximetro = request.getParameter("idOximetro");
			if (!StringUtils.isEmpty(idOximetro)) {
				OximetroBean bean = new OximetroBean();
				bean.setIdOximetro(Integer.parseInt(idOximetro));
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				OximetroService.anularOximetro(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios.");
			} else {
				respuestaBean.setMensajeRespuesta("Error, no se pudo Guardar los cambios.");
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			}
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
	 * M??todo que carga la vista oximetro
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista oximetro
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaOximetro", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaOximetro(HttpServletRequest request, Model model) throws GmdException {
		try {

			String modo = request.getParameter("modo");
			String mantenimientoBeanJSON = request.getParameter("mantenimientoBean");
			model.addAttribute("modo", modo);
			Util.setFiltroContext(request);
			OximetroBean bean = new OximetroBean();
			if (!modo.equals(ConstanteServices.ESTADO_OPCION_NUEVA)) {
				bean  = JsonUtil.convertirCadenaJsonAObjeto(mantenimientoBeanJSON, OximetroBean.class);
			}
			model.addAttribute("objOximetro", bean);
			model.addAttribute("ltaTipoOximetro", detalleGeneralService.obtenerDetalleGeneral(ConstantesUtil.ID_TIPO_OXIMETRO));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/Oximetro";
	}
	
	/**
	 * M??todo que realiza el registro de oximetros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarOximetro", method = RequestMethod.POST)
	public @ResponseBody String registrarOximetro(HttpServletRequest request, Model model) throws GmdException {
		try {
			String OximetroJSON = request.getParameter("mantenimientoBean");
			String modo = request.getParameter("modo");
			OximetroBean bean = JsonUtil.convertirCadenaJsonAObjeto(OximetroJSON,OximetroBean.class);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			if(StringUtils.isEmpty(bean.getDescripcionObservacion())){
				bean.setDescripcionObservacion(" ");
			}
			if (modo.equals(ConstantesUtil.ESTADO_OPCION_NUEVA)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				OximetroService.registrarOximetro(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			} else if (modo.equals(ConstantesUtil.ESTADO_OPCION_EDITAR)) {
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaModi(Util.getUserLoged());
				bean.setDeTermModi(Util.obtenerTerminal(request));
				bean.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(bean.getFechaRegistroString()));
				//validamos si la combinacion existe
				OximetroService.actualizarOximetro(bean);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Actualiz&oacute; el Registro Correctamente.");
			}
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
	 * M??todo que carga la vista plantilla DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista plantilla DBO
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaPlantillaDBO", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaPlantillaDBO(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/plantillaDBO";
	}
	
	/**
	 * M??todo que realiza la busqueda de registro de la plantilla DBO
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo plantillaDBO
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarPlantillaDBO", method = RequestMethod.POST)
	public @ResponseBody String buscarPlantillaDBO(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarSector = request.getParameter("idPtarSector");			
			List<PlantillaDBOBean> lista = plantillaDBOService.obtenerRegistroPlantillaDBO(Integer.parseInt(idPtarSector));
			List<PuntoMuestraPtarSectorBean> listaPuntoMuestra = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraxGrupoParametro(Integer.parseInt(idPtarSector),ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO);
			List<SubParametroPtarSectorBean> listaSubParametro = subParametroPtarSectorService.obtenerltaSubParametroPtarSectorAsignados(Integer.parseInt(idPtarSector),ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO);
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			mapResultado.put("ListaPuntoMuestra", JsonUtil.convertirObjetoACadenaJson(listaPuntoMuestra));
			mapResultado.put("ListaSubParametro", JsonUtil.convertirObjetoACadenaJson(listaSubParametro));
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
	 * M??todo que realiza la eliminaci??n de objetos plantillaDBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el mensaje y el estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarItemPlantillaDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  eliminarItemPlantillaDBO(HttpServletRequest request, Model model) throws GmdException{
		try {		
			String idGrupoRegistroDBO = request.getParameter("idGrupoRegistro");
			String idPtarxSector = request.getParameter("idPtarxSector");
			PlantillaDBOBean bean = new PlantillaDBOBean();
			bean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			bean.setIdUsuaModi(Util.getUserLoged());
			bean.setDeTermModi(Util.obtenerTerminal(request));
			bean.setIdGrupo(Integer.parseInt(idGrupoRegistroDBO));
			bean.setIdPtarxSector(Integer.parseInt(idPtarxSector));
			plantillaDBOService.anularPlantillaDBO(bean);		
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
	 * M??todo que realiza el registro de objetos tipo plantilla DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo Respuesta con el mensaje y estado del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarItemPlantillaDBO" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String  grabarItemPlantillaDBO(HttpServletRequest request, Model model) throws GmdException{
		try {		
			String itemPapaJSON = request.getParameter("itemPapa");
			String itemHijoJSON = request.getParameter("itemHijo");
			PlantillaDBOBean beanPapa = JsonUtil.convertirCadenaJsonAObjeto(itemPapaJSON, PlantillaDBOBean.class);
			PlantillaDBOBean beanHijo = JsonUtil.convertirCadenaJsonAObjeto(itemHijoJSON, PlantillaDBOBean.class);
			beanPapa.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			beanPapa.setIdUsuaModi(Util.getUserLoged());
			beanPapa.setDeTermModi(Util.obtenerTerminal(request));
			beanPapa.setIdUsuaCrea(Util.getUserLoged());
			beanPapa.setDeTermCrea(Util.obtenerTerminal(request));
			
			beanHijo.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			beanHijo.setIdUsuaModi(Util.getUserLoged());
			beanHijo.setDeTermModi(Util.obtenerTerminal(request));
			beanHijo.setIdUsuaCrea(Util.getUserLoged());
			beanHijo.setDeTermCrea(Util.obtenerTerminal(request));
			if(beanPapa.getIdPlantillaDBO() != null) {
				plantillaDBOService.actualizarPlantillaDBO(beanPapa);
				plantillaDBOService.actualizarPlantillaDBO(beanHijo);
			}else {
				plantillaDBOService.registrarPlantillaDBO(beanPapa);
				plantillaDBOService.registrarPlantillaDBO(beanHijo);
			}			
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
	 * M??todo que realiza la b??squeda de puntos de muestra faltantes
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo punto de muestra
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarPtoMuestraFalta", method = RequestMethod.POST)
	public @ResponseBody String buscarPtoMuestraFalta(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			List<PuntoMuestraPtarSectorBean> listaFaltante = plantillaDBOService.obtenerltaPuntoMuestraFaltante(Integer.parseInt(idPtarSector));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(listaFaltante));
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
	 * M??todo que realiza el registro objetos tipo plantillaDBO faltantes
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/grabarListaFaltantes", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String grabarListaFaltantes(HttpServletRequest request, Model model) throws GmdException {
		try {		
			String ltaAgregadosJSON = request.getParameter("ltaAgregadosJSON");
			Type listType = new TypeToken<List<PlantillaDBOBean>>() {}.getType();
			List<PlantillaDBOBean> ltaIngresosFaltantes = JsonUtil.convertirCadenaJsonALista(ltaAgregadosJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			plantillaDBOService.registrarListaFaltantes(ltaIngresosFaltantes, auditoria);			
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Registraron los Datos Correctamente.");
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
	 * M??todo que realiza la b??squeda de registros de plantilla DBO con puntos de muestra no asociados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo plantilla DBO
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarRegistrosPlantillaSobra", method = RequestMethod.POST)
	public @ResponseBody String buscarRegistrosPlantillaSobra(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idPtarSector = request.getParameter("idPtarSector");
			List<PlantillaDBOBean> listaRegistrosSobrante = plantillaDBOService.obtenerRegistrosPlantillaSobrante(Integer.parseInt(idPtarSector));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(listaRegistrosSobrante));
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
	 * M??todo que realiza el registro objetos tipo plantillaDBO faltantes
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/eliminarListaSobrantes", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String eliminarListaSobrantes(HttpServletRequest request, Model model) throws GmdException {
		try {		
			String ltaSobrantesJSON = request.getParameter("ltaSobrantesJSON");
			Type listType = new TypeToken<List<PlantillaDBOBean>>() {}.getType();
			List<PlantillaDBOBean> ltaRegistrosSobrantes = JsonUtil.convertirCadenaJsonALista(ltaSobrantesJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			plantillaDBOService.eliminarListaSobrantes(ltaRegistrosSobrantes, auditoria);			
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Eliminaron los Datos Correctamente.");
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
	 * M??todo que realiza la modificacion en los Registros de plantilla DBO
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/modificarAlterados", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String modificarAlterados(HttpServletRequest request, Model model) throws GmdException {
		try {		
			String idPtarSector = request.getParameter("idPtarSector");
			String ltaSobrantesJSON = request.getParameter("ltaSobrantesJSON");
			String ltaAgregadosJSON = request.getParameter("ltaAgregadosJSON");
			Type listType = new TypeToken<List<PlantillaDBOBean>>() {}.getType();
			List<PlantillaDBOBean> ltaRegistrosSobrantes = JsonUtil.convertirCadenaJsonALista(ltaSobrantesJSON, listType);
			List<PlantillaDBOBean> ltaIngresosFaltantes = JsonUtil.convertirCadenaJsonALista(ltaAgregadosJSON, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			plantillaDBOService.modificarRegistrosAlterados(ltaRegistrosSobrantes, ltaIngresosFaltantes, auditoria, Integer.parseInt(idPtarSector));			
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setMensajeRespuesta("Se Eliminaron los Datos Correctamente.");
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
	 * M??todo que carga la vista secuencial reporte
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista secuencial reporte
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaSecuencialReporte", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaSecuencialReporte(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/secuencialReporte";
	}
		
	/**
	 * M??todo que realiza la b??squeda de puntos de muestra con su secuencia
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo punto de muestra
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarlistaPuntosMuestraSecuencial", method = RequestMethod.POST)
	public @ResponseBody String buscarlistaPuntosMuestraSecuencial(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			List<PuntoMuestraPtarSectorBean> lista = puntoMuestraPtarSectorService.obtenerltaPuntoMuestraSecuencial(idPtarPorSector);
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
	 * M??todo que realiza el registro de puntos de muestra con su secuencia
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarPuntoMuestraPtarSecuencial", method = RequestMethod.POST)
	public @ResponseBody String registrarPuntoMuestraPtarSecuencial(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaPuntoMuestraxPTARJson = request.getParameter("listaPuntosMuestraSecuencial");
			Integer idPtarxSector = Integer.parseInt(request.getParameter("idPtarxsector"));
			Type listType = new TypeToken<ArrayList<PuntoMuestraPtarSectorBean>>(){}.getType();			
			List<PuntoMuestraPtarSectorBean> ltaPuntoMuestraxPTAR = JsonUtil.convertirCadenaJsonALista(ltaPuntoMuestraxPTARJson, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaPuntoMuestraxPTAR.size();
			Integer retorno = puntoMuestraPtarSectorService.registrarPuntoMuestraPtarSecuencial(ltaPuntoMuestraxPTAR, idPtarxSector, auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que carga la vista secuencial reporte de subparametros
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista secuencial reporte de subparametros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaSecuencialReporteSP", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaSecuencialReporteSP(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("ltaPtarPorSector", ptarxSectorService.obtenerPtarxSector());
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/secuencialReporteSP";
	}
	
	/**
	 * M??todo que realiza la b??squeda de subparametros con su secuencia
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo subparametro
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarlistaSubParametroSecuencial", method = RequestMethod.POST)
	public @ResponseBody String buscarlistaSubParametroSecuencial(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			Integer idPtarPorSector = Integer.parseInt(request.getParameter("idPtarPorSector"));
			List<SubParametroPtarSectorBean> lista = subParametroPtarSectorService.obtenerltaSubParametroSecuencial(idPtarPorSector);
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
	 * M??todo que realiza el registro de subparametros con su secuencia
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarSubParametroPtarSecuencial", method = RequestMethod.POST)
	public @ResponseBody String registrarSubParametroPtarSecuencial(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaSubParametroPTARJson = request.getParameter("listaSubParametroSecuencial");
			Integer idPtarxSector = Integer.parseInt(request.getParameter("idPtarxsector"));
			Type listType = new TypeToken<ArrayList<SubParametroPtarSectorBean>>(){}.getType();			
			List<SubParametroPtarSectorBean> ltaSubParametroxPTAR = JsonUtil.convertirCadenaJsonALista(ltaSubParametroPTARJson, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaSubParametroxPTAR.size();
			Integer retorno = subParametroPtarSectorService.registrarSubParametroSecuencial(ltaSubParametroxPTAR, idPtarxSector, auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
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
	 * M??todo que carga la vista secuencial de subaparametros para reportes historicos y acumulados
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo String con la ruta de la vista secuencial de subparametros para reporte historicos y acumulados
	 * @throws GmdException
	 */
	@RequestMapping(value = "/cargarVentanaSecSPHistAc", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaSecSPHistAc(HttpServletRequest request, Model model) throws GmdException {
		try {
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return "/sptar/mantenimiento/secuencialReporteSPHistAc";
	}
	
	/**
	 * M??todo que realiza la b??squeda de subparametros con su secuencia para reportes historicos y acumulados
	 * 
	 * @param request
	 * @param model
	 * @return Lista de objetos tipo subparametros
	 * @throws GmdException
	 */
	@RequestMapping(value = "/buscarlistaSubParametroSecHistAc", method = RequestMethod.POST)
	public @ResponseBody String buscarlistaSubParametroSecHistAc(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			List<SubParametroPtarSectorBean> lista = subParametroPtarSectorService.obtenerltaSubParametroSecHistAc();
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
	 * M??todo que realiza el registro de subparametros con su secuencia para historicos y acumulados
	 * 
	 * @param request
	 * @param model
	 * @return Objeto tipo respuesta con el estado y mensaje del m??todo
	 * @throws GmdException
	 */
	@RequestMapping(value = "/registrarSubParametroPtarSecuencialHistAc", method = RequestMethod.POST)
	public @ResponseBody String registrarSubParametroPtarSecuencialHistAc(HttpServletRequest request, Model model) throws GmdException {
		try {
			String ltaSubParametroPTARJson = request.getParameter("listaSubParametroSecuencial");
			Type listType = new TypeToken<ArrayList<SubParametroPtarSectorBean>>(){}.getType();			
			List<SubParametroPtarSectorBean> ltaSubParametroxPTAR = JsonUtil.convertirCadenaJsonALista(ltaSubParametroPTARJson, listType);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(Util.getUserLoged());
			auditoria.setDeTermCrea(Util.obtenerTerminal(request));
			Integer indicador = 0;
			indicador = ltaSubParametroxPTAR.size();
			Integer retorno = subParametroPtarSectorService.registrarSubParametroSecHistAc(ltaSubParametroxPTAR, auditoria);
			if(indicador == retorno) {
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Guardaron los Cambios Correctamente.");
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
				respuestaBean.setMensajeRespuesta("No se Guardaron Todos los cambios Correctamente.");
			}
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
}
