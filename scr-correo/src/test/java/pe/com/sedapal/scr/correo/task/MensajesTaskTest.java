package pe.com.sedapal.scr.correo.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.CuentaCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.MensajeUtil;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;
import pe.com.sedapal.scr.correo.task.LeerMensajesTask.RedirectionUrlCallback;
import pe.com.sedapal.scr.correo.util.ExchangeUtil;

public abstract class MensajesTaskTest {
	
	protected ConfiguracionCorreo configCorreo;
	protected ExchangeService exchangeService;
	
	@Autowired
	private IConfigCorreoService configCorreoService;

//	inicio adecuacion sedmail
	final static Logger logger = Logger.getLogger(MensajesTaskTest.class);
	
//	public void setUp() throws Exception {
	public void setUp() throws GmdException {
		try {
		
		CuentaCorreo cuentaCorreo = new CuentaCorreo();
//		cuentaCorreo.setStrCorreo(MensajeUtil.getMensaje(MensajeUtil.MENSAJE_USUARIO_CORREO));
//		cuentaCorreo.setStrClave(MensajeUtil.getMensaje(MensajeUtil.MENSAJE_CLAVE_CORREO));		
		
		configCorreo 
			= configCorreoService.obtenerConfigCorreo(Constants.TIPO_PROCESO_REPORTE, Constants.ESTADO_ACTIVO);
		
		configCorreo.setCuentaCorreo(cuentaCorreo);
	
//		exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
//	
//		WebCredentials credentials 
//			= new WebCredentials(configCorreo.getCuentaCorreo().getStrUsuario(), 
//				             	 configCorreo.getCuentaCorreo().getStrClave());
//		exchangeService.setCredentials(credentials);
//		exchangeService.autodiscoverUrl(configCorreo.getCuentaCorreo().getStrCorreo(), new RedirectionUrlCallback());
//		exchangeService.setTraceEnabled(true);
		exchangeService = ExchangeUtil.getExchangeService(configCorreo);
		
		/*
		// ConfiguracionCorreo
		CuentaCorreo cuentaCorreo = new CuentaCorreo();
		cuentaCorreo.setStrCorreo("scaudales@sedapal.com.pe");
		cuentaCorreo.setStrClave("sC2018$$");
		
		MensajeCorreo mensajeCorreo = new MensajeCorreo();
		mensajeCorreo.setStrNombreAdjuntoConfig("sedapal.txt");
		
		ServidorArchivos servidorArchivos = new ServidorArchivos();
		servidorArchivos.setStrUrl("D:\\FileServerTest\\sedapal.txt");
		
		configCorreo = new ConfiguracionCorreo();
		configCorreo.setCuentaCorreo(cuentaCorreo);
		configCorreo.setMensajeCorreo(mensajeCorreo);
		configCorreo.setServidorArchivos(servidorArchivos);
		
		// ExchangeService
		exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		
		WebCredentials credentials 
			= new WebCredentials(configCorreo.getCuentaCorreo().getStrUsuario(), 
					             configCorreo.getCuentaCorreo().getStrClave());
		exchangeService.setCredentials(credentials);
		exchangeService.autodiscoverUrl(configCorreo.getCuentaCorreo().getStrCorreo(), new RedirectionUrlCallback());
		exchangeService.setTraceEnabled(true);
		*/
		}catch(Exception excepcion){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
			throw new GmdException(excepcion.getMessage());
		}
						
	}
}
