package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;

public interface IFisicoQuimicoService {
	
	List<FisicoQuimicoBean> obtenerLtaFisicoQuimico(Integer idPtarxSerctor,String fechaInicio,String fechaFin) throws GmdException;
	
	List<FisicoQuimicoBean> obtenerLtaFisicoQuimicoTodo(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException;

    void anularFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	void actualizarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	FisicoQuimicoBean registrarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	List<FisicoQuimicoBean> obtenerLtaFisicoQuimicobyFilter(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	void reCalcularRegistrosFisicoQuimico(Integer idPtarxSector,Integer idPuntoMuestra , Double Volumen,BaseSptarBean auditoria,String fechaInicio,String fechaFin) throws GmdException;
	
	List<DetalleGeneralBean> obtenerFechasFisicoQuimicoTodo(Integer idPtarxSerctor) throws GmdException;
	
	List<DetalleGeneralBean> obtenerFechasFisicoQuimico(Integer idPtarxSerctor) throws GmdException;
	
	void registarCopiaLtaFisicoQuimicoTodo(List<FisicoQuimicoBean> ltaDataCopiar, Integer idPtarxSector,String fechaInicio,String fechaFin,BaseSptarBean auditoria) throws GmdException;
}
