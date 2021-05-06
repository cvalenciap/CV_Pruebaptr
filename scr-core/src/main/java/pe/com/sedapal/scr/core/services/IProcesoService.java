/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.scr.core.beans.DatosEnvioCorreoBean;
import pe.com.sedapal.scr.core.beans.DatosExtraccionCorreoBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProcesoService.
 */
public interface IProcesoService {
	
	/**
	 * Método que permite obtener los datos de configuración del correo a gerencia.
	 *
	 * @return the datos envio correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosEnvioCorreoBean que contiene los datos
	 */
	DatosEnvioCorreoBean obtenerCorreoGerenciaEnviar() throws Exception;
	
	/**
	 * Método que permite obtener los datos de configuración del correo resumen.
	 *
	 * @return the datos envio correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosEnvioCorreoBean que contiene los datos
	 */
	DatosEnvioCorreoBean obtenerCorreoResumenEnviar() throws Exception;

	/**
	 * Método que permite obtener los datos de configuración del proceso de extracción de correo.
	 *
	 * @return the datos extraccion correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosExtraccionCorreoBean que contiene los datos
	 */
	DatosExtraccionCorreoBean obtenerCorreoExtraccion() throws Exception;
	
}
