/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ClimaBean.
 */
public class ClimaBean extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The lon codigo. */
	/*
	 * La clase ClimaBean representa a un objeto que extiende de BaseBean.
	*/
	private Long lonCodigo;
    
    /** The nombre clima. */
    private String nombreClima;
    
    /** The abreviatura. */
    private String abreviatura;
    
    /** The estado. */
    private String estado;
	
    /** The codigo. */
    private String codigo;
	
	/**
	 * Instantiates a new clima bean.
	 */
	public ClimaBean() {
	}


	/**
	 * Instantiates a new clima bean.
	 *
	 * @param nombreClima the nombre clima
	 * @param abreviatura the abreviatura
	 * @param estado the estado
	 */
	public ClimaBean(String nombreClima,  String abreviatura, String estado) {
		super();
		this.nombreClima = nombreClima;
		this.abreviatura = abreviatura;
		this.estado = estado;
		
	}

	/**
	 * Gets the lon codigo.
	 *
	 * @return the lon codigo
	 */
	public Long getLonCodigo() {
		return lonCodigo;
	}


	/**
	 * Sets the lon codigo.
	 *
	 * @param lonCodigo the new lon codigo
	 */
	public void setLonCodigo(Long lonCodigo) {
		this.lonCodigo = lonCodigo;
	}


	/**
	 * Gets the nombre clima.
	 *
	 * @return the nombre clima
	 */
	public String getNombreClima() {
		return nombreClima;
	}

	/**
	 * Sets the nombre clima.
	 *
	 * @param nombreClima the new nombre clima
	 */
	public void setNombreClima(String nombreClima) {
		this.nombreClima = nombreClima;
	}

	/**
	 * Gets the abreviatura.
	 *
	 * @return the abreviatura
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * Sets the abreviatura.
	 *
	 * @param abreviatura the new abreviatura
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
