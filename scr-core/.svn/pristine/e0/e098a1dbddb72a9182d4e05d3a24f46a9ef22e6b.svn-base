package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;

public interface IRegistroLaboratorioService {
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin, String tipoConsulta) throws GmdException;
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioEmpty(String idPtarxSector, String fechaInicio, String fechaFin, String idEstado) throws GmdException;

	RegistroLaboratorioBean registrarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException;
	
	void actualizarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException;

	RegistroLaboratorioBean registrarListaRegistroDirecto(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroDirectoBean> listaRegDirecto,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroDirecto) throws GmdException;
	
	RegistroLaboratorioBean obtenerRegistroLaboratorio(Integer idRegistroLaboratorio) throws GmdException;
		
	void cambiarFlagAprobacion(List<RegistroLaboratorioBean> ltaRegistro, Integer idCambioEstado, BaseSptarBean auditoria) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroParasitologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroParasitologicoBean> listaRegParasitologico,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroParasitologico) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroHidrobiologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroHidrobiologicoBean> listaRegHidrobiologico,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroHidrobiologico) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroAceite(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroAceiteBean> listaRegAceite,BaseSptarBean auditoria, Integer flagValidador, String observacionRegistroAceite) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroSolido(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroSolidoBean> listaRegSolido,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroSolido) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroDBO(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroDBOBean> listaRegDBO,BaseSptarBean auditoria,Integer flagValidador, String observacionRegistroDBO) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroMicrobiologico(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroMicrobiologicoBean> listaRegMicrobiologico,BaseSptarBean auditoria,Integer flagValidador, AnalistaBean analistaBean, String observacionRegistroMicrobiologico) throws GmdException;
	
	RegistroLaboratorioBean registrarListaRegistroMicrobiologicoSecundario(Integer idRegistro,Integer idPtarxSector,Integer idParametro,String fechaRegistro, List<RegistroMicrobiologicoBean> listaRegMicrobiologico,BaseSptarBean auditoria,Integer flagValidador, AnalistaBean analistaBean, String observacionRegistroMicrobiologico) throws GmdException;
	
	void cambiarFlagEstadoAprobacion(List<RegistroLaboratorioBean> ltaRegistro, Integer idCambioEstado, BaseSptarBean auditoria) throws GmdException;
	
	void asignarFechaDBO(Integer idRegistroLaboratorio) throws GmdException;
}
