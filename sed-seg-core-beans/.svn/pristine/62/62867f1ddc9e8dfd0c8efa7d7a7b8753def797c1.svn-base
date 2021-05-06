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
public class PerfilAccionBean implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codPerfilAccion;
	private Integer codPerfil;
	private Integer codSistema;
	private Integer codAccionFormulario;
	// --- Campos para descripción
	private String sistemaNombre;
	private String perfilNombre;
	private String moduloNombre;
	private Integer codModulo;
	// ---------------------------------------------

	public PerfilAccionBean() {

	}

	public PerfilAccionBean(Integer codPerfilAccion, Integer codPerfil, Integer codSistema,
			Integer codAccionFormulario) {
		super();
		this.codPerfilAccion = codPerfilAccion;
		this.codPerfil = codPerfil;
		this.codSistema = codSistema;
		this.codAccionFormulario = codAccionFormulario;
		this.sistemaNombre = sistemaNombre;
		this.moduloNombre = moduloNombre;
		this.codModulo = codModulo;
		
	}

	public Integer getCodPerfilAccion() {
		return codPerfilAccion;
	}

	public void setCodPerfilAccion(Integer codPerfilAccion) {
		this.codPerfilAccion = codPerfilAccion;
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

	public Integer getCodAccionFormulario() {
		return codAccionFormulario;
	}

	public void setCodAccionFormulario(Integer codAccionFormulario) {
		this.codAccionFormulario = codAccionFormulario;
	}

	public String getSistemaNombre() {
		return sistemaNombre;
	}

	public void setSistemaNombre(String sistemaNombre) {
		this.sistemaNombre = sistemaNombre;
	}
	
	public String getPerfilNombre() {
		return perfilNombre;
	}

	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
	}

	public String getModuloNombre() {
		return moduloNombre;
	}

	public void setModuloNombre(String moduloNombre) {
		this.moduloNombre = moduloNombre;
	}

	public Integer getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new PerfilAccionBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
	}

	public boolean isNew() {
		return (this.codPerfilAccion == null);
	}

	@Override
	public String toString() {
		return "PerfilAccionBean [codPerfilAccion=" + codPerfilAccion + ", codPerfil=" + codPerfil + ", codSistema="
				+ codSistema + ", codAccionFormulario=" + codAccionFormulario + "]";
	}
	
	
	
}
