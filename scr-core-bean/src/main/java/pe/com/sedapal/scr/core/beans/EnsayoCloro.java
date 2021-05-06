/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EnsayoCloro.
 */
public class EnsayoCloro extends BaseBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private  Integer codigo;
	
	/** The fecha muestreo. */
	private  String fechaMuestreo;
	
	/** The hora muestreo. */
	private  String horaMuestreo;
	
	/** The fecha recepcion. */
	private  String fechaRecepcion;
	
	/** The hora recepcion. */
	private  String horaRecepcion;
	
	/** The fecha inicio ensayo. */
	private  String fechaInicioEnsayo;
	
	/** The hora inicio ensayo. */
	private  String horaInicioEnsayo;
	
	/** The plan muestreo. */
	private  String planMuestreo = "-";
	
	/** The metodo muestreo. */
	private  String metodoMuestreo;
	
	/** The analista. */
	private  Integer analista;
	
	/** The turno. */
	private  String turno;
	
	/** The muestreador. */
	private  Integer muestreador;
	
	/** The volumen muestra. */
	private  String volumenMuestra;
	
	/** The fecha reporte. */
	private  String fechaReporte;
	
	/** The tiempo contacto. */
	private  String tiempoContacto;
	
	/** The punto muestreo. */
	private Integer puntoMuestreo;
	
	/** The punto muestreo desc. */
	private String puntoMuestreoDesc;
	
	/** The ph. */
	private Double ph;
	
	/** The temperatura. */
	private String temperatura;
	
	
	
	/** The sodio 1. */
	private String sodio1;
	
	/** The sodio 2. */
	private String sodio2;
	
	/** The sodio 3. */
	private String sodio3;
	
	/** The sodio 4. */
	private String sodio4;
	
	/** The cloro 1. */
	private String cloro1;
	
	/** The cloro 2. */
	private String cloro2;
	
	/** The cloro 3. */
	private String cloro3;
	
	/** The cloro 4. */
	private String cloro4;
	
	/** The bicromato 1. */
	//nuebos atributos
	private String bicromato1;
	
	/** The bicromato 2. */
	private String bicromato2;
	
	/** The bicromato 3. */
	private String bicromato3;
	
	/** The solucion 1. */
	private String solucion1;
	
	/** The solucion 2. */
	private String solucion2;
	
	/** The solucion 3. */
	private String solucion3;
	
	/** The observacion. */
	private String observacion;
	
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
	 * Gets the bicromato 1.
	 *
	 * @return the bicromato 1
	 */
	public String getBicromato1() {
		return bicromato1;
	}

	/**
	 * Sets the bicromato 1.
	 *
	 * @param bicromato1 the new bicromato 1
	 */
	public void setBicromato1(String bicromato1) {
		this.bicromato1 = bicromato1;
	}

	/**
	 * Gets the bicromato 2.
	 *
	 * @return the bicromato 2
	 */
	public String getBicromato2() {
		return bicromato2;
	}

	/**
	 * Sets the bicromato 2.
	 *
	 * @param bicromato2 the new bicromato 2
	 */
	public void setBicromato2(String bicromato2) {
		this.bicromato2 = bicromato2;
	}

	/**
	 * Gets the bicromato 3.
	 *
	 * @return the bicromato 3
	 */
	public String getBicromato3() {
		return bicromato3;
	}

	/**
	 * Sets the bicromato 3.
	 *
	 * @param bicromato3 the new bicromato 3
	 */
	public void setBicromato3(String bicromato3) {
		this.bicromato3 = bicromato3;
	}

	/**
	 * Gets the solucion 1.
	 *
	 * @return the solucion 1
	 */
	public String getSolucion1() {
		return solucion1;
	}

	/**
	 * Sets the solucion 1.
	 *
	 * @param solucion1 the new solucion 1
	 */
	public void setSolucion1(String solucion1) {
		this.solucion1 = solucion1;
	}

	/**
	 * Gets the solucion 2.
	 *
	 * @return the solucion 2
	 */
	public String getSolucion2() {
		return solucion2;
	}

	/**
	 * Sets the solucion 2.
	 *
	 * @param solucion2 the new solucion 2
	 */
	public void setSolucion2(String solucion2) {
		this.solucion2 = solucion2;
	}

	/**
	 * Gets the solucion 3.
	 *
	 * @return the solucion 3
	 */
	public String getSolucion3() {
		return solucion3;
	}

	/**
	 * Sets the solucion 3.
	 *
	 * @param solucion3 the new solucion 3
	 */
	public void setSolucion3(String solucion3) {
		this.solucion3 = solucion3;
	}


	/** The detalles. */
	private List<EnsayoCloroDetalle> detalles;
	
	/** The detalles 2. */
	private List<EnsayoCloroDetalle> detalles2;
	
	/** The detalles 3. */
	private List<EnsayoCloroDetalle> detalles3;

	/**
	 * Adds the detalle 1.
	 *
	 * @param detalle the detalle
	 */
	public void addDetalle1(EnsayoCloroDetalle detalle){
		if(detalles==null){
			detalles = new ArrayList<>();
		}
		this.detalles.add(detalle);
	}
	
	/**
	 * Adds the detalle 2.
	 *
	 * @param detalle the detalle
	 */
	public void addDetalle2(EnsayoCloroDetalle detalle){
		if(detalles2==null){
			detalles2 = new ArrayList<>();
		}
		this.detalles2.add(detalle);
	}
	
	/**
	 * Adds the detalle 3.
	 *
	 * @param detalle the detalle
	 */
	public void addDetalle3(EnsayoCloroDetalle detalle){
		if(detalles3==null){
			detalles3 = new ArrayList<>();
		}
		this.detalles3.add(detalle);
	}
	
	/**
	 * Gets the detalles 2.
	 *
	 * @return the detalles 2
	 */
	public List<EnsayoCloroDetalle> getDetalles2() {
		return detalles2;
	}

	/**
	 * Sets the detalles 2.
	 *
	 * @param detalles2 the new detalles 2
	 */
	public void setDetalles2(List<EnsayoCloroDetalle> detalles2) {
		this.detalles2 = detalles2;
	}

	/**
	 * Gets the detalles 3.
	 *
	 * @return the detalles 3
	 */
	public List<EnsayoCloroDetalle> getDetalles3() {
		return detalles3;
	}

	/**
	 * Sets the detalles 3.
	 *
	 * @param detalles3 the new detalles 3
	 */
	public void setDetalles3(List<EnsayoCloroDetalle> detalles3) {
		this.detalles3 = detalles3;
	}

	/**
	 * Gets the temperatura.
	 *
	 * @return the temperatura
	 */
	public String getTemperatura() {
		return temperatura;
	}


	/**
	 * Sets the temperatura.
	 *
	 * @param temperatura the new temperatura
	 */
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}


	/**
	 * Instantiates a new ensayo cloro.
	 */
	public EnsayoCloro() {
	}
	

	/**
	 * Instantiates a new ensayo cloro.
	 *
	 * @param ensayoCloro the ensayo cloro
	 */
	public EnsayoCloro(EnsayoCloro ensayoCloro) {
		this.codigo = ensayoCloro.codigo;
		this.fechaMuestreo = ensayoCloro.fechaMuestreo;
		this.horaMuestreo = ensayoCloro.horaMuestreo;
		this.fechaRecepcion = ensayoCloro.fechaRecepcion;
		this.horaRecepcion = ensayoCloro.horaRecepcion;
		this.fechaInicioEnsayo = ensayoCloro.fechaInicioEnsayo;
		this.horaInicioEnsayo = ensayoCloro.horaInicioEnsayo;
		this.planMuestreo = ensayoCloro.planMuestreo;
		this.metodoMuestreo = ensayoCloro.metodoMuestreo;
		this.analista = ensayoCloro.analista;
		this.turno = ensayoCloro.turno;
		this.muestreador = ensayoCloro.muestreador;
		this.volumenMuestra = ensayoCloro.volumenMuestra;
		this.fechaReporte = ensayoCloro.fechaReporte;
		this.tiempoContacto = ensayoCloro.tiempoContacto;
		this.puntoMuestreo = ensayoCloro.puntoMuestreo;
		this.puntoMuestreoDesc = ensayoCloro.puntoMuestreoDesc;
		this.ph = ensayoCloro.ph;
		this.temperatura = ensayoCloro.temperatura;
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
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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





	/**
	 * Gets the fecha recepcion.
	 *
	 * @return the fechaRecepcion
	 */
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}





	/**
	 * Sets the fecha recepcion.
	 *
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}





	/**
	 * Gets the hora recepcion.
	 *
	 * @return the horaRecepcion
	 */
	public String getHoraRecepcion() {
		return horaRecepcion;
	}





	/**
	 * Sets the hora recepcion.
	 *
	 * @param horaRecepcion the horaRecepcion to set
	 */
	public void setHoraRecepcion(String horaRecepcion) {
		this.horaRecepcion = horaRecepcion;
	}





	/**
	 * Gets the fecha inicio ensayo.
	 *
	 * @return the fechaInicioEnsayo
	 */
	public String getFechaInicioEnsayo() {
		return fechaInicioEnsayo;
	}





	/**
	 * Sets the fecha inicio ensayo.
	 *
	 * @param fechaInicioEnsayo the fechaInicioEnsayo to set
	 */
	public void setFechaInicioEnsayo(String fechaInicioEnsayo) {
		this.fechaInicioEnsayo = fechaInicioEnsayo;
	}





	/**
	 * Gets the hora inicio ensayo.
	 *
	 * @return the horaInicioEnsayo
	 */
	public String getHoraInicioEnsayo() {
		return horaInicioEnsayo;
	}





	/**
	 * Sets the hora inicio ensayo.
	 *
	 * @param horaInicioEnsayo the horaInicioEnsayo to set
	 */
	public void setHoraInicioEnsayo(String horaInicioEnsayo) {
		this.horaInicioEnsayo = horaInicioEnsayo;
	}





	/**
	 * Gets the plan muestreo.
	 *
	 * @return the planMuestreo
	 */
	public String getPlanMuestreo() {
		return planMuestreo;
	}





	/**
	 * Sets the plan muestreo.
	 *
	 * @param planMuestreo the planMuestreo to set
	 */
	public void setPlanMuestreo(String planMuestreo) {
		this.planMuestreo = planMuestreo;
	}





	/**
	 * Gets the metodo muestreo.
	 *
	 * @return the metodoMuestreo
	 */
	public String getMetodoMuestreo() {
		return metodoMuestreo;
	}





	/**
	 * Sets the metodo muestreo.
	 *
	 * @param metodoMuestreo the metodoMuestreo to set
	 */
	public void setMetodoMuestreo(String metodoMuestreo) {
		this.metodoMuestreo = metodoMuestreo;
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
	 * Gets the turno.
	 *
	 * @return the turno
	 */
	public String getTurno() {
		return turno;
	}


	/**
	 * Sets the turno.
	 *
	 * @param turno the turno to set
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}


	/**
	 * Gets the muestreador.
	 *
	 * @return the muestreador
	 */
	public Integer getMuestreador() {
		return muestreador;
	}


	/**
	 * Sets the muestreador.
	 *
	 * @param muestreador the muestreador to set
	 */
	public void setMuestreador(Integer muestreador) {
		this.muestreador = muestreador;
	}


	/**
	 * Gets the volumen muestra.
	 *
	 * @return the volumenMuestra
	 */
	public String getVolumenMuestra() {
		return volumenMuestra;
	}


	/**
	 * Sets the volumen muestra.
	 *
	 * @param volumenMuestra the volumenMuestra to set
	 */
	public void setVolumenMuestra(String volumenMuestra) {
		this.volumenMuestra = volumenMuestra;
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
	 * Gets the tiempo contacto.
	 *
	 * @return the tiempoContacto
	 */
	public String getTiempoContacto() {
		return tiempoContacto;
	}





	/**
	 * Sets the tiempo contacto.
	 *
	 * @param tiempoContacto the tiempoContacto to set
	 */
	public void setTiempoContacto(String tiempoContacto) {
		this.tiempoContacto = tiempoContacto;
	}





	/**
	 * Gets the detalles.
	 *
	 * @return the detalles
	 */
	public List<EnsayoCloroDetalle> getDetalles() {
		return detalles;
	}





	/**
	 * Sets the detalles.
	 *
	 * @param detalles the detalles to set
	 */
	public void setDetalles(List<EnsayoCloroDetalle> detalles) {
		this.detalles = detalles;
	}





	/**
	 * Gets the punto muestreo.
	 *
	 * @return the puntoMuestreo
	 */
	public Integer getPuntoMuestreo() {
		return puntoMuestreo;
	}




	/**
	 * Sets the punto muestreo.
	 *
	 * @param puntoMuestreo the puntoMuestreo to set
	 */
	public void setPuntoMuestreo(Integer puntoMuestreo) {
		this.puntoMuestreo = puntoMuestreo;
	}




	/**
	 * Gets the punto muestreo desc.
	 *
	 * @return the puntoMuestreoDesc
	 */
	public String getPuntoMuestreoDesc() {
		return puntoMuestreoDesc;
	}




	/**
	 * Sets the punto muestreo desc.
	 *
	 * @param puntoMuestreoDesc the puntoMuestreoDesc to set
	 */
	public void setPuntoMuestreoDesc(String puntoMuestreoDesc) {
		this.puntoMuestreoDesc = puntoMuestreoDesc;
	}




	/**
	 * Gets the ph.
	 *
	 * @return the ph
	 */
	public Double getPh() {
		return ph;
	}




	/**
	 * Sets the ph.
	 *
	 * @param ph the ph to set
	 */
	public void setPh(Double ph) {
		this.ph = ph;
	}

	/**
	 * Gets the sodio 1.
	 *
	 * @return the sodio1
	 */
	public String getSodio1() {
		return sodio1;
	}


	/**
	 * Sets the sodio 1.
	 *
	 * @param sodio1 the sodio1 to set
	 */
	public void setSodio1(String sodio1) {
		this.sodio1 = sodio1;
	}


	/**
	 * Gets the sodio 2.
	 *
	 * @return the sodio2
	 */
	public String getSodio2() {
		return sodio2;
	}


	/**
	 * Sets the sodio 2.
	 *
	 * @param sodio2 the sodio2 to set
	 */
	public void setSodio2(String sodio2) {
		this.sodio2 = sodio2;
	}


	/**
	 * Gets the sodio 3.
	 *
	 * @return the sodio3
	 */
	public String getSodio3() {
		return sodio3;
	}


	/**
	 * Sets the sodio 3.
	 *
	 * @param sodio3 the sodio3 to set
	 */
	public void setSodio3(String sodio3) {
		this.sodio3 = sodio3;
	}


	/**
	 * Gets the sodio 4.
	 *
	 * @return the sodio4
	 */
	public String getSodio4() {
		return sodio4;
	}


	/**
	 * Sets the sodio 4.
	 *
	 * @param sodio4 the sodio4 to set
	 */
	public void setSodio4(String sodio4) {
		this.sodio4 = sodio4;
	}


	/**
	 * Gets the cloro 1.
	 *
	 * @return the cloro1
	 */
	public String getCloro1() {
		return cloro1;
	}


	/**
	 * Sets the cloro 1.
	 *
	 * @param cloro1 the cloro1 to set
	 */
	public void setCloro1(String cloro1) {
		this.cloro1 = cloro1;
	}


	/**
	 * Gets the cloro 2.
	 *
	 * @return the cloro2
	 */
	public String getCloro2() {
		return cloro2;
	}


	/**
	 * Sets the cloro 2.
	 *
	 * @param cloro2 the cloro2 to set
	 */
	public void setCloro2(String cloro2) {
		this.cloro2 = cloro2;
	}


	/**
	 * Gets the cloro 3.
	 *
	 * @return the cloro3
	 */
	public String getCloro3() {
		return cloro3;
	}


	/**
	 * Sets the cloro 3.
	 *
	 * @param cloro3 the cloro3 to set
	 */
	public void setCloro3(String cloro3) {
		this.cloro3 = cloro3;
	}


	/**
	 * Gets the cloro 4.
	 *
	 * @return the cloro4
	 */
	public String getCloro4() {
		return cloro4;
	}


	/**
	 * Sets the cloro 4.
	 *
	 * @param cloro4 the cloro4 to set
	 */
	public void setCloro4(String cloro4) {
		this.cloro4 = cloro4;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EnsayoCloro [codigo=" + codigo + ", fechaMuestreo=" + fechaMuestreo + ", horaMuestreo=" + horaMuestreo
				+ ", fechaRecepcion=" + fechaRecepcion + ", horaRecepcion=" + horaRecepcion + ", fechaInicioEnsayo="
				+ fechaInicioEnsayo + ", horaInicioEnsayo=" + horaInicioEnsayo + ", planMuestreo=" + planMuestreo
				+ ", metodoMuestreo=" + metodoMuestreo + ", analista=" + analista + ", turno=" + turno
				+ ", muestreador=" + muestreador + ", volumenMuestra=" + volumenMuestra + ", fechaReporte="
				+ fechaReporte + ", tiempoContacto=" + tiempoContacto + ", puntoMuestreo=" + puntoMuestreo
				+ ", puntoMuestreoDesc=" + puntoMuestreoDesc + ", ph=" + ph + ", temperatura=" + temperatura
				+ ", sodio1=" + sodio1 + ", sodio2=" + sodio2 + ", sodio3=" + sodio3 + ", sodio4=" + sodio4
				+ ", cloro1=" + cloro1 + ", cloro2=" + cloro2 + ", cloro3=" + cloro3 + ", cloro4=" + cloro4
				+ ", bicromato1=" + bicromato1 + ", bicromato2=" + bicromato2 + ", bicromato3=" + bicromato3
				+ ", solucion1=" + solucion1 + ", solucion2=" + solucion2 + ", solucion3=" + solucion3
				+ ", observacion=" + observacion + ", detalles=" + detalles + ", detalles2=" + detalles2
				+ ", detalles3=" + detalles3 + "]";
	}

	

}
