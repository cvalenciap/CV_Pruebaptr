package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ImpresionBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6085142857273082001L;

	private Integer codMuestra;
	private Integer nCodImpresion;
	private String codImpresion;
	private Blob archivo;
	private Integer estado;
	private Integer nCodLote;

	public ImpresionBean(Integer codMuestra, Integer nCodImpresion, String codImpresion, Blob archivo, Integer estado,
			Integer nCodLote) {
		super();
		this.codMuestra = codMuestra;
		this.nCodImpresion = nCodImpresion;
		this.codImpresion = codImpresion;
		this.archivo = archivo;
		this.estado = estado;
		this.nCodLote = nCodLote;
	}

	public ImpresionBean() {

	}

	public Integer getCodMuestra() {
		return codMuestra;
	}

	public void setCodMuestra(Integer codMuestra) {
		this.codMuestra = codMuestra;
	}

	public Integer getnCodImpresion() {
		return nCodImpresion;
	}

	public void setnCodImpresion(Integer nCodImpresion) {
		this.nCodImpresion = nCodImpresion;
	}

	public String getCodImpresion() {
		return codImpresion;
	}

	public void setCodImpresion(String codImpresion) {
		this.codImpresion = codImpresion;
	}

	public Blob getArchivo() {
		return archivo;
	}

	public void setArchivo(Blob archivo) {
		this.archivo = archivo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new ImpresionBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBlob(4), rs.getInt(5),
				rs.getInt(6));
	}

	public Integer getnCodLote() {
		return nCodLote;
	}

	public void setnCodLote(Integer nCodLote) {
		this.nCodLote = nCodLote;
	}

}
