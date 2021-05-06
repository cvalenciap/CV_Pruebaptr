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
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISubParametroPtarSectorDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class SubParametroPtarSectorDaoImpl implements ISubParametroPtarSectorDao {

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
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorAsignados(Integer idPtarPorSector,
			Integer idParametro) throws GmdException {
		List<SubParametroPtarSectorBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubParametro(rs.getString(4));
										record.setDescripcionLarga(rs.getString(5));
										record.setDescripcionCorta(rs.getString(9));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroPtarSectorNoAsignados(Integer idPtarPorSector,
			Integer idParametro) throws GmdException {
		List<SubParametroPtarSectorBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARA_NO_PTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionSubParametro(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAllSubParametroPtarSector(Integer idPtarPorSector, Integer idParametro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));	
			paramsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_SUBPARAXPTAR_T, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarSubParametroPtarSector(Integer idPtarPorSector, Integer idParametro,
			SubParametroPtarSectorBean item, BaseSptarBean auditoria)
			throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer contadorEjecucion = 0;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_SUBPARAXPTAR, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
				inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
				inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, item.getIdSubParametro());
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
	public void anularSubParametroPtarSector(SubParametroPtarSectorBean punto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));	
			paramsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_SUBPARAXPTAR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, punto.getIdSubParametro());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<SubParametroPtarSectorBean> validaSubParametroPtarSector(SubParametroPtarSectorBean punto)
			throws GmdException {
		List<SubParametroPtarSectorBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_VALIDA_SUBXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdSubParametro(rs.getInt(1));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, punto.getIdSubParametro());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public List<SubParametroPtarSectorBean> buscarSubParametroPtarSector(SubParametroPtarSectorBean punto)
			throws GmdException {
		List<SubParametroPtarSectorBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_BUSCAR_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, punto.getIdSubParametro());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<SubParametroPtarSectorBean> obtenerListaSubparametroMicro(Integer idPtarParametroHidrobiologico) throws GmdException {
		List<SubParametroPtarSectorBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_BUSCAR_SUBPARAXHIDRO)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionSubParametro(rs.getString(2));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idPtarParametroHidrobiologico);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroSecuencial(Integer idPtarPorSector) throws GmdException{
		List<SubParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		try {
			caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAM_PTARXRPT)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rownum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionSubParametro(rs.getString(2));
										record.setSecuencia(rs.getInt(3));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
			Map<String, Object> results = caller.execute(params);
			lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		}catch(Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return lstRetorno;
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSubParametroSecuencial(Integer idPtarPorSector) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SUBPARAM_PTARXRPT_T, 
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
	public void registrarSubParametroSecuencial(SubParametroPtarSectorBean item, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_SUBPARAM_RPT, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, item.getIdptarPorSector());
				inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, item.getIdSubParametro());
				inputs.put(ConstantesSptar.PAR_N_SECUENCIA, item.getSecuencia());
				inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
				inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
				inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());					
				this.execSp.executeSp(inputs);		
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SubParametroPtarSectorBean> obtenerltaSubParametroSecHistAc() throws GmdException{
		List<SubParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		try {
			caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT).withProcedureName(ConstantesSptar.PRC_LIST_SUBPMT_HIST_AC_XRPT)
				.declareParameters(
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SubParametroPtarSectorBean>() {
									@Override
									public SubParametroPtarSectorBean mapRow(ResultSet rs, int rownum) throws SQLException {
										SubParametroPtarSectorBean record = new SubParametroPtarSectorBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionSubParametro(rs.getString(2));
										record.setSecuencia(rs.getInt(3));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
			MapSqlParameterSource params = new MapSqlParameterSource();
			Map<String, Object> results = caller.execute(params);
			lstRetorno = (List<SubParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		}catch(Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return lstRetorno;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void registrarSubParametroSecHistAc(SubParametroPtarSectorBean item, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
				paramsInput = new ArrayList<SqlParameter>();
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA_HIST_AC, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGIST_SUBPTM_HIST_AC_RPT, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, item.getIdSubParametro());
				inputs.put(ConstantesSptar.PAR_N_SECUENCIA_HIST_AC, item.getSecuencia());
				inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
				inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
				inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());					
				this.execSp.executeSp(inputs);		
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSubParametroSecHistAc() throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SUBPARAM_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_SUBPMT_HIST_AC_XRPT, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}
