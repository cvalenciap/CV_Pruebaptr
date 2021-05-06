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
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IEnsayoCloroService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class EnsayoCloroController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class EnsayoCloroController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EnsayoCloroController.class);

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The ensayo cloro service. */
	@Autowired
	IEnsayoCloroService ensayoCloroService;
	
	/**
	 * Plan operativo get.
	 *
	 * @param request the request
	 * @param session the session
	 * @param ensayoCloroSearchBean the ensayo cloro search bean
	 * @param ensayoCloroEditBean the ensayo cloro edit bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/ensayoCloroPlanta", method = RequestMethod.GET)
	public String planOperativoGet(HttpServletRequest request, HttpSession session,
			@ModelAttribute("ensayoCloroSearchBean") EnsayoCloro ensayoCloroSearchBean,
			@ModelAttribute("ensayoCloroEditBean") EnsayoCloro ensayoCloroEditBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
			
			return "ensayoCloro/ensayoCloro";
			
	}

	/**
	 * Plan operativo save.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param ensayoCloroEditBean the ensayo cloro edit bean
	 * @return the string
	 */
	@RequestMapping(value = "/ensayoCloroSave", method = RequestMethod.POST)
	public String planOperativoSave(ModelMap model, HttpServletRequest request, HttpSession session,
			@ModelAttribute("ensayoCloroEditBean") EnsayoCloro ensayoCloroEditBean){

		String strMensajeTipo = "";
		String strMensajeError = "";
		try {
			    
				LOG.info("ensayoCloroEditBean: "+ensayoCloroEditBean);
				
				//GUARDAR ENSAYO
				ensayoCloroEditBean.setUsuarioCreacion(Util.getUserLoged());
				
				int idCabecera = ensayoCloroService.guardarEnsayoCloro(ensayoCloroEditBean);
				
				//GUARDANDO DETALLES DE ENSAYO
				List<EnsayoCloroDetalle> detalles = ensayoCloroEditBean.getDetalles();
				//5 ROW TABLA 1
				if(detalles!=null){
				for (EnsayoCloroDetalle ensayoCloroDetalle : detalles) {
					ensayoCloroDetalle.setIdCabecera(idCabecera);
					ensayoCloroDetalle.setUsuarioCreacion(Util.getUserLoged());
					ensayoCloroDetalle.setTipoDetalle(ConstantsLaboratorio.TIPO_TABLA_1);
					ensayoCloroService.guardarEnsayoCloroDetalle(ensayoCloroDetalle);
				 }
				}
				//5 ROW TABLA 2
				List<EnsayoCloroDetalle> detalles2 = ensayoCloroEditBean.getDetalles2();
				if(detalles2!=null){
				for (EnsayoCloroDetalle ensayoCloroDetalle2 : detalles2) {
					ensayoCloroDetalle2.setIdCabecera(idCabecera);
					ensayoCloroDetalle2.setUsuarioCreacion(Util.getUserLoged());
					ensayoCloroDetalle2.setTipoDetalle(ConstantsLaboratorio.TIPO_TABLA_2);
					ensayoCloroService.guardarEnsayoCloroDetalle(ensayoCloroDetalle2);
				 }
				}
				
				//1 ROW BLANCO TABLA 2
				List<EnsayoCloroDetalle> detalles3 = ensayoCloroEditBean.getDetalles3();
				if(detalles3!=null){
				for (EnsayoCloroDetalle ensayoCloroDetalle3 : detalles3) {
					ensayoCloroDetalle3.setIdCabecera(idCabecera);
					ensayoCloroDetalle3.setUsuarioCreacion(Util.getUserLoged());
					ensayoCloroDetalle3.setTipoDetalle(ConstantsLaboratorio.TIPO_TABLA_3);
					ensayoCloroService.guardarEnsayoCloroDetalle(ensayoCloroDetalle3);
				 }
				}
				
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje("plan.operativo.msg.guardado"));
				model.addAttribute("ensayoCloroEditBean",ensayoCloroEditBean);
				
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
	 * Buscar ensayo cloro.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/buscarEnsayoCloro/{id}", method = RequestMethod.POST)
	public String buscarEnsayoCloro(HttpServletRequest request, HttpSession session,
			Model model, @PathVariable int id){
			EnsayoCloro ensayo;
			
			try {
			ensayo =  ensayoCloroService.buscarEnsayoCloroXId(id);
			List<EnsayoCloroDetalle> detallesEnsayo;
			if(id!=-1){	
				detallesEnsayo = ensayoCloroService.obtenerListEnsayoCloro(id);
				
				if(detallesEnsayo!=null){
				// se reasigna los detalles en 2 listados
				for (EnsayoCloroDetalle detalle : detallesEnsayo) {
					if(ConstantsLaboratorio.TIPO_TABLA_1.equals(detalle.getTipoDetalle())){
						ensayo.addDetalle1(detalle);
					}else if(ConstantsLaboratorio.TIPO_TABLA_2.equals(detalle.getTipoDetalle())){
						ensayo.addDetalle2(detalle);
					}
					else if(ConstantsLaboratorio.TIPO_TABLA_3.equals(detalle.getTipoDetalle())){
						// SE AGREGA EL ULTIMO ROW
						ensayo.addDetalle3(detalle);
					}
				}
				}
				
			} 
			else {
				ensayo = new EnsayoCloro();
				
				EnsayoCloroDetalle detalle;
				for (int i = 0; i < 11; i++) {
					
					detalle = new EnsayoCloroDetalle();
					detalle.setVolumen(ConstantsLaboratorio.CERO_DOUBLE);
					detalle.setCl2Libre(ConstantsLaboratorio.CERO_DOUBLE);
					detalle.setCl2Total(ConstantsLaboratorio.CERO_DOUBLE);
					detalle.setCl2Comb(ConstantsLaboratorio.CERO_DOUBLE);
					detalle.setCloroLibre(ConstantsLaboratorio.CERO_DOUBLE);
					
					if(i < ConstantsLaboratorio.NRO_GRILLAS_TABLA_ENSAYO){
						ensayo.addDetalle1(detalle);
					} else if(i<ConstantsLaboratorio.INDICE_GRILLA_BLANCLO){
						ensayo.addDetalle2(detalle);
					} else if(i== ConstantsLaboratorio.INDICE_GRILLA_BLANCLO){
						detalle.setDosis(ConstantsLaboratorio.CERO_DOUBLE);
						detalle.setCloroTotal(ConstantsLaboratorio.CERO_DOUBLE);
						detalle.setDemandaCloro(ConstantsLaboratorio.CERO_DOUBLE);
						ensayo.addDetalle3(detalle);
					}
					
				}
			}
			
			LOG.info("Ensayo encontrado: " + ensayo);
			
			model.addAttribute("ensayoCloroEditBean",ensayo);
			} catch (Exception e) {
				LOG.info("ERROR",e);
				e.printStackTrace();
			}
			
			return "ensayoCloro/ensayoCloroTable :: resultsList";
	}
	
	
	

	/**
	 * Cargar ensayo xfecha.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @return the string
	 */
	@RequestMapping(value = "/cargarEnsayoXfecha", method = RequestMethod.POST)
	public String cargarEnsayoXfecha(ModelMap model, HttpServletRequest request, HttpSession session) {
		return "ensayoCloro/ensayoCloroBandeja :: resultsList";
	}
	
	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 */
	@RequestMapping(value = "/ensayoCloro/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams) {
		try {
			
			EnsayoCloro ensayoCloro = new EnsayoCloro();
			int estadoAprobado = Constants.ACTIVO;
			
			String fechaMuestreo = allRequestParams.get("fechaMuestreo");
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby(Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.ORDER_COLUMN_TABLE))==0?Integer.parseInt(allRequestParams.get(ConstantsLaboratorio.ORDER_COLUMN_TABLE))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			ensayoCloro.setFechaMuestreo(fechaMuestreo);

			Result result = ensayoCloroService.obtenerDatosEnsayoCloro(ensayoCloro, paginacion);
			LOG.info("DATA : " + result.getData());
			
			Map<String,Object> dtResponse = new HashMap<>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());
					
			dtResponse.put("aprobado", estadoAprobado);
		
			return new ResponseEntity<>(dtResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		@RequestMapping(value = "/refrescarEnsayosCloroXFecha", method = RequestMethod.GET)
		public String refrescarReportePorFecha(HttpServletRequest request, HttpSession session,
				Model model,@RequestParam Map<String,String> allRequestParams){
			   return "ensayoCloro/ensayoCloroBandeja :: resultsList";
		}
		
		
	
		/**
		 * Inactivar plan operativo.
		 *
		 * @param model the model
		 * @param request the request
		 * @param session the session
		 * @param allRequestParams the all request params
		 * @param id the id
		 * @return the string
		 */
		@RequestMapping(value = "/inactivarEnsayoCloro/{id}", method = RequestMethod.POST)
		public String inactivarPlanOperativo(ModelMap model, HttpServletRequest request, HttpSession session,
				@RequestParam Map<String,String> allRequestParams,@PathVariable int id) {
			
			String strMensajeTipo = "";
			String strMensajeError = "";
			
			EnsayoCloro ensayoCloro = new EnsayoCloro();
			
			try {
				ensayoCloro.setPrograma(ConstantsLaboratorio.PROGRAMA_INACTIVAR_ENSAYOCLORO);
				ensayoCloro.setUsuarioCreacion(Util.getUserLoged());
				ensayoCloro.setCodigo(id);

				ensayoCloroService.inactivarEnsayoCloro(ensayoCloro);
							
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
		 * Obtener mensaje.
		 *
		 * @param messageProp the message prop
		 * @return the string
		 */
		private String obtenerMensaje(String messageProp){
			return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
		}
	
	
}
