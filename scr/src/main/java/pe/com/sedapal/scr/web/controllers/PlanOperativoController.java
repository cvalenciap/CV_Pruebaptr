/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;
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
import pe.com.sedapal.scr.core.beans.ParametroPlan;
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.core.services.IParametroXSubactividadService;
import pe.com.sedapal.scr.core.services.IPlanOperativoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class PlanOperativoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class PlanOperativoController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PlanOperativoController.class);

	
	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The parametro X subactividad service. */
	@Autowired
	IParametroXSubactividadService parametroXSubactividadService;
	
	/** The plan operativo service. */
	@Autowired
	IPlanOperativoService planOperativoService;
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	
	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @param codplan the codplan
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/planOperativoDetalle/pagination/{codplan}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams,@PathVariable int codplan)
			throws Exception {
		try {
			
			ParametroPlan parametroPlan = new ParametroPlan();
			int estadoAprobado = Constants.ACTIVO;
			
			LOG.info("cod plan a consultar : " + codplan);
			
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby(Integer.parseInt((allRequestParams.get("order[0][column]")==null)?"0":allRequestParams.get("order[0][column]"))==0?1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			parametroPlan.setCodplan(codplan);

			Result result = planOperativoService.obtenerDatosParametrosPlan(parametroPlan, paginacion);
			LOG.info("DATA : " + result.getData());
			
			Map<String,Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
			
			if(codplan>0){
				PlanOperativo planOperativo = planOperativoService.obtenerPlanOperativo(codplan);
				estadoAprobado = planOperativo.getEstadoPlan();
				
				LOG.info("estadoAprobado : " + estadoAprobado + " ; codplan " + codplan);
				LOG.info("estadoAprobado : " + estadoAprobado + " ; data: " + result.getData().size());
			}
			
			
			
			dtResponse.put("aprobado", estadoAprobado);
			
			return new ResponseEntity<Map<String,Object>>(dtResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Tabla poisson get page.
	 *
	 * @param parametroPlanSearchBean the parametro plan search bean
	 * @param parametroPlanEditBean the parametro plan edit bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/planOperativoGet/{id}", method = RequestMethod.GET)
	public String tablaPoissonGetPage(
			@ModelAttribute("parametroPlanSearchBean") ParametroPlan parametroPlanSearchBean,
			@ModelAttribute("parametroPlanEditBean") ParametroPlan parametroPlanEditBean, Model model,
			@RequestParam Map<String,String> allRequestParams,
			@PathVariable int id) throws Exception {
		
		try {

			int codsxp = Integer.parseInt(allRequestParams.get("codsxp")!=null? allRequestParams.get("codsxp"):"0");
			int codplan = Integer.parseInt(allRequestParams.get("codplan")!=null? allRequestParams.get("codplan"):"0");
			
			int codarea = Integer.parseInt(allRequestParams.get("codarea")!=null? allRequestParams.get("codarea"):"0");
			int codact = Integer.parseInt(allRequestParams.get("codact")!=null? allRequestParams.get("codact"):"0");
			int codsubact = Integer.parseInt(allRequestParams.get("codsubact")!=null? allRequestParams.get("codsubact"):"0");
			
			LOG.info("codsxp: " + codsxp);
			LOG.info("codplan: " + codplan);
			LOG.info("codarea: " + codarea);
			LOG.info("codact: " + codact);
			LOG.info("codsubact: " + codsubact);
			LOG.info("id: " + id);
			
			
			LOG.info("textparam: " + allRequestParams.get("textparam"));
			
			if(codsxp > 0) {
				
				parametroPlanEditBean = planOperativoService.obtenerParametroPlan(codsxp,codplan);
				if(id==0){
					
					if(parametroPlanEditBean!=null && parametroPlanEditBean.getCodsxp()!= null && parametroPlanEditBean.getEstado()!=0 ){
						
							model.addAttribute(Constants.MENSAJE_TIPO, "parametroPlanExistente");
							model.addAttribute(Constants.MENSAJE_MOSTRAR,"El Par\u00E1metro seleccionado ya fue registrado");
							
							LOG.info(">>> retornando genericSave <<<");
							return "planOperativo/planOperativoDetalleSave :: genericSave";
					}
				}
				
			} else {
				parametroPlanEditBean = new ParametroPlan();  
				parametroPlanEditBean.setCodsxp(codsxp);
				parametroPlanEditBean.setCodplan(codplan);
			
				parametroPlanEditBean.setCodarea(codarea);
				parametroPlanEditBean.setCodact(codact);
				parametroPlanEditBean.setCodsubact(codsubact);
			}
		
			parametroPlanEditBean.setParametro(allRequestParams.get("textparam"));
			parametroPlanEditBean.setCodsxp(codsxp);
			parametroPlanEditBean.setCodplan(codplan);
			
			parametroPlanEditBean.setCodarea(codarea);
			parametroPlanEditBean.setCodact(codact);
			parametroPlanEditBean.setCodsubact(codsubact);
			
			parametroPlanEditBean.setUsuarioCreacion(Util.getUserLoged());
			parametroPlanEditBean.setUsuarioModificacion(Util.getUserLoged());
			
			LOG.info("parametroPlanEditBean: " + parametroPlanEditBean);
			model.addAttribute("parametroPlanEditBean", parametroPlanEditBean);

			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return "planOperativo/planOperativoDetalleTableEdit :: form-edit-tablaPoisson";
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
	@RequestMapping(value = "/refrescarPlanXArea", method = RequestMethod.GET)
	public String refrescarTablaFiltradaXArea(HttpServletRequest request, HttpSession session,
			Model model,@RequestParam Map<String,String> allRequestParams){
			return "planOperativo/planOperativoBandejaTable :: resultsList";
	}
	
	/**
	 * Insert parametro plan save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param parametroPlanEditBean the parametro plan edit bean
	 * @param idPlan the id plan
	 * @param aprobado the aprobado
	 * @return the string
	 */
	/*
	 * Método que permite registrar y/o actualizar un registro de tabla Poisson
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 * */
	@RequestMapping(value = "/aprobarPlan/{idPlan}/{aprobado}", method = RequestMethod.POST)
	public String insertParametroPlanSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("parametroPlanEditBean") ParametroPlan parametroPlanEditBean,@PathVariable int idPlan, @PathVariable int aprobado) {

		String strMensajeTipo = "";
		String strMensajeError = "";
	
		PlanOperativo planOperativo = new PlanOperativo();
		try {
			planOperativo.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PLANOPERATIVO);
			planOperativo.setUsuarioCreacion(Util.getUserLoged());
			planOperativo.setUsuarioModificacion(Util.getUserLoged());
			planOperativo.setCodigoPlan(idPlan);
			
			LOG.info("idPlan : " + idPlan);
			planOperativo.setEstadoPlan(aprobado);
			if(aprobado==0){
				LOG.info("planOperativo MODIFICARRRR: " + aprobado);
				planOperativo.setEstadoPlan(1);
			}else{
				LOG.info("planOperativo MODIFICAR: " + aprobado);
				planOperativo.setEstadoPlan(0);
			}
			
			planOperativo.setActivo(ConstantsLaboratorio.ACTIVO);
			
			planOperativoService.actualizarPlanOperativo(planOperativo);

			
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("parametro.plan.msg.guardado"));
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("ERROR",e);
			
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
	}
	
	/**
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param parametroPlanEditBean the parametro plan edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/parametroPlanSave", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("parametroPlanEditBean") ParametroPlan parametroPlanEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		String mensajeMostrar = "";
		
		
		parametroPlanEditBean.setUsuarioCreacion(Util.getUserLoged());
		parametroPlanEditBean.setUsuarioModificacion(Util.getUserLoged());
		
		try {
			
			LOG.info("getCodsxp: " + parametroPlanEditBean.getCodsxp());
			LOG.info("getCodplan: " + parametroPlanEditBean.getCodplan());
			LOG.info("getParametro: " + parametroPlanEditBean.getParametro());
			
			LOG.info("getCodarea: " + parametroPlanEditBean.getCodarea());
			LOG.info("getCodact: " + parametroPlanEditBean.getCodact());
			LOG.info("getCodsubact: " + parametroPlanEditBean.getCodsubact());
			
			boolean exists = planOperativoService.existeParametroPlan(parametroPlanEditBean.getCodsxp(), parametroPlanEditBean.getCodplan());
			parametroPlanEditBean.setEstado(1);
				LOG.info("exists: " + exists);
				
					if(exists) {
						
						LOG.info("parametroPlanEditBean MODIFICAR: " + parametroPlanEditBean);
						parametroPlanEditBean.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PARAMETROPLAN);
						planOperativoService.actualizarParametroPlan(parametroPlanEditBean);
						mensajeMostrar = obtenerMensaje("parametro.plan.msg.modificado");
					
					} else {
						
						LOG.info("parametroPlanEditBean INSERTAR: " + parametroPlanEditBean);
						parametroPlanEditBean.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_PARAMETROPLAN);
						planOperativoService.insertarParametroPlan(parametroPlanEditBean);
						mensajeMostrar = obtenerMensaje("parametro.plan.msg.guardado");

					}
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			model.addAttribute(Constants.MENSAJE_MOSTRAR,mensajeMostrar);
			
		} catch (Exception e) {
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("ERROR",e);
			
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "planOperativo/planOperativoDetalleSave :: genericSave";
	}

	/**
	 * Tabla poisson.
	 *
	 * @param request the request
	 * @param session the session
	 * @param parametroPlanSearchBean the parametro plan search bean
	 * @param parametroPlanEditBean the parametro plan edit bean
	 * @param codplan the codplan
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/planOperativoDetalle/{codplan}", method = RequestMethod.GET)
	public String tablaPoisson(HttpServletRequest request, HttpSession session,
			@ModelAttribute("parametroPlanSearchBean") ParametroPlan parametroPlanSearchBean,
			@ModelAttribute("parametroPlanEditBean") ParametroPlan parametroPlanEditBean,
			@PathVariable int codplan,@RequestParam Map<String,String> allRequestParams,
			Model model){
		
		LOG.info("icodplan2 :: "+ request.getParameter("codplan"));
		LOG.info("icodplan3 :: "+ request.getQueryString());
		LOG.info("icodpla4 :: "+ request.getScheme());
		
		model.addAttribute("codplan",codplan);
		model.addAttribute("codDescPlan",allRequestParams.get("codDescPlan"));
		model.addAttribute("descripcion",allRequestParams.get("descripcion"));
		
		return "planOperativo/planOperativoDetalle";
	}
	
	/**
	 * Plan operativo get.
	 *
	 * @param request the request
	 * @param session the session
	 * @param planOperativoSearchBean the plan operativo search bean
	 * @param planOperativoEditBean the plan operativo edit bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/planOperativoBandejaGet", method = RequestMethod.GET)
	public String planOperativoGet(HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoSearchBean") PlanOperativo planOperativoSearchBean,
			@ModelAttribute("planOperativoEditBean") PlanOperativo planOperativoEditBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
		
		String  codplan = allRequestParams.get("codplan");
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
		
		   
		   if(!codplan.equals("0")){
			   LOG.info("id codplan: " + codplan);

			   model.addAttribute("codplan",codplan);
			   model.addAttribute("codDescPlan",allRequestParams.get("codDescPlan"));
			   model.addAttribute("descripcion",allRequestParams.get("descripcion"));
			   model.addAttribute("codare",allRequestParams.get("codare")!=null?allRequestParams.get("codare"):1);
			   return "planOperativo/planOperativoDetalleTable :: resultsList";
		   }
		   
			model.addAttribute("planOperativoEditBean", planOperativo);
		
		return "planOperativo/planOperativoBandejaTableEdit :: form-edit-tablaPoisson";
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
	
	/**
	 * ************************************************************************** 
	 * ****************** METODOS PARA BANDJEA DE PLAN OPERATIVO *****************
	 * **************************************************************************.
	 *
	 * @param request the request
	 * @param session the session
	 * @param planOperativoSearchBean the plan operativo search bean
	 * @param planOperativoEditBean the plan operativo edit bean
	 * @param model the model
	 * @return the string
	 */
	
	@RequestMapping(value = "/planOperativo", method = RequestMethod.GET)
	public String planOperativoBandeja(HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoSearchBean") PlanOperativo planOperativoSearchBean,
			@ModelAttribute("planOperativoEditBean") PlanOperativo planOperativoEditBean,
			Model model){
		LOG.info("usuario logueado: " + Util.getUserLoged());
		String codigoArea = obtenerAreaPorUsuario(Util.getUserLoged());
		
		if(codigoArea.equals("B")){
			  model.addAttribute("codigoArea", 1);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.BIOLOGIA);
		  }else if(codigoArea.equals("F")){
			  model.addAttribute("codigoArea", 2);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.FISICOQUIMICA);
		  }else{
			  model.addAttribute("codigoArea", -1);
			  model.addAttribute("descripcionArea", ConstantsLaboratorio.AREA_UNKNOWN);
		  }
		LOG.info("codigoArea : " + codigoArea);
		
		return "planOperativo/planOperativoBandeja";
	}
	
	/**
	 * Plan operativo pagination.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/planOperativo/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> planOperativoPagination(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			PlanOperativo planOperativo = new PlanOperativo();
			planOperativo.setCodare(-1);
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			Result result = planOperativoService.obtenerDatosPlanOperativo(planOperativo, paginacion,1);
			
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
	 * Plan operativo pagination.
	 *
	 * @param allRequestParams the all request params
	 * @param id the id
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/planOperativo/pagination/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> planOperativoPagination(@RequestParam Map<String,String> allRequestParams,@PathVariable int id)
			throws Exception {
		try {
			PlanOperativo planOperativo = new PlanOperativo();
			planOperativo.setCodare(id);
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			Result result = planOperativoService.obtenerDatosPlanOperativo(planOperativo, paginacion , 1);
			
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
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param planOperativoEditBean the plan operativo edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/planOperativoSave", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoEditBean") PlanOperativo planOperativoEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		planOperativoEditBean.setUsuarioCreacion(Util.getUserLoged());
		planOperativoEditBean.setUsuarioModificacion(Util.getUserLoged());
		
		try {
			
				LOG.info("planOperativoEditBean:  " + planOperativoEditBean);
				LOG.info("desc: "+planOperativoEditBean.getDescripcion());
				LOG.info("codarea: "+planOperativoEditBean.getCodare());
				planOperativoEditBean.setPrograma(ConstantsLaboratorio.PRG_INSERT_PLAN);
				planOperativoEditBean.setEstadoPlan(Constants.ACTIVO);
				planOperativoEditBean.setActivo(Constants.INACTIVO);
				planOperativoEditBean.setCodare(planOperativoEditBean.getCodare());
				
				planOperativoService.grabarPlanOperativo(planOperativoEditBean);
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("plan.operativo.msg.guardado"));
				
				
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = String.format(obtenerMensaje("plan.operativo.error.duplicado.codigo"), planOperativoEditBean.getCodigoDescripcionPlan());
			Log.error(strMensajeError);
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "planOperativo/planOperativoBandejaSave :: planOperativoBandejaSave";
	}
	
	/**
	 * Inactivar plan operativo.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param allRequestParams the all request params
	 * @param codplan the codplan
	 * @return the string
	 */
	@RequestMapping(value = "/inactivarPlanOperativo/{codplan}", method = RequestMethod.POST)
	public String inactivarPlanOperativo(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam Map<String,String> allRequestParams,@PathVariable int codplan) {

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		PlanOperativo planOperativo = new PlanOperativo();
		
		try {
			planOperativo.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PLANOPERATIVO);
			planOperativo.setUsuarioCreacion(Util.getUserLoged());
			planOperativo.setUsuarioModificacion(Util.getUserLoged());
			planOperativo.setCodigoPlan(codplan);

			LOG.info("planOperativo MODIFICAR: " + planOperativo);
			
			planOperativo.setActivo(Constants.INACTIVO);
			planOperativo.setEstadoPlan(Constants.ACTIVO);
			int retorno = planOperativoService.actualizarPlanOperativo(planOperativo);
			
			if(retorno == -2){
				strMensajeTipo = ConstantsCommon.INFORMACION_NO_OK;
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("parametro.plan.msg.no.eliminado"));
			}else{
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("parametro.plan.msg.removido"));
			}
						
		} catch (Exception e) {
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("ERROR",e);
			
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		return "planOperativo/planOperativoBandejaSave :: planOperativoBandejaSave";
	}
	
	/**
	 * Inactivar parametro plan.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/inactivarParametroPlan", method = RequestMethod.POST)
	public String inactivarParametroPlan(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam Map<String,String> allRequestParams) {

		String strMensajeTipo = "";
		String strMensajeError = "";
		
		ParametroPlan parametroPlan = new ParametroPlan();
		try {
			
			LOG.info("User logged: " + Util.getUserLoged());
			LOG.info("parametroPlan MODIFICAR: " + parametroPlan);
			LOG.info("codsxp: " + allRequestParams.get("codsxp"));
			LOG.info("codplan: " + allRequestParams.get("codplan"));
			
			parametroPlan.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PARAMETROPLAN);
			parametroPlan.setUsuarioCreacion(Util.getUserLoged());
			parametroPlan.setUsuarioModificacion(Util.getUserLoged());
			parametroPlan.setCodsxp(Integer.parseInt(allRequestParams.get("codsxp")));
			parametroPlan.setCodplan(Integer.parseInt(allRequestParams.get("codplan")));
			parametroPlan.setEstado(Constants.INACTIVO);
			
			planOperativoService.inactivarParametroPlan(parametroPlan);
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("parametro.plan.msg.removido"));
			
		} catch (Exception e) {
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("ERROR",e);
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "planOperativo/planOperativoDetalleSave :: genericSave";
	}
	
	/**
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping(value = "/pruebaSave", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session
		 ){

				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("plan.operativo.msg.guardado"));
	
		model.addAttribute("mensajeTipo", ConstantsCommon.GRABADO_OK);
		model.addAttribute("mensajeError", "");
		return "planOperativo/planOperativoBandejaSave :: planOperativoBandejaSave";
	}
	
}
