package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaBlankBean;

public interface ISemillaBlankService {
	
    List<SemillaBlankBean> obtenerLtaSemillaBlankTodo() throws GmdException;
	
	List<SemillaBlankBean> obtenerLtaSemillaBlank() throws GmdException;

	void anularSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;

	void actualizarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;

	SemillaBlankBean registrarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException;
	
	void actualizarPromedioBlanks(BaseSptarBean auditoria) throws GmdException;
	
	List<SemillaBlankBean> obtenerLtaSemillaBlankDBO(Integer idRegLab) throws GmdException;
}
