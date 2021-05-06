package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;

public interface IRegistroDBODao {
	
	List<RegistroDBOBean> obtenerRegistroDBOByRegLaboratorio(Integer idRegLab) throws GmdException;	
	
	List<RegistroDBOBean> obtenerRegistroDBOByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroDBOBean registrarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
	
	void actualizarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
		
	void anularRegistroDBO(Integer idGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException;
	
	public List<RegistroDBOBean> obtenerRegistroDBOByidDBO(Integer idDBO,Date fechaDesde,Date FechaHasta)throws GmdException;
	
	List<RegistroDBOBean> obtenerRegistroDBOSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void actualizarFechasRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
	
	void aprobarRegistroBDO(RegistroDBOBean RegistroDBO) throws GmdException;
		
	void obtenerMejorRegistroEnlace(Integer idRegistroLaboratorio, Integer idPtoMuestra, Integer idSubparametro) throws GmdException;
	
	void obtenerMejorRegistroNoEnlace(Integer idRegistroLaboratorio, Integer idPtoMuestra, Integer idSubparametro) throws GmdException;
	
	void establecerMarcaMejorRegistro(Integer idRegistroDBO, Integer numMejorValor) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerMejorRegistroDBOES(Integer idRegistro) throws GmdException;
}
