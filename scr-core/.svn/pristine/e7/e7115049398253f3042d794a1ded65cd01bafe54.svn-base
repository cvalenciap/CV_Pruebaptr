/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.sedapal.scr.core.beans.BaseBean;
import pe.com.sedapal.scr.core.beans.CeldaMatrizBean;
import pe.com.sedapal.scr.core.exception.ValidationFileException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICaudalDetalleService.
 */
public interface ICaudalDetalleService {
	
	/**
	 * Método que permite registrar el detalle de un caudal.
	 *
	 * @param detalle representa la información de caudales
	 * @param caudalId es el identificador del caudal del cual se registrará su detalle
	 * @param audit representa la información de auditoría para la modificación en base de datos
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void registrarCaudalDetalle(List<List<CeldaMatrizBean>> detalle, Integer caudalId, BaseBean audit) throws Exception;
	
	/**
	 * Método que permite validar la información de caudales para la carga manual.
	 *
	 * @param datosExcel Contiene la información cargada por el usuario en archivo XLS
	 * @param periodoCarga Es el periodo (en nivel de mes) para el cual se carga el detalle
	 * @throws ValidationFileException Excepción de validación
	 * @throws Exception Excepción que puede ser lanzada
	 */
	void validaInformacionUploaded(List<List<String>> datosExcel, String periodoCarga) throws ValidationFileException, Exception;
	
	/**
	 * Método que permite validar la extensión del archivo de carga manual.
	 *
	 * @param strFileName Nombre completo del archivo a cargar
	 * @throws ValidationFileException Excepción de validación
	 */
	void validaExtensionFile(String strFileName) throws ValidationFileException;
}
