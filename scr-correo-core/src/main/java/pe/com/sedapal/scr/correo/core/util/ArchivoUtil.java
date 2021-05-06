/**
 * 
 * Resumen.
 * Objeto 				: ArchivoUtil
 * Descripción 			: Clase relacionada con el renombrar un archivo
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class ArchivoUtil {
	
	/**
	 * Método que permite otorgar formato al nombre de un archivo
	 * @Return Objeto de tipo String que contiene el nombre del archivo 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public static String formatearNombreArchivo(String nombreArchivo) {
		if(nombreArchivo != null) {
			int intPosDot = nombreArchivo.lastIndexOf(".");
			String fileName = nombreArchivo.substring(0, intPosDot);
			String strExtension = nombreArchivo.substring(intPosDot + 1);
			
			strExtension = strExtension != null ? strExtension : "txt";
			
			String newFileName = fileName + "_" + FechaUtil.getFechaHoraActual() + "." + strExtension;
			
			return newFileName;
		} else {
			return null;
		}
	}
	
	public static void copyFileUsingJcifs(final String userName,
            final String password, final String sourcePath,
            final String destinationPath) throws IOException {
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(
//                user);
//        final SmbFile sFile = new SmbFile(destinationPath, auth);
        final SmbFile sFile = new SmbFile("smb:"+destinationPath);
        final SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(
                sFile);
        final FileInputStream fileInputStream = new FileInputStream(new File(
                sourcePath));
        final byte[] buf = new byte[16 * 1024 * 1024];
        int len;
        while ((len = fileInputStream.read(buf)) > 0) {
            smbFileOutputStream.write(buf, 0, len);
        }
        fileInputStream.close();
        smbFileOutputStream.close();
    }
	
	public static void copyFileUsingJcifs(final InputStream source,
            final String destinationPath) throws IOException {
        final SmbFile sFile = new SmbFile("smb:"+destinationPath);
        final SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(
                sFile);
        final byte[] buf = new byte[16 * 1024 * 1024];
        int len;
        while ((len = source.read(buf)) > 0) {
            smbFileOutputStream.write(buf, 0, len);
        }
        source.close();
        smbFileOutputStream.close();
    }
	
	public static BufferedReader getReaderUsingJcifs(final String path) throws IOException{
		final SmbFile sFile = new SmbFile("smb:"+path);
		return new BufferedReader(new InputStreamReader(new SmbFileInputStream(sFile)));
	}
}
