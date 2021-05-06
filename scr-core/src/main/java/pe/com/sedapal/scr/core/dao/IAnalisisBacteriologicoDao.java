/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.dao;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.MuestraFirstBean;
import pe.com.sedapal.scr.core.beans.MuestraSecondBean;
import pe.com.sedapal.scr.core.beans.ResultMuestraBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAnalisisBacteriologicoDao.
 */
public interface IAnalisisBacteriologicoDao {

	/**
	 * Obtener datos equipos.
	 *
	 * @param analisisBacteriologicoBean the analisis bacteriologico bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de equipos
	 * @param analisisBacteriologicoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosEquipos(AnalisisBacteriologicoBean analisisBacteriologicoBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener analisis bacteriologico.
	 *
	 * @param id the id
	 * @return the analisis bacteriologico bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene Información de equipo por identificador
	 * @param nid Identificador del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id) throws Exception;
	
	
	/**
	 * Grabar analisis bacteriologico.
	 *
	 * @param analisisBacteriologicoBean the analisis bacteriologico bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de Analisis Bacteriologico
	 * @param analisisBacteriologicoBean objeto del tipo AnalisisBacteriologicoBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception;
	
	
	/**
	 * Actualizar analisis bacteriologico.
	 *
	 * @param analisisBacteriologicoBean the analisis bacteriologico bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la tabla de Analisis Bacteriologico
	 * @param analisisBacteriologicoBean objeto del tipo AnalisisBacteriologicoBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception;
	
	/**
	 * Inactivar analisis bacteriologico.
	 *
	 * @param analisisBacteriologicoBean the analisis bacteriologico bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el cambío de estado de un registro de la tabla de Analisis Bacteriologico
	 * @param analisisBacteriologicoBean objeto del tipo AnalisisBacteriologicoBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void inactivarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception;
	
	/**
	 * Grabar analisis bacteriologico resultado.
	 *
	 * @param resultMuestraBean the result muestra bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de resultado de Analisis Bacteriologico
	 * @param resultMuestraBean objeto del tipo ResultMuestraBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarAnalisisBacteriologicoResultado(ResultMuestraBean resultMuestraBean) throws Exception;
	
	/**
	 * Grabar analisis bacteriologico first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de primera muestra de Analisis Bacteriologico
	 * @param muestraFirstBean objeto del tipo MuestraFirstBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarAnalisisBacteriologicoFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Grabar analisis bacteriologico second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el registro de un registro de la tabla de segunda muestra de Analisis Bacteriologico
	 * @param muestraSecondBean objeto del tipo MuestraSecondBean que contiene el registro  
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	int grabarAnalisisBacteriologicoSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Obtener datos muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de la Primera muestra del Analisis Bacteriologico
	 * @param analisisBacteriologicoBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosMuestraFirst(MuestraFirstBean muestraFirstBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @return the muestra first bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene registro de Primera muestra del Analisis Bacteriologico por identificador
	 * @param muestraFirstBean bean que contiene los identificadores del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */	
	MuestraFirstBean obtenerMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Actualizar muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la primera muestra del Analisis Bacteriologico
	 * @param muestraFirstBean objeto del tipo MuestraFirstBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Obtener datos muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado de la Segunda muestra del Analisis Bacteriologico
	 * @param muestraSecondBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	Result obtenerDatosMuestraSecond(MuestraSecondBean muestraSecondBean, Paginacion paginacion) throws Exception;
	
	/**
	 * Obtener muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @return the muestra second bean
	 * @throws Exception the exception
	 */
	/* 
	 * Obtiene registro de Segunda muestra del Analisis Bacteriologico por identificador
	 * @param muestraSecondBean bean que contiene los identificadores del registro
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public MuestraSecondBean obtenerMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Actualizar muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la modificación de un registro de la segunda muestra del Analisis Bacteriologico
	 * @param muestraSecondBean objeto del tipo MuestraSecondBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	void actualizarMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Obtener datos result muestra.
	 *
	 * @param resultMuestraBean the result muestra bean
	 * @param paginacion the paginacion
	 * @return the result
	 * @throws Exception the exception
	 */
	/*
	 * Método que permite obtener el listado del resultado de la primera y segunda muestra del Analisis Bacteriologico
	 * @param resultMuestraBean Contiene el bean que representa la búsqueda
	 * @param paginacion Representa la página solicitada
	 * @Return Objeto de tipo Result que contiene los resultados
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public Result obtenerDatosResultMuestra(ResultMuestraBean resultMuestraBean, Paginacion paginacion)	throws Exception;
	
	/**
	 * Calcula resultado muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el calculo del resultado de la primera muestra del Analisis Bacteriologico
	 * @param muestraFirstBean objeto del tipo MuestraFirstBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int calculaResultadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Calcula resultado muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el calculo del resultado de la segunda muestra del Analisis Bacteriologico
	 * @param muestraSecondBean objeto del tipo MuestraSecondBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int calculaResultadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Adds the duplicado muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el duplicado del registro selecionado
	 * @param muestraFirstBean objeto del tipo MuestraFirstBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int addDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Delete duplicado muestra first.
	 *
	 * @param muestraFirstBean the muestra first bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la eliminacion del duplicado del registro selecionado
	 * @param muestraFirstBean objeto del tipo MuestraFirstBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int deleteDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception;
	
	/**
	 * Adds the duplicado muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza el duplicado del registro selecionado
	 * @param muestraSecondBean objeto del tipo MuestraSecondBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int addDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Delete duplicado muestra second.
	 *
	 * @param muestraSecondBean the muestra second bean
	 * @return the int
	 * @throws Exception the exception
	 */
	/* 
	 * Realiza la eliminacion del duplicado del registro selecionado
	 * @param muestraSecondBean objeto del tipo MuestraSecondBean que contiene el registro 
	 * @throws Exception Excepción que puede ser lanzada
	 * */
	public int deleteDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception;
	
	/**
	 * Obtiene Información de equipo por identificador.
	 *
	 * @param id the id
	 * @param tipo the tipo
	 * @return the analisis bacteriologico bean
	 * @throws Exception the exception
	 */
	public AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id,String tipo) throws Exception;
}
