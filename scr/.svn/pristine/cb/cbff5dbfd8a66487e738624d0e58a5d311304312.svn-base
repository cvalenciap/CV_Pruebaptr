/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CaudalDetalleBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.exception.ValidationFileException;
import pe.com.sedapal.scr.core.services.ICaudalDetalleService;
import pe.com.sedapal.scr.core.services.ICaudalService;
import pe.com.sedapal.scr.core.util.FileUtils;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class BandejaCaudalesDetalleController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class BandejaCaudalesDetalleController {

	/** The env. */
	@Autowired
	Environment env;
	
	/** The caudal service. */
	@Autowired
	ICaudalService caudalService;
	
	/** The caudal detalle service. */
	@Autowired
	ICaudalDetalleService caudalDetalleService;
	
	/**
	 * Método que permite redirigir a la vista de Detalle de Caudales.
	 *
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param caudalId Es el código del caudal del que se mostrará el detalle
	 * @param rioId Es el código del río del que se mostrará el detalle
	 * @param periodo Es el periodo (en mes específico) para el cual se mostrará el detalle
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/bandejaCaudalesDetalle", method = RequestMethod.GET)
	public String verDetalle(ModelMap model,
			@RequestParam("caudalId") Integer caudalId,
			@RequestParam("rioId") Integer rioId,
			@RequestParam("periodo") String periodo) throws Exception {
		
		if(caudalId!=null&&rioId!=null&&periodo!=null){
			CaudalDetalleBean detalle = caudalService.obtenerDetalle(caudalId, rioId, periodo);
			
			model.addAttribute("detalle", detalle);
			model.addAttribute("rioId", rioId);
			
			return "bandejaCaudalesDetalle";
		}
		else{
			return "redirect:/bandejaCaudales";
		}
	}
	
	/**
	 * Método que permite importar el archivo XLS para caudales.
	 *
	 * @param uploadfile the uploadfile
	 * @param caudalId Es el código del caudal del que se importará el detalle
	 * @param rioId Es el código del río del que se importará el detalle
	 * @param periodo Es el periodo (en mes específico) para el cual se importará el detalle
	 * @return Objeto de tipo ResponseEntity con información sobre la subida del archivo
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/bandejaCaudalesDetalle/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("caudalId") Integer caudalId,
			@RequestParam("rioId") Integer rioId,
			@RequestParam("periodo") String periodo) {
		List<List<String>> values = null;
		Map<String, List<List<CeldaMatrizBean>>> viewLists = null;
		try {
			//Borramos todos los temporales de caudales para este usuario
			FileUtils.deleteFiles(Constants.TEMP_FILENAME_CAUDALES, Util.getUserLoged(), Constants.TEMP_CAUDAL_EXTENSION);
			caudalDetalleService.validaExtensionFile(uploadfile.getOriginalFilename());
			values = FileUtils.readXlsFile(uploadfile.getInputStream(), Constants.NUMERO_HOJA_DATA);
			caudalDetalleService.validaInformacionUploaded(values, periodo);
			// Volvemos a llamar porque hay excepciones al trabajar con esta lista en sesion
			CaudalDetalleBean detalle = caudalService.obtenerDetalle(caudalId, rioId, periodo);
			viewLists = caudalService.prepararMatricesComparadas(values, detalle.getLstDetalles(), periodo);
			FileUtils.saveFile(uploadfile, Constants.TEMP_FILENAME_CAUDALES, Util.getUserLoged(), Constants.TEMP_CAUDAL_EXTENSION);
		} 	
		catch (ValidationFileException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Error generico. Volver a intentar", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(viewLists,HttpStatus.OK);
	} 
	
	/**
	 * Método que permite guardar en base de datos la información del archivo de importación.
	 *
	 * @param allRequestParams Es una estructura de datos que enmascara todos los parámetros del request
	 * @return Objeto de tipo ResponseEntity con información sobre el registro de datos
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/bandejaCaudalesDetalle/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> registrarDetalles(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		
		Map<String, Object> response = new HashMap<String, Object>();
		BaseBean auditoria = new BaseBean();
		auditoria.setUsuarioModificacion(Util.getUserLoged());
		auditoria.setPrograma(Util.getPageCall());
		auditoria.setCodigoArea(0);
		auditoria.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		
		try {
			String caudalId = allRequestParams.get("caudalId");
			String periodo = allRequestParams.get("periodo");
			Integer caudalIdent = null;
			if(caudalId!=null){
				caudalIdent = Integer.parseInt(caudalId);
			}
			
			// Recuperamos el archivo temporal
			List<List<String>> valuesFromFile = FileUtils.readXlsFile(FileUtils.getFileStream(Constants.TEMP_FILENAME_CAUDALES, Util.getUserLoged(), Constants.TEMP_CAUDAL_EXTENSION), 
					Constants.NUMERO_HOJA_DATA);
			List<List<CeldaMatrizBean>> values = caudalService.prepararDataExcel(valuesFromFile, periodo);
			
			caudalDetalleService.registrarCaudalDetalle(values, caudalIdent, auditoria);
			CaudalDetalleBean detalle = caudalService.obtenerDetalle(caudalIdent, null, periodo);
			
			response.put("detalle", detalle);
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		} catch (Exception e) {			
			e.printStackTrace();
			response.put("errorMessage", Constants.CARGA_MANUAL_ERROR_GENERICO);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Método que permite descargar un archivo plantilla para la importación de caudales.
	 *
	 * @param response Es el objeto que enmascara todos los datos de la respuesta a peticiones
	 */
	@RequestMapping(value="/bandejaCaudalesDetalle/downloadTemplate", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) {
        try {
			FileUtils.downloadFile(Constants.TEMPLATE_CAUDAL_FILE, Constants.CAUDAL_DOWNLOAD_BUFFERSIZE, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
