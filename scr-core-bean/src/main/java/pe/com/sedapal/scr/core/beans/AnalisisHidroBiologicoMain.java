/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHidroBiologicoMain.
 */
public class AnalisisHidroBiologicoMain  extends BaseBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codsub. */
	private Integer codsub;
	
	/** The tipo organismo. */
	private Integer tipoOrganismo;
	
	/** The localidad. */
	private String localidad;
	
	/** The fecha. */
	private String fecha;
	
	/** The hora. */
	private String hora;
	
	/** The list analisis. */
	private ArrayList<AnalisisHidroBiologico> listAnalisis;

	
	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the tipo organismo.
	 *
	 * @return the tipoOrganismo
	 */
	public Integer getTipoOrganismo() {
		return tipoOrganismo;
	}

	/**
	 * Sets the tipo organismo.
	 *
	 * @param tipoOrganismo the tipoOrganismo to set
	 */
	public void setTipoOrganismo(Integer tipoOrganismo) {
		this.tipoOrganismo = tipoOrganismo;
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
	 * @param codsub the codsub to set
	 */
	public void setCodsub(Integer codsub) {
		this.codsub = codsub;
	}

	/**
	 * Gets the list analisis.
	 *
	 * @return the listAnalisis
	 */
	public ArrayList<AnalisisHidroBiologico> getListAnalisis() {
		return listAnalisis;
	}

	/**
	 * Sets the list analisis.
	 *
	 * @param listAnalisis the listAnalisis to set
	 */
	public void setListAnalisis(ArrayList<AnalisisHidroBiologico> listAnalisis) {
		this.listAnalisis = listAnalisis;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnalisisHidroBiologicoMain [codsub=" + codsub + ", tipoOrganismo=" + tipoOrganismo + ", localidad="
				+ localidad + ", fecha=" + fecha + ", hora=" + hora + ", listAnalisis=" + listAnalisis + "]";
	}

	

}
