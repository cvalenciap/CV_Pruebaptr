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
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.services.IMantRioService;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class RioController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class RioController {

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

	/** The i mant rio service. */
	@Autowired
	IMantRioService iMantRioService;
	
	
	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Río.
	 *
	 * @param request the request
	 * @param session the session
	 * @param rioSearchBean the rio search bean
	 * @param rioEditBean the rio edit bean
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("rioSearchBean") Objeto de tipo RioBean que se utiliza como atributo para la búsqueda de rios
	 * @ModelAttribute("rioEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/rio", method = RequestMethod.GET)
	public String rio(HttpServletRequest request, HttpSession session,
			@ModelAttribute("rioSearchBean") RioBean rioSearchBean,
			@ModelAttribute("rioEditBean") RioBean rioEditBean,
			ModelMap model){
		
		return "rio";
	}
	
	/**
	 * Método que permite obtener un Río por id.
	 *
	 * @param rioSearchBean the rio search bean
	 * @param rioEditBean the rio edit bean
	 * @param session the session
	 * @param model the model
	 * @param idRio the id rio
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @ModelAttribute("rioSearchBean") Objeto de tipo RioBean que se utiliza como atributo para la búsqueda de rios
	 * @ModelAttribute("rioEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifica al id como variable
	 */
	@RequestMapping(value = "/rioGet/{idRio}", method = RequestMethod.GET)
	public String tipoProcesoGetPage(
			@ModelAttribute("rioSearchBean") RioBean rioSearchBean,
			@ModelAttribute("rioEditBean") RioBean rioEditBean, 
			HttpSession session,
			Model model,
			@PathVariable Integer idRio) throws Exception {
		if(idRio!=null && idRio>0){
			rioEditBean = iMantRioService.obtenerRio(idRio);
			session.setAttribute("rioForEdit", rioEditBean);
		}else{
			rioEditBean = new RioBean();  
		}
		model.addAttribute("rioEditBean", rioEditBean);
		return "rioEdit :: form-edit-rio";
	}
	
	/**
	 * Método que permite registrar y/o actualizar un Río.
	 *
	 * @param rioSearchBean the rio search bean
	 * @param rioEditBean the rio edit bean
	 * @param session the session
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("rioSearchBean") Objeto de tipo RioBean que se utiliza como atributo para la búsqueda de rios
	 * @ModelAttribute("rioEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/rioSave", method = RequestMethod.POST)
	public String actualizarRio(
			@Valid @ModelAttribute("rioSearchBean") RioBean rioSearchBean,
			@Valid @ModelAttribute("rioEditBean") RioBean rioEditBean,
			HttpSession session,
			BindingResult bindingResult, ModelMap model){
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		
		rioEditBean.setPrograma(Util.getPageCall());
		rioEditBean.setUsuarioModificacion(Util.getUserLoged());
		rioEditBean.setUsuarioCreacion(Util.getUserLoged());
		rioEditBean.setCodigoArea(0);
		rioEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {
			if(rioEditBean.getCodigo()!=null && !rioEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(rioEditBean.getCodigo())>0 ){
				RioBean rioEdit = (RioBean)session.getAttribute("rioForEdit");
				Map<String, Object> mapResultados = iMantRioService.actualizarRio(rioEditBean,
						!rioEdit.getAbreviatura().trim().toUpperCase().equals(rioEditBean.getAbreviatura().trim().toUpperCase()),
						!rioEdit.getNombreLargo().trim().toUpperCase().equals(rioEditBean.getNombreLargo().trim().toUpperCase()));
				boolean successact = (boolean) mapResultados.get("resultado");	
				if(!successact){
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
					strMensajeError = (String) mapResultados.get("mensaje");
				}
				else{
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
				}

			}else{
				Integer estado = ConstantsCommon.ESTADO_ACTIVO;
				rioEditBean.setEstado(estado.toString());
				Map<String, Object> mapResultados = iMantRioService.grabarRio(rioEditBean);	
				boolean successGrab = (boolean) mapResultados.get("resultado");	
				if(!successGrab){
					strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
					strMensajeError = (String) mapResultados.get("mensaje");
				}
				else{
					strMensajeTipo = ConstantsCommon.GRABADO_OK;
				}
			}
		} catch (Exception e) {
			if(rioEditBean.getCodigo()!=null && !rioEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(rioEditBean.getCodigo())>0 ){
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
		
		return "rioSave :: mensajesSave";
	}

	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla páginada.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 */
	@RequestMapping(value = "/rio/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			RioBean rioBean = new RioBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("nombreRio")!=null && !"".equals(allRequestParams.get("nombreRio"))) {
				rioBean.setNombrerio(allRequestParams.get("nombreRio").toUpperCase());
			}

			if(allRequestParams.get("abreviatura")!=null && !"".equals(allRequestParams.get("abreviatura"))) {
			   rioBean.setAbreviatura(allRequestParams.get("abreviatura").toUpperCase());
			}
			
			if(allRequestParams.get("perteneceAforo")!=null && !"".equals(allRequestParams.get("perteneceAforo"))){
				rioBean.setPerteneceAforo(allRequestParams.get("perteneceAforo"));
			}
			else{
				rioBean.setPerteneceAforo(""); // PARA QUE NO ENVIE CERO
			}
			
			if(allRequestParams.get("estado")!=null && !"".equals(allRequestParams.get("estado"))) {
				rioBean.setEstado(allRequestParams.get("estado"));
			}

			
			Result result = iMantRioService.obtenerMantRio(rioBean, paginacion);
			
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
	 * Este método inactiva el estado del Río
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdRio the inac id rio
	 * @return the string
	 * @RequestParam inacIdRio es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/rio/inactivar", method = RequestMethod.POST)
	public String inactivarRio(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdRio) {  

		String strMensajeTipo = "";
		String strMensajeError = "";

		
		
		RioBean rioBean = new RioBean();
		rioBean.setCodigo(inacIdRio);
		rioBean.setUsuarioModificacion(Util.getUserLoged());
		rioBean.setPrograma(Util.getPageCall());
		
		rioBean.setCodigoArea(0);
		rioBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {			

			iMantRioService.inactivarRio(rioBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTRIOS);
		return "rioSave :: mensajesSave";

	}
	
}
