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
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.beans.RegistroParasitologicoBean;
import pe.com.sedapal.scr.core.beans.SubParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroParasitologicoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroParasitologicoDaoImpl implements IRegistroParasitologicoDao {

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
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroParasitologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_PARASITO).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_PARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroParasitologicoBean>() {
									@Override
									public RegistroParasitologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroParasitologicoBean record = new RegistroParasitologicoBean();
										record.setIndice(rowNum);
										record.setIdRegParasitologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setNumCantidad(rs.getDouble(11));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));
										record.setIntervaloMinimo(rs.getString(15) != null && !Strings.isBlank(rs.getString(15)) && !rs.getString(15).equals(" ") ? Double.parseDouble(rs.getString(15)) : null );
										record.setIntervaloMaximo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setNombreAnalista(rs.getString(17));
										record.setNumVolumen(rs.getDouble(18));
										record.setNumResultado(rs.getDouble(20));
										record.setDescripcionObservacion(rs.getString(21));
										record.setIdFormula(rs.getInt(22));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroParasitologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroParasitologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroParasitologicoBean>() {
									@Override
									public RegistroParasitologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroParasitologicoBean record = new RegistroParasitologicoBean();
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
		lstRetorno = (List<RegistroParasitologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroParasitologicoBean registrarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
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
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CANTIDAD, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_PARASITO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_PARA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroParasitologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroParasitologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroParasitologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroParasitologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroParasitologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroParasitologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroParasitologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroParasitologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMO, registroParasitologico.getIdMicroorganismo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CANTIDAD, registroParasitologico.getNumCantidad());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroParasitologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroParasitologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroParasitologico.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registroParasitologico.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroParasitologico.getNumResultado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroParasitologico.getIdFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroParasitologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroParasitologico.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroParasitologico.getDeTermCrea());
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegParasitologico = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_PARASITOLOGICO));			
			registroParasitologico.setIdRegParasitologico(idRegParasitologico);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registroParasitologico;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CANTIDAD, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_PARASITO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_PARA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, registroParasitologico.getIdRegParasitologico());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroParasitologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroParasitologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroParasitologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroParasitologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroParasitologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroParasitologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroParasitologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroParasitologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMO, registroParasitologico.getIdMicroorganismo());
			inputs.put(ConstantesSptar.PAR_N_NUM_CANTIDAD, registroParasitologico.getNumCantidad());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroParasitologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroParasitologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroParasitologico.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registroParasitologico.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_RESULTADO, registroParasitologico.getNumResultado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_RESULTADO, registroParasitologico.getIdFormula());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroParasitologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroParasitologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroParasitologico.getDeTermModi());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroParasitologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_PARASITO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_PARA_XSUBP)//
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroParasitologicoBean>() {
									@Override
									public RegistroParasitologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroParasitologicoBean record = new RegistroParasitologicoBean();
										record.setIdRegParasitologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));
										record.setIntervaloMinimo(rs.getString(15) != null && !Strings.isBlank(rs.getString(15)) && !rs.getString(15).equals(" ") ? Double.parseDouble(rs.getString(15)) : null );
										record.setIntervaloMaximo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setNombreAnalista(rs.getString(17));
										record.setNumVolumen(rs.getDouble(18));
										record.setNumResultado(rs.getDouble(19));
										record.setDescripcionObservacion(rs.getString(20));
										record.setIdFormula(rs.getInt(21));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, subParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroParasitologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_PARASITO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_PARA,// 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, registroParasitologico.getIdRegParasitologico());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroParasitologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroParasitologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoByidParasitologico(Integer idParasitologico,Date fechaDesde,Date FechaHasta) throws GmdException {
		List<RegistroParasitologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_PARASITO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_PARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroParasitologicoBean>() {
									@Override
									public RegistroParasitologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroParasitologicoBean record = new RegistroParasitologicoBean();
										record.setIndice(rowNum);
										record.setIdRegParasitologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdMicroorganismo(rs.getInt(9));
										record.setDescripcionMicroorganismo(rs.getString(10));
										record.setNumCantidad(rs.getDouble(11));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));
										record.setIntervaloMinimo(rs.getString(15) != null && !Strings.isBlank(rs.getString(15)) && !rs.getString(15).equals(" ") ? Double.parseDouble(rs.getString(15)) : null );
										record.setIntervaloMaximo(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setNombreAnalista(rs.getString(17));
										record.setNumVolumen(rs.getDouble(18));
										record.setNumResultado(rs.getDouble(19));
										record.setDescripcionObservacion(rs.getString(20));
										record.setIdFormula(rs.getInt(21));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PARASITOLOGICO, idParasitologico);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroParasitologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroParasitologicoBean> obtenerRegistroParasitologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroParasitologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroParasitologicoBean>() {
									@Override
									public RegistroParasitologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroParasitologicoBean record = new RegistroParasitologicoBean();
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
		lstRetorno = (List<RegistroParasitologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroParasitologico(RegistroParasitologicoBean registroParasitologico) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_PARASITO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_PARA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroParasitologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroParasitologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroParasitologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroParasitologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}

