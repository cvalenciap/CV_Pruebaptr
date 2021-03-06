package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.TreeReporteBean;

public interface IRegistroLabReporteService {
	
//	List<RegistroLabReporteBean> obtenerListaReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, String fechaInicio, String fechaFin) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, String fechaInicio, String fechaFin, List<String> parametros) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaReporteHistorico(Integer idPtarxSector, String fechaInicio, String fechaFin) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaReporteAcumulado(String fechaInicio, String fechaFin) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaDashboard(String fechaInicio, String fechaFin, Integer[] arrayPuntos) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaLineDashboard(String fechaInicio, String fechaFin, Integer idPtarxSector) throws GmdException;
	
	List<RegistroLabReporteBean> obtenerListaLineNewAlterDashboard(String fechaInicio, String fechaFin, Integer idPtarSector) throws GmdException;
	
//	inicio ordenamiento inicial
//	List<TreeReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException;
	List<RegistroLabReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException;
//	fin ordenamiento inicial
}
