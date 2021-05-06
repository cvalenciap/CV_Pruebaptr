/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class CommArchivoAdjuntoBean.
 */
public class CommArchivoAdjuntoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The usuario creacion. */
	private String usuarioCreacion;

	/** The fecha creacion. */
	private String fechaCreacion;

	/** The usuario modificacion. */
	private String usuarioModificacion;

	/** The fecha modificacion. */
	private String fechaModificacion;

	/** The nombre programa. */
	private String nombrePrograma;

	/** The estado. */
	private Integer estado;

	/** The numero archivo. */
	private Long numeroArchivo;

	/** The codigo tipo documento. */
	private Integer codigoTipoDocumento;

	/** The codigo documento. */
	private Integer codigoDocumento;

	/** The extension archivo. */
	private String extensionArchivo; // fileExtension

	/** The codigo seccion. */
	private Integer codigoSeccion; // codSeccion

	/** The nombre archivo original. */
	private String nombreArchivoOriginal; // fileName

	/** The tamanho kilobytes. */
	private Long tamanhoKilobytes; // fileSize

	/** The ruta archivo. */
	private String rutaArchivo; // fileFinalPath

	/** The tipo archivo. */
	private String tipoArchivo; // fileType

	/** The nombre sin extension. */
	private String nombreSinExtension; // fileShortName

	/** The nombre temporal. */
	private String nombreTemporal; // fileNameTemporal

	/** The ruta temporal archivo. */
	private String rutaTemporalArchivo; // fileTemporalPath

	/**
	 * Instantiates a new comm archivo adjunto bean.
	 */
	public CommArchivoAdjuntoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new comm archivo adjunto bean.
	 *
	 * @param usuarioCreacion the usuario creacion
	 * @param fechaCreacion the fecha creacion
	 * @param usuarioModificacion the usuario modificacion
	 * @param fechaModificacion the fecha modificacion
	 * @param nombrePrograma the nombre programa
	 * @param estado the estado
	 * @param numeroArchivo the numero archivo
	 * @param codigoTipoDocumento the codigo tipo documento
	 * @param codigoDocumento the codigo documento
	 * @param extensionArchivo the extension archivo
	 * @param codigoSeccion the codigo seccion
	 * @param nombreArchivoOriginal the nombre archivo original
	 * @param tamanhoKilobytes the tamanho kilobytes
	 * @param rutaArchivo the ruta archivo
	 * @param tipoArchivo the tipo archivo
	 * @param nombreSinExtension the nombre sin extension
	 * @param nombreTemporal the nombre temporal
	 * @param rutaTemporalArchivo the ruta temporal archivo
	 */
	public CommArchivoAdjuntoBean(String usuarioCreacion, String fechaCreacion, String usuarioModificacion,
			String fechaModificacion, String nombrePrograma, Integer estado, Long numeroArchivo,
			Integer codigoTipoDocumento, Integer codigoDocumento, String extensionArchivo, Integer codigoSeccion,
			String nombreArchivoOriginal, Long tamanhoKilobytes, String rutaArchivo, String tipoArchivo,
			String nombreSinExtension, String nombreTemporal, String rutaTemporalArchivo) {
		super();
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.fechaModificacion = fechaModificacion;
		this.nombrePrograma = nombrePrograma;
		this.estado = estado;
		this.numeroArchivo = numeroArchivo;
		this.codigoTipoDocumento = codigoTipoDocumento;
		this.codigoDocumento = codigoDocumento;
		this.extensionArchivo = extensionArchivo;
		this.codigoSeccion = codigoSeccion;
		this.nombreArchivoOriginal = nombreArchivoOriginal;
		this.tamanhoKilobytes = tamanhoKilobytes;
		this.rutaArchivo = rutaArchivo;
		this.tipoArchivo = tipoArchivo;
		this.nombreSinExtension = nombreSinExtension;
		this.nombreTemporal = nombreTemporal;
		this.rutaTemporalArchivo = rutaTemporalArchivo;
	}

	/**
	 * Gets the usuario creacion.
	 *
	 * @return the usuario creacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * Sets the usuario creacion.
	 *
	 * @param usuarioCreacion the new usuario creacion
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * Gets the fecha creacion.
	 *
	 * @return the fecha creacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Sets the fecha creacion.
	 *
	 * @param fechaCreacion the new fecha creacion
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Gets the usuario modificacion.
	 *
	 * @return the usuario modificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * Sets the usuario modificacion.
	 *
	 * @param usuarioModificacion the new usuario modificacion
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * Gets the fecha modificacion.
	 *
	 * @return the fecha modificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Sets the fecha modificacion.
	 *
	 * @param fechaModificacion the new fecha modificacion
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Gets the nombre programa.
	 *
	 * @return the nombre programa
	 */
	public String getNombrePrograma() {
		return nombrePrograma;
	}

	/**
	 * Sets the nombre programa.
	 *
	 * @param nombrePrograma the new nombre programa
	 */
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * Gets the numero archivo.
	 *
	 * @return the numero archivo
	 */
	public Long getNumeroArchivo() {
		return numeroArchivo;
	}

	/**
	 * Sets the numero archivo.
	 *
	 * @param numeroArchivo the new numero archivo
	 */
	public void setNumeroArchivo(Long numeroArchivo) {
		this.numeroArchivo = numeroArchivo;
	}

	/**
	 * Gets the codigo tipo documento.
	 *
	 * @return the codigo tipo documento
	 */
	public Integer getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * Sets the codigo tipo documento.
	 *
	 * @param codigoTipoDocumento the new codigo tipo documento
	 */
	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * Gets the codigo documento.
	 *
	 * @return the codigo documento
	 */
	public Integer getCodigoDocumento() {
		return codigoDocumento;
	}

	/**
	 * Sets the codigo documento.
	 *
	 * @param codigoDocumento the new codigo documento
	 */
	public void setCodigoDocumento(Integer codigoDocumento) {
		this.codigoDocumento = codigoDocumento;
	}

	/**
	 * Gets the extension archivo.
	 *
	 * @return the extension archivo
	 */
	public String getExtensionArchivo() {
		return extensionArchivo;
	}

	/**
	 * Sets the extension archivo.
	 *
	 * @param extensionArchivo the new extension archivo
	 */
	public void setExtensionArchivo(String extensionArchivo) {
		this.extensionArchivo = extensionArchivo;
	}

	/**
	 * Gets the codigo seccion.
	 *
	 * @return the codigo seccion
	 */
	public Integer getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * Sets the codigo seccion.
	 *
	 * @param codigoSeccion the new codigo seccion
	 */
	public void setCodigoSeccion(Integer codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * Gets the nombre archivo original.
	 *
	 * @return the nombre archivo original
	 */
	public String getNombreArchivoOriginal() {
		return nombreArchivoOriginal;
	}

	/**
	 * Sets the nombre archivo original.
	 *
	 * @param nombreArchivoOriginal the new nombre archivo original
	 */
	public void setNombreArchivoOriginal(String nombreArchivoOriginal) {
		this.nombreArchivoOriginal = nombreArchivoOriginal;
	}

	/**
	 * Gets the tamanho kilobytes.
	 *
	 * @return the tamanho kilobytes
	 */
	public Long getTamanhoKilobytes() {
		return tamanhoKilobytes;
	}

	/**
	 * Sets the tamanho kilobytes.
	 *
	 * @param tamanhoKilobytes the new tamanho kilobytes
	 */
	public void setTamanhoKilobytes(Long tamanhoKilobytes) {
		this.tamanhoKilobytes = tamanhoKilobytes;
	}

	/**
	 * Gets the ruta archivo.
	 *
	 * @return the ruta archivo
	 */
	public String getRutaArchivo() {
		return rutaArchivo;
	}

	/**
	 * Sets the ruta archivo.
	 *
	 * @param rutaArchivo the new ruta archivo
	 */
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	/**
	 * Gets the tipo archivo.
	 *
	 * @return the tipo archivo
	 */
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	/**
	 * Sets the tipo archivo.
	 *
	 * @param tipoArchivo the new tipo archivo
	 */
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	/**
	 * Gets the nombre sin extension.
	 *
	 * @return the nombre sin extension
	 */
	public String getNombreSinExtension() {
		return nombreSinExtension;
	}

	/**
	 * Sets the nombre sin extension.
	 *
	 * @param nombreSinExtension the new nombre sin extension
	 */
	public void setNombreSinExtension(String nombreSinExtension) {
		this.nombreSinExtension = nombreSinExtension;
	}

	/**
	 * Gets the nombre temporal.
	 *
	 * @return the nombre temporal
	 */
	public String getNombreTemporal() {
		return nombreTemporal;
	}

	/**
	 * Sets the nombre temporal.
	 *
	 * @param nombreTemporal the new nombre temporal
	 */
	public void setNombreTemporal(String nombreTemporal) {
		this.nombreTemporal = nombreTemporal;
	}

	/**
	 * Gets the ruta temporal archivo.
	 *
	 * @return the ruta temporal archivo
	 */
	public String getRutaTemporalArchivo() {
		return rutaTemporalArchivo;
	}

	/**
	 * Sets the ruta temporal archivo.
	 *
	 * @param rutaTemporalArchivo the new ruta temporal archivo
	 */
	public void setRutaTemporalArchivo(String rutaTemporalArchivo) {
		this.rutaTemporalArchivo = rutaTemporalArchivo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean esIgual = false;
		String strNombreOtroArchivo = ((CommArchivoAdjuntoBean) obj).getNombreArchivoOriginal();
		Integer intCodSeccionOtroArchivo = ((CommArchivoAdjuntoBean) obj).getCodigoSeccion();
		if ((this.getNombreArchivoOriginal() != null)
				&& (this.getNombreArchivoOriginal().equalsIgnoreCase(strNombreOtroArchivo))
				&& (this.getCodigoSeccion() != null)
				&& (this.getCodigoSeccion().intValue() == intCodSeccionOtroArchivo.intValue())) {
			esIgual = true;
		}
		return esIgual;
	}

}
