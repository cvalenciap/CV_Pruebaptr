/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
import pe.com.sedapal.scr.core.beans.DetalleCatalogoBean;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.ICatalogoDao;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogoDaoImpl.
 */
@Repository
public class CatalogoDaoImpl implements ICatalogoDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CatalogoDaoImpl.class);
	
	
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
	 * @see pe.com.sedapal.scr.core.dao.ICatalogoDao#obtenerDetalleCatalogo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleCatalogoBean> obtenerDetalleCatalogo(String strAbreviatura) throws Exception {
		List<DetalleCatalogoBean> lstRetorno = null; 
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	try {
    		caller.withCatalogName(Constants.PACKAGE_CATALOGO).withProcedureName(Constants.SP_GET_DETAIL).declareParameters(	
    				new SqlParameter(Constants.PAR_CAT_ABREVIATURA, Types.VARCHAR),
					new SqlParameter(Constants.PAR_CAT_ESTADO, Types.INTEGER),
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DetalleCatalogoBean>() {
							@Override
							public DetalleCatalogoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								DetalleCatalogoBean record = new DetalleCatalogoBean();
								record.setStrAbreviatura(rs.getString(1));
								record.setStrValor1(rs.getString(2));
								record.setStrValor2(rs.getString(3));
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
			MapSqlParameterSource params = new MapSqlParameterSource();		
			params.addValue(Constants.PAR_CAT_ABREVIATURA, strAbreviatura);
			params.addValue(Constants.PAR_CAT_ESTADO, Constants.ATTRIBUTE_POSITIVE);
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List<DetalleCatalogoBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);   
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
        return lstRetorno;	
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICatalogoDao#obtenerCatalogo(java.lang.Integer)
	 */
	//catalogo para laboratorio
	@Override
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla){
		return obtenerCatalogo(idTabla, ConstantsLaboratorio.COLUMN_ALPHABETIC_ORDER_DETCATALOGO);
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICatalogoDao#obtenerCatalogo(java.lang.Integer, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> obtenerCatalogo(Integer idTabla,String columnOrder){
		List<SelectItemBean> lstRetorno = null; 
		try {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CATALOGO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_CATALOGO).declareParameters(
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
							@Override
							public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								SelectItemBean record = new SelectItemBean();
								record.setId(String.valueOf(rs.getLong(1)));
								record.setValue(rs.getString(2));
								record.setLabel(rs.getString(3));
								record.setDefecto(rs.getString(4));//descripcion fijo para los combos
								
							    int columns = rs.getMetaData().getColumnCount();
							    if(columns>=6){
							    	record.setCustom(rs.getString(6)==null?"":rs.getString(6));
							    }
								
								return record;
							}
						} )
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantsLaboratorio.PAR_COD_TABLE, idTabla);
		params.addValue(ConstantsLaboratorio.PAR_COLUMN, columnOrder);
		
		Map<String,Object>  results = caller.execute(params);
		lstRetorno =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		} catch (Exception e) {
			LOG.error("",e);
		}
        return lstRetorno;	
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICatalogoDao#obtenerCatalogoTodosNinguno(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> obtenerCatalogoTodosNinguno(Integer idTabla, Integer flag) throws Exception {
		List<SelectItemBean> lstRetornoTmp = new ArrayList<>(); 
		List<SelectItemBean> lstRetorno = new ArrayList<>(); 
		try {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CATALOGO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_CATALOGO).declareParameters(
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
							@Override
							public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								SelectItemBean record = new SelectItemBean();
								record.setValue(String.valueOf(rs.getLong(2)));
								record.setLabel(String.valueOf(rs.getString(3)));
								return record;
							}
						} )
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantsLaboratorio.PAR_COD_TABLE, idTabla);
		params.addValue(ConstantsLaboratorio.PAR_COLUMN, ConstantsLaboratorio.COLUMN_ALPHABETIC_ORDER_DETCATALOGO);
		
		
		Map<String,Object>  results = caller.execute(params);
		
				
		lstRetornoTmp =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
				
		if(flag==1){
			SelectItemBean d = new SelectItemBean();
			d.setLabel("TODOS");
			d.setValue("0");
			lstRetorno.add(d);
			
		}else if(flag==2){
			SelectItemBean d = new SelectItemBean();
			d.setLabel("NINGUNO");
			d.setValue("");
			lstRetorno.add(d);
		}else if(flag==3){
			SelectItemBean d = new SelectItemBean();
			d.setLabel("SELECCIONE");
			d.setValue("");
			lstRetorno.add(d);
		}
		
		for (SelectItemBean selectItemBean : lstRetornoTmp) {
			lstRetorno.add(selectItemBean);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        return lstRetorno;	
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICatalogoDao#obtenerCatalogoSeleccionar(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SelectItemBean> obtenerCatalogoSeleccionar(Integer idTabla, Integer flag) throws Exception {
		List<SelectItemBean> lstRetornoTmp = new ArrayList<>(); 
		List<SelectItemBean> lstRetorno = new ArrayList<>(); 
		try {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CATALOGO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_CATALOGO).declareParameters(
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<SelectItemBean>() {
							@Override
							public SelectItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								SelectItemBean record = new SelectItemBean();
								record.setValue(String.valueOf(rs.getLong(2)));
								record.setLabel(String.valueOf(rs.getString(3)));
								return record;
							}
						} )
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantsLaboratorio.PAR_COD_TABLE, idTabla);
		params.addValue(ConstantsLaboratorio.PAR_COLUMN, ConstantsLaboratorio.COLUMN_ALPHABETIC_ORDER_DETCATALOGO);
		
		Map<String,Object>  results = caller.execute(params);
		
				
		lstRetornoTmp =  (List<SelectItemBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
				
		if(flag==1){
			SelectItemBean d = new SelectItemBean();
			d.setLabel("TODOS");
			d.setValue("0");
			lstRetorno.add(d);
			
		}else if(flag==2){
			SelectItemBean d = new SelectItemBean();
			d.setLabel("Seleccionar");
			d.setValue("0");
			lstRetorno.add(d);
		}
		
		for (SelectItemBean selectItemBean : lstRetornoTmp) {
			lstRetorno.add(selectItemBean);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        return lstRetorno;	
	}



}
