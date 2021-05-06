package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.OximetroBean;

public interface IOximetroDao {
	
	List<OximetroBean> obtenerLtaOximetroTodo() throws GmdException;
	
	List<OximetroBean> obtenerLtaOximetro() throws GmdException;
	
    void anularOximetro(OximetroBean Oximetro) throws GmdException;
	
	void actualizarOximetro(OximetroBean Oximetro) throws GmdException;
	
	OximetroBean registrarOximetro(OximetroBean Oximetro) throws GmdException;
}
