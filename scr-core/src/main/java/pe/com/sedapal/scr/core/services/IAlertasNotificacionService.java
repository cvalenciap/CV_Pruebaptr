package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AlertasNotificacionBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;

public interface IAlertasNotificacionService {

	List<AlertasNotificacionBean> obtenerLtaAlertasNotificacionTodo() throws GmdException;
	
	List<AlertasNotificacionBean> obtenerLtaAlertasNotificacion(Integer idPtarxSector, Integer idTipoSolido) throws GmdException;

	void actualizarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException;

	AlertasNotificacionBean registrarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException;
	
	List<NotificacionAnalistaBean> obtenerLtaAnalistasVinculados(Integer idAlertasNotificacion) throws GmdException;
	
	List<AnalistaBean> obtenerLtaAnalistasNoVinculados(Integer idAlertasNotificacion) throws GmdException;
	
	void agregarRegistroAnalista(Integer idAlertasNotificacion, Integer idAnalista, BaseSptarBean auditoria) throws GmdException;
	
	void anularRegistroAnalista(Integer idAnalista, BaseSptarBean auditoria) throws GmdException;
	
	void anularAlertasNotificacion(Integer idAlertasNotificacion, BaseSptarBean auditoria) throws GmdException;
	
	AlertasNotificacionBean buscarAlertasNotificacionHTML(Integer idAlertasNotificacion) throws GmdException;
}
