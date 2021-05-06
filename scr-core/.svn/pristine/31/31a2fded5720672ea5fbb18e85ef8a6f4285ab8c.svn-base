/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.ItemPlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao;
import pe.com.sedapal.scr.core.services.IParametroXSubactividadService;

// TODO: Auto-generated Javadoc
/**
 * The Class ParametroXSubactividadServiceImpl.
 */
@Service
public class ParametroXSubactividadServiceImpl implements IParametroXSubactividadService{

	/** The parametro X subactividad dao. */
	@Autowired
	IParametroXSubactividadDao parametroXSubactividadDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IParametroXSubactividadService#actualizarParametroXSubactividad(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void actualizarParametroXSubactividad(ItemPlanOperativo subactividadXGrupo) {
		parametroXSubactividadDao.actualizarParametroXSubactividad(subactividadXGrupo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IParametroXSubactividadService#insertarParametroXSubactividad(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void insertarParametroXSubactividad(ItemPlanOperativo subactividadXGrupo) {
		parametroXSubactividadDao.insertarParametroXSubactividad(subactividadXGrupo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IParametroXSubactividadService#existeParametroXSubactividad(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean existeParametroXSubactividad(Integer idSubactividadXActividad, Integer idParametro) {
		return parametroXSubactividadDao.existeParametroXSubactividad(idSubactividadXActividad, idParametro);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IParametroXSubactividadService#listarSubActividades(java.lang.Integer)
	 */
	@Override
	public List<ItemPlanOperativo> listarSubActividades(Integer idActividad) {
		return parametroXSubactividadDao.listarSubActividades(idActividad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IParametroXSubactividadService#listarParametros(java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> listarParametros(Integer idSubActividad) {
		return parametroXSubactividadDao.listarParametros(idSubActividad);
	}

}
