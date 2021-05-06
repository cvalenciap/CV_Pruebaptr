package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.List;

public class OpcionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String accion;
	private Integer nivel;
	private Integer tipoOpcion;
	private String codigoOpcion;
	private String codigoOpcionPadre;
	private String rutaCompleta;	
	private String codigoSistema;
	private String codigoModulo;
	
	private List<OpcionBean> listaOpciones;	
	
	public String getCodigoSistema() {
		return codigoSistema;
	}
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	public String getCodigoModulo() {
		return codigoModulo;
	}
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public Integer getTipoOpcion() {
		return tipoOpcion;
	}
	public void setTipoOpcion(Integer tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}
	public String getCodigoOpcion() {
		return codigoOpcion;
	}
	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}
	public String getCodigoOpcionPadre() {
		return codigoOpcionPadre;
	}
	public void setCodigoOpcionPadre(String codigoOpcionPadre) {
		this.codigoOpcionPadre = codigoOpcionPadre;
	}
	public String getRutaCompleta() {
		return rutaCompleta;
	}
	public void setRutaCompleta(String rutaCompleta) {
		this.rutaCompleta = rutaCompleta;
	}
	public List<OpcionBean> getListaOpciones() {
		return listaOpciones;
	}
	public void setListaOpciones(List<OpcionBean> listaOpciones) {
		this.listaOpciones = listaOpciones;
	}
	
	
	
}
