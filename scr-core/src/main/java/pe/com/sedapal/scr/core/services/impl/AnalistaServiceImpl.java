package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IAnalistaDao;
import pe.com.sedapal.scr.core.services.IAnalistaService;

@Service
public class AnalistaServiceImpl implements IAnalistaService {
	
	/** The generalDao dao. */
	@Autowired
	private IAnalistaDao analistaDao;

	@Override
	public List<AnalistaBean> obtenerAnalista() throws GmdException {
		List<AnalistaBean> ltaDetalleGeneral = new ArrayList<AnalistaBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerAnalista();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAnalista(AnalistaBean analistaBean) throws GmdException {
		try {        	
			analistaDao.anularAnalista(analistaBean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarAnalista(AnalistaBean analistaBean) throws GmdException {
		try {        	
			analistaDao.actualizarAnalista(analistaBean);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(analistaBean.getIdUsuaModi());
			auditoria.setDeTermCrea(analistaBean.getDeTermModi());
			registrarParametrosxAnalista(analistaBean.getListaParametrosAsignados(),analistaBean.getIdAnalista(), auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public AnalistaBean registrarAnalista(AnalistaBean analistaBean) throws GmdException {
		AnalistaBean bean = new AnalistaBean();
		try {        	
			bean = analistaDao.registrarAnalista(analistaBean);
			BaseSptarBean auditoria = new BaseSptarBean();
			auditoria.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
			auditoria.setIdUsuaCrea(bean.getIdUsuaCrea());
			auditoria.setDeTermCrea(bean.getDeTermCrea());
			registrarParametrosxAnalista(analistaBean.getListaParametrosAsignados(),analistaBean.getIdAnalista(), auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<TrabajadorBean> obtenerTrabajador(String numeroFicha) throws GmdException {
		List<TrabajadorBean> ltaDetalleGeneral = new ArrayList<TrabajadorBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerTrabajador(numeroFicha);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<AnalistaBean> obtenerAnalistabyFiltro(AnalistaBean analistaBean) throws GmdException {
		List<AnalistaBean> ltaDetalleGeneral = new ArrayList<AnalistaBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerAnalistabyFiltro(analistaBean);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<AnalistaBean> obtenerAnalistaTodo() throws GmdException {
		List<AnalistaBean> ltaDetalleGeneral = new ArrayList<AnalistaBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerAnalistaTodo();    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<AnalistaBean> obtenerAnalistabyUsuario(String codigoUsuario) throws GmdException {
		List<AnalistaBean> ltaDetalleGeneral = new ArrayList<AnalistaBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerAnalistabyUsuario(codigoUsuario);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<ParametroPtarSectorBean> obtenerParametrosxAnalistaAsignados(Integer idAnalista) throws GmdException {
		List<ParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<ParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerParametrosxAnalistaAsignados(idAnalista);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<ParametroPtarSectorBean> obtenerParametrosxAnalistaNoAsignados(Integer idAnalista) throws GmdException {
		List<ParametroPtarSectorBean> ltaDetalleGeneral = new ArrayList<ParametroPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = analistaDao.obtenerParametrosxAnalistaNoAsignados(idAnalista);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarParametrosxAnalista(List<ParametroPtarSectorBean> ltaParametrosxAnalista,
			Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		Integer  indicador = 0 ;
		try {        
			if(idAnalista != null) {
				analistaDao.anularAllParametrosxAnalista(idAnalista);
				for (ParametroPtarSectorBean itemPrincipal : ltaParametrosxAnalista) {
					itemPrincipal.setIdAnalista(idAnalista);
					analistaDao.registrarParametrosxAnalista(idAnalista,itemPrincipal,auditoria);
				}
				if(ltaParametrosxAnalista != null) {
					indicador = ltaParametrosxAnalista.size();
				}else {
					indicador = 0;
				}
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }  
		return indicador ;
	}
}
