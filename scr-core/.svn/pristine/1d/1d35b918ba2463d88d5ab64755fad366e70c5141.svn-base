package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.MicroPorSubParametroBean;

public interface IMicroSubParametroService {
	
	List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroAsignados(int idPtarPorSector,int idParametro,int idSubParametro) throws GmdException;
	
	List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroNoAsignados(int idPtarPorSector,int idParametro,int idSubParametro) throws GmdException;
			
	Integer registrarMicroPorSubParametro(List<MicroPorSubParametroBean>  ltaMicroPorSubParametro,int idPtarPorSector,int idParametro,int idSubParametro,BaseSptarBean auditoria) throws GmdException;	

}
