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
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.services.IMantColaboradorService;
import pe.com.sedapal.scr.web.common.Util;



// TODO: Auto-generated Javadoc
/**
 * The Class ColaboradorController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ColaboradorController {

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
	
	/** The i mant colaborador service. */
	@Autowired
	IMantColaboradorService iMantColaboradorService;
	
	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Colaborador.
	 *
	 * @param request the request
	 * @param session the session
	 * @param colaboradorSearchBean the colaborador search bean
	 * @param colaboradorEditBean the colaborador edit bean
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("colaboradorSearchBean") Objeto de tipo ColaboradorBean que se utiliza como atributo para la búsqueda de colaborador
	 * @ModelAttribute("colaboradorEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/colaborador", method = RequestMethod.GET)
	public String contratos(HttpServletRequest request, HttpSession session,
			@ModelAttribute("colaboradorSearchBean") ColaboradorBean colaboradorSearchBean,
			@ModelAttribute("colaboradorEditBean") ColaboradorBean colaboradorEditBean,
			ModelMap model){
		
		return "colaborador";
	}
	
	/**
	 * Método que permite obtener un Colaborador por id.
	 *
	 * @param colaboradorEditBean the colaborador edit bean
	 * @param session the session
	 * @param model the model
	 * @param idcolaborador the idcolaborador
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @ModelAttribute("colaboradorSearchBean") Objeto de tipo ColaboradorBean que se utiliza como atributo para la búsqueda de colaborador
	 * @ModelAttribute("colaboradorEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * Model es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifica al id como variable
	 */
	@RequestMapping(value = "/colaboradorGet/{idcolaborador}", method = RequestMethod.GET)
	public String colaboradorGetPage(
			@ModelAttribute("colaboradorEditBean") ColaboradorBean colaboradorEditBean,HttpSession session, Model model,
			@PathVariable Integer idcolaborador) throws Exception {
		if(idcolaborador!=null && idcolaborador>0){
			colaboradorEditBean = iMantColaboradorService.obtenerColaborador(idcolaborador);
			session.setAttribute("colaboradorForEdit", colaboradorEditBean);
		}else{
			colaboradorEditBean = new ColaboradorBean();  
		}
		model.addAttribute("colaboradorEditBean", colaboradorEditBean);
		return "colaboradorEdit :: form-edit-colaborador";
	}
	
	
	/**
	 * Método que permite registrar y/o actualizar un Colaborador.
	 *
	 * @param colaboradorSearchBean the colaborador search bean
	 * @param colaboradorEditBean the colaborador edit bean
	 * @param session the session
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("colaboradorSearchBean") Objeto de tipo ColaboradorBean que se utiliza como atributo para la búsqueda de colaborador
	 * @ModelAttribute("colaboradorEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
		@RequestMapping(value = "/colaboradorSave", method = RequestMethod.POST)
		public String actualizarColaborador(
				@Valid @ModelAttribute("colaboradorSearchBean") ColaboradorBean colaboradorSearchBean,
				@Valid @ModelAttribute("colaboradorEditBean") ColaboradorBean colaboradorEditBean,
				HttpSession session,
				BindingResult bindingResult, ModelMap model){
			
			String strMensajeTipo = "";
			String strMensajeError = "";

			colaboradorEditBean.setUsuarioCreacion(Util.getUserLoged());
			colaboradorEditBean.setUsuarioModificacion(Util.getUserLoged());
			colaboradorEditBean.setPrograma(Util.getPageCall());
			
			colaboradorEditBean.setCodigoArea(0);
			colaboradorEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

			try {
				if(colaboradorEditBean.getCodigo()!=null && !colaboradorEditBean.getCodigo().trim().equals("")
						&& Integer.parseInt(colaboradorEditBean.getCodigo())>0 ){
					ColaboradorBean colaboradorEdit = (ColaboradorBean)session.getAttribute("colaboradorForEdit");
					boolean successact = iMantColaboradorService.actualizarColaborador(colaboradorEditBean, 
							!colaboradorEdit.getAbreviatura().trim().toUpperCase().equals(colaboradorEditBean.getAbreviatura().trim().toUpperCase()));	
					if(!successact){
						strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
						strMensajeError = Constants.VALIDACION_ABREVIATURA;
					}
					else{
						strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
					}

				}else{
					Integer estado = ConstantsCommon.ESTADO_ACTIVO;
					colaboradorEditBean.setEstado(estado.toString());
					boolean successGrab = iMantColaboradorService.grabarColaborador(colaboradorEditBean);	
					if(!successGrab){
						strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
						strMensajeError = Constants.VALIDACION_ABREVIATURA;
					}
					else{
						strMensajeTipo = ConstantsCommon.GRABADO_OK;
					}
				}
			} catch (Exception e) {
				if(colaboradorEditBean.getCodigo()!=null && !colaboradorEditBean.getCodigo().trim().equals("")
						&& Integer.parseInt(colaboradorEditBean.getCodigo())>0 ){
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
			
			return "colaboradorSave :: mensajesSave";
		}

		/**
		 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla páginada.
		 *
		 * @param allRequestParams the all request params
		 * @return the response entity
		 * @throws Exception the exception
		 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
		 */
		@RequestMapping(value = "/colaborador/pagination", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
				throws Exception {
			try {
				ColaboradorBean colaboradorBean = new ColaboradorBean();
				Paginacion paginacion = new Paginacion();
				paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
				paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
				paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
				paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
				
				if(allRequestParams.get("nombreColaborador")!=null && !"".equals(allRequestParams.get("nombreColaborador"))) {
					colaboradorBean.setNombreColaborador(allRequestParams.get("nombreColaborador").toUpperCase());
				}
				
				if(allRequestParams.get("nombreEmpresa")!=null && !"".equals(allRequestParams.get("nombreEmpresa"))) {
					colaboradorBean.setNombreEmpresa(allRequestParams.get("nombreEmpresa").toUpperCase());
				}

				if(allRequestParams.get("tipoTrabajador")!=null && !"".equals(allRequestParams.get("tipoTrabajador"))) {
					colaboradorBean.setTipoTrabajador(allRequestParams.get("tipoTrabajador"));
				}
				
				if(allRequestParams.get("estado")!=null && !"".equals(allRequestParams.get("estado"))) {
					colaboradorBean.setEstado(allRequestParams.get("estado"));
				}
				
				Result result = iMantColaboradorService.obtenerMantColaborador(colaboradorBean, paginacion);
				
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
		 * Este método inactiva el estado del Colaborador
		 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
		 *
		 * @param model the model
		 * @param request the request
		 * @param session the session
		 * @param inacIdColaborador the inac id colaborador
		 * @return the string
		 * @RequestParam inacIdColaborador es el parámetro que sirve para cambiar de estado
		 */
		@RequestMapping(value = "/colaborador/inactivar", method = RequestMethod.POST)
		public String inactivarColaborador(ModelMap model, HttpServletRequest request, HttpSession session,
				@RequestParam String inacIdColaborador) {
			
			String strMensajeTipo = "";
			String strMensajeError = "";

			ColaboradorBean colaboradorBean = new ColaboradorBean();
			colaboradorBean.setCodigo(inacIdColaborador);
			colaboradorBean.setUsuarioModificacion(Util.getUserLoged());
			colaboradorBean.setPrograma(Util.getPageCall());
			
			colaboradorBean.setCodigoArea(0);
			colaboradorBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

			try {			

				iMantColaboradorService.inactivarColaborador(colaboradorBean);
				strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
			} catch (Exception e) {
				strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
				strMensajeError = e.getMessage();
				e.printStackTrace();
			}
			
			model.addAttribute("mensajeTipo", strMensajeTipo);
			model.addAttribute("mensajeError", strMensajeError);
			model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCOLABORADOR);
			return "colaboradorSave :: mensajesSave";

		}
		
	
		
		/**
		 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla páginada.
		 *
		 * @param allRequestParams Se encarga de enviar los parámetros para los filtro de búsqueda
		 * @return the response entity
		 * @throws Exception the exception
		 */
	@RequestMapping(value = "/trabajador/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> obtenerDatosTrabajador(
			@RequestParam Map<String, String> allRequestParams) throws Exception {
		try {
			TrabajadorBean trabajadorBean = new TrabajadorBean();
			
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby(Integer.parseInt(allRequestParams.get("order[0][column]")) == 0 ? Integer.parseInt(allRequestParams.get("order[0][column]")) + 1 : Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));

			if (allRequestParams.get("numFichaTra") != null && !"".equals(allRequestParams.get("numFichaTra"))) {
				trabajadorBean.setNumFicha(allRequestParams.get("numFichaTra"));
			}

			if (allRequestParams.get("nombreTra") != null && !"".equals(allRequestParams.get("nombreTra"))) {
				trabajadorBean.setNombreCompleto(allRequestParams.get("nombreTra").trim().toUpperCase());
			}

			Result result = iMantColaboradorService.obtenerListaDeTrabajadores(trabajadorBean, paginacion);

			Map<String, Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());

			return new ResponseEntity<Map<String, Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Método que permite obtener un Trabajador por número de ficha.
	 *
	 * @param numFicha  Es el número de ficha que se desea filtrar
	 * @return the trabajador by ficha
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/trabajador/{numFicha}", method = RequestMethod.GET)
	public @ResponseBody TrabajadorBean getTrabajadorByFicha(@PathVariable Integer numFicha) throws Exception {
		TrabajadorBean trabajador = new TrabajadorBean();
		if(numFicha!=null && numFicha>0){
			trabajador = iMantColaboradorService.obtenerTrabajador(numFicha);
		}
		return trabajador;
	}

}
