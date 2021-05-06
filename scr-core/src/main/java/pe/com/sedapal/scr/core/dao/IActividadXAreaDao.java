/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.scr.core.beans.ActividadArea;
import pe.com.sedapal.scr.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IActividadXAreaDao.
 */
@Repository
public interface IActividadXAreaDao {

	/**
	 * Actualizar actividad X area.
	 *
	 * @param actividadArea the actividad area
	 */
	public void actualizarActividadXArea(ActividadArea actividadArea);
	
	/**
	 * Insertar actividad.
	 *
	 * @param actividadArea the actividad area
	 */
	public void insertarActividad(ActividadArea actividadArea);
	
	/**
	 * Existe actividad.
	 *
	 * @param idArea the id area
	 * @param idACtividad the id A ctividad
	 * @return true, if successful
	 */
	public boolean existeActividad(Integer idArea,Integer idACtividad);
	
	/**
	 * Listar actividades.
	 *
	 * @param idArea the id area
	 * @return the list
	 */
	public List<SelectItemBean> listarActividades(Integer idArea);
}
