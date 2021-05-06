/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */


package pe.com.sedapal.scr.web.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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
import pe.com.sedapal.scr.core.beans.MuestraFirstBean;
import pe.com.sedapal.scr.core.beans.MuestraSecondBean;
import pe.com.sedapal.scr.core.beans.ResultMuestraBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisBacteriologicoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class AnalisisBacteriologicoController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	private Environment env;
	
	
	/** The analisis bacteriologico service. */
	@Autowired
	IAnalisisBacteriologicoService analisisBacteriologicoService;
	
	/**
	 * Analisis bacteriologico.
	 *
	 * @param request the request
	 * @param session the session
	 * @param analisisBacteriologicoSearchBean the analisis bacteriologico search bean
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/analisisBacteriologico", method = RequestMethod.GET)
	public String analisisBacteriologico(HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean,
			ModelMap model){

		return "analisisBacteriologico/analisisBacteriologico";
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
	@RequestMapping(value = "/analisisBacteriologico/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			AnalisisBacteriologicoBean analisisBacteriologicoBean = new AnalisisBacteriologicoBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("fechaMuestreo")!=null && !"".equals(allRequestParams.get("fechaMuestreo"))){
				analisisBacteriologicoBean.setStrFechaMuestreo((String)allRequestParams.get("fechaMuestreo"));
			}else{
				analisisBacteriologicoBean.setStrFechaMuestreo("");
			}

			analisisBacteriologicoBean.setIntIdForm(ConstantsLaboratorio.PAR_NID_FORM_ANALISIS_BACTERIOLOGICO_BOCATOMA);
			Result result = analisisBacteriologicoService.obtenerDatosEquipos(analisisBacteriologicoBean, paginacion);
			
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
	@RequestMapping(value = "/analisisBacteriologicoGet/{id}", method = RequestMethod.GET)
	public String analisisBacteriologicoGetPage(
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean, Model model,
			@PathVariable int id) throws Exception {
		try {
			
			if(id != -1){
				analisisBacteriologicoEditBean.setIntId(id);
				analisisBacteriologicoEditBean = analisisBacteriologicoService.obtenerAnalisisBacteriologico(id,ConstantsLaboratorio.TIPO_ANALISIS_BACT);
				
			}else{
				analisisBacteriologicoEditBean = new AnalisisBacteriologicoBean();  
				

			}
			LOG.info("analisisBacteriologicoEditBean: " + analisisBacteriologicoEditBean);
			
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_ANALISIS_BACT);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));			
			model.addAttribute("analisisBacteriologicoEditBean", analisisBacteriologicoEditBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			return "analisisBacteriologico/analisisBacteriologicoEdit :: form-edit-analisisBacteriologico";
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
	@RequestMapping(value = "/analisisBacteriologicoSave", method = RequestMethod.POST)
	public String insertAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		analisisBacteriologicoEditBean.setIntEstado(Constants.ACTIVO);		
		analisisBacteriologicoEditBean.setUsuarioCreacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setUsuarioModificacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setIntIdForm(ConstantsLaboratorio.PAR_NID_FORM_ANALISIS_BACTERIOLOGICO_BOCATOMA);
		int codigo = analisisBacteriologicoEditBean.getIntId();
		try {
			if(codigo<=0){				
				analisisBacteriologicoEditBean.setPrograma(Constants.PROGRAMA_INS_ANALBACT);
				codigo = analisisBacteriologicoService.grabarAnalisisBacteriologico(analisisBacteriologicoEditBean);
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}else{	
				analisisBacteriologicoEditBean.setPrograma(Constants.PROGRAMA_UPD_ANALBACT);
				analisisBacteriologicoEditBean.setIntId(analisisBacteriologicoEditBean.getIntIdHide());
				analisisBacteriologicoService.actualizarAnalisisBacteriologico(analisisBacteriologicoEditBean);	
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}
			
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
	
	/**
	 * Inactivar analisis bacteriologico.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla de cabecera de Analisis Bacteriologico
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/analisisBacteriologico/inactivar", method = RequestMethod.POST)
	public String inactivarAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		AnalisisBacteriologicoBean analisisBacteriologicoBean = new AnalisisBacteriologicoBean();
		
		analisisBacteriologicoBean.setIntEstado(Constants.INACTIVO);
		analisisBacteriologicoBean.setIntId(inacId);

		try {			

			analisisBacteriologicoService.inactivarAnalisisBacteriologico(analisisBacteriologicoBean);;
			strMensajeTipo = ConstantsCommon.BORRADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}	
	
	/**
	 * Obtener datos muestra first.
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
	@RequestMapping(value = "/muestraFirst/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosMuestraFirst(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			MuestraFirstBean muestraFirstBean = new MuestraFirstBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				muestraFirstBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				muestraFirstBean.setIntIdCabecera(0);
			}

			Result result = analisisBacteriologicoService.obtenerDatosMuestraFirst(muestraFirstBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de Primera muestra de Analisis Bacteriologico
	 * @ModelAttribute("analisisBacteriologicoSearchBean") Objeto de tipo AnalisisBacteriologicoBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("analisisBacteriologicoEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Muestra first get page.
	 *
	 * @param muestraFirstSearchBean the muestra first search bean
	 * @param muestraFirstEditBean the muestra first edit bean
	 * @param model the model
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/muestraFirstGet/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String muestraFirstGetPage(
			@ModelAttribute("muestraFirstSearchBean") MuestraFirstBean muestraFirstSearchBean,
			@ModelAttribute("muestraFirstEditBean") MuestraFirstBean muestraFirstEditBean, Model model,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) throws Exception {
		
			if(idDetalle != 2){
				muestraFirstEditBean.setIntIdCabecera(idCabecera);
				muestraFirstEditBean.setIntIdMuestraFirst(idDetalle);
				muestraFirstEditBean = analisisBacteriologicoService.obtenerMuestraFirst(muestraFirstEditBean);
			}else{
				muestraFirstEditBean = new MuestraFirstBean();  

			}
			model.addAttribute("muestraFirstEditBean", muestraFirstEditBean);
			return "analisisBacteriologico/muestraFirstEdit :: form-edit-muestraFirst";
	}
	
	/**
	 * Insert muestra first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param muestraFirstEditBean the muestra first edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de la primera muestra del Analisis Bacteriologico
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/muestraFirstSave", method = RequestMethod.POST)
	public String insertMuestraFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("muestraFirstEditBean") MuestraFirstBean muestraFirstEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
			
		muestraFirstEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
				
			muestraFirstEditBean.setPrograma(Constants.PROGRAMA_UPD_MUESTRAFIRST);
			analisisBacteriologicoService.actualizarMuestraFirst(muestraFirstEditBean);		
			
			//strMensajeTipo = ConstantsCommon.GRABADO_OK;
			strMensajeTipo = "actualizadoOkSubFirstForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
	
	/**
	 * Obtener datos muestra second.
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
	@RequestMapping(value = "/muestraSecond/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosMuestraSecond(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			MuestraSecondBean muestraFirstBean = new MuestraSecondBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				muestraFirstBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				muestraFirstBean.setIntIdCabecera(0);
			}

			Result result = analisisBacteriologicoService.obtenerDatosMuestraSecond(muestraFirstBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de Segunda muestra de Analisis Bacteriologico
	 * @ModelAttribute("muestraSecondSearchBean") Objeto de tipo MuestraSecondBean que se utiliza como atributo para la búsqueda en la tabla se segunda muestra de analisis bacteriologico
	 * @ModelAttribute("muestraSecondEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Muestra second get page.
	 *
	 * @param muestraSecondSearchBean the muestra second search bean
	 * @param muestraSecondEditBean the muestra second edit bean
	 * @param model the model
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/muestraSecondGet/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String muestraSecondGetPage(
			@ModelAttribute("muestraSecondSearchBean") MuestraSecondBean muestraSecondSearchBean,
			@ModelAttribute("muestraSecondEditBean") MuestraSecondBean muestraSecondEditBean, Model model,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) throws Exception {
		
			if(idDetalle != 2){
				muestraSecondEditBean.setIntIdCabecera(idCabecera);
				muestraSecondEditBean.setIntIdMuestraSecond(idDetalle);
				muestraSecondEditBean = analisisBacteriologicoService.obtenerMuestraSecond(muestraSecondEditBean);
			}else{
				muestraSecondEditBean = new MuestraSecondBean();  

			}
			model.addAttribute("muestraSecondEditBean", muestraSecondEditBean);
			return "analisisBacteriologico/muestraSecondEdit :: form-edit-muestraSecond";
	}
	
	/**
	 * Insert muestra second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param muestraSecondEditBean the muestra second edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de la segunda muestra del Analisis Bacteriologico
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/muestraSecondSave", method = RequestMethod.POST)
	public String insertMuestraSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("muestraSecondEditBean") MuestraSecondBean muestraSecondEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
			
		muestraSecondEditBean.setUsuarioModificacion(Util.getUserLoged());
		try {
				
			muestraSecondEditBean.setPrograma(Constants.PROGRAMA_UPD_MUESTRASECOND);
			analisisBacteriologicoService.actualizarMuestraSecond(muestraSecondEditBean);		
			
			//strMensajeTipo = ConstantsCommon.GRABADO_OK;
			strMensajeTipo = "actualizadoOkSubSecondForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
	
	/**
	 * Obtener datos result muestra first.
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
	@RequestMapping(value = "/muestraResultFirst/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosResultMuestraFirst(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			ResultMuestraBean resultMuestraBean = new ResultMuestraBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				resultMuestraBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				resultMuestraBean.setIntIdCabecera(0);
			}
			
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.PRIMERA_MUESTRA);
			Result result = analisisBacteriologicoService.obtenerDatosResultMuestra(resultMuestraBean, paginacion);
			
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
	 * Obtener datos result muestra second.
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
	@RequestMapping(value = "/muestraResultSecond/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosResultMuestraSecond(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			ResultMuestraBean resultMuestraBean = new ResultMuestraBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				resultMuestraBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				resultMuestraBean.setIntIdCabecera(0);
			}
			
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			Result result = analisisBacteriologicoService.obtenerDatosResultMuestra(resultMuestraBean, paginacion);
			
			LOG.info("result second: " + result);
			
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
	 * Calcula resultado muestra first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idCabecera the id cabecera
	 * @return the string
	 */
	/* 
	 * Este método ejecuta el calculo de la primera muestra de Analisis Bacteriologico
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/analisisBacteriologico/calculoFirst", method = RequestMethod.POST)
	public String calculaResultadoMuestraFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int idCabecera) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraFirstBean muestraFirstBean = new MuestraFirstBean();
		
		muestraFirstBean.setUsuarioCreacion(Util.getUserLoged());
		muestraFirstBean.setUsuarioModificacion(Util.getUserLoged());
		muestraFirstBean.setPrograma(Constants.PROGRAMA_UPD_CALCULOFIRST);
		
		muestraFirstBean.setIntIdCabecera(idCabecera);

		try {			

			int retorno = analisisBacteriologicoService.calculaResultadoMuestraFirst(muestraFirstBean);
			
			if(retorno == 1){
				strMensajeTipo = Constants.CALCULO_ERROR_DILUCION;
			}else if(retorno == 2){
				strMensajeTipo = Constants.CALCULO_ERROR_TUBOSCLVBB;
			}else if(retorno == 3){
				strMensajeTipo = Constants.CALCULO_ERROR_TUBOSEC;
			}else if(retorno == 4){
				strMensajeTipo = Constants.CALCULO_ERROR_AGARML;
			}else if(retorno == 5){
				strMensajeTipo = Constants.CALCULO_ERROR_OBTDILMEN;
			}else if(retorno == 6){
				strMensajeTipo = Constants.CALCULO_ERROR_CALCPOTENDISOL;
			}else{
				strMensajeTipo = Constants.CALCULO_FIRST_OK;
			}
				
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}	
	
	/**
	 * Calcula resultado muestra second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idCabecera2 the id cabecera 2
	 * @return the string
	 */
	/* 
	 * Este método ejecuta el calculo de la Segunda muestra de Analisis Bacteriologico
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/analisisBacteriologico/calculoSecond", method = RequestMethod.POST)
	public String calculaResultadoMuestraSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int idCabecera2) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraSecondBean muestraSecondBean = new MuestraSecondBean();
		
		muestraSecondBean.setUsuarioCreacion(Util.getUserLoged());
		muestraSecondBean.setUsuarioModificacion(Util.getUserLoged());
		muestraSecondBean.setPrograma(Constants.PROGRAMA_UPD_CALCULOSECOND);
		
		muestraSecondBean.setIntIdCabecera(idCabecera2);

		try {			

			int retorno = analisisBacteriologicoService.calculaResultadoMuestraSecond(muestraSecondBean);
			if(retorno == -100){
				strMensajeTipo = Constants.CALCULO_ERROR_NUMERO_ALTO;
			}if(retorno == -102){
				strMensajeTipo = Constants.CALCULO_ERROR_CALC_COLIF_TOT; //CALCULAR COLIFOR. TOTALES
			}if(retorno == -103){
				strMensajeTipo = Constants.CALCULO_ERROR_CALC_COLIF_TERMOT; //CALCULAR COLIFOR. TERMOT
			}if(retorno == -104){
				strMensajeTipo = Constants.CALCULO_ERROR_CALC_BACTER_HETEROT; //CALCULAR BACTER. HETEROT
			}else{
				strMensajeTipo = Constants.CALCULO_SECOND_OK;
			}

			
			
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}	
	
	/**
	 * Adds the duplicado muestra first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @param idDetalleDupl the id detalle dupl
	 * @return the string
	 */
	/* 
	 * Este método duplica un registro seleccionado
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/addDuplicadoMuestraFirst/{idDetalle}/{idCabecera}/{idDetalleDupl}", method = RequestMethod.GET)
	public String addDuplicadoMuestraFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera,
			@PathVariable int idDetalleDupl) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraFirstBean muestraFirstBean = new MuestraFirstBean();
		
		muestraFirstBean.setUsuarioCreacion(Util.getUserLoged());
		muestraFirstBean.setUsuarioModificacion(Util.getUserLoged());
		muestraFirstBean.setPrograma(Constants.PROGRAMA_UPD_DUPLIC_MUESTRAFIRST);
		
		muestraFirstBean.setIntIdCabecera(idCabecera);
		muestraFirstBean.setIntIdMuestraFirst(idDetalle);
		muestraFirstBean.setIntIdDuplicadoMuestraFirst(idDetalleDupl);

		try {			

			int retorno = analisisBacteriologicoService.addDuplicadoMuestraFirst(muestraFirstBean);
			
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
	
	/**
	 * Delete duplicado muestra first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro duplicado
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/deleteDuplicadoMuestraFirst/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String deleteDuplicadoMuestraFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraFirstBean muestraFirstBean = new MuestraFirstBean();
		
		muestraFirstBean.setUsuarioCreacion(Util.getUserLoged());
		muestraFirstBean.setUsuarioModificacion(Util.getUserLoged());
		muestraFirstBean.setPrograma(Constants.PROGRAMA_DEL_DUPLIC_MUESTRAFIRST);
		
		muestraFirstBean.setIntIdCabecera(idCabecera);
		muestraFirstBean.setIntIdMuestraFirst(idDetalle);

		try {			

			int retorno = analisisBacteriologicoService.deleteDuplicadoMuestraFirst(muestraFirstBean);
			
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
	
	/**
	 * Adds the duplicado muestra second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @param idDetalleDupl the id detalle dupl
	 * @return the string
	 */
	/* 
	 * Este método duplica un registro seleccionado
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/addDuplicadoMuestraSecond/{idDetalle}/{idCabecera}/{idDetalleDupl}", method = RequestMethod.GET)
	public String addDuplicadoMuestraSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera,
			@PathVariable int idDetalleDupl) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraSecondBean muestraSecondBean = new MuestraSecondBean();
		
		muestraSecondBean.setUsuarioCreacion(Util.getUserLoged());
		muestraSecondBean.setUsuarioModificacion(Util.getUserLoged());
		muestraSecondBean.setPrograma(Constants.PROGRAMA_UPD_DUPLIC_MUESTRASECOND);
		
		muestraSecondBean.setIntIdCabecera(idCabecera);
		muestraSecondBean.setIntIdMuestraSecond(idDetalle);
		muestraSecondBean.setIntIdDuplicadoMuestraSecond(idDetalleDupl);

		try {			

			int retorno = analisisBacteriologicoService.addDuplicadoMuestraSecond(muestraSecondBean);
			
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
	
	/**
	 * Delete duplicado muestra second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro duplicado
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/deleteDuplicadoMuestraSecond/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String deleteDuplicadoMuestraSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		MuestraSecondBean muestraSecondBean = new MuestraSecondBean();
		
		muestraSecondBean.setUsuarioCreacion(Util.getUserLoged());
		muestraSecondBean.setUsuarioModificacion(Util.getUserLoged());
		muestraSecondBean.setPrograma(Constants.PROGRAMA_DEL_DUPLIC_MUESTRASECOND);
		
		muestraSecondBean.setIntIdCabecera(idCabecera);
		muestraSecondBean.setIntIdMuestraSecond(idDetalle);

		try {			

			int retorno = analisisBacteriologicoService.deleteDuplicadoMuestraSecond(muestraSecondBean);
			
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_KO;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "analisisBacteriologico/analisisBacteriologicoSave :: mensajesSave";
	}
}
