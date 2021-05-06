package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;

public interface IPuntoMuestraPtarSectorDao {
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignados(Integer idPtarPorSector,Integer idParametro,Integer idSubParametro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarNoAsignados(Integer idPtarPorSector,Integer idParametro,Integer idSubParametro) throws GmdException;
		
	void anularAllPuntoMuestraPtar(Integer idPtarPorSector) throws GmdException; 
	
	Integer registrarPuntoMuestraPtar(Integer idPtarPorSector,PuntoMuestraPtarSectorBean  bean,BaseSptarBean auditoria) throws GmdException;	
	
	void anularPuntoMuestraPtar(PuntoMuestraPtarSectorBean punto) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> validaPuntoMuestraPtar(PuntoMuestraPtarSectorBean punto) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> buscarPuntoMuestraPtar(PuntoMuestraPtarSectorBean punto) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametro(Integer PtarxSector,Integer idParametro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxPtar(Integer PtarxSector) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignadosMicrobiologico(int idPtarPorSector) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecuencial(Integer idPtarxSector) throws GmdException;
	
	void anularPuntoMuestraPtarSecuencial(Integer idPtarxSector) throws GmdException;
	
	void registrarPuntoMuestraPtarSecuencial(PuntoMuestraPtarSectorBean itemPrincipal, BaseSptarBean auditoria) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecEnlace(Integer idPtarxSector) throws GmdException;
}
