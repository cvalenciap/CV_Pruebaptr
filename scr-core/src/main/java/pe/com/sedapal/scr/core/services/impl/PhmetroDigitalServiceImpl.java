/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao;
import pe.com.sedapal.scr.core.services.IPhmetroDigitalService;

// TODO: Auto-generated Javadoc
/**
 * The Class PhmetroDigitalServiceImpl.
 */
@Service
public class PhmetroDigitalServiceImpl implements IPhmetroDigitalService{

	/** The phmetro digital dao. */
	@Autowired
	private IPhmetroDigitalDao phmetroDigitalDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(PhmetroDigitalBean phmetroDigitalBean, Paginacion paginacion) throws Exception {
		return phmetroDigitalDao.obtenerDatosEquipos(phmetroDigitalBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#obtenerPhmetroDigital(java.lang.Integer)
	 */
	@Override
	public PhmetroDigitalBean obtenerPhmetroDigital(Integer id) throws Exception {
		return phmetroDigitalDao.obtenerPhmetroDigital(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#grabarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public int grabarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		return phmetroDigitalDao.grabarPhmetroDigital(phmetroDigitalBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#actualizarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public void actualizarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		phmetroDigitalDao.actualizarPhmetroDigital(phmetroDigitalBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#inactivarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public void inactivarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		phmetroDigitalDao.inactivarPhmetroDigital(phmetroDigitalBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#obtenerDatosEquiposDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean, Paginacion paginacion)
			throws Exception {
		return phmetroDigitalDao.obtenerDatosEquiposDetalle(phmetroDigitalDetalleBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#grabarPhmetroDigitalDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean)
	 */
	@Override
	public int grabarPhmetroDigitalDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean) throws Exception {
		return phmetroDigitalDao.grabarPhmetroDigitalDetalle(phmetroDigitalDetalleBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPhmetroDigitalService#inactivarPhmetroDigitalDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean)
	 */
	@Override
	public void inactivarPhmetroDigitalDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean) throws Exception {
		phmetroDigitalDao.inactivarPhmetroDigitalDetalle(phmetroDigitalDetalleBean);
		
	}

}
