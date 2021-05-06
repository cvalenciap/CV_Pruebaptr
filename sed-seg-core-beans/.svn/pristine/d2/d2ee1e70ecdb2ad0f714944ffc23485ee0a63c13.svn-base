package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DatosBean implements Serializable, RowMapper<Object> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer nroOrden;
	private Integer codPerfilAccion;
	private Integer codPerfil;
	private Integer codSistema;
	private Integer codAccionFormulario;
	
	public DatosBean() {

	}

	public DatosBean(Integer nroOrden, Integer codPerfilAccion, Integer codPerfil, Integer codSistema,
			Integer codAccionFormulario) {
		super();
		this.nroOrden = nroOrden;
		this.codPerfilAccion = codPerfilAccion;
		this.codPerfil = codPerfil;
		this.codSistema = codSistema;
		this.codAccionFormulario = codAccionFormulario;
	}

	public Integer getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(Integer nroOrden) {
		this.nroOrden = nroOrden;
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

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new DatosBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
	}
	
}
