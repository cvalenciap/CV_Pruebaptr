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
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroSolidoDao;
import pe.com.sedapal.scr.core.services.IRegistroSolidoService;

@Service
public class RegistroSolidoServiceImpl implements IRegistroSolidoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroSolidoDao registroSolidoDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoByRegLaboratorio(Integer idRegLab,Integer idTipoSolido) throws GmdException {
		List<RegistroSolidoBean> ltaDetalleGeneral = new ArrayList<RegistroSolidoBean>();
        try {        	
        	ltaDetalleGeneral = registroSolidoDao.obtenerRegistroSolidoByRegLaboratorio(idRegLab,idTipoSolido);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroSolidoBean registrarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		RegistroSolidoBean bean = new RegistroSolidoBean();
		try {        	
			bean = registroSolidoDao.registrarRegistroSolido(RegistroSolido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		try {        	
			registroSolidoDao.actualizarRegistroSolido(RegistroSolido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		try {        	
			registroSolidoDao.anularRegistroSolido(RegistroSolido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoByidSolido(Integer idSolido, Integer idTipoSolido, Date fechaDesde, Date FechaHasta)
			throws GmdException {
		List<RegistroSolidoBean> ltaDetalleGeneral = new ArrayList<RegistroSolidoBean>();
        try {        	
        	ltaDetalleGeneral = registroSolidoDao.obtenerRegistroSolidoByidSolido(idSolido,idTipoSolido,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioSolido(Integer idRegistroLab, Integer idParametro,
			BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroSolidoBean> ltaRegistros = registroSolidoDao.obtenerRegistroSolidoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio(),null);
				if(ltaRegistros != null && ltaRegistros.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagSolido(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }		
	}

	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroSolidoBean> ltaDetalleGeneral = new ArrayList<RegistroSolidoBean>();
        try {        	
        	ltaDetalleGeneral = registroSolidoDao.obtenerRegistroSolidoByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroSolidoBean> ltaDetalleGeneral = new ArrayList<RegistroSolidoBean>();
        try {        	
        	ltaDetalleGeneral = registroSolidoDao.obtenerRegistroSolidoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		try {        	
			registroSolidoDao.aprobarRegistroSolido(RegistroSolido);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroSolido(List<RegistroSolidoBean> ltaRegistroSolido, BaseSptarBean auditoria) throws GmdException{
		try {
			String usuarioModificacion = auditoria.getIdUsuaModi();
			String terminalModificacion = auditoria.getDeTermModi();
			for(RegistroSolidoBean registroSolido : ltaRegistroSolido) {
				registroSolido.setIdUsuaModi(usuarioModificacion);
				registroSolido.setDeTermModi(terminalModificacion);
				registroSolidoDao.anularRegistroSolido(registroSolido);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
