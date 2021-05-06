/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportePlanOperativo.
 */
public class ReportePlanOperativo extends BaseBean implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private Integer codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The estado. */
	private Integer estado;
	
	/** The codplan. */
	private Integer codplan;
	
	/** The codare. */
	private Integer codare;
	
	/** The cod desc plan. */
	private String codDescPlan;
	
	/** The mes. */
	private String mes;
	
	/** The periodo. */
	private Integer periodo;
	
	/**
	 * Instantiates a new reporte plan operativo.
	 */
	public ReportePlanOperativo(){
	}
	
	/**
	 * Instantiates a new reporte plan operativo.
	 *
	 * @param repo the repo
	 */
	public ReportePlanOperativo(ReportePlanOperativo repo){
		
		codigo = repo.getCodigo();
		descripcion = repo.getDescripcion();
		estado = repo.getEstado();
		codplan = repo.getCodplan();
		codare = repo.getCodare();
		codDescPlan = repo.getCodDescPlan();
		mes = repo.getMes();
		actividades = repo.getActividades();
				
	}
	
	/** The actividades. */
	private ArrayList<ActividadReporte> actividades;
	
	/**
	 * Gets the codare.
	 *
	 * @return the codare
	 */
	public Integer getCodare() {
		return codare;
	}

	/**
	 * Sets the codare.
	 *
	 * @param codare the new codare
	 */
	public void setCodare(Integer codare) {
		this.codare = codare;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * Gets the actividades.
	 *
	 * @return the actividades
	 */
	public ArrayList<ActividadReporte> getActividades() {
		return actividades;
	}

	/**
	 * Sets the actividades.
	 *
	 * @param actividades the new actividades
	 */
	public void setActividades(ArrayList<ActividadReporte> actividades) {
		this.actividades = actividades;
	}
	
	/**
	 * Gets the codplan.
	 *
	 * @return the codplan
	 */
	public Integer getCodplan() {
		return codplan;
	}

	/**
	 * Sets the codplan.
	 *
	 * @param codplan the new codplan
	 */
	public void setCodplan(Integer codplan) {
		this.codplan = codplan;
	}

	/**
	 * Gets the cod desc plan.
	 *
	 * @return the cod desc plan
	 */
	public String getCodDescPlan() {
		return codDescPlan;
	}

	/**
	 * Sets the cod desc plan.
	 *
	 * @param codDescPlan the new cod desc plan
	 */
	public void setCodDescPlan(String codDescPlan) {
		this.codDescPlan = codDescPlan;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public Integer getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReportePlanOperativo [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", codplan=" + codplan + ", codare=" + codare + ", codDescPlan=" + codDescPlan + ", mes=" + mes
				+ ", periodo=" + periodo + ", actividades=" + actividades + "]";
	}

	

}
