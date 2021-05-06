package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

public class SistemaModuloFormBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//

	private Integer codSistema;
	private Integer codModulo;
	private Integer codFormulario;
	private String descripcion;
	private Integer estado;
	private Integer codFormularioPadre;
	private Integer nivelFormulario;
	private Integer ordenFormulario;
	private String urlFormulario;

	// --- Propiedads adicionales
	private boolean activarRoot;
	private String estadoNombre;
	private String sistemaNombre;
	private String moduloNombre;
	private String nombreFormularioPadre;
	private List<String> accion;
	private List<AccionFormularioBean> accionFormulario;
	// ---------------------------------------------
	private boolean readOnly;

	public SistemaModuloFormBean() {
		super();
	}

	public SistemaModuloFormBean(Integer codSistema, Integer codModulo, Integer codFormulario,
			String descripcion, Integer estado, Integer codFormularioPadre, Integer nivelFormulario,
			Integer ordenFormulario, String urlFormulario, 
			String estadoNombre, String sistemaNombre, String moduloNombre,
			String usuarioCreacion, Timestamp fechaCreacion, String usuarioModificacion,Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codSistema = codSistema;
		this.codModulo = codModulo;
		this.codFormulario = codFormulario;
		this.descripcion = descripcion;
		this.estado = estado;
		this.codFormularioPadre = codFormularioPadre;
		this.nivelFormulario = nivelFormulario;
		this.ordenFormulario = ordenFormulario;
		this.urlFormulario = urlFormulario;
		this.estadoNombre = estadoNombre;
		this.sistemaNombre = sistemaNombre;
		this.moduloNombre = moduloNombre;
	}

	public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
	}

	public Integer getCodFormulario() {
		return codFormulario;
	}

	public void setCodFormulario(Integer codFormulario) {
		this.codFormulario = codFormulario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getCodFormularioPadre() {
		return codFormularioPadre;
	}

	public void setCodFormularioPadre(Integer codFormularioPadre) {
		this.codFormularioPadre = codFormularioPadre;
	}

	public Integer getNivelFormulario() {
		return nivelFormulario;
	}

	public void setNivelFormulario(Integer nivelFormulario) {
		this.nivelFormulario = nivelFormulario;
	}

	public Integer getOrdenFormulario() {
		return ordenFormulario;
	}

	public void setOrdenFormulario(Integer ordenFormulario) {
		this.ordenFormulario = ordenFormulario;
	}

	public String getUrlFormulario() {
		return urlFormulario;
	}

	public void setUrlFormulario(String urlFormulario) {
		this.urlFormulario = urlFormulario;
	}

	public boolean getActivarRoot() {
		return activarRoot;
	}

	public void setActivarRoot(boolean activarRoot) {
		this.activarRoot = activarRoot;
	}
	
	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

	public String getSistemaNombre() {
		return sistemaNombre;
	}

	public void setSistemaNombre(String sistemaNombre) {
		this.sistemaNombre = sistemaNombre;
	}
	
	public String getModuloNombre() {
		return moduloNombre;
	}

	public void setModuloNombre(String moduloNombre) {
		this.moduloNombre = moduloNombre;
	}
	
	public String getNombreFormularioPadre() {
		return nombreFormularioPadre;
	}

	public void setNombreFormularioPadre(String nombreFormularioPadre) {
		this.nombreFormularioPadre = nombreFormularioPadre;
	}
	
	public List<String> getAccion() {
		return accion;
	}

	public void setAccion(List<String> accion) {
		this.accion = accion;
	}
	
	public List<AccionFormularioBean> getAccionFormulario() {
		return accionFormulario;
	}

	public void setAccionFormulario(List<AccionFormularioBean> accionFormulario) {
		this.accionFormulario = accionFormulario;
	}

	//private List<AccionFormularioBean> accionFormulario
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new SistemaModuloFormBean(rs.getInt(1), rs.getInt(2), rs.getInt(3),
				rs.getString(4), rs.getInt(5), rs.getInt(6), 
				rs.getInt(7), rs.getInt(8), rs.getString(9),
				rs.getString(10), rs.getString(11), rs.getString(12),
				rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
	}

	@Override
	public String toString() {
		return "SistemaModuloFormBean [codSistema=" + codSistema + ", codModulo=" + codModulo + ", codFormulario="
				+ codFormulario + ", descripcion=" + descripcion + ", estado=" + estado + ", codFormularioPadre="
				+ codFormularioPadre + ", nivelFormulario=" + nivelFormulario + ", ordenFormulario=" + ordenFormulario
				+ ", urlFormulario=" + urlFormulario 
				+ ", estadoNombre=" + estadoNombre + ", sistemaNombre=" + sistemaNombre + ", moduloNombre=" + moduloNombre 
				+ ", getUsuarioCreacion()=" + getUsuarioCreacion() 
				+ ", getFechaCreacion()=" + getFechaCreacion() 
				+ ", getUsuarioModificacion()=" + getUsuarioModificacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}

	public boolean isNew() {
		return (this.codFormulario == null);
	}
	
	public boolean isReadOnly() {
		if(this.estado != null){
			return (this.estado.intValue() == 0);
		}		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
