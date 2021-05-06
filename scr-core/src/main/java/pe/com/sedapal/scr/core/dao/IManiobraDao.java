/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ManiobraBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IManiobraDao.
 */
public interface IManiobraDao {
	
	/**
	 * Método que permite obtener el listado de maniobras.
	 *
	 * @param maniobraBean the maniobra bean
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result buscarManiobras(ManiobraBean maniobraBean, Paginacion paginacion) throws Exception;	
	
	/**
	 * Realiza el registro de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void registrarManiobra(ManiobraBean maniobraBean) throws Exception;
	
	/**
	 * Realiza la modificación de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarManiobra(ManiobraBean maniobraBean) throws Exception;
	
	/**
	 * Realiza el cambio de estado de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarManiobra(ManiobraBean maniobraBean) throws Exception;

	/**
	 *  
	 * Obtiene la lista de maniobras activas por represas (ordenadas ascendemente por hora).
	 *
	 * @param codRepresas Identificador del registro de represas
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ManiobraBean> obtenerManiobrasActivas(Integer codRepresas) throws Exception;
	
}
