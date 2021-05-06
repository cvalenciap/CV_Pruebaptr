package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;

public interface IAnalistaDao {
	
	List<AnalistaBean> obtenerAnalista() throws GmdException;
	
	List<AnalistaBean> obtenerAnalistaTodo() throws GmdException;
	
	void anularAnalista(AnalistaBean analistaBean) throws GmdException;
	
	void actualizarAnalista(AnalistaBean analistaBean) throws GmdException;
	
	AnalistaBean registrarAnalista(AnalistaBean analistaBean) throws GmdException;
	
	List<TrabajadorBean> obtenerTrabajador(String numeroFicha) throws GmdException;
	
	List<AnalistaBean> obtenerAnalistabyFiltro(AnalistaBean analistaBean) throws GmdException;
	
	List<AnalistaBean> obtenerAnalistabyUsuario(String codigoUsuario) throws GmdException;
	
	List<ParametroPtarSectorBean> obtenerParametrosxAnalistaAsignados(Integer idAnalista) throws GmdException;
	
	List<ParametroPtarSectorBean> obtenerParametrosxAnalistaNoAsignados(Integer idAnalista) throws GmdException;
	
	void anularAllParametrosxAnalista(Integer idAnalista) throws GmdException; 
	
	Integer registrarParametrosxAnalista(Integer idAnalista,ParametroPtarSectorBean  parametro,BaseSptarBean auditoria) throws GmdException;	
	
}
