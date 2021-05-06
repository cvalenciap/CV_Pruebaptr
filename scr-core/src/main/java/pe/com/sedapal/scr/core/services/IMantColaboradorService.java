/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;


// TODO: Auto-generated Javadoc
/**
 * The Interface IMantColaboradorService.
 */
public interface IMantColaboradorService {
	
	/**
	 * Método que permite obtener el listado de Colaboradores.
	 *
	 * @param colaboradorBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	Result obtenerMantColaborador(ColaboradorBean colaboradorBean, Paginacion paginacion) throws Exception;
	
	/**
	 *  
	 * Obtiene la represa por identificador.
	 *
	 * @param id the id
	 * @return the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ColaboradorBean que contiene el registro
	 */
	ColaboradorBean obtenerColaborador(Integer id) throws Exception;
	
	/**
	 * Realiza el registro de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @return boolean que indica el éxito de la operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean grabarColaborador(ColaboradorBean colaboradorBean) throws Exception;
	

	/**
	 * Realiza la modificación de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return boolean que indica el éxito de la operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	boolean actualizarColaborador(ColaboradorBean colaboradorBean, boolean abreviaturaIsUpdate) throws Exception;
	
	/**
	 * Realiza el cambio de estado de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarColaborador(ColaboradorBean colaboradorBean) throws Exception;
	
	/**
	 * Obtiene la lista de todos los colaboradores activos registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerColaboradoresActivos() throws Exception;
	
	/**
	 * Obtiene la lista de todos los colaboradores registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<SelectItemBean> obtenerColaboradoresTodos() throws Exception;
	
	/**
	 * Obtiene una lista de trabajadores de sedepal.
	 *
	 * @param trabajadorBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	Result obtenerListaDeTrabajadores(TrabajadorBean trabajadorBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtiene a un trabajador de sedapal buscado por número de ficha.
	 *
	 * @param intNumFicha es el número de ficha para la búsqueda
	 * @return Objeto de tipo TrabajadorBean que contiene los datos del trabajador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	TrabajadorBean obtenerTrabajador(Integer intNumFicha) throws Exception;
}
