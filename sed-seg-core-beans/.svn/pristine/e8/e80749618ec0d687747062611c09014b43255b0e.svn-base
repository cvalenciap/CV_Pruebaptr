package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

public class ClaveBean extends Auditoria implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codUsuario;
	private Integer codClave;
	private String pass;
	private Integer estado;
	private String flagTemp;
	// Propiedads adicionales
	private String passNuevo;
	private String passConfirmar;
	
	public ClaveBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClaveBean(String vCodUsuario, Integer nCodClave, String vPass, Integer nEstado, String vFlagTemp, 
			String usuarioCreacion, Timestamp fechaCreacion, String usuarioModificacion,
			Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codUsuario = vCodUsuario;
		this.codClave = nCodClave;
		this.pass = vPass;
		this.estado = nEstado;
		this.flagTemp = vFlagTemp;
	}

	public String getCodUsuario() {
		if(codUsuario !=null && !"".equals(codUsuario)){
			return codUsuario.toUpperCase();	
		} else {
			return codUsuario;
		}		
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario.toUpperCase();
	}

	public Integer getCodClave() {
		return codClave;
	}

	public void setCodClave(Integer codClave) {
		this.codClave = codClave;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFlagTemp() {
		return flagTemp;
	}

	public void setFlagTemp(String flagTemp) {
		this.flagTemp = flagTemp;
	}
	
	
	public String getPassNuevo() {
		return passNuevo;
	}

	public void setPassNuevo(String passNuevo) {
		this.passNuevo = passNuevo;
	}
	
	public String getPassConfirmar() {
		return passConfirmar;
	}

	public void setPassConfirmar(String passConfirmar) {
		this.passConfirmar = passConfirmar;
	}
	
	public boolean isNew() {
		return (this.codUsuario == null);
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ClaveBean(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
				rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
	}
	
	@Override
	public String toString() {
		return "ClaveBean [codUsuario=" + codUsuario + ", codClave=" + codClave + ", pass=" + pass + ", estado="
				+ estado + ", flagTemp=" + flagTemp + ", getUsuarioCreacion()=" + getUsuarioCreacion()
				+ ", getFechaCreacion()=" + getFechaCreacion() + ", getUsuarioModificacion()="
				+ getUsuarioModificacion() + ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}
	
	
}
