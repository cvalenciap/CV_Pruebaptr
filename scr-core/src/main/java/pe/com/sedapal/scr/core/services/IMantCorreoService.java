/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.MantCorreoBean;
import pe.com.sedapal.scr.core.beans.MantCorreoEditBean;
import pe.com.sedapal.scr.core.beans.ValidaUnicidadCorreoBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantCorreoService.
 */
public interface IMantCorreoService {

	
	/**
	 * Método que permite obtener el listado de Correos.
	 *
	 * @param mantCorreoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Result obtenerMantCorreo(MantCorreoBean mantCorreoBean, Paginacion paginacion) throws Exception;
	
	
	/**
	 *  
	 * Realiza el cambio de estado de correo.
	 *
	 * @param mantCorreoBean objeto del tipo MantCorreoBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarCorreo(MantCorreoBean mantCorreoBean) throws Exception;
	
	/**
	 *  
	 * Realiza el registro de correo.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void grabarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception;
	
	/**
	 *  
	 * Realiza la modificación de correo.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception;
	
	/**
	 *  
	 * Obtiene la configuración de correo por identificador.
	 *
	 * @param idMantCorreo Identificador del registro
	 * @return the mant correo
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo MantCorreoEditBean que contiene el registro
	 */
	MantCorreoEditBean getMantCorreo(Integer idMantCorreo) throws Exception;
	
	/**
	 *  
	 * Valida si existen configuraciones de correo activas para cada tipo.
	 *
	 * @return the validation data
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ValidaUnicidadCorreoBean que contiene la información de validación
	 */
	ValidaUnicidadCorreoBean getValidationData() throws Exception;
}
