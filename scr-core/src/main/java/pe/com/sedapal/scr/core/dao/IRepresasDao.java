/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.scr.core.beans.CalculoRepresasBean;
import pe.com.sedapal.scr.core.beans.DatosRepresasGerenciaBean;
import pe.com.sedapal.scr.core.beans.ReportePivotBean;
import pe.com.sedapal.scr.core.beans.ReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.beans.Result;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRepresasDao.
 */
public interface IRepresasDao {
	
	/**
	 * Método que permite obtener el listado de Represas.
	 *
	 * @param represasBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Result buscarRepresas(RepresasBean represasBean, Paginacion paginacion) throws Exception;
	
	/**
	 *  
	 * Obtiene el registro de represas por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	RepresasBean obtenerRepresas(Integer nid) throws Exception;
	
	/**
	 * Realiza el registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @return objeto del tipo RepresasBean con los valor autogenerados seteados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	RepresasBean registrarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Realiza la modificación de registro de represas.
	 *
	 * @param represasBean the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Realiza la modificación de registro de represas pudiendo solo updatear el factor de desacarga.
	 *
	 * @param represasBean the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarFactorRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Realiza la modificación de las formulas de represas menos del factor de represas.
	 *
	 * @param calculoRepresasBean objeto del tipo CalculoRepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarFormulasRepresas(CalculoRepresasBean calculoRepresasBean) throws Exception;
	
	/**
	 * Realiza el cambio de estado de un registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Obtiene la información para el reporte gráfico por represa .
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReporteRepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ReporteRepresaBean> generarReportePorRepresa (String periodo) throws Exception;
	
	/**
	 * Obtiene la información para el reporte gráfico por maniobras.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReporteRepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ReporteRepresaBean> generarReportePorRepresaManiobra(String periodo) throws Exception;
	
	/**
	 * Obtiene la cantidad de registros de represas activos para la represa y fecha consultados.
	 *
	 * @param strFecha Es la fecha de la que se desea consultar (dd/mm/yyyy)
	 * @param intCodRepresa Es el codigo de la represa de la que se desea consultar
	 * @return Objeto de tipo Integer que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Integer obtenerCatidadRepresasActivaPorFecha(String strFecha, Integer intCodRepresa) throws Exception;
	
	/**
	 * Obtiene la suma de promedios para un día y periodo del caudal para aquellos ríos que forman parte del caudal del rímac.
	 *
	 * @param strPeriodo Es el periodo para el que se requiere el aforo
	 * @param strDia Es el día para el que se requiere el aforo
	 * @return Objeto de tipo BigDecimal que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	BigDecimal obtenerAforoRioRimac(String strPeriodo, String strDia) throws Exception;
	
	/**
	 * Obtiene la información para el reporte resumen de represas por día.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReportePivotBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ReportePivotBean> generarReporteResumenRepresa(String periodo) throws Exception;
	
	/**
	 * Obtiene la información para el correo de envío a gerencia.
	 *
	 * @param fecha Es la fecha para la que se requiere generar el reporte
	 * @return lista de objetos de tipo DatosRepresasGerenciaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<DatosRepresasGerenciaBean> obtenerDatosReporteGerencia(String fecha) throws Exception;
}
