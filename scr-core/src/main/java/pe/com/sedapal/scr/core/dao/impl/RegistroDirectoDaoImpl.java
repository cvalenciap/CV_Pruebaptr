package pe.com.sedapal.scr.core.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
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
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroDirectoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroDirectoDaoImpl implements IRegistroDirectoDao {

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
	public List<RegistroDirectoBean> obtenerRegistroDirectoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroDirectoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_DIRECTO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDirectoBean>() {
									@Override
									public RegistroDirectoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDirectoBean record = new RegistroDirectoBean();
										record.setIndice(rowNum);
										record.setIdRegDirecto(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setNumValor(rs.getDouble(9));
										record.setNumProfundida(rs.getDouble(10));
										record.setNumValorDQO(rs.getDouble(11));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));				
										record.setNumFactor(rs.getDouble(15));
										record.setIntervaloMinimo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIntervaloMaximo(rs.getString(17) != null && !Strings.isBlank(rs.getString(17)) && !rs.getString(17).equals(" ") ? Double.parseDouble(rs.getString(17)) : null );
										record.setIndicadorFactor(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Integer.parseInt(rs.getString(18)) : 0 );
										record.setNombreAnalista(rs.getString(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDirectoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDirectoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDirectoBean>() {
									@Override
									public RegistroDirectoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDirectoBean record = new RegistroDirectoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setIndicadorFactor(rs.getString(8) != null  && !Strings.isEmpty(rs.getString(8)) && !rs.getString(8).trim().equals("") && !rs.getString(8).equals("") && !Strings.isEmpty(rs.getString(8))? Integer.parseInt(rs.getString(8).trim()) : null );
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDirectoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroDirectoBean registrarRegistroDirecto(RegistroDirectoBean registro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROFUNDIDAD, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_DQO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FACTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_DIRECTO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_DIRECTO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registro.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registro.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registro.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registro.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registro.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registro.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registro.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registro.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR, registro.getNumValor());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROFUNDIDAD, registro.getNumProfundida());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_DQO, registro.getNumValorDQO());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registro.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registro.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registro.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_FACTOR, registro.getNumFactor());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registro.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registro.getDeTermCrea());						
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegDirecto = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_DIRECTO));			
			registro.setIdRegDirecto(idRegDirecto);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registro;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DIRECTO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PROFUNDIDAD, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VALOR_DQO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FACTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_DIRECTO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_DIRECTO, registroDirecto.getIdRegDirecto());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroDirecto.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroDirecto.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroDirecto.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroDirecto.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroDirecto.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroDirecto.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroDirecto.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroDirecto.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR, registroDirecto.getNumValor());
			inputs.put(ConstantesSptar.PAR_N_NUM_PROFUNDIDAD, registroDirecto.getNumProfundida());
			inputs.put(ConstantesSptar.PAR_N_NUM_VALOR_DQO, registroDirecto.getNumValorDQO());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroDirecto.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroDirecto.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroDirecto.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_FACTOR, registroDirecto.getNumFactor());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroDirecto.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroDirecto.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroDirecto.getDeTermModi());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoBySubParametro(Integer idRegLab, Integer subParametro)
			throws GmdException {
		List<RegistroDirectoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_DIRECTO_XSUBP)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDirectoBean>() {
									@Override
									public RegistroDirectoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDirectoBean record = new RegistroDirectoBean();
										record.setIndice(rowNum);
										record.setIdRegDirecto(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setNumValor(rs.getDouble(9));
										record.setNumProfundida(rs.getDouble(10));
										record.setNumValorDQO(rs.getDouble(11));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));				
										record.setNumFactor(rs.getDouble(15));
										record.setIntervaloMinimo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIntervaloMaximo(rs.getString(17) != null && !Strings.isBlank(rs.getString(17)) && !rs.getString(17).equals(" ") ? Double.parseDouble(rs.getString(17)) : null );
										record.setIndicadorFactor(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Integer.parseInt(rs.getString(18)) : 0 );
										record.setNombreAnalista(rs.getString(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, subParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDirectoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DIRECTO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_DIRECTO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_DIRECTO, registroDirecto.getIdRegDirecto());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroDirecto.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroDirecto.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoByidDirecto(Integer idDirecto,Date fechaDesde,Date FechaHasta) throws GmdException {
		List<RegistroDirectoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_DIRECTO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_DIRECTO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDirectoBean>() {
									@Override
									public RegistroDirectoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDirectoBean record = new RegistroDirectoBean();
										record.setIndice(rowNum);
										record.setIdRegDirecto(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setNumValor(rs.getDouble(9));
										record.setNumProfundida(rs.getDouble(10));
										record.setNumValorDQO(rs.getDouble(11));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));				
										record.setNumFactor(rs.getDouble(15));
										record.setIntervaloMinimo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIntervaloMaximo(rs.getString(17) != null && !Strings.isBlank(rs.getString(17)) && !rs.getString(17).equals(" ") ? Double.parseDouble(rs.getString(17)) : null );
										record.setIndicadorFactor(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Integer.parseInt(rs.getString(18)) : 0 );
										record.setNombreAnalista(rs.getString(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_DIRECTO, idDirecto);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDirectoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDirectoBean> obtenerRegistroDirectoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDirectoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDirectoBean>() {
									@Override
									public RegistroDirectoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDirectoBean record = new RegistroDirectoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setIndicadorFactor(rs.getString(8) != null  && !Strings.isEmpty(rs.getString(8)) && !rs.getString(8).trim().equals("") && !rs.getString(8).equals("") && !Strings.isEmpty(rs.getString(8))? Integer.parseInt(rs.getString(8).trim()) : null );
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDirectoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroDirecto(RegistroDirectoBean registroDirecto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_DIRECTO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroDirecto.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroDirecto.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroDirecto.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroDirecto.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerMejorRegistroDirectoES(Integer idRegistro) throws GmdException{
		List<RegistroLabReporteBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DIRECTO).withProcedureName(ConstantesSptar.PRC_MEJOR_REGISTRO_ES)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionPtoMuestra(rs.getString(2));
										record.setIdPtoMuestra(rs.getInt(3));
										record.setDescripcionPtoMuestra(rs.getString(4));
										record.setNumValor(rs.getDouble(5));
										record.setDescripcionTipoRegistro(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
