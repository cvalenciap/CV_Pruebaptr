/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ParametroPlan;
import pe.com.sedapal.scr.core.beans.ParametroReporte;
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.TotalesForm20;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPlanOperativoDao.
 */
@Repository
public interface IPlanOperativoDao {

	/**
	 * Obtener datos parametros plan.
	 *
	 * @param parametroPlan the parametro plan
	 * @param paginacion the paginacion
	 * @return the result
	 */
	public Result obtenerDatosParametrosPlan(ParametroPlan parametroPlan, Paginacion paginacion);
	
	/**
	 * Obtener parametro plan.
	 *
	 * @param codsxp the codsxp
	 * @param codplan the codplan
	 * @return the parametro plan
	 */
	public ParametroPlan obtenerParametroPlan(Integer codsxp, Integer codplan);
	
	/**
	 * Insertar parametro plan.
	 *
	 * @param parametroPlan the parametro plan
	 */
	public void insertarParametroPlan(ParametroPlan parametroPlan);
	
	/**
	 * Existe parametro plan.
	 *
	 * @param codsxp the codsxp
	 * @param codplan the codplan
	 * @return true, if successful
	 */
	public boolean existeParametroPlan(Integer codsxp, Integer codplan);
	
	/**
	 * Actualizar parametro plan.
	 *
	 * @param parametroPlan the parametro plan
	 */
	public void actualizarParametroPlan(ParametroPlan parametroPlan);
	
	/**
	 * Actualizar plan operativo.
	 *
	 * @param planOperativo the plan operativo
	 * @return the int
	 */
	public int actualizarPlanOperativo(PlanOperativo planOperativo);
	
	/**
	 * Obtener plan operativo.
	 *
	 * @param codplan the codplan
	 * @return the plan operativo
	 */
	public PlanOperativo obtenerPlanOperativo(Integer codplan);
	
	/**
	 * Obtener datos plan operativo.
	 *
	 * @param planOperativo the plan operativo
	 * @param paginacion the paginacion
	 * @param flagPlanOperativo the flag plan operativo
	 * @return the result
	 */
	public Result obtenerDatosPlanOperativo(PlanOperativo planOperativo, Paginacion paginacion,Integer flagPlanOperativo);
	
	/**
	 * Grabar plan operativo.
	 *
	 * @param planOperativo the plan operativo
	 * @return the int
	 */
	public int grabarPlanOperativo(PlanOperativo planOperativo);
	
	/**
	 * Inactivar parametro plan.
	 *
	 * @param parametroPlan the parametro plan
	 */
	public void inactivarParametroPlan(ParametroPlan parametroPlan);
	
	/**
	 * Obtener reporte plan operativo.
	 *
	 * @param codplan the codplan
	 * @param idGrupo the id grupo
	 * @param mes the mes
	 * @return the list
	 */
	/*METODOS PARA REPORTE PLAN OPERATIVO*/
	public List<ParametroReporte> obtenerReportePlanOperativo(Integer codplan,Integer idGrupo, String mes);
	
	/**
	 * Guardar parametro reporte.
	 *
	 * @param parametroReporte the parametro reporte
	 * @param codplan the codplan
	 * @param mes the mes
	 */
	public void guardarParametroReporte(ParametroReporte parametroReporte , Integer codplan, String mes);
	
	/**
	 * Obtener totales form 20.
	 *
	 * @param anio the anio
	 * @param mes the mes
	 * @param tipoResult the tipo result
	 * @return the totales form 20
	 */
	public TotalesForm20 obtenerTotalesForm20(Integer anio, String mes, Integer tipoResult);
	
}