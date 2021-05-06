package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;

public interface ISemillaStandardService {
	
	List<SemillaStandardBean> obtenerLtaSemillaStandardTodo() throws GmdException;
	
	List<SemillaStandardBean> obtenerLtaSemillaStandard() throws GmdException;

	void anularSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException;

	void actualizarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException;

	SemillaStandardBean registrarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException;
	
	void actualizarInformacionStandard(Double promedioSCF, BaseSptarBean auditoria )throws GmdException;
	
	List<SemillaStandardBean> obtenerLtaSemillaStandardTodoDBO(Integer idRegLab) throws GmdException;
}
