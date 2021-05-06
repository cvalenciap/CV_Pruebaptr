package pe.com.sedapal.scr.correo.core.common;

public class Messages {
	
//	public static final String MENSAJE_RIOS_NO_VALIDOS = "No se corresponden los ríos registrados en Base de Datos y archivo de caudal";
	public static final String MENSAJE_CORREO_NO_VALIDO = "Ning&uacute;n correo nuevo cumple con algunas o todas de las siguientes validaciones: "
			+ "<br />- Remitente no coincide con el configurado<br />- Asunto no coincide con el configurado"
			+ "<br />- Nombre de Archivo adjunto no coincide con el configurado<br />- No hay correos con elementos adjuntos";
	public static final String MENSAJE_NO_NUEVOS = "No se encontraron mensajes nuevos en la bandeja";
	public static final String MENSAJE_NO_HAY_CONFIGURACION = "No se encontr&oacute; correo configurado activo para el proceso";
	public static final String MENSAJE_SERVFILE_NO_VALIDO = "No hay acceso para la ruta del servidor de archivos. El proceso no fue realizado";
	
	public static final String MENSAJE_ARCHIVO_ERRORES_HEADER = "Se encontraron los siguientes errores:<br />";
	public static final String MENSAJE_ARCHIVO_ERRORES_RIO = "<br />- Los siguientes r&iacute;os no se encuentran registrados o no est&aacute;n activos:<br />";
	public static final String MENSAJE_ARCHIVO_ERRORES_CAUDAL = "<br />- Los siguientes r&iacute;os presentan errores en la informaci&oacute;n de sus caudales:<br />";
	public static final String MENSAJE_ARCHIVO_ERRORES_CAUDAL_PASADO = "<br />- Los siguientes r&iacute;os no presentan informaci&oacute;n hasta el d&iacute;a actual:<br />";
	public static final String MENSAJE_ARCHIVO_ERRORES_CAUDAL_FUTURO = "<br />- Los siguientes r&iacute;os presentan informaci&oacute;n de d&iacute;as posteriores al actual:<br />";
	public static final String MENSAJE_ARCHIVO_ERRORES_SIN_CAUDAL = "<br />- Los siguientes r&iacute;os no presentan informaci&oacute;n de caudal:<br />";
	
	public static final String MENSAJE_ERROR_NO_ESPERADO = "Ha sucedido un error inesperado. Verifique sus configuraciones nuevamente";
	
	public static final String HTML_NEWLINE = "<br />";
	public static final String HTML_CHECK = "&#10003; ";
	public static final String HTML_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
	
	public static final String PARAMETRO_1 = "{param_1}";
	public static final String PARAMETRO_2 = "{param_2}";
	
	// ERROR EN PROCESO
	public static final String MENSAJE_ERROR_PROCESO = "ERROR EN PROCESO";
	
}
