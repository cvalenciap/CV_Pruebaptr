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
import pe.com.sedapal.scr.core.beans.FormulaMuestraBean;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.core.services.IFormulaMuestraService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class FormulaMuestraController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class FormulaMuestraController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	/** The formula muestra service. */
	@Autowired
	IFormulaMuestraService formulaMuestraService;
	
	/**
	 * Formula muestra.
	 *
	 * @param request the request
	 * @param session the session
	 * @param formulaMuestraSearchBean the formula muestra search bean
	 * @param formulaMuestraEditBean the formula muestra edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/formulaMuestra", method = RequestMethod.GET)
	public String formulaMuestra(HttpServletRequest request, HttpSession session,
			@ModelAttribute("formulaMuestraSearchBean") FormulaMuestraBean formulaMuestraSearchBean,
			@ModelAttribute("formulaMuestraEditBean") FormulaMuestraBean formulaMuestraEditBean,
			ModelMap model){

		return "formulaMuestra/formulaMuestra";
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
	@RequestMapping(value = "/formulaMuestra/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			FormulaMuestraBean formulaMuestraBean = new FormulaMuestraBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codGrupo")!=null && !"".equals(allRequestParams.get("codGrupo"))){
				formulaMuestraBean.setIntCodGrupo(Integer.parseInt(allRequestParams.get("codGrupo")));
			}else{
				formulaMuestraBean.setIntCodGrupo(0);
			}

			Result result = formulaMuestraService.obtenerDatosFormulasMuestra(formulaMuestraBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla Formula Muestra por id
	 * @ModelAttribute("formulaMuestraSearchBean") Objeto de tipo formulaMuestraBean que se utiliza como atributo para la búsqueda de tablas Poisson
	 * @ModelAttribute("formulaMuestraEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Formula muestra get page.
	 *
	 * @param formulaMuestraSearchBean the formula muestra search bean
	 * @param formulaMuestraEditBean the formula muestra edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulaMuestraGet/{id}", method = RequestMethod.GET)
	public String formulaMuestraGetPage(
			@ModelAttribute("formulaMuestraSearchBean") FormulaMuestraBean formulaMuestraSearchBean,
			@ModelAttribute("formulaMuestraEditBean") FormulaMuestraBean formulaMuestraEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id>0){
				formulaMuestraEditBean = formulaMuestraService.obtenerFormulaMuestra(id);
			}else{
				formulaMuestraEditBean = new FormulaMuestraBean();  
			}
			model.addAttribute("formulaMuestraEditBean", formulaMuestraEditBean);
			return "formulaMuestra/formulaMuestraEdit :: form-edit-formulaMuestra";
	}
	
	/**
	 * Insert tabla poisson.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formulaMuestraEditBean the formula muestra edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de tabla Poisson
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/formulaMuestraSave", method = RequestMethod.POST)
	public String insertTablaPoisson(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formulaMuestraEditBean") FormulaMuestraBean formulaMuestraEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		
		formulaMuestraEditBean.setStrUsuarioCreacion(Util.getUserLoged());
		formulaMuestraEditBean.setStrUsuarioModificacion(Util.getUserLoged());
		try {
			if(formulaMuestraEditBean.getIntCodFormula()<=0){		
				formulaMuestraEditBean.setPrograma(Constants.PROGRAMA_INS_FORMULA);
				formulaMuestraService.grabarFormulaMuestra(formulaMuestraEditBean);		
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{	
				formulaMuestraEditBean.setPrograma(Constants.PROGRAMA_UPD_FORMULA);
				formulaMuestraEditBean.setIntCodGrupo(formulaMuestraEditBean.getIntCodGrupoHide());
				//formulaMuestraEditBean.setStrCodFormulario(formulaMuestraEditBean.getStrCodFormularioHide());
				formulaMuestraService.actualizarFormulaMuestra(formulaMuestraEditBean);		
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "formulaMuestra/formulaMuestraSave :: mensajesSave";
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
	 * Este método elimina un registro de tabla Formula Maestra 
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/formulaMuestra/inactivar", method = RequestMethod.POST)
	public String inactivarTablaPoisson(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		FormulaMuestraBean formulaMuestraBean = new FormulaMuestraBean();

		formulaMuestraBean.setIntCodFormula(inacId);

		try {			

			formulaMuestraService.inactivarFormulaMuestra(formulaMuestraBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulaMuestra/formulaMuestraSave :: mensajesSave";

	}	
	
	/**
	 * Find grupo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listGrupoTodos", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findGrupo() throws Exception {
		List<SelectItemBean> listItems = new ArrayList<>();		
		listItems = this.catalogoService.obtenerCatalogoTodosNinguno(1, 1);

		return listItems;
	}

	
}
