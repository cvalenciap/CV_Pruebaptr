/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.CaudalSearchBean;
import pe.com.sedapal.scr.core.services.ICargaService;
import pe.com.sedapal.scr.web.common.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class HistorialCargasController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class HistorialCargasController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The carga service. */
	@Autowired
	ICargaService cargaService;
	
	/**
	 * Método que permite redirigir a la vista de Histórico de Cargas.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/historialCargas", method = RequestMethod.GET)
	public String bandejaCaudalesGo(ModelMap model) throws Exception {
		
		model.addAttribute("today",DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		
		return "historialCargas";
	}
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla paginada.
	 *
	 * @param allRequestParams Es el objeto que contiene todos los parámetros enviados en el request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/historialCargas/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			CaudalSearchBean  caudalSearchBean = new CaudalSearchBean();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("codTipoProceso")!=null && !"".equals(allRequestParams.get("codTipoProceso"))){
				caudalSearchBean.setStrTipoProceso(allRequestParams.get("codTipoProceso"));
			}
			
			if(allRequestParams.get("periodo")!=null && !"".equals(allRequestParams.get("periodo"))){
				caudalSearchBean.setStrPeriodo(allRequestParams.get("periodo"));
			}

			Result result = cargaService.buscarCargas(paginacion);
			
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
}
