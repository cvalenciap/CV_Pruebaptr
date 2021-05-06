/**
 * 
 * Resumen.
 * Objeto 				: IRioService
 * Descripción 			: Clase interfaz (de servicio) para el río
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.service;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RioBean;

public interface IRioService {
	
	/**
	 * Método que permite obtener ríos para un estado
	 * @Return Objeto de tipo RioBean que contiene los datos ríos 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public List<RioBean> listarRiosPorEstado(final String strEstado) throws GmdException;
	
}
