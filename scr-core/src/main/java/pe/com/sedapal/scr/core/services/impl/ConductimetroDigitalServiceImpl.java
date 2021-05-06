/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao;
import pe.com.sedapal.scr.core.services.IConductimetroDigitalService;

// TODO: Auto-generated Javadoc
/**
 * The Class ConductimetroDigitalServiceImpl.
 */
@Service
public class ConductimetroDigitalServiceImpl implements IConductimetroDigitalService {
	
	/** The conductimetro digital dao. */
	@Autowired
	IConductimetroDigitalDao conductimetroDigitalDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(ConductimetroDigitalBean conductimetroDigitalBean, Paginacion paginacion)
			throws Exception {
		return conductimetroDigitalDao.obtenerDatosEquipos(conductimetroDigitalBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#obtenerConductimetroDigital(java.lang.Integer)
	 */
	@Override
	public ConductimetroDigitalBean obtenerConductimetroDigital(Integer id) throws Exception {
		return conductimetroDigitalDao.obtenerConductimetroDigital(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#grabarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public int grabarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		return conductimetroDigitalDao.grabarConductimetroDigital(conductimetroDigitalBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#actualizarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public void actualizarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		conductimetroDigitalDao.actualizarConductimetroDigital(conductimetroDigitalBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#inactivarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public void inactivarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		conductimetroDigitalDao.inactivarConductimetroDigital(conductimetroDigitalBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#obtenerDatosEquiposDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean, Paginacion paginacion) throws Exception {
		
		return conductimetroDigitalDao.obtenerDatosEquiposDetalle(conductimetroDigitalDetalleBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#grabarConductimetroDigitalDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean)
	 */
	@Override
	public int grabarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception {
		return conductimetroDigitalDao.grabarConductimetroDigitalDetalle(conductimetroDigitalDetalleBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IConductimetroDigitalService#inactivarConductimetroDigitalDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean)
	 */
	@Override
	public void inactivarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception {
		conductimetroDigitalDao.inactivarConductimetroDigitalDetalle(conductimetroDigitalDetalleBean);
		
	}

}
