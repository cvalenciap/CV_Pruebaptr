package pe.com.sedapal.scr.correo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.common.Constants;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class CaudalServiceTest {
	
//	@Autowired
	private ICaudalService caudalService;
	
//	@Test
	public void testGetCaudal() throws Exception {
		int intCodigoRio = 7;
		String strPeriodo = "201706";
		String strEstado = "" + Constants.ESTADO_ACTIVO;
		
		Caudal caudal = caudalService.obtenerCaudal(intCodigoRio, strPeriodo, strEstado);
		
		assertNotNull(caudal);
		assertEquals("7", caudal.getRioBean().getCodigo());
		assertEquals("201706", caudal.getStrPeriodo());
	}
	
//	@Test
	public void testUpdateCaudal() throws Exception {
		Caudal caudal = new Caudal();
		caudal.setIntCodigo(7);
		caudal.setStrTipoProceso("p1");
		caudal.setStrRuta("\\\\192.10.10.10\\backup");
		caudal.setDteFechaHoraRegistro(new Date());
		caudal.setStrEstado("1");
		caudal.setStrNombreArchivo("sedapal_20170623124200.txt");
		caudal.setIntCodigoCarga(1);
		caudal.setDteFechaHoraProceso(new Date());
		caudal.setUsuarioModificacion("edgar.mamani.pe");
		caudal.setPrograma(Constants.PROGRAMA);
		caudal.setCodigoArea(Constants.CODIGO_AREA);
		caudal.setCodigoSistema(Constants.CODIGO_SISTEMA);
		
		caudalService.actualizarCaudal(caudal);
	}

//	@Test
	public void testGenerarReporte() throws Exception {
		
		List resultado = caudalService.generarReporteResumenCaudal("201706");
		
		assertNotNull(resultado);
		
		System.out.println(resultado);
	}
	
//	@Test
	public void testRegistrarCaudal() throws Exception {
		RioBean rioBean = new RioBean();
		rioBean.setCodigo("3");
		
		Caudal caudal = new Caudal();
		caudal.setRioBean(rioBean);
		caudal.setStrTipoProceso("p1");
		caudal.setStrRuta("\\\\192.10.10.10\\backup");
		caudal.setStrPeriodo("201706");
		caudal.setDteFechaHoraRegistro(new Date());
		caudal.setStrEstado("1");
		caudal.setStrNombreArchivo("sedapal.txt");
		caudal.setIntCodigoCarga(1);
		caudal.setDteFechaHoraProceso(new Date());
		caudal.setUsuarioCreacion("emamanic");
		
		Integer intCodigoCaudal = caudalService.registrarCaudal(caudal);
		
		assertNotNull(intCodigoCaudal);
		
		System.out.println(intCodigoCaudal);
	}
}
