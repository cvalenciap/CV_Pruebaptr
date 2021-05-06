/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
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
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean;
import pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class TurbidimetroDigitalDaoImpl.
 */
@Repository
public class TurbidimetroDigitalDaoImpl implements ITurbidimetroDigitalDao{

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
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(TurbidimetroDigitalBean turbidimetroDigitalBean, Paginacion paginacion)throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL).withProcedureName(ConstantsLaboratorio.SP_SEARCH_TURBIDI_DIGITAL)
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
										record.add(rs.getString(1)); // "n_ubicac Desc"
										record.add(rs.getString(2)); // "c_codequ"
										record.add(rs.getString(3)); // "c_noequi"
										record.add(rs.getString(4)); // "c_marequ"
										record.add(rs.getString(5)); // "c_modequ"
										record.add(rs.getInt(6)); // "n_mescal"
										record.add(rs.getInt(7)); // "n_codfo3"
										record.add(rs.getInt(8)); // "c_proxmc"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, turbidimetroDigitalBean.getStrCodEquipo());
		
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
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#obtenerVerificacionEquipo(java.lang.Integer)
	 */
	@Override
	public TurbidimetroDigitalBean obtenerVerificacionEquipo(Integer id) throws Exception {
		ArrayList ret = null;
		TurbidimetroDigitalBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL).withProcedureName(ConstantsLaboratorio.PRC_GET_TURBIDI_DIGITAL)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<TurbidimetroDigitalBean>() {
									
									@Override
									public TurbidimetroDigitalBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										TurbidimetroDigitalBean turbidimetroDigitalBean = new TurbidimetroDigitalBean();
										turbidimetroDigitalBean.setIntUbicacion(rs.getString(1)); // "ubicacion"
										turbidimetroDigitalBean.setStrCodEquipo(rs.getString(2)); // "c_codequ"
										turbidimetroDigitalBean.setStrCodEquipoHide(rs.getString(2)); // "c_codequ"
										turbidimetroDigitalBean.setStrNomEquipo(rs.getString(3)); // "c_noequi"
										turbidimetroDigitalBean.setStrMarca(rs.getString(4)); // "c_marequ"
										turbidimetroDigitalBean.setStrModelo(rs.getString(5)); // "c_modequ"
										turbidimetroDigitalBean.setIntMesCalibracion(rs.getInt(6)); // "n_mescal"
										turbidimetroDigitalBean.setIntCodigo(rs.getInt(7)); // "n_codfo3"
										turbidimetroDigitalBean.setIntProxMesCalibracion(rs.getInt(8)); // "c_proxmc"
										turbidimetroDigitalBean.setStrRangoNTU1(rs.getString(9)); // "c_ranntu1"
										turbidimetroDigitalBean.setStrMaxNTU1(rs.getString(10)); // "c_maxntu1"
										turbidimetroDigitalBean.setStrMinNTU1(rs.getString(11)); // "c_minntu1"
										turbidimetroDigitalBean.setStrRangoNTU2(rs.getString(12)); // "c_ranntu2"
										turbidimetroDigitalBean.setStrMaxNTU2(rs.getString(13)); // "c_maxntu2"
										turbidimetroDigitalBean.setStrMinNTU2(rs.getString(14)); // "c_minntu2"
										turbidimetroDigitalBean.setStrRangoNTU3(rs.getString(15)); // "c_ranntu3"
										turbidimetroDigitalBean.setStrMaxNTU3(rs.getString(16)); // "c_maxntu3"
										turbidimetroDigitalBean.setStrMinNTU3(rs.getString(17)); // "c_minntu3"
										turbidimetroDigitalBean.setStrRangoNTU4(rs.getString(18)); // "c_ranntu4"
										turbidimetroDigitalBean.setStrMaxNTU4(rs.getString(19)); // "c_maxntu4"
										turbidimetroDigitalBean.setStrMinNTU4(rs.getString(20)); // "c_minntu4"
										turbidimetroDigitalBean.setStrRangoNTU5(rs.getString(21)); // "c_ranntu5"
										turbidimetroDigitalBean.setStrMaxNTU5(rs.getString(22)); // "c_maxntu5"
										turbidimetroDigitalBean.setStrMinNTU5(rs.getString(23)); // "c_minntu5"

										return turbidimetroDigitalBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_EQUIPO, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (TurbidimetroDigitalBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#grabarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public int grabarVerificacionEquipo(TurbidimetroDigitalBean turbidimetroDigitalBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CUBICACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMARCA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMODELO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NMES_CALIBRACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NPROX_MES_CALIBRACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO5, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX5, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN5, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_TURBIDI_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, turbidimetroDigitalBean.getStrCodEquipo());		
		inputs.put(ConstantsLaboratorio.PAR_CUBICACION, turbidimetroDigitalBean.getIntUbicacion());	
		inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, turbidimetroDigitalBean.getStrNomEquipo());
		inputs.put(ConstantsLaboratorio.PAR_CMARCA, turbidimetroDigitalBean.getStrMarca());	
		inputs.put(ConstantsLaboratorio.PAR_CMODELO, turbidimetroDigitalBean.getStrModelo());	
		inputs.put(ConstantsLaboratorio.PAR_NMES_CALIBRACION, turbidimetroDigitalBean.getIntMesCalibracion());	
		inputs.put(ConstantsLaboratorio.PAR_NPROX_MES_CALIBRACION, turbidimetroDigitalBean.getIntProxMesCalibracion());	
					
		inputs.put(ConstantsLaboratorio.PAR_CRANGO1, turbidimetroDigitalBean.getStrRangoNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX1, turbidimetroDigitalBean.getStrMaxNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN1, turbidimetroDigitalBean.getStrMinNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO2, turbidimetroDigitalBean.getStrRangoNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX2, turbidimetroDigitalBean.getStrMaxNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN2, turbidimetroDigitalBean.getStrMinNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO3, turbidimetroDigitalBean.getStrRangoNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX3, turbidimetroDigitalBean.getStrMaxNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN3, turbidimetroDigitalBean.getStrMinNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO4, turbidimetroDigitalBean.getStrRangoNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX4, turbidimetroDigitalBean.getStrMaxNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN4, turbidimetroDigitalBean.getStrMinNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO5, turbidimetroDigitalBean.getStrRangoNTU5());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX5, turbidimetroDigitalBean.getStrMaxNTU5());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN5, turbidimetroDigitalBean.getStrMinNTU5());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, turbidimetroDigitalBean.getIntEstado());	
		

		inputs.put(Constants.PAR_CUSUARIO_CREACION, turbidimetroDigitalBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, turbidimetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, turbidimetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
									
		
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#actualizarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public void actualizarVerificacionEquipo(TurbidimetroDigitalBean turbidimetroDigitalBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;


		lstParamsInput = new ArrayList<SqlParameter>();	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CUBICACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMARCA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMODELO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NMES_CALIBRACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NPROX_MES_CALIBRACION, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO5, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MAX5, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRANGO_MIN5, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));

		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_TURBIDI_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, turbidimetroDigitalBean.getIntCodigo());	
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, turbidimetroDigitalBean.getStrCodEquipo());		
		inputs.put(ConstantsLaboratorio.PAR_CUBICACION, turbidimetroDigitalBean.getIntUbicacion());	
		inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, turbidimetroDigitalBean.getStrNomEquipo());
		inputs.put(ConstantsLaboratorio.PAR_CMARCA, turbidimetroDigitalBean.getStrMarca());	
		inputs.put(ConstantsLaboratorio.PAR_CMODELO, turbidimetroDigitalBean.getStrModelo());	
		inputs.put(ConstantsLaboratorio.PAR_NMES_CALIBRACION, turbidimetroDigitalBean.getIntMesCalibracion());	
		inputs.put(ConstantsLaboratorio.PAR_NPROX_MES_CALIBRACION, turbidimetroDigitalBean.getIntProxMesCalibracion());						
		inputs.put(ConstantsLaboratorio.PAR_CRANGO1, turbidimetroDigitalBean.getStrRangoNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX1, turbidimetroDigitalBean.getStrMaxNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN1, turbidimetroDigitalBean.getStrMinNTU1());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO2, turbidimetroDigitalBean.getStrRangoNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX2, turbidimetroDigitalBean.getStrMaxNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN2, turbidimetroDigitalBean.getStrMinNTU2());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO3, turbidimetroDigitalBean.getStrRangoNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX3, turbidimetroDigitalBean.getStrMaxNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN3, turbidimetroDigitalBean.getStrMinNTU3());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO4, turbidimetroDigitalBean.getStrRangoNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX4, turbidimetroDigitalBean.getStrMaxNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN4, turbidimetroDigitalBean.getStrMinNTU4());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO5, turbidimetroDigitalBean.getStrRangoNTU5());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MAX5, turbidimetroDigitalBean.getStrMaxNTU5());	
		inputs.put(ConstantsLaboratorio.PAR_CRANGO_MIN5, turbidimetroDigitalBean.getStrMinNTU5());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, turbidimetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, turbidimetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
					
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#inactivarVerificacionEquipo(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalBean)
	 */
	@Override
	public void inactivarVerificacionEquipo(TurbidimetroDigitalBean turbidimetroDigitalBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_TURBIDI_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, turbidimetroDigitalBean.getIntCodigo());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, turbidimetroDigitalBean.getIntEstado());
		
		this.execSp.executeSp(inputs);						
		
	}		
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#obtenerDatosEquiposVerificacion(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposVerificacion(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean, Paginacion paginacion)throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL).withProcedureName(ConstantsLaboratorio.SP_SEARCH_VERIFICA_TURBDIG)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO, Types.INTEGER),
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
										
										record.add(resultado); // "d_fecver"
										record.add(rs.getString(2)); // "c_valv01"
										record.add(rs.getString(3)); // "c_valv02"
										record.add(rs.getString(4)); // "c_valv03"
										record.add(rs.getString(5)); // "c_valv04"
										record.add(rs.getString(6)); // "c_valv05"
										record.add(rs.getString(7)); // "c_respon"
										record.add(rs.getString(8)); // "c_observ"
										record.add(rs.getInt(9)); // "n_codver"
										record.add(rs.getInt(10)); // "n_codfo3"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO, turbidimetroDigitalVerificacionBean.getIntCodigoTurbDigital());
		
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
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#grabarVerificacionEquipoForm(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean)
	 */
	@Override
	public int grabarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CFECHA_VERIFI, OracleTypes.DATE));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVALOR5, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESPONSABLE, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COBSERVACION, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_VERIFICA_TURBDIG), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, turbidimetroDigitalVerificacionBean.getIntCodigoTurbDigital());	
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date f1 = formatter.parse(turbidimetroDigitalVerificacionBean.getStrFechaVerificacion());
		java.sql.Date f1sql = new java.sql.Date(f1.getTime());
		inputs.put(ConstantsLaboratorio.PAR_CFECHA_VERIFI, f1sql);	
		inputs.put(ConstantsLaboratorio.PAR_CVALOR1, turbidimetroDigitalVerificacionBean.getStrValorVerificacion1());	
		inputs.put(ConstantsLaboratorio.PAR_CVALOR2, turbidimetroDigitalVerificacionBean.getStrValorVerificacion2());	
		inputs.put(ConstantsLaboratorio.PAR_CVALOR3, turbidimetroDigitalVerificacionBean.getStrValorVerificacion3());	
		inputs.put(ConstantsLaboratorio.PAR_CVALOR4, turbidimetroDigitalVerificacionBean.getStrValorVerificacion4());	
		inputs.put(ConstantsLaboratorio.PAR_CVALOR5, turbidimetroDigitalVerificacionBean.getStrValorVerificacion5());	
		inputs.put(ConstantsLaboratorio.PAR_CRESPONSABLE, turbidimetroDigitalVerificacionBean.getStrResponsable());	
		inputs.put(ConstantsLaboratorio.PAR_COBSERVACION, turbidimetroDigitalVerificacionBean.getStrObservacion());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, turbidimetroDigitalVerificacionBean.getIntEstado());	

		inputs.put(Constants.PAR_CUSUARIO_CREACION, turbidimetroDigitalVerificacionBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, turbidimetroDigitalVerificacionBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, turbidimetroDigitalVerificacionBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
									
		
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.ITurbidimetroDigitalDao#inactivarVerificacionEquipoForm(pe.com.sedapal.scr.core.beans.TurbidimetroDigitalVerificacionBean)
	 */
	@Override
	public void inactivarVerificacionEquipoForm(TurbidimetroDigitalVerificacionBean turbidimetroDigitalVerificacionBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_VERIFICA, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_TURBIDI_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_VERIFICA_TURBDIG), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_VERIFICA, turbidimetroDigitalVerificacionBean.getIntIdVerificacion());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, turbidimetroDigitalVerificacionBean.getIntEstado());
		
		this.execSp.executeSp(inputs);						
		
	}

}
