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
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ResultMuestraBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService;
import pe.com.sedapal.scr.core.services.IreporteEnsayoBacteriologicoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteEnsayoBacteriologicoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteEnsayoBacteriologicoController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	Environment env;
	
	/** The analisis bacteriologico service. */
	@Autowired
	IAnalisisBacteriologicoService analisisBacteriologicoService;
	
	/** The reporte ensayo bacteriologico service. */
	@Autowired
	IreporteEnsayoBacteriologicoService reporteEnsayoBacteriologicoService;
	
	/**
	 * Reporte ensayo bacteriologico.
	 *
	 * @param request the request
	 * @param session the session
	 * @param analisisBacteriologicoSearchBean the analisis bacteriologico search bean
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/reporteEnsayoBacteriologico", method = RequestMethod.GET)
	public String reporteEnsayoBacteriologico(HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean,
			ModelMap model){

		return "reporteEnsayoBacteriologico/reporteEnsayoBacteriologico";
	}
	
	/*
	 * Método que permite obtener un registro de la tabla de  Analisis Bacteriologico
	 * @ModelAttribute("analisisBacteriologicoSearchBean") Objeto de tipo AnalisisBacteriologicoBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("analisisBacteriologicoEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Analisis bacteriologico get page.
	 *
	 * @param analisisBacteriologicoSearchBean the analisis bacteriologico search bean
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reporteEnsayoBacteriologico/{id}", method = RequestMethod.GET)
	public String analisisBacteriologicoGetPage(
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				analisisBacteriologicoEditBean.setIntId(id);
				analisisBacteriologicoEditBean = analisisBacteriologicoService.obtenerAnalisisBacteriologico(id);
			}else{
				analisisBacteriologicoEditBean = new AnalisisBacteriologicoBean();  

			}
			
			LOG.info("analisisBacteriologicoEditBean: " +  analisisBacteriologicoEditBean);
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_ANALISIS_BACT);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("analisisBacteriologicoEditBean", analisisBacteriologicoEditBean);
			return "reporteEnsayoBacteriologico/reporteEnsayoBacteriologicoEdit :: form-edit-analisisBacteriologico";
	}
	
	/**
	 * Obtener datos tabla 1.
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
	@RequestMapping(value = "/planta1/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosTabla1(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ReporteEnsayoBacteriologicoBean reporteEnsayoBacteriologicoBean = new ReporteEnsayoBacteriologicoBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(0);
			}
			
			reporteEnsayoBacteriologicoBean.setIntTipo(ConstantsLaboratorio.PLANTA1);
			Result result = reporteEnsayoBacteriologicoService.obtenerDatosEnsayoBacteriologico(reporteEnsayoBacteriologicoBean, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", 1);
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
	 * Obtener datos tabla 2.
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
	@RequestMapping(value = "/planta2/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosTabla2(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ReporteEnsayoBacteriologicoBean reporteEnsayoBacteriologicoBean = new ReporteEnsayoBacteriologicoBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(0);
			}
			
			reporteEnsayoBacteriologicoBean.setIntTipo(ConstantsLaboratorio.PLANTA2);
			Result result = reporteEnsayoBacteriologicoService.obtenerDatosEnsayoBacteriologico(reporteEnsayoBacteriologicoBean, paginacion);
			
			LOG.error("PLANTA 2 : " + result.getData());
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", 1);
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
	 * Obtener datos eficiencia.
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
	@RequestMapping(value = "/eficiencia/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosEficiencia(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			ReporteEnsayoBacteriologicoBean reporteEnsayoBacteriologicoBean = new ReporteEnsayoBacteriologicoBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				reporteEnsayoBacteriologicoBean.setIntIdCabecera(0);
			}
			
			Result result = reporteEnsayoBacteriologicoService.obtenerDatosEficiencia(reporteEnsayoBacteriologicoBean, paginacion);
			
			LOG.error("EFICIENCIA : " + result.getData());
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", 1);
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
	 * Insert analisis bacteriologico.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @return the string
	 */
	/*
	 * Método que permite actualizar un registro de cabecera del Analisis Bacteriologico
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/analisisBacteriologicoResultSave", method = RequestMethod.POST)
	public String insertAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		analisisBacteriologicoEditBean.setIntEstado(Constants.ACTIVO);		
		analisisBacteriologicoEditBean.setUsuarioCreacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setUsuarioModificacion(Util.getUserLoged());
		int codigo = analisisBacteriologicoEditBean.getIntId();
		try {
			
			analisisBacteriologicoEditBean.setPrograma(Constants.PROGRAMA_UPD_ANALBACT);
			analisisBacteriologicoEditBean.setIntId(analisisBacteriologicoEditBean.getIntId());
			reporteEnsayoBacteriologicoService.actualizarAnalisisBacteriologicoResul(analisisBacteriologicoEditBean);		
			
			strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_ANALISIS_BACT);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_ANALISIS_BACT);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_ANALISIS_BACT);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", analisisBacteriologicoEditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}

}
