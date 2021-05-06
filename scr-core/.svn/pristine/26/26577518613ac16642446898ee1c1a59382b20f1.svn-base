/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.CaudalDetalleBean;
import pe.com.sedapal.scr.core.beans.CaudalSearchBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.core.beans.ReporteWrapperBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICaudalService.
 */
public interface ICaudalService {

	/**
	 * Método que permite obtener el listado de caudales de río.
	 *
	 * @param caudalSearchBean Contiene los filtros de búsqueda de caudal
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result buscarCaudales(CaudalSearchBean caudalSearchBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Método que permite obtener el detalle de un caudal de río.
	 *
	 * @param caudalId es el identificador de caudal
	 * @param rioId es el identificador de río
	 * @param periodo es el periodo del cual se requiere la información
	 * @return the caudal detalle bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CaudalDetalleBean que contiene los datos del detalle
	 */
	CaudalDetalleBean obtenerDetalle(Integer caudalId, Integer rioId, String periodo) throws Exception;
	
	/**
	 * Método que permite preparar las matrices de caudales comparadas al frontend (para mostrar diferencias).
	 *
	 * @param datosExcel contiene la información del archivo de carga manuaal de caudales
	 * @param datosDb contiene la información persistida de caudales
	 * @param periodo es el periodo para el cual se está comparando
	 * @return the map
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto mapa con la información de cada matriz (excel y bd)
	 */
	Map<String, List<List<CeldaMatrizBean>>> prepararMatricesComparadas(List<List<String>> datosExcel, List<List<CeldaMatrizBean>> datosDb, String periodo) throws Exception;
	
	/**
	 * Método que permite castear la data de excel en objeto que permite agregar información adicional.
	 *
	 * @param datosExcel contiene la información del archivo de carga manuaal de caudales
	 * @param periodo es el periodo para el cual se requiere información
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto mapa con la información casteada del excel
	 */
	List<List<CeldaMatrizBean>> prepararDataExcel(List<List<String>> datosExcel, String periodo) throws Exception;
	
	/**
	 * Método que permite obtener la información del reporte pdf.
	 *
	 * @param periodo es el periodo para el cual se requiere información
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos ReporteResumenBean que contiene la información del reporte pdf
	 */
	List<ReporteResumenBean> generarReporteResumenCaudal(String periodo) throws Exception;
	
	/**
	 * Método que permite obtener la información del reporte gráfico de caudales.
	 *
	 * @param periodo es el periodo para el cual se requiere información
	 * @param rios que contiene la lista de rios para los cuales se mostrará información
	 * @return the reporte wrapper bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos ReporteResumenBean que contiene la información del reporte gráfico
	 */
	ReporteWrapperBean generarReporteResumenCaudalGrafico(String periodo, String[] rios) throws Exception;
}
