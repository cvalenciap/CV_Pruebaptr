package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

public class SolidoSuspendidoBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idSolidoSuspendido;	
	public Integer idPtarxSector;
	public Integer idTipoSolido;
	public String descripcionPtar;	
	public String descripcionSector;	
	public Integer idPuntoMuestra;	
	public String descripcionPunto;	
	public Double nuPesoInicial;	
	public Double nuPesoFiltrado;	
	public Double nuPesoFinal;	
	public Double nuPesoCalcina;
	public Double nNumSS;	
	public Double nNumSSF;	
	public Double nNumSSV;	
	public String estado;
	public String descripcionTipoSolido;
	
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public Integer getIdTipoSolido() {
		return idTipoSolido;
	}
	public void setIdTipoSolido(Integer idTipoSolido) {
		this.idTipoSolido = idTipoSolido;
	}
	public String getDescripcionPtar() {
		return descripcionPtar;
	}
	public void setDescripcionPtar(String descripcionPtar) {
		this.descripcionPtar = descripcionPtar;
	}
	public String getDescripcionSector() {
		return descripcionSector;
	}
	public void setDescripcionSector(String descripcionSector) {
		this.descripcionSector = descripcionSector;
	}
	public Integer getIdPuntoMuestra() {
		return idPuntoMuestra;
	}
	public void setIdPuntoMuestra(Integer idPuntoMuestra) {
		this.idPuntoMuestra = idPuntoMuestra;
	}
	public String getDescripcionPunto() {
		return descripcionPunto;
	}
	public void setDescripcionPunto(String descripcionPunto) {
		this.descripcionPunto = descripcionPunto;
	}
	public Double getNuPesoInicial() {
		return nuPesoInicial;
	}
	public void setNuPesoInicial(Double nuPesoInicial) {
		this.nuPesoInicial = nuPesoInicial;
	}
	public Double getNuPesoFiltrado() {
		return nuPesoFiltrado;
	}
	public void setNuPesoFiltrado(Double nuPesoFiltrado) {
		this.nuPesoFiltrado = nuPesoFiltrado;
	}
	public Double getNuPesoFinal() {
		return nuPesoFinal;
	}
	public void setNuPesoFinal(Double nuPesoFinal) {
		this.nuPesoFinal = nuPesoFinal;
	}
	public Double getNuPesoCalcina() {
		return nuPesoCalcina;
	}
	public void setNuPesoCalcina(Double nuPesoCalcina) {
		this.nuPesoCalcina = nuPesoCalcina;
	}
	public Double getnNumSS() {
		return nNumSS;
	}
	public void setnNumSS(Double nNumSS) {
		this.nNumSS = nNumSS;
	}
	public Double getnNumSSF() {
		return nNumSSF;
	}
	public void setnNumSSF(Double nNumSSF) {
		this.nNumSSF = nNumSSF;
	}
	public Double getnNumSSV() {
		return nNumSSV;
	}
	public void setnNumSSV(Double nNumSSV) {
		this.nNumSSV = nNumSSV;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getIdSolidoSuspendido() {
		return idSolidoSuspendido;
	}
	public void setIdSolidoSuspendido(Integer idSolidoSuspendido) {
		this.idSolidoSuspendido = idSolidoSuspendido;
	}
	public String getDescripcionTipoSolido() {
		return descripcionTipoSolido;
	}
	public void setDescripcionTipoSolido(String descripcionTipoSolido) {
		this.descripcionTipoSolido = descripcionTipoSolido;
	}
}
