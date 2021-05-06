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
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.services.IMantClimaService;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class ClimaController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ClimaController {

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
	
	/** The i mant clima service. */
	@Autowired
	IMantClimaService iMantClimaService;
	
	
	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Clima.
	 *
	 * @param request the request
	 * @param session the session
	 * @param climaSearchBean the clima search bean
	 * @param climaEditBean the clima edit bean
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("climaSearchBean") Objeto de tipo ClimaBean que se utiliza como atributo para la búsqueda de rios
	 * @ModelAttribute("climaEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/clima", method = RequestMethod.GET)
	public String clima(HttpServletRequest request, HttpSession session,
			@ModelAttribute("climaSearchBean") ClimaBean climaSearchBean,
			@ModelAttribute("climaEditBean") ClimaBean climaEditBean,
			ModelMap model){

		return "clima";
	}
	
	/**
	 * Método que permite obtener un Clima por id.
	 *
	 * @param climaSearchBean the clima search bean
	 * @param climaEditBean the clima edit bean
	 * @param session the session
	 * @param model the model
	 * @param idclima the idclima
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @ModelAttribute("climaSearchBean") Objeto de tipo ClimaBean que se utiliza como atributo para la búsqueda de climas
	 * @ModelAttribute("climaEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 */
	@RequestMapping(value = "/climaGet/{idclima}", method = RequestMethod.GET)
	public String tipoProcesoGetPage(
			@ModelAttribute("climaSearchBean") ClimaBean climaSearchBean,
			@ModelAttribute("climaEditBean") ClimaBean climaEditBean, 
			HttpSession session,
			Model model,
			@PathVariable Integer idclima) throws Exception {
		if(idclima!=null && idclima>0){
			climaEditBean = iMantClimaService.obtenerClima(idclima);
			session.setAttribute("climaForEdit", climaEditBean);
		}else{
			climaEditBean = new ClimaBean();  
		}
		model.addAttribute("climaEditBean", climaEditBean);
		return "climaEdit :: form-edit-clima";
	}
	
	/**
	 * Método que permite registrar y/o actualizar un Clima.
	 *
	 * @param climaSearchBean the clima search bean
	 * @param climaEditBean the clima edit bean
	 * @param session the session
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("climaSearchBean") Objeto de tipo ClimaBean que se utiliza como atributo para la búsqueda de climas
	 * @ModelAttribute("climaEditBean") Objeto de tipo ClimaBean que se utiliza como atributo para la actualización de climas
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/climaSave", method = RequestMethod.POST)
	public String actualizarclima(
			@Valid @ModelAttribute("climaSearchBean") ClimaBean climaSearchBean,
			@Valid @ModelAttribute("climaEditBean") ClimaBean climaEditBean,
			HttpSession session,
			BindingResult bindingResult, ModelMap model){
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		climaEditBean.setUsuarioCreacion(Util.getUserLoged());
		climaEditBean.setUsuarioModificacion(Util.getUserLoged());
		climaEditBean.setPrograma(Util.getPageCall());
		climaEditBean.setCodigoArea(0);
		climaEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		

		try {
			if(climaEditBean.getCodigo()!=null && !climaEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(climaEditBean.getCodigo())>0 ){
				ClimaBean climaEdit = (ClimaBean)session.getAttribute("climaForEdit");
				boolean successact = iMantClimaService.actualizarClima(climaEditBean, 
						!climaEdit.getAbreviatura().trim().toUpperCase().equals(climaEditBean.getAbreviatura().trim().toUpperCase()));	
				if(!successact){
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
					strMensajeError = Constants.VALIDACION_ABREVIATURA;
				}
				else{
					strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
				}

			}else{
				Integer estado = ConstantsCommon.ESTADO_ACTIVO;
				climaEditBean.setEstado(estado.toString());
				boolean successGrab = iMantClimaService.grabarClima(climaEditBean);	
				if(!successGrab){
					strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
					strMensajeError = Constants.VALIDACION_ABREVIATURA;
				}
				else{
					strMensajeTipo = ConstantsCommon.GRABADO_OK;
				}
			}
		} catch (Exception e) {
			if(climaEditBean.getCodigo()!=null && !climaEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(climaEditBean.getCodigo())>0 ){
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
		
		return "climaSave :: mensajesSave";
	}
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla paginada.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 */
	@RequestMapping(value = "/clima/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ClimaBean climaBean = new ClimaBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("nombreClima")!=null && !"".equals(allRequestParams.get("nombreClima"))) {
				climaBean.setNombreClima(allRequestParams.get("nombreClima").toUpperCase());
			}

			if(allRequestParams.get("abreviatura")!=null && !"".equals(allRequestParams.get("abreviatura"))) {
			   climaBean.setAbreviatura(allRequestParams.get("abreviatura").toUpperCase());
					}
			
			if(allRequestParams.get("estado")!=null && !"".equals(allRequestParams.get("estado"))) {
				climaBean.setEstado(allRequestParams.get("estado"));
				}

			
			Result result = iMantClimaService.obtenerMantClima(climaBean, paginacion);
			
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
	 * Este método inactiva el estado del Clima
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdclima the inac idclima
	 * @return the string
	 * @RequestParam inacIdclima es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/clima/inactivar", method = RequestMethod.POST)
	public String inactivarClima(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdclima) {

		String strMensajeTipo = "";
		String strMensajeError = "";

		
		
		ClimaBean climaBean = new ClimaBean();
		climaBean.setCodigo(inacIdclima);
		climaBean.setUsuarioModificacion(Util.getUserLoged());
		climaBean.setPrograma(Util.getPageCall());
		
		climaBean.setCodigoArea(0);
		climaBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));


		try {			

			iMantClimaService.inactivarClima(climaBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCLIMA);
		return "climaSave :: mensajesSave";

	}
	

}
	
