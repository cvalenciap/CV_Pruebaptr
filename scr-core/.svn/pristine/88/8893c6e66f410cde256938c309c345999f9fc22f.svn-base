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
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantClimaDao;
import pe.com.sedapal.scr.core.services.IMantClimaService;


// TODO: Auto-generated Javadoc
/**
 * The Class MantClimaServiceImpl.
 */
@Service
public class MantClimaServiceImpl implements IMantClimaService {

	/** The i mant clima dao. */
	@Autowired
	private IMantClimaDao iMantClimaDao;
	
	/**
	 * Método que permite obtener el listado de Clima.
	 *
	 * @param climaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Result obtenerMantClima(ClimaBean climaBean, Paginacion paginacion) throws Exception {
		return iMantClimaDao.obtenerMantClima(climaBean, paginacion);
	}

	/**
	 *  
	 * Obtiene el clima por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo climaBean que contiene el registro
	 */
	@Override
	public ClimaBean obtenerClima(Integer nid) throws Exception {
		return iMantClimaDao.obtenerClima(nid);
	}
	
	/**
	 * Realiza el registro de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean grabarClima(ClimaBean climaBean) throws Exception {
		 if(!iMantClimaDao.validarAbrevMantClima(climaBean).isBolAbreviatura()){
			 climaBean.setAbreviatura(climaBean.getAbreviatura().trim());
			 iMantClimaDao.grabarClima(climaBean);
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Realiza la modificación de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean actualizarClima(ClimaBean climaBean, boolean abreviaturaIsUpdate) throws Exception {
		if(abreviaturaIsUpdate){
			if(!iMantClimaDao.validarAbrevMantClima(climaBean).isBolAbreviatura()){
				climaBean.setAbreviatura(climaBean.getAbreviatura().trim());
				iMantClimaDao.actualizarClima(climaBean);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			iMantClimaDao.actualizarClima(climaBean);
			return true;
		}
		
		
		
	}
	
	/**
	 *  
	 * Realiza el cambío de estado de clima.
	 *
	 * @param climaBean the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarClima(ClimaBean climaBean) throws Exception {
		iMantClimaDao.inactivarClima(climaBean);
	}
	
	/**
	 * Obtiene la lista de climas activos.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de los climas
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerClimasActivos() throws Exception {
		List<ClimaBean> climas = iMantClimaDao.obtenerClimas(Constants.ACTIVE_STATE);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < climas.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + climas.get(i).getLonCodigo());
			sib.setLabel(climas.get(i).getNombreClima());
			result.add(sib);
		}
		return result;
	}
	
	/**
	 * Obtiene la lista de todos los climas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del clima
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerClimasTodos() throws Exception {
		List<ClimaBean> climas = iMantClimaDao.obtenerClimas(null);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < climas.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + climas.get(i).getLonCodigo());
			sib.setLabel(climas.get(i).getNombreClima());
			result.add(sib);
		}
		return result;
	}
	
}
