/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ReporteFitoplanctonDetalle;
import pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao;
import pe.com.sedapal.scr.core.services.IReporteFitoplanctonService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteFitoplanctonServiceImpl.
 */
@Service
public class ReporteFitoplanctonServiceImpl implements IReporteFitoplanctonService{

	/** The reporte fitoplancton dao. */
	@Autowired
	private IReporteFitoplanctonDao reporteFitoplanctonDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#datosReporteFitoplancton(java.lang.String, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result datosReporteFitoplancton(String fechaReporte, Paginacion paginacion) {
		return reporteFitoplanctonDao.datosReporteFitoplancton(fechaReporte, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#datosDetalleReporteFitoplancton(java.lang.String, java.lang.Integer)
	 */
	@Override
	public ReporteFitoplanctonDetalle datosDetalleReporteFitoplancton(String fechaAnalisis, Integer puntoMuestreo) {
		return reporteFitoplanctonDao.datosDetalleReporteFitoplancton(fechaAnalisis, puntoMuestreo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#insertarReporteFitoplancton(pe.com.sedapal.scr.core.beans.ReporteFitoplanctonDetalle)
	 */
	@Override
	public void insertarReporteFitoplancton(ReporteFitoplanctonDetalle reporteFitoplanctonDetalle) {
		reporteFitoplanctonDao.insertarReporteFitoplancton(reporteFitoplanctonDetalle);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#obtenerCorrelativoDetalleReporte()
	 */
	@Override
	public Integer obtenerCorrelativoDetalleReporte() {
		return  reporteFitoplanctonDao.obtenerCorrelativoDetalleReporte();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#obtenerDetallePorCorrelativo(java.lang.Integer)
	 */
	@Override
	public ArrayList<ReporteFitoplanctonDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo) {
		return reporteFitoplanctonDao.obtenerDetallePorCorrelativo(idCorrelativo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IReporteFitoplanctonService#eliminarReportePorCorrelativo(java.lang.Integer)
	 */
	@Override
	public boolean eliminarReportePorCorrelativo(Integer idCorrelativo) {
		return reporteFitoplanctonDao.eliminarReportePorCorrelativo(idCorrelativo);
	}
	

}
