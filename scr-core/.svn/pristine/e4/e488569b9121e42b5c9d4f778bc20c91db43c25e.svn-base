package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;

public interface IRegistroAceiteDao {
	
	List<RegistroAceiteBean> obtenerRegistroAceiteByRegLaboratorio(Integer idRegLab) throws GmdException;
	
	List<RegistroAceiteBean> obtenerRegistroAceiteByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroAceiteBean registrarRegistroAceite(RegistroAceiteBean registro) throws GmdException;
	
	void actualizarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException;
	
	List<RegistroAceiteBean> obtenerRegistroAceiteBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
	
	void anularRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException;
	
	List<RegistroAceiteBean> obtenerRegistroAceiteByidAceite(Integer idAceite,Date fechaDesde,Date FechaHasta) throws GmdException;
	
	List<RegistroAceiteBean> obtenerRegistroAceiteSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void aprobarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException;	
}
