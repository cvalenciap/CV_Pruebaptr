/**
 * 
 * Resumen.
 * Objeto 				: CorreoCaudalDaoImpl
 * Descripción 			: Clase implementadora de la interfaz (de acceso a datos) de caudal
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.Caudal;
import pe.com.sedapal.scr.correo.core.beans.ReporteGraficoBean;
import pe.com.sedapal.scr.correo.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.AbstractJdbcDao;
import pe.com.sedapal.scr.correo.dao.ICaudalDao;
import pe.com.sedapal.scr.correo.mapper.CaudalMapper;
import pe.com.sedapal.scr.correo.util.ExecuteProcedure;

@Repository
public class CorreoCaudalDaoImpl extends AbstractJdbcDao implements ICaudalDao {
	
	@Autowired
	private Environment environment;
	
	/**
	 * Método que permite obtener detalles de caudales para un periodo
	 * @Return Objeto de tipo ReporteResumenBean que contiene los datos detalles de caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ReporteResumenBean> generarReporteResumenCaudal(String periodo) throws Exception {
		List<ReporteResumenBean> lstRetorno = null; 
		
			SimpleJdbcCall caller = new SimpleJdbcCall(getJdbcTemplate().getDataSource());
			caller.withCatalogName(Constants.PACKAGE_CAUDAL).withProcedureName(Constants.SP_GET_CAUDET_RES).declareParameters(    					
    					new SqlParameter(Constants.PAR_PERIODO, Types.VARCHAR),						
						new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteResumenBean>() {

							@Override
							public ReporteResumenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								ReporteResumenBean record = new ReporteResumenBean();								
								record.setPERIODO(rs.getString(1)); // PERIODO
								record.setNOMBRERIO(rs.getString(2)); // V_NOMBRERIO
								record.setDIA(rs.getString(3)); // V_DIA
								record.setNHR01(rs.getBigDecimal(4)); // N_NHR01
								record.setNHR02(rs.getBigDecimal(5)); // N_NHR02 		 						
								record.setNHR03(rs.getBigDecimal(6)); // N_NHR03
								record.setNHR04(rs.getBigDecimal(7)); // N_NHR04
								record.setNHR05(rs.getBigDecimal(8)); // N_NHR05
								record.setNHR06(rs.getBigDecimal(9)); // N_NHR06
								record.setNHR07(rs.getBigDecimal(10)); // N_NHR07
								record.setNHR08(rs.getBigDecimal(11)); // N_NHR08		 						
								record.setNHR09(rs.getBigDecimal(12)); // N_NHR09
								record.setNHR10(rs.getBigDecimal(13)); // N_NHR10
								record.setNHR11(rs.getBigDecimal(14)); // N_NHR11
								record.setNHR12(rs.getBigDecimal(15)); // N_NHR12
								record.setNHR13(rs.getBigDecimal(16)); // N_NHR13
								record.setNHR14(rs.getBigDecimal(17)); // N_NHR14		 						
								record.setNHR15(rs.getBigDecimal(18)); // N_NHR15
								record.setNHR16(rs.getBigDecimal(19)); // N_NHR16
								record.setNHR17(rs.getBigDecimal(20)); // N_NHR17
								record.setNHR18(rs.getBigDecimal(21)); // N_NHR18
								record.setNHR19(rs.getBigDecimal(22)); // N_NHR19
								record.setNHR20(rs.getBigDecimal(23)); // N_NHR20		 						
								record.setNHR21(rs.getBigDecimal(24)); // N_NHR21
								record.setNHR22(rs.getBigDecimal(25)); // N_NHR22
								record.setNHR23(rs.getBigDecimal(26)); // N_NHR23
								record.setNHR24(rs.getBigDecimal(27)); // N_NHR24
								record.setPROMED(rs.getBigDecimal(28)); // N_PROMED								
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_PERIODO, periodo);		
		
		Map<String,Object>  results = caller.execute(params);		
		lstRetorno =  (List) results.get(Constants.PAR_OUT_CURSOR);
		
        return lstRetorno;
	}
	
	/**
	 * Método que permite registrar caudal
	 * @Return Objeto de tipo Integer que contiene código de caudal 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer registrarCaudal(Caudal caudal) throws GmdException {
		Integer codigoCaudal = null;
		
		try {
			String spName = Constants.PACKAGE_CAUDAL + Constants.P_SEPARADOR + Constants.SP_INSERT_CAUDAL;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CODRIO, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_TIPROC, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_RUTA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_PERIOD, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_ESTADO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_NOMARC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CODCAR, OracleTypes.INTEGER));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUINS, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_COD_CAU, OracleTypes.INTEGER));
			
			// Creamos ejecutor de Store Procedure
			ExecuteProcedure execSp = new ExecuteProcedure(getJdbcTemplate().getDataSource(), spName,
					environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_CODRIO, Integer.parseInt(caudal.getRioBean().getCodigo()));
			inputs.put(Constants.PAR_V_TIPROC, caudal.getStrTipoProceso());
			inputs.put(Constants.PAR_V_RUTA, caudal.getStrRuta());
			inputs.put(Constants.PAR_V_PERIOD, caudal.getStrPeriodo());
			inputs.put(Constants.PAR_V_ESTADO, caudal.getStrEstado());
			inputs.put(Constants.PAR_V_NOMARC, caudal.getStrNombreArchivo());
			inputs.put(Constants.PAR_N_CODCAR, caudal.getIntCodigoCarga());
			
			inputs.put(Constants.PAR_A_USUINS, caudal.getUsuarioCreacion());		
			inputs.put(Constants.PAR_A_PROGRA, caudal.getPrograma());		
			inputs.put(Constants.PAR_A_CODARE, caudal.getCodigoArea());		
			inputs.put(Constants.PAR_A_CODSIS, caudal.getCodigoSistema());		

			// Ejecutamos el store procedure
			Map<String, Integer> outputs = execSp.executeSp(inputs);
			
			codigoCaudal = outputs.get(Constants.PAR_OUT_COD_CAU);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GmdException(e);
		}
		
		return codigoCaudal;
	}
	
	/**
	 * Método que permite actualizar caudal
	 * @Return No hay objeto de retorno 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Transactional
	@Override
	public void actualizarCaudal(Caudal caudal) throws GmdException {
		try {
			String spName = Constants.PACKAGE_CAUDAL + Constants.P_SEPARADOR + Constants.SP_UPDATE_CAUDAL;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CODIGO, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_TIPROC, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_RUTA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_D_FHREG, OracleTypes.DATE));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_ESTADO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_NOMARC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CODCAR, OracleTypes.INTEGER));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUUPD, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			// Creamos ejecutor de Store Procedure
			ExecuteProcedure execSp = new ExecuteProcedure(getJdbcTemplate().getDataSource(), spName,
					environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_CODIGO, caudal.getIntCodigo());
			inputs.put(Constants.PAR_V_TIPROC, caudal.getStrTipoProceso());
			inputs.put(Constants.PAR_V_RUTA, caudal.getStrRuta());
			inputs.put(Constants.PAR_D_FHREG, caudal.getDteFechaHoraRegistro());
			inputs.put(Constants.PAR_V_ESTADO, caudal.getStrEstado());
			inputs.put(Constants.PAR_V_NOMARC, caudal.getStrNombreArchivo());
			inputs.put(Constants.PAR_N_CODCAR, caudal.getIntCodigoCarga());
			
			inputs.put(Constants.PAR_A_USUUPD, caudal.getUsuarioModificacion());		
			inputs.put(Constants.PAR_A_PROGRA, caudal.getPrograma());		
			inputs.put(Constants.PAR_A_CODARE, caudal.getCodigoArea());		
			inputs.put(Constants.PAR_A_CODSIS, caudal.getCodigoSistema());		

			// Ejecutamos el store procedure
			execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GmdException(e);
		}
	}
	
	/**
	 * Método que permite obtener caudales para un periodo y sobre un estado
	 * @Return Objeto de tipo Caudal que contiene los datos caudales 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Caudal obtenerCaudal(Integer intCodigoRio, String strPeriodo, String strEstado) throws GmdException {
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(this.getJdbcTemplate().getDataSource());
			
			caller.withCatalogName(Constants.PACKAGE_CAUDAL)
			      .withProcedureName(Constants.SP_GET_CAUDAL)
			      .declareParameters(
					new SqlParameter(Constants.PAR_V_PERIOD, Types.VARCHAR),
					new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR),
					new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new CaudalMapper()))
			      .withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(Constants.PAR_N_CODRIO, intCodigoRio);
			params.addValue(Constants.PAR_V_PERIOD, strPeriodo);
			params.addValue(Constants.PAR_V_ESTADO, Constants.ESTADO_ACTIVO);

			Map<String, Object> results = caller.execute(params);
			List<Caudal> lstCaudales = (List<Caudal>) results.get(Constants.PAR_OUT_CURSOR);
			
			if(lstCaudales != null && lstCaudales.size() > 0) {
				return lstCaudales.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GmdException(ex);
		}
	}
	
	/**
	 * Método que permite obtener promedios diarios para un periodo
	 * @Return Objeto de tipo ReporteGraficoBean que contiene los datos promedios diarios 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteGraficoBean> generarReporteGraficoCaudal(String periodo) throws Exception {
		List<ReporteGraficoBean> lstRetorno = null; 
		
		SimpleJdbcCall caller = new SimpleJdbcCall(getJdbcTemplate().getDataSource());
		caller.withCatalogName(Constants.PACKAGE_CAUDAL).withProcedureName(Constants.SP_GET_CAUDET_GRA).declareParameters(    					
					new SqlParameter(Constants.PAR_PERIODO, Types.VARCHAR),						
					new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteGraficoBean>() {

						@Override
						public ReporteGraficoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ReporteGraficoBean record = new ReporteGraficoBean();
							record.setPERIODO(rs.getString(1)); // PERIODO
							record.setNOMRIO(rs.getString(2)); // NOMRIO
							record.setDIA(rs.getString(3)); // DIA
							record.setPROMED(rs.getBigDecimal(4)); // PROMED																
							return record;
						}
					} )						
			).withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));
	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_PERIODO, periodo);		
	
		Map<String,Object>  results = caller.execute(params);		
		lstRetorno =  (List) results.get(Constants.PAR_OUT_CURSOR);
	
		return lstRetorno;
	}
}
