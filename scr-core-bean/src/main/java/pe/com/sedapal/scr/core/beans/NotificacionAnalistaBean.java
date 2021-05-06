package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.List;

public class NotificacionAnalistaBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idNotificacionAnalista;
	public Integer idAnalista;
	public Integer idNotificacion;
	public String nombre;
	public String apellido;
	public String nombreCompleto;
	public String estado;
	public String correo;
	
	
	public Integer getIdNotificacionAnalista() {
		return idNotificacionAnalista;
	}
	public void setIdNotificacionAnalista(Integer idNotificacionAnalista) {
		this.idNotificacionAnalista = idNotificacionAnalista;
	}
	public Integer getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(Integer idAnalista) {
		this.idAnalista = idAnalista;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getIdNotificacion() {
		return idNotificacion;
	}
	public void setIdNotificacion(Integer idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
	
	
}
