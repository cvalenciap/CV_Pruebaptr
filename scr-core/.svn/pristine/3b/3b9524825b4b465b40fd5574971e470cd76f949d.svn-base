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
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.beans.TreeReporteBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IParametroPtarSectorDao;
import pe.com.sedapal.scr.core.dao.IRegistroAceiteDao;
import pe.com.sedapal.scr.core.dao.IRegistroDBODao;
import pe.com.sedapal.scr.core.dao.IRegistroDirectoDao;
import pe.com.sedapal.scr.core.dao.IRegistroHidrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroLabReporteDao;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroParasitologicoDao;
import pe.com.sedapal.scr.core.dao.IRegistroSolidoDao;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.services.IRegistroLabReporteService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class RegistroLabReporteServiceImpl implements IRegistroLabReporteService {
	
	/** The generalDao dao. */
	@Autowired
	private IRegistroLaboratorioDao registroLaboratorioDao;
	
	@Autowired
	private IRegistroLabReporteDao registroLabReporteDao;
	
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
	private IRegistroAceiteDao registroAceiteDao;
	
	@Autowired
	private IParametroPtarSectorDao parametroPtarSectorDao;
	
	@Autowired
	private IRegistroSolidoDao registroSolidoDao;
	
	@Autowired
	private IRegistroDBODao registroDBODao;
	
	@Override
	public List<RegistroLabReporteBean> obtenerListaReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, String fechaInicio, String fechaFin, List<String> parametros) throws GmdException {
		List<RegistroLabReporteBean> ltaDetalleReporte = new ArrayList<RegistroLabReporteBean>();
        try {
        	Date fechaInicioConvert = null, fechaFinConvert = null;
        	if(!StringUtils.isEmpty(fechaInicio)){
    			fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
    		}
        	if(!StringUtils.isEmpty(fechaFin)){
    			fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
    		}
        	for(String idParametro : parametros) {
        		List<RegistroLabReporteBean> ltaDetalleParametro = new ArrayList<RegistroLabReporteBean>();
        		ltaDetalleParametro = registroLabReporteDao.obtenerListaRegistroReporte(idPtarxSector, idRegistroLaboratorio, fechaInicioConvert, fechaFinConvert, idParametro);
        		if(ltaDetalleParametro != null && ltaDetalleParametro.size() > 0) {
        			ltaDetalleReporte.addAll(ltaDetalleParametro);
        		}
        	}
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleReporte;
	}
	
	@Override
	public List<RegistroLabReporteBean> obtenerListaReporteHistorico(Integer idPtarxSector, String fechaInicio, String fechaFin) throws GmdException {
		List<RegistroLabReporteBean> ltaReporteHistorico = new ArrayList<RegistroLabReporteBean>();
		try {
			Date fechaInicioConvert = null, fechaFinConvert = null;
        	if(!StringUtils.isEmpty(fechaInicio)){
    			fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
    		}
        	if(!StringUtils.isEmpty(fechaFin)){
    			fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
    		}
        	ltaReporteHistorico = registroLabReporteDao.obtenerListaRegistroReporteHistorico(idPtarxSector,fechaInicioConvert, fechaFinConvert);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaReporteHistorico;		
	}
	
	@Override
	public List<RegistroLabReporteBean> obtenerListaReporteAcumulado(String fechaInicio, String fechaFin) throws GmdException{
		List<RegistroLabReporteBean> ltaReporteAcumulado = new ArrayList<RegistroLabReporteBean>();
		try {
			Date fechaInicioConvert = null, fechaFinConvert = null;
			if(!StringUtils.isEmpty(fechaInicio)) {
				fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
			}
			if(!StringUtils.isEmpty(fechaFin)) {
				fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
			}
			ltaReporteAcumulado = registroLabReporteDao.obtenerListaRegistroReporteAcumulado(fechaInicioConvert, fechaFinConvert);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaReporteAcumulado;
	}
	
	@Transactional(rollbackFor=GmdException.class)
	@Override
	public List<RegistroLabReporteBean> obtenerListaDashboard(String fechaInicio, String fechaFin, Integer[] arrayPuntos) throws GmdException{
		List<RegistroLabReporteBean> ltaReporteDashboard = new ArrayList<RegistroLabReporteBean>();
		try {
			Date fechaInicioConvert = null, fechaFinConvert = null;
			if(!StringUtils.isEmpty(fechaInicio)) {
				fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
			}
			if(!StringUtils.isEmpty(fechaFin)) {
				fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
			}
			for(Integer idPtar : arrayPuntos) {
				List<RegistroLabReporteBean> ltaReporteUnidad = registroLabReporteDao.obtenerListaDashboard(fechaInicioConvert, fechaFinConvert, idPtar);
				if(ltaReporteUnidad.size()>0) {
					for(RegistroLabReporteBean registro : ltaReporteUnidad){
						ltaReporteDashboard.add(registro);
					}
				}
			}
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return ltaReporteDashboard;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroLabReporteBean> obtenerListaLineDashboard(String fechaInicio, String fechaFin, Integer idPtarxSector) throws GmdException{
		List<RegistroLabReporteBean> ltaReporteDashboard = new ArrayList<RegistroLabReporteBean>();
		try {
			Date fechaInicioConvert = null, fechaFinConvert = null;
			if(!StringUtils.isEmpty(fechaInicio)) {
				fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
			}
			if(!StringUtils.isEmpty(fechaFin)) {
				fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
			}
			ltaReporteDashboard = registroLabReporteDao.obtenerListaLineDashboard(fechaInicioConvert, fechaFinConvert, idPtarxSector);
		}catch(Exception excepcion){
			throw new GmdException(excepcion);
		}
		return ltaReporteDashboard;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<RegistroLabReporteBean> obtenerListaLineNewAlterDashboard(String fechaInicio, String fechaFin, Integer idPtarSector) throws GmdException{
		List<RegistroLabReporteBean> ltaReporteDashboard = new ArrayList<RegistroLabReporteBean>();
		try {
			Date fechaInicioConvert = null, fechaFinConvert = null;
			if(!StringUtils.isEmpty(fechaInicio)) {
				fechaInicioConvert = ParametrosUtil.convertirStringtoDateSP(fechaInicio);
			}
			if(!StringUtils.isEmpty(fechaFin)) {
				fechaFinConvert = ParametrosUtil.convertirStringtoDateSP(fechaFin);
			}
			ltaReporteDashboard = registroLabReporteDao.obtenerListaLineNewAlterDashboard(fechaInicioConvert, fechaFinConvert, idPtarSector);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaReporteDashboard;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
//	inicio ordenamiento inicial
//	public List<TreeReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException{
//		List<TreeReporteBean> listaTree = new ArrayList<TreeReporteBean>();
	public List<RegistroLabReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException{
		List<RegistroLabReporteBean> listaTree = new ArrayList<RegistroLabReporteBean>();
//	fin ordenamiento inicial
		try {
			listaTree = registroLabReporteDao.obtenerListaTree(idPtarxSector);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return listaTree;
	}
}
