package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroAceiteDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroAceiteService;

@Service
public class RegistroAceiteServiceImpl implements IRegistroAceiteService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroAceiteDao registroAceiteDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroAceiteBean> ltaDetalleGeneral = new ArrayList<RegistroAceiteBean>();
        try {        	
        	ltaDetalleGeneral = registroAceiteDao.obtenerRegistroAceiteByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteByPtarSubP(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroAceiteBean> ltaDetalleGeneral = new ArrayList<RegistroAceiteBean>();
        try {        	
        	ltaDetalleGeneral = registroAceiteDao.obtenerRegistroAceiteByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroAceiteBean registrarRegistroAceite(RegistroAceiteBean registro) throws GmdException {
		RegistroAceiteBean bean = new RegistroAceiteBean();
		try {        	
			bean = registroAceiteDao.registrarRegistroAceite(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
		try {        	
			registroAceiteDao.actualizarRegistroAceite(registroAceite);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroAceiteBean> obtenerRegistroAceiteBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroAceiteBean> ltaDetalleGeneral = new ArrayList<RegistroAceiteBean>();
        try {        	
        	ltaDetalleGeneral = registroAceiteDao.obtenerRegistroAceiteBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
		try {        	
			registroAceiteDao.anularRegistroAceite(registroAceite);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioAceite(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroAceiteBean> ltaSubParametroReg = registroAceiteDao.obtenerRegistroAceiteByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
				if(ltaSubParametroReg != null && ltaSubParametroReg.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagAceite(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteByidAceite(Integer idAceite, Date fechaDesde,
			Date FechaHasta) throws GmdException {
		List<RegistroAceiteBean> ltaDetalleGeneral = new ArrayList<RegistroAceiteBean>();
        try {        	
        	ltaDetalleGeneral = registroAceiteDao.obtenerRegistroAceiteByidAceite(idAceite,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroAceiteBean> ltaDetalleGeneral = new ArrayList<RegistroAceiteBean>();
        try {        	
        	ltaDetalleGeneral = registroAceiteDao.obtenerRegistroAceiteSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
		try {        	
			registroAceiteDao.aprobarRegistroAceite(registroAceite);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroAceite(List<RegistroAceiteBean> ltaRegistroAceite, BaseSptarBean auditoria) throws GmdException{
		try {
			String usuarioModificacion = auditoria.getIdUsuaModi();
			String terminalModificacion = auditoria.getDeTermModi();
			for(RegistroAceiteBean registroAceite : ltaRegistroAceite) {
				registroAceite.setIdUsuaModi(usuarioModificacion);
				registroAceite.setDeTermModi(terminalModificacion);
				registroAceiteDao.anularRegistroAceite(registroAceite);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
