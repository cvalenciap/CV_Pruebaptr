/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
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
import pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico;
import pe.com.sedapal.scr.core.beans.AnalisisHidroBiologicoMain;
import pe.com.sedapal.scr.core.beans.ReporteAnalisisHidro;
import pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHidroBiologicoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class AnalisisHidroBiologicoController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AnalisisHidroBiologicoController.class);
	
	/** The Constant BEAN_MAIN_ANALISISHIDRO. */
	private static final String  BEAN_MAIN_ANALISISHIDRO= "analisisHidroBiologicoMain";
	
	/** The Constant BEAN_MAIN_REPORTEHIDRO. */
	private static final String  BEAN_MAIN_REPORTEHIDRO= "reporteAnalisisHidroMain";
	
	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The analisis hibro biologico service. */
	@Autowired
	IAnalisisHibroBiologicoService analisisHibroBiologicoService;
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
//	analisisHidroBiologico/analisisHidroBiologicoTable :: resultsList
	
	/**
 * Cargar organismos.
 *
 * @param request the request
 * @param session the session
 * @param analisisHidroBiologicoMain the analisis hidro biologico main
 * @param model the model
 * @param allRequestParams the all request params
 * @return the string
 */
@RequestMapping(value = "/cargarOrganismos", method = RequestMethod.GET)
	public String cargarOrganismos(HttpServletRequest request, HttpSession session,
			@ModelAttribute(BEAN_MAIN_ANALISISHIDRO) AnalisisHidroBiologicoMain analisisHidroBiologicoMain,
			Model model,@RequestParam Map<String,String> allRequestParams){
		
		Integer puntoMuestra = Integer.parseInt(allRequestParams.get("puntoMuestra"));
		String fechaAnalisis = allRequestParams.get("fechaAnalisis");
		String tipoOrganismo = allRequestParams.get("tipoOrganismo");
		
		StringBuilder log = new StringBuilder();
		log.append("tipoOrganismo: ").append(tipoOrganismo).append("; fechaAnalisis: ").append(fechaAnalisis).append("; puntoMuestra: ").append(puntoMuestra);
		String rastros = log.toString();
		LOG.info(rastros);
		try {
			
		
		List<SelectItemBean> listCatalogo = catalogoService.obtenerCatalogo(Integer.parseInt(tipoOrganismo));
		
		List<AnalisisHidroBiologico> detalles =  analisisHibroBiologicoService.obtenerAnalisisPorParametros(fechaAnalisis, puntoMuestra, tipoOrganismo);
		Map<Integer, AnalisisHidroBiologico> mapAnalisis =  mapeoAnalisisHidroBiologico(detalles);
		
		ArrayList<AnalisisHidroBiologico> listAnalisisHidroBiologico = new ArrayList<>();
		
		AnalisisHidroBiologico analisisHidroBiologico;
		for (SelectItemBean selectItemBean : listCatalogo) {
			
			analisisHidroBiologico = mapAnalisis.get(Integer.parseInt(selectItemBean.getId()));
			if(analisisHidroBiologico !=null){
				listAnalisisHidroBiologico.add(analisisHidroBiologico);
			}else{
				analisisHidroBiologico = new AnalisisHidroBiologico();
				analisisHidroBiologico.setCodorg(Integer.parseInt(selectItemBean.getId()));
				analisisHidroBiologico.setDescripcion(selectItemBean.getLabel());
				analisisHidroBiologico.setCantidad("0");
				listAnalisisHidroBiologico.add(analisisHidroBiologico);
			}
		}
		
		if("21".equals(tipoOrganismo) || "22".equals(tipoOrganismo)){
			model.addAttribute("UnidadMedida","Org/mL");
		}else{
			model.addAttribute("UnidadMedida","Org/L");
		}
		
		model.addAttribute(BEAN_MAIN_ANALISISHIDRO,analisisHidroBiologicoMain);

		analisisHidroBiologicoMain.setListAnalisis(listAnalisisHidroBiologico);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "analisisHidroBiologico/analisisHidroBiologicoTable :: resultsList";
	}
	
	/**
	 * Mapeo analisis hidro biologico.
	 *
	 * @param listAnalisis the list analisis
	 * @return the map
	 */
	private Map<Integer, AnalisisHidroBiologico> mapeoAnalisisHidroBiologico(List<AnalisisHidroBiologico> listAnalisis){
		Map<Integer, AnalisisHidroBiologico> mapAnalisis = new HashMap<>();
		
		try {
			for (AnalisisHidroBiologico analisisHidroBiologico : listAnalisis) {
				mapAnalisis.put(analisisHidroBiologico.getCodorg(), analisisHidroBiologico);
			}
			
		} catch (Exception e) {
			LOG.error("",e);
		}
		
		return mapAnalisis;
		
	}
	
	/**
	 * Plan operativo bandeja.
	 *
	 * @param request the request
	 * @param session the session
	 * @param analisisHidroBiologicoMain the analisis hidro biologico main
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/analisisHidroBiologico", method = RequestMethod.GET)
	public String planOperativoBandeja(HttpServletRequest request, HttpSession session,
			@ModelAttribute(BEAN_MAIN_ANALISISHIDRO) AnalisisHidroBiologicoMain analisisHidroBiologicoMain,
			Model model){
		try {
			model.addAttribute(BEAN_MAIN_ANALISISHIDRO,analisisHidroBiologicoMain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "analisisHidroBiologico/analisisHidroBiologico";
	
	}
	
	/**
	 * Analisis hidro biologico save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param analisisHidroBiologicoMain the analisis hidro biologico main
	 * @return the string
	 */
	@RequestMapping(value = "/analisisHidroBiologicoSave", method = RequestMethod.POST)
	public String analisisHidroBiologicoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute(BEAN_MAIN_ANALISISHIDRO) AnalisisHidroBiologicoMain analisisHidroBiologicoMain){

		String strMensajeTipo = "";
		String strMensajeMostrar = "";
		String strMensajeError = "";
		String fecha = "";
		Integer codsub = 0;
		Integer tipoOrganismo = 0;
		int cantidadRegistrosInsertados = 0;
		try {
				fecha = analisisHidroBiologicoMain.getFecha();
				codsub = analisisHidroBiologicoMain.getCodsub();
				tipoOrganismo = analisisHidroBiologicoMain.getTipoOrganismo();
				
				StringBuilder log = new StringBuilder();
				log.append("fecha: ").append(fecha).append("; codsub: ").append(codsub).append("; tipoOrganismo: ").append(tipoOrganismo);
				String rastros = log.toString();
				LOG.info(rastros);
				
				LOG.info(fecha);
				
				ArrayList<AnalisisHidroBiologico> listAnalisisHidroBiologico = analisisHidroBiologicoMain.getListAnalisis();
				
				if(listAnalisisHidroBiologico!=null){
				for (AnalisisHidroBiologico analisis : listAnalisisHidroBiologico) {
					
					try {
						analisis.setUsuarioCreacion(Util.getUserLoged());
						analisis.setUsuarioModificacion(Util.getUserLoged());
						analisis.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_ANALISISHIDRO);
						analisis.setFechaAnalisis(fecha);
						analisis.setCodsub(codsub);
						analisis.setTipoOrganismo(tipoOrganismo);
						analisisHibroBiologicoService.insertarAnalisisHidroBiologico(analisis);
						cantidadRegistrosInsertados ++;
					} catch (Exception e) {						
						e.printStackTrace();
						LOG.info("Error al insertar el organismo: " + analisis.getCodorg() + "; " + e);
						e.printStackTrace();
					}
				 }
				}
				
				if(cantidadRegistrosInsertados == 0){
					strMensajeMostrar = obtenerMensaje("reporte.guardado.no.datos");
				}else{
					strMensajeMostrar = obtenerMensaje("analisis.hidrobiologico.guardado");
				}
				
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				
				model.addAttribute(Constants.MENSAJE_MOSTRAR,strMensajeMostrar);
				model.addAttribute(BEAN_MAIN_ANALISISHIDRO,analisisHidroBiologicoMain);
				
		} catch (Exception e) {	
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.error("ERROR ENCONTRADO EN EL CONTROLLER: ",e);
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
		
	}
	
	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 */
	@RequestMapping(value = "/reporteAnalisisHidroBiologico/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			{
		
		String fechaReporte;
		try {
			
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.ORDER_COLUMN_TABLE))==0?Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.ORDER_COLUMN_TABLE))+1:Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.ORDER_COLUMN_TABLE)));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			fechaReporte = allRequestParams.get("fechaReporte");
			

			StringBuilder log = new StringBuilder();
			log.append("fechaReporte: ").append(fechaReporte);
			String rastros = log.toString();
			LOG.info(rastros);

			Result result = analisisHibroBiologicoService.datosReporteAnalisisHidroBiologico(fechaReporte, paginacion);
			
			Map<String,Object> dtResponse = new HashMap<>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
			
			return new ResponseEntity<>(dtResponse, HttpStatus.OK);
			
		} catch (Exception e) {			
			LOG.error(e.getMessage(), e);	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Reporte analisis hidro biologico.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param reporteAnalisisHidroMain the reporte analisis hidro main
	 * @return the string
	 */
	@RequestMapping(value = "/reporteAnalisisHidroBiologico", method = RequestMethod.GET)
	public String reporteAnalisisHidroBiologico(HttpServletRequest request, HttpSession session,
			Model model, @ModelAttribute(BEAN_MAIN_REPORTEHIDRO) ReporteAnalisisHidro reporteAnalisisHidroMain) {
		ArrayList<ReporteAnalisisHidroDetalle> listDetalle = new ArrayList<>();

		reporteAnalisisHidroMain.setDetalles(listDetalle);
		
		model.addAttribute(BEAN_MAIN_REPORTEHIDRO, reporteAnalisisHidroMain);
		
		return "analisisHidroBiologico/reporteAnalisisHidroBiologico";
		
	}
	
	
	/**
	 * Reporte analisis hidro search.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param reporteAnalisisHidroMain the reporte analisis hidro main
	 * @param idCorrelativo the id correlativo
	 * @return the string
	 */
	@RequestMapping(value = "/reporteAnalisisHidroSearch/{idCorrelativo}", method = RequestMethod.GET)
	public String reporteAnalisisHidroSearch(HttpServletRequest request, HttpSession session,
			Model model, @ModelAttribute(BEAN_MAIN_REPORTEHIDRO) ReporteAnalisisHidro reporteAnalisisHidroMain,@PathVariable int idCorrelativo) {

		ArrayList<ReporteAnalisisHidroDetalle> listDetalle;
		
		try {
			
			listDetalle = analisisHibroBiologicoService.obtenerDetallePorCorrelativo(idCorrelativo);
			reporteAnalisisHidroMain.setFechaMuestreo(listDetalle.get(0).getFechaMuestreo());
			reporteAnalisisHidroMain.setPersonaMuestra(listDetalle.get(0).getPersonaMuestra());
			reporteAnalisisHidroMain.setAnalista(listDetalle.get(0).getAnalista());
			reporteAnalisisHidroMain.setDetalles(listDetalle);
			
		} catch (Exception e) {
			reporteAnalisisHidroMain.setDetalles(new ArrayList<ReporteAnalisisHidroDetalle>());
			LOG.error(e.getMessage());
		}
		
		model.addAttribute(BEAN_MAIN_REPORTEHIDRO, reporteAnalisisHidroMain);
		return "analisisHidroBiologico/reporteAnalisisHidroBiologicoTableDetalle :: resultsList";
	}
	
	
	/**
	 * Refrescar reporte por fecha.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	// METODO PARA REF4ESCAR TABLA
	@RequestMapping(value = "/refrescarReportePorFecha", method = RequestMethod.GET)
	public String refrescarReportePorFecha(HttpServletRequest request, HttpSession session,
			Model model,@RequestParam Map<String,String> allRequestParams){
			return "analisisHidroBiologico/reporteAnalisisHidroBiologicoTable :: resultsList";
	}
	
	
	/**
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param reporteAnalisisHidroMain the reporte analisis hidro main
	 * @return the string
	 */
	@RequestMapping(value = "/reporteAnalisisHidroBiologicoSave", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute(BEAN_MAIN_REPORTEHIDRO) ReporteAnalisisHidro reporteAnalisisHidroMain){

		String strMensajeTipo = "";
		String strMensajeError = "";
		ArrayList<ReporteAnalisisHidroDetalle> detalles;
		int cantidadRegistrosInsertados = 0;
		String strMensajeMostrar = "";
		try {
				detalles = reporteAnalisisHidroMain.getDetalles();
				StringBuilder log = new StringBuilder();
				log.append("reporteAnalisisHidroMain: ").append(reporteAnalisisHidroMain);
				String rastros = log.toString();
				LOG.info(rastros);
				
				int correlativoReporte = analisisHibroBiologicoService.obtenerCorrelativoDetalleReporte();
				correlativoReporte++;
				
				if(detalles==null || detalles.isEmpty()){
					
					model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("reporte.hidrobiologico.registros.vacio"));
					
				} else {
				
				for (ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle : detalles) {
					try {
					
						if (reporteAnalisisHidroDetalle.getCodsub() != null) {

							reporteAnalisisHidroDetalle.setFechaMuestreo(reporteAnalisisHidroMain.getFechaMuestreo());
							reporteAnalisisHidroDetalle.setPersonaMuestra(reporteAnalisisHidroMain.getPersonaMuestra());
							reporteAnalisisHidroDetalle.setAnalista(reporteAnalisisHidroMain.getAnalista());
							reporteAnalisisHidroDetalle.setCorrelativo(correlativoReporte);

							reporteAnalisisHidroDetalle.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_REPORTEHIDROBIO);
							reporteAnalisisHidroDetalle.setUsuarioCreacion(Util.getUserLoged());
							reporteAnalisisHidroDetalle.setUsuarioModificacion(Util.getUserLoged());

							LOG.info("FechaMuestreo: " + reporteAnalisisHidroMain.getFechaMuestreo());
							analisisHibroBiologicoService.insertarReporteHidroBiologico(reporteAnalisisHidroDetalle);
							cantidadRegistrosInsertados ++ ;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						LOG.info(e.toString());
					}
				}
				
				}
				
				if(cantidadRegistrosInsertados == 0){
					strMensajeMostrar = obtenerMensaje("reporte.guardado.no.datos");
				}else{
					strMensajeMostrar = obtenerMensaje("reporte.hidrobiologico.guardado");
				}
				
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				
				model.addAttribute(Constants.MENSAJE_MOSTRAR,strMensajeMostrar);
				
				model.addAttribute(BEAN_MAIN_REPORTEHIDRO,reporteAnalisisHidroMain);
				
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("reporte.hidrobiologico.error"));
			strMensajeError = e.getMessage();
			LOG.error(strMensajeError);
			e.printStackTrace();
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
		
	}
	
	/**
	 * Cargar registro.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/cargarRegistro", method = RequestMethod.GET)
	public String cargarRegistro(HttpServletRequest request, HttpSession session,
			Model model,@RequestParam Map<String,String> allRequestParams){
		
		String fechaMuestreo = allRequestParams.get("fechaMuestreo");
		Integer indiceRow = Integer.parseInt(allRequestParams.get("indiceRow"));
		Integer puntoMuestra = Integer.parseInt(allRequestParams.get("puntoMuestra"));
		Integer valueSelectPunto = Integer.parseInt(allRequestParams.get("valueSelectPunto"));
		ReporteAnalisisHidroDetalle registroReporteDetalle = null;
		StringBuilder log = new StringBuilder();
		log.append("fechaMuestreooo: ").append(fechaMuestreo).append("; puntoMuestra: ").append(puntoMuestra);
		String rastros = log.toString();
		LOG.info(rastros);
		try {
			
			registroReporteDetalle = analisisHibroBiologicoService.datosDetalleReporteHidroBiologico(fechaMuestreo,puntoMuestra);
			LOG.info("registroReporteDetalle resultado: " + registroReporteDetalle.toString());
			registroReporteDetalle.setValueSelectPunto(valueSelectPunto);
			
			
		} catch (Exception e) {
			LOG.info("Ha ocurrido un error",e.getMessage());
		}
		

		model.addAttribute("registroReporteDetalle",registroReporteDetalle);
		model.addAttribute("indiceDetalle",indiceRow);
		
		return "analisisHidroBiologico/reporteAnalisisHidroBiologicoTableDetalle :: registroDetalle";
	}
	

	/**
	 * Eliminar reporte hidro por correlativo.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @param idCorrelativo the id correlativo
	 * @return the string
	 */
	@RequestMapping(value = "/eliminarReporteHidroPorCorrelativo/{idCorrelativo}", method = RequestMethod.POST)
	public String eliminarReporteHidroPorCorrelativo(HttpServletRequest request, HttpSession session,
			Model model,@RequestParam Map<String,String> allRequestParams,@PathVariable int idCorrelativo){
		
		String strMensajeTipo;
		String strMensajeError = ConstantsLaboratorio.EMPTY_VALUE;
		try{
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("reporte.eliminado.ok"));
			analisisHibroBiologicoService.eliminarReporteHidroPorCorrelativo(idCorrelativo);
			
		} catch (Exception e) {
		LOG.error( ConstantsLaboratorio.EMPTY_VALUE,e);
		strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
		model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("reporte.eliminado.error"));
		strMensajeError = e.getMessage();
		}

		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
		
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
