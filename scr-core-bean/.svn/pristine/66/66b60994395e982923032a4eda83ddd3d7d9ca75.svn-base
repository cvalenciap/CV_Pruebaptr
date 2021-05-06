package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RegistroLabReporteBean extends BaseSptarBean implements Serializable {

	public static final long serialVersionUID = 1L;
	
	public Integer idRegistroLaboratorio;
	public Integer idTipoRegistro;
	public String descripcionTipoRegistro;
	public Integer idSubParametro;
	public String descripcionSubParametro;
	public Integer idPtoMuestra;
	public String descripcionPtoMuestra;
	public Double numValor;
	public String descripcionNumValor;
	public Date fechaRegistro; 
	public Timestamp fechaRegistroTimestamp;
	public String fechaRegistroString;
	public Integer idPtarxSector;
	public String descripcionPtarxSector;
	public Integer secuencia;
	public Integer secuenciaSubParam;
	public String tipoSemilla;
	public String idEPSP;
	
	public Integer getIdRegistroLaboratorio() {
		return idRegistroLaboratorio;
	}
	public void setIdRegistroLaboratorio(Integer idRegistroLaboratorio) {
		this.idRegistroLaboratorio = idRegistroLaboratorio;
	}
	public Integer getIdTipoRegistro() {
		return idTipoRegistro;
	}
	public void setIdTipoRegistro(Integer idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
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
	public Double getNumValor() {
		return numValor;
	}
	public void setNumValor(Double numValor) {
		this.numValor = numValor;
	}
	public String getDescripcionTipoRegistro() {
		return descripcionTipoRegistro;
	}
	public void setDescripcionTipoRegistro(String descripcionTipoRegistro) {
		this.descripcionTipoRegistro = descripcionTipoRegistro;
	}
	public String getDescripcionNumValor() {
		return descripcionNumValor;
	}
	public void setDescripcionNumValor(String descripcionNumValor) {
		this.descripcionNumValor = descripcionNumValor;
	}
	public String getFechaRegistroString() {
		return fechaRegistroString;
	}
	public void setFechaRegistroString(String fechaRegistroString) {
		this.fechaRegistroString = fechaRegistroString;
	}
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public String getDescripcionPtarxSector() {
		return descripcionPtarxSector;
	}
	public void setDescripcionPtarxSector(String descripcionPtarxSector) {
		this.descripcionPtarxSector = descripcionPtarxSector;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public String getTipoSemilla() {
		return tipoSemilla;
	}
	public void setTipoSemilla(String tipoSemilla) {
		this.tipoSemilla = tipoSemilla;
	}
	public Integer getSecuenciaSubParam() {
		return secuenciaSubParam;
	}
	public void setSecuenciaSubParam(Integer secuenciaSubParam) {
		this.secuenciaSubParam = secuenciaSubParam;
	}
	public String getIdEPSP() {
		return idEPSP;
	}
	public void setIdEPSP(String idEPSP) {
		this.idEPSP = idEPSP;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegistroString = df.format(fechaRegistro);
	}
	public Timestamp getFechaRegistroTimestamp() {
		return fechaRegistroTimestamp;
	}
	public void setFechaRegistroTimestamp(Timestamp fechaRegistroTimestamp) {
		this.fechaRegistroTimestamp = fechaRegistroTimestamp;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.fechaRegistroString = df.format(fechaRegistroTimestamp);
	}
	
}