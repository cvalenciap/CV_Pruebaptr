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
import pe.com.sedapal.scr.core.beans.SemillaStandardBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISemillaStandardDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class SemillaStandardDaoImpl implements ISemillaStandardDao {

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
	public List<SemillaStandardBean> obtenerLtaSemillaStandardTodo() throws GmdException {
		List<SemillaStandardBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_GGA).withProcedureName(ConstantesSptar.PRC_LIST_SEM_GGA_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaStandardBean>() {
									@Override
									public SemillaStandardBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaStandardBean record = new SemillaStandardBean();
										record.setIdStandard(rs.getInt(1));
										record.setNumVolGGa(rs.getDouble(2));
										record.setNumVolPoly(rs.getDouble(3));
										record.setNumInicial(rs.getDouble(4));
										record.setNumFinal(rs.getDouble(5));
										record.setNumDepletion(rs.getDouble(6));
										record.setNumPorcentajeDeplec(rs.getDouble(7));
										record.setNumValorBOD(rs.getDouble(8));
										record.setNumNetDep(rs.getDouble(9));
										record.setNumPromedioGGA(rs.getDouble(10));
										record.setStRegi(rs.getString(11));
										record.setEstado(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaStandardBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaStandardBean> obtenerLtaSemillaStandard() throws GmdException {
		List<SemillaStandardBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_GGA).withProcedureName(ConstantesSptar.PRC_LIST_SEM_GGA)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaStandardBean>() {
									@Override
									public SemillaStandardBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaStandardBean record = new SemillaStandardBean();
										record.setIdStandard(rs.getInt(1));
										record.setNumVolGGa(rs.getDouble(2));
										record.setNumVolPoly(rs.getDouble(3));
										record.setNumInicial(rs.getDouble(4));
										record.setNumFinal(rs.getDouble(5));
										record.setNumDepletion(rs.getDouble(6));
										record.setNumPorcentajeDeplec(rs.getDouble(7));
										record.setNumValorBOD(rs.getDouble(8));
										record.setNumNetDep(rs.getDouble(9));
										record.setNumPromedioGGA(rs.getDouble(10));
										record.setStRegi(rs.getString(11));
										record.setEstado(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SemillaStandardBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SGGA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_GGA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SEM_GGA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SGGA, SemillaStandard.getIdStandard());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaStandard.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaStandard.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SGGA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_GGA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_POLY, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_BOD, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_SCF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROM_SCF, OracleTypes.NUMBER));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_GGA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_SEM_GGA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SGGA, SemillaStandard.getIdStandard());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_GGA, SemillaStandard.getNumVolGGa());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_POLY, SemillaStandard.getNumVolPoly());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaStandard.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaStandard.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaStandard.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, SemillaStandard.getNumPorcentajeDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_BOD, SemillaStandard.getNumValorBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_SCF, SemillaStandard.getNumNetDep());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROM_SCF, SemillaStandard.getNumPromedioGGA());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaStandard.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, SemillaStandard.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, SemillaStandard.getDeTermModi());					
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
	public SemillaStandardBean registrarSemillaStandard(SemillaStandardBean SemillaStandard) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_GGA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOL_POLY, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_INICIAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FINAL_DO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLETION, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_BOD, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VAL_SCF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROM_SCF, OracleTypes.NUMBER));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SGGA, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SEM_GGA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_SEM_GGA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_GGA, SemillaStandard.getNumVolGGa());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOL_POLY, SemillaStandard.getNumVolPoly());
			inputs.put(ConstantesSptar.PAR_N_NUM_INICIAL_DO, SemillaStandard.getNumInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_FINAL_DO, SemillaStandard.getNumFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLETION, SemillaStandard.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_NUM_PORCEN_DEPLEC, SemillaStandard.getNumPorcentajeDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_BOD, SemillaStandard.getNumValorBOD());
			inputs.put(ConstantesSptar.PAR_N_NUM_VAL_SCF, SemillaStandard.getNumNetDep());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROM_SCF, SemillaStandard.getNumPromedioGGA());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, SemillaStandard.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, SemillaStandard.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, SemillaStandard.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SGGA));			
			SemillaStandard.setIdStandard(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return SemillaStandard;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemillaStandardBean> obtenerLtaSemillaStandardTodoDBO(Integer idRegLab) throws GmdException {
		List<SemillaStandardBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SEM_GGA).withProcedureName(ConstantesSptar.PRC_LIST_SEM_GGA_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SemillaStandardBean>() {
									@Override
									public SemillaStandardBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SemillaStandardBean record = new SemillaStandardBean();
										record.setIdStandard(rs.getInt(1));
										record.setNumVolGGa(rs.getDouble(2));
										record.setNumVolPoly(rs.getDouble(3));
										record.setNumInicial(rs.getDouble(4));
										record.setNumFinal(rs.getDouble(5));
										record.setNumDepletion(rs.getDouble(6));
										record.setNumNetDep(rs.getDouble(7));
										record.setNumPorcentajeDeplec(rs.getDouble(8));
										record.setNumValorBOD(rs.getDouble(9));
										record.setNumPromedioGGA(rs.getDouble(10));
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
		lstRetorno = (List<SemillaStandardBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}