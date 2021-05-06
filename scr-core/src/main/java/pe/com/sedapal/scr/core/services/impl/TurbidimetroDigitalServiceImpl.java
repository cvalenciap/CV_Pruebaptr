/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean;
import pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao;
import pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService;



// TODO: Auto-generated Javadoc
/**
 * The Class TurbidimetroDigitalServiceImpl.
 */
@Service
public class TurbidimetroDigitalServiceImpl implements ITurbidimetroDigitalService{

	/** The verificacion equipo dao. */
	@Autowired
	private ITurbidimetroDigitalDao verificacionEquipoDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(TurbidimetroDigitalBean verificacionEquipoBean, Paginacion paginacion)
			throws Exception {
		// TODO Auto-generated method stub
		return verificacionEquipoDao.obtenerDatosEquipos(verificacionEquipoBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#obtenerVerificacionEquipo(java.lang.Integer)
	 */
	@Override
	public TurbidimetroDigitalBean obtenerVerificacionEquipo(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return verificacionEquipoDao.obtenerVerificacionEquipo(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#grabarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public int grabarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception {
		// TODO Auto-generated method stub
		return verificacionEquipoDao.grabarVerificacionEquipo(verificacionEquipoBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#actualizarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public void actualizarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception {
		verificacionEquipoDao.actualizarVerificacionEquipo(verificacionEquipoBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#inactivarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public void inactivarVerificacionEquipo(TurbidimetroDigitalBean verificacionEquipoBean) throws Exception {
		verificacionEquipoDao.inactivarVerificacionEquipo(verificacionEquipoBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#obtenerDatosEquiposVerificacion(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposVerificacion(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean, Paginacion paginacion)
			throws Exception {
		return verificacionEquipoDao.obtenerDatosEquiposVerificacion(turbidimetroDigitalVerificacionBean, paginacion);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#grabarVerificacionEquipoForm(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean)
	 */
	@Override
	public int grabarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean)
			throws Exception {
		return verificacionEquipoDao.grabarVerificacionEquipoForm(turbidimetroDigitalVerificacionBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ITurbidimetroDigitalService#inactivarVerificacionEquipoForm(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean)
	 */
	@Override
	public void inactivarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean)
			throws Exception {
		verificacionEquipoDao.inactivarVerificacionEquipoForm(turbidimetroDigitalVerificacionBean);
		
	}
	

}
