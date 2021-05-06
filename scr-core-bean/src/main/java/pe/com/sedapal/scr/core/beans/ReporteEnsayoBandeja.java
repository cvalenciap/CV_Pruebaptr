/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteEnsayoBandeja.
 */
public class ReporteEnsayoBandeja {

	/** The id. */
	private Integer id;
	
	/** The fecha. */
	private String fecha;

	
	/** The detalles. */
	private List<ReporteEnsayoCloroResult> detalles;
	
	/**
	 * Gets the detalles.
	 *
	 * @return the detalles
	 */
	public List<ReporteEnsayoCloroResult> getDetalles() {
		return detalles;
	}
	
	/**
	 * Sets the detalles.
	 *
	 * @param detalles the new detalles
	 */
	public void setDetalles(List<ReporteEnsayoCloroResult> detalles) {
		this.detalles = detalles;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReporteEnsayoBandeja [id=" + id + ", fecha=" + fecha + ", detalles=" + detalles + "]";
	}
	
}
