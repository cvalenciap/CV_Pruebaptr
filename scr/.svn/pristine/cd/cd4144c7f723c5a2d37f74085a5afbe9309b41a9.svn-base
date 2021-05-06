/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.ManiobraBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.services.IManiobraService;
import pe.com.sedapal.scr.core.services.IRepresasService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Messages;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ManiobraController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ManiobraController {

/** The Constant LOG. */
private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The servlet context. */
	@Autowired
	ServletContext servletContext;
	
	/** The i maniobra service. */
	@Autowired
	IManiobraService iManiobraService;
	
	/** The i represas service. */
	@Autowired
	IRepresasService iRepresasService;
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla paginada.
	 *
	 * @param allRequestParams Es el objeto que contiene todos los parámetros enviados en el request
	 * @param idRepresas Es el código de represas al que pertenece la maniobra
	 * @param fechaRepresas Es la fecha de registro de represas
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/maniobras/pagination/{idRepresas}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> buscarManiobras(@RequestParam Map<String,String> allRequestParams, 
			@PathVariable Integer idRepresas, @RequestParam(name="fechaRepresas", required=true) String fechaRepresas)
			throws Exception {
		
		try {
			ManiobraBean maniobraBean = new ManiobraBean();
			maniobraBean.setRepresas(new RepresasBean());
			Paginacion paginacion = new Paginacion();
			
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			maniobraBean.getRepresas().setIntCodigo(idRepresas);
			maniobraBean.getRepresas().setStrFechaRegistro(fechaRepresas);
						
			//Recuperamos los datos de REPRESAS
			RepresasBean represasBean = iRepresasService.obtenerRepresas(idRepresas);
			Result result = iManiobraService.buscarManiobras(maniobraBean, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
			
			dtResponse.put("represasId", idRepresas);
			dtResponse.put("represasFecha", fechaRepresas);
			dtResponse.put("represasBean", represasBean);
			
			return new ResponseEntity<Map<String,Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {			
			LOG.error(e.getMessage(), e);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Este método inactiva el estado de la Maniobra.
	 *
	 * @param model Es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param request solicita la solicitud de envío a la vista
	 * @param session se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param inacIdManiobra the inac id maniobra
	 * @return the string
	 * @RequestParam inacIdManiobra es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/maniobra/inactivar", method = RequestMethod.POST)
	public String inactivarManiobra(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdManiobra) {
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		ManiobraBean maniobraBean = new ManiobraBean();
		maniobraBean.setIntCodigo(Integer.parseInt(inacIdManiobra));
		maniobraBean.setUsuarioModificacion(Util.getUserLoged());
		
		maniobraBean.setPrograma(Util.getPageCall());		
		maniobraBean.setCodigoArea(0);
		maniobraBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {			
			iManiobraService.inactivarManiobra(maniobraBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
			
			if(e instanceof DataIntegrityViolationException){
				strMensajeError = Messages.MSG_ERROR_INTEGRATION_MANIOBRAS;
			}
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANIOBRAS);
		return "maniobrasSave :: mensajesSave";

	}
	
	/**
	 * Método que permite registrar y/o actualizar una Maniobra.
	 *
	 * @param maniobrasEditBean Esta objeto contiene los datos de la entidad Maniobra
	 * @param bindingResult the binding result
	 * @param model es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @return the string
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/maniobrasSave", method = RequestMethod.POST)
	public String registrarActualizarManiobra(
			@Valid @ModelAttribute("maniobrasEditBean") ManiobraBean maniobrasEditBean,
			BindingResult bindingResult, ModelMap model){
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		maniobrasEditBean.setUsuarioCreacion(Util.getUserLoged());
		maniobrasEditBean.setUsuarioModificacion(Util.getUserLoged());
		
		maniobrasEditBean.setPrograma(Util.getPageCall());		
		maniobrasEditBean.setCodigoArea(0);
		maniobrasEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {
			if(maniobrasEditBean.getCodigo()!=null && !maniobrasEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(maniobrasEditBean.getCodigo())>0 ){
				iManiobraService.actualizarManiobra(maniobrasEditBean);			
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}else{				
				Integer estado = ConstantsCommon.ESTADO_ACTIVO;
				maniobrasEditBean.setStrEstado(estado.toString()); 
				iManiobraService.registrarManiobra(maniobrasEditBean);			
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}
		} catch (Exception e) {
			if(maniobrasEditBean.getCodigo()!=null && !maniobrasEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(maniobrasEditBean.getCodigo())>0 ){
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
			}
			else{
				strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			}
			strMensajeError = e.getMessage();
			
			if(e instanceof DataIntegrityViolationException){
				strMensajeError = Messages.MSG_ERROR_INTEGRATION_MANIOBRAS;
			}
			
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		
		return "maniobrasSave :: mensajesSave";
		
	}
	
	/**
	 * Método que permite actualizar el campo factor a un registro de represas.
	 *
	 * @param model es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @param represasId the represas id
	 * @param factor the factor
	 * @param factorValor the factor valor
	 * @param factorDescargaName the factor descarga name
	 * @return the string
	 * @Return Objeto de tipo String que contiene los resultados
	 */
	@RequestMapping(value = "/maniobras/factorSave", method = RequestMethod.POST)
	public String registrarActualizarFactor(ModelMap model,
			@RequestParam String represasId,
			@RequestParam String factor,
			@RequestParam String factorValor,
			@RequestParam String factorDescargaName){
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		try {
			RepresasBean represasBean = new RepresasBean();
			represasBean.setCodigo(represasId);
			represasBean.setIntUsaFactorDescarga(Integer.parseInt(factor));
			represasBean.setStrFactorDescarga(factorDescargaName);
			represasBean.setStrFactorValor(factorValor);
			
			represasBean.setCodigoArea(0);
			represasBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
			represasBean.setUsuarioModificacion(Util.getUserLoged());
			represasBean.setPrograma(Util.getPageCall());	
			
			iRepresasService.actualizarFactorRepresas(represasBean);
			strMensajeTipo = Constants.ACTUALIZADO_FACTOR_OK;
		} catch (Exception e) {
			strMensajeTipo = Constants.ACTUALIZADO_FACTOR_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
			
			if(e instanceof DataIntegrityViolationException){
				strMensajeError = Messages.MSG_ERROR_INTEGRATION_MANIOBRAS;
			}
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		
		return "maniobrasSave :: mensajesSave";
	}
	
	/**
	 * Método que permite enviar a al frontend la maniobra activa con mayor hora por represas.
	 *
	 * @param codRepresas Es el identificador del registro de represas al cual pertenece la maniobra
	 * @return Objeto de tipo ManiobraBean con el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/maniobras/getLast", method = RequestMethod.GET)
	public @ResponseBody ManiobraBean findLastManiobra(
			@RequestParam String codRepresas) throws Exception {
		
		if(!StringUtils.isNumeric(codRepresas)){
			return new ManiobraBean();
		}
		
		ManiobraBean maniobra = iManiobraService.obtenerUltimaManiobraActiva(Integer.parseInt(codRepresas));
		
		if(maniobra != null){
			return maniobra;
		}
		else{
			return new ManiobraBean();
		}
	}
	
	/**
	 * Método que permite enviar a al frontend el listado de maniobras activas ordenadas por hora.
	 *
	 * @param codRepresas Es el identificador del registro de represas al cual pertenece la maniobra
	 * @return Objeto de tipo ManiobraBean con el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/maniobras/getByRepresas", method = RequestMethod.GET)
	public @ResponseBody List<ManiobraBean> findManiobrasFromRepresas(
			@RequestParam String codRepresas) throws Exception {
		
		if(!StringUtils.isNumeric(codRepresas)){
			return new ArrayList<ManiobraBean>();
		}
		
		List<ManiobraBean> maniobras = iManiobraService.obtenerManiobrasActivas(Integer.parseInt(codRepresas));
		
		if(maniobras != null && maniobras.size() > 0){
			return maniobras;
		}
		else{
			return new ArrayList<ManiobraBean>();
		}
	}
}
