/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.scr.core.util.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUploadController.
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class FileUploadController {

	/** The uploaded folder. */
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://temp//";

	/**
	 * Método que permite redirigir a la vista demo de Importación de Archivos.
	 *
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/demoUpload", method = RequestMethod.GET)
	public String bandejaCaudalesGo() throws Exception {
		return "demoUpload";
	}

	/**
	 * Método que permite subir el archivo.
	 *
	 * @param uploadfile permite la abstracción completa de la tecnología de vista
	 * @return Objeto de tipo ResponseEntity con el informe del estado de la petición
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String filename = uploadfile.getOriginalFilename();
			String directory = UPLOADED_FOLDER;
			String filepath = Paths.get(directory, filename).toString();

			try {
				readFile(uploadfile.getInputStream());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	} // method uploadFile
	
	/**
	 * Método que permite imprimir los datos del archivo.
	 *
	 * @param input the input
	 * @return Objeto de tipo String con el nombre de la página o vista a redireccionar
	 * @throws EncryptedDocumentException excepción que puede ser lanzada
	 * @throws InvalidFormatException excepción que puede ser lanzada
	 * @throws IOException excepción que puede ser lanzada
	 */
	private void readFile(InputStream input) throws EncryptedDocumentException, InvalidFormatException, IOException {
		 List<List<String>> values = FileUtils.readXlsFile(input, 0);
		 for(List<String> rows : values){
			 for(String val : rows){
				 System.out.print(val + ",");
			 }
			 System.out.println();
		 }
	}
	
}
