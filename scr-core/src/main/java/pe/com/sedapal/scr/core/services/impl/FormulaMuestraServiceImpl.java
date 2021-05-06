/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.FormulaMuestraBean;
import pe.com.sedapal.scr.core.dao.ICaudalDao;
import pe.com.sedapal.scr.core.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.core.dao.IFormulaMuestraDao;
import pe.com.sedapal.scr.core.services.IFormulaMuestraService;

// TODO: Auto-generated Javadoc
/**
 * The Class FormulaMuestraServiceImpl.
 */
@Service
public class FormulaMuestraServiceImpl implements IFormulaMuestraService {

	/** The formula muestra dao. */
	@Autowired
	private IFormulaMuestraDao formulaMuestraDao;
		
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulaMuestraService#obtenerDatosFormulasMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosFormulasMuestra(FormulaMuestraBean formulaMuestraBean, Paginacion paginacion)throws Exception {
		
		return formulaMuestraDao.obtenerDatosFormulasMuestra(formulaMuestraBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulaMuestraService#obtenerFormulaMuestra(java.lang.Integer)
	 */
	@Override
	public FormulaMuestraBean obtenerFormulaMuestra(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return formulaMuestraDao.obtenerFormulaMuestra(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulaMuestraService#grabarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	@Override
	public int grabarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {

		return formulaMuestraDao.grabarFormulaMuestra(formulaMuestraBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulaMuestraService#actualizarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	@Override
	public void actualizarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {
		formulaMuestraDao.actualizarFormulaMuestra(formulaMuestraBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulaMuestraService#inactivarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	@Override
	public void inactivarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {
		formulaMuestraDao.inactivarFormulaMuestra(formulaMuestraBean);
		
	}

}
