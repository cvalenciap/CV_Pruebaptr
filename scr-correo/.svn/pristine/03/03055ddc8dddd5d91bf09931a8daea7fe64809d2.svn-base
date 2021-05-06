package pe.com.sedapal.scr.correo.task;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.scr.correo.core.beans.AdjuntoMensaje;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.MensajeUtil;

public class EnviarMensajeTaskTest extends MensajesTaskTest {
	
	@Autowired
	private EnviarMensajeTask enviarMensajeTask;

	@Test
	public void testEnviarMensaje() throws Exception {
		
		MensajeCorreo mensajeCorreo = new MensajeCorreo();
		
		/* ---Remitente--- */
		mensajeCorreo.setStrRemitente(MensajeUtil.getMensaje(MensajeUtil.MENSAJE_REMITENTE_CORREO));
		
		/* ---Destinatarios--- */
		List<String> lstDestinatarios = configCorreo.getMensajeCorreo().getLstDestinatarios();
		if(lstDestinatarios.size()>0){
			List<String> destinatarios = new ArrayList<String>();
			for (int i=0; i<lstDestinatarios.size(); i++){
				String strDestinatarios = lstDestinatarios.get(i);
				String[] arrDestinatarios = strDestinatarios.split(",");
				for(int j=0; j<arrDestinatarios.length; j++){
					String strDestinatario = arrDestinatarios[j];					
					destinatarios.add(strDestinatario);
				}				
			}
			mensajeCorreo.setLstDestinatarios(destinatarios);
		}
		
		/* ---CCs--- */
		List<String> lstCcs = configCorreo.getMensajeCorreo().getLstCcs();
		if(lstCcs.size()>0){
			List<String> ccs = new ArrayList<String>();
			for (int i=0; i<lstCcs.size(); i++){
				String strCcs = lstCcs.get(i);
				String[] arrCcs = strCcs.split(",");
				for(int j=0; j<arrCcs.length; j++){
					String strCc = arrCcs[j];					
					ccs.add(strCc);
				}				
			}
			mensajeCorreo.setLstCcs(ccs);
		}
			
		/* ---Asunto--- */
		mensajeCorreo.setStrAsunto(configCorreo.getMensajeCorreo().getStrAsunto());
		
		/* ---Contenido--- */
		mensajeCorreo.setStrContenido(configCorreo.getMensajeCorreo().getStrContenido());
		
		/* ---Adjuntos--- */
		List<AdjuntoMensaje> adjuntos = new ArrayList<AdjuntoMensaje>();
		
			/* ---Adjunto Reporte Grafico--- */
			String[] fechaRutaGrafico = enviarMensajeTask.generarReporteGraficoCaudal();
		
			AdjuntoMensaje adjuntoUno = new AdjuntoMensaje();		
			Path rutaUno = Paths.get(fechaRutaGrafico[1]);
			byte[] archivoUno = Files.readAllBytes(rutaUno);
		
			adjuntoUno.setStrNombreAdjunto(Constants.REPORTE_GRAFICO+fechaRutaGrafico[0].substring(6,9)+""+fechaRutaGrafico[0].substring(3,4)+".pdf");
			adjuntoUno.setBytArchivoAdjunto(archivoUno);		
			adjuntos.add(adjuntoUno);
			
			/* ---Adjunto Reporte Resumen--- */
			String[] fechaRutaResumen = enviarMensajeTask.generarReporteResumenCaudal();
		
			AdjuntoMensaje adjuntoDos = new AdjuntoMensaje();
			Path rutaDos = Paths.get(fechaRutaResumen[1]);
			byte[] archivoDos = Files.readAllBytes(rutaDos);		
		
			adjuntoDos.setStrNombreAdjunto(Constants.REPORTE_RESUMEN+fechaRutaResumen[0].substring(6,9)+""+fechaRutaResumen[0].substring(3,4)+".pdf");
			adjuntoDos.setBytArchivoAdjunto(archivoDos);		
			adjuntos.add(adjuntoDos);
		
		mensajeCorreo.setLstAdjuntos(adjuntos);		
		
		enviarMensajeTask.enviarMensaje(mensajeCorreo, exchangeService);
	}
	
}
