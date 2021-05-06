/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.Formulario312;
import pe.com.sedapal.scr.core.beans.Formulario314;
import pe.com.sedapal.scr.core.beans.Reporte312Bean;
import pe.com.sedapal.scr.core.beans.ReporteForm312Bean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IFormulario312Dao;
import pe.com.sedapal.scr.core.services.IFormulario312Service;

// TODO: Auto-generated Javadoc
/**
 * The Class Formulario312ServiceImpl.
 */
@Service
public class Formulario312ServiceImpl implements IFormulario312Service {
	private static final Logger LOG = LoggerFactory.getLogger(Formulario312ServiceImpl.class);
	/** The i formulario 312 dao. */
	@Autowired
	private IFormulario312Dao iFormulario312Dao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IFormulario312Service#generarReporteResumenGrafico(java.lang.String, java.lang.String, int)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ReporteForm312Bean generarReporteResumenGrafico(String pFechaIni, String pFechaFin, int pTipo) throws Exception {
		
		ReporteForm312Bean wrapper = new ReporteForm312Bean();
		Map<String, List> mapa = new HashMap <>();
		List<Formulario312> lista;
		Formulario314	formulario314;
		List<Reporte312Bean> lstresult = new ArrayList<>();
		Reporte312Bean result = null;
		

		formulario314 = iFormulario312Dao.getCalculoFormulario314(pFechaIni, pFechaFin, pTipo);
		lista         = iFormulario312Dao.getValoresRSD(pFechaIni, pFechaFin, pTipo);
		
		LOG.info("pFechaIni : " + pFechaIni + "; pFechaFin: " + pFechaFin + "; pTipo:"+ pTipo);
		LOG.info("formulario314 >> " + formulario314);
		LOG.info("lista >> " + lista);

		mapa.put("RSD", new ArrayList());
		mapa.put("DESVIACION", new ArrayList());
		mapa.put("MEDIA", new ArrayList());
		mapa.put("LCS", new ArrayList());
		mapa.put("LCAS", new ArrayList());
		mapa.put("LCAI", new ArrayList());
		mapa.put("LCI", new ArrayList());
		int count=0;
		for (Formulario312 rp : lista) {
			count++;
			
			Object obj = new Object[]{count, rp.getRsd()};			
			mapa.get("RSD").add(obj);	
			
			Object obj2 = new Object[]{count, formulario314.getValor1().replace(",", ".")};		//SE CAMBIA POR PUNTO PORQUE VIENE CON COMAS AL SER UN STRING	
			mapa.get("DESVIACION").add(obj2);	
			
			Object obj3 = new Object[]{count, formulario314.getValor2().replace(",", ".")};			
			mapa.get("MEDIA").add(obj3);		
			
			Object obj4 = new Object[]{count, formulario314.getValor3().replace(",", ".")};			
			mapa.get("LCS").add(obj4);	
			
			Object obj5 = new Object[]{count, formulario314.getValor4().replace(",", ".")};			
			mapa.get("LCAS").add(obj5);	
			
			Object obj6 = new Object[]{count, formulario314.getValor5().replace(",", ".")};			
			mapa.get("LCAI").add(obj6);	
			
			Object obj7 = new Object[]{count, formulario314.getValor6().replace(",", ".")};			
			mapa.get("LCI").add(obj7);	
		}
						
		for (String strKey : mapa.keySet()) {
			result = new Reporte312Bean();
			result.setLabel(strKey);
			result.setData(mapa.get(strKey));
			lstresult.add(result);
		}
				
		wrapper.setStrEjexTitulo(ConstantsLaboratorio.OBSERVACIONES);
		wrapper.setIntNroObservaciones(ConstantsLaboratorio.REP_GRAF1_NRO_OBS_DEFAULT);		
		
		wrapper.setStrEjeyTitulo(ConstantsLaboratorio.REP_GRAF1_Y_TITULO);
		wrapper.setLstDataReporte(lstresult);
		wrapper.setIntCount(lstresult!=null?lstresult.size():30);
		
		return wrapper;
	}

}
