package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class ReporteConsolidado implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2332051935372213733L;

	private String codObra;
	private String descripcionObra;
	private String codDistrito;
	private String descripcionDistrito;
	private String codigoLote;
	private String nombreProducto;
	private String fechaInicial;
	private String fechaFin;
	private String fechaLote;
	private String cantidad;
	private String numeroProtocolo;
	
	private List<ReporteConsolidado> detalles;

	public ReporteConsolidado() {

	}

	public ReporteConsolidado(String codObra, String descripcionObra, String codDistrito, String descripcionDistrito,
			String codigoLote, String nombreProducto, String fechaInicial, String fechaFin, String cantidad,
			String numeroProtocolo) {
		super();
		this.codObra = codObra;
		this.descripcionObra = descripcionObra;
		this.codDistrito = codDistrito;
		this.descripcionDistrito = descripcionDistrito;
		this.codigoLote = codigoLote;
		this.nombreProducto = nombreProducto;
		this.fechaInicial = fechaInicial;
		this.fechaFin = fechaFin;
		this.cantidad = cantidad;
		this.numeroProtocolo = numeroProtocolo;
	}



	public String getCodObra() {
		return codObra;
	}

	public void setCodObra(String codObra) {
		this.codObra = codObra;
	}

	public String getDescripcionObra() {
		return descripcionObra;
	}

	public void setDescripcionObra(String descripcionObra) {
		this.descripcionObra = descripcionObra;
	}

	public String getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}

	public String getDescripcionDistrito() {
		return descripcionDistrito;
	}

	public void setDescripcionDistrito(String descripcionDistrito) {
		this.descripcionDistrito = descripcionDistrito;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}
	
	public List<ReporteConsolidado> getDetalles() {
		if (detalles == null) {
			detalles = new ArrayList<>();
		}
		return detalles;
	}

	public void setDetalles(List<ReporteConsolidado> detalles) {
		this.detalles = detalles;
	}
		
	public String getFechaLote() {
		return fechaLote;
	}

	public void setFechaLote(String fechaLote) {
		this.fechaLote = fechaLote;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new ReporteConsolidado(rs.getString(1), 
									  rs.getString(2), 
									  rs.getString(3), 
									  rs.getString(4),
									  rs.getString(5), 
									  rs.getString(6), 
									  rs.getString(7), 
									  rs.getString(8), 
									  rs.getString(9),
									  rs.getString(10));
	}

	@Override
	public String toString() {
		return "ReporteConsolidado [codObra=" + codObra + ", descripcionObra=" + descripcionObra + ", codDistrito="
				+ codDistrito + ", descripcionDistrito=" + descripcionDistrito + ", codigoLote=" + codigoLote
				+ ", nombreProducto=" + nombreProducto + ", fechaInicial=" + fechaInicial + ", fechaFin=" + fechaFin
				+ ", cantidad=" + cantidad + ", numeroProtocolo=" + numeroProtocolo + "]";
	}

}
