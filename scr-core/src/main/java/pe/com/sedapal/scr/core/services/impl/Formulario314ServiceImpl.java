/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.Formulario314;
import pe.com.sedapal.scr.core.dao.IFormulario314Dao;
import pe.com.sedapal.scr.core.services.IFormulario314Service;

// TODO: Auto-generated Javadoc
/**
 * The Class Formulario314ServiceImpl.
 */
@Service
public class Formulario314ServiceImpl implements IFormulario314Service {
	
	/** The i formulario 314 dao. */
	@Autowired
	private IFormulario314Dao iFormulario314Dao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#getListadoFormulario314(pe.com.sedapal.scr.core.beans.Formulario314, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormulario314(Formulario314 formulario314, Paginacion paginacion) {
	
		return iFormulario314Dao.getListadoFormulario314(formulario314, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#grabarFormulario314(pe.com.sedapal.scr.core.beans.Formulario314)
	 */
	@Override
	public int grabarFormulario314(Formulario314 formulario314) {
		
		return iFormulario314Dao.grabarFormulario314(formulario314);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#getFormulario314(java.lang.Integer)
	 */
	@Override
	public Formulario314 getFormulario314(Integer id) {
		
		return iFormulario314Dao.getFormulario314(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#updateFormulario314(pe.com.sedapal.scr.core.beans.Formulario314)
	 */
	@Override
	public void updateFormulario314(Formulario314 formulario314) {
		
		iFormulario314Dao.updateFormulario314(formulario314);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#inactivarForm314(pe.com.sedapal.scr.core.beans.Formulario314)
	 */
	@Override
	public void inactivarForm314(Formulario314 formulario314) {
		
		iFormulario314Dao.inactivarForm314(formulario314);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario314Service#getCalculoFormulario314(pe.com.sedapal.scr.core.beans.Formulario314)
	 */
	@Override
	public Formulario314 getCalculoFormulario314(Formulario314 formulario314) throws Exception {
		// TODO Auto-generated method stub
		return iFormulario314Dao.getCalculoFormulario314(formulario314);
	}

}
