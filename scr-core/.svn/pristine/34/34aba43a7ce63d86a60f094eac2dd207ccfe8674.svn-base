package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaSeededBean;
import pe.com.sedapal.scr.core.beans.SemillaSeededBean;
import pe.com.sedapal.scr.core.dao.ISemillaSeededDao;
import pe.com.sedapal.scr.core.services.ISemillaSeededService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class SemillaSeededServiceImpl implements ISemillaSeededService {
	
	/** The generalDao dao. */
	@Autowired
	private ISemillaSeededDao SemillaSeededDao;

	@Override
	public List<SemillaSeededBean> obtenerLtaSemillaSeededTodo() throws GmdException {
		List<SemillaSeededBean> ltaDetalleGeneral = new ArrayList<SemillaSeededBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSeededDao.obtenerLtaSemillaSeededTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<SemillaSeededBean> obtenerLtaSemillaSeeded() throws GmdException {
		List<SemillaSeededBean> ltaDetalleGeneral = new ArrayList<SemillaSeededBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSeededDao.obtenerLtaSemillaSeeded();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		try {        	
			SemillaSeededDao.anularSemillaSeeded(SemillaSeeded);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		try {        	
			SemillaSeededDao.actualizarSemillaSeeded(SemillaSeeded);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SemillaSeededBean registrarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		SemillaSeededBean bean = new SemillaSeededBean();
		try {        	
			bean = SemillaSeededDao.registrarSemillaSeeded(SemillaSeeded);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	public List<SemillaSeededBean> calculoListaSeeded(Double valorGGA) throws GmdException {
		List<SemillaSeededBean> ListaFinal = new ArrayList<SemillaSeededBean>();
		try {
			List<SemillaSeededBean> ltaResultado = SemillaSeededDao.obtenerLtaSemillaSeeded(); 
			if(ltaResultado != null && ltaResultado.size() > 0) {
				Double contaSCF = Double.parseDouble("0");
				for (SemillaSeededBean item : ltaResultado) {
					item.setNumValGGa(valorGGA);
					Double valorSCF = ParametrosUtil.formatearDecimales((((item.getNumInicial()- item.getNumFinal())* (300/item.getNumVolumen()))*(valorGGA/300)),4);	
					contaSCF = contaSCF + valorSCF;
					item.setNumValorSCF(valorSCF);
				}
				Double promedio = Double.parseDouble("0");	
				promedio = ParametrosUtil.formatearDecimales((contaSCF/ltaResultado.size()),4);
				for (SemillaSeededBean item : ltaResultado) {
					item.setNumPromedio(promedio);
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
	public void actualizarPromedioSeeded(Double valorGGA, BaseSptarBean auditoria) throws GmdException {
		try {        	
			List<SemillaSeededBean> ltaResultado = new ArrayList<SemillaSeededBean>();
			ltaResultado = calculoListaSeeded(valorGGA);   
			for (SemillaSeededBean bean : ltaResultado) {
				bean.setIdUsuaModi(auditoria.getIdUsuaModi());
				bean.setDeTermModi(auditoria.getDeTermModi());
				bean.setStRegi(auditoria.getStRegi());
				SemillaSeededDao.actualizarSemillaSeeded(bean);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<SemillaSeededBean> obtenerLtaSemillaSeededTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaSeededBean> ltaDetalleGeneral = new ArrayList<SemillaSeededBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSeededDao.obtenerLtaSemillaSeededTodoDBO(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
