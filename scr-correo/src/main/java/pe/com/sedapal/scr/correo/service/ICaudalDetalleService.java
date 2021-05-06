/**
 * 
 * Resumen.
 * Objeto 				: ICaudalDetalleService
 * Descripción 			: Clase interfaz (de servicio) para el detalle del caudal
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

import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;

public interface ICaudalDetalleService {
	
	/**
	 * Método que permite registrar caudal detalle
	 * @Return Objeto de tipo Integer que contiene el código caudal detalle 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public Integer registrarCaudalDetalle(CaudalDetalle caudalDetalle) throws Exception;
	
}
