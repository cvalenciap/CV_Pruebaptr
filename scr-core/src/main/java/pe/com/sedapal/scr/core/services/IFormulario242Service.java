/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import pe.com.sedapal.scr.core.beans.FormularioDetalle242;
import pe.com.sedapal.scr.core.beans.FormularioHeader242;

import java.util.List;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;


// TODO: Auto-generated Javadoc
/**
 * The Interface IFormulario242Service.
 */
public interface IFormulario242Service {
	
	/**
	 * Gets the listado formulario header.
	 *
	 * @param formularioHeader242 the formulario header 242
	 * @param paginacion the paginacion
	 * @return the listado formulario header
	 */
	Result getListadoFormularioHeader(FormularioHeader242 formularioHeader242, Paginacion paginacion);
	
	/**
	 * Grabar formulario header.
	 *
	 * @param formularioHeader242 the formulario header 242
	 * @return the int
	 */
	int grabarFormularioHeader(FormularioHeader242 formularioHeader242);
	
	/**
	 * Gets the formulario header.
	 *
	 * @param id the id
	 * @return the formulario header
	 */
	FormularioHeader242 getFormularioHeader(Integer id);
	
	/**
	 * Gets the formulario detalle.
	 *
	 * @param id the id
	 * @return the formulario detalle
	 */
	FormularioDetalle242 getFormularioDetalle(Integer id);
	
	/**
	 * Grabar formulario detalle.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 */
	void grabarFormularioDetalle(FormularioDetalle242 formularioDetalle242);
	
	/**
	 * Inactivar formulario detalle.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 */
	void inactivarFormularioDetalle(FormularioDetalle242 formularioDetalle242);
	
	/**
	 * Gets the listado formulario detalle.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 * @param paginacion the paginacion
	 * @return the listado formulario detalle
	 */
	Result getListadoFormularioDetalle(FormularioDetalle242 formularioDetalle242, Paginacion paginacion);
	
	/**
	 * Update formulario detalle.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 */
	void updateFormularioDetalle(FormularioDetalle242 formularioDetalle242);
	
	/**
	 * Update formulario header.
	 *
	 * @param formularioHeader242 the formulario header 242
	 */
	void updateFormularioHeader(FormularioHeader242 formularioHeader242);
	
	/**
	 * Inactivar formulario header.
	 *
	 * @param formularioHeader242 the formulario header 242
	 */
	void inactivarFormularioHeader(FormularioHeader242 formularioHeader242);

	/**
	 * Gets the listado formulario detalle duplicar.
	 *
	 * @param formularioDetalle242 the formulario detalle 242
	 * @param paginacion the paginacion
	 * @return the listado formulario detalle duplicar
	 */
	public List<FormularioDetalle242> getListadoFormularioDetalleDuplicar(FormularioDetalle242 formularioDetalle242, Paginacion paginacion);
}
