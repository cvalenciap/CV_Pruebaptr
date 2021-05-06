package pe.com.sedapal.scr.correo.core.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.RioBean;

public class Caudal extends BaseBean {
	private Integer intCodigo;
	private RioBean rioBean;
	private String strTipoProceso;
	private String strRuta;
	private String strPeriodo;
	private Date dteFechaHoraRegistro;
	private String strEstado;
	private String strNombreArchivo;
	private Integer intCodigoCarga;
	private Date dteFechaHoraProceso;
	private List<CaudalDetalle> lstDetalles;
	
	public Caudal() {
		lstDetalles = new ArrayList<CaudalDetalle>();
	}

	public Integer getIntCodigo() {
		return intCodigo;
	}

	public void setIntCodigo(Integer intCodigo) {
		this.intCodigo = intCodigo;
	}

	public RioBean getRioBean() {
		return rioBean;
	}

	public void setRioBean(RioBean rioBean) {
		this.rioBean = rioBean;
	}

	public String getStrTipoProceso() {
		return strTipoProceso;
	}

	public void setStrTipoProceso(String strTipoProceso) {
		this.strTipoProceso = strTipoProceso;
	}

	public String getStrRuta() {
		return strRuta;
	}

	public void setStrRuta(String strRuta) {
		this.strRuta = strRuta;
	}

	public String getStrPeriodo() {
		return strPeriodo;
	}

	public void setStrPeriodo(String strPeriodo) {
		this.strPeriodo = strPeriodo;
	}

	public Date getDteFechaHoraRegistro() {
		return dteFechaHoraRegistro;
	}

	public void setDteFechaHoraRegistro(Date dteFechaHoraRegistro) {
		this.dteFechaHoraRegistro = dteFechaHoraRegistro;
	}

	public String getStrEstado() {
		return strEstado;
	}

	public void setStrEstado(String strEstado) {
		this.strEstado = strEstado;
	}

	public String getStrNombreArchivo() {
		return strNombreArchivo;
	}

	public void setStrNombreArchivo(String strNombreArchivo) {
		this.strNombreArchivo = strNombreArchivo;
	}

	public Integer getIntCodigoCarga() {
		return intCodigoCarga;
	}

	public void setIntCodigoCarga(Integer intCodigoCarga) {
		this.intCodigoCarga = intCodigoCarga;
	}

	public Date getDteFechaHoraProceso() {
		return dteFechaHoraProceso;
	}

	public void setDteFechaHoraProceso(Date dteFechaHoraProceso) {
		this.dteFechaHoraProceso = dteFechaHoraProceso;
	}

	public List<CaudalDetalle> getLstDetalles() {
		return lstDetalles;
	}

	public void setLstDetalles(List<CaudalDetalle> lstDetalles) {
		this.lstDetalles = lstDetalles;
	}
	
	public void addDetalle(final CaudalDetalle caudalDetalle) {
		this.lstDetalles.add(caudalDetalle);
	}
	
	public void setIntCodigoRio(int intCodigoRio) {
		RioBean rioBean = new RioBean();
		rioBean.setCodigo("" + intCodigoRio);
		
		this.rioBean = rioBean;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rioBean.getNombreLargo());
		sb.append("\n");
		
		for(CaudalDetalle detalle : lstDetalles) {
			sb.append(detalle.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
