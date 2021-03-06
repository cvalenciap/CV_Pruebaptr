/**
 * 
 * Resumen.
 * Objeto 				: CorreoRioServiceImpl
 * Descripción 			: Clase implementadora de la interfaz (de servicio) de río
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.RioBean;
import pe.com.sedapal.scr.correo.dao.IRioDao;
import pe.com.sedapal.scr.correo.service.IRioService;

@Service
public class CorreoRioServiceImpl implements IRioService {
	
	@Autowired
	private IRioDao irioDao;
	
	/**
	 * Método que permite obtener ríos para un estado
	 * @Return Objeto de tipo RioBean que contiene los datos ríos 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<RioBean> listarRiosPorEstado(final String strEstado) throws GmdException {
		try {
			return irioDao.listarRiosPorEstado(strEstado);
		} catch (Exception ex) {
			throw new GmdException(ex);
		}
		
	}

}
