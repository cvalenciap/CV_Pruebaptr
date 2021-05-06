/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.FormularioDetalle242;
import pe.com.sedapal.scr.core.beans.FormularioHeader242;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IFormulario242Service;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class Formulario242Controller.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class Formulario242Controller {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
		
	/** The iformulario 242 service. */
	@Autowired
	IFormulario242Service iformulario242Service;

	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/**
	 * Turbidimetro digital.
	 *
	 * @param request the request
	 * @param session the session
	 * @param formularioHeader242EditBean the formulario header 242 edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/formulario242", method = RequestMethod.GET)
	public String turbidimetroDigital(HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioHeader242EditBean") FormularioHeader242 formularioHeader242EditBean,
			ModelMap model){

		return "formulario242/formulario242";
	}
	

	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @param request the request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario242/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams,  HttpServletRequest request)
			throws Exception {
		try {
			FormularioHeader242 formularioHeader242 = new FormularioHeader242();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("pFecha")!=null && !"".equals(allRequestParams.get("pFecha"))){ 
				formularioHeader242.setStrFechaMuestreo(allRequestParams.get("pFecha"));
			}
			formularioHeader242.setCodare((Integer)request.getSession().getAttribute(pe.com.sedapal.scr.core.common.Constants.USR_CODARE));
			Result result = iformulario242Service.getListadoFormularioHeader(formularioHeader242, paginacion);
			
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
	 * Turbidimetro digital get page.
	 *
	 * @param formularioHeader242SearchBean the formulario header 242 search bean
	 * @param formularioHeader242EditBean the formulario header 242 edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario242Get/{id}", method = RequestMethod.GET)
	public String turbidimetroDigitalGetPage(
			@ModelAttribute("formularioHeader242SearchBean") FormularioHeader242 formularioHeader242SearchBean,
			@ModelAttribute("formularioHeader242EditBean") FormularioHeader242 formularioHeader242EditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id>0){
				formularioHeader242EditBean = iformulario242Service.getFormularioHeader(id);
			}else{
				formularioHeader242EditBean = new FormularioHeader242();  
				formularioHeader242EditBean.setIdHeader(0);
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_PARAM_CAMPO);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("formularioHeader242EditBean", formularioHeader242EditBean);
			
			if(id<=0){
				return "formulario242/formulario242Insert :: form-edit-form242";
			}
			
			return "formulario242/formulario242Edit :: form-edit-form242";
	}
	
	
	/**
	 * Turbidimetro digital verificacion get page.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDetalleGet/{id}", method = RequestMethod.GET)
	public String turbidimetroDigitalVerificacionGetPage(
			@ModelAttribute("formularioDetalle242") FormularioDetalle242 formularioDetalle242, Model model,
			@PathVariable int id) throws Exception {
		
		if(id>0){
			formularioDetalle242 = iformulario242Service.getFormularioDetalle(id);
		}
		else {
			//INSERT
		    formularioDetalle242 = new FormularioDetalle242();  
		    formularioDetalle242.setIdDetalle(0);
		}
		
		LOG.info("formularioDetalle242 obtenido para id: " + formularioDetalle242);
		
		model.addAttribute("formularioDetalle242", formularioDetalle242);
		return "formulario242/formularioDetalle242Edit :: form-edit-detalle";
	}
	
	
	/**
	 * Formulario duplicar.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDuplicar/{id}", method = RequestMethod.GET)
	public String formularioDuplicar(HttpServletRequest request, HttpSession session, Model model, @PathVariable int id) throws Exception {
		String mensajeTipo = Constants.GRABADO_OK;
	    String mensajeMostrar = obtenerMensaje("mensaje.duplicado.ok");
		
	    try {
			
		FormularioHeader242 formularioHeader242EditBean = iformulario242Service.getFormularioHeader(id);

		formularioHeader242EditBean.setPrograma(Util.getPageCall());
		formularioHeader242EditBean.setUsuarioCreacion(Util.getUserLoged());
		formularioHeader242EditBean.setUsuarioModificacion(Util.getUserLoged());
		formularioHeader242EditBean.setCodare((Integer)request.getSession().getAttribute(pe.com.sedapal.scr.core.common.Constants.USR_CODARE));
		
			Paginacion paginacion  = new Paginacion();
			paginacion.setColorderby(1);
			paginacion.setColorderbydir(ConstantsLaboratorio.DESC);
			paginacion.setPagdesde(0);
			paginacion.setCantidadpag(1000);// no habra' mas de 1000 detalles a clonar
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date(); 
			String fechaHoy = df.format(today);
			formularioHeader242EditBean.setStrFechaMuestreo(fechaHoy);
			int idForHeaderDetails;
			try {
			 idForHeaderDetails = iformulario242Service.grabarFormularioHeader(formularioHeader242EditBean);
			LOG.info("idForHeaderDetails: " + idForHeaderDetails);
			} catch(Exception ex) {

				model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_NO_OK);
				model.addAttribute(Constants.MENSAJE_ERROR, obtenerMensaje("mensaje.duplicado.no.ok"));
				return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
			}
			
			FormularioDetalle242 detalle = new FormularioDetalle242();
			detalle.setCodigoHeader(id);
			LOG.info("id header a duplicar "+ id);
			//obtenemos detalles
			List<FormularioDetalle242> lista =   iformulario242Service.getListadoFormularioDetalleDuplicar(detalle, paginacion);
			LOG.info("lista: "+ lista);
			
			for (FormularioDetalle242 formularioDetalle2422 : lista) {
				
				formularioDetalle2422.setCodigoHeader(idForHeaderDetails);
				formularioDetalle2422.setPrograma(Util.getPageCall());
				formularioDetalle2422.setUsuarioCreacion(Util.getUserLoged());
				formularioDetalle2422.setUsuarioModificacion(Util.getUserLoged());
				iformulario242Service.grabarFormularioDetalle(formularioDetalle2422);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			mensajeMostrar = obtenerMensaje("mensaje.duplicado.no.ok");
		}  
		model.addAttribute(Constants.MENSAJE_TIPO, mensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR, mensajeMostrar);
		
		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
	}
	
	/**
	 * Insert turbidimetro digital.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formularioHeader242EditBean the formulario header 242 edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/formularioHeaderSave", method = RequestMethod.POST)
	public String insertTurbidimetroDigital(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioHeader242EditBean") FormularioHeader242 formularioHeader242EditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		formularioHeader242EditBean.setPrograma(Util.getPageCall());
		formularioHeader242EditBean.setUsuarioCreacion(Util.getUserLoged());
		formularioHeader242EditBean.setUsuarioModificacion(Util.getUserLoged());
		formularioHeader242EditBean.setCodare((Integer)request.getSession().getAttribute(pe.com.sedapal.scr.core.common.Constants.USR_CODARE));
		int codigo = formularioHeader242EditBean.getIdHeader();
		try {
			if(codigo<=0){				
				codigo = iformulario242Service.grabarFormularioHeader(formularioHeader242EditBean);	
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{
				LOG.info("fechamuestreo : " + formularioHeader242EditBean.getStrFechaMuestreo());
				iformulario242Service.updateFormularioHeader(formularioHeader242EditBean);
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
			
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = String.format(obtenerMensaje("mensaje.form242.existente"),formularioHeader242EditBean.getStrFechaMuestreo());
			model.addAttribute("mensajeTipo", strMensajeTipo);
			model.addAttribute("mensajeError", strMensajeError);
			return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "formulario242/formulario242Save :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_PARAM_CAMPO);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_PARAM_CAMPO);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_PARAM_CAMPO);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", formularioHeader242EditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	
	/**
	 * Insert turbidimetro digital verificacion.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formularioDetalle242 the formulario detalle 242
	 * @return the string
	 */
	@RequestMapping(value = "/formularioDetalleSave", method = RequestMethod.POST)
	public String insertTurbidimetroDigitalVerificacion(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioDetalle242") FormularioDetalle242 formularioDetalle242){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		formularioDetalle242.setPrograma(Util.getPageCall());
		formularioDetalle242.setUsuarioCreacion(Util.getUserLoged());
		formularioDetalle242.setUsuarioModificacion(Util.getUserLoged());
		
		try {
			if(formularioDetalle242.getIdDetalle()<=0){	
				iformulario242Service.grabarFormularioDetalle(formularioDetalle242);
				strMensajeTipo = "grabadoOkSubForm";
			}else{	
				iformulario242Service.updateFormularioDetalle(formularioDetalle242);
				strMensajeTipo = "actualizadoOkSubForm";
			}
			
			
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "formulario242/formulario242Save :: mensajesSave";
	}
	

	/**
	 * Obtener datos verificacion.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDetalle242/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosVerificacion(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {

			FormularioDetalle242 formularioDetalle242 = new FormularioDetalle242();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"0".equals(allRequestParams.get("codigo")) && !"".equals(allRequestParams.get("codigo"))){

				formularioDetalle242.setCodigoHeader(Integer.parseInt(allRequestParams.get("codigo")));
			}

			Result result = iformulario242Service.getListadoFormularioDetalle(formularioDetalle242, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			if(result != null){				
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
			}
			
			return new ResponseEntity<Map<String,Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {			
			LOG.error(e.getMessage(), e);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Inactivar formulario detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inactivarDetalleID the inactivar detalle ID
	 * @return the string
	 */
	/* 
//	 * Este método elimina un registro de tabla Verificacion de equipos del formulario hijo
//	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
//	 * @param HttpServletRequest solicita la solicitud de envío a la vista
//	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
//	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
//	 * */
	@RequestMapping(value = "/formularioDetalle/inactivar", method = RequestMethod.POST)
	public String inactivarFormularioDetalle(ModelMap model, HttpServletRequest request, HttpSession session, 
			@RequestParam int inactivarDetalleID) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		FormularioDetalle242 formularioDetalle242 = new FormularioDetalle242(); 
		formularioDetalle242.setEstado(0);
		formularioDetalle242.setIdDetalle(inactivarDetalleID);

		try {			
			iformulario242Service.inactivarFormularioDetalle(formularioDetalle242);
			strMensajeTipo = "inactivarIdDetalle";
			//strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulario242/formulario242Save :: mensajesSave";
	}
	
	/**
	 * Inactivar formulario header.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdHeader the inac id header
	 * @return the string
	 */
	@RequestMapping(value = "/formularioHeader/inactivar", method = RequestMethod.POST)
	public String inactivarFormularioHeader(ModelMap model, HttpServletRequest request, HttpSession session, 
			@RequestParam int inacIdHeader) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		FormularioHeader242 formularioHeader242 = new FormularioHeader242(); 
		formularioHeader242.setEstado(0);
		formularioHeader242.setIdHeader(inacIdHeader);
		formularioHeader242.setUsuarioCreacion(Util.getUserLoged());
		formularioHeader242.setUsuarioModificacion(Util.getUserLoged());
		try {			
			iformulario242Service.inactivarFormularioHeader(formularioHeader242);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
			model.addAttribute("mensajeTipo", "inactivadoOkSubForm");
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulario242/formulario242Save :: mensajesSave";
	}
	
	/**
	 * Obtener mensaje.
	 *
	 * @param messageProp the message prop
	 * @return the string
	 */
	public String obtenerMensaje(String messageProp){
		return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
	}
	
		
}
