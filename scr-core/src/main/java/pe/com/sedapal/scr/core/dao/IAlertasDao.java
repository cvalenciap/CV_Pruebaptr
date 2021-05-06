package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AlertasBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;

public interface IAlertasDao {
	
	List<AlertasBean> obtenerLtaAlertasTodo() throws GmdException;
	
	List<AlertasBean> obtenerLtaAlertas(Integer idPtarxSector, Integer idTipoSolido) throws GmdException;
		
	void actualizarAlertas(AlertasBean Alertas) throws GmdException;
	
	AlertasBean registrarAlertas(AlertasBean Alertas) throws GmdException;
	
	List<NotificacionAnalistaBean> obtenerLtaAnalistasVinculados(Integer idAlertas) throws GmdException;
	
	List<AnalistaBean> obtenerLtaAnalistasNoVinculados(Integer idAlertas) throws GmdException;
	
	void agregarRegistroAnalista(Integer idAlertas, Integer idAnalista, BaseSptarBean auditoria) throws  GmdException;
	
	void anularRegistroAnalista(Integer idAnalista, BaseSptarBean auditoria) throws  GmdException;
	
	void anularAlertas(Integer idAlertas, BaseSptarBean auditoria) throws  GmdException;
	
	AlertasBean buscarAlertasHTML(Integer idAlertas) throws GmdException;
	
	String obtenerMensajeAlerta(Integer idAlertas) throws GmdException;
}
