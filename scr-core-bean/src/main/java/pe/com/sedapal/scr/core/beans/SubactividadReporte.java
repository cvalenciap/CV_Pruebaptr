/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SubactividadReporte.
 */
public class SubactividadReporte extends BaseBean implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private Integer codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The estado. */
	private Integer estado;
	
	/** The parametros. */
	private ArrayList<ParametroReporte> parametros;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	/**
	 * Gets the parametros.
	 *
	 * @return the parametros
	 */
	public ArrayList<ParametroReporte> getParametros() {
		return parametros;
	}
	
	/**
	 * Sets the parametros.
	 *
	 * @param parametros the new parametros
	 */
	public void setParametros(ArrayList<ParametroReporte> parametros) {
		this.parametros = parametros;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubactividadReporte [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", parametros=" + parametros + "]";
	}
		
}
