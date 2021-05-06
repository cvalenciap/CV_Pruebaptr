package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.MicroPorSubParametroBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.dao.IMicroSubParametroDao;
import pe.com.sedapal.scr.core.services.IMicroSubParametroService;

@Service
public class MicroSubParametroServiceImpl implements IMicroSubParametroService {

	/** The generalDao dao. */
	@Autowired
	private IMicroSubParametroDao microSubParametroDao;

	@Override
	public List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroAsignados(int idPtarPorSector, int idParametro,
			int idSubParametro) throws GmdException {
		List<MicroPorSubParametroBean> ltaDetalleGeneral = new ArrayList<MicroPorSubParametroBean>();
        try {        	
        	ltaDetalleGeneral = microSubParametroDao.obtenerltaMicroPorSubParametroAsignados(idPtarPorSector,idParametro,idSubParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroNoAsignados(int idPtarPorSector,
			int idParametro, int idSubParametro) throws GmdException {
		List<MicroPorSubParametroBean> ltaDetalleGeneral = new ArrayList<MicroPorSubParametroBean>();
        try {        	
        	ltaDetalleGeneral = microSubParametroDao.obtenerltaMicroPorSubParametroNoAsignados(idPtarPorSector,idParametro,idSubParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public Integer registrarMicroPorSubParametro(List<MicroPorSubParametroBean> ltaMicroPorSubParametro,
			int idPtarPorSector, int idParametro, int idSubParametro, BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		try {      
			List<MicroPorSubParametroBean> ltaDetalleGeneral = microSubParametroDao.obtenerltaMicroPorSubParametroAsignados(idPtarPorSector, idParametro, idSubParametro); 
			for(MicroPorSubParametroBean item : ltaDetalleGeneral) {
				List<MicroPorSubParametroBean> ltaTemporal = microSubParametroDao.validaMicroPorSubParametro(item);
				if((ltaTemporal == null || ltaTemporal!= null) && ltaTemporal.size() == 0) {
					microSubParametroDao.anularMicroPorSubParametro(item);					
				}
			}
			for(MicroPorSubParametroBean itemPrincipal : ltaMicroPorSubParametro) {
				List<MicroPorSubParametroBean> ltaPrincipal = microSubParametroDao.buscarMicroPorSubParametro(itemPrincipal);
				if((ltaPrincipal == null || ltaPrincipal!= null) && ltaPrincipal.size() == 0) {
					indicador = microSubParametroDao.registrarMicroPorSubParametro(idPtarPorSector, idParametro, idSubParametro, itemPrincipal, auditoria);
				}
			}
			indicador = ltaMicroPorSubParametro.size();
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return indicador ;
	}
}
