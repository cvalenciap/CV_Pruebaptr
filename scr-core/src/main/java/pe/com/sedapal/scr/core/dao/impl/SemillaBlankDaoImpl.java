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
import pe.com.sedapal.scr.core.beans.SemillaBlankBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISemillaBlankDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class SemillaBlankDaoImpl implements ISemillaBlankDao {

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
	public List<SemillaBlankBean> obtenerLtaSemillaBlankTodo() throws GmdException {
		List<SemillaBlankBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_BLK).withProcedureName(ConstantesSptar.PRC_LIST_SEM_BLK_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaBlankBean>() {
									@Override
									public SemillaBlankBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaBlankBean record = new SemillaBlankBean();
										record.setIdBlank(rs.getInt(1));
										record.setNumPromedio(rs.getDouble(2));
										record.setNumInicial(rs.getDouble(3));
										record.setNumFinal(rs.getDouble(4));
										record.setNumDepletion(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaBlankBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaBlankBean> obtenerLtaSemillaBlank() throws GmdException {
		List<SemillaBlankBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_BLK).withProcedureName(ConstantesSptar.PRC_LIST_SEM_BLK)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaBlankBean>() {
									@Override
									public SemillaBlankBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaBlankBean record = new SemillaBlankBean();
										record.setIdBlank(rs.getInt(1));
										record.setNumPromedio(rs.getDouble(2));
										record.setNumInicial(rs.getDouble(3));
										record.setNumFinal(rs.getDouble(4));
										record.setNumDepletion(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaBlankBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SBLANK, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_BLK+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SEM_BLK, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SBLANK, SemillaBlank.getIdBlank());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaBlank.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaBlank.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SBLANK, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROMEDIO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_BLK+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_SEM_BLK, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SBLANK, SemillaBlank.getIdBlank());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROMEDIO, SemillaBlank.getNumPromedio());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaBlank.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaBlank.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaBlank.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaBlank.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaBlank.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaBlank.getDeTermModi());					
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SemillaBlankBean registrarSemillaBlank(SemillaBlankBean SemillaBlank) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROMEDIO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SBLANK, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_BLK+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_SEM_BLK, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_NUM_PROMEDIO, SemillaBlank.getNumPromedio());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaBlank.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaBlank.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaBlank.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaBlank.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, SemillaBlank.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, SemillaBlank.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SBLANK));			
			SemillaBlank.setIdBlank(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return SemillaBlank;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaBlankBean> obtenerLtaSemillaBlankDBO(Integer idRegLab) throws GmdException {
		List<SemillaBlankBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_BLK).withProcedureName(ConstantesSptar.PRC_LIST_SEM_BLK_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaBlankBean>() {
									@Override
									public SemillaBlankBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaBlankBean record = new SemillaBlankBean();
										record.setIdBlank(rs.getInt(1));
										record.setNumPromedio(rs.getDouble(2));
										record.setNumInicial(rs.getDouble(3));
										record.setNumFinal(rs.getDouble(4));
										record.setNumDepletion(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										record.setDescripcionPtoMuestra(rs.getString(10));
										record.setDescripcionBotella(rs.getString(17));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaBlankBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
