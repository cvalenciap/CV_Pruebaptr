package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.GeneralBean;

public interface IGeneralService {
	
	/**
     * Descripcion	metodo listarTodos
     * @return		List<GeneralBean>
     * @throws 		GmdException
     */
	List<GeneralBean> listarTodos() throws GmdException;
}
