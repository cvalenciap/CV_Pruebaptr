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
import pe.com.sedapal.scr.core.beans.PtarxSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IPtarxSectorDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class PtarxSectorDaoImpl implements IPtarxSectorDao {

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
	public List<PtarxSectorBean> obtenerPtarxSector() throws GmdException {
		List<PtarxSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTARXSECTOR).withProcedureName(ConstantesSptar.PRC_LIST_PTARXSECTOR)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PtarxSectorBean>() {
									@Override
									public PtarxSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PtarxSectorBean record = new PtarxSectorBean();
										record.setIdPtarxsector(rs.getInt(1));
										record.setIdPtar(rs.getInt(2));
										record.setDescripcionPtar(rs.getString(3));
										record.setIdSector(rs.getInt(4));
										record.setDescripcionSector(rs.getString(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										record.setDescripcionPtarxSector(rs.getString(8));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PtarxSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTARXSECTOR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PTARXSECTOR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, ptarxSectorBean.getIdPtarxsector());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, ptarxSectorBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, ptarxSectorBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}		
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTAR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DE_PTARXSECTOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTARXSECTOR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_PTARXSECTOR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, ptarxSectorBean.getIdPtarxsector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTAR, ptarxSectorBean.getIdPtar());
			inputs.put(ConstantesSptar.PAR_N_ID_SECTOR, ptarxSectorBean.getIdSector());
			inputs.put(ConstantesSptar.PAR_V_DE_PTARXSECTOR, ptarxSectorBean.getDescripcionPtarxSector());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, ptarxSectorBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUMOD, ptarxSectorBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, ptarxSectorBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public PtarxSectorBean registrarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTAR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DE_PTARXSECTOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTARXSECTOR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_PTARXSECTOR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTAR, ptarxSectorBean.getIdPtar());
			inputs.put(ConstantesSptar.PAR_N_ID_SECTOR, ptarxSectorBean.getIdSector());
			inputs.put(ConstantesSptar.PAR_V_DE_PTARXSECTOR, ptarxSectorBean.getDescripcionPtarxSector());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI,  ptarxSectorBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE,  ptarxSectorBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA,  ptarxSectorBean.getDeTermCrea());
			
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idRetorno = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_PTARXSECTOR));			
			ptarxSectorBean.setIdPtarxsector(idRetorno);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return ptarxSectorBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer validaPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer indicador = 0;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTAR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SECTOR, OracleTypes.DECIMAL));	
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_V_CTA, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PTARXSECTOR+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_VALIDA_PTARXSECTOR, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTAR, ptarxSectorBean.getIdPtar());
			inputs.put(ConstantesSptar.PAR_N_ID_SECTOR, ptarxSectorBean.getIdSector());
			
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			indicador = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_V_CTA));			
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return indicador;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PtarxSectorBean> obtenerNoPtarxSector() throws GmdException {
		List<PtarxSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTARXSECTOR).withProcedureName(ConstantesSptar.PRC_LIST_NO_PTARXSECTOR)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PtarxSectorBean>() {
									@Override
									public PtarxSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PtarxSectorBean record = new PtarxSectorBean();
										record.setIdDetalleGeneral(rs.getInt(1));
										record.setDescripcion(rs.getString(2));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PtarxSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PtarxSectorBean> obtenerPtarxSectorTodo() throws GmdException {
		List<PtarxSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PTARXSECTOR).withProcedureName(ConstantesSptar.PRC_LIST_PTARXSECTOR_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PtarxSectorBean>() {
									@Override
									public PtarxSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PtarxSectorBean record = new PtarxSectorBean();
										record.setIdPtarxsector(rs.getInt(1));
										record.setIdPtar(rs.getInt(2));
										record.setDescripcionPtar(rs.getString(3));
										record.setIdSector(rs.getInt(4));
										record.setDescripcionSector(rs.getString(5));
										record.setStRegi(rs.getString(6));
										record.setEstado(rs.getString(7));
										record.setDescripcionPtarxSector(rs.getString(8));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PtarxSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
