/**
 * 
 * Resumen.
 * Objeto 				: CaudalMapper
 * Descripción 			: Clase que obtiene y guarda datos de caudal
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pe.com.sedapal.scr.correo.core.beans.Caudal;

public class CaudalMapper implements RowMapper<Caudal> {

	/**
	 * Método que permite obtener y guardar datos de Caudal
	 * @Return Objeto de tipo RioBean con el contenido de Caudal 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Caudal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Caudal caudal = new Caudal();
		caudal.setIntCodigo(rs.getInt(1));
		caudal.setIntCodigoRio(rs.getInt(2));
		caudal.setStrTipoProceso(rs.getString(3));
//		caudal.setStrLote(rs.getString(4));
		caudal.setStrRuta(rs.getString(5));
		caudal.setStrPeriodo(rs.getString(6));
		caudal.setDteFechaHoraRegistro(rs.getDate(7));
		caudal.setStrEstado(rs.getString(8));
		caudal.setStrNombreArchivo(rs.getString(9));
		caudal.setIntCodigoCarga(rs.getInt(10));
		caudal.setDteFechaHoraProceso(rs.getDate(11));
		
		return caudal;
	}
	
}