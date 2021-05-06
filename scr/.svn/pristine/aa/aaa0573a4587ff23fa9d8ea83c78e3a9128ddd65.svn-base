/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.TablaPoissonBean;
import pe.com.sedapal.scr.core.services.ITablaPoissonService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class TablaPoissonController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class TablaPoissonController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The tabla poisson service. */
	@Autowired
	ITablaPoissonService tablaPoissonService;
	

	/**
	 * Tabla poisson.
	 *
	 * @param request the request
	 * @param session the session
	 * @param tablaPoissonSearchBean the tabla poisson search bean
	 * @param tablaPoissonEditBean the tabla poisson edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/tablaPoisson", method = RequestMethod.GET)
	public String tablaPoisson(HttpServletRequest request, HttpSession session,
			@ModelAttribute("tablaPoissonSearchBean") TablaPoissonBean tablaPoissonSearchBean,
			@ModelAttribute("tablaPoissonEditBean") TablaPoissonBean tablaPoissonEditBean,
			ModelMap model){

		return "tablaPoisson/tablaPoisson";
	}
	
	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla páginada
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 * @throws Exception, Excepción que puede ser lanzada
	 * */
	@RequestMapping(value = "/tablaPoisson/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			TablaPoissonBean tablaPoissonBean = new TablaPoissonBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codLambda")!=null && !"".equals(allRequestParams.get("codLambda"))){
				tablaPoissonBean.setIntCodLambda((String)allRequestParams.get("codLambda"));
			}else{
				tablaPoissonBean.setIntCodLambda("0");
			}

			Result result = tablaPoissonService.obtenerDatosPoisson(tablaPoissonBean, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
			
			return new ResponseEntity<Map<String,Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {			
			LOG.error(e.getMessage(), e);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Método que permite obtener un registro de la tabla Poisson por id
	 * @ModelAttribute("tablaPoissonSearchBean") Objeto de tipo TablaPoissonBean que se utiliza como atributo para la búsqueda de tablas Poisson
	 * @ModelAttribute("tablaPoissonEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Tabla poisson get page.
	 *
	 * @param tablaPoissonSearchBean the tabla poisson search bean
	 * @param tablaPoissonEditBean the tabla poisson edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/tablaPoissonGet/{id}", method = RequestMethod.GET)
	public String tablaPoissonGetPage(
			@ModelAttribute("tablaPoissonSearchBean") TablaPoissonBean tablaPoissonSearchBean,
			@ModelAttribute("tablaPoissonEditBean") TablaPoissonBean tablaPoissonEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id>0){
				tablaPoissonEditBean = tablaPoissonService.obtenerTablaPoisson(id);
			}else{
				tablaPoissonEditBean = new TablaPoissonBean();  
			}
			model.addAttribute("tablaPoissonEditBean", tablaPoissonEditBean);
			return "tablaPoisson/tablaPoissonEdit :: form-edit-tablaPoisson";
	}
	
	
	/**
	 * Insert tabla poisson.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param tablaPoissonEditBean the tabla poisson edit bean
	 * @param response the response
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de tabla Poisson
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/tablaPoissonSave", method = RequestMethod.POST)
	public String insertTablaPoisson(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("tablaPoissonEditBean") TablaPoissonBean tablaPoissonEditBean, 
			HttpServletResponse response){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		
		tablaPoissonEditBean.setUsuarioCreacion(Util.getUserLoged());
		tablaPoissonEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
			if(tablaPoissonEditBean.getIntCodPoisson()<=0){
				tablaPoissonEditBean.setPrograma(Constants.PROGRAMA_INS_POISSON);
				int retorno = tablaPoissonService.grabarTablaPoisson(tablaPoissonEditBean);
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				if(retorno >0){
					model.addAttribute("mensajeTipo", "RegDuplicado");
					model.addAttribute("mensajeError", "DUPLICADO");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return "tablaPoisson/tablaPoissonSave :: mensajesSave";
				}
				
			}else{	
				tablaPoissonEditBean.setPrograma(Constants.PROGRAMA_UPD_POISSON);
				tablaPoissonEditBean.setIntCodLambda(tablaPoissonEditBean.getIntCodLambdaHide());
				tablaPoissonEditBean.setStrCodIgx(tablaPoissonEditBean.getStrCodIgxHide());
				tablaPoissonService.actualizarTablaPoisson(tablaPoissonEditBean);	
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCORREOS);
		return "tablaPoisson/tablaPoissonSave :: mensajesSave";
	}
	
	/**
	 * Inactivar tabla poisson.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método inactiva el estado del Clima
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/tablaPoisson/inactivar", method = RequestMethod.POST)
	public String inactivarTablaPoisson(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		TablaPoissonBean tablaPoissonBean = new TablaPoissonBean();
		//tablaPoissonBean.setIntCodPoisson(inaCodPoisson);
		tablaPoissonBean.setIntCodPoisson(inacId);
		tablaPoissonBean.setUsuarioModificacion(Util.getUserLoged());
		tablaPoissonBean.setPrograma(Util.getPageCall());

		try {			

			tablaPoissonService.inactivarTablaPoisson(tablaPoissonBean);;
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCLIMA);
		return "tablaPoisson/tablaPoissonSave :: mensajesSave";

	}	
	
}
