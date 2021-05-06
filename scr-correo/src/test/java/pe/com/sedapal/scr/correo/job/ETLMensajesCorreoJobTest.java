package pe.com.sedapal.scr.correo.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.gmd.util.exception.GmdException;

//import pe.com.sedapal.scr.correo.ScrCorreoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class ETLMensajesCorreoJobTest {
	@Autowired
	private ETLMensajesCorreoJob etlMensajesCorreoJob;
	
	@Test
	public void testExecute() throws GmdException{
		etlMensajesCorreoJob.execute();
	}
}