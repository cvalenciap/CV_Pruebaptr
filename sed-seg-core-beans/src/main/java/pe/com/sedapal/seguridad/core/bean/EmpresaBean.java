package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

public class EmpresaBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5147200690042477351L;
	private Integer codEmpresa;
	private String descripcion;
	private Integer estado;
	private String siglas;
	private String nombreEstado;

	public EmpresaBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresaBean(Integer codEmpresa, String descripcion, Integer estado, String siglas, String usuarioCreacion,
			Timestamp fechaCreacion, String usuarioModificacion, Timestamp fechaModificacion, String nombreEstado) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codEmpresa = codEmpresa;
		this.descripcion = descripcion;
		this.estado = estado;
		this.siglas = siglas;
		this.nombreEstado = nombreEstado;
	}

	public Integer getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(Integer codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@Override
	public String toString() {
		return "EmpresaBean [codEmpresa=" + codEmpresa + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", siglas=" + siglas + ", nombreEstado=" + nombreEstado + "]";
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new EmpresaBean(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
				rs.getTimestamp(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9));
	}

}
