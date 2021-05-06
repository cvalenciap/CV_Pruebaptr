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
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.TuboDilucionBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ITuboDilucionDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class TuboDilucionDaoImpl implements ITuboDilucionDao {

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
	public List<TuboDilucionBean> obtenerLtaTuboDilucion() throws GmdException {
		List<TuboDilucionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION).withProcedureName(ConstantesSptar.PRC_LIST_TUBODILUCION)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TuboDilucionBean>() {
									@Override
									public TuboDilucionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TuboDilucionBean record = new TuboDilucionBean();
										record.setIdTuboDilucion(rs.getInt(1));
										record.setCombinacion(rs.getString(2));
										record.setnMP100(rs.getDouble(3));
										record.setDebajo95(rs.getDouble(4));
										record.setSobre95(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										if(record.getnMP100() < ConstantesSptar.MINIMO_NMP100) {
											record.setDescripcionNMP100("Menor a "+ConstantesSptar.MINIMO_NMP100);
										}else {
											record.setDescripcionNMP100(""+record.getnMP100());
										}
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<TuboDilucionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_TUBODILUCION, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, tuboDilucionBean.getIdTuboDilucion());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, tuboDilucionBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, tuboDilucionBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarTuboDilucion(TuboDilucionBean tuboDilucionBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VAL_COMBINACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_NMP100, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_DEBAJO_95, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_SOBRE_95, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_TUBODILUCION, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, tuboDilucionBean.getIdTuboDilucion());
			inputs.put(ConstantesSptar.PAR_V_VAL_COMBINACION, tuboDilucionBean.getCombinacion());
			inputs.put(ConstantesSptar.PAR_N_VAL_NMP100, tuboDilucionBean.getnMP100());
			inputs.put(ConstantesSptar.PAR_N_VAL_DEBAJO_95, tuboDilucionBean.getDebajo95());
			inputs.put(ConstantesSptar.PAR_N_VAL_SOBRE_95, tuboDilucionBean.getSobre95());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, tuboDilucionBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, tuboDilucionBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, tuboDilucionBean.getDeTermModi());				
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public TuboDilucionBean registrarTuboDilucion(TuboDilucionBean TuboDilucionBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VAL_COMBINACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_NMP100, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_DEBAJO_95, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_SOBRE_95, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_TUBODILUCION, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_V_VAL_COMBINACION, TuboDilucionBean.getCombinacion());
			inputs.put(ConstantesSptar.PAR_N_VAL_NMP100, TuboDilucionBean.getnMP100());
			inputs.put(ConstantesSptar.PAR_N_VAL_DEBAJO_95, TuboDilucionBean.getDebajo95());
			inputs.put(ConstantesSptar.PAR_N_VAL_SOBRE_95, TuboDilucionBean.getSobre95());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, TuboDilucionBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, TuboDilucionBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, TuboDilucionBean.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_TUBO_DILUCION));			
			TuboDilucionBean.setIdTuboDilucion(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return TuboDilucionBean;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TuboDilucionBean> obtenerLtaTuboDilucionTodo() throws GmdException {
		List<TuboDilucionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION).withProcedureName(ConstantesSptar.PRC_LIST_TUBODILUCION_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TuboDilucionBean>() {
									@Override
									public TuboDilucionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TuboDilucionBean record = new TuboDilucionBean();
										record.setIdTuboDilucion(rs.getInt(1));
										record.setCombinacion(rs.getString(2));
										record.setnMP100(rs.getDouble(3));
										record.setDebajo95(rs.getDouble(4));
										record.setSobre95(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										if(record.getnMP100() < ConstantesSptar.MINIMO_NMP100) {
											record.setDescripcionNMP100("Menor a "+ConstantesSptar.MINIMO_NMP100);
										}else {
											record.setDescripcionNMP100(""+record.getnMP100());
										}
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<TuboDilucionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TuboDilucionBean> obtenerTuboDilucionByCadena(String valorCombinacion, Integer numExponente) throws GmdException {
		List<TuboDilucionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_TUBOS_DILUCION).withProcedureName(ConstantesSptar.PRC_BUSCAR_TUBODILUCION)
				.declareParameters(
						new  SqlParameter(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_V_VAL_COMBINACION, Types.VARCHAR),
						new  SqlParameter(ConstantesSptar.PAR_V_VAL_EXPONENTE, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TuboDilucionBean>() {
									@Override
									public TuboDilucionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TuboDilucionBean record = new TuboDilucionBean();
										record.setIdTuboDilucion(rs.getInt(1));
										record.setCombinacion(rs.getString(2));
										record.setnMP100(rs.getDouble(3));
										record.setDebajo95(rs.getDouble(4));
										record.setSobre95(rs.getDouble(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										record.setDescripcionExponencial(rs.getString(8));
										record.setValorDecimal(rs.getDouble(9));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_TUBO_DILUCION, null);
		params.addValue(ConstantesSptar.PAR_V_VAL_COMBINACION, valorCombinacion);
		params.addValue(ConstantesSptar.PAR_V_VAL_EXPONENTE, numExponente);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<TuboDilucionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
