package pe.com.sedapal.scr.correo.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalDia;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalHora;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {ScrCorreoApplication.class})
public class CaudalDetalleServiceTest {

//	@Autowired
	private ICaudalDetalleService caudalDetalleService;
	
//	@Test
	public void testRegistrarCaudalDetalle() throws Exception {
		CaudalDetalle caudalDetalle = new CaudalDetalle();
		
		Caudal caudal = new Caudal();
		caudal.setIntCodigo(7);
		caudalDetalle.setCaudal(caudal);
		
		List<MedidaCaudalHora> lstCaudalesDia = new ArrayList<MedidaCaudalHora>();
		
		MedidaCaudalHora medidaCaudalHora = new MedidaCaudalHora();
		medidaCaudalHora.setStrHora("01");
		medidaCaudalHora.setDblValor(69.69);
		lstCaudalesDia.add(medidaCaudalHora);
		
		medidaCaudalHora = new MedidaCaudalHora();
		medidaCaudalHora.setStrHora("02");
		medidaCaudalHora.setDblValor(70.0);
		lstCaudalesDia.add(medidaCaudalHora);
		
		medidaCaudalHora = new MedidaCaudalHora();
		medidaCaudalHora.setStrHora("03");
		medidaCaudalHora.setDblValor(71d);
		lstCaudalesDia.add(medidaCaudalHora);
		
		medidaCaudalHora = new MedidaCaudalHora();
		medidaCaudalHora.setStrHora("04");
		medidaCaudalHora.setDblValor(69d);
		lstCaudalesDia.add(medidaCaudalHora);
		
		MedidaCaudalDia medidaCaudalDia = new MedidaCaudalDia();
		medidaCaudalDia.setStrDia("01");
		medidaCaudalDia.setLstCaudalesDia(lstCaudalesDia);
		caudalDetalle.setCaudalDia(medidaCaudalDia);
		caudalDetalle.setCodigoArea(1);
		caudalDetalle.setCodigoSistema(2);
		caudalDetalle.setPrograma("00");
		caudalDetalle.setUsuarioCreacion("emamanic");
		
		Integer intCodigoDetalle = caudalDetalleService.registrarCaudalDetalle(caudalDetalle);
		
		assertNotNull(intCodigoDetalle);
		
		System.out.println(intCodigoDetalle);
	}
}
