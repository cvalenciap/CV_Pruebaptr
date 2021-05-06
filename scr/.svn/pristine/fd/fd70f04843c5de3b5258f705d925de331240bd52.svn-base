/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CalculoRepresasBean;
import pe.com.sedapal.scr.core.beans.CalculoResumenBean;
import pe.com.sedapal.scr.core.beans.FilaReporteRepresaBean;
import pe.com.sedapal.scr.core.services.IAlmacenamientoService;
import pe.com.sedapal.scr.core.services.IRepresasService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.ReporteXLSUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRepresasController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteRepresasController {

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The i represas service. */
	@Autowired
	IRepresasService iRepresasService;
	
	/** The i almacenamiento service. */
	@Autowired
	IAlmacenamientoService iAlmacenamientoService;
	
	/**
	 * Método que permite redirigir a la vista de Reporte de Represas por Periodo
	 * model permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/represasResumen", method = RequestMethod.GET)
	public String represas(ModelMap model) throws Exception{		

		model.addAttribute("today", DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		
		return "represasResumen";
	}	
	
	/**
	 * Método que permite obtener el reporte resumen de represas por periodo.
	 *
	 * @param periodo Es el periodo (en mes específico) para el cual se importará el detalle
	 * @return Objeto de tipo ResponseEntity con los datos del reporte
	 */
	@RequestMapping(value = "/represasResumen/getData", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getReporteRepresas(@RequestParam("periodo") String periodo) {
		List<FilaReporteRepresaBean> listReporte = null;
		try {
			listReporte = iRepresasService.generarReporteResumenRepresa(periodo);
		} 	
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error generico. Volver a intentar", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(listReporte, HttpStatus.OK);
	} 
	
	
	/**
	 * Método que permite actalizar el valor y las formulas para una fecha en el almacenamiento.
	 *
	 * @param calculo Objeto del tipo CalculoResumenBean que contiene la información del actualización
	 * @return the response entity
	 * @throws Exception the exception
	 * @Return Objeto de tipo ResponseEntity que contiene los resultados
	 */
	@RequestMapping(value = "/represasResumen/updateAlmacenamiento", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> actualizarAlmacenamiento(@RequestBody CalculoResumenBean calculo) throws Exception{
		
		List<FilaReporteRepresaBean> listReporte = null;
		BaseBean auditoria = new BaseBean();
		auditoria.setUsuarioModificacion(Util.getUserLoged());
		auditoria.setCodigoArea(0);
		auditoria.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		
		try {
			iAlmacenamientoService.actualizarAlmacenamiento(calculo.getStrFecha(), 
					calculo.getStrFormulaEmbalse(), calculo.getStrFormulaDescarga(),
					calculo.getStrFormulaUno(), calculo.getStrFormulaDos(), auditoria);
			Map<String, String> datos = pe.com.sedapal.scr.core.util.Utils.getDayAndPeriodFromDate(calculo.getStrFecha().replace("-","/"));
			listReporte = iRepresasService.generarReporteResumenRepresa(datos.get("periodo"));
		} 	
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error generico. Volver a intentar", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(listReporte, HttpStatus.OK);
	}
	
	/**
	 * Método que permite actalizar el valor y las formulas para una fecha en el registro de represas
	 * calculo Objeto del tipo CalculoRepresasBean que contiene la información de actualización.
	 *
	 * @param calculo the calculo
	 * @return the response entity
	 * @throws Exception the exception
	 * @Return Objeto de tipo ResponseEntity que contiene los resultados
	 */
	@RequestMapping(value = "/represasResumen/updateFormRepresas", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> actualizarFormulasRepresas(@RequestBody CalculoRepresasBean calculo) throws Exception{
		
		List<FilaReporteRepresaBean> listReporte = null;
		calculo.setUsuarioModificacion(Util.getUserLoged());
		calculo.setCodigoArea(0);
		calculo.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		
		try {
			iRepresasService.actualizarFormulasRepresas(calculo);
			Map<String, String> datos = pe.com.sedapal.scr.core.util.Utils.getDayAndPeriodFromDate(calculo.getStrFecha().replace("-","/"));
			listReporte = iRepresasService.generarReporteResumenRepresa(datos.get("periodo"));
		} 	
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error generico. Volver a intentar", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(listReporte, HttpStatus.OK);
	}
	
	/**
	 * Export XLS.
	 *
	 * @param periodo the periodo
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/represasResumen/export/xls", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<InputStreamResource> exportXLS(@RequestParam("periodo") String periodo) throws Exception{
		String file = Constants.CONST_NAME_REPORTE_RESUMEN_REPRESAS
                .concat(DateUtils.getIIACTranDateTime()).concat(Constants.CONST_EXTENSION_XLS);
		List<FilaReporteRepresaBean> listReporte = null;
		listReporte = iRepresasService.generarReporteResumenRepresa(periodo);
		byte[] content = ReporteXLSUtils.generarXLSReporteResumenAnio(listReporte, periodo);
        return UtilsController.getFile(content, file, Constants.CONST_APP_EXPORT_XLS);
    }
}
