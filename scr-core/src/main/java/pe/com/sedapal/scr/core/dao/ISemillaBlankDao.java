package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.SemillaBlankBean;

public interface ISemillaBlankDao {
	
	List<SemillaBlankBean> obtenerLtaSemillaBlankTodo() throws GmdException;
	
	List<SemillaBlankBean> obtenerLtaSemillaBlank() throws GmdException;

	void anularSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;

	void actualizarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;

	SemillaBlankBean registrarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;
	
	List<SemillaBlankBean> obtenerLtaSemillaBlankDBO(Integer idRegLab) throws GmdException;
}
