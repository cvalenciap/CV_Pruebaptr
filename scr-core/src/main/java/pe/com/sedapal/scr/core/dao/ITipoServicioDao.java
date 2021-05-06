/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.Parametro;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITipoServicioDao.
 */
@Repository
public interface ITipoServicioDao {

	/**
	 * Método que permite obtener los tipos de servicio para la paginacion.
	 *
	 * @param parametro Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Manejador de excepción que pueda ocurrir
	 */
	Result obtenerTipoDeServicio(Parametro parametro, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener tipo de servicio.
	 *
	 * @param nid the nid
	 * @return the parametro
	 * @throws Exception the exception
	 */
	Parametro obtenerTipoDeServicio(Integer nid) throws Exception;
	
	/**
	 * Grabar tipo de servicio.
	 *
	 * @param parametro the parametro
	 * @throws Exception the exception
	 */
	void grabarTipoDeServicio(Parametro parametro) throws Exception;
	
	/**
	 * Actualizar punto distribucion.
	 *
	 * @param parametroEditBean the parametro edit bean
	 * @throws Exception the exception
	 */
	void actualizarPuntoDistribucion(Parametro parametroEditBean) throws Exception;
	
	/**
	 * Inactivar tipo de servicio.
	 *
	 * @param parametro the parametro
	 * @throws Exception the exception
	 */
	void inactivarTipoDeServicio(Parametro parametro) throws Exception;
	
}
