package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.TreeReporteBean;

public interface IRegistroLabReporteDao {
	
//	List<RegistroLabReporteBean> obtenerListaRegistroReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, Date fechaInicio, Date fechaFin) throws GmdException;
	List<RegistroLabReporteBean> obtenerListaRegistroReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, Date fechaInicio, Date fechaFin, String idParametro) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaRegistroReporteHistorico(Integer idPtarxSector, Date fechaInicio, Date fechaFin) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaRegistroReporteAcumulado(Date fechaInicio, Date fechaFin) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaDashboard(Date fechaInicio, Date fechaFin, Integer idPtar) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaLineDashboard(Date fechaInicioConvert, Date fechaFinConvert, Integer idPtarxSector) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaLineNewAlterDashboard(Date fechaInicioConvert, Date fechaFinConvert, Integer idPtarSector) throws GmdException;
	
//	inicio ordenamiento inicial
//	List<TreeReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException;
	List<RegistroLabReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException;
//	fin ordenamiento inicial

}