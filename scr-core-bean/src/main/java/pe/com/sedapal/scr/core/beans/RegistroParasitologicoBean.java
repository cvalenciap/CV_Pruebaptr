package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroParasitologicoBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idRegParasitologico;
	public Integer idRegLaboratorio;
	public Integer idMicroorganismo;
	public String descripcionMicroorganismo;
	public Date    fechaRegistro;
	public Integer idPtarxSector;
	public Integer idSubParametro;
	public String  descripcionSubparametro;
	public Integer idPuntoMuestra;
	public String  descripcionPuntoMuestra;
	public Double  numCantidad;
	public Double numVolumen;
	public Integer idAnalista;
	public String nombreAnalista;
	public Date    fechaMonitoreo;
	public Date    fechaRegDato;
	public String fechaMonitoreoString;
	public String fechaRegDatoString;
	public Integer idParametro;
	public Double  intervaloMinimo;
	public Double intervaloMaximo;
	public Integer indicadorFactor;
	public String descripcionObservacion;
	public String flagRegistrador;
	public String flagAprobador;
	public Integer indice;
	public Integer idFormula;
	public Double numResultado;
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	
	
	public Integer getIdRegParasitologico() {
		return idRegParasitologico;
	}
	public void setIdRegParasitologico(Integer idRegParasitologico) {
		this.idRegParasitologico = idRegParasitologico;
	}
	public Integer getIdRegLaboratorio() {
		return idRegLaboratorio;
	}
	public void setIdRegLaboratorio(Integer idRegLaboratorio) {
		this.idRegLaboratorio = idRegLaboratorio;
	}
	public Integer getIdMicroorganismo() {
		return idMicroorganismo;
	}
	public void setIdMicroorganismo(Integer idMicroorganismo) {
		this.idMicroorganismo = idMicroorganismo;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public String getDescripcionMicroorganismo() {
		return descripcionMicroorganismo;
	}
	public void setDescripcionMicroorganismo(String descripcionMicroorganismo) {
		this.descripcionMicroorganismo = descripcionMicroorganismo;
	}
	public Integer getIdPuntoMuestra() {
		return idPuntoMuestra;
	}
	public void setIdPuntoMuestra(Integer idPuntoMuestra) {
		this.idPuntoMuestra = idPuntoMuestra;
	}
	public String getDescripcionPuntoMuestra() {
		return descripcionPuntoMuestra;
	}
	public void setDescripcionPuntoMuestra(String descripcionPuntoMuestra) {
		this.descripcionPuntoMuestra = descripcionPuntoMuestra;
	}
	public Double getNumCantidad() {
		return numCantidad;
	}
	public void setNumCantidad(Double numCantidad) {
		this.numCantidad = numCantidad;
	}
	public Integer getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(Integer idAnalista) {
		this.idAnalista = idAnalista;
	}
	public Date getFechaMonitoreo() {
		return fechaMonitoreo;
	}
	public void setFechaMonitoreo(Date fechaMonitoreo) {
		this.fechaMonitoreo = fechaMonitoreo;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaMonitoreoString = df.format(fechaMonitoreo);
	}
	public Date getFechaRegDato() {
		return fechaRegDato;
	}
	public void setFechaRegDato(Date fechaRegDato) {
		this.fechaRegDato = fechaRegDato;
		this.fechaRegDato = fechaRegDato;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegDatoString = df.format(fechaRegDato);
	}
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}
	public Double getIntervaloMinimo() {
		return intervaloMinimo;
	}
	public void setIntervaloMinimo(Double intervaloMinimo) {
		this.intervaloMinimo = intervaloMinimo;
	}
	public Double getIntervaloMaximo() {
		return intervaloMaximo;
	}
	public void setIntervaloMaximo(Double intervaloMaximo) {
		this.intervaloMaximo = intervaloMaximo;
	}
	public Integer getIndicadorFactor() {
		return indicadorFactor;
	}
	public void setIndicadorFactor(Integer indicadorFactor) {
		this.indicadorFactor = indicadorFactor;
	}
	public String getDescripcionObservacion() {
		return descripcionObservacion;
	}
	public void setDescripcionObservacion(String descripcionObservacion) {
		this.descripcionObservacion = descripcionObservacion;
	}
	public String getFlagRegistrador() {
		return flagRegistrador;
	}
	public void setFlagRegistrador(String flagRegistrador) {
		this.flagRegistrador = flagRegistrador;
	}
	public String getFlagAprobador() {
		return flagAprobador;
	}
	public void setFlagAprobador(String flagAprobador) {
		this.flagAprobador = flagAprobador;
	}
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}
	public String getFechaMonitoreoString() {
		return fechaMonitoreoString;
	}
	public void setFechaMonitoreoString(String fechaMonitoreoString) {
		this.fechaMonitoreoString = fechaMonitoreoString;
	}
	public String getFechaRegDatoString() {
		return fechaRegDatoString;
	}
	public void setFechaRegDatoString(String fechaRegDatoString) {
		this.fechaRegDatoString = fechaRegDatoString;
	}
	public String getDescripcionSubparametro() {
		return descripcionSubparametro;
	}
	public void setDescripcionSubparametro(String descripcionSubparametro) {
		this.descripcionSubparametro = descripcionSubparametro;
	}
	public Integer getIdSubParametro() {
		return idSubParametro;
	}
	public void setIdSubParametro(Integer idSubParametro) {
		this.idSubParametro = idSubParametro;
	}
	public Double getNumVolumen() {
		return numVolumen;
	}
	public void setNumVolumen(Double numVolumen) {
		this.numVolumen = numVolumen;
	}
	public Integer getIdFormula() {
		return idFormula;
	}
	public void setIdFormula(Integer idFormula) {
		this.idFormula = idFormula;
	}
	public Double getNumResultado() {
		return numResultado;
	}
	public void setNumResultado(Double numResultado) {
		this.numResultado = numResultado;
	}
}
