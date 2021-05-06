/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.ParametroPlan;
import pe.com.sedapal.scr.core.beans.ParametroReporte;
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.TotalesForm20;
import pe.com.sedapal.scr.core.dao.IPlanOperativoDao;
import pe.com.sedapal.scr.core.services.IPlanOperativoService;

// TODO: Auto-generated Javadoc
/**
 * The Class PlanOperativoServiceImpl.
 */
@Service
public class PlanOperativoServiceImpl implements IPlanOperativoService{

	/** The plan operativo dao. */
	@Autowired
	IPlanOperativoDao planOperativoDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerDatosParametrosPlan(pe.com.sedapal.scr.core.beans.ParametroPlan, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosParametrosPlan(ParametroPlan parametroPlan, Paginacion paginacion) {
		return planOperativoDao.obtenerDatosParametrosPlan(parametroPlan, paginacion);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerParametroPlan(java.lang.Integer, java.lang.Integer)
	 */
	public ParametroPlan obtenerParametroPlan(Integer codsxp, Integer codplan){
		
		return planOperativoDao.obtenerParametroPlan(codsxp, codplan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#insertarParametroPlan(pe.com.sedapal.scr.core.beans.ParametroPlan)
	 */
	public void insertarParametroPlan(ParametroPlan parametroPlan) {
		planOperativoDao.insertarParametroPlan(parametroPlan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#existeParametroPlan(java.lang.Integer, java.lang.Integer)
	 */
	public boolean existeParametroPlan(Integer codsxp, Integer codplan){
		return planOperativoDao.existeParametroPlan(codsxp,codplan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#actualizarParametroPlan(pe.com.sedapal.scr.core.beans.ParametroPlan)
	 */
	public void actualizarParametroPlan(ParametroPlan parametroPlan){
		planOperativoDao.actualizarParametroPlan(parametroPlan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#actualizarPlanOperativo(pe.com.sedapal.scr.core.beans.PlanOperativo)
	 */
	public int actualizarPlanOperativo(PlanOperativo planOperativo){
		return planOperativoDao.actualizarPlanOperativo(planOperativo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerPlanOperativo(java.lang.Integer)
	 */
	public PlanOperativo obtenerPlanOperativo(Integer codplan) {
		return planOperativoDao.obtenerPlanOperativo(codplan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerDatosPlanOperativo(pe.com.sedapal.scr.core.beans.PlanOperativo, pe.com.sedapal.common.core.beans.Paginacion, java.lang.Integer)
	 */
	public Result obtenerDatosPlanOperativo(PlanOperativo planOperativo, Paginacion paginacion, Integer flagReportePlan){
		return planOperativoDao.obtenerDatosPlanOperativo(planOperativo, paginacion,flagReportePlan);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#grabarPlanOperativo(pe.com.sedapal.scr.core.beans.PlanOperativo)
	 */
	public int grabarPlanOperativo(PlanOperativo planOperativo) {
		return planOperativoDao.grabarPlanOperativo(planOperativo);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#inactivarParametroPlan(pe.com.sedapal.scr.core.beans.ParametroPlan)
	 */
	public void inactivarParametroPlan(ParametroPlan parametroPlan){
		planOperativoDao.inactivarParametroPlan(parametroPlan);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerReportePlanOperativo(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<ParametroReporte> obtenerReportePlanOperativo(Integer codplan,Integer idGrupo, String mes) {
		return planOperativoDao.obtenerReportePlanOperativo(codplan,idGrupo, mes);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#guardarParametroReporte(pe.com.sedapal.scr.core.beans.ParametroReporte, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void guardarParametroReporte(ParametroReporte parametroReporte , Integer codplan, String mes){
		planOperativoDao.guardarParametroReporte(parametroReporte, codplan, mes);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPlanOperativoService#obtenerTotalesForm20(java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public TotalesForm20 obtenerTotalesForm20(Integer anio, String mes, Integer tipoResult){
		return  planOperativoDao.obtenerTotalesForm20(anio,mes,tipoResult);
	}
}
