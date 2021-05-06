package pe.com.sedapal.scr.correo.core.beans;

import java.util.ArrayList;
import java.util.List;

public class MedidaCaudalDia {
	private String strDia;
	private List<MedidaCaudalHora> lstCaudalesDia;
	private Double dblCaudalPromedioDia;
	
	public MedidaCaudalDia() {
		lstCaudalesDia = new ArrayList<MedidaCaudalHora>();
	}

	public String getStrDia() {
		return strDia;
	}

	public void setStrDia(String strDia) {
		this.strDia = strDia;
	}

	public List<MedidaCaudalHora> getLstCaudalesDia() {
		return lstCaudalesDia;
	}

	public void setLstCaudalesDia(List<MedidaCaudalHora> lstCaudalesDia) {
		this.lstCaudalesDia = lstCaudalesDia;
	}

	public Double getDblCaudalPromedioDia() {
		dblCaudalPromedioDia = 0.0;
		
		int contadorDatos = 0;
		
		for(MedidaCaudalHora medidaCaudalHora : lstCaudalesDia) {
			if(medidaCaudalHora.getDblValor() != null) {
				dblCaudalPromedioDia = dblCaudalPromedioDia + medidaCaudalHora.getDblValor();
				contadorDatos++;
			}
		}
		
		dblCaudalPromedioDia = dblCaudalPromedioDia / contadorDatos;
		
		dblCaudalPromedioDia = Math.round(dblCaudalPromedioDia * 10000)/10000D;
		
		return dblCaudalPromedioDia;
	}

	public void setDblCaudalPromedioDia(Double dblCaudalPromedioDia) {
		this.dblCaudalPromedioDia = dblCaudalPromedioDia;
	}
	
	public void addCaudalHora(final MedidaCaudalHora medidaCaudalHora) {
		if(lstCaudalesDia.size() < 10) {
			medidaCaudalHora.setStrHora("0" + (lstCaudalesDia.size() + 1));//01, 02, 03, 04, 05, ..., 09
		} else {
			medidaCaudalHora.setStrHora("" + (lstCaudalesDia.size() + 1));//10, 11, 12, 13, ..., 24
		}
		
		lstCaudalesDia.add(medidaCaudalHora);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		
		if(!(o instanceof MedidaCaudalDia)) {
			return false;
		}
		
		MedidaCaudalDia caudalDia = (MedidaCaudalDia) o;
		
		return caudalDia.strDia.equals(strDia);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		
		result = 31 * result + strDia.hashCode();
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(strDia);
		sb.append(":");
		
		for(MedidaCaudalHora medidaCaudalHora : lstCaudalesDia) {
			sb.append(medidaCaudalHora.toString());
		}
		
		sb.append(getDblCaudalPromedioDia());
		
		return sb.toString();
	}
}
