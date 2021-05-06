/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.sedapal.scr.core.beans.FilaReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.InfoReporteRepresasBean;
import pe.com.sedapal.scr.web.common.Constants;
import pe.com.sedapal.scr.web.util.ExcelUtils.FontWeight;
import pe.com.sedapal.scr.web.util.ExcelUtils.Horizontal;
// TODO: Auto-generated Javadoc

/**
 * The Class ReporteXLSUtils.
 *
 * @author Alexander Quispe
 * @version 1.0
 * 09/06/2017
 * La clase ReporteXLSUtils.java ha sido creada con el fin
 * de brindar metodos utilitarios en la elaboracion 
 * de reportes XLS con la libreria POI
 */
public class ReporteXLSUtils {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ReporteXLSUtils.class);

    /**
     * Instantiates a new reporte XLS utils.
     */
    private ReporteXLSUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generar XLS reporte resumen anio.
     *
     * @param data the data
     * @param periodo the periodo
     * @return the byte[]
     */
    public static byte[] generarXLSReporteResumenAnio(List<FilaReporteRepresaBean> data, String periodo) {
        try {
            int initRow = 5;
            short initColumn = 1;
            ExcelUtils excel = new ExcelUtils(Constants.CONST_FILE_REPORTE_RESUMEN_REPRESAS_XLS,
                    initRow, initColumn);
            excel.getSheet();
            setStyles(excel);

            String title = "Reporte Resumen de Represas por Periodo (" + periodo + ")";
            excel.setDataCell(title, Constants.CONST_FONT_TITLE, 2, 0);
            
            // INIT CABECERA
            FilaReporteRepresaBean datosCabecera = data.get(0);
            
            excel.createRow(3);
            
            excel.setDataCell("D\\R", Constants.CONST_STYLE_TABLE_HEADER);
            mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 2, 0); // en realidad 3
            
            String represasAlmacenamiento = "";
            String represasExcluidas = "";
            int intCantRepresas = datosCabecera.getLstData().size() - 5; // NO INCLUIMOS LOS RESUMENES (ALMACENAMIENTO, VOLUMEN TOTAL, MANIOBRAS, AFORO, CALCULADOS)
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_REPRESA){
            		excel.setDataCell(represa.getStrNombrePrincipalColumna(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 7); // en realidad 8
            	}
            }
            
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_ALMACENAMIENTO){
            		represasAlmacenamiento = represa.getStrRepresasAlmacenamiento();
            		represasExcluidas = represa.getStrRepresasExcluidas();
            		excel.setDataCell(represa.getStrNombrePrincipalColumna(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 3); // en realidad 4
            	}
            }
            
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_VOLUMENTOTAL){
            		excel.setDataCell(represa.getStrNombrePrincipalColumnaJava(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 1, 0); // en realidad 2
            	}
            }
            
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_MANIOBRAS){
            		excel.setDataCell(represa.getStrNombrePrincipalColumna(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 2); // en realidad 3
            	}
            }
            
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_AFORORIO){
            		excel.setDataCell(represa.getStrNombrePrincipalColumnaJava(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 1, 0); // en realidad 2
            	}
            }
            
            for(InfoReporteRepresasBean represa : datosCabecera.getLstData()){
            	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_AGREGADOS){
            		excel.setDataCell(represa.getStrNombrePrincipalColumna(), Constants.CONST_STYLE_TABLE_HEADER);
                    mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 1); // en realidad 2
            	}
            }
            
            // FILA DE SUBTITULOS
            excel.setRow(5);
            excel.setColumn(1);
            
            for(int i=0; i<intCantRepresas; i++){
            	excel.setDataCell("COTA", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("VOLUMEN", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("VOLUMEN\nTOTAL", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("DESCARGA", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("CAUDAL\nTRASVASADO\nPROMEDIO", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("VOLUMEN\nTRASVASADO", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("PRECIPITACIÓN", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("CLIMA", Constants.CONST_STYLE_TABLE_HEADER);
            }
            
            excel.setDataCell(represasAlmacenamiento, Constants.CONST_STYLE_TABLE_HEADER);
            mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 1); // en realidad 2
            
            excel.setDataCell("TOTAL 19 EMBALSES", Constants.CONST_STYLE_TABLE_HEADER);
            mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 1); // en realidad 2
            
            excel.setColumn(excel.getColumn() + 1); // Saltamos una columna
            excel.setDataCell("Q. NATURAL", Constants.CONST_STYLE_TABLE_HEADER);
            excel.setDataCell("Q. DESCARGA", Constants.CONST_STYLE_TABLE_HEADER);
            excel.setDataCell("Q. REGULADO", Constants.CONST_STYLE_TABLE_HEADER);
            
            excel.setColumn(excel.getColumn() + 1); // Saltamos una columna
            excel.setDataCell("S/ " +represasExcluidas, Constants.CONST_STYLE_TABLE_HEADER);
            mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 1); // en realidad 2
            
            // FILA DE UNIDADES
            excel.setRow(6);
            excel.setColumn(1);
            
            for(int i=0; i<intCantRepresas; i++){
            	excel.setDataCell("(m)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(MMC)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(MMC)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(m3/s)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(m3/s)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(m3)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("(mm)", Constants.CONST_STYLE_TABLE_HEADER);
            	excel.setDataCell("Tiempo", Constants.CONST_STYLE_TABLE_HEADER);
            }
            
            excel.setDataCell("(MMC)", Constants.CONST_STYLE_TABLE_HEADER);
        	excel.setDataCell("% ALMAC", Constants.CONST_STYLE_TABLE_HEADER);
        	excel.setDataCell("(MMC)", Constants.CONST_STYLE_TABLE_HEADER);
        	excel.setDataCell("% ALMAC", Constants.CONST_STYLE_TABLE_HEADER);
            
        	excel.setDataCell("(MMC)", Constants.CONST_STYLE_TABLE_HEADER);
        	
        	excel.setDataCell("(m3/s)", Constants.CONST_STYLE_TABLE_HEADER);
        	mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 2); // en realidad 3
        	
        	excel.setDataCell("(m3/s)", Constants.CONST_STYLE_TABLE_HEADER);
        	
        	excel.setDataCell("(m3)", Constants.CONST_STYLE_TABLE_HEADER);
        	mergeCells(excel, Constants.CONST_STYLE_TABLE_HEADER, 0, 1); // en realidad 2
        	
            // END CABECERA
        	
        	// INIT CUERPO
        	int numFila = 7;
        	
        	for(FilaReporteRepresaBean fila : data){
        		excel.setRow(numFila);
                excel.setColumn(0);
        		excel.setDataCell(fila.getStrDia(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_REPRESA){
                		excel.setDataCell(represa.getStrCota(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrVolumen(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrVolumenTotalRep(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrDescarga(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrCaudalTrasvasado(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrVolumenTrasvasado(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrPrecipitacion(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrTiempoAtmosferico(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_ALMACENAMIENTO){
                		excel.setDataCell(represa.getStrAlmacenamientoCantidad(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrAlmacenamientoPorcentaje(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrTotalEmbalseCantidad(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrTotalEmbalsePorcentaje(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_VOLUMENTOTAL){
                		excel.setDataCell(represa.getStrVolumenTotal(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_MANIOBRAS){
                		excel.setDataCell(represa.getStrCaudalNatural(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrCaudalDescarga(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrCaudalRegulado(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_AFORORIO){
                		excel.setDataCell(represa.getStrAforoRioRimac(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		for(InfoReporteRepresasBean represa : fila.getLstData()){
                	if(represa.getIntTipoColumna() == Constants.TYPE_COLUMN_AGREGADOS){
                		excel.setDataCell(represa.getStrColumnaUno(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                		excel.setDataCell(represa.getStrColumnaDos(), Constants.CONST_STYLE_TABLE_BODY_CENTER);
                	}
                }
        		
        		numFila++;
        	}
        	
        	// END CUERPO
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            excel.save(out);

            return out.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }
    
    /**
     * Merge cells.
     *
     * @param excel the excel
     * @param style the style
     * @param addRow the add row
     * @param addCol the add col
     */
    private static void mergeCells(ExcelUtils excel, String style, int addRow, int addCol) {
        int row = excel.getCurrentRowNumber();
        int col = excel.getCurrentColumnNumber();
        String area = excel.getArea(row, col, row + addRow, col + addCol);
        excel.mergeCells(area);
        excel.setStyleCell(style, area);
        excel.setColumn(col + addCol + 1);
    }

    /**
     * Sets the styles.
     *
     * @param excel the new styles
     */
    private static void setStyles(ExcelUtils excel) {
        createFonts(excel);
        createStyles(excel);
        createFontStyle(excel);
    }

    /**
     * Creates the fonts.
     *
     * @param excel the excel
     */
    private static void createFonts(ExcelUtils excel) {
    	excel.createFont(Constants.CONST_FONT_TITLE, Constants.CONST_FONT_FAMILY_CALIBRI,
                (short) 26, FontWeight.BOLD, Color.BLACK);
        excel.createFont(Constants.CONST_FONT_HEADER, Constants.CONST_FONT_FAMILY_CALIBRI,
                (short) 18, FontWeight.BOLD, Color.BLACK);
        excel.createFont(Constants.CONST_FONT_TABLE_HEADER, Constants.CONST_FONT_FAMILY_CALIBRI,
                (short) 11, FontWeight.BOLD, Color.WHITE);
        excel.createFont(Constants.CONST_FONT_TABLE_BODY, Constants.CONST_FONT_FAMILY_CALIBRI,
                (short) 11, FontWeight.NORMAL, Color.BLACK);
        excel.createFont(Constants.CONST_STYLE_TABLE_BODY_NUMBER_BOLD,
                Constants.CONST_FONT_FAMILY_CALIBRI, (short) 11, FontWeight.BOLD, Color.BLACK);
    }

    /**
     * Creates the styles.
     *
     * @param excel the excel
     */
    private static void createStyles(ExcelUtils excel) {
    	excel.createStyle(Constants.CONST_STYLE_TITLE, null, CellStyle.NO_FILL, false,
                Horizontal.LEFT, HSSFCellStyle.BORDER_NONE);
        excel.createStyle(Constants.CONST_STYLE_HEADER,
                Color.decode(Constants.CONST_COLOR_HEX_4F81BD), CellStyle.SOLID_FOREGROUND, true,
                Horizontal.CENTER, HSSFCellStyle.BORDER_NONE);
        excel.createStyle(Constants.CONST_STYLE_TABLE_HEADER,
                Color.decode(Constants.CONST_COLOR_HEX_4F81BD), CellStyle.SOLID_FOREGROUND, true,
                Horizontal.CENTER, HSSFCellStyle.BORDER_THIN);
        excel.createStyle(Constants.CONST_STYLE_FOOTER_RIGHT,
                Color.decode(Constants.CONST_COLOR_HEX_4F81BD), CellStyle.SOLID_FOREGROUND, true,
                Horizontal.RIGHT, HSSFCellStyle.BORDER_NONE);
        excel.createStyle(Constants.CONST_STYLE_TABLE_BODY, null, CellStyle.NO_FILL, true,
                Horizontal.LEFT, HSSFCellStyle.BORDER_NONE);
        excel.createStyle(Constants.CONST_STYLE_TABLE_BODY_CENTER, null, CellStyle.NO_FILL, true,
                Horizontal.CENTER, HSSFCellStyle.BORDER_THIN);
        excel.createStyle(Constants.CONST_STYLE_TABLE_BODY_NUMBER_BOLD, null, CellStyle.NO_FILL,
                true, Horizontal.RIGHT, HSSFCellStyle.BORDER_NONE);
    }
   
    /**
     * Creates the font style.
     *
     * @param excel the excel
     */
    private static void createFontStyle(ExcelUtils excel) {
    	excel.setFontStyle(Constants.CONST_FONT_TITLE, Constants.CONST_STYLE_TITLE);
        excel.setFontStyle(Constants.CONST_FONT_HEADER, Constants.CONST_STYLE_HEADER);
        excel.setFontStyle(Constants.CONST_FONT_TABLE_HEADER, Constants.CONST_STYLE_TABLE_HEADER);
        excel.setFontStyle(Constants.CONST_FONT_TABLE_HEADER, Constants.CONST_STYLE_FOOTER_RIGHT);
        excel.setFontStyle(Constants.CONST_FONT_TABLE_BODY, Constants.CONST_STYLE_TABLE_BODY_CENTER);
        excel.setFontStyle(Constants.CONST_FONT_TABLE_BODY_NUMBER_BOLD,
                Constants.CONST_STYLE_TABLE_BODY_NUMBER_BOLD);
    }

}
