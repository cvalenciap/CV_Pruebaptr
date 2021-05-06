package pe.com.sedapal.scr.correo.core.beans;

import pe.com.sedapal.scr.core.beans.BaseBean;

public class CaudalDetalle extends BaseBean {
	private Integer intCodigo;
	private Caudal caudal;
	private MedidaCaudalDia caudalDia;
	
	public CaudalDetalle() {}

	public Integer getIntCodigo() {
		return intCodigo;
	}

	public void setIntCodigo(Integer intCodigo) {
		this.intCodigo = intCodigo;
	}

	public MedidaCaudalDia getCaudalDia() {
		return caudalDia;
	}

	public Caudal getCaudal() {
		return caudal;
	}

	public void setCaudal(Caudal caudal) {
		this.caudal = caudal;
	}

	public void setCaudalDia(MedidaCaudalDia caudalDia) {
		this.caudalDia = caudalDia;
	}
	
	@Override
	public String toString() {
		return caudalDia.toString();
	}
}
