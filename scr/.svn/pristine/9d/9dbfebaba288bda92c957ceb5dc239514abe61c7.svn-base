/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import pe.com.sedapal.scr.core.beans.Formulario314;
import pe.com.sedapal.scr.core.beans.ReporteForm312Bean;
import pe.com.sedapal.scr.core.services.IFormulario312Service;
import pe.com.sedapal.scr.core.services.IFormulario314Service;
import pe.com.sedapal.scr.core.util.Utils;
import pe.com.sedapal.scr.web.common.Constants;



// TODO: Auto-generated Javadoc
/**
 * The Class Formulario312Controller.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class Formulario312Controller {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The iformulario 312 service. */
	@Autowired
	IFormulario312Service iformulario312Service;
	
	/** The iformulario 314 service. */
	@Autowired
	IFormulario314Service iformulario314Service;
	
	/**
	 * Go to formulario 312.
	 *
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario312", method = RequestMethod.GET)
	public String goToFormulario312(ModelMap model) throws Exception{
		
		return "formulario312/formulario312";
	}
	
	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario312/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> obtenerDatos(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		try {
			Formulario314 formulario314 = new Formulario314();
			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));
			
			if(allRequestParams.get("pFechaIni") == null || allRequestParams.get("pFechaIni").trim().equals("")){
				formulario314.setStrFechaIni("31/12/2999");
			}else{
				formulario314.setStrFechaIni(allRequestParams.get("pFechaIni"));
			}
			
			if(allRequestParams.get("pFechaFin") == null || allRequestParams.get("pFechaFin").trim().equals("")){
				formulario314.setStrFechaFin("31/12/2999");
			}else{
				formulario314.setStrFechaFin(allRequestParams.get("pFechaFin"));
			}
			
			
			if(allRequestParams.get("pTipo")!=null && !"".equals(allRequestParams.get("pTipo"))){ //campo codigo viene desde turbidimetroDigitalTable
				formulario314.setTipoEnsayo(Integer.parseInt(allRequestParams.get("pTipo")));
				
			}else{
				formulario314.setTipoEnsayo(0);
			}
			
			Result result =   iformulario314Service.getListadoFormulario314(formulario314, paginacion);
						
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
	 * Obtener data.
	 *
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param cboTipoEnsayo the cbo tipo ensayo
	 * @return the reporte form 312 bean
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reportesGrafico312/obtenerData", method = RequestMethod.GET)
	public @ResponseBody ReporteForm312Bean obtenerData(
			@RequestParam(value="fechaIni") String fechaIni,
			@RequestParam(value="fechaFin") String fechaFin,
			@RequestParam(value="cboTipoEnsayo") int cboTipoEnsayo) throws Exception {
		
		ReporteForm312Bean wrapperReport = new ReporteForm312Bean();
		wrapperReport = iformulario312Service.generarReporteResumenGrafico(fechaIni, fechaFin, cboTipoEnsayo);
		
		return wrapperReport;
	}	

	
}
