package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.beans.AlmacenamientoBean;
import pe.com.sedapal.scr.core.beans.GeneralBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IGeneralDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;


@Repository
public class GeneralDaoImpl implements IGeneralDao {

	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;

	@SuppressWarnings("unchecked")
	@Override
	public List<GeneralBean> listarTodos() throws GmdException {
		List<GeneralBean> lstRetorno = null;

		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_GENERALES).withProcedureName(ConstantesSptar.PRC_LIST_GENERALES)
				.declareParameters(									
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<GeneralBean>() {
									@Override
									public GeneralBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										GeneralBean record = new GeneralBean();
										record.setIdGeneral(rs.getInt(1));
										record.setDescripcion(rs.getString(2));
										record.setDescripcionCorta(rs.getString(3));
										record.setInmodificable(rs.getInt(4));
										record.setStRegi(rs.getString(5));
										record.setEstado(rs.getString(6));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();
		Map<String, Object> results = caller.execute(params);
		
		lstRetorno = (List<GeneralBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
