package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IPuntoMuestraPtarSectorDao;
import pe.com.sedapal.scr.core.services.IPuntoMuestraPtarSectorService;

@Service
public class PuntoMuestraPtarSectorServiceImpl implements IPuntoMuestraPtarSectorService {

	/** The generalDao dao. */
	@Autowired
	private IPuntoMuestraPtarSectorDao puntoMuestraPtarSectorDao;

	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignados(Integer PtarxSector,Integer idParametro,Integer idSubParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraPtarAsignados(PtarxSector,idParametro,idSubParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarNoAsignados(Integer PtarxSector,Integer idParametro,Integer idSubParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraPtarNoAsignados(PtarxSector,idParametro,idSubParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarPuntoMuestraPtar(List<PuntoMuestraPtarSectorBean> ltaPuntoMuestraPtarSectorBean,Integer idPtarSector,Integer idParametro, Integer idSubParametro,
			BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		try {        	
			List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraPtarAsignados(idPtarSector,idParametro,idSubParametro); 
			for (PuntoMuestraPtarSectorBean item : ltaDetalleGeneral) {
				List<PuntoMuestraPtarSectorBean> ltaTemporal = puntoMuestraPtarSectorDao.validaPuntoMuestraPtar(item);
				if((ltaTemporal == null || ltaTemporal!= null) && ltaTemporal.size() == 0) {
					item.setIdParametro(idParametro);
					item.setIdSubParametro(idSubParametro);
					puntoMuestraPtarSectorDao.anularPuntoMuestraPtar(item);					
				}
			}
			for (PuntoMuestraPtarSectorBean itemPrincipal : ltaPuntoMuestraPtarSectorBean) {
				itemPrincipal.setIdParametro(idParametro);
				itemPrincipal.setIdSubParametro(idSubParametro);
				List<PuntoMuestraPtarSectorBean> ltaPrincipal = puntoMuestraPtarSectorDao.buscarPuntoMuestraPtar(itemPrincipal);
				if((ltaPrincipal == null || ltaPrincipal!= null) && ltaPrincipal.size() == 0) {
					itemPrincipal.setIdParametro(idParametro);
					itemPrincipal.setIdSubParametro(idSubParametro);
					indicador = puntoMuestraPtarSectorDao.registrarPuntoMuestraPtar(idPtarSector,itemPrincipal,auditoria);	
				}
			}
			indicador = ltaPuntoMuestraPtarSectorBean.size();	
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return indicador ;
	}

	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametro(Integer PtarxSector,
			Integer idParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraxGrupoParametro(PtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametroMicroSecundario(Integer PtarxSector,Integer idParametro) throws GmdException{
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
		List<PuntoMuestraPtarSectorBean> ltaFinal = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraxGrupoParametro(PtarxSector,idParametro);
        	for(PuntoMuestraPtarSectorBean registroPuntoMuestra : ltaDetalleGeneral) {
        		if(registroPuntoMuestra.getIdPuntoMuestra() == ConstantesUtil.ID_SUBPARA_EP ||registroPuntoMuestra.getIdPuntoMuestra() == ConstantesUtil.ID_SUBPARA_SP) {
        			ltaFinal.add(registroPuntoMuestra);
        		}
        	}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaFinal;
	}
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxPtar(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraxPtar(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignadosMicrobiologico(int PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraPtarAsignadosMicrobiologico(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecuencial(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraSecuencial(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
		
	@SuppressWarnings("unused")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarPuntoMuestraPtarSecuencial(List<PuntoMuestraPtarSectorBean> ltaPuntoMuestraSecuencial, Integer idPtarSector, BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		try {        	
			List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraSecuencial(idPtarSector);
			puntoMuestraPtarSectorDao.anularPuntoMuestraPtarSecuencial(idPtarSector);
			if(ltaPuntoMuestraSecuencial.size()>0) {
				for (PuntoMuestraPtarSectorBean itemPrincipal : ltaPuntoMuestraSecuencial) {
					itemPrincipal.setIdptarPorSector(idPtarSector);
					puntoMuestraPtarSectorDao.registrarPuntoMuestraPtarSecuencial(itemPrincipal, auditoria);
				}
			}
			indicador = ltaPuntoMuestraSecuencial.size();	
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return indicador ;
	}
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecEnlace(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = puntoMuestraPtarSectorDao.obtenerltaPuntoMuestraSecEnlace(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
