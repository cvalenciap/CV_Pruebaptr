package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;

public interface ISubParametroPtarSectorDao {
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorAsignados(Integer idPtarPorSector,Integer idParametro) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorNoAsignados(Integer idPtarPorSector,Integer idParametro) throws GmdException;
		
	void anularAllSubParametroPtarSector(Integer idPtarPorSector,Integer idParametro) throws GmdException; 
	
	Integer registrarSubParametroPtarSector(Integer idPtarPorSector,Integer idParametro,SubParametroPtarSectorBean item,BaseSptarBean auditoria) throws GmdException;	
	
    void anularSubParametroPtarSector(SubParametroPtarSectorBean punto) throws GmdException;
	
	List<SubParametroPtarSectorBean> validaSubParametroPtarSector(SubParametroPtarSectorBean punto) throws GmdException;
	
	List<SubParametroPtarSectorBean> buscarSubParametroPtarSector(SubParametroPtarSectorBean punto) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerListaSubparametroMicro(Integer idParametroHidrobiologico) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroSecuencial(Integer idPtarPorSector) throws GmdException;
	
	void anularSubParametroSecuencial(Integer idPtarPorSector) throws GmdException;
	
	void registrarSubParametroSecuencial(SubParametroPtarSectorBean item, BaseSptarBean auditoria) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroSecHistAc() throws GmdException;
	
	void registrarSubParametroSecHistAc(SubParametroPtarSectorBean item, BaseSptarBean auditoria) throws GmdException;
	
	void anularSubParametroSecHistAc() throws GmdException;	
}
