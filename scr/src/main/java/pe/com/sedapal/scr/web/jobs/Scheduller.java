/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.services.ICommSubidaArchivosService;
import pe.com.sedapal.scr.core.services.impl.CommSubidaArchivosServiceImpl;
import pe.com.sedapal.scr.correo.job.ETLMensajesCorreoJob;
import pe.com.sedapal.scr.correo.job.ETLReportesResumenJob;

// TODO: Auto-generated Javadoc
/**
 * The Class Scheduller.
 */
@Service
public class Scheduller {
	
	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(CommSubidaArchivosServiceImpl.class);

	
	/** The etl mensaje correo job. */
	@Autowired
	private ETLMensajesCorreoJob etlMensajeCorreoJob;
	
	/** The etl reportes resumen job. */
	@Autowired
	private ETLReportesResumenJob etlReportesResumenJob;
	
	/** The subida archivo service. */
	@Autowired
	private ICommSubidaArchivosService subidaArchivoService;
	
	/**
	 * Método que permite ejecutar la tarea programada de extracción de correo.
	 *
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return No hay objeto de retorno
	 */
	@Scheduled(fixedRate=3600000) // 1h ==== 3600000 ms
	public void executeExtraccion() throws Exception {
		System.out.println("INICIANDO PROCESO AUTOMATICO (EXTRACCION DE CORREO)");
		System.out.println(etlMensajeCorreoJob);
		this.etlMensajeCorreoJob.execute();
		System.out.println("FINALIZANDO PROCESO AUTOMATICO (EXTRACCION DE CORREO)");
	}
	
	/**
	 * Método que permite ejecutar la tarea programada de envío de correo.
	 *
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return No hay objeto de retorno
	 */
//	@Scheduled(fixedRate=86400000) // 24h ==== 86400000 ms
	@Scheduled(fixedRate=3600000) // 1h ==== 3600000 ms
	public void executeResumen() throws Exception {
		System.out.println("INICIANDO PROCESO DE ENVIO DE CORREO RESUMEN DESDE SCRWEB");
		System.out.println(etlReportesResumenJob);
		this.etlReportesResumenJob.execute();
		System.out.println("FINALIZANDO PROCESO DE ENVIO DE CORREO RESUMEN DESDE SCRWEB");
	}
	
	/**
	 * Execute limpieza.
	 *
	 * @throws Exception the exception
	 */
	// Help: https://www.freeformatter.com/cron-expression-generator-quartz.html
	@Scheduled(fixedRate=7200000) // 2h ==== 7200000 ms
	//@Scheduled(cron="0 0 0/2 ? * * *"); : At second :00, at minute :00, every 2 hours starting at 00am, of every day
	public void executeLimpieza() throws Exception {
		logger.info("INICIANDO PROCESO DE LIMPIEZA DE TEMPORALES");
		subidaArchivoService.limpiarTemporales(DateUtils.now());
		logger.info("FINALIZANDO PROCESO DE LIMPIEZA DE TEMPORALES");
	}
	
}
