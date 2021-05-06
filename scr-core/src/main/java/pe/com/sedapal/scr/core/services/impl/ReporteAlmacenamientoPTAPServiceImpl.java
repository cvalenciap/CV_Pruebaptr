/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao;
import pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteAlmacenamientoPTAPServiceImpl.
 */
@Service
public class ReporteAlmacenamientoPTAPServiceImpl implements IReporteAlmacenamientoPTAPService{
	
	/** The reporte almacenamiento PTAP dao. */
	@Autowired
	private IReporteAlmacenamientoPTAPDao reporteAlmacenamientoPTAPDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService#actualizarReporteAlmacenamientoPTAPResul(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void actualizarReporteAlmacenamientoPTAPResul(AnalisisBacteriologicoBean analisisBacteriologicoBean)	throws Exception {
		// TODO Auto-generated method stub
		reporteAlmacenamientoPTAPDao.actualizarReporteAlmacenamientoPTAPResul(analisisBacteriologicoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService#obtenerDatosPTAPResult(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosPTAPResult(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean, Paginacion paginacion) throws Exception {
		return reporteAlmacenamientoPTAPDao.obtenerDatosPTAPResult(reporteAlmacenamientoPTAPBean, paginacion);

	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService#obtenerDatosPTAPResultTable(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean)
	 */
	@Override
	public ReporteAlmacenamientoPTAPBean obtenerDatosPTAPResultTable(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean) throws Exception {
		// TODO Auto-generated method stub
		return reporteAlmacenamientoPTAPDao.obtenerDatosPTAPResultTable(reporteAlmacenamientoPTAPBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteAlmacenamientoPTAPService#actualizarPTAPResultTable(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean)
	 */
	@Override
	public void actualizarPTAPResultTable(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean) throws Exception {
		// TODO Auto-generated method stub
		reporteAlmacenamientoPTAPDao.actualizarPTAPResultTable(reporteAlmacenamientoPTAPBean);
	}

}
