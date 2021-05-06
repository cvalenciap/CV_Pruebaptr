/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.services.IMantRepresaService;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class RepresaController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class RepresaController {

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
	
	/** The i mant represa service. */
	@Autowired
	IMantRepresaService iMantRepresaService;
	
	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Represa.
	 *
	 * @param request the request
	 * @param session the session
	 * @param represaSearchBean the represa search bean
	 * @param represaEditBean the represa edit bean
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("represaSearchBean") Objeto de tipo RepresaBean que se utiliza como atributo para la búsqueda de represa
	 * @ModelAttribute("represaEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/represa", method = RequestMethod.GET)
	public String contratos(HttpServletRequest request, HttpSession session,
			@ModelAttribute("represaSearchBean") RepresaBean represaSearchBean,
			@ModelAttribute("represaEditBean") RepresaBean represaEditBean,
			ModelMap model){

		return "represa";
	}
	
	/**
	 * Método que permite obtener una Represa por id.
	 *
	 * @param represaSearchBean the represa search bean
	 * @param represaEditBean the represa edit bean
	 * @param session the session
	 * @param model the model
	 * @param idrepresa the idrepresa
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @ModelAttribute("represaSearchBean") Objeto de tipo RepresaBean que se utiliza como atributo para la búsqueda de represas
	 * @ModelAttribute("represaEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * Model es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identica al id como variable
	 */
	@RequestMapping(value = "/represaGet/{idrepresa}", method = RequestMethod.GET)
	public String tipoProcesoGetPage(
			@ModelAttribute("represaSearchBean") RepresaBean represaSearchBean,
			@ModelAttribute("represaEditBean") RepresaBean represaEditBean, HttpSession session,Model model,
			@PathVariable Integer idrepresa) throws Exception {
		if(idrepresa!=null && idrepresa>0){
			represaEditBean = iMantRepresaService.obtenerRepresa(idrepresa);
			session.setAttribute("represaForEdit", represaEditBean);
		}else{
			represaEditBean = new RepresaBean();  
		}
		model.addAttribute("represaEditBean", represaEditBean);
		return "represaEdit :: form-edit-represa";
	}
	
	/**
	 * Método que permite registrar y/o actualizar una Represa.
	 *
	 * @param represaSearchBean the represa search bean
	 * @param represaEditBean the represa edit bean
	 * @param session the session
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("represaSearchBean") Objeto de tipo RepresaBean que se utiliza como atributo para la búsqueda de represas
	 * @ModelAttribute("represaEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/represaSave", method = RequestMethod.POST)
	public String actualizarRepresa(
			@Valid @ModelAttribute("represaSearchBean") RepresaBean represaSearchBean,
			@Valid @ModelAttribute("represaEditBean") RepresaBean represaEditBean,
			HttpSession session,
			BindingResult bindingResult, ModelMap model){
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		represaEditBean.setPrograma(Util.getPageCall());
		represaEditBean.setUsuarioCreacion(Util.getUserLoged());
		represaEditBean.setUsuarioModificacion(Util.getUserLoged());
		represaEditBean.setCodigoArea(0);
		represaEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		
		try {
			if(represaEditBean.getCodigo()!=null && !represaEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(represaEditBean.getCodigo())>0 ){
				
				RepresaBean represaEdit = (RepresaBean)session.getAttribute("represaForEdit");
				boolean successact = iMantRepresaService.actualizarRepresa(represaEditBean, 
						!represaEdit.getAbreviatura().trim().toUpperCase().equals(represaEditBean.getAbreviatura().trim().toUpperCase()));	
				if(!successact){
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
					strMensajeError = Constants.VALIDACION_ABREVIATURA;
				}
				else{
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
				}

			}else{
				Integer estado = ConstantsCommon.ESTADO_ACTIVO;
				represaEditBean.setEstado(estado.toString());
				boolean successGrab = iMantRepresaService.grabarRepresa(represaEditBean);	
				if(!successGrab){
					strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
					strMensajeError = Constants.VALIDACION_ABREVIATURA;
				}
				else{
					strMensajeTipo = ConstantsCommon.GRABADO_OK;
				}
			}
		} catch (Exception e) {
			if(represaEditBean.getCodigo()!=null && !represaEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(represaEditBean.getCodigo())>0 ){
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
			}
			else{
				strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			}
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		
		return "represaSave :: mensajesSave";
	}
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla páginada.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 */
	@RequestMapping(value = "/represa/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			RepresaBean represaBean = new RepresaBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("nombreRepresa")!=null && !"".equals(allRequestParams.get("nombreRepresa"))) {
				represaBean.setNombreRepresa(allRequestParams.get("nombreRepresa").toUpperCase());
			}

			if(allRequestParams.get("abreviatura")!=null && !"".equals(allRequestParams.get("abreviatura"))) {
			   represaBean.setAbreviatura(allRequestParams.get("abreviatura").toUpperCase());
			}
			
			if(allRequestParams.get("perteneceAlmacenamiento")!=null && !"".equals(allRequestParams.get("perteneceAlmacenamiento"))){
				represaBean.setPerteneceAlmacenamiento(allRequestParams.get("perteneceAlmacenamiento"));
			}
			else{
				represaBean.setPerteneceAlmacenamiento(""); // PARA QUE NO ENVIE CERO
			}
			
			if(allRequestParams.get("estado")!=null && !"".equals(allRequestParams.get("estado"))) {
				represaBean.setEstado(allRequestParams.get("estado"));
			}

			
			Result result = iMantRepresaService.obtenerMantRepresa(represaBean, paginacion);
			
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
	
	/**
	 *  
	 * Este método inactiva el estado del Represa
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdRepresa the inac id represa
	 * @return the string
	 * @RequestParam inacIdRepresa es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/represa/inactivar", method = RequestMethod.POST)
	public String inactivarRepresa(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdRepresa) {
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		RepresaBean represaBean = new RepresaBean();
		represaBean.setCodigo(inacIdRepresa);
		represaBean.setUsuarioModificacion(Util.getUserLoged());
		represaBean.setPrograma(Util.getPageCall());
		
		represaBean.setCodigoArea(0);
		represaBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {			

			iMantRepresaService.inactivarRepresa(represaBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTREPRESA);
		return "represaSave :: mensajesSave";

	}

}

