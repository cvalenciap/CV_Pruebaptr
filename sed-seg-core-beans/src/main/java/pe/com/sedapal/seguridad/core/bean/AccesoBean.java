package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 *  
 */

public class AccesoBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2668804594053620571L;

	private Integer codSistema;
	private String codUsuario;
	private Timestamp fecFecha;
	private String codIp;
	private Integer codArea;
	private String codAcceso;
	private String codIngreso;
	private Integer correlativo;
	private Integer estado;
	private String token;
	
			
	

	public AccesoBean(){
		
	}
	
	public AccesoBean(Integer codSistema, String codUsuario, Timestamp fecFecha, String codIp, Integer codArea,
			String codAcceso, String codIngreso, Integer correlativo, Integer estado, String usuarioCreacion,
			Timestamp fechaCreacion, String usuarioModificacion, Timestamp fechaModificacion,String token) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codSistema = codSistema;
		this.codUsuario = codUsuario;
		this.fecFecha = fecFecha;
		this.codIp = codIp;
		this.codArea = codArea;
		this.codAcceso = codAcceso;
		this.codIngreso = codIngreso;
		this.correlativo = correlativo;
		this.estado = estado;
		this.token = token;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Timestamp getFecFecha() {
		return fecFecha;
	}

	public void setFecFecha(Timestamp fecFecha) {
		this.fecFecha = fecFecha;
	}

	public String getCodIp() {
		return codIp;
	}

	public void setCodIp(String codIp) {
		this.codIp = codIp;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;

	}

	public Integer getCodArea() {
		return codArea;
	}

	public void setCodArea(Integer codArea) {
		this.codArea = codArea;
	}

	public String getCodAcceso() {
		return codAcceso;
	}

	public void setCodAcceso(String codAcceso) {
		this.codAcceso = codAcceso;
	}

	public String getCodIngreso() {
		return codIngreso;
	}

	public void setCodIngreso(String codIngreso) {
		this.codIngreso = codIngreso;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new AccesoBean(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getTimestamp(11), rs.getString(12), rs.getTimestamp(13),rs.getString(14));
		
	}

	@Override
	public String toString() {
		return "AccesoBean [codSistema=" + codSistema + ", codUsuario=" + codUsuario + ", fecFecha=" + fecFecha
				+ ", codIp=" + codIp + ", codArea=" + codArea + ", codAcceso=" + codAcceso + ", codIngreso="
				+ codIngreso + ", correlativo=" + correlativo + ", estado=" + estado + ", getUsuarioCreacion()="
				+ getUsuarioCreacion() + ", getFechaCreacion()=" + getFechaCreacion() + ", getUsuarioModificacion()="
				+ getUsuarioModificacion() + ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
