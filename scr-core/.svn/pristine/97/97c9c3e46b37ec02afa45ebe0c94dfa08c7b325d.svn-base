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
import pe.com.sedapal.scr.core.beans.FormularioDetalle222;
import pe.com.sedapal.scr.core.beans.FormularioHeader222;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IFormulario222Dao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class Formulario222DaoImpl.
 */
@Repository
public class Formulario222DaoImpl implements IFormulario222Dao {
	
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#getListadoFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioHeader(FormularioHeader222 formularioHeader222, Paginacion paginacion) {
		
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM222).withProcedureName(ConstantsLaboratorio.SP_LISTADO_HEADER222)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.P_FECHA_MUESTREO, Types.VARCHAR),
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
										record.add(rs.getString(10)); // 
										record.add(rs.getString(2)); // 
										record.add(rs.getString(3)); // 
										record.add(rs.getString(4)); // 
										record.add(rs.getString(5)); // 
										record.add(rs.getString(6)); // 
										record.add(rs.getString(7)); // 
//										record.add(rs.getDouble(8)); // 1
//										record.add(rs.getDouble(9)); //
//										record.add(rs.getDouble(10)); //
//										record.add(rs.getDouble(11)); //
//										record.add(rs.getDouble(12)); // 
//										record.add(rs.getDouble(13)); //
//										record.add(rs.getDouble(14)); //
//										record.add(rs.getDouble(15)); //
//										record.add(rs.getDouble(16)); //
//										record.add(rs.getDouble(17)); //
//										record.add(rs.getDouble(18)); //
//										record.add(rs.getDouble(19)); //
//										record.add(rs.getDouble(20)); //
//										record.add(rs.getDouble(21)); //
//										record.add(rs.getDouble(22)); // 15
										record.add(rs.getString(8)); // 
										record.add(rs.getInt(9)); // 
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_FECHA_MUESTREO, formularioHeader222.getStrfechaMuestreo());
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#getFormularioHeader(java.lang.Integer)
	 */
	@Override
	public FormularioHeader222 getFormularioHeader(Integer id) {
		ArrayList ret = null;
		FormularioHeader222 result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM222).withProcedureName(ConstantsLaboratorio.PRC_GET_HEADER222)
		.declareParameters(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormularioHeader222>() {

					@Override
					public FormularioHeader222 mapRow(ResultSet rs, int rowNum) throws SQLException {
						FormularioHeader222 record = new FormularioHeader222();
						
						record.setStrfechaMuestreo(rs.getString(1));
						record.setPerMuestrea(rs.getInt(2));
						record.setPerMuestrea2(rs.getInt(3));
						record.setPerRecepciona(rs.getInt(4));
						record.setPerRecepciona2(rs.getInt(5));
						record.setPerRecepciona3(rs.getInt(6));
						record.setPerAnalista(rs.getInt(7));
						record.setCodigoHeader(rs.getInt(8));
						record.setObservacion(rs.getString(9));
						record.setCodMuestra(rs.getString(10));
						record.setCodMuestra2(rs.getString(11));
						record.setCodMuestra3(rs.getString(12));
						record.setDup(rs.getDouble(13));
						record.setDup2(rs.getDouble(14));
						record.setDup3(rs.getDouble(15));
						record.setDup4(rs.getDouble(16));
						record.setDup5(rs.getDouble(17));
						record.setPro(rs.getDouble(18));
						record.setPro2(rs.getDouble(19));
						record.setPro3(rs.getDouble(20));
						record.setPro4(rs.getDouble(21));
						record.setPro5(rs.getDouble(22));
						record.setRds(rs.getDouble(23));
						record.setRds2(rs.getDouble(24));
						record.setRds3(rs.getDouble(25));
						record.setRds4(rs.getDouble(26));
						record.setRds5(rs.getDouble(27));
						
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(ConstantsLaboratorio.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_ID_HEADER, id);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (FormularioHeader222) ret.get(0);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#grabarFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222)
	 */
	@Override
	public int grabarFormularioHeader(FormularioHeader222 formularioHeader222) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDMUESTREA1, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDMUESTREA2, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA1, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA2, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA3, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDANALISTA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERV, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA1, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA2, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA3, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP4, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP5, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO4, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO5, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS4, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS5, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FEC_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));	
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM222,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_HEADER222), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
		    inputs.put(ConstantsLaboratorio.P_IDMUESTREA1, formularioHeader222.getPerMuestrea());
			inputs.put(ConstantsLaboratorio.P_IDMUESTREA2, formularioHeader222.getPerMuestrea2());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA1, formularioHeader222.getPerRecepciona());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA2, formularioHeader222.getPerRecepciona2());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA3, formularioHeader222.getPerRecepciona3());
			inputs.put(ConstantsLaboratorio.P_IDANALISTA, formularioHeader222.getPerAnalista());
			inputs.put(ConstantsLaboratorio.P_OBSERV, formularioHeader222.getObservacion()!=null?formularioHeader222.getObservacion().trim():" ");
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA1, formularioHeader222.getCodMuestra());
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA2, formularioHeader222.getCodMuestra2());
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA3, formularioHeader222.getCodMuestra3());
			inputs.put(ConstantsLaboratorio.P_DUP1, formularioHeader222.getDup());
			inputs.put(ConstantsLaboratorio.P_DUP2, formularioHeader222.getDup2());
			inputs.put(ConstantsLaboratorio.P_DUP3, formularioHeader222.getDup3());
			inputs.put(ConstantsLaboratorio.P_DUP4, formularioHeader222.getDup4());
			inputs.put(ConstantsLaboratorio.P_DUP5, formularioHeader222.getDup5());
			inputs.put(ConstantsLaboratorio.P_PRO1, formularioHeader222.getPro());
			inputs.put(ConstantsLaboratorio.P_PRO2, formularioHeader222.getPro2());
			inputs.put(ConstantsLaboratorio.P_PRO3, formularioHeader222.getPro3());
			inputs.put(ConstantsLaboratorio.P_PRO4, formularioHeader222.getPro4());
			inputs.put(ConstantsLaboratorio.P_PRO5, formularioHeader222.getPro5());
			inputs.put(ConstantsLaboratorio.P_RDS1, formularioHeader222.getRds());
			inputs.put(ConstantsLaboratorio.P_RDS2, formularioHeader222.getRds2());
			inputs.put(ConstantsLaboratorio.P_RDS3, formularioHeader222.getRds3());
			inputs.put(ConstantsLaboratorio.P_RDS4, formularioHeader222.getRds4());
			inputs.put(ConstantsLaboratorio.P_RDS5, formularioHeader222.getRds5());
			inputs.put(ConstantsLaboratorio.P_FEC_MUESTREO, formularioHeader222.getStrfechaMuestreo());
			inputs.put(Constants.A_PAR_USUARIO_CREACION, formularioHeader222.getUsuarioCreacion());		
			inputs.put(Constants.A_PAR_USUARIO_MODIFICACION,formularioHeader222.getUsuarioModificacion());		
			inputs.put(Constants.A_PAR_NOMBRE_PROGRAMA, formularioHeader222.getPrograma());	
			
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#grabarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void grabarFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FEC_MUESTREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HOR_MUESTREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NRO_FRASCO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PUNTO_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COD_MUESTRA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NRO_ENVASE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TIPO_MUESTRA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TIPO_PRESERVACION, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PH, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TEMP, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COND, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TURB, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_MN, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PB, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ZN, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_AS, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CD, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_BA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_AL, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SB, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CU, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_K, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HG,OracleTypes.VARCHAR));
			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_THM,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TOC,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DQO,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DETERG,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FENOLES,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NO2,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NO3,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PO4,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SDT,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ST,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SST,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DT,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CA,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_MG,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ALC,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CL,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SO4,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COLOR,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_F,OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CN,OracleTypes.VARCHAR));
			
		    lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_T_ENVASE, OracleTypes.VARCHAR));

			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_CREACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_USUARIO_MODIFICACION, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.A_PAR_NOMBRE_PROGRAMA, OracleTypes.VARCHAR));	
	
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
	    
			this.execSp = new ExecuteProcedure(template.getDataSource(), 
					CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM222,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_DETALLE222), 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
		    inputs.put(ConstantsLaboratorio.P_ID_HEADER, formularioDetalle222.getIdHeader());
			inputs.put(ConstantsLaboratorio.P_FEC_MUESTREO, formularioDetalle222.getStrfechaHora()); 
			inputs.put(ConstantsLaboratorio.P_HOR_MUESTREO, formularioDetalle222.getHora());
			inputs.put(ConstantsLaboratorio.P_NRO_FRASCO, formularioDetalle222.getNfrasco());
			inputs.put(ConstantsLaboratorio.P_PUNTO_MUESTREO, formularioDetalle222.getPuntoMuestreo());
			inputs.put(ConstantsLaboratorio.P_COD_MUESTRA, formularioDetalle222.getCodMuestra());
			inputs.put(ConstantsLaboratorio.P_NRO_ENVASE, formularioDetalle222.getNroEnvases());
			inputs.put(ConstantsLaboratorio.P_TIPO_MUESTRA, formularioDetalle222.getTipoMuestra());
			inputs.put(ConstantsLaboratorio.P_TIPO_PRESERVACION,formularioDetalle222.getPreservacion());
			inputs.put(ConstantsLaboratorio.P_OD, formularioDetalle222.getOd());
			inputs.put(ConstantsLaboratorio.P_PH, formularioDetalle222.getPh());
			inputs.put(ConstantsLaboratorio.P_TEMP, formularioDetalle222.getTemp());
			inputs.put(ConstantsLaboratorio.P_COND, formularioDetalle222.getCond());
			inputs.put(ConstantsLaboratorio.P_TURB, formularioDetalle222.getTurb());
			inputs.put(ConstantsLaboratorio.P_FE, formularioDetalle222.getFe2());
			inputs.put(ConstantsLaboratorio.P_MN, formularioDetalle222.getMn2());
			inputs.put(ConstantsLaboratorio.P_CR, formularioDetalle222.getCr2());
			inputs.put(ConstantsLaboratorio.P_PB, formularioDetalle222.getPb2());
			inputs.put(ConstantsLaboratorio.P_ZN, formularioDetalle222.getZn2());
			inputs.put(ConstantsLaboratorio.P_AS, formularioDetalle222.getAs2());
			inputs.put(ConstantsLaboratorio.P_CD, formularioDetalle222.getCd2());
			inputs.put(ConstantsLaboratorio.P_BA, formularioDetalle222.getBa2());
			inputs.put(ConstantsLaboratorio.P_SE, formularioDetalle222.getSe2());
			inputs.put(ConstantsLaboratorio.P_AL ,formularioDetalle222.getAl2());
			inputs.put(ConstantsLaboratorio.P_NA, formularioDetalle222.getNa2());
			inputs.put(ConstantsLaboratorio.P_SB, formularioDetalle222.getSb2());
			inputs.put(ConstantsLaboratorio.P_CU, formularioDetalle222.getCu2());
			inputs.put(ConstantsLaboratorio.P_K, formularioDetalle222.getK2());
			inputs.put(ConstantsLaboratorio.P_HG, formularioDetalle222.getHg2());
			
			inputs.put(ConstantsLaboratorio.P_THM, formularioDetalle222.getThm2());
			inputs.put(ConstantsLaboratorio.P_TOC, formularioDetalle222.getToc2());
			inputs.put(ConstantsLaboratorio.P_DQO, formularioDetalle222.getDqo2());
			inputs.put(ConstantsLaboratorio.P_DETERG, formularioDetalle222.getDeterg2());
			inputs.put(ConstantsLaboratorio.P_FENOLES, formularioDetalle222.getFenoles2());
			inputs.put(ConstantsLaboratorio.P_NO2, formularioDetalle222.getNo22());
			inputs.put(ConstantsLaboratorio.P_NO3, formularioDetalle222.getNo32());
			inputs.put(ConstantsLaboratorio.P_PO4, formularioDetalle222.getPo42());
			inputs.put(ConstantsLaboratorio.P_SDT, formularioDetalle222.getSdt2());
			inputs.put(ConstantsLaboratorio.P_ST, formularioDetalle222.getSt2());
			inputs.put(ConstantsLaboratorio.P_SST, formularioDetalle222.getSst2());
			inputs.put(ConstantsLaboratorio.P_DT, formularioDetalle222.getDt2());
			inputs.put(ConstantsLaboratorio.P_CA, formularioDetalle222.getCa2());
			inputs.put(ConstantsLaboratorio.P_MG, formularioDetalle222.getMg2());
			inputs.put(ConstantsLaboratorio.P_ALC, formularioDetalle222.getAlc2());
			inputs.put(ConstantsLaboratorio.P_CL, formularioDetalle222.getCl2());
			inputs.put(ConstantsLaboratorio.P_SO4, formularioDetalle222.getSo42());
			inputs.put(ConstantsLaboratorio.P_COLOR, formularioDetalle222.getColor2());
			inputs.put(ConstantsLaboratorio.P_F, formularioDetalle222.getF2());
			inputs.put(ConstantsLaboratorio.P_CN, formularioDetalle222.getCn2());
			
			
			inputs.put(ConstantsLaboratorio.P_T_ENVASE, formularioDetalle222.getTipoEnvase());
			
			inputs.put(Constants.A_PAR_USUARIO_CREACION, formularioDetalle222.getUsuarioCreacion());		
			inputs.put(Constants.A_PAR_USUARIO_MODIFICACION,formularioDetalle222.getUsuarioModificacion());		
			inputs.put(Constants.A_PAR_NOMBRE_PROGRAMA, formularioDetalle222.getPrograma());	
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#getListadoFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result getListadoFormularioDetalle(FormularioDetalle222 formularioDetalle222, Paginacion paginacion) {
		
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM222).withProcedureName(ConstantsLaboratorio.SP_LISTADO_DETALLE222)
				.declareParameters(
						// parametros de búsquedaConstantsLaboratorio
						new SqlParameter(ConstantsLaboratorio.P_COD_HEADER222, Types.VARCHAR),
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
										record.add(rs.getString(1)); // FECHA
										record.add(rs.getString(2)); // HORA
										record.add(rs.getString(3)); // NRO FRASCO
										record.add(rs.getString(4)); // PUNTO M
										record.add(rs.getString(5)); // COD MUESTRA
										record.add(rs.getString(6)); // ENVASE
										record.add(rs.getString(7)); // NRO DE ENVASES
										record.add(rs.getString(8)); // TIPO MUESTRA
										record.add(rs.getString(9)); // TIPO PRESERV
										record.add(rs.getString(10)); // PARAMETROS
										record.add(rs.getInt(11));   // COD_DETALLE
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_COD_HEADER222, formularioDetalle222.getIdHeader());
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
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#updateFormularioHeader(pe.com.sedapal.scr.core.beans.FormularioHeader222)
	 */
	@Override
	public void updateFormularioHeader(FormularioHeader222 formularioHeader222) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FEC_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDMUESTREA1, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDMUESTREA2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDRECEPCIONA3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_IDANALISTA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OBSERV, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA1, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA2, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CODMUESTRA3, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP4, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DUP5, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO1, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO2, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO3, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO4, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PRO5, OracleTypes.NUMBER));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS1, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS2, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS3, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS4, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_RDS5, OracleTypes.NUMBER));
			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_USUARIO_MODIF, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NOMBRE_PROG, OracleTypes.VARCHAR));
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM222 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_UPDATE_HEADER222, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_FEC_MUESTREO, formularioHeader222.getStrfechaMuestreo());
			inputs.put(ConstantsLaboratorio.P_IDMUESTREA1, formularioHeader222.getPerMuestrea());
			inputs.put(ConstantsLaboratorio.P_IDMUESTREA2, formularioHeader222.getPerMuestrea2());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA1, formularioHeader222.getPerRecepciona());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA2, formularioHeader222.getPerRecepciona2());
			inputs.put(ConstantsLaboratorio.P_IDRECEPCIONA3, formularioHeader222.getPerRecepciona3());
			inputs.put(ConstantsLaboratorio.P_IDANALISTA, formularioHeader222.getPerAnalista());
			inputs.put(ConstantsLaboratorio.P_OBSERV, formularioHeader222.getObservacion()!=null?formularioHeader222.getObservacion().trim():" ");
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA1, formularioHeader222.getCodMuestra());
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA2, formularioHeader222.getCodMuestra2());
			inputs.put(ConstantsLaboratorio.P_CODMUESTRA3, formularioHeader222.getCodMuestra3());
			inputs.put(ConstantsLaboratorio.P_DUP1, formularioHeader222.getDup());
			inputs.put(ConstantsLaboratorio.P_DUP2, formularioHeader222.getDup2());
			inputs.put(ConstantsLaboratorio.P_DUP3, formularioHeader222.getDup3());
			inputs.put(ConstantsLaboratorio.P_DUP4, formularioHeader222.getDup4());
			inputs.put(ConstantsLaboratorio.P_DUP5, formularioHeader222.getDup5());
			inputs.put(ConstantsLaboratorio.P_PRO1, formularioHeader222.getPro());
			inputs.put(ConstantsLaboratorio.P_PRO2, formularioHeader222.getPro2());
			inputs.put(ConstantsLaboratorio.P_PRO3, formularioHeader222.getPro3());
			inputs.put(ConstantsLaboratorio.P_PRO4, formularioHeader222.getPro4());
			inputs.put(ConstantsLaboratorio.P_PRO5, formularioHeader222.getPro5());
			inputs.put(ConstantsLaboratorio.P_RDS1, formularioHeader222.getRds());
			inputs.put(ConstantsLaboratorio.P_RDS2, formularioHeader222.getRds2());
			inputs.put(ConstantsLaboratorio.P_RDS3, formularioHeader222.getRds3());
			inputs.put(ConstantsLaboratorio.P_RDS4, formularioHeader222.getRds4());
			inputs.put(ConstantsLaboratorio.P_RDS5, formularioHeader222.getRds5());
			
			inputs.put(ConstantsLaboratorio.P_ID_HEADER, formularioHeader222.getCodigoHeader());
			inputs.put(ConstantsLaboratorio.P_USUARIO_MODIF,formularioHeader222.getUsuarioModificacion());	
			inputs.put(ConstantsLaboratorio.P_NOMBRE_PROG, formularioHeader222.getPrograma());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#getFormularioDetalle(java.lang.Integer)
	 */
	@Override
	public FormularioDetalle222 getFormularioDetalle(Integer id) {
		ArrayList ret = null;
		FormularioDetalle222 result = null;
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PKC_ALC_FORM222).withProcedureName(ConstantsLaboratorio.PRC_GET_DETALLE222)
		.declareParameters(new SqlParameter(ConstantsLaboratorio.P_ID_DETALLE222, Types.INTEGER), new SqlOutParameter(
				Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<FormularioDetalle222>() {

					@Override
					public FormularioDetalle222 mapRow(ResultSet rs, int rowNum) throws SQLException {
						FormularioDetalle222 record = new FormularioDetalle222();
						
						record.setIdDetalle(rs.getInt(1));
						record.setStrfechaHora(rs.getString(2));
						record.setHora(rs.getString(3));
						record.setNfrasco(rs.getString(4));
						record.setPuntoMuestreo(rs.getString(5));
						record.setCodMuestra(rs.getString(6));
						record.setNroEnvases(rs.getString(7));
						record.setTipoMuestra(rs.getString(8));
						record.setPreservacion(rs.getString(9));
						record.setOd(rs.getDouble(10));
						record.setPh(rs.getDouble(11));
						record.setTemp(rs.getDouble(12));
						record.setCond(rs.getDouble(13));
						record.setTurb(rs.getDouble(14));
						record.setFe2(rs.getString(15));
						record.setMn2(rs.getString(16));
						record.setCr2(rs.getString(17));
						record.setPb2(rs.getString(18));
						record.setZn2(rs.getString(19));
						record.setAs2(rs.getString(20));
						record.setCd2(rs.getString(21));
						record.setBa2(rs.getString(22));
						record.setSe2(rs.getString(23));
						record.setAl2(rs.getString(24));
						record.setNa2(rs.getString(25));
						record.setSb2(rs.getString(26));
						record.setCu2(rs.getString(27));
						record.setK2(rs.getString(28));
						record.setHg2(rs.getString(29));
						
						record.setThm2(rs.getString(30));
						record.setToc2(rs.getString(31));
						record.setDqo2(rs.getString(32));
						record.setDeterg2(rs.getString(33));
						record.setFenoles2(rs.getString(34));
						record.setNo22(rs.getString(35));
						record.setNo32(rs.getString(36));
						record.setPo42(rs.getString(37));
						record.setSdt2(rs.getString(38));
						record.setSt2(rs.getString(39));
						record.setSst2(rs.getString(40));
						record.setDt2(rs.getString(41));
						record.setCa2(rs.getString(42));
						record.setMg2(rs.getString(43));
						record.setAlc2(rs.getString(44));
						record.setCl2(rs.getString(45));
						record.setSo42(rs.getString(46));
						record.setColor2(rs.getString(47));
						record.setF2(rs.getString(48));
						record.setCn2(rs.getString(49));	
						
						record.setTipoEnvase(rs.getString(50));
						
						return record;
					}

				}))
		.withSchemaName(environment.getRequiredProperty(ConstantsLaboratorio.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.P_ID_DETALLE222, id);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);

		result = (FormularioDetalle222) ret.get(0);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#updateFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void updateFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;

		Map<String, Object> inputs = null;
		try {
			lstParamsInput = new ArrayList<SqlParameter>();
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_DETALLE, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FEC_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HOR_MUESTREO, OracleTypes.VARCHAR));	
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NRO_FRASCO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PUNTO_MUESTREO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COD_MUESTRA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NRO_ENVASE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TIPO_MUESTRA, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TIPO_PRESERVACION, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_OD, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PH, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TEMP, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COND, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TURB, OracleTypes.NUMBER));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_MN, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PB, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ZN, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_AS, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CD, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_BA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SE, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_AL, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SB, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CU, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_K, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_HG,OracleTypes.VARCHAR));
			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_THM, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_TOC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DQO, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DETERG, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_FENOLES, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NO2, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NO3, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_PO4, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SDT, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ST, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SST, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_DT, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CA, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_MG, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ALC, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CL, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_SO4, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_COLOR, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_F, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_CN, OracleTypes.VARCHAR));
			
		    lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_T_ENVASE, OracleTypes.VARCHAR));
			
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_USUARIO_MODIF, OracleTypes.VARCHAR));
			lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_NOMBRE_PROG, OracleTypes.VARCHAR));
			
			lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PKC_ALC_FORM222 + ConstantsLaboratorio.P_SEPARADOR + ConstantsLaboratorio.SP_UPDATE_DETALLE222, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(ConstantsLaboratorio.P_ID_DETALLE, formularioDetalle222.getIdDetalle()); 
			inputs.put(ConstantsLaboratorio.P_FEC_MUESTREO, formularioDetalle222.getStrfechaHora()); 
			inputs.put(ConstantsLaboratorio.P_HOR_MUESTREO, formularioDetalle222.getHora());
			inputs.put(ConstantsLaboratorio.P_NRO_FRASCO, formularioDetalle222.getNfrasco());
			inputs.put(ConstantsLaboratorio.P_PUNTO_MUESTREO, formularioDetalle222.getPuntoMuestreo());
			inputs.put(ConstantsLaboratorio.P_COD_MUESTRA, formularioDetalle222.getCodMuestra());
			inputs.put(ConstantsLaboratorio.P_NRO_ENVASE, formularioDetalle222.getNroEnvases());
			inputs.put(ConstantsLaboratorio.P_TIPO_MUESTRA, formularioDetalle222.getTipoMuestra());
			inputs.put(ConstantsLaboratorio.P_TIPO_PRESERVACION,formularioDetalle222.getPreservacion());
			inputs.put(ConstantsLaboratorio.P_OD, formularioDetalle222.getOd());
			inputs.put(ConstantsLaboratorio.P_PH, formularioDetalle222.getPh());
			inputs.put(ConstantsLaboratorio.P_TEMP, formularioDetalle222.getTemp());
			inputs.put(ConstantsLaboratorio.P_COND, formularioDetalle222.getCond());
			inputs.put(ConstantsLaboratorio.P_TURB, formularioDetalle222.getTurb());
			inputs.put(ConstantsLaboratorio.P_FE, formularioDetalle222.getFe2());
			inputs.put(ConstantsLaboratorio.P_MN, formularioDetalle222.getMn2());
			inputs.put(ConstantsLaboratorio.P_CR, formularioDetalle222.getCr2());
			inputs.put(ConstantsLaboratorio.P_PB, formularioDetalle222.getPb2());
			inputs.put(ConstantsLaboratorio.P_ZN, formularioDetalle222.getZn2());
			inputs.put(ConstantsLaboratorio.P_AS, formularioDetalle222.getAs2());
			inputs.put(ConstantsLaboratorio.P_CD, formularioDetalle222.getCd2());
			inputs.put(ConstantsLaboratorio.P_BA, formularioDetalle222.getBa2());
			inputs.put(ConstantsLaboratorio.P_SE, formularioDetalle222.getSe2());
			inputs.put(ConstantsLaboratorio.P_AL ,formularioDetalle222.getAl2());
			inputs.put(ConstantsLaboratorio.P_NA, formularioDetalle222.getNa2());
			inputs.put(ConstantsLaboratorio.P_SB, formularioDetalle222.getSb2());
			inputs.put(ConstantsLaboratorio.P_CU, formularioDetalle222.getCu2());
			inputs.put(ConstantsLaboratorio.P_K, formularioDetalle222.getK2());
			inputs.put(ConstantsLaboratorio.P_HG, formularioDetalle222.getHg2());
			
			inputs.put(ConstantsLaboratorio.P_THM, formularioDetalle222.getThm2());
			inputs.put(ConstantsLaboratorio.P_TOC, formularioDetalle222.getToc2());
			inputs.put(ConstantsLaboratorio.P_DQO, formularioDetalle222.getDqo2());
			inputs.put(ConstantsLaboratorio.P_DETERG, formularioDetalle222.getDeterg2());
			inputs.put(ConstantsLaboratorio.P_FENOLES, formularioDetalle222.getFenoles2());
			inputs.put(ConstantsLaboratorio.P_NO2, formularioDetalle222.getNo22());
			inputs.put(ConstantsLaboratorio.P_NO3, formularioDetalle222.getNo32());
			inputs.put(ConstantsLaboratorio.P_PO4, formularioDetalle222.getPo42());
			inputs.put(ConstantsLaboratorio.P_SDT, formularioDetalle222.getSdt2());
			inputs.put(ConstantsLaboratorio.P_ST, formularioDetalle222.getSt2());
			inputs.put(ConstantsLaboratorio.P_SST, formularioDetalle222.getSst2());
			inputs.put(ConstantsLaboratorio.P_DT, formularioDetalle222.getDt2());
			inputs.put(ConstantsLaboratorio.P_CA, formularioDetalle222.getCa2());
			inputs.put(ConstantsLaboratorio.P_MG, formularioDetalle222.getMg2());
			inputs.put(ConstantsLaboratorio.P_ALC, formularioDetalle222.getAlc2());
			inputs.put(ConstantsLaboratorio.P_CL, formularioDetalle222.getCl2());
			inputs.put(ConstantsLaboratorio.P_SO4, formularioDetalle222.getSo42());
			inputs.put(ConstantsLaboratorio.P_COLOR, formularioDetalle222.getColor2());
			inputs.put(ConstantsLaboratorio.P_F, formularioDetalle222.getF2());
			inputs.put(ConstantsLaboratorio.P_CN, formularioDetalle222.getCn2());
			
			inputs.put(ConstantsLaboratorio.P_T_ENVASE, formularioDetalle222.getTipoEnvase());
			
			inputs.put(ConstantsLaboratorio.P_USUARIO_MODIF,formularioDetalle222.getUsuarioModificacion());	
			inputs.put(ConstantsLaboratorio.P_NOMBRE_PROG, formularioDetalle222.getPrograma());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#inactivarFormularioDetalle(pe.com.sedapal.scr.core.beans.FormularioDetalle222)
	 */
	@Override
	public void inactivarFormularioDetalle(FormularioDetalle222 formularioDetalle222) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_DETALLE, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.P_STATUS, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM222,ConstantsLaboratorio.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_DETALLE222), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.P_ID_DETALLE, formularioDetalle222.getIdDetalle());	
		inputs.put(ConstantsLaboratorio.P_STATUS, formularioDetalle222.getStatus());
		
		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IFormulario222Dao#registrosPorHeader(int)
	 */
	@Override
	public int registrosPorHeader(int header)  {
		
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno = 0;

		lstParamsInput = new ArrayList<SqlParameter>();	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.P_ID_HEADER, OracleTypes.NUMBER));		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PKC_ALC_FORM222,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_COUNT_DETALLE), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		
		inputs = new HashMap<String, Object>();
		inputs.put(ConstantsLaboratorio.P_ID_HEADER, header);		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	
}
