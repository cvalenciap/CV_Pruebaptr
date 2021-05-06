
/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.RepresaBean;


// TODO: Auto-generated Javadoc
/**
 * The Interface IMantRepresaService.
 */
public interface IMantRepresaService {
	
	/**
	 * Método que permite obtener el listado de Represa.
	 *
	 * @param represaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result obtenerMantRepresa(RepresaBean represaBean, Paginacion paginacion) throws Exception;
	
	/**
	 *  
	 * Obtiene la represa por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represa bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo represaBean que contiene el registro
	 */
	RepresaBean obtenerRepresa(Integer nid) throws Exception;
	
	/**
	 *  
	 * Realiza el registro de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean grabarRepresa(RepresaBean represaBean) throws Exception;
	
	
	/**
	 * Realiza la modificación de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean actualizarRepresa(RepresaBean represaBean, boolean abreviaturaIsUpdate) throws Exception ;
	
	/**
	 *  
	 * Realiza el cambío de estado de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRepresa(RepresaBean represaBean) throws Exception;
	
	/**
	 * Obtiene la lista de represas activas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de las represas
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerRepresasActivas() throws Exception;
	
	/**
	 * Obtiene la lista de todas las represas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de la represa
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerRepresasTodas() throws Exception;
}
