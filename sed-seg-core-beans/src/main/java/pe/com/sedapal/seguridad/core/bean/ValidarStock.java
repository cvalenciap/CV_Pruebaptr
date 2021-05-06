package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ValidarStock extends Auditoria implements Serializable,RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3472635870752348315L;
	private int codLote;
	private int codProducto;
	private int codLineaProducto;
	private int cantidad;

	public ValidarStock(){
		
	}
	
	public ValidarStock(int codLote, int codProducto, int codLineaProducto, int cantidad) {
		super();
		this.codLote = codLote;
		this.codProducto = codProducto;
		this.codLineaProducto = codLineaProducto;
		this.cantidad = cantidad;
	}

	public int getCodLote() {
		return codLote;
	}

	public void setCodLote(int codLote) {
		this.codLote = codLote;
	}

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public int getCodLineaProducto() {
		return codLineaProducto;
	}

	public void setCodLineaProducto(int codLineaProducto) {
		this.codLineaProducto = codLineaProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
