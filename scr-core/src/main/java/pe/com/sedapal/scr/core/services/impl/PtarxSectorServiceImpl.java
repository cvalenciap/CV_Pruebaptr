package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.PtarxSectorBean;
import pe.com.sedapal.scr.core.dao.IPtarxSectorDao;
import pe.com.sedapal.scr.core.services.IPtarxSectorService;

@Service
public class PtarxSectorServiceImpl implements IPtarxSectorService {
	
	/** The generalDao dao. */
	@Autowired
	private IPtarxSectorDao ptarxSectorDao;

	@Override
	public List<PtarxSectorBean> obtenerPtarxSector() throws GmdException {
		List<PtarxSectorBean> ltaPtarxSectorBean = new ArrayList<PtarxSectorBean>();
        try {        	
        	ltaPtarxSectorBean = ptarxSectorDao.obtenerPtarxSector();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaPtarxSectorBean;
	}

	@Override
	public void anularPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		try {        	
			ptarxSectorDao.anularPtarxSector(ptarxSectorBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	public void actualizarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		try {        	
			ptarxSectorDao.actualizarPtarxSector(ptarxSectorBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  	
	}

	@Override
	public PtarxSectorBean registrarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		PtarxSectorBean bean = new PtarxSectorBean();
		try {        	
			bean = ptarxSectorDao.registrarPtarxSector(ptarxSectorBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public Integer validaPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		int indicador = 0;
		try {        	
			indicador = ptarxSectorDao.validaPtarxSector(ptarxSectorBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return indicador;
	}

	@Override
	public List<PtarxSectorBean> obtenerNoPtarxSector() throws GmdException {
		List<PtarxSectorBean> ltaPtarxSectorBean = new ArrayList<PtarxSectorBean>();
        try {        	
        	ltaPtarxSectorBean = ptarxSectorDao.obtenerNoPtarxSector();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaPtarxSectorBean;
	}

	@Override
	public List<PtarxSectorBean> obtenerPtarxSectorTodo() throws GmdException {
		List<PtarxSectorBean> ltaPtarxSectorBean = new ArrayList<PtarxSectorBean>();
        try {        	
        	ltaPtarxSectorBean = ptarxSectorDao.obtenerPtarxSectorTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaPtarxSectorBean;
	}
}
