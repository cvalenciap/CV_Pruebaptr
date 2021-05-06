/**
 * 
 * Resumen.
 * Objeto 				: CorreoCargaDaoImpl
 * Descripción 			: Clase implementadora de la interfaz de carga
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.Carga;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.AbstractJdbcDao;
import pe.com.sedapal.scr.correo.dao.ICargaDao;
import pe.com.sedapal.scr.correo.util.ExecuteProcedure;

@Repository
public class CorreoCargaDaoImpl extends AbstractJdbcDao implements ICargaDao {
	
	@Autowired
	private Environment environment;
	
	/**
	 * Método que permite registrar carga
	 * @Return Objeto de tipo Integer que contiene código de carga 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer registrarCarga(Carga carga) throws GmdException {
		Integer intCodigoCarga = null;
		
		try {
			String spName = Constants.PACKAGE_CARGA + Constants.P_SEPARADOR + Constants.SP_INSERT_CARGA;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_NOMCAR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_NOMARC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_RUTA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_D_FECHOR, OracleTypes.DATE));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_ESTCAR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_DESCRI, OracleTypes.VARCHAR));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUINS, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_COD_CAR, OracleTypes.INTEGER));
			
			// Creamos ejecutor de Store Procedure
			ExecuteProcedure execSp = new ExecuteProcedure(getJdbcTemplate().getDataSource(), spName,
					environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_V_NOMCAR, carga.getNombreCarga());
			inputs.put(Constants.PAR_V_NOMARC, carga.getNombreArchivo());
			inputs.put(Constants.PAR_V_RUTA, carga.getRutaArchivo());	
			inputs.put(Constants.PAR_D_FECHOR, carga.getFechaHoraRegistro());
			inputs.put(Constants.PAR_V_ESTCAR, carga.getEstado());
			inputs.put(Constants.PAR_V_DESCRI, carga.getDescripcion());
			
			inputs.put(Constants.PAR_A_USUINS, carga.getUsuarioCreacion());		
			inputs.put(Constants.PAR_A_PROGRA, carga.getPrograma());		
			inputs.put(Constants.PAR_A_CODARE, carga.getCodigoArea());		
			inputs.put(Constants.PAR_A_CODSIS, carga.getCodigoSistema());		

			// Ejecutamos el store procedure
			Map<String, Integer> outputs = execSp.executeSp(inputs);
			
			intCodigoCarga = outputs.get(Constants.PAR_OUT_COD_CAR);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GmdException(e);
		}
		return intCodigoCarga;
	}
	
}
