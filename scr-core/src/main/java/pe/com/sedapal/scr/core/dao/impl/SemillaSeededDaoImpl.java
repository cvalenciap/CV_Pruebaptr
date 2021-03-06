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
import pe.com.sedapal.scr.core.beans.SemillaSeededBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISemillaSeededDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class SemillaSeededDaoImpl implements ISemillaSeededDao {

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
	public List<SemillaSeededBean> obtenerLtaSemillaSeededTodo() throws GmdException {
		List<SemillaSeededBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SEE).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SEE_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSeededBean>() {
									@Override
									public SemillaSeededBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSeededBean record = new SemillaSeededBean();
										record.setIdSeeded(rs.getInt(1));
										record.setNumValGGa(rs.getDouble(2));
										record.setNumPromedio(rs.getDouble(3));
										record.setNumVolumen(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumPorcentajeDeplec(rs.getDouble(8));
										record.setNumValorBOD(rs.getDouble(9));
										record.setNumValorSCF(rs.getDouble(10));
										record.setStRegi(rs.getString(11));
										record.setEstado(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSeededBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaSeededBean> obtenerLtaSemillaSeeded() throws GmdException {
		List<SemillaSeededBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SEE).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SEE)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSeededBean>() {
									@Override
									public SemillaSeededBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSeededBean record = new SemillaSeededBean();
										record.setIdSeeded(rs.getInt(1));
										record.setNumValGGa(rs.getDouble(2));
										record.setNumPromedio(rs.getDouble(3));
										record.setNumVolumen(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumPorcentajeDeplec(rs.getDouble(8));
										record.setNumValorBOD(rs.getDouble(9));
										record.setNumValorSCF(rs.getDouble(10));
										record.setStRegi(rs.getString(11));
										record.setEstado(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSeededBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SSEEDED, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SEE+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SEM_SEE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SSEEDED, SemillaSeeded.getIdSeeded());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaSeeded.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaSeeded.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SSEEDED, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_GGA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROMEDIO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_BOD, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_SCF, OracleTypes.NUMBER));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SEE+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_SEM_SEE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SSEEDED, SemillaSeeded.getIdSeeded());
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_GGA, SemillaSeeded.getNumValGGa());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROMEDIO, SemillaSeeded.getNumPromedio());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, SemillaSeeded.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaSeeded.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaSeeded.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaSeeded.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, SemillaSeeded.getNumPorcentajeDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_BOD, SemillaSeeded.getNumValorBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_SCF, SemillaSeeded.getNumValorSCF());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaSeeded.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaSeeded.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaSeeded.getDeTermModi());					
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
	public SemillaSeededBean registrarSemillaSeeded(SemillaSeededBean SemillaSeeded) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_GGA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROMEDIO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_BOD, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_SCF, OracleTypes.NUMBER));	;			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SSEEDED, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_SEE+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_SEM_SEE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_GGA, SemillaSeeded.getNumValGGa());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROMEDIO, SemillaSeeded.getNumPromedio());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, SemillaSeeded.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaSeeded.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaSeeded.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaSeeded.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, SemillaSeeded.getNumPorcentajeDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_BOD, SemillaSeeded.getNumValorBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_SCF, SemillaSeeded.getNumValorSCF());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaSeeded.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, SemillaSeeded.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, SemillaSeeded.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SSEEDED));			
			SemillaSeeded.setIdSeeded(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return SemillaSeeded;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaSeededBean> obtenerLtaSemillaSeededTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaSeededBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_SEE).withProcedureName(ConstantesSptar.PRC_LIST_SEM_SEE_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaSeededBean>() {
									@Override
									public SemillaSeededBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaSeededBean record = new SemillaSeededBean();
										record.setIdSeeded(rs.getInt(1));
										record.setNumValGGa(rs.getDouble(2));
										record.setNumPromedio(rs.getDouble(3));
										record.setNumVolumen(rs.getDouble(4));
										record.setNumInicial(rs.getDouble(5));
										record.setNumFinal(rs.getDouble(6));
										record.setNumDepletion(rs.getDouble(7));
										record.setNumPorcentajeDeplec(rs.getDouble(8));
										record.setNumValorBOD(rs.getDouble(9));
										record.setNumValorSCF(rs.getDouble(10));
										record.setStRegi(rs.getString(11));
										record.setEstado(rs.getString(12));
										record.setDescripcionPtoMuestra(rs.getString(15));
										record.setDescripcionBotella(rs.getString(22));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaSeededBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
