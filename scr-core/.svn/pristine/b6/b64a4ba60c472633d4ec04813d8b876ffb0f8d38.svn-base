package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RegistroAdjuntoBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.dao.IRegistroAdjuntoDao;
import pe.com.sedapal.scr.core.services.IRegistroAdjuntoService;

@Service
public class RegistroAdjuntoServiceImpl implements IRegistroAdjuntoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroAdjuntoDao registroAdjuntoDao;

	@Override
	public List<RegistroAdjuntoBean> obtenerRegistroAdjuntobyRegistroLab(Integer idRegLab, Integer idParametro)
			throws GmdException {
		List<RegistroAdjuntoBean> ltaDetalleGeneral = new ArrayList<RegistroAdjuntoBean>();
        try {        	
        	ltaDetalleGeneral = registroAdjuntoDao.obtenerRegistroAdjuntobyRegistroLab(idRegLab,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroAdjuntoBean registrarRegistroAdjunto(RegistroAdjuntoBean item) throws GmdException {
		RegistroAdjuntoBean bean = new RegistroAdjuntoBean();
		try {        	
			bean = registroAdjuntoDao.registrarRegistroAdjunto(item);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
}
