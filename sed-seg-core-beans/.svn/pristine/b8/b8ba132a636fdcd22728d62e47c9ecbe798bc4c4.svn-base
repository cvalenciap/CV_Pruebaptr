package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EnsayoBean extends Auditoria implements Serializable, RowMapper<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7473199776927880850L;
	private Integer codProductoEn;
	private String ensayo;
	private String unidades;
	private String requisitosMax;
	private String requisitosMin;
	private String requisitosTexto;
	private String Obtenido;

	public EnsayoBean() {
		super();
	}


	public EnsayoBean(Integer codProductoEn, String ensayo, String unidades, String requisitosMin, String requisitosMax,String requisitosTexto,
			String obtenido) {
		super();
		this.codProductoEn = codProductoEn;
		this.ensayo = ensayo;
		this.unidades = unidades;
		this.requisitosMax = requisitosMax;
		this.requisitosMin = requisitosMin;
		this.requisitosTexto = requisitosTexto;
		this.Obtenido = obtenido;
	}


	public Integer getCodProductoEn() {
		return codProductoEn;
	}

	public void setCodProductoEn(Integer codProductoEn) {
		this.codProductoEn = codProductoEn;
	}

	public String getEnsayo() {
		return ensayo;
	}

	public void setEnsayo(String ensayo) {
		this.ensayo = ensayo;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}


	public String getRequisitosMax() {
		return requisitosMax;
	}

	public void setRequisitosMax(String requisitosMax) {
		this.requisitosMax = requisitosMax;
	}

	public String getRequisitosMin() {
		return requisitosMin;
	}

	public void setRequisitosMin(String requisitosMin) {
		this.requisitosMin = requisitosMin;
	}

	public String getObtenido() {
		return Obtenido;
	}

	public void setObtenido(String obtenido) {
		Obtenido = obtenido;
	}
	
	
	public String getRequisitosTexto() {
		return requisitosTexto;
	}

	public void setRequisitosTexto(String requisitosTexto) {
		this.requisitosTexto = requisitosTexto;
	}


	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new EnsayoBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7));
	}

	@Override
	public String toString() {
		return "EnsayoBean [codProductoEn=" + codProductoEn + ", ensayo=" + ensayo + ", unidades=" + unidades
				+ ", requisitosMax=" + requisitosMax + ", requisitosMin=" + requisitosMin 
				+ ", requisitosTexto=" + requisitosTexto 
				+ ", Obtenido=" + Obtenido
				+ "]";
	}
	
}
