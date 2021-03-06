package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class RegistroLaboratorioBean extends BaseSptarBean implements Serializable {

	public static final long serialVersionUID = 1L;
	
	public Integer idRegistroLaboratorio;
	public Integer idPtarxSector;
	public Integer idParametro;
	public String descripcionPtar;
	public String descripcionSector;
	public Date fechaRegistro;
	public String fechaRegistroString;
	public Integer idDirecto;
	public Integer flagDirecto;
	public String descripFlagDirecto;
	public Integer idDBO;
	public Integer flagDBO;
	public String descripFlagDBO;
	public Integer idAceite;
	public Integer flagAceite;
	public String descripFlagAceite;
	public Integer idSolido;
	public Integer flagSolido;
	public String descripFlagSolido;
	public Integer idHidrobiologico;
	public Integer flagHidrobiologico;
	public String descripFlagHidrobiologico;
	public Integer idMicrobiologico;
	public Integer flagMicrobiologico;
	public String descripFlagMicrobiologico;
	public Integer idParasitologico;
	public Integer flagParasitologico;
	public String descripFlagParasitologico;
	public Integer idEstadoAprob;
	public String descripcionEstadoAprob;
	public String estado;
	public Integer flagAprobacion;
	public Date fechaHoraCreacion;
	public String fechaHoraCreacionString;
	public Date fechaMonitoreo;
	public String fechaMonitoreoString;
	/*Observacions por Registro*/
	public String observacionDirecto;
	public String observacionDBO;
	public String observacionAceites;
	public String observacionSolidos;
	public String observacionHidrobiologico;
	public String observacionMicrobiologico;
	public String observacionParasitologico;
	/**/
	
	//PARA REPORTE
	public Double valorReporte;
	
	public Integer getIdRegistroLaboratorio() {
		return idRegistroLaboratorio;
	}
	public void setIdRegistroLaboratorio(Integer idRegistroLaboratorio) {
		this.idRegistroLaboratorio = idRegistroLaboratorio;
	}
	public Integer getIdPtarxSector() {
		return idPtarxSector;
	}
	public void setIdPtarxSector(Integer idPtarxSector) {
		this.idPtarxSector = idPtarxSector;
	}
	public Integer getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
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
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaRegistroString = df.format(fechaRegistro);
	}
	public String getFechaRegistroString() {
		return fechaRegistroString;
	}
	public void setFechaRegistroString(String fechaRegistroString) {
		this.fechaRegistroString = fechaRegistroString;
	}
	public Integer getIdDirecto() {
		return idDirecto;
	}
	public void setIdDirecto(Integer idDirecto) {
		this.idDirecto = idDirecto;
	}
	public Integer getFlagDirecto() {
		return flagDirecto;
	}
	public void setFlagDirecto(Integer flagDirecto) {
		this.flagDirecto = flagDirecto;
	}
	public String getDescripFlagDirecto() {
		return descripFlagDirecto;
	}
	public void setDescripFlagDirecto(String descripFlagDirecto) {
		this.descripFlagDirecto = descripFlagDirecto;
	}
	public Integer getIdDBO() {
		return idDBO;
	}
	public void setIdDBO(Integer idDBO) {
		this.idDBO = idDBO;
	}
	public Integer getFlagDBO() {
		return flagDBO;
	}
	public void setFlagDBO(Integer flagDBO) {
		this.flagDBO = flagDBO;
	}
	public String getDescripFlagDBO() {
		return descripFlagDBO;
	}
	public void setDescripFlagDBO(String descripFlagDBO) {
		this.descripFlagDBO = descripFlagDBO;
	}
	public Integer getIdAceite() {
		return idAceite;
	}
	public void setIdAceite(Integer idAceite) {
		this.idAceite = idAceite;
	}
	public Integer getFlagAceite() {
		return flagAceite;
	}
	public void setFlagAceite(Integer flagAceite) {
		this.flagAceite = flagAceite;
	}
	public String getDescripFlagAceite() {
		return descripFlagAceite;
	}
	public void setDescripFlagAceite(String descripFlagAceite) {
		this.descripFlagAceite = descripFlagAceite;
	}
	public Integer getIdSolido() {
		return idSolido;
	}
	public void setIdSolido(Integer idSolido) {
		this.idSolido = idSolido;
	}
	public Integer getFlagSolido() {
		return flagSolido;
	}
	public void setFlagSolido(Integer flagSolido) {
		this.flagSolido = flagSolido;
	}
	public String getDescripFlagSolido() {
		return descripFlagSolido;
	}
	public void setDescripFlagSolido(String descripFlagSolido) {
		this.descripFlagSolido = descripFlagSolido;
	}
	public Integer getIdHidrobiologico() {
		return idHidrobiologico;
	}
	public void setIdHidrobiologico(Integer idHidrobiologico) {
		this.idHidrobiologico = idHidrobiologico;
	}
	public Integer getFlagHidrobiologico() {
		return flagHidrobiologico;
	}
	public void setFlagHidrobiologico(Integer flagHidrobiologico) {
		this.flagHidrobiologico = flagHidrobiologico;
	}
	public String getDescripFlagHidrobiologico() {
		return descripFlagHidrobiologico;
	}
	public void setDescripFlagHidrobiologico(String descripFlagHidrobiologico) {
		this.descripFlagHidrobiologico = descripFlagHidrobiologico;
	}
	public Integer getIdMicrobiologico() {
		return idMicrobiologico;
	}
	public void setIdMicrobiologico(Integer idMicrobiologico) {
		this.idMicrobiologico = idMicrobiologico;
	}
	public Integer getFlagMicrobiologico() {
		return flagMicrobiologico;
	}
	public void setFlagMicrobiologico(Integer flagMicrobiologico) {
		this.flagMicrobiologico = flagMicrobiologico;
	}
	public String getDescripFlagMicrobiologico() {
		return descripFlagMicrobiologico;
	}
	public void setDescripFlagMicrobiologico(String descripFlagMicrobiologico) {
		this.descripFlagMicrobiologico = descripFlagMicrobiologico;
	}
	public Integer getIdParasitologico() {
		return idParasitologico;
	}
	public void setIdParasitologico(Integer idParasitologico) {
		this.idParasitologico = idParasitologico;
	}
	public Integer getFlagParasitologico() {
		return flagParasitologico;
	}
	public void setFlagParasitologico(Integer flagParasitologico) {
		this.flagParasitologico = flagParasitologico;
	}
	public String getDescripFlagParasitologico() {
		return descripFlagParasitologico;
	}
	public void setDescripFlagParasitologico(String descripFlagParasitologico) {
		this.descripFlagParasitologico = descripFlagParasitologico;
	}
	public Integer getIdEstadoAprob() {
		return idEstadoAprob;
	}
	public void setIdEstadoAprob(Integer idEstadoAprob) {
		this.idEstadoAprob = idEstadoAprob;
	}
	public String getDescripcionEstadoAprob() {
		return descripcionEstadoAprob;
	}
	public void setDescripcionEstadoAprob(String descripcionEstadoAprob) {
		this.descripcionEstadoAprob = descripcionEstadoAprob;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getFlagAprobacion() {
		return flagAprobacion;
	}
	public void setFlagAprobacion(Integer flagAprobacion) {
		this.flagAprobacion = flagAprobacion;
	}
	public Date getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}
	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaHoraCreacionString = df.format(fechaHoraCreacion);
	}
	public String getFechaHoraCreacionString() {
		return fechaHoraCreacionString;
	}
	public void setFechaHoraCreacionString(String fechaHoraCreacionString) {
		this.fechaHoraCreacionString = fechaHoraCreacionString;
	}
	public Double getValorReporte() {
		return valorReporte;
	}
	public void setValorReporte(Double valorReporte) {
		this.valorReporte = valorReporte;
	}
	public Date getFechaMonitoreo() {
		return fechaMonitoreo;
	}
	public void setFechaMonitoreo(Date fechaMonitoreo) {
		this.fechaMonitoreo = fechaMonitoreo;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaMonitoreoString = df.format(fechaMonitoreo);
	}
	public String getFechaMonitoreoString() {
		return fechaMonitoreoString;
	}
	public void setFechaMonitoreoString(String fechaMonitoreoString) {
		this.fechaMonitoreoString = fechaMonitoreoString;
	}
	public String getObservacionDirecto() {
		return observacionDirecto;
	}
	public void setObservacionDirecto(String observacionDirecto) {
		this.observacionDirecto = observacionDirecto;
	}
	public String getObservacionDBO() {
		return observacionDBO;
	}
	public void setObservacionDBO(String observacionDBO) {
		this.observacionDBO = observacionDBO;
	}
	public String getObservacionAceites() {
		return observacionAceites;
	}
	public void setObservacionAceites(String observacionAceites) {
		this.observacionAceites = observacionAceites;
	}
	public String getObservacionSolidos() {
		return observacionSolidos;
	}
	public void setObservacionSolidos(String observacionSolidos) {
		this.observacionSolidos = observacionSolidos;
	}
	public String getObservacionHidrobiologico() {
		return observacionHidrobiologico;
	}
	public void setObservacionHidrobiologico(String observacionHidrobiologico) {
		this.observacionHidrobiologico = observacionHidrobiologico;
	}
	public String getObservacionMicrobiologico() {
		return observacionMicrobiologico;
	}
	public void setObservacionMicrobiologico(String observacionMicrobiologico) {
		this.observacionMicrobiologico = observacionMicrobiologico;
	}
	public String getObservacionParasitologico() {
		return observacionParasitologico;
	}
	public void setObservacionParasitologico(String observacionParasitologico) {
		this.observacionParasitologico = observacionParasitologico;
	}
}