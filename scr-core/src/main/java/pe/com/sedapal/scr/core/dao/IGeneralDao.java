package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.GeneralBean;

public interface IGeneralDao {
	/**
     * Descripcion	metodo listarTodos
     * @return		List<GeneralBean>
     * @throws 		GmdException
     */
	List<GeneralBean> listarTodos() throws GmdException;
}
