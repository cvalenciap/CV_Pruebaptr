/**
 * 
 * Resumen.
 * Objeto 				: ConfigCorreoMapper
 * Descripción 			: Clase que obtiene y guarda datos de configuración de correo
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

import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.core.beans.CuentaCorreo;
import pe.com.sedapal.scr.correo.core.beans.MensajeCorreo;
import pe.com.sedapal.scr.correo.core.beans.ServidorArchivos;
import pe.com.sedapal.scr.correo.core.beans.ServidorCorreos;

public class ConfigCorreoMapper implements RowMapper<ConfiguracionCorreo> {

	/**
	 * Método que permite obtener y guardar datos de configuración de correo
	 * @Return Objeto de tipo RioBean con el contenido de configuración de correo 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ConfiguracionCorreo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfiguracionCorreo configCorreo = new ConfiguracionCorreo();
		
		// MensajeCorreo
		MensajeCorreo mensajeCorreo = new MensajeCorreo();
		mensajeCorreo.setStrAsunto(rs.getString(6)); // V_ASUNTO
		mensajeCorreo.setStrContenido(rs.getString(7)); // V_CUERPO
		mensajeCorreo.setStrRemitente(rs.getString(8)); // V_ORIGEN
		mensajeCorreo.setStrNombreAdjuntoConfig(rs.getString(16)); // V_NOMADJ
		mensajeCorreo.setStrPara(rs.getString(4)); // V_PARA
		mensajeCorreo.setStrConCopia(rs.getString(5).trim()); // V_CC
		
		// CuentaCorreo
		CuentaCorreo cuentaCorreo = new CuentaCorreo();
		cuentaCorreo.setStrCorreo(rs.getString(10)); // V_USUCORREO
		cuentaCorreo.setStrClave(rs.getString(11)); // V_PASSCORREO
		
		// ServidorArchivos
		ServidorArchivos servidorArchivos = new ServidorArchivos();
		servidorArchivos.setStrUrl(rs.getString(12)); // V_RUTASERVFILE
		servidorArchivos.setStrUsuario(rs.getString(13)); // V_USUFILE
		servidorArchivos.setStrClave(rs.getString(14)); // V_PASSFILE
		servidorArchivos.setIntRutaDefault(rs.getInt(17)); // N_RUTDEF
		
		// ServidorCorreos
		ServidorCorreos servidorCorreos = new ServidorCorreos();
		servidorCorreos.setStrIp(rs.getString(9)); // V_RUTASERVCORREO
		servidorCorreos.setIntPuerto(rs.getInt(15)); // V_PUERTO
		
		// Agregado de ConfiguracionCorreo
		configCorreo.setMensajeCorreo(mensajeCorreo);
		configCorreo.setCuentaCorreo(cuentaCorreo);
		configCorreo.setServidorArchivos(servidorArchivos);
		configCorreo.setServidorCorreos(servidorCorreos);
		
		return configCorreo;
	}

}
