/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.ICaudalDao;
import pe.com.sedapal.scr.core.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.core.exception.ValidationFileException;
import pe.com.sedapal.scr.core.services.ICaudalDetalleService;
import pe.com.sedapal.scr.core.util.Utils;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalDia;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalHora;

// TODO: Auto-generated Javadoc
/**
 * The Class CaudalDetalleServiceImpl.
 */
@Service
public class CaudalDetalleServiceImpl implements ICaudalDetalleService{
	
	/** The caudal dao. */
	@Autowired
	private ICaudalDao caudalDao;
	
	/** The caudal detalle dao. */
	@Autowired
	private ICaudalDetalleDao caudalDetalleDao;
	
	/**
	 * Método que permite registrar el detalle de un caudal.
	 *
	 * @param detalle representa la información de caudales
	 * @param caudalId es el identificador del caudal del cual se registrará su detalle
	 * @param audit representa la información de auditoría para la modificación en base de datos
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	@Transactional
	public void registrarCaudalDetalle(List<List<CeldaMatrizBean>> detalle, Integer caudalId, BaseBean audit) throws Exception {
		
		List<List<CeldaMatrizBean>> dias = detalle;
		
		// Borramos la ultima columna de promedio que solo es usada para mostrar en pantalla
		for(List<CeldaMatrizBean> lstFilas : dias){
			lstFilas.remove(lstFilas.size()-1); 
		} 
		
		for(int i=0; i<dias.size(); i++){
			// Por cada dia
			List<CeldaMatrizBean> datosDia = dias.get(i);
			if(rowToProcess(datosDia)){
				CaudalDetalle caudalDetalle = new CaudalDetalle();
				Caudal caudal = new Caudal();
				caudal.setIntCodigo(caudalId);
				MedidaCaudalDia medidaCaudalDia = new MedidaCaudalDia();
				medidaCaudalDia.setStrDia(String.format("%02d", (i+1)));
				medidaCaudalDia.setLstCaudalesDia(getCaudalDetalleHoras(datosDia));
				caudalDetalle.setCaudal(caudal);
				caudalDetalle.setCaudalDia(medidaCaudalDia);
				caudalDetalle.setUsuarioCreacion(audit.getUsuarioModificacion());
				caudalDetalle.setCodigoArea(audit.getCodigoArea());
				caudalDetalle.setPrograma(audit.getPrograma());
				caudalDetalle.setCodigoSistema(audit.getCodigoSistema());
				caudalDetalleDao.registrarCaudalDetalle(caudalDetalle);
			}else{
				//En caso tenga data futura registrada, esto elimina de BD las filas vacias
				CaudalDetalle caudalDetalle = new CaudalDetalle();
				Caudal caudal = new Caudal();
				caudal.setIntCodigo(caudalId);
				MedidaCaudalDia medidaCaudalDia = new MedidaCaudalDia();
				medidaCaudalDia.setStrDia(String.format("%02d", (i+1)));
				caudalDetalle.setCaudal(caudal);
				caudalDetalle.setCaudalDia(medidaCaudalDia);
				caudalDetalleDao.eliminarCaudalDetalle(caudalDetalle);
			}
		}
		
		// Si todo sale bien, actualizamos el caudal
		Caudal caudal = new Caudal();
		caudal.setIntCodigo(caudalId);
		caudal.setStrTipoProceso(Constants.PROCESS_TYPE_MANUAL);
		caudal.setUsuarioModificacion(audit.getUsuarioModificacion());
		caudal.setCodigoArea(audit.getCodigoArea());
		caudal.setPrograma(audit.getPrograma());
		caudal.setCodigoSistema(audit.getCodigoSistema());
		
		caudalDao.actualizarCaudal(caudal);
	}
	
	/**
	 * Método privado que permite castear la información de una fila de objetos de vista a objetos para persistencia.
	 *
	 * @param detalleHoras representa la información de una fila (no cabecera) del archivo a cargar
	 * @return the caudal detalle horas
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos de tipo MedidaCaudalHora que representan la información de caudales para las horas de un día
	 */
	private List<MedidaCaudalHora> getCaudalDetalleHoras(List<CeldaMatrizBean> detalleHoras) throws Exception{
		List<CeldaMatrizBean> datosPorHora = detalleHoras;
		List<MedidaCaudalHora> datosPorHoraFormated = new ArrayList<MedidaCaudalHora>();
		for(int i=0; i<datosPorHora.size(); i++){
			CeldaMatrizBean valor = datosPorHora.get(i);
			MedidaCaudalHora medidaCaudalHora = new MedidaCaudalHora();
			medidaCaudalHora.setStrHora(String.format("%02d", (i+1)));
			if(!valor.getStrData().trim().equals(Constants.NO_HAY_DATO_CARGADO+"")){
				medidaCaudalHora.setDblValor(Double.parseDouble(valor.getStrData().trim()));
			}
			datosPorHoraFormated.add(medidaCaudalHora);
		}
		return datosPorHoraFormated;
	}
	
	/**
	 * Método privado que permite validar que una fila del archivo tiene al menos un dato.
	 *
	 * @param detalleHoras representa la información de una fila (no cabecera) del archivo a cargar
	 * @return true, if successful
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return boolean que indica si la fila tiene al menos un dato de caudal
	 */
	private boolean rowToProcess(List<CeldaMatrizBean> detalleHoras) throws Exception{
		Set<String> conjuntoDetalles = new HashSet<>();
		conjuntoDetalles.add(Constants.NO_HAY_DATO_CARGADO+""); // poner el dato que representa vacio
		for(CeldaMatrizBean value : detalleHoras){
			conjuntoDetalles.add(value.getStrData().trim());
		}
		if(conjuntoDetalles.size() > 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Método que permite validar la información de caudales para la carga manual.
	 *
	 * @param datosExcel Contiene la información cargada por el usuario en archivo XLS
	 * @param periodo the periodo
	 * @throws ValidationFileException Excepción de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void validaInformacionUploaded(List<List<String>> datosExcel, String periodo) throws ValidationFileException, Exception {
		GregorianCalendar fecha = (GregorianCalendar)DateUtils.getCalendar();
		String periodoActual = this.getPeriodo(fecha);
		int intDiaActual = -1;
		int intHoraActual = -1;
		
		if(!DateUtils.isValidDate(periodo+"01")){ //yyyyMMdd
			throw new ValidationFileException(Constants.CARGA_MANUAL_PERIODO_NO_EXISTE);
		}
		// Validamos si es el period actual o periodo pasado
		int intCompare = this.comparePeriod(periodoActual, periodo);
		if(intCompare < 0){// Periodo futuro
			throw new ValidationFileException(Constants.CARGA_MANUAL_PERIODO_FUTURO);
		}
		else if(intCompare == 0){//Periodo actual
			intDiaActual = this.getDia(fecha);
			intHoraActual = this.getHora(fecha);
		}
		else{ // Periodo pasado
			intDiaActual = Utils.daysOfMonthFromPeriod(periodo);
			intHoraActual = 24;
		}
		
		if(datosExcel!=null){
			List<List<String>> matriz = datosExcel;
			
			// Validamos la cantidad de filas
			if(matriz.size() <= 1){
				throw new ValidationFileException(Constants.CARGA_MANUAL_NO_DATOS);
			}
			
			if(matriz.size() > 32){
				throw new ValidationFileException(Constants.CARGA_MANUAL_EXC_DIAS_PERMITIDOS);
			}
		
			for(int i=1; i<matriz.size(); i++){
				List<String> datosDia = matriz.get(i);
				if(datosDia.size() != 25){
					throw new ValidationFileException(Constants.CARGA_MANUAL_EXC_HRS_PERMITIDAS.replace(Constants.PARAM1, i+""));
				}
				for(int j=1; j<datosDia.size(); j++){
					boolean bolMustBeEmpty = false;
					String valor = datosDia.get(j).trim();
					if(i < intDiaActual){
						bolMustBeEmpty = false;
					}
					else if(i == intDiaActual){
						if(j < intHoraActual){
							bolMustBeEmpty = false;
						}
						else if(j == intHoraActual){
							bolMustBeEmpty = false;
						}
						else{ //mayor
							bolMustBeEmpty = true;
						}
					}
					else{ // mayor
						bolMustBeEmpty = true;
					}
					
					if(!bolMustBeEmpty){
						if(valor.equals("")){
							throw new ValidationFileException(Constants.CARGA_MANUAL_DATOS_INCOMPLETOS);
						}
						else{
							double dblValue = -1.0;
							
							try {
								dblValue = Double.parseDouble(valor);
							} catch (Exception e) { // Controlamos que pueda convettirse
								throw new ValidationFileException(Constants.CARGA_MANUAL_DATOS_NO_NUM
										.replace(Constants.PARAM1, i+"")
										.replace(Constants.PARAM2, j+""));
							}
							
							// Controlamos longitud de bd
							if(!Utils.longValidInputDecimal(valor, Constants.LONG_MAX_INTEGER_CAUDAL, Constants.LONG_MAX_DECIMAL_CAUDAL)){
								throw new ValidationFileException(Constants.CARGA_MANUAL_DATOS_LON_INVALID
										.replace(Constants.PARAM1, i+"")
										.replace(Constants.PARAM2, j+""));
							}
							
							if(dblValue <= 0){ // Controlamos que ingrese valores positivos
								throw new ValidationFileException(Constants.CARGA_MANUAL_DATOS_ZERO_LESS
										.replace(Constants.PARAM1, i+"")
										.replace(Constants.PARAM2, j+""));
							}
						}
					}
					else{
						if(!valor.equals("")){
							throw new ValidationFileException(Constants.CARGA_MANUAL_DATOS_EXC);
						}
					}
				}
			}
		}
		else{
			throw new ValidationFileException(Constants.CARGA_MANUAL_NO_DATOS);
		}
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
	
	/**
	 * Método privado que permite obtener el número ordinal de hora en base a una fecha.
	 *
	 * @param fecha representa una fecha específica
	 * @return the hora
	 * @Return int que indica el número ordinal de la hora
	 */
	private int getHora(GregorianCalendar fecha){
		if(fecha.get(Calendar.HOUR_OF_DAY) == 0){
			return 24;
		}else{
			return fecha.get(Calendar.HOUR_OF_DAY);
		}
	}

	/**
	 * Método que permite validar la extensión del archivo de carga manual.
	 *
	 * @param strFileName Nombre completo del archivo a cargar
	 * @throws ValidationFileException Excepción de validación
	 */
	@Override
	public void validaExtensionFile(String strFileName) throws ValidationFileException {
		String strFileNameParts[] = strFileName.split("\\.");
		// Validamos la extensión del archivo
		if (!strFileNameParts[strFileNameParts.length - 1].equals("xls")) {
			throw new ValidationFileException("El archivo cargado no presenta la extensión XLS");
		}
	}
	
}
