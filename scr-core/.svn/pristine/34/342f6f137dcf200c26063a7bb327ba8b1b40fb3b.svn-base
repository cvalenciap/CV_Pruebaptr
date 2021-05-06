/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.ArrayList;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ReporteFitoplanctonDetalle;

// TODO: Auto-generated Javadoc
/**
 * The Interface IReporteFitoplanctonService.
 */
public interface IReporteFitoplanctonService {

	/**
	 * Datos reporte fitoplancton.
	 *
	 * @param fechaReporte the fecha reporte
	 * @param paginacion the paginacion
	 * @return the result
	 */
	public Result datosReporteFitoplancton(String fechaReporte, Paginacion paginacion);
	
	/**
	 * Datos detalle reporte fitoplancton.
	 *
	 * @param fechaAnalisis the fecha analisis
	 * @param puntoMuestreo the punto muestreo
	 * @return the reporte fitoplancton detalle
	 */
	public ReporteFitoplanctonDetalle datosDetalleReporteFitoplancton(String fechaAnalisis,Integer puntoMuestreo);
	
	/**
	 * Insertar reporte fitoplancton.
	 *
	 * @param reporteFitoplanctonDetalle the reporte fitoplancton detalle
	 */
	public void insertarReporteFitoplancton(ReporteFitoplanctonDetalle reporteFitoplanctonDetalle);
	
	/**
	 * Obtener correlativo detalle reporte.
	 *
	 * @return the integer
	 */
	public Integer obtenerCorrelativoDetalleReporte();
	
	/**
	 * Obtener detalle por correlativo.
	 *
	 * @param idCorrelativo the id correlativo
	 * @return the array list
	 */
	public ArrayList<ReporteFitoplanctonDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo);
	
	/**
	 * Eliminar reporte por correlativo.
	 *
	 * @param idCorrelativo the id correlativo
	 * @return true, if successful
	 */
	public boolean eliminarReportePorCorrelativo(Integer idCorrelativo);
}
