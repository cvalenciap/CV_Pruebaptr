/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosRepresasGerenciaBean.
 */
public class DatosRepresasGerenciaBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The str fecha. */
	private String strFecha;
	
	/** The str hora corte. */
	private String strHoraCorte;
	
	/** The int codigo represa. */
	private Integer intCodigoRepresa;
	
	/** The str nombre represa. */
	private String strNombreRepresa;
	
	/** The bd tope maximo represa. */
	private BigDecimal bdTopeMaximoRepresa;
	
	/** The int codigo represas. */
	private Integer intCodigoRepresas;
	
	/** The bd volumen. */
	private BigDecimal bdVolumen;
	
	/** The bd volumen total. */
	private BigDecimal bdVolumenTotal;
	
	/** The bd descarga. */
	private BigDecimal bdDescarga;
	
	/** The str nombre clima. */
	private String strNombreClima;
	
	/**
	 * Gets the str fecha.
	 *
	 * @return the str fecha
	 */
	public String getStrFecha() {
		return strFecha;
	}
	
	/**
	 * Sets the str fecha.
	 *
	 * @param strFecha the new str fecha
	 */
	public void setStrFecha(String strFecha) {
		this.strFecha = strFecha;
	}
	
	/**
	 * Gets the int codigo represa.
	 *
	 * @return the int codigo represa
	 */
	public Integer getIntCodigoRepresa() {
		return intCodigoRepresa;
	}
	
	/**
	 * Sets the int codigo represa.
	 *
	 * @param intCodigoRepresa the new int codigo represa
	 */
	public void setIntCodigoRepresa(Integer intCodigoRepresa) {
		this.intCodigoRepresa = intCodigoRepresa;
	}
	
	/**
	 * Gets the str nombre represa.
	 *
	 * @return the str nombre represa
	 */
	public String getStrNombreRepresa() {
		return strNombreRepresa;
	}
	
	/**
	 * Sets the str nombre represa.
	 *
	 * @param strNombreRepresa the new str nombre represa
	 */
	public void setStrNombreRepresa(String strNombreRepresa) {
		this.strNombreRepresa = strNombreRepresa;
	}
	
	/**
	 * Gets the int codigo represas.
	 *
	 * @return the int codigo represas
	 */
	public Integer getIntCodigoRepresas() {
		return intCodigoRepresas;
	}
	
	/**
	 * Sets the int codigo represas.
	 *
	 * @param intCodigoRepresas the new int codigo represas
	 */
	public void setIntCodigoRepresas(Integer intCodigoRepresas) {
		this.intCodigoRepresas = intCodigoRepresas;
	}
	
	/**
	 * Gets the bd volumen.
	 *
	 * @return the bd volumen
	 */
	public BigDecimal getBdVolumen() {
		return bdVolumen;
	}
	
	/**
	 * Sets the bd volumen.
	 *
	 * @param bdVolumen the new bd volumen
	 */
	public void setBdVolumen(BigDecimal bdVolumen) {
		this.bdVolumen = bdVolumen;
	}
	
	/**
	 * Gets the bd volumen total.
	 *
	 * @return the bd volumen total
	 */
	public BigDecimal getBdVolumenTotal() {
		return bdVolumenTotal;
	}
	
	/**
	 * Sets the bd volumen total.
	 *
	 * @param bdVolumenTotal the new bd volumen total
	 */
	public void setBdVolumenTotal(BigDecimal bdVolumenTotal) {
		this.bdVolumenTotal = bdVolumenTotal;
	}
	
	/**
	 * Gets the bd descarga.
	 *
	 * @return the bd descarga
	 */
	public BigDecimal getBdDescarga() {
		return bdDescarga;
	}
	
	/**
	 * Sets the bd descarga.
	 *
	 * @param bdDescarga the new bd descarga
	 */
	public void setBdDescarga(BigDecimal bdDescarga) {
		this.bdDescarga = bdDescarga;
	}
	
	/**
	 * Gets the str nombre clima.
	 *
	 * @return the str nombre clima
	 */
	public String getStrNombreClima() {
		return strNombreClima;
	}
	
	/**
	 * Sets the str nombre clima.
	 *
	 * @param strNombreClima the new str nombre clima
	 */
	public void setStrNombreClima(String strNombreClima) {
		this.strNombreClima = strNombreClima;
	}
	
	/**
	 * Gets the str hora corte.
	 *
	 * @return the str hora corte
	 */
	public String getStrHoraCorte() {
		return strHoraCorte;
	}
	
	/**
	 * Sets the str hora corte.
	 *
	 * @param strHoraCorte the new str hora corte
	 */
	public void setStrHoraCorte(String strHoraCorte) {
		this.strHoraCorte = strHoraCorte;
	}
	
	/**
	 * Gets the bd tope maximo represa.
	 *
	 * @return the bd tope maximo represa
	 */
	public BigDecimal getBdTopeMaximoRepresa() {
		return bdTopeMaximoRepresa;
	}
	
	/**
	 * Sets the bd tope maximo represa.
	 *
	 * @param bdTopeMaximoRepresa the new bd tope maximo represa
	 */
	public void setBdTopeMaximoRepresa(BigDecimal bdTopeMaximoRepresa) {
		this.bdTopeMaximoRepresa = bdTopeMaximoRepresa;
	}
}
