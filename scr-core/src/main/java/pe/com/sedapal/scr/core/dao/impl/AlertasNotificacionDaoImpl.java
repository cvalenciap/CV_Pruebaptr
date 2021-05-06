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
import pe.com.sedapal.scr.core.beans.AlertasNotificacionBean;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.NotificacionAnalistaBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IAlertasNotificacionDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class AlertasNotificacionDaoImpl implements IAlertasNotificacionDao {

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
	public List<AlertasNotificacionBean> obtenerLtaAlertasNotificacionTodo() throws GmdException {
		List<AlertasNotificacionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_NOTIFICA).withProcedureName(ConstantesSptar.PRC_LIST_NOTIFICA_M)
				.declareParameters(
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AlertasNotificacionBean>() {
									@Override
									public AlertasNotificacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AlertasNotificacionBean record = new AlertasNotificacionBean();
										record.setIdAlertasNotificacion(rs.getInt(1));
										record.setDescripcionNotificacion(rs.getString(2));
										record.setStRegi(rs.getString(3));
										record.setDescripcionEstado(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AlertasNotificacionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AlertasNotificacionBean> obtenerLtaAlertasNotificacion(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<AlertasNotificacionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_N_TIPO_SOLI, idTipoSolido);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AlertasNotificacionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public AlertasNotificacionBean registrarAlertasNotificacion(AlertasNotificacionBean AlertasNotificacion) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SOLI_SUS));			
			AlertasNotificacion.setIdAlertasNotificacion(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return AlertasNotificacion;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NotificacionAnalistaBean> obtenerLtaAnalistasVinculados(Integer idAlertasNotificacion) throws GmdException {
		List<NotificacionAnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_NOTIFICA).withProcedureName(ConstantesSptar.PRC_LIST_NOTI_ANALISTA)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_NOTIFICACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<NotificacionAnalistaBean>() {
									@Override
									public NotificacionAnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										NotificacionAnalistaBean record = new NotificacionAnalistaBean();
										record.setIdNotificacionAnalista(rs.getInt(1));
										record.setIdNotificacion(rs.getInt(2));
										record.setIdAnalista(rs.getInt(3));
										record.setNombreCompleto(rs.getString(4));
										record.setStRegi(rs.getString(5));
										record.setEstado(rs.getString(6));
										record.setCorreo(rs.getString(7));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_NOTIFICACION, idAlertasNotificacion);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<NotificacionAnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AnalistaBean> obtenerLtaAnalistasNoVinculados(Integer idAlertasNotificacion) throws GmdException {
		List<AnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_NOTIFICA).withProcedureName(ConstantesSptar.PRC_LIST_NOTI_NO_ANALI)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_NOTIFICACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalistaBean>() {
									@Override
									public AnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalistaBean record = new AnalistaBean();
										record.setIdAnalista(rs.getInt(1));
										record.setNombre(rs.getString(2));
										record.setApellido(rs.getString(3));
										record.setNombreCompleto(rs.getString(4));
										record.setCorreo(rs.getString(5));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_NOTIFICACION, idAlertasNotificacion);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void agregarRegistroAnalista(Integer idAlertasNotificacion, Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_NOTIFICACION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_NOTI_ANALISTA, OracleTypes.DECIMAL));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_NOTIFICA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_NOTI_ANALISTA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_NOTIFICACION, idAlertasNotificacion);
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_NOTI_ANALISTA));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularRegistroAnalista(Integer idAnalista, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_NOTI_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_NOTIFICA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_NOTI_ANALISTA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_NOTI_ANALISTA, idAnalista);
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, auditoria.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, auditoria.getDeTermModi());
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAlertasNotificacion(Integer idAlertasNotificacion, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_NOTIFICACION, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_NOTIFICA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_NOTIFICA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_NOTIFICACION, idAlertasNotificacion);
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, auditoria.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, auditoria.getDeTermModi());
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AlertasNotificacionBean buscarAlertasNotificacionHTML(Integer idAlertasNotificacion) throws GmdException {
		List<AlertasNotificacionBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_NOTIFICA).withProcedureName(ConstantesSptar.PRC_BUSCAR_NOTIFICA)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_NOTIFICACION, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AlertasNotificacionBean>() {
									@Override
									public AlertasNotificacionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AlertasNotificacionBean record = new AlertasNotificacionBean();
										record.setIdAlertasNotificacion(rs.getInt(1));
										record.setDescripcionNotificacion(rs.getString(2));
										record.setStRegi(rs.getString(3));
										record.setDescripcionEstado(rs.getString(4));
										record.setHtmlMensaje(rs.getString(5));
										record.setHtmlMensajeLong(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_NOTIFICACION, idAlertasNotificacion);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AlertasNotificacionBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		AlertasNotificacionBean AlertaRetorno = lstRetorno.get(0);
		return AlertaRetorno;
	}
}
