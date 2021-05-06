/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoDao;
import pe.com.sedapal.scr.core.services.IAlmacenamientoService;

// TODO: Auto-generated Javadoc
/**
 * The Class AlmacenamientoServiceImpl.
 */
@Service
public class AlmacenamientoServiceImpl implements IAlmacenamientoService {
	
	/** The almacenamiento dao. */
	@Autowired
	private IAlmacenamientoDao almacenamientoDao;

	/**
	 * Realiza la modificación de almacenamiento.
	 *
	 * @param strFecha Es la fecha para la cual se realiza la modificación
	 * @param strFormulaEmbalse Formula a evaluar
	 * @param strFormulaManDescarga Formula a evaluar
	 * @param strForumlaUno the str forumla uno
	 * @param strForumlaDos the str forumla dos
	 * @param auditoria Es el objeto con los datos de auditoria
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarAlmacenamiento(String strFecha, String strFormulaEmbalse, 
			String strFormulaManDescarga, String strForumlaUno, String strForumlaDos,
			BaseBean auditoria) throws Exception {
		strFormulaEmbalse = strFormulaEmbalse.replace(",", ".");
		strFormulaManDescarga = strFormulaManDescarga.replace(",", ".");
		strForumlaUno = strForumlaUno.replace(",", ".");
		strForumlaDos = strForumlaDos.replace(",", ".");
		almacenamientoDao.actualizarAlmacenamiento(strFecha, strFormulaEmbalse, strFormulaManDescarga,
				strForumlaUno, strForumlaDos, auditoria);
	}

}
