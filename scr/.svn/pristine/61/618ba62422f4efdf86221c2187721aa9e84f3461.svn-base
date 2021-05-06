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
import pe.com.sedapal.scr.core.beans.MantCorreoBean;
import pe.com.sedapal.scr.core.beans.MantCorreoEditBean;
import pe.com.sedapal.scr.core.beans.ValidaUnicidadCorreoBean;
import pe.com.sedapal.scr.core.services.IMantCorreoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class ConfiguracionCorreoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ConfiguracionCorreoController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The mant correo service. */
	@Autowired
	IMantCorreoService mantCorreoService;
	
	/** The env. */
	@Autowired
	Environment env;

	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Río.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/configuracionCorreo", method = RequestMethod.GET)
	public String contratos(HttpServletRequest request, HttpSession session,
			ModelMap model){
		return "configuracionCorreo";
	}
	
	/**
	 * Método que permite obtener un correo por id
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param mantCorreoEditBean the mant correo edit bean
	 * @param idMantCorreo the id mant correo
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @PathVariable  identifica al id como variable
	 */
	@RequestMapping(value = "/configuracionCorreo/get/{idMantCorreo}", method = RequestMethod.GET)
	public String MantenimientoCorreoGetPage(Model model,
			@ModelAttribute("mantCorreoEditBean") MantCorreoEditBean mantCorreoEditBean, 
			@PathVariable Integer idMantCorreo) throws Exception {
		
		if(idMantCorreo!=null && idMantCorreo>0){
			mantCorreoEditBean = mantCorreoService.getMantCorreo(idMantCorreo);
		}else{
			mantCorreoEditBean = new MantCorreoEditBean();  
		}
		model.addAttribute("mantCorreoEditBean", mantCorreoEditBean);
		return "configuracionCorreoEdit :: formEdit";
	}
	
	/**
	 * Método que permite validar que no hayan correos activos de cada tipo.
	 *
	 * @return Objeto de tipo ValidaUnicidadCorreoBean con la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/configuracionCorreo/getValidationInfo", method = RequestMethod.GET)
	public @ResponseBody ValidaUnicidadCorreoBean getValidationInfo() throws Exception {
		ValidaUnicidadCorreoBean validaUnicidad = mantCorreoService.getValidationData();
		return validaUnicidad;
	}
	
	/**
	 * Método que permite registrar y/o actualizar un Correo
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param mantCorreoEditBean the mant correo edit bean
	 * @return the string
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/configuracionCorreo/save", method = RequestMethod.POST)
	public String actualizarMantCorreo(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("mantCorreoEditBean") MantCorreoEditBean mantCorreoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";

		mantCorreoEditBean.setStrUsuIns(Util.getUserLoged());
		mantCorreoEditBean.setStrUsuUpd(Util.getUserLoged());
		mantCorreoEditBean.setStrPrograma(Util.getPageCall());
		mantCorreoEditBean.setLonCodArea(0L);
		mantCorreoEditBean.setLonCodSist(Long.parseLong(env.getProperty("parametro.sistema.codigo")));

		try {
			if(mantCorreoEditBean.getLonCodigo()!=null && mantCorreoEditBean.getLonCodigo()>0 ){
				mantCorreoService.actualizarMantCorreo(mantCorreoEditBean);
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}else{
				mantCorreoService.grabarMantCorreo(mantCorreoEditBean);		
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}
		} catch (Exception e) {
			if(mantCorreoEditBean.getLonCodigo()!=null && mantCorreoEditBean.getLonCodigo()>0 ){
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
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCORREOS);
		return "fragments/genericSave :: mensajesSave";
	}
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla paginada.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 */
	@RequestMapping(value = "/configuracionCorreo/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			MantCorreoBean mantCorreoBean = new MantCorreoBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codTipoOperacion")!=null && !"".equals(allRequestParams.get("codTipoOperacion"))){
				mantCorreoBean.setCodTipoOperacion(allRequestParams.get("codTipoOperacion"));
			}
			
			if(allRequestParams.get("estado")!=null && !"".equals(allRequestParams.get("estado"))){
				mantCorreoBean.setEstado(allRequestParams.get("estado"));
			}

			Result result = mantCorreoService.obtenerMantCorreo(mantCorreoBean, paginacion);
			
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
	 * Este método inactiva el estado del correo
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdMantCorreo the inac id mant correo
	 * @return the string
	 * @RequestParam inacIdMantCorreo es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/configuracionCorreo/inactivar", method = RequestMethod.POST)
	public String correoInactivar(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdMantCorreo) {

		String strMensajeTipo = "";
		String strMensajeError = "";

		MantCorreoBean mantCorreoBean = new MantCorreoBean();
		mantCorreoBean.setCodigo(inacIdMantCorreo);
		mantCorreoBean.setaUsuUpd(Util.getUserLoged());
		mantCorreoBean.setaPrograma(Util.getPageCall());
		mantCorreoBean.setLonCodArea(0L);
		mantCorreoBean.setLonCodSist(Long.parseLong(env.getProperty("parametro.sistema.codigo")));

		try {			
			mantCorreoService.inactivarCorreo(mantCorreoBean);			
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_MANTCORREOS);
		return "fragments/genericSave :: mensajesSave";

	}
	
}
