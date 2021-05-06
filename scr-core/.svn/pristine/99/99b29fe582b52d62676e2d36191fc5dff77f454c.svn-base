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
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.scr.core.beans.BeanComodin;
import pe.com.sedapal.scr.core.beans.Formulario153;
import pe.com.sedapal.scr.core.beans.ReporteMuestraPdf;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroDeMuestraDaoImpl.
 */
@Repository
public class RegistroDeMuestraDaoImpl implements IRegistroDeMuestraDao {
	
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
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerRegistros(pe.com.sedapal.scr.core.beans.Formulario153, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerRegistros(Formulario153 formulario153, Paginacion paginacion) throws Exception {
		
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_LISTADO_REG_MUESTRAS)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_FECHA_INICIO, Types.VARCHAR),
//						new SqlParameter(Constants.PAR_FECHA_FIN, Types.DATE),
//						new SqlParameter(Constants.PAR_VESTADO_CLIMA, Types.VARCHAR),

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
										String fechaMuestreo = rs.getString(1) ;
										if(fechaMuestreo!=null && fechaMuestreo.length() > 10){
											fechaMuestreo = fechaMuestreo.substring(0, 10);
										}
										record.add(fechaMuestreo); // "FEC"
										record.add(rs.getString(2)); // "HORA"
										record.add(rs.getString(3)); // "COD_FRASCO"
										record.add(rs.getString(4)); // "PUNTO MUESTREO"
										record.add(rs.getString(5)); // "MATRIZ"
										record.add(rs.getString(6)); // "TIPO_ANALISIS"
										record.add(rs.getString(7)); // "TIPO_FRASCO"
										record.add(rs.getString(8)); // "TEMPERAT"
										record.add(rs.getString(9)); // "PERSONA_MUESTREO"
										record.add(rs.getString(10)); // "REC_ANALISTA"
										record.add(rs.getString(11)); // "APLIC MED SEG"
										record.add(rs.getInt(12)); // N_CODCUS (PK)

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_FECHA_INICIO, formulario153.getStrFechaHora());
//		params.addValue(Constants.PAR_FECHA_FIN, formulario153.getAbreviatura());
//		params.addValue(Constants.PAR_VESTADO_CLIMA, climaBean.getEstado());

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
		//return null;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerFormulario153(java.lang.Integer)
	 */
	@Override
	public Formulario153 obtenerFormulario153(Integer nid) {
		ArrayList ret = null;
		Formulario153 result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKG_REG_MUESTRAS).withProcedureName(ConstantsLaboratorio.PRC_GET_FORMULARIO153)
		.declareParameters(new SqlParameter(ConstantsLaboratorio.P_ID_REGISTRO, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<Formulario153>() {

					@Override
					public Formulario153 mapRow(ResultSet rs, int rowNum) throws SQLException {
						Formulario153 record = new Formulario153();
						
						record.setStrFechaHora(rs.getString(1));
						record.setHora(rs.getString(2));
						record.setCodigo(String.valueOf(rs.getInt(3)));
						record.setCodigoFrasco(rs.getString(4));
						record.setPuntoMuestreo(rs.getInt(5));
						record.setMatriz(rs.getInt(6));
						record.setTipoAnalisis(rs.getInt(7));
						record.setTipoFrasco(rs.getInt(8));
						record.setTemperatura(rs.getString(9));
						record.setPersonaMuestrea(rs.getInt(10));
						record.setRecepcionAnalista(rs.getInt(11));
						record.setAplicacionMedidas(rs.getString(12));
						record.setObservacion(rs.getString(13));
						
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(ConstantsLaboratorio.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_ID_REGISTRO, nid);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (Formulario153) ret.get(0);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#grabarMuestra(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public int grabarMuestra(Formulario153 formulario153)  {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		int intRetorno=-1;
		System.out.println("DAO_IMPLEMENTACION: " +formulario153.getStrFechaHora());
		
		if(formulario153.getAplicacionMedidas()==null || formulario153.getAplicacionMedidas().equals("")){
			formulario153.setAplicacionMedidas(" ");
		}
		if(formulario153.getTemperatura()==null || formulario153.getTemperatura().equals("")){
			formulario153.setTemperatura(" ");
		}
		if(formulario153.getObservacion()==null || formulario153.getObservacion().equals("")){
			formulario153.setObservacion(" ");
		}
		
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_FECHA_REGISTRO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_COD_FRASCO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_MATRIZ, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_PERSONA_MUESTREA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_TIPO_ANALISIS, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_RECEP_ANALISTA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_PUNTO_MUESTREO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_TIPO_FRASCO, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_MEDIDAS_SEGURIDAD, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_TEMP_PRESERVACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_HORA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_OBSERVACION, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));	
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PKG_REG_MUESTRAS,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_MUESTRA), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_FECHA_REGISTRO, formulario153.getStrFechaHora());
			inputs.put(Constants.PAR_COD_FRASCO, formulario153.getCodigoFrasco());	
			inputs.put(Constants.PAR_MATRIZ, formulario153.getMatriz());
			inputs.put(Constants.PAR_PERSONA_MUESTREA, formulario153.getPersonaMuestrea());
			inputs.put(Constants.PAR_TIPO_ANALISIS, formulario153.getTipoAnalisis());
			inputs.put(Constants.PAR_RECEP_ANALISTA, formulario153.getRecepcionAnalista());
			inputs.put(Constants.PAR_PUNTO_MUESTREO, formulario153.getPuntoMuestreo());
			inputs.put(Constants.PAR_TIPO_FRASCO, formulario153.getTipoFrasco());
			inputs.put(Constants.PAR_MEDIDAS_SEGURIDAD, formulario153.getAplicacionMedidas());
			inputs.put(Constants.PAR_TEMP_PRESERVACION, formulario153.getTemperatura());
			inputs.put(Constants.PAR_HORA, formulario153.getHora());
			inputs.put(Constants.PAR_OBSERVACION, formulario153.getObservacion());	
			inputs.put(Constants.A_PAR_USUARIO_CREACION, formulario153.getUsuarioCreacion());		
			inputs.put(Constants.A_PAR_USUARIO_MODIFICACION,formulario153.getUsuarioModificacion());		
			inputs.put(Constants.A_PAR_NOMBRE_PROGRAMA, formulario153.getPrograma());	
			
			
			//this.execSp.executeSp(inputs);
			Map<String, Integer> outputs = this.execSp.executeSp(inputs);
			intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
			return intRetorno;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#actualizarFormulario(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public void actualizarFormulario(Formulario153 formulario153) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		if(formulario153.getAplicacionMedidas()==null || formulario153.getAplicacionMedidas().equals("")){
			formulario153.setAplicacionMedidas(" ");
		}
		if(formulario153.getTemperatura()==null || formulario153.getTemperatura().equals("")){
			formulario153.setTemperatura(" ");
		}
		if(formulario153.getObservacion()==null || formulario153.getObservacion().equals("")){
			formulario153.setObservacion(" ");
		}
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));
		//	paramsInput.add(new SqlParameter(ConstantsLaboratorio.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ID_REGISTRO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COD_FRASCO, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECHA_REGISTRO, OracleTypes.VARCHAR)); 
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_HORA_REGISTRO, OracleTypes.VARCHAR)); 
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_MATRIZ, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERSONA_MUESTREA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TIPO_ANALISIS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_RECEP_ANALISTA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PUNTO_MUESTREO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TIPO_FRASCO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_MEDIDAS_SEGURIDAD, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TEMP_PRESERVACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_OBSERVACION, OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKG_REG_MUESTRAS+ConstantsLaboratorio.P_SEPARADOR+ConstantsLaboratorio.SP_UPDATE_FORMULARIO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.A_PAR_USUARIO_MODIFICACION, formulario153.getUsuarioModificacion());
		//	inputs.put(ConstantsLaboratorio.A_PAR_NOMBRE_PROGRAMA, formulario153.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_ID_REGISTRO, formulario153.getCodigo());
			inputs.put(ConstantsLaboratorio.PAR_COD_FRASCO, formulario153.getCodigoFrasco());
			inputs.put(ConstantsLaboratorio.PAR_FECHA_REGISTRO, formulario153.getStrFechaHora());
			inputs.put(ConstantsLaboratorio.PAR_HORA_REGISTRO, formulario153.getHora());
			inputs.put(ConstantsLaboratorio.PAR_MATRIZ, formulario153.getMatriz());
			inputs.put(ConstantsLaboratorio.PAR_PERSONA_MUESTREA, formulario153.getPersonaMuestrea());
			inputs.put(ConstantsLaboratorio.PAR_TIPO_ANALISIS, formulario153.getTipoAnalisis());
			inputs.put(ConstantsLaboratorio.PAR_RECEP_ANALISTA, formulario153.getRecepcionAnalista());
			inputs.put(ConstantsLaboratorio.PAR_PUNTO_MUESTREO, formulario153.getPuntoMuestreo());
			inputs.put(ConstantsLaboratorio.PAR_TIPO_FRASCO, formulario153.getTipoFrasco());
			inputs.put(ConstantsLaboratorio.PAR_MEDIDAS_SEGURIDAD, formulario153.getAplicacionMedidas());
			inputs.put(ConstantsLaboratorio.PAR_TEMP_PRESERVACION, formulario153.getTemperatura());
			inputs.put(Constants.PAR_OBSERVACION, formulario153.getObservacion());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#generarReporteMuestraPdf(java.lang.String)
	 */
	@Override
	public List<ReporteMuestraPdf> generarReporteMuestraPdf(String fecha) {
		
		List<ReporteMuestraPdf> lstRetorno = null; 
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKG_REG_MUESTRAS).withProcedureName(ConstantsLaboratorio.SP_GET_REG_MUESTRAS).declareParameters(    					
					new SqlParameter(ConstantsLaboratorio.PAR_FECHA, Types.VARCHAR),						
					new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteMuestraPdf>() {

						@Override
						public ReporteMuestraPdf mapRow(ResultSet rs, int rowNum) throws SQLException {
							ReporteMuestraPdf record = new ReporteMuestraPdf();								
							record.setFECHA(rs.getString(1)); // fecha
							record.setCODIGOFRASCO(rs.getString(2)); // c_frasco
							record.setPUNTOMUESTREO(rs.getString(3)); // V_DIA
							record.setMATRIZ(rs.getString(4)); // MATRIZ
							record.setTIPOANALISIS(rs.getString(5)); // ANALISIS 		 						
							record.setTIPOFRASCO(rs.getString(6)); // t_fras
							record.setTEMPERATURA(rs.getString(7)); //
							record.setPERSONAMUESTREO(rs.getString(8));
							record.setRECEPCIONANALISTA(rs.getString(9)); // 
							record.setSEGURIDAD(rs.getString(10)); // 
							record.setIDREGISTRO(rs.getInt(11)); // 
							return record;
						}
					} )						
			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
	
	MapSqlParameterSource params = new MapSqlParameterSource();		
	params.addValue(ConstantsLaboratorio.PAR_FECHA, fecha);		
	
	Map<String,Object>  results = caller.execute(params);		
	lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
	
    return lstRetorno;
    
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#eliminarFormulario(pe.com.sedapal.scr.core.beans.Formulario153)
	 */
	@Override
	public void eliminarFormulario(Formulario153 formulario153) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_REGISTRO, OracleTypes.VARCHAR));
//			paramsInput.add(new SqlParameter(ConstantsLaboratorio.P_U_MODIFICACION, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(ConstantsLaboratorio.PKG_REG_MUESTRAS,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.PRC_ELIMINA_FORMULARIO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_ID_REGISTRO, formulario153.getCodigo());	
//			inputs.put(ConstantsLaboratorio.P_U_MODIFICACION, formulario153.getUsuarioModificacion());
			this.execSp.executeSp(inputs);

		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerPuntoMuestreoCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerPuntoMuestreoCbo() {
		
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_PUNTO_MUESTREO)
				.declareParameters(
						// parametros de búsqueda
//						new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR),

						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue(Constants.PAR_V_ESTADO, estado);

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerMatrizCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerMatrizCbo() {
		
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_CBO_MATRIZ)
				.declareParameters(
	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerTipoAnalisisCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerTipoAnalisisCbo() {
		
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_CBO_TIPO_ANALISIS)
				.declareParameters(
	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerTipoFrascoCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerTipoFrascoCbo() {
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_CBO_TIPO_FRASCO)
				.declareParameters(
	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerPersonaMuestreaCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerPersonaMuestreaCbo() {
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_CBO_PERSONA_MUESTREA)
				.declareParameters(
	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IRegistroDeMuestraDao#obtenerRecepAnalistaCbo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BeanComodin> obtenerRecepAnalistaCbo() {
		List<BeanComodin> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PKG_REG_MUESTRAS).withProcedureName(Constants.SP_CBO_RECEP_ANALISTA)
				.declareParameters(
	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<BeanComodin>() {

									@Override
									public BeanComodin mapRow(ResultSet rs, int rowNum) throws SQLException {
										BeanComodin record = new BeanComodin();
										record.setCodigo(rs.getInt(1));
										record.setNombre(rs.getString(2)); // "n_codigo"
									
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		Map<String, Object> results = caller.execute();
		lstRetorno = (List<BeanComodin>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}


}
