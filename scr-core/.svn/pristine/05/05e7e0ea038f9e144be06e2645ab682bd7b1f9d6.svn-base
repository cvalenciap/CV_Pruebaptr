/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.common.core.beans.Paginacion;
import pe.com.sedapal.common.core.beans.Result;
import pe.com.sedapal.scr.core.beans.AlmacenamientoPTAPFirstBean;
import pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean;
import pe.com.sedapal.scr.core.beans.MuestraFirstBean;
import pe.com.sedapal.scr.core.beans.MuestraSecondBean;
import pe.com.sedapal.scr.core.beans.ReporteAlmacenamientoPTAPBean;
import pe.com.sedapal.scr.core.beans.ResultMuestraBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.dao.IAlmacenamientoPTAPDao;
import pe.com.sedapal.scr.core.dao.IAnalisisBacteriologicoDao;
import pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisBacteriologicoServiceImpl.
 */
@Service
public class AnalisisBacteriologicoServiceImpl implements IAnalisisBacteriologicoService{

	/** The analisis bacteriologic dao. */
	@Autowired
	private IAnalisisBacteriologicoDao analisisBacteriologicDao;
	
	/** The almacenamiento PTAP dao. */
	@Autowired
	private IAlmacenamientoPTAPDao almacenamientoPTAPDao;
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerDatosEquipos(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosEquipos(AnalisisBacteriologicoBean analisisBacteriologicoBean, Paginacion paginacion)	throws Exception {
		// TODO Auto-generated method stub
		return analisisBacteriologicDao.obtenerDatosEquipos(analisisBacteriologicoBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerAnalisisBacteriologico(java.lang.Integer)
	 */
	@Override
	public AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id) throws Exception {
		
		return analisisBacteriologicDao.obtenerAnalisisBacteriologico(id);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#grabarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	@Transactional
	public int grabarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		
		int idCabecera = analisisBacteriologicDao.grabarAnalisisBacteriologico(analisisBacteriologicoBean);
		
		
		String dessMuestra = "";
		
		if(analisisBacteriologicoBean.getIntIdForm() == ConstantsLaboratorio.PAR_NID_FORM_ANALISIS_BACTERIOLOGICO_BOCATOMA){
			//INICIO PRIMERA MUESTRA RESULTADO
			ResultMuestraBean resultMuestraBean = new ResultMuestraBean();
			resultMuestraBean.setUsuarioCreacion(analisisBacteriologicoBean.getUsuarioCreacion());
			resultMuestraBean.setUsuarioModificacion(analisisBacteriologicoBean.getUsuarioCreacion());
			resultMuestraBean.setPrograma(analisisBacteriologicoBean.getPrograma());
			resultMuestraBean.setIntIdCabecera(idCabecera);			
			resultMuestraBean.setIntEstado(ConstantsLaboratorio.ACTIVO);
			
			dessMuestra = "BOCATOMA 1";		
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.PRIMERA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);			
		
			dessMuestra = "BOCATOMA 2";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.PRIMERA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);			
		
			dessMuestra = "DUPLICADO";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.PRIMERA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);	
			//FIN SEGUNDA MUESTRA RESULTADO
			
			
			//INICIO SEGUNDA MUESTRA RESULTADO
			dessMuestra = "ENTRADA PLANTA 1";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "ENTRADA PLANTA 2";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "DEC. PLANTA 1";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "DEC. PLANTA 2";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "PLANTA CONVENCIONAL 1";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "FILTRADA PLANTA 1";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "FILTRADA PLANTA 2";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "DUPLICADO";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			
			dessMuestra = "BLANCO";
			resultMuestraBean.setStrDesMuestra(dessMuestra);
			resultMuestraBean.setIntTipo(ConstantsLaboratorio.SEGUNDA_MUESTRA);
			analisisBacteriologicDao.grabarAnalisisBacteriologicoResultado(resultMuestraBean);
			//FIN SEGUNDA MUESTRA RESULTADO
			
			
			/**INICIO PRIMERA MUESTRA**/
			for(int i=1; i<8;i++){
				MuestraFirstBean  muestraFirstBean  = new MuestraFirstBean();
				muestraFirstBean.setUsuarioCreacion(analisisBacteriologicoBean.getUsuarioCreacion());
				muestraFirstBean.setUsuarioModificacion(analisisBacteriologicoBean.getUsuarioCreacion());
				muestraFirstBean.setPrograma(analisisBacteriologicoBean.getPrograma());
				
				muestraFirstBean.setIntIdCabecera(idCabecera);		
				muestraFirstBean.setIntEstado(ConstantsLaboratorio.ACTIVO);
				muestraFirstBean.setIntNroOrden(i);
				if(i<4){
					dessMuestra = "BOCATOMA 1";
					muestraFirstBean.setStrDescMuestra(dessMuestra);			
				}else if(i>3 && i<7){
					dessMuestra = "BOCATOMA 2";
					muestraFirstBean.setStrDescMuestra(dessMuestra);
					
				}else if(i>6 && i<8){
					dessMuestra = "DUPLICADO";
					muestraFirstBean.setStrDescMuestra(dessMuestra);
				}		
				analisisBacteriologicDao.grabarAnalisisBacteriologicoFirst(muestraFirstBean);
			}
			/**FIN PRIMERA MUESTRA**/
	
			/**INICIO SEGUNDA MUESTRA**/
			MuestraSecondBean muestraSecondBean = new MuestraSecondBean();
			
			muestraSecondBean.setUsuarioCreacion(analisisBacteriologicoBean.getUsuarioCreacion());
			muestraSecondBean.setUsuarioModificacion(analisisBacteriologicoBean.getUsuarioCreacion());
			muestraSecondBean.setPrograma(analisisBacteriologicoBean.getPrograma());
			
			muestraSecondBean.setIntIdCabecera(idCabecera);
			muestraSecondBean.setIntEstado(ConstantsLaboratorio.ACTIVO);
			
			muestraSecondBean.setStrDescMuestra("ENTRADA PLANTA 1");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("ENTRADA PLANTA 2");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("DEC. PLANTA 1");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("DEC. PLANTA 2");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("PLANTA CONVENCIONAL 1");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("FILTRADA PLANTA 1");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("FILTRADA PLANTA 2");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("DUPLICADO");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			
			muestraSecondBean.setStrDescMuestra("BLANCO");
			analisisBacteriologicDao.grabarAnalisisBacteriologicoSecond(muestraSecondBean);
			/**FIN SEGUNDA MUESTRA**/
		}else if(analisisBacteriologicoBean.getIntIdForm() == ConstantsLaboratorio.PAR_NID_FORM_ALMACENAMIENTO_LTAP){
			//UNO
			AlmacenamientoPTAPFirstBean first = new AlmacenamientoPTAPFirstBean();
			first.setUsuarioCreacion(analisisBacteriologicoBean.getUsuarioCreacion());
			first.setUsuarioModificacion(analisisBacteriologicoBean.getUsuarioCreacion());
			first.setPrograma(analisisBacteriologicoBean.getPrograma());
			
			first.setIntIdCabecera(idCabecera);
			
			first.setStrDescMuestra("Salida de Menacho 1,2,3");
			almacenamientoPTAPDao.grabarMuestraForm21(first);

			first.setStrDescMuestra("Salida de Vicentelo 1,2,3,4");
			almacenamientoPTAPDao.grabarMuestraForm21(first);
			
			first.setStrDescMuestra("Salida de Reservorio Vicentelo N° 5");
			almacenamientoPTAPDao.grabarMuestraForm21(first);
			
			first.setStrDescMuestra("Salida P-2 Cámara de regulación y distribución");
			almacenamientoPTAPDao.grabarMuestraForm21(first);
			
			first.setStrDescMuestra("BLANCO");
			almacenamientoPTAPDao.grabarMuestraForm21(first);
			
			//DOS
			ReporteAlmacenamientoPTAPBean reporteAlmacenamientoPTAPBean = new ReporteAlmacenamientoPTAPBean();
			reporteAlmacenamientoPTAPBean.setUsuarioCreacion(analisisBacteriologicoBean.getUsuarioCreacion());
			reporteAlmacenamientoPTAPBean.setUsuarioModificacion(analisisBacteriologicoBean.getUsuarioCreacion());
			reporteAlmacenamientoPTAPBean.setPrograma(analisisBacteriologicoBean.getPrograma());
			
			reporteAlmacenamientoPTAPBean.setIntIdCabecera(idCabecera);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("COLIFORMES TOTALES UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo("0 UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad("0 UFC/100mL");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("COLIFORMES TERMOTOLERANTES UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo("0 UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad("0 UFC/100mL");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("ESCHERICHIA coli UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo("0 UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad("-");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("BACTERIAS HETEROTROFICAS UFC/mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo("500 UFC/mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad("-50 UFC/100mL");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("PSEUDOMONAS EURUGINOSA UFC/100mL");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo("-");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad("<10 UFC/100mL");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
			
			reporteAlmacenamientoPTAPBean.setStrDesMuestra("CODIGO DE LA MUESTRA");
			reporteAlmacenamientoPTAPBean.setStrLimiteMaximo(" ");
			reporteAlmacenamientoPTAPBean.setStrLimiteCalidad(" ");
			almacenamientoPTAPDao.grabarReporteAlmacenamientoPTAP(reporteAlmacenamientoPTAPBean);
		}
		
		return idCabecera;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#actualizarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void actualizarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		analisisBacteriologicDao.actualizarAnalisisBacteriologico(analisisBacteriologicoBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#inactivarAnalisisBacteriologico(pe.com.sedapal.scr.core.beans.AnalisisBacteriologicoBean)
	 */
	@Override
	public void inactivarAnalisisBacteriologico(AnalisisBacteriologicoBean analisisBacteriologicoBean) throws Exception {
		 analisisBacteriologicDao.inactivarAnalisisBacteriologico(analisisBacteriologicoBean);		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerDatosMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosMuestraFirst(MuestraFirstBean muestraFirstBean, Paginacion paginacion) throws Exception {
		return analisisBacteriologicDao.obtenerDatosMuestraFirst(muestraFirstBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public MuestraFirstBean obtenerMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		return analisisBacteriologicDao.obtenerMuestraFirst(muestraFirstBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#actualizarMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public void actualizarMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		analisisBacteriologicDao.actualizarMuestraFirst(muestraFirstBean);
		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerDatosMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosMuestraSecond(MuestraSecondBean muestraSecondBean, Paginacion paginacion)	throws Exception {
		return analisisBacteriologicDao.obtenerDatosMuestraSecond(muestraSecondBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public MuestraSecondBean obtenerMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		return analisisBacteriologicDao.obtenerMuestraSecond(muestraSecondBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#actualizarMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public void actualizarMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		analisisBacteriologicDao.actualizarMuestraSecond(muestraSecondBean);		
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerDatosResultMuestra(pe.com.sedapal.scr.core.beans.ResultMuestraBean, pe.com.sedapal.common.core.beans.Paginacion)
	 */
	@Override
	public Result obtenerDatosResultMuestra(ResultMuestraBean resultMuestraBean, Paginacion paginacion)	throws Exception {
		return analisisBacteriologicDao.obtenerDatosResultMuestra(resultMuestraBean, paginacion);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#calculaResultadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	@Transactional
	public int calculaResultadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		String dessMuestra="";
		int result = -1;
		
		dessMuestra = "BOCATOMA 1";
		muestraFirstBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraFirst(muestraFirstBean);
		
		if(result == 0){
		
			dessMuestra = "BOCATOMA 2";
			muestraFirstBean.setStrDescMuestra(dessMuestra);
			result = analisisBacteriologicDao.calculaResultadoMuestraFirst(muestraFirstBean);
		}
			
		/*if(result == 0){
			dessMuestra = "DUPLICADO";
			muestraFirstBean.setStrDescMuestra(dessMuestra);
			result = analisisBacteriologicDao.calculaResultadoMuestraFirst(muestraFirstBean);			
		}*/
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#calculaResultadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	@Transactional
	public int calculaResultadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		String dessMuestra="";
		int result = -1;
		
		dessMuestra = "ENTRADA PLANTA 1";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "ENTRADA PLANTA 2";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "DEC. PLANTA 1";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "DEC. PLANTA 2";		
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "PLANTA CONVENCIONAL 1";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "FILTRADA PLANTA 1";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "FILTRADA PLANTA 2";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		dessMuestra = "DUPLICADO";
		muestraSecondBean.setStrDescMuestra(dessMuestra);
		result = analisisBacteriologicDao.calculaResultadoMuestraSecond(muestraSecondBean);
		if(result != 0){
			return result;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#addDuplicadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int addDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		return analisisBacteriologicDao.addDuplicadoMuestraFirst(muestraFirstBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#deleteDuplicadoMuestraFirst(pe.com.sedapal.scr.core.beans.MuestraFirstBean)
	 */
	@Override
	public int deleteDuplicadoMuestraFirst(MuestraFirstBean muestraFirstBean) throws Exception {
		return analisisBacteriologicDao.deleteDuplicadoMuestraFirst(muestraFirstBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#addDuplicadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int addDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		return analisisBacteriologicDao.addDuplicadoMuestraSecond(muestraSecondBean);
	}

	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#deleteDuplicadoMuestraSecond(pe.com.sedapal.scr.core.beans.MuestraSecondBean)
	 */
	@Override
	public int deleteDuplicadoMuestraSecond(MuestraSecondBean muestraSecondBean) throws Exception {
		return analisisBacteriologicDao.deleteDuplicadoMuestraSecond(muestraSecondBean);
	}
	
	/* (non-Javadoc)
	 * @see pe.com.sedapal.scr.core.services.IAnalisisBacteriologicoService#obtenerAnalisisBacteriologico(java.lang.Integer, java.lang.String)
	 */
	@Override
	public AnalisisBacteriologicoBean obtenerAnalisisBacteriologico(Integer id,String tipo) throws Exception{
		return analisisBacteriologicDao.obtenerAnalisisBacteriologico(id, tipo);
	}

}
