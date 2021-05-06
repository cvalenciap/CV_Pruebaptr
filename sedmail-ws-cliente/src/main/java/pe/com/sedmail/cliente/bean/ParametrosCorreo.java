package pe.com.sedmail.cliente.bean;

import java.io.Serializable;
import java.util.List;

public class ParametrosCorreo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTipoEnvio;
	private String remitenteCorreo;
	private String remitenteClave;
	private String remitente;
	private List<String> destinatario;
	private List<String> copia;
	private List<String> copiaOculta;
	private String asunto;
	private String cuerpoHTML;
	private List<ArchivoAdjunto> archivosAdjuntos;
	private String userOffice365;
	private String passOffice365;
	
	public Integer getIdTipoEnvio() {
		return idTipoEnvio;
	}
	public void setIdTipoEnvio(Integer idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}
	public String getRemitenteCorreo() {
		return remitenteCorreo;
	}
	public void setRemitenteCorreo(String remitenteCorreo) {
		this.remitenteCorreo = remitenteCorreo;
	}
	public String getRemitenteClave() {
		return remitenteClave;
	}
	public void setRemitenteClave(String remitenteClave) {
		this.remitenteClave = remitenteClave;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public List<String> getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(List<String> destinatario) {
		this.destinatario = destinatario;
	}
	public List<String> getCopia() {
		return copia;
	}
	public void setCopia(List<String> copia) {
		this.copia = copia;
	}
	public List<String> getCopiaOculta() {
		return copiaOculta;
	}
	public void setCopiaOculta(List<String> copiaOculta) {
		this.copiaOculta = copiaOculta;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public List<ArchivoAdjunto> getArchivosAdjuntos() {
		return archivosAdjuntos;
	}
	public void setArchivosAdjuntos(List<ArchivoAdjunto> archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}
	public String getUserOffice365() {
		return userOffice365;
	}
	public void setUserOffice365(String userOffice365) {
		this.userOffice365 = userOffice365;
	}
	public String getPassOffice365() {
		return passOffice365;
	}
	public void setPassOffice365(String passOffice365) {
		this.passOffice365 = passOffice365;
	}
	public String getCuerpoHTML() {
		return cuerpoHTML;
	}
	public void setCuerpoHTML(String cuerpoHTML) {
		this.cuerpoHTML = cuerpoHTML;
	}
	
}
