package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Date;
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
 * @author maldana
 * @version 1.0
 * 
 */

public class TrabajadorBean implements Serializable, RowMapper<Object> {

	private static final long serialVersionUID = 1L;

	private Integer nCodTrabajador;
	private Integer nFicha;
	private String vNombres;
	private String vApePaterno;
	private String vApeMaterno;
	private Integer nAnexo;
	private Integer nDiaonOmastico;
	private Integer nMesonOmastico;
	private String vCargo;
	private String vDirelectronica;
	private Integer nCodArea;
	private String vCodTipo;
	private Date dFecCreacion;
	private Date dFecActualizacion;
	private String vResponsable;
	private Date dFecNacimiento;
	private Date dFecIngreso;
	private String vUbicacion;
	private Integer nNivel;
	private Integer nNextel;
	private Integer nCelular;
	private Integer nAnexocer;
	private String vCodEstado;
	private Integer nCodTipo;
	private Integer nCenCosto;
	private Integer nCodLocal;
	private Integer nSubArea;
	private String ind_email;
	private Date dFecCese;
	private String vDni;
	private String vTs;
	private String vNivel;
	private String vEc;
	private Integer nHijos;
	private String vDato1;
	private Integer nDato1;
	private String vDato2;
	private Integer nDato2;
	private String vTelefono;
	private String vDireccion;
	private String vAreaPersonal;
	private byte[] data;
	
	private String descArea;
	private String estadoTrabajador;
	
	public TrabajadorBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrabajadorBean(Integer nCodTrabajador, Integer nFicha, String vNombres, String vApePaterno,
			String vApeMaterno, Integer nAnexo, Integer nDiaonOmastico, Integer nMesonOmastico, String vCargo,
			String vDirelectronica, Integer nCodArea, String vCodTipo, Date dFecCreacion, Date dFecActualizacion,
			String vResponsable, Date dFecNacimiento, Date dFecIngreso, String vUbicacion, Integer nNivel,
			Integer nNextel, Integer nCelular, Integer nAnexocer, String vCodEstado, Integer nCodTipo,
			Integer nCenCosto, Integer nCodLocal, Integer nSubArea, String ind_email, Date dFecCese, String vDni,
			String vTs, String vNivel, String vEc, Integer nHijos, String vDato1, Integer nDato1, String vDato2,
			Integer nDato2, String vTelefono, String vDireccion, String vAreaPersonal, String descArea, String estadoTrabajador) {
		super();
		this.nCodTrabajador = nCodTrabajador;
		this.nFicha = nFicha;
		this.vNombres = vNombres;
		this.vApePaterno = vApePaterno;
		this.vApeMaterno = vApeMaterno;
		this.nAnexo = nAnexo;
		this.nDiaonOmastico = nDiaonOmastico;
		this.nMesonOmastico = nMesonOmastico;
		this.vCargo = vCargo;
		this.vDirelectronica = vDirelectronica;
		this.nCodArea = nCodArea;
		this.vCodTipo = vCodTipo;
		this.dFecCreacion = dFecCreacion;
		this.dFecActualizacion = dFecActualizacion;
		this.vResponsable = vResponsable;
		this.dFecNacimiento = dFecNacimiento;
		this.dFecIngreso = dFecIngreso;
		this.vUbicacion = vUbicacion;
		this.nNivel = nNivel;
		this.nNextel = nNextel;
		this.nCelular = nCelular;
		this.nAnexocer = nAnexocer;
		this.vCodEstado = vCodEstado;
		this.nCodTipo = nCodTipo;
		this.nCenCosto = nCenCosto;
		this.nCodLocal = nCodLocal;
		this.nSubArea = nSubArea;
		this.ind_email = ind_email;
		this.dFecCese = dFecCese;
		this.vDni = vDni;
		this.vTs = vTs;
		this.vNivel = vNivel;
		this.vEc = vEc;
		this.nHijos = nHijos;
		this.vDato1 = vDato1;
		this.nDato1 = nDato1;
		this.vDato2 = vDato2;
		this.nDato2 = nDato2;
		this.vTelefono = vTelefono;
		this.vDireccion = vDireccion;
		this.vAreaPersonal = vAreaPersonal;
		this.descArea = descArea;
		this.estadoTrabajador = estadoTrabajador;
	}

	public Integer getnCodTrabajador() {
		return nCodTrabajador;
	}

	public void setnCodTrabajador(Integer nCodTrabajador) {
		this.nCodTrabajador = nCodTrabajador;
	}

	public Integer getnFicha() {
		return nFicha;
	}

	public void setnFicha(Integer nFicha) {
		this.nFicha = nFicha;
	}

	public String getvNombres() {
		return vNombres;
	}

	public void setvNombres(String vNombres) {
		this.vNombres = vNombres;
	}

	public String getvApePaterno() {
		return vApePaterno;
	}

	public void setvApePaterno(String vApePaterno) {
		this.vApePaterno = vApePaterno;
	}

	public String getvApeMaterno() {
		return vApeMaterno;
	}

	public void setvApeMaterno(String vApeMaterno) {
		this.vApeMaterno = vApeMaterno;
	}

	public Integer getnAnexo() {
		return nAnexo;
	}

	public void setnAnexo(Integer nAnexo) {
		this.nAnexo = nAnexo;
	}

	public Integer getnDiaonOmastico() {
		return nDiaonOmastico;
	}

	public void setnDiaonOmastico(Integer nDiaonOmastico) {
		this.nDiaonOmastico = nDiaonOmastico;
	}

	public Integer getnMesonOmastico() {
		return nMesonOmastico;
	}

	public void setnMesonOmastico(Integer nMesonOmastico) {
		this.nMesonOmastico = nMesonOmastico;
	}

	public String getvCargo() {
		return vCargo;
	}

	public void setvCargo(String vCargo) {
		this.vCargo = vCargo;
	}

	public String getvDirelectronica() {
		return vDirelectronica;
	}

	public void setvDirelectronica(String vDirelectronica) {
		this.vDirelectronica = vDirelectronica;
	}

	public Integer getnCodArea() {
		return nCodArea;
	}

	public void setnCodArea(Integer nCodArea) {
		this.nCodArea = nCodArea;
	}

	public String getvCodTipo() {
		return vCodTipo;
	}

	public void setvCodTipo(String vCodTipo) {
		this.vCodTipo = vCodTipo;
	}

	public Date getdFecCreacion() {
		return dFecCreacion;
	}

	public void setdFecCreacion(Date dFecCreacion) {
		this.dFecCreacion = dFecCreacion;
	}

	public Date getdFecActualizacion() {
		return dFecActualizacion;
	}

	public void setdFecActualizacion(Date dFecActualizacion) {
		this.dFecActualizacion = dFecActualizacion;
	}

	public String getvResponsable() {
		return vResponsable;
	}

	public void setvResponsable(String vResponsable) {
		this.vResponsable = vResponsable;
	}

	public Date getdFecNacimiento() {
		return dFecNacimiento;
	}

	public void setdFecNacimiento(Date dFecNacimiento) {
		this.dFecNacimiento = dFecNacimiento;
	}

	public Date getdFecIngreso() {
		return dFecIngreso;
	}

	public void setdFecIngreso(Date dFecIngreso) {
		this.dFecIngreso = dFecIngreso;
	}

	public String getvUbicacion() {
		return vUbicacion;
	}

	public void setvUbicacion(String vUbicacion) {
		this.vUbicacion = vUbicacion;
	}

	public Integer getnNivel() {
		return nNivel;
	}

	public void setnNivel(Integer nNivel) {
		this.nNivel = nNivel;
	}

	public Integer getnNextel() {
		return nNextel;
	}

	public void setnNextel(Integer nNextel) {
		this.nNextel = nNextel;
	}

	public Integer getnCelular() {
		return nCelular;
	}

	public void setnCelular(Integer nCelular) {
		this.nCelular = nCelular;
	}

	public Integer getnAnexocer() {
		return nAnexocer;
	}

	public void setnAnexocer(Integer nAnexocer) {
		this.nAnexocer = nAnexocer;
	}

	public String getvCodEstado() {
		return vCodEstado;
	}

	public void setvCodEstado(String vCodEstado) {
		this.vCodEstado = vCodEstado;
	}

	public Integer getnCodTipo() {
		return nCodTipo;
	}

	public void setnCodTipo(Integer nCodTipo) {
		this.nCodTipo = nCodTipo;
	}

	public Integer getnCenCosto() {
		return nCenCosto;
	}

	public void setnCenCosto(Integer nCenCosto) {
		this.nCenCosto = nCenCosto;
	}

	public Integer getnCodLocal() {
		return nCodLocal;
	}

	public void setnCodLocal(Integer nCodLocal) {
		this.nCodLocal = nCodLocal;
	}

	public Integer getnSubArea() {
		return nSubArea;
	}

	public void setnSubArea(Integer nSubArea) {
		this.nSubArea = nSubArea;
	}

	public String getInd_email() {
		return ind_email;
	}

	public void setInd_email(String ind_email) {
		this.ind_email = ind_email;
	}

	public Date getdFecCese() {
		return dFecCese;
	}

	public void setdFecCese(Date dFecCese) {
		this.dFecCese = dFecCese;
	}

	public String getvDni() {
		return vDni;
	}

	public void setvDni(String vDni) {
		this.vDni = vDni;
	}

	public String getvTs() {
		return vTs;
	}

	public void setvTs(String vTs) {
		this.vTs = vTs;
	}

	public String getvNivel() {
		return vNivel;
	}

	public void setvNivel(String vNivel) {
		this.vNivel = vNivel;
	}

	public String getvEc() {
		return vEc;
	}

	public void setvEc(String vEc) {
		this.vEc = vEc;
	}

	public Integer getnHijos() {
		return nHijos;
	}

	public void setnHijos(Integer nHijos) {
		this.nHijos = nHijos;
	}

	public String getvDato1() {
		return vDato1;
	}

	public void setvDato1(String vDato1) {
		this.vDato1 = vDato1;
	}

	public Integer getnDato1() {
		return nDato1;
	}

	public void setnDato1(Integer nDato1) {
		this.nDato1 = nDato1;
	}

	public String getvDato2() {
		return vDato2;
	}

	public void setvDato2(String vDato2) {
		this.vDato2 = vDato2;
	}

	public Integer getnDato2() {
		return nDato2;
	}

	public void setnDato2(Integer nDato2) {
		this.nDato2 = nDato2;
	}

	public String getvTelefono() {
		return vTelefono;
	}

	public void setvTelefono(String vTelefono) {
		this.vTelefono = vTelefono;
	}

	public String getvDireccion() {
		return vDireccion;
	}

	public void setvDireccion(String vDireccion) {
		this.vDireccion = vDireccion;
	}

	public String getvAreaPersonal() {
		return vAreaPersonal;
	}

	public void setvAreaPersonal(String vAreaPersonal) {
		this.vAreaPersonal = vAreaPersonal;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new TrabajadorBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11),
				rs.getString(12), rs.getDate(13), rs.getDate(14), rs.getString(15), rs.getDate(16), rs.getDate(17),
				rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getInt(21), rs.getInt(22), rs.getString(23),
				rs.getInt(24), rs.getInt(25), rs.getInt(26), rs.getInt(27), rs.getString(28), rs.getDate(29),
				rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getInt(34), rs.getString(35),
				rs.getInt(36), rs.getString(37), rs.getInt(38), rs.getString(39), rs.getString(40), rs.getString(41),rs.getString(42),rs.getString(43));
	}

	public boolean isNew() {
		return (this.nCodTrabajador == null);
	}

	@Override
	public String toString() {
		return "Trabajador [nCodTrabajador=" + nCodTrabajador + ", nFicha=" + nFicha + ", vNombres=" + vNombres
				+ ", vApePaterno=" + vApePaterno + ", vApeMaterno=" + vApeMaterno + ", nAnexo=" + nAnexo
				+ ", nDiaonOmastico=" + nDiaonOmastico + ", nMesonOmastico=" + nMesonOmastico + ", vCargo=" + vCargo
				+ ", vDirelectronica=" + vDirelectronica + ", nCodArea=" + nCodArea + ", vCodTipo=" + vCodTipo
				+ ", dFecCreacion=" + dFecCreacion + ", dFecActualizacion=" + dFecActualizacion + ", vResponsable="
				+ vResponsable + ", dFecNacimiento=" + dFecNacimiento + ", dFecIngreso=" + dFecIngreso + ", vUbicacion="
				+ vUbicacion + ", nNivel=" + nNivel + ", nNextel=" + nNextel + ", nCelular=" + nCelular + ", nAnexocer="
				+ nAnexocer + ", vCodEstado=" + vCodEstado + ", nCodTipo=" + nCodTipo + ", nCenCosto=" + nCenCosto
				+ ", nCodLocal=" + nCodLocal + ", nSubArea=" + nSubArea + ", ind_email=" + ind_email + ", dFecCese="
				+ dFecCese + ", vDni=" + vDni + ", vTs=" + vTs + ", vNivel=" + vNivel + ", vEc=" + vEc + ", nHijos="
				+ nHijos + ", vDato1=" + vDato1 + ", nDato1=" + nDato1 + ", vDato2=" + vDato2 + ", nDato2=" + nDato2
				+ ", vTelefono=" + vTelefono + ", vDireccion=" + vDireccion + ", vAreaPersonal=" + vAreaPersonal + "]";
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getDescArea() {
		return descArea;
	}

	public void setDescArea(String descArea) {
		this.descArea = descArea;
	}

	public String getEstadoTrabajador() {
		return estadoTrabajador;
	}

	public void setEstadoTrabajador(String estadoTrabajador) {
		this.estadoTrabajador = estadoTrabajador;
	}


	
}
