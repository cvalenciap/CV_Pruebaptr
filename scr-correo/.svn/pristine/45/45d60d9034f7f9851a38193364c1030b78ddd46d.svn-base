/**
 * 
 * Resumen.
 * Objeto 				: ReporteUtilCaudal
 * Descripción 			: Clase relacionada con el exportar un jasper a PDF
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteUtilCaudal {

	/**
	 * Método que permite exportar a PDF 
	 * @Return No hay objeto de retorno
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public static void generarPDF(String nombreEntrada, Map<String, Object> params, Collection<?> collection, String nombreSalida) {			
		
		try {			
			InputStream jasperStream = ReporteUtilCaudal.class.getResourceAsStream("/reportes/" + nombreEntrada);
			if (params == null) {
				params = new HashMap<>();
			}
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection, false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrDataSource);			
			JasperExportManager.exportReportToPdfFile(jasperPrint, nombreSalida);			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Método que permite exportar a Excel 
	 * @Return No hay objeto de retorno
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public static void generarXLS(String nombreEntrada, Map<String, Object> parVars, String nombreSalida) {
		try {
			Map<String, Object> vars = parVars;
			InputStream excelStream = ReporteUtilCaudal.class.getResourceAsStream("/reportes/" + nombreEntrada);
			if (vars == null) {
				vars = new HashMap<>();
			}

			Context context = new Context();

			for (Map.Entry<String, Object> var : vars.entrySet()) {
				String key = var.getKey();
				Object value = var.getValue();
				context.putVar(key, value);
			}
			
			try (OutputStream outStream = new FileOutputStream(nombreSalida)) {
				JxlsHelper.getInstance().processTemplate(excelStream, outStream, context);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
