package pe.com.sedapal.scr.correo.core.common;

public class Constants {

	public static final String ESTADO_ACTIVO = "1";
	public static final String ESTADO_INACTIVO = "2";
	public static final String PROCESO_AUTOMATICO = "1";
	public static final String PACKAGE_RIO = "PCK_RIO";
	
	public static final String SP_LISTA_RIO = "PRC_LISTA_RIO";
	
	// PARAMETERS INPUTS
	// MANT RIO
	public static final String PAR_VNOMBRE_RIO = "PAR_VNOMBRE_RIO";
	public static final String PAR_VNOMBRE_LARGO_RIO = "PAR_VNOMBRE_LARGO_RIO";

	public static final String PAR_VABREVIATURA = "PAR_VABREVIATURA";
	public static final String PAR_VESTADO = "PAR_VESTADO";

	// BUSCAR
	public static final String PAR_TIPOOPERACION = "par_tipo_operacion";
	public static final String PAR_COLORDERBY = "par_col_order_by";
	public static final String PAR_COLORDERBYDIR = "par_col_order_by_dir";
	public static final String PAR_PAGDESDE = "par_pag_desde";
	public static final String PAR_CANTIDADPAG = "par_cantidad_pag";
	public static final String PAR_OUTQUANTITY = "par_out_quantity";
	
	public final static String ORACLE_PROCEDURES_SCHEMA = "oracle.schema.procedures";	
	public static final String P_CURSOR = "sys_refcursor";
	public static final String P_SEPARADOR = ".";	
	public static final String PAR_VUSER_UPDATE = "PAR_VUSER_UPDATE"; 
	public static final String PAR_VPROGRAM_UPDATE = "PAR_VPROGRAM_UPDATE"; 
	public static final String PAR_OUT_CURSOR = "PAR_OUT_CURSOR";
	
	public static final String PAR_TIPOPROCESO = "PAR_TIPO_PROCESO";
	public static final String PAR_PERIODO = "PAR_PERIODO";
	
	// CAUDALES
	public static final String PACKAGE_CAUDAL = "PCK_CAUDAL";
	public static final String SP_SEARCH_CAUDAL = "PRC_BUSCA_CAUDAL";
	public static final String SP_GET_CAUDET_RES = "PRC_GET_CAU_DET_RES";
	public static final String SP_GET_CAUDET_GRA = "PRC_GET_CAU_DET_GRA";
	
	public static final String SP_GET_CAUDAL2 = "PRC_GET_CAU_DET2";
	public static final String SP_INSERT_CAUDAL = "PRC_INSERT_CAUDAL";
	public static final String SP_UPDATE_CAUDAL = "PRC_UPDATE_CAUDAL";
	public static final String SP_GET_CAUDAL = "PRC_GET_CAUDAL";
	public static final String PAR_N_CODIGO = "PAR_N_CODIGO";
	public static final String PAR_N_CODRIO = "PAR_N_CODRIO";
	public static final String PAR_V_TIPROC = "PAR_V_TIPROC";
	public static final String PAR_V_PERIOD = "PAR_V_PERIOD";
	public static final String PAR_D_FHREG = "PAR_D_FHREG";
	public static final String PAR_V_ESTADO = "PAR_V_ESTADO";
	public static final String PAR_N_CODCAR = "PAR_N_CODCAR";
	public static final String PAR_D_FHPROC = "PAR_D_FHPROC";
	public static final String PAR_OUT_COD_CAU = "PAR_OUT_COD_CAU";
	public static final String TPO_PROCESO = "automatico";
	
	// CAUDAL DETALLE
	public static final String SP_INSERT_CAUDAL_DETALLE = "PRC_INSERT_CAUDAL_DETALLE";
	public static final String PAR_N_CODCAU = "PAR_N_CODCAU";
	public static final String PAR_V_DIA = "PAR_V_DIA";
	public static final String PREFIX_PAR_N_NHR = "PAR_N_NHR";
	public static final String PAR_N_PROMED = "PAR_N_PROMED";
	public static final String PAR_OUT_COD_CAU_DET = "PAR_OUT_COD_CAU_DET";
	
	// CARGA
	public static final String PACKAGE_CARGA = "PCK_CARGA";
	public static final String PAR_V_NOMCAR = "PAR_V_NOMCAR";
	public static final String PAR_V_NOMARC = "PAR_V_NOMARC";
	public static final String PAR_V_RUTA = "PAR_V_RUTA";
	public static final String PAR_D_FECHOR = "PAR_D_FECHOR";
	public static final String PAR_V_ESTCAR = "PAR_V_ESTCAR";
	public static final String PAR_V_DESCRI = "PAR_V_DESCRI";
	public static final String PROGRAMA = "automatico";
	public static final Integer CODIGO_AREA = 0;
	public static final Integer CODIGO_SISTEMA = null;
	public static final String SP_INSERT_CARGA = "PRC_INSERT_CARGA";
	public static final String ESTADO_CARGA_EXITO = "1";
	public static final String ESTADO_CARGA_FALLIDO = "2";
	public static final String PAR_OUT_COD_CAR = "PAR_OUT_COD_CAR";
	
	// AUDITORIA
	public static final String PAR_A_USUINS = "PAR_A_USUINS";
	public static final String PAR_A_FECINS = "PAR_A_FECINS";
	public static final String PAR_A_USUUPD = "PAR_A_USUUPD";
	public static final String PAR_A_FECUPD = "PAR_A_FECUPD";
	public static final String PAR_A_PROGRA = "PAR_A_PROGRA";
	public static final String PAR_A_CODARE = "PAR_A_CODARE";
	public static final String PAR_A_CODSIS = "PAR_A_CODSIS";
	
	// CORREO
	public static final String PCK_CORREO = "PCK_CORREO";
	public static final String SP_GET_CONFIG_CORREO = "PRC_GET_CONFIG_CORREO";
	public static final String PAR_V_TIPOPR = "PAR_V_TIPOPR";
	public static final String TIPO_PROCESO_REPORTE = "1";
	public static final String TIPO_PROCESO_EXTRACCION = "2";
	public static final String TIPO_CORREO_ENVIOS = "3";
	public static final String TIPO_PROCESO_REPORTE_GERENCIA = "4";
	public static final String USUARIO_ADMIN = "admin";
	public static final String CORREO_DIR_LEIDOS = "Leidos";
	
	// REPORTES
	public static final String REPORTE_RESUMEN = "ReporteResumenCaudales";
	public static final String REPORTE_GRAFICO = "ReporteGraficoCaudales";
	
	// VALIDACIONES INTERNAS
	public static final String ERROR_TIPO_RIO = "1";
	public static final String ERROR_TIPO_CAUDAL = "2";
	public static final String ERROR_TIPO_CAUDAL_PASADO = "2.1";
	public static final String ERROR_TIPO_CAUDAL_FUTURO = "2.2";
	public static final String ERROR_TIPO_SIN_CAUDAL = "3";
	public static final String ERROR_TIPO_NO_ESPERADO = "4";
	public static final String ERROR_SEPARATOR_TYPE = "-";
	
	// DEFAULTS BD
	public static final String DEFAULT_VARCHAR = " ";
	public static final Double DEFAULT_DOUBLE = 0.0;
	public static final Double DEFAULT_DETCAU_DOUBLE = -1.0;
	
	public static final Integer MAX_SIZE_DESC_ERROR = 2990; // 3000
	
	// PATHS
	public static final String PATH_SHARED_FILE = "/";
	
	public static final String UTF8_BOM = "\uFEFF";
	
	//SIZE STRING ADD TO HEAD FOR DATE (13): (" - DD/MM/YYYY").length()
	public static final Integer SIZE_DATE_HEADER_ADD = 13;
	
	//NULL VALUE FOR ROW HOUR
	public static final String NULL_VALUE_ROW_HOUR = "None";
}
