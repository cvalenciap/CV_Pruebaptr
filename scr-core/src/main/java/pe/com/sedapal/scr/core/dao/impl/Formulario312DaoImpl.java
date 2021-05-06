/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import pe.com.sedapal.scr.core.beans.Formulario312;
import pe.com.sedapal.scr.core.beans.Formulario314;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IFormulario312Dao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Formulario312DaoImpl.
 */
@Repository
public class Formulario312DaoImpl implements IFormulario312Dao {
	
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario312Dao#getCalculoFormulario314(java.lang.String, java.lang.String, int)
	 */
	@Override
	public Formulario314 getCalculoFormulario314(String pFechaIni, String pFechaFin, int pTipo) {
		
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM314).withProcedureName(ConstantsLaboratorio.SP_CALCULO_FORM314)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.PAR_FECHA_INICIO, Types.DATE),
						new SqlParameter(ConstantsLaboratorio.PAR_FECHA_FIN, Types.DATE),
						new SqlParameter(ConstantsLaboratorio.P_TIPO_ENSAYO, Types.INTEGER),
						
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<Formulario314>() {

									@SuppressWarnings("unchecked")
									@Override
									public Formulario314 mapRow(ResultSet rs, int rowNum) throws SQLException {
										Formulario314 record = new Formulario314();

										record.setValor1(rs.getString(1)!=null?rs.getString(1):"0.0"); // desviacion
										record.setValor2(rs.getString(2)!=null?rs.getString(2):"0.0"); // media
										record.setValor3(rs.getString(3)!=null?rs.getString(3):"0.0"); // lcs	
										record.setValor4(rs.getString(4)!=null?rs.getString(4):"0.0"); // lcas
										record.setValor5(rs.getString(5)!=null?rs.getString(5):"0.0"); // lcai
										record.setValor6(rs.getString(6)!=null?rs.getString(6):"0.0"); // lci

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECHA_INICIO, Utils.getDateOracle(pFechaIni));
		params.addValue(ConstantsLaboratorio.PAR_FECHA_FIN, Utils.getDateOracle(pFechaFin));
		params.addValue(ConstantsLaboratorio.P_TIPO_ENSAYO, pTipo);

		Map<String, Object> results = caller.execute(params);
		//int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		
		//Result result = new Result();
		Formulario314 ret = (Formulario314) lstRetorno.get(0);


		return ret;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario312Dao#getValoresRSD(java.lang.String, java.lang.String, int)
	 */
	@Override
	public List<Formulario312> getValoresRSD(String pFechaIni, String pFechaFin, int pTipo) throws Exception {
		List<Formulario312> lstRetorno = null; 
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM314).withProcedureName(ConstantsLaboratorio.SP_DATA_GRAFICO_RSD).declareParameters(    					
					new SqlParameter(ConstantsLaboratorio.PAR_FECHA_INICIO, Types.DATE),
					new SqlParameter(ConstantsLaboratorio.PAR_FECHA_FIN, Types.DATE),
					new SqlParameter(ConstantsLaboratorio.P_TIPO_ENSAYO, Types.INTEGER),						
					new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<Formulario312>() {

						@Override
						public Formulario312 mapRow(ResultSet rs, int rowNum) throws SQLException {
							Formulario312 record = new Formulario312();							
							record.setRsd(rs.getDouble(1));
							return record;
						}
					} )						
			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	
	MapSqlParameterSource params = new MapSqlParameterSource();		
	params.addValue(ConstantsLaboratorio.PAR_FECHA_INICIO, Utils.getDateOracle(pFechaIni));
	params.addValue(ConstantsLaboratorio.PAR_FECHA_FIN, Utils.getDateOracle(pFechaFin));
	params.addValue(ConstantsLaboratorio.P_TIPO_ENSAYO, pTipo);		
	
	Map<String,Object>  results = caller.execute(params);		
	lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
	
    return lstRetorno;
    
	}
	
}
