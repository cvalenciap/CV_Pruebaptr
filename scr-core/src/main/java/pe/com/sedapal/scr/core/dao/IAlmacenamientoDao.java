/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.sedapal.scr.core.beans.AlmacenamientoBean;
import pe.com.sedapal.scr.core.beans.BaseBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlmacenamientoDao.
 */
public interface IAlmacenamientoDao {

	/**
	 * Obtiene la información de datos calculados para el reporte de represas por periodo.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo AlmacenamientoBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<AlmacenamientoBean> obtenerDatosAcumulados(String periodo) throws Exception;
	
	/**
	 * Obtiene la información de datos calculados para el correo enviado a gerencia en una fecha .
	 *
	 * @param fecha Es la fecha para la que se requiere generar el reporte
	 * @return lista de objetos de tipo AlmacenamientoBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	AlmacenamientoBean obtenerDatosAcumuladosPorDia(String fecha) throws Exception;
	
	/**
	 * Obtiene el nombre de las represas excluidas para el calculo de la columna uno del excel.
	 *
	 * @return objeto de tipo String que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	String obtenerRepresasExcluidasColumnaUno() throws Exception;
	
	/**
	 * Realiza la modificación de almacenamiento.
	 *
	 * @param strFecha Es la fecha para la cual se realiza la modificación
	 * @param strFormulaEmbalse Formula a evaluar
	 * @param strFormulaManDescarga Formula a evaluar
	 * @param strFormulaUno Formula a evaluar
	 * @param strFormulaDos Formula a evaluar
	 * @param auditoria Es el objeto con los datos de auditoria
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarAlmacenamiento(String strFecha, String strFormulaEmbalse, String strFormulaManDescarga, 
			String strFormulaUno, String strFormulaDos, BaseBean auditoria) throws Exception;
}
