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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pe.com.sedapal.scr.core.services.ISubactividadXGrupoService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class SubactividadXGrupoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class SubactividadXGrupoController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SubactividadXGrupoController.class);

	/** The env. */
	@Autowired
	Environment env;
	
	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The subactividad X grupo service. */
	@Autowired
	ISubactividadXGrupoService subactividadXGrupoService;
	
	/**
	 * Modificar subactividad X grupo.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/modificarSubactividadXGrupo", method = RequestMethod.POST)
	public String modificarSubactividadXGrupo(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		
		String strMensajeTipo = ConstantsCommon.GRABADO_OK;
		String strMensajeError = "";
		String strMensajeMostrar = obtenerMensaje("subactividad.grupo.msg.guardado");
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
		
		ItemPlanOperativo actividadArea = new ItemPlanOperativo();
		actividadArea.setUsuarioCreacion(Util.getUserLoged());
		actividadArea.setUsuarioModificacion(Util.getUserLoged());
		actividadArea.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_SUBACTIVIDADXGRUPO);
		actividadArea.setIdMaster(idMaster);
		actividadArea.setIdDetalle(idDetalle);
		actividadArea.setEstado(estado);
		
		if(estado==0L){
			 strMensajeMostrar = obtenerMensaje("subactividad.grupo.msg.actividad.removido");
		}
		
		if(indicesRemovidos!=null){
			
			for (String idRemover : indicesRemovidos.split("#")){
				actividadArea.setIdDetalle(Long.parseLong(idRemover));
				subactividadXGrupoService.actualizarSubactividadXGrupo(actividadArea);
			}
		}
		
		
		}else {
			LOG.info("No ha sido seleccionada una actividad");
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeMostrar = obtenerMensaje("subactividad.grupo.msg.no.subactividad");
			strMensajeError = strMensajeMostrar;
		}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("", e);
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
	@RequestMapping(value = "/insertarSubactividadXGrupo", method = RequestMethod.POST)
	public String insertarSubactividadXGrupo(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		String strMensajeTipo = "";
		String strMensajeError = "";
		
		LOG.info("UHMMM");
	
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
			subactividadXGrupo.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_SUBACTIVIDADXGRUPO);
			
			if(indicesRemovidos!=null){
				for (String indiceDetalle : indicesRemovidos.split("#")){
					subactividadXGrupo.setIdDetalle(Long.parseLong(indiceDetalle));
					
					boolean exists = subactividadXGrupoService.existeSubactividadXGrupo(Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDMASTER)), Integer.parseInt(indiceDetalle));
					LOG.info("existe id Actividad [ "+ allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDMASTER) + "] ; indiceDetalle [ "+ indiceDetalle +"]: " + exists);
					if(exists){
						subactividadXGrupoService.actualizarSubactividadXGrupo(subactividadXGrupo);
					}else{
						subactividadXGrupoService.insertarSubactividadXGrupo(subactividadXGrupo);
					}
				}
			}
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("ERROR",e);
			
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("subactividad.grupo.msg.guardado"));
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
		
	}
	
	/**
	 * Find actividades asignadas.
	 *
	 * @param idArea the id area
	 * @return the list
	 */
	@RequestMapping(value = "/listActividadesCbo/{idArea}", method = RequestMethod.GET)
	public @ResponseBody
	List<ItemPlanOperativo> findActividadesAsignadas(@PathVariable String idArea) {
		Integer idAreaInteger = Integer.parseInt(idArea);
		return this.subactividadXGrupoService.listarActividades(idAreaInteger);
	}
	
	/**
	 * Find sub actividades asignadas.
	 *
	 * @param idMaster the id master
	 * @return the list
	 */
	@RequestMapping(value = "/listSubactividadesAsignadas/{idMaster}", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findSubActividadesAsignadas(@PathVariable String idMaster) {
		Integer idAreaInteger = Integer.parseInt(idMaster);
		return this.subactividadXGrupoService.listarSubactividades(idAreaInteger);
	}
	
	/**
	 * Bandeja caudales go.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/subactividadesXGrupo", method = RequestMethod.GET)
	public String bandejaCaudalesGo(HttpServletRequest request, HttpSession session,
			ModelMap model) {
		
		model.addAttribute("today",DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		return "subactividadXGrupo/subactividadXGrupo";
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

