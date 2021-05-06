package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;

public interface IRegistroDirectoDao {
	
	List<RegistroDirectoBean> obtenerRegistroDirectoByRegLaboratorio(Integer idRegLab) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroDirectoBean registrarRegistroDirecto(RegistroDirectoBean registro) throws GmdException;
	
	void actualizarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
	
	void anularRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoByidDirecto(Integer idDirecto,Date fechaDesde,Date FechaHasta) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;	
	
	void aprobarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerMejorRegistroDirectoES(Integer idRegistro) throws GmdException;
}
