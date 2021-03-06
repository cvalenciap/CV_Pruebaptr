/**
 * 
 * Resumen.
 * Objeto 				: CorreoCargaServiceImpl
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
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.correo.core.beans.Carga;
import pe.com.sedapal.scr.correo.dao.ICargaDao;
import pe.com.sedapal.scr.correo.service.ICargaService;

@Service
public class CorreoCargaServiceImpl implements ICargaService {
	
	@Autowired
	private ICargaDao cargaDao;
	
	/**
	 * Método que permite registrar carga
	 * @Return Objeto de tipo Integer que contiene código de carga 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public Integer registrarCarga(Carga carga) throws GmdException {
		try {
			return cargaDao.registrarCarga(carga);
		} catch (Exception ex) {
			throw new GmdException(ex);
		}
	}

}
