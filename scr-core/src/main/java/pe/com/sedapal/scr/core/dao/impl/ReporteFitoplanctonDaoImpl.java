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
import pe.com.sedapal.scr.core.beans.PlanOperativo;
import pe.com.sedapal.scr.core.beans.ReporteFitoplanctonDetalle;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteFitoplanctonDaoImpl.
 */
@Repository
public class ReporteFitoplanctonDaoImpl implements IReporteFitoplanctonDao {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ReporteFitoplanctonDaoImpl.class);

	/** The params input. */
	List<SqlParameter> paramsInput = null;
	
	/** The params output. */
	List<SqlOutParameter> paramsOutput = null;
	
	/** The inputs. */
	Map<String, Object> inputs = null;

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
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#datosDetalleReporteFitoplancton(java.lang.String, java.lang.Integer)
	 */
	@Override
	public ReporteFitoplanctonDetalle datosDetalleReporteFitoplancton(String fechaAnalisis, Integer puntoMuestreo) {
		ReporteFitoplanctonDetalle reporteFitoplanctonDetalle  = new ReporteFitoplanctonDetalle();
		
		ArrayList<ReporteFitoplanctonDetalle> ret = null;
		PlanOperativo result = null;
		SimpleJdbcCall caller = null;
		
		try {
		
		caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORFITODETALLE)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECREP, Types.VARCHAR),
						new SqlParameter(ConstantsLaboratorio.PAR_PUNTO, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteFitoplanctonDetalle>() {
									@Override
									public ReporteFitoplanctonDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
										//N_LOCMUE, D_FECMUE, N_CLOROF, N_FICOCI
										ReporteFitoplanctonDetalle reporteFitoplanctonDetalle = new ReporteFitoplanctonDetalle();
										reporteFitoplanctonDetalle.setFechaMuestreoDate(rs.getDate(2));
										reporteFitoplanctonDetalle.setCodsub(rs.getInt(1));
										reporteFitoplanctonDetalle.setClorofila(rs.getDouble(3));
										reporteFitoplanctonDetalle.setFicocianina(rs.getDouble(4));

										return reporteFitoplanctonDetalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECREP, fechaAnalisis);
		params.addValue(ConstantsLaboratorio.PAR_PUNTO, puntoMuestreo);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList<ReporteFitoplanctonDetalle>) results.get(Constants.PAR_OUT_CURSOR);
		if(ret!=null && !ret.isEmpty()){
			reporteFitoplanctonDetalle = ret.get(0);	
			
		}else{
			return new ReporteFitoplanctonDetalle();
		}
		
		return reporteFitoplanctonDetalle;
	 
	}



	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#datosReporteFitoplancton(java.lang.String, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result datosReporteFitoplancton(String fechaReporte, Paginacion paginacion) {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORTEFITO)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECREP, Types.VARCHAR),
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
//										N_CODREP, D_FECREP, D_FECMUE, N_PERMUE, N_PERANA 
										record.add(rs.getInt(1)); // N_CODREP
										record.add(rs.getString(2)); // D_FECREP
										record.add(rs.getString(3)); // D_FECMUE
										record.add(rs.getInt(4)); // N_PERMUE
										record.add(rs.getInt(5)); // N_PERANA
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECREP, fechaReporte);
	
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
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#insertarReporteFitoplancton(pe.com.sedapal.scr.core.beans.ReporteFitoplanctonDetalle)
	 */
	@Override
	public void insertarReporteFitoplancton(ReporteFitoplanctonDetalle reporteFitoplanctonDetalle) {
		paramsInput = new ArrayList<>();
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));

		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, OracleTypes.DATE));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERMUE, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERANA, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CORDET, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODSUB, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANCLO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANFIC, OracleTypes.NUMBER));
		
		paramsOutput = new ArrayList<>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON
				+ Constants.P_SEPARADOR + ConstantsLaboratorio.PRC_INSERT_REPORTEFITO, 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<>();

		inputs.put(ConstantsLaboratorio.PAR_USUCRE, reporteFitoplanctonDetalle.getUsuarioCreacion());
		inputs.put(ConstantsLaboratorio.PAR_NOMPRG, reporteFitoplanctonDetalle.getPrograma());

		inputs.put(ConstantsLaboratorio.PAR_FECMUE, Utils.getDateOracle(reporteFitoplanctonDetalle.getFechaMuestreo()));
		inputs.put(ConstantsLaboratorio.PAR_PERMUE, reporteFitoplanctonDetalle.getPersonaMuestra());
		inputs.put(ConstantsLaboratorio.PAR_PERANA, reporteFitoplanctonDetalle.getAnalista());
		inputs.put(ConstantsLaboratorio.PAR_CORDET, reporteFitoplanctonDetalle.getCorrelativo());
		inputs.put(ConstantsLaboratorio.PAR_CODSUB, reporteFitoplanctonDetalle.getCodsub());
		inputs.put(ConstantsLaboratorio.PAR_CANCLO, reporteFitoplanctonDetalle.getClorofila());
		inputs.put(ConstantsLaboratorio.PAR_CANFIC, reporteFitoplanctonDetalle.getFicocianina());

		this.execSp.executeSp(inputs);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#obtenerCorrelativoDetalleReporte()
	 */
	@Override
	public Integer obtenerCorrelativoDetalleReporte() {

			
			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON)
					.withProcedureName(ConstantsLaboratorio.PRC_CORREDET_REPORTEFITO)
					.declareParameters(
							// parametros de búsqueda
							new SqlParameter(ConstantsLaboratorio.PAR_FECREP, Types.VARCHAR),
							// parametros de arquitectura
							new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
							new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
							new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
							new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
							new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER),
							new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER)).
					withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			Map<String, Object> results = caller.execute(params);
			int correlativoDetalle = 0;
			
			if(results.get(Constants.PAR_OUTQUANTITY)!=null){
				correlativoDetalle = (int) results.get(Constants.PAR_OUTQUANTITY);
			}
			 
			return correlativoDetalle;
	}


	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#obtenerDetallePorCorrelativo(java.lang.Integer)
	 */
	@Override
	public ArrayList<ReporteFitoplanctonDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo) {
		
		ArrayList<ReporteFitoplanctonDetalle> ret = null;
		PlanOperativo result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_DETXCORRELFITO)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_CORDET, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteFitoplanctonDetalle>() {
									// D_FECREP,D_FECMUE,N_PERMUE,N_PERANA, N_CORDET,N_CODSUB,N_CANALG,N_CIANOB, N_NEMATO, N_ZOTROS, N_TOTORG, N_ECAMIN
									@Override
									public ReporteFitoplanctonDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										ReporteFitoplanctonDetalle reporteFitoplanctonDetalle = new ReporteFitoplanctonDetalle();
										reporteFitoplanctonDetalle.setFechaReporte(rs.getString(1));
										reporteFitoplanctonDetalle.setFechaMuestreoDate(rs.getDate(2));
										reporteFitoplanctonDetalle.setPersonaMuestra(rs.getInt(3));
										reporteFitoplanctonDetalle.setAnalista(rs.getInt(4));
										reporteFitoplanctonDetalle.setCorrelativo(rs.getInt(5));
										reporteFitoplanctonDetalle.setCodsub(rs.getInt(6));
										reporteFitoplanctonDetalle.setClorofila(rs.getDouble(7));
										reporteFitoplanctonDetalle.setFicocianina(rs.getDouble(8));

										return reporteFitoplanctonDetalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_CORDET, idCorrelativo);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList<ReporteFitoplanctonDetalle>) results.get(Constants.PAR_OUT_CURSOR);
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IReporteFitoplanctonDao#eliminarReportePorCorrelativo(java.lang.Integer)
	 */
	public boolean eliminarReportePorCorrelativo(Integer idCorrelativo) {
		boolean eliminado = true;
		try {
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CORDET, OracleTypes.INTEGER));
		
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_REPORFITOPLANCTON
					+ Constants.P_SEPARADOR + ConstantsLaboratorio.PRC_DELETE_REPORTEFITO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();

			inputs.put(ConstantsLaboratorio.PAR_CORDET, idCorrelativo);

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			LOG.error("Error al eliminar correlativo",e);
			eliminado = false;
		}
		return eliminado;
	}
	

}
