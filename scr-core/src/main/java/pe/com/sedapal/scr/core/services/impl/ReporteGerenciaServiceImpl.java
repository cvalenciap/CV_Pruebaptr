/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.utils.DateUtils;
import pe.com.sedapal.scr.core.beans.AlmacenamientoBean;
import pe.com.sedapal.scr.core.beans.DatosRepresasGerenciaBean;
import pe.com.sedapal.scr.core.common.MessagesHtml;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoDao;
import pe.com.sedapal.scr.core.dao.IRepresasDao;
import pe.com.sedapal.scr.core.services.IReporteGerenciaService;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteGerenciaServiceImpl.
 */
@Service
public class ReporteGerenciaServiceImpl implements IReporteGerenciaService {
	
	/** The almacenamiento dao. */
	@Autowired
	private IAlmacenamientoDao almacenamientoDao;
	
	/** The represas dao. */
	@Autowired
	private IRepresasDao represasDao;
	
	/** The formato fecha. */
	private static String FORMATO_FECHA = "dd.MM.yyyy";

	/**
	 * Método que permite generar el cuadro resumen del correo a gerencia.
	 *
	 * @return the string
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo String con el HTML de correo
	 */
	@Override
	public String generarCuadroResumenReporte() throws Exception {
		StringBuilder sb = new StringBuilder(MessagesHtml.NEWLINE).append(MessagesHtml.NEWLINE);
		
		String horaMenor = "";
		StringBuilder climasRepresas = new StringBuilder("");
		String fechaHoy = DateUtils.formatToDateString(FORMATO_FECHA);
		AlmacenamientoBean almacenamiento = almacenamientoDao.obtenerDatosAcumuladosPorDia(fechaHoy);
		List<DatosRepresasGerenciaBean> datosRepresas = represasDao.obtenerDatosReporteGerencia(fechaHoy);
		String represasEcluidas = almacenamientoDao.obtenerRepresasExcluidasColumnaUno();
		
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_INIT).append(MessagesHtml.REPORTE_GERENCIA_TABLE_BODY_INIT)
		.append(MessagesHtml.REPORTE_GERENCIA_HEADER.replace(MessagesHtml.PARAMETRO_1, fechaHoy));
		
		for(DatosRepresasGerenciaBean datoRepresa : datosRepresas){
			horaMenor = datoRepresa.getStrHoraCorte();
			climasRepresas.append(datoRepresa.getStrNombreRepresa()).append("(").append(datoRepresa.getStrNombreClima()).append("), ");
			sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_INIT);
			
			sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL1.replace(MessagesHtml.PARAMETRO_1, datoRepresa.getStrNombreRepresa()));
			sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL2.replace(MessagesHtml.PARAMETRO_1, datoRepresa.getBdVolumen().toString()));
			sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL3.replace(MessagesHtml.PARAMETRO_1, datoRepresa.getBdTopeMaximoRepresa().toString()));
			sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL4.replace(MessagesHtml.PARAMETRO_1, 
					MessagesHtml.COMENTARIO_VOLUMEN_TOTAL
						.replace(MessagesHtml.PARAMETRO_1, datoRepresa.getStrNombreRepresa())
						.replace(MessagesHtml.PARAMETRO_2, datoRepresa.getBdVolumenTotal().toString())
					+ MessagesHtml.NEWLINE +
					MessagesHtml.COMENTARIO_APERTURA.replace(MessagesHtml.PARAMETRO_1, datoRepresa.getBdDescarga().toString())));
			
			sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_END);
		}
		
		// SECCION DE DATOS SACADOS DE ALMACENAMIENTO
		
		// 1. AGREGADOS COLUMNA 1
		String valColSin = (almacenamiento == null ? "0" : almacenamiento.getDbColumnaUno().toString());
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_INIT);
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL1_SIN.replace(MessagesHtml.PARAMETRO_1, MessagesHtml.TEXTO_AGREGADO_SIN 
				+ (represasEcluidas == null ? "" : represasEcluidas)));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL2_SIN.replace(MessagesHtml.PARAMETRO_1, valColSin));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL3_SIN.replace(MessagesHtml.PARAMETRO_1, valColSin));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL4_SIN.replace(MessagesHtml.PARAMETRO_1, ""));
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_END);
		
		// 2. TOTAL VOLUMEN 
		String valColTot = (almacenamiento == null ? "0" : almacenamiento.getBdVolumenTotal().toString());
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_INIT);
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL1_TOT.replace(MessagesHtml.PARAMETRO_1, MessagesHtml.TEXTO_AGREGADO_TOT));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL2_TOT.replace(MessagesHtml.PARAMETRO_1, valColTot));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL3_TOT.replace(MessagesHtml.PARAMETRO_1, valColTot));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL4_TOT.replace(MessagesHtml.PARAMETRO_1, ""));
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_END);
	
		// 3. AGREGAMOS COLUMNA 2
		String valColCon = (almacenamiento == null ? "0" : almacenamiento.getDbColumnaDos().toString());
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_INIT);
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL1_CON.replace(MessagesHtml.PARAMETRO_1, MessagesHtml.TEXTO_AGREGADO_CON + (represasEcluidas == null ? "" : represasEcluidas)));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL2_CON.replace(MessagesHtml.PARAMETRO_1, valColCon));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL3_CON.replace(MessagesHtml.PARAMETRO_1, valColCon));
		sb.append(MessagesHtml.REPORTE_GERENCA_TABLE_BODY_COL4_CON.replace(MessagesHtml.PARAMETRO_1, ""));
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_END);
		
		// COLUMNA DE CLIMAS
		String textoDinamico = climasRepresas.toString();
		textoDinamico = textoDinamico.substring(0, textoDinamico.length()-1);
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_INIT);
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_BODY_ROWCLIMA
				.replace(MessagesHtml.PARAMETRO_1, MessagesHtml.TEXTO_CLIMA.replace(MessagesHtml.PARAMETRO_1, horaMenor)
						+ formatClimaTexto(textoDinamico)));
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_ROW_END);
		
		sb.append(MessagesHtml.REPORTE_GERENCIA_TABLE_BODY_END).append(MessagesHtml.REPORTE_GERENCIA_TABLE_END);
		
		return sb.toString();
	}

	/**
	 * Método privado que permite formatear el texto de climas en el correo de gerencia.
	 *
	 * @param texto Es el texto que se va a formatear
	 * @return the string
	 * @Return Objeto de tipo String con el texto formateado
	 */
	private String formatClimaTexto(String texto){
		int ind = texto.lastIndexOf(",");
		if( ind>=0 ){
			texto = new StringBuilder(texto).replace(ind, ind+1,"").toString();
		}
		ind = texto.lastIndexOf(",");
		if( ind>=0 ){
			texto = new StringBuilder(texto).replace(ind, ind+1," y").toString();
		}
		return texto;
	}
	
}
