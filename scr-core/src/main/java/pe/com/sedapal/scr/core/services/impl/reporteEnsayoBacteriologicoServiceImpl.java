/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoBacteriologicoBean;
import pe.com.sedapal.scr.core.dao.IreporteEnsayoBacteriologicoDao;
import pe.com.sedapal.scr.core.services.IreporteEnsayoBacteriologicoService;

// TODO: Auto-generated Javadoc
/**
 * The Class reporteEnsayoBacteriologicoServiceImpl.
 */
@Service
public class reporteEnsayoBacteriologicoServiceImpl implements IreporteEnsayoBacteriologicoService {

	/** The reporte ensayo bacteriologico dao. */
	@Autowired
	private IreporteEnsayoBacteriologicoDao reporteEnsayoBacteriologicoDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IreporteEnsayoBacteriologicoService#obtenerDatosEnsayoBacteriologico(pe.com.sedapal.scr.core.beans.ReporteEnsayoBacteriologicoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEnsayoBacteriologico(ReporteEnsayoBacteriologicoBean reporteEnsayoBacteriologicoBean,	Paginacion paginacion) throws Exception {
		
		reporteEnsayoBacteriologicoDao.exeInicioEnsayoBacteriologico(reporteEnsayoBacteriologicoBean.getIntIdCabecera());
		
		return reporteEnsayoBacteriologicoDao.obtenerDatosEnsayoBacteriologico(reporteEnsayoBacteriologicoBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IreporteEnsayoBacteriologicoService#obtenerDatosEficiencia(pe.com.sedapal.scr.core.beans.ReporteEnsayoBacteriologicoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEficiencia(ReporteEnsayoBacteriologicoBean reporteEnsayoBacteriologicoBean, Paginacion paginacion) throws Exception {
		// TODO Auto-generated method stub
		return reporteEnsayoBacteriologicoDao.obtenerDatosEficiencia(reporteEnsayoBacteriologicoBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IreporteEnsayoBacteriologicoService#actualizarAnalisisBacteriologicoResul(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void actualizarAnalisisBacteriologicoResul(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		// TODO Auto-generated method stub
		reporteEnsayoBacteriologicoDao.actualizarAnalisisBacteriologicoResul(analisisBacteriologicoBean);
	}

	

}
