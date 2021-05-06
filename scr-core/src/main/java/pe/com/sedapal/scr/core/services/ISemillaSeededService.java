package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaSeededBean;

public interface ISemillaSeededService {
	
	List<SemillaSeededBean> obtenerLtaSemillaSeededTodo() throws GmdException;
	
	List<SemillaSeededBean> obtenerLtaSemillaSeeded() throws GmdException;

	void anularSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException;

	void actualizarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException;

	SemillaSeededBean registrarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException;
	
	void actualizarPromedioSeeded(Double valorGGA,BaseSptarBean auditoria) throws GmdException;
	
	List<SemillaSeededBean> obtenerLtaSemillaSeededTodoDBO(Integer idRegLab) throws GmdException;
}
