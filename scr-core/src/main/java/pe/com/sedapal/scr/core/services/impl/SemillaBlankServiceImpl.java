package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;
import pe.com.sedapal.scr.core.beans.SemillaBlankBean;
import pe.com.sedapal.scr.core.dao.ISemillaBlankDao;
import pe.com.sedapal.scr.core.services.ISemillaBlankService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class SemillaBlankServiceImpl implements ISemillaBlankService {
	
	/** The generalDao dao. */
	@Autowired
	private ISemillaBlankDao SemillaBlankDao;

	@Override
	public List<SemillaBlankBean> obtenerLtaSemillaBlankTodo() throws GmdException {
		List<SemillaBlankBean> ltaDetalleGeneral = new ArrayList<SemillaBlankBean>();
        try {        	
        	ltaDetalleGeneral = SemillaBlankDao.obtenerLtaSemillaBlankTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<SemillaBlankBean> obtenerLtaSemillaBlank() throws GmdException {
		List<SemillaBlankBean> ltaDetalleGeneral = new ArrayList<SemillaBlankBean>();
        try {        	
        	ltaDetalleGeneral = SemillaBlankDao.obtenerLtaSemillaBlank();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		try {        	
			SemillaBlankDao.anularSemillaBlank(SemillaBlank);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		try {        	
			SemillaBlankDao.actualizarSemillaBlank(SemillaBlank);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SemillaBlankBean registrarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		SemillaBlankBean bean = new SemillaBlankBean();
		try {        	
			bean = SemillaBlankDao.registrarSemillaBlank(SemillaBlank);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	public List<SemillaBlankBean> calculoListaBlanks() throws GmdException {
		List<SemillaBlankBean> ListaFinal = new ArrayList<SemillaBlankBean>();
		try {
			List<SemillaBlankBean> ltaResultado = SemillaBlankDao.obtenerLtaSemillaBlank(); 
			if(ltaResultado != null && ltaResultado.size() > 0) {
				Double contaDepletion = Double.parseDouble("0");
				Double promedio = Double.parseDouble("0");
				for (int i = 0 ; i <  ltaResultado.size() ; i++) {
					contaDepletion = contaDepletion + ltaResultado.get(i).getNumDepletion();
				}
				promedio = ParametrosUtil.formatearDecimales((contaDepletion/ltaResultado.size()),4);
				for (SemillaBlankBean item : ltaResultado) {
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
	public void actualizarPromedioBlanks(BaseSptarBean auditoria) throws GmdException {
		try {        	
			List<SemillaBlankBean> ltaResultado = new ArrayList<SemillaBlankBean>();
			ltaResultado = calculoListaBlanks();   
			for (SemillaBlankBean bean : ltaResultado) {
				bean.setIdUsuaModi(auditoria.getIdUsuaModi());
				bean.setDeTermModi(auditoria.getDeTermModi());
				bean.setStRegi(auditoria.getStRegi());
				SemillaBlankDao.actualizarSemillaBlank(bean);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
	}

	@Override
	public List<SemillaBlankBean> obtenerLtaSemillaBlankDBO(Integer idRegLab) throws GmdException {
		List<SemillaBlankBean> ltaDetalleGeneral = new ArrayList<SemillaBlankBean>();
        try {        	
        	ltaDetalleGeneral = SemillaBlankDao.obtenerLtaSemillaBlankDBO(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
}
