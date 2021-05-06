package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Distrito implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4705505138115679861L;

	private int codDistrito;
	private String descripcion;

	public Distrito(){
		
	}
	
	public Distrito(int codDistrito, String descripcion) {
		super();
		this.codDistrito = codDistrito;
		this.descripcion = descripcion;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Distrito(rs.getInt(1), rs.getString(2));
	}

	public int getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Distrito [codDistrito=" + codDistrito + ", descripcion=" + descripcion + "]";
	}

}
