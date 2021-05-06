package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.DetalleGeneralBean;

public interface IDetalleGeneralService {
	
	List<DetalleGeneralBean> obtenerDetalleGeneral(int idGeneral) throws GmdException;
	
	List <DetalleGeneralBean> obtenerDetalleGeneralI(int idGeneral) throws GmdException;
	
	List<DetalleGeneralBean> obtenerDetalleGeneralTodo(int idGeneral) throws GmdException;
	
	void anularDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException;
	
	void actualizarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException;
	
	DetalleGeneralBean registrarDetalleGeneral(DetalleGeneralBean detalleGeneralBean) throws GmdException;
	
	List <DetalleGeneralBean> obtenerDetalleGeneralMicroorganismo(int idGeneral, int parametroMicro) throws GmdException;
	
	List<DetalleGeneralBean> obtenerDetalleGeneralbyidDetalle(Integer idDetalleGeneral) throws GmdException;
	
	List<DetalleGeneralBean> obtenerListaPuntoMuestraES(int idPuntoMuestra) throws GmdException;
	
	List<DetalleGeneralBean> obtenerListaMicroHidro(Integer idSubParametro) throws GmdException;
	
	List<DetalleGeneralBean> obtenerListaMicroPara(Integer idSubParametro) throws GmdException;
}
