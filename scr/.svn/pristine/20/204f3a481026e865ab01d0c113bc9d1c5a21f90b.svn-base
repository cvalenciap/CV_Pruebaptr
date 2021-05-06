/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.util;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
// TODO: Auto-generated Javadoc

/**
 * The Class ExcelUtils.
 *
 * @author Alexander Quispe
 * @version 1.0
 * 09/06/2017
 * La clase ExcelUtils.java ha sido creada con el fin
 * de brindar metodos utilitarios en la elaboracion 
 * de reportes XLS con la libreria POI
 */
public class ExcelUtils {

    /** The workbook. */
    private HSSFWorkbook workbook;
    
    /** The sheet. */
    private HSSFSheet sheet;

    /** The column. */
    private int column;
    
    /** The row. */
    private int row;

    /** The init column. */
    private short initColumn;
    
    /** The init row. */
    private int initRow;

    /** The input. */
    private InputStream input;
    
    /** The current row. */
    private HSSFRow currentRow = null;
    
    /** The current cell. */
    private HSSFCell currentCell = null;

    /** The styles. */
    private Map<String, HSSFCellStyle> styles;
    
    /** The fonts. */
    private Map<String, HSSFFont> fonts;
    
    /** The formats. */
    private Map<String, HSSFCellStyle> formats;
    
    /** The default row heigt. */
    private Short defaultRowHeigt = null;
    
    /** The helper. */
    private CreationHelper helper;
	
	/** The drawing. */
	private Drawing drawing;
	
	/** The anchor. */
	private ClientAnchor anchor;

    /**
     * Instantiates a new excel utils.
     *
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public ExcelUtils(String file) throws IOException {
        input = ExcelUtils.class.getResourceAsStream(ConstantsCommon.RUTA_REPORTES + file);
        this.workbook = new HSSFWorkbook(input);
        this.row = 0;
        this.column = 0;
        this.styles = new HashMap<>();
        this.fonts = new HashMap<>();
        this.formats = new HashMap<>();
    }

    /**
     * Instantiates a new excel utils.
     *
     * @param file the file
     * @param initRow the init row
     * @param initColumn the init column
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public ExcelUtils(String file, int initRow, short initColumn) throws IOException {
        input = ExcelUtils.class.getResourceAsStream(ConstantsCommon.RUTA_REPORTES + file);
        this.workbook = new HSSFWorkbook(input);
        this.initRow = initRow - 1;
        this.initColumn = (short) (initColumn - 1);
        this.row = this.initRow;
        this.column = this.initColumn;
        this.styles = new HashMap<>();
        this.formats = new HashMap<>();
        this.fonts = new HashMap<>();
    }
    
    /**
     * Adds the picture.
     *
     * @param image the image
     * @return the int
     */
    public int addPicture(byte[] image){
    	return this.workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
    }
    
    /**
     * Creates the picture.
     *
     * @param pictureIdx the picture idx
     */
    public void createPicture(int pictureIdx){
    	this.drawing.createPicture(this.anchor, pictureIdx);
    }
    
    /**
     * Creates the picture resources.
     */
    public void createPictureResources(){
    	this.helper = this.workbook.getCreationHelper();
    	this.drawing = this.sheet.createDrawingPatriarch();
    }
    
    /**
     * Creates the area image.
     *
     * @param rowInit the row init
     * @param rowEnd the row end
     * @param colIni the col ini
     * @param colEnd the col end
     */
    public void createAreaImage(int rowInit, int rowEnd, int colIni, int colEnd){
    	this.anchor = helper.createClientAnchor();
    	this.anchor.setCol1(colIni);
    	this.anchor.setRow1(rowInit);
    	this.anchor.setCol2(colEnd);
    	this.anchor.setRow2(rowEnd);
    	this.row = rowEnd;
        this.row++;
        this.column = this.initColumn;
    }

    /**
     * Creates the freeze pane.
     *
     * @param colSplit the col split
     * @param rowSplit the row split
     */
    public void createFreezePane(int colSplit, int rowSplit) {
        sheet.createFreezePane(colSplit, rowSplit);
    }

    /**
     * Gets the sheet.
     *
     * @return the sheet
     */
    public void getSheet() {
        sheet = workbook.getSheetAt(0);
    }

    /**
     * Gets the sheet.
     *
     * @param name the name
     * @return the sheet
     */
    public void getSheet(String name) {
        sheet = workbook.getSheet(name);
    }

    /**
     * Gets the sheet.
     *
     * @param index the index
     * @return the sheet
     */
    public void getSheet(int index) {
        sheet = workbook.getSheetAt(index);
    }

    /**
     * Sets the custom color.
     *
     * @param color the color
     * @return the HSSF color
     */
    public HSSFColor setCustomColor(Color color) {
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor custom = palette.findColor((byte) color.getRed(), (byte) color.getGreen(),
                (byte) color.getBlue());
        if (custom == null) {
            palette.setColorAtIndex(HSSFColor.LAVENDER.index, (byte) color.getRed(),
                    (byte) color.getGreen(), (byte) color.getBlue());
            custom = palette.getColor(HSSFColor.LAVENDER.index);
        }
        return custom;
    }

    /**
     * Creates the style.
     *
     * @param name the name
     * @param foreground the foreground
     * @param fillPattern the fill pattern
     * @param wrapText the wrap text
     * @param horizontal the horizontal
     */
    public void createStyle(String name, Color foreground, short fillPattern, boolean wrapText,
            Horizontal horizontal) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(wrapText);
        style.setFillForegroundColor(foreground != null ? setCustomColor(foreground).getIndex()
                : HSSFColor.AUTOMATIC.index);
        style.setFillPattern(fillPattern);
        style.setAlignment(
                (short) (horizontal != null ? horizontal.getValue() : CellStyle.ALIGN_GENERAL));
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        if (styles.containsKey(name)) {
            styles.remove(name);
        }
        styles.put(name, style);
    }
    
    /**
     * Creates the style.
     *
     * @param name the name
     * @param foreground the foreground
     * @param fillPattern the fill pattern
     * @param wrapText the wrap text
     * @param horizontal the horizontal
     * @param border the border
     */
    public void createStyle(String name, Color foreground, short fillPattern, boolean wrapText,
            Horizontal horizontal, short border) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(wrapText);
        style.setFillForegroundColor(foreground != null ? setCustomColor(foreground).getIndex()
                : HSSFColor.AUTOMATIC.index);
        style.setFillPattern(fillPattern);
        style.setAlignment(
                (short) (horizontal != null ? horizontal.getValue() : CellStyle.ALIGN_GENERAL));
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderBottom(border);
    	style.setBorderTop(border);
    	style.setBorderRight(border);
    	style.setBorderLeft(border);
        if (styles.containsKey(name)) {
            styles.remove(name);
        }
        styles.put(name, style);
    }

    /**
     * Creates the font.
     *
     * @param name the name
     * @param fontName the font name
     * @param size the size
     * @param boldWeight the bold weight
     * @param color the color
     */
    public void createFont(String name, String fontName, short size, FontWeight boldWeight,
            Color color) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setBoldweight(boldWeight != null ? boldWeight.getValue() : null);
        font.setColor(
                (short) (color != null ? setCustomColor(color).getIndex() : HSSFFont.COLOR_NORMAL));
        font.setFontName(fontName);
        if (fonts.containsKey(name)) {
            fonts.remove(name);
        }
        fonts.put(name, font);
    }

    /**
     * Gets the format.
     *
     * @param format the format
     * @return the format
     */
    private HSSFCellStyle getFormat(String format) {
        if (formats.get(format) == null) {
            HSSFCellStyle style = workbook.createCellStyle();
            style.setDataFormat(workbook.createDataFormat().getFormat(format));
            formats.put(format, style);
            return style;
        }
        return formats.get(format);
    }

    /**
     * Gets the format.
     *
     * @param format the format
     * @param styleName the style name
     * @return the format
     */
    private HSSFCellStyle getFormat(String format, String styleName) {
        String name = styleName + "-" + format;
        if (formats.get(name) == null) {
            HSSFCellStyle style = styles.get(styleName);
            style.setDataFormat(workbook.createDataFormat().getFormat(format));
            formats.put(name, style);
            return style;
        }
        return formats.get(name);
    }

    /**
     * Gets the row.
     *
     * @return the row
     */
    public void getRow() {
        currentRow =
                sheet.getRow(this.row) == null ? sheet.createRow(this.row) : sheet.getRow(this.row);
    }

    /**
     * Creates the row.
     */
    public void createRow() {
        currentRow =
                sheet.getRow(this.row) == null ? sheet.createRow(this.row) : sheet.getRow(this.row);
        if (this.defaultRowHeigt != null && this.defaultRowHeigt >= 0) {
            currentRow.setHeightInPoints(this.defaultRowHeigt);
        }
        this.row++;
        this.column = this.initColumn;
    }

    /**
     * Creates the row.
     *
     * @param rowNum the row num
     */
    public void createRow(int rowNum) {
        currentRow = sheet.createRow(rowNum);
        if (this.defaultRowHeigt != null && this.defaultRowHeigt >= 0) {
            currentRow.setHeightInPoints(this.defaultRowHeigt);
        }
        this.row = rowNum;
        this.row++;
        this.column = this.initColumn;
    }

    /**
     * Creates the cell.
     *
     * @param rowNum the row num
     * @param columnNum the column num
     */
    public void createCell(int rowNum, int columnNum) {
        HSSFRow rw =
                ((sheet.getRow(rowNum) == null) ? sheet.createRow(rowNum) : sheet.getRow(rowNum));
        if (rw.getCell(columnNum) == null) {
            rw.createCell(columnNum);
        } else {
            rw.getCell(columnNum);
        }
    }

    /**
     * Sets the data cell.
     *
     * @param value the new data cell
     */
    public void setDataCell(String value) {
        setDataCell(value, this.row, this.column);
    }

    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     */
    public void setDataCell(String value, String style) {
        setDataCell(value, style, null, null, this.row, this.column);
    }

    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param rowNum the row num
     * @param columnNum the column num
     */
    public void setDataCell(String value, int rowNum, int columnNum) {
        setDataCell(value, null, null, null, rowNum, columnNum);
    }
    
    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     * @param rowNum the row num
     * @param columnNum the column num
     */
    public void setDataCell(String value, String style, int rowNum, int columnNum) {
        setDataCell(value, style, null, null, rowNum, columnNum);
    }

    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     * @param cellType the cell type
     * @param format the format
     * @param rowNum the row num
     * @param columnNum the column num
     */
    public void setDataCell(String value, String style, CellType cellType, String format,
            int rowNum, int columnNum) {
        currentRow =
                ((sheet.getRow(rowNum) == null) ? sheet.createRow(rowNum) : sheet.getRow(rowNum));
        currentCell = currentRow.getCell(columnNum) == null ? currentRow.createCell(columnNum)
                : currentRow.getCell(columnNum);
        String val = value != null ? value : "";
        if (cellType != null && !val.isEmpty()) {
            switch (cellType) {
                case CELL_TYPE_BOOLEAN:
                    currentCell.setCellValue(Boolean.parseBoolean(val));
                    break;
                case CELL_TYPE_NUMERIC:
                    currentCell.setCellValue(Double.parseDouble(val));
                    break;
                case CELL_TYPE_STRING:
                    currentCell.setCellValue(val);
                    break;
                default:
                    currentCell.setCellValue(val);
                    break;
            }
            currentCell.setCellType(cellType.getValue());
        } else {
            currentCell.setCellValue(new HSSFRichTextString(val));
            currentCell.setCellType(CellType.CELL_TYPE_STRING.getValue());
        }
        setCellStyles(format, style);
        this.column++;
    }

    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     * @param format the format
     */
    public void setDataCell(Double value, String style, String format) {
        currentCell = currentRow.getCell(this.column) == null ? currentRow.createCell(this.column)
                : currentRow.getCell(this.column);
        if (value != null) {
            currentCell.setCellValue(value);
            currentCell.setCellType(CellType.CELL_TYPE_NUMERIC.getValue());
        }
        setCellStyles(format, style);
        this.column++;
    }
    
    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     * @param format the format
     */
    public void setDataCell(String value, String style, String format) {
        currentCell = currentRow.getCell(this.column) == null ? currentRow.createCell(this.column)
                : currentRow.getCell(this.column);
        if (value != null) {
            currentCell.setCellValue(value);
            currentCell.setCellType(CellType.CELL_TYPE_STRING.getValue());
        }
        setCellStyles(format, style);
        this.column++;
    }

    /**
     * Sets the data cell.
     *
     * @param value the value
     * @param style the style
     * @param format the format
     */
    public void setDataCell(Boolean value, String style, String format) {
        currentCell = currentRow.getCell(this.column) == null ? currentRow.createCell(this.column)
                : currentRow.getCell(this.column);
        currentCell.setCellValue(value);
        currentCell.setCellType(CellType.CELL_TYPE_BOOLEAN.getValue());
        setCellStyles(format, style);
        this.column++;
    }

    /**
     * Gets the data cell.
     *
     * @param rowNum the row num
     * @param columnNum the column num
     * @return the data cell
     */
    public String getDataCell(int rowNum, int columnNum) {
        HSSFRow rw =
                ((sheet.getRow(rowNum) == null) ? sheet.createRow(rowNum) : sheet.getRow(rowNum));
        HSSFCell cl =
                rw.getCell(columnNum) == null ? rw.createCell(columnNum) : rw.getCell(columnNum);
        return cl.getStringCellValue();
    }

    /**
     * Sets the cell styles.
     *
     * @param format the format
     * @param style the style
     */
    public void setCellStyles(String format, String style) {
        if (format != null && style != null && !format.isEmpty() && !style.isEmpty()) {
            currentCell.setCellStyle(getFormat(format, style));
        } else if (format != null && !format.isEmpty()) {
            currentCell.setCellStyle(getFormat(format));
        } else if (style != null && !style.isEmpty()) {
            currentCell.setCellStyle(styles.get(style));
        }
    }

    /**
     * Gets the area.
     *
     * @param initRow the init row
     * @param initCol the init col
     * @param endRow the end row
     * @param endCol the end col
     * @return the area
     */
    public String getArea(int initRow, int initCol, int endRow, int endCol) {
        AreaReference area = new AreaReference(new CellReference(initRow, initCol),
                new CellReference(endRow, endCol));
        return area.formatAsString();
    }

    /**
     * Merge cells.
     *
     * @param initRow the init row
     * @param initCol the init col
     * @param endRow the end row
     * @param endCol the end col
     */
    public void mergeCells(int initRow, int initCol, int endRow, int endCol) {
        AreaReference area = new AreaReference(new CellReference(initRow, initCol),
                new CellReference(endRow, endCol));
        mergeCells(area.formatAsString());
    }

    /**
     * Merge cells.
     *
     * @param area the area
     */
    public void mergeCells(String area) {
        if (CellRangeAddress.valueOf(area).getNumberOfCells() > 1)
            sheet.addMergedRegion(CellRangeAddress.valueOf(area));
    }

    /**
     * Sets the style cell.
     *
     * @param estilo the estilo
     * @param area the area
     */
    public void setStyleCell(String estilo, String area) {
        AreaReference areaReference = new AreaReference(area);
        for (CellReference cell : areaReference.getAllReferencedCells()) {
            setStyleCell(estilo, cell.getRow(), cell.getCol());
        }
    }

    /**
     * Sets the style cell.
     *
     * @param style the style
     * @param row the row
     * @param col the col
     */
    public void setStyleCell(String style, int row, int col) {
        currentRow = ((sheet.getRow(row) == null) ? sheet.createRow(row) : sheet.getRow(row));
        currentCell = currentRow.getCell(col) == null ? currentRow.createCell(col)
                : currentRow.getCell(col);
        currentCell.setCellStyle(styles.get(style));
    }

    /**
     * Sets the font style.
     *
     * @param fontName the font name
     * @param styleName the style name
     */
    public void setFontStyle(String fontName, String styleName) {
        styles.get(styleName).setFont(fonts.get(fontName));
    }

    /**
     * The Enum FontWeight.
     */
    public enum FontWeight {
        
        /** The bold. */
        BOLD(HSSFFont.BOLDWEIGHT_BOLD), 
 /** The normal. */
 NORMAL(HSSFFont.BOLDWEIGHT_NORMAL);
        
        /** The value. */
        private short value;

        /**
         * Instantiates a new font weight.
         *
         * @param value the value
         */
        private FontWeight(final short value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public short getValue() {
            return this.value;
        }
    }

    /**
     * The Enum Horizontal.
     */
    public enum Horizontal {
        
        /** The center. */
        CENTER(CellStyle.ALIGN_CENTER), 
 /** The center selection. */
 CENTER_SELECTION(CellStyle.ALIGN_CENTER_SELECTION), 
 /** The fill. */
 FILL(
                CellStyle.ALIGN_FILL), 
 /** The general. */
 GENERAL(CellStyle.ALIGN_GENERAL), 
 /** The justify. */
 JUSTIFY(
                        CellStyle.ALIGN_JUSTIFY), 
 /** The left. */
 LEFT(
                                CellStyle.ALIGN_LEFT), 
 /** The right. */
 RIGHT(CellStyle.ALIGN_RIGHT);

        /** The value. */
        private int value;

        /**
         * Instantiates a new horizontal.
         *
         * @param value the value
         */
        private Horizontal(final int value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public int getValue() {
            return this.value;
        }
    }

    /**
     * Save.
     *
     * @param file the file
     * @throws Exception the exception
     */
    public void save(OutputStream file) throws Exception {
        input.close();
        workbook.write(file);
        workbook.close();
    }

    /**
     * The Enum CellType.
     */
    public enum CellType {
        
        /** The cell type string. */
        CELL_TYPE_STRING(HSSFCell.CELL_TYPE_STRING), 
 /** The cell type blank. */
 CELL_TYPE_BLANK(
                HSSFCell.CELL_TYPE_BLANK), 
 /** The cell type boolean. */
 CELL_TYPE_BOOLEAN(
                        HSSFCell.CELL_TYPE_BOOLEAN), 
 /** The cell type error. */
 CELL_TYPE_ERROR(
                                HSSFCell.CELL_TYPE_ERROR), 
 /** The cell type formula. */
 CELL_TYPE_FORMULA(
                                        HSSFCell.CELL_TYPE_FORMULA), 
 /** The cell type numeric. */
 CELL_TYPE_NUMERIC(
                                                HSSFCell.CELL_TYPE_NUMERIC);

        /** The value. */
        private int value;

        /**
         * Instantiates a new cell type.
         *
         * @param value the value
         */
        private CellType(final int value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public int getValue() {
            return this.value;
        }
    }
    
    /**
     * Gets the current row number.
     *
     * @return the current row number
     */
    public int getCurrentRowNumber() {
        return currentRow == null ? this.row : currentRow.getRowNum();
    }

    /**
     * Gets the current column number.
     *
     * @return the current column number
     */
    public int getCurrentColumnNumber() {
        return currentCell == null ? this.column : currentCell.getColumnIndex();
    }

    /**
     * Gets the column.
     *
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the column.
     *
     * @param column the new column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Sets the row.
     *
     * @param row the new row
     */
    public void setRow(int row) {
        this.row = row;
    }

}
