package pe.com.sedapal.scr.core.dao;

import java.text.ParseException;
import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;
import pe.com.sedapal.scr.core.beans.FisicoQuimicoBean;

public interface IFisicoQuimicoDao {
	
	List<FisicoQuimicoBean> obtenerLtaFisicoQuimico(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException, ParseException;

	List<FisicoQuimicoBean> obtenerLtaFisicoQuimicoTodo(Integer idPtarxSector,String fechaInicio,String fechaFin) throws GmdException , ParseException;
	
    void anularFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	void actualizarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	FisicoQuimicoBean registrarFisicoQuimico(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;
	
	List<FisicoQuimicoBean> obtenerLtaFisicoQuimicobyFilter(FisicoQuimicoBean fisicoQuimicoBean) throws GmdException;

	List<DetalleGeneralBean> obtenerFechasFisicoQuimicoTodo(Integer idPtarxSerctor) throws GmdException;
	
	List<DetalleGeneralBean> obtenerFechasFisicoQuimico(Integer idPtarxSerctor) throws GmdException;
}
