package pe.com.sedapal.scr.correo.service;

import java.util.Date;

import pe.com.sedapal.scr.correo.core.beans.Carga;
import pe.com.sedapal.scr.correo.core.common.Constants;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class CargaServiceTest {
	
//	@Autowired
	private ICargaService cargaService;

//	@Test
	public void testRegistrarCarga() throws Exception {
		Carga carga = new Carga();
		carga.setNombreCarga("CargaPrueba");
		carga.setNombreArchivo("sedapal.txt");
		carga.setRutaArchivo("\\\\192.168.12.13\\cargas");
		carga.setFechaHoraRegistro(new Date());
		carga.setEstado(Constants.ESTADO_CARGA_EXITO);
		carga.setDescripcion(null);
		
		carga.setUsuarioCreacion("emamanic");
		carga.setPrograma("69");
		carga.setCodigoArea(69);
		carga.setCodigoSistema(69);
		
		cargaService.registrarCarga(carga);
	}
}
