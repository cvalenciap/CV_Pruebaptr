/**
 * 
 * Resumen.
 * Objeto 				: IConfigCorreoService
 * Descripción 			: Clase interfaz (de servicio) para la configuración de correo
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

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;

public interface IConfigCorreoService {
	
	/**
	 * Método que permite obtener datos de configuración de correo para un periodo y un estado
	 * @Return Objeto de tipo ConfiguracionCorreo que contiene los datos de configuración de correo 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public ConfiguracionCorreo obtenerConfigCorreo(final String strTipoOperacion, final String strEstado) throws GmdException;
	
}
