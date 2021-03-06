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
import pe.com.sedapal.scr.core.beans.RegistroLabReporteBean;
import pe.com.sedapal.scr.core.beans.RegistroLaboratorioBean;
import pe.com.sedapal.scr.core.beans.TreeReporteBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroLabReporteDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;
import pe.com.sedapal.scr.core.util.Utils;

@Repository
public class RegistroLabReporteDaoImpl implements IRegistroLabReporteDao {

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
	public List<RegistroLabReporteBean> obtenerListaRegistroReporte(Integer idPtarxSector, Integer idRegistroLaboratorio, Date fechaInicio, Date fechaFin, String idParametro) throws GmdException{
		List<RegistroLabReporteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB_1)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_V_TIPO_PARAMETRO, Types.VARCHAR),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										
										record.setDescripcionTipoRegistro(rs.getString(1));
										record.setIdSubParametro(rs.getInt(2));
										record.setDescripcionSubParametro(rs.getString(3));
										record.setIdPtoMuestra(rs.getInt(4));
										record.setDescripcionPtoMuestra(rs.getString(5));
										record.setNumValor(rs.getDouble(6));
										record.setSecuencia(rs.getInt(7));
										record.setSecuenciaSubParam(rs.getInt(8));
										record.setTipoSemilla(rs.getString(11));
										record.setDescripcionNumValor(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegistroLaboratorio);
		params.addValue(ConstantesSptar.PAR_V_TIPO_PARAMETRO, idParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerListaRegistroReporteHistorico(Integer idPtarxSector, Date fechaInicio, Date fechaFin) throws GmdException{
		List<RegistroLabReporteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB_2)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										record.setDescripcionTipoRegistro(rs.getString(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubParametro(rs.getString(4));
										record.setIdPtoMuestra(rs.getInt(5));
										record.setDescripcionPtoMuestra(rs.getString(6));
										record.setNumValor(rs.getDouble(7));
										record.setTipoSemilla(rs.getString(8));
										record.setDescripcionNumValor(rs.getString(9));
										record.setIdRegistroLaboratorio(rs.getInt(10));
										record.setSecuenciaSubParam(rs.getInt(11));
										record.setSecuencia(rs.getInt(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerListaRegistroReporteAcumulado(Date fechaInicio, Date fechaFin) throws GmdException{
		List<RegistroLabReporteBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_REG_LAB_3)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										
										record.setIdPtarxSector(rs.getInt(1));
										record.setDescripcionPtarxSector(rs.getString(2));
										record.setDescripcionTipoRegistro(rs.getString(3));
										record.setIdSubParametro(rs.getInt(4));
										record.setDescripcionSubParametro(rs.getString(5));
										record.setIdPtoMuestra(rs.getInt(6));
										record.setDescripcionPtoMuestra(rs.getString(7));
										record.setNumValor(rs.getDouble(8));
										record.setTipoSemilla(rs.getString(9));
										record.setDescripcionNumValor(rs.getString(10));
										record.setSecuenciaSubParam(rs.getInt(11));
										record.setSecuencia(rs.getInt(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerListaDashboard(Date fechaInicio, Date fechaFin, Integer idPtar) throws GmdException{
		List<RegistroLabReporteBean> ltaRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_DASHBOARD)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int RowNum) throws SQLException{
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										
										record.setIdPtarxSector(rs.getInt(1));
										record.setDescripcionPtarxSector(rs.getString(2));
										record.setDescripcionTipoRegistro(rs.getString(3));
										record.setIdSubParametro(rs.getInt(4));
										record.setDescripcionSubParametro(rs.getString(5));
										record.setIdPtoMuestra(rs.getInt(6));
										record.setDescripcionPtoMuestra(rs.getString(7));
										record.setNumValor(rs.getDouble(8));
										record.setTipoSemilla(rs.getString(9));
										record.setIdEPSP(rs.getString(10));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtar);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		Map<String, Object> results = caller.execute(params);
		ltaRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return ltaRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerListaLineDashboard(Date fechaInicio, Date fechaFin, Integer idPtar) throws GmdException{
		List<RegistroLabReporteBean> ltaRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_LINE_DASHBOARD)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int RowNum) throws SQLException{
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										record.setFechaRegistroTimestamp(rs.getTimestamp(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubParametro(rs.getString(4));
										record.setIdPtoMuestra(rs.getInt(5));
										record.setDescripcionPtoMuestra(rs.getString(6));
										record.setNumValor(rs.getDouble(7));
										record.setTipoSemilla(rs.getString(8));
										record.setIdEPSP(rs.getString(9));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtar);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		Map<String, Object> results = caller.execute(params);
		ltaRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return ltaRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroLabReporteBean> obtenerListaLineNewAlterDashboard(Date fechaInicio, Date fechaFin, Integer idPtarSector) throws GmdException{
		List<RegistroLabReporteBean> ltaRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_DASHXPARAMETRO)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_FE_DESDE, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_N_FE_HASTA, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
									public RegistroLabReporteBean mapRow(ResultSet rs, int RowNum) throws SQLException{
										RegistroLabReporteBean record = new RegistroLabReporteBean();
										record.setIdSubParametro(rs.getInt(1));
										record.setDescripcionSubParametro(rs.getString(2));
										record.setIdPtoMuestra(rs.getInt(3));
										record.setDescripcionPtoMuestra(rs.getString(4));
										record.setNumValor(rs.getDouble(5));
										record.setSecuencia(rs.getInt(6));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarSector);
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, null);
		params.addValue(ConstantesSptar.PAR_N_FE_DESDE, fechaInicio);
		params.addValue(ConstantesSptar.PAR_N_FE_HASTA, fechaFin);
		Map<String, Object> results = caller.execute(params);
		ltaRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return ltaRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
//	inicio ordenamiento reporte
//	public List<TreeReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException{
//		List<TreeReporteBean> ltaRetorno = null;
	public List<RegistroLabReporteBean> obtenerListaTree(Integer idPtarxSector) throws GmdException{
		List<RegistroLabReporteBean> ltaRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
//		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_ARBOLXPTAR)
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_RPT_LABORATORIO).withProcedureName(ConstantesSptar.PRC_ARBOLXPTAR_RPT)
				.declareParameters(
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
//								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TreeReporteBean>() {
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroLabReporteBean>() {
									@Override
//									public TreeReporteBean mapRow(ResultSet rs, int RowNum) throws SQLException{
									public RegistroLabReporteBean mapRow(ResultSet rs, int RowNum) throws SQLException{
//										TreeReporteBean record = new TreeReporteBean();
										RegistroLabReporteBean record = new RegistroLabReporteBean();
//										record.setIdPtarxSector(rs.getInt(1));
//										record.setIdParametro(rs.getInt(2));
//										record.setDescripcionParametro(rs.getString(3));
//										record.setIdSubParametro(rs.getInt(4));
//										record.setDescripcionSubParametro(rs.getString(5));
//										record.setSecuencia(rs.getInt(6));
//										record.setIdPtoMuestra(rs.getInt(7));
//										record.setDescripcionPtoMuestra(rs.getString(8));
										record.setDescripcionTipoRegistro(rs.getString(1));
										record.setFechaRegistro(rs.getDate(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubParametro(rs.getString(4));
										record.setIdPtoMuestra(rs.getInt(5));
										record.setDescripcionPtoMuestra(rs.getString(6));
										record.setNumValor(rs.getDouble(7));
										record.setTipoSemilla(rs.getString(8));
										record.setDescripcionNumValor(rs.getString(9));
										record.setIdRegistroLaboratorio(rs.getInt(10));
										record.setSecuenciaSubParam(rs.getInt(11));
										record.setSecuencia(rs.getInt(12));
										
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		Map<String, Object> results = caller.execute(params);
//		ltaRetorno = (List<TreeReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		ltaRetorno = (List<RegistroLabReporteBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return ltaRetorno;
		
	}
}
