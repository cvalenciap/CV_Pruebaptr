package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;

public interface IRegistroSolidoDao {
	
	List<RegistroSolidoBean> obtenerRegistroSolidoByRegLaboratorio(Integer idRegLab,Integer idTipoSolido) throws GmdException;	
	
	List<RegistroSolidoBean> obtenerRegistroSolidoByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroSolidoBean registrarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException;
	
	void actualizarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException;
	
	void anularRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException;
	
	public List<RegistroSolidoBean> obtenerRegistroSolidoByidSolido(Integer idSolido,Integer idTipoSolido,Date fechaDesde,Date FechaHasta)throws GmdException;
	
	List<RegistroSolidoBean> obtenerRegistroSolidoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void aprobarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException;
}
