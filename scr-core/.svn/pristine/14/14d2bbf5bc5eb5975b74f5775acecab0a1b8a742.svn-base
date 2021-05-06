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
import pe.com.sedapal.scr.core.beans.RegistroAdjuntoBean;
import pe.com.sedapal.scr.core.beans.RegistroDirectoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IRegistroAdjuntoDao;
import pe.com.sedapal.scr.core.dao.IRegistroDirectoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class RegistroAdjuntoDaoImpl implements IRegistroAdjuntoDao {

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
	public List<RegistroAdjuntoBean> obtenerRegistroAdjuntobyRegistroLab(Integer idRegLab, Integer Parametro)
			throws GmdException {
		List<RegistroAdjuntoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ADJUNTOS).withProcedureName(ConstantesSptar.PRC_LIST_RLAB_ADJ)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RegistroAdjuntoBean>() {
									@Override
									public RegistroAdjuntoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RegistroAdjuntoBean record = new RegistroAdjuntoBean();
										record.setIdAdjunto(rs.getInt(1));
										record.setIdRegistroLaboratorio(rs.getInt(2));
										record.setIdParametro(rs.getInt(3));
										record.setNombreOriginal(rs.getString(4));
										record.setNombreInterno(rs.getString(5));
										record.setFechaRegistro(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_REGISTRO, idRegLab);
		params.addValue(ConstantesSptar.PAR_N_ID_PARAMETRO, Parametro);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<RegistroAdjuntoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public RegistroAdjuntoBean registrarRegistroAdjunto(RegistroAdjuntoBean item) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_REGISTRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_FILE_ORIGINAL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_FILE_INTERNO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_ADJUNTO, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_ADJUNTOS+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGI_RLAB_ADJ, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_REGISTRO, item.getIdRegistroLaboratorio());
			inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, item.getIdParametro());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, item.getIdPtarxSector());
			inputs.put(ConstantesSptar.PAR_V_DES_FILE_ORIGINAL, item.getNombreOriginal());
			inputs.put(ConstantesSptar.PAR_V_DES_FILE_INTERNO, item.getNombreInterno());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, item.getFlagRegistrador());	
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, item.getFlagAprobador());
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, item.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, item.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, item.getDeTermCrea());						
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idAdjunto = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_ADJUNTO));			
			item.setIdAdjunto(idAdjunto);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return item;
	}
}
