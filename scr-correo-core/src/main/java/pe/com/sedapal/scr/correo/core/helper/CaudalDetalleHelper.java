/**
 * 
 * Resumen.
 * Objeto 				: CaudalDetalleHelper
 * Descripción 			: Clase relacionada con ríos y caudales
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.core.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalDia;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalHora;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.core.util.ArchivoUtil;
import pe.com.sedapal.scr.correo.core.util.ExpRegUtil;
import pe.com.sedapal.scr.correo.core.util.FechaUtil;

public class CaudalDetalleHelper {
	
	private final Set<String> setMensajesValidacion;
	private final GregorianCalendar todayAtNow;
	
	public CaudalDetalleHelper(GregorianCalendar todayAtNow) {
		setMensajesValidacion = new HashSet<String>();
		this.todayAtNow = todayAtNow;
	}
	
	/**
	 * Método que permite validar el contenido de Ríos
	 * @Return Objeto de tipo boolean que contiene true o false	 
	 */
	public boolean contieneRios(final List<RioBean> lstRios, final List<List<String>> lstContenidoArchivo) {
		boolean bolResultado = false;
		
		int intContadorRios = 0;
		
		for(List<String> lstGrupoContenido : lstContenidoArchivo) {
			for(RioBean rioBean : lstRios) {
				if(lstGrupoContenido.get(0).contains(rioBean.getNombreLargo())) {
					intContadorRios++;
				}
			}
		}
		
		if(intContadorRios == lstRios.size()) {
			bolResultado = true;
		}
		
		return bolResultado;
	}
	
	/**
	 * Método que permite validar el contenido de Río
	 * @Return Objeto de tipo boolean que contiene true o false	 
	 */
	public RioBean contieneRio(final String strLineaContenido, final List<RioBean> lstRios) {
		for(RioBean rioBean : lstRios) {
			if(strLineaContenido.equalsIgnoreCase(rioBean.getNombreLargo())) {
				return rioBean;
			}
		}
		return null;
	}
	
	/**
	 * Método que permite validar el contenido de un tipo de error
	 * @Return Objeto de tipo boolean que contiene true o false	 
	 */
	public boolean contieneTipoError(final Set<String> setErrores, final String strTipoError){
		for(String element : setErrores){
			if(element.equals(strTipoError)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que permite validar el contenido de una fila de caudal
	 * @Return Objeto de tipo boolean que contiene true o false	 
	 */
	public boolean filaCaudalValido(final String strLineaContenido, final boolean isToday, final int intHoraActual) {
		boolean bolResultado = false;
		if(strLineaContenido != null && (strLineaContenido.matches(ExpRegUtil.EXP_REG_FILA_MEDIDA_CAUDAL))) {
			bolResultado = true;
		}
		return bolResultado;
	}
	
	/**
	 * Método que permite obtener la medida de un caudal por dia
	 * @Return Objeto de tipo MedidaCaudalDia que contiene la medida	 
	 */
	public MedidaCaudalDia obtenerMedidaCaudalDia(final String strLineaContenido) {
		MedidaCaudalDia medidaCaudalDia = new MedidaCaudalDia();
		
		String[] arrMedidasHorasCaudales = strLineaContenido.split("( |\t)+");
		
		for(String strMedidaHoraCaudal : arrMedidasHorasCaudales) {
			strMedidaHoraCaudal = strMedidaHoraCaudal.trim();
			
			if(strMedidaHoraCaudal.matches(ExpRegUtil.EXP_REG_PREFIX_DIA)) {
				// DIA_01:, DIA_02:, DIA_03:, ..., DIA_19:, ... => 01, 02, 03, ...
//				String strDia = strMedidaHoraCaudal.substring(strMedidaHoraCaudal.indexOf("_") + 1, strMedidaHoraCaudal.indexOf(":"));
				String strDia = strMedidaHoraCaudal.substring(strMedidaHoraCaudal.indexOf("_") + 1, strMedidaHoraCaudal.length());
				medidaCaudalDia.setStrDia(strDia);
			} else if(strMedidaHoraCaudal.matches(ExpRegUtil.EXP_REG_HORA_MEDIDA_CAUDAL)) {
				MedidaCaudalHora medidaCaudalHora = new MedidaCaudalHora();
				medidaCaudalHora.setDblValor(Double.parseDouble(strMedidaHoraCaudal));
				medidaCaudalDia.addCaudalHora(medidaCaudalHora);
			} else if (strMedidaHoraCaudal.equals(Constants.NULL_VALUE_ROW_HOUR)) {
				MedidaCaudalHora medidaCaudalHora = new MedidaCaudalHora();
				medidaCaudalHora.setDblValor(null);
				medidaCaudalDia.addCaudalHora(medidaCaudalHora);
			}
		}
		
		return medidaCaudalDia;
	}
	
	private static String removeUTF8BOM(String s) {
        if (s.startsWith(Constants.UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }
	
	/**
	 * Método que permite leer el contenido de un archivo
	 * @Return Objeto de tipo List<List<String>> con los contenidos de los archivos	 
	 */
	public List<List<String>> leerContenidoArchivo(final String rutaArchivo, final List<RioBean> lstRios,
			final int useRutaDefault, final String nomAdjuntoGuardado) {
		
		List<List<String>> lstContenidoArchivo = new ArrayList<List<String>>();
		List<String> lstGrupoContenido = null;
		String strLineaHeader = "";
		try {
			BufferedReader brReader = null;
			
			if(useRutaDefault == 0){
				brReader = ArchivoUtil.getReaderUsingJcifs(rutaArchivo + Constants.PATH_SHARED_FILE + nomAdjuntoGuardado);
			}
			else{
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				brReader = new BufferedReader(new FileReader(classloader.getResource(rutaArchivo).getPath() + 
						File.separator + nomAdjuntoGuardado));
			}
			
			String strLinea = null;
			boolean firstLine = true;
			
			while ((strLinea = brReader.readLine()) != null) {
				
				if (firstLine) {
					strLinea = CaudalDetalleHelper.removeUTF8BOM(strLinea);
                    firstLine = false;
                }
				
				// Limpiamos espacios vacios
				strLinea = strLinea.trim();
				
				// Substring name less date header
				strLineaHeader = strLinea.substring(0, strLinea.length() - Constants.SIZE_DATE_HEADER_ADD);
				
				// Determinar: 1) Linea vacio, 2) Nombre caudal, 3) Cabecera de caudal, y 4) Fila caudal
				if(strLinea.length() == 0 || strLinea.matches(ExpRegUtil.EXP_REG_CABECERA_CAUDAL_MEDIDA)) {
					// En caso 1) Linea vacia y 3) Cabecera de caudal no hacer nada, pasar a la siguiente linea.
					continue;
				} else if(strLinea.matches(ExpRegUtil.EXP_REG_PREFIX_DIA)){
					continue;
				} else if(strLinea.matches(ExpRegUtil.EXP_REG_FILA_MEDIDA_CAUDAL) ) {// 4) Fila caudal
					if(lstGrupoContenido != null){ // GUSTAVO: Validamos que exista el grupo, sino no existe
						lstGrupoContenido.add(strLinea);
					}
				} else if(strLinea.length() > 0 && contieneRio(strLineaHeader, lstRios) != null) {// 2) Nombre caudal: Se valida string sin fecha
					// Si existe grupo de caudal, agregar a lstContenidoArchivo
					if(lstGrupoContenido != null && lstGrupoContenido.size() > 0) {// > 1 (para obviar grupo vacio)
						lstContenidoArchivo.add(lstGrupoContenido);
					}
					
					// Creamos nuevo lstGrupoContenido
					lstGrupoContenido = new ArrayList<String>();
					lstGrupoContenido.add(strLinea);
				}
				else{
					// Cerramos el grupo de caudal anterior
					if(lstGrupoContenido != null && lstGrupoContenido.size() > 0) {// > 1 (para obviar grupo vacio)
						lstContenidoArchivo.add(lstGrupoContenido);
					}
					lstGrupoContenido = null;
					// GUARDAMOS TIPO DE ERROR Y EL NOMBRE DEL RIO
					setMensajesValidacion.add(Constants.ERROR_TIPO_RIO+Constants.ERROR_SEPARATOR_TYPE+strLinea);
				}
			}
			
			// Si existe grupo de caudal, agregar a lstContenidoArchivo
			if(lstGrupoContenido != null && lstGrupoContenido.size() > 0) {// > 1 (para obviar grupo vacio)
				lstContenidoArchivo.add(lstGrupoContenido);
			}
			
		    brReader.close();
		} catch(IOException iox) {
			iox.printStackTrace();
			setMensajesValidacion.clear();
			setMensajesValidacion.add(Constants.ERROR_TIPO_NO_ESPERADO);
		}
		
		return lstContenidoArchivo;
	}
	
	/**
	 * Método que permite extraer caudales
	 * @Return Objeto de tipo List<Caudal> con los contenidos de caudales	 
	 */
	public List<Caudal> extraerCaudales(final List<List<String>> lstContenidoArchivo, final List<RioBean> lstRios) {
		String strLineaHeader = "";
		String strDateHeader = "";
		if(lstContenidoArchivo == null || lstContenidoArchivo.size() == 0) {
			return null;
		}
		
		// Obtenemos fecha actual para validar data ingresada
		int intDiaActual = this.getDiaActual(todayAtNow);
		int intHoraActual = this.getHoraActual(todayAtNow);
		///
		
		List<Caudal> lstCaudales = new ArrayList<Caudal>();
		
		// Convertimos a beans cada medida de caudal por dia y hora
		for(List<String> lstGrupoContenidoCaudal : lstContenidoArchivo) {
			Caudal caudal = new Caudal();
			
			// Aca tenemos la certeza que existe rio porque ya validamos en el metodo de lectura
			// Substring name less date header
			strLineaHeader = lstGrupoContenidoCaudal.get(0).substring(0, lstGrupoContenidoCaudal.get(0).length() - Constants.SIZE_DATE_HEADER_ADD);
			strDateHeader = (lstGrupoContenidoCaudal.get(0).substring(lstGrupoContenidoCaudal.get(0).lastIndexOf("-") + 1)).trim();
			RioBean rioBean = contieneRio(strLineaHeader, lstRios);
			
			// G: Validamos que no ingrese menos dias (uno es de cabecera)
			String[] date = strDateHeader.split("/");
			boolean bolContinue = true;
			if(lstGrupoContenidoCaudal.size() < (Integer.parseInt(date[0]) + 1)){ // PASADA
				setMensajesValidacion.add(Constants.ERROR_TIPO_CAUDAL_PASADO+Constants.ERROR_SEPARATOR_TYPE+rioBean.getNombreLargo());
				bolContinue = false;
			}
			else if(lstGrupoContenidoCaudal.size() > (Integer.parseInt(date[0]) + 1)){ // FUTURO
				setMensajesValidacion.add(Constants.ERROR_TIPO_CAUDAL_FUTURO+Constants.ERROR_SEPARATOR_TYPE+rioBean.getNombreLargo());
				bolContinue = false;
			}
			////
			
			if(bolContinue){
				if(rioBean != null && lstGrupoContenidoCaudal.size() > 1) {
					boolean bolFilaCaudalValido = true;
					
					// Obviamos la cabecera (fila 0) por eso mayor a 1 (> 1)
					for(int fila = 1; fila < lstGrupoContenidoCaudal.size(); fila++) {
						String strLineaContenido = lstGrupoContenidoCaudal.get(fila);
						bolFilaCaudalValido = this.filaCaudalValido(strLineaContenido, (Integer.parseInt(date[0]) == fila), intHoraActual);
						
						if(bolFilaCaudalValido) {
							MedidaCaudalDia medidaCaudalDia = obtenerMedidaCaudalDia(strLineaContenido);
							
							CaudalDetalle caudalDetalle = new CaudalDetalle();
							caudalDetalle.setCaudalDia(medidaCaudalDia);
							caudal.addDetalle(caudalDetalle);
						} else {
							// Aca aun no entra
							setMensajesValidacion.add(Constants.ERROR_TIPO_CAUDAL+Constants.ERROR_SEPARATOR_TYPE+rioBean.getNombreLargo());
						}
					}
					
					if(bolFilaCaudalValido) {
						// Adicionamos caudal a la lista de caudales
						caudal.setRioBean(rioBean);
						//Add date header period 
						caudal.setStrPeriodo(FechaUtil.getPeriodoArray(date));
						lstCaudales.add(caudal);
					}
				} else {
					// ESTA VALIDACION YA ESTA DE MAS PORQUE EN EL METODO ANTEIOR SE PASARON SOLO LOS ARCHIVOS EXISTENTES
					if(rioBean!=null&&rioBean.getNombreLargo()!=null){
						setMensajesValidacion.add(Constants.ERROR_TIPO_SIN_CAUDAL+Constants.ERROR_SEPARATOR_TYPE+rioBean.getNombreLargo());
					}
				}
			}
		}
		
		return lstCaudales;
	}
	
	/**
	 * Método que permite obtener la hora actual
	 * @Return Objeto de tipo int con el contenido de la hora	 
	 */
	private int getHoraActual(GregorianCalendar fecha){
		if(fecha.get(Calendar.HOUR_OF_DAY) == 0){
			return 24;
		}else{
			return fecha.get(Calendar.HOUR_OF_DAY);
		}
	}
	
	/**
	 * Método que permite obtener el día actual
	 * @Return Objeto de tipo int con el contenido del día	 
	 */
	private int getDiaActual(GregorianCalendar fecha){
		return fecha.get(Calendar.DAY_OF_MONTH);
	}

	public Set<String> getSetMensajesValidacion() {
		return setMensajesValidacion;
	}

	public static boolean validar(Caudal caudal, Caudal caudalBD) {
		return true;
	}
	
}
