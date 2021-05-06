package pe.com.sedapal.scr.web.controllers;

import static java.text.MessageFormat.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.gmd.util.exception.GmdException;
import pe.com.gmd.util.exception.MensajeExceptionUtil;
import pe.com.sedapal.common.core.beans.MenuOption;
import pe.com.sedapal.scr.web.common.BRespuestaBean;
import pe.com.sedapal.scr.web.common.ConstanteServices;
import pe.com.sedapal.scr.web.util.JsonUtil;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {
	
	
	private static final Logger LOGGER = Logger.getLogger(GeneralController.class);
	private BRespuestaBean respuestaBean = new BRespuestaBean();
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extraerMenu", method = RequestMethod.POST)
	public @ResponseBody String extraerMenu(HttpServletRequest request, Model model) throws GmdException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			 ArrayList<MenuOption> userMenuOptions = (ArrayList<MenuOption>) request.getSession().getAttribute("segUserMenuOptions");
			 String htmlMenu = generarMenu(request.getContextPath(),userMenuOptions);
			 mapResultado.put("htmlMenu", htmlMenu);
			 respuestaBean.setEstadoRespuesta(ConstanteServices.OK);
			 respuestaBean.setParametros(mapResultado);
		} catch (Exception excepcion) {			
			String[] error = MensajeExceptionUtil.obtenerMensajeError(excepcion);
			respuestaBean.setMensajeRespuesta(format(ConstanteServices.MENSAJE_ERROR, error[0]));
			respuestaBean.setEstadoRespuesta(ConstanteServices.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			LOGGER.error(error[1], excepcion);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
		
	private String generarMenu(String contextoWeb,ArrayList<MenuOption> listaOpciones){
		StringBuffer menuHTML = new StringBuffer();
        for (MenuOption opcionBean : listaOpciones) {
            menuHTML.append("<li class='nav-01'>");
            menuHTML.append("<span class='list-collapse'>" + opcionBean.getDescripcion() + " <i class='ico-collapse'></i></span>");
            if(opcionBean.getMenuOptionsHijos()!=null && opcionBean.getMenuOptionsHijos().size()>0){
                menuHTML.append("<ul class='nav-collapse'>");
                for (MenuOption subMenu : opcionBean.getMenuOptionsHijos()) {
                    menuHTML.append("<li class='nav-02'>");
                    if(subMenu.getMenuOptionsHijos()!=null && subMenu.getMenuOptionsHijos().size() > 0 ){
                        menuHTML.append("<a href='#' class>" + subMenu.getDescripcion() + "</a>");
                        menuHTML.append("<ul class='nav-collapse-sub'>");
                        for(MenuOption subMenu2: subMenu.getMenuOptionsHijos()){
                            menuHTML.append("<li class='nav-03'><a href='" + contextoWeb + subMenu2.getUrlFormulario() + "' class='nav-ajx'>" + subMenu2.getDescripcion() + "</a></li>");
                        }
                        menuHTML.append("</ul>");
                    }else{
                        menuHTML.append("<a href='" + contextoWeb + subMenu.getUrlFormulario() + "' onclick='optionClick(\"" + subMenu.getUrlFormulario() + "\")'>" + subMenu.getDescripcion() + "</a>");
                    }
                    menuHTML.append("</li>");
                }
                menuHTML.append("</ul>");
            }
            menuHTML.append("</li>");
        }
        return menuHTML.toString();
	}
	
}
