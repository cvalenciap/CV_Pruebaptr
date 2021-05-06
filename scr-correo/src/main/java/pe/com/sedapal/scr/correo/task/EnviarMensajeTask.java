/**
 * 
 * Resumen.
 * Objeto 				: EnviarMensajeTask
 * Descripción 			: Clase relacionada con los reportes de correo
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.task;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import net.sf.jasperreports.engine.JRParameter;
import pe.com.sedapal.scr.correo.core.beans.AdjuntoMensaje;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.beans.ReporteGraficoBean;
import pe.com.sedapal.scr.correo.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.FechaUtil;
import pe.com.sedapal.scr.correo.service.ICaudalService;
import pe.com.sedapal.scr.correo.util.ReporteUtilCaudal;

@Component
public class EnviarMensajeTask {

	@Autowired	
	private ICaudalService caudalService;
	
	/**
	 * Método que permite enviar mensaje (con adjuntos)
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void enviarMensaje(MensajeCorreo mensajeCorreo, ExchangeService exchangeService) throws Exception {
		EmailMessage emailMessage = new EmailMessage(exchangeService);

		EmailAddress from = new EmailAddress(mensajeCorreo.getStrRemitente());
		emailMessage.setFrom(from);

		for (String destinatario : mensajeCorreo.getLstDestinatarios()) {
			EmailAddress to = new EmailAddress(destinatario);
			emailMessage.getToRecipients().add(to);
		}
		
		for (String conCopia : mensajeCorreo.getLstCcs()) {
			EmailAddress cc = new EmailAddress(conCopia);
			emailMessage.getCcRecipients().add(cc);
		}

		emailMessage.setSubject(mensajeCorreo.getStrAsunto());

		MessageBody messageBody = new MessageBody(mensajeCorreo.getStrContenido());
		emailMessage.setBody(messageBody);

		if(mensajeCorreo.getLstAdjuntos() != null){
			for (AdjuntoMensaje adjunto : mensajeCorreo.getLstAdjuntos()) {
				emailMessage.getAttachments().addFileAttachment(adjunto.getStrNombreAdjunto(),
						adjunto.getBytArchivoAdjunto());
			}
		}
		
		emailMessage.send();
	}	
	
	/**
	 * Método que permite obtener detalles de caudales para un periodo,
	 * transformar a PDF y enrutar a un directorio
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public String[] generarReporteResumenCaudal() throws IOException {
		String[] fechaRuta = new String[2];		
		try{			
			String directorio =  System.getProperty("java.io.tmpdir");	
			
			if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
				directorio = directorio + System.getProperty("file.separator");
			}
			
			String fecha = FechaUtil.ahoraEnCadena("dd/MM/yyyy");
			fechaRuta[0] = fecha;
			
			String archivo = directorio + Constants.REPORTE_RESUMEN+fecha.substring(6,10)+""+fecha.substring(3,5)+".pdf" ;
			fechaRuta[1] = archivo;
			
			String nombreJasper = "/report_resumen.jasper";						
			
			List<ReporteResumenBean> result = caudalService.generarReporteResumenCaudal(fecha.substring(6,10)+""+fecha.substring(3,5));

			Map<String, Object> params = new HashMap<>();
			params.put(JRParameter.REPORT_LOCALE, Locale.US);
			params.put("BASE_DIR", this.getClass().getResource("/reportes/").getPath());

			ReporteUtilCaudal.generarPDF(nombreJasper, params, result, archivo);			
		} catch (Exception e) {
			e.getMessage();
		}
		return fechaRuta;
	}
	
	/**
	 * Método que permite obtener detalles de caudales para un periodo,
	 * transformar a Excel y enrutar a un directorio
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public String[] generarReporteResumenCaudalExcel() throws IOException {
		String[] fechaRuta = new String[2];		
		try{			
			String directorio =  System.getProperty("java.io.tmpdir");	
			
			if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
				directorio = directorio + System.getProperty("file.separator");
			}
			
			String fecha = FechaUtil.ahoraEnCadena("dd/MM/yyyy");
			fechaRuta[0] = fecha;
			
			String archivo = directorio + Constants.REPORTE_RESUMEN+fecha.substring(6,10)+""+fecha.substring(3,5)+".xls" ;
			fechaRuta[1] = archivo;
			
			String template = "reporte_resumen_template.xls";
			
			List<ReporteResumenBean> result = caudalService.generarReporteResumenCaudal(fecha.substring(6,10)+""+fecha.substring(3,5));

			Map<String, Object> values = new HashMap<>();
			values.put("values", result);
			values.put("periodo", result != null && result.get(0) != null ? result.get(0).getPERIODO() : "");
			
			ReporteUtilCaudal.generarXLS(template, values, archivo);
		} catch (Exception e) {
			e.getMessage();
		}
		return fechaRuta;
	}
	
	/**
	 * Método que permite eliminar archivos
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void deleteTempFiles(String nomAdj1, String nomAdj2, String nomAdj3) throws IOException{
		String directorio =  System.getProperty("java.io.tmpdir");		
		
		if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
			directorio = directorio + System.getProperty("file.separator");
		}
		
		File directory = new File(directorio);
		File[] files = directory.listFiles();
		for (File f : files)
		{
		    if (f.getName().equals(nomAdj1) || f.getName().equals(nomAdj2) || f.getName().equals(nomAdj3))
		    {
		      f.delete();
		    }
		}
	}
	
	/**
	 * Método que permite obtener promedios diarios para un periodo
	 * transformar a PDF y enrutar a un directorio
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public String[] generarReporteGraficoCaudal() throws IOException {
		String[] fechaRuta = new String[2];		
		try{			
			String directorio =  System.getProperty("java.io.tmpdir");		
			
			if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
				directorio = directorio + System.getProperty("file.separator");
			}
			
			String fecha = FechaUtil.ahoraEnCadena("dd/MM/yyyy");
			fechaRuta[0] = fecha;
			
			String archivo = directorio + Constants.REPORTE_GRAFICO+fecha.substring(6,10)+""+fecha.substring(3,5)+".pdf" ;
			fechaRuta[1] = archivo;
			
			String nombreJasper = "/report_grafico.jasper";						
			
			List<ReporteGraficoBean> result = caudalService.generarReporteGraficoCaudal(fecha.substring(6,10)+""+fecha.substring(3,5));

			Map<String, Object> params = new HashMap<>();
			params.put(JRParameter.REPORT_LOCALE, Locale.US);
			params.put("BASE_DIR", this.getClass().getResource("/reportes/").getPath());

			ReporteUtilCaudal.generarPDF(nombreJasper, params, result, archivo);			
		} catch (Exception e) {
			e.getMessage();
		}
		return fechaRuta;
	}
	
}
