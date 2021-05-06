/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import pe.com.sedapal.scr.core.services.IManiobraService;
import pe.com.sedapal.scr.core.services.IMantRepresaService;
import pe.com.sedapal.scr.core.services.IRepresasService;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteCaudalesController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteCaudalesController {

	/** The mant represa service. */
	@Autowired
	IMantRepresaService mantRepresaService;
	
	/** The maniobra service. */
	@Autowired
	IManiobraService maniobraService;
	
	/** The represas service. */
	@Autowired
	IRepresasService represasService;
	
	/**
	 * Método que permite redirigir a la vista de Reporte Gráfico de Caudales.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reporteCaudales", method = RequestMethod.GET)
	public String contratos(ModelMap model) throws Exception{
		
		List<SelectItemBean> tiposManiobra = maniobraService.obtenerTiposManiobra();
		
		model.addAttribute("tiposManiobra", tiposManiobra);
		
		return "reporteCaudales";
	}
	
	/**
	 * Método que envía la data y metadata para la visualización del reporte gráfico de caudales en la capa de presentación.
	 *
	 * @param periodo Es el periodo (mes específico) para el cual se desea generar el reporte
	 * @param maniobrasToGraph Es la colección de códigos de maniobras para los que se desea mostrar la información en el reporte
	 * @param response the response
	 * @return Objeto de tipo ReporteWrapperBean que enmascara la data y metadata para la generación del reporte
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/reporteCaudales/obtenerData", method = RequestMethod.GET)
	public @ResponseBody ReporteWrapperBean obtenerData(
			@RequestParam(value="periodo") String periodo,
			@RequestParam(value="maniobrasToGraph", required=false) String[] maniobrasToGraph,
			 HttpServletResponse response) throws Exception {
		
		ReporteWrapperBean wrapperReport = new ReporteWrapperBean();
		wrapperReport = represasService.generarReporteRepManiobraGrafico(periodo, maniobrasToGraph);
		
		return wrapperReport;
	}
}
