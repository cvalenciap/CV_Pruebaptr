package pe.com.sedapal.scr.web.util;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BPdf implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rutaJasper;
	private Map<String, Object> parametro;
	private List<?> listaDataSource;

	public String getRutaJasper() {
		return rutaJasper;
	}

	public void setRutaJasper(String rutaJasper) {
		this.rutaJasper = rutaJasper;
	}

	public Map<String, Object> getParametro() {
		return parametro;
	}

	public void setParametro(Map<String, Object> parametro) {
		this.parametro = parametro;
	}

	public List<?> getListaDataSource() {
		return listaDataSource;
	}

	public void setListaDataSource(List<?> listaDataSource) {
		this.listaDataSource = listaDataSource;
	}
}
