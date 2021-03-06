package pe.com.sedapal.scr.correo.task;

import static org.junit.Assert.assertEquals;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;

public class LeerMensajesTaskTest extends MensajesTaskTest {
	private List<MensajeCorreo> mensajesCorreo;
	
//	@Test
	public void testLeerMensajes() throws Exception {
		LeerMensajesTask leerMensajesTask = new LeerMensajesTask(configCorreo, exchangeService);
		
		mensajesCorreo = leerMensajesTask.leerMensajes(1);
		
		for(MensajeCorreo mensajeCorreo : mensajesCorreo) {
			System.out.println(mensajeCorreo.getStrIdMensaje());
		}
		
		assertEquals(1, mensajesCorreo.size());
		
		// inner test to avoid setUp, because it takes long time
		testGuardarAdjunto();
	}
	
	public void testGuardarAdjunto() throws GmdException{
		GuardarArchivoCaudalesTask guardarAdjuntoTask = new GuardarArchivoCaudalesTask(configCorreo.getServidorArchivos());
		
		if(mensajesCorreo != null && mensajesCorreo.size() > 0) {
			guardarAdjuntoTask.guardarAdjunto(mensajesCorreo.get(0));
		}
	}
}
