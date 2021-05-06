/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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
import pe.com.sedapal.scr.core.beans.EnsayoCloro;
import pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle;
import pe.com.sedapal.scr.core.beans.ReporteEnsayoCloroResult;
import pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IEnsayoCloroDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.scr.core.util.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class EnsayoCloroDaoImpl.
 */
@Repository
public class EnsayoCloroDaoImpl  implements IEnsayoCloroDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#obtenerDatosEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEnsayoCloro(EnsayoCloro ensayoCloro, Paginacion paginacion) {
		List<?> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO).withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ENSAYOCLORO)
				.declareParameters(
						// parametros de bUsqueda
						// parametros de arquitectura
						new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, Types.VARCHAR),
						new SqlParameter(Constants.PAR_COLORDERBY, Types.INTEGER),
						new SqlParameter(Constants.PAR_COLORDERBYDIR, Types.VARCHAR),
						new SqlParameter(Constants.PAR_PAGDESDE, Types.INTEGER),
						new SqlParameter(Constants.PAR_CANTIDADPAG, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<List<?>>() {
									
									@Override
									public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
										List<Object> record = new ArrayList<>();
										record.add("");
										String fechaMuestreo = rs.getString(1); 
										if(fechaMuestreo!=null && fechaMuestreo.length() > 10){
											fechaMuestreo = fechaMuestreo.substring(0, 10);
										}
										record.add(fechaMuestreo); // "D_FECMUE"
										record.add(rs.getString(2)); // "C_HORMUE"
										record.add(rs.getString(3)); // "C_PLAMUE"
										record.add(rs.getString(4)); // "C_METMUE"
										record.add(rs.getString(5)); // "N_PERANA"
										record.add(rs.getInt(6)); // "N_CODIGO"
										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		String fechaMuestreo = ensayoCloro.getFechaMuestreo();
		if(fechaMuestreo == null){
			fechaMuestreo = "";
		}
		
		params.addValue(ConstantsLaboratorio.PAR_FECMUE, fechaMuestreo);
		params.addValue(Constants.PAR_COLORDERBY, paginacion.getColorderby());
		params.addValue(Constants.PAR_COLORDERBYDIR, paginacion.getColorderbydir());
		params.addValue(Constants.PAR_PAGDESDE, paginacion.getPagdesde());
		params.addValue(Constants.PAR_CANTIDADPAG, paginacion.getCantidadpag());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);
		lstRetorno = (List<?>) results.get(ConstantsCommon.PAR_OUT_CURSOR);

		Result result = new Result();
		result.setData(lstRetorno);
		result.setRecords((long) quantity);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#obtenerListEnsayoCloro(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EnsayoCloroDetalle> obtenerListEnsayoCloro(Integer idEnsayo) {

		List<EnsayoCloroDetalle> ret = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ENSAYOCLORODETA)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<EnsayoCloroDetalle>() {
									@Override
									public EnsayoCloroDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
										EnsayoCloroDetalle ensayoCloroDetalle = new EnsayoCloroDetalle(); 
										ensayoCloroDetalle.setIdDetalle(rs.getInt(1));
										ensayoCloroDetalle.setPuntoMuestreo(rs.getInt(2));
										ensayoCloroDetalle.setPh(rs.getDouble(3));
										ensayoCloroDetalle.setTemperatura(rs.getDouble(4));
										ensayoCloroDetalle.setFrasco(rs.getString(5));
										ensayoCloroDetalle.setVolumen(rs.getDouble(6));
										ensayoCloroDetalle.setDosis(rs.getDouble(7));
										ensayoCloroDetalle.setCl2Libre(rs.getDouble(8));
										ensayoCloroDetalle.setCl2Comb(rs.getDouble(9));
										ensayoCloroDetalle.setCl2Total(rs.getDouble(10));
										ensayoCloroDetalle.setCloroLibre(rs.getDouble(11));
										ensayoCloroDetalle.setCloroTotal(rs.getDouble(12));
										ensayoCloroDetalle.setDemandaCloro(rs.getDouble(13));
										ensayoCloroDetalle.setTipoDetalle(rs.getString(14));
										ensayoCloroDetalle.setSeleccionado(rs.getInt(16));
										
										return ensayoCloroDetalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_CODIGO, idEnsayo);
		Map<String, Object> results = caller.execute(params);
		ret = (List<EnsayoCloroDetalle>) results.get(Constants.PAR_OUT_CURSOR);
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#buscarEnsayoCloroXId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EnsayoCloro buscarEnsayoCloroXId(Integer idEnsayo) {

		List<EnsayoCloro> ret = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ENSAYOCLOROXID)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<EnsayoCloro>() {

									@Override
									public EnsayoCloro mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										EnsayoCloro ensayoCloro = new EnsayoCloro(); 
										ensayoCloro.setCodigo(rs.getInt(1));
										ensayoCloro.setFechaMuestreo(rs.getString(2));
										ensayoCloro.setHoraMuestreo(rs.getString(3));
										ensayoCloro.setPlanMuestreo(rs.getString(4));
										ensayoCloro.setFechaReporte(rs.getString(5));
										ensayoCloro.setFechaInicioEnsayo(rs.getString(6));
										ensayoCloro.setHoraInicioEnsayo(rs.getString(7));
										ensayoCloro.setMetodoMuestreo(rs.getString(8));
										ensayoCloro.setVolumenMuestra(rs.getString(9));
										ensayoCloro.setTiempoContacto(rs.getString(10));
										ensayoCloro.setAnalista(rs.getInt(11));
										ensayoCloro.setTurno(rs.getString(12));
										ensayoCloro.setFechaRecepcion(rs.getString(14));
										ensayoCloro.setHoraRecepcion(rs.getString(15));
										ensayoCloro.setMuestreador(rs.getInt(16));
										
										ensayoCloro.setSodio1(rs.getString(17));
										ensayoCloro.setSodio2(rs.getString(18));
										ensayoCloro.setSodio3(rs.getString(19));
										ensayoCloro.setSodio4(rs.getString(20));
										ensayoCloro.setCloro1(rs.getString(21));
										ensayoCloro.setCloro2(rs.getString(22));
										ensayoCloro.setCloro3(rs.getString(23));
										ensayoCloro.setCloro4(rs.getString(24));
										ensayoCloro.setBicromato1(rs.getString(25));
										ensayoCloro.setBicromato2(rs.getString(26));
										ensayoCloro.setBicromato3(rs.getString(27));
										ensayoCloro.setSolucion1(rs.getString(28));
										ensayoCloro.setSolucion2(rs.getString(29));
										ensayoCloro.setSolucion3(rs.getString(30));
										ensayoCloro.setObservacion(rs.getString(31));
										return ensayoCloro;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_CODIGO, idEnsayo);
		Map<String, Object> results = caller.execute(params);
		ret = (List<EnsayoCloro>) results.get(Constants.PAR_OUT_CURSOR);
		if(ret == null || ret.isEmpty()){
			return new EnsayoCloro();
		}
		return ret.get(0);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#guardarEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int guardarEnsayoCloro(EnsayoCloro ensayoCloro) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));

			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_HORMUE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PLAMUE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FECREP, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FEINEN, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_HEINEN, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_METMUE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VOLMUE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TCONTA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PERANA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TURNO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_STATUS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_FRECEP, OracleTypes.DATE));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_HRECEP, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PRECEP, OracleTypes.INTEGER));
			
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SODIO1, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SODIO2, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SODIO3, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SODIO4, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORO1, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORO2, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORO3, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORO4, OracleTypes.VARCHAR));
			
			//ATRIBUTOS NUEVOS
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_BICRO1, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_BICRO1, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_BICRO1, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SOLUC1, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SOLUC2, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SOLUC3, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_OBSERV, OracleTypes.VARCHAR));
			

//		   --parametros
			paramsOutput = new ArrayList<>();
			paramsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO + Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INSERT_ENSAYOCLORO, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			java.sql.Date fechaMuestreo = ensayoCloro.getFechaMuestreo()!=null? Utils.getDateOracle(ensayoCloro.getFechaMuestreo()):new java.sql.Date(new Date().getTime());
			Integer codigo = ensayoCloro.getCodigo()==null ? 0 : ensayoCloro.getCodigo();
					
			inputs.put(ConstantsLaboratorio.PAR_USUCRE, ensayoCloro.getUsuarioCreacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, ensayoCloro.getPrograma());
			inputs.put(ConstantsLaboratorio.PAR_CODIGO, codigo);
			inputs.put(ConstantsLaboratorio.PAR_FECMUE, fechaMuestreo);
			inputs.put(ConstantsLaboratorio.PAR_HORMUE, ensayoCloro.getHoraMuestreo()!=null? ensayoCloro.getHoraMuestreo():"");
			inputs.put(ConstantsLaboratorio.PAR_PLAMUE, ensayoCloro.getPlanMuestreo()!=null? ensayoCloro.getPlanMuestreo():"");
		
			inputs.put(ConstantsLaboratorio.PAR_FECREP, Utils.getDateOracle(ensayoCloro.getFechaReporte()));
			inputs.put(ConstantsLaboratorio.PAR_FEINEN, Utils.getDateOracle(ensayoCloro.getFechaInicioEnsayo()));
			
			
			inputs.put(ConstantsLaboratorio.PAR_HEINEN, ensayoCloro.getHoraInicioEnsayo());
			inputs.put(ConstantsLaboratorio.PAR_METMUE, ensayoCloro.getMetodoMuestreo());
			inputs.put(ConstantsLaboratorio.PAR_VOLMUE, ensayoCloro.getVolumenMuestra());
			inputs.put(ConstantsLaboratorio.PAR_TCONTA, ensayoCloro.getTiempoContacto());
			inputs.put(ConstantsLaboratorio.PAR_PERANA, ensayoCloro.getAnalista());
			inputs.put(ConstantsLaboratorio.PAR_TURNO,  ensayoCloro.getTurno()!=null? ensayoCloro.getTurno().trim() : ensayoCloro.getTurno());
			inputs.put(ConstantsLaboratorio.PAR_STATUS, ConstantsLaboratorio.ACTIVO);
			inputs.put(ConstantsLaboratorio.PAR_FRECEP, Utils.getDateOracle(ensayoCloro.getFechaRecepcion()));
			inputs.put(ConstantsLaboratorio.PAR_HRECEP, ensayoCloro.getHoraRecepcion());
			inputs.put(ConstantsLaboratorio.PAR_PRECEP, ensayoCloro.getMuestreador());
			
			inputs.put(ConstantsLaboratorio.PAR_SODIO1, ensayoCloro.getSodio1());
			inputs.put(ConstantsLaboratorio.PAR_SODIO2, ensayoCloro.getSodio2());
			inputs.put(ConstantsLaboratorio.PAR_SODIO3, ensayoCloro.getSodio3());
			inputs.put(ConstantsLaboratorio.PAR_SODIO4, ensayoCloro.getSodio4());
			
			inputs.put(ConstantsLaboratorio.PAR_CLORO1, ensayoCloro.getCloro1());
			inputs.put(ConstantsLaboratorio.PAR_CLORO2, ensayoCloro.getCloro2());
			inputs.put(ConstantsLaboratorio.PAR_CLORO3, ensayoCloro.getCloro3());
			inputs.put(ConstantsLaboratorio.PAR_CLORO4, ensayoCloro.getCloro4());
			
			inputs.put(ConstantsLaboratorio.PAR_BICRO1, ensayoCloro.getBicromato1());
			inputs.put(ConstantsLaboratorio.PAR_BICRO2, ensayoCloro.getBicromato2());
			inputs.put(ConstantsLaboratorio.PAR_BICRO3, ensayoCloro.getBicromato3());
			inputs.put(ConstantsLaboratorio.PAR_SOLUC1, ensayoCloro.getSolucion1());
			inputs.put(ConstantsLaboratorio.PAR_SOLUC2, ensayoCloro.getSolucion2());
			inputs.put(ConstantsLaboratorio.PAR_SOLUC3, ensayoCloro.getSolucion3());
			inputs.put(ConstantsLaboratorio.PAR_OBSERV, ensayoCloro.getObservacion() !=null? ensayoCloro.getObservacion().trim() : ensayoCloro.getObservacion());
			
			Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
			return outputs.get(Constants.PAR_OUT_RETURN);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#guardarEnsayoCloroDetalle(pe.com.sedapal.scr.core.beans.EnsayoCloroDetalle)
	 */
	@Override
	public void guardarEnsayoCloroDetalle(EnsayoCloroDetalle ensayoCloroDetalle) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
			paramsInput = new ArrayList<>();
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUCRE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));

			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODETA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PUNMUE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_PH, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TEMPER, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NUMFRA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VOLUME, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DOSIS, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLLIBR, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLCOMB, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLTOTA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORLI, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORTO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CLORDE, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_TIPDET, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_STATUS, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_SELECT, OracleTypes.INTEGER));
			
//		   --parametros
			paramsOutput = new ArrayList<>();

			this.execSp = new ExecuteProcedure(template.getDataSource(), ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO + Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INSERT_ENSAYOCLORODET, 
					environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
			inputs = new HashMap<>();
			
			inputs.put(ConstantsLaboratorio.PAR_USUCRE, ensayoCloroDetalle.getUsuarioCreacion());
			inputs.put(ConstantsLaboratorio.PAR_NOMPRG, ensayoCloroDetalle.getPrograma());

			inputs.put(ConstantsLaboratorio.PAR_CODIGO, ensayoCloroDetalle.getIdCabecera()!=null? ensayoCloroDetalle.getIdCabecera(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CODETA, ensayoCloroDetalle.getIdDetalle()!=null? ensayoCloroDetalle.getIdDetalle(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_PUNMUE, ensayoCloroDetalle.getPuntoMuestreo()!=null? ensayoCloroDetalle.getPuntoMuestreo(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_PH, ensayoCloroDetalle.getPh()!=null? ensayoCloroDetalle.getPh(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_TEMPER, ensayoCloroDetalle.getTemperatura()!=null? ensayoCloroDetalle.getTemperatura(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_NUMFRA, ensayoCloroDetalle.getFrasco()!=null? ensayoCloroDetalle.getFrasco(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_VOLUME, ensayoCloroDetalle.getVolumen()!=null? ensayoCloroDetalle.getVolumen(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_DOSIS, ensayoCloroDetalle.getDosis()!=null? ensayoCloroDetalle.getDosis(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLLIBR, ensayoCloroDetalle.getCl2Libre()!=null? ensayoCloroDetalle.getCl2Libre(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLCOMB, ensayoCloroDetalle.getCl2Comb()!=null? ensayoCloroDetalle.getCl2Comb(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLTOTA, ensayoCloroDetalle.getCl2Total()!=null? ensayoCloroDetalle.getCl2Total(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLORLI, ensayoCloroDetalle.getCloroLibre()!=null? ensayoCloroDetalle.getCloroLibre(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLORTO, ensayoCloroDetalle.getCloroTotal()!=null? ensayoCloroDetalle.getCloroTotal(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_CLORDE, ensayoCloroDetalle.getDemandaCloro()!=null? ensayoCloroDetalle.getDemandaCloro(): 0D);
			inputs.put(ConstantsLaboratorio.PAR_TIPDET, ensayoCloroDetalle.getTipoDetalle());
			inputs.put(ConstantsLaboratorio.PAR_STATUS, ConstantsLaboratorio.ACTIVO);
			inputs.put(ConstantsLaboratorio.PAR_SELECT, ensayoCloroDetalle.getSeleccionado());
		
			this.execSp.executeSp(inputs);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#inactivarEnsayoCloro(pe.com.sedapal.scr.core.beans.EnsayoCloro)
	 */
	@Override
	public void inactivarEnsayoCloro(EnsayoCloro ensayoCloro) {
		
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		paramsInput = new ArrayList<>();
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_USUMOD, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NOMPRG, OracleTypes.VARCHAR));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CODIGO, OracleTypes.INTEGER));
		paramsOutput=new ArrayList<>();

		this.execSp=new ExecuteProcedure(template.getDataSource(),ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO+Constants.P_SEPARADOR+ConstantsLaboratorio.PRC_INACTIVAR_ENSAYOCLORO, 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA),paramsInput,paramsOutput);
		inputs=new HashMap<>();
		inputs.put(ConstantsLaboratorio.PAR_USUMOD, ensayoCloro.getUsuarioCreacion());
		inputs.put(ConstantsLaboratorio.PAR_NOMPRG, ensayoCloro.getPrograma());
		inputs.put(ConstantsLaboratorio.PAR_CODIGO, ensayoCloro.getCodigo());
	
		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#buscarEnsayoCloroFechaHora(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EnsayoCloro buscarEnsayoCloroFechaHora(String fechaMuetreo,String horaMuestreo){
		List<EnsayoCloro> ret = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_ENSAYOCLOROXFECHA)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, Types.VARCHAR),
						new SqlParameter(ConstantsLaboratorio.PAR_HORMUE, Types.VARCHAR),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<EnsayoCloro>() {

									@Override
									public EnsayoCloro mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										EnsayoCloro ensayoCloro = new EnsayoCloro(); 
										ensayoCloro.setCodigo(rs.getInt(1));
										ensayoCloro.setFechaMuestreo(rs.getString(2));
										ensayoCloro.setHoraMuestreo(rs.getString(3));
										ensayoCloro.setPlanMuestreo(rs.getString(4));
										ensayoCloro.setFechaReporte(rs.getString(5));
										ensayoCloro.setFechaInicioEnsayo(rs.getString(6));
										ensayoCloro.setHoraInicioEnsayo(rs.getString(7));
										ensayoCloro.setMetodoMuestreo(rs.getString(8));
										ensayoCloro.setVolumenMuestra(rs.getString(9));
										ensayoCloro.setTiempoContacto(rs.getString(10));
										ensayoCloro.setAnalista(rs.getInt(11));
										ensayoCloro.setTurno(rs.getString(12));
										ensayoCloro.setFechaRecepcion(rs.getString(14));
										ensayoCloro.setHoraRecepcion(rs.getString(15));
										ensayoCloro.setMuestreador(rs.getInt(16));
										
										ensayoCloro.setSodio1(rs.getString(17));
										ensayoCloro.setSodio2(rs.getString(18));
										ensayoCloro.setSodio3(rs.getString(19));
										ensayoCloro.setSodio4(rs.getString(20));
										ensayoCloro.setCloro1(rs.getString(21));
										ensayoCloro.setCloro2(rs.getString(22));
										ensayoCloro.setCloro3(rs.getString(23));
										ensayoCloro.setCloro4(rs.getString(24));
										ensayoCloro.setObservacion(rs.getString(25));
										return ensayoCloro;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECMUE, fechaMuetreo);
		params.addValue(ConstantsLaboratorio.PAR_HORMUE, horaMuestreo);
		Map<String, Object> results = caller.execute(params);
		ret = (List<EnsayoCloro>) results.get(Constants.PAR_OUT_CURSOR);
		if(ret == null || ret.isEmpty()){
			return new EnsayoCloro();
		}
		return ret.get(0);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#obtenerReporteEnsayoCloro(java.lang.Integer, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public EnsayoCloroDetalle obtenerReporteEnsayoCloro(Integer puntoMuestro, String fechaMuestreo){
		List<EnsayoCloroDetalle> ret = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORTENSAYO)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_PUNMUE, Types.INTEGER),
						new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, Types.VARCHAR),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<EnsayoCloroDetalle>() {

									@Override
									public EnsayoCloroDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										EnsayoCloroDetalle ensayoCloroDetalle = new EnsayoCloroDetalle(); 
										ensayoCloroDetalle.setPuntoMuestreo(rs.getInt(1));
										ensayoCloroDetalle.setTemperatura(rs.getDouble(2));
										ensayoCloroDetalle.setPh(rs.getDouble(3));
										ensayoCloroDetalle.setDosis(rs.getDouble(4));
										ensayoCloroDetalle.setCloroLibre(rs.getDouble(5));
										ensayoCloroDetalle.setCloroTotal(rs.getDouble(6));
										ensayoCloroDetalle.setDemandaCloro(rs.getDouble(7));
										ensayoCloroDetalle.setFechaMuestreo(rs.getString(8));
										ensayoCloroDetalle.setHoraMuestreo(rs.getString(9));
										ensayoCloroDetalle.setPlanMuestreo(rs.getString(10));
										ensayoCloroDetalle.setMetodoMuestreo(rs.getString(11));
										ensayoCloroDetalle.setAnalista(rs.getString(12));
										ensayoCloroDetalle.setMuestreador(rs.getString(13));
										
//										_PUNMUE,N_TEMPER,N_PH,N_DOSIS,N_CLORLI,N_CLORTO,N_CLORDE , D_FECMUE, C_HORMUE, C_PLAMUE,C_METMUE,N_PERAN
										return ensayoCloroDetalle;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_PUNMUE, puntoMuestro);
		params.addValue(ConstantsLaboratorio.PAR_FECMUE, fechaMuestreo);
		Map<String, Object> results = caller.execute(params);
		ret = (List<EnsayoCloroDetalle>) results.get(Constants.PAR_OUT_CURSOR);
		if(ret == null || ret.isEmpty()){
			return new EnsayoCloroDetalle();
		}
		return ret.get(0);
	}
	
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#obtenerReporteEnsayoCloroBandeja(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteEnsayoCloroResult> obtenerReporteEnsayoCloroBandeja(String fechaMes){
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO)
				.withProcedureName(ConstantsLaboratorio.PRC_BUSCA_REPORTENSAYOXMES)
				.declareParameters(
						// parametros de busqueda
						new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, Types.VARCHAR),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), new SqlOutParameter(
								ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<ReporteEnsayoCloroResult>() {
									@Override
									public ReporteEnsayoCloroResult mapRow(ResultSet rs, int rowNum) throws SQLException {
										
										ReporteEnsayoCloroResult result = new ReporteEnsayoCloroResult(); 
										result.setFechaMuestreo(rs.getString(1));
										result.setPuntoMuestra(rs.getInt(2));
										result.setCorrelativo(rs.getInt(3));
										return result;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_FECMUE, fechaMes);
		Map<String, Object> results = caller.execute(params);
		List<ReporteEnsayoCloroResult> resultados = (List<ReporteEnsayoCloroResult>) results.get(Constants.PAR_OUT_CURSOR);
//		String fecha;
//		for (ReporteEnsayoCloroResult resul : resultados) {
//			fecha = resultados.
//		}
//		
//		
//		if(ret == null || ret.isEmpty()){
//			return new EnsayoCloroDetalle();
//		}
		return resultados;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IEnsayoCloroDao#obtenerEnsayosParaDiaMuestreos(pe.com.sedapal.scr.core.beans.ValidacionEnsayoCloroBean)
	 */
	@Override
	public Integer obtenerEnsayosParaDiaMuestreos(ValidacionEnsayoCloroBean validacionBean) {
		Integer cantidad = 0;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PCK_ALC_ENSAYOCLORO).withProcedureName(ConstantsLaboratorio.PRC_VALIDAUNICIDAD_XDIA)
				.declareParameters(
						// parametros de bUsqueda
						// parametros de arquitectura
						new SqlParameter(ConstantsLaboratorio.PAR_FECMUE, Types.VARCHAR),
						new SqlParameter(Constants.PAR_CODIGO, Types.INTEGER),
						new SqlParameter(Constants.PAR_PUNTO_MUESTREO1, Types.INTEGER),
						new SqlParameter(Constants.PAR_PUNTO_MUESTREO2, Types.INTEGER),
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(ConstantsLaboratorio.PAR_FECMUE, validacionBean.getFechaMuestreo());
		params.addValue(Constants.PAR_CODIGO, validacionBean.getCodigoEnsayo());
		params.addValue(Constants.PAR_PUNTO_MUESTREO1, validacionBean.getCodigoMuestreo1());
		params.addValue(Constants.PAR_PUNTO_MUESTREO2, validacionBean.getCodigoMuestreo2());

		Map<String, Object> results = caller.execute(params);
		int quantity = (int) results.get(Constants.PAR_OUTQUANTITY);

		cantidad = quantity;

		return cantidad;
	}

}
