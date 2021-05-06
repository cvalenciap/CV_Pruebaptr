
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
import pe.com.sedapal.scr.core.beans.ActividadArea;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IActividadXAreaService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class ActividadXGrupoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ActividadXGrupoController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The Constant OPCION_UPDATE_DESCRIPCION. */
	private static final String  OPCION_UPDATE_DESCRIPCION= "UD";
	

	/** The env. */
	@Autowired
	Environment env;
	
	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The actividad X area service. */
	@Autowired
	IActividadXAreaService actividadXAreaService;
	
	/**
	 * Actualizarclima.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/modificarActividad", method = RequestMethod.POST)
	public String actualizarclima(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		
		String strMensajeTipo = ConstantsCommon.GRABADO_OK;
		String strMensajeError = "";
		String strMensajeMostrar = obtenerMensaje("actividad.area.msg.guardado");
		String indicesRemovidos;
		String descripcionAsignada;
		String accion;
		Long idArea;
		Long idActividad;
		Long estado;
		
		String actSelected;
		
		try {
			
		actSelected = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDACTIVIDAD);
			
		if(actSelected!=null && !actSelected.isEmpty()){
		
		descripcionAsignada = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_DESCRIPCION);
		idArea = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDREA));
		idActividad = Long.parseLong(actSelected);
		estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
		accion = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ACCION);
		indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
		
		ActividadArea actividadArea = new ActividadArea();
		actividadArea.setDescripcionAsignada(descripcionAsignada);
		actividadArea.setUsuarioModificacion(Util.getUserLoged());
		actividadArea.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_ACTIVIDAD);
		actividadArea.setIdArea(idArea);
		actividadArea.setIdActividad(idActividad);
		actividadArea.setEstado(estado);
		
		if(estado==0L){
			 strMensajeMostrar = obtenerMensaje("actividad.area.msg.actividad.removida");
		}
		
		//if usado para actualizar el mensaje personalizado
		if(accion!=null && OPCION_UPDATE_DESCRIPCION.equals(accion)){
			strMensajeMostrar = obtenerMensaje("actividad.area.msg.actividad.modificada");
			actividadXAreaService.actualizarActividadXArea(actividadArea);
		}
		
		if(indicesRemovidos!=null){
			
			for (String idRemover : indicesRemovidos.split("#")){
				actividadArea.setIdActividad(Long.parseLong(idRemover));
				actividadXAreaService.actualizarActividadXArea(actividadArea);
			}
		}
		
		
		}else {
			LOG.info("No ha sido seleccionada una actividad");
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeMostrar = obtenerMensaje("actividad.area.msg.no.actividad");
			strMensajeError = strMensajeMostrar;
		}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("USUARIO: " + Util.getUserLoged(), e);
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR,strMensajeMostrar);
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
	}
	
	/**
	 * Insertar actividad.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/insertarActividad", method = RequestMethod.POST)
	public String insertarActividad(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		String strMensajeTipo = "";
		String strMensajeError = "";
		
	
		Long idArea;
		Long idActividad;
		String descripcionAsignada = "";
		String indicesRemovidos;
		Long estado;
		
		descripcionAsignada = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_DESCRIPCION);
		idArea = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDREA));
		idActividad = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDACTIVIDAD));
		estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
		indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
		
		ActividadArea actividadArea = new ActividadArea();
		actividadArea.setIdArea(idArea);
		actividadArea.setIdActividad(idActividad);
		actividadArea.setDescripcionAsignada(descripcionAsignada);
		actividadArea.setEstado(estado);
		actividadArea.setUsuarioCreacion(Util.getUserLoged());
		actividadArea.setUsuarioModificacion(Util.getUserLoged());
		actividadArea.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_ACTIVIDAD);
		
		try {
			
			if(indicesRemovidos!=null){
				for (String idActividadInsertar : indicesRemovidos.split("#")){
					actividadArea.setIdActividad(Long.parseLong(idActividadInsertar));
					
					boolean exists = actividadXAreaService.existeActividad(Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDREA)), Integer.parseInt(idActividadInsertar));
					if(exists){
						actividadXAreaService.actualizarActividadXArea(actividadArea);
					}else{
						actividadXAreaService.insertarActividad(actividadArea);
					}
				}
			}
			
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			LOG.info("",e);
			
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, strMensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("actividad.area.msg.guardado"));
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
		
	}
	
	/**
	 * Find actividades asignadas.
	 *
	 * @param idArea the id area
	 * @return the list
	 */
	@RequestMapping(value = "/listActividadesAsignadas/{idArea}", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findActividadesAsignadas(@PathVariable String idArea) {
		Integer idAreaInteger = Integer.parseInt(idArea);
		return this.actividadXAreaService.listarActividades(idAreaInteger);
	}
	
	
	/**
	 * Bandeja caudales go.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/actividadXArea", method = RequestMethod.GET)
	public String bandejaCaudalesGo(HttpServletRequest request, HttpSession session,
			ModelMap model) {
		
		model.addAttribute("today",DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		
		return "actividadXArea/actividadArea";
	}

	/**
	 * Gets the actividad X area service.
	 *
	 * @return the actividad X area service
	 */
	public IActividadXAreaService getActividadXAreaService() {
		return actividadXAreaService;
	}

	/**
	 * Sets the actividad X area service.
	 *
	 * @param actividadXAreaService the new actividad X area service
	 */
	public void setActividadXAreaService(IActividadXAreaService actividadXAreaService) {
		this.actividadXAreaService = actividadXAreaService;
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
