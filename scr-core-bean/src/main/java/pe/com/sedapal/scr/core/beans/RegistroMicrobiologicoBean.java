package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroMicrobiologicoBean extends BaseSptarBean implements Serializable {

	public static final long serialVersionUID = 1L;

	public Integer idRegMicrobiologico;
	public Integer idRegLaboratorio;
	public Date fechaRegistro;
	public Integer idPtarxSector;
	public Integer idSubParametro;
	public String descripcionSubparametro;
	public Integer idPuntoMuestra;
	public String descripcionPuntoMuestra;
	public Integer idAnalista;
	public String nombreAnalista;
	public Date fechaMonitoreo;
	public Date fechaRegDato;
	public String fechaMonitoreoString;
	public String fechaRegDatoString;
	public Integer idParametro;
	public String flagRegistrador;
	public String flagAprobador;
	public Integer indice;
	public Integer indicePadre;
	public Integer secuencia;
	public Integer grupo;
	public Integer idDilucionInicial;
	public String descripcionDilucionInicial;
	public Double numValorDilucion;
	public String descripcionDilucion;
	public Double numCLT24;
	public Double numCLT48;
	public Double numCLVBB24;
	public Double numCLVBB48;
	public Double numEC24;
	public Double numEC48;
	public Double numValorCT;
	public String numCTNMP;
	public Double numValorCTTNMP;
	public String numCTTNMP;
	public Integer idTuboDilucionCLV48;
	public String descripcionTuboDilucionCLV48;
	public Double valorDecimalCLV48;
	public Integer idTuboDilucionEC48;
	public String descripcionTuboDilucionEC48;
	public Double valorDecimalEC48;
	public String descripcionObservacion;
	public Double  intervaloMinimo;
	public Double intervaloMaximo;
	public Integer flagMejorValor;

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getIdRegMicrobiologico() {
		return idRegMicrobiologico;
	}

	public void setIdRegMicrobiologico(Integer idRegMicrobiologico) {
		this.idRegMicrobiologico = idRegMicrobiologico;
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

	public Integer getIndicePadre() {
		return indicePadre;
	}

	public void setIndicePadre(Integer indicePadre) {
		this.indicePadre = indicePadre;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getIdDilucionInicial() {
		return idDilucionInicial;
	}

	public void setIdDilucionInicial(Integer idDilucionInicial) {
		this.idDilucionInicial = idDilucionInicial;
	}

	public String getDescripcionDilucionInicial() {
		return descripcionDilucionInicial;
	}

	public void setDescripcionDilucionInicial(String descripcionDilucionInicial) {
		this.descripcionDilucionInicial = descripcionDilucionInicial;
	}

	public String getDescripcionDilucion() {
		return descripcionDilucion;
	}

	public void setDescripcionDilucion(String descripcionDilucion) {
		this.descripcionDilucion = descripcionDilucion;
	}

	public Double getNumCLT24() {
		return numCLT24;
	}

	public void setNumCLT24(Double numCLT24) {
		this.numCLT24 = numCLT24;
	}

	public Double getNumCLT48() {
		return numCLT48;
	}

	public void setNumCLT48(Double numCLT48) {
		this.numCLT48 = numCLT48;
	}

	public Double getNumCLVBB24() {
		return numCLVBB24;
	}

	public void setNumCLVBB24(Double numCLVBB24) {
		this.numCLVBB24 = numCLVBB24;
	}

	public Double getNumCLVBB48() {
		return numCLVBB48;
	}

	public void setNumCLVBB48(Double numCLVBB48) {
		this.numCLVBB48 = numCLVBB48;
	}

	public Double getNumEC24() {
		return numEC24;
	}

	public void setNumEC24(Double numEC24) {
		this.numEC24 = numEC24;
	}

	public Double getNumEC48() {
		return numEC48;
	}

	public void setNumEC48(Double numEC48) {
		this.numEC48 = numEC48;
	}

	public Double getNumValorCT() {
		return numValorCT;
	}

	public void setNumValorCT(Double numValorCT) {
		this.numValorCT = numValorCT;
	}

	public Double getNumValorCTTNMP() {
		return numValorCTTNMP;
	}

	public void setNumValorCTTNMP(Double numValorCTTNMP) {
		this.numValorCTTNMP = numValorCTTNMP;
	}

	public Double getNumValorDilucion() {
		return numValorDilucion;
	}

	public void setNumValorDilucion(Double numValorDilucion) {
		this.numValorDilucion = numValorDilucion;
	}

	public Integer getIdTuboDilucionCLV48() {
		return idTuboDilucionCLV48;
	}

	public void setIdTuboDilucionCLV48(Integer idTuboDilucionCLV48) {
		this.idTuboDilucionCLV48 = idTuboDilucionCLV48;
	}

	public Integer getIdTuboDilucionEC48() {
		return idTuboDilucionEC48;
	}

	public void setIdTuboDilucionEC48(Integer idTuboDilucionEC48) {
		this.idTuboDilucionEC48 = idTuboDilucionEC48;
	}

	public String getNumCTNMP() {
		return numCTNMP;
	}

	public void setNumCTNMP(String numCTNMP) {
		this.numCTNMP = numCTNMP;
	}

	public String getNumCTTNMP() {
		return numCTTNMP;
	}

	public void setNumCTTNMP(String numCTTNMP) {
		this.numCTTNMP = numCTTNMP;
	}

	public String getDescripcionObservacion() {
		return descripcionObservacion;
	}

	public void setDescripcionObservacion(String descripcionObservacion) {
		this.descripcionObservacion = descripcionObservacion;
	}

	public String getDescripcionTuboDilucionCLV48() {
		return descripcionTuboDilucionCLV48;
	}

	public void setDescripcionTuboDilucionCLV48(String descripcionTuboDilucionCLV48) {
		this.descripcionTuboDilucionCLV48 = descripcionTuboDilucionCLV48;
	}

	public String getDescripcionTuboDilucionEC48() {
		return descripcionTuboDilucionEC48;
	}

	public void setDescripcionTuboDilucionEC48(String descripcionTuboDilucionEC48) {
		this.descripcionTuboDilucionEC48 = descripcionTuboDilucionEC48;
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

	public Integer getFlagMejorValor() {
		return flagMejorValor;
	}

	public void setFlagMejorValor(Integer flagMejorValor) {
		this.flagMejorValor = flagMejorValor;
	}

	public Double getValorDecimalCLV48() {
		return valorDecimalCLV48;
	}

	public void setValorDecimalCLV48(Double valorDecimalCLV48) {
		this.valorDecimalCLV48 = valorDecimalCLV48;
	}

	public Double getValorDecimalEC48() {
		return valorDecimalEC48;
	}

	public void setValorDecimalEC48(Double valorDecimalEC48) {
		this.valorDecimalEC48 = valorDecimalEC48;
	}
}
