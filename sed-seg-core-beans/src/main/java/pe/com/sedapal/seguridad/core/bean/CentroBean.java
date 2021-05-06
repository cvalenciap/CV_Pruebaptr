package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;
/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 06/01/2017.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldanaa
 * @version 1.0
 * 
 */
public class CentroBean implements Serializable, RowMapper<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codCentro;
	private String nombre;
	private String direccion;
	private String abreviatura;
	private String anxCentral;
	private Timestamp fecCreacion;
	private Timestamp fecActualizacion;
	private String responsable;
	private Integer zona;
	private Integer estado;
	private Integer centro;
	private Integer consumo;
	
	public CentroBean() {
	}

	public CentroBean(Integer codCentro, String nombre, String direccion, String abreviatura, String anxCentral,
			Timestamp fecCreacion, Timestamp fecActualizacion, String responsable, Integer zona, Integer estado,
			Integer centro, Integer consumo) {
		this.codCentro = codCentro;
		this.nombre = nombre;
		this.direccion = direccion;
		this.abreviatura = abreviatura;
		this.anxCentral = anxCentral;
		this.fecCreacion = fecCreacion;
		this.fecActualizacion = fecActualizacion;
		this.responsable = responsable;
		this.zona = zona;
		this.estado = estado;
		this.centro = centro;
		this.consumo = consumo; 
	}

	public Integer getnCodCentro() {
		return codCentro;
	}

	public void setnCodCentro(Integer codCentro) {
		this.codCentro = codCentro;
	}

	public String getvNombre() {
		return nombre;
	}

	public void setvNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getvDireccion() {
		return direccion;
	}

	public void setvDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getvAbreviatura() {
		return abreviatura;
	}

	public void setvAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getnAnxCentral() {
		return anxCentral;
	}

	public void setnAnxCentral(String anxCentral) {
		this.anxCentral = anxCentral;
	}

	public Timestamp getdFecCreacion() {
		return fecCreacion;
	}

	public void setdFecCreacion(Timestamp fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public Timestamp getdFecActualizacion() {
		return fecActualizacion;
	}

	public void setdFecActualizacion(Timestamp fecActualizacion) {
		this.fecActualizacion = fecActualizacion;
	}

	public String getvResponsable() {
		return responsable;
	}

	public void setvResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Integer getnZona() {
		return zona;
	}

	public void setnZona(Integer zona) {
		this.zona = zona;
	}

	public Integer getnEstado() {
		return estado;
	}

	public void setnEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getnCentro() {
		return centro;
	}

	public void setnCentro(Integer centro) {
		this.centro = centro;
	}

	public Integer getnConsumo() {
		return consumo;
	}

	public void setnConsumo(Integer consumo) {
		this.consumo = consumo;
	}
	
	public boolean isNew() {
		return (this.codCentro == null);
	}
	
	@Override
	public String toString() {
		return "CentroBean [codCentro=" + codCentro + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", abreviatura=" + abreviatura + ", anxCentral=" + anxCentral + ", fecCreacion=" + fecCreacion
				+ ", fecActualizacion=" + fecActualizacion + ", responsable=" + responsable + ", zona=" + zona
				+ ", estado=" + estado + ", centro=" + centro + ", consumo=" + consumo + "]";
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CentroBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getTimestamp(6), rs.getTimestamp(7), 
				rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
	}
}
