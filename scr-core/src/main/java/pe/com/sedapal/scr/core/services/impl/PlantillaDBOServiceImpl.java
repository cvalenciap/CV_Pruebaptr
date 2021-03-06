package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.BaseSptarBean;
import pe.com.sedapal.scr.core.beans.PlantillaDBOBean;
import pe.com.sedapal.scr.core.beans.PuntoMuestraPtarSectorBean;
import pe.com.sedapal.scr.core.beans.RegistroDBOBean;
import pe.com.sedapal.scr.core.common.ConstantesUtil;
import pe.com.sedapal.scr.core.dao.IPlantillaDBODao;
import pe.com.sedapal.scr.core.services.IPlantillaDBOService;
import pe.com.sedapal.scr.core.util.ParametrosUtil;

@Service
public class PlantillaDBOServiceImpl implements IPlantillaDBOService {
	
	/** The generalDao dao. */
	@Autowired
	private IPlantillaDBODao plantillaDBODao;

	@Override
	public List<PlantillaDBOBean> obtenerRegistroPlantillaDBO(Integer idPtarxSector) throws GmdException {
		List<PlantillaDBOBean> ltaDetalleGeneral = new ArrayList<PlantillaDBOBean>();
        try {        	
        	ltaDetalleGeneral = plantillaDBODao.obtenerRegistroPlantillaDBO(idPtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularPlantillaDBO(PlantillaDBOBean bean) throws GmdException {
		try {        	
			plantillaDBODao.anularPlantillaDBO(bean);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public PlantillaDBOBean registrarPlantillaDBO(PlantillaDBOBean registro) throws GmdException {
		try {        	
			registro = plantillaDBODao.registrarPlantillaDBO(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return registro;
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarPlantillaDBO(PlantillaDBOBean registro) throws GmdException {
		try {        	
			plantillaDBODao.actualizarPlantillaDBO(registro);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	public List<PuntoMuestraPtarSectorBean> obtenerltaPuntoMuestraFaltante(Integer PtarxSector) throws GmdException {
		List<PuntoMuestraPtarSectorBean> ltaDetalleGeneral = new ArrayList<PuntoMuestraPtarSectorBean>();
        try {        	
        	ltaDetalleGeneral = plantillaDBODao.obtenerltaPuntoMuestraFaltante(PtarxSector);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void registrarListaFaltantes(List<PlantillaDBOBean> ltaIngresosFaltantes, BaseSptarBean auditoria) throws GmdException {
		try {
			for (PlantillaDBOBean registroPlantillaDBO : ltaIngresosFaltantes) {
				registroPlantillaDBO.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
				registroPlantillaDBO.setIdUsuaCrea(auditoria.getIdUsuaCrea());
				registroPlantillaDBO.setDeTermCrea(auditoria.getDeTermCrea());
				PlantillaDBOBean registro = plantillaDBODao.registrarPlantillaDBO(registroPlantillaDBO);
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
	@Override
	public List<PlantillaDBOBean> obtenerRegistrosPlantillaSobrante(Integer idPtarSector) throws GmdException{
		List<PlantillaDBOBean> ltaRegistrosPlantillaDBO = new ArrayList<PlantillaDBOBean>();
		try {
			ltaRegistrosPlantillaDBO = plantillaDBODao.obtenerRegistrosPlantillaSobrante(idPtarSector);
		}catch(Exception excepcion) {
			throw new GmdException(excepcion);
		}
		return ltaRegistrosPlantillaDBO;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void eliminarListaSobrantes(List<PlantillaDBOBean> ltaRegistrosSobrantes, BaseSptarBean auditoria) throws GmdException {
		try {
			for(PlantillaDBOBean registroPlantillaDBO : ltaRegistrosSobrantes) {
				registroPlantillaDBO.setStRegi(auditoria.getStRegi());
				registroPlantillaDBO.setIdUsuaModi(auditoria.getIdUsuaModi());
				registroPlantillaDBO.setDeTermModi(auditoria.getDeTermModi());
				if(registroPlantillaDBO.getIndicePadre() == ConstantesUtil.INDICE_PADRE) {
					anularPlantillaDBO(registroPlantillaDBO);
				}
			}
		}catch (Exception excepcion) {
			throw new GmdException(excepcion);
		}
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void modificarRegistrosAlterados(List<PlantillaDBOBean> ltaRegistrosSobrantes, List<PlantillaDBOBean> ltaIngresosFaltantes, BaseSptarBean auditoria, Integer idPtarSector) throws GmdException{
		try {
			if(ltaRegistrosSobrantes != null && ltaRegistrosSobrantes.size() > 0) {
				for(PlantillaDBOBean registroPlantillaDBO : ltaRegistrosSobrantes) {
					registroPlantillaDBO.setIdPtarxSector(idPtarSector);
					registroPlantillaDBO.setStRegi(auditoria.getStRegi());
					registroPlantillaDBO.setIdUsuaModi(auditoria.getIdUsuaModi());
					registroPlantillaDBO.setDeTermModi(auditoria.getDeTermModi());
					if(registroPlantillaDBO.getIndicePadre() == ConstantesUtil.INDICE_PADRE) {
						anularPlantillaDBO(registroPlantillaDBO);
					}
				}
			}
			if(ltaIngresosFaltantes != null && ltaIngresosFaltantes.size() > 0) {
				for (PlantillaDBOBean registroPlantillaDBO : ltaIngresosFaltantes) {
					registroPlantillaDBO.setStRegi(ConstantesUtil.ST_REGI_ACTIVO);
					registroPlantillaDBO.setIdUsuaCrea(auditoria.getIdUsuaModi());
					registroPlantillaDBO.setDeTermCrea(auditoria.getDeTermModi());
					PlantillaDBOBean registro = plantillaDBODao.registrarPlantillaDBO(registroPlantillaDBO);
				}
			}
		} catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
	}
	
}
