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
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class ConductimetroDigitalDaoImpl.
 */
@Repository
public class ConductimetroDigitalDaoImpl implements IConductimetroDigitalDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(ConductimetroDigitalBean conductimetroDigitalBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_CONDUCTIMETRO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, Types.VARCHAR),
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
										record.add(rs.getInt(5)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, conductimetroDigitalBean.getStrCodEquipo().toUpperCase());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#obtenerConductimetroDigital(java.lang.Integer)
	 */
	@Override
	public ConductimetroDigitalBean obtenerConductimetroDigital(Integer id) throws Exception {
		ArrayList ret = null;
		ConductimetroDigitalBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO).withProcedureName(ConstantsLaboratorio.PRC_GET_CONDUCTIMETRO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ConductimetroDigitalBean>() {
									
									@Override
									public ConductimetroDigitalBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										ConductimetroDigitalBean conductimetroDigitalBean = new ConductimetroDigitalBean();
										conductimetroDigitalBean.setIntArea(rs.getInt(1)); // ""
										conductimetroDigitalBean.setIntAreaHide(rs.getInt(1)); // ""
										conductimetroDigitalBean.setStrEquipoMedicion(rs.getString(2)); // ""
										conductimetroDigitalBean.setStrCodEquipo(rs.getString(3)); // ""
										conductimetroDigitalBean.setStrCodEquipoHide(rs.getString(3)); // ""										
										conductimetroDigitalBean.setStrNroSerie(rs.getString(4)); // ""
										conductimetroDigitalBean.setIntEstado(rs.getInt(5)); // ""
										conductimetroDigitalBean.setStrEspecialista(rs.getString(6)); // ""
										conductimetroDigitalBean.setStrEspecialistaHide(rs.getString(6));
										conductimetroDigitalBean.setStrKmin(rs.getString(7));
										conductimetroDigitalBean.setStrKmax(rs.getString(8));
										conductimetroDigitalBean.setStrValor1(rs.getString(9)!=null?rs.getString(9).trim():"");
										conductimetroDigitalBean.setStrValor2(rs.getString(10)!=null?rs.getString(9).trim():"");									
										conductimetroDigitalBean.setStrCalNombre(rs.getString(11));
										conductimetroDigitalBean.setStrCalLote(rs.getString(12));
										conductimetroDigitalBean.setStrCalCodigo(rs.getString(13));
										conductimetroDigitalBean.setStrCalCaducidad(rs.getString(14));
										conductimetroDigitalBean.setStrCalMarca(rs.getString(15));
										conductimetroDigitalBean.setStrCalEspecificacion(rs.getString(16));										
										conductimetroDigitalBean.setStrVerNombre(rs.getString(17));
										conductimetroDigitalBean.setStrVerLote(rs.getString(18));
										conductimetroDigitalBean.setStrVerCodigo(rs.getString(19));
										conductimetroDigitalBean.setStrVerCaducidad(rs.getString(20));
										conductimetroDigitalBean.setStrVerMarca(rs.getString(21));
										conductimetroDigitalBean.setStrVerEspecificacion(rs.getString(22));
										conductimetroDigitalBean.setIntCodigo(rs.getInt(23));

										return conductimetroDigitalBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_EQUIPO, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (ConductimetroDigitalBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#grabarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public int grabarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CEQUIPO_MEDIC, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NAREA, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNRO_SERIE, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CESPECIALISTA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_KMAX, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_KMIN, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_VALOR1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_VALOR2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_CONDUCTIMETRO), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, conductimetroDigitalBean.getStrCodEquipo());		
		inputs.put(ConstantsLaboratorio.PAR_CEQUIPO_MEDIC, conductimetroDigitalBean.getStrEquipoMedicion());	
		inputs.put(ConstantsLaboratorio.PAR_NAREA, conductimetroDigitalBean.getIntArea());		
		inputs.put(ConstantsLaboratorio.PAR_CNRO_SERIE, conductimetroDigitalBean.getStrNroSerie());	
		inputs.put(ConstantsLaboratorio.PAR_CESPECIALISTA, conductimetroDigitalBean.getStrEspecialista());	
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE, conductimetroDigitalBean.getStrCalNombre());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE, conductimetroDigitalBean.getStrCalLote());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO, conductimetroDigitalBean.getStrCalCodigo());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD, conductimetroDigitalBean.getStrCalCaducidad());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA, conductimetroDigitalBean.getStrCalMarca());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION, conductimetroDigitalBean.getStrCalEspecificacion());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_KMAX, conductimetroDigitalBean.getStrKmax());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_KMIN, conductimetroDigitalBean.getStrKmin());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_VALOR1, conductimetroDigitalBean.getStrValor1());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_VALOR2, conductimetroDigitalBean.getStrValor2());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE, conductimetroDigitalBean.getStrVerNombre());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE, conductimetroDigitalBean.getStrVerLote());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO, conductimetroDigitalBean.getStrVerCodigo());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD, conductimetroDigitalBean.getStrVerCaducidad());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA, conductimetroDigitalBean.getStrVerMarca());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION, conductimetroDigitalBean.getStrVerEspecificacion());		
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, conductimetroDigitalBean.getIntEstado());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, conductimetroDigitalBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, conductimetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, conductimetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#actualizarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public void actualizarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CEQUIPO_MEDIC, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NAREA, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNRO_SERIE, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CESPECIALISTA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_KMAX, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_KMIN, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_VALOR1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_VALOR2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION, OracleTypes.VARCHAR));		
		
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_CONDUCTIMETRO), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, conductimetroDigitalBean.getIntCodigo());
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, conductimetroDigitalBean.getStrCodEquipo());		
		inputs.put(ConstantsLaboratorio.PAR_CEQUIPO_MEDIC, conductimetroDigitalBean.getStrEquipoMedicion());	
		inputs.put(ConstantsLaboratorio.PAR_NAREA, conductimetroDigitalBean.getIntArea());		
		inputs.put(ConstantsLaboratorio.PAR_CNRO_SERIE, conductimetroDigitalBean.getStrNroSerie());	
		inputs.put(ConstantsLaboratorio.PAR_CESPECIALISTA, conductimetroDigitalBean.getStrEspecialista());	
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE, conductimetroDigitalBean.getStrCalNombre());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE, conductimetroDigitalBean.getStrCalLote());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO, conductimetroDigitalBean.getStrCalCodigo());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD, conductimetroDigitalBean.getStrCalCaducidad());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA, conductimetroDigitalBean.getStrCalMarca());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION, conductimetroDigitalBean.getStrCalEspecificacion());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_KMAX, conductimetroDigitalBean.getStrKmax());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_KMIN, conductimetroDigitalBean.getStrKmin());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_VALOR1, conductimetroDigitalBean.getStrValor1());
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_VALOR2, conductimetroDigitalBean.getStrValor2());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE, conductimetroDigitalBean.getStrVerNombre());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE, conductimetroDigitalBean.getStrVerLote());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO, conductimetroDigitalBean.getStrVerCodigo());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD, conductimetroDigitalBean.getStrVerCaducidad());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA, conductimetroDigitalBean.getStrVerMarca());
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION, conductimetroDigitalBean.getStrVerEspecificacion());				

		inputs.put(Constants.PAR_CUSUARIO_MODI, conductimetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, conductimetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#inactivarConductimetroDigital(pe.com.sedapal.scr.core.beans.ConductimetroDigitalBean)
	 */
	@Override
	public void inactivarConductimetroDigital(ConductimetroDigitalBean conductimetroDigitalBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_CONDUCTIMETRO), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, conductimetroDigitalBean.getIntCodigo());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, conductimetroDigitalBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#obtenerDatosEquiposDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO).withProcedureName(ConstantsLaboratorio.SP_DETALLE_CONDUCTIMETRO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, Types.INTEGER),
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
										
										String resultado = "";
										try {
											SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
											if(rs.getDate(1)!=null){
												resultado = df.format(rs.getDate(1));
											}
										} catch (Exception e) {
											resultado = "";


										}
										
										record.add(resultado); // ""
										record.add(rs.getString(2)); // ""
										record.add(rs.getString(3)); // ""
										record.add(rs.getString(4)); // ""
										record.add(rs.getString(5)); // ""
										record.add(rs.getString(6)); // ""
										record.add(rs.getString(7)); // ""
										record.add(rs.getInt(8)); // ""
										

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_EQUIPO, conductimetroDigitalDetalleBean.getIntIdConductimetro());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#grabarConductimetroDigitalDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean)
	 */
	@Override
	public int grabarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESULT1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CKCELDA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESULT2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESPONSABLE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CFIRMA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COCURRENCIA, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_DETALLE_CONDUCTIMETRO_EDIT), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, conductimetroDigitalDetalleBean.getIntIdConductimetro());		
		inputs.put(ConstantsLaboratorio.PAR_DFECHA, conductimetroDigitalDetalleBean.getStrFecha());
		inputs.put(ConstantsLaboratorio.PAR_CRESULT1, conductimetroDigitalDetalleBean.getStrResult1());
		inputs.put(ConstantsLaboratorio.PAR_CKCELDA, conductimetroDigitalDetalleBean.getStrKCelda());
		inputs.put(ConstantsLaboratorio.PAR_CRESULT2, conductimetroDigitalDetalleBean.getStrResult2());
		inputs.put(ConstantsLaboratorio.PAR_CRESPONSABLE, conductimetroDigitalDetalleBean.getStrResponsable());
		inputs.put(ConstantsLaboratorio.PAR_CFIRMA, conductimetroDigitalDetalleBean.getStrFirma());
		inputs.put(ConstantsLaboratorio.PAR_COCURRENCIA, conductimetroDigitalDetalleBean.getStrOcurrencia());			
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, conductimetroDigitalDetalleBean.getIntEstado());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, conductimetroDigitalDetalleBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, conductimetroDigitalDetalleBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, conductimetroDigitalDetalleBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IConductimetroDigitalDao#inactivarConductimetroDigitalDetalle(pe.com.sedapal.scr.core.beans.ConductimetroDigitalDetalleBean)
	 */
	@Override
	public void inactivarConductimetroDigitalDetalle(ConductimetroDigitalDetalleBean conductimetroDigitalDetalleBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_CONDUCTIMETRO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INAC_DETALLE_CONDUCTI), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, conductimetroDigitalDetalleBean.getIntIdConductimetro());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, conductimetroDigitalDetalleBean.getIntEstado());
		
		this.execSp.executeSp(inputs);		
	}

}
