package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoSecundarioDao;
import pe.com.sedapal.scr.core.services.IRegistroMicrobiologicoSecundarioService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class RegistroMicrobiologicoSecundarioServiceImpl implements IRegistroMicrobiologicoSecundarioService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroMicrobiologicoSecundarioDao registroMicrobiologicoSecundarioDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	 private IRegistroMicrobiologicoDao registroMicrobiologicoDao;

	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByRegLaboratorio(Integer idRegLab, Integer idTipoMicrobiologico) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegLab, idTipoMicrobiologico);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarSubP(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroMicrobiologicoBean registrarRegistroMicrobiologico(RegistroMicrobiologicoBean registro) throws GmdException {
		RegistroMicrobiologicoBean bean = new RegistroMicrobiologicoBean();
		try {        	
			bean = registroMicrobiologicoSecundarioDao.registrarRegistroMicrobiologico(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoSecundarioDao.actualizarRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoSecundarioDao.anularRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioMicrobiologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria, Integer idSubParametro) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroMicrobiologicoBean> ltaSubParametroReg = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio(), idSubParametro);//
				if(ltaSubParametroReg != null && ltaSubParametroReg.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagMicrobiologico(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByidMicrobiologico(Integer idMicrobiologico, Date fechaDesde,
			Date FechaHasta) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoByidMicrobiologico(idMicrobiologico,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoSecundarioDao.obtenerRegistroMicrobiologicoByPtarPuntoMuestra(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer grabarListaItemRegistroMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico, BaseSptarBean auditoria, String usuarioLogueado, String flagValidador) throws GmdException {		
		Integer idRegistroLab = null;
		try {
			for (RegistroMicrobiologicoBean registroMicrobiologicoBean : ltaRegistroMicrobiologico) {
				registroMicrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroMicrobiologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdUsuaModi(usuarioLogueado);
				registroMicrobiologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdParametro(ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO);
				registroMicrobiologicoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroMicrobiologicoBean.getFechaMonitoreoString()));
				registroMicrobiologicoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroMicrobiologicoBean.getFechaRegDatoString()));
				registroMicrobiologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroMicrobiologicoBean.setFlagAprobador(flagValidador);
				registroMicrobiologicoBean.setDescripcionObservacion(" ");
				if(registroMicrobiologicoBean.getIdRegMicrobiologico() != null) {
					actualizarRegistroMicrobiologico(registroMicrobiologicoBean);
					idRegistroLab = registroMicrobiologicoBean.getIdRegLaboratorio();
				}else {
					registrarRegistroMicrobiologico(registroMicrobiologicoBean);
					idRegistroLab = registroMicrobiologicoBean.getIdRegLaboratorio();
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return idRegistroLab;
	}
		
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularListaItemRegistroMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologico, BaseSptarBean auditoria, String usuarioLogueado) throws GmdException {
		try {
			for (RegistroMicrobiologicoBean registroMicrobiologicoBean : ltaRegistroMicrobiologico) {
				registroMicrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_INACTIVO);
				registroMicrobiologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdUsuaModi(usuarioLogueado);
				registroMicrobiologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				List<RegistroMicrobiologicoBean> lta = obtenerRegistroMicrobiologicoByidMicrobiologico(registroMicrobiologicoBean.getIdRegMicrobiologico(),null,null);
				Integer idLab = null;
				if(lta != null && lta.size() > 0) {
					idLab = lta.get(0).getIdRegLaboratorio();
				}
				anularRegistroMicrobiologico(registroMicrobiologicoBean);
				actualizarEstadoRegistroLaboratorioMicrobiologico(idLab,ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO,auditoria, registroMicrobiologicoBean.getIdSubParametro());
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoSecundarioDao.aprobarRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void eliminarGrupoRegistroMicrobiologico(Integer idRegistroLaboratorio, Integer grupo, BaseSptarBean auditoria, Integer idSubParametro) throws GmdException{
		try {        	
			registroMicrobiologicoSecundarioDao.eliminarGrupoRegistroMicrobiologico(idRegistroLaboratorio, grupo, auditoria, idSubParametro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarMejorValorListaMicrobiologico(List<RegistroMicrobiologicoBean> ltaRegistroMicrobiologicoAux) throws GmdException{
		try {        	
			for(RegistroMicrobiologicoBean registroMicrobiologico : ltaRegistroMicrobiologicoAux) {
				if(registroMicrobiologico.getFlagMejorValor() == null) {
					registroMicrobiologico.setFlagMejorValor(ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				}
				registroMicrobiologicoSecundarioDao.actualizarMejorValorMicrobiologico(registroMicrobiologico);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
