package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;
import pe.com.sedapal.scr.core.dao.ISolidoSuspendidoDao;
import pe.com.sedapal.scr.core.services.ISolidoSuspendidoService;

@Service
public class SolidoSuspendidoServiceImpl implements ISolidoSuspendidoService {
	
	/** The generalDao dao. */
	@Autowired
	private ISolidoSuspendidoDao solidoSuspendidoDao;

	@Override
	public List<SolidoSuspendidoBean> obtenerLtaSolidoTodo(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<SolidoSuspendidoBean> ltaDetalleGeneral = new ArrayList<SolidoSuspendidoBean>();
        try {        	
        	ltaDetalleGeneral = solidoSuspendidoDao.obtenerLtaSolidoTodo(idPtarxSector, idTipoSolido);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<SolidoSuspendidoBean> obtenerLtaSolidoSuspendido(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<SolidoSuspendidoBean> ltaDetalleGeneral = new ArrayList<SolidoSuspendidoBean>();
        try {        	
        	ltaDetalleGeneral = solidoSuspendidoDao.obtenerLtaSolidoSuspendido(idPtarxSector, idTipoSolido);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		try {        	
			solidoSuspendidoDao.anularSolidoSuspendido(solidoSuspendido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		try {        	
			solidoSuspendidoDao.actualizarSolidoSuspendido(solidoSuspendido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SolidoSuspendidoBean registrarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		SolidoSuspendidoBean bean = new SolidoSuspendidoBean();
		try {        	
			bean = solidoSuspendidoDao.registrarSolidoSuspendido(solidoSuspendido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
}
