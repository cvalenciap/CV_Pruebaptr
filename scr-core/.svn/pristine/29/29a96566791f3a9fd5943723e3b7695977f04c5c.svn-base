package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;
import pe.com.sedapal.scr.core.dao.ITuboDilucionDao;
import pe.com.sedapal.scr.core.services.ITuboDilucionService;

@Service
public class TuboDilucionServiceImpl implements ITuboDilucionService {
	
	/** The generalDao dao. */
	@Autowired
	private ITuboDilucionDao tuboDilucionDao;

	@Override
	public List<TuboDilucionBean> obtenerLtaTuboDilucion() throws GmdException {
		List<TuboDilucionBean> ltaDetalleGeneral = new ArrayList<TuboDilucionBean>();
        try {        	
        	ltaDetalleGeneral = tuboDilucionDao.obtenerLtaTuboDilucion();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException {
		try {        	
			tuboDilucionDao.anularTuboDilucion(tuboDilucionBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException {
		try {        	
			tuboDilucionDao.actualizarTuboDilucion(tuboDilucionBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public TuboDilucionBean registrarTuboDilucion(TuboDilucionBean TuboDilucionBean) throws GmdException {
		TuboDilucionBean bean = new TuboDilucionBean();
		try {        	
			bean = tuboDilucionDao.registrarTuboDilucion(TuboDilucionBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<TuboDilucionBean> obtenerLtaTuboDilucionTodo() throws GmdException {
		List<TuboDilucionBean> ltaDetalleGeneral = new ArrayList<TuboDilucionBean>();
        try {        	
        	ltaDetalleGeneral = tuboDilucionDao.obtenerLtaTuboDilucionTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<TuboDilucionBean> obtenerTuboDilucionByCadena(String valorCombinacion, Integer numExponente) throws GmdException {
		List<TuboDilucionBean> ltaTuboDilucion = new ArrayList<TuboDilucionBean>();
        try {        	
        	ltaTuboDilucion = tuboDilucionDao.obtenerTuboDilucionByCadena(valorCombinacion, numExponente);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaTuboDilucion;
	}
}
