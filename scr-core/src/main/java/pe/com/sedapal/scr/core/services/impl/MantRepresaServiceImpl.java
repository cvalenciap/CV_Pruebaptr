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
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantRepresaDao;
import pe.com.sedapal.scr.core.services.IMantRepresaService;



// TODO: Auto-generated Javadoc
/**
 * The Class MantRepresaServiceImpl.
 */
@Service
public class MantRepresaServiceImpl implements IMantRepresaService {

	/** The i mant represa dao. */
	@Autowired
	private IMantRepresaDao iMantRepresaDao;
	
	/**
	 * Método que permite obtener el listado de Represa.
	 *
	 * @param represaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result obtenerMantRepresa(RepresaBean represaBean, Paginacion paginacion) throws Exception {
		return iMantRepresaDao.obtenerMantRepresa(represaBean, paginacion);
	}

	/**
	 *  
	 * Obtiene la represa por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represa bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo represaBean que contiene el registro
	 */
	@Override
	public RepresaBean obtenerRepresa(Integer nid) throws Exception {
		return iMantRepresaDao.obtenerRepresa(nid);
	}
	
	/**
	 * Realiza el registro de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean grabarRepresa(RepresaBean represaBean) throws Exception {
		 if(!iMantRepresaDao.validarAbrevMantRepresa(represaBean).isBolAbreviatura()){
			 represaBean.setAbreviatura(represaBean.getAbreviatura().trim());
			 iMantRepresaDao.grabarRepresa(represaBean);
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Realiza la modificación de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean actualizarRepresa(RepresaBean represaBean, boolean abreviaturaIsUpdate) throws Exception {
		
		if(abreviaturaIsUpdate){
			if(!iMantRepresaDao.validarAbrevMantRepresa(represaBean).isBolAbreviatura()){
				represaBean.setAbreviatura(represaBean.getAbreviatura().trim());
				iMantRepresaDao.actualizarRepresa(represaBean);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			iMantRepresaDao.actualizarRepresa(represaBean);
			return true;
		}
	}
	
	/**
	 *  
	 * Realiza el cambío de estado de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarRepresa(RepresaBean represaBean) throws Exception {
		iMantRepresaDao.inactivarRepresa(represaBean);
	}

	/**
	 * Obtiene la lista de represas activas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de las represas
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerRepresasActivas() throws Exception {
		List<RepresaBean> rios = iMantRepresaDao.obtenerRepresas(Constants.ACTIVE_STATE);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < rios.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + rios.get(i).getIntCodigo());
			sib.setLabel(rios.get(i).getNombreRepresa());
			result.add(sib);
		}
		return result;
	}
	
	/**
	 * Obtiene la lista de todas las represas.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de la represa
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerRepresasTodas() throws Exception {
		List<RepresaBean> rios = iMantRepresaDao.obtenerRepresas(null);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < rios.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + rios.get(i).getIntCodigo());
			sib.setLabel(rios.get(i).getNombreRepresa());
			result.add(sib);
		}
		return result;
	}
}
