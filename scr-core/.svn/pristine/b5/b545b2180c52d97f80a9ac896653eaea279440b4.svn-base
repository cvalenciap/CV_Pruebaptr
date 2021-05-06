/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */

package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMantColaboradorDao.
 */
@Repository
public interface IMantColaboradorDao {

	/**
	 * Método que permite obtener el listado de Colaborador.
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
	 * Obtiene el colaborador por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo colaboradorBean que contiene el registro
	 */
	ColaboradorBean obtenerColaborador(Integer nid) throws Exception;

	/**
	 *  
	 * Realiza el registro de colaborador.
	 *
	 * @param colaboradorBean objeto del tipo ColaboradorBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void grabarColaborador(ColaboradorBean colaboradorBean) throws Exception;
	
	/**
	 *  
	 * Realiza la modificación de colaborador.
	 *
	 * @param colaboradorBean objeto del tipo ColaboradorBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void actualizarColaborador(ColaboradorBean colaboradorBean) throws Exception;
	
	/**
	 * Realiza el cambio de estado de colaborador.
	 *
	 * @param colaboradorBean objeto del tipo ColaboradorBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void inactivarColaborador(ColaboradorBean colaboradorBean) throws Exception;
	
	/**
	 * Obtiene la lista de colaboradores según su estado.
	 *
	 * @param estado Contiene el estado de los datos que se desean listar
	 * @return Lista de objetos de tipo ColaboradorBean que contiene los datos de colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	List<ColaboradorBean> obtenerColaboradores(String estado) throws Exception;
	
	/**
	 *  
	 * Valida si existen colaboradores activos con misma abreviatura.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @return the valida abreviatura bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 */
	ValidaAbreviaturaBean validarAbrevMantColaborador(ColaboradorBean colaboradorBean) throws Exception ;
	
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
	 *  
	 * Obtiene el trabajador por su código de ficha.
	 *
	 * @param intNumFicha Identificador del registro
	 * @return the trabajador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo TrabajadorBean que contiene el registro
	 */
	TrabajadorBean obtenerTrabajador(Integer intNumFicha) throws Exception;
}
