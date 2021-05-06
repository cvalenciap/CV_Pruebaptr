/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

// TODO: Auto-generated Javadoc
/**
 * The Class FormularioHeader242.
 */
public class FormularioHeader242 extends BaseBean {

	/** The id header. */
	private Integer  idHeader;
	
	/** The str fecha muestreo. */
	private String  strFechaMuestreo;              
	
	/** The str fecha recepcion. */
	private String  strFechaRecepcion;   
	
	/** The persona muestrea. */
	private String  personaMuestrea;
	
	/** The persona recepcion. */
	private String  personaRecepcion;                   
	
	/** The observacion. */
	private String  observacion;
	
	/** The titulo header. */
	private String  tituloHeader;
	
	/** The estado. */
	private Integer estado;
	
	/** The page. */
	private String page;
	
	/** The codare. */
	private Integer codare;
	
	/** The transporte chofer. */
	private String  transporteChofer;
	
	/** The cumple epp. */
	private String cumpleEpp = "0";
	

	/**
	 * Gets the id header.
	 *
	 * @return the id header
	 */
	public Integer getIdHeader() {
		return idHeader;
	}

	/**
	 * Sets the id header.
	 *
	 * @param idHeader the new id header
	 */
	public void setIdHeader(Integer idHeader) {
		this.idHeader = idHeader;
	}

	/**
	 * Gets the str fecha muestreo.
	 *
	 * @return the str fecha muestreo
	 */
	public String getStrFechaMuestreo() {
		return strFechaMuestreo;
	}
	
	/**
	 * Sets the str fecha muestreo.
	 *
	 * @param strFechaMuestreo the new str fecha muestreo
	 */
	public void setStrFechaMuestreo(String strFechaMuestreo) {
		this.strFechaMuestreo = strFechaMuestreo;
	}
	
	/**
	 * Gets the str fecha recepcion.
	 *
	 * @return the str fecha recepcion
	 */
	public String getStrFechaRecepcion() {
		return strFechaRecepcion;
	}
	
	/**
	 * Sets the str fecha recepcion.
	 *
	 * @param strFechaRecepcion the new str fecha recepcion
	 */
	public void setStrFechaRecepcion(String strFechaRecepcion) {
		this.strFechaRecepcion = strFechaRecepcion;
	}
	
	/**
	 * Gets the persona muestrea.
	 *
	 * @return the persona muestrea
	 */
	public String getPersonaMuestrea() {
		return personaMuestrea;
	}

	/**
	 * Sets the persona muestrea.
	 *
	 * @param personaMuestrea the new persona muestrea
	 */
	public void setPersonaMuestrea(String personaMuestrea) {
		this.personaMuestrea = personaMuestrea;
	}

	/**
	 * Gets the persona recepcion.
	 *
	 * @return the persona recepcion
	 */
	public String getPersonaRecepcion() {
		return personaRecepcion;
	}
	
	/**
	 * Sets the persona recepcion.
	 *
	 * @param personaRecepcion the new persona recepcion
	 */
	public void setPersonaRecepcion(String personaRecepcion) {
		this.personaRecepcion = personaRecepcion;
	}
	
	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Sets the observacion.
	 *
	 * @param observacion the new observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Gets the titulo header.
	 *
	 * @return the titulo header
	 */
	public String getTituloHeader() {
		return tituloHeader;
	}

	/**
	 * Sets the titulo header.
	 *
	 * @param tituloHeader the new titulo header
	 */
	public void setTituloHeader(String tituloHeader) {
		this.tituloHeader = tituloHeader;
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
	 * Gets the page.
	 *
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * Gets the transporte chofer.
	 *
	 * @return the transporteChofer
	 */
	public String getTransporteChofer() {
		return transporteChofer;
	}

	/**
	 * Sets the transporte chofer.
	 *
	 * @param transporteChofer the transporteChofer to set
	 */
	public void setTransporteChofer(String transporteChofer) {
		this.transporteChofer = transporteChofer;
	}

	/**
	 * Gets the cumple epp.
	 *
	 * @return the cumpleEpp
	 */
	public String getCumpleEpp() {
		return cumpleEpp;
	}

	/**
	 * Sets the cumple epp.
	 *
	 * @param cumpleEpp the cumpleEpp to set
	 */
	public void setCumpleEpp(String cumpleEpp) {
		this.cumpleEpp = cumpleEpp;
	}

	/**
	 * Gets the codare.
	 *
	 * @return the codare
	 */
	public Integer getCodare() {
		return codare;
	}

	/**
	 * Sets the codare.
	 *
	 * @param codare the new codare
	 */
	public void setCodare(Integer codare) {
		this.codare = codare;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormularioHeader242 [idHeader=" + idHeader + ", strFechaMuestreo=" + strFechaMuestreo
				+ ", strFechaRecepcion=" + strFechaRecepcion + ", personaMuestrea=" + personaMuestrea
				+ ", personaRecepcion=" + personaRecepcion + ", observacion=" + observacion + ", tituloHeader="
				+ tituloHeader + ", estado=" + estado + ", page=" + page + ", transporteChofer=" + transporteChofer
				+ ", cumpleEpp=" + cumpleEpp + "]";
	}
	
}
