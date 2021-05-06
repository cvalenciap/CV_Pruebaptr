package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.List;

public class AnalistaBean extends BaseSptarBean implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public Integer idAnalista;
	public Integer tipoAnalista;
	public String numeroFicha;
	public Integer tipoDocumento;
	public String numeroDocumento;
	public String nombre;
	public String apellido;
	public String cargo;
	public String nombreCompleto;
	public Integer idPtarPorSector;
	public String descripcionPtar;
	public String descripcionSector;
	public String observacion;
	public String flagRegistrador;
	public String desFlagRegi;
	public String flagAprobador;
	public String desFlagAprobador;
	public String estado;
	public String descripcionTipoAnalista;
	public String descripcionTipoDoc;
	public String correo;
	public String codigoUsuario;
	public Integer idFlagTipo;
	public String descripcionFlagTipo;
	public List<ParametroPtarSectorBean> listaParametrosAsignados;
	
	public List<ParametroPtarSectorBean> getListaParametrosAsignados() {
		return listaParametrosAsignados;
	}
	public void setListaParametrosAsignados(List<ParametroPtarSectorBean> listaParametrosAsignados) {
		this.listaParametrosAsignados = listaParametrosAsignados;
	}
	public Integer getIdFlagTipo() {
		return idFlagTipo;
	}
	public void setIdFlagTipo(Integer idFlagTipo) {
		this.idFlagTipo = idFlagTipo;
	}
	public String getDescripcionFlagTipo() {
		return descripcionFlagTipo;
	}
	public void setDescripcionFlagTipo(String descripcionFlagTipo) {
		this.descripcionFlagTipo = descripcionFlagTipo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getDescripcionTipoDoc() {
		return descripcionTipoDoc;
	}
	public void setDescripcionTipoDoc(String descripcionTipoDoc) {
		this.descripcionTipoDoc = descripcionTipoDoc;
	}
	public String getDescripcionTipoAnalista() {
		return descripcionTipoAnalista;
	}
	public void setDescripcionTipoAnalista(String descripcionTipoAnalista) {
		this.descripcionTipoAnalista = descripcionTipoAnalista;
	}
	public Integer getIdAnalista() {
		return idAnalista;
	}
	public void setIdAnalista(Integer idAnalista) {
		this.idAnalista = idAnalista;
	}
	public Integer getTipoAnalista() {
		return tipoAnalista;
	}
	public void setTipoAnalista(Integer tipoAnalista) {
		this.tipoAnalista = tipoAnalista;
	}
	public String getNumeroFicha() {
		return numeroFicha;
	}
	public void setNumeroFicha(String numeroFicha) {
		this.numeroFicha = numeroFicha;
	}
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Integer getIdPtarPorSector() {
		return idPtarPorSector;
	}
	public void setIdPtarPorSector(Integer idPtarPorSector) {
		this.idPtarPorSector = idPtarPorSector;
	}
	public String getDescripcionPtar() {
		return descripcionPtar;
	}
	public void setDescripcionPtar(String descripcionPtar) {
		this.descripcionPtar = descripcionPtar;
	}
	public String getDescripcionSector() {
		return descripcionSector;
	}
	public void setDescripcionSector(String descripcionSector) {
		this.descripcionSector = descripcionSector;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFlagRegistrador() {
		return flagRegistrador;
	}
	public void setFlagRegistrador(String flagRegistrador) {
		this.flagRegistrador = flagRegistrador;
	}
	public String getDesFlagRegi() {
		return desFlagRegi;
	}
	public void setDesFlagRegi(String desFlagRegi) {
		this.desFlagRegi = desFlagRegi;
	}
	public String getFlagAprobador() {
		return flagAprobador;
	}
	public void setFlagAprobador(String flagAprobador) {
		this.flagAprobador = flagAprobador;
	}
	public String getDesFlagAprobador() {
		return desFlagAprobador;
	}
	public void setDesFlagAprobador(String desFlagAprobador) {
		this.desFlagAprobador = desFlagAprobador;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return  nombre + " "+apellido;
	}
}
