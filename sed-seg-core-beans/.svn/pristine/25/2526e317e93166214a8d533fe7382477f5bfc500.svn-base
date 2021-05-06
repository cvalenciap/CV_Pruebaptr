package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
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

public class SistemaModuloBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codSistema;
	private Integer codModulo;
	private String descripcion;
	private Integer estado;
	//--- Propiedads adicionales
	private String estadoNombre;
	private String sistemaNombre;
	//--------------------------------------------- 
	private boolean readOnly;

	public SistemaModuloBean() {
		super();
	}
	
	public SistemaModuloBean(Integer nCodSistema, Integer nCodModulo, String vDescripcion, Integer nEstado, 
				String vEstadoNombre, String vSistemaNombre,
			String usuarioCreacion, Timestamp fechaCreacion, String usuarioModificacion,Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codSistema = nCodSistema;
		this.codModulo = nCodModulo;
		this.descripcion = vDescripcion;
		this.estado = nEstado;
		this.estadoNombre = vEstadoNombre;
		this.sistemaNombre = vSistemaNombre;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
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
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SistemaModuloBean(rs.getInt(1), rs.getInt(2), rs.getString(3), 
				rs.getInt(4), rs.getString(5), rs.getString(6),
				rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
	}

	@Override
	public String toString() {
		return "ModuloBean [codSistema=" + codSistema + ", codModulo=" + codModulo + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", estadoNombre=" + estadoNombre + ", sistemaNombre=" + sistemaNombre
				+ ", getUsuarioCreacion()=" + getUsuarioCreacion() 
				+ ", getFechaCreacion()=" + getFechaCreacion() 
				+ ", getUsuarioModificacion()=" + getUsuarioModificacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}

	public boolean isNew() {
		return (this.codModulo == null);
	}
	
	public boolean isReadOnly() {
		if(this.estado !=null){
			return (this.estado.intValue() == 0);	
		}		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
