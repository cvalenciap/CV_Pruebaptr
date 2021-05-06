package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;

public interface IRegistroParasitologicoDao {
	
	List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByRegLaboratorio(Integer idRegLab) throws GmdException;
	
	List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroParasitologicoBean registrarRegistroParasitologico(RegistroParasitologicoBean registro) throws GmdException;
	
	void actualizarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException;
	
	List<RegistroParasitologicoBean> obtenerRegistroParasitologicoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
	
	void anularRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException;
	
	List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByidParasitologico(Integer idParasitologico,Date fechaDesde,Date FechaHasta) throws GmdException;
	
	List<RegistroParasitologicoBean> obtenerRegistroParasitologicoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void aprobarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException;
}
