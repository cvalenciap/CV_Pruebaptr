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
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IPuntoMuestraPtarSectorDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class PuntoMuestraPtarSectorDaoImpl implements IPuntoMuestraPtarSectorDao {

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
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignados(Integer idPtarPorSector,Integer idParametro,Integer idSubParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdPuntoMuestra(rs.getInt(2));
										record.setDescripcionPunto(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);	
		params.addValue(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, idSubParametro);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarNoAsignados(Integer idPtarPorSector,Integer idParametro,Integer idSubParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRA_NO_PTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdPuntoMuestra(rs.getInt(1));
										record.setDescripcionPunto(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);	
		params.addValue(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, idSubParametro);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAllPuntoMuestraPtar(Integer idPtarPorSector) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PTOMUESTRAXPTAR_T, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarPuntoMuestraPtar(Integer idPtarPorSector,PuntoMuestraPtarSectorBean item,BaseSptarBean auditoria)
			throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer contadorEjecucion = 1;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_PTOMUESTRAXPTAR, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
				inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, item.getIdParametro());
				inputs.put(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, item.getIdSubParametro());
				inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, item.getIdPuntoMuestra());
				inputs.put(ConstantesSptar.PAR_N_SECUENCIA, item.getSecuencia());
				inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
				inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
				inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());					
				this.execSp.executeSp(inputs);		
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
		return contadorEjecucion ;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularPuntoMuestraPtar(PuntoMuestraPtarSectorBean bean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PTOMUESTRAXPTAR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, bean.getIdptarPorSector());	
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, bean.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, bean.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, bean.getIdPuntoMuestra());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> validaPuntoMuestraPtar(PuntoMuestraPtarSectorBean punto)
			throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_VALIDA_PTOMXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(2));
										record.setIdPuntoMuestra(rs.getInt(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, punto.getIdPuntoMuestra());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> buscarPuntoMuestraPtar(PuntoMuestraPtarSectorBean punto)
			throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_BUSCAR_PTOMUESTRAXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setIdPuntoMuestra(rs.getInt(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdptarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, punto.getIdSubParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, punto.getIdPuntoMuestra());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraPtarAsignadosMicrobiologico(int idPtarPorSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdPuntoMuestra(rs.getInt(2));
										record.setDescripcionPunto(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, ConstantesUtil.ID_PARAMETRO_MICROBIOLOGICO);
		params.addValue(ConstantesSptar.PAR_N_ID_SUBPARAMETRO, null);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxGrupoParametro(Integer PtarxSector,
			Integer idParametro) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTAR_GRP)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdPuntoMuestra(rs.getInt(2));
										record.setDescripcionPunto(rs.getString(3));
										record.setIndicadorSemilla(rs.getString(5).trim());
										record.setDescripcionCorta(rs.getString(6));
										record.setIdSubParametro(rs.getInt(8));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, PtarxSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraxPtar(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRAXPTAR).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRAXPTAR_GRP2)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdptarPorSector(rs.getInt(1));
										record.setIdPuntoMuestra(rs.getInt(2));
										record.setDescripcionPunto(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, PtarxSector);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecuencial(Integer idPtarPorSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRA_PTARXRPT).withProcedureName(ConstantesSptar.PRC_LIST_PTO_PTARXRPT)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdPuntoMuestra(rs.getInt(1));
										record.setDescripcionPunto(rs.getString(2));
										record.setSecuencia(rs.getInt(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
		
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularPuntoMuestraPtarSecuencial(Integer idPtarxSector) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTOMUESTRA_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PTO_PTARXRPT_T, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void registrarPuntoMuestraPtarSecuencial(PuntoMuestraPtarSectorBean item, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_SECUENCIA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTOMUESTRA_PTARXRPT+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_PTOM_PTARXRPT, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, item.getIdptarPorSector());
				inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, item.getIdPuntoMuestra());
				inputs.put(ConstantesSptar.PAR_N_SECUENCIA, item.getSecuencia());
				inputs.put(ConstantesSptar.PAR_V_ST_REGI, auditoria.getStRegi());
				inputs.put(ConstantesSptar.PAR_A_V_USUCRE, auditoria.getIdUsuaCrea());
				inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, auditoria.getDeTermCrea());					
				this.execSp.executeSp(inputs);		
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraSecEnlace(Integer idPtarPorSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTOMUESTRA_PTARXRPT).withProcedureName(ConstantesSptar.PRC_LIST_PTORPT_ENL)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdPuntoMuestra(rs.getInt(1));
										record.setDescripcionPunto(rs.getString(2));
										record.setSecuencia(rs.getInt(3));
										record.setPtoEnlace(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PuntoMuestraPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
