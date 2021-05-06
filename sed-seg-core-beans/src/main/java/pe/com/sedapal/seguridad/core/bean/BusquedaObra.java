package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class BusquedaObra implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4199136624660763175L;
	private String codigo;
	private String descripcion;
	private int filas;
	private int codDistrito;
	private Date fechaIni;
	private Date fechaFin;
	private String nomDistrito;

	public BusquedaObra() {
	}
	
	public BusquedaObra(String codigo, String descripcion, int filas, int codDistrito, Date fechaIni, Date fechaFin,
			String nomDistrito) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.filas = filas;
		this.codDistrito = codDistrito;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.nomDistrito = nomDistrito;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
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
		// TODO Auto-generated method stub
		return new BusquedaObra(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getDate(6), rs.getString(7));
	}

	@Override
	public String toString() {
		return "BusquedaObra [codigo=" + codigo + ", descripcion=" + descripcion + ", filas=" + filas + ", codDistrito="
				+ codDistrito + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", nomDistrito=" + nomDistrito
				+ "]";
	}

}
