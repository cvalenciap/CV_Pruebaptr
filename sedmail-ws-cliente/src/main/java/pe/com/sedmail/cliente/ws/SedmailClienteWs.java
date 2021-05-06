package pe.com.sedmail.cliente.ws;

import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedmail.cliente.bean.ArchivoAdjunto;
import pe.com.sedmail.cliente.bean.ParametrosCorreo;
import pe.com.sedmail.cliente.bean.ResponseBean;
import pe.com.sedmail.cliente.util.ConstantesCliente;
import pe.com.sedmail.cliente.util.JsonUtil;

@PropertySource("classpath:pe/com/sedmail/cliente/ws/config/configClient.properties")
public class SedmailClienteWs {
	
	private ResponseBean respuestaBean = new ResponseBean();

	@Value("#{configClient['urlws']}")
	private String urlWs = "http://localhost:8099/sedmail-rest-web/sedmail";
	private RestTemplate restTemplate;
	
	public RestTemplate getRestTemplate(){
		return restTemplate;
	}
	
	public void setRestTemplate(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}
	
	public ResponseBean enviarCorreo(ParametrosCorreo parametros, String usuario, String password) throws GmdException{
		String urlMethod = "/correo/enviarCorreo";
		try{
			String paramValue = JsonUtil.convertirObjetoACadenaJson(parametros);
			/*set configuration header*/
			HttpHeaders headers = new HttpHeaders();
			headers.add(ConstantesCliente.AUTHORIZATION, setCredentialsAuth(usuario, password));
	        headers.setContentType(MediaType.APPLICATION_JSON);	
	        /**/
	        
			HttpEntity<String> request = new HttpEntity<String>(paramValue, headers);
			ResponseEntity<ResponseBean> response = restTemplate.exchange(urlWs + urlMethod, HttpMethod.POST, request, ResponseBean.class);
			respuestaBean = response.getBody();
		}catch(Exception exception){
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			respuestaBean.setMensajeRespuesta(format(ConstantesCliente.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstantesCliente.RESULTADO_ERROR);
		}
		return respuestaBean;
	}
	
	public ResponseBean enviarCorreoMultiPart(ParametrosCorreo parametros, String usuario, String password) throws GmdException{
		String urlMethod = "/correo/enviarCorreoMultiPart";
		List<ArchivoAdjunto> ltaAux = new ArrayList<ArchivoAdjunto>();
		try{
			/*set configuration header*/
			HttpHeaders headers = new HttpHeaders();
			headers.add(ConstantesCliente.AUTHORIZATION, setCredentialsAuth(usuario, password));
			
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			headers.setAccept(acceptableMediaTypes);
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			/*multipartValueContent*/
	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        
	        /*set content file attachment*/
	        if(parametros.getArchivosAdjuntos() != null && parametros.getArchivosAdjuntos().size() > 0){
	        	parametros.setArchivosAdjuntos(saveTempFile(parametros.getArchivosAdjuntos()));
	        	
	        	for(ArchivoAdjunto archivo : parametros.getArchivosAdjuntos()){
	        		body.add("fileString", new FileSystemResource((new File(archivo.getUrlArchivo()))));
		        }
	        }
	        
	        /*empty attachtment file value*/
	        ltaAux = parametros.getArchivosAdjuntos();
	        parametros.setArchivosAdjuntos(null);
	        String paramValue = JsonUtil.convertirObjetoACadenaJson(parametros);
			
	        body.add("map", paramValue);
	        
	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			ResponseEntity<ResponseBean> response = restTemplate.postForEntity(urlWs + urlMethod, requestEntity, ResponseBean.class);
			respuestaBean = response.getBody();
			/*delete content file attachment*/
			deleteTempFile(ltaAux);
		}catch(Exception exception){
			deleteTempFile(ltaAux);
			String[] error = MensajeExceptionUtil.obtenerMensajeError(exception);
			respuestaBean.setMensajeRespuesta(format(ConstantesCliente.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstantesCliente.RESULTADO_ERROR);
		}
		return respuestaBean;
	}
	
	private String setCredentialsAuth(String usuario, String password) throws GmdException{
		String authorization = "";
		try{
			String plainCreds = usuario + ":" + password;
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			authorization = ConstantesCliente.BASIC + new String(base64CredsBytes);
		}catch(Exception exception){
			throw new GmdException(exception);
		}
		return authorization;
	}
	
	private List<ArchivoAdjunto> saveTempFile(List<ArchivoAdjunto> listaArchivos) throws GmdException{
		try{
			for(ArchivoAdjunto archivo : listaArchivos){
				String ruta = "";
				int index = archivo.getNombreArchivo().lastIndexOf('.');
				String extension = archivo.getNombreArchivo().substring(index + 1);
				String nombreArchivoInterno = generarNombreArchivo(archivo.getNombreArchivo().substring(0, index), extension);
				ruta = System.getProperty("java.io.tmpdir") + "\\" + nombreArchivoInterno;
//				ruta = System.getProperty("java.io.tmpdir") + "\\" + archivo.getNombreArchivo();
				FileOutputStream fileOutputStream = new FileOutputStream(ruta);
				fileOutputStream.write(archivo.getBytesArchivo());
				fileOutputStream.close();
				archivo.setUrlArchivo(ruta);
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
		return listaArchivos;
	}
	
	private String generarNombreArchivo(String nombre, String extension){
		
		Calendar now = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
		
		String nombreArchivo = nombre + "." + sdf.format(now.getTime()) + "." + extension;
		
		return nombreArchivo;
	}
	
	private void deleteTempFile(List<ArchivoAdjunto> listaAdjuntos) throws GmdException {
		try{
			if(listaAdjuntos != null && listaAdjuntos.size() > 0){
				for(ArchivoAdjunto adjunto : listaAdjuntos){
					File currentFile = new File(adjunto.getUrlArchivo());
					currentFile.delete();
				}
			}
		}catch(Exception exception){
			throw new GmdException(exception);
		}
	}

}
