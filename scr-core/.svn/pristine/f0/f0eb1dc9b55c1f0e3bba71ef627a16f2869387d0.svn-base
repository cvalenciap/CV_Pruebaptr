package pe.com.sedapal.scr.core.dao.impl;

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
import pe.com.sedapal.scr.core.beans.PlantillaDBOBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IPlantillaDBODao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class PlantillaDBODaoImpl implements IPlantillaDBODao {

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
	public List<PlantillaDBOBean> obtenerRegistroPlantillaDBO(Integer idPtarxSector) throws GmdException {
		List<PlantillaDBOBean> lstRetorno = null;		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PLANT_DBO).withProcedureName(ConstantesSptar.PRC_LIST_PLANT_DBO)
				.declareParameters(	
						new  SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PlantillaDBOBean>() {
									@Override
									public PlantillaDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PlantillaDBOBean record = new PlantillaDBOBean();
										record.setIndice(rowNum);
										record.setIdPlantillaDBO(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setIdSubParametro(rs.getInt(3));
										record.setDescripcionSubparametro(rs.getString(4));
										record.setIntervaloMinimo(rs.getString(5) != null && !Strings.isEmpty(rs.getString(5)) && !rs.getString(5).trim().equals("") && !rs.getString(5).equals("")  && !Strings.isEmpty(rs.getString(5))? Double.parseDouble(rs.getString(5).trim()) : null );
										record.setIntervaloMaximo(rs.getString(6) != null  && !Strings.isEmpty(rs.getString(6)) && !rs.getString(6).trim().equals("") && !rs.getString(6).equals("") && !Strings.isEmpty(rs.getString(6))? Double.parseDouble(rs.getString(6).trim()) : null );
										record.setIndicadorFactor(rs.getString(7) != null  && !Strings.isEmpty(rs.getString(7)) && !rs.getString(7).trim().equals("") && !rs.getString(7).equals("") && !Strings.isEmpty(rs.getString(7))? Integer.parseInt(rs.getString(7).trim()) : null );
										record.setIdPuntoMuestra(rs.getInt(8));
										record.setDescripcionPuntoMuestra(rs.getString(9));
										record.setIndicadorSemilla(rs.getString(10) != null  && !Strings.isEmpty(rs.getString(10)) && !rs.getString(10).trim().equals("") && !rs.getString(10).equals("") && !Strings.isEmpty(rs.getString(10))? Integer.parseInt(rs.getString(10).trim()) : null );
										record.setNumVolumen(rs.getString(11) != null  && !Strings.isEmpty(rs.getString(11)) && !rs.getString(11).trim().equals("") && !rs.getString(11).equals("") && !Strings.isEmpty(rs.getString(11))? Double.parseDouble(rs.getString(11).trim()) : null );
										record.setNumValorSemilla(rs.getString(12) != null  && !Strings.isEmpty(rs.getString(12)) && !rs.getString(12).trim().equals("") && !rs.getString(12).equals("") && !Strings.isEmpty(rs.getString(12))? Double.parseDouble(rs.getString(12).trim()) : null );
										record.setIdGrupo(rs.getString(13) != null  && !Strings.isEmpty(rs.getString(13)) && !rs.getString(13).trim().equals("") && !rs.getString(13).equals("") && !Strings.isEmpty(rs.getString(13))? Integer.parseInt(rs.getString(13).trim()) : null );
										record.setFechaMonitoreo(ParametrosUtil.getFechaActualToDate());
										record.setFechaRegDato(ParametrosUtil.getFechaActualToDate());
										record.setFechaInicial(ParametrosUtil.getFechaActualToDate());
										record.setFechaVencimiento(ParametrosUtil.getFechaActualToDate());
										record.setIndicadorTipoSemilla(rs.getString(14));
										record.setIndicePadre(rs.getInt(15));
										record.setIdFormulaDbo5(0);
										record.setIdFormulaDeplec(0);
										record.setIdFormulaDilc(0);										
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PlantillaDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularPlantillaDBO(PlantillaDBOBean bean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PLANT_DBO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIM_PLANT_DBO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, bean.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, bean.getIdGrupo());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, bean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, bean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public PlantillaDBOBean registrarPlantillaDBO(PlantillaDBOBean registro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SEMILLA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_FIS, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.DECIMAL));					
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_PROM, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_CREA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_PLANTILLA_DBO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PLANT_DBO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_PLANT_DBO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registro.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_NUM_SEMILLA, registro.getNumValorSemilla());
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, registro.getIdGrupo());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, registro.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registro.getIdPuntoMuestra());			
			inputs.put(ConstantesSptar.PAR_N_TIPO_FIS, registro.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, registro.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, registro.getNumDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registro.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, registro.getNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, registro.getNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, registro.getNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_PROM, registro.getNumDbo5());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_CREA, registro.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, registro.getDeTermCrea());
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idPlantilla = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_PLANTILLA_DBO));			
			registro.setIdPlantillaDBO(idPlantilla);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return registro;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarPlantillaDBO(PlantillaDBOBean registro) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PLANTILLA_DBO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_SEMILLA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_GRUPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_VAL_PADRE, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_FIS, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.DECIMAL));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.DECIMAL));					
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_PROM, OracleTypes.DECIMAL));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PLANT_DBO+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTU_PLANT_DBO, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PLANTILLA_DBO, registro.getIdPlantillaDBO());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, registro.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_NUM_SEMILLA, registro.getNumValorSemilla());
			inputs.put(ConstantesSptar.PAR_N_ID_GRUPO, registro.getIdGrupo());
			inputs.put(ConstantesSptar.PAR_N_VAL_PADRE, registro.getIndicePadre());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, registro.getIdPuntoMuestra());			
			inputs.put(ConstantesSptar.PAR_N_TIPO_FIS, registro.getIdSubParametro());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, registro.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, registro.getNumDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, registro.getNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, registro.getNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, registro.getNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, registro.getNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_PROM, registro.getNumDbo5());			
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, registro.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, registro.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, registro.getDeTermModi());
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraFaltante(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PLANT_DBO).withProcedureName(ConstantesSptar.PRC_LIST_FALTA_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PuntoMuestraPtarSectorBean>() {
									@Override
									public PuntoMuestraPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PuntoMuestraPtarSectorBean record = new PuntoMuestraPtarSectorBean();
										record.setIdPuntoMuestra(rs.getInt(1));
										record.setDescripcionPunto(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										record.setIdSubParametro(rs.getString(4) != null  && !Strings.isEmpty(rs.getString(4)) && !rs.getString(4).trim().equals("") && !rs.getString(4).equals("") && !Strings.isEmpty(rs.getString(4))? Integer.parseInt(rs.getString(4).trim()) : null );
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
	public List<PlantillaDBOBean> obtenerRegistrosPlantillaSobrante(Integer idPtarSector) throws GmdException {
		List<PlantillaDBOBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PLANT_DBO).withProcedureName(ConstantesSptar.PRC_LIST_SOBRA_DBO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PlantillaDBOBean>() {
									@Override
									public PlantillaDBOBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PlantillaDBOBean record = new PlantillaDBOBean();
										record.setIdPlantillaDBO(rs.getInt(1));					
										record.setIdGrupo(rs.getInt(2));
										record.setIndicePadre(rs.getInt(3));
										record.setIdPuntoMuestra(rs.getInt(4));
										record.setDescripcionPuntoMuestra(rs.getString(5));
										record.setIdSubParametro(rs.getInt(7));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarSector);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PlantillaDBOBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
}
