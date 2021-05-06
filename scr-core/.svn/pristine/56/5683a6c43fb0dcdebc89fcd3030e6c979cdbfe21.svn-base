package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.ISubParametroPtarSectorService;


@Service
public class SubParametroPtarSectorServiceImpl implements ISubParametroPtarSectorService {

	/** The generalDao dao. */
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorAsignados(Integer PtarxSector, Integer idParametro)
			throws GmdException {
		List<SubParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<SubParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = subParametroPtarSectorDao.obtenerltaSubParametroPtarSectorAsignados(PtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorNoAsignados(Integer PtarxSector,
			Integer idParametro) throws GmdException {
		List<SubParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<SubParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = subParametroPtarSectorDao.obtenerltaSubParametroPtarSectorNoAsignados(PtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarSubParametroPtarSector(List<SubParametroPtarSectorBean> ltaSubParametroPtarSectorBean,
			Integer idPtarSector, Integer idParametro, BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		try {  
			List<SubParametroPtarSectorBean> ltaDetalleGeneral = subParametroPtarSectorDao.obtenerltaSubParametroPtarSectorAsignados(idPtarSector,idParametro); 
			for(SubParametroPtarSectorBean item : ltaDetalleGeneral) {
				List<SubParametroPtarSectorBean> ltaTemporal = subParametroPtarSectorDao.validaSubParametroPtarSector(item);
				if((ltaTemporal == null || ltaTemporal!= null) && ltaTemporal.size() == 0) {
					subParametroPtarSectorDao.anularSubParametroPtarSector(item);					
				}
			}
			for(SubParametroPtarSectorBean itemPrincipal : ltaSubParametroPtarSectorBean) {
				List<SubParametroPtarSectorBean> ltaPrincipal = subParametroPtarSectorDao.buscarSubParametroPtarSector(itemPrincipal);
				if((ltaPrincipal == null || ltaPrincipal!= null) && ltaPrincipal.size() == 0) {
					indicador = subParametroPtarSectorDao.registrarSubParametroPtarSector(idPtarSector,idParametro,itemPrincipal,auditoria);	
				}
			}
			indicador = ltaSubParametroPtarSectorBean.size();			
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return indicador ;
	}
	
	@Override
	public List<SubParametroPtarSectorBean> obtenerListaSubparametroMicro(Integer idParametroHidrobiologico) throws GmdException{
		List<SubParametroPtarSectorBean> ltaSubParametro = new ArrayList<SubParametroPtarSectorBean>();
        try {        	
        	ltaSubParametro = subParametroPtarSectorDao.obtenerListaSubparametroMicro(idParametroHidrobiologico);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaSubParametro;
	}
	
	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroSecuencial(Integer idPtarPorSector) throws GmdException{
		List<SubParametroPtarSectorBean> ltaSubParametro = new ArrayList<SubParametroPtarSectorBean>();
		try {
			ltaSubParametro = subParametroPtarSectorDao.obtenerltaSubParametroSecuencial(idPtarPorSector);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaSubParametro;		
	}
	
	@SuppressWarnings("unused")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarSubParametroSecuencial(List<SubParametroPtarSectorBean> ltaSubParametroSecuencial, Integer idPtarPorSector, BaseSptarBean auditoria) throws GmdException{
		Integer indicador = 0;
		try {
			List<SubParametroPtarSectorBean> ltaSubParametro = subParametroPtarSectorDao.obtenerltaSubParametroSecuencial(idPtarPorSector);
			subParametroPtarSectorDao.anularSubParametroSecuencial(idPtarPorSector);
			if(ltaSubParametroSecuencial.size() > 0) {
				for(SubParametroPtarSectorBean itemPrincipal : ltaSubParametroSecuencial) {
					itemPrincipal.setIdptarPorSector(idPtarPorSector);
					subParametroPtarSectorDao.registrarSubParametroSecuencial(itemPrincipal, auditoria);
				}
			}
			indicador = ltaSubParametroSecuencial.size();	
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return indicador;
	}
	
	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroSecHistAc() throws GmdException{
		List<SubParametroPtarSectorBean> ltaSubParametro = new ArrayList<SubParametroPtarSectorBean>();
		try {
			ltaSubParametro = subParametroPtarSectorDao.obtenerltaSubParametroSecHistAc();
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaSubParametro;
	}
	
	@SuppressWarnings("unused")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarSubParametroSecHistAc(List<SubParametroPtarSectorBean> ltaSubParametroSecuencial, BaseSptarBean auditoria) throws GmdException{
		Integer indicador = 0;
		try {
			List<SubParametroPtarSectorBean> ltaSubParametro = subParametroPtarSectorDao.obtenerltaSubParametroSecHistAc();
			subParametroPtarSectorDao.anularSubParametroSecHistAc();
			if(ltaSubParametroSecuencial.size() > 0) {
				for(SubParametroPtarSectorBean itemPrincipal : ltaSubParametroSecuencial) {
					subParametroPtarSectorDao.registrarSubParametroSecHistAc(itemPrincipal, auditoria);
				}
			}
			indicador = ltaSubParametroSecuencial.size();	
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return indicador;
	}
	
}
