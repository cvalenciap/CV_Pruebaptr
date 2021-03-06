package pe.com.sedapal.scr.core.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
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
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroDBODao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroDBODaoImpl implements IRegistroDBODao {

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
	public List<RegistroDBOBean> obtenerRegistroDBOByRegLaboratorio(Integer idRegLab) throws GmdException {
		List<RegistroDBOBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DBO5).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_DBO5)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDBOBean>() {
									@Override
									public RegistroDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDBOBean record = new RegistroDBOBean();
										record.setIndice(rowNum);
										record.setIdDBO(rs.getInt(1));
										record.setIdRegistroLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));										
										record.setNumVolumen(rs.getString(9) != null && !Strings.isBlank(rs.getString(9)) && !rs.getString(9).equals(" ") ? Double.parseDouble(rs.getString(9)) : null );
										record.setNumOdi(rs.getString(10) != null && !Strings.isBlank(rs.getString(10)) && !rs.getString(10).equals(" ") ? Double.parseDouble(rs.getString(10)) : null );
										record.setNumOdf(rs.getString(11) != null && !Strings.isBlank(rs.getString(11)) && !rs.getString(11).equals(" ") ? Double.parseDouble(rs.getString(11)) : null );
										record.setNumFactor(rs.getString(12) != null && !Strings.isBlank(rs.getString(12)) && !rs.getString(12).equals(" ") ? Double.parseDouble(rs.getString(12)) : null );
										record.setIdFormulaDeplec(rs.getInt(13));
										record.setNumDeplec(rs.getString(14) != null && !Strings.isBlank(rs.getString(14)) && !rs.getString(14).equals(" ") ? Double.parseDouble(rs.getString(14)) : null );
										record.setIdFormulaDilc(rs.getInt(15));
										record.setNumDilc(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIdFormulaDbo5(rs.getInt(17));
										record.setNumDbo5(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Double.parseDouble(rs.getString(18)) : null );
										record.setNumDbo5Sol(rs.getString(19) != null && !Strings.isBlank(rs.getString(19)) && !rs.getString(19).equals(" ") ? Double.parseDouble(rs.getString(19)) : null );
										record.setIdAnalista(rs.getInt(20));
//										record.setFechaMonitoreo(rs.getDate(21));
										record.setFechaMonitoreo(rs.getTimestamp(21));
										record.setFechaRegDato(rs.getDate(22));
										record.setIntervaloMinimo(rs.getString(23) != null && !Strings.isEmpty(rs.getString(23)) && !rs.getString(23).trim().equals("") && !rs.getString(23).equals("")  && !Strings.isEmpty(rs.getString(23))? Double.parseDouble(rs.getString(23).trim()) : null );
										record.setIntervaloMaximo(rs.getString(24) != null  && !Strings.isEmpty(rs.getString(24)) && !rs.getString(24).trim().equals("") && !rs.getString(24).equals("") && !Strings.isEmpty(rs.getString(24))? Double.parseDouble(rs.getString(24).trim()) : null );
										record.setIndicadorFactor(rs.getString(25) != null  && !Strings.isEmpty(rs.getString(25)) && !rs.getString(25).trim().equals("") && !rs.getString(25).equals("") && !Strings.isEmpty(rs.getString(25))? Integer.parseInt(rs.getString(25).trim()) : null );
										record.setNombreAnalista(rs.getString(26));
										record.setNroFrasco(rs.getInt(27));
//										record.setFechaInicial(rs.getDate(28));
										record.setFechaInicial(rs.getTimestamp(28));
										record.setFechaVencimiento(rs.getDate(29));
										record.setNumValorSemilla(rs.getString(30) != null && !Strings.isBlank(rs.getString(30)) && !rs.getString(30).equals(" ") ? Double.parseDouble(rs.getString(30)) : null );
										record.setIndicadorSemilla(rs.getString(31) != null  && !Strings.isEmpty(rs.getString(31)) && !rs.getString(31).trim().equals("") && !rs.getString(31).equals("") && !Strings.isEmpty(rs.getString(31))? Integer.parseInt(rs.getString(31).trim()) : null );
										record.setIdGrupo(rs.getString(32) != null  && !Strings.isEmpty(rs.getString(32)) && !rs.getString(32).trim().equals("") && !rs.getString(32).equals("") && !Strings.isEmpty(rs.getString(32))? Integer.parseInt(rs.getString(32).trim()) : null );
										record.setIndicadorTipoSemilla(rs.getString(33));
										record.setNumDepletion(rs.getDouble(34));
										record.setBlankPromedio(rs.getDouble(35));
										record.setSeededPorDeplec(rs.getDouble(36));
										record.setSeededBOD(rs.getDouble(37));
										record.setSeededSCF(rs.getDouble(38));
										record.setSeededPromedio(rs.getDouble(39));
										record.setGGANetDeplec(rs.getDouble(40));
										record.setGGAPorDeplec(rs.getDouble(41));
										record.setGGADBO(rs.getDouble(42));
										record.setGGAPromedio(rs.getDouble(43));
										record.setSamplesNetDeplec(rs.getDouble(44));
										record.setSamplesDilFactor(rs.getDouble(45));
										record.setSamplesBOD(rs.getDouble(46));
										record.setSamplesPromedio(rs.getDouble(47));
										record.setIndicePadre(rs.getInt(48));
										record.setIdPtoEnlace(rs.getString(49) != null && !Strings.isEmpty(rs.getString(49)) && !rs.getString(49).trim().equals("") && !rs.getString(49).equals("") && !Strings.isEmpty(rs.getString(49))? Integer.parseInt(rs.getString(49).trim()) : null);
										record.setFlagMejorValor(rs.getInt(50));
										record.setSecuencia(rs.getInt(51));
										record.setNumDbo5Formula(rs.getDouble(52));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDBOBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DBO5).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_DBO5_N)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDBOBean>() {
									@Override
									public RegistroDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDBOBean record = new RegistroDBOBean();
										record.setNroFrasco((rowNum+1));
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(2));
										record.setIdParametro(rs.getInt(3));
										record.setIdSubParametro(rs.getInt(4));
										record.setDescripcionSubparametro(rs.getString(5));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setIndicadorFactor(rs.getString(8) != null  && !Strings.isEmpty(rs.getString(8)) && !rs.getString(8).trim().equals("") && !rs.getString(8).equals("") && !Strings.isEmpty(rs.getString(8))? Integer.parseInt(rs.getString(8).trim()) : null );
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setIndicadorSemilla(rs.getString(11) != null  && !Strings.isEmpty(rs.getString(11)) && !rs.getString(11).trim().equals("") && !rs.getString(11).equals("") && !Strings.isEmpty(rs.getString(11))? Integer.parseInt(rs.getString(11).trim()) : null );
										record.setNumVolumen(rs.getString(12) != null  && !Strings.isEmpty(rs.getString(12)) && !rs.getString(12).trim().equals("") && !rs.getString(12).equals("") && !Strings.isEmpty(rs.getString(12))? Double.parseDouble(rs.getString(12).trim()) : null );
										record.setNumValorSemilla(rs.getString(13) != null  && !Strings.isEmpty(rs.getString(13)) && !rs.getString(13).trim().equals("") && !rs.getString(13).equals("") && !Strings.isEmpty(rs.getString(13))? Double.parseDouble(rs.getString(13).trim()) : null );
										record.setIdGrupo(rs.getString(14) != null  && !Strings.isEmpty(rs.getString(14)) && !rs.getString(14).trim().equals("") && !rs.getString(14).equals("") && !Strings.isEmpty(rs.getString(14))? Integer.parseInt(rs.getString(14).trim()) : null );
//										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualTimestamp());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
//										record.setFechaInicial(ParametrosUtil.getFechaActualToDate());
										record.setFechaInicial(ParametrosUtil.getFechaActualTimestamp());
										record.setFechaVencimiento(ParametrosUtil.getFechaActualToDate());
										record.setIndicadorTipoSemilla(rs.getString(15));
										record.setIndicePadre(rs.getInt(16));
										record.setIdFormulaDbo5(0);
										record.setIdFormulaDeplec(0);
										record.setIdFormulaDilc(0);	
										record.setSecuencia(rs.getInt(18));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroDBOBean registrarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.TIMESTAMP));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FACTOR, OracleTypes.DECIMAL));					
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DEPLEC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DILC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DBO, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FRASCO, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, OracleTypes.TIMESTAMP));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_VCTO, OracleTypes.DATE));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_SEMILLA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEM_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_BLA_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_POR_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_BOD, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_SCF, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_NETDEP, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_POR_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_DBO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_NETDEP, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_DILFACTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_DBO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_TIP_SEMILLA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_FORMULA, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_DBO5, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_DBO5, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroDBO.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, RegistroDBO.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, RegistroDBO.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, RegistroDBO.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, RegistroDBO.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, RegistroDBO.getIdAnalista());			
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, RegistroDBO.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, RegistroDBO.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, RegistroDBO.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, RegistroDBO.getNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, RegistroDBO.getNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_FACTOR, RegistroDBO.getNumFactor());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DEPLEC, RegistroDBO.getIdFormulaDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, RegistroDBO.getNumDeplec());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DILC, RegistroDBO.getIdFormulaDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, RegistroDBO.getNumDilc());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DBO, RegistroDBO.getIdFormulaDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, RegistroDBO.getNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_FRASCO, RegistroDBO.getNroFrasco());
			inputs.put(ConstantesSptar.PAR_D_FE_INICIO, RegistroDBO.getFechaInicial());
			inputs.put(ConstantesSptar.PAR_D_FE_VCTO, RegistroDBO.getFechaVencimiento());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, RegistroDBO.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, RegistroDBO.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, RegistroDBO.getFlagAprobador());		
			inputs.put(ConstantesSptar.PAR_N_VAL_SEMILLA, RegistroDBO.getNumValorSemilla());
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, RegistroDBO.getIdGrupo());
			inputs.put(ConstantesSptar.PAR_N_SEM_DEPLEC, RegistroDBO.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_BLA_PROM, RegistroDBO.getBlankPromedio());
			inputs.put(ConstantesSptar.PAR_N_SEE_POR_DEPLEC, RegistroDBO.getSeededPorDeplec());
			inputs.put(ConstantesSptar.PAR_N_SEE_BOD, RegistroDBO.getSeededBOD());
			inputs.put(ConstantesSptar.PAR_N_SEE_SCF, RegistroDBO.getSeededSCF());
			inputs.put(ConstantesSptar.PAR_N_SEE_PROM, RegistroDBO.getSeededPromedio());
			inputs.put(ConstantesSptar.PAR_N_GGA_NETDEP, RegistroDBO.getGGANetDeplec());
			inputs.put(ConstantesSptar.PAR_N_GGA_POR_DEPLEC, RegistroDBO.getGGAPorDeplec());
			inputs.put(ConstantesSptar.PAR_N_GGA_DBO, RegistroDBO.getGGADBO());
			inputs.put(ConstantesSptar.PAR_N_GGA_PROM, RegistroDBO.getGGAPromedio());
			inputs.put(ConstantesSptar.PAR_N_SAM_NETDEP, RegistroDBO.getSamplesNetDeplec());
			inputs.put(ConstantesSptar.PAR_N_SAM_DILFACTOR, RegistroDBO.getSamplesDilFactor());
			inputs.put(ConstantesSptar.PAR_N_SAM_DBO, RegistroDBO.getSamplesBOD());
			inputs.put(ConstantesSptar.PAR_N_SAM_PROM, RegistroDBO.getSamplesPromedio());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, RegistroDBO.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_V_TIP_SEMILLA, RegistroDBO.getIndicadorTipoSemilla());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroDBO.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, RegistroDBO.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, RegistroDBO.getDeTermCrea());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_FORMULA, RegistroDBO.getNumDbo5Formula());
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idDBO = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_DBO5));			
			RegistroDBO.setIdDBO(idDBO);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return RegistroDBO;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DBO5, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.TIMESTAMP));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FACTOR, OracleTypes.DECIMAL));					
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DEPLEC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DILC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_DBO, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_FRASCO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, OracleTypes.TIMESTAMP));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_VCTO, OracleTypes.DATE));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_SEMILLA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEM_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_BLA_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_POR_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_BOD, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_SCF, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SEE_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_NETDEP, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_POR_DEPLEC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_DBO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_GGA_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_NETDEP, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_DILFACTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_DBO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SAM_PROM, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_TIP_SEMILLA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_FORMULA, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_DBO5, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_DBO5, RegistroDBO.getIdDBO());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroDBO.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, RegistroDBO.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, RegistroDBO.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, RegistroDBO.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, RegistroDBO.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, RegistroDBO.getIdAnalista());			
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, RegistroDBO.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, RegistroDBO.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, RegistroDBO.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, RegistroDBO.getNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, RegistroDBO.getNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_FACTOR, RegistroDBO.getNumFactor());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DEPLEC, RegistroDBO.getIdFormulaDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, RegistroDBO.getNumDeplec());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DILC, RegistroDBO.getIdFormulaDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, RegistroDBO.getNumDilc());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_DBO, RegistroDBO.getIdFormulaDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, RegistroDBO.getNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_FRASCO, RegistroDBO.getNroFrasco());
			inputs.put(ConstantesSptar.PAR_D_FE_INICIO, RegistroDBO.getFechaInicial());
			inputs.put(ConstantesSptar.PAR_D_FE_VCTO, RegistroDBO.getFechaVencimiento());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, RegistroDBO.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, RegistroDBO.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, RegistroDBO.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_VAL_SEMILLA, RegistroDBO.getNumValorSemilla());
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, RegistroDBO.getIdGrupo());
			inputs.put(ConstantesSptar.PAR_N_SEM_DEPLEC, RegistroDBO.getNumDepletion());
			inputs.put(ConstantesSptar.PAR_N_BLA_PROM, RegistroDBO.getBlankPromedio());
			inputs.put(ConstantesSptar.PAR_N_SEE_POR_DEPLEC, RegistroDBO.getSeededPorDeplec());
			inputs.put(ConstantesSptar.PAR_N_SEE_BOD, RegistroDBO.getSeededBOD());
			inputs.put(ConstantesSptar.PAR_N_SEE_SCF, RegistroDBO.getSeededSCF());
			inputs.put(ConstantesSptar.PAR_N_SEE_PROM, RegistroDBO.getSeededPromedio());
			inputs.put(ConstantesSptar.PAR_N_GGA_NETDEP, RegistroDBO.getGGANetDeplec());
			inputs.put(ConstantesSptar.PAR_N_GGA_POR_DEPLEC, RegistroDBO.getGGAPorDeplec());
			inputs.put(ConstantesSptar.PAR_N_GGA_DBO, RegistroDBO.getGGADBO());
			inputs.put(ConstantesSptar.PAR_N_GGA_PROM, RegistroDBO.getGGAPromedio());
			inputs.put(ConstantesSptar.PAR_N_SAM_NETDEP, RegistroDBO.getSamplesNetDeplec());
			inputs.put(ConstantesSptar.PAR_N_SAM_DILFACTOR, RegistroDBO.getSamplesDilFactor());
			inputs.put(ConstantesSptar.PAR_N_SAM_DBO, RegistroDBO.getSamplesBOD());
			inputs.put(ConstantesSptar.PAR_N_SAM_PROM, RegistroDBO.getSamplesPromedio());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, RegistroDBO.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_V_TIP_SEMILLA, RegistroDBO.getIndicadorTipoSemilla());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroDBO.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroDBO.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroDBO.getDeTermModi());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_FORMULA, RegistroDBO.getNumDbo5Formula());
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroDBO(Integer idGrupoRegistroDBO, Integer idRegistroLaboratorio, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_DBO5, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, idGrupoRegistroDBO);
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, auditoria.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, auditoria.getDeTermCrea());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOByidDBO(Integer idDBO, Date fechaDesde, Date FechaHasta)
			throws GmdException {
		List<RegistroDBOBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DBO5).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_DBO5)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_DBO5, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDBOBean>() {
									@Override
									public RegistroDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDBOBean record = new RegistroDBOBean();
										record.setIndice(rowNum);
										record.setIdDBO(rs.getInt(1));
										record.setIdRegistroLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));										
										record.setNumVolumen(rs.getString(9) != null && !Strings.isBlank(rs.getString(9)) && !rs.getString(9).equals(" ") ? Double.parseDouble(rs.getString(9)) : null );
										record.setNumOdi(rs.getString(10) != null && !Strings.isBlank(rs.getString(10)) && !rs.getString(10).equals(" ") ? Double.parseDouble(rs.getString(10)) : null );
										record.setNumOdf(rs.getString(11) != null && !Strings.isBlank(rs.getString(11)) && !rs.getString(11).equals(" ") ? Double.parseDouble(rs.getString(11)) : null );
										record.setNumFactor(rs.getString(12) != null && !Strings.isBlank(rs.getString(12)) && !rs.getString(12).equals(" ") ? Double.parseDouble(rs.getString(12)) : null );
										record.setIdFormulaDeplec(rs.getInt(13));
										record.setNumDeplec(rs.getString(14) != null && !Strings.isBlank(rs.getString(14)) && !rs.getString(14).equals(" ") ? Double.parseDouble(rs.getString(14)) : null );
										record.setIdFormulaDilc(rs.getInt(15));
										record.setNumDilc(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIdFormulaDbo5(rs.getInt(17));
										record.setNumDbo5(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Double.parseDouble(rs.getString(18)) : null );
										record.setNumDbo5Sol(rs.getString(19) != null && !Strings.isBlank(rs.getString(19)) && !rs.getString(19).equals(" ") ? Double.parseDouble(rs.getString(19)) : null );
										record.setIdAnalista(rs.getInt(20));
//										record.setFechaMonitoreo(rs.getDate(21));
										record.setFechaMonitoreo(rs.getTimestamp(21));
										record.setFechaRegDato(rs.getDate(22));
										record.setIntervaloMinimo(rs.getString(23) != null && !Strings.isEmpty(rs.getString(23)) && !rs.getString(23).trim().equals("") && !rs.getString(23).equals("")  && !Strings.isEmpty(rs.getString(23))? Double.parseDouble(rs.getString(23).trim()) : null );
										record.setIntervaloMaximo(rs.getString(24) != null  && !Strings.isEmpty(rs.getString(24)) && !rs.getString(24).trim().equals("") && !rs.getString(24).equals("") && !Strings.isEmpty(rs.getString(24))? Double.parseDouble(rs.getString(24).trim()) : null );
										record.setIndicadorFactor(rs.getString(25) != null  && !Strings.isEmpty(rs.getString(25)) && !rs.getString(25).trim().equals("") && !rs.getString(25).equals("") && !Strings.isEmpty(rs.getString(25))? Integer.parseInt(rs.getString(25).trim()) : null );
										record.setNombreAnalista(rs.getString(26));
										record.setNroFrasco(rs.getInt(27));
//										record.setFechaInicial(rs.getDate(28));
										record.setFechaInicial(rs.getTimestamp(28));
										record.setFechaVencimiento(rs.getDate(29));
										record.setNumValorSemilla(rs.getString(30) != null && !Strings.isBlank(rs.getString(30)) && !rs.getString(30).equals(" ") ? Double.parseDouble(rs.getString(30)) : null );
										record.setIndicadorSemilla(rs.getString(31) != null  && !Strings.isEmpty(rs.getString(31)) && !rs.getString(31).trim().equals("") && !rs.getString(31).equals("") && !Strings.isEmpty(rs.getString(31))? Integer.parseInt(rs.getString(31).trim()) : null );
										record.setIdGrupo(rs.getString(32) != null  && !Strings.isEmpty(rs.getString(32)) && !rs.getString(32).trim().equals("") && !rs.getString(32).equals("") && !Strings.isEmpty(rs.getString(32))? Integer.parseInt(rs.getString(32).trim()) : null );
										record.setNumDbo5Formula(rs.getDouble(52));
										return record;								
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_DBO5, idDBO);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroDBOBean> obtenerRegistroDBOSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroDBOBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroDBOBean>() {
									@Override
									public RegistroDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroDBOBean record = new RegistroDBOBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setIndicadorFactor(rs.getString(8) != null  && !Strings.isEmpty(rs.getString(8)) && !rs.getString(8).trim().equals("") && !rs.getString(8).equals("") && !Strings.isEmpty(rs.getString(8))? Integer.parseInt(rs.getString(8).trim()) : null );
										record.setDescripcionSubparametro(rs.getString(9));
//										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualTimestamp());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
//										record.setFechaInicial(ParametrosUtil.getFechaActualToDate());
										record.setFechaInicial(ParametrosUtil.getFechaActualTimestamp());
										record.setFechaVencimiento(ParametrosUtil.getFechaActualToDate());
										record.setIdFormulaDbo5(0);
										record.setIdFormulaDeplec(0);
										record.setIdFormulaDilc(0);										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFechasRegistroDBO(RegistroDBOBean RegistroDBO) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, OracleTypes.TIMESTAMP));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_VCTO, OracleTypes.DATE));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_FECH_RLAB_DBO5, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroDBO.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_D_FE_INICIO, RegistroDBO.getFechaInicial());
			inputs.put(ConstantesSptar.PAR_D_FE_VCTO, RegistroDBO.getFechaVencimiento());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroDBO.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroDBO.getDeTermModi());
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroBDO(RegistroDBOBean RegistroDBO) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_DBO5, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroDBO.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroDBO.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroDBO.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroDBO.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void obtenerMejorRegistroEnlace(Integer idRegistroLaboratorio, Integer idPtoMuestra, Integer idSubParametro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_SEMILLA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
						
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_MEJOR_VALOR_CON_ENLACE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_SEMILLA, idPtoMuestra);
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void obtenerMejorRegistroNoEnlace(Integer idRegistroLaboratorio, Integer idPtoMuestra, Integer idSubParametro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
						
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_MEJOR_VALOR_SIN_ENLACE, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, idPtoMuestra);
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void establecerMarcaMejorRegistro(Integer idRegistroDBO, Integer numMejorValor) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DBO5, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, OracleTypes.DECIMAL));	
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_MEJOR_VALOR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_DBO5, idRegistroDBO);
			inputs.put(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, numMejorValor);
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerMejorRegistroDBOES(Integer idRegistro) throws GmdException{
		List<RegistroLabReporteBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_DBO5).withProcedureName(ConstantesSptar.PRC_MEJOR_REGISTRO_ES)
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
										record.setNumValor(rs.getDouble(6));
										record.setDescripcionTipoRegistro(rs.getString(7));
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
