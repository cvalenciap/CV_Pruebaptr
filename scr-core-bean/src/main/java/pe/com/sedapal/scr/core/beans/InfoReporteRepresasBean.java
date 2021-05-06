/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class InfoReporteRepresasBean.
 */
public class InfoReporteRepresasBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The int tipo columna. */
	// IDENTIFICADOR POR TIPO DE COLUMNA
	private Integer intTipoColumna;
	
	/** The str nombre principal columna. */
	// EN GENERAL
	private String strNombrePrincipalColumna;
	
	/** The str nombre principal columna java. */
	private String strNombrePrincipalColumnaJava;
	
	/** The int cod represa. */
	// PARA CADA REPRESA
	private Integer intCodRepresa;
	
	/** The int id represas. */
	private Integer intIdRepresas;
	
	/** The str cota. */
	private String strCota;
	
	/** The str volumen. */
	private String strVolumen;
	
	/** The str volumen total rep. */
	private String strVolumenTotalRep;
	
	/** The str descarga. */
	private String strDescarga;
	
	/** The str caudal trasvasado. */
	private String strCaudalTrasvasado;
	
	/** The str volumen trasvasado. */
	private String strVolumenTrasvasado;
	
	/** The str precipitacion. */
	private String strPrecipitacion;
	
	/** The str tiempo atmosferico. */
	private String strTiempoAtmosferico;
	
	/** The str formula volumen trasvasado. */
	private String strFormulaVolumenTrasvasado;
	
	/** The str represas almacenamiento. */
	// PARA ALMACENAMIENTO
	private String strRepresasAlmacenamiento;
	
	/** The str almacenamiento cantidad. */
	private String strAlmacenamientoCantidad;
	
	/** The str almacenamiento porcentaje. */
	private String strAlmacenamientoPorcentaje;
	
	/** The str total embalse cantidad. */
	private String strTotalEmbalseCantidad;
	
	/** The str total embalse porcentaje. */
	private String strTotalEmbalsePorcentaje;
	
	/** The str volumen total. */
	// PARA VOLUMEN TOTAL
	private String strVolumenTotal;
	
	/** The str aforo rio rimac. */
	// PARA AFORO RIO RIMAC
	private String strAforoRioRimac;
	
	/** The str caudal regulado. */
	// PARA SHEQUE TAMBORAQUE
	private String strCaudalRegulado;
	
	/** The str caudal descarga. */
	private String strCaudalDescarga;
	
	/** The str caudal natural. */
	private String strCaudalNatural;
	
	/** The str represas excluidas. */
	// PARA COLUMNA DE AGREGADOS
	private String strRepresasExcluidas;
	
	/** The str columna uno. */
	private String strColumnaUno;
	
	/** The str columna dos. */
	private String strColumnaDos;
	
	/** The bol exist data represa. */
	// SI EXISTE DATO PARA LA REPRESA
	private boolean bolExistDataRepresa; // SI ES QUE HAY AL MENOS ALGUN DATO PARA EL DIA;
	
	/**
	 * Gets the int cod represa.
	 *
	 * @return the int cod represa
	 */
	public Integer getIntCodRepresa() {
		return intCodRepresa;
	}
	
	/**
	 * Sets the int cod represa.
	 *
	 * @param intCodRepresa the new int cod represa
	 */
	public void setIntCodRepresa(Integer intCodRepresa) {
		this.intCodRepresa = intCodRepresa;
	}
	
	/**
	 * Gets the str nombre principal columna.
	 *
	 * @return the str nombre principal columna
	 */
	public String getStrNombrePrincipalColumna() {
		return strNombrePrincipalColumna;
	}
	
	/**
	 * Sets the str nombre principal columna.
	 *
	 * @param strNombrePrincipalColumna the new str nombre principal columna
	 */
	public void setStrNombrePrincipalColumna(String strNombrePrincipalColumna) {
		this.strNombrePrincipalColumna = strNombrePrincipalColumna;
	}
	
	/**
	 * Gets the str cota.
	 *
	 * @return the str cota
	 */
	public String getStrCota() {
		return strCota;
	}
	
	/**
	 * Sets the str cota.
	 *
	 * @param strCota the new str cota
	 */
	public void setStrCota(String strCota) {
		this.strCota = strCota;
	}
	
	/**
	 * Gets the str volumen.
	 *
	 * @return the str volumen
	 */
	public String getStrVolumen() {
		return strVolumen;
	}
	
	/**
	 * Sets the str volumen.
	 *
	 * @param strVolumen the new str volumen
	 */
	public void setStrVolumen(String strVolumen) {
		this.strVolumen = strVolumen;
	}
	
	/**
	 * Gets the str descarga.
	 *
	 * @return the str descarga
	 */
	public String getStrDescarga() {
		return strDescarga;
	}
	
	/**
	 * Sets the str descarga.
	 *
	 * @param strDescarga the new str descarga
	 */
	public void setStrDescarga(String strDescarga) {
		this.strDescarga = strDescarga;
	}
	
	/**
	 * Gets the str almacenamiento cantidad.
	 *
	 * @return the str almacenamiento cantidad
	 */
	public String getStrAlmacenamientoCantidad() {
		return strAlmacenamientoCantidad;
	}
	
	/**
	 * Sets the str almacenamiento cantidad.
	 *
	 * @param strAlmacenamientoCantidad the new str almacenamiento cantidad
	 */
	public void setStrAlmacenamientoCantidad(String strAlmacenamientoCantidad) {
		this.strAlmacenamientoCantidad = strAlmacenamientoCantidad;
	}
	
	/**
	 * Gets the str almacenamiento porcentaje.
	 *
	 * @return the str almacenamiento porcentaje
	 */
	public String getStrAlmacenamientoPorcentaje() {
		return strAlmacenamientoPorcentaje;
	}
	
	/**
	 * Sets the str almacenamiento porcentaje.
	 *
	 * @param strAlmacenamientoPorcentaje the new str almacenamiento porcentaje
	 */
	public void setStrAlmacenamientoPorcentaje(String strAlmacenamientoPorcentaje) {
		this.strAlmacenamientoPorcentaje = strAlmacenamientoPorcentaje;
	}
	
	/**
	 * Gets the str represas almacenamiento.
	 *
	 * @return the str represas almacenamiento
	 */
	public String getStrRepresasAlmacenamiento() {
		return strRepresasAlmacenamiento;
	}
	
	/**
	 * Sets the str represas almacenamiento.
	 *
	 * @param strRepresasAlmacenamiento the new str represas almacenamiento
	 */
	public void setStrRepresasAlmacenamiento(String strRepresasAlmacenamiento) {
		this.strRepresasAlmacenamiento = strRepresasAlmacenamiento;
	}
	
	/**
	 * Gets the str precipitacion.
	 *
	 * @return the str precipitacion
	 */
	public String getStrPrecipitacion() {
		return strPrecipitacion;
	}
	
	/**
	 * Sets the str precipitacion.
	 *
	 * @param strPrecipitacion the new str precipitacion
	 */
	public void setStrPrecipitacion(String strPrecipitacion) {
		this.strPrecipitacion = strPrecipitacion;
	}
	
	/**
	 * Gets the str tiempo atmosferico.
	 *
	 * @return the str tiempo atmosferico
	 */
	public String getStrTiempoAtmosferico() {
		return strTiempoAtmosferico;
	}
	
	/**
	 * Sets the str tiempo atmosferico.
	 *
	 * @param strTiempoAtmosferico the new str tiempo atmosferico
	 */
	public void setStrTiempoAtmosferico(String strTiempoAtmosferico) {
		this.strTiempoAtmosferico = strTiempoAtmosferico;
	}
	
	/**
	 * Gets the str total embalse cantidad.
	 *
	 * @return the str total embalse cantidad
	 */
	public String getStrTotalEmbalseCantidad() {
		return strTotalEmbalseCantidad;
	}
	
	/**
	 * Sets the str total embalse cantidad.
	 *
	 * @param strTotalEmbalseCantidad the new str total embalse cantidad
	 */
	public void setStrTotalEmbalseCantidad(String strTotalEmbalseCantidad) {
		this.strTotalEmbalseCantidad = strTotalEmbalseCantidad;
	}
	
	/**
	 * Gets the str total embalse porcentaje.
	 *
	 * @return the str total embalse porcentaje
	 */
	public String getStrTotalEmbalsePorcentaje() {
		return strTotalEmbalsePorcentaje;
	}
	
	/**
	 * Sets the str total embalse porcentaje.
	 *
	 * @param strTotalEmbalsePorcentaje the new str total embalse porcentaje
	 */
	public void setStrTotalEmbalsePorcentaje(String strTotalEmbalsePorcentaje) {
		this.strTotalEmbalsePorcentaje = strTotalEmbalsePorcentaje;
	}
	
	/**
	 * Gets the int tipo columna.
	 *
	 * @return the int tipo columna
	 */
	public Integer getIntTipoColumna() {
		return intTipoColumna;
	}
	
	/**
	 * Sets the int tipo columna.
	 *
	 * @param intTipoColumna the new int tipo columna
	 */
	public void setIntTipoColumna(Integer intTipoColumna) {
		this.intTipoColumna = intTipoColumna;
	}
	
	/**
	 * Gets the str volumen total.
	 *
	 * @return the str volumen total
	 */
	public String getStrVolumenTotal() {
		return strVolumenTotal;
	}
	
	/**
	 * Sets the str volumen total.
	 *
	 * @param strVolumenTotal the new str volumen total
	 */
	public void setStrVolumenTotal(String strVolumenTotal) {
		this.strVolumenTotal = strVolumenTotal;
	}
	
	/**
	 * Gets the str aforo rio rimac.
	 *
	 * @return the str aforo rio rimac
	 */
	public String getStrAforoRioRimac() {
		return strAforoRioRimac;
	}
	
	/**
	 * Sets the str aforo rio rimac.
	 *
	 * @param strAforoRioRimac the new str aforo rio rimac
	 */
	public void setStrAforoRioRimac(String strAforoRioRimac) {
		this.strAforoRioRimac = strAforoRioRimac;
	}
	
	/**
	 * Gets the str caudal regulado.
	 *
	 * @return the str caudal regulado
	 */
	public String getStrCaudalRegulado() {
		return strCaudalRegulado;
	}
	
	/**
	 * Sets the str caudal regulado.
	 *
	 * @param strCaudalRegulado the new str caudal regulado
	 */
	public void setStrCaudalRegulado(String strCaudalRegulado) {
		this.strCaudalRegulado = strCaudalRegulado;
	}
	
	/**
	 * Gets the str caudal descarga.
	 *
	 * @return the str caudal descarga
	 */
	public String getStrCaudalDescarga() {
		return strCaudalDescarga;
	}
	
	/**
	 * Sets the str caudal descarga.
	 *
	 * @param strCaudalDescarga the new str caudal descarga
	 */
	public void setStrCaudalDescarga(String strCaudalDescarga) {
		this.strCaudalDescarga = strCaudalDescarga;
	}
	
	/**
	 * Gets the str caudal natural.
	 *
	 * @return the str caudal natural
	 */
	public String getStrCaudalNatural() {
		return strCaudalNatural;
	}
	
	/**
	 * Sets the str caudal natural.
	 *
	 * @param strCaudalNatural the new str caudal natural
	 */
	public void setStrCaudalNatural(String strCaudalNatural) {
		this.strCaudalNatural = strCaudalNatural;
	}
	
	/**
	 * Gets the str caudal trasvasado.
	 *
	 * @return the str caudal trasvasado
	 */
	public String getStrCaudalTrasvasado() {
		return strCaudalTrasvasado;
	}
	
	/**
	 * Sets the str caudal trasvasado.
	 *
	 * @param strCaudalTrasvasado the new str caudal trasvasado
	 */
	public void setStrCaudalTrasvasado(String strCaudalTrasvasado) {
		this.strCaudalTrasvasado = strCaudalTrasvasado;
	}
	
	/**
	 * Gets the str volumen trasvasado.
	 *
	 * @return the str volumen trasvasado
	 */
	public String getStrVolumenTrasvasado() {
		return strVolumenTrasvasado;
	}
	
	/**
	 * Sets the str volumen trasvasado.
	 *
	 * @param strVolumenTrasvasado the new str volumen trasvasado
	 */
	public void setStrVolumenTrasvasado(String strVolumenTrasvasado) {
		this.strVolumenTrasvasado = strVolumenTrasvasado;
	}
	
	/**
	 * Gets the str formula volumen trasvasado.
	 *
	 * @return the str formula volumen trasvasado
	 */
	public String getStrFormulaVolumenTrasvasado() {
		return strFormulaVolumenTrasvasado;
	}
	
	/**
	 * Sets the str formula volumen trasvasado.
	 *
	 * @param strFormulaVolumenTrasvasado the new str formula volumen trasvasado
	 */
	public void setStrFormulaVolumenTrasvasado(String strFormulaVolumenTrasvasado) {
		this.strFormulaVolumenTrasvasado = strFormulaVolumenTrasvasado;
	}
	
	/**
	 * Gets the int id represas.
	 *
	 * @return the int id represas
	 */
	public Integer getIntIdRepresas() {
		return intIdRepresas;
	}
	
	/**
	 * Sets the int id represas.
	 *
	 * @param intIdRepresas the new int id represas
	 */
	public void setIntIdRepresas(Integer intIdRepresas) {
		this.intIdRepresas = intIdRepresas;
	}
	
	/**
	 * Checks if is bol exist data represa.
	 *
	 * @return true, if is bol exist data represa
	 */
	public boolean isBolExistDataRepresa() {
		return bolExistDataRepresa;
	}
	
	/**
	 * Sets the bol exist data represa.
	 *
	 * @param bolExistDataRepresa the new bol exist data represa
	 */
	public void setBolExistDataRepresa(boolean bolExistDataRepresa) {
		this.bolExistDataRepresa = bolExistDataRepresa;
	}
	
	/**
	 * Gets the str columna uno.
	 *
	 * @return the str columna uno
	 */
	public String getStrColumnaUno() {
		return strColumnaUno;
	}
	
	/**
	 * Sets the str columna uno.
	 *
	 * @param strColumnaUno the new str columna uno
	 */
	public void setStrColumnaUno(String strColumnaUno) {
		this.strColumnaUno = strColumnaUno;
	}
	
	/**
	 * Gets the str columna dos.
	 *
	 * @return the str columna dos
	 */
	public String getStrColumnaDos() {
		return strColumnaDos;
	}
	
	/**
	 * Sets the str columna dos.
	 *
	 * @param strColumnaDos the new str columna dos
	 */
	public void setStrColumnaDos(String strColumnaDos) {
		this.strColumnaDos = strColumnaDos;
	}
	
	/**
	 * Gets the str represas excluidas.
	 *
	 * @return the str represas excluidas
	 */
	public String getStrRepresasExcluidas() {
		return strRepresasExcluidas;
	}
	
	/**
	 * Sets the str represas excluidas.
	 *
	 * @param strRepresasExcluidas the new str represas excluidas
	 */
	public void setStrRepresasExcluidas(String strRepresasExcluidas) {
		this.strRepresasExcluidas = strRepresasExcluidas;
	}
	
	/**
	 * Gets the str volumen total rep.
	 *
	 * @return the str volumen total rep
	 */
	public String getStrVolumenTotalRep() {
		return strVolumenTotalRep;
	}
	
	/**
	 * Sets the str volumen total rep.
	 *
	 * @param strVolumenTotalRep the new str volumen total rep
	 */
	public void setStrVolumenTotalRep(String strVolumenTotalRep) {
		this.strVolumenTotalRep = strVolumenTotalRep;
	}
	
	/**
	 * Gets the str nombre principal columna java.
	 *
	 * @return the str nombre principal columna java
	 */
	public String getStrNombrePrincipalColumnaJava() {
		return strNombrePrincipalColumnaJava;
	}
	
	/**
	 * Sets the str nombre principal columna java.
	 *
	 * @param strNombrePrincipalColumnaJava the new str nombre principal columna java
	 */
	public void setStrNombrePrincipalColumnaJava(String strNombrePrincipalColumnaJava) {
		this.strNombrePrincipalColumnaJava = strNombrePrincipalColumnaJava;
	}
}
