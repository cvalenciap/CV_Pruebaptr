package pe.com.sedapal.scr.core.services;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;

public interface IRegistroDBOService {
	
	List<RegistroDBOBean> obtenerRegistroDBOByRegLaboratorio(Integer idRegLab) throws GmdException;	

	List<RegistroDBOBean> obtenerRegistroDBOByPtarSubP(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	RegistroDBOBean registrarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
	
	void actualizarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
		
	void anularRegistroDBO(Integer idGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException;
	
	void anularMultipleRegistroDBO(Integer[] listaGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException;
	
	public List<RegistroDBOBean> obtenerRegistroDBOByidDBO(Integer idDBO,Date fechaDesde,Date FechaHasta)throws GmdException;
	
	public void actualizarEstadoRegistroLaboratorioDBO(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException;
	
	List<RegistroDBOBean> obtenerRegistroDBOSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void actualizarFechasRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException;
	
	void aprobarRegistroBDO(RegistroDBOBean RegistroDBO) throws GmdException;
	
	Integer grabarListaItemRegistroDBO(List<RegistroDBOBean> ltaRegistroDBO, BaseSptarBean auditoria, String flagValidador) throws GmdException;
	
	void actualizarMejorValorInicial(List<RegistroDBOBean> ltaRegistroDBO) throws GmdException;
	
	void actualizarMejorValor(List<RegistroDBOBean> ltaRegistroDBO) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerMejorRegistroDBOES(Integer idRegistro) throws GmdException;
}
