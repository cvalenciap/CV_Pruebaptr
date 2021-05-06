package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SubParametroPtarSectorBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public  Integer idptarPorSector;
	
	public  Integer idParametro;
	
	public  Integer idSubParametro;
	
	public  String  descripcionSubParametro;
	
	public  String  descripcionCorta;
	
	public String descripcionLarga;
	
	public Integer secuencia;
	
	
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public Integer getIdptarPorSector() {
		return idptarPorSector;
	}
	public void setIdptarPorSector(Integer idptarPorSector) {
		this.idptarPorSector = idptarPorSector;
	}
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}
	public Integer getIdSubParametro() {
		return idSubParametro;
	}
	public void setIdSubParametro(Integer idSubParametro) {
		this.idSubParametro = idSubParametro;
	}
	public String getDescripcionSubParametro() {
		return descripcionSubParametro;
	}
	public void setDescripcionSubParametro(String descripcionSubParametro) {
		this.descripcionSubParametro = descripcionSubParametro;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
}
