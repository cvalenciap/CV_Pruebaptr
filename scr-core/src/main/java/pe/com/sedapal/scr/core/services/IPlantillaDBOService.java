package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PlantillaDBOBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;

public interface IPlantillaDBOService {

	List<PlantillaDBOBean> obtenerRegistroPlantillaDBO(Integer idPtarxSector) throws GmdException;
	
	public void anularPlantillaDBO(PlantillaDBOBean bean) throws GmdException;
	
	PlantillaDBOBean registrarPlantillaDBO(PlantillaDBOBean registro) throws GmdException;
	
	void actualizarPlantillaDBO(PlantillaDBOBean registro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraFaltante(Integer PtarxSector)throws GmdException;
	
	void registrarListaFaltantes(List<PlantillaDBOBean> ltaIngresosFaltantes, BaseSptarBean auditoria) throws GmdException;
	
	List<PlantillaDBOBean> obtenerRegistrosPlantillaSobrante(Integer idPtarSector) throws GmdException;
	
	void eliminarListaSobrantes(List<PlantillaDBOBean> ltaRegistrosSobrantes, BaseSptarBean auditoria) throws GmdException;
	
	void modificarRegistrosAlterados(List<PlantillaDBOBean> ltaRegistrosSobrantes, List<PlantillaDBOBean> ltaIngresosFaltantes, BaseSptarBean auditoria, Integer idPtarSector) throws GmdException;
}
