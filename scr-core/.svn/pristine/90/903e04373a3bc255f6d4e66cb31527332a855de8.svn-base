/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.RioBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantRioService.
 */
public interface IMantRioService {
	
	/**
	 * Método que permite obtener el listado de Rios.
	 *
	 * @param rioBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Result obtenerMantRio(RioBean rioBean, Paginacion paginacion) throws Exception;

	/**
	 *  
	 * Obtiene el río por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the rio bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	RioBean obtenerRio(Integer nid) throws Exception;
	
	/**
	 *  
	 * Realiza el registro de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @return Mapa de resultados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Map<String, Object> grabarRio(RioBean rioBean) throws Exception;
	
	/**
	 *  
	 * Realiza la modificación de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @param abreviaturaIsUpdate flag que nos indica si la abreviatura ha sido modificada
	 * @param nomlarIsUpdate flag que nos indica si el nombre largo ha sido modificado
	 * @return Mapa de resultados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Map<String, Object> actualizarRio(RioBean rioBean, boolean abreviaturaIsUpdate, boolean nomlarIsUpdate) throws Exception;

	/**
	 *  
	 * Realiza el cambio de estado de río.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRio(RioBean rioBean) throws Exception;
	
	/**
	 * Obtiene la lista de todos los ríos activos registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del rio
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerRiosActivos() throws Exception;

	/**
	 * Obtiene la lista de todos los ríos registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del rio
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerRiosTodos() throws Exception;
}
