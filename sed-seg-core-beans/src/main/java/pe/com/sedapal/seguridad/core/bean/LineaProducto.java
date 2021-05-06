package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LineaProducto extends Auditoria implements Serializable,RowMapper<Object> {

	private int codLineaProducto;
	private String descLineaProducto;

	public LineaProducto() {

	}

	public LineaProducto(int codLineaProducto, String descLineaProducto) {
		super();
		this.codLineaProducto = codLineaProducto;
		this.descLineaProducto = descLineaProducto;
	}

	public int getCodLineaProducto() {
		return codLineaProducto;
	}

	public void setCodLineaProducto(int codLineaProducto) {
		this.codLineaProducto = codLineaProducto;
	}

	public String getDescLineaProducto() {
		return descLineaProducto;
	}

	public void setDescLineaProducto(String descLineaProducto) {
		this.descLineaProducto = descLineaProducto;
	}

	@Override
	public String toString() {
		return "LineaProducto [codLineaProducto=" + codLineaProducto + ", descLineaProducto=" + descLineaProducto + "]";
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new LineaProducto(rs.getInt(1), rs.getString(2));
	}

}
