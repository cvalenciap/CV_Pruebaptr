/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoCloroResult;
import pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEnsayoCloroService.
 */
public interface IEnsayoCloroService {
	
	/**
	 * Obtener datos ensayo cloro.
	 *
	 * @param ensayoCloro the ensayo cloro
	 * @param paginacion the paginacion
	 * @return the result
	 */
	public Result obtenerDatosEnsayoCloro(EnsayoCloro ensayoCloro, Paginacion paginacion);
	
	/**
	 * Obtener list ensayo cloro.
	 *
	 * @param idEnsayo the id ensayo
	 * @return the list
	 */
	public List<EnsayoCloroDetalle> obtenerListEnsayoCloro(Integer idEnsayo);
	
	/**
	 * Guardar ensayo cloro.
	 *
	 * @param ensayoCloro the ensayo cloro
	 * @return the int
	 */
	public int guardarEnsayoCloro(EnsayoCloro ensayoCloro);
	
	/**
	 * Guardar ensayo cloro detalle.
	 *
	 * @param ensayoCloroDetalle the ensayo cloro detalle
	 */
	public void guardarEnsayoCloroDetalle(EnsayoCloroDetalle ensayoCloroDetalle);
	
	/**
	 * Buscar ensayo cloro X id.
	 *
	 * @param id the id
	 * @return the ensayo cloro
	 */
	public EnsayoCloro buscarEnsayoCloroXId(Integer id);
	
	/**
	 * Inactivar ensayo cloro.
	 *
	 * @param ensayoCloro the ensayo cloro
	 */
	public void inactivarEnsayoCloro(EnsayoCloro ensayoCloro);
	
	/**
	 * Buscar ensayo cloro fecha hora.
	 *
	 * @param fechaMuetreo the fecha muetreo
	 * @param horaMuestreo the hora muestreo
	 * @return the ensayo cloro
	 */
	public EnsayoCloro buscarEnsayoCloroFechaHora(String fechaMuetreo,String horaMuestreo);
	
	/**
	 * Obtener reporte ensayo cloro.
	 *
	 * @param puntoMuestro the punto muestro
	 * @param fechaMuestreo the fecha muestreo
	 * @return the ensayo cloro detalle
	 */
	public EnsayoCloroDetalle obtenerReporteEnsayoCloro(Integer puntoMuestro, String fechaMuestreo);
	
	/**
	 * Obtener reporte ensayo cloro bandeja.
	 *
	 * @param fechaMes the fecha mes
	 * @return the list
	 */
	public List<ReporteEnsayoCloroResult> obtenerReporteEnsayoCloroBandeja(String fechaMes);
	
	/**
	 * Checks if is valid.
	 *
	 * @param validacionBean the validacion bean
	 * @return the boolean
	 */
	public Boolean isValid(ValidacionEnsayoCloroBean validacionBean);
}
