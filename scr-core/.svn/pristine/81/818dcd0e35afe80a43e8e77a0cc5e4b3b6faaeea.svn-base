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
import pe.com.sedapal.scr.core.beans.ActividadArea;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IActividadXAreaDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ActividadXAreaDaoImpl.
 */
@Repository
public class ActividadXAreaDaoImpl implements IActividadXAreaDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IActividadXAreaDao#actualizarActividadXArea(pe.com.sedapal.scr.core.beans.ActividadArea)
	 */
	@Override
	public void actualizarActividadXArea(ActividadArea actividadArea) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		


			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DESPER, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ESTAXA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODARE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODACT, OracleTypes.INTEGER));
			
			
			paramsOutput = new ArrayList<>();
			
			

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ACTIVIDADXAREA+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_UPDATE_ACTIVIDAD, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_DESPER, actividadArea.getDescripcionAsignada());
			inputs.put(ConstantsLaboratorio.PAR_USUMOD, actividadArea.getUsuarioModificacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, actividadArea.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_ESTAXA, actividadArea.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_CODARE, actividadArea.getIdArea());
			inputs.put(ConstantsLaboratorio.PAR_CODACT, actividadArea.getIdActividad());
			
		
			this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IActividadXAreaDao#insertarActividad(pe.com.sedapal.scr.core.beans.ActividadArea)
	 */
	@Override
	public void insertarActividad(ActividadArea actividadArea) {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

			
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODARE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODACT, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DESPER, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ESTAXA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ACTIVIDADXAREA+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INSERT_ACTIVIDAD, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			inputs.put(ConstantsLaboratorio.PAR_CODARE, actividadArea.getIdArea());
			inputs.put(ConstantsLaboratorio.PAR_CODACT, actividadArea.getIdActividad());
			inputs.put(ConstantsLaboratorio.PAR_DESPER, actividadArea.getDescripcionAsignada());
			inputs.put(ConstantsLaboratorio.PAR_ESTAXA, actividadArea.getEstado());
			inputs.put(ConstantsLaboratorio.PAR_USUCRE, actividadArea.getUsuarioCreacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, actividadArea.getPrograma());	
			
			this.execSp.executeSp(inputs);
			
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IActividadXAreaDao#existeActividad(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean existeActividad(Integer idArea,Integer idACtividad) {

		List<SelectItemBean> lstRetorno;
		String countValue = "";
try {
	

    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ACTIVIDADXAREA).withProcedureName(ConstantsLaboratorio.PRC_GET_ACTIVIDADXAREA).declareParameters(
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
		params.addValue(ConstantsLaboratorio.PAR_CODARE, idArea);
		params.addValue(ConstantsLaboratorio.PAR_CODACT, idACtividad);
		
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
	 * @see pe.com.sedapal.scr.core.dao.IActividadXAreaDao#listarActividades(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> listarActividades(Integer idArea){
	
			List<SelectItemBean> lstRetorno = null; 
			
	    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
	    	caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ACTIVIDADXAREA).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ACTIVIDAD).declareParameters(
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
			params.addValue(ConstantsLaboratorio.PAR_CODARE, idArea);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

	        return lstRetorno;	
	}
	
}
