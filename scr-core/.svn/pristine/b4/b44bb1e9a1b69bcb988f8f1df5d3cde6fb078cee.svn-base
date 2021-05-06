/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.ItemPlanOperativo;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ParametroXSubactividadDaoImpl.
 */
@Repository
public class ParametroXSubactividadDaoImpl implements IParametroXSubactividadDao{

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ActividadXAreaDaoImpl.class);
	
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
	 * @see pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao#actualizarParametroXSubactividad(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void actualizarParametroXSubactividad(ItemPlanOperativo parametroXSubactividad) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_STATUS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CSUBXA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODPAR, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_PARAMETROXSUBACT+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_UPDATE_PARAMETROXSUBACT, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_USUMOD, parametroXSubactividad.getUsuarioModificacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, parametroXSubactividad.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_STATUS, parametroXSubactividad.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_CSUBXA, parametroXSubactividad.getIdMaster());
			inputs.put(ConstantsLaboratorio.PAR_CODPAR, parametroXSubactividad.getIdDetalle());
		
			this.execSp.executeSp(inputs);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao#insertarParametroXSubactividad(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void insertarParametroXSubactividad(ItemPlanOperativo actividadArea) {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
			
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CSUBXA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODPAR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_STATUS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_PARAMETROXSUBACT+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INSERT_PARAMETROXSUBACT, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_CSUBXA, actividadArea.getIdMaster());
			inputs.put(ConstantsLaboratorio.PAR_CODPAR, actividadArea.getIdDetalle());
			inputs.put(ConstantsLaboratorio.PAR_STATUS, actividadArea.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_USUCRE, actividadArea.getUsuarioCreacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, actividadArea.getPrograma());	
			
			this.execSp.executeSp(inputs);
						
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao#existeParametroXSubactividad(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean existeParametroXSubactividad(Integer idSubactividadXActividad, Integer idParametro) {

		List<SelectItemBean> lstRetorno;
		String countValue = "";
		try {

    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_PARAMETROXSUBACT).withProcedureName(ConstantsLaboratorio.PRC_GET_PARAMETROXSUBACT).declareParameters(
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
							@Override
							public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								SelectItemBean record = new SelectItemBean();
								record.setValue(String.valueOf(rs.getLong(1)));
								return record;
							}
						} )
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantsLaboratorio.PAR_CSUBXA, idSubactividadXActividad);
		params.addValue(ConstantsLaboratorio.PAR_CODPAR, idParametro);
		
		Map<String,Object>  results = caller.execute(params);
		
		lstRetorno =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
	    countValue = lstRetorno.get(0).getValue();
	    
} catch (Exception e) {
	e.printStackTrace();
	LOG.info("",e);

}
		return Integer.parseInt(countValue)>0;

		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao#listarSubActividades(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemPlanOperativo> listarSubActividades(Integer idActividad){
	
			List<ItemPlanOperativo> lstRetorno = null; 
	    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
	    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_SUBACTIVIDADXGRUPO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_SUBACTIVIDAD).declareParameters(
							new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ItemPlanOperativo>() {
								@Override
								public ItemPlanOperativo mapRow(ResultSet rs, int rowNum) throws SQLException {
									ItemPlanOperativo record = new ItemPlanOperativo();
									record.setIdDetalle(rs.getLong(1));
									record.setLabel(rs.getString(2));
									record.setValue(rs.getString(3));
									record.setIdMaster(rs.getLong(4));
									return record;
								}
							} )
	    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	    	
			MapSqlParameterSource params = new MapSqlParameterSource();	
			params.addValue(ConstantsLaboratorio.PAR_COAXAR, idActividad);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<ItemPlanOperativo>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

	        return lstRetorno;	
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IParametroXSubactividadDao#listarParametros(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> listarParametros(Integer idGrupo){
	
			List<SelectItemBean> lstRetorno = null; 
	    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
	    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_PARAMETROXSUBACT).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_PARAMETRO).declareParameters(
							new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
								@Override
								public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
									SelectItemBean record = new SelectItemBean();
									record.setId(String.valueOf(rs.getLong(1)));
									record.setLabel(rs.getString(2));
									record.setValue(rs.getString(3));
									record.setCustom(rs.getString(4));
									return record;
								}
							} )
	    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	    	
			MapSqlParameterSource params = new MapSqlParameterSource();	
			params.addValue(ConstantsLaboratorio.PAR_CSUBXA, idGrupo);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

	        return lstRetorno;	
	}
	
	
}

	

