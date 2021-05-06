/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.scr.core.beans.DatosEnvioCorreoBean;
import pe.com.sedapal.scr.core.beans.DatosExtraccionCorreoBean;
import pe.com.sedapal.scr.core.common.Constants;
import pe.com.sedapal.scr.core.dao.IMantCorreoDao;
import pe.com.sedapal.scr.core.services.IProcesoService;
import pe.com.sedapal.scr.correo.core.beans.ConfiguracionCorreo;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcesoServiceImpl.
 */
@Service
public class ProcesoServiceImpl implements IProcesoService {
	
	/** The mant correo dao. */
	@Autowired
	private IMantCorreoDao mantCorreoDao;

	/**
	 * Método que permite obtener los datos de configuración del correo a gerencia.
	 *
	 * @return the datos envio correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosEnvioCorreoBean que contiene los datos
	 */
	@Override
	public DatosEnvioCorreoBean obtenerCorreoGerenciaEnviar() throws Exception {
		DatosEnvioCorreoBean correoGerenciaBean = new DatosEnvioCorreoBean();
		correoGerenciaBean.setBolPuedeEnviar(true);
		
		ConfiguracionCorreo configuracionFuente = mantCorreoDao.obtenerConfigCorreo(Constants.OPERATION_TYPE_CORREO_FUENTE_ENVIOS, 
				Constants.ACTIVE_STATE);
		ConfiguracionCorreo configuracionDestino = mantCorreoDao.obtenerConfigCorreo(Constants.OPERATION_TYPE_REPORTE_GERENCIA, 
				Constants.ACTIVE_STATE);
		
		if(configuracionFuente==null){
			correoGerenciaBean.setBolPuedeEnviar(false);
			correoGerenciaBean.setStrRemitente(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
		}
		else{
			correoGerenciaBean.setStrRemitente(configuracionFuente.getCuentaCorreo().getStrCorreo());
		}
		
		if(configuracionDestino==null){
			correoGerenciaBean.setBolPuedeEnviar(false);
			correoGerenciaBean.setStrDestinatarios(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrCopiados(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrAsunto(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrCuerpo(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
		}
		else{
			correoGerenciaBean.setStrDestinatarios(configuracionDestino.getMensajeCorreo().getStrPara());
			correoGerenciaBean.setStrCopiados(configuracionDestino.getMensajeCorreo().getStrConCopia());
			correoGerenciaBean.setStrAsunto(configuracionDestino.getMensajeCorreo().getStrAsunto());
			correoGerenciaBean.setStrCuerpo(configuracionDestino.getMensajeCorreo().getStrContenido());
		}
		
		return correoGerenciaBean;
	}
	
	/**
	 * Método que permite obtener los datos de configuración del correo resumen.
	 *
	 * @return the datos envio correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosEnvioCorreoBean que contiene los datos
	 */
	@Override
	public DatosEnvioCorreoBean obtenerCorreoResumenEnviar() throws Exception {
		DatosEnvioCorreoBean correoGerenciaBean = new DatosEnvioCorreoBean();
		correoGerenciaBean.setBolPuedeEnviar(true);
		
		ConfiguracionCorreo configuracionFuente = mantCorreoDao.obtenerConfigCorreo(Constants.OPERATION_TYPE_CORREO_FUENTE_ENVIOS, 
				Constants.ACTIVE_STATE);
		ConfiguracionCorreo configuracionDestino = mantCorreoDao.obtenerConfigCorreo(Constants.OPERATION_TYPE_REPORTE_RESUMEN, 
				Constants.ACTIVE_STATE);
		
		if(configuracionFuente==null){
			correoGerenciaBean.setBolPuedeEnviar(false);
			correoGerenciaBean.setStrRemitente(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
		}
		else{
			correoGerenciaBean.setStrRemitente(configuracionFuente.getCuentaCorreo().getStrCorreo());
		}
		
		if(configuracionDestino==null){
			correoGerenciaBean.setBolPuedeEnviar(false);
			correoGerenciaBean.setStrDestinatarios(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrCopiados(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrAsunto(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			correoGerenciaBean.setStrCuerpo(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
		}
		else{
			correoGerenciaBean.setStrDestinatarios(configuracionDestino.getMensajeCorreo().getStrPara());
			correoGerenciaBean.setStrCopiados(configuracionDestino.getMensajeCorreo().getStrConCopia());
			correoGerenciaBean.setStrAsunto(configuracionDestino.getMensajeCorreo().getStrAsunto());
			correoGerenciaBean.setStrCuerpo(configuracionDestino.getMensajeCorreo().getStrContenido());
		}
		
		return correoGerenciaBean;
	}
	
	/**
	 * Método que permite obtener los datos de configuración del proceso de extracción de correo.
	 *
	 * @return the datos extraccion correo bean
	 * @throws Exception Excepción que puede ser lanzada
	 * @Return Objeto de tipo DatosExtraccionCorreoBean que contiene los datos
	 */
	@Override
	public DatosExtraccionCorreoBean obtenerCorreoExtraccion() throws Exception {
		DatosExtraccionCorreoBean extraccionCorreoBean = new DatosExtraccionCorreoBean();
		extraccionCorreoBean.setBolPuedeExtraer(true);
		
		ConfiguracionCorreo configuracionExtraccion = mantCorreoDao.obtenerConfigCorreo(Constants.OPERATION_TYPE_PROCESO_AUTOMATICO, 
				Constants.ACTIVE_STATE);
		
		if(configuracionExtraccion==null){
			extraccionCorreoBean.setBolPuedeExtraer(false);
			extraccionCorreoBean.setStrCorreoExtraer(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			extraccionCorreoBean.setStrRemitente(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			extraccionCorreoBean.setStrAsunto(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			extraccionCorreoBean.setStrNombreAdjunto(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
			extraccionCorreoBean.setStrRutaArchivo(Constants.REPORTE_GERENCIA_DATO_NO_CONFIGURADO);
		}
		else{
			extraccionCorreoBean.setStrCorreoExtraer(configuracionExtraccion.getCuentaCorreo().getStrCorreo());
			extraccionCorreoBean.setStrRemitente(configuracionExtraccion.getMensajeCorreo().getStrRemitente());
			extraccionCorreoBean.setStrAsunto(configuracionExtraccion.getMensajeCorreo().getStrAsunto());
			extraccionCorreoBean.setStrNombreAdjunto(configuracionExtraccion.getMensajeCorreo().getStrNombreAdjuntoConfig());
			extraccionCorreoBean.setStrRutaArchivo(configuracionExtraccion.getServidorArchivos().getStrUrl());
		}
		
		return extraccionCorreoBean;
	}

}
