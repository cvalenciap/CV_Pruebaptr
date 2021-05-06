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
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteAlmacenamientoPTAPDaoImpl.
 */
@Repository
public class ReporteAlmacenamientoPTAPDaoImpl implements IReporteAlmacenamientoPTAPDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao#actualizarReporteAlmacenamientoPTAPResul(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void actualizarReporteAlmacenamientoPTAPResul(AnalisisBacteriologicoBean analisisBacteriologicoBean)	throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_INTERPELACI, OracleTypes.VARCHAR));		
		

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPD_ANALBACTRESUL2), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, analisisBacteriologicoBean.getIntId());
		inputs.put(ConstantsLaboratorio.PAR_C_INTERPELACI, analisisBacteriologicoBean.getStrObsInterpelacion());			
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, analisisBacteriologicoBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, analisisBacteriologicoBean.getPrograma());	
		
		this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao#obtenerDatosPTAPResult(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosPTAPResult(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTRFOR299)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),
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
										record.add(rs.getString(1)); // ""
										record.add(rs.getString(2)); // ""
										record.add(rs.getString(3)); // ""
										record.add(rs.getString(4)); // ""
										record.add(rs.getString(5)); // ""										
										record.add(rs.getInt(6)); // ""
										record.add(rs.getString(7)); // ""	
										record.add(rs.getString(8)); // ""	

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, reporteAlmacenamientoPTAPBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao#obtenerDatosPTAPResultTable(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean)
	 */
	@Override
	public ReporteAlmacenamientoPTAPBean obtenerDatosPTAPResultTable(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean) throws Exception {
		ArrayList ret = null;
		ReporteAlmacenamientoPTAPBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_GET_MUESTRFOR299)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, Types.INTEGER),	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteAlmacenamientoPTAPBean>() {
									
									@Override
									public ReporteAlmacenamientoPTAPBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean = new ReporteAlmacenamientoPTAPBean();
										reporteAlmacenamientoPTAPBean.setStrReservorioMenacho(rs.getString(1));
										reporteAlmacenamientoPTAPBean.setStrReservorioVicentelo(rs.getString(2));
										reporteAlmacenamientoPTAPBean.setStrReservorioVicenteloRS(rs.getString(3));
										reporteAlmacenamientoPTAPBean.setStrCamraRegulacion(rs.getString(4));
										reporteAlmacenamientoPTAPBean.setIntIdDetalle(rs.getInt(5));

										return reporteAlmacenamientoPTAPBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_DETALLE, reporteAlmacenamientoPTAPBean.getIntIdDetalle());
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (ReporteAlmacenamientoPTAPBean) ret.get(0);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteAlmacenamientoPTAPDao#actualizarPTAPResultTable(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean)
	 */
	@Override
	public void actualizarPTAPResultTable(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESMEN, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESVIC, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CREVIRS, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESRED, OracleTypes.VARCHAR));	
		

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPD_MUESTRFOR299), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, reporteAlmacenamientoPTAPBean.getIntIdDetalle());
		inputs.put(ConstantsLaboratorio.PAR_CRESMEN, reporteAlmacenamientoPTAPBean.getStrReservorioMenacho());		
		inputs.put(ConstantsLaboratorio.PAR_CRESVIC, reporteAlmacenamientoPTAPBean.getStrReservorioVicentelo());	
		inputs.put(ConstantsLaboratorio.PAR_CREVIRS, reporteAlmacenamientoPTAPBean.getStrReservorioVicenteloRS());	
		inputs.put(ConstantsLaboratorio.PAR_CRESRED, reporteAlmacenamientoPTAPBean.getStrCamraRegulacion());	
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, reporteAlmacenamientoPTAPBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, reporteAlmacenamientoPTAPBean.getPrograma());	
		
		this.execSp.executeSp(inputs);
		
	}
	
}
