package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;

public interface IParametroPtarSectorDao {
	
	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignados(int idPtarPorSector) throws GmdException;
	
	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorNoAsignados(int idPtarPorSector) throws GmdException;
		
	void anularAllParametroPtarSector(int idPtarPorSector) throws GmdException; 
	
	Integer registrarParametroPtarSector(int idPtarPorSector, ParametroPtarSectorBean  item,BaseSptarBean auditoria) throws GmdException;	
	
	void anularParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException;
	
	Integer anularParametroPtarSectorTotal(ParametroPtarSectorBean punto) throws GmdException;
	
	List<ParametroPtarSectorBean> validaParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException;
	
	List<ParametroPtarSectorBean> buscarParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException;
	
	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignadosMenu(Integer idPtarPorSector,Integer idAnalista) throws GmdException;
}
