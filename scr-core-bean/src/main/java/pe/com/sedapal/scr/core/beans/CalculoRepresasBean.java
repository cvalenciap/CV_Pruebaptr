/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculoRepresasBean.
 */
public class CalculoRepresasBean extends BaseBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The int id represas. */
	private Integer intIdRepresas;
	
	/** The str formula volumen trasvasado. */
	private String strFormulaVolumenTrasvasado;
	
	/** The bd valor volumen trasvasado. */
	private BigDecimal bdValorVolumenTrasvasado;
	
	/** The str fecha. */
	private String strFecha;
	
	/**
	 * Gets the int id represas.
	 *
	 * @return the int id represas
	 */
	public Integer getIntIdRepresas() {
		return intIdRepresas;
	}
	
	/**
	 * Sets the int id represas.
	 *
	 * @param intIdRepresas the new int id represas
	 */
	public void setIntIdRepresas(Integer intIdRepresas) {
		this.intIdRepresas = intIdRepresas;
	}
	
	/**
	 * Gets the str formula volumen trasvasado.
	 *
	 * @return the str formula volumen trasvasado
	 */
	public String getStrFormulaVolumenTrasvasado() {
		return strFormulaVolumenTrasvasado;
	}
	
	/**
	 * Sets the str formula volumen trasvasado.
	 *
	 * @param strFormulaVolumenTrasvasado the new str formula volumen trasvasado
	 */
	public void setStrFormulaVolumenTrasvasado(String strFormulaVolumenTrasvasado) {
		this.strFormulaVolumenTrasvasado = strFormulaVolumenTrasvasado;
	}
	
	/**
	 * Gets the bd valor volumen trasvasado.
	 *
	 * @return the bd valor volumen trasvasado
	 */
	public BigDecimal getBdValorVolumenTrasvasado() {
		return bdValorVolumenTrasvasado;
	}
	
	/**
	 * Sets the bd valor volumen trasvasado.
	 *
	 * @param bdValorVolumenTrasvasado the new bd valor volumen trasvasado
	 */
	public void setBdValorVolumenTrasvasado(BigDecimal bdValorVolumenTrasvasado) {
		this.bdValorVolumenTrasvasado = bdValorVolumenTrasvasado;
	}
	
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
}
