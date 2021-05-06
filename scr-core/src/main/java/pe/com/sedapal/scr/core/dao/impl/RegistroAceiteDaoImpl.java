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
import org.springframework.util.StringUtils;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.beans.RegistroAceiteBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroAceiteDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroAceiteDaoImpl implements IRegistroAceiteDao {

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
	public List<RegistroAceiteBean> obtenerRegistroAceiteByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroAceiteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_ACEITES).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_ACEITE)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAceiteBean>() {
									@Override
									public RegistroAceiteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAceiteBean record = new RegistroAceiteBean();
										record.setIndice(rowNum);
										record.setIdRegAceite(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));
										record.setNumFrasco(rs.getDouble(9));
										record.setNumVolumen(rs.getDouble(10));
										record.setNumPesoInicial(rs.getDouble(11));
										record.setNumPesoFinal(rs.getDouble(12));
										record.setIdFormulaResultado(rs.getInt(13));
										record.setNumResultado(rs.getDouble(14));
										record.setDescripcionObservacion(rs.getString(15));
										record.setIdAnalista(rs.getInt(16));
										record.setFechaMonitoreo(rs.getDate(17));
										record.setFechaRegDato(rs.getDate(18));
										record.setNombreAnalista(rs.getString(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAceiteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroAceiteBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAceiteBean>() {
									@Override
									public RegistroAceiteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAceiteBean record = new RegistroAceiteBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6)) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7)) : null );
										record.setIndicadorFactor(rs.getString(8) != null  && !Strings.isEmpty(rs.getString(8)) && !rs.getString(8).trim().equals("") && !rs.getString(8).equals("") && !Strings.isEmpty(rs.getString(8))? Integer.parseInt(rs.getString(8)) : null );
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setIdFormulaResultado(0);
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAceiteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroAceiteBean registrarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
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
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FRASCO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_ACEITES, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_ACEITES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_ACEITE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroAceite.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroAceite.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroAceite.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroAceite.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroAceite.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroAceite.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroAceite.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroAceite.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_FRASCO, registroAceite.getNumFrasco());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registroAceite.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, registroAceite.getNumPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, registroAceite.getNumPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroAceite.getIdFormulaResultado());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroAceite.getNumResultado());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroAceite.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroAceite.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroAceite.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroAceite.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroAceite.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroAceite.getDeTermCrea());	
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegAceite = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_ACEITES));			
			registroAceite.setIdRegAceite(idRegAceite);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registroAceite;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ACEITES, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FRASCO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_ACEITES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_ACEITE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_ACEITES, registroAceite.getIdRegAceite());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroAceite.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroAceite.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroAceite.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroAceite.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroAceite.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroAceite.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroAceite.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroAceite.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_FRASCO, registroAceite.getNumFrasco());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registroAceite.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, registroAceite.getNumPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, registroAceite.getNumPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroAceite.getIdFormulaResultado());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroAceite.getNumResultado());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroAceite.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroAceite.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroAceite.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroAceite.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, registroAceite.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroAceite.getDeTermCrea());	
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroAceiteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_ACEITES).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_ACEITES_XSUBP)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAceiteBean>() {
									@Override
									public RegistroAceiteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAceiteBean record = new RegistroAceiteBean();
										record.setIdRegAceite(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));
										record.setNumFrasco(rs.getDouble(9));
										record.setNumVolumen(rs.getDouble(10));
										record.setNumPesoInicial(rs.getDouble(11));
										record.setNumPesoFinal(rs.getDouble(12));
										record.setIdFormulaResultado(rs.getInt(13));
										record.setNumResultado(rs.getDouble(14));
										record.setIdAnalista(rs.getInt(15));
										record.setFechaMonitoreo(rs.getDate(16));
										record.setFechaRegDato(rs.getDate(17));
										record.setNombreAnalista(rs.getString(18));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, subParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAceiteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ACEITES, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_ACEITES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_ACEITE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_ACEITES, registroAceite.getIdRegAceite());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroAceite.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroAceite.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteByidAceite(Integer idAceite,Date fechaDesde,Date FechaHasta) throws GmdException {
		List<RegistroAceiteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_ACEITES).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_ACEITE)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_ACEITES, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAceiteBean>() {
									@Override
									public RegistroAceiteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAceiteBean record = new RegistroAceiteBean();
										record.setIndice(rowNum);
										record.setIdRegAceite(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));
										record.setNumFrasco(rs.getDouble(9));
										record.setNumVolumen(rs.getDouble(10));
										record.setNumPesoInicial(rs.getDouble(11));
										record.setNumPesoFinal(rs.getDouble(12));
										record.setIdFormulaResultado(rs.getInt(13));
										record.setNumResultado(rs.getDouble(14));
										record.setIdAnalista(rs.getInt(15));
										record.setFechaMonitoreo(rs.getDate(16));
										record.setFechaRegDato(rs.getDate(17));
										record.setNombreAnalista(rs.getString(18));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_ACEITES, idAceite);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAceiteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroAceiteBean> obtenerRegistroAceiteSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroAceiteBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAceiteBean>() {
									@Override
									public RegistroAceiteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAceiteBean record = new RegistroAceiteBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setIdFormulaResultado(0);										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAceiteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroAceite(RegistroAceiteBean registroAceite) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_ACEITES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_ACEITE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroAceite.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroAceite.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroAceite.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroAceite.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}

