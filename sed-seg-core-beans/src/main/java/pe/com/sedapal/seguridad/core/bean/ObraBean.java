package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class ObraBean extends Auditoria implements Serializable, RowMapper<Object> {

	private static final long serialVersionUID = 10991343581015383L;

	private String codigo;
	private String descripcion;
	private Integer estado;
	private int codDistrito;
	private Date fechaIni;
	private Date fechaFin;
	private String nombreDistrito;
	private String nombreEstado;

	public ObraBean() {
	}

	public ObraBean(String codigo, String descripcion, Integer estado, int codDistrito, Date fechaIni, Date fechaFin,
			String nombreDistrito, String nombreEstado) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.codDistrito = codDistrito;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.nombreDistrito = nombreDistrito;
		this.nombreEstado = nombreEstado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public int getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ObraBean(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getDate(6),
				rs.getString(7), rs.getString(8));
	}

	public String getNombreDistrito() {
		return nombreDistrito;
	}

	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	@Override
	public String toString() {
		return "ObraBean [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado + ", codDistrito="
				+ codDistrito + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", nombreDistrito="
				+ nombreDistrito + ", nombreEstado=" + nombreEstado + "]";
	}
	
}
