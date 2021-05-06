package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class UsuarioSistemaBean implements Serializable, RowMapper<Object> {
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
	private String areaDescripcion;
	private String areaAbreviatura;
	private Integer codAreaTrabajador;
	private String nombrePerfil;
	private String estadoTrabajador;
	private String correo;

	private String botones;
	// ----------------------

	public UsuarioSistemaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioSistemaBean(Integer nCodSistema, String sCodUsuario, Integer nEstado, String vSistemaNombre,
			String vDescripcion, Integer nCodFicha, Integer nCodArea, String vEstadoNombre) {
		this.codSistema = nCodSistema;
		this.codUsuario = sCodUsuario;
		this.estado = nEstado;
		this.sistemaNombre = vSistemaNombre;
		this.descripcion = vDescripcion;
		this.codFicha = nCodFicha;
		this.codArea = nCodArea;
		this.estadoNombre = vEstadoNombre;
	}

	public UsuarioSistemaBean(Integer nCodSistema, String sCodUsuario, Integer nEstado, String vSistemaNombre,
			String vDescripcion, Integer nCodFicha, Integer nCodArea, String vEstadoNombre, String areaDescripcion,
			String areaAbreviatura, Integer codAreaTrabajador, String nombrePerfil, String estadoTrabajador,
			String correo) {
		this.codSistema = nCodSistema;
		this.codUsuario = sCodUsuario;
		this.estado = nEstado;
		this.sistemaNombre = vSistemaNombre;
		this.descripcion = vDescripcion;
		this.codFicha = nCodFicha;
		this.codArea = nCodArea;
		this.estadoNombre = vEstadoNombre;
		this.areaDescripcion = areaDescripcion;
		this.areaAbreviatura = areaAbreviatura;
		this.codAreaTrabajador = codAreaTrabajador;
		this.nombrePerfil = nombrePerfil;
		this.estadoTrabajador = estadoTrabajador;
		this.correo = correo;

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
		return new UsuarioSistemaBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), 
				rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)) ;
	}

	public boolean isNew() {
		return (this.codPerfil == null);
	}

	@Override
	public String toString() {
		return "UsuarioSistemaBean [codSistema=" + codSistema + ", codUsuario=" + codUsuario + ", estado=" + estado
				+ ", sistemaNombre=" + sistemaNombre + ", descripcion=" + descripcion + ", codFicha=" + codFicha
				+ ", codArea=" + codArea + ", estadoNombre=" + estadoNombre + "]";
	}

	public String getBotones() {
		return botones;
	}

	public void setBotones(String botones) {
		this.botones = botones;
	}

	public String getAreaDescripcion() {
		return areaDescripcion;
	}

	public void setAreaDescripcion(String areaDescripcion) {
		this.areaDescripcion = areaDescripcion;
	}

	public String getAreaAbreviatura() {
		return areaAbreviatura;
	}

	public void setAreaAbreviatura(String areaAbreviatura) {
		this.areaAbreviatura = areaAbreviatura;
	}

	public Integer getCodAreaTrabajador() {
		return codAreaTrabajador;
	}

	public void setCodAreaTrabajador(Integer codAreaTrabajador) {
		this.codAreaTrabajador = codAreaTrabajador;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getEstadoTrabajador() {
		return estadoTrabajador;
	}

	public void setEstadoTrabajador(String estadoTrabajador) {
		this.estadoTrabajador = estadoTrabajador;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
