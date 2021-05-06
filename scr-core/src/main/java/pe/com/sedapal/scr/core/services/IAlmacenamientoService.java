/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.scr.core.beans.BaseBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlmacenamientoService.
 */
public interface IAlmacenamientoService {

	/**
	 * Realiza la modificación de almacenamiento.
	 *
	 * @param strFecha Es la fecha para la cual se realiza la modificación
	 * @param strFormulaEmbalse Formula a evaluar
	 * @param strFormulaManDescarga Formula a evaluar
	 * @param strForumlaUno the str forumla uno
	 * @param strForumlaDos the str forumla dos
	 * @param auditoria Es el objeto con los datos de auditoria
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarAlmacenamiento(String strFecha, String strFormulaEmbalse, 
			String strFormulaManDescarga, String strForumlaUno, String strForumlaDos,
			BaseBean auditoria) throws Exception;
}
