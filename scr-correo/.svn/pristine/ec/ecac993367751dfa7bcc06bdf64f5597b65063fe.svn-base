/**
 * 
 * Resumen.
 * Objeto 				: ICaudalDao
 * Descripción 			: Clase interfaz (de acceso a datos) para el caudal
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.dao;

import java.util.List;

import pe.com.sedapal.scr.correo.core.beans.ReporteResumenBean;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.ReporteGraficoBean;

public interface ICaudalDao {
	
	/**
	 * Método que permite obtener detalles de caudales para un periodo
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ReporteResumenBean> generarReporteResumenCaudal (String periodo) throws Exception;
	
	/**
	 * Método que permite obtener promedios diarios para un periodo
	 * @Return Objeto de tipo ReporteGraficoBean que contiene los datos promedios diarios 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ReporteGraficoBean> generarReporteGraficoCaudal (String periodo) throws Exception;
	
	/**
	 * Método que permite registrar caudal
	 * @Return Objeto de tipo Integer que contiene código de caudal 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public Integer registrarCaudal(Caudal caudal) throws GmdException;
	
	/**
	 * Método que permite actualizar caudal
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void actualizarCaudal(Caudal caudal) throws GmdException;
	
	/**
	 * Método que permite obtener caudales para un periodo y sobre un estado
	 * @Return Objeto de tipo Caudal que contiene los datos caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public Caudal obtenerCaudal(Integer intCodigoRio, String strPeriodo, String strEstado) throws GmdException;
	
}
