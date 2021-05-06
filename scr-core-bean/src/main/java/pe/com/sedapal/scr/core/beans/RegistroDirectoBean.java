package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroDirectoBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idRegDirecto;
	public Integer idRegLaboratorio;
	public Date    fechaRegistro;
	public Integer idPtarxSector;
	public Integer idSubParametro;
	public String  descripcionSubparametro;
	public Integer idPuntoMuestra;
	public String  descripcionPuntoMuestra;
	public Double  numValor;
	public Double  numProfundida;
	public Double  numValorDQO;
	public Double  numFactor;
	public Integer idAnalista;
	public Date    fechaMonitoreo;
	public Date    fechaRegDato;
	public String  fechaMonitoreoString;
	public String  fechaRegDatoString;
	public Integer idParametro;
	public Double  intervaloMinimo;
	public Double intervaloMaximo;
	public Integer indicadorFactor;
	public String descripcionObservacion;
	public String flagRegistrador;
	public String flagAprobador;
	public String nombreAnalista;
	public Integer indice;	
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
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
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
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
	public String getDescripcionObservacion() {
		return descripcionObservacion;
	}
	public void setDescripcionObservacion(String descripcionObservacion) {
		this.descripcionObservacion = descripcionObservacion;
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
	public Double getNumFactor() {
		return numFactor;
	}
	public void setNumFactor(Double numFactor) {
		this.numFactor = numFactor;
	}
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}
	public Integer getIdRegDirecto() {
		return idRegDirecto;
	}
	public void setIdRegDirecto(Integer idRegDirecto) {
		this.idRegDirecto = idRegDirecto;
	}
	public Integer getIdRegLaboratorio() {
		return idRegLaboratorio;
	}
	public void setIdRegLaboratorio(Integer idRegLaboratorio) {
		this.idRegLaboratorio = idRegLaboratorio;
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
	public Integer getIdSubParametro() {
		return idSubParametro;
	}
	public void setIdSubParametro(Integer idSubParametro) {
		this.idSubParametro = idSubParametro;
	}
	public String getDescripcionSubparametro() {
		return descripcionSubparametro;
	}
	public void setDescripcionSubparametro(String descripcionSubparametro) {
		this.descripcionSubparametro = descripcionSubparametro;
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
	public Double getNumValor() {
		return numValor;
	}
	public void setNumValor(Double numValor) {
		this.numValor = numValor;
	}
	public Double getNumProfundida() {
		return numProfundida;
	}
	public void setNumProfundida(Double numProfundida) {
		this.numProfundida = numProfundida;
	}
	public Double getNumValorDQO() {
		return numValorDQO;
	}
	public void setNumValorDQO(Double numValorDQO) {
		this.numValorDQO = numValorDQO;
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
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegDatoString = df.format(fechaRegDato);
	}	
}
