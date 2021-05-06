package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.dao.IDetalleGeneralDao;
import pe.com.sedapal.scr.core.services.IDetalleGeneralService;

@Service
public class DetalleGeneralServiceImpl implements IDetalleGeneralService {
	
	/** The generalDao dao. */
	@Autowired
	private IDetalleGeneralDao detalleGeneralDao;

	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneral(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerDetalleGeneral(idGeneral);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralI(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerDetalleGeneralI(idGeneral);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		try {        	
			detalleGeneralDao.anularDetalleGeneral(detalleGeneralBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		try {        	
			detalleGeneralDao.actualizarDetalleGeneral(detalleGeneralBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public DetalleGeneralBean registrarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		DetalleGeneralBean bean = new DetalleGeneralBean();
		try {        	
			bean = detalleGeneralDao.registrarDetalleGeneral(detalleGeneralBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralTodo(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerDetalleGeneralTodo(idGeneral);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
		
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralMicroorganismo(int idGeneral, int parametroMicro) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerDetalleGeneralMicroorganismo(idGeneral, parametroMicro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralbyidDetalle(Integer idDetalleGeneral) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerDetalleGeneralbyidDetalle(idDetalleGeneral);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<DetalleGeneralBean> obtenerListaPuntoMuestraES(int idPuntoMuestra) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerListaPuntoMuestraES(idPuntoMuestra);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	
	@Override
	public List<DetalleGeneralBean> obtenerListaMicroHidro(Integer idSubParametro) throws GmdException{
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = detalleGeneralDao.obtenerListaMicroHidro(idSubParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<DetalleGeneralBean> obtenerListaMicroPara(Integer idSubParametro) throws GmdException{
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
		try {
			ltaDetalleGeneral = detalleGeneralDao.obtenerListaMicroPara(idSubParametro);
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return ltaDetalleGeneral;
	}
}
