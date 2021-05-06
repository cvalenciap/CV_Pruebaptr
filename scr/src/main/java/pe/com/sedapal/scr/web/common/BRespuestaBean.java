package pe.com.sedapal.scr.web.common;

import java.io.Serializable;
import java.util.Map;

public class BRespuestaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String estadoRespuesta;
	private String mensajeRespuesta;
	private Map<String, Object> parametros;
	private Object beanGenerico;

	public BRespuestaBean() {
		super();
	}

	public BRespuestaBean(String estadoRespuesta, String mensajeRespuesta) {
		super();
		this.estadoRespuesta = estadoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Object getBeanGenerico() {
		return beanGenerico;
	}

	public void setBeanGenerico(Object beanGenerico) {
		this.beanGenerico = beanGenerico;
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
