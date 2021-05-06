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
import pe.com.sedapal.scr.core.beans.TablaPoissonBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.ITablaPoissonDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class TablaPoissonDaoImpl.
 */
@Repository
public class TablaPoissonDaoImpl implements ITablaPoissonDao{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(Constants.PACKAGE);
	
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
	 * @see pe.com.sedapal.scr.core.dao.ITablaPoissonDao#obtenerDatosPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosPoisson(TablaPoissonBean tablaPoissonBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_TABLA_POISSON).withProcedureName(ConstantsLaboratorio.SP_SEARCH_TABLA_POISSON)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA, Types.DOUBLE),
						// parametros de arquitectura
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
										//record.add(rs.getString(1)); // "a_usucre"
										//record.add(rs.getString(2)); // "a_feccre"
										//record.add(rs.getString(3)); // "a_usumod"
										//record.add(rs.getString(4)); // "a_fecmod"
										//record.add(rs.getString(5)); // "a_nomprg"										
										record.add(rs.getDouble(1)); // "n_codlam"
										record.add(rs.getDouble(2)); // "n_codx"
										record.add(rs.getString(5)); // "n_valpoi"
										record.add(rs.getDouble(4)); // "n_codpoi"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA, Double.parseDouble(tablaPoissonBean.getIntCodLambda()));
		
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

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITablaPoissonDao#obtenerTablaPoisson(java.lang.Integer)
	 */
	@Override
	public TablaPoissonBean obtenerTablaPoisson(Integer id) throws Exception {
		ArrayList ret = null;
		TablaPoissonBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_TABLA_POISSON).withProcedureName(ConstantsLaboratorio.PRC_GET_TABLA_POISSON)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_POISSON, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TablaPoissonBean>() {
									
									@Override
									public TablaPoissonBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TablaPoissonBean tablaPoissonBean = new TablaPoissonBean();
										tablaPoissonBean.setIntCodPoisson(rs.getInt(4)); // "n_codpoi"
										tablaPoissonBean.setIntCodLambda(rs.getString(1)); // "n_codlam"
										tablaPoissonBean.setStrCodIgx(rs.getString(2)); // "n_codx"
										tablaPoissonBean.setStrValorPoisson(rs.getString(3)); // "n_valpoi"
										tablaPoissonBean.setIntCodLambdaHide(rs.getString(1));
										tablaPoissonBean.setStrCodIgxHide(rs.getString(2));

										return tablaPoissonBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_POISSON, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (TablaPoissonBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITablaPoissonDao#grabarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	@Override
	public int grabarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		
			lstParamsInput = new ArrayList<SqlParameter>();			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA, OracleTypes.NUMBER));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCODIGO_X, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));	

			lstParamsOutput = new ArrayList<SqlOutParameter>();			
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TABLA_POISSON,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_TABLA_POISSON), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			String valorLambda = tablaPoissonBean.getIntCodLambda();
			LOG.info("valorLambda " + valorLambda);
			//valorLambda.replace(".", ",")
			//new NUMBER(valorLambda,2)
			
			inputs.put(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA, new Double(valorLambda));	
			inputs.put(ConstantsLaboratorio.PAR_CCODIGO_X, tablaPoissonBean.getStrCodIgx());		
			inputs.put(ConstantsLaboratorio.PAR_CVALOR, tablaPoissonBean.getStrValorPoisson());	
			inputs.put(Constants.PAR_CUSUARIO_CREACION, tablaPoissonBean.getUsuarioCreacion());
			inputs.put(Constants.PAR_CUSUARIO_MODI, tablaPoissonBean.getUsuarioModificacion());
			inputs.put(Constants.PAR_CPROGRAMA, tablaPoissonBean.getPrograma());
			

			Map<String, Integer> outputs = this.execSp.executeSp(inputs);
			int intCodigoCarga = outputs.get(Constants.PAR_OUT_RETURN);
			return intCodigoCarga;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITablaPoissonDao#actualizarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	@Override
	public void actualizarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		
			lstParamsInput = new ArrayList<SqlParameter>();			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA, OracleTypes.NUMBER));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCODIGO_X, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));	

			lstParamsOutput = new ArrayList<SqlOutParameter>();			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TABLA_POISSON,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_TABLA_POISSON), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantsLaboratorio.PAR_NCODIGO_LAMBDA,new Double(tablaPoissonBean.getIntCodLambda()));		
			inputs.put(ConstantsLaboratorio.PAR_CCODIGO_X, tablaPoissonBean.getStrCodIgx());		
			inputs.put(ConstantsLaboratorio.PAR_CVALOR, tablaPoissonBean.getStrValorPoisson());	
			inputs.put(Constants.PAR_CUSUARIO_MODI, tablaPoissonBean.getUsuarioModificacion());
			inputs.put(Constants.PAR_CPROGRAMA, tablaPoissonBean.getPrograma());	

			this.execSp.executeSp(inputs);
		
		
	}

	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITablaPoissonDao#inactivarTablaPoisson(pe.com.sedapal.scr.core.beans.TablaPoissonBean)
	 */
	/* 
	 * Realiza el cambío de estado de un registro de tabla poisson
	 * @param rioBean objeto del tipo TablaPoissonBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	@Override
	public void inactivarTablaPoisson(TablaPoissonBean tablaPoissonBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_POISSON, OracleTypes.NUMBER));
			
			paramsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TABLA_POISSON,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_TABLA_POISSON), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			
			inputs.put(ConstantsLaboratorio.PAR_NCODIGO_POISSON, tablaPoissonBean.getIntCodPoisson());	
			
			inputs.put(Constants.PAR_CUSUARIO_MODI, tablaPoissonBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_CPROGRAMA, tablaPoissonBean.getPrograma());

			this.execSp.executeSp(inputs);
		
		
	}

}
