/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico;
import pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle;
import pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao;
import pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHibroBiologicoImpl.
 */
@Service
public class AnalisisHibroBiologicoImpl implements IAnalisisHibroBiologicoService {

	/** The analisis hibro biologico dao. */
	@Autowired
	private IAnalisisHibroBiologicoDao analisisHibroBiologicoDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#insertarAnalisisHidroBiologico(pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico)
	 */
	@Override
	public void insertarAnalisisHidroBiologico(AnalisisHidroBiologico analisisHidroBiologico) {
		analisisHibroBiologicoDao.insertarAnalisisHidroBiologico(analisisHidroBiologico);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#datosReporteAnalisisHidroBiologico(java.lang.String, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result datosReporteAnalisisHidroBiologico(String fechaReporte, Paginacion paginacion){
		return analisisHibroBiologicoDao.datosReporteAnalisisHidroBiologico(fechaReporte, paginacion);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#datosDetalleReporteHidroBiologico(java.lang.String, java.lang.Integer)
	 */
	@Override
	public ReporteAnalisisHidroDetalle datosDetalleReporteHidroBiologico(String fechaAnalisis,Integer puntoMuestreo){
		return analisisHibroBiologicoDao.datosDetalleReporteHidroBiologico(fechaAnalisis, puntoMuestreo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#insertarReporteHidroBiologico(pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle)
	 */
	@Override
	public void insertarReporteHidroBiologico(ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle){
		 analisisHibroBiologicoDao.insertarReporteHidroBiologico(reporteAnalisisHidroDetalle);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#obtenerCorrelativoDetalleReporte()
	 */
	@Override
	public Integer obtenerCorrelativoDetalleReporte() {
		return analisisHibroBiologicoDao.obtenerCorrelativoDetalleReporte();
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#obtenerDetallePorCorrelativo(java.lang.Integer)
	 */
	@Override
	public ArrayList<ReporteAnalisisHidroDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo){
		return analisisHibroBiologicoDao.obtenerDetallePorCorrelativo(idCorrelativo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#eliminarReporteHidroPorCorrelativo(java.lang.Integer)
	 */
	@Override
	public boolean eliminarReporteHidroPorCorrelativo(Integer idCorrelativo){
		return analisisHibroBiologicoDao.eliminarReporteHidroPorCorrelativo(idCorrelativo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisHibroBiologicoService#obtenerAnalisisPorParametros(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<AnalisisHidroBiologico> obtenerAnalisisPorParametros(String fechaMuestra,Integer puntoMuestra, String tipoOrganismo){
		return analisisHibroBiologicoDao.obtenerAnalisisPorParametros(fechaMuestra, puntoMuestra, tipoOrganismo);
	}
	
}
