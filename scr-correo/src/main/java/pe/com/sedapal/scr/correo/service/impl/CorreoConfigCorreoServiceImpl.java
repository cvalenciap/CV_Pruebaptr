/**
 * 
 * Resumen.
 * Objeto 				: CorreoConfigCorreoServiceImpl
 * Descripción 			: Clase implementadora de la interfaz (de servicio) de configuración del correo
 * Fecha de Creación 	: 25/07/2017
 * Autor 				: lmgamarrach
 * -----------------------------------------------------------------
 * Modificaciones
 * Fecha             Nombre                  Descripción
 * -----------------------------------------------------------------
 * 
 *
 */
package pe.com.sedapal.scr.correo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;
import pe.com.sedapal.scr.correo.dao.IConfigCorreoDao;
import pe.com.sedapal.scr.correo.service.IConfigCorreoService;

@Service
public class CorreoConfigCorreoServiceImpl implements IConfigCorreoService {
	
	@Autowired
	private IConfigCorreoDao configCorreoDao;
	
	/**
	 * Método que permite obtener datos de configuración de correo para un periodo y un estado
	 * @Return Objeto de tipo ConfiguracionCorreo que contiene los datos de configuración de correo 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public ConfiguracionCorreo obtenerConfigCorreo(String strTipoOperacion, String strEstado) throws GmdException {
		try {
			return configCorreoDao.obtenerConfigCorreo(strTipoOperacion, strEstado);
		} catch (Exception ex) {
			throw new GmdException(ex);
		}
	}
	
}
