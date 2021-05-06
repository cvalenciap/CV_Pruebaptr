/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.TablaPoissonBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITablaPoissonService.
 */
public interface ITablaPoissonService {
	
	/**
	 * Obtener datos poisson.
	 *
	 * @param tablaPoissonBean the tabla poisson bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de la tabla de Poisson
	 * @param tablaPoissonBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosPoisson(TablaPoissonBean tablaPoissonBean, Paginacion paginacion) throws Exception;

	/**
	 * Obtener tabla poisson.
	 *
	 * @param id the id
	 * @return the tabla poisson bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene tabla poisson por identificador
	 * @param nid Identificador del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	TablaPoissonBean obtenerTablaPoisson(Integer id) throws Exception;
	
	
	/**
	 * Grabar tabla poisson.
	 *
	 * @param tablaPoissonBean the tabla poisson bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla poisson
	 * @param tablaPoissonBean objeto del tipo TablaPoissonBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception;
	
	
	/**
	 * Actualizar tabla poisson.
	 *
	 * @param tablaPoissonBean the tabla poisson bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la tabla poisson
	 * @param tablaPoissonBean objeto del tipo TablaPoissonBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception;
	
	/**
	 * Inactivar tabla poisson.
	 *
	 * @param tablaPoissonBean the tabla poisson bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla poisson
	 * @param tablaPoissonBean objeto del tipo TablaPoissonBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void inactivarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception;
}
