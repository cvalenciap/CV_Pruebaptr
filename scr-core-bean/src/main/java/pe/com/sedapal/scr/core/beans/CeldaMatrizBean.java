/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class CeldaMatrizBean.
 */
public class CeldaMatrizBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant SIN_DATO. */
	private static final String SIN_DATO = "-1.0";
	
	/** The Constant CLASS_CSS_DEFAULT. */
	private static final String CLASS_CSS_DEFAULT = "celda-default";
	
	/** The Constant CLASS_CSS_SIN_DATO. */
	private static final String CLASS_CSS_SIN_DATO = "celda-sin-dato";
	
	/** The str data. */
	private String strData;
	
	/** The str class. */
	private String strClass;
	
	/**
	 * Instantiates a new celda matriz bean.
	 *
	 * @param strData the str data
	 * @param strClass the str class
	 */
	public CeldaMatrizBean(String strData, String strClass){
		this.strData = strData;
		this.strClass = strClass;
	}
	
	/**
	 * Instantiates a new celda matriz bean.
	 *
	 * @param dblData the dbl data
	 */
	public CeldaMatrizBean(double dblData){
		// para evitar la notacion exponencial PE: 12.3434E4
		if(dblData == Double.parseDouble(SIN_DATO)){ // Que no le quite el .0 
			this.strData = new BigDecimal(dblData + "").toPlainString();
		}
		else{
			this.strData = new BigDecimal(dblData + "").stripTrailingZeros().toPlainString();
		}
		
		if(strData.equals(String.valueOf(SIN_DATO))){
			this.strClass = CLASS_CSS_SIN_DATO;
		}
		else{
			this.strClass = CLASS_CSS_DEFAULT;
		}
	}
	
	/**
	 * Instantiates a new celda matriz bean.
	 *
	 * @param strData the str data
	 */
	public CeldaMatrizBean(String strData){
		this.strData = strData;
		if(strData.equals(String.valueOf(SIN_DATO))){
			this.strClass = CLASS_CSS_SIN_DATO;
		}
		else{
			this.strClass = CLASS_CSS_DEFAULT;
		}
		
	}
	
	/**
	 * Gets the str data.
	 *
	 * @return the str data
	 */
	public String getStrData() {
		return strData;
	}
	
	/**
	 * Sets the str data.
	 *
	 * @param strData the new str data
	 */
	public void setStrData(String strData) {
		this.strData = strData;
	}
	
	/**
	 * Gets the str class.
	 *
	 * @return the str class
	 */
	public String getStrClass() {
		return strClass;
	}
	
	/**
	 * Sets the str class.
	 *
	 * @param strClass the new str class
	 */
	public void setStrClass(String strClass) {
		this.strClass = strClass;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CeldaMatrizBean [strData=" + strData + ", strClass=" + strClass + "]";
	}
}
