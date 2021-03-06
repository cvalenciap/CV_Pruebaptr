package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroDBODao;
import pe.com.sedapal.scr.core.services.IRegistroDBOService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class RegistroDBOServiceImpl implements IRegistroDBOService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroDBODao registroDBODao;
	
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;

	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroDBOBean> ltaDetalleGeneral = new ArrayList<RegistroDBOBean>();
        try {        	
        	ltaDetalleGeneral = registroDBODao.obtenerRegistroDBOByRegLaboratorio(idRegLab);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDBOBean> ltaDetalleGeneral = new ArrayList<RegistroDBOBean>();
        try {        	
        	ltaDetalleGeneral = registroDBODao.obtenerRegistroDBOByPtarSubP(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroDBOBean registrarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		RegistroDBOBean bean = new RegistroDBOBean();
		try {        	
			bean = registroDBODao.registrarRegistroDBO(RegistroDBO);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		try {        	
			registroDBODao.actualizarRegistroDBO(RegistroDBO);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroDBO(Integer idGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException {
		try {        	
			registroDBODao.anularRegistroDBO(idGrupoRegistroDBO, idRegistroLaboratorio, auditoria);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularMultipleRegistroDBO(Integer[] listaGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException {
		try {
			for (Integer idGrupoRegistroDBO : listaGrupoRegistroDBO) {
				registroDBODao.anularRegistroDBO(idGrupoRegistroDBO, idRegistroLaboratorio, auditoria);
			}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOByidDBO(Integer idDBO, Date fechaDesde, Date FechaHasta)
			throws GmdException {
		List<RegistroDBOBean> ltaDetalleGeneral = new ArrayList<RegistroDBOBean>();
        try {        	
        	ltaDetalleGeneral = registroDBODao.obtenerRegistroDBOByidDBO(idDBO,fechaDesde,FechaHasta);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarEstadoRegistroLaboratorioDBO(Integer idRegistroLab, Integer idParametro,
			BaseSptarBean auditoria) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {			
			RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLab);
			if(beanOriginal != null) {
				List<RegistroDBOBean> ltaRegistros = registroDBODao.obtenerRegistroDBOByRegLaboratorio(beanOriginal.getIdRegistroLaboratorio());
				if(ltaRegistros != null && ltaRegistros.size() > 0) {
					estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
				}else {
					estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
				}
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagDBO(estadoRegistro);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);	
			}			
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDBOBean> ltaDetalleGeneral = new ArrayList<RegistroDBOBean>();
        try {        	
        	ltaDetalleGeneral = registroDBODao.obtenerRegistroDBOSubParametros(idPtarxSector,idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFechasRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		try {        	
			registroDBODao.actualizarFechasRegistroDBO(RegistroDBO);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroBDO(RegistroDBOBean RegistroDBO) throws GmdException {
		try {        	
			registroDBODao.aprobarRegistroBDO(RegistroDBO);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer grabarListaItemRegistroDBO(List<RegistroDBOBean> ltaRegistroDBO, BaseSptarBean auditoria, String flagValidador) throws GmdException {		
		Integer idRegistroLab = null;
		try {
			for (RegistroDBOBean registroDBOBean : ltaRegistroDBO) {
				registroDBOBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroDBOBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroDBOBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroDBOBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroDBOBean.setDeTermModi(auditoria.getDeTermCrea());
				registroDBOBean.setIdParametro(ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO);	
//				registroDBOBean.setFechaInicial(ParametrosUtil.convertirStringtoDateSP(registroDBOBean.getFechaInicialString()));
				registroDBOBean.setFechaInicial(ParametrosUtil.convertStringToTimestamp(registroDBOBean.getFechaInicialString(), ConstantesUtil.FORMATO_24_HORAS_DATE));
				registroDBOBean.setFechaVencimiento(ParametrosUtil.convertirStringtoDateSP(registroDBOBean.getFechaVencimientoString()));				
				registroDBOBean.setFechaMonitoreo(registroDBOBean.getFechaInicial());
				registroDBOBean.setFechaRegDato(registroDBOBean.getFechaInicial());
				registroDBOBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroDBOBean.setFlagAprobador(flagValidador);
				registroDBOBean.setDescripcionObservacion(" ");			
				if(registroDBOBean.getNumValorSemilla() != null) {
					registroDBOBean.setNumValorSemilla(ParametrosUtil.formatearDecimales(registroDBOBean.getNumValorSemilla(),2));
				}else {
					registroDBOBean.setNumValorSemilla(ParametrosUtil.formatearDecimales(0.00,0));
				}
				if(registroDBOBean.getNumDepletion() == null) {
					registroDBOBean.setNumDepletion(0.00); 
				}else {
					registroDBOBean.setNumDepletion(ParametrosUtil.formatearDecimales(registroDBOBean.getNumDepletion(),4));
				}
				if(registroDBOBean.getBlankPromedio() == null) {
					registroDBOBean.setBlankPromedio(0.00);
				}else {
					registroDBOBean.setBlankPromedio(ParametrosUtil.formatearDecimales(registroDBOBean.getBlankPromedio(),4));
				}
				if(registroDBOBean.getSeededPorDeplec() == null) {
					registroDBOBean.setSeededPorDeplec(0.00); 
				}else {
					registroDBOBean.setSeededPorDeplec(ParametrosUtil.formatearDecimales(registroDBOBean.getSeededPorDeplec(),4));
				}
				if(registroDBOBean.getSeededBOD() == null) {
					registroDBOBean.setSeededBOD(0.00); 
				}else {
					registroDBOBean.setSeededBOD(ParametrosUtil.formatearDecimales(registroDBOBean.getSeededBOD(),4));
				}
				if(registroDBOBean.getSeededSCF() == null) {
					registroDBOBean.setSeededSCF(0.00); 
				}else {
					registroDBOBean.setSeededSCF(ParametrosUtil.formatearDecimales(registroDBOBean.getSeededSCF(),4));
				}
				if(registroDBOBean.getSeededPromedio() == null) {
					registroDBOBean.setSeededPromedio(0.00); 
				}else {
					registroDBOBean.setSeededPromedio(ParametrosUtil.formatearDecimales(registroDBOBean.getSeededPromedio(),4));
				}
				if(registroDBOBean.getGGANetDeplec() == null) {
					registroDBOBean.setGGANetDeplec(0.00); 
				}else {
					registroDBOBean.setGGANetDeplec(ParametrosUtil.formatearDecimales(registroDBOBean.getGGANetDeplec(),4));
				}
				if(registroDBOBean.getGGAPorDeplec() == null) {
					registroDBOBean.setGGAPorDeplec(0.00); 
				}else {
					registroDBOBean.setGGAPorDeplec(ParametrosUtil.formatearDecimales(registroDBOBean.getGGAPorDeplec(),4));
				}
				if(registroDBOBean.getGGADBO() == null) {
					registroDBOBean.setGGADBO(0.00); 
				}else {
					registroDBOBean.setGGADBO(ParametrosUtil.formatearDecimales(registroDBOBean.getGGADBO(),4));
				}
				if(registroDBOBean.getGGAPromedio() == null) {
					registroDBOBean.setGGAPromedio(0.00); 
				}else {
					registroDBOBean.setGGAPromedio(ParametrosUtil.formatearDecimales(registroDBOBean.getGGAPromedio(),4));
				}
				if(registroDBOBean.getSamplesNetDeplec() == null) {
					registroDBOBean.setSamplesNetDeplec(0.00); 
				}else {
					registroDBOBean.setSamplesNetDeplec(ParametrosUtil.formatearDecimales(registroDBOBean.getSamplesNetDeplec(),4));
				}
				if(registroDBOBean.getSamplesDilFactor() == null) {
					registroDBOBean.setSamplesDilFactor(0.00); 
				}else {
					registroDBOBean.setSamplesDilFactor(ParametrosUtil.formatearDecimales(registroDBOBean.getSamplesDilFactor(),4));
				}
				if(registroDBOBean.getSamplesBOD() == null) {
					registroDBOBean.setSamplesBOD(0.00); 
				}else {
					registroDBOBean.setSamplesBOD(ParametrosUtil.formatearDecimales(registroDBOBean.getSamplesBOD(),4));
				}
				if(registroDBOBean.getSamplesPromedio() == null) {
					registroDBOBean.setSamplesPromedio(0.00); 
				}else {
					registroDBOBean.setSamplesPromedio(ParametrosUtil.formatearDecimales(registroDBOBean.getSamplesPromedio(),4));
				}
//				inicio obs
//				establecer valor inicial
				if(StringUtils.isEmpty(registroDBOBean.getIndicadorTipoSemilla())) {
					registroDBOBean.setFlagMejorValor(ConstantesUtil.FLAG_MARCA_MEJOR_VALOR);
				}else {
					registroDBOBean.setFlagMejorValor(ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				}
//				fin obs
				if(registroDBOBean.getIdDBO() != null) {
					actualizarRegistroDBO(registroDBOBean);
					idRegistroLab = registroDBOBean.getIdRegistroLaboratorio();
				}else {
					registrarRegistroDBO(registroDBOBean);
					idRegistroLab = registroDBOBean.getIdRegistroLaboratorio();
				}
			}
//			inicio obs
//			asignar mejor valor final
			for(RegistroDBOBean registroDBOBean : ltaRegistroDBO) {
				if(StringUtils.isEmpty(registroDBOBean.getIndicadorTipoSemilla())) {
					if(registroDBOBean.getIdPtoEnlace() != null) {
						registroDBODao.obtenerMejorRegistroEnlace(registroDBOBean.getIdRegistroLaboratorio(), registroDBOBean.getIdPuntoMuestra(), registroDBOBean.getIdSubParametro());
					}else {
						registroDBODao.obtenerMejorRegistroNoEnlace(registroDBOBean.getIdRegistroLaboratorio(), registroDBOBean.getIdPuntoMuestra(), registroDBOBean.getIdSubParametro());
					}
				}
			}
//			fin obs
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return idRegistroLab;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarMejorValorInicial(List<RegistroDBOBean> ltaRegistroDBO) throws GmdException{
		try {
			for(RegistroDBOBean registroDBO : ltaRegistroDBO) {
				if(StringUtils.isEmpty(registroDBO.getIndicadorTipoSemilla())) {
					registroDBODao.establecerMarcaMejorRegistro(registroDBO.getIdDBO(), ConstantesUtil.FLAG_MARCA_MEJOR_VALOR);
				}else {
					registroDBODao.establecerMarcaMejorRegistro(registroDBO.getIdDBO(), ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				}
			}
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarMejorValor(List<RegistroDBOBean> ltaRegistroDBO) throws GmdException{
		try {
			for(RegistroDBOBean registroDBO : ltaRegistroDBO) {
				if(StringUtils.isEmpty(registroDBO.getIndicadorTipoSemilla())) {
					if(registroDBO.getIdPtoEnlace() != null) {
						registroDBODao.obtenerMejorRegistroEnlace(registroDBO.getIdRegistroLaboratorio(), registroDBO.getIdPuntoMuestra(), registroDBO.getIdSubParametro());
					}else {
						registroDBODao.obtenerMejorRegistroNoEnlace(registroDBO.getIdRegistroLaboratorio(), registroDBO.getIdPuntoMuestra(), registroDBO.getIdSubParametro());
					}
				}
			}
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	public List<RegistroLabReporteBean> obtenerMejorRegistroDBOES(Integer idRegistro) throws GmdException{
		List<RegistroLabReporteBean> listaComparativoDBO = new ArrayList<RegistroLabReporteBean>();
        try {        	
        	listaComparativoDBO = registroDBODao.obtenerMejorRegistroDBOES(idRegistro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return listaComparativoDBO;
	}
}
