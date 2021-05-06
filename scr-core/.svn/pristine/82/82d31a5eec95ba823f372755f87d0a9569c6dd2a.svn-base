/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
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
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.AlmacenamientoBean;
import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class AlmacenamientoDaoImpl.
 */
@Repository
public class AlmacenamientoDaoImpl implements IAlmacenamientoDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAlmacenamientoDao#obtenerDatosAcumulados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AlmacenamientoBean> obtenerDatosAcumulados(String periodo) throws Exception {
		List<AlmacenamientoBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_ALMACENAMIENTO).withProcedureName(Constants.SP_GET_ALMACENAMIENTO_PERIODO)
				.declareParameters(						
						new SqlParameter(Constants.PAR_ALM_PERIODO, Types.VARCHAR),
						
						new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AlmacenamientoBean>() {
									@Override
									public AlmacenamientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AlmacenamientoBean record = new AlmacenamientoBean();
										record.setStrDia(rs.getString(1));
										record.setBdValorVolumen(rs.getBigDecimal(2));
										record.setBdPorcentajeVolumen(rs.getBigDecimal(3));
										record.setBdValorDescarga(rs.getBigDecimal(4));
										record.setBdPorcentajeDescarga(rs.getBigDecimal(5));
										record.setBdVolumenTotal(rs.getBigDecimal(6));
										record.setBdQRegulado(rs.getBigDecimal(7));
										record.setBdQDescarga(rs.getBigDecimal(8));
										record.setBdQNatural(rs.getBigDecimal(9));
										record.setDbColumnaUno(rs.getBigDecimal(10));
										record.setDbColumnaDos(rs.getBigDecimal(11));
										record.setStrFechaRegistro(rs.getString(12));
										record.setStrFormulaAlmacenamiento(rs.getString(13));
										record.setStrTextoFormulaAlmacenamiento(rs.getString(14));
										record.setStrFormulaManiobraDescarga(rs.getString(15));
										record.setStrTextoFormulaManiobraDescarga(rs.getString(16));
										record.setStrTextoFormulaColumnaUno(rs.getString(17));
										record.setStrTextoFormulaColumnaDos(rs.getString(18));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue(Constants.PAR_ALM_PERIODO, periodo);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<AlmacenamientoBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/**
	 * Obtiene la información de datos calculados para el correo enviado a gerencia en una fecha .
	 *
	 * @param fecha Es la fecha para la que se requiere generar el reporte
	 * @return lista de objetos de tipo AlmacenamientoBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AlmacenamientoBean obtenerDatosAcumuladosPorDia(String fecha) throws Exception {
		List<AlmacenamientoBean> lstRetorno = null;
		AlmacenamientoBean result = null;
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(Constants.PACKAGE_ALMACENAMIENTO).withProcedureName(Constants.SP_GET_ALMACENAMIENTO_FECHA)
					.declareParameters(						
							new SqlParameter(Constants.PAR_ALM_FECHA_GER, Types.VARCHAR),
							
							new SqlOutParameter(
									ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AlmacenamientoBean>() {
										@Override
										public AlmacenamientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
											AlmacenamientoBean record = new AlmacenamientoBean();
											record.setStrDia(rs.getString(1));
											record.setBdValorVolumen(rs.getBigDecimal(2));
											record.setBdPorcentajeVolumen(rs.getBigDecimal(3));
											record.setBdValorDescarga(rs.getBigDecimal(4));
											record.setBdPorcentajeDescarga(rs.getBigDecimal(5));
											record.setBdVolumenTotal(rs.getBigDecimal(6));
											record.setBdQRegulado(rs.getBigDecimal(7));
											record.setBdQDescarga(rs.getBigDecimal(8));
											record.setBdQNatural(rs.getBigDecimal(9));
											record.setDbColumnaUno(rs.getBigDecimal(10));
											record.setDbColumnaDos(rs.getBigDecimal(11));
											record.setStrFechaRegistro(rs.getString(12));
											record.setStrFormulaAlmacenamiento(rs.getString(13));
											record.setStrTextoFormulaAlmacenamiento(rs.getString(14));
											record.setStrFormulaManiobraDescarga(rs.getString(15));
											record.setStrTextoFormulaManiobraDescarga(rs.getString(16));
											record.setStrTextoFormulaColumnaUno(rs.getString(17));
											record.setStrTextoFormulaColumnaDos(rs.getString(18));
											return record;										
										}
									}))
					.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(Constants.PAR_ALM_FECHA_GER, fecha);

			Map<String, Object> results = caller.execute(params);
			lstRetorno = (List<AlmacenamientoBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);
			if(lstRetorno!=null && lstRetorno.size()>0){
				result = lstRetorno.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * Realiza la modificación de almacenamiento.
	 *
	 * @param strFecha Es la fecha para la cual se realiza la modificación
	 * @param strFormulaEmbalse Formula a evaluar
	 * @param strFormulaManDescarga Formula a evaluar
	 * @param strFormulaUno Formula a evaluar
	 * @param strFormulaDos Formula a evaluar
	 * @param auditoria Es el objeto con los datos de auditoria
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarAlmacenamiento(String strFecha, String strFormulaEmbalse, String strFormulaManDescarga, 
			String strFormulaUno, String strFormulaDos, BaseBean auditoria)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_FECHA, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_FOREMBALSE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_FORMANDESCARGA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_FORMULAUNO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_FORMULADOS, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_USUMODIFICACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_CODIGOAREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ALM_CODIGOSISTEMA, OracleTypes.INTEGER));
			
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_ALMACENAMIENTO+Constants.P_SEPARADOR+Constants.SP_UPDATE_ALMACENAMIENTO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_ALM_FECHA, new SimpleDateFormat("dd-MM-yyyy").parse(strFecha));
			inputs.put(Constants.PAR_ALM_FOREMBALSE, strFormulaEmbalse);
			inputs.put(Constants.PAR_ALM_FORMANDESCARGA, strFormulaManDescarga);
			inputs.put(Constants.PAR_ALM_FORMULAUNO, strFormulaUno);
			inputs.put(Constants.PAR_ALM_FORMULADOS, strFormulaDos);
			
			inputs.put(Constants.PAR_ALM_USUMODIFICACION, auditoria.getUsuarioModificacion());					
			inputs.put(Constants.PAR_ALM_CODIGOAREA, auditoria.getCodigoArea());		
			inputs.put(Constants.PAR_ALM_CODIGOSISTEMA, auditoria.getCodigoSistema());		
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}

	/**
	 * Obtiene el nombre de las represas excluidas para el calculo de la columna uno del excel.
	 *
	 * @return objeto de tipo String que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public String obtenerRepresasExcluidasColumnaUno() throws Exception {
		String represas = "";

		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(Constants.PACKAGE_ALMACENAMIENTO).withProcedureName(Constants.SP_NOMBRE_REPRESAS_EXCLUIDAS)
					.declareParameters(						
							new SqlOutParameter(Constants.PAR_ALM_OUT_REPRESAS, OracleTypes.VARCHAR))
					.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();

			Map<String, Object> results = caller.execute(params);
			represas = (String) results.get(Constants.PAR_ALM_OUT_REPRESAS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return represas;
	}
}
