package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaSamplesBean;
import pe.com.sedapal.scr.core.dao.ISemillaSamplesDao;
import pe.com.sedapal.scr.core.services.ISemillaSamplesService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;


@Service
public class SemillaSamplesServiceImpl implements ISemillaSamplesService {
	
	/** The generalDao dao. */
	@Autowired
	private ISemillaSamplesDao SemillaSamplesDao;

	@Override
	public List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodo() throws GmdException {
		List<SemillaSamplesBean> ltaDetalleGeneral = new ArrayList<SemillaSamplesBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSamplesDao.obtenerLtaSemillaSamplesTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<SemillaSamplesBean> obtenerLtaSemillaSamples() throws GmdException {
		List<SemillaSamplesBean> ltaDetalleGeneral = new ArrayList<SemillaSamplesBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSamplesDao.obtenerLtaSemillaSamples();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		try {        	
			SemillaSamplesDao.anularSemillaSamples(SemillaSamples);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		try {        	
			SemillaSamplesDao.actualizarSemillaSamples(SemillaSamples);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SemillaSamplesBean registrarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		SemillaSamplesBean bean = new SemillaSamplesBean();
		try {        	
			bean = SemillaSamplesDao.registrarSemillaSamples(SemillaSamples);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	public Double calcularPromedioDBO5(List<SemillaSamplesBean> listaAuxiliar,Double volumenSample) {
		Double contadorSampleBOD = Double.parseDouble("0");
		Double promedio = Double.parseDouble("0");
		int tamanio = 0;
		for (SemillaSamplesBean item : listaAuxiliar) {
			if(item.getNumVolSample().equals(volumenSample)){
				contadorSampleBOD = contadorSampleBOD + item.getNumSampleBOD();
				tamanio++;
			}
		}
		if(tamanio > 0) {
			promedio = ParametrosUtil.formatearDecimales((contadorSampleBOD / tamanio),2);
		}		
		return promedio;
	}
	public List<SemillaSamplesBean> calculoListaSamples(Double promedioSCF) throws GmdException {
		List<SemillaSamplesBean> ListaFinal = new ArrayList<SemillaSamplesBean>();
		try {
			List<SemillaSamplesBean> ltaResultado = SemillaSamplesDao.obtenerLtaSemillaSamples(); 
			if(ltaResultado != null && ltaResultado.size() > 0) {
				for (SemillaSamplesBean item : ltaResultado) {
					item.setNumValorSCF(promedioSCF);
					item.setNumValorNetDep(ParametrosUtil.formatearDecimales(((item.getNumInicial() - item.getNumFinal()) - item.getNumValorSCF()),2));
					item.setNumDilcFactor(ParametrosUtil.formatearDecimales((item.getNumVolSample()/300),2));
					item.setNumSampleBOD(ParametrosUtil.formatearDecimales((((item.getNumInicial() - item.getNumFinal()) - item.getNumValorSCF())/(item.getNumVolSample()/300)),2));;
				}
				List<SemillaSamplesBean> ListaClonada = new ArrayList<SemillaSamplesBean>();
				ListaClonada = ltaResultado;
				
				for (SemillaSamplesBean item : ltaResultado) {
					item.setNumPromedioDBO(calcularPromedioDBO5(ListaClonada,item.getNumVolSample()));
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
	public void actualizarInformacionSamples(Double promedioSCF, BaseSptarBean auditoria) throws GmdException {
		try {        	
			List<SemillaSamplesBean> ltaResultado = new ArrayList<SemillaSamplesBean>();
			ltaResultado = calculoListaSamples(promedioSCF);   
			for (SemillaSamplesBean bean : ltaResultado) {
				bean.setIdUsuaModi(auditoria.getIdUsuaModi());
				bean.setDeTermModi(auditoria.getDeTermModi());
				bean.setStRegi(auditoria.getStRegi());
				SemillaSamplesDao.actualizarSemillaSamples(bean);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaSamplesBean> ltaDetalleGeneral = new ArrayList<SemillaSamplesBean>();
        try {        	
        	ltaDetalleGeneral = SemillaSamplesDao.obtenerLtaSemillaSamplesTodoDBO(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
