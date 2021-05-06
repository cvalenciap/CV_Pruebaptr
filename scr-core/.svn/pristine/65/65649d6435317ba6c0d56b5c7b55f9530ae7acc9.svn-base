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
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.ISolidoSuspendidoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class SolidoSuspendidoDaoImpl implements ISolidoSuspendidoDao {

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
	public List<SolidoSuspendidoBean> obtenerLtaSolidoTodo(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<SolidoSuspendidoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SOLI_SUSP).withProcedureName(ConstantesSptar.PRC_LIST_SOLI_SUSP_M)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SolidoSuspendidoBean>() {
									@Override
									public SolidoSuspendidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SolidoSuspendidoBean record = new SolidoSuspendidoBean();
										record.setIdSolidoSuspendido(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setIdTipoSolido(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdPuntoMuestra(rs.getInt(6));
										record.setDescripcionPunto(rs.getString(7));
										record.setNuPesoInicial(rs.getDouble(8));
										record.setNuPesoFiltrado(rs.getDouble(9));
										record.setNuPesoFinal(rs.getDouble(10));
										record.setNuPesoCalcina(rs.getDouble(11));
										record.setnNumSS(rs.getDouble(12));
										record.setnNumSSF(rs.getDouble(13));
										record.setnNumSSV(rs.getDouble(14));
										record.setStRegi(rs.getString(15));
										record.setEstado(rs.getString(16));
										record.setDescripcionTipoSolido(rs.getString(17));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_N_TIPO_SOLI, idTipoSolido);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SolidoSuspendidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolidoSuspendidoBean> obtenerLtaSolidoSuspendido(Integer idPtarxSector, Integer idTipoSolido) throws GmdException {
		List<SolidoSuspendidoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_SOLI_SUSP).withProcedureName(ConstantesSptar.PRC_LIST_SOLI_SUSP)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SolidoSuspendidoBean>() {
									@Override
									public SolidoSuspendidoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										SolidoSuspendidoBean record = new SolidoSuspendidoBean();
										record.setIdSolidoSuspendido(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setIdTipoSolido(rs.getInt(3));
										record.setDescripcionPtar(rs.getString(4));
										record.setDescripcionSector(rs.getString(5));
										record.setIdPuntoMuestra(rs.getInt(6));
										record.setDescripcionPunto(rs.getString(7));
										record.setNuPesoInicial(rs.getDouble(8));
										record.setNuPesoFiltrado(rs.getDouble(9));
										record.setNuPesoFinal(rs.getDouble(10));
										record.setNuPesoCalcina(rs.getDouble(11));
										record.setnNumSS(rs.getDouble(12));
										record.setnNumSSF(rs.getDouble(13));
										record.setnNumSSV(rs.getDouble(14));
										record.setStRegi(rs.getString(15));
										record.setEstado(rs.getString(16));
										record.setDescripcionTipoSolido(rs.getString(17));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_N_TIPO_SOLI, idTipoSolido);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<SolidoSuspendidoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SOLI_SUS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SOLI_SUSP+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_SOLI_SUSP, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SOLI_SUS, solidoSuspendido.getIdSolidoSuspendido());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, solidoSuspendido.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, solidoSuspendido.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_SOLI_SUS, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FILTRADO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_CALCINA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SS, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSV, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SOLI_SUSP+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_SOLI_SUSP, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_SOLI_SUS, solidoSuspendido.getIdSolidoSuspendido());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, solidoSuspendido.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, solidoSuspendido.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, solidoSuspendido.getNuPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FILTRADO, solidoSuspendido.getNuPesoFiltrado());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, solidoSuspendido.getNuPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_CALCINA, solidoSuspendido.getNuPesoCalcina());
			inputs.put(ConstantesSptar.PAR_N_NUM_SS, solidoSuspendido.getnNumSS());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSF, solidoSuspendido.getnNumSSF());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSV, solidoSuspendido.getnNumSSV());
			inputs.put(ConstantesSptar.PAR_N_TIPO_SOLI, solidoSuspendido.getIdTipoSolido());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, solidoSuspendido.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, solidoSuspendido.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, solidoSuspendido.getDeTermModi());				
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public SolidoSuspendidoBean registrarSolidoSuspendido(SolidoSuspendidoBean solidoSuspendido) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FILTRADO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_FINAL, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_PESO_CALCINA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SS, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SSV, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_SOLI, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_SOLI_SUS, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_SOLI_SUSP+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_SOLI_SUSP, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, solidoSuspendido.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, solidoSuspendido.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_INICIAL, solidoSuspendido.getNuPesoInicial());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FILTRADO, solidoSuspendido.getNuPesoFiltrado());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_FINAL, solidoSuspendido.getNuPesoFinal());
			inputs.put(ConstantesSptar.PAR_N_NUM_PESO_CALCINA, solidoSuspendido.getNuPesoCalcina());
			inputs.put(ConstantesSptar.PAR_N_NUM_SS, solidoSuspendido.getnNumSS());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSF, solidoSuspendido.getnNumSSF());
			inputs.put(ConstantesSptar.PAR_N_NUM_SSV, solidoSuspendido.getnNumSSV());
			inputs.put(ConstantesSptar.PAR_N_TIPO_SOLI, solidoSuspendido.getIdTipoSolido());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, solidoSuspendido.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, solidoSuspendido.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, solidoSuspendido.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_SOLI_SUS));			
			solidoSuspendido.setIdSolidoSuspendido(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return solidoSuspendido;
	}
	
}
