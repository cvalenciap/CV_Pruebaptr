package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoteProyectoBean extends Auditoria implements Serializable, RowMapper<Object> {

	private static final long serialVersionUID = 1L;
	
	private Integer ncorr;
	private Integer codLote;
	private Integer ncodlineaproducto;
	private Integer ncodproducto;
	private Integer ncodempresa;
	private Integer ncodlote_proyecto;
	private Integer ncantidad;
	private String  vrescre;
	private Date dfeccre;
	private String vresact;
	private Date dfecact;
	
	public LoteProyectoBean() {
	}

	public LoteProyectoBean(Integer ncorr, Integer codLote, Integer ncodlineaproducto, Integer ncodproducto,
			Integer ncodempresa, Integer ncodlote_proyecto, Integer ncantidad, String vrescre, 
			String vresact) {
		super();
		this.ncorr = ncorr;
		this.codLote = codLote;
		this.ncodlineaproducto = ncodlineaproducto;
		this.ncodproducto = ncodproducto;
		this.ncodempresa = ncodempresa;
		this.ncodlote_proyecto = ncodlote_proyecto;
		this.ncantidad = ncantidad;
		this.vrescre = vrescre;		
		this.vresact = vresact;		
	}

	public Integer getNcorr() {
		return ncorr;
	}

	public void setNcorr(Integer ncorr) {
		this.ncorr = ncorr;
	}

	public Integer getCodLote() {
		return codLote;
	}

	public void setCodLote(Integer codLote) {
		this.codLote = codLote;
	}

	public Integer getNcodlineaproducto() {
		return ncodlineaproducto;
	}

	public void setNcodlineaproducto(Integer ncodlineaproducto) {
		this.ncodlineaproducto = ncodlineaproducto;
	}

	public Integer getNcodproducto() {
		return ncodproducto;
	}

	public void setNcodproducto(Integer ncodproducto) {
		this.ncodproducto = ncodproducto;
	}

	public Integer getNcodempresa() {
		return ncodempresa;
	}

	public void setNcodempresa(Integer ncodempresa) {
		this.ncodempresa = ncodempresa;
	}

	public Integer getNcodlote_proyecto() {
		return ncodlote_proyecto;
	}

	public void setNcodlote_proyecto(Integer ncodlote_proyecto) {
		this.ncodlote_proyecto = ncodlote_proyecto;
	}

	public Integer getNcantidad() {
		return ncantidad;
	}

	public void setNcantidad(Integer ncantidad) {
		this.ncantidad = ncantidad;
	}

	public String getVrescre() {
		return vrescre;
	}

	public void setVrescre(String vrescre) {
		this.vrescre = vrescre;
	}

	public Date getDfeccre() {
		return dfeccre;
	}

	public void setDfeccre(Date dfeccre) {
		this.dfeccre = dfeccre;
	}

	public String getVresact() {
		return vresact;
	}

	public void setVresact(String vresact) {
		this.vresact = vresact;
	}

	public Date getDfecact() {
		return dfecact;
	}

	public void setDfecact(Date dfecact) {
		this.dfecact = dfecact;
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new LoteProyectoBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
			rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), 
			rs.getString(9));
	}

	@Override
	public String toString() {
		return "LoteProyectoBean [ncorr=" + ncorr + ", codLote=" + codLote + ", ncodlineaproducto=" + ncodlineaproducto
				+ ", ncodproducto=" + ncodproducto + ", ncodempresa=" + ncodempresa + ", ncodlote_proyecto="
				+ ncodlote_proyecto + ", ncantidad=" + ncantidad + ", vrescre=" + vrescre + ", dfeccre=" + dfeccre
				+ ", vresact=" + vresact + ", dfecact=" + dfecact + "]";
	}
	
	

}
