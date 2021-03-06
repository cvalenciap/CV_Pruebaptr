/**
 * 
 * Resumen.
 * Objeto 				: CorreoCaudalDetalleDaoImpl
 * Descripción 			: Clase implementadora de la interfaz (de acceso a datos) del detalle del caudal
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalDia;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalHora;
import pe.com.sedapal.scr.correo.core.common.Constants;
import pe.com.sedapal.scr.correo.dao.AbstractJdbcDao;
import pe.com.sedapal.scr.correo.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.correo.util.ExecuteProcedure;

@Repository
public class CorreoCaudalDetalleDaoImpl extends AbstractJdbcDao implements ICaudalDetalleDao {
	
	@Autowired
	private Environment env;
	
	/**
	 * Método que permite registrar caudal detalle
	 * @Return Objeto de tipo Integer que contiene el código caudal detalle 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer registrarCaudalDetalle(CaudalDetalle caudalDetalle) throws GmdException {
		Integer intCodigoCaudalDetalle = null;
		
		try {
			String spName = Constants.PACKAGE_CAUDAL + Constants.P_SEPARADOR + Constants.SP_INSERT_CAUDAL_DETALLE;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_CODCAU, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(Constants.PAR_V_DIA, OracleTypes.VARCHAR));
			
			for(int i = 0; i < 24; i++) {
				String strParam = Constants.PREFIX_PAR_N_NHR + (i < 9 ? "0" + (i + 1) : i + 1);
				
				lstParamsInput.add(new SqlParameter(strParam, OracleTypes.NUMBER));
			}
			
			lstParamsInput.add(new SqlParameter(Constants.PAR_N_PROMED, OracleTypes.NUMBER));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_USUINS, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(Constants.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(Constants.PAR_OUT_COD_CAU_DET, OracleTypes.INTEGER));
			
			// Creamos ejecutor de Store Procedure
			ExecuteProcedure execSp = new ExecuteProcedure(getJdbcTemplate().getDataSource(), spName,
					env.getRequiredProperty(Constants.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(Constants.PAR_N_CODCAU, caudalDetalle.getCaudal().getIntCodigo());
			inputs.put(Constants.PAR_V_DIA, caudalDetalle.getCaudalDia().getStrDia());
			
			MedidaCaudalDia medidaCaudalDia = caudalDetalle.getCaudalDia();
			List<MedidaCaudalHora> lstMedidasCaudalesHora = medidaCaudalDia.getLstCaudalesDia();
			
			for(int i = 0; i < 24; i++) {
				String strParam = Constants.PREFIX_PAR_N_NHR + (i < 9 ? "0" + (i + 1) : i + 1);
				
				if(i < lstMedidasCaudalesHora.size()) {
					inputs.put(strParam, lstMedidasCaudalesHora.get(i).getDblValor() == null? Constants.DEFAULT_DETCAU_DOUBLE :
						lstMedidasCaudalesHora.get(i).getDblValor());
				} else {
					inputs.put(strParam, Constants.DEFAULT_DETCAU_DOUBLE);
				}
			}
			
			inputs.put(Constants.PAR_N_PROMED, medidaCaudalDia.getDblCaudalPromedioDia());
			inputs.put(Constants.PAR_A_USUINS, Constants.USUARIO_ADMIN);	
			inputs.put(Constants.PAR_A_PROGRA, Constants.PROGRAMA);		
			inputs.put(Constants.PAR_A_CODARE, Constants.CODIGO_AREA);		
			inputs.put(Constants.PAR_A_CODSIS, Integer.parseInt(env.getProperty("parametro.sistema.codigo")));		

			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>)execSp.executeSp(inputs);
			
			intCodigoCaudalDetalle = outputs.get(Constants.PAR_OUT_COD_CAU_DET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GmdException(e);
		}
		
		return intCodigoCaudalDetalle;
	}

}
