package pe.com.sedapal.scr.correo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.common.Constants;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class RioServiceTest {
	
//	@Autowired
	private IRioService irioService;

//	@Test
	public void testObtenerRiosActivos() throws Exception {
		String strEstado = "" + Constants.ESTADO_ACTIVO;
		
		List<RioBean> lstRios = irioService.listarRiosPorEstado(strEstado);
		
		assertNotNull(lstRios);
		assertEquals(7, lstRios.size());
	}
}
