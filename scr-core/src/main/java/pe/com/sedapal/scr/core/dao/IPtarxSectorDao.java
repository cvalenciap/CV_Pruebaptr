package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.PtarxSectorBean;

public interface IPtarxSectorDao {
		
		List<PtarxSectorBean> obtenerPtarxSector() throws GmdException;
		
		List<PtarxSectorBean> obtenerPtarxSectorTodo() throws GmdException;
		
		void anularPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException;
		
		void actualizarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException;
		
		PtarxSectorBean registrarPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException;
		
		Integer validaPtarxSector(PtarxSectorBean ptarxSectorBean) throws GmdException;
		
		List<PtarxSectorBean> obtenerNoPtarxSector() throws GmdException;
}
