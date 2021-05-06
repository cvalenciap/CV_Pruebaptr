package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroMicrobiologicoService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class RegistroMicrobiologicoServiceImpl implements IRegistroMicrobiologicoService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroMicrobiologicoDao registroMicrobiologicoDao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarSubP(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByPtarSubP(idPtarxSector,idParametro);    
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
			bean = registroMicrobiologicoDao.registrarRegistroMicrobiologico(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoDao.actualizarRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoBySubParametro(idRegLab,subParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoDao.anularRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioMicrobiologico(Integer idRegistroLab,Integer idParametro,BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroMicrobiologicoBean> ltaSubParametroReg = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
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
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByidMicrobiologico(idMicrobiologico,fechaDesde,FechaHasta);    
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
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByPtarPuntoMuestra(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestraSecundario(Integer idPtarxSector, Integer idParametro) throws GmdException {
		List<RegistroMicrobiologicoBean> ltaDetalleGeneral = new ArrayList<RegistroMicrobiologicoBean>();
		List<RegistroMicrobiologicoBean> ltaFinal = new ArrayList<RegistroMicrobiologicoBean>();
        try {        	
        	ltaDetalleGeneral = registroMicrobiologicoDao.obtenerRegistroMicrobiologicoByPtarPuntoMuestra(idPtarxSector,idParametro);
        	for(RegistroMicrobiologicoBean registroMicrobiologico : ltaDetalleGeneral) {
        		if(registroMicrobiologico.getIdPuntoMuestra() == ConstantesUtil.ID_SUBPARA_EP ||registroMicrobiologico.getIdPuntoMuestra() == ConstantesUtil.ID_SUBPARA_SP) {
        			ltaFinal.add(registroMicrobiologico);
        		}
        	}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaFinal;
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
				registroMicrobiologicoBean.setIdSubParametro(0);
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
				actualizarEstadoRegistroLaboratorioMicrobiologico(idLab,ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO,auditoria);
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		try {        	
			registroMicrobiologicoDao.aprobarRegistroMicrobiologico(registroMicrobiologico);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void eliminarGrupoRegistroMicrobiologico(Integer idRegistroLaboratorio, Integer grupo, BaseSptarBean auditoria) throws GmdException{
		try {        	
			registroMicrobiologicoDao.eliminarGrupoRegistroMicrobiologico(idRegistroLaboratorio, grupo, auditoria);
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
				registroMicrobiologicoDao.actualizarMejorValorMicrobiologico(registroMicrobiologico);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
}
