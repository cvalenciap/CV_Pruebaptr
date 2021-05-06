package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

public class MenuBean implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codSistema;
	private Integer codModulo;
	private Integer codFormulario;
	private String descripcion;
	private String urlFormulario;
	private Integer codFormularioPadre;
	private Integer nivelFormulario;
	private Integer ordenFormulario;
	private Integer estado;

	public MenuBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuBean(Integer codSistema, Integer codModulo, Integer codFormulario, String descripcion, String urlFormulario, Integer codFormularioPadre,
			Integer nivelFormulario, Integer ordenFormulario, Integer estado) {
		super();
		this.codSistema = codSistema;
		this.codModulo = codModulo;
		this.codFormulario = codFormulario;
		this.descripcion = descripcion;
		this.urlFormulario = urlFormulario;
		this.codFormularioPadre = codFormularioPadre;
		this.nivelFormulario = nivelFormulario;
		this.ordenFormulario = ordenFormulario;
		this.estado = estado;
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

	public Integer getCodFormulario() {
		return codFormulario;
	}

	public void setCodFormulario(Integer codFormulario) {
		this.codFormulario = codFormulario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlFormulario() {
		return urlFormulario;
	}

	public void setUrlFormulario(String urlFormulario) {
		this.urlFormulario = urlFormulario;
	}

	public Integer getCodFormularioPadre() {
		return codFormularioPadre;
	}

	public void setCodFormularioPadre(Integer codFormularioPadre) {
		this.codFormularioPadre = codFormularioPadre;
	}

	public Integer getNivelFormulario() {
		return nivelFormulario;
	}

	public void setNivelFormulario(Integer nivelFormulario) {
		this.nivelFormulario = nivelFormulario;
	}

	public Integer getOrdenFormulario() {
		return ordenFormulario;
	}

	public void setOrdenFormulario(Integer ordenFormulario) {
		this.ordenFormulario = ordenFormulario;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MenuBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), 
					rs.getInt(7), rs.getInt(8), rs.getInt(9));
	}
	
	@Override
	public String toString() {
		return "MenuBean [codFormulario=" + codFormulario + ", descripcion=" + descripcion + ", urlFormulario="
				+ urlFormulario + ", codFormularioPadre=" + codFormularioPadre + ", nivelFormulario=" + nivelFormulario
				+ ", ordenFormulario=" + ordenFormulario + ", estado=" + estado + "]";
	}
	
	public boolean isNew() {
		return (this.codFormulario == null);
	}



}