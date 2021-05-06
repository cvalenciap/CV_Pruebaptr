package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.dao.IParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IParametroPtarSectorService;

@Service
public class ParametroPtarSectorServiceImpl implements IParametroPtarSectorService {

	/** The generalDao dao. */
	@Autowired
	private IParametroPtarSectorDao parametroPtarSectorDao;

	@Override
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignados(int PtarxSector) throws GmdException {
		List<ParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<ParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorNoAsignados(int PtarxSector) throws GmdException {
		List<ParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<ParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = parametroPtarSectorDao.obtenerltaParametroPtarSectorNoAsignados(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer[] registrarParametroPtarSector(List<ParametroPtarSectorBean> ltaParametroPtarSectorBean,
			Integer idPtarSector, BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		Integer resultadoAnularTotal = 0;
		try {
			List<ParametroPtarSectorBean> ltaDetalleGeneral = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarSector); 
			for(ParametroPtarSectorBean item : ltaDetalleGeneral) {
				Integer resultadoAnulacionRegistro = parametroPtarSectorDao.anularParametroPtarSectorTotal(item);
				if(resultadoAnulacionRegistro == 1) {
					resultadoAnularTotal = 1;
				}
			}
			for(ParametroPtarSectorBean itemPrincipal : ltaParametroPtarSectorBean) {
				List<ParametroPtarSectorBean> ltaPrincipal = parametroPtarSectorDao.buscarParametroPtarSector(itemPrincipal);
				if((ltaPrincipal == null || ltaPrincipal!= null) && ltaPrincipal.size() == 0) {
					indicador = parametroPtarSectorDao.registrarParametroPtarSector(idPtarSector,itemPrincipal,auditoria);	
				}
			}
			indicador = ltaParametroPtarSectorBean.size();
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return new Integer[] {indicador, resultadoAnularTotal};
	}

	@Override
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignadosMenu(Integer idPtarPorSector,Integer idAnalista)
			throws GmdException {
		List<ParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<ParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignadosMenu(idPtarPorSector,idAnalista);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
