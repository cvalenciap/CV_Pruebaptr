package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroSolidoBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer indice;
	public Integer idSolido;
	public Integer idRegistroLaboratorio;
	public Date    fechaRegistro;
	public Integer idPtarxSector;
	public Integer idPuntoMuestra;
	public String  descripcionPuntoMuestra;
	public Double  pesoInicial;
	public Double  volumenFiltrado;
	public Double  pesoFinal;
	public Double  pesoCalcinado;
	public Double  numSst;
	public Double  numSsf;
	public Double  numSsv;
	public Integer idAnalista;
	public Date    fechaMonitoreo;
	public Date    fechaRegDato;
	public String  fechaMonitoreoString;
	public String  fechaRegDatoString;
	public Integer idParametro;
	public String descripcionObservacion;
	public String flagRegistrador;
	public String flagAprobador;
	public String nombreAnalista;
	public Integer idFormulaSst;
	public Integer idFormulaSsf;
	public Integer idFormulaSsv;
	public Integer idSubParametro;
	public String  descripcionSubparametro;
	public Double  intervaloMinimo;
	public Double intervaloMaximo;
	public Integer indicadorFactor;
	public Integer idTipoSolido;
	
	
	public Integer getIdTipoSolido() {
		return idTipoSolido;
	}
	public void setIdTipoSolido(Integer idTipoSolido) {
		this.idTipoSolido = idTipoSolido;
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
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Double getNumSst() {
		return numSst;
	}
	public void setNumSst(Double numSst) {
		this.numSst = numSst;
	}
	public Integer getIdFormulaSst() {
		return idFormulaSst;
	}
	public void setIdFormulaSst(Integer idFormulaSst) {
		this.idFormulaSst = idFormulaSst;
	}
	public Integer getIdFormulaSsf() {
		return idFormulaSsf;
	}
	public void setIdFormulaSsf(Integer idFormulaSsf) {
		this.idFormulaSsf = idFormulaSsf;
	}
	public Integer getIdFormulaSsv() {
		return idFormulaSsv;
	}
	public void setIdFormulaSsv(Integer idFormulaSsv) {
		this.idFormulaSsv = idFormulaSsv;
	}
	public Integer getIdSolido() {
		return idSolido;
	}
	public void setIdSolido(Integer idSolido) {
		this.idSolido = idSolido;
	}
	public Integer getIdRegistroLaboratorio() {
		return idRegistroLaboratorio;
	}
	public void setIdRegistroLaboratorio(Integer idRegistroLaboratorio) {
		this.idRegistroLaboratorio = idRegistroLaboratorio;
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
	public Double getPesoInicial() {
		return pesoInicial;
	}
	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	public Double getVolumenFiltrado() {
		return volumenFiltrado;
	}
	public void setVolumenFiltrado(Double volumenFiltrado) {
		this.volumenFiltrado = volumenFiltrado;
	}
	public Double getPesoFinal() {
		return pesoFinal;
	}
	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}
	public Double getPesoCalcinado() {
		return pesoCalcinado;
	}
	public void setPesoCalcinado(Double pesoCalcinado) {
		this.pesoCalcinado = pesoCalcinado;
	}	
	public Double getNumSsf() {
		return numSsf;
	}
	public void setNumSsf(Double numSsf) {
		this.numSsf = numSsf;
	}
	public Double getNumSsv() {
		return numSsv;
	}
	public void setNumSsv(Double numSsv) {
		this.numSsv = numSsv;
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
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
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
}
