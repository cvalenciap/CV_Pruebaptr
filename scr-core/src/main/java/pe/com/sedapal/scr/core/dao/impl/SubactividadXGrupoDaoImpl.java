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
import pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class SubactividadXGrupoDaoImpl.
 */
@Repository
public class SubactividadXGrupoDaoImpl implements ISubactividadXGrupoDao{

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
	 * @see pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao#actualizarSubactividadXGrupo(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void actualizarSubactividadXGrupo(ItemPlanOperativo subactividadXGrupo) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ESTSXG, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COAXAR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODSUB, OracleTypes.INTEGER));
			
			
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_SUBACTIVIDADXGRUPO+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_UPDATE_SUBACTIVIDADXGRUPO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_USUMOD, subactividadXGrupo.getUsuarioModificacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, subactividadXGrupo.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_ESTSXG, subactividadXGrupo.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_COAXAR, subactividadXGrupo.getIdMaster());
			inputs.put(ConstantsLaboratorio.PAR_CODSUB, subactividadXGrupo.getIdDetalle());
		
			this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao#insertarSubactividadXGrupo(pe.com.sedapal.scr.core.beans.ItemPlanOperativo)
	 */
	@Override
	public void insertarSubactividadXGrupo(ItemPlanOperativo actividadArea) {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
			
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COAXAR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODSUB, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ESTSXG, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_SUBACTIVIDADXGRUPO+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INSERT_SUBACTIVIDADXGRUPO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_COAXAR, actividadArea.getIdMaster());
			inputs.put(ConstantsLaboratorio.PAR_CODSUB, actividadArea.getIdDetalle());
			inputs.put(ConstantsLaboratorio.PAR_ESTSXG, actividadArea.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_USUCRE, actividadArea.getUsuarioCreacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, actividadArea.getPrograma());	
			
			this.execSp.executeSp(inputs);
						
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao#existeSubactividadXGrupo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean existeSubactividadXGrupo(Integer idGrupo, Integer idSubactividad) {

		List<SelectItemBean> lstRetorno;
		String countValue = "";
		try {
	

    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_SUBACTIVIDADXGRUPO).withProcedureName(ConstantsLaboratorio.PRC_GET_SUBACTIVIDADXGRUPO).declareParameters(
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
		params.addValue(ConstantsLaboratorio.PAR_COAXAR, idGrupo);
		params.addValue(ConstantsLaboratorio.PAR_CODSUB, idSubactividad);
		
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
	 * @see pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao#listarSubactividades(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> listarSubactividades(Integer idGrupo){
	
			List<SelectItemBean> lstRetorno = null; 
	    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
	    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_SUBACTIVIDADXGRUPO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_SUBACTIVIDAD).declareParameters(
							new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
								@Override
								public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
									SelectItemBean record = new SelectItemBean();
									record.setId(String.valueOf(rs.getLong(1)));
									record.setLabel(rs.getString(2));
									record.setValue(rs.getString(3));
									return record;
								}
							} )
	    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	    	
			MapSqlParameterSource params = new MapSqlParameterSource();	
			params.addValue(ConstantsLaboratorio.PAR_COAXAR, idGrupo);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

	        return lstRetorno;	
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ISubactividadXGrupoDao#listarActividades(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemPlanOperativo> listarActividades(Integer idArea){
	
			List<ItemPlanOperativo> lstRetorno = null; 
	    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
	    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ACTIVIDADXAREA).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ACTIVIDAD).declareParameters(
							new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ItemPlanOperativo>() {
								@Override
								public ItemPlanOperativo mapRow(ResultSet rs, int rowNum) throws SQLException {
									ItemPlanOperativo record = new ItemPlanOperativo();
									record.setIdDetalle(rs.getLong(1));
									record.setLabel(rs.getString(2));
									record.setValue(rs.getString(3));
									record.setIdMaster(rs.getLong(5));
									record.setCustom(rs.getString(6)==null?"":rs.getString(6));
									return record;
								}
							} )
	    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	    	
			MapSqlParameterSource params = new MapSqlParameterSource();	
			params.addValue(ConstantsLaboratorio.PAR_CODARE, idArea);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<ItemPlanOperativo>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

	        return lstRetorno;	
	}
	
	
}

	

