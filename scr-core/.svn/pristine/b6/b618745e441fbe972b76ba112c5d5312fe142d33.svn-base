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
import pe.com.sedapal.scr.core.beans.BeanComodin;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.Formulario153;
import pe.com.sedapal.scr.core.beans.ReporteMuestraPdf;
import pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao;
import pe.com.sedapal.scr.core.services.IRegistroDeMuestraService;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroDeMuestraServiceImpl.
 */
@Service
public class RegistroDeMuestraServiceImpl implements IRegistroDeMuestraService{
	
	/** The i registro de muestra dao. */
	@Autowired
	private IRegistroDeMuestraDao iRegistroDeMuestraDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerRegistros(pe.com.sedapal.scr.core.beans.Formulario153, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerRegistros(Formulario153 formulario153, Paginacion paginacion) throws Exception {
		
		return iRegistroDeMuestraDao.obtenerRegistros(formulario153, paginacion);

	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerFormulario153(java.lang.Integer)
	 */
	@Override
	public Formulario153 obtenerFormulario153(Integer nid) {
		
		return iRegistroDeMuestraDao.obtenerFormulario153(nid);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#grabarMuestra(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public int grabarMuestra(Formulario153 formulario153)  {
		
		return iRegistroDeMuestraDao.grabarMuestra(formulario153);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#generarReporteMuestraPdf(java.lang.String)
	 */
	@Override
	public List<ReporteMuestraPdf> generarReporteMuestraPdf(String fecha) {
		
		return iRegistroDeMuestraDao.generarReporteMuestraPdf(fecha);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#eliminarFormulario(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public void eliminarFormulario(Formulario153 formulario153) {
		
		iRegistroDeMuestraDao.eliminarFormulario(formulario153);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#actualizarFormulario(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public void actualizarFormulario(Formulario153 formulario153) {
		
		iRegistroDeMuestraDao.actualizarFormulario(formulario153);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerPuntoMuestreoCbo()
	 */
	@Override
	public List<BeanComodin> obtenerPuntoMuestreoCbo() {
		
		return iRegistroDeMuestraDao.obtenerPuntoMuestreoCbo();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerMatrizCbo()
	 */
	@Override
	public List<BeanComodin> obtenerMatrizCbo() {
		
		return iRegistroDeMuestraDao.obtenerMatrizCbo();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerTipoAnalisisCbo()
	 */
	@Override
	public List<BeanComodin> obtenerTipoAnalisisCbo() {
		
		return iRegistroDeMuestraDao.obtenerTipoAnalisisCbo();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerTipoFrascoCbo()
	 */
	@Override
	public List<BeanComodin> obtenerTipoFrascoCbo() {
		
		return iRegistroDeMuestraDao.obtenerTipoFrascoCbo();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerPersonaMuestreaCbo()
	 */
	@Override
	public List<BeanComodin> obtenerPersonaMuestreaCbo() {
		
		return iRegistroDeMuestraDao.obtenerPersonaMuestreaCbo();
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IRegistroDeMuestraService#obtenerRecepAnalistaCbo()
	 */
	@Override
	public List<BeanComodin> obtenerRecepAnalistaCbo() {
		
		return iRegistroDeMuestraDao.obtenerRecepAnalistaCbo();
	}

	
}
