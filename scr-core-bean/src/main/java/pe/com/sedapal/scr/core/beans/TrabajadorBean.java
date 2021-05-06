/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TrabajadorBean.
 */
public class TrabajadorBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private String codigo;
	
	/** The num ficha. */
	private String numFicha;
	
	/** The nombre completo. */
	private String nombreCompleto;
	
	/** The num documento. */
	private String numDocumento;
	
	/** The nombre empresa. */
	private String nombreEmpresa;
	
	/** The tipo trabajador. */
	private String tipoTrabajador;
	
	
	private String nombre;
	
	private String apellidoMaterno;
	
	private String apellidoPaterno;
	
	private String cargo;
	
	private String codigoEstado;
	
	private String ubicacion;
	
	private String grupoPersonal;
	
	private String dni;
	
	private String correo;
			
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getGrupoPersonal() {
		return grupoPersonal;
	}

	public void setGrupoPersonal(String grupoPersonal) {
		this.grupoPersonal = grupoPersonal;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	 * Gets the num ficha.
	 *
	 * @return the num ficha
	 */
	public String getNumFicha() {
		return numFicha;
	}

	/**
	 * Sets the num ficha.
	 *
	 * @param numFicha the new num ficha
	 */
	public void setNumFicha(String numFicha) {
		this.numFicha = numFicha;
	}

	/**
	 * Gets the nombre completo.
	 *
	 * @return the nombre completo
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Sets the nombre completo.
	 *
	 * @param nombreCompleto the new nombre completo
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Gets the num documento.
	 *
	 * @return the num documento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * Sets the num documento.
	 *
	 * @param numDocumento the new num documento
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
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
