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
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.ParametroPtarSectorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IAnalistaDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class AnalistaDaoImpl implements IAnalistaDao {

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
	public List<AnalistaBean> obtenerAnalista() throws GmdException {
		List<AnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ANALISTA).withProcedureName(ConstantesSptar.PRC_LIST_ANALISTA)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalistaBean>() {
									@Override
									public AnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalistaBean record = new AnalistaBean();
										record.setIdAnalista(rs.getInt(1));
										record.setTipoAnalista(rs.getInt(2));
										record.setNumeroFicha(rs.getString(3));
										record.setTipoDocumento(rs.getInt(4));
										record.setNumeroDocumento(rs.getString(5));
										record.setNombre(rs.getString(6));
										record.setApellido(rs.getString(7));
										record.setNombreCompleto(rs.getString(8));
										record.setIdPtarPorSector(rs.getInt(9));
										record.setDescripcionPtar(rs.getString(10));
										record.setDescripcionSector(rs.getString(11));
										record.setCargo(rs.getString(12));
										record.setObservacion(rs.getString(13));
										record.setFlagRegistrador(rs.getString(14));
										record.setDesFlagRegi(rs.getString(15));
										record.setFlagAprobador(rs.getString(16));
										record.setDesFlagAprobador(rs.getString(17));
										record.setStRegi(rs.getString(18));
										record.setEstado(rs.getString(19));
										record.setDescripcionTipoAnalista(rs.getString(20));
										record.setDescripcionTipoDoc(rs.getString(21));
										record.setCorreo(rs.getString(22));
										record.setCodigoUsuario(rs.getString(23));
										record.setIdFlagTipo(rs.getInt(24));
										record.setDescripcionFlagTipo(rs.getString(25));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAnalista(AnalistaBean analistaBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_ANALISTA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_ANALISTA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, analistaBean.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, analistaBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, analistaBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarAnalista(AnalistaBean analistaBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();			
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_FICHA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TIPO_DOC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NOMBRES, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_APELLIDOS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_CARGO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_CORREO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_COD_USUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLAG_TIPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_USUA_MODI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_MODI, OracleTypes.VARCHAR));			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_ANALISTA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ACTUALIZAR_ANALISTA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, analistaBean.getIdAnalista());
			inputs.put(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, analistaBean.getTipoAnalista());
			inputs.put(ConstantesSptar.PAR_V_DES_NUM_FICHA, analistaBean.getNumeroFicha());
			inputs.put(ConstantesSptar.PAR_N_NUM_TIPO_DOC, analistaBean.getTipoDocumento());
			inputs.put(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, analistaBean.getNumeroDocumento());
			inputs.put(ConstantesSptar.PAR_V_DES_NOMBRES, analistaBean.getNombre());
			inputs.put(ConstantesSptar.PAR_V_DES_APELLIDOS, analistaBean.getApellido());
			inputs.put(ConstantesSptar.PAR_V_DES_CARGO, analistaBean.getCargo());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, analistaBean.getIdPtarPorSector());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, analistaBean.getObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, analistaBean.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, analistaBean.getFlagAprobador());			
			inputs.put(ConstantesSptar.PAR_V_DES_CORREO, analistaBean.getCorreo());	
			inputs.put(ConstantesSptar.PAR_V_COD_USUARIO, analistaBean.getCodigoUsuario());	
			inputs.put(ConstantesSptar.PAR_N_FLAG_TIPO, analistaBean.getIdFlagTipo());	
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, analistaBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_USUA_MODI, analistaBean.getIdUsuaModi());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_MODI, analistaBean.getDeTermModi());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public AnalistaBean registrarAnalista(AnalistaBean analistaBean) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_FICHA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_NUM_TIPO_DOC, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_NOMBRES, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_APELLIDOS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_CARGO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_OBSERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_REGISTRADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_FLG_APROBADOR, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_DES_CORREO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_COD_USUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_FLAG_TIPO, OracleTypes.DECIMAL));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));		
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_ANALISTA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_ANALISTA, 
					environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, analistaBean.getTipoAnalista());
			inputs.put(ConstantesSptar.PAR_V_DES_NUM_FICHA, analistaBean.getNumeroFicha());
			inputs.put(ConstantesSptar.PAR_N_NUM_TIPO_DOC, analistaBean.getTipoDocumento());
			inputs.put(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, analistaBean.getNumeroDocumento());
			inputs.put(ConstantesSptar.PAR_V_DES_NOMBRES, analistaBean.getNombre());
			inputs.put(ConstantesSptar.PAR_V_DES_APELLIDOS, analistaBean.getApellido());
			inputs.put(ConstantesSptar.PAR_V_DES_CARGO, analistaBean.getCargo());
			inputs.put(ConstantesSptar.PAR_N_ID_PTARXSECTOR, analistaBean.getIdPtarPorSector());
			inputs.put(ConstantesSptar.PAR_V_DES_OBSERVACION, analistaBean.getObservacion());
			inputs.put(ConstantesSptar.PAR_V_FLG_REGISTRADOR, analistaBean.getFlagRegistrador());
			inputs.put(ConstantesSptar.PAR_V_FLG_APROBADOR, analistaBean.getFlagAprobador());	
			inputs.put(ConstantesSptar.PAR_V_DES_CORREO, analistaBean.getCorreo());	
			inputs.put(ConstantesSptar.PAR_V_COD_USUARIO, analistaBean.getCodigoUsuario());	
			inputs.put(ConstantesSptar.PAR_N_FLAG_TIPO, analistaBean.getIdFlagTipo());	
			inputs.put(ConstantesSptar.PAR_V_ST_REGI, analistaBean.getStRegi());
			inputs.put(ConstantesSptar.PAR_A_V_USUCRE, analistaBean.getIdUsuaCrea());
			inputs.put(ConstantesSptar.PAR_A_V_ID_TERM_CREA, analistaBean.getDeTermCrea());						
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			int idAnalistaGenerado = Integer.parseInt(""+outputs.get(ConstantesSptar.PAR_N_ID_ANALISTA));			
			analistaBean.setIdAnalista(idAnalistaGenerado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
		return analistaBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TrabajadorBean> obtenerTrabajador(String numeroFicha) throws GmdException {
		List<TrabajadorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ANALISTA).withProcedureName(ConstantesSptar.PRC_BUSCAR_TRABAJADOR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_FICHA, Types.VARCHAR),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TrabajadorBean>() {
									@Override
									public TrabajadorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TrabajadorBean record = new TrabajadorBean();
										record.setNumFicha(rs.getString(1));
										record.setNombre(rs.getString(2));
										record.setApellidoPaterno(rs.getString(3));
										record.setApellidoMaterno(rs.getString(4));
										record.setCargo(rs.getString(5));
										record.setCodigoEstado(rs.getString(6));
										record.setUbicacion(rs.getString(7));
										record.setGrupoPersonal(rs.getString(8));
										record.setDni(rs.getString(9));
										record.setCorreo(rs.getString(10));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_V_DES_NUM_FICHA, numeroFicha);	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<TrabajadorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnalistaBean> obtenerAnalistabyFiltro(AnalistaBean analistaBean) throws GmdException {
		List<AnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ANALISTA).withProcedureName(ConstantesSptar.PRC_BUSCAR_ANALISTA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, Types.DECIMAL),
						new SqlParameter(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, Types.VARCHAR),
						new SqlParameter(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalistaBean>() {
									@Override
									public AnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalistaBean record = new AnalistaBean();
										record.setIdAnalista(rs.getInt(1));
										record.setTipoAnalista(rs.getInt(2));
										record.setNumeroDocumento(rs.getString(3));
										record.setTipoDocumento(rs.getInt(4));
										record.setNumeroDocumento(rs.getString(5));
										record.setNombre(rs.getString(6));
										record.setApellido(rs.getString(7));
										record.setNombreCompleto(rs.getString(8));
										record.setIdPtarPorSector(rs.getInt(9));
										record.setDescripcionPtar(rs.getString(10));
										record.setDescripcionSector(rs.getString(11));
										record.setCargo(rs.getString(12));
										record.setObservacion(rs.getString(13));
										record.setFlagRegistrador(rs.getString(14));
										record.setDesFlagRegi(rs.getString(15));
										record.setFlagAprobador(rs.getString(16));
										record.setDesFlagAprobador(rs.getString(17));
										record.setStRegi(rs.getString(18));
										record.setEstado(rs.getString(19));
										record.setCorreo(rs.getString(20));
										record.setCodigoUsuario(rs.getString(21));
										record.setIdFlagTipo(rs.getInt(22));
										record.setDescripcionFlagTipo(rs.getString(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_ANALISTA, analistaBean.getIdAnalista());
		params.addValue(ConstantesSptar.PAR_V_DES_NUM_DOCUMENTO, analistaBean.getNumeroDocumento());
		params.addValue(ConstantesSptar.PAR_N_NUM_TIPO_ANALISTA, analistaBean.getTipoAnalista());
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnalistaBean> obtenerAnalistaTodo() throws GmdException {
		List<AnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ANALISTA).withProcedureName(ConstantesSptar.PRC_LIST_ANALISTA_M)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalistaBean>() {
									@Override
									public AnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalistaBean record = new AnalistaBean();
										record.setIdAnalista(rs.getInt(1));
										record.setTipoAnalista(rs.getInt(2));
										record.setNumeroFicha(rs.getString(3));
										record.setTipoDocumento(rs.getInt(4));
										record.setNumeroDocumento(rs.getString(5));
										record.setNombre(rs.getString(6));
										record.setApellido(rs.getString(7));
										record.setNombreCompleto(rs.getString(8));
										record.setIdPtarPorSector(rs.getInt(9));
										record.setDescripcionPtar(rs.getString(10));
										record.setDescripcionSector(rs.getString(11));
										record.setCargo(rs.getString(12));
										record.setObservacion(rs.getString(13));
										record.setFlagRegistrador(rs.getString(14));
										record.setDesFlagRegi(rs.getString(15));
										record.setFlagAprobador(rs.getString(16));
										record.setDesFlagAprobador(rs.getString(17));
										record.setStRegi(rs.getString(18));
										record.setEstado(rs.getString(19));
										record.setDescripcionTipoAnalista(rs.getString(20));
										record.setDescripcionTipoDoc(rs.getString(21));
										record.setCorreo(rs.getString(22));
										record.setCodigoUsuario(rs.getString(23));
										record.setIdFlagTipo(rs.getInt(24));
										record.setDescripcionFlagTipo(rs.getString(25));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
	
	@Transactional	
	@SuppressWarnings("unchecked")
	@Override
	public List<AnalistaBean> obtenerAnalistabyUsuario(String codigoUsuario) throws GmdException {
		List<AnalistaBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_ANALISTA).withProcedureName(ConstantesSptar.PRC_BUSCAR_USUARIO)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_V_COD_USUARIO, Types.VARCHAR),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalistaBean>() {
									@Override
									public AnalistaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalistaBean record = new AnalistaBean();
										record.setIdAnalista(rs.getInt(1));
										record.setTipoAnalista(rs.getInt(2));
										record.setNumeroDocumento(rs.getString(3));
										record.setTipoDocumento(rs.getInt(4));
										record.setNumeroDocumento(rs.getString(5));
										record.setNombre(rs.getString(6));
										record.setApellido(rs.getString(7));
										record.setNombreCompleto(rs.getString(8));
										record.setIdPtarPorSector(rs.getInt(9));
										record.setDescripcionPtar(rs.getString(10));
										record.setDescripcionSector(rs.getString(11));
										record.setCargo(rs.getString(12));
										record.setObservacion(rs.getString(13));
										record.setFlagRegistrador(rs.getString(14));
										record.setDesFlagRegi(rs.getString(15));
										record.setFlagAprobador(rs.getString(16));
										record.setDesFlagAprobador(rs.getString(17));
										record.setStRegi(rs.getString(18));
										record.setEstado(rs.getString(19));
										record.setCorreo(rs.getString(20));
										record.setCodigoUsuario(rs.getString(21));
										record.setIdFlagTipo(rs.getInt(22));
										record.setDescripcionFlagTipo(rs.getString(23));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_V_COD_USUARIO, codigoUsuario);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<AnalistaBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> obtenerParametrosxAnalistaAsignados(Integer idAnalista) throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXANA).withProcedureName(ConstantesSptar.PRC_LIST_PARAMETROSXANA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdAnalista(rs.getInt(1));
										record.setIdParametro(rs.getInt(2));
										record.setDescripcionParametro(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroPtarSectorBean> obtenerParametrosxAnalistaNoAsignados(Integer idAnalista) throws GmdException {
		List<ParametroPtarSectorBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_PARAMETROSXANA).withProcedureName(ConstantesSptar.PRC_LIST_PARAMETRO_NO_ANA)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ParametroPtarSectorBean>() {
									@Override
									public ParametroPtarSectorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ParametroPtarSectorBean record = new ParametroPtarSectorBean();
										record.setIdParametro(rs.getInt(1));
										record.setDescripcionParametro(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);		
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<ParametroPtarSectorBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularAllParametrosxAnalista(Integer idAnalista) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));				
			paramsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXANA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_ELIMINAR_PARAMETROXANA_T, 
			environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);	
			this.execSp.executeSp(inputs);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
			throw new GmdException(excepcion);
		}
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarParametrosxAnalista(Integer idAnalista, ParametroPtarSectorBean parametro,
			BaseSptarBean auditoria) throws GmdException {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Integer contadorEjecucion = 1;
		try {
				paramsInput = new ArrayList<SqlParameter>();			
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_ANALISTA, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_N_ID_PARAMETRO, OracleTypes.DECIMAL));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_V_ST_REGI, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_USUCRE, OracleTypes.VARCHAR));
				paramsInput.add(new SqlParameter(ConstantesSptar.PAR_A_V_ID_TERM_CREA, OracleTypes.VARCHAR));			
				
				paramsOutput = new ArrayList<SqlOutParameter>();
	
				this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantesSptar.PCK_SPTAR_PARAMETROSXANA+ConstantesSptar.P_SEPARADOR+ConstantesSptar.PRC_REGISTRAR_PARAMETROXANA, 
						environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
				inputs = new HashMap<String, Object>();
				inputs.put(ConstantesSptar.PAR_N_ID_ANALISTA, idAnalista);
				inputs.put(ConstantesSptar.PAR_N_ID_PARAMETRO, parametro.getIdParametro());
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
}
