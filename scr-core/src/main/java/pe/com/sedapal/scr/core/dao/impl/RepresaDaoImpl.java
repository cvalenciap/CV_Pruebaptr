/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */

package pe.com.sedapal.scr.core.dao.impl;

import java.math.BigDecimal;
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
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantRepresaDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class RepresaDaoImpl.
 */
@Repository
public class RepresaDaoImpl implements IMantRepresaDao {

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
	 * Método que permite obtener el listado de Represa.
	 *
	 * @param represaBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	
	@Override
	@SuppressWarnings("rawtypes")
	public Result obtenerMantRepresa(RepresaBean represaBean, Paginacion paginacion) throws Exception {

		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESA).withProcedureName(Constants.SP_SEARCH_MANTREPRESA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_VNOMBRE_REPRESA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VABREVIATURA_REPRESA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_NPERTENECEALMACENAMIENTO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VESTADO_REPRESA, Types.VARCHAR),

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
										record.add(rs.getString(2)!=null?rs.getString(2).toUpperCase():rs.getString(2)); // "c_abrevi"
										record.add(rs.getString(3)!=null?rs.getString(3).toUpperCase():rs.getString(3)); // "v_nombreRepresa"
										record.add(rs.getString(4)); // "n_peralm"
										record.add(rs.getBigDecimal(5).toString()); // n_topmax
										record.add(rs.getString(6)); // "v_estado"
										record.add(rs.getString(7)!=null?rs.getString(7).toUpperCase():rs.getString(7)); // "a_usuaClimacreacion"
										record.add(rs.getString(8)); // "a_fechacreacion"
										record.add(rs.getString(9)!=null?rs.getString(9).toUpperCase():rs.getString(9)); // "v_usuaClimamodificacion"
										record.add(rs.getString(10)); // "v_fechamodificacion"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_VNOMBRE_REPRESA, represaBean.getNombreRepresa());
		params.addValue(Constants.PAR_VABREVIATURA_REPRESA, represaBean.getAbreviatura());
		params.addValue(Constants.PAR_NPERTENECEALMACENAMIENTO, represaBean.getPerteneceAlmacenamiento());
		params.addValue(Constants.PAR_VESTADO_REPRESA, represaBean.getEstado());

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
	 * Obtiene la represa por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represa bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo represaBean que contiene el registro
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public RepresaBean obtenerRepresa(Integer nid) throws Exception {
		ArrayList ret = null;
		RepresaBean result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESA).withProcedureName(Constants.PRC_GET_REPRESA)
		.declareParameters(new SqlParameter(Constants.PAR_NCODIGO_REPRESA, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RepresaBean>() {

					@Override
					public RepresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						RepresaBean record = new RepresaBean();
						record.setCodigo(rs.getString(1));
						record.setNombreRepresa(rs.getString(2));
						record.setAbreviatura(rs.getString(3));
						record.setEstado(rs.getString(4));
						record.setPrograma(rs.getString(5));
						record.setCodigoArea(rs.getInt(6));
						record.setCodigoSistema(rs.getInt(7));
						record.setPerteneceAlmacenamiento(rs.getInt(8)+"");
						record.setStrTopeMaximo(rs.getBigDecimal(9).toString());
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_NCODIGO_REPRESA, nid);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (RepresaBean) ret.get(0);
		
		return result;
	}
	
	/**
	 *  
	 * Realiza el cambío de estado de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarRepresa(RepresaBean represaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESA, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESA_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESA, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_REPRESA,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANTREPRESA),
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_REPRESA, represaBean.getCodigo());	
			
			inputs.put(Constants.PAR_AUSUAREPRESA_MODIFICACION, represaBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_REPRESA, represaBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESA, represaBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESA, represaBean.getCodigoSistema()); 

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Realiza el registro de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarRepresa(RepresaBean represaBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_REPRESA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_REPRESA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_NTOPE_MAXIMO, OracleTypes.NUMERIC));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_NPERTENECEALMACENAMIENTO, OracleTypes.SMALLINT));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VESTADO_REPRESA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESA_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESA_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESA , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESA, OracleTypes.INTEGER));		
	

			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_REPRESA,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_REPRESA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_VNOMBRE_REPRESA, represaBean.getNombreRepresa());
			inputs.put(Constants.PAR_VABREVIATURA_REPRESA, represaBean.getAbreviatura());	
			inputs.put(Constants.PAR_NTOPE_MAXIMO, new BigDecimal(represaBean.getStrTopeMaximo()));	
			inputs.put(Constants.PAR_NPERTENECEALMACENAMIENTO, Integer.parseInt(represaBean.getPerteneceAlmacenamiento().trim()));
			inputs.put(Constants.PAR_VESTADO_REPRESA, represaBean.getEstado());	
			inputs.put(Constants.PAR_AUSUAREPRESA_CREACION, represaBean.getUsuarioCreacion());		
			inputs.put(Constants.PAR_AUSUAREPRESA_MODIFICACION,represaBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_REPRESA, represaBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESA, represaBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESA, represaBean.getCodigoSistema());		
		

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	
	/**
	 * Realiza la modificación de represa.
	 *
	 * @param represaBean objeto del tipo RepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarRepresa(RepresaBean represaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_REPRESA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_REPRESA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_NTOPE_MAXIMO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NPERTENECEALMACENAMIENTO, OracleTypes.SMALLINT));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO_REPRESA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESA_MODIFICACION, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESA, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_REPRESA+Constants.P_SEPARADOR+Constants.SP_UPDATE_REPRESA,
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_REPRESA, represaBean.getCodigo());
			inputs.put(Constants.PAR_VNOMBRE_REPRESA, represaBean.getNombreRepresa());
			inputs.put(Constants.PAR_VABREVIATURA_REPRESA, represaBean.getAbreviatura());
			inputs.put(Constants.PAR_NTOPE_MAXIMO, new BigDecimal(represaBean.getStrTopeMaximo()));
			inputs.put(Constants.PAR_NPERTENECEALMACENAMIENTO, Integer.parseInt(represaBean.getPerteneceAlmacenamiento().trim()));
			inputs.put(Constants.PAR_VESTADO_REPRESA, represaBean.getEstado());		
			inputs.put(Constants.PAR_AUSUAREPRESA_MODIFICACION, represaBean.getUsuarioModificacion());
			inputs.put(Constants.PAR_APROGRAMA_REPRESA, represaBean.getPrograma());
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESA, represaBean.getCodigoArea());
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESA, represaBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 *  
	 * Obtiene las represas registrados en la plataforma según su estado.
	 *
	 * @param estado de las represas que se desean listar
	 * @return lista de objetos de tipo RepresaBean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RepresaBean> obtenerRepresas(String estado) throws Exception {
		List<RepresaBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESA).withProcedureName(Constants.SP_LISTA_REPRESA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.LR_PAR_V_ESTADO, Types.VARCHAR),

						new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RepresaBean>() {

									@Override
									public RepresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										RepresaBean record = new RepresaBean();
										record.setIntCodigo(rs.getInt(1)); // "n_codigo"
										record.setNombreRepresa(rs.getString(2)); // "v_nombrerio"
										record.setAbreviatura(rs.getString(3)); // "v_abreviatura"
										record.setPerteneceAlmacenamiento(rs.getInt(4)+"");
										record.setEstado(rs.getString(5)); // "v_estado"
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_V_ESTADO, estado);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<RepresaBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/**
	 *  
	 * Valida si existen represas con una determinada condición.
	 *
	 * @param represaBean objeto del tipo ClimaBean que contiene el registro
	 * @return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ValidaAbreviaturaBean validarAbrevMantRepresa(RepresaBean represaBean) throws Exception {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_REPRESA).withProcedureName(Constants.PRC_VALIDA_ABREV_REPRESA).declareParameters(				

    			new SqlParameter(Constants.PAR_VABREVIATURA, Types.VARCHAR),
    			new SqlOutParameter(Constants.PRC_SALIDA_VAL_ABREV_REPRESA, Types.INTEGER) 

    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		
		params.addValue(Constants.PAR_VABREVIATURA, represaBean.getAbreviatura().trim());
		
		Map<String,Object>  results = caller.execute(params);
		int saliValAbrev = (int) results.get(Constants.PRC_SALIDA_VAL_ABREV_REPRESA);

		ValidaAbreviaturaBean vabrev = new ValidaAbreviaturaBean();
		vabrev.setBolAbreviatura(saliValAbrev > 0);
		
        return vabrev;	
	}


}
