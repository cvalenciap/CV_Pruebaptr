/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.common.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUtils.
 */
public class FileUtils {

	/**
	 * Método que permite obtener un archivo como inputstream.
	 *
	 * @param prefixnameFile es el prefijo del nombre del archivo
	 * @param username Es el nombre de usuario del usuario logueado en el Sistema
	 * @param extension Es la extensión del archivo
	 * @return the file stream
	 * @throws IOException Excepción que puede ser lanzada
	 * @Return Objeto de tipo InputStream con el resultado
	 */
	public static InputStream getFileStream(final String prefixnameFile, final String username, final String extension) throws IOException{
		InputStream inputResponse = null;
		String fileNameStarts = prefixnameFile + username;
		String directorio =  System.getProperty("java.io.tmpdir");		
		
		if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
			directorio = directorio + System.getProperty("file.separator");
		}
		
		File directory = new File(directorio);
		File[] files = directory.listFiles();
		for (File f : files)
		{
		    if (f.getName().startsWith(fileNameStarts)&&f.getName().endsWith(extension))
		    {
		      inputResponse = new FileInputStream(f);
		      break;
		    }
		}
		
		return inputResponse;
	}
	
	/**
	 * Método que permite guardar un archivo en la ruta temporal del servidor.
	 *
	 * @param uploadfile es el objeto java que representa al archivo
	 * @param prefixnameFile es el prefijo del nombre del archivo
	 * @param username Es el nombre de usuario del usuario logueado en el Sistema
	 * @param extension Es la extensión del archivo
	 * @return the string
	 * @throws IOException Excepción que puede ser lanzada
	 * @Return Objeto de tipo String con el nombre final del archivo guardado
	 */
	public static String saveFile(final MultipartFile uploadfile, final String prefixnameFile, 
			final String username, final String extension) throws IOException {
		String filename = prefixnameFile + username + DateUtil.ahoraEnCadena("yyyyMMddHHmmssSSS") + extension;
	    String directory = System.getProperty("java.io.tmpdir");	
	    String filepath = Paths.get(directory, filename).toString();
		
		BufferedOutputStream stream;
		stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		stream.write(uploadfile.getBytes());
		stream.close();
		
		return filename;
	}
	
	/**
	 * Método que permite eliminar una lista de archivos de la ruta temporal del servidor.
	 *
	 * @param prefixnameFile es el prefijo del nombre del archivo
	 * @param username Es el nombre de usuario del usuario logueado en el Sistema
	 * @param extension Es la extensión del archivo
	 * @throws IOException Excepción que puede ser lanzada
	 */
	public static void deleteFiles(final String prefixnameFile, final String username, final String extension) throws IOException{
		
		String fileNameStarts = prefixnameFile + username;
		
		String directorio =  System.getProperty("java.io.tmpdir");		
		
		if ( !(directorio.endsWith("/") || directorio.endsWith("\\")) ){
			directorio = directorio + System.getProperty("file.separator");
		}
		
		File directory = new File(directorio);
		File[] files = directory.listFiles();
		for (File f : files)
		{
		    if (f.getName().startsWith(fileNameStarts)&&f.getName().endsWith(extension))
		    {
		      f.delete();
		    }
		}
	}
	
	/**
	 * Método que permite leer un archivo XLS y pasar el contenido a una estructura de datos de listas que representan una matriz.
	 *
	 * @param inputStreamFile es el objeto archivo
	 * @param numHoja Es el número de hoja del excel del cual se leerán los datos
	 * @return Objeto de listas que representan una matriz con el contenido del archivo
	 * @throws IOException Excepción que puede ser lanzada
	 * @throws EncryptedDocumentException the encrypted document exception
	 * @throws InvalidFormatException the invalid format exception
	 */
	public static List<List<String>> readXlsFile(InputStream inputStreamFile, int numHoja) throws IOException, EncryptedDocumentException, InvalidFormatException{
		List<List<String>> rows = new ArrayList<List<String>>();
		Workbook workbook = WorkbookFactory.create(inputStreamFile);
        Sheet firstSheet = workbook.getSheetAt(numHoja);
        Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<String> col = new ArrayList<String>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

            	switch (cell.getCellType()) {
            		case Cell.CELL_TYPE_BLANK:
            			col.add("");
                    	break;
                    case Cell.CELL_TYPE_STRING:
                        col.add(cell.getStringCellValue().trim());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                    	col.add(cell.getBooleanCellValue() + "");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                    	col.add(new BigDecimal(cell.getStringCellValue()).stripTrailingZeros().toPlainString());
                        break;
            	}
            
            }
            rows.add(col);
        }
         
        workbook.close();
        inputStreamFile.close();
        
        return rows;
	}
	
	/**
	 * Método que permite pintar en consola el contenido de un archivo XLS.
	 *
	 * @param inputStreamFile es el objeto archivo
	 * @throws IOException Excepción que puede ser lanzada
	 * @throws EncryptedDocumentException Excepción que puede ser lanzada
	 * @throws InvalidFormatException Excepción que puede ser lanzada
	 */
	public static void printXlsFile(InputStream inputStreamFile) throws IOException, EncryptedDocumentException, InvalidFormatException{
		Workbook workbook = WorkbookFactory.create(inputStreamFile);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                 
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
         
        workbook.close();
        inputStreamFile.close();
	}
	
	/**
	 * Método que permite leer un archivo CSV y pasar el contenido a una estructura de datos de listas que representan una matriz.
	 *
	 * @param inputStreamFile es el objeto archivo
	 * @param bFile Es el flujo de bytes
	 * @param separator Es el caracter separador de
	 * @return Objeto de listas que representan una matriz con el contenido del archivo
	 */
	public static List<List<String>> readTextFile(InputStream inputStreamFile, byte[] bFile, String separator){
		List<List<String>> rows = new ArrayList<List<String>>();
		try {
			inputStreamFile.read(bFile);
			inputStreamFile.close();
			for (int i = 0; i < bFile.length; i++) {
				String[] cols = String.valueOf((char) bFile[i]).split(separator);
				List<String> col = new ArrayList<String>();
				for(String cellValue : cols){
					col.add(cellValue);
				}
				rows.add(col);
			}
		} catch (Exception e) {
			List<String> empty = new ArrayList<String>();
			rows.add(empty);
			return rows;
		}

		return rows;
	}
	
	/**
	 * Método que que realiza la descarga de un archivo.
	 *
	 * @param fileName Es el nombre del archivo a descargar
	 * @param bufferSize Es el tamaño del buffer para la descarga
	 * @param response Es el objeto de respuesta a la capa vista
	 * @throws IOException Excepción que puede ser lanzada
	 */
	public static void downloadFile(String fileName, int bufferSize, HttpServletResponse response) throws IOException {
		// get absolute path of the application
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File downloadFile = new File(classloader.getResource(Constants.DOWNLOADS_PATH+fileName).getFile());

		// construct the complete absolute path of the file
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = URLConnection.guessContentTypeFromName(downloadFile.getName());
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(ConstantsCommon.CONTENT_DISPOSITION, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[bufferSize];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}
}
