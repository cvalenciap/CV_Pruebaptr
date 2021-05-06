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

public class AreaBean implements Serializable, RowMapper<Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codArea;
	private Integer codCentro;
	private String direccion;
	private String abreviatura;
	private Integer anexo;
	private String tipArea;
	private Integer areSuperior;
	private Timestamp fecCreacion;
	private Timestamp fecActualizacion;
	private String responsable;
	private Integer indicador;
	private Integer estado;
	
	public AreaBean() {
	}
	
	public AreaBean(Integer codArea, Integer codCentro, String direccion, String abreviatura, Integer anexo,
			String tipArea, Integer areSuperior, Timestamp fecCreacion, Timestamp fecActualizacion,
			String responsable, Integer indicador, Integer estado) {
		this.codArea = codArea;
		this.codCentro = codCentro;
		this.direccion = direccion;
		this.abreviatura = abreviatura;
		this.anexo = anexo;
		this.tipArea = tipArea;
		this.areSuperior = areSuperior;
		this.fecCreacion = fecCreacion;
		this.fecActualizacion = fecActualizacion;
		this.responsable = responsable;
		this.indicador = indicador;
		this.estado = estado;
	}
	
	public Integer getnCodArea() {
		return codArea;
	}

	public void setnCodArea(Integer codArea) {
		this.codArea = codArea;
	}

	public Integer getnCodCentro() {
		return codCentro;
	}

	public void setnCodCentro(Integer codCentro) {
		this.codCentro = codCentro;
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

	public Integer getnAnexo() {
		return anexo;
	}

	public void setnAnexo(Integer anexo) {
		this.anexo = anexo;
	}

	public String getvTipArea() {
		return tipArea;
	}

	public void setvTipArea(String tipArea) {
		this.tipArea = tipArea;
	}

	public Integer getnAreSuperior() {
		return areSuperior;
	}

	public void setnAreSuperior(Integer areSuperior) {
		this.areSuperior = areSuperior;
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

	public Integer getnIndicador() {
		return indicador;
	}

	public void setnIndicador(Integer indicador) {
		this.indicador = indicador;
	}

	public Integer getnEstado() {
		return estado;
	}

	public void setnEstado(Integer estado) {
		this.estado = estado;
	}

	public boolean isNew() {
		return (this.codArea == null);
	}

	@Override
	public String toString() {
		return "AreaBean [codArea=" + codArea + ", codCentro=" + codCentro + ", direccion=" + direccion
				+ ", abreviatura=" + abreviatura + ", anexo=" + anexo + ", tipArea=" + tipArea + ", areSuperior="
				+ areSuperior + ", fecCreacion=" + fecCreacion + ", fecActualizacion=" + fecActualizacion
				+ ", responsable=" + responsable + ", indicador=" + indicador + ", estado=" + estado + "]";
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new AreaBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), 
				rs.getString(6),rs.getInt(7), 
				rs.getTimestamp(8), rs.getTimestamp(9), 
				rs.getString(10), rs.getInt(11), rs.getInt(12));
	}
	
	
}
