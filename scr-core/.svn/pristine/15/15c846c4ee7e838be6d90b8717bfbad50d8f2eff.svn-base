/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
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
import pe.com.sedapal.scr.core.beans.ManiobraBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IManiobraDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ManiobraDaoImpl.
 */
@Repository
public class ManiobraDaoImpl implements IManiobraDao {
	
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
	 * Método que permite obtener el listado de maniobras.
	 *
	 * @param maniobraBean the maniobra bean
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result buscarManiobras(ManiobraBean maniobraBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_MANIOBRAS).withProcedureName(Constants.SP_BUSCA_MANIOBRAS)
				.declareParameters(	
						new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, Types.INTEGER),
						
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
										
										record.add(rs.getString(1).toUpperCase()); // c_estado
										record.add(rs.getString(2).toUpperCase()); // c_nomrep										
										record.add(rs.getLong(3)==1?"REGULADO":rs.getLong(3)==2?"NATURAL":"DESCARGA"); // n_tipman										
										record.add(rs.getDouble(4)); // n_apeini
										record.add(rs.getDouble(5)); // n_apefin										
										record.add(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(6))); // d_fecreg																													
										record.add(rs.getString(7)); // c_hora																				
										record.add(rs.getString(8).toUpperCase()); // a_usucre
										record.add(rs.getString(9)); // a_feccre
										record.add(rs.getString(10).toUpperCase()); // a_usumod
										record.add(rs.getString(11)); // a_fecmod
										record.add(rs.getLong(12)); // n_codigo
										record.add(rs.getLong(13)); // n_codrep										

										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_NCODIGO_REPRESAS, maniobraBean.getRepresas().getIntCodigo());

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
	 * Realiza el registro de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void registrarManiobra(ManiobraBean maniobraBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NTIPO_MANIOBRA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_CHORA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_NAPERTURA_INICIAL, OracleTypes.NUMERIC));	
			paramsInput.add(new SqlParameter(Constants.PAR_NAPERTURA_FINAL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_CESTADO, OracleTypes.VARCHAR));			
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAMANIOBRAS_CREACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_MANIOBRAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_MANIOBRAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_MANIOBRAS,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_MANIOBRAS), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			
			inputs = new HashMap<String, Object>();			
			
			inputs.put(Constants.PAR_NTIPO_MANIOBRA, maniobraBean.getIntTipoManiobra());
			inputs.put(Constants.PAR_CHORA, maniobraBean.getStrHora());	
			inputs.put(Constants.PAR_NAPERTURA_INICIAL, maniobraBean.getDblAperturaInicial());
			inputs.put(Constants.PAR_NAPERTURA_FINAL, maniobraBean.getDblAperturaFinal());
			inputs.put(Constants.PAR_NCODIGO_REPRESAS, Integer.parseInt(maniobraBean.getStrCodigoRepresas()));
			inputs.put(Constants.PAR_CESTADO, maniobraBean.getStrEstado());						
			
			inputs.put(Constants.PAR_AUSUAMANIOBRAS_CREACION, maniobraBean.getUsuarioCreacion());					
			inputs.put(Constants.PAR_APROGRAMA_MANIOBRAS, maniobraBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_MANIOBRAS, maniobraBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, maniobraBean.getCodigoSistema());		

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Realiza la modificación de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarManiobra(ManiobraBean maniobraBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_MANIOBRAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_NTIPO_MANIOBRA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_CHORA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_NAPERTURA_INICIAL, OracleTypes.NUMERIC));	
			paramsInput.add(new SqlParameter(Constants.PAR_NAPERTURA_FINAL, OracleTypes.NUMERIC));			
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUMANIOBRAS_MODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_MANIOBRAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_MANIOBRAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_MANIOBRAS+Constants.P_SEPARADOR+Constants.SP_UPDATE_MANIOBRAS, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_MANIOBRAS, Integer.parseInt(maniobraBean.getCodigo()));
			inputs.put(Constants.PAR_NTIPO_MANIOBRA, maniobraBean.getIntTipoManiobra());
			inputs.put(Constants.PAR_CHORA, maniobraBean.getStrHora());	
			inputs.put(Constants.PAR_NAPERTURA_INICIAL, maniobraBean.getDblAperturaInicial());
			inputs.put(Constants.PAR_NAPERTURA_FINAL, maniobraBean.getDblAperturaFinal());			
			
			inputs.put(Constants.PAR_AUSUMANIOBRAS_MODIFICACION, maniobraBean.getUsuarioModificacion());					
			inputs.put(Constants.PAR_APROGRAMA_MANIOBRAS, maniobraBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_MANIOBRAS, maniobraBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, maniobraBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}

	/**
	 * Realiza el cambio de estado de maniobra.
	 *
	 * @param maniobraBean objeto del tipo ManiobraBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarManiobra(ManiobraBean maniobraBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_MANIOBRAS, OracleTypes.INTEGER));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUMANIOBRAS_MODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_MANIOBRAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_MANIOBRAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_MANIOBRAS,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANIOBRAS),
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_MANIOBRAS, maniobraBean.getIntCodigo());	
			
			inputs.put(Constants.PAR_AUSUMANIOBRAS_MODIFICACION, maniobraBean.getUsuarioModificacion());					
			inputs.put(Constants.PAR_APROGRAMA_MANIOBRAS, maniobraBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_MANIOBRAS, maniobraBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_MANIOBRAS, maniobraBean.getCodigoSistema()); 

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}

	/**
	 *  
	 * Obtiene la lista de maniobras activas por represas (ordenadas ascendemente por hora).
	 *
	 * @param codRepresas Identificador del registro de represas
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ManiobraBean> obtenerManiobrasActivas(Integer codRepresas) throws Exception {
		ArrayList<ManiobraBean> ret = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		try{
			caller.withCatalogName(Constants.PACKAGE_MANIOBRAS).withProcedureName(Constants.SP_GET_MANIOBRAS_ACTIVAS)
			.declareParameters(new SqlParameter(Constants.PAR_NCODREPRESAS, Types.INTEGER), new SqlOutParameter(
					Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ManiobraBean>() {
						@Override
						public ManiobraBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ManiobraBean record = new ManiobraBean();
							record.setIntCodigo(rs.getInt(1)); // n_codigo
							record.setIntTipoManiobra(rs.getInt(2)); // n_tipman
							record.setStrHora(rs.getString(3)); // c_hora
							record.setDblAperturaInicial(Double.parseDouble(new BigDecimal(rs.getDouble(4) + "").stripTrailingZeros().toPlainString())); // n_apeini
							record.setDblAperturaFinal(Double.parseDouble(new BigDecimal(rs.getDouble(5) + "").stripTrailingZeros().toPlainString())); // n_apefin
							record.setStrCodigoRepresas(rs.getInt(6) + ""); // n_codrep
							record.setStrEstado(rs.getString(7)); // c_estado
							return record;
						}

					}))
			.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(Constants.PAR_NCODREPRESAS, codRepresas);

			Map<String, Object> results = caller.execute(params);
			ret = (ArrayList<ManiobraBean>) results.get(Constants.PAR_OUT_CURSOR);

		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ret;
	}
}
