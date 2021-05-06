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
import pe.com.sedapal.scr.core.beans.CaudalSearchBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.beans.ReporteResumenBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsCorreo;
import pe.com.sedapal.scr.core.dao.ICaudalDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.correo.core.beans.Caudal;

// TODO: Auto-generated Javadoc
/**
 * The Class CaudalDaoImpl.
 */
@Repository
public class CaudalDaoImpl implements ICaudalDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ICaudalDao#buscarCaudales(pe.com.sedapal.scr.core.beans.CaudalSearchBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result buscarCaudales(CaudalSearchBean caudalSearchBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null; 
		
		/**
		 * Método que permite obtener el listado de caudales de río
		 * @param caudalSearchBean Contiene los filtros de búsqueda de caudal
		 * @param paginacion Representa la página solicitada
		 * @Return Objeto de tipo Result que contiene los resultados
		 * @throws Exception Excepción que puede ser lanzada
		 */
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CAUDAL).withProcedureName(Constants.SP_SEARCH_CAUDAL).declareParameters(
    					new SqlParameter(Constants.PAR_NOMBRELARGO, Types.VARCHAR),
    					new SqlParameter(Constants.PAR_TIPOPROCESO, Types.VARCHAR),
    					new SqlParameter(Constants.PAR_PERIODO, Types.VARCHAR),
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
								record.add(rs.getString(1)); //"v_period"
								record.add(rs.getString(2)); //"v_nombrelargo rio"
								record.add(rs.getString(3)); //"v_abreviatura rio"
								record.add(rs.getString(4)); //"v_tipoproceso"
								record.add(rs.getString(5)); //"v_estado"		 						
								record.add(rs.getString(6)); //"d.fhproc"
								record.add(rs.getLong(7)+""); //n_codigo
								record.add(rs.getLong(8)+""); //n_codigo de rio
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(Constants.PAR_NOMBRELARGO, caudalSearchBean.getStrNombreLargo());
		params.addValue(Constants.PAR_TIPOPROCESO, caudalSearchBean.getStrTipoProceso());
		params.addValue(Constants.PAR_PERIODO, caudalSearchBean.getStrPeriodo());
		
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

	/**
	 * Método que permite obtener el detalle de un caudal de río.
	 *
	 * @param caudalid the caudalid
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos de tipo CeldaMatrizBEan que contiene los datos del detalle
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CeldaMatrizBean> obtenerDetalleCaudal(Integer caudalid) throws Exception {
		List<CeldaMatrizBean> lstRetorno = null; 
		
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CAUDAL).withProcedureName(Constants.SP_GET_CAUDAL2).declareParameters(
    					new SqlParameter(Constants.PAR_CODIGO_CAUDAL, Types.INTEGER),    		
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<List<CeldaMatrizBean>>() {
							@Override
							public ArrayList<CeldaMatrizBean> mapRow(ResultSet rs, int rowNum) throws SQLException {
								ArrayList<CeldaMatrizBean> record = new ArrayList();
								record.add(new CeldaMatrizBean(rs.getDouble(2))); // n_nhr01
								record.add(new CeldaMatrizBean(rs.getDouble(3))); // n_nhr02
								record.add(new CeldaMatrizBean(rs.getDouble(4))); // n_nhr03
								record.add(new CeldaMatrizBean(rs.getDouble(5))); // n_nhr04
								record.add(new CeldaMatrizBean(rs.getDouble(6))); // n_nhr05
								record.add(new CeldaMatrizBean(rs.getDouble(7))); // n_nhr06
								record.add(new CeldaMatrizBean(rs.getDouble(8))); // n_nhr07
								record.add(new CeldaMatrizBean(rs.getDouble(9))); // n_nhr08
								record.add(new CeldaMatrizBean(rs.getDouble(10))); // n_nhr09
								record.add(new CeldaMatrizBean(rs.getDouble(11))); // n_nhr10
								record.add(new CeldaMatrizBean(rs.getDouble(12))); // n_nhr11
								record.add(new CeldaMatrizBean(rs.getDouble(13))); // n_nhr12
								record.add(new CeldaMatrizBean(rs.getDouble(14))); // n_nhr13
								record.add(new CeldaMatrizBean(rs.getDouble(15))); // n_nhr14
								record.add(new CeldaMatrizBean(rs.getDouble(16))); // n_nhr15
								record.add(new CeldaMatrizBean(rs.getDouble(17))); // n_nhr16
								record.add(new CeldaMatrizBean(rs.getDouble(18))); // n_nhr17
								record.add(new CeldaMatrizBean(rs.getDouble(19))); // n_nhr18
								record.add(new CeldaMatrizBean(rs.getDouble(20))); // n_nhr19
								record.add(new CeldaMatrizBean(rs.getDouble(21))); // n_nhr20
								record.add(new CeldaMatrizBean(rs.getDouble(22))); // n_nhr21
								record.add(new CeldaMatrizBean(rs.getDouble(23))); // n_nhr22
								record.add(new CeldaMatrizBean(rs.getDouble(24))); // n_nhr23
								record.add(new CeldaMatrizBean(rs.getDouble(25))); // n_nhr24	
								record.add(new CeldaMatrizBean(rs.getDouble(26))); // n_promed
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_CODIGO_CAUDAL, caudalid);
		
		Map<String,Object>  results = caller.execute(params);
		lstRetorno =  (List<CeldaMatrizBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);   
		
        return lstRetorno;	
	}

	/**
	 * Método que permite obtener la información para el reporte resumen pdf.
	 *
	 * @param periodo Es el periodo del cual se requiere información
	 * @return the list
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Lista de objetos ReporteResumenBean que contiene la información del reporte pdf
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<ReporteResumenBean> generarReporteResumenCaudal(String periodo) throws Exception {
		List<ReporteResumenBean> lstRetorno = null; 
		
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(Constants.PACKAGE_CAUDAL).withProcedureName(Constants.SP_GET_CAUDET).declareParameters(    					
    					new SqlParameter(Constants.PAR_PERIODO, Types.VARCHAR),						
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteResumenBean>() {

							@Override
							public ReporteResumenBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								ReporteResumenBean record = new ReporteResumenBean();								
								record.setPERIODO(rs.getString(1)); // PERIODO
								record.setNOMBRERIO(rs.getString(2)); // V_NOMBRERIO
								record.setDIA(rs.getString(3)); // V_DIA
								record.setNHR01(rs.getBigDecimal(4)); // N_NHR01
								record.setNHR02(rs.getBigDecimal(5)); // N_NHR02 		 						
								record.setNHR03(rs.getBigDecimal(6)); // N_NHR03
								record.setNHR04(rs.getBigDecimal(7)); // N_NHR04
								record.setNHR05(rs.getBigDecimal(8)); // N_NHR05
								record.setNHR06(rs.getBigDecimal(9)); // N_NHR06
								record.setNHR07(rs.getBigDecimal(10)); // N_NHR07
								record.setNHR08(rs.getBigDecimal(11)); // N_NHR08		 						
								record.setNHR09(rs.getBigDecimal(12)); // N_NHR09
								record.setNHR10(rs.getBigDecimal(13)); // N_NHR10
								record.setNHR11(rs.getBigDecimal(14)); // N_NHR11
								record.setNHR12(rs.getBigDecimal(15)); // N_NHR12
								record.setNHR13(rs.getBigDecimal(16)); // N_NHR13
								record.setNHR14(rs.getBigDecimal(17)); // N_NHR14		 						
								record.setNHR15(rs.getBigDecimal(18)); // N_NHR15
								record.setNHR16(rs.getBigDecimal(19)); // N_NHR16
								record.setNHR17(rs.getBigDecimal(20)); // N_NHR17
								record.setNHR18(rs.getBigDecimal(21)); // N_NHR18
								record.setNHR19(rs.getBigDecimal(22)); // N_NHR19
								record.setNHR20(rs.getBigDecimal(23)); // N_NHR20		 						
								record.setNHR21(rs.getBigDecimal(24)); // N_NHR21
								record.setNHR22(rs.getBigDecimal(25)); // N_NHR22
								record.setNHR23(rs.getBigDecimal(26)); // N_NHR23
								record.setNHR24(rs.getBigDecimal(27)); // N_NHR24
								record.setPROMED(rs.getBigDecimal(28)); // N_PROMED		
								record.setCODRIO(rs.getLong(29)); // N_CODIGO CODRIO
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_PERIODO, periodo);		
		
		Map<String,Object>  results = caller.execute(params);		
		lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		
        return lstRetorno;
	}

	/**
	 * Método que actualiza la información de un caudal.
	 *
	 * @param caudal Es el objeto con la información a actualizar
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarCaudal(Caudal caudal) throws Exception {
		try {
			String spName = Constants.PACKAGE_CAUDAL + Constants.P_SEPARADOR + Constants.SP_UPDATE_CAUDAL;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_N_CODIGO, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_TIPROC, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_RUTA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_D_FHREG, OracleTypes.DATE));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_ESTADO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_NOMARC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_N_CODCAR, OracleTypes.INTEGER));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_USUUPD, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			// Creamos ejecutor de Store Procedure
			this.execSp = new ExecuteProcedure(template.getDataSource(), spName, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(ConstantsCorreo.PAR_N_CODIGO, caudal.getIntCodigo());
			inputs.put(ConstantsCorreo.PAR_V_TIPROC, caudal.getStrTipoProceso());
			inputs.put(ConstantsCorreo.PAR_V_RUTA, caudal.getStrRuta());
			inputs.put(ConstantsCorreo.PAR_D_FHREG, caudal.getDteFechaHoraRegistro());
			inputs.put(ConstantsCorreo.PAR_V_ESTADO, caudal.getStrEstado());
			inputs.put(ConstantsCorreo.PAR_V_NOMARC, caudal.getStrNombreArchivo());
			inputs.put(ConstantsCorreo.PAR_N_CODCAR, caudal.getIntCodigoCarga());
			
			inputs.put(ConstantsCorreo.PAR_A_USUUPD, caudal.getUsuarioModificacion());		
			inputs.put(ConstantsCorreo.PAR_A_PROGRA, caudal.getPrograma());		
			inputs.put(ConstantsCorreo.PAR_A_CODARE, caudal.getCodigoArea());		
			inputs.put(ConstantsCorreo.PAR_A_CODSIS, caudal.getCodigoSistema());		

			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
