package pe.com.sedapal.scr.core.dao.impl;

import java.text.ParseException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroLaboratorioDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;
import pe.com.sedapal.scr.core.util.Utils;

@Repository
public class RegistroLaboratorioDaoImpl implements IRegistroLaboratorioDao {

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
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin) throws GmdException, ParseException{
		List<RegistroLaboratorioBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLaboratorioBean>() {
									@Override
									public RegistroLaboratorioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLaboratorioBean record = new RegistroLaboratorioBean();
										record.setIdRegistroLaboratorio(rs.getInt(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdPtarxSector(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdDirecto(rs.getInt(6));
										record.setDescripFlagDirecto(rs.getString(7));
										record.setIdDBO(rs.getInt(8));
										record.setDescripFlagDBO(rs.getString(9));
										record.setIdAceite(rs.getInt(10));
										record.setDescripFlagAceite(rs.getString(11));
										record.setIdSolido(rs.getInt(12));
										record.setDescripFlagSolido(rs.getString(13));
										record.setIdHidrobiologico(rs.getInt(14));
										record.setDescripFlagHidrobiologico(rs.getString(15));
										record.setIdMicrobiologico(rs.getInt(16));
										record.setDescripFlagMicrobiologico(rs.getString(17));
										record.setIdParasitologico(rs.getInt(18));
										record.setDescripFlagParasitologico(rs.getString(19));
										record.setDescripcionEstadoAprob(rs.getString(20));
										record.setStRegi(rs.getString(21));
										record.setEstado(rs.getString(22));
										record.setFechaHoraCreacionString(rs.getString(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector != null  && !Strings.isEmpty(idPtarxSector) && !idPtarxSector.trim().equals("") && !idPtarxSector.equals("") && !Strings.isEmpty(idPtarxSector) ? Integer.parseInt(idPtarxSector.trim()) : null );
        params.addValue(ConstantesSptar.PAR_N_FE_DESDE, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		params.addValue(ConstantesSptar.PAR_N_FLG_APROBACION, idEstado != null  && !Strings.isEmpty(idEstado) && !idEstado.trim().equals("") && !idEstado.equals("") && !Strings.isEmpty(idEstado) ? Integer.parseInt(idEstado.trim()) : null );
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLaboratorioBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorio2(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin) throws GmdException, ParseException{
		List<RegistroLaboratorioBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB_2)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLaboratorioBean>() {
									@Override
									public RegistroLaboratorioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLaboratorioBean record = new RegistroLaboratorioBean();
										record.setIdRegistroLaboratorio(rs.getInt(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdPtarxSector(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdDirecto(rs.getInt(6));
										record.setDescripFlagDirecto(rs.getString(7));
										record.setIdDBO(rs.getInt(8));
										record.setDescripFlagDBO(rs.getString(9));
										record.setIdAceite(rs.getInt(10));
										record.setDescripFlagAceite(rs.getString(11));
										record.setIdSolido(rs.getInt(12));
										record.setDescripFlagSolido(rs.getString(13));
										record.setIdHidrobiologico(rs.getInt(14));
										record.setDescripFlagHidrobiologico(rs.getString(15));
										record.setIdMicrobiologico(rs.getInt(16));
										record.setDescripFlagMicrobiologico(rs.getString(17));
										record.setIdParasitologico(rs.getInt(18));
										record.setDescripFlagParasitologico(rs.getString(19));
										record.setDescripcionEstadoAprob(rs.getString(20));
										record.setStRegi(rs.getString(21));
										record.setEstado(rs.getString(22));
										record.setFechaHoraCreacionString(rs.getString(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector != null  && !Strings.isEmpty(idPtarxSector) && !idPtarxSector.trim().equals("") && !idPtarxSector.equals("") && !Strings.isEmpty(idPtarxSector) ? Integer.parseInt(idPtarxSector.trim()) : null );	    
        params.addValue(ConstantesSptar.PAR_N_FE_DESDE, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		params.addValue(ConstantesSptar.PAR_N_FLG_APROBACION, idEstado != null  && !Strings.isEmpty(idEstado) && !idEstado.trim().equals("") && !idEstado.equals("") && !Strings.isEmpty(idEstado) ? Integer.parseInt(idEstado.trim()) : null );
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLaboratorioBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioTipo(String idPtarxSector, String idEstado, String fechaInicio, String fechaFin, Integer tipoFecha) throws GmdException, ParseException{
		List<RegistroLaboratorioBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB_TIPO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FLAG_TIPO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLaboratorioBean>() {
									@Override
									public RegistroLaboratorioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLaboratorioBean record = new RegistroLaboratorioBean();
										record.setIdRegistroLaboratorio(rs.getInt(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdPtarxSector(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdDirecto(rs.getInt(6));
										record.setDescripFlagDirecto(rs.getString(7));
										record.setIdDBO(rs.getInt(8));
										record.setDescripFlagDBO(rs.getString(9));
										record.setIdAceite(rs.getInt(10));
										record.setDescripFlagAceite(rs.getString(11));
										record.setIdSolido(rs.getInt(12));
										record.setDescripFlagSolido(rs.getString(13));
										record.setIdHidrobiologico(rs.getInt(14));
										record.setDescripFlagHidrobiologico(rs.getString(15));
										record.setIdMicrobiologico(rs.getInt(16));
										record.setDescripFlagMicrobiologico(rs.getString(17));
										record.setIdParasitologico(rs.getInt(18));
										record.setDescripFlagParasitologico(rs.getString(19));
										record.setDescripcionEstadoAprob(rs.getString(20));
										record.setStRegi(rs.getString(21));
										record.setEstado(rs.getString(22));
										record.setFechaHoraCreacionString(rs.getString(23));
										record.setFechaMonitoreoString(rs.getString(24));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector != null  && !Strings.isEmpty(idPtarxSector) && !idPtarxSector.trim().equals("") && !idPtarxSector.equals("") && !Strings.isEmpty(idPtarxSector) ? Integer.parseInt(idPtarxSector.trim()) : null );	    
        params.addValue(ConstantesSptar.PAR_N_FE_DESDE, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		params.addValue(ConstantesSptar.PAR_N_FLG_APROBACION, idEstado != null  && !Strings.isEmpty(idEstado) && !idEstado.trim().equals("") && !idEstado.equals("") && !Strings.isEmpty(idEstado) ? Integer.parseInt(idEstado.trim()) : null );
		params.addValue(ConstantesSptar.PAR_N_FLAG_TIPO, tipoFecha);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLaboratorioBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLaboratorioBean> obtenerLtaRegistroLaboratorioEmpty(String idPtarxSector, String fechaInicio, String fechaFin, String idEstado) throws GmdException, ParseException{
		List<RegistroLaboratorioBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLaboratorioBean>() {
									@Override
									public RegistroLaboratorioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLaboratorioBean record = new RegistroLaboratorioBean();
										record.setIdRegistroLaboratorio(rs.getInt(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdPtarxSector(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdDirecto(rs.getInt(6));
										record.setDescripFlagDirecto(rs.getString(7));
										record.setIdDBO(rs.getInt(8));
										record.setDescripFlagDBO(rs.getString(9));
										record.setIdAceite(rs.getInt(10));
										record.setDescripFlagAceite(rs.getString(11));
										record.setIdSolido(rs.getInt(12));
										record.setDescripFlagSolido(rs.getString(13));
										record.setIdHidrobiologico(rs.getInt(14));
										record.setDescripFlagHidrobiologico(rs.getString(15));
										record.setIdMicrobiologico(rs.getInt(16));
										record.setDescripFlagMicrobiologico(rs.getString(17));
										record.setIdParasitologico(rs.getInt(18));
										record.setDescripFlagParasitologico(rs.getString(19));
										record.setDescripcionEstadoAprob(rs.getString(20));
										record.setStRegi(rs.getString(21));
										record.setEstado(rs.getString(22));
										record.setFechaHoraCreacionString(rs.getString(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		
		if(StringUtils.isEmpty(idPtarxSector)){
			idPtarxSector = "";
			params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, null);
		}else {
			Integer idPtarxSectorConvert = Integer.valueOf(idPtarxSector);
			params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSectorConvert);
		}
		if(StringUtils.isEmpty(fechaInicio)){
			fechaInicio = "";
			params.addValue(ConstantesSptar.PAR_N_FE_DESDE, null);
		}else {
			params.addValue(ConstantesSptar.PAR_N_FE_DESDE, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		}
		if(StringUtils.isEmpty(fechaFin)){
			fechaFin = "";
			params.addValue(ConstantesSptar.PAR_N_FE_HASTA, null);
		}else {
			params.addValue(ConstantesSptar.PAR_N_FE_HASTA, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		}
		if(StringUtils.isEmpty(idEstado)){
			idEstado = "";
			params.addValue(ConstantesSptar.PAR_N_FLG_APROBACION, null);
		}else {
			Integer idEstadoConvert = Integer.valueOf(idEstado);
			params.addValue(ConstantesSptar.PAR_N_FLG_APROBACION, idEstadoConvert);
		}
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLaboratorioBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroLaboratorioBean registrarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FEC_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_DIRECTO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_DBO5, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_ACEITES, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_SOLIDOS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_HIDROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_MICROBIOLOGICO, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_PARASITOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			/*observaciones por parametros*/
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_DIRECTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_DBO5, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_ACEITES, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_SOLIDOS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_HIDROBIOLOGICO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_MICROBIOLOGICO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_PARASITOLOGICO, OracleTypes.VARCHAR));
			/**/
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_REG_LABORATORIO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_REG_LAB, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registro.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_D_FEC_REGISTRO, registro.getFechaRegistro());
			inputs.put(ConstantesSptar.PAR_N_FLG_APROBACION, registro.getFlagAprobacion());
			inputs.put(ConstantesSptar.PAR_N_FLG_DIRECTO, registro.getFlagDirecto());
			inputs.put(ConstantesSptar.PAR_N_FLG_DBO5, registro.getFlagDBO());
			inputs.put(ConstantesSptar.PAR_N_FLG_ACEITES, registro.getFlagAceite());
			inputs.put(ConstantesSptar.PAR_N_FLG_SOLIDOS, registro.getFlagSolido());
			inputs.put(ConstantesSptar.PAR_N_FLG_HIDROBIOLOGICO, registro.getFlagHidrobiologico());
			inputs.put(ConstantesSptar.PAR_N_FLG_MICROBIOLOGICO, registro.getFlagMicrobiologico());
			inputs.put(ConstantesSptar.PAR_N_FLG_PARASITOLOGICO, registro.getFlagParasitologico());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registro.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registro.getDeTermCrea());
			/*observaciones por parametros*/
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_DIRECTO, registro.getObservacionDirecto());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_DBO5, registro.getObservacionDBO());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_ACEITES, registro.getObservacionAceites());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_SOLIDOS, registro.getObservacionSolidos());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_HIDROBIOLOGICO, registro.getObservacionHidrobiologico());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_MICROBIOLOGICO, registro.getObservacionMicrobiologico());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_PARASITOLOGICO, registro.getObservacionParasitologico());
			/**/
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRegistroLaboratorio = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_REGISTRO));			
			registro.setIdRegistroLaboratorio(idRegistroLaboratorio);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registro;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarRegistroLaboratorio(RegistroLaboratorioBean registro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FEC_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_DIRECTO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_DBO5, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_ACEITES, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_SOLIDOS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_HIDROBIOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_MICROBIOLOGICO, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_PARASITOLOGICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));
			/*observaciones por parametros*/
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_DIRECTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_DBO5, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_ACEITES, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_SOLIDOS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_HIDROBIOLOGICO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_MICROBIOLOGICO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBS_PARASITOLOGICO, OracleTypes.VARCHAR));
			/**/
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_REG_LABORATORIO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_REG_LAB, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registro.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registro.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_D_FEC_REGISTRO, registro.getFechaRegistro());
			inputs.put(ConstantesSptar.PAR_N_FLG_APROBACION, registro.getFlagAprobacion());
			inputs.put(ConstantesSptar.PAR_N_FLG_DIRECTO, registro.getFlagDirecto());
			inputs.put(ConstantesSptar.PAR_N_FLG_DBO5, registro.getFlagDBO());
			inputs.put(ConstantesSptar.PAR_N_FLG_ACEITES, registro.getFlagAceite());
			inputs.put(ConstantesSptar.PAR_N_FLG_SOLIDOS, registro.getFlagSolido());
			inputs.put(ConstantesSptar.PAR_N_FLG_HIDROBIOLOGICO, registro.getFlagHidrobiologico());
			inputs.put(ConstantesSptar.PAR_N_FLG_MICROBIOLOGICO, registro.getFlagMicrobiologico());
			inputs.put(ConstantesSptar.PAR_N_FLG_PARASITOLOGICO, registro.getFlagParasitologico());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registro.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registro.getDeTermModi());
			/*observaciones por parametros*/
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_DIRECTO, registro.getObservacionDirecto());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_DBO5, registro.getObservacionDBO());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_ACEITES, registro.getObservacionAceites());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_SOLIDOS, registro.getObservacionSolidos());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_HIDROBIOLOGICO, registro.getObservacionHidrobiologico());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_MICROBIOLOGICO, registro.getObservacionMicrobiologico());
			inputs.put(ConstantesSptar.PAR_V_DES_OBS_PARASITOLOGICO, registro.getObservacionParasitologico());
			/**/
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RegistroLaboratorioBean obtenerRegistroLaboratorio(Integer idRegistroLaboratorio) throws GmdException {		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_BUSCAR_REG_LAB)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLaboratorioBean>() {
									@Override
									public RegistroLaboratorioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLaboratorioBean record = new RegistroLaboratorioBean();
										record.setIdRegistroLaboratorio(rs.getInt(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdPtarxSector(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setFlagDirecto(rs.getInt(6));
										record.setFlagDBO(rs.getInt(7));
										record.setFlagAceite(rs.getInt(8));
										record.setFlagSolido(rs.getInt(9));
										record.setFlagHidrobiologico(rs.getInt(10));
										record.setFlagMicrobiologico(rs.getInt(11));
										record.setFlagParasitologico(rs.getInt(12));
										record.setDescripcionEstadoAprob(rs.getString(13));
										record.setStRegi(rs.getString(14));
										record.setEstado(rs.getString(15));
										record.setFlagAprobacion(rs.getInt(17));
										record.setObservacionDirecto(rs.getString(18));
										record.setObservacionDBO(rs.getString(19));
										record.setObservacionAceites(rs.getString(20));
										record.setObservacionSolidos(rs.getString(21));
										record.setObservacionHidrobiologico(rs.getString(22));
										record.setObservacionMicrobiologico(rs.getString(23));
										record.setObservacionParasitologico(rs.getString(24));
										record.setFechaMonitoreo(rs.getDate(25));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);	    
		Map<String, Object> results = caller.execute(params);		
		List<RegistroLaboratorioBean> lstRetorno = (List<RegistroLaboratorioBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		if(lstRetorno != null && lstRetorno.size() > 0) {
			return lstRetorno.get(0);
		}else {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void cambiarFlagAprobacion(RegistroLaboratorioBean registroLaboratorio, Integer idCambioEstado) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLG_APROBACION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_REG_LABORATORIO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_APRUEBA_REG_LAB, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, registroLaboratorio.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_FLG_APROBACION, idCambioEstado);
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registroLaboratorio.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, registroLaboratorio.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registroLaboratorio.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void asignarFechaDBO(Integer idRegistroLaboratorio) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_RLAB_DBO5+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_FECDBO_REGLAB, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);						
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
}
