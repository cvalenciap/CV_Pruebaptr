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
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.RegistroMicrobiologicoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IRegistroMicrobiologicoSecundarioDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroMicrobiologicoSecundarioDaoImpl implements IRegistroMicrobiologicoSecundarioDao {

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
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByRegLaboratorio(Integer idRegLab, Integer idTipoMicrobiologico) throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_MBIOLOGICO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdRegMicrobiologico(rs.getInt(1));
										record.setGrupo(rs.getInt(2));
										record.setIndicePadre(rs.getInt(3));
										record.setIdRegLaboratorio(rs.getInt(4));
										record.setFechaRegistro(rs.getDate(5));
										record.setIdPtarxSector(rs.getInt(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setIdDilucionInicial(rs.getInt(11));
										record.setDescripcionDilucionInicial(rs.getString(12));
										record.setNumValorDilucion(rs.getDouble(13));
										record.setDescripcionDilucion(rs.getString(14));
										record.setSecuencia(rs.getInt(15));
										record.setNumCLT24(rs.getDouble(16));
										record.setNumCLT48(rs.getDouble(17));
										record.setNumCLVBB24(rs.getDouble(18));
										record.setNumCLVBB48(rs.getDouble(19));
										record.setIdTuboDilucionCLV48(rs.getInt(20));
										record.setDescripcionTuboDilucionCLV48(rs.getString(21));
										record.setNumValorCT(rs.getDouble(22));
										record.setNumCTNMP(rs.getString(23));
										record.setValorDecimalCLV48(rs.getDouble(24));
										record.setIdAnalista(rs.getInt(25));
										record.setFechaMonitoreo(rs.getDate(26));
										record.setFechaRegDato(rs.getDate(27));
										record.setIntervaloMinimo(rs.getString(28) != null && !Strings.isBlank(rs.getString(28)) && !rs.getString(28).equals(" ") ? Double.parseDouble(rs.getString(28)) : null );
										record.setIntervaloMaximo(rs.getString(29) != null && !Strings.isBlank(rs.getString(29)) && !rs.getString(29).equals(" ") ? Double.parseDouble(rs.getString(29)) : null );
										record.setNombreAnalista(rs.getString(31));
										record.setFlagMejorValor(rs.getInt(33));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idTipoMicrobiologico);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setIndicePadre(ConstantesUtil.INDICE_PADRE);
										record.setSecuencia(ConstantesUtil.ORDEN_PADRE);
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroMicrobiologicoBean registrarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROGRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DILUCION_INICIAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILUCION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_DILUCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLT24, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLT48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLVBB24, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLVBB48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TUBO_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VAL_CLV48, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_DEC_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_MBIOLOGICO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_MICROGRUPO, registroMicrobiologico.getGrupo());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, registroMicrobiologico.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroMicrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroMicrobiologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroMicrobiologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroMicrobiologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroMicrobiologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroMicrobiologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroMicrobiologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroMicrobiologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_DILUCION_INICIAL, registroMicrobiologico.getIdDilucionInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILUCION, registroMicrobiologico.getNumValorDilucion());
			inputs.put(ConstantesSptar.PAR_N_VAL_DILUCION, registroMicrobiologico.getDescripcionDilucion());
			inputs.put(ConstantesSptar.PAR_N_SECUENCIA, registroMicrobiologico.getSecuencia());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLT24, registroMicrobiologico.getNumCLT24());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLT48, registroMicrobiologico.getNumCLT48());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLVBB24, registroMicrobiologico.getNumCLVBB24());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLVBB48, registroMicrobiologico.getNumCLVBB48());
			inputs.put(ConstantesSptar.PAR_N_ID_TUBO_CLV48, registroMicrobiologico.getIdTuboDilucionCLV48());
			inputs.put(ConstantesSptar.PAR_N_VAL_CLV48, registroMicrobiologico.getNumValorCT());
			inputs.put(ConstantesSptar.PAR_V_VAL_CLV48, registroMicrobiologico.getNumCTNMP());
			inputs.put(ConstantesSptar.PAR_N_DEC_CLV48, registroMicrobiologico.getValorDecimalCLV48());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroMicrobiologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroMicrobiologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroMicrobiologico.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, registroMicrobiologico.getFlagMejorValor());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroMicrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroMicrobiologico.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroMicrobiologico.getDeTermCrea());
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegMicrobiologico = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO));			
			registroMicrobiologico.setIdRegMicrobiologico(idRegMicrobiologico);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registroMicrobiologico;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROGRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DILUCION_INICIAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILUCION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_DILUCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLT24, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLT48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLVBB24, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_CLVBB48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_TUBO_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VAL_CLV48, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_DEC_CLV48, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_MBIOLOGICO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, registroMicrobiologico.getIdRegMicrobiologico());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROGRUPO, registroMicrobiologico.getGrupo());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, registroMicrobiologico.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroMicrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, registroMicrobiologico.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, registroMicrobiologico.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, registroMicrobiologico.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, registroMicrobiologico.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, registroMicrobiologico.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registroMicrobiologico.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registroMicrobiologico.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_DILUCION_INICIAL, registroMicrobiologico.getIdDilucionInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILUCION, registroMicrobiologico.getNumValorDilucion());
			inputs.put(ConstantesSptar.PAR_N_VAL_DILUCION, registroMicrobiologico.getDescripcionDilucion());
			inputs.put(ConstantesSptar.PAR_N_SECUENCIA, registroMicrobiologico.getSecuencia());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLT24, registroMicrobiologico.getNumCLT24());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLT48, registroMicrobiologico.getNumCLT48());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLVBB24, registroMicrobiologico.getNumCLVBB24());
			inputs.put(ConstantesSptar.PAR_N_NUM_CLVBB48, registroMicrobiologico.getNumCLVBB48());
			inputs.put(ConstantesSptar.PAR_N_ID_TUBO_CLV48, registroMicrobiologico.getIdTuboDilucionCLV48());
			inputs.put(ConstantesSptar.PAR_N_VAL_CLV48, registroMicrobiologico.getNumValorCT());
			inputs.put(ConstantesSptar.PAR_V_VAL_CLV48, registroMicrobiologico.getNumCTNMP());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, registroMicrobiologico.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, registroMicrobiologico.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, registroMicrobiologico.getFlagAprobador());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroMicrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroMicrobiologico.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroMicrobiologico.getDeTermCrea());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoBySubParametro(Integer idRegLab, Integer subParametro)throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_PARASITO).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_PARA_XSUBP)//
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIdRegMicrobiologico(rs.getInt(1));
										record.setIdRegLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdSubParametro(rs.getInt(5));
										record.setDescripcionSubparametro(rs.getString(6));
										record.setIdPuntoMuestra(rs.getInt(7));
										record.setDescripcionPuntoMuestra(rs.getString(8));
										record.setIdAnalista(rs.getInt(12));
										record.setFechaMonitoreo(rs.getDate(13));
										record.setFechaRegDato(rs.getDate(14));
										record.setNombreAnalista(rs.getString(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, subParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_MBIOLOGICO,// 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, registroMicrobiologico.getIdRegMicrobiologico());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroMicrobiologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroMicrobiologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByidMicrobiologico(Integer idMicrobiologico,Date fechaDesde,Date FechaHasta) throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_MBIOLOGICO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdRegMicrobiologico(rs.getInt(1));
										record.setGrupo(rs.getInt(2));
										record.setIndicePadre(rs.getInt(3));
										record.setIdRegLaboratorio(rs.getInt(4));
										record.setFechaRegistro(rs.getDate(5));
										record.setIdPtarxSector(rs.getInt(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));
										record.setIdPuntoMuestra(rs.getInt(9));
										record.setDescripcionPuntoMuestra(rs.getString(10));
										record.setValorDecimalCLV48(rs.getDouble(29));
										record.setIdAnalista(rs.getInt(30));
										record.setFechaMonitoreo(rs.getDate(31));
										record.setFechaRegDato(rs.getDate(32));
										record.setNombreAnalista(rs.getString(36));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, idMicrobiologico);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
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
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroMicrobiologicoBean> obtenerRegistroMicrobiologicoByPtarPuntoMuestra(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroMicrobiologicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTAR_GRP)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroMicrobiologicoBean>() {
									@Override
									public RegistroMicrobiologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroMicrobiologicoBean record = new RegistroMicrobiologicoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdPuntoMuestra(rs.getInt(2));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdParametro(rs.getInt(4));
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setIndicePadre(ConstantesUtil.INDICE_PADRE);
										record.setSecuencia(ConstantesUtil.ORDEN_PADRE);
										record.setGrupo(rowNum);
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroMicrobiologicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_MBIOLOGICO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroMicrobiologico.getIdRegLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroMicrobiologico.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registroMicrobiologico.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registroMicrobiologico.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void eliminarGrupoRegistroMicrobiologico(Integer idRegistroLaboratorio, Integer grupo, BaseSptarBean auditoria, Integer idSubParametro) throws GmdException{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROGRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_MBIO_GRP, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);
			inputs.put(ConstantesSptar.PAR_N_ID_MICROGRUPO, grupo);
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, auditoria.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, auditoria.getDeTermCrea());	
			
			this.execSp.executeSp(inputs);
		}catch(Exception excepcion){
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarMejorValorMicrobiologico(RegistroMicrobiologicoBean registroMicrobiologico) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, OracleTypes.DECIMAL));	
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_MB_OTROS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_MV_MBIOLOGICO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantesSptar.PAR_N_ID_MICROBIOLOGICO, registroMicrobiologico.getIdRegMicrobiologico());
			inputs.put(ConstantesSptar.PAR_N_FLG_MEJOR_VALOR, registroMicrobiologico.getFlagMejorValor());
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}

