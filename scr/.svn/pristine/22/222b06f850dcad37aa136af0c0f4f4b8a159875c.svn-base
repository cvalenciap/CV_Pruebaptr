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
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IAlmacenamientoPTAPService;
import pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService;
import pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteAlmacenamientoPTAPController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteAlmacenamientoPTAPController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	/** The almacenamiento PTAP service. */
	@Autowired
	IAlmacenamientoPTAPService almacenamientoPTAPService;
	
	/** The analisis bacteriologico service. */
	@Autowired
	IAnalisisBacteriologicoService analisisBacteriologicoService;
	
	/** The reporte almacenamiento PTAP service. */
	@Autowired
	IReporteAlmacenamientoPTAPService reporteAlmacenamientoPTAPService;
	
	/**
	 * Reporte ensayo bacteriologico.
	 *
	 * @param request the request
	 * @param session the session
	 * @param almacenamientoPTAPSearchBean the almacenamiento PTAP search bean
	 * @param almacenamientoPTAPEditBean the almacenamiento PTAP edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/reportAlmacenamientoPTAP", method = RequestMethod.GET)
	public String reporteEnsayoBacteriologico(HttpServletRequest request, HttpSession session,
			@ModelAttribute("almacenamientoPTAPSearchBean") AlmacenamientoPTAPBean almacenamientoPTAPSearchBean,
			@ModelAttribute("almacenamientoPTAPEditBean") AnalisisBacteriologicoBean almacenamientoPTAPEditBean,
			ModelMap model){

		return "reporteAlmacenamientoPTAP/reporteAlmacenamientoPTAP";
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
	@RequestMapping(value = "/reporteAlmacenamientoPTAP/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			AlmacenamientoPTAPBean almacenamientoPTAPBean = new AlmacenamientoPTAPBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("fechaMuestreo")!=null && !"".equals(allRequestParams.get("fechaMuestreo"))){
				almacenamientoPTAPBean.setStrFechaMuestreo((String)allRequestParams.get("fechaMuestreo"));
			}else{
				almacenamientoPTAPBean.setStrFechaMuestreo("");
			}

			almacenamientoPTAPBean.setIntIdForm(ConstantsLaboratorio.PAR_NID_FORM_ALMACENAMIENTO_LTAP);
			
			Result result = almacenamientoPTAPService.obtenerDatosAlmacenamientoPTAP(almacenamientoPTAPBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla FORMULHEADER
	 * @ModelAttribute("analisisBacteriologicoSearchBean") Objeto de tipo AnalisisBacteriologicoBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("analisisBacteriologicoEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Reporte almacenamiento PTAP get page.
	 *
	 * @param analisisBacteriologicoSearchBean the analisis bacteriologico search bean
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reporteAlmacenamientoPTAPGet/{id}", method = RequestMethod.GET)
	public String reporteAlmacenamientoPTAPGetPage(
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				analisisBacteriologicoEditBean.setIntId(id);
				analisisBacteriologicoEditBean = analisisBacteriologicoService.obtenerAnalisisBacteriologico(id);
			}else{
				analisisBacteriologicoEditBean = new AnalisisBacteriologicoBean();  

			}
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_ANALISIS_BACT);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("analisisBacteriologicoEditBean", analisisBacteriologicoEditBean);
			return "reporteAlmacenamientoPTAP/reporteAlmacenamientoPTAPEdit :: form-edit-almacenamientoPTAP";
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
	 * Método que permite registrar y/o actualizar un registro de cabecera del Analisis Bacteriologico
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/reporteAlmacenamientoPTAPSave", method = RequestMethod.POST)
	public String insertAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
			
		analisisBacteriologicoEditBean.setUsuarioCreacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setUsuarioModificacion(Util.getUserLoged());
		int codigo = analisisBacteriologicoEditBean.getIntIdHide();
		try {
			
			analisisBacteriologicoEditBean.setPrograma(Constants.PROGRAMA_UPD_ANALBACT_RESULT2);
			analisisBacteriologicoEditBean.setIntId(analisisBacteriologicoEditBean.getIntIdHide());
			reporteAlmacenamientoPTAPService.actualizarReporteAlmacenamientoPTAPResul(analisisBacteriologicoEditBean);		
			
			strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		//return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_ALMAC_PTAP);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_ALMAC_PTAP);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_ANALISIS_BACT);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", analisisBacteriologicoEditBean.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}
	
	/**
	 * Obtener datos PTAP result.
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
	@RequestMapping(value = "/PTAPResult/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosPTAPResult(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean = new ReporteAlmacenamientoPTAPBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				reporteAlmacenamientoPTAPBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				reporteAlmacenamientoPTAPBean.setIntIdCabecera(0);
			}

			Result result = reporteAlmacenamientoPTAPService.obtenerDatosPTAPResult(reporteAlmacenamientoPTAPBean, paginacion);
			
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
	
	/*
	 * Método que permite obtener un registro de la tabla FORMULHEADER
	 * @ModelAttribute("analisisBacteriologicoSearchBean") Objeto de tipo AnalisisBacteriologicoBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("analisisBacteriologicoEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Reporte PTAP result table get page.
	 *
	 * @param pTAPResultTableSearchBean the TAP result table search bean
	 * @param pTAPResultTableEditBean the TAP result table edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/PTAPResultTable/{id}", method = RequestMethod.GET)
	public String reportePTAPResultTableGetPage(
			@ModelAttribute("pTAPResultTableSearchBean") ReporteAlmacenamientoPTAPBean pTAPResultTableSearchBean,
			@ModelAttribute("pTAPResultTableEditBean") ReporteAlmacenamientoPTAPBean pTAPResultTableEditBean, Model model,
			@PathVariable int id) throws Exception {
		
			if(id != -1){
				pTAPResultTableEditBean.setIntIdDetalle(id);
				pTAPResultTableEditBean = reporteAlmacenamientoPTAPService.obtenerDatosPTAPResultTable(pTAPResultTableEditBean);
			}
			model.addAttribute("pTAPResultTableEditBean", pTAPResultTableEditBean);
			return "reporteAlmacenamientoPTAP/reportePTAPResultTableEdit :: form-edit-PTAPResultTable";
	}
	
	/**
	 * Update PTAP result table.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param pTAPResultTableEditBean the TAP result table edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de la tabla ALC_MUESTRFOR299
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/PTAPResultTableSave", method = RequestMethod.POST)
	public String updatePTAPResultTable(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("pTAPResultTableEditBean") ReporteAlmacenamientoPTAPBean pTAPResultTableEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
			
		pTAPResultTableEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
			
			pTAPResultTableEditBean.setPrograma(Constants.PROGRAMA_UPD_MUESTRFOR299);
			reporteAlmacenamientoPTAPService.actualizarPTAPResultTable(pTAPResultTableEditBean);		
			
			strMensajeTipo = Constants.GRABADO_SUBFORM_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "reporteAlmacenamientoPTAP/reporteAlmacenamientoPTAPSave :: mensajesSave";
	}
	
}
