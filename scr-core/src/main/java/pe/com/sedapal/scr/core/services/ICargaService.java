/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.CargaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICargaService.
 */
public interface ICargaService {

	/**
	 * Método que permite obtener la última carga automática de caudales.
	 *
	 * @return the carga bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CargaBean que contiene los datos de la carga
	 */
	CargaBean obtenerUltimaCarga() throws Exception;
	
	/**
	 * Método que permite obtener el listado de cargas.
	 *
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result buscarCargas(Paginacion paginacion) throws Exception;
}
