/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ColaboradorBean.
 */
public class ColaboradorBean extends BaseBean implements Serializable {
	
	
	/*
	 * La clase ColaboradorBean representa a un objeto que extiende de BaseBean.
	*/

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lon codigo. */
	private Long lonCodigo;
    
    /** The nombre colaborador. */
    private String nombreColaborador;
    
    /** The dni. */
    private String dni;
    
    /** The nombre empresa. */
    private String nombreEmpresa;
    
    /** The tipo trabajador. */
    private String tipoTrabajador;
    
    /** The abreviatura. */
    private String abreviatura;
    
    /** The estado. */
    private String estado;
   
	
    /** The codigo. */
    private String codigo;
	
	/**
	 * Instantiates a new colaborador bean.
	 */
	public ColaboradorBean() {
	}



	/**
	 * Instantiates a new colaborador bean.
	 *
	 * @param nombreColaborador the nombre colaborador
	 * @param dni the dni
	 * @param nombreEmpresa the nombre empresa
	 * @param nombreRepresa the nombre represa
	 * @param tipoTrabajador the tipo trabajador
	 * @param usuario the usuario
	 * @param abreviatura the abreviatura
	 * @param estado the estado
	 * @param codigo the codigo
	 */
	public ColaboradorBean(String nombreColaborador, String dni, String nombreEmpresa, String nombreRepresa,
			String tipoTrabajador, String usuario, String abreviatura, String estado, String codigo) {
		super();
		this.nombreColaborador = nombreColaborador;
		this.dni = dni;
		this.nombreEmpresa = nombreEmpresa;
		this.tipoTrabajador = tipoTrabajador;
		this.abreviatura = abreviatura;
		this.estado = estado;
		this.codigo = codigo;
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
	 * Gets the nombre colaborador.
	 *
	 * @return the nombre colaborador
	 */
	public String getNombreColaborador() {
		return nombreColaborador;
	}

	/**
	 * Sets the nombre colaborador.
	 *
	 * @param nombreColaborador the new nombre colaborador
	 */
	public void setNombreColaborador(String nombreColaborador) {
		this.nombreColaborador = nombreColaborador;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa the new nombre empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the tipo trabajador.
	 *
	 * @return the tipo trabajador
	 */
	public String getTipoTrabajador() {
		return tipoTrabajador;
	}

	/**
	 * Sets the tipo trabajador.
	 *
	 * @param tipoTrabajador the new tipo trabajador
	 */
	public void setTipoTrabajador(String tipoTrabajador) {
		this.tipoTrabajador = tipoTrabajador;
	}
}
