package pe.com.sedapal.common.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

import pe.com.sedapal.common.core.beans.MenuOption;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

public class CoreUtils {
	
	private CoreUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	private static final Logger log = LoggerFactory.getLogger(CoreUtils.class);

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static List<MenuOption> getMenuOptions(List<SistemaModuloOpcionBean> lista) {
			
		List<MenuOption> padres = new ArrayList<>();
		List<MenuOption> menus = new ArrayList<>();

		for (int i = 1; i < 6; i++) {
			for (SistemaModuloOpcionBean sistemaModuloOpcionBean : lista) {
				if(sistemaModuloOpcionBean.getUrlFormulario()!=null && sistemaModuloOpcionBean.getUrlFormulario().length()>=1
						&& !"/".equals(sistemaModuloOpcionBean.getUrlFormulario().substring(0, 1)) ){
					sistemaModuloOpcionBean.setUrlFormulario("/"+sistemaModuloOpcionBean.getUrlFormulario());
				} 
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
						CoreUtils.addMenuOptionHijo(menus, menuOption);						
					}
					log.debug("### LLENANDO PRIMER NIVEL {} ### {}", i, menuOption);
				}
				
			}		
		}
		
		Collections.sort(padres, new Comparator<MenuOption>() {
			@Override
			public int compare(MenuOption o1, MenuOption o2) {
				return o1.getCodModulo().compareTo(o2.getCodModulo());
			}
		});

		return padres;

	}
	
	private static void addMenuOptionHijo(List<MenuOption> menus, MenuOption menuOption){
		for (MenuOption mo : menus) {
			if (menuOption.getCodFormularioPadre().intValue() == mo.getCodFormulario().intValue() && 
				menuOption.getCodModulo().intValue() == mo.getCodModulo().intValue()) {
				mo.getMenuOptionsHijos().add(menuOption);
				break;
			}
		}
	}
	
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

	public static String getPageCall() {
		String strProgram = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		strProgram = request.getHeader("Referer");
		strProgram = strProgram.substring(strProgram.lastIndexOf('/') + 1);
		return strProgram;
	}

	public static String getUserLoged() {
		return getAuthentication().getName();
	}
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static String concatenar(String... strParte) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strParte.length; i++) {
			sb.append(strParte[i]);
		}
		return sb.toString();
	}

	public static String prepString(String parStrValue) {
		String strValue = parStrValue;
		if (StringUtils.isEmpty(strValue)) {
			strValue = ConstantsCommon.VAL_STRING_NULL;
		}
		return strValue;
	}

	public static Integer prepInteger(Integer parIntValue) {
		Integer intValue = parIntValue;
		if (intValue == null) {
			intValue = ConstantsCommon.VAL_INT_NULL;
		}
		return intValue;
	}

	public static String prepStrInteger(String parStrValue) {
		String strValue = parStrValue;
		if (StringUtils.isEmpty(strValue)) {
			strValue = ConstantsCommon.VAL_INT_NULL.toString();
		}
		return strValue;
	}

}
