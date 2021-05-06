/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.scr.core.beans.DatosEnvioCorreoBean;
import pe.com.sedapal.scr.core.services.IProcesoService;
import pe.com.sedapal.scr.core.services.IReporteGerenciaService;
import pe.com.sedapal.scr.correo.job.ETLReporteGerenciaJob;

// TODO: Auto-generated Javadoc
/**
 * The Class EnvioReporteGerenciaController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class EnvioReporteGerenciaController {
	
	/** The proceso service. */
	@Autowired
	IProcesoService procesoService;

	/** The etl reporte gerencia job. */
	@Autowired
	ETLReporteGerenciaJob etlReporteGerenciaJob;
	
	/** The reporte gerencia service. */
	@Autowired
	IReporteGerenciaService reporteGerenciaService;
	
	/**
	 * Método que permite redirigir a la vista de Envio de Reporte de Gerencia.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/correoGerencia", method = RequestMethod.GET)
	public String bandejaCaudalesGo(ModelMap model) throws Exception {
		DatosEnvioCorreoBean bean = procesoService.obtenerCorreoGerenciaEnviar();
		
		model.addAttribute("correoGerenciaBean", bean);
		
		return "correoGerencia";
	}
	
	/**
	 * Método que permite ejecutar a demanda el proceso de envío de correo de reporte resumen de gerencia.
	 *
	 * @return Objeto de tipo ResponseBody con el mensaje de finalización del proceso
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/correoGerencia/ejecutar", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> reporteGerencia() throws Exception {
		try {
			etlReporteGerenciaJob.execute(reporteGerenciaService.generarCuadroResumenReporte());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("El correo no pudo ser enviado", HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<String>("El correo ha sido enviado", HttpStatus.OK);
	}
}
