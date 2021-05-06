package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
public class UsuarioBean implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codUsuario;
	private String descripcion;
	private Integer estado;
	private Integer codArea;
	private Date fecCreacion;
	private Date fecActualizacion;
	private String responsable;
	private String zona;
	private Integer codFicha;
	private Integer indicador;
	private String pass;
	private Date fecUltver;
	private Date decClave;
	private Integer conexion;
	private String sustentacion;
	private String doc;
	private byte[] bytesDoc;
	private String nombreDoc;

	// Propiedades adiconales
	private Integer codPerfil;
	private Integer codSistema;
	private String perfilNombre;
	private String sistemaNombre;
	private List<String> perfil;
	private List<UsuarioPerfilBean> usuarioPerfil;
	// ----------------------

	private Integer nroIntentos;
	private Date fechaInicioVigencia;
	private Date fechaFinVigencia;
	private String flagCambiarClave;

	private String idUsuario;

	private String descArea;
	private String estadoTrabajador;
	private String emailTrabajador;
	
	private boolean readOnly;

	public UsuarioBean() {
		super();
	}

	public UsuarioBean(String vCodUsuario, String vDescripcion, Integer nEstado, Integer nCodArea, Date dFecCreacion,
			Date dFecActualizacion, String vResponsable, String vZona, Integer nCodFicha, Integer nIndicador,
			String vPass, Date dFecUltver, Date dDecClave, Integer nConexion, String vSustentacion, String vDoc) {
		this.codUsuario = vCodUsuario;
		this.descripcion = vDescripcion;
		this.estado = nEstado;
		this.codArea = nCodArea;
		this.fecCreacion = dFecCreacion;
		this.fecActualizacion = dFecActualizacion;
		this.responsable = vResponsable;
		this.zona = vZona;
		this.codFicha = nCodFicha;
		this.indicador = nIndicador;
		this.pass = vPass;
		this.fecUltver = dFecUltver;
		this.decClave = dDecClave;
		this.conexion = nConexion;
		this.sustentacion = vSustentacion;
		this.doc = vDoc;
	}

	public UsuarioBean(String codUsuario, String descripcion, Integer estado, Integer codArea, Date fecCreacion,
			Date fecActualizacion, String responsable, String zona, Integer codFicha, Integer indicador, String pass,
			Date fecUltver, Date decClave, Integer conexion, String sustentacion, String doc, Integer nroIntentos,
			Date fechaInicioVigencia, Date fechaFinVigencia, String flagCambiarClave, int codSistema, int codPerfil) {
		super();
		this.codUsuario = codUsuario;
		this.descripcion = descripcion;
		this.estado = estado;
		this.codArea = codArea;
		this.fecCreacion = fecCreacion;
		this.fecActualizacion = fecActualizacion;
		this.responsable = responsable;
		this.zona = zona;
		this.codFicha = codFicha;
		this.indicador = indicador;
		this.pass = pass;
		this.fecUltver = fecUltver;
		this.decClave = decClave;
		this.conexion = conexion;
		this.sustentacion = sustentacion;
		this.doc = doc;
		this.codSistema = codSistema;
		this.codPerfil = codPerfil;
		this.nroIntentos = nroIntentos;
		this.fechaInicioVigencia = fechaInicioVigencia;
		this.fechaFinVigencia = fechaFinVigencia;
		this.flagCambiarClave = flagCambiarClave;
	}

	public String getCodUsuario() {
		if (codUsuario != null && !"".equals(codUsuario)) {
			return codUsuario.toUpperCase();
		} else {
			return codUsuario;
		}
	}

	public void setCodUsuario(String codUsuario) {
		if(codUsuario != null){
			this.codUsuario = codUsuario.toUpperCase();	
		} else {
			this.codUsuario = codUsuario;
		}
		
	}

	public String getDescripcion() {
		if (descripcion != null && !"".equals(descripcion)) {
			return descripcion.toUpperCase();
		} else {
			return descripcion;
		}
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
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

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public Date getFecActualizacion() {
		return fecActualizacion;
	}

	public void setFecActualizacion(Date fecActualizacion) {
		this.fecActualizacion = fecActualizacion;
	}

	public String getResponsable() {
		if (responsable != null && !"".equals(responsable)) {
			return responsable.toUpperCase();
		} else {
			return responsable;
		}
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable.toUpperCase();
	}

	public String getZona() {
		if (zona != null && !"".equals(zona)) {
			return zona.toUpperCase();
		} else {
			return zona;
		}
	}

	public void setZona(String zona) {
		this.zona = zona.toUpperCase();
	}

	public Integer getCodFicha() {
		return codFicha;
	}

	public void setCodFicha(Integer codFicha) {
		this.codFicha = codFicha;
	}

	public Integer getIndicador() {
		return indicador;
	}

	public void setIndicador(Integer indicador) {
		this.indicador = indicador;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getFecUltver() {
		return fecUltver;
	}

	public void setFecUltver(Date fecUltver) {
		this.fecUltver = fecUltver;
	}

	public Date getDecClave() {
		return decClave;
	}

	public void setDecClave(Date decClave) {
		this.decClave = decClave;
	}

	public Integer getConexion() {
		return conexion;
	}

	public void setConexion(Integer conexion) {
		this.conexion = conexion;
	}

	public String getSustentacion() {
		if (sustentacion != null && !"".equals(sustentacion)) {
			return sustentacion.toUpperCase();
		} else {
			return sustentacion;
		}
	}

	public void setSustentacion(String sustentacion) {
		this.sustentacion = sustentacion.toUpperCase();
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPerfilNombre() {
		if (perfilNombre != null && !"".equals(perfilNombre)) {
			return perfilNombre.toUpperCase();
		} else {
			return perfilNombre;
		}
	}

	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre.toUpperCase();
	}

	public String getSistemaNombre() {
		if (sistemaNombre != null && !"".equals(sistemaNombre)) {
			return sistemaNombre.toUpperCase();
		} else {
			return sistemaNombre;
		}
	}

	public void setSistemaNombre(String sistemaNombre) {
		this.sistemaNombre = sistemaNombre.toUpperCase();
	}

	public List<String> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<String> perfil) {
		this.perfil = perfil;
	}

	public List<UsuarioPerfilBean> getUsuarioPerfil() {
		return usuarioPerfil;
	}

	public void setUsuarioPerfil(List<UsuarioPerfilBean> usuarioPerfil) {
		this.usuarioPerfil = usuarioPerfil;
	}

	public boolean isNew() {
		return (this.codUsuario == null && this.fecCreacion == null);
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new UsuarioBean(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDate(5),
				rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11),
				rs.getDate(12), rs.getDate(13), rs.getInt(14), rs.getString(15), rs.getString(16), rs.getInt(17),
				rs.getDate(18), rs.getDate(19), rs.getString(20), rs.getInt(21), rs.getInt(22));
	}

	@Override
	public String toString() {
		return "UsuarioBean [codUsuario=" + codUsuario + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", codarea=" + codArea + ", fecCreacion=" + fecCreacion + ", fecActualizacion=" + fecActualizacion
				+ ", responsable=" + responsable + ", zona=" + zona + ", codFicha=" + codFicha + ", indicador="
				+ indicador + ", pass=" + pass + ", fecUltver=" + fecUltver + ", decClave=" + decClave + ", conexion="
				+ conexion + ", sustentacion=" + sustentacion + ", doc=" + doc + "]";
	}

	public Integer getNroIntentos() {
		return nroIntentos;
	}

	public void setNroIntentos(Integer nroIntentos) {
		this.nroIntentos = nroIntentos;
	}

	public Date getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}

	public void setFechaInicioVigencia(Date fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}

	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}

	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	public String getFlagCambiarClave() {
		return flagCambiarClave;
	}

	public void setFlagCambiarClave(String flagCambiarClave) {
		this.flagCambiarClave = flagCambiarClave;
	}

	public byte[] getBytesDoc() {
		return bytesDoc;
	}

	public void setBytesDoc(byte[] bytesDoc) {
		this.bytesDoc = bytesDoc;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public String getDescArea() {
		if (descArea != null && !"".equals(descArea)) {
			return descArea.toUpperCase();
		} else {
			return descArea;
		}
	}

	public void setDescArea(String descArea) {
		this.descArea = descArea.toUpperCase();
	}

	public String getEstadoTrabajador() {
		if (estadoTrabajador != null && !"".equals(estadoTrabajador)) {
			return estadoTrabajador.toUpperCase();
		} else {
			return estadoTrabajador;
		}
	}

	public void setEstadoTrabajador(String estadoTrabajador) {
		this.estadoTrabajador = estadoTrabajador.toUpperCase();
	}

	public boolean isReadOnly() {
		if (this.estado != null) {
			return (this.estado.intValue() == 0);	
		}		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getEmailTrabajador() {
		return emailTrabajador;
	}

	public void setEmailTrabajador(String emailTrabajador) {
		this.emailTrabajador = emailTrabajador;
	}
	
	

	
}
