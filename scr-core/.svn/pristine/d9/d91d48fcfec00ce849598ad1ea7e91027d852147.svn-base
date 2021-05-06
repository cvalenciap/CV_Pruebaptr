package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PlantillaDBOBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;

public interface IPlantillaDBODao {
	
	List<PlantillaDBOBean> obtenerRegistroPlantillaDBO(Integer idPtarxSector) throws GmdException;
	
	public void anularPlantillaDBO(PlantillaDBOBean bean) throws GmdException;
	
	PlantillaDBOBean registrarPlantillaDBO(PlantillaDBOBean registro) throws GmdException;
	
	void actualizarPlantillaDBO(PlantillaDBOBean registro) throws GmdException;
	
	List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraFaltante(Integer idPtarPorSector) throws GmdException;
	
	List<PlantillaDBOBean> obtenerRegistrosPlantillaSobrante(Integer idPtarSector) throws GmdException;
}
