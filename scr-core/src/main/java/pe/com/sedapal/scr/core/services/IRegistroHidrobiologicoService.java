package pe.com.sedapal.scr.core.services;

import java.sql.Date;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.GeneralBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;

public interface IRegistroHidrobiologicoService {
	
	List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByRegLaboratorio(Integer idRegLab) throws GmdException;	
	
	List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByPtarSubP(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	RegistroHidrobiologicoBean registrarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException;
	
	void actualizarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException;
	
	List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoBySubParametro(Integer idRegLab,Integer subParametro) throws GmdException;
		
	void anularRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException;
	
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByidHidrobiologico(Integer idHidrobiologico,Date fechaDesde,Date FechaHasta)throws GmdException;
	
	public void actualizarEstadoRegistroLaboratorioHidrobiologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException;
	
	List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoSubParametros(Integer idPtarxSector,Integer idParametro) throws GmdException;
	
	void aprobarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException;
	
	void anularMultipleRegistroHidrobiologico(List<RegistroHidrobiologicoBean> ltaRegistroHidrobiologico, BaseSptarBean auditoria) throws GmdException;
}

