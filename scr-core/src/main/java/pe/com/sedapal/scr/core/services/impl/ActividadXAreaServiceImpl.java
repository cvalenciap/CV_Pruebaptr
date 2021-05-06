/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.ActividadArea;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.dao.IActividadXAreaDao;
import pe.com.sedapal.scr.core.services.IActividadXAreaService;

// TODO: Auto-generated Javadoc
/**
 * The Class ActividadXAreaServiceImpl.
 */
@Service
public class ActividadXAreaServiceImpl implements IActividadXAreaService {
	
	/** The actividad X area dao. */
	@Autowired
	private IActividadXAreaDao actividadXAreaDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IActividadXAreaService#actualizarActividadXArea(pe.com.sedapal.scr.core.beans.ActividadArea)
	 */
	public void actualizarActividadXArea(ActividadArea actividadArea){
		actividadXAreaDao.actualizarActividadXArea(actividadArea);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IActividadXAreaService#insertarActividad(pe.com.sedapal.scr.core.beans.ActividadArea)
	 */
	public void insertarActividad(ActividadArea actividadArea) {
		actividadXAreaDao.insertarActividad(actividadArea);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IActividadXAreaService#listarActividades(java.lang.Integer)
	 */
	public List<SelectItemBean> listarActividades(Integer idArea){
		return actividadXAreaDao.listarActividades(idArea);
	}

	/**
	 * Gets the actividad X area dao.
	 *
	 * @return the actividad X area dao
	 */
	public IActividadXAreaDao getActividadXAreaDao() {
		return actividadXAreaDao;
	}

	/**
	 * Sets the actividad X area dao.
	 *
	 * @param actividadXAreaDao the new actividad X area dao
	 */
	public void setActividadXAreaDao(IActividadXAreaDao actividadXAreaDao) {
		this.actividadXAreaDao = actividadXAreaDao;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IActividadXAreaService#existeActividad(java.lang.Integer, java.lang.Integer)
	 */
	public boolean existeActividad(Integer idArea, Integer idACtividad) {
		return actividadXAreaDao.existeActividad(idArea, idACtividad);
	}
	
	
	
}
