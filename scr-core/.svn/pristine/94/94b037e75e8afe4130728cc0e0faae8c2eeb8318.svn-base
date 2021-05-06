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
import pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao;
import pe.com.sedapal.scr.core.services.ISubactividadXGrupoService;

// TODO: Auto-generated Javadoc
/**
 * The Class SubactividadXGrupoServiceImpl.
 */
@Service
public class SubactividadXGrupoServiceImpl implements ISubactividadXGrupoService{
	
	/** The subactividad X grupo dao. */
	@Autowired
	private ISubactividadXGrupoDao subactividadXGrupoDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ISubactividadXGrupoService#actualizarSubactividadXGrupo(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void actualizarSubactividadXGrupo(ItemPlanOperativo subactividadXGrupo) {
		subactividadXGrupoDao.actualizarSubactividadXGrupo(subactividadXGrupo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ISubactividadXGrupoService#insertarSubactividadXGrupo(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void insertarSubactividadXGrupo(ItemPlanOperativo subactividadXGrupo) {
		 subactividadXGrupoDao.insertarSubactividadXGrupo(subactividadXGrupo);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ISubactividadXGrupoService#existeSubactividadXGrupo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean existeSubactividadXGrupo(Integer idGrupo, Integer idSubactividad) {
		return subactividadXGrupoDao.existeSubactividadXGrupo(idGrupo, idSubactividad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ISubactividadXGrupoService#listarActividades(java.lang.Integer)
	 */
	@Override
	public List<ItemPlanOperativo> listarActividades(Integer idArea) {
		return subactividadXGrupoDao.listarActividades(idArea);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ISubactividadXGrupoService#listarSubactividades(java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> listarSubactividades(Integer idMaster) {
		return subactividadXGrupoDao.listarSubactividades(idMaster);
	}
	
}
