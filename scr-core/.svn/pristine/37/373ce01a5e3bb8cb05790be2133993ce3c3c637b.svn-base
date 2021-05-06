package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.VariableBean;
import pe.com.sedapal.scr.core.dao.IVariableDao;
import pe.com.sedapal.scr.core.services.IVariableService;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.FormulaBean;


@Service
public class VariableServiceImpl implements IVariableService {

		
		/** The generalDao dao. */
		@Autowired
		private IVariableDao variableDao;

		@Override
		public List<VariableBean> obtenerVariable() throws GmdException {
			List<VariableBean> ltaVariableBean = new ArrayList<VariableBean>();
	        try {        	
	        	ltaVariableBean = variableDao.obtenerVariable();    
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        }
	        return ltaVariableBean;
		}

		@Override
		public void anularVariable(VariableBean variableBean) throws GmdException {
			try {        	
				variableDao.anularVariable(variableBean);
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        } 
		}

		@Override
		public void actualizarVariable(VariableBean variableBean) throws GmdException {
			try {        
				variableDao.actualizarVariable(variableBean);
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        }  	
		}

		@Override
		public VariableBean registrarVariable(VariableBean variableBean) throws GmdException {
			VariableBean bean = new VariableBean();
			try {        	
				bean = variableDao.registrarVariable(variableBean);
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        }
			return bean;
		}

		@Override
		public List<VariableBean> obtenerVariableTodo(Integer idParametro) throws GmdException {
			List<VariableBean> ltaVariableBean = new ArrayList<VariableBean>();
	        try {        	
	        	ltaVariableBean = variableDao.obtenerVariableTodo(idParametro);    
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        }
	        return ltaVariableBean;
		}
		
		@Override
		public List<VariableBean> obtenerLtaVariable(Integer idParametro) throws GmdException{
			List<VariableBean> ltaDetalleGeneral = new ArrayList<VariableBean>();
	        try {        	
	        	ltaDetalleGeneral = variableDao.obtenerLtaVariable(idParametro);    
	        } catch (Exception excepcion) {
	            throw new GmdException(excepcion);
	        }        
	        return ltaDetalleGeneral;
		}
}
