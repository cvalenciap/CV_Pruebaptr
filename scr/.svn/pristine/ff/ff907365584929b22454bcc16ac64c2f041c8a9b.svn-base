/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.HashMap;
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
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.services.IPhmetroDigitalService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class PhmetroDigitalController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class PhmetroDigitalController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	/** The phmetro digital service. */
	@Autowired
	IPhmetroDigitalService phmetroDigitalService;
	
	/**
	 * Phmetro digital.
	 *
	 * @param request the request
	 * @param session the session
	 * @param phmetroDigitalSearchBean the phmetro digital search bean
	 * @param phmetroDigitalEditBean the phmetro digital edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/phmetroDigital", method = RequestMethod.GET)
	public String phmetroDigital(HttpServletRequest request, HttpSession session,
			@ModelAttribute("phmetroDigitalSearchBean") PhmetroDigitalBean phmetroDigitalSearchBean,
			@ModelAttribute("phmetroDigitalEditBean") PhmetroDigitalBean phmetroDigitalEditBean,
			ModelMap model){

		return "phmetroDigital/phmetroDigital";
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
	@RequestMapping(value = "/phmetroDigital/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			PhmetroDigitalBean phmetroDigitalBean = new PhmetroDigitalBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"".equals(allRequestParams.get("codigo"))){
				phmetroDigitalBean.setStrCodEquipo((String)allRequestParams.get("codigo"));
			}else{
				phmetroDigitalBean.setStrCodEquipo("0");
			}

			Result result = phmetroDigitalService.obtenerDatosEquipos(phmetroDigitalBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de PHmetro Digital
	 * @ModelAttribute("phmetroDigitalSearchBean") Objeto de tipo PhmetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("phmetroDigitalEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Phmetro digital get page.
	 *
	 * @param phmetroDigitalSearchBean the phmetro digital search bean
	 * @param phmetroDigitalEditBean the phmetro digital edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/phmetroDigitalGet/{id}", method = RequestMethod.GET)
	public String phmetroDigitalGetPage(
			@ModelAttribute("phmetroDigitalSearchBean") PhmetroDigitalBean phmetroDigitalSearchBean,
			@ModelAttribute("phmetroDigitalEditBean") PhmetroDigitalBean phmetroDigitalEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				phmetroDigitalEditBean = phmetroDigitalService.obtenerPhmetroDigital(id);
			}else{
				phmetroDigitalEditBean = new PhmetroDigitalBean();  
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_PHME_DIGI);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("phmetroDigitalEditBean", phmetroDigitalEditBean);
			return "phmetroDigital/phmetroDigitalEdit :: form-edit-phmetroDigital";
	}
	
	/**
	 * Insert phmetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param phmetroDigitalEditBean the phmetro digital edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Verificacion de Equipos
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/phmetroDigitalSave", method = RequestMethod.POST)
	public String insertPhmetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("phmetroDigitalEditBean") PhmetroDigitalBean phmetroDigitalEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		phmetroDigitalEditBean.setIntEstado(Constants.ACTIVO);		
		phmetroDigitalEditBean.setUsuarioCreacion(Util.getUserLoged());
		phmetroDigitalEditBean.setUsuarioModificacion(Util.getUserLoged());
		int codigo = phmetroDigitalEditBean.getIntCodigo();
		try {
			if(codigo<=0){				
				phmetroDigitalEditBean.setPrograma(Constants.PROGRAMA_INS_PHMETRO);
				codigo = phmetroDigitalService.grabarPhmetroDigital(phmetroDigitalEditBean);
				
				if(codigo==-1){
					model.addAttribute("mensajeTipo", "RegDuplicado");
					model.addAttribute("mensajeError", "DUPLICADO");					
					return "phmetroDigital/phmetroDigitalSave :: mensajesSave";
				}
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{	
				phmetroDigitalEditBean.setPrograma(Constants.PROGRAMA_UPD_PHMETRO);
				phmetroDigitalEditBean.setStrCodEquipo(phmetroDigitalEditBean.getStrCodEquipoHide());
				phmetroDigitalService.actualizarPhmetroDigital(phmetroDigitalEditBean);	
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "phmetroDigital/phmetroDigitalSave :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_PHME_DIGI);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_PHME_DIGI);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_PHME_DIGI);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", phmetroDigitalEditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	/**
	 * Inactivar phmetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla Phmetro Digital
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/phmetroDigital/inactivar", method = RequestMethod.POST)
	public String inactivarPhmetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		PhmetroDigitalBean phmetroDigitalBean = new PhmetroDigitalBean();
		
		phmetroDigitalBean.setIntEstado(Constants.INACTIVO);
		phmetroDigitalBean.setIntCodigo(inacId);

		try {			

			phmetroDigitalService.inactivarPhmetroDigital(phmetroDigitalBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "phmetroDigital/phmetroDigitalSave :: mensajesSave";

	}	
	

	/*
	 * Método que permite obtener rregistros de la tabla detalle de PHmetro Digital
	 * @ModelAttribute("phmetroDigitalSearchBean") Objeto de tipo PhmetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("phmetroDigitalEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Phmetro digital detalle get page.
	 *
	 * @param phmetroDigitalSearchBean the phmetro digital search bean
	 * @param phmetroDigitalEditBean the phmetro digital edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/phmetroDigitalDetalleGet/{id}", method = RequestMethod.GET)
	public String phmetroDigitalDetalleGetPage(
			@ModelAttribute("phmetroDigitalSearchBean") PhmetroDigitalBean phmetroDigitalSearchBean,
			@ModelAttribute("phmetroDigitalEditBean") PhmetroDigitalBean phmetroDigitalEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				phmetroDigitalEditBean = phmetroDigitalService.obtenerPhmetroDigital(id);
			}else{
				phmetroDigitalEditBean = new PhmetroDigitalBean();  
			}
			model.addAttribute("phmetroDigitalEditBean", phmetroDigitalEditBean);
			return "phmetroDigital/phmetroDigitalDetalle:: form-edit-phmetroDigitalDetalle";
	}
	
	/**
	 * Obtener datos detalle.
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
	@RequestMapping(value = "/phmetroDigitalDetalle/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosDetalle(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			PhmetroDigitalDetalleBean phmetroDigitalDetalleBean = new PhmetroDigitalDetalleBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"".equals(allRequestParams.get("codigo"))){
				phmetroDigitalDetalleBean.setIntIdPhmetroDigital(Integer.parseInt(allRequestParams.get("codigo")));

			}else{
				phmetroDigitalDetalleBean.setIntIdPhmetroDigital(0);
			}

			Result result = phmetroDigitalService.obtenerDatosEquiposDetalle(phmetroDigitalDetalleBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de detalle de PHmetro Digital
	 * @ModelAttribute("phmetroDigitalDetallerSearchBean") Objeto de tipo PhmetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla  de detalle de PHmetro Digital
	 * @ModelAttribute("phmetroDigitalDetalleEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Phmetro digital detalle edit get page.
	 *
	 * @param phmetroDigitalDetallerSearchBean the phmetro digital detaller search bean
	 * @param phmetroDigitalDetalleEditBean the phmetro digital detalle edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/phmetroDigitalDetalleEditGet/{id}", method = RequestMethod.GET)
	public String phmetroDigitalDetalleEditGetPage(
			@ModelAttribute("phmetroDigitalSearchBean") PhmetroDigitalDetalleBean phmetroDigitalDetallerSearchBean,
			@ModelAttribute("phmetroDigitalEditBean") PhmetroDigitalDetalleBean phmetroDigitalDetalleEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			phmetroDigitalDetalleEditBean = new PhmetroDigitalDetalleBean();  
			
			model.addAttribute("phmetroDigitalDetalleEditBean", phmetroDigitalDetalleEditBean);
			return "phmetroDigital/phmetroDigitalDetalleEdit :: form-edit-phmetroDigitalDetalleEdit";
	}
	
	/**
	 * Insert phmetro digital detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param phmetroDigitalDetalleEditBean the phmetro digital detalle edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Verificacion de Equipos
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/phmetroDigitalDetalleEditSave", method = RequestMethod.POST)
	public String insertPhmetroDigitalDetalle(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("phmetroDigitalDetalleEditBean") PhmetroDigitalDetalleBean phmetroDigitalDetalleEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		phmetroDigitalDetalleEditBean.setIntEstado(Constants.ACTIVO);		
		phmetroDigitalDetalleEditBean.setUsuarioCreacion(Util.getUserLoged());
		phmetroDigitalDetalleEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
	
			phmetroDigitalDetalleEditBean.setPrograma(Constants.PROGRAMA_INS_PHMETRO);
			phmetroDigitalService.grabarPhmetroDigitalDetalle(phmetroDigitalDetalleEditBean);								

			//strMensajeTipo = ConstantsCommon.GRABADO_OK;
			strMensajeTipo = "grabadoOkSubForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "phmetroDigital/phmetroDigitalSave :: mensajesSave";
	}
	
	/**
	 * Inactivar phmetro digital detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdDetalle the inac id detalle
	 * @return the string
	 */
	/* 
	 * Este método desactiva un registro de tabla Detalle de Phmetro Digital
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/phmetroDigitalDetalle/inactivar", method = RequestMethod.POST)
	public String inactivarPhmetroDigitalDetalle(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacIdDetalle) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		PhmetroDigitalDetalleBean phmetroDigitalDetalleBean = new PhmetroDigitalDetalleBean();
		
		phmetroDigitalDetalleBean.setIntEstado(Constants.INACTIVO);
		phmetroDigitalDetalleBean.setIntIdDetalle(inacIdDetalle);

		try {			

			phmetroDigitalService.inactivarPhmetroDigitalDetalle(phmetroDigitalDetalleBean);
			strMensajeTipo = "inactivadoSubFormOk";
			//strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "phmetroDigital/phmetroDigitalSave :: mensajesSave";

	}	
}
