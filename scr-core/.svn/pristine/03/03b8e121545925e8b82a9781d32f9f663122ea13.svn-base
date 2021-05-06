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
import pe.com.sedapal.scr.core.beans.CargaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.ICargaDao;

// TODO: Auto-generated Javadoc
/**
 * The Class CargaDaoImpl.
 */
@Repository
public class CargaDaoImpl implements ICargaDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/**
	 * Método que permite obtener la última carga automática de caudales.
	 *
	 * @return the carga bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CargaBean que contiene los datos de la carga
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public CargaBean obtenerUltimaCarga() throws Exception {
		List lstRetorno = null; 
		CargaBean result=null;
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	try {
    		caller.withCatalogName(Constants.PACKAGE_CARGA).withProcedureName(Constants.SP_FIND_LAST_LOAD).declareParameters(				
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<CargaBean>() {
							@Override
							public CargaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								CargaBean record = new CargaBean();
								record.setLonCodigo(rs.getLong(1));
								record.setStrNombre(rs.getString(2));
								record.setStrNombreArchivo(rs.getString(3));
								record.setStrRutaArchivo(rs.getString(4)); 
								record.setStrFechaRegistro(rs.getString(5)); 
								record.setStrEstado(rs.getString(6)); 
								record.setStrDescError(rs.getString(7)); 
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
			MapSqlParameterSource params = new MapSqlParameterSource();		
			
			Map<String,Object>  results = caller.execute(params);
			lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);   
			if(lstRetorno!=null && lstRetorno.size()>0){
				result = (CargaBean)lstRetorno.get(0);
			}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
        return result;	
	}

	/**
	 * Método que permite obtener el listado de cargas.
	 *
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result buscarCargas(Paginacion paginacion) throws Exception {
		List lstRetorno = null; 
		
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CARGA).withProcedureName(Constants.SP_FIND_LOAD).declareParameters(
						new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER ),
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ArrayList>() {
							
							@SuppressWarnings("unchecked")
							@Override
							public ArrayList mapRow(ResultSet rs, int rowNum) throws SQLException {
								ArrayList record = new ArrayList();
								record.add("");
								record.add(rs.getString(1)); //"v_nomcar"
								record.add(rs.getString(2)); //"d_fechor"
								record.add(rs.getString(3)); //"tipo proceso"
								record.add(rs.getString(4)); //"v_ruta || v_nomarc"
								record.add(rs.getString(5)); //"v_estcar"		 						
								record.add(rs.getLong(6)+""); //"n_codigo"
								record.add(rs.getString(7)); //v_estcar
								record.add(rs.getString(8)); //v_descri
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		
		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());
		
		Map<String,Object>  results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);   
		
		Result result = new Result();
		result.setData(lstRetorno);
		result.setRecords((long)quantity);
		
        return result;	
	}

}
