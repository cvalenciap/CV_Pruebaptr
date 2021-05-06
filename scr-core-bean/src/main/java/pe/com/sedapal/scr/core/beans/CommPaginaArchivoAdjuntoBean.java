/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class CommPaginaArchivoAdjuntoBean.
 */
public class CommPaginaArchivoAdjuntoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id pagina. */
	private String idPagina;

	/** The anho. */
	private Integer anho;

	/** The tipo. */
	private Integer tipo;

	/** The codigo. */
	private Integer codigo;

	/** The exp reg tipo archivo permitido. */
	private String expRegTipoArchivoPermitido;

	/** The peso maximo archivo. */
	private Long pesoMaximoArchivo;

	/** The peso maximo archivo app. */
	private Long pesoMaximoArchivoApp;
	
	/** The cant max archivo app. */
	private Integer cantMaxArchivoApp;

	/** The map secciones. */
	private Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones;

	/**
	 * Instantiates a new comm pagina archivo adjunto bean.
	 *
	 * @param idPagina the id pagina
	 * @param anho the anho
	 * @param tipo the tipo
	 * @param codigo the codigo
	 * @param expRegTipoArchivoPermitido the exp reg tipo archivo permitido
	 * @param pesoMaximoArchivo the peso maximo archivo
	 * @param pesoMaximoArchivoApp the peso maximo archivo app
	 * @param cantMaxArchivoApp the cant max archivo app
	 * @param mapSecciones the map secciones
	 */
	public CommPaginaArchivoAdjuntoBean(String idPagina, Integer anho, Integer tipo, Integer codigo,
			String expRegTipoArchivoPermitido, Long pesoMaximoArchivo, Long pesoMaximoArchivoApp,
			Integer cantMaxArchivoApp, Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones) {
		super();
		this.idPagina = idPagina;
		this.anho = anho;
		this.tipo = tipo;
		this.codigo = codigo;
		this.expRegTipoArchivoPermitido = expRegTipoArchivoPermitido;
		this.pesoMaximoArchivo = pesoMaximoArchivo;
		this.pesoMaximoArchivoApp = pesoMaximoArchivoApp;
		this.cantMaxArchivoApp = cantMaxArchivoApp;
		this.mapSecciones = mapSecciones;
	}

	/**
	 * Gets the id pagina.
	 *
	 * @return the id pagina
	 */
	public String getIdPagina() {
		return idPagina;
	}

	/**
	 * Sets the id pagina.
	 *
	 * @param idPagina the new id pagina
	 */
	public void setIdPagina(String idPagina) {
		this.idPagina = idPagina;
	}

	/**
	 * Gets the anho.
	 *
	 * @return the anho
	 */
	public Integer getAnho() {
		return anho;
	}

	/**
	 * Sets the anho.
	 *
	 * @param anho the new anho
	 */
	public void setAnho(Integer anho) {
		this.anho = anho;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the exp reg tipo archivo permitido.
	 *
	 * @return the exp reg tipo archivo permitido
	 */
	public String getExpRegTipoArchivoPermitido() {
		return expRegTipoArchivoPermitido;
	}

	/**
	 * Sets the exp reg tipo archivo permitido.
	 *
	 * @param expRegTipoArchivoPermitido the new exp reg tipo archivo permitido
	 */
	public void setExpRegTipoArchivoPermitido(String expRegTipoArchivoPermitido) {
		this.expRegTipoArchivoPermitido = expRegTipoArchivoPermitido;
	}

	/**
	 * Gets the peso maximo archivo.
	 *
	 * @return the peso maximo archivo
	 */
	public Long getPesoMaximoArchivo() {
		return pesoMaximoArchivo;
	}

	/**
	 * Sets the peso maximo archivo.
	 *
	 * @param pesoMaximoArchivo the new peso maximo archivo
	 */
	public void setPesoMaximoArchivo(Long pesoMaximoArchivo) {
		this.pesoMaximoArchivo = pesoMaximoArchivo;
	}

	/**
	 * Gets the map secciones.
	 *
	 * @return the map secciones
	 */
	public Map<String, CommSeccionArchivoAdjuntoBean> getMapSecciones() {
		return mapSecciones;
	}

	/**
	 * Sets the map secciones.
	 *
	 * @param mapSecciones the map secciones
	 */
	public void setMapSecciones(Map<String, CommSeccionArchivoAdjuntoBean> mapSecciones) {
		this.mapSecciones = mapSecciones;
	}

	/**
	 * Gets the peso maximo archivo app.
	 *
	 * @return the peso maximo archivo app
	 */
	public Long getPesoMaximoArchivoApp() {
		return pesoMaximoArchivoApp;
	}

	/**
	 * Sets the peso maximo archivo app.
	 *
	 * @param pesoMaximoArchivoApp the new peso maximo archivo app
	 */
	public void setPesoMaximoArchivoApp(Long pesoMaximoArchivoApp) {
		this.pesoMaximoArchivoApp = pesoMaximoArchivoApp;
	}

	/**
	 * Gets the cant max archivo app.
	 *
	 * @return the cant max archivo app
	 */
	public Integer getCantMaxArchivoApp() {
		return cantMaxArchivoApp;
	}

	/**
	 * Sets the cant max archivo app.
	 *
	 * @param cantMaxArchivoApp the new cant max archivo app
	 */
	public void setCantMaxArchivoApp(Integer cantMaxArchivoApp) {
		this.cantMaxArchivoApp = cantMaxArchivoApp;
	}

}
