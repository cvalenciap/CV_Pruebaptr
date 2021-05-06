/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.CargaBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.ICargaDao;
import pe.com.sedapal.scr.core.services.ICargaService;

// TODO: Auto-generated Javadoc
/**
 * The Class CargaServiceImpl.
 */
@Service
public class CargaServiceImpl implements ICargaService {
	
	/** The carga dao. */
	@Autowired
	private ICargaDao cargaDao;

	/**
	 * Método que permite obtener la última carga automática de caudales.
	 *
	 * @return the carga bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo CargaBean que contiene los datos de la carga
	 */
	@Override
	public CargaBean obtenerUltimaCarga() throws Exception {
		CargaBean carga = cargaDao.obtenerUltimaCarga();
		if(carga!=null&&carga.getStrEstado()!=null&&carga.getStrFechaRegistro()!=null){
			if(carga.getStrEstado().equals(Constants.CARGA_CAUDAL_FALLIDO)){
				carga.setStrMensajeAlerta(Constants.CARGA_ERROR.replace(Constants.PARAM1, carga.getStrFechaRegistro() + ""));
			}
			else if(carga.getStrEstado().equals(Constants.CARGA_CAUDAL_EXITO)){
				carga.setStrMensajeAlerta(Constants.CARGA_EXITO.replace(Constants.PARAM1, carga.getStrFechaRegistro() + ""));
			}
		}
		
		return carga;
	}

	/**
	 * Método que permite obtener el listado de cargas.
	 *
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result buscarCargas(Paginacion paginacion) throws Exception {
		return cargaDao.buscarCargas(paginacion);
	}

}
