/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao;
import pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService;

// TODO: Auto-generated Javadoc
/**
 * The Class CommArchivoAdjuntoServiceImpl.
 */
@Service
public class CommArchivoAdjuntoServiceImpl implements ICommArchivoAdjuntoService {

	/** The archivo adjunto dao. */
	@Autowired
	private ICommArchivoAdjuntoDao archivoAdjuntoDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService#guardar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	@Transactional
	public Long guardar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		return archivoAdjuntoDao.guardar(archivoAdjuntoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService#listar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result listar(CommArchivoAdjuntoBean archivoAdjuntoBean, Paginacion paginacion) throws Exception {
		Result result = archivoAdjuntoDao.listar(archivoAdjuntoBean, paginacion);
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService#eliminar(java.lang.Long)
	 */
	@Override
	@Transactional
	public void eliminar(Long codigo) throws Exception {
		archivoAdjuntoDao.eliminar(codigo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService#actualizar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	@Transactional
	public void actualizar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		archivoAdjuntoDao.actualizar(archivoAdjuntoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICommArchivoAdjuntoService#consultar(java.lang.Long)
	 */
	@Override
	public CommArchivoAdjuntoBean consultar(Long codigo) throws Exception {
		CommArchivoAdjuntoBean result = archivoAdjuntoDao.consultar(codigo);
		return result;
	}

}
