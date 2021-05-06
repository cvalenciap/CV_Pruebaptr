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
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.services.IConductimetroDigitalService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ConductimetroDigitalController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ConductimetroDigitalController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	
	/** The conductimetro digital service. */
	@Autowired
	IConductimetroDigitalService conductimetroDigitalService;
	
	/**
	 * Conductimetro digital.
	 *
	 * @param request the request
	 * @param session the session
	 * @param conductimetroDigitalSearchBean the conductimetro digital search bean
	 * @param conductimetroDigitalEditBean the conductimetro digital edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/conductimetroDigital", method = RequestMethod.GET)
	public String conductimetroDigital(HttpServletRequest request, HttpSession session,
			@ModelAttribute("conductimetroDigitalSearchBean") ConductimetroDigitalBean conductimetroDigitalSearchBean,
			@ModelAttribute("conductimetroDigitalEditBean") ConductimetroDigitalBean conductimetroDigitalEditBean,
			ModelMap model){

		return "conductimetroDigital/conductimetroDigital";
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
	@RequestMapping(value = "/conductimetroDigital/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ConductimetroDigitalBean conductimetroDigitalBean = new ConductimetroDigitalBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"".equals(allRequestParams.get("codigo"))){
				conductimetroDigitalBean.setStrCodEquipo((String)allRequestParams.get("codigo"));
			}else{
				conductimetroDigitalBean.setStrCodEquipo("VACIO");
			}

			Result result = conductimetroDigitalService.obtenerDatosEquipos(conductimetroDigitalBean, paginacion);
			
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
	 * @ModelAttribute("conductimetroDigitalSearchBean") Objeto de tipo ConductimetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla de Conductimetro
	 * @ModelAttribute("conductimetroDigitalEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Conductimetro digital get page.
	 *
	 * @param conductimetroDigitalSearchBean the conductimetro digital search bean
	 * @param conductimetroDigitalEditBean the conductimetro digital edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/conductimetroDigitalGet/{id}", method = RequestMethod.GET)
	public String conductimetroDigitalGetPage(
			@ModelAttribute("conductimetroDigitalSearchBean") ConductimetroDigitalBean conductimetroDigitalSearchBean,
			@ModelAttribute("conductimetroDigitalEditBean") ConductimetroDigitalBean conductimetroDigitalEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				conductimetroDigitalEditBean = conductimetroDigitalService.obtenerConductimetroDigital(id);
			}else{
				conductimetroDigitalEditBean = new ConductimetroDigitalBean();  
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_COND_DIGI);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("conductimetroDigitalEditBean", conductimetroDigitalEditBean);
			return "conductimetroDigital/conductimetroDigitalEdit :: form-edit-conductimetroDigital";
	}
	
	/**
	 * Insert conductimetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param conductimetroDigitalEditBean the conductimetro digital edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Conductimetro
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/conductimetroDigitalSave", method = RequestMethod.POST)
	public String insertConductimetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("phmetroDigitalEditBean") ConductimetroDigitalBean conductimetroDigitalEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		conductimetroDigitalEditBean.setIntEstado(Constants.ACTIVO);		
		conductimetroDigitalEditBean.setUsuarioCreacion(Util.getUserLoged());
		conductimetroDigitalEditBean.setUsuarioModificacion(Util.getUserLoged());
		int codigo = conductimetroDigitalEditBean.getIntCodigo();
		try {
			if(codigo<=0){				
				conductimetroDigitalEditBean.setPrograma(Constants.PROGRAMA_INS_CONDUCTIMETRO);
				codigo = conductimetroDigitalService.grabarConductimetroDigital(conductimetroDigitalEditBean);
				if(codigo==-1){
					model.addAttribute("mensajeTipo", "RegDuplicado");
					model.addAttribute("mensajeError", "DUPLICADO");					
					return "conductimetroDigital/conductimetroDigitalSave :: mensajesSave";
				}
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{	
				conductimetroDigitalEditBean.setPrograma(Constants.PROGRAMA_UPD_CONDUCTIMETRO);
				conductimetroDigitalEditBean.setStrCodEquipo(conductimetroDigitalEditBean.getStrCodEquipoHide());
				conductimetroDigitalService.actualizarConductimetroDigital(conductimetroDigitalEditBean);
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "conductimetroDigital/conductimetroDigitalSave :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_COND_DIGI);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_COND_DIGI);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_COND_DIGI);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", conductimetroDigitalEditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	/**
	 * Inactivar conductimetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla de Conductimetro
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/conductimetroDigital/inactivar", method = RequestMethod.POST)
	public String inactivarConductimetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		ConductimetroDigitalBean conductimetroDigitalBean = new ConductimetroDigitalBean();
		
		conductimetroDigitalBean.setIntEstado(Constants.INACTIVO);
		conductimetroDigitalBean.setIntCodigo(inacId);

		try {			

			conductimetroDigitalService.inactivarConductimetroDigital(conductimetroDigitalBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "conductimetroDigital/conductimetroDigitalSave :: mensajesSave";

	}	
	
	/*
	 * Método que permite obtener un registro de la tabla de Conductimetro
	 * @ModelAttribute("conductimetroDigitalSearchBean") Objeto de tipo ConductimetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla de Conductimetro
	 * @ModelAttribute("conductimetroDigitalEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Conductimetro digital detalle get page.
	 *
	 * @param conductimetroDigitalDetalleSearchBean the conductimetro digital detalle search bean
	 * @param conductimetroDigitalDetalleEditBean the conductimetro digital detalle edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/conductimetroDigitalDetalleGet/{id}", method = RequestMethod.GET)
	public String conductimetroDigitalDetalleGetPage(
			@ModelAttribute("conductimetroDigitalDetalleSearchBean") ConductimetroDigitalBean conductimetroDigitalDetalleSearchBean,
			@ModelAttribute("conductimetroDigitalDetalleEditBean") ConductimetroDigitalBean conductimetroDigitalDetalleEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				conductimetroDigitalDetalleEditBean = conductimetroDigitalService.obtenerConductimetroDigital(id);
			}else{
				conductimetroDigitalDetalleEditBean = new ConductimetroDigitalBean();  

			}
			model.addAttribute("conductimetroDigitalDetalleEditBean", conductimetroDigitalDetalleEditBean);
			return "conductimetroDigital/conductimetroDigitalDetalle :: form-edit-conductimetroDigitalDetalle";
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
	@RequestMapping(value = "/conductimetroDigitalDetalle/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosDetalle(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean = new ConductimetroDigitalDetalleBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"".equals(allRequestParams.get("codigo"))){
				conductimetroDigitalDetalleBean.setIntIdConductimetro(Integer.parseInt(allRequestParams.get("codigo")));

			}else{
				conductimetroDigitalDetalleBean.setIntIdConductimetro(0);
			}

			Result result = conductimetroDigitalService.obtenerDatosEquiposDetalle(conductimetroDigitalDetalleBean, paginacion);
			
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
	 * @ModelAttribute("phmetroDigitalDetalleSearchBean") Objeto de tipo PhmetroDigitalBean que se utiliza como atributo para la búsqueda en la tabla  de detalle de PHmetro Digital
	 * @ModelAttribute("phmetroDigitalDetalleEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Phmetro digital detalle edit get page.
	 *
	 * @param conductimetroDigitalDetalleSearchBean the conductimetro digital detalle search bean
	 * @param conductimetroDigitalDetalleEditBean the conductimetro digital detalle edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/conductimetroDigitalDetalleEditGet/{id}", method = RequestMethod.GET)
	public String phmetroDigitalDetalleEditGetPage(
			@ModelAttribute("conductimetroDigitalDetalleSearchBean") ConductimetroDigitalDetalleBean conductimetroDigitalDetalleSearchBean,
			@ModelAttribute("conductimetroDigitalDetalleEditBean") ConductimetroDigitalDetalleBean conductimetroDigitalDetalleEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			conductimetroDigitalDetalleEditBean = new ConductimetroDigitalDetalleBean();  
			
			model.addAttribute("conductimetroDigitalDetalleEditBean", conductimetroDigitalDetalleEditBean);
			return "conductimetroDigital/conductimetroDigitalDetalleEdit :: form-edit-conductimetroDigitalDetalleEdit";
	}
	
	/**
	 * Insert conductimetro digital detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param conductimetroDigitalDetalleEditBean the conductimetro digital detalle edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de Conductimetro
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/conductimetroDigitalDetalleEditSave", method = RequestMethod.POST)
	public String insertConductimetroDigitalDetalle(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("conductimetroDigitalDetalleEditBean") ConductimetroDigitalDetalleBean conductimetroDigitalDetalleEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		conductimetroDigitalDetalleEditBean.setIntEstado(Constants.ACTIVO);		
		conductimetroDigitalDetalleEditBean.setUsuarioCreacion(Util.getUserLoged());
		conductimetroDigitalDetalleEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
					
			conductimetroDigitalDetalleEditBean.setPrograma(Constants.PROGRAMA_INS_CONDUCTIMETRO_EDIT);
			conductimetroDigitalService.grabarConductimetroDigitalDetalle(conductimetroDigitalDetalleEditBean);
				
			
			//strMensajeTipo = ConstantsCommon.GRABADO_OK;
			strMensajeTipo = "grabadoOkSubForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "conductimetroDigital/conductimetroDigitalSave :: mensajesSave";
	}

	/**
	 * Inactivar conductimetro digital detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdDetalle the inac id detalle
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla detalle de Conductimetro
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/conductimetroDigitalDetalle/inactivar", method = RequestMethod.POST)
	public String inactivarConductimetroDigitalDetalle(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacIdDetalle) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean = new ConductimetroDigitalDetalleBean();
		
		conductimetroDigitalDetalleBean.setIntEstado(Constants.INACTIVO);
		conductimetroDigitalDetalleBean.setIntIdConductimetro(inacIdDetalle);

		try {			

			conductimetroDigitalService.inactivarConductimetroDigitalDetalle(conductimetroDigitalDetalleBean);
			//strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
			strMensajeTipo = "inactivadoSubFormOk";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "conductimetroDigital/conductimetroDigitalSave :: mensajesSave";
	}
}
