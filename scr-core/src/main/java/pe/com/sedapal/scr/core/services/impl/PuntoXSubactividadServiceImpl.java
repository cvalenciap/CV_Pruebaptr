/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.sedapal.scr.core.beans.PuntoXSubactividad;
import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.dao.PuntoXSubactividadDao;
import pe.com.sedapal.scr.core.services.IPuntoXSubactividadService;

// TODO: Auto-generated Javadoc
/**
 * The Class PuntoXSubactividadServiceImpl.
 */
@Service
public class PuntoXSubactividadServiceImpl implements IPuntoXSubactividadService {

	/** The punto X subactividad dao. */
	@Autowired
	private PuntoXSubactividadDao puntoXSubactividadDao;

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPuntoXSubactividadService#actualizarPuntoXSubactividad(pe.com.sedapal.scr.core.beans.PuntoXSubactividad)
	 */
	@Override
	public void actualizarPuntoXSubactividad(PuntoXSubactividad puntoXSubactividad) {
		puntoXSubactividadDao.actualizarPuntoXSubactividad(puntoXSubactividad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPuntoXSubactividadService#insertarPuntoXSubactividad(pe.com.sedapal.scr.core.beans.PuntoXSubactividad)
	 */
	@Override
	public void insertarPuntoXSubactividad(PuntoXSubactividad puntoXSubactividad) {
		puntoXSubactividadDao.insertarPuntoXSubactividad(puntoXSubactividad);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPuntoXSubactividadService#existePuntoXSubactividad(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean existePuntoXSubactividad(Integer idSubactividad, Integer idPunto) {
		return puntoXSubactividadDao.existePuntoXSubactividad(idSubactividad, idPunto);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IPuntoXSubactividadService#listarPuntos(java.lang.Integer)
	 */
	@Override
	public List<SelectItemBean> listarPuntos(Integer idSubactividad) {
		return puntoXSubactividadDao.listarPuntos(idSubactividad);
	}
	
}
