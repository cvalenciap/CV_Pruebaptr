/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */

package pe.com.sedapal.scr.core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sedapal.scr.core.util.ExecuteProcedure;
import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.common.ConstantsCorreo;
import pe.com.sedapal.scr.core.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalDia;
import pe.com.sedapal.scr.correo.core.beans.MedidaCaudalHora;

// TODO: Auto-generated Javadoc
/**
 * The Class CaudalDetalleDaoImpl.
 */
@Repository
public class CaudalDetalleDaoImpl implements ICaudalDetalleDao {
	
	/** The environment. */
	@Autowired
	private Environment environment;
	
	/** The template. */
	@Autowired
	private JdbcTemplate template;
	
	/** The exec sp. */
	private ExecuteProcedure execSp;

	/**
	 * Método que permite registrar el detalle de un caudal.
	 *
	 * @param caudalDetalle representa la información de detalle de caudal
	 * @return Objeto de tipo Integer con el identificador generado en la inserción
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer registrarCaudalDetalle(CaudalDetalle caudalDetalle) throws Exception {
		Integer intCodigoCaudalDetalle = null;
		
		try {
			String spName = ConstantsCorreo.PACKAGE_CAUDAL + ConstantsCorreo.P_SEPARADOR + ConstantsCorreo.SP_INSERT_CAUDAL_DETALLE;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a registrar
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_N_CODCAU, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_DIA, OracleTypes.VARCHAR));
			
			for(int i = 0; i < 24; i++) {
				String strParam = ConstantsCorreo.PREFIX_PAR_N_NHR + (i < 9 ? "0" + (i + 1) : i + 1);
				
				lstParamsInput.add(new SqlParameter(strParam, OracleTypes.NUMBER));
			}
			
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_N_PROMED, OracleTypes.NUMBER));
			
			// Datos de auditoria
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_USUINS, OracleTypes.VARCHAR));			
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_PROGRA, OracleTypes.VARCHAR));		
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_CODARE , OracleTypes.INTEGER));	
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_A_CODSIS, OracleTypes.INTEGER));		
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			lstParamsOutput.add(new SqlOutParameter(ConstantsCorreo.PAR_OUT_COD_CAU_DET, OracleTypes.INTEGER));
			
			// Creamos ejecutor de Store Procedure
			this.execSp = new ExecuteProcedure(template.getDataSource(), spName, environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(ConstantsCorreo.PAR_N_CODCAU, caudalDetalle.getCaudal().getIntCodigo());
			inputs.put(ConstantsCorreo.PAR_V_DIA, caudalDetalle.getCaudalDia().getStrDia());
			
			MedidaCaudalDia medidaCaudalDia = caudalDetalle.getCaudalDia();
			List<MedidaCaudalHora> lstMedidasCaudalesHora = medidaCaudalDia.getLstCaudalesDia();
			
			for(int i = 0; i < 24; i++) {
				String strParam = ConstantsCorreo.PREFIX_PAR_N_NHR + (i < 9 ? "0" + (i + 1) : i + 1);
				
				if(i < lstMedidasCaudalesHora.size()) {
					inputs.put(strParam, lstMedidasCaudalesHora.get(i).getDblValor() == null? Constants.DEFAULT_CAUDET_NUMBER_DOUBLE :
						lstMedidasCaudalesHora.get(i).getDblValor());
				} else {
					inputs.put(strParam, Constants.DEFAULT_CAUDET_NUMBER_DOUBLE);
				}
			}
			
			inputs.put(ConstantsCorreo.PAR_N_PROMED, medidaCaudalDia.getDblCaudalPromedioDia());
			inputs.put(ConstantsCorreo.PAR_A_USUINS, caudalDetalle.getUsuarioCreacion());		
			inputs.put(ConstantsCorreo.PAR_A_PROGRA, caudalDetalle.getPrograma());		
			inputs.put(ConstantsCorreo.PAR_A_CODARE, caudalDetalle.getCodigoArea());		
			inputs.put(ConstantsCorreo.PAR_A_CODSIS, caudalDetalle.getCodigoSistema());		
			
			// Ejecutamos el store procedure
			Map<String, Integer> outputs = (Map<String, Integer>) this.execSp.executeSp(inputs);
			
			intCodigoCaudalDetalle = outputs.get(ConstantsCorreo.PAR_OUT_COD_CAU_DET);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return intCodigoCaudalDetalle;
	}
	
	/**
	 * Método que permite eliminar un detalle de un caudal.
	 *
	 * @param caudalDetalle representa la información de detalle de caudal
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void eliminarCaudalDetalle(CaudalDetalle caudalDetalle) throws Exception {
		try {
			String spName = ConstantsCorreo.PACKAGE_CAUDAL + ConstantsCorreo.P_SEPARADOR + ConstantsCorreo.SP_DELETE_CAUDAL_DETALLE;
			
			// Parametros de entrada
			List<SqlParameter> lstParamsInput = new ArrayList<SqlParameter>();
			
			// Datos a filtrar para el delete
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_N_CODCAU, OracleTypes.INTEGER));
			lstParamsInput.add(new SqlParameter(ConstantsCorreo.PAR_V_DIA, OracleTypes.VARCHAR));
	
			// Parametros de salida
			List<SqlOutParameter> lstParamsOutput = new ArrayList<SqlOutParameter>();
			
			// Creamos ejecutor de Store Procedure
			this.execSp = new ExecuteProcedure(template.getDataSource(), spName, environment.getRequiredProperty(ConstantsCommon.ORACLE_PROCEDURES_SCHEMA), lstParamsInput, lstParamsOutput);
			
			
			// Preparamos parametros de entrada y sus valores
			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(ConstantsCorreo.PAR_N_CODCAU, caudalDetalle.getCaudal().getIntCodigo());
			inputs.put(ConstantsCorreo.PAR_V_DIA, caudalDetalle.getCaudalDia().getStrDia());
			
			// Ejecutamos el store procedure
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
