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
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroDirectoDao;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroDirectoService;

@Service
public class RegistroDirectoServiceImpl implements IRegistroDirectoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroDirectoDao registroDirectoDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;
	

	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroDirectoBean> ltaDetalleGeneral = new ArrayList<RegistroDirectoBean>();
        try {        	
        	ltaDetalleGeneral = registroDirectoDao.obtenerRegistroDirectoByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDirectoBean> ltaDetalleGeneral = new ArrayList<RegistroDirectoBean>();
        try {        	
        	ltaDetalleGeneral = registroDirectoDao.obtenerRegistroDirectoByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroDirectoBean registrarRegistroDirecto(RegistroDirectoBean registro) throws GmdException {
		RegistroDirectoBean bean = new RegistroDirectoBean();
		try {        	
			bean = registroDirectoDao.registrarRegistroDirecto(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		try {        	
			registroDirectoDao.actualizarRegistroDirecto(registroDirecto);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroDirectoBean> obtenerRegistroDirectoBySubParametro(Integer idRegLab, Integer subParametro)
			throws GmdException {
		List<RegistroDirectoBean> ltaDetalleGeneral = new ArrayList<RegistroDirectoBean>();
        try {        	
        	ltaDetalleGeneral = registroDirectoDao.obtenerRegistroDirectoBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		try {        	
			registroDirectoDao.anularRegistroDirecto(registroDirecto);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}	
	

	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioDirecto(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroDirectoBean> ltaSubParametroReg = registroDirectoDao.obtenerRegistroDirectoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
				if(ltaSubParametroReg != null && ltaSubParametroReg.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagDirecto(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoByidDirecto(Integer idDirecto, Date fechaDesde,
			Date FechaHasta) throws GmdException {
		List<RegistroDirectoBean> ltaDetalleGeneral = new ArrayList<RegistroDirectoBean>();
        try {        	
        	ltaDetalleGeneral = registroDirectoDao.obtenerRegistroDirectoByidDirecto(idDirecto,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDirectoBean> ltaDetalleGeneral = new ArrayList<RegistroDirectoBean>();
        try {        	
        	ltaDetalleGeneral = registroDirectoDao.obtenerRegistroDirectoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		try {        	
			registroDirectoDao.aprobarRegistroDirecto(registroDirecto);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroDirecto(List<RegistroDirectoBean> ltaRegistroDirecto, BaseSptarBean auditoria) throws GmdException{
		try {
			String usuarioModificacion = auditoria.getIdUsuaModi();
			String terminalModificacion = auditoria.getDeTermModi();
			for(RegistroDirectoBean registroDirecto : ltaRegistroDirecto) {
				registroDirecto.setIdUsuaModi(usuarioModificacion);
				registroDirecto.setDeTermModi(terminalModificacion);
				registroDirectoDao.anularRegistroDirecto(registroDirecto);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	public List<RegistroLabReporteBean> obtenerMejorRegistroDirectoES(Integer idRegistro) throws GmdException{
		List<RegistroLabReporteBean> listaComparativoDirecto = new ArrayList<RegistroLabReporteBean>();
        try {        	
        	listaComparativoDirecto = registroDirectoDao.obtenerMejorRegistroDirectoES(idRegistro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return listaComparativoDirecto;
	}
}
