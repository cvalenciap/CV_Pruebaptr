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
import pe.com.sedapal.scr.core.beans.PhmetroDigitalBean;
import pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

// TODO: Auto-generated Javadoc
/**
 * The Class PhmetroDigitalDaoImpl.
 */
@Repository
public class PhmetroDigitalDaoImpl implements IPhmetroDigitalDao{

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
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(PhmetroDigitalBean phmetroDigitalBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL).withProcedureName(ConstantsLaboratorio.SP_SEARCH_PHMETRO_DIGITAL)
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
										record.add(rs.getString(1)); // "c_nomequ"
										record.add(rs.getString(2)); // "c_codequ"
										record.add(rs.getString(3)); // "c_marequ"
										record.add(rs.getString(4)); // "c_modequ"
										record.add(rs.getString(5)); // "c_serieq"
										record.add(rs.getInt(6)); // "n_cof292"

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, phmetroDigitalBean.getStrCodEquipo());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#obtenerPhmetroDigital(java.lang.Integer)
	 */
	@Override
	public PhmetroDigitalBean obtenerPhmetroDigital(Integer id) throws Exception {
		ArrayList ret = null;
		PhmetroDigitalBean result = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL).withProcedureName(ConstantsLaboratorio.PRC_GET_PHMETRO_DIGITAL)
				.declareParameters(
						// parametros de búsqueda
						new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, Types.INTEGER),		
						new SqlOutParameter(Constants.PAR_OUTQUANTITY, Types.INTEGER), 
						new SqlOutParameter(ConstantsCommon.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PhmetroDigitalBean>() {
									
									@Override
									public PhmetroDigitalBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PhmetroDigitalBean PhmetroDigitalBean = new PhmetroDigitalBean();
										PhmetroDigitalBean.setStrNomEquipo(rs.getString(1)); // ""
										PhmetroDigitalBean.setStrCodEquipo(rs.getString(2)); // ""
										PhmetroDigitalBean.setStrCodEquipoHide(rs.getString(2)); // ""										
										PhmetroDigitalBean.setStrMarca(rs.getString(3)); // ""
										PhmetroDigitalBean.setStrModelo(rs.getString(4)); // ""
										PhmetroDigitalBean.setStrNroSerie(rs.getString(5));
										
										PhmetroDigitalBean.setStrCalNombre1(rs.getString(6));
										PhmetroDigitalBean.setStrCalNombre2(rs.getString(7));
										PhmetroDigitalBean.setStrCalNombre3(rs.getString(8));
										PhmetroDigitalBean.setStrCalLote1(rs.getString(9));
										PhmetroDigitalBean.setStrCalLote2(rs.getString(10));
										PhmetroDigitalBean.setStrCalLote3(rs.getString(11));
										PhmetroDigitalBean.setStrCalCodigo1(rs.getString(12));
										PhmetroDigitalBean.setStrCalCodigo2(rs.getString(13));
										PhmetroDigitalBean.setStrCalCodigo3(rs.getString(14));
										PhmetroDigitalBean.setStrCalCaducidad1(rs.getString(15));
										PhmetroDigitalBean.setStrCalCaducidad2(rs.getString(16));
										PhmetroDigitalBean.setStrCalCaducidad3(rs.getString(17));
										PhmetroDigitalBean.setStrCalMarca1(rs.getString(18));
										PhmetroDigitalBean.setStrCalMarca2(rs.getString(19));
										PhmetroDigitalBean.setStrCalMarca3(rs.getString(20));
										PhmetroDigitalBean.setStrCalEspecificacion1(rs.getString(21));
										PhmetroDigitalBean.setStrCalEspecificacion2(rs.getString(22));
										PhmetroDigitalBean.setStrCalEspecificacion3(rs.getString(23));
										
										PhmetroDigitalBean.setStrVerNombre1(rs.getString(24));
										PhmetroDigitalBean.setStrVerNombre2(rs.getString(25));
										PhmetroDigitalBean.setStrVerNombre3(rs.getString(26));
										PhmetroDigitalBean.setStrVerLote1(rs.getString(27));
										PhmetroDigitalBean.setStrVerLote2(rs.getString(28));
										PhmetroDigitalBean.setStrVerLote3(rs.getString(29));
										PhmetroDigitalBean.setStrVerCodigo1(rs.getString(30));
										PhmetroDigitalBean.setStrVerCodigo2(rs.getString(31));
										PhmetroDigitalBean.setStrVerCodigo3(rs.getString(32));
										PhmetroDigitalBean.setStrVerCaducidad1(rs.getString(33));
										PhmetroDigitalBean.setStrVerCaducidad2(rs.getString(34));
										PhmetroDigitalBean.setStrVerCaducidad3(rs.getString(35));
										PhmetroDigitalBean.setStrVerMarca1(rs.getString(36));
										PhmetroDigitalBean.setStrVerMarca2(rs.getString(37));
										PhmetroDigitalBean.setStrVerMarca3(rs.getString(38));
										PhmetroDigitalBean.setStrVerEspecificacion1(rs.getString(39));
										PhmetroDigitalBean.setStrVerEspecificacion2(rs.getString(40));
										PhmetroDigitalBean.setStrVerEspecificacion3(rs.getString(41));
										PhmetroDigitalBean.setIntEstado(rs.getInt(42));			
										PhmetroDigitalBean.setIntCodigo(rs.getInt(43));	
										PhmetroDigitalBean.setStrCalPendiente1(rs.getString(44));	
										PhmetroDigitalBean.setStrCalPendiente2(rs.getString(45));	
										PhmetroDigitalBean.setStrCalPendiente3(rs.getString(46));	
										PhmetroDigitalBean.setStrCalPendiente4(rs.getString(47));	

										return PhmetroDigitalBean;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_EQUIPO, id);
		
		Map<String, Object> results = caller.execute(params);
		ret = (ArrayList) results.get(Constants.PAR_OUT_CURSOR);
		
		result = (PhmetroDigitalBean) ret.get(0);

		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#grabarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public int grabarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMARCA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMODELO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNRO_SERIE, OracleTypes.VARCHAR));			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE4, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION3, OracleTypes.VARCHAR));
		
		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_PHMETRO_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, phmetroDigitalBean.getStrCodEquipo()!=null?phmetroDigitalBean.getStrCodEquipo():"");		
		inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, phmetroDigitalBean.getStrNomEquipo()!=null?phmetroDigitalBean.getStrNomEquipo().trim():"");
		inputs.put(ConstantsLaboratorio.PAR_CMARCA, phmetroDigitalBean.getStrMarca()!=null?phmetroDigitalBean.getStrMarca():"");	
		inputs.put(ConstantsLaboratorio.PAR_CMODELO, phmetroDigitalBean.getStrModelo()!=null?phmetroDigitalBean.getStrModelo():"");
		inputs.put(ConstantsLaboratorio.PAR_CNRO_SERIE, phmetroDigitalBean.getStrNroSerie()!=null?phmetroDigitalBean.getStrNroSerie():"");		
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE1, phmetroDigitalBean.getStrCalNombre1()!=null?phmetroDigitalBean.getStrCalNombre1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE2, phmetroDigitalBean.getStrCalNombre2()!=null?phmetroDigitalBean.getStrCalNombre2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE3, phmetroDigitalBean.getStrCalNombre3()!=null?phmetroDigitalBean.getStrCalNombre3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE1, phmetroDigitalBean.getStrCalLote1()!=null?phmetroDigitalBean.getStrCalLote1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE2, phmetroDigitalBean.getStrCalLote2()!=null?phmetroDigitalBean.getStrCalLote2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE3, phmetroDigitalBean.getStrCalLote3()!=null?phmetroDigitalBean.getStrCalLote3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO1, phmetroDigitalBean.getStrCalCodigo1()!=null?phmetroDigitalBean.getStrCalCodigo1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO2, phmetroDigitalBean.getStrCalCodigo2()!=null?phmetroDigitalBean.getStrCalCodigo2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO3, phmetroDigitalBean.getStrCalCodigo3()!=null?phmetroDigitalBean.getStrCalCodigo3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD1, phmetroDigitalBean.getStrCalCaducidad1()!=null?phmetroDigitalBean.getStrCalCaducidad1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD2, phmetroDigitalBean.getStrCalCaducidad2()!=null?phmetroDigitalBean.getStrCalCaducidad2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD3, phmetroDigitalBean.getStrCalCaducidad3()!=null?phmetroDigitalBean.getStrCalCaducidad3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA1, phmetroDigitalBean.getStrCalMarca1()!=null?phmetroDigitalBean.getStrCalMarca1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA2, phmetroDigitalBean.getStrCalMarca2()!=null?phmetroDigitalBean.getStrCalMarca2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA3, phmetroDigitalBean.getStrCalMarca3()!=null?phmetroDigitalBean.getStrCalMarca3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION1, phmetroDigitalBean.getStrCalEspecificacion1()!=null?phmetroDigitalBean.getStrCalEspecificacion1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION2, phmetroDigitalBean.getStrCalEspecificacion2()!=null?phmetroDigitalBean.getStrCalEspecificacion2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION3, phmetroDigitalBean.getStrCalEspecificacion3()!=null?phmetroDigitalBean.getStrCalEspecificacion3():"");	
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE1, phmetroDigitalBean.getStrCalPendiente1()!=null?phmetroDigitalBean.getStrCalPendiente1():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE2, phmetroDigitalBean.getStrCalPendiente2()!=null?phmetroDigitalBean.getStrCalPendiente2():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE3, phmetroDigitalBean.getStrCalPendiente3()!=null?phmetroDigitalBean.getStrCalPendiente3():"");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE4, phmetroDigitalBean.getStrCalPendiente4()!=null?phmetroDigitalBean.getStrCalPendiente4():"");		
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE1, phmetroDigitalBean.getStrVerNombre1()!=null?phmetroDigitalBean.getStrVerNombre1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE2, phmetroDigitalBean.getStrVerNombre2()!=null?phmetroDigitalBean.getStrVerNombre2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE3, phmetroDigitalBean.getStrVerNombre3()!=null?phmetroDigitalBean.getStrVerNombre3():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE1, phmetroDigitalBean.getStrVerLote1()!=null?phmetroDigitalBean.getStrVerLote1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE2, phmetroDigitalBean.getStrVerLote2()!=null?phmetroDigitalBean.getStrVerLote2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE3, phmetroDigitalBean.getStrVerLote3()!=null?phmetroDigitalBean.getStrVerLote3():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO1, phmetroDigitalBean.getStrVerCodigo1()!=null?phmetroDigitalBean.getStrVerCodigo1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO2, phmetroDigitalBean.getStrVerCodigo2()!=null?phmetroDigitalBean.getStrVerCodigo2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO3, phmetroDigitalBean.getStrVerCodigo3()!=null?phmetroDigitalBean.getStrVerCodigo3():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD1, phmetroDigitalBean.getStrVerCaducidad1()!=null?phmetroDigitalBean.getStrVerCaducidad1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD2, phmetroDigitalBean.getStrVerCaducidad2()!=null?phmetroDigitalBean.getStrVerCaducidad2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD3, phmetroDigitalBean.getStrVerCaducidad3()!=null?phmetroDigitalBean.getStrVerCaducidad3():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA1, phmetroDigitalBean.getStrVerMarca1()!=null?phmetroDigitalBean.getStrVerMarca1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA2, phmetroDigitalBean.getStrVerMarca2()!=null?phmetroDigitalBean.getStrVerMarca2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA3, phmetroDigitalBean.getStrVerMarca3()!=null?phmetroDigitalBean.getStrVerMarca3():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION1, phmetroDigitalBean.getStrVerEspecificacion1()!=null?phmetroDigitalBean.getStrVerEspecificacion1():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION2, phmetroDigitalBean.getStrVerEspecificacion2()!=null?phmetroDigitalBean.getStrVerEspecificacion2():"");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION3, phmetroDigitalBean.getStrVerEspecificacion3()!=null?phmetroDigitalBean.getStrVerEspecificacion3():"");

		inputs.put(ConstantsLaboratorio.PAR_VESTADO, phmetroDigitalBean.getIntEstado());			

		inputs.put(Constants.PAR_CUSUARIO_CREACION, phmetroDigitalBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, phmetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, phmetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#actualizarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public void actualizarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;

		lstParamsInput = new ArrayList<SqlParameter>();		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMARCA, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CMODELO, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CNRO_SERIE, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_NOMBRE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_LOTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CODIGO3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_MARCA3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE4, OracleTypes.VARCHAR));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_NOMBRE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_LOTE3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CODIGO3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_MARCA3, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION1, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION2, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION3, OracleTypes.VARCHAR));
		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_UPDATE_PHMETRO_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		inputs = new HashMap<String, Object>();		
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, phmetroDigitalBean.getIntCodigo());	
		inputs.put(ConstantsLaboratorio.PAR_NCODIGO_EQUIPO, phmetroDigitalBean.getStrCodEquipo()!=null?phmetroDigitalBean.getStrCodEquipo():" ");		
		inputs.put(ConstantsLaboratorio.PAR_CNOMBRE_EQUIPO, phmetroDigitalBean.getStrNomEquipo()!=null?phmetroDigitalBean.getStrNomEquipo():" ");
		inputs.put(ConstantsLaboratorio.PAR_CMARCA, phmetroDigitalBean.getStrMarca()!=null?phmetroDigitalBean.getStrMarca():" ");	
		inputs.put(ConstantsLaboratorio.PAR_CMODELO, phmetroDigitalBean.getStrModelo()!=null?phmetroDigitalBean.getStrModelo():" ");
		inputs.put(ConstantsLaboratorio.PAR_CNRO_SERIE, phmetroDigitalBean.getStrNroSerie()!=null?phmetroDigitalBean.getStrNroSerie():" ");		
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE1, phmetroDigitalBean.getStrCalNombre1()!=null?phmetroDigitalBean.getStrCalNombre1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE2, phmetroDigitalBean.getStrCalNombre2()!=null?phmetroDigitalBean.getStrCalNombre2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_NOMBRE3, phmetroDigitalBean.getStrCalNombre3()!=null?phmetroDigitalBean.getStrCalNombre3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE1, phmetroDigitalBean.getStrCalLote1()!=null?phmetroDigitalBean.getStrCalLote1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE2, phmetroDigitalBean.getStrCalLote2()!=null?phmetroDigitalBean.getStrCalLote2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_LOTE3, phmetroDigitalBean.getStrCalLote3()!=null?phmetroDigitalBean.getStrCalLote3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO1, phmetroDigitalBean.getStrCalCodigo1()!=null?phmetroDigitalBean.getStrCalCodigo1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO2, phmetroDigitalBean.getStrCalCodigo2()!=null?phmetroDigitalBean.getStrCalCodigo2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CODIGO3, phmetroDigitalBean.getStrCalCodigo3()!=null?phmetroDigitalBean.getStrCalCodigo3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD1, phmetroDigitalBean.getStrCalCaducidad1()!=null?phmetroDigitalBean.getStrCalCaducidad1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD2, phmetroDigitalBean.getStrCalCaducidad2()!=null?phmetroDigitalBean.getStrCalCaducidad2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_CADUCIDAD3, phmetroDigitalBean.getStrCalCaducidad3()!=null?phmetroDigitalBean.getStrCalCaducidad3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA1, phmetroDigitalBean.getStrCalMarca1()!=null?phmetroDigitalBean.getStrCalMarca1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA2, phmetroDigitalBean.getStrCalMarca2()!=null?phmetroDigitalBean.getStrCalMarca2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_MARCA3, phmetroDigitalBean.getStrCalMarca3()!=null?phmetroDigitalBean.getStrCalMarca3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION1, phmetroDigitalBean.getStrCalEspecificacion1()!=null?phmetroDigitalBean.getStrCalEspecificacion1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION2, phmetroDigitalBean.getStrCalEspecificacion2()!=null?phmetroDigitalBean.getStrCalEspecificacion2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_ESPECIFICACION3, phmetroDigitalBean.getStrCalEspecificacion3()!=null?phmetroDigitalBean.getStrCalEspecificacion3():" ");	
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE1, phmetroDigitalBean.getStrCalPendiente1()!=null?phmetroDigitalBean.getStrCalPendiente1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE2, phmetroDigitalBean.getStrCalPendiente2()!=null?phmetroDigitalBean.getStrCalPendiente2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE3, phmetroDigitalBean.getStrCalPendiente3()!=null?phmetroDigitalBean.getStrCalPendiente3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CCALIF_PENDIENTE4, phmetroDigitalBean.getStrCalPendiente4()!=null?phmetroDigitalBean.getStrCalPendiente4():" ");		
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE1, phmetroDigitalBean.getStrVerNombre1()!=null?phmetroDigitalBean.getStrVerNombre1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE2, phmetroDigitalBean.getStrVerNombre2()!=null?phmetroDigitalBean.getStrVerNombre2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_NOMBRE3, phmetroDigitalBean.getStrVerNombre3()!=null?phmetroDigitalBean.getStrVerNombre3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE1, phmetroDigitalBean.getStrVerLote1()!=null?phmetroDigitalBean.getStrVerLote1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE2, phmetroDigitalBean.getStrVerLote2()!=null?phmetroDigitalBean.getStrVerLote2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_LOTE3, phmetroDigitalBean.getStrVerLote3()!=null?phmetroDigitalBean.getStrVerLote3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO1, phmetroDigitalBean.getStrVerCodigo1()!=null?phmetroDigitalBean.getStrVerCodigo1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO2, phmetroDigitalBean.getStrVerCodigo2()!=null?phmetroDigitalBean.getStrVerCodigo2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CODIGO3, phmetroDigitalBean.getStrVerCodigo3()!=null?phmetroDigitalBean.getStrVerCodigo3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD1, phmetroDigitalBean.getStrVerCaducidad1()!=null?phmetroDigitalBean.getStrVerCaducidad1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD2, phmetroDigitalBean.getStrVerCaducidad2()!=null?phmetroDigitalBean.getStrVerCaducidad2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_CADUCIDAD3, phmetroDigitalBean.getStrVerCaducidad3()!=null?phmetroDigitalBean.getStrVerCaducidad3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA1, phmetroDigitalBean.getStrVerMarca1()!=null?phmetroDigitalBean.getStrVerMarca1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA2, phmetroDigitalBean.getStrVerMarca2()!=null?phmetroDigitalBean.getStrVerMarca2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_MARCA3, phmetroDigitalBean.getStrVerMarca3()!=null?phmetroDigitalBean.getStrVerMarca3():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION1, phmetroDigitalBean.getStrVerEspecificacion1()!=null?phmetroDigitalBean.getStrVerEspecificacion1():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION2, phmetroDigitalBean.getStrVerEspecificacion2()!=null?phmetroDigitalBean.getStrVerEspecificacion2():" ");
		inputs.put(ConstantsLaboratorio.PAR_CVERIF_ESPECIFICACION3, phmetroDigitalBean.getStrVerEspecificacion3()!=null?phmetroDigitalBean.getStrVerEspecificacion3():" ");

		inputs.put(ConstantsLaboratorio.PAR_VESTADO, phmetroDigitalBean.getIntEstado());			

		inputs.put(Constants.PAR_CUSUARIO_MODI, phmetroDigitalBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, phmetroDigitalBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#inactivarPhmetroDigital(pe.com.sedapal.scr.core.beans.PhmetroDigitalBean)
	 */
	@Override
	public void inactivarPhmetroDigital(PhmetroDigitalBean phmetroDigitalBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INACTIVA_PHMETRO_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, phmetroDigitalBean.getIntCodigo());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, phmetroDigitalBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#obtenerDatosEquiposDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquiposDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean, Paginacion paginacion) throws Exception {
		List lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL).withProcedureName(ConstantsLaboratorio.SP_DETALLE_PHMETRO_DIGITAL)
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
										record.add(rs.getString(8)); // ""
										record.add(rs.getString(9)); // ""
										record.add((rs.getString(10)!=null && rs.getString(10).trim().equals("1"))?"SI":"NO"); // ""
										record.add(rs.getString(11)); // ""
										record.add(rs.getInt(12)); // ""
										record.add(rs.getInt(13)); // ""
										

										return record;
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(ConstantsLaboratorio.PAR_NID_EQUIPO, phmetroDigitalDetalleBean.getIntIdPhmetroDigital());
		
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
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#grabarPhmetroDigitalDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean)
	 */
	@Override
	public int grabarPhmetroDigitalDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean) throws Exception {
		List<SqlParameter> lstParamsInput = null;
		List<SqlOutParameter> lstParamsOutput = null;
		Map<String, Object> inputs = null;
		int intRetorno=-1;

		lstParamsInput = new ArrayList<SqlParameter>();			
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_EQUIPO, OracleTypes.NUMBER));		
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_DFECHA, OracleTypes.DATE));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CRESPONSABLE_DET, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIBRACION1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIBRACION2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIBRACION3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCALIBRACION4, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIFICACION1, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIFICACION2, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CVERIFICACION3, OracleTypes.VARCHAR));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CCONFORMIDAD, OracleTypes.NUMBER));	
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_CREVISADO, OracleTypes.VARCHAR));					
		lstParamsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_CREACION, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CUSUARIO_MODI, OracleTypes.VARCHAR));
		lstParamsInput.add(new SqlParameter(Constants.PAR_CPROGRAMA, OracleTypes.VARCHAR));				
		
		lstParamsOutput = new ArrayList<SqlOutParameter>();			
		lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_RETURN, OracleTypes.INTEGER));
		
		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.SP_INSERT_DETALLE__PTRO_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
		
		inputs = new HashMap<String, Object>();			
		inputs.put(ConstantsLaboratorio.PAR_NID_EQUIPO, phmetroDigitalDetalleBean.getIntIdPhmetroDigital());	
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date f1 = formatter.parse(phmetroDigitalDetalleBean.getStrFecha());
		java.sql.Date f1sql = new java.sql.Date(f1.getTime());
		
		inputs.put(ConstantsLaboratorio.PAR_DFECHA, f1sql);
		inputs.put(ConstantsLaboratorio.PAR_CRESPONSABLE_DET, phmetroDigitalDetalleBean.getStrResponsable());	
		inputs.put(ConstantsLaboratorio.PAR_CCALIBRACION1, phmetroDigitalDetalleBean.getStrcalibracion1());
		inputs.put(ConstantsLaboratorio.PAR_CCALIBRACION2, phmetroDigitalDetalleBean.getStrcalibracion2());
		inputs.put(ConstantsLaboratorio.PAR_CCALIBRACION3, phmetroDigitalDetalleBean.getStrcalibracion3());
		inputs.put(ConstantsLaboratorio.PAR_CCALIBRACION4, phmetroDigitalDetalleBean.getStrcalibracion4());
		inputs.put(ConstantsLaboratorio.PAR_CVERIFICACION1, phmetroDigitalDetalleBean.getStrVerificacion1());
		inputs.put(ConstantsLaboratorio.PAR_CVERIFICACION2, phmetroDigitalDetalleBean.getStrVerificacion2());
		inputs.put(ConstantsLaboratorio.PAR_CVERIFICACION3, phmetroDigitalDetalleBean.getStrVerificacion3());
		inputs.put(ConstantsLaboratorio.PAR_CCONFORMIDAD, phmetroDigitalDetalleBean.getIntConformidad());	
		inputs.put(ConstantsLaboratorio.PAR_CREVISADO, phmetroDigitalDetalleBean.getStrRevisado());		
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, phmetroDigitalDetalleBean.getIntEstado());			

		inputs.put(Constants.PAR_CUSUARIO_CREACION, phmetroDigitalDetalleBean.getUsuarioCreacion());	
		inputs.put(Constants.PAR_CUSUARIO_MODI, phmetroDigitalDetalleBean.getUsuarioModificacion());	
		inputs.put(Constants.PAR_CPROGRAMA, phmetroDigitalDetalleBean.getPrograma());	
		
		Map<String, Integer> outputs = this.execSp.executeSp(inputs);
		intRetorno = outputs.get(Constants.PAR_OUT_RETURN);
		return intRetorno;
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.dao.IPhmetroDigitalDao#inactivarPhmetroDigitalDetalle(pe.com.sedapal.scr.core.beans.PhmetroDigitalDetalleBean)
	 */
	@Override
	public void inactivarPhmetroDigitalDetalle(PhmetroDigitalDetalleBean phmetroDigitalDetalleBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		
		paramsInput = new ArrayList<SqlParameter>();
		
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_NID_DETALLE, OracleTypes.NUMBER));
		paramsInput.add(new SqlParameter(ConstantsLaboratorio.PAR_VESTADO, OracleTypes.NUMBER));

		paramsOutput = new ArrayList<SqlOutParameter>();

		this.execSp = new ExecuteProcedure(template.getDataSource(), 
				CoreUtils.concatenar(ConstantsLaboratorio.PACKAGE_PHMETRO_DIGITAL,ConstantsCommon.P_SEPARADOR,ConstantsLaboratorio.PRC_INAC_DETALLE_PTRO_DIGITAL), 
				environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), paramsInput, paramsOutput);
		inputs = new HashMap<String, Object>();
		
		inputs.put(ConstantsLaboratorio.PAR_NID_DETALLE, phmetroDigitalDetalleBean.getIntIdDetalle());	
		inputs.put(ConstantsLaboratorio.PAR_VESTADO, phmetroDigitalDetalleBean.getIntEstado());
		
		this.execSp.executeSp(inputs);
		
	}

}
