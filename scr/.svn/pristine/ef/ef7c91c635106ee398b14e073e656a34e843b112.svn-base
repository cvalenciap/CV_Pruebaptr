/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilsController.
 *
 * @author Luis Sanchez
 * @version 1.0
 * 09/06/2017
 * La clase UtilsController.java ha 
 * sido creada con el fin de proporcionar
 * metodos utilitarios de las clases
 * controladoras
 */
public class UtilsController {

    /**
     * Instantiates a new utils controller.
     */
    private UtilsController() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets the file.
     *
     * @param bytes the bytes
     * @param file the file
     * @param contentType the content type
     * @return the file
     */
    public static ResponseEntity<InputStreamResource> getFile(byte[] bytes, String file,
            String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_ENCODING, "UTF-8");
        headers.setContentDispositionFormData("attachment", file);
        ByteArrayInputStream array = new ByteArrayInputStream(bytes);

        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new InputStreamResource(array));
    }
}
