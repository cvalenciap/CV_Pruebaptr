package pe.com.sedapal.common.core.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;

public class ReporteUtil {
	
	private ReporteUtil() {
	    throw new IllegalStateException("Utility class");
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ReporteUtil.class);

	public static void generarPDF(String nombreEntrada, 
						   Map<String, Object> params, 
						   Collection<?> collection, 
						   String nombreSalida,
						   HttpServletResponse response) {
		try {
			Map<String, Object> mapParams = params;
			InputStream jasperStream = ReporteUtil.class.getResourceAsStream(ConstantsCommon.RUTA_REPORTES+nombreEntrada);
			if (mapParams == null) {
				mapParams = new HashMap<>();
			}
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection, false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapParams, jrDataSource);
			
			response.setHeader(ConstantsCommon.CONTENT_DISPOSITION,"inline; filename=\""+nombreSalida+".pdf\"");
			response.setContentType("application/pdf; name=\""+nombreSalida+".pdf\"");
			
			try (OutputStream outStream = response.getOutputStream()) {
				JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public static void generarXLS(String nombreEntrada,
								  Map<String, Object> parVars,
								  String nombreSalida,
								  HttpServletResponse response) {
		try {
			Map<String, Object> vars = parVars;
			InputStream excelStream = ReporteUtil.class.getResourceAsStream(ConstantsCommon.RUTA_REPORTES+nombreEntrada);
			if (vars == null) {
				vars = new HashMap<>();
			}
			
			Context context = new Context();
			
			for (Map.Entry<String, Object> var : vars.entrySet()) {
				String key = var.getKey();
				Object value = var.getValue();
				context.putVar(key, value);
			}
			
			response.setCharacterEncoding(ConstantsCommon.UTF8);
	        response.setContentType("application/vnd.ms-excel");
			response.setHeader(ConstantsCommon.CONTENT_DISPOSITION,"attachment; filename=\""+nombreSalida+".xls\"");
			
			try (OutputStream outStream = response.getOutputStream()) {
				JxlsHelper.getInstance().processTemplate(excelStream, outStream, context);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static void generarImage(String nombreEntrada, 
			   Map<String, Object> parParams, 
			   Collection<?> collection, 
			   String nombreSalida,
			   HttpServletResponse response) {
			try {
				InputStream jasperStream = ReporteUtil.class.getResourceAsStream(ConstantsCommon.RUTA_REPORTES+nombreEntrada);
				Map<String, Object> params = parParams;
				if (params == null) {
					params = new HashMap<>();
				}
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				JRDataSource jrDataSource = new JRBeanCollectionDataSource(collection, false);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrDataSource);
				
				response.setHeader(ConstantsCommon.CONTENT_DISPOSITION,"inline; filename=\""+nombreSalida+".png\"");
				response.setContentType("image/png");
				
				try (OutputStream outStream = response.getOutputStream()) {
					int pageIndex = 0;
					BufferedImage pageImage = new BufferedImage(jasperPrint.getPageWidth() + 1, jasperPrint.getPageHeight() + 1, BufferedImage.TYPE_INT_RGB);
	                JRGraphics2DExporter exporter = new JRGraphics2DExporter();
	                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	                SimpleGraphics2DExporterOutput output = new SimpleGraphics2DExporterOutput();
	                output.setGraphics2D((Graphics2D) pageImage.getGraphics());
	                exporter.setExporterOutput(output);
	                SimpleGraphics2DReportConfiguration configuration = new SimpleGraphics2DReportConfiguration();	  
	                configuration.setPageIndex(pageIndex);
	                exporter.setConfiguration(configuration);	                
	                exporter.exportReport();
	                ImageIO.write(pageImage, "png", outStream);				
				}
			} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
	
}
