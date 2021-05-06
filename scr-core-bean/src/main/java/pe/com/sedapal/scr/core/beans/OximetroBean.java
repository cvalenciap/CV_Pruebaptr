package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OximetroBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idOximetro;
	public Integer idSensor;
	public String descripcionSensor;
	public Date fechaRegistro;
	public String fechaRegistroString;
	public Double numAirePdte;
	public Double numSaturacionAirePdte;
	public Double numSolucionPdte;
	public Double numSaturacionSolucionPdte;
	public String descripcionObservacion;
	public String descripcionEstado;
	
	
	public Integer getIdOximetro() {
		return idOximetro;
	}
	public void setIdOximetro(Integer idOximetro) {
		this.idOximetro = idOximetro;
	}
	public Integer getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}
	public String getDescripcionSensor() {
		return descripcionSensor;
	}
	public void setDescripcionSensor(String descripcionSensor) {
		this.descripcionSensor = descripcionSensor;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegistroString = df.format(fechaRegistro);
	}
	public String getFechaRegistroString() {
		return fechaRegistroString;
	}
	public void setFechaRegistroString(String fechaRegistroString) {
		this.fechaRegistroString = fechaRegistroString;
	}
	public Double getNumAirePdte() {
		return numAirePdte;
	}
	public void setNumAirePdte(Double numAirePdte) {
		this.numAirePdte = numAirePdte;
	}
	public Double getNumSaturacionAirePdte() {
		return numSaturacionAirePdte;
	}
	public void setNumSaturacionAirePdte(Double numSaturacionAirePdte) {
		this.numSaturacionAirePdte = numSaturacionAirePdte;
	}
	public Double getNumSolucionPdte() {
		return numSolucionPdte;
	}
	public void setNumSolucionPdte(Double numSolucionPdte) {
		this.numSolucionPdte = numSolucionPdte;
	}
	public Double getNumSaturacionSolucionPdte() {
		return numSaturacionSolucionPdte;
	}
	public void setNumSaturacionSolucionPdte(Double numSaturacionSolucionPdte) {
		this.numSaturacionSolucionPdte = numSaturacionSolucionPdte;
	}
	public String getDescripcionObservacion() {
		return descripcionObservacion;
	}
	public void setDescripcionObservacion(String descripcionObservacion) {
		this.descripcionObservacion = descripcionObservacion;
	}
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
}
