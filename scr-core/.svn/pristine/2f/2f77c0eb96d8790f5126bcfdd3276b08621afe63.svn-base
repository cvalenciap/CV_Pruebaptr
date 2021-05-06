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
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IDetalleGeneralDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class DetalleGeneralDaoImpl implements IDetalleGeneralDao {

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
	public List<DetalleGeneralBean> obtenerDetalleGeneral(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_DET_GENERALES)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										record.setVlDato06(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_GENERAL, idGeneral);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralI(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_DET_GENERALES_I)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_GENERAL, idGeneral);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DET_GENERAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_GENERALES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_DET_GENERALES, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_DET_GENERAL, detalleGeneralBean.getIdDetalleGeneral());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, detalleGeneralBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, detalleGeneralBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_DET_GENERAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_DET_GENERAL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_DET_CORTA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_1, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_2, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_3, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_4, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_5, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_6, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_GENERALES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALI_DET_GENERALES, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_DET_GENERAL, detalleGeneralBean.getIdDetalleGeneral());
			inputs.put(ConstantesSptar.PAR_N_ID_GENERAL, detalleGeneralBean.getIdGeneral());
			inputs.put(ConstantesSptar.PAR_V_DES_DET_GENERAL, detalleGeneralBean.getDescripcion());
			inputs.put(ConstantesSptar.PAR_V_DES_DET_CORTA, detalleGeneralBean.getDescripcionCorta());
			inputs.put(ConstantesSptar.PAR_V_VALOR_1, detalleGeneralBean.getVlDato01());
			inputs.put(ConstantesSptar.PAR_V_VALOR_2, detalleGeneralBean.getVlDato02());
			inputs.put(ConstantesSptar.PAR_V_VALOR_3, detalleGeneralBean.getVlDato03());
			inputs.put(ConstantesSptar.PAR_V_VALOR_4, detalleGeneralBean.getVlDato04());
			inputs.put(ConstantesSptar.PAR_V_VALOR_5, detalleGeneralBean.getVlDato05());
			inputs.put(ConstantesSptar.PAR_V_VALOR_6, detalleGeneralBean.getVlDato06());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, detalleGeneralBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, detalleGeneralBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, detalleGeneralBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public DetalleGeneralBean registrarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_DET_GENERAL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_DET_CORTA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_1, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_2, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_3, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_4, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_5, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_VALOR_6, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_DET_GENERAL, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_GENERALES+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGIST_DET_GENERALES, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_GENERAL, detalleGeneralBean.getIdGeneral());
			inputs.put(ConstantesSptar.PAR_V_DES_DET_GENERAL, detalleGeneralBean.getDescripcion());
			inputs.put(ConstantesSptar.PAR_V_DES_DET_CORTA, detalleGeneralBean.getDescripcionCorta());
			inputs.put(ConstantesSptar.PAR_V_VALOR_1, detalleGeneralBean.getVlDato01());
			inputs.put(ConstantesSptar.PAR_V_VALOR_2, detalleGeneralBean.getVlDato02());
			inputs.put(ConstantesSptar.PAR_V_VALOR_3, detalleGeneralBean.getVlDato03());
			inputs.put(ConstantesSptar.PAR_V_VALOR_4, detalleGeneralBean.getVlDato04());
			inputs.put(ConstantesSptar.PAR_V_VALOR_5, detalleGeneralBean.getVlDato05());
			inputs.put(ConstantesSptar.PAR_V_VALOR_6, detalleGeneralBean.getVlDato06());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, detalleGeneralBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, detalleGeneralBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, detalleGeneralBean.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idDetalleGeneralGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_DET_GENERAL));			
			detalleGeneralBean.setIdDetalleGeneral(idDetalleGeneralGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return detalleGeneralBean;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralTodo(int idGeneral) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_DET_GENERALES_M)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										record.setVlDato06(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_GENERAL, idGeneral);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralMicroorganismo(int idGeneral, int parametroMicro) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_DET_GEN_PARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_V_VALOR_1, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_GENERAL, idGeneral);
		params.addValue(ConstantesSptar.PAR_V_VALOR_1, parametroMicro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerDetalleGeneralbyidDetalle(Integer idDetalleGeneral) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_BUSCAR_DET_GENERALES)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_DET_GENERAL, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, Types.VARCHAR),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										record.setVlDato06(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_DET_GENERAL, idDetalleGeneral);		
		params.addValue(ConstantesSptar.PAR_V_ST_REGI, ConstantesUtil.ST_REGI_ACTIVO);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerListaPuntoMuestraES(int idPuntoMuestra) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_PTOMUESTRA_ES)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_GENERAL, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setIdDetalleGeneral(rs.getInt(2));
										record.setDescripcion(rs.getString(3));
										record.setDescripcionCorta(rs.getString(4));
										record.setVlDato01(rs.getString(5));
										record.setVlDato02(rs.getString(6));
										record.setVlDato03(rs.getString(7));
										record.setVlDato04(rs.getString(8));
										record.setVlDato05(rs.getString(9));
										record.setStRegi(rs.getString(10));
										record.setEstado(rs.getString(11));
										record.setVlDato06(rs.getString(12));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_GENERAL, idPuntoMuestra);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerListaMicroHidro(Integer idSubParametro) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_LISTA_MICROXHIDRO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdDetalleGeneral(rs.getInt(1));
										record.setDescripcion(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										record.setVlDato01(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerListaMicroPara(Integer idSubParametro) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_LISTA_MICROXPARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setIdDetalleGeneral(rs.getInt(1));
										record.setDescripcion(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										record.setVlDato01(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
