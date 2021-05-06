package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroHidrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroHidrobiologicoService;

@Service
public class RegistroHidrobiologicoServiceImpl implements IRegistroHidrobiologicoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroHidrobiologicoDao registroHidrobiologicoDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroHidrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroHidrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroHidrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroHidrobiologicoBean registrarRegistroHidrobiologico(RegistroHidrobiologicoBean registro) throws GmdException {
		RegistroHidrobiologicoBean bean = new RegistroHidrobiologicoBean();
		try {        	
			bean = registroHidrobiologicoDao.registrarRegistroHidrobiologico(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
		try {        	
			registroHidrobiologicoDao.actualizarRegistroHidrobiologico(registroHidrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoBySubParametro(Integer idRegLab, Integer subParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroHidrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
		try {        	
			registroHidrobiologicoDao.anularRegistroHidrobiologico(registroHidrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioHidrobiologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroHidrobiologicoBean> ltaSubParametroReg = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
				if(ltaSubParametroReg != null && ltaSubParametroReg.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagHidrobiologico(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByidHidrobiologico(Integer idHidrobiologico, Date fechaDesde, Date FechaHasta) throws GmdException {
		List<RegistroHidrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroHidrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoByidHidrobiologico(idHidrobiologico,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroHidrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroHidrobiologicoDao.obtenerRegistroHidrobiologicoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
		try {        	
			registroHidrobiologicoDao.aprobarRegistroHidrobiologico(registroHidrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroHidrobiologico(List<RegistroHidrobiologicoBean> ltaRegistroHidrobiologico, BaseSptarBean auditoria) throws GmdException{
		try {
			String usuarioModificacion = auditoria.getIdUsuaModi();
			String terminalModificacion = auditoria.getDeTermModi();
			for(RegistroHidrobiologicoBean registroHidrobiologico : ltaRegistroHidrobiologico) {
				registroHidrobiologico.setIdUsuaModi(usuarioModificacion);
				registroHidrobiologico.setDeTermModi(terminalModificacion);
				registroHidrobiologicoDao.anularRegistroHidrobiologico(registroHidrobiologico);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
