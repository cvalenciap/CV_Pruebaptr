/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.scr.core.beans.ItemPlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IParametroXSubactividadDao.
 */
@Repository
public interface IParametroXSubactividadDao {

	/**
	 * Actualizar parametro X subactividad.
	 *
	 * @param subactividadXGrupo the subactividad X grupo
	 */
	public void actualizarParametroXSubactividad(ItemPlanOperativo subactividadXGrupo);
	
	/**
	 * Insertar parametro X subactividad.
	 *
	 * @param subactividadXGrupo the subactividad X grupo
	 */
	public void insertarParametroXSubactividad(ItemPlanOperativo subactividadXGrupo);
	
	/**
	 * Existe parametro X subactividad.
	 *
	 * @param idSubactividadXActividad the id subactividad X actividad
	 * @param idParametro the id parametro
	 * @return true, if successful
	 */
	public boolean existeParametroXSubactividad(Integer idSubactividadXActividad, Integer idParametro);
	
	/**
	 * Listar sub actividades.
	 *
	 * @param idActividad the id actividad
	 * @return the list
	 */
	public List<ItemPlanOperativo> listarSubActividades(Integer idActividad);
	
	/**
	 * Listar parametros.
	 *
	 * @param idActividad the id actividad
	 * @return the list
	 */
	public List<SelectItemBean> listarParametros(Integer idActividad);
	
}
