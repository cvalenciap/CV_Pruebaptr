package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha CreaciÃ³n: 28/12/2016.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */
public class ParametroBean extends Auditoria implements Serializable, RowMapper<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codParametro;
	private Integer codigo;
	private String descripcion;
	private Integer estado;
	private Integer id;
	private String valor;
	private String nombreEstado;

	public ParametroBean() {

	}
	
	public ParametroBean(String nCodParametro, Integer nCodigo, String sDescripcion, Integer nEstado, Integer nId, String sValor) {
		this.codParametro = nCodParametro;
		this.codigo = nCodigo;
		this.descripcion = sDescripcion;
		this.estado = nEstado;
		this.id=nId;
		this.valor = sValor;			
	}

	public ParametroBean(String codParametro, Integer codigo, String descripcion, Integer estado, Integer id,
			String valor, String nombreEstado, String usuarioCreacion, Timestamp fechaCreacion,
			String usuarioModificacion, Timestamp fechaModificacion) {
		super(usuarioCreacion, fechaCreacion, usuarioModificacion, fechaModificacion);
		this.codParametro = codParametro;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.id = id;
		this.valor = valor;
		this.nombreEstado = nombreEstado;
	}

	public String getCodParametro() {
		return codParametro;
	}

	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		if (descripcion != null && !"".equals(descripcion)) {
			return descripcion.toUpperCase();
		} else {
			return descripcion;
		}
	}
	
	
	public String getDescripcionSinModificar() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.toUpperCase();
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ParametroBean(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
				rs.getString(6), rs.getString(11), rs.getString(7), rs.getTimestamp(8), rs.getString(9),
				rs.getTimestamp(10));

	}

	public boolean isNew() {
		return (this.codParametro == null);
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	@Override
	public String toString() {
		return "ParametroBean [codParametro=" + codParametro + ", codigo=" + codigo + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", id=" + id + ", valor=" + valor + ", nombreEstado=" + nombreEstado + "]";
	}

}
