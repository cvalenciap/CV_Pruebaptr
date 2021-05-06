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
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroParasitologicoDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroParasitologicoService;

@Service
public class RegistroParasitologicoServiceImpl implements IRegistroParasitologicoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroParasitologicoDao registroParasitologicoDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroParasitologicoBean> ltaDetalleGeneral = new ArrayList<RegistroParasitologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroParasitologicoDao.obtenerRegistroParasitologicoByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByPtarSubP(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroParasitologicoBean> ltaDetalleGeneral = new ArrayList<RegistroParasitologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroParasitologicoDao.obtenerRegistroParasitologicoByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroParasitologicoBean registrarRegistroParasitologico(RegistroParasitologicoBean registro) throws GmdException {
		RegistroParasitologicoBean bean = new RegistroParasitologicoBean();
		try {        	
			bean = registroParasitologicoDao.registrarRegistroParasitologico(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
		try {        	
			registroParasitologicoDao.actualizarRegistroParasitologico(registroParasitologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroParasitologicoBean> ltaDetalleGeneral = new ArrayList<RegistroParasitologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroParasitologicoDao.obtenerRegistroParasitologicoBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
		try {        	
			registroParasitologicoDao.anularRegistroParasitologico(registroParasitologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioParasitologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroParasitologicoBean> ltaSubParametroReg = registroParasitologicoDao.obtenerRegistroParasitologicoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
				if(ltaSubParametroReg != null && ltaSubParametroReg.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagParasitologico(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByidParasitologico(Integer idParasitologico, Date fechaDesde,
			Date FechaHasta) throws GmdException {
		List<RegistroParasitologicoBean> ltaDetalleGeneral = new ArrayList<RegistroParasitologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroParasitologicoDao.obtenerRegistroParasitologicoByidParasitologico(idParasitologico,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroParasitologicoBean> ltaDetalleGeneral = new ArrayList<RegistroParasitologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroParasitologicoDao.obtenerRegistroParasitologicoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
		try {        	
			registroParasitologicoDao.aprobarRegistroParasitologico(registroParasitologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroParasitologico(List<RegistroParasitologicoBean> ltaRegistroParasitologico, BaseSptarBean auditoria) throws GmdException{
		try {
			String usuarioModificacion = auditoria.getIdUsuaModi();
			String terminalModificacion = auditoria.getDeTermModi();
			for(RegistroParasitologicoBean registroParasitologico : ltaRegistroParasitologico) {
				registroParasitologico.setIdUsuaModi(usuarioModificacion);
				registroParasitologico.setDeTermModi(terminalModificacion);
				registroParasitologicoDao.anularRegistroParasitologico(registroParasitologico);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
