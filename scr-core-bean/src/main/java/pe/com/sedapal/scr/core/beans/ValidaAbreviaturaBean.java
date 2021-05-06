/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidaAbreviaturaBean.
 */
public class ValidaAbreviaturaBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The bol abreviatura. */
	private boolean bolAbreviatura;
	
	/** The bol nombre largo. */
	private boolean bolNombreLargo;
	
	/**
	 * Checks if is bol abreviatura.
	 *
	 * @return true, if is bol abreviatura
	 */
	public boolean isBolAbreviatura() {
		return bolAbreviatura;
	}
	
	/**
	 * Sets the bol abreviatura.
	 *
	 * @param bolAbreviatura the new bol abreviatura
	 */
	public void setBolAbreviatura(boolean bolAbreviatura) {
		this.bolAbreviatura = bolAbreviatura;
	}
	
	/**
	 * Checks if is bol nombre largo.
	 *
	 * @return true, if is bol nombre largo
	 */
	public boolean isBolNombreLargo() {
		return bolNombreLargo;
	}
	
	/**
	 * Sets the bol nombre largo.
	 *
	 * @param bolNombreLargo the new bol nombre largo
	 */
	public void setBolNombreLargo(boolean bolNombreLargo) {
		this.bolNombreLargo = bolNombreLargo;
	}
}
