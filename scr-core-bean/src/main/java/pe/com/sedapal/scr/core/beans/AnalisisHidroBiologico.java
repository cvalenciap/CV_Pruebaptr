/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHidroBiologico.
 */
public class AnalisisHidroBiologico extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private Integer codigo;
	
	/** The codsub. */
	private Integer codsub;
	
	/** The fecha analisis. */
	private String fechaAnalisis;
	
	/** The tipo organismo. */
	private Integer tipoOrganismo;
	
	/** The codorg. */
	private Integer codorg;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The cantidad. */
	private String cantidad;
	
	/**
	 * Gets the tipo organismo.
	 *
	 * @return the tipo organismo
	 */
	public Integer getTipoOrganismo() {
		return tipoOrganismo;
	}
	
	/**
	 * Sets the tipo organismo.
	 *
	 * @param tipoOrganismo the new tipo organismo
	 */
	public void setTipoOrganismo(Integer tipoOrganismo) {
		this.tipoOrganismo = tipoOrganismo;
	}
	
	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}
	
	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
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
	 * Gets the codorg.
	 *
	 * @return the codorg
	 */
	public Integer getCodorg() {
		return codorg;
	}
	
	/**
	 * Sets the codorg.
	 *
	 * @param codorg the new codorg
	 */
	public void setCodorg(Integer codorg) {
		this.codorg = codorg;
	}

	/**
	 * Gets the codsub.
	 *
	 * @return the codsub
	 */
	public Integer getCodsub() {
		return codsub;
	}
	
	/**
	 * Sets the codsub.
	 *
	 * @param codsub the new codsub
	 */
	public void setCodsub(Integer codsub) {
		this.codsub = codsub;
	}

	/**
	 * Gets the fecha analisis.
	 *
	 * @return the fecha analisis
	 */
	public String getFechaAnalisis() {
		return fechaAnalisis;
	}
	
	/**
	 * Sets the fecha analisis.
	 *
	 * @param fechaAnalisis the new fecha analisis
	 */
	public void setFechaAnalisis(String fechaAnalisis) {
		this.fechaAnalisis = fechaAnalisis;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnalisisHidroBiologico [codigo=" + codigo + ", codsub=" + codsub + ", fechaAnalisis=" + fechaAnalisis
				+ ", tipoOrganismo=" + tipoOrganismo + ", codorg=" + codorg + ", descripcion=" + descripcion + "]";
	}
	
}
