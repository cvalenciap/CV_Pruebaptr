package pe.com.sedapal.scr.core.dao;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;

public interface IRegistroLaboratorioDao {
	
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin) throws GmdException, ParseException;
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio2(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin) throws GmdException, ParseException;
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioTipo(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin, Integer tipoFecha) throws GmdException, ParseException;
	
	List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioEmpty(String idPtarxSector, String fechaInicio, String fechaFin, String idEstado) throws GmdException, ParseException;
	
	RegistroLaboratorioBean registrarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException;

	void actualizarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException;
	
	RegistroLaboratorioBean obtenerRegistroLaboratorio(Integer idRegistroLaboratorio) throws GmdException;
	
	void cambiarFlagAprobacion(RegistroLaboratorioBean registro, Integer idCambioEstado) throws GmdException;
	
	void asignarFechaDBO(Integer idRegistroLaboratorio) throws GmdException;
	
	
}
