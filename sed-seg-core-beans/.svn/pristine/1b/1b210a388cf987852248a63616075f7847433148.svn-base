package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class Impresion extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4832261180186880649L;

	public Impresion() {

	}

	private String descLineaProducto;
	private String codFormulario;
	private String codRevision;
	private String aprobado;
	private Date fechaAprovacion;

	private String nombreEmpresa;
	private String inspector1;
	private String inspector2;

	private String norma;
	private String codObraProyecto;
	private String nombreObraProyecto;

	private Date fechaFabricacionIni;
	private Date fechaFabricacionFin;
	private Date fechaInspeccionIni;
	private Date fechaInspeccionFin;
	private Integer cantidad;
	private Integer cantidadPv;
	private String nombreProducto;
	private String codigoLote;

	private String vCodImpresion;
	private String nPeriodo;

	public Impresion(String descLineaProducto, String codFormulario, String codRevision, String aprobado,
			Date fechaAprovacion, String nombreEmpresa, String inspector1, String inspector2, String norma,
			String codObraProyecto, String nombreObraProyecto, Date fechaFabricacionIni, Date fechaFabricacionFin,
			Date fechaInspeccionIni, Date fechaInspeccionFin, Integer cantidad, Integer cantidadPv,
			String nombreProducto, String codigoLote, String vCodImpresion, String nPeriodo) {
		super();
		this.descLineaProducto = descLineaProducto;
		this.codFormulario = codFormulario;
		this.codRevision = codRevision;
		this.aprobado = aprobado;
		this.fechaAprovacion = fechaAprovacion;
		this.nombreEmpresa = nombreEmpresa;
		this.inspector1 = inspector1;
		this.inspector2 = inspector2;
		this.norma = norma;
		this.codObraProyecto = codObraProyecto;
		this.nombreObraProyecto = nombreObraProyecto;
		this.fechaFabricacionIni = fechaFabricacionIni;
		this.fechaFabricacionFin = fechaFabricacionFin;
		this.fechaInspeccionIni = fechaInspeccionIni;
		this.fechaInspeccionFin = fechaInspeccionFin;
		this.cantidad = cantidad;
		this.cantidadPv = cantidadPv;
		this.nombreProducto = nombreProducto;
		this.codigoLote = codigoLote;
		this.vCodImpresion = vCodImpresion;
		this.nPeriodo = nPeriodo;
	}

	public String getDescLineaProducto() {
		return descLineaProducto;
	}

	public void setDescLineaProducto(String descLineaProducto) {
		this.descLineaProducto = descLineaProducto;
	}

	public String getCodFormulario() {
		return codFormulario;
	}

	public void setCodFormulario(String codFormulario) {
		this.codFormulario = codFormulario;
	}

	public String getCodRevision() {
		return codRevision;
	}

	public void setCodRevision(String codRevision) {
		this.codRevision = codRevision;
	}

	public String getAprobado() {
		return aprobado;
	}

	public void setAprobado(String aprobado) {
		this.aprobado = aprobado;
	}

	public Date getFechaAprovacion() {
		return fechaAprovacion;
	}

	public void setFechaAprovacion(Date fechaAprovacion) {
		this.fechaAprovacion = fechaAprovacion;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getInspector1() {
		return inspector1;
	}

	public void setInspector1(String inspector1) {
		this.inspector1 = inspector1;
	}

	public String getInspector2() {
		return inspector2;
	}

	public void setInspector2(String inspector2) {
		this.inspector2 = inspector2;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getCodObraProyecto() {
		return codObraProyecto;
	}

	public void setCodObraProyecto(String codObraProyecto) {
		this.codObraProyecto = codObraProyecto;
	}

	public String getNombreObraProyecto() {
		return nombreObraProyecto;
	}

	public void setNombreObraProyecto(String nombreObraProyecto) {
		this.nombreObraProyecto = nombreObraProyecto;
	}

	public Date getFechaFabricacionIni() {
		return fechaFabricacionIni;
	}

	public void setFechaFabricacionIni(Date fechaFabricacionIni) {
		this.fechaFabricacionIni = fechaFabricacionIni;
	}

	public Date getFechaFabricacionFin() {
		return fechaFabricacionFin;
	}

	public void setFechaFabricacionFin(Date fechaFabricacionFin) {
		this.fechaFabricacionFin = fechaFabricacionFin;
	}

	public Date getFechaInspeccionIni() {
		return fechaInspeccionIni;
	}

	public void setFechaInspeccionIni(Date fechaInspeccionIni) {
		this.fechaInspeccionIni = fechaInspeccionIni;
	}

	public Date getFechaInspeccionFin() {
		return fechaInspeccionFin;
	}

	public void setFechaInspeccionFin(Date fechaInspeccionFin) {
		this.fechaInspeccionFin = fechaInspeccionFin;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Impresion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
				rs.getDate(12), rs.getDate(13), rs.getDate(14), rs.getDate(15), rs.getInt(16), rs.getInt(17),
				rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21));
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	@Override
	public String toString() {
		return "Impresion [" + "  descLineaProducto=" + descLineaProducto + ", codFormulario=" + codFormulario
				+ ", codRevision=" + codRevision + ", aprobado=" + aprobado + ", fechaAprovacion=" + fechaAprovacion
				+ ", nombreEmpresa=" + nombreEmpresa + ", inspector1=" + inspector1 + ", inspector2=" + inspector2
				+ ", norma=" + norma + ", codObraProyecto=" + codObraProyecto + ", nombreObraProyecto="
				+ nombreObraProyecto + ", fechaFabricacionIni=" + fechaFabricacionIni + ", fechaFabricacionFin="
				+ fechaFabricacionFin + ", fechaInspeccionIni=" + fechaInspeccionIni + ", fechaInspeccionFin="
				+ fechaInspeccionFin + ", cantidad=" + cantidad + ", cantidadPv=" + cantidadPv + ", nombreProducto="
				+ nombreProducto + ", codigoLote=" + codigoLote + ", vCodImpresion=" + vCodImpresion + ", nPeriodo="
				+ nPeriodo + "]";
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public Integer getCantidadPv() {
		return cantidadPv;
	}

	public void setCantidadPv(Integer cantidadPv) {
		this.cantidadPv = cantidadPv;
	}

	/**
	 * @return the vCodImpresion
	 */
	public String getvCodImpresion() {
		return vCodImpresion;
	}

	/**
	 * @param vCodImpresion the vCodImpresion to set
	 */
	public void setvCodImpresion(String vCodImpresion) {
		this.vCodImpresion = vCodImpresion;
	}

	/**
	 * @return the nPeriodo
	 */
	public String getnPeriodo() {
		return nPeriodo;
	}

	/**
	 * @param nPeriodo the nPeriodo to set
	 */
	public void setnPeriodo(String nPeriodo) {
		this.nPeriodo = nPeriodo;
	}


}
