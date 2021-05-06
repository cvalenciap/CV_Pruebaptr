/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ManiobraBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IManiobraService.
 */
public interface IManiobraService {
	
	/**
	 * Método que permite obtener el listado de Maniobras.
	 *
	 * @param maniobraBean Contiene el bean que representa la búsqueda
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
	 * Obtiene la lista de los tipos de maniobra.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de la maniobra
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerTiposManiobra() throws Exception;

	/**
	 *  
	 * Obtiene el registro activo de mayor hora por identificador de represas.
	 *
	 * @param codRepresas Identificador del registro de represas
	 * @return the maniobra bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	ManiobraBean obtenerUltimaManiobraActiva(Integer codRepresas) throws Exception;
	
	/**
	 *  
	 * Obtiene el listado de maniobras activas (ordenadas por hora) de un registro de represas.
	 *
	 * @param codRepresas Identificador del registro de represas
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ManiobraBean> obtenerManiobrasActivas(Integer codRepresas) throws Exception;
}
