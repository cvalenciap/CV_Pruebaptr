/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ManiobraBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IManiobraDao;
import pe.com.sedapal.scr.core.services.IManiobraService;

// TODO: Auto-generated Javadoc
/**
 * The Class ManiobraServiceImpl.
 */
@Service
public class ManiobraServiceImpl implements IManiobraService {
	
	/** The i maniobra dao. */
	@Autowired
	private IManiobraDao iManiobraDao;

	/**
	 * Obtiene la lista de los tipos de maniobra.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de la maniobra
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerTiposManiobra() throws Exception {
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < Constants.MANIOBRA_TYPE.length; i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + (i+1));
			sib.setLabel(Constants.MANIOBRA_TYPE[i]);
			result.add(sib);
		}
		return result;
	}

	/**
	 * Método que permite obtener el listado de Maniobras.
	 *
	 * @param maniobraBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result buscarManiobras(ManiobraBean maniobraBean, Paginacion paginacion) throws Exception {
		return iManiobraDao.buscarManiobras(maniobraBean, paginacion);
	}

	/**
	 * Realiza el registro de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void registrarManiobra(ManiobraBean maniobraBean) throws Exception {
		iManiobraDao.registrarManiobra(maniobraBean);		
	}

	/**
	 * Realiza la modificación de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarManiobra(ManiobraBean maniobraBean) throws Exception {
		iManiobraDao.actualizarManiobra(maniobraBean);	
	}

	/**
	 * Realiza el cambio de estado de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarManiobra(ManiobraBean maniobraBean) throws Exception {
		iManiobraDao.inactivarManiobra(maniobraBean);
	}

	/**
	 *  
	 * Obtiene el registro activo de mayor hora por identificador de represas.
	 *
	 * @param codRepresas Identificador del registro de represas
	 * @return the maniobra bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ManiobraBean obtenerUltimaManiobraActiva(Integer codRepresas) throws Exception {
		List<ManiobraBean> lstManiobras = iManiobraDao.obtenerManiobrasActivas(codRepresas);
		if(lstManiobras==null || lstManiobras.size()==0){
			return null;
		}
		return lstManiobras.get(lstManiobras.size()-1);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IManiobraService#obtenerManiobrasActivas(java.lang.Integer)
	 */
	@Override
	public List<ManiobraBean> obtenerManiobrasActivas(Integer codRepresas) throws Exception {
		return iManiobraDao.obtenerManiobrasActivas(codRepresas);
	}

}
