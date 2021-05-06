/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class ReporteAnalisisHidroDetalle.
 */
public class ReporteAnalisisHidroDetalle  extends BaseBean implements Serializable {	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant VALUE_DEFAULT. */
	private static final double VALUE_DEFAULT= 0D;
	
	/** The correlativo. */
	//DATOS DE MAESTRO
	private Integer correlativo;
	
	/** The fecha reporte. */
	private String fechaReporte;
	
	/** The fecha muestreo. */
	private String fechaMuestreo;
	
	/** The fecha muestreo date. */
	private Date fechaMuestreoDate;
	
	/** The persona muestra. */
	private Integer personaMuestra;
	
	/** The analista. */
	private Integer analista;
	
	
	/** The codsub. */
	private Integer codsub;
	
	/** The algas. */
	private Double algas = VALUE_DEFAULT;
	
	/** The cianobacterias. */
	private Double cianobacterias = VALUE_DEFAULT;
	
	/** The nematodes. */
	private Double nematodes = VALUE_DEFAULT;
	
	/** The otros. */
	private Double otros = VALUE_DEFAULT;
	
	/** The total. */
	private Double total = VALUE_DEFAULT;
	
	/** The minam. */
	private Double minam = VALUE_DEFAULT;
	
	/** The descripcion. */
	private String  descripcion;
	
	/** The hora muestreo. */
	private String  horaMuestreo;
	
	/** The value select punto. */
	private Integer valueSelectPunto;
	
	/**
	 * Gets the fecha muestreo date.
	 *
	 * @return the fechaMuestreoDate
	 */
	public Date getFechaMuestreoDate() {
		return fechaMuestreoDate;
	}
	
	/**
	 * Sets the fecha muestreo date.
	 *
	 * @param fechaMuestreoDate the fechaMuestreoDate to set
	 */
	public void setFechaMuestreoDate(Date fechaMuestreoDate) {
		this.fechaMuestreoDate = fechaMuestreoDate;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.fechaMuestreo = df.format(fechaMuestreoDate);
	}
	
	/**
	 * Gets the correlativo.
	 *
	 * @return the correlativo
	 */
	public Integer getCorrelativo() {
		return correlativo;
	}
	
	/**
	 * Sets the correlativo.
	 *
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}
	
	/**
	 * Gets the fecha reporte.
	 *
	 * @return the fechaReporte
	 */
	public String getFechaReporte() {
		return fechaReporte;
	}
	
	/**
	 * Sets the fecha reporte.
	 *
	 * @param fechaReporte the fechaReporte to set
	 */
	public void setFechaReporte(String fechaReporte) {
		this.fechaReporte = fechaReporte;
	}
	
	/**
	 * Gets the fecha muestreo.
	 *
	 * @return the fechaMuestreo
	 */
	public String getFechaMuestreo() {
		return fechaMuestreo;
	}
	
	/**
	 * Sets the fecha muestreo.
	 *
	 * @param fechaMuestreo the fechaMuestreo to set
	 */
	public void setFechaMuestreo(String fechaMuestreo) {
		this.fechaMuestreo = fechaMuestreo;
	}
	
	/**
	 * Gets the persona muestra.
	 *
	 * @return the personaMuestra
	 */
	public Integer getPersonaMuestra() {
		return personaMuestra;
	}
	
	/**
	 * Sets the persona muestra.
	 *
	 * @param personaMuestra the personaMuestra to set
	 */
	public void setPersonaMuestra(Integer personaMuestra) {
		this.personaMuestra = personaMuestra;
	}
	
	/**
	 * Gets the analista.
	 *
	 * @return the analista
	 */
	public Integer getAnalista() {
		return analista;
	}
	
	/**
	 * Sets the analista.
	 *
	 * @param analista the analista to set
	 */
	public void setAnalista(Integer analista) {
		this.analista = analista;
	}
	
	/**
	 * Gets the value select punto.
	 *
	 * @return the valueSelectPunto
	 */
	public Integer getValueSelectPunto() {
		return valueSelectPunto;
	}
	
	/**
	 * Sets the value select punto.
	 *
	 * @param valueSelectPunto the valueSelectPunto to set
	 */
	public void setValueSelectPunto(Integer valueSelectPunto) {
		this.valueSelectPunto = valueSelectPunto;
	}
	
	/**
	 * Gets the codsub.
	 *
	 * @return the codsub
	 */
	public Integer getCodsub() {
		return codsub;
	}
	
	/**
	 * Sets the codsub.
	 *
	 * @param codsub the codsub to set
	 */
	public void setCodsub(Integer codsub) {
		this.codsub = codsub;
	}
	
	/**
	 * Gets the algas.
	 *
	 * @return the algas
	 */
	public Double getAlgas() {
		return algas;
	}
	
	/**
	 * Sets the algas.
	 *
	 * @param algas the new algas
	 */
	public void setAlgas(Double algas) {
		this.algas = algas;
	}
	
	/**
	 * Gets the cianobacterias.
	 *
	 * @return the cianobacterias
	 */
	public Double getCianobacterias() {
		return cianobacterias;
	}
	
	/**
	 * Sets the cianobacterias.
	 *
	 * @param cianobacterias the new cianobacterias
	 */
	public void setCianobacterias(Double cianobacterias) {
		this.cianobacterias = cianobacterias;
	}
	
	/**
	 * Gets the nematodes.
	 *
	 * @return the nematodes
	 */
	public Double getNematodes() {
		return nematodes;
	}
	
	/**
	 * Sets the nematodes.
	 *
	 * @param nematodes the new nematodes
	 */
	public void setNematodes(Double nematodes) {
		this.nematodes = nematodes;
	}
	
	/**
	 * Gets the otros.
	 *
	 * @return the otros
	 */
	public Double getOtros() {
		return otros;
	}
	
	/**
	 * Sets the otros.
	 *
	 * @param otros the new otros
	 */
	public void setOtros(Double otros) {
		this.otros = otros;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	
	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * Gets the minam.
	 *
	 * @return the minam
	 */
	public Double getMinam() {
		return minam;
	}
	
	/**
	 * Sets the minam.
	 *
	 * @param minam the new minam
	 */
	public void setMinam(Double minam) {
		this.minam = minam;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the hora muestreo.
	 *
	 * @return the horaMuestreo
	 */
	public String getHoraMuestreo() {
		return horaMuestreo;
	}
	
	/**
	 * Sets the hora muestreo.
	 *
	 * @param horaMuestreo the horaMuestreo to set
	 */
	public void setHoraMuestreo(String horaMuestreo) {
		this.horaMuestreo = horaMuestreo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReporteAnalisisHidro [codsub=" + codsub + ", algas=" + algas + ", cianobacterias=" + cianobacterias
				+ ", nematodes=" + nematodes + ", otros=" + otros + ", total=" + total + ", minam=" + minam
				+ ", descripcion=" + descripcion + ", horaMuestreo=" + horaMuestreo + "]";
	}
}
