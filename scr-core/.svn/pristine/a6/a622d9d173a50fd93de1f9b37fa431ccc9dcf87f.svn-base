package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.GeneralBean;
import pe.com.sedapal.scr.core.dao.IGeneralDao;
import pe.com.sedapal.scr.core.services.IGeneralService;


@Service
public class GeneralServiceImpl implements IGeneralService {
	
	/** The generalDao dao. */
	@Autowired
	private IGeneralDao generalDao;

	@Override
	public List<GeneralBean> listarTodos() throws GmdException {
		List<GeneralBean> ltaGeneral = new ArrayList<GeneralBean>();
        try {        	
        	ltaGeneral = generalDao.listarTodos();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaGeneral;
	}
}
