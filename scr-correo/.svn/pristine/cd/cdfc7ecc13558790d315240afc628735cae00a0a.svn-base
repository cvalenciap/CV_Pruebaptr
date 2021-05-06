package pe.com.sedapal.scr.correo.service;

import static org.junit.Assert.assertNotNull;

import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class ConfigCorreoServiceTest {

//	@Autowired
	public IConfigCorreoService configCorreoService;
	
//	@Test
	public void testObtenerConfigCorreo() throws Exception {
		String strTipoOperacion = "EXTRACCION";
		String strEstado = "1";
		
		ConfiguracionCorreo configCorreo = configCorreoService.obtenerConfigCorreo(strTipoOperacion, strEstado);
		
		assertNotNull(configCorreo);
		
		System.out.println(configCorreo.getCuentaCorreo().getStrCorreo());
	}
}
