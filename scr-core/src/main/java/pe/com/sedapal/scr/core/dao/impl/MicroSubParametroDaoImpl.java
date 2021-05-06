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
import pe.com.sedapal.scr.core.beans.MicroPorSubParametroBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IMicroSubParametroDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class MicroSubParametroDaoImpl implements IMicroSubParametroDao {

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
	public List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroAsignados(int idPtarPorSector, int idParametro,
			int idSubParametro) throws GmdException {
		List<MicroPorSubParametroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_LIST_MICROXSUBPARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MicroPorSubParametroBean>() {
									@Override
									public MicroPorSubParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MicroPorSubParametroBean record = new MicroPorSubParametroBean();
										record.setIdPtarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubParametro(rs.getString(3));
										record.setIdMicroOrganismo(rs.getInt(5));
										record.setDescripcionMicroOrganismo(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<MicroPorSubParametroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MicroPorSubParametroBean> obtenerltaMicroPorSubParametroNoAsignados(int idPtarPorSector,
			int idParametro, int idSubParametro) throws GmdException {
		List<MicroPorSubParametroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_LIST_MICRO_NO_PARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MicroPorSubParametroBean>() {
									@Override
									public MicroPorSubParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MicroPorSubParametroBean record = new MicroPorSubParametroBean();
										record.setIdMicroOrganismo(rs.getInt(1));
										record.setDescripcionMicroOrganismo(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
		params.addValue(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<MicroPorSubParametroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAllMicroPorSubParametro(int idPtarPorSector, int idParametro, int idSubParametro)
			throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_MICROXSUBPARA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_MICROXSUBPARA_T, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);	
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	public Integer registrarMicroPorSubParametro(int idPtarPorSector, int idParametro, int idSubParametro,
		MicroPorSubParametroBean item, BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer contadorEjecucion = 0;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_MICROXSUBPARA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_MICROXSUBPARA, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarPorSector);
				inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, idParametro);
				inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, idSubParametro);
				inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, item.getIdMicroOrganismo());
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
	public void anularMicroPorSubParametro(MicroPorSubParametroBean punto) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, OracleTypes.DECIMAL));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_MICROXSUBPARA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_MICROXSUBPARA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdPtarPorSector());	
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_SUB_PARAMETRO, punto.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, punto.getIdMicroOrganismo());
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MicroPorSubParametroBean> validaMicroPorSubParametro(MicroPorSubParametroBean punto)
			throws GmdException {
		List<MicroPorSubParametroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_VALIDA_MICROXSPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MicroPorSubParametroBean>() {
									@Override
									public MicroPorSubParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MicroPorSubParametroBean record = new MicroPorSubParametroBean();
										record.setIdMicroOrganismo(rs.getInt(1));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdPtarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, punto.getIdMicroOrganismo());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<MicroPorSubParametroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MicroPorSubParametroBean> buscarMicroPorSubParametro(MicroPorSubParametroBean punto)
			throws GmdException {
		List<MicroPorSubParametroBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_MICROXSUBPARA).withProcedureName(ConstantesSptar.PRC_BUSCAR_MICROXSUBPARA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MicroPorSubParametroBean>() {
									@Override
									public MicroPorSubParametroBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MicroPorSubParametroBean record = new MicroPorSubParametroBean();
										record.setIdPtarPorSector(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, punto.getIdPtarPorSector());		
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, punto.getIdParametro());
		params.addValue(ConstantesSptar.PAR_N_ID_MICROORGANISMOS, punto.getIdMicroOrganismo());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<MicroPorSubParametroBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
