package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.gmd.util.properties.PropiedadesUtil;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.GeneralBean;
import pe.com.sedapal.scr.core.beans.VariableBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;
import pe.com.sedapal.scr.core.services.IFormulaService;
import pe.com.sedapal.scr.core.services.IGeneralService;
import pe.com.sedapal.scr.core.services.IVariableService;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.common.Util;
import pe.com.sedapal.scr.web.util.JsonUtil;

/**
 * @author GMD
 * Controlador General PTAR
 */
@Controller
@RequestMapping(value = "/general")
public class GeneralController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Constants.PACKAGE);
	private String mensaje = "";
	private BRespuestaBean respuestaBean = new BRespuestaBean();
	
	@Autowired
	IGeneralService generalService;
	
	@Autowired
	IDetalleGeneralService detalleGeneralService;
	
	@Autowired
	IVariableService variableService;
	
	@Autowired
	IFormulaService formulaService;
	
	
	/**
	 * Método que realiza la busqueda de variables activas
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/buscarVariableActivo", method = RequestMethod.POST)
	public @ResponseBody String buscarVariableActivo(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idDetalleGeneral");
			List<VariableBean> lista = variableService.obtenerLtaVariable(Integer.parseInt(idParametro));
			mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * Método que realiza la busqueda de fórmulas principales
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Lista de fórmulas en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/buscarFormulaPrincipales", method = RequestMethod.POST)
	public @ResponseBody String buscarFormulaPrincipales(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametroFormula = request.getParameter("idParametroFormula");
			if (!StringUtils.isEmpty(idParametroFormula)) {
				List<FormulaBean> lista = formulaService.buscarFormulasPrincipales(Integer.parseInt(idParametroFormula));
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<FormulaBean> lista = new ArrayList<FormulaBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * Método que realiza la busqueda de fórmulas incluyendo eliminados
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Lista de fórmulas en formato JSON
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/buscarFormula", method = RequestMethod.POST)
	public @ResponseBody String buscarFormula(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String idParametro = request.getParameter("idParametroFormula");
			String idFormula = request.getParameter("idFormula");
			String combinacion = request.getParameter("combinacion");
			if (!StringUtils.isEmpty(idParametro)) {
				List<FormulaBean> lista = null;
				if (!StringUtils.isEmpty(idFormula)) {
					lista= formulaService.buscarFormulas(Integer.parseInt(idParametro),Integer.parseInt(idFormula),combinacion);
				}else {
					lista = formulaService.buscarFormulas(Integer.parseInt(idParametro),null,combinacion);
				}					
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}else {
				List<FormulaBean> lista = new ArrayList<FormulaBean>();
				mapResultado.put("ListaResultado", JsonUtil.convertirObjetoACadenaJson(lista));
			}
			respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	/**
	 * Método que realiza el registro de una nueva fórmula
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @return Objeto tipo Respuesta con estado y mensaje resultados del método
	 * @throws GmdException excepción de error
	 */
	@RequestMapping(value = "/registrarFormula", method = RequestMethod.POST)
	public @ResponseBody String registrarFormula(HttpServletRequest request, Model model) throws GmdException {
		try {
			String formulaJSON = request.getParameter("mantenimientoBean");
			FormulaBean bean = JsonUtil.convertirCadenaJsonAObjeto(formulaJSON, FormulaBean.class);
			Util.setFiltroContext(request);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setIdUsuaModi(Util.getUserLoged());
			auditoria.setDeTermModi(Util.obtenerTerminal(request));
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				bean.setIdUsuaCrea(Util.getUserLoged());
				bean.setDeTermCrea(Util.obtenerTerminal(request));
				//validamos si la combinacion existe
				FormulaBean beanRespuesta = formulaService.registrarFormula(bean);
				Map<String, Object> mapResultado = new HashMap<String, Object>();
				mapResultado.put("formulaResultado", JsonUtil.convertirObjetoACadenaJson(beanRespuesta));
				respuestaBean.setParametros(mapResultado);
				respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
				respuestaBean.setMensajeRespuesta("Se Registr&oacute; Correctamente.");							
			
		} catch (Exception excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
		
	/**
	 * Método que realiza la busqueda de parÁmetros generales
	 * 
	 * @return lista de parámetros generales en formato JSON
	 * @throws GmdException excepción de error
	 */
	public  List<GeneralBean> obtenerListaGeneral() throws GmdException{
		List<GeneralBean> ltaGeneral = new ArrayList<GeneralBean>();
		try {
			ltaGeneral = generalService.listarTodos();			
		} catch (GmdException excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return ltaGeneral;
	}	
	
	/**
	 * Método que realiza la busqueda de detalles generales activos
	 * 
	 * @param idGeneral identificador del grupo de detalle general
	 * @return lista de detalles generales en formato JSON
	 * @throws GmdException excepción de error
	 */
	public List<DetalleGeneralBean> obtenerListaDetalleGeneral(int idGeneral) throws GmdException{
		List<DetalleGeneralBean> listaDetalleParametroBean = new ArrayList<DetalleGeneralBean>();
		try {
			listaDetalleParametroBean = detalleGeneralService.obtenerDetalleGeneral(idGeneral);
		} catch (GmdException excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return listaDetalleParametroBean;
	}
	
	/**
	 * Método que realiza la busqueda de detalles generales incluyendo eliminados
	 * 
	 * @param idGeneral identificador del grupo de detalle general
	 * @return lista de detalles generales en formato JSON
	 * @throws GmdException excepción de error
	 */
	public List<DetalleGeneralBean> obtenerListaDetalleGeneralTodo(int idGeneral) throws GmdException{
		List<DetalleGeneralBean> listaDetalleParametroBean = new ArrayList<DetalleGeneralBean>();
		try {
			listaDetalleParametroBean = detalleGeneralService.obtenerDetalleGeneralTodo(idGeneral);
		} catch (GmdException excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return listaDetalleParametroBean;
	}
	
	/**
	 * Método que realiza la subida de un archivo
	 * 
	 * @param fileObj archivo tipo MultipartFile
	 * @param nombrePersonalizado nombre del archivo
	 * @return Map Respuesta con estado y mensaje resultados del método
	 */
	public Map<String, Object> procesarSubirArchivo(MultipartFile fileObj,String nombrePersonalizado){
		
		String mensajeError ="";
		String ruta="";
		Map<String, Object> listaResultado = new HashMap<String,Object>(); 

		try{			
			List<DetalleGeneralBean>  ltaDirectorio = detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_DIRECTORIO_ARCHIVOS);
			if(ltaDirectorio != null && ltaDirectorio.size() > 0) {
				if(!Strings.isEmpty(ltaDirectorio.get(0).getVlDato01())) {
					String path = ltaDirectorio.get(0).getVlDato01();	
					Long tamanioMax = Long.parseLong(PropiedadesUtil.obtenerPropiedad(ConstanteServices.ARCHIVO_CONFIGURACION, "archivo.tamanioMax"));	

					if(fileObj != null){

						File convFile = new File(fileObj.getOriginalFilename());
						String nombreArchivo = convFile.getPath();           
						int index = nombreArchivo.lastIndexOf('.'); 
						String extension = nombreArchivo.substring(index + 1);
						String nombre = nombrePersonalizado.equals("") ? nombreArchivo.substring(0, index) : nombrePersonalizado+"_"+nombreArchivo.substring(0, index);

						String nombreArchivoInterno = generarNombreArchivo(nombre, extension);
						ruta = path + "\\" + nombreArchivoInterno;

						if(tamanioMax >= fileObj.getSize()){
						
							FileOutputStream fileOuputStream = new FileOutputStream(ruta); 
							fileOuputStream.write(fileObj.getBytes());
							fileOuputStream.close();
			
							listaResultado.put("estadoRespuesta", ConstanteServices.OK);
							listaResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_GRABO_CORRECTAMENTE);
							listaResultado.put("nombreArchivoInterno", nombreArchivoInterno);
						}else{
							listaResultado.put("estadoRespuesta", ConstanteServices.ERROR);
							listaResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_EXCESO_TAMANIO + ": " + ConstanteServices.TAMANIO_MAX_ARCHIVO);
						}
						
						listaResultado.put("nombreArchivo", convFile.getPath());
						
					}else{
						listaResultado.put("estadoRespuesta", ConstanteServices.ERROR);
						listaResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ARCHIVO_NO_ENCONTRADO_SUBIR);
					}
				}else {
					listaResultado.put("estadoRespuesta", ConstanteServices.ERROR);
					listaResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
				}
			}else {
				listaResultado.put("estadoRespuesta", ConstanteServices.ERROR);
				listaResultado.put("mensajeRespuesta", ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
			}

		} catch (Exception exception) {		  
			listaResultado.put("estadoRespuesta", ConstanteServices.ERROR);
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			mensajeError = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			listaResultado.put("mensajeRespuesta", mensajeError);
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], exception);
		}

		return listaResultado;	
	}
	
	/**
	 * Método que genera el nombre del archivo
	 * 
	 * @param nombre nombre del archivo
	 * @param extension tipo de archivo
	 * @return nombre del archivo
	 */
	private String generarNombreArchivo(String nombre, String extension){
		
		Calendar now = Calendar.getInstance();
	
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
		
		String nombreArchivo = nombre + sdf.format(now.getTime()) + "." + extension;
		
		return nombreArchivo;
	}
	
	/**
	 * Método que valida la existencia de un archivo en la descarga
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param response retorna información de la petición HTTP
	 * @return model abstracción de resultado
	 * @throws IOException excepción de error
	 */
	@RequestMapping(value="/validarArchivo", method = RequestMethod.POST)
	public @ResponseBody String validarArchivo(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException{
		try{
			
			String nombreRealSecuencial = String.valueOf(request.getParameter("nSecuencial"));
			List<DetalleGeneralBean>  ltaDirectorio = detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_DIRECTORIO_ARCHIVOS);
			if(ltaDirectorio != null && ltaDirectorio.size() > 0) {
				if(!Strings.isEmpty(ltaDirectorio.get(0).getVlDato01())) {
					String path = ltaDirectorio.get(0).getVlDato01();
			        String ruta = path + "\\" + nombreRealSecuencial;
			        
			        File downloadFile = new File(ruta);
			        
			        if(downloadFile.exists() && !downloadFile.isDirectory()) { 
			        	respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			        	respuestaBean.setMensajeRespuesta(ConstanteServices.OK);
			        }else{
			        	respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			        	respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ARCHIVO_NO_ENCONTRADO_DESCARGAR);	        	
			        }
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
		        	respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
				}
			}else {
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
	        	respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
			}	        
	        model.addAttribute("respuesta", respuestaBean);
			
		}catch(Exception exception){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			mensaje = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			respuestaBean.setMensajeRespuesta(mensaje);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], exception);
		}
		
		return JsonUtil.convertirObjetoACadenaJson(model);
	}
	
	/**
	 * Método que obtiene los parámetros de correo
	 * 
	 * @return Objeto detalle general 
	 * @throws GmdException
	 */
	public DetalleGeneralBean obtenerParametrosCorreo() throws GmdException{		
		DetalleGeneralBean aDetalleParametro = new DetalleGeneralBean();		
		try {			
			List<DetalleGeneralBean>  ltaDirectorio = detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_SERVIDOR_CORREO);
			if(ltaDirectorio != null && ltaDirectorio.size() > 0) {
				return ltaDirectorio.get(0);
			}else {
				return aDetalleParametro;
			}
		} catch (GmdException excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			mensaje = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			respuestaBean.setMensajeRespuesta(mensaje);
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return aDetalleParametro;
	}
	
	/**
	 * Método que realiza la exportación de ARCHIVO
	 * 
	 * @param request se utiliza para almacenar información entre diferentes peticiones HTTP.
	 * @param model permite la abstracción completa de la tecnología de vista
	 * @param response retorna información de la petición HTTP
	 * @throws IOException excepción de error
	 */
	@RequestMapping(value="/accionExportarArchivo", method = RequestMethod.GET)
	public @ResponseBody void accionExportarArchivo(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException{
		try{
			
			String nombreRealSecuencial = String.valueOf(request.getParameter("nSecuencial"));
			String nombreVisibleArchivo = String.valueOf(request.getParameter("nVisible"));
			List<DetalleGeneralBean>  ltaDirectorio = detalleGeneralService.obtenerDetalleGeneralbyidDetalle(ConstantesUtil.ID_DIRECTORIO_ARCHIVOS);
			if(ltaDirectorio != null && ltaDirectorio.size() > 0) {
				if(!Strings.isEmpty(ltaDirectorio.get(0).getVlDato01())) {
					String path = ltaDirectorio.get(0).getVlDato01();
			        String ruta = path + "\\" + nombreRealSecuencial;
			        
			        File downloadFile = new File(ruta);
			        FileInputStream inStream = new FileInputStream(downloadFile);
			        
			        response.setContentType("application/octet-stream");
			        response.setContentLength((int) downloadFile.length());
			         
			        String headerKey = "Content-Disposition";
			        String headerValue = String.format("attachment; filename=\"%s\"", nombreVisibleArchivo);
			        response.setHeader(headerKey, headerValue);
			         
			        OutputStream outStream = response.getOutputStream();
			         
			        byte[] buffer = new byte[4096];
			        int bytesRead = -1;
			         
			        while ((bytesRead = inStream.read(buffer)) != -1) {
			            outStream.write(buffer, 0, bytesRead);
			        }
			        inStream.close();
			        outStream.close();
				}else {
					respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
		        	respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
				}
			}else{
				respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
	        	respuestaBean.setMensajeRespuesta(ConstanteServices.MENSAJE_ERROR_NO_DIRECTORIO);
			}	        
		}catch(Exception exception){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			mensaje = format(ConstanteServices.MENSAJE_ERROR, error[0]);
			respuestaBean.setMensajeRespuesta(mensaje);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], exception);
		}
	}
	
	/**
	 * Métdodo que obtiene los puntos de muestra de entrada y salida
	 * 
	 * @param idPtoMuestra identificador de punto de muestra
	 * @return lista de parametros en formato JSON
	 * @throws GmdException
	 */
	public List<DetalleGeneralBean> obtenerListaPuntoMuestraES(int idPtoMuestra) throws GmdException{
		List<DetalleGeneralBean> listaDetalleParametroBean = new ArrayList<DetalleGeneralBean>();
		try {
			listaDetalleParametroBean = detalleGeneralService.obtenerDetalleGeneralTodo(idPtoMuestra);
		} catch (GmdException excepcion) {
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			LOGGER.error(error[1], excepcion);
		}
		return listaDetalleParametroBean;
	}

}


