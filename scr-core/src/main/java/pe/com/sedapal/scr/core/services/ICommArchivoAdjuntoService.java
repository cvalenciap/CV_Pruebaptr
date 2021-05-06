/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICommArchivoAdjuntoService.
 */
public interface ICommArchivoAdjuntoService {
	
	/**
	 * Guardar.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @return the long
	 * @throws Exception the exception
	 */
	public Long guardar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception;

	/**
	 * Listar.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	public Result listar(CommArchivoAdjuntoBean archivoAdjuntoBean, Paginacion paginacion) throws Exception;

	/**
	 * Eliminar.
	 *
	 * @param codigo the codigo
	 * @throws Exception the exception
	 */
	public void eliminar(Long codigo) throws Exception;

	/**
	 * Actualizar.
	 *
	 * @param archivoAdjuntoBean the archivo adjunto bean
	 * @throws Exception the exception
	 */
	public void actualizar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception;

	/**
	 * Consultar.
	 *
	 * @param codigo the codigo
	 * @return the comm archivo adjunto bean
	 * @throws Exception the exception
	 */
	public CommArchivoAdjuntoBean consultar(Long codigo) throws Exception;
	
}
