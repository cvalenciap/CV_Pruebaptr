/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.Parametro;
import pe.com.sedapal.scr.core.dao.ITipoServicioDao;
import pe.com.sedapal.scr.core.services.ITipoServicioService;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoServicioServiceImpl.
 */
@Service
public class TipoServicioServiceImpl implements ITipoServicioService {

	/** The tipo servicio dao. */
	private ITipoServicioDao tipoServicioDao;
	
	/**
	 * Sets the tipo servicio dao.
	 *
	 * @param tipoServicioDao the new tipo servicio dao
	 */
	@Autowired
	public void setTipoServicioDao(ITipoServicioDao tipoServicioDao) {
		this.tipoServicioDao = tipoServicioDao;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITipoServicioService#obtenerTipoDeServicio(pe.com.sedapal.scr.core.beans.Parametro, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerTipoDeServicio(Parametro parametro, Paginacion paginacion) throws Exception {
		return tipoServicioDao.obtenerTipoDeServicio(parametro, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITipoServicioService#obtenerTipoDeServicio(java.lang.Integer)
	 */
	@Override
	public Parametro obtenerTipoDeServicio(Integer nid) throws Exception {
		return tipoServicioDao.obtenerTipoDeServicio(nid);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITipoServicioService#grabarTipoDeServicio(pe.com.sedapal.scr.core.beans.Parametro)
	 */
	@Override
	public void grabarTipoDeServicio(Parametro parametro) throws Exception {
		tipoServicioDao.grabarTipoDeServicio(parametro);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITipoServicioService#actualizarPuntoDistribucion(pe.com.sedapal.scr.core.beans.Parametro)
	 */
	@Override
	public void actualizarPuntoDistribucion(Parametro parametroEditBean) throws Exception {
		tipoServicioDao.actualizarPuntoDistribucion(parametroEditBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITipoServicioService#inactivarTipoDeServicio(pe.com.sedapal.scr.core.beans.Parametro)
	 */
	@Override
	public void inactivarTipoDeServicio(Parametro parametro) throws Exception {
		tipoServicioDao.inactivarTipoDeServicio(parametro);
	}

	

}
