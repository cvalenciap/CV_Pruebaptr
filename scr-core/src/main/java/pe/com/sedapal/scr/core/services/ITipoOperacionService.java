/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.common.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITipoOperacionService.
 */
public interface ITipoOperacionService {

	/**
	 * Obtiene la lista de tipos de operación para la configuración de correos.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de los tipos de operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerAllTipoOperacion() throws Exception;
}
