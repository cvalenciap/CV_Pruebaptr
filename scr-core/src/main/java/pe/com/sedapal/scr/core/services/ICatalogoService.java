/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.scr.core.beans.SelectItemBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICatalogoService.
 */
public interface ICatalogoService {

	/**
	 * Obtener catalogo.
	 *
	 * @param idTabla the id tabla
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla);
	
	/**
	 * Obtener catalogo todos ninguno.
	 *
	 * @param idTabla the id tabla
	 * @param flag the flag
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogoTodosNinguno(Integer idTabla, Integer flag);
	
	/**
	 * Obtener catalogo seleccionar.
	 *
	 * @param idTabla the id tabla
	 * @param flag the flag
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogoSeleccionar(Integer idTabla, Integer flag);
	
	/**
	 * Obtener catalogo.
	 *
	 * @param idTabla the id tabla
	 * @param columnToOrder the column to order
	 * @return the list
	 */
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla,String columnToOrder);
}
