package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 * 
 */
public class SistemaBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codSistema;
	private String descripcion;
	private String abreviatura;
	private String programa;
	private Integer estado;
	private String estadoNombre;
	private String flagNivel;
	private String nivelNombre;
	private boolean readOnly;

	public SistemaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SistemaBean(Integer nCodSistema, String vDescripcion, String vAbreviatura, String vPrograma, Integer nEstado,
			String vEstadoNombre, String vFlagNivel, String usuarioCreacion, Timestamp fechaCreacion,
			String usuarioModificacion, Timestamp fechaModificacion, String nivelNombre) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codSistema = nCodSistema;
		this.descripcion = vDescripcion;
		this.abreviatura = vAbreviatura;
		this.programa = vPrograma;
		this.estado = nEstado;
		this.estadoNombre = vEstadoNombre;
		this.flagNivel = vFlagNivel;
		this.nivelNombre = nivelNombre;
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

	public String getAbreviatura() {
		if(abreviatura !=null && !"".equals(abreviatura)){
			return abreviatura.toUpperCase();	
		} else {
			return abreviatura;
		}		
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura.toUpperCase();
	}

	public String getPrograma() {
		if(programa !=null && !"".equals(programa)){
			return programa.toUpperCase();	
		} else {
			return programa;
		}		
	}

	public void setPrograma(String programa) {
		this.programa = programa.toUpperCase();
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
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
		this.estadoNombre = estadoNombre;
	}

	public String getFlagNivel() {
		return flagNivel;
	}

	public void setFlagNivel(String flagNivel) {
		this.flagNivel = flagNivel;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SistemaBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10),
				rs.getTimestamp(11), rs.getString(12));
	}

	public boolean isNew() {
		return (this.codSistema == null);
	}
	
	

	@Override
	public String toString() {
		return "SistemaBean [codSistema=" + codSistema + ", descripcion=" + descripcion + ", abreviatura=" + abreviatura
				+ ", programa=" + programa + ", estado=" + estado + ", estadoNombre=" + estadoNombre + ", flagNivel="
				+ flagNivel + ", getUsuarioCreacion()=" + getUsuarioCreacion() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getUsuarioModificacion()=" + getUsuarioModificacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}

	public String getNivelNombre() {
		if(nivelNombre !=null && !"".equals(nivelNombre)){
			return nivelNombre.toUpperCase();	
		} else {
			return nivelNombre;
		}		
	}

	public void setNivelNombre(String nivelNombre) {
		this.nivelNombre = nivelNombre;
	}

	public boolean isReadOnly() {
		if (this.estado != null) {
			return (this.estado.intValue() == 1);	
		}		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
