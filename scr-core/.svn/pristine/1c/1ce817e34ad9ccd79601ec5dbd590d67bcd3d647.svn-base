package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;

public interface IRegistroMicrobiologicoDao {
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByRegLaboratorio(Integer idRegLab) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarSubP(Integer idPtarxSector,Integer idSubParametro) throws GmdException;
	
	RegistroMicrobiologicoBean registrarRegistroMicrobiologico(RegistroMicrobiologicoBean registro) throws GmdException;
	
	void actualizarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
	
	void anularRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByidMicrobiologico(Integer idMicrobiologico,Date fechaDesde,Date FechaHasta) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer idPtarxSector, Integer idParametro) throws GmdException;
	
	void aprobarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	void eliminarGrupoRegistroMicrobiologico(Integer idRegistroLaboratorio, Integer grupo, BaseSptarBean auditoria) throws GmdException;
	
	void actualizarMejorValorMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
}
