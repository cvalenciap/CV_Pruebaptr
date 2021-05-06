package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;
import pe.com.sedapal.scr.core.dao.ISemillaStandardDao;
import pe.com.sedapal.scr.core.services.ISemillaStandardService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class SemillaStandardServiceImpl implements ISemillaStandardService {
	
	/** The generalDao dao. */
	@Autowired
	private ISemillaStandardDao SemillaStandardDao;

	@Override
	public List<SemillaStandardBean> obtenerLtaSemillaStandardTodo() throws GmdException {
		List<SemillaStandardBean> ltaDetalleGeneral = new ArrayList<SemillaStandardBean>();
        try {        	
        	ltaDetalleGeneral = SemillaStandardDao.obtenerLtaSemillaStandardTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<SemillaStandardBean> obtenerLtaSemillaStandard() throws GmdException {
		List<SemillaStandardBean> ltaDetalleGeneral = new ArrayList<SemillaStandardBean>();
        try {        	
        	ltaDetalleGeneral = SemillaStandardDao.obtenerLtaSemillaStandard();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		try {        	
			SemillaStandardDao.anularSemillaStandard(SemillaStandard);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		try {        	
			SemillaStandardDao.actualizarSemillaStandard(SemillaStandard);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SemillaStandardBean registrarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		SemillaStandardBean bean = new SemillaStandardBean();
		try {        	
			bean = SemillaStandardDao.registrarSemillaStandard(SemillaStandard);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	public List<SemillaStandardBean> calculoListaStandard(Double promedioSCF) throws GmdException {
		List<SemillaStandardBean> ListaFinal = new ArrayList<SemillaStandardBean>();
		try {
			List<SemillaStandardBean> ltaResultado = SemillaStandardDao.obtenerLtaSemillaStandard(); 
			if(ltaResultado != null && ltaResultado.size() > 0) {
				for (SemillaStandardBean item : ltaResultado) {
					item.setNumNetDep(ParametrosUtil.formatearDecimales((item.getNumDepletion() - promedioSCF),2));
					item.setNumPorcentajeDeplec(ParametrosUtil.formatearDecimales((((item.getNumDepletion() - promedioSCF)/item.getNumInicial())*100),1));
					item.setNumValorBOD(ParametrosUtil.formatearDecimales(((item.getNumDepletion() - promedioSCF)*(300/item.getNumVolGGa())),1));
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
	public void actualizarInformacionStandard(Double promedioSCF, BaseSptarBean auditoria) throws GmdException {
		try {        	
			List<SemillaStandardBean> ltaResultado = new ArrayList<SemillaStandardBean>();
			ltaResultado = calculoListaStandard(promedioSCF);   
			for (SemillaStandardBean bean : ltaResultado) {
				bean.setIdUsuaModi(auditoria.getIdUsuaModi());
				bean.setDeTermModi(auditoria.getDeTermModi());
				bean.setStRegi(auditoria.getStRegi());
				SemillaStandardDao.actualizarSemillaStandard(bean);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<SemillaStandardBean> obtenerLtaSemillaStandardTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaStandardBean> ltaDetalleGeneral = new ArrayList<SemillaStandardBean>();
        try {        	
        	ltaDetalleGeneral = SemillaStandardDao.obtenerLtaSemillaStandardTodoDBO(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
