package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class GeneralBean extends BaseSptarBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idGeneral;
	private String descripcion;
	private String descripcionCorta;
	private String estado;
	private Integer inmodificable;
	
	
	public Integer getIdGeneral() {
		return idGeneral;
	}
	public void setIdGeneral(Integer idGeneral) {
		this.idGeneral = idGeneral;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getInmodificable() {
		return inmodificable;
	}
	public void setInmodificable(Integer inmodificable) {
		this.inmodificable = inmodificable;
	}
	
	
}