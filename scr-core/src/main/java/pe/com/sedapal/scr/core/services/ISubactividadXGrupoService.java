/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.scr.core.beans.ItemPlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISubactividadXGrupoService.
 */
public interface ISubactividadXGrupoService {

/**
 * Actualizar subactividad X grupo.
 *
 * @param subactividadXGrupo the subactividad X grupo
 */
public void actualizarSubactividadXGrupo(ItemPlanOperativo subactividadXGrupo);
	
	/**
	 * Insertar subactividad X grupo.
	 *
	 * @param subactividadXGrupo the subactividad X grupo
	 */
	public void insertarSubactividadXGrupo(ItemPlanOperativo subactividadXGrupo);
	
	/**
	 * Existe subactividad X grupo.
	 *
	 * @param idGrupo the id grupo
	 * @param idSubactividad the id subactividad
	 * @return true, if successful
	 */
	public boolean existeSubactividadXGrupo(Integer idGrupo, Integer idSubactividad);
	
	/**
	 * Listar subactividades.
	 *
	 * @param idGrupo the id grupo
	 * @return the list
	 */
	public List<SelectItemBean> listarSubactividades(Integer idGrupo);
	
	/**
	 * Listar actividades.
	 *
	 * @param idGrupo the id grupo
	 * @return the list
	 */
	public List<ItemPlanOperativo> listarActividades(Integer idGrupo);
}
