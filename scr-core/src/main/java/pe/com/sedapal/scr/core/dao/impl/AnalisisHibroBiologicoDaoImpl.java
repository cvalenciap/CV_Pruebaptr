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
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico;
import pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHibroBiologicoDaoImpl.
 */
@Repository
public class AnalisisHibroBiologicoDaoImpl implements IAnalisisHibroBiologicoDao {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AnalisisHibroBiologicoDaoImpl.class);

	/** The params input. */
	List<SqlParameter> paramsInput = null;
	
	/** The params output. */
	List<SqlOutParameter> paramsOutput = null;
	
	/** The inputs. */
	Map<String, Object> inputs = null;
	
	
	/** The message source. */
	@Autowired
	MessageSource messageSource;
	
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#obtenerDatosAnalisisHidroBiologico(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Result obtenerDatosAnalisisHidroBiologico(String fecha, Integer codsubac) {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#insertarAnalisisHidroBiologico(pe.com.sedapal.scr.core.beans.AnalisisHidroBiologico)
	 */
	@Override
	public void insertarAnalisisHidroBiologico(AnalisisHidroBiologico analisisHidroBiologico) {
		
		paramsInput = new ArrayList<>();
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));

		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODOXS, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECANA, OracleTypes.TIMESTAMP));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TIPORG, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODSUB, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODORG, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DESCRI, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANTID, OracleTypes.NUMBER));
		
		paramsOutput = new ArrayList<>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO
				+ Constants.P_SEPARADOR + ConstantsLaboratorio.PRC_INSERT_ANALISISHIDROBIO, 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<>();

		inputs.put(ConstantsLaboratorio.PAR_USUCRE, analisisHidroBiologico.getUsuarioCreacion());
		inputs.put(ConstantsLaboratorio.PAR_NOMPRG, analisisHidroBiologico.getPrograma());

		inputs.put(ConstantsLaboratorio.PAR_CODOXS, analisisHidroBiologico.getCodigo());
//		LOG.info("analisisHidroBiologico.getFechaAnalisis()).getTime(): " + analisisHidroBiologico.getFechaAnalisis()).getTime());
		
		inputs.put(ConstantsLaboratorio.PAR_FECANA, new java.sql.Timestamp(Utils.getDateOracle(analisisHidroBiologico.getFechaAnalisis()).getTime()));
		inputs.put(ConstantsLaboratorio.PAR_TIPORG, analisisHidroBiologico.getTipoOrganismo());
		inputs.put(ConstantsLaboratorio.PAR_CODSUB, analisisHidroBiologico.getCodsub());
		inputs.put(ConstantsLaboratorio.PAR_CODORG, analisisHidroBiologico.getCodorg());
		inputs.put(ConstantsLaboratorio.PAR_DESCRI, analisisHidroBiologico.getDescripcion());
		inputs.put(ConstantsLaboratorio.PAR_CANTID, new Double(analisisHidroBiologico.getCantidad()));

		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#datosReporteAnalisisHidroBiologico(java.lang.String, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result datosReporteAnalisisHidroBiologico(String fechaReporte, Paginacion paginacion){
			List lstRetorno = null;

			SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
			caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORTEHIDRO)
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
//											N_CODREP, D_FECREP, D_FECMUE, N_PERMUE, N_PERANA 
											record.add(rs.getInt(1)); // N_CODREP
											record.add(rs.getString(2)); // D_FECREP
											record.add(Utils.formatFechaStringddMMYYY(rs.getDate(3))); // D_FECMUE
											record.add(rs.getInt(4)); // N_PERMUE
											record.add(rs.getInt(5)); // N_PERANA
											return record;
										}
									}))
					.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			LOG.info("paginacion.getColorderby(): " + paginacion.getColorderby());
			LOG.info("paginacion.getColorderbydir(): " + paginacion.getColorderbydir());
			LOG.info("paginacion.getPagdesde(): " + paginacion.getPagdesde());
			LOG.info("paginacion.getCantidadpag(): " + paginacion.getCantidadpag());
			
			params.addValue(ConstantsLaboratorio.PAR_FECREP, fechaReporte);
			params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
			params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
			params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
			params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());
			Map<String, Object> results = caller.execute(params);
			int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
			lstRetorno = (List) results.get(ConstantsCommon.PAR_OUT_CURSOR);
			
			LOG.info("lstRetorno: " + lstRetorno);
			
			Result result = new Result();
			result.setData(lstRetorno);
			result.setRecords((long) quantity);
			return result;
	}


	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#datosDetalleReporteHidroBiologico(java.lang.String, java.lang.Integer)
	 */
	@Override
	public ReporteAnalisisHidroDetalle datosDetalleReporteHidroBiologico(String fechaAnalisis, Integer puntoMuestreo) {
		ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle  = new ReporteAnalisisHidroDetalle();
		
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORHIDRODETALLE)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECREP, Types.VARCHAR),
						new SqlParameter(ConstantsLaboratorio.PAR_PUNTO, Types.INTEGER),
						
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ArrayList>() {
									@SuppressWarnings("unchecked")
									@Override
									public ArrayList mapRow(ResultSet rs, int rowNum) throws SQLException {
										ArrayList record = new ArrayList();
										System.out.println("TIPORG:  " + rs.getInt(1));
										
										record.add(rs.getInt(1)); // C_TIPORG
										record.add(rs.getString(2)); // N_CANTID
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECREP, fechaAnalisis);
		params.addValue(ConstantsLaboratorio.PAR_PUNTO, puntoMuestreo);
	
		Map<String, Object> results = caller.execute(params);
		List listaDetalles = (List)results.get(ConstantsCommon.PAR_OUT_CURSOR);
		
		System.out.println("listaDetalles : " + listaDetalles);
		System.out.println("size : " + listaDetalles.size());
		
		Integer idAlgas = Integer.parseInt(obtenerMensaje(ConstantsLaboratorio.IDENTIFICADOR_ALGAS));
		Integer idCianobacterias = Integer.parseInt(obtenerMensaje(ConstantsLaboratorio.IDENTIFICADOR_CIANOBACTERIAS));
		Integer idNematodes = Integer.parseInt(obtenerMensaje(ConstantsLaboratorio.IDENTIFICADOR_NEMATODES));
		Integer idOtros = Integer.parseInt(obtenerMensaje(ConstantsLaboratorio.IDENTIFICADOR_OTROS));

		Double totalOrg = 0D; 
		try {
			
		
		for (Object object : listaDetalles) {
			
			System.out.println("object : " + object);
			if(object instanceof List){
				List<?> detalle = (List<?>)object;
				Object llave = detalle.get(0);
				Object valor = detalle.get(1);
				Double valorDouble  = valor.toString().trim().isEmpty()? 0D: Double.parseDouble(valor.toString().trim());
					if(idAlgas.equals(llave)){						
						reporteAnalisisHidroDetalle.setAlgas(valorDouble);
						valorDouble = valorDouble*1000D;
					}else if (idCianobacterias.equals(llave)){						
						reporteAnalisisHidroDetalle.setCianobacterias(valorDouble);
						valorDouble = valorDouble*1000D;
					}else if (idNematodes.equals(llave)){						
						reporteAnalisisHidroDetalle.setNematodes(valorDouble);
					}else if (idOtros.equals(llave)){						
						reporteAnalisisHidroDetalle.setOtros(valorDouble);
					}
					totalOrg += valorDouble;
			}
		}
		totalOrg = (double) Math.round(totalOrg * 100);
		totalOrg = totalOrg/100;
		
		reporteAnalisisHidroDetalle.setTotal(totalOrg);
		System.out.println("puntoMuestreoooo : " + puntoMuestreo);
		reporteAnalisisHidroDetalle.setCodsub(puntoMuestreo);
		reporteAnalisisHidroDetalle.setFechaMuestreo(fechaAnalisis);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return reporteAnalisisHidroDetalle;
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#insertarReporteHidroBiologico(pe.com.sedapal.scr.core.beans.ReporteAnalisisHidroDetalle)
	 */
	@Override
	public void insertarReporteHidroBiologico(ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle) {

		paramsInput = new ArrayList<>();
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));

		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, OracleTypes.DATE));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERMUE, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERANA, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CORDET, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODSUB, OracleTypes.INTEGER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CANALG, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CIANOB, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NEMATO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ZOTROS, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TOTORG, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_ECAMIN, OracleTypes.INTEGER));
		
		paramsOutput = new ArrayList<>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO
				+ Constants.P_SEPARADOR + ConstantsLaboratorio.PRC_INSERT_REPORTEHIDROBIO, 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<>();

		inputs.put(ConstantsLaboratorio.PAR_USUCRE, reporteAnalisisHidroDetalle.getUsuarioCreacion());
		inputs.put(ConstantsLaboratorio.PAR_NOMPRG, reporteAnalisisHidroDetalle.getPrograma());

		inputs.put(ConstantsLaboratorio.PAR_FECMUE, Utils.getDateOracle(reporteAnalisisHidroDetalle.getFechaMuestreo()));
		inputs.put(ConstantsLaboratorio.PAR_PERMUE, reporteAnalisisHidroDetalle.getPersonaMuestra());
		inputs.put(ConstantsLaboratorio.PAR_PERANA, reporteAnalisisHidroDetalle.getAnalista());
		inputs.put(ConstantsLaboratorio.PAR_CORDET, reporteAnalisisHidroDetalle.getCorrelativo());
		inputs.put(ConstantsLaboratorio.PAR_CODIGO, 0);
		inputs.put(ConstantsLaboratorio.PAR_CODSUB, reporteAnalisisHidroDetalle.getCodsub());
		inputs.put(ConstantsLaboratorio.PAR_CANALG, reporteAnalisisHidroDetalle.getAlgas());
		inputs.put(ConstantsLaboratorio.PAR_CIANOB, reporteAnalisisHidroDetalle.getCianobacterias());
		inputs.put(ConstantsLaboratorio.PAR_NEMATO, reporteAnalisisHidroDetalle.getNematodes());
		inputs.put(ConstantsLaboratorio.PAR_ZOTROS, reporteAnalisisHidroDetalle.getOtros());
		inputs.put(ConstantsLaboratorio.PAR_TOTORG, reporteAnalisisHidroDetalle.getTotal());
		inputs.put(ConstantsLaboratorio.PAR_ECAMIN, reporteAnalisisHidroDetalle.getMinam());

		this.execSp.executeSp(inputs);
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#obtenerCorrelativoDetalleReporte()
	 */
	public Integer obtenerCorrelativoDetalleReporte() {

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO)
				.withProcedureName(ConstantsLaboratorio.PRC_CORREDET_REPORTEHIDRO)
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
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#obtenerDetallePorCorrelativo(java.lang.Integer)
	 */
	public ArrayList<ReporteAnalisisHidroDetalle> obtenerDetallePorCorrelativo(Integer idCorrelativo) {
		ArrayList<ReporteAnalisisHidroDetalle> ret = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_DETXCORREL)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_CORDET, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteAnalisisHidroDetalle>() {
									// D_FECREP,D_FECMUE,N_PERMUE,N_PERANA, N_CORDET,N_CODSUB,N_CANALG,N_CIANOB, N_NEMATO, N_ZOTROS, N_TOTORG, N_ECAMIN
									@Override
									public ReporteAnalisisHidroDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										ReporteAnalisisHidroDetalle reporteAnalisisHidroDetalle = new ReporteAnalisisHidroDetalle();
										reporteAnalisisHidroDetalle.setFechaReporte(rs.getString(1));
										reporteAnalisisHidroDetalle.setFechaMuestreoDate(rs.getTimestamp(2));
										reporteAnalisisHidroDetalle.setPersonaMuestra(rs.getInt(3));
										reporteAnalisisHidroDetalle.setAnalista(rs.getInt(4));
										reporteAnalisisHidroDetalle.setCorrelativo(rs.getInt(5));
										reporteAnalisisHidroDetalle.setCodsub(rs.getInt(6));
										reporteAnalisisHidroDetalle.setAlgas(rs.getDouble(7));
										reporteAnalisisHidroDetalle.setCianobacterias(rs.getDouble(8));
										reporteAnalisisHidroDetalle.setNematodes(rs.getDouble(9));
										reporteAnalisisHidroDetalle.setOtros(rs.getDouble(10));
										reporteAnalisisHidroDetalle.setTotal(rs.getDouble(11));
										reporteAnalisisHidroDetalle.setMinam(rs.getDouble(12));

										return reporteAnalisisHidroDetalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_CORDET, idCorrelativo);

		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList<ReporteAnalisisHidroDetalle>) results.get(Constants.PAR_OUT_CURSOR);

		return ret;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#eliminarReporteHidroPorCorrelativo(java.lang.Integer)
	 */
	public boolean eliminarReporteHidroPorCorrelativo(Integer idCorrelativo){
		boolean eliminado = true;
		try {
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CORDET, OracleTypes.INTEGER));
		
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO
					+ Constants.P_SEPARADOR + ConstantsLaboratorio.PRC_DELETE_REPORTEHIDRO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();

			inputs.put(ConstantsLaboratorio.PAR_CORDET, idCorrelativo);

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			
			eliminado = false;
		}
		return eliminado;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IAnalisisHibroBiologicoDao#obtenerAnalisisPorParametros(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public List<AnalisisHidroBiologico> obtenerAnalisisPorParametros(String fechaMuestra,Integer puntoMuestra, String tipoOrganismo) {
		List<AnalisisHidroBiologico> ret = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ANALISISHIDROBIO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ANALISISHIDROPAR)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECREP, Types.VARCHAR),
						new SqlParameter(ConstantsLaboratorio.PAR_PUNTO, Types.INTEGER),
						new SqlParameter(ConstantsLaboratorio.PAR_TIPORG, Types.VARCHAR),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<AnalisisHidroBiologico>() {
									 //D_FECANA,N_CODSUB, C_TIPORG, N_CODORG , C_DESCRI, N_CANTID
									@Override
									public AnalisisHidroBiologico mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										AnalisisHidroBiologico analisisHidroBiologico = new AnalisisHidroBiologico();
										analisisHidroBiologico.setFechaAnalisis(rs.getString(1));
										analisisHidroBiologico.setCodsub(rs.getInt(2));
										analisisHidroBiologico.setTipoOrganismo(rs.getInt(3));
										analisisHidroBiologico.setCodorg(rs.getInt(4));
										analisisHidroBiologico.setDescripcion(rs.getString(5));
										analisisHidroBiologico.setCantidad(rs.getString(6));

										return analisisHidroBiologico;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECREP, fechaMuestra);
		params.addValue(ConstantsLaboratorio.PAR_PUNTO, puntoMuestra);
		params.addValue(ConstantsLaboratorio.PAR_TIPORG, tipoOrganismo);

		Map<String, Object> results = caller.execute(params);
		ret = (List<AnalisisHidroBiologico>) results.get(Constants.PAR_OUT_CURSOR);

		return ret;
	}
	

	/**
	 * Obtener mensaje.
	 *
	 * @param messageProp the message prop
	 * @return the string
	 */
	public String obtenerMensaje(String messageProp){
		return messageSource.getMessage(messageProp, new Object[] {}, Locale.US);
	}

}
