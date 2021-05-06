/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CaudalSearchBean.
 */
public class CaudalSearchBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The str nombre largo. */
	private String strNombreLargo;
	
	/** The str tipo proceso. */
	private String strTipoProceso;
	
	/** The str periodo. */
	private String strPeriodo;
	
	/**
	 * Gets the str nombre largo.
	 *
	 * @return the str nombre largo
	 */
	public String getStrNombreLargo() {
		return strNombreLargo;
	}
	
	/**
	 * Sets the str nombre largo.
	 *
	 * @param strNombreLargo the new str nombre largo
	 */
	public void setStrNombreLargo(String strNombreLargo) {
		this.strNombreLargo = strNombreLargo;
	}
	
	/**
	 * Gets the str tipo proceso.
	 *
	 * @return the str tipo proceso
	 */
	public String getStrTipoProceso() {
		return strTipoProceso;
	}
	
	/**
	 * Sets the str tipo proceso.
	 *
	 * @param strTipoProceso the new str tipo proceso
	 */
	public void setStrTipoProceso(String strTipoProceso) {
		this.strTipoProceso = strTipoProceso;
	}
	
	/**
	 * Gets the str periodo.
	 *
	 * @return the str periodo
	 */
	public String getStrPeriodo() {
		return strPeriodo;
	}
	
	/**
	 * Sets the str periodo.
	 *
	 * @param strPeriodo the new str periodo
	 */
	public void setStrPeriodo(String strPeriodo) {
		this.strPeriodo = strPeriodo;
	}
}
