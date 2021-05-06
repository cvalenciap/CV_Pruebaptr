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
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroHidrobiologicoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroHidrobiologicoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroHidrobiologicoDaoImpl implements IRegistroHidrobiologicoDao {

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
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroHidrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_HIDRO).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_HIDRO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroHidrobiologicoBean>() {
									@Override
									public RegistroHidrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroHidrobiologicoBean record = new RegistroHidrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdRegHidrobiologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setNumConteo(rs.getDouble(11));
										record.setNumCantidad(rs.getDouble(12));
										record.setIdTipoConteo(rs.getInt(14));
										record.setDescripcionTipoConteo(rs.getString(15));
										record.setIdAnalista(rs.getInt(16));
										record.setFechaMonitoreo(rs.getDate(17));
										record.setFechaRegDato(rs.getDate(18));
										record.setIntervaloMinimo(rs.getString(19) != null && !Strings.isBlank(rs.getString(19)) && !rs.getString(19).equals(" ") ? Double.parseDouble(rs.getString(19)) : null );
										record.setIntervaloMaximo(rs.getString(20) != null && !Strings.isBlank(rs.getString(20)) && !rs.getString(20).equals(" ") ? Double.parseDouble(rs.getString(20)) : null );
										record.setNombreAnalista(rs.getString(22));
										record.setNumResultado(rs.getDouble(24));
										record.setDescripcionObservacion(rs.getString(25));
										record.setIdFormula(rs.getInt(26));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroHidrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroHidrobiologicoBean>() {
									@Override
									public RegistroHidrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroHidrobiologicoBean record = new RegistroHidrobiologicoBean();
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
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroHidrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroHidrobiologicoBean registrarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
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
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CONTEO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CANTIDAD, OracleTypes.DECIMAL)); 
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_METODO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TIPO_CONTEO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_HIDRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_HIDRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroHidrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroHidrobiologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroHidrobiologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroHidrobiologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroHidrobiologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroHidrobiologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroHidrobiologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroHidrobiologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMO, registroHidrobiologico.getIdMicroorganismo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CONTEO, registroHidrobiologico.getNumConteo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CANTIDAD, registroHidrobiologico.getNumCantidad());
			inputs.put(ConstantesSptar.PAR_N_ID_METODO, 0);
			inputs.put(ConstantesSptar.PAR_N_ID_TIPO_CONTEO, registroHidrobiologico.getIdTipoConteo());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroHidrobiologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroHidrobiologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroHidrobiologico.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroHidrobiologico.getNumResultado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroHidrobiologico.getIdFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroHidrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroHidrobiologico.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroHidrobiologico.getDeTermCrea());						
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegHidrobiologico = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO));			
			registroHidrobiologico.setIdRegHidrobiologico(idRegHidrobiologico);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registroHidrobiologico;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CONTEO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CANTIDAD, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_METODO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TIPO_CONTEO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_HIDRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_HIDRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, registroHidrobiologico.getIdRegHidrobiologico());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroHidrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroHidrobiologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroHidrobiologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroHidrobiologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroHidrobiologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroHidrobiologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroHidrobiologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroHidrobiologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMO, registroHidrobiologico.getIdMicroorganismo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CONTEO, registroHidrobiologico.getNumConteo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CANTIDAD, registroHidrobiologico.getNumCantidad());
			inputs.put(ConstantesSptar.PAR_N_ID_METODO, 0);
			inputs.put(ConstantesSptar.PAR_N_ID_TIPO_CONTEO, registroHidrobiologico.getIdTipoConteo());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroHidrobiologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroHidrobiologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroHidrobiologico.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroHidrobiologico.getNumResultado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroHidrobiologico.getIdFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroHidrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroHidrobiologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroHidrobiologico.getDeTermModi());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoBySubParametro(Integer idRegLab, Integer subParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_HIDRO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_HIDRO_XSUBP)//
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroHidrobiologicoBean>() {
									@Override
									public RegistroHidrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroHidrobiologicoBean record = new RegistroHidrobiologicoBean();
										record.setIdRegHidrobiologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setNumConteo(rs.getDouble(11));
										record.setNumCantidad(rs.getDouble(12));
										record.setIdMetodo(rs.getInt(13));
										record.setIdTipoConteo(rs.getInt(14));
										record.setDescripcionTipoConteo(rs.getString(15));
										record.setIdAnalista(rs.getInt(16));
										record.setFechaMonitoreo(rs.getDate(17));
										record.setFechaRegDato(rs.getDate(18));
										record.setIntervaloMinimo(rs.getString(19) != null && !Strings.isBlank(rs.getString(19)) && !rs.getString(19).equals(" ") ? Double.parseDouble(rs.getString(19)) : null );
										record.setIntervaloMaximo(rs.getString(20) != null && !Strings.isBlank(rs.getString(20)) && !rs.getString(20).equals(" ") ? Double.parseDouble(rs.getString(20)) : null );
										record.setNombreAnalista(rs.getString(22));
										record.setNumResultado(rs.getDouble(23));
										record.setDescripcionObservacion(rs.getString(24));
										record.setIdFormula(rs.getInt(25));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, subParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroHidrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_HIDRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_HIDRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, registroHidrobiologico.getIdRegHidrobiologico());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroHidrobiologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroHidrobiologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoByidHidrobiologico(Integer idHidrobiologico,Date fechaDesde,Date FechaHasta) throws GmdException {
		List<RegistroHidrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_HIDRO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_HIDRO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroHidrobiologicoBean>() {
									@Override
									public RegistroHidrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroHidrobiologicoBean record = new RegistroHidrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdRegHidrobiologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setNumConteo(rs.getDouble(11));
										record.setNumCantidad(rs.getDouble(12));
										record.setIdTipoConteo(rs.getInt(14));
										record.setDescripcionTipoConteo(rs.getString(15));
										record.setIdAnalista(rs.getInt(16));
										record.setFechaMonitoreo(rs.getDate(17));
										record.setFechaRegDato(rs.getDate(18));
										record.setIntervaloMinimo(rs.getString(19) != null && !Strings.isBlank(rs.getString(19)) && !rs.getString(19).equals(" ") ? Double.parseDouble(rs.getString(19)) : null );
										record.setIntervaloMaximo(rs.getString(20) != null && !Strings.isBlank(rs.getString(20)) && !rs.getString(20).equals(" ") ? Double.parseDouble(rs.getString(20)) : null );
										record.setNombreAnalista(rs.getString(22));
										record.setNumResultado(rs.getDouble(23));
										record.setDescripcionObservacion(rs.getString(24));
										record.setIdFormula(rs.getInt(25));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_HIDROBIOLOGICO, idHidrobiologico);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroHidrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroHidrobiologicoBean> obtenerRegistroHidrobiologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroHidrobiologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroHidrobiologicoBean>() {
									@Override
									public RegistroHidrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroHidrobiologicoBean record = new RegistroHidrobiologicoBean();
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
		lstRetorno = (List<RegistroHidrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroHidrobiologico(RegistroHidrobiologicoBean registroHidrobiologico) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_HIDRO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_HIDRO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroHidrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroHidrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroHidrobiologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroHidrobiologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}
