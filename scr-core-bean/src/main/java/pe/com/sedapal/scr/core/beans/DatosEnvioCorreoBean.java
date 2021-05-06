/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosEnvioCorreoBean.
 */
public class DatosEnvioCorreoBean implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The str remitente. */
	private String strRemitente;
	
	/** The str destinatarios. */
	private String strDestinatarios;
	
	/** The str copiados. */
	private String strCopiados;
	
	/** The str asunto. */
	private String strAsunto;
	
	/** The str cuerpo. */
	private String strCuerpo;
	
	/** The bol puede enviar. */
	private boolean bolPuedeEnviar;
	
	/**
	 * Gets the str remitente.
	 *
	 * @return the str remitente
	 */
	public String getStrRemitente() {
		return strRemitente;
	}
	
	/**
	 * Sets the str remitente.
	 *
	 * @param strRemitente the new str remitente
	 */
	public void setStrRemitente(String strRemitente) {
		this.strRemitente = strRemitente;
	}
	
	/**
	 * Gets the str destinatarios.
	 *
	 * @return the str destinatarios
	 */
	public String getStrDestinatarios() {
		return strDestinatarios;
	}
	
	/**
	 * Sets the str destinatarios.
	 *
	 * @param strDestinatarios the new str destinatarios
	 */
	public void setStrDestinatarios(String strDestinatarios) {
		this.strDestinatarios = strDestinatarios;
	}
	
	/**
	 * Gets the str copiados.
	 *
	 * @return the str copiados
	 */
	public String getStrCopiados() {
		return strCopiados;
	}
	
	/**
	 * Sets the str copiados.
	 *
	 * @param strCopiados the new str copiados
	 */
	public void setStrCopiados(String strCopiados) {
		this.strCopiados = strCopiados;
	}
	
	/**
	 * Gets the str asunto.
	 *
	 * @return the str asunto
	 */
	public String getStrAsunto() {
		return strAsunto;
	}
	
	/**
	 * Sets the str asunto.
	 *
	 * @param strAsunto the new str asunto
	 */
	public void setStrAsunto(String strAsunto) {
		this.strAsunto = strAsunto;
	}
	
	/**
	 * Gets the str cuerpo.
	 *
	 * @return the str cuerpo
	 */
	public String getStrCuerpo() {
		return strCuerpo;
	}
	
	/**
	 * Sets the str cuerpo.
	 *
	 * @param strCuerpo the new str cuerpo
	 */
	public void setStrCuerpo(String strCuerpo) {
		this.strCuerpo = strCuerpo;
	}
	
	/**
	 * Checks if is bol puede enviar.
	 *
	 * @return true, if is bol puede enviar
	 */
	public boolean isBolPuedeEnviar() {
		return bolPuedeEnviar;
	}
	
	/**
	 * Sets the bol puede enviar.
	 *
	 * @param bolPuedeEnviar the new bol puede enviar
	 */
	public void setBolPuedeEnviar(boolean bolPuedeEnviar) {
		this.bolPuedeEnviar = bolPuedeEnviar;
	}
}
