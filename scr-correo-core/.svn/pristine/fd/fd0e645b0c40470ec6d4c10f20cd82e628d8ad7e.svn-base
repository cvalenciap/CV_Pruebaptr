package pe.com.sedapal.scr.correo.core.util;

import pe.com.sedapal.scr.correo.core.common.Constants;

public class ExpRegUtil {
	// *** RECONOCIMIENTO DE NOMBRE DE LOS RIOS y REPRESAS ***
	// Expresion regular para reconocer el nombre del rio
	// Ejemplo 1: CAUDALES EN EL CANAL HACIA HUAMPANI (m3/s) 
	// Ejemplo 2: VOLUMEN DE LA PRESA SHEQUE (Mm3) 
	// ([A-ZÑa-zñ ]+\((m3\/s|Mm3)\))
	public static String EXP_REG_NOMBRE_RIO = "([A-ZÑa-zñ ]+\\((m3\\/s|Mm3)\\))";
	
	// *** RECONOCIMIENTO DE LAS CABECERAS DIA/HORA DE CADA CAUDAL ***
	
	// Expresion regular para reconocer prefijo dia/hr seguido de cero o mas espacios
	private static String EXP_REG_PREFIX_DIA_HORA = "(dia\\/hr[ ]*){1}";
		
	// Expresion regular para reconocer en forma individual a cada una de las 24 horas: 01, 02, 03, ... 24
	private static String EXP_REG_HORA_24H = "(0[1-9]{1}|1[0-9]{1}|2[0-4]{1})";
	
	// Expresion regular para reconocer las 24 horas precedido por 1 o más tab \t:	h01	h02	h03	...	h24
	// Ejemplo:  	h01	h02	h03	h04	h05	h06	h07	h08	h09	h10	h11	h12	h13	h14	h15	h16	h17	h18	h19	h20	h21	h22	h23	h24	
	// (( |\t)h+(0[1-9]{1}|1[0-9]{1}|2[0-4]{1})){1,24}
	private static String EXP_REG_TAB_HORA_24H = "(( |\t)h+" + EXP_REG_HORA_24H + "){1,24}";
	
	// Expresion regular para reconocer la fila entera de horas del archivo de caudales
	// Ejemplo cabecera: dia/hr   	h01	h02	h03	h04	h05	h06	h07	h08	h09	h10	h11	h12	h13	h14	h15	h16	h17	h18	h19	h20	h21	h22	h23	h24	
	// (dia\/hr[ ]*){1}(( |\t)+(0[1-9]{1}|1[0-9]{1}|2[0-4]{1})){1,24}
	public static String EXP_REG_CABECERA_CAUDAL_MEDIDA = EXP_REG_PREFIX_DIA_HORA + EXP_REG_TAB_HORA_24H;
	
	// *** RECONOCIMIENTO DE LAS MEDIDAS DEL CAUDAL DE CADA DIA
	
	// Expresion regular para reconocer nro dias: 01, 02, 03, ..., 28, 29, 30, 31
	private static String EXP_REG_DIA = "(0[1-9]{1}|1[0-9]{1}|2[0-9]{1}|3[0-1]{1})";
	
	// Expresion regular para reconocer prefijo DIA_XX, DONDE XX CORRESPONDE AL NRO DIA: 
	// Ejemplo: DIA_01 , DIA_02 , DIA_03 , ..., DIA_28 , DIA_29 , DIA_30 , DIA_31 
	// DIA_(0[1-9]{1}|1[0-9]{1}|2[0-9]{1}|3[0-1]{1})[ ]*
	public static String EXP_REG_PREFIX_DIA = "(DIA|dia|día|DÍA)_" + EXP_REG_DIA + "[ ]*";
	
	// Expresion regular para reconocer valor nulo de medida del dia: 
	// Ejemplo: None
	// (( |\t)None)	
	public static String EXP_REG_FILA_EMPTY = ")?(|\t" + Constants.NULL_VALUE_ROW_HOUR + ")";
	
	// Expresion regular para reconocer valor de la medida del caudal: 23.3, 7, 0.23, 2.345, ...
	// [0-9]+(\.[0-9]+)?
	public static String EXP_REG_HORA_MEDIDA_CAUDAL = "[0-9]+(\\.[0-9]+)?";
	
	// Expresion regular para reconocer la fila entera de medidas del dia + None por registro nulo
	// Ejemplo: 	9	8	8	8	7	6.5	14	8.8	17.4	16.9	16.9	21	20.5	19.5	10	11.7	18.8	18.5	18.5	20	None	21	None	None	
	// (( |\t)+[0-9]+(\.[0-9]+)?){1,24}
	private static String EXP_REG_DIA_MEDIDA_CAUDAL = "((( |\t)+" + EXP_REG_HORA_MEDIDA_CAUDAL + EXP_REG_FILA_EMPTY + "){1,24}";
	
	// Expresion regular para reconocer la fila entera de medidas del dia con su prefijo.
	// Ejemplo: DIA_02 	9	8	8	8	7	6.5	14	8.8	17.4	16.9	16.9	21	20.5	19.5	10	11.7	18.8	18.5	18.5	20	None	21	None	None	
	// DIA_(0[1-9]{1}|1[0-9]{1}|2[0-9]{1}|3[0-1]{1})[ ]*(( |\t)+[0-9]+(\.[0-9]+)?){1,24}
	public static String EXP_REG_FILA_MEDIDA_CAUDAL = EXP_REG_PREFIX_DIA + EXP_REG_DIA_MEDIDA_CAUDAL;
		
	// Expresion regular para reconocer una ruta compartida
	// Ejemplo: \\192.168.150.173\
	public static String EXP_REG_RUTA_CARPETA_COMPARTIA = "";
}
