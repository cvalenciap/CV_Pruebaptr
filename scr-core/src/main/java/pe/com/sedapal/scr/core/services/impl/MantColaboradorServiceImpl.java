/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.common.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.beans.ColaboradorBean;
import pe.com.sedapal.scr.core.beans.TrabajadorBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantColaboradorDao;
import pe.com.sedapal.scr.core.services.IMantColaboradorService;

// TODO: Auto-generated Javadoc
/**
 * The Class MantColaboradorServiceImpl.
 */
@Service
public class MantColaboradorServiceImpl implements IMantColaboradorService {

	/** The i mant colaborador dao. */
	@Autowired
	private IMantColaboradorDao iMantColaboradorDao;
	
	
	/**
	 * Método que permite obtener el listado de Colaboradores.
	 *
	 * @param colaboradorBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo Result que contiene los resultados
	 */
	@Override
	public Result obtenerMantColaborador(ColaboradorBean colaboradorBean, Paginacion paginacion) throws Exception {
		return iMantColaboradorDao.obtenerMantColaborador(colaboradorBean, paginacion);
	}

	/**
	 *  
	 * Obtiene la represa por identificador.
	 *
	 * @param id the id
	 * @return the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ColaboradorBean que contiene el registro
	 */
	@Override
	public ColaboradorBean obtenerColaborador(Integer id) throws Exception {
		return iMantColaboradorDao.obtenerColaborador(id);
	}
	
	/**
	 * Realiza el registro de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @return boolean que indica el éxito de la operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean grabarColaborador(ColaboradorBean colaboradorBean) throws Exception {
		 if(!iMantColaboradorDao.validarAbrevMantColaborador(colaboradorBean).isBolAbreviatura()){
			 colaboradorBean.setAbreviatura(colaboradorBean.getAbreviatura().trim());
			 iMantColaboradorDao.grabarColaborador(colaboradorBean);
			return true;
		}
		else{
			return false;
		}
		
		
	}

	/**
	 * Realiza la modificación de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @param abreviaturaIsUpdate the abreviatura is update
	 * @return boolean que indica el éxito de la operación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public boolean actualizarColaborador(ColaboradorBean colaboradorBean, boolean abreviaturaIsUpdate) throws Exception {
		if(abreviaturaIsUpdate){
			if(!iMantColaboradorDao.validarAbrevMantColaborador(colaboradorBean).isBolAbreviatura()){
				colaboradorBean.setAbreviatura(colaboradorBean.getAbreviatura().trim());
				iMantColaboradorDao.actualizarColaborador(colaboradorBean);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			iMantColaboradorDao.actualizarColaborador(colaboradorBean);
			return true;
		}

	}
	
	/**
	 * Realiza el cambio de estado de colaborador.
	 *
	 * @param colaboradorBean the colaborador bean
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarColaborador(ColaboradorBean colaboradorBean) throws Exception {
		iMantColaboradorDao.inactivarColaborador(colaboradorBean);
	}
	
	/**
	 * Obtiene la lista de todos los colaboradores activos registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerColaboradoresActivos() throws Exception {
		List<ColaboradorBean> colaboradores = iMantColaboradorDao.obtenerColaboradores(Constants.ACTIVE_STATE);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < colaboradores.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + colaboradores.get(i).getLonCodigo());
			sib.setLabel(colaboradores.get(i).getNombreColaborador());
			result.add(sib);
		}
		return result;
	}
	
	/**
	 * Obtiene la lista de todos los colaboradores registrados en la plataforma.
	 *
	 * @return Lista de objetos de tipo SelectItemBean que contiene los datos del colaborador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public List<SelectItemBean> obtenerColaboradoresTodos() throws Exception {
		List<ColaboradorBean> colaboradores = iMantColaboradorDao.obtenerColaboradores(null);
		List<SelectItemBean> result = new ArrayList<>();
		for(int i = 0; i < colaboradores.size(); i++){
			SelectItemBean sib = new SelectItemBean();
			sib.setValue("" + colaboradores.get(i).getLonCodigo());
			sib.setLabel(colaboradores.get(i).getNombreColaborador());
			result.add(sib);
		}
		return result;
	}

	/**
	 * Obtiene una lista de trabajadores de sedepal.
	 *
	 * @param trabajadorBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Result obtenerListaDeTrabajadores(TrabajadorBean trabajadorBean, Paginacion paginacion) throws Exception {
		return iMantColaboradorDao.obtenerListaDeTrabajadores(trabajadorBean, paginacion);
	}

	/**
	 * Obtiene a un trabajador de sedapal buscado por número de ficha.
	 *
	 * @param intNumFicha es el número de ficha para la búsqueda
	 * @return Objeto de tipo TrabajadorBean que contiene los datos del trabajador
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public TrabajadorBean obtenerTrabajador(Integer intNumFicha) throws Exception {
		return iMantColaboradorDao.obtenerTrabajador(intNumFicha);
	}
	
}
