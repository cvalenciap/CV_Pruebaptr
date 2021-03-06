package pe.com.sedapal.scr.core.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IParametroPtarSectorDao;
import pe.com.sedapal.scr.core.dao.IRegistroAceiteDao;
import pe.com.sedapal.scr.core.dao.IRegistroDBODao;
import pe.com.sedapal.scr.core.dao.IRegistroDirectoDao;
import pe.com.sedapal.scr.core.dao.IRegistroHidrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoSecundarioDao;
import pe.com.sedapal.scr.core.dao.IRegistroParasitologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroSolidoDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroLaboratorioService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class RegistroLaboratorioServiceImpl implements IRegistroLaboratorioService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private ISubParametroPtarSectorDao subParametroPtarSectorDao;

	@Autowired
	private IRegistroDirectoDao registroDirectoDao;
	
	@Autowired
	private IRegistroParasitologicoDao registroParasitologicoDao;
	
	@Autowired
	private IRegistroHidrobiologicoDao registroHidrobiologicoDao;
	
	@Autowired
	private IRegistroMicrobiologicoDao registroMicrobiologicoDao;
	
	@Autowired
	private IRegistroMicrobiologicoSecundarioDao registroMicrobiologicoSecundarioDao;
	
	@Autowired
	private IRegistroAceiteDao registroAceiteDao;
	
	@Autowired
	private IParametroPtarSectorDao parametroPtarSectorDao;
	
	@Autowired
	private IRegistroSolidoDao registroSolidoDao;
	
	@Autowired
	private IRegistroDBODao registroDBODao;
		
	@Override
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin, String tipoConsulta) throws GmdException {
		List<RegistroLaboratorioBean> ltaDetalleGeneral = new ArrayList<RegistroLaboratorioBean>();
        try {
        	if(tipoConsulta.equals(ConstantesUtil.TIPO_CONSULTA_FECHA_REGISTRO)) {
        		ltaDetalleGeneral = registroLaboratorioDao.obtenerLtaRegistroLaboratorioTipo(idPtarxSector, idEstado, fechaInicio, fechaFin, ConstantesUtil.FLAG_FECHA_REGISTRO);
        	}else if(tipoConsulta.equals(ConstantesUtil.TIPO_CONSULTA_FECHA_MONITOREO)) {
        		ltaDetalleGeneral = registroLaboratorioDao.obtenerLtaRegistroLaboratorioTipo(idPtarxSector, idEstado, fechaInicio, fechaFin, ConstantesUtil.FLAG_FECHA_MONITOREO);
        	}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioEmpty(String idPtarxSector, String fechaInicio, String fechaFin, String idEstado) throws GmdException {
		List<RegistroLaboratorioBean> ltaDetalleGeneral = new ArrayList<RegistroLaboratorioBean>();
        try {        	
        	ltaDetalleGeneral = registroLaboratorioDao.obtenerLtaRegistroLaboratorioEmpty(idPtarxSector,fechaInicio, fechaFin, idEstado);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {        	
			bean = registroLaboratorioDao.registrarRegistroLaboratorio(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException {
		try {
			registroLaboratorioDao.actualizarRegistroLaboratorio(registro);
		} catch (Exception excepcion) {
	        throw new GmdException(excepcion);
	    }
	}	
	
	@Transactional(rollbackFor=GmdException.class)
	public Integer consultarEstadoRegistro(Integer idPtarxSector ,Integer idParametro, List<RegistroDirectoBean> listaRegDirecto) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			if(listaRegDirecto != null && listaRegDirecto.size() > 0) {
				estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
			}else {
				estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return estadoRegistro;
	}	
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroDirecto(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro,
			List<RegistroDirectoBean> listaRegDirecto,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroDirecto) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoDirecto = consultarEstadoRegistro(idPtarxSector,idParametro,listaRegDirecto);
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagDirecto(estadoDirecto);
				beanOriginal.setObservacionDirecto(observacionRegistroDirecto);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagDirecto(estadoDirecto);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(observacionRegistroDirecto);
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroDirectoBean registroDirectoBean : listaRegDirecto) {
				registroDirectoBean.setIdRegLaboratorio(idRegistro);
				registroDirectoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroDirectoBean.getFechaMonitoreoString()));
				registroDirectoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroDirectoBean.getFechaRegDatoString()));
				registroDirectoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroDirectoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroDirectoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroDirectoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroDirectoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroDirectoBean.setIdPtarxSector(idPtarxSector);
				registroDirectoBean.setIdParametro(idParametro);
				registroDirectoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroDirectoBean.setFlagAprobador(String.valueOf(flagValidador));	
				registroDirectoBean.setDescripcionObservacion(" ");
				registroDirectoBean.setNumProfundida(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumProfundida(),2));
				registroDirectoBean.setNumValor(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumValor(),2));
				if(registroDirectoBean.getNumFactor() != null) {
					registroDirectoBean.setNumFactor(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumFactor(),2));
					registroDirectoBean.setNumValorDQO(ParametrosUtil.formatearDecimales((registroDirectoBean.getNumValor() * registroDirectoBean.getNumFactor()) ,2));
				}else {
					registroDirectoBean.setNumFactor(ParametrosUtil.formatearDecimales(0.00,0));
					registroDirectoBean.setNumValorDQO(ParametrosUtil.formatearDecimales(registroDirectoBean.getNumValor(),2));
				}
				
				if(registroDirectoBean.getIdRegDirecto() != null) {
					//existe y es update
					registroDirectoDao.actualizarRegistroDirecto(registroDirectoBean);
				}else {
					//no existe y es insert
					registroDirectoDao.registrarRegistroDirecto(registroDirectoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public RegistroLaboratorioBean obtenerRegistroLaboratorio(Integer idRegistroLaboratorio) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {        	
			bean = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistroLaboratorio);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void cambiarFlagAprobacion(List<RegistroLaboratorioBean> ltaRegistro, Integer idCambioEstado, BaseSptarBean auditoria) throws GmdException {
		try {
			for(RegistroLaboratorioBean bean : ltaRegistro)
			{
				bean.setStRegi(auditoria.getStRegi());
				bean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				bean.setDeTermCrea(auditoria.getDeTermCrea());
				registroLaboratorioDao.cambiarFlagAprobacion(bean, idCambioEstado);
			}
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
	}
	
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroParasitologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, 
			List<RegistroParasitologicoBean> listaRegParasitologico,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroParasitologico) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoParasitologico = consultarEstadoRegistroParasitologico(idPtarxSector,idParametro,listaRegParasitologico);
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagParasitologico(estadoParasitologico);
				beanOriginal.setObservacionParasitologico(observacionRegistroParasitologico);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(estadoParasitologico);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(observacionRegistroParasitologico);
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroParasitologicoBean registroParasitologicoBean : listaRegParasitologico) {
				registroParasitologicoBean.setIdRegLaboratorio(idRegistro);
				registroParasitologicoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroParasitologicoBean.getFechaMonitoreoString()));
				registroParasitologicoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroParasitologicoBean.getFechaRegDatoString()));
				registroParasitologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroParasitologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroParasitologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroParasitologicoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroParasitologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroParasitologicoBean.setIdPtarxSector(idPtarxSector);
				registroParasitologicoBean.setIdParametro(idParametro);
				registroParasitologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroParasitologicoBean.setFlagAprobador(String.valueOf(flagValidador));
				registroParasitologicoBean.setNumCantidad(ParametrosUtil.formatearDecimales(registroParasitologicoBean.getNumCantidad(),2));
				
				if(registroParasitologicoBean.getIdRegParasitologico() != null) {
					//existe y es update
					registroParasitologicoDao.actualizarRegistroParasitologico(registroParasitologicoBean);
				}else {
					//no existe y es insert
					registroParasitologicoDao.registrarRegistroParasitologico(registroParasitologicoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	
	@Transactional(rollbackFor=GmdException.class)
	public Integer consultarEstadoRegistroParasitologico(Integer idPtarxSector ,Integer idParametro, List<RegistroParasitologicoBean> listaRegParasitologico) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			if(listaRegParasitologico != null && listaRegParasitologico.size() > 0) {
				estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
			}else {
				estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return estadoRegistro;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroHidrobiologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, 
			List<RegistroHidrobiologicoBean> listaRegHidrobiologico,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroHidrobiologico) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoHidrobiologico = consultarEstadoRegistroHidrobiologico(idPtarxSector,idParametro,listaRegHidrobiologico);
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagHidrobiologico(estadoHidrobiologico);
				beanOriginal.setObservacionHidrobiologico(observacionRegistroHidrobiologico);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(estadoHidrobiologico);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(observacionRegistroHidrobiologico);
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroHidrobiologicoBean registroHidrobiologicoBean : listaRegHidrobiologico) {
				registroHidrobiologicoBean.setIdRegLaboratorio(idRegistro);
				registroHidrobiologicoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroHidrobiologicoBean.getFechaMonitoreoString()));
				registroHidrobiologicoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroHidrobiologicoBean.getFechaRegDatoString()));
				registroHidrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroHidrobiologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroHidrobiologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroHidrobiologicoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroHidrobiologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroHidrobiologicoBean.setIdPtarxSector(idPtarxSector);
				registroHidrobiologicoBean.setIdParametro(idParametro);
				registroHidrobiologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroHidrobiologicoBean.setFlagAprobador(String.valueOf(flagValidador));
				registroHidrobiologicoBean.setNumCantidad(ParametrosUtil.formatearDecimales(registroHidrobiologicoBean.getNumCantidad(),2));
				
				if(registroHidrobiologicoBean.getIdRegHidrobiologico() != null) {
					//existe y es update
					registroHidrobiologicoDao.actualizarRegistroHidrobiologico(registroHidrobiologicoBean);
				}else {
					//no existe y es insert
					registroHidrobiologicoDao.registrarRegistroHidrobiologico(registroHidrobiologicoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Transactional(rollbackFor=GmdException.class)
	public Integer consultarEstadoRegistroHidrobiologico(Integer idPtarxSector ,Integer idParametro, List<RegistroHidrobiologicoBean> listaRegHidrobiologico) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			if(listaRegHidrobiologico != null && listaRegHidrobiologico.size() > 0) {
				estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
			}else {
				estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return estadoRegistro;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroAceite(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, 
			List<RegistroAceiteBean> listaRegAceite,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroAceite) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoAceite = consultarEstadoRegistroAceite(idPtarxSector,idParametro,listaRegAceite);
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagAceite(estadoAceite);
				beanOriginal.setObservacionAceites(observacionRegistroAceite);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(estadoAceite);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(observacionRegistroAceite);
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroAceiteBean registroAceiteBean : listaRegAceite) {
				registroAceiteBean.setIdRegLaboratorio(idRegistro);
				registroAceiteBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroAceiteBean.getFechaMonitoreoString()));
				registroAceiteBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroAceiteBean.getFechaRegDatoString()));
				registroAceiteBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroAceiteBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroAceiteBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroAceiteBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroAceiteBean.setDeTermModi(auditoria.getDeTermCrea());
				registroAceiteBean.setIdPtarxSector(idPtarxSector);
				registroAceiteBean.setIdParametro(idParametro);
				registroAceiteBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroAceiteBean.setFlagAprobador(String.valueOf(flagValidador));
				registroAceiteBean.setNumVolumen(ParametrosUtil.formatearDecimales(registroAceiteBean.getNumVolumen(),2));
				registroAceiteBean.setNumPesoInicial(ParametrosUtil.formatearDecimales(registroAceiteBean.getNumPesoInicial(),2));
				registroAceiteBean.setNumPesoFinal(ParametrosUtil.formatearDecimales(registroAceiteBean.getNumPesoFinal(),2));
				if(StringUtils.isEmpty(registroAceiteBean.getDescripcionObservacion())){
					registroAceiteBean.setDescripcionObservacion(" ");
				}else {
					registroAceiteBean.setDescripcionObservacion(registroAceiteBean.getDescripcionObservacion());
				}
				
				if(registroAceiteBean.getIdRegAceite() != null) {
					//existe y es update
					registroAceiteDao.actualizarRegistroAceite(registroAceiteBean);
				}else {
					//no existe y es insert
					registroAceiteDao.registrarRegistroAceite(registroAceiteBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Transactional(rollbackFor=GmdException.class)
	public Integer consultarEstadoRegistroAceite(Integer idPtarxSector ,Integer idParametro, List<RegistroAceiteBean> listaRegAceite) throws GmdException {
		Integer estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
		try {
			if(listaRegAceite != null && listaRegAceite.size() > 0) {
				estadoRegistro =  ConstantesUtil.PENDIENTE_APROBAR;
			}else {
				estadoRegistro = ConstantesUtil.PENDIENTE_INGRESO;
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return estadoRegistro;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroSolido(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro,
			List<RegistroSolidoBean> listaRegSolido,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroSolido) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoSolido = (listaRegSolido != null && listaRegSolido.size() > 0) ? ConstantesUtil.PENDIENTE_APROBAR : ConstantesUtil.PENDIENTE_INGRESO ;
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagSolido(estadoSolido);
				beanOriginal.setObservacionSolidos(observacionRegistroSolido);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagSolido(estadoSolido);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(observacionRegistroSolido);
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroSolidoBean registroSolidoBean : listaRegSolido) {
				registroSolidoBean.setIdRegistroLaboratorio(idRegistro);
				registroSolidoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(registroSolidoBean.getFechaMonitoreoString()));
				registroSolidoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroSolidoBean.getFechaRegDatoString()));
				registroSolidoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroSolidoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroSolidoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroSolidoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroSolidoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroSolidoBean.setIdPtarxSector(idPtarxSector);
				registroSolidoBean.setIdParametro(idParametro);
				registroSolidoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroSolidoBean.setFlagAprobador(String.valueOf(flagValidador));	
				registroSolidoBean.setDescripcionObservacion(" ");
				if(registroSolidoBean.getIdSolido() != null) {
					//existe y es update
					registroSolidoDao.actualizarRegistroSolido(registroSolidoBean);
				}else {
					//no existe y es insert
					registroSolidoDao.registrarRegistroSolido(registroSolidoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroDBO(Integer idRegistro, Integer idPtarxSector,
			Integer idParametro, String fechaRegistro, List<RegistroDBOBean> listaRegDBO, BaseSptarBean auditoria,
			Integer flagValidador, String observacionRegistroDBO) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoDBO = (listaRegDBO != null && listaRegDBO.size() > 0) ? ConstantesUtil.PENDIENTE_APROBAR : ConstantesUtil.PENDIENTE_INGRESO ;
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagDBO(estadoDBO);
				/*agregar observacion*/
				beanOriginal.setObservacionDBO(observacionRegistroDBO);
				/**/
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagDBO(estadoDBO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(observacionRegistroDBO);
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(" ");
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagMicrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroDBOBean registroDBOBean : listaRegDBO) {
				registroDBOBean.setIdRegistroLaboratorio(idRegistro);
//				registroDBOBean.setFechaInicial(ParametrosUtil.convertirStringtoDateSP(registroDBOBean.getFechaInicialString()));
				registroDBOBean.setFechaInicial(ParametrosUtil.convertStringToTimestamp(registroDBOBean.getFechaInicialString(), ConstantesUtil.FORMATO_24_HORAS_DATE));
				registroDBOBean.setFechaVencimiento(ParametrosUtil.convertirStringtoDateSP(registroDBOBean.getFechaVencimientoString()));				
				registroDBOBean.setFechaMonitoreo(registroDBOBean.getFechaInicial());
				registroDBOBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(registroDBOBean.getFechaRegDatoString()));
				registroDBOBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroDBOBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroDBOBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroDBOBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroDBOBean.setDeTermModi(auditoria.getDeTermCrea());
				registroDBOBean.setIdPtarxSector(idPtarxSector);
				registroDBOBean.setIdParametro(idParametro);
				registroDBOBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroDBOBean.setFlagAprobador(String.valueOf(flagValidador));	
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
//				establecer mejor valor inicial
				if(StringUtils.isEmpty(registroDBOBean.getIndicadorTipoSemilla())) {
					registroDBOBean.setFlagMejorValor(ConstantesUtil.FLAG_MARCA_MEJOR_VALOR);
				}else {
					registroDBOBean.setFlagMejorValor(ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				}
//				fin obs
				if(registroDBOBean.getIdDBO() != null) {
					//existe y es update
					registroDBODao.actualizarRegistroDBO(registroDBOBean);
				}else {
					//no existe y es insert
					registroDBODao.registrarRegistroDBO(registroDBOBean);
				}
			}
//			inicio obs
//			asignar mejor valor final
			for(RegistroDBOBean registroDBOBean : listaRegDBO) {
				if(StringUtils.isEmpty(registroDBOBean.getIndicadorTipoSemilla())) {
					if(registroDBOBean.getIdPtoEnlace() != null) {
						registroDBODao.obtenerMejorRegistroEnlace(registroDBOBean.getIdRegistroLaboratorio(), registroDBOBean.getIdPuntoMuestra(), registroDBOBean.getIdSubParametro());
					}else {
						registroDBODao.obtenerMejorRegistroNoEnlace(registroDBOBean.getIdRegistroLaboratorio(), registroDBOBean.getIdPuntoMuestra(), registroDBOBean.getIdSubParametro());
					}
				}
			}
//			asignar fecha DBO
			registroLaboratorioDao.asignarFechaDBO(bean.getIdRegistroLaboratorio());
//			fin obs
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroMicrobiologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro,
			List<RegistroMicrobiologicoBean> listaRegMicrobiologico,BaseSptarBean auditoria,Integer flagValidador, AnalistaBean analistaBean, String observacionRegistroMicrobiologico) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoMicrobiologico = (listaRegMicrobiologico != null && listaRegMicrobiologico.size() > 0) ? ConstantesUtil.PENDIENTE_APROBAR : ConstantesUtil.PENDIENTE_INGRESO ;
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagMicrobiologico(estadoMicrobiologico);
				beanOriginal.setObservacionMicrobiologico(observacionRegistroMicrobiologico);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagMicrobiologico(estadoMicrobiologico);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(observacionRegistroMicrobiologico);
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroMicrobiologicoBean registroMicrobiologicoBean : listaRegMicrobiologico) {
				registroMicrobiologicoBean.setIdRegLaboratorio(idRegistro);
				registroMicrobiologicoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroMicrobiologicoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroMicrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroMicrobiologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdPtarxSector(idPtarxSector);
				registroMicrobiologicoBean.setIdSubParametro(0);
				registroMicrobiologicoBean.setIdParametro(idParametro);
				registroMicrobiologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroMicrobiologicoBean.setFlagAprobador(String.valueOf(flagValidador));
				registroMicrobiologicoBean.setIdAnalista(analistaBean.idAnalista);
				registroMicrobiologicoBean.setDescripcionObservacion(" ");
				registroMicrobiologicoBean.setFlagMejorValor(registroMicrobiologicoBean.getFlagMejorValor() != null ? registroMicrobiologicoBean.getFlagMejorValor() : ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				if(registroMicrobiologicoBean.getIdRegMicrobiologico() != null) {
					//existe y es update
					registroMicrobiologicoDao.actualizarRegistroMicrobiologico(registroMicrobiologicoBean);
				}else {
					//no existe y es insert
					registroMicrobiologicoDao.registrarRegistroMicrobiologico(registroMicrobiologicoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarListaRegistroMicrobiologicoSecundario(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro,
			List<RegistroMicrobiologicoBean> listaRegMicrobiologico,BaseSptarBean auditoria,Integer flagValidador, AnalistaBean analistaBean, String observacionRegistroMicrobiologico) throws GmdException {
		RegistroLaboratorioBean bean = new RegistroLaboratorioBean();
		try {
			Integer estadoMicrobiologico = (listaRegMicrobiologico != null && listaRegMicrobiologico.size() > 0) ? ConstantesUtil.PENDIENTE_APROBAR : ConstantesUtil.PENDIENTE_INGRESO ;
			List<ParametroPtarSectorBean> listaParametros = parametroPtarSectorDao.obtenerltaParametroPtarSectorAsignados(idPtarxSector);
			if(idRegistro != null) {
				//hacemos update
				RegistroLaboratorioBean beanOriginal = registroLaboratorioDao.obtenerRegistroLaboratorio(idRegistro);
				beanOriginal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				beanOriginal.setIdUsuaModi(auditoria.getIdUsuaCrea());
				beanOriginal.setDeTermModi(auditoria.getDeTermCrea());
				beanOriginal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				beanOriginal.setFlagMicrobiologico(estadoMicrobiologico);
				beanOriginal.setObservacionMicrobiologico(observacionRegistroMicrobiologico);
				registroLaboratorioDao.actualizarRegistroLaboratorio(beanOriginal);
				bean = beanOriginal;				
				//comprobamos su estado
				
			}else {
				//hacemos insert
				RegistroLaboratorioBean registroLabBeanPrincipal = new RegistroLaboratorioBean();
				registroLabBeanPrincipal.setIdPtarxSector(idPtarxSector);
				registroLabBeanPrincipal.setFechaRegistro(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroLabBeanPrincipal.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroLabBeanPrincipal.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroLabBeanPrincipal.setDeTermCrea(auditoria.getDeTermCrea());
				registroLabBeanPrincipal.setFlagMicrobiologico(estadoMicrobiologico);
				registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.NO_INGRESA_PARAMETRO);
				registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.NO_INGRESA_PARAMETRO);
				/*Observacion de registro por parametro Directo*/
				registroLabBeanPrincipal.setObservacionDirecto(" ");
				registroLabBeanPrincipal.setObservacionDBO(" ");
				registroLabBeanPrincipal.setObservacionAceites(" ");
				registroLabBeanPrincipal.setObservacionSolidos(" ");
				registroLabBeanPrincipal.setObservacionHidrobiologico(" ");
				registroLabBeanPrincipal.setObservacionMicrobiologico(observacionRegistroMicrobiologico);
				registroLabBeanPrincipal.setObservacionParasitologico(" ");
				/**/
				for (ParametroPtarSectorBean beanParametro : listaParametros) {
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_FISICO_QUIMICO) {
						registroLabBeanPrincipal.setFlagDBO(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_ACEITE) {
						registroLabBeanPrincipal.setFlagAceite(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_HIDROBIOLOGICO) {
						registroLabBeanPrincipal.setFlagHidrobiologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_SOLIDO) {
						registroLabBeanPrincipal.setFlagSolido(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_PARASITOLOGICO) {
						registroLabBeanPrincipal.setFlagParasitologico(ConstantesUtil.PENDIENTE_INGRESO);
					}
					if(beanParametro.getIdParametro() == ConstantesUtil.ID_PARAMETRO_DIRECTO) {
						registroLabBeanPrincipal.setFlagDirecto(ConstantesUtil.PENDIENTE_INGRESO);
					}
				}
				registroLabBeanPrincipal.setFlagAprobacion(ConstantesUtil.ESTADO_PENDIENTE_REG_LAB);
				bean = registroLaboratorioDao.registrarRegistroLaboratorio(registroLabBeanPrincipal);
				idRegistro = bean.getIdRegistroLaboratorio();
			}
			for (RegistroMicrobiologicoBean registroMicrobiologicoBean : listaRegMicrobiologico) {
				registroMicrobiologicoBean.setIdRegLaboratorio(idRegistro);
				registroMicrobiologicoBean.setFechaMonitoreo(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroMicrobiologicoBean.setFechaRegDato(ParametrosUtil.convertirStringtoDateSP(fechaRegistro));
				registroMicrobiologicoBean.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroMicrobiologicoBean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermCrea(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				registroMicrobiologicoBean.setDeTermModi(auditoria.getDeTermCrea());
				registroMicrobiologicoBean.setIdPtarxSector(idPtarxSector);
				registroMicrobiologicoBean.setIdParametro(idParametro);
				registroMicrobiologicoBean.setFlagRegistrador(ConstantesUtil.FLAG_REGISTRADOR);
				registroMicrobiologicoBean.setFlagAprobador(String.valueOf(flagValidador));
				registroMicrobiologicoBean.setIdAnalista(analistaBean.idAnalista);
				registroMicrobiologicoBean.setDescripcionObservacion(" ");
				registroMicrobiologicoBean.setFlagMejorValor(registroMicrobiologicoBean.getFlagMejorValor() != null ? registroMicrobiologicoBean.getFlagMejorValor() : ConstantesUtil.FLAG_NO_MEJOR_VALOR);
				if(registroMicrobiologicoBean.getIdRegMicrobiologico() != null) {
					//existe y es update
					registroMicrobiologicoSecundarioDao.actualizarRegistroMicrobiologico(registroMicrobiologicoBean);
				}else {
					//no existe y es insert
					registroMicrobiologicoSecundarioDao.registrarRegistroMicrobiologico(registroMicrobiologicoBean);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void cambiarFlagEstadoAprobacion(List<RegistroLaboratorioBean> ltaRegistro, Integer idCambioEstado, BaseSptarBean auditoria) throws GmdException{
		try {
			for(RegistroLaboratorioBean bean : ltaRegistro)
			{	
				bean.setStRegi(auditoria.getStRegi());
				bean.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				bean.setDeTermCrea(auditoria.getDeTermCrea());
				registroLaboratorioDao.cambiarFlagAprobacion(bean, idCambioEstado);
				RegistroLaboratorioBean regLaboratorioBean = obtenerRegistroLaboratorio(bean.getIdRegistroLaboratorio());
				Integer flagDirecto = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagDirecto());
				Integer flagDBO5 = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagDBO());
				Integer flagAceite = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagAceite());
				Integer flagSolido = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagSolido());
				Integer flagHidrobiologico = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagHidrobiologico());
				Integer flagMicrobiologico = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagMicrobiologico());
				Integer flagParasitologico = ParametrosUtil.transformarFlag(regLaboratorioBean.getFlagParasitologico());
				regLaboratorioBean.setStRegi(auditoria.getStRegi());
				regLaboratorioBean.setIdUsuaModi(auditoria.getIdUsuaCrea());
				regLaboratorioBean.setDeTermModi(auditoria.getDeTermCrea());
				regLaboratorioBean.setFlagDirecto(flagDirecto);
				regLaboratorioBean.setFlagDBO(flagDBO5);
				regLaboratorioBean.setFlagAceite(flagAceite);
				regLaboratorioBean.setFlagSolido(flagSolido);
				regLaboratorioBean.setFlagHidrobiologico(flagHidrobiologico);
				regLaboratorioBean.setFlagMicrobiologico(flagMicrobiologico);
				regLaboratorioBean.setFlagParasitologico(flagParasitologico);
				registroLaboratorioDao.actualizarRegistroLaboratorio(regLaboratorioBean);
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }	
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void asignarFechaDBO(Integer idRegistroLaboratorio) throws GmdException{
		try {
			registroLaboratorioDao.asignarFechaDBO(idRegistroLaboratorio);
		} catch (Exception excepcion) {
	        throw new GmdException(excepcion);
	    }
	}
	
}
