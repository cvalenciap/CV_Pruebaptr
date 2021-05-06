package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;

public interface IPuntoMuestraPtarSectorService {
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignados(Integer PtarxSector,Integer idParametro,Integer idSubParametro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarNoAsignados(Integer PtarxSector,Integer idParametro,Integer idSubParametro) throws GmdException;
			
	Integer registrarPuntoMuestraPtar(List<PuntoMuestraPtarSectorBean>  ltaPuntoMuestraPtarSectorBean,Integer idPtarSector,Integer idParametro, Integer idSubParametro,BaseSptarBean auditoria) throws GmdException;	
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametro(Integer PtarxSector,Integer idParametro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxPtar(Integer PtarxSector) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignadosMicrobiologico(int PtarxSector) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecuencial(Integer idPtarPorSector) throws GmdException;
	
	Integer registrarPuntoMuestraPtarSecuencial(List<PuntoMuestraPtarSectorBean> ltaPuntoMuestraSecuencial, Integer idPtarSector, BaseSptarBean auditoria) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecEnlace(Integer idPtarPorSector) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametroMicroSecundario(Integer PtarxSector,Integer idParametro) throws GmdException;
}
