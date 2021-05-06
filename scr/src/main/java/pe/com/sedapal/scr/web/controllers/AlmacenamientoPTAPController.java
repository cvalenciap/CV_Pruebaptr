/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPSecondBean;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPThreeBean;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IAlmacenamientoPTAPService;
import pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class AlmacenamientoPTAPController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class AlmacenamientoPTAPController {

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
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	/**
	 * Analisis bacteriologico.
	 *
	 * @param request the request
	 * @param session the session
	 * @param almacenamientoPTAPSearchBean the almacenamiento PTAP search bean
	 * @param almacenamientoPTAPEditBean the almacenamiento PTAP edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/almacenamientoPTAP", method = RequestMethod.GET)
	public String analisisBacteriologico(HttpServletRequest request, HttpSession session,
			@ModelAttribute("almacenamientoPTAPSearchBean") AlmacenamientoPTAPBean almacenamientoPTAPSearchBean,
			@ModelAttribute("almacenamientoPTAPEditBean") AlmacenamientoPTAPBean almacenamientoPTAPEditBean,
			ModelMap model){

		return "almacenamientoPTAP/almacenamientoPTAP";
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
	@RequestMapping(value = "/almacenamientoPTAP/pagination", method = RequestMethod.POST)
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
	 * Método que permite obtener un registro de la tabla de  Almacenamiento PTAP
	 * @ModelAttribute("analisisBacteriologicoSearchBean") Objeto de tipo AnalisisBacteriologicoBean que se utiliza como atributo para la búsqueda en la tabla PHmetro Digital
	 * @ModelAttribute("analisisBacteriologicoEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Almacenamiento PTAP get page.
	 *
	 * @param analisisBacteriologicoSearchBean the analisis bacteriologico search bean
	 * @param analisisBacteriologicoEditBean the analisis bacteriologico edit bean
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/almacenamientoPTAPGet/{id}", method = RequestMethod.GET)
	public String almacenamientoPTAPGetPage(
			@ModelAttribute("analisisBacteriologicoSearchBean") AnalisisBacteriologicoBean analisisBacteriologicoSearchBean,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean, Model model,
			@PathVariable int id) throws Exception {
		
		  System.out.println("id:  " + id);
			if(id != -1){
				analisisBacteriologicoEditBean.setIntId(id);
				analisisBacteriologicoEditBean = analisisBacteriologicoService.obtenerAnalisisBacteriologico(id);
			}else{
				analisisBacteriologicoEditBean = new AnalisisBacteriologicoBean();  

			}
			
			LOG.info("analisisBacteriologicoEditBean: " + analisisBacteriologicoEditBean);
			
			model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_ANALISIS_BACT);
			model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
			model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
			model.addAttribute("analisisBacteriologicoEditBean", analisisBacteriologicoEditBean);
			return "almacenamientoPTAP/almacenamientoPTAPEdit :: form-edit-almacenamientoPTAP";
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
	@RequestMapping(value = "/almacenamientoPTAPSave", method = RequestMethod.POST)
	public String insertAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("analisisBacteriologicoEditBean") AnalisisBacteriologicoBean analisisBacteriologicoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		analisisBacteriologicoEditBean.setIntEstado(Constants.ACTIVO);		
		analisisBacteriologicoEditBean.setUsuarioCreacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setUsuarioModificacion(Util.getUserLoged());
		analisisBacteriologicoEditBean.setIntIdForm(ConstantsLaboratorio.PAR_NID_FORM_ALMACENAMIENTO_LTAP);
		int codigo = analisisBacteriologicoEditBean.getIntId();
		try {
			if(codigo<=0){				
				analisisBacteriologicoEditBean.setPrograma(Constants.PROGRAMA_INS_ANALBACT);
				codigo=analisisBacteriologicoService.grabarAnalisisBacteriologico(analisisBacteriologicoEditBean);
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
	 * Inactivar analisis bacteriologico.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacId the inac id
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla de cabecera de Almacenamiento de la PTAP
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/almacenamientoPTAP/inactivar", method = RequestMethod.POST)
	public String inactivarAnalisisBacteriologico(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacId) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		AnalisisBacteriologicoBean analisisBacteriologicoBean = new AnalisisBacteriologicoBean();
		
		analisisBacteriologicoBean.setIntEstado(Constants.INACTIVO);
		analisisBacteriologicoBean.setIntId(inacId);

		try {			

			analisisBacteriologicoService.inactivarAnalisisBacteriologico(analisisBacteriologicoBean);;
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}
	
	/**
	 * Obtener datos first table.
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
	@RequestMapping(value = "/tableFirst/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosFirstTable(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean = new AlmacenamientoPTAPFirstBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(10);
			paginacion.setPagdesde(0);
			paginacion.setColorderby(10);
			paginacion.setColorderbydir("asc");
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				almacenamientoPTAPFirstBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				almacenamientoPTAPFirstBean.setIntIdCabecera(0);
			}

			Result result = almacenamientoPTAPService.obtenerDatosTableFirst(almacenamientoPTAPFirstBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de MuestraForm21
	 * @ModelAttribute("almacenamientoPTAPFirstSearchBean") Objeto de tipo AlmacenamientoPTAPFirstBean que se utiliza como atributo para la búsqueda en la tabla MuestraForm21
	 * @ModelAttribute("almacenamientoPTAPFirstEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Muestra first get page.
	 *
	 * @param almacenamientoPTAPFirstSearchBean the almacenamiento PTAP first search bean
	 * @param almacenamientoPTAPFirstEditBean the almacenamiento PTAP first edit bean
	 * @param model the model
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/tableFirstGet/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String muestraFirstGetPage(
			@ModelAttribute("almacenamientoPTAPFirstSearchBean") AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstSearchBean,
			@ModelAttribute("almacenamientoPTAPFirstEditBean") AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstEditBean, Model model,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) throws Exception {
		
			if(idDetalle != 2){
				almacenamientoPTAPFirstEditBean.setIntIdCabecera(idCabecera);
				almacenamientoPTAPFirstEditBean.setIntIdDetalle(idDetalle);
				almacenamientoPTAPFirstEditBean = almacenamientoPTAPService.obtenerTableFirst(almacenamientoPTAPFirstEditBean);
			}else{
				almacenamientoPTAPFirstEditBean = new AlmacenamientoPTAPFirstBean();  

			}
			model.addAttribute("almacenamientoPTAPFirstEditBean", almacenamientoPTAPFirstEditBean);
			return "almacenamientoPTAP/almacenamientoFirstTableEdit :: form-edit-tableFirst";
	}
	
	/**
	 * Insert table first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param almacenamientoPTAPFirstEditBean the almacenamiento PTAP first edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro en la tabla MuestraForm21
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/tableFirstSave", method = RequestMethod.POST)
	public String insertTableFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("almacenamientoPTAPFirstEditBean") AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		try{
			almacenamientoPTAPFirstEditBean.setUsuarioModificacion(Util.getUserLoged());	
			almacenamientoPTAPFirstEditBean.setPrograma(Constants.PROGRAMA_UPD_MUESTRFORM21);
			
			almacenamientoPTAPService.updateMuestraForm21(almacenamientoPTAPFirstEditBean);
			
			strMensajeTipo = "actualizadoOkFirstForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}
	
	/**
	 * Obtener datos second table.
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
	@RequestMapping(value = "/tableSecond/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosSecondTable(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondBean = new AlmacenamientoPTAPSecondBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				almacenamientoPTAPSecondBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				almacenamientoPTAPSecondBean.setIntIdCabecera(0);
			}

			Result result = almacenamientoPTAPService.obtenerDatosTableSecond(almacenamientoPTAPSecondBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de Muestelef21
	 * @ModelAttribute("almacenamientoPTAPSecondSearchBean") Objeto de tipo AlmacenamientoPTAPSecondBean que se utiliza como atributo para la búsqueda en la tabla Muestelef21
	 * @ModelAttribute("almacenamientoPTAPSecondEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Muestra second get page.
	 *
	 * @param almacenamientoPTAPSecondSearchBean the almacenamiento PTAP second search bean
	 * @param almacenamientoPTAPSecondEditBean the almacenamiento PTAP second edit bean
	 * @param model the model
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/tableSecondGet/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String muestraSecondGetPage(
			@ModelAttribute("almacenamientoPTAPSecondSearchBean") AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondSearchBean,
			@ModelAttribute("almacenamientoPTAPSecondEditBean") AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondEditBean, Model model,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) throws Exception {
				
				almacenamientoPTAPSecondEditBean = new AlmacenamientoPTAPSecondBean(); 
				almacenamientoPTAPSecondEditBean.setIntIdCabecera(idCabecera);
				 

			
			model.addAttribute("almacenamientoPTAPSecondEditBean", almacenamientoPTAPSecondEditBean);
			return "almacenamientoPTAP/almacenamientoSecondTableEdit :: form-edit-tableSecond";
	}
	
	/**
	 * Insert table second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param almacenamientoPTAPSecondEditBean the almacenamiento PTAP second edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro en la tabla Muestelef21
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/tableSecondSave", method = RequestMethod.POST)
	public String insertTableSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("almacenamientoPTAPSecondEditBean") AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		try{
			almacenamientoPTAPSecondEditBean.setUsuarioModificacion(Util.getUserLoged());	
			almacenamientoPTAPSecondEditBean.setPrograma(Constants.PROGRAMA_INS_MUESTELEF21);
			
			almacenamientoPTAPService.grabarTableSecond(almacenamientoPTAPSecondEditBean);
			
			strMensajeTipo = "grabadoOkSecondForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}
	
	/**
	 * Inactivar tabla second.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdDetalle2 the inac id detalle 2
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla de Muestelef21
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/tableSecond/inactivar", method = RequestMethod.POST)
	public String inactivarTablaSecond(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacIdDetalle2) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondBean = new AlmacenamientoPTAPSecondBean();
		
		almacenamientoPTAPSecondBean.setIntEstado(Constants.INACTIVO);
		almacenamientoPTAPSecondBean.setIntIdDetalle(inacIdDetalle2);

		try {			

			almacenamientoPTAPService.inactivarTableSecond(almacenamientoPTAPSecondBean);;
			strMensajeTipo = "inactivadoOkSecondForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}	
	
	/**
	 * Obtener datos three table.
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
	@RequestMapping(value = "/tableThree/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatosThreeTable(@RequestParam Map<String,String> allRequestParams) throws Exception {
		try {
			AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeBean = new AlmacenamientoPTAPThreeBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("idCabecera")!=null && !"".equals(allRequestParams.get("idCabecera"))){
				almacenamientoPTAPThreeBean.setIntIdCabecera(Integer.parseInt(allRequestParams.get("idCabecera")));
			}else{
				almacenamientoPTAPThreeBean.setIntIdCabecera(0);
			}

			Result result = almacenamientoPTAPService.obtenerDatosTableThree(almacenamientoPTAPThreeBean, paginacion);
			
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
	 * Método que permite obtener un registro de la tabla de Muestepaf21
	 * @ModelAttribute("almacenamientoPTAPSecondSearchBean") Objeto de tipo AlmacenamientoPTAPSecondBean que se utiliza como atributo para la búsqueda en la tabla Muestelef21
	 * @ModelAttribute("almacenamientoPTAPSecondEditBean") Esta clase sirve como soporte genérico de modelo para Servlet 
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifíca al id como variable
	 * @throws Exception Excepción que puede ser lanzada
	 * */
		
	/**
	 * Muestra three get page.
	 *
	 * @param almacenamientoPTAPThreeSearchBean the almacenamiento PTAP three search bean
	 * @param almacenamientoPTAPThreeEditBean the almacenamiento PTAP three edit bean
	 * @param model the model
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/tableThreeGet/{idDetalle}/{idCabecera}", method = RequestMethod.GET)
	public String muestraThreeGetPage(
			@ModelAttribute("almacenamientoPTAPThreeSearchBean") AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeSearchBean,
			@ModelAttribute("almacenamientoPTAPThreeEditBean") AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeEditBean, Model model,
			@PathVariable int idDetalle,
			@PathVariable int idCabecera) throws Exception {
				
				almacenamientoPTAPThreeEditBean = new AlmacenamientoPTAPThreeBean(); 
				almacenamientoPTAPThreeEditBean.setIntIdCabecera(idCabecera);
				
			model.addAttribute("almacenamientoPTAPThreeEditBean", almacenamientoPTAPThreeEditBean);
			return "almacenamientoPTAP/almacenamientoThreeTableEdit :: form-edit-tableThree";
	}
	
	/**
	 * Insert table three.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param almacenamientoPTAPThreeEditBean the almacenamiento PTAP three edit bean
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro en la tabla Muestepaf21
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/tableThreeSave", method = RequestMethod.POST)
	public String insertTableThree(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("almacenamientoPTAPSecondEditBean") AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		try{
			almacenamientoPTAPThreeEditBean.setUsuarioModificacion(Util.getUserLoged());	
			almacenamientoPTAPThreeEditBean.setPrograma(Constants.PROGRAMA_INS_MUESTEPAF21);
			
			almacenamientoPTAPService.grabarTableThree(almacenamientoPTAPThreeEditBean);
			
			strMensajeTipo = "grabadoOkThreeForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}
	
	/**
	 * Inactivar tabla three.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdDetalle the inac id detalle
	 * @return the string
	 */
	/* 
	 * Este método elimina un registro de tabla de Muestepaf21
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/tableThree/inactivar", method = RequestMethod.POST)
	public String inactivarTablaThree(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int inacIdDetalle) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeBean = new AlmacenamientoPTAPThreeBean();
		
		almacenamientoPTAPThreeBean.setIntEstado(Constants.INACTIVO);
		almacenamientoPTAPThreeBean.setIntIdDetalle(inacIdDetalle);

		try {			

			almacenamientoPTAPService.inactivarTableThree(almacenamientoPTAPThreeBean);;
			strMensajeTipo = "inactivadoOkThreeForm";
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}	
	
	/**
	 * Calcular tabla first.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param idDetalle the id detalle
	 * @param idCabecera the id cabecera
	 * @return the string
	 */
	/* 
	 * Este método calcula un proceso para cada registro de tabla de Muestelef21
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista
	 * @param HttpServletRequest solicita la solicitud de envío a la vista
	 * @param HttpSession se utiliza para almacenar información entre diferentes peticiones HTTP. 
	 * @RequestParam inacId es el parámetro que sirve para cambiar de estado 
	 * */
	@RequestMapping(value = "/tableFirst/calcular", method = RequestMethod.POST)
	public String calcularTablaFirst(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam int idDetalle,
			@RequestParam int idCabecera) {

		String strMensajeTipo = "";
		String strMensajeError = "";		
		
		AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean = new AlmacenamientoPTAPFirstBean();
		
		almacenamientoPTAPFirstBean.setIntIdCabecera(idCabecera);
		almacenamientoPTAPFirstBean.setIntIdDetalle(idDetalle);

		try {			

			int retorno = almacenamientoPTAPService.calcularTablaFirst(almacenamientoPTAPFirstBean);
			if(retorno == 1){
				strMensajeTipo = Constants.CALCULO_ERROR_AGELFI;
			}else if(retorno == 2){
				strMensajeTipo = Constants.CALCULO_ERROR_AG2AML;
			}else{
				strMensajeTipo = Constants.CALCULO_FIRST_OK;
			}
		} catch (Exception e) {
			strMensajeTipo = Constants.CALCULO_ERROR;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "almacenamientoPTAP/almacenamientoPTAPSave :: mensajesSave";
	}
	
	/**
	 * Find grupo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPuntoMuestreoSeleccione", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findGrupo() throws Exception {
		
		List<SelectItemBean> listItems = new ArrayList<>();			
		listItems = this.catalogoService.obtenerCatalogoTodosNinguno(6, 3);

		return listItems;
	}
}
