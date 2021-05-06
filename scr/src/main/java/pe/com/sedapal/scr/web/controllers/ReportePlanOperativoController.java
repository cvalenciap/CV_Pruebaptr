/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import pe.com.sedapal.scr.core.beans.ActividadReporte;
import pe.com.sedapal.scr.core.beans.ParametroReporte;
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.ReportePlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.SubactividadReporte;
import pe.com.sedapal.scr.core.beans.TotalesForm20;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.core.services.IPlanOperativoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportePlanOperativoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReportePlanOperativoController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ReportePlanOperativoController.class);

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The plan operativo service. */
	@Autowired
	IPlanOperativoService planOperativoService;
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	/**
	 * Reporte plan operativo save.
	 *
	 * @param request the request
	 * @param session the session
	 * @param wrappper the wrappper
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/reportePlanOperativoSave", method = RequestMethod.POST)
	public String reportePlanOperativoSave(HttpServletRequest request, HttpSession session,
			@ModelAttribute("wrappper") ReportePlanOperativo wrappper,
			Model model){
		
	String	strMensajeTipo = ConstantsCommon.GRABADO_OK;
	String	strMensajeError = "";
		
	model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("reporte.plan.operativo.msg.guardado"));
	
	model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
	model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
	
	model.addAttribute("wrappper", wrappper);
		
		return "generic/genericSave :: genericSave";
	}
	
	/**
	 * Plan operativo get.
	 *
	 * @param request the request
	 * @param session the session
	 * @param planOperativoSearchBean the plan operativo search bean
	 * @param reportePlanEditBean the reporte plan edit bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/reportePlanOperativo", method = RequestMethod.GET)
	public String planOperativoGet(HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoSearchBean") PlanOperativo planOperativoSearchBean,
			@ModelAttribute("reportePlanEditBean") ReportePlanOperativo reportePlanEditBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
			

		String codigoArea = obtenerAreaPorUsuario(Util.getUserLoged());
		
		PlanOperativo planOperativo = new PlanOperativo();
		if(codigoArea.equals("B")){
			  planOperativo.setCodare(1);
			  model.addAttribute("codigoArea", 1);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.BIOLOGIA);
		  }else if(codigoArea.equals("F")){
			  planOperativo.setCodare(2);
			  model.addAttribute("codigoArea", 2);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.FISICOQUIMICA);
		  }else{
			  planOperativo.setCodare(-1);
			  model.addAttribute("codigoArea", -1);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.AREA_UNKNOWN);
		  }
		
			return "reportePlanOperativo/reportePlanOperativo";
	}
	
	/**
	 * Refrescar tabla filtrada X area.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/refrescarTablaFiltradaXArea", method = RequestMethod.GET)
	public String refrescarTablaFiltradaXArea(HttpServletRequest request, HttpSession session,
			Model model,@RequestParam Map<String,String> allRequestParams){
			return "reportePlanOperativo/reportePlanBandeja :: resultsList";
	}
	
	
	
	/**
	 * Buscar reporte plan.
	 *
	 * @param request the request
	 * @param session the session
	 * @param planOperativoSearchBean the plan operativo search bean
	 * @param reportePlanEditBean the reporte plan edit bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/buscarReportePlanSearch", method = RequestMethod.POST)
	public String buscarReportePlan(HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoSearchBean") PlanOperativo planOperativoSearchBean,
			@ModelAttribute("reportePlanEditBean") ReportePlanOperativo reportePlanEditBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
		
			/// alterno
			String periodo = allRequestParams.get("periodo");
			String mes = allRequestParams.get("idMes");
			String codpla = allRequestParams.get("codplan");
			String codare = allRequestParams.get("codare");
			String codDesc = allRequestParams.get("codDesc");
			String descPlan = allRequestParams.get("descPlan");
			
			LOG.info("mes : " + mes);
			LOG.info("codpla : " + codpla);
			LOG.info("codare : " + codare);
			LOG.info("codplan : " + codpla);
			LOG.info("periodo : " + periodo);
			Integer anio = periodo!=null && !periodo.isEmpty()? Integer.parseInt(periodo): 0000; // De obtenerse periodo null defecto 2999, no se encontrara' resultados
		
			List<ParametroReporte> parametrosbd = planOperativoService.obtenerReportePlanOperativo(Integer.parseInt(codpla),Integer.parseInt(codare), mes);
			model.addAttribute("estadoplan","OK");
			
			PlanOperativo planOperativo = planOperativoService.obtenerPlanOperativo(Integer.parseInt(codpla));
			LOG.info("Estado : " + planOperativo.getEstadoPlan());
			if(planOperativo.getEstadoPlan()==1){
				model.addAttribute("estadoplan",ConstantsCommon.ERROR);
				model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_NO_OK);
				model.addAttribute(Constants.MENSAJE_ERROR, obtenerMensaje("mensaje.reporte.plan.operativo.no.aprobada"));
				return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
			}
			
			reportePlanEditBean = obtenerReportePlanOperativo(parametrosbd,anio, mes);
			reportePlanEditBean.setPeriodo(anio);
			reportePlanEditBean.setCodDescPlan(codDesc);
			reportePlanEditBean.setDescripcion(descPlan);
			reportePlanEditBean.setCodplan(Integer.parseInt(codpla));
		    reportePlanEditBean.setMes(mes);
			reportePlanEditBean.setCodare(Integer.parseInt(codare));
			
			model.addAttribute("reportePlanEditBean",reportePlanEditBean);
			
			return "reportePlanOperativo/reportePlanOperativoTable :: resultsList";
	}
	
	
	/**
	 * Obtener reporte plan operativo.
	 *
	 * @param parametrosbd the parametrosbd
	 * @param anio the anio
	 * @param mes the mes
	 * @return the reporte plan operativo
	 */
	private ReportePlanOperativo obtenerReportePlanOperativo(List<ParametroReporte> parametrosbd,Integer anio, String mes){
		Map<Integer,Map<Integer,Map<Integer,Double>>>  totalesForm20;
		
		Map<Integer, Map<Integer, Map<Integer,ParametroReporte>>> mapActividad = new LinkedHashMap<>();
		ReportePlanOperativo reportePlanOperativo = new ReportePlanOperativo();
		try {
		 totalesForm20 = obtenerMapTotales(anio,mes);
		 
		 LOG.info("> totalesForm20 " + totalesForm20);
			
		 Map<Integer, Map<Integer,ParametroReporte>> subactividadesMap;
		 Map<Integer,ParametroReporte> parametroMap;
		 
		 
		 for (ParametroReporte parametroReporte : parametrosbd) {
		
			if(mapActividad.containsKey(parametroReporte.getCodact())){
				
				 Map<Integer, Map<Integer,ParametroReporte>> subactividadesReg = mapActividad.get(parametroReporte.getCodact());
				
				 Map<Integer,ParametroReporte> parametroReg;
				 if(subactividadesReg.containsKey(parametroReporte.getCodsubact())){
					 parametroReg = subactividadesReg.get(parametroReporte.getCodsubact());
					 
				 } else {
					 
					 parametroReg = new HashMap<>();
				 }
				 
				 LOG.info("parametroReg: " + parametroReg);
				 
				 parametroReg.put(parametroReporte.getCodparam(), parametroReporte);
				 subactividadesReg.put(parametroReporte.getCodsubact(), parametroReg);
				 
			} else {
				
				subactividadesMap = new HashMap<>();
				parametroMap = new HashMap<>();
				parametroMap.put(parametroReporte.getCodparam(), parametroReporte);
				
				subactividadesMap.put(parametroReporte.getCodsubact(), parametroMap);
				mapActividad.put(parametroReporte.getCodact(), subactividadesMap);
				
			}
			
		}
		
		LOG.info(" mapActividad: " + mapActividad);
		
		ArrayList<ActividadReporte> actividades = new ArrayList<ActividadReporte>();
		ArrayList<SubactividadReporte> subactividades = new ArrayList<SubactividadReporte>();
		ArrayList<ParametroReporte> parametros = new ArrayList<ParametroReporte>();
		
		//SE ITERA PARA DAR FORMA  LISTADO DE OBJETOSr
		for (Map.Entry<Integer, Map<Integer, Map<Integer,ParametroReporte>>> actividadRep : mapActividad.entrySet())
		{
			ActividadReporte actividadReporte = new ActividadReporte();
			actividadReporte.setCodigo(actividadRep.getKey());
		    
			Map<Integer, Map<Integer,ParametroReporte>> subactividadesRepo = actividadRep.getValue();
		    
			subactividades = new ArrayList<SubactividadReporte>();
		    for (Map.Entry<Integer, Map<Integer,ParametroReporte>> subactividadesEntry : subactividadesRepo.entrySet()){
		    	
		    	Map<Integer,ParametroReporte> parametrosRepo = subactividadesEntry.getValue();
		    	
		    	SubactividadReporte subactividadReporte = new SubactividadReporte();	
		    	subactividadReporte.setCodigo(subactividadesEntry.getKey());
		    	
		    	 parametros = new ArrayList<ParametroReporte>();
		    	 int totalProgramados = 0;
		    	 for (Map.Entry<Integer,ParametroReporte> parametrosEntry : parametrosRepo.entrySet()){
		    		 ParametroReporte paramRepo = parametrosEntry.getValue();
		    		 actividadReporte.setDescripcion(paramRepo.getActividad());
		    		 subactividadReporte.setDescripcion(paramRepo.getSubactividad());
		    		 totalProgramados+=paramRepo.getProgramado();
		    		 
		    		 if(paramRepo.getEjecutado()<=0){
		    			 
		    			 double ejecutados = obtenerMensajesActividad(totalesForm20, paramRepo.getCodact(), paramRepo.getCodsubact(), paramRepo.getCodpar());
		    			 LOG.info("ejecutados para " + "[ "+ paramRepo.getCodact() + " ] ; [ " + paramRepo.getCodsubact() + " ] ; [ " + paramRepo.getCodpar() + " ] : " + ejecutados + "; antes: " + paramRepo.getEjecutado());
		    			 paramRepo.setEjecutado(ejecutados);
		    		 }
		    		 
//		    		 paramRepo.setEjecutado(ejecutados);
		    		 parametros.add(paramRepo);
		    	 }
		    	 parametros.add(new ParametroReporte(ConstantsLaboratorio.TOTALES_PROGRAMADOS,ConstantsLaboratorio.TOTALES_PROGRAMADOS, totalProgramados));
		    	 subactividadReporte.setParametros(parametros);
		    	 subactividades.add(subactividadReporte);
		    	
		    }
		    actividadReporte.setSubactividades(subactividades);
		    actividades.add(actividadReporte);
		}
		
		reportePlanOperativo.setActividades(actividades);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		
		LOG.info(" reportePlanOperativo: " + reportePlanOperativo);
		return reportePlanOperativo;
	}
	
	/**
	 * Obtener map totales.
	 *
	 * @param anio the anio
	 * @param mes the mes
	 * @return the map
	 */
	private Map<Integer,Map<Integer,Map<Integer,Double>>> obtenerMapTotales(Integer anio,String mes) {
		
		 TotalesForm20 totalesActividad01 = planOperativoService.obtenerTotalesForm20(anio,mes,1);
		 TotalesForm20 totalesActividad02 = planOperativoService.obtenerTotalesForm20(anio,mes,2);
		 
		 LOG.info("anio: " + anio + " ; mes: " + mes);
		 LOG.info("totalesActividad01: " + totalesActividad01);
		 LOG.info("totalesActividad02: " + totalesActividad02);
		
		Map<Integer,Map<Integer,Map<Integer,Double>>> mapaTotales = new HashMap<>();
		
		Map<Integer,Map<Integer,Double>> mapaTotalSubactividadFirst =  new HashMap<>();
		Map<Integer,Map<Integer,Double>> mapaTotalSubactividadSecond =  new HashMap<>();
		
		Map<Integer,Double> mapaTotalParametroResultFirst =  new HashMap<>();
		Map<Integer,Double> mapaTotalParametroResultSecond=  new HashMap<>();
		
		String actividades[] = obtenerMensaje("actividad.formulario20.mapeo").split("#");
		String subactividades[]= obtenerMensaje("subactividad.formulario20.mapeo").split("#");
		String parametros[] = obtenerMensaje("parametros.formulario20.mapeo").split("#");

		
		mapaTotalParametroResultFirst.put(Integer.parseInt(parametros[0]), totalesActividad01.getColiTotales());
		mapaTotalParametroResultFirst.put(Integer.parseInt(parametros[1]), totalesActividad01.getColiTermoTolerantes());
		mapaTotalParametroResultFirst.put(Integer.parseInt(parametros[2]), totalesActividad01.getHeterotroficas());
		
		mapaTotalParametroResultSecond.put(Integer.parseInt(parametros[0]), totalesActividad02.getColiTotales());
		mapaTotalParametroResultSecond.put(Integer.parseInt(parametros[1]), totalesActividad02.getColiTermoTolerantes());
		mapaTotalParametroResultSecond.put(Integer.parseInt(parametros[2]), totalesActividad02.getHeterotroficas());
		
		// Las subactividades pueden ser igual o o diferentes (configurar 2 en messages.properties)
		mapaTotalSubactividadFirst.put(Integer.parseInt(subactividades[0]), mapaTotalParametroResultFirst);
		mapaTotalSubactividadSecond.put(Integer.parseInt(subactividades[1]), mapaTotalParametroResultSecond);
				
		mapaTotales.put(Integer.parseInt(actividades[0]), mapaTotalSubactividadFirst);
		mapaTotales.put(Integer.parseInt(actividades[1]), mapaTotalSubactividadSecond);
		
		return mapaTotales;
	}
	
	/**
	 * Obtener mensajes actividad.
	 *
	 * @param dataForm20 the data form 20
	 * @param codact the codact
	 * @param codsubact the codsubact
	 * @param codparam the codparam
	 * @return the double
	 */
	private static double  obtenerMensajesActividad(Map<Integer,Map<Integer,Map<Integer,Double>>> dataForm20,Integer codact,Integer codsubact, Integer codparam){
		double result = 0;
		Map<Integer,Map<Integer,Double>> subactividades = dataForm20.get(codact);
		if(subactividades != null){
			Map<Integer,Double> parametros = subactividades.get(codsubact);
			if(parametros!=null && codparam!=null){
				result = parametros.get(codparam) != null? parametros.get(codparam) : 0D;
			}
		}
		return result;
	}
	
	/**
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param reportePlanEditBean the reporte plan edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/reportePlanOperativoSave1", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("reportePlanEditBean") ReportePlanOperativo reportePlanEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		try {
			   ReportePlanOperativo repo = new ReportePlanOperativo(reportePlanEditBean);
				
				LOG.info("descrip: "+reportePlanEditBean.getDescripcion());
				LOG.info("reportePlanEditBean: "+reportePlanEditBean);
				LOG.info("codplan: " + reportePlanEditBean.getCodplan());
				LOG.info("descripcion: " + reportePlanEditBean.getDescripcion());
				LOG.info("mes: " + reportePlanEditBean.getMes());
				
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				
				PlanOperativo planOperativo = planOperativoService.obtenerPlanOperativo(reportePlanEditBean.getCodplan());
				LOG.info("Estado : " + planOperativo.getEstadoPlan());
				if(planOperativo.getEstadoPlan()==1){
					model.addAttribute("estadoplan",ConstantsCommon.ERROR);
					model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_NO_OK);
					model.addAttribute(Constants.MENSAJE_ERROR, obtenerMensaje("mensaje.reporte.plan.operativo.no.aprobada"));
					return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
				}
				
				
				List<ActividadReporte> actividades = reportePlanEditBean.getActividades();
				if(actividades!=null){
				for (ActividadReporte actividadReporte : actividades) {
					List<SubactividadReporte> subactividades = actividadReporte.getSubactividades();
					
					for (SubactividadReporte subactividadReporte : subactividades) {
						List<ParametroReporte> parametros = subactividadReporte.getParametros();
						
						for (ParametroReporte parametroReporte : parametros) {
							if(!ConstantsLaboratorio.TOTALES_PROGRAMADOS.equals(parametroReporte.getDescripcion())){
								parametroReporte.setUsuarioCreacion(Util.getUserLoged());
								parametroReporte.setUsuarioModificacion(Util.getUserLoged());
								parametroReporte.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_REPORTEPLAN);
								planOperativoService.guardarParametroReporte(parametroReporte, reportePlanEditBean.getCodplan(), reportePlanEditBean.getMes());	
							}
						}
					}
				 }
				
				}
				
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("plan.operativo.msg.guardado"));
				model.addAttribute("reportePlanEditBean",repo);
				
		} catch (Exception e) {
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "generic/genericSave :: genericSave";
		
	}

	/**
	 * Reporte plan operativo datos.
	 *
	 * @param allRequestParams the all request params
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reportePlanOperativo/pagination/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> reportePlanOperativoDatos(@RequestParam Map<String,String> allRequestParams,@PathVariable int id)
			throws Exception {
		try {
			PlanOperativo planOperativo = new PlanOperativo();
			planOperativo.setCodare(id);
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			Result result = planOperativoService.obtenerDatosPlanOperativo(planOperativo, paginacion , 0);
			
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
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reportePlanOperativo/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			PlanOperativo planOperativo = new PlanOperativo();
			planOperativo.setCodare(-1);
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			Result result = planOperativoService.obtenerDatosPlanOperativo(planOperativo, paginacion,0);
			
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
	 * Obtener area por usuario.
	 *
	 * @param idUser the id user
	 * @return the string
	 */
	private String obtenerAreaPorUsuario(String idUser){
		List<SelectItemBean> usuariosPerfil = catalogoService.obtenerCatalogo(25);
		for (SelectItemBean selectItemBean : usuariosPerfil) {
			LOG.info(">>>USER  " + selectItemBean.getLabel());
			  if(idUser.equals(selectItemBean.getLabel())){
				  return selectItemBean.getDefecto();
			  }
		}
		return "";
	}
	
	/**
	 * Obtener mensaje.
	 *
	 * @param messageProp the message prop
	 * @return the string
	 */
	private String obtenerMensaje(String messageProp){
		
		return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
		
	}
	
}
