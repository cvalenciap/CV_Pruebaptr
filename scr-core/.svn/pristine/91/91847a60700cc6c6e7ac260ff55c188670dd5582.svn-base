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
import pe.com.sedapal.scr.core.beans.SemillaSamplesBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISemillaSamplesDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;


@Repository
public class SemillaSamplesDaoImpl implements ISemillaSamplesDao {

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
	public List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodo() throws GmdException {
		List<SemillaSamplesBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SAM).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SAM_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSamplesBean>() {
									@Override
									public SemillaSamplesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSamplesBean record = new SemillaSamplesBean();
										record.setIdSamples(rs.getInt(1));
										record.setDescripcionBotella(rs.getString(2));
										record.setNumVolSample(rs.getDouble(3));
										record.setNumVolPoly(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumValorSCF(rs.getDouble(8));
										record.setNumValorNetDep(rs.getDouble(9));
										record.setNumDilcFactor(rs.getDouble(10));
										record.setNumSampleBOD(rs.getDouble(11));
										record.setNumPromedioDBO(rs.getDouble(12));
										record.setStRegi(rs.getString(13));
										record.setEstado(rs.getString(14));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSamplesBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaSamplesBean> obtenerLtaSemillaSamples() throws GmdException {
		List<SemillaSamplesBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SAM).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SAM)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSamplesBean>() {
									@Override
									public SemillaSamplesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSamplesBean record = new SemillaSamplesBean();
										record.setIdSamples(rs.getInt(1));
										record.setDescripcionBotella(rs.getString(2));
										record.setNumVolSample(rs.getDouble(3));
										record.setNumVolPoly(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumValorSCF(rs.getDouble(8));
										record.setNumValorNetDep(rs.getDouble(9));
										record.setNumDilcFactor(rs.getDouble(10));
										record.setNumSampleBOD(rs.getDouble(11));
										record.setNumPromedioDBO(rs.getDouble(12));
										record.setStRegi(rs.getString(13));
										record.setEstado(rs.getString(14));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSamplesBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SSAMPLE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SAM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SEM_SAM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SSAMPLE, SemillaSamples.getIdSamples());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaSamples.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaSamples.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SSAMPLE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_BOTELLA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_SAMPLE, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_POLY, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_SCF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_NETDEP, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DIL_FACTOR, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SAMPLE_BOD, OracleTypes.NUMBER));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROM_DBO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SAM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_SEM_SAM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SSAMPLE, SemillaSamples.getIdSamples());
			inputs.put(ConstantesSptar.PAR_V_DES_BOTELLA, SemillaSamples.getDescripcionBotella());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_SAMPLE, SemillaSamples.getNumVolSample());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_POLY, SemillaSamples.getNumVolPoly());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaSamples.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaSamples.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaSamples.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_SCF, SemillaSamples.getNumValorSCF());
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_NETDEP, SemillaSamples.getNumValorNetDep());
			inputs.put(ConstantesSptar.PAR_N_NUM_DIL_FACTOR, SemillaSamples.getNumDilcFactor());
			inputs.put(ConstantesSptar.PAR_N_NUM_SAMPLE_BOD, SemillaSamples.getNumSampleBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROM_DBO, SemillaSamples.getNumPromedioDBO());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaSamples.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaSamples.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaSamples.getDeTermModi());					
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
	public SemillaSamplesBean registrarSemillaSamples(SemillaSamplesBean SemillaSamples) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_BOTELLA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_SAMPLE, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_POLY, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_SCF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_NETDEP, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DIL_FACTOR, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SAMPLE_BOD, OracleTypes.NUMBER));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROM_DBO, OracleTypes.NUMBER));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SSAMPLE, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SAM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_SEM_SAM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_V_DES_BOTELLA, SemillaSamples.getDescripcionBotella());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_SAMPLE, SemillaSamples.getNumVolSample());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_POLY, SemillaSamples.getNumVolPoly());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaSamples.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaSamples.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaSamples.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_SCF, SemillaSamples.getNumValorSCF());
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_NETDEP, SemillaSamples.getNumValorNetDep());
			inputs.put(ConstantesSptar.PAR_N_NUM_DIL_FACTOR, SemillaSamples.getNumDilcFactor());
			inputs.put(ConstantesSptar.PAR_N_NUM_SAMPLE_BOD, SemillaSamples.getNumSampleBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROM_DBO, SemillaSamples.getNumPromedioDBO());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaSamples.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, SemillaSamples.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, SemillaSamples.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SSAMPLE));			
			SemillaSamples.setIdSamples(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return SemillaSamples;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaSamplesBean> obtenerLtaSemillaSamplesTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaSamplesBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SAM).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SAM_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSamplesBean>() {
									@Override
									public SemillaSamplesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSamplesBean record = new SemillaSamplesBean();
										record.setIdSamples(rs.getInt(1));
										record.setDescripcionBotella(rs.getString(2));
										record.setNumVolSample(rs.getDouble(3));
										record.setNumVolPoly(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumValorSCF(rs.getDouble(8));
										record.setNumValorNetDep(rs.getDouble(9));
										record.setNumDilcFactor(rs.getDouble(10));
										record.setNumSampleBOD(rs.getDouble(11));
										record.setNumPromedioDBO(rs.getDouble(12));
										record.setStRegi(rs.getString(13));
										record.setEstado(rs.getString(14));
										record.setDescripcionPtoMuestra(rs.getString(17));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSamplesBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
