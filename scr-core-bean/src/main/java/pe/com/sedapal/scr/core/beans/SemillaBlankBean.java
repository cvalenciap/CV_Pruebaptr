package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SemillaBlankBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idBlank;
	public Double numPromedio;
	public Double numInicial;
	public Double numFinal;
	public Double numDepletion;
	public String estado;
	public String descripcionPtoMuestra;
	public String descripcionBotella;
	
	public Integer getIdBlank() {
		return idBlank;
	}
	public void setIdBlank(Integer idBlank) {
		this.idBlank = idBlank;
	}
	public Double getNumPromedio() {
		return numPromedio;
	}
	public void setNumPromedio(Double numPromedio) {
		this.numPromedio = numPromedio;
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
