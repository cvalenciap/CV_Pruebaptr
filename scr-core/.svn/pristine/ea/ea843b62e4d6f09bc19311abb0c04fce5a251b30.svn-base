/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.dao.ICatalogoDao;
import pe.com.sedapal.scr.core.services.ICatalogoService;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogoServicesImpl.
 */
@Service
public class CatalogoServicesImpl implements ICatalogoService{
	
	/** The catalogo dao. */
	@Autowired
	private ICatalogoDao catalogoDao;
	
	
	/**
	 * Gets the catalogo dao.
	 *
	 * @return the catalogo dao
	 */
	public ICatalogoDao getCatalogoDao() {
		return catalogoDao;
	}

	/**
	 * Sets the catalogo dao.
	 *
	 * @param catalogoDao the new catalogo dao
	 */
	public void setCatalogoDao(ICatalogoDao catalogoDao) {
		this.catalogoDao = catalogoDao;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICatalogoService#obtenerCatalogo(java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla) {
		List<SelectItemBean> cats=null;
		try {
			cats= catalogoDao.obtenerCatalogo(idTabla);
		} catch (Exception e) {
			
		}
		return cats;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICatalogoService#obtenerCatalogoTodosNinguno(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> obtenerCatalogoTodosNinguno(Integer idTabla, Integer flag) {
		List<SelectItemBean> cats=null;
		try {
			cats= catalogoDao.obtenerCatalogoTodosNinguno(idTabla, flag);
		} catch (Exception e) {
			
		}
		return cats;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICatalogoService#obtenerCatalogoSeleccionar(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> obtenerCatalogoSeleccionar(Integer idTabla, Integer flag) {
		List<SelectItemBean> cats=null;
		try {
			cats= catalogoDao.obtenerCatalogoSeleccionar(idTabla, flag);
		} catch (Exception e) {
			
		}
		return cats;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.ICatalogoService#obtenerCatalogo(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla, String columnToOrder) {
		List<SelectItemBean> cats=null;
		cats= catalogoDao.obtenerCatalogo(idTabla,columnToOrder);
		return cats;
	}
	
	
}
