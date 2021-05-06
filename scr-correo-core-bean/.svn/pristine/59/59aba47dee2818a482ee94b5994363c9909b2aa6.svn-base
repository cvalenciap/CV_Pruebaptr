package pe.com.sedapal.scr.correo.core.beans;

public class CuentaCorreo {
	private String strCorreo;
	private String strClave;
	private String strUsuario;
	
	public CuentaCorreo() {}
	
	public String getStrCorreo() {
		return strCorreo;
	}

	public void setStrCorreo(String strCorreo) {
		this.strCorreo = strCorreo;
	}

	public String getStrClave() {
		return strClave;
	}

	public void setStrClave(String strClave) {
		this.strClave = strClave;
	}

	public String getStrUsuario() {
		if(strCorreo != null) {
			strUsuario = strCorreo.substring(0, strCorreo.indexOf("@"));
		}
		
		return strUsuario;
	}
}
