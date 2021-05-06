/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.MantCorreoBean;
import pe.com.sedapal.scr.core.beans.MantCorreoEditBean;
import pe.com.sedapal.scr.core.beans.ValidaUnicidadCorreoBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantCorreoDao;
import pe.com.sedapal.scr.core.services.IMantCorreoService;

// TODO: Auto-generated Javadoc
/**
 * The Class MantCorreoServiceImpl.
 */
@Service
public class MantCorreoServiceImpl implements IMantCorreoService {
	
	/** The mant correo dao. */
	@Autowired
	private IMantCorreoDao mantCorreoDao;

	
	
	/**
	 * Método que permite obtener el listado de Rio.
	 *
	 * @param mantCorreoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @return the result
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public Result obtenerMantCorreo(MantCorreoBean mantCorreoBean, Paginacion paginacion) throws Exception {
		return mantCorreoDao.obtenerMantCorreo(mantCorreoBean, paginacion);
	}

	
	/**
	 *  
	 * Realiza el cambio de estado del correo.
	 *
	 * @param mantCorreoBean objeto del tipo MantCorreoBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void inactivarCorreo(MantCorreoBean mantCorreoBean) throws Exception {
		mantCorreoDao.inactivarCorreo(mantCorreoBean);
	}
	
	/**
	 *  
	 * Obtiene la configuración de correo por identificador.
	 *
	 * @param idMantCorreo Identificador del registro
	 * @return the mant correo
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo MantCorreoEditBean que contiene el registro
	 */
	@Override
	public MantCorreoEditBean getMantCorreo(Integer idMantCorreo) throws Exception {
		MantCorreoEditBean mant = mantCorreoDao.obtenerMantenimientoCorreo(idMantCorreo);
		if(mant.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_RESUMEN)){
			mant.setStrAsuntoRep(mant.getStrAsunto());
			mant.setStrEstadoRep(mant.getStrEstado());
		}
		else if(mant.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_PROCESO_AUTOMATICO)){
			mant.setStrAsuntoExt(mant.getStrAsunto());
			mant.setStrEstadoExt(mant.getStrEstado());
		}
		else if(mant.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_CORREO_FUENTE_ENVIOS)){
			mant.setStrRutaServCorreoEnv(mant.getStrRutaServCorreo());
			mant.setLonPuertoEnv(mant.getLonPuerto());
			mant.setStrUsuCorreoEnv(mant.getStrUsuCorreo());
			mant.setStrPassCorreoEnv(mant.getStrPassCorreo());
			mant.setStrEstadoEnv(mant.getStrEstado());
		}
		else if(mant.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_GERENCIA)){
			mant.setStrParaGer(mant.getStrPara());
			mant.setStrCcGer(mant.getStrCc());
			mant.setStrAsuntoGer(mant.getStrAsunto());
			mant.setStrCuerpoGer(mant.getStrCuerpo());
			mant.setStrEstadoGer(mant.getStrEstado());
		}
		return mant;
	}

	
	/**
	 *  
	 * Realiza el registro de Correo.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void grabarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception {
		
		if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_RESUMEN)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrUsuCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrPara(mantCorreoEditBean.getStrPara().replace(", ", ","));
			mantCorreoEditBean.setStrCc(mantCorreoEditBean.getStrCc().replace(", ", ","));
			if(mantCorreoEditBean.getStrCc().trim().length()==0){
				mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			}
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoRep());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoRep());
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_PROCESO_AUTOMATICO)){
			mantCorreoEditBean.setStrPara(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCuerpo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoExt());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoExt());
			if(mantCorreoEditBean.getLonRutaDefault() == null){
				mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			}
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_CORREO_FUENTE_ENVIOS)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			
			mantCorreoEditBean.setStrPara(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCuerpo(Constants.DEFAULT_VARCHAR);
			
			mantCorreoEditBean.setStrAsunto(Constants.DEFAULT_VARCHAR);
			
			// COPIAMOS EN EL ATRIBUTO GENERAL
			mantCorreoEditBean.setStrRutaServCorreo(mantCorreoEditBean.getStrRutaServCorreoEnv());
			mantCorreoEditBean.setLonPuerto(mantCorreoEditBean.getLonPuertoEnv());
			mantCorreoEditBean.setStrUsuCorreo(mantCorreoEditBean.getStrUsuCorreoEnv());
			mantCorreoEditBean.setStrPassCorreo(mantCorreoEditBean.getStrPassCorreoEnv());
			
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoEnv());
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_GERENCIA)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrUsuCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			
			// COPIAMOS EN EL ATRIBUTO GENERAL
			mantCorreoEditBean.setStrPara(mantCorreoEditBean.getStrParaGer().replace(", ", ","));
			mantCorreoEditBean.setStrCc(mantCorreoEditBean.getStrCcGer().replace(", ", ","));
			if(mantCorreoEditBean.getStrCcGer().trim().length()==0){
				mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			}
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoGer());
			mantCorreoEditBean.setStrCuerpo(mantCorreoEditBean.getStrCuerpoGer());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoGer());
		}
		
		mantCorreoDao.grabarMantCorreo(mantCorreoEditBean);
	}

	/**
	 *  
	 * Realiza la modificación de correo.
	 *
	 * @param mantCorreoEditBean objeto del tipo MantCorreoEditBean que contiene el registro
	 * @throws Exception Excepción que puede ser lanzada
	 */
	@Override
	public void actualizarMantCorreo(MantCorreoEditBean mantCorreoEditBean) throws Exception {
		if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_RESUMEN)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrUsuCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrPara(mantCorreoEditBean.getStrPara().replace(", ", ","));
			mantCorreoEditBean.setStrCc(mantCorreoEditBean.getStrCc().replace(", ", ","));
			if(mantCorreoEditBean.getStrCc().trim().length()==0){
				mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			}
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoRep());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoRep());
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_PROCESO_AUTOMATICO)){
			mantCorreoEditBean.setStrPara(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCuerpo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoExt());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoExt());
			if(mantCorreoEditBean.getLonRutaDefault() == null){
				mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			}
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_CORREO_FUENTE_ENVIOS)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			
			mantCorreoEditBean.setStrPara(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrCuerpo(Constants.DEFAULT_VARCHAR);
			
			mantCorreoEditBean.setStrAsunto(Constants.DEFAULT_VARCHAR);
			
			// COPIAMOS ATRIBUTO GENERAL
			mantCorreoEditBean.setStrRutaServCorreo(mantCorreoEditBean.getStrRutaServCorreoEnv());
			mantCorreoEditBean.setLonPuerto(mantCorreoEditBean.getLonPuertoEnv());
			mantCorreoEditBean.setStrUsuCorreo(mantCorreoEditBean.getStrUsuCorreoEnv());
			mantCorreoEditBean.setStrPassCorreo(mantCorreoEditBean.getStrPassCorreoEnv());
			
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoEnv());
		}
		else if(mantCorreoEditBean.getStrTipoOperacion().equals(Constants.OPERATION_TYPE_REPORTE_GERENCIA)){
			mantCorreoEditBean.setStrNomAdjunto(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrOrigen(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonPuerto(Constants.DEFAULT_NOMBER_LONG);
			mantCorreoEditBean.setStrUsuCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassCorreo(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrRutaServFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrUsuFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setStrPassFile(Constants.DEFAULT_VARCHAR);
			mantCorreoEditBean.setLonRutaDefault(Constants.DEFAULT_NOMBER_LONG);
			
			// COPIAMOS EN EL ATRIBUTO GENERAL
			mantCorreoEditBean.setStrPara(mantCorreoEditBean.getStrParaGer().replace(", ", ","));
			mantCorreoEditBean.setStrCc(mantCorreoEditBean.getStrCcGer().replace(", ", ","));
			if(mantCorreoEditBean.getStrCcGer().trim().length()==0){
				mantCorreoEditBean.setStrCc(Constants.DEFAULT_VARCHAR);
			}
			mantCorreoEditBean.setStrAsunto(mantCorreoEditBean.getStrAsuntoGer());
			mantCorreoEditBean.setStrCuerpo(mantCorreoEditBean.getStrCuerpoGer());
			mantCorreoEditBean.setStrEstado(mantCorreoEditBean.getStrEstadoGer());
		}
		
		mantCorreoDao.actualizarMantCorreo(mantCorreoEditBean);
	}

	/**
	 *  
	 * Valida si existen configuraciones de correo activas para cada tipo.
	 *
	 * @return the validation data
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo ValidaUnicidadCorreoBean que contiene la información de validación
	 */
	@Override
	public ValidaUnicidadCorreoBean getValidationData() throws Exception {
		return mantCorreoDao.obtenerInformacionValidacion();
	}

}
