/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

	import pe.com.sedapal.common.core.beans.Paginacion;
	import pe.com.sedapal.common.core.beans.Result;
	import pe.com.sedapal.scr.core.beans.FormulaMuestraBean;

	// TODO: Auto-generated Javadoc
/**
	 * The Interface IFormulaMuestraDao.
	 */
	public interface IFormulaMuestraDao {

	/**
	 * Obtener datos formulas muestra.
	 *
	 * @param formulaMuestraBean the formula muestra bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de la tabla de Fórmlas de muestra
	 * @param formulaMuestraBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosFormulasMuestra(FormulaMuestraBean formulaMuestraBean, Paginacion paginacion) throws Exception;

	/**
	 * Obtener formula muestra.
	 *
	 * @param id the id
	 * @return the formula muestra bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene Fórmlas de muestra por identificador
	 * @param nid Identificador del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	FormulaMuestraBean obtenerFormulaMuestra(Integer id) throws Exception;
	
	
	/**
	 * Grabar formula muestra.
	 *
	 * @param formulaMuestraBean the formula muestra bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla Fórmlas de muestra
	 * @param formulaMuestraBean objeto del tipo formulaMuestraBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception;
	
	
	/**
	 * Actualizar formula muestra.
	 *
	 * @param formulaMuestraBean the formula muestra bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la tabla Fórmlas de muestra
	 * @param formulaMuestraBean objeto del tipo formulaMuestraBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception;
	
	/**
	 * Inactivar formula muestra.
	 *
	 * @param formulaMuestraBean the formula muestra bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla Formula de Muestra
	 * @param formulaMuestraBean objeto del tipo formulaMuestraBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void inactivarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception;
	
}
