/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */

package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantClimaDao.
 */
@Repository
public interface IMantClimaDao {
	
	/**
	 * Método que permite obtener el listado de Clima.
	 *
	 * @param climaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result obtenerMantClima(ClimaBean climaBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtiene el clima por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo climaBean que contiene el registro
	 */
	ClimaBean obtenerClima(Integer nid) throws Exception;

	/**
	 *  
	 * Realiza el registro de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void grabarClima(ClimaBean climaBean) throws Exception;
	
	/**
	 * Realiza la modificación de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarClima(ClimaBean climaBean) throws Exception;
	
	/**
	 *  
	 * Realiza el cambio de estado de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarClima(ClimaBean climaBean) throws Exception;
	
	/**
	 *  
	 * Obtiene los climas registrados en la plataforma según su estado.
	 *
	 * @param estado de los climas que se desean listar
	 * @return lista de objetos de tipo ClimaBean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ClimaBean> obtenerClimas(String estado) throws Exception;
	
	/**
	 *  
	 * Valida si existen climas con una determinada condición.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public ValidaAbreviaturaBean validarAbrevMantClima(ClimaBean climaBean) throws Exception;
}
