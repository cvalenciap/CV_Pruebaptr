package pe.com.sedapal.scr.correo.job;

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
import pe.com.gmd.util.properties.PropiedadesUtil;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;
import pe.com.sedapal.scr.correo.task.EnviarMensajeTask;
import pe.com.sedapal.scr.correo.task.LeerMensajesTask.RedirectionUrlCallback;
import pe.com.sedapal.scr.correo.util.ExchangeUtil;
import pe.com.sedmail.cliente.bean.ParametrosCorreo;
import pe.com.sedmail.cliente.bean.ResponseBean;
import pe.com.sedmail.cliente.util.ConstantesCliente;
import pe.com.sedmail.cliente.ws.SedmailClienteWs;

@Component
public class ETLReporteGerenciaJob {
	
	final static Logger logger = Logger.getLogger(ETLReporteGerenciaJob.class);

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
	 * Método que permite ejecutar la tarea programada de envío de correo a gerencia
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
//	inicio adecuacion sedmail
//	public void execute(String strTableResumenHtml) throws Exception{
	public void execute(String strTableResumenHtml) throws GmdException{
		try {
//	fin adecuacion sedmail
		
			ConfiguracionCorreo configCorreoEnvios 
			= configCorreoService.obtenerConfigCorreo(Constants.TIPO_CORREO_ENVIOS, Constants.ESTADO_ACTIVO);
			
			if(configCorreoEnvios == null){
				logger.error("No se ha encontrado correo fuente configurado para envios");
			}
			
			ConfiguracionCorreo configCorreo 
				= configCorreoService.obtenerConfigCorreo(Constants.TIPO_PROCESO_REPORTE_GERENCIA, Constants.ESTADO_ACTIVO);
			
			if(configCorreoEnvios == null){
				logger.error("No se ha encontrado configuracion de correo para gerencia a quien enviar");
			}
			
	//		inicio adecuacion sedmail
	//		exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
	//	
	//		WebCredentials credentials 
	//			= new WebCredentials(configCorreoEnvios.getCuentaCorreo().getStrUsuario(), 
	//					configCorreoEnvios.getCuentaCorreo().getStrClave());
	//		exchangeService.setCredentials(credentials);
	//		exchangeService.autodiscoverUrl(configCorreoEnvios.getCuentaCorreo().getStrCorreo(), new RedirectionUrlCallback());
	//		exchangeService.setTraceEnabled(true);
			
	//		MensajeCorreo mensajeCorreo = new MensajeCorreo();
			ParametrosCorreo correoParam = new ParametrosCorreo();
			
			/* ---Remitente--- */
	//		mensajeCorreo.setStrRemitente(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			correoParam.setRemitenteCorreo(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			
			/* ---Destinatarios--- */
			List<String> destinatarios = new ArrayList<String>();
			String paraBd = configCorreo.getMensajeCorreo().getStrPara() != null ? configCorreo.getMensajeCorreo().getStrPara() : "";
			String[] arrDestinatarios = paraBd.split(",");
			for(int j=0; j<arrDestinatarios.length; j++){
				String strDestinatario = arrDestinatarios[j].trim();					
				destinatarios.add(strDestinatario);
			}
	//		mensajeCorreo.setLstDestinatarios(destinatarios);
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
	//		mensajeCorreo.setLstCcs(ccs);
			correoParam.setCopia(ccs);
			
				
			/* ---Asunto--- */
	//		mensajeCorreo.setStrAsunto(configCorreo.getMensajeCorreo().getStrAsunto());
			correoParam.setAsunto(configCorreo.getMensajeCorreo().getStrAsunto());
			
			/* ---Contenido--- */
	//		mensajeCorreo.setStrContenido(configCorreo.getMensajeCorreo().getStrContenido()+""+strTableResumenHtml);
			correoParam.setCuerpoHTML(configCorreo.getMensajeCorreo().getStrContenido()+""+strTableResumenHtml);
			
	//		enviarMensajeTask.enviarMensaje(mensajeCorreo, exchangeService);
	//		parametros de autenticacion de rest sedmail
			String usuario = ExchangeUtil.obtenerPropiedad("sedmail.user");
			String password = ExchangeUtil.obtenerPropiedad("sedmail.password");
	//		credentials
			correoParam.setUserOffice365(configCorreoEnvios.getCuentaCorreo().getStrCorreo());
			correoParam.setPassOffice365(configCorreoEnvios.getCuentaCorreo().getStrClave());
			correoParam.setIdTipoEnvio(2);
			ResponseBean response = mailtub.enviarCorreo(correoParam, usuario, password);
			logger.info(response.getEstadoRespuesta());
			logger.info(response.getMensajeRespuesta());
    		if(response.getEstadoRespuesta().equals(ConstantesCliente.RESULTADO_ERROR)) {
    			throw new GmdException(response.getMensajeRespuesta());
    		}
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
			throw new GmdException(excepcion.getMessage());
		}
//		fin adecuacion sedmail
		
	}
}
