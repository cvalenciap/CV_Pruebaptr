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
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantClimaDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ClimaDaoImpl.
 */
@Repository
public class ClimaDaoImpl implements IMantClimaDao {

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
	
	/**
	 * Método que permite obtener el listado de Clima.
	 *
	 * @param climaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Result obtenerMantClima(ClimaBean climaBean, Paginacion paginacion) throws Exception {

		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_CLIMA).withProcedureName(Constants.SP_SEARCH_MANTCLIMA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_VNOMBRE_CLIMA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VABREVIATURA_CLIMA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VESTADO_CLIMA, Types.VARCHAR),

						new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ArrayList>() {

									@SuppressWarnings("unchecked")
									@Override
									public ArrayList mapRow(ResultSet rs, int rowNum) throws SQLException {
										ArrayList record = new ArrayList();
										record.add("");
										record.add(rs.getLong(1)); // "n_codigo"
										record.add(rs.getString(2).toUpperCase()); // "c_abrevi"
										record.add(rs.getString(3).toUpperCase()); // "v_nombreClima"
										record.add(rs.getString(4)); // "v_estado"
										record.add(rs.getString(5).toUpperCase()); // "a_usuaClimacreacion"
										record.add(rs.getString(6)); // "a_fechacreacion"
										record.add(rs.getString(7)!=null?rs.getString(7).toUpperCase():rs.getString(7)); // "v_usuaClimamodificacion"
										record.add(rs.getString(8)); // "v_fechamodificacion"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_VNOMBRE_CLIMA, climaBean.getNombreClima());
		params.addValue(Constants.PAR_VABREVIATURA_CLIMA, climaBean.getAbreviatura());
		params.addValue(Constants.PAR_VESTADO_CLIMA, climaBean.getEstado());

		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		Result result = new Result();
		result.setData(lstRetorno);
		result.setRecords((long) quantity);

		return result;
	
	}

	/**
	 *  
	 * Obtiene el clima por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo climaBean que contiene el registro
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ClimaBean obtenerClima(Integer nid) throws Exception {
		ArrayList ret = null;
		ClimaBean result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_CLIMA).withProcedureName(Constants.PRC_GET_CLIMA)
		.declareParameters(new SqlParameter(Constants.PAR_NCODIGO_CLIMA, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ClimaBean>() {

					@Override
					public ClimaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ClimaBean record = new ClimaBean();
						record.setCodigo(rs.getString(1));
						record.setNombreClima(rs.getString(2));
						record.setAbreviatura(rs.getString(3));
						record.setEstado(rs.getString(4));
						record.setPrograma(rs.getString(5));
						record.setCodigoArea(rs.getInt(6));
						record.setCodigoSistema(rs.getInt(7));
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_NCODIGO_CLIMA, nid);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (ClimaBean) ret.get(0);
		
		return result;
	}
	
	/**
	 *  
	 * Realiza el cambío de estado de clima.
	 *
	 * @param climaBean the clima bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarClima(ClimaBean climaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_CLIMA, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUACLIMA_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_CLIMA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_CLIMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_CLIMA, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_CLIMA,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANTCLIMA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_CLIMA, climaBean.getCodigo());	
			
			inputs.put(Constants.PAR_AUSUACLIMA_MODIFICACION, climaBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_CLIMA, climaBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA_CLIMA, climaBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_CLIMA, climaBean.getCodigoSistema()); 

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 *  
	 * Realiza el registro de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarClima(ClimaBean climaBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_CLIMA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_CLIMA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_VESTADO_CLIMA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUACLIMA_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUACLIMA_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_CLIMA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_CLIMA , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_CLIMA, OracleTypes.INTEGER));		
	

			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_CLIMA,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_CLIMA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_VNOMBRE_CLIMA, climaBean.getNombreClima());
			inputs.put(Constants.PAR_VABREVIATURA_CLIMA, climaBean.getAbreviatura());	
			inputs.put(Constants.PAR_VESTADO_CLIMA, climaBean.getEstado());	
			inputs.put(Constants.PAR_AUSUACLIMA_CREACION, climaBean.getUsuarioCreacion());		
			inputs.put(Constants.PAR_AUSUACLIMA_MODIFICACION,climaBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_CLIMA, climaBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_CLIMA, climaBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_CLIMA, climaBean.getCodigoSistema());		
		

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	/**
	 *  
	 * Realiza la modificación de clima.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarClima(ClimaBean climaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_CLIMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_CLIMA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_CLIMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO_CLIMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUACLIMA_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_CLIMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_CLIMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_CLIMA, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_CLIMA+Constants.P_SEPARADOR+Constants.SP_UPDATE_CLIMA, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_CLIMA, climaBean.getCodigo());
			inputs.put(Constants.PAR_VNOMBRE_CLIMA, climaBean.getNombreClima());
			inputs.put(Constants.PAR_VABREVIATURA_CLIMA, climaBean.getAbreviatura());
			inputs.put(Constants.PAR_VESTADO_CLIMA, climaBean.getEstado());		
			inputs.put(Constants.PAR_AUSUACLIMA_MODIFICACION, climaBean.getUsuarioModificacion());
			inputs.put(Constants.PAR_APROGRAMA_CLIMA, climaBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA_CLIMA, climaBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_CLIMA, climaBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 *  
	 * Obtiene los climas registrados en la plataforma según su estado.
	 *
	 * @param estado de los climas que se desean listar
	 * @return lista de objetos de tipo ClimaBean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ClimaBean> obtenerClimas(String estado) throws Exception {
		List<ClimaBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_CLIMA).withProcedureName(Constants.SP_LISTA_CLIMA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_VESTADO_LCLIMA, Types.VARCHAR),

						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ClimaBean>() {

									@Override
									public ClimaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ClimaBean record = new ClimaBean();
										record.setLonCodigo(rs.getLong(1)); // "n_codigo"
										record.setNombreClima(rs.getString(2)); // "v_nombreclima"
										record.setAbreviatura(rs.getString(3));
										record.setEstado(rs.getString(4)); // "v_estado"
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_VESTADO_LCLIMA, estado);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<ClimaBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/**
	 *  
	 * Valida si existen climas con una determinada condición.
	 *
	 * @param climaBean objeto del tipo ClimaBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ValidaAbreviaturaBean validarAbrevMantClima(ClimaBean climaBean) throws Exception {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CLIMA).withProcedureName(Constants.PRC_VALIDA_ABREV_CLIMA).declareParameters(				

    			new SqlParameter(Constants.PAR_VABREVIATURA, Types.VARCHAR),
    			new SqlOutParameter(Constants.PRC_SALIDA_VAL_ABREV_CLIMA, Types.INTEGER) 

    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		
		params.addValue(Constants.PAR_VABREVIATURA, climaBean.getAbreviatura().trim());
		
		Map<String,Object>  results = caller.execute(params);
		int saliValAbrev = (int) results.get(Constants.PRC_SALIDA_VAL_ABREV_CLIMA);

		ValidaAbreviaturaBean vabrev = new ValidaAbreviaturaBean();
		vabrev.setBolAbreviatura(saliValAbrev > 0);
		
        return vabrev;	
	}

}
