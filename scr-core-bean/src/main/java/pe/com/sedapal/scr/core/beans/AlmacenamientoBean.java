/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class AlmacenamientoBean.
 */
public class AlmacenamientoBean implements Serializable  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The str dia. */
	private String strDia;
	
	/** The bd valor volumen. */
	private BigDecimal bdValorVolumen;
	
	/** The bd porcentaje volumen. */
	private BigDecimal bdPorcentajeVolumen;
	
	/** The bd valor descarga. */
	private BigDecimal bdValorDescarga;
	
	/** The bd porcentaje descarga. */
	private BigDecimal bdPorcentajeDescarga;
	
	/** The bd volumen total. */
	private BigDecimal bdVolumenTotal;
	
	/** The bd Q regulado. */
	private BigDecimal bdQRegulado;
	
	/** The bd Q descarga. */
	private BigDecimal bdQDescarga;
	
	/** The bd Q natural. */
	private BigDecimal bdQNatural;
	
	/** The db columna uno. */
	private BigDecimal dbColumnaUno;
	
	/** The db columna dos. */
	private BigDecimal dbColumnaDos;
	
	/** The str fecha registro. */
	private String strFechaRegistro;
	
	/** The str formula almacenamiento. */
	private String strFormulaAlmacenamiento;
	
	/** The str texto formula almacenamiento. */
	private String strTextoFormulaAlmacenamiento;
	
	/** The str formula maniobra descarga. */
	private String strFormulaManiobraDescarga;
	
	/** The str texto formula maniobra descarga. */
	private String strTextoFormulaManiobraDescarga;
	
	/** The str texto formula columna uno. */
	private String strTextoFormulaColumnaUno;
	
	/** The str texto formula columna dos. */
	private String strTextoFormulaColumnaDos;
	
	/**
	 * Gets the str dia.
	 *
	 * @return the str dia
	 */
	public String getStrDia() {
		return strDia;
	}
	
	/**
	 * Sets the str dia.
	 *
	 * @param strDia the new str dia
	 */
	public void setStrDia(String strDia) {
		this.strDia = strDia;
	}
	
	/**
	 * Gets the bd valor volumen.
	 *
	 * @return the bd valor volumen
	 */
	public BigDecimal getBdValorVolumen() {
		return bdValorVolumen;
	}
	
	/**
	 * Sets the bd valor volumen.
	 *
	 * @param bdValorVolumen the new bd valor volumen
	 */
	public void setBdValorVolumen(BigDecimal bdValorVolumen) {
		this.bdValorVolumen = bdValorVolumen;
	}
	
	/**
	 * Gets the bd porcentaje volumen.
	 *
	 * @return the bd porcentaje volumen
	 */
	public BigDecimal getBdPorcentajeVolumen() {
		return bdPorcentajeVolumen;
	}
	
	/**
	 * Sets the bd porcentaje volumen.
	 *
	 * @param bdPorcentajeVolumen the new bd porcentaje volumen
	 */
	public void setBdPorcentajeVolumen(BigDecimal bdPorcentajeVolumen) {
		this.bdPorcentajeVolumen = bdPorcentajeVolumen;
	}
	
	/**
	 * Gets the bd valor descarga.
	 *
	 * @return the bd valor descarga
	 */
	public BigDecimal getBdValorDescarga() {
		return bdValorDescarga;
	}
	
	/**
	 * Sets the bd valor descarga.
	 *
	 * @param bdValorDescarga the new bd valor descarga
	 */
	public void setBdValorDescarga(BigDecimal bdValorDescarga) {
		this.bdValorDescarga = bdValorDescarga;
	}
	
	/**
	 * Gets the bd porcentaje descarga.
	 *
	 * @return the bd porcentaje descarga
	 */
	public BigDecimal getBdPorcentajeDescarga() {
		return bdPorcentajeDescarga;
	}
	
	/**
	 * Sets the bd porcentaje descarga.
	 *
	 * @param bdPorcentajeDescarga the new bd porcentaje descarga
	 */
	public void setBdPorcentajeDescarga(BigDecimal bdPorcentajeDescarga) {
		this.bdPorcentajeDescarga = bdPorcentajeDescarga;
	}
	
	/**
	 * Gets the str fecha registro.
	 *
	 * @return the str fecha registro
	 */
	public String getStrFechaRegistro() {
		return strFechaRegistro;
	}
	
	/**
	 * Sets the str fecha registro.
	 *
	 * @param strFechaRegistro the new str fecha registro
	 */
	public void setStrFechaRegistro(String strFechaRegistro) {
		this.strFechaRegistro = strFechaRegistro;
	}
	
	/**
	 * Gets the str formula almacenamiento.
	 *
	 * @return the str formula almacenamiento
	 */
	public String getStrFormulaAlmacenamiento() {
		return strFormulaAlmacenamiento;
	}
	
	/**
	 * Sets the str formula almacenamiento.
	 *
	 * @param strFormulaAlmacenamiento the new str formula almacenamiento
	 */
	public void setStrFormulaAlmacenamiento(String strFormulaAlmacenamiento) {
		this.strFormulaAlmacenamiento = strFormulaAlmacenamiento;
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
	 * Gets the bd Q regulado.
	 *
	 * @return the bd Q regulado
	 */
	public BigDecimal getBdQRegulado() {
		return bdQRegulado;
	}
	
	/**
	 * Sets the bd Q regulado.
	 *
	 * @param bdQRegulado the new bd Q regulado
	 */
	public void setBdQRegulado(BigDecimal bdQRegulado) {
		this.bdQRegulado = bdQRegulado;
	}
	
	/**
	 * Gets the bd Q descarga.
	 *
	 * @return the bd Q descarga
	 */
	public BigDecimal getBdQDescarga() {
		return bdQDescarga;
	}
	
	/**
	 * Sets the bd Q descarga.
	 *
	 * @param bdQDescarga the new bd Q descarga
	 */
	public void setBdQDescarga(BigDecimal bdQDescarga) {
		this.bdQDescarga = bdQDescarga;
	}
	
	/**
	 * Gets the bd Q natural.
	 *
	 * @return the bd Q natural
	 */
	public BigDecimal getBdQNatural() {
		return bdQNatural;
	}
	
	/**
	 * Sets the bd Q natural.
	 *
	 * @param bdQNatural the new bd Q natural
	 */
	public void setBdQNatural(BigDecimal bdQNatural) {
		this.bdQNatural = bdQNatural;
	}
	
	/**
	 * Gets the str texto formula almacenamiento.
	 *
	 * @return the str texto formula almacenamiento
	 */
	public String getStrTextoFormulaAlmacenamiento() {
		return strTextoFormulaAlmacenamiento;
	}
	
	/**
	 * Sets the str texto formula almacenamiento.
	 *
	 * @param strTextoFormulaAlmacenamiento the new str texto formula almacenamiento
	 */
	public void setStrTextoFormulaAlmacenamiento(String strTextoFormulaAlmacenamiento) {
		this.strTextoFormulaAlmacenamiento = strTextoFormulaAlmacenamiento;
	}
	
	/**
	 * Gets the str formula maniobra descarga.
	 *
	 * @return the str formula maniobra descarga
	 */
	public String getStrFormulaManiobraDescarga() {
		return strFormulaManiobraDescarga;
	}
	
	/**
	 * Sets the str formula maniobra descarga.
	 *
	 * @param strFormulaManiobraDescarga the new str formula maniobra descarga
	 */
	public void setStrFormulaManiobraDescarga(String strFormulaManiobraDescarga) {
		this.strFormulaManiobraDescarga = strFormulaManiobraDescarga;
	}
	
	/**
	 * Gets the str texto formula maniobra descarga.
	 *
	 * @return the str texto formula maniobra descarga
	 */
	public String getStrTextoFormulaManiobraDescarga() {
		return strTextoFormulaManiobraDescarga;
	}
	
	/**
	 * Sets the str texto formula maniobra descarga.
	 *
	 * @param strTextoFormulaManiobraDescarga the new str texto formula maniobra descarga
	 */
	public void setStrTextoFormulaManiobraDescarga(String strTextoFormulaManiobraDescarga) {
		this.strTextoFormulaManiobraDescarga = strTextoFormulaManiobraDescarga;
	}
	
	/**
	 * Gets the db columna uno.
	 *
	 * @return the db columna uno
	 */
	public BigDecimal getDbColumnaUno() {
		return dbColumnaUno;
	}
	
	/**
	 * Sets the db columna uno.
	 *
	 * @param dbColumnaUno the new db columna uno
	 */
	public void setDbColumnaUno(BigDecimal dbColumnaUno) {
		this.dbColumnaUno = dbColumnaUno;
	}
	
	/**
	 * Gets the db columna dos.
	 *
	 * @return the db columna dos
	 */
	public BigDecimal getDbColumnaDos() {
		return dbColumnaDos;
	}
	
	/**
	 * Sets the db columna dos.
	 *
	 * @param dbColumnaDos the new db columna dos
	 */
	public void setDbColumnaDos(BigDecimal dbColumnaDos) {
		this.dbColumnaDos = dbColumnaDos;
	}
	
	/**
	 * Gets the str texto formula columna uno.
	 *
	 * @return the str texto formula columna uno
	 */
	public String getStrTextoFormulaColumnaUno() {
		return strTextoFormulaColumnaUno;
	}
	
	/**
	 * Sets the str texto formula columna uno.
	 *
	 * @param strTextoFormulaColumnaUno the new str texto formula columna uno
	 */
	public void setStrTextoFormulaColumnaUno(String strTextoFormulaColumnaUno) {
		this.strTextoFormulaColumnaUno = strTextoFormulaColumnaUno;
	}
	
	/**
	 * Gets the str texto formula columna dos.
	 *
	 * @return the str texto formula columna dos
	 */
	public String getStrTextoFormulaColumnaDos() {
		return strTextoFormulaColumnaDos;
	}
	
	/**
	 * Sets the str texto formula columna dos.
	 *
	 * @param strTextoFormulaColumnaDos the new str texto formula columna dos
	 */
	public void setStrTextoFormulaColumnaDos(String strTextoFormulaColumnaDos) {
		this.strTextoFormulaColumnaDos = strTextoFormulaColumnaDos;
	}
}
