package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;

public interface ITuboDilucionService {
	
	List<TuboDilucionBean> obtenerLtaTuboDilucion() throws GmdException;
	
	List<TuboDilucionBean> obtenerLtaTuboDilucionTodo() throws GmdException;

    void anularTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException;
	
	void actualizarTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException;
	
	TuboDilucionBean registrarTuboDilucion(TuboDilucionBean TuboDilucionBean) throws GmdException;
	
	List<TuboDilucionBean> obtenerTuboDilucionByCadena(String valorCombinacion, Integer numExponente) throws GmdException;
}
