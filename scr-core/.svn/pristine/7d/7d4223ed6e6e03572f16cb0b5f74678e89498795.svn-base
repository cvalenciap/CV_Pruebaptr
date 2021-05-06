/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConductimetroDigitalService.
 */
public interface IConductimetroDigitalService {

	/**
	 * Obtener datos equipos.
	 *
	 * @param conductimetroDigitalBean the conductimetro digital bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de equipos
	 * @param conductimetroDigitalBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosEquipos(ConductimetroDigitalBean conductimetroDigitalBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener conductimetro digital.
	 *
	 * @param id the id
	 * @return the conductimetro digital bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene Información de equipo por identificador
	 * @param nid Identificador del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	ConductimetroDigitalBean obtenerConductimetroDigital(Integer id) throws Exception;
	
	
	/**
	 * Grabar conductimetro digital.
	 *
	 * @param conductimetroDigitalBean the conductimetro digital bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de Conductimetro
	 * @param conductimetroDigitalBean objeto del tipo ConductimetroDigitalBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception;
	
	
	/**
	 * Actualizar conductimetro digital.
	 *
	 * @param conductimetroDigitalBean the conductimetro digital bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la tabla de Conductimetro
	 * @param conductimetroDigitalBean objeto del tipo ConductimetroDigitalBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception;
	
	/**
	 * Inactivar conductimetro digital.
	 *
	 * @param conductimetroDigitalBean the conductimetro digital bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla de Conductimetro
	 * @param conductimetroDigitalBean objeto del tipo ConductimetroDigitalBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void inactivarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception;
	
	/**
	 * Obtener datos equipos detalle.
	 *
	 * @param conductimetroDigitalDetalleBean the conductimetro digital detalle bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el detalle de la tabla  Conductimetro
	 * @param conductimetroDigitalDetalleBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public Result obtenerDatosEquiposDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Grabar conductimetro digital detalle.
	 *
	 * @param conductimetroDigitalDetalleBean the conductimetro digital detalle bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de detalle de Conductimetro
	 * @param conductimetroDigitalDetalleBean objeto del tipo ConductimetroDigitalDetalleBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int grabarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception;
	
	/**
	 * Inactivar conductimetro digital detalle.
	 *
	 * @param conductimetroDigitalDetalleBean the conductimetro digital detalle bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla detalle de Conductimetro
	 * @param conductimetroDigitalDetalleBean objeto del tipo ConductimetroDigitalDetalleBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public void inactivarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception;	
	
}
