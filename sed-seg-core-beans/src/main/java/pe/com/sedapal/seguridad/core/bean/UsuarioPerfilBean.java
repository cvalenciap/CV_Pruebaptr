package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

public class UsuarioPerfilBean extends Auditoria implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codPerfil;
	private Integer codSistema;
	private String codUsuario;
	private Integer estado;
	
	// Propiedades adiconales
	private String perfilNombre;
	private String sistemaNombre;
	private String descripcion;
	private Integer codFicha;
	private Integer codArea;
	private String estadoNombre;
	//----------------------
	
	public UsuarioPerfilBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioPerfilBean(Integer nCodPerfil, Integer nCodSistema, String sCodUsuario, Integer nEstado,
			String usuarioCreacion, Timestamp fechaCreacion, String usuarioModificacion,Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codPerfil = nCodPerfil;
		this.codSistema = nCodSistema;
		this.codUsuario = sCodUsuario;
		this.estado = nEstado;
	}
	
	public UsuarioPerfilBean(Integer nCodPerfil, Integer nCodSistema, String sCodUsuario, Integer nEstado,
					String vPerfilNombre, String vSistemaNombre, String vDescripcion, Integer nCodFicha, Integer nCodArea,
			String usuarioCreacion, Timestamp fechaCreacion, String usuarioModificacion,Timestamp fechaModificacion
			) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codPerfil = nCodPerfil;
		this.codSistema = nCodSistema;
		this.codUsuario = sCodUsuario;
		this.estado = nEstado;
		this.perfilNombre = vPerfilNombre;
		this.sistemaNombre = vSistemaNombre;
		this.descripcion = vDescripcion;
		this.codFicha = nCodFicha;
		this.codArea = nCodArea;
		//this.estadoNombre = vEstadoNombre;
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

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public String getPerfilNombre() {
		return perfilNombre;
	}

	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}

	public String getSistemaNombre() {
		return sistemaNombre;
	}

	public void setSistemaNombre(String sistemaNombre) {
		this.sistemaNombre = sistemaNombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCodFicha() {
		return codFicha;
	}

	public void setCodFicha(Integer codFicha) {
		this.codFicha = codFicha;
	}
	
	public Integer getCodArea() {
		return codArea;
	}
	
	public void setCodArea(Integer codArea) {
		this.codArea = codArea;
	}
	

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UsuarioPerfilBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
				rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9),
				rs.getString(10), rs.getTimestamp(11), rs.getString(12), rs.getTimestamp(13)
				);
	}
	
	public boolean isNew() {
		return (this.codPerfil == null);
	}

	@Override
	public String toString() {
		return "PerfilBean [codPerfil=" + codPerfil + ", codSistema=" + codSistema + ", codUsuario=" + codUsuario
				+ ", estado=" + estado + ", getUsuarioCreacion()=" + getUsuarioCreacion() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getUsuarioModificacion()=" + getUsuarioModificacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}
	
	
}
