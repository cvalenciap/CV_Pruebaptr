package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroDBOBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer indice;
	public Integer idDBO;
	public Integer idRegistroLaboratorio;
	public Date    fechaRegistro;
	public Integer idPtarxSector;
	public Integer idPuntoMuestra;
	public String  descripcionPuntoMuestra;
	public Integer idSubParametro;
	public String  descripcionSubparametro;
	public Double  numVolumen;
	public Double  numOdi;
	public Double  numOdf;
	public Double  numFactor;
	public Integer idFormulaDeplec;
	public Double  numDeplec;
	public Integer idFormulaDilc;
	public Double  numDilc;
	public Integer idFormulaDbo5;
	public Double  numDbo5;
	public Double numDbo5Formula;
	public Double  numDbo5Sol;
	public Integer idAnalista;
//	public Date    fechaMonitoreo;
	public Timestamp    fechaMonitoreo;
	public Date    fechaRegDato;
	public String  fechaMonitoreoString;
	public String  fechaRegDatoString;
	public Double  intervaloMinimo;
	public Double intervaloMaximo;
	public Integer indicadorFactor;
	public String nombreAnalista;
	public Integer idParametro;
	public String descripcionObservacion;
	public String flagRegistrador;
	public String flagAprobador;
	public Integer nroFrasco;
//	public Date   fechaInicial;
	public Timestamp fechaInicial;
	public Date   fechaVencimiento;
	public String fechaInicialString;
	public String fechaVencimientoString;
	public Double numValorSemilla;
	public Integer indicadorSemilla;
	public Integer idGrupo;
	
	public String indicadorTipoSemilla;
	public Double numDepletion;
	public Double blankPromedio;
	public Double seededPorDeplec;
	public Double seededBOD;
	public Double seededSCF;
	public Double seededPromedio;
	public Double GGANetDeplec;
	public Double GGAPorDeplec;
	public Double GGADBO;
	public Double GGAPromedio;
	public Double samplesNetDeplec;
	public Double samplesDilFactor;
	public Double samplesBOD;
	public Double samplesPromedio;
	public Integer indicePadre;
	public Integer idPtoEnlace;
	public Integer flagMejorValor;
	public Integer secuencia;
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Integer getIndicadorSemilla() {
		return indicadorSemilla;
	}
	public void setIndicadorSemilla(Integer indicadorSemilla) {
		this.indicadorSemilla = indicadorSemilla;
	}
	public Double getNumValorSemilla() {
		return numValorSemilla;
	}
	public void setNumValorSemilla(Double numValorSemilla) {
		this.numValorSemilla = numValorSemilla;
	}
	public Integer getNroFrasco() {
		return nroFrasco;
	}
	public void setNroFrasco(Integer nroFrasco) {
		this.nroFrasco = nroFrasco;
	}
//	public Date getFechaInicial() {
//		return fechaInicial;
//	}
//	public void setFechaInicial(Date fechaInicial) {
//		this.fechaInicial = fechaInicial;
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		this.fechaInicialString = df.format(fechaInicial);
//	}
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.fechaInicialString = df.format(fechaInicial);
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaVencimientoString = df.format(fechaVencimiento);
	}
	public String getFechaInicialString() {
		return fechaInicialString;
	}
	public void setFechaInicialString(String fechaInicialString) {
		this.fechaInicialString = fechaInicialString;
	}
	public String getFechaVencimientoString() {
		return fechaVencimientoString;
	}
	public void setFechaVencimientoString(String fechaVencimientoString) {
		this.fechaVencimientoString = fechaVencimientoString;
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
	public Integer getIdFormulaDeplec() {
		return idFormulaDeplec;
	}
	public void setIdFormulaDeplec(Integer idFormulaDeplec) {
		this.idFormulaDeplec = idFormulaDeplec;
	}
	public Integer getIdFormulaDilc() {
		return idFormulaDilc;
	}
	public void setIdFormulaDilc(Integer idFormulaDilc) {
		this.idFormulaDilc = idFormulaDilc;
	}
	public Integer getIdFormulaDbo5() {
		return idFormulaDbo5;
	}
	public void setIdFormulaDbo5(Integer idFormulaDbo5) {
		this.idFormulaDbo5 = idFormulaDbo5;
	}
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	public Integer getIdDBO() {
		return idDBO;
	}
	public void setIdDBO(Integer idDBO) {
		this.idDBO = idDBO;
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
	public Double getNumVolumen() {
		return numVolumen;
	}
	public void setNumVolumen(Double numVolumen) {
		this.numVolumen = numVolumen;
	}
	public Double getNumOdi() {
		return numOdi;
	}
	public void setNumOdi(Double numOdi) {
		this.numOdi = numOdi;
	}
	public Double getNumOdf() {
		return numOdf;
	}
	public void setNumOdf(Double numOdf) {
		this.numOdf = numOdf;
	}
	public Double getNumFactor() {
		return numFactor;
	}
	public void setNumFactor(Double numFactor) {
		this.numFactor = numFactor;
	}
	public Double getNumDeplec() {
		return numDeplec;
	}
	public void setNumDeplec(Double numDeplec) {
		this.numDeplec = numDeplec;
	}
	public Double getNumDilc() {
		return numDilc;
	}
	public void setNumDilc(Double numDilc) {
		this.numDilc = numDilc;
	}
	public Double getNumDbo5() {
		return numDbo5;
	}
	public void setNumDbo5(Double numDbo5) {
		this.numDbo5 = numDbo5;
	}
	public Double getNumDbo5Sol() {
		return numDbo5Sol;
	}
	public void setNumDbo5Sol(Double numDbo5Sol) {
		this.numDbo5Sol = numDbo5Sol;
	}
	public Integer getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(Integer idAnalista) {
		this.idAnalista = idAnalista;
	}
//	public Date getFechaMonitoreo() {
//		return fechaMonitoreo;
//	}
//	public void setFechaMonitoreo(Date fechaMonitoreo) {
//		this.fechaMonitoreo = fechaMonitoreo;
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		this.fechaMonitoreoString = df.format(fechaMonitoreo);
//	}
	public Timestamp getFechaMonitoreo() {
		return fechaMonitoreo;
	}
	public void setFechaMonitoreo(Timestamp fechaMonitoreo) {
		this.fechaMonitoreo = fechaMonitoreo;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}
	public String getIndicadorTipoSemilla() {
		return indicadorTipoSemilla;
	}
	public void setIndicadorTipoSemilla(String indicadorTipoSemilla) {
		this.indicadorTipoSemilla = indicadorTipoSemilla;
	}
	public Double getNumDepletion() {
		return numDepletion;
	}
	public void setNumDepletion(Double numDepletion) {
		this.numDepletion = numDepletion;
	}
	public Double getBlankPromedio() {
		return blankPromedio;
	}
	public void setBlankPromedio(Double blankPromedio) {
		this.blankPromedio = blankPromedio;
	}
	public Double getSeededPorDeplec() {
		return seededPorDeplec;
	}
	public void setSeededPorDeplec(Double seededPorDeplec) {
		this.seededPorDeplec = seededPorDeplec;
	}
	public Double getSeededBOD() {
		return seededBOD;
	}
	public void setSeededBOD(Double seededBOD) {
		this.seededBOD = seededBOD;
	}
	public Double getSeededSCF() {
		return seededSCF;
	}
	public void setSeededSCF(Double seededSCF) {
		this.seededSCF = seededSCF;
	}
	public Double getSeededPromedio() {
		return seededPromedio;
	}
	public void setSeededPromedio(Double seededPromedio) {
		this.seededPromedio = seededPromedio;
	}
	public Double getGGANetDeplec() {
		return GGANetDeplec;
	}
	public void setGGANetDeplec(Double gGANetDeplec) {
		GGANetDeplec = gGANetDeplec;
	}
	public Double getGGAPorDeplec() {
		return GGAPorDeplec;
	}
	public void setGGAPorDeplec(Double gGAPorDeplec) {
		GGAPorDeplec = gGAPorDeplec;
	}
	public Double getGGADBO() {
		return GGADBO;
	}
	public void setGGADBO(Double gGADBO) {
		GGADBO = gGADBO;
	}
	public Double getGGAPromedio() {
		return GGAPromedio;
	}
	public void setGGAPromedio(Double gGAPromedio) {
		GGAPromedio = gGAPromedio;
	}
	public Double getSamplesNetDeplec() {
		return samplesNetDeplec;
	}
	public void setSamplesNetDeplec(Double samplesNetDeplec) {
		this.samplesNetDeplec = samplesNetDeplec;
	}
	public Double getSamplesDilFactor() {
		return samplesDilFactor;
	}
	public void setSamplesDilFactor(Double samplesDilFactor) {
		this.samplesDilFactor = samplesDilFactor;
	}
	public Double getSamplesBOD() {
		return samplesBOD;
	}
	public void setSamplesBOD(Double samplesBOD) {
		this.samplesBOD = samplesBOD;
	}
	public Double getSamplesPromedio() {
		return samplesPromedio;
	}
	public void setSamplesPromedio(Double samplesPromedio) {
		this.samplesPromedio = samplesPromedio;
	}
	public Integer getIndicePadre() {
		return indicePadre;
	}
	public void setIndicePadre(Integer indicePadre) {
		this.indicePadre = indicePadre;
	}
	public Integer getIdPtoEnlace() {
		return idPtoEnlace;
	}
	public void setIdPtoEnlace(Integer idPtoEnlace) {
		this.idPtoEnlace = idPtoEnlace;
	}
	public Integer getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	public Integer getFlagMejorValor() {
		return flagMejorValor;
	}
	public void setFlagMejorValor(Integer flagMejorValor) {
		this.flagMejorValor = flagMejorValor;
	}
	public Double getNumDbo5Formula() {
		return numDbo5Formula;
	}
	public void setNumDbo5Formula(Double numDbo5Formula) {
		this.numDbo5Formula = numDbo5Formula;
	}
	
}
