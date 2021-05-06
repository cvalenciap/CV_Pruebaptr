package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
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
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IFisicoQuimicoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Repository
public class FisicoQuimicoDaoImpl implements IFisicoQuimicoDao {

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
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimico(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException,ParseException {
		List<FisicoQuimicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FISI_QUIM).withProcedureName(ConstantesSptar.PRC_LIST_FISI_QUIM)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_D_FE_FIN, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FisicoQuimicoBean>() {
									@Override
									public FisicoQuimicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FisicoQuimicoBean record = new FisicoQuimicoBean();
										record.setIdFisicoQuimico(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setDescripcionPtar(rs.getString(3));
										record.setDescripcionSector(rs.getString(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPunto(rs.getString(6));
										record.setNuDeplec(rs.getDouble(7));
										record.setNuDilc(rs.getDouble(8));
										record.setnNumVolumen(rs.getDouble(9));
										record.setnNumOdi(rs.getDouble(10));
										record.setnNumOdf(rs.getDouble(11));
										record.setnNumDbo5(rs.getDouble(12));
										record.setnNumDbo5Prom(rs.getDouble(13));
										record.setStRegi(rs.getString(14));
										record.setEstado(rs.getString(15));
										record.setIdTipoFisico(rs.getInt(16));
										record.setDescripcionFisico(rs.getString(17));
										record.setFechaInicio(rs.getDate(18));
										record.setFechaFin(rs.getDate(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_D_FE_INICIO, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		params.addValue(ConstantesSptar.PAR_D_FE_FIN, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FisicoQuimicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FISI_QUIM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_FISI_QUIM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO, fisicoQuimicoBean.getIdFisicoQuimico());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, fisicoQuimicoBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, fisicoQuimicoBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_PROM, OracleTypes.NUMBER));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_FIS, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_FIN, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FISI_QUIM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_FISI_QUIM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO, fisicoQuimicoBean.getIdFisicoQuimico());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, fisicoQuimicoBean.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, fisicoQuimicoBean.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, fisicoQuimicoBean.getNuDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, fisicoQuimicoBean.getNuDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, fisicoQuimicoBean.getnNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, fisicoQuimicoBean.getnNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, fisicoQuimicoBean.getnNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, fisicoQuimicoBean.getnNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_PROM, fisicoQuimicoBean.getnNumDbo5Prom());
			inputs.put(ConstantesSptar.PAR_N_TIPO_FIS, fisicoQuimicoBean.getIdTipoFisico());
			inputs.put(ConstantesSptar.PAR_D_FE_INICIO, fisicoQuimicoBean.getFechaInicio());
			inputs.put(ConstantesSptar.PAR_D_FE_FIN, fisicoQuimicoBean.getFechaFin());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, fisicoQuimicoBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, fisicoQuimicoBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, fisicoQuimicoBean.getDeTermModi());				
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public FisicoQuimicoBean registrarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();		
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DEPLEC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DILC, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODI, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_ODF, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_DBO5_PROM, OracleTypes.NUMBER));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_TIPO_FIS, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_D_FE_FIN, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_FISI_QUIM+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_FISI_QUIM, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, fisicoQuimicoBean.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, fisicoQuimicoBean.getIdPuntoMuestra());
			inputs.put(ConstantesSptar.PAR_N_NUM_DEPLEC, fisicoQuimicoBean.getNuDeplec());
			inputs.put(ConstantesSptar.PAR_N_NUM_DILC, fisicoQuimicoBean.getNuDilc());
			inputs.put(ConstantesSptar.PAR_N_NUM_VOLUMEN, fisicoQuimicoBean.getnNumVolumen());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODI, fisicoQuimicoBean.getnNumOdi());
			inputs.put(ConstantesSptar.PAR_N_NUM_ODF, fisicoQuimicoBean.getnNumOdf());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5, fisicoQuimicoBean.getnNumDbo5());
			inputs.put(ConstantesSptar.PAR_N_NUM_DBO5_PROM, fisicoQuimicoBean.getnNumDbo5Prom());
			inputs.put(ConstantesSptar.PAR_N_TIPO_FIS, fisicoQuimicoBean.getIdTipoFisico());
			inputs.put(ConstantesSptar.PAR_D_FE_INICIO, fisicoQuimicoBean.getFechaInicio());
			inputs.put(ConstantesSptar.PAR_D_FE_FIN, fisicoQuimicoBean.getFechaFin());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, fisicoQuimicoBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, fisicoQuimicoBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, fisicoQuimicoBean.getDeTermCrea());
					
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_FISICO_QUIMICO));			
			fisicoQuimicoBean.setIdFisicoQuimico(idGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return fisicoQuimicoBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimicobyFilter(FisicoQuimicoBean fisicoQuimicoBean)
			throws GmdException {
		List<FisicoQuimicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FISI_QUIM).withProcedureName(ConstantesSptar.PRC_LIST_FISQUIXPTO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_NUM_VOLUMEN, OracleTypes.NUMBER),
						new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_D_FE_FIN, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FisicoQuimicoBean>() {
									@Override
									public FisicoQuimicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FisicoQuimicoBean record = new FisicoQuimicoBean();
										record.setIdFisicoQuimico(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setDescripcionPtar(rs.getString(3));
										record.setDescripcionSector(rs.getString(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPunto(rs.getString(6));
										record.setNuDeplec(rs.getDouble(7));
										record.setNuDilc(rs.getDouble(8));
										record.setnNumVolumen(rs.getDouble(9));
										record.setnNumOdi(rs.getDouble(10));
										record.setnNumOdf(rs.getDouble(11));
										record.setnNumDbo5(rs.getDouble(12));
										record.setnNumDbo5Prom(rs.getDouble(13));
										record.setStRegi(rs.getString(14));
										record.setEstado(rs.getString(15));
										record.setIdTipoFisico(rs.getInt(16));
										record.setDescripcionFisico(rs.getString(17));
										record.setFechaInicio(rs.getDate(18));
										record.setFechaFin(rs.getDate(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, fisicoQuimicoBean.getIdPtarxSector());
		params.addValue(ConstantesSptar.PAR_N_ID_PTO_MUESTRA, fisicoQuimicoBean.getIdPuntoMuestra());
		params.addValue(ConstantesSptar.PAR_N_NUM_VOLUMEN, fisicoQuimicoBean.getnNumVolumen());
		params.addValue(ConstantesSptar.PAR_D_FE_INICIO, fisicoQuimicoBean.getFechaInicio());
		params.addValue(ConstantesSptar.PAR_D_FE_FIN, fisicoQuimicoBean.getFechaFin());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FisicoQuimicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FisicoQuimicoBean> obtenerLtaFisicoQuimicoTodo(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException,ParseException {
		List<FisicoQuimicoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FISI_QUIM).withProcedureName(ConstantesSptar.PRC_LIST_FISI_QUIM_M)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_D_FE_INICIO, Types.DATE),
						new SqlParameter(ConstantesSptar.PAR_D_FE_FIN, Types.DATE),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FisicoQuimicoBean>() {
									@Override
									public FisicoQuimicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FisicoQuimicoBean record = new FisicoQuimicoBean();
										record.setIdFisicoQuimico(rs.getInt(1));
										record.setIdPtarxSector(rs.getInt(2));
										record.setDescripcionPtar(rs.getString(3));
										record.setDescripcionSector(rs.getString(4));
										record.setIdPuntoMuestra(rs.getInt(5));
										record.setDescripcionPunto(rs.getString(6));
										record.setNuDeplec(rs.getDouble(7));
										record.setNuDilc(rs.getDouble(8));
										record.setnNumVolumen(rs.getDouble(9));
										record.setnNumOdi(rs.getDouble(10));
										record.setnNumOdf(rs.getDouble(11));
										record.setnNumDbo5(rs.getDouble(12));
										record.setnNumDbo5Prom(rs.getDouble(13));
										record.setStRegi(rs.getString(14));
										record.setEstado(rs.getString(15));
										record.setIdTipoFisico(rs.getInt(16));
										record.setDescripcionFisico(rs.getString(17));
										record.setFechaInicio(rs.getDate(18));
										record.setFechaFin(rs.getDate(19));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSector);
		params.addValue(ConstantesSptar.PAR_D_FE_INICIO, ParametrosUtil.convertirStringtoDateSP(fechaInicio));
		params.addValue(ConstantesSptar.PAR_D_FE_FIN, ParametrosUtil.convertirStringtoDateSP(fechaFin));
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<FisicoQuimicoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerFechasFisicoQuimicoTodo(Integer idPtarxSerctor) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FISI_QUIM).withProcedureName(ConstantesSptar.PRC_LIST_FECHAS_PTAR_M)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setFechaInicio(ParametrosUtil.convertirDateSPString(rs.getDate(2)));
										record.setFechaFin(ParametrosUtil.convertirDateSPString(rs.getDate(3)));
										record.setPeriodo(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSerctor);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleGeneralBean> obtenerFechasFisicoQuimico(Integer idPtarxSerctor) throws GmdException {
		List<DetalleGeneralBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_FISI_QUIM).withProcedureName(ConstantesSptar.PRC_LIST_FECHAS_PTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleGeneralBean>() {
									@Override
									public DetalleGeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										DetalleGeneralBean record = new DetalleGeneralBean();
										record.setFechaInicio(ParametrosUtil.convertirDateSPString(rs.getDate(2)));
										record.setFechaFin(ParametrosUtil.convertirDateSPString(rs.getDate(3)));
										record.setPeriodo(rs.getString(4));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtarxSerctor);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<DetalleGeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
