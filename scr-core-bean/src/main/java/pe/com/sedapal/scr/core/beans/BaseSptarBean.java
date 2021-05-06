package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class BaseSptarBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stRegi;
	private String idUsuaCrea;
	private Timestamp feUsuaCrea;
	private String deTermCrea;
	private String idUsuaModi;
	private Timestamp feUsuaModi;
	private String deTermModi;
	
	
	public String getStRegi() {
		return stRegi;
	}
	public void setStRegi(String stRegi) {
		this.stRegi = stRegi;
	}
	public String getIdUsuaCrea() {
		return idUsuaCrea;
	}
	public void setIdUsuaCrea(String idUsuaCrea) {
		this.idUsuaCrea = idUsuaCrea;
	}
	public Timestamp getFeUsuaCrea() {
		return feUsuaCrea;
	}
	public void setFeUsuaCrea(Timestamp feUsuaCrea) {
		this.feUsuaCrea = feUsuaCrea;
	}
	public String getDeTermCrea() {
		return deTermCrea;
	}
	public void setDeTermCrea(String deTermCrea) {
		this.deTermCrea = deTermCrea;
	}
	public String getIdUsuaModi() {
		return idUsuaModi;
	}
	public void setIdUsuaModi(String idUsuaModi) {
		this.idUsuaModi = idUsuaModi;
	}
	public Timestamp getFeUsuaModi() {
		return feUsuaModi;
	}
	public void setFeUsuaModi(Timestamp feUsuaModi) {
		this.feUsuaModi = feUsuaModi;
	}
	public String getDeTermModi() {
		return deTermModi;
	}
	public void setDeTermModi(String deTermModi) {
		this.deTermModi = deTermModi;
	}
	
	
}