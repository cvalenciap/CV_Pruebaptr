package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AlertasBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;
import pe.com.sedapal.scr.core.dao.IAlertasDao;
import pe.com.sedapal.scr.core.services.IAlertasService;

@Service
public class AlertasServiceImpl implements IAlertasService {
	
	/** The generalDao dao. */
	@Autowired
	private IAlertasDao AlertasDao;

	@Override
	public List<AlertasBean> obtenerLtaAlertasTodo() throws GmdException {
		List<AlertasBean> ltaDetalleGeneral = new ArrayList<AlertasBean>();
        try {        	
        	ltaDetalleGeneral = AlertasDao.obtenerLtaAlertasTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<AlertasBean> obtenerLtaAlertas(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<AlertasBean> ltaDetalleGeneral = new ArrayList<AlertasBean>();
        try {        	
        	ltaDetalleGeneral = AlertasDao.obtenerLtaAlertas(idPtarxSector, idTipoSolido);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarAlertas(AlertasBean Alertas) throws GmdException {
		try {        	
			AlertasDao.actualizarAlertas(Alertas);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public AlertasBean registrarAlertas(AlertasBean Alertas) throws GmdException {
		AlertasBean bean = new AlertasBean();
		try {        	
			bean = AlertasDao.registrarAlertas(Alertas);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<NotificacionAnalistaBean> obtenerLtaAnalistasVinculados(Integer idAlertas) throws GmdException {
		List<NotificacionAnalistaBean> ltaAnalistasVinculados = new ArrayList<NotificacionAnalistaBean>();
        try {        	
        	ltaAnalistasVinculados = AlertasDao.obtenerLtaAnalistasVinculados(idAlertas);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaAnalistasVinculados;
	}
	
	@Override
	public List<AnalistaBean> obtenerLtaAnalistasNoVinculados(Integer idAlertas) throws GmdException {
		List<AnalistaBean> ltaAnalistasNoVinculados = new ArrayList<AnalistaBean>();
        try {        	
        	ltaAnalistasNoVinculados = AlertasDao.obtenerLtaAnalistasNoVinculados(idAlertas);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaAnalistasNoVinculados;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void agregarRegistroAnalista(Integer idAlertas, Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasDao.agregarRegistroAnalista(idAlertas, idAnalista, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroAnalista(Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasDao.anularRegistroAnalista(idAnalista, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAlertas(Integer idAlertas, BaseSptarBean auditoria) throws GmdException {
		try {        	
			AlertasDao.anularAlertas(idAlertas, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	public AlertasBean buscarAlertasHTML(Integer idAlertas) throws GmdException {
		AlertasBean AlertaNotificacion = new AlertasBean();
        try {        	
        	AlertaNotificacion = AlertasDao.buscarAlertasHTML(idAlertas);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return AlertaNotificacion;
	}
	
	@Override
	public String obtenerMensajeAlerta(Integer idAlertas) throws GmdException{
		String mensaje = null;
		try {
			mensaje = AlertasDao.obtenerMensajeAlerta(idAlertas);
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return mensaje;
	}
}
