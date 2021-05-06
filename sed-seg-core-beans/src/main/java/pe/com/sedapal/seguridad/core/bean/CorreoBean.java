package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;

public class CorreoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4342205461229801852L;
	public String vFrom;
	public String vTo;
	public String vSubject;
	public StringBuilder vMessage;
	public String vCcto;
	public String vBccto;

	public CorreoBean(String vFrom, String vTo, String vSubject, StringBuilder vMessage, String vCcto, String vBccto) {
		super();
		this.vFrom = vFrom;
		this.vTo = vTo;
		this.vSubject = vSubject;
		this.vMessage = vMessage;
		this.vCcto = vCcto;
		this.vBccto = vBccto;
	}

	public String getvFrom() {
		return vFrom;
	}

	public void setvFrom(String vFrom) {
		this.vFrom = vFrom;
	}

	public String getvTo() {
		return vTo;
	}

	public void setvTo(String vTo) {
		this.vTo = vTo;
	}

	public String getvSubject() {
		return vSubject;
	}

	public void setvSubject(String vSubject) {
		this.vSubject = vSubject;
	}

	public StringBuilder getvMessage() {
		return vMessage;
	}

	public void setvMessage(StringBuilder vMessage) {
		this.vMessage = vMessage;
	}

	public String getvCcto() {
		return vCcto;
	}

	public void setvCcto(String vCcto) {
		this.vCcto = vCcto;
	}

	public String getvBccto() {
		return vBccto;
	}

	public void setvBccto(String vBccto) {
		this.vBccto = vBccto;
	}

}
