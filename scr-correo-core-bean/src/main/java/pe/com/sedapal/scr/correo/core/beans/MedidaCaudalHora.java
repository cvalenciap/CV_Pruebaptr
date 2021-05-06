package pe.com.sedapal.scr.correo.core.beans;

public class MedidaCaudalHora {
	private String strHora;
	private Double dblValor;
	
	public MedidaCaudalHora() {}

	public String getStrHora() {
		return strHora;
	}

	public void setStrHora(String strHora) {
		this.strHora = strHora;
	}

	public Double getDblValor() {
		return dblValor;
	}

	public void setDblValor(Double dblValor) {
		this.dblValor = dblValor;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if(!(o instanceof MedidaCaudalHora)) {
			return false;
		}
		
		MedidaCaudalHora caudalHora = (MedidaCaudalHora) o;
		
		return caudalHora.strHora.equals(strHora);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + strHora.hashCode();
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(strHora);
		sb.append(",");
		sb.append(dblValor);
		sb.append(")");
		sb.append("\t");
		
		return sb.toString();
	}
}
