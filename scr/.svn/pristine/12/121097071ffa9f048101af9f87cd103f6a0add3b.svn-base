/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.web.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pe.com.sedapal.common.core.utils.ConstantsCommon;
import pe.com.sedapal.common.core.utils.CoreUtils;
import pe.com.sedapal.scr.core.beans.AnalistaBean;
import pe.com.sedapal.scr.core.beans.MenuOption;
import pe.com.sedapal.scr.web.util.JsonUtil;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(CoreUtils.class);

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Gets the menu options.
	 *
	 * @param lista the lista
	 * @return the menu options
	 */
	@SuppressWarnings("unused")
	public static List<MenuOption> getMenuOptions(List<SistemaModuloOpcionBean> lista) {
		/*
		log.info("### MENUS FORMULARIOS SIZE ### {}", lista.size());
		log.info("### CREANDO VARIABLE MENUS ###");
		
		List<MenuOption> listaCompleta = new ArrayList<>();
		
		log.info("### CONVIRTIENDO LISTA GENERICA NUEVA LISTA ###");
		
		for (SistemaModuloOpcionBean fila : lista) {
			MenuOption menu = new MenuOption();
			BeanUtils.copyProperties(fila, menu);
			listaCompleta.add(menu);
		}
		
		log.info("### NUEVA LISTA SIZE ### {}", listaCompleta.size());
		
		List<MenuOption> retorno = llenarHijos(listaCompleta, new MenuOption());
		
		log.info("### NUEVO RETORNO ### {}", retorno.size());
		
		log.info("### LLENANDO NUESTRO LISTADO DE NIVEL UNO ###");
		
		List<MenuOption> listaNivelUno = new ArrayList<>();
		
		for (SistemaModuloOpcionBean sistemaModuloOpcionBean : lista) {
			if (sistemaModuloOpcionBean.getNivelFormulario() == 1) {
				MenuOption menuOption = new MenuOption();
				BeanUtils.copyProperties(sistemaModuloOpcionBean, menuOption);
				listaNivelUno.add(menuOption);
			}
		}
		
		log.info("### LLENANDO LOS HIJOS A NUEVOS LISTADO NIVEL UNO ###");*/
		
		int nivel = 1;
		
		List<MenuOption> padres = new ArrayList<>();
		List<MenuOption> menus = new ArrayList<>();

		for (int i = 1; i < 4; i++) {
			for (SistemaModuloOpcionBean sistemaModuloOpcionBean : lista) {
				if (sistemaModuloOpcionBean.getNivelFormulario() == i) {
					MenuOption menuOption = new MenuOption();
					BeanUtils.copyProperties(sistemaModuloOpcionBean, menuOption);
					menus.add(menuOption);
					if (menuOption.getMenuOptionsHijos() == null) {
						menuOption.setMenuOptionsHijos(new ArrayList<MenuOption>());
					}
					if (i == 1) {
						padres.add(menuOption);
					} else {
						//obtenerPadre(padres, menuOption.getCodModulo(), menuOption.getCodFormularioPadre()).getMenuOptionsHijos().add(menuOption);}
						for (MenuOption mo : menus) {
							if (menuOption.getCodFormularioPadre().intValue() == mo.getCodFormulario().intValue() && 
								menuOption.getCodModulo().intValue() == mo.getCodModulo().intValue()) {
								mo.getMenuOptionsHijos().add(menuOption);
								break;
							}
						}
					}
					log.info("### LLENANDO PRIMER NIVEL {} ### {}", i, menuOption);
				}
				
			}		
		}
		
		Collections.sort(padres, new Comparator<MenuOption>() {
			@Override
			public int compare(MenuOption o1, MenuOption o2) {
				return o1.getCodModulo().compareTo(o2.getCodModulo());
			}
		});
		
		/*for (MenuOption padre : padres) {
			List<MenuOption> hijos = new ArrayList<>();
			for (SistemaModuloOpcionBean menu : lista) {
				if (menu.getCodFormularioPadre().intValue() == padre.getCodFormulario().intValue() && 
					menu.getCodModulo().intValue() == padre.getCodModulo().intValue() &&
					menu.getNivelFormulario() > nivel) {
					MenuOption menuOption = new MenuOption();
					BeanUtils.copyProperties(menu, menuOption);
					hijos.add(menuOption);
				}
			}
			
			padre.setMenuOptionsHijos(hijos);
		}*/
		
		/*while(hayFilaConNivel(nivel, lista)) {
			
			
			
			nivel++;
		}*/

		return padres;

	}
	
	/**
	 * Obtener padre.
	 *
	 * @param padres the padres
	 * @param modulo the modulo
	 * @param codFormularioPadre the cod formulario padre
	 * @return the menu option
	 */
	@SuppressWarnings("unused")
	private static MenuOption obtenerPadre(List<MenuOption> padres, Integer modulo, Integer codFormularioPadre) {
		MenuOption mo = new MenuOption();
		for (MenuOption padre : padres) {
			if (codFormularioPadre == padre.getCodFormulario() && modulo == padre.getCodModulo()) {
				mo = padre;
				break;
			} else {
				mo = obtenerPadre(padre.getMenuOptionsHijos(), modulo, codFormularioPadre);
			}
		}
		return mo;
	}
	
	// lista, null
	// lista, codForm=1, codFormPadre=0, codModulo=9
	
	
	/**
	 * Llenar hijos.
	 *
	 * @param lista the lista
	 * @param padre the padre
	 * @return the list
	 */
	@SuppressWarnings("unused")
	private static List<MenuOption> llenarHijos(List<MenuOption> lista, MenuOption padre) {
		List<MenuOption> retorno = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			if ((lista.get(i).getCodFormularioPadre() == padre.getCodFormulario() && lista.get(i).getCodModulo() == padre.getCodModulo()) || 
				(lista.get(i).getNivelFormulario() == 1 && lista.get(i).getCodFormularioPadre() == 0)) {
				List<MenuOption> hijos = llenarHijos(lista, lista.get(i));
				if (!hijos.isEmpty()) {
					lista.get(i).setMenuOptionsHijos(hijos);
				}
				retorno.add(lista.get(i));
			}
		}
		return retorno;
	}
	
	/**
	 * Hay fila con nivel.
	 *
	 * @param nivel the nivel
	 * @param lista the lista
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private static boolean hayFilaConNivel(int nivel, List<SistemaModuloOpcionBean> lista) {
		boolean ret = false;
		for (SistemaModuloOpcionBean sistemaModuloOpcionBean : lista) {
			if (sistemaModuloOpcionBean.getNivelFormulario().intValue() == nivel) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	/**
	 * Gets the page call.
	 *
	 * @return the page call
	 */
	public static String getPageCall() {
		String strProgram = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		strProgram = request.getHeader("Referer");
		strProgram = strProgram.substring(strProgram.lastIndexOf("/") + 1);
		return strProgram;
	}

	/**
	 * Gets the user loged.
	 *
	 * @return the user loged
	 */
	public static String getUserLoged() {
		return getAuthentication().getName();
	}
	
	/**
	 * Gets the authentication.
	 *
	 * @return the authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * Concatenar.
	 *
	 * @param strParte the str parte
	 * @return the string
	 */
	public static String concatenar(String... strParte) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strParte.length; i++) {
			sb.append(strParte[i]);
		}
		return sb.toString();
	}

	/**
	 * Prep string.
	 *
	 * @param strValue the str value
	 * @return the string
	 */
	public static String prepString(String strValue) {
		if (StringUtils.isEmpty(strValue)) {
			strValue = ConstantsCommon.VAL_STRING_NULL;
		}
		return strValue;
	}

	/**
	 * Prep integer.
	 *
	 * @param intValue the int value
	 * @return the integer
	 */
	public static Integer prepInteger(Integer intValue) {
		if (intValue == null) {
			intValue = ConstantsCommon.VAL_INT_NULL;
		}
		return intValue;
	}

	/**
	 * Prep str integer.
	 *
	 * @param strValue the str value
	 * @return the string
	 */
	public static String prepStrInteger(String strValue) {
		if (StringUtils.isEmpty(strValue)) {
			strValue = ConstantsCommon.VAL_INT_NULL.toString();
		}
		return strValue;
	}
	
	public static String obtenerTerminal(HttpServletRequest request) {
		return request.getRemoteAddr();
	}
	
	@SuppressWarnings("unchecked")
	public static void setFiltroContext(HttpServletRequest request){
		
		Map<String,Object> mapFiltro =request.getParameter("filtro")!=null
				                      ? JsonUtil.convertirCadenaJsonAObjeto(request.getParameter("filtro"), Map.class)
				                      : null;
		request.getSession().setAttribute("filtro", mapFiltro);
	}
	
	@SuppressWarnings("unchecked")
	public static String obtenerFiltroContext(HttpServletRequest request){
		
		
		String regresar=  request.getParameter("regresar");
		if(regresar!=null && regresar.equals(ConstanteServices.REGRESAR)){
			Map<String,Object> mapFiltro =request.getSession().getAttribute("filtro")!=null 
					                      ? (Map<String,Object>)request.getSession().getAttribute("filtro")
					                      : null;
		 
			if(mapFiltro!=null){
			    mapFiltro.put("mensaje", request.getParameter("mensaje"));
				return JsonUtil.convertirObjetoACadenaJson(mapFiltro);
			}				
			else{
				if(request.getParameter("mensaje")!=null && !request.getParameter("mensaje").toString().isEmpty()){	
					mapFiltro =  new HashMap<String,Object>();
					mapFiltro.put("mensaje", request.getParameter("mensaje"));
					return JsonUtil.convertirObjetoACadenaJson(mapFiltro);
				}
			} 
				
		}
		
		request.getSession().setAttribute("filtro", null);	
		return JsonUtil.convertirObjetoACadenaJson(new HashMap<String,Object>());		
    }
	
	public static AnalistaBean obtenerAnalistaBeanLogin(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		AnalistaBean usuarioAnalista = (AnalistaBean) getSessionAttribute(servletRequestAttributes.getRequest(), "userAnalistaBean");
		return usuarioAnalista;
	}
	
	public static void setSessionAttribute(HttpServletRequest request, String nombreAtributo, Object objeto) {
		request.getSession().setAttribute(nombreAtributo, objeto);
	}

	public static Object getSessionAttribute(HttpServletRequest request, String nombreAtributo) {
		return request.getSession().getAttribute(nombreAtributo);
	}
}
