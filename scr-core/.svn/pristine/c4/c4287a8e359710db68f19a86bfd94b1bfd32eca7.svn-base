/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoCloroResult;
import pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean;
import pe.com.sedapal.scr.core.dao.IEnsayoCloroDao;
import pe.com.sedapal.scr.core.services.IEnsayoCloroService;

// TODO: Auto-generated Javadoc
/**
 * The Class IEnsayoCloroServiceImpl.
 */
@Service
public class IEnsayoCloroServiceImpl implements IEnsayoCloroService{
	
	/** The ensayo cloro dao. */
	@Autowired
	IEnsayoCloroDao ensayoCloroDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#obtenerDatosEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEnsayoCloro(EnsayoCloro ensayoCloro, Paginacion paginacion) {
		return ensayoCloroDao.obtenerDatosEnsayoCloro(ensayoCloro, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#obtenerListEnsayoCloro(java.lang.Integer)
	 */
	@Override
	public List<EnsayoCloroDetalle> obtenerListEnsayoCloro(Integer idEnsayo) {
		return ensayoCloroDao.obtenerListEnsayoCloro(idEnsayo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#guardarEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro)
	 */
	@Override
	public int guardarEnsayoCloro(EnsayoCloro ensayoCloro) {
		return ensayoCloroDao.guardarEnsayoCloro(ensayoCloro);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#guardarEnsayoCloroDetalle(pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle)
	 */
	@Override
	public void guardarEnsayoCloroDetalle(EnsayoCloroDetalle ensayoCloroDetalle) {
		ensayoCloroDao.guardarEnsayoCloroDetalle(ensayoCloroDetalle);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#buscarEnsayoCloroXId(java.lang.Integer)
	 */
	@Override
	public EnsayoCloro buscarEnsayoCloroXId(Integer id){
		return ensayoCloroDao.buscarEnsayoCloroXId(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#inactivarEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro)
	 */
	@Override
	public void inactivarEnsayoCloro(EnsayoCloro ensayoCloro) {
		ensayoCloroDao.inactivarEnsayoCloro(ensayoCloro);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#buscarEnsayoCloroFechaHora(java.lang.String, java.lang.String)
	 */
	public EnsayoCloro buscarEnsayoCloroFechaHora(String fechaMuetreo,String horaMuestreo){
		return ensayoCloroDao.buscarEnsayoCloroFechaHora(fechaMuetreo,horaMuestreo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#obtenerReporteEnsayoCloro(java.lang.Integer, java.lang.String)
	 */
	public EnsayoCloroDetalle obtenerReporteEnsayoCloro(Integer puntoMuestro, String fechaMuestreo){
		return ensayoCloroDao.obtenerReporteEnsayoCloro(puntoMuestro, fechaMuestreo);
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#obtenerReporteEnsayoCloroBandeja(java.lang.String)
	 */
	public List<ReporteEnsayoCloroResult> obtenerReporteEnsayoCloroBandeja(String fechaMes){
		return ensayoCloroDao.obtenerReporteEnsayoCloroBandeja(fechaMes);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IEnsayoCloroService#isValid(pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean)
	 */
	@Override
	public Boolean isValid(ValidacionEnsayoCloroBean validacionBean) {
		return ensayoCloroDao.obtenerEnsayosParaDiaMuestreos(validacionBean) == 0;
	}
}
