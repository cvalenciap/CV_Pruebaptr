package pe.com.sedapal.scr.core.services;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;

public interface IRegistroMicrobiologicoSecundarioService {
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByRegLaboratorio(Integer idRegLab, Integer idTipoMicrobiologico) throws GmdException;	
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarSubP(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	RegistroMicrobiologicoBean registrarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	void actualizarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;

	void anularRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByidMicrobiologico(Integer idMicrobiologico,Date fechaDesde,Date FechaHasta)throws GmdException;
	
	public void actualizarEstadoRegistroLaboratorioMicrobiologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria, Integer idSubParametro) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer idPtarxSector, Integer idParametro) throws GmdException;
	
	Integer grabarListaItemRegistroMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico, BaseSptarBean auditoria, String usuarioLogueado, String flagValidador) throws GmdException;
	
	void anularListaItemRegistroMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico, BaseSptarBean auditoria, String usuarioLogueado) throws GmdException;
	
	void aprobarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException;
	
	void eliminarGrupoRegistroMicrobiologico(Integer idRegistroLaboratorio, Integer grupo, BaseSptarBean auditoria, Integer idSubParametro) throws GmdException;
	
	void actualizarMejorValorListaMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologicoAux) throws GmdException;
}