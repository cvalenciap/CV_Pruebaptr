package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccesosUsuarioBean extends Auditoria implements Serializable, RowMapper<Object>{

	private String acceso;
	
	public AccesosUsuarioBean() {
		// TODO Auto-generated constructor stub
	}
	
	public AccesosUsuarioBean(String acceso) {
		// TODO Auto-generated constructor stub
		this.acceso = acceso;
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new AccesosUsuarioBean(rs.getString(1));
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	
	

}
