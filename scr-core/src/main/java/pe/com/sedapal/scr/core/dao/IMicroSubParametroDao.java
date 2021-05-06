package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.MicroPorSubParametroBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;

public interface IMicroSubParametroDao {
	
	List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroAsignados(int idPtarPorSector,int idParametro,int idSubParametro) throws GmdException;
	
	List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroNoAsignados(int idPtarPorSector,int idParametro,int idSubParametro) throws GmdException;
		
	void anularAllMicroPorSubParametro(int idPtarPorSector,int idParametro,int idSubParametro) throws GmdException; 
	
	Integer registrarMicroPorSubParametro(int idPtarPorSector,int idParametro,int idSubParametro,MicroPorSubParametroBean  ltaMicroPorSubParametro,BaseSptarBean auditoria) throws GmdException;	
	
	void anularMicroPorSubParametro(MicroPorSubParametroBean punto) throws GmdException;
	
	List<MicroPorSubParametroBean> validaMicroPorSubParametro(MicroPorSubParametroBean punto) throws GmdException;
	
	List<MicroPorSubParametroBean> buscarMicroPorSubParametro(MicroPorSubParametroBean punto) throws GmdException;
}
