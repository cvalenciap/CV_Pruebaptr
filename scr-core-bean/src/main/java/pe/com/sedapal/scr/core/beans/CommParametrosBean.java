/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CommParametrosBean.
 */
public class CommParametrosBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo. */
	public Integer codigo;
	
	/** The usuario creacion. */
	public String usuarioCreacion;
	
	/** The fecha creacion. */
	public String fechaCreacion;
	
	/** The usuario modificacion. */
	public String usuarioModificacion;
	
	/** The fecha modificacion. */
	public String fechaModificacion;
	
	/** The nombre programa. */
	public String nombrePrograma;
	
	/** The estado. */
	public Integer estado;
	
	/** The descripcion. */
	public String descripcion;
	
	/** The abreviatura. */
	public String abreviatura;
	
	/** The valor. */
	public String valor;
	
	/** The codigo dependencia. */
	public Integer codigoDependencia;

	/**
	 * Instantiates a new comm parametros bean.
	 */
	public CommParametrosBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new comm parametros bean.
	 *
	 * @param codigo the codigo
	 * @param usuarioCreacion the usuario creacion
	 * @param fechaCreacion the fecha creacion
	 * @param usuarioModificacion the usuario modificacion
	 * @param fechaModificacion the fecha modificacion
	 * @param nombrePrograma the nombre programa
	 * @param estado the estado
	 * @param descripcion the descripcion
	 * @param abreviatura the abreviatura
	 * @param valor the valor
	 * @param codigoDependencia the codigo dependencia
	 */
	public CommParametrosBean(Integer codigo, String usuarioCreacion, String fechaCreacion, String usuarioModificacion,
			String fechaModificacion, String nombrePrograma, Integer estado, String descripcion, String abreviatura,
			String valor, Integer codigoDependencia) {
		super();
		this.codigo = codigo;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.nombrePrograma = nombrePrograma;
		this.estado = estado;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
		this.valor = valor;
		this.codigoDependencia = codigoDependencia;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the usuario creacion.
	 *
	 * @return the usuario creacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * Sets the usuario creacion.
	 *
	 * @param usuarioCreacion the new usuario creacion
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * Gets the fecha creacion.
	 *
	 * @return the fecha creacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets the fecha creacion.
	 *
	 * @param fechaCreacion the new fecha creacion
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Gets the usuario modificacion.
	 *
	 * @return the usuario modificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * Sets the usuario modificacion.
	 *
	 * @param usuarioModificacion the new usuario modificacion
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * Gets the fecha modificacion.
	 *
	 * @return the fecha modificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Sets the fecha modificacion.
	 *
	 * @param fechaModificacion the new fecha modificacion
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Gets the nombre programa.
	 *
	 * @return the nombre programa
	 */
	public String getNombrePrograma() {
		return nombrePrograma;
	}

	/**
	 * Sets the nombre programa.
	 *
	 * @param nombrePrograma the new nombre programa
	 */
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the codigo dependencia.
	 *
	 * @return the codigo dependencia
	 */
	public Integer getCodigoDependencia() {
		return codigoDependencia;
	}

	/**
	 * Sets the codigo dependencia.
	 *
	 * @param codigoDependencia the new codigo dependencia
	 */
	public void setCodigoDependencia(Integer codigoDependencia) {
		this.codigoDependencia = codigoDependencia;
	}

}
