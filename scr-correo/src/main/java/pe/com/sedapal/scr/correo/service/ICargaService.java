/**
 * 
 * Resumen.
 * Objeto 				: ICargaService
 * Descripción 			: Clase interfaz (de servicio) para la carga
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
import pe.com.sedapal.scr.correo.core.beans.Carga;

public interface ICargaService {
	
	/**
	 * Método que permite registrar carga
	 * @Return Objeto de tipo Integer que contiene código de carga 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public Integer registrarCarga(Carga carga) throws GmdException;
	
}
