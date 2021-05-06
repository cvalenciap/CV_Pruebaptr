/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.services.ITipoProcesoService;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoProcesoController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class TipoProcesoController {

	/** The tipo proceso service. */
	@Autowired
	ITipoProcesoService tipoProcesoService;
	
	/**
	 * Método que permite enviar a al frontend el listado de tipos de proceso.
	 *
	 * @return lista de objetos de tipo SelectItemBean con la información de tipos de proceso
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/listTiposProceso", method = RequestMethod.GET)
	public @ResponseBody List<SelectItemBean> findAll() throws Exception {
		return this.tipoProcesoService.obtenerAllTipoProceso();
	}
}
