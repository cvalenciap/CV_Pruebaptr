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
import pe.com.sedapal.scr.core.beans.FormularioDetalle242;
import pe.com.sedapal.scr.core.beans.FormularioHeader242;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IFormulario242Dao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class Formulario242DaoImpl.
 */
@Repository
public class Formulario242DaoImpl implements IFormulario242Dao {
	
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#getListadoFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioHeader(FormularioHeader242 formularioHeader242, Paginacion paginacion) {
		
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM242).withProcedureName(ConstantsLaboratorio.SP_LISTADO_HEADER242)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.P_FECHA_MUESTREO, Types.VARCHAR),
						new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
						new SqlParameter(ConstantsLaboratorio.PAR_CODARE, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ArrayList>() {

									@SuppressWarnings("unchecked")
									@Override
									public ArrayList mapRow(ResultSet rs, int rowNum) throws SQLException {
										ArrayList record = new ArrayList();
										record.add("");
										record.add(rs.getString(1)); // "FEC_MUESTREO"
										record.add(rs.getString(2)); // "FEC_RECEPCION"
										record.add(rs.getString(3)); // "PERSONA_MUESTREO"
										record.add(rs.getString(4)); // "PERSONA_RECEPCION"
										record.add(rs.getString(5)); // "CODIGO"
										record.add(rs.getString(6)); // EXISTE_REGISTRO_HOY
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_FECHA_MUESTREO, formularioHeader242.getStrFechaMuestreo());
		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());
		params.addValue(ConstantsLaboratorio.PAR_CODARE, formularioHeader242.getCodare());
		
		LOG.info("paginacion.getColorderby() :" + paginacion.getColorderby());
		LOG.info("paginacion.getColorderbydir() :" + paginacion.getColorderbydir());
		LOG.info("paginacion.getPagdesde() :" + paginacion.getPagdesde());
		LOG.info("paginacion.getCantidadpag() :" + paginacion.getCantidadpag());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		Result result = new Result();
		result.setData(lstRetorno);
		result.setRecords((long) quantity);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#grabarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public int grabarFormularioHeader(FormularioHeader242 formularioHeader242)  {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		int intRetorno=-1;
		System.out.println("DAO_IMPLEMENTACION: " +formularioHeader242.getStrFechaMuestreo());

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FECHA_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FECHA_RECEPCION, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PERSONA_MUESTREA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PERSONA_RECEPCION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERVACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TRACHO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CUMPLE, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODARE, OracleTypes.INTEGER));
			
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM242,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_HEADER), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
		    inputs.put(ConstantsLaboratorio.P_FECHA_MUESTREO, formularioHeader242.getStrFechaMuestreo());
			inputs.put(ConstantsLaboratorio.P_FECHA_RECEPCION, formularioHeader242.getStrFechaRecepcion());
			inputs.put(ConstantsLaboratorio.P_PERSONA_MUESTREA, formularioHeader242.getPersonaMuestrea());
			inputs.put(ConstantsLaboratorio.P_PERSONA_RECEPCION, formularioHeader242.getPersonaRecepcion());
			inputs.put(ConstantsLaboratorio.P_OBSERVACION, formularioHeader242.getObservacion());
			inputs.put(Constants.A_PAR_USUARIO_CREACION, formularioHeader242.getUsuarioCreacion());		
			inputs.put(Constants.A_PAR_USUARIO_MODIFICACION,formularioHeader242.getUsuarioModificacion());		
			inputs.put(Constants.A_PAR_NOMBRE_PROGRAMA, formularioHeader242.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_TRACHO, formularioHeader242.getTransporteChofer());	
			inputs.put(ConstantsLaboratorio.PAR_CUMPLE, formularioHeader242.getCumpleEpp());	
			inputs.put(ConstantsLaboratorio.PAR_CODARE, formularioHeader242.getCodare());	
			
			Map<String, Integer> outputs = this.execSp.executeSp(inputs);
			intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
			return intRetorno;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#grabarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void grabarFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_LOCALIDAD_MUESTREO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ESTACION_MUESTREO, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ALTURA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HORA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COORD_W, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COORD_E, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PH, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TEMPERATURA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CONDUCTIVIDAD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TURBIEDAD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OXIGENO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CLOROFILA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FICOCIANINA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERVACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODIGO_HEADER, OracleTypes.NUMBER));
			
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));	
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM242,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_DETALLE), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_LOCALIDAD_MUESTREO, formularioDetalle242.getLocalMuestreo());
			inputs.put(ConstantsLaboratorio.P_ESTACION_MUESTREO, formularioDetalle242.getEstacionMuestreo());
			
			LOG.info(">> ALTURA: " + formularioDetalle242.getAltura());
			inputs.put(ConstantsLaboratorio.P_ALTURA, formularioDetalle242.getAltura());
			inputs.put(ConstantsLaboratorio.P_HORA, formularioDetalle242.getHora());
			inputs.put(ConstantsLaboratorio.P_COORD_W, formularioDetalle242.getCoordenadaW());
			inputs.put(ConstantsLaboratorio.P_COORD_E, formularioDetalle242.getCoordenadaE());
			inputs.put(ConstantsLaboratorio.P_PH, formularioDetalle242.getPh());
			inputs.put(ConstantsLaboratorio.P_TEMPERATURA, formularioDetalle242.getTemperatura());
			inputs.put(ConstantsLaboratorio.P_CONDUCTIVIDAD, formularioDetalle242.getConductividad());
			inputs.put(ConstantsLaboratorio.P_TURBIEDAD, formularioDetalle242.getTurbiedad());
			inputs.put(ConstantsLaboratorio.P_OXIGENO, formularioDetalle242.getOxigeno());
			inputs.put(ConstantsLaboratorio.P_CLOROFILA, formularioDetalle242.getClorofila());
			inputs.put(ConstantsLaboratorio.P_FICOCIANINA, formularioDetalle242.getFicocianina());
			inputs.put(ConstantsLaboratorio.P_OBSERVACION, formularioDetalle242.getObservaciones());
			inputs.put(ConstantsLaboratorio.P_CODIGO_HEADER, formularioDetalle242.getCodigoHeader());
		
			inputs.put(Constants.A_PAR_USUARIO_CREACION, formularioDetalle242.getUsuarioCreacion());		
			inputs.put(Constants.A_PAR_USUARIO_MODIFICACION,formularioDetalle242.getUsuarioModificacion());		
			inputs.put(Constants.A_PAR_NOMBRE_PROGRAMA, formularioDetalle242.getPrograma());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#getFormularioHeader(java.lang.Integer)
	 */
	@Override
	public FormularioHeader242 getFormularioHeader(Integer id) {
		ArrayList ret = null;
		FormularioHeader242 result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM242).withProcedureName(ConstantsLaboratorio.PRC_GET_HEADER)
		.declareParameters(new SqlParameter(ConstantsLaboratorio.P_ID_REGISTRO, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormularioHeader242>() {

					@Override
					public FormularioHeader242 mapRow(ResultSet rs, int rowNum) throws SQLException {
						FormularioHeader242 record = new FormularioHeader242();
						
						record.setStrFechaMuestreo(rs.getString(1));
						record.setStrFechaRecepcion(rs.getString(2));
						record.setPersonaMuestrea(rs.getString(3));
						record.setPersonaRecepcion(rs.getString(4));
						record.setObservacion(rs.getString(5));
						record.setIdHeader(rs.getInt(6));
						record.setTransporteChofer(rs.getString(7));
						record.setCumpleEpp(rs.getString(8));
						
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(ConstantsLaboratorio.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_ID_REGISTRO, id);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (FormularioHeader242) ret.get(0);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#getListadoFormularioDetalleDuplicar(pe.com.sedapal.scr.core.beans.FormularioDetalle242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public List getListadoFormularioDetalleDuplicar(FormularioDetalle242 formularioDetalle242, Paginacion paginacion) {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM242).withProcedureName(ConstantsLaboratorio.SP_LISTADO_DETALLE242)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.P_COD_HEADER, Types.VARCHAR),
						new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormularioDetalle242>() {

									@SuppressWarnings("unchecked")
									@Override
									public FormularioDetalle242 mapRow(ResultSet rs, int rowNum) throws SQLException {
										FormularioDetalle242 detalle = new FormularioDetalle242();
										detalle.setLocalMuestreo(rs.getString(17)!=null && !rs.getString(17).trim().isEmpty()? rs.getInt(17) : 0);// LOCALIDAd, agregar un ultimo codigo de localidad
										detalle.setEstacionMuestreo( rs.getString(18)!=null && !rs.getString(18).trim().isEmpty()? rs.getInt(18) : 0); // ESTACION DE MUESTREO, agregar un ultimo codigo de estacion
										detalle.setCoordenadaW(rs.getString(3)!=null && !rs.getString(3).trim().isEmpty()? rs.getDouble(3) : 0); // COORDEN W
										detalle.setCoordenadaE(rs.getString(4)!=null && !rs.getString(4).trim().isEmpty()? rs.getDouble(4) : 0); // COORDEN E
										detalle.setAltura(rs.getString(5)!=null && !rs.getString(5).trim().isEmpty()? rs.getInt(5) : 0); // ALTURA  // agregar un ultimo codigo de estacion
										detalle.setHora(rs.getString(6)); // HORA
										detalle.setPh(rs.getString(7)!=null && !rs.getString(7).trim().isEmpty()? rs.getDouble(7) : 0); // PH
										detalle.setTemperatura(rs.getString(8)!=null && !rs.getString(8).trim().isEmpty()? rs.getDouble(8) : 0); // TEMP
										detalle.setConductividad(rs.getString(9)!=null && !rs.getString(9).trim().isEmpty()? rs.getDouble(9) : 0); // CONDUCTIV
										detalle.setTurbiedad(rs.getString(10)!=null && !rs.getString(10).trim().isEmpty()? rs.getDouble(10) : 0); // TURBIEDAD
										detalle.setOxigeno(rs.getString(11)!=null && !rs.getString(11).trim().isEmpty()? rs.getDouble(11) : 0); // OXIGENO
										detalle.setClorofila(rs.getString(12)!=null && !rs.getString(12).trim().isEmpty()? rs.getDouble(12) : 0); // CLOROF
										detalle.setFicocianina(rs.getString(13)!=null && !rs.getString(13).trim().isEmpty()? rs.getDouble(13) : 0); //ficoci
										detalle.setObservaciones(rs.getString(14)); // OBSERV
										detalle.setIdDetalle(rs.getString(15)!=null && !rs.getString(15).trim().isEmpty()? rs.getInt(15) : 0); // COD_DETALLE(N_CODMUE)	
//										record.add(rs.getString(1)); // LOCALIDAD
//										record.add(rs.getString(2)); // ESTACION DE MUESTREO
//										record.add(rs.getDouble(3)); // COORDEN W
//										record.add(rs.getDouble(4)); // COORDEN E
//										record.add(rs.getString(5)); // ALTURA
//										record.add(rs.getString(6)); // HORA
//										record.add(rs.getDouble(7)); // PH
//										record.add(rs.getDouble(8)); // TEMP
//										record.add(rs.getDouble(9)); // CONDUCTIV
//										record.add(rs.getDouble(10)); // TURBIEDAD
//										record.add(rs.getDouble(11)); // OXIGENO
//										record.add(rs.getDouble(12)); // CLOROF
//										record.add(rs.getDouble(13)); //ficoci
//										record.add(rs.getString(14)); // OBSERV
//										record.add(rs.getInt(15)); // COD_DETALLE(N_CODMUE)	
										return detalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_COD_HEADER, formularioDetalle242.getCodigoHeader());
		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());
		
		LOG.info("det formularioDetalle242.getCodigoHeader() :" + formularioDetalle242.getCodigoHeader());
		LOG.info("det paginacion.getColorderby() :" + paginacion.getColorderby());
		LOG.info("det paginacion.getColorderbydir() :" + paginacion.getColorderbydir());
		LOG.info("det paginacion.getPagdesde() :" + paginacion.getPagdesde());
		LOG.info("det paginacion.getCantidadpag() :" + paginacion.getCantidadpag());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);


		return lstRetorno;
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#getListadoFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioDetalle(FormularioDetalle242 formularioDetalle242, Paginacion paginacion) {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM242).withProcedureName(ConstantsLaboratorio.SP_LISTADO_DETALLE242)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.P_COD_HEADER, Types.VARCHAR),
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
										record.add(rs.getString(1)); // LOCALIDAD
										record.add(rs.getString(2)); // ESTACION DE MUESTREO
										record.add(rs.getDouble(3)); // COORDEN W
										record.add(rs.getDouble(4)); // COORDEN E
										record.add(rs.getString(5)); // ALTURA
										record.add(rs.getString(6)); // HORA
										record.add(rs.getDouble(7)); // PH
										record.add(rs.getDouble(8)); // TEMP
										record.add(rs.getDouble(9)); // CONDUCTIV
										record.add(rs.getDouble(10)); // TURBIEDAD
										record.add(rs.getDouble(11)); // OXIGENO
										record.add(rs.getDouble(12)); // CLOROF
										record.add(rs.getDouble(13)); //ficoci
										record.add(rs.getString(14)); // OBSERV
										record.add(rs.getInt(15)); // COD_DETALLE(N_CODMUE)										
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_COD_HEADER, formularioDetalle242.getCodigoHeader());
		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());
		
		LOG.info("det formularioDetalle242.getCodigoHeader() :" + formularioDetalle242.getCodigoHeader());
		LOG.info("det paginacion.getColorderby() :" + paginacion.getColorderby());
		LOG.info("det paginacion.getColorderbydir() :" + paginacion.getColorderbydir());
		LOG.info("det paginacion.getPagdesde() :" + paginacion.getPagdesde());
		LOG.info("det paginacion.getCantidadpag() :" + paginacion.getCantidadpag());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		Result result = new Result();
		result.setData(lstRetorno);
		result.setRecords((long) quantity);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#inactivarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void inactivarFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDDETALLE, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ESTADO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM242 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_INACTIVA_DETALLE, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_IDDETALLE, formularioDetalle242.getIdDetalle());
			inputs.put(ConstantsLaboratorio.P_ESTADO, formularioDetalle242.getEstado());
			inputs.put(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION,formularioDetalle242.getUsuarioModificacion());		
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#getFormularioDetalle(java.lang.Integer)
	 */
	@Override
	public FormularioDetalle242 getFormularioDetalle(Integer id) {
		ArrayList ret = null;
		FormularioDetalle242 result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM242).withProcedureName(ConstantsLaboratorio.PRC_GET_DETALLE)
		.declareParameters(new SqlParameter(ConstantsLaboratorio.P_ID_DETALLE, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormularioDetalle242>() {

					@Override
					public FormularioDetalle242 mapRow(ResultSet rs, int rowNum) throws SQLException {
						FormularioDetalle242 record = new FormularioDetalle242();
						
						record.setLocalMuestreo(rs.getInt(1));
						record.setEstacionMuestreo(rs.getInt(2));
						record.setCoordenadaW(rs.getDouble(3));
						record.setCoordenadaE(rs.getDouble(4));
						record.setAltura(rs.getInt(5));
						record.setHora(rs.getString(6));
						record.setPh(rs.getDouble(7));
						record.setTemperatura(rs.getDouble(8));
						record.setConductividad(rs.getDouble(9));
						record.setTurbiedad(rs.getDouble(10));
						record.setOxigeno(rs.getDouble(11));
						record.setClorofila(rs.getDouble(12));
						record.setObservaciones(rs.getString(13));
						record.setIdDetalle(rs.getInt(14));
						record.setFicocianina(rs.getDouble(15));
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(ConstantsLaboratorio.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_ID_DETALLE, id);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (FormularioDetalle242) ret.get(0);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#updateFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle242)
	 */
	@Override
	public void updateFormularioDetalle(FormularioDetalle242 formularioDetalle242) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_LOCALIDAD_MUESTREO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ESTACION_MUESTREO, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ALTURA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HORA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COORD_W, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COORD_E, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PH, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TEMPERATURA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CONDUCTIVIDAD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TURBIEDAD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OXIGENO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CLOROFILA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FICOCIANINA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERVACION, OracleTypes.VARCHAR));
//			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODIGO_HEADER, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDDETALLE, OracleTypes.NUMBER));	
//			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ESTADO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM242 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_UPDATE_DETALLE, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_LOCALIDAD_MUESTREO, formularioDetalle242.getLocalMuestreo());
			inputs.put(ConstantsLaboratorio.P_ESTACION_MUESTREO, formularioDetalle242.getEstacionMuestreo());
			LOG.info("alturaaaa: " + formularioDetalle242.getAltura());
			
			inputs.put(ConstantsLaboratorio.P_ALTURA, formularioDetalle242.getAltura());
			inputs.put(ConstantsLaboratorio.P_HORA, formularioDetalle242.getHora());
			inputs.put(ConstantsLaboratorio.P_COORD_W, formularioDetalle242.getCoordenadaW());
			inputs.put(ConstantsLaboratorio.P_COORD_E, formularioDetalle242.getCoordenadaE());
			inputs.put(ConstantsLaboratorio.P_PH, formularioDetalle242.getPh());
			inputs.put(ConstantsLaboratorio.P_TEMPERATURA, formularioDetalle242.getTemperatura());
			inputs.put(ConstantsLaboratorio.P_CONDUCTIVIDAD, formularioDetalle242.getConductividad());
			inputs.put(ConstantsLaboratorio.P_TURBIEDAD, formularioDetalle242.getTurbiedad());
			inputs.put(ConstantsLaboratorio.P_OXIGENO, formularioDetalle242.getOxigeno());
			inputs.put(ConstantsLaboratorio.P_CLOROFILA, formularioDetalle242.getClorofila());
			inputs.put(ConstantsLaboratorio.P_FICOCIANINA, formularioDetalle242.getFicocianina());
			inputs.put(ConstantsLaboratorio.P_OBSERVACION, formularioDetalle242.getObservaciones());
//			inputs.put(ConstantsLaboratorio.P_CODIGO_HEADER, formularioDetalle242.getCodigoHeader());
			inputs.put(ConstantsLaboratorio.P_IDDETALLE, formularioDetalle242.getIdDetalle());
//			inputs.put(ConstantsLaboratorio.P_ESTADO, formularioDetalle242.getEstado());
			inputs.put(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION,formularioDetalle242.getUsuarioModificacion());		
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#updateFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public void updateFormularioHeader(FormularioHeader242 formularioHeader242) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FECHA_MUESTREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FECHA_RECEPCION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PERSONA_MUESTREA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PERSONA_RECEPCION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERVACION, OracleTypes.VARCHAR));
		
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_USUARIO_MODIF, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NOMBRE_PROG, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TRACHO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CUMPLE, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODARE, OracleTypes.INTEGER));
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM242 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_UPDATE_HEADER242, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_ID_HEADER, formularioHeader242.getIdHeader());
			inputs.put(ConstantsLaboratorio.P_FECHA_MUESTREO, formularioHeader242.getStrFechaMuestreo());
			inputs.put(ConstantsLaboratorio.P_FECHA_RECEPCION, formularioHeader242.getStrFechaRecepcion());
			inputs.put(ConstantsLaboratorio.P_PERSONA_MUESTREA, formularioHeader242.getPersonaMuestrea());
			inputs.put(ConstantsLaboratorio.P_PERSONA_RECEPCION, formularioHeader242.getPersonaRecepcion());
			inputs.put(ConstantsLaboratorio.P_OBSERVACION, formularioHeader242.getObservacion());
	
			inputs.put(ConstantsLaboratorio.P_USUARIO_MODIF,formularioHeader242.getUsuarioModificacion());
			inputs.put(ConstantsLaboratorio.P_NOMBRE_PROG,formularioHeader242.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_TRACHO,formularioHeader242.getTransporteChofer());
			inputs.put(ConstantsLaboratorio.PAR_CUMPLE,formularioHeader242.getCumpleEpp());
			inputs.put(ConstantsLaboratorio.PAR_CODARE,formularioHeader242.getCodare());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario242Dao#inactivarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader242)
	 */
	@Override
	public void inactivarFormularioHeader(FormularioHeader242 formularioHeader242) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ESTADO, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM242 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_INACTIVA_HEADER, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_ID_HEADER, formularioHeader242.getIdHeader());
			inputs.put(ConstantsLaboratorio.P_ESTADO, formularioHeader242.getEstado());
			inputs.put(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION,formularioHeader242.getUsuarioModificacion());		
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	
}
