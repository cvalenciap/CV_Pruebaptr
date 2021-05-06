package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.SemillaSamplesBean;

public interface ISemillaSamplesService {

	List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodo() throws GmdException;
	
	List<SemillaSamplesBean> obtenerLtaSemillaSamples() throws GmdException;

	void anularSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException;

	void actualizarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException;

	SemillaSamplesBean registrarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException;
	
	void actualizarInformacionSamples(Double promedioSCF, BaseSptarBean auditoria )throws GmdException;
	
	List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodoDBO(Integer idRegLab) throws GmdException;
}
