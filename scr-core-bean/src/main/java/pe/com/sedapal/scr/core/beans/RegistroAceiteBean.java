package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroAceiteBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idRegAceite;
	public Integer idRegLaboratorio;
	public Date    fechaRegistro;
	public Integer idPtarxSector;
	public Integer idSubParametro;
	public String  descripcionSubparametro;
	public Integer idPuntoMuestra;
	public String  descripcionPuntoMuestra;
	public Double  numFrasco;
	public Double  numVolumen;
	public Double  numPesoInicial;
	public Double  numPesoFinal;
	public Integer idFormulaResultado;
	public Double  numResultado;
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
	
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Integer getIdRegAceite() {
		return idRegAceite;
	}
	public void setIdRegAceite(Integer idRegAceite) {
		this.idRegAceite = idRegAceite;
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
	public Double getNumFrasco() {
		return numFrasco;
	}
	public void setNumFrasco(Double numFrasco) {
		this.numFrasco = numFrasco;
	}
	public Double getNumVolumen() {
		return numVolumen;
	}
	public void setNumVolumen(Double numVolumen) {
		this.numVolumen = numVolumen;
	}
	public Double getNumPesoInicial() {
		return numPesoInicial;
	}
	public void setNumPesoInicial(Double numPesoInicial) {
		this.numPesoInicial = numPesoInicial;
	}
	public Double getNumPesoFinal() {
		return numPesoFinal;
	}
	public void setNumPesoFinal(Double numPesoFinal) {
		this.numPesoFinal = numPesoFinal;
	}
	public Double getNumResultado() {
		return numResultado;
	}
	public void setNumResultado(Double numResultado) {
		this.numResultado = numResultado;
	}
	public Integer getIdFormulaResultado() {
		return idFormulaResultado;
	}
	public void setIdFormulaResultado(Integer idFormulaResultado) {
		this.idFormulaResultado = idFormulaResultado;
	}
}
