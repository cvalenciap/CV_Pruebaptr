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
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IFormulaDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class FormulaDaoImpl implements IFormulaDao{

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
	public List<FormulaBean> obtenerLtaFormula(Integer idParametro) throws GmdException{
		List<FormulaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FORMULAS).withProcedureName(ConstantesSptar.PRC_LIST_FORMULA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormulaBean>() {
									@Override
									public FormulaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FormulaBean record = new FormulaBean();
										record.setIdFormulaxParametro(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										record.setTipoOrden(rs.getInt(4));
										record.setDescripcionOrden(rs.getString(5));
										record.setDescripcionFormula(rs.getString(6));
										record.setCombinacionFormula(rs.getString(7));;
										record.setStRegi(rs.getString(8));
										record.setEstado(rs.getString(9));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FormulaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularFormula(FormulaBean formula) throws GmdException{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FORMULAS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_FORMULA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, formula.getIdFormulaxParametro());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, formula.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, formula.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
    }
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFormula(FormulaBean formula) throws GmdException{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_ORDEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_FORMULA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_COMBINACION_FORMULA, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FORMULAS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_FORMULA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, formula.getIdFormulaxParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, formula.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_TIPO_ORDEN, formula.getTipoOrden());
			inputs.put(ConstantesSptar.PAR_V_DES_FORMULA, formula.getDescripcionFormula());
			inputs.put(ConstantesSptar.PAR_V_COMBINACION_FORMULA, formula.getCombinacionFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, formula.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, formula.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, formula.getDeTermModi());				
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public FormulaBean registrarFormula(FormulaBean formula) throws GmdException{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_ORDEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_FORMULA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_COMBINACION_FORMULA, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FORMULAS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_FORMULA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, formula.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_TIPO_ORDEN, formula.getTipoOrden());
			inputs.put(ConstantesSptar.PAR_V_DES_FORMULA, formula.getDescripcionFormula());
			inputs.put(ConstantesSptar.PAR_V_COMBINACION_FORMULA, formula.getCombinacionFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, formula.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, formula.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, formula.getDeTermCrea());	
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO));			
			formula.setIdFormulaxParametro(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return formula;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormulaBean> buscarFormulas(Integer idParametro, Integer idFormulaxPara, String combinacionFormula)
			throws GmdException {
		List<FormulaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FORMULAS).withProcedureName(ConstantesSptar.PRC_BUSCAR_FORMULA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_V_COMBINACION_FORMULA, Types.VARCHAR),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormulaBean>() {
									@Override
									public FormulaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FormulaBean record = new FormulaBean();
										record.setIdFormulaxParametro(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										record.setTipoOrden(rs.getInt(4));
										record.setDescripcionOrden(rs.getString(5));
										record.setDescripcionFormula(rs.getString(6));
										record.setCombinacionFormula(rs.getString(7));;
										record.setStRegi(rs.getString(8));
										record.setEstado(rs.getString(9));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		params.addValue(ConstantesSptar.PAR_N_ID_FORMULAXPARAMETRO, idFormulaxPara);
		params.addValue(ConstantesSptar.PAR_V_COMBINACION_FORMULA, combinacionFormula);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FormulaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormulaBean> buscarFormulasPrincipales(Integer idParametro) throws GmdException {
		List<FormulaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FORMULAS).withProcedureName(ConstantesSptar.PRC_LIST_PRI_FRMXPARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormulaBean>() {
									@Override
									public FormulaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FormulaBean record = new FormulaBean();
										record.setIdFormulaxParametro(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										record.setTipoOrden(rs.getInt(4));
										record.setDescripcionOrden(rs.getString(5));
										record.setDescripcionFormula(rs.getString(6));
										record.setCombinacionFormula(rs.getString(7));;
										record.setStRegi(rs.getString(8));
										record.setEstado(rs.getString(9));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FormulaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
