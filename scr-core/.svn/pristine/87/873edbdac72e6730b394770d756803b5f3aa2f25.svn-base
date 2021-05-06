package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;

public interface IParametroPtarSectorService {
	
	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignados(int PtarxSector) throws GmdException;
	
	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorNoAsignados(int PtarxSector) throws GmdException;
			
	Integer[] registrarParametroPtarSector(List<ParametroPtarSectorBean>  ltaParametroPtarSectorBean,Integer idPtarSector,BaseSptarBean auditoria) throws GmdException;	

	List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignadosMenu(Integer idPtarPorSector,Integer idAnalista) throws GmdException;
}
