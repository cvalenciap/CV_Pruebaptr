package pe.com.sedapal.scr.correo.task;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pe.com.sedapal.scr.correo.task.ActualizarMensajeTask;

public class ActualizarMensajeTaskTest extends MensajesTaskTest {
	
//	@Test
	public void testActualizarMensajes() throws Exception {
		List<String> idsMensajes = new ArrayList<String>();
		idsMensajes.add("AAMkADc2MWNjMDEzLWQ1ZTYtNDgxNy1hZDc0LTMwMjcwZmY3YWJkYwBGAAAAAAB3Jajoj6cOSaTfy58vqRm8BwBHnadumOjIQpnViMmKttxWAAAAyu0NAABHnadumOjIQpnViMmKttxWAAAAy4GUAAA=");
		
		ActualizarMensajeTask actualizarMensajesTask = new ActualizarMensajeTask(exchangeService);
		int result = actualizarMensajesTask.marcarMensajes(idsMensajes);
		
		assertEquals(idsMensajes.size(), result);
	}
}
