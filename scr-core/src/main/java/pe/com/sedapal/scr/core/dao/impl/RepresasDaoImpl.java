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

import org.apache.commons.lang.StringUtils;
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
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.scr.core.beans.CalculoRepresasBean;
import pe.com.sedapal.scr.core.beans.ClimaBean;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.DatosRepresasGerenciaBean;
import pe.com.sedapal.scr.core.beans.ReportePivotBean;
import pe.com.sedapal.scr.core.beans.ReporteRepresaBean;
import pe.com.sedapal.scr.core.beans.RepresaBean;
import pe.com.sedapal.scr.core.beans.RepresasBean;
import pe.com.sedapal.scr.core.beans.Result;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IRepresasDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class RepresasDaoImpl.
 */
@Repository
public class RepresasDaoImpl implements IRepresasDao {
	
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
	 * Método que permite obtener el listado de Represas.
	 *
	 * @param represasBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result buscarRepresas(RepresasBean represasBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_BUSCA_REPRESAS)
				.declareParameters(						
						new SqlParameter(Constants.PAR_CNOMBRE_REPRESA, Types.VARCHAR),
						new SqlParameter(Constants.PAR_CNUMERO_PRECIPITACION, Types.VARCHAR),						

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
										record.add(rs.getString(2)); // v_fecreg
										record.add(rs.getString(3).toUpperCase()); // c_numpre
										record.add(rs.getString(4).toUpperCase()); // c_nomrep
										record.add(rs.getDouble(5)); // n_cota
										record.add(rs.getDouble(6)); // n_volume
										record.add(rs.getDouble(7)); // n_voltot
										record.add(rs.getDouble(8)); // n_descar
										record.add(rs.getDouble(9)); // n_lluvia
										record.add(rs.getDouble(10)); // n_evapor
										record.add(rs.getLong(11)); // n_humrel
										record.add(rs.getDouble(12)); // n_hy
										record.add(rs.getDouble(13)); // n_caudal										
										record.add(rs.getString(14).toUpperCase()); // c_nomcol
										record.add(rs.getString(15).toUpperCase()); // c_nomcli
										record.add(rs.getDouble(16)); // n_difniv
										record.add(rs.getDouble(17)); // n_difvol
										record.add(rs.getLong(18)); // n_tmpmax
										record.add(rs.getLong(19)); // n_tmpmin
										record.add(rs.getDouble(20)); // n_cautra
										record.add(rs.getDouble(21)); // n_voltra
										record.add(rs.getDouble(22)); // n_ariori
										record.add(rs.getString(23).toUpperCase()); // a_usucre
										record.add(rs.getString(24)); // a_feccre
										record.add(rs.getString(25).toUpperCase()); // a_usumod
										record.add(rs.getString(26)); // a_fecmod										
										record.add(rs.getLong(27)); // n_codigo
										record.add(rs.getLong(28)); // n_codrep										
										record.add(rs.getLong(29)); // n_ccolre										
										record.add(rs.getLong(30)); // n_codcli																

										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_CNOMBRE_REPRESA, represasBean.getRepresa().getNombreRepresa());
		params.addValue(Constants.PAR_CNUMERO_PRECIPITACION, represasBean.getStrNumeroPrecipitacion());		

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
	 *  
	 * Obtiene el registro de represas por identificador.
	 *
	 * @param nid Identificador del registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("rawtypes")
	public RepresasBean obtenerRepresas(Integer nid) throws Exception {
		ArrayList ret = null;
		RepresasBean result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_GET_REPRESAS)
		.declareParameters(new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<RepresasBean>() {

					@Override
					public RepresasBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						RepresasBean record = new RepresasBean();
						record.setRepresa(new RepresaBean());
						record.setColaborador(new ColaboradorBean());
						record.setClima(new ClimaBean());						
						
						record.setStrNumeroPrecipitacion(rs.getString(1)); // c_numpre						
						record.getRepresa().setNombreRepresa(rs.getString(2)); // c_nomrep
						record.setStrCota(new BigDecimal(rs.getDouble(3) + "").stripTrailingZeros().toPlainString()); // n_cota
						record.setStrVolumen(new BigDecimal(rs.getDouble(4) + "").stripTrailingZeros().toPlainString()); // n_volume
						record.setStrVolumenTotal(new BigDecimal(rs.getDouble(5) + "").stripTrailingZeros().toPlainString()); // n_voltot
						record.setStrDescarga(new BigDecimal(rs.getDouble(6) + "").stripTrailingZeros().toPlainString()); // n_descar
						record.setStrLluvia(new BigDecimal(rs.getDouble(7) + "").stripTrailingZeros().toPlainString()); // n_lluvia
						record.setStrEvaporacion(new BigDecimal(rs.getDouble(8) + "").stripTrailingZeros().toPlainString()); // n_evapor
						record.setIntHumedadRelativa((rs.getInt(9))); // n_humrel
						record.setStrHy(new BigDecimal(rs.getDouble(10) + "").stripTrailingZeros().toPlainString()); // n_hy
						record.setStrCaudal(new BigDecimal(rs.getDouble(11) + "").stripTrailingZeros().toPlainString()); // n_caudal
						record.setStrFechaRegistro(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(12))); // d_fecreg
						record.getColaborador().setNombreColaborador(rs.getString(13)); // c_nomcol
						record.getClima().setNombreClima(rs.getString(14)); // c_nomcli
						record.setStrDiferenciaNivel(new BigDecimal(rs.getDouble(15) + "").stripTrailingZeros().toPlainString()); // n_difniv
						record.setStrDiferenciaVolumen(new BigDecimal(rs.getDouble(16) + "").stripTrailingZeros().toPlainString()); // n_difvol						
						record.setStrTemperaturaMaxima(new BigDecimal(rs.getDouble(17) + "").stripTrailingZeros().toPlainString()); // n_tmpmax
						record.setStrTemperaturaMinima(new BigDecimal(rs.getDouble(18) + "").stripTrailingZeros().toPlainString()); // n_tmpmin
						record.setStrCaudalTrasvasado(new BigDecimal(rs.getDouble(19) + "").stripTrailingZeros().toPlainString()); // n_cautra
						record.setStrVolumenTrasvasado(new BigDecimal(rs.getDouble(20) + "").stripTrailingZeros().toPlainString()); // n_voltra
						record.setStrAforoRio(new BigDecimal(rs.getDouble(21) + "").stripTrailingZeros().toPlainString()); // n_ariori																				
						record.setIntCodigo((rs.getInt(22))); // n_codigo					
						record.setStrCodigoRepresa(String.valueOf(rs.getInt(23))); // n_codrep						
						record.setStrCodigoColaborador(String.valueOf(rs.getInt(24))); // n_ccolre						
						record.setStrCodigoClima(String.valueOf(rs.getInt(25))); // n_codcli	
						record.setStrEstado(rs.getString(26)); // c_estado
						record.setIntUsaFactorDescarga(rs.getInt(27)); // n_usefac
						record.setStrFactorDescarga(rs.getString(28)); // c_facdes
						
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_NCODIGO_REPRESAS, nid);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (RepresasBean) ret.get(0);
		
		return result;
	}

	/**
	 * Obtiene la información para el reporte gráfico por represa .
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReporteRepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteRepresaBean> generarReportePorRepresa(String periodo) throws Exception {
		List<ReporteRepresaBean> lstRetorno = null; 
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_REPORTE_REPRESA).declareParameters(    					
					new SqlParameter(Constants.PAR_PERIODO_REP_GRAF, Types.VARCHAR),						
					new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteRepresaBean>() {

						@Override
						public ReporteRepresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ReporteRepresaBean record = new ReporteRepresaBean();								
							record.setCodMantRepresa(rs.getLong(1)); // COD REPRESA
							record.setNombreRepresa(rs.getString(2)); // V_NOMBREREP
							record.setDia(rs.getString(3)); // V_DIA
							record.setPromVolumen(rs.getBigDecimal(5)); // PROM_VOL
							record.setPromPrecitpitaciones(rs.getBigDecimal(6)); // PROM_PRECIP
							return record;
						}
					} )						
			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_PERIODO_REP_GRAF, periodo);		
		
		Map<String,Object>  results = caller.execute(params);		
		lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		
	    return lstRetorno;
	}
	
	/**
	 * Obtiene la información para el reporte gráfico por maniobras.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReporteRepresaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReporteRepresaBean> generarReportePorRepresaManiobra(String periodo) throws Exception {
		List<ReporteRepresaBean> lstRetorno = null; 
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_REPORTE_REPRESA_MANIOBRA).declareParameters(    					
					new SqlParameter(Constants.PAR_PERIODO_REP_GRAF2, Types.VARCHAR),					
					new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteRepresaBean>() {

						@Override
						public ReporteRepresaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							ReporteRepresaBean record = new ReporteRepresaBean();								
							record.setTipoManiobra(rs.getInt(2)); // N_TIPMAN
							record.setDia(rs.getString(3)); // V_DIA
							record.setBdValorManiobra(rs.getBigDecimal(4)); // PROM_APERFIN
							return record;
						}
					} )						
			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_PERIODO_REP_GRAF2, periodo);		
		
		Map<String,Object>  results = caller.execute(params);		
		lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		
	    return lstRetorno;
	}

	/**
	 * Realiza el registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @return the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public RepresasBean registrarRepresas(RepresasBean represasBean) throws Exception {
		Integer intCodigoRepresas = -1;
		String numPrecipitacion = "";
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN_TOTAL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_DFECHA_REGISTRO, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_NCOD_COL_REP, OracleTypes.INTEGER));	
			paramsInput.add(new SqlParameter(Constants.PAR_NCOTA, OracleTypes.NUMERIC));		
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_CLIMA, OracleTypes.INTEGER));		
			paramsInput.add(new SqlParameter(Constants.PAR_NTEMPERATURA_MAXIMA, OracleTypes.NUMERIC));		
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN, OracleTypes.NUMERIC));	
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN_TRASVASADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCAUDAL_TRASVASADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDESCARGA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NTEMPERATURA_MINIMA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NLLUVIA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDIFERENCIA_NIVEL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDIFERENCIA_VOLUMEN, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NEVAPORACION, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NHUMEDAD_RELATIVA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_NAFORO_RIO_RIMAC, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NHY, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCAUDAL, OracleTypes.NUMERIC));	
			paramsInput.add(new SqlParameter(Constants.PAR_USEFACTOR, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_FACTORDESCARGA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_FACTORDESCARGAVAL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_FORMULA_VOL_TRASV, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESAS_CREACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_COD_REP, OracleTypes.INTEGER));
			paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_NUM_PRE, OracleTypes.VARCHAR));
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_REPRESAS,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_REPRESAS), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			
			inputs = new HashMap<String, Object>();			
			
			inputs.put(Constants.PAR_NVOLUMEN_TOTAL, new BigDecimal(represasBean.getStrVolumenTotal()));
			inputs.put(Constants.PAR_DFECHA_REGISTRO, new SimpleDateFormat("dd/MM/yyyy").parse(represasBean.getStrFechaRegistro()));
			inputs.put(Constants.PAR_NCODIGO_REPRESA, Integer.parseInt(represasBean.getStrCodigoRepresa()));	
			inputs.put(Constants.PAR_NCOD_COL_REP, Integer.parseInt(represasBean.getStrCodigoColaborador()));
			inputs.put(Constants.PAR_NCOTA, new BigDecimal(represasBean.getStrCota()));
			inputs.put(Constants.PAR_NCODIGO_CLIMA, Integer.parseInt(represasBean.getStrCodigoClima()));
			inputs.put(Constants.PAR_NTEMPERATURA_MAXIMA, new BigDecimal(represasBean.getStrTemperaturaMaxima()));
			inputs.put(Constants.PAR_NVOLUMEN, new BigDecimal(represasBean.getStrVolumen()));
			inputs.put(Constants.PAR_NVOLUMEN_TRASVASADO, new BigDecimal(represasBean.getStrVolumenTrasvasado()));
			inputs.put(Constants.PAR_NCAUDAL_TRASVASADO, new BigDecimal(represasBean.getStrCaudalTrasvasado()));
			inputs.put(Constants.PAR_NDESCARGA, new BigDecimal(represasBean.getStrDescarga()));
			inputs.put(Constants.PAR_NTEMPERATURA_MINIMA, new BigDecimal(represasBean.getStrTemperaturaMinima()));
			inputs.put(Constants.PAR_NLLUVIA, new BigDecimal(represasBean.getStrLluvia()));
			inputs.put(Constants.PAR_NDIFERENCIA_NIVEL, new BigDecimal(represasBean.getStrDiferenciaNivel()));
			inputs.put(Constants.PAR_NDIFERENCIA_VOLUMEN, new BigDecimal(represasBean.getStrDiferenciaVolumen()));
			inputs.put(Constants.PAR_NEVAPORACION, new BigDecimal(represasBean.getStrEvaporacion()));
			inputs.put(Constants.PAR_NHUMEDAD_RELATIVA, represasBean.getIntHumedadRelativa());
			inputs.put(Constants.PAR_NAFORO_RIO_RIMAC, new BigDecimal(represasBean.getStrAforoRio()));
			inputs.put(Constants.PAR_NHY, new BigDecimal(represasBean.getStrHy()));
			inputs.put(Constants.PAR_NCAUDAL, new BigDecimal(represasBean.getStrCaudal()));	
			inputs.put(Constants.PAR_USEFACTOR, represasBean.getIntUsaFactorDescarga());	
			inputs.put(Constants.PAR_FACTORDESCARGA, represasBean.getStrFactorDescarga());	
			inputs.put(Constants.PAR_FACTORDESCARGAVAL, Double.parseDouble(represasBean.getStrFactorValor()));
			inputs.put(Constants.PAR_FORMULA_VOL_TRASV, represasBean.getStrFormulaVolumenTrasvasado());
			inputs.put(Constants.PAR_VESTADO, represasBean.getStrEstado());			
			
			inputs.put(Constants.PAR_AUSUAREPRESAS_CREACION, represasBean.getUsuarioCreacion());					
			inputs.put(Constants.PAR_APROGRAMA_REPRESAS, represasBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESAS, represasBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, represasBean.getCodigoSistema());		
			
			Map<String, Object> outputs = this.execSp.executeSp(inputs);
			
			intCodigoRepresas = (Integer)outputs.get(Constants.PAR_OUT_COD_REP);
			numPrecipitacion = (String)outputs.get(Constants.PAR_OUT_NUM_PRE);
			
			represasBean.setIntCodigo(intCodigoRepresas);
			represasBean.setStrNumeroPrecipitacion(numPrecipitacion);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return represasBean;
	}

	/**
	 * Realiza la modificación de registro de represas.
	 *
	 * @param represasBean the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarRepresas(RepresasBean represasBean) throws Exception {		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN_TOTAL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESA, OracleTypes.INTEGER));	
			paramsInput.add(new SqlParameter(Constants.PAR_NCOD_COL_REP, OracleTypes.INTEGER));	
			paramsInput.add(new SqlParameter(Constants.PAR_NCOTA, OracleTypes.NUMERIC));		
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_CLIMA, OracleTypes.INTEGER));		
			paramsInput.add(new SqlParameter(Constants.PAR_NTEMPERATURA_MAXIMA, OracleTypes.NUMERIC));		
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN, OracleTypes.NUMERIC));	
			paramsInput.add(new SqlParameter(Constants.PAR_NVOLUMEN_TRASVASADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCAUDAL_TRASVASADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDESCARGA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NTEMPERATURA_MINIMA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NLLUVIA, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDIFERENCIA_NIVEL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NDIFERENCIA_VOLUMEN, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NEVAPORACION, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NHUMEDAD_RELATIVA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_NAFORO_RIO_RIMAC, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NHY, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_NCAUDAL, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_USEFACTOR, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_FACTORDESCARGA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_FORMULA_VOL_TRASV, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESAS_MODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_REPRESAS+Constants.P_SEPARADOR+Constants.SP_UPDATE_REPRESAS, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_REPRESAS, Integer.parseInt(represasBean.getCodigo()));
			inputs.put(Constants.PAR_NVOLUMEN_TOTAL, new BigDecimal(represasBean.getStrVolumenTotal()));
			inputs.put(Constants.PAR_NCODIGO_REPRESA, Integer.parseInt(represasBean.getStrCodigoRepresa()));	
			inputs.put(Constants.PAR_NCOD_COL_REP, Integer.parseInt(represasBean.getStrCodigoColaborador()));
			inputs.put(Constants.PAR_NCOTA, new BigDecimal(represasBean.getStrCota()));
			inputs.put(Constants.PAR_NCODIGO_CLIMA, Integer.parseInt(represasBean.getStrCodigoClima()));
			inputs.put(Constants.PAR_NTEMPERATURA_MAXIMA, new BigDecimal(represasBean.getStrTemperaturaMaxima()));
			inputs.put(Constants.PAR_NVOLUMEN, new BigDecimal(represasBean.getStrVolumen()));
			inputs.put(Constants.PAR_NVOLUMEN_TRASVASADO, new BigDecimal(represasBean.getStrVolumenTrasvasado()));
			inputs.put(Constants.PAR_NCAUDAL_TRASVASADO, new BigDecimal(represasBean.getStrCaudalTrasvasado()));
			inputs.put(Constants.PAR_NDESCARGA, new BigDecimal(represasBean.getStrDescarga()));
			inputs.put(Constants.PAR_NTEMPERATURA_MINIMA, new BigDecimal(represasBean.getStrTemperaturaMinima()));
			inputs.put(Constants.PAR_NLLUVIA, new BigDecimal(represasBean.getStrLluvia()));
			inputs.put(Constants.PAR_NDIFERENCIA_NIVEL, new BigDecimal(represasBean.getStrDiferenciaNivel()));
			inputs.put(Constants.PAR_NDIFERENCIA_VOLUMEN, new BigDecimal(represasBean.getStrDiferenciaVolumen()));			
			inputs.put(Constants.PAR_NEVAPORACION, new BigDecimal(represasBean.getStrEvaporacion()));
			inputs.put(Constants.PAR_NHUMEDAD_RELATIVA, represasBean.getIntHumedadRelativa());
			inputs.put(Constants.PAR_NAFORO_RIO_RIMAC, new BigDecimal(represasBean.getStrAforoRio()));
			inputs.put(Constants.PAR_NHY, new BigDecimal(represasBean.getStrHy()));
			inputs.put(Constants.PAR_NCAUDAL, new BigDecimal(represasBean.getStrCaudal()));
			inputs.put(Constants.PAR_USEFACTOR, represasBean.getIntUsaFactorDescarga());	
			inputs.put(Constants.PAR_FACTORDESCARGA, represasBean.getStrFactorDescarga());	
			inputs.put(Constants.PAR_FORMULA_VOL_TRASV, represasBean.getStrFormulaVolumenTrasvasado());
			inputs.put(Constants.PAR_VESTADO, represasBean.getStrEstado());
			
			inputs.put(Constants.PAR_AUSUAREPRESAS_MODIFICACION, represasBean.getUsuarioCreacion());					
			inputs.put(Constants.PAR_APROGRAMA_REPRESAS, represasBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESAS, represasBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, represasBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	
	/**
	 * Realiza la modificación de registro de represas pudiendo solo updatear solo el factor de desarga.
	 *
	 * @param represasBean the represas bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarFactorRepresas(RepresasBean represasBean) throws Exception {		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_USEFACTOR, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.PAR_FACTORDESCARGA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_FACTORDESCARGAVAL, OracleTypes.NUMERIC));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESAS_MODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_REPRESAS+Constants.P_SEPARADOR+Constants.SP_UPDATE_FACTOR_REPRESAS, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_REPRESAS, Integer.parseInt(represasBean.getCodigo()));
			
			inputs.put(Constants.PAR_USEFACTOR, represasBean.getIntUsaFactorDescarga());	
			inputs.put(Constants.PAR_FACTORDESCARGA, StringUtils.isEmpty(represasBean.getStrFactorDescarga()) ?
					null : represasBean.getStrFactorDescarga());	
			inputs.put(Constants.PAR_FACTORDESCARGAVAL, StringUtils.isEmpty(represasBean.getStrFactorValor()) ? 
					null : Double.parseDouble(represasBean.getStrFactorValor()));
			
			inputs.put(Constants.PAR_AUSUAREPRESAS_MODIFICACION, represasBean.getUsuarioModificacion());					
			inputs.put(Constants.PAR_APROGRAMA_REPRESAS, represasBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESAS, represasBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, represasBean.getCodigoSistema());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}

	/**
	 * Realiza la modificación de las formulas de represas menos del factor de represas.
	 *
	 * @param calculoRepresasBean objeto del tipo CalculoRepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	public void actualizarFormulasRepresas(CalculoRepresasBean calculoRepresasBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_N_CODREPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_C_FORMULAVOLTRAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_N_VALVOLTRAS, OracleTypes.NUMERIC));
			
			paramsInput.add(new SqlParameter(Constants.PAR_A_USUMOD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_A_AREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_A_SISTEMA, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_REPRESAS+Constants.P_SEPARADOR+Constants.SP_UPDATE_FORMULAS_REPRESAS, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_CODREPRESAS, calculoRepresasBean.getIntIdRepresas());
			inputs.put(Constants.PAR_C_FORMULAVOLTRAS, calculoRepresasBean.getStrFormulaVolumenTrasvasado());	
			inputs.put(Constants.PAR_N_VALVOLTRAS, calculoRepresasBean.getBdValorVolumenTrasvasado());	
			
			inputs.put(Constants.PAR_A_USUMOD, calculoRepresasBean.getUsuarioModificacion());					
			inputs.put(Constants.PAR_A_AREA, calculoRepresasBean.getCodigoArea());		
			inputs.put(Constants.PAR_A_SISTEMA, calculoRepresasBean.getCodigoSistema());		
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	/**
	 * Realiza el cambio de estado de un registro de represas.
	 *
	 * @param represasBean objeto del tipo RepresasBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarRepresas(RepresasBean represasBean) throws Exception {		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_REPRESAS, OracleTypes.INTEGER));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUAREPRESAS_MODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_REPRESAS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_REPRESAS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_REPRESAS,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_REPRESAS), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_REPRESAS, represasBean.getIntCodigo());	
			
			inputs.put(Constants.PAR_AUSUAREPRESAS_MODIFICACION, represasBean.getUsuarioModificacion());					
			inputs.put(Constants.PAR_APROGRAMA_REPRESAS, represasBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_REPRESAS, represasBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_REPRESAS, represasBean.getCodigoSistema()); 

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}	
	
	/**
	 * Obtiene la cantidad de registros de represas activos para la represa y fecha consultados.
	 *
	 * @param strFecha Es la fecha de la que se desea consultar (dd/mm/yyyy)
	 * @param intCodRepresa Es el codigo de la represa de la que se desea consultar
	 * @return Objeto de tipo Integer que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer obtenerCatidadRepresasActivaPorFecha(String strFecha, Integer intCodRepresa) throws Exception {
		Integer cantidadRepresas = null;
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_FECHA_REGISTRO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_CODIGO_MANT_REPRESA, OracleTypes.NUMBER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_CANTIDAD_REPRESAS, OracleTypes.NUMBER));

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_REPRESAS,ConstantsCommon.P_SEPARADOR,Constants.SP_GET_CANTIDAD_REPRESAS_FECHA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_FECHA_REGISTRO, strFecha);	
			inputs.put(Constants.PAR_CODIGO_MANT_REPRESA, intCodRepresa);					

			// Ejecutamos el store procedure
			Map<String, BigDecimal> outputs = this.execSp.executeSp(inputs);
			
			cantidadRepresas = outputs.get(Constants.PAR_OUT_CANTIDAD_REPRESAS).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return cantidadRepresas;
	}
	
	/**
	 * Obtiene la suma de promedios para un día y periodo del caudal para aquellos ríos que forman parte del caudal del rímac.
	 *
	 * @param strPeriodo Es el periodo para el que se requiere el aforo
	 * @param strDia Es el día para el que se requiere el aforo
	 * @return Objeto de tipo BigDecimal que contiene el resultado
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal obtenerAforoRioRimac(String strPeriodo, String strDia) throws Exception {
		BigDecimal aforoRioRimac = null;
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_CPERIODO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_CDIA, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_AFORO, OracleTypes.NUMBER));

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_REPRESAS,ConstantsCommon.P_SEPARADOR,Constants.SP_GET_AFORO_RIO_RIMAC), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_CPERIODO, strPeriodo);	
			inputs.put(Constants.PAR_CDIA, strDia);					

			// Ejecutamos el store procedure
			Map<String, BigDecimal> outputs = this.execSp.executeSp(inputs);
			
			aforoRioRimac = outputs.get(Constants.PAR_OUT_AFORO);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return aforoRioRimac;
	}
	
	/**
	 * Obtiene la información para el reporte resumen de represas por día.
	 *
	 * @param periodo Es el periodo para el que se requiere generar el reporte
	 * @return lista de objetos de tipo ReportePivotBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportePivotBean> generarReporteResumenRepresa(String periodo) throws Exception {
		List<ReportePivotBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_GET_REPORTE_RESUMEN_REPRESAS)
				.declareParameters(						
						new SqlParameter(Constants.PAR_PERIODO, Types.VARCHAR),
						
						new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReportePivotBean>() {

									@Override
									public ReportePivotBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ReportePivotBean record = new ReportePivotBean();
										record.setStrAttributeVertical(rs.getString(1));
										record.setStrAttributeHorizontal(rs.getString(2));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue(Constants.PAR_PERIODO, periodo);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<ReportePivotBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/**
	 * Obtiene la información para el correo de envío a gerencia.
	 *
	 * @param fecha Es la fecha para la que se requiere generar el reporte
	 * @return lista de objetos de tipo DatosRepresasGerenciaBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DatosRepresasGerenciaBean> obtenerDatosReporteGerencia(String fecha) throws Exception {
		List<DatosRepresasGerenciaBean> lstRetorno = null;
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(Constants.PACKAGE_REPRESAS).withProcedureName(Constants.SP_GET_REPORTE_GERENCIA)
					.declareParameters(						
							new SqlParameter(Constants.PAR_CFECHA, Types.VARCHAR),
							
							new SqlOutParameter(
									ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<DatosRepresasGerenciaBean>() {

										@Override
										public DatosRepresasGerenciaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
											DatosRepresasGerenciaBean record = new DatosRepresasGerenciaBean();
											record.setStrFecha(rs.getString(1));
											record.setStrHoraCorte(rs.getString(2));
											record.setIntCodigoRepresa(rs.getInt(3));
											record.setStrNombreRepresa(rs.getString(4));
											record.setBdTopeMaximoRepresa(rs.getBigDecimal(5));
											record.setIntCodigoRepresas(rs.getInt(6));
											record.setBdVolumen(rs.getBigDecimal(7));
											record.setBdVolumenTotal(rs.getBigDecimal(8));
											record.setBdDescarga(rs.getBigDecimal(9));
											record.setStrNombreClima(rs.getString(10));
											return record;										
										}
									}))
					.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(Constants.PAR_CFECHA, fecha);

			Map<String, Object> results = caller.execute(params);
			lstRetorno = (List<DatosRepresasGerenciaBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lstRetorno;
	}
}
