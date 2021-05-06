/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.FormularioDetalle242;
import pe.com.sedapal.scr.core.beans.FormularioHeader242;
import pe.com.sedapal.scr.core.dao.IFormulario242Dao;
import pe.com.sedapal.scr.core.services.IFormulario242Service;


// TODO: Auto-generated Javadoc
/**
 * The Class Formulario242ServiceImpl.
 */
@Service
public class Formulario242ServiceImpl implements IFormulario242Service {
	
	/** The i formulario 242 dao. */
	@Autowired
	private IFormulario242Dao iFormulario242Dao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#getListadoFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioHeader(FormularioHeader242 formularioHeader242, Paginacion paginacion) {
		return iFormulario242Dao.getListadoFormularioHeader(formularioHeader242, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#grabarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public int grabarFormularioHeader(FormularioHeader242 formularioHeader242) {
		
		return iFormulario242Dao.grabarFormularioHeader(formularioHeader242);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#getFormularioHeader(java.lang.Integer)
	 */
	@Override
	public FormularioHeader242 getFormularioHeader(Integer id) {

		return iFormulario242Dao.getFormularioHeader(id);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#getFormularioDetalle(java.lang.Integer)
	 */
	@Override
	public FormularioDetalle242 getFormularioDetalle(Integer id) {
		
		return iFormulario242Dao.getFormularioDetalle(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#grabarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void grabarFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		
		iFormulario242Dao.grabarFormularioDetalle(formularioDetalle242);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#getListadoFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioDetalle(FormularioDetalle242 formularioDetalle242, Paginacion paginacion) {
		
		return iFormulario242Dao.getListadoFormularioDetalle(formularioDetalle242, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#inactivarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void inactivarFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		
		iFormulario242Dao.inactivarFormularioDetalle(formularioDetalle242);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#updateFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void updateFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		
		iFormulario242Dao.updateFormularioDetalle(formularioDetalle242);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#updateFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public void updateFormularioHeader(FormularioHeader242 formularioHeader242) {
		
		iFormulario242Dao.updateFormularioHeader(formularioHeader242);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#inactivarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public void inactivarFormularioHeader(FormularioHeader242 formularioHeader242) {
		iFormulario242Dao.inactivarFormularioHeader(formularioHeader242);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario242Service#getListadoFormularioDetalleDuplicar(pe.com.sedapal.scr.core.beans.FormularioDetalle242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public List<FormularioDetalle242> getListadoFormularioDetalleDuplicar(FormularioDetalle242 formularioDetalle242, Paginacion paginacion){
		return iFormulario242Dao.getListadoFormularioDetalleDuplicar(formularioDetalle242,  paginacion);
	}
	
}
