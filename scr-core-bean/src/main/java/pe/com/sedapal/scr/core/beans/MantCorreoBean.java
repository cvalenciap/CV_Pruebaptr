/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class MantCorreoBean.
 */
public class MantCorreoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private String codigo;
	
	/** The cod tipo operacion. */
	private String codTipoOperacion;
	
	/** The estado. */
	private String estado;
	
	/** The a usu upd. */
	private String aUsuUpd;
	
	/** The a programa. */
	private String aPrograma;
	
	/** The lon cod area. */
	private Long lonCodArea;
	
	/** The lon cod sist. */
	private Long lonCodSist;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the cod tipo operacion.
	 *
	 * @return the cod tipo operacion
	 */
	public String getCodTipoOperacion() {
		return codTipoOperacion;
	}
	
	/**
	 * Sets the cod tipo operacion.
	 *
	 * @param codTipoOperacion the new cod tipo operacion
	 */
	public void setCodTipoOperacion(String codTipoOperacion) {
		this.codTipoOperacion = codTipoOperacion;
	}
	
	/**
	 * Gets the a usu upd.
	 *
	 * @return the a usu upd
	 */
	public String getaUsuUpd() {
		return aUsuUpd;
	}
	
	/**
	 * Sets the a usu upd.
	 *
	 * @param aUsuUpd the new a usu upd
	 */
	public void setaUsuUpd(String aUsuUpd) {
		this.aUsuUpd = aUsuUpd;
	}
	
	/**
	 * Gets the a programa.
	 *
	 * @return the a programa
	 */
	public String getaPrograma() {
		return aPrograma;
	}
	
	/**
	 * Sets the a programa.
	 *
	 * @param aPrograma the new a programa
	 */
	public void setaPrograma(String aPrograma) {
		this.aPrograma = aPrograma;
	}
	
	/**
	 * Gets the lon cod sist.
	 *
	 * @return the lon cod sist
	 */
	public Long getLonCodSist() {
		return lonCodSist;
	}
	
	/**
	 * Sets the lon cod sist.
	 *
	 * @param lonCodSist the new lon cod sist
	 */
	public void setLonCodSist(Long lonCodSist) {
		this.lonCodSist = lonCodSist;
	}
	
	/**
	 * Gets the lon cod area.
	 *
	 * @return the lon cod area
	 */
	public Long getLonCodArea() {
		return lonCodArea;
	}
	
	/**
	 * Sets the lon cod area.
	 *
	 * @param lonCodArea the new lon cod area
	 */
	public void setLonCodArea(Long lonCodArea) {
		this.lonCodArea = lonCodArea;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
