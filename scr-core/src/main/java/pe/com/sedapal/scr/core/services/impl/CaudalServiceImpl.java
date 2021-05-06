/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.CaudalDetalleBean;
import pe.com.sedapal.scr.core.beans.CaudalSearchBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.beans.ReporteBean;
import pe.com.sedapal.scr.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.core.beans.ReporteWrapperBean;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.ICaudalDao;
import pe.com.sedapal.scr.core.dao.IMantRioDao;
import pe.com.sedapal.scr.core.services.ICaudalService;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class CaudalServiceImpl.
 */
@Service
public class CaudalServiceImpl implements ICaudalService {

	/** The caudal dao. */
	@Autowired
	private ICaudalDao caudalDao;
	
	/** The mant rio dao. */
	@Autowired
	private IMantRioDao mantRioDao;
	
	/**
	 * Método que permite obtener el listado de caudales de río.
	 *
	 * @param caudalSearchBean Contiene los filtros de búsqueda de caudal
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result buscarCaudales(CaudalSearchBean caudalSearchBean, Paginacion paginacion) throws Exception {
		return caudalDao.buscarCaudales(caudalSearchBean, paginacion);
	}
	
	/**
	 * Método que permite castear la data de excel en objeto que permite agregar información adicional.
	 *
	 * @param datosExcel contiene la información del archivo de carga manuaal de caudales
	 * @param periodo es el periodo para el cual se requiere información
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto mapa con la información casteada del excel
	 */
	// Aca preparamos datos de excel solo para guardarlo
	@Override
	public List<List<CeldaMatrizBean>> prepararDataExcel(List<List<String>> datosExcel, String periodo) throws Exception {
		List<List<CeldaMatrizBean>> lstDataConvertida = new ArrayList<List<CeldaMatrizBean>>();
		int intNumDiasMes = Utils.daysOfMonthFromPeriod(periodo.trim());

		// Eliminamos primera fila de cabecera
		datosExcel.remove(0); 
		
		// borrar dias sobrantes
		int intNumDiasLoaded = datosExcel.size();
		int intNumDiasSobra = intNumDiasLoaded - intNumDiasMes;
		for(int i=1; i<=intNumDiasSobra; i++){
			datosExcel.remove(datosExcel.size() - 1);
		}
		
		// Quitamos la primera columna de dias
		// Ademas agrega el promedio redondeado a dos decimales
		for(List<String> lstFilas : datosExcel){
			lstFilas.remove(0); 
			lstFilas.add(Utils.calculateRowAverage(lstFilas));
		} 
		
		// generamos lista con objetos celda matriz en base a archivo cargado
		for(List<String> lstColumnas : datosExcel){
			List<CeldaMatrizBean> lstColumnasFormated = new ArrayList<CeldaMatrizBean>();
			for(String cell : lstColumnas){
				if(cell.trim().equals("")){
					cell = String.valueOf(Constants.NO_HAY_DATO_CARGADO);
				}
				lstColumnasFormated.add(new CeldaMatrizBean(cell));
			}
			lstDataConvertida.add(lstColumnasFormated);
		}
		return lstDataConvertida;
	}
	
	/**
	 * Método que permite preparar las matrices de caudales comparadas al frontend (para mostrar diferencias).
	 *
	 * @param datosExcel contiene la información del archivo de carga manuaal de caudales
	 * @param datosDb contiene la información persistida de caudales
	 * @param periodo es el periodo para el cual se está comparando
	 * @return the map
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto mapa con la información de cada matriz (excel y bd)
	 */
	// Aca la lista ya llega validada por el componente del standalone
	@Override
	public Map<String, List<List<CeldaMatrizBean>>> prepararMatricesComparadas(List<List<String>> datosExcel, 
			List<List<CeldaMatrizBean>> datosDb, String periodo) throws Exception{
		Map<String, List<List<CeldaMatrizBean>>> viewValues = new HashMap<>();
		
		List<List<CeldaMatrizBean>> lstDataConvertida = this.prepararDataExcel(datosExcel, periodo);

		int intNumDiasMes = Utils.daysOfMonthFromPeriod(periodo.trim());
		
		// Comparamos las listas
		for(int i=0; i<intNumDiasMes; i++){
			List<CeldaMatrizBean> rowsExcel = lstDataConvertida.get(i);
			List<CeldaMatrizBean> rowsDb = datosDb.get(i);
			for(int j=0; j<Constants.TOTAL_COLUMNAS_DISPLAY; j++){
				if(rowsExcel.get(j).getStrData().equals(Constants.NO_HAY_DATO_CARGADO+"") && rowsDb.get(j).getStrData().equals(Constants.NO_HAY_DATO_CARGADO+"")){
					
				}
				else{
					if(rowsExcel.get(j).getStrData().equals(rowsDb.get(j).getStrData())){
						datosDb.get(i).set(j, new CeldaMatrizBean(rowsDb.get(j).getStrData(),Constants.CLASS_CSS_DATO_IGUAL));
						lstDataConvertida.get(i).set(j, new CeldaMatrizBean(rowsExcel.get(j).getStrData(),Constants.CLASS_CSS_DATO_IGUAL));
					}
					else{
						datosDb.get(i).set(j, new CeldaMatrizBean(rowsDb.get(j).getStrData(),Constants.CLASS_CSS_DATO_DIFERENTE));
						lstDataConvertida.get(i).set(j, new CeldaMatrizBean(rowsExcel.get(j).getStrData(),Constants.CLASS_CSS_DATO_DIFERENTE));
					}
				}
			}
		}
		
		viewValues.put(Constants.OBJECT_NAME_EXCEL_LIST, lstDataConvertida);
		viewValues.put(Constants.OBJECT_NAME_DB_LIST, datosDb);

		return viewValues;
	}
	
	/**
	 * Método que permite obtener el detalle de un caudal de río.
	 *
	 * @param caudalId es el identificador de caudal
	 * @param rioId es el identificador de río
	 * @param periodo es el periodo del cual se requiere la información
	 * @return the caudal detalle bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CaudalDetalleBean que contiene los datos del detalle
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CaudalDetalleBean obtenerDetalle(Integer caudalId, Integer rioId, String periodo) throws Exception{
		List<CeldaMatrizBean> matrizDatos = caudalDao.obtenerDetalleCaudal(caudalId);
		RioBean rioBean = new RioBean();
		if(rioId!=null){
			rioBean = mantRioDao.obtenerRio(rioId);
		}
		CaudalDetalleBean detalle = new CaudalDetalleBean();
		detalle.setLstDetalles(matrizDatos);
		detalle.setStrToday(DateUtils.dateToddMMyyyyhhmmss12(new Date()));
		detalle.setStrNombreLargoRio(rioBean.getNombreLargo());
		detalle.setStrPeriodo(periodo);
		detalle.setIntCodigoCaudal(caudalId);
		
		if(null!=detalle && null!=detalle.getLstDetalles()){
			int intNumDiasMes = Utils.daysOfMonthFromPeriod(periodo.trim());
			int intNumHoras = Constants.TOTAL_COLUMNAS_DISPLAY;
			
			int intNumDiasObtenidosBd = detalle.getLstDetalles().size();
			int intDiasRestantes = intNumDiasMes - intNumDiasObtenidosBd;
			for(int i = 0; i<intDiasRestantes; i++){
				ArrayList datosDia = cargarDatosVaciosDia(intNumHoras);
				detalle.getLstDetalles().add(datosDia);
			}
		}
		
		return detalle;
	}
	
	/**
	 * Método privado que permite llenar datos vacios para poder gestionarlos.
	 *
	 * @param intNumHoras informa las horas que debe contener la información para un día
	 * @return the array list
	 * @Return Lista que contiene la información completada para vacíos
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList cargarDatosVaciosDia(int intNumHoras){
		ArrayList datos = new ArrayList();
		for(int i=0;i<intNumHoras; i++){
			datos.add(new CeldaMatrizBean(Constants.NO_HAY_DATO_CARGADO + ""));
		}
		return datos;
	}
	
	/**
	 * Método que permite obtener la información del reporte gráfico de caudales.
	 *
	 * @param periodo es el periodo para el cual se requiere información
	 * @param rios que contiene la lista de rios para los cuales se mostrará información
	 * @return the reporte wrapper bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos ReporteResumenBean que contiene la información del reporte gráfico
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ReporteWrapperBean generarReporteResumenCaudalGrafico(String periodo, String[] rios) throws Exception {
		boolean bolFiltraRios = (rios!=null && periodo!=null && !periodo.trim().equals(""));
		ReporteWrapperBean wrapper = new ReporteWrapperBean();
		Map<String, List> mapa = new HashMap <String, List>();
		List<ReporteResumenBean> lista = new ArrayList<ReporteResumenBean>();
		List<ReporteBean> lstresult = new ArrayList<>();
		ReporteBean result = null;
		
		if(bolFiltraRios){
			lista = caudalDao.generarReporteResumenCaudal(periodo);
		}
		
		for (ReporteResumenBean rp : lista) {
			if(Utils.valueInCollection(rios, rp.getCODRIO()+"")){
				Object obj = new Object[]{rp.getDIA().replace("DIA_", ""), rp.getPROMED().toString()};
				if(!mapa.containsKey(rp.getNOMBRERIO())){
					mapa.put(rp.getNOMBRERIO(), new ArrayList());
				}
				mapa.get(rp.getNOMBRERIO()).add(obj);
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
			wrapper.setStrEjexTitulo(Constants.REP_GRAF1_X_TITULO.replace(Constants.PARAM1, mesTexto).
					replace(Constants.PARAM2, anio));
			wrapper.setIntDiasPeriodo(Utils.daysOfMonthFromPeriod(periodo.trim()));
		}
		else{
			wrapper.setStrEjexTitulo(Constants.REP_GRAF1_X_TITULO_UNDEFINED);
			wrapper.setIntDiasPeriodo(Constants.REP_GRAF1_DIAS_DEFAULT);
		}
		
		wrapper.setStrEjeyTitulo(Constants.REP_GRAF1_Y_TITULO);
		wrapper.setLstDataReporte(lstresult);
		
		return wrapper;
	}
	
	/**
	 * Método que permite obtener la información del reporte pdf.
	 *
	 * @param periodo es el periodo para el cual se requiere información
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos ReporteResumenBean que contiene la información del reporte pdf
	 */
	@Override
	public List<ReporteResumenBean> generarReporteResumenCaudal(String periodo) throws Exception {
		
		List<ReporteResumenBean> listaResultados = caudalDao.generarReporteResumenCaudal(periodo);
		for(ReporteResumenBean dato : listaResultados){
			if(dato.getNHR01().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR01(null);
			}
			if(dato.getNHR02().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR02(null);
			}
			if(dato.getNHR03().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR03(null);
			}
			if(dato.getNHR04().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR04(null);
			}
			if(dato.getNHR05().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR05(null);
			}
			if(dato.getNHR06().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR06(null);
			}
			if(dato.getNHR07().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR07(null);
			}
			if(dato.getNHR08().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR08(null);
			}
			if(dato.getNHR09().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR09(null);
			}
			if(dato.getNHR10().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR10(null);
			}
			if(dato.getNHR11().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR11(null);
			}
			if(dato.getNHR12().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR12(null);
			}
			if(dato.getNHR13().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR13(null);
			}
			if(dato.getNHR14().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR14(null);
			}
			if(dato.getNHR15().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR15(null);
			}
			if(dato.getNHR16().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR16(null);
			}
			if(dato.getNHR17().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR17(null);
			}
			if(dato.getNHR18().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR18(null);
			}
			if(dato.getNHR19().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR19(null);
			}
			if(dato.getNHR20().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR20(null);
			}
			if(dato.getNHR21().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR21(null);
			}
			if(dato.getNHR22().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR22(null);
			}
			if(dato.getNHR23().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR23(null);
			}
			if(dato.getNHR24().compareTo(new BigDecimal(Constants.DEFAULT_CAUDET_NUMBER_DOUBLE + "")) == 0){
				dato.setNHR24(null);
			}
		}
		
		return listaResultados;
	}

}
