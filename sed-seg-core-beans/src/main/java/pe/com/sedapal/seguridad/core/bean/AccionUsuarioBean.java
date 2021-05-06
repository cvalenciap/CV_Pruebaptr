package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccionUsuarioBean implements Serializable, RowMapper<Object> {

	private static final long serialVersionUID = 1L;

	private Integer codAccionFormulario;
	/*private Integer codSistema;
	private Integer codModulo;
	private Integer codFormulario;*/
	private String codParametro;
	private Integer codigo;
	private Integer estado;	
	// Propiedades adicionales
	private String nombreAccion;
	private Integer accionPerfil;

	public AccionUsuarioBean() {
	}

	public AccionUsuarioBean(Integer codAccionFormulario, 
			//Integer codSistema, Integer codModulo, Integer codFormulario,
			String codParametro, Integer codigo, Integer estado, String nombreAccion, Integer accionPerfil) {
		super();
		this.codAccionFormulario = codAccionFormulario;
		/*this.codSistema = codSistema;
		this.codModulo = codModulo;
		this.codFormulario = codFormulario;*/
		this.codParametro = codParametro;
		this.codigo = codigo;
		this.estado = estado;
		this.nombreAccion = nombreAccion;
		this.accionPerfil = accionPerfil;
	}

	public Integer getCodAccionFormulario() {
		return codAccionFormulario;
	}

	public void setCodAccionFormulario(Integer codAccionFormulario) {
		this.codAccionFormulario = codAccionFormulario;
	}

	/*public Integer getCodSistema() {
		return codSistema;
	}

	public void setCodSistema(Integer codSistema) {
		this.codSistema = codSistema;
	}

	public Integer getCodModulo() {
		return codModulo;
	}

	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
	}

	public Integer getCodFormulario() {
		return codFormulario;
	}

	public void setCodFormulario(Integer codFormulario) {
		this.codFormulario = codFormulario;
	}*/

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

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public String getNombreAccion() {
		return nombreAccion;
	}
	
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	public Integer getAccionPerfil() {
		return accionPerfil;
	}

	public void setAccionPerfil(Integer accionPerfil) {
		this.accionPerfil = accionPerfil;
	}

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new AccionUsuarioBean(rs.getInt(1), 
					/*rs.getInt(2), rs.getInt(3), rs.getInt(4),*/ 
					rs.getString(2), rs.getInt(3),
				rs.getInt(4), rs.getString(5), rs.getInt(6));
	}
	
	

}
