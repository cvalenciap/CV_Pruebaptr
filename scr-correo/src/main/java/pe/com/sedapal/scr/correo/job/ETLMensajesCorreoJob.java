/**
 * 
 * Resumen.
 * Objeto 				: ETLMensajesCorreoJob
 * Descripción 			: Clase ejecutora de la tarea programada de extracción de correo
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.beans.Carga;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.common.Messages;
import pe.com.sedapal.scr.correo.core.helper.CaudalDetalleHelper;
import pe.com.sedapal.scr.correo.core.util.FechaUtil;
import pe.com.sedapal.scr.correo.service.ICargaService;
import pe.com.sedapal.scr.correo.service.ICaudalService;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;
import pe.com.sedapal.scr.correo.service.IRioService;
import pe.com.sedapal.scr.correo.task.ActualizarMensajeTask;
import pe.com.sedapal.scr.correo.task.GuardarArchivoCaudalesTask;
import pe.com.sedapal.scr.correo.task.LeerArchivoCaudalesTask;
import pe.com.sedapal.scr.correo.task.LeerMensajesTask;
import pe.com.sedapal.scr.correo.util.ExchangeUtil;

@Component
public class ETLMensajesCorreoJob {
	final static Logger logger = Logger.getLogger(ETLMensajesCorreoJob.class);

	@Autowired
	private Environment env;
	
	@Autowired
	private IConfigCorreoService configCorreoService;
	
	@Autowired
	private IRioService rioService;
	
	@Autowired
	private ICaudalService caudalService;
	
	@Autowired
	private ICargaService cargaService;
	
	private String strPeriodoActual;
	
	public ETLMensajesCorreoJob() {
		strPeriodoActual = FechaUtil.getPeriodoActual();
	}
	
	/**
	 * Método que permite ejecutar la tarea programada de extracción de correo
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void execute() throws GmdException{
		List<MensajeCorreo> lstMensajes = new ArrayList<MensajeCorreo>();
		List<MensajeCorreo> lstMensajesNoProcess = new ArrayList<MensajeCorreo>();
		ExchangeService exchangeService = null;
		GregorianCalendar todayAtNow = (GregorianCalendar)Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
//		Date date = new Date(2020, 4, 1);
//		GregorianCalendar todayAtNow = new GregorianCalendar();
//		todayAtNow.setTime(date);
		try {
			// P1: Obtener configuracion del correo (Tipo Operacion = Extraccion y Estado = Activo)
			logger.info("Obteniendo configuracion del correo desde base de datos");
			ConfiguracionCorreo configCorreo 
				= configCorreoService.obtenerConfigCorreo(Constants.TIPO_PROCESO_EXTRACCION, Constants.ESTADO_ACTIVO);
			
			if(configCorreo != null){
				// P2: Instanciancion del servicio de acceso al correo
				/*inicio adecuacion sedmail*/
//				exchangeService = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
//				
//				WebCredentials credentials 
//					= new WebCredentials(configCorreo.getCuentaCorreo().getStrUsuario(), 
//							             configCorreo.getCuentaCorreo().getStrClave());
//				exchangeService.setCredentials(credentials);
//				exchangeService.autodiscoverUrl(configCorreo.getCuentaCorreo().getStrCorreo(), new RedirectionUrlCallback());
//				exchangeService.setTraceEnabled(true);
				exchangeService = ExchangeUtil.getExchangeService(configCorreo);
				/*fin adecuacion sedmail*/
				
				// P3: Identificar los correos (mensajes) por leer y Descargar adjunto
				LeerMensajesTask leerMensajesTask = new LeerMensajesTask(configCorreo, exchangeService);
				lstMensajes = leerMensajesTask.leerMensajes(Integer.parseInt(env.getProperty("parametro.numero.correos.leer")));

				// Su no es valido, lo reseteamos para que no procese
				if(lstMensajes.size()>0 && !lstMensajes.get(0).isBolValid()){
					// ERRORES EN BUSQUEDA DE ADJUNTO
					Carga carga = new Carga(Constants.USUARIO_ADMIN, Constants.CODIGO_AREA, 
							Integer.parseInt(env.getProperty("parametro.sistema.codigo")), Constants.ESTADO_CARGA_FALLIDO);
					carga.setDescripcion(lstMensajes.get(0).getStrMensajeError());
					carga.setNombreCarga(Messages.MENSAJE_ERROR_PROCESO);
					carga.setNombreArchivo(Constants.DEFAULT_VARCHAR);
					carga.setRutaArchivo(Messages.MENSAJE_ERROR_PROCESO);
					// Persistimos en BD
					cargaService.registrarCarga(carga);
					lstMensajes = new ArrayList<MensajeCorreo>(0); // Para que no itere
				}
				
				/*igualamos lista de mensajes sin procesar*/
				lstMensajesNoProcess = lstMensajes;
				
				// P4: Obtenemos la lista de rios 
				List<RioBean> lstRios = rioService.listarRiosPorEstado(Constants.ESTADO_ACTIVO);
				
				// Iteracion de mensajes en caso de haberlos encontrado
				for(MensajeCorreo mensajeCorreo : lstMensajes) {
					
					// P5: Guardar adjunto en Servidor de Archivos
					GuardarArchivoCaudalesTask guardarAdjuntoTask = new GuardarArchivoCaudalesTask(configCorreo.getServidorArchivos());
					mensajeCorreo = guardarAdjuntoTask.guardarAdjunto(mensajeCorreo);
					
					// Validamos que tengamos acceso a la ruta del file, sino excepcion
					if(mensajeCorreo.getStrNombreAdjuntoGuardado() == null){
						// ERROR EN ACCESO A SERVIDOR FILE
						Carga carga = new Carga(Constants.USUARIO_ADMIN, Constants.CODIGO_AREA, 
								Integer.parseInt(env.getProperty("parametro.sistema.codigo")), Constants.ESTADO_CARGA_FALLIDO);
						carga.setDescripcion(Messages.MENSAJE_SERVFILE_NO_VALIDO);
						carga.setNombreCarga(Messages.MENSAJE_ERROR_PROCESO);
						carga.setNombreArchivo(Constants.DEFAULT_VARCHAR);
						carga.setRutaArchivo(Messages.MENSAJE_ERROR_PROCESO);
						// Persistimos en BD
						cargaService.registrarCarga(carga);
						break; // Salimos del bucle para terminar el proceso
					}
					
					// P6: Mover el correo a la carpeta de archivos leidos
					ActualizarMensajeTask actualizarMensajeTask = new ActualizarMensajeTask(exchangeService);
					actualizarMensajeTask.marcarMensaje(mensajeCorreo.getStrIdMensaje());
//					actualizarMensajeTask.returnInboxFolder(mensajeCorreo.getStrIdMensaje());
					
					// P7: Leer contenido del archivo guardado y Extraer la información
					LeerArchivoCaudalesTask leerContenidoArchivo 
						= new LeerArchivoCaudalesTask(configCorreo.getServidorArchivos(), lstRios, todayAtNow);
					List<Caudal> lstCaudales = leerContenidoArchivo.leerCaudales(mensajeCorreo);
					
					// P8: Validacion de contenido del fichero (para descartar procesamiento posterior)
					if(leerContenidoArchivo.isArchivoAdjuntoValido()) {
						
						// Si no hay caudales encontrados
						if((lstCaudales == null || lstCaudales.size() == 0) && leerContenidoArchivo.getSetMensajesValidacion().size()>0){
							// NINGUN RIO CUMPLE
							Carga carga = new Carga();
							carga.setNombreCarga(mensajeCorreo.getLstAdjuntos().get(0).getStrNombreAdjunto());
							carga.setNombreArchivo(mensajeCorreo.getStrNombreAdjuntoGuardado());
							carga.setRutaArchivo(configCorreo.getServidorArchivos().getStrUrl());
							carga.setEstado(Constants.ESTADO_CARGA_FALLIDO);
							carga.setDescripcion(leerContenidoArchivo.getMensajeValidacion());
							carga.setUsuarioCreacion(Constants.USUARIO_ADMIN);
							carga.setPrograma(Constants.PROGRAMA);
							carga.setCodigoArea(Constants.CODIGO_AREA);
							carga.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
							// Persistimos en BD
							cargaService.registrarCarga(carga);
							break;
						}
						
						// P9: Registra el proceso de carga (estado = exito, fallido)
						Carga carga = new Carga();
						carga.setNombreCarga(mensajeCorreo.getLstAdjuntos().get(0).getStrNombreAdjunto());
						carga.setNombreArchivo(mensajeCorreo.getStrNombreAdjuntoGuardado());
						carga.setRutaArchivo(configCorreo.getServidorArchivos().getStrUrl());
						carga.setFechaHoraRegistro(new Date()); // BORRAR, esto es ignorado, guarda SYSDATE
						
						// REESCRIBIENDO LA CONDICION PARA QUE VALIDE ERRORES
						boolean bolResultadoValidacionGeneral = (leerContenidoArchivo.getSetMensajesValidacion().size() == 0);
						
						if(bolResultadoValidacionGeneral) {
							carga.setEstado(Constants.ESTADO_CARGA_EXITO);
							carga.setDescripcion(Constants.DEFAULT_VARCHAR);
						} else {
							carga.setEstado(Constants.ESTADO_CARGA_FALLIDO);
							carga.setDescripcion(leerContenidoArchivo.getMensajeValidacion());
						}
						
						carga.setUsuarioCreacion(Constants.USUARIO_ADMIN);
						carga.setPrograma(Constants.PROGRAMA);
						carga.setCodigoArea(Constants.CODIGO_AREA);
						carga.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
						
						// Persistimos en BD
						Integer codCarga = cargaService.registrarCarga(carga);
						
						// Iteramos caudales
						for(Caudal caudal : lstCaudales) {
							// P10: Extraer la información de los caudales, detalles, y rios de las tablas de BD.
//							Caudal caudalBD = caudalService.obtenerCaudal(Integer.parseInt(caudal.getRioBean().getCodigo()), strPeriodoActual, Constants.ESTADO_ACTIVO);
							//set header date period caudal
							Caudal caudalBD = caudalService.obtenerCaudal(Integer.parseInt(caudal.getRioBean().getCodigo()), caudal.getStrPeriodo(), Constants.ESTADO_ACTIVO);
							
							// P11: Valida la informacion del fichero y de las tablas.
							boolean bolResultadoValidacion = CaudalDetalleHelper.validar(caudal, caudalBD);
							
							if(bolResultadoValidacion) {
								// P11: Poblamiento de datos necesarios en caso nuevo caudal o actualizacion en caso exista
								// P11: Registra la información de los caudales.
								
								if(caudalBD == null) {
									caudal.setUsuarioCreacion(Constants.USUARIO_ADMIN);
									caudal.setCodigoArea(Constants.CODIGO_AREA);
									caudal.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
									caudal.setStrRuta(configCorreo.getServidorArchivos().getStrUrl() + File.separator + mensajeCorreo.getStrNombreAdjuntoGuardado());
									caudal.setStrPeriodo(caudal.getStrPeriodo());
									caudal.setStrEstado(Constants.ESTADO_ACTIVO);
									caudal.setStrNombreArchivo(mensajeCorreo.getStrNombreAdjuntoGuardado());
									caudal.setStrTipoProceso(Constants.PROCESO_AUTOMATICO);
									caudal.setIntCodigoCarga(codCarga);
									caudalService.registrarCaudal(caudal);
								} else {
									caudalBD.setUsuarioModificacion(Constants.USUARIO_ADMIN);
									caudalBD.setCodigoArea(Constants.CODIGO_AREA);
									caudalBD.setCodigoSistema(Integer.parseInt(env.getProperty("parametro.sistema.codigo")));
									caudalBD.setStrRuta(configCorreo.getServidorArchivos().getStrUrl() + File.separator + mensajeCorreo.getStrNombreAdjuntoGuardado());
									caudalBD.setStrNombreArchivo(mensajeCorreo.getStrNombreAdjuntoGuardado());
									caudalBD.setLstDetalles(caudal.getLstDetalles());
									caudalBD.setStrTipoProceso(Constants.PROCESO_AUTOMATICO);
									caudalBD.setIntCodigoCarga(codCarga);
									caudalService.actualizarCaudal(caudalBD);
								}
							}
						}
						
						/*remove message of list no process*/
						lstMensajesNoProcess.remove(mensajeCorreo);
						
					} else {
						// Imprimimos los mensajes de validacion de la lectura del fichero adjunto
						// Opcional
						for(String mensajeValidacion : leerContenidoArchivo.getSetMensajesValidacion()) {
							logger.error(mensajeValidacion);
						}
						
						throw new Exception(); // LANZAMOS EXCEPCION NO ESPERADA
					}
					
					// Esto es para que solo intente hacer esta lógica con el primer mensaje que encuentra
					break;
				}
				
				// P12 Borramos el correo leído (el primero) y luego recorremos los siguientes para moverlos de la bandeja
				if(lstMensajes.size() > 1){ // Si es que hay más correos
					lstMensajes.remove(0);
					for(MensajeCorreo mensajeCorreo : lstMensajes) {
						// 12.1: Mover el correo a la carpeta de archivos leidos
						ActualizarMensajeTask actualizarMensajeTask = new ActualizarMensajeTask(exchangeService);
						actualizarMensajeTask.marcarMensaje(mensajeCorreo.getStrIdMensaje());
					}
				}
				
			}
			else{
				// NO SE ECONTRO CORREO CONFIGURAD
				Carga carga = new Carga(Constants.USUARIO_ADMIN, Constants.CODIGO_AREA, 
						Integer.parseInt(env.getProperty("parametro.sistema.codigo")), Constants.ESTADO_CARGA_FALLIDO);
				carga.setDescripcion(Messages.MENSAJE_NO_HAY_CONFIGURACION);
				carga.setNombreCarga(Messages.MENSAJE_ERROR_PROCESO);
				carga.setNombreArchivo(Constants.DEFAULT_VARCHAR);
				carga.setRutaArchivo(Messages.MENSAJE_ERROR_PROCESO);
				// Persistimos en BD
				cargaService.registrarCarga(carga);
			}
			
		} catch(Exception excepcion) {
			// ERRORES NO ESPERADOS
			Carga carga = new Carga(Constants.USUARIO_ADMIN, Constants.CODIGO_AREA, 
					Integer.parseInt(env.getProperty("parametro.sistema.codigo")), Constants.ESTADO_CARGA_FALLIDO);
			carga.setDescripcion(Messages.MENSAJE_ERROR_NO_ESPERADO);
			carga.setNombreCarga(Messages.MENSAJE_ERROR_PROCESO);
			carga.setNombreArchivo(Constants.DEFAULT_VARCHAR);
			carga.setRutaArchivo(Messages.MENSAJE_ERROR_PROCESO);
			// Persistimos en BD
			try {
				/*retornar correos a bandeja de entrada*/
//				if(lstMensajesNoProcess.size() > 0 && exchangeService != null) {
//					for(MensajeCorreo mensajeCorreo: lstMensajesNoProcess) {
//						ActualizarMensajeTask actualizarMensajeTask = new ActualizarMensajeTask(exchangeService);
//						actualizarMensajeTask.returnInboxFolder(mensajeCorreo.getStrIdMensaje());
//					}
//				}
				cargaService.registrarCarga(carga);
				/**/
			} catch (Exception e) {
				logger.error(e);
			}
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			logger.error(error[1], excepcion);
			throw new GmdException(excepcion);
		} finally {
			if(exchangeService != null) {
				exchangeService.close();
			}
		}
	}
}