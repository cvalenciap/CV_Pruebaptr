/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
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
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean;
import pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class TurbidimetroDigitalController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class TurbidimetroDigitalController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
		
	/** The turbidimetro digital service. */
	@Autowired
	ITurbidimetroDigitalService turbidimetroDigitalService;
	
	/**
	 * Turbidimetro digital.
	 *
	 * @param request the request
	 * @param session the session
	 * @param turbidimetroDigitalSearchBean the turbidimetro digital search bean
	 * @param turbidimetroDigitalEditBean the turbidimetro digital edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/turbidimetroDigital", method = RequestMethod.GET)
	public String turbidimetroDigital(HttpServletRequest request, HttpSession session,
			@ModelAttribute("turbidimetroDigitalSearchBean") TurbidimetroDigitalBean turbidimetroDigitalSearchBean,
			@ModelAttribute("turbidimetroDigitalEditBean") TurbidimetroDigitalBean turbidimetroDigitalEditBean,
			ModelMap model){

		return "turbidimetroDigital/turbidimetroDigital";
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
	@RequestMapping(value = "/turbidimetroDigital/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			TurbidimetroDigitalBean turbidimetroDigitalBean = new TurbidimetroDigitalBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"".equals(allRequestParams.get("codigo"))){ //campo codigo viene desde turbidimetroDigitalTable
				turbidimetroDigitalBean.setStrCodEquipo((String)allRequestParams.get("codigo"));
			}else{
				turbidimetroDigitalBean.setStrCodEquipo("0");
			}

			Result result = turbidimetroDigitalService.obtenerDatosEquipos(turbidimetroDigitalBean, paginacion);
			
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
	 * @ModelAttribute("turbidimetroDigitalSearchBean") Objeto de tipo turbidimetroDigitalBean que se utiliza como atributo para la búsqueda de tablas Poisson
	 * @ModelAttribute("turbidimetroDigitalEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Turbidimetro digital get page.
	 *
	 * @param turbidimetroDigitalSearchBean the turbidimetro digital search bean
	 * @param turbidimetroDigitalEditBean the turbidimetro digital edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/turbidimetroDigitalGet/{id}", method = RequestMethod.GET)
	public String turbidimetroDigitalGetPage(
			@ModelAttribute("turbidimetroDigitalSearchBean") TurbidimetroDigitalBean turbidimetroDigitalSearchBean,
			@ModelAttribute("turbidimetroDigitalEditBean") TurbidimetroDigitalBean turbidimetroDigitalEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id>0){
				turbidimetroDigitalEditBean = turbidimetroDigitalService.obtenerVerificacionEquipo(id);
			}else{
				turbidimetroDigitalEditBean = new TurbidimetroDigitalBean();  
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_TURB_DIGI);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("turbidimetroDigitalEditBean", turbidimetroDigitalEditBean);
			return "turbidimetroDigital/turbidimetroDigitalEdit :: form-edit-turbidimetroDigital";
	}
	
	/**
	 * Insert turbidimetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param turbidimetroDigitalEditBean the turbidimetro digital edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Verificacion de Equipos
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/turbidimetroDigitalSave", method = RequestMethod.POST)
	public String insertTurbidimetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("turbidimetroDigitalEditBean") TurbidimetroDigitalBean turbidimetroDigitalEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		turbidimetroDigitalEditBean.setIntEstado(Constants.ACTIVO);
		
		turbidimetroDigitalEditBean.setUsuarioCreacion(Util.getUserLoged());
		turbidimetroDigitalEditBean.setUsuarioModificacion(Util.getUserLoged());
		int codigo = turbidimetroDigitalEditBean.getIntCodigo();
		try {
			if(codigo<=0){	
				turbidimetroDigitalEditBean.setPrograma(Constants.PROGRAMA_INS_TURBIDI);
				codigo = turbidimetroDigitalService.grabarVerificacionEquipo(turbidimetroDigitalEditBean);		
				if(codigo==-1){
					model.addAttribute("mensajeTipo", "RegDuplicado");
					model.addAttribute("mensajeError", "DUPLICADO");					
					return "turbidimetroDigital/turbidimetroDigitalSave :: mensajesSave";
				}
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{				
				turbidimetroDigitalEditBean.setPrograma(Constants.PROGRAMA_UPD_TURBIDI);
				turbidimetroDigitalEditBean.setStrCodEquipo(turbidimetroDigitalEditBean.getStrCodEquipoHide());
				turbidimetroDigitalService.actualizarVerificacionEquipo(turbidimetroDigitalEditBean);		
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "turbidimetroDigital/turbidimetroDigitalSave :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_TURB_DIGI);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_TURB_DIGI);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_TURB_DIGI);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", turbidimetroDigitalEditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	/**
	 * Inactivar turbidimetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla Verificacion de equipos
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/turbidimetroDigital/inactivar", method = RequestMethod.POST)
	public String inactivarTurbidimetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		TurbidimetroDigitalBean turbidimetroDigitalBean = new TurbidimetroDigitalBean();
		
		turbidimetroDigitalBean.setIntEstado(Constants.INACTIVO);
		turbidimetroDigitalBean.setIntCodigo(inacId);

		try {			

			turbidimetroDigitalService.inactivarVerificacionEquipo(turbidimetroDigitalBean);;
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "turbidimetroDigital/turbidimetroDigitalSave :: mensajesSave";

	}	
	
	/**
	 * Obtener datos verificacion.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	/*
	 * Muestra grilla páginada
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda, en este caso es estático
	 * @throws Exception, Excepción que puede ser lanzada
	 * */
	@RequestMapping(value = "/turbidimetroDigitalVerif/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosVerificacion(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean = new TurbidimetroDigitalVerificacionBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"0".equals(allRequestParams.get("codigo")) && !"".equals(allRequestParams.get("codigo"))){
				turbidimetroDigitalVerificacionBean.setIntCodigoTurbDigital(Integer.parseInt(allRequestParams.get("codigo")));
			}else{
				turbidimetroDigitalVerificacionBean.setIntCodigoTurbDigital(0);
			}

			Result result = turbidimetroDigitalService.obtenerDatosEquiposVerificacion(turbidimetroDigitalVerificacionBean, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			if(result != null){				
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords()+1);
			dtResponse.put("recordsFiltered", result.getRecords()+1);
			dtResponse.put("data", result.getData());
			}
			
			return new ResponseEntity<Map<String,Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {			
			LOG.error(e.getMessage(), e);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Turbidimetro digital verificacion get page.
	 *
	 * @param turbidimetroDigitalVerifSearchBean the turbidimetro digital verif search bean
	 * @param turbidimetroDigitalVerifEditBean the turbidimetro digital verif edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/turbidimetroDigitalVerificacionGet/{id}", method = RequestMethod.GET)
	public String turbidimetroDigitalVerificacionGetPage(
			@ModelAttribute("turbidimetroDigitalVerifSearchBean") TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerifSearchBean,
			@ModelAttribute("turbidimetroDigitalVerifEditBean") TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerifEditBean, Model model,
			@PathVariable int id) throws Exception {
		
				turbidimetroDigitalVerifEditBean = new TurbidimetroDigitalVerificacionBean();  
			
				model.addAttribute("turbidimetroDigitalVerifEditBean", turbidimetroDigitalVerifEditBean);
				return "turbidimetroDigital/turbidimetroDigitalVerifiEdit :: form-edit-turbidimetroDigitalVerif";
	}
	
	/**
	 * Insert turbidimetro digital verificacion.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param turbidimetroDigitalVerifEditBean the turbidimetro digital verif edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Verificacion de Equipos del formulario hijo
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/turbidimetroDigitalVerificacionSave", method = RequestMethod.POST)
	public String insertTurbidimetroDigitalVerificacion(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("turbidimetroDigitalVerifEditBean") TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerifEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		turbidimetroDigitalVerifEditBean.setIntEstado(Constants.ACTIVO);
		turbidimetroDigitalVerifEditBean.setPrograma(Constants.PROGRAMA_INS_TURBIDI_EDIT);
		turbidimetroDigitalVerifEditBean.setUsuarioCreacion(Util.getUserLoged());
		turbidimetroDigitalVerifEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
				
			turbidimetroDigitalService.grabarVerificacionEquipoForm(turbidimetroDigitalVerifEditBean);								
			
			//strMensajeTipo = ConstantsCommon.GRABADO_OK;
			model.addAttribute("mensajeTipo", "grabadoOkSubForm");
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		return "turbidimetroDigital/turbidimetroDigitalSave :: mensajesSave";
	}
	
	/**
	 * Inactivar turbidimetro digital verificacion.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdVerif the inac id verif
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla Verificacion de equipos del formulario hijo
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/turbidimetroDigitalVerificacion/inactivar", method = RequestMethod.POST)
	public String inactivarTurbidimetroDigitalVerificacion(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacIdVerif) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean = new TurbidimetroDigitalVerificacionBean();
		
		turbidimetroDigitalVerificacionBean.setIntEstado(Constants.INACTIVO);
		turbidimetroDigitalVerificacionBean.setIntIdVerificacion(inacIdVerif);

		try {			
			turbidimetroDigitalService.inactivarVerificacionEquipoForm(turbidimetroDigitalVerificacionBean);;
			//strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
			model.addAttribute("mensajeTipo", "inactivadoOkSubForm");
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		//model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "turbidimetroDigital/turbidimetroDigitalSave :: mensajesSave";

	}	
}
