/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CalculoRepresasBean;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.FilaReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.ReporteWrapperBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.beans.Result;
import pe.com.sedapal.scr.core.exception.ValidationFileException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRepresasService.
 */
public interface IRepresasService {
	
	/**
	 * Método que permite obtener el listado de Represas.
	 *
	 * @param represasBean the represas bean
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result buscarRepresas(RepresasBean represasBean, Paginacion paginacion) throws Exception;
	
	/**
	 *  
	 * Obtiene el registro de represas por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo RepresasBean que contiene el registro
	 */
	RepresasBean obtenerRepresas(Integer nid) throws Exception;
	
	/**
	 * Realiza el registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @return objeto del tipo RepresasBean que contiene ademas los valores autogeneraods
	 * @throws Exception Excepción que puede ser lanzada
	 */
	RepresasBean registrarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Realiza la modificación de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Realiza la modificación de factor de descarga represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
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
	 * Realiza el cambio de estado de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRepresas(RepresasBean represasBean) throws Exception;
	
	/**
	 * Obtiene la lista de los colaboradores registrados en la plataforma según su estado.
	 *
	 * @param estado representa el estado del registro de represas
	 * @return Lista de objetos de tipo ColaboradorBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ColaboradorBean> obtenerColaboradores(String estado) throws Exception;
	
	/**
	 * Obtiene la lista de los climas registrados en la plataforma según su estado.
	 *
	 * @param estado representa el estado del registro de climas
	 * @return Lista de objetos de tipo ClimaBean que contiene los datos del clima
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ClimaBean> obtenerClimas(String estado) throws Exception;
	
	/**
	 * Obtiene la información necesaria para generar el reporte gráfico (tanto de volumen como de precipitación).
	 *
	 * @param periodo para el cual se desea generar el reporte gráfico
	 * @param represas es el listado de códigos de represas para los cuales se desea generar el reporte gráfico
	 * @param tiporep el es el tipo de reporte que se desea generar (volumen o precipitación)
	 * @return objeto del tipo ReporteWrapperBean que contiene los datos y parámetros del reporte gráfico
	 * @throws Exception Excepción que puede ser lanzada
	 */
	ReporteWrapperBean generarReporteRepresaGrafico(String periodo, String[] represas, String tiporep) throws Exception;
	
	/**
	 * Obtiene la información necesaria para generar el reporte gráfico de caudal por maniobra.
	 *
	 * @param periodo para el cual se desea generar el reporte gráfico
	 * @param maniobras el es el listado de códigos de maniobra para los cuales se generará el reporte
	 * @return objeto del tipo ReporteWrapperBean que contiene los datos y parámetros del reporte gráfico
	 * @throws Exception Excepción que puede ser lanzada
	 */
	ReporteWrapperBean generarReporteRepManiobraGrafico(String periodo, String[] maniobras) throws Exception;
	
	/**
	 * Valida la extensión de un archivo.
	 *
	 * @param strFileName es el nombre completo del archivo
	 * @return objeto de tipo String que contiene la información de la validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	String validaExtensionFile(String strFileName) throws Exception;
	
	/**
	 * Valida la información contenida en el archivo de importación de represas.
	 *
	 * @param datosExcel es una estructura de datos que representa la información del excel como una matriz de strings
	 * @return estructura de datos que enmascara dos matrices de datos, una con la información correcta a importar y la otra con los
	 * registros que no serán importados porque no pasó todas las validaciones
	 * @throws ValidationFileException Excepción customizada que será lanzada según las validaciones
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Map<String,List<List<String>>> validaInformacionImportar(List<List<String>> datosExcel) throws ValidationFileException, Exception;
	
	/**
	 * Realiza la inserción de un listado de represas.
	 *
	 * @param represas es la estructura matriz con la infromación de las represas a insertar
	 * @param auditoria es el objeto que contiene los datos de auditoría
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void registrarRepresasImport(List<List<String>> represas, BaseBean auditoria) throws Exception;
	
	/**
	 * Obtiene la cantidad de registros de represas activos para la represa y fecha consultados.
	 *
	 * @param strFecha Es la fecha de la que se desea consultar (dd/mm/yyyy)
	 * @param intCodRepresa Es el codigo de la represa de la que se desea consultar
	 * @return Objeto de tipo Integer que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	String obtenerCatidadRepresasActivaPorFecha(String strFecha, Integer intCodRepresa) throws Exception;
	
	/**
	 * Obtiene el valor del aforo del río rímac	.
	 *
	 * @param strFechaRegistro Es la fecha para la cual se requiere el aforo
	 * @return Objeto de tipo String que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	String obtenerAforoRioRimac(String strFechaRegistro) throws Exception;
	
	/**
	 * Obtiene el valor de la fórmula de volumen trasvasado.
	 *
	 * @return Objeto de tipo String que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	String obtenerFormulaVolumenTrasvasado() throws Exception;
	
	/**
	 * Obtiene la información para el reporte resumen de represas por día.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReportePivotBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<FilaReporteRepresaBean> generarReporteResumenRepresa(String periodo) throws Exception;
	
}
