package pe.com.sedapal.scr.correo.core.beans;

public class ServidorArchivos {
	private String strUrl;
	private String strUsuario;
	private String strClave;
	private Integer intRutaDefault;
	
	public ServidorArchivos() {}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	public String getStrUsuario() {
		return strUsuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	public String getStrClave() {
		return strClave;
	}

	public void setStrClave(String strClave) {
		this.strClave = strClave;
	}

	public Integer getIntRutaDefault() {
		return intRutaDefault;
	}

	public void setIntRutaDefault(Integer intRutaDefault) {
		this.intRutaDefault = intRutaDefault;
	}
}
