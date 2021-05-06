/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ReporteWrapperBean;
import pe.com.sedapal.scr.core.services.ICaudalService;
import pe.com.sedapal.scr.core.services.IMantRioService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteGraficoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteGraficoController {

	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The caudal service. */
	@Autowired
	ICaudalService caudalService;
	
	/** The mant rio service. */
	@Autowired
	IMantRioService mantRioService;
	
	/**
	 * Método que permite redirigir a la vista de Reporte Grafico por Represas.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reportesGrafico", method = RequestMethod.GET)
	public String goToReporteGrafico(ModelMap model) throws Exception{
		
		List<SelectItemBean> riosSelect = mantRioService.obtenerRiosActivos();
		model.addAttribute("riosActivos", riosSelect);
		
		return "reportesGrafico";
	}
	
	/**
	 * Método que envía la data y metadata para la visualización del reporte gráfica en la capa de presentación.
	 *
	 * @param periodo Es el periodo (mes específico) para el cual se desea generar el reporte
	 * @param riosToGraph the rios to graph
	 * @return Objeto de tipo ReporteWrapperBean que enmascara la data y metadata para la generación del reporte
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reportesGrafico/obtenerData", method = RequestMethod.GET)
	public @ResponseBody ReporteWrapperBean obtenerData(
			@RequestParam(value="periodo") String periodo,
			@RequestParam(value="riosToGraph", required=false) String[] riosToGraph) throws Exception {
		
		ReporteWrapperBean wrapperReport = new ReporteWrapperBean();
		wrapperReport = caudalService.generarReporteResumenCaudalGrafico(periodo, riosToGraph);
		
		return wrapperReport;
	}
	
}
