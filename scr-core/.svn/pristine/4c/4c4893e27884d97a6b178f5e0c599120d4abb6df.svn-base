/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantRepresaDao.
 */
@Repository
public interface IMantRepresaDao {

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
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void grabarRepresa(RepresaBean represaBean) throws Exception;
	
	
	/**
	 * Realiza la modificación de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarRepresa(RepresaBean represaBean) throws Exception;
	
	/**
	 *  
	 * Realiza el cambio de estado de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarRepresa(RepresaBean represaBean) throws Exception;
	
	/**
	 *  
	 * Obtiene las represas registrados en la plataforma según su estado.
	 *
	 * @param estado de las represas que se desean listar
	 * @return lista de objetos de tipo RepresaBean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<RepresaBean> obtenerRepresas(String estado) throws Exception;
	
	/**
	 *  
	 * Valida si existen represas con una determinada condición.
	 *
	 * @param represaBean objeto del tipo ClimaBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public ValidaAbreviaturaBean validarAbrevMantRepresa(RepresaBean represaBean) throws Exception;
	
}
