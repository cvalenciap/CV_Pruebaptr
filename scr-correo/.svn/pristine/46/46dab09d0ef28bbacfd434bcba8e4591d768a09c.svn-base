/**
 * 
 * Resumen.
 * Objeto 				: CorreoCaudalDetalleServiceImpl
 * Descripción 			: Clase implementadora de la interfaz (de servicio) del detalle del caudal
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

import pe.com.sedapal.scr.correo.core.beans.CaudalDetalle;
import pe.com.sedapal.scr.correo.dao.ICaudalDetalleDao;
import pe.com.sedapal.scr.correo.service.ICaudalDetalleService;

@Service
public class CorreoCaudalDetalleServiceImpl implements ICaudalDetalleService {
	
	@Autowired
	private ICaudalDetalleDao caudalDetalleDao;
	
	/**
	 * Método que permite registrar caudal detalle
	 * @Return Objeto de tipo Integer que contiene el código caudal detalle 
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Integer registrarCaudalDetalle(CaudalDetalle caudalDetalle) throws Exception {
		return caudalDetalleDao.registrarCaudalDetalle(caudalDetalle);
	}

}
