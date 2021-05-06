package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class TuboDilucionBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idTuboDilucion;
	
	public String combinacion;
	
	public Double nMP100;
	
	public String descripcionNMP100;
	
	public Double debajo95;
	
	public Double sobre95;
	
	public String estado;

	public String descripcionExponencial;
	
	public Double valorDecimal;
	
	
	public String getDescripcionNMP100() {
		return descripcionNMP100;
	}

	public void setDescripcionNMP100(String descripcionNMP100) {
		this.descripcionNMP100 = descripcionNMP100;
	}

	public Integer getIdTuboDilucion() {
		return idTuboDilucion;
	}

	public void setIdTuboDilucion(Integer idTuboDilucion) {
		this.idTuboDilucion = idTuboDilucion;
	}

	public String getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(String combinacion) {
		this.combinacion = combinacion;
	}

	public Double getnMP100() {
		return nMP100;
	}

	public void setnMP100(Double nMP100) {
		this.nMP100 = nMP100;
	}

	public Double getDebajo95() {
		return debajo95;
	}

	public void setDebajo95(Double debajo95) {
		this.debajo95 = debajo95;
	}

	public Double getSobre95() {
		return sobre95;
	}

	public void setSobre95(Double sobre95) {
		this.sobre95 = sobre95;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcionExponencial() {
		return descripcionExponencial;
	}

	public void setDescripcionExponencial(String descripcionExponencial) {
		this.descripcionExponencial = descripcionExponencial;
	}

	public Double getValorDecimal() {
		return valorDecimal;
	}

	public void setValorDecimal(Double valorDecimal) {
		this.valorDecimal = valorDecimal;
	}	
}
