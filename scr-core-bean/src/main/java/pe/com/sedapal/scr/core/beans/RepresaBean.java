/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RepresaBean.
 */
public class RepresaBean extends BaseBean implements Serializable {

	/*
	 * La clase RepresaBean representa a un objeto que extiende de BaseBean.
	*/
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The int codigo. */
	private Integer intCodigo;
    
    /** The nombre represa. */
    private String nombreRepresa;
    
    /** The abreviatura. */
    private String abreviatura;
    
    /** The str tope maximo. */
    private String strTopeMaximo;
    
    /** The estado. */
    private String estado;
    
    /** The pertenece almacenamiento. */
    private String perteneceAlmacenamiento;
   
    /** The codigo. */
    private String codigo;
	
	/**
	 * Instantiates a new represa bean.
	 */
	public RepresaBean() {
		this.perteneceAlmacenamiento = "0";
	}

	/**
	 * Instantiates a new represa bean.
	 *
	 * @param nombreRepresa the nombre represa
	 * @param abreviatura the abreviatura
	 * @param estado the estado
	 */
	public RepresaBean(String nombreRepresa,  String abreviatura, String estado) {
		super();
		this.nombreRepresa = nombreRepresa;
		this.abreviatura = abreviatura;
		this.estado = estado;
		
	}

	/**
	 * Gets the int codigo.
	 *
	 * @return the int codigo
	 */
	public Integer getIntCodigo() {
		return intCodigo;
	}

	/**
	 * Sets the int codigo.
	 *
	 * @param intCodigo the new int codigo
	 */
	public void setIntCodigo(Integer intCodigo) {
		this.intCodigo = intCodigo;
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

	/**
	 * Gets the pertenece almacenamiento.
	 *
	 * @return the pertenece almacenamiento
	 */
	public String getPerteneceAlmacenamiento() {
		return perteneceAlmacenamiento;
	}

	/**
	 * Sets the pertenece almacenamiento.
	 *
	 * @param perteneceAlmacenamiento the new pertenece almacenamiento
	 */
	public void setPerteneceAlmacenamiento(String perteneceAlmacenamiento) {
		this.perteneceAlmacenamiento = perteneceAlmacenamiento;
	}

	/**
	 * Gets the str tope maximo.
	 *
	 * @return the str tope maximo
	 */
	public String getStrTopeMaximo() {
		return strTopeMaximo;
	}

	/**
	 * Sets the str tope maximo.
	 *
	 * @param strTopeMaximo the new str tope maximo
	 */
	public void setStrTopeMaximo(String strTopeMaximo) {
		this.strTopeMaximo = strTopeMaximo;
	}
}
