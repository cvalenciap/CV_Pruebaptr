package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SemillaSeededBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idSeeded;
	public Double numValGGa;
	public Double numPromedio;
	public Double numVolumen;
	public Double numInicial;
	public Double numFinal;
	public Double numDepletion;
	public Double numPorcentajeDeplec;
	public Double numValorBOD;
	public Double numValorSCF;	
	public String estado;
	public String descripcionPtoMuestra;
	public String descripcionBotella;
	
	public Integer getIdSeeded() {
		return idSeeded;
	}
	public void setIdSeeded(Integer idSeeded) {
		this.idSeeded = idSeeded;
	}
	public Double getNumValGGa() {
		return numValGGa;
	}
	public void setNumValGGa(Double numValGGa) {
		this.numValGGa = numValGGa;
	}
	public Double getNumPromedio() {
		return numPromedio;
	}
	public void setNumPromedio(Double numPromedio) {
		this.numPromedio = numPromedio;
	}
	public Double getNumVolumen() {
		return numVolumen;
	}
	public void setNumVolumen(Double numVolumen) {
		this.numVolumen = numVolumen;
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
	public Double getNumPorcentajeDeplec() {
		return numPorcentajeDeplec;
	}
	public void setNumPorcentajeDeplec(Double numPorcentajeDeplec) {
		this.numPorcentajeDeplec = numPorcentajeDeplec;
	}
	public Double getNumValorBOD() {
		return numValorBOD;
	}
	public void setNumValorBOD(Double numValorBOD) {
		this.numValorBOD = numValorBOD;
	}
	public Double getNumValorSCF() {
		return numValorSCF;
	}
	public void setNumValorSCF(Double numValorSCF) {
		this.numValorSCF = numValorSCF;
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
	public String getDescripcionBotella() {
		return descripcionBotella;
	}
	public void setDescripcionBotella(String descripcionBotella) {
		this.descripcionBotella = descripcionBotella;
	}
		
}
