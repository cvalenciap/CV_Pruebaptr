package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SemillaSamplesBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idSamples;
	public String descripcionBotella;
	public Double numVolSample;
	public Double numVolPoly;
	public Double numInicial;
	public Double numFinal;
	public Double numDepletion;
	public Double numValorSCF;
	public Double numValorNetDep;
	public Double numDilcFactor;
	public Double numSampleBOD;	
	public Double numPromedioDBO;
	public String estado;
	public String descripcionPtoMuestra;
	
	public Integer getIdSamples() {
		return idSamples;
	}
	public void setIdSamples(Integer idSamples) {
		this.idSamples = idSamples;
	}
	public String getDescripcionBotella() {
		return descripcionBotella;
	}
	public void setDescripcionBotella(String descripcionBotella) {
		this.descripcionBotella = descripcionBotella;
	}
	public Double getNumVolSample() {
		return numVolSample;
	}
	public void setNumVolSample(Double numVolSample) {
		this.numVolSample = numVolSample;
	}
	public Double getNumVolPoly() {
		return numVolPoly;
	}
	public void setNumVolPoly(Double numVolPoly) {
		this.numVolPoly = numVolPoly;
	}
	public Double getNumInicial() {
		return numInicial;
	}
	public void setNumInicial(Double numInicial) {
		this.numInicial = numInicial;
	}
	public Double getNumFinal() {
		return numFinal;
	}
	public void setNumFinal(Double numFinal) {
		this.numFinal = numFinal;
	}
	public Double getNumDepletion() {
		return numDepletion;
	}
	public void setNumDepletion(Double numDepletion) {
		this.numDepletion = numDepletion;
	}
	public Double getNumValorSCF() {
		return numValorSCF;
	}
	public void setNumValorSCF(Double numValorSCF) {
		this.numValorSCF = numValorSCF;
	}
	public Double getNumValorNetDep() {
		return numValorNetDep;
	}
	public void setNumValorNetDep(Double numValorNetDep) {
		this.numValorNetDep = numValorNetDep;
	}
	public Double getNumDilcFactor() {
		return numDilcFactor;
	}
	public void setNumDilcFactor(Double numDilcFactor) {
		this.numDilcFactor = numDilcFactor;
	}
	public Double getNumSampleBOD() {
		return numSampleBOD;
	}
	public void setNumSampleBOD(Double numSampleBOD) {
		this.numSampleBOD = numSampleBOD;
	}
	public Double getNumPromedioDBO() {
		return numPromedioDBO;
	}
	public void setNumPromedioDBO(Double numPromedioDBO) {
		this.numPromedioDBO = numPromedioDBO;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcionPtoMuestra() {
		return descripcionPtoMuestra;
	}
	public void setDescripcionPtoMuestra(String descripcionPtoMuestra) {
		this.descripcionPtoMuestra = descripcionPtoMuestra;
	}
}
