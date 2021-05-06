package pe.com.sedapal.scr.web.controllers;

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
import pe.com.sedapal.scr.core.beans.ReporteMuestraPdf;
import pe.com.sedapal.scr.core.services.IRegistroDeMuestraService;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;

@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class MuestraController {

	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);

	@Autowired
	Environment env;

	@Autowired
	MessageSource messageSource;

	@Autowired
	ServletContext servletContext;

	@Autowired
	IRegistroDeMuestraService iRegistroDeMuestraService;

	private List<BeanComodin> cboPuntoMuestreo = null;
	private List<BeanComodin> cboMatriz = null;
	private List<BeanComodin> cboTipoAnalisis = null;
	private List<BeanComodin> cboTipoFrasco = null;
	private List<BeanComodin> cboPersonaMuestrea = null;
	private List<BeanComodin> cboRecepcionAnalista = null;

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
		}
		model.addAttribute("formulario153", formulario153);
		model.addAttribute("cboPuntoMuestreo", cboPuntoMuestreo);
		model.addAttribute("cboMatriz", cboMatriz);
		model.addAttribute("cboTipoAnalisis", cboTipoAnalisis);
		model.addAttribute("cboTipoFrasco", cboTipoFrasco);
		model.addAttribute("cboPersonaMuestrea", cboPersonaMuestrea);
		model.addAttribute("cboRecepcionAnalista", cboRecepcionAnalista);
		return "formulario153/muestraEdit :: form-edit-clima";
	}
	
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

		try {
			if (formulario153.getCodigo() != null && !formulario153.getCodigo().trim().equals("")
					&& Integer.parseInt(formulario153.getCodigo()) > 0) {
				System.out.println("idRegistro: "+formulario153.getCodigo());
				System.out.println("CDO FRASCO: "+formulario153.getCodigoFrasco());
				iRegistroDeMuestraService.actualizarFormulario(formulario153);
			} else {
				// NUEVO REGISTRO
				iRegistroDeMuestraService.grabarMuestra(formulario153);
				System.out.println("fecha " + formulario153.getStrFechaHora());
			}
			strMensajeTipo = ConstantsCommon.GRABADO_OK;
		} catch (Exception e) {
			strMensajeTipo = ConstantsCommon.GRABADO_NO_OK;
			strMensajeError = e.getMessage();
			e.printStackTrace();
		}

		model.addAttribute("mensajeTipo", strMensajeTipo);
		model.addAttribute("mensajeError", strMensajeError);

		return "formulario153/muestraSave :: mensajesSave";
	}

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

}
