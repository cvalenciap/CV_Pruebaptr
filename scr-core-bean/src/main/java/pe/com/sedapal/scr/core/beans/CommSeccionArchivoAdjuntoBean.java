/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class CommSeccionArchivoAdjuntoBean.
 */
public class CommSeccionArchivoAdjuntoBean implements Serializable {

	/** The Constant serialVersionUID. */

	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The codigo. */
	private Integer codigo;

	/** The cantidad maxima archivos. */
	private Integer cantidadMaximaArchivos;

	/** The lst archivo adjunto. */
	private LinkedList<CommArchivoAdjuntoBean> lstArchivoAdjunto;

	/**
	 * Instantiates a new comm seccion archivo adjunto bean.
	 *
	 * @param id the id
	 * @param codigo the codigo
	 * @param cantidadMaximaArchivos the cantidad maxima archivos
	 */
	public CommSeccionArchivoAdjuntoBean(String id, Integer codigo, Integer cantidadMaximaArchivos) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.cantidadMaximaArchivos = cantidadMaximaArchivos;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the cantidad maxima archivos.
	 *
	 * @return the cantidad maxima archivos
	 */
	public Integer getCantidadMaximaArchivos() {
		return cantidadMaximaArchivos;
	}

	/**
	 * Sets the cantidad maxima archivos.
	 *
	 * @param cantidadMaximaArchivos the new cantidad maxima archivos
	 */
	public void setCantidadMaximaArchivos(Integer cantidadMaximaArchivos) {
		this.cantidadMaximaArchivos = cantidadMaximaArchivos;
	}

	/**
	 * Gets the lst archivo adjunto.
	 *
	 * @return the lst archivo adjunto
	 */
	public LinkedList<CommArchivoAdjuntoBean> getLstArchivoAdjunto() {
		return lstArchivoAdjunto;
	}

	/**
	 * Sets the lst archivo adjunto.
	 *
	 * @param lstArchivoAdjunto the new lst archivo adjunto
	 */
	public void setLstArchivoAdjunto(LinkedList<CommArchivoAdjuntoBean> lstArchivoAdjunto) {
		this.lstArchivoAdjunto = lstArchivoAdjunto;
	}

}
