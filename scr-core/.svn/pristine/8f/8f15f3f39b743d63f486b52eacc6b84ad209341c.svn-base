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
import pe.com.sedapal.scr.core.beans.FormulaMuestraBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IFormulaMuestraDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class FormulaMuestraDaoImpl.
 */
@Repository
public class FormulaMuestraDaoImpl implements IFormulaMuestraDao {
	
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulaMuestraDao#obtenerDatosFormulasMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosFormulasMuestra(FormulaMuestraBean formulaMuestraBean, Paginacion paginacion)throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_FORMULA_MUESTRA).withProcedureName(ConstantsLaboratorio.SP_SEARCH_FORMULA_MUESTRA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, Types.INTEGER),
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
										record.add(rs.getString(1)); // "c_ddcatl"
										record.add(rs.getString(2)); // "a_formul"										
										record.add(rs.getInt(3)); // "n_idform"
										record.add(rs.getString(4)); // "a_nomfor"
										record.add(rs.getString(5)); // "a_funcio"
										record.add(rs.getInt(6)); // "n_codgrp"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, formulaMuestraBean.getIntCodGrupo());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulaMuestraDao#obtenerFormulaMuestra(java.lang.Integer)
	 */
	@Override
	public FormulaMuestraBean obtenerFormulaMuestra(Integer id) throws Exception {
		ArrayList ret = null;
		FormulaMuestraBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_FORMULA_MUESTRA).withProcedureName(ConstantsLaboratorio.PRC_GET_FORMULA_MUESTRA)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_FORMULARIO, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormulaMuestraBean>() {
									
									@Override
									public FormulaMuestraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										FormulaMuestraBean formulaMuestraBean = new FormulaMuestraBean();
										formulaMuestraBean.setIntCodFormula(rs.getInt(1)); // "n_idform"
										formulaMuestraBean.setIntCodGrupo(rs.getInt(2)); // "n_codgrp"
										formulaMuestraBean.setIntCodGrupoHide(rs.getInt(2)); // "n_codgrp"
										formulaMuestraBean.setStrCodFormulario(rs.getString(3)); // "a_formul"
										formulaMuestraBean.setStrCodFormularioHide(rs.getString(3)); // "a_formul"
										formulaMuestraBean.setStrNomFormula(rs.getString(4)); // "a_nomfor"
										formulaMuestraBean.setStrNomFuncion(rs.getString(5)); // "a_funcio"

										return formulaMuestraBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_FORMULARIO, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (FormulaMuestraBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulaMuestraDao#grabarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	@Override
	public int grabarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;
		
			lstParamsInput = new ArrayList<SqlParameter>();			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, OracleTypes.NUMBER));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CFORMULARIO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_FORM, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_FUNCION, OracleTypes.VARCHAR));	

			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));	

			lstParamsOutput = new ArrayList<SqlOutParameter>();			
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_FORMULA_MUESTRA,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_FORMULA_MUESTRA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, formulaMuestraBean.getIntCodGrupo());		
			inputs.put(ConstantsLaboratorio.PAR_CFORMULARIO, formulaMuestraBean.getStrCodFormulario());	
			inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_FORM, formulaMuestraBean.getStrNomFormula());	
			inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_FUNCION, formulaMuestraBean.getStrNomFuncion());	
			
			inputs.put(Constants.PAR_CUSUARIO_CREACION, formulaMuestraBean.getStrUsuarioCreacion());	
			inputs.put(Constants.PAR_CUSUARIO_MODI, formulaMuestraBean.getStrUsuarioModificacion());	
			inputs.put(Constants.PAR_CPROGRAMA, formulaMuestraBean.getPrograma());	
			
			Map<String, Integer> outputs = this.execSp.executeSp(inputs);
			intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
			
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulaMuestraDao#actualizarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	@Override
	public void actualizarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		
			lstParamsInput = new ArrayList<SqlParameter>();	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_FORMULARIO, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, OracleTypes.NUMBER));		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CFORMULARIO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_FORM, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_FUNCION, OracleTypes.VARCHAR));	

			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));	

			lstParamsOutput = new ArrayList<SqlOutParameter>();			
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_FORMULA_MUESTRA,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_FORMULA_MUESTRA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(ConstantsLaboratorio.PAR_NID_FORMULARIO, formulaMuestraBean.getIntCodFormula());
			inputs.put(ConstantsLaboratorio.PAR_NCODIGO_GRUPO, formulaMuestraBean.getIntCodGrupo());		
			inputs.put(ConstantsLaboratorio.PAR_CFORMULARIO, formulaMuestraBean.getStrCodFormulario());	
			inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_FORM, formulaMuestraBean.getStrNomFormula());	
			inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_FUNCION, formulaMuestraBean.getStrNomFuncion());	
			
			inputs.put(Constants.PAR_CUSUARIO_CREACION, formulaMuestraBean.getStrUsuarioCreacion());	
			inputs.put(Constants.PAR_CUSUARIO_MODI, formulaMuestraBean.getStrUsuarioModificacion());	
			inputs.put(Constants.PAR_CPROGRAMA, formulaMuestraBean.getPrograma());	
			
			Map<String, Integer> outputs = this.execSp.executeSp(inputs);												
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulaMuestraDao#inactivarFormulaMuestra(pe.com.sedapal.scr.core.beans.FormulaMuestraBean)
	 */
	/* 
	 * Realiza el cambío de estado de un registro de tabla Formulas Maestra
	 * @param rioBean objeto del tipo formulaMuestraBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public void inactivarFormulaMuestra(FormulaMuestraBean formulaMuestraBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_FORMULARIO, OracleTypes.NUMBER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_FORMULA_MUESTRA,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_FORMULA_MUESTRA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			
			inputs.put(ConstantsLaboratorio.PAR_NID_FORMULARIO, formulaMuestraBean.getIntCodFormula());	
			
			this.execSp.executeSp(inputs);
				
	}

}
