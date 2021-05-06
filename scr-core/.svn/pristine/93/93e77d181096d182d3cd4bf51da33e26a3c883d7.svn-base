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
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.VariableBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IVariableDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class VariableDaoImpl implements IVariableDao {

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
	public List<VariableBean> obtenerVariable() throws GmdException {
		List<VariableBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_VARIABLES).withProcedureName(ConstantesSptar.PRC_LIST_VARIABLE)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<VariableBean>() {
									@Override
									public VariableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										VariableBean record = new VariableBean();
										record.setIdVariable(rs.getInt(1));
										record.setDescripcionLetra(rs.getString(2));
										record.setDescripcionVariable(rs.getString(3));
										record.setStRegi(rs.getString(4));
										record.setEstado(rs.getString(5));
										record.setDescripcionConcat(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<VariableBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularVariable(VariableBean variableBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_VARIABLE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_VARIABLES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_VARIABLE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_VARIABLE, variableBean.getIdVariable());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, variableBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, variableBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@Override
	public void actualizarVariable(VariableBean variableBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_VARIABLE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_LETRA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_VARIABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_VARIABLES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_VARIABLE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_VARIABLE, variableBean.getIdVariable());
			inputs.put(ConstantesSptar.PAR_V_DES_LETRA, variableBean.getDescripcionLetra());
			inputs.put(ConstantesSptar.PAR_V_DES_VARIABLE, variableBean.getDescripcionVariable());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, variableBean.getIdParametro());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, variableBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, variableBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, variableBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public VariableBean registrarVariable(VariableBean variableBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_LETRA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_VARIABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_VARIABLE, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_VARIABLES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_VARIABLE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_V_DES_LETRA, variableBean.getDescripcionLetra());
			inputs.put(ConstantesSptar.PAR_V_DES_VARIABLE, variableBean.getDescripcionVariable());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, variableBean.getIdParametro());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI,  variableBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE,  variableBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA,  variableBean.getDeTermCrea());
			
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRetorno = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_VARIABLE));			
			variableBean.setIdVariable(idRetorno);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return variableBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VariableBean> obtenerLtaVariable(Integer idParametro) throws GmdException {
		List<VariableBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_VARIABLES).withProcedureName(ConstantesSptar.PRC_LIST_VARIABLE)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<VariableBean>() {
									@Override
									public VariableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										VariableBean record = new VariableBean();
										record.setIdVariable(rs.getInt(1));
										record.setDescripcionLetra(rs.getString(2));
										record.setDescripcionVariable(rs.getString(3));
										record.setStRegi(rs.getString(4));
										record.setEstado(rs.getString(5));
										record.setDescripcionConcat(rs.getString(6));
										record.setIdParametro(rs.getInt(7));
										record.setDescripcionParametro(rs.getString(8));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<VariableBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VariableBean> obtenerVariableTodo(Integer idParametro) throws GmdException{
		List<VariableBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_VARIABLES).withProcedureName(ConstantesSptar.PRC_LIST_VARIABLE_M)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<VariableBean>() {
									@Override
									public VariableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										VariableBean record = new VariableBean();
										record.setIdVariable(rs.getInt(1));
										record.setDescripcionLetra(rs.getString(2));
										record.setDescripcionVariable(rs.getString(3));
										record.setStRegi(rs.getString(4));
										record.setEstado(rs.getString(5));
										record.setDescripcionConcat(rs.getString(6));
										record.setIdParametro(rs.getInt(7));
										record.setDescripcionParametro(rs.getString(8));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<VariableBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
