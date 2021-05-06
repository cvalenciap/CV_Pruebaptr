/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRepresaBean.
 */
public class ReporteRepresaBean implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cod mant represa. */
	private Long codMantRepresa;
	
	/** The nombre represa. */
	private String nombreRepresa;
	
	/** The str abreviatura. */
	private String strAbreviatura;
	
	/** The dia. */
	private String dia;
	
	/** The prom volumen. */
	private BigDecimal promVolumen;
	
	/** The prom precitpitaciones. */
	private BigDecimal promPrecitpitaciones;
	
	/** The str nombre maniobra. */
	private String strNombreManiobra;
	
	/** The tipo maniobra. */
	private Integer tipoManiobra;
	
	/** The bd valor maniobra. */
	private BigDecimal bdValorManiobra;
	
	/**
	 * Gets the cod mant represa.
	 *
	 * @return the cod mant represa
	 */
	public Long getCodMantRepresa() {
		return codMantRepresa;
	}
	
	/**
	 * Sets the cod mant represa.
	 *
	 * @param codMantRepresa the new cod mant represa
	 */
	public void setCodMantRepresa(Long codMantRepresa) {
		this.codMantRepresa = codMantRepresa;
	}
	
	/**
	 * Gets the nombre represa.
	 *
	 * @return the nombre represa
	 */
	public String getNombreRepresa() {
		return nombreRepresa;
	}
	
	/**
	 * Sets the nombre represa.
	 *
	 * @param nombreRepresa the new nombre represa
	 */
	public void setNombreRepresa(String nombreRepresa) {
		this.nombreRepresa = nombreRepresa;
	}
	
	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	/**
	 * Gets the prom volumen.
	 *
	 * @return the prom volumen
	 */
	public BigDecimal getPromVolumen() {
		return promVolumen;
	}
	
	/**
	 * Sets the prom volumen.
	 *
	 * @param promVolumen the new prom volumen
	 */
	public void setPromVolumen(BigDecimal promVolumen) {
		this.promVolumen = promVolumen;
	}
	
	/**
	 * Gets the prom precitpitaciones.
	 *
	 * @return the prom precitpitaciones
	 */
	public BigDecimal getPromPrecitpitaciones() {
		return promPrecitpitaciones;
	}
	
	/**
	 * Sets the prom precitpitaciones.
	 *
	 * @param promPrecitpitaciones the new prom precitpitaciones
	 */
	public void setPromPrecitpitaciones(BigDecimal promPrecitpitaciones) {
		this.promPrecitpitaciones = promPrecitpitaciones;
	}
	
	/**
	 * Gets the bd valor maniobra.
	 *
	 * @return the bd valor maniobra
	 */
	public BigDecimal getBdValorManiobra() {
		return bdValorManiobra;
	}
	
	/**
	 * Sets the bd valor maniobra.
	 *
	 * @param bdValorManiobra the new bd valor maniobra
	 */
	public void setBdValorManiobra(BigDecimal bdValorManiobra) {
		this.bdValorManiobra = bdValorManiobra;
	}
	
	/**
	 * Gets the tipo maniobra.
	 *
	 * @return the tipo maniobra
	 */
	public Integer getTipoManiobra() {
		return tipoManiobra;
	}
	
	/**
	 * Sets the tipo maniobra.
	 *
	 * @param tipoManiobra the new tipo maniobra
	 */
	public void setTipoManiobra(Integer tipoManiobra) {
		this.tipoManiobra = tipoManiobra;
	}
	
	/**
	 * Gets the str abreviatura.
	 *
	 * @return the str abreviatura
	 */
	public String getStrAbreviatura() {
		return strAbreviatura;
	}
	
	/**
	 * Sets the str abreviatura.
	 *
	 * @param strAbreviatura the new str abreviatura
	 */
	public void setStrAbreviatura(String strAbreviatura) {
		this.strAbreviatura = strAbreviatura;
	}
	
	/**
	 * Gets the str nombre maniobra.
	 *
	 * @return the str nombre maniobra
	 */
	public String getStrNombreManiobra() {
		return strNombreManiobra;
	}
	
	/**
	 * Sets the str nombre maniobra.
	 *
	 * @param strNombreManiobra the new str nombre maniobra
	 */
	public void setStrNombreManiobra(String strNombreManiobra) {
		this.strNombreManiobra = strNombreManiobra;
	}
}
