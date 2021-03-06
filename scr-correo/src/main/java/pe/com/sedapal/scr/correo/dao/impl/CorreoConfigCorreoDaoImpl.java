/**
 * 
 * Resumen.
 * Objeto 				: CorreoConfigCorreoDaoImpl
 * Descripción 			: Clase implementadora de la interfaz (de acceso a datos) de configuración del correo
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
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.AbstractJdbcDao;
import pe.com.sedapal.scr.correo.dao.IConfigCorreoDao;
import pe.com.sedapal.scr.correo.mapper.ConfigCorreoMapper;

@Repository
public class CorreoConfigCorreoDaoImpl extends AbstractJdbcDao implements IConfigCorreoDao {
	
	@Autowired
	private Environment environment;
	
	/**
	 * Método que permite obtener datos de configuración de correo para un periodo y un estado
	 * @Return Objeto de tipo ConfiguracionCorreo que contiene los datos de configuración de correo 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConfiguracionCorreo obtenerConfigCorreo(String strTipoOperacion, String strEstado) throws GmdException {
		List<ConfiguracionCorreo> lstConfigCorreo = new ArrayList<ConfiguracionCorreo>();
		try {
			SimpleJdbcCall caller = new SimpleJdbcCall(jdbcTemplate.getDataSource());
			
			caller.withCatalogName(Constants.PCK_CORREO)
			      .withProcedureName(Constants.SP_GET_CONFIG_CORREO)
			      .declareParameters(
					new SqlParameter(Constants.PAR_V_TIPOPR, Types.VARCHAR),
					new SqlParameter(Constants.PAR_V_ESTADO, Types.VARCHAR),
					new SqlOutParameter(Constants.PAR_OUT_CURSOR, OracleTypes.CURSOR, new ConfigCorreoMapper()))
			      .withSchemaName(environment.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA));

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(Constants.PAR_V_TIPOPR, strTipoOperacion);
			params.addValue(Constants.PAR_V_ESTADO, Constants.ESTADO_ACTIVO);

			Map<String, Object> mapResults = caller.execute(params);
			lstConfigCorreo = (List<ConfiguracionCorreo>) mapResults.get(Constants.PAR_OUT_CURSOR);
			
			if(lstConfigCorreo != null && lstConfigCorreo.size() > 0) {
				return lstConfigCorreo.get(0);
			} else {
				return null;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new GmdException(ex);
		}		
	}

}
