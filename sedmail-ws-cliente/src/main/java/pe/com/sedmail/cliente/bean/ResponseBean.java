package pe.com.sedmail.cliente.bean;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String estadoRespuesta;
	private String mensajeRespuesta;
	private Map<String, Object> parametros;
	
	public ResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ResponseBean(String estadoRespuesta, String mensajeRespuesta, Map<String, Object> parametros) {
		super();
		this.estadoRespuesta = estadoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
		this.parametros = parametros;
	}
	
	@Override
	public String toString() {
		return "ResponseBean [estadoRespuesta=" + estadoRespuesta + ", mensajeRespuesta=" + mensajeRespuesta + ", parametros=" + parametros + "]";
	}
	
	public String getEstadoRespuesta() {
		return estadoRespuesta;
	}

	public void setEstadoRespuesta(String estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
	
}
