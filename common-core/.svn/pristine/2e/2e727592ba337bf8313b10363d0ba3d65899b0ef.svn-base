package pe.com.sedapal.common.core.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;


public class MenuOption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8495171281282394039L;
	
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
	private Integer codPerfil;
	private String perfilNombre;

	private String estadoNombre;
	private String sistemaNombre;
	private String moduloNombre;
	private String nombreFormularioPadre;
	
	private List<String> accion;
	private List<AccionUsuarioBean> accionFormulario;
	
	private List<MenuOption> menuOptionsHijos;


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


	public Integer getCodPerfil() {
		return codPerfil;
	}


	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}


	public String getPerfilNombre() {
		return perfilNombre;
	}


	public void setPerfilNombre(String perfilNombre) {
		this.perfilNombre = perfilNombre;
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


	public List<MenuOption> getMenuOptionsHijos() {
		if (menuOptionsHijos == null) {
			menuOptionsHijos = new ArrayList<>();
		}
		return menuOptionsHijos;
	}


	public void setMenuOptionsHijos(List<MenuOption> menuOptionsHijos) {
		this.menuOptionsHijos = menuOptionsHijos;
	}


	@Override
	public String toString() {
		return "MenuOption [codSistema=" + codSistema + ", codModulo=" + codModulo + ", codFormulario=" + codFormulario
				+ ", descripcion=" + descripcion + ", estado=" + estado + ", codFormularioPadre=" + codFormularioPadre
				+ ", nivelFormulario=" + nivelFormulario + ", ordenFormulario=" + ordenFormulario + ", urlFormulario="
				+ urlFormulario + ", codPerfil=" + codPerfil + ", perfilNombre=" + perfilNombre + ", estadoNombre="
				+ estadoNombre + ", sistemaNombre=" + sistemaNombre + ", moduloNombre=" + moduloNombre
				+ ", nombreFormularioPadre=" + nombreFormularioPadre + ", accion=" + accion + ", accionFormulario="
				+ accionFormulario + ", menuOptionsHijos=" + menuOptionsHijos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFormulario == null) ? 0 : codFormulario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuOption other = (MenuOption) obj;
		if (codFormulario == null) {
			if (other.codFormulario != null)
				return false;
		} else if (!codFormulario.equals(other.codFormulario))
			return false;
		return true;
	}


	public List<String> getAccion() {
		return accion;
	}


	public void setAccion(List<String> accion) {
		this.accion = accion;
	}


	public List<AccionUsuarioBean> getAccionFormulario() {
		return accionFormulario;
	}


	public void setAccionFormulario(List<AccionUsuarioBean> accionFormulario) {
		this.accionFormulario = accionFormulario;
	}

}
