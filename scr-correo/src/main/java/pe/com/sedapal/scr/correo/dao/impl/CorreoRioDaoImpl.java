/**
 * 
 * Resumen.
 * Objeto 				: CorreoRioDaoImpl
 * Descripción 			: Clase implementadora de la interfaz (de acceso a datos) de río
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.AbstractJdbcDao;
import pe.com.sedapal.scr.correo.dao.IRioDao;
import pe.com.sedapal.scr.correo.mapper.RioMapper;

@Repository
public class CorreoRioDaoImpl extends AbstractJdbcDao implements IRioDao {
	@Autowired
	private Environment environment;
	
	/**
	 * Método que permite obtener ríos para un estado
	 * @Return Objeto de tipo RioBean que contiene los datos ríos 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RioBean> listarRiosPorEstado(String strEstado) throws GmdException {
		List<RioBean> lstRios = new ArrayList<RioBean>();
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(getJdbcTemplate().getDataSource());
			caller.withCatalogName(Constants.PACKAGE_RIO).withProcedureName(Constants.SP_LISTA_RIO)
					.declareParameters(
							new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR), 
							new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new RioMapper()))
					.withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(Constants.PAR_V_ESTADO, strEstado);

			Map<String, Object> results = caller.execute(params);
			lstRios = (List<RioBean>) results.get(Constants.PAR_OUT_CURSOR);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new GmdException(ex);
		}
		return lstRios;
	}
}
