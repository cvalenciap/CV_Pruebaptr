/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.ArrayList;
import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico;
import pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAnalisisHibroBiologicoDao.
 */
public interface IAnalisisHibroBiologicoDao{

	/**
	 * Obtener datos analisis hidro biologico.
	 *
	 * @param fecha the fecha
	 * @param codsubac the codsubac
	 * @return the result
	 */
	public Result obtenerDatosAnalisisHidroBiologico(String fecha, Integer codsubac);
	
	/**
	 * Insertar analisis hidro biologico.
	 *
	 * @param analisisHidroBiologico the analisis hidro biologico
	 */
	public void insertarAnalisisHidroBiologico(AnalisisHidroBiologico analisisHidroBiologico);
	
	/**
	 * Datos reporte analisis hidro biologico.
	 *
	 * @param fechaReporte the fecha reporte
	 * @param paginacion the paginacion
	 * @return the result
	 */
	public Result datosReporteAnalisisHidroBiologico(String fechaReporte, Paginacion paginacion);
	
	/**
	 * Datos detalle reporte hidro biologico.
	 *
	 * @param fechaAnalisis the fecha analisis
	 * @param puntoMuestreo the punto muestreo
	 * @return the reporte analisis hidro detalle
	 */
	public ReporteAnalisisHidroDetalle datosDetalleReporteHidroBiologico(String fechaAnalisis,Integer puntoMuestreo);
	
	/**
	 * Insertar reporte hidro biologico.
	 *
	 * @param reporteAnalisisHidroDetalle the reporte analisis hidro detalle
	 */
	public void insertarReporteHidroBiologico(ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle);
	
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
	public ArrayList<ReporteAnalisisHidroDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo);
	
	/**
	 * Eliminar reporte hidro por correlativo.
	 *
	 * @param idCorrelativo the id correlativo
	 * @return true, if successful
	 */
	public boolean eliminarReporteHidroPorCorrelativo(Integer idCorrelativo);
	
	/**
	 * Obtener analisis por parametros.
	 *
	 * @param fechaMuestra the fecha muestra
	 * @param puntoMuestra the punto muestra
	 * @param tipoOrganismo the tipo organismo
	 * @return the list
	 */
	public List<AnalisisHidroBiologico> obtenerAnalisisPorParametros(String fechaMuestra,Integer puntoMuestra, String tipoOrganismo);
}
