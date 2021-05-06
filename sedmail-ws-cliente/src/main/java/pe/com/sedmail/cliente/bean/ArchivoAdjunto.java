package pe.com.sedmail.cliente.bean;

import java.io.Serializable;

public class ArchivoAdjunto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreArchivo;
	private String fileString;
	private String extension;
	private String urlArchivo;
	private Integer sizeArchivo;
	private byte[] bytesArchivo;
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getFileString() {
		return fileString;
	}
	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getUrlArchivo() {
		return urlArchivo;
	}
	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}
	public Integer getSizeArchivo() {
		return sizeArchivo;
	}
	public void setSizeArchivo(Integer sizeArchivo) {
		this.sizeArchivo = sizeArchivo;
	}
	public byte[] getBytesArchivo() {
		return bytesArchivo;
	}
	public void setBytesArchivo(byte[] bytesArchivo) {
		this.bytesArchivo = bytesArchivo;
	}
	
}
