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
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPSecondBean;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPThreeBean;
import pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class AlmacenamientoPTAPDaoImpl.
 */
@Repository
public class AlmacenamientoPTAPDaoImpl implements IAlmacenamientoPTAPDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#obtenerDatosAlmacenamientoPTAP(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosAlmacenamientoPTAP(AlmacenamientoPTAPBean almacenamientoPTAPBean, Paginacion paginacion)	throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_SEARCH_ALMACENA_PTAP)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, Types.VARCHAR),
						new SqlParameter(ConstantsLaboratorio.PAR_NID_FORM, Types.INTEGER),
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
										record.add(Utils.formatFechaStringddMMYYY( rs.getDate(1))); // "c_nomequ"
										record.add(rs.getString(2)); // "c_codequ"
										record.add(Utils.formatFechaStringddMMYYY( rs.getDate(3))); // "c_marequ"
										record.add(rs.getString(4)); // "c_modequ"
										record.add(Utils.formatFechaStringddMMYYY( rs.getDate(5))); // "c_serieq"
										record.add(rs.getString(6)); // "c_nomequ"
										record.add(rs.getString(7)); // "c_nomequ"
										record.add(rs.getString(8)); // "c_nomequ"
										record.add(rs.getString(9)); // "c_nomequ"
										record.add(rs.getInt(10)); // "n_cof292"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, almacenamientoPTAPBean.getStrFechaMuestreo());
		params.addValue(ConstantsLaboratorio.PAR_NID_FORM, almacenamientoPTAPBean.getIntIdForm());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#grabarMuestraForm21(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean)
	 */
	@Override
	public int grabarMuestraForm21(AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTRFORM21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, almacenamientoPTAPFirstBean.getStrDescMuestra());	
				
		inputs.put(Constants.PAR_CUSUARIO_CREACION, almacenamientoPTAPFirstBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, almacenamientoPTAPFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, almacenamientoPTAPFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#grabarReporteAlmacenamientoPTAP(pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean)
	 */
	@Override
	public int grabarReporteAlmacenamientoPTAP(ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLIMITEMAX, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLIMITECAL, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTRFOR299), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, reporteAlmacenamientoPTAPBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, reporteAlmacenamientoPTAPBean.getStrDesMuestra());	
		inputs.put(ConstantsLaboratorio.PAR_CLIMITEMAX, reporteAlmacenamientoPTAPBean.getStrLimiteMaximo());
		inputs.put(ConstantsLaboratorio.PAR_CLIMITECAL, reporteAlmacenamientoPTAPBean.getStrLimiteCalidad());
				
		inputs.put(Constants.PAR_CUSUARIO_CREACION, reporteAlmacenamientoPTAPBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, reporteAlmacenamientoPTAPBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, reporteAlmacenamientoPTAPBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#obtenerDatosTableFirst(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosTableFirst(AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTRFORM21)
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
										record.add((rs.getString(1)!=null && (!"".equals(rs.getString(1).trim())))?rs.getString(1):""); // ""
										record.add((rs.getString(2)!=null && (!"".equals(rs.getString(2).trim())))?rs.getString(2):""); // ""
										record.add((rs.getString(3)!=null && (!"".equals(rs.getString(3).trim())))?rs.getString(3):""); // ""
										record.add((rs.getString(4)!=null && (!"".equals(rs.getString(4).trim())))?rs.getString(4):""); // ""
										record.add((rs.getString(5)!=null && (!"".equals(rs.getString(5).trim())))?rs.getString(5):""); // ""
										record.add((rs.getString(6)!=null && (!"".equals(rs.getString(6).trim())))?rs.getString(6):""); // ""
										record.add((rs.getString(7)!=null && (!"".equals(rs.getString(7).trim())))?rs.getString(7):""); // ""
										record.add((rs.getString(8)!=null && (!"".equals(rs.getString(8).trim())))?rs.getString(8):""); // ""
										record.add((rs.getString(9)!=null && (!"".equals(rs.getString(9).trim())))?rs.getString(9):""); // ""
										record.add((rs.getString(10)!=null && (!"".equals(rs.getString(10).trim())))?rs.getString(10):""); // ""
										record.add((rs.getString(11)!=null && (!"".equals(rs.getString(11).trim())))?rs.getString(11):""); // ""
										record.add((rs.getString(12)!=null && (!"".equals(rs.getString(12).trim())))?rs.getString(12):""); // ""
										record.add((rs.getString(13)!=null && (!"".equals(rs.getString(13).trim())))?rs.getString(13):""); // ""
										record.add((rs.getString(14)!=null && (!"".equals(rs.getString(14).trim())))?rs.getString(14):""); // ""
										record.add((rs.getString(15)!=null && (!"".equals(rs.getString(15).trim())))?rs.getString(15):""); // ""
										record.add((rs.getString(16)!=null && (!"".equals(rs.getString(16).trim())))?rs.getString(16):""); // ""
										record.add((rs.getString(17)!=null && (!"".equals(rs.getString(17).trim())))?rs.getString(17):""); // ""
										record.add(rs.getInt(18)); // ""
										record.add(rs.getInt(19)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPFirstBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#obtenerTableFirst(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean)
	 */
	@Override
	public AlmacenamientoPTAPFirstBean obtenerTableFirst(AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean) throws Exception {
		ArrayList ret = null;
		AlmacenamientoPTAPFirstBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_GET_MUESTRFORM21)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),		
						new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, Types.INTEGER),	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AlmacenamientoPTAPFirstBean>() {
									
									@Override
									public AlmacenamientoPTAPFirstBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean = new AlmacenamientoPTAPFirstBean();
										almacenamientoPTAPFirstBean.setStrDescMuestra(rs.getString(1));										
										almacenamientoPTAPFirstBean.setStrTurntu(rs.getString(2));
										almacenamientoPTAPFirstBean.setStrClrlib(rs.getString(3));
										almacenamientoPTAPFirstBean.setStrAgelfi(rs.getString(4));
										almacenamientoPTAPFirstBean.setStrCoagel(rs.getString(5));
										almacenamientoPTAPFirstBean.setStrAgmfcv(rs.getString(6));
										almacenamientoPTAPFirstBean.setStrColamf(rs.getString(7));
										almacenamientoPTAPFirstBean.setStrAg2aml(rs.getString(8));
										almacenamientoPTAPFirstBean.setStrAg2adl(rs.getString(9));
										almacenamientoPTAPFirstBean.setStrCola2a(rs.getString(10));
										almacenamientoPTAPFirstBean.setStrAgmpvf(rs.getString(11));
										almacenamientoPTAPFirstBean.setStrColapa(rs.getString(12));
										almacenamientoPTAPFirstBean.setStrColito(rs.getString(13));										
										almacenamientoPTAPFirstBean.setStrColter(rs.getString(14));
										almacenamientoPTAPFirstBean.setStrEscoli(rs.getString(15));	
										almacenamientoPTAPFirstBean.setStrBahete(rs.getString(16));
										almacenamientoPTAPFirstBean.setStrPseser(rs.getString(17));
										almacenamientoPTAPFirstBean.setIntIdDetalle(rs.getInt(18));
										almacenamientoPTAPFirstBean.setIntIdCabecera(rs.getInt(19));

										return almacenamientoPTAPFirstBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPFirstBean.getIntIdCabecera());
		params.addValue(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPFirstBean.getIntIdDetalle());
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (AlmacenamientoPTAPFirstBean) ret.get(0);

		return result;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#updateMuestraForm21(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean)
	 */
	@Override
	public int updateMuestraForm21(AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_TURNTU, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_CLRLIB, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_AGELFI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_COAGEL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_AGMFCV, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_COLAMF, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_AG2AML, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_AG2ADL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_COLA2A, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_AGMPVF, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_C_COLAPA, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_MUESTRFORM21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPFirstBean.getIntIdDetalle());	
		
		inputs.put(ConstantsLaboratorio.PAR_C_TURNTU, almacenamientoPTAPFirstBean.getStrTurntu());
		inputs.put(ConstantsLaboratorio.PAR_C_CLRLIB, almacenamientoPTAPFirstBean.getStrClrlib());
		inputs.put(ConstantsLaboratorio.PAR_C_AGELFI, almacenamientoPTAPFirstBean.getStrAgelfi());
		inputs.put(ConstantsLaboratorio.PAR_C_COAGEL, almacenamientoPTAPFirstBean.getStrCoagel());
		inputs.put(ConstantsLaboratorio.PAR_C_AGMFCV, almacenamientoPTAPFirstBean.getStrAgmfcv());
		inputs.put(ConstantsLaboratorio.PAR_C_COLAMF, almacenamientoPTAPFirstBean.getStrColamf());
		inputs.put(ConstantsLaboratorio.PAR_C_AG2AML, almacenamientoPTAPFirstBean.getStrAg2aml());
		inputs.put(ConstantsLaboratorio.PAR_C_AG2ADL, almacenamientoPTAPFirstBean.getStrAg2adl());
		inputs.put(ConstantsLaboratorio.PAR_C_COLA2A, almacenamientoPTAPFirstBean.getStrCola2a());
		inputs.put(ConstantsLaboratorio.PAR_C_AGMPVF, almacenamientoPTAPFirstBean.getStrAgmpvf());
		inputs.put(ConstantsLaboratorio.PAR_C_COLAPA, almacenamientoPTAPFirstBean.getStrColapa());
				
		inputs.put(Constants.PAR_CUSUARIO_MODI, almacenamientoPTAPFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, almacenamientoPTAPFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#obtenerDatosTableSecond(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPSecondBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosTableSecond(AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTELEF21)
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
										record.add(rs.getString(6)); // ""
										record.add(rs.getString(7)); // ""
										record.add(rs.getString(8)); // ""
										record.add(rs.getString(9)); // ""
										record.add(rs.getInt(10)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPSecondBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#grabarTableSecond(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPSecondBean)
	 */
	@Override
	public int grabarTableSecond(AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
	   lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCRI, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLONI, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CGASCLT, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CGASCLV, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CGSEC24, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CFECMUG, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLTOT, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLTER, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CESCOLI, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTELEF21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPSecondBean.getIntIdDetalle());
		inputs.put(ConstantsLaboratorio.PAR_CDESCRI, almacenamientoPTAPSecondBean.getStrDescMuestra());                
      inputs.put(ConstantsLaboratorio.PAR_CCOLONI, almacenamientoPTAPSecondBean.getStrColoni());                
      inputs.put(ConstantsLaboratorio.PAR_CGASCLT, almacenamientoPTAPSecondBean.getStrGasclt());                
      inputs.put(ConstantsLaboratorio.PAR_CGASCLV, almacenamientoPTAPSecondBean.getStrGasclv());                
      inputs.put(ConstantsLaboratorio.PAR_CGSEC24, almacenamientoPTAPSecondBean.getStrGsec24());                
      inputs.put(ConstantsLaboratorio.PAR_CFECMUG, almacenamientoPTAPSecondBean.getStrFecmug());                
      inputs.put(ConstantsLaboratorio.PAR_CCOLTOT, !almacenamientoPTAPSecondBean.getStrColtot().trim().equals("")?almacenamientoPTAPSecondBean.getStrColtot(): " ");                
      inputs.put(ConstantsLaboratorio.PAR_CCOLTER, almacenamientoPTAPSecondBean.getStrColter());                
      inputs.put(ConstantsLaboratorio.PAR_CESCOLI, almacenamientoPTAPSecondBean.getStrEscoli());
				
		inputs.put(Constants.PAR_CUSUARIO_CREACION, almacenamientoPTAPSecondBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, almacenamientoPTAPSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, almacenamientoPTAPSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#inactivarTableSecond(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPSecondBean)
	 */
	@Override
	public void inactivarTableSecond(AlmacenamientoPTAPSecondBean almacenamientoPTAPSecondBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_MUESTELEF21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPSecondBean.getIntIdDetalle());
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, almacenamientoPTAPSecondBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#obtenerDatosTableThree(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPThreeBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosTableThree(AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTEPAF21)
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
										record.add((rs.getString(1)==null||"".equals(rs.getString(1).trim()))?"":rs.getString(1)); // ""
										record.add((rs.getString(2)==null||"".equals(rs.getString(2).trim()))?"":rs.getString(2)); // ""
										record.add((rs.getString(3)==null||"".equals(rs.getString(3).trim()))?"":rs.getString(3)); // ""
										record.add((rs.getString(4)==null||"".equals(rs.getString(4).trim()))?"":rs.getString(4)); // ""
										record.add(rs.getInt(5)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPThreeBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#grabarTableThree(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPThreeBean)
	 */
	@Override
	public int grabarTableThree(AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
	   lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCRI, OracleTypes.VARCHAR));                  
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLONI, OracleTypes.VARCHAR));         
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGLECH, OracleTypes.VARCHAR));
       lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CPSEAE, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTEPAF21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPThreeBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPThreeBean.getIntIdDetalle());
		inputs.put(ConstantsLaboratorio.PAR_CDESCRI, almacenamientoPTAPThreeBean.getStrDescMuest());                
        inputs.put(ConstantsLaboratorio.PAR_CCOLONI, almacenamientoPTAPThreeBean.getStrColoni());              
        inputs.put(ConstantsLaboratorio.PAR_CAGLECH, almacenamientoPTAPThreeBean.getStrAglech());  
        inputs.put(ConstantsLaboratorio.PAR_CPSEAE, almacenamientoPTAPThreeBean.getStrPseae());  
				
		inputs.put(Constants.PAR_CUSUARIO_CREACION, almacenamientoPTAPThreeBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, almacenamientoPTAPThreeBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, almacenamientoPTAPThreeBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#inactivarTableThree(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPThreeBean)
	 */
	@Override
	public void inactivarTableThree(AlmacenamientoPTAPThreeBean almacenamientoPTAPThreeBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_MUESTEPAF21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPThreeBean.getIntIdDetalle());
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, almacenamientoPTAPThreeBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao#calcularTablaFirst(pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean)
	 */
	@Override
	public int calcularTablaFirst(AlmacenamientoPTAPFirstBean almacenamientoPTAPFirstBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();		
		paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ALMACENA_PTAP,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_CALCULO_MUESTELEF21), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		LOG.info("getIntIdCabecera: " + almacenamientoPTAPFirstBean.getIntIdCabecera());
		LOG.info("getIntIdDetalle: " + almacenamientoPTAPFirstBean.getIntIdDetalle());
		
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, almacenamientoPTAPFirstBean.getIntIdCabecera());
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, almacenamientoPTAPFirstBean.getIntIdDetalle());
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
		return outputs.get(Constants.PAR_OUT_RETURN);
	}
	
}
