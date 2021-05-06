/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.scr.core.beans.ReporteForm312Bean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IFormulario312Service.
 */
public interface IFormulario312Service {
	
	/**
	 * Generar reporte resumen grafico.
	 *
	 * @param pFechaIni the fecha ini
	 * @param pFechaFin the fecha fin
	 * @param pTipo the tipo
	 * @return the reporte form 312 bean
	 * @throws Exception the exception
	 */
	ReporteForm312Bean generarReporteResumenGrafico(String pFechaIni, String pFechaFin, int pTipo) throws Exception;

}
