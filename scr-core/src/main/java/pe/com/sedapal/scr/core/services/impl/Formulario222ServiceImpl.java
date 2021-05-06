/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.FormularioDetalle222;
import pe.com.sedapal.scr.core.beans.FormularioHeader222;
import pe.com.sedapal.scr.core.dao.IFormulario222Dao;
import pe.com.sedapal.scr.core.services.IFormulario222Service;


// TODO: Auto-generated Javadoc
/**
 * The Class Formulario222ServiceImpl.
 */
@Service
public class Formulario222ServiceImpl implements IFormulario222Service {
	
	/** The i formulario 222 dao. */
	@Autowired
	private IFormulario222Dao iFormulario222Dao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#getListadoFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioHeader(FormularioHeader222 formularioHeader222, Paginacion paginacion) {
		
		return iFormulario222Dao.getListadoFormularioHeader(formularioHeader222, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#getFormularioHeader(java.lang.Integer)
	 */
	@Override
	public FormularioHeader222 getFormularioHeader(Integer id) {
		
		return iFormulario222Dao.getFormularioHeader(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#grabarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222)
	 */
	@Override
	public int grabarFormularioHeader(FormularioHeader222 formularioHeader222) {
		
		return iFormulario222Dao.grabarFormularioHeader(formularioHeader222);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#grabarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void grabarFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		
		iFormulario222Dao.grabarFormularioDetalle(formularioDetalle222);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#getListadoFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioDetalle(FormularioDetalle222 formularioDetalle222, Paginacion paginacion) {

		return iFormulario222Dao.getListadoFormularioDetalle(formularioDetalle222, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#updateFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222)
	 */
	@Override
	public void updateFormularioHeader(FormularioHeader222 formularioHeader222) {
		
		iFormulario222Dao.updateFormularioHeader(formularioHeader222);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#getFormularioDetalle(java.lang.Integer)
	 */
	@Override
	public FormularioDetalle222 getFormularioDetalle(Integer id) {
		
		return iFormulario222Dao.getFormularioDetalle(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#updateFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void updateFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		
		iFormulario222Dao.updateFormularioDetalle(formularioDetalle222);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#inactivarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void inactivarFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		
		iFormulario222Dao.inactivarFormularioDetalle(formularioDetalle222);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario222Service#registrosPorHeader(int)
	 */
	@Override
	public int registrosPorHeader(int header) {

		return iFormulario222Dao.registrosPorHeader(header);
	}	
	
}
