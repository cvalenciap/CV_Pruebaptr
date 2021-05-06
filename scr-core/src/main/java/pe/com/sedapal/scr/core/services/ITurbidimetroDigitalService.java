/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITurbidimetroDigitalService.
 */
public interface ITurbidimetroDigitalService {
	
	/**
	 * Obtener datos equipos.
	 *
	 * @param verificacionEquipoBean the verificacion equipo bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de Equipo
	 * @param verificacionEquipoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosEquipos(TurbidimetroDigitalBean verificacionEquipoBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener verificacion equipo.
	 *
	 * @param id the id
	 * @return the turbidimetro digital bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene Información de equipo por identificador
	 * @param nid Identificador del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	TurbidimetroDigitalBean obtenerVerificacionEquipo(Integer id) throws Exception;
	
	
	/**
	 * Grabar verificacion equipo.
	 *
	 * @param verificacionEquipoBean the verificacion equipo bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de Verificación de Equipos
	 * @param verificacionEquipoBean objeto del tipo VerificacionEquipoBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception;
	
	
	/**
	 * Actualizar verificacion equipo.
	 *
	 * @param verificacionEquipoBean the verificacion equipo bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la tabla de Verificación de Equipos
	 * @param verificacionEquipoBean objeto del tipo VerificacionEquipoBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception;
	
	/**
	 * Inactivar verificacion equipo.
	 *
	 * @param verificacionEquipoBean the verificacion equipo bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla de Verificación de Equipos
	 * @param verificacionEquipoBean objeto del tipo VerificacionEquipoBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void inactivarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception;
	
	/**
	 * Obtener datos equipos verificacion.
	 *
	 * @param turbidimetroDigitalVerificacionBean the turbidimetro digital verificacion bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de Equipo
	 * @param turbidimetroDigitalVerificacionBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosEquiposVerificacion(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Grabar verificacion equipo form.
	 *
	 * @param turbidimetroDigitalVerificacionBean the turbidimetro digital verificacion bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de Verificación de Equipos del formulario
	 * @param verificacionEquipoBean objeto del tipo turbidimetroDigitalVerificacionBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	int grabarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean)throws Exception;
	
	/**
	 * Inactivar verificacion equipo form.
	 *
	 * @param turbidimetroDigitalVerificacionBean the turbidimetro digital verificacion bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla de Verificación de Equipos dek formulario hijo
	 * @param turbidimetroDigitalVerificacionBean objeto del tipo TurbidimetroDigitalVerificacionBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public void inactivarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean) throws Exception;

}
