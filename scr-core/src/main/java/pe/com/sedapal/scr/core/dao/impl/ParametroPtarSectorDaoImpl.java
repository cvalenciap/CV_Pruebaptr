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
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IParametroPtarSectorDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class ParametroPtarSectorDaoImpl implements IParametroPtarSectorDao {

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
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignados(int idPtarPorSector)
			throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PARAMETROSXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorNoAsignados(int idPtarPorSector)
			throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PARAMETRO_NO_PTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdParametro(rs.getInt(1));
										record.setDescripcionParametro(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAllParametroPtarSector(int idPtarPorSector) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PARAMETROXPTAR_T, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarParametroPtarSector(int idPtarPorSector,
			ParametroPtarSectorBean item, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer contadorEjecucion = 0;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_PARAMETROXPTAR, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
				inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, item.getIdParametro());
				inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
				inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
				inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());					
				this.execSp.executeSp(inputs);		
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
		return contadorEjecucion ;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PARAMETROXPTAR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());			
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer anularParametroPtarSectorTotal(ParametroPtarSectorBean punto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer valorRespuesta = null;
		String mensajeRespuesta = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.V_SALIDA, OracleTypes.DECIMAL));
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.VL_DES_ERROR, OracleTypes.VARCHAR));

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PARAMETROXPTAR_2, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());			
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
			Map<String, Object> results = this.execSp.executeSp(inputs);
			valorRespuesta = Integer.parseInt(results.get(ConstantesSptar.V_SALIDA).toString());
			mensajeRespuesta = results.get(ConstantesSptar.VL_DES_ERROR).toString();			
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return valorRespuesta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> validaParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR).withProcedureName(ConstantesSptar.PRC_VALIDA_PARAMXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdParametro(rs.getInt(1));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> buscarParametroPtarSector(ParametroPtarSectorBean punto) throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR).withProcedureName(ConstantesSptar.PRC_BUSCAR_PARAMETROXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> obtenerltaParametroPtarSectorAsignadosMenu(Integer idPtarPorSector,Integer idAnalista)
			throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PARAMETROSXPTAR_F)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
