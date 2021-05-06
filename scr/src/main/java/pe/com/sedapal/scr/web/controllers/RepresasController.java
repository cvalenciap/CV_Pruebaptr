/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.ManiobraBean;
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.beans.Result;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.exception.ValidationFileException;
import pe.com.sedapal.scr.core.services.IMantClimaService;
import pe.com.sedapal.scr.core.services.IMantColaboradorService;
import pe.com.sedapal.scr.core.services.IMantRepresaService;
import pe.com.sedapal.scr.core.services.IRepresasService;
import pe.com.sedapal.scr.core.util.FileUtils;
import pe.com.sedapal.scr.web.common.Util;


// TODO: Auto-generated Javadoc
/**
 * The Class RepresasController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class RepresasController {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
	/** The servlet context. */
	@Autowired
	ServletContext servletContext;
	
	/** The i represas service. */
	@Autowired
	IRepresasService iRepresasService;
	
	/** The i mant represa service. */
	@Autowired
	IMantRepresaService iMantRepresaService;
	
	/** The i mant colaborador service. */
	@Autowired
	IMantColaboradorService iMantColaboradorService;
	
	/** The i mant clima service. */
	@Autowired
	IMantClimaService iMantClimaService;
	
	/**
	 * Método que permite redirigir a la vista de Mantenimiento de Represas.
	 *
	 * @param request the request
	 * @param session the session
	 * @param represasSearchBean the represas search bean
	 * @param represasEditBean the represas edit bean
	 * @param maniobrasEditBean the maniobras edit bean
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 * @ModelAttribute("represasSearchBean") Objeto de tipo RepresasBean que se utiliza como atributo para la búsqueda de represas
	 * @ModelAttribute("represasEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/represas", method = RequestMethod.GET)
	public String represas(HttpServletRequest request, HttpSession session,
			@ModelAttribute("represasSearchBean") RepresasBean represasSearchBean,
			@ModelAttribute("represasEditBean") RepresasBean represasEditBean,
			@ModelAttribute("maniobrasEditBean") ManiobraBean maniobrasEditBean,
			ModelMap model) throws Exception{		

		model.addAttribute("represasActivas", iMantRepresaService.obtenerRepresasActivas());
		model.addAttribute("represasTodas", iMantRepresaService.obtenerRepresasTodas());
		
		model.addAttribute("colaboradoresActivos", iMantColaboradorService.obtenerColaboradoresActivos());
		model.addAttribute("colaboradoresTodos", iMantColaboradorService.obtenerColaboradoresTodos());
		
		model.addAttribute("climasActivos", iMantClimaService.obtenerClimasActivos());
		model.addAttribute("climasTodos", iMantClimaService.obtenerClimasTodos());

		return "represas";
	}	
	
	/**
	 * Método que permite realizar los filtros de búsqueda y luego mostrar la grilla paginada.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 * @RequestParam Se encarga de enviar los parámetros para los filtro de búsqueda
	 */
	@RequestMapping(value = "/represas/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> buscarRepresas(@RequestParam Map<String,String> allRequestParams)
			throws Exception {
		
		try {
			RepresasBean represasBean = new RepresasBean();
			represasBean.setRepresa(new RepresaBean());
			Paginacion paginacion = new Paginacion();
			
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby( Integer.parseInt(allRequestParams.get("order[0][column]"))==0?Integer.parseInt(allRequestParams.get("order[0][column]"))+1:Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));			
			
			if(allRequestParams.get("nombreRepresa")!=null && !"".equals(allRequestParams.get("nombreRepresa"))) {				
				represasBean.getRepresa().setNombreRepresa(allRequestParams.get("nombreRepresa").toUpperCase());
			}

			if(allRequestParams.get("numeroPrecipitacion")!=null && !"".equals(allRequestParams.get("numeroPrecipitacion"))) {
				represasBean.setStrNumeroPrecipitacion(allRequestParams.get("numeroPrecipitacion").toUpperCase());
			}
			
			if(allRequestParams.get("nombreRepresa").equals("")){
				represasBean.getRepresa().setNombreRepresa("");
			}
			
			if(allRequestParams.get("numeroPrecipitacion").equals("")){
				represasBean.setStrNumeroPrecipitacion("");
			}
			
			Result result = iRepresasService.buscarRepresas(represasBean, paginacion);
			
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
	 * Este método inactiva el estado del registro de Represas
	 * ModelMap es una interfaz de Map, que permite la abstracción completa de la tecnología de vista.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdRepresas the inac id represas
	 * @return the string
	 * @RequestParam inacIdRio es el parámetro que sirve para cambiar de estado
	 */
	@RequestMapping(value = "/represas/inactivar", method = RequestMethod.POST)
	public String inactivarRepresas(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdRepresas) {
		
		String strMensajeTipo = "";
		String strMensajeError = "";

		RepresasBean represasBean = new RepresasBean();
		represasBean.setIntCodigo(Integer.parseInt(inacIdRepresas));
		represasBean.setUsuarioModificacion(Util.getUserLoged());
		represasBean.setPrograma(Util.getPageCall());		
		represasBean.setCodigoArea(0);
		represasBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {			
			iRepresasService.inactivarRepresas(represasBean);
			strMensajeTipo = ConstantsCommon.INACTIVADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.INACTIVADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("btnBusqueda", pe.com.sedapal.scr.core.common.Constants.BTN_BUSCAR_REPRESAS);
		return "represasSave :: mensajesSave";

	}
	
	/**
	 * Método que permite registrar y/o actualizar un registro de Represas.
	 *
	 * @param represasSearchBean the represas search bean
	 * @param represasEditBean the represas edit bean
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 * @ModelAttribute("represasSearchBean") Objeto de tipo RepresasBean que se utiliza como atributo para la búsqueda de rios
	 * @ModelAttribute("represasEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap es una interfaz de Map que permite la abstracción completa de la tecnología de vista
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@RequestMapping(value = "/represasSave", method = RequestMethod.POST)
	public String registrarActualizarRepresas(
			@Valid @ModelAttribute("represasSearchBean") RepresasBean represasSearchBean,
			@Valid @ModelAttribute("represasEditBean") RepresasBean represasEditBean,
			BindingResult bindingResult, ModelMap model){
		
		String strMensajeTipo = "";
		String strMensajeError = "";
		String strCodigoRepresas = "";
		String strFechaRepresas = "";
		String strNumeroPrecipitacion = "";
		
		represasEditBean.setUsuarioCreacion(Util.getUserLoged());
		represasEditBean.setUsuarioModificacion(Util.getUserLoged());
		represasEditBean.setPrograma(Util.getPageCall());
		
		represasEditBean.setCodigoArea(0);
		represasEditBean.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));

		try {
			if(represasEditBean.getCodigo()!=null && !represasEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(represasEditBean.getCodigo())>0 ){
				iRepresasService.actualizarRepresas(represasEditBean);	
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
			}else{				
				Integer estado = ConstantsCommon.ESTADO_ACTIVO;
				represasEditBean.setStrEstado(estado.toString()); 
				RepresasBean insertado = iRepresasService.registrarRepresas(represasEditBean);
				strCodigoRepresas = String.valueOf(insertado.getIntCodigo());	
				strNumeroPrecipitacion = insertado.getStrNumeroPrecipitacion();
				RepresasBean represas = iRepresasService.obtenerRepresas(Integer.parseInt(strCodigoRepresas));
				strFechaRepresas = represas.getStrFechaRegistro();
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
			}
		} catch (Exception e) {
			if(represasEditBean.getCodigo()!=null && !represasEditBean.getCodigo().trim().equals("")
					&& Integer.parseInt(represasEditBean.getCodigo())>0 ){
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_NO_OK;
			}
			else{
				strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			}
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}
		
		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		model.addAttribute("codigoRepresas", strCodigoRepresas);
		model.addAttribute("fechaRepresas", strFechaRepresas);
		model.addAttribute("numPrecipitacion", strNumeroPrecipitacion);
		
		return "represasSave :: mensajesSave";
		
	}
	
	/**
	 * Método que permite obtener un registro de Represas por id.
	 *
	 * @param represasSearchBean the represas search bean
	 * @param represasEditBean the represas edit bean
	 * @param model the model
	 * @param idrepresas the idrepresas
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @ModelAttribute("represasSearchBean") Objeto de tipo RepresasBean que se utiliza como atributo para la búsqueda de represas
	 * @ModelAttribute("represasEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * Model es una interfaz de Map , que permite la abstracción completa de la tecnología de vista
	 * @PathVariable  identifica al id como variable
	 */
	@RequestMapping(value = "/represasGet/{idrepresas}", method = RequestMethod.GET)
	public String tipoProcesoGetPage(
			@ModelAttribute("represasSearchBean") RepresasBean represasSearchBean,
			@ModelAttribute("represasEditBean") RepresasBean represasEditBean, Model model,
			@PathVariable Integer idrepresas) throws Exception {
		if(idrepresas!=null && idrepresas>0){
			represasEditBean = iRepresasService.obtenerRepresas(idrepresas);
		}else{
			represasEditBean = new RepresasBean();  
		}
		model.addAttribute("represasEditBean", represasEditBean);
		return "represasEdit :: form-edit-represas";
	}
	
	/**
	 * Método que permite hacer upload a un archivo XLS.
	 *
	 * @param uploadfile the uploadfile
	 * @param session the session
	 * @return objeto de tipo ResponseEntity con información de la operación
	 */
	@RequestMapping(value = "/represas/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			HttpSession session) {
		
		List<List<String>> values = null;
		List<List<String>> erroresValidacion = new ArrayList<>();
		List<List<String>> registrosCorrectos = new ArrayList<>();
		Map<String, List<List<String>>> resultadoValidacion = null;
		try {
			
			String errorMessageExtension = iRepresasService.validaExtensionFile(uploadfile.getOriginalFilename());
			
			if(!errorMessageExtension.equals("")){
				return new ResponseEntity<>(errorMessageExtension, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			values = FileUtils.readXlsFile(uploadfile.getInputStream(), pe.com.sedapal.scr.core.common.Constants.NUMERO_HOJA_DATA);
			resultadoValidacion = iRepresasService.validaInformacionImportar(values);
			erroresValidacion = resultadoValidacion.get("badData");
			registrosCorrectos = resultadoValidacion.get("goodData");
			
			session.setAttribute("valuesImport", registrosCorrectos);
		} 	
		catch (ValidationFileException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Volver a intentar", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(erroresValidacion,HttpStatus.OK);
	} 
	
	/**
	 * Método que permite guardar los datos del archivo en base de datos.
	 *
	 * @param session Es el objeto que mantiene la información de la sesión
	 * @return objeto de tipo ResponseEntity con información de la operación
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/represas/import", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String,Object>> guardarImportacion(HttpSession session)
			throws Exception {
		
		Map<String, Object> response = new HashMap<String, Object>();
		BaseBean auditoria = new BaseBean();
		auditoria.setUsuarioModificacion(Util.getUserLoged());
		auditoria.setUsuarioCreacion(Util.getUserLoged());
		auditoria.setPrograma(Util.getPageCall());
		auditoria.setCodigoArea(0);
		auditoria.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		
		try {
			List<List<String>> values = (List<List<String>>)session.getAttribute("valuesImport");
			if(values.size() > 0){
				iRepresasService.registrarRepresasImport(values, auditoria);
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
			}
			else{
				response.put("errorMessage", pe.com.sedapal.scr.core.common.Constants.IMP_REPRESAS_CERO_DATOS_IMPORTADOS);
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {		
			e.printStackTrace();
			response.put("errorMessage", pe.com.sedapal.scr.core.common.Constants.IMP_REPRESAS_ERROR_GENERICO);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Método que permite hacer download de un archivo.
	 *
	 * @param response Es el objeto que mantiene la información de respuesta a la petición
	 */
	@RequestMapping(value="/represas/downloadTemplate", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) {
        try {
			FileUtils.downloadFile(Constants.TEMPLATE_REPRESAS_FILE, Constants.REPRESAS_DOWNLOAD_BUFFERSIZE, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * Método que permite enviar a al frontend la cantidad de represas activas por fecha.
	 *
	 * @param fechaRegistro Es la fecha que se registrará el registor de represas
	 * @param codRepresa the cod represa
	 * @return Objeto de tipo String con la información
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/represas/getCantActivasFecha", method = RequestMethod.GET)
	public @ResponseBody String getCantidadRepresasFecha(@RequestParam String fechaRegistro,
			@RequestParam String codRepresa) throws Exception {
		String cantidadDefault = "0";
		if(fechaRegistro != null && fechaRegistro.trim().length()!=0 && NumberUtils.isNumber(codRepresa)){
			cantidadDefault = iRepresasService.obtenerCatidadRepresasActivaPorFecha(fechaRegistro, Integer.parseInt(codRepresa));
		}
		return cantidadDefault;
	}
	
	/**
	 * Método que permite enviar al frontend el aforo del río rímac.
	 *
	 * @param fechaRegistro Es la fecha que se registrará el registor de represas
	 * @return Objeto de tipo String con la información
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/represas/getAforo", method = RequestMethod.GET)
	public @ResponseBody String findAforoRioRimac(@RequestParam String fechaRegistro) throws Exception {
		String aforoDefault = "0";
		if(fechaRegistro != null && fechaRegistro.trim().length()!=0){
			aforoDefault = iRepresasService.obtenerAforoRioRimac(fechaRegistro);
		}
		return aforoDefault;
	}
	
	/**
	 * Método que permite enviar al frontend el valor de la formula compelemtaria para volumen trasvasado.
	 *
	 * @return Objeto de tipo String con la información
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/represas/getFormulaTrasv", method = RequestMethod.GET)
	public @ResponseBody String findFactorVolumenTrasvasado() throws Exception {
		String factorDefault = iRepresasService.obtenerFormulaVolumenTrasvasado();
		return factorDefault;
	}
	
	/**
	 * Método que permite enviar datos para cargar combos en represas.
	 *
	 * @return the response entity
	 * @throws Exception the exception
	 * @ModelAttribute("represasSearchBean") Objeto de tipo RepresasBean que se utiliza como atributo para la búsqueda de represas
	 * @ModelAttribute("represasEditBean") Esta clase sirve como soporte genérico de modelo para Servlet
	 * ModelMap permite la abstracción completa de la tecnología de vista
	 */
	@RequestMapping(value = "/represas/datosCombos", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> represas() throws Exception{		
		
		Map<String, Object> mapValues = new HashMap<String, Object>();
		mapValues.put("represasActivas", iMantRepresaService.obtenerRepresasActivas());
		mapValues.put("represasTodas", iMantRepresaService.obtenerRepresasTodas());
		
		mapValues.put("colaboradoresActivos", iMantColaboradorService.obtenerColaboradoresActivos());
		mapValues.put("colaboradoresTodos", iMantColaboradorService.obtenerColaboradoresTodos());
		
		mapValues.put("climasActivos", iMantClimaService.obtenerClimasActivos());
		mapValues.put("climasTodos", iMantClimaService.obtenerClimasTodos());

		return new ResponseEntity<Map<String,Object>>(mapValues, HttpStatus.OK);
	}
}
