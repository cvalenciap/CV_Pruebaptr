/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import net.sf.jasperreports.engine.JRParameter;
import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.ReporteUtil;
import pe.com.sedapal.scr.core.beans.BeanComodin;
import pe.com.sedapal.scr.core.beans.Formulario153;
import pe.com.sedapal.scr.core.beans.FormularioDetalle242;
import pe.com.sedapal.scr.core.beans.FormularioHeader242;
import pe.com.sedapal.scr.core.beans.ReporteMuestraPdf;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.IRegistroDeMuestraService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

// TODO: Auto-generated Javadoc
/**
 * The Class MuestraController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class MuestraController {

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

	/** The i registro de muestra service. */
	@Autowired
	IRegistroDeMuestraService iRegistroDeMuestraService;

	/** The cbo punto muestreo. */
	private List<BeanComodin> cboPuntoMuestreo = null;
	
	/** The cbo matriz. */
	private List<BeanComodin> cboMatriz = null;
	
	/** The cbo tipo analisis. */
	private List<BeanComodin> cboTipoAnalisis = null;
	
	/** The cbo tipo frasco. */
	private List<BeanComodin> cboTipoFrasco = null;
	
	/** The cbo persona muestrea. */
	private List<BeanComodin> cboPersonaMuestrea = null;
	
	/** The cbo recepcion analista. */
	private List<BeanComodin> cboRecepcionAnalista = null;

	/**
	 * Clima.
	 *
	 * @param request the request
	 * @param session the session
	 * @param formulario153 the formulario 153
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formulario153", method = RequestMethod.GET)
	public String clima(HttpServletRequest request, HttpSession session,
			@ModelAttribute("formulario153") Formulario153 formulario153, ModelMap model) throws Exception {

		cboPuntoMuestreo = iRegistroDeMuestraService.obtenerPuntoMuestreoCbo();
		cboMatriz = iRegistroDeMuestraService.obtenerMatrizCbo();
		cboTipoAnalisis = iRegistroDeMuestraService.obtenerTipoAnalisisCbo();
		cboTipoFrasco = iRegistroDeMuestraService.obtenerTipoFrascoCbo();
		cboPersonaMuestrea = iRegistroDeMuestraService.obtenerPersonaMuestreaCbo();
		cboRecepcionAnalista = iRegistroDeMuestraService.obtenerRecepAnalistaCbo();

		model.addAttribute("cboPuntoMuestreo", cboPuntoMuestreo);
		model.addAttribute("cboMatriz", cboMatriz);
		model.addAttribute("cboTipoAnalisis", cboTipoAnalisis);
		model.addAttribute("cboTipoFrasco", cboTipoFrasco);
		model.addAttribute("cboPersonaMuestrea", cboPersonaMuestrea);
		model.addAttribute("cboRecepcionAnalista", cboRecepcionAnalista);

		return "formulario153/formulario153";
	}

	/**
	 * Tipo proceso get page.
	 *
	 * @param formulario153 the formulario 153
	 * @param model the model
	 * @param idclima the idclima
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/muestraGet/{idclima}", method = RequestMethod.GET)
	public String tipoProcesoGetPage(@Valid @ModelAttribute("formulario153") Formulario153 formulario153,
			Model model, @PathVariable Integer idclima) throws Exception {

		cboPuntoMuestreo = iRegistroDeMuestraService.obtenerPuntoMuestreoCbo();
		cboMatriz = iRegistroDeMuestraService.obtenerMatrizCbo();
		cboTipoAnalisis = iRegistroDeMuestraService.obtenerTipoAnalisisCbo();
		cboTipoFrasco = iRegistroDeMuestraService.obtenerTipoFrascoCbo();
		cboPersonaMuestrea = iRegistroDeMuestraService.obtenerPersonaMuestreaCbo();
		cboRecepcionAnalista = iRegistroDeMuestraService.obtenerRecepAnalistaCbo();

		if (idclima != null && idclima > 0) {
			formulario153 = iRegistroDeMuestraService.obtenerFormulario153(idclima);
			System.out.println("FechaGet " + formulario153.getStrFechaHora());
		} else {
			formulario153 = new Formulario153();
			formulario153.setCodigo("0");
		}
		model.addAttribute("codigoTipoDocumento", Constants.TIP_DOC_MUESTRA);
		model.addAttribute("tipArchDocumento", env.getProperty(Constants.PAR_TIP_ARCHIVOS));
		model.addAttribute("tamMaxArchDocumento", env.getProperty(Constants.PAR_PESO_MAX_DOC));	
		model.addAttribute("formulario153", formulario153);
		model.addAttribute("cboPuntoMuestreo", cboPuntoMuestreo);
		model.addAttribute("cboMatriz", cboMatriz);
		model.addAttribute("cboTipoAnalisis", cboTipoAnalisis);
		model.addAttribute("cboTipoFrasco", cboTipoFrasco);
		model.addAttribute("cboPersonaMuestrea", cboPersonaMuestrea);
		model.addAttribute("cboRecepcionAnalista", cboRecepcionAnalista);
		
		if (idclima == null || idclima <= 0) {
			return "formulario153/muestraInsert :: form-edit-clima";
		} 
		
		return "formulario153/muestraEdit :: form-edit-clima";
	}
	
	/**
	 * Reporte completo pdf.
	 *
	 * @param allRequestParams the all request params
	 * @param response the response
	 * @param nombreArchivo the nombre archivo
	 */
	@RequestMapping(value = "/registroMuestraReportePDF/{nombreArchivo}", method = RequestMethod.GET)
	public @ResponseBody void reporteCompletoPdf(@RequestParam Map<String, String> allRequestParams, 
												 HttpServletResponse response,
												 @PathVariable String nombreArchivo) {
		try {
			String strFecha="";
			
			if(allRequestParams.get("periodo")!=null && !"".equals(allRequestParams.get("periodo"))){
				strFecha = allRequestParams.get("periodo");
			}

			List<ReporteMuestraPdf> result = iRegistroDeMuestraService.generarReporteMuestraPdf(strFecha);
			
			String nombreJasper = "/reporte_registro_de_muestras_pdf.jasper";
			String pdf = nombreArchivo;
			
			Map<String,Object> params = new HashMap<>();
			params.put(JRParameter.REPORT_LOCALE, Locale.US);
			params.put("BASE_DIR", this.getClass().getResource("/reportes/").getPath());
			
			
			ReporteUtil.generarPDF(nombreJasper, params, result, pdf, response);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Actualizarclima.
	 *
	 * @param formulario153 the formulario 153
	 * @param bindingResult the binding result
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/formularioSave", method = RequestMethod.POST)
	public String actualizarclima(
			// @Valid @ModelAttribute("climaSearchBean") ClimaBean
			// climaSearchBean,
			@Valid @ModelAttribute("formulario153") Formulario153 formulario153,
			BindingResult bindingResult, ModelMap model) {

		String strMensajeTipo = "";
		String strMensajeError = "";

		formulario153.setUsuarioCreacion(Util.getUserLoged());
		formulario153.setUsuarioModificacion(Util.getUserLoged());
		formulario153.setPrograma(Util.getPageCall());

		formulario153.setCodigoArea(0);
		formulario153.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
		formulario153.setCodigoFrasco("0");
		int codigo = -1;
		try {
			if (formulario153.getCodigo() != null && !formulario153.getCodigo().trim().equals("")
					&& Integer.parseInt(formulario153.getCodigo()) > 0) {
				System.out.println("idRegistro: "+formulario153.getCodigo());
				System.out.println("CDO FRASCO: "+formulario153.getCodigoFrasco());
				iRegistroDeMuestraService.actualizarFormulario(formulario153);
				strMensajeTipo = ConstantsCommon.ACTUALIZADO_OK;
				codigo = Integer.parseInt(formulario153.getCodigo());
			} else {
				// NUEVO REGISTRO
				codigo = iRegistroDeMuestraService.grabarMuestra(formulario153);
				strMensajeTipo = ConstantsCommon.GRABADO_OK;
				System.out.println("fecha " + formulario153.getStrFechaHora());
			}
			
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		//return "formulario153/muestraSave :: mensajesSave";
		
		model.addAttribute("btnBusqueda", Constants.BTN_FORM_BUSCAR_MUESTRA);
		model.addAttribute("modalId", Constants.MODAL_REGISTRA_MUESTRA);
		model.addAttribute("codigo", codigo);
		model.addAttribute("tipo", Constants.TIP_DOC_MUESTRA);
		model.addAttribute("anio", "2017");
		model.addAttribute("page", formulario153.getPage());
		return "COMM/customFilesSave :: mensajesSave";
	}

	/**
	 * Obtener datos.
	 *
	 * @param allRequestParams the all request params
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/muestra/pagination", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Object>> obtenerDatos(
			@RequestParam Map<String, String> allRequestParams) throws Exception {
		try {
			Formulario153 formulario153 = new Formulario153();

			Paginacion paginacion = new Paginacion();
			paginacion.setCantidadpag(Integer.parseInt(allRequestParams.get("length")));
			paginacion.setPagdesde(Integer.parseInt(allRequestParams.get("start")));
			paginacion.setColorderby(Integer.parseInt(allRequestParams.get("order[0][column]")) == 0
					? Integer.parseInt(allRequestParams.get("order[0][column]")) + 1
					: Integer.parseInt(allRequestParams.get("order[0][column]")));
			paginacion.setColorderbydir(allRequestParams.get("order[0][dir]"));

			if (allRequestParams.get("fechax") != null && !"".equals(allRequestParams.get("fechax"))) {
				formulario153.setStrFechaHora(allRequestParams.get("fechax"));
				System.out.println("fechax " + allRequestParams.get("fechax"));
			}

			Result result = iRegistroDeMuestraService.obtenerRegistros(formulario153, paginacion);
			System.out.println("result Muestra " + result.toString());

			Map<String, Object> dtResponse = new HashMap<String, Object>();
			dtResponse.put("draw", allRequestParams.get("draw"));
			dtResponse.put("recordsTotal", result.getRecords());
			dtResponse.put("recordsFiltered", result.getRecords());
			dtResponse.put("data", result.getData());

			return new ResponseEntity<Map<String, Object>>(dtResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Inactivar clima.
	 *
	 * @param model the model
	 * @param request the request
	 * @param session the session
	 * @param inacIdmuestra the inac idmuestra
	 * @return the string
	 */
	@RequestMapping(value = "/muestra/eliminar", method = RequestMethod.POST)
	public String inactivarClima(ModelMap model, HttpServletRequest request, HttpSession session,
			@RequestParam String inacIdmuestra) {

		String strMensajeTipo = "";
		String strMensajeError = "";

		Formulario153 f = new Formulario153();
		f.setCodigo(inacIdmuestra);

		try {

			iRegistroDeMuestraService.eliminarFormulario(f);
			strMensajeTipo = ConstantsCommon.BORRADO_OK;

		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.BORRADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);
		return "formulario153/muestraSave :: mensajesSave";

		///////////////

	}
	

	/**
	 * Formulario duplicar.
	 *
	 * @param request the request
	 * @param session the session
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/formularioDuplicar153/{id}", method = RequestMethod.GET)
	public String formularioDuplicar(HttpServletRequest request, HttpSession session, Model model, @PathVariable int id)
			throws Exception {

		String mensajeTipo = Constants.GRABADO_OK;
		String mensajeMostrar = obtenerMensaje("mensaje.duplicado.ok");
		try {

			LOG.info("id header: " + id);
			Formulario153 formulario153 = iRegistroDeMuestraService.obtenerFormulario153(id);

			formulario153.setPrograma(Util.getPageCall());
			formulario153.setUsuarioCreacion(Util.getUserLoged());
			formulario153.setUsuarioModificacion(Util.getUserLoged());

			DateFormat dateFormatDay = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dateFormatHour = new SimpleDateFormat("hh:mm");
			
			
			Date today = new Date();
			String fechaDia = dateFormatDay.format(today);
			String fechaHora = dateFormatHour.format(today);
			
			LOG.info("FECHA DE DUPLICIDAD: " + fechaDia + " : " + fechaHora);
			
			formulario153.setStrFechaHora(fechaDia);
			formulario153.setHora(fechaHora);

			iRegistroDeMuestraService.grabarMuestra(formulario153);

		} catch (Exception e) {
			LOG.error(e.getMessage());
			mensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			mensajeMostrar = obtenerMensaje("mensaje.duplicado.no.ok");
		}
		
		model.addAttribute(Constants.MENSAJE_TIPO, mensajeTipo);
		model.addAttribute(Constants.MENSAJE_MOSTRAR, mensajeMostrar);

		return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
	}

	/**
	 * Obtener mensaje.
	 *
	 * @param messageProp the message prop
	 * @return the string
	 */
	public String obtenerMensaje(String messageProp){
		return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
	}

}
