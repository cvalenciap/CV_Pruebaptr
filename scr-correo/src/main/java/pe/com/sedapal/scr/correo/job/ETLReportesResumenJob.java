/**
 * 
 * Resumen.
 * Objeto 				: ETLReportesResumenJob
 * DescripciÃ³n 			: Clase ejecutora de la tarea programada de envÃ­o de correo
 * Fecha de CreaciÃ³n 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  DescripciÃ³n
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.job;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.beans.AdjuntoMensaje;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;
import pe.com.sedapal.scr.correo.task.EnviarMensajeTask;
import pe.com.sedapal.scr.correo.task.LeerMensajesTask.RedirectionUrlCallback;
import pe.com.sedapal.scr.correo.util.ExchangeUtil;
import pe.com.sedmail.cliente.bean.ArchivoAdjunto;
import pe.com.sedmail.cliente.bean.ParametrosCorreo;
import pe.com.sedmail.cliente.bean.ResponseBean;
import pe.com.sedmail.cliente.util.ConstantesCliente;
import pe.com.sedmail.cliente.ws.SedmailClienteWs;

@Component
public class ETLReportesResumenJob {
	final static Logger logger = Logger.getLogger(ETLReportesResumenJob.class);

	@Autowired
	private EnviarMensajeTask enviarMensajeTask;
	
	@Autowired
	private IConfigCorreoService configCorreoService;
	
//	inicio adecuacion sedmail	
	@Autowired
	private SedmailClienteWs mailtub;
//	fin adecuacion sedmail
	
//	private ExchangeService exchangeService;
	
	/**
	 * MÃ©todo que permite ejecutar la tarea programada de envÃ­o de correo
	 * @Return No hay objeto de retorno 
	 * @throws Exception ExcepciÃ³n que puede ser lanzada
	 */
//	inicio adecuacion sedmail	
//	public void execute() throws Exception{
	public void execute() throws GmdException{
		try {
//	fin adecuacion sedmail
			logger.info("**Iniciando Proceso de Reportes Resumen**");
			
			ConfiguracionCorreo configCorreoEnvios 
			= configCorreoService.obtenerConfigCorreo(Constants.TIPO_CORREO_ENVIOS, Constants.ESTADO_ACTIVO);
			
			ConfiguracionCorreo configCorreo 
				= configCorreoService.obtenerConfigCorreo(Constants.TIPO_PROCESO_REPORTE, Constants.ESTADO_ACTIVO);
			
			logger.info("Correos recuperados correctamente");
			
//			inicio adecuacion sedmail
//			exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
//		
//			WebCredentials credentials 
//				= new WebCredentials(configCorreoEnvios.getCuentaCorreo().getStrUsuario(), 
//						configCorreoEnvios.getCuentaCorreo().getStrClave());
//			exchangeService.setCredentials(credentials);
//			exchangeService.autodiscoverUrl(configCorreoEnvios.getCuentaCorreo().getStrCorreo(), new RedirectionUrlCallback());
//			exchangeService.setTraceEnabled(true);		
//			
//			logger.info("Conexion al correo establecida");
//			
//			MensajeCorreo mensajeCorreo = new MensajeCorreo();
			ParametrosCorreo correoParam = new ParametrosCorreo();
			
			/* ---Remitente--- */
//			mensajeCorreo.setStrRemitente(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			correoParam.setRemitenteCorreo(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			
			/* ---Destinatarios--- */
			List<String> destinatarios = new ArrayList<String>();
			String paraBd = configCorreo.getMensajeCorreo().getStrPara() != null ? configCorreo.getMensajeCorreo().getStrPara() : "";
			String[] arrDestinatarios = paraBd.split(",");
			for(int j=0; j<arrDestinatarios.length; j++){
				String strDestinatario = arrDestinatarios[j].trim();					
				destinatarios.add(strDestinatario);
			}
//			mensajeCorreo.setLstDestinatarios(destinatarios);
			correoParam.setDestinatario(destinatarios);
			
			/* ---CCs--- */
			List<String> ccs = new ArrayList<String>();
			String ccBd = configCorreo.getMensajeCorreo().getStrConCopia() != null ? configCorreo.getMensajeCorreo().getStrConCopia() : "";
			String[] arrCcs = ccBd.split(",");
			for(int j=0; j<arrCcs.length; j++){
				String strCc = arrCcs[j].trim();
				if(!StringUtils.isEmpty(strCc)){
					ccs.add(strCc);
				}
			}
//			mensajeCorreo.setLstCcs(ccs);
			correoParam.setCopia(ccs);
			
				
			/* ---Asunto--- */
//			mensajeCorreo.setStrAsunto(configCorreo.getMensajeCorreo().getStrAsunto());
			correoParam.setAsunto(configCorreo.getMensajeCorreo().getStrAsunto());
			
			/* ---Contenido--- */
//			mensajeCorreo.setStrContenido(configCorreo.getMensajeCorreo().getStrContenido());
			correoParam.setCuerpoHTML(configCorreo.getMensajeCorreo().getStrContenido());
			
			/* ---Adjuntos--- */
//			List<AdjuntoMensaje> adjuntos = new ArrayList<AdjuntoMensaje>();
			List<ArchivoAdjunto> adjuntos = new ArrayList<ArchivoAdjunto>();
			
			/* ---Adjunto Reporte Grafico--- */
			String[] fechaRutaGrafico = enviarMensajeTask.generarReporteGraficoCaudal();
			
//			AdjuntoMensaje adjuntoUno = new AdjuntoMensaje();		
			ArchivoAdjunto adjuntoUno = new ArchivoAdjunto();
			Path rutaUno = Paths.get(fechaRutaGrafico[1]);
			byte[] archivoUno = Files.readAllBytes(rutaUno);
			
			adjuntoUno.setNombreArchivo(Constants.REPORTE_GRAFICO+fechaRutaGrafico[0].substring(6,10)+""+fechaRutaGrafico[0].substring(3,5)+".pdf");
			adjuntoUno.setBytesArchivo(archivoUno);
//			adjuntoUno.setStrNombreAdjunto(Constants.REPORTE_GRAFICO+fechaRutaGrafico[0].substring(6,10)+""+fechaRutaGrafico[0].substring(3,5)+".pdf");
//			adjuntoUno.setBytArchivoAdjunto(archivoUno);		
			adjuntos.add(adjuntoUno);
			
				
			/* ---Adjunto Reporte Resumen PDF--- */
			String[] fechaRutaResumen = enviarMensajeTask.generarReporteResumenCaudal();
			
//			AdjuntoMensaje adjuntoDos = new AdjuntoMensaje();
			ArchivoAdjunto adjuntoDos = new ArchivoAdjunto();
			Path rutaDos = Paths.get(fechaRutaResumen[1]);
			byte[] archivoDos = Files.readAllBytes(rutaDos);		
			
			adjuntoDos.setNombreArchivo(Constants.REPORTE_RESUMEN+fechaRutaResumen[0].substring(6,10)+""+fechaRutaResumen[0].substring(3,5)+".pdf");
			adjuntoDos.setBytesArchivo(archivoDos);
//			adjuntoDos.setStrNombreAdjunto(Constants.REPORTE_RESUMEN+fechaRutaResumen[0].substring(6,10)+""+fechaRutaResumen[0].substring(3,5)+".pdf");
//			adjuntoDos.setBytArchivoAdjunto(archivoDos);		
			adjuntos.add(adjuntoDos);
			
			/* ---Adjunto Reporte Resumen Excel--- */
			String[] fechaRutaResumenExcel = enviarMensajeTask.generarReporteResumenCaudalExcel();
			
//			AdjuntoMensaje adjuntoTres = new AdjuntoMensaje();
			ArchivoAdjunto adjuntoTres = new ArchivoAdjunto();
			Path rutaTres = Paths.get(fechaRutaResumenExcel[1]);
			byte[] archivoTres = Files.readAllBytes(rutaTres);	
			
			adjuntoTres.setNombreArchivo(Constants.REPORTE_RESUMEN+fechaRutaResumen[0].substring(6,10)+""+fechaRutaResumen[0].substring(3,5)+".xls");
			adjuntoTres.setBytesArchivo(archivoTres);
//			adjuntoTres.setStrNombreAdjunto(Constants.REPORTE_RESUMEN+fechaRutaResumen[0].substring(6,10)+""+fechaRutaResumen[0].substring(3,5)+".xls");
//			adjuntoTres.setBytArchivoAdjunto(archivoTres);		
			adjuntos.add(adjuntoTres);
			
			/**/
//			mensajeCorreo.setLstAdjuntos(adjuntos);
			correoParam.setArchivosAdjuntos(adjuntos);
			
//			enviarMensajeTask.enviarMensaje(mensajeCorreo, exchangeService);
//			parametros de autenticacion de rest sedmail
			String usuario = ExchangeUtil.obtenerPropiedad("sedmail.user");
			String password = ExchangeUtil.obtenerPropiedad("sedmail.password");
	//		credentials
			correoParam.setUserOffice365(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			correoParam.setPassOffice365(configCorreoEnvios.getCuentaCorreo().getStrClave());
			correoParam.setIdTipoEnvio(2);
			ResponseBean response = mailtub.enviarCorreoMultiPart(correoParam, usuario, password);
			logger.info(response.getEstadoRespuesta());
			logger.info(response.getMensajeRespuesta());
    		if(response.getEstadoRespuesta().equals(ConstantesCliente.RESULTADO_ERROR)) {
    			throw new GmdException(response.getMensajeRespuesta());
    		}
			
			
			/* --- Borrar Archivos Temporales--- */
			enviarMensajeTask.deleteTempFiles(adjuntoUno.getNombreArchivo(), adjuntoDos.getNombreArchivo(), adjuntoTres.getNombreArchivo());
//			enviarMensajeTask.deleteTempFiles(adjuntoUno.getStrNombreAdjunto(), adjuntoDos.getStrNombreAdjunto(), adjuntoTres.getStrNombreAdjunto());
			
			logger.info("**Finalizando Proceso de Reportes Resumen**");
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
			throw new GmdException(excepcion.getMessage());
		}
//		fin adecuacion sedmail
	}
	
}
