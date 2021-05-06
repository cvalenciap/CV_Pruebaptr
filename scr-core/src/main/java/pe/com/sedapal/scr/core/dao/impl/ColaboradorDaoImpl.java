
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
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.beans.ValidaAbreviaturaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantColaboradorDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ColaboradorDaoImpl.
 */
@Repository
public class ColaboradorDaoImpl implements IMantColaboradorDao {
	
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
	
	/**
	 * Método que permite obtener el listado de Represa.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Result obtenerMantColaborador(ColaboradorBean colaboradorBean, Paginacion paginacion) throws Exception {

		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_COLABORADOR).withProcedureName(Constants.SP_SEARCH_MANTCOLABORADOR)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_VNOMBRE_COLABORADOR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VNOMBRE_REPRESA_COLABORAD, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VESTADO_COLABORADOR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_VTIPO_TRABAJADOR, Types.VARCHAR),

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
										record.add(rs.getLong(1)); // "n_codigo"
										record.add(rs.getString(2)!=null?rs.getString(2).toUpperCase():rs.getString(2)); // "c_abrevi"
										record.add(rs.getString(3)!=null?rs.getString(3).toUpperCase():rs.getString(3)); // "v_nombreColaborador"
										record.add(rs.getString(4)!=null?rs.getString(4).toUpperCase():rs.getString(4)); // "v_nomemp"
										record.add(rs.getString(5)!=null?rs.getString(5).toUpperCase():rs.getString(5)); // "v_tipo_trabajador"
										record.add(rs.getString(6)); // "v_estado"
										record.add(rs.getString(7)!=null?rs.getString(7).toUpperCase():rs.getString(7)); // "a_usuaColaboradorcreacion"
										record.add(rs.getString(8)); // "a_fechacreacion"
										record.add(rs.getString(9)!=null?rs.getString(9).toUpperCase():rs.getString(9)); // "v_usuaClimaModificacion"
										record.add(rs.getString(10)); // "v_fechamodificacion"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_VNOMBRE_COLABORADOR, colaboradorBean.getNombreColaborador());
		params.addValue(Constants.PAR_VNOMBRE_REPRESA_COLABORAD, colaboradorBean.getNombreEmpresa());
		params.addValue(Constants.PAR_VESTADO_COLABORADOR, colaboradorBean.getEstado());
		params.addValue(Constants.PAR_VTIPO_TRABAJADOR, colaboradorBean.getTipoTrabajador());

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
	 * Obtiene el colaborador por identificador.
	 *
	 * @param id the id
	 * @return the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo colaboradorBean que contiene el registro
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ColaboradorBean obtenerColaborador(Integer id) throws Exception {
		ArrayList ret = null;
		ColaboradorBean result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_COLABORADOR).withProcedureName(Constants.PRC_GET_COLABORADOR)
		.declareParameters(new SqlParameter(Constants.PAR_NCODIGO_COLABORADOR, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ColaboradorBean>() {

					@Override
					public ColaboradorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ColaboradorBean record = new ColaboradorBean();
						record.setCodigo(rs.getString(1));
						record.setNombreColaborador(rs.getString(2));
						record.setDni(rs.getString(3));
						record.setNombreEmpresa(rs.getString(4));
						record.setAbreviatura(rs.getString(5));
						record.setEstado(rs.getString(6));
						
						
						record.setPrograma(rs.getString(7));
						record.setCodigoArea(rs.getInt(8));
						record.setCodigoSistema(rs.getInt(9));
						record.setTipoTrabajador(rs.getString(10));
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_NCODIGO_COLABORADOR, id);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (ColaboradorBean) ret.get(0);
		
		return result;
	}
	
	/**
	 * Realiza el cambio de estado de colaborador.
	 *
	 * @param colaboradorBean objeto del tipo ColaboradorBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarColaborador(ColaboradorBean colaboradorBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_COLABORADOR, OracleTypes.VARCHAR));
			
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUACOLABORADOR_MODIFICA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_COLABORADOR, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_COLABORADOR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, OracleTypes.INTEGER));	

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), CoreUtils.concatenar(Constants.PACKAGE_COLABORADOR,ConstantsCommon.P_SEPARADOR,Constants.SP_INACTIVA_MANTCOLABORADOR),
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_COLABORADOR, colaboradorBean.getCodigo());	
			
		
			
			inputs.put(Constants.PAR_AUSUACOLABORADOR_MODIFICA,colaboradorBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_COLABORADOR, colaboradorBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_COLABORADOR, colaboradorBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, colaboradorBean.getCodigoSistema());	

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Realiza el registro de represa.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarColaborador(ColaboradorBean colaboradorBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_COLABORADOR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_EMPRESA, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_VNUMERO_DOCUMENTO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_VESTADO_COLABORADOR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VTIPO_TRABAJADOR_COLABORA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_COLABORADOR, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_CCODIGO_FICHA, OracleTypes.VARCHAR));
			
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUACOLABORADOR_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_AUSUACOLABORADOR_MODIFICA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_COLABORADOR, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_COLABORADOR , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, OracleTypes.INTEGER));

			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(Constants.PACKAGE_COLABORADOR,ConstantsCommon.P_SEPARADOR,Constants.SP_INSERT_COLABORADOR),
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_VNOMBRE_COLABORADOR, colaboradorBean.getNombreColaborador());
			inputs.put(Constants.PAR_VNOMBRE_EMPRESA, colaboradorBean.getNombreEmpresa());	
			inputs.put(Constants.PAR_VNUMERO_DOCUMENTO, colaboradorBean.getDni());
			inputs.put(Constants.PAR_VESTADO_COLABORADOR, colaboradorBean.getEstado());
			inputs.put(Constants.PAR_VTIPO_TRABAJADOR_COLABORA, colaboradorBean.getTipoTrabajador());	
			inputs.put(Constants.PAR_VABREVIATURA_COLABORADOR, colaboradorBean.getAbreviatura());
			inputs.put(Constants.PAR_CCODIGO_FICHA, colaboradorBean.getTipoTrabajador().trim().equals(Constants.TIPO_TRABAJADOR_INTERNO)?colaboradorBean.getAbreviatura():Constants.DEFAULT_NUMBER_INT);
			
			inputs.put(Constants.PAR_AUSUACOLABORADOR_CREACION, colaboradorBean.getUsuarioCreacion());		
			inputs.put(Constants.PAR_AUSUACOLABORADOR_MODIFICA,colaboradorBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_COLABORADOR, colaboradorBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_COLABORADOR, colaboradorBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, colaboradorBean.getCodigoSistema());		
		

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	
	/**
	 * Realiza la modificación de represa.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarColaborador(ColaboradorBean colaboradorBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.PAR_NCODIGO_COLABORADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_COLABORADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VNOMBRE_EMPRESA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VNUMERO_DOCUMENTO, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_VABREVIATURA_COLABORADOR, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.PAR_VESTADO_COLABORADOR, OracleTypes.VARCHAR));
	
			paramsInput.add(new SqlParameter(Constants.PAR_AUSUACOLABORADOR_MODIFICA, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_APROGRAMA_COLABORADOR, OracleTypes.VARCHAR));	
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_AREA_COLABORADOR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, OracleTypes.INTEGER));	
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), Constants.PACKAGE_COLABORADOR+Constants.P_SEPARADOR+Constants.SP_UPDATE_COLABORADOR,
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_NCODIGO_COLABORADOR, colaboradorBean.getCodigo());
			inputs.put(Constants.PAR_VNOMBRE_COLABORADOR, colaboradorBean.getNombreColaborador());
			inputs.put(Constants.PAR_VNOMBRE_EMPRESA, colaboradorBean.getNombreEmpresa());
			inputs.put(Constants.PAR_VNUMERO_DOCUMENTO, colaboradorBean.getDni());
			inputs.put(Constants.PAR_VABREVIATURA_COLABORADOR, colaboradorBean.getAbreviatura());
			inputs.put(Constants.PAR_VESTADO_COLABORADOR, colaboradorBean.getEstado());	
			
			inputs.put(Constants.PAR_AUSUACOLABORADOR_MODIFICA,colaboradorBean.getUsuarioModificacion());		
			inputs.put(Constants.PAR_APROGRAMA_COLABORADOR, colaboradorBean.getPrograma());		
			inputs.put(Constants.PAR_ACODIGO_AREA_COLABORADOR, colaboradorBean.getCodigoArea());		
			inputs.put(Constants.PAR_ACODIGO_SISTEMA_COLABORAD, colaboradorBean.getCodigoSistema());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/**
	 * Obtiene la lista de colaboradores según su estado.
	 *
	 * @param estado Contiene el estado de los datos que se desean listar
	 * @return Lista de objetos de tipo ColaboradorBean que contiene los datos de colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ColaboradorBean> obtenerColaboradores(String estado) throws Exception {
		List<ColaboradorBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_COLABORADOR).withProcedureName(Constants.SP_LISTA_COLABORADOR)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_ESTADO_LCOLABORADOR, Types.VARCHAR),

						new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ColaboradorBean>() {

									@Override
									public ColaboradorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ColaboradorBean record = new ColaboradorBean();
										record.setLonCodigo(rs.getLong(1)); // "n_codigo"
										record.setNombreColaborador(rs.getString(2)); // "v_nombrecolaborador"
										record.setDni(rs.getString(3)); // "n_dni"
										record.setEstado(rs.getString(4)); // "v_estado"
										record.setAbreviatura(rs.getString(5));
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_ESTADO_LCOLABORADOR, estado);

		Map<String, Object> results = caller.execute(params);
		lstRetorno = (List<ColaboradorBean>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		return lstRetorno;
	}	

	/**
	 *  
	 * Valida si existen colaboradores activos con misma abreviatura.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @return the valida abreviatura bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ValidaAbreviaturaBean que contiene la información de validación
	 */
	@Override
	public ValidaAbreviaturaBean validarAbrevMantColaborador(ColaboradorBean colaboradorBean) throws Exception {
    	SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
    	caller.withCatalogName(Constants.PACKAGE_COLABORADOR).withProcedureName(Constants.PRC_VALIDA_ABREV_COLABORADOR).declareParameters(				

    			new SqlParameter(Constants.PAR_VABREVIATURA, Types.VARCHAR),
    			new SqlOutParameter(Constants.PRC_SALIDA_VAL_ABREV_COLABOR, Types.INTEGER) 

    			).withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
    	
		MapSqlParameterSource params = new MapSqlParameterSource();		
		
		params.addValue(Constants.PAR_VABREVIATURA, colaboradorBean.getAbreviatura().trim());
		
		Map<String,Object>  results = caller.execute(params);
		int saliValAbrev = (int) results.get(Constants.PRC_SALIDA_VAL_ABREV_COLABOR);

		ValidaAbreviaturaBean vabrev = new ValidaAbreviaturaBean();
		vabrev.setBolAbreviatura(saliValAbrev > 0);
		
        return vabrev;	
	}

	/**
	 * Obtiene una lista de trabajadores de sedepal.
	 *
	 * @param trabajadorBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Result obtenerListaDeTrabajadores(TrabajadorBean trabajadorBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(Constants.PACKAGE_TRABAJADOR).withProcedureName(Constants.SP_SEARCH_TRABAJADOR)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(Constants.PAR_FICHA_TRABAJADOR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_NOMBRE_TRABAJADOR, Types.VARCHAR),

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
										record.add(rs.getString(1));     // "ficha"
										record.add(rs.getString(2));    // "nombre_completo"
										record.add(rs.getString(3));   // "dni"
										record.add(rs.getString(4));  // "empresa"
										record.add(rs.getString(5)); // "tipo"
										record.add(rs.getLong(6));  // "codigo"
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(Constants.PAR_FICHA_TRABAJADOR, trabajadorBean.getNumFicha());
		params.addValue(Constants.PAR_NOMBRE_TRABAJADOR, trabajadorBean.getNombreCompleto());

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
	 * Obtiene el trabajador por su código de ficha.
	 *
	 * @param intNumFicha Identificador del registro
	 * @return the trabajador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo TrabajadorBean que contiene el registro
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TrabajadorBean obtenerTrabajador(Integer intNumFicha)  throws Exception{
		ArrayList<TrabajadorBean> ret = null;
		TrabajadorBean result = null;
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(Constants.PACKAGE_TRABAJADOR).withProcedureName(Constants.PRC_GET_TRABAJADOR)
			.declareParameters(new SqlParameter(Constants.PAR_FICHA_TRABAJADOR, Types.INTEGER), new SqlOutParameter(
					Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TrabajadorBean>() {

						@Override
						public TrabajadorBean mapRow(ResultSet rs, int rowNum) throws SQLException {
							TrabajadorBean record = new TrabajadorBean();
							record.setNumFicha(rs.getInt(1)+"");
							record.setNombreCompleto(rs.getString(2).toUpperCase().trim());
							record.setNumDocumento(rs.getString(3));
							record.setNombreEmpresa(rs.getString(4));
							record.setTipoTrabajador(rs.getString(5));
							record.setCodigo(rs.getInt(6) + "");
							return record;
						}

					}))
			.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(Constants.PAR_FICHA_TRABAJADOR, intNumFicha);

			Map<String, Object> results = caller.execute(params);
			ret = (ArrayList<TrabajadorBean>) results.get(Constants.PAR_OUT_CURSOR);

			result = (TrabajadorBean) ret.get(0);
		} catch (Exception e) {
			LOG.error("Error al obtener trabajador: ", e);
			throw e;
		}
		return result;
	}
	
}
