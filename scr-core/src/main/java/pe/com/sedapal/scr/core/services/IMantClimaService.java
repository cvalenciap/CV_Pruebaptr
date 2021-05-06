/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;



import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ClimaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantClimaService.
 */
public interface IMantClimaService {
	
	/**
	 * Método que permite obtener el listado de Clima.
	 *
	 * @param climaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Result obtenerMantClima(ClimaBean climaBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtiene el clima por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	ClimaBean obtenerClima(Integer nid) throws Exception;
	
	
	/**
	 * Realiza el registro de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean grabarClima(ClimaBean climaBean) throws Exception;
	
	
	/**
	 *  
	 * Realiza la modificación de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean actualizarClima(ClimaBean climaBean, boolean abreviaturaIsUpdate) throws Exception;
	
	/**
	 * Realiza el cambio de estado de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarClima(ClimaBean climaBean) throws Exception;
	
	/**
	 * Obtiene la lista de climas activos.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de los climas
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerClimasActivos() throws Exception;
	
	/**
	 * Obtiene la lista de todos los climas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del clima
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerClimasTodos() throws Exception;
	
}
