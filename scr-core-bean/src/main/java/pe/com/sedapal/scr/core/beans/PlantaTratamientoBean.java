package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class PlantaTratamientoBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idPtarxSector;
	public Integer idPtar;
	public Integer idSector;
	public String descripcionPtarxSector;
	public String descripcionCorta;
	public String direccion;
	public String latitud;
	public String longitud;
	public String descripcionSector;
	public Integer idParametro;
	
	
	
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}
	public String getDescripcionSector() {
		return descripcionSector;
	}
	public void setDescripcionSector(String descripcionSector) {
		this.descripcionSector = descripcionSector;
	}
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public Integer getIdPtar() {
		return idPtar;
	}
	public void setIdPtar(Integer idPtar) {
		this.idPtar = idPtar;
	}
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public String getDescripcionPtarxSector() {
		return descripcionPtarxSector;
	}
	public void setDescripcionPtarxSector(String descripcionPtarxSector) {
		this.descripcionPtarxSector = descripcionPtarxSector;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	
	
}
