package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;

public interface ISubParametroPtarSectorService {
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorAsignados(Integer PtarxSector,Integer idParametro) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorNoAsignados(Integer PtarxSector,Integer idParametro) throws GmdException;
			
	Integer registrarSubParametroPtarSector(List<SubParametroPtarSectorBean>  ltaSubParametroPtarSectorBean,Integer idPtarSector,Integer idParametro,BaseSptarBean auditoria) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerListaSubparametroMicro(Integer idParametroHidrobiologico) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroSecuencial(Integer idPtarPorSector) throws GmdException;
	
	Integer registrarSubParametroSecuencial(List<SubParametroPtarSectorBean> ltaSubParametroSecuencial, Integer idPtarPorSector, BaseSptarBean auditoria) throws GmdException;
	
	List<SubParametroPtarSectorBean> obtenerltaSubParametroSecHistAc() throws GmdException;
	
	Integer registrarSubParametroSecHistAc(List<SubParametroPtarSectorBean> ltaSubParametroSecuencial, BaseSptarBean auditoria) throws GmdException;
}
