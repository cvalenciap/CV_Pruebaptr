/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.beans.FilaReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.Formulario314;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IFormulario314Service;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;



// TODO: Auto-generated Javadoc
/**
 * The Class Formulario314Controller.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class Formulario314Controller {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	/** The iformulario 314 service. */
	@Autowired
	IFormulario314Service iformulario314Service;
	
	/**
	 * Formulario 058.
	 *
	 * @param request the request
	 * @param session the session
	 * @param formulario314 the formulario 314
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/formulario314", method = RequestMethod.GET)
	public String formulario058(HttpServletRequest request, HttpSession session,
			@ModelAttribute("formulario314") Formulario314 formulario314,
			ModelMap model){

		return "formulario314/formulario314";
	}
	

	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario314/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			Formulario314 formulario314 = new Formulario314();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			

			if(allRequestParams.get("pFechaIni") == null || allRequestParams.get("pFechaIni").trim().equals("")){
				formulario314.setStrFechaIni("31/12/2999");
			}else{
				formulario314.setStrFechaIni(allRequestParams.get("pFechaIni"));
			}
			
			if(allRequestParams.get("pFechaFin") == null || allRequestParams.get("pFechaFin").trim().equals("")){
				formulario314.setStrFechaFin("31/12/2999");
			}else{
				formulario314.setStrFechaFin(allRequestParams.get("pFechaFin"));
			}
			
			if(allRequestParams.get("pTipo")!=null && !"".equals(allRequestParams.get("pTipo"))){ //campo codigo viene desde turbidimetroDigitalTable
				formulario314.setTipoEnsayo(Integer.parseInt(allRequestParams.get("pTipo")));
				
			}else{
				formulario314.setTipoEnsayo(0);
			}
			
			Result result =   iformulario314Service.getListadoFormulario314(formulario314, paginacion);
			
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
	 * Formulario 314 get.
	 *
	 * @param formulario314 the formulario 314
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario314Get/{id}", method = RequestMethod.GET)
	public String formulario314Get(
			@ModelAttribute("formulario314") Formulario314 formulario314, Model model,
			@PathVariable int id) throws Exception {
		
		    int cantidad = 0;
		    
			if(id != -1){
				formulario314 = iformulario314Service.getFormulario314(id);
				
			}else{
				formulario314 = new Formulario314();
				formulario314.setIdformulario314(0);
			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_ESTI_LIMI);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("formulario314", formulario314);
			return "formulario314/formulario314Edit :: form-edit-form314";
	}

	
	/**
	 * Formulario save 314.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param formulario314 the formulario 314
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping(value = "/formularioSave314", method = RequestMethod.POST)
	public String formularioSave314(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("formulario314") Formulario314 formulario314, HttpServletResponse response){

		String strMensajeTipo = "";
		String strMensajeError = "";
		Double a, b, c;
		Double rsd;
		int codigo = formulario314.getIdformulario314();
        try{
        	
        	a = formulario314.getResultado1();
    		b = formulario314.getResultado2();
    		c = Math.abs(a - b);
    		rsd = (c * 100 * Math.sqrt(2)) / (a + b);
    		LOG.info("RSD: " + rsd);
    		formulario314.setRsd(rsd);
    		
        	if(formulario314.getOpcion().equals("S")  ){
        		
        		formulario314.setPrograma(Util.getPageCall());
        		formulario314.setUsuarioCreacion(Util.getUserLoged());
        		formulario314.setUsuarioModificacion(Util.getUserLoged());
        		codigo = iformulario314Service.grabarFormulario314(formulario314);		
        		if(codigo == -100){
        			strMensajeTipo = ConstantsLaboratorio.DUPLICADO;
        			model.addAttribute("mensajeTipo", strMensajeTipo);
					model.addAttribute("mensajeError", "");        			
        			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return "formulario314/formulario314Save :: mensajesSave";
        		}else{
        			strMensajeTipo = ConstantsCommon.GRABADO_OK;
        		}
       
        	}else {
        		
        		formulario314.setStrFecha(formulario314.getStrFechaHidden());
        		formulario314.setTipoEnsayo(formulario314.getTipoEnsayoHidden());
        		iformulario314Service.updateFormulario314(formulario314);		
        		strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
        	}
        	
        	
        	
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "formulario314/formulario314Save :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_ESTI_LIMI);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_ESTI_LIMI);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_ESTI_LIMI);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", formulario314.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}


	
	/**
	 * Inactivar formulario detalle.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	@RequestMapping(value = "/formulario314/inactivar", method = RequestMethod.POST)
	public String inactivarFormularioDetalle(ModelMap model, HttpServletRequest request, HttpSession session, 
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		Formulario314 formulario314 = new Formulario314(); 
		formulario314.setStatus(0);
		formulario314.setIdformulario314(inacId);

		try {			
			iformulario314Service.inactivarForm314(formulario314);
			strMensajeTipo = "inactivadoOk";
			
//			model.addAttribute("mensajeTipo", "inactivadoOkSubForm");
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulario314/formulario314Save :: mensajesSave";
		
	}
		
	/**
	 * Gets the reporte represas.
	 *
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the reporte represas
	 */
	@RequestMapping(value = "/calculoFormulario314", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getReporteRepresas(Model model, @RequestParam Map<String,String> allRequestParams) {
		Formulario314 formulario314 = new Formulario314(); 
		
		if(allRequestParams.get("strFechaIni") == null || allRequestParams.get("strFechaIni").trim().equals("")){
			formulario314.setStrFechaIni("31/12/2999");
		}else{
			formulario314.setStrFechaIni(allRequestParams.get("strFechaIni"));
		}
		
		if(allRequestParams.get("strFechaFin") == null || allRequestParams.get("strFechaFin").trim().equals("")){
			formulario314.setStrFechaFin("31/12/2999");
		}else{
			formulario314.setStrFechaFin(allRequestParams.get("strFechaFin"));
		}
		
		if(allRequestParams.get("cboTipoEnsayo")!=null && !"".equals(allRequestParams.get("cboTipoEnsayo"))){ //campo codigo viene desde turbidimetroDigitalTable
			formulario314.setTipoEnsayo(Integer.parseInt(allRequestParams.get("cboTipoEnsayo")));
			
		}else{
			formulario314.setTipoEnsayo(0);
		}
		
		
		Formulario314 result;
		try {
			result = iformulario314Service.getCalculoFormulario314(formulario314);
		

			List<Formulario314> lisFormulario314 = new ArrayList<>();
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("DESVIACI\u00D3N");
			formulario314.setDescripcion("DESVIACI\u00D3N ESTANDAR");
			formulario314.setCalculo(result.getValor1());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("MEDIA");
			formulario314.setDescripcion("L\u00CDNEA CENTRAL");
			formulario314.setCalculo(result.getValor2());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("LCS");
			formulario314.setDescripcion("L\u00CDMITE DE CONTROL SUPERIOR");
			formulario314.setCalculo(result.getValor3());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("LCAS");
			formulario314.setDescripcion("L\u00CDMITE DE CONTROL DE ALERTA SUPERIOR");
			formulario314.setCalculo(result.getValor4());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("LCAI");
			formulario314.setDescripcion("L\u00CDMITE DE CONTROL DE ALERTA INFERIOR");
			formulario314.setCalculo(result.getValor5());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setAbreviatura("LCI");
			formulario314.setDescripcion("L\u00CDMITE DE CONTROL INFERIOR");
			formulario314.setCalculo(result.getValor6());
			lisFormulario314.add(formulario314);
			
			formulario314 = new Formulario314();
			formulario314.setListFormulario314(lisFormulario314);				
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(formulario314, HttpStatus.OK);
	} 
	
}
