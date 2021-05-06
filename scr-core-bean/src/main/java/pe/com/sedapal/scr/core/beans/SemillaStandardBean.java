package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SemillaStandardBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idStandard;
	public Double numVolGGa;
	public Double numVolPoly;
	public Double numInicial;
	public Double numFinal;
	public Double numDepletion;
	public Double numNetDep;
	public Double numPorcentajeDeplec;
	public Double numValorBOD;	
	public Double numPromedioGGA;
	public String estado;
	public String descripcionPtoMuestra;
	public String descripcionBotella;
	
	public Integer getIdStandard() {
		return idStandard;
	}
	public void setIdStandard(Integer idStandard) {
		this.idStandard = idStandard;
	}
	public Double getNumVolGGa() {
		return numVolGGa;
	}
	public void setNumVolGGa(Double numVolGGa) {
		this.numVolGGa = numVolGGa;
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
	public Double getNumNetDep() {
		return numNetDep;
	}
	public void setNumNetDep(Double numNetDep) {
		this.numNetDep = numNetDep;
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
	public Double getNumPromedioGGA() {
		return numPromedioGGA;
	}
	public void setNumPromedioGGA(Double numPromedioGGA) {
		this.numPromedioGGA = numPromedioGGA;
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
