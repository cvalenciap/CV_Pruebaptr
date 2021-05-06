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
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantRioDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class RioDaoImpl.
 */
@Repository
public class RioDaoImpl implements IMantRioDao {

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
	 * Método que permite obtener el listado de Rios.
	 *
	 * @param rioBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Result obtenerMantRio(RioBean rioBean, Paginacion paginacion) throws Exception {

		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_RIO).withProcedureName(Constants.SP_SEARCH_MANTRIO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_VNOMBRE_RIO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VABREVIATURA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_NPERTENECEAFORO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VESTADO, Types.VARCHAR),

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
										record.add(rs.getString(3).toUpperCase()); // "v_nombrerio"
										record.add(rs.getString(4).toUpperCase()); // "v_nombrelargo"
										record.add(rs.getString(5)); // n_perafo
										record.add(rs.getString(6)); // "v_estado"
										record.add(rs.getString(7).toUpperCase()); // "a_usuariocreacion"
										record.add(rs.getString(8)); // "a_fechacreacion"
										record.add(rs.getString(9)!=null?rs.getString(9).toUpperCase():rs.getString(9)); // "v_usuariomodificacion"
										record.add(rs.getString(10)); // "v_fechamodificacion"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_VNOMBRE_RIO, rioBean.getNombrerio());
		params.addValue(Constants.PAR_VABREVIATURA, rioBean.getAbreviatura());
		params.addValue(Constants.PAR_NPERTENECEAFORO, rioBean.getPerteneceAforo());
		params.addValue(Constants.PAR_VESTADO, rioBean.getEstado());

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
	 * Obtiene el río por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the rio bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public RioBean obtenerRio(Integer nid) throws Exception {
		ArrayList ret = null;
		RioBean result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_RIO).withProcedureName(Constants.PRC_GET_RIO)
		.declareParameters(new SqlParameter(Constants.PAR_CODIGO, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RioBean>() {

					@Override
					public RioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						RioBean record = new RioBean();
						record.setCodigo(rs.getString(1));
						record.setNombrerio(rs.getString(2));
						record.setNombreLargo(rs.getString(3));
						record.setAbreviatura(rs.getString(4));
						record.setEstado(rs.getString(5));
						record.setPrograma(rs.getString(6));
						record.setCodigoArea(rs.getInt(7));
						record.setCodigoSistema(rs.getInt(8));
						record.setPerteneceAforo(rs.getInt(9)+"");
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_CODIGO, nid);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (RioBean) ret.get(0);
		
		return result;
	}
	
	/**
	 * Realiza el cambío de estado de río.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarRio(RioBean rioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUARIO_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_RIO,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANTRIO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO, rioBean.getCodigo());	
			
			inputs.put(Constants.PAR_AUSUARIO_MODIFICACION, rioBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA, rioBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA, rioBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA, rioBean.getCodigoSistema()); 

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Realiza el registro de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarRio(RioBean rioBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_RIO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_LARGO_RIO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_NPERTENECEAFORO, OracleTypes.SMALLINT));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VESTADO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_APROGRAMA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA, OracleTypes.INTEGER));		
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_RIO,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_RIO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_VNOMBRE_RIO, rioBean.getNombrerio());
			inputs.put(Constants.PAR_VNOMBRE_LARGO_RIO, rioBean.getNombreLargo());
			inputs.put(Constants.PAR_VABREVIATURA, rioBean.getAbreviatura());	
			inputs.put(Constants.PAR_NPERTENECEAFORO, Integer.parseInt(rioBean.getPerteneceAforo().trim()));
			inputs.put(Constants.PAR_VESTADO, rioBean.getEstado());	
			inputs.put(Constants.PAR_AUSUARIO_CREACION, rioBean.getUsuarioCreacion());		
			inputs.put(Constants.PAR_AUSUARIO_MODIFICACION,rioBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA, rioBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA, rioBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA, rioBean.getCodigoSistema());		
		
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Realiza la modificación de rios.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarRio(RioBean rioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_CODIGO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_RIO, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_LARGO_RIO, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_NPERTENECEAFORO, OracleTypes.SMALLINT));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUARIO_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_RIO+Constants.P_SEPARADOR+Constants.SP_UPDATE_RIO,
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_CODIGO, rioBean.getCodigo());
			inputs.put(Constants.PAR_VNOMBRE_RIO, rioBean.getNombrerio());
			inputs.put(Constants.PAR_VNOMBRE_LARGO_RIO, rioBean.getNombreLargo());
			inputs.put(Constants.PAR_VABREVIATURA, rioBean.getAbreviatura());
			inputs.put(Constants.PAR_NPERTENECEAFORO, Integer.parseInt(rioBean.getPerteneceAforo().trim()));
			inputs.put(Constants.PAR_VESTADO, rioBean.getEstado());		
			inputs.put(Constants.PAR_AUSUARIO_MODIFICACION, rioBean.getUsuarioModificacion());
			inputs.put(Constants.PAR_APROGRAMA, rioBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA, rioBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA, rioBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 *  
	 * Obtiene los ríos registrados en la plataforma según su estado.
	 *
	 * @param estado de los ríos que se desean listar
	 * @return lista de objetos de tipo RioBean que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RioBean> obtenerRios(String estado) throws Exception {
		List<RioBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_RIO).withProcedureName(Constants.SP_LISTA_RIO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR),

						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RioBean>() {

									@Override
									public RioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RioBean record = new RioBean();
										record.setIntCodigo(rs.getInt(1)); // "n_codigo"
										record.setNombrerio(rs.getString(2)); // "v_nombrerio"
										record.setNombreLargo(rs.getString(3)); // "v_nombrelargo"
										record.setAbreviatura(rs.getString(4)); // "v_abreviatura"
										record.setEstado(rs.getString(5)); // "v_estado"
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_V_ESTADO, estado);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<RioBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/**
	 *  
	 * Valida si existen ríos con una determinada condición.
	 *
	 * @param rioBean objeto del tipo RioBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ValidaAbreviaturaBean validarAbrevMantRio(RioBean rioBean) throws Exception {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_RIO).withProcedureName(Constants.PRC_VALIDA_ABREV_RIO).declareParameters(				

    			new SqlParameter(Constants.PAR_VABREVIATURA, Types.VARCHAR),
    			new SqlParameter(Constants.PAR_VOMLARGO_VALIDA_RIO, Types.VARCHAR),
    			new SqlOutParameter(Constants.PRC_SALIDA_VAL_ABREV_RIO, Types.INTEGER),
    			new SqlOutParameter(Constants.PRC_SALIDA_VAL_NOMLAR_RIO, Types.INTEGER) 

    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		
		params.addValue(Constants.PAR_VABREVIATURA, rioBean.getAbreviatura().trim());
		params.addValue(Constants.PAR_VOMLARGO_VALIDA_RIO, rioBean.getNombreLargo().trim());
		
		Map<String,Object>  results = caller.execute(params);
		int saliValAbrev = (int) results.get(Constants.PRC_SALIDA_VAL_ABREV_RIO);
		int saliValNomLar = (int) results.get(Constants.PRC_SALIDA_VAL_NOMLAR_RIO);

		ValidaAbreviaturaBean vabrev = new ValidaAbreviaturaBean();
		vabrev.setBolAbreviatura(saliValAbrev > 0);
		vabrev.setBolNombreLargo(saliValNomLar > 0);
		
        return vabrev;	
	}
	

}
