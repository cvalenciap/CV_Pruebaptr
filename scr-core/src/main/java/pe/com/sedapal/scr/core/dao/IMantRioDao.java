/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantRioDao.
 */
@Repository
public interface IMantRioDao {
	
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
	 * Realiza el registro de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void grabarRio(RioBean rioBean) throws Exception;
	
	/**
	 * Realiza la modificación de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarRio(RioBean rioBean) throws Exception;
	
	/**
	 * Realiza el cambio de estado de río.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRio(RioBean rioBean) throws Exception;
	
	/**
	 *  
	 * Obtiene los ríos registrados en la plataforma según su estado.
	 *
	 * @param estado de los ríos que se desean listar
	 * @return lista de objetos de tipo RioBean que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<RioBean> obtenerRios(String estado) throws Exception;
	
	/**
	 *  
	 * Valida si existen ríos con una determinada condición.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public ValidaAbreviaturaBean validarAbrevMantRio(RioBean rioBean) throws Exception;
	
}
