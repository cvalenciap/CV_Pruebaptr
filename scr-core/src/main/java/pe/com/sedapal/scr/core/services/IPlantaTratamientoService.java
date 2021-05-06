package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;

public interface IPlantaTratamientoService {
	
	List<PlantaTratamientoBean> obtenerPlantaTratamiento() throws GmdException;
	
	List<PlantaTratamientoBean> obtenerPlantaTratamientoxPtarSector(Integer idPtar) throws GmdException;
}
