package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AlertasNotificacionBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;
import pe.com.sedapal.scr.core.dao.IAlertasNotificacionDao;
import pe.com.sedapal.scr.core.services.IAlertasNotificacionService;

@Service
public class AlertasNotificacionServiceImpl implements IAlertasNotificacionService {
	
	/** The generalDao dao. */
	@Autowired
	private IAlertasNotificacionDao AlertasNotificacionDao;

	@Override
	public List<AlertasNotificacionBean> obtenerLtaAlertasNotificacionTodo() throws GmdException {
		List<AlertasNotificacionBean> ltaDetalleGeneral = new ArrayList<AlertasNotificacionBean>();
        try {        	
        	ltaDetalleGeneral = AlertasNotificacionDao.obtenerLtaAlertasNotificacionTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<AlertasNotificacionBean> obtenerLtaAlertasNotificacion(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<AlertasNotificacionBean> ltaDetalleGeneral = new ArrayList<AlertasNotificacionBean>();
        try {        	
        	ltaDetalleGeneral = AlertasNotificacionDao.obtenerLtaAlertasNotificacion(idPtarxSector, idTipoSolido);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException {
		try {        	
			AlertasNotificacionDao.actualizarAlertasNotificacion(AlertasNotificacion);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public AlertasNotificacionBean registrarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException {
		AlertasNotificacionBean bean = new AlertasNotificacionBean();
		try {        	
			bean = AlertasNotificacionDao.registrarAlertasNotificacion(AlertasNotificacion);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<NotificacionAnalistaBean> obtenerLtaAnalistasVinculados(Integer idAlertasNotificacion) throws GmdException {
		List<NotificacionAnalistaBean> ltaAnalistasVinculados = new ArrayList<NotificacionAnalistaBean>();
        try {        	
        	ltaAnalistasVinculados = AlertasNotificacionDao.obtenerLtaAnalistasVinculados(idAlertasNotificacion);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaAnalistasVinculados;
	}
	
	@Override
	public List<AnalistaBean> obtenerLtaAnalistasNoVinculados(Integer idAlertasNotificacion) throws GmdException {
		List<AnalistaBean> ltaAnalistasNoVinculados = new ArrayList<AnalistaBean>();
        try {        	
        	ltaAnalistasNoVinculados = AlertasNotificacionDao.obtenerLtaAnalistasNoVinculados(idAlertasNotificacion);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaAnalistasNoVinculados;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void agregarRegistroAnalista(Integer idAlertasNotificacion, Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasNotificacionDao.agregarRegistroAnalista(idAlertasNotificacion, idAnalista, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroAnalista(Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasNotificacionDao.anularRegistroAnalista(idAnalista, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAlertasNotificacion(Integer idAlertasNotificacion, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasNotificacionDao.anularAlertasNotificacion(idAlertasNotificacion, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	public AlertasNotificacionBean buscarAlertasNotificacionHTML(Integer idAlertasNotificacion) throws GmdException {
		AlertasNotificacionBean AlertaNotificacion = new AlertasNotificacionBean();
        try {        	
        	AlertaNotificacion = AlertasNotificacionDao.buscarAlertasNotificacionHTML(idAlertasNotificacion);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return AlertaNotificacion;
	}
}
