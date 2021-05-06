package pe.com.sedapal.scr.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.PlantaTratamientoBean;
import pe.com.sedapal.scr.core.common.ConstantesSptar;
import pe.com.sedapal.scr.core.dao.IPlantaTratamientoDao;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;

@Repository
public class PlantaTratamientoDaoImpl implements IPlantaTratamientoDao {

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
	public List<PlantaTratamientoBean> obtenerPlantaTratamiento() throws GmdException {
		List<PlantaTratamientoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_PTAR_MAPS)
				.declareParameters(	
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PlantaTratamientoBean>() {
									@Override
									public PlantaTratamientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PlantaTratamientoBean record = new PlantaTratamientoBean();
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdPtar(rs.getInt(2));
										record.setDescripcionPtarxSector(rs.getString(3));
										record.setIdSector(rs.getInt(4));
										record.setDescripcionSector(rs.getString(5));
										record.setDescripcionPtarxSector(rs.getString(6));
										record.setDireccion(rs.getString(7));
										record.setLatitud(rs.getString(8));
										record.setLongitud(rs.getString(9));
										record.setDescripcionCorta(rs.getString(10));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PlantaTratamientoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlantaTratamientoBean> obtenerPlantaTratamientoxPtarSector(Integer idPtar) throws GmdException {
		List<PlantaTratamientoBean> lstRetorno = null;
		SimpleJdbcCall caller = new SimpleJdbcCall(template.getDataSource());
		caller.withCatalogName(ConstantesSptar.PCK_SPTAR_REG_LABORATORIO).withProcedureName(ConstantesSptar.PRC_LIST_MAPSXPTAR)
				.declareParameters(	
						new SqlParameter(ConstantesSptar.PAR_N_ID_PTARXSECTOR, Types.DECIMAL),
						new SqlOutParameter(
								ConstantesSptar.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<PlantaTratamientoBean>() {
									@Override
									public PlantaTratamientoBean mapRow(ResultSet rs, int rowNum) throws SQLException {
										PlantaTratamientoBean record = new PlantaTratamientoBean();
										record.setIdPtarxSector(rs.getInt(1));
										record.setIdPtar(rs.getInt(2));
										record.setDescripcionPtarxSector(rs.getString(3));
										record.setIdSector(rs.getInt(4));
										record.setDescripcionSector(rs.getString(5));
										record.setDescripcionPtarxSector(rs.getString(6));
										record.setDireccion(rs.getString(7));
										record.setLatitud(rs.getString(8));
										record.setLongitud(rs.getString(9));
										record.setDescripcionCorta(rs.getString(10));
										return record;										
									}
								}))
				.withSchemaName(environment.getRequiredProperty(ConstantesSptar.ORACLE_PROCEDURES_SCHEMA));

		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue(ConstantesSptar.PAR_N_ID_PTARXSECTOR, idPtar);
		Map<String, Object> results = caller.execute(params);		
		lstRetorno = (List<PlantaTratamientoBean>) results.get(ConstantesSptar.PAR_OUT_CURSOR);
		return lstRetorno;
	}
}
