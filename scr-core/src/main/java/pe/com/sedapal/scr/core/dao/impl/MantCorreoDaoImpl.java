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
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.beans.MantCorreoBean;
import pe.com.sedapal.scr.core.beans.MantCorreoEditBean;
import pe.com.sedapal.scr.core.beans.ValidaUnicidadCorreoBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsCorreo;
import pe.com.sedapal.scr.core.dao.IMantCorreoDao;
import pe.com.sedapal.scr.core.dao.mapper.ConfigCorreoMapper;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;

// TODO: Auto-generated Javadoc
/**
 * The Class MantCorreoDaoImpl.
 */
@Repository
public class MantCorreoDaoImpl implements IMantCorreoDao {
	
	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;

	/**
	 * Realiza el registro de correos.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUINSERCION, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PROGRAMA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CODAREA, OracleTypes.NUMERIC));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CODSISTEMA, OracleTypes.NUMERIC));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_TIPOOPERACION, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PARA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CONCOPIA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_ASUNTO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CUERPO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_ORIGEN, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_NOMADJUNTO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTASERVIDORCORREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUARIOCORREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PASSWORDCORREO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTASERVIDORARCHIVO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUARIOARCHIVO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PASSWORDARCHIVO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PUERTO, OracleTypes.NUMERIC));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTADEFAULT, OracleTypes.NUMERIC));	
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_CORREO,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERTA_MANTCORREO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_CU_USUINSERCION, mantCorreoEditBean.getStrUsuIns());		
			inputs.put(Constants.PAR_CU_PROGRAMA, mantCorreoEditBean.getStrPrograma());	
			inputs.put(Constants.PAR_CU_CODAREA, mantCorreoEditBean.getLonCodArea());		
			inputs.put(Constants.PAR_CU_CODSISTEMA, mantCorreoEditBean.getLonCodSist());		
			inputs.put(Constants.PAR_CU_TIPOOPERACION, mantCorreoEditBean.getStrTipoOperacion());	
			inputs.put(Constants.PAR_CU_PARA, mantCorreoEditBean.getStrPara());		
			inputs.put(Constants.PAR_CU_CONCOPIA, mantCorreoEditBean.getStrCc());		
			inputs.put(Constants.PAR_CU_ASUNTO, mantCorreoEditBean.getStrAsunto());		
			inputs.put(Constants.PAR_CU_CUERPO, mantCorreoEditBean.getStrCuerpo());		
			inputs.put(Constants.PAR_CU_ORIGEN, mantCorreoEditBean.getStrOrigen());		
			inputs.put(Constants.PAR_CU_NOMADJUNTO, mantCorreoEditBean.getStrNomAdjunto());
			inputs.put(Constants.PAR_CU_RUTASERVIDORCORREO, mantCorreoEditBean.getStrRutaServCorreo());	
			inputs.put(Constants.PAR_CU_USUARIOCORREO, mantCorreoEditBean.getStrUsuCorreo());		
			inputs.put(Constants.PAR_CU_PASSWORDCORREO, mantCorreoEditBean.getStrPassCorreo());		
			inputs.put(Constants.PAR_CU_RUTASERVIDORARCHIVO, mantCorreoEditBean.getStrRutaServFile());	
			inputs.put(Constants.PAR_CU_USUARIOARCHIVO, mantCorreoEditBean.getStrUsuFile());		
			inputs.put(Constants.PAR_CU_PASSWORDARCHIVO, mantCorreoEditBean.getStrPassFile());	
			inputs.put(Constants.PAR_CU_PUERTO, mantCorreoEditBean.getLonPuerto());	
			inputs.put(Constants.PAR_CU_RUTADEFAULT, mantCorreoEditBean.getLonRutaDefault());	

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Obtiene el correo por identificador.
	 *
	 * @param idMantCorreo Identificador del registro
	 * @return the mant correo edit bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public MantCorreoEditBean obtenerMantenimientoCorreo(Integer idMantCorreo) throws Exception {
		List lstRetorno = null; 
		MantCorreoEditBean result=null;
		
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CORREO).withProcedureName(Constants.SP_GET_MANT_CORREO).declareParameters(
    					new SqlParameter(Constants.PAR_R_CODIGO, Types.INTEGER),    					
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MantCorreoEditBean>() {
							@Override
							public MantCorreoEditBean mapRow(ResultSet rs, int rowNum) throws SQLException {
								MantCorreoEditBean record = new MantCorreoEditBean();
								record.setLonCodigo(rs.getLong(1));
								record.setStrEstado(rs.getString(2));
								record.setStrTipoOperacion(rs.getString(3)); 
								record.setStrPara(rs.getString(4)); 
								record.setStrCc(rs.getString(5)); 
								record.setStrAsunto(rs.getString(6)); 
								record.setStrCuerpo(rs.getString(7)); 
								record.setStrOrigen(rs.getString(8)); 	 						
								record.setStrNomAdjunto(rs.getString(9)); 
								record.setStrRutaServCorreo(rs.getString(10)); 
								record.setLonPuerto(rs.getLong(11));
								record.setStrUsuCorreo(rs.getString(12));						
								record.setStrPassCorreo(rs.getString(13));
								record.setStrRutaServFile(rs.getString(14));
								record.setStrUsuFile(rs.getString(15));					
								record.setStrPassFile(rs.getString(16));
								record.setLonRutaDefault(rs.getLong(17));
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_R_CODIGO, idMantCorreo);
		
		
		Map<String,Object>  results = caller.execute(params);
		lstRetorno =  (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);   
		
		result = (MantCorreoEditBean)lstRetorno.get(0);
		
        return result;	
	}

	/**
	 * Realiza la modificación de correo.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CODIGO, OracleTypes.NUMERIC));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUEDICION, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PROGRAMA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CODAREA, OracleTypes.NUMERIC));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CODSISTEMA, OracleTypes.NUMERIC));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_ESTADO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PARA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CONCOPIA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_ASUNTO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_CUERPO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_ORIGEN, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_NOMADJUNTO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTASERVIDORCORREO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUARIOCORREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PASSWORDCORREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTASERVIDORARCHIVO, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_USUARIOARCHIVO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PASSWORDARCHIVO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_PUERTO, OracleTypes.NUMERIC));
			lstParamsInput.add(new SqlParameter(Constants.PAR_CU_RUTADEFAULT, OracleTypes.NUMERIC));

			lstParamsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_CORREO,ConstantsCommon.P_SEPARADOR,Constants.SP_UPDATE_MANTCORREO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_CU_CODIGO, mantCorreoEditBean.getLonCodigo());
			inputs.put(Constants.PAR_CU_USUEDICION, mantCorreoEditBean.getStrUsuUpd());		
			inputs.put(Constants.PAR_CU_PROGRAMA, mantCorreoEditBean.getStrPrograma());	
			inputs.put(Constants.PAR_CU_CODAREA, mantCorreoEditBean.getLonCodArea());		
			inputs.put(Constants.PAR_CU_CODSISTEMA, mantCorreoEditBean.getLonCodSist());		
			inputs.put(Constants.PAR_CU_ESTADO, mantCorreoEditBean.getStrEstado());		
			inputs.put(Constants.PAR_CU_PARA, mantCorreoEditBean.getStrPara());		
			inputs.put(Constants.PAR_CU_CONCOPIA, mantCorreoEditBean.getStrCc());		
			inputs.put(Constants.PAR_CU_ASUNTO, mantCorreoEditBean.getStrAsunto());		
			inputs.put(Constants.PAR_CU_CUERPO, mantCorreoEditBean.getStrCuerpo());		
			inputs.put(Constants.PAR_CU_ORIGEN, mantCorreoEditBean.getStrOrigen());		
			inputs.put(Constants.PAR_CU_NOMADJUNTO, mantCorreoEditBean.getStrNomAdjunto());
			inputs.put(Constants.PAR_CU_RUTASERVIDORCORREO, mantCorreoEditBean.getStrRutaServCorreo());		
			inputs.put(Constants.PAR_CU_USUARIOCORREO, mantCorreoEditBean.getStrUsuCorreo());	
			inputs.put(Constants.PAR_CU_PASSWORDCORREO, mantCorreoEditBean.getStrPassCorreo());		
			inputs.put(Constants.PAR_CU_RUTASERVIDORARCHIVO, mantCorreoEditBean.getStrRutaServFile());		
			inputs.put(Constants.PAR_CU_USUARIOARCHIVO, mantCorreoEditBean.getStrUsuFile());	
			inputs.put(Constants.PAR_CU_PASSWORDARCHIVO, mantCorreoEditBean.getStrPassFile());		
			inputs.put(Constants.PAR_CU_PUERTO, mantCorreoEditBean.getLonPuerto());		
			inputs.put(Constants.PAR_CU_RUTADEFAULT, mantCorreoEditBean.getLonRutaDefault());
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Método que permite obtener el listado de Correos.
	 *
	 * @param mantCorreoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Result obtenerMantCorreo(MantCorreoBean mantCorreoBean, Paginacion paginacion) throws Exception {
		
		List lstRetorno = null; 
		
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CORREO).withProcedureName(Constants.SP_SEARCH_MANTCORREO).declareParameters(
    					new SqlParameter(Constants.PAR_TIPOOPERACION, Types.VARCHAR),
    					new SqlParameter(Constants.PAR_ESTADO, Types.VARCHAR),
    			
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
								record.add(rs.getLong(1)); //"n_codigo"
								record.add(rs.getString(2)); //"v_tipooperacion"
								record.add(rs.getString(3)); //"a_usuins"
								record.add(rs.getString(4)); //"a_fecins"
								record.add(rs.getString(5)); //"a_usuupd"		 						
								record.add(rs.getString(6)); //"a_fecupd"		
								record.add(rs.getString(7)); //v_estado
								record.add(rs.getString(8)); // cod_tipoOperacion
								return record;
							}
						} )						
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue(Constants.PAR_TIPOOPERACION, mantCorreoBean.getCodTipoOperacion());
		params.addValue(Constants.PAR_ESTADO, mantCorreoBean.getEstado());
		
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
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IMantCorreoDao#inactivarCorreo(pe.com.sedapal.scr.core.beans.MantCorreoBean)
	 */
	@Override
	public void inactivarCorreo(MantCorreoBean mantCorreoBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_CODIGO, OracleTypes.VARCHAR));			
			paramsInput.add(new SqlParameter(ConstantsCommon.PAR_VUSER_UPDATE, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(ConstantsCommon.PAR_VPROGRAM_UPDATE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODAREA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODSIST, OracleTypes.NUMBER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_CORREO,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANTCORREO), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_CODIGO, mantCorreoBean.getCodigo());			
			inputs.put(ConstantsCommon.PAR_VUSER_UPDATE, mantCorreoBean.getaUsuUpd());		
			inputs.put(ConstantsCommon.PAR_VPROGRAM_UPDATE, mantCorreoBean.getaPrograma()); 	
			inputs.put(Constants.PAR_ACODAREA, mantCorreoBean.getLonCodArea());
			inputs.put(Constants.PAR_ACODSIST, mantCorreoBean.getLonCodSist());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IMantCorreoDao#obtenerInformacionValidacion()
	 */
	@Override
	public ValidaUnicidadCorreoBean obtenerInformacionValidacion() throws Exception {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_CORREO).withProcedureName(Constants.SP_GET_VALIDATION_INFO).declareParameters(				
    			new SqlOutParameter(Constants.PAR_OUT_CANT_RESUMEN, Types.INTEGER ),	
    			new SqlOutParameter(Constants.PAR_OUT_CANT_AUTOMATICO, Types.INTEGER ),
    			new SqlOutParameter(Constants.PAR_OUT_CANT_ENVIOS, Types.INTEGER ),
    			new SqlOutParameter(Constants.PAR_OUT_CANT_GERENCIA, Types.INTEGER )
    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		
		Map<String,Object>  results = caller.execute(params);
		int quantityResumen = (int) results.get(Constants.PAR_OUT_CANT_RESUMEN);
		int quantityAutomatico = (int) results.get(Constants.PAR_OUT_CANT_AUTOMATICO);
		int quantityEnvios = (int) results.get(Constants.PAR_OUT_CANT_ENVIOS);
		int quantityGerencia = (int) results.get(Constants.PAR_OUT_CANT_GERENCIA);
		
		ValidaUnicidadCorreoBean vucb = new ValidaUnicidadCorreoBean();
		vucb.setBolExistReporteResumen(quantityResumen > 0);
		vucb.setBolExistProcesoAutomatico(quantityAutomatico > 0);
		vucb.setBolExistEnvios(quantityEnvios > 0);
		vucb.setBolExistReporteGerencia(quantityGerencia > 0);
		
        return vucb;	
	}

	/**
	 * Método que permite obtener datos de configuración de correo para un periodo y un estado.
	 *
	 * @param strTipoOperacion the str tipo operacion
	 * @param strEstado the str estado
	 * @return the configuracion correo
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ConfiguracionCorreo que contiene los datos de configuración de correo
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConfiguracionCorreo obtenerConfigCorreo(String strTipoOperacion, String strEstado) throws Exception {
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		
		caller.withCatalogName(ConstantsCorreo.PCK_CORREO)
		      .withProcedureName(ConstantsCorreo.SP_GET_CONFIG_CORREO)
		      .declareParameters(
				new SqlParameter(ConstantsCorreo.PAR_V_TIPOPR, Types.VARCHAR),
				new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR),
				new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new ConfigCorreoMapper()))
		      .withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsCorreo.PAR_V_TIPOPR, strTipoOperacion);
		params.addValue(Constants.PAR_V_ESTADO, ConstantsCorreo.ESTADO_ACTIVO);

		Map<String, Object> mapResults = caller.execute(params);
		List<ConfiguracionCorreo> lstConfigCorreo = (List<ConfiguracionCorreo>) mapResults.get(Constants.PAR_OUT_CURSOR);
		
		if(lstConfigCorreo != null && lstConfigCorreo.size() > 0) {
			return lstConfigCorreo.get(0);
		} else {
			return null;
		}
	}
}
