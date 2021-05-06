package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;

public interface ISolidoSuspendidoDao {
	
	List<SolidoSuspendidoBean> obtenerLtaSolidoTodo(Integer idPtarxSector, Integer idTipoSolido) throws GmdException;
	
	List<SolidoSuspendidoBean> obtenerLtaSolidoSuspendido(Integer idPtarxSector, Integer idTipoSolido) throws GmdException;
	
    void anularSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException;
	
	void actualizarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException;
	
	SolidoSuspendidoBean registrarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException;
}
