/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.sedapal.scr.core.beans.DetalleCatalogoBean;
import pe.com.sedapal.scr.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICatalogoDao.
 */
public interface ICatalogoDao {

	/**
	 * Método que permite obtener el detalle de un catalogo por su abreviatura.
	 *
	 * @param strAbreviatura the str abreviatura
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CargaBean que contiene los datos de la carga
	 */
	List<DetalleCatalogoBean> obtenerDetalleCatalogo(String strAbreviatura) throws Exception;
	
	/**
	 * Obtener catalogo.
	 *
	 * @param idTabla the id tabla
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla);
	
	/**
	 * Obtener catalogo todos ninguno.
	 *
	 * @param idTabla the id tabla
	 * @param flag the flag
	 * @return the list
	 * @throws Exception the exception
	 */
	List<SelectItemBean> obtenerCatalogoTodosNinguno(Integer idTabla, Integer flag)  throws Exception;
	
	/**
	 * Obtener catalogo seleccionar.
	 *
	 * @param idTabla the id tabla
	 * @param flag the flag
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<SelectItemBean> obtenerCatalogoSeleccionar(Integer idTabla, Integer flag) throws Exception;
	
	/**
	 * Obtener catalogo.
	 *
	 * @param idTabla the id tabla
	 * @param columnToOrder the column to order
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla,String columnToOrder);
}
