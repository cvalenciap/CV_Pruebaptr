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
import pe.com.sedapal.scr.core.beans.RegistroSolidoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroSolidoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class RegistroSolidoDaoImpl implements IRegistroSolidoDao {

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
	public List<RegistroSolidoBean> obtenerRegistroSolidoByRegLaboratorio(Integer idRegLab,Integer idTipoSolido) throws GmdException {
		List<RegistroSolidoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_SOLI).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_SOLI)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, Types.DECIMAL),						
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroSolidoBean>() {
									@Override
									public RegistroSolidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroSolidoBean record = new RegistroSolidoBean();
										record.setIndice(rowNum);
										record.setIdSolido(rs.getInt(1));
										record.setIdRegistroLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));										
										record.setPesoInicial(rs.getString(9) != null && !Strings.isBlank(rs.getString(9)) && !rs.getString(9).equals(" ") ? Double.parseDouble(rs.getString(9)) : null );
										record.setVolumenFiltrado(rs.getString(10) != null && !Strings.isBlank(rs.getString(10)) && !rs.getString(10).equals(" ") ? Double.parseDouble(rs.getString(10)) : null );
										record.setPesoFinal(rs.getString(11) != null && !Strings.isBlank(rs.getString(11)) && !rs.getString(11).equals(" ") ? Double.parseDouble(rs.getString(11)) : null );
										record.setPesoCalcinado(rs.getString(12) != null && !Strings.isBlank(rs.getString(12)) && !rs.getString(12).equals(" ") ? Double.parseDouble(rs.getString(12)) : null );
										record.setIdFormulaSst(rs.getInt(13));
										record.setNumSst(rs.getString(14) != null && !Strings.isBlank(rs.getString(14)) && !rs.getString(14).equals(" ") ? Double.parseDouble(rs.getString(14)) : null );
										record.setIdFormulaSsf(rs.getInt(15));
										record.setNumSsf(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIdFormulaSsv(rs.getInt(17));
										record.setNumSsv(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Double.parseDouble(rs.getString(18)) : null );
										record.setIdAnalista(rs.getInt(19));
										record.setFechaMonitoreo(rs.getDate(20));
										record.setFechaRegDato(rs.getDate(21));
										record.setNombreAnalista(rs.getString(22));
										record.setIdTipoSolido(rs.getInt(23));										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_TIPO_SOLI, idTipoSolido);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroSolidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroSolidoBean registrarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
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
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TARA, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN_FILTRADO, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_CALCINADO, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SST, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SST, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SSF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SSV, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSV, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SOLIDOS, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_SOLI+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_SOLI, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroSolido.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, RegistroSolido.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, RegistroSolido.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, RegistroSolido.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, RegistroSolido.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, RegistroSolido.getIdAnalista());			
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, RegistroSolido.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, RegistroSolido.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_TARA, 0);
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, RegistroSolido.getPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, RegistroSolido.getPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN_FILTRADO, RegistroSolido.getVolumenFiltrado());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_CALCINADO, RegistroSolido.getPesoCalcinado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SST, RegistroSolido.getIdFormulaSst());
			inputs.put(ConstantesSptar.PAR_N_NUM_SST, RegistroSolido.getNumSst());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SSF, RegistroSolido.getIdFormulaSsf());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSF, RegistroSolido.getNumSsf());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SSV, RegistroSolido.getIdFormulaSsv());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSV, RegistroSolido.getNumSsv());			
			inputs.put(ConstantesSptar.PAR_N_TIPO_SOLI, RegistroSolido.getIdTipoSolido());	
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, RegistroSolido.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, RegistroSolido.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, RegistroSolido.getFlagAprobador());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroSolido.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, RegistroSolido.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, RegistroSolido.getDeTermCrea());
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idSolido = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SOLIDOS));			
			RegistroSolido.setIdSolido(idSolido);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return RegistroSolido;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SOLIDOS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_MONITOREO, OracleTypes.DATE));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TARA, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN_FILTRADO, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_CALCINADO, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SST, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SST, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SSF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FRM_SSV, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSV, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_SOLI+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_RLAB_SOLI, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SOLIDOS, RegistroSolido.getIdSolido());
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroSolido.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, RegistroSolido.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, RegistroSolido.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, RegistroSolido.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, RegistroSolido.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, RegistroSolido.getIdAnalista());			
			inputs.put(ConstantesSptar.PAR_D_FE_REGISTRO, RegistroSolido.getFechaRegDato());
			inputs.put(ConstantesSptar.PAR_D_FE_MONITOREO, RegistroSolido.getFechaMonitoreo());
			inputs.put(ConstantesSptar.PAR_N_NUM_TARA, 0);
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, RegistroSolido.getPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, RegistroSolido.getPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN_FILTRADO, RegistroSolido.getVolumenFiltrado());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_CALCINADO, RegistroSolido.getPesoCalcinado());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SST, RegistroSolido.getIdFormulaSst());
			inputs.put(ConstantesSptar.PAR_N_NUM_SST, RegistroSolido.getNumSst());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SSF, RegistroSolido.getIdFormulaSsf());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSF, RegistroSolido.getNumSsf());
			inputs.put(ConstantesSptar.PAR_N_ID_FRM_SSV, RegistroSolido.getIdFormulaSsv());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSV, RegistroSolido.getNumSsv());		
			inputs.put(ConstantesSptar.PAR_N_TIPO_SOLI, RegistroSolido.getIdTipoSolido());	
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, RegistroSolido.getDescripcionObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, RegistroSolido.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, RegistroSolido.getFlagAprobador());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroSolido.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroSolido.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroSolido.getDeTermModi());
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SOLIDOS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_SOLI+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_RLAB_SOLI, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SOLIDOS, RegistroSolido.getIdSolido());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroSolido.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroSolido.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoByidSolido(Integer idSolido,Integer idTipoSolido, Date fechaDesde, Date FechaHasta)
			throws GmdException {
		List<RegistroSolidoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RLAB_SOLI).withProcedureName(ConstantesSptar.PRC_BUSCAR_RLAB_SOLI)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_SOLIDOS, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroSolidoBean>() {
									@Override
									public RegistroSolidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroSolidoBean record = new RegistroSolidoBean();
										record.setIndice(rowNum);
										record.setIdSolido(rs.getInt(1));
										record.setIdRegistroLaboratorio(rs.getInt(2));
										record.setFechaRegistro(rs.getDate(3));
										record.setIdPtarxSector(rs.getInt(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPuntoMuestra(rs.getString(6));
										record.setIdSubParametro(rs.getInt(7));
										record.setDescripcionSubparametro(rs.getString(8));										
										record.setPesoInicial(rs.getString(9) != null && !Strings.isBlank(rs.getString(9)) && !rs.getString(9).equals(" ") ? Double.parseDouble(rs.getString(9)) : null );
										record.setVolumenFiltrado(rs.getString(10) != null && !Strings.isBlank(rs.getString(10)) && !rs.getString(10).equals(" ") ? Double.parseDouble(rs.getString(10)) : null );
										record.setPesoFinal(rs.getString(11) != null && !Strings.isBlank(rs.getString(11)) && !rs.getString(11).equals(" ") ? Double.parseDouble(rs.getString(11)) : null );
										record.setPesoCalcinado(rs.getString(12) != null && !Strings.isBlank(rs.getString(12)) && !rs.getString(12).equals(" ") ? Double.parseDouble(rs.getString(12)) : null );
										record.setIdFormulaSst(rs.getInt(13));
										record.setNumSst(rs.getString(14) != null && !Strings.isBlank(rs.getString(14)) && !rs.getString(14).equals(" ") ? Double.parseDouble(rs.getString(14)) : null );
										record.setIdFormulaSsf(rs.getInt(15));
										record.setNumSsf(rs.getString(16) != null && !Strings.isBlank(rs.getString(16)) && !rs.getString(16).equals(" ") ? Double.parseDouble(rs.getString(16)) : null );
										record.setIdFormulaSsv(rs.getInt(17));
										record.setNumSsv(rs.getString(18) != null && !Strings.isBlank(rs.getString(18)) && !rs.getString(18).equals(" ") ? Double.parseDouble(rs.getString(18)) : null );
										record.setIdAnalista(rs.getInt(19));
										record.setFechaMonitoreo(rs.getDate(20));
										record.setFechaRegDato(rs.getDate(21));
										record.setNombreAnalista(rs.getString(22));
										record.setIdTipoSolido(rs.getInt(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_SOLIDOS, idSolido);
		params.addValue(ConstantesSptar.PAR_N_TIPO_SOLI, idTipoSolido);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaDesde);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, FechaHasta);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroSolidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoByPtarSubP(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroSolidoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTARXSUB)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroSolidoBean>() {
									@Override
									public RegistroSolidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroSolidoBean record = new RegistroSolidoBean();
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
										record.setIdFormulaSst(0);
										record.setIdFormulaSsf(0);
										record.setIdFormulaSsv(0);										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroSolidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroSolidoBean> obtenerRegistroSolidoSubParametros(Integer idPtarxSector, Integer idParametro)
			throws GmdException {
		List<RegistroSolidoBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SUBPARAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_SUBPARAXPTAR)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroSolidoBean>() {
									@Override
									public RegistroSolidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroSolidoBean record = new RegistroSolidoBean();
										record.setIndice(rowNum);
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(6) != null && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("")  && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIntervaloMaximo(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Double.parseDouble(rs.getString(7).trim()) : null );
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setIdFormulaSst(0);
										record.setIdFormulaSsf(0);
										record.setIdFormulaSsv(0);										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroSolidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void aprobarRegistroSolido(RegistroSolidoBean RegistroSolido) throws GmdException {
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

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_SOLI+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APROB_RLAB_SOLI, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, RegistroSolido.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, RegistroSolido.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, RegistroSolido.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, RegistroSolido.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
}
