package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.OximetroBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IOximetroDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class OximetroDaoImpl implements IOximetroDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;

	@SuppressWarnings("unchecked")
	@Override
	public List<OximetroBean> obtenerLtaOximetroTodo() throws GmdException {
		List<OximetroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_OXIMETRO).withProcedureName(ConstantesSptar.PRC_LIST_OXIMETRO_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<OximetroBean>() {
									@Override
									public OximetroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										OximetroBean record = new OximetroBean();
										record.setIdOximetro(rs.getInt(1));
										record.setIdSensor(rs.getInt(2));
										record.setDescripcionSensor(rs.getString(3));
										record.setFechaRegistro(rs.getDate(4));
										record.setNumAirePdte(rs.getDouble(5));
										record.setNumSaturacionAirePdte(rs.getDouble(6));
										record.setNumSolucionPdte(rs.getDouble(7));
										record.setNumSaturacionSolucionPdte(rs.getDouble(8));
										record.setDescripcionObservacion(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setDescripcionEstado(rs.getString(11));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<OximetroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OximetroBean> obtenerLtaOximetro() throws GmdException {
		List<OximetroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_OXIMETRO).withProcedureName(ConstantesSptar.PRC_LIST_OXIMETRO)
				.declareParameters(
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<OximetroBean>() {
									@Override
									public OximetroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										OximetroBean record = new OximetroBean();
										record.setIdOximetro(rs.getInt(1));
										record.setIdSensor(rs.getInt(2));
										record.setDescripcionSensor(rs.getString(3));
										record.setFechaRegistro(rs.getDate(4));
										record.setNumAirePdte(rs.getDouble(5));
										record.setNumSaturacionAirePdte(rs.getDouble(6));
										record.setNumSolucionPdte(rs.getDouble(7));
										record.setNumSaturacionSolucionPdte(rs.getDouble(8));
										record.setDescripcionObservacion(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setDescripcionEstado(rs.getString(11));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<OximetroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularOximetro(OximetroBean Oximetro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_OXIMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_OXIMETRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_OXIMETRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_OXIMETRO, Oximetro.getIdOximetro());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, Oximetro.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, Oximetro.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarOximetro(OximetroBean Oximetro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_OXIMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SENSOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CAIRE_PDTE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CAIRE_SATU, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CSOL_PDTE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CSOL_SATU, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_OXIMETRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_OXIMETRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_OXIMETRO, Oximetro.getIdOximetro());
			inputs.put(ConstantesSptar.PAR_N_ID_SENSOR, Oximetro.getIdSensor());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, Oximetro.getFechaRegistro());
			inputs.put(ConstantesSptar.PAR_N_CAIRE_PDTE, Oximetro.getNumAirePdte());
			inputs.put(ConstantesSptar.PAR_N_CAIRE_SATU, Oximetro.getNumSaturacionAirePdte());
			inputs.put(ConstantesSptar.PAR_N_CSOL_PDTE, Oximetro.getNumSolucionPdte());
			inputs.put(ConstantesSptar.PAR_N_CSOL_SATU, Oximetro.getNumSaturacionSolucionPdte());
			inputs.put(ConstantesSptar.PAR_V_OBSERVACION, Oximetro.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, Oximetro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, Oximetro.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, Oximetro.getDeTermModi());				
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public OximetroBean registrarOximetro(OximetroBean Oximetro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SENSOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CAIRE_PDTE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CAIRE_SATU, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CSOL_PDTE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_CSOL_SATU, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_OXIMETRO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_OXIMETRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_OXIMETRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SENSOR, Oximetro.getIdSensor());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, ParametrosUtil.getFechaActualToDate());
			inputs.put(ConstantesSptar.PAR_N_CAIRE_PDTE, Oximetro.getNumAirePdte());
			inputs.put(ConstantesSptar.PAR_N_CAIRE_SATU, Oximetro.getNumSaturacionAirePdte());
			inputs.put(ConstantesSptar.PAR_N_CSOL_PDTE, Oximetro.getNumSolucionPdte());
			inputs.put(ConstantesSptar.PAR_N_CSOL_SATU, Oximetro.getNumSaturacionSolucionPdte());
			inputs.put(ConstantesSptar.PAR_V_OBSERVACION, Oximetro.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, Oximetro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, Oximetro.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, Oximetro.getDeTermCrea());	
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_OXIMETRO));			
			Oximetro.setIdOximetro(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return Oximetro;
	}
	
}
