package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.dao.IPlantaTratamientoDao;
import pe.com.sedapal.scr.core.services.IPlantaTratamientoService;

@Service
public class PlantaTratamientoServiceImpl implements IPlantaTratamientoService {
	
	/** The generalDao dao. */
	@Autowired
	private IPlantaTratamientoDao plantaTratamientoDao;

	@Override
	public List<PlantaTratamientoBean> obtenerPlantaTratamiento() throws GmdException {
		List<PlantaTratamientoBean> ltaDetalleGeneral = new ArrayList<PlantaTratamientoBean>();
        try {        	
        	ltaDetalleGeneral = plantaTratamientoDao.obtenerPlantaTratamiento();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<PlantaTratamientoBean> obtenerPlantaTratamientoxPtarSector(Integer idPtar) throws GmdException {
		List<PlantaTratamientoBean> ltaDetalleGeneral = new ArrayList<PlantaTratamientoBean>();
        try {        	
        	ltaDetalleGeneral = plantaTratamientoDao.obtenerPlantaTratamientoxPtarSector(idPtar);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
