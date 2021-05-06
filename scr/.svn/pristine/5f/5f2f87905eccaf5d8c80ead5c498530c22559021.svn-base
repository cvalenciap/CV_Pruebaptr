/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.ItemPlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IParametroXSubactividadService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class ParametroXSubactividadController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ParametroXSubactividadController {

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The parametro X subactividad service. */
	@Autowired
	IParametroXSubactividadService parametroXSubactividadService;
	
	/**
	 * Modificar parametro X subactividad.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/modificarParametroXSubactividad", method = RequestMethod.POST)
	public String modificarParametroXSubactividad(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		
		String strMensajeTipo = ConstantsCommon.GRABADO_OK;
		String strMensajeError = "";
		String strMensajeMostrar = obtenerMensaje("subactividad.parametro.msg.guardado");
		String indicesRemovidos;
		Long idMaster;
		Long idDetalle;
		Long estado;
		
		String detalleSeleccionado;
		
		try {
			
		detalleSeleccionado = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDDETALLE);
			
		if(detalleSeleccionado!=null && !detalleSeleccionado.isEmpty()){
		
		idMaster = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDMASTER));
		idDetalle = Long.parseLong(detalleSeleccionado);
		estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
		indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
		
		ItemPlanOperativo itemPlan = new ItemPlanOperativo();
		itemPlan.setUsuarioCreacion(Util.getUserLoged());
		itemPlan.setUsuarioModificacion(Util.getUserLoged());
		itemPlan.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PARAMETROXSUBACT);
		itemPlan.setIdMaster(idMaster);
		itemPlan.setIdDetalle(idDetalle);
		itemPlan.setEstado(estado);
		
		if(estado==0L){
			 strMensajeMostrar = obtenerMensaje("subactividad.parametro.msg.actividad.removido");
		}
		
		if(indicesRemovidos!=null){
			
			for (String idRemover : indicesRemovidos.split("#")){
				itemPlan.setIdDetalle(Long.parseLong(idRemover));
				parametroXSubactividadService.actualizarParametroXSubactividad(itemPlan);
			}
		}
		
		}else {
			
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeMostrar = obtenerMensaje("subactividad.parametro.msg.no.actividad");
			strMensajeError = strMensajeMostrar;
			
		}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR,strMensajeMostrar);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
	}
	
	/**
	 * Insertar subactividad X grupo.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/insertarParametroXSubactividad", method = RequestMethod.POST)
	public String insertarSubactividadXGrupo(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		String strMensajeTipo = "";
		String strMensajeError = "";
		
		Long idMaster;
		Long idDetalle;
		String indicesRemovidos;
		Long estado;
		
		try {
			
			idMaster = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDMASTER));
			idDetalle = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDDETALLE));
			estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
			indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
			
			ItemPlanOperativo subactividadXGrupo = new ItemPlanOperativo();
			subactividadXGrupo.setIdMaster(idMaster);
			subactividadXGrupo.setIdDetalle(idDetalle);
			subactividadXGrupo.setEstado(estado);
			subactividadXGrupo.setUsuarioCreacion(Util.getUserLoged());
			subactividadXGrupo.setUsuarioModificacion(Util.getUserLoged());
			subactividadXGrupo.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_PARAMETROXSUBACT);
			
			if(indicesRemovidos!=null){
				for (String indiceDetalle : indicesRemovidos.split("#")){
					subactividadXGrupo.setIdDetalle(Long.parseLong(indiceDetalle));
					
					boolean exists = parametroXSubactividadService.existeParametroXSubactividad(Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDMASTER)), Integer.parseInt(indiceDetalle));
					
					if(exists){
						parametroXSubactividadService.actualizarParametroXSubactividad(subactividadXGrupo);
					}else{
						parametroXSubactividadService.insertarParametroXSubactividad(subactividadXGrupo);
					}
				}
			}
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
		}
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("subactividad.parametro.msg.guardado"));
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
	}
	
	/**
	 * Find sub actividades cbo.
	 *
	 * @param idActividad the id actividad
	 * @return the list
	 */
	@RequestMapping(value = "/listSubActividadesCbo/{idActividad}", method = RequestMethod.GET)
	public @ResponseBody
	List<ItemPlanOperativo> findSubActividadesCbo(@PathVariable String idActividad) {
		Integer idActividadString = Integer.parseInt(idActividad);
		return this.parametroXSubactividadService.listarSubActividades(idActividadString);
	}
	
	/**
	 * Find sub actividades asignadas.
	 *
	 * @param idMaster the id master
	 * @return the list
	 */
	@RequestMapping(value = "/listParametrosAsignados/{idMaster}", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findSubActividadesAsignadas(@PathVariable String idMaster) {
		Integer idAreaInteger = Integer.parseInt(idMaster);
		return this.parametroXSubactividadService.listarParametros(idAreaInteger);
	}
	
	/**
	 * Parametro X subactividades URL.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/parametroXSubactividad", method = RequestMethod.GET)
	public String parametroXSubactividadesURL(HttpServletRequest request, HttpSession session,
			ModelMap model) {
		model.addAttribute("today",DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		return "parametroXSubactividad/parametroXSubactividad";
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
