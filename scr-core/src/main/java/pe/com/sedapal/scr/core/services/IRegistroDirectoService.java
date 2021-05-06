package pe.com.sedapal.scr.core.services;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;

public interface IRegistroDirectoService {
	
	List<RegistroDirectoBean> obtenerRegistroDirectoByRegLaboratorio(Integer idRegLab) throws GmdException;	
	
	List<RegistroDirectoBean> obtenerRegistroDirectoByPtarSubP(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	RegistroDirectoBean registrarRegistroDirecto(RegistroDirectoBean registro) throws GmdException;
	
	void actualizarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
	
	void anularRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	public List<RegistroDirectoBean> obtenerRegistroDirectoByidDirecto(Integer idDirecto,Date fechaDesde,Date FechaHasta)throws GmdException;
		
	public void actualizarEstadoRegistroLaboratorioDirecto(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException;
	
	List<RegistroDirectoBean> obtenerRegistroDirectoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;	
	
	void aprobarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException;
	
	void anularMultipleRegistroDirecto(List<RegistroDirectoBean> ltaRegistroDirecto, BaseSptarBean auditoria) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerMejorRegistroDirectoES(Integer idRegistro) throws GmdException;
}
