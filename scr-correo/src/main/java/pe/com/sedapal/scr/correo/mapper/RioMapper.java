/**
 * 
 * Resumen.
 * Objeto 				: RioMapper
 * Descripción 			: Clase que obtiene y guarda datos de río
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

import pe.com.sedapal.scr.core.beans.RioBean;

public class RioMapper implements RowMapper<RioBean> {

	/**
	 * Método que permite obtener y guardar datos de Río
	 * @Return Objeto de tipo RioBean con el contenido de Río 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public RioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RioBean rioBean = new RioBean();
		
		rioBean.setCodigo(rs.getString(1));
		rioBean.setNombrerio(rs.getString(2));
		rioBean.setNombreLargo(rs.getString(3));
		rioBean.setAbreviatura(rs.getString(4));
		rioBean.setEstado(rs.getString(5));
		
		return rioBean;
	}

}
