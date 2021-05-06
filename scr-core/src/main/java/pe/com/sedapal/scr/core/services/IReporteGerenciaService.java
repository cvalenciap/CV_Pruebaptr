/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

// TODO: Auto-generated Javadoc
/**
 * The Interface IReporteGerenciaService.
 */
public interface IReporteGerenciaService {

	/**
	 * Método que permite generar el cuadro resumen del correo a gerencia.
	 *
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo String con el HTML de correo
	 */
	String generarCuadroResumenReporte() throws Exception;
}
