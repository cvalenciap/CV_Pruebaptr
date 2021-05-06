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
import pe.com.sedapal.scr.core.beans.FormularioDetalle222;
import pe.com.sedapal.scr.core.beans.FormularioHeader222;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IFormulario222Service;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;



// TODO: Auto-generated Javadoc
/**
 * The Class Formulario222Controller.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class Formulario222Controller {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	/** The iformulario 222 service. */
	@Autowired
	IFormulario222Service iformulario222Service;
	
	/**
	 * Formulario 222.
	 *
	 * @param request the request
	 * @param session the session
	 * @param formularioHeader222 the formulario header 222
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/formulario222", method = RequestMethod.GET)
	public String formulario222(HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioHeader222") FormularioHeader222 formularioHeader222,
			ModelMap model){

		return "formulario222/formulario222";
	}
	

	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario222/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			FormularioHeader222 formularioHeader222 = new FormularioHeader222();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("pFecha")!=null && !"".equals(allRequestParams.get("pFecha"))){ //campo codigo viene desde turbidimetroDigitalTable
				formularioHeader222.setStrfechaMuestreo(allRequestParams.get("pFecha"));
			}

			Result result =   iformulario222Service.getListadoFormularioHeader(formularioHeader222, paginacion);
			
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
	 * Formulario 222 get.
	 *
	 * @param formularioHeader222 the formulario header 222
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario222Get/{id}", method = RequestMethod.GET)
	public String formulario222Get(
			@ModelAttribute("formularioHeader222") FormularioHeader222 formularioHeader222, Model model,
			@PathVariable int id) throws Exception {
		
		    int cantidad = 0;
		    
			if(id != -1){
				formularioHeader222 = iformulario222Service.getFormularioHeader(id);
				cantidad = iformulario222Service.registrosPorHeader(id);
				
			}else{
				formularioHeader222 = new FormularioHeader222();  
				formularioHeader222.setCodigoHeader(0);
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_CADE_CUST);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("cantidad", cantidad);
			model.addAttribute("formularioHeader222", formularioHeader222);
			return "formulario222/formulario222Edit :: form-edit-form222";
	}
	
	
	/**
	 * Formulario detalle get 222.
	 *
	 * @param formularioHeader222 the formulario header 222
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDetalleGet222/{id}", method = RequestMethod.GET)
	public String formularioDetalleGet222(
			@ModelAttribute("formularioHeader222") FormularioHeader222 formularioHeader222, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				formularioHeader222 = iformulario222Service.getFormularioHeader(id);
			}else{
				formularioHeader222 = new FormularioHeader222();  
			}
			model.addAttribute("formularioHeader222", formularioHeader222);
			return "formulario222/formulario222HDetalle :: form-edit-Detalle222";
	}
	
	
	/**
	 * Formulario header save 222.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formularioHeader222EditBean the formulario header 222 edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/formularioHeaderSave222", method = RequestMethod.POST)
	public String formularioHeaderSave222(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioHeader222EditBean") FormularioHeader222 formularioHeader222EditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		int codigo = formularioHeader222EditBean.getCodigoHeader();
        try{
        	formularioHeader222EditBean.setPrograma(Util.getPageCall());
        	formularioHeader222EditBean.setUsuarioModificacion(Util.getUserLoged());
        	if(codigo<=0){			         
		         formularioHeader222EditBean.setUsuarioCreacion(Util.getUserLoged());
		         codigo = iformulario222Service.grabarFormularioHeader(formularioHeader222EditBean);		         
        	}else {        		
        		iformulario222Service.updateFormularioHeader(formularioHeader222EditBean);		
        	}
        	
        	strMensajeTipo = ConstantsCommon.GRABADO_OK;
        	
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "formulario222/formulario222Save :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_CADE_CUST);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_CADE_CUST);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_CADE_CUST);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", formularioHeader222EditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	/**
	 * Formulario header save 222 in detail.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formularioHeader222EditBean the formulario header 222 edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/formularioHeaderSave222InDetail", method = RequestMethod.POST)
	public String formularioHeaderSave222InDetail(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioHeader222EditBean") FormularioHeader222 formularioHeader222EditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		int codigo = formularioHeader222EditBean.getCodigoHeader()!=null?formularioHeader222EditBean.getCodigoHeader():0;
        try{
        	if(codigo<=0){	
		         formularioHeader222EditBean.setPrograma(Util.getPageCall());
		         formularioHeader222EditBean.setUsuarioCreacion(Util.getUserLoged());
		         formularioHeader222EditBean.setUsuarioModificacion(Util.getUserLoged());
		
		         codigo = iformulario222Service.grabarFormularioHeader(formularioHeader222EditBean);							
		         
        	}else {
        		
        		iformulario222Service.updateFormularioHeader(formularioHeader222EditBean);		
        	}
        	
        	strMensajeTipo = "grabadoOkInDetail";
        	
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("codigo", codigo);
		return "formulario222/formulario222Save :: mensajesSave";
	}
	
	/**
	 * Formulario detalle save 222.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formularioDetalle222 the formulario detalle 222
	 * @return the string
	 */
	@RequestMapping(value = "/formularioDetalleSave222", method = RequestMethod.POST)
	public String formularioDetalleSave222(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formularioDetalle222") FormularioDetalle222 formularioDetalle222){

		String strMensajeTipo = "";
		String strMensajeError = "";

		formularioDetalle222.setPrograma(Util.getPageCall());
		formularioDetalle222.setUsuarioCreacion(Util.getUserLoged());
		formularioDetalle222.setUsuarioModificacion(Util.getUserLoged());
		
		formularioDetalle222.setAl2(formularioDetalle222.isAl() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS); 
		formularioDetalle222.setAs2(formularioDetalle222.isAs() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS); 
		formularioDetalle222.setBa2(formularioDetalle222.isBa() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setCd2(formularioDetalle222.isCd() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS); 
		formularioDetalle222.setCr2(formularioDetalle222.isCr() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS); 
		formularioDetalle222.setCu2(formularioDetalle222.isCu() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setFe2(formularioDetalle222.isFe() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setHg2(formularioDetalle222.isHg() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setK2(formularioDetalle222.isK()   ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS); 
		formularioDetalle222.setMn2(formularioDetalle222.isMn() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setNa2(formularioDetalle222.isNa() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setPb2(formularioDetalle222.isPb() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSb2(formularioDetalle222.isSb() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSe2(formularioDetalle222.isSe() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setZn2(formularioDetalle222.isZn() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		
		formularioDetalle222.setThm2(formularioDetalle222.isThm() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setToc2(formularioDetalle222.isToc() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setDqo2(formularioDetalle222.isDqo() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setDeterg2(formularioDetalle222.isDeterg() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setFenoles2(formularioDetalle222.isFenoles() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		
		formularioDetalle222.setNo22(formularioDetalle222.isNo2() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setNo32(formularioDetalle222.isNo3() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setPo42(formularioDetalle222.isPo4() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSdt2(formularioDetalle222.isSdt() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSt2(formularioDetalle222.isSt() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSst2(formularioDetalle222.isSst() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setDt2(formularioDetalle222.isDt() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setCa2(formularioDetalle222.isCa() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setMg2(formularioDetalle222.isMg() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setAlc2(formularioDetalle222.isAlc() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setCl2(formularioDetalle222.isCl() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setSo42(formularioDetalle222.isSo4() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setColor2(formularioDetalle222.isColor() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setF2(formularioDetalle222.isF() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		formularioDetalle222.setCn2(formularioDetalle222.isCn() ? ConstantsLaboratorio.ACTIVOS : ConstantsLaboratorio.INACTIVOS);
		
		
		
				
		try {
			
			if(formularioDetalle222.getAccion().equals("S")){	
				
				iformulario222Service.grabarFormularioDetalle(formularioDetalle222);	

			}else{
			   
				iformulario222Service.updateFormularioDetalle(formularioDetalle222);
			}	
			
			strMensajeTipo = "grabadoOkSubForm";
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "formulario222/formulario222Save :: mensajesSave";
	}
	

	/**
	 * Obtener datos verificacion.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDetalle222/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosVerificacion(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {

			FormularioDetalle222 formularioDetalle222 = new FormularioDetalle222();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codigo")!=null && !"0".equals(allRequestParams.get("codigo")) && !"".equals(allRequestParams.get("codigo"))){
				formularioDetalle222.setIdHeader(Integer.parseInt(allRequestParams.get("codigo")));
			}

			Result result = iformulario222Service.getListadoFormularioDetalle(formularioDetalle222, paginacion);
			
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
	 * Formulario detalle get edit 222.
	 *
	 * @param formularioDetalle222 the formulario detalle 222
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDetalleGetEdit222/{id}", method = RequestMethod.GET)
	public String formularioDetalleGetEdit222(
			@ModelAttribute("formularioDetalle222") FormularioDetalle222 formularioDetalle222, 
			Model model, @PathVariable int id) throws Exception {
		
		if(id != -1){
			
			formularioDetalle222 = iformulario222Service.getFormularioDetalle(id);
			
		}else {
			
			formularioDetalle222 = new FormularioDetalle222(); 
		}
				
		model.addAttribute("formularioDetalle222", formularioDetalle222);
		return "formulario222/formulario222RDetalle :: form-edit-RDetalleEdit";
	}
	
	
	/**
	 * Inactivar formulario detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param iddetalle_inac222 the iddetalle inac 222
	 * @return the string
	 */
	@RequestMapping(value = "/formularioDetalle222/inactivar", method = RequestMethod.POST)
	public String inactivarFormularioDetalle(ModelMap model, HttpServletRequest request, HttpSession session, 
			@RequestParam int iddetalle_inac222) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		FormularioDetalle222 formularioDetalle222 = new FormularioDetalle222(); 
		formularioDetalle222.setStatus(0);
		formularioDetalle222.setIdDetalle(iddetalle_inac222);

		try {			
			iformulario222Service.inactivarFormularioDetalle(formularioDetalle222);
			strMensajeTipo = "inactivadoOkSubForm";
			
			model.addAttribute("mensajeTipo", "inactivadoOkSubForm");
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulario222/formulario222Save :: mensajesSave";
	}
	
}
