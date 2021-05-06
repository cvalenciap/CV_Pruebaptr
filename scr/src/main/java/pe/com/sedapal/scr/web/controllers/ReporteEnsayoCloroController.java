/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoCloro;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoCloroResult;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.ICatalogoService;
import pe.com.sedapal.scr.core.services.IEnsayoCloroService;
import pe.com.sedapal.scr.web.common.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteEnsayoCloroController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ReporteEnsayoCloroController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PlanOperativoController.class);

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The ensayo cloro service. */
	@Autowired
	IEnsayoCloroService ensayoCloroService;
	
	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	/**
	 * Plan operativo bandeja.
	 *
	 * @param request the request
	 * @param session the session
	 * @param planOperativoSearchBean the plan operativo search bean
	 * @param planOperativoEditBean the plan operativo edit bean
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/reporteEnsayoCloro", method = RequestMethod.GET)
	public String planOperativoBandeja(HttpServletRequest request, HttpSession session,
			@ModelAttribute("planOperativoSearchBean") PlanOperativo planOperativoSearchBean,
			@ModelAttribute("planOperativoEditBean") PlanOperativo planOperativoEditBean,
			Model model){
		
		return "reporteEnsayoCloro/reporteEnsayoCloro";
	}
	

//	@RequestMapping(value = "/reporteEnsayoCloroSearch", method = RequestMethod.POST)
//	public String reporteEnsayoCloroSearch(HttpServletRequest request, HttpSession session,
//			@ModelAttribute("reporteEnsayoCloroBean") ReporteEnsayoCloro reporteEnsayoCloroBean,
//			Model model,@RequestParam Map<String,String> allRequestParams){
//		
//			/// alterno
//			String fechaMuestreo = allRequestParams.get("fechaMuestreo");
//			String horaMuestreo = allRequestParams.get("horaMuestreo");
//	
//			
//			LOG.info("fechaMuestreo : " + fechaMuestreo);
//			LOG.info("horaMuestreo : " + horaMuestreo);
//			
//			List<ReporteEnsayoCloroResult> parametrosbd = new ArrayList<>();
//			
//			ReporteEnsayoCloroResult bean = new ReporteEnsayoCloroResult();
//			bean.setCloroResidualLibre(4D);
//			bean.setCloroResidualTotal(10D);
//			bean.setCodigoArea(1);
//			bean.setCorrelativo(1);
//			bean.setPuntoMuestra(1);
//			bean.setDosisClor(20D);
//			bean.setPh(9D);
//			parametrosbd.add(bean);
//			ReporteEnsayoCloro reporteEnsayoCloroBeanBD = new ReporteEnsayoCloro();
//			reporteEnsayoCloroBeanBD.setDetalles(parametrosbd);
//			
//			reporteEnsayoCloroBean = reporteEnsayoCloroBeanBD;
//			model.addAttribute("reporteEnsayoCloroBean", reporteEnsayoCloroBean);
//			
//			return "reporteEnsayoCloro/reporteEnsayoCloroTable :: resultsList";
//	}
	
	/**
 * Reporte ensayo cloro bandeja.
 *
 * @param request the request
 * @param session the session
 * @param reporteEnsayoCloroBean the reporte ensayo cloro bean
 * @param model the model
 * @param allRequestParams the all request params
 * @return the string
 */
@RequestMapping(value = "/reporteEnsayoCloroBandeja", method = RequestMethod.POST)
	public String reporteEnsayoCloroBandeja(HttpServletRequest request, HttpSession session,
			@ModelAttribute("reporteEnsayoCloroBean") ReporteEnsayoCloro reporteEnsayoCloroBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
			Map<String, List<ReporteEnsayoCloroResult>> mapeo = new HashMap<String, List<ReporteEnsayoCloroResult>>();
			
			String fechaMes = allRequestParams.get("fechaMuestreo");
			LOG.info("fechaMes: " + fechaMes);
			
			List<ReporteEnsayoCloroResult> detalles;
			List<ReporteEnsayoCloroResult> detallesToView;
			
			detalles = ensayoCloroService.obtenerReporteEnsayoCloroBandeja(fechaMes);
			
			for (ReporteEnsayoCloroResult reporteEnsayoCloroResult : detalles) {
				String fechaMuestreo = reporteEnsayoCloroResult.getFechaMuestreo();
				if(mapeo.containsKey(fechaMuestreo)){
					detallesToView = mapeo.get(fechaMuestreo);
					detallesToView.add(reporteEnsayoCloroResult);
				}else{
					detallesToView = new ArrayList<>();
					detallesToView.add(reporteEnsayoCloroResult);
					mapeo.put(fechaMuestreo, detallesToView);
				}
			}
			
//			ReporteEnsayoCloroResult detalle = new ReporteEnsayoCloroResult();
//			detalle.setCorrelativo(1);
//			detalle.setPuntoMuestra(88);
//			detalles.add(detalle);
//			
//			detalle = new ReporteEnsayoCloroResult();
//			detalle.setCorrelativo(2);
//			detalle.setPuntoMuestra(99);
//			detalles.add(detalle);
//			
//			mapeo.put("27/09/2017", detalles);
			
			model.addAttribute("reportes",mapeo);
			return "reporteEnsayoCloro/reporteEnsayoCloroBandeja :: resultsList";
	}
	

	/**
	 * Reporte ensayo cloro search.
	 *
	 * @param request the request
	 * @param session the session
	 * @param reporteEnsayoCloroBean the reporte ensayo cloro bean
	 * @param model the model
	 * @param allRequestParams the all request params
	 * @return the string
	 */
	@RequestMapping(value = "/reporteEnsayoCloroSearch", method = RequestMethod.POST)
	public String reporteEnsayoCloroSearch(HttpServletRequest request, HttpSession session,
			@ModelAttribute("reporteEnsayoCloroBean") ReporteEnsayoCloro reporteEnsayoCloroBean,
			Model model,@RequestParam Map<String,String> allRequestParams){
		
			/// alterno
			String fechaMuestreo = allRequestParams.get("fechaMuestreo");
			try {
			int c= 0;
			EnsayoCloro ensayoCloro = new EnsayoCloro();
			List<SelectItemBean> listaPuntos =  this.catalogoService.obtenerCatalogo(26);
			for (SelectItemBean selectItemBean : listaPuntos) {
				int idPunto = Integer.parseInt(selectItemBean.getId());
				String descPunto  = selectItemBean.getLabel();
				
				EnsayoCloroDetalle detalle = ensayoCloroService.obtenerReporteEnsayoCloro(idPunto, fechaMuestreo);
				if(detalle!=null && detalle.getPuntoMuestreo()!=null){
					detalle.setPuntoMuestreoDesc(descPunto);
					ensayoCloro.addDetalle1(detalle);
					c++;
				}
			}
			
			if(c==0){
				model.addAttribute(Constants.MENSAJE_TIPO, ConstantsCommon.GRABADO_OK);
				model.addAttribute(Constants.MENSAJE_MOSTRAR,obtenerMensaje(ConstantsLaboratorio.MENSAJE_REPORTE_ENSAYO_VACIO));
				return ConstantsLaboratorio.PATH_RESULT_GENERICSAVE;
			}
			
			LOG.info("ensayoCloro : " + ensayoCloro);
			model.addAttribute("registroReporteDetalle",ensayoCloro);
			
			} catch (Exception e) {
				LOG.error("ERROR: ",e);
			}
			
			return "reporteEnsayoCloro/reporteEnsayoCloroTable :: resultSearchXFecha";
	}
	
	/**
	 * Método que permite validar que no hayan duplicados para una fecha.
	 *
	 * @param fechaMuestreo the fecha muestreo
	 * @param codigo the codigo
	 * @param puntoMuestreo1 the punto muestreo 1
	 * @param puntoMuestreo2 the punto muestreo 2
	 * @return Objeto de tipo ValidaUnicidadCorreoBean con la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@RequestMapping(value = "/reporteEnsayoCloroValidation", method = RequestMethod.GET)
	public @ResponseBody Boolean getValidationInfo(
			@RequestParam String fechaMuestreo,
			@RequestParam String codigo,
			@RequestParam String puntoMuestreo1,
			@RequestParam String puntoMuestreo2
			) throws Exception {
		ValidacionEnsayoCloroBean validacionBean = new ValidacionEnsayoCloroBean();
		validacionBean.setFechaMuestreo(fechaMuestreo);
		validacionBean.setCodigoEnsayo(Integer.parseInt(codigo));
		validacionBean.setCodigoMuestreo1(Integer.parseInt(puntoMuestreo1));
		validacionBean.setCodigoMuestreo1(Integer.parseInt(puntoMuestreo2));
		return ensayoCloroService.isValid(validacionBean);
	}
	
	/**
	 * Obtener mensaje.
	 *
	 * @param messageProp the message prop
	 * @return the string
	 */
	private String obtenerMensaje(String messageProp){
		return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
	}

}
