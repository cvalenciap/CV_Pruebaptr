/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.sedapal.scr.core.beans.SelectItemBean;
import pe.com.sedapal.scr.core.common.ConstantsLaboratorio;
import pe.com.sedapal.scr.core.services.ICatalogoService;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemsController.
 *
 * @author Mijail Aymara
 */
@Controller
@PropertySources(value = { @PropertySource(name = "props", value = {
		"classpath:application.properties" }, ignoreResourceNotFound = true) })
public class ItemsController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);

	/** The env. */
	@Autowired
	Environment env;

	/** The message source. */
	@Autowired
	MessageSource messageSource;

	/** The catalogo service. */
	@Autowired
	ICatalogoService catalogoService;
	
	/**
	 * Find areas.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listAreas", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findAreas() throws Exception {
		
		logger.debug("finding all distritos");
		return this.catalogoService.obtenerCatalogo(1);
	}
	
	/**
	 * Findl lambdas.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listLambdas", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findlLambdas() throws Exception {
		
		logger.debug("finding all distritos");
		return this.catalogoService.obtenerCatalogo(2);
	}
	
	/**
	 * Find actividad.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listActividades", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findActividad() throws Exception {
		logger.debug("finding all actividades");
		return this.catalogoService.obtenerCatalogo(2);
	}
	
	/**
	 * Find sub actividades.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listSubactividades", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findSubActividades() throws Exception {
		logger.debug("finding all actividades");
		return this.catalogoService.obtenerCatalogo(3);
	}
	
	/**
	 * Find puntos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPuntos", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findPuntos() throws Exception {
		logger.debug("finding all puntos");
		return this.catalogoService.obtenerCatalogo(6);
	}
	
	/**
	 * Find grupo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listGrupo", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findGrupo() throws Exception {
		
		logger.debug("finding all Grupos");
		return this.catalogoService.obtenerCatalogo(1);
	}
	
	/**
	 * Find ubicacion.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listComboUbicacion", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findUbicacion() throws Exception {
		
		logger.debug("finding all Ubicacion");
		return this.catalogoService.obtenerCatalogo(13);
	}
	
	/**
	 * Find meses.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listComboMeses", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findMeses() throws Exception {
		
		logger.debug("finding all Meses");
		return this.catalogoService.obtenerCatalogo(12,ConstantsLaboratorio.COLUMN_SEQUENCE_ORDER_DETCATALOGO);
	}
	
	/**
	 * Find unidad medida.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listUnidadMedida", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findUnidadMedida() throws Exception {
		
		logger.debug("finding all Meses");
		return this.catalogoService.obtenerCatalogo(20,ConstantsLaboratorio.COLUMN_SEQUENCE_ORDER_DETCATALOGO);
	}
	
	/**
	 * Find parametros.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listParametros", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findParametros() throws Exception {
		
		logger.debug("finding all Parametros");
		
		return this.catalogoService.obtenerCatalogo(4);
	}
	
	/**
	 * Find persona muestrea.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPersonaMuestrea", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findPersonaMuestrea() throws Exception {
		
		logger.debug("finding all PersonaMuestrea");
		System.out.println("consultando Personas q muestrean");
		return this.catalogoService.obtenerCatalogoSeleccionar(10,2);
	}
	
	/**
	 * Find persona recepcion.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPersonaRecepcion", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findPersonaRecepcion() throws Exception {
		
		logger.debug("finding all PersonaRecepcion");
		System.out.println("consultando Persona q recepciona");
		return this.catalogoService.obtenerCatalogoSeleccionar(11,2);
	}
	
	/**
	 * Find especialistas.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listEspecialistas", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findEspecialistas() throws Exception {
		
		logger.debug("finding all Especialistas");
		
		return this.catalogoService.obtenerCatalogoSeleccionar(14,2);
	}
	
	/**
	 * Find tipo frasco.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoFrasco", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findTipoFrasco() throws Exception {
		
		logger.debug("finding all listTipoFrasco");
		
		return this.catalogoService.obtenerCatalogo(9);
	}
	
	/**
	 * Find tipo muestra.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoMuestra", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findTipoMuestra() throws Exception {
		
		logger.debug("finding all listTipoMuestra");
		
		return this.catalogoService.obtenerCatalogo(7);
	}
	
	/**
	 * Find tipo preservacion.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoPreservacion", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findTipoPreservacion() throws Exception {
		
		logger.debug("finding all listTipoPreservacion");
		
		return this.catalogoService.obtenerCatalogo(15);
	}
	
	/**
	 * Find puntos 2.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPuntos2", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> findPuntos2() throws Exception {
		logger.debug("finding all actividades");
		return this.catalogoService.obtenerCatalogoSeleccionar(6,2);
	}
	
	/**
	 * List tipo ensayo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoEnsayo", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listTipoEnsayo() throws Exception {
		logger.debug("finding all Metodo de Ensayo");
		return this.catalogoService.obtenerCatalogoSeleccionar(16,2);
	}
	
	/**
	 * List tipo ensayo 2.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoEnsayo2", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listTipoEnsayo2() throws Exception {
		logger.debug("finding all Metodo de Ensayo");
		return this.catalogoService.obtenerCatalogo(16);
	}
	
	/**
	 * List analistas.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listAnalistas", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listAnalistas() throws Exception {
		
		logger.debug("finding all listAnalistas");
		return this.catalogoService.obtenerCatalogo(11);
	}
	
	/**
	 * List muestreadores.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listMuestreadores", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listMuestreadores() throws Exception {
		
		System.out.println("consultando listMuestreadores");
		return this.catalogoService.obtenerCatalogo(10);
	}
	
	/**
	 * List turnos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTurnos", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listTurnos() throws Exception {
		
		System.out.println("consultando turnos");
		return this.catalogoService.obtenerCatalogo(17);
	}
	
	/**
	 * List puntos ensayo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listPuntosEnsayo", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listPuntosEnsayo() throws Exception {
		
		System.out.println("consultando listPuntosEnsayo");
		return this.catalogoService.obtenerCatalogo(26);
	}

	/**
	 * List puntos ensayo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listTipoAnalisis", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listTipoAnalisis() throws Exception {
		
		System.out.println("consultando listTipoAnalisis");
		return this.catalogoService.obtenerCatalogo(8);
	}
	
	/**
	 * List puntos ensayo.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/listFrascos", method = RequestMethod.GET)
	public @ResponseBody
	List<SelectItemBean> listFrascos() throws Exception {
		
		System.out.println("consultando listTipoAnalisis");
		return this.catalogoService.obtenerCatalogo(9);
	}
	
	
	
}
