package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class MicroPorSubParametroBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idPtarPorSector;
	
	public Integer idParametro;
	
	public Integer idSubParametro;
	
	public Integer idMicroOrganismo;
	
	public String descripcionMicroOrganismo;
	
	public String descripcionCorta;
	
	public String descripcionSubParametro;
	
	

	public String getDescripcionSubParametro() {
		return descripcionSubParametro;
	}

	public void setDescripcionSubParametro(String descripcionSubParametro) {
		this.descripcionSubParametro = descripcionSubParametro;
	}

	public Integer getIdPtarPorSector() {
		return idPtarPorSector;
	}

	public void setIdPtarPorSector(Integer idPtarPorSector) {
		this.idPtarPorSector = idPtarPorSector;
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

	public Integer getIdMicroOrganismo() {
		return idMicroOrganismo;
	}

	public void setIdMicroOrganismo(Integer idMicroOrganismo) {
		this.idMicroOrganismo = idMicroOrganismo;
	}

	public String getDescripcionMicroOrganismo() {
		return descripcionMicroOrganismo;
	}

	public void setDescripcionMicroOrganismo(String descripcionMicroOrganismo) {
		this.descripcionMicroOrganismo = descripcionMicroOrganismo;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
}
