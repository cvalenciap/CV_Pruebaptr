package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 28/12/2016.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */

public class PerfilSistemaBean extends Auditoria implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codPerfil;
	private Integer codSistema;
	private String descripcion;
	private String estado;
	// --- Campos para descripción
	private String estadoNombre;
	private String sistemaNombre;
	private List<String> perfilAccion;
	private boolean isUsuarioAccion;
	// ---------------------------------------------
	private boolean readOnly;

	public PerfilSistemaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerfilSistemaBean(Integer nCodPerfil, Integer nCodSistema, String vDescripcion, String vEstado,
			String vEstadoNombre, String vSistemaNombre, String usuarioCreacion, Timestamp fechaCreacion,
			String usuarioModificacion, Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codPerfil = nCodPerfil;
		this.codSistema = nCodSistema;
		this.descripcion = vDescripcion;
		this.estado = vEstado;
		this.estadoNombre = vEstadoNombre;
		this.sistemaNombre = vSistemaNombre;
	}

	public Integer getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getDescripcion() {
		if(descripcion !=null && !"".equals(descripcion)){
			return descripcion.toUpperCase();	
		} else {
			return descripcion;
		}
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoNombre() {
		if(estadoNombre !=null && !"".equals(estadoNombre)){
			return estadoNombre.toUpperCase();	
		} else {
			return estadoNombre;
		}		
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre.toUpperCase();
	}

	public String getSistemaNombre() {
		if(sistemaNombre !=null && !"".equals(sistemaNombre)){
			return sistemaNombre.toUpperCase();	
		} else {
			return sistemaNombre;
		}
		
	}

	public void setSistemaNombre(String sistemaNombre) {
		this.sistemaNombre = sistemaNombre.toUpperCase();
	}

	public List<String> getPerfilAccion() {
		return perfilAccion;
	}

	public void setPerfilAccion(List<String> perfilAccion) {
		this.perfilAccion = perfilAccion;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new PerfilSistemaBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
	}

	public boolean isNew() {
		return (this.codPerfil == null);
	}

	@Override
	public String toString() {
		return "PerfilBean [codPerfil=" + codPerfil + ", codSistema=" + codSistema + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", estadoNombre=" + estadoNombre + ", sistemaNombre=" + sistemaNombre
				+ ", getUsuarioCreacion()=" + getUsuarioCreacion() + ", getFechaCreacion()=" + getFechaCreacion()
				+ ", getUsuarioModificacion()=" + getUsuarioModificacion() + ", getFechaModificacion()="
				+ getFechaModificacion() + "]";
	}

	public boolean isUsuarioAccion() {
		return isUsuarioAccion;
	}

	public void setUsuarioAccion(boolean isUsuarioAccion) {
		this.isUsuarioAccion = isUsuarioAccion;
	}

	public boolean isReadOnly() {
		if(this.estado !=null){
			return (this.estado.equals("0"));	
		}
		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
