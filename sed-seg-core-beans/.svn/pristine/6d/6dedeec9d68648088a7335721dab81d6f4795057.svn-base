package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsuarioEmpresa implements Serializable, RowMapper<Object> {

	private String codUsuario;
	private String nombreUsuario;
	private int codEmpresa;
	private String nombreEmpresa;

	public UsuarioEmpresa() {

	}

	public UsuarioEmpresa(String codUsuario, String nombreUsuario, int codEmpresa, String nombreEmpresa) {
		super();
		this.codUsuario = codUsuario;
		this.nombreUsuario = nombreUsuario;
		this.codEmpresa = codEmpresa;
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public int getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(int codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new UsuarioEmpresa(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
	}

	@Override
	public String toString() {
		return "UsuarioEmpresa [codUsuario=" + codUsuario + ", nombreUsuario=" + nombreUsuario + ", codEmpresa="
				+ codEmpresa + ", nombreEmpresa=" + nombreEmpresa + "]";
	}

}
