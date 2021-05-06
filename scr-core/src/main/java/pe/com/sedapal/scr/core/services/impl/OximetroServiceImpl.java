package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.OximetroBean;
import pe.com.sedapal.scr.core.dao.IOximetroDao;
import pe.com.sedapal.scr.core.services.IOximetroService;

@Service
public class OximetroServiceImpl implements IOximetroService {
	
	/** The generalDao dao. */
	@Autowired
	private IOximetroDao OximetroDao;

	@Override
	public List<OximetroBean> obtenerLtaOximetroTodo() throws GmdException {
		List<OximetroBean> ltaDetalleGeneral = new ArrayList<OximetroBean>();
        try {        	
        	ltaDetalleGeneral = OximetroDao.obtenerLtaOximetroTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<OximetroBean> obtenerLtaOximetro() throws GmdException {
		List<OximetroBean> ltaDetalleGeneral = new ArrayList<OximetroBean>();
        try {        	
        	ltaDetalleGeneral = OximetroDao.obtenerLtaOximetro();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularOximetro(OximetroBean Oximetro) throws GmdException {
		try {        	
			OximetroDao.anularOximetro(Oximetro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarOximetro(OximetroBean Oximetro) throws GmdException {
		try {        	
			OximetroDao.actualizarOximetro(Oximetro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public OximetroBean registrarOximetro(OximetroBean Oximetro) throws GmdException {
		OximetroBean bean = new OximetroBean();
		try {        	
			bean = OximetroDao.registrarOximetro(Oximetro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
}
