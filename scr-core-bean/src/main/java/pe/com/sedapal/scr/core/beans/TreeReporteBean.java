package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class TreeReporteBean extends BaseSptarBean implements Serializable{

	public static final long serialVersionUID = 1L;
	
	public Integer idPtarxSector;
	public Integer idParametro;
	public String descripcionParametro;
	public Integer idSubParametro;
	public String descripcionSubParametro;
	public Integer secuencia;
	public Integer idPtoMuestra;
	public String descripcionPtoMuestra;
	
	
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}
	public String getDescripcionParametro() {
		return descripcionParametro;
	}
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
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
	public Integer getIdPtoMuestra() {
		return idPtoMuestra;
	}
	public void setIdPtoMuestra(Integer idPtoMuestra) {
		this.idPtoMuestra = idPtoMuestra;
	}
	public String getDescripcionPtoMuestra() {
		return descripcionPtoMuestra;
	}
	public void setDescripcionPtoMuestra(String descripcionPtoMuestra) {
		this.descripcionPtoMuestra = descripcionPtoMuestra;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	
}
