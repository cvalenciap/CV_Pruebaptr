/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.BeanComodin;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.Formulario153;
import pe.com.sedapal.scr.core.beans.ReporteMuestraPdf;
import pe.com.sedapal.scr.core.beans.RioBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRegistroDeMuestraDao.
 */
@Repository
public interface IRegistroDeMuestraDao {
	
	/**
	 * Obtener registros.
	 *
	 * @param formulario153 the formulario 153
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	Result obtenerRegistros(Formulario153 formulario153, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener formulario 153.
	 *
	 * @param nid the nid
	 * @return the formulario 153
	 */
	Formulario153 obtenerFormulario153(Integer nid);
	
	/**
	 * Grabar muestra.
	 *
	 * @param formulario153 the formulario 153
	 * @return the int
	 */
	int grabarMuestra(Formulario153 formulario153);
	
	/**
	 * Eliminar formulario.
	 *
	 * @param formulario153 the formulario 153
	 */
	void eliminarFormulario(Formulario153 formulario153);
	
	/**
	 * Actualizar formulario.
	 *
	 * @param formulario153 the formulario 153
	 */
	void actualizarFormulario(Formulario153 formulario153);
	
	/**
	 * Generar reporte muestra pdf.
	 *
	 * @param fecha the fecha
	 * @return the list
	 */
	List<ReporteMuestraPdf> generarReporteMuestraPdf(String fecha);
	
	/**
	 * Obtener punto muestreo cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerPuntoMuestreoCbo();
	
	/**
	 * Obtener matriz cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerMatrizCbo();
	
	/**
	 * Obtener tipo analisis cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerTipoAnalisisCbo();
	
	/**
	 * Obtener tipo frasco cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerTipoFrascoCbo();
	
	/**
	 * Obtener persona muestrea cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerPersonaMuestreaCbo();
	
	/**
	 * Obtener recep analista cbo.
	 *
	 * @return the list
	 */
	List<BeanComodin> obtenerRecepAnalistaCbo();

}
