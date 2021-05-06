/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.common.core.utils.ExecuteProcedure;
import pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao;

// TODO: Auto-generated Javadoc
/**
 * The Class CommArchivoAdjuntoDaoImpl.
 */
@Repository
public class CommArchivoAdjuntoDaoImpl implements ICommArchivoAdjuntoDao {

	/** The Constant logger. */
	public static final Logger logger = LoggerFactory.getLogger(CommArchivoAdjuntoDaoImpl.class);
	
	/** The environment. */
	@Autowired
	private Environment environment;

	/** The template. */
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;

	/**
	 * Sets the template.
	 *
	 * @param template the new template
	 */
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao#guardar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	public Long guardar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		Long codigo = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_ESTADO, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CTIPODOC, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CDOCUMENTO, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_EXTENSION, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CSECCION, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_NOMARCHIVO, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_TAMANOKB, Types.DOUBLE));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_RUTARCHIVO, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUCRE, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUMOD, Types.VARCHAR));
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_ESTADO, archivoAdjuntoBean.getEstado());
			inputs.put(Constants.PAR_N_CTIPODOC, archivoAdjuntoBean.getCodigoTipoDocumento());
			inputs.put(Constants.PAR_N_CDOCUMENTO, archivoAdjuntoBean.getCodigoDocumento());
			inputs.put(Constants.PAR_C_EXTENSION, archivoAdjuntoBean.getExtensionArchivo());
			inputs.put(Constants.PAR_N_CSECCION, archivoAdjuntoBean.getCodigoSeccion());
			inputs.put(Constants.PAR_C_NOMARCHIVO, archivoAdjuntoBean.getNombreArchivoOriginal());
			inputs.put(Constants.PAR_N_TAMANOKB, archivoAdjuntoBean.getTamanhoKilobytes());
			inputs.put(Constants.PAR_C_RUTARCHIVO, archivoAdjuntoBean.getRutaArchivo());
			inputs.put(Constants.PAR_A_USUCRE, archivoAdjuntoBean.getUsuarioCreacion());
			inputs.put(Constants.PAR_A_USUMOD, archivoAdjuntoBean.getUsuarioModificacion());
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_N_CARCHIADJU, OracleTypes.BIGINT));
			this.execSp = new ExecuteProcedure(
					template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_COMUN,
							ConstantsCommon.P_SEPARADOR, Constants.PRC_INSERTAR_ARCHIVO_ADJUNTO),
					lstParamsInput, lstParamsOutput);
			Map<String, Object> mapResultados = this.execSp.execute(inputs);
			logger.debug(
					"codigo: " + ((mapResultados != null) ? (Long) mapResultados.get(Constants.PAR_N_CARCHIADJU) : ""));
			codigo = (mapResultados != null) ? (Long) mapResultados.get(Constants.PAR_N_CARCHIADJU) : 0;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return codigo;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao#listar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result listar(CommArchivoAdjuntoBean archivoAdjuntoBean, Paginacion paginacion) throws Exception {
		Result result = new Result();
		List<CommArchivoAdjuntoBean> lstArchivosAdjuntos = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_COMUN).withProcedureName(Constants.PRC_BUSCAR_ARCHIVO_ADJUNTO)
				.declareParameters(new SqlParameter(Constants.PAR_N_CARCHIADJU, OracleTypes.BIGINT),
						new SqlParameter(Constants.PAR_N_ESTADO, Types.INTEGER),
						new SqlParameter(Constants.PAR_N_CTIPODOC, Types.INTEGER),
						new SqlParameter(Constants.PAR_N_CDOCUMENTO, Types.INTEGER),
						new SqlParameter(Constants.PAR_C_EXTENSION, Types.VARCHAR),
						new SqlParameter(Constants.PAR_N_CSECCION, Types.INTEGER),
						new SqlParameter(Constants.PAR_C_NOMARCHIVO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_N_TAMANOKB, Types.DOUBLE),
						new SqlParameter(Constants.PAR_C_RUTARCHIVO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_COL_ORDER_BY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COL_ORDER_BY_DIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAG_DESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDAD_PAG, Types.INTEGER))
				.declareParameters(new SqlOutParameter(Constants.PAR_OUT_QUANTITY, Types.INTEGER), new SqlOutParameter(
						ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<CommArchivoAdjuntoBean>() {
							@Override
							public CommArchivoAdjuntoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								CommArchivoAdjuntoBean record = new CommArchivoAdjuntoBean();
								record.setEstado(rs.getInt(1)); // N_ESTADO
								record.setNumeroArchivo(rs.getLong(2)); // N_CARCHIADJU
								record.setCodigoTipoDocumento(rs.getInt(3)); // N_CTIPODOC
								record.setCodigoDocumento(rs.getInt(4)); // N_CDOCUMENTO
								record.setExtensionArchivo(rs.getString(5)); // C_EXTENSION
								record.setCodigoSeccion(rs.getInt(6)); // N_CSECCION
								record.setNombreArchivoOriginal(rs.getString(7)); // C_NOMARCHIVO
								record.setTamanhoKilobytes(rs.getLong(8)); // N_TAMANOKB
								record.setRutaArchivo(rs.getString(9)); // C_RUTARCHIVO
								return record;
							}
						}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		MapSqlParameterSource mapParametros = new MapSqlParameterSource();
		mapParametros.addValue(Constants.PAR_N_CARCHIADJU, archivoAdjuntoBean.getNumeroArchivo());
		mapParametros.addValue(Constants.PAR_N_ESTADO, archivoAdjuntoBean.getEstado());
		mapParametros.addValue(Constants.PAR_N_CTIPODOC, archivoAdjuntoBean.getCodigoTipoDocumento());
		mapParametros.addValue(Constants.PAR_N_CDOCUMENTO, archivoAdjuntoBean.getCodigoDocumento());
		mapParametros.addValue(Constants.PAR_C_EXTENSION, archivoAdjuntoBean.getExtensionArchivo());
		mapParametros.addValue(Constants.PAR_N_CSECCION, archivoAdjuntoBean.getCodigoSeccion());
		mapParametros.addValue(Constants.PAR_C_NOMARCHIVO, archivoAdjuntoBean.getNombreArchivoOriginal());
		mapParametros.addValue(Constants.PAR_N_TAMANOKB, archivoAdjuntoBean.getTamanhoKilobytes());
		mapParametros.addValue(Constants.PAR_C_RUTARCHIVO, archivoAdjuntoBean.getRutaArchivo());
		mapParametros.addValue(Constants.PAR_COL_ORDER_BY, paginacion.getColorderby());
		mapParametros.addValue(Constants.PAR_COL_ORDER_BY_DIR, paginacion.getColorderbydir());
		mapParametros.addValue(Constants.PAR_PAG_DESDE, paginacion.getPagdesde());
		mapParametros.addValue(Constants.PAR_CANTIDAD_PAG, paginacion.getCantidadpag());
		Map<String, Object> mapResultados = caller.execute(mapParametros);
		lstArchivosAdjuntos = (List<CommArchivoAdjuntoBean>) mapResultados.get(ConstantsCommon.PAR_OUT_CURSOR);
		int intCantidadRegistros = (int) mapResultados.get(Constants.PAR_OUT_QUANTITY);
		result.setData(lstArchivosAdjuntos);
		result.setRecords((long) intCantidadRegistros);
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao#eliminar(java.lang.Long)
	 */
	@Override
	public void eliminar(Long codigo) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao#actualizar(pe.com.sedapal.scr.core.beans.CommArchivoAdjuntoBean)
	 */
	@Override
	public void actualizar(CommArchivoAdjuntoBean archivoAdjuntoBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CARCHIADJU, OracleTypes.BIGINT));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_ESTADO, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CTIPODOC, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CDOCUMENTO, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_EXTENSION, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CSECCION, Types.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_NOMARCHIVO, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_TAMANOKB, Types.DOUBLE));
			lstParamsInput.add(new SqlParameter(Constants.PAR_C_RUTARCHIVO, Types.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUMOD, Types.VARCHAR));
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_CARCHIADJU, archivoAdjuntoBean.getNumeroArchivo());
			inputs.put(Constants.PAR_N_ESTADO, archivoAdjuntoBean.getEstado());
			inputs.put(Constants.PAR_N_CTIPODOC, archivoAdjuntoBean.getCodigoTipoDocumento());
			inputs.put(Constants.PAR_N_CDOCUMENTO, archivoAdjuntoBean.getCodigoDocumento());
			inputs.put(Constants.PAR_C_EXTENSION, archivoAdjuntoBean.getExtensionArchivo());
			inputs.put(Constants.PAR_N_CSECCION, archivoAdjuntoBean.getCodigoSeccion());
			inputs.put(Constants.PAR_C_NOMARCHIVO, archivoAdjuntoBean.getNombreArchivoOriginal());
			inputs.put(Constants.PAR_N_TAMANOKB, archivoAdjuntoBean.getTamanhoKilobytes());
			inputs.put(Constants.PAR_C_RUTARCHIVO, archivoAdjuntoBean.getRutaArchivo());
			inputs.put(Constants.PAR_A_USUMOD, archivoAdjuntoBean.getUsuarioModificacion());
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			this.execSp = new ExecuteProcedure(
					template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_COMUN,
							ConstantsCommon.P_SEPARADOR, Constants.PRC_ACTUALIZAR_ARCHIVO_ADJUNTO),
					lstParamsInput, lstParamsOutput);
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICommArchivoAdjuntoDao#consultar(java.lang.Long)
	 */
	@Override
	public CommArchivoAdjuntoBean consultar(Long codigo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
