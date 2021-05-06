/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.AlmacenamientoBean;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CalculoRepresasBean;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.DetalleCatalogoBean;
import pe.com.sedapal.scr.core.beans.FilaReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.InfoReporteRepresasBean;
import pe.com.sedapal.scr.core.beans.ReporteBean;
import pe.com.sedapal.scr.core.beans.ReportePivotBean;
import pe.com.sedapal.scr.core.beans.ReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.ReporteWrapperBean;
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.beans.Result;
import pe.com.sedapal.scr.core.beans.pivote.Column;
import pe.com.sedapal.scr.core.beans.pivote.Item;
import pe.com.sedapal.scr.core.beans.pivote.PivotSet;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoDao;
import pe.com.sedapal.scr.core.dao.ICatalogoDao;
import pe.com.sedapal.scr.core.dao.IMantClimaDao;
import pe.com.sedapal.scr.core.dao.IMantColaboradorDao;
import pe.com.sedapal.scr.core.dao.IMantRepresaDao;
import pe.com.sedapal.scr.core.dao.IRepresasDao;
import pe.com.sedapal.scr.core.exception.ValidationFileException;
import pe.com.sedapal.scr.core.services.IRepresasService;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class RepresasServiceImpl.
 */
@Service
public class RepresasServiceImpl implements IRepresasService {

	/** The i represas dao. */
	@Autowired
	private IRepresasDao iRepresasDao;
	
	/** The mant represa dao. */
	@Autowired
	private IMantRepresaDao mantRepresaDao;
	
	/** The mant colaborador dao. */
	@Autowired
	private IMantColaboradorDao mantColaboradorDao;
	
	/** The almacenamiento dao. */
	@Autowired
	private IAlmacenamientoDao almacenamientoDao;
	
	/** The catalogo dao. */
	@Autowired
	private ICatalogoDao catalogoDao;
	
	/** The mant clima dao. */
	@Autowired
	private IMantClimaDao mantClimaDao;
	
	/**
	 * Método que permite obtener el listado de Represas.
	 *
	 * @param represasBean the represas bean
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result buscarRepresas(RepresasBean represasBean, Paginacion paginacion) throws Exception {
		return iRepresasDao.buscarRepresas(represasBean, paginacion);
	}

	/**
	 * Método privado que permite completar datos del reporte gráfico.
	 *
	 * @param represasData Contiene la información de represas por día
	 * @param periodo del cual se generará el reporte
	 * @param tipoRep Representa el tipo de reporte (volumen o precipitación)
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos de tipo ReporteRepresaBean con la información para el reporte
	 */
	private List<ReporteRepresaBean> completeDataReporteRepresa(Map<String, Map<String, BigDecimal>> represasData,
			String periodo, String tipoRep) throws Exception{
		
		List<ReporteRepresaBean> data = new ArrayList<>();
		int intNumDays = Utils.daysOfMonthFromPeriod(periodo);
		if(Utils.isActualPeriod(periodo)){
			intNumDays = Utils.getActualDate();
		}
		
		for (String strKey : represasData.keySet()) {
			String[] parts = strKey.split("-");
			Map<String, BigDecimal> datosDia = represasData.get(strKey);
			
			for(int i=1; i<=intNumDays; i++){
				ReporteRepresaBean repBean = new ReporteRepresaBean();
				repBean.setCodMantRepresa(Long.parseLong(parts[0]));
				repBean.setNombreRepresa(parts[1]);
				String diaFormat = String.format("%02d", i);
				repBean.setDia(diaFormat);
				BigDecimal value = datosDia.get(diaFormat) == null ? new BigDecimal(0) : datosDia.get(diaFormat);
				if(tipoRep.equals(Constants.REPORT_TYPE_VOLUMEN)){
					repBean.setPromVolumen(value);
				}
				else if(tipoRep.equals(Constants.REPORT_TYPE_PRECIPITACION)){
					repBean.setPromPrecitpitaciones(value);
				}
				data.add(repBean);
			}
		}
		
		return data;
	}
	
	/**
	 * Método privado que permite completar datos del reporte gráfico por maniobra.
	 *
	 * @param represasData Contiene la información de represa por maniobra por día
	 * @param periodo del cual se generará el reporte
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos de tipo ReporteRepresaBean con la información para el reporte
	 */
	private List<ReporteRepresaBean> completeDataReporteRepresaManiobra(Map<String, Map<String, BigDecimal>> represasData,
			String periodo) throws Exception{
		
		List<ReporteRepresaBean> data = new ArrayList<>();
		int intNumDays = Utils.daysOfMonthFromPeriod(periodo);
		if(Utils.isActualPeriod(periodo)){
			intNumDays = Utils.getActualDate();
		}
		
		for (String strKey : represasData.keySet()) {
			String tipoManiobra = strKey; 
			Map<String, BigDecimal> datosDia = represasData.get(tipoManiobra);
			
			for(int i=1; i<=intNumDays; i++){
				Integer codManiobra = Integer.parseInt(tipoManiobra);
				ReporteRepresaBean repBean = new ReporteRepresaBean();
				repBean.setTipoManiobra(codManiobra);
				repBean.setStrNombreManiobra(Constants.MANIOBRA_TYPE[codManiobra-1]);
				String diaFormat = String.format("%02d", i);
				repBean.setDia(diaFormat);
				BigDecimal value = datosDia.get(diaFormat) == null ? new BigDecimal(0) : datosDia.get(diaFormat);
				repBean.setBdValorManiobra(value);
				data.add(repBean);
			}
		}
		
		return data;
	}
	
	/**
	 * Obtiene la información necesaria para generar el reporte gráfico de caudal por maniobra.
	 *
	 * @param periodo para el cual se desea generar el reporte gráfico
	 * @param maniobras el es el listado de códigos de maniobra para los cuales se generará el reporte
	 * @return objeto del tipo ReporteWrapperBean que contiene los datos y parámetros del reporte gráfico
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ReporteWrapperBean generarReporteRepManiobraGrafico(String periodo, String[] maniobras)
			throws Exception {
		boolean bolFiltraRepresas = (maniobras!=null && periodo!=null && !periodo.trim().equals(""));
		ReporteWrapperBean wrapper = new ReporteWrapperBean();
		Map<String, List> mapa = new HashMap <String, List>();
		List<ReporteRepresaBean> lista = new ArrayList<ReporteRepresaBean>();
		List<ReporteBean> lstresult = new ArrayList<>();
		ReporteBean result = null;
		
		try {
			if(bolFiltraRepresas){
				lista = iRepresasDao.generarReportePorRepresaManiobra(periodo);
				
				Map<String, Map<String, BigDecimal>> mapToCompleteDays = new HashMap<String, Map<String, BigDecimal>>();
				for(ReporteRepresaBean rrb : lista){
					String day = rrb.getDia().trim();
					Integer tipoManiobra = rrb.getTipoManiobra();
					String key = tipoManiobra+"";
					if(!mapToCompleteDays.containsKey(key)){
						mapToCompleteDays.put(key, new HashMap<String, BigDecimal>());
					}
					mapToCompleteDays.get(key).put(day, rrb.getBdValorManiobra());
				}
				// Ahora recorremos los maps para completar dias
				lista = this.completeDataReporteRepresaManiobra(mapToCompleteDays, periodo);
			}
			
			for (ReporteRepresaBean ra : lista) {
				if(Utils.valueInCollection(maniobras, ra.getTipoManiobra()+"")){
					Object obj = new Object[]{ra.getDia().trim(), ra.getBdValorManiobra().toString()};
					if(!mapa.containsKey(ra.getStrNombreManiobra())){
						mapa.put(ra.getStrNombreManiobra(), new ArrayList());
					}
					mapa.get(ra.getStrNombreManiobra()).add(obj);
				}
			}
			
			for (String strKey : mapa.keySet()) {
				result = new ReporteBean();
				result.setLabel(strKey);
				result.setData(mapa.get(strKey));
				lstresult.add(result);
			}
			
			// Obtenermos mes y anio y num de dias
			if(periodo!=null&&periodo.trim().length() == 6){
				String anio = periodo.substring(0, 4);
				String mes = periodo.substring(4, periodo.length());
				String mesTexto = Utils.getMonthName(mes);
				wrapper.setStrEjexTitulo(Constants.REP_GRAF3_X_TITULO.
						replace(Constants.PARAM2, Utils.getMonthName(mesTexto)).
						replace(Constants.PARAM3, anio));
				wrapper.setIntDiasPeriodo(Utils.daysOfMonthFromPeriod(periodo.trim()));
			}
			else{
				wrapper.setStrEjexTitulo(Constants.REP_GRAF3_X_TITULO_UNDEFINED);
				wrapper.setIntDiasPeriodo(Constants.REP_GRAF3_DIAS_DEFAULT);
			}
			
			wrapper.setStrEjeyTitulo(Constants.REP_GRAF3_Y_TITULO);
			
			wrapper.setLstDataReporte(lstresult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wrapper;
	}
	
	/**
	 * Obtiene la información necesaria para generar el reporte gráfico (tanto de volumen como de precipitación).
	 *
	 * @param periodo para el cual se desea generar el reporte gráfico
	 * @param represas es el listado de códigos de represas para los cuales se desea generar el reporte gráfico
	 * @param tipoRep the tipo rep
	 * @return objeto del tipo ReporteWrapperBean que contiene los datos y parámetros del reporte gráfico
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ReporteWrapperBean generarReporteRepresaGrafico(String periodo, String[] represas, String tipoRep) throws Exception {
		boolean bolFiltraRepresas = (represas!=null && periodo!=null && !periodo.trim().equals(""));
		ReporteWrapperBean wrapper = new ReporteWrapperBean();
		Map<String, List> mapa = new HashMap <String, List>();
		List<ReporteRepresaBean> lista = new ArrayList<ReporteRepresaBean>();
		List<ReporteBean> lstresult = new ArrayList<>();
		ReporteBean result = null;
		try {
		
			if(bolFiltraRepresas){
				lista = iRepresasDao.generarReportePorRepresa(periodo);
				
				Map<String, Map<String, BigDecimal>> mapToCompleteDays = new HashMap<String, Map<String, BigDecimal>>();
				for(ReporteRepresaBean rrb : lista){
					String day = rrb.getDia().trim();
					String cod = String.valueOf(rrb.getCodMantRepresa());
					String nombre = rrb.getNombreRepresa();
					String key = cod + "-" + nombre;
					if(!mapToCompleteDays.containsKey(key)){
						mapToCompleteDays.put(key, new HashMap<String, BigDecimal>());
					}
					if(tipoRep.equals(Constants.REPORT_TYPE_VOLUMEN)){
						mapToCompleteDays.get(key).put(day, rrb.getPromVolumen());
					}
					else if(tipoRep.equals(Constants.REPORT_TYPE_PRECIPITACION)){
						mapToCompleteDays.get(key).put(day, rrb.getPromPrecitpitaciones());
					}
				}
				// Ahora recorremos los maps paa completar dias
				lista = this.completeDataReporteRepresa(mapToCompleteDays, periodo, tipoRep);
			}
			
			for (ReporteRepresaBean ra : lista) {
				if(Utils.valueInCollection(represas, ra.getCodMantRepresa()+"")){
					Object obj = null;
					if(tipoRep.equals(Constants.REPORT_TYPE_VOLUMEN)){
						obj = new Object[]{ra.getDia().trim(), ra.getPromVolumen().toString()};
					}
					else if(tipoRep.equals(Constants.REPORT_TYPE_PRECIPITACION)){
						obj = new Object[]{ra.getDia().trim(), ra.getPromPrecitpitaciones().toString()};
					}
					if(!mapa.containsKey(ra.getNombreRepresa())){
						mapa.put(ra.getNombreRepresa(), new ArrayList());
					}
					mapa.get(ra.getNombreRepresa()).add(obj);
				}
			}
			
			for (String strKey : mapa.keySet()) {
				result = new ReporteBean();
				result.setLabel(strKey);
				result.setData(mapa.get(strKey));
				lstresult.add(result);
			}
			
			// Obtenermos mes y anio y num de dias
			if(periodo!=null&&periodo.trim().length() == 6){
				String anio = periodo.substring(0, 4);
				String mes = periodo.substring(4, periodo.length());
				String mesTexto = Utils.getMonthName(mes);
				wrapper.setStrEjexTitulo(Constants.REP_GRAF2_X_TITULO.replace(Constants.PARAM1, mesTexto).
						replace(Constants.PARAM2, anio));
				wrapper.setIntDiasPeriodo(Utils.daysOfMonthFromPeriod(periodo.trim()));
			}
			else{
				wrapper.setStrEjexTitulo(Constants.REP_GRAF2_X_TITULO_UNDEFINED);
				wrapper.setIntDiasPeriodo(Constants.REP_GRAF2_DIAS_DEFAULT);
			}
			
			if(tipoRep.equals(Constants.REPORT_TYPE_VOLUMEN)){
				wrapper.setStrEjeyTitulo(Constants.REP_GRAF2_VOL_Y_TITULO);
			}
			else if(tipoRep.equals(Constants.REPORT_TYPE_PRECIPITACION)){
				wrapper.setStrEjeyTitulo(Constants.REP_GRAF2_PRE_Y_TITULO);
			}
			
			wrapper.setLstDataReporte(lstresult);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wrapper;
	}

	/**
	 * Valida la información contenida en el archivo de importación de represas.
	 *
	 * @param datosExcel es una estructura de datos que representa la información del excel como una matriz de strings
	 * @return estructura de datos que enmascara dos matrices de datos, una con la información correcta a importar y la otra con los
	 * registros que no serán importados porque no pasó todas las validaciones
	 * @throws ValidationFileException Excepción customizada que será lanzada según las validaciones
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Map<String,List<List<String>>> validaInformacionImportar(List<List<String>> datosExcel) throws ValidationFileException, Exception {
		List<List<String>> dataTemp = datosExcel;
		List<List<String>> data = new ArrayList<List<String>>();
		
		// SI ES QUE HAY FILAS VACIAS, LAS ELIMINAMOS
		for(List<String> datoRepresa : dataTemp){
			boolean haveData = false;
			for(String cell : datoRepresa){
				if(cell.trim().length() > 0){
					haveData = true;
				}
			}
			if(haveData){
				data.add(datoRepresa);
			}
		}
		
		// VALIDAMOS NUMERO DE FILAS
		if(data.size() > 0){
			if(data.size() > Constants.NRO_MAX_ROWS_TO_INSERT + 1){
				throw new ValidationFileException(Constants.IMP_REPRESAS_EXCEED_MAX);
			}
		}
		else{
			throw new ValidationFileException(Constants.IMP_REPRESAS_NO_DATOS);
		}
		
		List<List<String>> badData = new ArrayList<>();
		List<List<String>> goodData = new ArrayList<>();
		Map<String, List<List<String>>> mapData = new HashMap<>();
		List<RepresaBean> represasActivas = mantRepresaDao.obtenerRepresas(Constants.ACTIVE_STATE);
		List<ColaboradorBean> colaboradoresActivos = mantColaboradorDao.obtenerColaboradores(Constants.ACTIVE_STATE);
		List<ClimaBean> climasActivos = mantClimaDao.obtenerClimas(Constants.ACTIVE_STATE);
		Map<String,RepresaBean> mapRep = new HashMap<String,RepresaBean>();
		Map<String,ColaboradorBean> mapCol = new HashMap<String,ColaboradorBean>();
		Map<String,ClimaBean> mapCli = new HashMap<String,ClimaBean>();
		for (RepresaBean i : represasActivas) {
			mapRep.put(i.getAbreviatura().toUpperCase().trim(),i);
		}
		for (ColaboradorBean i : colaboradoresActivos) {
			mapCol.put(i.getAbreviatura().toUpperCase()+"",i);
		}
		for(ClimaBean i : climasActivos){
			mapCli.put(i.getAbreviatura().toUpperCase().trim(), i);
		}
		
		//No validamos la cabecera
		data.remove(0);
		for(int i=0; i<data.size(); i++){
			List<String> badDataFila = new ArrayList<>();
			badDataFila.add(String.format("%02d", i+2));
			List<String> dataFila = data.get(i);
			for(int j=0; j<dataFila.size(); j++){
				String value = dataFila.get(j);
				String tmpMax = "";
				String cantidadRepresasDia = "";
				try {
					int codRepresa = mapRep.get(dataFila.get(Constants.COL_NOMBRE_REPRESA-1)).getIntCodigo();
					String fechaReg = dataFila.get(Constants.COL_FECHA_REGISTRO-1);
					cantidadRepresasDia = iRepresasDao.obtenerCatidadRepresasActivaPorFecha(fechaReg, codRepresa)+"";
					tmpMax = dataFila.get(Constants.COL_TEMPERATURA_MAX-1);
				} catch (Exception e) {
				}
				String error = this.getErrorValidation(j+1, value, mapRep, mapCol, mapCli, tmpMax, cantidadRepresasDia);
				if(error.length() > 0){
					badDataFila.add(String.format("%02d", (j+1)));
					badDataFila.add(value);
					badDataFila.add(error);
					break;
				}
			}
			if(badDataFila.size() >1){
				badData.add(badDataFila);
			}
			else{
				goodData.add(dataFila);
			}
		}
		
		mapData.put("badData", badData);
		mapData.put("goodData", goodData);
		return mapData;
	}
	
	/**
	 * Método privado que permite obtener el error de una fila según una validación específica.
	 *
	 * @param col Es el número de columna de la fila que se está validando
	 * @param val Es el valor de la celda del excel a importar
	 * @param represas Contiene la información de represas que pueden ser consideradas en la importación
	 * @param colaboradores Contiene la información de colaboradores que pueden ser consideradas en la importación
	 * @param climas Contiene la información de climas que pueden ser consideradas en la importación
	 * @param tempMax Es el valor de la columna Temperatura Máxima de la fila para validar con la Temperatura Mínima
	 * @param cantRepresasDia the cant represas dia
	 * @return the error validation
	 * @Return Objeto String con la información o mensaje de error en caso lo hubiese
	 */
	private String getErrorValidation(int col, String val, Map<String,RepresaBean> represas,
			Map<String,ColaboradorBean> colaboradores, Map<String, ClimaBean> climas, String tempMax,
			String cantRepresasDia){
		String errorMessage = "";
		if(val != null){
			val = val.trim();
		}
		if(col==Constants.COL_FECHA_REGISTRO){    
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_FECHA_REGISTRO);
			}
			else if(!Utils.isValidDate(val, null)){
				return Constants.IMP_MENSAJE_VALIDA_FECHA.replace(Constants.PARAM1, Constants.NOM_COL_FECHA_REGISTRO);
			}
			else if(Utils.compareWithToday(val, null) > 0){
				return Constants.IMP_MENSAJE_VALIDA_FUTURO.replace(Constants.PARAM1, Constants.NOM_COL_FECHA_REGISTRO);
			}
			else if(Integer.parseInt(cantRepresasDia) > 0){
				return Constants.IMP_MENSAJE_DUPLICATED;
			}
		}
		else if(col==Constants.COL_NOMBRE_REPRESA){ // ABREVIATURA
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_NOMBRE_REPRESA);
			}
			else {
				if(represas.get(val.toUpperCase()) == null){
					return Constants.IMP_MENSAJE_EXIST_REGISTRO.replace(Constants.PARAM1, Constants.NOM_COL_NOMBRE_REPRESA);
				}
			}
		}	
		else if(col==Constants.COL_DESC_CLIMA){ // ABREVIATURA
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_DESC_CLIMA);
			}
			else{
				val = val.split("\\.")[0];
				if(climas.get(val.toUpperCase()) == null){
					return Constants.IMP_MENSAJE_EXIST_REGISTRO.replace(Constants.PARAM1, Constants.NOM_COL_DESC_CLIMA);
				}
			}
		}	
		else if(col==Constants.COL_COTA){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_COTA);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_COTA);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_COTA);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_4, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_COTA)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_4+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		} 			
		else if(col==Constants.COL_COLABORADOR){ // ABREVIATURA
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_COLABORADOR);
			}
			else{
				val = val.split("\\.")[0];
				if(colaboradores.get(val.toUpperCase()) == null){
					return Constants.IMP_MENSAJE_EXIST_REGISTRO.replace(Constants.PARAM1, Constants.NOM_COL_COLABORADOR);
				}
			}
		} 
		else if(col==Constants.COL_TEMPERATURA_MAX){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_TEMPERATURA_MAX);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_TEMPERATURA_MAX);
			}
			else if(!Utils.isValidDecimal((val.contains("-") ? val.split("-")[1] : val), Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_2)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_TEMPERATURA_MAX)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_2+"");
			}
		} 
		else if(col==Constants.COL_VOLUMEN){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_9, Constants.MAX_DIGITS_ALLOW_6)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_9+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_6+"");
			}
		} 		
		else if(col==Constants.COL_VOLUMEN_TOTAL){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN_TOTAL);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN_TOTAL);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN_TOTAL);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_9, Constants.MAX_DIGITS_ALLOW_6)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_VOLUMEN_TOTAL)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_9+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_6+"");
			}
		}
		else if(col==Constants.COL_CAUDAL_TRASVASADO){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL_TRASVASADO);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL_TRASVASADO);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL_TRASVASADO);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_8)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL_TRASVASADO)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_8+"");
			}
		} 
		else if(col==Constants.COL_TEMP_MIN){
			// SE DEBE VALIDAR QUE SEA MENOR A LA TEMPERATURA MAXIMA
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_TEMP_MIN);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_TEMP_MIN);
			}
			else if(!Utils.isValidDecimal((val.contains("-") ? val.split("-")[1] : val), Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_2)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_TEMP_MIN)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_2+"");
			}
			else if(new BigDecimal(val).compareTo(new BigDecimal(tempMax)) == 1){
				return Constants.IMP_MENSAJE_VALIDA_TEMPERATURAS.replace(Constants.PARAM1, Constants.NOM_COL_TEMP_MIN)
						.replace(Constants.PARAM2, Constants.NOM_COL_TEMPERATURA_MAX);
			}
		} 
		else if(col==Constants.COL_LLUVIAS){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_LLUVIAS);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_LLUVIAS);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_LLUVIAS);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_LLUVIAS)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		} 		
		else if(col==Constants.COL_DIFERENCIAL_NIVEL){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIAL_NIVEL);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIAL_NIVEL);
			}
			else if(!Utils.isValidDecimal((val.contains("-") ? val.split("-")[1] : val), Constants.MAX_DIGITS_ALLOW_8, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIAL_NIVEL)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_8+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		} 
		else if(col==Constants.COL_DIFERENCIA_VOLUMEN){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIA_VOLUMEN);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIA_VOLUMEN);
			}
			else if(!Utils.isValidDecimal((val.contains("-") ? val.split("-")[1] : val), Constants.MAX_DIGITS_ALLOW_8, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_DIFERENCIA_VOLUMEN)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_8+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		}
		else if(col==Constants.COL_HUMEDAD_RELATIVA){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_HUMEDAD_RELATIVA);
			}
			else if(!StringUtils.isNumeric(val.contains("-") ? val.split("-")[1] : val)){
				return Constants.IMP_MENSAJE_VALIDA_ENTERO.replace(Constants.PARAM1, Constants.NOM_COL_HUMEDAD_RELATIVA);
			}
			else if(Integer.parseInt(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_HUMEDAD_RELATIVA);
			}
			else if(val.length() > Constants.MAX_DIGITS_ALLOW_5){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_ENTERO.replace(Constants.PARAM1, Constants.NOM_COL_HUMEDAD_RELATIVA)
						.replace(Constants.PARAM1, Constants.MAX_DIGITS_ALLOW_5+"");
			}
		}
		else if(col==Constants.COL_EVAPORACION){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_EVAPORACION);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_EVAPORACION);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_EVAPORACION);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_EVAPORACION)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		}		
		else if(col==Constants.COL_HY){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_HY);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_HY);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_HY);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_HY)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		}				
		else if(col==Constants.COL_CAUDAL){
			if(StringUtils.isEmpty(val)){
				return Constants.IMP_MENSAJE_VALIDA_VACIO.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL);
			}
			else if(!NumberUtils.isNumber(val)){
				return Constants.IMP_MENSAJE_VALIDA_NUMERICO.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL);
			}
			else if(Double.parseDouble(val) < 0){
				return Constants.IMP_MENSAJE_VALIDA_POSITIVOS.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL);
			}
			else if(!Utils.isValidDecimal(val, Constants.MAX_DIGITS_ALLOW_2, Constants.MAX_DIGITS_ALLOW_4)){
				return Constants.IMP_MENSAJE_VALIDA_LONGITUD_DECIMAL.replace(Constants.PARAM1, Constants.NOM_COL_CAUDAL)
						.replace(Constants.PARAM2, Constants.MAX_DIGITS_ALLOW_2+"").replace(Constants.PARAM3, Constants.MAX_DIGITS_ALLOW_4+"");
			}
		}	
		return errorMessage;
	}
	
	/**
	 * Realiza el registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public RepresasBean registrarRepresas(RepresasBean represasBean) throws Exception {
		represasBean.setStrFactorDescarga(Constants.REPRESAS_FACTOR_DESCARGA);
		represasBean.setIntUsaFactorDescarga(Constants.ATTRIBUTE_NEGATIVE);
		represasBean.setStrFactorValor(Constants.REPRESAS_FACTOR_DESCARGA_VALUE + "");
		represasBean.setStrFormulaVolumenTrasvasado(represasBean.getStrCaudalTrasvasado()+""+this.obtenerFormulaVolumenTrasvasado());
		return iRepresasDao.registrarRepresas(represasBean);		
	}

	/**
	 * Realiza la modificación de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override	
	public void actualizarRepresas(RepresasBean represasBean) throws Exception {
		represasBean.setStrFormulaVolumenTrasvasado(represasBean.getStrCaudalTrasvasado()+""+this.obtenerFormulaVolumenTrasvasado());
		iRepresasDao.actualizarRepresas(represasBean);			
	}
	
	/**
	 * Realiza la modificación de factor de descarga represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override	
	public void actualizarFactorRepresas(RepresasBean represasBean) throws Exception {
		iRepresasDao.actualizarFactorRepresas(represasBean);			
	}
	
	/**
	 * Realiza la modificación de las formulas de represas menos del factor de represas.
	 *
	 * @param calculoRepresasBean objeto del tipo CalculoRepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarFormulasRepresas(CalculoRepresasBean calculoRepresasBean) throws Exception {
		String valorVolumen = Utils.evalMathExpression(calculoRepresasBean.getStrFormulaVolumenTrasvasado());
		calculoRepresasBean.setBdValorVolumenTrasvasado(new BigDecimal(valorVolumen).setScale(8, BigDecimal.ROUND_HALF_EVEN));
		iRepresasDao.actualizarFormulasRepresas(calculoRepresasBean);
	}
	
	/**
	 * Realiza el cambio de estado de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarRepresas(RepresasBean represasBean) throws Exception {
		iRepresasDao.inactivarRepresas(represasBean);			
	}

	/**
	 * Valida la extensión de un archivo.
	 *
	 * @param strFileName es el nombre completo del archivo
	 * @return objeto de tipo String que contiene la información de la validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public String validaExtensionFile(String strFileName) throws Exception {
		String errorMessage = "";
		String strFileNameParts[] = strFileName.split("\\.");
		// Validamos la extensión del archivo
		if (!strFileNameParts[strFileNameParts.length - 1].equals("xls")) {
			errorMessage = "El archivo cargado no presenta la extensión XLS";
		}
		return errorMessage;
	}

	/**
	 *  
	 * Obtiene el registro de represas por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo RepresasBean que contiene el registro
	 */
	@Override
	public RepresasBean obtenerRepresas(Integer nid) throws Exception {
		RepresasBean bean = iRepresasDao.obtenerRepresas(nid);
		//bean.setStrAforoRio(this.obtenerAforoRioRimac(bean.getStrFechaRegistro()));
		return bean;
	}
	
	/**
	 * Obtiene la lista de los colaboradores registrados en la plataforma según su estado.
	 *
	 * @param estado representa el estado del registro de represas
	 * @return Lista de objetos de tipo ColaboradorBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<ColaboradorBean> obtenerColaboradores(String estado) throws Exception{
		return mantColaboradorDao.obtenerColaboradores(estado);
	}
	
	/**
	 * Obtiene la lista de los climas registrados en la plataforma según su estado.
	 *
	 * @param estado representa el estado del registro de climas
	 * @return Lista de objetos de tipo ClimaBean que contiene los datos del clima
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<ClimaBean> obtenerClimas(String estado) throws Exception{
		return mantClimaDao.obtenerClimas(estado);
	}

	/**
	 * Realiza la inserción de un listado de represas.
	 *
	 * @param represas es la estructura matriz con la infromación de las represas a insertar
	 * @param auditoria es el objeto que contiene los datos de auditoría
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	@Transactional
	public void registrarRepresasImport(List<List<String>> represas, BaseBean auditoria) throws Exception {
		List<ColaboradorBean> colaboradoresActivos = mantColaboradorDao.obtenerColaboradores(Constants.ACTIVE_STATE);
		List<RepresaBean> represasActivas = mantRepresaDao.obtenerRepresas(Constants.ACTIVE_STATE);
		List<ClimaBean> climasActivos = mantClimaDao.obtenerClimas(Constants.ACTIVE_STATE);
		Map<String,RepresaBean> mapRep = new HashMap<String,RepresaBean>();
		Map<String,ClimaBean> mapCli = new HashMap<String,ClimaBean>();
		Map<String,ColaboradorBean> mapCol = new HashMap<String,ColaboradorBean>();
		for (ColaboradorBean i : colaboradoresActivos) {
			mapCol.put(i.getAbreviatura().toUpperCase()+"",i);
		}
		for (RepresaBean i : represasActivas) {
			mapRep.put(i.getAbreviatura().toUpperCase().trim(),i);
		}
		for (ClimaBean i : climasActivos) {
			mapCli.put(i.getAbreviatura().toUpperCase().trim(),i);
		}
		List<List<String>> data = represas;
		for(List<String> d : data){
			List<String> fila = d;
			RepresasBean bean = new RepresasBean();
			for(int i=0; i<fila.size(); i++){
				if(Constants.COL_FECHA_REGISTRO == (i+1)){
					// dd/MM/yyyy
					bean.setStrFechaRegistro(fila.get(i).trim());
				}
				else if(Constants.COL_NOMBRE_REPRESA == (i+1)){
					bean.setStrCodigoRepresa(mapRep.get(fila.get(i).trim().toUpperCase()).getIntCodigo()+"");
				}
				else if(Constants.COL_DESC_CLIMA == (i+1)){
					bean.setStrCodigoClima(mapCli.get(fila.get(i).trim().toUpperCase()).getLonCodigo()+"");
				}
				else if(Constants.COL_COTA == (i+1)){
					bean.setStrCota(fila.get(i).trim());
				}
				else if(Constants.COL_COLABORADOR == (i+1)){
					bean.setStrCodigoColaborador(mapCol.get(fila.get(i).trim().toUpperCase()).getLonCodigo()+"");
				}
				else if(Constants.COL_TEMPERATURA_MAX == (i+1)){
					bean.setStrTemperaturaMaxima(fila.get(i).trim());
				}
				else if(Constants.COL_VOLUMEN == (i+1)){
					bean.setStrVolumen(fila.get(i).trim());
				}
				else if(Constants.COL_VOLUMEN_TOTAL == (i+1)){
					bean.setStrVolumenTotal(fila.get(i).trim());
				}
				else if(Constants.COL_CAUDAL_TRASVASADO == (i+1)){
					bean.setStrCaudalTrasvasado(fila.get(i).trim());
				}
				else if(Constants.COL_TEMP_MIN == (i+1)){
					bean.setStrTemperaturaMinima(fila.get(i).trim());
				}
				else if(Constants.COL_LLUVIAS == (i+1)){
					bean.setStrLluvia(fila.get(i).trim());
				}
				else if(Constants.COL_DIFERENCIAL_NIVEL == (i+1)){
					bean.setStrDiferenciaNivel(fila.get(i).trim());
				}
				else if(Constants.COL_DIFERENCIA_VOLUMEN == (i+1)){
					bean.setStrDiferenciaVolumen(fila.get(i).trim());
				}
				else if(Constants.COL_HUMEDAD_RELATIVA == (i+1)){
					bean.setIntHumedadRelativa(Integer.parseInt(fila.get(i).trim()));
				}
				else if(Constants.COL_EVAPORACION == (i+1)){
					bean.setStrEvaporacion(fila.get(i).trim());
				}
				else if(Constants.COL_HY == (i+1)){
					bean.setStrHy(fila.get(i).trim());
				}
				else if(Constants.COL_CAUDAL == (i+1)){
					bean.setStrCaudal(fila.get(i).trim());
				}
			}
			// Seteamos el aforo
			bean.setStrAforoRio(obtenerAforoRioRimac(bean.getStrFechaRegistro()));
			//
			bean.setStrFactorDescarga(Constants.REPRESAS_FACTOR_DESCARGA);
			bean.setIntUsaFactorDescarga(Constants.ATTRIBUTE_NEGATIVE);
			bean.setStrFactorValor(Constants.REPRESAS_FACTOR_DESCARGA_VALUE + "");
			bean.setStrDescarga(Constants.REPRESAS_DESCARGA_DEFAULT);
			// Calculamos el volumen trasvasado
			String formulaVolumenTrasvasado = this.obtenerFormulaVolumenTrasvasado(); // COMO POSTFIJO DE LA FORMULA
			String formulaVolumenTrasvasadoCompleta = bean.getStrCaudalTrasvasado()+""+formulaVolumenTrasvasado;
			String voltra = Utils.evalMathExpression(formulaVolumenTrasvasadoCompleta);
			BigDecimal voltrasv = new BigDecimal(voltra);
			voltrasv = voltrasv.setScale(8, BigDecimal.ROUND_HALF_EVEN);
			bean.setStrVolumenTrasvasado(voltrasv.toString());
			bean.setStrFormulaVolumenTrasvasado(formulaVolumenTrasvasadoCompleta);
			//
			bean.setStrEstado(Constants.ACTIVE_STATE);
			bean.setPrograma(auditoria.getPrograma());
			bean.setUsuarioCreacion(auditoria.getUsuarioCreacion());
			bean.setUsuarioModificacion(auditoria.getUsuarioModificacion());
			bean.setCodigoSistema(auditoria.getCodigoSistema());
			bean.setCodigoArea(auditoria.getCodigoArea());
			
			iRepresasDao.registrarRepresas(bean);
		}
		
	}
	
	/**
	 * Obtiene la cantidad de registros de represas activos para la represa y fecha consultados.
	 *
	 * @param strFecha Es la fecha de la que se desea consultar (dd/mm/yyyy)
	 * @param intCodRepresa Es el codigo de la represa de la que se desea consultar
	 * @return Objeto de tipo Integer que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public String obtenerCatidadRepresasActivaPorFecha(String strFecha, Integer intCodRepresa) throws Exception {
		return iRepresasDao.obtenerCatidadRepresasActivaPorFecha(strFecha, intCodRepresa)+"";
	}
	
	/**
	 * Obtiene el valor del aforo del río rímac.
	 *
	 * @param strFechaRegistro Es la fecha para la cual se requiere el aforo
	 * @return Objeto de tipo String que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public String obtenerAforoRioRimac(String strFechaRegistro) throws Exception {
		Map<String, String> datos = Utils.getDayAndPeriodFromDate(strFechaRegistro);
		BigDecimal aforo = iRepresasDao.obtenerAforoRioRimac(datos.get("periodo"), datos.get("dia"));
		return aforo.toString();
	}
	
	/**
	 * Obtiene el valor de la fórmula de volumen trasvasado.
	 *
	 * @return Objeto de tipo String que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public String obtenerFormulaVolumenTrasvasado() throws Exception {
		String formula = null;
		List<DetalleCatalogoBean> detalle = catalogoDao.obtenerDetalleCatalogo(Constants.CATALOGO_ABREVIATURA_FORM_TRASVASADO);
		for(DetalleCatalogoBean und : detalle){
			if(und.getStrAbreviatura().equals(Constants.CATALOGO_ABREVIATURA_FORMULA)){
				formula = und.getStrValor1();
				break;
			}
		}
		return formula;
	}
	
	/**
	 * Obtiene la información para el reporte resumen de represas por día.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReportePivotBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<FilaReporteRepresaBean> generarReporteResumenRepresa(String periodo) throws Exception {
		
		List<FilaReporteRepresaBean> datosReporte = new ArrayList<FilaReporteRepresaBean>();
		
		String represasExcluidasColUno = almacenamientoDao.obtenerRepresasExcluidasColumnaUno();
		List<RepresaBean> mantRepresas = mantRepresaDao.obtenerRepresas(Constants.ACTIVE_STATE);
		List<ReportePivotBean> listReporte = iRepresasDao.generarReporteResumenRepresa(periodo);
		List<AlmacenamientoBean> listAlmacenamiento = almacenamientoDao.obtenerDatosAcumulados(periodo);
		
		Map<String, FilaReporteRepresaBean> mapDiasConData = new HashMap<String, FilaReporteRepresaBean>();
		Map<Integer,RepresaBean> mapRepresa = new HashMap<Integer,RepresaBean>();
		List<String> lstNombresRepresasActivas = new ArrayList<String>();
		for (RepresaBean i : mantRepresas) {
			if(i.getPerteneceAlmacenamiento().equals(Constants.ATTRIBUTE_POSITIVE+"")){
				lstNombresRepresasActivas.add(i.getNombreRepresa());
			}
			mapRepresa.put(i.getIntCodigo(),i);
		}
		Map<String,AlmacenamientoBean> mapAlmacenamiento = new HashMap<String,AlmacenamientoBean>();
		for (AlmacenamientoBean alm : listAlmacenamiento) {
			mapAlmacenamiento.put(alm.getStrDia().trim(), alm);
		}
		// CREAMOS MAPA QUE GUARDARA EL AFORO (ES POR DIA ASI QUE BASTA QUE HAYA DATO, RECUPERAMOS)
		Map<String,BigDecimal> mapAforoDia = new HashMap<String,BigDecimal>();
		
		// 1. LLENAMOS LOS DATOS CON LA INFORMACION DE BD
		for(ReportePivotBean rpb : listReporte){
			if(!StringUtils.isEmpty(rpb.getStrAttributeVertical())){
				FilaReporteRepresaBean filaReporte = new  FilaReporteRepresaBean();
				
				filaReporte.setStrDia(rpb.getStrAttributeVertical());
				filaReporte.setBolExistData(true);
				
				JAXBContext jaxbContext = JAXBContext.newInstance(PivotSet.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				StringReader reader = new StringReader(rpb.getStrAttributeHorizontal());
				JAXBElement<PivotSet> ediElement = unmarshaller.unmarshal(new StreamSource(reader), PivotSet.class);
				PivotSet pivote = ediElement.getValue();
				
				List<Item> items = pivote.getItem();
				List<InfoReporteRepresasBean> lstDatosFila = new ArrayList<InfoReporteRepresasBean>();
				for(Item i : items){
					List<Column> columns = i.getColumn();
					InfoReporteRepresasBean infoReporte = new InfoReporteRepresasBean();
					infoReporte.setBolExistDataRepresa(true);
					infoReporte.setIntTipoColumna(Constants.TYPE_COLUMN_REPRESA);
					for(Column col : columns){
						
						if(col.getName().trim().equals(Constants.NAME_CODIGO_FROMDB)){
							infoReporte.setIntCodRepresa(Integer.parseInt(col.getValue()));
							infoReporte.setStrNombrePrincipalColumna(mapRepresa.get(Integer.parseInt(col.getValue())).getNombreRepresa());
						}
						else if(col.getName().trim().equals(Constants.NAME_IDREPRESAS_FROMDB)){
							infoReporte.setIntIdRepresas(Integer.parseInt(col.getValue()));
						}
						else if(col.getName().trim().equals(Constants.NAME_COTA_FROMDB)){
							String valCota = col.getValue().replace(",", ".");
							infoReporte.setStrCota(new BigDecimal(valCota).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_DESCARGA_FROMDB)){
							String valDescarga = col.getValue().replace(",", ".");
							infoReporte.setStrDescarga(new BigDecimal(valDescarga).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_VOLUMEN_FROMDB)){
							String valVolumen = col.getValue().replace(",", ".");
							infoReporte.setStrVolumen(new BigDecimal(valVolumen).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_VOLUMENTOTAL_FROMDB)){
							String valVolumenTotal = col.getValue().replace(",", ".");
							infoReporte.setStrVolumenTotalRep(new BigDecimal(valVolumenTotal).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_CAUDALTRASVASADO_FROMDB)){
							String valCauTra = col.getValue().replace(",", ".");
							infoReporte.setStrCaudalTrasvasado(new BigDecimal(valCauTra).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_VOLUMENTRASVASADO_FROMDB)){
							String valVolTra = col.getValue().replace(",", ".");
							infoReporte.setStrVolumenTrasvasado(new BigDecimal(valVolTra).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_FORMULAVOLUMENTRASVASADO_FROMDB)){
							infoReporte.setStrFormulaVolumenTrasvasado(col.getValue());
						}
						else if(col.getName().trim().equals(Constants.NAME_PRECIPITACION_FROMDB)){
							String valPrecipitacion = col.getValue().replace(",", ".");
							infoReporte.setStrPrecipitacion(new BigDecimal(valPrecipitacion).stripTrailingZeros().toPlainString());
						}
						else if(col.getName().trim().equals(Constants.NAME_TIEMPO_FROMDB)){
							infoReporte.setStrTiempoAtmosferico(col.getValue());
						}
						else if(col.getName().trim().equals(Constants.NAME_AFORO_FROMDB)){
							String valAforo = col.getValue().replace(",", ".");
							mapAforoDia.put(filaReporte.getStrDia(), new BigDecimal(valAforo));
						}

					}
					lstDatosFila.add(infoReporte);
				}
				
				filaReporte.setLstData(lstDatosFila);
				
				mapDiasConData.put(filaReporte.getStrDia(), filaReporte);
				
				datosReporte.add(filaReporte);
			}
		}
		
		// 2. COMPLETAMOS LA INFORMACION QUE NO TENGA DATOS
		datosReporte = this.completeData(datosReporte, mapRepresa, periodo, mapDiasConData);
		
		// 3. ORDENAMOS LA LISTA POR DIA
		Collections.sort(datosReporte, new Comparator<FilaReporteRepresaBean>() {
			@Override
			public int compare(FilaReporteRepresaBean o1, FilaReporteRepresaBean o2) {
				return o1.getStrDia().compareTo(o2.getStrDia());
			}
	    });
		
		// 4. ORDENAMOS LAS LISTAS POR NOMBRE
		for(FilaReporteRepresaBean dato : datosReporte){
			Collections.sort(dato.getLstData(), new Comparator<InfoReporteRepresasBean>() {
				@Override
				public int compare(InfoReporteRepresasBean o1, InfoReporteRepresasBean o2) {
					return o1.getStrNombrePrincipalColumna().compareTo(o2.getStrNombrePrincipalColumna());
				}
		    });
		}
		
		// 5. AHORA AGREGAMOS COLUMNAS DE ALMACENAMIENTO, VOLUMEN TOTAL, AFORO RIO RIMAC
		for(FilaReporteRepresaBean dato : datosReporte){
			AlmacenamientoBean almacenamientoDia= mapAlmacenamiento.get(dato.getStrDia().trim());
			
			String strRepresasAlmac = StringUtils.join(lstNombresRepresasActivas, " + ");
			
			// REGISTRO PARA COLUMNA DE ALMACENAMIENTO
			InfoReporteRepresasBean infoAlmacenamiento = new InfoReporteRepresasBean();
			infoAlmacenamiento.setIntTipoColumna(Constants.TYPE_COLUMN_ALMACENAMIENTO);
			infoAlmacenamiento.setStrNombrePrincipalColumna(Constants.REPRESAS_ALMACANEMIENTO_TITULO);
			infoAlmacenamiento.setStrRepresasAlmacenamiento(strRepresasAlmac);
			infoAlmacenamiento.setStrRepresasExcluidas(represasExcluidasColUno == null ? "" : represasExcluidasColUno);
			
			// REGISTRO PARA COLUMNA DE VOLUMEN TOTAL
			InfoReporteRepresasBean infoVolumenTotal = new InfoReporteRepresasBean();
			infoVolumenTotal.setIntTipoColumna(Constants.TYPE_COLUMN_VOLUMENTOTAL);
			infoVolumenTotal.setStrNombrePrincipalColumna(Constants.REPRESAS_VOLUMENTOTAL_TITULO);
			infoVolumenTotal.setStrNombrePrincipalColumnaJava(Constants.REPRESAS_VOLUMENTOTAL_TITULO_JAVA);
			infoVolumenTotal.setStrRepresasAlmacenamiento(strRepresasAlmac);
			infoVolumenTotal.setStrRepresasExcluidas(represasExcluidasColUno == null ? "" : represasExcluidasColUno);
			
			// REGISTRO PARA COLUMNA DE MANIOBRAS
			InfoReporteRepresasBean infoManiobras = new InfoReporteRepresasBean();
			infoManiobras.setIntTipoColumna(Constants.TYPE_COLUMN_MANIOBRAS);
			infoManiobras.setStrNombrePrincipalColumna(Constants.REPRESAS_MANIOBRAS_TITULO);
			infoManiobras.setStrRepresasAlmacenamiento(strRepresasAlmac);
			infoManiobras.setStrRepresasExcluidas(represasExcluidasColUno == null ? "" : represasExcluidasColUno);
			
			// REGISTRO PARA COLUMNA DE VALORES AGREGADOS
			InfoReporteRepresasBean infoAgregados = new InfoReporteRepresasBean();
			infoAgregados.setIntTipoColumna(Constants.TYPE_COLUMN_AGREGADOS);
			infoAgregados.setStrNombrePrincipalColumna(Constants.REPRESAS_AGREGADOS_TITULO);
			infoAgregados.setStrRepresasAlmacenamiento(strRepresasAlmac);
			infoAgregados.setStrRepresasExcluidas(represasExcluidasColUno == null ? "" : represasExcluidasColUno);
			
			if(null == almacenamientoDia){
				infoAlmacenamiento.setStrAlmacenamientoCantidad(Constants.CELL_WITHOUT_VALUE);
				infoAlmacenamiento.setStrAlmacenamientoPorcentaje(Constants.CELL_WITHOUT_VALUE);
				infoAlmacenamiento.setStrTotalEmbalseCantidad(Constants.CELL_WITHOUT_VALUE);
				infoAlmacenamiento.setStrTotalEmbalsePorcentaje(Constants.CELL_WITHOUT_VALUE);
				
				infoVolumenTotal.setStrVolumenTotal(Constants.CELL_WITHOUT_VALUE);
				
				infoManiobras.setStrCaudalRegulado(Constants.CELL_WITHOUT_VALUE);
				infoManiobras.setStrCaudalDescarga(Constants.CELL_WITHOUT_VALUE);
				infoManiobras.setStrCaudalNatural(Constants.CELL_WITHOUT_VALUE);
			}
			else{
				// AGREGAMOS LA FECHA
				dato.setStrFecha(almacenamientoDia.getStrFechaRegistro());
				dato.setStrFormulaAlmacenamiento(almacenamientoDia.getStrTextoFormulaAlmacenamiento());
				dato.setStrFormulaManDescarga(almacenamientoDia.getStrTextoFormulaManiobraDescarga());
				dato.setStrFormulaColumnaUno(almacenamientoDia.getStrTextoFormulaColumnaUno());
				dato.setStrFormulaColumnaDos(almacenamientoDia.getStrTextoFormulaColumnaDos());
				////
				
				infoAlmacenamiento.setStrAlmacenamientoCantidad(almacenamientoDia.getBdValorVolumen().toString());
				infoAlmacenamiento.setStrAlmacenamientoPorcentaje(almacenamientoDia.getBdPorcentajeVolumen().toString());
				infoAlmacenamiento.setStrTotalEmbalseCantidad(almacenamientoDia.getBdValorDescarga().toString());
				infoAlmacenamiento.setStrTotalEmbalsePorcentaje(almacenamientoDia.getBdPorcentajeDescarga().toString());
				
				infoVolumenTotal.setStrVolumenTotal(almacenamientoDia.getBdVolumenTotal().toString());
				
				infoManiobras.setStrCaudalRegulado(almacenamientoDia.getBdQRegulado().toString());
				infoManiobras.setStrCaudalDescarga(almacenamientoDia.getBdQDescarga().toString());
				infoManiobras.setStrCaudalNatural(almacenamientoDia.getBdQNatural().toString());
			}
			
			// REGISTRO PARA COLUMNA DE AFORO RIO RIMAC
			InfoReporteRepresasBean infoAforoRioRimac = new InfoReporteRepresasBean();
			infoAforoRioRimac.setIntTipoColumna(Constants.TYPE_COLUMN_AFORORIO);
			infoAforoRioRimac.setStrNombrePrincipalColumna(Constants.REPRESAS_AFORORIO_TITULO);
			infoAforoRioRimac.setStrNombrePrincipalColumnaJava(Constants.REPRESAS_AFORORIO_TITULO_JAVA);
			infoAforoRioRimac.setStrRepresasAlmacenamiento(strRepresasAlmac);
			infoAforoRioRimac.setStrAforoRioRimac(mapAforoDia.get(dato.getStrDia().trim()) == null ?
				Constants.CELL_WITHOUT_VALUE : mapAforoDia.get(dato.getStrDia().trim()).toString());
			
			if(null == almacenamientoDia){
				infoAgregados.setStrColumnaUno(Constants.CELL_WITHOUT_VALUE);
				infoAgregados.setStrColumnaDos(Constants.CELL_WITHOUT_VALUE);
			}
			else{
				infoAgregados.setStrColumnaUno(almacenamientoDia.getDbColumnaUno().toString());
				infoAgregados.setStrColumnaDos(almacenamientoDia.getDbColumnaDos().toString());
			}
			
			dato.getLstData().add(infoAlmacenamiento);
			dato.getLstData().add(infoVolumenTotal);
			dato.getLstData().add(infoManiobras);
			dato.getLstData().add(infoAforoRioRimac);
			dato.getLstData().add(infoAgregados);
		}
		
		return datosReporte;
	}
	
	/**
	 * Método privado que permite completar datos del reporte resumen de represas.
	 *
	 * @param listReaded Es la lista con los datos obtenidos de la base de datos
	 * @param represas Es un mapa con los datos de las maestras de represas activas
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @param mapDiasConData the map dias con data
	 * @return lista de objetos de tipo FilaReporteRepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "rawtypes"})
	private List<FilaReporteRepresaBean> completeData(List<FilaReporteRepresaBean> listReaded, 
			Map<Integer,RepresaBean> represas, String periodo, 
			Map<String, FilaReporteRepresaBean> mapDiasConData) throws Exception {
		
		GregorianCalendar fecha = (GregorianCalendar)DateUtils.getCalendar();
		String periodoActual = this.getPeriodo(fecha);
		int intDiaActual = -1;
		
		// Validamos si es el period actual o periodo pasado
		int intCompare = this.comparePeriod(periodoActual, periodo);
		if(intCompare == 0){//Periodo actual
			intDiaActual = this.getDia(fecha);
		}
		else{ // Periodo pasado
			intDiaActual = Utils.daysOfMonthFromPeriod(periodo);
		}
		
		
		for(int i=0; i<intDiaActual; i++){
			FilaReporteRepresaBean newFila = new FilaReporteRepresaBean();
			newFila.setStrDia(String.format("%02d", (i+1)));
			newFila.setStrFecha(null); // Para identificar que no tenemos
			newFila.setBolExistData(false);
			newFila.setLstData(new ArrayList<InfoReporteRepresasBean>());
			
			FilaReporteRepresaBean filaReporte = mapDiasConData.get(newFila.getStrDia());
			
			if(filaReporte == null){
				// AGREGAR TODOS LOS CAMPOS
				Iterator it = represas.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        RepresaBean rb = (RepresaBean) pair.getValue();
			        InfoReporteRepresasBean info = new InfoReporteRepresasBean();
			        info.setBolExistDataRepresa(false);
			        info.setIntTipoColumna(Constants.TYPE_COLUMN_REPRESA);
			        info.setIntCodRepresa(rb.getIntCodigo());
			        info.setStrNombrePrincipalColumna(rb.getNombreRepresa());
			        info.setStrCota(Constants.CELL_WITHOUT_VALUE);
			        info.setStrVolumen(Constants.CELL_WITHOUT_VALUE);
			        info.setStrVolumenTotalRep(Constants.CELL_WITHOUT_VALUE);
			        info.setStrDescarga(Constants.CELL_WITHOUT_VALUE);
			        info.setStrCaudalTrasvasado(Constants.CELL_WITHOUT_VALUE);
			        info.setStrVolumenTrasvasado(Constants.CELL_WITHOUT_VALUE);
			        info.setStrFormulaVolumenTrasvasado(Constants.CELL_TEXT_WITHOUT_VALUE);
			        info.setStrPrecipitacion(Constants.CELL_WITHOUT_VALUE);
			        info.setStrTiempoAtmosferico(Constants.CELL_TEXT_WITHOUT_VALUE);
			        newFila.getLstData().add(info);
			    }
				
			    listReaded.add(newFila);
			}
			else{ // ESTAS POR TENER AL MENOS UN DATO, YA TIENEN LA COLUMNA DE ALMACENAMIENTO
				// BUSCAMOS
				FilaReporteRepresaBean searchedElement = new FilaReporteRepresaBean();
				for(FilaReporteRepresaBean elementoExistente : listReaded){
					if(elementoExistente.getStrDia().equals(newFila.getStrDia())){
						searchedElement = elementoExistente;
						break;
					}
				}
				
				// RECORREMOS EL MAP Y BUSCAMOS EN EL ELEMENTO EXISTENTE
				for (Integer key : represas.keySet()) {
				    RepresaBean represaBean = represas.get(key);
				    boolean contieneRepresa = false;
				    for(InfoReporteRepresasBean info : searchedElement.getLstData()){
				    	if(represaBean.getIntCodigo() == info.getIntCodRepresa()){
					    	contieneRepresa = true;
					    	break;
					    }
				    }
				    
				    if(!contieneRepresa){
				    	InfoReporteRepresasBean info = new InfoReporteRepresasBean();
				    	info.setBolExistDataRepresa(false);
				    	info.setIntTipoColumna(Constants.TYPE_COLUMN_REPRESA);
				    	info.setIntCodRepresa(represaBean.getIntCodigo());
				        info.setStrNombrePrincipalColumna(represaBean.getNombreRepresa());
				        info.setStrCota(Constants.CELL_WITHOUT_VALUE);
				        info.setStrVolumen(Constants.CELL_WITHOUT_VALUE);
				        info.setStrVolumenTotalRep(Constants.CELL_WITHOUT_VALUE);
				        info.setStrDescarga(Constants.CELL_WITHOUT_VALUE);
				        info.setStrCaudalTrasvasado(Constants.CELL_WITHOUT_VALUE);
				        info.setStrVolumenTrasvasado(Constants.CELL_WITHOUT_VALUE);
				        info.setStrFormulaVolumenTrasvasado(Constants.CELL_TEXT_WITHOUT_VALUE);
				        info.setStrPrecipitacion(Constants.CELL_WITHOUT_VALUE);
				        info.setStrTiempoAtmosferico(Constants.CELL_TEXT_WITHOUT_VALUE);
				        searchedElement.getLstData().add(info);
				    }
				    
				}
			}
		}
		
		return listReaded;
	}
	
	/**
	 * Método privado que permite obtener el número ordinal de día en base a una fecha.
	 *
	 * @param fecha representa una fecha específica
	 * @return the dia
	 * @Return int que indica el número ordinal del día
	 */
	private int getDia(GregorianCalendar fecha){
		return fecha.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Método privado que permite comparar dos períodos (granular en meses).
	 *
	 * @param period1 representa primer periodo
	 * @param period2 representa segundo periodo
	 * @return the int
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return int que indica qué período es mayor o si son iguales
	 */
	private int comparePeriod(String period1, String period2) throws Exception{
		Date date1 = DateUtils.toYyyyMMddToDate(period1+"01");
		Date date2 = DateUtils.toYyyyMMddToDate(period2+"01");
		return date1.compareTo(date2);
	}
	
	/**
	 * Método privado que permite obtener el periodo al cual pertenece una fecha determinada.
	 *
	 * @param fecha representa una fecha específica
	 * @return the periodo
	 * @Return String con el valor del período
	 */
	private String getPeriodo(GregorianCalendar fecha){
		String mes = String.format("%02d", fecha.get(Calendar.MONTH) + 1);
		String anio = String.valueOf(fecha.get(Calendar.YEAR));
		return anio+mes;
	}

}
