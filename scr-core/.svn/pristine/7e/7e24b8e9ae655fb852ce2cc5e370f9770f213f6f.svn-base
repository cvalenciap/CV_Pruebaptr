/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.services.ITipoOperacionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoOperacionServiceImpl.
 */
@Service
public class TipoOperacionServiceImpl implements ITipoOperacionService {

	/**
	 * Obtiene la lista de tipos de operación para la configuración de correos.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos de los tipos de operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerAllTipoOperacion() throws Exception {
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < Constants.MANTCORREO_TIPOOPERACION.length; i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + (i+1));
			sib.setLabel(Constants.MANTCORREO_TIPOOPERACION[i]);
			result.add(sib);
		}
		return result;
	}

}
