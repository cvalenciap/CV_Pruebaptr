/*
 * @author Tgestiona
 * @version 1.0 13/10/2017
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
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.MuestraFirstBean;
import pe.com.sedapal.scr.core.beans.MuestraSecondBean;
import pe.com.sedapal.scr.core.beans.ResultMuestraBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisBacteriologicoDaoImpl.
 */
@Repository
public class AnalisisBacteriologicoDaoImpl implements IAnalisisBacteriologicoDao{

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
	 * @param template
	 *            the new template
	 */
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(AnalisisBacteriologicoBean analisisBacteriologicoBean, Paginacion paginacion)	throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_ANALBACT)
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
				
		params.addValue(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, analisisBacteriologicoBean.getStrFechaMuestreo());
		params.addValue(ConstantsLaboratorio.PAR_NID_FORM, analisisBacteriologicoBean.getIntIdForm());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerAnalisisBacteriologico(java.lang.Integer)
	 */
	@Override
	public AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id) throws Exception {
		return obtenerAnalisisBacteriologico(id,"");
	}
	

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerAnalisisBacteriologico(java.lang.Integer, java.lang.String)
	 */
	@Override
	public AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id,String tipo) throws Exception {
		ArrayList ret = null;
		AnalisisBacteriologicoBean result = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_GET_ANALBACT)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalisisBacteriologicoBean>() {
									
									@Override
									public AnalisisBacteriologicoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										AnalisisBacteriologicoBean analisisBacteriologicoBean = new AnalisisBacteriologicoBean();
										analisisBacteriologicoBean.setIntId(rs.getInt(1));
										analisisBacteriologicoBean.setIntIdHide(rs.getInt(1));
										
										analisisBacteriologicoBean.setStrFechaMuestreo(Utils.formatFechaStringddMMYYY(rs.getDate(2)));
										analisisBacteriologicoBean.setStrFechaRecepcionMuestra(Utils.formatFechaStringddMMYYY(rs.getDate(3)));
										analisisBacteriologicoBean.setStrFechaInicioEnsayo(Utils.formatFechaStringddMMYYY(rs.getDate(4)));
										
//										analisisBacteriologicoBean.setStrFechaMuestreo(rs.getString(2));
//										analisisBacteriologicoBean.setStrFechaRecepcionMuestra(rs.getString(3));
//									    analisisBacteriologicoBean.setStrFechaInicioEnsayo(rs.getString(4));	
										
										analisisBacteriologicoBean.setStrTurno(rs.getString(5));
										analisisBacteriologicoBean.setStrMuestreador(rs.getString(6));
										analisisBacteriologicoBean.setStrMuestreadorHide(rs.getString(6));
										analisisBacteriologicoBean.setStrAnalista(rs.getString(7));
										analisisBacteriologicoBean.setStrAnalistaHide(rs.getString(7));
										analisisBacteriologicoBean.setIntEstado(rs.getInt(8));
										analisisBacteriologicoBean.setStrHoraMuestreo(rs.getString(9));
										analisisBacteriologicoBean.setStrHoraRecepcionMuestra(rs.getString(10));
										analisisBacteriologicoBean.setStrHoraInicioEnsayo(rs.getString(11));
										analisisBacteriologicoBean.setBolCumplimientoLBD( (rs.getString(12)!=null && rs.getString(12).trim().equals("1")?true:false ) );
										analisisBacteriologicoBean.setIntCumplimientoLBDHide(Integer.parseInt(rs.getString(12)!=null?rs.getString(12):"0"));
										analisisBacteriologicoBean.setBolMantConexiones( (rs.getString(13)!=null && rs.getString(13).trim().equals("1")?true:false ) );
										analisisBacteriologicoBean.setIntMantConexionesHide(Integer.parseInt(rs.getString(13)!=null?rs.getString(13):"0"));
										analisisBacteriologicoBean.setStrInterperlacionResult(rs.getString(14));
										analisisBacteriologicoBean.setStrObservacion(rs.getString(15));
										analisisBacteriologicoBean.setStrCepas1(rs.getString(16));
										analisisBacteriologicoBean.setStrCepas2(rs.getString(17));
										analisisBacteriologicoBean.setStrObsInterpelacion(rs.getString(18));
										return analisisBacteriologicoBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (AnalisisBacteriologicoBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#grabarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public int grabarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, OracleTypes.DATE));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_RECEPCION, OracleTypes.DATE));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_INI_ENSAYO, OracleTypes.DATE));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_MUESTREO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_RECEPCION, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_INI_ENSAYO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTURNO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMUESTREADOR, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANALISTA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCUMPLIMIENTO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMANTENIMIENTO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_FORM, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COBSERVACION, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCEPAS1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCEPAS2, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_ANALBACT), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaMuestreo()));		
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_RECEPCION, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaRecepcionMuestra()));	
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_INI_ENSAYO, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaInicioEnsayo()));		
		inputs.put(ConstantsLaboratorio.PAR_CHORA_MUESTREO, analisisBacteriologicoBean.getStrHoraMuestreo());	
		inputs.put(ConstantsLaboratorio.PAR_CHORA_RECEPCION, analisisBacteriologicoBean.getStrHoraRecepcionMuestra());	
		inputs.put(ConstantsLaboratorio.PAR_CHORA_INI_ENSAYO, analisisBacteriologicoBean.getStrHoraInicioEnsayo());
		inputs.put(ConstantsLaboratorio.PAR_CTURNO, analisisBacteriologicoBean.getStrTurno());
		inputs.put(ConstantsLaboratorio.PAR_CMUESTREADOR, analisisBacteriologicoBean.getStrMuestreador());
		inputs.put(ConstantsLaboratorio.PAR_CANALISTA, analisisBacteriologicoBean.getStrAnalista());
		inputs.put(ConstantsLaboratorio.PAR_CCUMPLIMIENTO, analisisBacteriologicoBean.isBolCumplimientoLBD()?1:0);
		inputs.put(ConstantsLaboratorio.PAR_CMANTENIMIENTO, analisisBacteriologicoBean.isBolMantConexiones()?1:0);
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, analisisBacteriologicoBean.getIntEstado());
		inputs.put(ConstantsLaboratorio.PAR_NID_FORM, analisisBacteriologicoBean.getIntIdForm());
		inputs.put(ConstantsLaboratorio.PAR_COBSERVACION, analisisBacteriologicoBean.getStrObservacion());
		inputs.put(ConstantsLaboratorio.PAR_CCEPAS1, analisisBacteriologicoBean.getStrCepas1());
		inputs.put(ConstantsLaboratorio.PAR_CCEPAS2, analisisBacteriologicoBean.getStrCepas2());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, analisisBacteriologicoBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, analisisBacteriologicoBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, analisisBacteriologicoBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#actualizarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void actualizarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean)	throws Exception {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, OracleTypes.DATE));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_RECEPCION, OracleTypes.DATE));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA_INI_ENSAYO, OracleTypes.DATE));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_MUESTREO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_RECEPCION, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CHORA_INI_ENSAYO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTURNO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMUESTREADOR, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANALISTA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCUMPLIMIENTO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMANTENIMIENTO, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_COBSERVACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCEPAS1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCEPAS2, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_ANALBACT), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, analisisBacteriologicoBean.getIntId());
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_MUESTREO, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaMuestreo()));		
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_RECEPCION, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaRecepcionMuestra()));	
		inputs.put(ConstantsLaboratorio.PAR_DFECHA_INI_ENSAYO, Utils.getDateOracle(analisisBacteriologicoBean.getStrFechaInicioEnsayo()));		
		inputs.put(ConstantsLaboratorio.PAR_CHORA_MUESTREO, analisisBacteriologicoBean.getStrHoraMuestreo());	
		inputs.put(ConstantsLaboratorio.PAR_CHORA_RECEPCION, analisisBacteriologicoBean.getStrHoraRecepcionMuestra());	
		inputs.put(ConstantsLaboratorio.PAR_CHORA_INI_ENSAYO, analisisBacteriologicoBean.getStrHoraInicioEnsayo());
		inputs.put(ConstantsLaboratorio.PAR_CTURNO, analisisBacteriologicoBean.getStrTurno());
		inputs.put(ConstantsLaboratorio.PAR_CMUESTREADOR, analisisBacteriologicoBean.getStrMuestreador());
		inputs.put(ConstantsLaboratorio.PAR_CANALISTA, analisisBacteriologicoBean.getStrAnalista());
		inputs.put(ConstantsLaboratorio.PAR_CCUMPLIMIENTO, analisisBacteriologicoBean.isBolCumplimientoLBD()?1:0);
		inputs.put(ConstantsLaboratorio.PAR_CMANTENIMIENTO, analisisBacteriologicoBean.isBolMantConexiones()?1:0);
		inputs.put(ConstantsLaboratorio.PAR_COBSERVACION, analisisBacteriologicoBean.getStrObservacion());
		inputs.put(ConstantsLaboratorio.PAR_CCEPAS1, analisisBacteriologicoBean.getStrCepas1());
		inputs.put(ConstantsLaboratorio.PAR_CCEPAS2, analisisBacteriologicoBean.getStrCepas2());
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, analisisBacteriologicoBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, analisisBacteriologicoBean.getPrograma());	
		
		this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#inactivarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void inactivarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_ANALBACT), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, analisisBacteriologicoBean.getIntId());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, analisisBacteriologicoBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#grabarAnalisisBacteriologicoResultado(pe.com.sedapal.scr.core.beans.ResultMuestraBean)
	 */
	@Override
	public int grabarAnalisisBacteriologicoResultado(ResultMuestraBean resultMuestraBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLIFOR, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLTER, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CBACHET, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NTIPO, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTURNTU, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_RESULTMUES), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, resultMuestraBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CCOLIFOR, resultMuestraBean.getStrColifor());	
		inputs.put(ConstantsLaboratorio.PAR_CCOLTER, resultMuestraBean.getStrColter());
		inputs.put(ConstantsLaboratorio.PAR_CBACHET, resultMuestraBean.getStrBachet());
		inputs.put(ConstantsLaboratorio.PAR_NTIPO, resultMuestraBean.getIntTipo());
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, resultMuestraBean.getStrDesMuestra());
		inputs.put(ConstantsLaboratorio.PAR_CTURNTU, resultMuestraBean.getStrTurntu());
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, resultMuestraBean.getIntEstado());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, resultMuestraBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, resultMuestraBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, resultMuestraBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#grabarAnalisisBacteriologicoFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int grabarAnalisisBacteriologicoFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDIL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLT24, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLT48, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLV24, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLV48, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTUEC24, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARML, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARDL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ORDEN, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTRAFIRST), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, muestraFirstBean.getStrDescMuestra());	
		inputs.put(ConstantsLaboratorio.PAR_CDIL, muestraFirstBean.getStrDil());
		inputs.put(ConstantsLaboratorio.PAR_CTCLT24, muestraFirstBean.getStrTclt24());
		inputs.put(ConstantsLaboratorio.PAR_CTCLT48, muestraFirstBean.getStrTclv48());
		inputs.put(ConstantsLaboratorio.PAR_CTCLV24, muestraFirstBean.getStrTclv24());
		inputs.put(ConstantsLaboratorio.PAR_CTCLV48, muestraFirstBean.getStrTclv48());
		inputs.put(ConstantsLaboratorio.PAR_CTUEC24, muestraFirstBean.getStrTuec24());
		inputs.put(ConstantsLaboratorio.PAR_CAGARML, muestraFirstBean.getStrAgarml());
		inputs.put(ConstantsLaboratorio.PAR_CAGARDL, muestraFirstBean.getStrAgardl());
		inputs.put(ConstantsLaboratorio.PAR_ORDEN, muestraFirstBean.getIntNroOrden());
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, muestraFirstBean.getIntEstado());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, muestraFirstBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#grabarAnalisisBacteriologicoSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int grabarAnalisisBacteriologicoSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TURBNTU, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCL2RES, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGAREL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGELE, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CGAMFC, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGMFC, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARML, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARDL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOAGAR, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_MUESTRASECOND), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, muestraSecondBean.getStrDescMuestra());	
		inputs.put(ConstantsLaboratorio.PAR_TURBNTU, muestraSecondBean.getStrTurbNtu());		
		inputs.put(ConstantsLaboratorio.PAR_CCL2RES, muestraSecondBean.getStrCl2res());
		inputs.put(ConstantsLaboratorio.PAR_CAGAREL, muestraSecondBean.getStrAgarel());
		inputs.put(ConstantsLaboratorio.PAR_CAGELE, muestraSecondBean.getStrAgele());
		inputs.put(ConstantsLaboratorio.PAR_CGAMFC, muestraSecondBean.getStrGamfc());
		inputs.put(ConstantsLaboratorio.PAR_CAGMFC, muestraSecondBean.getStrAgmfc());
		inputs.put(ConstantsLaboratorio.PAR_CAGARML, muestraSecondBean.getStrAgarml());
		inputs.put(ConstantsLaboratorio.PAR_CAGARDL, muestraSecondBean.getStrAgardl());
		inputs.put(ConstantsLaboratorio.PAR_CCOAGAR, muestraSecondBean.getStrCoagar());
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, muestraSecondBean.getIntEstado());
		
		inputs.put(Constants.PAR_CUSUARIO_CREACION, muestraSecondBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerDatosMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosMuestraFirst(MuestraFirstBean muestraFirstBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTFIRST)
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
										record.add(rs.getString(2).trim()); // ""
										record.add(rs.getString(3).trim()); // ""
										record.add(rs.getString(4)); // ""
										record.add(rs.getString(5)); // ""
										record.add(rs.getString(6)); // ""
										record.add(rs.getString(7)); // ""
										record.add(rs.getString(8)); // ""
										record.add(rs.getString(9)); // ""
										record.add(rs.getString(10)); // ""
										record.add(rs.getString(11)); // ""
										record.add(rs.getInt(12)); // ""
										record.add(rs.getInt(13)); // ""
										record.add(rs.getInt(14)); // ""
										record.add(rs.getInt(15)); // ""
										record.add(rs.getInt(16)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public MuestraFirstBean obtenerMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		ArrayList ret = null;
		MuestraFirstBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_GET_MUESTFIRST)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),		
						new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, Types.INTEGER),	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MuestraFirstBean>() {
									
									@Override
									public MuestraFirstBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MuestraFirstBean muestraFirstBean = new MuestraFirstBean();
										muestraFirstBean.setStrDescMuestra(rs.getString(1));
										muestraFirstBean.setStrDil(rs.getString(2));
										muestraFirstBean.setStrTclt24(rs.getString(3));
										muestraFirstBean.setStrTclt48(rs.getString(4));
										muestraFirstBean.setStrTclv24(rs.getString(5));
										muestraFirstBean.setStrTclv48(rs.getString(6));
										muestraFirstBean.setStrTuec24(rs.getString(7));
										muestraFirstBean.setStrAgarml(rs.getString(8));
										muestraFirstBean.setStrAgardl(rs.getString(9));
										muestraFirstBean.setStrColaga(rs.getString(10));
										muestraFirstBean.setIntIdMuestraFirst(rs.getInt(11));
										muestraFirstBean.setIntIdCabecera(rs.getInt(12));
										muestraFirstBean.setIntNroOrden(rs.getInt(13));
										muestraFirstBean.setStrTurbntu(rs.getString(14));

										return muestraFirstBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());
		params.addValue(ConstantsLaboratorio.PAR_NID_DETALLE, muestraFirstBean.getIntIdMuestraFirst());
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (MuestraFirstBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#actualizarMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public void actualizarMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDIL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLT24, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLT48, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLV24, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTCLV48, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CTUEC24, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARML, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARDL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOLAGA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ORDEN, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TURBNTU, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_MUESTFIRST), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraFirstBean.getIntIdMuestraFirst());	
		inputs.put(ConstantsLaboratorio.PAR_CDIL, muestraFirstBean.getStrDil());
		inputs.put(ConstantsLaboratorio.PAR_CTCLT24, muestraFirstBean.getStrTclt24());
		inputs.put(ConstantsLaboratorio.PAR_CTCLT48, muestraFirstBean.getStrTclt48());
		inputs.put(ConstantsLaboratorio.PAR_CTCLV24, muestraFirstBean.getStrTclv24());
		inputs.put(ConstantsLaboratorio.PAR_CTCLV48, muestraFirstBean.getStrTclv48());
		inputs.put(ConstantsLaboratorio.PAR_CTUEC24, muestraFirstBean.getStrTuec24());
		inputs.put(ConstantsLaboratorio.PAR_CAGARML, muestraFirstBean.getStrAgarml());
		inputs.put(ConstantsLaboratorio.PAR_CAGARDL, muestraFirstBean.getStrAgardl());
		inputs.put(ConstantsLaboratorio.PAR_CCOLAGA, muestraFirstBean.getStrColaga());
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, muestraFirstBean.getStrDescMuestra());
		inputs.put(ConstantsLaboratorio.PAR_ORDEN, muestraFirstBean.getIntNroOrden());
		inputs.put(ConstantsLaboratorio.PAR_TURBNTU, muestraFirstBean.getStrTurbntu());
		
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		//intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		//return intRetorno;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerDatosMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosMuestraSecond(MuestraSecondBean muestraSecondBean, Paginacion paginacion)	throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_MUESTSECOND)
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
										record.add(rs.getString(2));
										record.add(rs.getString(3).trim()); // ""
										record.add(rs.getString(4).trim()); // ""
										record.add(rs.getString(5)); // ""
										record.add(rs.getString(6)); // ""
										record.add(rs.getString(7)); // ""
										record.add(rs.getString(8)); // ""
										record.add(rs.getString(9)); // ""
										record.add(rs.getString(10)); // ""
										record.add(rs.getInt(11)); // ""
										record.add(rs.getInt(12)); // ""
										record.add(rs.getInt(13)); // ""

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public MuestraSecondBean obtenerMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		ArrayList ret = null;
		MuestraSecondBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_GET_MUESTSECOND)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),		
						new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, Types.INTEGER),	
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<MuestraSecondBean>() {
									
									@Override
									public MuestraSecondBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										MuestraSecondBean muestraSecondBean = new MuestraSecondBean();
										muestraSecondBean.setStrDescMuestra(rs.getString(1));
										muestraSecondBean.setStrTurbNtu(rs.getString(2));
										muestraSecondBean.setStrCl2res(rs.getString(3));
										muestraSecondBean.setStrAgarel(rs.getString(4));
										muestraSecondBean.setStrAgele(rs.getString(5));
										muestraSecondBean.setStrGamfc(rs.getString(6));
										muestraSecondBean.setStrAgmfc(rs.getString(7));
										muestraSecondBean.setStrAgarml(rs.getString(8));
										muestraSecondBean.setStrAgardl(rs.getString(9));
										muestraSecondBean.setStrCoagar(rs.getString(10));
										muestraSecondBean.setIntIdMuestraSecond(rs.getInt(11));
										muestraSecondBean.setIntIdCabecera(rs.getInt(12));
										return muestraSecondBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());
		params.addValue(ConstantsLaboratorio.PAR_NID_DETALLE, muestraSecondBean.getIntIdMuestraSecond());
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (MuestraSecondBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#actualizarMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public void actualizarMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TURBNTU, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCL2RES, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGAREL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGELE, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CGAMFC, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGMFC, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARML, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CAGARDL, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCOAGAR, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_MUESTSECOND), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraSecondBean.getIntIdMuestraSecond());	
		inputs.put(ConstantsLaboratorio.PAR_TURBNTU, muestraSecondBean.getStrTurbNtu());	
		inputs.put(ConstantsLaboratorio.PAR_CCL2RES, muestraSecondBean.getStrCl2res());
		inputs.put(ConstantsLaboratorio.PAR_CAGAREL, muestraSecondBean.getStrAgarel());
		inputs.put(ConstantsLaboratorio.PAR_CAGELE, muestraSecondBean.getStrAgele());
		inputs.put(ConstantsLaboratorio.PAR_CGAMFC, muestraSecondBean.getStrGamfc());
		inputs.put(ConstantsLaboratorio.PAR_CAGMFC, muestraSecondBean.getStrAgmfc());
		inputs.put(ConstantsLaboratorio.PAR_CAGARML, muestraSecondBean.getStrAgarml());
		inputs.put(ConstantsLaboratorio.PAR_CAGARDL, muestraSecondBean.getStrAgardl());
		inputs.put(ConstantsLaboratorio.PAR_CCOAGAR, muestraSecondBean.getStrCoagar());
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#obtenerDatosResultMuestra(pe.com.sedapal.scr.core.beans.ResultMuestraBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosResultMuestra(ResultMuestraBean resultMuestraBean, Paginacion paginacion)	throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO).withProcedureName(ConstantsLaboratorio.SP_SEARCH_RESULTMUES)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, Types.INTEGER),
						new SqlParameter(ConstantsLaboratorio.PAR_NTIPO, Types.INTEGER),
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
										record.add((rs.getString(1)==null||"".equals(rs.getString(1).trim()))?"":rs.getString(1).replaceAll(",", ".")); // ""
										record.add((rs.getString(2)==null||"".equals(rs.getString(2).trim()))?"":rs.getString(2).replaceAll(",", ".")); // ""
										record.add((rs.getString(3)==null||"".equals(rs.getString(3).trim()))?"":rs.getString(3).replaceAll(",", ".")); // ""
										record.add((rs.getString(4)==null||"".equals(rs.getString(4).trim()))?"":rs.getString(4).replaceAll(",", ".")); // ""
										record.add(rs.getString(5)); // ""		

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
				
		params.addValue(ConstantsLaboratorio.PAR_NID_CABECERA, resultMuestraBean.getIntIdCabecera());
		params.addValue(ConstantsLaboratorio.PAR_NTIPO, resultMuestraBean.getIntTipo());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#calculaResultadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int calculaResultadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_CALCULO_MUESTFIRST), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();	
		
		LOG.info("getIntIdCabecera : " + muestraFirstBean.getIntIdCabecera());
		LOG.info("getStrDescMuestra : " + muestraFirstBean.getStrDescMuestra());
		
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, muestraFirstBean.getStrDescMuestra());
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		int intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#calculaResultadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int calculaResultadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CDESCMUESTRA, OracleTypes.VARCHAR));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_CALCULO_MUESTSECOND), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_CDESCMUESTRA, muestraSecondBean.getStrDescMuestra());
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		int intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#addDuplicadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int addDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE_DUP, OracleTypes.NUMBER));	

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_DUPLICADO_MUESTFIRST), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraFirstBean.getIntIdMuestraFirst());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE_DUP, muestraFirstBean.getIntIdDuplicadoMuestraFirst());	
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#deleteDuplicadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int deleteDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_DEL_DUPLIC_MUESTFIRST), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraFirstBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraFirstBean.getIntIdMuestraFirst());	
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraFirstBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraFirstBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#addDuplicadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int addDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE_DUP, OracleTypes.NUMBER));	

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_DUPLICADO_MUESTSECOND), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraSecondBean.getIntIdMuestraSecond());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE_DUP, muestraSecondBean.getIntIdDuplicadoMuestraSecond());	
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao#deleteDuplicadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int deleteDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_CABECERA, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));	

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_ANALISIS_BACTERIOLOGICO,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_DEL_DUPLIC_MUESTSECOND), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();				
		inputs.put(ConstantsLaboratorio.PAR_NID_CABECERA, muestraSecondBean.getIntIdCabecera());	
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, muestraSecondBean.getIntIdMuestraSecond());	
		
		inputs.put(Constants.PAR_CUSUARIO_MODI, muestraSecondBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, muestraSecondBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
		return 0;
	}

}
