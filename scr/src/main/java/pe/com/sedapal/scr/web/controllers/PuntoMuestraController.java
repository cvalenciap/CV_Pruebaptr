/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import static pe.com.sedapal.scr.web.common.Util.getPageCall;
import static pe.com.sedapal.scr.web.common.Util.getUserLoged;

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

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.PuntoXSubactividad;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IPuntoXSubactividadService;
import pe.com.sedapal.scr.core.util.ConstantsCommon;
import pe.com.sedapal.scr.web.common.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class PuntoMuestraController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class PuntoMuestraController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);

	/** The env. */
	@Autowired
	Environment env;
	
	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The punto X subactividad service. */
	@Autowired
	IPuntoXSubactividadService puntoXSubactividadService;
	
	
	/**
	 * Actualizar punto.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/modificarPunto", method = RequestMethod.POST)
	public String actualizarPunto(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		
		String strMensajeTipo = ConstantsCommon.GRABADO_OK;
		String strMensajeError = "";
		String strMensajeMostrar = obtenerMensaje("punto.subactividad.msg.guardado");
		String indicesRemovidos;
		Long idSubActividad;
		Long idPunto;
		Long estado;
		
		String puntoSelected;
		
		try {
			
		puntoSelected = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDPUNTO);
			
		if(puntoSelected!=null && !puntoSelected.isEmpty()){
		
		idSubActividad = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDSUBACTIVIDAD));
		idPunto = Long.parseLong(puntoSelected);
		estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
		indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
		
		PuntoXSubactividad puntoXSubactividad = new PuntoXSubactividad();
		puntoXSubactividad.setUsuarioCreacion(getUserLoged());
		puntoXSubactividad.setUsuarioModificacion(getUserLoged());
		puntoXSubactividad.setPrograma(ConstantsLaboratorio.PROGRAMA_UPDATE_PUNTOXSUBACTIVIDAD);
		puntoXSubactividad.setIdSubactividad(idSubActividad);
		puntoXSubactividad.setIdPunto(idPunto);
		puntoXSubactividad.setEstado(estado);
		LOG.info("idArea : " + idSubActividad);
		if(estado==0L){
			 strMensajeMostrar = obtenerMensaje("punto.subactividad.msg.actividad.removido");
		}
		
		if(indicesRemovidos!=null){
			
			for (String idRemover : indicesRemovidos.split("#")){
				puntoXSubactividad.setIdPunto(Long.parseLong(idRemover));
				puntoXSubactividadService.actualizarPuntoXSubactividad(puntoXSubactividad);
			}
		}
		
		
		}else {
			LOG.info("No ha sido seleccionada una actividad");
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeMostrar = obtenerMensaje("punto.subactividad.msg.no.punto");
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
	 * Insertar punto.
	 *
	 * @param allRequestParams the all request params
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/insertarPunto", method = RequestMethod.POST)
	public String insertarPunto(@RequestParam Map<String,String> allRequestParams,ModelMap model){
		String strMensajeTipo = "";
		String strMensajeError = "";
		
	
		Long idArea;
		Long idActividad;
		String indicesRemovidos;
		Long estado;
		
		idArea = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDSUBACTIVIDAD));
		idActividad = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDPUNTO));
		estado = Long.parseLong(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_ESTADO));
		indicesRemovidos = allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_INDICES);
		
		PuntoXSubactividad puntoXSubactividad = new PuntoXSubactividad();
		puntoXSubactividad.setIdSubactividad(idArea);
		puntoXSubactividad.setIdPunto(idActividad);
		puntoXSubactividad.setEstado(estado);
		puntoXSubactividad.setUsuarioCreacion(getUserLoged());
		puntoXSubactividad.setUsuarioModificacion(getUserLoged());
		puntoXSubactividad.setPrograma(ConstantsLaboratorio.PROGRAMA_INSERT_SUBACTIVIDADXGRUPO);
		
		try {
			
			if(indicesRemovidos!=null){
				for (String idActividadInsertar : indicesRemovidos.split("#")){
					puntoXSubactividad.setIdPunto(Long.parseLong(idActividadInsertar));
					
					boolean exists = puntoXSubactividadService.existePuntoXSubactividad(Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.PAR_REQUEST_IDSUBACTIVIDAD)), Integer.parseInt(idActividadInsertar));
					if(exists){
						puntoXSubactividadService.actualizarPuntoXSubactividad(puntoXSubactividad);
					}else{
						puntoXSubactividadService.insertarPuntoXSubactividad(puntoXSubactividad);
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
		model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("punto.subactividad.msg.guardado"));
		model.addAttribute(Constants.MENSAJE_ERROR, strMensajeError);
		
		return "generic/genericSave :: genericSave";
		
	}
	
	/**
	 * Find actividades asignadas.
	 *
	 * @param idSubactividad the id subactividad
	 * @return the list
	 */
	@RequestMapping(value = "/listPuntosAsignadas/{idSubactividad}", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findActividadesAsignadas(@PathVariable String idSubactividad) {
		Integer idSubactividadInteger = Integer.parseInt(idSubactividad);
		LOG.info("idSubactividad " + idSubactividad);
		return this.puntoXSubactividadService.listarPuntos(idSubactividadInteger);
	}
	
	
	/**
	 * Bandeja caudales go.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/puntoXSubactividad", method = RequestMethod.GET)
	public String bandejaCaudalesGo(HttpServletRequest request, HttpSession session,
			ModelMap model) {
		
		model.addAttribute("today",DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		
		return "puntoXSubactividad/puntoXSubactividad";
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
