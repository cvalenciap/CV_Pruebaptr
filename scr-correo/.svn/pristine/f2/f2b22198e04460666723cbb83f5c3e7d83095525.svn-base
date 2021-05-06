/**
 * 
 * Resumen.
 * Objeto 				: CorreoCaudalServiceImpl
 * Descripción 			: Clase implementadora de la interfaz (de servicio) de caudal
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.ReporteGraficoBean;
import pe.com.sedapal.scr.correo.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.ICaudalDao;
import pe.com.sedapal.scr.correo.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.correo.service.ICaudalService;

@Service
public class CorreoCaudalServiceImpl implements ICaudalService {
	
	@Autowired
	private ICaudalDao caudalDao;
	
	@Autowired
	private ICaudalDetalleDao caudalDetalleDao;
	
	/**
	 * Método que permite obtener detalles de caudales para un periodo
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<ReporteResumenBean> generarReporteResumenCaudal(String periodo) throws Exception {
		
		List<ReporteResumenBean> listaResultados = caudalDao.generarReporteResumenCaudal(periodo);
		for(ReporteResumenBean dato : listaResultados){
			if(dato.getNHR01().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR01(null);
			}
			if(dato.getNHR02().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR02(null);
			}
			if(dato.getNHR03().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR03(null);
			}
			if(dato.getNHR04().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR04(null);
			}
			if(dato.getNHR05().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR05(null);
			}
			if(dato.getNHR06().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR06(null);
			}
			if(dato.getNHR07().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR07(null);
			}
			if(dato.getNHR08().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR08(null);
			}
			if(dato.getNHR09().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR09(null);
			}
			if(dato.getNHR10().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR10(null);
			}
			if(dato.getNHR11().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR11(null);
			}
			if(dato.getNHR12().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR12(null);
			}
			if(dato.getNHR13().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR13(null);
			}
			if(dato.getNHR14().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR14(null);
			}
			if(dato.getNHR15().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR15(null);
			}
			if(dato.getNHR16().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR16(null);
			}
			if(dato.getNHR17().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR17(null);
			}
			if(dato.getNHR18().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR18(null);
			}
			if(dato.getNHR19().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR19(null);
			}
			if(dato.getNHR20().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR20(null);
			}
			if(dato.getNHR21().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR21(null);
			}
			if(dato.getNHR22().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR22(null);
			}
			if(dato.getNHR23().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR23(null);
			}
			if(dato.getNHR24().compareTo(new BigDecimal(Constants.DEFAULT_DETCAU_DOUBLE + "")) == 0){
				dato.setNHR24(null);
			}
		}
		
		return listaResultados;
	}
	
	/**
	 * Método que permite obtener promedios diarios para un periodo
	 * @Return Objeto de tipo ReporteGraficoBean que contiene los datos promedios diarios 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<ReporteGraficoBean> generarReporteGraficoCaudal(String periodo) throws Exception {		
		return caudalDao.generarReporteGraficoCaudal(periodo);
	}
	
	/**
	 * Método que permite registrar caudal
	 * @Return Objeto de tipo Integer que contiene código de caudal 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarCaudal(Caudal caudal) throws GmdException {
		Integer intCodigoCaudal;
		try {
			intCodigoCaudal = caudalDao.registrarCaudal(caudal);
			
			for(CaudalDetalle caudalDetalle : caudal.getLstDetalles()) {
				Caudal innerCaudal = new Caudal();
				innerCaudal.setIntCodigo(intCodigoCaudal);
				caudalDetalle.setCaudal(innerCaudal);
				caudalDetalleDao.registrarCaudalDetalle(caudalDetalle);
			}
		}catch(Exception ex) {
			throw new GmdException(ex);
		}
		return intCodigoCaudal;
	}
	
	/**
	 * Método que permite actualizar caudal
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarCaudal(Caudal caudal) throws GmdException {
		try {
			caudalDao.actualizarCaudal(caudal);
			
			for(CaudalDetalle caudalDetalle : caudal.getLstDetalles()) {
				Caudal innerCaudal = new Caudal();
				innerCaudal.setIntCodigo(caudal.getIntCodigo());
				caudalDetalle.setCaudal(innerCaudal);
				caudalDetalleDao.registrarCaudalDetalle(caudalDetalle);
			}
		} catch (Exception exception) {
			throw new GmdException(exception);
		}
	}
	
	/**
	 * Método que permite obtener caudales para un periodo y sobre un estado
	 * @Return Objeto de tipo Caudal que contiene los datos caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Caudal obtenerCaudal(Integer intCodigoRio, String strPeriodo, String strEstado) throws GmdException {
		try {
			return caudalDao.obtenerCaudal(intCodigoRio, strPeriodo, strEstado);
		} catch (Exception ex) {
			throw new GmdException(ex);
		}
	}	
	
}
