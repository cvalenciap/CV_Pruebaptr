package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;
import pe.com.sedapal.scr.core.dao.IFisicoQuimicoDao;
import pe.com.sedapal.scr.core.services.IFisicoQuimicoService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class FisicoQuimicoServiceImpl implements IFisicoQuimicoService {
	
	/** The generalDao dao. */
	@Autowired
	private IFisicoQuimicoDao fisicoQuimicoDao;

	@Override
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimico(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException {
		List<FisicoQuimicoBean> ltaDetalleGeneral = new ArrayList<FisicoQuimicoBean>();
        try {        	
        	ltaDetalleGeneral = fisicoQuimicoDao.obtenerLtaFisicoQuimico(idPtarxSector,fechaInicio,fechaFin);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		try {        	
			fisicoQuimicoDao.anularFisicoQuimico(fisicoQuimicoBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		try {        	
			fisicoQuimicoDao.actualizarFisicoQuimico(fisicoQuimicoBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public FisicoQuimicoBean registrarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		FisicoQuimicoBean bean = new FisicoQuimicoBean();
		try {        	
			bean = fisicoQuimicoDao.registrarFisicoQuimico(fisicoQuimicoBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimicobyFilter(FisicoQuimicoBean fisicoQuimicoBean)
			throws GmdException {
		List<FisicoQuimicoBean> ltaDetalleGeneral = new ArrayList<FisicoQuimicoBean>();
        try {        	
        	ltaDetalleGeneral = fisicoQuimicoDao.obtenerLtaFisicoQuimicobyFilter(fisicoQuimicoBean);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
		
	public List<FisicoQuimicoBean> calculoListaFisicoQuimico(Integer idPtarxSector, Integer idPuntoMuestra, Double volumen,
			String fechaInicio,String fechaFin)throws GmdException {
		List<FisicoQuimicoBean> ListaFinal = new ArrayList<FisicoQuimicoBean>();
		try {        	
			FisicoQuimicoBean busqueda = new FisicoQuimicoBean();
			busqueda.setIdPtarxSector(idPtarxSector);
			busqueda.setIdPuntoMuestra(idPuntoMuestra);
			busqueda.setnNumVolumen(volumen);
			busqueda.setFechaInicioString(fechaInicio);
			busqueda.setFechaFinString(fechaFin);
			busqueda.setFechaInicio(ParametrosUtil.convertirStringtoDateSP(busqueda.getFechaInicioString()));
			busqueda.setFechaFin(ParametrosUtil.convertirStringtoDateSP(busqueda.getFechaFinString()));
			List<FisicoQuimicoBean> ltaResultado = new ArrayList<FisicoQuimicoBean>();
			ltaResultado = fisicoQuimicoDao.obtenerLtaFisicoQuimicobyFilter(busqueda);    
			if(ltaResultado != null && ltaResultado.size() > 0) {
				Double contadorBDO5 = Double.parseDouble("0");
				Double promedio = Double.parseDouble("0");
				for (int i = 0 ; i <  ltaResultado.size() ; i++) {
					contadorBDO5 = contadorBDO5 + ltaResultado.get(i).getnNumDbo5();
				}		
				promedio = ParametrosUtil.formatearDecimales((contadorBDO5/ltaResultado.size()),0);
				for (FisicoQuimicoBean item : ltaResultado) {
					Double PrimerOdi = item.getnNumOdi();
					Double UltimoOdf = item.getnNumOdf();
					Double resultado = ParametrosUtil.formatearDecimales(((UltimoOdf/PrimerOdi)*100),0);
					item.setnNumDbo5Prom(promedio);
					item.setNuDeplec(resultado);
				}
				ListaFinal = ltaResultado;
			}
			
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
		return ListaFinal;
	}
	
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void reCalcularRegistrosFisicoQuimico(Integer idPtarxSector, Integer idPuntoMuestra, Double volumen,BaseSptarBean auditoria,
		    String fechaInicio,String fechaFin)
			throws GmdException {
		try {        	
			List<FisicoQuimicoBean> ltaResultado = new ArrayList<FisicoQuimicoBean>();
			ltaResultado = calculoListaFisicoQuimico(idPtarxSector,idPuntoMuestra,volumen,fechaInicio,fechaFin);   
			for (FisicoQuimicoBean fisicoQuimicoBean : ltaResultado) {
				fisicoQuimicoBean.setIdUsuaModi(auditoria.getIdUsuaModi());
				fisicoQuimicoBean.setDeTermModi(auditoria.getDeTermModi());
				fisicoQuimicoBean.setStRegi(auditoria.getStRegi());
				fisicoQuimicoDao.actualizarFisicoQuimico(fisicoQuimicoBean);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
	}

	@Override
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimicoTodo(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException {
		List<FisicoQuimicoBean> ltaDetalleGeneral = new ArrayList<FisicoQuimicoBean>();
        try {        	
        	ltaDetalleGeneral = fisicoQuimicoDao.obtenerLtaFisicoQuimicoTodo(idPtarxSector,fechaInicio,fechaFin);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<DetalleGeneralBean> obtenerFechasFisicoQuimicoTodo(Integer idPtarxSerctor) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = fisicoQuimicoDao.obtenerFechasFisicoQuimicoTodo(idPtarxSerctor);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<DetalleGeneralBean> obtenerFechasFisicoQuimico(Integer idPtarxSerctor) throws GmdException {
		List<DetalleGeneralBean> ltaDetalleGeneral = new ArrayList<DetalleGeneralBean>();
        try {        	
        	ltaDetalleGeneral = fisicoQuimicoDao.obtenerFechasFisicoQuimico(idPtarxSerctor);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void registarCopiaLtaFisicoQuimicoTodo(List<FisicoQuimicoBean> ltaDataCopiar, Integer idPtarxSector,
			String fechaInicio, String fechaFin, BaseSptarBean auditoria) throws GmdException {
		 try {
				for (FisicoQuimicoBean fisicoQuimicoBean : ltaDataCopiar) {
					fisicoQuimicoBean.setIdFisicoQuimico(null);
					fisicoQuimicoBean.setIdPtarxSector(idPtarxSector);
					fisicoQuimicoBean.setFechaInicioString(fechaInicio);
					fisicoQuimicoBean.setFechaFinString(fechaFin);
					fisicoQuimicoBean.setFechaInicio(ParametrosUtil.convertirStringtoDateSP(fisicoQuimicoBean.getFechaInicioString()));
					fisicoQuimicoBean.setFechaFin(ParametrosUtil.convertirStringtoDateSP(fisicoQuimicoBean.getFechaFinString()));					
					fisicoQuimicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
					fisicoQuimicoBean.setDeTermCrea(auditoria.getDeTermCrea());
					fisicoQuimicoBean.setStRegi(auditoria.getStRegi());
					fisicoQuimicoDao.registrarFisicoQuimico(fisicoQuimicoBean);
				}
		 } catch (Exception excepcion) {
	           throw new GmdException(excepcion);
	     }   
	}
}
