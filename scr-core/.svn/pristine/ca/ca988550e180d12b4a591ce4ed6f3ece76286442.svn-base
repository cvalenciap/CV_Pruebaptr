/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.TablaPoissonBean;
import pe.com.sedapal.scr.core.dao.ITablaPoissonDao;
import pe.com.sedapal.scr.core.services.ITablaPoissonService;


// TODO: Auto-generated Javadoc
/**
 * The Class TablaPoissonServiceImpl.
 */
@Service
public class TablaPoissonServiceImpl implements ITablaPoissonService {

	/** The i tabla poisson dao. */
	@Autowired
	private ITablaPoissonDao iTablaPoissonDao;
	
	/**
	 * Sets the i tabla poisson dao.
	 *
	 * @param iTablaPoissonDao the new i tabla poisson dao
	 */
	public void setiTablaPoissonDao(ITablaPoissonDao iTablaPoissonDao) {
		this.iTablaPoissonDao = iTablaPoissonDao;
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITablaPoissonService#obtenerDatosPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosPoisson(TablaPoissonBean tablaPoissonBean, Paginacion paginacion) throws Exception {
		
		return iTablaPoissonDao.obtenerDatosPoisson(tablaPoissonBean, paginacion);
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITablaPoissonService#obtenerTablaPoisson(java.lang.Integer)
	 */
	@Override
	public TablaPoissonBean obtenerTablaPoisson(Integer id) throws Exception {
		return iTablaPoissonDao.obtenerTablaPoisson(id);
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITablaPoissonService#grabarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	@Override
	public int grabarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		return iTablaPoissonDao.grabarTablaPoisson(tablaPoissonBean);		
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITablaPoissonService#actualizarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	@Override
	public void actualizarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		iTablaPoissonDao.actualizarTablaPoisson(tablaPoissonBean);
		
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITablaPoissonService#inactivarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	@Override
	public void inactivarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		iTablaPoissonDao.inactivarTablaPoisson(tablaPoissonBean);
		
	}

}
