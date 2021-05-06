package pe.com.sedapal.scr.correo.task;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.ServidorArchivos;
import pe.com.sedapal.scr.correo.task.LeerArchivoCaudalesTask;
import pe.com.sedapal.scr.core.beans.RioBean;

public class LeerArchivoCaudalesTaskTest {
	private ServidorArchivos servidorArchivos;
	private List<RioBean> rios = new ArrayList<RioBean>();

//	@Before
	public void setUp() {
		servidorArchivos = new ServidorArchivos();
		servidorArchivos.setStrUrl("D:\\FileServerTest\\sedapal.txt");
		
		RioBean rioBean = new RioBean();
		rioBean.setNombreLargo("CAUDALES EN EL CANAL HACIA HUAMPANI (m3/s)");
		rios.add(rioBean);
		
		rioBean = new RioBean();
		rioBean.setNombreLargo("CAUDAL TOTAL DEL RIMAC A LA ALTURA DE LA TOMA HUAMPANI (m3/s)");
		rios.add(rioBean);
		
		rioBean = new RioBean();
		rioBean.setNombreLargo("CAUDALES EN LA TOMA SHEQUE (m3/s)");
		rios.add(rioBean);
		
		rioBean = new RioBean();
		rioBean.setNombreLargo("CAUDALES EN LA TOMA TAMBORAQUE (m3/s)");
		rios.add(rioBean);
		
		rioBean = new RioBean();
		rioBean.setNombreLargo("VOLUMEN DE LA PRESA SHEQUE (Mm3)");
		rios.add(rioBean);
	}
	
//	@Test
	public void testLeerArchivoCaudales() {
//		LeerArchivoCaudalesTask leerCaudalesTask = new LeerArchivoCaudalesTask(servidorArchivos, rios);
//		
//		List<Caudal> caudales = leerCaudalesTask.leerCaudales();
//		
//		for(Caudal caudal : caudales) {
//			System.out.println(caudal);
//		}
//		
//		assertEquals(5, caudales.size());
	}
}
